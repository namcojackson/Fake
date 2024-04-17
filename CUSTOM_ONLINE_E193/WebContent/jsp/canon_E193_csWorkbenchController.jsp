<!-- $Header: canon_E193_csWorkbenchController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csWorkbenchController.jsp - Account Controller
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
 | 18-Dec-2006    Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes.
 | 29-Jan-2016    Mangala Shenoy	 Modified for S21 Changes
 |
 +=======================================================================--%>
<%@page language="java" %> 

<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>

<%@ include file="canon_E193_csCheckSessionInc.jsp" %>

<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_WBTicket" id="objWBTicket" scope="page"/>
 
<%
	try
	{
		//Get orgId 
		int iOrgId = (int)objCiSession.getOrgId();
		//Start changes for S21 by Mangala
		//int iResId = (int)objCiSession.getResourceId();
		String iResId = objCiSession.getResourceId();		
		//End changes for S21 by Mangala		
		String isOwnedFlag = "OWNED";
		//System.out.println("iResId "+iResId);
		//System.out.println("iOrgId "+iOrgId);
		/* ITG# 73987 : Begin */	
		String strRegionCode = (String)objCiSession.getRegionCode();	
                /* ITG# 73987 : End */
		
		ArrayList alWBTicket = objWBTicket.getWBTktOwnerAssignee(null, iResId, iOrgId, strRegionCode);
		
		if (alWBTicket.size() > 0) {
			isOwnedFlag = "OWNED";
%>
			<jsp:forward page="canon_E193_csWorkbenchOwned.jsp">
				<jsp:param name="ownedYesNo" value="<%=isOwnedFlag%>"/>
				<jsp:param name="hPageFrom" value="WorkbenchController"/>
			</jsp:forward>
<%
		}
		else 
		{
			isOwnedFlag = "ASSIGNED";
%>
			<jsp:forward page="canon_E193_csWorkbenchAssigned.jsp">
				<jsp:param name="ownedYesNo" value="<%=isOwnedFlag%>"/>
				<jsp:param name="hPageFrom" value="WorkbenchController"/>
			</jsp:forward>
<%
		}
} 
catch(com.canon.oracle.custapp.util.CanonCustAppExceptionUtil eCustExp) {
       
       String strErr = "-- Exception : " + eCustExp.getStrErrorDesc() + " -- Exception Location :" + eCustExp.getStrErrorLocation();
       
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + strErr);
} 
catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}

%>