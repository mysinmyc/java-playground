FROM maven as buildStage

RUN git clone https://github.com/mysinmyc/java-playground.git

RUN cd java-playground/j2ee-rest &&  mvn -P!wlp-embedded,wlp-mariadb package

RUN mkdir -p /staging/j2ee-rest && unzip -qq /java-playground/j2ee-rest/target/j2ee-rest_mariadb.zip -d /staging/j2ee-rest_mariadb

FROM open-liberty:webProfile8

COPY --from=buildStage /staging/j2ee-rest_mariadb/wlp/usr/ /opt/ol/wlp/usr

CMD ["/opt/ol/wlp/bin/server", "run", "j2ee-restServer_mariadb"]
