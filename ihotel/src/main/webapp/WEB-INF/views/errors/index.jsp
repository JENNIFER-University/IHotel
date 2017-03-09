<div class="container">

	<h1 class="title">Error</h1>
	<div class="row">
		<div class="alert alert-danger">iHotel has encountered an error. Please contact support team and provide them with the following error details</div>
		<p><strong>Failed URL: </strong>${location}</p>
		<p><strong>Exception Message: </strong>${exception.message}</p>
		<%
			Exception ex = (Exception) request.getAttribute("exception");
				
		%>
    </div>
    

    <div class="spacer">

    </div>

</div>