FROM openjdk:21-jdk
WORKDIR /app
COPY build/libs/api-0.0.1.jar .
CMD ["java","-jar","api-0.0.1.jar"]