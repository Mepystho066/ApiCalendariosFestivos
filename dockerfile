#Crear entorno
From eclipse-temurin:23-jdk AS builder

#Crear la carpteta 
WORKDIR /app

COPY ..

RUN ./mvnw clean pakage-DskipTest

From eclipse-termurin:23-jre

WORKDIR /app

COPY --from=builder /app/target

expose 8080

ENTRYPOINT ["java","-jar","app.jar"]