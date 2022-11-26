import AST_Grammar.{symbolTable, symbolTableBuilder, typeCheckingVisitor}
import Main.{buildAST, main, parse}

import scala.language.postfixOps
import sys.process.*

class CodeGenerationTests extends munit.FunSuite {
  test("Binary Search Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/BinarySearch.java"))
    testOutput("BinarySearch", "20\n21\n22\n23\n24\n25\n26\n27\n28\n29\n30\n31\n32\n33\n34\n35\n36\n37\n38\n99999\n0\n0\n1\n1\n1\n1\n0\n0\n999\n")
  }

  test("Binary Tree Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/BinaryTree.java"))
    testOutput("BinaryTree", "16\n100000000\n8\n16\n4\n8\n12\n14\n16\n20\n24\n28\n1\n1\n1\n0\n1\n4\n8\n14\n16\n20\n24\n28\n0\n0\n")
  }

  test("Bubble Sort Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/BubbleSort.java"))
    testOutput("BubbleSort", "20\n7\n12\n18\n2\n11\n6\n9\n19\n5\n99999\n2\n5\n6\n7\n9\n11\n12\n18\n19\n20\n0\n")
  }

  test("Factorial Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/Factorial.java"))
    testOutput("Factorial", "23357002\n")
  }

  test("Linear Search Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/LinearSearch.java"))
    testOutput("LinearSearch", "10\n11\n12\n13\n14\n15\n16\n17\n18\n9999\n0\n1\n1\n0\n55\n")
  }

  test("Linked List Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/LinkedList.java"))
    testOutput("LinkedList", "a\n25\n10000000\n39\n25\n10000000\n22\n39\n25\n1\n0\n10000000\n28\n22\n39\n25\n2220000\n-555\n-555\n28\n22\n25\n33300000\n22\n25\n44440000\n0\n")
  }

  test("Quick Sort Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/QuickSort.java"))
    testOutput("QuickSort", "20\n7\n12\n18\n2\n11\n6\n9\n19\n5\n9999\n2\n5\n6\n7\n9\n11\n12\n18\n19\n20\n0\n")
  }

  test("Tree Visitor Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/miniJavaSiteTests/TreeVisitor.java"))
    testOutput("TreeVisitor", "16\n100000000\n4\n8\n12\n14\n16\n20\n24\n28\n100000000\n50000000\n333\n333\n333\n28\n24\n333\n20\n16\n333\n333\n333\n14\n12\n8\n333\n4\n100000000\n1\n1\n1\n0\n1\n4\n8\n14\n16\n20\n24\n28\n0\n0\n")
  }

  test("If Branch Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/codeGenTests/IfBranch.java"))
    testOutput("IfBranch", "1\n0\n")
  }

  test("Char Test Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/codeGenTests/CharTest.java"))
    testOutput("CharTest", "\n\n\\\n\t\n\n\b\n\"\n\'\n\f\n")
  }

  test("Instance Variable Change Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/codeGenTests/InstanceVariableChange.java"))
    testOutput("InstanceVariableChange", "10\n")
  }

  test("Parent Instance Variable Return Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/codeGenTests/ParentInstanceVariableReturn.java"))
    testOutput("ParentInstanceVariableReturn", "0\n")
  }

  test("Grandparent Instave Variable Change Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/codeGenTests/GrandParentInstanceVariableChange.java"))
    testOutput("GrandParentInstanceVariableChange", "100\n")
  }

  test("And Test Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/codeGenTests/AndTest.java"))
    testOutput("AndTest", "1\n0\n")
  }

  test("Tree Visitor With Char Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/codeGenTests/TreeVisitorWithChar.java"))
    testOutput("TreeVisitorWithChar", "16\n_\n\n\n100000000\n4\nc\n\n\n8\na\n\n\n12\nd\n\n\n14\ng\n\n\n16\n_\n\n\n20\ne\n\n\n24\nb\n\n\n28\nf\n\n\n100000000\n50000000\n333\n333\n333\n28\n24\n333\n20\n16\n333\n333\n333\n14\n12\n8\n333\n4\n100000000\n1\n1\n1\n0\n1\n4\nc\n\n\n8\na\n\n\n14\nd\n\n\n16\n_\n\n\n20\ne\n\n\n24\nb\n\n\n28\nf\n\n\n0\n0\n")
  }

  test("Method Overload Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/codeGenTests/MethodOverLoad.java"))
    testOutput("MethodOverLoad", "-1\n8\na\n")
  }

  test("Preserve OOP Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/codeGenTests/PreserveOOP.java"))
    testOutput("PreserveOOP", "-517\n")
  }

  test("While Loop Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/codeGenTests/WhileTest.java"))
    testOutput("WhileTest", "0\n1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n")
  }

  test("Method With Parent Type Params Output Test") {
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/codeGenTests/MethodWithParentTypeParams.java"))
    testOutput("MethodWithParentTypeParams", "200\n")
  }

  test("Array Test Output Test"){
    main(Array("/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/testFiles/codeGenTests/ArrayTest.java"))
    testOutput("ArrayTest", "0\n1\n2\n3\n4\n0\n")
  }




  def testOutput(compiledFileName: String, expectedOutput: String): Unit = {
    val command = "java -cp " + "/home/dominic/IdeaProjects/miniJavaCompiler/minijavacompiler/compiledPrograms/" + compiledFileName + " " + compiledFileName
    val output = command !!

    println(compiledFileName + " Output: ")
    println(output)
    assertEquals(output, expectedOutput)
  }
}
