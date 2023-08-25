package Assignment_1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItem;

public class Task_1 {
    @Test
    public void Get_Items(){
        RestAssured.baseURI = "https://reqres.in/api";
       Response response = RestAssured.given()
               .get("/users?page=2")
               .then()
               .extract()
               .response();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Status code is not 200");
        System.out.println(response.statusCode());

        int expectedId = 9;
        String expectedFirstName = "Tobias";

        int actualId = response.path("data[2].id");
        String actualFirstName = response.path("data[2].first_name");

        System.out.println(actualId);
        System.out.println(actualFirstName);

        Assert.assertEquals(actualId, expectedId, "ID doesn't match");
        Assert.assertEquals(actualFirstName, expectedFirstName, "First name doesn't match");


        response.then()
                .body("data.email", hasItem("michael.lawson@reqres.in"))
                .body("data.last_name", hasItem("Fields"))
                .body("data.last_name", hasItem("Howell"))
                .log().all();

    }
}
