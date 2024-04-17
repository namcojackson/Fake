<!-- $Header: canon_E193_csCustProfileLine.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csCustProfileLine.jsp - Customer Profile Link JSP.
 |   
 | DESCRIPTION
 |   Link JSP to call Customer Profile Application from Customer Service
 |   Re-Engineering App
 |
 | AUTHOR
 |	Vikas Basal
 |
 | CREATION DATE
 |	08/30/2005
 |
 +=======================================================================--%>
<%@ page language="java" %> 
<%@ page import="com.canon.oracle.custapp.custprof.beans.canonPD297SecurityBean" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_SystemObj" %>
<%@ page language="java" import="java.net.URLEncoder"%>

<jsp:useBean class="com.canon.oracle.custapp.custprof.beans.canonPD297SecurityBean" id="objSecurity" scope="session"/>
<jsp:setProperty name="objSecurity" property="*"/>
<%	
 try
 {

	/* System Bean Call*/		
	Canon_E193_SystemObj objCiSession = new Canon_E193_SystemObj();

	objCiSession = (Canon_E193_SystemObj)session.getAttribute("objCiSession");

	if(objCiSession.getRespId() == "" )
	{
		//response.sendRedirect("AppsLocalLogin.jsp");
		String strMsg = "No Org id found";
		response.sendRedirect("canon_E193_csErrorPage.jsp?err="+strMsg);
	}
	else
	{
	    //Put system variables in the session object
	    objSecurity.setUserId(objCiSession.getUserId());
	    objSecurity.setUserName(objCiSession.getUserName());
	    objSecurity.setRespId(objCiSession.getRespId());
	    objSecurity.setApplId(objCiSession.getApplId());

		// forward to requested page
		String mainMenuUrl ="" ;
		String strCustName = request.getParameter("customerName");
		String encodedCustName = URLEncoder.encode(strCustName,"UTF-8");
        mainMenuUrl = "canonPD297CustInfoController.jsp";
		mainMenuUrl = mainMenuUrl + "?recordType=ALL&actionFlag=custSearch&searchField=all&pageName=PD297CustInfoSearchTrue";
		mainMenuUrl = mainMenuUrl + "&custAcctId=" + request.getParameter("custAcctId");		
		mainMenuUrl = mainMenuUrl + "&orgId=" + objCiSession.getOrgId();
		mainMenuUrl = mainMenuUrl + "&customerName=" + encodedCustName;
		mainMenuUrl = mainMenuUrl + "&custName=" + encodedCustName;
		mainMenuUrl = mainMenuUrl + "&customerNumber=" + request.getParameter("customerNumber");
		mainMenuUrl = mainMenuUrl + "&custNumber=" + request.getParameter("customerNumber");

%>
		<jsp:forward page="<%=mainMenuUrl%>"/>		
<%
	} 
} 
catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>

