# Etapa de build
FROM eclipse-temurin:23-jdk AS builder

# Crear carpeta de trabajo
WORKDIR /app

# Copiar archivos del proyecto
COPY . .

# Dar permisos al wrapper de Maven
RUN chmod +x mvnw

# Compilar aplicación
RUN ./mvnw clean package -DskipTests

# Etapa runtime
FROM eclipse-temurin:23-jre

WORKDIR /app

# Copiar el jar generado
COPY --from=builder /app/target/*.jar app.jar

# Puerto de la aplicación
EXPOSE 8080

# Ejecutar aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]