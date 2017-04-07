<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
					href="${pageContext.request.contextPath}/welcome">iHotel</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse navbar-right"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/welcome">Home
					</a></li>
					<li><a href="${pageContext.request.contextPath}/rooms/list">Rooms
							& Tariff</a></li>
					<li><a
						href="${pageContext.request.contextPath}/about">Hotel
							Introduction</a></li>
					<li><a
						href="${pageContext.request.contextPath}/booking/find">Manage
							Booking</a></li>
					<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
					<li><a href="javascript:void();" data-toggle="modal" data-target="#aboutModal">About</a></li>
				</ul>
			</div>
			<!-- Wnavbar-collapse -->
		</div>
		<!-- container-fluid -->
	</nav>