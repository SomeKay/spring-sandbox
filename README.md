# Spring Sandbox

A Java Spring playground for testing out different things.

## Run application

The application can be run by invoking `./mvnw spring-boot:run`.

The application can be accessed [locally at port 5000](http://localhost:5000).

## Deployment on AWS

To deploy the application:
 - create a new Elastic Beanstalk Application
 - build this application with `./mvnw package`
 - upload it to your Elastic Beanstalk Application (no need for special configuration)
 