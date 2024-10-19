FROM maven:3.9.6-eclipse-temurin-17-alpine
WORKDIR /ui-test
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
COPY entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]