<!-- $Header: ITG# 74988 canon_E193_csCFSTicketController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csCFSTicketController.jsp - CFS Ticket Controller
 |   
 | DESCRIPTION
 |   For a given criteria insert records and forwards it to respective jsp page.
 |
 | AUTHOR
 |	Vikas 
 |
 | CREATION DATE
 |	12/04/2006
 |
 | HISTORY
 | DATE		WHO		WHY
 | 12/04/2006	Vikas Basal	Inital version
 | 03/08/2007   Vikas Basal     Included the filter data based on region code
 | 01/29/2016   Mangala Shenoy	Modified for S21 changes
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
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Assignment" id="objAssignDao" scope="page"/>
<jsp:setProperty name="objHeaderLinesList" property="*" />
<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
		String strPageFrom = request.getParameter("hPageFrom");
		ArrayList alLineId = new ArrayList();
		
		//Kireet: Commented the following .
		/*
		Canon_E193_AcctInfoObj objSessionAcctInfo = (Canon_E193_AcctInfoObj)session.getAttribute("objSessionAcctInfo");
		
		String strAcctName = objSessionAcctInfo.getAcctName();
		String strAcctNo = objSessionAcctInfo.getAcctNum();
		String strContactName = objSessionAcctInfo.getContactName();
		String strContactNo = objSessionAcctInfo.getContactNum();
		*/

		//Kireet:Begin
		Object objAcct = session.getAttribute("objSessionAcctInfo");
		Canon_E193_AcctInfoObj objSessionAcctInfo = null;
		if(objAcct == null){
	        	objSessionAcctInfo = new Canon_E193_AcctInfoObj();
	        }
	        else{
		        objSessionAcctInfo = (Canon_E193_AcctInfoObj)session.getAttribute("objSessionAcctInfo");
				  
		}
		objSessionAcctInfo.setContactName(request.getParameter("origName"));
		//objSessionAcctInfo.setContactNum(strFormatPhNo);
		objSessionAcctInfo.setAcctName(request.getParameter("selAcctName"));
		objSessionAcctInfo.setAcctNum(""); /// if fails, later try with -99
		objSessionAcctInfo.setAcctId(-99);
		objSessionAcctInfo.setPORequiredFlag("N");
		session.setAttribute("objSessionAcctInfo",objSessionAcctInfo);
		
		
		
		String strAcctName = objSessionAcctInfo.getAcctName();
		String strAcctNo = objSessionAcctInfo.getAcctNum();
		String strContactName = objSessionAcctInfo.getContactName();
		//String strContactNo = objSessionAcctInfo.getContactNum();
		
		//Kireet: End

		
		int iCustAcctId = objSessionAcctInfo.getAcctId();
		
		//Get Org ID
		int iOrgId = objCiSession.getOrgId();
		
		// get userId
		String iUserId = objCiSession.getUserId();
		
		// get resource id
		//Start Changes for S21 by Mangala
		//int iResourceId = objCiSession.getResourceId();
		String iResourceId = objCiSession.getResourceId();
		//End Changes for S21 by Mangala
		/* ADDED By Vikas */	
		String strRegionCode = (String)objCiSession.getRegionCode();	
		/* ADDED By Vikas Ends */	
		
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
		String strCatId = request.getParameter("hCatId")==null?"0":request.getParameter("hCatId");
		String strParentCatId = request.getParameter("hParentCatId")==null?"":request.getParameter("hParentCatId");;
		String strCatDesc = request.getParameter("hCatDesc");
		String strSeverity = request.getParameter("severity");
		
		/* Kireet : Begin */
		String strFormatPhNo="";
		String strOrigPhCd = "", strOrigPhNo = "", strOrigPhNo2 = "", strOrigExtNo = "";
		
		strOrigPhCd = request.getParameter("origPhCd")==null?"":request.getParameter("origPhCd");
		strOrigPhNo = request.getParameter("origPhNo")==null?"":request.getParameter("origPhNo");
		strOrigPhNo2 = request.getParameter("origPhNo2")==null?"":request.getParameter("origPhNo2");
		strOrigExtNo = request.getParameter("origPhNoExt")==null?"":request.getParameter("origPhNoExt");
			
		if((!"".equalsIgnoreCase(strOrigPhCd)) && (!"null".equalsIgnoreCase(strOrigPhCd)))
			strFormatPhNo = strOrigPhCd + "." + strOrigPhNo + "." + strOrigPhNo2;
		strFormatPhNo = strFormatPhNo + " Ext " + strOrigExtNo;
                /*
		String strCustPhCd = "", strCustPhNo = "", strCustPhNo2 = "", strCustExtNo = "", strFormatPhNo = "";
		strCustPhCd = request.getParameter("custPhCd")==null?"":request.getParameter("custPhCd");
		strCustPhNo = request.getParameter("custPhNo")==null?"":request.getParameter("custPhNo");
		strCustPhNo2 = request.getParameter("custPhNo2")==null?"":request.getParameter("custPhNo2");
		strCustExtNo = request.getParameter("custPhNoExt")==null?"":request.getParameter("custPhNoExt");
		*/


		objSessionAcctInfo.setContactNum(strFormatPhNo);
		String strContactNo = objSessionAcctInfo.getContactNum();
		
		/*Kireet :End */		
		//System.out.println("Here come2");

		if("".equals(strReadData) || "null".equals(strReadData)) {
			String strOrigName = request.getParameter("origName");
			//String strOrigPhNo = request.getParameter("origPhNo"); // Modified to use strFormatPhNo by Kireet
			strOrigPhNo = strFormatPhNo; 
			String strOrigEmailAddress = request.getParameter("origEmailAddress");
			String strOrigType = request.getParameter("origType");
			String strSourceType = request.getParameter("sourceType");
			String strCustName = request.getParameter("origName"); ///request.getParameter("custName");
			String strCustPhNo = strOrigPhNo; ///request.getParameter("custPhNo");
			String strCustEmailAddress = request.getParameter("origEmailAddress");  ///request.getParameter("custEmailAddress");
			String strRecurProb = request.getParameter("recurProb");
			String strProbType = request.getParameter("probType");
			String strVal1 = request.getParameter("val1");
			String strSelAcctId = request.getParameter("selAcctId");
			String strSelLocId = request.getParameter("selLocId");
			String strSelAcctName = request.getParameter("selAcctName");
			String strSelAcctNum = request.getParameter("selAcctNum");
			//Capture CFS Serial Number: Begin
			String strOrigCFSSerialNumber = request.getParameter("origCFSSerialNumber"); 
			//Capture CFS Serial Number: End
			
			if("".equals(strTicketId)) {
				
				// setting header values to object
				System.out.println("strParentCatId::::"+strParentCatId);
				if(strParentCatId!=null && !"null".equalsIgnoreCase(strParentCatId))					
				objHeaders.setCatId(Integer.parseInt(strParentCatId));
				//System.out.println("After strTicketId");
				objHeaders.setStatus("UNASSIGNED");
				if("no".equalsIgnoreCase(strRecurProb))
					objHeaders.setRecurring("N");
				else objHeaders.setRecurring("Y");
				objHeaders.setBillingIssue("N");
				objHeaders.setOrgId(iOrgId);
                                /* ADDED By Vikas */
				objHeaders.setAttribute6(strRegionCode);
				/* ADDED By Vikas Ends */	
				objHeaders.setCustAcctId(iCustAcctId);
				objHeaders.setCustomerName(strAcctName);
				objHeaders.setCustomerNo(strAcctNo);
				objHeaders.setOrigName(strOrigName);
				objHeaders.setOrigPhNo(strOrigPhNo);
				objHeaders.setOrigEmail(strOrigEmailAddress);
				//Capture CFS Serial Number: Begin
				objHeaders.setAttribute4(strOrigCFSSerialNumber);
				//Capture CFS Serial Number: End
				objHeaders.setOrigType(strOrigType);
				
				//Capture CFS Source Name: Begin
				objHeaders.setAttribute9(strSourceType);
				// Capture CFS Source Name: End
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
				//System.out.println("Here e");
			}
			
			// setting lines values to object
			objLines.setOrgId(iOrgId);
			if(strCatId!=null && !"null".equalsIgnoreCase(strCatId))
			objLines.setCatId(Integer.parseInt(strCatId));
			//System.out.println("Here come3");
			objLines.setLineStatus("UNASSIGNED");
			objLines.setSeverity(strSeverity);
			objLines.setReasonCd(strReasonCd);
			objLines.setReason(strReasonCdDesc);
			objLines.setJtfNotesFlag("Y");
			objLines.setCreatedBy(iUserId);
			objLines.setNotes(strNotes);
			
			if("".equals(strTicketId)) {
				iTicketId = objNonBillDao.addNonBillHeaderLines(objHeaders, objLines);
				
			}else{
				objLines.setTicketId(iTicketId);
				objNonBillDao.addNonBillLines(objLines);
			}

		}
		ArrayList alHLList = new ArrayList();
		if(iTicketId > 0) {
			alHLList = objNonBillDao.getNonBillHeaderLines(iOrgId, iTicketId);
		}
        System.out.println(" ~alHLList~ At CFSTicketController " +iOrgId + " ~~ "+ iTicketId);
		Canon_E193_TicketLinesObj objTicketLines1 = new Canon_E193_TicketLinesObj();
		if(alHLList.size()>1)
		objTicketLines1 = (Canon_E193_TicketLinesObj)alHLList.get(1);
		
		ArrayList alAssignList = new ArrayList();
		alAssignList.add(objTicketLines1);
		objAssignDao.addAssignments(alAssignList);
		alLineId  = objAssignDao.callWrapUp(iOrgId, iTicketId, 0, "ASSIGNED", iUserId);	
		//Start changes for S21 by Mangala
		//TBD
		//Canon_E193_CRMeterObj mcr = new Canon_E193_CRMeterObj(); 
		//Canon_E193_CRBaseObj  bcr = new Canon_E193_CRBaseObj(); 
		//Canon_E193_CRPriceObj pcr = new Canon_E193_CRPriceObj();
		
		//if(alLineId != null && alLineId.size() > 0) 
		//{
          //  for(int i=0; i<alLineId.size(); i++) 
			//{
           // 	Canon_E193_LineIdObj objLineId = (Canon_E193_LineIdObj)alLineId.get(i);
            //	Object[] obj1 = null;
        	//	obj1 = objAssignDao.getCrDtls(objLineId.getiLineId(),iUserId);
        	//	 if(obj1[8] !=null) {
        		//	 mcr =		 (Canon_E193_CRMeterObj)obj1[8] ;
        		// }
        		 
        		// if(obj1[9] !=null) {
        		//	 bcr =		 (Canon_E193_CRBaseObj)obj1[9] ;
        		// }
        		 
        		 //if(obj1[10] !=null) {
        		//	 pcr =		 (Canon_E193_CRPriceObj)obj1[10] ;
        		 //}
        		 //refer canonE307ServiceReqCreate.jsp,CanonE307ServiceReqCreateDao.java
        		 
        		//End Changes for S21 by Mangala
        		
            	//TBD - Add the code for sending Notification 
            	//TBD - Add the code for CallWrap Up 
				//if(objLineId.getStrTrxNum().equals(strVal))
				//{
				//	selected = "selected";
				//}
		// }
		// }
%>
		<form name="cfsIssueControlForm" method="post">
			<jsp:forward page="canon_E193_csCFSTicketConfirmation.jsp">
				<jsp:param name="hPageFrom" value="CFSTicketController" />
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
	eExp.printStackTrace();
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>