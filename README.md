# Getting Started

## Dependencies

- Maven 3.x
- Firebase CLI
- Google Cloud CLI


## Build the back-end
In the main project directory, where `pom.xml` resides, execute:
`mvn clean package`
 Maven will build the project and generate a `target` directory, which will contain the project JAR package.
 
## Build the front-end

## Run the back-end
With the back-end built and `target` directory with JAR present, execute:
`java -jar connectedgraphs-*.jar`
Open a browser and navigate to:
`localhost:8080`

## Viewing the API
To view the available API endpoints and details about them:
1. Ensure the project is running and you can navigate to `localhost:8080`
2. Navigate to: 
http://localhost:8080/api/swagger-ui.html
![Swagger UI screenshot](https://i.imgur.com/yEwxkYc.png)

## Deploy the back-end

1. Build Docker image:
`gcloud builds submit --tag gcr.io/connected-graphs/connectedgraphs`
2. Deploy the image:
`gcloud beta run deploy --image gcr.io/connected-graphs/connectedgraphs`

## Deploy the front-end

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

