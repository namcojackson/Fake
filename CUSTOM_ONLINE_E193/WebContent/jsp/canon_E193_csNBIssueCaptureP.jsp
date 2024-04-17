<!-- $Header: canon_E193_csNBIssueCaptureP.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csNBIssueCaptureP.jsp - Non billing issues
 |   
 | DESCRIPTION
 |   Details of the non billing issue.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	10/27/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 +=======================================================================--%>

<%@page language="java" %>
<% String ctxPath = request.getContextPath(); %>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csCButton.js"></script>
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/styles.css" type="text/css">
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css" type="text/css">

<%
	try {	
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>

<form name="nonBillingIssueForm" action="canon_E193_csTicketSummaryNBController.jsp" method="post">
    <table class="request-service" style="align:center;" cellspacing="0">			 		
   		<tr>
   			<td colspan="2">
   				<font class="promptReadOnly">Captured Details</font>
   			</td>
   		</tr>    	
  		<tr>
  			<td width="10"></td>
  			<td>
  				<table cellspacing="1" class="request-service">
  					<tr>
  						<th>Reason Code</th>
  						<th>Urgency</th>
  					</tr>
  					<tr>
  						<td><font class="promptReadOnly"><%=request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc")%></font></td>
  						<td><font class="promptReadOnly"><%=request.getParameter("severity")==null?"":request.getParameter("severity")%></font></td>
  					</tr>
  				</table>
  			</td>
  		</tr>    	
   		<tr>
   			<td class="tdTableCellStyle" colspan="3">
   				<font class="promptReadOnly">Captured Notes</font>
   			</td>
   		</tr>    	
		<tr>
			<td width="10">&nbsp;</td>
			<td><font class="promptReadOnly"><%=request.getParameter("notes")==null?"":request.getParameter("notes")%></font></td>		
		</tr>
		
	</table>
</form>
<%		
}catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}		
%>