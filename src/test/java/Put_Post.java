import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Put_Post {

    @Test
    public void Test_POST_Map()
    {

        //Post using Map.
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name","Test1");
        paramMap.put("Job","Test_Job");

        JSONObject param = new JSONObject(paramMap);
        given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)  //only JSON will entertained.
                .body(param.toString())
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().all();
    }


    @Test
    public void Test_POST_Request()
    {

        //Post using Map.
        JSONObject request = new JSONObject();
        request.put("name","Test1");
        request.put("Job","Test_Job");
        System.out.println(request);
        System.out.println("-----------------------------------------\n");
        given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)  //only JSON will entertained.
                .body(request.toString())
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().all();
    }


    @Test
    public void Test_POST_RequestWithJSON()
    {
        String requestWithJson = "{\"name\":\"Test2\",\"Job\":\"Test_Job\"}";
        given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)  //only JSON will entertained.
                .body(requestWithJson.toString())
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    public void Test_Put_Request()
    {

        //Post using Map.
        JSONObject request = new JSONObject();
        request.put("name","Test4");
        System.out.println(request);
        System.out.println("-----------------------------------------\n");
        given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)  //only JSON will entertained.
                .body(request.toString())
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().all();
    }


}
