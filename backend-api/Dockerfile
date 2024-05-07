FROM openjdk:17-slim AS build
RUN mkdir /project
COPY . ./project
WORKDIR /project
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine
RUN apk add dumb-init
RUN mkdir /api
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=build /project/target/*.jar /app/java-application.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser

EXPOSE 8080

CMD "dumb-init" "java" "-jar" "java-application.jar"

