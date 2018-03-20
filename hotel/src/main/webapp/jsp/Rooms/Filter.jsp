<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container">

	<h2>Our Rooms Rooms - Type [<s:property value='%{type.replace("-", " ")}' />] </h2>
	<div class="row">
		<div class="col col-md-9">
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
		<div class="col col-md-3">
			<h3>Filter by type</h3>
			<ul class="list-group">
				<s:iterator value="roomTypes">
					<li class="list-group-item">
						<a href="filter?type=<s:property value='%{roomType.replace(" ", "-")}' />" class="btn btn-default"><s:property value="roomType"/> </a>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
</div>