package edu.jennifer.hotel.action;

import edu.jennifer.hotel.model.Guest;
import edu.jennifer.hotel.model.Payment;
import edu.jennifer.hotel.model.Reservation;
import edu.jennifer.hotel.model.Room;
import edu.jennifer.hotel.util.Common;
import edu.jennifer.hotel.util.PaymentGateway;
import edu.jennifer.hotel.util.RandomString;

/**
 * @author khalid
 * @created 03/04/2017.
 */
public class BookingSubmitAction extends BaseAction {

    private String surename;
    private String firstname;
    private String address;
    private String phone;
    private String email;

    private int roomNo = 0;
    private String checkin;
    private String checkout;
    private int guestsno;

    private String cardno;
    private String expire;
    private String seccode;
    private double totalAmmount;

    private String reservationId;

    private String reason;


    @Override
    public String execute() throws Exception {
        Guest guestInformation = getOrCreateGuest();


        Reservation reservation = initReservationObject(guestInformation);

        String reservationId = getReservationDAO().reservRoom(reservation);


        reservation.setId(reservationId);
        Payment paymenInfo = initPaymentInfoI(guestInformation);
        paymenInfo.setReservationId(reservationId);

        int status = PaymentGateway.makePayment(paymenInfo);
        if(status == PaymentGateway.OK) {
            if(getReservationDAO().confirmReservation(reservationId)){
                setReservationId(reservationId);
                return SUCCESS;
            }
        }

        setReason(PaymentGateway.getErrorReason(status));
        return ERROR;

    }

    private Payment initPaymentInfoI(Guest guest) {
        String cardNumber = new RandomString(16).nextString();
        if(getCardno() == null) {
            return generatePaymentInfo(cardNumber);
        }

        Payment payment = new Payment();
        payment.setCardNumber(getCardno() == null ? cardNumber : getCardno());
        payment.setAmmount(getTotalAmmount());
        payment.setCardHolder(guest.getFirstname() + " " + guest.getLastname());
        payment.setCcv(getSeccode());
        payment.setExpire(getExpire());
        return  payment;

    }

    private Payment generatePaymentInfo(String cardNumber){
	    Payment p = new Payment();
	    p.setReservationId(Common.getCurrentTimeStamp());
	    p.setCardNumber(cardNumber);
	    p.setCcv(new RandomString(3).nextString());
	    p.setExpire(Common.getCurrentDate());
	    p.setAmmount(Common.getRandom(500, 2000));
	    p.setCardHolder(Common.getRandomName());
	    return p;
	  }

    private Guest getOrCreateGuest() {
        Guest guest = getGuestDAO().findByEmail(getEmail(), initalize());
        if(guest == null) {
            guest = new Guest();
            guest.setTitle("Mr");
            guest.setLastname(getSurename());
            guest.setFirstname(getFirstname());
            guest.setEmail(getEmail());
            guest.setAddress(getAddress());
            guest.setPhone(getPhone());
            int guestId = getGuestDAO().saveGuestInformationh(guest);
            guest.setId(guestId);
        }

        return  guest;
    }

    private Reservation initReservationObject(Guest guestInformation){
        Reservation reservation = new Reservation();
        Room room = new Room();
        room.setId(getRoomNo());
        reservation.setGuest(guestInformation);
        reservation.setCheckIn(getCheckin());
        reservation.setCheckOut(getCheckout());
        reservation.setRoom(room);
        reservation.setGuestNumbers(getGuestsno());
        return  reservation;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoomNo() {
        if(roomNo == 0)
            return 1;
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getCheckin() {
        return checkin == null ? "" : checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout  == null ? "" : checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public int getGuestsno() {
        return guestsno;
    }

    public void setGuestsno(int guestsno) {
        this.guestsno = guestsno;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getExpire() {
        return expire == null ? "Tomorrow" : expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getSeccode() {
        return seccode == null ? "123" : seccode;
    }

    public void setSeccode(String seccode) {
        this.seccode = seccode;
    }

    public double getTotalAmmount() {
        if(totalAmmount == 0){
            return Common.getRandom(500, 2000);
        }
        return totalAmmount;
    }

    public void setTotalAmmount(double totalAmmount) {
        this.totalAmmount = totalAmmount;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
