<!-- $Header: canon_E193_csTicketHistoryController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csTicketHistoryController.jsp - Ticket Controller
 |   
 | DESCRIPTION
 |   For a given criteria Retrives records and forwards it to respective jsp page.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	09/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Ticket" id="objTicket" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objHistArrayList" scope="request" />
<jsp:setProperty name="objHistArrayList" property="*" />
<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%
		String strPageFrom = request.getParameter("hPageFrom");
		
		// getting search by and value
		String strOption = request.getParameter("searchType");
		String strValue = request.getParameter("searchValue");
		System.out.println("in ticket controiller " + strOption + "strValue" + strValue);
		String strOrderName = request.getParameter("orderName");
		String strOrderBy = request.getParameter("orderBy");
		//String strHistoryProfile ="";
		if("null".equals(strOrderName) || "".equals(strOrderName)) strOrderName = null;
		if("null".equals(strOrderBy) || "".equals(strOrderBy)) strOrderBy = null;
		
		//Get Org ID
		int iOrgId = objCiSession.getOrgId();
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
		
		ArrayList alTicketHist = new ArrayList();
		Object objAcct = session.getAttribute("objSessionAcctInfo");
		Canon_E193_AcctInfoObj objSessionAcctInfo = null;
		if(objAcct != null) objSessionAcctInfo = (Canon_E193_AcctInfoObj)objAcct;
		
		System.out.println("in ticket controiller iOrgId " + iOrgId);
		if(objAcct != null) {
			alTicketHist = objTicket.getTicketHistory(strOption, strValue, objSessionAcctInfo.getAcctId(), iOrgId, strOrderName, strOrderBy, iPageNo, iTotPageNo);
			System.out.println("in if of ticket controiller ");
		}else if(strOption != null) {
			alTicketHist = objTicket.getTicketHistory(strOption, strValue, 0, iOrgId, strOrderName, strOrderBy, iPageNo, iTotPageNo);
			System.out.println("in else of ticket controiller ");
		}
		if(alTicketHist != null && alTicketHist.size() > 0) {
			iPageNo = ((Integer)alTicketHist.get(0)).intValue();
			alTicketHist.remove(0);
			iTotPageNo = ((Integer)alTicketHist.get(0)).intValue();
			alTicketHist.remove(0);
		}
		
		objHistArrayList.setArrayList(alTicketHist);

		strOrderName = strOrderName==null?"null":strOrderName;
		strOrderBy = strOrderBy==null?"null":strOrderBy;
		
		String nextPage = "";
		nextPage = request.getParameter("popup")==null?"":request.getParameter("popup");
		String extApp ="";
		extApp= request.getParameter("extApp")==null?"":request.getParameter("extApp");
		System.out.println("next page in ticket controller is" + nextPage);
		if("".equalsIgnoreCase(nextPage)) nextPage = "canon_E193_csTicketHistory.jsp";
		else if (!"".equalsIgnoreCase(extApp)) nextPage = "canon_E193_csEITicketHistoryP.jsp";
		else  nextPage = "canon_E193_csTicketHistoryP.jsp";		
		
		System.out.println("in ticket controiller nextPage is " + nextPage);
%>
		<form name="ticketControllerForm" method="post">
			<input type="hidden" name="hPageFrom" value="TicketHistoryController">
			<jsp:forward page="<%=nextPage%>">
				<jsp:param name="iPageNo" value="<%=iPageNo%>" />
				<jsp:param name="iTotPageNo" value="<%=iTotPageNo%>" />
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