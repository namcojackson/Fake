<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20191109165844 --%>
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
			<input type="hidden" name="pageID" value="NWAL2240Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Interface D&I/Contact/Site Survey Screen">
			
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
				<%@ page import="business.servlet.NWAL2240.NWAL2240BMsg" %>
				<% NWAL2240BMsg scrnMsg = (NWAL2240BMsg)databean.getEZDBMsg(); %>
					
<%-- ##### BODY(HEADER) ##### --%>
							<div class="pTab_BODY">
								<table border="0" cellspacing="0">
									<tr>
										<td valign="top">
											<fieldset>
												<table border="0" cellspacing="0">
													<col width="120">
													<col width="87">
													<col width="10">
													<col width="130">
													<col width="87">
													<tr>
														<td>Order Ref Num</td>
														<td><ezf:inputText name="ordSrcRefNum_H0" ezfName="ordSrcRefNum_H0" value="10001234" otherAttr=" size=\"20\" maxlength=\"8\" tabindex=\"1\" ezftoupper=\"\""/></td>
														<td></td>
														<td>Configuration Num</td>
														<td><ezf:inputText name="dsOrdPosnNum_H0" ezfName="dsOrdPosnNum_H0" value="1" otherAttr=" size=\"20\" maxlength=\"6\" tabindex=\"1\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td>Category Type</td>
														<td><ezf:select name="configCatgCd_H0" ezfName="configCatgCd_H0" ezfCodeName="configCatgCd_L0" ezfDispName="configCatgDescTxt_L0" otherAttr=" tabindex=\"1\""/></td>
														<td colspan="5" align="right">
															<ezf:inputButton name="Search_Order" value="Search" htmlClass="pBtn4" otherAttr=" tabindex=\"1\""/>
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td>
											<table border="0" cellspacing="0">
												<col width="" valign="top">
												<!-- Mod Start 2017/09/29 H.Sugawara QC#19922 -->
												<!-- <col width="5"> -->
												<col width="2">
												<!-- Mod End   2017/09/29 H.Sugawara QC#19922 -->
												<col width="" valign="top">
												<tr>
													<td>
														<table border="0" cellspacing="0">
															<col width="90" align="left" valign="top">
															<col width="160" align="left" valign="top">
															<tr>
																<td>Category</td>
																<td><ezf:inputText name="dsOrdCatgDescTxt_H0" ezfName="dsOrdCatgDescTxt_H0" value="LEASE, CSA" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td>Reason Code</td>
																<td><ezf:inputText name="dsOrdTpDescTxt_H0" ezfName="dsOrdTpDescTxt_H0" value="ESS STANDARD" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td>Time Zone</td>
																<td><ezf:inputText name="tmZoneCd_H0" ezfName="tmZoneCd_H0" value="EST" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
															</tr>
														</table>
													</td>
													<td></td>
													<td>
														<table border="0" cellspacing="0">
															<!-- Mod Start 2017/09/29 H.Sugawara QC#19922 -->
															<!-- <col width="140" align="left" valign="top"> -->
															<col width="143" align="left" valign="top">
															<!-- Mod End   2017/09/29 H.Sugawara QC#19922 -->
															<col width="195" align="left" valign="top">
															<tr>
																<td>Ship To Cust Acct</td>
																<td><ezf:inputText name="shipToCustAcctCd_H0" ezfName="shipToCustAcctCd_H0" value="10001234" otherAttr=" size=\"28\" maxlength=\"30\" ezftoupper=\"\""/></td>
															</tr>	
															<tr>
																<!-- Mod Start 2017/09/29 H.Sugawara QC#19922 -->
																<!-- <td>Location Code</td> -->
																<td>Ship To Code</td>
																<!-- Mod End   2017/09/29 H.Sugawara QC#19922 -->
																<td><ezf:inputText name="shipToCustLocCd_H0" ezfName="shipToCustLocCd_H0" value="46270" otherAttr=" size=\"28\" maxlength=\"30\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td>Name</td>
																<td><ezf:inputText name="shipToCustAcctNm_H0" ezfName="shipToCustAcctNm_H0" value="Chicago Bulls" otherAttr=" size=\"28\" maxlength=\"30\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td>Address Line 1</td>
																<td><ezf:inputText name="firstLineAddr_H0" ezfName="firstLineAddr_H0" value="100 MAIN STREET SUITE 1" otherAttr=" size=\"28\" maxlength=\"30\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td>Address Line 2</td>
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
																<col width="200">
																<col width="75">
																<col width="75">
																<col width="150">
																<tr>
																	<td>Hours of Operation</td>
																<tr>
																	<td> From</td>
																	<td colspan="3"><ezf:inputText name="xxSvcFromHourMnTxt_OP" ezfName="xxSvcFromHourMnTxt_OP" value="09:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/>
																	<ezf:select name="opsFromHourMn_AP" ezfName="opsFromHourMn_AP" ezfBlank="1" ezfCodeName="xxTpCd" ezfDispName="xxTpNm" otherAttr=" style=\"width: 85px\" tabindex=\"1\""/>
																	</td>
																</tr>
																<tr>
																	<td> To</td>
																	<td colspan="3"><ezf:inputText name="xxSvcToHourMnTxt_OP" ezfName="xxSvcToHourMnTxt_OP" value="08:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/>
																	<ezf:select name="opsToHourMn_AP" ezfName="opsToHourMn_AP" ezfBlank="1" ezfCodeName="xxTpCd" ezfDispName="xxTpNm" otherAttr=" style=\"width: 85px\" tabindex=\"1\""/>
																	</td>
																</tr>
																<tr>
																	<td>Loading Dock</td>
																	<td><ezf:inputRadio name="loadDockAvalFlg_DI" ezfName="loadDockAvalFlg_DI" value="Y" otherAttr=" tabindex=\"1\""/>YES</td>
																	<td><ezf:inputRadio name="loadDockAvalFlg_DI" ezfName="loadDockAvalFlg_DI" value="N" otherAttr=" tabindex=\"1\""/>NO</td>
																	<td></td>
																</tr>
																<tr>
																	<td>Stairs</td>
																	<td><ezf:inputRadio name="stairCrawReqFlg_DI" ezfName="stairCrawReqFlg_DI" value="Y" otherAttr=" tabindex=\"1\""/>YES</td>
																	<td><ezf:inputRadio name="stairCrawReqFlg_DI" ezfName="stairCrawReqFlg_DI" value="N" otherAttr=" tabindex=\"1\""/>NO</td>
																	<td></td>
																</tr>
																<tr>
																	<td>Number of Stairs</td>
																	<td colspan="3"><ezf:inputText name="stairCrawNum_DI" ezfName="stairCrawNum_DI" value="10" otherAttr=" size=\"25\" maxlength=\"4\" tabindex=\"1\" ezftoupper=\"\""/></td>
																</tr>
																<tr>
																	<td>Elevator</td>
																	<td><ezf:inputRadio name="elevAvalFlg_DI" ezfName="elevAvalFlg_DI" value="Y" otherAttr=" tabindex=\"1\""/>YES</td>
																	<td><ezf:inputRadio name="elevAvalFlg_DI" ezfName="elevAvalFlg_DI" value="N" otherAttr=" tabindex=\"1\""/>NO</td>
																	<td></td>
																</tr>
																<tr>
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																	<td valign="TOP">Delivery Instructions</td>
																	<% } else { %>
																	<td valign="TOP">Pickup Instructions</td>
																	<% } %>
																	<td colspan="3" valign="TOP"><ezf:textArea name="delyAddlCmntTxt_DI" ezfName="delyAddlCmntTxt_DI" otherAttr=" rows=\"3\" cols=\"40\" tabindex=\"1\" ezftoupper=\"\""/></td>
																</tr>
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
																<col width="150">
																<col width="300">
																<tr>
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																	<td>Install Type</td>
																	<% } else { %>
																	<td>De-install Type</td>
																	<% } %>
																	<td><ezf:select name="svcIstlRuleNum_DI" ezfName="svcIstlRuleNum_DI" ezfBlank="1" ezfCodeName="svcIstlRuleNum_L0" ezfDispName="svcIstlRuleNm_L0" otherAttr=" style=\"width: 200px\" tabindex=\"1\""/></td>
																</tr>
																<tr>
																    <!-- 2019/11/09 QC#53993 Mod Start -->
																    <!--
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																	<td>Install Division</td>
																	<% } else { %>
																	<td>De-install Division</td>
																	<% } %>
																	<td><select style="width: 200px" name="istlDivCd_DI" ezfname="istlDivCd_DI" ezflist="istlDivCd_L0,lineBizTpDescTxt_L0, 99, ,blank" tabindex="1"><option>ESS</option></select></td>
																     -->
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																	<td>To Be Installed By</td>
																	<% } else { %>
																	<td>To Be De-Installed By</td>
																	<% } %>
																	<td><ezf:select name="istlBySvcPrvdPtyCd_DI" ezfName="istlBySvcPrvdPtyCd_DI" ezfBlank="1" ezfCodeName="istlBySvcPrvdPtyCd_L0" ezfDispName="svcPrvdPtyDescTxt_L0" otherAttr=" style=\"width: 200px\" tabindex=\"1\""/></td>
																	<!-- 2019/11/09 QC#53993 Mod End -->
																</tr>
																<tr>
																	<td><ezf:anchor name="techNm_LK" ezfName="techNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_TechCd" otherAttr=" id=\"techNm_LK\" tabindex=\"1\" ezfanchortext=\"\"">
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																		Install Technician
																	<% } else { %>
																		De-install Technician
																	<% } %>
																	</ezf:anchor></td>
																	<td>
																		<ezf:inputText name="istlTechCd_DI" ezfName="istlTechCd_DI" value="C11590" otherAttr=" size=\"8\" maxlength=\"8\" tabindex=\"1\" ezftoupper=\"\""/>
																		<ezf:inputButton name="Search_TechNm" value=">>" htmlClass="pBtn0" otherAttr=" tabindex=\"1\""/>
																		<ezf:inputText name="techNm_DI" ezfName="techNm_DI" value="BLOOM JOSH" otherAttr=" size=\"20\" ezftoupper=\"\""/>
 																	</td>
																</tr>
																<tr>
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																	<td>Install Time</td>
																	<% } else { %>
																	<td>De-install Time</td>
																	<% } %>
																	<td><ezf:inputText name="xxSvcFromHourMnTxt_RQ" ezfName="xxSvcFromHourMnTxt_RQ" value="09:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/>
																		<ezf:select name="rqstIstlTm_AP" ezfName="rqstIstlTm_AP" ezfBlank="1" ezfCodeName="xxTpCd" ezfDispName="xxTpNm" otherAttr=" style=\"width: 85px\" tabindex=\"1\""/>
																	</td>
																</tr>
																<tr>
																	<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																	<td valign="TOP">Install Instructions</td>
																	<% } else { %>
																	<td valign="TOP">De-install Instructions</td>
																	<% } %>
																	<td><ezf:textArea name="istlAddlCmntTxt_DI" ezfName="istlAddlCmntTxt_DI" otherAttr=" rows=\"5\" cols=\"40\" tabindex=\"1\" ezftoupper=\"\""/></td>
																</tr>
																<!-- 2019/10/25 QC#53993 Add Start -->
																<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																<tr>
																	<td>Service Provided By</td>
																	<td><ezf:select name="svcBySvcPrvdPtyCd_DI" ezfName="svcBySvcPrvdPtyCd_DI" ezfBlank="1" ezfCodeName="svcBySvcPrvdPtyCd_L0" ezfDispName="svcPrvdPtyDescTxt_L1" otherAttr=" style=\"width: 200px\" tabindex=\"1\""/></td>
																</tr>
																<% } %>
																<!-- 2019/10/25 QC#53993 Add End -->
															</table>
														</fieldset>
													</td>
												</tr>
											</table>
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
															<fieldset>
																<legend style="font-size:11px;">Company Information</legend>
																<table style="table-layout:fixed;" cellspacing="0">
																	<col width="95">
																	<col width="150">
																	<col width="155">
																	<col width="150">
																	<tr>
																		<td>Company Name</td>
																		<td><ezf:inputText name="shipToLocNm_SS" ezfName="shipToLocNm_SS" value="CHICAGO BULLS" otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																		<td>Customer Num</td>
																		<td><ezf:inputText name="shipToCustLocCd_SS" ezfName="shipToCustLocCd_SS" value="5005123" otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																	</tr>
																	<tr>
																		<td>Street</td>
																		<td><ezf:inputText name="shipToFirstLineAddr_SS" ezfName="shipToFirstLineAddr_SS" value="3900 COVER ST." otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																		<td>City</td>
																		<td><ezf:inputText name="shipToCtyAddr_SS" ezfName="shipToCtyAddr_SS" value="LONG BEACH" otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																	</tr>
																	<tr>
																		<td>Apt. or Building</td>
																		<td><ezf:inputText name="cmpyInfoAptBldgNm_SS" ezfName="cmpyInfoAptBldgNm_SS" otherAttr=" size=\"20\" maxlength=\"60\" tabindex=\"1\" ezftoupper=\"\""/></td>
																		<td>State</td>
																		<td><ezf:inputText name="shipToStCd_SS" ezfName="shipToStCd_SS" value=" " otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																	</tr>
																	<tr>
																		<td>Floor</td>
																		<td><ezf:inputText name="cmpyInfoFlNm_SS" ezfName="cmpyInfoFlNm_SS" otherAttr=" size=\"20\" maxlength=\"10\" tabindex=\"1\" ezftoupper=\"\""/></td>
																		<td>Postal</td>
																		<td><ezf:inputText name="shipToPostCd_SS" ezfName="shipToPostCd_SS" value="92714" otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																	</tr>
																	<tr>
																		<td>Department</td>
																		<td><ezf:inputText name="cmpyInfoDeptNm_SS" ezfName="cmpyInfoDeptNm_SS" value="Engineering Dept." otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" ezftoupper=\"\""/></td>
																		<td>Floor Protection Req?</td>
																		<td><ezf:inputRadio name="elevProtReqFlg_SS" ezfName="elevProtReqFlg_SS" value="Y" otherAttr=" tabindex=\"1\""/>Yes&nbsp;<ezf:inputRadio name="elevProtReqFlg_SS" ezfName="elevProtReqFlg_SS" value="N" otherAttr=" tabindex=\"1\""/>No</td>
																	</tr>
																</table>
															</fieldset>
														</td>
														<td>
															<fieldset>
																<legend style="font-size:11px;">Additional Comments</legend>
																<ezf:textArea name="siteSrvyAddlCmntTxt_SS" ezfName="siteSrvyAddlCmntTxt_SS" otherAttr=" rows=\"8\" cols=\"75\" tabindex=\"1\" ezftoupper=\"\""/>
															</fieldset>
														</td>
													</tr>
													<tr>
														<td>
															<fieldset>
																<legend style="font-size:11px;">Site Information</legend>
																<table cellspacing="0">
																	<tr>
																		<td>
																			<table style="table-layout:fixed;" cellspacing="0">
																				<col width="150">
																				<col width="130">
																				<col width="130">
																				<col width="130">
																				<tr>
																					<td>No of Steps Outside</td>
																					<td><ezf:inputText name="otsdStepNum_SS" ezfName="otsdStepNum_SS" value="1" otherAttr=" size=\"10\" maxlength=\"4\" tabindex=\"1\" ezftoupper=\"\""/></td>
																					<td>Loading Dock Available</td>
																					<td>
																						<ezf:inputRadio name="loadDockAvalFlg_SS" ezfName="loadDockAvalFlg_SS" value="Y" otherAttr=" onclick=\"sendServer( 'OnChange_LoadDockAvalFlg' );\" tabindex=\"1\" "/>Yes&nbsp;
																						<ezf:inputRadio name="loadDockAvalFlg_SS" ezfName="loadDockAvalFlg_SS" value="N" otherAttr=" onclick=\"sendServer( 'OnChange_LoadDockAvalFlg' );\" tabindex=\"1\" "/>No
																					</td>
																				</tr>
																				<tr>
																					<td>No of Steps Inside</td>
																					<td><ezf:inputText name="insdStepNum_SS" ezfName="insdStepNum_SS" value="3" otherAttr=" size=\"10\" maxlength=\"4\" tabindex=\"1\" ezftoupper=\"\""/></td>
																					<td>Dock Height(in.)</td>
																					<td><ezf:inputText name="loadDockHgt_SS" ezfName="loadDockHgt_SS" value="100" otherAttr=" size=\"10\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/></td>
																				</tr>
																				<tr>
																					<td>Step Crawler required?</td>
																					<td>
																					<ezf:inputRadio name="stairCrawReqFlg_SS" ezfName="stairCrawReqFlg_SS" value="Y" otherAttr=" tabindex=\"1\""/>Yes&nbsp;<ezf:inputRadio name="stairCrawReqFlg_SS" ezfName="stairCrawReqFlg_SS" value="N" otherAttr=" tabindex=\"1\""/>No</td>
																					
																					<%if ("Y".equals(scrnMsg.xxDplyCtrlFlg_DI.getValue())) {%>
																					<td>Delivery Hours</td>
																					<% } else { %>
																					<td>Pickup Hours</td>
																					<% } %>
																					<td></td>
																				</tr>
																				<tr>
																					<td>No of flights</td>
																					<td><ezf:inputText name="flgtStairNum_SS" ezfName="flgtStairNum_SS" value="3" otherAttr=" size=\"10\" maxlength=\"3\" tabindex=\"1\" ezftoupper=\"\""/></td>
																					<td>From</td>
																					<td>
																						<ezf:inputText name="xxSvcFromHourMnTxt_LD" ezfName="xxSvcFromHourMnTxt_LD" value="09:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"6\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/>
																						<ezf:select name="loadDockAvalFromHourMn_AP" ezfName="loadDockAvalFromHourMn_AP" ezfBlank="1" ezfCodeName="xxTpCd" ezfDispName="xxTpNm" otherAttr=" style=\"width: 65px\" tabindex=\"1\""/>
																					</td>
																				</tr>
																				<tr>
																					<td>Width of stairs and landings(in.)</td>
																					<td><ezf:inputText name="stairAndLdgWdt_SS" ezfName="stairAndLdgWdt_SS" value="35" otherAttr=" size=\"10\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/></td>
																					<td>To</td>
																					<td>
																						<ezf:inputText name="xxSvcToHourMnTxt_LD" ezfName="xxSvcToHourMnTxt_LD" value="08:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"6\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/>
																						<ezf:select name="loadDockAvalToHourMn_AP" ezfName="loadDockAvalToHourMn_AP" ezfBlank="1" ezfCodeName="xxTpCd" ezfDispName="xxTpNm" otherAttr=" style=\"width: 65px\" tabindex=\"1\""/>
																					</td>
																				</tr>
																				<tr>
																					<td></td>
																					<td></td>
																					<td>Tractor Trailer Accessible</td>
																					<td><ezf:inputRadio name="trctrAndTrailAccsFlg_SS" ezfName="trctrAndTrailAccsFlg_SS" value="Y" otherAttr=" tabindex=\"1\""/>Yes&nbsp;<ezf:inputRadio name="trctrAndTrailAccsFlg_SS" ezfName="trctrAndTrailAccsFlg_SS" value="N" otherAttr=" tabindex=\"1\""/>No</td>
																				</tr>
																				<tr>
																					<td></td>
																					<td></td>
																					<td>Timestop</td>
																					<td>
																						<ezf:inputText name="xxSvcFromHourMnTxt_CD" ezfName="xxSvcFromHourMnTxt_CD" value="11:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"6\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/>
																						<ezf:select name="carrDelyTmHourMn_AP" ezfName="carrDelyTmHourMn_AP" ezfBlank="1" ezfCodeName="xxTpCd" ezfDispName="xxTpNm" otherAttr=" style=\"width: 65px\" tabindex=\"1\""/>
																					</td>
																				</tr>
																				<tr>
																					<td></td>
																					<td></td>
																					<td class="stab">Transport Option</td>
																					<td><ezf:select name="delyTrnspOptCd_SS" ezfName="delyTrnspOptCd_SS" ezfBlank="1" ezfCodeName="delyTrnspOptCd_L0" ezfDispName="delyTrnspOptNm_L0" otherAttr=" style=\"width: 125px\" tabindex=\"1\""/></td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>
															</fieldset>
														</td>
														<td>
															<fieldset>
																<legend style="font-size:11px;">Elevator Information & Dimensions</legend>
																<table cellspacing="0" cellpadding="0">
																	<tr>
																		<td>
																			<table cellspacing="0">
																				<col width="200">
																				<col width="100">
																				<col width="30">
																				<col width="100">
																				<tr>
																					<td>Elevator Available</td>
																					<td>
																						<ezf:inputRadio name="elevAvalFlg_SS" ezfName="elevAvalFlg_SS" value="Y" otherAttr=" onclick=\"sendServer( 'OnChange_ElevAvalFlg' );\" tabindex=\"1\" "/>Yes&nbsp;
																						<ezf:inputRadio name="elevAvalFlg_SS" ezfName="elevAvalFlg_SS" value="N" otherAttr=" onclick=\"sendServer( 'OnChange_ElevAvalFlg' );\" tabindex=\"1\" "/>No
																					</td>
																				</tr>
																				<tr>
																					<td>Elevator hours From</td>
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
																					<td>Elevator Appointment Needed?</td>
																					<td>
																						<ezf:inputRadio name="elevApptReqFlg_SS" ezfName="elevApptReqFlg_SS" value="Y" otherAttr=" onclick=\"sendServer( 'OnChange_ElevApptReqFlg' );\" tabindex=\"1\" "/>Yes&nbsp;
																						<ezf:inputRadio name="elevApptReqFlg_SS" ezfName="elevApptReqFlg_SS" value="N" otherAttr=" onclick=\"sendServer( 'OnChange_ElevApptReqFlg' );\" tabindex=\"1\" "/>No
																					</td>
																				</tr>
																				<tr>
																					<td>Elevator Contact</td>
																					<td><ezf:inputText name="elevCtacPsnNm_SS" ezfName="elevCtacPsnNm_SS" value="Mr.President" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\""/></td>
																					<td>Phone</td>
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
																							<legend style="font-size:11px;">Building Entrance</legend>
																							<table cellspacing="0">
																								<tr>
																									<td>Height(in.)</td>
																									<td><ezf:inputText name="bldgEntDoorHgt_SS" ezfName="bldgEntDoorHgt_SS" value="10" otherAttr=" size=\"7\" maxlength=\"6\" tabindex=\"1\" ezftoupper=\"\""/></td>
																								</tr>
																								<tr>
																									<td>Width(in.)</td>
																									<td><ezf:inputText name="bldgEntDoorWdt_SS" ezfName="bldgEntDoorWdt_SS" value="10" otherAttr=" size=\"7\" maxlength=\"6\" tabindex=\"1\" ezftoupper=\"\""/></td>
																								</tr>
																								<tr>
																									<td>Corridor Width(in.)</td>
																									<td><ezf:inputText name="crdrWdt_SS" ezfName="crdrWdt_SS" value="10" otherAttr=" size=\"7\" maxlength=\"6\" tabindex=\"1\" ezftoupper=\"\""/></td>
																								</tr>
																								<tr>
																									<td>Door Width(in.)</td>
																									<td><ezf:inputText name="doorWdt_SS" ezfName="doorWdt_SS" value="10" otherAttr=" size=\"7\" maxlength=\"6\" tabindex=\"1\" ezftoupper=\"\""/></td>
																								</tr>
																							</table>
																						</fieldset>
																					</td>
																					<td>
																						<fieldset>
																							<legend style="font-size:11px;">Elevator</legend>
																							<table cellspacing="0">
																								<tr>
																									<td>
																										<table cellspacing="0">
																											<tr>
																												<td>Width(in.)</td>
																												<td><ezf:inputText name="elevWdt_SS" ezfName="elevWdt_SS" value="100" otherAttr=" size=\"7\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/></td>
																											</tr>
																											<tr>
																												<td>Depth(in.)</td>
																												<td><ezf:inputText name="elevDepthNum_SS" ezfName="elevDepthNum_SS" value="100" otherAttr=" size=\"7\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/></td>
																											</tr>
																											<tr>
																												<td>Capacity(Lbs.)</td>
																												<td><ezf:inputText name="elevCapWt_SS" ezfName="elevCapWt_SS" value="100" otherAttr=" size=\"7\" maxlength=\"7\" tabindex=\"1\" ezftoupper=\"\""/></td>
																											</tr>
																										</table>
																									</td>
																									<td>
																										<fieldset>
																											<legend style="font-size:11px;">Door Opening</legend>
																											<table cellspacing="0">
																												<tr>
																													<td>Height(in.)</td>
																													<td><ezf:inputText name="elevDoorHgt_SS" ezfName="elevDoorHgt_SS" value="100" otherAttr=" size=\"7\" maxlength=\"5\" tabindex=\"1\" ezftoupper=\"\""/></td>
																												</tr>
																												<tr>
																													<td>Width(in.)</td>
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
													<tr>
														<td colspan="2">
															<fieldset>
																<legend style="font-size:11px;">Material Weights and Dimensions Information</legend>
																<%--------------------------------------------%>
																<%---------------- List START ----------------%>
																<%--------------------------------------------%>
																<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
																	<col align="left" valign="top">
																	<tr>
																		<td>
																			<div id="RightTop" style="overflow-x:hidden; width:920;"
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
																			<div id="Right" style="overflow-x:hidden; width:936; height:50px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );">
																				<table id="B" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																					<col width="120">
																					<col width="350">
																					<col width="120">
																					<col width="120">
																					<col width="120">
																					<col width="120">
																					<col width="120">
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
												<ezf:inputButton name="Add_Row" value="Add" htmlClass="pBtn4" otherAttr=" tabindex=\"1\""/>
												<ezf:inputButton name="Delete_Row" value="Delete" htmlClass="pBtn4" otherAttr=" tabindex=\"1\""/>
												
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
																	<col align="center" width="180">
																	<col align="center" width="120">
																	<col align="center" width="150">
																	<col align="center" width="60">
																	<col align="center" width="220">
																	<col align="center" width="150">
																	<tr height="24">
																		<td class="pClothBs"></td>
																		<td class="pClothBs">Role</td>
																		<td class="pClothBs">First Name</td>
																		<td class="pClothBs">Last Name</td>
																		<td class="pClothBs">Phone</td>
																		<td class="pClothBs">EXT</td>
																		<td class="pClothBs">Email</td>
																		<td class="pClothBs">Fax</td>
																	</tr>
																</table>
															</div>
															<div id="Right" style="overflow-x:hidden; width:1100; height:351px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );">
																<table id="C" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col width="24">
																	<col width="180">
																	<col width="180">
																	<col width="120">
																	<col width="150">
																	<col width="60">
																	<col width="220">
																	<col width="150">
																	<ezf:row ezfHyo="C">
																	<tr>
																		<td><ezf:inputCheckBox name="delFlg_C0" ezfName="delFlg_C0" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"delFlg_C0#{EZF_ROW_NUMBER}\" tabindex=\"1\""/></td>
																		<td><ezf:select name="ctacPsnTpCd_C0" ezfName="ctacPsnTpCd_C0" ezfHyo="C" ezfArrayNo="0" ezfCodeName="ctacPsnTpCd_L0" ezfDispName="ctacTpNm_L0" otherAttr=" style=\"width: 176px\" tabindex=\"1\""/></td>
																		<td><ezf:inputText name="ctacPsnFirstNm_C0" ezfName="ctacPsnFirstNm_C0" value="JOSHUA" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"1\""/><ezf:inputButton name="OpenWin_CtacPsn" value="..." ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" /></td>
																		<td><ezf:inputText name="ctacPsnLastNm_C0" ezfName="ctacPsnLastNm_C0" value="BLOOM" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" tabindex=\"1\""/></td>
																		<td><ezf:inputText name="ctacPsnTelNum_C0" ezfName="ctacPsnTelNum_C0" value="732-521-7659" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" tabindex=\"1\" ezftoupper=\"\""/></td>
																		<td><ezf:inputText name="ctacPsnExtnNum_C0" ezfName="ctacPsnExtnNum_C0" value="1234" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"10\" tabindex=\"1\" ezftoupper=\"\""/></td>
																		<td><ezf:inputText name="ctacPsnEmlAddr_C0" ezfName="ctacPsnEmlAddr_C0" value="JBLOOM@CSA.CANON.COM" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"320\" tabindex=\"1\" ezftoupper=\"\""/></td>
																		<td><ezf:inputText name="ctacPsnFaxNum_C0" ezfName="ctacPsnFaxNum_C0" value="732-521-7659" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" tabindex=\"1\" ezftoupper=\"\""/></td>
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
