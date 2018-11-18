SPRING-MONGODB-REST
===================

Mongodb crud REST example

Mongodb
-------

by default application is configured to connect to a local unprotected mongodb.
 
To connect to a different host, set the following environment variables (or command line arguments):
* SPRING_PROFILES_ACTIVE=mongoremote
* MONGO_URI=*uri of mongodb (mongodb://{user}:{password}@{host}:{port}/{db})*


How to test
-----------

By default swagger ui console is available at url [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)