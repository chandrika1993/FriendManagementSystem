DROP TABLE IF EXISTS BlockStatus;
DROP TABLE IF EXISTS Subscription;
DROP TABLE IF EXISTS Friends;
DROP TABLE IF EXISTS UserProfile;

create schema dbo;

CREATE TABLE UserProfile(
	userProfileId int IDENTITY NOT NULL PRIMARY KEY,
	userName varchar(256) NULL,
	userEmailId varchar(256) NOT NULL,
	contactNo varchar(256) NULL
	)
	
	CREATE TABLE Friends(
	id int IDENTITY NOT NULL PRIMARY KEY,
	friendEmailId varchar(256) NULL,
	userProfile int NULL FOREIGN KEY REFERENCES UserProfile(userProfileId)
	)
	
	CREATE TABLE Subscription(
	id int IDENTITY NOT NULL PRIMARY KEY,
	emailId varchar(256) NULL,
	subscriptionStatus char(1) NULL,
	userProfile int NULL FOREIGN KEY REFERENCES UserProfile(userProfileId)
	)
	
	CREATE TABLE BlockStatus(
	id int IDENTITY NOT NULL PRIMARY KEY,
	emailId varchar(256) NULL,
	block_status char(1) NULL,
	userProfile int NULL FOREIGN KEY REFERENCES UserProfile(userProfileId)
	)
	