name := """SOEN_6441_Reactive-App"""
organization := "com.SOEN"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.15"

libraryDependencies += guice
