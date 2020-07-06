package API.JIRA;


import Utils.PayloadUtils;
import com.fasterxml.jackson.core.type.TypeReference;
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

import static Utils.PayloadUtils.getPetPayload;


public class Jira_Auth {

    public static String aut;
    @Test
    public void addIssues1() throws Exception{

        HttpClient httpClient=HttpClientBuilder.create().build();
        //http://localhost:8080/rest/auth/1/session
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("localhost").setPort(8080);
        uriBuilder.setPath("rest/auth/1/session");


        HttpPost httpPost=new HttpPost(uriBuilder.build());

        httpPost.setHeader("Content-Type","application/json");
        httpPost.setHeader("Accept","application/json");

        HttpEntity httpEntity=new StringEntity("{\"username\":\"MuhammedA\",\n" +
                "\"password\":\"MQ5141ma\"}");

        httpPost.setEntity(httpEntity);

        HttpResponse httpResponse=httpClient.execute(httpPost);

        ObjectMapper objectMapper=new ObjectMapper();

        JiraPojo jiraPojo=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<JiraPojo>() {
                });

         aut=String.format("%s=%s",jiraPojo.getSession().getName(),jiraPojo.getSession().getValue());
        System.out.println(aut);

    }
    @Test
    public void addIssues2()throws Exception{

        System.out.println(aut);
        HttpClient httpClient=HttpClientBuilder.create().build();
        //http://localhost:8080/rest/api/2/issue
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("localhost").setPort(8080);
        uriBuilder.setPath("rest/api/2/issue");

        String auto= PayloadUtils.cookieAuthPayload();
        HttpPost httpPost=new HttpPost(uriBuilder.build());

        httpPost.setHeader("Content-Type","application/json");
        httpPost.setHeader("Accept","application/json");
        httpPost.setHeader("Cookie",auto);

        HttpEntity httpEntity=new StringEntity(PayloadUtils.getJiraIssuePayload("Http response error","gives Unexpected numbers",
                "Bug"));

        /*
        {
    "fields": {
       "project":
       {
          "key": "TECH"
       },
       "summary": "REST ye merry beautiful.",
       "description": "Creating of an issue using project keys and issue type names using the REST API",
       "issuetype": {
          "name": "Bug"
       }
   }
}
         */

        httpPost.setEntity(httpEntity);

        HttpResponse httpResponse=httpClient.execute(httpPost);

        System.out.println(httpResponse.getStatusLine().getStatusCode());


    }
}
