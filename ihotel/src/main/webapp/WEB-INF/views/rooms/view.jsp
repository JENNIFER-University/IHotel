<%@page import="edu.jennifer.ihotel.model.Facility"%>
<%@page import="edu.jennifer.ihotel.model.Room"%>
<%@page import="java.util.ArrayList"%>
<%
	Room room = (Room) request.getAttribute("room");

%>
<div class="container">

<h1 class="title"><%= room.getRoomType().getRoomType() %> [<%= room.getFloor() %>]</h1>

<%@ include file="../elements/_flash.jsp" %>

 <!-- RoomDetails -->
            <div id="RoomDetails" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                <div class="item active"><img src="../decorators/images/photos/8.jpg" class="img-responsive" alt="slide"></div>
                <div class="item  height-full"><img src="../decorators/images/photos/9.jpg"  class="img-responsive" alt="slide"></div>
                <div class="item  height-full"><img src="../decorators/images/photos/10.jpg"  class="img-responsive" alt="slide"></div>
                </div>
                <!-- Controls -->
                <a class="left carousel-control" href="#RoomDetails" role="button" data-slide="prev"><i class="fa fa-angle-left"></i></a>
                <a class="right carousel-control" href="#RoomDetails" role="button" data-slide="next"><i class="fa fa-angle-right"></i></a>
            </div>
  <!-- RoomCarousel-->


<div class="room-features spacer">
	<div class="row">
		<div class="col-sm-12 col-md-5">
          <%= room.getDescription() %>
      	</div>
      	
      	<div class="col-sm-6 col-md-3 amenitites">
      		<h3>Facilities</h3>
      		<ul>
      			<% for(Facility f: room.getRoomFacilities()){ %>
    				<li><%= f.getFacilityName() %></li>
    			<%} %>
			</ul>
      	</div>
        
		<div class="col-sm-3 col-md-2">
			<div class="size-price">Size<span><%= room.getRoomType().getRoomSize() %></span></div>
      	</div>
      	
      	<div class="col-sm-3 col-md-2">
          <div class="size-price">Price Per Night<span>$<%= room.getPrice() %></span></div>
      </div>
		
    </div>
    
   </div>

<div class="row">
	<div class="col-sm-12 col-md-5"></div>
	<div class="col-sm-6 col-md-3 amenitites"></div>
	
	<div class="col-sm-3 col-md-4">
		<div class="panel panel-info">
			<div class="panel-footer">
  				<a id="selectRoom" href="../booking/book.do?roomNo=<%= room.getId() %>" class="btn btn-info btn-block"><i class="fa fa-credit-card" aria-hidden="true"></i> Book Now</a>
  			</div>
		</div>
	</div>
	
</div>


</div>