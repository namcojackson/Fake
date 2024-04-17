<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160706175128 --%>
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
			<input type="hidden" name="pageID" value="NMAL7410Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="CSA - CSMP Price List Mapping Maintenance">
			

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### FROM HEADER ################################################### --%>

				<%-- ###TAB - HEAD --%>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<!-- include S21BusinessProcessTAB --> 
										<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
									</div>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				<div class="pTab_BODY_In">
					<table border="0" cellspacing="0" cellpadding="0" style="text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="40">
						<col width="110">
						<col width="160">
						<col width="110">
						<col width="160">
						<col width="90">
						<col width="120">
						<col width="10">
						<col width="130">
						<col width="170">
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td class="stab">CSMP Price List(*)</td>
							<td><ezf:inputText name="crListTxt" ezfName="crListTxt" value="12345678" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="prcCatg_LK" ezfName="_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PrcCatg" otherAttr=" id=\"prcCatg_LK\" ezfanchortext=\"\"">CSA Price List(*)</ezf:anchor></td>
							<td><ezf:inputText name="prcCatgNm" ezfName="prcCatgNm" value="12345678" otherAttr=" size=\"15\" maxlength=\"75\" ezftoupper=\"\""/></td>
							<td class="stab">Effective From</td>
							<td><ezf:inputText name="effFromDt_FR" ezfName="effFromDt_FR" value="01/04/2016" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_FR', 4);"></td>
							<td>-</td>
							<td><ezf:inputText name="effFromDt_TO" ezfName="effFromDt_TO" value="01/04/2016" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_TO', 4);"></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td class="stab">CSMP Generation#(*)</td>
							<td><ezf:inputText name="crListGnrnNum" ezfName="crListGnrnNum" value="12345" otherAttr=" size=\"15\" maxlength=\"5\" ezftoupper=\"\""/></td>
							<td colspan="2">&nbsp;</td>
							<td class="stab">Effective Thru</td>
							<td><ezf:inputText name="effThruDt_FR" ezfName="effThruDt_FR" value="01/04/2016" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_FR', 4);"></td>
							<td>-</td>
							<td><ezf:inputText name="effThruDt_TO" ezfName="effThruDt_TO" value="01/04/2016" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_TO', 4);"></td>
							<td align="right">
								<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
							</td>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>

<%-- #################################################### TO HEADER ################################################### --%>
					<hr>
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<!-- Prev/Next Page-->
					<table cellpadding="0" cellspacing="0">
						<col width="150">
						<col width="940">
						<tr>
							<td align="left">
								<ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" />
								<ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn6" />
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
													<td><input type="button" class="pBtn3" value="Jump" name="PageJump" ezfname="PageJump" onclick="sendServer(this)"></td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" ezfname="PagePrev" onclick="sendServer(this)"></td>
													<td><input type="button" class="pBtn3" value="Next" name="PageNext" ezfname="PageNext" onclick="sendServer(this)"></td>
												</tr>
											</table>
										</ezf:skip>	
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- #################################################### FROM DETAIL ################################################### --%>
					<div>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<col align="left" valign="top" width="1107">
						<tr>
							<!-- @@@@@ Left -->
							<td style="padding-left:3px;">
								<div>
									<div id="TopTBL" style="overflow-y:none; overflow-x:none;">
										<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1090px" style="margin-right:20px" >
											<col align="center" width="040">	<!-- Check Box -->
											<col align="center" width="200">	<!-- CSMP Price List -->
											<col align="center" width="130">	<!-- CSMP Generation# -->
											<col align="center" width="190">	<!-- CSA Price List -->
											<col align="center" width="270">	<!-- Description -->
											<col align="center" width="130">	<!-- Effective From -->
											<col align="center" width="130">	<!-- Effective Thru -->
											<tr height="30">
												<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs">CSMP Price List</td>
												<td class="pClothBs">CSMP Generation#</td>
												<td class="pClothBs">CSA Price List</td>
												<td class="pClothBs">Description</td>
												<td class="pClothBs">Effective From</td>
												<td class="pClothBs">Effective Thru</td>
											</tr>
										</table>
									</div>
									
									<div id="RowTBL" style="width:1107px; height:440px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
										<table border="1" cellpadding="1" cellspacing="0" id="A" width="1090" style="table-layout:fixed;">
											<col align="center" width="040">	<!-- Check Box -->
											<col align="left" width="200">		<!-- CSMP Price List -->
											<col align="left" width="130">		<!-- CSMP Generation# -->
											<col align="left" width="190">		<!-- CSA Price List -->
											<col align="left" width="270">		<!-- Description -->
											<col align="left" width="130">		<!-- Effective From -->
											<col align="left" width="130">		<!-- Effective Thru -->
											<ezf:row ezfHyo="A">
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputText name="crListTxt_A" ezfName="crListTxt_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" style=\"padding:0px;\" ezftoupper=\"\""/></td>
													<td><ezf:inputText name="crListGnrnNum_A" ezfName="crListGnrnNum_A" value="12345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"padding:0px;\" ezftoupper=\"\""/></td>
													<td>
														<ezf:inputText name="csaPrcCatgCd_A" ezfName="csaPrcCatgCd_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" style=\"padding:0px;\""/>
														<ezf:inputButton name="OpenWin_ListPrcCatg" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn3" />
													</td>
													<td><ezf:inputText name="prcCatgNm_A" ezfName="prcCatgNm_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													<td>
														<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="56/78/1234" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"effFromDt_A{EZF_ROW_NUMBER}\" size=\"12\" maxlength=\"10\" style=\"padding:0px;\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A{EZF_ROW_NUMBER}', 4);">
													</td>
													<td>
														<ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="56/78/1234" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"effThruDt_A{EZF_ROW_NUMBER}\" size=\"12\" maxlength=\"10\" style=\"padding:0px;\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A{EZF_ROW_NUMBER}', 4);">
													</td>
												</tr>
											</ezf:row>
												
										</table>
									</div>
								</div>
							</td>
						</tr>
					</table>
					</div>
				</div>
<%-- #################################################### DETAIL ################################################### --%>
			</td>
		</tr>
	</table>
</center>




			

<%-- **** Task specific area ends here **** --%>
