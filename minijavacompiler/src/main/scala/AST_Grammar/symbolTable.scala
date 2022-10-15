package AST_Grammar

import scala.collection.mutable
import scala.collection.mutable.HashMap

class symbolTable:
  private var variableTable = new mutable.HashMap[Any, Any]()
  private var classTable = new mutable.HashMap[Any, Any]()
  private var methodTable = new mutable.HashMap[Any, Any]()
  private var parentTable = None:Option[symbolTable]
  
  def checkIfClassIDExists(key: Any): Boolean = classTable.contains(key)

  def putVarVal(key: Any, newVal: Any): Unit = variableTable.put(key, newVal)

  def putClassVal(key: Any, newVal: Any): Unit = classTable.put(key, newVal)

  def putMethodVal(key: Any, newVal: Any): Unit = methodTable.put(key, newVal)

  def getVariableVal(key: Any): Option[Any] = {
    if(variableTable.contains(key)){
      Some(variableTable.get(key))
    }else{
      None
    }
  }

  def getMethodVal(key: Any): Option[Any] = {
    if (methodTable.contains(key)) {
      Some(methodTable.get(key))
    } else {
      None
    }
  }

  def getClassVal(key: Any): Option[Any] = {
    if (classTable.contains(key)) {
      Some(classTable.get(key))
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