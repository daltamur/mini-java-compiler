val scala3Version = "3.2.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "miniJavaCompiler",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,
    assemblyJarName in assembly := "mjavac.jar",
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", _*) => MergeStrategy.discard
      case _ => MergeStrategy.first
    },
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,

  )

libraryDependencies ++= Seq(
  "org.antlr" % "antlr4-runtime" % "4.10.1",
  "org.antlr" % "stringtemplate" % "3.2",
  "org.ow2.asm" % "asm" % "9.4",
  "org.soot-oss" % "soot" % "4.2.1"
)

