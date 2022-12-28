# springboot

spring_boot = springboot REST API
  --library used = Spring Data JPA, Spring Web, Spring Boot DevTools, mysql (mysql-connector-java), taglibs (standard), org.apache.tomcat.embed (tomcat-embed-jasper)
  
consume_api01 = consume API using HttpURLConnection
  --library used = javax.servlet (servlet-api, jstl), org.javassist (javassist), com.sun.xml.bind (jaxb-core), org.springframework (spring-webmvc, spring-tx, spring-jdbc, spring-orm, spring-core, spring-web, spring-context), taglibs (standard), org.glassfish.jaxb (jaxb-runtime), junit (junit), org.apache.tomcat (tomcat-catalina), com.googlecode.json-simple (json-simple)

SpringbootConsumeAPI = consume API using restTemplate
  --library used = Spring Web, Spring Boot DevTools, taglibs (standard), org.apache.tomcat.embed (tomcat-embed-jasper), javax.servlet (jstl), org.apache.httpcomponents (httpclient)

tomcat-embed-jasper --> view JSP
JSTL --> allows developers to write custom iteration tags by implementing the LoopTag interface.
servlet-api --> build web applications
javassist --> editing bytecodes in Java
jaxb-core --> Contains sources required by XJC, JXC and Runtime modules with dependencies
spring-webmvc -->  implementation of Spring MVC, spring-webmvc depends on on spring-web, thus including it will transitively add spring-web (didnt need to add spring web explicitly
spring-web --> spring-web provides core HTTP integration, including some handy Servlet filters, Spring HTTP Invoker, infrastructure to integrate with other web frameworks and HTTP technologies e.g. Hessian, Burlap
spring-tx --> Support for programmatic and declarative transaction management for classes that implement special interfaces or any POJO
spring-orm --> used to access a relational database from an object-oriented language. ORM (Object Relation Mapping) covers many persistence technologies. They are as follows: JPA(Java Persistence API): It is mainly used to persist data between Java objects and relational databases.
spring-core --> provides dependency injection and IoC features
spring-context --> for instantiating, configuring, and assembling beans by reading configuration metadata from XML, Java annotations, and/or Java code in the configuration files
jaxb-runtime --> (Java Architecture for XML Binding) to convert XML instance documents to and from Java objects
junit --> (Java unit testing framework) unit testing open-source framework for the Java programming language
tomcat-catalina --> for server tomcat
json-simple --> simple Java toolkit for JSON for JSON processing, read and write JSON
