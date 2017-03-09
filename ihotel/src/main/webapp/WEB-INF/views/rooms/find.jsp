<%@page import="edu.jennifer.ihotel.util.Common"%>
<%@page import="edu.jennifer.ihotel.model.Room"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<Room> rooms = (ArrayList<Room>) request.getAttribute("rooms");
	String checkin = (String) request.getAttribute("checkin");
	String checkOut = (String) request.getAttribute("checkOut");
	long days = Long.parseLong(request.getAttribute("days").toString());
	int guestno = Integer.parseInt(request.getAttribute("guestno").toString());
%>

<div class="container">

<h2>Available Rooms</h2>


	<%@ include file="../elements/_flash.jsp" %>

	<div class="row">
		<% 
			if(rooms != null){
			for(Room room : rooms){
				int id = room.getId();
				if( (id < 7)  || (id > 11)){
					id = Common.getRandom(7, 10);
				}
		%>
			<div class="col-sm-6 wowload fadeInUp">
				<div class="rooms">
				<img src="../decorators/images/photos/<%= id %>.jpg" class="img-responsive">
				<div class="info"><h3><%= room.getRoomType().getRoomType() %> [<%= room.getFloor() %>] - <%= room.getPrice() %>$ Per Night</h3>
				<p><%= room.getDescription() %></p>
				<a id="selectRoom" href="view.do?id=<%= room.getId() %>&checkin=<%= checkin %>&checkout=<%= checkOut %>&days=<%= days %>&guestno=<%= guestno %>" class="btn btn-default">Details & Booking</a></div></div></div>	
		<%}} %>
	</div>


</div>