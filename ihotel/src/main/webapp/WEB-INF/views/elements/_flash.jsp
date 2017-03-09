<%@page import="edu.jennifer.ihotel.util.Flash"%>
<%@page import="edu.jennifer.ihotel.util.SessionKeys"%>
<%
   if(session.getAttribute(SessionKeys.FLASH) != null){
	   Flash flash = (Flash) session.getAttribute(SessionKeys.FLASH);
%>

<div class="alert alert-<%=flash.getType() %> alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">x</button>
    <%= flash.getMessage() %>
    
</div>
<%
session.setAttribute(SessionKeys.FLASH, null);
}
%>