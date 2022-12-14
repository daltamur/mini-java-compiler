import Main.{buildAST, parse}
import AST_Grammar.*
import AST_Grammar.symbolTable
import AST_Grammar.symbolTableBuilder

class TypeCheckingTests extends munit.FunSuite {
  test("Binary Search Test") {
    val parser = parse("testFiles/miniJavaSiteTests/BinarySearch.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Binary Tree Test"){
    val parser = parse("testFiles/miniJavaSiteTests/BinaryTree.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("BubbleSort Test") {
    val parser = parse("testFiles/miniJavaSiteTests/BubbleSort.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Factorial Test") {
    val parser = parse("testFiles/miniJavaSiteTests/Factorial.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Linear Search Test") {
    val parser = parse("testFiles/miniJavaSiteTests/LinearSearch.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Linked List Test") {
    val parser = parse("testFiles/miniJavaSiteTests/LinkedList.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("QuickSort Test") {
    val parser = parse("testFiles/miniJavaSiteTests/QuickSort.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Tree Visitor Test") {
    val parser = parse("testFiles/miniJavaSiteTests/TreeVisitor.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }
  test("Array Length Fail Test") {
    val parser = parse("testFiles/typeCheckingTests/arrayLengthFail.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }
  test("Array Length Fail Test") {
    val parser = parse("testFiles/typeCheckingTests/ArrayRightSideInvalidIndex.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Boolean 1 Fail Test") {
    val parser = parse("testFiles/typeCheckingTests/Boolean1Invalid.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Boolean 2 Fail Test") {
    val parser = parse("testFiles/typeCheckingTests/Boolean2Invalid.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Circular Inheritance 1 Fail Test") {
    val parser = parse("testFiles/typeCheckingTests/circularInheritance1.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isDefined)
    println(symbolTableBuilder.getError.get.errorVal)
  }

  test("Circular Inheritance 2 Fail Test") {
    val parser = parse("testFiles/typeCheckingTests/circularInheritance2.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isDefined)
    println(symbolTableBuilder.getError.get.errorVal)
  }

  test("Circular Inheritance 3 Fail Test") {
    val parser = parse("testFiles/typeCheckingTests/circularInheritance3.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isDefined)
    println(symbolTableBuilder.getError.get.errorVal)
  }

  test("Circular Inheritance 3V1 Fail Test") {
    val parser = parse("testFiles/typeCheckingTests/circularInheritance3v1.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isDefined)
    println(symbolTableBuilder.getError.get.errorVal)
  }

  test("Class ALready Defined Fail Test") {
    val parser = parse("testFiles/typeCheckingTests/classAlreadyDefined.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isDefined)
    println(symbolTableBuilder.getError.get.errorVal)
  }

  test("If Condition Not Boolean") {
    val parser = parse("testFiles/typeCheckingTests/ifCondNotBoolean.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Inherited Assignment") {
    val parser = parse("testFiles/typeCheckingTests/inheritedAssignment.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Inherited Assignment Fail") {
    val parser = parse("testFiles/typeCheckingTests/inheritedAssignmentInvalid.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Inherited Method 1") {
    val parser = parse("testFiles/typeCheckingTests/inheritedMethod1.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Inherited Return 1") {
    val parser = parse("testFiles/typeCheckingTests/invalidReturn1.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Inherited Return 2") {
    val parser = parse("testFiles/typeCheckingTests/invalidReturn2.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Inherited Method 2") {
    val parser = parse("testFiles/typeCheckingTests/inheritedMethod2.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Inherited Method 3") {
    val parser = parse("testFiles/typeCheckingTests/inheritedMethod3.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Inherited Method Params Simple") {
    val parser = parse("testFiles/typeCheckingTests/inheritedMethodParamsSimple.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Inherited Method Params Hard") {
    val parser = parse("testFiles/typeCheckingTests/inheritedMethodParamsHard.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Inherited Return Type") {
    val parser = parse("testFiles/typeCheckingTests/inheritedReturnType.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Int array ID invalid") {
    val parser = parse("testFiles/typeCheckingTests/intArrayInvalidID.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Int array ID index") {
    val parser = parse("testFiles/typeCheckingTests/intArrayInvalidIndex.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Int array value invalid") {
    val parser = parse("testFiles/typeCheckingTests/intArrayInvalidValue.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Invalid Boolean") {
    val parser = parse("testFiles/typeCheckingTests/invalidBoolean.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }
  test("Invalid Class Type") {
    val parser = parse("testFiles/typeCheckingTests/invalidClassType.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }
  test("Invalid Extended Class") {
    val parser = parse("testFiles/typeCheckingTests/invalidExtendedClass.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isDefined)
    println(symbolTableBuilder.getError.get.errorVal)
  }

  test("Invalid Inherited Method") {
    val parser = parse("testFiles/typeCheckingTests/invalidInheritedMethod.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Invalid Inherited Method Return Type") {
    val parser = parse("testFiles/typeCheckingTests/invalidInheritedReturnType.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Invalid Inherited Method Parameter Type") {
    val parser = parse("testFiles/typeCheckingTests/invalidMethodParamType.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isDefined)
    println(symbolTableBuilder.getError.get.errorVal)
  }

  test("Invalid Inherited Method Return Type") {
    val parser = parse("testFiles/typeCheckingTests/invalidMethodReturnType.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Invalid Negated Boolean Type") {
    val parser = parse("testFiles/typeCheckingTests/invalidNegatedBoolean.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Invalid New Int") {
    val parser = parse("testFiles/typeCheckingTests/invalidNewInt.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Invalid Print") {
    val parser = parse("testFiles/typeCheckingTests/invalidPrint.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }
  test("Method Already Defined") {
    val parser = parse("testFiles/typeCheckingTests/methodAlreadyDefined.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isDefined)
    println(symbolTableBuilder.getError.get.errorVal)
  }

  test("Method Overload") {
    val parser = parse("testFiles/typeCheckingTests/methodOverloadExample.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Math Invalid") {
    val parser = parse("testFiles/typeCheckingTests/multiplicationAdditionSubtractionInvalid.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }
  test("Math Valid") {
    val parser = parse("testFiles/typeCheckingTests/multiplicationAdditionSubtractionTest.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Invalid Int Size Type") {
    val parser = parse("testFiles/typeCheckingTests/newIntArrayInvalidSize.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }
  test("Valid Class Type Type") {
    val parser = parse("testFiles/typeCheckingTests/validClassType.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Valid Method Param Type") {
    val parser = parse("testFiles/typeCheckingTests/validMethodParamType.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Var Check 1") {
    val parser = parse("testFiles/typeCheckingTests/varCheck1.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Var Check 2") {
    val parser = parse("testFiles/typeCheckingTests/varCheck2.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Var Check 3") {
    val parser = parse("testFiles/typeCheckingTests/varCheck3.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }

  test("Var Check Fail") {
    val parser = parse("testFiles/typeCheckingTests/varCheckFail.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }
  test("Var Already Defined Class Scope") {
    val parser = parse("testFiles/typeCheckingTests/variableAlreadyDefinedClassScope.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isDefined)
    println(symbolTableBuilder.getError.get.errorVal)
  }

  test("Var Already Defined Method Scope") {
    val parser = parse("testFiles/typeCheckingTests/variableAlreadyDefinedMethodScope.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isDefined)
    println(symbolTableBuilder.getError.get.errorVal)
  }
  test("Var Already Defined Method Scope 2") {
    val parser = parse("testFiles/typeCheckingTests/variableAlreadyDefinedMethodScope2.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isDefined)
    println(symbolTableBuilder.getError.get.errorVal)
  }
  test("Weird Array") {
    val parser = parse("testFiles/typeCheckingTests/weirdArray.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isEmpty)
  }
  test("While Condition Not Boolean") {
    val parser = parse("testFiles/typeCheckingTests/WhileCondNotBoolean.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }

  test("Chained Methods") {
    val parser = parse("testFiles/testFile.java")
    //make the ast
    val programAST = buildAST(parser)
    assertEquals(programAST._2, false)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST._1.get, symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodParams(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    assert(symbolTableBuilder.getError.isEmpty)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST._1.get, symbolTable)
    assert(statementTypeChecker.getCurError.isDefined)
    println(statementTypeChecker.getCurError.get.errorVal)
  }
























}
