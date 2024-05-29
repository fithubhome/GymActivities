# GymActivities

Exercises, Workouts, Events &amp; History

## How to set up the project

1. Get docker image for mysql
   docker run --name activities_db -v "Path to a folder where you want to save the data":/var/lib/mysql -e
   MYSQL_ROOT_PASSWORD=password -p 3306:3306 -d mysql:latest
2. Connect to DB using root user
3. Run the sql script setUp.sql
4. Create the user and pass that are mentioned in database configuration file "application-dev.yml"
5. Run the spring-boot app

