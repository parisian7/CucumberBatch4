package API.RestAssure;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class RestAssureGet {
    @Before
    public void setUp(){

        baseURI="https://petstore.swagger.io/";
        basePath="v2/pet";

    }


    @Test
    public void get1(){


        given().accept(ContentType.JSON)
                .when().get("110").then().contentType(ContentType.JSON).statusCode(200);
    }

    @Test
    public void getPet2(){
        given().accept(ContentType.JSON)
                .when().get("{id}",110).then().contentType(ContentType.JSON).statusCode(200);
    }

    @Test
    public void getPet3(){
        given().accept(ContentType.JSON)
                .when().request("GET","110").then().statusCode(200);


    }
    @Test
    public void getPet4(){
        given().accept(ContentType.JSON)
                .when().request("GET","{id}",110).then().statusCode(200).log().body(false);
    }


}
