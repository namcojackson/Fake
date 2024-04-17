<!-- $Header: ITG# 74988 canon_E193_csCFSAccessMsg.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csCFSAccessMsg - CFS Access Message
 |   
 | DESCRIPTION
 |   CFS Access Denied.
 |
 | AUTHOR
 |	Vikas Basal 
 |
 | CREATION DATE
 |	12/05/2006
 |
 | HISTORY
 | DATE		WHO		WHY
 | 12/05/2006	Vikas Basal	Inital version (ITG# 74988)
 |
 |
 +=======================================================================--%>
<%@page language="java" %> 
<%@page import ="java.util.*" %>
<%@ include file="canon_E193_csTopInc.jsp" %>

<%
	// Menu Prompt
	strPageName = "Enter & Inquiry";
%>
<%@ include file="canon_E193_csMenuInc.jsp" %>
<%
	//Get Org ID
	int iOrgId = objCiSession.getOrgId();	
	
%> 

	
	<form name="nonBillingIssueForm" action="canon_E193_csEIHome.jsp" method="post">
	<table class="request-service" align="center" cellpadding="2" cellspacing="0" border="0" bgcolor="#FFFFFF">
	<tr><td height="10" colspan="2"></td></tr>
	<tr>
		<td colspan="2">
			<font class="titleStyle"><b><%=strCFSAccessMsgT1%></b></font>
		</td>
	<tr>  
	<tr><td height="10" colspan="2"></td></tr>
	<tr>
		<td colspan="2">
		</td>
	<tr>  
		
  		<tr>
    		<td colspan="2">&nbsp;</td>
  		</tr>
		<tr>
			<td class="tableQuestionCell" colspan="4"><font class="promptReadOnly" color="#FF0000"><b><%=strCFSAccessMsgM1%></b></font></td>
		</tr>
    	<tr>
    		<td colspan="2"></td>
  		</tr>
    	<tr>
    		<td colspan="2">&nbsp;</td>
  		</tr>
		<tr>
			<td align="right" colspan="2"> 
			</td>
		</tr>
	</table>
	</form>
	
<%@ include file="canon_E193_csBottomInc.jsp" %>