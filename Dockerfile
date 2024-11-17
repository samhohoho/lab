#FROM eclipse-temurin:17-jre-alpine
#WORKDIR /app
#COPY .mvn/ mvnw pom.xml src  ./
##COPY .mvn/ ./.mvn/
##COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
##COPY src ./src
#CMD ["./mvnw", "spring-boot:run"]

FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]