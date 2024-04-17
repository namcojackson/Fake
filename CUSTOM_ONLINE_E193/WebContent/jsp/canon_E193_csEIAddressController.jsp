<!-- $Header: canon_E193_csEIAddressController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csEIAddressController.jsp - Address Controller
 |   
 | DESCRIPTION
 |   Retrives Addresses for given Account name or Number, stores them in session
 |   and forwards to respective jsp page.
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	08/07/2005
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

<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_AcctAddress" id="objCiAddr" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objAddrArrayList" scope="request" />
<jsp:setProperty name="objAddrArrayList" property="*" />

<%
	// Get AcctName and AcctNum
	String strProbType = request.getParameter("probType");
	String strVal1 = request.getParameter("val1");
	
	String strAcctName = "";
	String strAcctNum = "";
	
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
		if (strProbType.equals("acctname")) {
			strAcctName = strVal1;
			strAcctNum = null;
		}else if (strProbType.equals("acctnum")) {
			strAcctName = null;
			strAcctNum = strVal1;
		}else{
			strAcctName = null;
			strAcctNum = null;
		}

		//Get address fields
		String strAddr1 = request.getParameter("address1");
		if (strAddr1.equals(""))
			strAddr1 = null;

		String strAddr2 = request.getParameter("address2");
		if (strAddr2.equals(""))
			strAddr2 = null;

		String strCity = request.getParameter("city");
		if (strCity.equals(""))
			strCity = null;

		String strState = request.getParameter("state");
		if (strState.equals(""))
			strState = null;

		String strZipCode = request.getParameter("zipCode");
		if (strZipCode.equals(""))
			strZipCode = null;

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
		
		//Get Addresses
		ArrayList alAddrList = objCiAddr.getAddress(strAcctName, strAcctNum, strAddr1, strAddr2, strCity, strState, strZipCode, iOrgId, strOrderName, strOrderBy, iPageNo, iTotPageNo);
		String strNextPage = "";
		String strErrorMsg = "";
		if(alAddrList != null && alAddrList.size() > 0) {
			iPageNo = ((Integer)alAddrList.get(0)).intValue();
			alAddrList.remove(0);
			iTotPageNo = ((Integer)alAddrList.get(0)).intValue();
			alAddrList.remove(0);
			objAddrArrayList.setArrayList(alAddrList);
			strNextPage = "canon_E193_csEISelectAddress.jsp";

		}else		
//		if(alAddrList.size() < 2)
		{
			String strPageFrom = request.getParameter("hPageFrom");
			strErrorMsg = "Address not found for this value. Please revise your search criteria.";
			System.out.println("in if strNextPage is :" + strNextPage);
			if("EIAcctAddress".equalsIgnoreCase(strPageFrom))
				strNextPage = "canon_E193_csEIAcctAddress.jsp";
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
		
		strAddr1 = strAddr1==null?"":strAddr1;
		strAddr2 = strAddr2==null?"":strAddr2;
		strCity = strCity==null?"":strCity;
		strState = strState==null?"":strState;
		strZipCode = strZipCode==null?"":strZipCode;
		
		strOrderName = strOrderName==null?"null":strOrderName;
		strOrderBy = strOrderBy==null?"null":strOrderBy;
		// Set ArrayList Obj In Session
		//session.putValue("alAddrList", alAddrList);
%>
		<form name="acctControllerForm" method="post">
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
				<jsp:param name="address1" value="<%=strAddr1%>" />
				<jsp:param name="address2" value="<%=strAddr2%>"/>
				<jsp:param name="city" value="<%=strCity%>"/>
				<jsp:param name="state" value="<%=strState%>"/>
				<jsp:param name="zipCode" value="<%=strZipCode%>"/>
				<jsp:param name="hPageFrom" value="EIAddressController" />
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