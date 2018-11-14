FROM maven as buildStage

RUN git clone https://github.com/mysinmyc/java-playground.git

RUN cd java-playground/j2ee-rest &&  mvn -Pwlp-package package


FROM open-liberty:webProfile8

COPY --from=buildStage /java-playground/j2ee-rest/target/liberty/wlp/usr /opt/ol/wlp/usr/

CMD ["/opt/ol/wlp/bin/server", "run", "j2ee-restServer"]
