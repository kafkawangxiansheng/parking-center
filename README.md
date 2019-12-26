# Sanei
1. Install maven
2. Install mySQL newest version
3. restore sanei_db.sql files in MySQL database server
4. open application.properties file and edit to right information in your system (database, and so on)
5. open terminal command window and goto sanei root folder
6. run command: mvn clean install
5.1 Import all projects in miracleave folder into eclipse IDE then use can clean, install the project as maven project as well. Ofcourse you need install the maven plugin into eclipse IDE before that.
7. In eclipse IDE, "run as" -> "Spring Boot App" on SaneiMainApi, and SaneiMainSystem projects
8. For API project,  you can see the result in URL:  http://localhost:1235/profiles/
9. For Web project, you can see the result in URL: http://localhost:8080/list-profile (login with username: admin and password: password)
10. For export user profile, click onto Profile export link below the list of user info in (9)
11. The API project has support swagger-ui for easy to learn and test the API,  you can explore all API document in this URL: http://localhost:1235/swagger-ui.html

################################### For Log4j2
1. Open resources/log4j2.xml change your logs/app.log folder to correct with your system
2. Look into the resources/log4j2.component.properties file, it contains all config for log4j2
3. Try to run http://localhost:8080/list-profile and look into the console, and the log file in step 1, you will see the debug line, 
ex: 2019-12-22 23:28:48,908 DEBUG j.c.s.m.c.ProfileController [http-nio-8080-exec-1] Get list profile by Vincent
