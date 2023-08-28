package Session_Get_Post_Put_Delete;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItem;

public class Get_Assertion {
    @Test
    public void test_Get()
    {
        System.out.println("Test Get");
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(response.getBody().asPrettyString());
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(),200);
    }
    //Method chaining process.
    @Test
    public void Test_CheckItem() {
        RestAssured.given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .body("data.id", hasItem(8))
                .log().all();
    }
}
