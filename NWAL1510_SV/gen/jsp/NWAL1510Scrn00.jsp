<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20200402101225 --%>
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
			<input type="hidden" name="pageID" value="NWAL1510Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="D&I/Contact/Site Survey Screen">
			
<center>
	<table width="100%">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
<%--				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Order Entry" class="pTab_ON"><a href="#">Order Entry</a></li>
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
 --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<%@ page import="business.servlet.NWAL1510.NWAL1510BMsg" %>
				<% NWAL1510BMsg scrnMsg = (NWAL1510BMsg)databean.getEZDBMsg(); %>
					
<%-- ##### BODY(HEADER) ##### --%>
							<div class="pTab_BODY">
								<table border="0" cellspacing="0">
									<tr>
										<td valign="top">
											<fieldset>
												<table border="0" cellspacing="0">
													<col width="100">
													<col width="65">
													<col width="10">
													<col width="160">
													<col width="37">
													<tr>
														<td class="stab">Order Number</td>
														<td><ezf:inputText name="cpoOrdNum_H0" ezfName="cpoOrdNum_H0" value="10001234" otherAttr=" size=\"10\" maxlength=\"8\" tabindex=\"1\" ezftoupper=\"\""/></td>
														<td></td>
														<%if (scrnMsg.H.getValidCount() > 1) {%>
															<td class="stab"><ezf:anchor name="configTpNm_LK" ezfName="configTpNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Config" otherAttr=" id=\"configTpNm_LK\" tabindex=\"1\" ezfanchortext=\"\"">View Selected Configration</ezf:anchor></td>
														<% } else { %>
															<td class="stab">Configuration Num</td>
														<% } %>
														<td><ezf:inputText name="dsOrdPosnNum_H0" ezfName="dsOrdPosnNum_H0" value="1" otherAttr=" size=\"6\" maxlength=\"6\" tabindex=\"1\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">Category Type</td>
														<td><ezf:select name="configCatgCd_H0" ezfName="configCatgCd_H0" ezfCodeName="configCatgCd_L0" ezfDispName="configCatgDescTxt_L0" otherAttr=" tabindex=\"1\""/></td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td>
											<table border="0" cellspacing="0">
												<col width="" valign="top">
												<col width="5">
												<col width="" valign="top">
												<tr>
													<td>
														<table border="0" cellspacing="0">
															<col width="90" align="left" valign="top">
															<col width="160" align="left" valign="top">
															<tr>
																<td class="stab">Category</td>
																<td><ezf:inputText name="dsOrdCatgDescTxt_H0" ezfName="dsOrdCatgDescTxt_H0" value="LEASE, CSA" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td class="stab">Reason Code</td>
																<td><ezf:inputText name="dsOrdTpDescTxt_H0" ezfName="dsOrdTpDescTxt_H0" value="ESS STANDARD" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
															</tr>
														</table>
													</td>
													<td></td>
													<td>
														<table border="0" cellspacing="0">
															<col width="120" align="left" valign="top">
															<col width="100" align="left" valign="top">
															<tr>
																<td class="stab">Ship To Cust Acct</td>
																<td><ezf:inputText name="shipToCustAcctCd_H0" ezfName="shipToCustAcctCd_H0" value="10001234" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
															</tr>	
															<tr>
																<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
																<!-- <td class="stab">Location Code</td> -->
																<td class="stab">Ship To Code</td>
																<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
																<td><ezf:inputText name="shipToCustLocCd_H0" ezfName="shipToCustLocCd_H0" value="46270" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
															<tr>
																<td class="stab">Time Zone</td>
																<td><ezf:inputText name="tmZoneCd_H0" ezfName="tmZoneCd_H0" value="EDT" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
															</tr>
															</tr>
														</table>
													</td>
													<td>
														<table border="0" cellspacing="0">
															<col width="100" align="left" valign="top">
															<col width="150" align="left" valign="top">
															<tr>
																<td class="stab">Name</td>
																<td><ezf:inputText name="dsAcctNm_H0" ezfName="dsAcctNm_H0" value="Chicago Bulls" otherAttr=" size=\"28\" maxlength=\"30\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td class="stab">Address Line 1</td>
																<td><ezf:inputText name="firstLineAddr_H0" ezfName="firstLineAddr_H0" value="100 MAIN STREET SUITE 1" otherAttr=" size=\"28\" maxlength=\"30\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td class="stab">Address Line 2</td>
																<td><ezf:inputText name="scdLineAddr_H0" ezfName="scdLineAddr_H0" value="CHICAGO, IL, 80001" otherAttr=" size=\"28\" maxlength=\"30\" ezftoupper=\"\""/></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
<%-- ##### BODY(TAB) ##### --%>
								<div class="pTab_HEAD">
									<ul>
										<li id="Install" title="Install" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Install" otherAttr=" tabindex=\"-1\"">Delivery&Install</ezf:anchor></li>
										<li id="Survey" title="Survey" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="TAB_Survey" otherAttr=" tabindex=\"-1\"">Site Survey</ezf:anchor></li>
										<li id="Contact" title="Contact" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="TAB_Contact" otherAttr=" tabindex=\"-1\"">Contacts</ezf:anchor></li>
									</ul>
									
								</div>

<%-- ##### TAB(Install) ##### --%>
								<c:choose>
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='Install'}">
										<script type="text/javascript">
											document.getElementById( "Install" ).className="pTab_ON";
											document.getElementById( "Survey" ).className="pTab_OFF";
											document.getElementById( "Contact" ).className="pTab_OFF";
										</script>
										<div class="pTab_BODY_In">
											<table style="table-layout:fixed;" border="0" cellspacing="0">
												<col align="left" valign="top">
												<col align="left" valign="top">
												<tr>
													<td>
														<fieldset>
															<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
															<legend style="font-size:12px;">Delivery Details</legend>
															<% } else { %>
															<legend style="font-size:12px;">Pickup Details</legend>
															<% } %>
															<table style="table-layout:fixed;" cellspacing="0" height="220">
																<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_MU.getValue())) {%>
																<col width="20">
																<col width="180">
																<col width="75">
																<col width="75">
																<col width="170">
																<% } else { %>
																<col width="1">
																<col width="200">
																<col width="75">
																<col width="75">
																<col width="170">
																<% } %>
																<tr>
																	<td><ezf:inputCheckBox name="xxChkBox_D0" ezfName="xxChkBox_D0" value="Y" otherAttr=" tabindex=\"1\""/></td>
																	<td class="stab">Hours of Operation</td>
																<tr>
																	<td></td>
																	<td class="stab"> From</td>
																	<td colspan="3"><ezf:inputText name="xxSvcFromHourMnTxt_OP" ezfName="xxSvcFromHourMnTxt_OP" value="09:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/>
																	<ezf:select name="opsFromHourMn_AP" ezfName="opsFromHourMn_AP" ezfBlank="1" ezfCodeName="xxTpCd" ezfDispName="xxTpNm" otherAttr=" style=\"width: 85px\" tabindex=\"1\""/>
																	</td>
																</tr>
																<tr>
																	<td></td>
																	<td class="stab"> To</td>
																	<td colspan="3"><ezf:inputText name="xxSvcToHourMnTxt_OP" ezfName="xxSvcToHourMnTxt_OP" value="08:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/>
																	<ezf:select name="opsToHourMn_AP" ezfName="opsToHourMn_AP" ezfBlank="1" ezfCodeName="xxTpCd" ezfDispName="xxTpNm" otherAttr=" style=\"width: 85px\" tabindex=\"1\""/>
																	</td>
																</tr>
																<tr>
																	<td><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" otherAttr=" tabindex=\"1\""/></td>
																	<td class="stab">Loading Dock</td>
																	<td><ezf:inputRadio name="loadDockAvalFlg_DI" ezfName="loadDockAvalFlg_DI" value="Y" otherAttr=" tabindex=\"1\""/>YES</td>
																	<td><ezf:inputRadio name="loadDockAvalFlg_DI" ezfName="loadDockAvalFlg_DI" value="N" otherAttr=" tabindex=\"1\""/>NO</td>
																	<td></td>
																</tr>
																<tr>
																	<td><ezf:inputCheckBox name="xxChkBox_D2" ezfName="xxChkBox_D2" value="Y" otherAttr=" tabindex=\"1\""/></td>
																	<td class="stab">Stairs</td>
																	<td><ezf:inputRadio name="stairCrawReqFlg_DI" ezfName="stairCrawReqFlg_DI" value="Y" otherAttr=" tabindex=\"1\""/>YES</td>
																	<td><ezf:inputRadio name="stairCrawReqFlg_DI" ezfName="stairCrawReqFlg_DI" value="N" otherAttr=" tabindex=\"1\""/>NO</td>
																	<td></td>
																</tr>
																<tr>
																	<td></td>
																	<td class="stab">Number of Stairs</td>
																	<td colspan="3"><ezf:inputText name="stairCrawNum_DI" ezfName="stairCrawNum_DI" value="10" otherAttr=" size=\"25\" maxlength=\"4\" tabindex=\"1\" ezftoupper=\"\""/></td>
																</tr>
																<tr>
																	<td><ezf:inputCheckBox name="xxChkBox_D3" ezfName="xxChkBox_D3" value="Y" otherAttr=" tabindex=\"1\""/></td>
																	<td class="stab">Elevator</td>
																	<td><ezf:inputRadio name="elevAvalFlg_DI" ezfName="elevAvalFlg_DI" value="Y" otherAttr=" tabindex=\"1\""/>YES</td>
																	<td><ezf:inputRadio name="elevAvalFlg_DI" ezfName="elevAvalFlg_DI" value="N" otherAttr=" tabindex=\"1\""/>NO</td>
																	<td></td>
																</tr>
																<tr>
																	<td valign="TOP"><ezf:inputCheckBox name="xxChkBox_D4" ezfName="xxChkBox_D4" value="Y" otherAttr=" tabindex=\"1\""/></td>
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																	<td class="stab" valign="TOP">Delivery Instructions</td>
																	<% } else { %>
																	<td class="stab" valign="TOP">Pickup Instructions</td>
																	<% } %>
																	<td colspan="3" valign="TOP"><ezf:textArea name="delyAddlCmntTxt_DI" ezfName="delyAddlCmntTxt_DI" otherAttr=" rows=\"3\" cols=\"40\" tabindex=\"1\" ezftoupper=\"\""/></td>
																</tr>
																<!-- 2019/12/20 QC#54725 Add Start -->
																<tr>
																	<td valign="TOP"><ezf:inputCheckBox name="xxChkBox_DB" ezfName="xxChkBox_DB" value="Y" otherAttr=" tabindex=\"1\""/></td>
																	<td class="stab" valign="TOP">Scheduling Code</td>
																	<td colspan="3" valign="TOP"><ezf:select name="delySchdStsCd_DI" ezfName="delySchdStsCd_DI" ezfBlank="1" ezfCodeName="delySchdStsCd_L0" ezfDispName="delySchdStsDescTxt_L0" otherAttr=" style=\"width: 260px\" tabindex=\"1\""/></td>
																<tr>
																	<td valign="TOP"><ezf:inputCheckBox name="xxChkBox_DC" ezfName="xxChkBox_DC" value="Y" otherAttr=" tabindex=\"1\""/></td>
																	<td class="stab" valign="TOP">Scheduling Comments</td>
																	<td colspan="3" valign="TOP"><ezf:textArea name="xxAttDataCmntTxt_DI" ezfName="xxAttDataCmntTxt_DI" otherAttr=" rows=\"6\" cols=\"40\" tabindex=\"1\" ezftoupper=\"\""/></td>
																</tr>
																<!-- 2019/12/20 QC#54725 Add End -->
															</table>
														</fieldset>
													</td>
													<td>
														<fieldset>
															<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
															<legend style="font-size:12px;">Install Details</legend>
															<% } else { %>
															<legend style="font-size:12px;">De-install Details</legend>
															<% } %>
															<table style="table-layout:fixed;" cellspacing="0" height="220">
																<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_MU.getValue())) {%>
																<col width="20">
																<col width="150">
																<col width="350">
																<% } else { %>
																<col width="1">
																<col width="150">
																<col width="350">
																<% } %>
																<tr>
																	<td><ezf:inputCheckBox name="xxChkBox_D5" ezfName="xxChkBox_D5" value="Y" otherAttr=" tabindex=\"1\""/></td>
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																	<td class="stab">Install Type</td>
																	<% } else { %>
																	<td class="stab">De-install Type</td>
																	<% } %>
																	<td><ezf:select name="svcIstlRuleNum_DI" ezfName="svcIstlRuleNum_DI" ezfBlank="1" ezfCodeName="svcIstlRuleNum_L0" ezfDispName="svcIstlRuleNm_L0" otherEvent1=" onchange=\"sendServer('OnChange_InstallType')\"" otherAttr=" style=\"width: 200px\" tabindex=\"1\""/></td>
																</tr>
																<tr>
																    <!-- 2019/10/25 QC#53993 Mod Start -->
																    <!--
																	<td></td>
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																	<td class="stab">Install Division</td>
																	<% } else { %>
																	<td class="stab">De-install Division</td>
																	<% } %>
																	<td><select style="width: 200px" name="istlDivCd_DI" ezfname="istlDivCd_DI" ezflist="istlDivCd_L0,lineBizTpDescTxt_L0, 99, ,blank" tabindex="1"><option>ESS</option></select></td>
																	-->
																	<td></td>
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																	<td class="stab">To Be Installed By</td>
																	<% } else { %>
																	<td class="stab">To Be De-Installed By</td>
																	<% } %>
																	<td><ezf:select name="istlBySvcPrvdPtyCd_DI" ezfName="istlBySvcPrvdPtyCd_DI" ezfBlank="1" ezfCodeName="istlBySvcPrvdPtyCd_L0" ezfDispName="svcPrvdPtyDescTxt_L0" otherEvent1=" onchange=\"sendServer('OnChange_ToBeInstalledBy')\"" otherAttr=" style=\"width: 200px\" tabindex=\"1\""/></td>
																	<!-- 2019/10/25 QC#53993 Mod End -->
																</tr>
																<tr>
																	<td></td>
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																	<td class="stab">Time Stop</td>
																	<% } else { %>
																	<td class="stab">Time Stop</td>
																	<% } %>
																	<td><ezf:inputText name="xxSvcFromHourMnTxt_RQ" ezfName="xxSvcFromHourMnTxt_RQ" value="09:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/>
																		<ezf:select name="rqstIstlTm_AP" ezfName="rqstIstlTm_AP" ezfBlank="1" ezfCodeName="xxTpCd" ezfDispName="xxTpNm" otherAttr=" style=\"width: 85px\" tabindex=\"1\""/>
																	</td>
																</tr>
																<tr>
																	<td><ezf:inputCheckBox name="xxChkBox_D7" ezfName="xxChkBox_D7" value="Y" otherAttr=" tabindex=\"1\""/></td>
																	<td class="stab"><ezf:anchor name="techNm_LK" ezfName="techNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_TechCd" otherAttr=" id=\"techNm_LK\" tabindex=\"1\" ezfanchortext=\"\"">
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																		Install Technician
																	<% } else { %>
																		De-install Technician
																	<% } %>
																	</ezf:anchor></td>
																	<td>
																		<ezf:inputText name="istlTechCd_DI" ezfName="istlTechCd_DI" value="C11590" otherEvent1=" onblur=\"onBlurZeroEvent(this);\"" otherAttr=" size=\"8\" maxlength=\"8\" tabindex=\"1\" ezftoupper=\"\""/>
																		<ezf:inputButton name="Search_TechNm" value=">>" htmlClass="pBtn0" otherAttr=" tabindex=\"1\""/>
																		<ezf:inputText name="techNm_DI" ezfName="techNm_DI" value="BLOOM JOSH" otherAttr=" size=\"20\" ezftoupper=\"\""/>
 																	</td>
																</tr>
																<!-- 2018/01/08 S21_NA#18460(Sol#087) DEL START 
																<tr>
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																	<td class="stab">Install Date</td>
																	<% } else { %>
																	<td class="stab">De-install Date</td>
																	<% } %>
																	<td><input type="text" name="rqstIstlDt_DI" ezfname="rqstIstlDt_DI" class="" size="25" maxlength="10" value="MM/DD/YYYY" tabindex="1" ezftoupper><img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('rqstIstlDt_DI', 4);" ></td>
																</tr> 2018/01/08 S21_NA#18460(Sol#087) DEL END -->
																<tr>
																	<td valign="TOP"><ezf:inputCheckBox name="xxChkBox_D9" ezfName="xxChkBox_D9" value="Y" otherAttr=" tabindex=\"1\""/></td>
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																	<td class="stab" valign="TOP">Install Instructions</td>
																	<% } else { %>
																	<td class="stab" valign="TOP">De-install Instructions</td>
																	<% } %>
																	<td><ezf:textArea name="istlAddlCmntTxt_DI" ezfName="istlAddlCmntTxt_DI" otherAttr=" rows=\"5\" cols=\"40\" tabindex=\"1\" ezftoupper=\"\""/></td>
																</tr>
																<!-- 2019/10/25 QC#53993 Add Start -->
																<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																<tr>
																	<td><ezf:inputCheckBox name="xxChkBox_DA" ezfName="xxChkBox_DA" value="Y" otherAttr=" tabindex=\"1\""/></td>
																	<td class="stab">Service Provided By</td>
																	<td><ezf:select name="svcBySvcPrvdPtyCd_DI" ezfName="svcBySvcPrvdPtyCd_DI" ezfBlank="1" ezfCodeName="svcBySvcPrvdPtyCd_L0" ezfDispName="svcPrvdPtyDescTxt_L1" otherAttr=" style=\"width: 200px\" tabindex=\"1\""/></td>
																</tr>
																<% } %>
																<!-- 2019/10/25 QC#53993 Add End -->
															</table>
														</fieldset>
													</td>
												</tr>
											</table>
											<fieldset>
												<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
												<legend style="font-size:12px;">Install Details</legend>
												<% } else { %>
												<legend style="font-size:12px;">De-install Details</legend>
												<% } %>
												<%--------------------------------------------%>
												<%---------------- List START ----------------%>
												<%--------------------------------------------%>
												<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
													<col align="left" valign="top">
													<tr>
														<td colspan="2">
															<div id="RightTop" style="overflow-x:hidden; width:1090;"
																onScroll="synchroScrollLeft( this.id, new Array( 'Right' ));">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col align="center" width="80">
																	<col align="center" width="150">
																	<col align="center" width="100">
																	<col align="center" width="100">
																	<col align="center" width="160">
																	<col align="center" width="130">
																	<col align="center" width="200">
																	<col align="center" width="170">
																	<tr height="24">
																		<td class="pClothBs">Call Status</td>
																		<td class="pClothBs">Service Request Number</td>
																		<td class="pClothBs">Task Number</td>
																		<td class="pClothBs">Call Type</td>
																		<td class="pClothBs">Scheduled Start Date/Time</td>
																		<td class="pClothBs">End Time</td>
																		<td class="pClothBs">Assigned Tech</td>
																		<td class="pClothBs">Last Modified Date/Time</td>
																	</tr>
																</table>
															</div>
															<!-- 2019/12/19 QC#54725 Mod Start -->
															<!-- <div id="Right" style="overflow-x:hidden; width:1108; height:175px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );"> -->
															<div id="Right" style="overflow-x:hidden; width:1108; height:110px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );">
															<!-- 2019/12/19 QC#54725 Mod End -->
																<table id="A" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col width="80">
																	<col width="150">
																	<col width="100">
																	<col width="100">
																	<col width="160">
																	<col width="130">
																	<col width="200">
																	<col width="170">
																	<ezf:row ezfHyo="A">
																	<tr>
																		<td><ezf:label name="svcCallStsNm_A0" ezfName="svcCallStsNm_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="fsrNum_A0" ezfName="fsrNum_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="svcTaskNum_A0" ezfName="svcTaskNum_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="dsSvcCallTpNm_A0" ezfName="dsSvcCallTpNm_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="fsrVisitSchdDt_A0" ezfName="fsrVisitSchdDt_A0" ezfHyo="A" ezfArrayNo="0" />&nbsp;<ezf:label name="fsrVisitSchdTm_A0" ezfName="fsrVisitSchdTm_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="fsrCpltDt_A0" ezfName="fsrCpltDt_A0" ezfHyo="A" ezfArrayNo="0" />&nbsp;<ezf:label name="fsrCpltTm_A0" ezfName="fsrCpltTm_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="techCd_A0" ezfName="techCd_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="xxTsDsp19Txt_A0" ezfName="xxTsDsp19Txt_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																	</tr>
																	</ezf:row>
																</table>
															</div>
														</td>
													</tr>
												</table>
											</fieldset>
										</div>
									</c:when>
								</c:choose>
<%-- ##### TAB(Survey) ##### --%>
								<c:choose>
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='Survey'}">
										<script type="text/javascript">
											document.getElementById( "Install" ).className="pTab_OFF";
											document.getElementById( "Survey" ).className="pTab_ON";
											document.getElementById( "Contact" ).className="pTab_OFF";
										</script>
										<div class="pTab_BODY_In">
											<!--<div style="OVERFLOW-Y: scroll; HEIGHT: 480px" >-->
												<table style="table-layout:fixed;" cellspacing="0">
													<col align="left" valign="top">
													<col align="left" valign="top">
													<tr>
														<td>
														<table>
															<tr>
															<td>
															<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_MU.getValue())) {%>
															<table style="table-layout:fixed;" cellspacing="0">
																<col width="20">
																<col width="230">
																<col width="20">
																<col width="270">
																<tr>
																	<td><ezf:inputRadio name="xxEdtModeFlg_SS" ezfName="xxEdtModeFlg_SS" value="Y" otherAttr=" tabindex=\"1\""/></td>
																	<td style="font-size:10px;">Enable entire Site Survey for Update</td>
																	<td><ezf:inputRadio name="xxEdtModeFlg_SS" ezfName="xxEdtModeFlg_SS" value="N" otherAttr=" tabindex=\"1\""/></td>
																	<td style="font-size:10px;">Enable field by field Site Survey for Update</td>
																</tr>
															</table>
															<% } %>
															<fieldset>
																<legend style="font-size:11px;">Company Information</legend>
																<table style="table-layout:fixed;" cellspacing="0">
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_MU.getValue())) {%>
																	<col width="20">
																	<col width="95">
																	<col width="150">
																	<col width="20">
																	<col width="135">
																	<col width="130">
																	<% } else {%>
																	<col width="1">
																	<col width="95">
																	<col width="150">
																	<col width="1">
																	<col width="153">
																	<col width="150">
																	<% } %>
																	<tr>
																		<td></td>
																		<td class="stab">Company Name</td>
																		<td><ezf:inputText name="dsAcctNm_SS" ezfName="dsAcctNm_SS" value="CHICAGO BULLS" otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																		<td></td>
																		<td class="stab">Customer Num</td>
																		<td><ezf:inputText name="shipToCustLocCd_SS" ezfName="shipToCustLocCd_SS" value="5005123" otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																	</tr>
																		<td></td>
																		<td class="stab">Street</td>
																		<td><ezf:inputText name="shipToFirstLineAddr_SS" ezfName="shipToFirstLineAddr_SS" value="3900 COVER ST." otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																		<td></td>
																		<td class="stab">City</td>
																		<td><ezf:inputText name="shipToCtyAddr_SS" ezfName="shipToCtyAddr_SS" value="LONG BEACH" otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																	</tr>
																	<tr>
																		<td><ezf:inputCheckBox name="xxChkBox_S0" ezfName="xxChkBox_S0" value="Y" otherAttr=" tabindex=\"1\""/></td>
																		<td class="stab">Apt. or Building</td>
																		<td><ezf:inputText name="cmpyInfoAptBldgNm_SS" ezfName="cmpyInfoAptBldgNm_SS" otherAttr=" size=\"20\" maxlength=\"60\" tabindex=\"1\" ezftoupper=\"\""/></td>
																		<td></td>
																		<td class="stab">State</td>
																		<td><ezf:inputText name="shipToStCd_SS" ezfName="shipToStCd_SS" value=" " otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																	</tr>
																	<tr>
																		<td><ezf:inputCheckBox name="xxChkBox_S1" ezfName="xxChkBox_S1" value="Y" otherAttr=" tabindex=\"1\""/></td>
																		<td class="stab">Floor</td>
																		<td><ezf:inputText name="cmpyInfoFlNm_SS" ezfName="cmpyInfoFlNm_SS" otherAttr=" size=\"20\" maxlength=\"10\" tabindex=\"1\" ezftoupper=\"\""/></td>
																		<td></td>
																		<td class="stab">Postal</td>
																		<td><ezf:inputText name="shipToPostCd_SS" ezfName="shipToPostCd_SS" value="92714" otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																	</tr>
																	<tr>
																		<td><ezf:inputCheckBox name="xxChkBox_S2" ezfName="xxChkBox_S2" value="Y" otherAttr=" tabindex=\"1\""/></td>
																		<td class="stab">Department</td>
																		<td><ezf:inputText name="cmpyInfoDeptNm_SS" ezfName="cmpyInfoDeptNm_SS" value="Engineering Dept." otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" ezftoupper=\"\""/></td>
																		<td><ezf:inputCheckBox name="xxChkBox_S3" ezfName="xxChkBox_S3" value="Y" otherAttr=" tabindex=\"1\""/></td>
																		<td class="stab">Floor Protection Req?</td>
																		<td><ezf:inputRadio name="elevProtReqFlg_SS" ezfName="elevProtReqFlg_SS" value="Y" otherAttr=" tabindex=\"1\""/>Yes&nbsp;<ezf:inputRadio name="elevProtReqFlg_SS" ezfName="elevProtReqFlg_SS" value="N" otherAttr=" tabindex=\"1\""/>No</td>
																	</tr>
																</table>
															</fieldset>
															<fieldset>
																<legend style="font-size:11px;">Site Information</legend>
																<table cellspacing="0">
																	<tr>
																		<td>
																			<table style="table-layout:fixed;" cellspacing="0">
																				<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_MU.getValue())) {%>
																				<col width="20">
																				<col width="150">
																				<col width="95">
																				<col width="20">
																				<col width="130">
																				<col width="125">
																				<% } else {%>
																				<col width="1">
																				<col width="149">
																				<col width="120">
																				<col width="1">
																				<col width="130">
																				<col width="139">
																				<% } %>
																				<tr>
																					<td><ezf:inputCheckBox name="xxChkBox_S4" ezfName="xxChkBox_S4" value="Y" otherAttr=" tabindex=\"1\""/></td>
																					<td class="stab">No of Steps Outside</td>
																					<td><ezf:inputText name="otsdStepNum_SS" ezfName="otsdStepNum_SS" value="1" otherAttr=" size=\"10\" maxlength=\"4\" tabindex=\"1\" ezftoupper=\"\""/></td>
																					<td><ezf:inputCheckBox name="xxChkBox_S5" ezfName="xxChkBox_S5" value="Y" otherAttr=" tabindex=\"1\""/></td>
																					<td class="stab">Loading Dock Available</td>
																					<td>
																						<ezf:inputRadio name="loadDockAvalFlg_SS" ezfName="loadDockAvalFlg_SS" value="Y" otherAttr=" onclick=\"sendServer( 'OnChange_LoadDockAvalFlg' );\" tabindex=\"1\" "/>Yes&nbsp;
																						<ezf:inputRadio name="loadDockAvalFlg_SS" ezfName="loadDockAvalFlg_SS" value="N" otherAttr=" onclick=\"sendServer( 'OnChange_LoadDockAvalFlg' );\" tabindex=\"1\" "/>No
																					</td>
																				</tr>
																				<tr>
																					<td></td>
																					<td class="stab">No of Steps Inside</td>
																					<td><ezf:inputText name="insdStepNum_SS" ezfName="insdStepNum_SS" value="3" otherAttr=" size=\"10\" maxlength=\"4\" tabindex=\"1\" ezftoupper=\"\""/></td>
																					<td></td>
																					<td class="stab">Dock Height(in.)</td>
																					<td><ezf:inputText name="loadDockHgt_SS" ezfName="loadDockHgt_SS" value="100" otherAttr=" size=\"10\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/></td>
																				</tr>
																				<tr>
																					<td></td>
																					<td class="stab">Step Crawler required?</td>
																					<td>
																					<ezf:inputRadio name="stairCrawReqFlg_SS" ezfName="stairCrawReqFlg_SS" value="Y" otherAttr=" tabindex=\"1\""/>Yes&nbsp;<ezf:inputRadio name="stairCrawReqFlg_SS" ezfName="stairCrawReqFlg_SS" value="N" otherAttr=" tabindex=\"1\""/>No</td>
																					<td></td>
																					<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																					<td class="stab">Delivery Hours</td>
																					<% } else { %>
																					<td class="stab">Pickup Hours</td>
																					<% } %>
																					<td></td>
																				</tr>
																				<tr>
																					<td></td>
																					<td class="stab">No of flights</td>
																					<td><ezf:inputText name="flgtStairNum_SS" ezfName="flgtStairNum_SS" value="3" otherAttr=" size=\"10\" maxlength=\"3\" tabindex=\"1\" ezftoupper=\"\""/></td>
																					<td></td>
																					<td class="stab">From</td>
																					<td>
																						<ezf:inputText name="xxSvcFromHourMnTxt_LD" ezfName="xxSvcFromHourMnTxt_LD" value="09:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"6\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/>
																						<ezf:select name="loadDockAvalFromHourMn_AP" ezfName="loadDockAvalFromHourMn_AP" ezfBlank="1" ezfCodeName="xxTpCd" ezfDispName="xxTpNm" otherAttr=" style=\"width: 65px\" tabindex=\"1\""/>
																					</td>
																				</tr>
																				<tr>
																					<td></td>
																					<td class="stab">Width of stairs and landings(in.)</td>
																					<td><ezf:inputText name="stairAndLdgWdt_SS" ezfName="stairAndLdgWdt_SS" value="35" otherAttr=" size=\"10\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/></td>
																					<td></td>
																					<td class="stab">To</td>
																					<td>
																						<ezf:inputText name="xxSvcToHourMnTxt_LD" ezfName="xxSvcToHourMnTxt_LD" value="08:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"6\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/>
																						<ezf:select name="loadDockAvalToHourMn_AP" ezfName="loadDockAvalToHourMn_AP" ezfBlank="1" ezfCodeName="xxTpCd" ezfDispName="xxTpNm" otherAttr=" style=\"width: 65px\" tabindex=\"1\""/>
																					</td>
																				</tr>
																				<tr>
																					<td></td>
																					<td></td>
																					<td></td>
																					<td><ezf:inputCheckBox name="xxChkBox_SC" ezfName="xxChkBox_SC" value="Y" otherAttr=" tabindex=\"1\""/></td>
																					<td class="stab">Tractor Trailer Accessible</td>
																					<td><ezf:inputRadio name="trctrAndTrailAccsFlg_SS" ezfName="trctrAndTrailAccsFlg_SS" value="Y" otherAttr=" tabindex=\"1\""/>Yes&nbsp;<ezf:inputRadio name="trctrAndTrailAccsFlg_SS" ezfName="trctrAndTrailAccsFlg_SS" value="N" otherAttr=" tabindex=\"1\""/>No</td>
																				</tr>

																				<tr>
																					<td></td>
																					<td></td>
																					<td></td>
																					<td><ezf:inputCheckBox name="xxChkBox_SD" ezfName="xxChkBox_SD" value="Y" otherAttr=" tabindex=\"1\""/></td>
																					<td class="stab">Transport Option</td>
																					<td><ezf:select name="delyTrnspOptCd_SS" ezfName="delyTrnspOptCd_SS" ezfBlank="1" ezfCodeName="delyTrnspOptCd_L0" ezfDispName="delyTrnspOptNm_L0" otherAttr=" style=\"width: 125px\" tabindex=\"1\""/></td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>
															</fieldset>
															</td>
															</tr>
														</table>
														</td>
														
														<td>
														<table>
															<tr>
															<td>
															<fieldset>
																<legend style="font-size:11px;">
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_MU.getValue())) {%>
																	<ezf:inputCheckBox name="xxChkBox_SE" ezfName="xxChkBox_SE" value="Y" otherAttr=" tabindex=\"1\""/>
																	<% } %>
																	Additional Comments</legend>
																<ezf:textArea name="xxFldValTxt_SS" ezfName="xxFldValTxt_SS" otherAttr=" rows=\"8\" cols=\"75\" tabindex=\"1\" ezftoupper=\"\""/>
															</fieldset>
														
															<fieldset>
																<legend style="font-size:11px;">Elevator Information & Dimensions</legend>
																<table cellspacing="0" cellpadding="0">
																	<tr>
																		<td>
																			<table style="table-layout:fixed;" cellspacing="0">
																				<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_MU.getValue())) {%>
																				<col width="20">
																				<col width="180">
																				<col width="150">
																				<col width="30">
																				<col width="150">
																				<% } else {%>
																				<col width="1">
																				<col width="200">
																				<col width="150">
																				<col width="30">
																				<col width="150">
																				<% } %>
																				<tr>
																					<td><ezf:inputCheckBox name="xxChkBox_SF" ezfName="xxChkBox_SF" value="Y" otherAttr=" tabindex=\"1\""/></td>
																					<td class="stab">Elevator Available</td>
																					<td>
																						<ezf:inputRadio name="elevAvalFlg_SS" ezfName="elevAvalFlg_SS" value="Y" otherAttr=" onclick=\"sendServer( 'OnChange_ElevAvalFlg' );\" tabindex=\"1\" "/>Yes&nbsp;
																						<ezf:inputRadio name="elevAvalFlg_SS" ezfName="elevAvalFlg_SS" value="N" otherAttr=" onclick=\"sendServer( 'OnChange_ElevAvalFlg' );\" tabindex=\"1\" "/>No
																					</td>
																					<td></td>
																					<td></td>
																				</tr>
																				<tr>
																					<td></td>
																					<td class="stab">Elevator hours From</td>
																					<td>
																						<ezf:inputText name="xxSvcFromHourMnTxt_EA" ezfName="xxSvcFromHourMnTxt_EA" value="9:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"6\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/>
																						<ezf:select name="elevAvalFromHourMn_AP" ezfName="elevAvalFromHourMn_AP" ezfBlank="1" ezfCodeName="xxTpCd" ezfDispName="xxTpNm" otherAttr=" style=\"width: 65px\" tabindex=\"1\""/>
																					</td>
																					<td>To</td>
																					<td><ezf:inputText name="xxSvcToHourMnTxt_EA" ezfName="xxSvcToHourMnTxt_EA" value="7:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"6\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/>
																						<ezf:select name="elevAvalToHourMn_AP" ezfName="elevAvalToHourMn_AP" ezfBlank="1" ezfCodeName="xxTpCd" ezfDispName="xxTpNm" otherAttr=" style=\"width: 65px\" tabindex=\"1\""/>
																					</td>
																				</tr>
																				<tr>
																					<td><ezf:inputCheckBox name="xxChkBox_SH" ezfName="xxChkBox_SH" value="Y" otherAttr=" tabindex=\"1\""/></td>
																					<td class="stab">Elevator Appointment Needed?</td>
																					<td>
																						<ezf:inputRadio name="elevApptReqFlg_SS" ezfName="elevApptReqFlg_SS" value="Y" otherAttr=" onclick=\"sendServer( 'OnChange_ElevApptReqFlg' );\" tabindex=\"1\" "/>Yes&nbsp;
																						<ezf:inputRadio name="elevApptReqFlg_SS" ezfName="elevApptReqFlg_SS" value="N" otherAttr=" onclick=\"sendServer( 'OnChange_ElevApptReqFlg' );\" tabindex=\"1\" "/>No
																					</td>
																					<td></td>
																					<td></td>
																				</tr>
																				<tr>
																					<td></td>
																					<td class="stab">Elevator Contact</td>
																					<td><ezf:inputText name="elevCtacPsnNm_SS" ezfName="elevCtacPsnNm_SS" value="Mr.President" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" ezftoupper=\"\""/></td>
																					<td class="stab">Phone</td>
																					<td><ezf:inputText name="elevCtacTelNum_SS" ezfName="elevCtacTelNum_SS" value="111-222-3333" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" ezftoupper=\"\""/></td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<table cellspacing="0">
																				<col align="left" valign="top">
																				<col align="left" valign="top">
																				<col align="left" valign="top">
																				<tr>
																					<td>
																						<fieldset>
																							<legend style="font-size:11px;">
																							<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_MU.getValue())) {%>
																							<ezf:inputCheckBox name="xxChkBox_SJ" ezfName="xxChkBox_SJ" value="Y" otherAttr=" tabindex=\"1\""/>
																							<% } %>
																							Building Entrance</legend>
																							<table cellspacing="0">
																								<tr>
																									<td class="stab">Height(in.)</td>
																									<td><ezf:inputText name="bldgEntDoorHgt_SS" ezfName="bldgEntDoorHgt_SS" value="10" otherAttr=" size=\"7\" maxlength=\"6\" tabindex=\"1\" ezftoupper=\"\""/></td>
																								</tr>
																								<tr>
																									<td class="stab">Width(in.)</td>
																									<td><ezf:inputText name="bldgEntDoorWdt_SS" ezfName="bldgEntDoorWdt_SS" value="10" otherAttr=" size=\"7\" maxlength=\"6\" tabindex=\"1\" ezftoupper=\"\""/></td>
																								</tr>
																								<tr>
																									<td class="stab">Corridor Width(in.)</td>
																									<td><ezf:inputText name="crdrWdt_SS" ezfName="crdrWdt_SS" value="10" otherAttr=" size=\"7\" maxlength=\"6\" tabindex=\"1\" ezftoupper=\"\""/></td>
																								</tr>
																								<tr>
																									<td class="stab">Door Width(in.)</td>
																									<td><ezf:inputText name="doorWdt_SS" ezfName="doorWdt_SS" value="10" otherAttr=" size=\"7\" maxlength=\"6\" tabindex=\"1\" ezftoupper=\"\""/></td>
																								</tr>
																							</table>
																						</fieldset>
																					</td>
																					<td>
																						<fieldset>
																							<legend style="font-size:11px;">
																							<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_MU.getValue())) {%>
																							<ezf:inputCheckBox name="xxChkBox_SK" ezfName="xxChkBox_SK" value="Y" otherAttr=" tabindex=\"1\""/>
																							<% } %>
																							Elevator</legend>
																							<table cellspacing="0">
																								<tr>
																									<td>
																										<table cellspacing="0">
																											<tr>
																												<td class="stab">Width(in.)</td>
																												<td><ezf:inputText name="elevWdt_SS" ezfName="elevWdt_SS" value="100" otherAttr=" size=\"7\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/></td>
																											</tr>
																											<tr>
																												<td class="stab">Depth(in.)</td>
																												<td><ezf:inputText name="elevDepthNum_SS" ezfName="elevDepthNum_SS" value="100" otherAttr=" size=\"7\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/></td>
																											</tr>
																											<tr>
																												<td class="stab">Capacity(Lbs.)</td>
																												<td><ezf:inputText name="elevCapWt_SS" ezfName="elevCapWt_SS" value="100" otherAttr=" size=\"7\" maxlength=\"7\" tabindex=\"1\" ezftoupper=\"\""/></td>
																											</tr>
																										</table>
																									</td>
																									<td>
																										<fieldset>
																											<legend style="font-size:11px;">Door Opening</legend>
																											<table cellspacing="0">
																												<tr>
																													<td class="stab">Height(in.)</td>
																													<td><ezf:inputText name="elevDoorHgt_SS" ezfName="elevDoorHgt_SS" value="100" otherAttr=" size=\"7\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/></td>
																												</tr>
																												<tr>
																													<td class="stab">Width(in.)</td>
																													<td><ezf:inputText name="elevDoorWdt_SS" ezfName="elevDoorWdt_SS" value="100" otherAttr=" size=\"7\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/></td>
																												</tr>
																											</table>
																										</fieldset>
																									</td>
																								</tr>
																							</table>
																						</fieldset>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>
															</fieldset>
															</td>
															</tr>
														</table>
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<fieldset>
																<legend style="font-size:11px;">Material Weights and Dimensions Information</legend>
																<%--------------------------------------------%>
																<%---------------- List START ----------------%>
																<%--------------------------------------------%>
																<table border="0" cellpadding="01" cellspacing="0" width="1086" align="center"  rules="none"  style="margin-bottom: 0px;">
																	<tr align="right">
																		<col width="110"  align="left">
																		<col width="160"  align="center">
																		<col width="550"  align="right">
																		<td>&nbsp;</td>
																		<td class="stab">
																			<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																				<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
																				<jsp:param name="TableNm"          value="B" />
																				<jsp:param name="ShowingFrom"      value="xxPageShowFromNum" />
																				<jsp:param name="ShowingTo"        value="xxPageShowToNum" />
																				<jsp:param name="ShowingOf"        value="xxPageShowOfNum" />
																				<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum" />
																				<jsp:param name="ShowingTotal"     value="xxPageShowTotNum" />
																				<jsp:param name="ViewMode"         value="FULL" />
																			</jsp:include>
																			<ezf:skip>
																				<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
																					<col>
																					<col width="54"  align="center">
																					<col width="40"  align="center">
																					<col width="16"  align="center">
																					<col width="40"  align="center">
																					<col width="26"  align="center">
																					<col width="10">
																					<col>
																					<col width="20">
																					<col>	
																					<col width="1">
																					<col>
																					<tr>
																						<td>Results 990 - 1000 of 1000</td>
																						<td class="stab">Showing</td>
																						<td><input type="text" value="1" size="4" maxlength="4" style="text-align:right" class="pPro" readOnly name="xxPageShowFromNum" ezfname="xxPageShowFromNum"></td>
																						<td class="stab">/</td>
																						<td><input type="text" size="4" maxlength="4" value="1" class="pPro" style="text-align:right" id="txtShowingTot" readOnly name="xxPageShowToNum" ezfname="xxPageShowToNum"></td>
																						<td class="stab">page</td>
																						<td>&nbsp;</td>
																						<td><input type="button" class="pBtn3" value="Jump" name="PageJump" disabled></td>
																						<td></td>
																						<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" disabled></td>
																						<td></td>
																						<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" disabled></td>
																					</tr>
																				</table>
																			</ezf:skip>
																		</td>
																	</tr>
																</table>
																<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
																	<col align="left" valign="top">
																	<tr>
																		<td>
																			<div id="RightTop" style="overflow-x:hidden; width:1070;"
																				onScroll="synchroScrollLeft( this.id, new Array( 'Right' ));">
																				<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																					<col align="center" width="120">
																					<col align="center" width="350">
																					<col align="center" width="120">
																					<col align="center" width="120">
																					<col align="center" width="120">
																					<col align="center" width="120">
																					<col align="center" width="120">
																					<tr>
																						<td class="pClothBs">Item Code</td>
																						<td class="pClothBs">Item Description</td>
																						<td class="pClothBs">Cr Wt</td>
																						<td class="pClothBs">Cr Len</td>
																						<td class="pClothBs">Cr Wdt</td>
																						<td class="pClothBs">Cr Ht</td>
																						<td class="pClothBs">Cr Dg</td>
																					</tr>
																				</table>
																			</div>
																			<div id="Right" style="overflow-x:hidden; width:1086; height:50px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );">
																				<table id="B" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																					<col width="120">
																					<col width="350">
																					<col align="right" width="120">
																					<col align="right" width="120">
																					<col align="right" width="120">
																					<col align="right" width="120">
																					<col align="right" width="120">
																					<ezf:row ezfHyo="B">
																					<tr>
																						<td><ezf:label name="mdseCd_B0" ezfName="mdseCd_B0" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="mdseDescShortTxt_B0" ezfName="mdseDescShortTxt_B0" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="inPoundWt_B0" ezfName="inPoundWt_B0" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="inInchLg_B0" ezfName="inInchLg_B0" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="inInchWdt_B0" ezfName="inInchWdt_B0" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="inInchHgt_B0" ezfName="inInchHgt_B0" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="xxInInchDgnlNum_B0" ezfName="xxInInchDgnlNum_B0" ezfHyo="B" ezfArrayNo="0" /></td>
																					</tr>
																					</ezf:row>
																				</table>
																			</div>
																		</td>
																	</tr>
																</table>
															</fieldset>
														</td>
													</tr>
												</table>
											<!--</div>-->
										</div>
									</c:when>
								</c:choose>
<%-- ##### TAB(Contact) ##### --%>
								<c:choose>
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='Contact'}">
										<script type="text/javascript">
											document.getElementById( "Install" ).className="pTab_OFF";
											document.getElementById( "Survey" ).className="pTab_OFF";
											document.getElementById( "Contact" ).className="pTab_ON";
										</script>
										<div class="pTab_BODY_In">

											<fieldset>
												<legend style="font-size:12px;">Contact Details</legend>
												<table>
												<col align="center" width="55">
												<col align="center" width="55">
												<col align="left" width="20">
												<col align="left" width="100">
												<col align="left" width="20">
												<col align="left" width="100">
												<col align="right" width="710">
												<td><ezf:inputButton name="Add_Row" value="Add" htmlClass="pBtn4" otherAttr=" tabindex=\"1\""/></td>
												<td><ezf:inputButton name="Delete_Row" value="Delete" htmlClass="pBtn4" otherAttr=" tabindex=\"1\""/></td>
												<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_MU.getValue())) {%>
													<td><ezf:inputRadio name="xxEdtModeFlg_CT" ezfName="xxEdtModeFlg_CT" value="Y" otherAttr=" tabindex=\"1\""/></td>
													<td class="stab">Replaces Contacts</td>
													<td><ezf:inputRadio name="xxEdtModeFlg_CT" ezfName="xxEdtModeFlg_CT" value="N" otherAttr=" tabindex=\"1\""/></td>
													<td class="stab">Add Contacts</td>
												<% } else { %>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												<% } %>
												<td class="stab" align="right"><label>Telephone # Format : "999-999-9999" or "9999999999"</label></td>
												</table>
												<%--------------------------------------------%>
												<%---------------- List START ----------------%>
												<%--------------------------------------------%>
												<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
													<col align="left" valign="top">
													<tr>
														<td>
															<div id="RightTop" style="overflow-x:hidden; width:1084;"
																onScroll="synchroScrollLeft( this.id, new Array( 'Right' ));">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col align="center" width="24">
																	<col align="center" width="180">
																	<col align="center" width="165">
																	<col align="center" width="130">
																	<col align="center" width="120">
																	<col align="center" width="50">
																	<col align="center" width="210">
																	<col align="center" width="110">
																	<col align="center" width="90">
																	<tr height="24">
																		<td class="pClothBs"></td>
																		<td class="pClothBs">Role</td>
																		<td class="pClothBs">First Name</td>
																		<td class="pClothBs">Last Name</td>
																		<td class="pClothBs">Phone</td>
																		<td class="pClothBs">EXT</td>
																		<td class="pClothBs">Email</td>
																		<td class="pClothBs">Fax</td>
																		<td class="pClothBs">Customer Ref</td>
																	</tr>
																</table>
															</div>
															<div id="Right" style="overflow-x:hidden; width:1100; height:391px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );">
																<table id="C" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col width="24">
																	<col width="180">
																	<col width="165">
																	<col width="130">
																	<col width="120">
																	<col width="50">
																	<col width="210">
																	<col width="110">
																	<col width="90">
																	<ezf:row ezfHyo="C">
																	<tr>
																		<td><ezf:inputCheckBox name="delFlg_C0" ezfName="delFlg_C0" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"delFlg_C0#{EZF_ROW_NUMBER}\" tabindex=\"1\""/></td>
																		<td><ezf:select name="ctacPsnTpCd_C0" ezfName="ctacPsnTpCd_C0" ezfHyo="C" ezfArrayNo="0" ezfCodeName="ctacPsnTpCd_L0" ezfDispName="ctacTpNm_L0" otherEvent1=" onchange=\"sendServer('OnChange_ContactRole', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width: 176px\" tabindex=\"1\""/></td>
																		<!--td><input type="text" size="18" maxlength="30" value="JOSHUA" name="ctacPsnFirstNm_C0" ezfname="ctacPsnFirstNm_C0" tabindex="1" ezfhyo="C"><input type="button" class="pBtn0" name="OpenWin_CtacPsn" ezfname="ctacPsnFirstNm_LK" value="..." onClick="sendServer(this)" ezfhyo="C"></td-->
																		<td>
																			<table border="0" cellpadding="1" cellspacing="0">
																				<tr style="padding:0;">
																					<td><ezf:inputText name="ctacPsnFirstNm_C0" ezfName="ctacPsnFirstNm_C0" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"1\""/></td>
																					<td><ezf:anchor name="ctacPsnFirstNm_LK" ezfName="ctacPsnFirstNm_LK" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_CtacPsn" otherAttr=" tabindex=\"1\" ezfanchortext=\"\"">F</ezf:anchor></td>
																				</tr>
																			</table>
																		</td>
																		<td><ezf:inputText name="ctacPsnLastNm_C0" ezfName="ctacPsnLastNm_C0" value="BLOOM" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"30\" tabindex=\"1\""/></td>
																		<td><ezf:inputText name="ctacPsnTelNum_C0" ezfName="ctacPsnTelNum_C0" value="732-521-7659" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" tabindex=\"1\" ezftoupper=\"\""/></td>
																		<td><ezf:inputText name="ctacPsnExtnNum_C0" ezfName="ctacPsnExtnNum_C0" value="1234" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"10\" tabindex=\"1\" ezftoupper=\"\""/></td>
																		<td><ezf:inputText name="ctacPsnEmlAddr_C0" ezfName="ctacPsnEmlAddr_C0" value="JBLOOM@CSA.CANON.COM" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"320\" tabindex=\"1\""/></td>
																		<td><ezf:inputText name="ctacPsnFaxNum_C0" ezfName="ctacPsnFaxNum_C0" value="732-521-7659" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"20\" tabindex=\"1\" ezftoupper=\"\""/></td>
																		<td>
																			<ezf:select name="ctacCustRefTpCd_C0" ezfName="ctacCustRefTpCd_C0" ezfHyo="C" ezfArrayNo="0" ezfCodeName="ctacCustRefTpCd_L0" ezfDispName="ctacCustRefTpDescTxt_L0" otherAttr=" tabindex=\"1\" style=\"width:80px;\""/>
																		</td>
																	</tr>
																	</ezf:row>
																</table>
															</div>
														</td>
													</tr>
												</table>
											</fieldset>
										</div>
									</c:when>
								</c:choose>
							</div>
						</td>
					</tr>
				</table>
			</center>

<%-- ###SCRIPT --%>
<script type="text/javascript">
	function synchroScroll_fromRightTblAction() {
		var rightTopTBL = this.document.getElementById( 'rightTopTBL' );
		var rightTBL    = this.document.getElementById( 'rightTBL'     );
		var leftTBL     = this.document.getElementById( 'leftTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
		// synchronize scroll - X
		rightTopTBL.scrollLeft = rightTBL.scrollLeft;
	}
	
	function synchroScroll_fromLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftTBL'  );
		var rightTBL = this.document.getElementById( 'rightTBL' );
		
		// synchronize scroll - Y
		rightTBL.scrollTop = leftTBL.scrollTop;
	}

	function onFocusEvent(item){
		if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
		}
		item.select();
	}

	function onBlurEvent(item){
		// 2017/07/24 S21_NA#19980 Mod Start
//		if(item.value.length === 1){
//			item.value = '0' + item.value.charAt(0) + ':00';
//		}else if(item.value.length === 2){
//			item.value = item.value.charAt(0) + item.value.charAt(1) + ':00';
//		}else if(item.value.length === 3){
//			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + '0';
//		}else if(item.value.length === 4){
//			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
//		}else if(item.value.length === 5){
//			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
//		}
		var items = item.value.split(':', 2);
		if (items.length == 1) {
			if (item.value.length == 1) {
				item.value = '0' + item.value.charAt(0) + ':00';
			} else if (item.value.length == 2) {
				item.value = item.value.charAt(0) + item.value.charAt(1) + ':00';
			} else if (item.value.length == 3) {
				item.value = '0' + item.value.charAt(0) + ':' + item.value.charAt(1) + item.value.charAt(2);
			} else if (item.value.length == 4) {
				item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
			}
		} else if (items.length == 2) {
			var hh = '00';
			if (items[0].length == 1) {
				hh = '0' + items[0];
			} else if (items[0].length > 1) {
				hh = items[0].substring(0, 2);
			}
			var mm = '00';
			if (items[1].length == 1) {
				mm = '0' + items[1];
			} else if (items[1].length > 1) {
				mm = items[1].substring(0, 2);
			}
			item.value = hh + ':' + mm;
		}
		// 2017/07/24 S21_NA#19980 Mod End
	}

	function onBlurZeroEvent(item){
		if(item.value.length >= 1 && item.value.length < 6){
			item.value = ("00000"+item.value).slice(-6);
		}
	}
</script>

<%-- **** Task specific area ends here **** --%>
