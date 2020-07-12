package API.RestAssure;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class RestAssureDelete {

    ResponseSpecification responseSpecification;
    RequestSpecification requestSpecification;

    @Before
    public void setUp(){

        requestSpecification =new RequestSpecBuilder().log(LogDetail.ALL)
                .setAccept(ContentType.JSON)
                .setAccept(ContentType.JSON).build();

        responseSpecification=new ResponseSpecBuilder().log(LogDetail.BODY)
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
    }

    @Test
    public void deletePet(){

        RestAssured.baseURI="https://petstore.swagger.io";
        basePath="/v2/pet";

        given().accept(ContentType.JSON)
                .when().delete("1903")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON).log().body();

    }
    }
