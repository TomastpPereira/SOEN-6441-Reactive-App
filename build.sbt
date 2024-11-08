name := """SOEN_6441_Reactive-App"""
organization := "com.SOEN"

version := "1.0-SNAPSHOT"


lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.15"

libraryDependencies += guice
libraryDependencies += "org.mockito" % "mockito-core" % "5.7.0" % Test

libraryDependencies ++= Seq(
  "org.junit.jupiter" % "junit-jupiter-api" % "5.9.2" % Test,  // JUnit 5 API
  "org.junit.jupiter" % "junit-jupiter-engine" % "5.9.2" % Test // JUnit 5 Engine
)