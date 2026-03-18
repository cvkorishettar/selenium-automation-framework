package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class fetchResponce {
    public void getResponseData(){
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(403)
                .body("data.first_name",equalTo("Janet"));


    }
}
