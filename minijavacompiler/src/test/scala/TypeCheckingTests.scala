import Main.parse
import Main.buildAST
import AST_Grammar._
import AST_Grammar.symbolTable
import AST_Grammar.symbolTableBuilder

// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
class TypeCheckingTests extends munit.FunSuite {
  test("Binary Search Test") {
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/BinarySearch.java")
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
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/BinaryTree.java")
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
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/BubbleSort.java")
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
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/Factorial.java")
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
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/LinearSearch.java")
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
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/LinkedList.java")
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
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/QuickSort.java")
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
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/TreeVisitor.java")
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
}
