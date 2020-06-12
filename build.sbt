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

lazy val root = (project in file("."))
  .settings(
    name := "optimized",
    scalacOptions ++= optimizeSettings
  )
