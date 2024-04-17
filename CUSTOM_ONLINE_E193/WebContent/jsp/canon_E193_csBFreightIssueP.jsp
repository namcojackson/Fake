<!-- $Header: canon_E193_csBFreightIssueP.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csBFreightIssueP.jsp - Freight issues.
 |   
 | DESCRIPTION
 |   Details of the tax issue.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	10/26/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 +=======================================================================--%>
<html>
<head>
<title>Billing Freight Issue</title>
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
	String strTaxOrig = request.getParameter("iTaxOrig")==null?"0":request.getParameter("iTaxOrig");
	String strFreightOrig = request.getParameter("iFreightOrig")==null?"":request.getParameter("iFreightOrig");
%> 

<form name="taxIssueForm" action="canon_E193_csTicketSummaryB2Controller.jsp" method="post">
	<table class="request-service" width="900" ALIGN="center" cellspacing="1">		
  		<tr><td colspan="3" height="10"></td></tr>
   		<tr>
   			<td class="tableQuestionCell" colspan="3">
   				<font class="promptReadOnly" color="#FF0000">
   					<b><%=strReviewPopupM1%></b>
   				</font>
   			</td>
   		</tr>
  		<tr><td colspan="3" height="10"></td></tr>
   		<tr>
   			<td colspan="3"><font class="promptReadOnly">Selected Invoice: <%=strInvNo%></font></td>
   		</tr>  		
   		<tr>
   			<td  colspan="3">
   				<font class="promptReadOnly">Freight Amounts</font>
   			</td>
   		</tr>
		<tr>
			<td width="10"></td>
			<td width="200">
				<font class="promptReadOnly">Existing Freight Amount</font>
			</td>
			<td width="690">
				<font class="promptReadOnly">$<%=strFreightOrig%></font>
			</td>
		</tr>
		<tr >
			<td width="10"></td>	
			<td align="left">
				<font class="promptReadOnly">Expected Freight Amount</font>
			</td>
			<td align="left" >
				<font class="promptReadOnly">$<%=request.getParameter("expFreightAmt")==null?"":request.getParameter("expFreightAmt")%></font>
			</td>
		</tr>  		    	
   		<tr>
   			
     	 <td colspan="3"> <font class="promptReadOnly">Captured Details:</font> </td>
   		</tr>    	
  		<tr>
  			<td width="10"></td>
  			<td colspan="2">
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
   			<td colspan="3">
   				<font class="promptReadOnly" >Captured Notes:</font>
   			</td>
   		</tr>    	
		<tr class="tdTableCellStyle">
			<td width="10"></td>
			<td colspan="2"><font class="promptReadOnly"><%=request.getParameter("notes")==null?"":request.getParameter("notes")%></font></td>		
		</tr>    			
	</table>
</form>
</html>
<%
}catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}		
%>