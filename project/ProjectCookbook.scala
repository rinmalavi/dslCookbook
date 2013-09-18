import sbt._
import Keys._

object Cookbook extends Build {

  lazy val dslClientHttp = ProjectRef( file("../dsl-client-java/code/"), "http")
  
  lazy val beginner = cookProject("Beginner") 

  lazy val intermediate = cookProject("Intermediate")
   //settings (  unmanagedSourceDirectories in Compile <+= (javaSource in Compile)(_ / ".." / ".." / "generated_final" / "java" )   )
  lazy val advanced = cookProject("Advanced")

  // Dependencies
  // ------------
  
  val commonsIo = "commons-io" % "commons-io" % "2.4"
  
  //val dslClientHttp = "com.dslplatform" % "dsl-client-http" % "0.4.1"
  
  val jackson = "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.3"
  
  val logback = "ch.qos.logback" % "logback-classic" % "1.0.10"

  val jUnit = "junit" % "junit" % "4.11" 
  
  // ------------
  
  import com.typesafe.sbteclipse.plugin.EclipsePlugin.{ settings => eclipseSettings, _ }
  import net.virtualvoid.sbt.graph.Plugin._
  
  private def cookProject(id: String) = Project(
    id.toLowerCase
  , file(id.toLowerCase)
  , settings = 
      Defaults.defaultSettings ++
      Resolvers.settings ++
      eclipseSettings ++
      graphSettings ++ Seq(
        version := "0.0.0"
      , name := "DSL-Platform-CookBook-" + id
      , organization := "com.dslplatform.examples"
      , libraryDependencies ++= Seq(logback, /* dslClientHttp, */ jUnit % "test")
      , javaHome := sys.env.get("JAVA_HOME").map(file(_))
      , javacOptions := Seq("-encoding", "UTF-8" )
      , crossScalaVersions := Seq("2.10.2")
      , scalaVersion <<= crossScalaVersions(_.head)
      , scalacOptions <<= scalaVersion map ScalaOptions.apply
      , conflictWarning in ThisBuild ~= { _.copy(level = Level.Debug) }
      , autoScalaLibrary := false
      , crossPaths := false    
      , EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource// + EclipseCreateSrc.Managed
      , EclipseKeys.projectFlavor := EclipseProjectFlavor.Java
      , unmanagedSourceDirectories in Compile <<= (javaSource in Compile)(src => src :: src / ".." / ".." / "generated" / "java" :: Nil)
      , unmanagedSourceDirectories in Test <<= (javaSource in Test)(_ :: Nil)
      , unmanagedResourceDirectories in Compile <<= (resourceDirectory in Compile)( src => src :: src / ".." / ".." / "generated" / "resources" :: Nil )
    )
  ) dependsOn(dslClientHttp)
  
  private object ScalaOptions {
    val scala2_8 = Seq(
      "-unchecked"
    , "-deprecation"
    , "-optimise"
    , "-encoding", "UTF-8"
    , "-Xcheckinit"
    , "-Yclosure-elim"
    , "-Ydead-code"
    , "-Yinline"
    )

    val scala2_9 = Seq(
      // "-Xmax-classfile-name", "72"
    )

    val scala2_9_1 = Seq(
      "-Yrepl-sync"
    , "-Xlint"
    , "-Xverify"
    , "-Ywarn-all"
    )

    val scala2_10 = Seq(
      "-feature"
    , "-language:postfixOps"
    , "-language:implicitConversions"
    , "-language:existentials"
    )

    def apply(version: String) = scala2_8 ++ (version match {
      case x if (x startsWith "2.10.")                => scala2_9 ++ scala2_9_1 ++ scala2_10
      case x if (x startsWith "2.9.") && x >= "2.9.1" => scala2_9 ++ scala2_9_1
      case x if (x startsWith "2.9.")                 => scala2_9
      case _ => Nil
    } )
  }
}

object Repositories {
  val NGSNexus                   = "NGS Nexus"                  at "http://ngs.hr/nexus/content/groups/public/"
  val NGSSnapshots               = "NGS Private Snapshots"      at "http://ngs.hr/nexus/content/repositories/snapshots-private/"
}

object Resolvers {
  import Repositories._

  val settings = Seq(
    resolvers := Seq(NGSNexus, NGSSnapshots)
  , externalResolvers <<= resolvers map { r =>
      Resolver.withDefaultResolvers(r, mavenCentral = false)
    }
  )
}

