<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220922164824 --%>
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

			<!-- QC#18799(L3#127) Add LOB info with Contract Default Rule Screen  -->
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NSAL0800Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract Default Rule">

			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" otherAttr=" size=\"0\" id=\"xxRecHistCratTs\""/>
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm\""/>
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs\""/>
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm\""/>
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" otherAttr=" size=\"0\" id=\"xxRecHistTblNm\""/>

			<center>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td>
							<%-- #################################################### HEADER ################################################### --%>
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Interface Default Rule" class="pTab_ON"><a href="#">Def Rule</a></li>
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
							<div class="pTab_BODY">
								<table border="0" width="100%">
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
												<col width="120">
												<col width="230">
												<col width="20">
												<col width="60">
												<col width="110">
												<col width="20">
												<col width="120">
												<col width="110">
												<col width="20">
												<col width="200">
												<tr>
													<td class="stab">Source Type</td>
													<td>
														<ezf:select name="contrIntfcSrcTpCd_HS" ezfName="contrIntfcSrcTpCd_HS" ezfCodeName="contrIntfcSrcTpCd_H1" ezfDispName="contrIntfcSrcTpDescTxt_H1" otherEvent1=" onchange=\"sendServer('OnChangeContrIntfcSrcTpCd')\"" otherAttr=" style=\"width:210;\""/>
													</td>
													<td>&nbsp;</td>
													<td class="stab">Category</td>
													<td>
														<ezf:select name="dsContrClsCd_HS" ezfName="dsContrClsCd_HS" ezfCodeName="dsContrClsCd_H1" ezfDispName="dsContrClsDescTxt_H1" otherAttr=" style=\"width:90;\""/>
													</td>
													<td>&nbsp;</td>
													<td class="stab">Line Of Business</td>
													<td>
														<ezf:select name="svcLineBizCd_HS" ezfName="svcLineBizCd_HS" ezfCodeName="svcLineBizCd_H1" ezfDispName="svcLineBizDescTxt_H1" otherAttr=" style=\"width:90;\""/>
													</td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="Search" value="Search" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<%-- #################################################### DETAIL ################################################### --%>
								<hr>
								<table border="0" width="100%">
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
												<col width="120">
												<col width="150">
												<col width="20">
												<col width="60">
												<col width="150">
												<tr>
													<td class="stab">Effect Start Date</td>
													<td>
														<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
													</td>
													<td>&nbsp;</td>
													<td class="stab">Enable Flag</td>
													<td><ezf:inputCheckBox name="enblFlg_H1" ezfName="enblFlg_H1" value="Y" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>

								<table border="0" width="100%">
									<col width="300">
									<col width="300">
									<tr>
										<td valign="top">
											<fieldset style="height:130;">
												<legend>General Defaults</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="150">
													<col width="40">
													<col width="20">
													<col width="100">
													<tr>
														<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Account" >Default Lease Company</ezf:anchor></td>
														<td><ezf:inputText name="leaseCmpyCd_A1" ezfName="leaseCmpyCd_A1" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
														<td><ezf:inputButton name="GetAcctNm" value=">>" htmlClass="pBtn0" /></td>
														<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
													</tr>
													<tr>
														<td class="stab">Mtr Method Default</td>
														<td colspan="3">
															<ezf:select name="mtrReadMethCd_AS" ezfName="mtrReadMethCd_AS" ezfBlank="1" ezfCodeName="mtrReadMethCd_A1" ezfDispName="mtrReadMethDescTxt_A1" otherAttr=" style=\"width:250;\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Sales Credit</td>
														<td colspan="3">
															<ezf:select name="dsContrSlsCrTpCd_AS" ezfName="dsContrSlsCrTpCd_AS" ezfBlank="1" ezfCodeName="dsContrSlsCrTpCd_A1" ezfDispName="dsContrSlsCrTpDescTxt_A1" otherAttr=" style=\"width:250;\""/>
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td valign="top">
											<fieldset style="height:130;">
												<legend>Entitlement Defaults</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="170">
													<col width="20">
													<col width="20">
													<col width="120">
													<col width="40">
													<tr>
														<td class="stab">Auto Estimation</td>
														<td colspan=5>
															<ezf:select name="mtrEstTpCd_ES" ezfName="mtrEstTpCd_ES" ezfBlank="1" ezfCodeName="mtrEstTpCd_E1" ezfDispName="mtrEstTpDescTxt_E1" otherAttr=" style=\"width:260;\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Invoicing Option</td>
														<td colspan=4>
														<ezf:select name="dsInvTgtrTpCd_E1" ezfName="dsInvTgtrTpCd_E1" ezfCodeName="dsInvTgtrTpCd_E2" ezfDispName="dsInvTgtrTpDescTxt_E1" otherAttr=" style=\"width:320;\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Use Master Setting</td>
														<td colspan=4><ezf:inputCheckBox name="invSeptBaseUsgMstrFlg_E1" ezfName="invSeptBaseUsgMstrFlg_E1" value="Y" /></td>
													</tr>
													<tr>
														<td class="stab">Allocate Across All Machines</td>
														<td><ezf:inputCheckBox name="prcAllocByMachQtyFlg_E1" ezfName="prcAllocByMachQtyFlg_E1" value="Y" /></td>
														<td>&nbsp;</td>
														<td class="stab">Base Frequency</td>
														<td>
															<ezf:select name="bllgCycleCd_E2" ezfName="bllgCycleCd_E2" ezfBlank="1" ezfCodeName="bllgCycleCd_E1" ezfDispName="bllgCycleDescTxt_E1" otherAttr=" style=\"width:150;\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Print On Invoice</td>
														<td><ezf:inputCheckBox name="printDtlFlg_E1" ezfName="printDtlFlg_E1" value="Y" /></td>
														<td>&nbsp;</td>
														<td class="stab">Overage Frequency</td>
														<td>
															<ezf:select name="bllgCycleCd_E4" ezfName="bllgCycleCd_E4" ezfBlank="1" ezfCodeName="bllgCycleCd_E3" ezfDispName="bllgCycleDescTxt_E2" otherAttr=" style=\"width:150;\""/>
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
									<tr>
										<td valign="top">
											<fieldset style="height:80;">
												<legend>Renewal Defaults</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="50">
													<col width="210">
													<col width="5">
													<col width="80">
													<col width="40">
													<col width="15">
													<col width="70">
													<col width="40">
													<tr>
														<td class="stab">Type</td>
														<td>
															<ezf:select name="contrAutoRnwTpCd_BS" ezfName="contrAutoRnwTpCd_BS" ezfBlank="1" ezfCodeName="contrAutoRnwTpCd_B1" ezfDispName="contrAutoRnwTpDescTxt_B1" otherAttr=" style=\"width:205;\""/>
														</td>
														<td>&nbsp;</td>
														<td class="stab">#Days Before</td>
														<td><ezf:inputText name="befEndRnwDaysAot_B1" ezfName="befEndRnwDaysAot_B1" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/></td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td class="stab">Method</td>
														<td>
															<ezf:select name="rnwPrcMethCd_BS" ezfName="rnwPrcMethCd_BS" ezfBlank="1" ezfCodeName="rnwPrcMethCd_B1" ezfDispName="rnwPrcMethDescTxt_B1" otherAttr=" style=\"width:205;\""/>
														</td>
														<td>&nbsp;</td>
														<td class="stab">%Base</td>
														<td><ezf:inputText name="basePrcUpRatio_B1" ezfName="basePrcUpRatio_B1" otherAttr=" size=\"5\" maxlength=\"5\" style=\"text-align:right;\""/></td>
														<td>&nbsp;</td>
														<td class="stab">%Overage</td>
														<td><ezf:inputText name="mtrPrcUpRatio_B1" ezfName="mtrPrcUpRatio_B1" otherAttr=" size=\"5\" maxlength=\"5\" style=\"text-align:right;\""/></td>
													</tr>
													<tr>
														<td colspan=8 class="stab">Extended Month
														&nbsp;<ezf:inputText name="rnwExtMthAot_B1" ezfName="rnwExtMthAot_B1" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/></td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td valign="top">
											<fieldset style="height:80;">
												<legend>Upliftment Defaults</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="50">
													<col width="180">
													<col width="5">
													<col width="80">
													<col width="40">
													<col width="15">
													<col width="70">
													<col width="40">
													<tr>
														<td class="stab">Type</td>
														<td>
															<ezf:select name="contrUplftTpCd_FS" ezfName="contrUplftTpCd_FS" ezfBlank="1" ezfCodeName="contrUplftTpCd_F1" ezfDispName="contrUplftTpDescTxt_F1" otherAttr=" style=\"width:175;\""/>
														</td>
														<td>&nbsp;</td>
														<td class="stab">#Days Before</td>
														<td><ezf:inputText name="befEndUplftDaysAot_F1" ezfName="befEndUplftDaysAot_F1" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/></td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td class="stab">Method</td>
														<td>
															<ezf:select name="uplftPrcMethCd_FS" ezfName="uplftPrcMethCd_FS" ezfBlank="1" ezfCodeName="uplftPrcMethCd_F1" ezfDispName="uplftPrcMethDescTxt_F1" otherAttr=" style=\"width:175;\""/>
														</td>
														<td>&nbsp;</td>
														<td class="stab">%Base</td>
														<td><ezf:inputText name="uplftBasePrcUpRatio_F1" ezfName="uplftBasePrcUpRatio_F1" otherAttr=" size=\"5\" maxlength=\"5\" style=\"text-align:right;\""/></td>
														<td>&nbsp;</td>
														<td class="stab">%Overage</td>
														<td><ezf:inputText name="uplftMtrPrcUpRatio_F1" ezfName="uplftMtrPrcUpRatio_F1" otherAttr=" size=\"5\" maxlength=\"5\" style=\"text-align:right;\""/></td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
									<tr>
										<td valign="top">
											<fieldset style="height:130;">
												<legend>Overage Defaults</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="120">
													<col width="60">
													<col width="20">
													<col width="20">
													<col width="80">
													<col width="20">
													<tr>
														<td class="stab">Start Read Validation</td>
														<td colspan=5>
															<ezf:select name="startReadVldTpCd_CS" ezfName="startReadVldTpCd_CS" ezfBlank="1" ezfCodeName="startReadVldTpCd_C1" ezfDispName="startReadVldTpDescTxt_C1" otherAttr=" style=\"width:300;\""/>
														</td>
													</tr>
													<tr>
														<td colspan=6 class="stab">Use Install Call Meter Read
														&nbsp;<ezf:inputCheckBox name="istlCallMtrReadUseFlg_C1" ezfName="istlCallMtrReadUseFlg_C1" value="Y" /></td>
													</tr>
													<tr>
														<td class="stab">Counter Rollover %</td>
														<td colspan=5><ezf:inputText name="bllgRollOverRatio_C1" ezfName="bllgRollOverRatio_C1" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align:right;\""/></td>
													</tr>
													<tr>
														<td class="stab">Preview Amount</td>
														<td colspan=5><ezf:inputText name="bllgLimitAmt_C1" ezfName="bllgLimitAmt_C1" otherAttr=" size=\"20\" maxlength=\"20\" style=\"text-align:right;\""/></td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td valign="top">
											<fieldset style="height:130;">
												<legend>Bill Through Date Default</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="240">
													<col width="140">
													<tr>
														<td class="stab">Mark As Billed Schedule end date Prior To</td>
														<td>
															<ezf:select name="bllgThruTpCd_GS" ezfName="bllgThruTpCd_GS" ezfBlank="1" ezfCodeName="bllgThruTpCd_G1" ezfDispName="bllgThruTpDescTxt_G1" otherAttr=" style=\"width:110;\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Specific Date</td>
														<td><ezf:inputText name="bllgThruDt_G1" ezfName="bllgThruDt_G1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgThruDt_G1', 4);" ></td>
													</tr>
													<tr>
														<td class="stab">Period End Date Ctrl</td>
														<td>
															<ezf:select name="contrCloDay_GS" ezfName="contrCloDay_GS" ezfBlank="1" ezfCodeName="contrCloDay_G1" ezfDispName="xxEdtDescTxt_G1" otherAttr=" style=\"width:90;\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Invoice Date Ctrl</td>
														<td>
															<ezf:select name="contrBllgDay_GS" ezfName="contrBllgDay_GS" ezfBlank="1" ezfCodeName="contrBllgDay_G1" ezfDispName="xxEdtDescTxt_G2" otherAttr=" style=\"width:90;\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Base Billing Timing</td>
														<td>
															<ezf:select name="bllgTmgTpCd_GS" ezfName="bllgTmgTpCd_GS" ezfBlank="1" ezfCodeName="bllgTmgTpCd_G1" ezfDispName="bllgTmgTpDescTxt_G1" otherAttr=" style=\"width:110;\""/>
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
									<tr>
										<td valign="top">
											<fieldset style="height:110;">
												<legend>Interface Maintenance form Default</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="120">
													<col width="140">
													<tr>
														<td class="stab">Allow Data Correction</td>
														<td ><ezf:inputCheckBox name="allwDataCrctFlg_D1" ezfName="allwDataCrctFlg_D1" value="Y" /></td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td valign="top">
											<fieldset style="height:110;">
												<legend>Effectivity Defaults</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="280">
													<col width="230">
													<tr>
														<td class="stab">Stub Prevention For Start</td>
														<td>
															<ezf:select name="stubPrepFromTpCd_IS" ezfName="stubPrepFromTpCd_IS" ezfBlank="1" ezfCodeName="stubPrepFromTpCd_I1" ezfDispName="stubPrepFromTpDescTxt_I1" otherAttr=" style=\"width:225;\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Stub Prevention For End</td>
														<td>
															<ezf:select name="stubPrepThruTpCd_IS" ezfName="stubPrepThruTpCd_IS" ezfBlank="1" ezfCodeName="stubPrepThruTpCd_I1" ezfDispName="stubPrepThruTpDescTxt_I1" otherAttr=" style=\"width:225;\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Auto Derive Effectivity For Add To Fleet</td>
														<td ><ezf:inputCheckBox name="autoEffFleetFlg_I1" ezfName="autoEffFleetFlg_I1" value="Y" /></td>
													</tr>
													<tr>
														<td class="stab">Auto Derive Effectivity For Add To Aggregate</td>
														<td ><ezf:inputCheckBox name="autoEffAggrFlg_I1" ezfName="autoEffAggrFlg_I1" value="Y" /></td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
								</table>
							</div><!--  pTab_BODY  -->
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
