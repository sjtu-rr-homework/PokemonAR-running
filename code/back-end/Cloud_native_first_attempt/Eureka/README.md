# EurekaTest
这是一个使用Eureka,zuul,feign,hystrix,ribbon的简易项目.

http://localhost:8761/       

Eureka 服务注册中心

zuul的端口为8766

现支持在8766的zuul端口调用负载均衡，转发需求到其余的微服务

分别为调用simulation和save两个微服务的url

目前支持以下简易功能

http://localhost:8766/simulation/test

http://localhost:8766/save/test

hystrix数据监测服务：

http://localhost:8766/hystrix
路径中填入
http://localhost:8764/turbine.stream
