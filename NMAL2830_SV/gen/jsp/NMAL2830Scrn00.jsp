<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160629102552 --%>
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
			<input type="hidden" name="pageID" value="NMAL2830Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Merge Prospect">


			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>

							<%-- ### TAB - HEAD ### --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Merge Prospect" class="pTab_ON"><a href="#">Merge Prospect</a></li>
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
							<div class="pTab_BODY_In">

								<%-- ### HEAD(Save Search) ### --%>
								<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
									<col width="5">
									<col width="125">
									<col width="345">
									<col width="110">
									<col width="300">
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Saved Search Options</td>
										<td>
											<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
										</td>
										<td class="stab">Search Option Name</td>
										<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td>
											<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
											<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
										</td>
									</tr>
								</table>
								<hr style="height: 0px;" cellpadding="0">

								<%-- ### HEAD ### --%>
								<table border="0" cellpadding="0" cellspacing="0" height="100">
									<tr>
										<td valign="top" width="">
											<table width="" cellpadding="0" border="0" >
												<col align="left" width="5">
												<col align="left" width="125">
												<col align="left" width="160">
												<col align="left" width="110">
												<col align="left" width="160">
												<col align="left" width="120">
												<col align="left" width="160">
												<col align="left" width="70">
												<col align="left" width="160">
												<tr>
													<td>&nbsp;</td>
													<td class="stab">Prospects created from</td>
													<td>
														<ezf:inputText name="xxFromDt" ezfName="xxFromDt" value="12/31/9999" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt', 4);" >
													</td>
													<td class="stab">Prospects created to</td>
													<td>
														<ezf:inputText name="xxToDt" ezfName="xxToDt" value="12/31/9999" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt', 4);" >
													</td>
													<td class="stab">Advanced Search</td>
													<td colspan="3">&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td class="stab">
														<ezf:anchor name="" ezfName="OpenWin_SearchAccount" ezfEmulateBtn="1" ezfGuard="OpenWin_PROS_NM" >Prospect Name(*)</ezf:anchor>
													</td>
													<td>
														<ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="123456789012345678901234567890" otherAttr=" size=\"20\" maxlength=\"360\" ezftoupper=\"\""/>
													</td>
													<td class="stab">
														<ezf:anchor name="" ezfName="OpenWin_SearchAccount" ezfEmulateBtn="1" ezfGuard="OpenWin_PROS_RVW" >Prospect #(*)</ezf:anchor>
													</td>
													<td>
														<ezf:inputText name="dsXrefAcctCd" ezfName="dsXrefAcctCd" value="123456789012345678901234567890" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/>
													</td>
													<td class="stab">Location Address(*)</td>
													<td>
														<ezf:inputText name="xxAllLineAddr" ezfName="xxAllLineAddr" value="123456789012345678901234567890" otherAttr=" size=\"20\" maxlength=\"240\" ezftoupper=\"\""/>
													</td>
													<td class="stab">
														<ezf:anchor name="" ezfName="OpenWin_SearchAccount" ezfEmulateBtn="1" ezfGuard="OpenWin_ACCT_SRH" >Account #(*)</ezf:anchor>
													</td>
													<td>
														<ezf:inputText name="dsAcctNum" ezfName="dsAcctNum" value="12345678901234567890" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td class="stab">
														<ezf:anchor name="" ezfName="OpenWin_SearchAccount" ezfEmulateBtn="1" ezfGuard="OpenWin_RESRC" >Resource Name(*)</ezf:anchor>
													</td>
													<td>
														<ezf:inputText name="fill65Txt_RN" ezfName="fill65Txt_RN" value="123456789012345678901234567890" otherAttr=" size=\"20\" maxlength=\"61\""/>
													</td>
													<td class="stab">
														<ezf:anchor name="" ezfName="OpenWin_SearchAccount" ezfEmulateBtn="1" ezfGuard="OpenWin_EMP_ID" >Employee ID(*)</ezf:anchor>
													</td>
													<td>
														<ezf:inputText name="psnCd" ezfName="psnCd" value="12345678" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
													</td>
													<td class="stab">Location City(*)</td>
													<td>
														<ezf:inputText name="ctyAddr" ezfName="ctyAddr" value="123456789012345678901234567890" otherAttr=" size=\"20\" maxlength=\"25\" ezftoupper=\"\""/>
													</td>
													<td class="stab">
														<ezf:anchor name="" ezfName="OpenWin_SearchAccount" ezfEmulateBtn="1" ezfGuard="OpenWin_LOC_SRH" >Location #(*)</ezf:anchor>
													</td>
													<td>
														<ezf:inputText name="locNum" ezfName="locNum" value="123456789012345678901234567890" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td class="stab">
														<ezf:anchor name="" ezfName="OpenWin_SearchAccount" ezfEmulateBtn="1" ezfGuard="OpenWin_TERR_NM_INFO" >Territory Name(*)</ezf:anchor>
													</td>
													<td>
														<ezf:inputText name="orgNm_TN" ezfName="orgNm_TN" value="123456789012345678901234567890" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
													<td class="stab">
														<ezf:anchor name="" ezfName="OpenWin_SearchAccount" ezfEmulateBtn="1" ezfGuard="OpenWin_ORG_NM" >Organization Name(*)</ezf:anchor>
													</td>
													<td>
														<ezf:inputText name="orgNm_ON" ezfName="orgNm_ON" value="123456789012345678901234567890" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
													<td class="stab">Location State</td>
													<td colspan="3">
														<ezf:select name="stCd" ezfName="stCd" ezfBlank="1" ezfCodeName="stCd_L" ezfDispName="stDescTxt_L" otherAttr=" style=\"width:145px\""/>
													</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td class="stab">Display Territory Visibility</td>
													<td>
														<ezf:select name="xxTpCd_D" ezfName="xxTpCd_D" ezfBlank="1" ezfCodeName="xxTpCd_DL" ezfDispName="xxTpNm_DL" otherAttr=" style=\"width:40px\""/>
													</td>
													<td class="stab">
														Real Time Inquiry
													</td>
													<td class="stab">
														<ezf:inputCheckBox name="xxChkBox_RT" ezfName="xxChkBox_RT" value="Y" />
													</td>
													<td class="stab">Location Postal Code(*)</td>
													<td>
														<ezf:inputText name="postCd" ezfName="postCd" value="123456789012345678901234567890" otherAttr=" size=\"20\" maxlength=\"15\" ezftoupper=\"\""/>
													</td>
													<td colspan="2">
														<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<hr style="height: 0px;" cellpadding="0">

								<table border="0">
									<tr>
										<td align="top" width="1500">

											<%-- ### Detail Control ### --%>
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="432">
												<col align="right" width="670">
												<tr>
													<td>&nbsp;</td>
													<td align="right">
														<ezf:skip>
														<table border="0" cellpadding="0" width="100%">
															<tr>
																<td align="left">
																	<table border="0" cellpadding="0" align="left" cellspacing="0">
																		<col>
																			<tr>
																			<td>Results 999 - 999 of 999</td>
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
																			<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																			<td class="stab">/</td>
																			<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																			<td class="stab">page</td>
																			<td>&nbsp;</td>
																			<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
																			<td></td>
																			<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
																			<td></td>
																			<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
														</ezf:skip>
														<table width="100%">
															<tr align="right">
																<td>
																	<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																		<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
																		<jsp:param name="TableNm"           value="A" />
																		<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
																		<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
																		<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
																		<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
																		<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
																		<jsp:param name="ViewMode"          value="FULL" />
																	</jsp:include>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											
											<%-- ### Detail - LEFT TBL - TOP ### --%>
<%@ page import="business.servlet.NMAL2830.constant.NMAL2830Constant" %>
<%@ page import="business.servlet.NMAL2830.NMAL2830BMsg" %>
<%@ page import="business.servlet.NMAL2830.NMAL2830_ABMsg" %>
<%  NMAL2830BMsg bMsg = (NMAL2830BMsg)databean.getEZDBMsg(); %>
											<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none; width:417; height:45; float:left;word-break: break-all;">
												<table border="1" cellpadding="1" cellspacing="0"  height="45" style="table-layout: fixed;">
													<col width="10" align="center">
													<col width="140" align="center">
													<col width="45" align="center"><!-- Merge To -->
													<col width="60" align="center"><!-- All Prospect Merge To -->
													<col width="80" align="center"><!-- Account # -->
													<col width="80" align="center"><!-- Location # -->
													<tr>
														<td class="pClothBs" colspan="2" rowspan="2">&nbsp;</td>
														<td class="pClothBs" rowspan="2">Merge<br>To</td>
														<td class="pClothBs" rowspan="2">All<br>Merge To</td>
														<td class="pClothBs" rowspan="2">Account #</td>
														<td class="pClothBs" rowspan="2">Location #</td>
													</tr>
												</table>
											</div>

											<%-- ### Detail - RIGHT TBL - TOP ### --%>
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:682; height:45; float:left;word-break: break-all;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
												<table border="1" cellpadding="1" cellspacing="0" height="45" style="table-layout: fixed;">
													<col align="center" width="115"><!-- Prospect # -->
													<col align="center" width="70"><!-- Account Category -->
													<col align="center" width="165"><!-- Account Name -->
													<col align="center" width="220"><!-- Address -->
													<col align="center" width="115"><!-- City -->
													<col align="center" width="35"><!-- State -->
													<col align="center" width="80"><!-- Postal Code -->
													<col align="center" width="500"><!-- Assigned Territory -->
													<col align="center" width="85"><!-- Exact Match -->
													<col align="center" width="85"><!-- Partial Match -->
													<col align="center" width="85"><!-- Duns Match -->
													<tr>
														<td class="pClothBs" rowspan="2">Prospect #</td>
														<td class="pClothBs" rowspan="2">Account<br>Category</td>
														<td class="pClothBs" rowspan="2">Account Name</td>
														<td class="pClothBs" rowspan="2">Address</td>
														<td class="pClothBs" rowspan="2">City</td>
														<td class="pClothBs" rowspan="2">State</td>
														<td class="pClothBs" rowspan="2">Postal Code</td>
														<td class="pClothBs" rowspan="2">Assigned Territory</td>
														<td class="pClothBs" colspan="3">Matching Condition</td>
													</tr>
													<tr>
														<td class="pClothBs">Exact Match</td>
														<td class="pClothBs">Partial Match</td>
														<td class="pClothBs">Duns Match</td>
													</tr>
												</table>
											</div>

											<%-- ### Detail - LEFT TBL - BOTTOM ### --%>
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:291; width:417; float:left;word-break: break-all;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL' ))" >
												<table border="1" cellpadding="1" cellspacing="0"  id="A" style="table-layout: fixed;">
													<col width="10">
													<col width="140">
													<col width="45" align="center"><!-- Merge To -->
													<col width="60" align="center"><!-- All Prospect Merge To -->
													<col width="80"><!-- Account # -->
													<col width="80"><!-- Location # -->
													<% int reftTblCnt = 0; %>
													<ezf:row ezfHyo="A">
														<% NMAL2830_ABMsg abMsg = bMsg.A.no(reftTblCnt); %>
														<% if(NMAL2830Constant.ROW_TP_PROSPECT.equals(abMsg.xxRowId_AT.getValue())) { %>
															<tr height="28" style="background-color:#f5f5f5;">
																<ezf:inputHidden name="xxChkBox_M" ezfName="xxChkBox_M" ezfHyo="A" ezfArrayNo="0"/>
																<ezf:inputHidden name="xxChkBox_AM" ezfName="xxChkBox_AM" ezfHyo="A" ezfArrayNo="0"/>
																<td>&nbsp;</td>
																<td colspan="2" align="center">
																	<ezf:inputText name="locNum_M" ezfName="locNum_M" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
																	<ezf:inputButton name="OpenWin_MERGE_TO" value=".." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" style=\"width:15px;\""/>
																</td>
																<td>&nbsp;</td>
																<td>
																	<ezf:inputText name="dsAcctNum_A1" ezfName="dsAcctNum_A1" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="locNum_A1" ezfName="locNum_A1" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
															</tr>
														<% } else if(NMAL2830Constant.ROW_TP_REQUESTED_MERGE_TO.equals(abMsg.xxRowId_AT.getValue())) { %>
															<tr height="28" style="background-color:#dff0d8;">
																<ezf:inputHidden name="locNum_M" ezfName="locNum_M" ezfHyo="A" ezfArrayNo="0"/>
																<ezf:inputHidden name="xxChkBox_M" ezfName="xxChkBox_M" ezfHyo="A" ezfArrayNo="0"/>
																<ezf:inputHidden name="xxChkBox_AM" ezfName="xxChkBox_AM" ezfHyo="A" ezfArrayNo="0"/>
																<td>&nbsp;</td>
																<td>Requested Merge To</td>
																<td>
																	<ezf:inputButton name="RetrieveRequestedMergeTo" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" />
																</td>
																<td>&nbsp;</td>
																<td>
																	<ezf:inputText name="dsAcctNum_A2" ezfName="dsAcctNum_A2" value="606" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="locNum_A2" ezfName="locNum_A2" value="500" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
															</tr>
														<% } else if(NMAL2830Constant.ROW_TP_DUPLICATE.equals(abMsg.xxRowId_AT.getValue())) { %>
															<tr height="28" style="background-color:#fcf8e3;">
																<ezf:inputHidden name="locNum_M" ezfName="locNum_M" ezfHyo="A" ezfArrayNo="0"/>
																<td>&nbsp;</td>
																<td>
																	<ezf:inputText name="xxRowId_AN" ezfName="xxRowId_AN" value="Option 1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputCheckBox name="xxChkBox_M" ezfName="xxChkBox_M" value="Y" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:inputCheckBox name="xxChkBox_AM" ezfName="xxChkBox_AM" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('MergeAllProspectsToThisCustomer', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"xxChkBox_AM#{EZF_ROW_NUMBER}\""/>
																</td>
																<td>
																	<ezf:inputText name="dsAcctNum_A3" ezfName="dsAcctNum_A3" value="606" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="locNum_A3" ezfName="locNum_A3" value="500" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
															</tr>
														<% } %>
														<% reftTblCnt++; %>
													</ezf:row>
													<ezf:skip>
														<tr height="28">
															<td>&nbsp;</td>
															<td colspan="2" align="center">
																<input type="text" size="20" maxlength="30" value="123456789012345678901234567890" name="dsAcctCustNum_A" ezfname="dsAcctCustNum_A"  ezfHyo="A" >
																<input type="button" class="pBtn6" value=".." name="OpenWin_SearchAccount" onclick="sendServer(this)" style="width:15px;" ezfHyo="A">
															</td>
															<td>&nbsp;</td>
															<td>
																<input type="text" value="12345678901234567890" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="123456789012345678901234567890" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
														</tr>
														<tr height="28">
															<td>&nbsp;</td>
															<td>Requested Merge To</td>
															<td>
																<input type="button" class="pBtn2" value=">>" name="Search_Account" onclick="sendServer(this)" ezfHyo="A">
															</td>
															<td>&nbsp;</td>
															<td>
																<input type="text" value="606" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="500" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
														</tr>
														<tr height="28">
															<td>&nbsp;</td>
															<td>Option 1</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="text" value="606" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="500" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
														</tr>
														<tr height="28">
															<td>&nbsp;</td>
															<td>Option 2</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="text" value="606" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="500" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
														</tr>
														<tr height="28">
															<td>&nbsp;</td>
															<td>Option 3</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="text" value="606" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="500" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
														</tr>
														<tr height="28">
															<td>&nbsp;</td>
															<td>Option 4</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="text" value="606" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="500" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
														</tr>
														<tr height="28">
															<td>&nbsp;</td>
															<td>Option 5</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="text" value="606" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="500" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
														</tr>
														<tr height="28">
															<td>&nbsp;</td>
															<td>Option 6</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="text" value="606" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="500" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
														</tr>
														<tr height="28">
															<td>&nbsp;</td>
															<td>Option 7</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="text" value="606" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="500" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
														</tr>
														<tr height="28">
															<td>&nbsp;</td>
															<td>Option 8</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="text" value="606" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="500" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
														</tr>
														<tr height="28">
															<td>&nbsp;</td>
															<td>Option 9</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="text" value="606" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="500" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
														</tr>
														<tr height="28">
															<td>&nbsp;</td>
															<td>Option 10</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="text" value="606" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="500" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
														</tr>
													</ezf:skip>
												</table>
											</div>

											<%-- ### Detail - RIGHT TBL - BOTTOM ### --%>
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:308; width:699; float:left;word-break: break-all;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed;">
													<col width="115"><!-- Prospect # -->
													<col width="70"><!-- Account Category -->
													<col width="165"><!-- Account Name -->
													<col width="220"><!-- Address -->
													<col width="115"><!-- City -->
													<col width="35"><!-- State -->
													<col width="80"><!-- Postal Code -->
													<col width="500"><!-- Assigned Territory -->
													<col align="center" width="85"><!-- Exact Match -->
													<col align="center" width="85"><!-- Partial Match -->
													<col align="center" width="85"><!-- Duns Match -->
													<% int rigthtTblCnt = 0; %>
													<ezf:row ezfHyo="A">
														<% NMAL2830_ABMsg abMsg = bMsg.A.no(rigthtTblCnt++); %>
														<% if(NMAL2830Constant.ROW_TP_PROSPECT.equals(abMsg.xxRowId_AT.getValue())) { %>
															<tr height="28" style="background-color:#f5f5f5;">
																<td>
																	<ezf:inputText name="dsXrefAcctCd_A1" ezfName="dsXrefAcctCd_A1" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="dsAcctTpNm_A1" ezfName="dsAcctTpNm_A1" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="123456789012345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="xxAllLineAddr_A1" ezfName="xxAllLineAddr_A1" value="123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="ctyAddr_A1" ezfName="ctyAddr_A1" value="1234567890123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="stCd_A1" ezfName="stCd_A1" value="12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="postCd_A1" ezfName="postCd_A1" value="123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="xxAsgTrtyNm_A1" ezfName="xxAsgTrtyNm_A1" value="123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"70\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
														<% } else if(NMAL2830Constant.ROW_TP_REQUESTED_MERGE_TO.equals(abMsg.xxRowId_AT.getValue())) { %>
															<tr height="28" style="background-color:#dff0d8;">
																<td>
																	<ezf:inputText name="dsXrefAcctCd_A2" ezfName="dsXrefAcctCd_A2" value="SFDC-4714144" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="dsAcctTpNm_A2" ezfName="dsAcctTpNm_A2" value="PROSPECT" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="dsAcctNm_A2" ezfName="dsAcctNm_A2" value="RESIDENCE INN MARRIOTT" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="xxAllLineAddr_A2" ezfName="xxAllLineAddr_A2" value="2190 OLYMPIC AVE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="ctyAddr_A2" ezfName="ctyAddr_A2" value="HENDERSON" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="stCd_A2" ezfName="stCd_A2" value="NV" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="postCd_A2" ezfName="postCd_A2" value="89014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="xxAsgTrtyNm_A2" ezfName="xxAsgTrtyNm_A2" value="662AE08 <- ESS Writing Rep(CHLOE, TODD�j" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"70\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputCheckBox name="xxChkBox_E2" ezfName="xxChkBox_E2" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputCheckBox name="xxChkBox_P2" ezfName="xxChkBox_P2" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputCheckBox name="xxChkBox_D2" ezfName="xxChkBox_D2" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\""/>
																</td>
															</tr>
														<% } else if(NMAL2830Constant.ROW_TP_DUPLICATE.equals(abMsg.xxRowId_AT.getValue())) { %>
															<tr height="28" style="background-color:#fcf8e3;">
																<td>
																	<ezf:inputText name="dsXrefAcctCd_A3" ezfName="dsXrefAcctCd_A3" value="SFDC-4714144" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="dsAcctTpNm_A3" ezfName="dsAcctTpNm_A3" value="PROSPECT" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="dsAcctNm_A3" ezfName="dsAcctNm_A3" value="RESIDENCE INN MARRIOTT" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="xxAllLineAddr_A3" ezfName="xxAllLineAddr_A3" value="2190 OLYMPIC AVE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="ctyAddr_A3" ezfName="ctyAddr_A3" value="HENDERSON" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="stCd_A3" ezfName="stCd_A3" value="NV" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="postCd_A3" ezfName="postCd_A3" value="89014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputText name="xxAsgTrtyNm_A3" ezfName="xxAsgTrtyNm_A3" value="662AE08 <- ESS Writing Rep(CHLOE, TODD�j" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"70\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputCheckBox name="xxChkBox_E3" ezfName="xxChkBox_E3" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputCheckBox name="xxChkBox_P3" ezfName="xxChkBox_P3" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\""/>
																</td>
																<td>
																	<ezf:inputCheckBox name="xxChkBox_D3" ezfName="xxChkBox_D3" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\""/>
																</td>
															</tr>
														<% } %>
													</ezf:row>
													<ezf:skip>
														<tr height="28">
															<td>
																<input type="text" value="123456789012345678901234567890" name="dsXrefAcctCd_A" ezfName="dsXrefAcctCd_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="12345678901234567890" name="dsAcctTpNm_A" ezfName="dsAcctTpNm_A" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="123456789012345678901234567890123456789012345678901234567890" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" name="xxAllLineAddr_A" ezfName="xxAllLineAddr_A" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="1234567890123456789012345" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="12" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="123456789012345" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="70" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
														</tr>
														<tr height="28">
															<td>
																<input type="text" value="SFDC-4714144" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="PROSPECT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="RESIDENCE INN MARRIOTT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="2190 OLYMPIC AVE" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="HENDERSON" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="NV" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="89014" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="662AE08 <- ESS Writing Rep(CHLOE, TODD�j" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="70" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
														</tr>
														<tr height="28">
															<td>
																<input type="text" value="SFDC-4714144" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="PROSPECT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="RESIDENCE INN MARRIOTT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="2190 OLYMPIC AVE" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="HENDERSON" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="NV" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="89014" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="662AE08 <- ESS Writing Rep(CHLOE, TODD�j" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="70" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
														</tr>
														<tr height="28">
															<td>
																<input type="text" value="SFDC-4714144" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="PROSPECT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="RESIDENCE INN MARRIOTT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="2190 OLYMPIC AVE" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="HENDERSON" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="NV" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="89014" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="662AE08 <- ESS Writing Rep(CHLOE, TODD�j" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="70" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
														</tr>
														<tr height="28">
															<td>
																<input type="text" value="SFDC-4714144" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="PROSPECT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="RESIDENCE INN MARRIOTT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="2190 OLYMPIC AVE" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="HENDERSON" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="NV" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="89014" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="662AE08 <- ESS Writing Rep(CHLOE, TODD�j" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="70" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
														</tr>
														<tr height="28">
															<td>
																<input type="text" value="SFDC-4714144" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="PROSPECT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="RESIDENCE INN MARRIOTT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="2190 OLYMPIC AVE" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="HENDERSON" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="NV" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="89014" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="662AE08 <- ESS Writing Rep(CHLOE, TODD�j" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="70" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
														</tr>
														<tr height="28">
															<td>
																<input type="text" value="SFDC-4714144" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="PROSPECT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="RESIDENCE INN MARRIOTT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="2190 OLYMPIC AVE" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="HENDERSON" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="NV" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="89014" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="662AE08 <- ESS Writing Rep(CHLOE, TODD�j" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="70" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
														</tr>
														<tr height="28">
															<td>
																<input type="text" value="SFDC-4714144" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="PROSPECT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="RESIDENCE INN MARRIOTT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="2190 OLYMPIC AVE" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="HENDERSON" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="NV" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="89014" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="662AE08 <- ESS Writing Rep(CHLOE, TODD�j" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="70" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
														</tr>
														<tr height="28">
															<td>
																<input type="text" value="SFDC-4714144" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="PROSPECT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="RESIDENCE INN MARRIOTT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="2190 OLYMPIC AVE" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="HENDERSON" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="NV" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="89014" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="662AE08 <- ESS Writing Rep(CHLOE, TODD�j" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="70" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
														</tr>
														<tr height="28">
															<td>
																<input type="text" value="SFDC-4714144" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="PROSPECT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="RESIDENCE INN MARRIOTT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="2190 OLYMPIC AVE" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="HENDERSON" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="NV" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="89014" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="662AE08 <- ESS Writing Rep(CHLOE, TODD�j" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="70" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
														</tr>
														<tr height="28">
															<td>
																<input type="text" value="SFDC-4714144" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="PROSPECT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="RESIDENCE INN MARRIOTT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="2190 OLYMPIC AVE" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="HENDERSON" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="NV" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="89014" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="662AE08 <- ESS Writing Rep(CHLOE, TODD�j" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="70" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
														</tr>
														<tr height="28">
															<td>
																<input type="text" value="SFDC-4714144" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="PROSPECT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="RESIDENCE INN MARRIOTT" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="2190 OLYMPIC AVE" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="30" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="HENDERSON" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="NV" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="89014" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="text" value="662AE08 <- ESS Writing Rep(CHLOE, TODD�j" name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" size="70" style="border:none;background-color:transparent;padding:0px;"/>
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" value="Y">
															</td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</center>



<%-- **** Task specific area ends here **** --%>
