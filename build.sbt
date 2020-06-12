ThisBuild / scalaVersion     := "2.13.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

// inliner causes failures right now with
// "scala.reflect.internal.MissingRequirementError: object scala in compiler mirror not found."
// https://www.lightbend.com/blog/scala-inliner-optimizer
// https://docs.scala-lang.org/overviews/compiler-options/index.html
val optimizeSettings = Seq(
  "-opt:l:inline",
  "-opt-inline-from:example.**",
  "-opt-warnings:any-inline-failed",
  "-Yopt-log-inline"
)

val diagnosticOptions = Seq(
  "-XX:+UnlockDiagnosticVMOptions",
  "-XX:+PrintCompilation",
  "-XX:+PrintInlining"
)

// run with sbt "jmh:run"
lazy val root = (project in file(".")).enablePlugins(JmhPlugin)
  .settings(
    name := "scala-bug-12035",
    //javaOptions := diagnosticOptions,
    scalacOptions ++= optimizeSettings,
    // Comment this out to see https://github.com/scala/bug/issues/12035
    //classpathOptions := classpathOptions.value.withFilterLibrary(false)
  )
