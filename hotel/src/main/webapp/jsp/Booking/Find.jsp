<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">

<h2>Manage Your Booking</h2>


	<div class="row">
		<div class="panel">
			<div class="panel-heading">Please enter your booking reference</div>
			<div class="panel-body">
				<form  action="doFind">
			        <div class="form-group">
			        	<label>Booking Reference:</label>
			            <input type="text" class="form-control" name="reservationId"/>
			        </div>
			        <button id="findBooking" class="btn btn-default">Find My Booking</button>

			    </form>
			</div>
		</div>
	</div>


</div>