FROM openjdk:21
VOLUMEN /tmp
EXPOSE 8080
ADD ./target/ApiSuperHeroes-0.0.1-SNAPSHOT.jar api-super-heroes.jar
ENTRYPOINT ["java","-jar","/api-super-heroes.jar"]