name := """SOEN_6441_Reactive-App"""
organization := "com.SOEN"

version := "1.0-SNAPSHOT"


// lazy val root = (project in file(".")).
val playVersion = "2.8.20"
scalaVersion := "2.13.15"

enablePlugins(PlayJava)

// Testing
libraryDependencies ++= Seq(
  "org.mockito" % "mockito-core" % "5.7.0" % Test,
  "org.junit.jupiter" % "junit-jupiter-api" % "5.9.2" % Test,  // JUnit 5 API
  "org.junit.jupiter" % "junit-jupiter-engine" % "5.9.2" % Test // JUnit 5 Engine
)

// Other
libraryDependencies ++= Seq(
  guice,
  "com.typesafe.play" %% "play-guice" % playVersion,
  "com.typesafe.akka" %% "akka-actor" % "2.6.20",
  "com.typesafe.akka" %% "akka-stream" % "2.6.20",
  "com.typesafe.akka" %% "akka-slf4j" % "2.6.20"
)

// Force scala-xml to a compatible version
dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "1.3.1"

// Adjust eviction rules to prevent errors
evictionErrorLevel := Level.Warn