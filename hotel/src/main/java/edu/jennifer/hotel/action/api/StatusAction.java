package edu.jennifer.hotel.action.api;

import edu.jennifer.hotel.action.BaseAction;
import edu.jennifer.hotel.util.ConnectionUtil;
import edu.jennifer.hotel.util.PaymentGateway;
import java.sql.SQLException;

/**
 * Testing communications
 * @author Khalid
 */
public class StatusAction extends BaseAction {

    private final String PASSED = "passed";
    private final String FAILED = "failed";

    private String databaseConnection;
    private String databaseTable;
    private String iPaymentConnection;
    private String icheckConnection;



    @Override
    public String execute() throws Exception {
        checkDatabaseConnection();
        checkDatabaseTables();
        checkIPaymentConnection();
        return SUCCESS;
    }


    private void checkIPaymentConnection() {
        String[] status = PaymentGateway.getConnectionStatus();
        iPaymentConnection = status == null ? FAILED : status[0];
        icheckConnection    = status == null ? FAILED : status[1];
    }

    private void checkDatabaseTables() {
        databaseTable = ConnectionUtil.getInstance().tablesAreOk() ? PASSED : FAILED;
    }

    private void checkDatabaseConnection() {
        try{
            if (ConnectionUtil.getInstance().getDataSource().getConnection() != null) {
                databaseConnection = PASSED;
            }else{
                databaseConnection = FAILED;
            }

        }catch (SQLException sql) {
            databaseConnection = FAILED;
        }
    }

    public String getDatabaseConnection() {
        return databaseConnection;
    }

    public String getDatabaseTable() {
        return databaseTable;
    }

    public String getiPaymentConnection() {
        return iPaymentConnection;
    }

    public String getIcheckConnection() {
        return icheckConnection;
    }
}
