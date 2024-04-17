<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190129084322 --%>
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
<fmt:setBundle basename="I18N_NSBL0010Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSBL0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSBL0010Scrn00.title" bundle="${I18N_SCREEN_ID}">Service Dispatch Schedule/Dispatch</fmt:message>">

<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		
<!-- ######################################## HEADER ######################################## -->
			<!-- ###TAB - HEAD -->
			<!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

			<%
				String loclTimeCheck = ((business.servlet.NSBL0010.NSBL0010Bean)databean).getXxChkBox_L1();
			%>

			<!-- ###TAB - BODY -->
			<div class="pTab_BODY">
				<table border="0">
					<col width="40" align="right">
					<col width="110">
					<col width="15" align="right">
					<col width="130">
					<col width="40" align="right">
					<col width="75">
					<col width="60" align="right">
					<col width="95">
					<col width="10">
					<col width="90">
					<col width="60" align="right">
					<col width="90">
					<col width="30" align="right">
					<col width="57">
					<col width="30" align="right">
					<col width="60">
					
					<tr>
						<td class="stab">
						<ezf:anchor name="svcContrBrCd_LK" ezfName="svcContrBrCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Branch" otherAttr=" id=\"svcContrBrCd_LK\" ezfanchortext=\"\""><fmt:message key="i18n.NSBL0010Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Branch</fmt:message></ezf:anchor></td>
						<td><ezf:inputText name="svcContrBrCd" ezfName="svcContrBrCd" value="XX3" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/><ezf:inputText name="svcContrBrDescTxt" ezfName="svcContrBrDescTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"10\" maxlength=\"50\" style=\"background-color:transparent;\""/></td>
						<td class="stab"><Div Align="right"><fmt:message key="i18n.NSBL0010Scrn00.label.2" bundle="${I18N_SCREEN_ID}">BU</fmt:message></td>
						<td><ezf:select name="svcByLineBizTpCd_H" ezfName="svcByLineBizTpCd_H" ezfBlank="1" ezfCodeName="lineBizTpCd_L" ezfDispName="lineBizTpDescTxt_L" otherAttr=" style=\"width:125px;\""/></td>
						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Technician" ><fmt:message key="i18n.NSBL0010Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Tech(*)</fmt:message></ezf:anchor></td>
						<td><ezf:inputText name="techCd" ezfName="techCd" value="---------1" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
						<td class="stab"><fmt:message key="i18n.NSBL0010Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Rcv Date</fmt:message></td>
						<td><ezf:inputText name="svcTaskRcvDt_H1" ezfName="svcTaskRcvDt_H1" value="01/01/2000" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'svcTaskRcvDt_H1', 4 );"></td>
						<td class="stab">-</td>
						<td><ezf:inputText name="svcTaskRcvDt_H2" ezfName="svcTaskRcvDt_H2" value="01/01/2000" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'svcTaskRcvDt_H2', 4 );"></td>
						<td class="stab"><fmt:message key="i18n.NSBL0010Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Sche Date</fmt:message></td>
						<td><ezf:inputText name="techSchdFromDt" ezfName="techSchdFromDt" value="01/01/2000" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'techSchdFromDt', 4 );"></td>
						<td><ezf:inputCheckBox name="xxChkBox_HO" ezfName="xxChkBox_HO" value="Y" /></td>
						<td class="stab"><fmt:message key="i18n.NSBL0010Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Only Hold</fmt:message></td>
						<td><ezf:inputCheckBox name="xxChkBox_L0" ezfName="xxChkBox_L0" value="Y" /></td>
						<td class="stab"><fmt:message key="i18n.NSBL0010Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Local Time</fmt:message></td>
					</tr>
				</table>

				<table border="0">
					<col width="40" align="right">
					<col width="85">
					<col width="40" align="right">
					<col width="130">
					<col width="40" align="right">
					<col width="75">
					<col width="30">
					<col width="287">
					<col width="138" align="right">
					<col width="100">
					
					<tr>
						<td class="stab"><fmt:message key="i18n.NSBL0010Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Task(*)</fmt:message></td>
						<td><ezf:inputText name="svcTaskNum" ezfName="svcTaskNum" value="---------1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
						<td class="stab"><fmt:message key="i18n.NSBL0010Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Status</fmt:message></td>
						<td><ezf:select name="svcTaskStsCd_H3" ezfName="svcTaskStsCd_H3" ezfBlank="1" ezfCodeName="svcTaskStsCd_H1" ezfDispName="svcTaskStsNm_H2" otherAttr=" style=\"width:125px;\""/></td>
						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" ><fmt:message key="i18n.NSBL0010Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Ship to</fmt:message></ezf:anchor></td>
						<td><ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" value="---------1---------2" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
						<td><ezf:inputButton name="SearchShipToName" value=">>" htmlClass="pBtn0" /></td>
						<td class="stab"><ezf:inputText name="locNm" ezfName="locNm" value="---------1---------2---------3---------4---------5---------6" otherAttr=" size=\"40\" maxlength=\"60\" style=\"background-color:transparent;\" tabindex=\"-1\""/></td>
						<td></td>
						<td></td>
					</tr>
				</table>

				<table border="0">
					<col width="40" align="right">
					<col width="85">
					<col width="40" align="right">
					<col width="110">
					<col width="60" align="right">
					<col width="85">
					<col width="40" align="right">
					<col width="130">
					<col width="40" align="right">
					<col width="100">
					<col width="78" align="right">
					<col width="70">
					<col width="100">

					<tr>
						<td class="stab"><fmt:message key="i18n.NSBL0010Scrn00.label.11" bundle="${I18N_SCREEN_ID}">FSR(*)</fmt:message></td>
						<td><ezf:inputText name="fsrNum" ezfName="fsrNum" value="---------1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
						<td class="stab"><fmt:message key="i18n.NSBL0010Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Visit</fmt:message></td>
						<td><ezf:inputText name="fsrVisitNum" ezfName="fsrVisitNum" value="---------1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
						<td class="stab"><fmt:message key="i18n.NSBL0010Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Model Name</fmt:message></td>
						<td><ezf:inputText name="mdlNm" ezfName="mdlNm" value="---------1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
						<td class="stab"><fmt:message key="i18n.NSBL0010Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Call Tp</fmt:message></td>
						<td><ezf:select name="dsSvcCallTpCd_H3" ezfName="dsSvcCallTpCd_H3" ezfBlank="1" ezfCodeName="dsSvcCallTpCd_H1" ezfDispName="xxStCdListTxt_H2" otherAttr=" style=\"width:125px;\""/></td>
						<td class="stab"><fmt:message key="i18n.NSBL0010Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Bill Cd</fmt:message></td>
						<td><ezf:select name="svcBillTpCd_H3" ezfName="svcBillTpCd_H3" ezfBlank="1" ezfCodeName="svcBillTpCd_H1" ezfDispName="svcBillTpDescTxt_H2" otherAttr=" style=\"width:150px;\""/></td>
						<td><ezf:inputCheckBox name="xxChkBox_DW" ezfName="xxChkBox_DW" value="Y" /></td>
						<td class="stab"><fmt:message key="i18n.NSBL0010Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Only Down</fmt:message></td>
						<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" /></td>
					</tr>
				</table>

				<table border="0">
				</table>

				<hr>

	<!-- ######################################## DETAIL ######################################## -->
				<%-- ******************************* --%>
				<%-- *** Table Area(Detail) *** --%>
				<%-- ******************************* --%>
				<div id="TBL_D" align="center">

					<table border="0" cellpadding="0" cellspacing="0" width="100%" style="table-layout:fixed;">
						<tr>
							<td align="right" valign="top" width="620">
								<%-- ******************************* --%>
								<%-- *** Left Table Area(Header) *** --%>
								<%-- ******************************* --%>
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
									<col width="22" align="center">
									<col width="110" align="center">
									<col width="27" align="center">
									<col width="34" align="center">
									<col width="35" align="center">
									<col width="80" align="center">
									<col width="46" align="center">
									<col width="86" align="center">
									<col width="180" align="center">
									<tr height="18">
										<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0010Scrn00.label.17" bundle="${I18N_SCREEN_ID}">All<br></fmt:message><ezf:inputCheckBox name="xxChkBox_AL" ezfName="xxChkBox_AL" value="Y" onClick="sendServer('AllCheck')" /></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'techCd_A')"><fmt:message key="i18n.NSBL0010Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Tech</fmt:message><img id="sortIMG.techCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0010Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Aval</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0010Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Notes</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0010Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Br</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0010Scrn00.label.22" bundle="${I18N_SCREEN_ID}">Branch Nm</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0010Scrn00.label.2" bundle="${I18N_SCREEN_ID}">BU</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0010Scrn00.label.23" bundle="${I18N_SCREEN_ID}">Model</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0010Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Accept Date</fmt:message></td>
									</tr>

									<tr height="19">
										<td class="pClothBs" colspan="7"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxDtTm_A4')" id="xxDtTm"><fmt:message key="i18n.NSBL0010Scrn00.label.25" bundle="${I18N_SCREEN_ID}">Schedulling Date</fmt:message><img id="sortIMG.xxDtTm_A4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0010Scrn00.label.26" bundle="${I18N_SCREEN_ID}">ETA Date</fmt:message></td>
									</tr>
								</table>
								<%-- ******************************* --%>
								<%-- *** Left Table Area(Detail) *** --%>
								<%-- ******************************* --%>
								<div id="LeftTBL" style="overflow-y:hidden; height:394; overflow-x:hidden;"
									onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
									<table border="1" cellpadding="1" cellspacing="0" id="A_Left" style="table-layout:fixed;">
										<col width="22" align="center">
										<col width="110" align="left">
										<col width="27" align="center">
										<col width="34" align="center">
										<col width="35" align="center">
										<col width="80" align="center">
										<col width="46" align="center">
										<col width="86" align="center">
										<col width="180" align="center">
										<ezf:row ezfHyo="A">
										<tr height="25" ezfhyo="A" id="A_leftTBLRow01#{EZF_ROW_NUMBER}">
										<% if ("Y".equals(loclTimeCheck)) { %>
<!-- check box -->							<td rowspan="3">
										<% } else  { %>
											<td rowspan="2">
										<% } %>
												<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
<!-- Tech -->								<td><ezf:inputButton name="OpenWin_TechnicianDetail" value="Tech" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /><ezf:inputText name="techCd_A" ezfName="techCd_A" value="---------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td>
												<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_TechnicianRecommended" >
<!-- Aval -->										<ezf:label name="techAvalFlg_A" ezfName="techAvalFlg_A" ezfHyo="A" ezfArrayNo="0" />
												</ezf:anchor>
											</td>
											<td>
												<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ServiceMemo" >
<!-- Notes -->										<ezf:label name="xxBtnFlg_AM" ezfName="xxBtnFlg_AM" ezfHyo="A" ezfArrayNo="0" />
												</ezf:anchor>
											</td>
<!-- Branch -->								<td><ezf:label name="svcContrBrCd_A" ezfName="svcContrBrCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- Branch Name -->						<td><ezf:inputText name="svcContrBrDescTxt_A" ezfName="svcContrBrDescTxt_A" value="---------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
<!-- BU -->									<td><ezf:inputText name="svcByLineBizTpCd_A" ezfName="svcByLineBizTpCd_A" value="---------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
<!-- Model Name -->							<td><ezf:inputText name="mdlNm_A" ezfName="mdlNm_A" value="---------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
<!-- Accept Date -->						<td align="left">
												<ezf:label name="techAcptDt_A" ezfName="techAcptDt_A" ezfHyo="A" ezfArrayNo="0" />&nbsp;&nbsp;&nbsp;&nbsp;
												<ezf:label name="xxDtTm_A6" ezfName="xxDtTm_A6" ezfHyo="A" ezfArrayNo="0" />
												<ezf:label name="xxTechAcptTz_A" ezfName="xxTechAcptTz_A" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<td class="pAuditInfo">
												<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A1\""/>
												<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A1\""/>
												<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A1\""/>
												<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A1\""/>
												<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A1\""/>
											</td>
										</tr>
										<tr height="24" ezfhyo="A" id="A_leftTBLRow02#{EZF_ROW_NUMBER}">
											<td colspan="7" align="left">
<!-- Schedule Date -->							<ezf:inputText name="techSchdFromDt_A" ezfName="techSchdFromDt_A" value="01/01/2000" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\" id=\"techSchdFromDt_A#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'techSchdFromDt_A#{EZF_ROW_NUMBER}', 4 );"
												>&nbsp;<ezf:inputText name="xxDtTm_A1" ezfName="xxDtTm_A1" value="00:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onfocus=\"onFocusEvent(this);\" onblur=\"onBlurEvent(this);\"" otherAttr=" maxlength=\"5\" size=\"5\" ezftoupper=\"\""/><fmt:message key="i18n.NSBL0010Scrn00.label.27" bundle="${I18N_SCREEN_ID}">&nbsp;-&nbsp;</fmt:message><ezf:inputText name="techSchdToDt_A" ezfName="techSchdToDt_A" value="01/01/2000" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\" id=\"techSchdToDt_A#{EZF_ROW_NUMBER}\" style=\"margin-left: 2;\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'techSchdToDt_A#{EZF_ROW_NUMBER}', 4 );"
												>&nbsp;<ezf:inputText name="xxDtTm_A2" ezfName="xxDtTm_A2" value="00:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onfocus=\"onFocusEvent(this);\" onblur=\"onBlurEvent(this);\"" otherAttr=" maxlength=\"5\" size=\"5\" ezftoupper=\"\""/>
												<ezf:inputText name="techSchdTz_A" ezfName="techSchdTz_A" value="EST" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"5\" size=\"3\""/>
											</td>
											<td align="left">
<!-- ETA Date -->								<ezf:inputText name="fsrVisitEtaDt_A" ezfName="fsrVisitEtaDt_A" value="01/01/2000" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\" id=\"fsrVisitEtaDt_A#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'fsrVisitEtaDt_A#{EZF_ROW_NUMBER}', 4 );"
												>&nbsp;<ezf:inputText name="xxDtTm_A5" ezfName="xxDtTm_A5" value="00:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onfocus=\"onFocusEvent(this);\" onblur=\"onBlurEvent(this);\"" otherAttr=" maxlength=\"5\" size=\"5\" ezftoupper=\"\""/>
												<ezf:inputText name="xxFsrVisitEtaTz_A" ezfName="xxFsrVisitEtaTz_A" value="EST" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"5\" size=\"3\""/>
											</td>
											<td class="pAuditInfo">
												<ezf:inputHidden name="xxRecHistCratTs_A2" ezfName="xxRecHistCratTs_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A2\""/>
												<ezf:inputHidden name="xxRecHistCratByNm_A2" ezfName="xxRecHistCratByNm_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A2\""/>
												<ezf:inputHidden name="xxRecHistUpdTs_A2" ezfName="xxRecHistUpdTs_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A2\""/>
												<ezf:inputHidden name="xxRecHistUpdByNm_A2" ezfName="xxRecHistUpdByNm_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A2\""/>
												<ezf:inputHidden name="xxRecHistTblNm_A2" ezfName="xxRecHistTblNm_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A2\""/>
											</td>
										</tr>
										<% if ("Y".equals(loclTimeCheck)) { %>
										<tr height="24" ezfhyo="A" id="A_leftTBLRow03#{EZF_ROW_NUMBER}">
											<td colspan="7" align="left">
<!-- Schedule Date -->							<ezf:inputText name="techSchdFromDt_AL" ezfName="techSchdFromDt_AL" value="01/01/2000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\" id=\"techSchdFromDt_A#{EZF_ROW_NUMBER}\""/><ezf:inputText name="xxDtTm_L1" ezfName="xxDtTm_L1" value="00:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onfocus=\"onFocusEvent(this);\" onblur=\"onBlurEvent(this);\"" otherAttr=" size=\"5\" ezftoupper=\"\" style=\"margin-left:23;\""/>&nbsp;-&nbsp;<ezf:inputText name="techSchdToDt_AL" ezfName="techSchdToDt_AL" value="01/01/2000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\" id=\"techSchdToDt_A#{EZF_ROW_NUMBER}\" style=\"margin-left: 2;\""/><ezf:inputText name="xxDtTm_L2" ezfName="xxDtTm_L2" value="00:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onfocus=\"onFocusEvent(this);\" onblur=\"onBlurEvent(this);\"" otherAttr=" size=\"5\" ezftoupper=\"\" style=\"margin-left:23;\""/>
												<ezf:inputText name="techSchdTz_AL" ezfName="techSchdTz_AL" value="PST" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\""/>
											</td>
											<td align="left">
<!-- ETA Date -->								<ezf:inputText name="fsrVisitEtaDt_AL" ezfName="fsrVisitEtaDt_AL" value="01/01/2000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\" id=\"fsrVisitEtaDt_AL#{EZF_ROW_NUMBER}\""/><ezf:inputText name="xxDtTm_L5" ezfName="xxDtTm_L5" value="00:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onfocus=\"onFocusEvent(this);\" onblur=\"onBlurEvent(this);\"" otherAttr=" size=\"5\" ezftoupper=\"\" style=\"margin-left:23;\""/>
												<ezf:inputText name="xxFsrVisitEtaTz_AL" ezfName="xxFsrVisitEtaTz_AL" value="PST" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\""/>
											</td>
											<td class="pAuditInfo">
												<ezf:inputHidden name="xxRecHistCratTs_A3" ezfName="xxRecHistCratTs_A3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A3\""/>
												<ezf:inputHidden name="xxRecHistCratByNm_A3" ezfName="xxRecHistCratByNm_A3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A3\""/>
												<ezf:inputHidden name="xxRecHistUpdTs_A3" ezfName="xxRecHistUpdTs_A3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A3\""/>
												<ezf:inputHidden name="xxRecHistUpdByNm_A3" ezfName="xxRecHistUpdByNm_A3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A3\""/>
												<ezf:inputHidden name="xxRecHistTblNm_A3" ezfName="xxRecHistTblNm_A3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A3\""/>
											</td>
										</tr>
										<% } %>
										</ezf:row>
										<ezf:skip>
										<script type="text/javascript">
											for(i=0; i<30; i++) {
												var str = '';
												if (i % 2 == 0) { str = 'class="pEvenNumberBGcolor"'; }
												document.write(
													'<tr ' + str + ' height="25">'
													+ '<td rowspan="3">'
													+ '<input type="checkbox" value="Y"</td>'
													+ '<td><input type="button" class="pBtn0" value="Tech">'
													+ '<input type="text" class="pHsu" size="10" maxlength="20" value="---------1---------2"></td>'
													+ '<td><a href="#" onclick=""><label>Y</label></a></td>'
													+ '<td><a href="#" onclick=""><label>Y</label></a></td>'
													+ '<td><label>3</label></td>'
													+ '<td><label>012345</label></td>'
													+ '<td><label>01234567890123456789012345678901234567890123456789</label></td>'
													+ '<td><label>0123456789</label></td>'
													+ '<td  align="left">'
													+ '<label style="margin-left:1;">01/01/2000</label> '
													+ '<label style="margin-left:23;">00:00</label> '
													+ '<label style="margin-left:4;">EST</label> '
													+ '</td>'
													+ '</tr>'
													+ '<tr ' + str + ' height="24">'
													+ '<td colspan="7" align="left">'
													+ '<input type="text" class="pHsu" maxlength="10" size="10" value="01/01/2000"><img src="./img/calendar.gif" class="pCalendar"> '
													+ '<input type="text" class="pHsu" maxlength="5" size="5" value="00:00">'
													+ ' - '
													+ '<input type="text" class="pHsu" maxlength="10" size="10" value="01/01/2000" style="margin-left: 2;"><img src="./img/calendar.gif" class="pCalendar"> '
													+ '<input type="text" class="pHsu" maxlength="5" size="5" value="00:00:00"> '
													+ '<input type="text" class="pPro" size="3" value="EST">'
													+ '</td>'
													+ '<td align="left">'
													+ '<input type="text" class="pHsu" maxlength="10" size="10" value="01/01/2000"><img src="./img/calendar.gif" class="pCalendar"> '
													+ '<input type="text" class="pHsu" maxlength="5" size="5" value="00:00"> '
													+ '<input type="text" class="pPro" size="3" value="EST">'
													+ '</td>'
													+ '</tr>'
													+ '<tr ' + str + ' height="24" ezfhyo="A"> '
													+ '<td colspan="7" align="left"> '
													+ '<input type="text" class="pPro" size="10" value="01/01/2000" '
													+ '><input type="text" class="pPro" size="5" value="00:00" onfocus="onFocusEvent(this);" onblur="onBlurEvent(this);" style="margin-left:23;" '
													+ '><fmt:message key="i18n.NSBL0010Scrn00.label.27" bundle="${I18N_SCREEN_ID}">&nbsp;-&nbsp;</fmt:message><input type="text" class="pPro"size="10"value="01/01/2000" style="margin-left: 2;" '
													+ '><input type="text" class="pPro" size="5" value="00:00" onfocus="onFocusEvent(this);" onblur="onBlurEvent(this);" style="margin-left:23;"> '
													+ '<input type="text" class="pPro" size="3" value="PST"> '
													+ '</td>'
													+ '<td align="left"> '
													+ '<input type="text" class="pPro" size="10" value="01/01/2000" '
													+ '><input type="text" class="pPro" size="5" value="00:00" onfocus="onFocusEvent(this);" onblur="onBlurEvent(this);" style="margin-left:23;"> '
													+ '<input type="text" class="pPro" size="3" value="PST"> '
													+ '</td>'
													+ '</tr>'
											);
											}
										</script>
										</ezf:skip>
									</table>
								</div>
							</td>

							<td valign="top">
								<%-- ******************************** --%>
								<%-- *** Right Table Area(Header) *** --%>
								<%-- ******************************** --%>
								<div id="topRightTBL" style="overflow-y:none; height:20; overflow-x:hidden; width:488;">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
										<col width="32" align="center">
										<col width="40" align="center">
										<col width="42" align="center">
										<col width="150" align="center">
										<col width="110" align="center">
										<col width="47" align="center">
										<col width="61" align="center">
										<col width="80" align="center">
										<col width="80" align="center">
										<col width="50" align="center">
										<!--11/18/2015 Delete -->
										<!--<col width="32" align="center">-->
										<col width="62" align="center">
										<col width="83" align="center">
										<tr height="18">
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcCallPrtyCd_A')" id="svcCallPrtyCd"><fmt:message key="i18n.NSBL0010Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Pty</fmt:message><img id="sortIMG.svcCallPrtyCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxBtnFlg_AE')"><fmt:message key="i18n.NSBL0010Scrn00.label.29" bundle="${I18N_SCREEN_ID}">Excs</fmt:message><img id="sortIMG.xxBtnFlg_AE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsSvcCallTpCd_A')"><fmt:message key="i18n.NSBL0010Scrn00.label.30" bundle="${I18N_SCREEN_ID}">CallTp</fmt:message><img id="sortIMG.dsSvcCallTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs" colspan="2"><fmt:message key="i18n.NSBL0010Scrn00.label.31" bundle="${I18N_SCREEN_ID}">Symptom</fmt:message></td>
											<td class="pClothBs" colspan="2"><fmt:message key="i18n.NSBL0010Scrn00.label.32" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcTaskNum_A')"><fmt:message key="i18n.NSBL0010Scrn00.label.33" bundle="${I18N_SCREEN_ID}">Task#</fmt:message><img id="sortIMG.svcTaskNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><fmt:message key="i18n.NSBL0010Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Status</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSBL0010Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Ship to</fmt:message></td>
											<!--11/18/2015 Change -->
											<!--<td class="pClothBs" colspan="3">Ship to Name</td>-->
											<td class="pClothBs" colspan="2"><fmt:message key="i18n.NSBL0010Scrn00.label.34" bundle="${I18N_SCREEN_ID}">Ship to Name</fmt:message></td>
										</tr>
										<tr height="20">
											<td class="pClothBs"><fmt:message key="i18n.NSBL0010Scrn00.label.35" bundle="${I18N_SCREEN_ID}">o.Pty</fmt:message></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'machDownFlg_A')"><fmt:message key="i18n.NSBL0010Scrn00.label.36" bundle="${I18N_SCREEN_ID}">Down</fmt:message><img id="sortIMG.machDownFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcBillTpCd_A')"><fmt:message key="i18n.NSBL0010Scrn00.label.37" bundle="${I18N_SCREEN_ID}">BillCd</fmt:message><img id="sortIMG.svcBillTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcTaskRcvDt_A')"><fmt:message key="i18n.NSBL0010Scrn00.label.38" bundle="${I18N_SCREEN_ID}">Received Date</fmt:message><img id="sortIMG.svcTaskRcvDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'custAvalFromHourMn_A')"><fmt:message key="i18n.NSBL0010Scrn00.label.39" bundle="${I18N_SCREEN_ID}">Aval Hour</fmt:message><img id="sortIMG.custAvalFromHourMn_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcRspTmMnAot_A')"><fmt:message key="i18n.NSBL0010Scrn00.label.40" bundle="${I18N_SCREEN_ID}">Res.Gr</fmt:message><img id="sortIMG.svcRspTmMnAot_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxTotAmt_A')"><fmt:message key="i18n.NSBL0010Scrn00.label.41" bundle="${I18N_SCREEN_ID}">Res.Tm</fmt:message><img id="sortIMG.xxTotAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'fsrNum_A')"><fmt:message key="i18n.NSBL0010Scrn00.label.42" bundle="${I18N_SCREEN_ID}">FSR#</fmt:message><img id="sortIMG.fsrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'fsrVisitNum_A')"><fmt:message key="i18n.NSBL0010Scrn00.label.43" bundle="${I18N_SCREEN_ID}">Visit#</fmt:message><img id="sortIMG.fsrVisitNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcCrHldFlg_A')"><fmt:message key="i18n.NSBL0010Scrn00.label.44" bundle="${I18N_SCREEN_ID}">Hold</fmt:message><img id="sortIMG.svcCrHldFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- 11/18/2015 Delete -->
											<!--<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxBtnFlg_AC')">C/O<img id="sortIMG.xxBtnFlg_AC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>-->
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'techAcptFlg_A')"><fmt:message key="i18n.NSBL0010Scrn00.label.45" bundle="${I18N_SCREEN_ID}">Tech Acpt</fmt:message><img id="sortIMG.techAcptFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'schdDisptEmlFlg_A')" id="schdDisptEmlFlg"><fmt:message key="i18n.NSBL0010Scrn00.label.46" bundle="${I18N_SCREEN_ID}">Notfy</fmt:message><img id="sortIMG.schdDisptEmlFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										</tr>
									</table>
								</div>
								<%-- ******************************** --%>
								<%-- *** Right Table Area(Detail) *** --%>
								<%-- ******************************** --%>
								<div id="RightTBL" style="overflow-y:scroll; height:411; overflow-x:scroll; width:505;" 
									onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
									<table border="1" cellpadding="1" cellspacing="0" id="A_Right" style="table-layout:fixed;">
										<col width="32" align="center">
										<col width="40" align="center">
										<col width="42" align="center">
										<col width="150" align="center">
										<col width="110" align="center">
										<col width="47" align="right">
										<col width="61" align="right">
										<col width="80" align="center">
										<col width="80" align="center">
										<col width="50" align="center">
										<!--11/18/2015 Delete-->
										<!--<col width="32" align="center">-->
										<col width="62" align="center">
										<col width="83" align="center">
										<ezf:row ezfHyo="A">
										<tr height="25" ezfhyo="A" id="A_rightTBLRow01#{EZF_ROW_NUMBER}">
<!-- Pty -->								<td><ezf:inputText name="svcCallPrtyCd_A" ezfName="svcCallPrtyCd_A" value="12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\" id=\"svcCallPrtyCd_A#{EZF_ROW_NUMBER}\""/></td>
<!-- Excs -->								<td><ezf:label name="xxBtnFlg_AE" ezfName="xxBtnFlg_AE" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- Call Tp -->							<td><ezf:label name="dsSvcCallTpCd_A" ezfName="dsSvcCallTpCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td colspan="2">
<!-- Symptom code -->							<ezf:inputText name="svcPblmSympTpCd_A" ezfName="svcPblmSympTpCd_A" value="012" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"4\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/>
<!-- Symptom name -->							<ezf:inputText name="svcPblmSympTpNm_A" ezfName="svcPblmSympTpNm_A" value="---------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/>
											</td>
<!-- Serial# -->							<td colspan="2" align="center"><ezf:inputText name="serNum_A" ezfName="serNum_A" value="---------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
<!-- Task# -->								<td>
												<!--11/18/2015 Delete-->
												<!--<a href="#" ezfhyo="A" onclick="sendServer('OpenWin_ServiceCallEntry')">-->
													<ezf:label name="svcTaskNum_A" ezfName="svcTaskNum_A" ezfHyo="A" ezfArrayNo="0" />
												<!--</a>-->
											</td>
<!-- Status -->								<td><ezf:inputText name="svcTaskStsNm_A" ezfName="svcTaskStsNm_A" value="---------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
<!-- Ship to code -->						<td><ezf:inputText name="shipToCustCd_A" ezfName="shipToCustCd_A" value="---------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
<!-- Ship to name -->						<!--11/18/2015 Change-->
											<!--<td colspan="3"><input type="text" size="24" name="locNm_A" ezfname="locNm_A" ezfhyo="A" value="---------1---------2---------3---------4---------5---------6" style="border:none; background-color:transparent;" tabindex="-1"></td>-->
											<td colspan="2"><ezf:inputText name="locNm_A" ezfName="locNm_A" value="---------1---------2---------3---------4---------5---------6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
											<td class="pAuditInfo">
												<ezf:inputHidden name="xxRecHistCratTs_A4" ezfName="xxRecHistCratTs_A4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A4\""/>
												<ezf:inputHidden name="xxRecHistCratByNm_A4" ezfName="xxRecHistCratByNm_A4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A4\""/>
												<ezf:inputHidden name="xxRecHistUpdTs_A4" ezfName="xxRecHistUpdTs_A4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A4\""/>
												<ezf:inputHidden name="xxRecHistUpdByNm_A4" ezfName="xxRecHistUpdByNm_A4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A4\""/>
												<ezf:inputHidden name="xxRecHistTblNm_A4" ezfName="xxRecHistTblNm_A4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A4\""/>
											</td>
										</tr>

										<tr height="24" ezfhyo="A" id="A_rightTBLRow02#{EZF_ROW_NUMBER}">
<!-- o.Pty -->								<td><ezf:label name="origSvcCallPrtyCd_A" ezfName="origSvcCallPrtyCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- Down -->								<td><ezf:label name="machDownFlg_A" ezfName="machDownFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- Bill Cd -->							<td><ezf:label name="svcBillTpCd_A" ezfName="svcBillTpCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td>
<!-- Received Date -->							<ezf:label name="svcTaskRcvDt_A" ezfName="svcTaskRcvDt_A" ezfHyo="A" ezfArrayNo="0" />
												<ezf:label name="xxDtTm_A3" ezfName="xxDtTm_A3" ezfHyo="A" ezfArrayNo="0" />
												<ezf:label name="svcTaskRcvTz_A" ezfName="svcTaskRcvTz_A" ezfHyo="A" ezfArrayNo="0" />
											</td>
<!-- Aval Hour -->							<td><ezf:label name="xxDtTm_H1" ezfName="xxDtTm_H1" ezfHyo="A" ezfArrayNo="0" />-<ezf:label name="xxDtTm_H2" ezfName="xxDtTm_H2" ezfHyo="A" ezfArrayNo="0" />&nbsp;<ezf:label name="tmZoneCd_A" ezfName="tmZoneCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- Res.Gr -->								<td><ezf:label name="xxDtTm_R1" ezfName="xxDtTm_R1" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- Res.Tm -->								<td><ezf:label name="xxDtTm_R2" ezfName="xxDtTm_R2" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- FSR# -->								<td><ezf:label name="fsrNum_A" ezfName="fsrNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- Visit# -->								<td><ezf:label name="fsrVisitNum_A" ezfName="fsrVisitNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td>
												<ezf:anchor name="svcCrHldFlg_AL" ezfName="svcCrHldFlg_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_HoldDetail" otherAttr=" id=\"svcCrHldFlg_AL#{EZF_ROW_NUMBER}\"">
<!-- Hold -->										<ezf:label name="svcCrHldFlg_A" ezfName="svcCrHldFlg_A" ezfHyo="A" ezfArrayNo="0" />
												</ezf:anchor>
											</td>
											<!--11/18/2015 Delete-->
											<!--<td>-->
												<!--<a href="#" name="xxBtnFlg_AL" ezfname="xxBtnFlg_AL" ezfhyo="A" onclick="sendServer('OpenWin_PartsRequestInquiry')" id="xxBtnFlg_AL#{EZF_ROW_NUMBER}">-->
<!-- C/O -->										<!--<label ezfout name="xxBtnFlg_AC" ezfname="xxBtnFlg_AC" ezfhyo="A">Y</label>-->
												<!--</a>-->
											<!--</td>-->
<!-- Tech Acpt -->							<td><ezf:label name="techAcptFlg_A" ezfName="techAcptFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- Notfy -->								<td><ezf:inputCheckBox name="schdDisptEmlFlg_A" ezfName="schdDisptEmlFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"schdDisptEmlFlg_A#{EZF_ROW_NUMBER}\""/></td>
											<td class="pAuditInfo">
												<ezf:inputHidden name="xxRecHistCratTs_A5" ezfName="xxRecHistCratTs_A5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A5\""/>
												<ezf:inputHidden name="xxRecHistCratByNm_A5" ezfName="xxRecHistCratByNm_A5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A5\""/>
												<ezf:inputHidden name="xxRecHistUpdTs_A5" ezfName="xxRecHistUpdTs_A5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A5\""/>
												<ezf:inputHidden name="xxRecHistUpdByNm_A5" ezfName="xxRecHistUpdByNm_A5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A5\""/>
												<ezf:inputHidden name="xxRecHistTblNm_A5" ezfName="xxRecHistTblNm_A5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A5\""/>
											</td>
										</tr>
										<% if ("Y".equals(loclTimeCheck)) { %>
										<tr height="24" ezfhyo="A" id="A_rightTBLRow03#{EZF_ROW_NUMBER}">
											<td colspan="3">&nbsp;</td>
											<td>
<!-- Received Date -->							<ezf:label name="svcTaskRcvDt_AL" ezfName="svcTaskRcvDt_AL" ezfHyo="A" ezfArrayNo="0" />
												<ezf:label name="xxDtTm_L3" ezfName="xxDtTm_L3" ezfHyo="A" ezfArrayNo="0" />
												<ezf:label name="svcTaskRcvTz_AL" ezfName="svcTaskRcvTz_AL" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<td colspan="10">&nbsp;</td>
											<td class="pAuditInfo">
												<ezf:inputHidden name="xxRecHistCratTs_A6" ezfName="xxRecHistCratTs_A6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A6\""/>
												<ezf:inputHidden name="xxRecHistCratByNm_A6" ezfName="xxRecHistCratByNm_A6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A6\""/>
												<ezf:inputHidden name="xxRecHistUpdTs_A6" ezfName="xxRecHistUpdTs_A6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A6\""/>
												<ezf:inputHidden name="xxRecHistUpdByNm_A6" ezfName="xxRecHistUpdByNm_A6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A6\""/>
												<ezf:inputHidden name="xxRecHistTblNm_A6" ezfName="xxRecHistTblNm_A6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A6\""/>
											</td>
										</tr>
										<% } %>
										</ezf:row>
										<ezf:skip>
										<script type="text/javascript">
											for(i=0; i<30; i++) {
												var str = '';
												if (i % 2 == 0) { str = 'class="pEvenNumberBGcolor"'; }
												document.write(
												    '<tr ' + str + ' height="25"> '
													+ '<td><input type="text" size="2" maxlength="2" value="12"></td>'
													+ '<td><label>Y</label></td>'
													+ '<td><label>01</label></td>'
													+ '<td colspan="2">'
													+ '<input type="text" size="4" value="012" style="border:none; background-color:transparent;"> '
													+ '<input class="pOut" type="text" size="26" value="---------1---------2---------3" style="border:none; background-color:transparent;">'
													+ '</td>'
													+ '<td colspan="2"><input type="text" size="10" value="---------1---------2---------3" style="border:none; background-color:transparent;"></td>'
													+ '<td><a href="#"><label>---------1</label></a></td>'
													+ '<td><input type="text" size="10" value="---------1---------2" style="border:none; background-color:transparent;"></td>'
													+ '<td><input type="text" size="6"  value="---------1---------2" style="border:none; background-color:transparent;"></td> '
													+ '<td colspan="3"><input type="text" size="24" value="---------1---------2---------3---------4---------5---------6" style="border:none; background-color:transparent;"></td>'
													+ '</tr>'
													+ '<tr ' + str + ' height="24"> '
													+ '<td><label>12</label></td>'
													+ '<td><label>Y</label></td>'
													+ '<td><label>01</label></td>'
													+ '<td>'
													+ '<label>01/01/2000</label> '
													+ '<label>00:00:00</label> '
													+ '<label>EST</label> '
													+ '</td>'
													+ '<td><label>09:00-17:00 EST</label>'
													+ '<td><label>12</label></td>'
													+ '<td><input type="text" size="4" value="----" style="border:none; background-color:transparent;"></td>'
													+ '<td><label>---------1</label></td>'
													+ '<td><label>---------1</label></td>'
													+ '<td><label>N</label></td>'
													+ '<td><a href="#" onclick=""><label>Y</label></a></td>'
													+ '<td><label>Y</label></td>'
													+ '<td><input type="checkbox" value="Y"></td>'
													+ '</tr>'
													+ '<tr ' + str + ' height="24"> '
													+ '<td colspan="3">&nbsp;</td> '
													+ '<td>'
													+ '<label>01/01/2000</label> '
													+ '<label>00:00:00</label> '
													+ '<label>PST</label> '
													+ '</td>'
													+ '<td colspan="10">&nbsp;</td> '
													+ '</tr>'
											);
											}
										</script>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
					</table>
<%-- Pagenation area start ============================================================== --%>
					<table align="left">
						<col width="20">
						<col width="70">
						<col width="70">
						<col width="70">
						<col width="70">
						<col width="70">
						<col width="730" align="right">
						<col width="100">
						<tr >
<!-- ######################################## BUTTONS ######################################## -->
							<td width="10"/>
							<td><ezf:inputButton name="Schedule" value="Schedule" htmlClass="pBtn7" /></td>
							<td><ezf:inputButton name="Accept" value="Accept" htmlClass="pBtn7" /></td>
							<td><ezf:inputButton name="Cancel" value="Un Dispatch" htmlClass="pBtn7" /></td>
							<td><ezf:inputButton name="Dispatch" value="In Route" htmlClass="pBtn7" /></td>
							<!--11/18/2015 Change-->
							<!--<td><input type="button" class="pBtn7" value="UnSche / Dispt" name="Cancel" onclick="sendServer(this)"></td>-->
							<td><ezf:inputButton name="OpenWin_History" value="History" htmlClass="pBtn7" /></td>
							<td><ezf:inputButton name="UpdateETA" value="Update ETA" htmlClass="pBtn7" /></td>

							<td align="right" width="755">
								<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
								</jsp:include>
								<ezf:skip>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="54"  align="center">
									<col width="40"  align="right">
									<col width="16"  align="center">
									<col width="40"  align="right">
									<col width="16"  align="center">
									<col width="40"  align="right">
									<col width="10">
									<col>
									<col width="1">
									<col>
									<tr>
										<td class="stab">Showing</td>
										<td class="pOut">1</td>
										<td class="stab">to</td>
										<td class="pOut">40</td>
										<td class="stab">of</td>
										<td class="pOut">200</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
									</tr>
								</table>
								</ezf:skip>
							</td>
						</tr>
					</table>
<%-- Pagenation area end ================================================================ --%>
<!-- ######################################## FOOTER ######################################## -->

				</div>

			</div>
		</td>
	</tr>
	</table>
</center>

<%-- ###SCRIPT --%>
<script type="text/javascript">
	function onFocusEvent(item){
		if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
		}
	}

	function onBlurEvent(item){
		if(item.value.length === 1){
			item.value = '0' + item.value.charAt(0) + ':00';
		}else if(item.value.length === 2){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':00';
		}else if(item.value.length === 3){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + '0';
		}else if(item.value.length === 4){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}else if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}
	}
</script>


<%-- **** Task specific area ends here **** --%>
