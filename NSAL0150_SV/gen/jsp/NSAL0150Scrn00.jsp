<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240409145708 --%>
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
			<input type="hidden" name="pageID" value="NSAL0150Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Meter Entry">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Service Contract Inquiry" class="pTab_ON"><a href="#">Meter Entry</a></li>
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
				<%@ page import="business.servlet.NSAL0150.NSAL0150BMsg" %>
				<% NSAL0150BMsg cMsg = (NSAL0150BMsg)databean.getEZDBMsg(); %>
                <%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
				<div class="pTab_BODY">
					<div style="border-style:solid; border-width:0; height:572; overflow-y:scroll; width:1133; overflowx:none;">
						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="5">
							<col valign="top">
							<col valign="top">
							<col valign="top">
							<tr>
<%-- ######################################## Customer Detail ######################################## --%>
								<td>
									<fieldset style="height:363;">
										<legend><font size="2">Customer Detail</font></legend>
										<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
											<col width="140" align="right" style="padding-right:5;">
											<col align="left">
											<tr height="19">
												<td class="stab">Bill To</td>
												<td class="stab"><ezf:inputText name="billToLocNm" ezfName="billToLocNm" value="WWWWWWWWW1WWWWWWWWW2" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Ship To</td>
												<td class="stab"><ezf:inputText name="shipToLocNm" ezfName="shipToLocNm" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Contact</td>
												<td class="stab"><ezf:inputText name="xxGenlFldAreaTxt_CT" ezfName="xxGenlFldAreaTxt_CT" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Fax Contact</td>
												<td class="stab"><ezf:inputText name="xxGenlFldAreaTxt_FC" ezfName="xxGenlFldAreaTxt_FC" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Last Service Call Contact</td>
												<td class="stab"><ezf:inputText name="xxGenlFldAreaTxt_LS" ezfName="xxGenlFldAreaTxt_LS" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Telephone</td>
												<td class="stab"><ezf:inputText name="xxGenlFldAreaTxt_TL" ezfName="xxGenlFldAreaTxt_TL" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Fax Number</td>
												<td class="stab"><ezf:inputText name="xxGenlFldAreaTxt_FX" ezfName="xxGenlFldAreaTxt_FX" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Email</td>
												<td class="stab"><ezf:inputText name="dsCtacPntValTxt_EM" ezfName="dsCtacPntValTxt_EM" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Machine Location</td>
												<td class="stab"><ezf:inputText name="addrline1L3000If" ezfName="addrline1L3000If" /></td>
											</tr>
											<tr height="19">
												<td class="stab">MDS Customer</td>
												<td class="stab"><ezf:inputText name="xxYesNoNm_MC" ezfName="xxYesNoNm_MC" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Scheduled Date</td>
												<td class="stab"><ezf:inputText name="bllgSchdThruDt" ezfName="bllgSchdThruDt" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Reading Method</td>
												<td class="stab"><ezf:inputText name="mtrReadMethDescTxt" ezfName="mtrReadMethDescTxt" /></td>
											</tr>
											<tr height="57">
												<td class="stab" valign="top">Comment</td>
												<td class="stab"><ezf:textArea name="svcCmntTxt" ezfName="svcCmntTxt" otherAttr=" cols=\"27\" rows=\"4\""/></td>
											</tr>
										</table>
									</fieldset>
								</td>
<%-- ######################################## Product Detail ######################################## --%>
								<td>
									<fieldset style="height:363;">
										<legend><font size="2">Product Detail</font></legend>
										<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
											<col width="160" align="right" style="padding-right:5;">
											<col align="left">
											<tr height="19">
												<td class="stab">Contract Number</td>
												<td><ezf:anchor name="dsContrNum" ezfName="dsContrNum" ezfEmulateBtn="1" ezfGuard="MoveWin_ContrMaint" otherAttr=" ezfanchortext=\"\""><ezf:label name="dsContrNum" ezfName="dsContrNum" /></ezf:anchor></td>
											</tr>
											<tr height="19">
                                                <td class="stab">Contract Detail Status</td>
                                                <td class="stab"><ezf:inputText name="dsContrCtrlStsNm" ezfName="dsContrCtrlStsNm" /></td>
                                            </tr>
											<tr height="19">
												<td class="stab">Sub Contract Number</td>
												<td class="stab"><ezf:inputText name="dsSubContrPk" ezfName="dsSubContrPk" otherAttr=" style=\"text-align: left;\""/></td>
											</tr>
											<tr height="19">
												<td class="stab">Contract Branch</td>
												<td class="stab"><ezf:inputText name="xxCoaBrSrchTxt" ezfName="xxCoaBrSrchTxt" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Vendor Name</td>
												<td class="stab"><ezf:inputText name="prntVndNm" ezfName="prntVndNm" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Sub Contract Supplies Included</td>
												<td class="stab"><ezf:inputText name="splyInclFlg" ezfName="splyInclFlg" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Model Number</td>
												<td class="stab"><ezf:inputText name="mdlNm" ezfName="mdlNm" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Serial Number</td>
												<td><ezf:anchor name="serNum" ezfName="serNum" ezfEmulateBtn="1" ezfGuard="MoveWin_MachMaint" otherAttr=" ezfanchortext=\"\""><ezf:label name="serNum" ezfName="serNum" /></ezf:anchor></td>
											</tr>
											<tr height="19">
												<td class="stab">Install Date</td>
												<td class="stab"><ezf:inputText name="istlDt" ezfName="istlDt" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Start Date</td>
												<td class="stab"><ezf:inputText name="contrVrsnEffFromDt" ezfName="contrVrsnEffFromDt" /></td>
											</tr>
											<tr height="19">
												<td class="stab">End Date</td>
												<td class="stab"><ezf:inputText name="contrVrsnEffThruDt" ezfName="contrVrsnEffThruDt" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Reading Cycle</td>
												<td class="stab"><ezf:inputText name="bllgCycleDescTxt" ezfName="bllgCycleDescTxt" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Contract Indicator</td>
												<td class="stab"><ezf:inputText name="dsContrCatgDescTxt" ezfName="dsContrCatgDescTxt" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Invoicing Option</td>
												<td class="stab"><ezf:inputText name="dsInvTgtrTpDescTxt" ezfName="dsInvTgtrTpDescTxt" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Staples Inclusive</td>
												<td class="stab"><ezf:inputText name="svcTermCondDataDispTxt" ezfName="svcTermCondDataDispTxt" /></td>
											</tr>
											<tr height="19">
											    <td class="stab">Contract Supplies Included</td>
												<td class="stab"><ezf:inputText name="splyContrChkFlg" ezfName="splyContrChkFlg" /></td>
											</tr>
											<tr height="19">
												<td class="stab">eManage Users</td>
												<td><ezf:label name="xxSetFlg" ezfName="xxSetFlg" /></td>
											</tr>
											<tr height="19">
												<td class="stab">eManage Pilot</td>
												<td class="stab">&nbsp;<!-- <input type="text" value="WWWWWWWWW1WWWWWWWWW2"> --></td>
											</tr>
										</table>
									</fieldset>
								</td>
<%-- ######################################## UGW Meter Detail ######################################## --%>
								<td>
									<fieldset style="height:363;">
										<legend><font size="2">UGW Meter Detail</font></legend>
										<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
											<col width="140" align="right" style="padding-right:5;">
											<col align="left">
											<tr height="19">
												<td class="stab">Last Communication Date</td>
												<td class="stab"><ezf:inputText name="xxOpsDt_CM" ezfName="xxOpsDt_CM" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Shared</td>
												<td class="stab"><ezf:inputText name="shrDlrFlg" ezfName="shrDlrFlg" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Status</td>
												<td class="stab"><ezf:inputText name="iwrCondDescTxt" ezfName="iwrCondDescTxt" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Registration Date</td>
												<td class="stab"><ezf:inputText name="iwrRgtnDt" ezfName="iwrRgtnDt" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Deregistration Date</td>
												<td class="stab"><ezf:inputText name="iwrDeinsDt" ezfName="iwrDeinsDt" /></td>
											</tr>
											<tr height="19">
												<td class="stab">Last Reads Communicated</td>
												<td class="stab">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="2" align="left">
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
														<col width="150" align="center">
														<col width="150" align="center">
														<tr>
															<td class="pClothBs">Counter Name</td>
															<td class="pClothBs">Last Reading</td>
														</tr>
													</table>
													<div style="height:98; overflow-y:scroll; width:320;">
														<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A">
															<col width="150" align="left">
															<col width="150" align="right">
															<ezf:row ezfHyo="A">
															<tr height="24">
																<td class="stab"><ezf:inputText name="mtrLbDescTxt_A" ezfName="mtrLbDescTxt_A" value="BW Small Simplex" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="readMtrCnt_A" ezfName="readMtrCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</tr>
															</ezf:row>
															<ezf:skip>
															<tr height="24">
																<td class="stab"><input type="text" class="pPro" value="BW Small Duplex"></td>
																<td><label ezfout>WWWWWWWWW1</label></td>
															</tr>
															<tr height="24">
																<td class="stab"><input type="text" class="pPro" value="BW Large Simplex"></td>
																<td><label ezfout>WWWWWWWWW1</label></td>
															</tr>
															<tr height="24">
																<td class="stab"><input type="text" class="pPro" value="BW Large Duplex"></td>
																<td><label ezfout>WWWWWWWWW1</label></td>
															</tr>
															<tr height="24">
																<td class="stab"><input type="text" class="pPro" value="BW Large Duplex"></td>
																<td><label ezfout>WWWWWWWWW1</label></td>
															</tr>
															</ezf:skip>
														</table>
													</div>
												</td>
											</tr>
											<tr>
												<td colspan="2" align="center">
													<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="5">
														<col width="150" align="right">
														<col width="150" align="left">
														<tr>
															<td><ezf:inputButton name="Register" value="Register" htmlClass="pBtn6" /></td>
															<td><ezf:inputButton name="Deregister" value="Deregister" htmlClass="pBtn6" /></td>
														</tr>
														<tr>
															
															<td>
																<ezf:inputHidden name="dsContrPk_SP" ezfName="dsContrPk_SP" otherAttr=" id=\"dsContrPk_SP\""/>
																<ezf:inputHidden name="dsContrDtlPk_SP" ezfName="dsContrDtlPk_SP" otherAttr=" id=\"dsContrDtlPk_SP\""/>
															<% if ("Y".equals(cMsg.xxDplyCtrlFlg_BT.getValue())) { %>
																<input type="button" name="OpenWin_OrderSupplies" value="Order&nbsp;Supplies" onclick="if(popupConfForOrdSply()){buttonSendServer(this);}" class="pBtn8" >
															<% } else { %>
																<ezf:inputButton name="OpenWin_OrderSupplies" value="Order Supplies" htmlClass="pBtn8" />
															<% } %>
															</td>
															<td><ezf:inputButton name="OpenWin_OrderHistory" value="Order History" htmlClass="pBtn8" /></td>
														</tr>
														<tr>
															<td><ezf:inputButton name="OpenWin_ScheduleAgreement" value="Schedule Agreement" htmlClass="pBtn10" /></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
							</tr>
						</table>
						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="5">
							<% if (ZYPCommonFunc.hasValue(cMsg.contrMsgTxt)) { %>
								<tr>
									<td class="stab"><ezf:label name="contrMsgTxt" ezfName="contrMsgTxt" /></td>
								</tr>
							<% } %>
						</table>
						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="5">
							<col valign="top">
							<tr>
<%-- ######################################## Meter Read Capture ######################################## --%>
								<td>
									<fieldset>
										<legend><font size="2">Meter Read Capture</font></legend>
										<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="5">
											<col width="100%" align="center">
											<tr>
												<td>
													<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="220">
														<col width="84">
														<col width="84">
														<col width="64">
														<tr height="24">
															<td align="left"><ezf:select name="dsMtrReadTpGrpCd_BS" ezfName="dsMtrReadTpGrpCd_BS" ezfCodeName="dsMtrReadTpGrpCd_BD" ezfDispName="xxGenlFldAreaTxt_BC" otherEvent1=" onchange=\"sendServer('ChangeDsMtrReadTpGrpCd')\"" /></td>
															<td align="left" class="pClothBs">Task Number</td>
															<td align="left"><ezf:inputText name="svcTaskNum_B" ezfName="svcTaskNum_B" value="1234567890" otherAttr=" size=\"10\""/></td>
															<td align="left" class="pClothBs">In/Out Flag</td>
															<td align="left"><ezf:select name="dsTestCopyClsCd_BS" ezfName="dsTestCopyClsCd_BS" ezfBlank="1" ezfCodeName="dsTestCopyClsCd_BC" ezfDispName="dsTestCopyClsDescTxt_BD" /></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
														<col width="150" align="center"><!-- Counter Name -->
														<col width="190" align="center"><!-- Meter Type -->
														<col width="110" align="center"><!-- Date -->
														<col width="100" align="center"><!-- Current Read -->
														<col width="110" align="center"><!-- New Read -->
														<col width="390" align="center"><!-- Comment -->
														<tr height="24">
															<td class="pClothBs">Counter Name</td>
															<td class="pClothBs">Meter Type</td>
															<td class="pClothBs">Date</td>
															<td class="pClothBs">Current Read</td>
															<td class="pClothBs">New Read</td>
															<td class="pClothBs">Comment</td>
														</tr>
													</table>
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="B">
														<col width="150" align="left"><!-- Counter Name -->
														<col width="190" align="left"><!-- Meter Type -->
														<col width="110" align="center"><!-- Date -->
														<col width="100" align="right"><!-- Current Read -->
														<col width="110" align="left"><!-- New Read -->
														<col width="390" align="left"><!-- Comment -->
														<ezf:row ezfHyo="B">
														<tr height="24">
															<td><ezf:inputText name="mtrLbDescTxt_B" ezfName="mtrLbDescTxt_B" value="BW Small Simplex" ezfHyo="B" ezfArrayNo="0" otherAttr=" ezftoupper=\"\""/></td>
															<td>
																<ezf:select name="dsMtrReadTpCd_B" ezfName="dsMtrReadTpCd_B" ezfHyo="B" ezfArrayNo="0" ezfCodeName="dsMtrReadTpCd_B1" ezfDispName="dsMtrReadTpDescTxt_B" ezfCodeDispHyo="B" otherEvent1=" onchange=\"sendServer('OnChange_MeterType_Row')\"" otherAttr=" style=\"width:180\" id=\"dsMtrReadTpCd_B#{EZF_ROW_NUMBER}\""/>
															</td>
															<td>
																<ezf:inputText name="mtrReadDt_B" ezfName="mtrReadDt_B" value="99/99/9999" ezfHyo="B" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurMtrReadDt()\"" otherAttr=" size=\"10\" maxlength=\"10\""/>
																<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrReadDt_B', 4, '{EZF_ROW_NUMBER}');">
															</td>
															<td><ezf:label name="prevReadMtrCnt_B" ezfName="prevReadMtrCnt_B" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="readMtrCnt_B" ezfName="readMtrCnt_B" value="9999999999" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"14\""/></td>
															<td><ezf:inputText name="mtrEntryCmntTxt_B" ezfName="mtrEntryCmntTxt_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4W" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"53\" maxlength=\"400\""/></td>
														</tr>
														</ezf:row>
														<ezf:skip>
														<tr height="24">
															<td><input type="text" class="pPro" value="BW Small Duplex" ezftoupper=""></td>
															<td>
																<select style="width:180" class="pPro" name="dsMtrReadTpCd_B" onchange="sendServer('OnChange_MeterType_Row')">
																	<option value="1">Periodic Meter Reading</option>
																	<option value="2">Initial Meter Reading</option>
																	<option value="3">Periodic Meter Reading</option>
																	<option value="4">Final Meter Reading</option>
																</select>
															</td>
															<td>
																<input type="text" class="pPro" size="10" maxlength="10" value="99/99/9999" name="mtrReadDt_B" onblur="onBlurMtrReadDt()">
																<img src="./img/calendar.gif" class="pCalendar">
															</td>
															<td><label ezfout>WWWWWWWWW1</label></td>
															<td><input type="text" size="14" value="9999999999" ezftoupper=""></td>
															<td><input type="text" size="53" maxlength="400" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4W"></td>
														</tr>
														<tr height="24">
															<td><input type="text" class="pPro" value="BW Large Simplex" ezftoupper=""></td>
															<td>
																<select style="width:180" class="pPro" name="dsMtrReadTpCd_B" onchange="sendServer('OnChange_MeterType_Row')">
																	<option value="1">Periodic Meter Reading</option>
																	<option value="2">Initial Meter Reading</option>
																	<option value="3">Periodic Meter Reading</option>
																	<option value="4">Final Meter Reading</option>
																</select>
															</td>
															<td>
																<input type="text" class="pPro" size="10" maxlength="10" value="99/99/9999" name="mtrReadDt_B" onblur="onBlurMtrReadDt()">
																<img src="./img/calendar.gif" class="pCalendar">
															</td>
															<td><label ezfout>WWWWWWWWW1</label></td>
															<td><input type="text" size="14" value="9999999999" ezftoupper=""></td>
															<td><input type="text" size="53" maxlength="400" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4W"></td>
														</tr>
														<tr height="24">
															<td><input type="text" class="pPro" value="BW Large Duplex" ezftoupper=""></td>
															<td>
																<select style="width:180" class="pPro" name="dsMtrReadTpCd_B" onchange="sendServer('OnChange_MeterType_Row')">
																	<option value="1">Periodic Meter Reading</option>
																	<option value="2">Initial Meter Reading</option>
																	<option value="3">Periodic Meter Reading</option>
																	<option value="4">Final Meter Reading</option>
																</select>
															</td>
															<td>
																<input type="text" class="pPro" size="10" maxlength="10" value="99/99/9999" name="mtrReadDt_B" onblur="onBlurMtrReadDt()">
																<img src="./img/calendar.gif" class="pCalendar">
															</td>
															<td><label ezfout>WWWWWWWWW1</label></td>
															<td><input type="text" size="14" value="9999999999" ezftoupper=""></td>
															<td><input type="text" size="53" maxlength="400" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4W"></td>
														</tr>
														</ezf:skip>
													</table>
												</td>
											</tr>
											<tr>
												<td width="100%" align="right"><ezf:inputButton name="OpenWin_Estimate" value="Estimate" htmlClass="pBtn8" /></td>
											</tr>
										</table>
									</fieldset>
								</td>
							</tr>
						</table>
						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="5">
							<col valign="top">
							<tr>
<%-- ######################################## Meter History ######################################## --%>
								<td>
									<fieldset>
										<legend><font size="2">Meter History</font></legend>
										<table style="table-layout:fixed;" cellSpacing=0 cellPadding=0 border=0>
											<col width=10>
											<col width=40>
											<col width=105>
											<col width=30>
											<col width=110>
											<col width=80>
											<tbody>
												<tr height=20>
													<td></td>
													<td>From</td>
													<td>
														<ezf:inputText name="effFromDt" ezfName="effFromDt" value="01/01/2014" otherAttr=" size=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('effFromDt', 4);">
													</td>
													<td class="stab">Thru</td>
														<td>
															<ezf:inputText name="effThruDt" ezfName="effThruDt" value="01/01/2015" otherAttr=" size=\"10\""/>
															<img src="./img/calendar.gif" class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('effThruDt', 4);">
														</td>
														<td><ezf:inputButton name="SearchOfDate" value="Search" htmlClass="pBtn6" /></td>
												 </tr>
											</tbody>
										</table>
										<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
											<col width="100%" align="center">
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="5">
														<tr>
															<td>
																<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
																	<col width="175" align="center">
																	<tr height="24">
																		<td class="pClothBs">Counter Name</td>
																	</tr>
																</table>
																<div style="height:98; overflow-y:scroll; width:195;">
																	<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="C">
																		<col width="25" align="left">
																		<col width="150" align="right">
																		<ezf:row ezfHyo="C">
																		<tr height="24">
																			<td><ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" ezfHyo="C" ezfArrayNo="0" onClick="sendServer('OnChange_CounterName_Row','{EZF_ROW_NUMBER}')" otherAttr=" id=\"xxChkBox_C#{EZF_ROW_NUMBER}\""/></td>
																			<td class="stab"><ezf:inputText name="mtrLbDescTxt_C" ezfName="mtrLbDescTxt_C" value="All" ezfHyo="C" ezfArrayNo="0" otherAttr=" ezftoupper=\"\""/></td>
																		</tr>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="24">
																			<td><input type="checkbox" value="Y"></td>
																			<td class="stab"><input type="text" class="pPro" value="BW Small Simplex" ezftoupper=""></td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" value="Y"></td>
																			<td class="stab"><input type="text" class="pPro" value="BW Small Duplex" ezftoupper=""></td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" value="Y"></td>
																			<td class="stab"><input type="text" class="pPro" value="BW Large Simplex" ezftoupper=""></td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" value="Y"></td>
																			<td class="stab"><input type="text" class="pPro" value="BW Large Duplex" ezftoupper=""></td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" value="Y"></td>
																			<td class="stab"><input type="text" class="pPro" value="BW Small Simplex" ezftoupper=""></td>
																		</tr>
																		</ezf:skip>
																	</table>
																</div>
															</td>
															<td>
																<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
																	<col width="175" align="center">
																	<tr height="24">
																		<td class="pClothBs">Reading Type</td>
																	</tr>
																</table>
																<div style="height:98; overflow-y:scroll; width:195;">
																	<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="D">
																		<col width="25" align="left">
																		<col width="150" align="right">
																		<ezf:row ezfHyo="D">
																		<tr height="24">
																			<td><ezf:inputCheckBox name="xxChkBox_D" ezfName="xxChkBox_D" value="Y" ezfHyo="D" ezfArrayNo="0" onClick="sendServer('OnChange_ReadingType_Row','{EZF_ROW_NUMBER}')" otherAttr=" id=\"xxChkBox_D#{EZF_ROW_NUMBER}\""/></td>
																			<td class="stab"><ezf:inputText name="xxGenlFldAreaTxt_D" ezfName="xxGenlFldAreaTxt_D" value="All" ezfHyo="D" ezfArrayNo="0" otherAttr=" ezftoupper=\"\""/></td>
																		</tr>
																		</ezf:row>
																		<ezf:skip>
																		<tr>
																			<td><input type="checkbox" value="Y"></td>
																			<td class="stab"><input type="text" class="pPro" value="Initial Meter Reading" ezftoupper=""></td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" value="Y"></td>
																			<td class="stab"><input type="text" class="pPro" value="Periodic Meter Reading" ezftoupper=""></td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" value="Y"></td>
																			<td class="stab"><input type="text" class="pPro" value="Final Meter Reading" ezftoupper=""></td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" value="Y"></td>
																			<td class="stab"><input type="text" class="pPro" value="Service" ezftoupper=""></td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" value="Y"></td>
																			<td class="stab"><input type="text" class="pPro" value="Preventive Maintenance" ezftoupper=""></td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" value="Y"></td>
																			<td class="stab"><input type="text" class="pPro" value="Drum" ezftoupper=""></td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" value="Y"></td>
																			<td class="stab"><input type="text" class="pPro" value="Exchange Meter To" ezftoupper=""></td>
																		</tr>
																		</ezf:skip>
																	</table>
																</div>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>
													<!-- Prev/Next Page-->
													<table width="99%">
														<tr align="right">
															<td>
																<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																	<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																	<jsp:param name="TableNm"     value="A" />
																	<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
																	<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
																	<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
																</jsp:include>
															</td>
														</tr>
													</table>
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
														<col width="60" align="center"><!-- Insert as Actual -->
														<col width="30" align="center"><!-- Valid -->
														<col width="151" align="center"><!-- Counter Name -->
														<col width="101" align="center"><!-- Meter Type -->
														<col width="52" align="center"><!-- Reading -->
														<col width="75" align="center"><!-- Date -->
														<col width="95" align="center"><!-- Meter Count -->
														<col width="50" align="center"><!-- Test Copies -->
														<col width="84" align="center"><!-- Net Count -->
														<col width="84" align="center"><!-- Avg Count -->
														<col width="188" align="center"><!-- Comment -->
														<col width="76" align="center"><!-- Task Number -->
														<col width="44" align="center"><!-- IN/OUT Flag -->
														<tr height="48">
															<td class="pClothBs">Insert<br>as Actual</td>
															<td class="pClothBs">Valid</td>
															<td class="pClothBs">Counter Name</td>
															<td class="pClothBs">Meter Type</td>
															<td class="pClothBs">Reading</td>
															<td class="pClothBs">Date</td>
															<td class="pClothBs">Meter Count</td>
															<td class="pClothBs">Test<br>Copies</td>
															<td class="pClothBs">Net Count</td>
															<td class="pClothBs">Avg Count</td>
															<td class="pClothBs">Comment</td>
															<td class="pClothBs">Task<br>Number</td>
															<td class="pClothBs">In/Out<br>Flag</td>
														</tr>
													</table>
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="E">
														<col width="60" align="center"><!-- Insert as Actual -->
														<col width="30" align="center"><!-- Valid -->
														<col width="151" align="left"><!-- Counter Name -->
														<col width="101" align="left"><!-- Meter Type -->
														<col width="52" align="center"><!-- Reading -->
														<col width="75" align="center"><!-- Date -->
														<col width="95" align="right"><!-- Meter Count -->
														<col width="50" align="right"><!-- Test Copies -->
														<col width="84" align="right"><!-- Net Count -->
														<col width="84" align="right"><!-- Avg Count -->
														<col width="188" align="left"><!-- Comment -->
														<col width="76" align="right"><!-- Task Number -->
														<col width="44" align="center"><!-- In/Out Flag -->
														<ezf:row ezfHyo="E">
														<tr height="24">
															<td><ezf:inputButton name="InsertAsActual" value="Insert" ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn4" otherAttr=" id=\"InsertAsActual#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputCheckBox name="vldMtrFlg_E" ezfName="vldMtrFlg_E" value="Y" ezfHyo="E" ezfArrayNo="0" onClick="popupConfirm(this, {EZF_ROW_NUMBER})" otherAttr=" id=\"vldMtrFlg_E#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="mtrLbDescTxt_E" ezfName="mtrLbDescTxt_E" value="BW Small Simplex" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
															<td><ezf:inputText name="dsMtrReadTpDescTxt_E" ezfName="dsMtrReadTpDescTxt_E" value="Periodic Meter Reading" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"13\" ezftoupper=\"\""/></td>
															<td><ezf:label name="dsMtrReadTpGrpCd_E" ezfName="dsMtrReadTpGrpCd_E" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="mtrReadDt_E" ezfName="mtrReadDt_E" value="9999/99/99" ezfHyo="E" ezfArrayNo="0" otherAttr=" id=\"mtrReadDt_E#{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" size=\"14\" tabindex=\"-1\""/></td>
															<td><ezf:label name="readMtrCnt_E" ezfName="readMtrCnt_E" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:label name="testMtrCnt_E" ezfName="testMtrCnt_E" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:label name="avgCopyVolCnt_EN" ezfName="avgCopyVolCnt_EN" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:label name="avgCopyVolCnt_EA" ezfName="avgCopyVolCnt_EA" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="mtrEntryCmntTxt_E" ezfName="mtrEntryCmntTxt_E" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"27\" ezftoupper=\"\""/></td>
															<td><ezf:label name="svcTaskNum_E" ezfName="svcTaskNum_E" ezfHyo="E" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsTestCopyClsDescTxt_E" ezfName="dsTestCopyClsDescTxt_E" ezfHyo="E" ezfArrayNo="0" /></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_E" ezfName="xxRecHistCratTs_E" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_E\""/>
																<ezf:inputHidden name="xxRecHistCratByNm_E" ezfName="xxRecHistCratByNm_E" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_E\""/>
																<ezf:inputHidden name="xxRecHistUpdTs_E" ezfName="xxRecHistUpdTs_E" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_E\""/>
																<ezf:inputHidden name="xxRecHistUpdByNm_E" ezfName="xxRecHistUpdByNm_E" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_E\""/>
																<ezf:inputHidden name="xxRecHistTblNm_E" ezfName="xxRecHistTblNm_E" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_E\""/>
															</td>
														</tr>
														</ezf:row>
														<ezf:skip>
														<tr height="24">
															<td><input type="button" class="pBtn4" value="Insert" name="InsertAsActual" onclick="sendServer(this)"></td>
															<td><input type="checkbox" value="Y"></td>
															<td><input type="text" class="pPro" size="20" value="BW Small Simplex" ezftoupper=""></td>
															<td><input type="text" class="pPro" size="13" value="Periodic Meter Reading" ezftoupper=""></td>
															<td><label ezfout>S</label></td>
															<td><input type="text" value="9999/99/99" style="border:none;background-color:transparent;padding:0px;"></td>
															<td><label ezfout>WWWWWWWWW1</label></td>
															<td><label ezfout>123</label></td>
															<td><label ezfout>WWWWWWWWW1</label></td>
															<td><label ezfout>WWWWWWWWW1</label></td>
															<td><input type="text" class="pPro" size="27" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezftoupper=""></td>
															<td><label ezfout name="readMtrCnt_E" ezfname="readMtrCnt_E" ezfhyo="E">WWWWWWWWW1</label></td>
															<td><label ezfout name="readMtrCnt_E" ezfname="readMtrCnt_E" ezfhyo="E">Out</label></td>
														</tr>
														</ezf:skip>
													</table>
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<ezf:inputHidden name="svcCmntTxt_PP" ezfName="svcCmntTxt_PP" />
</center>
<%--SCRIPT--%>
<script type="text/javascript">
function onBlurMtrReadDt() {
	var numOfMtr = document.getElementsByName('mtrReadDt_B').length;
	if (numOfMtr > 0) {
		var mtrReadDt = document.getElementsByName('mtrReadDt_B')[0].value;
		for (var i = 0; i < numOfMtr; i++) {
			if (i > 0) {
				document.getElementsByName('mtrReadDt_B')[i].value = mtrReadDt;
			}
		}
	}
}
function popupConfirm(boolEnable, i) {
	if (boolEnable.checked) {
		if(window.confirm("Are you sure you want to make the read for date " + document.getElementsByName('mtrReadDt_E')[i].value + " valid?")){
			boolEnable.checked = true;
		} else {
			boolEnable.checked = false;
		}
	} else {
		if(window.confirm("Are you sure you want to invalidate read for " + document.getElementsByName('mtrReadDt_E')[i].value + "?")){
			boolEnable.checked = false;
		} else {
			boolEnable.checked = true;
		}
	}
}
function popupConfForOrdSply() {
	var svcCmntTxt = document.getElementsByName('svcCmntTxt_PP')[0].value;
	if (svcCmntTxt == '') {
		return true;
	} else if (window.confirm(svcCmntTxt)) {
		return true;
	}
	return false;
}

</script>
<%--SCRIPT--%>

<%-- **** Task specific area ends here **** --%>
