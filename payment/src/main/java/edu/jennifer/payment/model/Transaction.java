package edu.jennifer.payment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue
	private Long id;

	private String reservationid;
	private String ammount;
	private String cardNumber;
	private String cardExpire;
	private String cardHolder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservationid() {
		return reservationid;
	}
	public void setReservationid(String reservation_id) {
		this.reservationid = reservation_id;
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
		details.setReservationid(reservationId);
		details.setAmmount("125.5");
		details.setCardExpire("2015-02-12");
		details.setCardHolder("Khalid Saeed");
		details.setCardNumber("12345678913");
		return details;
	}
}
