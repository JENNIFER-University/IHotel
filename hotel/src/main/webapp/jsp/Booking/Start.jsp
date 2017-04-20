<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">

<h1 class="title">Booking: <s:property value="room.roomType.roomType"/></h1>


<div class="room-features">

	<div class="row">

		<form action="doBook" method="get">

		<div class="panel panel-info">
			<div class="panel-heading">
				Guests Information
				<div class="pull-right">
					<a href="javascript:void(0)" onclick="autoFill();"><i class="fa fa-lock"></i></a>
				</div>
			</div>

			<input type="hidden" name="roomNo" value="<s:property value="room.id"/>"/>
			<input type="hidden" name="totalAmmount" value="<s:property value="room.price"/>"/>


			<div class="panel-body">
				<div class="form-group">
	        		<label>Title:</label>
	            	<select name="title" class="form-control">
              			<option>Mr.</option>
              			<option>Ms.</option>
              			<option>Mrs.</option>
	        		</select>
	        	</div>

	        	<div class="form-group">
	        		<label>Surname:</label>
	        		<input type="text" class="form-control" id="surename" name="surname">
	        	</div>

	        	<div class="form-group">
	        		<label>First Name:</label>
	        		<input type="text" class="form-control" id="firstname" name="firstname">
	        	</div>

	        	<div class="form-group">
	        		<label>Date Of Birth:</label>
	        		<input type="text" class="form-control" name="dob">
	        	</div>

	        	<div class="form-group">
	        		<label>Address:</label>
	        		<input type="text" class="form-control" name="address">
	        	</div>

	        	<div class="form-group">
	        		<label>Phone:</label>
	        		<input type="text" class="form-control" name="phone">
	        	</div>

	        	<div class="form-group">
	        		<label>Email:</label>
	        		<input type="text" class="form-control" id="email" name="email">
	        	</div>



        	</div>

		</div>

		<div class="panel panel-info">
			<div class="panel-heading">Booking Info</div>
			<div class="panel-body">
				<div class="form-group">
	        		<label>check In Date:</label>
	        		<input type="text" class="form-control" id="checkin" name="checkin">
	        	</div>
	        	<div class="form-group">
	        		<label>check Out Date:</label>
	        		<input type="text" class="form-control" id="checkout" name="checkout">
	        	</div>
	        	<div class="form-group">
	        		<label>Guests Numbers:</label>
	        		<input type="text" class="form-control" id="guestsno" name="guestsno">
	        	</div>
			</div>
		</div>

		<div class="panel panel-info">
			<div class="panel-heading">Payment Method</div>

			<div class="panel-body">

				<div class="form-group">
	        		<label>Card Type:</label>
	            	<select name="cardtype" class="form-control">
              			<option>VISA</option>
              			<option>Master Card</option>
              			<option>American Express</option>
	        		</select>
	        	</div>

				<div class="form-group">
	        		<label>Card Number:</label>
	        		<input type="text" class="form-control" id="cardno" name="cardno">
	        	</div>

	        	<div class="form-group">
	        		<label>Security Code:</label>
	        		<input type="text" class="form-control" id="seccode" name="seccode">
	        	</div>

	        	<div class="form-group">
	        		<label>Expire Date:</label>
	        		<input type="text" class="form-control" id="expire" name="expire">
	        	</div>

	        	<div class="form-group">
	        		<label>Comments for our Booking Team:</label>
	        		<input type="text" class="form-control" name="comments">
	        	</div>

			</div>

		</div>

		<div class="panel panel-info">
			<div class="panel-heading">Booking Summary</div>
			<ul class="list-group">
				<li class="list-group-item"><strong>Room Type:</strong> <s:property value="room.roomType.roomType"/> </li>
				<li class="list-group-item"><strong>Price Per Night :</strong> <s:property value="room.price"/> </li>
			</ul>

			<div class="panel-footer">
				<button id="bookNow" type="submit" class="btn btn-default btn-lg">
					<i class="fa fa-credit-card" aria-hidden="true"></i> Book Now
				</button>

				<div class="pull-right">
					<a id="autoFill" href="javascript:void(0)" onclick="app.autoFill(0);"><i class="fa fa-lock"></i></a>
					<a id="autoFill500" href="javascript:void(0)" onclick="app.autoFill(1);"><i class="fa fa-unlock-alt"></i></a>
				</div>
			</div>
		</div>


		</form>

	</div>
</div>

</div>

