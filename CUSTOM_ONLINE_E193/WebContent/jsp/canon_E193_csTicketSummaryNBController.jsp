<!-- $Header: canon_E193_csTicketSummaryNBController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csTicketSummaryNBController.jsp - Non billing issue Controller.
 |   
 | DESCRIPTION
 |   For a given criteria insert records and forwards it to respective jsp page.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	09/19/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 18-Dec-2006    Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 | 29-Jan-2016    Mangala Shenoy	 Modified for S21 changes
 |
 +=======================================================================--%>
<%@page language="java" %> 

<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketHeaderObj" id="objHeaders" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj" id="objLines" scope="page"/>
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
		String strCatDesc = request.getParameter("hCatDesc");
		String strSeverity = request.getParameter("severity");

		if("".equals(strReadData) || "null".equals(strReadData)) {
			String strOrigName = request.getParameter("origName");
			String strOrigPhNo = request.getParameter("origPhNo");
			String strOrigEmailAddress = request.getParameter("origEmailAddress");
			String strorigCheckbox = request.getParameter("origCheckbox")==null?"":request.getParameter("origCheckbox");
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
			
			if("".equals(strTicketId)) {
				// setting header values to object
				objHeaders.setCatId(Integer.parseInt(strParentCatId));
				objHeaders.setStatus("UNASSIGNED");
				if("no".equalsIgnoreCase(strRecurProb))
					objHeaders.setRecurring("N");
				else objHeaders.setRecurring("Y");
				objHeaders.setBillingIssue("N");
				objHeaders.setOrgId(iOrgId);
				/* ITG# 73987 : Begin */
				objHeaders.setAttribute6(strRegionCode);
				/* ITG# 73987 : End */
				
				objHeaders.setCustAcctId(iCustAcctId);
				objHeaders.setCustomerName(strAcctName);
				objHeaders.setCustomerNo(strAcctNo);
				objHeaders.setOrigName(strOrigName);
				objHeaders.setOrigPhNo(strOrigPhNo);
				objHeaders.setOrigEmail(strOrigEmailAddress);
				objHeaders.setsendEmailFlag(strorigCheckbox);
				objHeaders.setOrigType(strOrigType);
				// Set Customer Source Type : Start
                objHeaders.setAttribute9(strSourceType);
               // Set Customer Source Type : End  
				if("customer".equalsIgnoreCase(strOrigType)) {
					objHeaders.setCustContact(strOrigName);
					objHeaders.setCustPhNo(strOrigPhNo);
					objHeaders.setCustEmail(strOrigEmailAddress);
				}else{
					objHeaders.setCustContact(strCustName);
					objHeaders.setCustPhNo(strCustPhNo);
					objHeaders.setCustEmail(strCustEmailAddress);
				}
				objHeaders.setJtfNotesFlag("N");
				objHeaders.setCreatedBy(iUserId);
				objHeaders.setCreatedByResId(iResourceId);
			}
			
			// setting lines values to object
			objLines.setOrgId(iOrgId);
			objLines.setCatId(Integer.parseInt(strCatId));
			objLines.setLineStatus("UNASSIGNED");
			objLines.setSeverity(strSeverity);
			objLines.setReasonCd(strReasonCd);
			objLines.setReason(strReasonCdDesc);
			objLines.setJtfNotesFlag("Y");
			objLines.setCreatedBy(iUserId);
			objLines.setNotes(strNotes);
			
			if("".equals(strTicketId)) {
				iTicketId = objNonBillDao.addNonBillHeaderLines(objHeaders, objLines);
				 System.out.println("!!!!!!!!!! iTicketId = "+iTicketId);
			}else{
				objLines.setTicketId(iTicketId);
				objNonBillDao.addNonBillLines(objLines);
			}
		}

		if("update".equals(strFlag)) {
			// setting lines values to object
			objLines.setLineId(iLineId);
			objLines.setTicketId(iTicketId);
			objLines.setOrgId(iOrgId);
			objLines.setCatId(Integer.parseInt(strCatId));
			objLines.setSeverity(strSeverity);
			objLines.setReasonCd(strReasonCd);
			objLines.setReason(strReasonCdDesc);
			objLines.setCreatedBy(iUserId);
			objLines.setNotes(strNotes);
			objNonBillDao.updateLines(objLines);
		}

		ArrayList alHLList = new ArrayList();
		System.out.println("!!!!!!!!!! Before calling alHLList = ");
		if(iTicketId > 0) {
			alHLList = objNonBillDao.getNonBillHeaderLines(iOrgId, iTicketId);
			//System.out.println("~TicketSummaryNBController alHLList ~"+alHLList + " ~~ "+iOrgId + " ~~ "+ iTicketId);
		}
		objHeaderLinesList.setArrayList(alHLList);

%>
		<form name="nonBillingIssueControlForm" method="post">
			<jsp:forward page="canon_E193_csTicketSummary.jsp">
				<jsp:param name="hPageFrom" value="TicketSummaryNBController" />
				<jsp:param name="nextPage" value="" />
				<jsp:param name="iTicketId" value="<%=String.valueOf(iTicketId)%>" />
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