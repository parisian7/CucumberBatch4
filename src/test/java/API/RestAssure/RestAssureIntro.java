package API.RestAssure;

import Utils.PayloadUtils;
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

public class RestAssureIntro {

//    @Test
//    public void Test2() throws URISyntaxException, IOException {
//        HttpClient client = HttpClientBuilder.create().build();
//        URIBuilder uriBuilder = new URIBuilder();
//        //http://localhost:8080/rest/api/2/search?jql=assignee=abdulkadir&maxResults=5
//        uriBuilder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/api/2/search").setCustomQuery("jql=assignee=abdulkadir&maxResults=5");
//        HttpGet httpGet = new HttpGet(uriBuilder.build());
//        httpGet.setHeader("Accept" , "application/json");
//        httpGet.setHeader("Cookie", PayloadUtils.getJSessionCookie());
//        HttpResponse response =client.execute(httpGet);
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode pardesResp=  objectMapper.readTree(response.getEntity().getContent());
//        System.out.println(pardesResp.get("issues"));
//        JsonNode issues = pardesResp.get("issues");
//        System.out.println(issues.get(0).get("self").asText());
//    }
}
