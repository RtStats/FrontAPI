import sbt._
import Keys._
import play.Project._
import com.typesafe.config._

object ApplicationBuild extends Build {

    val conf            = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()
    val appName         = conf.getString("app.name").toLowerCase()
    val appVersion      = conf.getString("app.version")

    val _versionSpringFramework = "3.2.10.RELEASE"
    val _versionTsc = "0.5.1"
    
    val appDependencies = Seq(
        "org.slf4j"         % "log4j-over-slf4j" % "1.7.7",
        "org.apache.thrift" % "libthrift" % "0.9.1" excludeAll(
            ExclusionRule("com.sun.jdmk", "jmxtools"),
            ExclusionRule("com.sun.jmx", "jmxri"),
            ExclusionRule("javax.jms", "jmx"),
            ExclusionRule("org.slf4j", "slf4j-log4j12"),
            ExclusionRule("org.slf4j", "slf4j-simple"),
            ExclusionRule("org.jboss.netty", "netty")
        ),
        "com.github.ddth" % "ddth-zookeeper"     % "0.3.1.1" excludeAll(
            ExclusionRule("com.sun.jdmk", "jmxtools"),
            ExclusionRule("com.sun.jmx", "jmxri"),
            ExclusionRule("javax.jms", "jmx"),
            ExclusionRule("org.slf4j", "slf4j-log4j12"),
            ExclusionRule("org.slf4j", "slf4j-simple"),
            ExclusionRule("org.jboss.netty", "netty")
        ),
        "com.github.ddth" % "ddth-kafka"         % "0.2.1.1" excludeAll(
            ExclusionRule("com.sun.jdmk", "jmxtools"),
            ExclusionRule("com.sun.jmx", "jmxri"),
            ExclusionRule("javax.jms", "jmx"),
            ExclusionRule("org.slf4j", "slf4j-log4j12"),
            ExclusionRule("org.slf4j", "slf4j-simple"),
            ExclusionRule("org.jboss.netty", "netty")
        ),
        "com.datastax.cassandra" % "cassandra-driver-core" % "2.0.1",
        "org.springframework"    % "spring-core"           % _versionSpringFramework,
        "org.springframework"    % "spring-expression"     % _versionSpringFramework,
        "com.github.ddth"        %  "ddth-tsc-redis"       % _versionTsc,
        "com.github.ddth"        %  "ddth-tsc-cassandra"   % _versionTsc,
        "com.github.ddth"        %  "ddth-tsc"             % _versionTsc,
        "org.apache.commons"     %  "commons-pool2"        % "2.2",
        "com.github.ddth"        %  "ddth-commons"         % "0.2.2.2",
        "com.github.ddth"        %% "play-module-plommon"  % "0.5.1.2",
        filters
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
        // Disable generating scaladoc
        sources in doc in Compile := List(),
        
        // Custom Maven repository
        resolvers += "Sonatype OSS repository" at "https://oss.sonatype.org/content/repositories/releases/",
        
        // Force compilation in java 1.6
        javacOptions in Compile ++= Seq("-source", "1.6", "-target", "1.6"),
        
        // Add your own project settings here
        organization := "RtStats",
        organizationName := "RtStats",
        organizationHomepage := Some(new URL("https://github.com/RtStats/"))
    )
}
