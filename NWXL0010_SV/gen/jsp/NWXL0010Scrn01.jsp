<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100916074003 --%>
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
<fmt:setBundle basename="I18N_NWXL0010Scrn01" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NWXL0010Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NWXL0010Scrn01.title" bundle="${I18N_SCREEN_ID}">Report Entry</fmt:message>">
			
			
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

<!--
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Report Dwnld" class="pTab_ON"><a href="#">Report Dwnld</a></li>
									</div>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
-->

				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<div class="pTab_BODY">

					<table border="0" cellspacing="3" cellpadding="0" style="margin-top:5px; margin-left:10px;" width="100%">
						<tr>
							<td>
								<table border="1" cellspacing="0" cellpadding="1">
									<col width="130">
								
									<tr>
										<!-- Report ID / Name -->
										<td class="pClothBs"><fmt:message key="i18n.NWXL0010Scrn01.label.1" bundle="${I18N_SCREEN_ID}">Report ID / Name</fmt:message></td>
										<td><ezf:inputText name="rptSqlId_01" ezfName="rptSqlId_01" otherAttr=" size=\"10\" ezftoupper=\"\""/></td>
										<td><ezf:inputText name="rptSqlNm_01" ezfName="rptSqlNm_01" otherAttr=" size=\"100\""/></td>
									</tr>
									<tr>
										<!-- Function ID -->
										<td class="pClothBs"><fmt:message key="i18n.NWXL0010Scrn01.label.2" bundle="${I18N_SCREEN_ID}">Function ID</fmt:message></td>
										<td colspan="2">
											<ezf:select name="rptSqlFuncId_01" ezfName="rptSqlFuncId_01" ezfCodeName="rptSqlFuncId_CL" ezfDispName="rptSqlFuncId_NL" />
										</td>
									</tr>
									<tr>
										<!-- Data Timing -->
										<td class="pClothBs"><fmt:message key="i18n.NWXL0010Scrn01.label.3" bundle="${I18N_SCREEN_ID}">Data Timing</fmt:message></td>
										<td colspan="2">
											<ezf:select name="rptSqlDataTmgTxt_01" ezfName="rptSqlDataTmgTxt_01" ezfCodeName="rptSqlDataTmgTxt_CL" ezfDispName="rptSqlDataTmgTxt_NL" />
										</td>
									</tr>
									<tr>
										<!-- Requestor / Date -->
										<td class="pClothBs"><fmt:message key="i18n.NWXL0010Scrn01.label.4" bundle="${I18N_SCREEN_ID}">Requestor / Date</fmt:message></td>
										<td colspan="2">
											<ezf:inputText name="rptSqlReqPsnCd_01" ezfName="rptSqlReqPsnCd_01" otherAttr=" size=\"8\" ezftoupper=\"\""/>
											<ezf:inputText name="rptSqlReqDt_01" ezfName="rptSqlReqDt_01" otherAttr=" size=\"10\" maxlength=\"10\""/>&nbsp;<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rptSqlReqDt_01', 4 );">
										</td>
									</tr>
									<tr>
										<!-- SQL Creator / Date -->
										<td class="pClothBs"><fmt:message key="i18n.NWXL0010Scrn01.label.5" bundle="${I18N_SCREEN_ID}">SQL Creator / Date</fmt:message></td>
										<td colspan="2">
											<ezf:inputText name="rptSqlRgtnPsnCd_01" ezfName="rptSqlRgtnPsnCd_01" otherAttr=" size=\"8\" ezftoupper=\"\""/>
											<ezf:inputText name="rptSqlRgtnDt_01" ezfName="rptSqlRgtnDt_01" otherAttr=" size=\"10\" maxlength=\"10\""/>&nbsp;<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rptSqlRgtnDt_01', 4 );">
											</td>
									</tr>
									<tr>
										<!-- Description -->
										<td class="pClothBs" valign="top"><fmt:message key="i18n.NWXL0010Scrn01.label.6" bundle="${I18N_SCREEN_ID}">Description</fmt:message></td>
										<td colspan="2"><ezf:textArea name="rptSqlDescTxt_01" ezfName="rptSqlDescTxt_01" otherAttr=" rows=\"3\" cols=\"109\""/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				
					<hr>

					<table border="0" cellspacing="3" cellpadding="0" style="margin-left:10px;" width="100%">
						<tr>
							<td>
								<table border="1" cellspacing="0" cellpadding="1">
								
									<tr>
										<!-- SQL -->
										<td class="pClothBs" align="center"><fmt:message key="i18n.NWXL0010Scrn01.label.7" bundle="${I18N_SCREEN_ID}">SQL</fmt:message></td>
									</tr>
									<tr>
										<td><ezf:textArea name="xxRptSqlTxt_01" ezfName="xxRptSqlTxt_01" otherAttr=" rows=\"31\" cols=\"153\""/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
				</div>
				
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
