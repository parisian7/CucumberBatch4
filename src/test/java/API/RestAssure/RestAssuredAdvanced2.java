package API.RestAssure;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class RestAssuredAdvanced2 {
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
    public void practice() {


        int numberOfGoalsByHarryKane = response.path("scorers.find{ it.player.name == 'Harry Kane'}.numberOfGoals");

        System.out.println(numberOfGoalsByHarryKane);


    }

    @Test
    public void practice2() {
        String countryOfBirth = response.path("scorers.find { it.player.name == 'Denis Cheryshev' }.player.countryOfBirth");
        Assert.assertEquals("Russia", countryOfBirth);
    }

    @Test
    public void practiceMax() {
        String playerWithMaxGoals = response.path("scorers.max { it.numberOfGoals }.player.name");
        System.out.println(playerWithMaxGoals);
    }

    @Test
    public void practiceMin(){
        String playerWithMinGoals = response.path("scorers.min {it.numberOgGoals }.player.name");
        System.out.println(playerWithMinGoals);
    }

    @Test
    public void practice3(){
        int numOfMinGoals=response.path("scorers.min {it.numberOfGoals }.numberOfGoals");
        //List<String> minScorers=response.path("scorers.findAll{ it.numberOfGoals =="+numOfMinGoals+"}.player.name");
        List<String> minScorers1=response.path(String.format("scorers.findAll{ it.numberOfGoals == %s}.player.name",numOfMinGoals));
        System.out.println(minScorers1);
    }
@Test
    public void adv5(){
        response.then().body("scorers.find { it.player.name == 'Harry Kane' }.numberOfGoals", Matchers.equalTo(6));
}
@Test
    public void adv4(){
        List<String> russianPlayers=response.path("scorers.player.findAll { it.nationality == 'Russia'}.name");
                //nationality": "Russia
    System.out.println(russianPlayers);

    response.then().assertThat().body("scorers.findAll { it.team.name == 'Russia' }",Matchers.hasSize(2));
    response.then().assertThat().body("scorers.findAll { it.team.name == 'Russia' }",Matchers.greaterThan(0));

    }
    @Test
    public void adv(){
        //using generics is using java generics Map / String / List ...
        // * is called wildcard
        Response response=given().spec(requestSpecification).when().get().then().spec(responseSpecification).extract().response();

        Map<String,?> map=response.as(Map.class);
        //map.get("status")
    }
}
