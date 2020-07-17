package API.RestAssure;
import API.Exercise.Football.FootBallPojo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
public class RestAssuredAdvanced {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response ;
    @Before
    public void setup(){
        RestAssured.baseURI="http://api.football-data.org";
        RestAssured.basePath="v2/competitions";
        requestSpecification=new RequestSpecBuilder().setAccept(ContentType.JSON).build();
        responseSpecification=new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
        //http://api.football-data.org/v2/competitions/
        response=given().spec(requestSpecification).when().get().then().spec(responseSpecification).extract().response();
    }
    @Test
    public void adv1(){
        FootBallPojo competitionsPojoParsed = response.getBody().as(FootBallPojo.class);
        System.out.println(competitionsPojoParsed.getCompetitions().get(0).get("name"));
        // print out the competition name with id higher than 2100
//      for(int i=0 ; i<competitionsPojoParsed.getCompetitions().size() ; i++){
//          if ((int)competitionsPojoParsed.getCompetitions().get(i).get("id")>2000){
//              System.out.println(competitionsPojoParsed.getCompetitions().get(i).get("name"));
//          }
//      }
        List<Map<String , Object>> competitionList = competitionsPojoParsed.getCompetitions();
        for(Map<String , Object> map :competitionList){
            int id = (int) map.get("id");
            if(id >= 2100){
                System.out.println(map.get("name"));
            }
        }
    }
    @Test
    public void adv2(){
        JsonPath jsonPath = response.jsonPath();
        List<Map<String , Object>> competitionList = jsonPath.getList("competitions");
        for(Map<String , Object> map :competitionList){
            int id = (int) map.get("id");
            if(id >= 2100){
                System.out.println(map.get("name"));
            }
        }
    }
    @Test
    public void adv3(){
        List<String> competitionList= response.path("competitions.findAll { it.id > 2100 }.name");
        System.out.println(competitionList);
        // RestAssured support groovy (Language) specification
    }
    @Test
    public void adv4(){
        List<String> listOfNames= response.path("competitions.findAll { it.area.name == 'Mexico' }.name");
        System.out.println(listOfNames);
        // RestAssured support groovy (Language) specification
    }
}
