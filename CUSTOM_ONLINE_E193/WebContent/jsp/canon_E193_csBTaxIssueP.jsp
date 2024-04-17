<!-- $Header: canon_E193_csTaxIssueP.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csTaxIssueP.jsp - Tax issues.
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
<title>Billing Tax Issue</title>
</head> 
<%@page language="java" %> 
<% String ctxPath = request.getContextPath(); %>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csCButton.js"></script>
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/styles.css" type="text/css">
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css" type="text/css">
<%
try {	
   	String strTaxCharge = request.getParameter("taxCharge")==null?"":request.getParameter("taxCharge");
	String strTExep = request.getParameter("taxExemption")==null?"":request.getParameter("taxExemption");
	if("TAX_NOT_CHARGED".equals(strTExep)) strTaxCharge = "N";
	else if("Y".equals(strTExep) || "N".equals(strTExep)) strTaxCharge = "Y";
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>

<%
	String strInvNo = request.getParameter("invoiceNumber")==null?"":request.getParameter("invoiceNumber");
	String strTaxOrig = request.getParameter("iTaxOrig")==null?"0":request.getParameter("iTaxOrig");
%> 
<form name="taxIssueForm" action="canon_E193_csTicketSummaryB2Controller.jsp" method="post">
	<table class="request-service" style="align:center;" cellspacing="1">	
		  		
   		<tr>
   			<td class="tableQuestionCell" colspan="3">
   				<font class="promptReadOnly" color="#FF0000">
   					<b><%=strReviewPopupM1%></b>
   				</font>
   			</td>
   		</tr>
   		<tr>
   			<td  colspan="3"><font class="promptReadOnly">Selected Invoice: <%=strInvNo%></font></td>
   		</tr>  		
   		<tr>
   			<td  colspan="3">
   				<font class="promptReadOnly">Tax Charged: </font>
			<%
				String strDisplay = "";
				if("Y".equals(strTaxCharge)) strDisplay = "Yes";
				else if("N".equals(strTaxCharge)) strDisplay = "No";
			%>			
				<font class="promptReadOnly"><%=strDisplay%></font>
			</td>
		</tr>
	<%
		if("Y".equals(strTaxCharge)) {
	%>			  
	   		<tr>
	   			<td  colspan="3">
	   				<font class="promptReadOnly">Tax Details</font>
	   			</td>
	   		</tr>
			<tr >
				<td width="10"></td>
				<td width="160"> 
					<font class="promptReadOnly">Existing Tax Amount</font> 
				</td>
				<td width="730">
					<font class="promptReadOnly">$<%=strTaxOrig%></font>
				</td>
			</tr>
			<tr >
				<td width="10"></td>	
				<td align="left">
					<font class="promptReadOnly">Credit Tax Amount</font>
				</td>
				<td align="left" >
					<font class="promptReadOnly">$<%=request.getParameter("expTaxAmt")==null?"":request.getParameter("expTaxAmt")%></font>
				</td>
			</tr>  			    	
	   		<tr >
	   			<td colspan="3">
	   				<font class="promptReadOnly">Exemption Certificate: </font>
				<%
					if("Y".equalsIgnoreCase(strTExep)) strDisplay = "Yes";
					else if("N".equalsIgnoreCase(strTExep)) strDisplay = "No";
				%>			
					<font class="promptReadOnly"><%=strDisplay%></font>
				</td>
			</tr>
  	<%
		}
  	%>    	
   		<tr>
   			<td  colspan="3">
   				<font class="promptReadOnly">Captured Details:</font>
   			</td>
   		</tr>    	
  		<tr>
  			<td width="10"></td>
  			<td colspan="2">
  				<table cellspacing="0" cellpadding="2" rules="all" border="1" class="tabTableStyle">
  					<tr class="subhead">
  						<td>Reason Code</td>
  						<td>Urgency</td>
  					</tr>
  					<tr >
  						<td><font class="promptReadOnly"><%=request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc")%></font></td>
  						<td><font class="promptReadOnly"><%=request.getParameter("severity")==null?"":request.getParameter("severity")%></font></td>
  					</tr>
  				</table>
  			</td>
  		</tr>    	
   		<tr>
   			<td  colspan="3">
   				<font class="promptReadOnly">Captured Notes:</font>
   			</td>
   		</tr>    	
		<tr>
			<td width="10"></td>
			<td colspan="2"><font class="promptReadOnly"><%=request.getParameter("notes")==null?"":request.getParameter("notes")%></font></td>		
		</tr>    	
		<tr>
			<td colspan="3"> 
				<table>
					<tr>
						<td>
						<a class="btn_red" href="javascript:closeDlg();">Close</a>							
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
</html>
<%		
}catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}		
%>
