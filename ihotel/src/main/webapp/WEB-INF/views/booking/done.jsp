<div class="container">

<h2>Booking Complete.Reservation Number [<%= request.getAttribute("reservationNumber") %>]</h2>


	<%@ include file="../elements/_flash.jsp" %>

	<div class="row">
		<div class="panel panel-info">
			<div class="panel-heading">Thank You!</div>
			
			<div class="panel-body">
				<p>Your booking is complete. We hope you enjoy your stay at our hotel. If you have any questions, please do not annoy us with it.</p>
				<p>Your booking reference is <strong id="bookingNumber"><%= request.getAttribute("reservationNumber") %></strong>, keep this number save in case you needed to modify your booking</p>
			</div>
		</div>
	</div>


</div>