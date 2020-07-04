package API.POJO.BreakingBad;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreakingBad {

    private static final String ALIVE = "alive";
    private static final String DECEASED = "deceased";
    private static final String PRESUMED_DEAD = "presumed dead";

    @Test
    public void breakingBadapi() throws URISyntaxException, IOException, IOException, URISyntaxException {
        /*
        GET https://breakingbadapi.com/api/characters
        Deserialize response  using POJO classes
        Get Alive Deceased characters names(1 Map with only 3 keys :Alive,Deceased,Presumed dead)
         */
        HttpClient client = HttpClientBuilder.create().build();
        //Specify the URL/URI
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("breakingbadapi.com");
        uri.setPath("api/characters");
        //Specify the method type (GET/POST/PUT/DELETE)
        HttpGet get = new HttpGet(uri.build());
        //Add header parameter
        get.setHeader("Accept", "application/json");
        //Execute
        HttpResponse response = client.execute(get);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<BreakingBadCharsPojo> breakingBadPojo = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<List<BreakingBadCharsPojo>>() {
        });
        System.out.println(breakingBadPojo.get(0).getName());
        Map<String, List<String>> characterByCategory = new HashMap<>();
        List<String> alive = new ArrayList<>();
        List<String> presumeDead = new ArrayList<>();
        List<String> deceased = new ArrayList<>();
        for (int i = 0; i < breakingBadPojo.size(); i++) {
            if (breakingBadPojo.get(i).getStatus().equalsIgnoreCase(ALIVE)) {
                alive.add(breakingBadPojo.get(i).getName());
            } else if (breakingBadPojo.get(i).getStatus().equalsIgnoreCase(DECEASED)) {
                deceased.add(breakingBadPojo.get(i).getName());
            } else if (breakingBadPojo.get(i).getStatus().equalsIgnoreCase(PRESUMED_DEAD)) {
                presumeDead.add(breakingBadPojo.get(i).getName());
            } else {
                System.out.println("Unknown status :" + breakingBadPojo.get(i).getStatus());
            }
        }
        characterByCategory.put(ALIVE, alive);
        characterByCategory.put(DECEASED, deceased);
        characterByCategory.put(PRESUMED_DEAD, presumeDead);
        System.out.println("List of alive characters: " + characterByCategory.get("alive"));
        System.out.println("List of deceased characters: " + characterByCategory.get("deceased"));
        System.out.println("List of presumed dead characters: " + characterByCategory.get("presumed dead"));
    }


    @Test
    public void getCharById() throws URISyntaxException, IOException, IOException, URISyntaxException {
        HttpClient client = HttpClientBuilder.create().build();
        //Specify the URL/URI
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("breakingbadapi.com");
        uri.setPath("api/characters/12");
        //Specify the method type (GET/POST/PUT/DELETE)
        HttpGet get = new HttpGet(uri.build());
        //Add header parameter
        get.setHeader("Accept", "application/json");
        //Execute
        HttpResponse response = client.execute(get);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<BBCharPojo> bbCharPojo = objectMapper.readValue(response.getEntity().getContent(),
                objectMapper.getTypeFactory().constructCollectionType(List.class,BBCharPojo.class));

       // System.out.println(bbCharPojo.get(0).getName()+"is"+bbCharPojo.get(0).getStatus()+" portrayed: "+ bbCharPojo.get(0).getPortrayed()+", in "+bbCharPojo.get(0).getCategory());

        System.out.println(String.format("%s is %s portrayed: %s, in %s",bbCharPojo.get(0).getName(),
                bbCharPojo.get(0).getStatus(), bbCharPojo.get(0).getPortrayed(), bbCharPojo.get(0).getCategory()));
    }
}