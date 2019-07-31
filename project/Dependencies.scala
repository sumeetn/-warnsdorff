/*
 * Dependencies.scala
 *
 */

import sbt._

object Dependencies {

  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % Version.scalaLogging
  lazy val logbackClassic = "ch.qos.logback" % "logback-classic" % Version.logbackClassic
  lazy val scalaTest =  "org.scalatest" %% "scalatest" % Version.scalaTest % Test
}