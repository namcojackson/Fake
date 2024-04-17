<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20151124110755 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- I18N START --%>
<%@ page import="parts.i18n.*" %>

<%	pageContext.setAttribute("ezdi18nlocale", EZDI18NContext.getInstance().getI18NAccessor().getLocale()); %>
<fmt:setLocale value="${ezdi18nlocale}" scope="request" />
<fmt:setBundle basename="I18N_NSBL0130Scrn00" var="I18N_SCREEN_ID" scope="request" />
<fmt:setBundle basename="I18N" var="I18N_DEFAULT" scope="request" />
<%-- I18N END --%>

<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NSBL0130Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSBL0130Scrn00.title" bundle="${I18N_SCREEN_ID}">Hold Detail Popup</fmt:message>">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center">
<%-- ######################################## FROM (COMMON)HEADER ######################################## --%>
				<br />
				<table width="739">
					<tr>
						<td>
							<div style="overflow-x:hidden; overflow-y:none; width:722;">
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed" width="498">
									<colgroup>
										<col align="center" width="80">		<!-- FSR # -->
										<col align="center" width="240">	<!-- Serial # -->
										<col align="center" width="240">	<!-- Machine # -->
									</colgroup>
									<tbody>
										<tr height="28">
											<td class="pClothBs"><fmt:message key="i18n.NSBL0130Scrn00.label.1" bundle="${I18N_SCREEN_ID}">FSR#</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSBL0130Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Serial #</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSBL0130Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Machine #</fmt:message></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div style="overflow-x:hidden; overflow-y:hidden; width:739;">
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;word-break:break-all" width="498">
									<colgroup>
										<col align="center" width="80">	<!-- FSR # -->
										<col align="center" width="240">	<!-- Serial # -->
										<col align="center" width="240">	<!-- Machine # -->
									</colgroup>
									<tbody>
										<tr height="28">
											<td><ezf:label name="fsrNum" ezfName="fsrNum" /></td>
											<td><ezf:label name="serNum" ezfName="serNum" /></td>
											<td><ezf:label name="custMachCtrlNum" ezfName="custMachCtrlNum" /></td>
										</tr>
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<br />
<%-- ######################################## TO (COMMON)HEADER ######################################## --%>
				<hr />
<%-- ######################################## FROM (COMMON)DETAIL ######################################## --%>
				<br />
				<table width="739">
					<tr>
						<td>
							<div id="topTBL" style="overflow-x:hidden; overflow-y:none; width:722;">
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed" width="722">
									<colgroup>
										<col align="center" width="80">		<!-- Task # -->
										<col align="center" width="90">		<!-- Hold Reason -->
										<col align="center" width="320">	<!-- Hold Reason Name -->
										<col align="center" width="80">		<!-- Release Date -->
										<col align="center" width="128">	<!-- Release Person -->
									</colgroup>
									<tbody>
										<tr height="28">
											<td class="pClothBs"><fmt:message key="i18n.NSBL0130Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Task #</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSBL0130Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Hold Reason</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSBL0130Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Hold Reason Name</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSBL0130Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Release Date</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSBL0130Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Release Person</fmt:message></td>
										</tr>
									</tbody>
								</table>
							</div>

							<div id="bottomTBL" style="overflow-x:hidden; overflow-y:scroll; width:739; height:402;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
								<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;word-break:break-all" width="722">
									<colgroup>
										<col align="center" width="80">		<!-- Task # -->
										<col align="center" width="90">		<!-- Hold Reason -->
										<col align="center" width="320">	<!-- Hold Reason Name -->
										<col align="center" width="80">		<!-- Release Date -->
										<col align="center" width="128">	<!-- Release Person -->
									</colgroup>
									<tbody>
										<ezf:row ezfHyo="A">
										<tr height="28">
											<td><ezf:label name="svcTaskNum" ezfName="svcTaskNum" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="svcTaskHldRsnCd" ezfName="svcTaskHldRsnCd" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="svcTaskHldRsnNm" ezfName="svcTaskHldRsnNm" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="svcTaskRelDt" ezfName="svcTaskRelDt" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="svcTaskRelUsrId" ezfName="svcTaskRelUsrId" ezfHyo="A" ezfArrayNo="0" /></td>
										</tr>
										</ezf:row>
										<ezf:skip>
										<tr height="28">
											<td><label>ZZZZZZZZZZ</label></td>
											<td><label>ZZZ</label></td>
											<td><label>ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4</label></td>
											<td><label>MM/DD/YYYY</label></td>
											<td><label>ZZZZZZZZZ1ZZZZZZ</label></td>
										</tr>
										<tr height="28">
											<td><label>ZZZZZZZZZZ</label></td>
											<td><label>ZZZ</label></td>
											<td><label>ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4</label></td>
											<td><label>MM/DD/YYYY</label></td>
											<td><label>ZZZZZZZZZ1ZZZZZZ</label></td>
										</tr>
										<tr height="28">
											<td><label>ZZZZZZZZZZ</label></td>
											<td><label>ZZZ</label></td>
											<td><label>ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4</label></td>
											<td><label>MM/DD/YYYY</label></td>
											<td><label>ZZZZZZZZZ1ZZZZZZ</label></td>
										</tr>
										<tr height="28">
											<td><label>ZZZZZZZZZZ</label></td>
											<td><label>ZZZ</label></td>
											<td><label>ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4</label></td>
											<td><label>MM/DD/YYYY</label></td>
											<td><label>ZZZZZZZZZ1ZZZZZZ</label></td>
										</tr>
										</ezf:skip>
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
<%-- ######################################## TO (COMMON)DETAIL ######################################## --%>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
