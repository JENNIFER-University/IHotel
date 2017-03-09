<!DOCTYPE html>
<%@page import="edu.jennifer.ihotel.util.Common"%>
<%@page import="edu.jennifer.ihotel.util.SessionKeys"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>iHotel | The Most Luxuary Hotel</title>

<!-- Google fonts -->
<link
	href='http://fonts.googleapis.com/css?family=Raleway:300,500,800|Old+Standard+TT'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Raleway:300,500,800'
	rel='stylesheet' type='text/css'>

<!-- font awesome -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">

<!-- bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/decorators/css/bootstrap.min.css" />

<!-- uniform -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/decorators/css/uniform.default.min.css" />

<!-- animate.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/decorators/css/animate.css" />


<!-- Jquery UI -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">


<!-- gallery -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/decorators/css/blueimp-gallery.min.css">


<!-- favicon -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/decorators/images/favicon.png"
	type="image/x-icon">
<link rel="icon"
	href="${pageContext.request.contextPath}/decorators/images/favicon.png"
	type="image/x-icon">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/decorators/css/style.css">

<script src="${pageContext.request.contextPath}/decorators/js/jquery.js"></script>

</head>

<body id="home">

	<!-- header -->
	<nav class="navbar  navbar-default" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/home.do">iHotel</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse navbar-right"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/home.do">Home
					</a></li>
					<li><a href="${pageContext.request.contextPath}/rooms/list.do">Rooms
							& Tariff</a></li>
					<li><a
						href="${pageContext.request.contextPath}/introduction.do">Hotel
							Introduction</a></li>
					<li><a
						href="${pageContext.request.contextPath}/booking/find.do">Manage
							Booking</a></li>
					<li><a href="${pageContext.request.contextPath}/contact.do">Contact</a></li>
					<li><a href="javascript:void();" data-toggle="modal" data-target="#aboutModal">About</a></li>
				</ul>
			</div>
			<!-- Wnavbar-collapse -->
		</div>
		<!-- container-fluid -->
	</nav>
	<!-- header -->


	<!-- Body  -->

	<sitemesh:write property='body' />
	<!-- Body  -->



	<footer class="spacer">
		<div class="container">
			<div class="row">

				<div class="col-sm-5">
					<h4>iHotel</h4>
					<p>iHotel Most luxurious hotel of the world with the royal
						treatments and excellent customer service. iHotel Most luxurious
						hotel of the world with the royal treatments and excellent
						customer service.</p>
				</div>

				<div class="col-sm-3">
					<h4>Quick Links</h4>
					<ul class="list-unstyled">
						<li><a href="${pageContext.request.contextPath}/home.do">Home
						</a></li>
						<li><a
							href="${pageContext.request.contextPath}/rooms/list.do">Rooms
								& Tariff</a></li>
						<li><a
							href="${pageContext.request.contextPath}/introduction.do">Hotel
								Introduction</a></li>
						<li><a
							href="${pageContext.request.contextPath}/booking/find.do">Manage
								Booking</a></li>
						<li><a href="${pageContext.request.contextPath}/contact.do">Contact</a></li>
					</ul>
				</div>

				<div class="col-sm-4 subscribe">
					<h4>Subscription</h4>
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Enter email id here"> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button">Get Notify</button>
						</span>
					</div>
					<div class="social">
						<a href="#"><i class="fa fa-facebook-square"
							data-toggle="tooltip" data-placement="top"
							data-original-title="facebook"></i></a> <a href="#"><i
							class="fa fa-instagram" data-toggle="tooltip"
							data-placement="top" data-original-title="instragram"></i></a> <a
							href="#"><i class="fa fa-twitter-square"
							data-toggle="tooltip" data-placement="top"
							data-original-title="twitter"></i></a> <a href="#"><i
							class="fa fa-pinterest-square" data-toggle="tooltip"
							data-placement="top" data-original-title="pinterest"></i></a> <a
							href="#"><i class="fa fa-tumblr-square" data-toggle="tooltip"
							data-placement="top" data-original-title="tumblr"></i></a> <a
							href="#"><i class="fa fa-youtube-square"
							data-toggle="tooltip" data-placement="top"
							data-original-title="youtube"></i></a>
					</div>
				</div>
			</div>
			<!--/.row-->
		</div>
		<!--/.container-->

		<!--/.footer-bottom-->
	</footer>



	<a href="#home" class="toTop scroll"><i class="fa fa-angle-up"></i></a>




	<!-- The Bootstrap Image Gallery lightbox, should be a child element of the document body -->
	<div id="blueimp-gallery"
		class="blueimp-gallery blueimp-gallery-controls">
		<!-- The container for the modal slides -->
		<div class="slides"></div>
		<!-- Controls for the borderless lightbox -->
		<h3 class="title">title</h3>
		<a class="prev"></a> <a class="next"></a> <a class="close"></a>
		<!-- The modal dialog, which will be used to wrap the lightbox content -->
	</div>


	<!-- wow script -->
	<script
		src="${pageContext.request.contextPath}/decorators/js/wow.min.js"></script>
	<!-- uniform -->
	<script
		src="${pageContext.request.contextPath}/decorators/js/jquery.uniform.js"></script>
	<!-- boostrap -->
	<script
		src="${pageContext.request.contextPath}/decorators/js/bootstrap.js"
		type="text/javascript"></script>
	<!-- jquery mobile -->
	<script
		src="${pageContext.request.contextPath}/decorators/js/touchSwipe.min.js"></script>
	<!-- jquery mobile -->
	<script
		src="${pageContext.request.contextPath}/decorators/js/respond.js"></script>
	<!-- gallery -->
	<script
		src="${pageContext.request.contextPath}/decorators/js/jquery.blueimp-gallery.min.js"></script>
	<!-- Jquery UI -->
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

	<!-- custom script -->
	<script src="${pageContext.request.contextPath}/decorators/js/script.js"></script>


<div id="aboutModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">About iHotel v<%= Common.APP_VERSION %></h4>
            </div>
            <div class="modal-body">
                <p><strong>iHotel</strong> and its compoents (iHotel,iPayment) are developed for the purpose of illustrating and showing JenniferSoft's product -JENNIFER V5- functions, and to be used in training and educational sessions conducted by JenniferSoft only.</p>
                <p>It shall not be used for any other purposes without permission from the application developer</p>
                <p>For more information please contact the developer : </p>
                <p class="text-warning"><small>Khalid Elshafie.</small> <a href="mailto:khalid@jennifersoft.com">khalid@jennifersoft.com</a></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>

