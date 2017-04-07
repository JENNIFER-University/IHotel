package edu.jennifer.ipayment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author khalid
 * @version Created: 09/03/2017.
 */
public class TransactionsDAO {

    public void save(Transaction transaction) throws Exception{
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO transactions value (?,?,?,?,?,?)");
        pst.setString(1, transaction.getId());
        pst.setString(2, transaction.getReservation_id());
        pst.setString(3, transaction.getAmmount());
        pst.setString(4, transaction.getCardHolder());
        pst.setString(5, transaction.getCardNumber());
        pst.setString(6, transaction.getCardExpire());
        ResultSet resultSet = pst.executeQuery();
        Database.getInstance().disconnect(connection);
    }

    public Transaction get(String reservationId) throws Exception{
        Connection connection = Database.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM transactions where reservation_id = ?");
        pst.setString(1, reservationId);
        ResultSet resultSet = pst.executeQuery();
        while(resultSet.next()) {
        }
        Database.getInstance().disconnect(connection);
        return Transaction.generate(reservationId);
    }
}
