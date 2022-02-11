val scala3Version = "3.0.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "mani",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,
)

scalacOptions := Seq("-unchecked", "-deprecation")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"
libraryDependencies += "commons-io" % "commons-io" % "20030203.000550"
