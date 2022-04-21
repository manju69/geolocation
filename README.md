This code challenge is based on the IP geolocation provided by https://ip-api.com/docs/api:json

1.	Create a relational database schema where you can store the results from the API call
2.	Implement the database schema from the first step using a database of your choice (you can use in-memory SQLite as well)
3.	Create an API endpoint that will take an IP address as parameter (path or query is up to you) and will return its geolocation data by first checking if we have the data on the database and falling back to the external API call
4.	Use a time-based (set at 1 minute) expiring cache to return the data before querying the database again
5.	Think about a way to automatically test this endpoint so that you end up with reliable and reproducible results

Optional steps:
6.	Extend your database schema adding a new column with the insertion timestamp
7.	Modify the third point to refresh the geolocation data if the timestamp is older than 5 minutes
8.	Take error handling and logging in consideration and think about ways of validating the input data

Hints:
•	Build the service using Dropwizard
•	Connect to the database using JDBI
•	Dropwizard includes Guava, so you can use its CacheBuilder to build the cache
•	Dropwizard includes SLF4J
•	Dropwizard includes Hibernate Validator


Thank you for taking this challenge and we hope you will enjoy working on it. 
