Setup
=====

First, obtain a copy of Microsoft's SQL Server JDBC Driver from here:

https://www.microsoft.com/en-us/download/details.aspx?displaylang=en&id=11774

Then locate the jar file you want and install it in your local maven repository with a command like this:

    mvn install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar

You must use sqljdbc4.jar not sqljdbc.jar because the latter does not support Java 1.7.


Building
========

When developing you can just say:

    mvn package

You can also build a jar that contains all dependencies like this:

    mvn clean compile assembly:single

Note that `clean` is important to avoid stuffing extra stuff into the jar. See here:

http://stackoverflow.com/questions/574594/how-can-i-create-an-executable-jar-with-dependencies-using-maven

Running
=======

Run it like this:

    mvn exec:java -Dexec.mainClass=com.illuminatedcomputing.sqlserverclient.Report

Or to run the fully self-contained executable jar, say this:

    java -jar target/sqlserverclient-1.0-SNAPSHOT-jar-with-dependencies.jar

