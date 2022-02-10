# spring-boot-quick-start
mvn clean install
## REQUISITOS DE INSTALACIÓN
* Java 8 + Sptring Boot


## Compilar Jar

mvn clean package

## EJECUCION EN DOCKER

docker build --tag=ptchile-server:latest .
docker run -p8080:8081 message-server:latest

Url:
http://localhost:8080/swagger-ui.html