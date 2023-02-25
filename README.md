# Hotel Service CSI2132 SQL 
 A hotel service created with SQL

https://mkyong.com/jdbc/how-do-connect-to-postgresql-with-jdbc-driver-java/
How to install the api that will connect to the databae with java

https://www.postgresqltutorial.com/postgresql-jdbc/call-postgresql-stored-function/
example of how its used

INSTRUCTIONS:

First: Add the postgresql.jar to your java project by following this image (I use visual studio code)
![image](https://user-images.githubusercontent.com/113709937/221067494-1eb2e6f9-3505-4f09-b150-9286f4004748.png)


Second: Make sure to set your variables correctly
String url = "jdbc:postgresql://127.0.0.1:5432/postgres?currentSchema=public"; 
String username = "postgres";
String password = "password"; <-- This needs to be changed to your pgAdmin4 Postgres password
I had issues with it resulting in me only being able to use the public schema

#INSTRUCTION TO RUN
You will need to install tomcat https://tomcat.apache.org/download-90.cgi
You will need to install the community server from redhat on the visual studio store
![image](https://user-images.githubusercontent.com/113709937/221330704-6997d27b-af18-4f45-adb9-6b7138c83b62.png)
First you need to right click the MAVEN
![image](https://user-images.githubusercontent.com/113709937/221330740-3c4ffc32-df50-4b19-a4f0-3b18f9b7b504.png)
After that you need to click the install to load a new war file
![image](https://user-images.githubusercontent.com/113709937/221330816-ea0a6f5d-55c1-4653-8216-72caf4a1bb95.png)
After that you need to deploy the newly created war file (should be found in the target folder)
Better instruction in the following video



# Usefull video
https://www.youtube.com/watch?v=RiPot1ne8rI&ab_channel=LearningFromExperience
