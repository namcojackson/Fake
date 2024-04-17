<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20191218104848 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NLCL0650Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Tech PI Message">

<center>
	<table width="90%">
		<tr>
			<td valign="top">
				<div style="word-break:break-all">
					<table border="0" cellpadding="0" cellspacing="0" style="margin-top:200px;">
						<tr>
							<td style="word-break:keep-all;">
<!-- START 2019/12/17 T.Ogura [QC#54986,MOD] -->
								<!-- <label ezfout name="xxPopPrm" ezfname="xxPopPrm">Your Physical Inventory counting process has been completed and the results have been submitted to your manager.  Please contact your manager to approve.  Your inventory will not be available until the approval is complete.</label> -->
<%@ page import="business.servlet.NLCL0650.NLCL0650BMsg" %>
<%  NLCL0650BMsg bMsg = (NLCL0650BMsg)databean.getEZDBMsg(); %>
								<% String btnLabel = bMsg.xxScrItem20Txt.getValue(); %>
								<% if ("Close".equals(btnLabel)) { %>
									Your Physical Inventory has been completed with all variances reconciled.<br>
									Navigate to S21 Reports, Physical Inventory Report for details.
								<% } else { %>
									Variances exists in your uploaded counts.<br>
									Please review the counts and parts in the file. If you have changes to that file or not, you must<br>re-upload a file.<br>
									<b>WARNING: Failure to re-upload a file before re-submitting will results in zeroing out all parts for the Physical.</b>
								<% } %>
<!-- END   2019/12/17 T.Ogura [QC#54986,MOD] -->
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
