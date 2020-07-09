package API.RestAssure;

import API.POJO.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class RestAssuredDeserialization2 {

    @Test
    public void getUser(){

        UserPojo parsedUser=given().header("accept", ContentType.JSON).when()
                .request("GET","https://reqres.in/api/users/2")
                .then().statusCode(200).and().contentType(ContentType.JSON).extract().as(UserPojo.class);

        Assert.assertEquals("janet.weaver@reqres.in",parsedUser.getData().getEmail());
    }

    @Test
    public void getUser2(){
        //prerequisite
        RequestSpecification requestSpecification=given().header("accept",ContentType.JSON);
        //getting response
        Response response=when().get("https://reqres.in/api/users/2");
        //validation
        ValidatableResponse validatableResponse=response.then().statusCode(200).and().contentType(ContentType.JSON);
        //deserialization
        UserPojo userPojo= validatableResponse.log().all().extract().as(UserPojo.class);

        Assert.assertEquals("Janet",userPojo.getData().getFirst_name());
    }



}
