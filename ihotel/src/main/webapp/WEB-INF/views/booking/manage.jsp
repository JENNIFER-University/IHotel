<%@page import="edu.jennifer.ihotel.model.Payment"%>
<%@page import="edu.jennifer.ihotel.util.Common"%>
<%@page import="edu.jennifer.ihotel.model.Reservation"%>
<div class="container">
<%
	Reservation booking = (Reservation) request.getAttribute("bookings");
%>
<h2><%= request.getAttribute("byString") %></h2>


	<%@ include file="../elements/_flash.jsp" %>

	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Booking Reference : <%= booking.getId() %></div>
			<div class="panel-body">
				
				<div class="panel panel-info">
					<div class="panel-heading">Booking & Room Details</div>
					<div class="panel-body table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Check-in date</th>
									<th>Check-out date</th>
									<th>Guests Number</th>
									<th>Room Number</th>
									<th>Room Type</th>
									<th>Room Size</th>
									<th>Max Capacity</th>
									<th>Booking Status</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><%= booking.getCheckIn()%></td>
									<td><%= booking.getCheckOut() %></td>
									<td><%= booking.getGuestNumbers() %></td>
									<td><%= booking.getRoom().getRoomNumber()%></td>
									<td><%= booking.getRoom().getRoomType().getRoomType()%></td>
									<td><%= booking.getRoom().getRoomType().getRoomSize()%></td>								
									<td><%= booking.getRoom().getRoomType().getMaxCapacity()%> guests</td>
									<td><%= Common.reservationToString(booking.getStatus())%></td>
									</tr>	
							</tbody>
						</table>
					</div>
				</div>
				
				<%
					Payment paymentDetails = booking.getPaymentDetails();
				%>
				<div class="panel panel-warning">
					<div class="panel-heading">Payment Information</div>
					<div class="panel-body table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Transaction Id</th>
									<th>Amount</th>
									<th>Card Holder</th>
									<th>Card Number</th>
									<th>Expire Date</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><%= paymentDetails.getId() %></td>
									<td><%= paymentDetails.getAmmount()%></td>
									<td><%= paymentDetails.getCardHolder() %></td>
									<td><%= Common.maskCreditCard(paymentDetails.getCardNumber()) %></td>
									<td><%= paymentDetails.getExpire() %></td>
									</tr>	
							</tbody>
						</table>
					</div>
				</div>
				
				
			</div>
		</div>
	</div>



</div>