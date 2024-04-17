<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230904151431 --%>
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
			<input type="hidden" name="pageID" value="NSAL0310Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Add Machines">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<div class="pTab_BODY">
					<table style="table-layout:fixed; margin-left:4;" border="0" cellpadding="1" cellspacing="0">
						<tr height="5">
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
									<col width="80" align="right"><!-- Customer # Label -->
									<col width="56"><!-- Customer # -->
									<col width="300"><!-- Customer Name -->
									<tr height="20">
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Customer" >Customer #</ezf:anchor></td>
										<td><ezf:inputText name="dsAcctNum_H" ezfName="dsAcctNum_H" value="0000043" otherAttr=" size=\"7\" tabindex=\"1\" ezftoupper=\"\""/></td>
										<td><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"40\" tabindex=\"1\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
									<col width="80" align="right">
									<col width="160">
									<col width="10">
									<col width="110" align="right">
									<col width="160">
									<col width="20">
									<col width="120" align="right">
									<col width="200">
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Serial" >Serial#(*)</ezf:anchor></td>
										<td><ezf:inputText name="serNum" ezfName="serNum" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" tabindex=\"1\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Model" >Model(*)</ezf:anchor></td>
										<td><ezf:inputText name="mdlNm" ezfName="mdlNm" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" tabindex=\"2\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Installed At Location(*)</td>
										<td><ezf:inputText name="xxComnScrColValTxt_H" ezfName="xxComnScrColValTxt_H" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"25\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
									<col width="80" align="right">
									<col width="160">
									<col width="10">
									<col width="110" align="right">
									<col width="160">
									<col width="25">
									<col width="20">
									<col width="105">
									<col width="10">
									<col width="20">
									<col width="148">
									<col width="10">
									<col width="90">
									<tr>
										<td class="stab">IB ID(*)</td>
										<td><ezf:inputText name="xxScrItem29Txt" ezfName="xxScrItem29Txt" value="01234567890123456789" otherAttr=" size=\"20\" tabindex=\"5\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Item Name(*)</td>
										<td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" tabindex=\"6\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td><ezf:inputCheckBox name="xxConfigFlg" ezfName="xxConfigFlg" value="Y" otherAttr=" tabindex=\"7\""/></td>
										<td class="stab">Include Configuration</td>
										<td>&nbsp;</td>
										<td><ezf:inputCheckBox name="contrInacFlg" ezfName="contrInacFlg" value="Y" otherAttr=" tabindex=\"8\""/></td>
										<td class="stab">Exclude Contracted Machines</td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" tabindex=\"9\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr height="10">
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td width="963" align="right">
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
						<tr>
							<td>
								<div id="HeaderTBL"
									style="overflow-y:none; overflow-x:hidden; width:963;">
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width="25" align="center"><!-- Select -->
										<col width="25" align="center"><!-- Config -->
										<col width="72" align="center"><!-- Machine Master -->
										<col width="79" align="center"><!-- Serial# -->
										<col width="65" align="center"><!-- Model -->
										<col width="178" align="center"><!-- Merchandise Name -->
										<col width="62" align="center"><!-- Ship to -->
										<col width="82" align="center"><!-- Ship to Name -->
										<col width="152" align="center"><!-- Address -->
										<col width="97" align="center"><!-- From -->
										<col width="97" align="center"><!-- Thru -->
										<col width="30" align="center"><!-- Bill Usg -->
										<col width="82" align="center"><!-- Service Program -->
										<tr>
											<td class="pClothBs" rowspan="2" colspan="2">Select<br/>/ Config</td>
											<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachMstrPk_A')">IB ID</a><img id="sortIMG.svcMachMstrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')">Serial#</a><img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdlNm_A')">Model</a><img id="sortIMG.mdlNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseDescShortTxt_A')">Item Name</a><img id="sortIMG.mdseDescShortTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs" rowspan="1" colspan="3">Installed at Customer</td>
											<td class="pClothBs" rowspan="1" colspan="2">Effective Date</td>
											<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxChkBox_AB')">Bill Usg</a><img id="sortIMG.xxChkBox_AB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs" rowspan="2" colspan="1">Service Program</td>
										</tr>
										<tr>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'indCurLocNum_A')">Ship to</a><img id="sortIMG.indCurLocNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'shipToLocNm_A')">Ship to Name</a><img id="sortIMG.shipToLocNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxGenlFldAreaTxt_A')">Address</a><img id="sortIMG.xxGenlFldAreaTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrEffFromDt_A')">From</a><img id="sortIMG.contrEffFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrEffThruDt_A')">Thru</a><img id="sortIMG.contrEffThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										</tr>
									</table>
								</div>
<%@ page import="business.servlet.NSAL0310.constant.NSAL0310Constant" %>
								<div id="ResultsTBL"
								    style="overflow-x:scroll; width:983; overflow-y:scroll; height:366;"
								    onScroll = "synchroScrollLeft (this.id, new Array ('HeaderTBL'));">
									<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width="25" align="center"><!-- Select -->
										<col width="25" align="center"><!-- Config -->
										<col width="72"><!-- Machine Master -->
										<col width="79"><!-- Serial# -->
										<col width="65"><!-- Model -->
										<col width="178"><!-- Merchandise Name -->
										<col width="62"><!-- Ship to -->
										<col width="82"><!-- Ship to Name -->
										<col width="152"><!-- Address -->
										<col width="97"><!-- From -->
										<col width="97"><!-- Thru -->
										<col width="30" align="center"><!-- Bill Usg -->
										<col width="82" align="center"><!-- Service Program -->
										<ezf:row ezfHyo="A">
										<tr height="28">
											<td style="border-right:0;"><ezf:inputCheckBox name="xxChkBox_AM" ezfName="xxChkBox_AM" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('Check_MainMachine', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"xxChkBox_AM#{EZF_ROW_NUMBER}\""/></td>
											<td style="border-left:0;"><ezf:inputCheckBox name="xxChkBox_AA" ezfName="xxChkBox_AA" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"margin-top:2; margin-bottom:2;\" id=\"xxChkBox_AA#{EZF_ROW_NUMBER}\""/><ezf:anchor name="xxLinkProt_AE" ezfName="xxLinkProt_AE" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Expand_Machines" otherAttr=" id=\"xxLinkProt_AE#{EZF_ROW_NUMBER}\""><img id="<%= NSAL0310Constant.IMG_NM_XPND %>#{EZF_ROW_NUMBER}" src="<%= NSAL0310Constant.IMG_BTN_URL_XPND %>" style="height:16px; width:16px; vertical-align:middle; border:0;"></ezf:anchor><ezf:anchor name="xxLinkProt_AC" ezfName="xxLinkProt_AC" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Collapse_Machines" otherAttr=" id=\"xxLinkProt_AC#{EZF_ROW_NUMBER}\""><img id="<%= NSAL0310Constant.IMG_NM_CLPS %>#{EZF_ROW_NUMBER}" src="<%= NSAL0310Constant.IMG_BTN_URL_CLPS %>" style="height:16px; width:16px; vertical-align:middle; border:0;"></ezf:anchor></td>
											<td><ezf:inputText name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" value="012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\""/></td>
											<td><ezf:inputText name="serNum_A" ezfName="serNum_A" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
											<td><ezf:inputText name="mdlNm_A" ezfName="mdlNm_A" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
											<td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\""/></td>
											<td><ezf:inputText name="indCurLocNum_A" ezfName="indCurLocNum_A" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\""/></td>
											<td><ezf:inputText name="shipToLocNm_A" ezfName="shipToLocNm_A" value="WWWWWWWWW1WWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
											<td><ezf:inputText name="xxGenlFldAreaTxt_A" ezfName="xxGenlFldAreaTxt_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
											<td><ezf:inputText name="contrEffFromDt_A" ezfName="contrEffFromDt_A" value="99/99/9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrEffFromDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
											<td><ezf:inputText name="contrEffThruDt_A" ezfName="contrEffThruDt_A" value="99/99/9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrEffThruDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
											<td><ezf:inputCheckBox name="xxChkBox_AB" ezfName="xxChkBox_AB" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="mdseNm_A" ezfName="mdseNm_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
										</tr>
										</ezf:row>
										<ezf:skip>
										<tr>
											<td><input type="checkbox"></td>
											<td><a href="#"><img src="./img/downarrow.png" style="height:16px; width:16px; vertical-align:middle; border:0;"></a></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"></td>
											<td><input type="text" size="9" class="pPro" value="012345"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="8" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="30" class="pPro" value="WWWWWWWWW1WW"></td>
											<td><input type="text" size="7" class="pPro" value="WWWWWWWWW1"></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWW"></td>
											<td><input type="text" size="20" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="text" size="10" value="99/99/9999"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('', 4);"></td>
											<td><input type="checkbox"class="pPro" ></td>
											<td><input type="text" size="10" class="pPro" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
										</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td align="left">
										<ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn6" />
										<ezf:inputButton name="Unselect_All" value="Unselect All" htmlClass="pBtn6" />
										<ezf:inputButton name="Add_Machines" value="Add Machines" htmlClass="pBtn7" />
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
<style type="text/css">
configarrow {

}
</style>

<%-- **** Task specific area ends here **** --%>
