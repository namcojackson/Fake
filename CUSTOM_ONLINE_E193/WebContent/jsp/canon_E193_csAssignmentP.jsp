<!-- $Header: canon_E193_csAssignmentP.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csAssignmentP.jsp - Assignment Details.
 |   
 | DESCRIPTION
 |   Details of the assignment / changes to this ticket.
 |
 | AUTHOR
 |	Subba 
 |
 | CREATION DATE
 |	09/16/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 
 <% String ctxPath = request.getContextPath(); %>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csCButton.js"></script>
<link rel="stylesheet" href="<%=ctxPath%>/e193/css/styles.css" type="text/css">
<link rel=stylesheet href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css">
	
<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %>
<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>

<%
	try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>

<script language="javascript">

</script>

<%
	//Get Org ID
	int iOrgId = objCiSession.getOrgId();
	String strTicketId = request.getParameter("ticketId");
	int iTktId = -1;
	if(strTicketId != null && !"".equals(strTicketId) && !"null".equalsIgnoreCase(strTicketId))
		iTktId = Integer.parseInt(strTicketId);
		
	Canon_E193_Assignment objAssignDao = new Canon_E193_Assignment();
	
	int iTempLineid = -1;
	ArrayList alAssign = null;
	if(iTktId != -1)
		alAssign = objAssignDao.getAssignments(iOrgId,iTktId,iTempLineid);

%>
<form name="adminRoleForm" action="canon_E193_csAdminRoleP.jsp" method="post">
<table class="request-service" cellspacing="0">	
	<tr>
		<td>Assignment Details for Ticket <%=iTktId%></td>
	</tr>	
	<tr>
		<td>
			<table cellspacing="1" class="request-service">
			<tr> 
					<th rowspan="2">Line Number</th>
					<th rowspan="2">Line Status</th>
					<th rowspan="2">Status Time<br>(in days)</th>
					<th colspan="3" align="center">Assigned To</th>
					<th colspan="3" align="center">Assigned By</th>
					<th rowspan="2">Last Updated By</th>
					<th rowspan="2">Last Updated Date</th>
			  	</tr>
         		<tr> 
					<th>Resource</th>
					<th>Role</th>
					<th>Department</th>
					<th>Resource</th>
					<th>Role</th>
					<th>Department</th>
          		</tr>				
          <%
				Canon_E193_AssignmentPObj objAssignObj = null;
				int iTempLineNum = -1;
				String strTempLineNum = "";
				for(int i=0;i<alAssign.size();i++) {
					objAssignObj = (Canon_E193_AssignmentPObj)alAssign.get(i);
					int iLineId = objAssignObj.getILineId();
					int iLineNum = objAssignObj.getILineNum();
					String strLineStatus = objAssignObj.getStrLineStatus();
					int iAssignId = objAssignObj.getIAssignId();
					//String strAssignStatus = objAssignObj.getStrAssignStatus();
					String strAssignToRes = objAssignObj.getStrAssignToRes();
					String strAssignToRole = objAssignObj.getStrAssignToRole();
					String strAssignToDept = objAssignObj.getStrAssignToDept();
					String strAssignByRes = objAssignObj.getStrAssignByRes();
					String strAssignByRole = objAssignObj.getStrAssignByRole();
					String strAssignByDept = objAssignObj.getStrAssignByDept();
					String strCreatedBy = objAssignObj.getStrCreatedBy();
					String strCreatedDate = objAssignObj.getStrCreatedDate();
					String strLastUpdtBy = objAssignObj.getStrLastUpdtBy();
					String strLastUpdtDate = objAssignObj.getStrLastUpdtDate();
					String strStatusTime = objAssignObj.getStrStatusTime();
					if(iTempLineNum != iLineNum)
					{
						iTempLineNum = iLineNum;
						strTempLineNum = String.valueOf(iTempLineNum);
					}
					else
					{
						strTempLineNum = "";
					}		
%>
					  <tr class="tdTableCellStyle"> 
						<td align="center"><%=strTempLineNum%> <input type="hidden" name="iLineId" id="iLineId" value="<%=iLineId%>"></td>
						<td><%=strLineStatus==null?"":strLineStatus%><input type="hidden" name="iAssgnId" value="<%=iAssignId%>"></td>
						<td><%=strStatusTime==null?"":strStatusTime%></td>
						<td><%=strAssignToRes==null?"":strAssignToRes%></td>
						<td><%=strAssignToRole==null?"":strAssignToRole%></td>
						<td><%=strAssignToDept==null?"":strAssignToDept%></td>
						<td><%=strAssignByRes==null?"":strAssignByRes%></td>
						<td><%=strAssignByRole==null?"":strAssignByRole%></td>
						<td><%=strAssignByDept==null?"":strAssignByDept%></td>
						<td><%=strLastUpdtBy==null?"":strLastUpdtBy%></td>
						<td nowrap><%=strLastUpdtDate==null?"":strLastUpdtDate%></td>
					  </tr>
<%
				}
				if(alAssign.size() == 0) { 
%>
				<tr class="tdTableCellStyle"> 
					<td colspan="13"><font class="promptReadOnly"><b><%=strAssignmentPM1%></b></font></td>
			  	</tr>
<%
				}
%>
        	</table>
		</td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr>
	
</table>
</form>
		<div style="float:right;">
		 <a class="btn_red" href="javascript:closeDlg();">Close</a>		 
		</div>
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