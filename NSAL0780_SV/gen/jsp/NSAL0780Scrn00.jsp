<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170302092148 --%>
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
<fmt:setBundle basename="I18N_NSAL0780Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSAL0780Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0780Scrn00.title" bundle="${I18N_SCREEN_ID}">Fleet Rollup Search</fmt:message>">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="<fmt:message key="i18n.NSAL0780Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Fleet Rollup Search</fmt:message>" class="pTab_ON"><a href="#">Rlup Srch</a></li>
									</div>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<div class="pTab_BODY">
				<table border="0" width="99%" align="center">
					<col width="" align="" valign="top">
					<col width="" align="" valign="top">
					<col width="" align="" valign="top">
					<col width="" align="" valign="bottom">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="100" align="left">
								<col width="100">
								<col width="30">
								<col width="90" align="left">
								<col width="100">
								<col width="30">
								<col width="90" align="left">
								<col width="100">
								<col width="30">
								<col width="160" align="left">
								<col width="30">
								<col width="100" align="left">
								<tr height="21">
									<td class="stab"><ezf:anchor name="dsAcctNm_LK" ezfName="dsAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Customer" ><fmt:message key="i18n.NSAL0780Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Customer Name(*)</fmt:message></ezf:anchor></td>
									<td><ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" otherAttr=" size=\"15\" maxlength=\"360\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:anchor name="serNum_LK" ezfName="serNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Serial" ><fmt:message key="i18n.NSAL0780Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Machine Serial#</fmt:message></ezf:anchor></td>
									<td><ezf:inputText name="serNum_H" ezfName="serNum_H" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:anchor name="dsContrNum_LK" ezfName="dsContrNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Contract" ><fmt:message key="i18n.NSAL0780Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Contract#</fmt:message></ezf:anchor></td>
									<td><ezf:inputText name="dsContrNum_H" ezfName="dsContrNum_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" /><fmt:message key="i18n.NSAL0780Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Meter Read is not completed</fmt:message></td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr height="21">
									<td class="stab"><ezf:anchor name="dsAcctNum_LK" ezfName="dsAcctNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Customer" ><fmt:message key="i18n.NSAL0780Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Customer#</fmt:message></ezf:anchor></td>
									<td><ezf:inputText name="dsAcctNum_H" ezfName="dsAcctNum_H" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><fmt:message key="i18n.NSAL0780Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Billing From Date</fmt:message></td>
									<td><ezf:inputText name="bllgFromDt_H" ezfName="bllgFromDt_H" value="99/99/9999" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgFromDt_H', 4);"></td>
									<td>-</td>
									<td class="stab"><fmt:message key="i18n.NSAL0780Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Billing Thru Date</fmt:message></td>
									<td><ezf:inputText name="bllgThruDt_H" ezfName="bllgThruDt_H" value="99/99/9999" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgThruDt_H', 4);"></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" /><fmt:message key="i18n.NSAL0780Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Errors Only</fmt:message></td>
									<td>&nbsp;</td>
									<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>

<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
				<table width="99%">
					<tr align="right">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
								<col width="200">
								<col width="40">
								<col width="218">
								<col width="120">
								<col width="520">
								<tr height='25px'>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td align="right" class="stab">
										<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
											<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"           value="A" />
											<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
											<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
											<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
											<jsp:param name="ViewMode"          value="FULL" />
										</jsp:include>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- ######################################## DETAIL ######################################## --%>
<%@ page import = "com.canon.cusa.s21.framework.ZYP.common.ZYPConstant" %>
<%@ page import = "business.servlet.NSAL0780.NSAL0780BMsg" %>
<% NSAL0780BMsg bMsg = (NSAL0780BMsg) databean.getEZDBMsg(); %>
				<table>
					<tr>
						<td>
							<div id='rightTblHead' style="width:1098px; display:block; overflow:hidden; margin:0px;padding:0px;">
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" width="1098px" style="margin-right:20px" >
									<col width="50"  align="center">	<!-- Detail -->
									<col width="78" align="center">		<!-- Contract# -->
									<col width="160" align="center">	<!-- Counter Name -->
									<col width="80" align="center">		<!-- Bill From -->
									<col width="80" align="center">		<!-- Bill To -->
									<col width="115" align="center">	<!-- Rolled Up Reading -->
									<col width="98" align="center">		<!-- Test Copies -->
									<col width="80"  align="center">	<!-- Read Completed -->
									<col width="130"  align="center">	<!-- Process Flag -->
									<col width="225" align="center">	<!-- Process Message -->
									<tr height="25px">
										<td class="pClothBs colFix">&nbsp;</td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0780Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Contract#</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0780Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Counter Name</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0780Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Bill From</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0780Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Bill To</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0780Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Rolled Up Reading</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0780Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Test Copies</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0780Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Read Comp</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0780Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Process Flag</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0780Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Process Message</fmt:message></td>
									</tr>
								</table>
							</div>
							<div id="rightTbl" style="width:1115px; height:420px; display:block; overflow:scroll; margin:0px; padding:0px;" >
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A" width="1098px">
									<col width="50"  align="center">	<!-- Detail -->
									<col width="78" align="left">		<!-- Contract# -->
									<col width="160" align="left">		<!-- Counter Name -->
									<col width="80" align="left">		<!-- Bill From -->
									<col width="80" align="left">		<!-- Bill To -->
									<col width="115" align="right">		<!-- Rolled Up Reading -->
									<col width="98" align="right">		<!-- Test Copies -->
									<col width="80"  align="left">		<!-- Read Completed -->
									<col width="130"  align="left">		<!-- Process Flag -->
									<col width="225" align="left">		<!-- Process Message -->
									<% int i = 0; %>
									<ezf:row ezfHyo="A">
										<tr height="25px">
											<% if (bMsg.A.no(i).xxBtnFlg_A.getValue().equals(ZYPConstant.FLG_ON_Y)) { %>
											<td rowspan=<%= bMsg.A.no(i).xxRowNum_A.getValue() %>><ezf:inputButton name="Detail" value="Detail" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" otherAttr=" id=\"Detail\""/></td>
											<% } %>
											<td><ezf:inputText name="dsContrNum_A" ezfName="dsContrNum_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="mtrLbDescTxt_A" ezfName="mtrLbDescTxt_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:label name="bllgFromDt_A" ezfName="bllgFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="bllgThruDt_A" ezfName="bllgThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="curFleetReadMtrCnt_A" ezfName="curFleetReadMtrCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="testCopyQty_A" ezfName="testCopyQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="mtrEntryCpltFlg_A" ezfName="mtrEntryCpltFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="fleetCalcProcDescTxt_A" ezfName="fleetCalcProcDescTxt_A" value="ReBill InComplete" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td><ezf:inputText name="vldMsgTxt_A" ezfName="vldMsgTxt_A" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										</tr>
										<% i++; %>
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

<style TYPE="text/css">
<!--
	tr.checkLineBGcolor{background-color:yellow;}
	a img{border-style:none;}
-->
</style>


<%-- **** Task specific area ends here **** --%>
