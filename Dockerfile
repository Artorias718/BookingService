FROM openjdk:13-alpine
RUN addgroup -S spring && adduser -S spring -G spring
VOLUME /tmp
EXPOSE 7500
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar booking.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/booking.jar"]