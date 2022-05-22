# build jar
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

# run app from image
FROM openjdk:11-jre-slim
COPY --from=build /app/target/book-store-0.0.1-SNAPSHOT.jar /usr/local/lib/book-store.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/book-store.jar"]