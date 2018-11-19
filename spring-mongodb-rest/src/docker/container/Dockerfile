FROM maven as buildStage

RUN git clone https://github.com/mysinmyc/java-playground.git

RUN cd java-playground/spring-mongodb-rest &&  mvn package


FROM openjdk:10-jre-slim

COPY --from=buildStage /java-playground/spring-mongodb-rest/target/spring-mongodb-rest*.jar /

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=mongoremote

ENV MONGO_URI=mongodb://mongodb/test

CMD [ "sh", "-c", "java -jar /spring-mongodb-rest*.jar" ]

