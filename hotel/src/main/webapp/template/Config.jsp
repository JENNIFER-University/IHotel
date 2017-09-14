<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">

<head>
  <% if (request.getAttribute("JENNIFER_FEM") != null) { %>
  <%= request.getAttribute("JENNIFER_FEM") %>
  <% } %>


	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>iHotel | Config</title>

	<!-- Google fonts -->
	<link href='http://fonts.googleapis.com/css?family=Raleway:300,500,800|Old+Standard+TT'  rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Raleway:300,500,800'  rel='stylesheet' type='text/css'>

	<!-- font awesome -->
	<link  href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

	<!-- bootstrap -->
	<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/config.css" />

	<!-- favicon -->
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/decorators/images/favicon.png" type="image/x-icon">
	<link rel="icon" href="${pageContext.request.contextPath}/decorators/images/favicon.png" type="image/x-icon">


	<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">

	<!-- Jquery -->
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>

</head>

<body>


	<!-- Body  -->
	<tiles:insertAttribute name="body" />
	<!-- Body  -->

	<!-- boostrap -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript"></script>
	<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/config.js" type="text/javascript"></script>


</body>
</html>

