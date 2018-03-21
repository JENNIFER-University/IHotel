package edu.jennifer.stress.simula;

import edu.jennifer.stress.model.*;
import edu.jennifer.stress.simula.Browser;
import edu.jennifer.stress.util.AppUtil;

import java.util.*;

/**
 * @author Khalid
 * @Created 10/24/17 4:15 PM.
 */
public class VirtualUser extends Thread{

	/**
	 * Total calls for this user
	 */
	private final int CALLS_PER_USER 	= 6;

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
	private Browser browser;

	/**
	 * Helper for the user request
	 */
	private RequestParams requestParams;



	public VirtualUser(HashSet<String> activeUsers, String id, String appBaseUrl){
		this.id = id;
		this.activeUsers = activeUsers;
		this.appBaseUrl = appBaseUrl;
		this.browser = new Browser();
		this.requestParams = new RequestParams();
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
		String url = appBaseUrl + "/welcome?simula=1";
		this.browser.doGet(url);
	}

	/**
	 * Login Request
	 */
	private void login(){
		Account account = this.requestParams.getAccountInformation();
		String url = String.format("%s/doLogin?simula=1&username=%s&password=%s", this.appBaseUrl, account.getUsername(), account.getPassword());
		this.browser.doGet(url);
	}

	/**
	 * List all rooms
	 */
	private void listRooms(){
		this.browser.doGet(String.format("%s/rooms/list",this.appBaseUrl));
	}

	/**
	 * Filter rooms by type
	 */
	private void filterRooms(){
		String url = String.format("%s/rooms/filter?type=%s", this.appBaseUrl, this.requestParams.getRoom().getRoomType());
		this.browser.doGet(url);
	}

	/**
	 * View a specific room
	 */
	private void selectRoom() {
		String url = String.format("%s/rooms/view?id=%d", this.appBaseUrl, this.requestParams.getRoom().getRoomId());
		this.browser.doGet(url);
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

	private void _clickBookNow(){
		String url = String.format("%s/booking/book?roomNo=%d", this.appBaseUrl, this.requestParams.getRoom().getRoomId());
		this.browser.doGet(url);
	}

	private void _submitBooking(){
		int decisionMaker = (int) AppUtil.getRandom(0, 10);
		String bookingBaseUrl = this.appBaseUrl + "/booking/";

		if(decisionMaker >= 0 && decisionMaker < 3){ //Submit Invalid Card (Booking Fail)
			bookingBaseUrl += "doBook";
			this.browser.doPost(bookingBaseUrl, this.requestParams.makePostRequestParams(true));
		}else if(decisionMaker >= 3 && decisionMaker < 6){ //Cancel the booking
			bookingBaseUrl += "cancel";
			this.browser.doGet(bookingBaseUrl);
		}else{
			bookingBaseUrl += "doBook";
			String result = this.browser.doPost(bookingBaseUrl, this.requestParams.makePostRequestParams(false));
			think(3000, 4000);
			this.browser.doGet(result);
		}
	}

	/**
	 * Logout
	 */
	public void logout() {
		this.browser.doGet(String.format("%s/logout", this.appBaseUrl));
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
			sleep(thinkTime);
		}catch(InterruptedException ex){}
	}

}
