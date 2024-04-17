<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171218094810 --%>
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
			<input type="hidden" name="pageID" value="ZZIL0040Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Error Log Details">

<center>
	<table border="0" cellpadding="1" cellspacing="0" width="100%">
		<tr>
			<td>
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr height="48">
							<td align="left" colspan="2">&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td class="stab" align="left" width="12%"><label><b>Request Primary Key</b></label></td>
							<td class="stab" align="left" width="68%"><ezf:label name="mdbInbdIntfcRqstPk_D" ezfName="mdbInbdIntfcRqstPk_D" /></td>
						</tr>
						<tr>
							<td class="stab" align="left" width="12%"><label><b>Interface ID</b></label></td>
							<td class="stab" align="left" width="68%"><ezf:label name="intfcId_D" ezfName="intfcId_D" /></td>
						</tr>
						<tr>
							<td class="stab" align="left" width="12%"><label><b>Business API ID</b></label></td>
							<td class="stab" align="left" width="68%"><ezf:label name="bizApiId_D" ezfName="bizApiId_D" /></td>
						</tr>
						<tr>
							<td class="stab" align="left" width="12%"><label><b>JMS Message ID</b></label></td>
							<td class="stab" align="left" width="68%"><ezf:label name="rqstJmsMsgId_D" ezfName="rqstJmsMsgId_D" /></td>
						</tr>
						<tr>
							<td class="stab" align="left" width="12%"><label><b>Start Time</b></label></td>
							<td class="stab" align="left" width="68%"><ezf:label name="xxDtTm_DS" ezfName="xxDtTm_DS" /></td>
						</tr>
						<tr>
							<td class="stab" align="left" width="12%"><label><b>End Time</b></label></td>
							<td class="stab" align="left" width="68%"><ezf:label name="xxDtTm_DE" ezfName="xxDtTm_DE" /></td>
						</tr>
						<tr>
							<td class="stab" align="left" width="12%"><label><b>Status</b></label></td>
							<td class="stab" align="left" width="68%"><ezf:label name="intfcRqstStsTxt_D" ezfName="intfcRqstStsTxt_D" /></td>
						</tr>
						<tr>
							<td class="stab" align="left" width="12%"><label><b>OnBatch Type</b></label></td>
							<td class="stab" align="left" width="68%"><ezf:label name="onBatTpNm_D" ezfName="onBatTpNm_D" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="stab" align="left" colspan="2"><label><b>Details</b></label></td>
						</tr>
						<tr>
							<td class="stab" align="left" colspan="2"><ezf:textArea name="errLogDtlTxt_D" ezfName="errLogDtlTxt_D" otherAttr=" rows=\"28\" cols=\"125\""/></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
