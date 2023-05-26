FROM amazoncorretto:17

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
