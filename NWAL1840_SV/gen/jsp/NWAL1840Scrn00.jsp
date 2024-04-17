<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20231018091012 --%>
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
			<input type="hidden" name="pageID" value="NWAL1840Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Scheduling Agreement Entry">
<center>
	<table border="1" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%"><li title = "Schd Agmt Entry" class="pTab_ON" ><a href="#">Schd Agmt Entry</a></li></td>
								<td valign="bottom" align="center"><a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a></td>
								<td valign="bottom" align="center"><a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a></td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<%-- ######################################## HEADER ######################################## --%>
				<div class="pTab_BODY">
					<table border="0" width="99%" align="center" style="table-layout:fixed;">
						<col width="355">
						<col width="330">
						<col width="380">
						<tr valign="top">
							<td rowspan="2">
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
									<col width="130">
									<col width="">
									<tr height="22">
										<td class="stab">Schd. Agreement Number</td>
										<td>
											<ezf:inputText name="schdAgmtNum" ezfName="schdAgmtNum" value="1234567890" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" />
											<!-- START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] -->
											<ezf:inputButton name="MoveWin_MemoEntry" value="Notes" htmlClass="pBtn5" />
											<!-- END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] -->
										</td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="dsOrdCatgDescTxt_LK" ezfName="dsOrdCatgDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Category" otherAttr=" id=\"dsOrdCatgDescTxt_LK\" ezfanchortext=\"\"">Category (*)</ezf:anchor></td>
										<td><ezf:inputText name="dsOrdCatgDescTxt" ezfName="dsOrdCatgDescTxt" value="WWWWWWWWW1WWWWWWWWW2" otherEvent1="OnBlur_DeriveFromCategory" otherAttr=" size=\"30\" maxlength=\"50\" ezffocusout=\"OnBlur_DeriveFromCategory\""/>
									</tr>
									</tr>
									<tr height="22">
										<td class="stab">Reason Code</td>
										<td>
											<ezf:select name="dsOrdTpCd" ezfName="dsOrdTpCd" ezfBlank="1" ezfCodeName="dsOrdTpCd_CD" ezfDispName="dsOrdTpDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_Reason')\"" otherAttr=" style=\"width:215px;\""/>
										</td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="prcCatgCd_LK" ezfName="prcCatgCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PriceList" otherAttr=" id=\"prcCatgCd_LK\" ezfanchortext=\"\"">Price List</ezf:anchor></td>
										<td><ezf:inputText name="prcCatgNm" ezfName="prcCatgNm" value="FLOOR, CSA" otherAttr=" size=\"30\" maxlength=\"50\" tabindex=\"0\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="mdlNm_LK" ezfName="mdlNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_MdlNm" otherAttr=" id=\"mdlNm_LK\" ezfanchortext=\"\"">Model Name</ezf:anchor></td>
										<td><ezf:inputText name="mdlNm" ezfName="mdlNm" value="WWWWWWWWW1WWWWWWWWW2WW" otherAttr=" size=\"30\" maxlength=\"90\" tabindex=\"0\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="serNum_LK" ezfName="serNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SerNum" otherAttr=" id=\"serNum_LK\" ezfanchortext=\"\"">Serial #</ezf:anchor></td>
										<td><ezf:inputText name="serNum" ezfName="serNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherEvent1="OnBlur_DeriveFromSerNum" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"0\" ezffocusout=\"OnBlur_DeriveFromSerNum\""/></td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="svcConfigMstrPk_LK" ezfName="svcConfigMstrPk_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ServiceConfig" otherAttr=" id=\"svcConfigMstrPk_LK\" ezfanchortext=\"\"">Configuration ID</ezf:anchor></td>
										<td><ezf:inputText name="svcConfigMstrPk" ezfName="svcConfigMstrPk" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWW" otherAttr=" size=\"30\" maxlength=\"28\" tabindex=\"0\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="dsContrNum_LK" ezfName="dsContrNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Contract" otherAttr=" id=\"svcConfigMstrPk_LK\" ezfanchortext=\"\"">Contract #</ezf:anchor></td>
										<td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherEvent1="OnBlur_DeriveFromContract" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"0\" ezffocusout=\"OnBlur_DeriveFromContract\""/></td>
									</tr>
									<tr height="22">
										<td class="stab">Contract End Date</td>
										<td><ezf:inputText name="contrVrsnEffThruDt" ezfName="contrVrsnEffThruDt" value="01/04/2016" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/></td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
									<col width="95">
									<col width="100">
									<col width="45">
									<col width="100">
									<tr height="22">
										<td class="stab">Schdule Status</td>
										<td colspan="3"><ezf:inputText name="schdAgmtStsDescTxt" ezfName="schdAgmtStsDescTxt" value="ACTIVE" otherAttr=" size=\"34\" maxlength=\"50\" tabindex=\"-1\""/>
									</tr>
									<tr height="22">
										<td class="stab">Valid From</td>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<tr style="padding:0;">
													<td><ezf:inputText name="schdAgmtVldFromDt" ezfName="schdAgmtVldFromDt" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"0\""/></td>
													<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('schdAgmtVldFromDt', 4);" tabindex="0"></td>
												</tr>
											</table>
										</td>
										<td class="stab">Valid To</td>
										<td>
											<table border="0" cellpadding="1" cellspacing="1">
												<tr style="padding:0;">
													<td><ezf:inputText name="schdAgmtVldThruDt" ezfName="schdAgmtVldThruDt" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"0\""/></td>
													<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('schdAgmtVldThruDt', 4);" tabindex="0"></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr height="22">
										<td class="stab">Delivery Hold</td>
										<td colspan="3">
											<ezf:select name="schdAgmtDelyHldCd" ezfName="schdAgmtDelyHldCd" ezfBlank="1" ezfCodeName="schdAgmtDelyHldCd_CD" ezfDispName="schdAgmtDelyHldDescTxt_NM" otherAttr=" style=\"width: 202px\" tabindex=\"0\""/>
										</td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="pmtTermCashDiscDescTxt_LK" ezfName="pmtTermCashDiscDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PaymentTerms" otherAttr=" id=\"pmtTermCashDiscDescTxt_LK\" ezfanchortext=\"\"">Payment Terms</ezf:anchor></td>
										<td colspan="3">
											<ezf:inputText name="pmtTermCashDiscDescTxt" ezfName="pmtTermCashDiscDescTxt" value="NET 30" otherAttr=" size=\"30\" maxlength=\"50\" tabindex=\"0\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr height="22">
										<td class="stab">Source Name</td>
										<td colspan="3"><ezf:inputText name="cpoSrcTpDescTxt" ezfName="cpoSrcTpDescTxt" value="(SOM) SALES ORDER MAKER" otherAttr=" size=\"34\" maxlength=\"50\" tabindex=\"-1\""/>
									</tr>
									<tr height="22">
										<td class="stab">Source Reference</td>
										<td colspan="3"><ezf:inputText name="refCpoOrdNum" ezfName="refCpoOrdNum" value="S0012345" otherAttr=" size=\"34\" maxlength=\"50\" tabindex=\"-1\""/>
									</tr>
								</table>
							</td>
							<td valign="top">
								<fieldset style="border:1px solid #808080;">
									<legend style="font-size:12px;">Schedule Summary</legend>
									<table border ="0" cellpadding="0" cellspacing="0" width="370">
										<tr>
											<td>
												<div id="TopTBL_G" style="overflow-y:none; overflow-x:none;  width:365;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="170" align="center">
														<col width="65" align="center">
														<col width="65" align="center">
														<col width="65" align="center">
														<tr height="36">
															<td class="pClothBs">Items</td>
															<td class="pClothBs">Total Qty<BR>Allowed</td>
															<td class="pClothBs">Total Qty<BR>Scheduled</td>
															<td class="pClothBs">Total Qty<BR>Ordered</td>
														</tr>
													</table>
												</div>
												<div id="TBL_G" style="overflow-y:scroll; overflow-x:none; height:70; width:382;">
													<table id="G" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col width="170" align="left">
														<col width="65" align="right">
														<col width="65" align="right">
														<col width="65" align="right">
														<ezf:row ezfHyo="G">
														<tr height="22">
															<td><ezf:inputText name="mdseDescShortTxt_G" ezfName="mdseDescShortTxt_G" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"50\" tabindex=\"-1\""/></td>
															<td><ezf:label name="schdAllwQty_G" ezfName="schdAllwQty_G" ezfHyo="G" ezfArrayNo="0" /></td>
															<td><ezf:label name="ordQty_G" ezfName="ordQty_G" ezfHyo="G" ezfArrayNo="0" /></td>
															<td><ezf:label name="shipQty_G" ezfName="shipQty_G" ezfHyo="G" ezfArrayNo="0" /></td>
														</tr>
														</ezf:row>
														<ezf:skip>
														<tr height="22">
															<td><input type="text" size="22" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="cpoSrcTpDescTxt" ezfname="cpoSrcTpDescTxt" class="pPro"></td>
															<td><label ezfout name="" ezfname="">90,000</label></td>
															<td><label ezfout name="" ezfname="">10,000</label></td>
															<td><label ezfout name="" ezfname="">10,000</label></td>
														</tr>
														<tr height="22">
															<td><input type="text" size="22" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="cpoSrcTpDescTxt" ezfname="cpoSrcTpDescTxt" class="pPro"></td>
															<td><label ezfout name="" ezfname="">90,000</label></td>
															<td><label ezfout name="" ezfname="">10,000</label></td>
															<td><label ezfout name="" ezfname="">10,000</label></td>
														</tr>
														<tr height="22">
															<td><input type="text" size="22" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="cpoSrcTpDescTxt" ezfname="cpoSrcTpDescTxt" class="pPro"></td>
															<td><label ezfout name="" ezfname="">90,000</label></td>
															<td><label ezfout name="" ezfname="">10,000</label></td>
															<td><label ezfout name="" ezfname="">10,000</label></td>
														</tr>
														<tr height="22">
															<td><input type="text" size="22" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="cpoSrcTpDescTxt" ezfname="cpoSrcTpDescTxt" class="pPro"></td>
															<td><label ezfout name="" ezfname="">90,000</label></td>
															<td><label ezfout name="" ezfname="">10,000</label></td>
															<td><label ezfout name="" ezfname="">10,000</label></td>
														</tr>
														<tr height="22">
															<td><input type="text" size="22" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" name="cpoSrcTpDescTxt" ezfname="cpoSrcTpDescTxt" class="pPro"></td>
															<td><label ezfout name="" ezfname="">90,000</label></td>
															<td><label ezfout name="" ezfname="">10,000</label></td>
															<td><label ezfout name="" ezfname="">10,000</label></td>
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
						<tr>
							<td colspan="2">
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" align="left">
									<col width="95">
									<col width="80">
									<tr height="22">
										<td class="stab">Schd. Create Date</td>
										<td><ezf:inputText name="schdAgmtCratDt" ezfName="schdAgmtCratDt" value="01/04/2016" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/></td>
									</tr>
								</table>
								<fieldset style="border:1px solid #808080; width:482px" style="table-layout:fixed;" align="right">
									<legend style="font-size:12px;">Schedule Pricing Summary</legend>
									<table width="480" align="right">
										<tr>
											<td>
												<table width="400" border="1" cellpadding="1" cellspacing="0">
													<col width="100">
													<col width="100">
													<col width="100">
													<col width="100">
													<tr>
														<td class="pClothBs" align="center">Sub Total</td>
														<td class="pClothBs" align="center">Charges</td>
														<td class="pClothBs" align="center">Tax</td>
														<td class="pClothBs" align="Center">Amount</td>
													</tr>
													<tr>
														<td class="pOut" align="right"><ezf:label name="xxSubTotCalcPrcAmt" ezfName="xxSubTotCalcPrcAmt" /></td>
														<td class="pOut" align="right"><ezf:label name="xxTotChrgPrcAmt" ezfName="xxTotChrgPrcAmt" /></td>
														<td class="pOut" align="right"><ezf:label name="xxTotTaxAmt" ezfName="xxTotTaxAmt" /></td>
														<td class="pOut" align="right"><ezf:label name="xxTotAmt" ezfName="xxTotAmt" /></td>
													</tr>
											</table>
										</td>
										<td>
											<ezf:inputButton name="Calculation_PriceAmount" value="$" htmlClass="pBtn5" />
										</td>
								 	</tr>
								 </table>
								</fieldset>
							</td>
						</tr>
					</table>
					<%-- ######################################## DETAIL ######################################## --%>
					<table border="0" width="99%" align="center" style="table-layout:fixed;">
						<col valign="top">
						<tr>
							<td>
								<div class="pTab_HEAD">
									<ul>
										<li id="Customer" title="Customer / Contact" class="pTab_ON" ><ezf:anchor name="" ezfName="xxTabProt_C" ezfEmulateBtn="1" ezfGuard="TAB_Customer" >Customer</ezf:anchor></li>
										<li id="Header" title="Header" class="pTab_OFF" ><ezf:anchor name="" ezfName="xxTabProt_H" ezfEmulateBtn="1" ezfGuard="TAB_Header" >Header</ezf:anchor></li>
										<li id="Lines" title="Lines" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_L" ezfEmulateBtn="1" ezfGuard="TAB_Lines" >Schedule Line</ezf:anchor></li>
										<li id="Comments" title="Comments" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_CM" ezfEmulateBtn="1" ezfGuard="TAB_Comments" >Comments</ezf:anchor></li>
										<li id="Additional" title="Additional Data" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_AD" ezfEmulateBtn="1" ezfGuard="TAB_AdditionalData" >Additional</ezf:anchor></li>
										<!-- START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] -->
										<li id="SaHistory" title="SA History" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_OH" ezfEmulateBtn="1" ezfGuard="TAB_SaHistory" >SA History</ezf:anchor></li>
										<!-- END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] -->
									</ul>
								</div>
								<c:choose>
									<%-- ######################################## Customer / Contact Tab ######################################## --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Customer'}">
										<script type="text/javascript">
											document.getElementById( "Customer").className = "pTab_ON";
											document.getElementById( "Header").className = "pTab_OFF";
											document.getElementById( "Lines").className = "pTab_OFF";
											document.getElementById( "Comments").className = "pTab_OFF";
											document.getElementById( "Additional").className = "pTab_OFF";
											<%-- START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] --%>
											document.getElementById( "SaHistory").className = "pTab_OFF";
											<%-- END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] --%>
										</script>
										<div class="pTab_BODY_In">
											<br>
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
												<!-- <col width="5"> -->
												<!-- <col width="360"> -->
												<!-- <col width="5"> -->
												<!-- <col width="360"> -->
												<!-- <col width="5"> -->
												<!-- <col width="360"> -->
												<col width="3">
												<col width="362">
												<col width="3">
												<col width="362">
												<col width="3">
												<col width="362">
												<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
												<tr>
													<!-- Add Start 2017/09/25 H.Sugawara QC#21322 -->
													<td>&nbsp;</td>
													<td valign="top">
														<fieldset style="border:1px solid #808080;">
															<legend style="font-size:12px;">Sold To Customer</legend>
															<br>
															<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																<col width="60">
																<col width="100">
																<col width="87">
																<col width="100">
																<tr height="23">
																	<td class="stab"><ezf:anchor name="soldToCustAcctNm_LK" ezfName="soldToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" otherAttr=" id=\"soldToCustAcctNm_LK\" ezfanchortext=\"\"">Name (*)</ezf:anchor></td>
																	<td colspan="3"><ezf:inputText name="soldToCustAcctNm" ezfName="soldToCustAcctNm" value="CANON FINANCIAL SVC" otherEvent1="OnBlur_DeriveFromSoldToName" otherAttr=" size=\"39\" maxlength=\"60\" ezffocusout=\"OnBlur_DeriveFromSoldToName\""/></td>
																</tr>
																<tr height="23">
																	<td class="stab"><ezf:anchor name="sellToCustCd_LK" ezfName="sellToCustCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" otherAttr=" id=\"sellToCustCd_LK\" ezfanchortext=\"\"">Number (*)</ezf:anchor></td>
																	<td><ezf:inputText name="sellToCustCd" ezfName="sellToCustCd" value="1002805" otherEvent1="OnBlur_DeriveFromSoldToAccount" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromSoldToAccount\""/></td>
																	<td class="stab"><ezf:anchor name="soldToCustLocCd_LK" ezfName="soldToCustLocCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" otherAttr=" id=\"soldToCustLocCd_LK\" ezfanchortext=\"\"">Sold To Code (*)</ezf:anchor></td>
																	<td><ezf:inputText name="soldToCustLocCd" ezfName="soldToCustLocCd" value="5819214" otherEvent1="OnBlur_DeriveFromSoldToLocation" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromSoldToLocation\""/></td>
																</tr>
																<tr>
																	<td class="stab">Address</td>
																	<td colspan="3"><ezf:textArea name="xxAllLineAddr_SE" ezfName="xxAllLineAddr_SE" otherAttr=" rows=\"3\" cols=\"36\""/></td>
																</tr>
															</table>
														</fieldset>
														<!-- Add Start   2018/10/09 H.Kumagai QC#28618 -->
														<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
															<col width="155">
															<tr>
																<td>&nbsp;</td>
															<tr>
															<tr>
																<td><ezf:inputButton name="OpenWin_SpecialInstruction" value="Special Instruction" htmlClass="pBtn10" /></td>
															<tr>
														</table>
														<!-- Add End   2018/10/09 H.Kumagai QC#28618 -->

													</td>
													<!-- Add End   2017/09/25 H.Sugawara QC#21322 -->
													<td>&nbsp;</td>
													<td valign="top">
														<fieldset style="border:1px solid #808080;">
															<legend style="font-size:12px;">Bill To Customer</legend>
															<br>
															<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																<col width="60">
																<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																<!-- <col width="108"> -->
																<!-- <col width="65"> -->
																<!-- <col width="110"> -->
																<col width="100">
																<col width="87">
																<col width="100">
																<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																<tr height="23">
																	<td class="stab"><ezf:anchor name="billToCustAcctNm_LK" ezfName="billToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"billToCustAcctNm_LK\" ezfanchortext=\"\"">Name (*)</ezf:anchor></td>
																	<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																	<!-- <td colspan="3"><input type="text" size="37" maxlength="60" value="CANON FINANCIAL SVC" name="billToCustAcctNm" ezfname="billToCustAcctNm" class="pHsu" ezffocusout="OnBlur_DeriveFromBillToName"></td> -->
																	<td colspan="3"><ezf:inputText name="billToCustAcctNm" ezfName="billToCustAcctNm" value="CANON FINANCIAL SVC" otherEvent1="OnBlur_DeriveFromBillToName" otherAttr=" size=\"39\" maxlength=\"60\" ezffocusout=\"OnBlur_DeriveFromBillToName\""/></td>
																	<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																</tr>
																<tr height="23">
																	<td class="stab"><ezf:anchor name="billToCustAcctCd_LK" ezfName="billToCustAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"billToCustAcctCd_LK\" ezfanchortext=\"\"">Number (*)</ezf:anchor></td>
																	<td><ezf:inputText name="billToCustAcctCd" ezfName="billToCustAcctCd" value="1002805" otherEvent1="OnBlur_DeriveFromBillToAccount" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromBillToAccount\""/></td>
																	<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																	<!-- <td class="stab"><a href="#" id="billToCustLocCd_LK" name="billToCustLocCd_LK" ezfName="billToCustLocCd_LK" onclick="sendServer('OpenWin_BillTo')" ezfanchortext>Location (*)</a></td> -->
																	<td class="stab"><ezf:anchor name="billToCustLocCd_LK" ezfName="billToCustLocCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"billToCustLocCd_LK\" ezfanchortext=\"\"">Bill To Code (*)</ezf:anchor></td>
																	<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																	<td><ezf:inputText name="billToCustLocCd" ezfName="billToCustLocCd" value="5819214" otherEvent1="OnBlur_DeriveFromBillToLocation" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromBillToLocation\""/></td>
																</tr>
																<tr>
																	<td class="stab">Address</td>
																	<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																	<!-- <td colspan="3"><textarea rows="3" cols="35" name="xxAllLineAddr_BT" ezfname="xxAllLineAddr_BT" class="pPro">1233 GAITHER STRET MT LAUREL, NU 70012</textarea></td> -->
																	<td colspan="3"><ezf:textArea name="xxAllLineAddr_BT" ezfName="xxAllLineAddr_BT" otherAttr=" rows=\"3\" cols=\"36\""/></td>
																	<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																</tr>
															</table>
														</fieldset>
													</td>
													<td>&nbsp;</td>
													<td valign="top">
														<fieldset style="border:1px solid #808080;">
															<legend style="font-size:12px;">Ship To Customer</legend>
															<br>
															<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																<col width="60">
																<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																<!-- <col width="108"> -->
																<!-- <col width="65"> -->
																<!-- <col width="110"> -->
																<col width="101">
																<col width="87">
																<col width="100">
																<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																<tr height="23">
																	<td class="stab"><ezf:anchor name="shipToCustAcctNm_LK" ezfName="shipToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"shipToCustAcctNm_LK\" ezfanchortext=\"\"">Name (*)</ezf:anchor></td>
																	<td colspan="3">
																		<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																		<!-- <input type="text" size="31" maxlength="60" value="CANON FINANCIAL SVC" name="shipToCustAcctNm" ezfname="shipToCustAcctNm" class="pHsu" ezffocusout="OnBlur_DeriveFromShipToName"> -->
																		<ezf:inputText name="shipToCustAcctNm" ezfName="shipToCustAcctNm" value="CANON FINANCIAL SVC" otherEvent1="OnBlur_DeriveFromShipToName" otherAttr=" size=\"33\" maxlength=\"60\" ezffocusout=\"OnBlur_DeriveFromShipToName\""/>
																		<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																		DS
																		<ezf:anchor name="dropShipFlg_LK" ezfName="dropShipFlg_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToChange" htmlClass="pOut" otherAttr=" id=\"dropShipFlg_LK\""><ezf:label name="dropShipFlg" ezfName="dropShipFlg" /></ezf:anchor>
																	</td>
																</tr>
																<tr>
																	<td class="stab">DS Name</td>
																	<td colspan="3"><ezf:inputText name="shipToLocNm_DS" ezfName="shipToLocNm_DS" value="DS Name" otherAttr=" size=\"39\" maxlength=\"90\""/></td>
																</tr>
																<tr height="23">
																	<td class="stab"><ezf:anchor name="shipToCustAcctCd_LK" ezfName="shipToCustAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"shipToCustAcctCd_LK\" ezfanchortext=\"\"">Number (*)</ezf:anchor></td>
																	<td><ezf:inputText name="shipToCustAcctCd" ezfName="shipToCustAcctCd" value="1002805" otherEvent1="OnBlur_DeriveFromShipToAccount" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromShipToAccount\""/></td>
																	<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																	<!-- <td class="stab"><a href="#" id="shipToCustLocCd_LK" name="shipToCustLocCd_LK" ezfName="shipToCustLocCd_LK" onclick="sendServer('OpenWin_ShipTo')" ezfanchortext>Location (*)</a></td> -->
																	<td class="stab"><ezf:anchor name="shipToCustLocCd_LK" ezfName="shipToCustLocCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"shipToCustLocCd_LK\" ezfanchortext=\"\"">Ship To Code (*)</ezf:anchor></td>
																	<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																	<td><ezf:inputText name="shipToCustLocCd" ezfName="shipToCustLocCd" value="5819214" otherEvent1="OnBlur_DeriveFromShipToLocation" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromShipToLocation\""/></td>
																</tr>
																<tr>
																	<td class="stab">Address</td>
																	<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																	<!-- <td colspan="3"><textarea rows="3" cols="35" name="xxAllLineAddr_SH" ezfname="xxAllLineAddr_SH" class="pPro">1233 GAITHER STRET MT LAUREL, NU 70012</textarea></td> -->
																	<td colspan="3"><ezf:textArea name="xxAllLineAddr_SH" ezfName="xxAllLineAddr_SH" otherAttr=" rows=\"3\" cols=\"36\""/></td>
																	<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																</tr>
																<tr>
																	<td class="stab">Attention</td>
																	<td colspan="3"><ezf:inputText name="sellToFirstRefCmntTxt" ezfName="sellToFirstRefCmntTxt" value="Christopher Siperko" otherAttr=" size=\"39\" maxlength=\"90\""/></td>
																</tr>
															</table>
														</fieldset>
													</td>
													<!-- Del Start 2017/09/25 H.Sugawara QC#21322 -->
													<!-- <td>&nbsp;</td> -->
													<!-- <td> -->
													<!-- 	<fieldset style="border:1px solid #808080;"> -->
													<!-- 		<legend style="font-size:12px;">Sold To Customer</legend> -->
													<!-- 		<br> -->
													<!-- 		<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;"> -->
													<!-- 			<col width="60"> -->
													<!-- 			<col width="108"> -->
													<!-- 			<col width="65"> -->
													<!-- 			<col width="110"> -->
													<!-- 			<tr height="23"> -->
													<!-- 				<td class="stab"><a href="#" id="soldToCustAcctNm_LK" name="soldToCustAcctNm_LK" ezfName="soldToCustAcctNm_LK" onclick="sendServer('OpenWin_SoldTo')" ezfanchortext>Name (*)</a></td> -->
													<!-- 				<td colspan="3"><input type="text" size="37" maxlength="60" value="CANON FINANCIAL SVC" name="soldToCustAcctNm" ezfname="soldToCustAcctNm" class="pHsu" ezffocusout="OnBlur_DeriveFromSoldToName"></td> -->
													<!-- 			</tr> -->
													<!-- 			<tr height="23"> -->
													<!-- 				<td class="stab"><a href="#" id="sellToCustCd_LK" name="sellToCustCd_LK" ezfName="sellToCustCd_LK" onclick="sendServer('OpenWin_SoldTo')" ezfanchortext>Number (*)</a></td> -->
													<!-- 				<td><input type="text" size="12" maxlength="20" value="1002805" name="sellToCustCd" ezfname="sellToCustCd" class="pHsu" ezffocusout="OnBlur_DeriveFromSoldToAccount"></td> -->
													<!-- 				<td class="stab"><a href="#" id="soldToCustLocCd_LK" name="soldToCustLocCd_LK" ezfName="soldToCustLocCd_LK" onclick="sendServer('OpenWin_SoldTo')" ezfanchortext>Location (*)</a></td> -->
													<!-- 				<td><input type="text" size="12" maxlength="20" value="5819214" name="soldToCustLocCd" ezfname="soldToCustLocCd" class="pHsu" ezffocusout="OnBlur_DeriveFromSoldToLocation"></td> -->
													<!-- 			</tr> -->
													<!-- 			<tr> -->
													<!-- 				<td class="stab">Address</td> -->
													<!-- 				<td colspan="3"><textarea rows="3" cols="35" name="xxAllLineAddr_SE" ezfname="xxAllLineAddr_SE" class="pPro">1233 GAITHER STRET MT LAUREL, NU 70012</textarea></td> -->
													<!-- 			</tr> -->
													<!-- 		</table> -->
													<!-- 	</fieldset> -->
													<!-- </td> -->
													<!-- Del End   2017/09/25 H.Sugawara QC#21322 -->
												</tr>
											</table>
											<!--<br>-->
											<!-- Del Strat   2018/10/09 H.Kumagai QC#28618 -->
											<!-- 
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="5">
												<col width="115">
												<col width="95">
												<col width="95">
												<tr>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn10" value="Special Instruction" name="OpenWin_SpecialInstruction" onclick="sendServer(this)"></td>
												<tr>
											</table> -->
											<!-- Del End   2018/10/09 H.Kumagai QC#28618 -->
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="5">
												<col width="1094">
												<tr>
													<td>&nbsp;</td>
													<td>
														<fieldset style="border:1px solid #808080;">
															<legend style="font-size:12px;">Contact</legend>
															<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																<col width="95">
																<col width="95">
																<tr>
																	<td><ezf:inputButton name="Add_ContactLine" value="Add Line" htmlClass="pBtn8" /></td>
																	<td><ezf:inputButton name="Delete_ContactLine" value="Delete Line" htmlClass="pBtn8" /></td>
																</tr>
																<tr>
																	<td colspan="6">
																		<table border ="0" cellpadding="0" cellspacing="0" width="100%">
																			<tr>
																				<td>
																					<div id="TopTBL_D" style="overflow-y:none; overflow-x:none;">
																						<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																							<col width="28">
																							<col width="180" align="center">
																							<col width="154" align="center">
																							<col width="152" align="center">
																							<col width="110" align="center">
																							<col width="40" align="center">
																							<col width="200" align="center">
																							<col width="110" align="center">
																							<col width="90" align="center">
																							<tr height="24">
																								<td class="pClothBs">&nbsp;</td>
																								<td class="pClothBs">Role</td>
																								<td class="pClothBs">First Name</td>
																								<td class="pClothBs">Last Name</td>
																								<td class="pClothBs">Phone</td>
																								<td class="pClothBs">EXT</td>
																								<td class="pClothBs">EMail</td>
																								<td class="pClothBs">Fax</td>
																								<td class="pClothBs">Customer Ref</td>
																							</tr>
																						</table>
																					</div>
																					<div id="TBL_D" style="overflow-y:scroll; overflow-x:none; height:53; width:1074; word-break: break-all;">
																						<table border="1" cellpadding="1" cellspacing="0" id="D" style="table-layout: fixed;">
																							<col width="28" align="center">
																							<col width="180" align="center">
																							<col width="154" align="center">
																							<col width="152" align="center">
																							<col width="110" align="center">
																							<col width="40" align="center">
																							<col width="200" align="center">
																							<col width="110" align="center">
																							<col width="90" align="center">
																							<ezf:row ezfHyo="D">
																								<tr>
																									<td><ezf:inputCheckBox name="xxChkBox_D" ezfName="xxChkBox_D" value="Y" ezfHyo="D" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_D#{EZF_ROW_NUMBER}\""/></td>
																									<td>
																										<ezf:select name="ctacPsnTpCd_D" ezfName="ctacPsnTpCd_D" ezfHyo="D" ezfArrayNo="0" ezfCodeName="ctacTpCd_CD" ezfDispName="ctacTpDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_ContactRole', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width:172px;\""/>
																									</td>
																									<td>
																										<table border="0" cellpadding="1" cellspacing="0">
																											<tr style="padding:0;">
																												<td><ezf:inputText name="ctacPsnFirstNm_D" ezfName="ctacPsnFirstNm_D" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"30\""/></td>
																												<td><ezf:anchor name="ctacPsnFirstNm_LK" ezfName="ctacPsnFirstNm_LK" ezfHyo="D" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ContactPerson" otherAttr=" ezfanchortext=\"\"">F</ezf:anchor></td>
																											</tr>
																										</table>
																									</td>
																									<td><ezf:inputText name="ctacPsnLastNm_D" ezfName="ctacPsnLastNm_D" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
																									<td><ezf:inputText name="ctacPsnTelNum_D" ezfName="ctacPsnTelNum_D" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/></td>
																									<td><ezf:inputText name="ctacPsnExtnNum_D" ezfName="ctacPsnExtnNum_D" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"4\" maxlength=\"10\" ezftoupper=\"\""/></td>
																									<td><ezf:inputText name="ctacPsnEmlAddr_D" ezfName="ctacPsnEmlAddr_D" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"320\" ezftoupper=\"\""/></td>
																									<td><ezf:inputText name="ctacPsnFaxNum_D" ezfName="ctacPsnFaxNum_D" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/></td>
																									<td>
																										<ezf:select name="ctacCustRefTpCd_D" ezfName="ctacCustRefTpCd_D" ezfHyo="D" ezfArrayNo="0" ezfCodeName="ctacCustRefTpCd_CD" ezfDispName="ctacCustRefTpDescTxt_NM" otherAttr=" style=\"width:80px;\""/>
																									</td>
																								</tr>
																							</ezf:row>
																							<ezf:skip>
																								<tr>
																									<td><input type="checkbox" name="xxChkBox_D" ezfname="xxChkBox_D" value="Y" ezfhyo="D" id="xxChkBox_D#{EZF_ROW_NUMBER}"></td>
																									<td>
																										<select name="ctacPsnTpCd_D" ezfname="ctacPsnTpCd_D" ezflist="ctacTpCd_NM,ctacTpDescTxt_NM,99, ,noblank" ezfhyo="D" style="width:172px;">
																											<option>WWWWWWWWW1WWWWWWWWW2</option>
																											<option>WWWWWWWWW1WWWWWWWWW2</option>
																										</select>
																									</td>
																									<td>
																										<table border="0" cellpadding="1" cellspacing="0">
																											<tr style="padding:0;">
																												<td><input type="text" size="21" maxlength="30" value="WWWWWWWWW1WWWWWWWWW2" name="ctacPsnFirstNm_D" ezfname="ctacPsnFirstNm_D" ezfhyo="D" ezftoupper></td>
																												<td><a href="#" name="ctacPsnFirstNm_LK" ezfname="ctacPsnFirstNm_LK" onclick="sendServer('OpenWin_ContactPerson')" ezfhyo="D" ezfanchortext>F</a></td>
																											</tr>
																										</table>
																									</td>
																									<td><input type="text" size="22" maxlength="30" value="WWWWWWWWW1WWWWWWWWW2" name="ctacPsnLastNm_D" ezfname="ctacPsnLastNm_D" ezfhyo="D" ezftoupper></td>
																									<td><input type="text" size="14" maxlength="20" value="WWWWWWWWW1WWWWWWWWW2" name="ctacPsnTelNum_D" ezfname="ctacPsnTelNum_D" ezfhyo="D" ezftoupper></td>
																									<td><input type="text" size="5" maxlength="10" value="WWWWWWWWW1WWWWWWWWW2" name="ctacPsnExtnNum_D" ezfname="ctacPsnExtnNum_D" ezfhyo="D" ezftoupper></td>
																									<td><input type="text" size="20" maxlength="320" value="WWWWWWWWW1WWWWWWWWW2" name="ctacPsnEmlAddr_D" ezfname="ctacPsnEmlAddr_D" ezfhyo="D" ezftoupper></td>
																									<td><input type="text" size="14" maxlength="20" value="WWWWWWWWW1WWWWWWWWW2" name="ctacPsnFaxNum_D" ezfname="ctacPsnFaxNum_D" ezfhyo="D" ezftoupper></td>
																								</tr>
																								<tr>
																									<td><input type="checkbox" name="xxChkBox_D" ezfname="xxChkBox_D" value="Y" ezfhyo="D" id="xxChkBox_D#{EZF_ROW_NUMBER}"></td>
																									<td>
																										<select name="ctacPsnTpCd_D" ezfname="ctacPsnTpCd_D" ezflist="ctacTpCd_NM,ctacTpDescTxt_NM,99, ,noblank" ezfhyo="D" style="width:172px;">
																											<option>WWWWWWWWW1WWWWWWWWW2</option>
																											<option>WWWWWWWWW1WWWWWWWWW2</option>
																										</select>
																									</td>
																									<td>
																										<table border="0" cellpadding="1" cellspacing="0">
																											<tr style="padding:0;">
																												<td><input type="text" size="21" maxlength="30" value="WWWWWWWWW1WWWWWWWWW2" name="ctacPsnFirstNm_D" ezfname="ctacPsnFirstNm_D" ezfhyo="D" ezftoupper></td>
																												<td><a href="#" name="ctacPsnFirstNm_LK" ezfname="ctacPsnFirstNm_LK" onclick="sendServer('OpenWin_ContactPerson')" ezfhyo="D" ezfanchortext>F</a></td>
																											</tr>
																										</table>
																									</td>
																									<td><input type="text" size="22" maxlength="30" value="WWWWWWWWW1WWWWWWWWW2" name="ctacPsnLastNm_D" ezfname="ctacPsnLastNm_D" ezfhyo="D" ezftoupper></td>
																									<td><input type="text" size="19" maxlength="20" value="WWWWWWWWW1WWWWWWWWW2" name="ctacPsnTelNum_D" ezfname="ctacPsnTelNum_D" ezfhyo="D" ezftoupper></td>
																									<td><input type="text" size="5" maxlength="10" value="WWWWWWWWW1WWWWWWWWW2" name="ctacPsnExtnNum_D" ezfname="ctacPsnExtnNum_D" ezfhyo="D" ezftoupper></td>
																									<td><input type="text" size="19" maxlength="320" value="WWWWWWWWW1WWWWWWWWW2" name="ctacPsnEmlAddr_D" ezfname="ctacPsnEmlAddr_D" ezfhyo="D" ezftoupper></td>
																									<td><input type="text" size="19" maxlength="20" value="WWWWWWWWW1WWWWWWWWW2" name="ctacPsnFaxNum_D" ezfname="ctacPsnFaxNum_D" ezfhyo="D" ezftoupper></td>
																								</tr>
																							</ezf:skip>
																						</table>
																					</div>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</fieldset>
													</td>
												</tr>
											</table>
											<br>
										</div>
									</c:when>
									<%-- ######################################## Header Tab ######################################## --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Header'}">
										<script type="text/javascript">
											document.getElementById( "Customer").className = "pTab_OFF";
											document.getElementById( "Header").className = "pTab_ON";
											document.getElementById( "Lines").className = "pTab_OFF";
											document.getElementById( "Comments").className = "pTab_OFF";
											document.getElementById( "Additional").className = "pTab_OFF";
											<%-- START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] --%>
											document.getElementById( "SaHistory").className = "pTab_OFF";
											<%-- END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] --%>
										</script>
										<div class="pTab_BODY_In">
											<br>
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="110">
												<col width="239">
												<col width="261">
												<tr height="22">
													<td class="stab">Customer PO</td>
													<td colspan="2"><ezf:inputText name="custIssPoNum" ezfName="custIssPoNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" otherAttr=" size=\"35\" maxlength=\"35\" ezftoupper=\"\""/></td>
													</tr>
													<tr height="22">
														<td class="stab">Customer PO Date</td>
														<td colspan="2">
															<table border="0" cellpadding="0" cellspacing="0">
															<tr style="padding:0;">
																<td><ezf:inputText name="custIssPoDt" ezfName="custIssPoDt" value="01/04/2016" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('custIssPoDt', 4);" tabindex="0"></td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td class="stab" valign="top"><ezf:anchor name="slsRepTocNm_LK" ezfName="slsRepTocNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Salesrep" otherAttr=" id=\"slsRepTocNm_LK\" tabindex=\"0\" ezfanchortext=\"\"">Salesrep (*)</ezf:anchor></td>
													<td>
														<ezf:inputText name="slsRepTocNm" ezfName="slsRepTocNm" value="DOE,JOHN" otherEvent1="OnBlur_DeriveFromSalesRepName" otherAttr=" size=\"32\" maxlength=\"50\" tabindex=\"0\" ezfnoupperfocusout=\"OnBlur_DeriveFromSalesRepName\""/>
													</td>
													<td>
														<table border="0" cellpadding="1" cellspacing="1">
															<tr style="padding:0;">
																<td class="stab" valign="top"><ezf:anchor name="psnNum_LK" ezfName="psnNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Salesrep" htmlClass="stab" otherAttr=" id=\"psnNum_LK\" tabindex=\"0\" valign=\"top\" ezfanchortext=\"\"">Number(*)</ezf:anchor></td>
																<td><ezf:inputText name="psnNum" ezfName="psnNum" value="D012345" otherEvent1="OnBlur_DeriveFromSalesRepCode" otherAttr=" size=\"8\" maxlength=\"50\" tabindex=\"0\" ezffocusout=\"OnBlur_DeriveFromSalesRepCode\""/></td>
															</tr>
														</table>
													</td>
												</tr>
												<tr height="22">
													<td class="stab">Sales Bus Unit</td>
													<td colspan="2"><ezf:inputText name="xxScrItem54Txt_CB" ezfName="xxScrItem54Txt_CB" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"50\" tabindex=\"-1\""/></td>
												</tr>
												<tr height="22">
													<td class="stab">Sales Rep Branch</td>
													<td colspan="2"><ezf:inputText name="xxScrItem54Txt_CE" ezfName="xxScrItem54Txt_CE" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"50\" tabindex=\"-1\""/></td>
												</tr>
												<tr height="22">
													<td class="stab">&nbsp;</td>
													<td><ezf:inputButton name="OpenWin_SalesCredit" value="Sales Credit" htmlClass="pBtn7" />
														<ezf:inputButton name="OpenWin_Attachments" value="Attachment" htmlClass="pBtn7" /></td>
												</tr>
											</table>
											<table style="table-layout:fixed;">
												<col width="620" valign="top">
												<tr>
													<td>
														<fieldset style="border:1px solid #808080;">
															<legend style="font-size:12px;">Freight Information</legend>
															<table style="table-layout:fixed;" border="0">
																<col width="100">
																<col width="250">
																<col width="100">
																<col width="150">
																<tr>
																	<td class="stab"><ezf:anchor name="frtCondDescTxt_LK" ezfName="frtCondDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_FreightTerms" otherAttr=" id=\"frtCondDescTxt_LK\" ezfanchortext=\"\"">Freight Terms</ezf:anchor></td>
																	<td>
																		<ezf:inputText name="frtCondDescTxt" ezfName="frtCondDescTxt" value="ESS-SUPPLIES-STANDARD" otherEvent1="OnBlur_FreightTerms" otherAttr=" size=\"30\" maxlength=\"50\" tabindex=\"0\" ezffocusout=\"OnBlur_FreightTerms\""/>
																	</td>
																	<td class="stab">Service Level</td>
																	<td><ezf:select name="shpgSvcLvlCd" ezfName="shpgSvcLvlCd" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_CD" ezfDispName="shpgSvcLvlDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_ShpgSvcLvlCd')\"" otherAttr=" style=\"width: 145px\" tabindex=\"0\""/></td>
																</tr>
																<tr>
																	<td class="stab"><ezf:anchor name="carrSvcLvlDescTxt_LK" ezfName="carrSvcLvlDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CarrierServiceLevel" otherAttr=" id=\"carrSvcLvlDescTxt_LK\" ezfanchortext=\"\"">Carrier Service Level</ezf:anchor></td>
																	<td>
																		<ezf:inputText name="carrSvcLvlDescTxt" ezfName="carrSvcLvlDescTxt" value="FedEx PR1 OVRNT" otherAttr=" size=\"30\" maxlength=\"50\" tabindex=\"0\""/>
																	</td>
																	<td class="stab">Carrier Acct Num</td>
																	<td><ezf:inputText name="carrAcctNum" ezfName="carrAcctNum" value="FEDEX-0001-002" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"0\" ezftoupper=\"\""/></td>
																</tr>
																<tr>
																	<td></td>
																	<td></td>
																	<td class="stab">Special Handling</td>
																	<td><ezf:select name="spclHdlgTpCd" ezfName="spclHdlgTpCd" ezfBlank="1" ezfCodeName="spclHdlgTpCd_CD" ezfDispName="spclHdlgTpDescTxt_NM" otherAttr=" style=\"width: 145px\" tabindex=\"0	\""/></td>
																</tr>
															</table>
														</fieldset>
													</td>
													
												</tr>
											</table>
										</div>
									</c:when>
									<%-- ######################################## Line Tab ######################################## --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Lines'}">
										<script type="text/javascript">
											document.getElementById( "Customer").className = "pTab_OFF";
											document.getElementById( "Header").className = "pTab_OFF";
											document.getElementById( "Lines").className = "pTab_ON";
											document.getElementById( "Comments").className = "pTab_OFF";
											document.getElementById( "Additional").className = "pTab_OFF";
											<%-- START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] --%>
											document.getElementById( "SaHistory").className = "pTab_OFF";
											<%-- END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] --%>
										</script>
										<div class="pTab_BODY_In">
											<table>
												<tr>
													<td>
														<ezf:inputButton name="Line_All_Expand" value="All Expand" htmlClass="pBtn6" otherAttr=" tabindex=\"0\""/>
													</td>
													<td>
														<ezf:inputButton name="Line_All_Collapsed" value="All Collapse" htmlClass="pBtn6" otherAttr=" tabindex=\"0\""/>
													</td>
													<td>
														<ezf:inputButton name="Add_line" value="Add Line" htmlClass="pBtn6" otherAttr=" tabindex=\"0\""/>
													</td>
													<td>
														<ezf:inputButton name="Add_Schd_line" value="Add Schedule Line" htmlClass="pBtn10" otherAttr=" tabindex=\"0\""/>
													</td>
													<td>
														<ezf:inputButton name="Cancel_Line" value="Cancel" htmlClass="pBtn6" otherAttr=" tabindex=\"0\""/>
													</td>
													<td>
														<ezf:inputButton name="OpenWin_SchdLineAssist" value="Schedule Line Assist" htmlClass="pBtn10" otherAttr=" tabindex=\"0\""/>
													</td>
													<td>
														<ezf:inputButton name="OpenWin_PriceChanges" value="Price Change" htmlClass="pBtn8" otherAttr=" tabindex=\"0\""/>
													</td>
													<td>
														<ezf:inputButton name="OpenWin_ShippingDetail" value="Shipping Status" htmlClass="pBtn8" otherAttr=" tabindex=\"0\""/>
													</td>
												</tr>
											</table>
											<table width="99%">
												<col valign="top">
												<tr>
													<td>
														<div style="float:left; width:1103; height:290; overflow-y:scroll; overflow-x:scroll;">
											<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
<%@ page import="business.servlet.NWAL1840.NWAL1840BMsg" %>
<%@ page import="business.servlet.NWAL1840.NWAL1840_ABMsg" %>
<%@ page import="business.servlet.NWAL1840.NWAL1840_BBMsg" %>
<% NWAL1840BMsg bMsg = (NWAL1840BMsg)databean.getEZDBMsg(); 
	int lineIdx = 0;
%>
															<table border="0" cellpadding="0" cellspacing="0" style="width:2245; table-layout: fixed;">
<% for ( int i = 0; i < bMsg.A.getValidCount(); i++ ) { %>
																<tr>
																	<td>
																		<table border="1" cellpadding="0" cellspacing="0" id="A" style="table-layout: fixed;">
																			<col width="30" align="center">
																			<col width="30" align="center">
																			<col width="50" align="center">
																			<col width="130" align="center">
																			<col width="220" align="center">
																			<col width="65" align="center">
																			<col width="50" align="center">
																			<col width="130" align="center">
																			<col width="90" align="center">
																			<col width="90" align="center">
																			<col width="90" align="center">
																			<col width="140" align="center">
																			<col width="120" align="center">
																			<col width="140" align="center">
																			<col width="140" align="center">
																			<col width="140" align="center">
																			<col width="140" align="center">
																			<col width="140" align="center">
																			<col width="140" align="center">
																			<col width="140" align="center">
																			<tr height="36">
																				<td class="pClothBs">&nbsp;</td>
																				<td class="pClothBs">&nbsp;</td>
																				<td class="pClothBs">Line#</td>
																				<td class="pClothBs">Item#</td>
																				<td class="pClothBs">Description</td>
																				<td class="pClothBs">UOM</td>
																				<td class="pClothBs">Supd. Lock</td>
																				<td class="pClothBs">Substitute Item</td>
																				<td class="pClothBs">Qty Allowed</td>
																				<td class="pClothBs">Total Qty Scheduled</td>
																				<td class="pClothBs">Total Qty Ordered</td>
																				<td class="pClothBs">Unit List Price</td>
																				<td class="pClothBs">Unit Discount</td>
																				<td class="pClothBs">Sell Price</td>
																				<td class="pClothBs">Subtotal</td>
																				<td class="pClothBs">Charges</td>
																				<td class="pClothBs">Tax</td>
																				<td class="pClothBs">Total Price</td>
																				<td class="pClothBs">Frequency</td>
																				<td class="pClothBs">Periodic Qty</td>
																			</tr>
																			<tr height="22">
																				<td>
	<% boolean isSmryLine = "Y".equals(bMsg.A.no(i).xxSmryLineFlg_A.getValue()); %>
	<% if (isSmryLine) { %>
																					<img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('Line_Expand','<%= i %>')" ezfHyo="A" height="14">
	<% } else if (!isSmryLine) { %>
																					<img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('Line_Collapsed','<%= i %>')" ezfHyo="A" height="14">
	<% } %>
																				</td>
																				<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"0\""/></td>
																				<td><ezf:label name="xxLineNum_A" ezfName="xxLineNum_A" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"0\""/></td>
																				<td>
																					<table border="0" cellpadding="1" cellspacing="0">
																						<tr style="padding:0;">
																							<td><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromItem" otherAttr=" size=\"15\" maxlength=\"16\" tabindex=\"0\" ezffocusout=\"OnBlur_DeriveFromItem\""/></td>
																							<td><ezf:anchor name="mdseCd_LK" ezfName="mdseCd_LK" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_Merchandise" otherAttr=" ezfanchortext=\"\"">I</ezf:anchor></td>
																						</tr>
																					</table>
																				</td>
																				<td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"30\" maxlength=\"30\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/>
																				<td><ezf:inputText name="pkgUomDescTxt_A" ezfName="pkgUomDescTxt_A" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"5\" maxlength=\"16\" style=\"text-align:left\""/></td>
																				<td><ezf:inputCheckBox name="supdLockFlg_A" ezfName="supdLockFlg_A" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"0\""/></td>
																				<td>
																					<table border="0" cellpadding="1" cellspacing="0">
																						<tr style="padding:0;">
																							<td><ezf:inputText name="sbstMdseCd_A" ezfName="sbstMdseCd_A" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"15\" maxlength=\"16\" tabindex=\"0\" ezftoupper=\"\""/></td>
																							<td><ezf:anchor name="sbstMdseCd_LK" ezfName="sbstMdseCd_LK" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_SubstituteItem" otherAttr=" tabindex=\"0\" ezfanchortext=\"\"">S</ezf:anchor></td>
																						</tr>
																					</table>
																				</td>
																				<td><ezf:inputText name="schdAllwQty_A" ezfName="schdAllwQty_A" value="1,234,567,890" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromSchdAllwQty" otherAttr=" size=\"11\" maxlength=\"11\" style=\"text-align:right\" ezffocusout=\"OnBlur_DeriveFromSchdAllwQty\""/></td>
																				<td><ezf:inputText name="ordQty_SC" ezfName="ordQty_SC" value="1,234,567,890" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"11\" maxlength=\"11\" style=\"text-align:right\""/></td>
																				<td><ezf:inputText name="ordQty_DE" ezfName="ordQty_DE" value="1,234,567,890" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"11\" maxlength=\"11\" style=\"text-align:right\""/></td>
																				<td><ezf:inputText name="dealPrcListPrcAmt_A" ezfName="dealPrcListPrcAmt_A" value="123,456,789,012.00" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"18\" maxlength=\"18\" style=\"text-align:right\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="xxTotDiscAmt_A" ezfName="xxTotDiscAmt_A" value="100.00" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"13\" maxlength=\"18\" style=\"text-align:right\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="dealNetUnitPrcAmt_A" ezfName="dealNetUnitPrcAmt_A" value="123,456,789,012.00" ezfHyo="A" ezfArrayNo="<%= i %>"  otherEvent1="OnBlur_DeriveFromDealNetUnitPrcAmt" otherAttr=" size=\"18\" maxlength=\"18\" style=\"text-align:right\" ezffocusout=\"OnBlur_DeriveFromDealNetUnitPrcAmt\""/></td>
																				<td><ezf:inputText name="schdAgmtLineDealNetAmt_A" ezfName="schdAgmtLineDealNetAmt_A" value="123,456,789,012.00" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"18\" maxlength=\"18\" style=\"text-align:right\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="schdAgmtLineDealFrtAmt_A" ezfName="schdAgmtLineDealFrtAmt_A" value="123,456,789,012.00" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"18\" maxlength=\"18\" style=\"text-align:right\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="schdAgmtLineDealTaxAmt_A" ezfName="schdAgmtLineDealTaxAmt_A" value="123,456,789,012.00" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"18\" maxlength=\"18\" style=\"text-align:right\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="xxTotAmt_A" ezfName="xxTotAmt_A" value="123,456,789,012.00" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"18\" maxlength=\"18\" style=\"text-align:right\" ezftoupper=\"\""/></td>
																				<td>
																					<ezf:select name="shpgIntvlCd_A" ezfName="shpgIntvlCd_A" ezfHyo="A" ezfArrayNo="<%= i %>" ezfBlank="1" ezfCodeName="shpgIntvlCd" ezfDispName="shpgIntvlDescTxt" otherAttr=" style=\"width:136px;\""/>
																				</td>
																				<td><ezf:inputText name="otmShipQty_A" ezfName="otmShipQty_A" value="1" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"18\" maxlength=\"18\" style=\"text-align:right\" ezftoupper=\"\""/></td>
																			<tr>
																		</table>
																	</td>
																</tr>
	<% 
	boolean dplyFlg = true;
	for ( int j = lineIdx; j < bMsg.B.getValidCount(); j++ ) { 
	
		NWAL1840_ABMsg lineMsg = bMsg.A.no(i);
		isSmryLine = "Y".equals(lineMsg.xxSmryLineFlg_A.getValue());
		String lineNum_A = lineMsg.schdAgmtLineNum_A.getValue().toPlainString();
	
		String lineNum_B = bMsg.B.no(j).schdAgmtLineNum_B.getValue().toPlainString();
		if (!lineNum_A.equals(lineNum_B)) {
			break;
		}
		lineIdx++;
	
		if (isSmryLine) {
		%>
																<ezf:inputHidden name="xxChkBox_B" ezfName="xxChkBox_B" ezfHyo="B" ezfArrayNo="<%= j %>" />
																<ezf:inputHidden name="rddDt_B" ezfName="rddDt_B" value="02/02/2015" ezfHyo="B" ezfArrayNo="<%= j %>" />
																<ezf:inputHidden name="ordQty_B" ezfName="ordQty_B" ezfHyo="B" ezfArrayNo="<%= j %>" />
		<%  continue;
		}
		if (dplyFlg) {
	%>
														
																<tr>
																	<td>
																		<table border="1" cellpadding="0" cellspacing="0" id="B" style="table-layout: fixed;">
																			<col width="30" align="center">
																			<col width="30" align="center">
																			<col width="50" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="120" align="center">
																			<col width="70" align="center">
																			<tr height="36">
																				<td class="pClothBs">&nbsp;</td>
																				<td class="pClothBs">&nbsp;</td>
																				<td class="pClothBs">Schd Line#</td>
																				<td class="pClothBs">Requested Delivery Date</td>
																				<td class="pClothBs">Order Qty</td>
																				<td class="pClothBs">Qty Scheduled</td>
																				<td class="pClothBs">Qty Ordered</td>
																				<td class="pClothBs">Order Number</td>
																				<td class="pClothBs">Ordered Item#</td>
																				<td class="pClothBs">Order Cancelled</td>
																			</tr>
																
		<%  dplyFlg = false;
        } 
        %>
																			<tr height="22">
																				<td>&nbsp;</td>
																				<td><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" id=\"\" tabindex=\"0\""/></td>
																				<td><ezf:label name="xxLineNum_B" ezfName="xxLineNum_B" ezfHyo="B" ezfArrayNo="<%= j %>" /></td>
																				<td><table border="0" cellpadding="1" cellspacing="0">
																						<tr style="padding:0;">
																							<td><ezf:inputText name="rddDt_B" ezfName="rddDt_B" value="02/02/2015" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"0\""/></td>
																							<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rddDt_B', 4, '<%= j %>');" tabindex="0"></td>
																						</tr>
																					</table>
																				</td>
																				<td><ezf:inputText name="ordQty_B" ezfName="ordQty_B" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\" maxlength=\"13\" tabindex=\"0\" style=\"text-align:right\""/></td>
																				<td align="right"><ezf:label name="ordQty_BS" ezfName="ordQty_BS" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\""/></td>
																				<td align="right"><ezf:label name="ordQty_BD" ezfName="ordQty_BD" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\""/></td>
																				<td align="left"><ezf:anchor name="cpoOrdNum_LK" ezfName="cpoOrdNum_LK" ezfHyo="B" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="MoveWin_OrderEntry" otherAttr=" ezfanchortext=\"\""><ezf:label name="cpoOrdNum_B" ezfName="cpoOrdNum_B" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\""/></ezf:anchor></td>
																				<td align="left"><ezf:label name="ordMdseCd_B" ezfName="ordMdseCd_B" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\""/></td>
																				<td><ezf:label name="cancFlg_B" ezfName="cancFlg_B" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"1\""/></td>
																			</tr>
	<% }	
		if (!dplyFlg) { %>
																		</table>
																	</td>
																</tr>
	<% } %>
<% } %>
															<%-- +++++ [END] Programming JSP for Nesting Records Table +++++ --%>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</div>
									</c:when>
									<%-- ######################################## Comments Tab ######################################## --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Comments'}">
										<script type="text/javascript">
											document.getElementById( "Customer").className = "pTab_OFF";
											document.getElementById( "Header").className = "pTab_OFF";
											document.getElementById( "Lines").className = "pTab_OFF";
											document.getElementById( "Comments").className = "pTab_ON";
											document.getElementById( "Additional").className = "pTab_OFF";
											<%-- START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] --%>
											document.getElementById( "SaHistory").className = "pTab_OFF";
											<%-- END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] --%>
										</script>
										<div class="pTab_BODY_In">
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="5">
												<col width="610">
												<tr>
													<td>&nbsp;</td>
													<td>
														<fieldset style="border:1px solid #808080;">
															<legend style="font-size:12px;">Shipping Comments</legend>
															<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																<tr>
																	<td><ezf:textArea name="shpgCmntTxt" ezfName="shpgCmntTxt" otherAttr=" rows=\"6\" cols=\"80\" maxlength=\"260\""/></td>
																</tr>
															</table>
														</fieldset>
													</td>
												</tr>
											</table>
											<br>
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="5">
												<col width="610">
												<tr>
													<td>&nbsp;</td>
													<td>
														<fieldset style="border:1px solid #808080;">
															<legend style="font-size:12px;">Internal Order Comments</legend>
															<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																<tr>
																	<td><ezf:textArea name="itrlOrdCmntTxt" ezfName="itrlOrdCmntTxt" otherAttr=" rows=\"6\" cols=\"80\" maxlength=\"260\""/></td>
																</tr>
															</table>
														</fieldset>
													</td>
												</tr>
											</table>
											<br>
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="5">
												<col width="610">
												<tr>
													<td>&nbsp;</td>
													<td>
														<fieldset style="border:1px solid #808080;">
															<legend style="font-size:12px;">Invoice Comments</legend>
															<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																<tr>
																	<td><ezf:textArea name="invCmntTxt" ezfName="invCmntTxt" otherAttr=" rows=\"6\" cols=\"80\" maxlength=\"260\""/></td>
																</tr>
															</table>
														</fieldset>
													</td>
												</tr>
											</table>
										</div>
									</c:when>
									<%-- ######################################## Additional Data Tab ######################################## --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Additional'}">
										<script type="text/javascript">
											document.getElementById( "Customer").className = "pTab_OFF";
											document.getElementById( "Header").className = "pTab_OFF";
											document.getElementById( "Lines").className = "pTab_OFF";
											document.getElementById( "Comments").className = "pTab_OFF";
											document.getElementById( "Additional").className = "pTab_ON";
											<%-- START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] --%>
											document.getElementById( "SaHistory").className = "pTab_OFF";
											<%-- END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] --%>
										</script>
										<div class="pTab_BODY_In">
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="5">
												<col width="">
												<tr>
													<td></td>
													<td>
														<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
															<col width="110">
															<col width="240">
															<col width="160">
															<col width="240">
															<col width="">
															<tr height="22">
																<td class="stab">Created By</td>
																<td><ezf:inputText name="xxPsnNm_SV" ezfName="xxPsnNm_SV" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
																<td class="stab"><ezf:anchor name="prcContrNum_LK" ezfName="prcContrNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PrcContrNum" otherAttr=" id=\"prcContrNum_LK\" ezfanchortext=\"\"">Association Program Name(*)</ezf:anchor></td>
																<td><ezf:inputText name="prcContrNum" ezfName="prcContrNum" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/></td>
																<td>&nbsp;</td>
															</tr>
															<tr height="22">
																<td class="stab">Created Date</td>
																<td><ezf:inputText name="xxTsDsp19Txt_SV" ezfName="xxTsDsp19Txt_SV" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"30\" maxlength=\"17\""/></td>
																<td class="stab">Classification</td>
																<td><ezf:inputText name="dsAcctClsDescTxt" ezfName="dsAcctClsDescTxt" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
																<td>&nbsp;</td>
															</tr>
															<tr height="22">
																<td class="stab">Order Submitted Date</td>
																
																<td>
																	<ezf:select name="xxViewNm" ezfName="xxViewNm" ezfCodeName="xxViewNm_CD" ezfDispName="xxViewNm_NM" otherAttr=" style=\"width:220px;\""/>
																<td class="stab">GL Acct Classification</td>
																<td><ezf:inputText name="xxScrItem54Txt_GL" ezfName="xxScrItem54Txt_GL" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
																<td>&nbsp;</td>
															</tr>
															<tr height="22">
																<td>&nbsp;</td>
																<td><ezf:inputButton name="OpenWin_ViewChangeLog" value="View Change Log" htmlClass="pBtn10" /></td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											<hr>
											
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<tr>
													<td>
														<div id="parentBoxF">
															<table border ="0" cellpadding="0" cellspacing="0" width="100%">
																<col width="5">
																<col width="">
																<tr>
																	<td></td>
																	<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
																</tr>
																<tr>
																	<td></td>
																	<td>
																		<div style="float:left; display:block">
																			<div id='leftTblHead' style="display:block;"></div>
																			<div id='leftTbl' style="float:left; display:block; height:116px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
																		</div>
																		<div style="float:left">
																			<div id='rightTblHead' style="width:1072px; display:block; overflow:hidden; margin:0px;padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" id="FHEAD" width="1740px" style="margin-right:20px">
																					<col width="50" align="center">
																					<col width="130" align="center">
																					<col width="220" align="center">
																					<col width="100" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<tr height="24">
																						<td id="FH0" class="pClothBs colFix">Line #</td>
																						<td id="FH1" class="pClothBs">Item #</td>
																						<td id="FH2" class="pClothBs">Description</td>
																						<td id="FH3" class="pClothBs">Total Weight</td>
																						<td id="FH4" class="pClothBs">Merchandise Type</td>
																						<td id="FH5" class="pClothBs">Product Code</td>
																						<td id="FH6" class="pClothBs">Product Level 1</td>
																						<td id="FH7" class="pClothBs">Product Level 2</td>
																						<td id="FH8" class="pClothBs">Product Level 3</td>
																						<td id="FH9" class="pClothBs">Product Level 4</td>
																						<td id="FH10" class="pClothBs">Product Level 5</td>
																					</tr>
																				</table>
																			</div>
																			<div id="rightTbl" style="width:1089px; height:133px; display:block; overflow:scroll; margin:0px; padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" id="F" style="table-layout: fixed;">
																					<col width="50" align="center">
																					<col width="130" align="center">
																					<col width="220" align="center">
																					<col width="100" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<ezf:row ezfHyo="F">
																						<tr>
																							<td><ezf:label name="xxLineNum_F" ezfName="xxLineNum_F" ezfHyo="F" ezfArrayNo="0" /></td>
																							<td><ezf:inputText name="mdseCd_F" ezfName="mdseCd_F" value="WWWWWWWWW1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"16\""/></td>
																							<td><ezf:inputText name="mdseDescShortTxt_F" ezfName="mdseDescShortTxt_F" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																							<td><ezf:inputText name="xxTotUnitNetWt_F" ezfName="xxTotUnitNetWt_F" value="1234567890" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"17\""/></td>
																							<td><ezf:inputText name="coaMdseTpDescTxt_F" ezfName="coaMdseTpDescTxt_F" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"50\""/></td>
																							<td><ezf:inputText name="coaProdDescTxt_F" ezfName="coaProdDescTxt_F" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"50\""/></td>
																							<td><ezf:inputText name="zerothProdCtrlNm_F" ezfName="zerothProdCtrlNm_F" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"60\""/></td>
																							<td><ezf:inputText name="firstProdCtrlNm_F" ezfName="firstProdCtrlNm_F" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"60\""/></td>
																							<td><ezf:inputText name="scdProdCtrlNm_F" ezfName="scdProdCtrlNm_F" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"60\""/></td>
																							<td><ezf:inputText name="thirdProdCtrlNm_F" ezfName="thirdProdCtrlNm_F" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"60\""/></td>
																							<td><ezf:inputText name="frthProdCtrlNm_F" ezfName="frthProdCtrlNm_F" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"60\""/></td>
																						</tr>
																					</ezf:row>
																				</table>
																			</div>
																		</div>
																	</td>
																</tr>
															</table>
														</div>
													</td>
												</tr>
											</table>
											<script type="text/javascript" defer>
												S21TableUI.initialize("parentBoxF", "FHEAD", "F", -1, false, false);
											</script>
											<br>
										</div>
									</c:when>
									<%-- START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] --%>
									<%-- ######################################## SA History Tab ######################################## --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'SaHistory'}">
										<script type="text/javascript">
											document.getElementById( "Customer").className = "pTab_OFF";
											document.getElementById( "Header").className = "pTab_OFF";
											document.getElementById( "Lines").className = "pTab_OFF";
											document.getElementById( "Comments").className = "pTab_OFF";
											document.getElementById( "Additional").className = "pTab_OFF";
											document.getElementById( "SaHistory").className = "pTab_ON";
										</script>
										<div class="pTab_BODY_In">
											<br>
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<tr>
													<td>
														<div id="parentBoxH">
															<table border ="0" cellpadding="0" cellspacing="0" width="100%">
																<col width="5">
																<col width="">
																<tr>
																	<td></td>
																	<td>
																		<div style="float:left; display:block">
																			<div id='leftTblHead' style="display:block;"></div>
																			<div id='leftTbl' style="float:left; display:block; height:245px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
																		</div>
																		<div style="float:left">
																			<div id='rightTblHead' style="width:1073px; display:block; overflow:hidden; margin:0px;padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" id="HHEAD" width="694px" style="margin-right:20px">
																					<col width="70" align="left">
																					<col width="90" align="left">
																					<col width="80" align="left">
																					<col width="432" align="left">
																					<col width="95" align="left">
																					<col width="110" align="left">
																					<col width="95" align="left">
																					<col width="95" align="left">

																					<tr height="24">
																						<td id="HH0" class="pClothBs" style="padding-left:5px">SA #</td>
																						<td id="HH1" class="pClothBs" style="padding-left:5px">Serial #</td>
																						<td id="HH2" class="pClothBs" style="padding-left:5px">Contract #</td>
																						<td id="HH3" class="pClothBs" style="padding-left:5px">Sold To Customer</td>
																						<td id="HH4" class="pClothBs" style="padding-left:5px">Created Date</td>
																						<td id="HH5" class="pClothBs" style="padding-left:5px">Schedule Status</td>
																						<td id="HH6" class="pClothBs" style="padding-left:5px">Valid From</td>
																						<td id="HH7" class="pClothBs" style="padding-left:5px">Valid To</td>
																					</tr>
																				</table>
																			</div>
																			<div id="rightTbl" style="width:1090px; height:245px; display:block; overflow:scroll; margin:0px; padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" id="H" style="table-layout: fixed;">
																					<col width="70" align="left">
																					<col width="90" align="left">
																					<col width="80" align="left">
																					<col width="432" align="left">
																					<col width="95" align="left">
																					<col width="110" align="left">
																					<col width="95" align="left">
																					<col width="95" align="left">

																					<ezf:row ezfHyo="H">
																						<tr>
																							<td style="padding-left:5px;padding-top:5px;padding-bottom:5px;padding-right:5px"><ezf:label name="schdAgmtNum_H" ezfName="schdAgmtNum_H" ezfHyo="H" ezfArrayNo="0" /></td>
																							<td style="padding-left:5px;padding-top:5px;padding-bottom:5px;padding-right:5px"><ezf:label name="serNum_H" ezfName="serNum_H" ezfHyo="H" ezfArrayNo="0" /></td>
																							<td style="padding-left:5px;padding-top:5px;padding-bottom:5px;padding-right:5px"><ezf:label name="dsContrNum_H" ezfName="dsContrNum_H" ezfHyo="H" ezfArrayNo="0" /></td>
																							<td style="padding-left:5px;padding-top:5px;padding-bottom:5px;padding-right:5px"><ezf:label name="locNm_H" ezfName="locNm_H" ezfHyo="H" ezfArrayNo="0" /></td>
																							<td style="padding-left:5px;padding-top:5px;padding-bottom:5px;padding-right:5px"><ezf:label name="schdAgmtCratDt_H" ezfName="schdAgmtCratDt_H" ezfHyo="H" ezfArrayNo="0" /></td>
																							<td style="padding-left:5px;padding-top:5px;padding-bottom:5px;padding-right:5px"><ezf:label name="schdAgmtStsNm_H" ezfName="schdAgmtStsNm_H" ezfHyo="H" ezfArrayNo="0" /></td>
																							<td style="padding-left:5px;padding-top:5px;padding-bottom:5px;padding-right:5px"><ezf:label name="schdAgmtVldFromDt_H" ezfName="schdAgmtVldFromDt_H" ezfHyo="H" ezfArrayNo="0" /></td>
																							<td style="padding-left:5px;padding-top:5px;padding-bottom:5px;padding-right:5px"><ezf:label name="schdAgmtVldThruDt_H" ezfName="schdAgmtVldThruDt_H" ezfHyo="H" ezfArrayNo="0" /></td>
																						</tr>
																					</ezf:row>
																				</table>
																			</div>
																		</div>
																	</td>
																</tr>
															</table>
														</div>
													</td>
												</tr>
											</table>
											<script type="text/javascript" defer>
												S21TableUI.initialize("parentBoxH", "HHEAD", "H", -1, false, false);
											</script>
											<br>
										</div>
									</c:when>
									<%-- END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD] --%>
								</c:choose>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<%-- **** Task specific area ends here **** --%>
