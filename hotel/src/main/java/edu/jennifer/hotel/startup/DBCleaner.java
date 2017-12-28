package edu.jennifer.hotel.startup;

import edu.jennifer.hotel.util.ConnectionUtil;

import java.sql.Statement;

/**
 * @author Khalid Elshafie
 * @Created 9/11/17 2:40 PM.
 */
public class DBCleaner {

    public void cleanReservationTable() {
        try{
            Statement stm = ConnectionUtil.getInstance().getDataSource().getConnection().createStatement();
            String query = "DELETE FROM reservations";
            stm.executeUpdate(query);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
