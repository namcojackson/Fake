<!-- $Header: canon_E193_csTicketStatusController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csTicketStatusController.jsp - Ticket Status Controller
 |   
 | DESCRIPTION
 |   
 |   
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	08/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |----------------------------------------------------------
 |01/29/2016   Mangala Shenoy	   Modified for S21 Changes.
 |
 +=======================================================================--%>
 
<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@page import="java.lang.*" %>

<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.*"%>
<%@page import="com.canon.oracle.custapp.util.*" %>
<%@page import="com.canon.oracle.custapp.util.CanonE193CreditRebillAPIUtil" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Ticket" id="objTicket" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objHeaderLinesDtls" scope="request" />
<jsp:setProperty name="objHeaderLinesDtls" property="*" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objHeaderLinesList" scope="request" />
<jsp:setProperty name="objHeaderLinesList" property="*" />
 <% String requestURL = request.getRequestURL() == null ? "" : request.getRequestURL().toString(); %>
<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%	String strTicketId = request.getParameter("ticketId");
	System.out.println("!!!!!! Ticket Staus Controller strTicketId : "+strTicketId );
	//System.out.println("!!!!!! in Ticket Staus Controller :" );
	//System.out.println("!!!!!! TicketClose Flage : "+strTicketId );
	
	int iTktId = -1;
	if(strTicketId != null && !("".equals(strTicketId)) && !("null".equalsIgnoreCase(strTicketId)) )
		iTktId = Integer.parseInt(strTicketId);
	//System.out.println("!!!!!! iTktId = "+iTktId );
	if (iTktId != -1) {	
		//System.out.println("!!!!!! In If 1" );
		//Get Org ID
		int iOrgId = objCiSession.getOrgId();
		//Start Changes for S21 by Mangala
		//Get User Id
		//int iUserId = objCiSession.getUserId();
		String iUserId = objCiSession.getUserId();
		//Get Resource Id
		//int iResId = objCiSession.getResourceId();
		String iResId = objCiSession.getResourceId();
		//End Changes for S21 by Mangala
		// Check if ticket is updated 
		String strIsChanged = request.getParameter("hSaveChanges");
		
		if("YES".equalsIgnoreCase(strIsChanged)) {
			//System.out.println("!!!!!! In If YES: " );
			String strHdrStatus = request.getParameter("hHdrStatus");
			String strLineId = request.getParameter("hLineId");
			System.out.println("!!!!!! iLineId = "+strLineId );
			String strhrescode = request.getParameter("hrescode");
			
			int iLineId = -1;
			if(strLineId != null && !("".equals(strLineId)) && !("null".equalsIgnoreCase(strLineId)) )
				iLineId = Integer.parseInt(strLineId);
			//System.out.println("!!!!!! iLineId = "+iLineId );
			String strLineStatus = request.getParameter("hLineStatus");
			String strLineSeverity = request.getParameter("hLineSeverity");
			String strLineDept = request.getParameter("hLineDept");
			String strLineRole = request.getParameter("hLineRole");
			String strResolCd =request.getParameter("rescd");
			String iRoleId = "";
			if(strLineRole != null && !("".equals(strLineRole)) && !("null".equalsIgnoreCase(strLineRole)) )
				iRoleId = strLineRole;
			String strLineResource = request.getParameter("hLineResource");
			//System.out.println("!!!!!! strLineResource = "+strLineResource );
			//Start changes for S21 by Mangala
			//int iResourceId = 0;
			String iResourceId = "";		
			//End changes for S21 by Mangala
			if(strLineResource != null && !("".equals(strLineResource)) && !("null".equalsIgnoreCase(strLineResource)) )
				//Start changes for S21 by Mangala
				//iResourceId = Integer.parseInt(strLineResource);
				iResourceId = strLineResource;	
				//System.out.println("!!!!!! iResourceId = "+iResourceId );
				//End changes for S21 by Mangala		
			String strLineNotes = request.getParameter("hLineNotes");
			//System.out.println("!!!!!! strLineNotes = "+strLineNotes );
			// Object to invoke update methods
			Canon_E193_Assignment updateHdrLineStsAssign = new Canon_E193_Assignment();
			//System.out.println("!!!!!! strLineNotes = "+strLineNotes );
			System.out.println("!!!!!! strHdrStatus = "+strHdrStatus );
			
			/* 
			 * Cancel CreditRebill For Close Ticket
			 */
			if("CLOSE".equals(strHdrStatus)){
				System.out.println("!!!!!! CLOSE Ticket need to Cancel  : " );
				int svcCrRebilPk =0;
				int svcCrRebilDtPk = 0; 
				//objHeaderLinesDtls.setIsCloseTicketFlag(true);
				ArrayList alTicketStatus = new ArrayList();
				alTicketStatus= updateHdrLineStsAssign.cancelCreditRebillForCloseTicket(iTktId);  // For Testing
				if(alTicketStatus != null && alTicketStatus.size() > 0) {
					svcCrRebilPk = ((Integer)alTicketStatus.get(0)).intValue();
					svcCrRebilDtPk = ((Integer)alTicketStatus.get(1)).intValue();
					//System.out.println("!!!!!! In If CLOSE Status_11 : " + svcCrRebilPk + " --- " + svcCrRebilDtPk);
				}
				CanonE193CreditRebillAPIUtil canonE193CreditRebillAPIUtil=new CanonE193CreditRebillAPIUtil();
				/*
				* Donot call cancelCreditRebill if svcCrRebilPk and svcCrRebilDtPk both are zero
				*/
				if(svcCrRebilPk!=0 && svcCrRebilDtPk!=0){
					request.setAttribute("custIncdtId", String.valueOf(iTktId));
					request.setAttribute("svcCrRebilPk",String.valueOf(svcCrRebilPk));
					request.setAttribute("svcCrRebilDtPk",String.valueOf(svcCrRebilDtPk));
					canonE193CreditRebillAPIUtil.cancelCreditRebill(request);
				}
			} // End OF Close Senario 
			if(strHdrStatus != null && !("".equals(strHdrStatus)) && !("null".equalsIgnoreCase(strHdrStatus))) 
			{
				System.out.println("!!!!!! In if 3:- "+strHdrStatus  );
				//0. Header Status update
				updateHdrLineStsAssign.headerUpdate(iOrgId,iTktId,strHdrStatus,iUserId,strhrescode);
				if(strLineNotes != null && !("".equals(strLineNotes)) && !("null".equalsIgnoreCase(strLineNotes)))
				{
					String strLineIdConCat = request.getParameter("hLineIdConCat"); 
					StringTokenizer st = new StringTokenizer(strLineIdConCat,"|");
					int iCountTokens = st.countTokens() - 1;
					for(int i=0; i<iCountTokens; i++)
					{
						int iLineIdAll = Integer.parseInt(st.nextToken());
						updateHdrLineStsAssign.updateStatus(iOrgId,iLineIdAll,"",strHdrStatus,iUserId,strLineNotes);
					}
				}
				String emailToAddress=request.getParameter("email_to_address");
				String emailSubject=request.getParameter("email_subject");
				String emailBody=request.getParameter("email_body");
				boolean skipEmailNotification="Y".equals(request.getParameter("email_skip_notification"));
				updateHdrLineStsAssign.closeTicketNotification(strHdrStatus, iTktId, skipEmailNotification, emailToAddress, 
						emailSubject, emailBody, requestURL, iUserId);
			}
			else 
			{
				int flagSts = 0;
				int flagSev = 0;
				int flagNotes = 0;
				int flagAssign = 0;
				String strSts = null;
				String strSev = null;
				String strNotes = null;
				//System.out.println("!!!!!! In if 4 "  );
				//1. Line Status update
				if(strLineStatus != null && !("".equals(strLineStatus)) && !("null".equalsIgnoreCase(strLineStatus)) ) 
				{
					flagSts = 1;
					strSts = strLineStatus;
				}
				//2. Line Severity Update
				if(strLineSeverity != null && !("".equals(strLineSeverity)) && !("null".equalsIgnoreCase(strLineSeverity))) {
					flagSev = 1;
					strSev = strLineSeverity;
				}
				//3. Check if notes are added		
				if(strLineNotes != null && !("".equals(strLineNotes)) && !("null".equalsIgnoreCase(strLineNotes)))
				{
					flagNotes = 1;
					strNotes = strLineNotes;
				}
				System.out.println("!!!!!! In if flagSts: "+flagSts+" ,flagSev: "+flagSev);
				if(flagSts == 1 || flagSev == 1 || flagNotes == 1){
					/* String emailToAddress=request.getParameter("email_to_address");
					String emailSubject=request.getParameter("email_subject");
					String emailBody=request.getParameter("email_body");						
					boolean skipEmailNotification="Y".equals(request.getParameter("email_skip_notification")); */						
					updateHdrLineStsAssign.updateStatus(iOrgId,iLineId,strSev,strSts,iUserId,strNotes/* ,iTktId,skipEmailNotification, emailToAddress,emailSubject,emailBody, requestURL */);
				}
				//System.out.println("!!!!!! iRoleId= "+iRoleId  );
				//4. Assignments update
				//Start changes for S21 by Mangala
				//if((iResourceId != 0) || (iRoleId != 0) ||
				if((iResourceId != "") || (iRoleId != "") ||		
				//End changes for S21 by Mangala		
				   (strLineDept != null && !("".equals(strLineDept)) && !("null".equalsIgnoreCase(strLineDept))))
				   flagAssign = 1;
				   
				if(flagSts == 1 || flagAssign == 1)  
				{
					System.out.println(" UserId:  " + iUserId + " LineId: "+iLineId + " strLineDept~ "+strLineDept + " iRoleId "+iRoleId + " iResourceId~ "+iResourceId  );
					ArrayList alTktStsAssign = new ArrayList();
					Canon_E193_TicketLinesObj objTktStsAssign = new Canon_E193_TicketLinesObj();
					objTktStsAssign.setLineId(iLineId);
					if(flagAssign == 1)
					{
						objTktStsAssign.setDeptCode(strLineDept);
						objTktStsAssign.setRoleId(iRoleId);
						objTktStsAssign.setResourceId(iResourceId);
					}
					objTktStsAssign.setCreatedBy(iUserId);
					
					alTktStsAssign.add(objTktStsAssign);
					//System.out.println("alTktStsAssign = " + alTktStsAssign.get(0));
					updateHdrLineStsAssign.addAssignments(alTktStsAssign); 
					if(flagAssign == 1)
					{
						updateHdrLineStsAssign.sendEmailNotificationToAsignee(iTktId, iLineId, requestURL, iUserId);
					}
				}		
			}
		}
		
		// Check if  destination is ticket summary or ticket status
		int tktSummaryNoAccess = 0;
		String strPageFrom = request.getParameter("hPageFrom");
		//System.out.println("!!!!!! Before 1"  );
		//Canon_E193_Ticket objTktUnassigned = new Canon_E193_Ticket();
		//String strOutput = objTktUnassigned.getTicketSummaryAccess(iOrgId,iTktId,iResId,iUserId);
		String strOutput = objTicket.getTicketSummaryAccess(iOrgId,iTktId,iResId,iUserId);
		System.out.println("!!!!!! strOutput= "+strOutput  );
		StringTokenizer st = new StringTokenizer(strOutput, "|");
		int iCount = -1; 
		String strAccess = null;
		//System.out.println("CSR Workbench Testing strOutput is " + strOutput);
		if(st.countTokens()> 0)
			iCount = Integer.parseInt(st.nextToken());
		if(st.countTokens() > 0)
			strAccess = st.nextToken();
		System.out.println("CSR Workbench Testing iCount is " + iCount);
		System.out.println("CSR Workbench Testing strAccess is " + strAccess);			
		String strNextPage = "";
		String strControlForm = "";
		String strErrorMsg = "";
		
		Canon_E193_TicketHeaderObj objTktHdrObj = null;
		if (iCount > 0)
		{
			//System.out.println("!!!!!! if (iCount > 0)"  );
			if("Y".equalsIgnoreCase(strAccess))
			{
				Canon_E193_NonBillingIssue objNonBillDao = new Canon_E193_NonBillingIssue();
				ArrayList alHLList = new ArrayList();
				if(iTktId > 0) {
					System.out.println("~IOrgId~ "+ iOrgId +  " ~TktId~ "+iTktId);
					alHLList = objNonBillDao.getNonBillHeaderLines(iOrgId, iTktId);
					//alHLList = objNonBillDao.
					//System.out.println("!!!!!! alHLList= "+alHLList  );
				}
				if(alHLList.size() > 0)
				{
					System.out.println("!!!!!! if(alHLList.size() > 0)"  );
					objHeaderLinesList.setArrayList(alHLList);
					strNextPage = "canon_E193_csTicketSummary.jsp";			
					objTktHdrObj = (Canon_E193_TicketHeaderObj)alHLList.get(0);
					if("Y".equalsIgnoreCase(objTktHdrObj.getBillingIssue()))
						strControlForm = "B";
				}
			}
			else
			{
				tktSummaryNoAccess = 1; 
			}
		}
		else
		{
			System.out.println("!!!!!! if (iCount not greater than 0)"  );
			ArrayList alTktDtls = new ArrayList();
			//Canon_E193_Ticket objTkt = new Canon_E193_Ticket();
			Canon_E193_Severity objSeverity = new Canon_E193_Severity();
					
			// Header Line details
			System.out.println("iOrgId,iTktId,iResId-- "+iOrgId+","+iTktId+","+iResId);
			ArrayList alTktHeaderLineDtls =(ArrayList)objTicket.getTicketDetails(iOrgId,iTktId,iResId);
			alTktDtls.add(alTktHeaderLineDtls);
			if(alTktHeaderLineDtls.size() > 0)
			{
				//System.out.println("!!!!!! alTktHeaderLineDtls.size() = "+alTktHeaderLineDtls.size()  );
				// Add Header and Line Status
				ArrayList alTktStatusObj =(ArrayList)objTicket.getTicketStatus();
				//System.out.println("!!!!!! alTktStatusObj = "+ alTktStatusObj.toString());
				alTktDtls.add(alTktStatusObj);
		    	// Add Line Severity		
				ArrayList alSeverityObj =(ArrayList)objSeverity.getSeverity();
				//System.out.println("!!!!!! alSeverityObj = "+alSeverityObj  );
				alTktDtls.add(alSeverityObj);
		    	// Add this object to session object with scope as request
				objHeaderLinesDtls.setArrayList(alTktDtls); 
				strNextPage = "canon_E193_csTicketStatus.jsp";
				//System.out.println("!!!!!! strNextPage = "+strNextPage  );
				objTktHdrObj = (Canon_E193_TicketHeaderObj)alTktHeaderLineDtls.get(0);
				//System.out.println("!!!!!! objTktHdrObj = "+objTktHdrObj  );
			}
		} // End of Else.	 	
		
		System.out.println("strPageFrom:"+strPageFrom);
		if(tktSummaryNoAccess == 1)
		{
			System.out.println("!!!!!! tktSummaryNoAccess == 1 "  );
			strErrorMsg = "Ticket creation is not complete. Ticket Status will be available once the creator / Owner of ticket completes the ticket.";
			if("EICheckRequest".equalsIgnoreCase(strPageFrom))
				strNextPage = "canon_E193_csEICheckRequest.jsp";
			else if("EIInvoiceSearch".equalsIgnoreCase(strPageFrom))			
				strNextPage = "canon_E193_csEIInvoiceController.jsp";
			else if("TicketHistory".equalsIgnoreCase(strPageFrom))
				strNextPage = "canon_E193_csTicketHistoryController.jsp";
			else if(("WBOwned".equalsIgnoreCase(strPageFrom)) || ("WBAssigned".equalsIgnoreCase(strPageFrom)) || ("WBCreated".equalsIgnoreCase(strPageFrom)) || ("WorkbenchController".equalsIgnoreCase(strPageFrom)))
				strNextPage = "canon_E193_csWorkbenchController.jsp";
			else  
				strNextPage = "canon_E193_csErrorPage.jsp?err=PageFromNotFound";								
		}		
		else if(objTktHdrObj == null)
		{
			System.out.println("!!!!!! objTktHdrObj == null "  );
			strErrorMsg = "Invalid Ticket Number";
			if("EICheckRequest".equalsIgnoreCase(strPageFrom))
				strNextPage = "canon_E193_csEICheckRequest.jsp";
			else if("EIInvoiceSearch".equalsIgnoreCase(strPageFrom))			
				strNextPage = "canon_E193_csEIInvoiceP.jsp";				
			else if("TicketHistoryP".equalsIgnoreCase(strPageFrom))
				strNextPage = "canon_E193_csTicketHistoryController.jsp";
			else if("TicketHistory".equalsIgnoreCase(strPageFrom))
				strNextPage = "canon_E193_csTicketHistoryController.jsp";	
			else if(("WBOwned".equalsIgnoreCase(strPageFrom)) || ("WBAssigned".equalsIgnoreCase(strPageFrom)) || ("WBCreated".equalsIgnoreCase(strPageFrom)) || ("WorkbenchController".equalsIgnoreCase(strPageFrom)))
				strNextPage = "canon_E193_csWorkbenchController.jsp";
			else		
				strNextPage = "canon_E193_csErrorPage.jsp?err=PageFromNotFound";		
		}
		else
		{
			System.out.println("!!!!!! ELSE "  );
			// Check if session acctInfo object exists
			Object objAcct = session.getAttribute("objSessionAcctInfo");
			Canon_E193_AcctInfoObj objSessionAcctInfo = new Canon_E193_AcctInfoObj();
			
				objSessionAcctInfo.setContactName(objTktHdrObj.getCustContact()==null?objTktHdrObj.getOrigName():objTktHdrObj.getCustContact());
				objSessionAcctInfo.setContactNum(objTktHdrObj.getCustPhNo()==null?objTktHdrObj.getOrigPhNo():objTktHdrObj.getCustPhNo());
				objSessionAcctInfo.setAcctName(objTktHdrObj.getCustomerName()==null?"":objTktHdrObj.getCustomerName());
				objSessionAcctInfo.setAcctNum(objTktHdrObj.getCustomerNo()==null?"":objTktHdrObj.getCustomerNo());
				objSessionAcctInfo.setAcctId(objTktHdrObj.getCustAcctId());
				objSessionAcctInfo.setPORequiredFlag(objTktHdrObj.getAttribute2()==null?"":objTktHdrObj.getAttribute2());
				session.setAttribute("objSessionAcctInfo",objSessionAcctInfo);
			
		}
		System.out.println("strNextPage:::____:"+strNextPage);
%>	
		<form name="tktStsControllerForm" method="post">
			<jsp:forward page= "<%=strNextPage%>">
				<jsp:param name="hPageFrom" value="TicketStatusController" />
				<jsp:param name="errorMsg" value="<%=strErrorMsg%>" />
				<jsp:param name="controlForm" value="<%=strControlForm%>" />
				<jsp:param name="iTicketId" value="<%=String.valueOf(iTktId)%>" />
			</jsp:forward>
		</form>
<%
	}
	else {
		String strErr = "-- Exception : -- Invalid Ticket Number";
		throw new CanonCustAppExceptionUtil(100001,strErr, "TicketStatusController.jsp");	
	}	
}
catch(com.canon.oracle.custapp.util.CanonCustAppExceptionUtil eCustExp) {
	//System.out.println("!!!!!! in Exception 1 :" + eCustExp);
       String strErr = "-- Exception : " + eCustExp.getStrErrorDesc() + " -- Exception Location :" + eCustExp.getStrErrorLocation();
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + strErr);
}
catch (Exception eExp) {
	//System.out.println("!!!!!! in Exception 2 :" + eExp);
    response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>



