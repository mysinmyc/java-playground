FROM maven as buildStage

RUN git clone https://github.com/mysinmyc/java-playground.git

RUN cd java-playground/j2ee-rest &&  mvn -P!wlp-embedded,wlp-mariadb package

RUN mkdir -p /staging/j2ee-rest && unzip -qq /java-playground/j2ee-rest/target/j2ee-rest_mariadb.zip -d /staging/j2ee-rest_mariadb

FROM golang as buildGo

RUN go get github.com/mysinmyc/mytester

FROM open-liberty:webProfile8

COPY --from=buildStage /staging/j2ee-rest_mariadb/wlp/usr/ /opt/ol/wlp/usr

COPY --from=buildGo /go/bin/mytester /usr/local/bin

CMD ["/opt/ol/wlp/bin/server", "run", "j2ee-restServer_mariadb"]

HEALTHCHECK --start-period=60s --interval=5s --timeout=30s --retries=3 CMD /usr/local/bin/mytester -url http://localhost:9080/j2ee-rest/whoami || exit 1

