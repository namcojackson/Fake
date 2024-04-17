<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160414140622 --%>
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
			<input type="hidden" name="pageID" value="NWAL1670Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Order Team Set up">


<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Order Team Set up" class="pTab_ON"><a href="#">Order Team Set up</a></li>
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
				</ezf:skip>
				<div class="pTab_BODY">
				<br/>
				<br/>
				<table border="0" cellpadding="1" cellspacing="0" align="left">
					<col width="560">
					<col width="5">
					<col width="540">
					<tr>
						<%-- ######################################## LEFT TABLE ######################################## --%>
						<td valign="top">
							<fieldset>
								<legend>Team</legend>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="80">
									<col width="300">
									<col width="100">
									<col width="80">
									<tr>
										<td class="stab">Team Name(*)</td>
										<td class="stab"><ezf:inputText name="ordTeamMstrNm" ezfName="ordTeamMstrNm" value="01234567890123456789012345678901234567890123456789" otherAttr=" ezftoupper=\"\" size=\"35\""/></td>
										<td class="stab" align="right">Active Team Only</td>
										<td class="stab"><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" /></td>
									</tr>
									<tr>
										<td class="stab">Zone</td>
										<td class="stab">
											<ezf:select name="ordZnCd" ezfName="ordZnCd" ezfBlank="1" ezfCodeName="ordZnCd_01" ezfDispName="ordZnDescTxt_01" otherAttr=" style=\"width:100\""/>
										</td>
										<td class="stab" colspan="2" align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" /></td>
										
									</tr>
									<tr>
										<td class="stab" colspan="4">
											<hr>
										</td>
									</tr>									<tr>
										<td class="stab" colspan="4">
											<ezf:inputButton name="Add_Team" value="Add line" htmlClass="pBtn5" />
											&nbsp;
											<ezf:inputButton name="Del_Team" value="Delete line" htmlClass="pBtn5" />
											&nbsp;
											<ezf:inputButton name="Copy_Team" value="Copy line" htmlClass="pBtn5" />
										</td>
									</tr>
									<tr>
										<td class="stab" colspan="4">
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"          value="A" />
												<jsp:param name="ShowingFrom"      value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"        value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"        value="xxPageShowOfNum" />
												<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum" />
												<jsp:param name="ShowingTotal"     value="xxPageShowTotNum" />
												<jsp:param name="ViewMode"         value="FULL" />
											</jsp:include>
											<ezf:skip>
												<table border="0" cellpadding="0" width="100%">
													<tr>
														<td align="right">
															<table border="0" cellpadding="0" align="left" cellspacing="0">
																<col>
																<tr>
																	<td>Results 801 - 1000 of 1000</td>
																</tr>
															</table>
														</td>
														<td align="right">
															<table border="0" cellpadding="0" cellspacing="0">
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
																	<td class="stab">Showing</td>
																	<td>5</td>
																	<td class="stab">/</td>
																	<td>5</td>
																	<td class="stab">page</td>
																	<td>&nbsp;</td>
																	<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')" disabled></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</ezf:skip>
										</td>
									</tr>
									<tr>
										<td class="stab" colspan="4">
											<div style="width:523px; overflow-y:hidden; overflow-x:hidden; text-align:center;">
												<table border="1" cellpadding="1" cellspacing="0" align="left">
													<col width="25" align="center"><!-- Radio -->
													<col width="165" align="center"><!-- Team Name -->
													<col width="110" align="center"><!-- Zone -->
													<col width="110" align="center"><!-- Start Date -->
													<col width="110" align="center"><!-- End Date -->
													<tr height="37">
														<td class="pClothBs"></td>
														<td class="pClothBs">Team Name</td>
														<td class="pClothBs">Zone</td>
														<td class="pClothBs">Start Date</td>
														<td class="pClothBs">End Date</td>
													</tr>
												</table>
											</div>
											<div style="width:540px; height:370px; display:block; overflow-y:scroll; margin:0px; padding: 0px; margin-left:2px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A">
													<col width="25"><!-- Radio -->
													<col width="165"><!-- Team Name -->
													<col width="108"><!-- Zone -->
													<col width="110"><!-- Start Date -->
													<col width="110"><!-- End Date -->
													<ezf:row ezfHyo="A">
													<tr height="25">
														<td align="center">
															<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr="onclick=\"sendServer('OnClick_Team');\""/>
														<ezf:skip>
															<input type="radio" name="xxRadioBtn" ezfname="xxRadioBtn" >
														</ezf:skip>
														</td>
														<td><ezf:inputText name="ordTeamMstrNm_A" ezfName="ordTeamMstrNm_A" value="01234567890123456789012345678901234567890123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" ezftoupper=\"\""/></td>
														<td>
															<ezf:select name="ordZnCd_A" ezfName="ordZnCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="ordZnCd_01" ezfDispName="ordZnDescTxt_01" otherAttr=" style=\"width:100\""/>
														</td>
														<td>
															<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="01/01/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
															<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');">
														</td>
														<td>
															<ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="12/31/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
															<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');">
														</td>
													</tr>
													<ezf:skip>
													<tr height="25">
														<td align="center"><input type="radio" name="r" ></td>
														<td><input type="text" name="omTeamMstrNm" ezfname="omTeamMstrNm" value="wwwwwwwww1wwwwwwwww2wwwwwwwww3wwwwwwwww4wwwwwwwww5" size="22"></td>
														<td>
															<select style="width:100" name="ordZnCd_A" ezfname="ordZnCd_A" ezflist="ordZnCd_01,ordZnDescTxt_01,99, ,blank" ezfhyo="A">
																				<ezf:skip>
																				<option value="" selected></option>
																				<option value="1">Central</option>
																				<option value="2">West</option>
																				<option value="3">North East</option>
																				<option value="4">South East</option>
																				</ezf:skip>
															</select>
														</td>
														<td>
															<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="01/01/2016" ezfHyo="A" ezfArrayNo="1" otherAttr=" size=\"10\" maxlength=\"10\""/>
															<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');">
														</td>
														<td>
															<ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="12/31/2016" ezfHyo="A" ezfArrayNo="1" otherAttr=" size=\"10\" maxlength=\"10\""/>
															<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');">
														</td>
													</tr>
													<tr height="25">
														<td align="center"><ezf:inputRadio name="r" ezfName="" value="" /></td>
														<td><ezf:inputText name="omTeamMstrNm" ezfName="omTeamMstrNm" value="wwwwwwwww1wwwwwwwww2wwwwwwwww3wwwwwwwww4wwwwwwwww5" otherAttr=" size=\"22\""/></td>
														<td>
															<ezf:select name="ordZnCd_A" ezfName="ordZnCd_A" ezfHyo="A" ezfArrayNo="1" ezfBlank="1" ezfCodeName="ordZnCd_01" ezfDispName="ordZnDescTxt_01" otherAttr=" style=\"width:100\""/>
														</td>
														<td>
															<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="01/01/2016" ezfHyo="A" ezfArrayNo="2" otherAttr=" size=\"10\" maxlength=\"10\""/>
															<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');">
														</td>
														<td>
															<ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="12/31/2016" ezfHyo="A" ezfArrayNo="2" otherAttr=" size=\"10\" maxlength=\"10\""/>
															<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');">
														</td>
													</tr>
													</ezf:skip>
													</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
						</fieldset>
						</td>
						
						<td>&nbsp;</td>
						<td valign="top">
							<table border="0" cellpadding="1" cellspacing="1">
								<%-- ######################################## UPPER RIGHT TABLE ######################################## --%>
								<tr>
									<td valign="top">
										<fieldset>
											<legend>Member</legend>
											<table border="0" cellpadding="1" cellspacing="0">
												<tr>
													<td class="stab">
														<ezf:inputButton name="Add_Member" value="Add line" htmlClass="pBtn5" />
														&nbsp;
														<ezf:inputButton name="Del_Member" value="Delete line" htmlClass="pBtn5" />
													</td>
												</tr>
												<tr>
													<td>
														<div style="width:537px; display:block; overflow:hidden; margin:0px; padding:0px;">
															<table border="1" cellpadding="1" cellspacing="0" align="left" width="520">
																<col width="25" align="center"><!-- Select -->
																<col width="165" align="center"><!-- User Id -->
																<col width="328" align="center"><!-- User Name -->
																<tr height="37">
																	<td class="pClothBs"></td>
																	<td class="pClothBs">User Id</td>
																	<td class="pClothBs">User Name</td>
																</tr>
															</table>
														</div>
														<div style="width:537px; height:120px; display:block; overflow-y:scroll; margin:0px; padding: 0px; margin-left:2px;">
															<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="B" width="518">
																<col width="25"><!-- Select -->
																<col width="165"><!-- User Id -->
																<col width="326"><!-- User Name -->
																<ezf:row ezfHyo="B">
																<tr>
																	<td align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																	<td>
																		<ezf:inputText name="ordTeamAttrbValTxt_B" ezfName="ordTeamAttrbValTxt_B" value="01234567" ezfHyo="B" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"16\" ezftoupper=\"\""/>
																		<ezf:inputButton name="OpenWin_ResourceLookup" value="U" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" />
																	</td>
																	<td><ezf:inputText name="fullPsnNm_B" ezfName="fullPsnNm_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWW" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"45\" maxlength=\"62\""/></td>
																</tr>
																<ezf:skip>
																<tr>
																	<td align="center"><input type="checkbox" name="xxChkBox_B" value="Y" ezfname="xxChkBox_B" ezfhyo="B"></td>
																	<td>
																		<input type="text" name="ordTeamAttrbValTxt_B" ezfname="ordTeamAttrbValTxt_B" value="01234567" ezftoupper size="16" ezfhyo="B">
																		<input class="pBtn0" type="button" name="OpenWin_ResourceLookup" value="U" onClick="sendServer(this)" ezfhyo="B">
																	</td>
																	<td><input type="text" class="pPro" readOnly name="fullPsnNm_B" ezfname="fullPsnNm_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWW" size="45" maxlength="62" ezfHyo="B"></td>
																</tr>
																<tr>
																	<td align="center"><input type="checkbox" name="xxChkBox_B" value="Y" ezfname="xxChkBox_B" ezfhyo="B"></td>
																	<td>
																		<input type="text" name="ordTeamAttrbValTxt_B" ezfname="ordTeamAttrbValTxt_B" value="01234567" ezftoupper size="16" ezfhyo="B">
																		<input class="pBtn0" type="button" name="OpenWin_ResourceLookup" value="U" onClick="sendServer(this)" ezfhyo="B">
																	</td>
																	<td><input type="text" class="pPro" readOnly name="fullPsnNm_B" ezfname="fullPsnNm_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWW" size="45" maxlength="62" ezfHyo="B"></td>
																</tr>
																</ezf:skip>
																</ezf:row>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
								<%-- ######################################## LOWER RIGHT TABLE ######################################## --%>
								<tr>
									<td valign="top">
										<fieldset>
											<legend>Attribute</legend>
											<table border="0" cellpadding="1" cellspacing="0">
												<tr>
													<td class="stab">
														<ezf:inputButton name="Add_Attribute" value="Add line" htmlClass="pBtn5" />
														&nbsp;
														<ezf:inputButton name="Del_Attribute" value="Delete line" htmlClass="pBtn5" />
													</td>
													<td align="right">
														<ezf:select name="ordTeamAttrbTpCd" ezfName="ordTeamAttrbTpCd" ezfBlank="1" ezfCodeName="ordTeamAttrbTpCd_01" ezfDispName="ordTeamAttrbTpDescTxt_01" otherEvent1=" onchange=\"sendServer('OnChangeOrdTeamAttrbTpCd', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:140\""/>
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<div style="width:537; overflow-y:hidden; overflow-x:hidden; text-align:center;">
															<table border="1" cellpadding="1" cellspacing="0" align="left" width="520">
																<col width="25" align="center"><!-- Select -->
																<col width="150" align="center"><!-- Attribute -->
																<col width="170" align="center"><!-- Value -->
																<col width="170" align="center"><!-- Description -->
																<tr height="37">
																	<td class="pClothBs"></td>
																	<td class="pClothBs">Attribute</td>
																	<td class="pClothBs">Value</td>
																	<td class="pClothBs">Description</td>
																</tr>
															</table>
														</div>
														<div style="width:537; height:250px; display:block; overflow-y:scroll; margin:0px; padding: 0px;; margin-left:2px;">
															<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="C"  width="518">
																<col width="25"><!-- Select -->
																<col width="150"><!-- Attribute -->
																<col width="170"><!-- Value -->
																<col width="168"><!-- Description -->
																<ezf:row ezfHyo="C">
																<tr>
																	<td align="center"><ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
																	<td>
																		<ezf:select name="ordTeamAttrbTpCd_C" ezfName="ordTeamAttrbTpCd_C" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="ordTeamAttrbTpCd_01" ezfDispName="ordTeamAttrbTpDescTxt_01" otherAttr=" style=\"width:140\""/>
																	</td>
																	<td>
																		<ezf:inputText name="ordTeamAttrbValTxt_C" ezfName="ordTeamAttrbValTxt_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"100\""/>
																		<ezf:inputButton name="OpenWin_Attribute" value="S" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" />
																	</td>
																	<td><ezf:inputText name="dsAcctNm_C" ezfName="dsAcctNm_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"50\""/></td>
																</tr>
																<ezf:skip>
																<tr>
																	<td align="center"><input type="checkbox" name="xxChkBox_C" value="Y" ezfname="xxChkBox_C" ezfhyo="C"></td>
																	<td>
																		<select style="width:140" name="ordTeamAttrbTpCd_C" ezfname="ordTeamAttrbTpCd_C" ezflist="ordTeamAttrbTpCd_01,ordTeamAttrbTpDescTxt_01,99, ,blank" ezfhyo="C">
																			<ezf:skip>
																			<option value="" selected></option>
																			<option value="20">CUSTOMER NUMBER</option>
																			<option value="30">LINE OF BUSINESS</option>
																			<option value="40">BRANCH</option>
																			</ezf:skip>
																		</select>
																	</td>
																	<td>
																		<ezf:inputText name="ordTeamAttrbValTxt_C" ezfName="ordTeamAttrbValTxt_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWW" ezfHyo="C" ezfArrayNo="1" otherAttr=" size=\"17\" maxlength=\"100\""/>
																		<ezf:inputButton name="OpenWin_Attribute" value="S" ezfHyo="C" ezfArrayNo="1" htmlClass="pBtn0" />
																	</td>
																	<td><ezf:inputText name="dsAcctNm_C" ezfName="dsAcctNm_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="C" ezfArrayNo="1" otherAttr=" size=\"22\" maxlength=\"50\""/></td>
																</tr>
																<tr>
																	<td align="center"><ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" ezfHyo="C" ezfArrayNo="1" /></td>
																	<td>
																		<ezf:select name="ordTeamAttrbTpCd_C" ezfName="ordTeamAttrbTpCd_C" ezfHyo="C" ezfArrayNo="1" ezfBlank="1" ezfCodeName="ordTeamAttrbTpCd_01" ezfDispName="ordTeamAttrbTpDescTxt_01" otherAttr=" style=\"width:140\""/>
																	</td>
																	<td>
																		<ezf:inputText name="ordTeamAttrbValTxt_C" ezfName="ordTeamAttrbValTxt_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWW" ezfHyo="C" ezfArrayNo="2" otherAttr=" size=\"17\" maxlength=\"100\""/>
																		<ezf:inputButton name="OpenWin_Attribute" value="S" ezfHyo="C" ezfArrayNo="2" htmlClass="pBtn0" />
																	</td>
																	<td><ezf:inputText name="dsAcctNm_C" ezfName="dsAcctNm_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="C" ezfArrayNo="2" otherAttr=" size=\"22\" maxlength=\"50\""/></td>
																</tr>
																</ezf:skip>
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
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
