# SpringBootResillience4j

- Dependency
  
  - 
    ```
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
        <version>2.2.2.RELEASE</version>
    </dependency>
    ```
  

- Requests:
    - ```
        curl --location --request GET 'http://localhost:1337/health-check'
      ```
    - ```
        curl --location --request GET 'http://localhost:1337/api/sample/getServiceName'
      ```
    - ```
        curl --location --request GET 'http://localhost:1337/api/sample/getNameById?id=1'
      ```
    - ``` 
      curl --location --request POST 'http://localhost:1337/api/sample/saveUser' \
       --header 'Content-Type: application/json' \
       --data-raw '{
           "id" : 2,
           "name" : "hello world",
           "email": "helloworld@gmail.com"
       }'
      ```
    - ```
      curl --location --request GET 'http://localhost:1337/api/sample/getEmailById?id=1'
      ```
    - 
    