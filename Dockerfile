FROM amazoncorretto:17-alpine AS builder
WORKDIR /app

COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw
COPY src src

RUN ./mvnw clean package -DskipTests

FROM amazoncorretto:17-alpine
WORKDIR /app

COPY --from=builder /app/target/api-calendario-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]