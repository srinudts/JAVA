Ad Campaign Application - Project Overview 

a) Developed Ad Campaign web application using Spring Boot, 
    Webserver-  Tomcat Server (port:8080)- provided by Spring
    Database - H2 Database provided by Spring
    Used Postman for posting requests to the tomcat server
    Used Spring Scheduler for deactivating the Ads based on their duration time given
  
=================================================================================
b) Steps to run the application

	1)Run the CIMApplication.java in CIM-WEB module as Java Application

	2) Post Requests using PostMan Rest Client

		a) http://localhost:8080/ad - Post Method
	 
		    POST : save Ad()
			Sample Request:
			{
				"partnerId" :"SRI",
				"duration" : 100,
				"adContent" :"SRINIVAS"
			}

	   b) http://localhost:8080/ad/SRI - Get Method

			GET :getAdByParterId()
			 Sample Response
			 {
				"errors": [],
				"otherInformation": null,
				"data": {
					"adId": 1,
					"partnerId": "SRI",
					"duration": 100,
					"adContent": "SRINIVAS",
					"createdDate": 1498452797163,
					"actvFlag": true
				},
				"requestId": null
			  }

================================================================================
c) Testing
      Unit Test Cases for AdController, AdService 
      Integration Testing for whole Ad application

