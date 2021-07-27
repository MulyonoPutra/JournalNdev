## Journal Ndev
A Blog Engine Web Apps Rest API

## With Implements Features
- Authentication
  - Login
    - POST http://localhost:8080/api/auth/login
  - Register
    - POST http://localhost:8080/api/auth/register
  
- Post Blog
  - Create
    - POST http://localhost:8080/api/v1/post
  - Find All
    - GET http://localhost:8080/api/v1/post
  - Delete
    - DELETE http://localhost:8080/api/v1/post/6
  - Find By Title
    - POST http://localhost:8080/api/v1/post/search/title
  - Find By ID
    - GET http://localhost:8080/api/v1/post/1
  
- Category Blog
  - Create
    - POST http://localhost:8080/api/v1/category
  - Find All
    - GET http://localhost:8080/api/v1/category
  - Delete
    - DELETE http://localhost:8080/api/v1/category/2
  - Find By ID
    - GET http://localhost:8080/api/v1/category/2
  
- Flipped Card Blog
  - Create
    - POST http://localhost:8080/api/v1/card
  - Find All
    - GET http://localhost:8080/api/v1/card
  - Delete
    - DELETE http://localhost:8080/api/v1/card/2
  - Find By ID
    - GET http://localhost:8080/api/v1/card/2


## Developed Using
- Spring Boot
- Spring Data JPA
- MySQL Database
- Spring Security
- Json Web Token
- Swagger
- Mapstruct
- Lombok
