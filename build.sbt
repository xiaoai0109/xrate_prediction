name := "alpha-vantage-scala"

organization := "com.benmosheron"

version := "0.0.3"

scalaVersion := "2.12.4"

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

// https://mvnrepository.com/artifact/org.apache.httpcomponents/fluent-hc
libraryDependencies += "org.apache.httpcomponents" % "fluent-hc" % "4.5.5"
// https://mvnrepository.com/artifact/io.spray/spray-json
libraryDependencies += "io.spray" %% "spray-json" % "1.3.4"
// https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
// https://mvnrepository.com/artifact/com.typesafe.scala-logging/scala-logging
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0"

// Dependency for the test app - will be removed.
// https://mvnrepository.com/artifact/com.github.pathikrit/better-files
libraryDependencies += "com.github.pathikrit" %% "better-files" % "3.4.0"

libraryDependencies ++= Seq(
  "com.google.guava" % "guava" % "14.0",
  "org.apache.hadoop" % "hadoop-common" % "2.6.0",
  "org.apache.hadoop" % "hadoop-mapred" % "0.22.0",
  "org.apache.hbase" % "hbase-common" % "1.2.0",
  "org.apache.hbase" % "hbase-client" % "1.2.0"
)

dependencyOverrides += "com.google.guava" % "guava" % "14.0"

resolvers ++= Seq(
// HTTPS is unavailable for Maven Central
"Maven Repository"     at "http://repo.maven.apache.org/maven2",
"Apache Repository"    at "https://repository.apache.org/content/repositories/releases",
"JBoss Repository"     at "https://repository.jboss.org/nexus/content/repositories/releases/",
"MQTT Repository" at "https://repo.eclipse.org/content/repositories/paho-releases/",
"Cloudera Repository"  at "http://repository.cloudera.com/artifactory/cloudera-repos/",
// For Sonatype publishing
// "sonatype-snapshots"   at "https://oss.sonatype.org/content/repositories/snapshots",
// "sonatype-staging"     at "https://oss.sonatype.org/service/local/staging/deploy/maven2/",
// also check the local Maven repository ~/.m2
Resolver.mavenLocal
)
