libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  "com.bizo" % "mighty-csv_2.11" % "0.2",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)

lazy val root = (project in file(".")).
  settings(
    name := "contact_link",
    version := "0.0.1",
    scalaVersion := "2.11.8"
  )

