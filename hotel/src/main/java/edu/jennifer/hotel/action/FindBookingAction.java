package edu.jennifer.hotel.action;

import edu.jennifer.hotel.model.Payment;
import edu.jennifer.hotel.model.Reservation;
import edu.jennifer.hotel.problem.ProblemPool;
import edu.jennifer.hotel.util.PaymentGateway;

/**
 * @author khalid
 * @created 03/04/2017.
 */
public class FindBookingAction extends BaseAction {


    private String reservationId;
    private Reservation reservation;
    private Payment payment; //TODO: Clean up

    @Override
    public String execute() throws Exception {

        if (ProblemPool.getInstance().makeProblem(ProblemPool.EX_CALL_EXCEPTION)) {
            Payment p = PaymentGateway.getPaymentDetails("ex1234567");
            return ERROR;
        }

        Reservation result = getReservationDAO().findByReservationId(getReservationId());
        if(reservationId == null || reservationId.length() == 0 || result == null) {
            return  ERROR;
        }

        Payment p = PaymentGateway.getPaymentDetails(result.getId());
        if (p == null) {
            return ERROR;
        }
        setReservation(result);
        setPayment(p);

        if (isSimula()) {
            doLongTx();
        }

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
