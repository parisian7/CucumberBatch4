package API.POJO.StarWars;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class Deserialization {



    @Test
    public void getStarWars() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("swapi.dev");
        uri.setPath("api/starships");
        HttpGet get = new HttpGet(uri.build());
        get.setHeader("Accept", "application/json");
        HttpResponse response = client.execute(get);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,true);
        StarWarsPojo starWarsPojo =objectMapper.readValue(response.getEntity().getContent(),StarWarsPojo.class);
        System.out.println(starWarsPojo.getResults().get(0).getName());
        System.out.println(starWarsPojo.getResults().get(0).getManufacturer());
    }
}
