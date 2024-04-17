<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170817170406 --%>
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
			<input type="hidden" name="pageID" value="NLBL3150Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Allocation Rule Setup by Item and WH">
			
<%-- #################################################### FROM HEADER ################################################### --%>
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
			<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%"><li title = "Supersession" class="pTab_ON" ><a href="#">Allc Rule Set</a></li></td>
								<td valign="bottom" align="center"><a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a></td>
								<td valign="bottom" align="center"><a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a></td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
			
			<div class="pTab_BODY">
			<table style="table-layout:fixed;">
				<col width="50">
				<col width="40">
				<col width="150">
				<col width="35">
				<col width="220">
				<col width="40">
				<col width="220">
				<col width="80">
						<tr>
							<td>&nbsp;</td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SearchItemNum_Link" >Item#(*)</ezf:anchor></td>
							<td><ezf:inputText name="mdseCd" ezfName="mdseCd" value="WWWWWWWWW1WWWWWW" otherAttr=" size=\"18\" maxlength=\"16\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SearchWh_Link" >WH(*)</ezf:anchor></td>
							<td><ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="WWWWWW" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/>
								<ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="WWWWWWWWW1WWWWWWWW" otherAttr=" size=\"18\" maxlength=\"16\" ezftoupper=\"\" tabindex=\"-1\""/></td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SearchWh_Link" >SWH(*)</ezf:anchor></td>
							<td><ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="WWWWWWWW" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/>
								<ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" value="WWWWWWWWW1WWWWWWWW" otherAttr=" size=\"18\" maxlength=\"16\" ezftoupper=\"\" tabindex=\"-1\""/></td>
							<td valign="top" align="right" width="300" >
								<table border="0" width="100%" height="100%">
									<tr align="right" valign="bottom">
										<td colspan="2" valign="bottom" align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
			</table>
			
<%-- #################################################### TO HEADER ################################################### --%>
			<hr>
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>

			<table cellpadding="0" cellspacing="0">
				<col width="350">
				<col width="750">
				<tr>
					<td align="left" style="padding-left:3px;">
						<ezf:inputButton name="AddNewRow" value="Add New Row" htmlClass="pBtn8" />
					</td>
					<td align="right">
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td align="right">
									<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"       value="FULL" />
									</jsp:include>
								<ezf:skip>
									<table border="0" cellpadding="0" cellspacing="0">
										<col width="44"  align="center">
										<col width="20"  align="center">
										<col width="20"  align="center">
										<col width="20"  align="center">
										<col width="20"  align="center">
										<col width="20"  align="center">
										<col width="10"  align="center">
										<col width="46"  align="center">
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="30"  align="center">
										<col width="10">
										<col width="50">
										<col width="20">
										<col width="50">
										<col width="50">
										<tr>
											<td class="stab">Results</td>
											<td class="stab">1</td>
											<td class="stab">-</td>
											<td class="stab">40</td>
											<td class="stab">of</td>
											<td class="stab">41</td>
											<td>&nbsp;</td>
											<td class="stab">Showing</td>
											<td class="pOut">1</td>
											<td class="stab">/</td>
											<td class="pOut">2</td>
											<td class="stab">page</td>
											<td>&nbsp;</td>
											<td><input type="button" class="pBtn3" value="Jump" onclick="sendServer(this)" name="PageJump"></td>
											<td>&nbsp;</td>
											<td><input type="button" class="pBtn3" value="Prev" onclick="sendServer(this)" name="PagePrev"></td>
											<td><input type="button" class="pBtn3" value="Next" onclick="sendServer(this)" name="PageNext"></td>
										</tr>
									</table>
								</ezf:skip>	
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

<%-- ######################################## TO (COMMON)PAGENATION #################################### --%>

<%-- #################################################### FROM DETAIL ################################################### --%>
					<div>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<col align="left" valign="top" width="1127">
						<tr>
							<!-- @@@@@ Left -->
							<td style="padding-left:3px;">
								<div>
									<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:hidden; float:fixed;">
										<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="AHEAD">
											<col align="center" width="30">	
											<col align="center" width="230">
											<col align="center" width="330">
											<col align="center" width="290">
											<col align="center" width="230">
											<tr height="24">
												<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs">Item</td>
												<td class="pClothBs">Item Description</td>
												<td class="pClothBs">WH</td>
												<td class="pClothBs">SWH</td>
											</tr>
										</table>
									</div>
									<div id="LeftTable_A" style="overflow-y:scroll; overflow-x:none; height:422; width:1127;">
										<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;">
											<col align="center" width="30">	
											<col align="center" width="230">
											<col align="center" width="330">
											<col align="center" width="290">
											<col align="center" width="230">
											<ezf:row ezfHyo="A">
												<tr id="id_row{EZF_ROW_NUMBER}" height="35">
													<ezf:inputHidden name="mdseWhCondPk_A" ezfName="mdseWhCondPk_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mdseWhCondPk_A\""/>
													<ezf:inputHidden name="ezUpTime_A" ezfName="ezUpTime_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"ezUpTime_A\""/>
													<ezf:inputHidden name="ezUpTimeZone_A" ezfName="ezUpTimeZone_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"ezUpTimeZone_A\""/>
													<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td>
														<ezf:inputButton name="OpenWin_SearchItemNum" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
														<ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"16\" style=\"padding:0px;\" ezftoupper=\"\""/>
														<ezf:inputButton name="SetItemDesc" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
													</td>
													<td>
														<ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"45\" maxlength=\"42\" style=\"padding:0px;\" tabindex=\"-1\""/>
													</td>
													<td>
														<ezf:inputButton name="OpenWin_SearchSwh" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
														<ezf:inputText name="rtlWhCd_A" ezfName="rtlWhCd_A" value="WWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" style=\"padding:0px;\" ezftoupper=\"\""/>
														<ezf:inputButton name="SetWh" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
														<ezf:inputText name="rtlWhNm_A" ezfName="rtlWhNm_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"18\" style=\"padding:0px;\" tabindex=\"-1\""/>
													</td>
													<td>
														<ezf:inputButton name="OpenWin_SearchWh" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
														<ezf:inputText name="rtlSwhCd_A" ezfName="rtlSwhCd_A" value="WWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" style=\"padding:0px;\" ezftoupper=\"\""/>
														<ezf:inputButton name="SetSwh" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
														<ezf:inputText name="rtlSwhNm_A" ezfName="rtlSwhNm_A" value="WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\" tabindex=\"-1\""/>
													</td>
												</tr>
											</ezf:row>
											<ezf:skip>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
												<tr height="35">
													<input type="hidden" id="mdseWhCondPk_A" name="mdseWhCondPk_A" ezfname="mdseWhCondPk_A" ezfhyo="A">
													<input type="hidden" id="ezUpTime_A" name="ezUpTime_A" ezfname="ezUpTime_A" ezfhyo="A">
													<input type="hidden" id="ezUpTimeZone_A" name="ezUpTimeZone_A" ezfname="ezUpTimeZone_A" ezfhyo="A">
													<td><input type="checkbox" value="Y"  name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchItemNum" ezfhyo="A">
														<input type="text" name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWW"  size="18" maxlength="16" style="padding:0px;" ezftoupper ezfhyo="A" >
														<input onclick="sendServer(this)" type="button" value=">>" class="pBtn0" name="SetItemDesc" ezfhyo="A">
													</td>
													<td>
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW" size="45" maxlength="42" style="padding:0px;" name="mdseDescShortTxt_A" ezfname="mdseDescShortTxt_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value="..." name="OpenWin_SearchSwh" ezfhyo="A">
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlWhCd_A" ezfname="rtlWhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetWh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WWWWWWWWW2" size="20" maxlength="18" style="padding:0px;" name="rtlWhNm_A" ezfname="rtlWhNm_A" ezfhyo="A" tabIndex="-1">
													</td>
													<td>
														<input type="button" class="pBtn0" value="..." onclick="sendServer(this)" name="OpenWin_SearchWh" ezfhyo="A" >
														<input type="text" value="WWWWWW" size="6" maxlength="6" style="padding:0px;" name="rtlSwhCd_A" ezfname="rtlSwhCd_A" ezfhyo="A" ezftoupper>
														<input onclick="sendServer(this)" type="button" class="pBtn0" value=">>" name="SetSwh" ezfhyo="A">
														<input type="text" value="WWWWWWWWW1WW"  size="12" maxlength="10"  name="rtlSwhNm_A" ezfname="rtlSwhNm_A" ezftoupper ezfhyo="A" tabIndex="-1">
													</td>
												</tr>
											</ezf:skip>
										</table>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<table border="0">
								<col align="left" width="10">
								<tr>
									<td>&nbsp;</td>
									<td>
										<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn7" />
										<ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="pBtn7" />
										<ezf:inputButton name="LineCancel" value="Line Cancel" htmlClass="pBtn7" />
									</td>
								</tr>
							</table>
						</tr>
					</table>
					</div>
				</div>
<%-- #################################################### DETAIL ################################################### --%>
			</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
