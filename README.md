# FriendManagementSystem
This is an application with a need to build its own social network, "Friends Management" is a common requirement which usually starts off simple but can grow in complexity depending on the application's use case. Usually, the application comprised of features like "Friend", "Unfriend", "Block", "Receive Updates" etc.

# Technology Choice

# Spring Boot
1.	Spring Boot allows easy setup of standalone Spring-based applications.
2.	Ideal for spinning up microservices and easy to deploy.
3.	Makes data access less of a pain, i.e. JPA mappings through Spring Data.

# MySQL
	1.	Used MySQL Workbench for storing the Data of different tables.
	2.	MySQL Workbench allows easy setup of tables by use its UI.
	
#Pivotal Cloud Foundry 
		1.	PCF allows easy deployment of Applications on Cloud.
		2.	Through PCF Metrics we can easily manage and analyse the load on the 			application.
		3.	Allows easy integration of different tools on the deployed Application.

# Deployment to PCF





# Database
	Below is the simple SQL Design Diagram used for the application .
	
![SQLDB_Design](sql/db_design/SQLDB_Design.png?raw=true "SQLDB_Design")

# List of REST Endpoints and Explanation

	1.	Creates Friend Connection between two emails specified within friends set.

	•	Path : /createFriendConnection
	
	•	Input :
		{
		"userName":"example",
		"emailId":" example@gmail.com",
		"contactNo":"453457834",
		"friends":["example@gmail.com","abc@gmail.com"]
		}

	•	Sample Output :
		{	"success": true }
	
	•	Defined Errors :
			504 : Occurs when Database Error occurs.

	2.	Returns list of friends for an Email Id.

	•	Path : /getFriends

	•	Input :
		{
		"userName":"example",
		"emailId":" example@gmail.com",
		"contactNo":"453457834",
		"friends":[“example@gmail.com”]
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
			401 : Unauthorized Access -The two email addresses in the input are the same.
			404 : Email Id not Found i.e. Persons given by the email do not exist.
			504 : Occurs when Database Error occurs.
	
	4.	Returns list of common friends between two Email Ids.
	
		•	Path : /getCommonFriends
	
		•	Input :
			{
			"userName":"example",
			"emailId":" example@gmail.com",
			"contactNo":"453457834",
			"friends":["example@gmail.com","abc@gmail.com"]
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
				401 : Unauthorized Access - The two emails in the json are same.
				404 : Email Id not Found i.e. Persons given by the email do not exist.
				504 : Occurs when Database Error occurs.
	
	5.	Person subscribe for Email Updates from another Person
	
		•	Path : /subscribeForEmailUpdates
	
		•	Input :
			{
			"requestor":"abc@gmail.com",
			"target":"xyz@gmail.com"
			}
	
		•	Output :
			{"success": true}
	
	•	Defined Errors :
			401 : Unauthorized Access -The two emails in the json are same.
			401 : Unauthorized Access -Blocked for Subscription.
			404 : Email Id is not Found.
			504 : Occurs when Database Error occurs.
	
	6.	Person block updates from another Person
	
	•	Path : /blockUpdates
	
	•	Input :
		{
		"requestor":"example1@example.com",
		"target":"example2@example.com"
		}
	•	Output :
		{ "success": true }
		
	•	Defined Errors :
			401 : Unauthorized Access - The two emails in the JSON are same.
			404 : One of the Email Id not Found. 
			504 : Occurs when Database Error occurs.

	7.	Post an update which returns a list of emails that will receive the update.
	
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
			401 : Unauthorized Access for Emails which are Blocked for Subscription.
			504 : Occurs when Database Error occurs.



