package Session_auth;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import org.testng.annotations.Test;

public class auth {
    @Test
    public void BasicAuth() {
        String url = "https://postman-echo.com/basic-auth";
        //username: Postman
        //Password: password
        RestAssured.given().auth().basic("postman", "password")
                .get(url)
                .then()
                .log().all();
    }

    @Test
    public void BasicAuthPreemptive() {
        String url = "https://postman-echo.com/basic-auth";
        //username: Postman
        //Password: password
        RestAssured.given().auth().preemptive().basic("postman", "password")
                .get(url)
                .then()
                .log().all();
    }

    @Test
    public void BasicAuthDigest() {

        //intermidate of Oauth and basic
        //Generate the hash of username and password
        String url = "https://postman-echo.com/basic-auth";
        //username: Postman
        //Password: password
        RestAssured.given().auth().digest("postman", "password")
                .get(url)
                .then()
                .log().all();
    }

    @Test
    public void BasicAuthForms() {

        //intermidate of Oauth and basic
        //Generate the hash of username and password
        String url = "https://practice.expandtesting.com/login";
        //username: Postman
        //Password: password
        RestAssured.given().auth()
                .form("practice ", "SuperSecretPassword!",
                        new FormAuthConfig("/authenticate", "username", "password")
                )
                .get(url)
                .then()
                .log().all();
    }
}
