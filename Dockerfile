FROM adoptopenjdk/maven-openjdk11 as builder

# image layer
WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never

# Image layer: with the application
ADD . /app
RUN mvn -v
RUN mvn clean install -DskipTests
FROM adoptopenjdk/openjdk11

EXPOSE 8080
COPY --from=builder app/target/spring-boot-k8-training-*.jar /developments/app.jar
ENTRYPOINT ["java","-jar","/developments/app.jar"]
