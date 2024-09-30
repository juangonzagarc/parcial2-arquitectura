FROM ubuntu:latest
LABEL authors="jotik"
# Usa una imagen base de Java
FROM openjdk:11-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos de Gradle y la configuración del proyecto
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Copia el código fuente
COPY src ./src

# Ejecuta el comando de Gradle para construir el proyecto
RUN ./gradlew build --no-daemon

# Establece el comando para ejecutar la aplicación
CMD ["java", "-jar", "build/libs/parcial.jar"]

