<!-- $Header: canon_E193_csEIAccountController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csEIAccountController.jsp - Account Controller
 |   
 | DESCRIPTION
 |   For a given criteria Retrives Account name, number, CI information and 
 |   stores them in session and forwards it to respective jsp page.
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	08/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 16-Sep-2009  Naveen Khandelwal   MW Project Changes.
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_InvoiceObj" %>
<@page session="true" %>

<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Invoice" id="objCiInvInfo" scope="page"/>

<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objInvArrayList" scope="request" />
<jsp:setProperty name="objInvArrayList" property="*" />

<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%	
System.out.println("hello in canon_E193_csEIAccountController .jsp");
		final String NON_BILLING = "NON_BILLING";
		String strInvNo = request.getParameter("invoiceNumber");
        //System.out.println(" strInvNo=  " + strInvNo);
		String strPageFrom = request.getParameter("hPageFrom");
		String strOrderName = request.getParameter("orderName");
		String strOrderBy = request.getParameter("orderBy");
		
		System.out.println("strInvNo=  " + strInvNo + " strPageFrom= " + strPageFrom );	
		
		if("null".equals(strOrderName) || "".equals(strOrderName)) strOrderName = null;
		if("null".equals(strOrderBy) || "".equals(strOrderBy)) strOrderBy = null;
		
		// Get Bill To Cust Id
		Canon_E193_AcctInfoObj objSessionAcctInfo = (Canon_E193_AcctInfoObj)session.getAttribute("objSessionAcctInfo");
		int iBillToCustId = objSessionAcctInfo.getAcctId();
		//System.out.println("iBillToCustId" + iBillToCustId);
		// Get Invoice Type. 
		String strInvSource = request.getParameter("strInvSource")==null?"":request.getParameter("strInvSource");
		//System.out.println("strInvSource" + strInvSource);
		if(("X1".equalsIgnoreCase(strInvSource)) || ("X2".equalsIgnoreCase(strInvSource)) 
				|| ("null".equalsIgnoreCase(strInvSource))) { strInvSource = null; }
	
		// Get Invoice From and To dates
		String strInvFromDate = request.getParameter("invoiceFrom")==null?"":request.getParameter("invoiceFrom");
		String strInvToDate = request.getParameter("invoiceTo")==null?"":request.getParameter("invoiceTo");
		
		//Get Org ID
		int iOrgId = objCiSession.getOrgId();
		//System.out.println("iOrgId" + iOrgId);
		// Get Page Number
		int iPageNum = 0;
		String strPageNum = request.getParameter("iPageNum");
		if ( (strPageNum != null) && (!("null".equalsIgnoreCase(strPageNum))))
			iPageNum = (int)(Integer.parseInt(strPageNum));
		
		// Get Total Page Numbers
		int iTotPageNum = 0;
		String strTotPageNum = request.getParameter("iTotPageNum");
		if ((strTotPageNum != null) && (!("null".equalsIgnoreCase(strTotPageNum))) )
			iTotPageNum = (int)(Integer.parseInt(strTotPageNum));
		
		String errorMsg = "", InvSource = "", nextPage="";
		String strPurchageOrd = "", strStatus = "";
		long iTrxId = 0;
		double iFreightOrig = 0.0; 
		//Basudev - Defect ID# 6001576 - Start.
		String iBillToSiteUseId = "";
		String  iShipToSiteUseId = "";
		//Basudev - Defect ID# 6001576 - End.
		double iTaxOrig = 0;
		String strInvoiceType = "", strExpirationDate = "", strContractStatus = "", strServiceBranch = "";
		String strBasePeriod = "", strOveragePeriod = "", strCount = "";
		String strContractNo = "", strContactNoMod = "", strOrderNo = "", strOrderType = "";
		String strNewRequest = request.getParameter("newrequest")==null?"":request.getParameter("newrequest");
		String strBilling = request.getParameter("billing")==null?"":request.getParameter("billing");
		String isCancelledInErrTkt = "";
		if(strInvNo == null || "".equals(strInvNo) || "null".equalsIgnoreCase(strInvNo)) {
			
			
			ArrayList alInvList = objCiInvInfo.getInvoices(iBillToCustId, strInvSource, strInvFromDate, strInvToDate, iOrgId, strOrderName, strOrderBy, iPageNum, iTotPageNum);
			System.out.println("in if alInvList : " + alInvList.size());
			if(alInvList != null && alInvList.size() > 0) {
				iPageNum = ((Integer)alInvList.get(0)).intValue();
				alInvList.remove(0);
				iTotPageNum = ((Integer)alInvList.get(0)).intValue();
				alInvList.remove(0);
			}
			
			System.out.println("canon_E193_csEIInvoiceSearch.jsp ----> iBillToCustId " + iBillToCustId + " strInvSource " + strInvSource + " strInvFromDate " + 
					strInvFromDate + " strInvToDate " + strInvToDate + " iOrgId " + iOrgId + " strOrderName " + strOrderName 
					+ " strOrderBy " + strOrderBy + " iPageNum " + iPageNum + " iTotPageNum " + iTotPageNum);
			objInvArrayList.setArrayList(alInvList);
			nextPage = "canon_E193_csEIInvoiceSearch.jsp";
		}else{
			System.out.println(" iBillToCustId= " + iBillToCustId + " iOrgId= "+ iOrgId + " strInvNo= "+ strInvNo);
			Canon_E193_InvoiceObj objInvoice = objCiInvInfo.getInvoiceDetails(iBillToCustId, iOrgId, strInvNo);
			isCancelledInErrTkt = objCiInvInfo.getInvoiceStatus(strInvNo);
			System.out.println("in else isCancelledInErrTkt " + isCancelledInErrTkt);
			if(objInvoice == null) {
				errorMsg = "Invoice number not found";
				nextPage = "canon_E193_csEICheckRequest.jsp";
			}else{
				/*ITG# : Kireet: Retrieve region code from Invoice number: Begin */

				objCiSession = (com.canon.oracle.custapp.custinq.beans.Canon_E193_SystemObj)session.getAttribute("objCiSession");
				String strRegionCode = Canon_E193_RegionCodeDAO.getRegionCode(strInvNo);
				System.out.println("in else strRegionCode : " + strRegionCode );
				
				if(NON_BILLING.equalsIgnoreCase(strRegionCode)){
					//System.out.println("in if : " + strRegionCode );
				  errorMsg = strNonBillingConvertedInv; // "Tickets for converted invoices related to contracts are to be logged in as Non-Billing issues.";
				  nextPage = "canon_E193_csEICheckRequest.jsp";
				}else{
					//System.out.println("in else : " + strRegionCode );
					//  objCiSession.setRegionCode(strRegionCode);
					request.setAttribute("strRegionCode", strRegionCode);
					  //System.out.println("before in else : " + strRegionCode );
					  session.setAttribute("objCiSession",objCiSession);
					 // System.out.println("after in else : " + strRegionCode );
					/*ITG# : Kireet: Retrieve region code from Invoice number: End */

					String strTicketNo = objInvoice.getStrTicketNo()==null?"":objInvoice.getStrTicketNo();
					String strAggContractSts = objInvoice.getAggContractSts()==null?"":objInvoice.getAggContractSts();
					int i = strTicketNo.indexOf("A");
					if(i != -1) {
						String str1 = strTicketNo.substring(0, i);
						int j = str1.lastIndexOf("|");
						if(j != -1) {
							strTicketNo = str1.substring(j+1, i);
							}
						else {
							strTicketNo = str1;
						}
						errorMsg = "Ticket no "+strTicketNo+" is already open for this invoice, please close the open ticket first";
						
						if(strAggContractSts!=null && strAggContractSts.length()>0){
							errorMsg = "Ticket no "+strTicketNo+" is already open for aggregate contract, please close the open ticket first";
							nextPage = "canon_E193_csEICheckRequest.jsp";
						}
						nextPage = "canon_E193_csEICheckRequest.jsp";
					}
					else if(isCancelledInErrTkt!=null && isCancelledInErrTkt.length()>0){
						errorMsg = "Ticket no "+isCancelledInErrTkt+" is in CANCELLED IN ERROR status for this invoice, please process credit rebill manually.";
						nextPage = "canon_E193_csEICheckRequest.jsp";
					}
					else {
						InvSource = objInvoice.getStrTrxType()==null?"":objInvoice.getStrTrxType();
						strContractNo = objInvoice.getStrContNum()==null?"":objInvoice.getStrContNum();
						strContactNoMod = objInvoice.getStrContNumMod()==null?"":objInvoice.getStrContNumMod();
						strOrderNo = objInvoice.getStrOrdrNum()==null?"":objInvoice.getStrOrdrNum();
						strOrderType = objInvoice.getStrOrdrType()==null?"":objInvoice.getStrOrdrType();
						iTrxId = objInvoice.getICustTrxId();
						System.out.println("in else iTrxId : " + iTrxId );
						strPurchageOrd = objInvoice.getStrPurchageOrder()==null?"":objInvoice.getStrPurchageOrder();
						iTaxOrig = objInvoice.getITaxOrig();
						strStatus = objInvoice.getStrStatus()==null?"":objInvoice.getStrStatus();
						/* iBillToSiteUseId = Integer.parseInt(objInvoice.getIBillToSiteUseId());
						iShipToSiteUseId = Integer.parseInt( objInvoice.getIShipToSiteUseId()); */
						//Basudev - Defect ID# 6001576 - Start.
						iBillToSiteUseId  = objInvoice.getIBillToSiteUseId().toString();
						iShipToSiteUseId  = objInvoice.getIShipToSiteUseId().toString(); //iShipToSiteUseId= 3038032
						System.out.println( "iShipToSiteUseId=  "+ iShipToSiteUseId +" iBillToSiteUseId= "+ iBillToSiteUseId);
						//Basudev - Defect ID# 6001576 - End.
						iFreightOrig = objInvoice.getIFreightOrig();
						// values for displaying header details of incorrect billing issue.
						//MW Project Changes - Starts
						if(request.getParameter("strInvoiceType") == null)
							strInvoiceType = objInvoice.getStrAttribute1()==null?"":objInvoice.getStrAttribute1();
						else
							strInvoiceType = request.getParameter("strInvoiceType");
						//MW Project Changes - Ends
						strExpirationDate = objInvoice.getStrAttribute2()==null?"":objInvoice.getStrAttribute2();
						strContractStatus = objInvoice.getStrAttribute3()==null?"":objInvoice.getStrAttribute3();
						strServiceBranch = objInvoice.getStrAttribute4()==null?"":objInvoice.getStrAttribute4();
						strBasePeriod = objInvoice.getStrAttribute5()==null?"":objInvoice.getStrAttribute5();
						strOveragePeriod = objInvoice.getStrAttribute6()==null?"":objInvoice.getStrAttribute6();
						strCount = objInvoice.getStrAttribute7()==null?"":objInvoice.getStrAttribute7();
						nextPage = "canon_E193_csBIssueList.jsp";
					}
			        } //Billing Issues
	
			}
		}		
		if(strInvSource == null) strInvSource = "X2";
			
		strOrderName = strOrderName==null?"null":strOrderName;
		strOrderBy = strOrderBy==null?"null":strOrderBy;
		System.out.println("nextPage : " + nextPage );
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
		<input type="hidden" name="vBillType" value="<%=request.getParameter("vBillType")%>">
		 <input type="hidden" name="consolidateBillNum" value="<%=request.getParameter("consolidateBillNum")==null?"":request.getParameter("consolidateBillNum")%>">
		<input type="hidden" name="hPageFrom" value="EIInvoiceController" >

		<jsp:forward page="<%=nextPage%>">
			<jsp:param name="strInvFromDate" value="<%=strInvFromDate%>" />
			<jsp:param name="strInvToDate" value="<%=strInvToDate%>" />
			<jsp:param name="strInvSource" value="<%=strInvSource%>" />
			<jsp:param name="iPageNum" value="<%=iPageNum%>" />
			<jsp:param name="iTotPageNum" value="<%=iTotPageNum%>" />
			<jsp:param name="orderName" value="<%=strOrderName%>"/>
			<jsp:param name="orderBy" value="<%=strOrderBy%>"/>
			
			<jsp:param name="InvSource" value="<%=InvSource%>"/>
			<jsp:param name="errorMsg" value="<%=errorMsg%>"/>
			<jsp:param name="newrequest" value="<%=strNewRequest%>"/>
			<jsp:param name="billing" value="<%=strBilling%>"/>
			
			<jsp:param name="strPurchageOrd" value="<%=strPurchageOrd%>"/>
			<jsp:param name="strStatus" value="<%=strStatus%>"/>
			<jsp:param name="iTrxId" value="<%=iTrxId%>"/>
			<jsp:param name="iTaxOrig" value="<%=iTaxOrig%>"/>
			<jsp:param name="iFreightOrig" value="<%=iFreightOrig%>"/>
			<jsp:param name="iBillToSiteUseId" value="<%=iBillToSiteUseId%>"/>
			<jsp:param name="iShipToSiteUseId" value="<%=iShipToSiteUseId%>"/>
			
			<jsp:param name="strContractNo" value="<%=strContractNo%>"/>
			<jsp:param name="strContactNoMod" value="<%=strContactNoMod%>"/>
			<jsp:param name="strOrderNo" value="<%=strOrderNo%>"/>
			<jsp:param name="strOrderType" value="<%=strOrderType%>"/>
			
			<jsp:param name="strInvoiceType" value="<%=strInvoiceType%>"/>
			<jsp:param name="strExpirationDate" value="<%=strExpirationDate%>"/>
			<jsp:param name="strContractStatus" value="<%=strContractStatus%>"/>
			<jsp:param name="strServiceBranch" value="<%=strServiceBranch%>"/>
			<jsp:param name="strBasePeriod" value="<%=strBasePeriod%>"/>
			<jsp:param name="strOveragePeriod" value="<%=strOveragePeriod%>"/>
			<jsp:param name="strCount" value="<%=strCount%>"/>
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