package API.RestAssure;


import io.restassured.RestAssured;
import io.restassured.http.*;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssured2 {

    @Test
    public void getReq1(){

        given().header("accept", ContentType.JSON).when().get("https://breakingbadapi.com/api/characters/50")
                .then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
                .body("[0].name", Matchers.equalToIgnoringCase("juan bolsa"))
                .and().body("[0].occupation",Matchers.hasSize(1));
    }
    @Test
    public void getReq2(){

        given().header("accept", ContentType.JSON).when().get("https://breakingbadapi.com/api/characters/50")
                .then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
                .and().body("[0].status",Matchers.is("Deceased"))
                .and().body("[0].nickname.size()",Matchers.greaterThan(1));

    }
    @Test
    public void getReq3() {

        RestAssured.baseURI="https://api.got.show";
        RestAssured.basePath="/api/map/characters/byId/5cc0743504e71a0010b852d9";
        given().header("accept", ContentType.JSON).when().get()
                .then().log().ifStatusCodeIsEqualTo(500).assertThat().statusCode(200).contentType(ContentType.JSON)
                .rootPath("data") //if you put root path as data you dont need to start with putting data to the body
                .and().body("books", Matchers.hasItem("A Storm of Swords"))
                .and().body("dateOfBirth",Matchers.equalTo(283))
                .and().body("house", Matchers.is("House Stark"))
                .body("male",Matchers.isA(Boolean.class));

    }
}
