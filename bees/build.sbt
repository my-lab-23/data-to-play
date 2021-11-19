name := "bees"

version := "1.0"

scalaVersion := "2.13.1"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "com.danielasfregola" %% "twitter4s" % "7.0",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

scalacOptions := Seq("-unchecked", "-deprecation", "-Ywarn-unused:imports")
