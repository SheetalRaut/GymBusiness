# GymBusiness
A repository consist of REST API endpoints to manage gym business and data gets store in in memory h2 database.

Members 
- It provides REST endpoint to add new member, get list of all member and get specific member detail by passing member id. 

Gym Classes
- It provides Rest endpoint to add new gym classes with details provided, get list of all gym classes and get details of specific gym class by passing gym class id
- Members can use endpoint /bookings to register themself for a gym class. in case of valid scenario gym member and class gets added in Memberships table.

Steps to run application 

1. mvn clean install 
2. java -jar /target/demo-0.0.1-SNAPSHOT

Rest Enpoints 

1.To add memeber 

curl -X POST \
  http://localhost:8080/addMember \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 79d6b8f0-7ecf-6820-03e7-f5d48a96aa9b' \
  -d '{
	"name" : "David"
}'

2. Get List of all memebrs

curl -X GET \
  http://localhost:8080/getAllMembers \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 126985ff-5115-7529-32d8-1aa2a1642881'

3. Get details of specific member

curl -X GET \
  http://localhost:8080/getMember/1 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 90691cf5-6ec8-62e4-1a56-0f823b6c420e'

4. To add gym class

curl -X POST \
  http://localhost:8080/classes \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: b2d380c9-2cf1-c4b0-bd76-94337098d85e' \
  -d '{
	"name" : "Zumba",
	"capacity" : "20",
	"startDate" : "01/06/2022",
	"endDate" : "28/06/2022",
	"category" : "dance",
	"healthLevelRequired" : "3"
	
}'

5. Get list of all gym classes

curl -X GET \
  http://localhost:8080/classes \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 8f936651-c7dc-ceaa-c789-2f5559d266ec'

6. Get details of specific gym class

curl -X GET \
  http://localhost:8080/classes/1 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: eaba866d-d4b3-67f7-e9ba-4cfa6e01b599'

7. Book a class for a member

curl -X POST \
  http://localhost:8080/bookings \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 26ff5394-c3fa-510d-9949-b0d751ad5f73' \
  -d '{
	"classNumber" : "1",
	"memberNumber" : "2",
	"bookingDate" : "20/05/2020"
}'
