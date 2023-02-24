# Hotel Service CSI2132 SQL 
 A hotel service created with SQL

https://mkyong.com/jdbc/how-do-connect-to-postgresql-with-jdbc-driver-java/
How to install the api that will connect to the databae with java

https://www.postgresqltutorial.com/postgresql-jdbc/call-postgresql-stored-function/
example of how its used

INSTRUCTIONS:

First: Add the postgresql.jar to your java project by following this image

Second: Make sure to set your variables correctly
String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public"; 
String username = "postgres";
String password = "password"; <-- This needs to be changed to your pgAdmin4 Postgres password
I had issues with it resulting in me only being able to use the public schema