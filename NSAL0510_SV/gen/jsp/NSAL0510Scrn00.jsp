<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171222144915 --%>
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
<fmt:setBundle basename="I18N_NSAL0510Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSAL0510Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0510Scrn00.title" bundle="${I18N_SCREEN_ID}">Sub Contract Search</fmt:message>">

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
										<li title="<fmt:message key="i18n.NSAL0510Scrn00.title" bundle="${I18N_SCREEN_ID}">Sub Contract Search</fmt:message>" class="pTab_ON"><a href="#">Sub Cont Srch</a></li>
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
								<col width="80" align="left"> <%-- Supplier Site label --%>
								<col width=""> <%-- Vnedor value --%>
								<col width="30">
								<col width="50" align="left"> <%-- Account label --%>
								<col width=""> <%-- Account value --%>
								<col width="30">
								<col width="80" align="left"> <%-- Contract label --%>
								<col width=""> <%-- Contract value --%>
								<col width="30">
								<col width="80" align="left"> <%-- Contract Status label --%>
								<col width=""> <%-- Contract Status value --%>
								<col width="100"> <%-- Search Button --%>
								<tr>
									<td class="stab"><ezf:anchor name="vndCd_LK" ezfName="vndCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Vendor" ><fmt:message key="i18n.NSAL0510Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Supplier Site</fmt:message></ezf:anchor></td>
									<td><ezf:inputText name="vndCd_H" ezfName="vndCd_H" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:anchor name="dsAcctNum_LK" ezfName="dsAcctNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Account" ><fmt:message key="i18n.NSAL0510Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Account</fmt:message></ezf:anchor></td>
									<td><ezf:inputText name="dsAcctNum_H" ezfName="dsAcctNum_H" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><fmt:message key="i18n.NSAL0510Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Contract#</fmt:message></td>
									<td><ezf:inputText name="dsContrNum_H" ezfName="dsContrNum_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><fmt:message key="i18n.NSAL0510Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Contract Status</fmt:message></td>
									<td align="left">
										<ezf:select name="dsContrCtrlStsNm_H" ezfName="dsContrCtrlStsNm_H" ezfBlank="1" ezfCodeName="dsContrCtrlStsCd_L" ezfDispName="dsContrCtrlStsNm_L" otherAttr=" id=\"dsContrCtrlSts\" style=\"width:128px\""/>
									</td>
									<td></td>
								</tr>
								<tr>
									<td class="stab"><fmt:message key="i18n.NSAL0510Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Dealer Fleet#</fmt:message></td>
									<td><ezf:inputText name="dlrFleetNum_H" ezfName="dlrFleetNum_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:anchor name="serNum_LK" ezfName="serNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Serial" ><fmt:message key="i18n.NSAL0510Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></ezf:anchor></td>
									<td><ezf:inputText name="serNum_H" ezfName="serNum_H" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab"><fmt:message key="i18n.NSAL0510Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Active Status</fmt:message></td>
									<td align="left">
										<ezf:select name="mdlActvFlg_H" ezfName="mdlActvFlg_H" ezfBlank="1" ezfCodeName="mdlActvFlg_L" ezfDispName="xxScrItem10Txt_L" otherAttr=" id=\"mdlActvFlg\" style=\"width:128px\""/>
									</td>
									<td>&nbsp;</td>
									<td class="stab"><ezf:anchor name="t_MdlNm_LK" ezfName="t_MdlNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ModelName" ><fmt:message key="i18n.NSAL0510Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Model Name (*)</fmt:message></ezf:anchor></td>
									<td><ezf:inputText name="t_MdlNm_H" ezfName="t_MdlNm_H" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
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
							<ezf:skip>
									<table border="0" cellpadding="1" cellspacing="0">
										<col >
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="10">
										<col width="0">
										<col width="1">
										<col width="0">
										<tr>
											<td class="stab">Showing</td>
											<td class="pOut">1</td>
											<td class="stab">to</td>
											<td class="pOut">3</td>
											<td class="stab">of</td>
											<td class="pOut">1000</td>
											<td>&nbsp;</td>
											<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
											<td></td>
											<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
										</tr>
									</table>
							</ezf:skip>

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
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- ######################################## DETAIL ######################################## --%>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<col align="left" valign="top">
					<tr>
						<td>
							<div id="Top" style="overflow-x:hidden; width:1122;">
								<table border="1" cellpadding="1" cellspacing="0" width="" height="24">
									<col width="85"  align="center">		<!-- Supplier Site Code -->
									<col width="130" align="center">		<!-- Supplier Site Name -->
									<col width="85"  align="center">		<!-- Account Number -->
									<col width="120" align="center">		<!-- Account Name -->
									<col width="70"  align="center">		<!-- Contract# -->
									<col width="85"  align="center">		<!-- Status -->
									<col width="60"  align="center">		<!-- Fleet# -->
									<col width="90"  align="center">		<!-- Serial# -->
									<col width="110" align="center">		<!-- Model Name -->
									<col width="90"  align="center">		<!-- Effective From -->
									<col width="90"  align="center">		<!-- Effective Thru -->
									<col width="40"  align="center">		<!-- Detail -->
									<tr>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'vndCd_A')"><fmt:message key="i18n.NSAL0510Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Supplier Site Code</fmt:message></a><img id="sortIMG.vndCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'prntVndNm_A')"><fmt:message key="i18n.NSAL0510Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Supplier Site Name</fmt:message></a><img id="sortIMG.prntVndNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNum_A')"><fmt:message key="i18n.NSAL0510Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Account Code</fmt:message></a><img id="sortIMG.dsAcctNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A')"><fmt:message key="i18n.NSAL0510Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Account Name</fmt:message></a><img id="sortIMG.dsAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')"><fmt:message key="i18n.NSAL0510Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Contract#</fmt:message></a><img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrItem10Txt_A')"><fmt:message key="i18n.NSAL0510Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Status</fmt:message></a><img id="sortIMG.xxScrItem10Txt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dlrFleetNum_A')"><fmt:message key="i18n.NSAL0510Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Fleet#</fmt:message></a><img id="sortIMG.dlrFleetNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')"><fmt:message key="i18n.NSAL0510Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></a><img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 't_MdlNm_A')"><fmt:message key="i18n.NSAL0510Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Model Name</fmt:message></a><img id="sortIMG.t_MdlNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effFromDt_A')"><fmt:message key="i18n.NSAL0510Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Effective From</fmt:message></a><img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effThruDt_A')"><fmt:message key="i18n.NSAL0510Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Effective Thru</fmt:message></a><img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs">&nbsp;</td>
									</tr>
								</table>
							</div>
							<div id="Detail" style="width:1122; overflow-y:scroll; height:436;">
								<table id="A" border="1" cellpadding="1" cellspacing="0">
									<col width="85"  align="center">		<!-- Supplier Site Code -->
									<col width="130" align="center">		<!-- Supplier Site Name -->
									<col width="85"  align="center">		<!-- Account Number -->
									<col width="120" align="center">		<!-- Account Name -->
									<col width="70"  align="center">		<!-- Contract# -->
									<col width="85"  align="center">		<!-- Status -->
									<col width="60"  align="center">		<!-- Fleet# -->
									<col width="90"  align="center">		<!-- Serial# -->
									<col width="110" align="center">		<!-- Model Name -->
									<col width="90"  align="center">		<!-- Effective From -->
									<col width="90"  align="center">		<!-- Effective Thru -->
									<col width="40"  align="center">		<!-- Detail -->
									<ezf:row ezfHyo="A">
										<tr>
											<td>
												<ezf:inputText name="vndCd_A" ezfName="vndCd_A" value="050039" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"vndCd_A#{EZF_ROW_NUMBER}\" size=\"11\" maxlength=\"20\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="prntVndNm_A" ezfName="prntVndNm_A" value="BARRISTER GLOBAL SERVICES INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"prntVndNm_A#{EZF_ROW_NUMBER}\" size=\"17\" maxlength=\"240\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="dsAcctNum_A" ezfName="dsAcctNum_A" value="0000072" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsAcctNum_A#{EZF_ROW_NUMBER}\" size=\"11\" maxlength=\"28\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="PECKAR & ABRAMSON CORP" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsAcctNm_A#{EZF_ROW_NUMBER}\" size=\"16\" maxlength=\"60\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="dsContrNum_A" ezfName="dsContrNum_A" value="1009647" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsContrNum_A#{EZF_ROW_NUMBER}\" size=\"9\" maxlength=\"30\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="xxScrItem10Txt_A" ezfName="xxScrItem10Txt_A" value="Active" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem10Txt_A#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"30\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="dlrFleetNum_A" ezfName="dlrFleetNum_A" value="1123123" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dlrFleetNum_A#{EZF_ROW_NUMBER}\" size=\"7\" maxlength=\"30\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="serNum_A" ezfName="serNum_A" value="USCB001082" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"serNum_A#{EZF_ROW_NUMBER}\" size=\"12\" maxlength=\"30\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="t_MdlNm_A" ezfName="t_MdlNm_A" value="HPLI8000" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"t_MdlNm_A#{EZF_ROW_NUMBER}\" size=\"15\" maxlength=\"50\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="02/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"effFromDt_A#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="05/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"effThruDt_A#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/>
											</td>
											<td>
												<ezf:inputButton name="Detail" value="Detail" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" otherAttr=" id=\"Detail\""/>
											</td>
											<td class="pAuditInfo">
												<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
											</td>
										</tr>
										<ezf:skip>
										<tr>
											<td>
												<input id="vndCd_A#{EZF_ROW_NUMBER}" type="text" size="11" maxlength="20" class="pPro" value="050040" name="vndCd_A" ezfname="vndCd_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="prntVndNm_A#{EZF_ROW_NUMBER}" type="text" size="17" maxlength="60" class="pPro" value="BPS CORE SERVICES, BPC" name="prntVndNm_A" ezfname="prntVndNm_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="11" maxlength="28" class="pPro" value="0000072" name="dsAcctNum_A" ezfname="dsAcctNum_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="dsAcctNm_A#{EZF_ROW_NUMBER}" type="text" size="16" maxlength="60" class="pPro" value="PECKAR & ABRAMSON CORP" name="dsAcctNm_A" ezfname="dsAcctNm_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="dsContrNum_A#{EZF_ROW_NUMBER}" type="text" size="9" maxlength="30" class="pPro" value="1009686" name="dsContrNum_A" ezfname="dsContrNum_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="xxScrItem10Txt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="30" class="pPro" value="Active" name="xxScrItem10Txt_A" ezfname="xxScrItem10Txt_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="dlrFleetNum_A#{EZF_ROW_NUMBER}" type="text" size="7" maxlength="30" class="pPro" value="3424234" name="dlrFleetNum_A" ezfname="dlrFleetNum_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="serNum_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="30" class="pPro" value="JPDL66W053" name="serNum_A" ezfname="serNum_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="t_MdlNm_A#{EZF_ROW_NUMBER}" type="text" size="15" maxlength="50" class="pPro" value="HPLI9040" name="t_MdlNm_A" ezfname="t_MdlNm_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="effFromDt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="02/01/2015" name="effFromDt_A" ezfname="effFromDt_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="effThruDt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="05/01/2015" name="effThruDt_A" ezfname="effThruDt_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input type="button" class="pBtn2" value="Detail" name="Detail" onclick="sendServer('Detail','{EZF_ROW_NUMBER}')" id="Detail" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input id="vndCd_A#{EZF_ROW_NUMBER}" type="text" size="11" maxlength="20" class="pPro" value="050041" name="vndCd_A" ezfname="vndCd_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="prntVndNm_A#{EZF_ROW_NUMBER}" type="text" size="17" maxlength="60" class="pPro" value="COPY WORLD BUSINESS SOLUTIONS" name="prntVndNm_A" ezfname="prntVndNm_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="dsAcctNum_A#{EZF_ROW_NUMBER}" type="text" size="11" maxlength="28" class="pPro" value="0000072" name="dsAcctNum_A" ezfname="dsAcctNum_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="dsAcctNm_A#{EZF_ROW_NUMBER}" type="text" size="16" maxlength="60" class="pPro" value="PECKAR & ABRAMSON CORP" name="dsAcctNm_A" ezfname="dsAcctNm_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="dsContrNum_A#{EZF_ROW_NUMBER}" type="text" size="9" maxlength="30" class="pPro" value="1746571" name="dsContrNum_A" ezfname="dsContrNum_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="xxScrItem10Txt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="30" class="pPro" value="Active" name="xxScrItem10Txt_A" ezfname="xxScrItem10Txt_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="dlrFleetNum_A#{EZF_ROW_NUMBER}" type="text" size="7" maxlength="30" class="pPro" value="3534543" name="dlrFleetNum_A" ezfname="dlrFleetNum_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="serNum_A#{EZF_ROW_NUMBER}" type="text" size="12" maxlength="30" class="pPro" value="QGF06776" name="serNum_A" ezfname="serNum_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="t_MdlNm_A#{EZF_ROW_NUMBER}" type="text" size="15" maxlength="50" class="pPro" value="IR1730" name="t_MdlNm_A" ezfname="t_MdlNm_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="effFromDt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="02/01/2015" name="effFromDt_A" ezfname="effFromDt_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input id="effThruDt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="05/01/2015" name="effThruDt_A" ezfname="effThruDt_A" ezfhyo="A" tabindex="-1">
											</td>
											<td>
												<input type="button" class="pBtn2" value="Detail" name="Detail" onclick="sendServer('Detail','{EZF_ROW_NUMBER}')" id="Detail" ezfhyo="A">
											</td>
										</tr>
										</ezf:skip>
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

<script type="text/javascript">

	function clickImg(prntObj, idx) {
		var eventNm = prntObj.all(0).value;
		sendServer(eventNm, idx);
	}

	function changeBGColor(chkObj, idx) {
		var bgColorCls  = "";
		var origBgColor = document.getElementById("xxTblItemTxt_A#" + idx).value;
		
		if (chkObj.checked) {
			bgColorCls = "checkLineBGcolor";
			if (bgColorCls != origBgColor) {
				document.getElementById("xxTblItemTxt_A#" + idx).value =
					document.getElementById("A_RightTr#"  + idx).className;
			}
		} else {
			bgColorCls = origBgColor;
		}
		document.getElementById("A_RightTr#" + idx).className = bgColorCls;
	}
	
</script>


<%-- **** Task specific area ends here **** --%>
