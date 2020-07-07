package API.Football;

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
import java.util.ArrayList;
import java.util.List;

public class Runner {

    @Test
    public  void getAttackerFromEngland() throws URISyntaxException, IOException {
        HttpClient client= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.football-data.org").setPath("v2/teams/66");
        HttpGet get= new HttpGet(uriBuilder.build());
        get.setHeader("Accept","application/json");
        get.setHeader("Content-Type", "application/json");
        get.setHeader("X-Auth-Token","3f4dda87d3404d7cadcfaeedf7a6c667");
        get.setHeader("limit","500");

        HttpResponse response=client.execute(get);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        EnglandTeamPojo englandTeamPojo=objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<EnglandTeamPojo>() {
                });
        System.out.println(englandTeamPojo.getId());

        // JiraPojo jiraPojo=objectMapper.readValue(httpResponse.getEntity().getContent(),
        //       new TypeReference<JiraPojo>() {
        //    });
        System.out.println(englandTeamPojo.getSquad().size());
        List<String> englandAttackers=new ArrayList<>();
        int size=englandTeamPojo.getSquad().size();

        for (int i = 0; i < size; i++) {
            if(englandTeamPojo.getSquad().get(i).getPosition()!=null){


            if(englandTeamPojo.getSquad().get(i).getPosition().equalsIgnoreCase("Attacker")) {
           if( englandTeamPojo.getSquad().get(i).getNationality().equalsIgnoreCase("England")){
                englandAttackers.add(englandTeamPojo.getSquad().get(i).getName());
            }
        }}}
        System.out.println(englandAttackers.toString());

    }
}
