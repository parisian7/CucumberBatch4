package API.Serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class SerializationIntro {

    @Test
    public void serializ1() throws IOException {

        Pet pet =new Pet("Hatiko", "waiting",3);

        pet.setId(1);

        pet.setPhotoUrl("https://s3.petpics.amazon.com");

        ObjectMapper objectMapper=new ObjectMapper();

        objectMapper.writeValue(new File("target/pet.json"),pet);

    }
    @Test
    public void createPet()throws URISyntaxException,IOException{
         HttpClient client= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet");
        HttpPost post=new HttpPost(uriBuilder.build());

        post.setHeader("Content-Type", "application/json");
        post.setHeader("accept","application/json");

        String petPayload=new String (Files.readAllBytes(Paths.get("target/pet.json")));

        HttpEntity entity= new StringEntity(petPayload);

        post.setEntity(entity);
        HttpResponse response= (HttpResponse) client.execute(post);
        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
    }
}
