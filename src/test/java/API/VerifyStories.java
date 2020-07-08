package API;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;

public class VerifyStories {

    @Test
    public void verifyNumberOfStories()throws  Exception{
        // execute a GET request to:

        HttpClient client= HttpClientBuilder.create().build();

        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/findByStatus");
        uriBuilder.setCustomQuery("status=sold");

        HttpGet httpGet=new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept","application/json");

        HttpResponse response=client.execute(httpGet);
    }

}
