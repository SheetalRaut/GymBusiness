# GymBusiness
A repository consist of REST API endpoints to manage gym business and data gets store in in memory h2 database.

Members 
- It provides REST endpoint to add new member, get list of all member and get specific member detail by passing member id. 

Gym Classes
- It provides Rest endpoint to add new gym classes with details provided, get list of all gym classes and get details of specific gym class by passing gym class id
- Members can use endpoint /bookings to register themself for a gym class. in case of valid scenario gym member and class gets added in Memberships table.
