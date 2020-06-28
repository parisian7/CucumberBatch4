package API.Exercise;


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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TVSeriesCharacters {

    @Test
    public void deserialization1() throws Exception {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        //https://api.got.show/api/map/characters/byId/5cc0743504e71a0010b852d9
        uriBuilder.setScheme("https").setHost("api.got.show").setPath("api/map/characters/byId/5cc0743504e71a0010b852d9");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        Map<String, Object> map = parseResponse;

        Map<String, Map<String, Object>> insideData = (Map<String, Map<String, Object>>) map.get("data");
        System.out.println(insideData.get("titles"));

        List<String> books = (List<String>) insideData.get("books");
        for (String book :
                books) {
            System.out.println(book);
        }
        List<String> keys = new ArrayList<String>(insideData.keySet());
        System.out.println(keys.toString());

        for (int i = 0; i < keys.size(); i++) {
            System.out.println(insideData.get(keys.get(i)));
        }

    }

    @Test
    public void deserialization2() throws Exception {
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.got.show").setPath("api/map/characters/");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parseMap = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        List<Map<String, Object>> dataInside = (List<Map<String, Object>>) parseMap.get("data");
        System.out.println(dataInside.size());
        System.out.println("===============");
        int count = 0;
        for (int i = 0; i < dataInside.size(); i++) {
            if (dataInside.get(i).get("house") != null) {
                System.out.println(dataInside.get(i).get("house"));
                count++;
            }
        }
        System.out.println(count);

        System.out.println("*********************************");
        List<Map<String, List<String>>> books = (List<Map<String, List<String>>>) parseMap.get("data");
        Map<String, Integer> bookList = new HashMap<>();
        System.out.println(books.size());
        System.out.println("what is this" + books.get(0).get("male"));
        int sum = 0;
        for (int i = 0; i < books.size(); i++) {
            books.get(0).get("_id");
            for (int j = 0; j < books.get(i).get("books").size(); j++) {
                if (bookList.containsKey(books.get(i).get("books").get(j))) {
                    int cnt = bookList.get(books.get(i).get("books").get(j));
                    ++cnt;
                    bookList.replace(books.get(i).get("books").get(j), cnt);
                    continue;
                }
                bookList.put(books.get(i).get("books").get(j), 1);
            }
        }
        System.out.println("////////////////////////////////////////");
        List<String> keys = new ArrayList<String>(bookList.keySet());
        System.out.println(keys.toString());
        for (int i = 0; i < keys.size(); i++) {
            System.out.println("key=> " + keys.get(i) + " number of => " + bookList.get(keys.get(i)));
        }
    }

    @Test
    public void deserialization3() throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        //https://reqres.in/api/users
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("reqres.in").setPath("api/users");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = client.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parseMap = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        List<Map<String, Integer>> data = (List<Map<String, Integer>>) parseMap.get("data");

        Integer sum = 0;
        for (int i = 0; i < data.size(); i++) {
            sum += data.get(i).get("id");

        }
        System.out.println(sum / data.size() + 1);
    }

    @Test
    public void test4() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        //https://itunes.apple.com/search?term=linkinpark
        uriBuilder.setScheme("https").setHost("itunes.apple.com").setPath("search").setCustomQuery("term=linkinpark");
        HttpGet get = new HttpGet(uriBuilder.build());
        HttpResponse response = client.execute(get);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsObject = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });
        List<Map<String, Object>> songs = (List<Map<String, Object>>) jsObject.get("results");
        Assert.assertEquals(songs.size(), jsObject.get("resultCount"));
        System.out.println(songs.size());
        int cnt=0;
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).get("artistName").toString().contains("LINKIN PARK")){
            cnt++;
            }
        }
    }
}