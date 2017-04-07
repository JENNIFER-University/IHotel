package edu.jennifer.ihotel.action.elproblemo;

import edu.jennifer.ihotel.action.BaseAction;
import edu.jennifer.ihotel.model.Reservation;

import java.sql.SQLException;

/**
 * Created by khalid on 07/04/2017.
 */
public class SqlExceptionAction extends BaseAction {

    @Override
    public String execute() throws Exception {
        long start = System.currentTimeMillis();
        Reservation reservation= getReservationDAO().getReservation();
        try{
            getReservationDAO().insert(reservation);
        }catch (SQLException sql){
            long end = System.currentTimeMillis() - start;
			System.out.printf("[%s] in [%d] ms\n",sql.getMessage() ,end);
			throw sql;

        }
        return SUCCESS;
    }
}
