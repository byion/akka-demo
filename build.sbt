name := """akka-demo"""

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  // https://mvnrepository.com/artifact/org.scala-lang.modules/scala-java8-compat
  "org.scala-lang.modules" %% "scala-java8-compat" % "0.8.0",
  "com.typesafe.akka" %% "akka-actor" % "2.4.16",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.6" % "test",
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.10" % "test"
)

