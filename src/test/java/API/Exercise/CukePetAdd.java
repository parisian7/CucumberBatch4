package API.Exercise;


import Utils.PayloadUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class CukePetAdd {

    HttpResponse response;
    HttpClient client;
    @When("user send a POST request to create a pet {int} , {string}, {string}")
    public void user_send_a_POST_request_to_create_a_pet(Integer id, String name, String status) throws IOException, URISyntaxException {
         client= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet");

        HttpPost post=new HttpPost(uriBuilder.build());

        post.setHeader("Content-Type","application/json");
        post.setHeader("Content", "application/json");

        HttpEntity entity=new StringEntity(PayloadUtils.getPetPayload(id,name,status));

        post.setEntity(entity);
         response= client.execute(post);
    }

    @Then("the status code is twoHundred")
    public void the_status_code_is_twoHundred() {
        System.out.println(response.getStatusLine().getStatusCode());
        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
        Assert.assertEquals("application/json",response.getEntity().getContentType().getValue());
    }

   @Then("pet with id,name status is created {int} , {string}, {string}")
public void pet_with_id_name_status_is_created(Integer id, String name, String status) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });
        Assert.assertEquals(id, map.get("id"));
        Assert.assertEquals(name,map.get("name"));
        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
        Assert.assertEquals(status,map.get("status"));
    }





}
