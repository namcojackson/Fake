<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170801155653 --%>
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
			<input type="hidden" name="pageID" value="NSAL0360Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Charge View Pricing - Usage">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="View Prc Base" class="pTab_ON"><a href="#">View Prc Base</a></li>
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
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
						<col width="180">
						<col width="330">
						<col width="320">
						<col>
						<tr>
							<td valign="top">
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
									<col width="60">
									<col width="120">
									<tr height="21">
										<td class="stab">Contract#</td>
										<td><ezf:inputText name="dsContrNum_H1" ezfName="dsContrNum_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"15\" maxlength=\"30\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
									<tr height="21">
										<td class="stab">Serial#</td>
										<td><ezf:inputText name="serNum_H1" ezfName="serNum_H1" value="WWWWWWWWW1WWWWW" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
									<tr height="21">
										<td><br/></td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
									<col width="80">
									<col width="250">
									<tr height="21">
										<td class="stab">Effective Date</td>
										<td>
											<ezf:inputText name="contrEffFromDt_H1" ezfName="contrEffFromDt_H1" value="12/31/2015" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/>
											-
											<ezf:inputText name="contrEffThruDt_H1" ezfName="contrEffThruDt_H1" value="12/31/2015" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/>
										</td>
									</tr>
									<tr height="21">
										<td class="stab">Billing Timing</td>
										<td><ezf:select name="mtrBllgTmgCd_H1" ezfName="mtrBllgTmgCd_H1" ezfBlank="1" ezfCodeName="bllgTmgTpCd_H1" ezfDispName="bllgTmgTpNm_H2" /></td>
									</tr>
									<tr height="21">
										<td class="stab">Period End Date</td>
										<td>
											<ezf:select name="mtrDplyPerEndDay_H1" ezfName="mtrDplyPerEndDay_H1" ezfCodeName="mtrDplyPerEndDay_MC" ezfDispName="xxEdtDescTxt_MC" otherAttr=" style=\"width:90\""/>
										</td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
									<col width="70">
									<col width="250">
									<tr height="21">
										<td class="stab">Billed Upto</td>
										<td><ezf:inputText name="mtrBllgLastBllgDt_H1" ezfName="mtrBllgLastBllgDt_H1" value="12/31/2015" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
									<tr height="21">
										<td><br/></td>
										<td><br/></td>
									</tr>
									<tr height="21">
										<td class="stab">Invoice Date</td>
										<td>
											<ezf:select name="mtrBllgDay_H1" ezfName="mtrBllgDay_H1" ezfCodeName="mtrBllgDay_MB" ezfDispName="xxEdtDescTxt_MB" otherAttr=" style=\"width:90\""/>
										</td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
									<tr height="21">
										<td><br/></td>
									</tr>
									<tr height="21">
										<td><br/></td>
									</tr>
									<tr height="21">
										<td>
											<!-- pagenation -->
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<hr />
<%-- #################################################### DETAIL ################################################### --%>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="business.servlet.NSAL0360.NSAL0360BMsg" %>
<%@ page import="business.servlet.NSAL0360.NSAL0360_ABMsg" %>
<%@ page import="business.servlet.NSAL0360.NSAL0360_BBMsg" %>
<%@ page import="business.servlet.NSAL0360.NSAL0360_CBMsg" %>
<%@ page import="business.servlet.NSAL0360.NSAL0360_DBMsg" %>
<%@ page import="business.servlet.NSAL0360.NSAL0360_EBMsg" %>
<%@ page import="business.servlet.NSAL0360.NSAL0360_FBMsg" %>
<%@ page import="business.servlet.NSAL0360.NSAL0360_GBMsg" %>
<%@ page import="business.servlet.NSAL0360.NSAL0360_HBMsg" %>
<%@ page import="business.servlet.NSAL0360.NSAL0360_IBMsg" %>
<%@ page import="business.servlet.NSAL0360.NSAL0360_JBMsg" %>
<%@ page import="business.servlet.NSAL0360.constant.NSAL0360Constant" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>

<%  NSAL0360BMsg bMsg = (NSAL0360BMsg)databean.getEZDBMsg(); %>
					<div id="tableTop" style="overflow-y:scroll; height:390; overflow-x:hidden; width:100%; float:left;">
						<% int i = 0; %>
						<% BigDecimal targetPeSqNum = null; %>
						<% String targetSqNum = null; %>
						<% String visibleStyle = null; %>
						<% String buttonDisable = "disabled"; %>
						<% if (ZYPCommonFunc.hasValue(bMsg.xxModeCd_HD) && NSAL0360Constant.EDIT_MODE.equals(bMsg.xxModeCd_HD.getValue())) { %>
						<%     buttonDisable = ""; %>
						<% } %>
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_A)) { %>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="left" width="40">
												<col align="left" width="200">
												<col>
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_A" ezfName="mtrLbNm_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><br /></td>
													<td><br /></td>
													<td><br /></td>
													<td><input type="button" class="pBtn6" value="Select All" name="SelectAll" onclick="execTableEvent('A', 'SelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Un Select All" name="UnSelectAll" onclick="execTableEvent('A', 'UnSelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Insert Row" name="InsertRow" onclick="execTableEvent('A', 'InsertRow');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Delete Row" name="DeleteRow" onclick="execTableEvent('A', 'DeleteRow');" <%= buttonDisable %>></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="center" width="25">
													<col align="center" width="50">		<!-- PE Seq# -->
													<col align="center" width="50">		<!-- TS Seq# -->
													<col align="center" width="60">		<!-- Periods -->
													<col align="center" width="105">	<!-- Sched.UOM -->
													<col align="center" width="110">	<!-- Sched.From Date -->
													<col align="center" width="110">	<!-- Sched.Thru Date -->
													<col align="center" width="140">	<!-- Billing Cycle -->
													<col align="center" width="105">	<!-- Allowance/Period -->
													<col align="center" width="130">	<!-- Allowance -->
													<col align="center" width="130">	<!-- Price -->
													<col align="center" width="70">		<!-- Currency -->
													<tr>
														<td class="pClothBs"><br /></td>
														<td class="pClothBs">PE Seq#</td>
														<td class="pClothBs">TS Seq#</td>
														<td class="pClothBs">Periods</td>
														<td class="pClothBs">Sched.UOM</td>
														<td class="pClothBs">Sched.From Date</td>
														<td class="pClothBs">Sched.Thru Date</td>
														<td class="pClothBs">Billing Cycle</td>
														<td class="pClothBs">Allowance/Period</td>
														<td class="pClothBs">Allowance</td>
														<td class="pClothBs">Price</td>
														<td class="pClothBs">Currency</td>
													</tr>
												</table>
											</div>
											<div id="LeftTable_A" style="overflow-y:scroll; height:149; overflow-x:hidden; width:1105; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
														<col align="center" width="25">
														<col align="center" width="50">		<!-- PE Seq# -->
														<col align="center" width="50">		<!-- TS Seq# -->
														<col align="center" width="60">		<!-- Periods -->
														<col align="center" width="105">	<!-- Sched.UOM -->
														<col align="center" width="110">	<!-- Sched.From Date -->
														<col align="center" width="110">	<!-- Sched.Thru Date -->
														<col align="center" width="140">	<!-- Billing Cycle -->
														<col align="center" width="105">	<!-- Allowance/Period -->
														<col align="center" width="130">	<!-- Allowance -->
														<col align="center" width="130">	<!-- Price -->
														<col align="center" width="70">		<!-- Currency -->
										<ezf:row ezfHyo="A">
										<% 
											NSAL0360_ABMsg abMsg = bMsg.A.no(i);
											if (i == 0 || !targetSqNum.equals(abMsg.dsContrBllgSchdSqNum_A1.getValue()) || targetPeSqNum.compareTo(abMsg.dsContrPrcEffSqNum_A1.getValue()) != 0) {
												targetSqNum = abMsg.dsContrBllgSchdSqNum_A1.getValue();
												targetPeSqNum = abMsg.dsContrPrcEffSqNum_A1.getValue();
												visibleStyle = "visible";
											} else { 
												visibleStyle = "hidden";
											}
										%>
														<tr ezfhyo="A" id="id_leftA_row{EZF_ROW_NUMBER}">
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrPrcEffSqNum_A1" ezfName="dsContrPrcEffSqNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrBllgSchdSqNum_A1" ezfName="dsContrBllgSchdSqNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="perSchdNum_A1" ezfName="perSchdNum_A1" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onchange=\"execTableEvent('A','OnChangePeriods','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"4\" maxlength=\"4\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:select name="perBllgCycleCd_A1" ezfName="perBllgCycleCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleUomCd_A1" ezfDispName="bllgCycleUomNm_A2" ezfCodeDispHyo="A" otherEvent1=" onchange=\"execTableEvent('A','OnChangeUOM','{EZF_ROW_NUMBER}');\"" /></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdFromDt_A1" ezfName="bllgSchdFromDt_A1" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('A','OnChangeFromDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdFromDt_A1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdFromDt_A1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdThruDt_A1" ezfName="bllgSchdThruDt_A1" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('A','OnChangeThruDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdThruDt_A1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdThruDt_A1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><ezf:inputText name="bllgCycleDescTxt_A1" ezfName="bllgCycleDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_A1" ezfName="xsMtrCopyQty_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"10\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_AS" ezfName="xsMtrCopyQty_AS" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="xsMtrAmtRate_A1" ezfName="xsMtrAmtRate_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="ccyCd_A1" ezfName="ccyCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
															</td>
														</tr>
											<% i++; %>
										</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
						<% } %>
						
						
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_B)) { %>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="left" width="40">
												<col align="left" width="200">
												<col>
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_B" ezfName="mtrLbNm_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><br /></td>
													<td><br /></td>
													<td><br /></td>
													<td><input type="button" class="pBtn6" value="Select All" name="SelectAll" onclick="execTableEvent('B', 'SelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Un Select All" name="UnSelectAll" onclick="execTableEvent('B', 'UnSelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Insert Row" name="InsertRow" onclick="execTableEvent('B', 'InsertRow');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Delete Row" name="DeleteRow" onclick="execTableEvent('B', 'DeleteRow');" <%= buttonDisable %>></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_B_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="center" width="25">
													<col align="center" width="50">		<!-- PE Seq# -->
													<col align="center" width="50">		<!-- TS Seq# -->
													<col align="center" width="60">		<!-- Periods -->
													<col align="center" width="105">	<!-- Sched.UOM -->
													<col align="center" width="110">	<!-- Sched.From Date -->
													<col align="center" width="110">	<!-- Sched.Thru Date -->
													<col align="center" width="140">	<!-- Billing Cycle -->
													<col align="center" width="105">	<!-- Allowance/Period -->
													<col align="center" width="130">	<!-- Allowance -->
													<col align="center" width="130">	<!-- Price -->
													<col align="center" width="70">		<!-- Currency -->
													<tr>
														<td class="pClothBs"><br /></td>
														<td class="pClothBs">PE Seq#</td>
														<td class="pClothBs">TS Seq#</td>
														<td class="pClothBs">Periods</td>
														<td class="pClothBs">Sched.UOM</td>
														<td class="pClothBs">Sched.From Date</td>
														<td class="pClothBs">Sched.Thru Date</td>
														<td class="pClothBs">Billing Cycle</td>
														<td class="pClothBs">Allowance/Period</td>
														<td class="pClothBs">Allowance</td>
														<td class="pClothBs">Price</td>
														<td class="pClothBs">Currency</td>
													</tr>
												</table>
											</div>
											<div id="LeftTable_B" style="overflow-y:scroll; height:149; overflow-x:hidden; width:1109; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed">
														<col align="center" width="25">
														<col align="center" width="50">		<!-- PE Seq# -->
														<col align="center" width="50">		<!-- TS Seq# -->
														<col align="center" width="60">		<!-- Periods -->
														<col align="center" width="105">	<!-- Sched.UOM -->
														<col align="center" width="110">	<!-- Sched.From Date -->
														<col align="center" width="110">	<!-- Sched.Thru Date -->
														<col align="center" width="140">	<!-- Billing Cycle -->
														<col align="center" width="105">	<!-- Allowance/Period -->
														<col align="center" width="130">	<!-- Allowance -->
														<col align="center" width="130">	<!-- Price -->
														<col align="center" width="70">		<!-- Currency -->
										<% i = 0; %>
										<% targetPeSqNum = null; %>
										<% targetSqNum = null; %>
										<ezf:row ezfHyo="B">
											<% NSAL0360_BBMsg bbMsg = bMsg.B.no(i); %>
											<% 
												if (i == 0 || !targetSqNum.equals(bbMsg.dsContrBllgSchdSqNum_B1.getValue()) || targetPeSqNum.compareTo(bbMsg.dsContrPrcEffSqNum_B1.getValue()) != 0) {
													targetSqNum = bbMsg.dsContrBllgSchdSqNum_B1.getValue();
													targetPeSqNum = bbMsg.dsContrPrcEffSqNum_B1.getValue();
													visibleStyle = "visible";
												} else { 
													visibleStyle = "hidden";
												}
											%>
														<tr ezfhyo="B" id="id_leftB_row{EZF_ROW_NUMBER}">
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B1#{EZF_ROW_NUMBER}\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrPrcEffSqNum_B1" ezfName="dsContrPrcEffSqNum_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrBllgSchdSqNum_B1" ezfName="dsContrBllgSchdSqNum_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="perSchdNum_B1" ezfName="perSchdNum_B1" ezfHyo="B" ezfArrayNo="0" otherEvent1=" onchange=\"execTableEvent('B','OnChangePeriods','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:select name="perBllgCycleCd_B1" ezfName="perBllgCycleCd_B1" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleUomCd_B1" ezfDispName="bllgCycleUomNm_B2" ezfCodeDispHyo="B" otherEvent1=" onchange=\"execTableEvent('B','OnChangeUOM','{EZF_ROW_NUMBER}');\"" /></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdFromDt_B1" ezfName="bllgSchdFromDt_B1" ezfHyo="B" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('B','OnChangeFromDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdFromDt_B1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdFromDt_B1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdThruDt_B1" ezfName="bllgSchdThruDt_B1" ezfHyo="B" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('B','OnChangeThruDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdThruDt_B1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdThruDt_B1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><ezf:inputText name="bllgCycleDescTxt_B1" ezfName="bllgCycleDescTxt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_B1" ezfName="xsMtrCopyQty_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_BS" ezfName="xsMtrCopyQty_BS" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="xsMtrAmtRate_B1" ezfName="xsMtrAmtRate_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="ccyCd_B1" ezfName="ccyCd_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_B1" ezfName="xxRecHistCratTs_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_B1" ezfName="xxRecHistCratByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_B1" ezfName="xxRecHistUpdTs_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_B1" ezfName="xxRecHistUpdByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_B1" ezfName="xxRecHistTblNm_B1" ezfHyo="B" ezfArrayNo="0" />
															</td>
														</tr>
											<% i++; %>
										</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
						<% } %>
						
						
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_C)) { %>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="left" width="40">
												<col align="left" width="200">
												<col>
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_C" ezfName="mtrLbNm_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><br /></td>
													<td><br /></td>
													<td><br /></td>
													<td><input type="button" class="pBtn6" value="Select All" name="SelectAll" onclick="execTableEvent('C', 'SelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Un Select All" name="UnSelectAll" onclick="execTableEvent('C', 'UnSelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Insert Row" name="InsertRow" onclick="execTableEvent('C', 'InsertRow');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Delete Row" name="DeleteRow" onclick="execTableEvent('C', 'DeleteRow');" <%= buttonDisable %>></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_C_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="center" width="25">
													<col align="center" width="50">		<!-- PE Seq# -->
													<col align="center" width="50">		<!-- TS Seq# -->
													<col align="center" width="60">		<!-- Periods -->
													<col align="center" width="105">	<!-- Sched.UOM -->
													<col align="center" width="110">	<!-- Sched.From Date -->
													<col align="center" width="110">	<!-- Sched.Thru Date -->
													<col align="center" width="140">	<!-- Billing Cycle -->
													<col align="center" width="105">	<!-- Allowance/Period -->
													<col align="center" width="130">	<!-- Allowance -->
													<col align="center" width="130">	<!-- Price -->
													<col align="center" width="70">		<!-- Currency -->
													<tr>
														<td class="pClothBs"><br /></td>
														<td class="pClothBs">PE Seq#</td>
														<td class="pClothBs">TS Seq#</td>
														<td class="pClothBs">Periods</td>
														<td class="pClothBs">Sched.UOM</td>
														<td class="pClothBs">Sched.From Date</td>
														<td class="pClothBs">Sched.Thru Date</td>
														<td class="pClothBs">Billing Cycle</td>
														<td class="pClothBs">Allowance/Period</td>
														<td class="pClothBs">Allowance</td>
														<td class="pClothBs">Price</td>
														<td class="pClothBs">Currency</td>
													</tr>
												</table>
											</div>
											<div id="LeftTable_C" style="overflow-y:scroll; height:149; overflow-x:hidden; width:1109; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="C" style="table-layout: fixed">
														<col align="center" width="25">
														<col align="center" width="50">		<!-- PE Seq# -->
														<col align="center" width="50">		<!-- TS Seq# -->
														<col align="center" width="60">		<!-- Periods -->
														<col align="center" width="105">	<!-- Sched.UOM -->
														<col align="center" width="110">	<!-- Sched.From Date -->
														<col align="center" width="110">	<!-- Sched.Thru Date -->
														<col align="center" width="140">	<!-- Billing Cycle -->
														<col align="center" width="105">	<!-- Allowance/Period -->
														<col align="center" width="130">	<!-- Allowance -->
														<col align="center" width="130">	<!-- Price -->
														<col align="center" width="70">		<!-- Currency -->
										<% i = 0; %>
										<% targetPeSqNum = null; %>
										<% targetSqNum = null; %>
										<ezf:row ezfHyo="C">
											<% NSAL0360_CBMsg cbMsg = bMsg.C.no(i); %>
											<% 
												if (i == 0 || !targetSqNum.equals(cbMsg.dsContrBllgSchdSqNum_C1.getValue()) || targetPeSqNum.compareTo(cbMsg.dsContrPrcEffSqNum_C1.getValue()) != 0) {
													targetSqNum = cbMsg.dsContrBllgSchdSqNum_C1.getValue();
													targetPeSqNum = cbMsg.dsContrPrcEffSqNum_C1.getValue();
													visibleStyle = "visible";
												} else { 
													visibleStyle = "hidden";
												}
											%>
														<tr ezfhyo="C" id="id_leftC_row{EZF_ROW_NUMBER}">
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputCheckBox name="xxChkBox_C1" ezfName="xxChkBox_C1" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_C1#{EZF_ROW_NUMBER}\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrPrcEffSqNum_C1" ezfName="dsContrPrcEffSqNum_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrBllgSchdSqNum_C1" ezfName="dsContrBllgSchdSqNum_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="perSchdNum_C1" ezfName="perSchdNum_C1" ezfHyo="C" ezfArrayNo="0" otherEvent1=" onchange=\"execTableEvent('C','OnChangePeriods','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:select name="perBllgCycleCd_C1" ezfName="perBllgCycleCd_C1" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleUomCd_C1" ezfDispName="bllgCycleUomNm_C2" ezfCodeDispHyo="C" otherEvent1=" onchange=\"execTableEvent('C','OnChangeUOM','{EZF_ROW_NUMBER}');\"" /></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdFromDt_C1" ezfName="bllgSchdFromDt_C1" ezfHyo="C" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('C','OnChangeFromDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdFromDt_C1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdFromDt_C1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdThruDt_C1" ezfName="bllgSchdThruDt_C1" ezfHyo="C" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('C','OnChangeThruDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdThruDt_C1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdThruDt_C1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><ezf:inputText name="bllgCycleDescTxt_C1" ezfName="bllgCycleDescTxt_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_C1" ezfName="xsMtrCopyQty_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_CS" ezfName="xsMtrCopyQty_CS" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="xsMtrAmtRate_C1" ezfName="xsMtrAmtRate_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="ccyCd_C1" ezfName="ccyCd_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_C1" ezfName="xxRecHistCratTs_C1" ezfHyo="C" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_C1" ezfName="xxRecHistCratByNm_C1" ezfHyo="C" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_C1" ezfName="xxRecHistUpdTs_C1" ezfHyo="C" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_C1" ezfName="xxRecHistUpdByNm_C1" ezfHyo="C" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_C1" ezfName="xxRecHistTblNm_C1" ezfHyo="C" ezfArrayNo="0" />
															</td>
														</tr>
											<% i++; %>
										</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
						<% } %>
						
						
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_D)) { %>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="left" width="40">
												<col align="left" width="200">
												<col>
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_D" ezfName="mtrLbNm_D" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><br /></td>
													<td><br /></td>
													<td><br /></td>
													<td><input type="button" class="pBtn6" value="Select All" name="SelectAll" onclick="execTableEvent('D', 'SelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Un Select All" name="UnSelectAll" onclick="execTableEvent('D', 'UnSelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Insert Row" name="InsertRow" onclick="execTableEvent('D', 'InsertRow');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Delete Row" name="DeleteRow" onclick="execTableEvent('D', 'DeleteRow');" <%= buttonDisable %>></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_D_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="center" width="25">
													<col align="center" width="50">		<!-- PE Seq# -->
													<col align="center" width="50">		<!-- TS Seq# -->
													<col align="center" width="60">		<!-- Periods -->
													<col align="center" width="105">	<!-- Sched.UOM -->
													<col align="center" width="110">	<!-- Sched.From Date -->
													<col align="center" width="110">	<!-- Sched.Thru Date -->
													<col align="center" width="140">	<!-- Billing Cycle -->
													<col align="center" width="105">	<!-- Allowance/Period -->
													<col align="center" width="130">	<!-- Allowance -->
													<col align="center" width="130">	<!-- Price -->
													<col align="center" width="70">		<!-- Currency -->
													<tr>
														<td class="pClothBs"><br /></td>
														<td class="pClothBs">PE Seq#</td>
														<td class="pClothBs">TS Seq#</td>
														<td class="pClothBs">Periods</td>
														<td class="pClothBs">Sched.UOM</td>
														<td class="pClothBs">Sched.From Date</td>
														<td class="pClothBs">Sched.Thru Date</td>
														<td class="pClothBs">Billing Cycle</td>
														<td class="pClothBs">Allowance/Period</td>
														<td class="pClothBs">Allowance</td>
														<td class="pClothBs">Price</td>
														<td class="pClothBs">Currency</td>
													</tr>
												</table>
											</div>
											<div id="LeftTable_D" style="overflow-y:scroll; height:149; overflow-x:hidden; width:1109; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="D" style="table-layout: fixed">
														<col align="center" width="25">
														<col align="center" width="50">		<!-- PE Seq# -->
														<col align="center" width="50">		<!-- TS Seq# -->
														<col align="center" width="60">		<!-- Periods -->
														<col align="center" width="105">	<!-- Sched.UOM -->
														<col align="center" width="110">	<!-- Sched.From Date -->
														<col align="center" width="110">	<!-- Sched.Thru Date -->
														<col align="center" width="140">	<!-- Billing Cycle -->
														<col align="center" width="105">	<!-- Allowance/Period -->
														<col align="center" width="130">	<!-- Allowance -->
														<col align="center" width="130">	<!-- Price -->
														<col align="center" width="70">		<!-- Currency -->
										<% i = 0; %>
										<% targetPeSqNum = null; %>
										<% targetSqNum = null; %>
										<ezf:row ezfHyo="D">
											<% NSAL0360_DBMsg dbMsg = bMsg.D.no(i); %>
											<% 
												if (i == 0 || !targetSqNum.equals(dbMsg.dsContrBllgSchdSqNum_D1.getValue()) || targetPeSqNum.compareTo(dbMsg.dsContrPrcEffSqNum_D1.getValue()) != 0) {
													targetSqNum = dbMsg.dsContrBllgSchdSqNum_D1.getValue();
													targetPeSqNum = dbMsg.dsContrPrcEffSqNum_D1.getValue();
													visibleStyle = "visible";
												} else { 
													visibleStyle = "hidden";
												}
											%>
														<tr ezfhyo="D" id="id_leftD_row{EZF_ROW_NUMBER}">
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="D" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_D1#{EZF_ROW_NUMBER}\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrPrcEffSqNum_D1" ezfName="dsContrPrcEffSqNum_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrBllgSchdSqNum_D1" ezfName="dsContrBllgSchdSqNum_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="perSchdNum_D1" ezfName="perSchdNum_D1" ezfHyo="D" ezfArrayNo="0" otherEvent1=" onchange=\"execTableEvent('D','OnChangePeriods','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:select name="perBllgCycleCd_D1" ezfName="perBllgCycleCd_D1" ezfHyo="D" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleUomCd_D1" ezfDispName="bllgCycleUomNm_D2" ezfCodeDispHyo="D" otherEvent1=" onchange=\"execTableEvent('D','OnChangeUOM','{EZF_ROW_NUMBER}');\"" /></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdFromDt_D1" ezfName="bllgSchdFromDt_D1" ezfHyo="D" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('D','OnChangeFromDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdFromDt_D1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdFromDt_D1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdThruDt_D1" ezfName="bllgSchdThruDt_D1" ezfHyo="D" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('D','OnChangeThruDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdThruDt_D1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdThruDt_D1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><ezf:inputText name="bllgCycleDescTxt_D1" ezfName="bllgCycleDescTxt_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_D1" ezfName="xsMtrCopyQty_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_DS" ezfName="xsMtrCopyQty_DS" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="xsMtrAmtRate_D1" ezfName="xsMtrAmtRate_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="ccyCd_D1" ezfName="ccyCd_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_D1" ezfName="xxRecHistCratTs_D1" ezfHyo="D" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_D1" ezfName="xxRecHistCratByNm_D1" ezfHyo="D" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_D1" ezfName="xxRecHistUpdTs_D1" ezfHyo="D" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_D1" ezfName="xxRecHistUpdByNm_D1" ezfHyo="D" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_D1" ezfName="xxRecHistTblNm_D1" ezfHyo="D" ezfArrayNo="0" />
															</td>
														</tr>
											<% i++; %>
										</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
						<% } %>
						
						
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_E)) { %>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="left" width="40">
												<col align="left" width="200">
												<col>
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_E" ezfName="mtrLbNm_E" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><br /></td>
													<td><br /></td>
													<td><br /></td>
													<td><input type="button" class="pBtn6" value="Select All" name="SelectAll" onclick="execTableEvent('E', 'SelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Un Select All" name="UnSelectAll" onclick="execTableEvent('E', 'UnSelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Insert Row" name="InsertRow" onclick="execTableEvent('E', 'InsertRow');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Delete Row" name="DeleteRow" onclick="execTableEvent('E', 'DeleteRow');" <%= buttonDisable %>></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_E_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="center" width="25">
													<col align="center" width="50">		<!-- PE Seq# -->
													<col align="center" width="50">		<!-- TS Seq# -->
													<col align="center" width="60">		<!-- Periods -->
													<col align="center" width="105">	<!-- Sched.UOM -->
													<col align="center" width="110">	<!-- Sched.From Date -->
													<col align="center" width="110">	<!-- Sched.Thru Date -->
													<col align="center" width="140">	<!-- Billing Cycle -->
													<col align="center" width="105">	<!-- Allowance/Period -->
													<col align="center" width="130">	<!-- Allowance -->
													<col align="center" width="130">	<!-- Price -->
													<col align="center" width="70">		<!-- Currency -->
													<tr>
														<td class="pClothBs"><br /></td>
														<td class="pClothBs">PE Seq#</td>
														<td class="pClothBs">TS Seq#</td>
														<td class="pClothBs">Periods</td>
														<td class="pClothBs">Sched.UOM</td>
														<td class="pClothBs">Sched.From Date</td>
														<td class="pClothBs">Sched.Thru Date</td>
														<td class="pClothBs">Billing Cycle</td>
														<td class="pClothBs">Allowance/Period</td>
														<td class="pClothBs">Allowance</td>
														<td class="pClothBs">Price</td>
														<td class="pClothBs">Currency</td>
													</tr>
												</table>
											</div>
											<div id="LeftTable_E" style="overflow-y:scroll; height:149; overflow-x:hidden; width:1109; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="E" style="table-layout: fixed">
														<col align="center" width="25">
														<col align="center" width="50">		<!-- PE Seq# -->
														<col align="center" width="50">		<!-- TS Seq# -->
														<col align="center" width="60">		<!-- Periods -->
														<col align="center" width="105">	<!-- Sched.UOM -->
														<col align="center" width="110">	<!-- Sched.From Date -->
														<col align="center" width="110">	<!-- Sched.Thru Date -->
														<col align="center" width="140">	<!-- Billing Cycle -->
														<col align="center" width="105">	<!-- Allowance/Period -->
														<col align="center" width="130">	<!-- Allowance -->
														<col align="center" width="130">	<!-- Price -->
														<col align="center" width="70">		<!-- Currency -->
										<% i = 0; %>
										<% targetPeSqNum = null; %>
										<% targetSqNum = null; %>
										<ezf:row ezfHyo="E">
											<% NSAL0360_EBMsg ebMsg = bMsg.E.no(i); %>
											<% 
												if (i == 0 || !targetSqNum.equals(ebMsg.dsContrBllgSchdSqNum_E1.getValue()) || targetPeSqNum.compareTo(ebMsg.dsContrPrcEffSqNum_E1.getValue()) != 0) {
													targetSqNum = ebMsg.dsContrBllgSchdSqNum_E1.getValue();
													targetPeSqNum = ebMsg.dsContrPrcEffSqNum_E1.getValue();
													visibleStyle = "visible";
												} else { 
													visibleStyle = "hidden";
												}
											%>
														<tr ezfhyo="E" id="id_leftE_row{EZF_ROW_NUMBER}">
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputCheckBox name="xxChkBox_E1" ezfName="xxChkBox_E1" value="Y" ezfHyo="E" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_E1#{EZF_ROW_NUMBER}\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrPrcEffSqNum_E1" ezfName="dsContrPrcEffSqNum_E1" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrBllgSchdSqNum_E1" ezfName="dsContrBllgSchdSqNum_E1" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="perSchdNum_E1" ezfName="perSchdNum_E1" ezfHyo="E" ezfArrayNo="0" otherEvent1=" onchange=\"execTableEvent('E','OnChangePeriods','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:select name="perBllgCycleCd_E1" ezfName="perBllgCycleCd_E1" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleUomCd_E1" ezfDispName="bllgCycleUomNm_E2" ezfCodeDispHyo="E" otherEvent1=" onchange=\"execTableEvent('E','OnChangeUOM','{EZF_ROW_NUMBER}');\"" /></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdFromDt_E1" ezfName="bllgSchdFromDt_E1" ezfHyo="E" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('E','OnChangeFromDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdFromDt_E1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdFromDt_E1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdThruDt_E1" ezfName="bllgSchdThruDt_E1" ezfHyo="E" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('E','OnChangeThruDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdThruDt_E1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdThruDt_E1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><ezf:inputText name="bllgCycleDescTxt_E1" ezfName="bllgCycleDescTxt_E1" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_E1" ezfName="xsMtrCopyQty_E1" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_ES" ezfName="xsMtrCopyQty_ES" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="xsMtrAmtRate_E1" ezfName="xsMtrAmtRate_E1" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="ccyCd_E1" ezfName="ccyCd_E1" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_E1" ezfName="xxRecHistCratTs_E1" ezfHyo="E" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_E1" ezfName="xxRecHistCratByNm_E1" ezfHyo="E" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_E1" ezfName="xxRecHistUpdTs_E1" ezfHyo="E" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_E1" ezfName="xxRecHistUpdByNm_E1" ezfHyo="E" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_E1" ezfName="xxRecHistTblNm_E1" ezfHyo="E" ezfArrayNo="0" />
															</td>
														</tr>
											<% i++; %>
										</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
						<% } %>
						
						
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_F)) { %>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="left" width="40">
												<col align="left" width="200">
												<col>
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_F" ezfName="mtrLbNm_F" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><br /></td>
													<td><br /></td>
													<td><br /></td>
													<td><input type="button" class="pBtn6" value="Select All" name="SelectAll" onclick="execTableEvent('F', 'SelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Un Select All" name="UnSelectAll" onclick="execTableEvent('F', 'UnSelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Insert Row" name="InsertRow" onclick="execTableEvent('F', 'InsertRow');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Delete Row" name="DeleteRow" onclick="execTableEvent('F', 'DeleteRow');" <%= buttonDisable %>></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_F_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="center" width="25">
													<col align="center" width="50">		<!-- PE Seq# -->
													<col align="center" width="50">		<!-- TS Seq# -->
													<col align="center" width="60">		<!-- Periods -->
													<col align="center" width="105">	<!-- Sched.UOM -->
													<col align="center" width="110">	<!-- Sched.From Date -->
													<col align="center" width="110">	<!-- Sched.Thru Date -->
													<col align="center" width="140">	<!-- Billing Cycle -->
													<col align="center" width="105">	<!-- Allowance/Period -->
													<col align="center" width="130">	<!-- Allowance -->
													<col align="center" width="130">	<!-- Price -->
													<col align="center" width="70">		<!-- Currency -->
													<tr>
														<td class="pClothBs"><br /></td>
														<td class="pClothBs">PE Seq#</td>
														<td class="pClothBs">TS Seq#</td>
														<td class="pClothBs">Periods</td>
														<td class="pClothBs">Sched.UOM</td>
														<td class="pClothBs">Sched.From Date</td>
														<td class="pClothBs">Sched.Thru Date</td>
														<td class="pClothBs">Billing Cycle</td>
														<td class="pClothBs">Allowance/Period</td>
														<td class="pClothBs">Allowance</td>
														<td class="pClothBs">Price</td>
														<td class="pClothBs">Currency</td>
													</tr>
												</table>
											</div>
											<div id="LeftTable_F" style="overflow-y:scroll; height:149; overflow-x:hidden; width:1109; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="F" style="table-layout: fixed">
														<col align="center" width="25">
														<col align="center" width="50">		<!-- PE Seq# -->
														<col align="center" width="50">		<!-- TS Seq# -->
														<col align="center" width="60">		<!-- Periods -->
														<col align="center" width="105">	<!-- Sched.UOM -->
														<col align="center" width="110">	<!-- Sched.From Date -->
														<col align="center" width="110">	<!-- Sched.Thru Date -->
														<col align="center" width="140">	<!-- Billing Cycle -->
														<col align="center" width="105">	<!-- Allowance/Period -->
														<col align="center" width="130">	<!-- Allowance -->
														<col align="center" width="130">	<!-- Price -->
														<col align="center" width="70">		<!-- Currency -->
										<% i = 0; %>
										<% targetPeSqNum = null; %>
										<% targetSqNum = null; %>
										<ezf:row ezfHyo="F">
											<% NSAL0360_FBMsg fbMsg = bMsg.F.no(i); %>
											<% 
												if (i == 0 || !targetSqNum.equals(fbMsg.dsContrBllgSchdSqNum_F1.getValue()) || targetPeSqNum.compareTo(fbMsg.dsContrPrcEffSqNum_F1.getValue()) != 0) {
													targetSqNum = fbMsg.dsContrBllgSchdSqNum_F1.getValue();
													targetPeSqNum = fbMsg.dsContrPrcEffSqNum_F1.getValue();
													visibleStyle = "visible";
												} else { 
													visibleStyle = "hidden";
												}
											%>
														<tr ezfhyo="F" id="id_leftF_row{EZF_ROW_NUMBER}">
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputCheckBox name="xxChkBox_F1" ezfName="xxChkBox_F1" value="Y" ezfHyo="F" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_F1#{EZF_ROW_NUMBER}\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrPrcEffSqNum_F1" ezfName="dsContrPrcEffSqNum_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrBllgSchdSqNum_F1" ezfName="dsContrBllgSchdSqNum_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="perSchdNum_F1" ezfName="perSchdNum_F1" ezfHyo="F" ezfArrayNo="0" otherEvent1=" onchange=\"execTableEvent('F','OnChangePeriods','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:select name="perBllgCycleCd_F1" ezfName="perBllgCycleCd_F1" ezfHyo="F" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleUomCd_F1" ezfDispName="bllgCycleUomNm_F2" ezfCodeDispHyo="F" otherEvent1=" onchange=\"execTableEvent('F','OnChangeUOM','{EZF_ROW_NUMBER}');\"" /></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdFromDt_F1" ezfName="bllgSchdFromDt_F1" ezfHyo="F" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('F','OnChangeFromDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdFromDt_F1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdFromDt_F1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdThruDt_F1" ezfName="bllgSchdThruDt_F1" ezfHyo="F" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('F','OnChangeThruDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdThruDt_F1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdThruDt_F1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><ezf:inputText name="bllgCycleDescTxt_F1" ezfName="bllgCycleDescTxt_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_F1" ezfName="xsMtrCopyQty_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_FS" ezfName="xsMtrCopyQty_FS" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="xsMtrAmtRate_F1" ezfName="xsMtrAmtRate_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="ccyCd_F1" ezfName="ccyCd_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_F1" ezfName="xxRecHistCratTs_F1" ezfHyo="F" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_F1" ezfName="xxRecHistCratByNm_F1" ezfHyo="F" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_F1" ezfName="xxRecHistUpdTs_F1" ezfHyo="F" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_F1" ezfName="xxRecHistUpdByNm_F1" ezfHyo="F" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_F1" ezfName="xxRecHistTblNm_F1" ezfHyo="F" ezfArrayNo="0" />
															</td>
														</tr>
											<% i++; %>
										</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
						<% } %>
						
						
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_G)) { %>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="left" width="40">
												<col align="left" width="200">
												<col>
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_G" ezfName="mtrLbNm_G" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><br /></td>
													<td><br /></td>
													<td><br /></td>
													<td><input type="button" class="pBtn6" value="Select All" name="SelectAll" onclick="execTableEvent('G', 'SelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Un Select All" name="UnSelectAll" onclick="execTableEvent('G', 'UnSelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Insert Row" name="InsertRow" onclick="execTableEvent('G', 'InsertRow');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Delete Row" name="DeleteRow" onclick="execTableEvent('G', 'DeleteRow');" <%= buttonDisable %>></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_G_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="center" width="25">
													<col align="center" width="50">		<!-- PE Seq# -->
													<col align="center" width="50">		<!-- TS Seq# -->
													<col align="center" width="60">		<!-- Periods -->
													<col align="center" width="105">	<!-- Sched.UOM -->
													<col align="center" width="110">	<!-- Sched.From Date -->
													<col align="center" width="110">	<!-- Sched.Thru Date -->
													<col align="center" width="140">	<!-- Billing Cycle -->
													<col align="center" width="105">	<!-- Allowance/Period -->
													<col align="center" width="130">	<!-- Allowance -->
													<col align="center" width="130">	<!-- Price -->
													<col align="center" width="70">		<!-- Currency -->
													<tr>
														<td class="pClothBs"><br /></td>
														<td class="pClothBs">PE Seq#</td>
														<td class="pClothBs">TS Seq#</td>
														<td class="pClothBs">Periods</td>
														<td class="pClothBs">Sched.UOM</td>
														<td class="pClothBs">Sched.From Date</td>
														<td class="pClothBs">Sched.Thru Date</td>
														<td class="pClothBs">Billing Cycle</td>
														<td class="pClothBs">Allowance/Period</td>
														<td class="pClothBs">Allowance</td>
														<td class="pClothBs">Price</td>
														<td class="pClothBs">Currency</td>
													</tr>
												</table>
											</div>
											<div id="LeftTable_G" style="overflow-y:scroll; height:149; overflow-x:hidden; width:1109; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="G" style="table-layout: fixed">
														<col align="center" width="25">
														<col align="center" width="50">		<!-- PE Seq# -->
														<col align="center" width="50">		<!-- TS Seq# -->
														<col align="center" width="60">		<!-- Periods -->
														<col align="center" width="105">	<!-- Sched.UOM -->
														<col align="center" width="110">	<!-- Sched.From Date -->
														<col align="center" width="110">	<!-- Sched.Thru Date -->
														<col align="center" width="140">	<!-- Billing Cycle -->
														<col align="center" width="105">	<!-- Allowance/Period -->
														<col align="center" width="130">	<!-- Allowance -->
														<col align="center" width="130">	<!-- Price -->
														<col align="center" width="70">		<!-- Currency -->
										<% i = 0; %>
										<% targetPeSqNum = null; %>
										<% targetSqNum = null; %>
										<ezf:row ezfHyo="G">
											<% NSAL0360_GBMsg gbMsg = bMsg.G.no(i); %>
											<% 
												if (i == 0 || !targetSqNum.equals(gbMsg.dsContrBllgSchdSqNum_G1.getValue()) || targetPeSqNum.compareTo(gbMsg.dsContrPrcEffSqNum_G1.getValue()) != 0) {
													targetSqNum = gbMsg.dsContrBllgSchdSqNum_G1.getValue();
													targetPeSqNum = gbMsg.dsContrPrcEffSqNum_G1.getValue();
													visibleStyle = "visible";
												} else { 
													visibleStyle = "hidden";
												}
											%>
														<tr ezfhyo="G" id="id_leftG_row{EZF_ROW_NUMBER}">
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputCheckBox name="xxChkBox_G1" ezfName="xxChkBox_G1" value="Y" ezfHyo="G" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_G1#{EZF_ROW_NUMBER}\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrPrcEffSqNum_G1" ezfName="dsContrPrcEffSqNum_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrBllgSchdSqNum_G1" ezfName="dsContrBllgSchdSqNum_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="perSchdNum_G1" ezfName="perSchdNum_G1" ezfHyo="G" ezfArrayNo="0" otherEvent1=" onchange=\"execTableEvent('G','OnChangePeriods','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:select name="perBllgCycleCd_G1" ezfName="perBllgCycleCd_G1" ezfHyo="G" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleUomCd_G1" ezfDispName="bllgCycleUomNm_G2" ezfCodeDispHyo="G" otherEvent1=" onchange=\"execTableEvent('G','OnChangeUOM','{EZF_ROW_NUMBER}');\"" /></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdFromDt_G1" ezfName="bllgSchdFromDt_G1" ezfHyo="G" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('G','OnChangeFromDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdFromDt_G1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdFromDt_G1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdThruDt_G1" ezfName="bllgSchdThruDt_G1" ezfHyo="G" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('G','OnChangeThruDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdThruDt_G1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdThruDt_G1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><ezf:inputText name="bllgCycleDescTxt_G1" ezfName="bllgCycleDescTxt_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_G1" ezfName="xsMtrCopyQty_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_GS" ezfName="xsMtrCopyQty_GS" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="xsMtrAmtRate_G1" ezfName="xsMtrAmtRate_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="ccyCd_G1" ezfName="ccyCd_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_G1" ezfName="xxRecHistCratTs_G1" ezfHyo="G" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_G1" ezfName="xxRecHistCratByNm_G1" ezfHyo="G" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_G1" ezfName="xxRecHistUpdTs_G1" ezfHyo="G" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_G1" ezfName="xxRecHistUpdByNm_G1" ezfHyo="G" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_G1" ezfName="xxRecHistTblNm_G1" ezfHyo="G" ezfArrayNo="0" />
															</td>
														</tr>
											<% i++; %>
										</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
						<% } %>
						
						
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_H)) { %>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="left" width="40">
												<col align="left" width="200">
												<col>
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_H" ezfName="mtrLbNm_H" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><br /></td>
													<td><br /></td>
													<td><br /></td>
													<td><input type="button" class="pBtn6" value="Select All" name="SelectAll" onclick="execTableEvent('H', 'SelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Un Select All" name="UnSelectAll" onclick="execTableEvent('H', 'UnSelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Insert Row" name="InsertRow" onclick="execTableEvent('H', 'InsertRow');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Delete Row" name="DeleteRow" onclick="execTableEvent('H', 'DeleteRow');" <%= buttonDisable %>></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_H_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="center" width="25">
													<col align="center" width="50">		<!-- PE Seq# -->
													<col align="center" width="50">		<!-- TS Seq# -->
													<col align="center" width="60">		<!-- Periods -->
													<col align="center" width="105">	<!-- Sched.UOM -->
													<col align="center" width="110">	<!-- Sched.From Date -->
													<col align="center" width="110">	<!-- Sched.Thru Date -->
													<col align="center" width="140">	<!-- Billing Cycle -->
													<col align="center" width="105">	<!-- Allowance/Period -->
													<col align="center" width="130">	<!-- Allowance -->
													<col align="center" width="130">	<!-- Price -->
													<col align="center" width="70">		<!-- Currency -->
													<tr>
														<td class="pClothBs"><br /></td>
														<td class="pClothBs">PE Seq#</td>
														<td class="pClothBs">TS Seq#</td>
														<td class="pClothBs">Periods</td>
														<td class="pClothBs">Sched.UOM</td>
														<td class="pClothBs">Sched.From Date</td>
														<td class="pClothBs">Sched.Thru Date</td>
														<td class="pClothBs">Billing Cycle</td>
														<td class="pClothBs">Allowance/Period</td>
														<td class="pClothBs">Allowance</td>
														<td class="pClothBs">Price</td>
														<td class="pClothBs">Currency</td>
													</tr>
												</table>
											</div>
											<div id="LeftTable_H" style="overflow-y:scroll; height:149; overflow-x:hidden; width:1109; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="H" style="table-layout: fixed">
														<col align="center" width="25">
														<col align="center" width="50">		<!-- PE Seq# -->
														<col align="center" width="50">		<!-- TS Seq# -->
														<col align="center" width="60">		<!-- Periods -->
														<col align="center" width="105">	<!-- Sched.UOM -->
														<col align="center" width="110">	<!-- Sched.From Date -->
														<col align="center" width="110">	<!-- Sched.Thru Date -->
														<col align="center" width="140">	<!-- Billing Cycle -->
														<col align="center" width="105">	<!-- Allowance/Period -->
														<col align="center" width="130">	<!-- Allowance -->
														<col align="center" width="130">	<!-- Price -->
														<col align="center" width="70">		<!-- Currency -->
										<% i = 0; %>
										<% targetPeSqNum = null; %>
										<% targetSqNum = null; %>
										<ezf:row ezfHyo="H">
											<% NSAL0360_HBMsg hbMsg = bMsg.H.no(i); %>
											<% 
												if (i == 0 || !targetSqNum.equals(hbMsg.dsContrBllgSchdSqNum_H1.getValue()) || targetPeSqNum.compareTo(hbMsg.dsContrPrcEffSqNum_H1.getValue()) != 0) {
													targetSqNum = hbMsg.dsContrBllgSchdSqNum_H1.getValue();
													targetPeSqNum = hbMsg.dsContrPrcEffSqNum_H1.getValue();
													visibleStyle = "visible";
												} else { 
													visibleStyle = "hidden";
												}
											%>
														<tr ezfhyo="H" id="id_leftH_row{EZF_ROW_NUMBER}">
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" ezfHyo="H" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_H1#{EZF_ROW_NUMBER}\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrPrcEffSqNum_H1" ezfName="dsContrPrcEffSqNum_H1" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrBllgSchdSqNum_H1" ezfName="dsContrBllgSchdSqNum_H1" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="perSchdNum_H1" ezfName="perSchdNum_H1" ezfHyo="H" ezfArrayNo="0" otherEvent1=" onchange=\"execTableEvent('H','OnChangePeriods','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:select name="perBllgCycleCd_H1" ezfName="perBllgCycleCd_H1" ezfHyo="H" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleUomCd_H1" ezfDispName="bllgCycleUomNm_H2" ezfCodeDispHyo="H" otherEvent1=" onchange=\"execTableEvent('H','OnChangeUOM','{EZF_ROW_NUMBER}');\"" /></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdFromDt_H1" ezfName="bllgSchdFromDt_H1" ezfHyo="H" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('H','OnChangeFromDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdFromDt_H1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdFromDt_H1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdThruDt_H1" ezfName="bllgSchdThruDt_H1" ezfHyo="H" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('H','OnChangeThruDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdThruDt_H1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdThruDt_H1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><ezf:inputText name="bllgCycleDescTxt_H1" ezfName="bllgCycleDescTxt_H1" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_H1" ezfName="xsMtrCopyQty_H1" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_HS" ezfName="xsMtrCopyQty_HS" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="xsMtrAmtRate_H1" ezfName="xsMtrAmtRate_H1" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="ccyCd_H1" ezfName="ccyCd_H1" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_H1" ezfName="xxRecHistCratTs_H1" ezfHyo="H" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_H1" ezfName="xxRecHistCratByNm_H1" ezfHyo="H" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_H1" ezfName="xxRecHistUpdTs_H1" ezfHyo="H" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_H1" ezfName="xxRecHistUpdByNm_H1" ezfHyo="H" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_H1" ezfName="xxRecHistTblNm_H1" ezfHyo="H" ezfArrayNo="0" />
															</td>
														</tr>
											<% i++; %>
										</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
						<% } %>
						
						
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_I)) { %>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="left" width="40">
												<col align="left" width="200">
												<col>
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_I" ezfName="mtrLbNm_I" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><br /></td>
													<td><br /></td>
													<td><br /></td>
													<td><input type="button" class="pBtn6" value="Select All" name="SelectAll" onclick="execTableEvent('I', 'SelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Un Select All" name="UnSelectAll" onclick="execTableEvent('I', 'UnSelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Insert Row" name="InsertRow" onclick="execTableEvent('I', 'InsertRow');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Delete Row" name="DeleteRow" onclick="execTableEvent('I', 'DeleteRow');" <%= buttonDisable %>></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_I_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="center" width="25">
													<col align="center" width="50">		<!-- PE Seq# -->
													<col align="center" width="50">		<!-- TS Seq# -->
													<col align="center" width="60">		<!-- Periods -->
													<col align="center" width="105">	<!-- Sched.UOM -->
													<col align="center" width="110">	<!-- Sched.From Date -->
													<col align="center" width="110">	<!-- Sched.Thru Date -->
													<col align="center" width="140">	<!-- Billing Cycle -->
													<col align="center" width="105">	<!-- Allowance/Period -->
													<col align="center" width="130">	<!-- Allowance -->
													<col align="center" width="130">	<!-- Price -->
													<col align="center" width="70">		<!-- Currency -->
													<tr>
														<td class="pClothBs"><br /></td>
														<td class="pClothBs">PE Seq#</td>
														<td class="pClothBs">TS Seq#</td>
														<td class="pClothBs">Periods</td>
														<td class="pClothBs">Sched.UOM</td>
														<td class="pClothBs">Sched.From Date</td>
														<td class="pClothBs">Sched.Thru Date</td>
														<td class="pClothBs">Billing Cycle</td>
														<td class="pClothBs">Allowance/Period</td>
														<td class="pClothBs">Allowance</td>
														<td class="pClothBs">Price</td>
														<td class="pClothBs">Currency</td>
													</tr>
												</table>
											</div>
											<div id="LeftTable_I" style="overflow-y:scroll; height:149; overflow-x:hidden; width:1109; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="I" style="table-layout: fixed">
														<col align="center" width="25">
														<col align="center" width="50">		<!-- PE Seq# -->
														<col align="center" width="50">		<!-- TS Seq# -->
														<col align="center" width="60">		<!-- Periods -->
														<col align="center" width="105">	<!-- Sched.UOM -->
														<col align="center" width="110">	<!-- Sched.From Date -->
														<col align="center" width="110">	<!-- Sched.Thru Date -->
														<col align="center" width="140">	<!-- Billing Cycle -->
														<col align="center" width="105">	<!-- Allowance/Period -->
														<col align="center" width="130">	<!-- Allowance -->
														<col align="center" width="130">	<!-- Price -->
														<col align="center" width="70">		<!-- Currency -->
										<% i = 0; %>
										<% targetPeSqNum = null; %>
										<% targetSqNum = null; %>
										<ezf:row ezfHyo="I">
											<% NSAL0360_IBMsg ibMsg = bMsg.I.no(i); %>
											<% 
												if (i == 0 || !targetSqNum.equals(ibMsg.dsContrBllgSchdSqNum_I1.getValue()) || targetPeSqNum.compareTo(ibMsg.dsContrPrcEffSqNum_I1.getValue()) != 0) {
													targetSqNum = ibMsg.dsContrBllgSchdSqNum_I1.getValue();
													targetPeSqNum = ibMsg.dsContrPrcEffSqNum_I1.getValue();
													visibleStyle = "visible";
												} else { 
													visibleStyle = "hidden";
												}
											%>
														<tr ezfhyo="I" id="id_leftI_row{EZF_ROW_NUMBER}">
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputCheckBox name="xxChkBox_I1" ezfName="xxChkBox_I1" value="Y" ezfHyo="I" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_I1#{EZF_ROW_NUMBER}\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrPrcEffSqNum_I1" ezfName="dsContrPrcEffSqNum_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrBllgSchdSqNum_I1" ezfName="dsContrBllgSchdSqNum_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="perSchdNum_I1" ezfName="perSchdNum_I1" ezfHyo="I" ezfArrayNo="0" otherEvent1=" onchange=\"execTableEvent('I','OnChangePeriods','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:select name="perBllgCycleCd_I1" ezfName="perBllgCycleCd_I1" ezfHyo="I" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleUomCd_I1" ezfDispName="bllgCycleUomNm_I2" ezfCodeDispHyo="I" otherEvent1=" onchange=\"execTableEvent('I','OnChangeUOM','{EZF_ROW_NUMBER}');\"" /></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdFromDt_I1" ezfName="bllgSchdFromDt_I1" ezfHyo="I" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('I','OnChangeFromDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdFromDt_I1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdFromDt_I1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdThruDt_I1" ezfName="bllgSchdThruDt_I1" ezfHyo="I" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('I','OnChangeThruDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdThruDt_I1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdThruDt_I1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><ezf:inputText name="bllgCycleDescTxt_I1" ezfName="bllgCycleDescTxt_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_I1" ezfName="xsMtrCopyQty_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_IS" ezfName="xsMtrCopyQty_IS" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="xsMtrAmtRate_I1" ezfName="xsMtrAmtRate_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="ccyCd_I1" ezfName="ccyCd_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_I1" ezfName="xxRecHistCratTs_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_I1" ezfName="xxRecHistCratByNm_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_I1" ezfName="xxRecHistUpdTs_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_I1" ezfName="xxRecHistUpdByNm_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_I1" ezfName="xxRecHistTblNm_I1" ezfHyo="I" ezfArrayNo="0" />
															</td>
														</tr>
											<% i++; %>
										</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
						<% } %>
						
						
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_J)) { %>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="left" width="40">
												<col align="left" width="200">
												<col>
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<col align="right" width="70">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_J" ezfName="mtrLbNm_J" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><br /></td>
													<td><br /></td>
													<td><br /></td>
													<td><input type="button" class="pBtn6" value="Select All" name="SelectAll" onclick="execTableEvent('J', 'SelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Un Select All" name="UnSelectAll" onclick="execTableEvent('J', 'UnSelectAll');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Insert Row" name="InsertRow" onclick="execTableEvent('J', 'InsertRow');" <%= buttonDisable %>></td>
													<td><input type="button" class="pBtn6" value="Delete Row" name="DeleteRow" onclick="execTableEvent('J', 'DeleteRow');" <%= buttonDisable %>></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_J_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="center" width="25">
													<col align="center" width="50">		<!-- PE Seq# -->
													<col align="center" width="50">		<!-- TS Seq# -->
													<col align="center" width="60">		<!-- Periods -->
													<col align="center" width="105">	<!-- Sched.UOM -->
													<col align="center" width="110">	<!-- Sched.From Date -->
													<col align="center" width="110">	<!-- Sched.Thru Date -->
													<col align="center" width="140">	<!-- Billing Cycle -->
													<col align="center" width="105">	<!-- Allowance/Period -->
													<col align="center" width="130">	<!-- Allowance -->
													<col align="center" width="130">	<!-- Price -->
													<col align="center" width="70">		<!-- Currency -->
													<tr>
														<td class="pClothBs"><br /></td>
														<td class="pClothBs">PE Seq#</td>
														<td class="pClothBs">TS Seq#</td>
														<td class="pClothBs">Periods</td>
														<td class="pClothBs">Sched.UOM</td>
														<td class="pClothBs">Sched.From Date</td>
														<td class="pClothBs">Sched.Thru Date</td>
														<td class="pClothBs">Billing Cycle</td>
														<td class="pClothBs">Allowance/Period</td>
														<td class="pClothBs">Allowance</td>
														<td class="pClothBs">Price</td>
														<td class="pClothBs">Currency</td>
													</tr>
												</table>
											</div>
											<div id="LeftTable_J" style="overflow-y:scroll; height:149; overflow-x:hidden; width:1109; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="J" style="table-layout: fixed">
														<col align="center" width="25">
														<col align="center" width="50">		<!-- PE Seq# -->
														<col align="center" width="50">		<!-- TS Seq# -->
														<col align="center" width="60">		<!-- Periods -->
														<col align="center" width="105">	<!-- Sched.UOM -->
														<col align="center" width="110">	<!-- Sched.From Date -->
														<col align="center" width="110">	<!-- Sched.Thru Date -->
														<col align="center" width="140">	<!-- Billing Cycle -->
														<col align="center" width="105">	<!-- Allowance/Period -->
														<col align="center" width="130">	<!-- Allowance -->
														<col align="center" width="130">	<!-- Price -->
														<col align="center" width="70">		<!-- Currency -->
										<% i = 0; %>
										<% targetPeSqNum = null; %>
										<% targetSqNum = null; %>
										<ezf:row ezfHyo="J">
											<% NSAL0360_JBMsg jbMsg = bMsg.J.no(i); %>
											<% 
												if (i == 0 || !targetSqNum.equals(jbMsg.dsContrBllgSchdSqNum_J1.getValue()) || targetPeSqNum.compareTo(jbMsg.dsContrPrcEffSqNum_J1.getValue()) != 0) {
													targetSqNum = jbMsg.dsContrBllgSchdSqNum_J1.getValue();
													targetPeSqNum = jbMsg.dsContrPrcEffSqNum_J1.getValue();
													visibleStyle = "visible";
												} else { 
													visibleStyle = "hidden";
												}
											%>
														<tr ezfhyo="J" id="id_leftJ_row{EZF_ROW_NUMBER}">
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputCheckBox name="xxChkBox_J1" ezfName="xxChkBox_J1" value="Y" ezfHyo="J" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_J1#{EZF_ROW_NUMBER}\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrPrcEffSqNum_J1" ezfName="dsContrPrcEffSqNum_J1" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="dsContrBllgSchdSqNum_J1" ezfName="dsContrBllgSchdSqNum_J1" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="perSchdNum_J1" ezfName="perSchdNum_J1" ezfHyo="J" ezfArrayNo="0" otherEvent1=" onchange=\"execTableEvent('J','OnChangePeriods','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"3\" maxlength=\"3\""/></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:select name="perBllgCycleCd_J1" ezfName="perBllgCycleCd_J1" ezfHyo="J" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleUomCd_J1" ezfDispName="bllgCycleUomNm_J2" ezfCodeDispHyo="J" otherEvent1=" onchange=\"execTableEvent('J','OnChangeUOM','{EZF_ROW_NUMBER}');\"" /></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdFromDt_J1" ezfName="bllgSchdFromDt_J1" ezfHyo="J" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('J','OnChangeFromDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdFromDt_J1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdFromDt_J1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><p style="visibility:<%= visibleStyle %>;"><ezf:inputText name="bllgSchdThruDt_J1" ezfName="bllgSchdThruDt_J1" ezfHyo="J" ezfArrayNo="0" otherEvent1=" onblur=\"execTableEvent('J','OnChangeThruDate','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdThruDt_J1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdThruDt_J1#{EZF_ROW_NUMBER}', 4);"></p></td>
															<td><ezf:inputText name="bllgCycleDescTxt_J1" ezfName="bllgCycleDescTxt_J1" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_J1" ezfName="xsMtrCopyQty_J1" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
															<td><ezf:inputText name="xsMtrCopyQty_JS" ezfName="xsMtrCopyQty_JS" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="xsMtrAmtRate_J1" ezfName="xsMtrAmtRate_J1" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="ccyCd_J1" ezfName="ccyCd_J1" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_J1" ezfName="xxRecHistCratTs_J1" ezfHyo="J" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_J1" ezfName="xxRecHistCratByNm_J1" ezfHyo="J" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_J1" ezfName="xxRecHistUpdTs_J1" ezfHyo="J" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_J1" ezfName="xxRecHistUpdByNm_J1" ezfHyo="J" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_J1" ezfName="xxRecHistTblNm_J1" ezfHyo="J" ezfArrayNo="0" />
															</td>
														</tr>
											<% i++; %>
										</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
						<% } %>
						
					</div>
					<br />
					<table width="98%" border="0" align="center" style="table-layout:fixed;">
						<col>
						<col align="right" width="75">
						<col align="right" width="75">
						<tr>
							<td><br/ ></td>
							<td><ezf:inputButton name="Schedules" value="Schedules" htmlClass="pBtn6" /></td>
							<td><ezf:inputButton name="SkipMonth" value="Skip Month" htmlClass="pBtn6" /></td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" style="table-layout:fixed;">
						<col width="80">
						<col width="900">
						<tr>
							<td class="stab">Reason Code</td>
							<td><ezf:select name="svcMemoRsnCd_F3" ezfName="svcMemoRsnCd_F3" ezfBlank="1" ezfCodeName="svcMemoRsnCd_F1" ezfDispName="svcMemoRsnNm_F2" /></td>
						</tr>
						<tr>
							<td class="stab">Comment</td>
							<td><ezf:textArea name="svcCmntTxt_F1" ezfName="svcCmntTxt_F1" otherAttr=" cols=\"130\" rows=\"4\""/></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<ezf:inputHidden name="srcTblNm_P1" ezfName="srcTblNm_P1" />
<script type="text/javascript">
	function execTableEvent( tblNm, eventNm,  number) {
		document.getElementById( "srcTblNm_P1" ).value = tblNm;
		sendServer(eventNm, number);
	}
</script>

<%-- ###SCRIPT --%>
<script type="text/javascript">
	function onFocusEvent(item){
		if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
		}
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
