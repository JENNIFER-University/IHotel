package edu.jennifer.stress.simula;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

/**
 * @author Khalid
 * @Created 3/21/18 2:08 PM.
 */
public class Browser {

    private CloseableHttpClient httpClient = null;

    public Browser() {
        httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }


    public void doGet(String url){
        HttpGet request = null;
        CloseableHttpResponse response = null;
        try{
            request = new HttpGet(url);
            response = httpClient.execute(request);
            response.getStatusLine().getStatusCode();
            readResponse(response);
        }catch (IOException io){
            System.out.printf("Failed to goGet the url %s. Reason is %s%n", url, io.getMessage());
        }finally {
            closeResources(request, response);
        }

    }

    public String doPost(String url, List<NameValuePair> params) {
        HttpPost request = null;
        HttpClientContext context = null;
        CloseableHttpResponse response = null;
        try {
            context = HttpClientContext.create();
            request = new HttpPost(url);
            request.setEntity(new UrlEncodedFormEntity(params));
            response = httpClient.execute(request, context);
            response.getStatusLine().getStatusCode();
            readResponse(response);
            List<URI> redirectURIs = context.getRedirectLocations();
            if (redirectURIs != null && !redirectURIs.isEmpty()) {
                return redirectURIs.get(redirectURIs.size() - 1).toString();
            }

        }catch (IOException io){
            System.out.printf("Failed to doPost for the url %s. Reason is %s%n", url, io.getMessage());
        }finally {
           closeResources(request, response);
        }

        return null;
    }

    private void closeResources(HttpRequestBase request, CloseableHttpResponse response) {
        try {
            if (response != null)
                response.close();
            if (request != null)
                request.releaseConnection();
        }catch (Exception ex){

        }
    }

    private void readResponse(CloseableHttpResponse response) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = reader.readLine()) != null) {}
    }
}
