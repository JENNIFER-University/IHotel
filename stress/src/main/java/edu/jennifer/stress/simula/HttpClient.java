package edu.jennifer.stress.simula;

import edu.jennifer.common.Logger;
import edu.jennifer.stress.factory.BrowserTypeFactory;
import edu.jennifer.stress.model.BrowserType;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Khalid
 * @Created 3/21/18 2:08 PM.
 */
public class HttpClient {

    private CloseableHttpClient httpClient = null;
    private HttpClientContext httpContext;
    private BrowserType browserType;
    private String location;

    private final int TIMEOUT = (int) TimeUnit.SECONDS.toMillis(30);

    public HttpClient(String location) {
        this.location = location;
        this.browserType = BrowserTypeFactory.createBrowserType();
        this.location = location;
        this.httpClient = configure().build();
        this.httpContext = HttpClientContext.create();
        this.httpContext.setAttribute(HttpClientContext.COOKIE_STORE, new BasicCookieStore());
    }


    public void doGet(String url){
        HttpGet request = null;
        CloseableHttpResponse response = null;
        try{
            request = new HttpGet(url);
            response = httpClient.execute(request, this.httpContext);
            response.getStatusLine().getStatusCode();
            readResponse(response);
        }catch (IOException io){
            Logger.error(String.format("Failed to execute GET request for the URL: %s", url), io);;
        }finally {
            closeResources(request, response);
        }

    }

    public String doPost(String url, List<NameValuePair> params) {
        HttpPost request = null;
        CloseableHttpResponse response = null;
        try {
            request = new HttpPost(url);
            request.setEntity(new UrlEncodedFormEntity(params));
            response = httpClient.execute(request, this.httpContext);
            response.getStatusLine().getStatusCode();
            readResponse(response);
            List<URI> redirectURIs = this.httpContext.getRedirectLocations();
            if (redirectURIs != null && !redirectURIs.isEmpty()) {
                String result =  redirectURIs.get(redirectURIs.size() - 1).toString();
                return result;
            }

        }catch (IOException io){
            Logger.error(String.format("Failed to execute POST request for the URL: %s", url), io);
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

    private HttpClientBuilder configure() {
        HttpClientBuilder httpClientBuilder = null;
        try{
            httpClientBuilder = HttpClientBuilder.create();
            RequestConfig.Builder requestBuilder = RequestConfig.custom();
            requestBuilder = requestBuilder.setConnectTimeout(TIMEOUT)
                    .setConnectionRequestTimeout(TIMEOUT)
                    .setSocketTimeout(TIMEOUT);

            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setDefaultMaxPerRoute(100);
            connectionManager.setMaxTotal(200);
            httpClientBuilder.setConnectionManager(connectionManager);
            httpClientBuilder.setDefaultRequestConfig(requestBuilder.build());
            httpClientBuilder.setRedirectStrategy(new LaxRedirectStrategy());

            List<Header> headers = new ArrayList<>();
            headers.add(new BasicHeader(HttpHeaders.USER_AGENT, this.browserType.getUserAgent()));
            headers.add(new BasicHeader("x-forwarded-for", this.location));

            httpClientBuilder.setDefaultHeaders(headers);


        }catch (Exception ex) { }

        return httpClientBuilder;
    }
}
