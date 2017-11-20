package edu.jennifer.stress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.jennifer.stress.model.GuestInfo;
import edu.jennifer.stress.model.PaymentInfo;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;


public class VirtualUser extends Thread{
	
	private final int CALLS_PER_USER 	= 6;
	private boolean debug 		   		= false;
	private String IHOTEL_URL	   		= null;
		
	 
	private String id;
	private String name;
	private HashSet<String> activeUsers;


	public VirtualUser(HashSet<String> activeUsers, String id, String appUrl,boolean debug){
		this.id = id;
		this.activeUsers = activeUsers;
		IHOTEL_URL = appUrl;
		this.debug = debug;
	}
	
	@Override
	public void run() {
		synchronized (activeUsers) {
			activeUsers.add(id);
		}
		
		CloseableHttpClient client = null;
		try{
			debug("Generating random params for this user .... ");
			BookingParams params = generateBookingParams();
			debug("This user name is ["+params.getGuestInfo().getTitle() + " " + params.getGuestInfo().getFirstName() + " " + params.getGuestInfo().getSureName()+"],  will book Room Number[" 
					+ params.getRoomId()+"] For [" + params.getDays()+"] days and guests number are [" + params.getGuestsNumber()+"]. He will check in on [" 
					+ params.getCheckIn()+"] and check out on [" + params.getCheckOut()+"]"
					+ ". with credid card # " + params.getPaymentInfo().getCardNumber() + " which will expire on " + params.getPaymentInfo().getExpire());
			
			this.name = params.getGuestInfo().getTitle() + " " + params.getGuestInfo().getFirstName() + " " + params.getGuestInfo().getSureName();
			
			newLine();
			think(1000, 3000);
			
			client = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();

			debug("Start Browsing. Shuffling calls order ... ");
			List<Integer> lstIndexes = new ArrayList<Integer>();
			for(int i = 1; i <= CALLS_PER_USER; i++)
				lstIndexes.add(i);
			
			int callLoopCounter = 0;
			while(callLoopCounter < CALLS_PER_USER){
				Collections.shuffle(lstIndexes);
				for(Integer index : lstIndexes){
					switch (index) {
					case 1:
						openHomePage(client);
						think(3000, 4000);
						break;
					case 2:
						checkHotelIntroduction(client);
						think(3000, 4000);
						break;
					case 3:
						listRooms(client);
						think(3000, 4000);
						break;
					case 4:
						selectRoom(client, params);
						think(3000, 4000);
						break;
					case 5:
						startBooking(client,params);
						think(3000, 4000);
						break;
					case 6:
						contactHotel(client);
						think(3000, 4000);
						break;
					default:
						System.out.println("ERRS");
						break;
					}
				}
				callLoopCounter ++;
			}
			
			debug("Finished Browsing");

			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			synchronized (activeUsers) {
				activeUsers.remove(id);
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	private void openHomePage(CloseableHttpClient httpClient) throws IOException{
		HttpGet getRequest = new HttpGet(IHOTEL_URL + "/ihotel/welcome");
		CloseableHttpResponse response = httpClient.execute(getRequest);
		response.getStatusLine().getStatusCode();
		response.close();
		getRequest.releaseConnection();
	}
	
	private void checkHotelIntroduction(CloseableHttpClient httpClient) throws IOException{
		HttpGet getRequest = new HttpGet(IHOTEL_URL + "/ihotel/about");
		CloseableHttpResponse response = httpClient.execute(getRequest);
		response.getStatusLine().getStatusCode();
		response.close();
		getRequest.releaseConnection();
	}
	
	private void listRooms(CloseableHttpClient httpClient) throws IOException{
		HttpGet getRequest = new HttpGet(IHOTEL_URL + "/ihotel/rooms/list");
		CloseableHttpResponse response = httpClient.execute(getRequest);
		response.getStatusLine().getStatusCode();
		response.close();
		getRequest.releaseConnection();
	}
		
	private void selectRoom(CloseableHttpClient httpClient, BookingParams params) throws IOException{
		String url = IHOTEL_URL + "/ihotel/rooms/view?id="+params.getRoomId();
		HttpGet getRequest = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(getRequest);
		response.getStatusLine().getStatusCode();
		response.close();
		getRequest.releaseConnection();
	}
	
	private void startBooking(CloseableHttpClient httpClient, BookingParams params) throws IOException{
		clickBookNow(httpClient, params);
		think(3000, 4000);
		submitBooking(httpClient, params);
		think(3000, 4000);
		if(params.getBookingNumber() != null)
			checkBookingStatus(httpClient, params.getBookingNumber());
	}
	
	private void clickBookNow(CloseableHttpClient httpClient, BookingParams params) throws IOException{
		String url = IHOTEL_URL + "/ihotel/booking/book?roomNo="+params.getRoomId();
		HttpGet getRequest = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(getRequest);
		response.getStatusLine().getStatusCode();
		response.close();
		getRequest.releaseConnection();
	}
	
	private void submitBooking(CloseableHttpClient httpClient, BookingParams params) throws IOException{
		debug("User [" +this.name+"] At the booking page for Room # [" + params.getRoomId()+"] for [" + params.getDays()+"] days and making decision about the booking .... ");
		int decisionMaker = (int)Util.getRandom(0, 10);

		debug("Making Decision based on Value = " + decisionMaker);
		
		String url = IHOTEL_URL + "/ihotel/booking/";
		if(decisionMaker >= 0 && decisionMaker < 3){
			debug("Decided to submit wrong credit card");
			url = url +"doBook";
			HttpPost postRequest = new HttpPost(url);
			List<NameValuePair> postParams = makePostParams(true, params);
			postRequest.setEntity(new UrlEncodedFormEntity(postParams));
			CloseableHttpResponse response = httpClient.execute(postRequest);
			postRequest.releaseConnection();

			//Added for business group example
//			url = IHOTEL_URL + "/ihotel/booking/book_failed?reason="+ URLEncoder.encode("Failed to contact iPayment", "UTF-8");
//			HttpGet getRequest = new HttpGet(url);
//			httpClient.execute(getRequest);
//
//			response.getStatusLine().getStatusCode();
//			response.close();
			
		}else if(decisionMaker >= 3 && decisionMaker < 6){
			debug("Decided to cancel booking");
			url = url +"cancel";
			HttpGet getRequest = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(getRequest);
			response.getStatusLine().getStatusCode();
			response.close();
			getRequest.releaseConnection();
			
		}else{

			//Normal Processing
			debug("Decided to continue booking");
			url = url +"doBook";
			HttpPost postRequest = new HttpPost(url);
			List<NameValuePair> postParams = makePostParams(false, params);
			postRequest.setEntity(new UrlEncodedFormEntity(postParams));
			CloseableHttpResponse response = httpClient.execute(postRequest);

			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line ;
			String bookingNumber = null; //Added for business group example
			while( (line = reader.readLine()) != null){
				bookingNumber = searchForReferenceNumber(line,params);
			}

			postRequest.releaseConnection();

			if (bookingNumber != null) {
				//Invoke booking complete
				url = IHOTEL_URL + "/ihotel/booking/book_complete?reservationId="+bookingNumber;
				HttpGet getRequest = new HttpGet(url);
				httpClient.execute(getRequest);
				getRequest.releaseConnection();
			}
			
			reader.close();
			response.close();
			
		}

	}
	
	private void checkBookingStatus(CloseableHttpClient httpClient,String bookingNumber)throws IOException{
		debug("Checking Booking");
		String url = IHOTEL_URL + "/ihotel/booking/find";
		HttpGet getRequest = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(getRequest);
		response.getStatusLine().getStatusCode();
		response.close();
		getRequest.releaseConnection();
		
		think(3000, 4000);
		url = IHOTEL_URL + "/ihotel/booking/doFind";
		HttpPost postRequest = new HttpPost(url);
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("booking",bookingNumber));
		postRequest.setEntity(new UrlEncodedFormEntity(postParams));
		response = httpClient.execute(postRequest);
		response.close();
		postRequest.releaseConnection();
	}
	
	private void contactHotel(CloseableHttpClient httpClient)throws IOException{
		//TODO: Override this exeception for now:
		if (true)
			return;
		String url = IHOTEL_URL + "/ihotel/contact";
		HttpGet getRequest = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(getRequest);
		response.getStatusLine().getStatusCode();
		response.close();
	}
	
	private void debug(String msg){
		if(debug)
		System.out.println("[DEBUG]["+this.name+"]:\n\t" + msg);
	}
	
	private void newLine(){
		if(debug)
		System.out.println("============================================");
	}
	
	private void think(int minTime, int maxTime){
		int differ  = maxTime - minTime;
		int rand    = Math.abs(new Random().nextInt()) % differ;
		int thinkTime = rand + minTime;
		try{
			sleep(thinkTime);
		}catch(InterruptedException ex){}
	}
	
	private BookingParams generateBookingParams(){
		BookingParams params = Util.getCheckInOut();
		String guestsNumber = "" + Util.getRandom(1, 5);
		params.setGuestsNumber(guestsNumber);
		String roomId = "" + Util.getRandom(1, 12);
		params.setRoomId(roomId);
		params.setGuestInfo(new GuestInfo());
		params.setPaymentInfo(new PaymentInfo());
		return params;
		
	}
	
	private List<NameValuePair> makePostParams(boolean invalidCard,BookingParams params){
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("roomNo",params.getRoomId()));	
		postParams.add(new BasicNameValuePair("title",params.getGuestInfo().getTitle()));
		postParams.add(new BasicNameValuePair("surename",params.getGuestInfo().getSureName()));
		postParams.add(new BasicNameValuePair("firstname",params.getGuestInfo().getFirstName()));
		postParams.add(new BasicNameValuePair("email",params.getGuestInfo().getEmail()));
		postParams.add(new BasicNameValuePair("dob","January 31st 1986"));
		postParams.add(new BasicNameValuePair("address","123 Street"));
		postParams.add(new BasicNameValuePair("phone","012345679"));
		
		postParams.add(new BasicNameValuePair("checkin",params.getCheckIn()));
		postParams.add(new BasicNameValuePair("checkout",params.getCheckOut()));
		postParams.add(new BasicNameValuePair("guestsno",params.getGuestsNumber()));
		
		postParams.add(new BasicNameValuePair("cardtype","VISA"));
		if(invalidCard)
			postParams.add(new BasicNameValuePair("cardno","500" + params.getPaymentInfo().getCardNumber()));
		else
			postParams.add(new BasicNameValuePair("cardno",params.getPaymentInfo().getCardNumber()));
		
		postParams.add(new BasicNameValuePair("seccode",params.getPaymentInfo().getCCV()));
		postParams.add(new BasicNameValuePair("expire",params.getPaymentInfo().getExpire()));
		
		return postParams;
	}
	
	private String searchForReferenceNumber(String line,BookingParams params){
		String resevation = "2017" + System.currentTimeMillis();
		params.setBookingNumber(resevation);
		return resevation;
	}

}
