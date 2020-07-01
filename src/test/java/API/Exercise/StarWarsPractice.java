package API.Exercise;

import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StarWarsPractice {

    @Test
    public void task() throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("swapi.dev");
        uriBuilder.setPath("api/planets");
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response=httpClient.execute(httpGet);
        if(response.getStatusLine().getStatusCode()!=200){
            Assert.fail();
        }
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> map=objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {});
        List<Map<String,Object>> results=(List<Map<String,Object>>)map.get("results");
        Map<String,String>  planetPopulation=new HashMap<>();
        for (int i=0; i<results.size();i++){
            String planet=results.get(i).get("name").toString() ;
            String population=results.get(i).get("population").toString();
            planetPopulation.put(planet,population);
        }
        System.out.println(planetPopulation);
    }
}

