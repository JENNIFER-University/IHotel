<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container">

<h2>Our Rooms Rooms</h2>


	<div class="row">
		<s:set var="counter" value="7" />

		<s:iterator value="rooms">

		<s:if test="#counter > 11">
		    <s:set var="counter" value="7" />
		</s:if>

		<div class="col-sm-6 wowload fadeInUp">
			<div class="rooms">
				<img src="${pageContext.request.contextPath}/imgs/photos/<s:property value="counter"/>.jpg" class="img-responsive">
				<div class="info"><h3><s:property value="roomType.roomType" /> - <s:property value="price" />$ Per Night</h3>
				<p><s:property value="description" /></p>
				<a href="view?id=<s:property value="id" />" class="btn btn-default">Details & Booking</a>
				</div>
			</div>
		</div>
		    <s:set var="counter" value="%{#counter+1}" />
		</s:iterator>
	</div>


</div>
