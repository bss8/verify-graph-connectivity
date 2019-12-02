# Verify-Graph-Connectivity Mircroservice

## Dependencies
To build:
- Maven 3.3.9
- JDK 1.8.0_121
To deploy: 
- Google Cloud CLI

## Technology Stack
Component of a microservice architecture, developed with a `Mobile First` approach. 

REST API: Spring Boot 2.2.1.RELEASE
Templating Engine: Thymeleaf
Java testing: JUnit 5 Jupiter
Client-side: JQuery 3.4.1
UI: Bootstrap 4.3.1 

## Build the back-end
In the main project directory, where `pom.xml` resides, execute:
`mvn clean package`
Maven will build the project and generate a `target` directory, which will contain the project JAR package.
To build and skip unit tests, execute:
`mvn clean package -DskipTests`

## Run the back-end

With the back-end built and `target` directory with JAR present, open a CMD window. 
 
Execute: `java -jar connectedgraphs-*.jar`

Open a browser and navigate to:
`localhost:8080/graphs`

## Viewing the API
To view the available API endpoints and details about them:
1. Ensure the project is running and you can navigate to `localhost:8080`
2. Navigate to: 
http://localhost:8080/swagger-ui.html

    ![Swagger UI screenshot](https://i.imgur.com/yEwxkYc.png)

## Deploy the back-end

1. Build Docker image:
`gcloud builds submit --tag gcr.io/connected-graphs/connectedgraphs`
2. Deploy the image:
`gcloud beta run deploy --image gcr.io/connected-graphs/connectedgraphs`
    - Select [1] Cloud Run (fully managed) for the target platform
    - Select [3] us-central1 for the region
    - Press `Enter` to select the default for service name (connectedgraphs)

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

# Connected Graphs

## UML Class Diagram

![Graphs Application Class Diagram](https://i.imgur.com/nQfIKKf.png)

## User Guide
For a single graph run, use the left panel. 
Enter the number of vertices. Preferably for a low value, as a large number of vertices in a 
directed graph of N vertices will have N*(N-1) possible edges (e.g., 200 vertices have `200*(199)=39800`)
that have to be checked.  

Select the type of graph - `directed` or `undirected`

Click `Connect Vertices` - result of # of edges will be displayed to the right. 

To run a simulation, use the right panel. 
Enter the number of desired runs in the input field. As mentioned above, try to keep N within reasonable
bounds so as to limit the growth. 

Click `Run Simulation` - you can press `Ctrl+Shift+I` and view the console output for details. 
The first 30 results will be plotted in the graph at the bottom of the page. The x axis signifies 
the number of vertices. The y axis is the number of edges it took to connect the graph. On occasion, 
the simulation degenerates and the number of edges for a given N value will be displayed as the maximum 
possible. Re-run the N value using the single graph run left panel. 

### Debug Information
Debug is turned on by default. Press `Ctr+Shft+I` or `F12` on some browsers to view the console.
Running the application locally will also display details in the console. 

## Simulation Results


