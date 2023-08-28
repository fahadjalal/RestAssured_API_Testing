package Session_Get_Post_Put_Delete;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Delete {
    @Test
    public void deleteUser() {
        // Base URL for the API
        String baseUrl = "https://reqres.in/api";
        int userIdToDelete = 2;
        String deleteUserEndpoint = baseUrl + "/users/" + userIdToDelete;
        given()
                .delete(deleteUserEndpoint)
                .then()
                .statusCode(204) // Expected status code for successful deletion
                .assertThat().body(equalTo("")); // Expected empty response body
    }
}
