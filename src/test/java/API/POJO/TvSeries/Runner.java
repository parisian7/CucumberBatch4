package API.POJO.TvSeries;

import API.POJO.BreakingBad.BBCharPojo;
import API.POJO.BreakingBad.BreakingBadCharsPojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Runner {

    @Test
    public void houseGender()throws Exception{

        /*
        GET https://api.got.show/api/map/characters
        Deserialize response  using POJO classes
        Get Alive Deceased characters names(1 Map with only 3 keys :Alive,Deceased,Presumed dead)
         */
        HttpClient client = HttpClientBuilder.create().build();
        //Specify the URL/URI
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("api.got.show");
        uri.setPath("api/map/characters");
        //Specify the method type (GET/POST/PUT/DELETE)
        HttpGet get = new HttpGet(uri.build());
        //Add header parameter
        get.setHeader("Accept", "application/json");
        //Execute
        HttpResponse response = client.execute(get);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CharactersPojo charactersPojo = objectMapper.readValue(response.getEntity().getContent(),  CharactersPojo.class);

        System.out.println(charactersPojo.getData().get(0).get_id());

//        Set<String>maleHouses=new HashSet<>();
//        Set<String>femaleHouses=new HashSet<>();
//
//        for (int i = 0; i < charactersPojo.getData().size(); i++) {
//            if(charactersPojo.getData().get(i).getMale()==true){
//                maleHouses.add(charactersPojo.getData().get(i).getHouse());
//            }else if(charactersPojo.getData().get(i).getMale()==false){
//                femaleHouses.add(charactersPojo.getData().get(i).getHouse());
//
//            }
//        }
//        System.out.println("Male houses are ; "+maleHouses.toString());
//        System.out.println("Female houses are; "+ femaleHouses.toString());
        Map<String,String> charsInBook=new HashMap<>();
        for (int i = 0; i < charactersPojo.getData().size(); i++) {
            for (int j = 0; j < charactersPojo.getData().get(i).getBooks().size(); j++) {
                if (!charsInBook.containsKey(charactersPojo.getData().get(i).getBooks().get(j))) {
                int count=1;
                String chars=charactersPojo.getData().get(i).getName()+"=>"+count;
                charsInBook.put(charactersPojo.getData().get(i).getBooks().get(j),chars);

                }else if (charsInBook.containsKey(charactersPojo.getData().get(i).getBooks().get(j))){
                    int count=Integer.parseInt(charsInBook.get(charactersPojo.getData().get(i).getBooks().get(j)).substring(charsInBook.get(charactersPojo.getData().get(i).getBooks().get(j)).indexOf('>')+1))+1;
                    String totalChars=charsInBook.get(charactersPojo.getData().get(i).getBooks().get(j)).substring(0,charsInBook.get(charactersPojo.getData().get(i).getBooks().get(j)).indexOf('='));
                    totalChars+=", "+charactersPojo.getData().get(i).getName()+"=>"+count;
                    charsInBook.replace(charactersPojo.getData().get(i).getBooks().get(j),totalChars);
                }
            }
        }

        Set<String> keys = charsInBook.keySet();
//print all the keys
        for (String key : keys) {
            System.out.println(key +"= "+charsInBook.get(key));
            System.out.println();
        }

    }
    @Test
    public void bbDesById() throws URISyntaxException, IOException {
        Random random=new Random();
        int a=random.nextInt(56)+1;
        HttpClient client= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        //https://breakingbadapi.com/api/characters/57
        uriBuilder.setScheme("https").setHost("breakingbadapi.com").setPath("api/characters/"+a);
        HttpGet get=new HttpGet(uriBuilder.build());
        get.setHeader("Accept","application/json");
        HttpResponse response = client.execute(get);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        List<BBCharPojo> bbCharPojo = objectMapper.readValue(response.getEntity().getContent(),
//                objectMapper.getTypeFactory().constructCollectionType(List.class,BBCharPojo.class));
        List<BBCharPojo>bbCharPojos=objectMapper.readValue(response.getEntity().getContent(),
              objectMapper.getTypeFactory().constructCollectionType(List.class, BBCharPojo.class)  )    ;

        System.out.println(bbCharPojos.get(0).getChar_id());



    }
}
