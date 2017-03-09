<div class="container">

<h2>Manage Your Booking</h2>


	<%@ include file="../elements/_flash.jsp" %>

	<div class="row">
		<div class="panel">
			<div class="panel-heading">Please enter your booking reference</div>
			<div class="panel-body">
				<form role="form" action="manage.do">
			        <div class="form-group">
			        	<label>Booking Reference:</label>
			            <input type="text" class="form-control" name="booking"/>
			        </div>
			        <button id="findBooking" class="btn btn-default">Find My Booking</button>
			        
			    </form>
			</div>
		</div>
	</div>


</div>