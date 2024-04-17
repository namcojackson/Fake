<!-- $Header: canon_E193_csTicketStatusAddIssuesController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csTicketStatusAddIssuesController.jsp - Add Issue Controller
 |   
 | DESCRIPTION
 |   
 |  
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	10/18/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 

<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_InvoiceObj" %>

<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Invoice" id="objCiInvInfo" scope="page"/>

<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%	
	String strInvNo = request.getParameter("invNum");
	
	//Get Org ID
	int iOrgId = objCiSession.getOrgId();
	String strBillToCustId = request.getParameter("selAcctId");
	int iBillToCustId = -1;
	if((strBillToCustId != null) && !("".equalsIgnoreCase(strBillToCustId)) && !("null".equalsIgnoreCase(strBillToCustId)))
		iBillToCustId = Integer.parseInt(strBillToCustId);
		
	Canon_E193_InvoiceObj objInvoice = objCiInvInfo.getInvoiceDetails(iBillToCustId, iOrgId, strInvNo);
	String invSource = objInvoice.getStrTrxType()==null?"":objInvoice.getStrTrxType();
	long iTrxId = objInvoice.getICustTrxId();
	String strPurchageOrd = objInvoice.getStrPurchageOrder()==null?"":objInvoice.getStrPurchageOrder();
	double iTaxOrig = objInvoice.getITaxOrig();
	String strStatus = objInvoice.getStrStatus()==null?"":objInvoice.getStrStatus();
	//Basudev - Defect ID# 6001576 - Start.
	/* int iBillToSiteUseId = Integer.parseInt(objInvoice.getIBillToSiteUseId());
	int iShipToSiteUseId = Integer.parseInt(objInvoice.getIShipToSiteUseId()); */
	
	String  iBillToSiteUseId = objInvoice.getIBillToSiteUseId().toString();
	String  iShipToSiteUseId = objInvoice.getIShipToSiteUseId().toString();
	System.out.println("iShipToSiteUseId= "+ iShipToSiteUseId + " iBillToSiteUseId= " + iBillToSiteUseId);
	//Basudev - Defect ID# 6001576 - End.
	double iFreightOrig = objInvoice.getIFreightOrig();
	// values for displaying header details of incorrect billing issue.
	String strInvoiceType = objInvoice.getStrAttribute1()==null?"":objInvoice.getStrAttribute1();
	String strExpirationDate = objInvoice.getStrAttribute2()==null?"":objInvoice.getStrAttribute2();
	String strContractStatus = objInvoice.getStrAttribute3()==null?"":objInvoice.getStrAttribute3();
	String strServiceBranch = objInvoice.getStrAttribute4()==null?"":objInvoice.getStrAttribute4();
	String strBasePeriod = objInvoice.getStrAttribute5()==null?"":objInvoice.getStrAttribute5();
	String strOveragePeriod = objInvoice.getStrAttribute6()==null?"":objInvoice.getStrAttribute6();
	String strCount = objInvoice.getStrAttribute7()==null?"":objInvoice.getStrAttribute7();
	String strNextPage = "canon_E193_csBIssueList.jsp";
	String strCatIdP = request.getParameter("strCatIdP");
%>
	<form name="acctControllerForm" method="post">
	<!-- hidden variables -->

	<input type="hidden" name="origName" value="<%=request.getParameter("origName")%>" >
	<input type="hidden" name="origPhNo" value="<%=request.getParameter("origPhNo")%>">
	<input type="hidden" name="origEmailAddress" value="<%=request.getParameter("origEmailAddress")%>">
	<input type="hidden" name="origCheckbox" value="<%=request.getParameter("origCheckbox")%>">
	<input type="hidden" name="origType" value="<%=request.getParameter("origType")%>" >
	<input type="hidden" name="sourceType" value="<%=request.getParameter("sourceType")%>" >
			
	<input type="hidden" name="custName" value="<%=request.getParameter("custName")%>" >
	<input type="hidden" name="custPhNo" value="<%=request.getParameter("custPhNo")%>">
	<input type="hidden" name="custEmailAddress" value="<%=request.getParameter("custEmailAddress")%>">
	
	<input type="hidden" name="recurProb" value="<%=request.getParameter("recurProb")%>">
	<input type="hidden" name="probType" value="<%=request.getParameter("probType")%>">
	<input type="hidden" name="val1" value="<%=request.getParameter("val1")%>">
	
	<input type="hidden" name="selAcctId" value="<%=request.getParameter("selAcctId")%>" >
	<input type="hidden" name="selLocId" value="<%=request.getParameter("selLocId")%>" >
	<input type="hidden" name="selAcctName" value="<%=request.getParameter("selAcctName")%>" >
	<input type="hidden" name="selAcctNum" value="<%=request.getParameter("selAcctNum")%>" >
	
	<input type="hidden" name="hPageFrom" value="TicketStatusAddIssuesController" >
	
	<jsp:forward page="<%=strNextPage%>">
		<jsp:param name="invoiceNumber" value="<%=strInvNo%>"/>
		<jsp:param name="InvSource" value="<%=invSource%>"/>
		<jsp:param name="strPurchageOrd" value="<%=strPurchageOrd%>"/>
		<jsp:param name="strStatus" value="<%=strStatus%>"/>
		<jsp:param name="iTrxId" value="<%=iTrxId%>"/>
		<jsp:param name="iTaxOrig" value="<%=iTaxOrig%>"/>
		<jsp:param name="iFreightOrig" value="<%=iFreightOrig%>"/>
		<jsp:param name="iBillToSiteUseId" value="<%=iBillToSiteUseId%>"/>
		<jsp:param name="iShipToSiteUseId" value="<%=iShipToSiteUseId%>"/>
		<jsp:param name="strInvoiceType" value="<%=strInvoiceType%>"/>
		<jsp:param name="strExpirationDate" value="<%=strExpirationDate%>"/>
		<jsp:param name="strContractStatus" value="<%=strContractStatus%>"/>
		<jsp:param name="strServiceBranch" value="<%=strServiceBranch%>"/>
		<jsp:param name="strBasePeriod" value="<%=strBasePeriod%>"/>
		<jsp:param name="strOveragePeriod" value="<%=strOveragePeriod%>"/>
		<jsp:param name="strCount" value="<%=strCount%>"/>
		<jsp:param name="strCatIdP" value="<%=strCatIdP%>"/>
	</jsp:forward>
	</form>
<%
}
catch(com.canon.oracle.custapp.util.CanonCustAppExceptionUtil eCustExp) {
       String strErr = "-- Exception : " + eCustExp.getStrErrorDesc() + " -- Exception Location :" + eCustExp.getStrErrorLocation();
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + strErr);
}
catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>
