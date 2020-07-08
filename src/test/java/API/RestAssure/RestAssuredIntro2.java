package API.RestAssure;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
public class RestAssuredIntro2 {
    @Test
    public void rest1(){
        given().header("contentType", ContentType.JSON).header("accept", ContentType.JSON);
        when().get("https://swapi.dev/api/planets/1").then().statusCode(200).and().contentType(ContentType.JSON);
    }
    @Test
    public void rest2(){
        given().header("accept", ContentType.JSON).when().get("https://swapi.dev/api/planets/1").then().statusCode(200)
                .and().contentType(ContentType.JSON).and().assertThat().body("name",equalTo("Tatooine"));
    }
    @Test
    public void rest3(){
        given().header("accept", ContentType.JSON).when().get("https://swapi.dev/api/planets/1").then().statusCode(200)
                .and().contentType(ContentType.JSON).and().assertThat().body("population", equalTo("200000"));
    }
    @Test
    public void rest4(){
        given().header("accept", ContentType.JSON).when().get("https://swapi.dev/api/planets").then().log().all().statusCode(200)
                .and().log().ifStatusCodeIsEqualTo(400).contentType(ContentType.JSON).and().assertThat().body("results[0].gravity", Matchers.is("1 standard"))
                .body("results[0].terrain", Matchers.isA(String.class));
    }
}
