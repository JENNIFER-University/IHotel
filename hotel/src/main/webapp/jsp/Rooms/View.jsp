<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container">

<h1 class="title"> <s:property value="room.roomType.roomType"/> [<s:property value="room.floor"/>]</h1>


 <!-- RoomDetails -->
            <div id="RoomDetails" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                <div class="item active"><img src="${pageContext.request.contextPath}/imgs/photos/8.jpg" class="img-responsive" alt="slide"></div>
                <div class="item  height-full"><img src="${pageContext.request.contextPath}/imgs/photos/9.jpg"  class="img-responsive" alt="slide"></div>
                <div class="item  height-full"><img src="${pageContext.request.contextPath}/imgs/photos/10.jpg"  class="img-responsive" alt="slide"></div>
                </div>
                <!-- Controls -->
                <a class="left carousel-control" href="#RoomDetails" role="button" data-slide="prev"><i class="fa fa-angle-left"></i></a>
                <a class="right carousel-control" href="#RoomDetails" role="button" data-slide="next"><i class="fa fa-angle-right"></i></a>
            </div>
  <!-- RoomCarousel-->


<div class="room-features spacer">
	<div class="row">
		<div class="col-sm-12 col-md-5">
		    <s:property value="room.description"/>
      	</div>

      	<div class="col-sm-6 col-md-3 amenitites">
      		<h3>Facilities</h3>
      		<ul>
      		    <s:iterator value="room.roomFacilities">
      		        <li><s:property value="name"/></li>
      		    </s:iterator>

			</ul>
      	</div>

		<div class="col-sm-3 col-md-2">
			<div class="size-price">Size<span><s:property value="room.roomType.roomSize"/></span></div>
      	</div>

      	<div class="col-sm-3 col-md-2">
          <div class="size-price">Price Per Night<span>$<s:property value="room.price"/></span></div>
      </div>

    </div>

   </div>

<div class="row">
	<div class="col-sm-12 col-md-5"></div>
	<div class="col-sm-6 col-md-3 amenitites"></div>

	<div class="col-sm-3 col-md-4">
		<div class="panel panel-info">
			<div class="panel-footer">
  				<a id="selectRoom" href="../booking/book?roomNo=<s:property value="room.id"/>" class="btn btn-info btn-block"><i class="fa fa-credit-card" aria-hidden="true"></i> Book Now</a>
  			</div>
		</div>
	</div>

</div>


</div>