package edu.jennifer.stress.model;

import edu.jennifer.stress.util.AppUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khalid
 * @Created 3/21/18 3:02 PM.
 */
public class RequestParams {

    /**
     * User Account
     */
    private Account accountInformation;

    /**
     * Room to book
     */
    private Room room;

    /**
     * Guest Information for the booking form
     */
    private GuestInfo guestInfo;

    /**
     * Payment Information
     */
    private PaymentInfo paymentInfo;

    public RequestParams() {
        this.accountInformation = new Account();
        this.room = new Room();
        this.guestInfo = new GuestInfo();
        this.paymentInfo = new PaymentInfo();
    }

    public Account getAccountInformation() {
        return accountInformation;
    }

    public Room getRoom() {
        return room;
    }

    /**
     * Generate Post Request Params
     * @param invalidCard
     * @return
     */
    public List<NameValuePair> makePostRequestParams(boolean invalidCard){
        List<NameValuePair> postParams = new ArrayList<>();

        //Room
        postParams.add(new BasicNameValuePair("roomNo", Integer.toString(room.getRoomId())));

        //Guest
        postParams.add(new BasicNameValuePair("title",guestInfo.getTitle()));
        postParams.add(new BasicNameValuePair("surename",guestInfo.getSureName()));
        postParams.add(new BasicNameValuePair("firstname",guestInfo.getFirstName()));
        postParams.add(new BasicNameValuePair("email",guestInfo.getEmail()));
        postParams.add(new BasicNameValuePair("dob","January 31st 1986"));
        postParams.add(new BasicNameValuePair("address","123 Street"));
        postParams.add(new BasicNameValuePair("phone","012345679"));

        //Check in
        postParams.add(new BasicNameValuePair("checkin", AppUtil.getDateFormatted(0) ));
        postParams.add(new BasicNameValuePair("checkout",AppUtil.getDateFormatted(AppUtil.getRandom(3, 8))));
        postParams.add(new BasicNameValuePair("guestsno", Integer.toString(AppUtil.getRandom(1,6)))); //TODO: Remove this

        //Payment
        postParams.add(new BasicNameValuePair("cardtype","VISA"));
        if(invalidCard)
            postParams.add(new BasicNameValuePair("cardno","500" + paymentInfo.getCardNumber()));
        else
            postParams.add(new BasicNameValuePair("cardno",paymentInfo.getCardNumber()));

        postParams.add(new BasicNameValuePair("seccode",paymentInfo.getCCV()));
        postParams.add(new BasicNameValuePair("expire",paymentInfo.getExpire()));

        return postParams;
    }
}
