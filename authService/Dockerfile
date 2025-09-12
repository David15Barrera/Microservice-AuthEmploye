# ==============================
# Etapa 1: Build
# ==============================
FROM gradle:8.8-jdk21 AS build

WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Compilamos la aplicación (genera el jar en build/libs)
RUN gradle clean build -x test

# ==============================
# Etapa 2: Runtime
# ==============================
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiamos el JAR generado en la etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# Puerto expuesto (informativo, Render lo ignora pero sirve localmente)
EXPOSE 8082

# Arranque de la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
