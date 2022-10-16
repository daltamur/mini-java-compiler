package AST_Grammar

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class symbolTableBuilder extends ASTVisitor[symbolTable, AST_Grammar.symbolTableVal] {

  override def visitGoal(goal: goal, a: symbolTable): AST_Grammar.symbolTableVal = {
    //get the symbolTable for the main class and put it in the goal symbol table
    val mainClassSymbolTable = new symbolTable
    mainClassSymbolTable.setParentTable(a)
    a.putClassVal(goal.main.className.name, this.visit(goal.main, mainClassSymbolTable))
    for(currentClass <- goal.classes){
      if (a.checkIfClassIDExists(currentClass.get.className.name)) {
        println("ERROR: " + currentClass.get.className.name + " already exists.")
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
    val mainClassName = clazz.className.name
    val mainClassSymbolTable = new symbolTable
    mainClassSymbolTable.setParentTable(a)
    mainClassSymbolTable.putVarVal(clazz.commandLineArgs.name, AST_Grammar.commandLineArgs())
    a.putMethodVal("main", methodVal(mainClassSymbolTable, List[varType]{commandLineArgs()}, voidType()))
    classVal(a, None)
  }

  override def visitClass(klass: klass, a: symbolTable): AST_Grammar.symbolTableVal = {
    // we need to get the variable declarations and methods

    //var decs first
    for(currentVarDec <- klass.variables){
      val varType = currentVarDec.get.typeval
      val varName = currentVarDec.get.name.name
      varType match
        case integer() => a.putVarVal(varName, AST_Grammar.variableVal(integerType()))
        case character() => a.putVarVal(varName, AST_Grammar.variableVal(characterType()))
        case identifierType(_) => a.putVarVal(varName, AST_Grammar.variableVal(classType(varType.asInstanceOf[AST_Grammar.identifier].name)))
        case intArray() => a.putVarVal(varName, AST_Grammar.variableVal(AST_Grammar.intArrayType()))
        case boolean() => a.putVarVal(varName, AST_Grammar.variableVal(booleanType()))
    }

    //method declarations next
    for(currentMethod <- klass.methods){
      val methodSymbolTable = new symbolTable
      methodSymbolTable.setParentTable(a)
      val methodName = currentMethod.get.methodName.name
      if(a.checkIfMethodIDExists(methodName)){
        println("ERROR: " + methodName + " has already been defined in this scope")
        System.exit(1)
      }
      a.putMethodVal(methodName, visit(currentMethod.get, methodSymbolTable))
    }

    klass.extendedClassName match
      case Some(value) =>
        AST_Grammar.classVal(a, Some(value.name))
      case None =>   AST_Grammar.classVal(a, None)
  }

  override def visitMethod(method: method, a: symbolTable): symbolTableVal = {
    val methodName = method.methodName.name
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
      val varType = currentVarDec.get.typeval
      val varName = currentVarDec.get.name.name
      if(a.checkIfVarIDExists(varName)){
        println("ERROR: "+varName+ " is already defined in the current scope")
      }
      varType match
        case integer() => a.putVarVal(varName, AST_Grammar.variableVal(integerType()))
        case character() => a.putVarVal(varName, AST_Grammar.variableVal(characterType()))
        case x: identifierType => a.putVarVal(varName, AST_Grammar.variableVal(classType(x.name.name)))
        case intArray() => a.putVarVal(varName, AST_Grammar.variableVal(AST_Grammar.intArrayType()))
        case boolean() => a.putVarVal(varName, AST_Grammar.variableVal(booleanType()))
    }


    methodVal(a, paramTypes.toList, AST_Grammar.getVarType(methodReturnType))
  }

  def checkForCircularInheritance(programSymbolTable: symbolTable): Unit = {
    val classIDs = programSymbolTable.getClassKeys
    for(classID <- classIDs){
      val curClass = programSymbolTable.getClassVal(classID).get.asInstanceOf[AST_Grammar.classVal]
      curClass.extendedClass match
        case Some(parentClass) =>
          val currentClassList = new ListBuffer[String]
          currentClassList += classID.asInstanceOf[String]
          if(checkParentClassType(programSymbolTable, parentClass, currentClassList)){
            println("ERROR: Circular Inheritance happening at class " + classID + " extending " + parentClass)
            System.exit(1)
          }
        case None =>
    }
  }

  def checkParentClassType(programSymbolTable: symbolTable, curClassID: String, currentClasses: ListBuffer[String]): Boolean = {
    if(!programSymbolTable.getClassKeys.contains(curClassID)){
      println("ERROR: Extended class "+curClassID+" does not exist")
      System.exit(1)
      false
    }else{
      val curClass = programSymbolTable.getClassVal(curClassID)
      curClass.get.asInstanceOf[classVal].extendedClass match
        case Some(extendedClass) =>
          if(currentClasses.toList.contains(extendedClass)){
            true
          }else{
            currentClasses += extendedClass
            checkParentClassType(programSymbolTable, extendedClass, currentClasses)
          }
        case None => false
    }

  }

  def checkMethodReturnTypes(programSymbolTable: symbolTable): Unit = {
    val classIDs = programSymbolTable.getClassKeys
    for(classID <- classIDs){
      val currentClass = programSymbolTable.getClassVal(classID).get.asInstanceOf[classVal]
      //iterate through the methods of the current class, make sure the return type is defined if it returns a class
      val methodKeys = currentClass.classScope.getMethodKeys
      for(methodID <- methodKeys){
        val currentMethod = currentClass.classScope.getMethodVal(methodID).get.asInstanceOf[methodVal]
        val methodReturnType = currentMethod.returnType
        methodReturnType match
          case x: classType =>
            if(!programSymbolTable.checkIfClassIDExists(x.clazz)){
              println("ERROR: method "+methodID.asInstanceOf[String]+" of class "+classID.asInstanceOf[String]+" has an undefined return type of "+x.clazz)
            }
          case _ =>
      }
    }
  }
}
