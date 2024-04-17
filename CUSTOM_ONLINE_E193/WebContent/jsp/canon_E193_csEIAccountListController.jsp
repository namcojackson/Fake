<!-- $Header: canon_E193_csEIAddressController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csEIAccountListController.jsp - Accounts Controller
 |   
 |
 | AUTHOR
 |	Q11607 
 |
 | CREATION DATE
 |	04/18/2019
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
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" %>

<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_AcctInfo" id="objAcctInfo" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objAcctArrayList" scope="request" />
<jsp:setProperty name="objAcctArrayList" property="*" />

<%
	// Get SerialNum 
	String strProbType = request.getParameter("probType");
	String strVal1 = request.getParameter("val1");
	
	String strSerialNum = "", strPONum = "";
	
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
		
		//Get Org ID
		int iOrgId = objCiSession.getOrgId();
		String strOrderName = request.getParameter("orderName");
		String strOrderBy = request.getParameter("orderBy");		
		if("null".equals(strOrderName) || "".equals(strOrderName)) strOrderName = null;
		if("null".equals(strOrderBy) || "".equals(strOrderBy)) strOrderBy = null;

		// Get Page Number
		int iPageNo = 0;
		String strPageNo = request.getParameter("iPageNo");
		if (strPageNo != null && !strPageNo.equalsIgnoreCase("null")) 
			iPageNo = (int)(Integer.parseInt(strPageNo));
		
		// Get Total Page Numbers
		int iTotPageNo = 0;
		String strTotPageNo = request.getParameter("iTotPageNo");
		if (strTotPageNo != null && !strTotPageNo.equalsIgnoreCase("null")) 
			iTotPageNo = (int)(Integer.parseInt(strTotPageNo));
		
		ArrayList alAcctList = null;
		
		if (strProbType != null && strProbType.equals("sernum")) {
			strSerialNum = strVal1;
			//Get Account Details
			alAcctList = objAcctInfo.getSerialNoAccountDetails(strProbType, strSerialNum, strOrderName, strOrderBy, iPageNo, iTotPageNo);
		}else{
			strSerialNum = null;
		}

		if (strProbType != null && strProbType.equals("ponum")) {
			strPONum = strVal1;
			alAcctList = objAcctInfo.getMultipleAccountDetails(iOrgId, strProbType, strPONum);
		}else{
			strPONum = null;
		}
		
		System.out.println("canon_E193_csEIAccountListController strProbType : " + strProbType 
				+ " strSerialNum : " + strSerialNum + " iOrgId : " + iOrgId + " strOrderName : " + strOrderName
				+ " strOrderBy : " + strOrderBy + " iPageNo : " + iPageNo + " iTotPageNo : "+ iTotPageNo); 
		
		
		//System.out.println("canon_E193_csEIAccountListController alAcctList : " + alAcctList); 
		
		String strNextPage = ""; 
		String strErrorMsg = "";
		
		
		if(alAcctList != null && alAcctList.size() > 0) {
			/* iPageNo = ((Integer)alAddrList.get(0)).intValue();
			alAddrList.remove(0);
			iTotPageNo = ((Integer)alAddrList.get(0)).intValue();
			alAddrList.remove(0); */
			objAcctArrayList.setArrayList(alAcctList);
			strNextPage = "canon_E193_csEIAccountList.jsp";

		}else		
		{
			String strPageFrom = request.getParameter("hPageFrom");
			strErrorMsg = "Account not found for this value. Please revise your search criteria.";
			System.out.println("in if strNextPage is :" + strNextPage);
			if("EIAcctSearch".equalsIgnoreCase(strPageFrom))
				strNextPage = "canon_E193_csEIAcctSearch.jsp";
			else  
				strNextPage = "canon_E193_csErrorPage.jsp?err=PageFromNotFound";
		}
		
		String strOrigName = request.getParameter("origName");
		String strOrigPhNo = request.getParameter("origPhNo");
		String strOrigEmailAddr = request.getParameter("origEmailAddress");
		String strorigCheckbox = request.getParameter("origCheckbox");
		String strOrigType = request.getParameter("origType");
		String strsourceType = request.getParameter("sourceType");
		String strCustName = request.getParameter("custName");
		String strCustPhNo = request.getParameter("custPhNo");
		String strCustEmailAddr = request.getParameter("custEmailAddress");
		String strRecurProb = request.getParameter("recurProb");
		
		
		strOrderName = strOrderName==null?"null":strOrderName;
		strOrderBy = strOrderBy==null?"null":strOrderBy;
		// Set ArrayList Obj In Session
		//session.putValue("alAddrList", alAddrList);
%>
		<form name="acctListControllerForm" method="post">
		<!-- hidden variables -->
			<jsp:forward page="<%=strNextPage%>">
				<jsp:param name="errorMsg" value="<%=strErrorMsg%>" />
				<jsp:param name="origName" value="<%=strOrigName%>" />
				<jsp:param name="origPhNo" value="<%=strOrigPhNo%>"/>
				<jsp:param name="origEmailAddress" value="<%=strOrigEmailAddr%>"/>
				<jsp:param name="origCheckbox" value="<%=strorigCheckbox%>"/>
				<jsp:param name="origType" value="<%=strOrigType%>" />
				<jsp:param name="sourceType" value="<%=strsourceType%>" />
				<jsp:param name="custName" value="<%=strCustName%>" />
				<jsp:param name="custPhNo" value="<%=strCustPhNo%>"/>
				<jsp:param name="custEmailAddress" value="<%=strCustEmailAddr%>"/>
				<jsp:param name="recurProb" value="<%=strRecurProb%>"/>
				<jsp:param name="probType" value="<%=strProbType%>"/>
				<jsp:param name="val1" value="<%=strVal1%>"/>
				<jsp:param name="hPageFrom" value="EIAccountListController" />
				<jsp:param name="iPageNo" value="<%=iPageNo%>" />
				<jsp:param name="iTotPageNo" value="<%=iTotPageNo%>"/>
				<jsp:param name="orderName" value="<%=strOrderName%>"/>
				<jsp:param name="orderBy" value="<%=strOrderBy%>"/>
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