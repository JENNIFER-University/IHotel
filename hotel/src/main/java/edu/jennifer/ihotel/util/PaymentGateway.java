package edu.jennifer.ihotel.util;

import edu.jennifer.ihotel.model.Payment;
import edu.jennifer.ihotel.problem.ProblemPool;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

/**
 * Created by khalid on 03/04/2017.
 */
public class PaymentGateway {

    private static final String UTF8 = "UTF-8";
    public static final int OK 						= 200;
    public static final int NOT_FOUND 				= 404;
    public static final int AUTHRIZATION_FAILED 	= 401;
    public static final int INTERNAL_ERROR		 	= 500;


    private static final String AUTHORIZE_ACTION			= "validateCard";
    private static final String MAKE_PAYMENT_ACTION			= "makePayment";
    private static final String PAYMENT_DETAILS_ACTIONS		= "payment_detail";

    private static final String JSON_KEY_ID					="id";
    private static final String JSON_KEY_RESERVATION_ID		="reservationid";
    private static final String JSON_KEY_AMMOUNT			="ammount";
    private static final String JSON_KEY_CARD_NUMBER		="cardNumber";
    private static final String JSON_KEY_CARD_HOLDER		="cardHolder";
    private static final String JSON_KEY_EXPIRE				="cardExpire";

    private static Exception lastPaymentException;
    private static Exception lastDetailException;
    private static Exception lastValidateException;

    public static Payment getPaymentDetails(String reservationId){
        CloseableHttpClient httpClient  = null;
        CloseableHttpResponse response  = null;
        try{
            String baseUrl = getIpaymentBaseUrl();
            httpClient = HttpClientBuilder.create().build();
            String callUrl = baseUrl + PAYMENT_DETAILS_ACTIONS +"?resverationId=" + URLEncoder.encode(reservationId, UTF8);
            HttpGet getRequest = new HttpGet(callUrl);
            response = httpClient.execute(getRequest);
            int responseCode = response.getStatusLine().getStatusCode();
            if(responseCode == OK){
                BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
                String output = br.readLine();
                br.close();
                JSONObject object = new JSONObject(output);
                Payment paymentDeatils = null;
                paymentDeatils = new Payment(object.getString(JSON_KEY_ID), object.getString(JSON_KEY_RESERVATION_ID), object.getDouble(JSON_KEY_AMMOUNT),
                        object.getString(JSON_KEY_CARD_NUMBER), object.getString(JSON_KEY_CARD_HOLDER), object.getString(JSON_KEY_EXPIRE), "");

                return paymentDeatils;
            }

            return null;
        }catch(Exception ex){
            PaymentGateway.lastDetailException = ex;
            return null;
        }
    }

    private static boolean validateCard(String cardNumber){

        CloseableHttpClient httpClient  = null;
        CloseableHttpResponse response  = null;
        try{
            if (ProblemPool.getInstance().makeProblem(ProblemPool.SERVICE_QUEUE)) {
                cardNumber += "-07012014";
            }
            String baseUrl = getIpaymentBaseUrl();
            httpClient = HttpClientBuilder.create().build();
            String callUrl = baseUrl + AUTHORIZE_ACTION +"?cardNumber=" + URLEncoder.encode(cardNumber, UTF8);

            HttpGet getRequest = new HttpGet(callUrl);
            response = httpClient.execute(getRequest);

            int responseCode = response.getStatusLine().getStatusCode();
            if(responseCode == OK){
                BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
                String checkResult = br.readLine();
                br.close();
                JSONObject result = new JSONObject(checkResult);
                int hasError = result.getInt("error");
                if(hasError == 1){
                    lastValidateException = new Exception(result.getString("errorMessage"));
                    return false;
                }

                return true;
            }

            return false;
        }catch(Exception ex){
            PaymentGateway.lastValidateException = ex;
            return false;
        }
    }

    public static int makePayment(Payment p ){

        if(validateCard(p.getCardNumber())){
            CloseableHttpClient httpClient  = null;
            CloseableHttpResponse response  = null;
            try{

                String query = "reservation_id="+ URLEncoder.encode(p.getReservationId() , "UTF-8");
                query += "&ammount="+URLEncoder.encode(p.getAmmountAsString(), "UTF-8");
                query += "&cardnumber="+URLEncoder.encode(p.getCardNumber() , "UTF-8");
                query += "&cardholder=" + URLEncoder.encode(p.getCardHolder() , "UTF-8");
                query += "&expire="+URLEncoder.encode(p.getExpire() , "UTF-8");
                query += "&ccv="+URLEncoder.encode(p.getCcv() , "UTF-8");


                String paymentUrl = getIpaymentBaseUrl() + MAKE_PAYMENT_ACTION + "?" + query;
                httpClient = HttpClientBuilder.create().build();
                HttpGet getRequest = new HttpGet(paymentUrl);
                response = httpClient.execute(getRequest);
                int responseCode = response.getStatusLine().getStatusCode();
                return responseCode;
            }catch(Exception ex){
                ex.printStackTrace();
                lastPaymentException = ex;
                return -1;
            }finally{
                try{
                    response.close();
                    httpClient.close();
                }catch(Exception ex){}
            }
        }
        return AUTHRIZATION_FAILED;
    }


    public static String getIpaymentBaseUrl(){
        String baseUrl = "";
        try{
            baseUrl = Conf.getInstance().getProperty(Conf.IPAYMENT_URL);
        }catch(Exception ex){
            baseUrl = Conf.DEFAULT_IPAYMENT_URL;
        }

        if(!baseUrl.endsWith("/"))
            baseUrl = baseUrl + "/";

        return baseUrl;
    }

    public static String getErrorReason(int code){
        switch (code) {
            case NOT_FOUND:
                return "Payment URL Not Found";
            case AUTHRIZATION_FAILED:
                return "Credit Card Authorization Failed";
            case INTERNAL_ERROR:
                return "Internal System Error";
            default:
                return "Unknown Error";
        }
    }

    public static String[] getConnectionStatus() {
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
                String[] result = { object.getString("ipayment") ,object.getString("icheck")};
                return result;
            }
            return null;
        }catch(Exception ex){
            return null;
        }
    }

    public static Exception getLastDetailException() {
        return lastDetailException;
    }
    public static Exception getLastPaymentException() {
        return lastPaymentException;
    }
    public static Exception getLastValidateException() {
        return lastValidateException;
    }
}
