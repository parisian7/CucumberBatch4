package PracticeOfEverything;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
public class GOTPractice {

    @Test
    public void jsonNode() throws URISyntaxException, IOException {

        HttpClient client=HttpClientBuilder.create().build();
//https://api.got.show/api/map/characters/
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.got.show").setPath("api/map/characters/");

        HttpGet get=new HttpGet(uriBuilder.build());

        get.setHeader("accept","application/json");

        HttpResponse response= client.execute(get);

        ObjectMapper objectMapper=new ObjectMapper();

        JsonNode parsResp= objectMapper.readTree(response.getEntity().getContent());
        String message= parsResp.get("message").toString();
        System.out.println(message);

        String book=parsResp.get("data").get(0).get("books").get(0).toString();

        System.out.println(book);
    }


}
