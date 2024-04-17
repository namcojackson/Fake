<!-- $Header: canon_E193_csPOIssueP.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csPOIssueP.jsp - Purchase order issues.
 |   
 | DESCRIPTION
 |   Details of the purchase order issue.
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
<title>Billing PO Issue</title>
</head> 
<% String ctxPath = request.getContextPath(); %>
<%@page language="java" %> 

<SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csCButton.js"></script>
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/styles.css" type="text/css">
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css" type="text/css">

<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>

<%
	Object objAcct = session.getAttribute("objSessionAcctInfo");
	Canon_E193_AcctInfoObj objSessionAcctInfo = null;
	if(objAcct != null) objSessionAcctInfo = (Canon_E193_AcctInfoObj)objAcct;

	String strInvNo = request.getParameter("invoiceNumber")==null?"":request.getParameter("invoiceNumber");
	String strTaxOrig = request.getParameter("iTaxOrig")==null?"0":request.getParameter("iTaxOrig");
	String strPurchageOrd = request.getParameter("strPurchageOrd")==null?"":request.getParameter("strPurchageOrd");
%> 
<form name="taxIssueForm" action="canon_E193_csTicketSummaryB2Controller.jsp" method="post">
	<table class="request-service" width="900" align="center" cellpadding="2" cellspacing="0" border="0" bgcolor="#FFFFFF">		
  		<tr>
    		<td colspan="3" height="10"></td>
  		</tr>
   		<tr>
   			<td class="tableQuestionCell" colspan="3">
   				<font class="promptReadOnly" color="#FF0000">
   					<b><%=strReviewPopupM1%></b>
   				</font>
   			</td>
   		</tr>
  		<tr><td colspan="3" height="10"></td></tr>
   		<tr>
   			<td class="tdTableCellStyle" colspan="3"><font class="promptReadOnly">Selected Invoice: <%=strInvNo%></font></td>
   		</tr>
  		<tr>
    		<td colspan="3" height="10"></td>
  		</tr>
   		<tr>
   			<td class="tdTableCellStyle" colspan="3">
   				<font class="promptReadOnly">PO Number Details:</font>
   			</td>
   		</tr>
<%
		String strPOReqFlag = objSessionAcctInfo.getPORequiredFlag()==null?"":objSessionAcctInfo.getPORequiredFlag();
%>		
		<tr>
			<td width="10"></td>
			<td align="left">
				<font class="promptReadOnly">PO Required</font>
			</td>
			<td align="left">
				<font class="promptReadOnly"><%=strPOReqFlag%></font>
			</td>
		</tr>
		<tr><td height="10" colspan="3"></td></tr>		
		<tr class="tdTableCellStyle" >
			<td width="10"></td>
			<td width="160">
				<font class="promptReadOnly">Existing PO#</font>
			</td>
			<td width="730" >
				<font class="promptReadOnly"><%=strPurchageOrd%></font>
			</td>
		</tr>
		<tr class="tdTableCellStyle" >
			<td width="10"></td>	
			<td align="left">
				<font class="promptReadOnly">New PO#</font>
			</td>
			<td align="left" >
				<font class="promptReadOnly"><%=request.getParameter("newPurchageOrd")==null?"":request.getParameter("newPurchageOrd")%></font>
			</td>
		</tr>  		
    	<tr>
    		<td colspan="3" height="10"></td>
  		</tr>
   		<tr>
   			<td class="tdTableCellStyle" colspan="3">
   				<font class="promptReadOnly">Captured Details:</font>
   			</td>
   		</tr>
    	<tr>
    		<td colspan="3" height="10"></td>
  		</tr>
  		<tr class="tdTableCellStyle">
  			<td width="10"></td>
  			<td colspan="2">
  				<table cellspacing="0" cellpadding="2" border="1" class="tabTableStyle">
  					<tr class="subhead">
  						<td>Reason Code</td>
  						<td>Urgency</td>
  					</tr>
  					<tr class="tdTableCellStyle">
  						<td><font class="promptReadOnly"><%=request.getParameter("reasonCdDesc")==null?"":request.getParameter("reasonCdDesc")%></font></td>
  						<td><font class="promptReadOnly"><%=request.getParameter("severity")==null?"":request.getParameter("severity")%></font></td>
  					</tr>
  				</table>
  			</td>
  		</tr>
    	<tr>
    		<td colspan="3" height="10"></td>
  		</tr>
   		<tr>
   			<td class="tdTableCellStyle" colspan="3">
   				<font class="promptReadOnly">Captured Notes</font>
   			</td>
   		</tr>
    	<tr>
    		<td colspan="3" height="10"></td>
  		</tr>
		<tr>
			<td width="10"></td>
			<td colspan="2"><font class="promptReadOnly"><%=request.getParameter("notes")==null?"":request.getParameter("notes")%></font></td>		
		</tr>
    	<tr>
    		<td colspan="3" height="10"></td>
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