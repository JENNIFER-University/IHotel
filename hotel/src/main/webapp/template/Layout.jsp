<%@ page import="edu.jennifer.hotel.util.Version" %>
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
	<title>iHotel | The Most Luxuary Hotel</title>

	<!-- Google fonts -->
	<link href='http://fonts.googleapis.com/css?family=Raleway:300,500,800|Old+Standard+TT'  rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Raleway:300,500,800'  rel='stylesheet' type='text/css'>

	<!-- font awesome -->
	<link  href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

	<!-- bootstrap -->
	<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrap.min.css" />

	<!-- uniform -->
	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/css/uniform.default.min.css" />

	<!-- animate.css -->
	<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/animate.css" />


	<!-- Jquery UI -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">


	<!-- gallery -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/blueimp-gallery.min.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<!-- favicon -->
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/decorators/images/favicon.png" type="image/x-icon">
	<link rel="icon" href="${pageContext.request.contextPath}/decorators/images/favicon.png" type="image/x-icon">


	<!-- Jquery -->
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>

</head>

<body id="home">

	<!-- header -->
    <tiles:insertAttribute name="header" />
	<!-- header -->


	<!-- Body  -->
	<tiles:insertAttribute name="body" />
	<!-- Body  -->



    <!-- footer -->
    <tiles:insertAttribute name="footer" />
    <!-- footer -->



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
		src="${pageContext.request.contextPath}/js/wow.min.js"></script>
	<!-- uniform -->
	<script
		src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
	<!-- boostrap -->
	<script
		src="${pageContext.request.contextPath}/js/bootstrap.js"
		type="text/javascript"></script>
	<!-- jquery mobile -->
	<script
		src="${pageContext.request.contextPath}/js/touchSwipe.min.js"></script>
	<!-- jquery mobile -->
	<script
		src="${pageContext.request.contextPath}/js/respond.js"></script>
	<!-- gallery -->
	<script
		src="${pageContext.request.contextPath}/js/jquery.blueimp-gallery.min.js"></script>
	<!-- Jquery UI -->
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

	<!-- custom script -->
	<script
    		src="${pageContext.request.contextPath}/js/app.js"></script>



<div id="aboutModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">About iHotel v <%= Version.getVersion() %> </h4>
            </div>
            <div class="modal-body">
                <p><strong>iHotel System</strong> with all of its compoents (iHotel,iPayment, iCheck) are developed for the purpose of illustrating and showing JenniferSoft's product -JENNIFER V5- functions, and to be used in training and educational sessions conducted by JenniferSoft only.</p>
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

