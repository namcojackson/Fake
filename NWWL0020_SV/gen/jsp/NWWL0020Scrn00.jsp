<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160913090743 --%>
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
			<input type="hidden" name="pageID" value="NWWL0020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Notification Center Setup">

<center>
	<table width="100%">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<%@ page import="business.servlet.NWWL0020.NWWL0020BMsg" %>
				<% NWWL0020BMsg scrnMsg = (NWWL0020BMsg)databean.getEZDBMsg(); %>

				<div class="pTab_BODY">

<%-- ##### BODY(TAB) ##### --%>
					<div class="pTab_HEAD">
						<ul>
							<li id="Header" title="Header" class="pTab_ON">
								<ezf:anchor name="" ezfName="xxTabProt_HD" ezfEmulateBtn="1" ezfGuard="TAB_Header" otherAttr=" tabindex=\"-1\"">Header</ezf:anchor>
							</li>
							<li id="Sql" title="Sql" class="pTab_ON">
								<ezf:anchor name="" ezfName="xxTabProt_SQ" ezfEmulateBtn="1" ezfGuard="TAB_Sql" otherAttr=" tabindex=\"-1\"">SQL</ezf:anchor>
							</li>
							<li id="ActionDetail" title="ActionDetail" class="pTab_ON">
								<ezf:anchor name="" ezfName="xxTabProt_AD" ezfEmulateBtn="1" ezfGuard="TAB_ActionDetail" otherAttr=" tabindex=\"-1\"">Action Detail</ezf:anchor>
							</li>
						</ul>
						
					</div>
<%-- ##### TAB(Header) ##### --%>
					<c:choose>
						<c:when test="${pageScope._ezddatabean.xxDplyTab=='Header'}">
							<script type="text/javascript">
								document.getElementById( "Header" ).className="pTab_ON";
								document.getElementById( "Sql" ).className="pTab_OFF";
								document.getElementById( "ActionDetail" ).className="pTab_OFF";
							</script>
							<div class="pTab_BODY_In">
							<%-- ### Header Parts ### --%>
								<table border="0" cellspacing="0" cellpadding="0" style="text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
									<col width="90">
									<col width="130">
									<col width="60">
									<col width="40">
									<col width="90">
									<col width="130">
									<tr>
										<td class="stab">Name</td>
										<td class="stab" colspan="2"><ezf:inputText name="ntfyHdrNm_H0" ezfName="ntfyHdrNm_H0" value="1234567890" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
										<td></td>
										<td class="stab">Notification ID</td>
										<td class="stab"><ezf:inputText name="ntfyHdrId_H0" ezfName="ntfyHdrId_H0" value="12345678" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
									</tr>
									<tr height="26">
										<td class="stab">Description</td>
										<td colspan="5" class="stab"><ezf:inputText name="ntfyHdrDescTxt_H0" ezfName="ntfyHdrDescTxt_H0" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"60\" maxlength=\"240\""/></td>
									</tr>
									<tr height="26">
										<td class="stab">Business Area</td>
										<td colspan="2">
											<ezf:select name="ntfyBizAreaTpCd_SL" ezfName="ntfyBizAreaTpCd_SL" ezfBlank="1" ezfCodeName="ntfyBizAreaTpCd_L0" ezfDispName="ntfyBizAreaTpDescTxt_L0" otherEvent1=" onchange=\"sendServer('OnChange_BizArea')\"" otherAttr=" style=\"width:200px\" id=\"ntfyBizAreaTpCd_SL\""/>
										</td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr height="26">
										<td class="stab">Sub Area</td>
										<td colspan="2">
											<ezf:select name="ntfySubAreaTpCd_SL" ezfName="ntfySubAreaTpCd_SL" ezfBlank="1" ezfCodeName="ntfySubAreaTpCd_L0" ezfDispName="ntfySubAreaTpDescTxt_L0" otherAttr=" style=\"width:200px\" id=\"ntfySubAreaTpCd_SL\""/>
										</td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									
									<tr height="30">
										<td class="stab">Start Date</td>
										<td class="stab">
											<ezf:inputText name="effFromDt_H0" ezfName="effFromDt_H0" value="07/01/2016" otherAttr=" size=\"12\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H0', 4);" >
										</td>
										<td class="stab">End Date</td>
										<td class="stab" colspan="2">
											<ezf:inputText name="effThruDt_H0" ezfName="effThruDt_H0" value="07/31/2016" otherAttr=" size=\"12\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H0', 4);" >
										</td>
										<td class="stab">Enabled <ezf:inputCheckBox name="ntfyHdrActvFlg_H0" ezfName="ntfyHdrActvFlg_H0" value="Y" otherAttr=" id=\"ntfyHdrActvFlg_H0\""/></td>
									</tr>
									
								</table>
								<br>
								
							<%-- ### Notification Periodic Detail Parts ### --%>
								<table style="table-layout:fixed;" border="0" cellspacing="0" width="540" height="100%">
									<tr>
										<td valign="top">
											<fieldset>
												<legend style="font-size:12px;">Notification Periodic Detail</legend>
												<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
													<col width="85">
													<col width="25">
													<col width="20">
													<col width="30">
													<col width="45">
													<col width="230">
													<tr>
														<td class="stab">Frequency</td>
														<td colspan="3">
															<ezf:select name="ntfyFreqTpCd_SL" ezfName="ntfyFreqTpCd_SL" ezfCodeName="ntfyFreqTpCd_L0" ezfDispName="ntfyFreqTpDescTxt_L0" otherEvent1=" onchange=\"sendServer('OnChange_Frequency')\"" otherAttr=" style=\"width:130px\" id=\"ntfyFreqTpCd_SL\""/>
														</td>
														<td></td>
														<td></td>
													</tr>
													<% if ("03".equals(scrnMsg.ntfyFreqTpCd_SL.getValue())) { %>
													<tr>
														<td class="stab">Run Calender</td>
														<td colspan="4"><ezf:inputText name="ntfyRunDayListTxt_PD" ezfName="ntfyRunDayListTxt_PD" value="10,20" otherAttr=" size=\"20\""/></td>
														<td class="stab">End Of Month <ezf:inputCheckBox name="ntfyEomFlg_PD" ezfName="ntfyEomFlg_PD" value="Y" otherAttr=" id=\"ntfyEomFlg_PD\""/></td>
													</tr>
													<% } else if ("02".equals(scrnMsg.ntfyFreqTpCd_SL.getValue())) { %>
													<tr>
														<td class="stab" colspan="6">
														<table width="360">
														<tr>
														<td>
														<fieldset>
														<legend style="font-size:12px;">Run Days</legend>
															<table>
																<col width="90">
																<col width="90">
																<col width="90">
																<col width="90">
																<tr>
																	<td class="stab">
																		<ezf:inputCheckBox name="ntfyRunSunFlg_PD" ezfName="ntfyRunSunFlg_PD" value="Y" otherAttr=" id=\"ntfyRunSunFlg_PD\""/>Sunday
																	</td>
																	<td class="stab">
																		<ezf:inputCheckBox name="ntfyRunMonFlg_PD" ezfName="ntfyRunMonFlg_PD" value="Y" otherAttr=" id=\"ntfyRunMonFlg_PD\""/>Monday
																	</td>
																	<td class="stab">
																		<ezf:inputCheckBox name="ntfyRunTueFlg_PD" ezfName="ntfyRunTueFlg_PD" value="Y" otherAttr=" id=\"ntfyRunTueFlg_PD\""/>Tuesday
																	</td>
																	<td class="stab">
																		<ezf:inputCheckBox name="ntfyRunWedFlg_PD" ezfName="ntfyRunWedFlg_PD" value="Y" otherAttr=" id=\"ntfyRunWedFlg_PD\""/>Wednesday
																	</td>
																</tr>
																<tr>
																	<td class="stab">
																		<ezf:inputCheckBox name="ntfyRunThuFlg_PD" ezfName="ntfyRunThuFlg_PD" value="Y" otherAttr=" id=\"ntfyRunThuFlg_PD\""/>Thursday
																	</td>
																	<td class="stab">
																		<ezf:inputCheckBox name="ntfyRunFriFlg_PD" ezfName="ntfyRunFriFlg_PD" value="Y" otherAttr=" id=\"ntfyRunFriFlg_PD\""/>Friday
																	</td>
																	<td class="stab">
																		<ezf:inputCheckBox name="ntfyRunSatFlg_PD" ezfName="ntfyRunSatFlg_PD" value="Y" otherAttr=" id=\"ntfyRunSatFlg_PD\""/>Saturday
																	</td>
																	<td></td>
																</tr>
															</table>
														</td>
														</fieldset>
														</td>
														</tr>
														</table>
													</tr>
													<% } %>
													<tr>
														<td class="stab">Start Time</td>
														<td colspan="2"><ezf:inputText name="xxStartDplyTmTxt" ezfName="xxStartDplyTmTxt" value="07:00" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"6\" maxlength=\"5\""/></td>
														<td class="stab" align="right" >Repeat</td>
														<td><ezf:inputCheckBox name="xxRptChkFlg_PD" ezfName="xxRptChkFlg_PD" value="Y" onClick="sendServer('OnChange_RepeatChk')" otherAttr=" id=\"xxRptChkFlg_PD\""/></td>
														<td></td>
													</tr>
													<tr>
														<td class="stab">End Time</td>
														<td><ezf:inputText name="xxEndDplyTmTxt" ezfName="xxEndDplyTmTxt" value="21:30" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"6\" maxlength=\"5\""/></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
													
														<td class="stab" colspan="2">Interval</td>
														<td><ezf:inputRadio name="xxRadioBtn_PD" ezfName="xxRadioBtn_PD" value="1" otherAttr=" onclick=\"sendServer( 'OnChange_SelIntvl' );\" "/></td>
														<td><ezf:inputText name="ntfyIntvlAot_PD" ezfName="ntfyIntvlAot_PD" value="2" otherAttr=" size=\"4\" maxlength=\"3\""/></td>
														<td colspan="2">
															<ezf:select name="ntfyIntvlUomTpCd_SL" ezfName="ntfyIntvlUomTpCd_SL" ezfBlank="1" ezfCodeName="ntfyIntvlUomTpCd_L0" ezfDispName="ntfyIntvlUomTpDescTxt_L0" otherAttr=" style=\"width:100px\" id=\"ntfyIntvlUomTpCd_SL\""/>
														</td>
													</tr>
													<tr>
														<td class="stab" colspan="2">Minutes after each hour</td>
														<td><ezf:inputRadio name="xxRadioBtn_PD" ezfName="xxRadioBtn_PD" value="2" otherAttr=" onclick=\"sendServer( 'OnChange_SelIntvl' );\" "/></td>
														<td colspan="3"><ezf:inputText name="ntfyRunMnListTxt_PD" ezfName="ntfyRunMnListTxt_PD" value="10,30" otherAttr=" size=\"20\" maxlength=\"180\""/></td>
													</tr>
													<tr>
														<td class="stab" colspan="2">Maintain History For(Days)</td>
														<td colspan="2"><ezf:inputText name="histDaysAot_PD" ezfName="histDaysAot_PD" value="7" otherAttr=" size=\"5\" maxlength=\"3\""/></td>
														<td></td>
														<td></td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
								</table>
							</div>
						</c:when>
					</c:choose>
<%-- ##### TAB(SQL) ##### --%>
					<c:choose>
						<c:when test="${pageScope._ezddatabean.xxDplyTab=='Sql'}">
							<script type="text/javascript">
								document.getElementById( "Header" ).className="pTab_OFF";
								document.getElementById( "Sql" ).className="pTab_ON";
								document.getElementById( "ActionDetail" ).className="pTab_OFF";
							</script>
							<div class="pTab_BODY_In">
							<%-- ### Header Parts ### --%>
								<table border="0" cellspacing="0" cellpadding="0" style="text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
									<col width="90">
									<col width="130">
									<col width="60">
									<col width="40">
									<col width="90">
									<col width="130">
									<tr>
										<td class="stab">Name</td>
										<td class="stab" colspan="2"><ezf:inputText name="ntfyHdrNm_H0" ezfName="ntfyHdrNm_H0" value="1234567890" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
										<td></td>
										<td class="stab">Notification ID</td>
										<td class="stab"><ezf:inputText name="ntfyHdrId_H0" ezfName="ntfyHdrId_H0" value="12345678" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
										
									</tr>
									<tr height="26">
										<td class="stab">Description</td>
										<td colspan="5" class="stab"><ezf:inputText name="ntfyHdrDescTxt_H0" ezfName="ntfyHdrDescTxt_H0" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"60\" maxlength=\"240\""/></td>
									</tr>
								</table>
								<br>
								
							<%-- ### SQL Parts ### --%>
								<table style="table-layout:fixed;" border="0" cellspacing="0">
									<tr>
										<td>
											<label>Notification Selection(SQL)</label>
										</td>
									</tr>
									<tr>
										<td>
											<ezf:textArea name="xxNtfySqlTxt" ezfName="xxNtfySqlTxt" otherAttr=" rows=\"35\" cols=\"150\""/>
										</td>
									</tr>
									<br>
									<tr>
										<td>
											<ezf:inputButton name="Validate" value="Validate" htmlClass="pBtn8" />
											<ezf:inputButton name="Run" value="Run" htmlClass="pBtn8" />
										</td>
									</tr>
								</table>
							</div>
						</c:when>
					</c:choose>
<%-- ##### TAB(ActionDetail) ##### --%>
					<c:choose>
						<c:when test="${pageScope._ezddatabean.xxDplyTab=='ActionDetail'}">
						<div id="TBL" style="width:100%; height:540px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
							<script type="text/javascript">
								document.getElementById( "Header" ).className="pTab_OFF";
								document.getElementById( "Sql" ).className="pTab_OFF";
								document.getElementById( "ActionDetail" ).className="pTab_ON";
							</script>
							<div class="pTab_BODY_In">
							<%-- ### Header Parts ### --%>
								<table border="0" cellspacing="0" cellpadding="0" style="text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
									<col width="90">
									<col width="130">
									<col width="60">
									<col width="40">
									<col width="90">
									<col width="130">
									<tr>
										<td class="stab">Name</td>
										<td class="stab" colspan="2"><ezf:inputText name="ntfyHdrNm_H0" ezfName="ntfyHdrNm_H0" value="1234567890" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
										<td></td>
										<td class="stab">Notification ID</td>
										<td class="stab"><ezf:inputText name="ntfyHdrId_H0" ezfName="ntfyHdrId_H0" value="12345678" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
										
									</tr>
									<tr height="26">
										<td class="stab">Description</td>
										<td colspan="5" class="stab"><ezf:inputText name="ntfyHdrDescTxt_H0" ezfName="ntfyHdrDescTxt_H0" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"60\" maxlength=\"240\""/></td>
									</tr>
								</table>
								<br>
								
							<%-- ### Notification Actions Parts ### --%>
								<table style="table-layout:fixed;" border="0" cellspacing="0">
									<tr>
										<td>
											<label>Notification Actions</label>
										</td>
									</tr>
								</table>
								<table style="table-layout:fixed;" border="0" cellspacing="0">
									<col width="90">
									<col width="550">
									<col width="90">
									<tr>
										<td align="center" valign="top">
											<ezf:inputButton name="Add_Line" value="+" htmlClass="pBtn1" />
											<ezf:inputButton name="Del_Line" value="-" htmlClass="pBtn1" />
										</td>
										<td>
											<table>
												<tr>
													<td style="padding-left:3px;">
														<div>
															<div id="TopTBL" style="overflow-y:none; overflow-x:none;">
																<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="500px" style="margin-right:20px" >
																	<col align="center" width="030">	<!-- Radio -->
																	<col align="center" width="055">	<!-- Action ID -->
																	<col align="center" width="090">	<!-- Action Name -->
																	<col align="center" width="090">	<!-- Description -->
																	<col align="center" width="090">	<!-- Notification Type -->

																	<tr id="id_row${EZF_ROW_NUMBER}" height="30">
																		<td class="pClothBs">&nbsp;</td>
																		<td class="pClothBs">
																			<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyActId_A0' )">Action ID
																				<img id="sortIMG.ntfyActId_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
																			</a>
																		</td>
																		<td class="pClothBs">
																			<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyActNm_A0' )">Action Name
																				<img id="sortIMG.ntfyActNm_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
																			</a>
																		</td>
																		<td class="pClothBs">
																			<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyActDescTxt_A0' )">Description
																				<img id="sortIMG.ntfyActDescTxt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
																			</a>
																		</td>
																		<td class="pClothBs">
																			<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyActTpDescTxt_A0' )">Action Type
																				<img id="sortIMG.ntfyActTpDescTxt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
																			</a>
																		</td>
																	</tr>
																</table>
															</div>
															
															<div id="RowTBL" style="width:517px; height:74px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
																<table border="1" cellpadding="1" cellspacing="0" id="A" width="500" style="table-layout:fixed;">
																	<col align="center" width="030">	<!-- Radio -->
																	<col align="center" width="055">	<!-- Action ID -->
																	<col align="center" width="090">	<!-- Action Name -->
																	<col align="center" width="090">	<!-- Description -->
																	<col align="center" width="090">	<!-- Notification Type -->
																	<tbody>
																	<ezf:row ezfHyo="A">
																		<tr id="id_row{EZF_ROW_NUMBER}" height="24">
																			<td><ezf:inputRadio name="xxRadioBtn_A0" ezfName="xxRadioBtn_A0" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn\""/></td>
																			<td align="left">
																				<ezf:anchor name="ntfyActId_A0" ezfName="ntfyActId_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewActionDetail" otherAttr=" id=\"ntfyActId_A0#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
																					<ezf:label name="ntfyActId_A0" ezfName="ntfyActId_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"ntfyActId_A0#{EZF_ROW_NUMBER}\""/>
																				</ezf:anchor>
																			</td>
																			<td align="center"><ezf:inputText name="ntfyActNm_A0" ezfName="ntfyActNm_A0" value="Action Name234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" style=\"border:none; background-color:transparent;\""/></td>
																			<td align="center"><ezf:inputText name="ntfyActDescTxt_A0" ezfName="ntfyActDescTxt_A0" value="Desc0000901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" style=\"border:none; background-color:transparent;\""/></td>
																			<td align="center"><ezf:inputText name="ntfyActTpDescTxt_A0" ezfName="ntfyActTpDescTxt_A0" value="ActionType123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" style=\"border:none; background-color:transparent;\""/></td>
																		</tr>
																	</ezf:row>
																	<ezf:skip>
																		
																	</ezf:skip>
																	</tbody>
																</table>
															</div>
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td align="left" valign="bottom">
											<ezf:inputButton name="ViewActionDetail" value="Edit" htmlClass="pBtn4" />
										</td>
									</tr>
									
								</table>
								<hr>
							<%-- ### Notification Action Detail Parts ### --%>
								<table style="table-layout:fixed;" border="0" cellspacing="0">
									<tr>
										<td>
											<label>Notification Action Detail</label>
										</td>
									</tr>
								</table>
								<table style="table-layout:fixed;" border="0" cellspacing="0">
									<col width="90">
									<col width="450">
									<col width="90">
									<col width="450">
									<tr>
										<td class="stab">Action Name</td>
										<td class="stab"><ezf:inputText name="ntfyActNm" ezfName="ntfyActNm" value="Action Name234567890123456789012345678901234567890" otherAttr=" size=\"55\" maxlength=\"60\""/></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td class="stab">Description</td>
										<td class="stab"><ezf:inputText name="ntfyActDescTxt" ezfName="ntfyActDescTxt" value="Description XXXXXXXXXXXX" otherAttr=" size=\"55\" maxlength=\"240\""/></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td class="stab">Action Type</td>
										<td>
											<ezf:select name="ntfyActTpCd_SL" ezfName="ntfyActTpCd_SL" ezfCodeName="ntfyActTpCd_L0" ezfDispName="ntfyActTpDescTxt_L0" otherAttr=" style=\"width:200px\" id=\"ntfyActTpCd_SL\""/>
										</td>
										<td class="stab">To</td>
										<td class="stab"><ezf:inputText name="ntfyEmlToAddr" ezfName="ntfyEmlToAddr" value="1234567890" otherAttr=" size=\"55\" maxlength=\"2000\""/></td>
									</tr>
									<tr>
										<td class="stab">Output Type</td>
										<td>
											<ezf:select name="ntfyOtptTpCd_SL" ezfName="ntfyOtptTpCd_SL" ezfCodeName="ntfyOtptTpCd_L0" ezfDispName="ntfyOtptTpDescTxt_L0" otherAttr=" style=\"width:200px\" id=\"ntfyOtptTpCd_SL\""/>
										</td>
										<td class="stab">Cc</td>
										<td class="stab"><ezf:inputText name="ntfyEmlCcAddr" ezfName="ntfyEmlCcAddr" value="1234567890" otherAttr=" size=\"55\" maxlength=\"2000\""/></td>
									</tr>
									<tr>
										<td class="stab">Reply To</td>
										<td class="stab"><ezf:inputText name="ntfyEmlRpyToAddr" ezfName="ntfyEmlRpyToAddr" value="1234567890" otherAttr=" size=\"55\" maxlength=\"2000\""/></td>
										<td class="stab">Bcc</td>
										<td class="stab"><ezf:inputText name="ntfyEmlBccAddr" ezfName="ntfyEmlBccAddr" value="1234567890" otherAttr=" size=\"55\" maxlength=\"2000\""/></td>
									</tr>
									<tr>
										<td class="stab" colspan="2">Retrieve To address from SQL&nbsp;&nbsp;<ezf:inputCheckBox name="rtrvToAddrFromSqlFlg" ezfName="rtrvToAddrFromSqlFlg" value="Y" onClick="sendServer('OnChange_RetrieveChk')" /></td>
										<% if ("Y".equals(scrnMsg.rtrvToAddrFromSqlFlg.getValue()) || "N".equals(scrnMsg.xxDplyCtrlFlg.getValue()) || "Y".equals(scrnMsg.xxDtlProtFlg.getValue())) { %>
											<td class="stab">Distribution List</td>
										<% } else { %>
											<td class="stab">
												<ezf:anchor name="ntfyDistListNmListTxt_LK" ezfName="ntfyDistListNmListTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_DistList" otherAttr=" ezfanchortext=\"\"">
													<ezf:label name="ntfyDistListNmListTxt_LK" ezfName="ntfyDistListNmListTxt_LK" />
												</ezf:anchor>
											</td>
										<% } %>
										<td class="stab"><ezf:inputText name="ntfyDistListNmListTxt" ezfName="ntfyDistListNmListTxt" value="1234567890" otherAttr=" size=\"55\" maxlength=\"2000\""/></td>
									</tr>
									<tr>
										<td class="stab" colspan="2">Attachment Format&nbsp;&nbsp;
											<ezf:select name="ntfyAttTpCd_SL" ezfName="ntfyAttTpCd_SL" ezfCodeName="ntfyAttTpCd_L0" ezfDispName="ntfyAttTpDescTxt_L0" otherAttr=" style=\"width:200px\" id=\"ntfyAttTpCd_SL\""/>
										</td>
										<td></td>
									</tr>
									<tr>
										<td class="stab">Subject</td>
										<td class="stab" colspan="3"><ezf:inputText name="ntfyEmlSubjTxt" ezfName="ntfyEmlSubjTxt" value="1234567890" otherAttr=" size=\"132\" maxlength=\"200\""/></td>
									</tr>
									<tr>
										<td class="stab" valign="top">Body</td>
										<td colspan="3">
											<ezf:textArea name="xxNtfyEmlBodyTxt" ezfName="xxNtfyEmlBodyTxt" otherAttr=" rows=\"20\" cols=\"130\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">#of Column</td>
										<td class="stab" colspan="3">
											<ezf:inputText name="xxNum" ezfName="xxNum" value="6" otherAttr=" size=\"3\" maxlength=\"3\""/>
											<ezf:inputButton name="CreateColumn" value=">>" htmlClass="pBtn4" />
											&nbsp;&nbsp;Please enter Summary Table Header Labels and Details data template below.
										</td>
									</tr>
									<tr>
										<td></td>
										<td colspan="3">
										
										<table>
												<tr>
													<td style="padding-left:3px;">
														<div>
															<div id="TopTBL" style="overflow-y:none; overflow-x:none;">
																<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="284px" style="margin-right:20px" >
																	<col align="center" width="037">	<!-- Sort Num -->
																	<col align="center" width="097">	<!-- Label -->
																	<col align="center" width="150">	<!-- Place Holder -->

																	<tr id="id_row${EZF_ROW_NUMBER}" height="24">
																		<td class="pClothBs">Sort</td>
																		<td class="pClothBs">Label</td>
																		<td class="pClothBs">Value</td>
																	</tr>
																</table>
															</div>
															
															<div id="RowTBL" style="width:301px; height:74px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
																<table border="1" cellpadding="1" cellspacing="0" id="B" width="284" style="table-layout:fixed;">
																	<col align="center" width="037">	<!-- Sort Num -->
																	<col align="center" width="097">	<!-- Label -->
																	<col align="center" width="150">	<!-- Place Holder -->
																	<tbody>
																	<ezf:row ezfHyo="B">
																		<tr id="id_row{EZF_ROW_NUMBER}" height="24">
																			<td><ezf:inputText name="ntfyActDtlColSortNum_B0" ezfName="ntfyActDtlColSortNum_B0" value="1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"4\" maxlength=\"3\""/></td>
																			<td><ezf:inputText name="hdrLbNm_B0" ezfName="hdrLbNm_B0" value="Line#" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"60\""/></td>
																			<td><ezf:inputText name="placeHldNm_B0" ezfName="placeHldNm_B0" value="DS_CPO_DTL_LINE_NUM" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\""/></td>
																		</tr>
																	</ezf:row>
																	<ezf:skip>
																		<tr id="id_row{EZF_ROW_NUMBER}" height="24">
																			<td><input type="text" ezfhyo="B"  name="ntfyActDtlColSortNum_B0" ezfname="ntfyActDtlColSortNum_B0" class="pPro" size="4" value="2"></td>
																			<td><input type="text" ezfhyo="B"  name="hdrLbNm_B0" ezfname="hdrLbNm_B0" class="pPro" size="13" value="Item#"></td>
																			<td><input type="text" ezfhyo="B"  name="placeHldNm_B0" ezfname="placeHldNm_B0" class="pPro" size="20" value="MDSE_CD"></td>
																		</tr>
																		<tr id="id_row{EZF_ROW_NUMBER}" height="24">
																			<td><input type="text" ezfhyo="B"  name="ntfyActDtlColSortNum_B0" ezfname="ntfyActDtlColSortNum_B0" class="pPro" size="4" value="3"></td>
																			<td><input type="text" ezfhyo="B"  name="hdrLbNm_B0" ezfname="hdrLbNm_B0" class="pPro" size="13" value="Qty"></td>
																			<td><input type="text" ezfhyo="B"  name="placeHldNm_B0" ezfname="placeHldNm_B0" class="pPro" size="20" value="ORD_QTY"></td>
																		</tr>
																		<tr id="id_row{EZF_ROW_NUMBER}" height="24">
																			<td><input type="text" ezfhyo="B"  name="ntfyActDtlColSortNum_B0" ezfname="ntfyActDtlColSortNum_B0" class="pPro" size="4" value="4"></td>
																			<td><input type="text" ezfhyo="B"  name="hdrLbNm_B0" ezfname="hdrLbNm_B0" class="pPro" size="13" value="Price"></td>
																			<td><input type="text" ezfhyo="B"  name="placeHldNm_B0" ezfname="placeHldNm_B0" class="pPro" size="20" value="ORD_PRC"></td>
																		</tr>
																	</ezf:skip>
																	</tbody>
																</table>
															</div>
														</div>
													</td>
												</tr>
											</table>
										
										</td>
									</tr>
								</table>
							</div>
						</div>
						</c:when>
					</c:choose>
				</div>
			</td>
		</tr>
	</table>
</center>

<script type="text/javascript">
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
