package AST_Grammar

import scala.collection.mutable
import scala.collection.mutable.HashMap

class symbolTable(tableName: String):
  private val name = tableName
  private var variableTable = new mutable.HashMap[Any, symbolTableVal]()
  private var classTable = new mutable.HashMap[Any, symbolTableVal]()
  private var methodTable = new mutable.HashMap[Any, symbolTableVal]()
  private var parentTable = None:Option[symbolTable]

  def getClassKeys: List[Any] = classTable.keys.toList
  def getMethodKeys: List[Any] = methodTable.keys.toList
  def checkIfClassIDExists(key: Any): Boolean = classTable.contains(key)

  def getName: String = name
  
  def checkIfMethodIDExists(key: Any): Boolean = methodTable.contains(key)
  def checkIfVarIDExists(key: Any): Boolean = variableTable.contains(key)
  

  def putVarVal(key: Any, newVal: symbolTableVal): Unit = variableTable.put(key, newVal)

  def putClassVal(key: Any, newVal: symbolTableVal): Unit = classTable.put(key, newVal)

  def putMethodVal(key: Any, newVal: symbolTableVal): Unit = methodTable.put(key, newVal)

  def getVariableVal(key: Any): Option[symbolTableVal] = {
    if(variableTable.contains(key)){
      Some(variableTable(key))
    }else{
      None
    }
  }

  def getMethodVal(key: Any): Option[symbolTableVal] = {
    if (methodTable.contains(key)) {
      Some(methodTable(key))
    } else {
      None
    }
  }

  def getClassVal(key: Any): Option[symbolTableVal] = {
    if (classTable.contains(key)) {
      Some(classTable(key))
    } else {
      None
    }
  }

  /*
  this is so when a class is not in scope & we can check our parent to see if the class or variable was referenced
  previously
  */
  def getParentTable: Option[symbolTable] = parentTable

  //this is so we can set the parent table upon hitting a method or class reference
  def setParentTable(parent: symbolTable): Unit = parentTable = Some(parent)