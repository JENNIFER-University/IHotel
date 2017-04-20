<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="container">

<h2>Booking Failed</h2>

	<div class="row">
		<div class="panel panel-danger">
			<div class="panel-heading">Booking Failed!</div>

			<div class="panel-body">
				<p>We are so sorry, but something went wrong with the booking process. Please contact our customer service</p>
				<p>Failure Reason: <s:property value="reason" />
			</div>
		</div>
	</div>


</div>
