# Getting Started

Livi Bank Coding challenge

## Introduction

Backend API development coding challenge

## Functionality
- Retrieve credit assessment score


## Assumptions

1. The minimum number of employees is 1
1. The minimum number of years operated is 0
1. Assign score 0 if rule not found. i.e. Company Type = 'ABC'


## Setup

In order to run this application, these information are required:

1. [IDE] (https://spring.io/tools)
1. [Java8](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
1. [Gradle](https://gradle.org/releases/) Gradle 5 (5.6.x only) or Gradle 6 (6.3 or later)
1. [Lombok](https://search.maven.org/search?q=g:org.projectlombok%20AND%20a:lombok&core=gav) lombok-1.18.20.jar

## Build

### Docker Build/ Deploy

- Change directary to project livi
```cd livi_coding_challenge```
- Execute build.sh to build docker image
``./build.sh``
- Execute deploy.sh to deploy docker image
``./deploy.sh``

1. **Java and gradle should be installed first!!!**

### Local Build  


- Change directary to project livi
```cd livi_coding_challenge```
- Execute Gradle cleanEclipse eclipse if you are using Eclipse IDE
``gradlew cleanEclipse eclipse``
- Install lombok plugin if you are using Eclipse IDE
``double click 'lombok-1.18.20.jar' to install it``
- Execute Gradle build
``gradlew clean build``
- livi-coding-challenge-0.0.1-SNAPSHOT.jar file location:
``livi_coding_challenge\build\libs`` 

## Cucumber Test

- Change directary to project livi
```cd livi_coding_challenge```
- Execute Gradle build
``gradlew clean build cucumber``
- Report file location:
``livi_coding_challenge\target`` 

## Configuration
| Name                | Path  | Remarks |
| ------------- | -----:|-------------:|
|  Message       | /livi-coding-challenge/src/main/resources/message/baseMessages.properties  | |
|  Application config	         |  /livi-coding-challenge/src/main/resources/application.yml, application-[ENV].yml	| |
|  Credit scoring rules            |/livi-coding-challenge/src/main/resources/data.sql||
|  Log file config           |/livi_coding_challenge/src/main/resources/logback.xml||


1. **[ENV]** = local

## Log Location

| Name                | Path  | Remarks |
| ------------- | -----:|-------------:|
| Application log   	| /logs/livi-coding-challenge.[DATE]-app.log   | |
| Error log	| /logs/livi-coding-challenge.[DATE]-err.log   | |


1. **[DATE]** = yyyy-mm-dd


## Application links

| Name                | Path  | Remarks |
| ------------- | -----:|-------------:|
| Swagger link	| http://localhost:8089/swagger-ui/index.html  | |
| Credit Service Calculator	| http://localhost:8089/creditservice/v1/calculator  | Should obtain OAuth Token and assign token to Bearer Token in Authorization.| 


## Obtain OAuth Token
```

POST /oauth/token HTTP/1.1
Host: localhost:8089
Content-Type: application/x-www-form-urlencoded


grant_type=client_credentials&scope=R&client_id=app&client_secret=secret
```


