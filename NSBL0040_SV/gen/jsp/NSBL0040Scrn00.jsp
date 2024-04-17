<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161205153627 --%>
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
<fmt:setBundle basename="I18N_NSBL0040Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSBL0040Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSBL0040Scrn00.title" bundle="${I18N_SCREEN_ID}">Credit Approval</fmt:message>">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table>
						<col width="" align="" valign="top">
						<col width="" align="" valign="top">
						<col width="" align="" valign="top">
						<col width="" align="" valign="top">
						<tr>

							<td>
								<table border="0" cellpadding="1" cellspacing="0" style="margin-left:10px">
									<col width="80">
									<col width="">
									<col width="">
									<col width="">
									<tr>
										<td class="stab"><ezf:anchor name="xxLinkProt_01" ezfName="xxLinkProt_01" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" ><fmt:message key="i18n.NSBL0040Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Bill to</fmt:message></ezf:anchor></td>
										<td><ezf:inputText name="billToCustCd" ezfName="billToCustCd" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_BillTo" value=">>" htmlClass="pBtn2" /></td>
										<td><ezf:inputText name="locNm_01" ezfName="locNm_01" otherAttr=" size=\"28\" tabindex=\"-1\""/></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="xxLinkProt_02" ezfName="xxLinkProt_02" ezfEmulateBtn="1" ezfGuard="OpenWin_SellTo" ><fmt:message key="i18n.NSBL0040Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Sell to</fmt:message></ezf:anchor></td>
										<td><ezf:inputText name="sellToCustCd" ezfName="sellToCustCd" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_SellTo" value=">>" htmlClass="pBtn2" /></td>
										<td><ezf:inputText name="locNm_02" ezfName="locNm_02" otherAttr=" size=\"28\" tabindex=\"-1\""/></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="xxLinkProt_03" ezfName="xxLinkProt_03" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" ><fmt:message key="i18n.NSBL0040Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Ship to</fmt:message></ezf:anchor></td>
										<td><ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_ShipTo" value=">>" htmlClass="pBtn2" /></td>
										<td><ezf:inputText name="locNm_03" ezfName="locNm_03" otherAttr=" size=\"28\" tabindex=\"-1\""/></td>
									</tr>
								</table>
							</td>

							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="80">
									<col width="">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSBL0040Scrn00.label.4" bundle="${I18N_SCREEN_ID}">FSR(*)</fmt:message></td>
										<td><ezf:inputText name="fsrNum" ezfName="fsrNum" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="xxLinkProt_04" ezfName="xxLinkProt_04" ezfEmulateBtn="1" ezfGuard="OpenWin_Tech" ><fmt:message key="i18n.NSBL0040Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Tech</fmt:message></ezf:anchor></td>
										<td><ezf:inputText name="techCd" ezfName="techCd" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>

							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="120">
									<tr>
										<td class="stab"><ezf:anchor name="xxLinkProt_05" ezfName="xxLinkProt_05" ezfEmulateBtn="1" ezfGuard="OpenWin_TaskCreatedBy" ><fmt:message key="i18n.NSBL0040Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Call Created By</fmt:message></ezf:anchor></td>
										<td><ezf:inputText name="ezInUserID" ezfName="ezInUserID" otherAttr=" size=\"12\" maxlength=\"16\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>

						</tr>
					</table>
					<%-- Search --%>
					<table  border="0" cellpadding="0" cellspacing="0" width="100%">
						<col width="" align="right" valign="top">
						<tr>
							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="80">
									<tr>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<hr>
					<%-- Detail --%>
					<table  border="0" cellpadding="0" cellspacing="0" width="100%">
						<col width="" align="right" valign="top">
						<%-- Navigation --%>
						<tr>
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
										<td>&nbsp;</td>
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
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table  border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-left:10px">
						<%-- Detail --%>
						<col width="" align="" valign="top">
						<tr>
							<td>
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
									<col width="25"  align="center">		<!-- Check -->
									<col width="50"  align="center">		<!-- Bill To Cust Code -->
									<col width="190" align="center">		<!-- Bill To Cust Name -->
									<col width="30"  align="center">		<!-- Rsn -->
									<col width="65"  align="center">		<!-- FSR Number -->
									<col width="38"  align="center">		<!-- Notes -->
									<col width="40"  align="center">		<!-- Term Code -->
									<col width="200" align="center">		<!-- Term Name -->
									<col width="80"  align="center">		<!-- Tech -->
									<col width="35"  align="center">		<!-- CCY -->
									<col width="100" align="center">		<!-- Total Charge -->
									<col width="60"  align="center">		<!-- Call Created By -->
									<col width="120" align="center">		<!-- Call Created By Name -->
									<tr>
										<td class="pClothBs">&nbsp;</td>
										<td class="pClothBs" colspan="2"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd_A1')"><fmt:message key="i18n.NSBL0040Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Bill to</fmt:message></a><img id="sortIMG.billToCustCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0040Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Rsn</fmt:message></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'fsrNum_A1')"><fmt:message key="i18n.NSBL0040Scrn00.label.8" bundle="${I18N_SCREEN_ID}">FSR</fmt:message></a><img id="sortIMG.fsrNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0040Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Notes</fmt:message></td>
										<td class="pClothBs" colspan="2"><fmt:message key="i18n.NSBL0040Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Term</fmt:message></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'techCd_A1')"><fmt:message key="i18n.NSBL0040Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Tech</fmt:message></a><img id="sortIMG.techCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0040Scrn00.label.11" bundle="${I18N_SCREEN_ID}">CCY</fmt:message></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcLborDealAmt_A1')"><fmt:message key="i18n.NSBL0040Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Total Charge</fmt:message></a><img id="sortIMG.svcLborDealAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs" colspan="2"><fmt:message key="i18n.NSBL0040Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Call Created By</fmt:message></td>
									</tr>
								</table>
								<div id="" style="overflow-x:hidden; overflow-y:scroll; width:1055; height:377;">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
										<col width="25"  align="center">	<!-- Check -->
										<col width="50"  align="center">	<!-- Bill To Cust Code -->
										<col width="190" align="left">		<!-- Bill To Cust Name -->
										<col width="30"  align="center">	<!-- Rsn -->
										<col width="65"  align="center">	<!-- FSR Number -->
										<col width="38"  align="center">	<!-- Notes -->
										<col width="40"  align="center">	<!-- Term Code -->
										<col width="200" align="left">		<!-- Term Name -->
										<col width="80"  align="center">	<!-- Tech -->
										<col width="35"  align="center">	<!-- CCY -->
										<col width="100" align="right">		<!-- Total Charge -->
										<col width="60"  align="center">	<!-- Call Created By -->
										<col width="120" align="left">		<!-- Call Created By Name -->
										<ezf:row ezfHyo="A">
										<tr id="A#{EZF_ROW_NUMBER}" height="25">
											<td>
												<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="changeBGColor(this, {EZF_ROW_NUMBER});" />
												<ezf:inputHidden name="xxTblItemTxt_A1" ezfName="xxTblItemTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxTblItemTxt_A1#{EZF_ROW_NUMBER}\""/>
											</td>
											<td>
												<ezf:anchor name="xxLinkProt_A1" ezfName="xxLinkProt_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_CreditProfile" ><ezf:label name="billToCustCd_A1" ezfName="billToCustCd_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
											</td>
											<td>
												<ezf:inputText name="locNm_A1" ezfName="locNm_A1" value="WWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\""/>
											</td>
											<td>
												<ezf:anchor name="xxLinkProt_A2" ezfName="xxLinkProt_A2" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_HoldDetail" ><ezf:label name="xxExstFlg_A1" ezfName="xxExstFlg_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
											</td>
											<td>
												<ezf:label name="fsrNum_A1" ezfName="fsrNum_A1" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<td>
												<ezf:anchor name="xxLinkProt_A4" ezfName="xxLinkProt_A4" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_Memo" ><ezf:label name="xxExstFlg_A2" ezfName="xxExstFlg_A2" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
											</td>
											<td>
												<ezf:label name="pmtTermCashDiscCd_A1" ezfName="pmtTermCashDiscCd_A1" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<td>
												<ezf:inputText name="pmtTermCashDiscNm_A1" ezfName="pmtTermCashDiscNm_A1" value="NET 50 DAYS" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\""/>
											</td>
											<td style="word-break:break-all;">
												<ezf:anchor name="xxLinkProt_A5" ezfName="xxLinkProt_A5" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_Tech" otherAttr=" id=\"techLink#{EZF_ROW_NUMBER}\""><ezf:label name="techCd_A1" ezfName="techCd_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
											</td>
											<td>
												<ezf:label name="origInvCcyCd_A1" ezfName="origInvCcyCd_A1" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<td>
												<ezf:label name="svcLborDealAmt_A1" ezfName="svcLborDealAmt_A1" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<td>
												<ezf:inputText name="ezInUserID_A1" ezfName="ezInUserID_A1" value="WWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\""/>
											</td>
											<td>
												<ezf:inputText name="hrTtlNm_A1" ezfName="hrTtlNm_A1" value="Doe,John" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\""/>
											</td>
											<td class="pAuditInfo">
												<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
											</td>
										</tr>
										</ezf:row>
<ezf:skip>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_CreditProfile')" name="xxLinkProt_A1" ezfname="xxLinkProt_A1" ezfhyo="A"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW</label></a>
											</td>
											<td>
												<input type="text" size="25" class="pPro" value="WWWWWWWWWWWWWWWWWWWW" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_HoldDetail')" name="xxLinkProt_A2" ezfname="xxLinkProt_A2" ezfhyo="A"><label ezfout name="xxExstFlg_A1" ezfname="xxExstFlg_A1" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">WWWWWWWW</label>
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Memo')" name="xxLinkProt_A4" ezfname="xxLinkProt_A4" ezfhyo="A"><label ezfout name="xxExstFlg_A2" ezfname="xxExstFlg_A2" ezfhyo="A">Y</label></a>
											</td>
											<td>
												<label ezfout name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" ezfhyo="A">AY</label>
											</td>
											<td>
												<input type="text" size="27" class="pPro" value="NET 50 DAYS" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" ezfhyo="A">
											</td>
											<td>
												<a href="#" onclick="sendServer('OpenWin_Tech')" name="xxLinkProt_A5" ezfname="xxLinkProt_A5" ezfhyo="A" ezfanchortext><label ezfout name="techCd_A1" ezfname="techCd_A1" ezfhyo="A">TEXXXX</label></a>
											</td>
											<td>
												<label ezfout name="origInvCcyCd_A1" ezfname="origInvCcyCd_A1" ezfhyo="A">USD</label>
											</td>
											<td>
												<label ezfout name="svcLborDealAmt_A1" ezfname="svcLborDealAmt_A1" ezfhyo="A">999,999,999</label>
											</td>
											<td>
												<input type="text" size="6" class="pPro" value="WWWWWW" name="ezInUserID_A1" ezfname="ezInUserID_A1" ezfhyo="A">
											</td>
											<td>
												<input type="text" size="15" class="pPro" value="Doe,John" name="hrTtlNm_A1" ezfname="hrTtlNm_A1" ezfhyo="A">
											</td>
										</tr>
</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<%-- Select All, Un Select All --%>
					<table  border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-left:10px">
						<col width="" align="left" valign="top">
						<tr>
							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="80">
									<tr>
										<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn10" /></td>
										<td><ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="pBtn10" /></td>
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
<style type="text/css">
<!--
	tr.checkLineBGcolor{background-color:yellow;}
-->
</style>
<script type="text/javascript">
function changeBGColor(chkObj, idx) {

	var bgColorCls  = "";
	var origBgColor = document.getElementById("xxTblItemTxt_A1#" + idx).value;
	
	if (chkObj.checked) {
		bgColorCls = "checkLineBGcolor";
		if (bgColorCls != origBgColor) {
			document.getElementById("xxTblItemTxt_A1#" + idx).value =
				document.getElementById("A#"  + idx).className;
		}
	} else {
		bgColorCls = origBgColor;
	}
	
	document.getElementById("A#" + idx).className = bgColorCls;
}
</script>

<%-- **** Task specific area ends here **** --%>
