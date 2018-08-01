Requirment :

This is to create tiny url from any long url


Technical Specification :
Java 1.8
Springboot 2.0.3
Mockito


Project specification :

Service takes long url in request.
We are generating a random number and appending to the base url for every request url which we are storing in Database. For duplicate url request instead of creating a new URL we are sending back the old url.

Build Project :
mvn clean install

Run:
cd target/
java -jar tinyurl-0.0.1-SNAPSHOT.jar