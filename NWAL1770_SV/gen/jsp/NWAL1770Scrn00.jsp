<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230829095517 --%>
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
			<input type="hidden" name="pageID" value="NWAL1770Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supply Quote Entry">

<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%"><li title = "Supply Quote Entry" class="pTab_ON" ><a href="#">Quote Entry</a></li></td>
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
						<col width="340">
						<col width="395">
						<col width="">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
									<col width="77">
									<col width="102">
									<col width="65">
									<col width="75">
									<tr height="22">
										<td class="stab">Quote Number</td>
										<td><ezf:inputText name="splyQuoteNum" ezfName="splyQuoteNum" value="12345678" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td colspan="2"><ezf:inputButton name="Quote_Search" value="Search" htmlClass="pBtn5" />
										<ezf:inputButton name="Quote_Copy" value="Copy" htmlClass="pBtn5" /></td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="dsOrdCatgDescTxt_LK" ezfName="dsOrdCatgDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderCategory" otherAttr=" id=\"dsOrdCatgDescTxt_LK\" ezfanchortext=\"\"">Category (*)</ezf:anchor></td>
										<td colspan="3"><ezf:inputText name="dsOrdCatgDescTxt" ezfName="dsOrdCatgDescTxt" value="WWWWWWWWW1WWWWWWWWW2" otherEvent1="OnBlur_DeriveFromCategory" otherAttr=" size=\"33\" maxlength=\"50\" ezffocusout=\"OnBlur_DeriveFromCategory\""/>
									</tr>
									<tr height="22">
										<td class="stab">Reason Code</td>
										<td colspan="3">
											<ezf:select name="dsOrdTpCd" ezfName="dsOrdTpCd" ezfBlank="1" ezfCodeName="dsOrdTpCd_PL" ezfDispName="dsOrdTpDescTxt_PL" otherEvent1=" onchange=\"sendServer('OnChange_Reason')\"" otherAttr=" style=\"width:237px;\""/>
										</td>
									</tr>
									<tr height="22">
										<td class="stab">Source Name</td>
										<td colspan="3">
											<ezf:select name="splyQuoteSrcTpCd" ezfName="splyQuoteSrcTpCd" ezfBlank="1" ezfCodeName="splyQuoteSrcTpCd_PL" ezfDispName="splyQuoteSrcTpDescTxt_PL" otherAttr=" style=\"width:237px;\""/>
										</td>
									</tr>
									<tr height="22">
										<td class="stab">Quote Name</td>
										<td colspan="3"><ezf:inputText name="splyQuoteNm" ezfName="splyQuoteNm" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"33\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="22">
										<td class="stab">Quote Date</td>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<tr style="padding:0;">
													<td><ezf:inputText name="splyQuoteDt" ezfName="splyQuoteDt" value="01/04/2016" otherEvent1="OnBlur_QuoteDate" otherAttr=" size=\"10\" maxlength=\"10\" ezffocusout=\"OnBlur_QuoteDate\""/></td>
													<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('splyQuoteDt', 4);"></td>
												</tr>
											</table>
										</td>
										<td class="stab">Days Valid&nbsp;&nbsp;</td>
										<td><ezf:inputText name="splyQuoteVldDaysAot" ezfName="splyQuoteVldDaysAot" value="999" otherEvent1="OnBlur_DaysValid" otherAttr=" size=\"9\" maxlength=\"3\" ezffocusout=\"OnBlur_DaysValid\""/></td>
									</tr>
									<tr height="22">
										<td class="stab">Valid To Date</td>
										<td><ezf:inputText name="splyQuoteVldThruDt" ezfName="splyQuoteVldThruDt" value="01/08/2016" otherAttr=" size=\"10\" tabindex=\"-1\""/></td>
										<td class="stab">Status</td>
										<td><ezf:inputText name="splyQuoteStsDescTxt" ezfName="splyQuoteStsDescTxt" value="Submitted" otherAttr=" size=\"9\" tabindex=\"-1\""/></td>
									</tr>
								</table>
							</td>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
									<col width="105">
									<col width="77">
									<col width="200">
									<tr height="22">
										<td class="stab">Customer PO</td>
										<td colspan="2">
											<ezf:inputText name="custIssPoNum" ezfName="custIssPoNum" value="WWWWWWWWW1WWWWWWWWW2" otherEvent1="OnBlur_CustomerPo" otherAttr=" size=\"35\" maxlength=\"35\" ezffocusout=\"OnBlur_CustomerPo\""/>
										</td>
									</tr>
									<tr height="22">
										<td class="stab">Customer PO Date</td>
										<td colspan="2">
											<table border="0" cellpadding="1" cellspacing="0">
												<tr style="padding:0;">
													<td><ezf:inputText name="custIssPoDt" ezfName="custIssPoDt" value="01/04/2016" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
													<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('custIssPoDt', 4);"></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="slsRepTocNm_LK" ezfName="slsRepTocNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SalesRep" otherAttr=" id=\"slsRepTocNm_LK\" ezfanchortext=\"\"">Sales Rep Name (*)</ezf:anchor></td>
										<td colspan="2"><ezf:inputText name="slsRepTocNm" ezfName="slsRepTocNm" value="WWWWWWWWW1WWWWWWWWW2" otherEvent1="OnBlur_DeriveFromSalesRepName" otherAttr=" size=\"35\" maxlength=\"50\" ezfnoupperfocusout=\"OnBlur_DeriveFromSalesRepName\""/>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="psnNum_LK" ezfName="psnNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SalesRep" otherAttr=" id=\"psnNum_LK\" ezfanchortext=\"\"">Sales Rep Number (*)</ezf:anchor></td>
										<td colspan="2">
											<ezf:inputText name="psnNum" ezfName="psnNum" value="12345678" otherEvent1="OnBlur_DeriveFromSalesRepCode" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_DeriveFromSalesRepCode\""/>
											<ezf:inputButton name="OpenWin_SalesCredit" value="Sales Credit" htmlClass="pBtn7" />
										</td>
									</tr>
									<tr height="22">
										<td class="stab">Sales Bus Unit</td>
										<td colspan="2"><ezf:inputText name="xxScrItem54Txt_CB" ezfName="xxScrItem54Txt_CB" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"35\" tabindex=\"-1\""/></td>
									</tr>
									<tr height="22">
										<td class="stab">Sales Rep Branch</td>
										<td colspan="2"><ezf:inputText name="xxScrItem54Txt_CE" ezfName="xxScrItem54Txt_CE" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"35\" tabindex=\"-1\""/></td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="prcCatgNm_LK" ezfName="prcCatgNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PriceList" otherAttr=" id=\"prcCatgNm_LK\" ezfanchortext=\"\"">Price List</ezf:anchor></td>
										<td colspan="2"><ezf:inputText name="prcCatgNm" ezfName="prcCatgNm" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"35\" maxlength=\"50\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
									<col width="75">
									<col width="80">
									<col width="15">
									<col width="195">
									<tr height="22">
										<td class="stab">Order Number</td>
										<td class="pOut">
											<ezf:anchor name="cpoOrdNum_LK" ezfName="cpoOrdNum_LK" ezfEmulateBtn="1" ezfGuard="MoveWin_OrderEntry" otherAttr=" id=\"cpoOrdNum_LK\" ezfanchortext=\"\"">
												<ezf:label name="cpoOrdNum" ezfName="cpoOrdNum" />
											</ezf:anchor>
										</td>
										<td class="stab">&nbsp;</td>
										<td>
											<ezf:inputButton name="OpenWin_ShippingDetail" value="Shipping Detail" htmlClass="pBtn8" />
											<ezf:inputButton name="OpenWin_Attachments" value="Attachment" htmlClass="pBtn8" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<br>
											<fieldset style="border:1px solid #808080;">
												<legend style="font-size:12px;">Quote Price Summary</legend>
													<table border="0" cellpadding="0" cellspacing="0">
														<col width="10">
														<col width="265">
														<col width="" valign="bottom">
														<tr>
															<td></td>
															<td>
																<table border="1" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																	<col width="110">
																	<col width="130">
																	<tr height="23">
																		<td class="pClothBs">Sub Total</td>
																		<td align="right"><ezf:label name="xxSubTotCalcPrcAmt" ezfName="xxSubTotCalcPrcAmt" /></td>
																	</tr>
																	<tr height="23">
																		<td class="pClothBs">Charges</td>
																		<td align="right"><ezf:label name="xxTotChrgPrcAmt" ezfName="xxTotChrgPrcAmt" /></td>
																	</tr>
																	<tr height="23">
																		<td class="pClothBs">Tax</td>
																		<td align="right"><ezf:label name="xxTotTaxAmt" ezfName="xxTotTaxAmt" /></td>
																	</tr>
																	<tr height="23">
																		<td class="pClothBs">Total</td>
																		<td align="right"><ezf:label name="xxTotAmt" ezfName="xxTotAmt" /></td>
																	</tr>
																</table>
															</td>
															<td>
																<ezf:inputButton name="Calculation_QuoteAmount" value="$" htmlClass="pBtn5" />
															</td>
														</tr>
													</table>
											</fieldset>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br>

					<%-- ######################################## DETAIL ######################################## --%>
					<table width="99%">
						<col valign="top">
						<tr>
							<td>
								<div class="pTab_HEAD">
									<ul>
										<li id="Customer" title="Customer / Contact" class="pTab_ON" ><ezf:anchor name="" ezfName="xxTabProt_CC" ezfEmulateBtn="1" ezfGuard="TAB_CustomerContact" >Customer</ezf:anchor></li>
										<li id="Delivery" title="Delivery / Payment" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_DP" ezfEmulateBtn="1" ezfGuard="TAB_DeliveryPayment" >Delivery/Pmt</ezf:anchor></li>
										<li id="Items" title="Items" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_IT" ezfEmulateBtn="1" ezfGuard="TAB_Items" >Items</ezf:anchor></li>
										<li id="Comments" title="Comments" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_CM" ezfEmulateBtn="1" ezfGuard="TAB_Comments" >Comments</ezf:anchor></li>
										<li id="Additional" title="Additional Data" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_AD" ezfEmulateBtn="1" ezfGuard="TAB_AdditionalData" >Additional</ezf:anchor></li>
										<!-- START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->
										<li id="OrderHistory" title="Order History" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_OH" ezfEmulateBtn="1" ezfGuard="TAB_OrderHistory" >Order History</ezf:anchor></li>
										<!-- END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->
									</ul>
								</div>
								<c:choose>
									<%-- ######################################## Customer / Contact Tab ######################################## --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Customer'}">
										<script type="text/javascript">
											document.getElementById( "Customer").className = "pTab_ON";
											document.getElementById( "Delivery").className = "pTab_OFF";
											document.getElementById( "Items").className = "pTab_OFF";
											document.getElementById( "Comments").className = "pTab_OFF";
											document.getElementById( "Additional").className = "pTab_OFF";
											<%-- START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] --%>
											document.getElementById( "OrderHistory").className = "pTab_OFF";
											<%-- END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] --%>
										</script>
										<div class="pTab_BODY_In">
											<br>
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="5">
												<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
												<!-- <col width="360"> -->
												<!-- <col width="5"> -->
												<!-- <col width="360"> -->
												<!-- <col width="5"> -->
												<!-- <col width="360"> -->
												<col width="362">
												<col width="4">
												<col width="362">
												<col width="4">
												<col width="362">
												<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
												<tr>
													<!-- Add Start 2017/09/25 H.Sugawara QC#21322 -->
													<td>&nbsp;</td>
													<td valign="top">

														<!-- START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->
														<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
															<tr>
																<td>
														<!-- END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->

																	<!-- START 2023/07/20 T.Fukuta [CSA-QC#61467,MOD] -->
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
																				<td colspan="3"><ezf:textArea name="xxAllLineAddr_SE" ezfName="xxAllLineAddr_SE" otherAttr=" rows=\"4\" cols=\"36\" tabindex=\"-1\""/></td>
																			</tr>
																		</table>
																	</fieldset>
																	<!-- END 2023/07/20 T.Fukuta [CSA-QC#61467,MOD] -->

														<!-- START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->
																</td>
															</tr>
															<tr>
																<td>
																	<br>
																	<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																		<col width="3">
																		<col width="60">
																		<col width="280">
																		<tr>
																			<td></td>
																			<td class="stab">Credit Rep</td>
																			<td><ezf:inputText name="crAnlstPsnNm" ezfName="crAnlstPsnNm" value="X99999 XXXXXXXXXX XXXXXXXXXX" otherAttr=" size=\"39\" maxlength=\"90\" tabindex=\"-1\""/></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
														<!-- END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->

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
																	<!-- <td class="stab"><a href="#" id="billToCustCd_LK" name="billToCustCd_LK" ezfName="billToCustCd_LK" onclick="sendServer('OpenWin_BillTo')" ezfanchortext>Location (*)</a></td> -->
																	<td class="stab"><ezf:anchor name="billToCustCd_LK" ezfName="billToCustCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"billToCustCd_LK\" ezfanchortext=\"\"">Bill To Code (*)</ezf:anchor></td>
																	<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																	<td><ezf:inputText name="billToCustCd" ezfName="billToCustCd" value="5819214" otherEvent1="OnBlur_DeriveFromBillToLocation" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromBillToLocation\""/></td>
																</tr>
																<tr>
																	<td class="stab">Address</td>
																	<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																	<!-- <td colspan="3"><textarea rows="4" cols="35" name="xxAllLineAddr_BT" ezfname="xxAllLineAddr_BT" tabindex="-1" class="pPro">1233 GAITHER STRET MT LAUREL, NU 70012</textarea></td> -->
																	<td colspan="3"><ezf:textArea name="xxAllLineAddr_BT" ezfName="xxAllLineAddr_BT" otherAttr=" rows=\"4\" cols=\"36\" tabindex=\"-1\""/></td>
																	<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																</tr>
															</table>
														</fieldset>
													</td>
													<td>&nbsp;</td>
													<td>
														<fieldset style="border:1px solid #808080;">
															<legend style="font-size:12px;">Ship To Customer</legend>
															<br>
															<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																<col width="60">
																<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																<!-- <col width="110"> -->
																<!-- <col width="65"> -->
																<!-- <col width="110"> -->
																<col width="100">
																<col width="87">
																<col width="100">
																<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																<tr height="23">
																	<td class="stab"><ezf:anchor name="shipToCustAcctNm_LK" ezfName="shipToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"shipToCustAcctNm_LK\" ezfanchortext=\"\"">Name (*)</ezf:anchor></td>
																	<td colspan="3">
																		<table border="0" cellpadding="0" cellspacing="0">
																			<col width="">
																			<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																			<!-- <col width="30" align="center"> -->
																			<col width="28" align="center">
																			<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																			<col width="10" align="center">
																			<tr>
																				<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																				<!-- <td><input type="text" size="31" maxlength="60" value="CANON FINANCIAL SVC" name="shipToCustAcctNm" ezfname="shipToCustAcctNm" class="pHsu" ezffocusout="OnBlur_DeriveFromShipToName"></td> -->
																				<td><ezf:inputText name="shipToCustAcctNm" ezfName="shipToCustAcctNm" value="CANON FINANCIAL SVC" otherEvent1="OnBlur_DeriveFromShipToName" otherAttr=" size=\"33\" maxlength=\"60\" ezffocusout=\"OnBlur_DeriveFromShipToName\""/></td>
																				<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																				<td>DS</td>
																				<td class="pOut"><ezf:anchor name="dropShipFlg_LK" ezfName="dropShipFlg_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToChange" otherAttr=" id=\"dropShipFlg_LK\" ezfanchortext=\"\""><ezf:label name="dropShipFlg" ezfName="dropShipFlg" /></ezf:anchor></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td class="stab">DS Name</td>
																	<td colspan="3"><ezf:inputText name="shipToLocNm_DS" ezfName="shipToLocNm_DS" value="1002805" otherAttr=" size=\"39\" maxlength=\"90\""/></td>
																</tr>
																<tr height="23">
																	<td class="stab"><ezf:anchor name="shipToCustAcctCd_LK" ezfName="shipToCustAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"shipToCustAcctCd_LK\" ezfanchortext=\"\"">Number (*)</ezf:anchor></td>
																	<td><ezf:inputText name="shipToCustAcctCd" ezfName="shipToCustAcctCd" value="1002805" otherEvent1="OnBlur_DeriveFromShipToAccount" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromShipToAccount\""/></td>
																	<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																	<!-- <td class="stab"><a href="#" id="shipToCustCd_LK" name="shipToCustCd_LK" ezfName="shipToCustCd_LK" onclick="sendServer('OpenWin_ShipTo')" ezfanchortext>Location (*)</a></td> -->
																	<td class="stab"><ezf:anchor name="shipToCustCd_LK" ezfName="shipToCustCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"shipToCustCd_LK\" ezfanchortext=\"\"">Ship To Code (*)</ezf:anchor></td>
																	<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																	<td><ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" value="5819214" otherEvent1="OnBlur_DeriveFromShipToLocation" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromShipToLocation\""/></td>
																</tr>
																<tr>
																	<td class="stab">Address</td>
																	<!-- Mod Start 2017/09/25 H.Sugawara QC#19922 -->
																	<!-- <td colspan="3"><textarea rows="4" cols="35" name="xxAllLineAddr_SH" ezfname="xxAllLineAddr_SH" tabindex="-1" class="pPro">1233 GAITHER STRET MT LAUREL, NU 70012</textarea></td> -->
																	<td colspan="3"><ezf:textArea name="xxAllLineAddr_SH" ezfName="xxAllLineAddr_SH" otherAttr=" rows=\"4\" cols=\"36\" tabindex=\"-1\""/></td>
																	<!-- Mod End   2017/09/25 H.Sugawara QC#19922 -->
																</tr> 
																<tr>
																	<td class="stab">Attention</td>
																	<td colspan="3"><ezf:inputText name="sellToFirstRefCmntTxt" ezfName="sellToFirstRefCmntTxt" value="1002805" otherAttr=" size=\"39\" maxlength=\"90\""/></td>
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
													<!-- 				<td colspan="3"><textarea rows="4" cols="35" name="xxAllLineAddr_SE" ezfname="xxAllLineAddr_SE" tabindex="-1" class="pPro">1233 GAITHER STRET MT LAUREL, NU 70012</textarea></td> -->
													<!-- 			</tr> -->
													<!-- 		</table> -->
													<!-- 	</fieldset> -->
													<!-- </td> -->
													<!-- Del End   2017/09/25 H.Sugawara QC#21322 -->
												</tr>
											</table>
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="5">
												<col width="115">
												<col width="95">
												<col width="95">
												<tr>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="OpenWin_SpecialInstruction" value="Special Instruction" htmlClass="pBtn10" /></td>
													<td><ezf:inputButton name="OpenWin_Confirmation" value="Confirmations" htmlClass="pBtn8" /></td>
													<td><ezf:inputButton name="OpenWin_Tracking" value="Tracking" htmlClass="pBtn8" /></td>
												<tr>
											</table>
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="5">
												<col width="1094">
												<tr>
													<td>&nbsp;</td>
													<td>
														<fieldset style="border:1px solid #808080;">
															<legend style="font-size:12px;">Contact</legend>
															<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																<col width="90">
																<col width="90">
																<tr>
																	<td><ezf:inputButton name="Add_ContactLine" value="Add Line" htmlClass="pBtn7" /></td>
																	<td><ezf:inputButton name="Delete_ContactLine" value="Delete Line" htmlClass="pBtn7" /></td>
																</tr>
																<tr>
																	<td colspan="6">
																		<table border ="0" cellpadding="0" cellspacing="0" width="100%">
																			<tr>
																				<td>
																					<div id="TopTBL" style="overflow-y:none; overflow-x:none;">
																						<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																							<col width="28">
																							<col width="180" align="center">
																							<col width="174" align="center">
																							<col width="152" align="center">
																							<col width="120" align="center">
																							<col width="50" align="center">
																							<col width="150" align="center">
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
																					<div id="TBL" style="overflow-y:scroll; overflow-x:none; height:57; width:1074; word-break: break-all;">
																						<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed;">
																							<col width="28" align="center">
																							<col width="180" align="center">
																							<col width="174" align="center">
																							<col width="152" align="center">
																							<col width="120" align="center">
																							<col width="50" align="center">
																							<col width="150" align="center">
																							<col width="110" align="center">
																							<col width="90" align="center">
																							<ezf:row ezfHyo="A">
																								<tr>
																									<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
																									<td>
																										<ezf:select name="ctacPsnTpCd_A" ezfName="ctacPsnTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="ctacTpCd_PL" ezfDispName="ctacTpDescTxt_PL" otherEvent1=" onchange=\"sendServer('OnChange_ContactRole', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width:172px;\""/>
																									</td>
																									<td>
																										<table border="0" cellpadding="1" cellspacing="0">
																											<tr style="padding:0;">
																												<td><ezf:inputText name="ctacPsnFirstNm_A" ezfName="ctacPsnFirstNm_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"30\""/></td>
																												<td><ezf:anchor name="ctacPsnFirstNm_LK" ezfName="ctacPsnFirstNm_LK" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ContactPerson" otherAttr=" ezfanchortext=\"\"">F</ezf:anchor></td>
																											</tr>
																										</table>
																									</td>
																									<td><ezf:inputText name="ctacPsnLastNm_A" ezfName="ctacPsnLastNm_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"30\""/></td>
																									<td><ezf:inputText name="ctacPsnTelNum_A" ezfName="ctacPsnTelNum_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\""/></td>
																									<td><ezf:inputText name="ctacPsnExtnNum_A" ezfName="ctacPsnExtnNum_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"10\""/></td>
																									<td><ezf:inputText name="ctacPsnEmlAddr_A" ezfName="ctacPsnEmlAddr_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"320\""/></td>
																									<td><ezf:inputText name="ctacPsnFaxNum_A" ezfName="ctacPsnFaxNum_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\""/></td>
																									<td>
																										<ezf:select name="ctacCustRefTpCd_A" ezfName="ctacCustRefTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="ctacCustRefTpCd_PL" ezfDispName="ctacCustRefTpDescTxt_PL" otherAttr=" style=\"width:80px;\""/>
																									</td>
																								</tr>
																							</ezf:row>
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

									<%-- ######################################## Delivery / Payment Tab ######################################## --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Delivery'}">
										<script type="text/javascript">
											document.getElementById( "Customer").className = "pTab_OFF";
											document.getElementById( "Delivery").className = "pTab_ON";
											document.getElementById( "Items").className = "pTab_OFF";
											document.getElementById( "Comments").className = "pTab_OFF";
											document.getElementById( "Additional").className = "pTab_OFF";
											<%-- START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] --%>
											document.getElementById( "OrderHistory").className = "pTab_OFF";
											<%-- END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] --%>
										</script>
										<div class="pTab_BODY_In">
											<br>
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="5">
												<col width="730">
												<tr>
													<td>&nbsp;</td>
													<td>
														<fieldset style="border:1px solid #808080;">
															<legend style="font-size:12px;">Delivery Information</legend>
															<br>
															<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																<col width="88">
																<col width="250">
																<col width="120">
																<col width="250">
																<tr height="27">
																	<td class="stab"><ezf:anchor name="frtCondDescTxt_LK" ezfName="frtCondDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_FreightTerms" otherAttr=" id=\"frtCondDescTxt_LK\" ezfanchortext=\"\"">Freight Terms</ezf:anchor></td>
																	<td><ezf:inputText name="frtCondDescTxt" ezfName="frtCondDescTxt" value="WWWWWWWWW1WWWWWWWWW2" otherEvent1="OnBlur_FreightTerms" otherAttr=" size=\"30\" maxlength=\"50\" ezffocusout=\"OnBlur_FreightTerms\""/></td>
																	<td class="stab">Service Level</td>
																	<td>
																		<ezf:select name="shpgSvcLvlCd" ezfName="shpgSvcLvlCd" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_PL" ezfDispName="shpgSvcLvlDescTxt_PL" otherEvent1=" onchange=\"sendServer('OnChange_ShpgSvcLvlCd')\"" otherAttr=" style=\"width:216px;\""/>
																	</td>
																</tr>
																<tr height="28">
																	<td class="stab"><ezf:anchor name="carrSvcLvlDescTxt_LK" ezfName="carrSvcLvlDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CarrierServiceLevel" otherAttr=" id=\"carrSvcLvlDescTxt_LK\" ezfanchortext=\"\"">Carrier Service Level</ezf:anchor></td>
																	<td><ezf:inputText name="carrSvcLvlDescTxt" ezfName="carrSvcLvlDescTxt" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"30\" maxlength=\"50\""/></td>
																	<td class="stab">Carrier Acct Number</td>
																	<td><ezf:inputText name="carrAcctNum" ezfName="carrAcctNum" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"30\" maxlength=\"20\" ezftoupper=\"\""/></td>
																</tr>
																<tr height="27">
																	<td class="stab">Special Handling</td>
																	<td>
																		<ezf:select name="spclHdlgTpCd" ezfName="spclHdlgTpCd" ezfBlank="1" ezfCodeName="spclHdlgTpCd_PL" ezfDispName="spclHdlgTpDescTxt_PL" otherAttr=" style=\"width:216px;\""/>
																	</td>
																	<td class="stab">Future Delivery Date</td>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0">
																			<tr style="padding:0;">
																				<td><ezf:inputText name="rddDt" ezfName="rddDt" value="01/04/2016" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																				<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rddDt', 4);"></td>
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
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="5">
												<col width="730">
												<tr>
													<td>&nbsp;</td>
													<td>
														<fieldset style="border:1px solid #808080;">
															<legend style="font-size:12px;">Payment Terms Information</legend>
															<br>
															<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																<col width="88">
																<col width="250">
																<col width="120">
																<col width="250">
																<tr height="27">
																	<td class="stab"><ezf:anchor name="pmtTermCashDiscDescTxt_LK" ezfName="pmtTermCashDiscDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PaymentTerms" otherAttr=" id=\"pmtTermCashDiscDescTxt_LK\" ezfanchortext=\"\"">Payment Terms</ezf:anchor></td>
																	<td><ezf:inputText name="pmtTermCashDiscDescTxt" ezfName="pmtTermCashDiscDescTxt" value="WWWWWWWWW1WWWWWWWWW2" otherEvent1="OnBlur_DeriveFromPaymentTerms" otherAttr=" size=\"30\" ezffocusout=\"OnBlur_DeriveFromPaymentTerms\" tabindex=\"-1\""/></td>
																	<td class="stab">Credit Card Information</td>
																	<td><ezf:inputButton name="MoveWin_CreditCard" value="Credit Card" htmlClass="pBtn7" /></td>
																</tr>
																<tr height="27">
																	<td class="stab">Payment Method</td>
																	<td colspan="3">
																		<ezf:select name="dsPmtMethCd" ezfName="dsPmtMethCd" ezfBlank="1" ezfCodeName="dsPmtMethCd_PL" ezfDispName="dsPmtMethDescTxt_PL" otherEvent1=" onchange=\"sendServer('OnChange_PaymentMethod')\"" otherAttr=" style=\"width:216px;\""/>
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

									<%-- ######################################## Items Tab ######################################## --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Items'}">
										<script type="text/javascript">
											document.getElementById( "Customer").className = "pTab_OFF";
											document.getElementById( "Delivery").className = "pTab_OFF";
											document.getElementById( "Items").className = "pTab_ON";
											document.getElementById( "Comments").className = "pTab_OFF";
											document.getElementById( "Additional").className = "pTab_OFF";
											<%-- START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] --%>
											document.getElementById( "OrderHistory").className = "pTab_OFF";
											<%-- END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] --%>
										</script>
										<div class="pTab_BODY_In">
											<br>
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="5">
												<col width="180">
												<col width="96">
												<col width="96">
												<col width="96">
												<col width="96">
												<col width="96">
												<col width="96">
												<col width="96">
												<tr>
													<td></td>
													<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
													<td><ezf:inputButton name="Add_ItemLine" value="Add Line(F12)" htmlClass="pBtn8" /></td>
													<td><ezf:inputButton name="Cancel_ItemLine" value="Cancel" htmlClass="pBtn8" /></td>
													<td><ezf:inputButton name="OpenWin_Profitability" value="Profitability" htmlClass="pBtn8" /></td>
													<td><ezf:inputButton name="OpenWin_PriceChanges" value="Price Changes" htmlClass="pBtn8" /></td>
													<td><ezf:inputButton name="OpenWin_StockOverview" value="Invty. Inq" htmlClass="pBtn8" /></td>
													<td><ezf:inputButton name="OpenWin_CheckContract" value="Check Contract" htmlClass="pBtn8" /></td>
												</tr>
											</table>
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<tr>
													<td>
														<div id="parentBoxB">
															<table border ="0" cellpadding="0" cellspacing="0" width="100%">
																<col width="5">
																<col width="">
																<tr>
																	<td></td>
																	<td>
																		<div style="float:left; display:block">
																			<div id='leftTblHead' style="display:block;"></div>
																			<div id='leftTbl' style="float:left; display:block; height:280px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
																		</div>
																		<div style="float:left">
																			<div id='rightTblHead' style="width:1073px; display:block; overflow:hidden; margin:0px;padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" id="BHEAD" width="2428px" style="margin-right:20px">
																					<col width="28" align="center">
																					<col width="50" align="center">
																					<col width="130" align="center">
																					<col width="130" align="center">
																					<col width="220" align="center">
																					<col width="90" align="center">
																					<col width="140" align="center">
																					<!-- START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->
																					<col width="90" align="center">
																					<!-- END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->
																					<col width="105" align="center">
																					<col width="105" align="center">
																					<col width="105" align="center">
																					<col width="150" align="center">
																					<col width="120" align="center">
																					<col width="120" align="center">
																					<col width="105" align="center">
																					<col width="105" align="center">
																					<col width="105" align="center">
																					<col width="105" align="center">
																					<col width="105" align="center">
																					<col width="140" align="center">
																					<col width="100" align="center">
																					<col width="200" align="center">
																					<col width="200" align="center">
																					<col width="150" align="center">
																					<col width="150" align="center">
																					<col width="90" align="center">
																					<col width="120" align="center">
																					<tr height="24">
																						<td id="BH0" class="pClothBs colFix">&nbsp;</td>
																						<td id="BH1" class="pClothBs colFix">Line #</td>
																						<td id="BH2" class="pClothBs">Item # (*)</td>
																						<td id="BH3" class="pClothBs">Manufacture #</td>
																						<td id="BH4" class="pClothBs">Description</td>
																						<td id="BH5" class="pClothBs">Qty</td>
																						<td id="BH6" class="pClothBs">UOM</td>
																						<!-- START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->
																						<td id="BH26" class="pClothBs">HazMat</td>
																						<!-- END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->
																						<td id="BH7" class="pClothBs">Sell Price</td>
																						<td id="BH8" class="pClothBs">Unit List Price</td>
																						<td id="BH9" class="pClothBs">Unit Sell Price</td>
																						<td id="BH10" class="pClothBs">Price List</td>
																						<td id="BH25" class="pClothBs">Future Delivery Date</td>
																						<td id="BH24" class="pClothBs">Pricing Date</td>
																						<td id="BH11" class="pClothBs">Unit Discount</td>
																						<td id="BH12" class="pClothBs">Subtotal</td>
																						<td id="BH13" class="pClothBs">Freight</td>
																						<td id="BH14" class="pClothBs">Tax</td>
																						<td id="BH15" class="pClothBs">Total Price</td>
																						<td id="BH16" class="pClothBs">Customer Item #</td>
																						<td id="BH17" class="pClothBs">Supersede Lock</td>
																						<td id="BH18" class="pClothBs">Line Category</td>
																						<td id="BH19" class="pClothBs">Line Source</td>
																						<td id="BH20" class="pClothBs">Warehouse</td>
																						<td id="BH21" class="pClothBs">Sub Warehouse</td>
																						<td id="BH22" class="pClothBs">Each Qty</td>
																						<td id="BH23" class="pClothBs">Status</td>
																					</tr>
																				</table>
																			</div>
																			<div id="rightTbl" style="width:1090px; height:280px; display:block; overflow:scroll; margin:0px; padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed;">
																					<col width="28" align="center">
																					<col width="50" align="center">
																					<col width="130" align="center">
																					<col width="130" align="center">
																					<col width="220" align="center">
																					<col width="90" align="center">
																					<col width="140" align="center">
																					<!-- START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->
																					<col width="90" align="center">
																					<!-- END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->
																					<col width="105" align="center">
																					<col width="105" align="center">
																					<col width="105" align="center">
																					<col width="150" align="center">
																					<col width="120" align="center">
																					<col width="120" align="center">
																					<col width="105" align="center">
																					<col width="105" align="center">
																					<col width="105" align="center">
																					<col width="105" align="center">
																					<col width="105" align="center">
																					<col width="140" align="center">
																					<col width="100" align="center">
																					<col width="200" align="center">
																					<col width="200" align="center">
																					<col width="150" align="center">
																					<col width="150" align="center">
																					<col width="90" align="center">
																					<col width="120" align="center">
																					<ezf:row ezfHyo="B">
																						<tr>
																							<td><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B#{EZF_ROW_NUMBER}\""/></td>
																							<td><ezf:label name="xxLineNum_B" ezfName="xxLineNum_B" ezfHyo="B" ezfArrayNo="0" /></td>
																							<td>
																								<table border="0" cellpadding="1" cellspacing="0">
																									<tr style="padding:0;">
																										<td><ezf:inputText name="mdseCd_B" ezfName="mdseCd_B" value="WWWWWWWWW1" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_DeriveFromItem" otherAttr=" size=\"15\" maxlength=\"16\" ezffocusout=\"OnBlur_DeriveFromItem\""/></td>
																										<td><ezf:anchor name="mdseCd_LK" ezfName="mdseCd_LK" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_Merchandise" otherAttr=" ezfanchortext=\"\"">I</ezf:anchor></td>
																									</tr>
																								</table>
																							</td>
																							<td><ezf:inputText name="mnfItemCd_B" ezfName="mnfItemCd_B" value="WWWWWWWWW1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"17\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="mdseDescShortTxt_B" ezfName="mdseDescShortTxt_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="ordCustUomQty_B" ezfName="ordCustUomQty_B" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_DeriveFromQty" otherAttr=" size=\"11\" maxlength=\"10\" ezffocusout=\"OnBlur_DeriveFromQty\""/></td>
																							<td>
																								<ezf:select name="custUomCd_B" ezfName="custUomCd_B" ezfHyo="B" ezfArrayNo="0" ezfCodeName="pkgUomCd_PL" ezfDispName="pkgUomDescTxt_PL" ezfCodeDispHyo="B" otherEvent1=" onchange=\"sendServer('OnChange_UOM', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:134px;\""/>
																							</td>
																							<!-- START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->
																							<td><ezf:inputText name="hazMatFlg_B" ezfName="hazMatFlg_B" value="N" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"1\" tabindex=\"-1\""/></td>
																							<!-- END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] -->
																							<td><ezf:inputText name="dealSplyQuoteDtlSlsAmt_B" ezfName="dealSplyQuoteDtlSlsAmt_B" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_DeriveFromDtlSlsAmt" otherAttr=" size=\"13\" maxlength=\"19\" ezffocusout=\"OnBlur_DeriveFromDtlSlsAmt\""/></td>
																							<td><ezf:inputText name="dealPrcListPrcAmt_B" ezfName="dealPrcListPrcAmt_B" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="xxDtlDiscAmt_B" ezfName="xxDtlDiscAmt_B" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\""/></td>
																							<td>
																								<table border="0" cellpadding="1" cellspacing="0">
																									<tr style="padding:0;">
																										<td><ezf:inputText name="prcCatgNm_B" ezfName="prcCatgNm_B" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/></td>
																										<td><ezf:anchor name="prcCatgNm_BL" ezfName="prcCatgNm_BL" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_PriceList" otherAttr=" ezfanchortext=\"\"">P</ezf:anchor></td>
																									</tr>
																								</table>
																							</td>
																							<td>
																								<table border="0" cellpadding="1" cellspacing="0">
																									<tr style="padding:0;">
																										<td><ezf:inputText name="rddDt_B" ezfName="rddDt_B" value="99/99/9999" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																										<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rddDt_B', 4, '{EZF_ROW_NUMBER}');" ></td>
																									</tr>
																								</table>
																							</td>
																							<td>
																								<table border="0" cellpadding="1" cellspacing="0">
																									<tr style="padding:0;">
																										<td><ezf:inputText name="prcBaseDt_B" ezfName="prcBaseDt_B" value="99/99/9999" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																										<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('prcBaseDt_B', 4, '{EZF_ROW_NUMBER}');" ></td>
																									</tr>
																								</table>
																							</td>
																							<td><ezf:inputText name="xxTotDiscAmt_B" ezfName="xxTotDiscAmt_B" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="xxSubTotCalcPrcAmt_B" ezfName="xxSubTotCalcPrcAmt_B" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="xxTotFrtAmt_B" ezfName="xxTotFrtAmt_B" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="xxTotTaxAmt_B" ezfName="xxTotTaxAmt_B" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="xxTotAmt_B" ezfName="xxTotAmt_B" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="custMdseCd_B" ezfName="custMdseCd_B" value="WWWWWWWWW1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"18\" tabindex=\"-1\""/></td>
																							<td><ezf:inputCheckBox name="supdLockFlg_B" ezfName="supdLockFlg_B" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"supdLockFlg_B#{EZF_ROW_NUMBER}\""/></td>
																							<td>
																								<ezf:select name="dsOrdLineCatgCd_B" ezfName="dsOrdLineCatgCd_B" ezfHyo="B" ezfArrayNo="0" ezfCodeName="dsOrdLineCatgCd_PL" ezfDispName="dsOrdLineCatgDescTxt_PL" otherAttr=" style=\"width:192px;\""/>
																							</td>
																							<td>
																								<ezf:select name="ordLineSrcCd_B" ezfName="ordLineSrcCd_B" ezfHyo="B" ezfArrayNo="0" ezfCodeName="ordLineSrcCd_PL" ezfDispName="ordLineSrcNm_PL" otherAttr=" style=\"width:192px;\""/>
																							</td>
																							<td>
																								<table border="0" cellpadding="1" cellspacing="0">
																									<tr style="padding:0;">
																										<td><ezf:inputText name="rtlWhNm_B" ezfName="rtlWhNm_B" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_ChangeWH" otherAttr=" size=\"18\" maxlength=\"30\" ezffocusout=\"OnBlur_ChangeWH\""/></td>
																										<td><ezf:anchor name="rtlWhNm_LK" ezfName="rtlWhNm_LK" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" ezfanchortext=\"\"">W</ezf:anchor></td>
																									</tr>
																								</table>
																							</td>
																							<td>
																								<table border="0" cellpadding="1" cellspacing="0">
																									<tr style="padding:0;">
																										<td><ezf:inputText name="rtlSwhNm_B" ezfName="rtlSwhNm_B" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/></td>
																										<td><ezf:anchor name="rtlSwhNm_LK" ezfName="rtlSwhNm_LK" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWarehouse" otherAttr=" ezfanchortext=\"\"">S</ezf:anchor></td>
																									</tr>
																								</table>
																							</td>
																							<td><ezf:inputText name="ordQty_B" ezfName="ordQty_B" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"11\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="splyQuoteStsDescTxt_B" ezfName="splyQuoteStsDescTxt_B" value="WWWWWWWWW1WWWWW" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" tabindex=\"-1\""/></td>
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
												S21TableUI.initialize("parentBoxB", "BHEAD", "B", -1, false, true);
											</script>
											<br>
										</div>
									</c:when>

									<%-- ######################################## Comments Tab ######################################## --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Comments'}">
										<script type="text/javascript">
											document.getElementById( "Customer").className = "pTab_OFF";
											document.getElementById( "Delivery").className = "pTab_OFF";
											document.getElementById( "Items").className = "pTab_OFF";
											document.getElementById( "Comments").className = "pTab_ON";
											document.getElementById( "Additional").className = "pTab_OFF";
											<%-- START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] --%>
											document.getElementById( "OrderHistory").className = "pTab_OFF";
											<%-- END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] --%>
										</script>
										<div class="pTab_BODY_In">
											<br>
											<div id="TBL" style="overflow-y:scroll; overflow-x:none; height:350; width:700; word-break: break-all;">
												<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
													<col width="5">
													<col width="610">
													<tr>
														<td>&nbsp;</td>
														<td>
															<fieldset style="border:1px solid #808080;">
																<legend style="font-size:12px;">Quote Comments</legend>
																<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																	<tr>
																		<td><ezf:textArea name="quotePrintCmntTxt" ezfName="quotePrintCmntTxt" otherAttr=" rows=\"6\" cols=\"80\""/></td>
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
																<legend style="font-size:12px;">Order Comments</legend>
																<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
																	<tr>
																		<td><ezf:textArea name="ordPrintCmntTxt" ezfName="ordPrintCmntTxt" otherAttr=" rows=\"6\" cols=\"80\""/></td>
																	</tr>
																</table>
															</fieldset>
														</td>
													</tr>
												</table>
												<br>
												<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
													<col width="595">
													<tr>
														<td class="stab" align="right">Print on Confirmation</td>
														<td><ezf:inputCheckBox name="shpgCmntPrintCd" ezfName="shpgCmntPrintCd" value="Y" otherAttr=" tabindex=\"1\""/></td>
													</tr>
												</table>
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
																		<td><ezf:textArea name="shpgCmntTxt" ezfName="shpgCmntTxt" otherAttr=" rows=\"6\" cols=\"80\""/></td>
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
																		<td><ezf:textArea name="splyQuoteCmntTxt" ezfName="splyQuoteCmntTxt" otherAttr=" rows=\"6\" cols=\"80\""/></td>
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
																		<td><ezf:textArea name="invCmntTxt" ezfName="invCmntTxt" otherAttr=" rows=\"6\" cols=\"80\""/></td>
																	</tr>
																</table>
															</fieldset>
														</td>
													</tr>
												</table>
												<br><br>
											</div>
										</div>
									</c:when>

									<%-- ######################################## Additional Data Tab ######################################## --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Additional'}">
										<script type="text/javascript">
											document.getElementById( "Customer").className = "pTab_OFF";
											document.getElementById( "Delivery").className = "pTab_OFF";
											document.getElementById( "Items").className = "pTab_OFF";
											document.getElementById( "Comments").className = "pTab_OFF";
											document.getElementById( "Additional").className = "pTab_ON";
											<%-- START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] --%>
											document.getElementById( "OrderHistory").className = "pTab_OFF";
											<%-- END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] --%>
										</script>
										<div class="pTab_BODY_In">
											<br>
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<col width="5">
												<col width="">
												<tr>
													<td></td>
													<td>
														<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
															<col width="110">
															<col width="240">
															<col width="68">
															<col width="158">
															<col width="226">
															<col width="116">
															<col width="">
															<tr height="22">
																<td class="stab">Quote Created By</td>
																<td><ezf:inputText name="xxPsnNm_SV" ezfName="xxPsnNm_SV" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\""/></td>
																<td class="stab">Reference 1</td>
																<td><ezf:inputText name="firstBllgAttrbNm" ezfName="firstBllgAttrbNm" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"21\" maxlength=\"50\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="firstBllgAttrbValTxt" ezfName="firstBllgAttrbValTxt" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"28\" maxlength=\"50\" ezftoupper=\"\""/></td>
																<td class="stab"><ezf:anchor name="prcContrNum_LK" ezfName="prcContrNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_AssnProgram" otherAttr=" id=\"prcContrNum_LK\" ezfanchortext=\"\"">Association Program</ezf:anchor></td>
																<td><ezf:inputText name="prcContrNum" ezfName="prcContrNum" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
															</tr>
															<tr height="22">
																<td class="stab">Quote Created Date</td>
																<td><ezf:inputText name="xxTsDsp19Txt_SV" ezfName="xxTsDsp19Txt_SV" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"30\" maxlength=\"17\" tabindex=\"-1\""/></td>
																<td class="stab">Reference 2</td>
																<td><ezf:inputText name="scdBllgAttrbNm" ezfName="scdBllgAttrbNm" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"21\" maxlength=\"50\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="scdBllgAttrbValTxt" ezfName="scdBllgAttrbValTxt" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"28\" maxlength=\"50\" ezftoupper=\"\""/></td>
																<td class="stab">Classification</td>
																<td><ezf:inputText name="dsAcctClsDescTxt" ezfName="dsAcctClsDescTxt" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" maxlength=\"50\" tabindex=\"-1\""/></td>
															</tr>
															<tr height="22">
																<td class="stab">Order Submitted By</td>
																<td><ezf:inputText name="xxPsnNm_SB" ezfName="xxPsnNm_SB" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\""/></td>
																<td class="stab">Reference 3</td>
																<td><ezf:inputText name="thirdBllgAttrbNm" ezfName="thirdBllgAttrbNm" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"21\" maxlength=\"50\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="thirdBllgAttrbValTxt" ezfName="thirdBllgAttrbValTxt" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"28\" maxlength=\"50\" ezftoupper=\"\""/></td>
																<td class="stab">GL Acct Classification</td>
																<td><ezf:inputText name="xxScrItem54Txt_GL" ezfName="xxScrItem54Txt_GL" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" maxlength=\"50\" tabindex=\"-1\""/></td>
															</tr>
															<tr height="22">
																<td class="stab">Order Submitted Date</td>
																<td><ezf:inputText name="xxTsDsp19Txt_SB" ezfName="xxTsDsp19Txt_SB" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"30\" maxlength=\"17\" tabindex=\"-1\""/></td>
																<td class="stab">Reference 4</td>
																<td><ezf:inputText name="frthBllgAttrbNm" ezfName="frthBllgAttrbNm" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"21\" maxlength=\"50\" tabindex=\"-1\""/></td>
																<td colspan="3"><ezf:inputText name="frthBllgAttrbValTxt" ezfName="frthBllgAttrbValTxt" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"28\" maxlength=\"50\" ezftoupper=\"\""/></td>
															</tr>
															<tr height="22">
																<td class="stab">Confirmation Output</td>
																<td>
																	<ezf:select name="splyQuoteRptOtptLogPk" ezfName="splyQuoteRptOtptLogPk" ezfCodeName="splyQuoteRptOtptLogPk_PL" ezfDispName="xxDtlNm_PL" otherAttr=" style=\"width:215px;\""/>
																</td>
																<td class="stab">Reference 5</td>
																<td><ezf:inputText name="fifthBllgAttrbNm" ezfName="fifthBllgAttrbNm" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"21\" maxlength=\"50\" tabindex=\"-1\""/></td>
																<td colspan="3"><ezf:inputText name="fifthBllgAttrbValTxt" ezfName="fifthBllgAttrbValTxt" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"28\" maxlength=\"50\" ezftoupper=\"\""/></td>
															</tr>
															<tr height="22">
																<td>&nbsp;</td>
																<td><ezf:inputButton name="OpenWin_ViewChangeLog" value="View Change Log" htmlClass="pBtn10" /></td>
																<td class="stab">Reference 6</td>
																<td><ezf:inputText name="sixthBllgAttrbNm" ezfName="sixthBllgAttrbNm" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"21\" maxlength=\"50\" tabindex=\"-1\""/></td>
																<td colspan="3"><ezf:inputText name="sixthBllgAttrbValTxt" ezfName="sixthBllgAttrbValTxt" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"28\" maxlength=\"50\" ezftoupper=\"\""/></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											<hr>
											<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
												<tr>
													<td>
														<div id="parentBoxC">
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
																			<div id='leftTbl' style="float:left; display:block; height:133px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
																		</div>
																		<div style="float:left">
																			<div id='rightTblHead' style="width:1072px; display:block; overflow:hidden; margin:0px;padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" id="CHEAD" width="1840px" style="margin-right:20px">
																					<col width="50" align="center">
																					<col width="130" align="center">
																					<col width="220" align="center">
																					<col width="120" align="center">
																					<col width="180" align="center">
																					<col width="100" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<tr height="24">
																						<td id="CH0" class="pClothBs colFix">Line #</td>
																						<td id="CH1" class="pClothBs">Item #</td>
																						<td id="CH2" class="pClothBs">Description</td>
																						<td id="CH3" class="pClothBs">Total Weight</td>
																						<td id="CH4" class="pClothBs">Merchandise Type</td>
																						<td id="CH5" class="pClothBs">Status</td>
																						<td id="CH6" class="pClothBs">Product Code</td>
																						<td id="CH7" class="pClothBs">Product Level 1</td>
																						<td id="CH8" class="pClothBs">Product Level 2</td>
																						<td id="CH9" class="pClothBs">Product Level 3</td>
																						<td id="CH10" class="pClothBs">Product Level 4</td>
																						<td id="CH11" class="pClothBs">Product Level 5</td>
																					</tr>
																				</table>
																			</div>
																			<div id="rightTbl" style="width:1089px; height:133px; display:block; overflow:scroll; margin:0px; padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" id="C" style="table-layout: fixed;">
																					<col width="50" align="center">
																					<col width="130" align="center">
																					<col width="220" align="center">
																					<col width="120" align="center">
																					<col width="180" align="center">
																					<col width="100" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<col width="180" align="center">
																					<ezf:row ezfHyo="C">
																						<tr>
																							<td><ezf:label name="xxLineNum_C" ezfName="xxLineNum_C" ezfHyo="C" ezfArrayNo="0" /></td>
																							<td><ezf:inputText name="mdseCd_C" ezfName="mdseCd_C" value="WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"17\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="mdseDescShortTxt_C" ezfName="mdseDescShortTxt_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"30\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="xxTotUnitNetWt_C" ezfName="xxTotUnitNetWt_C" value="1234567890" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"15\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="coaMdseTpDescTxt_C" ezfName="coaMdseTpDescTxt_C" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"24\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="splyQuoteStsDescTxt_C" ezfName="splyQuoteStsDescTxt_C" value="WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="coaProdDescTxt_C" ezfName="coaProdDescTxt_C" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"24\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="zerothProdCtrlNm_C" ezfName="zerothProdCtrlNm_C" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"24\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="firstProdCtrlNm_C" ezfName="firstProdCtrlNm_C" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"24\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="scdProdCtrlNm_C" ezfName="scdProdCtrlNm_C" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"24\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="thirdProdCtrlNm_C" ezfName="thirdProdCtrlNm_C" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"24\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="frthProdCtrlNm_C" ezfName="frthProdCtrlNm_C" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"24\" tabindex=\"-1\""/></td>
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
												S21TableUI.initialize("parentBoxC", "CHEAD", "C", -1, false, false);
											</script>
											<br>
										</div>
									</c:when>

									<%-- START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] --%>
									<%-- ######################################## Order History ######################################## --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'OrderHistory'}">
										<script type="text/javascript">
											document.getElementById( "Customer").className = "pTab_OFF";
											document.getElementById( "Delivery").className = "pTab_OFF";
											document.getElementById( "Items").className = "pTab_OFF";
											document.getElementById( "Comments").className = "pTab_OFF";
											document.getElementById( "Additional").className = "pTab_OFF";
											document.getElementById( "OrderHistory").className = "pTab_ON";
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
																			<div id='leftTbl' style="float:left; display:block; height:280px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
																		</div>
																		<div style="float:left">
																			<div id='rightTblHead' style="width:1073px; display:block; overflow:hidden; margin:0px;padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" id="HHEAD" width="694px" style="margin-right:20px">
																					<col width="80" align="center">
																					<col width="50" align="center">
																					<col width="124" align="center">
																					<col width="130" align="center">
																					<col width="220" align="center">
																					<col width="90" align="center">

																					<tr height="24">
																						<td id="HH0" class="pClothBs">Order #</td>
																						<td id="HH1" class="pClothBs colFix">Line #</td>
																						<td id="HH2" class="pClothBs">Item #</td>
																						<td id="HH3" class="pClothBs">Manufacture #</td>
																						<td id="HH4" class="pClothBs">Description</td>
																						<td id="HH5" class="pClothBs">Qty</td>
																					</tr>
																				</table>
																			</div>
																			<div id="rightTbl" style="width:1090px; height:280px; display:block; overflow:scroll; margin:0px; padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" id="H" style="table-layout: fixed;">
																					<col width="80" align="center">
																					<col width="50" align="center">
																					<col width="124" align="center">
																					<col width="130" align="center">
																					<col width="220" align="center">
																					<col width="90" align="center">

																					<ezf:row ezfHyo="H">
																						<tr>
																							<td>
																								<ezf:anchor name="cpoOrdNum_HL" ezfName="cpoOrdNum_HL" ezfHyo="H" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderEntry" otherAttr=" id=\"cpoOrdNum_HL\" ezfanchortext=\"\"">
																									<ezf:label name="cpoOrdNum_H" ezfName="cpoOrdNum_H" ezfHyo="H" ezfArrayNo="0" />
																								</ezf:anchor>
																							</td>
																							<td><ezf:label name="xxLineNum_H" ezfName="xxLineNum_H" ezfHyo="H" ezfArrayNo="0" /></td>
																							<td><ezf:inputText name="mdseCd_H" ezfName="mdseCd_H" value="WWWWWWWWW1" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"16\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="mnfItemCd_H" ezfName="mnfItemCd_H" value="WWWWWWWWW1" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"17\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="mdseDescShortTxt_H" ezfName="mdseDescShortTxt_H" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"30\" tabindex=\"-1\""/></td>
																							<td><ezf:inputText name="ordCustUomQty_H" ezfName="ordCustUomQty_H" value="1234567890" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"11\" tabindex=\"-1\""/></td>

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
									<%-- END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD] --%>

								</c:choose>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<div style="display:none;">
	<ezf:inputButton name="btn12" value="btn12" otherAttr=" id=\"btn12\""/>
</div>

<script type="text/javascript">

setKeyDownHandler();

function setKeyDownHandler() {

	if( window.addEventListener ) {
	    window.addEventListener("keyup", emulateFuncKeyDown, false);
	} else if( document.attachEvent ) {
	    document.attachEvent("onkeyup", emulateFuncKeyDown);
	} else {
	    document.onkeyup = emulateFuncKeyDown;
	}
}

function emulateFuncKeyDown() {

	var code = event.keyCode;
	//alert("keyCode:["+event.keyCode+"]");

	if(event.keyCode == 123) {
		event.keyCode = null;
		event.returnValue = false;
	}

	switch(code) {
	// F12
	case 123:
		//sendServer("Line_Add");
		emulateOnClickEvent("btn12");
		break;
	default:
		break;
	}
	return;
}

function emulateOnClickEvent(elementId) {

	var elem = document.getElementById(elementId);
	if( /*@cc_on ! @*/ false ) {
		elem.fireEvent("onclick");
	} else {
		var evt = document.createEvent("MouseEvents");
		evt.initEvent("click", false, true);
		elem.dispatchEvent(evt);
	}
}
</script>

<%-- **** Task specific area ends here **** --%>
