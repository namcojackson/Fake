<%@page language="java" %> 
<% String ctxPath = request.getContextPath(); %>
	
<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %>
<%@page import="org.apache.commons.mail.Email" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<%@page import="canon.apps.common.CanonEmailUtil" %>

<%

String status = "N";
	try {
		int ticketId = Integer.parseInt(request.getParameter("ticket_id"));
		String skipNotification = request.getParameter("email_skip_notification");
		String toAddress = request.getParameter("email_to_address");
		String subject = request.getParameter("email_subject");
		String comments  = request.getParameter("email_comment");
		String userId  = request.getParameter("user_id");
		
		status = new Canon_E193_Assignment().insertOrUpdateCloseEmail(ticketId, skipNotification, toAddress,
				subject, comments, userId);
	}
	catch (Exception eExp) {
		status = "N";
	}
	
	response.getWriter().write(status);
%>