package AST_Grammar

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control._

class symbolTableBuilder extends ASTVisitor[symbolTable, AST_Grammar.symbolTableVal] {
  private var curError:Option[error] = None

  def getError: Option[error] = curError
  override def visitGoal(goal: goal, a: symbolTable): AST_Grammar.symbolTableVal = {
    //get the symbolTable for the main class and put it in the goal symbol table
    val mainClassSymbolTable = new symbolTable
    mainClassSymbolTable.setParentTable(a)
    a.putClassVal(goal.main.className.name, this.visit(goal.main, mainClassSymbolTable))
    for(currentClass <- goal.classes){
      if (a.checkIfClassIDExists(currentClass.get.className.name)) {
        println(Console.RED+"ERROR "+Console.GREEN+"on line "+Console.BLUE+"\u001b[4m" +  currentClass.get.line +Console.GREEN +"\u001b[0m: Class named "+ currentClass.get.className.name + " already exists.")
        System.exit(1)
      } else {
        println(currentClass.get.className.name)
        val curClassSymbolTable = new symbolTable
        curClassSymbolTable.setParentTable(a)
        a.putClassVal(currentClass.get.className.name, visit(currentClass.get, curClassSymbolTable))
      }
    }
    AST_Grammar.programVal(a)
  }

  override def visitMainClass(clazz: mainClass, a: symbolTable): AST_Grammar.symbolTableVal = {
    val mainClassSymbolTable = new symbolTable
    mainClassSymbolTable.setParentTable(a)
    mainClassSymbolTable.putVarVal(clazz.commandLineArgs.name, AST_Grammar.commandLineArgs())
    a.putMethodVal("main", methodVal(mainClassSymbolTable, List[varType]{commandLineArgs()}, voidType(), clazz.line))
    classVal(a, None, clazz.line)
  }

  override def visitClass(klass: klass, a: symbolTable): AST_Grammar.symbolTableVal = {
    // we need to get the variable declarations and methods
    val classLine = klass.line
    //var decs first
    for(currentVarDec <- klass.variables){
      val varDecLine = currentVarDec.get.line
      val varType = currentVarDec.get.typeval
      val varName = currentVarDec.get.name.name
      varType match
        case integer() => a.putVarVal(varName, AST_Grammar.variableVal(integerType(), varDecLine))
        case character() => a.putVarVal(varName, AST_Grammar.variableVal(characterType(), varDecLine))
        case identifierType(_) => a.putVarVal(varName, AST_Grammar.variableVal(classType(varType.asInstanceOf[AST_Grammar.identifier].name), varDecLine))
        case intArray() => a.putVarVal(varName, AST_Grammar.variableVal(AST_Grammar.intArrayType(), varDecLine))
        case boolean() => a.putVarVal(varName, AST_Grammar.variableVal(booleanType(), varDecLine))
    }

    //method declarations next
    for(currentMethod <- klass.methods){
      val methodSymbolTable = new symbolTable
      methodSymbolTable.setParentTable(a)
      val methodName = currentMethod.get.methodName.name
      val curMethodVal = visit(currentMethod.get, methodSymbolTable).asInstanceOf[methodVal]
      val methodKey = (methodName, curMethodVal.paramTypes)
      if (a.checkIfMethodIDExists(methodKey)) {
        var curError = "ERROR on line " + currentMethod.get.line + ": " + methodName + "("
        for(typeVal <- methodKey._2){
          curError+=varTypeToString(typeVal)
          if(methodKey._2.last != typeVal){
            curError+=", "
          }
        }
        curError += ") has already been defined in this scope"
        println(curError)
        System.exit(1)
      }
      a.putMethodVal(methodKey, curMethodVal)
    }

    klass.extendedClassName match
      case Some(value) =>
        AST_Grammar.classVal(a, Some(value.name), classLine)
      case None =>   AST_Grammar.classVal(a, None, classLine)
  }

  override def visitMethod(method: method, a: symbolTable): symbolTableVal = {
    val methodLine = method.line
    val methodReturnType = method.returnType
    val params = method.params
    //get a list of the parameter types
    val paramTypes = new ListBuffer[AST_Grammar.varType]
    for (param <- params) {
      val paramType = param._1
      val paramID = param._2.name
      val symbolTableParam = AST_Grammar.getVarType(paramType)
      paramTypes += symbolTableParam
      a.putVarVal(paramID, symbolTableParam)
    }

    //check variables
    for (currentVarDec <- method.variables) {
      val varLine = currentVarDec.get.line
      val varType = currentVarDec.get.typeval
      val varName = currentVarDec.get.name.name
      if(a.checkIfVarIDExists(varName)){
        println("ERROR on line "+varLine+": "+varName+ " is already defined in the current scope")
        System.exit(1)
      }
      varType match
        case integer() => a.putVarVal(varName, AST_Grammar.variableVal(integerType(), varLine))
        case character() => a.putVarVal(varName, AST_Grammar.variableVal(characterType(), varLine))
        case x: identifierType => a.putVarVal(varName, AST_Grammar.variableVal(classType(x.name.name), varLine))
        case intArray() => a.putVarVal(varName, AST_Grammar.variableVal(AST_Grammar.intArrayType(), varLine))
        case boolean() => a.putVarVal(varName, AST_Grammar.variableVal(booleanType(), varLine))
    }


    methodVal(a, paramTypes.toList, AST_Grammar.getVarType(methodReturnType), methodLine)
  }

  def checkForCircularInheritance(programSymbolTable: symbolTable): Unit = {
    val classIDs = programSymbolTable.getClassKeys
    val loop = new Breaks;
    loop.breakable {
      for (classID <- classIDs) {
        if (curError.isDefined) {
          loop.break()
        }
        val curClass = programSymbolTable.getClassVal(classID).get.asInstanceOf[AST_Grammar.classVal]
        curClass.extendedClass match
          case Some(parentClass) =>
            val currentClassList = new ListBuffer[String]
            currentClassList += classID.asInstanceOf[String]
            if (checkParentClassType(programSymbolTable, parentClass, currentClassList, curClass)) {
              curError = Some(circularInheritanceError(classID.asInstanceOf[String], parentClass, curClass.line))
              loop.break()
            }
          case None =>
      }
    }
  }

  def checkParentClassType(programSymbolTable: symbolTable, curClassID: String, currentClasses: ListBuffer[String], childClass: classVal): Boolean = {
    if(!programSymbolTable.getClassKeys.contains(curClassID)){
      curError = Some(noSuchParentMethodError(curClassID, childClass.line))
      false
    }else{
      val curClass = programSymbolTable.getClassVal(curClassID)
      curClass.get.asInstanceOf[classVal].extendedClass match
        case Some(extendedClass) =>
          if(currentClasses.toList.contains(extendedClass)){
            true
          }else{
            currentClasses += extendedClass
            checkParentClassType(programSymbolTable, extendedClass, currentClasses, curClass.get.asInstanceOf[classVal])
          }
        case None => false
    }

  }

  def checkMethodReturnTypes(programSymbolTable: symbolTable): Unit = {
    val outloop = new Breaks;
    val inloop = new Breaks;
    val classIDs = programSymbolTable.getClassKeys
    outloop.breakable{
    for (classID <- classIDs) {
      val currentClass = programSymbolTable.getClassVal(classID).get.asInstanceOf[classVal]
      //iterate through the methods of the current class, make sure the return type is defined if it returns a class
      val methodKeys = currentClass.classScope.getMethodKeys
      inloop.breakable{
      for (methodID <- methodKeys) {
        val currentMethod = currentClass.classScope.getMethodVal(methodID).get.asInstanceOf[methodVal]
        val methodReturnType = currentMethod.returnType
        methodReturnType match
          case x: classType =>
            if (!programSymbolTable.checkIfClassIDExists(x.clazz)) {
              curError = Some(noSuchReturnType(classID.asInstanceOf[String], methodID.asInstanceOf[String], x.clazz, currentMethod.line))
              inloop.break
            }
          case _ =>
      }
    }
      if (curError.isDefined) {
        outloop.break
      }
    }
  }
  }
}
