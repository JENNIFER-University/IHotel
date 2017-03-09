<%@page import="edu.jennifer.ihotel.util.Common"%>
<%@page import="edu.jennifer.ihotel.model.Room"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<Room> rooms = (ArrayList<Room>) request.getAttribute("roomsList");

%>

<div class="container">

<h2>Our Rooms Rooms</h2>
	
	
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
				<div class="info"><h3><%= room.getRoomType().getRoomType() %> - <%= room.getPrice() %>$ Per Night</h3>
				<p><%= room.getDescription() %></p>
				<a href="view.do?id=<%= room.getId() %>" class="btn btn-default">Details & Booking</a>
				</div>
			</div>
		</div>	
		<%}} %>
	</div>
	

</div>



<script>
$(function(){
	$("#checkin").datepicker();
	$("#checkout").datepicker();
});
	
</script>