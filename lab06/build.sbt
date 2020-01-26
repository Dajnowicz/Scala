name 			:= "Lab06"
version 		:= "0.0.1"
scalaVersion 	:= "2.13.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
	val akkaV = "2.6.1"
	Seq(
		"com.typesafe.akka" %% "akka-actor" % akkaV,
	)
}
