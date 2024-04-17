<!-- $Header: canon_E193_csTicketSummaryAssignController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csTicketSummaryAssignController.jsp - Assignment Controller
 |   
 | DESCRIPTION
 |   It update the assignment of each line.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	09/21/2005
 |
 | HISTORY
 | DATE			  WHO					WHY
 |-------------------------------------------------------
 | 01/29/2016    Mangala Shenoy		Modified for S21 changes
 |
 +=======================================================================--%>
<%@page language="java" %> 

<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_TicketLinesObj" %>
<%@page import="com.canon.oracle.custapp.util.CanonE193CreditRebillAPIUtil" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Assignment" id="objAssignDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_NonBillingIssue" id="objNonBillDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objHeaderLinesList" scope="request" />
<jsp:setProperty name="objHeaderLinesList" property="*" />
 <% String requestURL = request.getRequestURL() == null ? "" : request.getRequestURL().toString(); %>
<% 
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
		String strPageFrom = request.getParameter("hPageFrom");
		System.out.println("######### Inside canon_E193_csTicketSummaryAssignController.jsp");
		Canon_E193_AcctInfoObj objSessionAcctInfo = (Canon_E193_AcctInfoObj)session.getAttribute("objSessionAcctInfo");
		
		String strAcctName = objSessionAcctInfo.getAcctName();
		String strAcctNo = objSessionAcctInfo.getAcctNum();
		String strContactName = objSessionAcctInfo.getContactName();
		String strContactNo = objSessionAcctInfo.getContactNum();
		int iCustAcctId = objSessionAcctInfo.getAcctId();
		
		//Get Org ID
		int iOrgId = objCiSession.getOrgId();
		
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
		String strTicketId = request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId");
		if(!"".equals(strTicketId)) iTicketId = Integer.parseInt(strTicketId);
		int iCount = 0;
		String strCountRecords = request.getParameter("iCountRecords")==null?"":request.getParameter("iCountRecords");
		if(!"".equals(strCountRecords)) iCount = Integer.parseInt(strCountRecords);
		
		String strNoReAssignFlag = request.getParameter("noReAssign")==null?"":request.getParameter("noReAssign");
		String strReAssignFlag = request.getParameter("reAssign")==null?"":request.getParameter("reAssign");
		String strSubmitFlag = request.getParameter("submitFlag")==null?"":request.getParameter("submitFlag");
		
		String strConBillSts = request.getParameter("consolidatedBill")==null?"":request.getParameter("consolidatedBill");
		
		
		String origCheckbox = request.getParameter("origCheckbox")==null?"":request.getParameter("origCheckbox");
		
		String strSwitch = "N";
		ArrayList alAssignList = new ArrayList();
		Canon_E193_TicketLinesObj objLines = null;
		String strNextPage = "canon_E193_csTicketSummary.jsp";
		if("remove".equalsIgnoreCase(strSubmitFlag)) {
			// get lineid
			int iLineId = 0;
			String strLineId = request.getParameter("iLineId")==null?"":request.getParameter("iLineId");
			if(!"".equals(strLineId)) iLineId = Integer.parseInt(strLineId);
			String emailToAddress=request.getParameter("email_to_address");
			String emailSubject=request.getParameter("email_subject");
			String emailBody=request.getParameter("email_body");
			boolean skipEmailNotification="Y".equals(request.getParameter("email_skip_notification"));
			objAssignDao.updateStatus(iOrgId, iLineId, null, "CLOSE", iUserId, "System generated message: Line deleted by user");
			
			objLines = new Canon_E193_TicketLinesObj();
			objLines.setLineId(iLineId);
			objLines.setCreatedBy(iUserId);
			//objLines.setConBillStatus(strConBillSts);
			alAssignList.add(objLines);
			objAssignDao.addAssignments(alAssignList);
			
		}else if("wrapup".equalsIgnoreCase(strSubmitFlag)) {
			System.out.println("Wrapup parameters:"+iOrgId+" ,"+iTicketId+", "+iUserId);
			ArrayList<Canon_E193_LineIdObj> callWrapUplist=(ArrayList<Canon_E193_LineIdObj>)objAssignDao.callWrapUp(iOrgId, iTicketId, 0, "ASSIGNED", iUserId);
			if(origCheckbox != null && origCheckbox.equalsIgnoreCase("Y"))
			{
				objAssignDao.sendEmailNotificationToUser(iTicketId, "OPEN", iUserId);
			}
			for(Canon_E193_LineIdObj callWrapUp:callWrapUplist)
			{
				int lineId=	callWrapUp.getiLineId();
				System.out.println("lineId after wrap up:"+lineId);
				Object[] creditBillResults=objAssignDao.getCrDtls(lineId,iUserId);
				//System.out.println("CreditBillResults Length!!!! "+creditBillResults.length+ " Object!!! "+creditBillResults.toString());
				String custIncdtId=(String)creditBillResults[0];
				String custIncdtLineId=(String)creditBillResults[1];
				String svcCrRebilDescTxt=(String)creditBillResults[2];
				String svcCrRebilSrcNm=(String)creditBillResults[3];
				String custCareRgtnPsnCd=(String)creditBillResults[4];
				int svcCrRebilPk=(Integer)creditBillResults[5];
				String origSvcInvNum=(String)creditBillResults[6];
				String svcContrBrCd=(String)creditBillResults[7];
				String custIncdtAsgPsnCd=(String)creditBillResults[8]; // Newly Added for Defect#17205
				//System.out.println("CustIncdtAsgPsnCd!!! " + custIncdtAsgPsnCd);
				List<Canon_E193_CRMeterObj> meterList=(List<Canon_E193_CRMeterObj>)creditBillResults[9];
				List<Canon_E193_CRBaseObj> baseList=(List<Canon_E193_CRBaseObj>)creditBillResults[10];
				List<Canon_E193_CRPriceObj> priceList=(List<Canon_E193_CRPriceObj>)creditBillResults[11];
				//System.out.println("meterList size:-- "+ meterList.toString());
				//System.out.println("baseList size:");
				//System.out.println("priceList size:--- "+priceList.toString());
				
				if( (meterList != null && !meterList.isEmpty() ) ||  ( baseList != null && !baseList.isEmpty()) || (priceList != null && !priceList.isEmpty()) )
				{
					CanonE193CreditRebillAPIUtil canonE193CreditRebillAPIUtil=new CanonE193CreditRebillAPIUtil();
					request.setAttribute("custIncdtId", custIncdtId);
					request.setAttribute("custIncdtLineId",custIncdtLineId);
					request.setAttribute("svcCrRebilDescTxt",svcCrRebilDescTxt);
					request.setAttribute("svcCrRebilSrcNm",svcCrRebilSrcNm);
					request.setAttribute("custCareRgtnPsnCd",custCareRgtnPsnCd);
					request.setAttribute("origSvcInvNum",origSvcInvNum);
					request.setAttribute("svcCrRebilPk",String.valueOf(svcCrRebilPk));
					request.setAttribute("svcContrBrCd",svcContrBrCd);
					request.setAttribute("custIncdtAsgPsnCd",custIncdtAsgPsnCd); // Newly Added setAttribute for custIncdtPsnCd(for Defect#17205)
					request.setAttribute("meterList", meterList);
					request.setAttribute("priceList", priceList);
					request.setAttribute("baseList", baseList);
					System.out.println("HAIL_CREDIT_REBILL Params Values---"+custIncdtId+ " , "+custIncdtLineId+" , "+svcCrRebilDescTxt);
					System.out.println("HAIL_CREDIT_REBILL Params Values_1--"+svcCrRebilSrcNm+ " , "+custCareRgtnPsnCd+" , "+origSvcInvNum);
					System.out.println("HAIL_CREDIT_REBILL Params Values_2--"+svcCrRebilPk+ " , "+svcContrBrCd+" , "+custIncdtAsgPsnCd);
					//System.out.println("HAIL_CREDIT_REBILL Params Values_3--"+" MeterList-- "+ meterList+ " , "+" PriceList-- "+priceList+" , "+"BaseList-- "+baseList);
					String[] message = canonE193CreditRebillAPIUtil.createCreditRebill(request); // New Parameter added for Mode of Opeeration
					objAssignDao.updateCRInfo(iTicketId, lineId, message[0], message[1]);
				}
				
				objAssignDao.sendEmailNotificationToAsignee(iTicketId, lineId, requestURL, iUserId);
			}
			strNextPage = "canon_E193_csTicketConfirmation.jsp";
		}else{
			if((iCount == 1) && ("reAssigned".equalsIgnoreCase(strReAssignFlag))) {
				System.out.println("######### Inside reAssigned == reAssign");
				
				objLines = new Canon_E193_TicketLinesObj();
				objLines.setLineId(Integer.parseInt(request.getParameter("lineId0")));
				objLines.setDeptCode(request.getParameter("deptCode0"));
				objLines.setRoleId(request.getParameter("roleId0"));
				//Start changes for S21 by Mangala
				objLines.setResourceId(request.getParameter("resourceId0"));
				//End changes for S21 by Mangala
				objLines.setCreatedBy(iUserId);
				alAssignList.add(objLines);
				System.out.println("request.getParameter(lineId0) = " +request.getParameter("lineId0") 
						+ " request.getParameter(deptCode0)= "+request.getParameter("deptCode0")
						+ " request.getParameter(roleId0) = " +request.getParameter("roleId0")
						+ " request.getParameter(resourceId0) = " +request.getParameter("resourceId0"));
			} else if((iCount == 1) && ("normal".equalsIgnoreCase(strNoReAssignFlag))) {
				System.out.println("######### Inside normal == noReAssign");
				objLines = new Canon_E193_TicketLinesObj();
				objLines.setLineId(Integer.parseInt(request.getParameter("lineId0")));
				objLines.setDeptCode(request.getParameter("deptCode0"));
				objLines.setRoleId(request.getParameter("roleId0"));
				//Start changes for S21 by Mangala
				objLines.setResourceId(request.getParameter("resourceId0"));
				//End changes for S21 by Mangala
				objLines.setCreatedBy(iUserId);
				alAssignList.add(objLines);
			}
			else{
				System.out.println("canon_E193_csTicketSummaryAssignController ######### Inside iCount > 1");
				for(int i=0; i<iCount; i++) {
					
					/* System.out.println("canon_E193_csTicketSummaryAssignController ######### isChangedLine :" + request.getParameter("isChangedLine"+i));
					System.out.println("canon_E193_csTicketSummaryAssignController ######### roleId :" + request.getParameter("roleId"+i));
					System.out.println("canon_E193_csTicketSummaryAssignController ######### deptCode :" + request.getParameter("deptCode"+i));
					System.out.println("canon_E193_csTicketSummaryAssignController ######### lineId :" + request.getParameter("lineId"+i));
					System.out.println("canon_E193_csTicketSummaryAssignController ######### resourceId :" + request.getParameter("resourceId"+i)); */
					
					if("YES".equalsIgnoreCase(request.getParameter("isChangedLine"+i))) {
						objLines = new Canon_E193_TicketLinesObj();
						objLines.setLineId(Integer.parseInt(request.getParameter("lineId"+i)));
						objLines.setDeptCode(request.getParameter("deptCode"+i));
						objLines.setRoleId(request.getParameter("roleId"+i));
						//Start changes for S21 by Mangala
						//objLines.setResourceId(Integer.parseInt(request.getParameterValues("resourceId")[i]));
						objLines.setResourceId(request.getParameter("resourceId"+i));		
						//End changes for S21 by Mangala		
						objLines.setCreatedBy(iUserId);
						//objLines.setConBillStatus(strConBillSts);
						alAssignList.add(objLines);
					}
				}
			}
			//System.out.println("canon_E193_csTicketSummaryAssignController ######### iCount" + iCount + " alAssignList size " + alAssignList.size());
			if(iCount > 0 && alAssignList.size() > 0) {
				//System.out.println(" alAssignList " + alAssignList.get(0));
				objAssignDao.addAssignments(alAssignList);
				strSwitch = "Y";
			}
		}
		
		ArrayList alHLList = new ArrayList();
		if(iTicketId > 0) {
			objAssignDao.updateConsolidateSts(iOrgId, iTicketId,strConBillSts);
			alHLList = objNonBillDao.getNonBillHeaderLines(iOrgId, iTicketId);
			
			//System.out.println("~TicketStatusController alHLList ~"+alHLList + " ~~ "+iOrgId + " ~~ "+ iTicketId);
		}
		objHeaderLinesList.setArrayList(alHLList);

%>
		<form name="nonBillingIssueControlForm" method="post">
			<jsp:forward page="<%=strNextPage%>">
				<jsp:param name="hPageFrom" value="TicketSummaryAssignController" />
				<jsp:param name="nextPage" value="" />
				<jsp:param name="iTicketId" value="<%=String.valueOf(iTicketId)%>" />
				<jsp:param name="switch" value="<%=strSwitch%>" />
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