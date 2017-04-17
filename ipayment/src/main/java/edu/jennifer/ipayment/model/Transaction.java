package edu.jennifer.ipayment.model;

import com.google.gson.Gson;
import org.bson.Document;


public class Transaction {

	private String id;
	private String reservation_id;
	private String ammount;
	private String cardNumber;
	private String cardExpire;
	private String cardHolder;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(String reservation_id) {
		this.reservation_id = reservation_id;
	}
	public String getAmmount() {
		return ammount;
	}
	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardExpire() {
		return cardExpire;
	}
	public void setCardExpire(String cardExpire) {
		this.cardExpire = cardExpire;
	}
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public static Transaction generate(String reservationId){
		Transaction details = new Transaction();
		details.setId(reservationId);
		details.setReservation_id(reservationId);
		details.setAmmount("125.5");
		details.setCardExpire("2015-02-12");
		details.setCardHolder("Khalid Saeed");
		details.setCardNumber("12345678913");
		return details;
	}

	public String toJson(){
		Gson g = new Gson();
		return g.toJson(this);
	}

	public Document toMongoDocument(){
		Document document = new Document();
		document.put("id",getId());
		document.put("reservation_id",getReservation_id());
		document.put("ammount",getAmmount());
		document.put("cardNumber",getCardNumber());
		document.put("cardHolder",getCardHolder());
		document.put("cardExpire",getCardExpire());

		return document;
	}

	
}
