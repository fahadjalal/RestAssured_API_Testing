package Assignment_2;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ApiTest extends BaseTest{

    @Test
    public void RegisterUser() {
        Response response = RestAssured.given()
                .spec(requestSpec)
                .body("{\n" +
                        "\"name\":\"" + RegistrationData.NAME + "\",\n" +
                        "\"email\":\"" + RegistrationData.EMAIL + "\",\n" +
                        "\"password\":\"" + RegistrationData.PASSWORD + "\"\n" +
                        "}")
                .post("/api/authaccount/registration")
                .then()
                .statusCode(200)
                .spec(responseSpec).log().all()
                .extract()
                .response();

    }
    @Test
    public void LoginUser() {
        Response response = RestAssured.given()
                .spec(requestSpec)
                .body("{\n" +
                        "\"email\":\"" + LoginData.EMAIL + "\",\n" +
                        "\"password\":\"" + LoginData.PASSWORD + "\"\n" +
                        "}")
                .when()
                .post("/api/authaccount/login")
                .then()
                .statusCode(200)
                .spec(responseSpec).log().all()
                .extract()
                .response();

        String token = response.jsonPath().getString("token");
        System.out.println("Token: " + token);
    }
    @Test
    public void TestAPIWithToken() {
        // Get the token from the login step
        String token = "Bearer b063ee41-d310-4d1a-b24d-982f51d33cd8"; // Replace with the actual token

        Headers headers_ = new Headers(new Header("Authorization", token));

        Response response = RestAssured.given()
                .headers(headers_)
                .when()
                .get("/api/users?page=1")
                .then()
                .statusCode(200)
                .spec(responseSpec).log().all()
                .extract()
                .response();

        // Verify schema
        response.then().body(matchesJsonSchemaInClasspath("rest_api.json"));

        // Verify ID 11133 exists
        response.then().body("data.id", hasItem(11133));

        // Verify second index email
        response.then().body("data[1].email", equalTo("qweqw@mail.ru"));

        // Verify name is not null
        response.then().body("data.name", notNullValue());

        // Validate Response Time
        response.then().time(lessThan(10000L));

    }


}
