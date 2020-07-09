package API.RestAssure;


import API.POJO.BreakingBad.BBCharPojo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class RestAssuredDeserialization {

    @Before
    public void setup(){
        RestAssured.baseURI="https://breakingbadapi.com";
        basePath="/api/characters";
    }


@Test
    public void deser(){


   List<BBCharPojo> charResp= given().header("accept", ContentType.JSON).when().get("/50")
           .then().statusCode(200).contentType(ContentType.JSON)
           .extract().as(new TypeRef<List<BBCharPojo>>() {});

    Assert.assertEquals("Juan Bolsa",charResp.get(0).getName());

}
    @Test
    public void deser2(){
        List<BBCharPojo> charResp= given().header("accept", ContentType.JSON).when().get("/35")
                .then().statusCode(200).and().contentType(ContentType.JSON)
                .extract().as(new TypeRef<List<BBCharPojo>>() {});

        Assert.assertEquals("Unknown",charResp.get(0).getBirthday());
        Assert.assertEquals(2,charResp.get(0).getAppearance().size());
        Assert.assertEquals("Dr. Delcavoli",charResp.get(0).getNickname());

    }
}
