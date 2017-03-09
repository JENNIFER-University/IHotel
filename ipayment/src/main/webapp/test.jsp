<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="example.jennifer.ipayment.ConnectionUtil"%>
<%

   try{
	   ConnectionUtil connectionUtil = new ConnectionUtil();
	   String qString = "SELECT RESERVATION.AMMOUNT,RESERVATION.CARDNUMBER,RESERVATION.CARDHOLDER,RESERVATION.EXPIRE,RESERVATION.DATE,RESERVATION.STATUS FROM RESERVATIONS WHERE ID = ?";
	   out.println("<h3>Executing Test Query : </h3>");
	   out.println("<pre>" + qString  +"</pre>");
	   Connection connection = connectionUtil.getConnection();
	   PreparedStatement pst = connection.prepareStatement(qString);
	   pst.setString(1, "1");
	   ResultSet rs = pst.executeQuery();
	   while(rs.next()){}
	   connectionUtil.close(connection, pst, rs);
	   out.println("<h3>iPayment is running just fine</h3>");
   }catch(Exception ex){
	   out.println("<h3>Failed to run a test query: </h3> " + ex.getMessage());
	   response.getWriter().println("<pre>");
	   ex.printStackTrace(new PrintWriter(out));
	   response.getWriter().println("</pre>");
	}

%>