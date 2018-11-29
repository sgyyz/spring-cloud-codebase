## Spring Cloud Code Base [![Build Status](https://travis-ci.org/sgyyz/spring-cloud-codebase.svg?branch=master)](https://travis-ci.org/sgyyz/spring-cloud-codebase)
This repository is used to learn build spring cloud based framework. It includes: discovery, gateway, admin, oauth and web services. 
And in the future we can based on this repository to build more applications.

### Spring boot and Spring Cloud versions
|Module|Version|
-------|---------
|Spring Boot| 2.0.4.RELEASE|
|Spring Cloud| Finchley.SR1 | 


### Project structure
In this demo project, it contains the standard spring cloud structure. As you can see
the structure is:
```
spring-cloud-codebase
    ├── admin
    ├── discovery
    ├── docker
    ├── gateway
    ├── oauth
    └── web
```

### How to run it?
#### Run it through IDE
Start each module. `discovery` module should be started firstly, cause all other modules will be registered on it.)
#### Run it through Docker
1. Build the docker images(benefit from `dockerfile-maven-plugin`)
```
mvn clean install -Pdocker
```
2. Run it through docker command or `docker-compose` command. Change directory to `docker` folder and run the below command:
```
docker-compose up -d
```
#### Test it
##### Application Testing
Once all modules run without any error, you can access `oauth` or `web` module through gateway(one stop service exposure place).
Like
```
GET http://localhost:4000/web/v1/test
```
##### Docker Status Checking
You can check all images running status or watch the log separately.
1. Check status in real time
```
watch docker ps
```
2. Tail running logs
```
docker logs -f [container_id]
```
