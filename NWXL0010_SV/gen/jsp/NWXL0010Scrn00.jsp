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
<fmt:setBundle basename="I18N_NWXL0010Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NWXL0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NWXL0010Scrn00.title" bundle="${I18N_SCREEN_ID}">Report Download</fmt:message>">
			
			
<%@ page import="business.servlet.NWXL0010.NWXL0010BMsg" %>
<%@ page import="business.servlet.NWXL0010.NWXL0010_ABMsgArray" %>
<%@ page import="business.servlet.NWXL0010.common.NWXL0010CommonLogic" %>

<%
	NWXL0010BMsg        bMsg      = (NWXL0010BMsg)databean.getEZDBMsg();
	NWXL0010_ABMsgArray bMsgArray = bMsg.A;
	
	// has Modification Function?
	final boolean hasModificationFunction = NWXL0010CommonLogic.hasModificationFunction();
%>

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

<!-- ################################################## Search Criteria - [START] ################################################## -->
					<table border="0" cellspacing="3" cellpadding="0" style="margin-top:3px; margin-left:250px;">
						<col width="" align="right">
						<col width="30%" align="left">
					
						<tr>
							<td>
								<table border="1" cellspacing="0" cellpadding="1">
									<col width="90" align="center">
									<col width="70" align="left">
									<col>
								
									<tr>
										<td class="pClothBs" valign="top" style="border-bottom: none;"><fmt:message key="i18n.NWXL0010Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Report (*)</fmt:message></td>
										<!-- Report ID -->
										<td class="pClothBs"><fmt:message key="i18n.NWXL0010Scrn00.label.2" bundle="${I18N_SCREEN_ID}">ID</fmt:message></td>
										<td class=""><ezf:inputText name="rptSqlId" ezfName="rptSqlId" otherAttr=" size=\"10\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<!-- New Report (button) -->
										<td class="pClothBs" style="border-top: none; border-bottom: none;">
											<% if(!hasModificationFunction) out.println("<span style='visibility:hidden'>"); %>
												<ezf:inputButton name="NewReport" value="New Report" htmlClass="cBtn" otherAttr=" tabindex=\"-1\""/>
											<% if(!hasModificationFunction) out.println("</span>"); %>
										</td>
										<!-- Report Name -->
										<td class="pClothBs"><fmt:message key="i18n.NWXL0010Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Name</fmt:message></td>
										<td><ezf:inputText name="rptSqlNm" ezfName="rptSqlNm" otherAttr=" size=\"50\""/></td>
									</tr>
									<tr>
										<!-- Download History (button) -->
										<td class="pClothBs" style="border-top: none;">
											<% if(!hasModificationFunction) out.println("<span style='visibility:hidden'>"); %>
												<ezf:inputButton name="History" value="History" htmlClass="cBtn" otherAttr=" tabindex=\"-1\""/>
											<% if(!hasModificationFunction) out.println("</span>"); %>
										</td>
										<!-- Report Description -->
										<td class="pClothBs"><fmt:message key="i18n.NWXL0010Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Description</fmt:message></td>
										<td align="right"><ezf:inputText name="rptSqlDescTxt" ezfName="rptSqlDescTxt" otherAttr=" size=\"50\""/></td>
									</tr>
								</table>
							</td>
								
							<td valign="bottom">
								<table border="0" cellspacing="0" cellpadding="0">
									<col width="5">
								
									<tr>
										<td></td>
										<!-- Search (button) -->
										<td><ezf:inputButton name="Search" value="Search" htmlClass="cBtn" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<!-- ################################################## Search Criteria - [E N D] ################################################## -->

					<hr>

<!-- ################################################## Search Result - [START] ################################################## -->
					<table border="0" cellspacing="0" cellpadding="1" style="margin-left:2px;">
						<tr>
							<td>
								<table  border="0" cellspacing="0" cellpadding="1" width="1105">
									<tr>
										<!-- Pagination -->
										<td align="right">
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"			value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"			value="A" />
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
							<td>
								<table border="1" cellspacing="0" cellpadding="1" width="1105">
									<col align="center" width="30">
									<col align="center" width="75">
									<col align="center" width="350">
									<col align="center" width="400">
									<col align="center" width="70">
									<col align="center" width="70">
									<col align="center" width="80">
								
									<tr>
										<td class="pClothBs" rowspan="2">&nbsp;</td>
										<td class="pClothBs" colspan="4"><fmt:message key="i18n.NWXL0010Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Report</fmt:message></td>
										<td class="pClothBs" colspan="2"><fmt:message key="i18n.NWXL0010Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Requestor</fmt:message></td>
									</tr>
									<tr>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rptSqlId_A' )"><fmt:message key="i18n.NWXL0010Scrn00.label.2" bundle="${I18N_SCREEN_ID}">ID</fmt:message><img id="sortIMG.rptSqlId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rptSqlNm_A' )"><fmt:message key="i18n.NWXL0010Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Name</fmt:message><img id="sortIMG.rptSqlNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rptSqlDescTxt_A' )"><fmt:message key="i18n.NWXL0010Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Description</fmt:message><img id="sortIMG.rptSqlDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rptSqlDataTmgTxt_A' )">Data<img id="sortIMG.rptSqlDataTmgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rptSqlReqPsnCd_A' )"><fmt:message key="i18n.NWXL0010Scrn00.label.9" bundle="${I18N_SCREEN_ID}">User</fmt:message><img id="sortIMG.rptSqlReqPsnCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rptSqlReqDt_A' )"><fmt:message key="i18n.NWXL0010Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Date</fmt:message><img id="sortIMG.rptSqlReqDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
									</tr>
								</table>
										
								<div style="overflow-y:scroll; height:380;">
									<table id="A" border="1" cellspacing="0" cellpadding="1" width="1105">
										<col align="center" width="30">
										<col align="left"   width="75">
										<col align="left"   width="350">
										<col align="left"   width="400">
										<col align="center" width="70">
										<col align="center" width="70">
										<col align="center" width="80">
										
										<ezf:row ezfHyo="A">
											<tr>
												<td><ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
												<td>
													<ezf:anchor name="rptSqlId_A" ezfName="rptSqlId_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ReportDetail" otherAttr=" id=\"rptSqlId_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
													<ezf:label name="rptSqlId_A" ezfName="rptSqlId_A" ezfHyo="A" ezfArrayNo="0" />
													</ezf:anchor>
												</td>
												<td style="word-break: break-all;"><ezf:label name="rptSqlNm_A" ezfName="rptSqlNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td style="word-break: break-all;"><ezf:label name="rptSqlDescTxt_A" ezfName="rptSqlDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="rptSqlDataTmgTxt_A" ezfName="rptSqlDataTmgTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="rptSqlReqPsnCd_A" ezfName="rptSqlReqPsnCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="rptSqlReqDt_A" ezfName="rptSqlReqDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											</tr>
										</ezf:row>
									</table>
								</div>
							
							</td>
						</tr>
					</table>
<!-- ################################################## Search Result - [E N D] ################################################## -->

				</div>
				
			</td>
		</tr>
	</table>
</center>


<%-- ########## Biz JavaScript - [START] ########## --%>
	<% if (bMsgArray.getValidCount() > 0) { %>
		<script type="text/javascript">
			<% for (int i = 0; i < bMsgArray.getValidCount(); i++) { %>
				<% if (!hasModificationFunction) { %>
					disableAnchor(document.getElementById("rptSqlId_A#"+ <%= i %>));
				<% } %>
			<% } %>
			
			function disableAnchor(e) {
				e.removeAttribute('href');
				e.setAttribute("onclick", new Function(""));
			}
		</script>
	<% } %>
<%-- ########## Biz JavaScript - [E N D] ########## --%>

<%-- **** Task specific area ends here **** --%>
