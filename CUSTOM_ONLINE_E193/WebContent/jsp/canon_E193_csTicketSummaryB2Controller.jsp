<!-- $Header: canon_E193_csTicketSummaryB2Controller.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csTicketSummaryB2Controller.jsp - billing issue Controller.
 |   
 | DESCRIPTION
 |   For a given criteria insert records and forwards it to respective jsp page.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	09/29/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 18-Dec-2006    Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 | 29-Jan-2016    Mangala Shenoy	 Modified for S21 changes
 |
 +=======================================================================--%>
<%@page language="java" %> 

<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketSubLinesObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_BillingIssue" id="objBillDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_NonBillingIssue" id="objNonBillDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objHeaderLinesList" scope="request" />
<jsp:setProperty name="objHeaderLinesList" property="*" />
<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
		String strPageFrom = request.getParameter("hPageFrom");
		
		Canon_E193_AcctInfoObj objSessionAcctInfo = (Canon_E193_AcctInfoObj)session.getAttribute("objSessionAcctInfo");
		
		String strAcctName = objSessionAcctInfo.getAcctName();
		String strAcctNo = objSessionAcctInfo.getAcctNum();
		String strContactName = objSessionAcctInfo.getContactName();
		String strContactNo = objSessionAcctInfo.getContactNum();
		int iCustAcctId = objSessionAcctInfo.getAcctId();
		String strPOReqFlag = objSessionAcctInfo.getPORequiredFlag();
		
		//Get Org ID
		int iOrgId = objCiSession.getOrgId();
		
		/* ITG# 73987 : Begin */	
		String strRegionCode = (String)objCiSession.getRegionCode();	
		/* ITG# 73987 : End */
		//Start changes for S21 by Mangala
		// get userId
		//int iUserId = objCiSession.getUserId();
		String iUserId = objCiSession.getUserId();
		// get resource id
		//int iResourceId = objCiSession.getResourceId();
		String iResourceId = objCiSession.getResourceId();
		//End changes for S21 by Mangala
		// get ticket no
		int iTicketId = 0;
		int iLineId = 0;
		String strTicketId = request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId");
		if(!"".equals(strTicketId)) iTicketId = Integer.parseInt(strTicketId);
		String strLineId = request.getParameter("iLineId")==null?"":request.getParameter("iLineId");
		if(!"".equals(strLineId)) iLineId = Integer.parseInt(strLineId);
		
		String strReadData = request.getParameter("readData")==null?"":request.getParameter("readData");
		String strFlag = request.getParameter("flag")==null?"":request.getParameter("flag");
		
		String strReasonCd = request.getParameter("hReasonCd");
		String strReasonCdDesc = request.getParameter("reasonCdDesc");
		String strNotes = request.getParameter("notes");
		String strCatId = request.getParameter("hCatId");
		String strParentCatId = request.getParameter("hParentCatId");
		String strCatCode = request.getParameter("hCatCode");
		String strCatDesc = request.getParameter("hCatDesc");
		String strSeverity = request.getParameter("severity");
		String strInvNo = request.getParameter("invoiceNumber");
		// tax
		String strTaxOrig = request.getParameter("iTaxOrig");
		String strExpTaxAmt = request.getParameter("expTaxAmt")==null?"":request.getParameter("expTaxAmt");
		String strNewTaxAmt = request.getParameter("newTaxAmt")==null?"":request.getParameter("newTaxAmt");
		String strTaxExemption = request.getParameter("taxExemption")==null?"":request.getParameter("taxExemption");
		String strTaxCharge = request.getParameter("taxCharge")==null?"":request.getParameter("taxCharge");
		//po
		String strPurchageOrd = request.getParameter("strPurchageOrd");
		String strNewPurchageOrd = request.getParameter("newPurchageOrd");
		//Freight
		String strFreightOrig = request.getParameter("iFreightOrig");
		String strNewFreight = request.getParameter("expFreightAmt");

		Canon_E193_TicketLinesObj[] objLines = new Canon_E193_TicketLinesObj[1];
		Canon_E193_TicketSubLinesObj[] objSubLines = new Canon_E193_TicketSubLinesObj[1];
			
		if("".equals(strReadData) || "null".equals(strReadData)) {
			String strOrigName = request.getParameter("origName");
			String strOrigPhNo = request.getParameter("origPhNo");
			String strOrigEmailAddress = request.getParameter("origEmailAddress");
			String strorigCheckbox = request.getParameter("origCheckbox");
			String strOrigType = request.getParameter("origType");
			String strSourceType = request.getParameter("sourceType");
			String strCustName = request.getParameter("custName");
			String strCustPhNo = request.getParameter("custPhNo");
			String strCustEmailAddress = request.getParameter("custEmailAddress");
			String strRecurProb = request.getParameter("recurProb");
			String strProbType = request.getParameter("probType");
			String strVal1 = request.getParameter("val1");
			String strSelAcctId = request.getParameter("selAcctId");
			String strSelLocId = request.getParameter("selLocId");
			String strSelAcctName = request.getParameter("selAcctName");
			String strSelAcctNum = request.getParameter("selAcctNum");
			String strContractNo = request.getParameter("strContractNo")==null?"":request.getParameter("strContractNo");
			String strContactNoMod = request.getParameter("strContactNoMod")==null?"":request.getParameter("strContactNoMod");
			String strOrderNo = request.getParameter("strOrderNo")==null?"":request.getParameter("strOrderNo");
			String strOrderType = request.getParameter("strOrderType")==null?"":request.getParameter("strOrderType");
			String strServiceBranch = request.getParameter("strServiceBranch")==null?"":request.getParameter("strServiceBranch");
			
			Canon_E193_TicketHeaderObj[] objHeaders = new Canon_E193_TicketHeaderObj[1];

			if("".equals(strTicketId)) {
				objHeaders[0] = new Canon_E193_TicketHeaderObj();
				// setting header values to object
				objHeaders[0].setCatId(Integer.parseInt(strParentCatId));
				objHeaders[0].setStatus("UNASSIGNED");
				if("no".equalsIgnoreCase(strRecurProb))
					objHeaders[0].setRecurring("N");
				else objHeaders[0].setRecurring("Y");
				objHeaders[0].setBillingIssue("Y");
				objHeaders[0].setOrgId(iOrgId);
				
				/* ITG# 73987 : Begin */
				objHeaders[0].setAttribute6(strRegionCode);
				/* ITG# 73987 : End */
				
				objHeaders[0].setCustAcctId(iCustAcctId);
				objHeaders[0].setCustomerName(strAcctName);
				objHeaders[0].setCustomerNo(strAcctNo);
				objHeaders[0].setOrigName(strOrigName);
				objHeaders[0].setOrigPhNo(strOrigPhNo);
				objHeaders[0].setOrigEmail(strOrigEmailAddress);
				objHeaders[0].setsendEmailFlag(strorigCheckbox);
				objHeaders[0].setOrigType(strOrigType);
				// Set Customer Source Type : Start
				objHeaders[0].setAttribute9(strSourceType);
				// Set Customer Source Type : End
				
				if("customer".equalsIgnoreCase(strOrigType)) {
					objHeaders[0].setCustContact(strOrigName);
					objHeaders[0].setCustPhNo(strOrigPhNo);
					objHeaders[0].setCustEmail(strOrigEmailAddress);
				}else{
					objHeaders[0].setCustContact(strCustName);
					objHeaders[0].setCustPhNo(strCustPhNo);
					objHeaders[0].setCustEmail(strCustEmailAddress);
				}
				objHeaders[0].setJtfNotesFlag("N");
				objHeaders[0].setCreatedBy(iUserId);
				objHeaders[0].setCreatedByResId(iResourceId);
				objHeaders[0].setInvoiceNo(strInvNo);
				objHeaders[0].setContractNo(strContractNo);
				objHeaders[0].setContractModifier(strContactNoMod);
				if(strOrderNo != null && !"".equals(strOrderNo) && !"null".equals(strOrderNo)) 
					objHeaders[0].setOrderNo(Integer.parseInt(strOrderNo));
				objHeaders[0].setOrderType(strOrderType);
				if(!"".equals(strServiceBranch) && !"null".equals(strServiceBranch)) 
					objHeaders[0].setAttribute1(strServiceBranch);
				if(!"".equals(strPOReqFlag) && !"null".equals(strPOReqFlag)) 
					objHeaders[0].setAttribute2(strPOReqFlag);
			}
			
			// setting lines values to object
			objLines[0] = new Canon_E193_TicketLinesObj();
			objLines[0].setOrgId(iOrgId);
			objLines[0].setCatId(Integer.parseInt(strCatId));
			objLines[0].setLineStatus("UNASSIGNED");
			objLines[0].setSeverity(strSeverity);
			objLines[0].setReasonCd(strReasonCd);
			objLines[0].setReason(strReasonCdDesc);
			objLines[0].setJtfNotesFlag("Y");
			objLines[0].setCreatedBy(iUserId);
			
			//setting sublines
			objSubLines[0] = new Canon_E193_TicketSubLinesObj();
			objSubLines[0].setNewFlag("N");
			objSubLines[0].setCrFlag("N");
			objSubLines[0].setCompanyMoveFlag("N");
			objSubLines[0].setCancelEquipFlag("N");
			objSubLines[0].setSerialNo("INV_LEVEL");
			objSubLines[0].setObjectType("INV_NUMBER");
			objSubLines[0].setObjectValue(strInvNo);
			if("TAX".equalsIgnoreCase(strCatCode)) {
				objSubLines[0].setCurrentValue(strTaxOrig);
				if("Y".equals(strTaxCharge)) {
					objSubLines[0].setTaxExamption(strTaxExemption);
					objSubLines[0].setNewValue(strExpTaxAmt);
				}else{
					objSubLines[0].setTaxExamption("TAX_NOT_CHARGED");
					objSubLines[0].setNewValue(strNewTaxAmt);
				}
			}else if("PO".equalsIgnoreCase(strCatCode)) {
				objSubLines[0].setCurrentValue(strPurchageOrd);
				objSubLines[0].setNewValue(strNewPurchageOrd);
			}else if("FREIGHT".equalsIgnoreCase(strCatCode)) {
				objSubLines[0].setCurrentValue(strFreightOrig);
				objSubLines[0].setNewValue(strNewFreight);
			}
			
			if("".equals(strTicketId)) {
				iTicketId = objBillDao.addBillHeaderLineSubLines(objHeaders, objLines, objSubLines, strNotes);
			}else{
				objLines[0].setTicketId(iTicketId);
				objBillDao.addBillSubLines(iTicketId, iOrgId, objLines, objSubLines, strNotes);
			}
		}
		System.out.println(" in if after call to addBillHeaderLineSubLines " + iTicketId);
		if("update".equals(strFlag)) {
			// setting lines values to object
			objLines[0] = new Canon_E193_TicketLinesObj();
			objLines[0].setLineId(iLineId);
			objLines[0].setTicketId(iTicketId);
			objLines[0].setOrgId(iOrgId);
			objLines[0].setCatId(Integer.parseInt(strCatId));
			objLines[0].setSeverity(strSeverity);
			objLines[0].setReasonCd(strReasonCd);
			objLines[0].setReason(strReasonCdDesc);
			objLines[0].setCreatedBy(iUserId);
			objLines[0].setNotes(strNotes);
			
			//setting sublines
			objSubLines[0] = new Canon_E193_TicketSubLinesObj();
			objSubLines[0].setNewFlag("N");
			objSubLines[0].setCrFlag("N");
			objSubLines[0].setCompanyMoveFlag("N");
			objSubLines[0].setCancelEquipFlag("N");
			objSubLines[0].setSerialNo("INV_LEVEL");
			objSubLines[0].setObjectType("INV_NUMBER");
			objSubLines[0].setObjectValue(strInvNo);
			if("TAX".equalsIgnoreCase(strCatCode)) {
				objSubLines[0].setTaxExamption(strTaxExemption);
				objSubLines[0].setCurrentValue(strTaxOrig);
				objSubLines[0].setNewValue(strExpTaxAmt);
			}else if("PO".equalsIgnoreCase(strCatCode)) {
				objSubLines[0].setCurrentValue(strPurchageOrd);
				objSubLines[0].setNewValue(strNewPurchageOrd);
			}else if("FREIGHT".equalsIgnoreCase(strCatCode)) {
				objSubLines[0].setCurrentValue(strFreightOrig);
				objSubLines[0].setNewValue(strNewFreight);
			}
			objBillDao.updateLineSubLine(iTicketId, iOrgId, objLines, objSubLines, strNotes);
		}

		ArrayList alHLList = new ArrayList();
		if(iTicketId > 0) {
			alHLList = objNonBillDao.getNonBillHeaderLines(iOrgId, iTicketId);
			//System.out.println("~TicketSummaryB2Controller alHLList ~"+alHLList + " ~~ "+iOrgId + " ~~ "+ iTicketId);
		}
		objHeaderLinesList.setArrayList(alHLList);

%>
		<form name="nonBillingIssueControlForm" method="post">
			<jsp:forward page="canon_E193_csTicketSummary.jsp">
				<jsp:param name="hPageFrom" value="TicketSummaryB2Controller" />
				<jsp:param name="nextPage" value="" />
				<jsp:param name="iTicketId" value="<%=String.valueOf(iTicketId)%>" />
				<jsp:param name="controlForm" value="B"/>
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