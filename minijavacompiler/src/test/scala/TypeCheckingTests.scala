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
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST.get, symbolTable)
    var obtained = symbolTableBuilder.getError
    var expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodParams(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST.get, symbolTable)
    obtained = statementTypeChecker.getCurError
    expected = None
    assertEquals(obtained, expected)
  }

  test("Binary Tree Test"){
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/BinaryTree.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST.get, symbolTable)
    var obtained = symbolTableBuilder.getError
    var expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodParams(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST.get, symbolTable)
    obtained = statementTypeChecker.getCurError
    expected = None
    assertEquals(obtained, expected)
  }

  test("BubbleSort Test") {
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/BubbleSort.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST.get, symbolTable)
    var obtained = symbolTableBuilder.getError
    var expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodParams(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST.get, symbolTable)
    obtained = statementTypeChecker.getCurError
    expected = None
    assertEquals(obtained, expected)
  }

  test("Factorial Test") {
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/Factorial.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST.get, symbolTable)
    var obtained = symbolTableBuilder.getError
    var expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodParams(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST.get, symbolTable)
    obtained = statementTypeChecker.getCurError
    expected = None
    assertEquals(obtained, expected)
  }

  test("Linear Search Test") {
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/LinearSearch.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST.get, symbolTable)
    var obtained = symbolTableBuilder.getError
    var expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodParams(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST.get, symbolTable)
    obtained = statementTypeChecker.getCurError
    expected = None
    assertEquals(obtained, expected)
  }

  test("Linked List Test") {
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/LinkedList.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST.get, symbolTable)
    var obtained = symbolTableBuilder.getError
    var expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodParams(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST.get, symbolTable)
    obtained = statementTypeChecker.getCurError
    expected = None
    assertEquals(obtained, expected)
  }

  test("QuickSort Test") {
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/QuickSort.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST.get, symbolTable)
    var obtained = symbolTableBuilder.getError
    var expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodParams(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST.get, symbolTable)
    obtained = statementTypeChecker.getCurError
    expected = None
    assertEquals(obtained, expected)
  }

  test("Tree Visitor Test") {
    val parser = parse("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/TreeVisitor.java")
    //make the ast
    val programAST = buildAST(parser)
    //do the checks
    //make the symbol table
    val symbolTable = new symbolTable("goal")
    val symbolTableBuilder = new symbolTableBuilder
    symbolTableBuilder.visit(programAST.get, symbolTable)
    var obtained = symbolTableBuilder.getError
    var expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodParams(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkForCircularInheritance(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    symbolTableBuilder.checkMethodReturnTypes(symbolTable)
    obtained = symbolTableBuilder.getError
    expected = None
    assertEquals(obtained, expected)
    val statementTypeChecker = new typeCheckingVisitor
    statementTypeChecker.visit(programAST.get, symbolTable)
    obtained = statementTypeChecker.getCurError
    expected = None
    assertEquals(obtained, expected)
  }
}
