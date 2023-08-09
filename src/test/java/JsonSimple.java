import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class JsonSimple {

    @Test
    public void test_Get2()
    {
        Map<String,Object> pramMap = new HashMap<>();
        pramMap.put("name","Test1");
        pramMap.put("Job","Test_Job");

        JSONObject param = new JSONObject(pramMap);
        given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(param.toString())
                .post("https://reqres.in/api/users?page=2");

    }

}
