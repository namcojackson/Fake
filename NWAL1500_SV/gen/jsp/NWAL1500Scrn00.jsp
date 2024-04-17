<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180801103539 --%>
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
			<input type="hidden" name="pageID" value="NWAL1500Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Order Entry">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			
<%-- ######################################## HEADER ######################################## --%>
<%--				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Order Entry" class="pTab_ON"><a href="#">Order Entry</a></li>
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
 --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

<%-- ##### BODY(HEADER) ##### --%>
							<div class="pTab_BODY">

<%-- ##### BODY(TAB) ##### --%>
								<div class="pTab_HEAD">
									<ul>
										<li id="Header" title="Header" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Header" otherAttr=" tabindex=\"-1\"">Header</ezf:anchor></li>
										<li id="Addl_Header" title="Addl_Header" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="TAB_Addl_Header" otherAttr=" tabindex=\"-1\"">Addl Header</ezf:anchor></li>
										<li id="Line_Config" title="Line_Config" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="TAB_Line_Config" otherAttr=" tabindex=\"-1\"">Line Config</ezf:anchor></li>
										<li id="RMA" title="RMA" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_04" ezfEmulateBtn="1" ezfGuard="TAB_RMA" otherAttr=" tabindex=\"-1\"">RMA</ezf:anchor></li>
									</ul>
								</div>

<%-- ##### TAB(Main) ##### --%>
								<c:choose>
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='Header'}">
										<script type="text/javascript">
											document.getElementById( "Header" ).className="pTab_ON";
											document.getElementById( "Addl_Header" ).className="pTab_OFF";
											document.getElementById( "Line_Config" ).className="pTab_OFF";
											document.getElementById( "RMA" ).className="pTab_OFF";
										</script>
								<div class="pTab_BODY_In">
								<table>
									<col valign="top">
									<col valign="top">
									<col valign="top">
									<col valign="top">
									<tr>
										<td>
										
											<fieldset>
												<legend style="font-size:12px;">Order Information</legend>
												<table style="table-layout:fixed;">
													<col width="80">
													<col width="210">
													<col width="10">
													<col width="75">
													<col width="204">
													<tr>
														<td class="stab">Order Number</td>
														<td>
															<ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="100123" otherAttr=" size=\"8\" maxlength=\"8\" tabindex=\"1\" ezftoupper=\"\""/>
															<ezf:inputButton name="Order_Search" value="Search" htmlClass="pBtn5" otherAttr=" tabindex=\"1\""/>
														</td>
														<td></td>
														<td class="stab">Order Status</td>
														<td class="pOut"><ezf:inputText name="ordHdrStsDescTxt" ezfName="ordHdrStsDescTxt" value="ENTERED" otherAttr=" size=\"28\" maxlength=\"50\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													</tr>
													<tr>
														<td class="stab"><ezf:anchor name="dsOrdCatgDescTxt_LK" ezfName="dsOrdCatgDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderCategory" otherAttr=" id=\"dsOrdCatgDescTxt_LK\" tabindex=\"1\" ezfanchortext=\"\"">Category (*)</ezf:anchor></td>
														<td><ezf:inputText name="dsOrdCatgDescTxt" ezfName="dsOrdCatgDescTxt" value="LEASE, CSA" otherEvent1="OnBlur_DeriveFromCategory" otherAttr=" size=\"28\" maxlength=\"50\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromCategory\""/></td>
														<td></td>
														<td class="stab"><ezf:anchor name="dsOrdTpDescTxt_LK" ezfName="dsOrdTpDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderReason" otherAttr=" id=\"dsOrdTpDescTxt_LK\" tabindex=\"1\" ezfanchortext=\"\"">Reason (*)</ezf:anchor></td>
														<td><ezf:inputText name="dsOrdTpDescTxt" ezfName="dsOrdTpDescTxt" value="ESS-LEASE-CFS" otherEvent1="OnBlur_DeriveFromReason" otherAttr=" size=\"28\" maxlength=\"50\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromReason\""/></td>
													</tr>
													<tr>
														<td class="stab">Sub Reason</td>
														<td>
															<ezf:select name="dsOrdRsnCd" ezfName="dsOrdRsnCd" ezfBlank="1" ezfCodeName="dsOrdRsnCd_CD" ezfDispName="dsOrdRsnDescTxt_NM" otherAttr=" style=\"width: 202px\" tabindex=\"1\""/>
														</td>
														<td></td>
														<td class="stab">Order Date</td>
														<td>
															<table border="0" cellpadding="1" cellspacing="0">
																<tr style="padding:0;">
																	<td><ezf:inputText name="ordDt_DP" ezfName="ordDt_DP" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1\""/></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</fieldset>
											
										</td>
										<td>

											<fieldset>
												<legend style="font-size:12px;">Order Pricing Summary</legend>
												<table border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td valign="top">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																<col align="center" width="90">
																<col align="right" width="90">
																<tr height="18">
																	<td class="pClothBs">Sub Total</td>
																	<td><ezf:label name="xxTotBaseAmt" ezfName="xxTotBaseAmt" /></td>
																</tr>
																<tr height="18">
																	<td class="pClothBs">Charges</td>
																	<td><ezf:label name="xxTotFrtAmt" ezfName="xxTotFrtAmt" /></td>
																</tr>
																<tr height="18">
																	<td class="pClothBs">Tax</td>
																	<td><ezf:label name="xxTotTaxAmt" ezfName="xxTotTaxAmt" /></td>
																</tr>
																<tr height="18">
																	<td class="pClothBs">Total</td>
																	<td><ezf:label name="xxTotAmt" ezfName="xxTotAmt" /></td>
																</tr>
															</table>
														</td>
														<td>&nbsp;</td>
														<td valign="top">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																<col width="90" align="center">
																<col width="90" align="right">
																<tr>
																	<td class="pClothBs">Lines</td>
																	<td><ezf:label name="xxTotBaseAmt_LN" ezfName="xxTotBaseAmt_LN" /></td>
																</tr>
																<tr>
																	<td class="pClothBs">Main w/Eq</td>
																	<td><ezf:label name="xxTotBaseAmt_MT" ezfName="xxTotBaseAmt_MT" /></td>
																</tr>
																<tr>
																	<td class="pClothBs">RMA</td>
																	<td><ezf:label name="xxTotBaseAmt_RT" ezfName="xxTotBaseAmt_RT" /></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td>&nbsp;&nbsp;
										</td>
										<td valign="bottom">
											<table>
												<tr>
													<ezf:inputButton name="Pricing" value="$" htmlClass="pBtn5" otherAttr=" tabindex=\"1\""/>
												</tr>
												<br/>
												<tr>
													<ezf:inputButton name="Book" value="Book" htmlClass="pBtn5" otherAttr=" tabindex=\"1\""/>
												</tr>
											</table>
										</td>
									</tr>
									<td>
									<hr width="1130">
									</td>
								</table>
								
								<table style="table-layout:fixed; margin-top: -5px">
									<col width="620" valign="top">
									<col width="490" valign="top">
									<tr>
										<td>

											<fieldset>
												<legend style="font-size:12px;">Customer Information</legend>

												<fieldset>
													<legend style="font-size:12px;">Bill To</legend>
													<table style="table-layout:fixed;" border="0">
														<col width="48" valign="top">
														<col width="330" valign="top">
														<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
														<!-- <col width="60" valign="top"> -->
														<!-- <col width="140" valign="top"> -->
														<col width="85" valign="top">
														<col width="115" valign="top">
														<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
														<tr>
															<td class="stab"><ezf:anchor name="billToCustAcctNm_LK" ezfName="billToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"billToCustAcctNm_LK\" tabindex=\"1\" ezfanchortext=\"\"">Name (*)</ezf:anchor></td>
															<td>
																<ezf:inputText name="billToCustAcctNm" ezfName="billToCustAcctNm" value="CANON FINANCIAL SVC" otherEvent1="OnBlur_DeriveFromBillToName" otherAttr=" size=\"32\" maxlength=\"60\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromBillToName\""/>
															</td>
															<td class="stab"><ezf:anchor name="billToCustAcctCd_LK" ezfName="billToCustAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"billToCustAcctCd_LK\" tabindex=\"1\" ezfanchortext=\"\"">Number (*)</ezf:anchor></td>
															<td>
																<ezf:inputText name="billToCustAcctCd" ezfName="billToCustAcctCd" value="1002805" otherEvent1="OnBlur_DeriveFromBillToAccount" otherAttr=" size=\"12\" maxlength=\"20\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromBillToAccount\""/>
															</td>
														</tr>
														<tr>
															<td class="stab">Address</td>
															<td><ezf:textArea name="entBillToCustLocAddr" ezfName="entBillToCustLocAddr" otherAttr=" rows=\"4\" cols=\"40\" tabindex=\"1\" ezftoupper=\"\""/></td>
															<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
															<!-- <td class="stab"><a href="#" id="billToCustCd_LK" name="billToCustCd_LK" ezfName="billToCustCd_LK" tabindex="1" onclick="sendServer('OpenWin_BillTo')" ezfanchortext>Location (*)</a></td> -->
															<td class="stab"><ezf:anchor name="billToCustCd_LK" ezfName="billToCustCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"billToCustCd_LK\" tabindex=\"1\" ezfanchortext=\"\"">Bill To Code (*)</ezf:anchor></td>
															<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
															<td>
																<ezf:inputText name="billToCustCd" ezfName="billToCustCd" value="5819" otherEvent1="OnBlur_DeriveFromBillToLocation" otherAttr=" size=\"12\" maxlength=\"20\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromBillToLocation\""/>
															</td>
														</tr>
													</table>
												</fieldset>

												<fieldset>
													<legend style="font-size:12px;">Ship To</legend>
													<table style="table-layout:fixed;" border="0">
														<col width="48" valign="top">
														<col width="330" valign="top">
														<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
														<!-- <col width="60" valign="top"> -->
														<!-- <col width="140" valign="top"> -->
														<col width="85" valign="top">
														<col width="115" valign="top">
														<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
														<tr>
															<td class="stab"><ezf:anchor name="shipToCustAcctNm_LK" ezfName="shipToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"shipToCustAcctNm_LK\" tabindex=\"1\" ezfanchortext=\"\"">Name (*)</ezf:anchor></td>
															<td>
																<ezf:inputText name="shipToCustAcctNm" ezfName="shipToCustAcctNm" value="CANON BULLS" otherEvent1="OnBlur_DeriveFromShipToName" otherAttr=" size=\"32\" maxlength=\"60\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromShipToName\""/>
																&nbsp;DS&nbsp;
																<ezf:anchor name="dropShipFlg_LK" ezfName="dropShipFlg_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToChange" htmlClass="pOut" otherAttr=" id=\"dropShipFlg_LK\" tabindex=\"1\" ezfanchortext=\"\""><ezf:label name="dropShipFlg" ezfName="dropShipFlg" /></ezf:anchor>
															</td>
															<td class="stab"><ezf:anchor name="shipToCustAcctCd_LK" ezfName="shipToCustAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"shipToCustAcctCd_LK\" tabindex=\"1\" ezfanchortext=\"\"">Number (*)</ezf:anchor></td>
															<td>
																<ezf:inputText name="shipToCustAcctCd" ezfName="shipToCustAcctCd" value="1471766" otherEvent1="OnBlur_DeriveFromShipToAccount" otherAttr=" size=\"12\" maxlength=\"20\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromShipToAccount\""/>
															</td>
														</tr>
														<tr>
															<td class="stab">DS Name</td>
															<td>
																<ezf:inputText name="addShipToLocNm" ezfName="addShipToLocNm" value="1002805" otherAttr=" size=\"42\" maxlength=\"90\" tabindex=\"1\""/>
															</td>
															<td class="stab"><ezf:anchor name="shipToCustCd_LK" ezfName="shipToCustCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"shipToCustCd_LK\" tabindex=\"1\" ezfanchortext=\"\"">Ship To Code (*)</ezf:anchor></td>
															<td>
																<ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" value="2014164" otherEvent1="OnBlur_DeriveFromShipToLocation" otherAttr=" size=\"12\" maxlength=\"20\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromShipToLocation\""/>
															</td>
														</tr>
														<tr>
															<td class="stab">Address</td>
															<td><ezf:textArea name="entShipToCustLocAddr" ezfName="entShipToCustLocAddr" otherAttr=" rows=\"5\" cols=\"40\" tabindex=\"1\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">Attention</td>
															<td>
																<ezf:inputText name="sellToFirstRefCmntTxt" ezfName="sellToFirstRefCmntTxt" value="1002805" otherAttr=" size=\"42\" maxlength=\"90\" tabindex=\"1\""/>
															</td>
														</tr>
													</table>
												</fieldset>

												<fieldset>
													<legend style="font-size:12px;">Sold To</legend>
													<table style="table-layout:fixed;">
														<col width="48" valign="top">
														<col width="330" valign="top">
														<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
														<!-- <col width="60" valign="top"> -->
														<!-- <col width="140" valign="top"> -->
														<col width="85" valign="top">
														<col width="115" valign="top">
														<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
														<tr>
															<td class="stab"><ezf:anchor name="soldToCustAcctNm_LK" ezfName="soldToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" otherAttr=" id=\"soldToCustAcctNm_LK\" tabindex=\"1\" ezfanchortext=\"\"">Name (*)</ezf:anchor></td>
															<td>
																<ezf:inputText name="soldToCustAcctNm" ezfName="soldToCustAcctNm" value="CANON BULLS" otherEvent1="OnBlur_DeriveFromSoldToName" otherAttr=" size=\"32\" maxlength=\"60\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromSoldToName\""/>
															</td>
															<td class="stab"><ezf:anchor name="sellToCustCd_LK" ezfName="sellToCustCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" otherAttr=" id=\"sellToCustCd_LK\" tabindex=\"1\" ezfanchortext=\"\"">Number (*)</ezf:anchor></td>
															<td>
																<ezf:inputText name="sellToCustCd" ezfName="sellToCustCd" value="1471766" otherEvent1="OnBlur_DeriveFromSoldToAccount" otherAttr=" size=\"12\" maxlength=\"20\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromSoldToAccount\""/>
															</td>
														</tr>
														<tr>
															<td class="stab">Address</td>
															<td><ezf:textArea name="entSoldToCustLocAddr" ezfName="entSoldToCustLocAddr" otherAttr=" rows=\"4\" cols=\"40\" tabindex=\"1\" ezftoupper=\"\""/></td>
															<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
															<!-- <td class="stab"><a href="#" id="soldToCustLocCd_LK" name="soldToCustLocCd_LK" ezfName="soldToCustLocCd_LK" tabindex="1" onclick="sendServer('OpenWin_SoldTo')" ezfanchortext>Location (*)</a></td> -->
															<td class="stab"><ezf:anchor name="soldToCustLocCd_LK" ezfName="soldToCustLocCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" otherAttr=" id=\"soldToCustLocCd_LK\" tabindex=\"1\" ezfanchortext=\"\"">Sold To Code (*)</ezf:anchor></td>
															<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
															<td>
																<ezf:inputText name="soldToCustLocCd" ezfName="soldToCustLocCd" value="555555" otherEvent1="OnBlur_DeriveFromSoldToLocation" otherAttr=" size=\"12\" maxlength=\"20\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromSoldToLocation\""/>
															</td>
														</tr>
													</table>
												</fieldset>
											</fieldset>
										</td>
										<td>

											<fieldset>
												<legend style="font-size:12px;">Header Details</legend>
												<table style="table-layout:fixed;" border="0">
													<col width="100">
													<col width="130">
													<col width="50">
													<col width="65">
													<col width="120"><!--122 -->
													<tr>
														<td class="stab">Negotiated Deal</td>
														<td><ezf:inputText name="negoDealAmt" ezfName="negoDealAmt" value="$100.00" otherAttr=" size=\"16\" maxlength=\"20\" tabindex=\"1\" ezftoupper=\"\""/></td>
														<td colspan="3">
															<table style="table-layout:fixed;">
																<col width="60">
																<col width="120">
																<col width="40">
																<tr>
																	<td class="stab">Invoiced</td>
																	<td class="pOut" align="right"><ezf:label name="xxTotInvApplyAmt" ezfName="xxTotInvApplyAmt" /></td>
																	<td class="pOut"><ezf:label name="xxTotInvPct" ezfName="xxTotInvPct" /></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td class="stab" valign="top"><ezf:anchor name="slsRepTocNm_LK" ezfName="slsRepTocNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Salesrep" otherAttr=" id=\"slsRepTocNm_LK\" tabindex=\"1\" ezfanchortext=\"\"">Salesrep (*)</ezf:anchor></td>
														<td colspan="3">
															<ezf:inputText name="slsRepTocNm" ezfName="slsRepTocNm" value="DOE,JOHN" otherEvent1="OnBlur_DeriveFromSalesRepName" otherAttr=" size=\"32\" maxlength=\"50\" tabindex=\"1\" ezfnoupperfocusout=\"OnBlur_DeriveFromSalesRepName\""/>
														</td>
														<td class="stab" valign="top" >
														<ezf:anchor name="psnNum_LK" ezfName="psnNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Salesrep" otherAttr=" id=\"psnNum_LK\" tabindex=\"1\" ezfanchortext=\"\"">Number (*)</ezf:anchor>
														<ezf:inputText name="psnNum" ezfName="psnNum" value="D012345" otherEvent1="OnBlur_DeriveFromSalesRepCode" otherAttr=" size=\"8\" maxlength=\"50\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromSalesRepCode\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Salesrep Branch</td>
														<td class="pOut" colspan="4"><ezf:inputText name="xxScrItem54Txt_CB" ezfName="xxScrItem54Txt_CB" value="CHICAGO[225]" otherAttr=" size=\"32\" maxlength=\"54\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													</tr>
													<tr>
														<td class="stab">Salesrep Bus Unit</td>
														<td class="pOut" colspan="4"><ezf:inputText name="xxScrItem54Txt_CE" ezfName="xxScrItem54Txt_CE" value="200-CORE[ESS" otherAttr=" size=\"32\" maxlength=\"54\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													</tr>
													<tr>
														<td class="stab"><ezf:anchor name="prcCatgNm_LK" ezfName="prcCatgNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PriceList" otherAttr=" id=\"prcCatgNm_LK\" tabindex=\"1\" ezfanchortext=\"\"">Price List (*)</ezf:anchor></td>
														<td colspan="4">
															<ezf:inputText name="prcCatgNm" ezfName="prcCatgNm" value="FLOOR, CSA" otherEvent1="OnBlur_DeriveFromPriceList" otherAttr=" size=\"50\" maxlength=\"250\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromPriceList\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Customer PO</td>
														<td colspan="4"><ezf:inputText name="custIssPoNum" ezfName="custIssPoNum" value="BPR TEST 1" otherAttr=" size=\"30\" maxlength=\"35\" tabindex=\"1\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">Lease Company PO</td>
														<td colspan="4"><ezf:inputText name="leaseCmpyPoNum" ezfName="leaseCmpyPoNum" value="121" otherAttr=" size=\"30\" maxlength=\"35\" tabindex=\"1\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">Customer PO Date</td>
														<td>
															<table border="0" cellpadding="1" cellspacing="0">
																<tr style="padding:0;">
																	<td><ezf:inputText name="custIssPoDt" ezfName="custIssPoDt" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1\""/></td>
																	<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('custIssPoDt', 4);" tabindex="1"></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
												<table style="table-layout:fixed;" border="0">
													<col width="100">
													<col width="130">
													<col width="65">
													<col width="35">
													<col width="30">
													<col width="105">
													<tr>	
														<td class="stab">Acquisition Number</td>
														<td ><ezf:inputText name="aquNum" ezfName="aquNum" value="50012345" otherAttr=" size=\"16\" maxlength=\"35\" tabindex=\"1\" ezftoupper=\"\""/></td>
														<td class="stab"><ezf:anchor name="ordLogTpCd_LK" ezfName="ordLogTpCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_OrdLogTp" otherAttr=" id=\"ordLogTpCd_LK\" tabindex=\"1\" ezfanchortext=\"\"">Log Type (*)</ezf:anchor></td>
														<td><ezf:inputText name="ordLogTpCd" ezfName="ordLogTpCd" value="DM" otherEvent1="OnBlur_OrdLogTpNm" otherAttr=" size=\"4\" maxlength=\"10\" tabindex=\"1\" ezffocusout=\"OnBlur_OrdLogTpNm\""/></td>
														<td class="stab">Name</td>
														<td><ezf:inputText name="ordLogTpDescTxt_NM" ezfName="ordLogTpDescTxt_NM" value="Dealer Manual" otherAttr=" size=\"14\" maxlength=\"50\" tabindex=\"1\""/></td>
													</tr>
													<tr>
														<td class="stab">Invoice Comments</td>
														<td colspan="5"><ezf:textArea name="invCmntTxt" ezfName="invCmntTxt" otherAttr=" rows=\"2\" cols=\"40\" tabindex=\"1\" ezftoupper=\"\""/></td>
													</tr>
												</table>
											</fieldset>

											<fieldset>
												<legend style="font-size:12px;">Order Source Details</legend>
												<table style="table-layout:fixed;">
													<col width="100">
													<col width="364">
													<tr>
														<td class="stab">Source Name</td>
														<td class="pOut"><ezf:inputText name="cpoSrcTpDescTxt" ezfName="cpoSrcTpDescTxt" value="(SOM) SALES ORDER MAKER" otherAttr=" size=\"28\" maxlength=\"50\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													</tr>
													<tr>
														<td class="stab">Import Creation Date</td>
														<td class="pOut"><ezf:label name="ordSrcImptTsDplyTxt" ezfName="ordSrcImptTsDplyTxt" /></td>
													</tr>
													<tr>
														<td class="stab">Source Reference</td>
														<td class="pOut"><ezf:label name="ordSrcRefNum" ezfName="ordSrcRefNum" /></td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
								</table>
								<!-- Actions -->
								<table>
									<col width="1100" valign="bottom">
									<td align="left">
										<fieldset>
											<table style="table-layout:fixed;">
												<col width="60">
												<col width="110">
												<col width="20">
												<col width="120">
												<col width="110">
												<col width="20">
												<col width="200">
												<col width="110">
												<col width="20">
												<col width="50">
												<col width="110">
												<td class="stab">Actions(<u>A</u>)</td>
												<td>
													<ezf:select name="ordEntryActCd_AC" ezfName="ordEntryActCd_AC" ezfBlank="1" ezfCodeName="ordEntryActCd_HA" ezfDispName="ordEntryActDescTxt_HA" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryAction')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"1\" accesskey=\"A\""/>
												</td>
												<td>&nbsp;</td>
												<td class="stab">Additional Information(<u>F</u>)</td>
												<td>
													<ezf:select name="ordEntryActCd_IF" ezfName="ordEntryActCd_IF" ezfBlank="1" ezfCodeName="ordEntryActCd_HI" ezfDispName="ordEntryActDescTxt_HI" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryActionAddlInfo')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"1\" accesskey=\"F\""/>
												</td>
												<td>&nbsp;</td>
												<td class="stab">Delivery/Install/Site Survey/Contacts(<u>C</u>)</td>
												<td>
													<ezf:select name="ordEntryActCd_DL" ezfName="ordEntryActCd_DL" ezfBlank="1" ezfCodeName="ordEntryActCd_HD" ezfDispName="ordEntryActDescTxt_HD" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryActionDelivery')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"1\" accesskey=\"C\""/>
												</td>
												<td>&nbsp;</td>
												<td class="stab">Tools(<u>T</u>)</td>
												<td>
													<ezf:select name="ordEntryActCd_TO" ezfName="ordEntryActCd_TO" ezfBlank="1" ezfCodeName="ordEntryActCd_HT" ezfDispName="ordEntryActDescTxt_HT" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryActionTool')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"1\" accesskey=\"T\""/>
												</td>
											</table>
										</fieldset>
									</td>
								</table>
								</div>
								</c:when>
								</c:choose>

<%-- ##### TAB(addl Header) ##### --%>
								<c:choose>
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='Addl_Header'}">
										<script type="text/javascript">
											document.getElementById( "Header" ).className="pTab_OFF";
											document.getElementById( "Addl_Header" ).className="pTab_ON";
											document.getElementById( "Line_Config" ).className="pTab_OFF";
											document.getElementById( "RMA" ).className="pTab_OFF";
										</script>
									<div class="pTab_BODY_In">
									<table>
										<col valign="top">
										<col valign="top">
										<col valign="top">
										<col valign="top">
										<tr>
											<td>

												<fieldset>
													<legend style="font-size:12px;">Order Information</legend>
													<table style="table-layout:fixed;">
														<col width="80">
														<col width="210">
														<col width="10">
														<col width="75">
														<col width="204">
														<tr>
															<td class="stab">Order Number</td>
															<td>
																<ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="100123" otherAttr=" size=\"8\" maxlength=\"8\" tabindex=\"1\" ezftoupper=\"\""/>
																<ezf:inputButton name="Order_Search" value="Search" htmlClass="pBtn5" otherAttr=" tabindex=\"1\""/>
															</td>
															<td></td>
															<td class="stab">Order Status</td>
															<td class="pOut"><ezf:inputText name="ordHdrStsDescTxt" ezfName="ordHdrStsDescTxt" value="ENTERED" otherAttr=" size=\"28\" maxlength=\"50\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														</tr>
														<tr>
															<td class="stab"><ezf:anchor name="dsOrdCatgDescTxt_LK" ezfName="dsOrdCatgDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderCategory" otherAttr=" id=\"dsOrdCatgDescTxt_LK\" tabindex=\"1\" ezfanchortext=\"\"">Category (*)</ezf:anchor></td>
															<td><ezf:inputText name="dsOrdCatgDescTxt" ezfName="dsOrdCatgDescTxt" value="LEASE, CSA" otherEvent1="OnBlur_DeriveFromCategory" otherAttr=" size=\"28\" maxlength=\"50\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromCategory\""/></td>
															<td></td>
															<td class="stab"><ezf:anchor name="dsOrdTpDescTxt_LK" ezfName="dsOrdTpDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderReason" otherAttr=" id=\"dsOrdTpDescTxt_LK\" tabindex=\"1\" ezfanchortext=\"\"">Reason (*)</ezf:anchor></td>
															<td><ezf:inputText name="dsOrdTpDescTxt" ezfName="dsOrdTpDescTxt" value="ESS-LEASE-CFS" otherEvent1="OnBlur_DeriveFromReason" otherAttr=" size=\"28\" maxlength=\"50\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromReason\""/></td>
														</tr>
														<tr>
															<td class="stab">Sub Reason</td>
															<td>
																<ezf:select name="dsOrdRsnCd" ezfName="dsOrdRsnCd" ezfBlank="1" ezfCodeName="dsOrdRsnCd_CD" ezfDispName="dsOrdRsnDescTxt_NM" otherAttr=" style=\"width: 202px\" tabindex=\"1\""/>
															</td>
															<td></td>
															<td class="stab">Order Date</td>
															<td>
																<table border="0" cellpadding="1" cellspacing="0">
																	<tr style="padding:0;">
																		<td><ezf:inputText name="ordDt_DP" ezfName="ordDt_DP" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1\""/></td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</fieldset>

											</td>
											<td>

												<fieldset>
													<legend style="font-size:12px;">Order Pricing Summary</legend>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td valign="top">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col align="center" width="90">
																	<col align="right" width="90">
																	<tr height="18">
																		<td class="pClothBs">Sub Total</td>
																		<td><ezf:label name="xxTotBaseAmt" ezfName="xxTotBaseAmt" /></td>
																	</tr>
																	<tr height="18">
																		<td class="pClothBs">Charges</td>
																		<td><ezf:label name="xxTotFrtAmt" ezfName="xxTotFrtAmt" /></td>
																	</tr>
																	<tr height="18">
																		<td class="pClothBs">Tax</td>
																		<td><ezf:label name="xxTotTaxAmt" ezfName="xxTotTaxAmt" /></td>
																	</tr>
																	<tr height="18">
																		<td class="pClothBs">Total</td>
																		<td><ezf:label name="xxTotAmt" ezfName="xxTotAmt" /></td>
																	</tr>
																</table>
															</td>
															<td>&nbsp;</td>
															<td valign="top">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col width="90" align="center">
																	<col width="90" align="right">
																	<tr>
																		<td class="pClothBs">Lines</td>
																		<td><ezf:label name="xxTotBaseAmt_LN" ezfName="xxTotBaseAmt_LN" /></td>
																	</tr>
																	<tr>
																		<td class="pClothBs">Main w/Eq</td>
																		<td><ezf:label name="xxTotBaseAmt_MT" ezfName="xxTotBaseAmt_MT" /></td>
																	</tr>
																	<tr>
																		<td class="pClothBs">RMA</td>
																		<td><ezf:label name="xxTotBaseAmt_RT" ezfName="xxTotBaseAmt_RT" /></td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</fieldset>

											</td>
											<td>&nbsp;&nbsp;
											</td>
											<td valign="bottom">
												<table>
													<tr>
														<ezf:inputButton name="Book" value="Book" htmlClass="pBtn5" otherAttr=" tabindex=\"1\""/>
													</tr>
												</table>
											</td>
										</tr>
										<td>
										<hr width="1130">
										</td>
									</table>
									
									<table style="table-layout:fixed; margin-top: -5px" height="380">
										<col width="620" valign="top">
										<col width="490" valign="top">
										<tr>
											<td>

												<fieldset>
													<legend style="font-size:12px;">Freight Information</legend>
													<table style="table-layout:fixed;">
														<col width="100">
														<col width="250">
														<col width="100">
														<col width="150">
														<tr>
															<td class="stab"><ezf:anchor name="frtCondDescTxt_LK" ezfName="frtCondDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_FreightTerms" otherAttr=" id=\"frtCondDescTxt_LK\" tabindex=\"1\" ezfanchortext=\"\"">Freight Terms (*)</ezf:anchor></td>
															<td>
																<ezf:inputText name="frtCondDescTxt" ezfName="frtCondDescTxt" value="ESS-SUPPLIES-STANDARD" otherEvent1="OnBlur_DeriveFromFreightTerms" otherAttr=" size=\"30\" maxlength=\"50\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromFreightTerms\""/>
															</td>
															<td class="stab">Service Level</td>
															<td><ezf:select name="shpgSvcLvlCd" ezfName="shpgSvcLvlCd" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_CD" ezfDispName="shpgSvcLvlDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_ShpgSvcLvlCd')\"" otherAttr=" style=\"width: 145px\" tabindex=\"1\""/></td>
														</tr>
														<tr>
															<td class="stab"><ezf:anchor name="carrSvcLvlDescTxt_LK" ezfName="carrSvcLvlDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CarrierServiceLevel" otherAttr=" id=\"carrSvcLvlDescTxt_LK\" tabindex=\"1\" ezfanchortext=\"\"">Carrier Service Level (*)</ezf:anchor></td>
															<td>
																<ezf:inputText name="carrSvcLvlDescTxt" ezfName="carrSvcLvlDescTxt" value="FedEx PR1 OVRNT" otherEvent1="OnBlur_DeriveFromCarrierServiceLevel" otherAttr=" size=\"30\" maxlength=\"50\" tabindex=\"1\" ezfnoupperfocusout=\"OnBlur_DeriveFromCarrierServiceLevel\""/>
															</td>
															<td class="stab">Carrier Acct Num</td>
															<td><ezf:inputText name="carrAcctNum" ezfName="carrAcctNum" value="FEDEX-0001-002" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td></td>
															<td></td>
															<td class="stab">Special Handling</td>
															<td><ezf:select name="spclHdlgTpCd" ezfName="spclHdlgTpCd" ezfBlank="1" ezfCodeName="spclHdlgTpCd_CD" ezfDispName="spclHdlgTpDescTxt_NM" otherAttr=" style=\"width: 145px\" tabindex=\"1\""/></td>
														</tr>
													</table>
												</fieldset>

												<fieldset>
													<legend style="font-size:12px;">Payment Information</legend>
													<table style="table-layout:fixed;">
														<col width="100">
														<col width="250">
														<col width="115">
														<col width="130">
														<tr>
															<td class="stab"><ezf:anchor name="pmtTermCashDiscDescTxt_LK" ezfName="pmtTermCashDiscDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PaymentTerms" otherAttr=" id=\"pmtTermCashDiscDescTxt_LK\" tabindex=\"1\" ezfanchortext=\"\"">Payment Terms (*)</ezf:anchor></td>
															<td>
																<ezf:inputText name="pmtTermCashDiscDescTxt" ezfName="pmtTermCashDiscDescTxt" value="NET 30" otherEvent1="OnBlur_DeriveFromPaymentTerms" otherAttr=" size=\"30\" maxlength=\"50\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromPaymentTerms\""/>
															</td>
															
														</tr>
														<tr>
															<td class="stab">Payment Method</td>
															<td><ezf:select name="dsPmtMethCd" ezfName="dsPmtMethCd" ezfBlank="1" ezfCodeName="dsPmtMethCd_CD" ezfDispName="dsPmtMethDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_PaymentMethod')\"" otherAttr=" style=\"width: 215px\" tabindex=\"1\""/></td>
															<td class="stab">Credit Card Information</td>
															<td><ezf:inputButton name="MoveWin_CreditCard" value="Credit Card" htmlClass="pBtn6" otherAttr=" tabindex=\"1\""/></td>
														</tr>
														<tr>
															<td class="stab">Number</td>
															<td>
																<ezf:inputText name="prePmtChkNum" ezfName="prePmtChkNum" value="10001" otherAttr=" size=\"30\" maxlength=\"50\" tabindex=\"1\" ezftoupper=\"\""/>
															</td>
															<td class="stab">Prepayment Type</td>
															<td><ezf:select name="prePmtTpCd" ezfName="prePmtTpCd" ezfBlank="1" ezfCodeName="prePmtTpCd_CD" ezfDispName="prePmtTpDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_PrepaymentType')\"" otherAttr=" style=\"width: 128px\" tabindex=\"1\""/></td>
														</tr>
														<tr>
															<td class="stab">Amount</td>
															<td>
																<ezf:inputText name="prePmtAmt" ezfName="prePmtAmt" value="$1,000.00" otherAttr=" size=\"30\" maxlength=\"15\" tabindex=\"1\" ezftoupper=\"\""/>
															</td>
															<td></td>
															<td></td>
														</tr>
													</table>
												</fieldset>
												<table height = "1">
													<tr>&nbsp;</tr>
												</table>
												<fieldset>
													<table style="table-layout:fixed;">
														<col width="100">
														<col width="200">
														<col width="80">
														<col width="215">
														<tr>
															<td class="stab">Creation Date</td>
															<td class="pOut">
																<ezf:inputText name="cratTsDplyTxt" ezfName="cratTsDplyTxt" value="10/10/2016 08:08:08" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
															<td class="stab">Created By</td>
															<td class="pOut">
																<ezf:inputText name="cratUsrNm" ezfName="cratUsrNm" value="H00157(Yuzo Okumura)" otherAttr=" size=\"29\" maxlength=\"100\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
														</tr>
														<tr>
															<td class="stab">Last Update Date</td>
															<td class="pOut">
																<ezf:inputText name="updTsDplyTxt" ezfName="updTsDplyTxt" value="10/11/2016 10:09:09" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
															<td class="stab">Last Update By</td>
															<td class="pOut">
																<ezf:inputText name="updUsrNm" ezfName="updUsrNm" value="H00157(Yuzo Okumura)" otherAttr=" size=\"29\" maxlength=\"100\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
														</tr>
														<tr>
															<td class="stab">Book Req. Date</td>
															<td class="pOut">
																<ezf:inputText name="ordBookReqTsDplyTxt" ezfName="ordBookReqTsDplyTxt" value="10/11/2016 10:09:09" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
															<td class="stab">Book Req. By</td>
															<td class="pOut">
																<ezf:inputText name="ordBookReqUsrNm" ezfName="ordBookReqUsrNm" value="H00157(Yuzo Okumura)" otherAttr=" size=\"29\" maxlength=\"100\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
														</tr>
														<tr>
															<td class="stab">Booked Date</td>
															<td class="pOut">
																<ezf:inputText name="ordBookTsDplyTxt" ezfName="ordBookTsDplyTxt" value="10/10/2016 09:09:09" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
															<td></td>
															<td></td>
														</tr>
													</table>
												</fieldset>
											</td>
											<td>

												<fieldset>
													<legend style="font-size:12px;">Additional Details</legend>
													<table style="table-layout:fixed;">
														<col width="150">
														<col width="160">
														<col width="55">
														<col width="100">
														<tr>
															<td class="stab">Requested Delivery Date</td>
															<td colspan="3">
																<table border="0" cellpadding="1" cellspacing="0">
																	<tr style="padding:0;">
																		<td><ezf:inputText name="addRddDt" ezfName="addRddDt" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1\""/></td>
																		<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('addRddDt', 4);" tabindex="1"></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td class="stab"><ezf:anchor name="prcContrNm_LK" ezfName="prcContrNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_AssnProgram" otherAttr=" id=\"prcContrNm_LK\" tabindex=\"1\" ezfanchortext=\"\"">Association Program Name (*)</ezf:anchor></td>
															<td><ezf:inputText name="prcContrNm" ezfName="prcContrNm" value="XXXXXXXXXX" otherEvent1="OnBlur_DeriveFromAssnProgramName" otherAttr=" size=\"20\" maxlength=\"150\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromAssnProgramName\""/></td>
															<td class="stab"><ezf:anchor name="prcContrNum_LK" ezfName="prcContrNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_AssnProgram" otherAttr=" id=\"prcContrNum_LK\" tabindex=\"1\" ezfanchortext=\"\"">Number (*)</ezf:anchor></td>
															<td><ezf:inputText name="prcContrNum" ezfName="prcContrNum" value="XXXXXXXXXX" otherEvent1="OnBlur_DeriveFromAssnProgramNumber" otherAttr=" size=\"12\" maxlength=\"50\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromAssnProgramNumber\""/></td>
														</tr>
														<tr>
															<td class="stab"><ezf:anchor name="flPrcListDescTxt_LK" ezfName="flPrcListDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_FloorPriceList" otherAttr=" id=\"flPrcListDescTxt_LK\" tabindex=\"1\" ezfanchortext=\"\"">Floor Price List (*)</ezf:anchor></td>
															<td colspan="3">
																<ezf:inputText name="flPrcListNm" ezfName="flPrcListNm" value="FLOOR, CA" otherEvent1="OnBlur_DeriveFromFloorPriceList" otherAttr=" size=\"30\" maxlength=\"75\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromFloorPriceList\""/>
															</td>
														</tr>
														<tr>
															<td class="stab">Loan Period (Days)</td>
															<td colspan="3"><ezf:inputText name="loanPerDaysAot" ezfName="loanPerDaysAot" value="30" otherAttr=" size=\"4\" maxlength=\"4\" tabindex=\"1\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab"><ezf:anchor name="csmpContrNum_LK" ezfName="csmpContrNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CSMPNumber" otherAttr=" id=\"csmpContrNum_LK\" tabindex=\"1\" ezfanchortext=\"\"">CSMP Number</ezf:anchor></td>
															<td colspan="3">
																<ezf:inputText name="csmpContrNum" ezfName="csmpContrNum" value="12345-ABCD02" otherEvent1="OnBlur_DeriveFromCSMPNumber" otherAttr=" size=\"15\" maxlength=\"15\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromCSMPNumber\""/>
															</td>
														</tr>
														<tr>
															<td class="stab"><ezf:anchor name="dlrRefNum_LK" ezfName="dlrRefNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CSMPNumber" otherAttr=" id=\"dlrRefNum_LK\" tabindex=\"1\" ezfanchortext=\"\"">CSA Number(Dealer Ref#)</ezf:anchor></td>
															<td colspan="3">
																<ezf:inputText name="dlrRefNum" ezfName="dlrRefNum" value="xxxxxxxx10xxxxxxxx20" otherEvent1="OnBlur_DeriveFromDealerRefNumber" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" ezffocusout=\"OnBlur_DeriveFromDealerRefNumber\""/>
															</td>
														</tr>
														<tr>
															<td class="stab">Customer Signed Date</td>
															<td colspan="3">
																<table border="0" cellpadding="1" cellspacing="0">
																	<tr style="padding:0;">
																		<td><ezf:inputText name="ordSgnDt" ezfName="ordSgnDt" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1\""/></td>
																		<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('ordSgnDt', 4);" tabindex="1"></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td class="stab">Customer Declines Equipment Maintenance</td>
															<td><ezf:inputCheckBox name="dclnSvcCd" ezfName="dclnSvcCd" value="Y" onClick="sendServer('OnChange_DclnSvcCdHdr')" otherAttr=" tabindex=\"1\""/></td>
														</tr>
													</table>
												</fieldset>

												<% boolean isLeaseDtlDisp = "Y".equals(((NWAL1500BMsg)databean.getEZDBMsg()).xxDplyCtrlFlg_LD.getValue()); %>
												<% if (isLeaseDtlDisp) { %>
												<fieldset>
													<legend style="font-size:12px;">Lease Details</legend>
													<table style="table-layout:fixed;">
														<col width="150">
														<col width="250">
														<tr>
															<td class="stab">End of Term Purchase Option</td>
															<td><ezf:select name="leasePrchOptCd" ezfName="leasePrchOptCd" ezfBlank="1" ezfCodeName="leasePrchOptCd_CD" ezfDispName="leasePrchOptDescTxt_NM" otherAttr=" style=\"width: 200px\" tabindex=\"1\""/></td>
														</tr>
														<tr>
															<td class="stab">Term</td>
															<td><ezf:inputText name="leaseTermMthAot" ezfName="leaseTermMthAot" value="12" otherAttr=" size=\"28\" maxlength=\"3\" tabindex=\"1\" style=\"text-align: right\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">Payment Frequency</td>
															<td><ezf:select name="leasePmtFreqCd" ezfName="leasePmtFreqCd" ezfBlank="1" ezfCodeName="leasePmtFreqCd_CD" ezfDispName="leasePmtFreqDescTxt_NM" otherAttr=" style=\"width: 200px\" tabindex=\"1\""/></td>
														</tr>
														<tr>
															<td class="stab">Lease Total Payment Amount</td>
															<td><ezf:inputText name="leaseTotPmtAmt" ezfName="leaseTotPmtAmt" value="$1,000.00" otherAttr=" size=\"28\" maxlength=\"15\" tabindex=\"1\" style=\"text-align: right\" ezftoupper=\"\""/></td>
														</tr>
													</table>
												</fieldset>
												<% } %>

												<% boolean isEMSDSegmentDisp = "Y".equals(((NWAL1500BMsg)databean.getEZDBMsg()).xxDplyCtrlFlg_EM.getValue()); %>
												<% if (isEMSDSegmentDisp) { %>
												<fieldset>
													<legend style="font-size:12px;">Lease Details</legend>
													<table style="table-layout:fixed;">
														<col width="150">
														<col width="250">
														<tr>
															<td class="stab">Term</td>
															<td><ezf:inputText name="leaseTermMthAot_EM" ezfName="leaseTermMthAot_EM" value="12" otherAttr=" size=\"28\" maxlength=\"3\" tabindex=\"1\" style=\"text-align: right\" ezftoupper=\"\""/></td>
														</tr>
													</table>
												</fieldset>
												<% } %>

												<% boolean isGLSegmentDisp = "Y".equals(((NWAL1500BMsg)databean.getEZDBMsg()).xxDplyCtrlFlg_GS.getValue()); %>
												<% if (isGLSegmentDisp) { %>
												<fieldset>
													<legend style="font-size:12px;">GL Segments</legend>
													<table style="table-layout:fixed;">
														<col width="100">
														<col width="100">
														<col width="250">
														<tr>
															<td class="stab">Sales Rep</td>
															<td class="pOut">
																<ezf:inputText name="psnNum_GS" ezfName="psnNum_GS" value="10017520" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
															<td class="pOut">
																<ezf:inputText name="tocNm_GS" ezfName="tocNm_GS" value="FM - 51149 MORGAN STANLEY" otherAttr=" size=\"35\" maxlength=\"35\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
														</tr>
														<tr>
															<td class="stab">Bus Unit</td>
															<td class="pOut">
																<ezf:inputText name="coaExtnCd_GS" ezfName="coaExtnCd_GS" value="032" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
															<td class="pOut">
																<ezf:inputText name="coaExtnDescTxt_GS" ezfName="coaExtnDescTxt_GS" value="320 - Biz Svc(FM)" otherAttr=" size=\"35\" maxlength=\"35\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
														</tr>
														<tr>
															<td class="stab">Branch</td>
															<td class="pOut">
																<ezf:inputText name="coaBrCd_GS" ezfName="coaBrCd_GS" value="3z1" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
															<td class="pOut">
																<ezf:inputText name="coaBrDescTxt_GS" ezfName="coaBrDescTxt_GS" value="BSD - East" otherAttr=" size=\"35\" maxlength=\"35\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
														</tr>
														<tr>
															<td class="stab">Cost Center</td>
															<td class="pOut">
																<ezf:inputText name="coaCcCd_GS" ezfName="coaCcCd_GS" value="413136" otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
															<td class="pOut">
																<ezf:inputText name="coaCcDescTxt_GS" ezfName="coaCcDescTxt_GS" value="FM - MORGAN STANLEY - ISG" otherAttr=" size=\"35\" maxlength=\"35\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/>
															</td>
														</tr>
													</table>
												</fieldset>
												<% } %>

											</td>
										</tr>
									</table>
									<table height="40">
										<col width="1100" valign="bottom">
										<td align="left">
											<fieldset>
												<table style="table-layout:fixed;">
													<col width="60">
													<col width="110">
													<col width="20">
													<col width="120">
													<col width="110">
													<col width="20">
													<col width="200">
													<col width="110">
													<col width="20">
													<col width="50">
													<col width="110">
													<td class="stab">Actions(<u>A</u>)</td>
													<td>
														<ezf:select name="ordEntryActCd_AC" ezfName="ordEntryActCd_AC" ezfBlank="1" ezfCodeName="ordEntryActCd_HA" ezfDispName="ordEntryActDescTxt_HA" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryAction')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"1\" accesskey=\"A\""/>
													</td>
													<td>&nbsp;</td>
													<td class="stab">Additional Information(<u>F</u>)</td>
													<td>
														<ezf:select name="ordEntryActCd_IF" ezfName="ordEntryActCd_IF" ezfBlank="1" ezfCodeName="ordEntryActCd_HI" ezfDispName="ordEntryActDescTxt_HI" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryActionAddlInfo')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"1\" accesskey=\"F\""/>
													</td>
													<td>&nbsp;</td>
													<td class="stab">Delivery/Install/Site Survey/Contacts(<u>C</u>)</td>
													<td>
														<ezf:select name="ordEntryActCd_DL" ezfName="ordEntryActCd_DL" ezfBlank="1" ezfCodeName="ordEntryActCd_HD" ezfDispName="ordEntryActDescTxt_HD" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryActionDelivery')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"1\" accesskey=\"C\""/>
													</td>
													<td>&nbsp;</td>
													<td class="stab">Tools(<u>T</u>)</td>
													<td>
														<ezf:select name="ordEntryActCd_TO" ezfName="ordEntryActCd_TO" ezfBlank="1" ezfCodeName="ordEntryActCd_HT" ezfDispName="ordEntryActDescTxt_HT" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryActionTool')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"1\" accesskey=\"T\""/>
													</td>
												</table>
											</fieldset>
										</td>
									</table>
									</div>
									</c:when>
								</c:choose>

<%-- ##### TAB(Line_Config) ##### --%>
								<c:choose>
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='Line_Config'}">
										<script type="text/javascript">
											document.getElementById( "Header" ).className="pTab_OFF";
											document.getElementById( "Addl_Header" ).className="pTab_OFF";
											document.getElementById( "Line_Config" ).className="pTab_ON";
											document.getElementById( "RMA" ).className="pTab_OFF";
										</script>
										<div class="pTab_BODY_In">
<!-- Order Summary Start -->
											<table>
												<col valign="top" align="left">
												<col valign="top" align="left">
												<tr>
													<td>
														<fieldset>
															<legend style="font-size:12px;">Order Information</legend>
															<!-- table style="table-layout:fixed;" -->
															<table width=630 height = 50>
																<td valign="top">
																	<!-- table style="table-layout:fixed;" -->
																	<table width=210>
																		<col width="100">
																		<col width="100">
																		<tr height="20">
																			<td class="stab">Order Number</td>
																			<td class="pOut">
																				<ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="100123" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/>
																			</td>
																		</tr>
																		<tr height="20">
																			<td class="stab">Order Status</td>
																			<td class="pOut"><ezf:inputText name="ordHdrStsDescTxt" ezfName="ordHdrStsDescTxt" value="ENTERED" otherAttr=" size=\"15\" maxlength=\"50\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																		</tr>
																	</table>
																</td>
																<td valign="top">
																	<!-- table style="table-layout:fixed;" -->
																	<table border="1" cellpadding="1" cellspacing="0" width=420>
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
																			<td class="pOut" align="right"><ezf:label name="xxTotBaseAmt_LN" ezfName="xxTotBaseAmt_LN" /></td>
																			<td class="pOut" align="right"><ezf:label name="xxTotFrtAmt_LN" ezfName="xxTotFrtAmt_LN" /></td>
																			<td class="pOut" align="right"><ezf:label name="xxTotTaxAmt_LN" ezfName="xxTotTaxAmt_LN" /></td>
																			<td class="pOut" align="right"><ezf:label name="xxTotAmt_LN" ezfName="xxTotAmt_LN" /></td>
																		</tr>
																	</table>
																</td>
															</table>
														</fieldset>
													</td>
													<td valign="top">
														<fieldset>
															<legend style="font-size:12px;">Ship To</legend>
															<table height="50" width="450">
																<td>
																<table>
																	<col width="100">
																	<col width="350">
																	<tr height="21">
																		<td class="stab">Account Name</td>
																		<td class="pOut"><ezf:label name="shipToCustAcctNm" ezfName="shipToCustAcctNm" /></td>
																	</tr>
																	<tr height="21">
																		<td class="stab">Account Number</td>
																		<td class="pOut"><ezf:label name="shipToCustAcctCd" ezfName="shipToCustAcctCd" /></td>
																	</tr>
																</table>
																</td>
															</table>
														</fieldset>
													</td>
												</tr>
												<td>
													<hr width="1130">
												</td>
											</table>
<!-- Order Summary End -->
<!-- Page Component Start -->
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="300">
												<col align="left" width="">
												<col align="right" width="580">
												<tr>
													<td><!-- input type="hidden" size="0" value="" id="xxComnColOrdTxt" name="xxComnColOrdTxt" ezfname="xxComnColOrdTxt" --></td>
													<td>
														Config#
														<ezf:inputText name="dsOrdPosnNum_AS" ezfName="dsOrdPosnNum_AS" value="200" otherAttr=" size=\"4\" maxlength=\"3\""/>
														/
														<ezf:label name="dsOrdPosnNum_AT" ezfName="dsOrdPosnNum_AT" />
														<ezf:inputButton name="ConfigJump" value="Config Jump" htmlClass="pBtn8" />
													</td>
													<td align="right">
														<ezf:skip>
														<table border="0" cellpadding="0" width="100%">
															<tr>
																<td align="left">
																	<table border="0" cellpadding="0" align="left" cellspacing="0">
																		<col>
																			<tr>
																			<td>Results 1999 - 1999 of 2000</td>
																			</tr>
																	</table>
																</td>
																<td align="right">
																	<table border="0" cellpadding="0" cellspacing="0">
																		<col width="54"  align="center">
																		<col width="40"  align="center">
																		<col width="16"  align="center">
																		<col width="40"  align="center">
																		<col width="26"  align="center">
																		<col width="10">
																		<col>
																		<col width="20">
																		<col>
																		<col width="1">
																		<col>
																		<tr>
																			<td class="stab">Showing</td>
																			<td><input type="text" name="xxPageShowCurNum_LL" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																			<td class="stab">/</td>
																			<td><input type="text" name="xxPageShowTotNum_LL" ezfName="xxPageShowTotNum_LL" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																			<td class="stab">page</td>
																			<td>&nbsp;</td>
																			<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'B')"></td>
																			<td></td>
																			<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'B')"></td>
																			<td></td>
																			<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'B')"></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
														</ezf:skip>
														<table width="100%">
															<tr align="right">
																<td>
																	<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																		<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
																		<jsp:param name="TableNm"           value="B" />
																		<jsp:param name="ShowingFrom"       value="xxPageShowFromNum_LL" />
																		<jsp:param name="ShowingTo"         value="xxPageShowToNum_LL" />
																		<jsp:param name="ShowingOf"         value="xxPageShowOfNum_LL" />
																		<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum_LL" />
																		<jsp:param name="ShowingTotal"      value="xxPageShowTotNum_LL" />
																		<jsp:param name="ViewMode"          value="FULL" />
																	</jsp:include>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
<!-- Page Component End -->
<!-- Order Detail Start -->
											<table width="100%">
												<col valign="top">
												<tr>
													<td>
														<table border="0">
															<tr>
																<td valign="top">
																	<table width="100%" cellpadding="0" cellspacing="0" border="0">
																		<col align="right" width="510">
																		<col align="left"  width="598">
																		<col width="">
																		<tr>
																			<td valign="top">

																				<%-- LEFT-TABLE(TOP) --%>
																				<div id="leftTBL" style="overflow-y:hidden; width:512; height:342; overflow-x:hidden;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));">
																					<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
																					<%@ page import="business.servlet.NWAL1500.NWAL1500BMsg" %>
																					<%@ page import="business.servlet.NWAL1500.NWAL1500_ABMsg" %>
																					<%@ page import="business.servlet.NWAL1500.NWAL1500_BBMsg" %>
																					<%@ page import="com.canon.cusa.s21.framework.common.S21StringUtil" %>
																					<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPConstant" %>
																					<% NWAL1500BMsg bMsg = (NWAL1500BMsg)databean.getEZDBMsg(); 
																						int lineIdx = 0;
																					%>

																					<% for ( int i = 0; i < bMsg.A.getValidCount(); i++ ) { %> 

																						<table border="0" cellpadding="0" cellspacing="0" style="margin-top: 2px;">
																							<tr>
																								<td align="right">
																									<% NWAL1500_ABMsg lineMsg = bMsg.A.no(i); %>
																									<% boolean isSmryLine = "Y".equals(lineMsg.xxSmryLineFlg_L.getValue()); %>
																										
                                                                                                        <table border="1" cellpadding="1" cellspacing="0" width="510" style="table-layout:fixed;" >
                                                                                                            <col align="center" width="30"> <!-- 1. Collapse Mark -->
                                                                                                            <col align="center" width="30"> <!-- 2. Check Box: Config -->
                                                                                                            <col align="center" width="60"> <!-- 3. Config# -->
                                                                                                            <col align="center" width="150"><!-- 4. Config Type -->
                                                                                                            <col align="center" width="140"><!-- 5. Model -->
                                                                                                            <col align="center" width="100"><!-- 6. Config Id -->
                                                                                                            <tr>
                                                                                                                <td class="pClothBs">&nbsp</td>
                                                                                                                <td class="pClothBs">&nbsp</td>
                                                                                                                <td class="pClothBs">Config#</td>
                                                                                                                <td class="pClothBs">Config Action</td>
                                                                                                                <td class="pClothBs">Model</td>
                                                                                                                <td class="pClothBs">Config ID</td>
                                                                                                            </tr>
                                                                                                        </table>
                                                                                                        
																										<table border="1" cellpadding="1" cellspacing="0" width="510" style="table-layout:fixed;" >
																											<col align="center" width="30"> <!-- 1. Collapse Mark -->
																											<col align="center" width="30"> <!-- 2. Check Box: Config -->
																											<col align="center" width="60"> <!-- 3. Config# -->
																											<col align="center" width="150"><!-- 4. Config Type -->
																											<col align="center" width="140"><!-- 5. Model -->
																											<col align="center" width="100"><!-- 6. Config Id -->
																											<tr height="24">
																												<td bgcolor="#bfd8d8">
																													<% if (isSmryLine) { %>
																														<img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('Line_Expand','<%= i %>')" ezfHyo="A" height="14">
																													<% } else if (!isSmryLine) { %>
																														<img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('Line_Collapsed','<%= i %>')" ezfHyo="A" height="14">
																													<% } %>
																												</td>
																												<td bgcolor="#bfd8d8">
																													<% pageContext.setAttribute("xxResFltrFlg_LC", bMsg.A.no(i).xxResFltrFlg_LC.getValue()); %>
																													<input type="hidden" name="xxResFltrFlg_LC" ezfName="" value="<c:out value='${xxResFltrFlg_LC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																													<ezf:inputCheckBox name="xxChkBox_LC" ezfName="xxChkBox_LC" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>"  otherAttr=" tabindex=\"0\"" />
																												</td>
																												<td bgcolor="#bfd8d8" align="center">
																													<ezf:label name="dsOrdPosnNum_LC" ezfName="dsOrdPosnNum_LC" ezfHyo="A" ezfArrayNo="<%= i %>"/>
																													<% pageContext.setAttribute("dsOrdPosnNum_LC", bMsg.A.no(i).dsOrdPosnNum_LC.getValue()); %>
																													<input type="hidden" name="dsOrdPosnNum_LC" ezfName="" value="<c:out value='${dsOrdPosnNum_LC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																												</td>
																												<td bgcolor="#bfd8d8" align="center">
																													<span id="<%= i %>">
																														<ezf:select name="configTpCd_LC" ezfName="configTpCd_LC" ezfHyo="A" ezfArrayNo="<%= i %>" ezfCodeName="configTpCd_LD" ezfDispName="configTpDescTxt_LD" otherEvent1="onchange=\"sendServer('OnChange_ConfigAction', this.parentNode.id);\"" otherAttr="tabindex=\"0\" style=\"width:135\""/>
																													</span>
																												</td>
																												<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="mdlNm_LC" ezfName="mdlNm_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"-1\" size=\"18\" maxlength=\"90\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																												<td bgcolor="#bfd8d8">
																													<table border="0" cellpadding="1" cellspacing="0">
																														<tr style="padding:0;">
																															<td><ezf:inputText name="svcConfigMstrPk_LC" ezfName="svcConfigMstrPk_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromConfigId" otherAttr=" size=\"10\" maxlength=\"28\" style=\"text-align: left;\" ezftoupper=\"\" ezffocusout=\"OnBlur_DeriveFromConfigId\""/></td>
																															<td>
																																<ezf:anchor name="xxImageTxt_AC" ezfName="xxImageTxt_AC" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ConfigID" otherAttr=" id=\"xxImageTxt_AC\" ezfanchortext=\"\"">C</ezf:anchor>
																															</td>
																														</tr>
																													</table>
																												</td>
																											</tr>
																										</table>
																										<% if (!isSmryLine) { %>
                                                                                                        <table border="1" cellpadding="1" cellspacing="0" width="450" style="table-layout:fixed; margin-top: 2px">
                                                                                                            <col align="center" width="30"> <!-- 1. Check Box -->
                                                                                                            <col align="center" width="60"> <!-- 2. Line# -->
                                                                                                            <col align="center" width="120"><!-- 3. Item#(*) -->
                                                                                                            <col align="center" width="240"><!-- 4. Item Description -->
                                                                                                            <tr>
                                                                                                                <td class="pClothBs" >&nbsp</td>
                                                                                                                <td class="pClothBs" >Line#</td>
                                                                                                                <td class="pClothBs" >Item#(*)</td>
                                                                                                                <td class="pClothBs" >Item Description</td>
                                                                                                            </tr>
                                                                                                        </table>
																										<% } %>
																								</td>
																							</tr>
																							<tr>
																								<td align="right">
																									<table border="1" cellpadding="1" cellspacing="0" width="450" align="right" style="table-layout:fixed;" >
																										<col align="center" width="30"> <!-- 1. Check Box -->
																										<col align="center" width="60"> <!-- 2. Line# -->
																										<col align="center" width="120"><!-- 3. Item#(*) -->
																										<col align="center" width="240"><!-- 4. Item Description -->
																										<% for ( int j = lineIdx; j < bMsg.B.getValidCount(); j++ ) { %>
																											<%
																												NWAL1500_ABMsg configLineMsg = bMsg.A.no(i);
																												String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_LC.getValue();
																												
																												String lineDsOrdPosnNum = bMsg.B.no(j).dsOrdPosnNum_LL.getValue();
																												if (!dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
																													break;
																												}
																												lineIdx++;
																												
																												if (isSmryLine) {
																													pageContext.setAttribute("xxResFltrFlg_LL", bMsg.B.no(j).xxResFltrFlg_LL.getValue());
																													pageContext.setAttribute("xxChkBox_LL", bMsg.B.no(j).xxChkBox_LL.getValue());
																													pageContext.setAttribute("xxLineNum_LL", bMsg.B.no(j).xxLineNum_LL.getValue());
																													pageContext.setAttribute("mdseCd_LL", bMsg.B.no(j).mdseCd_LL.getValue());
																													pageContext.setAttribute("mdseDescShortTxt_LL", bMsg.B.no(j).mdseDescShortTxt_LL.getValue());
																											%>

																													<input type="hidden" name="xxResFltrFlg_LL" ezfName="" value="<c:out value='${xxResFltrFlg_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																													<input type="hidden" name="xxChkBox_LL" ezfName="" value="<c:out value='${xxChkBox_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																													<input type="hidden" name="xxLineNum_LL" ezfName="" value="<c:out value='${xxLineNum_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																													<input type="hidden" name="mdseCd_LL" ezfName="" value="<c:out value='${mdseCd_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																													<input type="hidden" name="mdseDescShortTxt_LL" ezfName="" value="<c:out value='${mdseDescShortTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																											<%
																													continue;
																												}
																											%>

																											<tr height="24">
																												<td>
																													<% pageContext.setAttribute("xxResFltrFlg_LL", bMsg.B.no(j).xxResFltrFlg_LL.getValue()); %>
																													<input type="hidden" name="xxResFltrFlg_LL" ezfName="" value="<c:out value='${xxResFltrFlg_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																													<ezf:inputCheckBox name="xxChkBox_LL" ezfName="xxChkBox_LL" value="Y" ezfHyo="B" ezfArrayNo="<%= j %>"/>
																												</td>
																												<td>
																													<ezf:label name="xxLineNum_LL" ezfName="xxLineNum_LL" ezfHyo="B" ezfArrayNo="<%= j %>"/>
																													<% pageContext.setAttribute("xxLineNum_LL", bMsg.B.no(j).xxLineNum_LL.getValue()); %>
																													<input type="hidden" name="xxLineNum_LL" ezfName="" value="<c:out value='${xxLineNum_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																												</td>
																												<td>
																													<table border="0" cellpadding="1" cellspacing="0">
																														<tr style="padding:0;">
																															<td><ezf:inputText name="mdseCd_LL" ezfName="mdseCd_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromItem" otherAttr=" size=\"12\" maxlength=\"16\" ezffocusout=\"OnBlur_DeriveFromItem\"" /></td>
																															<td>
																																<ezf:anchor ezfName="xxImageTxt_BI" ezfHyo="B" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_Merchandise" otherAttr=" id=\"xxImageTxt_BI\" ezfanchortext=\"\"">I</ezf:anchor>
																															</td>
																														</tr>
																													</table>
																												</td>
																												<td align="left"><ezf:inputText name="mdseDescShortTxt_LL" ezfName="mdseDescShortTxt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"30\" maxlength=\"250\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																											</tr>
																										<% } %>
																									<%-- +++++ [END] Programming JSP for Nesting Records Table +++++ --%>
																									</table>
																								</td>
																							</tr>
																						</table>
																					<% } %>
																				</div>
																			</td>

																			<td valign="top">

																				<!-- RIGHT-TABLE(TOP) -->
																				<div id="rightTBL" style="overflow-y:scroll; height:362; overflow-x:scroll; width:598;" onscroll="setScrollPosition();synchroScrollTop(this.id, new Array('leftTBL'));synchroScrollLeft(this.id, new Array('rightTopTBL'));">

																					<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
																					<%@ page import="business.servlet.NWAL1500.NWAL1500BMsg" %>
																					<%@ page import="business.servlet.NWAL1500.NWAL1500_ABMsg" %>
																					<%@ page import="business.servlet.NWAL1500.NWAL1500_BBMsg" %>
																					<%@ page import="com.canon.cusa.s21.framework.common.S21StringUtil" %>
																					<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPConstant" %>
																					<% NWAL1500BMsg bMsg_rt = (NWAL1500BMsg)databean.getEZDBMsg(); 
																						int lineIdx_rt = 0;
																					%>

																					<% for ( int i = 0; i < bMsg_rt.A.getValidCount(); i++ ) { %>
																						<table border="0" cellpadding="0" cellspacing="0" style="margin-top: 2px;">
																							<tr>
																								<td align="left">
			                                                                                        
			                                                                                        <table border="1" cellpadding="1" cellspacing="0" width="4190" style="table-layout:fixed;" >
			                                                                                            <col align="center" width="300"><!-- 7.Ship To Address-->
			                                                                                            <col align="center" width="100"><!-- 8.Ship To Acct#-->
			                                                                                            <col align="center" width="100"><!-- 9.Ship To Loc#-->
			                                                                                            <col align="center" width="180"><!-- 10.Ship To Name-->
			                                                                                            <col align="center" width="180"><!-- 11.DS Name-->
			                                                                                            <col align="center" width="310"><!-- 12.Bill To Address-->
			                                                                                            <col align="center" width="100"><!-- 13.Bill To Acct#-->
			                                                                                            <col align="center" width="100"><!-- 14.Bill To Loc#-->
			                                                                                            <col align="center" width="180"><!-- 15.Bill TO Name-->
			                                                                                            <col align="center" width="300"><!-- 16.Sold to address -->
			                                                                                            <col align="center" width="90" ><!-- 17.Sold To Number-->
			                                                                                            <col align="center" width="90" ><!-- 18.Sold To Loc Number-->
			                                                                                            <col align="center" width="180"><!-- 19.Sold To Name-->
			                                                                                            <col align="center" width="110"><!-- 20.5.Customer Declines Equipment Maintenance -->
			                                                                                            <col align="center" width="110"><!-- 21.Model Group-->
			                                                                                            <col align="center" width="180"><!-- 22.Model Description-->
			                                                                                            <col align="center" width="180"><!-- 23.Segment-->
			                                                                                            <col align="center" width="80" ><!-- 24.Install Required for Revenue Y/N-->
			                                                                                            <col align="center" width="80" ><!-- 25.Site Survey Req-->
			                                                                                            <col align="center" width="80" ><!-- 26.Sales Credit Splits  (Y/N)-->
			                                                                                            <col align="center" width="80" ><!-- 27.On Hold (Y/N)-->
			                                                                                            <col align="center" width="190"><!-- 28.Created By (User ID & Name)-->
			                                                                                            <col align="center" width="160"><!-- 29.Creation Date & Time-->
			                                                                                            <col align="center" width="190"><!-- 30.Last Update By (ID & Name)-->
			                                                                                            <col align="center" width="160"><!-- 31.Last Update Date & Time-->
			                                                                                            <col align="center" width="120"><!-- 32.Sub Total (Sum the Lines inside the config)-->
			                                                                                            <col align="center" width="120"><!-- 33.Charges-->
			                                                                                            <col align="center" width="120"><!-- 34.Tax-->
			                                                                                            <col align="center" width="120"><!-- 35. Config  Total-->
			                                                                                            <col align="center" width="80" ><!-- 36.DS-->
			                                                                                            <col align="center" width="180"><!-- 37.CSMP Number-->
			                                                                                            <col align="center" width="200"><!-- 38.CSA Dealer Ref#-->
			                                                                                            <col align="center" width="180"><!-- 39.CSMP Price List-->
			                                                                                            <tr>
			                                                                                                <td class="pClothBs">Ship To Address</td>
			                                                                                                <td class="pClothBs">Ship To Acct#(*)</td>
			                                                                                                <td class="pClothBs">Ship To Code(*)</td>
			                                                                                                <td class="pClothBs">Ship To Name(*)</td>
			                                                                                                <td class="pClothBs">DS Name</td>
			                                                                                                <td class="pClothBs">Bill To Address</td>
			                                                                                                <td class="pClothBs">Bill To Acct#(*)</td>
			                                                                                                <td class="pClothBs">Bill To Code(*)</td>
			                                                                                                <td class="pClothBs">Bill To Name(*)</td>
			                                                                                                <td class="pClothBs">Sold to Address</td>
			                                                                                                <td class="pClothBs">Sold To#</td>
			                                                                                                <td class="pClothBs">Sold To Code</td>
			                                                                                                <td class="pClothBs">Sold To Name</td>
			                                                                                                <td class="pClothBs">Decline Maint</td>
			                                                                                                <td class="pClothBs">Model Group</td>
			                                                                                                <td class="pClothBs">Model Description</td>
			                                                                                                <td class="pClothBs">Segment</td>
			                                                                                                <td class="pClothBs">Install Req.</td>
			                                                                                                <td class="pClothBs">Site Sur.</td>
			                                                                                                <td class="pClothBs">Sls CR Splits</td>
			                                                                                                <td class="pClothBs">On Hold</td>
			                                                                                                <td class="pClothBs">Created By</td>
			                                                                                                <td class="pClothBs">Creation Date</td>
			                                                                                                <td class="pClothBs">Last Update By</td>
			                                                                                                <td class="pClothBs">Last Update Date</td>
			                                                                                                <td class="pClothBs">Sub Total</td>
			                                                                                                <td class="pClothBs">Charges</td>
			                                                                                                <td class="pClothBs">Tax</td>
			                                                                                                <td class="pClothBs">Config Total</td>
			                                                                                                <td class="pClothBs">DS</td>
			                                                                                                <td class="pClothBs">CSMP Number</td>
			                                                                                                <td class="pClothBs">CSA Dealer Ref#</td>
			                                                                                                <td class="pClothBs">CSMP Price List</td>
			                                                                                            </tr>
			                                                                                        </table>
			                                                                                        
			                                                                                        <table border="0" cellpadding="0" cellspacing="0" id="A2"">
			                                                                                            <tr>
			                                                                                                <td>
																												<% NWAL1500_ABMsg lineMsg_rt = bMsg_rt.A.no(i); %>
																												<% boolean isSmryLine = "Y".equals(lineMsg_rt.xxSmryLineFlg_L.getValue()); %>
																													<table border="1" cellpadding="1" cellspacing="0" width="4190" style="table-layout:fixed;">
																														<col align="center" width="300"><!-- 7.Ship To Address-->
																														<col align="center" width="100"><!-- 8.Ship To Acct#-->
																														<col align="center" width="100"><!-- 9.Ship To Loc#-->
																														<col align="center" width="180"><!-- 10.Ship To Name-->
																														<col align="center" width="180"><!-- 11.DS Name-->
																														<col align="center" width="310"><!-- 12.Bill To Address-->
																														<col align="center" width="100"><!-- 13.Bill To Acct#-->
																														<col align="center" width="100"><!-- 14.Bill To Loc#-->
																														<col align="center" width="180"><!-- 15.Bill TO Name-->
																														<col align="center" width="300"><!-- 16.Sold to address -->
																														<col align="center" width="90" ><!-- 17.Sold To Number-->
																														<col align="center" width="90" ><!-- 18.Sold To Loc Number-->
																														<col align="center" width="180"><!-- 19.Sold To Name-->
																														<col align="center" width="110"><!-- 20.5.Customer Declines Equipment Maintenance -->
																														<col align="center" width="110"><!-- 21.Model Group-->
																														<col align="center" width="180"><!-- 22.Model Description-->
																														<col align="center" width="180"><!-- 23.Segment-->
																														<col align="center" width="80" ><!-- 24.Install Required for Revenue Y/N-->
																														<col align="center" width="80" ><!-- 25.Site Survey Req-->
																														<col align="center" width="80" ><!-- 26.Sales Credit Splits  (Y/N)-->
																														<col align="center" width="80" ><!-- 27.On Hold (Y/N)-->
																														<col align="center" width="190"><!-- 28.Created By (User ID & Name)-->
																														<col align="center" width="160"><!-- 29.Creation Date & Time-->
																														<col align="center" width="190"><!-- 30.Last Update By (ID & Name)-->
																														<col align="center" width="160"><!-- 31.Last Update Date & Time-->
																														<col align="center" width="120"><!-- 32.Sub Total (Sum the Lines inside the config)-->
																														<col align="center" width="120"><!-- 33.Charges-->
																														<col align="center" width="120"><!-- 34.Tax-->
																														<col align="center" width="120"><!-- 35.Config  Total-->
																														<col align="center" width="80" ><!-- 36.DS-->
																														<col align="center" width="180"><!-- 37.CSMP Number-->
																														<col align="center" width="200"><!-- 38.CSA Dealer Ref#-->
																														<col align="center" width="180"><!-- 39.CSMP Price List-->
																														<tr height="24">
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="shipToCustLocAddr_LC" ezfName="shipToCustLocAddr_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"40\" maxlength=\"400\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8">
																																<table border="0" cellpadding="1" cellspacing="0">
																																	<tr style="padding:0;">
																																		<td><ezf:inputText name="shipToCustAcctCd_LC" ezfName="shipToCustAcctCd_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromShipToAccount" otherAttr=" size=\"10\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromShipToAccount\"" /></td>
																																		<td>
																																			<ezf:anchor name="xxImageTxt_AA" ezfName="xxImageTxt_AA" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"xxImageTxt_AA\" ezfanchortext=\"\"">S</ezf:anchor>
																																		</td>
																																	</tr>
																																</table>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<table border="0" cellpadding="1" cellspacing="0">
																																	<tr style="padding:0;">
																																		<td><ezf:inputText name="shipToCustCd_LC" ezfName="shipToCustCd_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromShipToLocation" otherAttr=" size=\"10\" maxlength=\"22\" ezffocusout=\"OnBlur_DeriveFromShipToLocation\"" /></td>
																																		<td>
																																			<ezf:anchor name="xxImageTxt_AS" ezfName="xxImageTxt_AS" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"xxImageTxt_AS\" ezfanchortext=\"\"">S</ezf:anchor>
																																		</td>
																																	</tr>
																																</table>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<table border="0" cellpadding="1" cellspacing="0">
																																	<tr style="padding:0;">
																																		<td><ezf:inputText name="shipToCustAcctNm_LC" ezfName="shipToCustAcctNm_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromShipToName" otherAttr=" size=\"22\" maxlength=\"60\" ezffocusout=\"OnBlur_DeriveFromShipToName\"" /></td>
																																		<td>
																																			<ezf:anchor name="xxImageTxt_AN" ezfName="xxImageTxt_AN" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"xxImageTxt_AN\" ezfanchortext=\"\"">S</ezf:anchor>
																																		</td>
																																	</tr>
																																</table>
																															</td>
																															<td align="left" bgcolor="#bfd8d8">
																																<ezf:inputText name="addShipToLocNm_LC" ezfName="addShipToLocNm_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"23\" maxlength=\"90\" ezftoupper=\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="billToCustLocAddr_LC" ezfName="billToCustLocAddr_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"40\" maxlength=\"400\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8">
																																<table border="0" cellpadding="1" cellspacing="0">
																																	<tr style="padding:0;">
																																		<td><ezf:inputText name="billToCustAcctCd_LC" ezfName="billToCustAcctCd_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromBillToAccount" otherAttr=" size=\"10\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromBillToAccount\"" /></td>
																																		<td>
																																			<ezf:anchor name="xxImageTxt_AT" ezfName="xxImageTxt_AT" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"xxImageTxt_AT\" ezfanchortext=\"\"">B</ezf:anchor>
																																		</td>
																																	</tr>
																																</table>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<table border="0" cellpadding="1" cellspacing="0">
																																	<tr style="padding:0;">
																																		<td><ezf:inputText name="billToCustCd_LC" ezfName="billToCustCd_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromBillToLocation" otherAttr=" size=\"10\" maxlength=\"60\" ezffocusout=\"OnBlur_DeriveFromBillToLocation\"" /></td>
																																		<td>
																																			<ezf:anchor name="xxImageTxt_AB" ezfName="xxImageTxt_AB" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"xxImageTxt_AB\" ezfanchortext=\"\"">B</ezf:anchor>
																																		</td>
																																	</tr>
																																</table>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<table border="0" cellpadding="1" cellspacing="0">
																																	<tr style="padding:0;">
																																		<td><ezf:inputText name="billToCustAcctNm_LC" ezfName="billToCustAcctNm_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromBillToName" otherAttr=" size=\"22\" maxlength=\"60\" ezffocusout=\"OnBlur_DeriveFromBillToName\"" /></td>
																																		<td>
																																			<ezf:anchor name="xxImageTxt_AM" ezfName="xxImageTxt_AM" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"xxImageTxt_AM\" ezfanchortext=\"\"">B</ezf:anchor>
																																		</td>
																																	</tr>
																																</table>
																															</td>
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="soldToCustLocAddr_LC" ezfName="soldToCustLocAddr_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"40\" maxlength=\"400\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="sellToCustCd_LC" ezfName="sellToCustCd_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"10\" maxlength=\"400\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="soldToCustLocCd_LC" ezfName="soldToCustLocCd_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"10\" maxlength=\"400\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="soldToCustAcctNm_LC" ezfName="soldToCustAcctNm_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"20\" maxlength=\"400\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																															
																															<td bgcolor="#bfd8d8" align="center">
																																<span id="<%= i %>">
																																<ezf:inputCheckBox name="dclnSvcCd_LC" ezfName="dclnSvcCd_LC" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"0\"" otherEvent1=" onclick=\"sendServer('OnChange_DclnSvcCdConfig',this.parentNode.id)\""/>
																																</span>
																															</td>
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="mdlGrpDescTxt_LC" ezfName="mdlGrpDescTxt_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"13\" maxlength=\"400\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="mdlDescTxt_LC" ezfName="mdlDescTxt_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"22\" maxlength=\"400\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																															
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="svcSegDescTxt_LC" ezfName="svcSegDescTxt_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"20\" maxlength=\"400\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																															
																															<td bgcolor="#bfd8d8">
																																<ezf:label name="svcIstlReqFlg_LC" ezfName="svcIstlReqFlg_LC" ezfHyo="A" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("svcIstlReqFlg_LC", bMsg_rt.A.no(i).svcIstlReqFlg_LC.getValue()); %>
																																<input type="hidden" name="svcIstlReqFlg_LC" ezfName="" value="<c:out value='${svcIstlReqFlg_LC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<ezf:label name="siteSrvyReqFlg_LC" ezfName="siteSrvyReqFlg_LC" ezfHyo="A" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("siteSrvyReqFlg_LC", bMsg_rt.A.no(i).siteSrvyReqFlg_LC.getValue()); %>
																																<input type="hidden" name="siteSrvyReqFlg_LC" ezfName="" value="<c:out value='${siteSrvyReqFlg_LC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<ezf:label name="slsCrSplFlg_LC" ezfName="slsCrSplFlg_LC" ezfHyo="A" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("slsCrSplFlg_LC", bMsg_rt.A.no(i).slsCrSplFlg_LC.getValue()); %>
																																<input type="hidden" name="slsCrSplFlg_LC" ezfName="" value="<c:out value='${slsCrSplFlg_LC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<ezf:label name="dsCpoConfigHldFlg_LC" ezfName="dsCpoConfigHldFlg_LC" ezfHyo="A" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("dsCpoConfigHldFlg_LC", bMsg_rt.A.no(i).dsCpoConfigHldFlg_LC.getValue()); %>
																																<input type="hidden" name="dsCpoConfigHldFlg_LC" ezfName="" value="<c:out value='${dsCpoConfigHldFlg_LC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="cratUsrNm_LC" ezfName="cratUsrNm_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"26\" maxlength=\"93\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8" align="left">
																																<ezf:label name="cratTsDplyTxt_LC" ezfName="cratTsDplyTxt_LC" ezfHyo="A" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("cratTsDplyTxt_LC", bMsg_rt.A.no(i).cratTsDplyTxt_LC.getValue()); %>
																																<input type="hidden" name="cratTsDplyTxt_LC" ezfName="" value="<c:out value='${cratTsDplyTxt_LC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="updUsrNm_LC" ezfName="updUsrNm_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"26\" maxlength=\"93\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8" align="left">
																																<ezf:label name="updTsDplyTxt_LC" ezfName="updTsDplyTxt_LC" ezfHyo="A" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("updTsDplyTxt_LC", bMsg_rt.A.no(i).updTsDplyTxt_LC.getValue()); %>
																																<input type="hidden" name="updTsDplyTxt_LC" ezfName="" value="<c:out value='${updTsDplyTxt_LC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															
																															<td bgcolor="#bfd8d8" align="right">
																																<ezf:label name="configTotDealNetAmt_LC" ezfName="configTotDealNetAmt_LC" ezfHyo="A" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("configTotDealNetAmt_LC", bMsg_rt.A.no(i).configTotDealNetAmt_LC.getValue()); %>
																																<input type="hidden" name="configTotDealNetAmt_LC" ezfName="" value="<c:out value='${configTotDealNetAmt_LC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8" align="right">
																																<ezf:label name="configTotDealChrgAmt_LC" ezfName="configTotDealChrgAmt_LC" ezfHyo="A" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("configTotDealChrgAmt_LC", bMsg_rt.A.no(i).configTotDealChrgAmt_LC.getValue()); %>
																																<input type="hidden" name="configTotDealChrgAmt_LC" ezfName="" value="<c:out value='${configTotDealChrgAmt_LC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8" align="right">
																																<ezf:label name="configTotDealTaxAmt_LC" ezfName="configTotDealTaxAmt_LC" ezfHyo="A" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("configTotDealTaxAmt_LC", bMsg_rt.A.no(i).configTotDealTaxAmt_LC.getValue()); %>
																																<input type="hidden" name="configTotDealTaxAmt_LC" ezfName="" value="<c:out value='${configTotDealTaxAmt_LC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8" align="right">
																																<ezf:label name="configSubTotDealAmt_LC" ezfName="configSubTotDealAmt_LC" ezfHyo="A" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("configSubTotDealAmt_LC", bMsg_rt.A.no(i).configSubTotDealAmt_LC.getValue()); %>
																																<input type="hidden" name="configSubTotDealAmt_LC" ezfName="" value="<c:out value='${configSubTotDealAmt_LC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															
																															<td align="center" bgcolor="#bfd8d8">
																																<ezf:anchor name="xxImageTxt_AD" ezfName="xxImageTxt_AD" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToChange" otherAttr=" tabindex=\"-1\" id=\"xxImageTxt_AD\" ezfanchortext=\"\"">
																																	<ezf:label name="dropShipFlg_LC" ezfName="dropShipFlg_LC" ezfHyo="A" ezfArrayNo="<%= i %>" />
																																	<% pageContext.setAttribute("dropShipFlg_LC", bMsg_rt.A.no(i).dropShipFlg_LC.getValue()); %>
																																	<input type="hidden" name="dropShipFlg_LC" ezfName="" value="<c:out value='${dropShipFlg_LC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</ezf:anchor>
																															</td>
																															<td align="left" bgcolor="#bfd8d8">
																																<ezf:inputText name="csmpContrNum_LC" ezfName="csmpContrNum_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromCSMPNumber" otherAttr=" size=\"18\" maxlength=\"15\" ezffocusout=\"OnBlur_DeriveFromCSMPNumber\"" />
																																<ezf:anchor name="xxImageTxt_LN" ezfName="xxImageTxt_LN" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_CSMPNumber" otherAttr=" id=\"xxImageTxt_LN\" ezfanchortext=\"\"">C#</ezf:anchor>
																															</td>
																															<td align="left" bgcolor="#bfd8d8">
																																<ezf:inputText name="dlrRefNum_LC" ezfName="dlrRefNum_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromDealerRefNumber" otherAttr=" size=\"23\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromDealerRefNumber\"" />
																																<ezf:anchor name="xxImageTxt_LD" ezfName="xxImageTxt_LD" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_CSMPNumber" otherAttr=" id=\"xxImageTxt_LD\" ezfanchortext=\"\"">R#</ezf:anchor>
																															</td>
																															<td align="left" bgcolor="#bfd8d8">
																																<ezf:inputText name="prcCatgNm_LC" ezfName="prcCatgNm_LC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"23\" maxlength=\"75\" ezftoupper=\"\""/>
																															</td>
																														</tr>
																													</table>
																													<% if (!isSmryLine) { %>
			                                                                                                        <table border="1" cellpadding="1" cellspacing="0" width="7370" style="table-layout:fixed; margin-top: 2px">
			                                                                                                            <col align="center" width="80" ><!-- 5.Qty Order-->
			                                                                                                            <col align="center" width="110"><!-- 6.Sell Price-->
			                                                                                                            <col align="center" width="110"><!-- 7.Net Price -->
			                                                                                                            <col align="center" width="200"><!-- 8.Status-->
			                                                                                                            <col align="center" width="180"><!-- 9.Line Category-->
			                                                                                                            <col align="center" width="50" ><!-- 10.Credit & Rebill Flag-->
			                                                                                                            <col align="center" width="180"><!-- 11.Warehouse-->
			                                                                                                            <col align="center" width="80" ><!-- 12.Sub Warehouse-->
			                                                                                                            <col align="center" width="190"><!-- 13.Line Source-->
			                                                                                                            <col align="center" width="80" ><!-- 14.UOM-->
			                                                                                                            <col align="center" width="185"><!-- 15.Price List-->
			                                                                                                            <col align="center" width="130"><!-- 16.Unit List Price-->
			                                                                                                            <col align="center" width="185"><!-- 17.Floor Price List-->
			                                                                                                            <col align="center" width="130"><!-- 18.Floor Price-->
			                                                                                                            <col align="center" width="160"><!-- 19.Serial Number-->
			                                                                                                            <col align="center" width="110"><!-- 20'.Machine Master ID-->
			                                                                                                            <col align="center" width="110"><!-- 21.Substitute Item Code-->
			                                                                                                            <col align="center" width="110"><!-- 22.Customer Item Code-->
			                                                                                                            <col align="center" width="120"><!-- 23.Customer Requested Delivery Date-->
			                                                                                                            <col align="center" width="120"><!-- 24.Pricing Date-->
			                                                                                                            <col align="center" width="180"><!-- 25.Booked Date & Time-->
			                                                                                                            <col align="center" width="170"><!-- 26.Shipped Date & Time-->
			                                                                                                            <col align="center" width="170"><!-- 27.Delivery Scheduled Date-->
			                                                                                                            <col align="center" width="90" ><!-- 28.5.QTY B/O-->
			                                                                                                            <col align="center" width="90" ><!-- 29.QTY Allocated-->
			                                                                                                            <col align="center" width="90" ><!-- 30.QTY Shipped-->
			                                                                                                            <col align="center" width="90" ><!-- 31.QTY Invoiced-->
			                                                                                                            <col align="center" width="90" ><!-- 32.QTY Cancelled-->
			                                                                                                            <col align="center" width="200"><!-- 33.Cancel Reason Code-->
			                                                                                                            <col align="center" width="80" ><!-- 34.Superseed Lock-->
			                                                                                                            <col align="center" width="80" ><!-- 35.Pricing Config Number-->
			                                                                                                            <col align="center" width="200"><!-- 36.Picing Config Description-->
			                                                                                                            <col align="center" width="180"><!-- 37.Merchandise Type-->
			                                                                                                            <col align="center" width="180"><!-- 38.Product Code-->
			                                                                                                            <col align="center" width="180"><!-- 39.Item Classification-->
			                                                                                                            <col align="center" width="180"><!-- 40.Manufacturer Number-->
			                                                                                                            <col align="center" width="100"><!-- 41.Line Reference-->
			                                                                                                            <col align="center" width="180"><!-- 42.Customer PO#-->
			                                                                                                            <col align="center" width="180"><!-- 43.Customer PO Date-->
			                                                                                                            <col align="center" width="180"><!-- 44.Order Source Name-->
			                                                                                                            <col align="center" width="180"><!-- 45.Order Source Reference-->
			                                                                                                            <col align="center" width="180"><!-- 46.Line Source Reference-->
			                                                                                                            <col align="center" width="180"><!-- 47.BOM Parent Line#-->
			                                                                                                            <col align="center" width="180"><!-- 48.BOM Parent Version#-->
			                                                                                                            <col align="center" width="180"><!-- 49.BOM Parent Item Code-->
			                                                                                                            <col align="center" width="180"><!-- 50.Created By -->
			                                                                                                            <col align="center" width="180"><!-- 51.Creation Date & Time-->
			                                                                                                            <col align="center" width="180"><!-- 52.Last Update By -->
			                                                                                                            <col align="center" width="180"><!-- 53.Last Update Date & Time-->
			                                                                                                            <col align="center" width="120"><!-- 54.Sub Total -->
			                                                                                                            <col align="center" width="120"><!-- 55.Charges-->
			                                                                                                            <col align="center" width="120"><!-- 56.Tax-->
			                                                                                                            <col align="center" width="120"><!-- 57.Line Total-->
			                                                                                                            <tr>
			                                                                                                                <td class="pClothBs">Qty Order</td>
			                                                                                                                <td class="pClothBs">Sell Price</td>
			                                                                                                                <td class="pClothBs">Net Price </td>
			                                                                                                                <td class="pClothBs">Status</td>
			                                                                                                                <td class="pClothBs">Line Category</td>
			                                                                                                                <td class="pClothBs">C/R</td>
			                                                                                                                <td class="pClothBs">Warehouse(*)</td>
			                                                                                                                <td class="pClothBs">Sub WH(*)</td>
			                                                                                                                <td class="pClothBs">Line Source</td>
			                                                                                                                <td class="pClothBs">UOM</td>
			                                                                                                                <td class="pClothBs">Price List</td>
			                                                                                                                <td class="pClothBs">Unit List Price</td>
			                                                                                                                <td class="pClothBs">Floor Price List</td>
			                                                                                                                <td class="pClothBs">Floor Price</td>
			                                                                                                                <td class="pClothBs">Serial#</td>
			                                                                                                                <td class="pClothBs">Machine Master ID</td>
			                                                                                                                <td class="pClothBs">Substitute Item(*)</td>
			                                                                                                                <td class="pClothBs">Customer Item</td>
			                                                                                                                <td class="pClothBs">Req. Delivery Date</td>
			                                                                                                                <td class="pClothBs">Pricing Date</td>
			                                                                                                                <td class="pClothBs">Booked Date</td>
			                                                                                                                <td class="pClothBs">Shipped Date</td>
			                                                                                                                <td class="pClothBs">Delivery Scheduled Date</td>
			                                                                                                                <td class="pClothBs">QTY B/O</td>
			                                                                                                                <td class="pClothBs">QTY Allocated</td>
			                                                                                                                <td class="pClothBs">QTY Shipped</td>
			                                                                                                                <td class="pClothBs">QTY Invoiced</td>
			                                                                                                                <td class="pClothBs">QTY Cancelled</td>
			                                                                                                                <td class="pClothBs">Cancel Reason</td>
			                                                                                                                <td class="pClothBs">Supd. Lock</td>
			                                                                                                                <td class="pClothBs">Price Config#</td>
			                                                                                                                <td class="pClothBs">Picing Config Description</td>
			                                                                                                                <td class="pClothBs">Merchandise Type</td>
			                                                                                                                <td class="pClothBs">Product Code</td>
			                                                                                                                <td class="pClothBs">Item Classification</td>
			                                                                                                                <td class="pClothBs">Manufacturer#</td>
			                                                                                                                <td class="pClothBs">Line Ref#</td>
			                                                                                                                <td class="pClothBs">Customer PO#</td>
			                                                                                                                <td class="pClothBs">Customer PO Date</td>
			                                                                                                                <td class="pClothBs">Order Source Name</td>
			                                                                                                                <td class="pClothBs">Order Source Reference</td>
			                                                                                                                <td class="pClothBs">Line Source Reference</td>
			                                                                                                                <td class="pClothBs">BOM Parent Line#</td>
			                                                                                                                <td class="pClothBs">BOM Parent Version#</td>
			                                                                                                                <td class="pClothBs">BOM Parent Item Code</td>
			                                                                                                                <td class="pClothBs">Created By</td>
			                                                                                                                <td class="pClothBs">Creation Date</td>
			                                                                                                                <td class="pClothBs">Last Update By</td>
			                                                                                                                <td class="pClothBs">Last Update Date</td>
			                                                                                                                <td class="pClothBs">Sub Total </td>
			                                                                                                                <td class="pClothBs">Charges</td>
			                                                                                                                <td class="pClothBs">Tax</td>
			                                                                                                                <td class="pClothBs">Line Total</td>
			                                                                                                        	</tr>
			                                                                                                   		</table>
			                                                                                                        <% } %>
																												</td>
																											</tr>
																											<tr>
																												<td>
																													<table border="1" cellpadding="1" cellspacing="0" width="7370" style="table-layout:fixed;">
																														<col align="center" width="80" ><!-- 5.Qty Order-->
																														<col align="center" width="110"><!-- 6.Sell Price-->
																														<col align="center" width="110"><!-- 7.Net Price -->
																														<col align="center" width="200"><!-- 8.Status-->
																														<col align="center" width="180"><!-- 9.Line Category-->
																														<col align="center" width="50" ><!-- 10.Credit & Rebill Flag-->
																														<col align="center" width="180"><!-- 11.Warehouse-->
																														<col align="center" width="80" ><!-- 12.Sub Warehouse-->
																														<col align="center" width="190"><!-- 13.Line Source-->
																														<col align="center" width="80" ><!-- 14.UOM-->
																														<col align="center" width="185"><!-- 15.Price List-->
																														<col align="center" width="130"><!-- 16.Unit List Price-->
																														<col align="center" width="185"><!-- 17.Floor Price List-->
																														<col align="center" width="130"><!-- 18.Unit List Price-->
																														<col align="center" width="160"><!-- 19.Serial Number-->
																														<col align="center" width="110"><!-- 20'.Machine Master ID-->
																														<col align="center" width="110"><!-- 21.Substitute Item Code-->
																														<col align="center" width="110"><!-- 22.Customer Item Code-->
																														<col align="center" width="120"><!-- 23.Customer Requested Delivery Date-->
																														<col align="center" width="120"><!-- 24.Pricing Date-->
																														<col align="center" width="180"><!-- 25.Booked Date & Time-->
																														<col align="center" width="170"><!-- 26.Shipped Date & Time-->
																														<col align="center" width="170"><!-- 27.Delivery Scheduled Date-->
																														<col align="center" width="90" ><!-- 28.5.QTY B/O-->
																														<col align="center" width="90" ><!-- 29.QTY Allocated-->
																														<col align="center" width="90" ><!-- 30.QTY Shipped-->
																														<col align="center" width="90" ><!-- 31.QTY Invoiced-->
																														<col align="center" width="90" ><!-- 32.QTY Cancelled-->
																														<col align="center" width="200"><!-- 33.Cancel Reason Code-->
																														<col align="center" width="80" ><!-- 34.Superseed Lock-->
																														<col align="center" width="80" ><!-- 35.Pricing Config Number-->
																														<col align="center" width="200"><!-- 36.Picing Config Description-->
																														<col align="center" width="180"><!-- 37.Merchandise Type-->
																														<col align="center" width="180"><!-- 38.Product Code-->
																														<col align="center" width="180"><!-- 39.Item Classification-->
																														<col align="center" width="180"><!-- 40.Manufacturer Number-->
																														<col align="center" width="100"><!-- 41.Line Reference-->
																														<col align="center" width="180"><!-- 42.Customer PO#-->
																														<col align="center" width="180"><!-- 43.Customer PO Date-->
																														<col align="center" width="180"><!-- 44.Order Source Name-->
																														<col align="center" width="180"><!-- 45.Order Source Reference-->
																														<col align="center" width="180"><!-- 46.Line Source Reference-->
																														<col align="center" width="180"><!-- 47.BOM Parent Line#-->
																														<col align="center" width="180"><!-- 48.BOM Parent Version#-->
																														<col align="center" width="180"><!-- 49.BOM Parent Item Code-->
																														<col align="center" width="180"><!-- 50.Created By -->
																														<col align="center" width="180"><!-- 51.Creation Date & Time-->
																														<col align="center" width="180"><!-- 52.Last Update By -->
																														<col align="center" width="180"><!-- 53.Last Update Date & Time-->
																														<col align="center" width="120"><!-- 54.Sub Total -->
																														<col align="center" width="120"><!-- 55.Charges-->
																														<col align="center" width="120"><!-- 56.Tax-->
																														<col align="center" width="120"><!-- 57.Line Total-->
																														
																														<% for ( int j = lineIdx_rt; j < bMsg_rt.B.getValidCount(); j++ ) { %>
																														
																															<%
																																NWAL1500_ABMsg configLineMsg = bMsg_rt.A.no(i);
																																String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_LC.getValue();
																																
																																String lineDsOrdPosnNum = bMsg_rt.B.no(j).dsOrdPosnNum_LL.getValue();
																																if (!dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
																																	break;
																																}
																																lineIdx_rt++;
																																
																																if (isSmryLine) {
																																	pageContext.setAttribute("ordCustUomQty_LL", bMsg_rt.B.no(j).ordCustUomQty_LL.getValue());
																																	pageContext.setAttribute("entCpoDtlDealSlsAmt_LL", bMsg_rt.B.no(j).entCpoDtlDealSlsAmt_LL.getValue());
																																	pageContext.setAttribute("entDealNetUnitPrcAmt_LL", bMsg_rt.B.no(j).entDealNetUnitPrcAmt_LL.getValue());
																																	pageContext.setAttribute("ordLineStsDescTxt_LL", bMsg_rt.B.no(j).ordLineStsDescTxt_LL.getValue());
																																	pageContext.setAttribute("ordLineStsDescTxt_LD", bMsg_rt.B.no(j).ordLineStsDescTxt_LD.getValue());
																																	pageContext.setAttribute("dsOrdLineCatgCd_LL", bMsg_rt.B.no(j).dsOrdLineCatgCd_LL.getValue());
																																	pageContext.setAttribute("crRebilCd_LL", bMsg_rt.B.no(j).crRebilCd_LL.getValue());
																																	pageContext.setAttribute("crRebilDescTxt_LL", bMsg_rt.B.no(j).crRebilDescTxt_LL.getValue());
																																	pageContext.setAttribute("rtlWhNm_LL", bMsg_rt.B.no(j).rtlWhNm_LL.getValue());
																																	pageContext.setAttribute("rtlSwhNm_LL", bMsg_rt.B.no(j).rtlSwhNm_LL.getValue());
																																	pageContext.setAttribute("ordLineSrcCd_LL", bMsg_rt.B.no(j).ordLineSrcCd_LL.getValue());
																																	pageContext.setAttribute("custUomCd_LL", bMsg_rt.B.no(j).custUomCd_LL.getValue());
																																	pageContext.setAttribute("prcCatgNm_LL", bMsg_rt.B.no(j).prcCatgNm_LL.getValue());
																																	pageContext.setAttribute("flPrcListNm_LL", bMsg_rt.B.no(j).flPrcListNm_LL.getValue());
																																	pageContext.setAttribute("dealPrcListPrcAmt_LL", bMsg_rt.B.no(j).dealPrcListPrcAmt_LL.getValue());
																																	pageContext.setAttribute("funcUnitFlPrcAmt_LL", bMsg_rt.B.no(j).funcUnitFlPrcAmt_LL.getValue());
																																	pageContext.setAttribute("serNum_LL", bMsg_rt.B.no(j).serNum_LL.getValue());
																																	pageContext.setAttribute("svcMachMstrPk_LL", bMsg_rt.B.no(j).svcMachMstrPk_LL.getValue());
																																	pageContext.setAttribute("sbstMdseCd_LL", bMsg_rt.B.no(j).sbstMdseCd_LL.getValue());
																																	pageContext.setAttribute("custMdseCd_LL", bMsg_rt.B.no(j).custMdseCd_LL.getValue());
																																	pageContext.setAttribute("ordBookTsDplyTxt_LL", bMsg_rt.B.no(j).ordBookTsDplyTxt_LL.getValue());
																																	pageContext.setAttribute("shipDtTsDplyTxt_LL", bMsg_rt.B.no(j).shipDtTsDplyTxt_LL.getValue());
																																	pageContext.setAttribute("schdDelyTsDplyTxt_LL", bMsg_rt.B.no(j).schdDelyTsDplyTxt_LL.getValue());
																																	pageContext.setAttribute("boQty_LL", bMsg_rt.B.no(j).boQty_LL.getValue());
																																	pageContext.setAttribute("allocQty_LL", bMsg_rt.B.no(j).allocQty_LL.getValue());
																																	pageContext.setAttribute("shipQty_LL", bMsg_rt.B.no(j).shipQty_LL.getValue());
																																	pageContext.setAttribute("invQty_LL", bMsg_rt.B.no(j).invQty_LL.getValue());
																																	pageContext.setAttribute("cancQty_LL", bMsg_rt.B.no(j).cancQty_LL.getValue());
																																	pageContext.setAttribute("cancRsnDescTxt_LL", bMsg_rt.B.no(j).cancRsnDescTxt_LL.getValue());
																																	pageContext.setAttribute("supdLockFlg_LL", bMsg_rt.B.no(j).supdLockFlg_LL.getValue());
																																	pageContext.setAttribute("prcListEquipConfigNum_LL", bMsg_rt.B.no(j).prcListEquipConfigNum_LL.getValue());
																																	pageContext.setAttribute("prcListEquipConfigNm_LL", bMsg_rt.B.no(j).prcListEquipConfigNm_LL.getValue());
																																	pageContext.setAttribute("coaMdseTpDescTxt_LL", bMsg_rt.B.no(j).coaMdseTpDescTxt_LL.getValue());
																																	pageContext.setAttribute("coaProdDescTxt_LL", bMsg_rt.B.no(j).coaProdDescTxt_LL.getValue());
																																	pageContext.setAttribute("mdseItemClsTpDescTxt_LL", bMsg_rt.B.no(j).mdseItemClsTpDescTxt_LL.getValue());
																																	pageContext.setAttribute("mnfItemCd_LL", bMsg_rt.B.no(j).mnfItemCd_LL.getValue());
																																	pageContext.setAttribute("dplyLineRefNum_LL", bMsg_rt.B.no(j).dplyLineRefNum_LL.getValue());
																																	pageContext.setAttribute("custIssPoNum_LL", bMsg_rt.B.no(j).custIssPoNum_LL.getValue());
																																	pageContext.setAttribute("custIssPoDt_LL", bMsg_rt.B.no(j).custIssPoDt_LL.getValue());
																																	pageContext.setAttribute("cpoSrcTpDescTxt_LL", bMsg_rt.B.no(j).cpoSrcTpDescTxt_LL.getValue());
																																	pageContext.setAttribute("ordSrcRefNum_LL", bMsg_rt.B.no(j).ordSrcRefNum_LL.getValue());
																																	pageContext.setAttribute("lineSrcRefNum_LL", bMsg_rt.B.no(j).lineSrcRefNum_LL.getValue());
																																	pageContext.setAttribute("setPrntLineNum_LL", bMsg_rt.B.no(j).setPrntLineNum_LL.getValue());
																																	pageContext.setAttribute("cmpsnRevnNum_LL", bMsg_rt.B.no(j).cmpsnRevnNum_LL.getValue());
																																	pageContext.setAttribute("setMdseCd_LL", bMsg_rt.B.no(j).setMdseCd_LL.getValue());
																																	pageContext.setAttribute("cratUsrNm_LL", bMsg_rt.B.no(j).cratUsrNm_LL.getValue());
																																	pageContext.setAttribute("cratTsDplyTxt_LL", bMsg_rt.B.no(j).cratTsDplyTxt_LL.getValue());
																																	pageContext.setAttribute("updUsrNm_LL", bMsg_rt.B.no(j).updUsrNm_LL.getValue());
																																	pageContext.setAttribute("updTsDplyTxt_LL", bMsg_rt.B.no(j).updTsDplyTxt_LL.getValue());
																																	pageContext.setAttribute("lineDealSubTotAmt_LL", bMsg_rt.B.no(j).lineDealSubTotAmt_LL.getValue());
																																	pageContext.setAttribute("lineDealChrgAmt_LL", bMsg_rt.B.no(j).lineDealChrgAmt_LL.getValue());
																																	pageContext.setAttribute("lineDealTaxAmt_LL", bMsg_rt.B.no(j).lineDealTaxAmt_LL.getValue());
																																	pageContext.setAttribute("lineDealTotAmt_LL", bMsg_rt.B.no(j).lineDealTotAmt_LL.getValue());
																															%>
																																<input type="hidden" name="ordCustUomQty_LL" ezfName="" value="<c:out value='${ordCustUomQty_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="entCpoDtlDealSlsAmt_LL" ezfName="" value="<c:out value='${entCpoDtlDealSlsAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="entDealNetUnitPrcAmt_LL" ezfName="" value="<c:out value='${entDealNetUnitPrcAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="ordLineStsDescTxt_LL" ezfName="" value="<c:out value='${ordLineStsDescTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="ordLineStsDescTxt_LD" ezfName="" value="<c:out value='${ordLineStsDescTxt_LD}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="dsOrdLineCatgCd_LL" ezfName="" value="<c:out value='${dsOrdLineCatgCd_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="crRebilCd_LL" ezfName="" value="<c:out value='${crRebilCd_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="crRebilDescTxt_LL" ezfName="" value="<c:out value='${crRebilDescTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="rtlWhNm_LL" ezfName="" value="<c:out value='${rtlWhNm_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="rtlSwhNm_LL" ezfName="" value="<c:out value='${rtlSwhNm_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="ordLineSrcCd_LL" ezfName="" value="<c:out value='${ordLineSrcCd_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="custUomCd_LL" ezfName="" value="<c:out value='${custUomCd_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="prcCatgNm_LL" ezfName="" value="<c:out value='${prcCatgNm_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="flPrcListNm_LL" ezfName="" value="<c:out value='${flPrcListNm_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="dealPrcListPrcAmt_LL" ezfName="" value="<c:out value='${dealPrcListPrcAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="funcUnitFlPrcAmt_LL" ezfName="" value="<c:out value='${funcUnitFlPrcAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="serNum_LL" ezfName="" value="<c:out value='${serNum_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="svcMachMstrPk_LL" ezfName="" value="<c:out value='${svcMachMstrPk_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="sbstMdseCd_LL" ezfName="" value="<c:out value='${sbstMdseCd_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="custMdseCd_LL" ezfName="" value="<c:out value='${custMdseCd_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="rddDt_LL" ezfName="rddDt_LL" ezfHyo="B" ezfArrayNo="<%= j %>"/>
																																<input type="hidden" name="prcBaseDt_LL" ezfName="prcBaseDt_LL" ezfHyo="B" ezfArrayNo="<%= j %>"/>
																																<input type="hidden" name="ordBookTsDplyTxt_LL" ezfName="" value="<c:out value='${ordBookTsDplyTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="shipDtTsDplyTxt_LL" ezfName="" value="<c:out value='${shipDtTsDplyTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="schdDelyTsDplyTxt_LL" ezfName="" value="<c:out value='${schdDelyTsDplyTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="boQty_LL" ezfName="" value="<c:out value='${boQty_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="allocQty_LL" ezfName="" value="<c:out value='${allocQty_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="shipQty_LL" ezfName="" value="<c:out value='${shipQty_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="invQty_LL" ezfName="" value="<c:out value='${invQty_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="cancQty_LL" ezfName="" value="<c:out value='${cancQty_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="cancRsnDescTxt_LL" ezfName="" value="<c:out value='${cancRsnDescTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="supdLockFlg_LL" ezfName="" value="<c:out value='${supdLockFlg_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="prcListEquipConfigNum_LL" ezfName="" value="<c:out value='${prcListEquipConfigNum_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="prcListEquipConfigNm_LL" ezfName="" value="<c:out value='${prcListEquipConfigNm_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="coaMdseTpDescTxt_LL" ezfName="" value="<c:out value='${coaMdseTpDescTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="coaProdDescTxt_LL" ezfName="" value="<c:out value='${coaProdDescTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="mdseItemClsTpDescTxt_LL" ezfName="" value="<c:out value='${mdseItemClsTpDescTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="mnfItemCd_LL" ezfName="" value="<c:out value='${mnfItemCd_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="dplyLineRefNum_LL" ezfName="" value="<c:out value='${dplyLineRefNum_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="custIssPoNum_LL" ezfName="" value="<c:out value='${custIssPoNum_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="custIssPoDt_LL" ezfName="" value="<c:out value='${custIssPoDt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="cpoSrcTpDescTxt_LL" ezfName="" value="<c:out value='${cpoSrcTpDescTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="ordSrcRefNum_LL" ezfName="" value="<c:out value='${ordSrcRefNum_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="lineSrcRefNum_LL" ezfName="" value="<c:out value='${lineSrcRefNum_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="setPrntLineNum_LL" ezfName="" value="<c:out value='${setPrntLineNum_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="cmpsnRevnNum_LL" ezfName="" value="<c:out value='${cmpsnRevnNum_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="setMdseCd_LL" ezfName="" value="<c:out value='${setMdseCd_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="cratUsrNm_LL" ezfName="" value="<c:out value='${cratUsrNm_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="cratTsDplyTxt_LL" ezfName="" value="<c:out value='${cratTsDplyTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="updUsrNm_LL" ezfName="" value="<c:out value='${updUsrNm_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="updTsDplyTxt_LL" ezfName="" value="<c:out value='${updTsDplyTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="lineDealSubTotAmt_LL" ezfName="" value="<c:out value='${lineDealSubTotAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="lineDealChrgAmt_LL" ezfName="" value="<c:out value='${lineDealChrgAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="lineDealTaxAmt_LL" ezfName="" value="<c:out value='${lineDealTaxAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																<input type="hidden" name="lineDealTotAmt_LL" ezfName="" value="<c:out value='${lineDealTotAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															<%
																																	continue;
																																}
																															%>
																															<tr height="24">
																																<td><ezf:inputText name="ordCustUomQty_LL" ezfName="ordCustUomQty_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromQty" otherAttr=" size=\"6\" maxlength=\"10\" ezffocusout=\"OnBlur_DeriveFromQty\"" /></td>
																																<td><ezf:inputText name="entCpoDtlDealSlsAmt_LL" ezfName="entCpoDtlDealSlsAmt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromSellPrice" otherAttr=" size=\"13\" maxlength=\"13\" ezffocusout=\"OnBlur_DeriveFromSellPrice\""/></td>
																																<td align="right">
																																	<ezf:label name="entDealNetUnitPrcAmt_LL" ezfName="entDealNetUnitPrcAmt_LL" ezfHyo="B" ezfArrayNo="<%= j %>"/>
																																	<% pageContext.setAttribute("entDealNetUnitPrcAmt_LL", bMsg_rt.B.no(j).entDealNetUnitPrcAmt_LL.getValue()); %>
																																	<input type="hidden" name="entDealNetUnitPrcAmt_LL" ezfName="" value="<c:out value='${entDealNetUnitPrcAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="left">
																																	<ezf:label name="ordLineStsDescTxt_LD" ezfName="ordLineStsDescTxt_LD" ezfHyo="B" ezfArrayNo="<%= j %>"/>
																																	<% pageContext.setAttribute("ordLineStsDescTxt_LD", bMsg_rt.B.no(j).ordLineStsDescTxt_LD.getValue()); %>
																																	<input type="hidden" name="ordLineStsDescTxt_LD" ezfName="" value="<c:out value='${ordLineStsDescTxt_LD}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td>
																																	<div id="<%= j %>">
																																		<ezf:select name="dsOrdLineCatgCd_LL" ezfName="dsOrdLineCatgCd_LL" ezfHyo="B" ezfArrayNo="<%= j %>" ezfCodeName="dsOrdLineCatgCd_CD" ezfDispName="dsOrdLineCatgDescTxt_NM" otherAttr=" style=\"width:170\"" otherEvent1=" onchange=\"sendServer('OnChange_LineCategory',this.parentNode.id)\" ezfHyo=\"B\""/>
																																	</div>
																																</td>
																																<td align="left">
																																	<ezf:label name="crRebilDescTxt_LL" ezfName="crRebilDescTxt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("crRebilCd_LL", bMsg_rt.B.no(j).crRebilCd_LL.getValue()); %>
																																	<input type="hidden" name="crRebilCd_LL" ezfName="" value="<c:out value='${crRebilCd_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<% pageContext.setAttribute("crRebilDescTxt_LL", bMsg_rt.B.no(j).crRebilDescTxt_LL.getValue()); %>
																																	<input type="hidden" name="crRebilDescTxt_LL" ezfName="" value="<c:out value='${crRebilDescTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td>
																																	<table border="0" cellpadding="1" cellspacing="0">
																																		<tr style="padding:0;">
																																			<td><ezf:inputText name="rtlWhNm_LL" ezfName="rtlWhNm_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_ChangeWH" otherAttr=" size=\"20\" maxlength=\"60\" ezffocusout=\"OnBlur_ChangeWH\"" /></td>
																																			<td>
																																				<ezf:anchor name="xxImageTxt_BW" ezfName="xxImageTxt_BW" ezfHyo="B" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" id=\"xxImageTxt_BW\" ezfanchortext=\"\"">W</ezf:anchor>
																																			</td>
																																		</tr>
																																	</table>
																																</td>
																																<td>
																																	<table border="0" cellpadding="1" cellspacing="0">
																																		<tr style="padding:0;">
																																			<td><ezf:inputText name="rtlSwhNm_LL" ezfName="rtlSwhNm_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_ChangeSubWH" otherAttr=" size=\"7\" maxlength=\"50\" ezffocusout=\"OnBlur_ChangeSubWH\"" /></td>
																																			<td>
																																				<ezf:anchor name="xxImageTxt_BU" ezfName="xxImageTxt_BU" ezfHyo="B" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWarehouse" otherAttr=" id=\"xxImageTxt_BU\" ezfanchortext=\"\"">U</ezf:anchor>
																																			</td>
																																		</tr>
																																	</table>
																																</td>
																																
																																<td>
																																	<div id="<%= j %>">
																																		<ezf:select name="ordLineSrcCd_LL" ezfName="ordLineSrcCd_LL" ezfHyo="B" ezfArrayNo="<%= j %>" ezfCodeName="ordLineSrcCd_CD" ezfDispName="ordLineSrcNm_NM" otherAttr=" style=\"width:180\""  otherEvent1=" onchange=\"sendServer('OnChange_LineSource',this.parentNode.id)\" ezfHyo=\"B\""/>
																																	</div>
																																</td>
																																<td>
																																	<div id="<%= j %>">
																																		<ezf:select name="custUomCd_LL" ezfName="custUomCd_LL" ezfCodeName="pkgUomCd_CD" ezfDispName="pkgUomDescTxt_NM" ezfArrayNo="<%= j %>" ezfHyo="B" ezfCodeDispHyo="B" otherAttr=" style=\"width:70\"" otherEvent1=" onchange=\"sendServer('OnChange_UOM',this.parentNode.id)\" ezfHyo=\"B\"" />
																																	</div>
																																</td>
																																<td>
																																	<table border="0" cellpadding="1" cellspacing="0">
																																		<tr style="padding:0;">
																																			<td><ezf:inputText name="prcCatgNm_LL" ezfName="prcCatgNm_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromPriceList" otherAttr=" size=\"21\" maxlength=\"250\" ezffocusout=\"OnBlur_DeriveFromPriceList\"" /></td>
																																			<td>
																																				<ezf:anchor name="xxImageTxt_BP" ezfName="xxImageTxt_BP" ezfHyo="B" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_PriceList" otherAttr=" id=\"xxImageTxt_BP\" ezfanchortext=\"\"">P</ezf:anchor>
																																			</td>
																																		</tr>
																																	</table>
																																</td>
																																<td align="right">
																																	<ezf:label name="dealPrcListPrcAmt_LL" ezfName="dealPrcListPrcAmt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("dealPrcListPrcAmt_LL", bMsg_rt.B.no(j).dealPrcListPrcAmt_LL.getValue()); %>
																																	<input type="hidden" name="dealPrcListPrcAmt_LL" ezfName="" value="<c:out value='${dealPrcListPrcAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td>
																																	<table border="0" cellpadding="1" cellspacing="0">
																																		<tr style="padding:0;">
																																			<td><ezf:inputText name="flPrcListNm_LL" ezfName="flPrcListNm_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromFloorPriceList" otherAttr=" size=\"21\" maxlength=\"70\" ezffocusout=\"OnBlur_DeriveFromFloorPriceList\"" /></td>
																																			<td>
																																				<ezf:anchor name="xxImageTxt_BF" ezfName="xxImageTxt_BF" ezfHyo="B" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_FloorPriceList" otherAttr=" id=\"xxImageTxt_BF\" ezfanchortext=\"\"">F</ezf:anchor>
																																			</td>
																																		</tr>
																																	</table>
																																</td>
																																<td><ezf:inputText name="funcUnitFlPrcAmt_LL" ezfName="funcUnitFlPrcAmt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
																																<td>
																																	<ezf:inputText name="serNum_LL" ezfName="serNum_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromSerial" otherAttr=" size=\"21\" maxlength=\"30\" ezffocusout=\"OnBlur_DeriveFromSerial\""/>
																																</td>
																																<td>
																																	<ezf:inputText name="svcMachMstrPk_LL" ezfName="svcMachMstrPk_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"21\" maxlength=\"30\"  style=\"text-align: left; border:none; background-color:transparent\" ezftoupper=\"\""/>
																																</td>
																																<td>
																																	<table border="0" cellpadding="1" cellspacing="0">
																																		<tr style="padding:0;">
																																			<td><ezf:inputText name="sbstMdseCd_LL" ezfName="sbstMdseCd_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromSubstituteItem" otherAttr=" size=\"12\" maxlength=\"16\" ezffocusout=\"OnBlur_DeriveFromSubstituteItem\"" /></td>
																																			<td>
																																				<ezf:anchor name="xxImageTxt_BS" ezfName="xxImageTxt_BS" ezfHyo="B" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_SubstituteItem" otherAttr=" id=\"xxImageTxt_BS\" ezfanchortext=\"\"">S</ezf:anchor>
																																			</td>
																																		</tr>
																																	</table>
																																</td>
																																<td>
																																	<ezf:inputText name="custMdseCd_LL" ezfName="custMdseCd_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/>
																																</td>
																																
																																<td>
																																	<table border="0" cellpadding="1" cellspacing="0">
																																		<tr style="padding:0;">
																																			<td><ezf:inputText name="rddDt_LL" ezfName="rddDt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																																			<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rddDt_LL', 4, '<%= j %>');" ></td>
																																		</tr>
																																	</table>
																																</td>
																																<td>
																																	<table border="0" cellpadding="1" cellspacing="0">
																																		<tr style="padding:0;">
																																			<td><ezf:inputText name="prcBaseDt_LL" ezfName="prcBaseDt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																																			<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('prcBaseDt_LL', 4, '<%= j %>');" ></td>
																																		</tr>
																																	</table>
																																</td>
																																<td align="left">
																																	<ezf:label name="ordBookTsDplyTxt_LL" ezfName="ordBookTsDplyTxt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("ordBookTsDplyTxt_LL", bMsg_rt.B.no(j).ordBookTsDplyTxt_LL.getValue()); %>
																																	<input type="hidden" name="ordBookTsDplyTxt_LL" ezfName="" value="<c:out value='${ordBookTsDplyTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="left">
																																	<ezf:label name="shipDtTsDplyTxt_LL" ezfName="shipDtTsDplyTxt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("shipDtTsDplyTxt_LL", bMsg_rt.B.no(j).shipDtTsDplyTxt_LL.getValue()); %>
																																	<input type="hidden" name="shipDtTsDplyTxt_LL" ezfName="" value="<c:out value='${shipDtTsDplyTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="left">
																																	<ezf:label name="schdDelyTsDplyTxt_LL" ezfName="schdDelyTsDplyTxt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("schdDelyTsDplyTxt_LL", bMsg_rt.B.no(j).schdDelyTsDplyTxt_LL.getValue()); %>
																																	<input type="hidden" name="schdDelyTsDplyTxt_LL" ezfName="" value="<c:out value='${schdDelyTsDplyTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																
																																<td align="right">
																																	<ezf:label name="boQty_LL" ezfName="boQty_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("boQty_LL", bMsg_rt.B.no(j).boQty_LL.getValue()); %>
																																	<input type="hidden" name="boQty_LL" ezfName="" value="<c:out value='${boQty_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="right">
																																	<ezf:label name="allocQty_LL" ezfName="allocQty_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("allocQty_LL", bMsg_rt.B.no(j).allocQty_LL.getValue()); %>
																																	<input type="hidden" name="allocQty_LL" ezfName="" value="<c:out value='${allocQty_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="right">
																																	<ezf:label name="shipQty_LL" ezfName="shipQty_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("shipQty_LL", bMsg_rt.B.no(j).shipQty_LL.getValue()); %>
																																	<input type="hidden" name="shipQty_LL" ezfName="" value="<c:out value='${shipQty_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="right">
																																	<ezf:label name="invQty_LL" ezfName="invQty_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("invQty_LL", bMsg_rt.B.no(j).invQty_LL.getValue()); %>
																																	<input type="hidden" name="invQty_LL" ezfName="" value="<c:out value='${invQty_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="right">
																																	<ezf:label name="cancQty_LL" ezfName="cancQty_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("cancQty_LL", bMsg_rt.B.no(j).cancQty_LL.getValue()); %>
																																	<input type="hidden" name="cancQty_LL" ezfName="" value="<c:out value='${cancQty_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="left"><ezf:inputText name="cancRsnDescTxt_LL" ezfName="cancRsnDescTxt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"25\" maxlength=\"50\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																
																																<td><ezf:inputCheckBox name="supdLockFlg_LL" ezfName="supdLockFlg_LL" ezfHyo="B" ezfArrayNo="<%= j %>" value="Y"/></td>
																																<td><ezf:inputText name="prcListEquipConfigNum_LL" ezfName="prcListEquipConfigNum_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"8\" maxlength=\"3\" ezftoupper=\"\""/></td>
																																<td align="left"><ezf:inputText name="prcListEquipConfigNm_LL" ezfName="prcListEquipConfigNm_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"18\" maxlength=\"50\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																
																																</td>
																																<td align="left">
																																	<ezf:label name="coaMdseTpDescTxt_LL" ezfName="coaMdseTpDescTxt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("coaMdseTpDescTxt_LL", bMsg_rt.B.no(j).coaMdseTpDescTxt_LL.getValue()); %>
																																	<input type="hidden" name="coaMdseTpDescTxt_LL" ezfName="" value="<c:out value='${coaMdseTpDescTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="left">
																																	<ezf:label name="coaProdDescTxt_LL" ezfName="coaProdDescTxt_LL" ezfHyo="B" ezfArrayNo="<%= j %>"/>
																																	<% pageContext.setAttribute("coaProdDescTxt_LL", bMsg_rt.B.no(j).coaProdDescTxt_LL.getValue()); %>
																																	<input type="hidden" name="coaProdDescTxt_LL" ezfName="" value="<c:out value='${coaProdDescTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																
																																<td align="left"><ezf:inputText name="mdseItemClsTpDescTxt_LL" ezfName="mdseItemClsTpDescTxt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"18\" maxlength=\"50\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td align="left">
																																	<ezf:label name="mnfItemCd_LL" ezfName="mnfItemCd_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("mnfItemCd_LL", bMsg_rt.B.no(j).mnfItemCd_LL.getValue()); %>
																																	<input type="hidden" name="mnfItemCd_LL" ezfName="" value="<c:out value='${mnfItemCd_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td><ezf:inputText name="dplyLineRefNum_LL" ezfName="dplyLineRefNum_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromDplyLineRefNum" otherAttr=" size=\"8\" maxlength=\"15\"  ezffocusout=\"OnBlur_DeriveFromDplyLineRefNum\""/></td>
																																
																																<td align="left"><ezf:inputText name="custIssPoNum_LL" ezfName="custIssPoNum_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"15\" maxlength=\"60\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td align="left">
																																	<ezf:label name="custIssPoDt_LL" ezfName="custIssPoDt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("custIssPoDt_LL", bMsg_rt.B.no(j).custIssPoDt_LL.getValue()); %>
																																</td>
																																
																																<td align="left"><ezf:inputText name="cpoSrcTpDescTxt_LL" ezfName="cpoSrcTpDescTxt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"18\" maxlength=\"50\"  style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																																<td align="left"><ezf:inputText name="ordSrcRefNum_LL" ezfName="ordSrcRefNum_LL" ezfHyo="B" ezfArrayNo="<%= j %>"otherAttr="tabindex=\"-1\" size=\"18\" maxlength=\"50\"  style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td align="left"><ezf:inputText name="lineSrcRefNum_LL" ezfName="lineSrcRefNum_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"18\" maxlength=\"100\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																
																																<td align="left"><ezf:inputText name="setPrntLineNum_LL" ezfName="setPrntLineNum_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"18\" maxlength=\"65\" style=\"border:none; background-color:transparent\"   ezftoupper=\"\""/></td>
																																<td align="left"><ezf:inputText name="cmpsnRevnNum_LL" ezfName="cmpsnRevnNum_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"15\" maxlength=\"3\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td align="left"><ezf:inputText name="setMdseCd_LL" ezfName="setMdseCd_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"18\" maxlength=\"16\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																
																																<td align="left"><ezf:inputText name="cratUsrNm_LL" ezfName="cratUsrNm_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"25\" maxlength=\"93\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td align="left">
																																	<ezf:label name="cratTsDplyTxt_LL" ezfName="cratTsDplyTxt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("cratTsDplyTxt_LL", bMsg_rt.B.no(j).cratTsDplyTxt_LL.getValue()); %>
																																	<input type="hidden" name="cratTsDplyTxt_LL" ezfName="" value="<c:out value='${cratTsDplyTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="left"><ezf:inputText name="updUsrNm_LL" ezfName="updUsrNm_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"25\" maxlength=\"93\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td align="left">
																																	<ezf:label name="updTsDplyTxt_LL" ezfName="updTsDplyTxt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("updTsDplyTxt_LL", bMsg_rt.B.no(j).updTsDplyTxt_LL.getValue()); %>
																																	<input type="hidden" name="updTsDplyTxt_LL" ezfName="" value="<c:out value='${updTsDplyTxt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																
																																<td align="right">
																																	<ezf:label name="lineDealSubTotAmt_LL" ezfName="lineDealSubTotAmt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("lineDealSubTotAmt_LL", bMsg_rt.B.no(j).lineDealSubTotAmt_LL.getValue()); %>
																																	<input type="hidden" name="lineDealSubTotAmt_LL" ezfName="" value="<c:out value='${lineDealSubTotAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="right">
																																	<ezf:label name="lineDealChrgAmt_LL" ezfName="lineDealChrgAmt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("lineDealChrgAmt_LL", bMsg_rt.B.no(j).lineDealChrgAmt_LL.getValue()); %>
																																	<input type="hidden" name="lineDealChrgAmt_LL" ezfName="" value="<c:out value='${lineDealChrgAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="right">
																																	<ezf:label name="lineDealTaxAmt_LL" ezfName="lineDealTaxAmt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("lineDealTaxAmt_LL", bMsg_rt.B.no(j).lineDealTaxAmt_LL.getValue()); %>
																																	<input type="hidden" name="lineDealTaxAmt_LL" ezfName="" value="<c:out value='${lineDealTaxAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="right">
																																	<ezf:label name="lineDealTotAmt_LL" ezfName="lineDealTotAmt_LL" ezfHyo="B" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("lineDealTotAmt_LL", bMsg_rt.B.no(j).lineDealTotAmt_LL.getValue()); %>
																																	<input type="hidden" name="lineDealTotAmt_LL" ezfName="" value="<c:out value='${lineDealTotAmt_LL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																															</tr>
																														<% } %>
																													</table>
																												</td>
																											</tr>
																										</table>
                                                     									     	  	</td>
                                                                                           		 </tr>
                                                                                      		  </table>
																					<% } %>
																					<%-- +++++ [END] Programming JSP for Nesting Records Table +++++ --%>

																					<!-- /table -->
																				</div>

																			</td>
																		</tr>
																	</table>
																</td>
															</tr>

														</table>
													</td>
												</tr>
											</table>
<!-- Order Detail END -->
											<!-- Actions -->
											<table valign="bottom">
												<col width="1100">
												<td align="left">
													<fieldset>
														<table style="table-layout:fixed;">
															<col width="60">
															<col width="110">
															<col width="20">
															<col width="120">
															<col width="110">
															<col width="20">
															<col width="200">
															<col width="110">
															<col width="20">
															<col width="50">
															<col width="110">
															<td class="stab">Actions(<u>A</u>)</td>
															<td>
																<ezf:select name="ordEntryActCd_AC" ezfName="ordEntryActCd_AC" ezfBlank="1" ezfCodeName="ordEntryActCd_LA" ezfDispName="ordEntryActDescTxt_LA" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryAction')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"0\" accesskey=\"A\""/>
															</td>
															<td>&nbsp;</td>
															<td class="stab">Additional Information(<u>F</u>)</td>
															<td>
																<ezf:select name="ordEntryActCd_IF" ezfName="ordEntryActCd_IF" ezfBlank="1" ezfCodeName="ordEntryActCd_LI" ezfDispName="ordEntryActDescTxt_LI" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryActionAddlInfo')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"0\" accesskey=\"F\""/>
															</td>
															<td>&nbsp;</td>
															<td class="stab">Delivery/Install/Site Survey/Contacts(<u>C</u>)</td>
															<td>
																<ezf:select name="ordEntryActCd_DL" ezfName="ordEntryActCd_DL" ezfBlank="1" ezfCodeName="ordEntryActCd_LD" ezfDispName="ordEntryActDescTxt_LD" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryActionDelivery')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"0\" accesskey=\"C\""/>
															</td>
															<td>&nbsp;</td>
															<td class="stab">Tools(<u>T</u>)</td>
															<td>
																<ezf:select name="ordEntryActCd_TO" ezfName="ordEntryActCd_TO" ezfBlank="1" ezfCodeName="ordEntryActCd_LT" ezfDispName="ordEntryActDescTxt_LT" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryActionTool')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"0\" accesskey=\"T\""/>
															</td>
														</table>
													</fieldset>
												</td>
											</table>	
										</div>
									</c:when>
								</c:choose>

<%-- ##### TAB(RMA) ##### --%>
								<c:choose>
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='RMA'}">
										<script type="text/javascript">
											document.getElementById( "Header" ).className="pTab_OFF";
											document.getElementById( "Addl_Header" ).className="pTab_OFF";
											document.getElementById( "Line_Config" ).className="pTab_OFF";
											document.getElementById( "RMA" ).className="pTab_ON";
										</script>
										<div class="pTab_BODY_In">
<!-- Order Summary Start -->
											<table>
												<col valign="top" align="left">
												<col valign="top" align="left">
												<tr>
													<td>
														<fieldset>
															<legend style="font-size:12px;">Order Information</legend>
															<!-- table style="table-layout:fixed;" -->
															<table width=630 height = 50>
																<td valign="top">
																	<!-- table style="table-layout:fixed;" -->
																	<table width=210>
																		<col width="100">
																		<col width="100">
																		<tr height="20">
																			<td class="stab">Order Number</td>
																			<td class="pOut">
																				<ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="100123" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/>
																			</td>
																		</tr>
																		<tr height="20">
																			<td class="stab">Order Status</td>
																			<td class="pOut"><ezf:inputText name="ordHdrStsDescTxt" ezfName="ordHdrStsDescTxt" value="ENTERED" otherAttr=" size=\"15\" maxlength=\"50\" tabindex=\"1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																		</tr>
																	</table>
																</td>
																<td valign="top">
																	<!-- table style="table-layout:fixed;" -->
																	<table border="1" cellpadding="1" cellspacing="0" width=420>
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
																			<td class="pOut" align="right"><ezf:label name="xxTotBaseAmt_RT" ezfName="xxTotBaseAmt_RT" /></td>
																			<td class="pOut" align="right"><ezf:label name="xxTotFrtAmt_RT" ezfName="xxTotFrtAmt_RT" /></td>
																			<td class="pOut" align="right"><ezf:label name="xxTotTaxAmt_RT" ezfName="xxTotTaxAmt_RT" /></td>
																			<td class="pOut" align="right"><ezf:label name="xxTotAmt_RT" ezfName="xxTotAmt_RT" /></td>
																		</tr>
																	</table>
																</td>
															</table>
														</fieldset>
													</td>
													<td valign="top">
														<fieldset>
															<legend style="font-size:12px;">Ship To</legend>
															<table height="50" width="450">
																<td>
																<table>
																	<col width="100">
																	<col width="350">
																	<tr height="21">
																		<td class="stab">Account Name</td>
																		<td class="pOut"><ezf:label name="shipToCustAcctNm" ezfName="shipToCustAcctNm" /></td>
																	</tr>
																	<tr height="21">
																		<td class="stab">Account Number</td>
																		<td class="pOut"><ezf:label name="shipToCustAcctCd" ezfName="shipToCustAcctCd" /></td>
																	</tr>
																</table>
																</td>
															</table>
														</fieldset>
													</td>
												</tr>
												<td>
												<hr width="1125">
												</td>
											</table>
<!-- Order Summary End -->
<!-- RMA Page Component Start -->
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="300">
												<col align="left" width="">
												<col align="right" width="580">
												<tr>
													<td><!-- input type="hidden" size="0" value="" id="xxComnColOrdTxt" name="xxComnColOrdTxt" ezfname="xxComnColOrdTxt" --></td>
													<td>
														Config#
														<ezf:inputText name="dsOrdPosnNum_CS" ezfName="dsOrdPosnNum_CS" value="200" otherAttr=" size=\"4\" maxlength=\"3\""/>
														/
														<ezf:label name="dsOrdPosnNum_CT" ezfName="dsOrdPosnNum_CT" />
														<ezf:inputButton name="ConfigJump" value="Config Jump" htmlClass="pBtn8" />
													</td>
													<td align="right">
														<ezf:skip>
														<table border="0" cellpadding="0" width="100%">
															<tr>
																<td align="left">
																	<table border="0" cellpadding="0" align="left" cellspacing="0">
																		<col>
																			<tr>
																			<td>Results 1999 - 1999 of 2000</td>
																			</tr>
																	</table>
																</td>
																<td align="right">
																	<table border="0" cellpadding="0" cellspacing="0">
																		<col width="54"  align="center">
																		<col width="40"  align="center">
																		<col width="16"  align="center">
																		<col width="40"  align="center">
																		<col width="26"  align="center">
																		<col width="10">
																		<col>
																		<col width="20">
																		<col>
																		<col width="1">
																		<col>
																		<tr>
																			<td class="stab">Showing</td>
																			<td><input type="text" name="xxPageShowCurNum_RL" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																			<td class="stab">/</td>
																			<td><input type="text" name="xxPageShowTotNum_RL" ezfName="xxPageShowTotNum_RL" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																			<td class="stab">page</td>
																			<td>&nbsp;</td>
																			<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'D')"></td>
																			<td></td>
																			<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'D')"></td>
																			<td></td>
																			<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'D')"></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
														</ezf:skip>
														<table width="100%">
															<tr align="right">
																<td>
																	<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																		<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
																		<jsp:param name="TableNm"           value="D" />
																		<jsp:param name="ShowingFrom"       value="xxPageShowFromNum_RL" />
																		<jsp:param name="ShowingTo"         value="xxPageShowToNum_RL" />
																		<jsp:param name="ShowingOf"         value="xxPageShowOfNum_RL" />
																		<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum_RL" />
																		<jsp:param name="ShowingTotal"      value="xxPageShowTotNum_RL" />
																		<jsp:param name="ViewMode"          value="FULL" />
																	</jsp:include>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
<!-- RMA Page Component End -->
<!-- RMA Detail Start -->
											<table width="100%">
												<col valign="top">
												<tr>
													<td>
														<table border="0">
															<tr>
																<td valign="top">
																	<table width="100%" cellpadding="0" cellspacing="0" border="0">
																		<col align="right" width="510">
																		<col align="left"  width="598">
																		<col width="">
																		<tr>
																			<td valign="top">

																				<%-- LEFT-TABLE(TOP) --%>
																				<div id="leftTBL" style="overflow-y:hidden; width:512; height:340; overflow-x:hidden;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));">
																					<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
																					<%@ page import="business.servlet.NWAL1500.NWAL1500BMsg" %>
																					<%@ page import="business.servlet.NWAL1500.NWAL1500_CBMsg" %>
																					<%@ page import="business.servlet.NWAL1500.NWAL1500_DBMsg" %>
																					<%@ page import="com.canon.cusa.s21.framework.common.S21StringUtil" %>
																					<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPConstant" %>
																					<% NWAL1500BMsg bMsg = (NWAL1500BMsg)databean.getEZDBMsg(); 
																						int lineIdx = 0;
																					%>

																					<% for ( int i = 0; i < bMsg.C.getValidCount(); i++ ) { %> 

																						<table border="0" cellpadding="0" cellspacing="0" width="510" style="margin-top: 2px;">
																							<tr>
																								<td align="right">
																									<% NWAL1500_CBMsg lineMsg = bMsg.C.no(i); %>
																									<% boolean isSmryLine = "Y".equals(lineMsg.xxSmryLineFlg_R.getValue()); %>
																										
																										<table border="1" cellpadding="1" cellspacing="0" width="510" style="table-layout:fixed;" >
																											<col align="center" width="30"> <!-- 1. Collapse Mark -->
																											<col align="center" width="30"> <!-- 2. Check Box: Config -->
																											<col align="center" width="60"> <!-- 3. Config# -->
																											<col align="center" width="150"><!-- 4. Config Type -->
																											<col align="center" width="140"><!-- 5. Model -->
																											<col align="center" width="100"><!-- 6. Config Id -->
																											<tr>
																												<td class="pClothBs">&nbsp</td>
																												<td class="pClothBs">&nbsp</td>
																												<td class="pClothBs">Config#</td>
																												<td class="pClothBs">Config Action</td>
																												<td class="pClothBs">Model</td>
																												<td class="pClothBs">Config ID</td>
																											</tr>
																										</table>
																										
																										<table border="1" cellpadding="1" cellspacing="0" width="510" style="table-layout:fixed;" >
																											<col align="center" width="30"> <!-- 1. Collapse Mark -->
																											<col align="center" width="30"> <!-- 2. Check Box: Config -->
																											<col align="center" width="60"> <!-- 3. Config# -->
																											<col align="center" width="150"><!-- 4. Config Type -->
																											<col align="center" width="140"><!-- 5. Model -->
																											<col align="center" width="100"><!-- 6. Config Id -->
																											<tr height="24">
																												<td bgcolor="#bfd8d8">
																													<% if (isSmryLine) { %>
																														<img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('Line_Expand','<%= i %>')" ezfHyo="C" height="14">
																													<% } else if (!isSmryLine) { %>
																														<img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('Line_Collapsed','<%= i %>')" ezfHyo="C" height="14">
																													<% } %>
																												</td>
																												<td bgcolor="#bfd8d8">
																													<% pageContext.setAttribute("xxResFltrFlg_RC", bMsg.C.no(i).xxResFltrFlg_RC.getValue()); %>
																													<input type="hidden" name="xxResFltrFlg_RC" ezfName="" value="<c:out value='${xxResFltrFlg_RC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																													<ezf:inputCheckBox name="xxChkBox_RC" ezfName="xxChkBox_RC" value="Y" ezfHyo="C" ezfArrayNo="<%= i %>"/>
																												</td>
																												<td bgcolor="#bfd8d8" align="center">
																													<ezf:label name="dsOrdPosnNum_RC" ezfName="dsOrdPosnNum_RC" ezfHyo="C" ezfArrayNo="<%= i %>"/>
																													<% pageContext.setAttribute("dsOrdPosnNum_RC", bMsg.C.no(i).dsOrdPosnNum_RC.getValue()); %>
																													<input type="hidden" name="dsOrdPosnNum_RC" ezfName="" value="<c:out value='${dsOrdPosnNum_RC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																												</td>
																												<td bgcolor="#bfd8d8" align="center">
																													<span id="<%= i %>">
																														<ezf:select name="configTpCd_RC" ezfName="configTpCd_RC" ezfHyo="C" ezfArrayNo="<%= i %>" ezfCodeName="configTpCd_RD" ezfDispName="configTpDescTxt_RD" otherEvent1="onchange=\"sendServer('OnChange_RmaConfigAction', this.parentNode.id);\"" otherAttr=" style=\"width:135\""/>
																													</span>
																												</td>
																												<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="mdlNm_RC" ezfName="mdlNm_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"-1\" size=\"18\" maxlength=\"90\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																												<td bgcolor="#bfd8d8">
																													<table border="0" cellpadding="1" cellspacing="0">
																														<tr style="padding:0;">
																															<td><ezf:inputText name="svcConfigMstrPk_RC" ezfName="svcConfigMstrPk_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr=" size=\"10\" maxlength=\"28\" style=\"text-align: left;\" ezftoupper=\"\""/></td>
																															<td>
																																<ezf:anchor name="xxImageTxt_CC" ezfName="xxImageTxt_CC" ezfHyo="C" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ConfigID" otherAttr=" id=\"xxImageTxt_CC\" ezfanchortext=\"\"">C</ezf:anchor>
																															</td>
																														</tr>
																													</table>
																												</td>
																											</tr>
																										</table>
																										<% if (!isSmryLine) { %>
																										<table border="1" cellpadding="1" cellspacing="0" width="450" style="table-layout:fixed; margin-top: 2px">
																											<col align="center" width="30"> <!-- 1. Check Box -->
																											<col align="center" width="60"> <!-- 2. Line# -->
																											<col align="center" width="120"><!-- 3. Item#(*) -->
																											<col align="center" width="240"><!-- 4. Item Description -->
																											<tr>
																												<td class="pClothBs" >&nbsp</td>
																												<td class="pClothBs" >Line#</td>
																												<td class="pClothBs" >Item#(*)</td>
																												<td class="pClothBs" >Item Description</td>
																											</tr>
																										</table>
																										<% } %>
																								</td>
																							</tr>
																							<tr>
																								<td align="right">
																									<table border="1" cellpadding="1" cellspacing="0" width="450" align="right" style="table-layout:fixed;" >
																										<col align="center" width="30"> <!-- 1. Check Box -->
																										<col align="center" width="60"> <!-- 2. Line# -->
																										<col align="center" width="120"><!-- 3. Item#(*) -->
																										<col align="center" width="240"><!-- 4. Item Description -->
																										
																										<% for ( int j = lineIdx; j < bMsg.D.getValidCount(); j++ ) { %>
																										
																											<%
																												NWAL1500_CBMsg configLineMsg = bMsg.C.no(i);
																												String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_RC.getValue();
																												
																												String lineDsOrdPosnNum = bMsg.D.no(j).dsOrdPosnNum_RL.getValue();
																												if (!dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
																													break;
																												}
																												lineIdx++;
																												
																												if (isSmryLine) {
																													pageContext.setAttribute("xxResFltrFlg_RL", bMsg.D.no(j).xxResFltrFlg_RL.getValue());
																													pageContext.setAttribute("xxChkBox_RL", bMsg.D.no(j).xxChkBox_RL.getValue());
																													pageContext.setAttribute("xxLineNum_RL", bMsg.D.no(j).xxLineNum_RL.getValue());
																													pageContext.setAttribute("mdseCd_RL", bMsg.D.no(j).mdseCd_RL.getValue());
																													pageContext.setAttribute("mdseDescShortTxt_RL", bMsg.D.no(j).mdseDescShortTxt_RL.getValue());
																											%>

																													<input type="hidden" name="xxResFltrFlg_RL" ezfName="" value="<c:out value='${xxResFltrFlg_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																													<input type="hidden" name="xxChkBox_RL" ezfName="" value="<c:out value='${xxChkBox_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																													<input type="hidden" name="xxLineNum_RL" ezfName="" value="<c:out value='${xxLineNum_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																													<input type="hidden" name="mdseCd_RL" ezfName="" value="<c:out value='${mdseCd_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																													<input type="hidden" name="mdseDescShortTxt_RL" ezfName="" value="<c:out value='${mdseDescShortTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																											<%
																													continue;
																												}
																											%>

																											<tr height="24">
																												<td>
																													<% pageContext.setAttribute("xxResFltrFlg_RL", bMsg.D.no(j).xxResFltrFlg_RL.getValue()); %>
																													<input type="hidden" name="xxResFltrFlg_RL" ezfName="" value="<c:out value='${xxResFltrFlg_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																													<ezf:inputCheckBox name="xxChkBox_RL" ezfName="xxChkBox_RL" value="Y" ezfHyo="D" ezfArrayNo="<%= j %>"/>
																												</td>
																												<td>
																													<ezf:label name="xxLineNum_RL" ezfName="xxLineNum_RL" ezfHyo="D" ezfArrayNo="<%= j %>"/>
																													<% pageContext.setAttribute("xxLineNum_RL", bMsg.D.no(j).xxLineNum_RL.getValue()); %>
																													<input type="hidden" name="xxLineNum_RL" ezfName="" value="<c:out value='${xxLineNum_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																												</td>
																												<td>
																													<table border="0" cellpadding="1" cellspacing="0">
																														<tr style="padding:0;">
																															<td><ezf:inputText name="mdseCd_RL" ezfName="mdseCd_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromItem" otherAttr=" size=\"12\" maxlength=\"16\" ezffocusout=\"OnBlur_DeriveFromItem\"" /></td>
																															<td>
																																<ezf:anchor name="xxImageTxt_DI" ezfName="xxImageTxt_DI" ezfHyo="D" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_Merchandise" otherAttr=" id=\"xxImageTxt_DI\" ezfanchortext=\"\"">I</ezf:anchor>
																															</td>
																														</tr>
																													</table>
																												</td>
																												<td align="left"><ezf:inputText name="mdseDescShortTxt_RL" ezfName="mdseDescShortTxt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"30\" maxlength=\"250\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																											</tr>
																										<% } %>
																									<%-- +++++ [END] Programming JSP for Nesting Records Table +++++ --%>
																									</table>
																								</td>
																							</tr>
																						</table>
																					<% } %>
																				</div>
																			</td>

																			<td valign="top">

																				<!-- RIGHT-TABLE(TOP) -->
																				<div id="rightTBL" style="overflow-y:scroll; height:360; overflow-x:scroll; width:598;" onscroll="setScrollPosition();synchroScrollTop(this.id, new Array('leftTBL'));synchroScrollLeft(this.id, new Array('rightTopTBL'));">

																					<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
																					<%@ page import="business.servlet.NWAL1500.NWAL1500BMsg" %>
																					<%@ page import="business.servlet.NWAL1500.NWAL1500_CBMsg" %>
																					<%@ page import="business.servlet.NWAL1500.NWAL1500_DBMsg" %>
																					<%@ page import="com.canon.cusa.s21.framework.common.S21StringUtil" %>
																					<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPConstant" %>
																					<% NWAL1500BMsg bMsg_rt = (NWAL1500BMsg)databean.getEZDBMsg(); 
																						int lineIdx_rt = 0;
																					%>

																					<% for ( int i = 0; i < bMsg_rt.C.getValidCount(); i++ ) { %>
																						<table border="0" cellpadding="0" cellspacing="0"  style="margin-top:2px;">
																							<tr>
																								<td align="left">
																									
																									<table border="1" cellpadding="1" cellspacing="0" width="4100" style="table-layout:fixed;" >
																										<col align="center" width="300"><!-- 7.Ship To Address-->
																										<col align="center" width="100"><!-- 8.Ship To Acct#-->
																										<col align="center" width="100"><!-- 9.Ship To Loc#-->
																										<col align="center" width="190"><!-- 10.Ship To Name-->
																										<col align="center" width="180"><!-- 11.DS Name-->
																										<col align="center" width="310"><!-- 12.Bill To Address-->
																										<col align="center" width="100"><!-- 13.Bill To Acct#-->
																										<col align="center" width="100"><!-- 14.Bill To Loc#-->
																										<col align="center" width="180"><!-- 15.Bill TO Name-->
																										<col align="center" width="300"><!-- 16.Sold to address -->
																										<col align="center" width="90" ><!-- 17.Sold To Number-->
																										<col align="center" width="90" ><!-- 18.Sold To Loc Number-->
																										<col align="center" width="180"><!-- 19.Sold To Name-->
																										<col align="center" width="110"><!-- 20.Model Group-->
																										<col align="center" width="170"><!-- 21.Model Description-->
																										<col align="center" width="170"><!-- 22.Segment-->
																										<col align="center" width="80" ><!-- 23.Install Required for Revenue Y/N-->
																										<col align="center" width="90" ><!-- 24.Site Survey Req-->
																										<col align="center" width="90" ><!-- 25.Sales Credit Splits  (Y/N)-->
																										<col align="center" width="90" ><!-- 26.On Hold (Y/N)-->
																										<col align="center" width="190"><!-- 27.Created By (User ID & Name)-->
																										<col align="center" width="160"><!-- 28.Creation Date & Time-->
																										<col align="center" width="190"><!-- 29.Last Update By (ID & Name)-->
																										<col align="center" width="160"><!-- 30.Last Update Date & Time-->
																										<col align="center" width="120"><!-- 31.Sub Total (Sum the Lines inside the config)-->
																										<col align="center" width="120"><!-- 32.Charges-->
																										<col align="center" width="120"><!-- 33.Tax-->
																										<col align="center" width="120"><!-- 34.Config  Total-->
																										<col align="center" width="80" ><!-- 35.DS-->
																										<col align="center" width="180"><!-- 36.CSMP Number-->
																										<col align="center" width="200"><!-- 37.CSA Dealer Ref#-->
																										<col align="center" width="180"><!-- 38.CSMP Price List-->
																										<tr>
																											<td class="pClothBs">Ship To Address</td>
																											<td class="pClothBs">Ship To Acct#(*)</td>
																											<td class="pClothBs">Ship To Code(*)</td>
																											<td class="pClothBs">Ship To Name(*)</td>
																											<td class="pClothBs">DS Name</td>
																											<td class="pClothBs">Bill To Address</td>
																											<td class="pClothBs">Bill To Acct#(*)</td>
																											<td class="pClothBs">Bill To Code(*)</td>
																											<td class="pClothBs">Bill To Name(*)</td>
																											<td class="pClothBs">Sold to Address</td>
																											<td class="pClothBs">Sold To#</td>
																											<td class="pClothBs">Sold To Code</td>
																											<td class="pClothBs">Sold To Name</td>
																											<td class="pClothBs">Model Group</td>
																											<td class="pClothBs">Model Description</td>
																											<td class="pClothBs">Segment</td>
																											<td class="pClothBs">Install Req.</td>
																											<td class="pClothBs">Site Sur.</td>
																											<td class="pClothBs">Sls CR Splits</td>
																											<td class="pClothBs">On Hold</td>
																											<td class="pClothBs">Created By</td>
																											<td class="pClothBs">Creation Date</td>
																											<td class="pClothBs">Last Update By</td>
																											<td class="pClothBs">Last Update Date</td>
																											<td class="pClothBs">Sub Total</td>
																											<td class="pClothBs">Charges</td>
																											<td class="pClothBs">Tax</td>
																											<td class="pClothBs">Config Total</td>
																											<td class="pClothBs">DS</td>
																											<td class="pClothBs">CSMP Number</td>
																											<td class="pClothBs">CSA Dealer Ref#</td>
																											<td class="pClothBs">CSMP Price List</td>
																										</tr>
																									</table>
																									
																									<table border="0" cellpadding="0" cellspacing="0" id="A2"">
			                                                                                            <tr>
			                                                                                                <td>
			                                                                                                	<% NWAL1500_CBMsg lineMsg_rt = bMsg_rt.C.no(i); %>
			                                                                                                	<% boolean isSmryLine = "Y".equals(lineMsg_rt.xxSmryLineFlg_R.getValue()); %>
																													<table border="1" cellpadding="1" cellspacing="0" width="4100" style="table-layout:fixed;">
																														<col align="center" width="300"><!-- 7.Ship To Address-->
																														<col align="center" width="100"><!-- 8.Ship To Acct#-->
																														<col align="center" width="100"><!-- 9.Ship To Loc#-->
																														<col align="center" width="190"><!-- 10.Ship To Name-->
																														<col align="center" width="180"><!-- 11.DS Name-->
																														<col align="center" width="310"><!-- 12.Bill To Address-->
																														<col align="center" width="100"><!-- 13.Bill To Acct#-->
																														<col align="center" width="100"><!-- 14.Bill To Loc#-->
																														<col align="center" width="180"><!-- 15.Bill TO Name-->
																														<col align="center" width="300"><!-- 16.Sold to address -->
																														<col align="center" width="90" ><!-- 17.Sold To Number-->
																														<col align="center" width="90" ><!-- 18.Sold To Loc Number-->
																														<col align="center" width="180"><!-- 19.Sold To Name-->
																														<col align="center" width="110"><!-- 20.Model Group-->
																														<col align="center" width="170"><!-- 21.Model Description-->
																														<col align="center" width="170"><!-- 22.Segment-->
																														<col align="center" width="80" ><!-- 23.Install Required for Revenue Y/N-->
																														<col align="center" width="90" ><!-- 24.Site Survey Req-->
																														<col align="center" width="90" ><!-- 25.Sales Credit Splits  (Y/N)-->
																														<col align="center" width="90" ><!-- 26.On Hold (Y/N)-->
																														<col align="center" width="190"><!-- 27.Created By (User ID & Name)-->
																														<col align="center" width="160"><!-- 28.Creation Date & Time-->
																														<col align="center" width="190"><!-- 29.Last Update By (ID & Name)-->
																														<col align="center" width="160"><!-- 30.Last Update Date & Time-->
																														<col align="center" width="120"><!-- 31.Sub Total (Sum the Lines inside the config)-->
																														<col align="center" width="120"><!-- 32.Charges-->
																														<col align="center" width="120"><!-- 33.Tax-->
																														<col align="center" width="120"><!-- 34.Config  Total-->
																														<col align="center" width="80" ><!-- 35.DS-->
																														<col align="center" width="180"><!-- 36.CSMP Number-->
																														<col align="center" width="200"><!-- 37.CSA Dealer Ref#-->
																														<col align="center" width="180"><!-- 38.CSMP Price List-->
																														<tr height="24">
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="shipToCustLocAddr_RC" ezfName="shipToCustLocAddr_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"-1\" size=\"40\" maxlength=\"400\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8">
																																<table border="0" cellpadding="1" cellspacing="0">
																																	<tr style="padding:0;">
																																		<td><ezf:inputText name="shipToCustAcctCd_RC" ezfName="shipToCustAcctCd_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromShipToAccount" otherAttr=" size=\"10\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromShipToAccount\"" /></td>
																																		<td>
																																			<ezf:anchor name="xxImageTxt_CA" ezfName="xxImageTxt_CA" ezfHyo="C" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"xxImageTxt_CA\" ezfanchortext=\"\"">S</ezf:anchor>
																																		</td>
																																	</tr>
																																</table>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<table border="0" cellpadding="1" cellspacing="0">
																																	<tr style="padding:0;">
																																		<td><ezf:inputText name="shipToCustCd_RC" ezfName="shipToCustCd_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromShipToLocation" otherAttr=" size=\"10\" maxlength=\"22\" ezffocusout=\"OnBlur_DeriveFromShipToLocation\"" /></td>
																																		<td>
																																			<ezf:anchor name="xxImageTxt_CS" ezfName="xxImageTxt_CS" ezfHyo="C" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"xxImageTxt_CS\" ezfanchortext=\"\"">S</ezf:anchor>
																																		</td>
																																	</tr>
																																</table>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<table border="0" cellpadding="1" cellspacing="0">
																																	<tr style="padding:0;">
																																		<td><ezf:inputText name="shipToCustAcctNm_RC" ezfName="shipToCustAcctNm_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromShipToName" otherAttr=" size=\"22\" maxlength=\"60\" ezffocusout=\"OnBlur_DeriveFromShipToName\"" /></td>
																																		<td>
																																			<ezf:anchor name="xxImageTxt_CN" ezfName="xxImageTxt_CN" ezfHyo="C" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"xxImageTxt_CN\" ezfanchortext=\"\"">S</ezf:anchor>
																																		</td>
																																	</tr>
																																</table>
																															</td>
																															<td align="left" bgcolor="#bfd8d8">
																																<ezf:inputText name="addShipToLocNm_RC" ezfName="addShipToLocNm_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"23\" maxlength=\"90\" ezftoupper=\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="billToCustLocAddr_RC" ezfName="billToCustLocAddr_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"-1\" size=\"40\" maxlength=\"400\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8">
																																<table border="0" cellpadding="1" cellspacing="0">
																																	<tr style="padding:0;">
																																		<td><ezf:inputText name="billToCustAcctCd_RC" ezfName="billToCustAcctCd_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromBillToAccount" otherAttr=" size=\"10\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromBillToAccount\"" /></td>
																																		<td>
																																			<ezf:anchor name="xxImageTxt_CT" ezfName="xxImageTxt_CT" ezfHyo="C" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"xxImageTxt_CT\" ezfanchortext=\"\"">B</ezf:anchor>
																																		</td>
																																	</tr>
																																</table>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<table border="0" cellpadding="1" cellspacing="0">
																																	<tr style="padding:0;">
																																		<td><ezf:inputText name="billToCustCd_RC" ezfName="billToCustCd_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromBillToLocation" otherAttr=" size=\"10\" maxlength=\"60\" ezffocusout=\"OnBlur_DeriveFromBillToLocation\"" /></td>
																																		<td>
																																			<ezf:anchor name="xxImageTxt_CB" ezfName="xxImageTxt_CB" ezfHyo="C" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"xxImageTxt_CB\" ezfanchortext=\"\"">B</ezf:anchor>
																																		</td>
																																	</tr>
																																</table>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<table border="0" cellpadding="1" cellspacing="0">
																																	<tr style="padding:0;">
																																			<td><ezf:inputText name="billToCustAcctNm_RC" ezfName="billToCustAcctNm_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromBillToName" otherAttr=" size=\"22\" maxlength=\"60\" ezffocusout=\"OnBlur_DeriveFromBillToName\"" /></td>
																																			<td>
																																			<ezf:anchor name="xxImageTxt_CM" ezfName="xxImageTxt_CM" ezfHyo="C" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"xxImageTxt_CM\" ezfanchortext=\"\"">B</ezf:anchor>
																																		</td>
																																	</tr>
																																</table>
																															</td>
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="soldToCustLocAddr_RC" ezfName="soldToCustLocAddr_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"-1\" size=\"40\" maxlength=\"400\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="sellToCustCd_RC" ezfName="sellToCustCd_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"-1\" size=\"10\" maxlength=\"400\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="soldToCustLocCd_RC" ezfName="soldToCustLocCd_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"-1\"	 size=\"10\" maxlength=\"400\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="soldToCustAcctNm_RC" ezfName="soldToCustAcctNm_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"-1\" size=\"20\" maxlength=\"400\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																															
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="mdlGrpDescTxt_RC" ezfName="mdlGrpDescTxt_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"-1\" size=\"13\" maxlength=\"400\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="mdlDescTxt_RC" ezfName="mdlDescTxt_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"-1\" size=\"22\" maxlength=\"400\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																															
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="svcSegDescTxt_RC" ezfName="svcSegDescTxt_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"-1\" size=\"20\" maxlength=\"400\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																															
																															<td bgcolor="#bfd8d8">
																																<ezf:label name="svcIstlReqFlg_RC" ezfName="svcIstlReqFlg_RC" ezfHyo="C" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("svcIstlReqFlg_RC", bMsg_rt.C.no(i).svcIstlReqFlg_RC.getValue()); %>
																																<input type="hidden" name="svcIstlReqFlg_RC" ezfName="" value="<c:out value='${svcIstlReqFlg_RC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<ezf:label name="siteSrvyReqFlg_RC" ezfName="siteSrvyReqFlg_RC" ezfHyo="C" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("siteSrvyReqFlg_RC", bMsg_rt.C.no(i).siteSrvyReqFlg_RC.getValue()); %>
																																<input type="hidden" name="siteSrvyReqFlg_RC" ezfName="" value="<c:out value='${siteSrvyReqFlg_RC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<ezf:label name="slsCrSplFlg_RC" ezfName="slsCrSplFlg_RC" ezfHyo="C" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("slsCrSplFlg_RC", bMsg_rt.C.no(i).slsCrSplFlg_RC.getValue()); %>
																																<input type="hidden" name="slsCrSplFlg_RC" ezfName="" value="<c:out value='${slsCrSplFlg_RC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8">
																																<ezf:label name="dsCpoConfigHldFlg_RC" ezfName="dsCpoConfigHldFlg_RC" ezfHyo="C" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("dsCpoConfigHldFlg_RC", bMsg_rt.C.no(i).dsCpoConfigHldFlg_RC.getValue()); %>
																																<input type="hidden" name="dsCpoConfigHldFlg_RC" ezfName="" value="<c:out value='${dsCpoConfigHldFlg_RC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="cratUsrNm_RC" ezfName="cratUsrNm_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"-1\" size=\"26\" maxlength=\"93\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8" align="left">
																																<ezf:label name="cratTsDplyTxt_RC" ezfName="cratTsDplyTxt_RC" ezfHyo="C" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("cratTsDplyTxt_RC", bMsg_rt.C.no(i).cratTsDplyTxt_RC.getValue()); %>
																																<input type="hidden" name="cratTsDplyTxt_RC" ezfName="" value="<c:out value='${cratTsDplyTxt_RC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															
																															<td bgcolor="#bfd8d8" align="left"><ezf:inputText name="updUsrNm_RC" ezfName="updUsrNm_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"-1\" size=\"26\" maxlength=\"93\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																															<td bgcolor="#bfd8d8" align="left">
																																<ezf:label name="updTsDplyTxt_RC" ezfName="updTsDplyTxt_RC" ezfHyo="C" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("updTsDplyTxt_RC", bMsg_rt.C.no(i).updTsDplyTxt_RC.getValue()); %>
																																<input type="hidden" name="updTsDplyTxt_RC" ezfName="" value="<c:out value='${updTsDplyTxt_RC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															
																															<td bgcolor="#bfd8d8" align="right">
																																<ezf:label name="configTotDealNetAmt_RC" ezfName="configTotDealNetAmt_RC" ezfHyo="C" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("configTotDealNetAmt_RC", bMsg_rt.C.no(i).configTotDealNetAmt_RC.getValue()); %>
																																<input type="hidden" name="configTotDealNetAmt_RC" ezfName="" value="<c:out value='${configTotDealNetAmt_RC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8" align="right">
																																<ezf:label name="configTotDealChrgAmt_RC" ezfName="configTotDealChrgAmt_RC" ezfHyo="C" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("configTotDealChrgAmt_RC", bMsg_rt.C.no(i).configTotDealChrgAmt_RC.getValue()); %>
																																<input type="hidden" name="configTotDealChrgAmt_RC" ezfName="" value="<c:out value='${configTotDealChrgAmt_RC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8" align="right">
																																<ezf:label name="configTotDealTaxAmt_RC" ezfName="configTotDealTaxAmt_RC" ezfHyo="C" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("configTotDealTaxAmt_RC", bMsg_rt.C.no(i).configTotDealTaxAmt_RC.getValue()); %>
																																<input type="hidden" name="configTotDealTaxAmt_RC" ezfName="" value="<c:out value='${configTotDealTaxAmt_RC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															<td bgcolor="#bfd8d8" align="right">
																																<ezf:label name="configSubTotDealAmt_RC" ezfName="configSubTotDealAmt_RC" ezfHyo="C" ezfArrayNo="<%= i %>"/>
																																<% pageContext.setAttribute("configSubTotDealAmt_RC", bMsg_rt.C.no(i).configSubTotDealAmt_RC.getValue()); %>
																																<input type="hidden" name="configSubTotDealAmt_RC" ezfName="" value="<c:out value='${configSubTotDealAmt_RC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															</td>
																															
																															<td align="center" bgcolor="#bfd8d8">
																																<ezf:anchor name="xxImageTxt_CD" ezfName="xxImageTxt_CD" ezfHyo="C" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToChange" otherAttr=" id=\"xxImageTxt_CD\" ezfanchortext=\"\"">
																																	<ezf:label name="dropShipFlg_RC" ezfName="dropShipFlg_RC" ezfHyo="C" ezfArrayNo="<%= i %>" />
																																	<% pageContext.setAttribute("dropShipFlg_RC", bMsg_rt.C.no(i).dropShipFlg_RC.getValue()); %>
																																	<input type="hidden" name="dropShipFlg_RC" ezfName="" value="<c:out value='${dropShipFlg_RC}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</ezf:anchor>
																															</td>
																															<td align="left" bgcolor="#bfd8d8">
																																<ezf:inputText name="csmpContrNum_RC" ezfName="csmpContrNum_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromCSMPNumber" otherAttr=" size=\"18\" maxlength=\"15\" ezffocusout=\"OnBlur_DeriveFromCSMPNumber\"" />
																																<ezf:anchor name="xxImageTxt_RN" ezfName="xxImageTxt_RN" ezfHyo="C" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_CSMPNumber" otherAttr=" id=\"xxImageTxt_RN\" ezfanchortext=\"\"">C#</ezf:anchor>
																															</td>
																															<td align="left" bgcolor="#bfd8d8">
																																<ezf:inputText name="dlrRefNum_RC" ezfName="dlrRefNum_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromDealerRefNumber" otherAttr=" size=\"23\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromDealerRefNumber\"" />
																																<ezf:anchor name="xxImageTxt_RD" ezfName="xxImageTxt_RD" ezfHyo="C" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_CSMPNumber" otherAttr=" id=\"xxImageTxt_RD\" ezfanchortext=\"\"">R#</ezf:anchor>
																															</td>
																															<td align="left" bgcolor="#bfd8d8">
																																<ezf:inputText name="prcCatgNm_RC" ezfName="prcCatgNm_RC" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"23\" maxlength=\"75\" ezftoupper=\"\""/>
																															</td>
																														</tr>
																													</table>
																													<% if (!isSmryLine) { %>
																													<table border="1" cellpadding="1" cellspacing="0" width="6570" style="table-layout:fixed; margin-top: 2px">
																														<col align="center" width="80" ><!-- 5.Qty Order-->
																														<col align="center" width="220"><!-- 6.Return Reason-->
																														<col align="center" width="100"><!-- 7.Sell Price-->
																														<col align="center" width="100"><!-- 8.Net Price -->
																														<col align="center" width="190"><!-- 9.Status-->
																														<col align="center" width="180"><!-- 10.Line Category-->
																														<col align="center" width="180"><!-- 11.Warehouse-->
																														<col align="center" width="70" ><!-- 12.Sub Warehouse-->
																														<col align="center" width="80" ><!-- 13.UOM-->
																														<col align="center" width="180"><!-- 14.Price List-->
																														<col align="center" width="120"><!-- 15.Unit List Price-->
																														<col align="center" width="180"><!-- 16.Floor Price List-->
																														<col align="center" width="120"><!-- 17.Floor Price-->
																														<col align="center" width="180"><!-- 18.Serial Number-->
																														<col align="center" width="180"><!-- 19'.Machine Master ID-->
																														<col align="center" width="110"><!-- 20.Customer Item Code-->
																														<col align="center" width="120"><!-- 21.Customer Requested Pick up Date-->
																														<col align="center" width="120"><!-- 22.Pricing Date-->
																														<col align="center" width="180"><!-- 23.Booked Date & Time-->
																														<col align="center" width="120"><!-- 24.Pickup Scheduled Date-->
																														<col align="center" width="170"><!-- 25.Received Date & Time-->
																														<col align="center" width="80" ><!-- 26.Qty Returned-->
																														<col align="center" width="90" ><!-- 27.QTY Cancelled-->
																														<col align="center" width="190"><!-- 28.Cancel Reason Code-->
																														<col align="center" width="150"><!-- 29.Merchandise Type-->
																														<col align="center" width="170"><!-- 30.Product Code-->
																														<col align="center" width="180"><!-- 31.Item Classification-->
																														<col align="center" width="180"><!-- 32.Manufacturer Number-->
																														<col align="center" width="100"><!-- 33.Line Reference-->
																														<col align="center" width="180"><!-- 34.Customer PO#-->
																														<col align="center" width="180"><!-- 35.Customer PO Date-->
																														<col align="center" width="180"><!-- 36.Order Source Name-->
																														<col align="center" width="180"><!-- 37.Order Source Reference-->
																														<col align="center" width="180"><!-- 38.Line Source Reference-->
																														<col align="center" width="180"><!-- 39.Original Invoice Number-->
																														<col align="center" width="180"><!-- 40.Original Invoice Line Number-->
																														<col align="center" width="180"><!-- 41.BOM Parent Line#-->
																														<col align="center" width="180"><!-- 42.BOM Parent Version#-->
																														<col align="center" width="180"><!-- 43.BOM Parent Item Code-->
																														<col align="center" width="180"><!-- 44.Created By -->
																														<col align="center" width="180"><!-- 45.Creation Date & Time-->
																														<col align="center" width="180"><!-- 46.Last Update By -->
																														<col align="center" width="180"><!-- 47.Last Update Date & Time-->
																														<col align="center" width="120"><!-- 48.Sub Total -->
																														<col align="center" width="120"><!-- 49.Charges-->
																														<col align="center" width="120"><!-- 50.Tax-->
																														<col align="center" width="120"><!-- 51.Line Total-->
																														<col align="center" width="110"><!-- 52.HDD Removal-->
																														
																														
																														<tr>
																															<td class="pClothBs">Qty Order</td>
																															<td class="pClothBs">Return Reason</td>
																															<td class="pClothBs">Sell Price</td>
																															<td class="pClothBs">Net Price </td>
																															<td class="pClothBs">Status</td>
																															<td class="pClothBs">Line Category</td>
																															<td class="pClothBs">Warehouse(*)</td>
																															<td class="pClothBs">Sub WH</td>
																															<td class="pClothBs">UOM</td>
																															<td class="pClothBs">Price List</td>
																															<td class="pClothBs">Unit List Price</td>
																															<td class="pClothBs">Floor Price List</td>
																															<td class="pClothBs">Floor Price</td>
																															<td class="pClothBs">Serial#</td>
																															<td class="pClothBs">Machine Master ID</td>
																															<td class="pClothBs">Customer Item</td>
																															<td class="pClothBs">Req. Pick Up Date</td>
																															<td class="pClothBs">Pricing Date</td>
																															<td class="pClothBs">Booked Date</td>
																															<td class="pClothBs">Schd. Pickup Date</td>
																															<td class="pClothBs">Received Date</td>
																															<td class="pClothBs">Qty Returned</td>
																															<td class="pClothBs">QTY Cancelled</td>
																															<td class="pClothBs">Cancel Reason</td>
																															<td class="pClothBs">Merchandise Type</td>
																															<td class="pClothBs">Product Code</td>
																															<td class="pClothBs">Item Classification</td>
																															<td class="pClothBs">Manufacturer#</td>
																															<td class="pClothBs">Line Ref#</td>
																															<td class="pClothBs">Customer PO#</td>
																															<td class="pClothBs">Customer PO Date</td>
																															<td class="pClothBs">Order Source Name</td>
																															<td class="pClothBs">Order Source Reference</td>
																															<td class="pClothBs">Line Source Reference</td>
																															<td class="pClothBs">Original Invoice Number</td>
																															<td class="pClothBs">Original Invoice Line Number</td>
																															<td class="pClothBs">BOM Parent Line#</td>
																															<td class="pClothBs">BOM Parent Version#</td>
																															<td class="pClothBs">BOM Parent Item Code</td>
																															<td class="pClothBs">Created By</td>
																															<td class="pClothBs">Creation Date</td>
																															<td class="pClothBs">Last Update By</td>
																															<td class="pClothBs">Last Update Date</td>
																															<td class="pClothBs">Sub Total </td>
																															<td class="pClothBs">Charges</td>
																															<td class="pClothBs">Tax</td>
																															<td class="pClothBs">Line Total</td>
																															<td class="pClothBs">HDD Removal</td>
																														</tr>
																													</table>
																													<% } %>
																												</td>
																											</tr>
																											<tr>
																												<td>
																													<table border="1" cellpadding="1" cellspacing="0" width="6570" style="table-layout:fixed;">
																														<col align="center" width="80" ><!-- 5.Qty Order-->
																														<col align="center" width="220"><!-- 6.Return Reason-->
																														<col align="center" width="100"><!-- 7.Sell Price-->
																														<col align="center" width="100"><!-- 8.Net Price -->
																														<col align="center" width="190"><!-- 9.Status-->
																														<col align="center" width="180"><!-- 10.Line Category-->
																														<col align="center" width="180"><!-- 11.Warehouse-->
																														<col align="center" width="70" ><!-- 12.Sub Warehouse-->
																														<col align="center" width="80" ><!-- 13.UOM-->
																														<col align="center" width="180"><!-- 14.Price List-->
																														<col align="center" width="120"><!-- 16.Unit List Price-->
																														<col align="center" width="180"><!-- 17.Floor Price List-->
																														<col align="center" width="120"><!-- 18.Floor Price-->
																														<col align="center" width="180"><!-- 19.Serial Number-->
																														<col align="center" width="180"><!-- 20.Machine Master ID-->
																														<col align="center" width="110"><!-- 21.Customer Item Code-->
																														<col align="center" width="120"><!-- 22.Customer Requested Pick up Date-->
																														<col align="center" width="120"><!-- 23.Pricing Date-->
																														<col align="center" width="180"><!-- 24.Booked Date & Time-->
																														<col align="center" width="120"><!-- 25.Pickup Scheduled Date-->
																														<col align="center" width="170"><!-- 26.Received Date & Time-->
																														<col align="center" width="80" ><!-- 27.Qty Returned-->
																														<col align="center" width="90" ><!-- 28.QTY Cancelled-->
																														<col align="center" width="190"><!-- 29.Cancel Reason Code-->
																														<col align="center" width="150"><!-- 30.Merchandise Type-->
																														<col align="center" width="170"><!-- 31.Product Code-->
																														<col align="center" width="180"><!-- 32.Item Classification-->
																														<col align="center" width="180"><!-- 33.Manufacturer Number-->
																														<col align="center" width="100"><!-- 34.Line Reference-->
																														<col align="center" width="180"><!-- 35.Customer PO#-->
																														<col align="center" width="180"><!-- 36.Customer PO Date-->
																														<col align="center" width="180"><!-- 37.Order Source Name-->
																														<col align="center" width="180"><!-- 38.Order Source Reference-->
																														<col align="center" width="180"><!-- 39.Line Source Reference-->
																														<col align="center" width="180"><!-- 40.Original Invoice Number-->
																														<col align="center" width="180"><!-- 41.Original Invoice Line Number-->
																														<col align="center" width="180"><!-- 42.BOM Parent Line#-->
																														<col align="center" width="180"><!-- 43.BOM Parent Version#-->
																														<col align="center" width="180"><!-- 44.BOM Parent Item Code-->
																														<col align="center" width="180"><!-- 45.Created By -->
																														<col align="center" width="180"><!-- 46.Creation Date & Time-->
																														<col align="center" width="180"><!-- 47.Last Update By -->
																														<col align="center" width="180"><!-- 48.Last Update Date & Time-->
																														<col align="center" width="120"><!-- 49.Sub Total -->
																														<col align="center" width="120"><!-- 50.Charges-->
																														<col align="center" width="120"><!-- 51.Tax-->
																														<col align="center" width="120"><!-- 52.Line Total-->
																														<col align="center" width="110"><!-- 53.HDD Removal-->
																														
																														<% for ( int j = lineIdx_rt; j < bMsg_rt.D.getValidCount(); j++ ) { %>
																														
																															<%
																																NWAL1500_CBMsg configLineMsg = bMsg_rt.C.no(i);
																																String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_RC.getValue();
																																
																																String lineDsOrdPosnNum = bMsg_rt.D.no(j).dsOrdPosnNum_RL.getValue();
																																if (!dsOrdPosnNum.equals(lineDsOrdPosnNum)) {
																																	break;
																																}
																																lineIdx_rt++;
																																
																																if (isSmryLine) {
																																	pageContext.setAttribute("ordCustUomQty_RL", bMsg_rt.D.no(j).ordCustUomQty_RL.getValue());
																																	pageContext.setAttribute("rtrnRsnCd_RL", bMsg_rt.D.no(j).rtrnRsnCd_RL.getValue());
																																	pageContext.setAttribute("entCpoDtlDealSlsAmt_RL", bMsg_rt.D.no(j).entCpoDtlDealSlsAmt_RL.getValue());
																																	pageContext.setAttribute("entDealNetUnitPrcAmt_RL", bMsg_rt.D.no(j).entDealNetUnitPrcAmt_RL.getValue());
																																	pageContext.setAttribute("rtrnLineStsDescTxt_RL", bMsg_rt.D.no(j).rtrnLineStsDescTxt_RL.getValue());
																																	pageContext.setAttribute("dsOrdLineCatgCd_RL", bMsg_rt.D.no(j).dsOrdLineCatgCd_RL.getValue());
																																	pageContext.setAttribute("rtlWhNm_RL", bMsg_rt.D.no(j).rtlWhNm_RL.getValue());
																																	pageContext.setAttribute("rtlSwhNm_RL", bMsg_rt.D.no(j).rtlSwhNm_RL.getValue());
																																	pageContext.setAttribute("custUomCd_RL", bMsg_rt.D.no(j).custUomCd_RL.getValue());
																																	pageContext.setAttribute("prcCatgNm_RL", bMsg_rt.D.no(j).prcCatgNm_RL.getValue());
																																	pageContext.setAttribute("flPrcListNm_RL", bMsg_rt.D.no(j).flPrcListNm_RL.getValue());
																																	pageContext.setAttribute("dealPrcListPrcAmt_RL", bMsg_rt.D.no(j).dealPrcListPrcAmt_RL.getValue());
																																	pageContext.setAttribute("funcUnitFlPrcAmt_RL", bMsg_rt.D.no(j).funcUnitFlPrcAmt_RL.getValue());
																																	pageContext.setAttribute("serNum_RL", bMsg_rt.D.no(j).serNum_RL.getValue());
																																	pageContext.setAttribute("svcMachMstrPk_RL", bMsg_rt.D.no(j).svcMachMstrPk_RL.getValue());
																																	pageContext.setAttribute("custMdseCd_RL", bMsg_rt.D.no(j).custMdseCd_RL.getValue());
																																	pageContext.setAttribute("ordBookTsDplyTxt_RL", bMsg_rt.D.no(j).ordBookTsDplyTxt_RL.getValue());
																																	pageContext.setAttribute("schdPickUpDtDplyTxt_RL", bMsg_rt.D.no(j).schdPickUpDtDplyTxt_RL.getValue());
																																	pageContext.setAttribute("rcvTsDplyTxt_RL", bMsg_rt.D.no(j).rcvTsDplyTxt_RL.getValue());
																																	pageContext.setAttribute("rtrnQty_RL", bMsg_rt.D.no(j).rtrnQty_RL.getValue());
																																	pageContext.setAttribute("cancQty_RL", bMsg_rt.D.no(j).cancQty_RL.getValue());
																																	pageContext.setAttribute("cancRsnDescTxt_RL", bMsg_rt.D.no(j).cancRsnDescTxt_RL.getValue());
																																	pageContext.setAttribute("coaMdseTpDescTxt_RL", bMsg_rt.D.no(j).coaMdseTpDescTxt_RL.getValue());
																																	pageContext.setAttribute("coaProdDescTxt_RL", bMsg_rt.D.no(j).coaProdDescTxt_RL.getValue());
																																	pageContext.setAttribute("mdseItemClsTpDescTxt_RL", bMsg_rt.D.no(j).mdseItemClsTpDescTxt_RL.getValue());
																																	pageContext.setAttribute("mnfItemCd_RL", bMsg_rt.D.no(j).mnfItemCd_RL.getValue());
																																	pageContext.setAttribute("dplyLineRefNum_RL", bMsg_rt.D.no(j).dplyLineRefNum_RL.getValue());
																																	pageContext.setAttribute("custIssPoNum_RL", bMsg_rt.D.no(j).custIssPoNum_RL.getValue());
																																	pageContext.setAttribute("custIssPoDt_RL", bMsg_rt.D.no(j).custIssPoDt_RL.getValue());
																																	pageContext.setAttribute("cpoSrcTpDescTxt_RL", bMsg_rt.D.no(j).cpoSrcTpDescTxt_RL.getValue());
																																	pageContext.setAttribute("ordSrcRefNum_RL", bMsg_rt.D.no(j).ordSrcRefNum_RL.getValue());
																																	pageContext.setAttribute("lineSrcRefNum_RL", bMsg_rt.D.no(j).lineSrcRefNum_RL.getValue());
																																	pageContext.setAttribute("xxInvRefNum_RL", bMsg_rt.D.no(j).xxInvRefNum_RL.getValue());
																																	pageContext.setAttribute("xxInvLineRefNum_RL", bMsg_rt.D.no(j).xxInvLineRefNum_RL.getValue());
																																	pageContext.setAttribute("setPrntLineNum_RL", bMsg_rt.D.no(j).setPrntLineNum_RL.getValue());
																																	pageContext.setAttribute("cmpsnRevnNum_RL", bMsg_rt.D.no(j).cmpsnRevnNum_RL.getValue());
																																	pageContext.setAttribute("setMdseCd_RL", bMsg_rt.D.no(j).setMdseCd_RL.getValue());
																																	pageContext.setAttribute("cratUsrNm_RL", bMsg_rt.D.no(j).cratUsrNm_RL.getValue());
																																	pageContext.setAttribute("cratTsDplyTxt_RL", bMsg_rt.D.no(j).cratTsDplyTxt_RL.getValue());
																																	pageContext.setAttribute("updUsrNm_RL", bMsg_rt.D.no(j).updUsrNm_RL.getValue());
																																	pageContext.setAttribute("updTsDplyTxt_RL", bMsg_rt.D.no(j).updTsDplyTxt_RL.getValue());
																																	pageContext.setAttribute("lineDealSubTotAmt_RL", bMsg_rt.D.no(j).lineDealSubTotAmt_RL.getValue());
																																	pageContext.setAttribute("lineDealChrgAmt_RL", bMsg_rt.D.no(j).lineDealChrgAmt_RL.getValue());
																																	pageContext.setAttribute("lineDealTaxAmt_RL", bMsg_rt.D.no(j).lineDealTaxAmt_RL.getValue());
																																	pageContext.setAttribute("lineDealTotAmt_RL", bMsg_rt.D.no(j).lineDealTotAmt_RL.getValue());
																																	pageContext.setAttribute("hddRmvCd_RL", bMsg_rt.D.no(j).hddRmvCd_RL.getValue());
																															%>
																																	<input type="hidden" name="ordCustUomQty_RL" ezfName="" value="<c:out value='${ordCustUomQty_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="rtrnRsnCd_RL" ezfName="" value="<c:out value='${rtrnRsnCd_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="entCpoDtlDealSlsAmt_RL" ezfName="" value="<c:out value='${entCpoDtlDealSlsAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="entDealNetUnitPrcAmt_RL" ezfName="" value="<c:out value='${entDealNetUnitPrcAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="rtrnLineStsDescTxt_RL" ezfName="" value="<c:out value='${rtrnLineStsDescTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="dsOrdLineCatgCd_RL" ezfName="" value="<c:out value='${dsOrdLineCatgCd_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="rtlWhNm_RL" ezfName="" value="<c:out value='${rtlWhNm_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="rtlSwhNm_RL" ezfName="" value="<c:out value='${rtlSwhNm_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="custUomCd_RL" ezfName="" value="<c:out value='${custUomCd_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="prcCatgNm_RL" ezfName="" value="<c:out value='${prcCatgNm_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="flPrcListNm_RL" ezfName="" value="<c:out value='${flPrcListNm_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="dealPrcListPrcAmt_RL" ezfName="" value="<c:out value='${dealPrcListPrcAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="funcUnitFlPrcAmt_RL" ezfName="" value="<c:out value='${funcUnitFlPrcAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="serNum_RL" ezfName="" value="<c:out value='${serNum_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="svcMachMstrPk_RL" ezfName="" value="<c:out value='${svcMachMstrPk_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="custMdseCd_RL" ezfName="" value="<c:out value='${custMdseCd_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="rqstPickUpDt_RL" ezfName="rqstPickUpDt_RL" ezfHyo="D" ezfArrayNo="<%= j %>"/>
																																	<input type="hidden" name="prcBaseDt_RL" ezfName="prcBaseDt_RL" ezfHyo="D" ezfArrayNo="<%= j %>"/>
																																	<input type="hidden" name="ordBookTsDplyTxt_RL" ezfName="" value="<c:out value='${ordBookTsDplyTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="schdPickUpDtDplyTxt_RL" ezfName="" value="<c:out value='${schdPickUpDtDplyTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="rcvTsDplyTxt_RL" ezfName="" value="<c:out value='${rcvTsDplyTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="rtrnQty_RL" ezfName="" value="<c:out value='${rtrnQty_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="cancQty_RL" ezfName="" value="<c:out value='${cancQty_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="cancRsnDescTxt_RL" ezfName="" value="<c:out value='${cancRsnDescTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="coaMdseTpDescTxt_RL" ezfName="" value="<c:out value='${coaMdseTpDescTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="coaProdDescTxt_RL" ezfName="" value="<c:out value='${coaProdDescTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="mdseItemClsTpDescTxt_RL" ezfName="" value="<c:out value='${mdseItemClsTpDescTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="mnfItemCd_RL" ezfName="" value="<c:out value='${mnfItemCd_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="dplyLineRefNum_RL" ezfName="" value="<c:out value='${dplyLineRefNum_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="custIssPoNum_RL" ezfName="" value="<c:out value='${custIssPoNum_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="custIssPoDt_RL" ezfName="" value="<c:out value='${custIssPoDt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="cpoSrcTpDescTxt_RL" ezfName="" value="<c:out value='${cpoSrcTpDescTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="ordSrcRefNum_RL" ezfName="" value="<c:out value='${ordSrcRefNum_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="lineSrcRefNum_RL" ezfName="" value="<c:out value='${lineSrcRefNum_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="xxInvRefNum_RL" ezfName="" value="<c:out value='${xxInvRefNum_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="xxInvLineRefNum_RL" ezfName="" value="<c:out value='${xxInvLineRefNum_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="setPrntLineNum_RL" ezfName="" value="<c:out value='${setPrntLineNum_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="cmpsnRevnNum_RL" ezfName="" value="<c:out value='${cmpsnRevnNum_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="setMdseCd_RL" ezfName="" value="<c:out value='${setMdseCd_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="cratUsrNm_RL" ezfName="" value="<c:out value='${cratUsrNm_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="cratTsDplyTxt_RL" ezfName="" value="<c:out value='${cratTsDplyTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="updUsrNm_RL" ezfName="" value="<c:out value='${updUsrNm_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="updTsDplyTxt_RL" ezfName="" value="<c:out value='${updTsDplyTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="lineDealSubTotAmt_RL" ezfName="" value="<c:out value='${lineDealSubTotAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="lineDealChrgAmt_RL" ezfName="" value="<c:out value='${lineDealChrgAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="lineDealTaxAmt_RL" ezfName="" value="<c:out value='${lineDealTaxAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="lineDealTotAmt_RL" ezfName="" value="<c:out value='${lineDealTotAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																	<input type="hidden" name="hddRmvCd_RL" ezfName="" value="<c:out value='${hddRmvCd_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																															<%
																																	continue;
																																}
																															%>
																															<tr height="24">
																																<td><ezf:inputText name="ordCustUomQty_RL" ezfName="ordCustUomQty_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromQty" otherAttr=" size=\"7\" maxlength=\"10\" ezffocusout=\"OnBlur_DeriveFromQty\"" /></td>
																																<td>
																																	<div id="<%= j %>">
																																		<ezf:select name="rtrnRsnCd_RL" ezfName="rtrnRsnCd_RL" ezfHyo="D" ezfArrayNo="<%= j %>" ezfBlank="1" ezfCodeName="rtrnRsnCd_CD" ezfDispName="rtrnRsnDescTxt_NM" otherAttr=" style=\"width:200\"" otherEvent1=" onchange=\"sendServer('OnChange_ReturnReason',this.parentNode.id)\" ezfHyo=\"D\""/>
																																	</div>
																																</td>
																																<td><ezf:inputText name="entCpoDtlDealSlsAmt_RL" ezfName="entCpoDtlDealSlsAmt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromSellPrice" otherAttr=" size=\"13\" maxlength=\"13\" ezffocusout=\"OnBlur_DeriveFromSellPrice\""/></td>
																																<td align="right">
																																	<ezf:label name="entDealNetUnitPrcAmt_RL" ezfName="entDealNetUnitPrcAmt_RL" ezfHyo="D" ezfArrayNo="<%= j %>"/>
																																	<% pageContext.setAttribute("entDealNetUnitPrcAmt_RL", bMsg_rt.D.no(j).entDealNetUnitPrcAmt_RL.getValue()); %>
																																	<input type="hidden" name="entDealNetUnitPrcAmt_RL" ezfName="" value="<c:out value='${entDealNetUnitPrcAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="left"><ezf:inputText name="rtrnLineStsDescTxt_RL" ezfName="rtrnLineStsDescTxt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"15\" maxlength=\"50\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td>
																																	<div id="<%= j %>">
																																		<ezf:select name="dsOrdLineCatgCd_RL" ezfName="dsOrdLineCatgCd_RL" ezfHyo="D" ezfArrayNo="<%= j %>" ezfCodeName="dsOrdLineCatgCd_CR" ezfDispName="dsOrdLineCatgDescTxt_NR" ezfCodeDispHyo="D" otherAttr=" style=\"width:170\"" otherEvent1=" onchange=\"sendServer('OnChange_LineCategory',this.parentNode.id)\" ezfHyo=\"D\""/>
																																	</div>
																																</td>
																																<td>
																																	<table border="0" cellpadding="1" cellspacing="0">
																																		<tr style="padding:0;">
																																			<td><ezf:inputText name="rtlWhNm_RL" ezfName="rtlWhNm_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_ChangeWH" otherAttr=" size=\"20\" maxlength=\"60\" ezffocusout=\"OnBlur_ChangeWH\"" /></td>
																																			<td>
																																				<ezf:anchor name="xxImageTxt_DW" ezfName="xxImageTxt_DW" ezfHyo="D" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" id=\"xxImageTxt_DW\" ezfanchortext=\"\"">W</ezf:anchor>
																																			</td>
																																		</tr>
																																	</table>
																																</td>
																																<td>
																																	<table border="0" cellpadding="1" cellspacing="0">
																																		<tr style="padding:0;">
																																			<td><ezf:inputText name="rtlSwhNm_RL" ezfName="rtlSwhNm_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_ChangeSubWH" otherAttr=" size=\"7\" maxlength=\"50\" ezffocusout=\"OnBlur_ChangeSubWH\""/></td>
																																			<td>
																																				<ezf:anchor name="xxImageTxt_DU" ezfName="xxImageTxt_DU" ezfHyo="D" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWarehouse" otherAttr=" id=\"xxImageTxt_DU\" ezfanchortext=\"\"">U</ezf:anchor>
																																			</td>
																																		</tr>
																																	</table>
																																</td>
																																
																																<td>
																																	<div id="<%= j %>">
																																		<ezf:select name="custUomCd_RL" ezfName="custUomCd_RL" ezfCodeName="pkgUomCd_CR" ezfDispName="pkgUomDescTxt_CN" ezfArrayNo="<%= j %>" ezfHyo="D" ezfCodeDispHyo="D" otherAttr=" style=\"width:70\"" otherEvent1=" onchange=\"sendServer('OnChange_UOM',this.parentNode.id)\" ezfHyo=\"D\"" />
																																	</div>
																																</td>
																																<td>
																																	<table border="0" cellpadding="1" cellspacing="0">
																																		<tr style="padding:0;">
																																			<td><ezf:inputText name="prcCatgNm_RL" ezfName="prcCatgNm_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromPriceList" otherAttr=" size=\"21\" maxlength=\"250\" ezffocusout=\"OnBlur_DeriveFromPriceList\"" /></td>
																																			<td>
																																				<ezf:anchor name="xxImageTxt_DP" ezfName="xxImageTxt_DP" ezfHyo="D" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_PriceList" otherAttr=" id=\"xxImageTxt_DP\" ezfanchortext=\"\"">P</ezf:anchor>
																																			</td>
																																		</tr>
																																	</table>
																																</td>
																																<td align="right">
																																	<ezf:label name="dealPrcListPrcAmt_RL" ezfName="dealPrcListPrcAmt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("dealPrcListPrcAmt_RL", bMsg_rt.D.no(j).dealPrcListPrcAmt_RL.getValue()); %>
																																	<input type="hidden" name="dealPrcListPrcAmt_RL" ezfName="" value="<c:out value='${dealPrcListPrcAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td>
																																	<table border="0" cellpadding="1" cellspacing="0">
																																		<tr style="padding:0;">
																																			<td><ezf:inputText name="flPrcListNm_RL" ezfName="flPrcListNm_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromFloorPriceList" otherAttr=" size=\"21\" maxlength=\"75\" ezffocusout=\"OnBlur_DeriveFromFloorPriceList\"" /></td>
																																			<td>
																																				<ezf:anchor name="xxImageTxt_DF" ezfName="xxImageTxt_DF" ezfHyo="D" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_FloorPriceList" otherAttr=" id=\"xxImageTxt_DF\" ezfanchortext=\"\"">F</ezf:anchor>
																																			</td>
																																		</tr>
																																	</table>
																																</td>
																																<td><ezf:inputText name="funcUnitFlPrcAmt_RL" ezfName="funcUnitFlPrcAmt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
																																
																																<td>
																																	<ezf:inputText name="serNum_RL" ezfName="serNum_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromSerial" otherAttr=" size=\"21\" maxlength=\"30\" ezffocusout=\"OnBlur_DeriveFromSerial\"" />
																																</td>
																																<td>
																																	<ezf:inputText name="svcMachMstrPk_RL" ezfName="svcMachMstrPk_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"21\" maxlength=\"30\"  style=\"text-align: left; border:none; background-color:transparent\" ezftoupper=\"\""/>
																																</td>
																																<td>
																																	<ezf:inputText name="custMdseCd_RL" ezfName="custMdseCd_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/>
																																</td>
																																
																																<td>
																																	<table border="0" cellpadding="1" cellspacing="0">
																																		<tr style="padding:0;">
																																			<td><ezf:inputText name="rqstPickUpDt_RL" ezfName="rqstPickUpDt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																																			<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstPickUpDt_RL', 4, '<%= j %>');" ></td>
																																		</tr>
																																	</table>
																																</td>
																																<td>
																																	<table border="0" cellpadding="1" cellspacing="0">
																																		<tr style="padding:0;">
																																			<td><ezf:inputText name="prcBaseDt_RL" ezfName="prcBaseDt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																																			<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('prcBaseDt_RL', 4, '<%= j %>');" ></td>
																																		</tr>
																																	</table>
																																</td>
																																<td align="left">
																																	<ezf:label name="ordBookTsDplyTxt_RL" ezfName="ordBookTsDplyTxt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("ordBookTsDplyTxt_RL", bMsg_rt.D.no(j).ordBookTsDplyTxt_RL.getValue()); %>
																																	<input type="hidden" name="ordBookTsDplyTxt_RL" ezfName="" value="<c:out value='${ordBookTsDplyTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="left">
																																	<ezf:label name="schdPickUpDtDplyTxt_RL" ezfName="schdPickUpDtDplyTxt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("schdPickUpDtDplyTxt_RL", bMsg_rt.D.no(j).schdPickUpDtDplyTxt_RL.getValue()); %>
																																	<input type="hidden" name="schdPickUpDtDplyTxt_RL" ezfName="" value="<c:out value='${schdPickUpDtDplyTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="left">
																																	<ezf:label name="rcvTsDplyTxt_RL" ezfName="rcvTsDplyTxt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("rcvTsDplyTxt_RL", bMsg_rt.D.no(j).rcvTsDplyTxt_RL.getValue()); %>
																																	<input type="hidden" name="rcvTsDplyTxt_RL" ezfName="" value="<c:out value='${rcvTsDplyTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																
																																
																																<td align="right">
																																	<ezf:label name="rtrnQty_RL" ezfName="rtrnQty_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("rtrnQty_RL", bMsg_rt.D.no(j).rtrnQty_RL.getValue()); %>
																																	<input type="hidden" name="rtrnQty_RL" ezfName="" value="<c:out value='${rtrnQty_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="right">
																																	<ezf:label name="cancQty_RL" ezfName="cancQty_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("cancQty_RL", bMsg_rt.D.no(j).cancQty_RL.getValue()); %>
																																	<input type="hidden" name="cancQty_RL" ezfName="" value="<c:out value='${cancQty_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="left"><ezf:inputText name="cancRsnDescTxt_RL" ezfName="cancRsnDescTxt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"25\" maxlength=\"50\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																
																																<td align="left"><ezf:inputText name="coaMdseTpDescTxt_RL" ezfName="coaMdseTpDescTxt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"15\" maxlength=\"50\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																																<td align="left"><ezf:inputText name="coaProdDescTxt_RL" ezfName="coaProdDescTxt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"15\" maxlength=\"50\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																																<td align="left"><ezf:inputText name="mdseItemClsTpDescTxt_RL" ezfName="mdseItemClsTpDescTxt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"18\" maxlength=\"50\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td align="left">
																																	<ezf:label name="mnfItemCd_RL" ezfName="mnfItemCd_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("mnfItemCd_RL", bMsg_rt.D.no(j).mnfItemCd_RL.getValue()); %>
																																	<input type="hidden" name="mnfItemCd_RL" ezfName="" value="<c:out value='${mnfItemCd_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																
																																<td><ezf:inputText name="dplyLineRefNum_RL" ezfName="dplyLineRefNum_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_DeriveFromDplyLineRefNum" otherAttr=" size=\"8\" maxlength=\"15\" ezffocusout=\"OnBlur_DeriveFromDplyLineRefNum\""/></td>
																																
																																<td align="left"><ezf:inputText name="custIssPoNum_RL" ezfName="custIssPoNum_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"18\" maxlength=\"60\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td align="left">
																																	<ezf:label name="custIssPoDt_RL" ezfName="custIssPoDt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("custIssPoDt_RL", bMsg_rt.D.no(j).custIssPoDt_RL.getValue()); %>
																																</td>
																																
																																<td align="left"><ezf:inputText name="cpoSrcTpDescTxt_RL" ezfName="cpoSrcTpDescTxt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"20\" maxlength=\"50\"  style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																																<td align="left"><ezf:inputText name="ordSrcRefNum_RL" ezfName="ordSrcRefNum_RL" ezfHyo="D" ezfArrayNo="<%= j %>"otherAttr="tabindex=\"-1\" size=\"20\" maxlength=\"50\"  style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td align="left"><ezf:inputText name="lineSrcRefNum_RL" ezfName="lineSrcRefNum_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"20\" maxlength=\"100\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																
																																<td align="left"><ezf:inputText name="xxInvRefNum_RL" ezfName="xxInvRefNum_RL" ezfHyo="D" ezfArrayNo="<%= j %>"otherAttr="tabindex=\"-1\" size=\"20\" maxlength=\"50\"  style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td align="left"><ezf:inputText name="xxInvLineRefNum_RL" ezfName="xxInvLineRefNum_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"20\" maxlength=\"100\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																
																																<td align="left"><ezf:inputText name="setPrntLineNum_RL" ezfName="setPrntLineNum_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"20\" maxlength=\"65\" style=\"border:none; background-color:transparent\"   ezftoupper=\"\""/></td>
																																<td align="left"><ezf:inputText name="cmpsnRevnNum_RL" ezfName="cmpsnRevnNum_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"20\" maxlength=\"3\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td align="left"><ezf:inputText name="setMdseCd_RL" ezfName="setMdseCd_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"20\" maxlength=\"16\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																
																																<td align="left"><ezf:inputText name="cratUsrNm_RL" ezfName="cratUsrNm_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"25\" maxlength=\"93\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td align="left">
																																	<ezf:label name="cratTsDplyTxt_RL" ezfName="cratTsDplyTxt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("cratTsDplyTxt_RL", bMsg_rt.D.no(j).cratTsDplyTxt_RL.getValue()); %>
																																	<input type="hidden" name="cratTsDplyTxt_RL" ezfName="" value="<c:out value='${cratTsDplyTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="left"><ezf:inputText name="updUsrNm_RL" ezfName="updUsrNm_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr="tabindex=\"-1\" size=\"25\" maxlength=\"93\" style=\"border:none; background-color:transparent\"  ezftoupper=\"\""/></td>
																																<td align="left">
																																	<ezf:label name="updTsDplyTxt_RL" ezfName="updTsDplyTxt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("updTsDplyTxt_RL", bMsg_rt.D.no(j).updTsDplyTxt_RL.getValue()); %>
																																	<input type="hidden" name="updTsDplyTxt_RL" ezfName="" value="<c:out value='${updTsDplyTxt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																
																																<td align="right">
																																	<ezf:label name="lineDealSubTotAmt_RL" ezfName="lineDealSubTotAmt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("lineDealSubTotAmt_RL", bMsg_rt.D.no(j).lineDealSubTotAmt_RL.getValue()); %>
																																	<input type="hidden" name="lineDealSubTotAmt_RL" ezfName="" value="<c:out value='${lineDealSubTotAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="right">
																																	<ezf:label name="lineDealChrgAmt_RL" ezfName="lineDealChrgAmt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("lineDealChrgAmt_RL", bMsg_rt.D.no(j).lineDealChrgAmt_RL.getValue()); %>
																																	<input type="hidden" name="lineDealChrgAmt_RL" ezfName="" value="<c:out value='${lineDealChrgAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="right">
																																	<ezf:label name="lineDealTaxAmt_RL" ezfName="lineDealTaxAmt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("lineDealTaxAmt_RL", bMsg_rt.D.no(j).lineDealTaxAmt_RL.getValue()); %>
																																	<input type="hidden" name="lineDealTaxAmt_RL" ezfName="" value="<c:out value='${lineDealTaxAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td align="right">
																																	<ezf:label name="lineDealTotAmt_RL" ezfName="lineDealTotAmt_RL" ezfHyo="D" ezfArrayNo="<%= j %>" />
																																	<% pageContext.setAttribute("lineDealTotAmt_RL", bMsg_rt.D.no(j).lineDealTotAmt_RL.getValue()); %>
																																	<input type="hidden" name="lineDealTotAmt_RL" ezfName="" value="<c:out value='${lineDealTotAmt_RL}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
																																</td>
																																<td>
																																	<div id="<%= j %>">
																																		<ezf:select name="hddRmvCd_RL" ezfName="hddRmvCd_RL" ezfHyo="D" ezfArrayNo="<%= j %>" ezfBlank="1" ezfCodeName="hddRmvCd_CD" ezfDispName="hddRmvDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_HddRemoval',this.parentNode.id)\" ezfHyo=\"D\"" otherAttr=" style=\"width:100\""/>
																																	</div>
																																</td>
																															</tr>
																														<% } %>
																													</table>
																												</td>
																											</tr>
																										</table>
																								</td>
																							</tr>
																						</table>
																					<% } %>
																					<%-- +++++ [END] Programming JSP for Nesting Records Table +++++ --%>

																					<!-- /table -->
																				</div>

																			</td>
																		</tr>
																	</table>
																</td>
															</tr>

														</table>
													</td>
												</tr>
											</table>
<!-- RMA Detail End -->
											<!-- Actions -->
											<table valign="bottom">
												<col width="1100">
												<td align="left">
													<fieldset>
														<table style="table-layout:fixed;">
															<col width="60">
															<col width="110">
															<col width="20">
															<col width="120">
															<col width="110">
															<col width="20">
															<col width="200">
															<col width="110">
															<col width="20">
															<col width="50">
															<col width="110">
															<td class="stab">Actions(<u>A</u>)</td>
															<td>
																<ezf:select name="ordEntryActCd_AC" ezfName="ordEntryActCd_AC" ezfBlank="1" ezfCodeName="ordEntryActCd_RA" ezfDispName="ordEntryActDescTxt_RA" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryAction')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"0\" accesskey=\"A\""/>
															</td>
															<td>&nbsp;</td>
															<td class="stab">Additional Information(<u>F</u>)</td>
															<td>
																<ezf:select name="ordEntryActCd_IF" ezfName="ordEntryActCd_IF" ezfBlank="1" ezfCodeName="ordEntryActCd_LI" ezfDispName="ordEntryActDescTxt_LI" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryActionAddlInfo')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"0\" accesskey=\"F\""/>
															</td>
															<td>&nbsp;</td>
															<td class="stab">Delivery/Install/Site Survey/Contacts(<u>C</u>)</td>
															<td>
																<ezf:select name="ordEntryActCd_DL" ezfName="ordEntryActCd_DL" ezfBlank="1" ezfCodeName="ordEntryActCd_LD" ezfDispName="ordEntryActDescTxt_LD" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryActionDelivery')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"0\" accesskey=\"C\""/>
															</td>
															<td>&nbsp;</td>
															<td class="stab">Tools(<u>T</u>)</td>
															<td>
																<ezf:select name="ordEntryActCd_TO" ezfName="ordEntryActCd_TO" ezfBlank="1" ezfCodeName="ordEntryActCd_LT" ezfDispName="ordEntryActDescTxt_LT" otherEvent1=" onchange=\"setTimeout(function () {sendServer('OnChange_OrderEntryActionTool')}, 20)\"" otherAttr=" style=\"width: 100px\" tabindex=\"0\" accesskey=\"T\""/>
															</td>
														</table>
													</fieldset>
												</td>
											</table>
										</div>
									</c:when>
								</c:choose>
							</div>
						</td>
					</tr>
				</table>
			</center>
<div style="display:none;">
	<ezf:inputButton name="btn11" value="btn11" otherAttr=" id=\"btn11\""/>
	<ezf:inputButton name="btn12" value="btn12" otherAttr=" id=\"btn12\""/>
</div>
<% pageContext.setAttribute("xxListNum_LX", ((NWAL1500BMsg)databean.getEZDBMsg()).xxListNum_LX.getValue()); %>
<input type="hidden" name="xxListNum_LX" ezfName="" value="<c:out value='${xxListNum_LX}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
<% pageContext.setAttribute("xxListNum_LY", ((NWAL1500BMsg)databean.getEZDBMsg()).xxListNum_LY.getValue()); %>
<input type="hidden" name="xxListNum_LY" ezfName="" value="<c:out value='${xxListNum_LY}' />" otherAttr=" otherattr=\" id=\\\" =\"\""/>
<%-- ###SCRIPT --%>
<script type="text/javascript">

	setKeyDownHandler();

	// setScroll(); 
	setTimeout(setScroll,10);

	function synchroScroll_fromRightTblAction() {
		var rightTopTBL = this.document.getElementById( 'rightTopTBL' );
		var rightTBL    = this.document.getElementById( 'rightTBL'     );
		var leftTBL     = this.document.getElementById( 'leftTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
		// synchronize scroll - X
		rightTopTBL.scrollLeft = rightTBL.scrollLeft;
	}
	
	function synchroScroll_fromLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftTBL'  );
		var rightTBL = this.document.getElementById( 'rightTBL' );
		
		// synchronize scroll - Y
		rightTBL.scrollTop = leftTBL.scrollTop;
	}

	function onFocusEvent(item){
		if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
		}
		item.select();
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
	
	<%@ page import="com.fujitsu.uji.util.Parameters" %>
	<%@ page import="com.fujitsu.uji.http.HttpDispatchContext" %>
	function setScroll() {
		<%
		Parameters params = (Parameters) pageContext.getAttribute(HttpDispatchContext.PARAMETERS_KEY, PageContext.REQUEST_SCOPE);
		String ezdEvent = params.getSingleValue("ezd.event");
		%>
		var xxListNum_LX = this.document.getElementById( 'xxListNum_LX' );
		var xxListNum_LY = this.document.getElementById( 'xxListNum_LY' );
		var leftTBL = this.document.getElementById( 'leftTBL' );
		var rightTopTBL = this.document.getElementById( 'rightTopTBL' );
		var rightTBL = this.document.getElementById( 'rightTBL' );

		<% if (ezdEvent!=null && ezdEvent.startsWith("TAB_")) { %>
			xxListNum_LX.value = 0;
			xxListNum_LY.value = 0;
		<% } %>
	    var LX = xxListNum_LX.value;
	    var LY = xxListNum_LY.value
		if (xxListNum_LY.value > 0) {
			leftTBL.scrollTop = LY;
			rightTBL.scrollTop = LY;
		}
		
		if (xxListNum_LX.value > 0) {
			rightTopTBL.scrollLeft = LX;
			rightTBL.scrollLeft = LX;
		 }
	}

	function setScrollPosition() {
		var rightTBL    = this.document.getElementById( 'rightTBL' );
		this.document.getElementById( 'xxListNum_LX' ).value = rightTBL.scrollLeft;
		this.document.getElementById( 'xxListNum_LY' ).value = rightTBL.scrollTop;
	}

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

		if(event.keyCode >= 122 && event.keyCode <= 123) {
			event.keyCode = null;
			event.returnValue = false;
		}

		switch(code) {
		// F11
		case 122:
			//sendServer("Line_Config_Add");
			emulateOnClickEvent("btn11");
			break;
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
