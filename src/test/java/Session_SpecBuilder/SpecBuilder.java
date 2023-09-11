package Session_SpecBuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class SpecBuilder {
    @Test
    public void Spec_Request_Builder()
    {

        //These are the common functionalities
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addParam("Param1", "testingVal");
        requestSpecBuilder.addHeader("auth", "beared 121212");


        RequestSpecification requestSpecification = requestSpecBuilder.build();
        given().spec(requestSpecification)
                .get("https://reqres.in/api/users?page=2")
                .then()
                .body("data.id",hasItem(8))
                .log().all();

    }


    @Test
    public void Spec_Response_Builder()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addParam("Param1", "testingVal");
        requestSpecBuilder.addHeader("auth", "beared 121212");

        RequestSpecification requestSpecification = requestSpecBuilder.build();

        //These are the common assertions for Response
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.expectContentType(ContentType.JSON);
        responseSpecBuilder.expectBody("data.id",hasItem(8));

        ResponseSpecification responseSpecification = responseSpecBuilder.build();

        given().spec(requestSpecification)
                .get("https://reqres.in/api/users?page=2")
                .then()
                .spec(responseSpecification)
                .log().all();
    }

    @Test
    public void Json_Schema()
    {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addParam("Param1", "testingVal");
        requestSpecBuilder.addHeader("auth", "beared 121212");

        RequestSpecification requestSpecification = requestSpecBuilder.build();

        //These are the common assertions for Response
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.expectContentType(ContentType.JSON);
        responseSpecBuilder.expectBody("data.id",hasItem(8));

        ResponseSpecification responseSpecification = responseSpecBuilder.build();

        given().spec(requestSpecification)
                .get("https://reqres.in/api/users?page=2")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema.json"))
                .log().all();
    }
}
