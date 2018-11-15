FROM maven as buildStage

RUN git clone https://github.com/mysinmyc/java-playground.git

RUN cd java-playground/j2ee-vaadin8 &&  mvn -Pwlp-package package

RUN mkdir -p /staging/j2ee-vaadin8 && unzip -qq /java-playground/j2ee-vaadin8/target/j2ee-vaadin8.zip -d /staging/j2ee-vaadin8

FROM open-liberty:webProfile8

COPY --from=buildStage /staging/j2ee-vaadin8/wlp/usr/ /opt/ol/wlp/usr

CMD ["/opt/ol/wlp/bin/server", "run", "j2ee-vaadin8Server"]
