# Microservices SpringBoot_3.x.x
Multiple projects are connected using the microservice architecture, using the Spring boot 3.x.x and Java-17.

## TOPICS
- Microservice architecture 
- Web clients
  - Rest Template (call using application.name)
  - Open feign (Interface)
  - Web client
- API gateway
  ```
    cloud:
    gateway:
      routes:
        - id : USER-SERVICE
          uri: lb://USER-SERVICE
          predicates:
            - Path=/** (allow to all)
            - path= /user/**,/staff/** (allow to specific)
  ```
- Eureka Server (Server Discovery)