# H2Embedded
Embed H2 programmatically with a REST API using SpringBoot

**Master**

[![Build Status](https://travis-ci.org/lcappuccio/h2-embedded.svg?branch=master)](https://travis-ci.org/lcappuccio/h2-embedded)

**Develop**

[![Build Status](https://travis-ci.org/lcappuccio/h2-embedded.svg?branch=develop)](https://travis-ci.org/lcappuccio/h2-embedded)

## H2 Console
The H2 Web Console is available on [deployed instance](http://localhost:8080/h2-console)

## Documentation

Check the bundled [documentation](http://localhost:8080/swagger-ui.html)

## Monitoring

Actuators are deployed (e.g.):

* [autoconfig](http://localhost:8080/autoconfig)
* [beans](http://localhost:8080/beans)
* [metrics](http://localhost:8080/metrics)

Further endpoints: [Spring Reference](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready-endpoints)

## Usage

Use postman or curl to interact with data, e.g.

```curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{"dataValue":"'$data'"}' http://xxx.xxx.xxx.xxx:8080/api/data```

## Frontend

A very basic frontend is [available](http://localhost:8080)