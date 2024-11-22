// The Play plugin (downgraded to 2.9.5)
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.9.5")

// Defines scaffolding (found under .g8 folder)
// http://www.foundweekends.org/giter8/scaffolding.html
// sbt "g8Scaffold form"
addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.16.2")

// Check for the latest version of sbt-jacoco
addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.3.0")
