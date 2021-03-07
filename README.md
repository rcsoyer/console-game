# console-game rock-paper-scissors

### About

Simple console application to play the game rock-paper-scissors

### Run the app

This Java projects uses JDK 15 and its preview features.
<br>The JDK must be configured accordingly on local machine

#### Command line

* ./gradlew clean build - build the application
* gradle run - run the app via gradle command line

#### IntelliJ IDEA

Configure the project to point to a JDK 15 available on the local machine enabling JDK preview
feature.
<br>The project uses Lombok hence it must be enabled Annotation Processors

### Game rules

The winner is determined by the following schema:

* Paper beats (wraps) rock
* Rock beats (blunts) scissors
* Scissors beats (cuts) paper