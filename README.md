COURSE MANAGEMENT SYSTEM --- here we are providing several apis to manage the course and related assignment and enrollemnt for any course/assignment. 
                           For which I have used microservices to create independent services for USER MANAGEMENT, ASSIGNMENT MANAGEMENT, COURSE MANAGEMENT, STUDENT ENROLLMENT.
						   I have used java 8, Spring boot 2.7.5, Spring security, Spring Cloud API Gatway, Eureka server, h2 database
						   
Setup - Start ServiceRegistrationAndDiscoveryService
        Start UserManagement service
		Start CourseManagement Service
		Start AssignmentManagement Service
		Start StudentEnrollment Service
		
Testing-- To test the business logic I have used Junit4 and Mockito

Requirements - To access any end point will require token on the basis of token only authorized points will be allowed to access.

ServiceRegistrationAndDiscoveryService  ---  this is used to find the details of the services when we need them. I have used Eureka Server

API Gatway- To route through a single point I have used spring-cloud-starter-gateway. Its purpose is to act as a proxy, and it receives requests from different clients. 
            Then process to send API calls to specific internal services based on characteristics in clients requests. It further aggregates the results obtained from the 
			microservices and sends them back to the client.


To access all the service all the details given below-

USER MANAGEMENT SERVICE
http://localhost:8080/user/login  -- POST METHOD --- this is used to generate token which will be used to authenticate users
http://localhost:8080/user/signup  --- POST METHOD --- this is used to create user no authentication or authorization is needed for this
http://localhost:8080/user/update/{id}  --- PUT METHOD---- this is used to update user details by user id authorized by ADMIN/SELF Created details only
http://localhost:8080/user/{id} --- GET METHOD --- this is used to update user details by user id authorized by ADMIN only
http://localhost:8080/user/delete/{id}  --- DELETE METHOD --- this is used to delete user by id authorized by ADMIN only
http://localhost:8080/user/removeALL --- DELETE METHOD --- this is used to dlete all data authorized by ADMIN only

ASSIGNMENT MANAGEMENT SERVICE
http://localhost:8083/assignment/all --- GET METHOD --- this is used to get all course details which is created by loged in userauthorized by INSTRUCTOR role (authentication will be done be token)
http://localhost:8083/assignment/{id} --- GET METHOD --- this is used to get course details by ID which is created by loged in userauthorized by INSTRUCTOR role (authentication will be done be token)
http://localhost:8083/assignment/createAssignment  --- post method --- THIS IS USED TO CREATE COURSE AUTHORIZED by INSTRUCTOR ROLE
http://localhost:8083/assignment/{id}  --- DLETE METHOD-- This is used to delete course by id authorized by INSTRUCTOR ROLE created by by loggen in user
http://localhost:8083/assignment/update/2 --- PUT METHOD --- This is used to update the course details created by the logged in user authorized by INSTRUCTOR

COURSE MANAGEMENT SERVICE
http://localhost:8081/course/all --- GET METHOD --- this is used to get all course details which is created by loged in userauthorized by INSTRUCTOR role (authentication will be done be token)
http://localhost:8081/course/{id} --- GET METHOD --- this is used to get course details by ID which is created by loged in userauthorized by INSTRUCTOR role (authentication will be done be token)
http://localhost:8081/course/createCourse  --- post method --- THIS IS USED TO CREATE COURSE AUTHORIZED by INSTRUCTOR ROLE
http://localhost:8081/course/{id}  --- DLETE METHOD-- This is used to delete course by id authorized by INSTRUCTOR ROLE created by by loggen in user
http://localhost:8081/course/update/2 --- PUT METHOD --- This is used to update the course details created by the logged in user authorized by INSTRUCTOR

STUDENT ENROLLMENT SERVICE
http://localhost:8082/enroll/course - POST METHOD --- this is used to enroll any student for any course which is created by the logged in instructor authorized by INSTRUCTOR
http://localhost:8082/enroll/assignment- POST METHOD --- this is used to enroll any student for any assignment which is created by the logged in instructor authorized by INSTRUCTOR
http://localhost:8082/enroll/student-details-by-enrollment-no/{id} --- this is used to get the student details by using enrollment id authorized by INSTRUCTOR


EUREKA SERVER
http://localhost:8761/

SPRING CLOUD API GATEWAY
http://localhost:9191/user/login  -- POST METHOD --- this is used to generate token which will be used to authenticate users
http://localhost:9191/user/signup  --- POST METHOD --- this is used to create user no authentication or authorization is needed for this
http://localhost:9191/user/update/{id}  --- PUT METHOD---- this is used to update user details by user id authorized by ADMIN/SELF Created details only
http://localhost:9191/user/{id} --- GET METHOD --- this is used to update user details by user id authorized by ADMIN only
http://localhost:9191/user/delete/{id}  --- DELETE METHOD --- this is used to delete user by id authorized by ADMIN only
http://localhost:9191/user/removeALL --- DELETE METHOD --- this is used to dlete all data authorized by ADMIN only

http://localhost:9191/assignment/all --- GET METHOD --- this is used to get all course details which is created by loged in userauthorized by INSTRUCTOR role (authentication will be done be token)
http://localhost:9191/assignment/{id} --- GET METHOD --- this is used to get course details by ID which is created by loged in userauthorized by INSTRUCTOR role (authentication will be done be token)
http://localhost:9191/assignment/createAssignment  --- post method --- THIS IS USED TO CREATE COURSE AUTHORIZED by INSTRUCTOR ROLE
http://localhost:9191/assignment/{id}  --- DLETE METHOD-- This is used to delete course by id authorized by INSTRUCTOR ROLE created by by loggen in user
http://localhost:9191/assignment/update/2 --- PUT METHOD --- This is used to update the course details created by the logged in user authorized by INSTRUCTOR

http://localhost:9191/course/all --- GET METHOD --- this is used to get all course details which is created by loged in userauthorized by INSTRUCTOR role (authentication will be done be token)
http://localhost:9191/course/{id} --- GET METHOD --- this is used to get course details by ID which is created by loged in userauthorized by INSTRUCTOR role (authentication will be done be token)
http://localhost:9191/course/createCourse  --- post method --- THIS IS USED TO CREATE COURSE AUTHORIZED by INSTRUCTOR ROLE
http://localhost:9191/course/{id}  --- DLETE METHOD-- This is used to delete course by id authorized by INSTRUCTOR ROLE created by by loggen in user
http://localhost:9191/course/update/2 --- PUT METHOD --- This is used to update the course details created by the logged in user authorized by INSTRUCTOR

http://localhost:9191/enroll/course - POST METHOD --- this is used to enroll any student for any course which is created by the logged in instructor authorized by INSTRUCTOR
http://localhost:9191/enroll/assignment- POST METHOD --- this is used to enroll any student for any assignment which is created by the logged in instructor authorized by INSTRUCTOR
http://localhost:9191/enroll/student-details-by-enrollment-no/{id} --- this is used to get the student details by using enrollment id authorized by INSTRUCTOR