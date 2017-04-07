package edu.jennifer.ihotel.action;

import edu.jennifer.ihotel.model.Payment;
import edu.jennifer.ihotel.model.Reservation;
import edu.jennifer.ihotel.util.PaymentGateway;

/**
 * Created by khalid on 03/04/2017.
 */
public class FindBookingAction extends BaseAction {


    private String reservationId;
    private Reservation reservation;
    private Payment payment;

    @Override
    public String execute() throws Exception {
        Reservation result = getReservationDAO().findByReservationId(getReservationId(), initalize());
        if(reservationId == null || reservationId.length() == 0 || result == null) {
            return  ERROR;
        }

        Payment p = PaymentGateway.getPaymentDetails(result.getId());
        setReservation(result);
        setPayment(p);


        return SUCCESS;
    }


    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }
}
