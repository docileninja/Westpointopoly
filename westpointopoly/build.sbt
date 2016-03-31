name := "westpointopoly"
version := "0.2.1"

scalaVersion := "2.11.7"

target in Compile in doc := baseDirectory.value / "api"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11+"
