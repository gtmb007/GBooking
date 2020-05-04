# GBooking
GBooking is a RESTful Web Service built using SpringBoot with the following functionalities:- 
###### Admin Login
1.) Add Vendor <br/>
2.) Get Vendor <br/>
3.) Add Hotel <br/>
4.) Get Hotel <br/>
5.) Delete Hotel <br/>
6.) Remove Vendor from Hotel <br/>
7.) Add Vendor to Hotel <br/>
###### User SignUp <br/>
###### User SignIn <br/>
1.) User Details with All Bookings <br/>
2.) Update User Name <br/>
3.) Update User Password <br/>
4.) Recharge User Wallet <br/>
5.) Search Hotel by Name Search Key <br/>
6.) Search Hotel by Location Search Key <br/>
7.) Book Hotel (amount debited from User Wallet) <br/>
8.) Update Booking (amount debited/credited from User Wallet) <br/>
9.) Cancel Booking (amount credited to User Wallet) <br/>

# Start the project from Scratch
Go to https://start.spring.io/  generate, extract the downloaded zip file and import it into Eclipse. <br/>

Your installed JDK should be at least Java8 Version and establish database connection on eclipse. <br/>

Go to pom.xml and add all required dependencies.

# Modify these according to your database credential
Go to application.properties file
###### MYSQL settings
spring.datasource.url=jdbc:mysql://localhost:3306/spring_project <br/>
spring.datasource.username=root <br/>
spring.datasource.password=abcdef
###### Oracle settings
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe <br/>
spring.datasource.username=system <br/>
spring.datasource.password=oracle
###### Tomcat Server
Default server port used by tomcat is 8080 <br/>
You can manually set the server port in the application.properties file- server.port=8090

# Using Postman to validate REST API URL

###### Note:- REST API part is on process
Admin Login:- GET Request http://localhost:8090/admin/{id}/{password} <br/>
Add Vendor:- POST Request http://localhost:8090/vendor <br/>
Get Vendor:- GET Request http://localhost:8090/vendor/{vendorId} <br/>
Add Hotel:- POST Request http://localhost:8090/hotel <br/>
Get Hotel:- GET request http://localhost:8090/hotel/{hotelId} <br/>
Delete Hotel:- DELETE request http://localhost:8090/hotel/{hotelId} <br/>
Remove Vendor from Hotel:- PUT request http://localhost:8090/hotel/removeVendor/{hotelId} <br/>
Add Vendor to Hotel:- PUT request http://localhost:8090/hotel/addVendor/{hotelId} <br/>
User SignUp:- POST Request http://localhost:8090/user <br/>
User SignIn:- GET Request http://localhost:8090/user/{id}/{password}<br/>
User Details with All Bookings:- GET Request http://localhost:8090/user/{id} <br/>
Update User Name:- PUT Request http://localhost:8090/user/name/{id} <br/>
Update User Password:- PUT Request http://localhost:8090/user/password/{id} <br/>
Recharge User Wallet:- PUT Request http://localhost:8090/user/{id}/{amount} <br/>
Search Hotel by Name Search Key:- GET request http://localhost:8090/hotel/name/{key} <br/>
Search Hotel by Location Search Key:- GET request http://localhost:8090/hotel/location/{key} <br/>
Book Hotel (amount debited from User Wallet):- PUT Request http://localhost:8090/booking/{userId} <br/>
Get Booking:- GET Request http://localhost:8090/booking/{id} <br/>
Update Booking (amount debited/credited from User Wallet):- PUT Request http://localhost:8090/booking/{userId}/{bookingId} <br/>
Cancel Booking (amount credited to User Wallet):- DELETE Request http://localhost:8090/booking/{userId}/{bookingId}  <br/>

# Logging using Log4j
I have provided logging logic in LoggingExpect.java class i.e followed the Spring AOP concept for all the methods of DAO and Service class after throwing an exception on error level. <br/>

If you want to get the log details into a file then create a folder(named as logs) and add a file(error.log) into that folder and define the path of this log file in the application.properties file- logging.file=logs/error.log
