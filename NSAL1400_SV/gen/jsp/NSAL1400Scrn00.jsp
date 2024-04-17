<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171204164225 --%>
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
			<input type="hidden" name="pageID" value="NSAL1400Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Named Customer Resource setup">


<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### FROM HEADER ################################################### --%>

				<%-- ###TAB - HEAD --%>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<ezf:skip>
							<tr>
								<td width="96%">
									<div>
										<li title="Mach Mnt" class="pTab_ON"><a href="#">Cust Resrc Reln</a></li>
									</div>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>
							</tr>
							</ezf:skip>
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
					<table border="0" width="99%" align="center">
						<tr>
							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="40">
									<col width="40"><!-- LOB Label -->
									<col width="90"><!-- LOB -->
									<col width="20">
									<col width="100"><!-- Customer Link -->
									<col width="250"><!-- Customer -->
									<col width="300">
									<col width="100">
									<col width="20">
									<col width="80">
									<tr height="25">
										<td>&nbsp;</td>
										<td class="stab">LOB</td>
										<td><ezf:select name="svcLineBizCd_H" ezfName="svcLineBizCd_H" ezfBlank="1" ezfCodeName="svcLineBizCd_PL" ezfDispName="lineBizTpDescTxt_PL" otherAttr=" style=\"width: 80px\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="dsAcctNm_LK" ezfName="dsAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Customer" >Customer Name(*)</ezf:anchor></td>
										<td>
											<ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" value="TEST CUSTOMER" otherAttr=" size=\"25\" maxlength=\"360\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp;</td>
										<td align="right" class="stab"><ezf:inputCheckBox name="xxChkBox_H" ezfName="xxChkBox_H" value="Y" />Active Only</td>
										<td>&nbsp;</td>
										<td>
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search\""/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

<%-- #################################################### TO HEADER ################################################### --%>
					<hr>
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<!-- Prev/Next Page-->
					<table width="99%">
						<tr align="right">
							<td>
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
										<td class="pOut">20</td>
										<td class="stab">of</td>
										<td class="pOut">200</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
									</tr>
								</table>
								</ezf:skip>
								<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
									<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
								</jsp:include>
							</td>
						</tr>
					</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- #################################################### FROM DETAIL ################################################### --%>
					<div>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<col width="20">
						<col align="left" valign="top">
						<tr>
							<td>&nbsp;</td>
							<td>
								<div id="TopTBL" style="overflow-y:none; overflow-x:none;">
									<table border="1" cellpadding="0" cellspacing="0"id="AHEAD" width="1000" style="table-layout:fixed;" >
										<col align="center" width="30"><!-- Check Box -->
										<col align="center" width="100"><!-- LOB -->
										<col align="center" width="300"><!-- Customer -->
										<col align="center" width="300"><!-- Resource -->
										<col align="center" width="50"><!-- Active -->
										<col align="center" width="110"><!-- Start -->
										<col align="center" width="110"><!-- End -->
										<tr height="30">
											<td class="pClothBs">&nbsp;</td>
											<td class="pClothBs">LOB</td>
											<td class="pClothBs">Customer</td>
											<td class="pClothBs">Resource</td>
											<td class="pClothBs">Active</td>
											<td class="pClothBs">Start</td>
											<td class="pClothBs">End</td>
										</tr>
									</table>
								</div>
								<div id="RowTBL" style="width:1017px; height:422px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
									<table border="1" cellpadding="1" cellspacing="0" id="A" width="1000"  style="table-layout:fixed;">
										<col align="center" width="30"><!-- Check Box -->
										<col align="center" width="100"><!-- LOB -->
										<col align="center" width="300"><!-- Customer -->
										<col align="center" width="300"><!-- Resource -->
										<col align="center" width="50"><!-- Active -->
										<col align="center" width="110"><!-- Start -->
										<col align="center" width="110"><!-- End -->
										<ezf:row ezfHyo="A">
											<tr id="id_row{EZF_ROW_NUMBER}" height="28">
												<td>
													<ezf:inputHidden name="ezUpTime_A" ezfName="ezUpTime_A" ezfHyo="A" ezfArrayNo="0" />
													<ezf:inputHidden name="ezUpTimeZone_A" ezfName="ezUpTimeZone_A" ezfHyo="A" ezfArrayNo="0" />
													<ezf:inputHidden name="acctContrAdminRelnPk_A" ezfName="acctContrAdminRelnPk_A" ezfHyo="A" ezfArrayNo="0" />
													<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/>
												</td>
												<td>
													<ezf:select name="svcLineBizCd_A" ezfName="svcLineBizCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcLineBizCd_PL" ezfDispName="lineBizTpDescTxt_PL" otherAttr=" style=\"width: 80px\""/>
												</td>
												<td>
													<ezf:inputText name="dsAcctNum_A" ezfName="dsAcctNum_A" value="01234567890123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"20\""/>
													<ezf:inputButton name="OpenWin_CustomerLine" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CustomerLine\""/>
													<ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" size=\"25\" maxlength=\"360\""/>
												</td>
												<td>
													<ezf:inputText name="contrAdminPsnCd_A" ezfName="contrAdminPsnCd_A" value="01234567" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\""/>
													<ezf:inputButton name="OpenWin_Resource" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Resource\""/>
													<ezf:inputText name="xxCustPsnNm_A" ezfName="xxCustPsnNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" size=\"25\" maxlength=\"450\""/>
												</td>
												<td><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A2{EZF_ROW_NUMBER}\""/></td>
												<td>
													<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');">
												</td>
												<td>
													<ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');">
												</td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
												<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
											<tr height="28">
												<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" id="xxChkBox_A1[0]"></td>
												<td>
													<select style="width: 80px" ezfhyo="A" name="svcLineBizCd_A">
														<option></option>
														<option>ESS</option>
														<option>LFS</option>
														<option>PPS</option>
													</select>
												</td>
												<td>
													<input type="text" size="8" maxlength="20" value="01234567890123456789" name="dsAcctNum_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="dsAcctNm_A" class="pPro">
												</td>
												<td>
													<input type="text" size="8" maxlength="8" value="01234567" ezfhyo="A" name="contrAdminPsnCd_A">
													<input type="button" class="pBtn0" value="..." ezfhyo="A"  name="OpenWin_CustomerLine" onclick="sendServer(this)" id="OpenWin_CustomerLine">
													<input type="text" tabindex="-1" size="25" maxlength="450" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="xxCustPsnNm_A" class="pPro">
												</td>
												<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A2" ezfname="xxChkBox_A2" value="Y"></td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effFromDt_A" class="pHsu">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="MM/DD/YYYY" name="effThruDt_A" ezfname="effThruDt_A">
													<img src="./img/calendar.gif" class="pCalendar">
												</td>
											</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<table  border="0" style="table-layout:fixed;width=96%; margin-left:20px">
						<col align="left" width="250">
						<col>
						<col align="right">
						<tr>
							<td>
								<ezf:inputButton name="Add" value="Add" htmlClass="pBtn8" />
								<ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn8" />
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
