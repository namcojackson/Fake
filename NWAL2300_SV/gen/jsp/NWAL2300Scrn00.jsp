<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180416111709 --%>
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
			<input type="hidden" name="pageID" value="NWAL2300Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Credit/Rebill Entry">
			<table width="1133" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<%-- ###TAB - HEAD --%>
						<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
						<div class="pTab_BODY">
							<%-- ## HEADER ## --%>
							<table border="0" cellpadding="0" cellspacing="0">
								<col width="5">
								<col width="650">
								<col width="5">
								<col width="430">
								<tr>
									<td></td>
									<td valign="top">
										<div style="float:left;">
											<fieldset style="width: 650">
												<legend>Credit & Rebill Information</legend>
												<table cellpadding="0" border="0">
													<col align="left" width="80">
													<col align="left" width="50">
													<col align="left" width="60">
													<!-- Mod Start 2017/10/02 H.Sugawara QC#19922 -->
													<!-- <col align="left" width="175"> -->
													<col align="left" width="200">
													<!-- Mod End   2017/10/02 H.Sugawara QC#19922 -->
													<col align="left" width="70">
													<col align="left" width="60">
													<tr>
														<td class="stab"><ezf:anchor name="cpoOrdNum_L1" ezfName="cpoOrdNum_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_OrigOrdSearch" otherAttr=" id=\"cpoOrdNum_L1\" ezfanchortext=\"\"">Order Number</ezf:anchor></td>
														<td><ezf:inputText name="cpoOrdNum_H1" ezfName="cpoOrdNum_H1" value="1234567890" otherAttr=" size=\"15\" maxlength=\"8\" ezftoupper=\"\""/></td>
														<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn4" /></td>
														<td>&nbsp;</td>
														<td class="stab">Credit Order:</td>
														<td class="pOut">
															<ezf:anchor name="cpoOrdNum_L2" ezfName="cpoOrdNum_L2" ezfEmulateBtn="1" ezfGuard="MoveWin_OrderEntry_Credit" otherAttr=" id=\"cpoOrdNum_L2\" ezfanchortext=\"\""><ezf:label name="cpoOrdNum_H2" ezfName="cpoOrdNum_H2" /></ezf:anchor>
														</td>
													</tr>
												</table>
												<table cellpadding="0" border="0">
													<col align="left" width="80">
													<col align="left" width="90">
													<col align="left" width="90">
													<col align="left" width="5">
													<!-- Mod Start 2017/10/02 H.Sugawara QC#19922 -->
													<!-- <col align="left" width="50"> -->
													<col align="left" width="75">
													<!-- Mod End   2017/10/02 H.Sugawara QC#19922 -->
													<col align="left" width="90">
													<col align="left" width="13">
													<col align="left" width="70">
													<col align="left" width="60">
													<tr>
														<td class="stab">Bill To Cust:</td>
														<td><ezf:inputText name="billToCustAcctCd_H1" ezfName="billToCustAcctCd_H1" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\" tabindex=\"-1\""/></td>
														<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" otherAttr=" size=\"12\" maxlength=\"60\" ezftoupper=\"\" tabindex=\"-1\""/></td>
														<td>&nbsp;</td>
														<!-- Mod Start 2017/10/02 H.Sugawara QC#19922 -->
														<!-- <td class="stab">Location:</td> -->
														<td class="stab">Bill To Code:</td>
														<!-- Mod End   2017/10/02 H.Sugawara QC#19922 -->
														<td><ezf:inputText name="billToCustCd_H1" ezfName="billToCustCd_H1" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\" tabindex=\"-1\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Rebill Order:</td>
														<td class="pOut">
															<ezf:anchor name="cpoOrdNum_L3" ezfName="cpoOrdNum_L3" ezfEmulateBtn="1" ezfGuard="MoveWin_OrderEntry_Rebill" otherAttr=" id=\"cpoOrdNum_L3\" ezfanchortext=\"\""><ezf:label name="cpoOrdNum_H3" ezfName="cpoOrdNum_H3" /></ezf:anchor>
														</td>
													</tr>
													<tr>
														<td class="stab">Ship To Cust:</td>
														<td><ezf:inputText name="shipToCustAcctCd_H1" ezfName="shipToCustAcctCd_H1" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\" tabindex=\"-1\""/></td>
														<td><ezf:inputText name="dsAcctNm_H2" ezfName="dsAcctNm_H2" otherAttr=" size=\"12\" maxlength=\"60\" ezftoupper=\"\" tabindex=\"-1\""/></td>
														<td>&nbsp;</td>
														<!-- Mod Start 2017/10/02 H.Sugawara QC#19922 -->
														<!-- <td class="stab">Location:</td> -->
														<td class="stab">Ship To Code:</td>
														<!-- Mod End   2017/10/02 H.Sugawara QC#19922 -->
														<td><ezf:inputText name="shipToCustCd_H1" ezfName="shipToCustCd_H1" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\" tabindex=\"-1\""/></td>
													</tr>
												</table>
												<hr style="border-top: 4px double width:125; height:7;" >
												<table cellpadding="0" border="0">
													<col align="left" width="90">
													<col align="left" width="50">
													<col align="left" width="20">
													<col align="left" width="10">
													<tr>
														<td class="stab" valign="top">Reason Code:</td>
														<td>
															<table border="1" cellpadding="0" cellspacing="0">
																<tr>
																	<td>
																		<div id="LeftTable_A" style="overflow-y:scroll; height:65; overflow-x:hidden; width:230; float:left;">
																			<table border="0" cellpadding="0" cellspacing="0" height="50" id="A" >
																				<col width="30" align="center">
																				<col width="160" align="left">
																				<ezf:row ezfHyo="A">
																					<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																						<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="crRebilRsnNm_A1" ezfName="crRebilRsnNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"30\" ezftoupper=\"\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/></td>
																					</tr>
																				</ezf:row>
																				<ezf:skip>
																					<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																						<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfHyo="A"></td>
																						<td valign="middle"><input type="text" name="prcRuleCondNum_A1" value="Sales:-REWRITE LEASEPAY" size="25" maxlength="30" ezftoupper="" ezfname="prcRuleCondNum_C1" class="pPro" ezfHyo="C" style="border:none;background-color:transparent;padding:0px;"></td>
																					</tr>
																					<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																						<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfHyo="A"></td>
																						<td valign="middle"><input type="text" name="prcRuleCondNum_A1" value="Sales:-XXXXXXXXX" size="25" maxlength="30" ezftoupper="" ezfname="prcRuleCondNum_C1" class="pPro" ezfHyo="C" style="border:none;background-color:transparent;padding:0px;vertical-align:middle;"></td>
																					</tr>
																					<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																						<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfHyo="A"></td>
																						<td valign="middle"><input type="text" name="prcRuleCondNum_A1" value="Sales:-XXXXXXXXX" size="25" maxlength="30" ezftoupper="" ezfname="prcRuleCondNum_C1" class="pPro" ezfHyo="C" style="border:none;background-color:transparent;padding:0px;vertical-align:middle;"></td>
																					</tr>
																				</ezf:skip>
																			</table>
																		</div>
																	</td>
																</tr>
															</table>
														</td>
														<td>&nbsp;</td>
														<td class="stab" valign="top">Comments:</td>
														<td class="stab"><ezf:textArea name="xxOrdMemoTxt_H1" ezfName="xxOrdMemoTxt_H1" otherAttr=" rows=\"6\" cols=\"30\""/></td>
													</tr>
												</table>
											</fieldset>
										</div>
									</td>
									<td></td>
									<td valign="top">
										
											<fieldset style="width: 430">
												<legend>Optional Order Details</legend>
												<table cellpadding="0" border="0">
													<col align="left" width="100">
													<col width="80">
													<col width="10">
													<col width="80">
													<tr>
														<td class="stab">Total Invoiced($):</td>
														<td colspan="2" align="right"><ezf:inputText name="invTotDealNetAmt_H1" ezfName="invTotDealNetAmt_H1" otherAttr=" size=\"13\" maxlength=\"25\" ezftoupper=\"\" tabindex=\"-1\""/></td>
													</tr>
													<tr>
														<td class="stab">Open Lines:</td>
														<td><ezf:inputText name="xxYesNoNm_H1" ezfName="xxYesNoNm_H1" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\" tabindex=\"-1\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Open RMAs:</td>
														<td><ezf:inputText name="xxYesNoNm_H2" ezfName="xxYesNoNm_H2" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\" tabindex=\"-1\""/></td>
													</tr>
												</table>
											</fieldset>
											<fieldset style="width: 430">
												<legend>Original Order Profitability Details</legend>
												<table cellpadding="0" border="0">
													<col align="left" width="50">
													<col align="left" width="60">
													<col align="left" width="20">
													<col align="left" width="50">
													<col align="left" width="20">
													<col align="right" width="30">
													<tr>
														<td class="stab">Version#:</td>
														<td><ezf:inputText name="ordPrftVrsnNum_H1" ezfName="ordPrftVrsnNum_H1" otherAttr=" size=\"5\" maxlength=\"8\" ezftoupper=\"\" tabindex=\"-1\""/></td>
														<td class="stab">GP%:</td>
														<td><ezf:inputText name="grsPrftPct_H1" ezfName="grsPrftPct_H1" otherAttr=" size=\"6\" maxlength=\"25\" ezftoupper=\"\" tabindex=\"-1\""/></td>
														<td class="stab">%</td>
														<td class="stab">GP$:</td>
														<td><ezf:inputText name="funcGrsPrftAmt_H1" ezfName="funcGrsPrftAmt_H1" otherAttr=" size=\"10\" maxlength=\"25\" ezftoupper=\"\" tabindex=\"-1\""/></td>
													</tr>
												</table>
												<table cellpadding="0" border="0">
													<col align="left" width="60">
													<col align="left" width="100">
													<col align="left" width="20">
													<col align="left" width="70">
													<tr>
														<td class="stab">Neg Deal($):</td>
														<td><ezf:inputText name="funcNegoDealAmt_H1" ezfName="funcNegoDealAmt_H1" otherAttr=" size=\"15\" maxlength=\"25\" ezftoupper=\"\" tabindex=\"-1\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Final Floor($):</td>
														<td><ezf:inputText name="totFuncFinalFlAmt_H1" ezfName="totFuncFinalFlAmt_H1" otherAttr=" size=\"15\" maxlength=\"25\" ezftoupper=\"\" tabindex=\"-1\""/></td>
													</tr>
												</table>
												<table cellpadding="0" border="0">
													<col align="left" width="160">
													<tr>
														<td class="stab">Incentive Comp Rep Amount($):</td>
														<td><ezf:inputText name="totFuncRepRevAmt_H1" ezfName="totFuncRepRevAmt_H1" otherAttr=" size=\"15\" maxlength=\"25\" ezftoupper=\"\" tabindex=\"-1\""/></td>
													</tr>
												</table>
											</fieldset>
										
									</td>
								</tr>
							</table>
							<%-- Invoice Summary --%>
							<table border="0">
								<tr>
									<td>
										<fieldset style="width: 1000">
											<legend>Invoice Summary</legend>
											<table border="0" cellpadding="1" cellspacing="0" width="1000" height="90">
												<tr>
													<td align="top">
														<table border="0" width="500">
															<tr>
																<td><ezf:inputButton name="Apply_Summary" value="Apply" htmlClass="pBtn6" otherAttr=" id=\"Apply_Summary\""/></td>
															<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="1" otherAttr="onclick=\"sendServer('OnChange_RadioBtnOrderInvoice');\""/>Order</td>
																<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="2" otherAttr="onclick=\"sendServer('OnChange_RadioBtnOrderInvoice');\""/>Invoice</td>
															</tr>
														</table>
														<div id="LeftTable_B_Top" style="overflow-x:none; overflow-y:none; width:850; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed; style="margin-right:20px">
																<col align="center" width="60">
																<col align="center" width="100">
																<col align="center" width="120">
																<col align="center" width="120">
																<col align="center" width="120">
																<col align="center" width="120">
																<col align="center" width="120">
																<col align="center" width="120">
																<tr height="18">
																	<td class="pClothBs">&nbsp;</td>
																	<td class="pClothBs">Action</td>
																	<td class="pClothBs">Invoice Number</td>
																	<td class="pClothBs">Invoice Date</td>
																	<td class="pClothBs">Invoice Amount($)</td>
																	<td class="pClothBs">Balance Due($)</td>
																	<td class="pClothBs">Credit Order</td>
																	<td class="pClothBs">Rebill Order</td>
																</tr>
															</table>
														</div>
														<div id="LeftTable_B" style="overflow-y:scroll; height:50; overflow-x:hidden; width:900; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed">
																<col width="60" align="center">
																<col width="100" align="center">
																<col width="120" align="center">
																<col width="120" align="center">
																<col width="120" align="right">
																<col width="120" align="right">
																<col width="120" align="center">
																<col width="120" align="center">
																<ezf:row ezfHyo="B">
																	<tr id="id_leftB_row{EZF_ROW_NUMBER}">
																		<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td>
																		<ezf:select name="xxTpCd_B1" ezfName="xxTpCd_B1" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="xxTpCd_L1" ezfDispName="xxTpNm_L1" otherAttr=" style=\"width: 90px\""/>
																		</td>
																		<td><ezf:label name="invNum_B1" ezfName="invNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="invDt_B1" ezfName="invDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="invTotDealNetAmt_B1" ezfName="invTotDealNetAmt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="dealRmngBalGrsAmt_B1" ezfName="dealRmngBalGrsAmt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td>
																			<ezf:anchor name="cpoOrdNum_L4" ezfName="cpoOrdNum_L4" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_OrderEntry_InvoiceCredit" otherAttr=" id=\"cpoOrdNum_L4\" ezfanchortext=\"\""><ezf:label name="cpoOrdNum_B1" ezfName="cpoOrdNum_B1" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor>
																		</td>
																		<td>
																			<ezf:anchor name="cpoOrdNum_L5" ezfName="cpoOrdNum_L5" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_OrderEntry_InvoiceRebill" otherAttr=" id=\"cpoOrdNum_L5\" ezfanchortext=\"\""><ezf:label name="cpoOrdNum_B2" ezfName="cpoOrdNum_B2" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor>
																		</td>
																	</tr>
																</ezf:row>
																<ezf:skip>
																	<tr id="id_leftB_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" value="Y" name="xxChkBox_B1" ezfname="xxChkBox_B1" ezfHyo="B"></td>
																		<td>
																		<select name="xxTpCd_B1" ezfname="xxTpCd_B1" ezflist="xxTpCd_L1,xxTpNm_L1,99, ,blank" ezfHyo="B" style="width: 90px">
																			<option>C/R</option>
																			<option>RMA/Rb</option>
																		</select>
																		</td>
																		<td><label ezfout name="invNum_B1" ezfname="v" ezfhyo="B">70002</label></td>
																		<td><label ezfout name="invDt_B1" ezfname="invDt_B1" ezfhyo="B">08/31/2015</label></td>
																		<td><label ezfout name="invTotDealNetAmt_B1" ezfname="invTotDealNetAmt_B1" ezfhyo="B">10,000,00</label></td>
																		<td><label ezfout name="dealRmngBalGrsAmt_B1" ezfname="dealRmngBalGrsAmt_B1" ezfhyo="B">10,000,00</label></td>
																		<td>
																			<a href="#" id="cpoOrdNum_L4" name="cpoOrdNum_L4" ezfname="cpoOrdNum_L4" onclick="sendServer('MoveWin_OrderEntry_InvoiceCredit')" ezfhyo="B" ezfanchortext><label ezfout name="cpoOrdNum_B1" ezfname="cpoOrdNum_B1" ezfhyo="B">20001234</label></a>
																		</td>
																		<td>
																			<a href="#" id="cpoOrdNum_L5" name="cpoOrdNum_L5" ezfname="cpoOrdNum_L5" onclick="sendServer('MoveWin_OrderEntry_InvoiceRebill')" ezfhyo="B" ezfanchortext><label ezfout name="cpoOrdNum_B2" ezfname="cpoOrdNum_B2" ezfhyo="B">20001235</label></a>
																		</td>
																	</tr>
																	<tr id="id_leftB_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" value="Y" name="xxChkBox_B1" ezfname="xxChkBox_B1" ezfHyo="B"></td>
																		<td>
																		<select name="xxTpCd_B1" ezfname="xxTpCd_B1" ezflist="xxTpCd_L1,xxTpNm_L1,99, ,blank" ezfHyo="B" style="width: 90px">
																			<option>C/R</option>
																			<option>RMA/Rb</option>
																		</select>
																		</td>
																		<td><label ezfout name="invNum_B1" ezfname="invNum_B1" ezfhyo="B">70003</label></td>
																		<td><label ezfout name="invDt_B1" ezfname="invDt_B1" ezfhyo="B">08/31/2015</label></td>
																		<td><label ezfout name="invTotDealNetAmt_B1" ezfname="invTotDealNetAmt_B1" ezfhyo="B">75,000,00</label></td>
																		<td><label ezfout name="dealRmngBalGrsAmt_B1" ezfname="dealRmngBalGrsAmt_B1" ezfhyo="B">75,000,00</label></td>
																		<td>
																			<a href="#" id="cpoOrdNum_L4" name="cpoOrdNum_L4" ezfname="cpoOrdNum_L4" onclick="sendServer('MoveWin_OrderEntry_InvoiceCredit')" ezfhyo="B" ezfanchortext><label ezfout name="cpoOrdNum_B1" ezfname="cpoOrdNum_B1" ezfhyo="B">20001236</label></a>
																		</td>
																		<td>
																			<a href="#" id="cpoOrdNum_L5" name="cpoOrdNum_L5" ezfname="cpoOrdNum_L5" onclick="sendServer('MoveWin_OrderEntry_InvoiceRebill')" ezfhyo="B" ezfanchortext><label ezfout name="cpoOrdNum_B2" ezfname="cpoOrdNum_B2" ezfhyo="B">20001237</label></a>
																		</td>
																	</tr>
																	<tr id="id_leftB_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" value="Y" name="xxChkBox_B1" ezfname="xxChkBox_B1" ezfHyo="B"></td>
																		<td>
																		<select name="xxTpCd_B1" ezfname="xxTpCd_B1" ezflist="xxTpCd_L1,xxTpNm_L1,99, ,blank" ezfHyo="B" style="width: 90px">
																			<option>C/R</option>
																			<option>RMA/Rb</option>
																		</select>
																		</td>
																		<td><label ezfout name="invNum_B1" ezfname="invNum_B1" ezfhyo="B">70004</label></td>
																		<td><label ezfout name="invDt_B1" ezfname="invDt_B1" ezfhyo="B">08/31/2015</label></td>
																		<td><label ezfout name="invTotDealNetAmt_B1" ezfname="invTotDealNetAmt_B1" ezfhyo="B">75,000,00</label></td>
																		<td><label ezfout name="dealRmngBalGrsAmt_B1" ezfname="dealRmngBalGrsAmt_B1" ezfhyo="B">75,000,00</label></td>
																		<td>
																			<a href="#" id="cpoOrdNum_L4" name="cpoOrdNum_L4" ezfname="cpoOrdNum_L4" onclick="sendServer('MoveWin_OrderEntry_InvoiceCredit')" ezfhyo="B" ezfanchortext><label ezfout name="cpoOrdNum_B1" ezfname="cpoOrdNum_B1" ezfhyo="B">20001238</label></a>
																		</td>
																		<td>
																			<a href="#" id="cpoOrdNum_L5" name="cpoOrdNum_L5" ezfname="cpoOrdNum_L5" onclick="sendServer('MoveWin_OrderEntry_InvoiceRebill')" ezfhyo="B" ezfanchortext><label ezfout name="cpoOrdNum_B2" ezfname="cpoOrdNum_B2" ezfhyo="B">20001239</label></a>
																		</td>
																	</tr>
																</ezf:skip>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
							</table>
							<%-- ## Invoice Line ## --%>
							<table border="0">
								<tr>
									<td valign="top">
										<fieldset style="width: 1300 height: 500">
										<legend>Invoice Line</legend>
										<table border="0" cellpadding="1" cellspacing="0" width="720" height="220">
											<tr>
												<td valign="top">
													<table border="0" width="750">
														<col align="left" width="150">
														<col align="left" width="100">
														<col align="left" width="100">
														<col align="left" width="">
														<col align="left" width="">
														<col align="left" width="">
														<tr>
															<td class="stab"><b>Please Select an action</b></td>
															<td class="stab"><label><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="1" />Credit & Rebill</label></td>
															<td class="stab"><label><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="2" />RMA With Credit</label></td>
															<td>&nbsp;</td>
															<td><ezf:inputButton name="Apply" value="Apply" htmlClass="pBtn6" otherAttr=" id=\"Apply\""/></td>
														</tr>
													</table>
													<div id="LeftTable_C_Top" style="overflow-x:none; overflow-y:none; width:1100; float:left;">
														<table border="1" cellpadding="1" cellspacing="0" height="35" style="table-layout: fixed; margin-right:20px">
															<col align="center" width="100">
															<col align="center" width="80">
															<col align="center" width="60">
															<col align="center" width="70">
															<col align="center" width="120">
															<col align="center" width="170">
															<col align="center" width="60">
															<col align="center" width="40">
															<col align="center" width="100">
															<col align="center" width="100">
															<col align="center" width="100">
															<col align="center" width="100">
															<tr height="18">
																<td class="pClothBs">&nbsp;<br>Action</td>
																<td class="pClothBs">Cancel<br>Open RMA</td>
																<td class="pClothBs">Config<br>Line</td>
																<td class="pClothBs">Line<br>Number</td>
																<td class="pClothBs">&nbsp;<br>Item Code</td>
																<td class="pClothBs">&nbsp;<br>Description</td>
																<td class="pClothBs">&nbsp;<br>Status</td>
																<td class="pClothBs">&nbsp;<br>Qty</td>
																<td class="pClothBs">Unit<br>Price($)</td>
																<td class="pClothBs">Extended<br>Price($)</td>
																<td class="pClothBs">&nbsp;<br>Invoice#</td>
																<td class="pClothBs">Invoice<br>Amount($)</td>
															</tr>
														</table>
													</div>
													<div id="LeftTable_C" style="overflow-y:scroll; height:170; overflow-x:hidden; width:1120; float:left;">
														<table border="1" cellpadding="1" cellspacing="0" id="C" style="table-layout: fixed">
															<col width="100" align="center">
															<col width="80"  align="center">
															<col width="60"  align="center">
															<col width="70"  align="center">
															<col width="120" align="left"><!--Item Cd -->
															<col width="170" align="left"><!--Item Name -->
															<col width="60"  align="center">
															<col width="40"  align="center">
															<col width="100" align="right">
															<col width="100" align="right">
															<col width="100" align="left">
															<col width="100" align="right">
															<ezf:row ezfHyo="C">
																<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																	<td>
																		<ezf:select name="xxTpCd_C1" ezfName="xxTpCd_C1" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="xxTpCd_L1" ezfDispName="xxTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_Action', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width: 90px\""/>
																	</td>
																	<td><ezf:inputCheckBox name="xxChkBox_C1" ezfName="xxChkBox_C1" value="Y" ezfHyo="C" ezfArrayNo="0" onClick="sendServer('OnChange_ChkBox', {EZF_ROW_NUMBER})" /></td>
																	<td><ezf:label name="xxDplyTrxNumTxt_C1" ezfName="xxDplyTrxNumTxt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																	<td><ezf:label name="dplyLineNum_C1" ezfName="dplyLineNum_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																	<td><ezf:label name="mdseCd_C1" ezfName="mdseCd_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																	<td><ezf:inputText name="mdseDescShortTxt_C1" ezfName="mdseDescShortTxt_C1" value="1111111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000111111111122222222223333333333444444444455555555556666666666777777777788888888889999999999000000000011111111112222222222333333333344444444445555555555" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"23\" maxlength=\"250\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/></td>
																	<td><ezf:label name="xxDplyStsNm_C1" ezfName="xxDplyStsNm_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																	<td><ezf:label name="ordQty_C1" ezfName="ordQty_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																	<td><ezf:label name="dealNetUnitPrcAmt_C1" ezfName="dealNetUnitPrcAmt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																	<td><ezf:label name="spTotDealNetAmt_C1" ezfName="spTotDealNetAmt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																	<td><ezf:label name="invNum_C1" ezfName="invNum_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																	<td><ezf:label name="invAmt_C1" ezfName="invAmt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																</tr>
															</ezf:row>
															<ezf:skip>
																<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																	<td>
																		<select name="prcRuleAttrbCd_C1" ezfname="prcRuleAttrbCd_C1" ezflist="prcRuleAttrbCd_LA,prcRuleAttrbDescTxt_LA,99, ,noblank" ezfHyo="C" onchange="sendServer('OnChange_Attribute')" style="width: 90px">
																			<option>C/R</option>
																			<option>RMA/Rb</option>
																		</select>
																	<td><input type="checkbox" value="Y" name="inpReqFlg_C1" ezfname="inpReqFlg_C1" ezfHyo="C"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1.2</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">0306B001</label></td>
																	<td><input type="text" value="FEEDER" name="firstLineAddr_A1" ezfname="firstLineAddr_A1" ezfhyo="A" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">Closed</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">6,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">6,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">70003</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">6,000.00</label></td>
																</tr>
																<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																	<td>
																		<select name="prcRuleAttrbCd_C1" ezfname="prcRuleAttrbCd_C1" ezflist="prcRuleAttrbCd_LA,prcRuleAttrbDescTxt_LA,99, ,noblank" ezfHyo="C" onchange="sendServer('OnChange_Attribute')" style="width: 90px">
																			<option>C/R</option>
																			<option>RMA/Rb</option>
																		</select>
																	<td><input type="checkbox" value="Y" name="inpReqFlg_C1" ezfname="inpReqFlg_C1" ezfHyo="C"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1.3</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">0306B001</label></td>
																	<td><input type="text" value="FINISHER" name="firstLineAddr_A1" ezfname="firstLineAddr_A1" ezfhyo="A" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">Closed</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">7,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">7,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">70005</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">7,000.00</label></td>
																</tr>
																<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																	<td>
																		<select name="prcRuleAttrbCd_C1" ezfname="prcRuleAttrbCd_C1" ezflist="prcRuleAttrbCd_LA,prcRuleAttrbDescTxt_LA,99, ,noblank" ezfHyo="C" onchange="sendServer('OnChange_Attribute')" style="width: 90px">
																			<option>C/R</option>
																			<option>RMA/Rb</option>
																		</select>
																	<td><input type="checkbox" value="Y" name="inpReqFlg_C1" ezfname="inpReqFlg_C1" ezfHyo="C"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">2.1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">0306B001</label></td>
																	<td><input type="text" name="firstLineAddr_A1" ezfname="firstLineAddr_A1" ezfhyo="A" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">Closed</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">8,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">8,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">70001</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">8,000.00</label></td>
																</tr>
																<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																	<td>
																		<select name="prcRuleAttrbCd_C1" ezfname="prcRuleAttrbCd_C1" ezflist="prcRuleAttrbCd_LA,prcRuleAttrbDescTxt_LA,99, ,noblank" ezfHyo="C" onchange="sendServer('OnChange_Attribute')" style="width: 90px">
																			<option>C/R</option>
																			<option>RMA/Rb</option>
																		</select>
																	<td><input type="checkbox" value="Y" name="inpReqFlg_C1" ezfname="inpReqFlg_C1" ezfHyo="C"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">2.2</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">0306B001</label></td>
																	<td><input type="text" name="firstLineAddr_A1" ezfname="firstLineAddr_A1" ezfhyo="C" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">Closed</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">9,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">9,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">70001</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">9,000.00</label></td>
																</tr>
																<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																	<td>
																		<select name="prcRuleAttrbCd_C1" ezfname="prcRuleAttrbCd_C1" ezflist="prcRuleAttrbCd_LA,prcRuleAttrbDescTxt_LA,99, ,noblank" ezfHyo="C" onchange="sendServer('OnChange_Attribute')" style="width: 90px">
																			<option>C/R</option>
																			<option>RMA/Rb</option>
																		</select>
																	<td><input type="checkbox" value="Y" name="inpReqFlg_C1" ezfname="inpReqFlg_C1" ezfHyo="C"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">2.2</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">0306B001</label></td>
																	<td><input type="text" name="firstLineAddr_A1" ezfname="firstLineAddr_A1" ezfhyo="A" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">Closed</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">70001</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																</tr>
																<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																	<td>
																		<select name="prcRuleAttrbCd_C1" ezfname="prcRuleAttrbCd_C1" ezflist="prcRuleAttrbCd_LA,prcRuleAttrbDescTxt_LA,99, ,noblank" ezfHyo="C" onchange="sendServer('OnChange_Attribute')" style="width: 90px">
																			<option>C/R</option>
																			<option>RMA/Rb</option>
																		</select>
																	<td><input type="checkbox" value="Y" name="inpReqFlg_C1" ezfname="inpReqFlg_C1" ezfHyo="C"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">3</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">3.1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">0306B001</label></td>
																	<td><input type="text" name="firstLineAddr_A1" ezfname="firstLineAddr_A1" ezfhyo="A" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">Closed</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">70001</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																</tr>
																<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																	<td>
																		<select name="prcRuleAttrbCd_C1" ezfname="prcRuleAttrbCd_C1" ezflist="prcRuleAttrbCd_LA,prcRuleAttrbDescTxt_LA,99, ,noblank" ezfHyo="C" onchange="sendServer('OnChange_Attribute')" style="width: 90px">
																			<option>C/R</option>
																			<option>RMA/Rb</option>
																		</select>
																	<td><input type="checkbox" value="Y" name="inpReqFlg_C1" ezfname="inpReqFlg_C1" ezfHyo="C"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">3</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">3.1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">0306B001</label></td>
																	<td><input type="text" name="firstLineAddr_A1" ezfname="firstLineAddr_A1" ezfhyo="A" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">Closed</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">70001</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																</tr>
																<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																	<td>
																		<select name="prcRuleAttrbCd_C1" ezfname="prcRuleAttrbCd_C1" ezflist="prcRuleAttrbCd_LA,prcRuleAttrbDescTxt_LA,99, ,noblank" ezfHyo="C" onchange="sendServer('OnChange_Attribute')" style="width: 90px">
																			<option>C/R</option>
																			<option>RMA/Rb</option>
																		</select>
																	<td><input type="checkbox" value="Y" name="inpReqFlg_C1" ezfname="inpReqFlg_C1" ezfHyo="C"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">3</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">3.1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">0306B001</label></td>
																	<td><input type="text" name="firstLineAddr_A1" ezfname="firstLineAddr_A1" ezfhyo="A" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">Closed</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">70001</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																</tr>
																<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																	<td>
																		<select name="prcRuleAttrbCd_C1" ezfname="prcRuleAttrbCd_C1" ezflist="prcRuleAttrbCd_LA,prcRuleAttrbDescTxt_LA,99, ,noblank" ezfHyo="C" onchange="sendServer('OnChange_Attribute')" style="width: 90px">
																			<option>C/R</option>
																			<option>RMA/Rb</option>
																		</select>
																	<td><input type="checkbox" value="Y" name="inpReqFlg_C1" ezfname="inpReqFlg_C1" ezfHyo="C"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">3</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">3.1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">0306B001</label></td>
																	<td><input type="text" name="firstLineAddr_A1" ezfname="firstLineAddr_A1" ezfhyo="A" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">Closed</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">1</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">70001</label></td>
																	<td><label ezfout name="prcFmlaTpNm_A1" ezfname="prcFmlaTpNm_A1" ezfhyo="B">10,000.00</label></td>
																</tr>
															</ezf:skip>
														</table>
													</div>
												</td>
											</tr>
										</table>
										</fieldset>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>

<%-- **** Task specific area ends here **** --%>
