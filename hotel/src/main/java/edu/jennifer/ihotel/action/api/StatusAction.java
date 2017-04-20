package edu.jennifer.ihotel.action.api;

import edu.jennifer.ihotel.action.BaseAction;
import edu.jennifer.ihotel.util.PaymentGateway;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by khalid on 07/04/2017.
 */
public class StatusAction extends BaseAction {

    private String hotel;
    private String payment;
    private String check;

    @Override
    public String execute() throws Exception {
        setHotel("alive");
        checkIPaymentAndICheck();
        return  SUCCESS;

    }


    private void checkIPaymentAndICheck(){
        CloseableHttpClient httpClient;
        CloseableHttpResponse response;
        try{
            String baseUrl = PaymentGateway.getIpaymentBaseUrl();
            httpClient = HttpClientBuilder.create().build();
            String callUrl = baseUrl + "status";

            HttpGet getRequest = new HttpGet(callUrl);
            response = httpClient.execute(getRequest);
            int responseCode = response.getStatusLine().getStatusCode();
            if(responseCode == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
                String output = br.readLine();
                br.close();
                JSONObject object = new JSONObject(output);
                setPayment(object.getString("ipayment"));
                setCheck(object.getString("icheck"));
            }
        }catch(Exception ex){
            System.out.println("ERROR " + ex.getMessage());
        }
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getHotel() {
        return hotel;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPayment() {
        return payment;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCheck() {
        return check;
    }
}
