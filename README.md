[![Build Status](https://travis-ci.org/gossie/home-automation.svg?branch=master)](https://travis-ci.org/gossie/home-automation)
# home-automation

* Java needs to be installed (>= Java 8)
* docker and docker-compose need to be installed
* Run `mvnw package` to build all the code (nothing needs to be installed, a maven wrapper is included)
* Run `docker-compose up -d`. This will start the follwing containers:
** A configuration server
** A service registry
** A monitoring service containing a hystrix dashboard
** A light service (backend)
** A room service (backend)
** A power supply server (backend)
** A gateway service (REST API for the frontend)
** A rabbitmq
