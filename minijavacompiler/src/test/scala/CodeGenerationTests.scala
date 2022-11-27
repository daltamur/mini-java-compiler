import AST_Grammar.{symbolTable, symbolTableBuilder, typeCheckingVisitor}
import Main.{buildAST, main, parse}

import scala.language.postfixOps
import sys.process.*

class CodeGenerationTests extends munit.FunSuite {
  test("Binary Search Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/miniJavaSiteTests/BinarySearch.java"))
    testOutput("BinarySearch", "20\n21\n22\n23\n24\n25\n26\n27\n28\n29\n30\n31\n32\n33\n34\n35\n36\n37\n38\n99999\n0\n0\n1\n1\n1\n1\n0\n0\n999\n")
  }

  test("Binary Tree Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/miniJavaSiteTests/BinaryTree.jav"))
    testOutput("BinaryTree", "16\n100000000\n8\n16\n4\n8\n12\n14\n16\n20\n24\n28\n1\n1\n1\n0\n1\n4\n8\n14\n16\n20\n24\n28\n0\n0\n")
  }

  test("Bubble Sort Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/miniJavaSiteTests/BubbleSort.java"))
    testOutput("BubbleSort", "20\n7\n12\n18\n2\n11\n6\n9\n19\n5\n99999\n2\n5\n6\n7\n9\n11\n12\n18\n19\n20\n0\n")
  }

  test("Factorial Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/miniJavaSiteTests/Factorial.java"))
    testOutput("Factorial", "23357002\n")
  }

  test("Linear Search Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/miniJavaSiteTests/LinearSearch.java"))
    testOutput("LinearSearch", "10\n11\n12\n13\n14\n15\n16\n17\n18\n9999\n0\n1\n1\n0\n55\n")
  }

  test("Linked List Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/miniJavaSiteTests/LinkedList.java"))
    testOutput("LinkedList", "a\n25\n10000000\n39\n25\n10000000\n22\n39\n25\n1\n0\n10000000\n28\n22\n39\n25\n2220000\n-555\n-555\n28\n22\n25\n33300000\n22\n25\n44440000\n0\n")
  }

  test("Quick Sort Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/miniJavaSiteTests/QuickSort.java"))
    testOutput("QuickSort", "20\n7\n12\n18\n2\n11\n6\n9\n19\n5\n9999\n2\n5\n6\n7\n9\n11\n12\n18\n19\n20\n0\n")
  }

  test("Tree Visitor Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/miniJavaSiteTests/TreeVisitor.java"))
    testOutput("TreeVisitor", "16\n100000000\n4\n8\n12\n14\n16\n20\n24\n28\n100000000\n50000000\n333\n333\n333\n28\n24\n333\n20\n16\n333\n333\n333\n14\n12\n8\n333\n4\n100000000\n1\n1\n1\n0\n1\n4\n8\n14\n16\n20\n24\n28\n0\n0\n")
  }

  test("If Branch Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/IfBranch.java"))
    testOutput("IfBranch", "1\n0\n")
  }

  test("Char Special Character Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/CharTest.java"))
    testOutput("CharTest", "\n\n\\\n\t\n\n\b\n\"\n\'\n\f\n")
  }

  test("Instance Variable Change Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/InstanceVariableChange.java"))
    testOutput("InstanceVariableChange", "10\n")
  }

  test("Parent Instance Variable Return Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/ParentInstanceVariableReturn.java"))
    testOutput("ParentInstanceVariableReturn", "0\n")
  }

  test("Grandparent Instave Variable Change Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/GrandParentInstanceVariableChange.java"))
    testOutput("GrandParentInstanceVariableChange", "100\n")
  }

  test("And Test Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/AndTest.java"))
    testOutput("AndTest", "1\n0\n")
  }

  test("Tree Visitor With Char Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/TreeVisitorWithChar.java"))
    testOutput("TreeVisitorWithChar", "16\n_\n\n\n100000000\n4\nc\n\n\n8\na\n\n\n12\nd\n\n\n14\ng\n\n\n16\n_\n\n\n20\ne\n\n\n24\nb\n\n\n28\nf\n\n\n100000000\n50000000\n333\n333\n333\n28\n24\n333\n20\n16\n333\n333\n333\n14\n12\n8\n333\n4\n100000000\n1\n1\n1\n0\n1\n4\nc\n\n\n8\na\n\n\n14\nd\n\n\n16\n_\n\n\n20\ne\n\n\n24\nb\n\n\n28\nf\n\n\n0\n0\n")
  }

  test("Method Overload Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/MethodOverLoad.java"))
    testOutput("MethodOverLoad", "-1\n8\na\n")
  }

  test("Preserve OOP Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/PreserveOOP.java"))
    testOutput("PreserveOOP", "-517\n")
  }

  test("While Loop Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/WhileTest.java"))
    testOutput("WhileTest", "0\n1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n")
  }

  test("Method With Parent Type Params Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/MethodWithParentTypeParams.java"))
    testOutput("MethodWithParentTypeParams", "200\n")
  }

  test("Array Test Output Test"){
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/ArrayTest.java"))
    testOutput("ArrayTest", "0\n1\n2\n3\n4\n0\n")
  }
  test("Array Variable Overload Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/ArrayVariableOverloadTest.java"))
    testOutput("ArrayVariableOverloadTest", "0\n1\n2\n3\n4\n0\n1\n2\n3\n4\n0\n")
  }

  test("Array Parameter Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/ArrayParameterTest.java"))
    testOutput("ArrayParameterTest", "0\n1\n2\n3\n4\n0\n")
  }

  test("Array Instance Var Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/ArrayInstanceVarTest.java"))
    testOutput("ArrayInstanceVarTest", "0\n1\n2\n3\n4\n0\n")
  }

  test("Array From Parent Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/ArrayFromParentTest.java"))
    testOutput("ArrayFromParentTest", "0\n1\n2\n3\n4\n0\n")
  }

  test("Array From Grandparent Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/ArrayFromGrandparentTest.java"))
    testOutput("ArrayFromGrandparentTest", "0\n1\n2\n3\n4\n0\n")
  }

  test("Array From Method Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/ArrayFromMethod.java"))
    testOutput("ArrayFromMethod", "1000\n")
  }

  test("Add Method Call Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/AddMethodCall.java"))
    testOutput("AddMethodCall", "3\n")
  }
  test("Array length Add Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/ArrLengthAdd.java"))
    testOutput("ArrLengthAdd", "15\n")
  }

  test("Array length Multiply Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/ArrLengthMult.java"))
    testOutput("ArrLengthMult", "50\n")
  }

  test("Array length Subtract Output Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/ArrLengthSub.java"))
    testOutput("ArrLengthSub", "-5\n")
  }

  test("Method Array Add Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/MethodArrAdd.java"))
    testOutput("MethodArrAdd", "10\n")
  }

  test("Method Array Sub Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/MethodArrSub.java"))
    testOutput("MethodArrSub", "0\n")
  }

  test("Method Array Multiply Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/MethodArrMult.java"))
    testOutput("MethodArrMult", "25\n")
  }

  test("Method Multiply Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/MultMethodCall.java"))
    testOutput("MultMethodCall", "2\n")
  }

  test("Order of Operations With Methods & Array Lengths Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/OOPMethodArr.java"))
    testOutput("OOPMethodArr", "105\n")
  }

  test("Method Subtract Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/SubMethodCall.java"))
    testOutput("SubMethodCall", "-1\n")
  }

  test("Char Demo Test") {
    main(Array(System.getProperty("user.dir") + "/testFiles/codeGenTests/CharDemo.java"))
    testOutput("CharDemo", "q\nQ\nw\nW\ne\nE\nr\nR\nt\nT\ny\nY\nu\nU\ni\nI\no\nO\np\nP\na\nA\ns\nS\nd\nD\nf\nF\ng\nG\nh\nH\nj\nJ\nk\nK\nl\nL\nz\nZ\nx\nX\nc\nC\nv\nV\nb\nB\nn\nN\nm\nM\n0\n)\n1\n!\n2\n@\n3\n#\n4\n$\n5\n%\n6\n^\n7\n&\n8\n*\n9\n(\n`\n~\n-\n_\n=\n+\n[\n{\n]\n}\n;\n:\n\"\n,\n<\n.\n>\n/\n?\n")
  }



  def testOutput(compiledFileName: String, expectedOutput: String): Unit = {
    val command = "java -cp " + System.getProperty("user.dir") + "/compiledPrograms/" + compiledFileName + " " + compiledFileName
    val output = command !!

    println(compiledFileName + " Output: ")
    println(output)
    assertEquals(output, expectedOutput)
  }
}
