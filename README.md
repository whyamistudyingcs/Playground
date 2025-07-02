use mvn verify to test

To select the scenario on line 10 of the `example.feature` file use:

```shell
mvn test -Dsurefire.includeJUnit5Engines=cucumber -Dcucumber.plugin=pretty -Dcucumber.features=path/to/example.feature:10 
```

To debug test config
```shell
mvn test -X

[DEBUG]   (s) projectBuildDirectory = /home/dameningen/Workspace/Playground/target
....
[DEBUG]   (s) testSourceDirectory = /home/dameningen/Workspace/Playground/src/test/java
....
[DEBUG]   (s) workingDirectory = /home/dameningen/Workspace/Playground
[DEBUG] -- end configuration --
[DEBUG] Using JVM: /usr/lib/jvm/java-17-openjdk-amd64/bin/java with Java version 17.0
[DEBUG] Resolved included and excluded patterns: **/Test*.java, **/*Test.java, **/*Tests.java, **/*TestCase.java, !**/*$*
```
