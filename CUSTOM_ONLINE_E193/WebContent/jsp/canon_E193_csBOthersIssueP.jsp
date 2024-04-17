<!-- $Header: canon_E193_csOthersIssue.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csOthersIssue.jsp - Others issues
 |   
 | DESCRIPTION
 |   Details of the Others issue.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	10/03/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 +=======================================================================--%>
<html>
<head>
<title>Billing Other Issue</title>
</head> 
<%@page language="java" %>
 <% String ctxPath = request.getContextPath(); %>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csCButton.js"></script>
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/styles.css" type="text/css">
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css" type="text/css">
<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>

<%
	String strInvNo = request.getParameter("invoiceNumber")==null?"":request.getParameter("invoiceNumber");
%> 

<form name="taxIssueForm" action="canon_E193_csTicketSummaryB2Controller.jsp" method="post">
	<table class="request-service" width="900" ALIGN="center" cellspacing="1">		
   		<tr>
   			<td class="tableQuestionCell" colspan="2">
   				<font class="promptReadOnly" color="#FF0000">
   					<b><%=strReviewPopupM1%></b>
   				</font>
   			</td>
   		</tr>  		
   		<tr>
   			<td  colspan="2"><font class="promptReadOnly">Selected Invoice: <%=strInvNo%></font></td>
   		</tr>  		
   		<tr>
   			<td  colspan="2">
   				<font class="promptReadOnly">Captured Details:</font>
   			</td>
   		</tr>
    	<tr>
    		<td colspan="2" height="10"></td>
  		</tr>
  		<tr  >
  			<td width="10"></td>
  			<td colspan="2">
  				<table cellspacing="1" class="request-service">
  					<tr>
  						<th>Reason Code</th>
  						<th>Urgency</th>
  					</tr>
  					<tr >
  						<td><font class="promptReadOnly"><%=request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc")%></font></td>
  						<td><font class="promptReadOnly"><%=request.getParameter("severity")==null?"":request.getParameter("severity")%></font></td>
  					</tr>
  				</table>
  			</td>
  		</tr>    	
   		<tr>
   			<td  colspan="2">
   				<font class="promptReadOnly">Captured Notes:</font>
   			</td>
   		</tr>    	
		<tr >
			<td width="10"></td>
			<td><font class="promptReadOnly"><%=request.getParameter("notes")==null?"":request.getParameter("notes")%></font></td>		
		</tr>    			
	</table>
</form>
</html>
<%
}catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}		
%>