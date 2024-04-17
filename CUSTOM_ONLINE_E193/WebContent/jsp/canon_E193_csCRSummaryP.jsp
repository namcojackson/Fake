<!-- $Header: canon_E193_csCRSummaryP.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csCRSummaryP.jsp - Credit/Rebill Summary
 |   
 | DESCRIPTION
 |   Details of the Credit/Rebill Summary
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	11/02/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 +=======================================================================--%>
<%@page import="java.text.DecimalFormat"%>
<html>
<head>
<title>Credit/Rebill Summary</title>
</head> 

<%@page language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_CRSummaryObj" %>
<%@ page import="com.canon.oracle.custapp.util.CanonCustAppUtil" %> 

<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Ticket" id="objTicketDao" scope="page"/>
<% String ctxPath = request.getContextPath(); %>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csCButton.js"></script>
<link rel=stylesheet href="<%=ctxPath%>/e193/css/styles.css">
<link rel=stylesheet href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css">

<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>

<%
	//Get Org ID
	int iOrgId = objCiSession.getOrgId();
	int iTicketId = 0, iLineId = 0, iLineNo = 0;
	String strTicketId = request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId");
	String strLineId = request.getParameter("iLineId")==null?"":request.getParameter("iLineId");
	String strLineNo = request.getParameter("iLineNo")==null?"":request.getParameter("iLineNo");
	if(!"".equalsIgnoreCase(strTicketId)) iTicketId = Integer.parseInt(strTicketId);
	if(!"".equalsIgnoreCase(strLineId)) iLineId = Integer.parseInt(strLineId);
	if(!"".equalsIgnoreCase(strLineNo)) iLineNo = Integer.parseInt(strLineNo);
	
	Canon_E193_CRSummaryObj crObj = null;
	ArrayList alSummaryList = objTicketDao.getCRSummary(iOrgId, iTicketId, iLineId, iLineNo);
	DecimalFormat df = new DecimalFormat("#.00");
	//System.out.println("!!!!!! alSummaryList= "+alSummaryList  );
%> 

<form name="crSummaryForm" method="post">
	<table width="900" class="request-service" style="align:center;" cellspacing="0" >				
   		<tr>
   			<td colspan="2">
   				<font class="promptReadOnly">Credit/Rebill Summary</font>
   			</td>
   		</tr>
		<tr> 
			<td width="10"></td>
			<td>
				<table cellspacing="0" cellpadding="2" border="1" class="supplies-table">
		 			<tr>
		 				<th>Credit-Rebill<br>Number</th>
		 				<th>Invoice<br>Number</th>
		 				<th>Invoice Date</th>
		 				<th>Contract<br>Number</th>
		 				<th>Date Billed From</th>
		 				<th>Date Billed To</th>
		 				<th>Old Invoice<br>Amount</th>
		 				<th>New Invoice<br>Amount</th>
		 				<th>Net Credit</th>
		 				<th>Rebill Invoice<br>Number</th>
		 				<th>CM Transaction<br>Number</th>
		 			</tr>
		 		<%
		 			boolean isNegative = false;
		 			if(alSummaryList != null && alSummaryList.size() > 0) {
		 				//Start Changes for S21 by Mangala
		 				double oldInvoiceAmount = 0.00;
		 				 double newInvoiceAmount = 0.00;
		 				 double totCredit =  0.00;
		 				//End Changes for S21 by Mangala
		 				for(int i=0; i<alSummaryList.size(); i++) {
		 					crObj = (Canon_E193_CRSummaryObj)alSummaryList.get(i);
		 		%>
		 					<tr>
		 						<td><%=crObj.getICrId()%></td>
		 						<td><%=crObj.getStrTrxNo()%></td>
		 						<td nowrap><%=crObj.getStrTrxDate()==null?"":crObj.getStrTrxDate()%></td>
		 						<td><%=crObj.getStrContractNo()==null?"":crObj.getStrContractNo()%></td>
		 						<td nowrap><%=crObj.getStrDateBilledFrom()==null?"":crObj.getStrDateBilledFrom()%></td>
		 						<td nowrap><%=crObj.getStrDateBilledTo()==null?"":crObj.getStrDateBilledTo()%></td>
		 						<td>$<%=CanonCustAppUtil.getFormattedDouble(crObj.getDOldInvoiceAmount())%></td>
		 						<td>$<%=CanonCustAppUtil.getFormattedDouble(crObj.getDNewInvoiceAmount())%></td>
		 						<td>$<%=CanonCustAppUtil.getFormattedDouble(crObj.getDNetCredit())%></td>
		 						<td><%=crObj.getStrRebillTrxNumber()==null?"":crObj.getStrRebillTrxNumber()%></td>
		 						<td><%=crObj.getStrCmTrxNo()==null?"":crObj.getStrCmTrxNo()%></td>
		 					</tr>
		 					
		 					
		 		<%  //Start Changes for S21 by Mangala
		 			oldInvoiceAmount=oldInvoiceAmount + Double.parseDouble(CanonCustAppUtil.getFormattedDouble(crObj.getDOldInvoiceAmount()));
		 		   newInvoiceAmount=newInvoiceAmount + Double.parseDouble(CanonCustAppUtil.getFormattedDouble(crObj.getDNewInvoiceAmount()));
		 		   totCredit=totCredit + Double.parseDouble(CanonCustAppUtil.getFormattedDouble(crObj.getDNetCredit()));
		 		  
		 		   //System.out.println("getDNewInvoiceAmount = "+Double.parseDouble(CanonCustAppUtil.getFormattedDouble(crObj.getDNewInvoiceAmount())));
		 		  //System.out.println("getDNewInvoiceAmount1 = "+CanonCustAppUtil.getFormattedDouble(crObj.getDNewInvoiceAmount()));
		 		   //System.out.println("newInvoiceAmount = "+newInvoiceAmount);
		 		    
			 		  if(totCredit < Double.parseDouble("0.00"))
			 		  {
			 			 isNegative = true;
			 		  }
			 		  else 
			 		  {
			 		  	 isNegative = false;
			 		  }
		 		//End Changes for S21 by Mangala
		 				}%>
		 				
		 				<%  //Start Changes for S21 by Mangala %>
		 				<tr>
	 					<td colspan="5" style="border-left:1px solid #FFF;border-bottom:1px solid #FFF;"></td> 
	 					<td>Sum</td> 
	 					<td>$<%=oldInvoiceAmount==0.0?"0.00":CanonCustAppUtil.getFormattedDouble(oldInvoiceAmount) %></td>
	 					<td>$<%=newInvoiceAmount==0.0?"0.00":CanonCustAppUtil.getFormattedDouble(newInvoiceAmount) %></td>
	 					<% if(isNegative) {%>
	 					<td colspan="3"><font color="red">($<%=totCredit==0.0?"0.00":CanonCustAppUtil.getFormattedDouble(totCredit * -1)%>)</font></td>
	 					<%} else { %>
	 					<td colspan="3">$<%=totCredit==0.0?"0.00":CanonCustAppUtil.getFormattedDouble(totCredit) %></td>
	 					<%} %>
	 					</tr>
		 				<%  //End Changes for S21 by Mangala%>
		 			<%}else{
		 		%>
		 				<tr>
							<td colspan="11">
								<font class="promptReadOnly"><b><%=strCRSummaryPM1%></b></font>
							</td>
						</tr>
		 		<%
		 			}
		 		%>
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