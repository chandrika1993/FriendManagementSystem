# Friend Management System
This is an application with a need to build its own social network, "Friends Management" is a common requirement which usually starts off simple but can grow in complexity depending on the application's use case. Usually, the application comprised of features like "Friend", "Unfriend", "Block", "Receive Updates" etc.

## Technology Choice

### Spring Boot
1.	Spring Boot allows easy setup of standalone Spring-based applications.
2.	Ideal for spinning up microservices and easy to deploy.
3.	Makes data access less of a pain, i.e. JPA mappings through Spring Data.

### MySQL
	1.	Used MySQL Workbench for storing the Data of different tables.
	2.	MySQL Workbench allows easy setup of tables by use its UI.
	
### Pivotal Cloud Foundry 
	1.	PCF allows easy deployment of Applications on Cloud.
	2.	Through PCF Metrics we can easily manage and analyze the load on the application.
	3.	Allows easy integration of different tools on the deployed Application.
	
## Deployment on PCF
###
	The Route for Deployment is - 
			https://friend-management-system.cfapps.io/friendmanagementservice/
	
	For example: To access /createFriendConnection endpoint, the URL should be:
			https://friend-management-system.cfapps.io/friendmanagementservice/createFriendConnection

	The Credentials for PCF account are as follows :
			Email ID- chandrika.a.mohan@capgemini.com
			Password- Pcf@1234
	
	The following are the PCF Metrics statics -
	
![Db Script](../master/src/main/resources/pcf/PCF_Metrics.png)
	

## Database

	Below is the simple ER Diagram used for the application.
	
![Db Script](../master/src/main/resources/sql/db_design/ER_Diagram.png)

	The Database is pre populated with 10 persons for testing purpose, also the data 
	can be found from the SQL script file which is placed inside the code repository.
![Db Script](../master/src/main/resources/sql/scripts/SQL_Script.sql)

## Swagger

	Run the Java code locally access the following swagger link to test the API's:
		http://localhost:8080/swagger-ui.html

## List of REST Endpoints and Explanation

	1.	Creates Friend Connection between two Email's specified within friends set.

	•	Path : /createFriendConnection
	
	•	Input :
		{
			"friends":["test1@gmail.com","test2@gmail.com"]
		}

	•	Sample Output :
		{	"success": true }
	
	•	Defined Errors :
				504 : Occurs when Database Error occurs.

	2.	Returns list of friends for an Email Id within friends set.

	•	Path : /getFriends

	•	Input :
		{
			"friends":[“test1@gmail.com”]
		}
	
	•	Output :
		{
		"success": true,
		"friends":[
			"test2@gmail.com",
			"test3@gmail.com"
		],
		"count":2
		}

	•	Defined Errors :
				404 : Email Id not Found i.e. Persons given by the email do not exist.
				504 : Occurs when Database Error occurs.
	
	3.	Returns list of common friends between two Email Id's within friends set.
	
		•	Path : /getCommonFriends
	
		•	Input :
			{
				"friends":["test1@gmail.com","test2@gmail.com"]
			}
			
		•	Output :
			{
			"success": true,
			"friends":[
					"abc@gmail.com",
					"xyz@gmail.com"
						],
			"count":2
			}
		•	Defined Errors :
					401 : Unauthorized Access - The two Email's in the JSON are same.
					404 : Email Id not Found i.e. Persons given by the email do not exist.
					504 : Occurs when Database Error occurs.
	
	4.	Person subscribe for Email Updates from another Person
	
		•	Path : /subscribeForEmailUpdates
	
		•	Input :
			{
				"requestor":"abc@gmail.com",
				"target":"xyz@gmail.com"
			}
	
		•	Output :
				{	"success": true		}
	
	•	Defined Errors :
				401 : Unauthorized Access -The two Email's in the JSON are same.
				401 : Unauthorized Access -Blocked for Subscription.
				404 : Email Id is not Found.
				504 : Occurs when Database Error occurs.
	
	5.	Person block updates from another Person
	
	•	Path : /blockUpdates
	
	•	Input :
		{
			"requestor":"example1@example.com",
			"target":"example2@example.com"
		}
	•	Output :
		{ 	"success": true 	}
		
	•	Defined Errors :
				401 : Unauthorized Access - The two Email's in the JSON are same.
				404 : One of the Email Id not Found. 
				504 : Occurs when Database Error occurs.

	6.	Post an update which returns a list of Email's that will receive the update.
	
	•	Path : /getEmailsWithSubscription
		
	•	Input :
		{
			"sender":"abc@gmail.com",
		 	"target":"Hello, how are you ! xyz@gmail.com"
		}
		
	•	Output :
		{
			"success": true,
			"friends":[
				"xyz@gmail.com",
				"pqr@gmail.com"			
					],
			"count":2
		}
		
	•	Defined Errors :
				404 : Email Id is not Found 
				401 : Unauthorized Access for Email's which are Blocked for Subscription.
				504 : Occurs when Database Error occurs.

## JUnit Code Coverage of REST Endpoints.

	The following is the JUnit Code Coverage.

![Db Script](../master/src/main/resources/Junit_Stats/Junit_Coverage.PNG)