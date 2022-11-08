package codeGen
<<<<<<< HEAD
import org.objectweb.asm
import org.objectweb.asm.{ClassWriter, Label, MethodVisitor, Opcodes}

import java.io.FileOutputStream
class codeGenerator extends AST_Grammar.ASTVisitor [MethodVisitor, Unit]{
  private val cw = new ClassWriter(ClassWriter.COMPUTE_MAXS)
}

object codeGenerator{
  def main(args: Array[String]): Unit ={
    val cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES)
    val className = "Test"
    cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, "java/lang/Object", null)
    val mmw = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
    mmw.visitCode()
    mmw.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
    mmw.visitLdcInsn('a')
    mmw.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(C)V", false)
    mmw.visitInsn(Opcodes.RETURN)
    mmw.visitEnd()
    mmw.visitMaxs(-1, -1)
    cw.visitEnd()

    val fos = new FileOutputStream(f"$className.class")
    fos.write(cw.toByteArray)
    fos.close()

  }
}
