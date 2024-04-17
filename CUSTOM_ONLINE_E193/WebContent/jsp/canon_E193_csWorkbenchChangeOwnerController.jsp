<!-- $Header: canon_E193_csWorkbenchChangeOwnerController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csWorkbenchChangeOwnerController.jsp - Change Owner Controller
 |   
 | DESCRIPTION
 |   It update the owner of each ticket.
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	10/03/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 | 18-Dec-2006    Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 

<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %>
 <jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_WBTicket" id="objChangeOwner" scope="page"/>

<%
	try
	{
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
	//String ownedYesNo =  request.getParameter("ownedYesNo");
	String strNewOwner = request.getParameter("reassignOwner");
	String strPrevOwner = request.getParameter("slOwner");
	int iOrgId = (int)objCiSession.getOrgId();
	String iUserId = (String)objCiSession.getUserId();
	
	//ArrayList alTktId = new ArrayList();
	String[] strChkBox = request.getParameterValues("tktdetails");
	int[] alTktId = new int[strChkBox.length]; 
	
	for(int i =0; i<strChkBox.length; i++)
	{
		alTktId[i] =  Integer.parseInt(strChkBox[i]);
	}
	
	int iResult = objChangeOwner.changeOwner(alTktId, strNewOwner, strPrevOwner, iOrgId, iUserId);
	// out.println("iResult --" + iResult);
	//ITG : 73987 : Begin : Commented the following
	%><%-- 
	if (iResult == 1)
	{
%>	
		<jsp:forward page="canon_E193_csWorkbenchOwned.jsp">
			<jsp:param name="ownedYesNo" value="OWNED"/>
		</jsp:forward>		
<%	
	}
	
	--%><%
	//ITG : 73987 : End 
	
	//ITG : 73987 : Begin
	%><jsp:forward page="canon_E193_csWorkbenchOwned.jsp">
	      <jsp:param name="ownedYesNo" value="OWNED" />
	      <jsp:param name="iResult"    value="<%=iResult%>" />
	</jsp:forward>
	<%
	//ITG : 73987 : End	
        	

} 
catch(com.canon.oracle.custapp.util.CanonCustAppExceptionUtil eCustExp) {
       
       String strErr = "-- Exception : " + eCustExp.getStrErrorDesc() + " -- Exception Location :" + eCustExp.getStrErrorLocation();
	   response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + strErr);
}
catch (Exception eExp) {
     response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());  
}
%>
