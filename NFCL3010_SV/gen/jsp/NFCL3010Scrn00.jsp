<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180405091248 --%>
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
			<input type="hidden" name="pageID" value="NFCL3010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Batch Receipt Search">
<center>
	<table width="1128" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table width="100%" border="0" cellpadding="1" cellspacing="0" align="center">
						<tr>
							<td>
								<table border="1" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="150">
												<col width="210">
												<col width="5">
												<col width="150">
												<col width="270">
												<col width="5">
												<col width="200">
												<col width="260">
												<tr>
													<td class="stab" colspan="2"><b>Batch</b></td>
													<td></td>
													<td class="stab" colspan="2"><b>Receipt</b></td>
													<td></td>
													<td class="stab" colspan="2"><b>Common</b></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab" style="hight:37px;">Receipt Source</td>
													<td>
														<ezf:select name="arRcptSrcCd_H" ezfName="arRcptSrcCd_H" ezfBlank="1" ezfCodeName="arRcptSrcCd_LC" ezfDispName="arRcptSrcDescTxt_LD" otherAttr=" style=\"width:220px;\" tabindex=\"1502\""/>
													</td>
													<td></td>
													<td class="stab" style="hight:37px;"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_LinkCustomer" otherAttr=" tabindex=\"1502\"">Customer Number(*)</ezf:anchor></td>
													<td><ezf:inputText name="dsAcctNum_H" ezfName="dsAcctNum_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"12\" maxlength=\"28\" tabindex=\"1502\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab">Creation Date</td>
													<td>
														<ezf:inputText name="cratDt_H1" ezfName="cratDt_H1" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1502\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cratDt_H1', 4);" >
														-
														<ezf:inputText name="cratDt_H2" ezfName="cratDt_H2" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1502\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cratDt_H2', 4);" >
													</td>
												</tr>
												<tr>
													<td class="stab" style="hight:37px;">Batch Name(*)</td>
													<td><ezf:inputText name="arBatRcptNm_H" ezfName="arBatRcptNm_H" value="BOA_CHI_20150303_001" otherAttr=" size=\"31\" maxlength=\"55\" tabindex=\"1502\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab" style="hight:37px;"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_LinkCustomer" otherAttr=" tabindex=\"1502\"">Customer Name(*)</ezf:anchor></td>
													<td><ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" value="BOA_CHI_20150303_001" otherAttr=" size=\"29\" maxlength=\"55\" tabindex=\"1502\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab"><ezf:anchor name="dsBankAcctNm_L1" ezfName="dsBankAcctNm_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_1" otherAttr=" tabindex=\"1502\"">Remittance Bank Name(*)</ezf:anchor></td>
													<td><ezf:inputText name="dsBankAcctNm_H" ezfName="dsBankAcctNm_H" otherAttr=" size=\"31\" maxlength=\"80\" tabindex=\"1502\""/></td>
												</tr>
												<tr>
													<td class="stab">Batch Status</td>
													<td>
														<ezf:select name="arBatRcptStsCd_H" ezfName="arBatRcptStsCd_H" ezfBlank="1" ezfCodeName="arBatRcptStsCd_LC" ezfDispName="arBatRcptStsNm_LD" otherAttr=" style=\"width:220px;\" tabindex=\"1502\""/>
													</td>
													<td></td>
													<td class="stab">Receipt Dates</td>
													<td>
														<ezf:inputText name="rcptDt_H1" ezfName="rcptDt_H1" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1502\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rcptDt_H1', 4);" >
														-
														<ezf:inputText name="rcptDt_H2" ezfName="rcptDt_H2" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1502\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rcptDt_H2', 4);" >
													</td>
													<td></td>
													<td class="stab"><ezf:anchor name="dsBankBrNm_L1" ezfName="dsBankBrNm_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_2" otherAttr=" tabindex=\"1502\"">Remittance Branch Name(*)</ezf:anchor></td>
													<td><ezf:inputText name="dsBankBrNm_H" ezfName="dsBankBrNm_H" otherAttr=" size=\"31\" maxlength=\"80\" tabindex=\"1502\""/></td>
												</tr>
												<tr>
													<td class="stab">Lockbox File Name(*)</td>
													<td><ezf:inputText name="arLockBoxFileNm_H" ezfName="arLockBoxFileNm_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX1XXXX2XXXX3XXXX4XXXX5" otherAttr=" size=\"31\" maxlength=\"50\" tabindex=\"1502\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab">Receipt Numbers</td>
													<td><ezf:inputText name="rcptChkNum_H1" ezfName="rcptChkNum_H1" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"12\" maxlength=\"15\" tabindex=\"1502\" ezftoupper=\"\""/>
													    &nbsp;-&nbsp;<ezf:inputText name="rcptChkNum_H2" ezfName="rcptChkNum_H2" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"12\" maxlength=\"15\" tabindex=\"1502\" ezftoupper=\"\""/>
													</td>
													<td></td>
													<td class="stab"><ezf:anchor name="dsBankAcctNum_L1" ezfName="dsBankAcctNum_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_4" otherAttr=" tabindex=\"1502\"">Remittance Account Number(*)</ezf:anchor></td>
													<td><ezf:inputText name="dsBankAcctNum_H" ezfName="dsBankAcctNum_H" otherAttr=" size=\"31\" maxlength=\"14\" tabindex=\"1502\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab">Lockbox</td>
													<td>
														<ezf:select name="arLockBoxCd_H" ezfName="arLockBoxCd_H" ezfBlank="1" ezfCodeName="arLockBoxCd_LC" ezfDispName="arLockBoxNm_LD" otherAttr=" style=\"width:220px;\" tabindex=\"1502\""/>
													</td>
													<td></td>
													<td class="stab">Receipt Doc Numbers</td>
													<td><ezf:inputText name="rcptNum_H1" ezfName="rcptNum_H1" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"12\" maxlength=\"8\" tabindex=\"1502\" ezftoupper=\"\""/>
													    &nbsp;-&nbsp;<ezf:inputText name="rcptNum_H2" ezfName="rcptNum_H2" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"12\" maxlength=\"8\" tabindex=\"1502\" ezftoupper=\"\""/>
													</td>
													<td></td>
													<td class="stab">&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">Lockbox Batch</td>
													<td><ezf:inputText name="arLockBoxBatNum_H" ezfName="arLockBoxBatNum_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6XXXX1XXXX2XXXX3XXXX4XXXX5" otherAttr=" size=\"3\" maxlength=\"3\" tabindex=\"1502\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab">Receipt Amount</td>
													<td><ezf:inputText name="funcRcptAmt_H1" ezfName="funcRcptAmt_H1" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"12\" maxlength=\"19\" tabindex=\"1502\" ezftoupper=\"\""/>
													    &nbsp;-&nbsp;<ezf:inputText name="funcRcptAmt_H2" ezfName="funcRcptAmt_H2" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"12\" maxlength=\"19\" tabindex=\"1502\" ezftoupper=\"\""/>
													</td>
													<td></td>
													<td class="stab">&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">&nbsp;</td>
													<td class="stab">&nbsp;</td>
													<td></td>
													<td class="stab">Receipt Status</td>
													<td colspan = "3">
														<ezf:select name="arRcptStsCd_H" ezfName="arRcptStsCd_H" ezfBlank="1" ezfCodeName="arRcptStsCd_LC" ezfDispName="arRcptStsDescTxt_LD" otherAttr=" style=\"width:210px;\" tabindex=\"1502\""/>
													</td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">&nbsp;</td>
													<td>&nbsp;</td>
													<td></td>
													<td class="stab">Cash App Status</td>
													<td colspan = "3">
														<ezf:select name="arCashApplyStsCd_H" ezfName="arCashApplyStsCd_H" ezfBlank="1" ezfCodeName="arCashApplyStsCd_LC" ezfDispName="arCashApplyStsDescTxt_LD" otherAttr=" style=\"width:210px;\" tabindex=\"1502\""/>
													</td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">&nbsp;</td>
													<td>&nbsp;</td>
													<td></td>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_LinkCollector" otherAttr=" tabindex=\"1502\"">Collector</ezf:anchor></td>
													<td colspan = "3">
														<ezf:inputText name="psnCd_H" ezfName="psnCd_H" value="XXXX1XXXX2XXX" otherAttr=" size=\"12\" maxlength=\"8\" tabindex=\"1502\" ezftoupper=\"\""/>
														<ezf:inputButton name="Click_SearchCollectorName" value=">>" htmlClass="pBtn0" otherAttr=" tabindex=\"1502\""/>
														<ezf:inputText name="fullPsnNm_H" ezfName="fullPsnNm_H" value="XXXX1XXXX2XXX" otherAttr=" size=\"33\" maxlength=\"13\" tabindex=\"-1\" ezftoupper=\"\""/>
													</td>
													<td align="right">
														<!-- <input type="button" class="pBtn8" value="New Receipt" name="Click_New" onclick="sendServer(this)" tabindex="1502" > -->
														<ezf:inputButton name="Click_Search" value="Search" htmlClass="pBtn8" otherAttr=" tabindex=\"1502\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
	<%-- ######################################## DETAIL ######################################## --%>

					<%-- ###TAB - BODY --%>
					<div class="">
						<table border="0" cellpadding="1" cellspacing="0" width="1003">
							<tr>
								<td>
									<table width="100%">
										<col valign="top">
											<tr>
												<td>
													<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
												</td>
												<td align="right">
													<ezf:skip>
														<table border="0" cellpadding="1" cellspacing="0">
															<col width="54"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="10">
															<col width="50">
															<col width="50">
															<tr>
																<td class="stab">Showing</td>
																<td class="pOut">1</td>
																<td class="stab">to</td>
																<td class="pOut">20</td>
																<td class="stab">of</td>
																<td class="pOut">20</td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
																<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
															</tr>
														</table>
													</ezf:skip>
													<%-- Pagenation --%>
													<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
														<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
														<jsp:param name="TableNm"     value="A" />
														<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
														<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
														<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
													</jsp:include>
												</td>
											</tr>
										</col>
									</table>
								</td>
							</tr>
							<div id="parentBoxA">
								<table>
									<tr>
										<td width="10"></td>
										<td>
											<div style="float:left; display:block"> <!-- left table -->
												<div id="leftTblHead" style="display:block;"></div>
												<div id="leftTbl" style="float:left; display:block; height:258px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
											</div>  <!-- left table -->
											<div style="float:left"> <!-- right table -->
												<div id="rightTblHead" style="width:1083px; display:block; overflow:hidden; margin:0px;padding:0px;">
													<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0"  id="AHEAD" width="1945px" style="margin-right:20px">
														<col width="120" align="center">
														<col width="120" align="center">
														<col width="95" align="center">
														<col width="95" align="center">
														<col width="95" align="center"><!-- On Account -->
														<col width="95" align="center">
														<col width="95" align="center">
														<col width="85"  align="center">
														<col width="100" align="center">
														<col width="160" align="center"><!-- costomer name -->
														<col width="170" align="center">
														<col width="115" align="center">
														<col width="80" align="center">
														<col width="65" align="center">
														<col width="60" align="center"><!-- Ctrl Count -->
														<col width="80" align="center">
														<col width="85" align="center">
														<col width="85" align="center">
														<col width="85" align="center"><!-- Lockbox Batch -->
														<tr>
															<td id="AH0"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'rcptNum_A')">Receipt<br>Doc#<img id="sortIMG.rcptNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH1" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'rcptChkNum_A')">Receipt#<img id="sortIMG.rcptChkNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH2" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'funcRcptAmt_A')">Receipt<br>Amount<img id="sortIMG.funcRcptAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH3" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'funcApplyAmt_A')">Applied<br>Amount<img id="sortIMG.funcApplyAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH4" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxTotAmt_A')">On Account<img id="sortIMG.xxTotAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH5" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'funcRcptRmngBalAmt_A')">Unapplied<img id="sortIMG.funcRcptRmngBalAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH6" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arRcptTrxTpNm_A')">Receipt<br>Type<img id="sortIMG.arRcptTrxTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH7" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'rcptDt_A')">Receipt<br>Date<img id="sortIMG.rcptDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH8" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'payerCustCd_A')">Customer#<img id="sortIMG.payerCustCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH9" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A')">Customer<br>Name<img id="sortIMG.dsAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH10"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arBatRcptNm_A')">Batch<br>Name<img id="sortIMG.arBatRcptNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH11"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arRcptSrcNm_A')">Receipt<br>Source<img id="sortIMG.arRcptSrcNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH12"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arBatRcptDt_A')">Batch<br>Date<img id="sortIMG.arBatRcptDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH13"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arBatRcptStsNm_A')">Batch<br>Status<img id="sortIMG.arBatRcptStsNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH14"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arBatRcptCnt_A')">Ctrl<br>Count<img id="sortIMG.arBatRcptCnt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH15"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arBatRcptAmt_A')">Control<br>Amount<img id="sortIMG.arBatRcptAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH16"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arLockBoxFileNm_A')">Lockbox<br>File Nm<img id="sortIMG.arLockBoxFileNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH17"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arLockBoxNm_A')">Lockbox<br>Name<img id="sortIMG.arLockBoxNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH18"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arLockBoxBatNum_A')">Lockbox<br>Batch<img id="sortIMG.arLockBoxBatNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														</tr>
													</table>
												</div><!-- rightTblHead-->
												<div id="rightTbl" style="width:1100px; height:275px; display:block; overflow:scroll; margin:0px; padding:0px;" >
													<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="1915px" >
														<col width="120" align="left">
														<col width="120" align="left">
														<col width="95" align="right">
														<col width="95" align="right">
														<col width="95" align="right"><!-- On Account -->
														<col width="95" align="right">
														<col width="95" align="left">
														<col width="85" align="left">
														<col width="100" align="left">
														<col width="160" align="left"><!-- costomer name -->
														<col width="170" align="left">
														<col width="115" align="left">
														<col width="80" align="left">
														<col width="65" align="left">
														<col width="60" align="right"><!-- Ctrl Count -->
														<col width="80" align="right">
														<col width="85" align="left">
														<col width="85" align="left">
														<col width="85" align="left"><!-- Lockbox Batch -->
														<ezf:row ezfHyo="A">
															<tr id="id_row{EZF_ROW_NUMBER}">
																<td>
																	<div id="rcptNum_A#{EZF_ROW_NUMBER}">
																		<ezf:anchor name="Click_Link RcptNum" ezfName="Click_LinkRcptNum" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_LinkRcptNum" otherAttr=" id=\"rcptNum_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\" tabindex=\"1502\"">
																			<ezf:label name="rcptNum_A" ezfName="rcptNum_A" ezfHyo="A" ezfArrayNo="0" />
																		</ezf:anchor>
																	</div>
																</td>
																<td><ezf:inputText name="rcptChkNum_A" ezfName="rcptChkNum_A" value="CashCashCashCas" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="funcRcptAmt_A" ezfName="funcRcptAmt_A" value="812.25" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>
																<td><ezf:inputText name="funcApplyAmt_A" ezfName="funcApplyAmt_A" value="812.25" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>
																<td><ezf:inputText name="xxTotAmt_A" ezfName="xxTotAmt_A" value="812.25" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>
																<td><ezf:inputText name="funcRcptRmngBalAmt_A" ezfName="funcRcptRmngBalAmt_A" value="812.25" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>
																<td><ezf:inputText name="arRcptTrxTpNm_A" ezfName="arRcptTrxTpNm_A" value="Cash" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="rcptDt_A" ezfName="rcptDt_A" value="10/08/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="payerCustCd_A" ezfName="payerCustCd_A" value="1015536" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="NJ DEPT OF LAW & PUBLIC" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td>
																	<div id="arBatRcptNm_A#{EZF_ROW_NUMBER}">
																		<ezf:anchor name="Click_LinkBatchName_A" ezfName="Click_LinkBatchName_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_LinkBatchName_A" otherAttr=" id=\"arBatRcptNm_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\" tabindex=\"1502\"">
																			<ezf:label name="arBatRcptNm_A" ezfName="arBatRcptNm_A" ezfHyo="A" ezfArrayNo="0" />
																		</ezf:anchor>
																	</div>
																</td>
																<td><ezf:inputText name="arRcptSrcNm_A" ezfName="arRcptSrcNm_A" value="ONA MISDIRECTED" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="arBatRcptDt_A" ezfName="arBatRcptDt_A" value="10/08/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="arBatRcptStsNm_A" ezfName="arBatRcptStsNm_A" value="Closed" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="arBatRcptCnt_A" ezfName="arBatRcptCnt_A" value="35" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>
																<td><ezf:inputText name="arBatRcptAmt_A" ezfName="arBatRcptAmt_A" value="171256.21" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>
																<td><ezf:inputText name="arLockBoxFileNm_A" ezfName="arLockBoxFileNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="arLockBoxNm_A" ezfName="arLockBoxNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="arLockBoxBatNum_A" ezfName="arLockBoxBatNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" />
																</td>
															</tr>
														</ezf:row>
													</table>
												</div><!-- rightTbl-->
											</div><!-- right table-->
										</td>
									</tr>
								</table>
							</div><!-- parentBoxA -->
							<tr><td><hr></td></tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>
</center>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", 2, true);
</script>

<%-- **** Task specific area ends here **** --%>
