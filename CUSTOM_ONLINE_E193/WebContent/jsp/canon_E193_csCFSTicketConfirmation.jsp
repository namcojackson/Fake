<!-- $Header: ITG# 74988 canon_E193_csTicketConfirmation.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csCFSTicketConfirmation.jsp - CFS Ticket Confirmation
 |   
 | DESCRIPTION
 |   Details of the CFS ticket confirmation
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	11/07/2005
 |
 | HISTORY
 | DATE		WHO		WHY
 | 12/06/2006	Vikas Basal	Inital version (ITG# 74988)
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 

<%@ include file="canon_E193_csTopInc.jsp" %>

<%
	// Menu Prompt
	strPageName = "CFS Cost Per Copy - Care";
		if(request.getParameter("hStrPageName")!=null)
			strPageName=request.getParameter("hStrPageName");
	
	// Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");
	String hPath = request.getParameter("hPath");
	if (hPath == null) hPath = strCFSTicketConfirmationT1;
	else if(hPath.indexOf(strCFSTicketConfirmationT1) < 0)
			hPath = hPath + " -> " + strCFSTicketConfirmationT1;
	
%>
<%@ include file="canon_E193_csMenuInc.jsp" %>

<script language="JavaScript">
	function action_func1() {
		var objForm = document.ticketSummaryForm;
		var v = objForm.controlForm.value;
		if(v == null || v == '' || v == 'null') objForm.action = "canon_E193_csNBIssueList.jsp";
		else objForm.action = "canon_E193_csBIssueList.jsp";
		objForm.submit();
	}
	function action_func2() {
		var objForm = document.ticketSummaryForm;
		objForm.action = "canon_E193_csWorkbenchController.jsp";
		objForm.submit();
	}
	function action_func3() {
		var objForm = document.ticketSummaryForm;
		objForm.action = "canon_E193_csCFSIssueCapture.jsp";
		objForm.submit();
	}	
	function action_func4() {
		var objForm = document.ticketSummaryForm;
		objForm.action = "canon_E193_csTicketHistoryController.jsp";
		objForm.submit();
	}
//-->
</script>
 <div id="main_content">
	<div id="page_top">
	   <h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strCFSTicketConfirmationT1%></h1>
	   <div class="breadcrumb"><%=hPath%></div>
	</div>	
<form name="ticketSummaryForm" method="post">
	
	<input type="hidden" name="hPageFrom" value="TicketConfirmation">
	<input type="hidden" name="nextPage" value="">
	<input type="hidden" name="iTicketId" value="<%=request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId")%>">
	
	<input type="hidden" name="invoiceNumber" value="<%=request.getParameter("invoiceNumber")==null?"":request.getParameter("invoiceNumber")%>">
	<input type="hidden" name="InvSource" value="<%=request.getParameter("InvSource")==null?"":request.getParameter("InvSource")%>">
	<input type="hidden" name="strPurchageOrd" value="<%=request.getParameter("strPurchageOrd")==null?"":request.getParameter("strPurchageOrd")%>">
	<input type="hidden" name="strStatus" value="<%=request.getParameter("strStatus")==null?"":request.getParameter("strStatus")%>">
	<input type="hidden" name="iTrxId" value="<%=request.getParameter("iTrxId")==null?"":request.getParameter("iTrxId")%>">
	<input type="hidden" name="iTaxOrig" value="<%=request.getParameter("iTaxOrig")==null?"":request.getParameter("iTaxOrig")%>">
	<input type="hidden" name="iFreightOrig" value="<%=request.getParameter("iFreightOrig")==null?"":request.getParameter("iFreightOrig")%>">
	<input type="hidden" name="iBillToSiteUseId" value="<%=request.getParameter("iBillToSiteUseId")==null?"":request.getParameter("iBillToSiteUseId")%>">
	<input type="hidden" name="iShipToSiteUseId" value="<%=request.getParameter("iShipToSiteUseId")==null?"":request.getParameter("iShipToSiteUseId")%>">
	<input type="hidden" name="controlForm" value="<%=request.getParameter("controlForm")==null?"":request.getParameter("controlForm")%>">
	<input type="hidden" name="hCatId" value="<%=request.getParameter("hCatId")==null?"":request.getParameter("hCatId")%>">
	<input type="hidden" name="hParentCatId" value="<%=request.getParameter("hParentCatId")==null?"":request.getParameter("hParentCatId")%>">
	<input type="hidden" name="hCatCode" value="<%=request.getParameter("hCatCode")==null?"":request.getParameter("hCatCode")%>">
	<input type="hidden" name="hCatDesc" value="<%=request.getParameter("hCatDesc")==null?"":request.getParameter("hCatDesc")%>">

	<input type="hidden" name="strContractNo" value="<%=request.getParameter("strContractNo")==null?"":request.getParameter("strContractNo")%>">
	<input type="hidden" name="strContactNoMod" value="<%=request.getParameter("strContactNoMod")==null?"":request.getParameter("strContactNoMod")%>">
	<input type="hidden" name="strOrderNo" value="<%=request.getParameter("strOrderNo")==null?"":request.getParameter("strOrderNo")%>">
	<input type="hidden" name="strOrderType" value="<%=request.getParameter("strOrderType")==null?"":request.getParameter("strOrderType")%>">
	
	<input type="hidden" name="origName" value="<%=request.getParameter("origName")==null?"":request.getParameter("origName")%>" >
	<input type="hidden" name="origPhNo" value="<%=request.getParameter("origPhNo")==null?"":request.getParameter("origPhNo")%>">
	<input type="hidden" name="origEmailAddress" value="<%=request.getParameter("origEmailAddress")==null?"":request.getParameter("origEmailAddress")%>">

	<input type="hidden" name="origType" value="<%=request.getParameter("origType")==null?"":request.getParameter("origType")%>">
	<input type="hidden" name="sourceType" value="<%=request.getParameter("sourceType")==null?"":request.getParameter("sourceType")%>">
	
	<input type="hidden" name="custName" value="<%=request.getParameter("custName")==null?"":request.getParameter("custName")%>" >
	<input type="hidden" name="custPhNo" value="<%=request.getParameter("custPhNo")==null?"":request.getParameter("custPhNo")%>">
	<input type="hidden" name="custEmailAddress" value="<%=request.getParameter("custEmailAddress")==null?"":request.getParameter("custEmailAddress")%>">

	<input type="hidden" name="strInvoiceType" value="<%=request.getParameter("strInvoiceType")==null?"":request.getParameter("strInvoiceType")%>">
	<input type="hidden" name="strExpirationDate" value="<%=request.getParameter("strExpirationDate")==null?"":request.getParameter("strExpirationDate")%>">
	<input type="hidden" name="strContractStatus" value="<%=request.getParameter("strContractStatus")==null?"":request.getParameter("strContractStatus")%>">
	<input type="hidden" name="strServiceBranch" value="<%=request.getParameter("strServiceBranch")==null?"":request.getParameter("strServiceBranch")%>">
	<input type="hidden" name="strBasePeriod" value="<%=request.getParameter("strBasePeriod")==null?"":request.getParameter("strBasePeriod")%>">
	<input type="hidden" name="strOveragePeriod" value="<%=request.getParameter("strOveragePeriod")==null?"":request.getParameter("strOveragePeriod")%>">
	<input type="hidden" name="strCount" value="<%=request.getParameter("strCount")==null?"":request.getParameter("strCount")%>">
	
	<input type="hidden" name="recurProb" value="<%=request.getParameter("recurProb")==null?"":request.getParameter("recurProb")%>">
	<input type="hidden" name="probType" value="<%=request.getParameter("probType")==null?"":request.getParameter("probType")%>">
	<input type="hidden" name="val1" value="<%=request.getParameter("val1")==null?"":request.getParameter("val1")%>">
	
	<input type="hidden" name="selAcctId" value="<%=request.getParameter("selAcctId")==null?"":request.getParameter("selAcctId")%>">
	<input type="hidden" name="selLocId" value="<%=request.getParameter("selLocId")==null?"":request.getParameter("selLocId")%>">
	<input type="hidden" name="selAcctName" value="<%=request.getParameter("selAcctName")==null?"":request.getParameter("selAcctName")%>">
	<input type="hidden" name="selAcctNum" value="<%=request.getParameter("selAcctNum")==null?"":request.getParameter("selAcctNum")%>">
	<input type="hidden" name="hPath" value="<%=request.getParameter("hPath")==null?"":request.getParameter("hPath")%>">
	<input type="hidden" name="strCatIdP" value="<%=request.getParameter("strCatIdP")==null?"":request.getParameter("strCatIdP")%>">
	<input type="hidden" name="iCountRecords" value="<%=request.getParameter("iCountRecords")==null?"":request.getParameter("iCountRecords")%>">
	<table class="request-service" style="align:center;" cellpadding="1" cellspacing="0" border="0">		
	<tr>
	<td width="10"></td>
		<td colspan="2" class="messageBoxStyle">
			<b>
				<%=strCFSTicketConfirmationM1%> 
				<font color="#FF0000"><%=request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId")%></font>
			</b>
		</td>
	</tr>	
	<tr><td height="10" colspan="3"></td></tr>
	<tr><td height="10" colspan="3"></td></tr>
	<tr>
		<td width="10"></td>
		<td >
			<font class="promptReadOnly"><%=strCFSTicketConfirmationM4%></font>
		</td>
		<td>
		<a class="btn_red" href="javascript:action_func4();">Ticket History</a>			
		</td>
	</tr>
	<tr><td height="10" colspan="3"></td></tr>
	<tr>
		<td width="10"></td>
		<td >
			<font class="promptReadOnly"><%=strCFSTicketConfirmationM5%></font>
		</td>
		<td>
		    <a class="btn_red" href="javascript:action_func2();">Workbench</a>			
		</td>
	</tr>
	<tr><td height="10" colspan="3"></td></tr>
	<tr>
		<td width="10"></td>
		<td >
			<font class="promptReadOnly"><%=strCFSTicketConfirmationM6%></font>
		</td>
		<td>
		<a class="btn_red" href="javascript:action_func3();">New Ticket</a>				
		</td>
	</tr>
	<tr><td height="10" colspan="3"></td></tr>
</table>
</form>
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>