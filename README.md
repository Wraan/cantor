# Cantor Web Application

## Description:

Web Application written for the recruitment purposes. The Task is available to read in the repo root directory which is called Cantor task [web][PL].
It is using WebSockets to connect to FP outside server and to send changed exchange rates to the web client. 
The app was designed to work without refreshing the dashoard with each action
(unless the connection with outside server is closed or not established - app is informing the user is such case). 

Used spring modules and libraries:
* Spring Data JPA
* Spring Security
* Thymeleaf
* Spring MVC
* Spring WebSocket

* Bootstrap
* jQuery
* SockJs
* STOMP WebSocket

## How to run it:

The app is using Postgres Database version 10.4.
In order to run it locally you can just simply run 2 docker images with below commands:

```
docker run --name postgres -e POSTGRES_PASSWORD=password -e POSTGRES_USER=admin -e POSTGRES_DB=cantor -p 5432:5432
 -d postgres:10.4
docker run -p 8080:8080 --name cantor --link postgres:postgres -d zibix12/cantor:1.0
```

After a moment the application will be running on port 8080. You can access it by reaching the `localhost:8080` address. The database is set to `create-drop mode`, so each restart will wipe all the data from the database. On the startup there are created 2 users: `admin` and `user`, both with `password` password.

## Screenshots
https://prnt.sc/oxpy9t

https://prnt.sc/oxpysq

https://prnt.sc/oxpyel

https://prnt.sc/oxpyk3
