<!-- $Header: canon_E193_csEITicketHistory.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csEITicketHistory.jsp - Ticket History
 |   
 | DESCRIPTION
 |   Ticket history screen accessed from other applications.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	09/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |-----------------------------------------------------------
 |04/04/2016    Mangala Shenoy		Created for S21
 |
 +=======================================================================--%>
<html>
<head><title>Ticket History</title>
</head>

<% String ctxPath = request.getContextPath(); %>

<link rel=stylesheet href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csCButton.js"></script>

<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<%
	try {
%>

<%@ include file="canon_E193_csCheckSessionInc.jsp" %>

<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objHistArrayList" scope="request" />

<script language="JavaScript">

	var orderAsc = "asc";
	var orderDesc = "desc";
	var tArray = new Array("number", "category", "date", "status", "acctName", "acctNo", "invNo", "contractNo", "ordNo", "daysOpen", "linesUnassigned");
	
	function fnOrderBy(name) {
		var objForm = document.ticketHistoryForm;
		var vOrdName = objForm.orderName.value;
		if(name == tArray[0] && vOrdName == "null") {
			vOrdName = tArray[0];
			objForm.orderBy.value = orderAsc;
		}
		if(vOrdName != name) objForm.orderBy.value = 'null';
		objForm.orderName.value = name;
		var reqOrderBy = objForm.orderBy.value;
		
		for(i=0; i<tArray.length; i++) {
			if(name == tArray[i] && (reqOrderBy == 'null' || reqOrderBy == orderDesc)) {
				objForm.orderBy.value = orderAsc;
			}else if(name == tArray[i] && reqOrderBy == orderAsc) {
				objForm.orderBy.value = orderDesc;
			}
		}
		objForm.submit();
		return false;
	}
	
	function action_func2(iPageIndex) {
		var objForm = document.ticketHistoryForm;
		objForm.searchType.value = '<%=request.getParameter("searchType")==null?"":request.getParameter("searchType")%>';
		objForm.searchValue.value = '<%=request.getParameter("searchValue")==null?"":request.getParameter("searchValue")%>';
		objForm.iPageNo.value = iPageIndex;
		objForm.submit();
	}	
	
	function TicketStatus(tktVal, accessVal) {
		resetErroMsg();
		if(accessVal == 1) {
			displayErrorMsg("Ticket creation is not complete. Ticket Status will be available once the creator / Owner of ticket completes the ticket.");
		}
		else if (accessVal == 2){
			displayErrorMsg("Access denied");
		}
		else {
			if(window.opener == null) {
				document.ticketHistoryForm.ticketId.value = tktVal;
				document.ticketHistoryForm.action = "canon_E193_csTicketStatusController.jsp";
				document.ticketHistoryForm.submit();
			}else{
				window.opener.location.href = "canon_E193_csTicketStatusController.jsp?hPageFrom=TicketHistory&ticketId="+tktVal;
			}
		}
	}

</script>
<%
	//Get Org ID
	int iOrgId = objCiSession.getOrgId();
	//Get User Id
	String iUserId = objCiSession.getUserId();
	//Get Resource Id
	//Start changes for S21 by Mangala
	//int iResId = objCiSession.getResourceId();
	String iResId = objCiSession.getResourceId();	
		 //End changes for S21 by Mangala
	Object objAcct = session.getAttribute("objSessionAcctInfo");
	Canon_E193_AcctInfoObj objSessionAcctInfo = null;
	if(objAcct != null) objSessionAcctInfo = (Canon_E193_AcctInfoObj)objAcct;

%> 
<body link='#663300' alink='FF6600' vlink='#996633' topmargin=0 marginheight=0>
<form name="ticketHistoryForm" action="canon_E193_csTicketHistoryController.jsp" method="post">
	<input type="hidden" name="iPageNo" value="<%=request.getParameter("iPageNo")==null?"0":request.getParameter("iPageNo")%>">
	<input type="hidden" name="iTotPageNo" value="<%=request.getParameter("iTotPageNo")==null?"0":request.getParameter("iTotPageNo")%>">
	<input type="hidden" name="orderName" value="<%=request.getParameter("orderName")%>">
	<input type="hidden" name="orderBy" value="<%=request.getParameter("orderBy")%>">
	<input type="hidden" name="popup" value="<%=request.getParameter("popup")==null?"":request.getParameter("popup")%>">
	
	<input type="hidden" name="hPageFrom" value="TicketHistory">
	<input type="hidden" name="ticketId" value="" >
	<table class="request-service" style="align:center;width:900px" cellpadding="1" cellspacing="0" border="0">
	<tr>
		<td colspan="2" height="10"></td>
	</tr>
	<tr> 
		<td class="tdTableCellStyle" colspan="2">
			<table cellspacing="0" cellpadding="2" rules="all" border="0" class="request-service">
				<tr>
	   				<td width="300"><font class="titleStyle1">Tickets History</font></td>
	   				</tr>
	   				<tr>
	   				<td>&nbsp; </td></tr>
	   		</table>
		</td>
	</tr>
	<tr>
			    <td colspan="2">
			       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
				   </div>
			   </td>
			</tr>
   	<tr>
   		<td width="10"></td>
    	<td>
	    	<table cellspacing="0" cellpadding="2" border="1" class="request-service">
		 		<tr class="subhead2">
					<td><a>Number</a></td>
					<td><a>Category</a></td>
					<td><a>Date</a></td>
					<td><a>Status</a></td>
					<td><a>Account Name</a></td>
					<td><a>Account Number</a></td>
				   	<td><a>Invoice Number</a></td>
					<td><a>Contract Number</a></td>
					<td><a>Order Number</a></td>
					<td><a>Days Open</a></td>
					<td><a>Lines Unassigned</a></td>
	 			</tr>
	 			<%
	 				Canon_E193_TicketHeaderObj objTicketHeader = null;
	 				ArrayList alTicket = (ArrayList)objHistArrayList.getArrayList();
	 				if(alTicket != null && alTicket.size() > 0) {
	 					for(int i = 0; i < alTicket.size(); i++) {
	 					 	objTicketHeader = (Canon_E193_TicketHeaderObj)alTicket.get(i);
	 					 	String strOrdrNo = String.valueOf(objTicketHeader.getOrderNo());
							if("0".equalsIgnoreCase(strOrdrNo))
								strOrdrNo = "";
								
							String strUnassignedLinesFlag = objTicketHeader.getAttribute3();
							String strUnassignedLinesFlagDesc = "";
							if("N".equalsIgnoreCase(strUnassignedLinesFlag))
								strUnassignedLinesFlagDesc = "No";
							else if("Y".equalsIgnoreCase(strUnassignedLinesFlag))
								strUnassignedLinesFlagDesc = "Yes";
										
							int tktSummaryNoAccess = 0;
							String strTicketId = objTicketHeader.getTicketNo();
							int iTktId = -1;
							if(strTicketId != null && !("".equals(strTicketId)) && !("null".equalsIgnoreCase(strTicketId)) )
								iTktId = Integer.parseInt(strTicketId);
							if(iTktId == -1)
							{
								tktSummaryNoAccess = 1;
							}
							else
							{
								Canon_E193_Ticket objTktUnassigned = new Canon_E193_Ticket();
								String strOutput = objTktUnassigned.getTicketSummaryAccess(iOrgId,iTktId,iResId,iUserId);
								//System.out.println("####strOutput = "+strOutput);
								StringTokenizer st = new StringTokenizer(strOutput, "|");
								int iCount = -1; 
								String strAccess = null;
								//System.out.println("CSR Workbench Testing strOutput is " + strOutput);
								if(st.countTokens()> 0)
									iCount = Integer.parseInt(st.nextToken());
								//System.out.println("####iCount = "+iCount);
								if(st.countTokens() > 0)
									strAccess = st.nextToken();	 	
								//System.out.println("####strAccess = "+strAccess);
								//if(iCount > 0 && !("Y".equalsIgnoreCase(strAccess)) ){
									//tktSummaryNoAccess = 1;}
								if ((iCount > 0 && !("Y".equalsIgnoreCase(strAccess)) )) 
									tktSummaryNoAccess = 2;
							}
						//	System.out.println("####tktSummaryNoAccess = "+tktSummaryNoAccess);

				%>
						   	<tr class="tdTableCellStyle">
				               	<td><%= objTicketHeader.getTicketNo()==null?"":objTicketHeader.getTicketNo()%></a></td>
							   	<td><%= objTicketHeader.getCategory()==null?"":objTicketHeader.getCategory()%></td>
							   	<td nowrap><%=objTicketHeader.getDate()==null?"":objTicketHeader.getDate()%></td>
							   	<td><%=objTicketHeader.getStatus()==null?"":objTicketHeader.getStatus()%></td>
							   	<td><%=objTicketHeader.getAcctName()==null?"":objTicketHeader.getAcctName()%></td>
							   	<td><%=objTicketHeader.getAcctNo()==null?"":objTicketHeader.getAcctNo()%></td>
							   	<td><%=objTicketHeader.getInvoiceNo()==null?"":objTicketHeader.getInvoiceNo()%></td>
							   	<td><%=objTicketHeader.getContractNo()==null?"":objTicketHeader.getContractNo()%></td>
								<td><%=strOrdrNo%></td>
								<td><%=objTicketHeader.getIDaysOpen()%></td>
								<td><%=strUnassignedLinesFlagDesc%></td>
							</tr>
				<%		} 
						int iTotPageNo = (int)(Integer.parseInt(request.getParameter("iTotPageNo")));
						int iPageNo = (int)(Integer.parseInt(request.getParameter("iPageNo")));
						if (iTotPageNo > 1) {
				%>
							<tr class="tdTableCellStyle">
								<td colspan="11"> Pages:  &nbsp; &nbsp;
				<%
							for(int j=1;j<=iTotPageNo;j++) {
								if(j == iPageNo) {
					%>									
									&nbsp;<%=j%>
					<%										
								}else{
					%>
									&nbsp;<a href="javascript:action_func2(<%=j%>);"><%=j%></a>
					<%
								}
							} // close for loop
					%>
								</td>
							</tr>
					<%									
						} // close total page no
					}else{
					%>
						<tr class="tdTableCellStyle">
							<td colspan="11">
									<font class="promptReadOnly"><b><%=strTicketHistoryPM1%></b></font>
							</td>
						</tr>
					<% } %>
	     		</table>   
	  		</td>
  		</tr>
    	<tr>
    		<td colspan="2" height="10"></td>
  		</tr>
		<tr>
			<td align="left" colspan="2"> 
				<table>
					<tr>
						<td>
							<script language="javascript"> 
								buttonGen("Close", "javascript:window.close();"); 
							</script>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
<%
}
catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>