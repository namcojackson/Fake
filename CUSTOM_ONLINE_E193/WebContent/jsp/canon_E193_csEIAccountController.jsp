<!-- $Header: canon_E193_csEIAccountController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csEIAccountController.jsp - Account Controller.
 |   
 | DESCRIPTION
 |   For a given criteria Retrives Account name, number, CI information and 
 |   stores them in session and forwards it to respective jsp page
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	08/07/2005
 |
 | HISTORY
 | DATE			WHO				WHY
 |
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 

<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>

<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_AcctInfo" id="objCiAcctInfo" scope="page"/>

<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
		//System.out.println("1");
		String strPageFrom = request.getParameter("hPageFrom");
		String strProbType = request.getParameter("probType");
		String strVal1 = request.getParameter("val1");
		System.out.println("strProbType : " + strProbType+" strVal1 : "+strVal1);
		int iAcctId = 0;
		int iLocId = 0;
		String strAcctName = "";
		String strAcctNum = "";
		String billType ="";
		//System.out.println("Before org Id");
		//Get Org ID
		int iOrgId = objCiSession.getOrgId();
		if (strPageFrom.equals("EIAcctSearch"))
		{
			System.out.println("in EIAcctSearch");
			String strArrAcctDetails[] = (String [])objCiAcctInfo.getAccountDetails(strVal1,strProbType,iOrgId);
			//System.out.println("After acct details");
			if (strArrAcctDetails[2] != null) {
				strAcctName = strArrAcctDetails[0];
				strAcctNum = strArrAcctDetails[1];
				iAcctId = (int)Integer.parseInt(strArrAcctDetails[2]);
				billType = strArrAcctDetails[3];
				//System.out.println("Value of billType =  "+billType);
			} 
		}else if(strPageFrom.equals("EIAccountList"))
		{
			System.out.println("In EIAccountList");
			iAcctId = (int)Integer.parseInt(request.getParameter("selAcctId"));
			strAcctName = request.getParameter("selAcctName");
			strAcctNum = request.getParameter("selAcctNum");
		}
		else	
		{	
			// Get AcctName and AcctNum
			System.out.println("In else acct details");
			iAcctId = (int)Integer.parseInt(request.getParameter("selAcctId"));
			iLocId = (int)Integer.parseInt(request.getParameter("selLocId"));
			strAcctName = request.getParameter("selAcctName");
			strAcctNum = request.getParameter("selAcctNum");
		}
		//System.out.println("before Account API");
		//Get Account Information
		Canon_E193_AcctInfoObj objSessionAcctInfo = new Canon_E193_AcctInfoObj();
		String strNextPage = "";
		String strErrorMsg = "";
		if (iAcctId != 0)
		{
			objSessionAcctInfo = (Canon_E193_AcctInfoObj)objCiAcctInfo.getAcctInfo(strAcctName,strAcctNum,iAcctId,iOrgId);
			String strContactName = request.getParameter("custName");
			String billableFlg = objCiAcctInfo.getBillableFlag(strProbType);
			System.out.println("billableFlg in acct Controller: "+ billableFlg);
			if( (strContactName == null) || ("null".equalsIgnoreCase(strContactName)) || ("".equalsIgnoreCase(strContactName)) )
				 strContactName = request.getParameter("origName");
				 
			String strContactNum = request.getParameter("custPhNo");
			if((strContactNum == null) || ("null".equalsIgnoreCase(strContactNum)) || ("".equalsIgnoreCase(strContactNum)))
				 strContactNum = request.getParameter("origPhNo");
			objSessionAcctInfo.setContactName(strContactName);
			objSessionAcctInfo.setContactNum(strContactNum);
			objSessionAcctInfo.setStrBillFlg(billableFlg);
			session.setAttribute("objSessionAcctInfo",objSessionAcctInfo);
			strNextPage = "canon_E193_csEISelectAcct.jsp";
		}
		else
		{
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
		String strSourceType = request.getParameter("sourceType");
		
		String strCustName = request.getParameter("custName");
		String strCustPhNo = request.getParameter("custPhNo");
		String strCustEmailAddr = request.getParameter("custEmailAddress");
		String strRecurProb = request.getParameter("recurProb");
		String strPath = request.getParameter("hPath");
		//System.out.println("Value of billType 2 =  "+billType);
		if(billType == null){
			billType="";
		}
		//System.out.println("Value of billType 3 =  "+billType + "strNextPage : " + strNextPage);

%>
		<form name="acctControllerForm" method="post">
			<jsp:forward page="<%=strNextPage%>">
				<jsp:param name="hPageFrom" value="EIAccountController" />
				<jsp:param name="errorMsg" value="<%=strErrorMsg%>" />
				<jsp:param name="origName" value="<%=strOrigName%>" />
				<jsp:param name="origPhNo" value="<%=strOrigPhNo%>"/>
				<jsp:param name="origEmailAddress" value="<%=strOrigEmailAddr%>"/>
				<jsp:param name="origCheckbox" value="<%=strorigCheckbox%>"/>
				<jsp:param name="origType" value="<%=strOrigType%>" />
				<jsp:param name="sourceType" value="<%=strSourceType%>" />
				<jsp:param name="custName" value="<%=strCustName%>" />
				<jsp:param name="custPhNo" value="<%=strCustPhNo%>"/>
				<jsp:param name="custEmailAddress" value="<%=strCustEmailAddr%>"/>
				<jsp:param name="recurProb" value="<%=strRecurProb%>"/>
				<jsp:param name="probType" value="<%=strProbType%>"/>
				<jsp:param name="val1" value="<%=strVal1%>"/>
				<jsp:param name="selAcctId" value="<%=iAcctId%>" />
				<jsp:param name="selLocId" value="<%=iLocId%>"/>
				<jsp:param name="selAcctName" value="<%=strAcctName%>"/>
				<jsp:param name="selAcctNum" value="<%=strAcctNum%>"/>
				<jsp:param name="hPath" value="<%=strPath%>"/>
				<jsp:param name="vBillType" value="<%=billType%>"/>				
			</jsp:forward>
		</form>
<%
} 
/* catch(com.canon.oracle.custapp.util.CanonCustAppExceptionUtil eCustExp) {
       
       String strErr = "-- Exception : " + eCustExp.getStrErrorDesc() + " -- Exception Location :" + eCustExp.getStrErrorLocation();
       
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + strErr);
} */
catch (Exception eExp) {
	System.out.println("hello in exception in acct controller");
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>