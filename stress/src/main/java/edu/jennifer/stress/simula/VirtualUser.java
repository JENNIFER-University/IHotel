package edu.jennifer.stress.simula;

import edu.jennifer.stress.factory.UserFactory;
import edu.jennifer.stress.model.*;
import edu.jennifer.common.AppUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.*;

/**
 * @author Khalid
 * @Created 10/24/17 4:15 PM.
 */
public class VirtualUser implements Runnable{

	/**
	 * Base URL for the application (iHotel)
	 */
	private String appBaseUrl;

	/**
	 * For the active users
	 */
	private String id;

	/**
	 * For Simula active users
	 */
	private HashSet<String> activeUsers;

	/**
	 * Http Client Instance
	 */
	private HttpClient httpClient;

	/**
	 * User Information
	 */
	private User user;

	/**
	 * Room that will be booked
	 */
	private Room room;

	/**
	 * User's Payment Information
	 */
	private PaymentInfo paymentInfo;




	public VirtualUser(HashSet<String> activeUsers, String id, String appBaseUrl){
		this.id = id;
		this.activeUsers = activeUsers;
		this.appBaseUrl = appBaseUrl;
		this.user = UserFactory.createUser();
		this.httpClient = new HttpClient(this.user.getLocation());
		this.room = Room.createRoom();
		this.paymentInfo = PaymentInfo.createPaymentInfo();

	}
	
	@Override
	public void run() {
		synchronized (activeUsers) {
			activeUsers.add(id);
		}

		openHomePage();
		think(3000, 4000);
		login();
		think(3000, 4000);
		listRooms();
		think(3000, 4000);
		filterRooms();
		think(3000, 4000);
		selectRoom();
		think(3000, 4000);
		bookRoom();
		think(3000, 4000);
		logout();


		synchronized (activeUsers) {
			activeUsers.remove(id);
		}
	}


	/**
	 * Call IHotel Main Page
	 */
	private void openHomePage(){
		String url = appBaseUrl + "/welcome?simula=true";
		this.httpClient.doGet(url);
	}

	/**
	 * Login Request
	 */
	private void login(){
		String url = String.format("%s/doLogin", this.appBaseUrl);
		this.httpClient.doPost(url, this.createLoginParams());
	}

	/**
	 * List all rooms
	 */
	private void listRooms(){
		this.httpClient.doGet(String.format("%s/rooms/list?simula=true",this.appBaseUrl));
	}

	/**
	 * Filter rooms by type
	 */
	private void filterRooms(){
		String url = String.format("%s/rooms/filter?type=%s&simula=true", this.appBaseUrl, this.room.getRoomType());
		this.httpClient.doGet(url);
	}

	/**
	 * View a specific room
	 */
	private void selectRoom() {
		String url = String.format("%s/rooms/view?id=%d&simula=true", this.appBaseUrl, this.room.getRoomId());
		this.httpClient.doGet(url);
	}

	/**
	 * Book a room
	 */
	private void bookRoom() {
		//1- Click the book Now button
		_clickBookNow();
		think(3000, 4000);

		//2- Submit the form
		_submitBooking();

	}

	/**
	 * Simulate click book now button
	 */
	private void _clickBookNow(){
		String url = String.format("%s/booking/book?roomNo=%d&simula=true", this.appBaseUrl, this.room.getRoomId());
		this.httpClient.doGet(url);
	}

	/**
	 * Booking Page
	 * Based on the random value, either continue boooking, cancle booking or submit invalid card (fail)
	 */
	private void _submitBooking(){
		int decisionMaker = (int) AppUtil.getRandom(0, 10);
		String bookingBaseUrl = this.appBaseUrl + "/booking/";

		if(decisionMaker >= 0 && decisionMaker < 3){ //Submit Invalid Card (Booking Fail)
			bookingBaseUrl += "doBook";
			this.httpClient.doPost(bookingBaseUrl, this.createBookingParams(true));
		}else if(decisionMaker >= 3 && decisionMaker < 6){ //Cancel the booking
			bookingBaseUrl += "cancel";
			this.httpClient.doGet(bookingBaseUrl);
		}else{
			bookingBaseUrl += "doBook";
			String result = this.httpClient.doPost(bookingBaseUrl, this.createBookingParams(false));
			think(3000, 4000);
			if (result != null)
				this.httpClient.doGet(result);
		}
	}

	/**
	 * Logout
	 */
	public void logout() {
		this.httpClient.doGet(String.format("%s/logout", this.appBaseUrl));
	}

	/**
	 * Random Delay
	 * @param minTime
	 * @param maxTime
	 */
	private void think(int minTime, int maxTime){
		int differ  = maxTime - minTime;
		int rand    = Math.abs(new Random().nextInt()) % differ;
		int thinkTime = rand + minTime;
		try{
			Thread.sleep(thinkTime);
		}catch(InterruptedException ex){}
	}


	/**
	 * Generate Booking Request Params
	 * @param invalidCard
	 * @return
	 */
	private List<NameValuePair> createBookingParams(boolean invalidCard){
		List<NameValuePair> postParams = new ArrayList<>();

		//Room
		postParams.add(new BasicNameValuePair("roomNo", Integer.toString(room.getRoomId())));

		//Guest
        postParams.add(new BasicNameValuePair("title","Mr"));
        postParams.add(new BasicNameValuePair("surename",this.user.getLastName()));
        postParams.add(new BasicNameValuePair("firstname",this.user.getFirstName()));
        postParams.add(new BasicNameValuePair("email",this.user.getEmail()));
		postParams.add(new BasicNameValuePair("dob","January 31st 1986"));
		postParams.add(new BasicNameValuePair("address","123 Street"));
		postParams.add(new BasicNameValuePair("phone","012345679"));

		//Check in
		postParams.add(new BasicNameValuePair("checkin", AppUtil.addDaysAndGetDateFormatted(0) ));
		postParams.add(new BasicNameValuePair("checkout",AppUtil.addDaysAndGetDateFormatted(AppUtil.getRandom(3, 8))));
		postParams.add(new BasicNameValuePair("guestsno", Integer.toString(AppUtil.getRandom(1,6)))); //TODO: Remove this

		//Payment
		postParams.add(new BasicNameValuePair("cardtype","VISA"));
		if(invalidCard)
			postParams.add(new BasicNameValuePair("cardno","500" + this.paymentInfo.getCardNumber()));
		else
			postParams.add(new BasicNameValuePair("cardno",this.paymentInfo.getCardNumber()));

		postParams.add(new BasicNameValuePair("seccode",this.paymentInfo.getCCV()));
		postParams.add(new BasicNameValuePair("expire",this.paymentInfo.getExpire()));

		return postParams;
	}

	private List<NameValuePair> createLoginParams(){
		List<NameValuePair> postParams = new ArrayList<>();
		postParams.add(new BasicNameValuePair("username", user.getUsername()));
		postParams.add(new BasicNameValuePair("password", user.getPassword()));

		return postParams;
	}

}
