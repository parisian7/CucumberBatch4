package API.RestAssure;

import API.POJO.PetPojo;

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

import static io.restassured.RestAssured.*;

public class RestAssuredPost {

    public static final String NAME ="name";
    public static final String STATUS ="status";

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @Before
    public void setUp(){

        baseURI="https://petstore.swagger.io/";
        basePath="v2/pet";
        requestSpecification=new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON).build();
                                                                        //if you want to see the body/or any other options
        responseSpecification=new ResponseSpecBuilder().log(LogDetail.BODY)
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

            }

    /**
     * Json file
     */
    @Test
    public void createPet(){

        File file=new File("target\\pet.json");
        given().spec(requestSpecification).body(file)
                .when().post("https://petstore.swagger.io/v2/pet")
                .then().spec(responseSpecification).body("name", Matchers.equalTo("Hatiko"))
                .and().body("status",Matchers.is("goneAlso"));

        //Request body for POST request is RestAssure can be assigned
        //-JsonFile
        //-Java Object (Pojo)
        //
    }
    /*
    Java Object(Pojo class)
     */
    @Test
    public void createPet2(){
        PetPojo petPojo=new PetPojo(1011, "hatiko","gone");


        //prerequisite
        given().spec(requestSpecification).body(petPojo)
                .when().post()
                .then().spec(responseSpecification)
                .and().body(NAME, Matchers.is(petPojo.getName()))
                .and().body(STATUS,Matchers.is(petPojo.getStatus())).log().body();

    }

    /**
     * Java generics(Map/List)
     */
    @Test
    public void createPet3(){

        Map<String,Object> petPayload=new HashMap<>();

        petPayload.put(NAME,"Hatiko");
        petPayload.put("age",7);
        petPayload.put(STATUS,"gone");
        petPayload.put("id",1012);
        petPayload.put("photoUrl","s3.amazon.com/myPet/bestpic.jpg");

        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(petPayload)
                .when().post()
                .then().spec(responseSpecification)
                .and().body(NAME,Matchers.is(petPayload.get(NAME))).log().all();






    }

}
