package edu.jennifer.ipayment.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jennifer.ipayment.model.Transaction;
import edu.jennifer.ipayment.util.ConnectionUtil;

@WebServlet(name="PaymentDetailsController", urlPatterns={"/payment_detail/*"})
public class PaymentDetailsController extends BaseController{

	private static final long serialVersionUID = 1L;

	@Override
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
	    String[] parts = pathInfo.split("/");
	    String reservationId = parts[1];
	    String result;
		try {
			result = getTransactionDetails(reservationId);
			resp.getWriter().println(result);
		} catch (Throwable e) {

		}

	}

	private String getTransactionDetails(String reservationId) throws Throwable{
		try{
			ConnectionUtil connUtil = ConnectionUtil.getInstance();
			return connUtil.get(reservationId).toJson();
		}catch(Exception ex){
			return "404";
		}
	}
}
