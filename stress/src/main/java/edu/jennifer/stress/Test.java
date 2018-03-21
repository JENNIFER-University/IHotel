package edu.jennifer.stress;

import edu.jennifer.stress.model.Account;
import edu.jennifer.stress.model.RequestParams;
import edu.jennifer.stress.util.AppUtil;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.*;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Khalid
 * @Created 3/21/18 1:09 PM.
 */
public class Test {

    private String cookies;
    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) {

        try{
            RequestParams requestParams = new RequestParams();
            HttpClientContext context = HttpClientContext.create();
            CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
            HttpPost httpPost = new HttpPost("http://localhost:8080/ihotel/booking/doBook");

             httpPost.setEntity(new UrlEncodedFormEntity(requestParams.makePostRequestParams(false)));

            CloseableHttpResponse response = httpClient.execute(httpPost, context);

            List<URI> redirectURIs = context.getRedirectLocations();
            if (redirectURIs != null && !redirectURIs.isEmpty()) {
                for (URI redirectURI : redirectURIs) {
                    System.out.println("Redirect URI: " + redirectURI);
                }
                URI finalURI = redirectURIs.get(redirectURIs.size() - 1);
            }

//
//            Header[] a = response.getHeaders("Location");
//            for (Header h : a) {
//                System.out.println(h.getValue());
//            }


        }catch (Exception ex) {
            ex.printStackTrace();
        }


//        System.out.println(AppUtil.getDateFormatted(0));
//        System.out.println(AppUtil.getDateFormatted(25));
//        String login = "http://localhost:8080/ihotel/doLogin?username=khalid&password=admin";
//        String list = "http://localhost:8080/ihotel/rooms/list";
//
//
//        try{
//
//            new Test().play(login, list);
//
//
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }

    }

    public void play(String login, String list) throws Exception{

//        CookieHandler.setDefault(new CookieManager());

//        request.setHeader("Account-Agent", USER_AGENT);
//        request.setHeader("Accept",
//                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//        request.setHeader("Accept-Language", "en-US,en;q=0.5");
//

        HttpClient client = HttpClientBuilder.create().build();
//        HttpResponse response = client.execute(request);
//        int responseCode = response.getStatusLine().getStatusCode();
//        System.out.println(responseCode);
//        BufferedReader rd = new BufferedReader(
//                new InputStreamReader(response.getEntity().getContent()));
//
//        StringBuffer result = new StringBuffer();
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }
//
//        // set cookies
//        setCookies(response.getFirstHeader("Set-Cookie") == null ? "" :
//                response.getFirstHeader("Set-Cookie").toString());
//
//        System.out.println(result.toString());
//
//        System.out.println("----------------------------");
//        Thread.sleep(1000);
//
//        HttpGet tany = new HttpGet(list);
//        tany.setHeader("Account-Agent", USER_AGENT);
//        tany.setHeader("Accept",
//                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//        tany.setHeader("Accept-Language", "en-US,en;q=0.5");
//        tany.setHeader("HTTP_CLIENT_IP","192.158.10.123");
//        tany.setHeader("REMOTE_ADDR","192.158.10.123");
//        HttpResponse tanyResponse = client.execute(tany);
//        int responseCode2 = tanyResponse.getStatusLine().getStatusCode();
//        System.out.println("Response Code : " + responseCode2);
//
//        BufferedReader rd2 = new BufferedReader(
//                new InputStreamReader(tanyResponse.getEntity().getContent()));
//
//        StringBuffer result2 = new StringBuffer();
//        String line2 = "";
//        while ((line2 = rd2.readLine()) != null) {
//            result2.append(line2);
//        }
//
//         System.out.println(result2.toString());
//
//
//        System.out.println("----------------------------");
//        Thread.sleep(1000);
//
//        HttpGet logout = new HttpGet("http://localhost:8080/ihotel/logout");
//        HttpResponse logoutResponse = client.execute(logout);
//
//        System.out.println("----------------------------");
//        Thread.sleep(1000);
//
//        HttpGet test = new HttpGet(list);
//        test.setHeader("Account-Agent", USER_AGENT);
//        test.setHeader("Accept",
//                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//        test.setHeader("Accept-Language", "en-US,en;q=0.5");
//        test.setHeader("HTTP_CLIENT_IP","192.158.10.123");
//        test.setHeader("REMOTE_ADDR","192.158.10.123");
//        HttpResponse testReponse = client.execute(test);
//        int testResponseCode = testReponse.getStatusLine().getStatusCode();
//        System.out.println("Response Code : " + testResponseCode);


        getRequest(login,client );
        System.out.println("----------------------------");
        Thread.sleep(1000);

        getRequest(list,client );
        System.out.println("----------------------------");
        Thread.sleep(1000);

        getRequest("http://localhost:8080/ihotel/logout",client );
        System.out.println("----------------------------");
        Thread.sleep(1000);

        getRequest(list,client );
        System.out.println("----------------------------");
        Thread.sleep(1000);

    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getRequest(String url, HttpClient client) throws Exception {
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println(responseCode);
        request.releaseConnection();
//        BufferedReader rd = new BufferedReader(
//                new InputStreamReader(response.getEntity().getContent()));
//
//        StringBuffer result = new StringBuffer();
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }
//
//        return result.toString();
        return "";
    }
}
