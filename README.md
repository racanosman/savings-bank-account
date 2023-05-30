# Savings Account Banking

This project represent a savings account microservice for opening a savings account in bank. The code is written in Java with Spring Boot.

## Installation

Clone the repo and run
```java
mvn spring-boot:run
```
in the root of the project.

## Usage

The project exposes a endpoint on localhost:8080 at:
```
/api/v1/savingsApi
```

There are 3 http methods available: PUT,POST and GET.

GET method returns the balance of the account.


PUT is used to open a new account for an user. The body of the request must be empty.


POST is used to change the balance of an account.

The POST body must contain the following JSON

```json
{
    "amount":18.92,
    "type":"deposit"
}
```

or

```
{
    "amount":12.65,
    "type":"withdraw"
}
```

## Users

Being a savings microservice the user storing is not relevant for the project. The users passwords are stored in an unsecure fashion in plain text. To select a user you simply add two params ( userName and password ) to any request. Two users having been added for testing purposes (John/12345 & Sara/123456)

Example

```
?userName=John&password=12345
```

## Storage

The data is stored in memory with a list and a map. There is no need for a database set-up.

## Responses

The response send back by the server contains status code and a message that the user will see in frontend.

A response has the following format

```
{
    "message": "User has opened a savings account",
    "code": 1
}
```

## Example Request-Response

Example 1:


PUT request for creating a new savings account for John user with password 12345. There is no request body

URL

```
http://localhost:8080/api/v1/savingsApi?userName=John&password=12345
```
Response

```
{
    "message": "User has opened a savings account",
    "code": 1
}
```

POST request for modify of the balance

URL

```
http://localhost:8080/api/v1/savingsApi?userName=John&password=12345
```


Request body

```
{
    "amount":13,
    "type":"deposit"
}
```

Response

```
{
    "message": "User has made a successful deposit",
    "code": 6
}
```
