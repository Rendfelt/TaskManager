# project-manager


## Technologies:

Java 1.7
Maven 4.0.0


## Project base structure:

Application: class with entry point;
Bootstrap: class with main application loop;

api: package with base interfaces;
command: package with command classes;
exception: package with exceptions;

DomainEntity: Class-entity contains temp data for save/load;
Project: Class-entity contains project data;
Task: Class-entity contains task data;
User: Class-entity contains user data;

ProjectRepositoryImpl: Class provides project data;
TaskRepositoryImpl: Class provides task data;
UserRepositoryImpl: Class provides user data;

AuthorizationServiceImpl: Class presents authorization service;
DomainServiceImpl: Class presents save/load service;
ProjectServiceImpl: Class presents project data;
TaskServiceImpl: Class presents task data;
UserServiceImpl: Class presents user data;

```
project-manager
──src
  └───main
      └───java
          └───org
              └───dragard
                  └───projectmanager
                      ├───api
                      │   ├───command
                      │   ├───domain
                      │   ├───repository
                      │   └───service
                      ├───command
                      ├───entity
                      ├───exception
                      ├───repository
                      └───service
```


## Base functions:

to see base functions type "help";

to login type: "login" or "create_user" (if no login);

to exit from application type "exit".



## Build:

```
mvn clean install
```

## Deployment:

```
java -jar ./project-manager.jar
```