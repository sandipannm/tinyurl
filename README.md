Requirment :

This is to create tiny url from any long url


Technical Specification :
Java 1.8
Springboot 2.0.3
Ehcache
Spring Scheduler
Mockito


Project specification :

Service takes long url in request.
We are generating a random number and appending to the base url for every request url which we are storing in Database. For duplicate url request instead of creatinga new URL we are sending back the old url.
We are caching database entries which we are frequently modifing from DB. So that we don't insert any duplicate rows. Reason being, if we start calling get service again and again for every request It will create too many Database calls.
Kept Scheduler to refresh cache automatically. and whenever the server starts it should fetch all the data from DB and keep it in cache.

Build Project :
mvn clean install

Run:
cd target/
java -jar tinyurl-0.0.1-SNAPSHOT.jar


