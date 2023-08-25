package Assignment_1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Task_2 {
    @Test
    public void Delete_Item() {

        //DELETE Call and Verify Status Code
        RestAssured.baseURI = "https://reqres.in/api";
        Response deleteResponse = given()
                .when()
                .delete("/users/2")
                .then()
                .extract()
                .response();

        int deleteStatusCode = deleteResponse.getStatusCode();
        System.out.println("DELETE Status Code: " + deleteStatusCode);

    }

    //POST Call and Verification
    @Test
    public void Post_Item() {
        String requestWithJson = "{ \"name\": \"fahJa\", \"year\": \"2020\" }";
        RestAssured.baseURI = "https://reqres.in/api";
        Response postResponse = given()
                .body(requestWithJson.toString())
                .when()
                .post("/register")
                .then()
                .extract()
                .response();
        postResponse.then().log().all();


       String token = postResponse.jsonPath().getString("token");
       System.out.println("Token Value: " + token);

       Assert.assertNotNull(token, "Token value is null");
       Assert.assertFalse(token.isEmpty(), "Token value is empty");

       long responseTime = postResponse.time();
       Assert.assertTrue(responseTime < 10000, "Response time is greater than 10 seconds");
    }

}

