<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230522132942 --%>
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
<fmt:setBundle basename="I18N_NSAL0520Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSAL0520Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0520Scrn00.title" bundle="${I18N_SCREEN_ID}">Configuration History Popup</fmt:message>">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<div class="pTab_BODY">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<col width="" align="right" valign="center">
						<tr height="30">
							<td>
<ezf:skip>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="54"  align="center">
									<col width="32"  align="right">
									<col width="16"  align="center">
									<col width="32"  align="right">
									<col width="16"  align="center">
									<col width="32"  align="right">
									<col width="10">
									<col>
									<col width="1">
									<col>
									<tr>
										<td class="stab">Showing</td>
										<td class="pOut">9999</td>
										<td class="stab">to</td>
										<td class="pOut">9999</td>
										<td class="stab">of</td>
										<td class="pOut">9999</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Prev" name="PagePrev"></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
									</tr>
								</table>
</ezf:skip>
								<table width="500">
									<tr align="right">
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
						<col width="524" align="left" valign="top">
						<col align="left" valign="top">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width="80" align="center">
									<col width="80" align="center">
									<col width="80" align="center">
									<col width="80" align="center">
									<col width="115" align="center">
									<col width="87" align="center">
									<tr height="35">
										<td class="pClothBs"><fmt:message key="i18n.NSAL0520Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Config#</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0520Scrn00.label.2" bundle="${I18N_SCREEN_ID}">IB ID</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0520Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0520Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Item Code</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0520Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Item Name</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0520Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Svc Model</fmt:message></td>
									</tr>
								</table>
								<div id="Left" style="width:524; overflow-x:hidden; height:455; overflow-y:hidden;">
									<table id="A_Left" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width="80" align="right">
										<col width="80" align="right">
										<col width="80" align="left">
										<col width="80" align="left">
										<col width="115" align="left">
										<col width="87" align="left">
										<ezf:row ezfHyo="A">
											<tr height="23">
												<td><ezf:inputText name="svcConfigMstrPk_A" ezfName="svcConfigMstrPk_A" value="0123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"svcConfigMstrPk_A#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" value="0123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="serNum_A" ezfName="serNum_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWW5W" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="WWWWWWWWW1WWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\""/></td>
												<td><ezf:inputText name="mdlNm_A" ezfName="mdlNm_A" value="WWWWWWWWW1W" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" id=\"mdlNm_A#{EZF_ROW_NUMBER}\""/></td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><input type="text" class="pPro" size="10" value="0123456789" id="svcConfigMstrPk_A#{EZF_ROW_NUMBER}" name="svcConfigMstrPk_A" ezfname="svcConfigMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="0123456789" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="serNum_A" ezfname="serNum_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWW5W" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="11" value="WWWWWWWWW1W" id="mdlNm_A#{EZF_ROW_NUMBER}" name="mdlNm_A" ezfname="mdlNm_A" ezfhyo="A"></td>
											</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
							<td>
								<div id="RightTop" style="width:466; overflow-x:hidden;" onScroll="synchroScrollLeft(this.id, new Array('Right'));">
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width="80" align="center">
										<col width="80" align="center">
										<col width="115" align="center">
										<col width="115" align="center">
										<col width="75" align="center">
										<col width="115" align="center">
										<col width="255" align="center">
										<tr height="35">
											<td class="pClothBs"><fmt:message key="i18n.NSAL0520Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Install Date</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSAL0520Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Remove Date</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSAL0520Scrn00.label.9" bundle="${I18N_SCREEN_ID}">IB Owner</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSAL0520Scrn00.label.10" bundle="${I18N_SCREEN_ID}">IB Bill To</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSAL0520Scrn00.label.11" bundle="${I18N_SCREEN_ID}">IB Ship To Acct#</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSAL0520Scrn00.label.12" bundle="${I18N_SCREEN_ID}">IB Ship To</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSAL0520Scrn00.label.13" bundle="${I18N_SCREEN_ID}">IB Ship To Address</fmt:message></td>
										</tr>
									</table>
								</div>
								<div id="Right" style="width:484; overflow-x:scroll; height:470; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array('RightTop') ); synchroScrollTop(this.id, new Array('Left') );">
									<table id="A_Right" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width="80" align="center">
										<col width="80" align="center">
										<col width="115" align="left">
										<col width="115" align="left">
										<col width="75" align="left">
										<col width="115" align="left">
										<col width="255" align="left">
										<ezf:row ezfHyo="A">
											<tr height="23">
												<td><ezf:label name="istlDt_A" ezfName="istlDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="svcMachRmvDt_A" ezfName="svcMachRmvDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="billToDsAcctNm_A" ezfName="billToDsAcctNm_A" value="WWWWWWWWW1WWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" id=\"billToDsAcctNm_A#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="sellDsAcctNm_A" ezfName="sellDsAcctNm_A" value="WWWWWWWWW1WWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\""/></td>
												<td><ezf:inputText name="shipToCustCd_A" ezfName="shipToCustCd_A" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="shipDsAcctNm_A" ezfName="shipDsAcctNm_A" value="WWWWWWWWW1WWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\""/></td>
												<td><ezf:inputText name="xxGenlFldAreaTxt_A" ezfName="xxGenlFldAreaTxt_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\" id=\"xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}\""/></td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
											<tr height="23">
												<td><label ezfout name="istlDt_A" ezfname="istlDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><label ezfout name="svcMachRmvDt_A" ezfname="svcMachRmvDt_A" ezfhyo="A" >99/99/9999</label></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" id="billToDsAcctNm_A#{EZF_ROW_NUMBER}" name="billToDsAcctNm_A" ezfname="billToDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="sellDsAcctNm_A" ezfname="sellDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1" name="shipToCustCd_A" ezfname="shipToCustCd_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="15" value="WWWWWWWWW1WWWW5" name="shipDsAcctNm_A" ezfname="shipDsAcctNm_A" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="35" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWW5" id="xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}" name="xxGenlFldAreaTxt_A" ezfname="xxGenlFldAreaTxt_A" ezfhyo="A"></td>
											</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
                        <tr>
                          <table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
                              <tr height="23" >
                                    <td align="left">
                                        <ezf:inputButton name="Download" value="Download" htmlClass="pBtn6" />
                                    </td>
                              </tr>
                          </table>
                        </tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
