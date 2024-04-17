<!-- $Header: canon_E193_csAssignmentP.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csSendEmailOnClose.jsp
 |   
 | DESCRIPTION
 |        On Status Close, provide email feature.
 |
 | AUTHOR
 |	sunil vytle 
 |
 | CREATION DATE
 |	11/03/2016
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 
<% String ctxPath = request.getContextPath(); %>
	
<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %>
<%@page import="org.apache.commons.mail.Email" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<%@page import="canon.apps.common.CanonEmailUtil" %>

<%
	try {
%>

<%
    boolean emailSuccess = false;
    String hSendEmailAction     = request.getParameter("hSendEmail")!= null? (String) request.getParameter("hSendEmail") : "";
    System.out.println("value of hSendEmailAction:"+hSendEmailAction);
		
	// To do to capture email details and send email	
	if (hSendEmailAction != null && hSendEmailAction.equals("true")) {
		String emailIdOnClose = request.getParameter("emailIdOnClose") != null? (String) request.getParameter("emailIdOnClose") : "";
		String emailSubject   = request.getParameter("subjectOnClose") != null? (String) request.getParameter("subjectOnClose") : "";
		String emailBody      = request.getParameter("commentsOnClose") != null? (String) request.getParameter("commentsOnClose") : "";
		
		String receiptEmail=emailIdOnClose;	
		String receiptEmails[]=null;
		if(receiptEmail==null || "".equalsIgnoreCase(receiptEmail))
			receiptEmail="test.testt@cusa.canon.com";
		else
		{
			StringTokenizer receiptTokens = new StringTokenizer(
					receiptEmail, ",");
			receiptEmails = new String[receiptTokens
					.countTokens()];
			int emailCount = 0;
			while (receiptTokens.hasMoreTokens()) {
				receiptEmails[emailCount] = receiptTokens.nextToken();
				emailCount++;
			}
		}
				
		try {
			Email email = CanonEmailUtil.createSimpleEmail(); 
			email.setSubject(emailSubject);
			if(receiptEmails==null)
				email.setMsg(emailBody).addTo(receiptEmail);
			else
				email.setMsg(emailBody).addTo(receiptEmails);
			email.send();
			emailSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			emailSuccess = false;
		}
	}	
	
	
	int ticketId =  Integer.parseInt(request.getParameter("ticketId"));
	
	String existingStatus = request.getParameter("existingStatus");
	
	String disableFields = (existingStatus != null && existingStatus.equalsIgnoreCase("CLOSE")) ? "disabled" : ""; 
	
	Canon_E193_CloseEmailObj closeEmailObj = new Canon_E193_Assignment().getCloseEmailDetails(ticketId);
	
	String skipNotificationChecked = (closeEmailObj.getSkipNotificationFlag() == null || !closeEmailObj.getSkipNotificationFlag().equals("Y")) ? "" : "checked";
	

%>

<input type="hidden" name="hSendEmail" id="hSendEmail" value="">
<table class="request-service" cellspacing="0">	

		<tr>    
			<td>
				<label><input type="checkbox" id="skipEmailNotification" <%=skipNotificationChecked%> />Skip Email Notification</label>
			</td>
		</tr>
		<tr>    
			<td>To address:<br>
				<input type="text" id="emailIdOnClose" name="emailIdOnClose" style="width:430px" 
					value="<%=closeEmailObj.getEmailAddress() %>" onChange="checkEmails(); " />
			</td>
		</tr>
		
		<tr>    
			<td>Subject:<br/>
				<input type="text" id="subjectOnClose" name="subjectOnClose" style="width:430px" value="<%=closeEmailObj.getSubject()%>"
				 />
			</td>
		</tr>
		
		<tr>    
			<td>
					<pre id="email_body_top" class="email_template" style="font-weight:normal; white-space: pre-wrap;">
Dear [CUSTOMER CONTACT NAME]:

Your Customer Care Inquiry [TICKET NUMBER] was closed on [DATE CLOSED].

</pre>					
				<textarea rows="8" cols="30" id="commentsOnClose" name="commentsOnClose" style="width:600px;height:150px" placeholder="Insert Comments Here" 
				><%= closeEmailObj.getComments()%></textarea>
<pre style="font-weight:normal; white-space: pre-wrap;" id="email_body_bottom">


If you have additional questions regarding this inquiry, please call our Customer Service department at 1-800-613-2228 with the above number.

Thank you,
Customer Service
Canon Solutions America, Inc.
</pre>					
			</td>
		</tr>
		<tr>
		   <td>
		     <div style="float:right;">
		     <a class="btn_red" href="javascript:closeDlg();">Cancel</a>
		     <% if(disableFields.equalsIgnoreCase("disabled"))
		     	{
		     %>
			 		<a class="btn_disabled" id="setup_email_ok_disabled" >Save</a>	
			 <%	}
		     	else
		     	{ %>
		     		<a class="btn_red" id="setup_email_ok" >Save</a>
		     	 
		     <% }%>		 
			</div>
		   </td>
		</tr>    		    		
	<tr>
		<td height="10"></td>
	</tr>
	
</table>
		<!-- <div style="float:right;">
		 <a class="btn_red" href="javascript:closeDlg();">Close</a>		 
		</div> -->
<%
}
catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>