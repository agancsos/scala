
fork := true
mainClass in Global := Some("com.artemis.Main")

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.artemis",
      scalaVersion    := "2.13.4"
    )),
    name := "Artemis Health Preparation",
	version := "1.0.0.0",
	cleanFiles ++= Seq(
		 new java.io.File("./project"),
		 new java.io.File("./target")
	),
	// Use local dependencies as they have already been resolved and there's another method of building the utility. 
	// For a full clean, run `sbt clean && find . \( -name "project" -o -name "target" \) -a -type d -exec bash -c 'rm -fr $0 2>&1' {} \;` 
    libraryDependencies ++= Seq(
    )
  )

