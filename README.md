# console-game rock-paper-scissors

### About

Simple interactive console application to play the game rock-paper-scissors.
<br> The game is user (on input via console) against random (not vicious) machine hand

### Run the app

This Java projects uses JDK 21.
<br>The JDK must be configured accordingly on local machine

#### Command line

* ./gradlew clean build - build the application
* gradle run - run the app via gradle command line

#### IntelliJ IDEA

* Configure the project to point to a JDK 21 available on the local machine.
* The project uses Lombok hence it must be enabled Annotation Processors.
* Run the main class [GameApp](src/main/java/org/acme/game/GameApp.java)

### Game rules

The winner is determined by the following schema:

* Paper beats (wraps) rock
* Rock beats (blunts) scissors
* Scissors beats (cuts) paper

### Play the app

To play the game the user must input, from console, only int values on range as described the
actions:

* 1 - user informs that it is playing PAPER
* 2 - user informs that it is playing ROCK
* 3 - user informs that it is playing SCISSORS
* 0 - user informs that wants to finish the game

The user can play as any matches as they want to.
<br> If the user type on console any other value, besides above mentioned, the program exits with
error

See: [HandOptions](src/main/java/org/acme/game/domain/Hand.java)