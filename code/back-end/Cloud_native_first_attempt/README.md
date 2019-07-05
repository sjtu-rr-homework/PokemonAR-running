### This project is a homework for SE418 in SJTU
#### Our coding standards:
[Google style guide](https://google.github.io/styleguide/)<br>

#### Our idea:
One microservice is used to stimulate the Cellular automaton.<br>

The other microservice is used to draw the graph.

#### About Cellular automaton
[Cellular automaton](https://en.wikipedia.org/wiki/Cellular_automaton)

#### Tech stack

There is no denying that we are a little bit familiar with Eureka and native Docker with spring boot.<br>

We decided to use Eureka to discover service and ZUUL as the api gateway. This tool chain brings us the Ribbon as  a client side load balancer and Hystrix as an implementation of [Circuit Breaker pattern](http://martinfowler.com/bliki/CircuitBreaker.html)

#### Reference

[Microservice Architecture with Spring Boot, Spring Cloud and Docker](https://github.com/sqshq/piggymetrics)

[spring-boot-microservices-on-kubernetes](https://github.com/IBM/spring-boot-microservices-on-kubernetes)


