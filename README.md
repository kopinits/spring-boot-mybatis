# spring-boot-mybatis
A simple Spring-Boot rest application with mybatis using annotation

To run: 

mvn clean install

mvn spring-boot:run

Endpoints

* http://localhost:8889/deal/{id} (GET) get by id
* http://localhost:8889/deal (GET) get all
* http://localhost:8889/deal (PUT) insert. json= {"name":"name", "startDate="2015-09-15"}
* http://localhost:8889/deal (POST) update. json= {"id":"1", "name":"name", "startDate="2015-09-15", "version":"0"}
 

#### TODO
* update database properties in application.properties (main and test)
* implement internacionalization
