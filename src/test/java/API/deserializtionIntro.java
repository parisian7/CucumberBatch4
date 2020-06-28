package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class deserializtionIntro {

    @Test
    public void deserialization1() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/1");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        System.out.println("The id from response body is:" + deserializedResponse.get("id"));
        System.out.println(deserializedResponse.get("category"));
        System.out.println(deserializedResponse.get("name"));
        System.out.println(deserializedResponse.get("tags"));
        System.out.println(deserializedResponse.get("status"));

        Map<String, Object> category = (Map<String, Object>) deserializedResponse.get("category");
        System.out.println("category id is:" + category.get("id"));
        System.out.println("category name is: " + category.get("name"));

//        Map<String,Object[]> tags= (Map<String, Object[]>) deserializedResponse.get("tags");
//        System.out.println(tags.get(0));
    }

    @Test
    public void deserialization2() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users/2");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializedResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        Map<String, Object> ad = (Map<String, Object>) deserializedResponse.get("ad");

        System.out.println(ad.get("company"));
        System.out.println(ad.get("url"));
        System.out.println(ad.get("text"));
    }

    @Test
    public void deserialization3() throws URISyntaxException, IOException {
        //http://cat-fact.herokuapp.com/facts

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("cat-fact.herokuapp.com");
        uriBuilder.setPath("facts");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,List<Map<String,Object>>> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference <Map<String,List<Map<String,Object>>>> () {
                });

        System.out.println(parseResponse.get("all").get(0).get("text"));

        Map<String, List<Map<String, Object>>> all=  parseResponse;
        Map<String,Object> userInfo = (Map<String, Object>) all.get("all").get(0).get("user");
        System.out.println(userInfo.get("name"));
        //System.out.println(all.get("all").get(0).get("user").get("name").get("first"));
       // System.out.println("The id from response body is:" + deserializedResponse.get("all"));

//        List<Object>all= (List<Object>) deserializedResponse.get("all");
//        System.out.println(all.get(5));
//        System.out.println(all.size());
//
//        int count=0;
//        for (Object one:all
//             ) {
//            Map<String, Object> oneMap= (Map<String, Object>) one;
//            String text=String.valueOf(oneMap.get("text"));
//
//            if (text.contains("cat") || text.contains("Cat")){
//                count++;
//            }
//           // System.out.println(text);
//        }
//        System.out.println(all.size()-count);


    }
    @Test
    public void deserialization4() throws URISyntaxException, IOException {
        //https://reqres.in/api/users/

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users/");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,Object> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference <Map<String,Object>> () {
                });

        Map<String,Object> map= parseResponse;

        String [] keys ={"page","per_page","total","total_pages"};
      for (int i=0; i<3;i++){

          System.out.println(map.get(keys[i]));
      }

      List<Map<String,String>> data= (List<Map<String, String>>) map.get("data");
        for (int i=0; i<data.size();i++){
            System.out.println(data.get(i).get("first_name"));

        }

}}