<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180731114919 --%>
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
			<input type="hidden" name="pageID" value="NSAL1310Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Default Coverage T&C Setup">

<%@ page import="business.servlet.NSAL1310.NSAL1310BMsg" %>
<%@ page import="business.servlet.NSAL1310.NSAL1310_ABMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP" %>
<%  NSAL1310BMsg bMsg = (NSAL1310BMsg)databean.getEZDBMsg(); %>

<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>

<%-- ######################################## HEADER ######################################## --%>

				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Col Search</a></li>
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
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<%-- ###TAB - BODY --%>

				<div class="pTab_BODY">
					<table height="550">
						<tr valign="top">
							<td>
								<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed; margin-top:10px; margin-left:10px; margin-bottom:10px;">
									<col width="100">
									<col width="350">
									<col width="20">
									<col width="70">
									<col width="100">
									<col width="20">
									<col>
									<tr>
										<td class="stab">Coverage Template</td>
										<td>
											<ezf:select name="svcCovTmplTpCd_SV" ezfName="svcCovTmplTpCd_SV" ezfBlank="1" ezfCodeName="svcCovTmplTpCd_CD" ezfDispName="svcCovTmplTpDescTxt_SC" otherAttr=" style=\"width:265px;\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									<tr>
										<td class="stab">T&C Attrb(*)</td>
										<td><ezf:inputText name="svcTermAttrbDescTxt_H" ezfName="svcTermAttrbDescTxt_H" value="Canon Standard QBR Reporting Required" otherAttr=" size=\"37\" maxlength=\"50\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Active Only</td>
										<td><ezf:inputCheckBox name="xxChkBox_H" ezfName="xxChkBox_H" value="Y" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>

<%-- ######################################## DETAIL ######################################## --%>

								<table width="1100" border="0">
									<col width="70">
									<col width="100">
									<col width="300">
									<col>
									<tr>
										<td><ezf:inputButton name="AddLine" value="Add line" htmlClass="pBtn6" /></td>
										<td><ezf:inputButton name="DeleteLine" value="Delete line" htmlClass="pBtn6" /></td>
										<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
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
								<table width"1100" border="0">
									<tr>
										<td>
											<div id="parentBoxA">
												<div style="float:left; display:block"><!-- left table -->
													<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
													<div id="leftTbl" style="float:left; display:block; height:430px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
												</div><!-- left table -->
												<div style="float:left"><!-- right table -->
													<div id="rightTblHead" style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;" >
														<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" style="margin-right:20px">
															<col width="30"  align="center">
															<col width="270" align="center">
															<col width="320" align="center">
															<col width="240" align="center">
															<col width="110" align="center">
															<col width="110" align="center">
															<col width="110" align="center">
															<col width="110" align="center">
															<col width="110" align="center">
															<col width="110" align="center">
															<col width="120" align="center">
															<col width="120" align="center">
															<tr height="32">
																<td class="pClothBs colFix" id="A3H0"></td>
																<td class="pClothBs " id="AH1">Coverage Template</td>
																<td class="pClothBs " id="AH2">T&C Attrb</td>
																<td class="pClothBs " id="AH3">Default Value</td>
																<td class="pClothBs " id="AH4">Attrb Sort Num</td>
																<td class="pClothBs " id="AH5">Cond Category</td>
																<td class="pClothBs " id="AH6">Attrb Updatable</td>
																<td class="pClothBs " id="AH7">Contract Level</td>
																<td class="pClothBs " id="AH8">Machine Level</td>
																<td class="pClothBs " id="AH9">Active Flag</td>
																<td class="pClothBs " id="AH10">Effective From Date</td>
																<td class="pClothBs " id="AH11">Effective Thru Date</td>
															</tr>
														</table>
													</div>
													<div id="rightTbl" style="width:1118px; height:430px; display:block; overflow:scroll; margin:0px; padding:0px;" >
														<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A">
															<col width="30"  align="center">
															<col width="270" align="left">
															<col width="320" align="center">
															<col width="240" align="left">
															<col width="110" align="right">
															<col width="110" align="left">
															<col width="110" align="center">
															<col width="110" align="center">
															<col width="110" align="center">
															<col width="110" align="center">
															<col width="120" align="center">
															<col width="120" align="center">
															<% int idx = 0; %>
															<ezf:row ezfHyo="A">
															<tr height="25px" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
																<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></td>
																<td>
																	<ezf:select name="svcCovTmplTpCd_A1" ezfName="svcCovTmplTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="svcCovTmplTpCd_CD" ezfDispName="svcCovTmplTpDescTxt_SC" otherAttr=" style=\"width:265px;\""/>
																</td>
																<td>
																	<ezf:inputText name="svcTermAttrbDescTxt_A1" ezfName="svcTermAttrbDescTxt_A1" value="After Hours Billing Rate" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTermAttrbDescTxt_A1#{EZF_ROW_NUMBER}\" size=\"37\" maxlength=\"50\""/>
																	<ezf:inputButton name="OpenWin_CondAttrb" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
																</td>
																<td>
																<% if (SVC_TERM_ATTRB_DATA_TP.TEXTBOX.equals(bMsg.A.no(idx).svcTermDataTpCd_A1.getValue())) { %>
																	<ezf:inputText name="svcTermAttrbDefValTxt_A1" ezfName="svcTermAttrbDefValTxt_A1" value="Text" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"32\" maxlength=\"30\""/>
																	<ezf:inputHidden name="condValNum_A1" ezfName="condValNum_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="xxTrxDt_A1" ezfName="xxTrxDt_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_D1" ezfName="svcTermCondDataValCd_D1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_L1" ezfName="svcTermCondDataValCd_L1" ezfHyo="A" ezfArrayNo="${idx}" />
																<% } else if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(bMsg.A.no(idx).svcTermDataTpCd_A1.getValue())) { %>
																	<ezf:select name="svcTermCondDataValCd_D1" ezfName="svcTermCondDataValCd_D1" ezfHyo="A" ezfArrayNo="${idx}" ezfCodeName="svcTermCondDataValCd_DC" ezfDispName="svcTermCondDataDispTxt_DS" ezfCodeDispHyo="A" otherAttr=" style=\"width:230px;\""/>
																	<ezf:inputHidden name="svcTermAttrbDefValTxt_A1" ezfName="svcTermAttrbDefValTxt_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="condValNum_A1" ezfName="condValNum_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="xxTrxDt_A1" ezfName="xxTrxDt_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_L1" ezfName="svcTermCondDataValCd_L1" ezfHyo="A" ezfArrayNo="${idx}" />
																<% } else if (SVC_TERM_ATTRB_DATA_TP.NUMBER.equals(bMsg.A.no(idx).svcTermDataTpCd_A1.getValue())) { %>
																	<ezf:inputText name="condValNum_A1" ezfName="condValNum_A1" value="123.123" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"32\" maxlength=\"30\" style=\"text-align:right;\""/>
																	<ezf:inputHidden name="svcTermAttrbDefValTxt_A1" ezfName="svcTermAttrbDefValTxt_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="xxTrxDt_A1" ezfName="xxTrxDt_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_D1" ezfName="svcTermCondDataValCd_D1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_L1" ezfName="svcTermCondDataValCd_L1" ezfHyo="A" ezfArrayNo="${idx}" />
																<% } else if (SVC_TERM_ATTRB_DATA_TP.DATE.equals(bMsg.A.no(idx).svcTermDataTpCd_A1.getValue())) { %>
																	<ezf:inputText name="xxTrxDt_A1" ezfName="xxTrxDt_A1" value="10/31/2016" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxTrxDt_A1', 4, '{EZF_ROW_NUMBER}');" ezfname="xxTrxDt_A1" ezfhyo="A" >
																	<ezf:inputHidden name="svcTermAttrbDefValTxt_A1" ezfName="svcTermAttrbDefValTxt_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="condValNum_A1" ezfName="condValNum_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_D1" ezfName="svcTermCondDataValCd_D1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_L1" ezfName="svcTermCondDataValCd_L1" ezfHyo="A" ezfArrayNo="${idx}" />
																<% } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(bMsg.A.no(idx).svcTermDataTpCd_A1.getValue())) { %>
																	<ezf:select name="svcTermCondDataValCd_L1" ezfName="svcTermCondDataValCd_L1" ezfHyo="A" ezfArrayNo="${idx}" ezfCodeName="svcTermCondDataValCd_LC" ezfDispName="svcTermCondDataDispTxt_LS" ezfCodeDispHyo="A" otherAttr=" style=\"width:230px;\""/>
																	<ezf:inputHidden name="svcTermAttrbDefValTxt_A1" ezfName="svcTermAttrbDefValTxt_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="condValNum_A1" ezfName="condValNum_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="xxTrxDt_A1" ezfName="xxTrxDt_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_D1" ezfName="svcTermCondDataValCd_D1" ezfHyo="A" ezfArrayNo="${idx}" />
																<% } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(bMsg.A.no(idx).svcTermDataTpCd_A1.getValue())) { %>
																	<ezf:inputText name="svcTermAttrbDefValTxt_A1" ezfName="svcTermAttrbDefValTxt_A1" value="Text" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"27\" maxlength=\"30\""/>
																	<ezf:inputButton name="OpenWin_DefValue" value="..." ezfHyo="A" ezfArrayNo="${idx}" htmlClass="pBtn0" />
																	<ezf:inputHidden name="condValNum_A1" ezfName="condValNum_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="xxTrxDt_A1" ezfName="xxTrxDt_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_D1" ezfName="svcTermCondDataValCd_D1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_L1" ezfName="svcTermCondDataValCd_L1" ezfHyo="A" ezfArrayNo="${idx}" />
																<% } else if (SVC_TERM_ATTRB_DATA_TP.TEXT_NUMERIC.equals(bMsg.A.no(idx).svcTermDataTpCd_A1.getValue())) { %>
																	<ezf:inputText name="svcTermAttrbDefValTxt_A1" ezfName="svcTermAttrbDefValTxt_A1" value="Text" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"32\" maxlength=\"30\""/>
																	<ezf:inputHidden name="condValNum_A1" ezfName="condValNum_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="xxTrxDt_A1" ezfName="xxTrxDt_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_D1" ezfName="svcTermCondDataValCd_D1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_L1" ezfName="svcTermCondDataValCd_L1" ezfHyo="A" ezfArrayNo="${idx}" />
																<% } else { %>
																	<ezf:inputHidden name="svcTermAttrbDefValTxt_A1" ezfName="svcTermAttrbDefValTxt_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="condValNum_A1" ezfName="condValNum_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="xxTrxDt_A1" ezfName="xxTrxDt_A1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_D1" ezfName="svcTermCondDataValCd_D1" ezfHyo="A" ezfArrayNo="${idx}" />
																	<ezf:inputHidden name="svcTermCondDataValCd_L1" ezfName="svcTermCondDataValCd_L1" ezfHyo="A" ezfArrayNo="${idx}" />
																<% } %>
																</td>
																<td><ezf:inputText name="svcTermAttrbSortNum_A1" ezfName="svcTermAttrbSortNum_A1" value="999" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTermAttrbSortNum_A1#{EZF_ROW_NUMBER}\" size=\"3\" maxlength=\"3\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="svcTermCondCatgDescTxt_A1" ezfName="svcTermCondCatgDescTxt_A1" value="Non-Standard" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTermCondCatgDescTxt_A1#{EZF_ROW_NUMBER}\" size=\"13\" maxlength=\"13\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
																<td><ezf:inputCheckBox name="attrbUpdAvalFlg_A1" ezfName="attrbUpdAvalFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"attrbUpdAvalFlg_A1#{EZF_ROW_NUMBER}\""/></td>
																<td><ezf:inputCheckBox name="asgContrLvlFlg_A1" ezfName="asgContrLvlFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"asgContrLvlFlg_A1#{EZF_ROW_NUMBER}\""/></td>
																<td><ezf:inputCheckBox name="asgMachLvlFlg_A1" ezfName="asgMachLvlFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"asgMachLvlFlg_A1#{EZF_ROW_NUMBER}\""/></td>
																<td><ezf:inputCheckBox name="covTermCondActvFlg_A1" ezfName="covTermCondActvFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"covTermCondActvFlg_A1#{EZF_ROW_NUMBER}\""/></td>
																<td><ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" value="10/27/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"effFromDt_A1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4, '{EZF_ROW_NUMBER}');" ezfname="effFromDt_A1" ezfhyo="A" ></td>
																<td><ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" value="10/27/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"effThruDt_A1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4, '{EZF_ROW_NUMBER}');" ezfname="effThruDt_A1" ezfhyo="A" ></td>
																<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																	<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																	<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																	<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																	<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																</td>
															</tr>
															<% idx++; %>
															</ezf:row>
															<ezf:skip>
															<tr height="25px" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
																<td><input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A"></td>
																<td>
																	<select style="width:265px;" name="scvCovTmplTpCd_AV" ezfname="scvCovTmplTpCd_AV" ezflist="scvCovTmplTpCd_AC,scvCovTmplTpDescTxt_AS,99, ,blank">
																		<option value=""></option>
																		<option value="00">00:AFTER HOUR - CLC</option>
																		<option value="01">01:AFTER HOUR - FAX/MISC</option>
																		<option value="19" selected>19:MPS Laser Plus - Parts Included</option>
																	</select>
																</td>
																<td valing="top">
																	<input type="text" id="svcTermAttrbDescTxt_A1#{EZF_ROW_NUMBER}" name="svcTermAttrbDescTxt_A1" ezfname="svcTermAttrbDescTxt_A1" size="37" maxlength="50" value="Canon Standard QBR Reporting Required" >
																	<input type="button" class="pBtn0" value="..." name="OpenWin_TCAttr" onclick="sendServer(this)"  ezfArrayNo="{EZF_ROW_NUMBER}" tabindex="-1" ezfhyo="A">
																</td>
																<td>
																	<select style="width:215px;" name="svcTermAttrbDefValTxt_A1" ezfname="svcTermAttrbDefValTxt_A1" ezflist="scvCovTmplTpCd_AC,scvCovTmplTpDescTxt_AS,99, ,blank">
																		<option value=""></option>
																		<option value="01">Custom</option>
																		<option value="02">Format</option>
																		<option value="03">None</option>
																		<option value="04">Replace</option>
																		<option value="05">Secure Erase</option>
																		<option value="06" selected>Secure Erase (3pass)</option>
																		<option value="07">Secure Erase (7pass)</option>
																	</select>
																</td>
																<td><input type="text" id="svcTermAttrbSortNum_A1#{EZF_ROW_NUMBER}"    name="svcTermAttrbSortNum_A1"    ezfname="svcTermAttrbSortNum_A1"    ezfHyo="A" ezfArrayNo="0" size="3"  maxlength="3"  style="border:none; background-color:transparent;" tabindex="-1" readonly value="999" /></td>
																<td><input type="text" id="svcTermCondCatgDescTxt_A1#{EZF_ROW_NUMBER}" name="svcTermCondCatgDescTxt_A1" ezfname="svcTermCondCatgDescTxt_A1" ezfHyo="A" ezfArrayNo="0" size="13" maxlength="13" style="border:none; background-color:transparent;" tabindex="-1" readonly value="Non-Standard" /></td>
																<td><input type="checkbox" id="xxChkBox_A1#{EZF_ROW_NUMBER}" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" checked></td>
																<td><input type="checkbox" id="xxChkBox_A2#{EZF_ROW_NUMBER}" value="Y" name="xxChkBox_A2" ezfname="xxChkBox_A2" checked></td>
																<td><input type="checkbox" id="xxChkBox_A3#{EZF_ROW_NUMBER}" value="Y" name="xxChkBox_A3" ezfname="xxChkBox_A3" checked></td>
																<td><input type="checkbox" id="xxChkBox_A4#{EZF_ROW_NUMBER}" value="Y" name="xxChkBox_A4" ezfname="xxChkBox_A4" checked></td>
																<td><input type="text" id="effFromDt_A1#{EZF_ROW_NUMBER}"              name="effFromDt_A1"              ezfname="effFromDt_A1"              ezfhyo="A" ezfArrayNo="0" size="10" maxlength="10" value="10/27/2016" ><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4, '{EZF_ROW_NUMBER}');" ezfname="effFromDt_A1" ezfhyo="A" ></td>
																<td><input type="text" id="effThruDt_A1#{EZF_ROW_NUMBER}"              name="effFromDt_A1"              ezfname="effFromDt_A1"              ezfhyo="A" ezfArrayNo="0" size="10" maxlength="10" value="10/27/2016" ><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effToDt_A1'  , 4, '{EZF_ROW_NUMBER}');" ezfname="effToDt_A1"   ezfhyo="A" ></td>
															</tr>
															</ezf:skip>
														</table>
													</div>
												</div>
											</div>
										</td>
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

<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0,  false, false);
</script>



<%-- **** Task specific area ends here **** --%>
