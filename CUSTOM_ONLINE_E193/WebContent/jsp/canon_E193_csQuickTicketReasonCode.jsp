<!-- $Header: ITG# 74988 canon_E193_csQuickTicketReasonCode.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csQuickTicketReasonCode.jsp - Quick Ticket reason codes.
 |   
 | DESCRIPTION
 |   Details of the reason code and reason description.
 |
 | AUTHOR
 |	Hema 
 |
 | CREATION DATE
 |	08/23/2013
 |
 |
 |
 +=======================================================================--%>
<% String ctxPath = request.getContextPath(); %>
<%@page language="java" %> 

<SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csCButton.js"></script>
<link rel=stylesheet href="<%=ctxPath%>/e193/css/styles.css">
<link rel=stylesheet href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css">
<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_ReasonCodeObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_QuickTicketDAO" id="objReasonDao" scope="page"/>
<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>

<script language="javascript">

function fnRefresh() {
	document.reasonCodeForm.submit();
}

</script>

<%
	//Get Application ID
	//boolean isFirstTime = true;
	int iApplId = objCiSession.getApplId();
	String strReasonCode = request.getParameter("reasonCode");
	if(strReasonCode != null && !strReasonCode.startsWith("CSR_E193_")) strReasonCode = "CSR_E193_"+strReasonCode;
	String strCodeDesc = request.getParameter("hCodeDesc")==null?"":request.getParameter("hCodeDesc");

	ArrayList alReasonList = new ArrayList();
	Canon_E193_ReasonCodeObj objReason = null;	
	alReasonList = objReasonDao.getQuickTicketReasonCodes(iApplId, strReasonCode);
%>


	<form name="reasonCodeForm" id="reasonCodeForm" action="canon_E193_csQuickTicketReasonCode.jsp" method="post">
	<div style="margin-left:30px;">Quick Ticket Reason Codes &nbsp;<a class="btn_red" href="javascript:select_reasonCode();" style="float:right;">Select</a></div>

		<table class="customer-care" style="align:center;" cellspacing="1">	
				<tr>
	 				<th>&nbsp;</th>
					<th>CI Reason</th>
					<th>CI Description</th>
		 		</tr>  
		 		<%
		 				if(alReasonList != null && alReasonList.size() > 0) {
	 						for(int j=0; j<alReasonList.size(); j++) {
		 							objReason = (Canon_E193_ReasonCodeObj)alReasonList.get(j);
		 			%>
				   				<tr >
					   				<td><input name="thdetails" type="radio" value="<%=objReason.getReason()==null?"":objReason.getReason()%>"></td>
		               				<td><%=objReason.getReason()==null?"":objReason.getReason()%></td>
					   				<td><%=objReason.getDescription()==null?"":objReason.getDescription()%></td>
								</tr>
					<%			}
							}else{
					%>
							<tr >
								<td colspan="3">
									<font class="promptReadOnly"><b><%=strTicketReasonCodePM1%></b></font>
								</td>
							</tr>
					<%		
							}
					%> 				   		    
		<tr>
			<td align="left" colspan="3"> 
			   <a class="btn_red" href="javascript:select_reasonCode();" style="float:right;">Select</a>				
			</td>
		</tr>
	</table>
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