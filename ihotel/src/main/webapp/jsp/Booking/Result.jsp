<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">

<h2>Find Result </h2>



	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Booking Reference : <s:property value="reservationId"/></div>
			<div class="panel-body">

				<div class="panel panel-info">
					<div class="panel-heading">Booking & Room Details</div>
					<div class="panel-body table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Reservation Id</th>
									<th>Room Number</th>
									<th>Room Type</th>
									<th>Amount</th>
                                    <th>Card Holder</th>
                                    <th>Card Number</th>
								</tr>
							</thead>
							<tbody>
								<tr>
                                    <td><s:property value="reservation.id"/></td>
                                    <td><s:property value="reservation.room.roomNumber"/></td>
                                    <td><s:property value="reservation.room.roomType.roomType"/></td>
                                    <td><s:property value="payment.ammount"/></td>
                                    <td><s:property value="payment.cardHolder"/></td>
                                    <td><s:property value="payment.cardNumber"/></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>


			</div>
		</div>
	</div>



</div>
