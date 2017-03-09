package edu.jennifer.ipayment.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author khalid
 * @version Created: 08/03/2017.
 */
public class TestMock {

    public static void main(String[] args) throws Exception{
        Class.forName("mock.jdbc.MockDriver");
        Connection connection = DriverManager.getConnection("jdbc:mock");
        System.out.println("Gotcha");
    }
}
