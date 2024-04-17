<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161108133735 --%>
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
			<input type="hidden" name="pageID" value="NMAL7280Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Pricing Rule Conditon Group">

			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<%-- ###TAB - HEAD --%>
							<div class="pTab_BODY_In">
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign="top">
											<table cellpadding="0" border="0">
												<col align="left" width="100">
												<col align="left" width="540">
												<col align="left" width="100">
												<col align="left" width="110">
												<col align="center" width="85">
												<col align="left" width="150">
												<tr>
													<td class="stab">Rule Name</td>
													<td ><ezf:inputText name="prcRuleNm" ezfName="prcRuleNm" value="LFS LIFTGATE FEE" otherAttr=" size=\"50\" ezftoupper=\"\""/></td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td class="stab">Rule ID</td>
													<td ><ezf:inputText name="prcRuleHdrPk" ezfName="prcRuleHdrPk" value="1" otherAttr=" size=\"10\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>

								<hr style="height: 0px;" cellpadding="0">

<%--------------------------------%>
<%-- Detail						--%>
<%--------------------------------%>

								<table>
									<tr>
										<td align="top" width="1130">
											<table border="0" width="600">
												<tr>
													<td>
														<ezf:inputButton name="InsertRow_CondGrp" value="Insert Row" htmlClass="pBtn6" />
														<ezf:inputButton name="DeleteRow_CondGrp" value="Delete Row" htmlClass="pBtn6" />
													</td>
												</tr>
											</table>
											<%-- ### MEISAI - LEFT TBL - TOP --%>
											<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none; width:100; height:34; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" width="100" height="34">
													<col align="center" width="30">
													<col align="center" width="70">
													<tr>
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs">Condition<br>Group</td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - Right TBL - TOP --%>
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:1005; height:34; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="1885" height="34">
													<col align="center" width="75">
													<col align="center" width="65">
													<col align="center" width="75">
													<col align="center" width="65">
													<col align="center" width="75">
													<col align="center" width="65">
													<col align="center" width="75">
													<col align="center" width="65">
													<col align="center" width="75">
													<col align="center" width="65">
													<col align="center" width="75">
													<col align="center" width="65">
													<col align="center" width="75">
													<col align="center" width="65">
													<col align="center" width="75">
													<col align="center" width="65">
													<col align="center" width="75">
													<col align="center" width="65">
													<col align="center" width="75">
													<col align="center" width="65">
													<col align="center" width="75">
													<col align="center" width="65">
													<col align="center" width="75">
													<col align="center" width="120">
													<col align="center" width="120">
													<tr height="18">
														<td class="pClothBs">Condition#</td>
														<td class="pClothBs">Operator</td>
														<td class="pClothBs">Condition#</td>
														<td class="pClothBs">Operator</td>
														<td class="pClothBs">Condition#</td>
														<td class="pClothBs">Operator</td>
														<td class="pClothBs">Condition#</td>
														<td class="pClothBs">Operator</td>
														<td class="pClothBs">Condition#</td>
														<td class="pClothBs">Operator</td>
														<td class="pClothBs">Condition#</td>
														<td class="pClothBs">Operator</td>
														<td class="pClothBs">Condition#</td>
														<td class="pClothBs">Operator</td>
														<td class="pClothBs">Condition#</td>
														<td class="pClothBs">Operator</td>
														<td class="pClothBs">Condition#</td>
														<td class="pClothBs">Operator</td>
														<td class="pClothBs">Condition#</td>
														<td class="pClothBs">Operator</td>
														<td class="pClothBs">Condition#</td>
														<td class="pClothBs">Operator</td>
														<td class="pClothBs">Condition#</td>
														<td class="pClothBs">Effective Date From</td>
														<td class="pClothBs">Effective Date To</td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - LEFT TBL - BOTTOM --%>
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:451; width:100; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL' ))" >
												<table border="1" cellpadding="1" cellspacing="0" width="100" id="A1">
													<col align="center" width="25">
													<col align="center" width="75">
													<ezf:row ezfHyo="A">
														<tr height="28" id="B_leftTBLRow#{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="prcRuleCondGrpCd_A" ezfName="prcRuleCondGrpCd_A" value="A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\""/></td>
														</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</table>
											</div>
											<%-- ### MEISAI - RIGHT TBL - BOTTOM --%>
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:468; width:1022; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="1885" id="A2">
													<col align="center" width="77">
													<col align="center" width="65">
													<col align="center" width="77">
													<col align="center" width="65">
													<col align="center" width="77">
													<col align="center" width="65">
													<col align="center" width="77">
													<col align="center" width="65">
													<col align="center" width="77">
													<col align="center" width="65">
													<col align="center" width="77">
													<col align="center" width="65">
													<col align="center" width="77">
													<col align="center" width="65">
													<col align="center" width="77">
													<col align="center" width="65">
													<col align="center" width="77">
													<col align="center" width="65">
													<col align="center" width="77">
													<col align="center" width="65">
													<col align="center" width="77">
													<col align="center" width="65">
													<col align="center" width="77">
													<col align="center" width="115">
													<col align="center" width="115">
													<ezf:row ezfHyo="A">
														<tr height="28" id="B_rightTBLRow#{EZF_ROW_NUMBER}">
															<td><ezf:inputText name="prcRuleCondNum_A1" ezfName="prcRuleCondNum_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align: right;\""/></td>
															<td>
																<ezf:select name="prcRuleOpTpCd_A1" ezfName="prcRuleOpTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcRuleOpTpCd_P" ezfDispName="prcRuleOpTpDescTxt_P" otherEvent1=" onchange=\"sendServer('OnChange_OpTp', '{EZF_ROW_NUMBER}')\"" />
															</td>
															<td><ezf:inputText name="prcRuleCondNum_A2" ezfName="prcRuleCondNum_A2" value="2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align: right;\""/></td>
															<td>
																<ezf:select name="prcRuleOpTpCd_A2" ezfName="prcRuleOpTpCd_A2" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcRuleOpTpCd_P" ezfDispName="prcRuleOpTpDescTxt_P" />
															</td>
															<td><ezf:inputText name="prcRuleCondNum_A3" ezfName="prcRuleCondNum_A3" value="3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align: right;\""/></td>
															<td>
																<ezf:select name="prcRuleOpTpCd_A3" ezfName="prcRuleOpTpCd_A3" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcRuleOpTpCd_P" ezfDispName="prcRuleOpTpDescTxt_P" />
															</td>
															<td><ezf:inputText name="prcRuleCondNum_A4" ezfName="prcRuleCondNum_A4" value="4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align: right;\""/></td>
															<td>
																<ezf:select name="prcRuleOpTpCd_A4" ezfName="prcRuleOpTpCd_A4" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcRuleOpTpCd_P" ezfDispName="prcRuleOpTpDescTxt_P" />
															</td>
															<td><ezf:inputText name="prcRuleCondNum_A5" ezfName="prcRuleCondNum_A5" value="5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align: right;\""/></td>
															<td>
																<ezf:select name="prcRuleOpTpCd_A5" ezfName="prcRuleOpTpCd_A5" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcRuleOpTpCd_P" ezfDispName="prcRuleOpTpDescTxt_P" />
															</td>
															<td><ezf:inputText name="prcRuleCondNum_A6" ezfName="prcRuleCondNum_A6" value="6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align: right;\""/></td>
															<td>
																<ezf:select name="prcRuleOpTpCd_A6" ezfName="prcRuleOpTpCd_A6" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcRuleOpTpCd_P" ezfDispName="prcRuleOpTpDescTxt_P" />
															</td>
															<td><ezf:inputText name="prcRuleCondNum_A7" ezfName="prcRuleCondNum_A7" value="7" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align: right;\""/></td>
															<td>
																<ezf:select name="prcRuleOpTpCd＿A7" ezfName="prcRuleOpTpCd_A7" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcRuleOpTpCd_P" ezfDispName="prcRuleOpTpDescTxt_P" />
															</td>
															<td><ezf:inputText name="prcRuleCondNum_A8" ezfName="prcRuleCondNum_A8" value="8" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align: right;\""/></td>
															<td>
																<ezf:select name="prcRuleOpTpCd_A8" ezfName="prcRuleOpTpCd_A8" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcRuleOpTpCd_P" ezfDispName="prcRuleOpTpDescTxt_P" />
															</td>
															<td><ezf:inputText name="prcRuleCondNum_A9" ezfName="prcRuleCondNum_A9" value="9" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align: right;\""/></td>
															<td>
																<ezf:select name="prcRuleOpTpCd_A9" ezfName="prcRuleOpTpCd_A9" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcRuleOpTpCd_P" ezfDispName="prcRuleOpTpDescTxt_P" />
															</td>
															<td><ezf:inputText name="prcRuleCondNum_AA" ezfName="prcRuleCondNum_AA" value="10" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align: right;\""/></td>
															<td>
																<ezf:select name="prcRuleOpTpCd_AA" ezfName="prcRuleOpTpCd_AA" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcRuleOpTpCd_P" ezfDispName="prcRuleOpTpDescTxt_P" />
															</td>
															<td><ezf:inputText name="prcRuleCondNum_AB" ezfName="prcRuleCondNum_AB" value="11" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align: right;\""/></td>
															<td>
																<ezf:select name="prcRuleOpTpCd_AB" ezfName="prcRuleOpTpCd_AB" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcRuleOpTpCd_P" ezfDispName="prcRuleOpTpDescTxt_P" />
															</td>
															<td><ezf:inputText name="prcRuleCondNum_AC" ezfName="prcRuleCondNum_AC" value="12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align: right;\""/></td>
															<td>
																<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td>
																<ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
			                                                <td class="pAuditInfo">
			                                                    <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" />
			                                                    <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" />
			                                                    <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" />
			                                                    <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" />
			                                                    <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" />
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
							</div>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
