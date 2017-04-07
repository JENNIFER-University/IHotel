package edu.jennifer.ipayment.controller;

import edu.jennifer.ipayment.model.Database;
import edu.jennifer.ipayment.util.Conf;
import edu.jennifer.ipayment.util.Validator;

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
@WebServlet(name="TestICheckController" ,urlPatterns={"/test_icheck"})
public class TestICheckController extends BaseController{

    private PrintWriter writer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        if(writer == null) {
            writer = resp.getWriter();
        }
        out("Testing ICheck Connection, getting IP and Port Number .... ");
        String icheckIP = Conf.getInstance().getICheckIP();
        String icheckPort = Conf.getInstance().getICheckPort();
        out(String.format("ICheck IP is [%s] Port Number is [%s]", icheckIP, icheckPort));
        Validator validator = new Validator();
        boolean initialized = validator.initialize(icheckIP,icheckPort);
        if(!initialized) {
            out("Failed to connect to iCheck, please makre sure the IP and Port are correct and ICheck is up Running");
            return;
        }

        String cardNumber = "459900000000";
        out(String.format("Connected to Icheck. Testing card Validating on Card Number [%s]", cardNumber));
        String result = validator.checkCard(cardNumber) ? "Valid" : "Invalid";
        out(String.format("Card Validation Result is %s", result));
        out("Test Passed");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new RuntimeException("Not Implemented");
    }


    private void out(String message) {
        System.out.println(message);
        writer.println(message);
        writer.flush();
    }
}
