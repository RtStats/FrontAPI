FrontAPI - Installation Guide
=============================

0. Environment
--------------
- OS: Linux box (recommend: 64 bit)
- Sun JDK 1.6+ (recommend: 1.7+)
- Apache Cassandra 2.0+
- (Optional) [Play! Framework](https://www.playframework.com/download#older-versions) 2.2.x+: to build `FrontAPI` from source.


1. Download
-----------
- Source: [https://github.com/RtStats/FrontAPI](https://github.com/RtStats/FrontAPI)
- Binary: [https://github.com/RtStats/FrontAPI/releases](https://github.com/RtStats/FrontAPI/releases)
- Database schema file: `dbschema/dbschema.cassandra.cql`


2. Build from source
--------------------
```
$ cd /path/to/<frontapi-project-directory>
$ play clean dist
```
The binary package will be available at `/path/to/<frontapi-project-source>/target/univeral/frontapi-<version>.zip`


3. Start/Stop FrontAPI
---------------------
** Start FrontAPI: **
```
$ cd /path/to/<frontapi-binary-directory>
$ ./conf/server.sh start [-m <JVM memory limit in mb>] [-p <web-based status port>]
```

Parameters:
```
-m: JVM memory limit in mb (default 128)
-p: Port for HTTP requests (default 9000)
```

Example: start server 64mb memory limit, on port 9001
```
$ ./conf/server.sh start -m 64 -p 9001
```

** Stop FrontAPI: **
```
$ cd /path/to/<frontapi-binary-directory>
$ ./conf/server.sh stop
```


4. FrontAPI's Config
--------------------
Edit file `/path/to/<frontapi-binary-directory>/conf/spring/beans.xml` - it is self-explanatory!
