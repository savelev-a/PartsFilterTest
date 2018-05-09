This is example Parts filter

Install instructions:
1. run sql\init.cmd to create sample database. You must have psql in your PATH. Skip this step if you already have database named "parts".
2. Deploy distr\parts.war to your tomcat server. Just copy it to "webapps" directory on your tomcat server.
3. Default username and password for database is "postgres", database name is "parts". You can change this by editing "db.properties" file in "webapps\parts\WEB-INF\classes" directory on your tomcat server.
4. Restart tomcat server and navigate to http://localhost:8080/parts/