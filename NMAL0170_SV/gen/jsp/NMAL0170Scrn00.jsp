<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20191023101523 --%>
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
			<input type="hidden" name="pageID" value="NMAL0170Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supersession Staging Info Inquiry">

			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
<%-- ###################### HEAD ###################### --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Personal Calendar Maintenance" class="pTab_ON"><a href="#">Tech Tng Hist</a></li>
												</div>
											</td>
											<td valign="bottom" align="center">
												<a href="#">
													<img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0">
												</a>
											</td>
											<td valign="bottom" align="center">
												<a href="#">
													<img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0">
												</a>
											</td>
										</tr>
									</table>
								</ul>
							</div>
						</ezf:skip>

<%-- ###################### BODY ###################### --%>
							<div class="pTab_BODY">
								<fieldset style="margin-left:25px; width:1040px;">
									<table border="0" cellspacing="5" cellpadding="0" style="table-layout:fixed">
										<col width="140" />
										<col width="170" />
										<col width="10" />
										<col width="110" />
										<col width="170" />
										<!-- update start 2016/02/22 v0.4 -->
										<tr>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_LinkItemNum" >Item Number</ezf:anchor></td>
											<td class="stab">
												<ezf:inputText name="supdToMdseCd" ezfName="supdToMdseCd" otherAttr=" id=\"supdToMdseCd\" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
											</td>
											<td>&nbsp;</td>
											<td class="stab">Date Staged From</td>
											<td class="stab">
												<ezf:inputText name="supdRelnStageDt_FM" ezfName="supdRelnStageDt_FM" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" id=\"supdRelnStageDt_FM\" ezftoupper=\"\""/>
												<img src="img/calendar.gif" class="pCalendar" onclick="calendar('supdRelnStageDt_FM', 4);">
											</td>
										</tr>
										<tr>
											<td class="stab">Item Type</td>
											<td class="stab">
												<ezf:select name="mdseItemTpCd_H1" ezfName="mdseItemTpCd_H1" ezfBlank="1" ezfCodeName="mdseItemTpCd_L1" ezfDispName="mdseItemTpDescTxt_L1" otherAttr=" id=\"mdseItemTpCd_H1\" style=\"width: 170px\""/>
											</td>
											<td>&nbsp;</td>
											<td class="stab">Date Staged To</td>
											<td class="stab">
												<ezf:inputText name="supdRelnStageDt_TO" ezfName="supdRelnStageDt_TO" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" id=\"supdRelnStageDt_TO\" ezftoupper=\"\""/>
												<img src="img/calendar.gif" class="pCalendar" onclick="calendar('supdRelnStageDt_TO', 4);">
											</td>
										</tr>
										<tr>
											<td class="stab">Item Classification</td>
											<td class="stab">
												<ezf:select name="mdseItemClsTpCd_H1" ezfName="mdseItemClsTpCd_H1" ezfBlank="1" ezfCodeName="mdseItemClsTpCd_L1" ezfDispName="mdseItemClsTpDescTxt_L1" otherAttr=" id=\"mdseItemClsTpCd_H1\" style=\"width: 170px\""/>
											</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<!-- update end 2016/02/22 v0.4 -->
									</table>
									<table>
										<tr>
										<!-- del 2016/02/22 V0.4
											<td>
												<input class="pBtn8" type="button" value="New" onClick="sendServer('New')" name="New" id="New">
											</td>
											<td width="5">
												&nbsp;
											</td>
											-->
											<td>
												<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search\""/>
											</td>
										</tr>
									</table>
								</fieldset>
								<hr>
<%-- ###################### DETAIL ###################### --%>
							<!-- update start 2016/02/22 v0.4 -->
							<fieldset style="margin-left:20px; width:1090px;">
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="80">
									<col width="400">
									<col width="630">
									<tr>
										<td>
											<ezf:inputButton name="InsertRow" value="insert Row" htmlClass="pBtn6" />
										</td>
										<!--<td width="10">
											&nbsp;
										</td> -->
										<td>
											<ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn6" />
										</td>
										<!--<td width="350">
											 
										</td> -->
<%-- Pagenation area start ============================================================== --%>
										<%-- =============== Paging Parts =============== --%>
										<!--<td> -->
<!--											<td align="right">
												<table border="0" cellpadding="0" align="left" cellspacing="0">
													<col>
													<tr>
														<td>Results 999 - 999 of 999</td>
													</tr>
												</table>
											</td>
-->
											<td align="right">
												<ezf:skip>
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
														<td> </td>
														<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
													</tr>
												</table>
												</ezf:skip>
												<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
													<jsp:param name="beanId"         value='<%= request.getParameter( "beanId" ) %>' />
													<jsp:param name="TableNm"        value="A" />
													<jsp:param name="ShowingFrom"    value="xxPageShowFromNum" />
													<jsp:param name="ShowingTo"      value="xxPageShowToNum" />
													<jsp:param name="ShowingOf"      value="xxPageShowOfNum" />
													<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
													<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
													<jsp:param name="ViewMode"       value="FULL" />
												</jsp:include>
										</td>
									</tr>
								</table>
<%-- Pagenation area end ============================================================== --%>
								<table align="center" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
									<tr>
										<td>
											<table border="1" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td valign="top">
														<!-- Right TBL Header -->
														<div id="rightTopTBL" style="width:1063; display:block; overflow:hidden;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTBL' ) );">
															<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" >
																<col width="25" align="center">
																<col width="190" align="center">
																<col width="190" align="center">
																<col width="190" align="center">
																<col width="190" align="center">
																<col width="110" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<tr class="pEvenNumberBGcolor" height="18">
																	<td class="pClothBs">&nbsp;</td>
																	<td class="pClothBs">Item Number</td>
																	<td class="pClothBs">Description</td>
																	<td class="pClothBs">Supersedes</td>
																	<td class="pClothBs">Description</td>
																	<td class="pClothBs">Date Staged</td>
																	<td class="pClothBs">Item Type</td>
																	<td class="pClothBs">Item Classification</td>
																</tr>
															</table>
														</div>
														<!-- Right TBL Main -->
														<div id="rightTBL" style="width:1080px; height:350px; display:block; overflow:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTopTBL' ) );">
															<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" >
																<col width="25" align="center">
																<col width="190" align="center">
																<col width="190" align="center">
																<col width="190" align="center">
																<col width="190" align="center">
																<col width="110" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<tbody>
																	<ezf:row ezfHyo="A">
																	<tr height="28">
																		<td>
																			<ezf:inputHidden name="supdRelnStagePk_A1" ezfName="supdRelnStagePk_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\""/>
																			<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A0#{EZF_ROW_NUMBER}\""/></td>
																		<td>
																			<ezf:inputText name="supdToMdseCd_A1" ezfName="supdToMdseCd_A1" value="WWWWWWWWW1WWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_LinkItemDetailSupdTo" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" ezftoupper=\"\""/>
																			<ezf:inputButton name="SetItemDescriptionTo" value=">>" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:inputText name="mdseDescShortTxt_AT" ezfName="mdseDescShortTxt_AT" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:inputText name="supdFromMdseCd_A1" ezfName="supdFromMdseCd_A1" value="WWWWWWWWW1WWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_LinkItemDetailSupdFm" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" ezftoupper=\"\""/>
																			<ezf:inputButton name="SetItemDescriptionFm" value=">>" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:inputText name="mdseDescShortTxt_AF" ezfName="mdseDescShortTxt_AF" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:inputText name="supdRelnStageDt_A1" ezfName="supdRelnStageDt_A1" value="mm/dd/yyyy" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"supdRelnStageDt_A1\" ezftoupper=\"\""/>
																			<img src="img/calendar.gif" class="pCalendar" onclick="calendar('supdRelnStageDt_A1', 4, '{EZF_ROW_NUMBER}');">
																		</td>
																		<td>
																			<ezf:inputText name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"40\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:inputText name="mdseItemClsTpNm_A1" ezfName="mdseItemClsTpNm_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"40\" ezftoupper=\"\""/>
																		</td>
																		<td class="pAuditInfo">
																			<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
																			<ezf:inputHidden name="ezUpTimeZone_A1" ezfName="ezUpTimeZone_A1" ezfHyo="A" ezfArrayNo="0" />
																		</td>
																	</tr>
																	</ezf:row>
																</tbody>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table align="right">
									<tr>
										<td>
											<ezf:inputButton name="View" value="View" htmlClass="pBtn8" otherAttr=" id=\"View\""/>
										</td>
									</tr>
								</table>
							</fieldset>
							<!-- update end 2016/02/22 v0.4 -->
							<ezf:inputHidden name="supdRelnStagePk_P" ezfName="supdRelnStagePk_P" />
							</div> <!-- End of pTab_Body -->
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
