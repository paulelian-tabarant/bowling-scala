ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "bowling-scala"
  )

libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
