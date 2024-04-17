<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240318092708 --%>
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
			<input type="hidden" name="pageID" value="NSAL0490Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Model Maintenance">

			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" otherAttr=" size=\"0\" id=\"xxRecHistCratTs\""/>
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm\""/>
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs\""/>
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm\""/>
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" otherAttr=" size=\"0\" id=\"xxRecHistTblNm\""/>

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
								<td width="96%"><li title = "Model Maintenance" class="pTab_ON" ><a href="#">Model Maint</a></li></td>
								<td valign="bottom" align="center"><a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a></td>
								<td valign="bottom" align="center"><a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a></td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<%-- ######################################## HEADER ######################################## --%>
				<div class="pTab_BODY">
					<table border="0" width="99%" align="center">
						<col width="440">
						<col width="">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="100">
									<col width="120">
									<col width="120">
									<col width="85">
									<tr height="22">
										<td class="stab"><ezf:anchor name="mdlNm_LK" ezfName="mdlNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_MdlNm" otherAttr=" id=\"mdlNm_LK\" ezfanchortext=\"\"">Model Name</ezf:anchor></td>
										<td colspan="2"><ezf:inputText name="mdlNm" ezfName="mdlNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"33\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td align="center"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" /></td>
									</tr>
									<tr height="22">
										<td class="stab">Model Description</td>
										<td colspan="3"><ezf:inputText name="mdlDescTxt" ezfName="mdlDescTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" otherAttr=" size=\"46\" maxlength=\"90\""/></td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="mdlGrpNm_LK" ezfName="mdlGrpNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_MdlGrp" otherAttr=" id=\"mdlGrpNm_LK\" ezfanchortext=\"\"">Model Group</ezf:anchor></td>
										<td colspan="2"><ezf:inputText name="mdlGrpNm" ezfName="mdlGrpNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"33\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
									</tr>
									<tr height="22">
										<td class="stab">Service Segment</td>
										<td colspan="2">
											<ezf:select name="svcSegCd" ezfName="svcSegCd" ezfBlank="1" ezfCodeName="svcSegCd_PL" ezfDispName="svcSegDescTxt_PL" otherAttr=" style=\"width:236px;\""/>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="22">
										<td class="stab">Active Status</td>
										<td colspan="2">
											<ezf:select name="mdlActvFlg" ezfName="mdlActvFlg" ezfCodeName="keyInfoCd_PL" ezfDispName="xxScrItem20Txt_PL" otherAttr=" style=\"width:236px;\""/>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="22">
										<td class="stab">Created Date</td>
										<td><ezf:inputText name="ezInTime_CD" ezfName="ezInTime_CD" value="12/31/9999" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
										<td colspan="2">&nbsp;</td>
									</tr>
									<tr height="25">
										<td class="stab">Model Type</td>
										<td colspan="2">
											<ezf:select name="svcMdlTpCd" ezfName="svcMdlTpCd" ezfBlank="1" ezfCodeName="svcMdlTpCd_PL" ezfDispName="svcMdlTpDescTxt_PL" otherAttr=" style=\"width:236px;\""/>
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="145">
									<col width="200">
									<col width="10">
									<col width="100">
									<col width="200">
									<tr height="22">
										<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
									</tr>
									<tr height="22">
										<td class="stab">Meter Group</td>
										<td>
											<ezf:select name="mtrGrpPk" ezfName="mtrGrpPk" ezfBlank="1" ezfCodeName="mtrGrpPk_PL" ezfDispName="mtrGrpNm_PL" otherAttr=" style=\"width:200px;\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab">Service Label Group</td>
										<td>
											<ezf:select name="svcLbGrpCd" ezfName="svcLbGrpCd" ezfBlank="1" ezfCodeName="svcLbGrpCd_PL" ezfDispName="svcLbGrpDescTxt_PL" otherAttr=" style=\"width:200px;\""/>
										</td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="svcSkillDescTxt_LK" ezfName="svcSkillDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SvcSkill" otherAttr=" id=\"svcSkillDescTxt_LK\" ezfanchortext=\"\"">Service Skills</ezf:anchor></td>
										<td><ezf:inputText name="svcSkillNum" ezfName="svcSkillNum" value="XXXX5" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/><ezf:inputText name="svcSkillDescTxt" ezfName="svcSkillDescTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"21\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="22">
										<td class="stab">Installation Rules</td>
										<td>
											<ezf:select name="svcIstlRuleNum" ezfName="svcIstlRuleNum" ezfBlank="1" ezfCodeName="svcIstlRuleNum_PY" ezfDispName="svcIstlRuleNm_PY" otherAttr=" style=\"width:200px;\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab">Install Call Group</td>
										<td>
											<ezf:select name="svcIstlCallGrpNum_IN" ezfName="svcIstlCallGrpNum_IN" ezfBlank="1" ezfCodeName="svcIstlCallGrpNum_PI" ezfDispName="svcIstlCallGrpNm_PI" otherAttr=" style=\"width:200px;\""/>
										</td>
									</tr>
									<tr height="22">
										<td class="stab">Deinstallation Rules</td>
										<td>
											<ezf:select name="svcDeinsRuleNum" ezfName="svcDeinsRuleNum" ezfBlank="1" ezfCodeName="svcIstlRuleNum_PN" ezfDispName="svcIstlRuleNm_PN" otherAttr=" style=\"width:200px;\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab">Deinstall Call Group</td>
										<td>
											<ezf:select name="svcIstlCallGrpNum_DE" ezfName="svcIstlCallGrpNum_DE" ezfBlank="1" ezfCodeName="svcIstlCallGrpNum_PD" ezfDispName="svcIstlCallGrpNm_PD" otherAttr=" style=\"width:200px;\""/>
										</td>
									</tr>
									<tr height="22">
										<td class="stab">Customer Installable</td>
										<td><ezf:inputCheckBox name="custIstlFlg" ezfName="custIstlFlg" value="Y" /></td>
										<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
									</tr>
									<tr height="22">
										<td class="stab">Install Required for Revenue</td>
										<td><ezf:inputCheckBox name="svcIstlReqFlg" ezfName="svcIstlReqFlg" value="Y" /></td>
										<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
									</tr>
									<tr height="22">
										<td class="stab">Site Survey Required</td>
										<td><ezf:inputCheckBox name="siteSrvyReqFlg" ezfName="siteSrvyReqFlg" value="Y" /></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td align="right"><ezf:inputButton name="ContractUplifts" value="Contract Uplifts" htmlClass="pBtn8" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div style="border-bottom:solid 1px #AAAAAA;padding-top:1px;margin-bottom:0;"></div>
				<br>

				<%-- ######################################## DETAIL ######################################## --%>
				<table width="98%">
					<col valign="top">
					<tr>
						<td>
							<div class="pTab_HEAD">
								<ul>
									<li id="ItemConfig" title="Item Configurations" class="pTab_ON" ><ezf:anchor name="" ezfName="xxTabProt_IC" ezfEmulateBtn="1" ezfGuard="TAB_ItemConfig" >Item Config</ezf:anchor></li>
									<li id="SvcRules" title="Service Rules" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_SR" ezfEmulateBtn="1" ezfGuard="TAB_SvcRules" >Svc Rules</ezf:anchor></li>
									<li id="SupplyMap" title="Supply Mapping" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_SM" ezfEmulateBtn="1" ezfGuard="TAB_SupplyMap" >Supply Map</ezf:anchor></li>
									<li id="EndOfLife" title="End of Life" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_EL" ezfEmulateBtn="1" ezfGuard="TAB_EndOfLife" >End of Life</ezf:anchor></li>
									<li id="Criticality" title="Criticality" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_CR" ezfEmulateBtn="1" ezfGuard="TAB_Criticality" >Criticality</ezf:anchor></li>
								</ul>
							</div>

							<c:choose>

								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++ Item Configurations TAB +++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'ItemConfig'}">
									<script type="text/javascript">
										document.getElementById( "ItemConfig").className = "pTab_ON";
										document.getElementById( "SvcRules").className = "pTab_OFF";
										document.getElementById( "SupplyMap").className = "pTab_OFF";
										document.getElementById( "EndOfLife").className = "pTab_OFF";
										document.getElementById( "Criticality").className = "pTab_OFF";
									</script>
									<div class="pTab_BODY_In">
										<br>
										<%-- Pagenation --%>
<!--
										<table border="0" cellpadding="1" cellspacing="0">
											<col width="740">
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
												<td></td>
												<td class="stab">Showing</td>
												<td class="pOut">99999</td>
												<td class="stab">to</td>
												<td class="pOut">99999</td>
												<td class="stab">of</td>
												<td class="pOut">99999</td>
												<td>&nbsp;</td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev"></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
											</tr>
										</table>
-->
										<table width="100%">
											<tr>
												<td>
													<table width="98%">
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
										<table border ="0" cellpadding="1" cellspacing="0" width="100%">
											<tr>
												<td align="top">
													<div id="TopTBL" style="overflow-y:none; overflow-x:none;">
														<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
															<col width="25">					<!-- Radio Button -->
															<col width="300" align="center">	<!-- Mdse Code -->
															<col width="250" align="center">	<!-- Mdse Name -->
															<col width="95" align="center">		<!-- Mdse Category -->
															<col width="70" align="center">		<!-- Mdse Type -->
															<col width="130" align="center">	<!-- COA Product -->
															<col width="102" align="center">	<!-- Start Date-->
															<col width="102" align="center">	<!-- End Date-->
															<tr height="27">
																<td class="pClothBs">&nbsp;</td>
																<td class="pClothBs">Item Code</td>
																<td class="pClothBs">Item Name</td>
																<td class="pClothBs">Mdse Category</td>
																<td class="pClothBs">Mdse Type</td>
																<td class="pClothBs">COA Product</td>
																<td class="pClothBs">Start Date</td>
																<td class="pClothBs">End Date</td>
																
															</tr>
														</table>
													</div>
													<div id="TBL" style="overflow-y:scroll; overflow-x:none; height:230; width:1093; word-break: break-all;">
														<table border="1" cellpadding="1" cellspacing="0" id="A">
															<col width="21" align="center">	<!-- Radio Button -->
															<col width="296">				<!-- Mdse Code -->
															<col width="246">				<!-- Mdse Name -->
															<col width="91">				<!-- Mdse Category -->
															<col width="66">				<!-- Mdse Type -->
															<col width="126">				<!-- COA Product -->
															<col width="98">				<!-- Start Date-->
															<col width="98">				<!-- End Date-->
															<ezf:row ezfHyo="A">
																<tr id="id_row{EZF_ROW_NUMBER}">
																	<td><ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn_A#{EZF_ROW_NUMBER}\""/></td>
																	<td>
																		&nbsp;<ezf:inputButton name="OpenWin_MdseCd" value="M" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_MdseCd#{EZF_ROW_NUMBER}\""/>
																		<ezf:inputText name="prntMdseCd_A" ezfName="prntMdseCd_A" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/>
																		<ezf:inputText name="childMdseCd_A" ezfName="childMdseCd_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/>
																		<ezf:inputButton name="Setting_MdseInfo" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"Setting_MdseInfo#{EZF_ROW_NUMBER}\""/>
																	</td>
																	<td><ezf:label name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:label name="mdseCatgNm_A" ezfName="mdseCatgNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:label name="mdseTpNm_A" ezfName="mdseTpNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:label name="coaProdNm_A" ezfName="coaProdNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0">
																			<tr style="padding:0;">
																				<td><ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																				<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
																			</tr>
																		</table>
																	</td>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0">
																			<tr style="padding:0;">
																				<td><ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																				<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
																			</tr>
																		</table>
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
															</ezf:skip>
														</table>
													</div>
												</td>
											</tr>
										</table>
										<table border="0" width="100%">
											<tr>
												<td align="left">
													<table border="0" cellpadding="3" cellspacing="1">
														<tr>
															<td><ezf:inputButton name="InsertRow_Parent" value="Insert Parent" htmlClass="pBtn8" /></td>
															<td><ezf:inputButton name="InsertRow_Child" value="Insert Child" htmlClass="pBtn8" /></td>
															<td><ezf:inputButton name="DeleteRow_ItemConfig" value="Delete Row" htmlClass="pBtn8" /></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
								</c:when>

								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++ Service Rules TAB +++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'SvcRules'}">
									<script type="text/javascript">
										document.getElementById( "ItemConfig").className = "pTab_OFF";
										document.getElementById( "SvcRules").className = "pTab_ON";
										document.getElementById( "SupplyMap").className = "pTab_OFF";
										document.getElementById( "EndOfLife").className = "pTab_OFF";
										document.getElementById( "Criticality").className = "pTab_OFF";
									</script>
									<div class="pTab_BODY_In">
										<br>
										<table border="0" cellpadding="0" cellspacing="0" width="100%">
											<tr>
												<td>
													<table border="0" cellpadding="3" cellspacing="0">
														<col width="25" align="center">
														<col width="310" align="center">
														<col width="320" align="center">
														<col width="345" align="center">
														<tr>
															<td>&nbsp;</td>
															<td>
																<fieldset>
																	<legend>&nbsp;<b><font size="2" color="black">Recall Rules</font></b>&nbsp;</legend>
																	<table border="0" cellpadding="0" cellspacing="0">
																		<col width="150">
																		<col width="140">
																		<tr height="28">
																			<td class="stab">Local Recall Rules (Days)</td>
																			<td><ezf:inputText name="rcllIntvlDaysAot" ezfName="rcllIntvlDaysAot" value="12.3" otherAttr=" size=\"18\" maxlength=\"4\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">Local Recall Rules (Copies)</td>
																			<td><ezf:inputText name="rcllCopyVolCnt" ezfName="rcllCopyVolCnt" value="123456789" otherAttr=" size=\"18\" maxlength=\"9\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">Global Recall Rules (Days)</td>
																			<td><ezf:inputText name="rcllGlblIntvlDaysAot" ezfName="rcllGlblIntvlDaysAot" value="12.3" otherAttr=" size=\"18\" maxlength=\"4\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">Global Recall Rules (Copies)</td>
																			<td><ezf:inputText name="rcllGlblCopyVolCnt" ezfName="rcllGlblCopyVolCnt" value="123456789" otherAttr=" size=\"18\" maxlength=\"9\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td></td><td></td>
																		</tr>
																		<tr height="28">
																			<td></td><td></td>
																		</tr>
																		<tr height="28">
																			<td></td><td></td>
																		</tr>
																	</table>
																</fieldset>
															</td>
															<td>
																<fieldset>
																	<legend>&nbsp;<b><font size="2" color="black">Machine Rules</font></b>&nbsp;</legend>
																	<table border="0" cellpadding="0" cellspacing="0">
																		<col width="160">
																		<col width="140">
																		<tr height="28">
																			<td class="stab">Response Time Target (Hours)</td>
																			<td><ezf:inputText name="xxRtoTaskTmNum" ezfName="xxRtoTaskTmNum" value="2" otherAttr=" size=\"18\" maxlength=\"5\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">Excessive Calls</td>
																			<td><ezf:inputText name="xsVisitCnt" ezfName="xsVisitCnt" value="12" otherAttr=" size=\"18\" maxlength=\"2\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">Phone Fix Enabled</td>
																			<td><ezf:inputCheckBox name="phoneFixFlg" ezfName="phoneFixFlg" value="Y" /></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">Phone Fix Period (Days)</td>
																			<td><ezf:inputText name="phoneFixIntvlDaysAot" ezfName="phoneFixIntvlDaysAot" value="123" otherAttr=" size=\"18\" maxlength=\"3\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">After Hours Allowed</td>
																			<td><ezf:inputCheckBox name="afterHourAllwFlg" ezfName="afterHourAllwFlg" value="Y" /></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">MIF Inactive(Months)</td>
																			<td><ezf:inputText name="machInFldInacMthAot" ezfName="machInFldInacMthAot" value="123.4" otherAttr=" size=\"18\" maxlength=\"5\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">Model Duration(Minutes)</td>
																			<td><ezf:inputText name="mdlDurnTmNum" ezfName="mdlDurnTmNum" value="123456789012" otherAttr=" size=\"18\" maxlength=\"12\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td></td><td></td>
																		</tr>
																		<tr height="28">
																			<td></td><td></td>
																		</tr>
																	</table>
																</fieldset>
															</td>
															<td>
																<fieldset>
																	<legend>&nbsp;<b><font size="2" color="black">Model Facts</font></b>&nbsp;</legend>
																	<table border="0" cellpadding="0" cellspacing="0">
																		<col width="185">
																		<col width="140">
																		<tr height="28">
																			<td class="stab">Average Daily Copy Volume (ADCV)</td>
																			<td><ezf:inputText name="copyVolDaysAot" ezfName="copyVolDaysAot" value="123456789" otherAttr=" size=\"18\" maxlength=\"9\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">Max Copies Per Day - Total</td>
																			<td><ezf:inputText name="maxCopyPerDayTotCnt" ezfName="maxCopyPerDayTotCnt" value="123456789" otherAttr=" size=\"18\" maxlength=\"9\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">Max Copies Per Day - BW</td>
																			<td><ezf:inputText name="maxCopyPerDayBlackCnt" ezfName="maxCopyPerDayBlackCnt" value="123456789" otherAttr=" size=\"18\" maxlength=\"9\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">Max Test Copies</td>
																			<td><ezf:inputText name="maxCopyTestCnt" ezfName="maxCopyTestCnt" value="123456789" otherAttr=" size=\"18\" maxlength=\"9\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">Speed - BW (Copies Per Minute)</td>
																			<td><ezf:inputText name="mdlSpeedBlackRate" ezfName="mdlSpeedBlackRate" value="123456789" otherAttr=" size=\"18\" maxlength=\"9\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">Speed - Color (Copies Per Minute)</td>
																			<td><ezf:inputText name="mdlSpeedColorRate" ezfName="mdlSpeedColorRate" value="123456789" otherAttr=" size=\"18\" maxlength=\"9\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																		</tr>
																		<tr height="28">
																			<td class="stab">Paper Size</td>
																			<td>
																				<ezf:select name="dsMdlPaperSizeCd" ezfName="dsMdlPaperSizeCd" ezfBlank="1" ezfCodeName="dsMdlPaperSizeCd_PL" ezfDispName="dsMdlPaperSizeDescTxt_PL" otherAttr=" style=\"width:132px;\""/></td>
																		</tr>
																	</table>
																</fieldset>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										<br>
									</div>
								</c:when>

								<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++ Supply Mapping TAB +++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'SupplyMap'}">
									<script type="text/javascript">
										document.getElementById( "ItemConfig").className = "pTab_OFF";
										document.getElementById( "SvcRules").className = "pTab_OFF";
										document.getElementById( "SupplyMap").className = "pTab_ON";
										document.getElementById( "EndOfLife").className = "pTab_OFF";
										document.getElementById( "Criticality").className = "pTab_OFF";
									</script>
									<div class="pTab_BODY_In">
										<br>
										<%-- Pagenation --%>
<!--
										<table border="0" cellpadding="1" cellspacing="0">
											<col width="740">
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
												<td></td>
												<td class="stab">Showing</td>
												<td class="pOut">99999</td>
												<td class="stab">to</td>
												<td class="pOut">99999</td>
												<td class="stab">of</td>
												<td class="pOut">99999</td>
												<td>&nbsp;</td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev"></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
											</tr>
										</table>
-->
										<table width="100%">
											<tr>
												<td>
													<table width="98%">
														<tr align="right">
															<td>
																<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																	<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																	<jsp:param name="TableNm"     value="B" />
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
										
										<table  border="0" cellpadding="1" cellspacing="0" width="100%">
											<col width="" align="" valign="top">
											<tr>
												<td align="right" valign="top">
													<%-- Left Table Header --%>
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													</table>
													<%-- Left Table Detail --%>
													<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:220;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
														<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A1" >
														</table>
													</div>
												</td>
												<td align="left" valign="top">
													<%-- Right Table Header --%>
													<div id="topRightTBL" style="overflow-x:hidden; width:1077; overflow-y:none; height:20;">
														<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;width:1244;margin-right:20px">
															<col width="25">					<!-- Radio Button -->
															<col width="185" align="center">	<!-- Mdse Code -->
															<col width="75" align="center">		<!-- OEM Code -->
															<col width="155" align="center">	<!-- Mdse Name -->
															<col width="80" align="center">		<!-- Class -->
															<col width="60" align="center">		<!-- Supply Type -->
															<col width="60" align="center">		<!-- Supply Color -->
															<col width="55" align="center">		<!-- Yield -->
															<col width="74" align="center">		<!-- Capacity -->
															<col width="60" align="center">		<!-- Tolerance -->
															<col width="46" align="center">		<!-- Cust Stock -->
															<col width="55" align="center">		<!-- Initial Quantity -->
															<col width="55" align="center">		<!-- Contract Quantity -->
															<col width="100" align="center">	<!-- Start Date-->
															<col width="100" align="center">	<!-- End Date-->
															<tr height="32">
																<td class="pClothBs">&nbsp;</td>
																<td class="pClothBs">Item Code</td>
																<td class="pClothBs">OEM Code</td>
																<td class="pClothBs">Item Name</td>
																<td class="pClothBs">Class</td>
																<td class="pClothBs">Supply<br>Type</td>
																<td class="pClothBs">Supply<br>Color</td>
																<td class="pClothBs">Yield</td>
																<td class="pClothBs">Capacity</td>
																<td class="pClothBs">Tolerance</td>
																<td class="pClothBs">Cust<br>Stock</td>
																<td class="pClothBs">Initial<br>Quantity</td>
																<td class="pClothBs">Contract<br>Quantity</td>
																<td class="pClothBs">Start Date</td>
																<td class="pClothBs">End Date</td>
															</tr>
														</table>
													</div>
													<%-- Right Table Detail --%>
													<div id="RightTBL" style="overflow-x:scroll; width:1094; overflow-y:scroll; height:220;" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) ); synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
														<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;width:1244;" id="B">
															<col width="25" align="center">	<!-- Radio Button -->
															<col width="185">				<!-- Mdse Code -->
															<col width="75">				<!-- OEM Code -->
															<col width="155">				<!-- Mdse Name -->
															<col width="80">				<!-- Class -->
															<col width="60">				<!-- Supply Type -->
															<col width="60">				<!-- Supply Color -->
															<col width="55" align="right">	<!-- Yield -->
															<col width="74">				<!-- Capacity -->
															<col width="60">				<!-- Tolerance -->
															<col width="46">				<!-- Cust Stock -->
															<col width="55">				<!-- Initial Quantity -->
															<col width="55">				<!-- Contract Quantity -->
															<col width="100">				<!-- Start Date-->
															<col width="100">				<!-- End Date-->
															<ezf:row ezfHyo="B">
																<tr id="id_row{EZF_ROW_NUMBER}">
																	<td><ezf:inputRadio name="xxRadioBtn_B" ezfName="xxRadioBtn_B" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="B" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn_B#{EZF_ROW_NUMBER}\""/></td>
																	<td>
																		<ezf:inputButton name="OpenWin_MdseCd" value="M" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_MdseCd#{EZF_ROW_NUMBER}\""/>
																		<ezf:inputText name="mdseCd_B" ezfName="mdseCd_B" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/>
																		<ezf:inputButton name="Setting_MdseInfo" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"Setting_MdseInfo#{EZF_ROW_NUMBER}\""/>
																	</td>
																	<td><ezf:label name="imgSplyOemCd_B" ezfName="imgSplyOemCd_B" ezfHyo="B" ezfArrayNo="0" /></td>
																	<td><ezf:label name="mdseDescShortTxt_B" ezfName="mdseDescShortTxt_B" ezfHyo="B" ezfArrayNo="0" /></td>
																	<td><ezf:label name="mdseItemClsTpNm_B" ezfName="mdseItemClsTpNm_B" ezfHyo="B" ezfArrayNo="0" /></td>
																	<td><ezf:label name="imgSplyTpNm_B" ezfName="imgSplyTpNm_B" ezfHyo="B" ezfArrayNo="0" /></td>
																	<td><ezf:label name="imgSplyColorTpNm_B" ezfName="imgSplyColorTpNm_B" ezfHyo="B" ezfArrayNo="0" /></td>
																	<td><ezf:label name="imgSplyYieldCnt_B" ezfName="imgSplyYieldCnt_B" ezfHyo="B" ezfArrayNo="0" /></td>
																	<td><ezf:label name="imgSplyYieldTpNm_B" ezfName="imgSplyYieldTpNm_B" ezfHyo="B" ezfArrayNo="0" /></td>
																	<td><ezf:inputText name="splyTolPct_B" ezfName="splyTolPct_B" value="123.45" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"6\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																	<td><ezf:inputText name="custStkQty_B" ezfName="custStkQty_B" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																	<td><ezf:inputText name="splyInitQty_B" ezfName="splyInitQty_B" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																	<td><ezf:inputText name="splyContrQty_B" ezfName="splyContrQty_B" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0">
																			<tr style="padding:0;">
																				<td><ezf:inputText name="effFromDt_B" ezfName="effFromDt_B" value="MM/DD/YYYY" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																				<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_B', 4, '{EZF_ROW_NUMBER}');"></td>
																			</tr>
																		</table>
																	</td>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0">
																			<tr style="padding:0;">
																				<td><ezf:inputText name="effThruDt_B" ezfName="effThruDt_B" value="MM/DD/YYYY" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																				<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_B', 4, '{EZF_ROW_NUMBER}');"></td>
																			</tr>
																		</table>
																	</td>
																	<td class="pAuditInfo">
																		<ezf:inputHidden name="xxRecHistCratTs_B" ezfName="xxRecHistCratTs_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																		<ezf:inputHidden name="xxRecHistCratByNm_B" ezfName="xxRecHistCratByNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																		<ezf:inputHidden name="xxRecHistUpdTs_B" ezfName="xxRecHistUpdTs_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																		<ezf:inputHidden name="xxRecHistUpdByNm_B" ezfName="xxRecHistUpdByNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																		<ezf:inputHidden name="xxRecHistTblNm_B" ezfName="xxRecHistTblNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																	</td>
																</tr>
															</ezf:row>
															<ezf:skip>
															
															</ezf:skip>
														</table>
													</div>
												</td>
											</tr>
										</table>
										<table border="0" width="100%">
											<tr>
												<td align="left">
													<table border="0" cellpadding="3" cellspacing="1">
														<tr>
															<td><ezf:inputButton name="InsertRow_SupplyMap" value="Insert Row" htmlClass="pBtn8" /></td>
															<td><ezf:inputButton name="DeleteRow_SupplyMap" value="Delete Row" htmlClass="pBtn8" /></td>
															<td class="stab"><ezf:anchor name="CopyModelMdlNm" ezfName="CopyModelMdlNm" ezfEmulateBtn="1" ezfGuard="OpenWin_CopyModelMdlNm" otherAttr=" id=\"CopyModelMdlNm\"">Model Name</ezf:anchor></td>
															<td><ezf:inputText name="mdlNm_CF" ezfName="mdlNm_CF" value="XX" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/></td>
															<td><ezf:inputButton name="CopyModel" value="Copy Model" htmlClass="pBtn8" /></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
								</c:when>

								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++ End of Life TAB +++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'EndOfLife'}">
									<script type="text/javascript">
										document.getElementById( "ItemConfig").className = "pTab_OFF";
										document.getElementById( "SvcRules").className = "pTab_OFF";
										document.getElementById( "SupplyMap").className = "pTab_OFF";
										document.getElementById( "EndOfLife").className = "pTab_ON";
										document.getElementById( "Criticality").className = "pTab_OFF";
									</script>
									<div class="pTab_BODY_In">
										<br>
										<table border="0" cellpadding="0" cellspacing="0" width="100%">
											<tr>
												<td>
													<table border="0" cellpadding="3" cellspacing="0">
														<col width="10" align="left">
														<col width="315" align="left">
														<col width="45" align="left">
														<col width="600" align="left">
														<tr valign="top">
															<td>&nbsp;</td>
															<td>
																<b>End of Life Control Dates</b>
																<table border="0" cellpadding="0" cellspacing="0">
																	<col width="50" align="left">
																	<col width="5" align="left">
																	<col width="240" align="left">
																	<col width="5" align="left">
																	<col width="12" align="left">
																	<col width="3" align="center">
																	<tr height="28">
																		<td class="stab">Status</td>
																		<td>&nbsp;</td>
																		<td>
																			<ezf:select name="dsMdlEolStsCd_D1" ezfName="dsMdlEolStsCd_D1" ezfBlank="1" ezfCodeName="dsMdlEolStsCd_PL" ezfDispName="dsMdlEolStsDescTxt_PL" otherAttr=" style=\"width:236px;\""/>
																		</td>
																		<td>&nbsp;</td>
																		<td><ezf:inputText name="dsMdlEolDt_D1" ezfName="dsMdlEolDt_D1" value="MM/DD/YYYY" otherAttr=" size=\"12\" maxlength=\"10\""/></td>
																		<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsMdlEolDt_D1', 4);"></td>
																	</tr>
																	<tr height="28">
																		<td class="stab">Status</td>
																		<td>&nbsp;</td>
																		<td>
																			<ezf:select name="dsMdlEolStsCd_D2" ezfName="dsMdlEolStsCd_D2" ezfBlank="1" ezfCodeName="dsMdlEolStsCd_PL" ezfDispName="dsMdlEolStsDescTxt_PL" otherAttr=" style=\"width:236px;\""/>
																		</td>
																		<td>&nbsp;</td>
																		<td><ezf:inputText name="dsMdlEolDt_D2" ezfName="dsMdlEolDt_D2" value="MM/DD/YYYY" otherAttr=" size=\"12\" maxlength=\"10\""/></td>
																		<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsMdlEolDt_D2', 4);"></td>
																	</tr>
																	<tr height="28">
																		<td class="stab">Status</td>
																		<td>&nbsp;</td>
																		<td>
																			<ezf:select name="dsMdlEolStsCd_D3" ezfName="dsMdlEolStsCd_D3" ezfBlank="1" ezfCodeName="dsMdlEolStsCd_PL" ezfDispName="dsMdlEolStsDescTxt_PL" otherAttr=" style=\"width:236px;\""/>
																		</td>
																		<td>&nbsp;</td>
																		<td><ezf:inputText name="dsMdlEolDt_D3" ezfName="dsMdlEolDt_D3" value="MM/DD/YYYY" otherAttr=" size=\"12\" maxlength=\"10\""/></td>
																		<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsMdlEolDt_D3', 4);"></td>
																	</tr>
																</table>
																<br>
																<br>
																<br>
																<br>
																<b>Dispatch Comments</b>
																<table border="0" cellpadding="0" cellspacing="0">
																<tr style="padding-top:5px">
																	<td>
																		<ezf:textArea name="eolDisptCmntTxt" ezfName="eolDisptCmntTxt" otherAttr=" cols=\"52\" rows=\"11\""/>
																	</td>
																</tr>
																</table>
															</td>
															<td>&nbsp;</td>
															<td>
																<b>Service Comments</b>
																<table border="0" cellpadding="0" cellspacing="0">
																	<col width="200" valign="top">
																	<col width="10">
																	<col width="60" valign="top">
																	<tr height="70">
																		<td class="stab">Service Contracts Information</td>
																		<td>&nbsp;</td>
																		<td>
																			<ezf:textArea name="eolSvcContrCmntTxt" ezfName="eolSvcContrCmntTxt" otherAttr=" cols=\"60\" rows=\"5\""/>
																		</td>
																	</tr>
																	<tr height="70">
																		<td class="stab">Time and Material Information</td>
																		<td>&nbsp;</td>
																		<td>
																			<ezf:textArea name="eolTmMatCmntTxt" ezfName="eolTmMatCmntTxt" otherAttr=" cols=\"60\" rows=\"5\""/>
																		</td>
																	</tr>
																	<tr height="70">
																		<td class="stab">Technical Support Information</td>
																		<td>&nbsp;</td>
																		<td>
																			<ezf:textArea name="eolTechSprtCmntTxt" ezfName="eolTechSprtCmntTxt" otherAttr=" cols=\"60\" rows=\"5\""/>
																		</td>
																	</tr>
																	<tr height="70">
																		<td class="stab">Other Comments</td>
																		<td>&nbsp;</td>
																		<td>
																			<ezf:textArea name="eolOthCmntTxt" ezfName="eolOthCmntTxt" otherAttr=" cols=\"60\" rows=\"5\""/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										<br>
									</div>
								</c:when>
								
								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++ Criticality TAB +++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Criticality'}">
									<script type="text/javascript">
										document.getElementById( "ItemConfig").className = "pTab_OFF";
										document.getElementById( "SvcRules").className = "pTab_OFF";
										document.getElementById( "SupplyMap").className = "pTab_OFF";
										document.getElementById( "EndOfLife").className = "pTab_OFF";
										document.getElementById( "Criticality").className = "pTab_ON";
									</script>
									<div class="pTab_BODY_In">
										<br>
										<table border ="0" cellpadding="1" cellspacing="0" width="100%">
											<tr>
												<td align="top">
													<div id="TopTBL" style="overflow-y:none; overflow-x:none;">
														<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
															<col width="25">								<!-- Radio Button -->
															<col width="200" align="center">				<!-- Mdse Code -->
															<col width="250" align="center">				<!-- Mdse Name -->
															<col width="150" align="center">				<!-- Criticality(Item) -->
															<col width="150" align="center">				<!-- Criticality(Model) -->
															<tr height="27">
																<td class="pClothBs" rowspan ="2">&nbsp;</td>
																<td class="pClothBs" rowspan ="2">Item Code</td>
																<td class="pClothBs" rowspan ="2">Item Name</td>
																<td class="pClothBs" colspan="2">Criticality</td>
															</tr>
															<tr>
																<td class="pClothBs" width="150">Item</td>
																<td class="pClothBs" width="150">Model</td>
															</tr>
														</table>
													</div>
													<div id="TBL" style="overflow-y:scroll; overflow-x:none; height:230; width:1093; word-break: break-all;">
														<table border="1" cellpadding="1" cellspacing="0" id="D">
															<col width="21" align="center">	<!-- Radio Button -->
															<col width="196">				<!-- Mdse Code -->
															<col width="246">				<!-- Mdse Name -->
															<col width="146">				<!-- Criticality(Item) -->
															<col width="146">				<!-- Criticality(Model) -->
															<ezf:row ezfHyo="D">
																<tr id="id_row{EZF_ROW_NUMBER}">
																	<td><ezf:inputCheckBox name="xxChkBox_D" ezfName="xxChkBox_D" value="Y" ezfHyo="D" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_D{EZF_ROW_NUMBER}\""/></td>
																	<td>
																		&nbsp;<ezf:inputButton name="OpenWin_MdseCd" value="M" ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_MdseCd#{EZF_ROW_NUMBER}\""/>
																		<ezf:inputText name="mdseCd_D" ezfName="mdseCd_D" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/>
																		<ezf:inputButton name="Setting_MdseInfo" value=">>" ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"Setting_MdseInfo#{EZF_ROW_NUMBER}\""/>
																	</td>
																	<td><ezf:label name="mdseDescShortTxt_D" ezfName="mdseDescShortTxt_D" ezfHyo="D" ezfArrayNo="0" /></td>
																	<td><ezf:label name="backOrdImpctTpDescTxt_D1" ezfName="backOrdImpctTpDescTxt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																<td>
																	<ezf:select name="backOrdImpctTpCd_D2" ezfName="backOrdImpctTpCd_D2" ezfHyo="D" ezfArrayNo="0" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_PL" ezfDispName="backOrdImpctTpDescTxt_PL" otherAttr=" style=\"width:140\""/>
																</td>
																</tr>
															</ezf:row>
															<ezf:skip>
															</ezf:skip>
														</table>
													</div>
												</td>
											</tr>
										</table>
										<table border="0" width="100%">
											<tr>
												<td align="left">
													<table border="0" cellpadding="3" cellspacing="1">
														<tr>
															<td><ezf:inputButton name="InsertRow_Criticality" value="Insert Row" htmlClass="pBtn8" /></td>
															<td><ezf:inputButton name="DeleteRow_Criticality" value="Delete Row" htmlClass="pBtn8" /></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
								</c:when>
							</c:choose>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
