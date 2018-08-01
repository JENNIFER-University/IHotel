package edu.jennifer.hotel.tasks;

import edu.jennifer.hotel.util.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Statement;

/**
 * @author Khalid Elshafie
 * @Created 9/11/17 2:40 PM.
 */
public class DBCleaner {
    Logger logger = LogManager.getRootLogger();
    public void cleanReservationTable() {
        try{
            Statement stm = ConnectionUtil.getInstance().getDataSource().getConnection().createStatement();
            String query = "DELETE FROM reservations";
            stm.executeUpdate(query);
        }catch (Exception ex) {
            logger.error("Error while clearning reservations table", ex);
        }
    }
}
