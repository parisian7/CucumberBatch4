package API;

import API.POJO.PetPojo;
import API.POJO.ReqresPojo;
import API.POJO.UserPojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class PetReq {

    @Test
            public void asd() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet/12");

        HttpGet httpGet=new HttpGet(uriBuilder.build());

        HttpResponse response=client.execute(httpGet);

        ObjectMapper objectMapper=new ObjectMapper();

        PetPojo deserilizedObject=objectMapper.readValue(response.getEntity().getContent(), PetPojo.class);
        System.out.println(deserilizedObject.getId());
        System.out.println(deserilizedObject.getName());
    }
    @Test
    public void asd2() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("reqres.in").setPath("api/users/7");

        HttpGet httpGet=new HttpGet(uriBuilder.build());

        HttpResponse response=client.execute(httpGet);

        ObjectMapper objectMapper=new ObjectMapper();

        UserPojo deserilizedObject=objectMapper.readValue(response.getEntity().getContent(), UserPojo.class);
        System.out.println(deserilizedObject.getData().getFirst_name());
        System.out.println(deserilizedObject.getAd().getText());
    }
}
