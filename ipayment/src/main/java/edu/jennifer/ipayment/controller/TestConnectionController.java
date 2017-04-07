package edu.jennifer.ipayment.controller;

import edu.jennifer.ipayment.model.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author khalid
 * @version Created: 09/03/2017.
 */
@WebServlet(name="TestConnectionController" ,urlPatterns={"/test_connection"})
public class TestConnectionController extends BaseController{

    private PrintWriter writer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        if(writer == null) {
            writer = resp.getWriter();
        }
        out("Testing database .... ");
        String qString = "SELECT id, reservation_id, ammount, cardNumber, cardHolder from transactions";
        Connection connection = null;
        try{
            connection = Database.getInstance().getConnectionDM();
            PreparedStatement preparedStatement = connection.prepareStatement(qString);
            preparedStatement.execute();
            out("Test Passed");
        }catch (Exception ex){
            out("Test Failed");
        }finally {
            if(connection != null)
                Database.getInstance().disconnect(connection);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new RuntimeException("Not Implemented");
    }


    private void out(String message) {
        System.out.println(message);
        writer.print(message);
        writer.flush();
    }
}
