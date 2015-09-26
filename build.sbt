name := "MyProject"

version := "1.0"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq("com.typesafe.slick" %% "slick" % "3.0.0",
  "com.github.tminglei" %% "slick-pg" % "0.9.0",
  "com.typesafe.akka" %% "akka-actor" % "2.3.6",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.6",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "io.spray" %%   "spray-can" % "1.3.2",
  "io.spray" %% "spray-http" % "1.3.2",
  "io.spray" %% "spray-routing" % "1.3.2",
  "net.liftweb" % "lift-json_2.11" % "3.0-M2"
)

resolvers ++= Seq(
  "Spray repository" at "http://repo.spray.io",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)