# H2Embedded
Embed H2 programmatically with a REST API using SpringBoot and Spring Security

**Master**

[![Build Status](https://travis-ci.org/lcappuccio/h2-embedded.svg?branch=master)](https://travis-ci.org/lcappuccio/h2-embedded)
[![codecov.io](https://codecov.io/github/lcappuccio/h2-embedded/coverage.svg?branch=master)](https://codecov.io/github/lcappuccio/h2-embedded?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/37a67909f9104902aad70d7a0ad5504e)](https://www.codacy.com/app/leo_4/h2-embedded)

**Develop**

[![Build Status](https://travis-ci.org/lcappuccio/h2-embedded.svg?branch=develop)](https://travis-ci.org/lcappuccio/h2-embedded)
[![codecov.io](https://codecov.io/github/lcappuccio/h2-embedded/coverage.svg?branch=develop)](https://codecov.io/github/lcappuccio/h2-embedded?branch=develop)

## H2 Console
The H2 Web Console is available on [deployed instance](http://localhost:8080/h2-console)

To login use the **ADMIN** role credentials found in: `org.systemexception.h2embedded.SecurityConfig`

## Documentation

Check the bundled [documentation](http://localhost:8080/swagger-ui.html)

## Monitoring

Actuators are deployed (e.g.), verify `management.port` in `application.properties`:

* [autoconfig](http://localhost:8080/autoconfig)
* [beans](http://localhost:8080/beans)
* [metrics](http://localhost:8080/metrics)

Further endpoints: [Spring Reference](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready-endpoints)

## Usage

Use postman or curl to interact with data, e.g.

```curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{"dataValue":"'$data'"}' http://xxx.xxx.xxx.xxx:8080/api/data -u user:password ```

Additionally a JMeter test plan is available.


### Security

Check credentials in `org.systemexception.h2embedded.SecurityConfig`
and add them to your REST utility for basic authentication.

## Frontend

A very basic frontend is [available](http://localhost:8080)

* to add an item enter the data to be saved and click the **+** icon
* to update an item click the value, edit it and click update