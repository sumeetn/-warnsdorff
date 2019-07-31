import Dependencies._

lazy val root = Project(id = "assignment-2", base = file("."))
  .settings(Commons.settings: _*)
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.scalaLogging,
      Dependencies.scalaTest,
      Dependencies.logbackClassic
    ))
