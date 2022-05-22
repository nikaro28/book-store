# Getting Started - Book Store App
***

Book Store App is an online service that will allow retailers to add books to the online store, and it will allow readers to order book from the available stock.


##Technologies Used
***
- JDK 11
- Spring Boot
- Docker


##Running the Application
- ###Using Docker
    - You would need to have docker engine installed on your system for this.
      Please install it following the instructions given over link : https://docs.docker.com/get-started/
    - Once Docker is installed successfully try running the hello-world docker image.
      This will pull the latest image of hello-world from docker hub if it does not exist locally and run it within a container.
    - The above proves as a test that docker is successfully installed and running on you system.
      Now to run this application on your system via docker, please execute below command on CMD:
  ```commandline
  docker compose up --build app
  ```
    - Above command will create a jar of the application, then create a docker image of it and will run it on your system.
    - Go to your web browser and hit the URL : http://localhost:8080/swagger-ui/, you should land on the **_documentation_** page.
      This confirms that the application is up and running fine on your system.

- ###Using IDE
    - ####Building the Project
        - Make sure you have Maven plugin installed in your IDE, else go to IDE marketplace and install the Maven plugin.
        - Go to the Gradle sidebar and choose Maven -> Reload Project by right-clicking on the project. 
        - This will download all the project dependencies that are needed.


- Once the project is build successfully, navigate to the BookStoreApplication.java file and right-click it to choose Run.
    - This will start your application on the default 8080 port using the embedded tomcat.


##APIs Used
***
- ###POST : User Login API.

  URL : http://localhost:8080/rest/users/login

  Request Payload:
    ```json
    {
      "username": "user",
      "password": "User$12345"
    }
    ```
  Response:
    ```json
    {
      "bearer-token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlt7ImF1dGhvcml0eSI6IlVTRVIifV0sImlhdCI6MTY1MzIwNzc4NywiZXhwIjoxNjUzMjA5NTg3fQ.PLtSDgwj6806eRjVVBh5A4BcE2HtGZWwlO5hx6A5-YFFJT9OZfacn1-adtqSEeWKZa-3uZ2wndi0-EhLoq5VXg"
    }
    ```
  
- ###POST : To add book to store.
  
  URL : http://localhost:8080/rest/books

  Request Headers:
    ```properties
    "Authorization": "Bearer {bearer-token}"
    ```

  Request Payload:
    ```json
    {
      "title": "Harry Potter",
      "author": "J K Rowling",
      "price": 10.99,
      "stock": 20
    }
    ```

- ###POST : To add customer.
  
  URL : http://localhost:8080/rest/customers

  Request Headers:
    ```properties
    "Authorization": "Bearer {bearer-token}"
    ```

  Request Payload:
    ```json
    {
      "firstName": "Nishant",
      "lastName": "Sharma",
      "email": "nishant.sharma@email.com"
    }
    ```

- ###GET : To get orders of a customer
  
  URL : http://localhost:8080/rest/customers/:customer_id/orders?pageNo=0&pageSize=2

  Request Headers:
    ```properties
    "Authorization": "Bearer {bearer-token}"
    ```


### POSTMAN API Collection
- The postman API collections for the project can be found under 
  - /root/postman-collection

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.0/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Session](https://docs.spring.io/spring-session/reference/)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#boot-features-security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

