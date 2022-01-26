# Artemis Health Preparation

## Synopsis
Small tool to help prepare for the Artemis Health interview.  Samples include simple Scala operations, database operations, and socket operations.

## Assumptions
* Questions will be related to database and sockets.
* Questions may or may not also include LEETCODE and algorithm questions.
* As a back-end Software Engineer, I will be dealing with sockets, databases, algorithms, and REST.
* As a back-end Software Engineer, I may or may not be using a vast number of frameworks.

## Requirements
* The program will offer a help menu to users.
* The program will take in command-line arguments to configure the current session.
* The program will have an option to connect to a database.
* The program will have an option to open a socket for listening.
* The program will demonstrate understanding of algorithms by offering options to invoke specific algorithms.
* The program will offer a simple REST service.

## Implementation Details
The utility is made up of smaller helper classes and 3 main services.

### DataService
This service is responsible for handling database operations.  A larger helper function of itself, but plays a more important role in the framework.

### SocketService
This service is responsible to opening the socket and handling the incoming messages.

### AlgorithmService
This service is responsible for invoking the specified algorithm and check if it succeeds.

### ArtemisService
This service is a session wrapper service to handle incoming information from the main entry point.  It's also responsible for configuring the utility and performing the specified operation.

#### Operations
##### dummy
Simply prints a welcome message.  Mostly to test the application framework.

##### basic-db
Connects to a local PostgreSQL database and prints the user's tables.  Mostly to demonstrate ability to use JDBC in a Java/Scala application.

##### basic-socket
Opens a socket and listens to incoming messages, which are then printed to the console.  This is a personal component as it came up in previous interviews, which I didn't so so well.

### Flags
|Flag|Description|
|--|--|
|--op          | Operation to run                                                    |
|--db          | Name of the PostgreSQL to connect to                                |
|--host        | Target host for the operation                                       |
|--user        | Username for user to use for PostgreSQL connection                  |
|-h|--help     | Prints help menu                                                    |
|--pass        | Password for user to use for PostgreSQL connection                  |
|--socket-port | Port to use when creating the socket.  Default is 3434              |
|--alg         | Name of the algorithm to run.  Default is fibonacci                 | 

## Examples

### Build
```bash
./util/build.sh -n artemis
```

### dummy
```bash
scala -cp ".:./lib/*:./dist/*" com.artemis.Main --op dummy
```

### basic-db
```bash
scala -cp ".:./lib/*:./dist/*" com.artemis.Main --user user1 --pass "password123" --db test --op basic-db --host "127.0.0.1"
```

### basic-socket
```bash
## Start the socket
scala -cp ".:./lib/*:./dist/*" com.artemis.Main --op basic-socket --socket-port 8081

## Connect
echo "hello" | nc -4v localhost 8081
```

### alg
```bash
scala -cp ".:./lib/*:./dist/*" com.artemis.Main --op alg
```

### basic-rest
```bash

## Start the service
scala -cp ".:./lib/*:./dist/*" com.artemis.Main --op basic-rest --socket-port 8082

## test
curl http://localhost:8082/api/tables

```

## Retrospective

* Although a little different to configure at first, Play/AKKA are actually very nice and simple frameworks for building REST services.

## Closing thoughts
* The only reason it might be difficult to configure at first is because the frameworks are designed to be used with SBT, which I was trying to avoid.
* Another reason why the frameworks were difficult to setup at first is because they're route based instead of servlet based, which is a much better implementation of a service.

## References
* https://www.programcreek.com/scala/?project_name=databricks%2Fdevbox#
* https://stackoverflow.com/questions/10149027/why-do-i-get-a-some-instead-of-a-string-in-scala

