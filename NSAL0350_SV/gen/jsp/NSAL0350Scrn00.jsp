<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20221012102805 --%>
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
			<input type="hidden" name="pageID" value="NSAL0350Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Schedule Inquiry">

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
										<li title="View Prc Base" class="pTab_ON"><a href="#">Schedule Inquiry</a></li>
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
					<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
						<col>
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="60">
									<col width="200">
									<col width="30">
									<col width="50">
									<col width="200">
									<col width="20">
									<col width="30">
									<col width="90">
									<col width="10">
									<col width="100">
									<col width="260">
									<tr>
										<td class="stab">Contract#</td>
										<td><ezf:inputText name="dsContrNum_H1" ezfName="dsContrNum_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"27\" maxlength=\"30\""/></td>
										<td><br /></td>
										<td class="stab">Serial#</td>
										<td><ezf:inputText name="serNum_H1" ezfName="serNum_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"27\" maxlength=\"30\""/></td>
										<td><br /></td>
										<td class="stab">IB ID</td>
										<td><ezf:inputText name="svcMachMstrPk_H1" ezfName="svcMachMstrPk_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"10\" maxlength=\"28\""/></td>
										<td><br /></td>
										<td class="stab">Service Program</td>
										<td><ezf:inputText name="mdseDescShortTxt_H1" ezfName="mdseDescShortTxt_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"31\" maxlength=\"250\""/></td>
									</tr>
								</table>
								<table border="0" style="table-layout:fixed;">
									<col width="70">
									<col width="80">
									<col width="20">
									<col width="10">
									<col width="60">
									<col width="80">
									<col width="20">
									<col width="10">
									<col width="85">
									<col width="100">
									<col width="10">
									<col width="55">
									<col width="130">
									<col width="140">
									<col width="20">
									<col width="60">
									<col width="10">
									<col width="85">
									<tr>
										<td class="stab">Bill From Date</td>
										<td><ezf:inputText name="bllgSchdFromDt_HD" ezfName="bllgSchdFromDt_HD" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
										<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdFromDt_HD', 4);"></td>
										<td><br /></td>
										<td class="stab">Bill To Date</td>
										<td><ezf:inputText name="bllgSchdThruDt_HD" ezfName="bllgSchdThruDt_HD" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
										<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdThruDt_HD', 4);"></td>
										<td><br /></td>
										<td class="stab">Schedule Status</td>
										<td><ezf:select name="dsBllgSchdStsCd_HD" ezfName="dsBllgSchdStsCd_HD" ezfBlank="1" ezfCodeName="dsBllgSchdStsCd_PL" ezfDispName="dsBllgSchdStsDescTxt_PL" otherAttr=" style=\"width:95\""/></td>
										<td><br /></td>
										<td class="stab">Skip Type</td>
										<td><ezf:select name="skipRecovTpCd_HD" ezfName="skipRecovTpCd_HD" ezfBlank="1" ezfCodeName="skipRecovTpCd_H1" ezfDispName="skipRecovTpNm_H1" otherAttr=" style=\"width:125\""/></td>
										<td><br /></td>
										<td><ezf:inputCheckBox name="xxChkBox_HD" ezfName="xxChkBox_HD" value="Y" /></td>
										<td class="stab">Latest Only</td>
										<td><br /></td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<hr />
<%-- #################################################### DETAIL ################################################### --%>
<%@ page import="business.servlet.NSAL0350.NSAL0350BMsg" %>
<%@ page import="business.servlet.NSAL0350.NSAL0350_ABMsg" %>
<%@ page import="business.servlet.NSAL0350.NSAL0350_BBMsg" %>
<%@ page import="business.servlet.NSAL0350.NSAL0350_CBMsg" %>
<%@ page import="business.servlet.NSAL0350.NSAL0350_DBMsg" %>
<%@ page import="business.servlet.NSAL0350.NSAL0350_EBMsg" %>
<%@ page import="business.servlet.NSAL0350.NSAL0350_FBMsg" %>
<%@ page import="business.servlet.NSAL0350.NSAL0350_GBMsg" %>
<%@ page import="business.servlet.NSAL0350.NSAL0350_HBMsg" %>
<%@ page import="business.servlet.NSAL0350.NSAL0350_IBMsg" %>
<%@ page import="business.servlet.NSAL0350.NSAL0350_JBMsg" %>
<%@ page import="business.servlet.NSAL0350.NSAL0350_OBMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>

<%  NSAL0350BMsg bMsg = (NSAL0350BMsg)databean.getEZDBMsg(); %>
					<div id="tableTop" style="overflow-y:scroll; height:500; overflow-x:hidden; width:100%; float:left;">
							<% if (ZYPCommonFunc.hasValue(bMsg.xxAbendMsgTxt_H1)) { %>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center" style="table-layout: fixed;">
									<col align="center" width="1080">		<!-- Seq No -->
									<tr>
										<td><ezf:inputText name="xxAbendMsgTxt_H1" ezfName="xxAbendMsgTxt_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8WWWWWWWWW9WWWWWWWWW0WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8WWWWWWWWW9WWWWWWWWW0" otherAttr=" size=\"155\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
								</table>
							<% } %>

					<% if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(bMsg.svcInvChrgTpCd_H1.getValue())) { %>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<div id="LeftTable_O_Top" style="overflow-x:none; overflow-y:none; width:1085; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="50" align="center">		<!-- PE Seq# -->
														<col width="50" align="center">		<!-- TS Seq# -->
														<col width="60" align="center">		<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="82" align="center">		<!-- Bill From Date -->
														<col width="76" align="center">		<!-- Bill To Date -->
														<col width="82" align="center">		<!-- Interface Date -->
														<col width="110" align="center">	<!-- Price/Period -->
														<col width="108" align="center">	<!-- Schedule Status -->
														<col width="75" align="center">		<!-- Invoice# -->
														<col width="90" align="center">		<!-- Actual Invoice Date -->
														<col width="110" align="center">	<!-- Invoiced Amount -->
														<col width="100" align="center">	<!-- Tax Amount -->
														<tr>
															<td class="pClothBs">PE Seq#</td>
															<td class="pClothBs">TS Seq#</td>
															<td class="pClothBs">Schedule Seq#</td>
															<td class="pClothBs">Skip Type</td>
															<td class="pClothBs">Bill From Date</td>
															<td class="pClothBs">Bill To Date</td>
															<td class="pClothBs">Interface Date</td>
															<td class="pClothBs">Price/Period</td>
															<td class="pClothBs">Schedule<br />Status</td>
															<td class="pClothBs">Invoice#</td>
															<td class="pClothBs">Actual Invoice<br />Date</td>
															<td class="pClothBs">Invoiced Amount</td>
															<td class="pClothBs">Tax Amount</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_O" style="overflow-y:scroll; height:460; overflow-x:hidden; width:1104; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="O" style="table-layout: fixed">
														<col width="50">					<!-- PE Seq# -->
														<col width="50">					<!-- TS Seq# -->
														<col width="60">					<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="82">					<!-- Bill From Date -->
														<col width="76">					<!-- Bill To Date -->
														<col width="82">					<!-- Interface Date -->
														<col width="110" align="right">		<!-- Price/Period -->
														<col width="108">					<!-- Schedule Status -->
														<col width="75">					<!-- Invoice# -->
														<col width="90">					<!-- Actual Invoice Date -->
														<col width="110" align="right">		<!-- Invoiced Amount -->
														<col width="100" align="right">		<!-- Tax Amount -->
											<% int count = 0; %>
											<ezf:row ezfHyo="O">
														<tr ezfhyo="O" id="id_leftO_row{EZF_ROW_NUMBER}">
															<td><ezf:label name="dsContrPrcEffSqNum_O1" ezfName="dsContrPrcEffSqNum_O1" ezfHyo="O" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdLvlNum_O1" ezfName="dsContrBllgSchdLvlNum_O1" ezfHyo="O" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdSqNum_O1" ezfName="dsContrBllgSchdSqNum_O1" ezfHyo="O" ezfArrayNo="0" /></td>
															<td><ezf:select name="skipRecovTpCd_O3" ezfName="skipRecovTpCd_O3" ezfHyo="O" ezfArrayNo="0" ezfBlank="1" ezfCodeName="skipRecovTpCd_H1" ezfDispName="skipRecovTpNm_H1" otherAttr=" style=\"width:85\""/></td>
															<td><ezf:label name="bllgSchdFromDt_O1" ezfName="bllgSchdFromDt_O1" ezfHyo="O" ezfArrayNo="0" /></td>
															<td><ezf:label name="bllgSchdThruDt_O1" ezfName="bllgSchdThruDt_O1" ezfHyo="O" ezfArrayNo="0" /></td>
															<td><ezf:label name="nextBllgDt_O1" ezfName="nextBllgDt_O1" ezfHyo="O" ezfArrayNo="0" /></td>
															<td><ezf:label name="basePrcDealAmt_O1" ezfName="basePrcDealAmt_O1" ezfHyo="O" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsBllgSchdStsDescTxt_O1" ezfName="dsBllgSchdStsDescTxt_O1" ezfHyo="O" ezfArrayNo="0" /></td>
															<td>
															<% if (ZYPCommonFunc.hasValue(bMsg.O.no(count).eipRptRqstPk_O1)) { %>
															<ezf:anchor name="svcInvNum_O1" ezfName="svcInvNum_O1" ezfHyo="O" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewPaperInvoiceO" otherAttr=" id=\"svcInvNum_O1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="svcInvNum_O1" ezfName="svcInvNum_O1" ezfHyo="O" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/></ezf:anchor>
															<% } else { %>
															<ezf:label name="svcInvNum_O1" ezfName="svcInvNum_O1" ezfHyo="O" ezfArrayNo="0" />
															<% } %>
															</td>
															<td><ezf:label name="invDt_O1" ezfName="invDt_O1" ezfHyo="O" ezfArrayNo="0" /></td>
															<td><ezf:label name="baseActlPrcDealAmt_O1" ezfName="baseActlPrcDealAmt_O1" ezfHyo="O" ezfArrayNo="0" /></td>
															<td><ezf:label name="dealTaxAmt_O1" ezfName="dealTaxAmt_O1" ezfHyo="O" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_O1" ezfName="xxRecHistCratTs_O1" ezfHyo="O" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_O1" ezfName="xxRecHistCratByNm_O1" ezfHyo="O" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_O1" ezfName="xxRecHistUpdTs_O1" ezfHyo="O" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_O1" ezfName="xxRecHistUpdByNm_O1" ezfHyo="O" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_O1" ezfName="xxRecHistTblNm_O1" ezfHyo="O" ezfArrayNo="0" />
															</td>
														</tr>
											<% count++; %>
											</ezf:row>
													<ezf:skip>
														<tr ezfhyo="O" id="id_leftO_row{EZF_ROW_NUMBER}">
															<td><label ezfout name="dsContrPrcEffSqNum_O1" ezfname="dsContrPrcEffSqNum_O1" ezfhyo="O" ezfArrayNo="0">1234</label></td>
															<td><label ezfout name="dsContrBllgSchdLvlNum_O1" ezfname="dsContrBllgSchdLvlNum_O1" ezfhyo="O" ezfArrayNo="0">1</label></td>
															<td><label ezfout name="dsContrBllgSchdSqNum_O1" ezfname="dsContrBllgSchdSqNum_O1" ezfhyo="O" ezfArrayNo="0">1</label></td>
															<td><select name="skipRecovTpCd_O3" ezfname="skipRecovTpCd_O3" ezflist="skipRecovTpCd_H1,skipRecovTpNm_H1,99, ,blank" ezfhyo="O" ezfArrayNo="0" style="width:85"><option>None</option><option>Skip</option><option>Recover</option></select></td>
															<td><label ezfout name="bllgSchdFromDt_O1" ezfname="bllgSchdFromDt_O1" ezfhyo="O" ezfArrayNo="0">01/01/2000</label></td>
															<td><label ezfout name="bllgSchdThruDt_O1" ezfname="bllgSchdThruDt_O1" ezfhyo="O" ezfArrayNo="0">12/31/2000</label></td>
															<td><label ezfout name="nextBllgDt_O1" ezfname="nextBllgDt_O1" ezfhyo="O" ezfArrayNo="0">06/31/2000</label></td>
															<td><label ezfout name="basePrcDealAmt_O1" ezfname="basePrcDealAmt_O1" ezfhyo="O" ezfArrayNo="0">100.00</label></td>
															<td><label ezfout name="dsBllgSchdStsDescTxt_O1" ezfname="dsBllgSchdStsDescTxt_O1" ezfhyo="O" ezfArrayNo="0">Closed</label></td>
															<td><label ezfout name="svcInvNum_O1" ezfname="svcInvNum_O1" ezfhyo="O" ezfArrayNo="0">12345678</label></td>
															<td><label ezfout name="invDt_O1" ezfname="invDt_O1" ezfhyo="O" ezfArrayNo="0">06/31/2000</label></td>
															<td><label ezfout name="baseActlPrcDealAmt_O1" ezfname="baseActlPrcDealAmt_O1" ezfhyo="O" ezfArrayNo="0">100.00</label></td>
															<td><label ezfout name="dealTaxAmt_O1" ezfname="dealTaxAmt_O1" ezfhyo="O" ezfArrayNo="0">200.00</label></td>
														</tr>
														<tr ezfhyo="O" id="id_leftO_row{EZF_ROW_NUMBER}">
															<td><label ezfout name="dsContrPrcEffSqNum_O1" ezfname="dsContrPrcEffSqNum_O1" ezfhyo="O" ezfArrayNo="0">1234</label></td>
															<td><label ezfout name="dsContrBllgSchdLvlNum_O1" ezfname="dsContrBllgSchdLvlNum_O1" ezfhyo="O" ezfArrayNo="0">1</label></td>
															<td><label ezfout name="dsContrBllgSchdSqNum_O1" ezfname="dsContrBllgSchdSqNum_O1" ezfhyo="O" ezfArrayNo="0">1</label></td>
															<td><select name="skipRecovTpCd_O3" ezfname="skipRecovTpCd_O3" ezflist="skipRecovTpCd_H1,skipRecovTpNm_H1,99, ,blank" ezfhyo="O" ezfArrayNo="0" style="width:85"><option>None</option><option>Skip</option><option>Recover</option></select></td>
															<td><label ezfout name="bllgSchdFromDt_O1" ezfname="bllgSchdFromDt_O1" ezfhyo="O" ezfArrayNo="0">01/01/2000</label></td>
															<td><label ezfout name="bllgSchdThruDt_O1" ezfname="bllgSchdThruDt_O1" ezfhyo="O" ezfArrayNo="0">12/31/2000</label></td>
															<td><label ezfout name="nextBllgDt_O1" ezfname="nextBllgDt_O1" ezfhyo="O" ezfArrayNo="0">06/31/2000</label></td>
															<td><label ezfout name="basePrcDealAmt_O1" ezfname="basePrcDealAmt_O1" ezfhyo="O" ezfArrayNo="0">100.00</label></td>
															<td><label ezfout name="dsBllgSchdStsDescTxt_O1" ezfname="dsBllgSchdStsDescTxt_O1" ezfhyo="O" ezfArrayNo="0">Closed</label></td>
															<td><label ezfout name="svcInvNum_O1" ezfname="svcInvNum_O1" ezfhyo="O" ezfArrayNo="0">12345678</label></td>
															<td><label ezfout name="invDt_O1" ezfname="invDt_O1" ezfhyo="O" ezfArrayNo="0">06/31/2000</label></td>
															<td><label ezfout name="baseActlPrcDealAmt_O1" ezfname="baseActlPrcDealAmt_O1" ezfhyo="O" ezfArrayNo="0">100.00</label></td>
															<td><label ezfout name="dealTaxAmt_O1" ezfname="dealTaxAmt_O1" ezfhyo="O" ezfArrayNo="0">200.00</label></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
					<% } %>
						
					<% if (SVC_INV_CHRG_TP.METER_CHARGE.equals(bMsg.svcInvChrgTpCd_H1.getValue())) { %>
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_A)) { %>
							<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
								<col>
								<tr>
									<td>
										<table id="MtrList" border="1" cellpadding="1" cellspacing="0">
											<col align="left" width="20">
											<col align="left" width="250">
											<tr>
												<td align="center" class="pClothBs">No</td>
												<td align="center" class="pClothBs">Billing Meter</td>
											</tr>
										<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_A)) { %>
											<tr>
												<td>1</td>
												<td><a href="#Table_A"><ezf:label name="mtrLbNm_A" ezfName="mtrLbNm_A" /></a></td>
											</tr> 
										<% } %>
										<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_B)) { %>
											<tr>
												<td>2</td>
												<td><a href="#Table_B"><ezf:label name="mtrLbNm_B" ezfName="mtrLbNm_B" /></a></td>
											</tr>
										<% } %>
										<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_C)) { %>
											<tr>
												<td>3</td>
												<td><a href="#Table_C"><ezf:label name="mtrLbNm_C" ezfName="mtrLbNm_C" /></a></td>
											</tr>
										<% } %>
										<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_D)) { %>
											<tr>
												<td>4</td>
												<td><a href="#Table_D"><ezf:label name="mtrLbNm_D" ezfName="mtrLbNm_D" /></a></td>
											</tr> 
										<% } %>
										<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_E)) { %>
											<tr>
												<td>5</td>
												<td><a href="#Table_E"><ezf:label name="mtrLbNm_E" ezfName="mtrLbNm_E" /></a></td>
											</tr> 
										<% } %>
										<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_F)) { %>
											<tr>
												<td>6</td>
												<td><a href="#Table_F"><ezf:label name="mtrLbNm_F" ezfName="mtrLbNm_F" /></a></td>
											</tr> 
										<% } %>
										<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_G)) { %>
											<tr>
												<td>7</td>
												<td><a href="#Table_G"><ezf:label name="mtrLbNm_G" ezfName="mtrLbNm_G" /></a></td>
											</tr> 
										<% } %>
										<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_H)) { %>
											<tr>
												<td>8</td>
												<td><a href="#Table_H"><ezf:label name="mtrLbNm_H" ezfName="mtrLbNm_H" /></a></td>
											</tr> 
										<% } %>
										<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_I)) { %>
											<tr>
												<td>9</td>
												<td><a href="#Table_I"><ezf:label name="mtrLbNm_I" ezfName="mtrLbNm_I" /></a></td>
											</tr> 
										<% } %>
										<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_J)) { %>
											<tr>
												<td>10</td>
												<td><a href="#Table_J"><ezf:label name="mtrLbNm_J" ezfName="mtrLbNm_J" /></a></td>
											</tr>
										<% } %>
										</table>
									</td>
								</tr>
							</table>
							<br />
						<% } %>
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_A)) { %>
								<table id="Table_A" border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="right">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_A" ezfName="mtrLbNm_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><a href="#MtrList">Back To Top</a></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:none; width:1030; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="50" align="center">		<!-- PE Seq# -->
														<col width="50" align="center">		<!-- TS Seq# -->
														<col width="60" align="center">		<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90" align="center">		<!-- Bill From Date -->
														<col width="90" align="center">		<!-- Bill To Date -->
														<col width="90" align="center">		<!-- Interface Date -->
														<col width="50" align="center">		<!-- Meter Fulfill -->
														<col width="80" align="center">		<!-- Schedule Status -->
														<col width="80" align="center">		<!-- Invoice# -->
														<col width="90" align="center">		<!-- Actual Invoice Date -->
														<col width="110" align="center">	<!-- Invoiced Amount -->
														<col width="100" align="center">	<!-- Tax Amount -->
														<tr>
															<td class="pClothBs">PE Seq#</td>
															<td class="pClothBs">TS Seq#</td>
															<td class="pClothBs">Schedule Seq#</td>
															<td class="pClothBs">Skip Type</td>
															<td class="pClothBs">Bill From Date</td>
															<td class="pClothBs">Bill To Date</td>
															<td class="pClothBs">Interface Date</td>
															<td class="pClothBs">Meter<br />Fulfill</td>
															<td class="pClothBs">Schedule<br />Status</td>
															<td class="pClothBs">Invoice#</td>
															<td class="pClothBs">Actual<br />Invoice Date</td>
															<td class="pClothBs">Invoiced Amount</td>
															<td class="pClothBs">Tax Amount</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_A" style="overflow-y:scroll; height:440; overflow-x:hidden; width:1049; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
														<col width="50">					<!-- PE Seq# -->
														<col width="50">					<!-- TS Seq# -->
														<col width="60">					<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90">					<!-- Bill From Date -->
														<col width="90">					<!-- Bill To Date -->
														<col width="90">					<!-- Interface Date -->
														<col width="50">					<!-- Meter Fulfill -->
														<col width="80">					<!-- Schedule Status -->
														<col width="80">					<!-- Invoice# -->
														<col width="90">					<!-- Actual Invoice Date -->
														<col width="110" align="right">		<!-- Invoiced Amount -->
														<col width="100" align="right">		<!-- Tax Amount -->
											<% int count = 0; %>
											<ezf:row ezfHyo="A">
														<tr ezfhyo="A" id="id_left_A_row{EZF_ROW_NUMBER}">
															<td><ezf:label name="dsContrPrcEffSqNum_A1" ezfName="dsContrPrcEffSqNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdLvlNum_A1" ezfName="dsContrBllgSchdLvlNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdSqNum_A1" ezfName="dsContrBllgSchdSqNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:select name="skipRecovTpCd_A3" ezfName="skipRecovTpCd_A3" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="skipRecovTpCd_H1" ezfDispName="skipRecovTpNm_H1" otherAttr=" style=\"width:85\""/></td>
															<td><ezf:label name="bllgSchdFromDt_A1" ezfName="bllgSchdFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="bllgSchdThruDt_A1" ezfName="bllgSchdThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="nextBllgDt_A1" ezfName="nextBllgDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="mtrEntryCpltFlg_A1" ezfName="mtrEntryCpltFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsBllgSchdStsDescTxt_A1" ezfName="dsBllgSchdStsDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(count).eipRptRqstPk_A1)) { %>
															<ezf:anchor name="svcInvNum_A1" ezfName="svcInvNum_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewPaperInvoiceA" otherAttr=" id=\"svcInvNum_A1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="svcInvNum_A1" ezfName="svcInvNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/></ezf:anchor>
															<% } else { %>
															<ezf:label name="svcInvNum_A1" ezfName="svcInvNum_A1" ezfHyo="A" ezfArrayNo="0" />
															<% } %>
															</td>
															<td><ezf:label name="invDt_A1" ezfName="invDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="baseActlPrcDealAmt_A1" ezfName="baseActlPrcDealAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="dealTaxAmt_A1" ezfName="dealTaxAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
															</td>
														</tr>
											<% count++; %>
											</ezf:row>
													<ezf:skip>
														<tr ezfhyo="A" id="id_left_A_row{EZF_ROW_NUMBER}">
															<td><label ezfout name="dsContrPrcEffSqNum_A1" ezfname="dsContrPrcEffSqNum_A1" ezfhyo="A" ezfArrayNo="0">1234</label></td>
															<td><label ezfout name="dsContrBllgSchdLvlNum_A1" ezfname="dsContrBllgSchdLvlNum_A1" ezfhyo="A" ezfArrayNo="0">1</label></td>
															<td><label ezfout name="dsContrBllgSchdSqNum_A1" ezfname="dsContrBllgSchdSqNum_A1" ezfhyo="A" ezfArrayNo="0">1</label></td>
															<td><select name="skipRecovTpCd_A3" ezfname="skipRecovTpCd_A3" ezflist="skipRecovTpCd_H1,skipRecovTpNm_H1,99, ,blank" ezfhyo="A" ezfArrayNo="0" style="width:85"><option>None</option><option>Skip</option><option>Recover</option></select></td>
															<td><label ezfout name="bllgSchdFromDt_A1" ezfname="bllgSchdFromDt_A1" ezfhyo="A" ezfArrayNo="0">01/01/2000</label></td>
															<td><label ezfout name="bllgSchdThruDt_A1" ezfname="bllgSchdThruDt_A1" ezfhyo="A" ezfArrayNo="0">12/31/2000</label></td>
															<td><label ezfout name="nextBllgDt_A1" ezfname="nextBllgDt_A1" ezfhyo="A" ezfArrayNo="0">06/31/2000</label></td>
															<td><label ezfout name="mtrEntryCpltFlg_A1" ezfname="mtrEntryCpltFlg_A1" ezfhyo="A" ezfArrayNo="0">Y</label></td>
															<td><label ezfout name="dsBllgSchdStsDescTxt_A1" ezfname="dsBllgSchdStsDescTxt_A1" ezfhyo="A" ezfArrayNo="0">Closed</label></td>
															<td><label ezfout name="svcInvNum_A1" ezfname="svcInvNum_A1" ezfhyo="A" ezfArrayNo="0">12345678</label></td>
															<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A" ezfArrayNo="0">06/31/2000</label></td>
															<td><label ezfout name="baseActlPrcDealAmt_A1" ezfname="baseActlPrcDealAmt_A1" ezfhyo="A" ezfArrayNo="0">100.00</label></td>
															<td><label ezfout name="dealTaxAmt_A1" ezfname="dealTaxAmt_A1" ezfhyo="A" ezfArrayNo="0">200.00</label></td>
														</tr>
														<tr ezfhyo="A" id="id_left_A_row{EZF_ROW_NUMBER}">
															<td><label ezfout name="dsContrPrcEffSqNum_A1" ezfname="dsContrPrcEffSqNum_A1" ezfhyo="A" ezfArrayNo="0">1234</label></td>
															<td><label ezfout name="dsContrBllgSchdLvlNum_A1" ezfname="dsContrBllgSchdLvlNum_A1" ezfhyo="A" ezfArrayNo="0">1</label></td>
															<td><label ezfout name="dsContrBllgSchdSqNum_A1" ezfname="dsContrBllgSchdSqNum_A1" ezfhyo="A" ezfArrayNo="0">1</label></td>
															<td><select name="skipRecovTpCd_A3" ezfname="skipRecovTpCd_A3" ezflist="skipRecovTpCd_H1,skipRecovTpNm_H1,99, ,blank" ezfhyo="A" ezfArrayNo="0" style="width:85"><option>None</option><option>Skip</option><option>Recover</option></select></td>
															<td><label ezfout name="bllgSchdFromDt_A1" ezfname="bllgSchdFromDt_A1" ezfhyo="A" ezfArrayNo="0">01/01/2000</label></td>
															<td><label ezfout name="bllgSchdThruDt_A1" ezfname="bllgSchdThruDt_A1" ezfhyo="A" ezfArrayNo="0">12/31/2000</label></td>
															<td><label ezfout name="nextBllgDt_A1" ezfname="nextBllgDt_A1" ezfhyo="A" ezfArrayNo="0">06/31/2000</label></td>
															<td><label ezfout name="mtrEntryCpltFlg_A1" ezfname="mtrEntryCpltFlg_A1" ezfhyo="A" ezfArrayNo="0">Y</label></td>
															<td><label ezfout name="dsBllgSchdStsDescTxt_A1" ezfname="dsBllgSchdStsDescTxt_A1" ezfhyo="A" ezfArrayNo="0">Closed</label></td>
															<td><label ezfout name="svcInvNum_A1" ezfname="svcInvNum_A1" ezfhyo="A" ezfArrayNo="0">12345678</label></td>
															<td><label ezfout name="invDt_A1" ezfname="invDt_A1" ezfhyo="A" ezfArrayNo="0">06/31/2000</label></td>
															<td><label ezfout name="baseActlPrcDealAmt_A1" ezfname="baseActlPrcDealAmt_A1" ezfhyo="A" ezfArrayNo="0">100.00</label></td>
															<td><label ezfout name="dealTaxAmt_A1" ezfname="dealTaxAmt_A1" ezfhyo="A" ezfArrayNo="0">200.00</label></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
						<% } %>
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_B)) { %>
								<table id="Table_B" border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="right">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_B" ezfName="mtrLbNm_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><a href="#MtrList">Back To Top</a></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_B_Top" style="overflow-x:none; overflow-y:none; width:1030; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="50" align="center">		<!-- PE Seq# -->
														<col width="50" align="center">		<!-- TS Seq# -->
														<col width="60" align="center">		<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90" align="center">		<!-- Bill From Date -->
														<col width="90" align="center">		<!-- Bill To Date -->
														<col width="90" align="center">		<!-- Interface Date -->
														<col width="50" align="center">		<!-- Meter Fulfill -->
														<col width="80" align="center">		<!-- Schedule Status -->
														<col width="80" align="center">		<!-- Invoice# -->
														<col width="90" align="center">		<!-- Actual Invoice Date -->
														<col width="110" align="center">	<!-- Invoiced Amount -->
														<col width="100" align="center">	<!-- Tax Amount -->
														<tr>
															<td class="pClothBs">PE Seq#</td>
															<td class="pClothBs">TS Seq#</td>
															<td class="pClothBs">Schedule Seq#</td>
															<td class="pClothBs">Skip Type</td>
															<td class="pClothBs">Bill From Date</td>
															<td class="pClothBs">Bill To Date</td>
															<td class="pClothBs">Interface Date</td>
															<td class="pClothBs">Meter<br />Fulfill</td>
															<td class="pClothBs">Schedule<br />Status</td>
															<td class="pClothBs">Invoice#</td>
															<td class="pClothBs">Actual<br />Invoice Date</td>
															<td class="pClothBs">Invoiced Amount</td>
															<td class="pClothBs">Tax Amount</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_B" style="overflow-y:scroll; height:440; overflow-x:hidden; width:1049; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed">
														<col width="50">					<!-- PE Seq# -->
														<col width="50">					<!-- TS Seq# -->
														<col width="60">					<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90">					<!-- Bill From Date -->
														<col width="90">					<!-- Bill To Date -->
														<col width="90">					<!-- Interface Date -->
														<col width="50">					<!-- Meter Fulfill -->
														<col width="80">					<!-- Schedule Status -->
														<col width="80">					<!-- Invoice# -->
														<col width="90">					<!-- Actual Invoice Date -->
														<col width="110" align="right">		<!-- Invoiced Amount -->
														<col width="100" align="right">		<!-- Tax Amount -->
											<% int count = 0; %>
											<ezf:row ezfHyo="B">
														<tr ezfhyo="B" id="id_left_B_row{EZF_ROW_NUMBER}">
															<td><ezf:label name="dsContrPrcEffSqNum_B1" ezfName="dsContrPrcEffSqNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdLvlNum_B1" ezfName="dsContrBllgSchdLvlNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdSqNum_B1" ezfName="dsContrBllgSchdSqNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:select name="skipRecovTpCd_B3" ezfName="skipRecovTpCd_B3" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="skipRecovTpCd_H1" ezfDispName="skipRecovTpNm_H1" otherAttr=" style=\"width:85\""/></td>
															<td><ezf:label name="bllgSchdFromDt_B1" ezfName="bllgSchdFromDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:label name="bllgSchdThruDt_B1" ezfName="bllgSchdThruDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:label name="nextBllgDt_B1" ezfName="nextBllgDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:label name="mtrEntryCpltFlg_B1" ezfName="mtrEntryCpltFlg_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsBllgSchdStsDescTxt_B1" ezfName="dsBllgSchdStsDescTxt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															<td>
															<% if (ZYPCommonFunc.hasValue(bMsg.B.no(count).eipRptRqstPk_B1)) { %>
															<ezf:anchor name="svcInvNum_B1" ezfName="svcInvNum_B1" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewPaperInvoiceB" otherAttr=" id=\"svcInvNum_B1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="svcInvNum_B1" ezfName="svcInvNum_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/></ezf:anchor>
															<% } else { %>
															<ezf:label name="svcInvNum_B1" ezfName="svcInvNum_B1" ezfHyo="B" ezfArrayNo="0" />
															<% } %>
															</td>
															<td><ezf:label name="invDt_B1" ezfName="invDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:label name="baseActlPrcDealAmt_B1" ezfName="baseActlPrcDealAmt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:label name="dealTaxAmt_B1" ezfName="dealTaxAmt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_B1" ezfName="xxRecHistCratTs_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_B1" ezfName="xxRecHistCratByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_B1" ezfName="xxRecHistUpdTs_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_B1" ezfName="xxRecHistUpdByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_B1" ezfName="xxRecHistTblNm_B1" ezfHyo="B" ezfArrayNo="0" />
															</td>
														</tr>
											<% count++; %>
											</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
						<% } %>
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_C)) { %>
								<table id="Table_C" border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="right">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_C" ezfName="mtrLbNm_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><a href="#MtrList">Back To Top</a></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_C_Top" style="overflow-x:none; overflow-y:none; width:1030; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="50" align="center">		<!-- PE Seq# -->
														<col width="50" align="center">		<!-- TS Seq# -->
														<col width="60" align="center">		<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90" align="center">		<!-- Bill From Date -->
														<col width="90" align="center">		<!-- Bill To Date -->
														<col width="90" align="center">		<!-- Interface Date -->
														<col width="50" align="center">		<!-- Meter Fulfill -->
														<col width="80" align="center">		<!-- Schedule Status -->
														<col width="80" align="center">		<!-- Invoice# -->
														<col width="90" align="center">		<!-- Actual Invoice Date -->
														<col width="110" align="center">	<!-- Invoiced Amount -->
														<col width="100" align="center">	<!-- Tax Amount -->
														<tr>
															<td class="pClothBs">PE Seq#</td>
															<td class="pClothBs">TS Seq#</td>
															<td class="pClothBs">Schedule Seq#</td>
															<td class="pClothBs">Skip Type</td>
															<td class="pClothBs">Bill From Date</td>
															<td class="pClothBs">Bill To Date</td>
															<td class="pClothBs">Interface Date</td>
															<td class="pClothBs">Meter<br />Fulfill</td>
															<td class="pClothBs">Schedule<br />Status</td>
															<td class="pClothBs">Invoice#</td>
															<td class="pClothBs">Actual<br />Invoice Date</td>
															<td class="pClothBs">Invoiced Amount</td>
															<td class="pClothBs">Tax Amount</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_C" style="overflow-y:scroll; height:440; overflow-x:hidden; width:1049; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="C" style="table-layout: fixed">
														<col width="50">					<!-- PE Seq# -->
														<col width="50">					<!-- TS Seq# -->
														<col width="60">					<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90">					<!-- Bill From Date -->
														<col width="90">					<!-- Bill To Date -->
														<col width="90">					<!-- Interface Date -->
														<col width="50">					<!-- Meter Fulfill -->
														<col width="80">					<!-- Schedule Status -->
														<col width="80">					<!-- Invoice# -->
														<col width="90">					<!-- Actual Invoice Date -->
														<col width="110" align="right">		<!-- Invoiced Amount -->
														<col width="100" align="right">		<!-- Tax Amount -->
											<% int count = 0; %>
											<ezf:row ezfHyo="C">
														<tr ezfhyo="C" id="id_left_C_row{EZF_ROW_NUMBER}">
															<td><ezf:label name="dsContrPrcEffSqNum_C1" ezfName="dsContrPrcEffSqNum_C1" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdLvlNum_C1" ezfName="dsContrBllgSchdLvlNum_C1" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdSqNum_C1" ezfName="dsContrBllgSchdSqNum_C1" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:select name="skipRecovTpCd_C3" ezfName="skipRecovTpCd_C3" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="skipRecovTpCd_H1" ezfDispName="skipRecovTpNm_H1" otherAttr=" style=\"width:85\""/></td>
															<td><ezf:label name="bllgSchdFromDt_C1" ezfName="bllgSchdFromDt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:label name="bllgSchdThruDt_C1" ezfName="bllgSchdThruDt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:label name="nextBllgDt_C1" ezfName="nextBllgDt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:label name="mtrEntryCpltFlg_C1" ezfName="mtrEntryCpltFlg_C1" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsBllgSchdStsDescTxt_C1" ezfName="dsBllgSchdStsDescTxt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
															<td>
															<% if (ZYPCommonFunc.hasValue(bMsg.C.no(count).eipRptRqstPk_C1)) { %>
															<ezf:anchor name="svcInvNum_C1" ezfName="svcInvNum_C1" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewPaperInvoiceC" otherAttr=" id=\"svcInvNum_C1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="svcInvNum_C1" ezfName="svcInvNum_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/></ezf:anchor>
															<% } else { %>
															<ezf:label name="svcInvNum_C1" ezfName="svcInvNum_C1" ezfHyo="C" ezfArrayNo="0" />
															<% } %>
															</td>
															<td><ezf:label name="invDt_C1" ezfName="invDt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:label name="baseActlPrcDealAmt_C1" ezfName="baseActlPrcDealAmt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
															<td><ezf:label name="dealTaxAmt_C1" ezfName="dealTaxAmt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_C1" ezfName="xxRecHistCratTs_C1" ezfHyo="C" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_C1" ezfName="xxRecHistCratByNm_C1" ezfHyo="C" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_C1" ezfName="xxRecHistUpdTs_C1" ezfHyo="C" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_C1" ezfName="xxRecHistUpdByNm_C1" ezfHyo="C" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_C1" ezfName="xxRecHistTblNm_C1" ezfHyo="C" ezfArrayNo="0" />
															</td>
														</tr>
											<% count++; %>
											</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
						<% } %>
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_D)) { %>
								<table id="Table_D" border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="right">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_D" ezfName="mtrLbNm_D" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><a href="#MtrList">Back To Top</a></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_D_Top" style="overflow-x:none; overflow-y:none; width:1030; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="50" align="center">		<!-- PE Seq# -->
														<col width="50" align="center">		<!-- TS Seq# -->
														<col width="60" align="center">		<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90" align="center">		<!-- Bill From Date -->
														<col width="90" align="center">		<!-- Bill To Date -->
														<col width="90" align="center">		<!-- Interface Date -->
														<col width="50" align="center">		<!-- Meter Fulfill -->
														<col width="80" align="center">		<!-- Schedule Status -->
														<col width="80" align="center">		<!-- Invoice# -->
														<col width="90" align="center">		<!-- Actual Invoice Date -->
														<col width="110" align="center">	<!-- Invoiced Amount -->
														<col width="100" align="center">	<!-- Tax Amount -->
														<tr>
															<td class="pClothBs">PE Seq#</td>
															<td class="pClothBs">TS Seq#</td>
															<td class="pClothBs">Schedule Seq#</td>
															<td class="pClothBs">Skip Type</td>
															<td class="pClothBs">Bill From Date</td>
															<td class="pClothBs">Bill To Date</td>
															<td class="pClothBs">Interface Date</td>
															<td class="pClothBs">Meter<br />Fulfill</td>
															<td class="pClothBs">Schedule<br />Status</td>
															<td class="pClothBs">Invoice#</td>
															<td class="pClothBs">Actual<br />Invoice Date</td>
															<td class="pClothBs">Invoiced Amount</td>
															<td class="pClothBs">Tax Amount</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_D" style="overflow-y:scroll; height:440; overflow-x:hidden; width:1049; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="D" style="table-layout: fixed">
														<col width="50">					<!-- PE Seq# -->
														<col width="50">					<!-- TS Seq# -->
														<col width="60">					<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90">					<!-- Bill From Date -->
														<col width="90">					<!-- Bill To Date -->
														<col width="90">					<!-- Interface Date -->
														<col width="50">					<!-- Meter Fulfill -->
														<col width="80">					<!-- Schedule Status -->
														<col width="80">					<!-- Invoice# -->
														<col width="90">					<!-- Actual Invoice Date -->
														<col width="110" align="right">		<!-- Invoiced Amount -->
														<col width="100" align="right">		<!-- Tax Amount -->
											<% int count = 0; %>
											<ezf:row ezfHyo="D">
														<tr ezfhyo="D" id="id_left_D_row{EZF_ROW_NUMBER}">
															<td><ezf:label name="dsContrPrcEffSqNum_D1" ezfName="dsContrPrcEffSqNum_D1" ezfHyo="D" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdLvlNum_D1" ezfName="dsContrBllgSchdLvlNum_D1" ezfHyo="D" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdSqNum_D1" ezfName="dsContrBllgSchdSqNum_D1" ezfHyo="D" ezfArrayNo="0" /></td>
															<td><ezf:select name="skipRecovTpCd_D3" ezfName="skipRecovTpCd_D3" ezfHyo="D" ezfArrayNo="0" ezfBlank="1" ezfCodeName="skipRecovTpCd_H1" ezfDispName="skipRecovTpNm_H1" otherAttr=" style=\"width:85\""/></td>
															<td><ezf:label name="bllgSchdFromDt_D1" ezfName="bllgSchdFromDt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
															<td><ezf:label name="bllgSchdThruDt_D1" ezfName="bllgSchdThruDt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
															<td><ezf:label name="nextBllgDt_D1" ezfName="nextBllgDt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
															<td><ezf:label name="mtrEntryCpltFlg_D1" ezfName="mtrEntryCpltFlg_D1" ezfHyo="D" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsBllgSchdStsDescTxt_D1" ezfName="dsBllgSchdStsDescTxt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
															<td>
															<% if (ZYPCommonFunc.hasValue(bMsg.D.no(count).eipRptRqstPk_D1)) { %>
															<ezf:anchor name="svcInvNum_D1" ezfName="svcInvNum_D1" ezfHyo="D" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewPaperInvoiceD" otherAttr=" id=\"svcInvNum_D1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="svcInvNum_D1" ezfName="svcInvNum_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/></ezf:anchor>
															<% } else { %>
															<ezf:label name="svcInvNum_D1" ezfName="svcInvNum_D1" ezfHyo="D" ezfArrayNo="0" />
															<% } %>
															</td>
															<td><ezf:label name="invDt_D1" ezfName="invDt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
															<td><ezf:label name="baseActlPrcDealAmt_D1" ezfName="baseActlPrcDealAmt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
															<td><ezf:label name="dealTaxAmt_D1" ezfName="dealTaxAmt_D1" ezfHyo="D" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_D1" ezfName="xxRecHistCratTs_D1" ezfHyo="D" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_D1" ezfName="xxRecHistCratByNm_D1" ezfHyo="D" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_D1" ezfName="xxRecHistUpdTs_D1" ezfHyo="D" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_D1" ezfName="xxRecHistUpdByNm_D1" ezfHyo="D" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_D1" ezfName="xxRecHistTblNm_D1" ezfHyo="D" ezfArrayNo="0" />
															</td>
														</tr>
											<% count++; %>
											</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
						<% } %>
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_E)) { %>
								<table id="Table_E" border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="right">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_E" ezfName="mtrLbNm_E" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><a href="#MtrList">Back To Top</a></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_E_Top" style="overflow-x:none; overflow-y:none; width:1030; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="50" align="center">		<!-- PE Seq# -->
														<col width="50" align="center">		<!-- TS Seq# -->
														<col width="60" align="center">		<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90" align="center">		<!-- Bill From Date -->
														<col width="90" align="center">		<!-- Bill To Date -->
														<col width="90" align="center">		<!-- Interface Date -->
														<col width="50" align="center">		<!-- Meter Fulfill -->
														<col width="80" align="center">		<!-- Schedule Status -->
														<col width="80" align="center">		<!-- Invoice# -->
														<col width="90" align="center">		<!-- Actual Invoice Date -->
														<col width="110" align="center">	<!-- Invoiced Amount -->
														<col width="100" align="center">	<!-- Tax Amount -->
														<tr>
															<td class="pClothBs">PE Seq#</td>
															<td class="pClothBs">TS Seq#</td>
															<td class="pClothBs">Schedule Seq#</td>
															<td class="pClothBs">Skip Type</td>
															<td class="pClothBs">Bill From Date</td>
															<td class="pClothBs">Bill To Date</td>
															<td class="pClothBs">Interface Date</td>
															<td class="pClothBs">Meter<br />Fulfill</td>
															<td class="pClothBs">Schedule<br />Status</td>
															<td class="pClothBs">Invoice#</td>
															<td class="pClothBs">Actual<br />Invoice Date</td>
															<td class="pClothBs">Invoiced Amount</td>
															<td class="pClothBs">Tax Amount</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_E" style="overflow-y:scroll; height:440; overflow-x:hidden; width:1049; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="E" style="table-layout: fixed">
														<col width="50">					<!-- PE Seq# -->
														<col width="50">					<!-- TS Seq# -->
														<col width="60">					<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90">					<!-- Bill From Date -->
														<col width="90">					<!-- Bill To Date -->
														<col width="90">					<!-- Interface Date -->
														<col width="50">					<!-- Meter Fulfill -->
														<col width="80">					<!-- Schedule Status -->
														<col width="80">					<!-- Invoice# -->
														<col width="90">					<!-- Actual Invoice Date -->
														<col width="110" align="right">		<!-- Invoiced Amount -->
														<col width="100" align="right">		<!-- Tax Amount -->
											<% int count = 0; %>
											<ezf:row ezfHyo="E">
														<tr ezfhyo="E" id="id_left_E_row{EZF_ROW_NUMBER}">
															<td><ezf:label name="dsContrPrcEffSqNum_E1" ezfName="dsContrPrcEffSqNum_E1" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdLvlNum_E1" ezfName="dsContrBllgSchdLvlNum_E1" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdSqNum_E1" ezfName="dsContrBllgSchdSqNum_E1" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:select name="skipRecovTpCd_E3" ezfName="skipRecovTpCd_E3" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="skipRecovTpCd_H1" ezfDispName="skipRecovTpNm_H1" otherAttr=" style=\"width:85\""/></td>
															<td><ezf:label name="bllgSchdFromDt_E1" ezfName="bllgSchdFromDt_E1" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:label name="bllgSchdThruDt_E1" ezfName="bllgSchdThruDt_E1" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:label name="nextBllgDt_E1" ezfName="nextBllgDt_E1" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:label name="mtrEntryCpltFlg_E1" ezfName="mtrEntryCpltFlg_E1" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsBllgSchdStsDescTxt_E1" ezfName="dsBllgSchdStsDescTxt_E1" ezfHyo="E" ezfArrayNo="0" /></td>
															<td>
															<% if (ZYPCommonFunc.hasValue(bMsg.E.no(count).eipRptRqstPk_E1)) { %>
															<ezf:anchor name="svcInvNum_E1" ezfName="svcInvNum_E1" ezfHyo="E" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewPaperInvoiceE" otherAttr=" id=\"svcInvNum_E1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="svcInvNum_E1" ezfName="svcInvNum_E1" ezfHyo="E" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/></ezf:anchor>
															<% } else { %>
															<ezf:label name="svcInvNum_E1" ezfName="svcInvNum_E1" ezfHyo="E" ezfArrayNo="0" />
															<% } %>
															</td>
															<td><ezf:label name="invDt_E1" ezfName="invDt_E1" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:label name="baseActlPrcDealAmt_E1" ezfName="baseActlPrcDealAmt_E1" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:label name="dealTaxAmt_E1" ezfName="dealTaxAmt_E1" ezfHyo="E" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_E1" ezfName="xxRecHistCratTs_E1" ezfHyo="E" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_E1" ezfName="xxRecHistCratByNm_E1" ezfHyo="E" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_E1" ezfName="xxRecHistUpdTs_E1" ezfHyo="E" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_E1" ezfName="xxRecHistUpdByNm_E1" ezfHyo="E" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_E1" ezfName="xxRecHistTblNm_E1" ezfHyo="E" ezfArrayNo="0" />
															</td>
														</tr>
											<% count++; %>
											</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
						<% } %>
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_F)) { %>
								<table id="Table_F" border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="right">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_F" ezfName="mtrLbNm_F" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><a href="#MtrList">Back To Top</a></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_F_Top" style="overflow-x:none; overflow-y:none; width:1030; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="50" align="center">		<!-- PE Seq# -->
														<col width="50" align="center">		<!-- TS Seq# -->
														<col width="60" align="center">		<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90" align="center">		<!-- Bill From Date -->
														<col width="90" align="center">		<!-- Bill To Date -->
														<col width="90" align="center">		<!-- Interface Date -->
														<col width="50" align="center">		<!-- Meter Fulfill -->
														<col width="80" align="center">		<!-- Schedule Status -->
														<col width="80" align="center">		<!-- Invoice# -->
														<col width="90" align="center">		<!-- Actual Invoice Date -->
														<col width="110" align="center">	<!-- Invoiced Amount -->
														<col width="100" align="center">	<!-- Tax Amount -->
														<tr>
															<td class="pClothBs">PE Seq#</td>
															<td class="pClothBs">TS Seq#</td>
															<td class="pClothBs">Schedule Seq#</td>
															<td class="pClothBs">Skip Type</td>
															<td class="pClothBs">Bill From Date</td>
															<td class="pClothBs">Bill To Date</td>
															<td class="pClothBs">Interface Date</td>
															<td class="pClothBs">Meter<br />Fulfill</td>
															<td class="pClothBs">Schedule<br />Status</td>
															<td class="pClothBs">Invoice#</td>
															<td class="pClothBs">Actual<br />Invoice Date</td>
															<td class="pClothBs">Invoiced Amount</td>
															<td class="pClothBs">Tax Amount</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_F" style="overflow-y:scroll; height:440; overflow-x:hidden; width:1049; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="F" style="table-layout: fixed">
														<col width="50">					<!-- PE Seq# -->
														<col width="50">					<!-- TS Seq# -->
														<col width="60">					<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90">					<!-- Bill From Date -->
														<col width="90">					<!-- Bill To Date -->
														<col width="90">					<!-- Interface Date -->
														<col width="50">					<!-- Meter Fulfill -->
														<col width="80">					<!-- Schedule Status -->
														<col width="80">					<!-- Invoice# -->
														<col width="90">					<!-- Actual Invoice Date -->
														<col width="110" align="right">		<!-- Invoiced Amount -->
														<col width="100" align="right">		<!-- Tax Amount -->
											<% int count = 0; %>
											<ezf:row ezfHyo="F">
														<tr ezfhyo="F" id="id_left_F_row{EZF_ROW_NUMBER}">
															<td><ezf:label name="dsContrPrcEffSqNum_F1" ezfName="dsContrPrcEffSqNum_F1" ezfHyo="F" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdLvlNum_F1" ezfName="dsContrBllgSchdLvlNum_F1" ezfHyo="F" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdSqNum_F1" ezfName="dsContrBllgSchdSqNum_F1" ezfHyo="F" ezfArrayNo="0" /></td>
															<td><ezf:select name="skipRecovTpCd_F3" ezfName="skipRecovTpCd_F3" ezfHyo="F" ezfArrayNo="0" ezfBlank="1" ezfCodeName="skipRecovTpCd_H1" ezfDispName="skipRecovTpNm_H1" otherAttr=" style=\"width:85\""/></td>
															<td><ezf:label name="bllgSchdFromDt_F1" ezfName="bllgSchdFromDt_F1" ezfHyo="F" ezfArrayNo="0" /></td>
															<td><ezf:label name="bllgSchdThruDt_F1" ezfName="bllgSchdThruDt_F1" ezfHyo="F" ezfArrayNo="0" /></td>
															<td><ezf:label name="nextBllgDt_F1" ezfName="nextBllgDt_F1" ezfHyo="F" ezfArrayNo="0" /></td>
															<td><ezf:label name="mtrEntryCpltFlg_F1" ezfName="mtrEntryCpltFlg_F1" ezfHyo="F" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsBllgSchdStsDescTxt_F1" ezfName="dsBllgSchdStsDescTxt_F1" ezfHyo="F" ezfArrayNo="0" /></td>
															<td>
															<% if (ZYPCommonFunc.hasValue(bMsg.F.no(count).eipRptRqstPk_F1)) { %>
															<ezf:anchor name="svcInvNum_F1" ezfName="svcInvNum_F1" ezfHyo="F" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewPaperInvoiceF" otherAttr=" id=\"svcInvNum_F1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="svcInvNum_F1" ezfName="svcInvNum_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/></ezf:anchor>
															<% } else { %>
															<ezf:label name="svcInvNum_F1" ezfName="svcInvNum_F1" ezfHyo="F" ezfArrayNo="0" />
															<% } %>
															</td>
															<td><ezf:label name="invDt_F1" ezfName="invDt_F1" ezfHyo="F" ezfArrayNo="0" /></td>
															<td><ezf:label name="baseActlPrcDealAmt_F1" ezfName="baseActlPrcDealAmt_F1" ezfHyo="F" ezfArrayNo="0" /></td>
															<td><ezf:label name="dealTaxAmt_F1" ezfName="dealTaxAmt_F1" ezfHyo="F" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_F1" ezfName="xxRecHistCratTs_F1" ezfHyo="F" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_F1" ezfName="xxRecHistCratByNm_F1" ezfHyo="F" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_F1" ezfName="xxRecHistUpdTs_F1" ezfHyo="F" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_F1" ezfName="xxRecHistUpdByNm_F1" ezfHyo="F" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_F1" ezfName="xxRecHistTblNm_F1" ezfHyo="F" ezfArrayNo="0" />
															</td>
														</tr>
											<% count++; %>
											</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
						<% } %>
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_G)) { %>
								<table id="Table_G" border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="right">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_G" ezfName="mtrLbNm_G" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><a href="#MtrList">Back To Top</a></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_G_Top" style="overflow-x:none; overflow-y:none; width:1030; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="50" align="center">		<!-- PE Seq# -->
														<col width="50" align="center">		<!-- TS Seq# -->
														<col width="60" align="center">		<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90" align="center">		<!-- Bill From Date -->
														<col width="90" align="center">		<!-- Bill To Date -->
														<col width="90" align="center">		<!-- Interface Date -->
														<col width="50" align="center">		<!-- Meter Fulfill -->
														<col width="80" align="center">		<!-- Schedule Status -->
														<col width="80" align="center">		<!-- Invoice# -->
														<col width="90" align="center">		<!-- Actual Invoice Date -->
														<col width="110" align="center">	<!-- Invoiced Amount -->
														<col width="100" align="center">	<!-- Tax Amount -->
														<tr>
															<td class="pClothBs">PE Seq#</td>
															<td class="pClothBs">TS Seq#</td>
															<td class="pClothBs">Schedule Seq#</td>
															<td class="pClothBs">Skip Type</td>
															<td class="pClothBs">Bill From Date</td>
															<td class="pClothBs">Bill To Date</td>
															<td class="pClothBs">Interface Date</td>
															<td class="pClothBs">Meter<br />Fulfill</td>
															<td class="pClothBs">Schedule<br />Status</td>
															<td class="pClothBs">Invoice#</td>
															<td class="pClothBs">Actual<br />Invoice Date</td>
															<td class="pClothBs">Invoiced Amount</td>
															<td class="pClothBs">Tax Amount</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_G" style="overflow-y:scroll; height:440; overflow-x:hidden; width:1049; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="G" style="table-layout: fixed">
														<col width="50">					<!-- PE Seq# -->
														<col width="50">					<!-- TS Seq# -->
														<col width="60">					<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90">					<!-- Bill From Date -->
														<col width="90">					<!-- Bill To Date -->
														<col width="90">					<!-- Interface Date -->
														<col width="50">					<!-- Meter Fulfill -->
														<col width="80">					<!-- Schedule Status -->
														<col width="80">					<!-- Invoice# -->
														<col width="90">					<!-- Actual Invoice Date -->
														<col width="110" align="right">		<!-- Invoiced Amount -->
														<col width="100" align="right">		<!-- Tax Amount -->
											<% int count = 0; %>
											<ezf:row ezfHyo="G">
														<tr ezfhyo="G" id="id_left_G_row{EZF_ROW_NUMBER}">
															<td><ezf:label name="dsContrPrcEffSqNum_G1" ezfName="dsContrPrcEffSqNum_G1" ezfHyo="G" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdLvlNum_G1" ezfName="dsContrBllgSchdLvlNum_G1" ezfHyo="G" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdSqNum_G1" ezfName="dsContrBllgSchdSqNum_G1" ezfHyo="G" ezfArrayNo="0" /></td>
															<td><ezf:select name="skipRecovTpCd_G3" ezfName="skipRecovTpCd_G3" ezfHyo="G" ezfArrayNo="0" ezfBlank="1" ezfCodeName="skipRecovTpCd_H1" ezfDispName="skipRecovTpNm_H1" otherAttr=" style=\"width:85\""/></td>
															<td><ezf:label name="bllgSchdFromDt_G1" ezfName="bllgSchdFromDt_G1" ezfHyo="G" ezfArrayNo="0" /></td>
															<td><ezf:label name="bllgSchdThruDt_G1" ezfName="bllgSchdThruDt_G1" ezfHyo="G" ezfArrayNo="0" /></td>
															<td><ezf:label name="nextBllgDt_G1" ezfName="nextBllgDt_G1" ezfHyo="G" ezfArrayNo="0" /></td>
															<td><ezf:label name="mtrEntryCpltFlg_G1" ezfName="mtrEntryCpltFlg_G1" ezfHyo="G" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsBllgSchdStsDescTxt_G1" ezfName="dsBllgSchdStsDescTxt_G1" ezfHyo="G" ezfArrayNo="0" /></td>
															<td>
															<% if (ZYPCommonFunc.hasValue(bMsg.G.no(count).eipRptRqstPk_G1)) { %>
															<ezf:anchor name="svcInvNum_G1" ezfName="svcInvNum_G1" ezfHyo="G" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewPaperInvoiceG" otherAttr=" id=\"svcInvNum_G1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="svcInvNum_G1" ezfName="svcInvNum_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/></ezf:anchor>
															<% } else { %>
															<ezf:label name="svcInvNum_G1" ezfName="svcInvNum_G1" ezfHyo="G" ezfArrayNo="0" />
															<% } %>
															</td>
															<td><ezf:label name="invDt_G1" ezfName="invDt_G1" ezfHyo="G" ezfArrayNo="0" /></td>
															<td><ezf:label name="baseActlPrcDealAmt_G1" ezfName="baseActlPrcDealAmt_G1" ezfHyo="G" ezfArrayNo="0" /></td>
															<td><ezf:label name="dealTaxAmt_G1" ezfName="dealTaxAmt_G1" ezfHyo="G" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_G1" ezfName="xxRecHistCratTs_G1" ezfHyo="G" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_G1" ezfName="xxRecHistCratByNm_G1" ezfHyo="G" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_G1" ezfName="xxRecHistUpdTs_G1" ezfHyo="G" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_G1" ezfName="xxRecHistUpdByNm_G1" ezfHyo="G" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_G1" ezfName="xxRecHistTblNm_G1" ezfHyo="G" ezfArrayNo="0" />
															</td>
														</tr>
											<% count++; %>
											</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
						<% } %>
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_H)) { %>
								<table id="Table_H" border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="right">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_H" ezfName="mtrLbNm_H" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><a href="#MtrList">Back To Top</a></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_H_Top" style="overflow-x:none; overflow-y:none; width:1030; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="50" align="center">		<!-- PE Seq# -->
														<col width="50" align="center">		<!-- TS Seq# -->
														<col width="60" align="center">		<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90" align="center">		<!-- Bill From Date -->
														<col width="90" align="center">		<!-- Bill To Date -->
														<col width="90" align="center">		<!-- Interface Date -->
														<col width="50" align="center">		<!-- Meter Fulfill -->
														<col width="80" align="center">		<!-- Schedule Status -->
														<col width="80" align="center">		<!-- Invoice# -->
														<col width="90" align="center">		<!-- Actual Invoice Date -->
														<col width="110" align="center">	<!-- Invoiced Amount -->
														<col width="100" align="center">	<!-- Tax Amount -->
														<tr>
															<td class="pClothBs">PE Seq#</td>
															<td class="pClothBs">TS Seq#</td>
															<td class="pClothBs">Schedule Seq#</td>
															<td class="pClothBs">Skip Type</td>
															<td class="pClothBs">Bill From Date</td>
															<td class="pClothBs">Bill To Date</td>
															<td class="pClothBs">Interface Date</td>
															<td class="pClothBs">Meter<br />Fulfill</td>
															<td class="pClothBs">Schedule<br />Status</td>
															<td class="pClothBs">Invoice#</td>
															<td class="pClothBs">Actual<br />Invoice Date</td>
															<td class="pClothBs">Invoiced Amount</td>
															<td class="pClothBs">Tax Amount</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_H" style="overflow-y:scroll; height:440; overflow-x:hidden; width:1049; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="H" style="table-layout: fixed">
														<col width="50">					<!-- PE Seq# -->
														<col width="50">					<!-- TS Seq# -->
														<col width="60">					<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90">					<!-- Bill From Date -->
														<col width="90">					<!-- Bill To Date -->
														<col width="90">					<!-- Interface Date -->
														<col width="50">					<!-- Meter Fulfill -->
														<col width="80">					<!-- Schedule Status -->
														<col width="80">					<!-- Invoice# -->
														<col width="90">					<!-- Actual Invoice Date -->
														<col width="110" align="right">		<!-- Invoiced Amount -->
														<col width="100" align="right">		<!-- Tax Amount -->
											<% int count = 0; %>
											<ezf:row ezfHyo="H">
														<tr ezfhyo="H" id="id_left_H_row{EZF_ROW_NUMBER}">
															<td><ezf:label name="dsContrPrcEffSqNum_H1" ezfName="dsContrPrcEffSqNum_H1" ezfHyo="H" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdLvlNum_H1" ezfName="dsContrBllgSchdLvlNum_H1" ezfHyo="H" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdSqNum_H1" ezfName="dsContrBllgSchdSqNum_H1" ezfHyo="H" ezfArrayNo="0" /></td>
															<td><ezf:select name="skipRecovTpCd_H3" ezfName="skipRecovTpCd_H3" ezfHyo="H" ezfArrayNo="0" ezfBlank="1" ezfCodeName="skipRecovTpCd_H1" ezfDispName="skipRecovTpNm_H1" otherAttr=" style=\"width:85\""/></td>
															<td><ezf:label name="bllgSchdFromDt_H1" ezfName="bllgSchdFromDt_H1" ezfHyo="H" ezfArrayNo="0" /></td>
															<td><ezf:label name="bllgSchdThruDt_H1" ezfName="bllgSchdThruDt_H1" ezfHyo="H" ezfArrayNo="0" /></td>
															<td><ezf:label name="nextBllgDt_H1" ezfName="nextBllgDt_H1" ezfHyo="H" ezfArrayNo="0" /></td>
															<td><ezf:label name="mtrEntryCpltFlg_H1" ezfName="mtrEntryCpltFlg_H1" ezfHyo="H" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsBllgSchdStsDescTxt_H1" ezfName="dsBllgSchdStsDescTxt_H1" ezfHyo="H" ezfArrayNo="0" /></td>
															<td>
															<% if (ZYPCommonFunc.hasValue(bMsg.H.no(count).eipRptRqstPk_H1)) { %>
															<ezf:anchor name="svcInvNum_H1" ezfName="svcInvNum_H1" ezfHyo="H" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewPaperInvoiceH" otherAttr=" id=\"svcInvNum_H1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="svcInvNum_H1" ezfName="svcInvNum_H1" ezfHyo="H" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/></ezf:anchor>
															<% } else { %>
															<ezf:label name="svcInvNum_H1" ezfName="svcInvNum_H1" ezfHyo="H" ezfArrayNo="0" />
															<% } %>
															</td>
															<td><ezf:label name="invDt_H1" ezfName="invDt_H1" ezfHyo="H" ezfArrayNo="0" /></td>
															<td><ezf:label name="baseActlPrcDealAmt_H1" ezfName="baseActlPrcDealAmt_H1" ezfHyo="H" ezfArrayNo="0" /></td>
															<td><ezf:label name="dealTaxAmt_H1" ezfName="dealTaxAmt_H1" ezfHyo="H" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_H1" ezfName="xxRecHistCratTs_H1" ezfHyo="H" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_H1" ezfName="xxRecHistCratByNm_H1" ezfHyo="H" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_H1" ezfName="xxRecHistUpdTs_H1" ezfHyo="H" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_H1" ezfName="xxRecHistUpdByNm_H1" ezfHyo="H" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_H1" ezfName="xxRecHistTblNm_H1" ezfHyo="H" ezfArrayNo="0" />
															</td>
														</tr>
											<% count++; %>
											</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
						<% } %>
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_I)) { %>
								<table id="Table_I" border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="right">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_I" ezfName="mtrLbNm_I" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><a href="#MtrList">Back To Top</a></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_I_Top" style="overflow-x:none; overflow-y:none; width:1030; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="50" align="center">		<!-- PE Seq# -->
														<col width="50" align="center">		<!-- TS Seq# -->
														<col width="60" align="center">		<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90" align="center">		<!-- Bill From Date -->
														<col width="90" align="center">		<!-- Bill To Date -->
														<col width="90" align="center">		<!-- Interface Date -->
														<col width="50" align="center">		<!-- Meter Fulfill -->
														<col width="80" align="center">		<!-- Schedule Status -->
														<col width="80" align="center">		<!-- Invoice# -->
														<col width="90" align="center">		<!-- Actual Invoice Date -->
														<col width="110" align="center">	<!-- Invoiced Amount -->
														<col width="100" align="center">	<!-- Tax Amount -->
														<tr>
															<td class="pClothBs">PE Seq#</td>
															<td class="pClothBs">TS Seq#</td>
															<td class="pClothBs">Schedule Seq#</td>
															<td class="pClothBs">Skip Type</td>
															<td class="pClothBs">Bill From Date</td>
															<td class="pClothBs">Bill To Date</td>
															<td class="pClothBs">Interface Date</td>
															<td class="pClothBs">Meter<br />Fulfill</td>
															<td class="pClothBs">Schedule<br />Status</td>
															<td class="pClothBs">Invoice#</td>
															<td class="pClothBs">Actual<br />Invoice Date</td>
															<td class="pClothBs">Invoiced Amount</td>
															<td class="pClothBs">Tax Amount</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_I" style="overflow-y:scroll; height:440; overflow-x:hidden; width:1049; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="I" style="table-layout: fixed">
														<col width="50">					<!-- PE Seq# -->
														<col width="50">					<!-- TS Seq# -->
														<col width="60">					<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90">					<!-- Bill From Date -->
														<col width="90">					<!-- Bill To Date -->
														<col width="90">					<!-- Interface Date -->
														<col width="50">					<!-- Meter Fulfill -->
														<col width="80">					<!-- Schedule Status -->
														<col width="80">					<!-- Invoice# -->
														<col width="90">					<!-- Actual Invoice Date -->
														<col width="110" align="right">		<!-- Invoiced Amount -->
														<col width="100" align="right">		<!-- Tax Amount -->
											<% int count = 0; %>
											<ezf:row ezfHyo="I">
														<tr ezfhyo="I" id="id_left_I_row{EZF_ROW_NUMBER}">
															<td><ezf:label name="dsContrPrcEffSqNum_I1" ezfName="dsContrPrcEffSqNum_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdLvlNum_I1" ezfName="dsContrBllgSchdLvlNum_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdSqNum_I1" ezfName="dsContrBllgSchdSqNum_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:select name="skipRecovTpCd_I3" ezfName="skipRecovTpCd_I3" ezfHyo="I" ezfArrayNo="0" ezfBlank="1" ezfCodeName="skipRecovTpCd_H1" ezfDispName="skipRecovTpNm_H1" otherAttr=" style=\"width:85\""/></td>
															<td><ezf:label name="bllgSchdFromDt_I1" ezfName="bllgSchdFromDt_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:label name="bllgSchdThruDt_I1" ezfName="bllgSchdThruDt_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:label name="nextBllgDt_I1" ezfName="nextBllgDt_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:label name="mtrEntryCpltFlg_I1" ezfName="mtrEntryCpltFlg_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsBllgSchdStsDescTxt_I1" ezfName="dsBllgSchdStsDescTxt_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td>
															<% if (ZYPCommonFunc.hasValue(bMsg.I.no(count).eipRptRqstPk_I1)) { %>
															<ezf:anchor name="svcInvNum_I1" ezfName="svcInvNum_I1" ezfHyo="I" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewPaperInvoiceI" otherAttr=" id=\"svcInvNum_I1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="svcInvNum_I1" ezfName="svcInvNum_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/></ezf:anchor>
															<% } else { %>
															<ezf:label name="svcInvNum_I1" ezfName="svcInvNum_I1" ezfHyo="I" ezfArrayNo="0" />
															<% } %>
															</td>
															<td><ezf:label name="invDt_I1" ezfName="invDt_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:label name="baseActlPrcDealAmt_I1" ezfName="baseActlPrcDealAmt_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td><ezf:label name="dealTaxAmt_I1" ezfName="dealTaxAmt_I1" ezfHyo="I" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_I1" ezfName="xxRecHistCratTs_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_I1" ezfName="xxRecHistCratByNm_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_I1" ezfName="xxRecHistUpdTs_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_I1" ezfName="xxRecHistUpdByNm_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_I1" ezfName="xxRecHistTblNm_I1" ezfHyo="I" ezfArrayNo="0" />
															</td>
														</tr>
											<% count++; %>
											</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
						<% } %>
						
						
						<% if (ZYPCommonFunc.hasValue(bMsg.dsContrBllgMtrPk_J)) { %>
								<table id="Table_J" border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="70">
												<col align="left" width="260">
												<col align="right">
												<tr>
													<td class="stab">Billing Meter</td>
													<td><ezf:inputText name="mtrLbNm_J" ezfName="mtrLbNm_J" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\""/></td>
													<td><a href="#MtrList">Back To Top</a></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_J_Top" style="overflow-x:none; overflow-y:none; width:1030; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="50" align="center">		<!-- PE Seq# -->
														<col width="50" align="center">		<!-- TS Seq# -->
														<col width="60" align="center">		<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90" align="center">		<!-- Bill From Date -->
														<col width="90" align="center">		<!-- Bill To Date -->
														<col width="90" align="center">		<!-- Interface Date -->
														<col width="50" align="center">		<!-- Meter Fulfill -->
														<col width="80" align="center">		<!-- Schedule Status -->
														<col width="80" align="center">		<!-- Invoice# -->
														<col width="90" align="center">		<!-- Actual Invoice Date -->
														<col width="110" align="center">	<!-- Invoiced Amount -->
														<col width="100" align="center">	<!-- Tax Amount -->
														<tr>
															<td class="pClothBs">PE Seq#</td>
															<td class="pClothBs">TS Seq#</td>
															<td class="pClothBs">Schedule Seq#</td>
															<td class="pClothBs">Skip Type</td>
															<td class="pClothBs">Bill From Date</td>
															<td class="pClothBs">Bill To Date</td>
															<td class="pClothBs">Interface Date</td>
															<td class="pClothBs">Meter<br />Fulfill</td>
															<td class="pClothBs">Schedule<br />Status</td>
															<td class="pClothBs">Invoice#</td>
															<td class="pClothBs">Actual<br />Invoice Date</td>
															<td class="pClothBs">Invoiced Amount</td>
															<td class="pClothBs">Tax Amount</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_J" style="overflow-y:scroll; height:440; overflow-x:hidden; width:1049; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="J" style="table-layout: fixed">
														<col width="50">					<!-- PE Seq# -->
														<col width="50">					<!-- TS Seq# -->
														<col width="60">					<!-- Schedule Seq# -->
														<col width="90" align="center">		<!-- Skip Type -->
														<col width="90">					<!-- Bill From Date -->
														<col width="90">					<!-- Bill To Date -->
														<col width="90">					<!-- Interface Date -->
														<col width="50">					<!-- Meter Fulfill -->
														<col width="80">					<!-- Schedule Status -->
														<col width="80">					<!-- Invoice# -->
														<col width="90">					<!-- Actual Invoice Date -->
														<col width="110" align="right">		<!-- Invoiced Amount -->
														<col width="100" align="right">		<!-- Tax Amount -->
											<% int count = 0; %>
											<ezf:row ezfHyo="J">
														<tr ezfhyo="J" id="id_left_J_row{EZF_ROW_NUMBER}">
															<td><ezf:label name="dsContrPrcEffSqNum_J1" ezfName="dsContrPrcEffSqNum_J1" ezfHyo="J" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdLvlNum_J1" ezfName="dsContrBllgSchdLvlNum_J1" ezfHyo="J" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsContrBllgSchdSqNum_J1" ezfName="dsContrBllgSchdSqNum_J1" ezfHyo="J" ezfArrayNo="0" /></td>
															<td><ezf:select name="skipRecovTpCd_J3" ezfName="skipRecovTpCd_J3" ezfHyo="J" ezfArrayNo="0" ezfBlank="1" ezfCodeName="skipRecovTpCd_H1" ezfDispName="skipRecovTpNm_H1" otherAttr=" style=\"width:85\""/></td>
															<td><ezf:label name="bllgSchdFromDt_J1" ezfName="bllgSchdFromDt_J1" ezfHyo="J" ezfArrayNo="0" /></td>
															<td><ezf:label name="bllgSchdThruDt_J1" ezfName="bllgSchdThruDt_J1" ezfHyo="J" ezfArrayNo="0" /></td>
															<td><ezf:label name="nextBllgDt_J1" ezfName="nextBllgDt_J1" ezfHyo="J" ezfArrayNo="0" /></td>
															<td><ezf:label name="mtrEntryCpltFlg_J1" ezfName="mtrEntryCpltFlg_J1" ezfHyo="J" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsBllgSchdStsDescTxt_J1" ezfName="dsBllgSchdStsDescTxt_J1" ezfHyo="J" ezfArrayNo="0" /></td>
															<td>
															<% if (ZYPCommonFunc.hasValue(bMsg.J.no(count).eipRptRqstPk_J1)) { %>
															<ezf:anchor name="svcInvNum_J1" ezfName="svcInvNum_J1" ezfHyo="J" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewPaperInvoiceJ" otherAttr=" id=\"svcInvNum_J1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="svcInvNum_J1" ezfName="svcInvNum_J1" ezfHyo="J" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/></ezf:anchor>
															<% } else { %>
															<ezf:label name="svcInvNum_J1" ezfName="svcInvNum_J1" ezfHyo="J" ezfArrayNo="0" />
															<% } %>
															</td>
															<td><ezf:label name="invDt_J1" ezfName="invDt_J1" ezfHyo="J" ezfArrayNo="0" /></td>
															<td><ezf:label name="baseActlPrcDealAmt_J1" ezfName="baseActlPrcDealAmt_J1" ezfHyo="J" ezfArrayNo="0" /></td>
															<td><ezf:label name="dealTaxAmt_J1" ezfName="dealTaxAmt_J1" ezfHyo="J" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_J1" ezfName="xxRecHistCratTs_J1" ezfHyo="J" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_J1" ezfName="xxRecHistCratByNm_J1" ezfHyo="J" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_J1" ezfName="xxRecHistUpdTs_J1" ezfHyo="J" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_J1" ezfName="xxRecHistUpdByNm_J1" ezfHyo="J" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_J1" ezfName="xxRecHistTblNm_J1" ezfHyo="J" ezfArrayNo="0" />
															</td>
														</tr>
											<% count++; %>
											</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<br />
						<% } %>
					<% } %>	
					</div>
					<br />
				</div>
			</td>
		</tr>
	</table>
</center>

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
