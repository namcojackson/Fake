<!-- $Header: canon_E193_csActualInvoiceController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csActualInvoiceController.jsp - View actual invoice from 170 system
 |   
 | DESCRIPTION
 |   For a given invoice, get actual invoice link
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	10/24/2005
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
<%@ page import="com.canon.oracle.custapp.custinq.dao.Canon_E193_Invoice" %>

<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
		String strPageFrom = request.getParameter("hPageFrom");
		
		//Get Org ID
		int iOrgId = objCiSession.getOrgId();
			
		// forward to next page
		String strNextPage = "";
		String strInvoiceNum = request.getParameter("InvNo")==null?"":request.getParameter("InvNo");
		//out.println("InvNo" + request.getParameter("InvNo"));
		//out.println("strInvoiceNum" + strInvoiceNum);
		Canon_E193_Invoice objActualInv = new Canon_E193_Invoice();
	/*
     	String strActualInvLink = objActualInv.getActualInvoice(iOrgId,strInvoiceNum);
		//out.println("strActualInvLink" + strActualInvLink);
		if("ERROR".equalsIgnoreCase(strActualInvLink))
			strNextPage = "canon_E193_csErrorPage.jsp?err=ActalInvViewErr";
		else
			strNextPage = strActualInvLink;
	*/	
		strNextPage="canonInvoiceDownloadNoSecurity.jsp?InvoiceNumber="+strInvoiceNum;	
		//out.println("strNextPage" + strNextPage);
		response.sendRedirect(strNextPage);
} 
/*catch(com.canon.oracle.custapp.util.CanonCustAppExceptionUtil eCustExp) {
       
       String strErr = "-- Exception : " + eCustExp.getStrErrorDesc() + " -- Exception Location :" + eCustExp.getStrErrorLocation();

       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + strErr);
}
*/
catch (Exception eExp) {

       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>