/*
 * Commons.scala
 *
 *
 */

import sbt.Keys._
import sbt._

object Commons {

  /*
  lazy val nexus = sys.env.getOrElse("NEXUS_URL", "<Nexus URL>")
  val releaseUrl = "releases" at s"$nexus/releases"
  val snapshotUrl = "snapshots" at s"$nexus/snapshots"
  */
  val settings: Seq[Def.Setting[_]] = Seq(
    name := "sumeet-nikam_platform-assignment-2",
    version := Version.appVersion,
    organization := "com.smn",
    scalaVersion := Version.scala,
    resolvers ++= Seq("Artima Maven Repository" at "http://repo.artima.com/releases")

  )
}