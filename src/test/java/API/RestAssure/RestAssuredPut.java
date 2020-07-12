package API.RestAssure;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredPut {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    @Before
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON).build();
                                                                                        //if you want to see the body
        responseSpecification=new ResponseSpecBuilder().log(LogDetail.BODY)
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200).build();
    }

    @Test
    public void updatePet() {

        File updatePetPayload = new File("target/pet.json");

        given().spec(requestSpecification).body(updatePetPayload)
                .when().put("https://petstore.swagger.io/v2/pet")
                .then().spec(responseSpecification)
                .and().body(RestAssuredPost.NAME, Matchers.is("Hatiko"))
                .and().body(RestAssuredPost.STATUS, Matchers.is("goneAlso")).log().body();


    }

    //what was your last contribution Matchers Class for assertion
    @Test
    public void updatePet2() {

        Map<String, Object> updatePetPayload = new HashMap<>();
        updatePetPayload.put(RestAssuredPost.NAME, "Pretzel");
        updatePetPayload.put("id", 198745);
        updatePetPayload.put(RestAssuredPost.STATUS, "available");

        given().spec(requestSpecification).body(updatePetPayload)
                .when().put("https://petstore.swagger.io/v2/pet")
                .then().spec(responseSpecification)
                .and().body(RestAssuredPost.NAME, Matchers.equalTo(updatePetPayload.get(RestAssuredPost.NAME)))
                .and().body(RestAssuredPost.STATUS, Matchers.is(updatePetPayload.get(RestAssuredPost.STATUS))).log().body();


    }
}
