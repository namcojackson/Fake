<!-- $Header: canon_E193_csTicketSummaryNBController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csQuickTicketController.jsp - Quick Ticket 
 |   
 | DESCRIPTION
 |   For a given criteria insert records and forwards it to respective jsp page.
 |
 | AUTHOR
 |	Hema 
 |
 | CREATION DATE
 |	08/22/2013
 |
 | Date				By				Decription
 |--------------------------------------------------------
 |01/29/2016   Mangala Shenoy		Modified for S21 Changes
 |
 +=======================================================================--%>
<%@page language="java" %> 

<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj" id="objHeaders" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj" id="objLines" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_NonBillingIssue" id="objNonBillDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_QuickTicketDAO" id="objQuickTicketDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Assignment" id="objAssignDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_AcctInfo" id="objCiAcctInfo" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objHeaderLinesList" scope="request" />
<jsp:setProperty name="objHeaderLinesList" property="*" />
 <% String requestURL = request.getRequestURL() == null ? "" : request.getRequestURL().toString(); %>
<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
	int iOrgId = objCiSession.getOrgId();
	String strRegionCode = (String)objCiSession.getRegionCode();
	
	System.out.println(" strRegionCode" + strRegionCode);
	// get userId	
	String iUserId = objCiSession.getUserId();
	// get resource id
	//Start Changes for S21
	//int iResourceId = objCiSession.getResourceId();
    String iResourceId = objCiSession.getResourceId();			
	//End Changes for S21
	
	// get ticket no
	int iTicketId = 0;
	int iLineId = 0;
		String strAcctNo = request.getParameter("acctNum")==null?"":request.getParameter("acctNum");
		String strPartyName = request.getParameter("partyName")==null?"":request.getParameter("partyName");
		String strCustAcctId  = request.getParameter("custAcctId")==null?"":request.getParameter("custAcctId");
		String strTrxType = request.getParameter("trxType")==null?"":request.getParameter("trxType").trim();
		String strOrigName = request.getParameter("origName")==null?"":request.getParameter("origName").trim();
		String strFirstName = request.getParameter("origFirstName")==null?"":request.getParameter("origFirstName");
		String strLastName = request.getParameter("origLastName")==null?"":request.getParameter("origLastName");
		//String strPhoneNum = request.getParameter("origPhCd")==null?"":request.getParameter("origPhCd");
		String strEmailAddress = request.getParameter("origEmailAddress")==null?"":request.getParameter("origEmailAddress");
		String strProbAccount = request.getParameter("origType")==null?"":request.getParameter("origType");
		String strReasonCdDesc = request.getParameter("reasonCdDesc");
		String strOrigPhCd = request.getParameter("origPhCd")==null?"":request.getParameter("origPhCd");
		String strOrigPhNo = request.getParameter("origPhNo")==null?"":request.getParameter("origPhNo");
		String strOrigPhNo2 = request.getParameter("origPhNo2")==null?"":request.getParameter("origPhNo2");
		String strOrigExtNo = request.getParameter("origPhNoExt")==null?"":request.getParameter("origPhNoExt");
		String strSeverity = request.getParameter("severity");
		String strNotes = request.getParameter("notes")==null?"":request.getParameter("notes");
		String strFormatPhNo="";
		String strCatId = "";
		String strParentCatId="";
		int iCustAcctId = -1;
		if((!"".equalsIgnoreCase(strOrigPhCd)) && (!"null".equalsIgnoreCase(strOrigPhCd))){
			strFormatPhNo = strOrigPhCd + "." + strOrigPhNo + "." + strOrigPhNo2;
		    
			strFormatPhNo = strFormatPhNo + " Ext " + strOrigExtNo;
			//System.out.println("StrFormatPhNo= "+ strFormatPhNo);
		}
		System.out.println("strCustAcctId= "+ strCustAcctId);
		if("".equalsIgnoreCase(strCustAcctId) || "null".equalsIgnoreCase(strCustAcctId)){
			// DO Nothing
		}else{
			iCustAcctId = Integer.parseInt(strCustAcctId);
			//System.out.println("iCustAcctId= "+ iCustAcctId);
			Canon_E193_AcctInfoObj objSessionAcctInfo = (Canon_E193_AcctInfoObj)objCiAcctInfo.getAcctInfo(strPartyName,strAcctNo,iCustAcctId,iOrgId);
			//System.out.println("strCustAcctId iCustAcctId= "+ iCustAcctId);
			objSessionAcctInfo.setContactName(strOrigName);
			objSessionAcctInfo.setContactNum(strFormatPhNo);
			objSessionAcctInfo.setAcctId(iCustAcctId);
			objSessionAcctInfo.setAcctName(strPartyName);
			objSessionAcctInfo.setAcctNum(strAcctNo);
			session.setAttribute("objSessionAcctInfo",objSessionAcctInfo);
		}
		//System.out.println(" strTrxType : "+strTrxType+" strRegionCode : "+strRegionCode+" strAccNum : " + strAcctNo+" strPartyName : "+strPartyName+" strCustAcctId : "+strCustAcctId+" strOrigName : "+strOrigName);
		//System.out.println(" iOrgId : "+iOrgId+" strEmailAddress : " + strEmailAddress+" strProbAccount : "+strProbAccount+" strReasonCdDesc : "+ strReasonCdDesc+" strSeverity : "+strSeverity);
	
			System.out.println("Before catId : " + strRegionCode);
		if(strRegionCode == null || "null".equals(strRegionCode) || "".equals(strRegionCode)){
			strRegionCode = "EAST_REGION";
		} 
		String[] code = objQuickTicketDao.getQuickTicketCatId(strRegionCode);
		if(code!=null && code.length>0){
			strCatId = code[0];
			strParentCatId	=	code[1];
			
			objHeaders.setCatId(Integer.parseInt(strParentCatId));
			//System.out.println("strCatId : " + strCatId + " strParentCatId : " + strParentCatId);
		}
		
		objHeaders.setStatus("UNASSIGNED");
		objHeaders.setRecurring("N");
		objHeaders.setBillingIssue("N");
		objHeaders.setOrgId(iOrgId);
		objHeaders.setAttribute6(strRegionCode);
		objHeaders.setCustAcctId(iCustAcctId);
		objHeaders.setCustomerName(strPartyName);
		objHeaders.setCustomerNo(strAcctNo);
		objHeaders.setOrigName(strOrigName);
		objHeaders.setOrigPhNo(strFormatPhNo);
		objHeaders.setOrigEmail(strEmailAddress);
		objHeaders.setCustContact(strOrigName);
		objHeaders.setCustPhNo(strFormatPhNo);
		objHeaders.setCustEmail(strEmailAddress);
		objHeaders.setJtfNotesFlag("Y");
		objHeaders.setCreatedBy(iUserId);
		objHeaders.setCreatedByResId(iResourceId);
		
		objLines.setOrgId(iOrgId);
		if(strCatId!=null && strCatId!="null"){
			objLines.setCatId(Integer.parseInt(strCatId));
		}
		objLines.setLineStatus("UNASSIGNED");
		objLines.setSeverity(strSeverity);
		objLines.setReasonCd("CSR_E193_QUICKTICKET");
		objLines.setReason(strReasonCdDesc);
		objLines.setJtfNotesFlag("Y");
		objLines.setCreatedBy(iUserId);
		objLines.setNotes(strNotes);
		//System.out.println("Before header lines..");
		iTicketId = objQuickTicketDao.createQuickTicket(objHeaders, objLines, iOrgId, iUserId, iResourceId, strRegionCode, strCatId, requestURL);
		String strSwitch = "Y";
		System.out.println("iTicketId 2 : " + iTicketId);
%>
		<form name="nonBillingIssueControlForm" method="post">
			<jsp:forward page="canon_E193_csTicketConfirmation.jsp">
				<jsp:param name="action" value="quickTicket" />
				<jsp:param name="disabledAddIssueBtn" value="true" />
				<jsp:param name="hPageFrom" value="csQuickTicketController" />
				<jsp:param name="nextPage" value="" />
				<jsp:param name="iTicketId" value="<%=String.valueOf(iTicketId)%>" />
				<jsp:param name="switch" value="<%=strSwitch%>" />
				<jsp:param name="origType" value="<%=strProbAccount%>" />
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