import sbt.Keys._
import sbt._

object Common {
  val settings = Seq(
    version := "1.0",
    scalaVersion := "2.11.6"
  )

  val scalazVersion = "7.1.1"

  val libraryDependencies = Seq(
    "joda-time" % "joda-time" % "2.7",
    "org.scalatest" %% "scalatest" % "2.2.1" % "test",
    "org.scalacheck" %% "scalacheck" % "1.12.2" % "test",
    "org.scalaz" %% "scalaz-core" % scalazVersion,
    "org.scalaz" %% "scalaz-effect" % scalazVersion,
    "org.scalaz" %% "scalaz-typelevel" % scalazVersion,
    "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion
  )

  scalacOptions += "-feature"
}