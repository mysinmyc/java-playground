FROM maven as buildStage

RUN git clone https://github.com/mysinmyc/java-playground.git

RUN cd java-playground/j2ee-rest &&  mvn -Pwlp-package package

RUN mkdir -p /staging/j2ee-rest && unzip -qq /java-playground/j2ee-rest/target/j2ee-rest.zip -d /staging/j2ee-rest

FROM open-liberty:webProfile8

COPY --from=buildStage /staging/j2ee-rest/wlp/usr/ /opt/ol/wlp/usr

CMD ["/opt/ol/wlp/bin/server", "run", "j2ee-restServer"]
