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
<fmt:setBundle basename="I18N_NWXL0010Scrn02" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NWXL0010Scrn02">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NWXL0010Scrn02.title" bundle="${I18N_SCREEN_ID}">Report Download History</fmt:message>">
			
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

					<table border="0" cellspacing="0" cellpadding="1" style="margin-top:5px; margin-left:250px;">
						<tr>
							<td>
								<table  border="0" cellspacing="0" cellpadding="0" width="605">
									<tr>
										<!-- Pagination -->
										<td align="right">
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"			value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"			value="B" />
												<jsp:param name="ShowingFrom"		value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"			value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"			value="xxPageShowOfNum" />
												<jsp:param name="ShowingCurrent"	value="xxPageShowCurNum" />
												<jsp:param name="ShowingTotal"		value="xxPageShowTotNum" />
												<jsp:param name="ViewMode"			value="FULL" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td a>
								<table border="1" cellspacing="0" cellpadding="1" width="605">
									<col align="center" width="75">
									<col align="center" width="350">
									<col align="center" width="70">
									<col align="center" width="80">
								
									<tr>
										<td class="pClothBs" colspan="2"><fmt:message key="i18n.NWXL0010Scrn02.label.1" bundle="${I18N_SCREEN_ID}">Report</fmt:message></td>
										<td class="pClothBs" colspan="2"><fmt:message key="i18n.NWXL0010Scrn02.label.2" bundle="${I18N_SCREEN_ID}">Download</fmt:message></td>
									</tr>
									<tr>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rptSqlId_B' )"><fmt:message key="i18n.NWXL0010Scrn02.label.3" bundle="${I18N_SCREEN_ID}">ID</fmt:message><img id="sortIMG.rptSqlId_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rptSqlNm_B' )"><fmt:message key="i18n.NWXL0010Scrn02.label.4" bundle="${I18N_SCREEN_ID}">Name</fmt:message><img id="sortIMG.rptSqlNm_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rptSqlReqPsnCd_B' )"><fmt:message key="i18n.NWXL0010Scrn02.label.5" bundle="${I18N_SCREEN_ID}">User</fmt:message><img id="sortIMG.rptSqlReqPsnCd_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rptSqlReqDt_B' )"><fmt:message key="i18n.NWXL0010Scrn02.label.6" bundle="${I18N_SCREEN_ID}">Date</fmt:message><img id="sortIMG.rptSqlReqDt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
									</tr>
								</table>
										
								<div style="overflow-y:scroll; height:498;">
									<table id="B" border="1" cellspacing="0" cellpadding="1" width="605">
										<col align="left"   width="75">
										<col align="left"   width="350">
										<col align="center" width="70">
										<col align="center" width="80">
										
										<ezf:row ezfHyo="B">
											<tr>
												<td><ezf:label name="rptSqlId_B" ezfName="rptSqlId_B" ezfHyo="B" ezfArrayNo="0" /></td>
												<td style="word-break: break-all;"><ezf:label name="rptSqlNm_B" ezfName="rptSqlNm_B" ezfHyo="B" ezfArrayNo="0" /></td>
												<td><ezf:label name="rptSqlReqPsnCd_B" ezfName="rptSqlReqPsnCd_B" ezfHyo="B" ezfArrayNo="0" /></td>
												<td><ezf:label name="rptSqlReqDt_B" ezfName="rptSqlReqDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
											</tr>
										</ezf:row>
									</table>
								</div>
							
							</td>
						</tr>
					</table>

				</div>
				
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
