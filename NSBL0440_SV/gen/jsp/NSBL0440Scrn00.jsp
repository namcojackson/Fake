<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180604150300 --%>
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
			<input type="hidden" name="pageID" value="NSBL0440Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mods Machine Level Status">

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
													<li title="Mods Machine Level Status" class="pTab_ON"><a href="#">MdMchnLvStts</a></li>
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
								<table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="5">
												<col width="100" valign="top">
												<col width="840" valign="top">
												<col width="" valign="top">
												<tr>
													<td class="stab">Mod Plan ID</td>
													<td><ezf:inputText name="svcModPlnId" ezfName="svcModPlnId" value="1234567890123456" otherAttr=" size=\"18\""/></td>
													<td><ezf:inputButton name="MoveWin_ModPlanSearch" value="Mod Plan Search" htmlClass="pBtn9" /></td>
												</tr>
												<tr>
													<td class="stab">Description</td>
													<td><ezf:inputText name="svcModNm" ezfName="svcModNm" value="12345678901234567901234567890123456789X" otherAttr=" size=\"42\""/></td>
													<td><ezf:inputButton name="MoveWin_PlanDetail" value="Plan Detail" htmlClass="pBtn9" /></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td class="stab">
														Position to row Item Code
														<ezf:inputText name="mdseCd_F" ezfName="mdseCd_F" value="123456789012345X" otherAttr=" size=\"18\" maxlength=\"16\" ezftoupper=\"\""/>
														&nbsp;&nbsp;
														or
														<ezf:inputCheckBox name="xxChkBox_OR" ezfName="xxChkBox_OR" value="Y" />
														&nbsp;&nbsp;
														Serial#
														<ezf:inputText name="serNum_F" ezfName="serNum_F" value="12345678901234567890123456789X" otherAttr=" size=\"32\" maxlength=\"30\" ezftoupper=\"\""/>
														<ezf:inputButton name="Filter" value="Filter" htmlClass="pBtn6" />
													</td>
													<td><ezf:inputButton name="MoveWin_SerialAssinment" value="Serial# Assign" htmlClass="pBtn9" /></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td class="stab">
													    IB Status
													    &nbsp;&nbsp;
														<ezf:select name="svcMachMstrStsCd" ezfName="svcMachMstrStsCd" ezfBlank="1" ezfCodeName="svcMachMstrStsCd_01" ezfDispName="svcMachMstrStsDescTxt_01" otherAttr=" style=\"width:160;\""/>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														Mods Status
														&nbsp;&nbsp;
														<ezf:select name="svcModProcStsCd" ezfName="svcModProcStsCd" ezfBlank="1" ezfCodeName="svcModProcStsCd_01" ezfDispName="svcModProcStsDescTxt_01" otherAttr=" style=\"width:160;\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td >
										<hr width="98%" align="left">
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
										<table border="0" style="table-layout:fixed;width=1035px;margin-left:20;">
											<col width="135">
											<col width="300">
											<col width="600">
											<tr>
												<td></td>
												<td></td>
												<!-- Pagination & Navigation--START-->
												<td align="">
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
													<table border="0" cellpadding="1" width="100%">
														<tr>
															<td	align="left">
																<table border="0" cellpadding="1" align="right" cellspacing="0">
																	<col>
																	<tr>
																		<td	class="stab">Result 9999 - 9999 of	9999</td>
																	</tr>
																 </table>
															</td>
															<td	align="right">
																<table border="0" cellpadding="1" cellspacing="0">
																	<col width="54"	 align="center">
																	<col width="40"	 align="right">
																	<col width="16"	 align="center">
																	<col width="40"	 align="right">
																	<col width="16"	 align="center">
																	<col width="10">
																	<col>
																	<col width="20">
																	<col>	 
																	<col width="1">
																	<col>
																	<tr>
																		<td	class="stab">Showing</td>
																		<td><input type="text" name="key_ShowingCurrent" ezfName="key_ShowingCurrent" value="99999"	size="4" maxlength="4"/></td>
																		<td	class="stab">/</td>
																		<td><input type="text" name="key_ShowingTotal" ezfName="key_ShowingTotal" size="4" maxlength="4" value="99999" class="pPro"	readOnly></td>
																		<td	class="stab">page</td>
																		<td>&nbsp;</td>
																		<td><input type="button" class="pBtn3" value="Jump"	name="PageJump"	onclick="tablePagenation(this.name,	'key_TableNm')"	></td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Prev"	name="PagePrev"	onclick="tablePagenation(this.name,	'key_TableNm')"	></td>
																		<td></td>
																		<td><input type="button" class="pBtn3" value="Next"	name="PageNext"	onclick="tablePagenation(this.name,	'key_TableNm')"	></td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
													</ezf:skip>
												</td>
												<!-- Pagination & Navigation--END-->
											</tr>
										</table>
<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
										</td>
									</tr>
<!-- ######################################## DETAIL #################################### -->
									<tr>
										<td valign="top">
											<%-- ########## TOP ########## --%>
											<div id="top" style="overflow-y:hidden; height:; overflow-x:hidden; width:1060; text-align:center;">
												<table border="1" cellpadding="1" cellspacing="0" align="left">
													<col width="20" align="center">
													<col width="120" align="center">
													<col width="120" align="center">
													<col width="100" align="center">
													<col width="60" align="center">
													<col width="80" align="center">
													<col width="100" align="center">
													<col width="75" align="center">
													<col width="110" align="center">
													<col width="235" align="center">
													<tr height="37">
														<td class="pClothBs"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','mdseCd_A')" ezfhyo="A">Item Code</a><img id="sortIMG.mdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','serNum_A')" ezfhyo="A">Serial# ID</a><img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','svcMachMstrStsDescTxt_A')" ezfhyo="A">IB Status</a><img id="sortIMG.svcMachMstrStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','svcModProcStsDescTxt_A')" ezfhyo="A">Mods<br>Status</a><img id="sortIMG.svcModProcStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','svcTaskNum_A')" ezfhyo="A">Task#</a><img id="sortIMG.svcTaskNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','svcTaskCloDt_A')" ezfhyo="A">Completion Date</a><img id="sortIMG.svcTaskCloDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','svcModOptCd_A')" ezfhyo="A">Option</a><img id="sortIMG.svcModOptCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','svcModOptDt_A')" ezfhyo="A">Option Date</a><img id="sortIMG.svcModOptDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','svcModNoteTxt_A')" ezfhyo="A">Notes</a><img id="sortIMG.svcModNoteTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													</tr>
												</table>
											</div>
											<%-- ########## DETAIL ########## --%>
											<div id="Detail" style="word-break:break-all; overflow-y:scroll; height:350; overflow-x:hidden; width:1077;">
												<table id="A" border="1" cellpadding="1" cellspacing="0" align="left">
													<col width="20" align="left">
													<col width="120" align="left">
													<col width="120" align="left">
													<col width="100" align="left">
													<col width="60" align="left">
													<col width="80" align="left">
													<col width="100" align="left">
													<col width="75" align="left">
													<col width="110" align="left">
													<col width="234" align="left">
													<ezf:row ezfHyo="A">
													<tr height="28">
														<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="svcMachMstrStsDescTxt_A" ezfName="svcMachMstrStsDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="svcModProcStsDescTxt_A" ezfName="svcModProcStsDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="svcTaskNum_A" ezfName="svcTaskNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="svcTaskCloDt_A" ezfName="svcTaskCloDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td>
															<ezf:select name="svcModOptCd_A" ezfName="svcModOptCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcModOptCd_AO" ezfDispName="svcModOptDescTxt_AO" otherAttr=" style=\"width:70\""/>
														</td>
														<td><ezf:inputText name="svcModOptDt_A" ezfName="svcModOptDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('svcModOptDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
														<td><ezf:inputText name="svcModNoteTxt_A" ezfName="svcModNoteTxt_A" value="123456789012345678901234567890123456789X" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"32\""/></td>
                                                        <td class="pAuditInfo">
                                                            <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
                                                            <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
                                                            <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
                                                            <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
                                                            <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
                                                        </td>
													</tr>
													</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<table border="0" width="93%" align="center">
									<col width="" align="" valign="top">
									<col width="" align="" valign="top">
									<col width="" align="" valign="top">
									<col width="" align="" valign="bottom">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<tr>
													<td >
														<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn7" otherAttr=" id=\"btnSelectAll\""/>
													</td>
													<td>&nbsp;</td>
													<td>
														<ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn7" otherAttr=" id=\"btnUnselectAll\""/>
													</td>
													<td>&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
