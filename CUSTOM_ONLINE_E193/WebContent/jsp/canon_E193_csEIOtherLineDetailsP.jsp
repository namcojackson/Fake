<!-- $Header: canon_E193_csEIOtherLineDetailsP.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csEIOtherLineDetailsP.jsp - Other line details.
 |   
 | DESCRIPTION
 |   Details of the incorrect billing issue details.
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
<html>
<head>
<title>Other Line Details</title>
</head> 
<%@page language="java" %> 

<%@page import ="java.util.*" %>
<%@ page import="com.canon.oracle.custapp.util.CanonCustAppUtil" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_InvoiceLinesObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objIBList" scope="request" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objIssueList" scope="request" />
<% String ctxPath = request.getContextPath(); %>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csCButton.js"></script>
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/styles.css" type="text/css">
	<link rel="stylesheet" href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css" type="text/css">
<%
	try {	
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>

<%
	ArrayList alSupplyList = (ArrayList)objIBList.getArrayList();
  	ArrayList alIssueList = (ArrayList)objIssueList.getArrayList();

  	Canon_E193_InvoiceLinesObj objInvLines = null;
  	Canon_E193_IssueListObj objIL = null;
  	
	String strInvNo = request.getParameter("invoiceNumber")==null?"":request.getParameter("invoiceNumber");
%>
 
<form name="supplyLineForm" action="canon_E193_csTicketSummaryB1Controller.jsp" method="post">
	<table width="900" cellspacing="1" bgcolor="#FFFFFF" class="request-service" >		 		
   		<tr>
   			<td colspan="2">
   				<font class="promptReadOnly" color="#FF0000">
   					<b><%=strReviewPopupM1%></b>
   				</font>
   			</td>
   		</tr>  		
   		<tr>
   			<td class="tdTableCellStyle" colspan="2"><font class="promptReadOnly">Selected Invoice: <%=strInvNo%></font></td>
   		</tr>  		
  		<tr>
   			<td colspan="2">
   				<font class="promptReadOnly">Invoice Details:</font>
   			</td>
   		</tr>    	
  		<tr>
  			<td width="10"></td>
  			<td>
  			<% if(alSupplyList != null && alSupplyList.size() > 5) { %>
  			<div style="overflow:auto; width:875; height:250;">
  			<% } %>
  				<table cellspacing="1"  class="request-service">
  					<tr>
  						<th>Item Number#</th>
  						<th>Description</th>
  						<th>Quantity</th>
  						<th>Unit Price</th>
  						<th>Extended Price</th>
  					<%
  						if(alIssueList != null && alIssueList.size() > 0) {
  							for(int i=0; i<alIssueList.size(); i++) {
  								objIL = (Canon_E193_IssueListObj)alIssueList.get(i);
  					%>
								<th><%=objIL.getCatDesc()%></th>
  					<%
  							}
  						}
  					%>
  					</tr>
  					<%
  						if(alSupplyList != null && alSupplyList.size() > 0) {
  							for(int i=0; i<alSupplyList.size(); i++) {
  								objInvLines = (Canon_E193_InvoiceLinesObj)alSupplyList.get(i);
  					%>
  								<tr>
  									<td><%=objInvLines.getItemNo()==null?"":objInvLines.getItemNo()%></td>
  									<td><%=objInvLines.getLineDesc()==null?"":objInvLines.getLineDesc()%></td>
  									<td><%=String.valueOf(objInvLines.getQuantity())%></td>
  									<td>$<%=CanonCustAppUtil.getDoubleWithFormat(objInvLines.getUnitPrice())%></td>
  									<td>$<%=CanonCustAppUtil.getDoubleWithFormat(objInvLines.getLineTotal())%></td>
  									<td><%=objInvLines.getStrCreditQty()==null?"":CanonCustAppUtil.getNumberWithFormat(Integer.parseInt(objInvLines.getStrCreditQty()))%></td>
  									<td><%=objInvLines.getStrCreditAmt()==null?"":"$"+CanonCustAppUtil.getDoubleWithFormat(Double.parseDouble(objInvLines.getStrCreditAmt()))%></td>
  								</tr>  								
				<%
							}
						}else{
				%>
							<tr >
								<td colspan="7">
									<font class="promptReadOnly"><b><%=strEIOtherLineDetailsPM1%></b></font>
								</td>
							</tr>
				<%		} %>
  				</table>
  			<% if(alSupplyList != null && alSupplyList.size() > 5) { %>
  				</div>
  			<% } %>
  			</td>
  		</tr>    	
   		<tr>
   			<td class="tdTableCellStyle" colspan="2">
   				<font class="promptReadOnly">Captured Details:</font>
   			</td>
   		</tr>    	
  		<tr>
  			<td width="10"></td>
  			<td>
  				<table cellspacing="0" cellpadding="2" rules="all" border="1" class="tabTableStyle">
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
    		<td colspan="2" height="10"></td>
  		</tr>
   		<tr>
   			<td class="tdTableCellStyle" colspan="2">
   				<font class="promptReadOnly">Captured Notes:</font>
   			</td>
   		</tr>
    	<tr>
    		<td colspan="2" height="10"></td>
  		</tr>
		<tr>
			<td width="10"></td>
			<td><font class="promptReadOnly"><%=request.getParameter("notes")==null?"":request.getParameter("notes")%></font></td>		
		</tr>
    	<tr>
    		<td colspan="2" height="10"></td>
  		</tr>				
	</table>
</form>
</html>
<%
}catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}		
%>