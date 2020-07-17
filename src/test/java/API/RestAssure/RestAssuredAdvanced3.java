package API.RestAssure;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class RestAssuredAdvanced3 {
    Response response;

    @Before
    public void setUp() {

        RestAssured.baseURI = "http://api.football-data.org/";
        RestAssured.basePath = "v2/competitions/2000/scorers";

        RestAssured.requestSpecification = new RequestSpecBuilder().setAccept(ContentType.JSON)
                .addHeader("X-Auth-Token", "3f4dda87d3404d7cadcfaeedf7a6c667").build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
                .expectStatusCode(200).build();
        response = given().spec(requestSpecification).when().get().then().spec(responseSpecification).extract().response();
    }
    @Test
    public void adv(){
        //using generics is using java generics Map / String / List ...
        // * is called wildcard
        Response response=given().spec(requestSpecification).when().get().then().spec(responseSpecification).extract().response();

        Map<?,?> map=response.as(Map.class);
        //map.get("status")

        //We are using 0Auth 2.0 authentication to create token it lasts 3-5 minutes.
    }
}
