<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190328102332 --%>
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
			<input type="hidden" name="pageID" value="NWAL2200Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Order Import Form">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<ezf:skip>
				<div class="pTab_HEAD">
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
</ezf:skip>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<div class="pTab_BODY">

<%-- ######################################## HEADER ######################################## --%>
					<table>
						<col valign="top">
						<col valign="top">
						<col valign="top">
						<tr>
							<td>

								<fieldset>
									<legend style="font-size:12px;">Import Information</legend>
									<table>
										<tr>
											<td class="stab">
												Source Reference Num
											</td>
											<td>
												<ezf:inputText name="ordSrcRefNum" ezfName="ordSrcRefNum" value="100123" otherAttr=" size=\"20\" ezftoupper=\"\""/>
												<ezf:inputButton name="Order_Search" value="Search" htmlClass="pBtn5" />
											</td>
											<td class="stab">
												Import Status
											</td>
											<td>
												<ezf:inputText name="imptStsDescTxt" ezfName="imptStsDescTxt" value="Saved" otherAttr=" size=\"32\""/>
											</td>
										</tr>
										<tr>
											<td class="stab">
												Source Name
											</td>
											<td>
												<ezf:inputText name="cpoSrcTpDescTxt" ezfName="cpoSrcTpDescTxt" value="(SOM) SALES ORDER MAKER" otherAttr=" size=\"32\" ezftoupper=\"\""/>
											</td>
											<td class="stab">
												Import Date
											</td>
											<td>
												<ezf:inputText name="xxDtTm" ezfName="xxDtTm" value="02/02/2015 09:00" otherAttr=" size=\"32\""/>
											</td>
										</tr>
										<tr>
											<td class="stab">
												<ezf:anchor name="dsOrdCatgDescTxt_LK" ezfName="dsOrdCatgDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderCategory" otherAttr=" ezfanchortext=\"\"">Category (*)</ezf:anchor>
											</td>
											<td>
												<ezf:inputText name="dsOrdCatgDescTxt" ezfName="dsOrdCatgDescTxt" value="PURCHASE, CSA" otherEvent1="OnBlur_DeriveFromCategory" otherAttr=" size=\"32\" maxlength=\"50\" ezffocusout=\"OnBlur_DeriveFromCategory\""/>
											</td>
											<td class="stab">
												Reason Code
											</td>
											<td>
												<ezf:select name="dsOrdTpCd" ezfName="dsOrdTpCd" ezfBlank="1" ezfCodeName="dsOrdTpCd_CD" ezfDispName="dsOrdTpDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_OrderReason')\"" htmlClass="phsu" otherAttr=" style=\"width: 202px\""/>
											</td>
										</tr>
										<tr>
											<td class="stab">
												Sub Reason Code
											</td>
											<td>
												<ezf:select name="dsOrdRsnCd" ezfName="dsOrdRsnCd" ezfBlank="1" ezfCodeName="dsOrdRsnCd_CD" ezfDispName="dsOrdRsnDescTxt_NM" otherAttr=" style=\"width: 202px\""/>
											</td>
											<td class="stab">
												<ezf:anchor name="cpoOrdNum_PL" ezfName="cpoOrdNum_PL" ezfEmulateBtn="1" ezfGuard="OpenWin_CpoOrdNum" otherAttr=" ezfanchortext=\"\"">CPO Order Num</ezf:anchor>
											</td>
											<% boolean isMultiOrd = "Y".equals(((NWAL2200BMsg)databean.getEZDBMsg()).xxDplyCtrlFlg.getValue()); %>
										    <% if (!isMultiOrd) { %>
											<td class="pOut">
												<ezf:anchor name="cpoOrdNum_LK" ezfName="cpoOrdNum_LK" ezfEmulateBtn="1" ezfGuard="MoveWin_OrderEntry" otherAttr=" ezfanchortext=\"\""><ezf:label name="cpoOrdNum" ezfName="cpoOrdNum" /></ezf:anchor>
											</td>
											<% } else { %>
											<td class="pOut">
												<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_CpoOrdNum" otherAttr=" ezfanchortext=\"\"">Multiple Orders</ezf:anchor>
											</td>
											<% }  %>
										</tr>
									</table>
								</fieldset>

								<table>
									<tr align="left">
										<td>
											<ezf:inputButton name="OpenWin_DeliveryInfo" value="Delivery Info" htmlClass="pBtn8" />
										</td>
										<td>
											<ezf:inputButton name="OpenWin_ServicePricing" value="Service Pricing" htmlClass="pBtn8" />
										</td>
										<td>
											<ezf:inputButton name="OpenWin_Approval" value="Approval" htmlClass="pBtn8" />
										</td>
										<td>
											<ezf:inputButton name="OpenWin_Profitability" value="Profitability" htmlClass="pBtn8" />
										</td>
										<td>
											<ezf:inputButton name="Validate" value="Validate" htmlClass="pBtn8" />
										</td>
										<td>
											<ezf:inputButton name="OpenWin_ImportAttribute" value="Import Attribute" htmlClass="pBtn8" />
										</td>
									</tr>
								</table>

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
																<tr height="23">
																	<td class="pClothBs">Sub Total</td>
																	<td><ezf:label name="xxTotBaseAmt" ezfName="xxTotBaseAmt" /></td>
																</tr>
																<tr height="23">
																	<td class="pClothBs">Charges</td>
																	<td><ezf:label name="xxTotFrtAmt" ezfName="xxTotFrtAmt" /></td>
																</tr>
																<tr height="23">
																	<td class="pClothBs">Tax</td>
																	<td><ezf:label name="xxTotTaxAmt" ezfName="xxTotTaxAmt" /></td>
																</tr>
																<tr height="23">
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
																<tr height="23">
																	<td class="pClothBs">Lines</td>
																	<td><ezf:label name="xxTotBaseAmt_LN" ezfName="xxTotBaseAmt_LN" /></td>
																</tr>
																<tr height="23">
																	<td class="pClothBs">Maintenance</td>
																	<td><ezf:label name="xxTotBaseAmt_MT" ezfName="xxTotBaseAmt_MT" /></td>
																</tr>
																<tr height="23">
																	<td class="pClothBs">RMA</td>
																	<td><ezf:label name="xxTotBaseAmt_RT" ezfName="xxTotBaseAmt_RT" /></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
								</fieldset>
							</td>
						</tr>
					</table>
<%-- ######################################## HEADER ######################################## --%>
<%-- ##### BODY(TAB) ##### --%>
					<div class="pTab_HEAD">
						<ul>
							<li id="Header" title="Header" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Header" otherAttr=" tabindex=\"-1\"">Header</ezf:anchor></li>
							<li id="Addl_Header" title="Addl_Header" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="TAB_Addl_Header" otherAttr=" tabindex=\"-1\"">Addl Header</ezf:anchor></li>
							<li id="Line_Config" title="Line_Config" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="TAB_Line_Config" otherAttr=" tabindex=\"-1\"">Line Config</ezf:anchor></li>
							<li id="RMA" title="RMA" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_04" ezfEmulateBtn="1" ezfGuard="TAB_RMA" otherAttr=" tabindex=\"-1\"">RMA</ezf:anchor></li>
							<li id="Errors" title="Errors" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_05" ezfEmulateBtn="1" ezfGuard="TAB_Errors" otherAttr=" tabindex=\"-1\"">Errors</ezf:anchor></li>
						</ul>
					</div>
<%-- ##### TAB(Main) ##### --%>
				<c:choose>
<%-- ######################################## HEADER TAB ######################################## --%>
					<c:when test="${pageScope._ezddatabean.xxDplyTab=='Header'}">
						<script type="text/javascript">
							document.getElementById( "Header" ).className="pTab_ON";
							document.getElementById( "Addl" ).className="pTab_OFF";
							document.getElementById( "Line_Config" ).className="pTab_OFF";
							document.getElementById( "RMA" ).className="pTab_OFF";
							document.getElementById( "Errors" ).className="pTab_OFF";
						</script>
						<div class="pTab_BODY_In">
							<table style="table-layout:fixed;">
								<col width="620" valign="top">
								<col width="" valign="top">
								<tr>
									<td>

										<fieldset>
											<legend style="font-size:12px;">Customer Information</legend>

											<fieldset>
												<legend style="font-size:12px;">Bill To</legend>
												<table style="table-layout:fixed;" border="0">
													<col width="48" valign="top">
													<col width="330" valign="top">
													<!-- Mod Start 2017/09/26 H.Sugawara QC#19922 -->
													<!-- <col width="60" valign="top"> -->
													<!-- <col width="140" valign="top"> -->
													<col width="85" valign="top">
													<col width="115" valign="top">
													<!-- Mod End   2017/09/26 H.Sugawara QC#19922 -->
													<tr>
														<td class="stab"><ezf:anchor name="billToCustAcctNm_LK" ezfName="billToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"billToCustAcctNm_LK\" ezfanchortext=\"\"">Name (*)</ezf:anchor></td>
														<td>
															<ezf:inputText name="billToCustAcctNm" ezfName="billToCustAcctNm" value="CANON FINANCIAL SVC" otherEvent1="OnBlur_DeriveFromBillToName" otherAttr=" size=\"32\" maxlength=\"60\" ezffocusout=\"OnBlur_DeriveFromBillToName\""/>
														</td>
														<td class="stab"><ezf:anchor name="billToCustAcctCd_LK" ezfName="billToCustAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"billToCustAcctCd_LK\" ezfanchortext=\"\"">Number (*)</ezf:anchor></td>
														<td>
															<ezf:inputText name="billToCustAcctCd" ezfName="billToCustAcctCd" value="1002805" otherEvent1="OnBlur_DeriveFromBillToAccount" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromBillToAccount\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Address</td>
														<td><ezf:textArea name="xxAllLineAddr_BT" ezfName="xxAllLineAddr_BT" otherAttr=" rows=\"3\" cols=\"40\" ezftoupper=\"\""/></td>
														<!-- Mod Start 2017/09/26 H.Sugawara QC#19922 -->
														<!-- <td class="stab"><a href="#" id="billToCustCd_LK" name="billToCustCd_LK" ezfName="billToCustCd_LK" onclick="sendServer('OpenWin_BillTo')" ezfanchortext>Location (*)</a></td> -->
														<td class="stab"><ezf:anchor name="billToCustCd_LK" ezfName="billToCustCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"billToCustCd_LK\" ezfanchortext=\"\"">Bill To Code (*)</ezf:anchor></td>
														<!-- Mod End   2017/09/26 H.Sugawara QC#19922 -->
														<td>
															<ezf:inputText name="billToCustCd" ezfName="billToCustCd" value="5819" otherEvent1="OnBlur_DeriveFromBillToLocation" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromBillToLocation\""/>
														</td>
													</tr>
												</table>
											</fieldset>

											<fieldset>
												<legend style="font-size:12px;">Ship To</legend>
												<table style="table-layout:fixed;" border="0">
													<col width="48" valign="top">
													<col width="330" valign="top">
													<!-- Mod Start 2017/09/26 H.Sugawara QC#19922 -->
													<!-- <col width="60" valign="top"> -->
													<!-- <col width="140" valign="top"> -->
													<col width="85" valign="top">
													<col width="115" valign="top">
													<!-- Mod End   2017/09/26 H.Sugawara QC#19922 -->
													<tr>
														<td class="stab"><ezf:anchor name="shipToCustAcctNm_LK" ezfName="shipToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"shipToCustAcctNm_LK\" ezfanchortext=\"\"">Name (*)</ezf:anchor></td>
														<td>
															<ezf:inputText name="shipToCustAcctNm" ezfName="shipToCustAcctNm" value="CANON BULLS" otherEvent1="OnBlur_DeriveFromShipToName" otherAttr=" size=\"32\" maxlength=\"60\" ezffocusout=\"OnBlur_DeriveFromShipToName\""/>
															&nbsp;DS&nbsp;
															<ezf:anchor name="dropShipFlg_LK" ezfName="dropShipFlg_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToChange" htmlClass="pOut" otherAttr=" id=\"dropShipFlg_LK\" ezfanchortext=\"\""><ezf:label name="dropShipFlg" ezfName="dropShipFlg" /></ezf:anchor>
														</td>
														<td class="stab"><ezf:anchor name="shipToCustAcctCd_LK" ezfName="shipToCustAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"shipToCustAcctCd_LK\" ezfanchortext=\"\"">Number (*)</ezf:anchor></td>
														<td>
															<ezf:inputText name="shipToCustAcctCd" ezfName="shipToCustAcctCd" value="1471766" otherEvent1="OnBlur_DeriveFromShipToAccount" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromShipToAccount\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Address</td>
														<td><ezf:textArea name="xxAllLineAddr_SH" ezfName="xxAllLineAddr_SH" otherAttr=" rows=\"3\" cols=\"40\" ezftoupper=\"\""/></td>
														<!-- Mod Start 2017/09/26 H.Sugawara QC#19922 -->
														<!-- <td class="stab"><a href="#" id="shipToCustCd_LK" name="shipToCustCd_LK" ezfName="shipToCustCd_LK" onclick="sendServer('OpenWin_ShipTo')" ezfanchortext>Location (*)</a></td> -->
														<td class="stab"><ezf:anchor name="shipToCustCd_LK" ezfName="shipToCustCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"shipToCustCd_LK\" ezfanchortext=\"\"">Ship To Code (*)</ezf:anchor></td>
														<!-- Mod End   2017/09/26 H.Sugawara QC#19922 -->
														<td>
															<ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" value="2014164" otherEvent1="OnBlur_DeriveFromShipToLocation" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromShipToLocation\""/>
														</td>
													</tr>
												</table>
											</fieldset>

											<fieldset>
												<legend style="font-size:12px;">Sold To</legend>
												<table style="table-layout:fixed;">
													<col width="48" valign="top">
													<col width="330" valign="top">
													<!-- Mod Start 2017/09/26 H.Sugawara QC#19922 -->
													<!-- <col width="60" valign="top"> -->
													<!-- <col width="140" valign="top"> -->
													<col width="85" valign="top">
													<col width="115" valign="top">
													<!-- Mod End   2017/09/26 H.Sugawara QC#19922 -->
													<tr>
														<td class="stab"><ezf:anchor name="soldToCustAcctNm_LK" ezfName="soldToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" otherAttr=" id=\"soldToCustAcctNm_LK\" ezfanchortext=\"\"">Name (*)</ezf:anchor></td>
														<td>
															<ezf:inputText name="soldToCustAcctNm" ezfName="soldToCustAcctNm" value="CANON BULLS" otherEvent1="OnBlur_DeriveFromSoldToName" otherAttr=" size=\"32\" maxlength=\"60\" ezffocusout=\"OnBlur_DeriveFromSoldToName\""/>
														</td>
														<td class="stab"><ezf:anchor name="sellToCustCd_LK" ezfName="sellToCustCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" otherAttr=" id=\"sellToCustCd_LK\" ezfanchortext=\"\"">Number (*)</ezf:anchor></td>
														<td>
															<ezf:inputText name="sellToCustCd" ezfName="sellToCustCd" value="1471766" otherEvent1="OnBlur_DeriveFromSoldToAccount" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromSoldToAccount\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Address</td>
														<td><ezf:textArea name="xxAllLineAddr_SE" ezfName="xxAllLineAddr_SE" otherAttr=" rows=\"3\" cols=\"40\" ezftoupper=\"\""/></td>
														<!-- Mod Start 2017/09/26 H.Sugawara QC#19922 -->
														<!-- <td class="stab"><a href="#" id="soldToCustLocCd_LK" name="soldToCustLocCd_LK" ezfName="soldToCustLocCd_LK" onclick="sendServer('OpenWin_SoldTo')" ezfanchortext>Location (*)</a></td> -->
														<td class="stab"><ezf:anchor name="soldToCustLocCd_LK" ezfName="soldToCustLocCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" otherAttr=" id=\"soldToCustLocCd_LK\" ezfanchortext=\"\"">Sold To Code (*)</ezf:anchor></td>
														<!-- Mod End   2017/09/26 H.Sugawara QC#19922 -->
														<td>
															<ezf:inputText name="soldToCustLocCd" ezfName="soldToCustLocCd" value="555555" otherEvent1="OnBlur_DeriveFromSoldToLocation" otherAttr=" size=\"12\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromSoldToLocation\""/>
														</td>
													</tr>
												</table>
											</fieldset>
										</fieldset>
										<br>
										<ezf:inputButton name="OpenWin_SpecialInstruction" value="Special Instruction" htmlClass="pBtn10" />
									</td>
									<td>

										<fieldset>
											<legend style="font-size:12px;">Header Details</legend>
											<table style="table-layout:fixed;" border="0">
												<col width="120">
												<col width="130">
												<col width="50">
												<col width="50">
												<col width="122">
												<tr>
													<td class="stab">Negotiated Deal</td>
													<td colspan="4">
														<ezf:inputText name="negoDealAmt" ezfName="negoDealAmt" value="100.00" otherAttr=" size=\"16\" style=\"text-align:right;\" maxlength=\"20\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td class="stab" valign="top"><ezf:anchor name="slsRepPsnNum_LK" ezfName="slsRepPsnNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Salesrep" otherAttr=" id=\"slsRepPsnNum_LK\" ezfanchortext=\"\"">Salesrep</ezf:anchor></td>
													<td colspan="4">
														<ezf:inputText name="slsRepTocNm" ezfName="slsRepTocNm" value="DOE,JOHN" otherAttr=" size=\"40\" maxlength=\"50\""/>
														<ezf:inputText name="slsRepPsnNum" ezfName="slsRepPsnNum" value="D012345" otherAttr=" size=\"8\" maxlength=\"50\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Salesrep Branch</td>
													<td colspan="4">
														<ezf:inputText name="xxScrItem54Txt_CB" ezfName="xxScrItem54Txt_CB" value="CHICAGO[225]" otherAttr=" size=\"50\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Salesrep Bus Unit</td>
													<td colspan="4">
														<ezf:inputText name="xxScrItem54Txt_CE" ezfName="xxScrItem54Txt_CE" value="200-CORE[ESS]" otherAttr=" size=\"50\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">&nbsp</td>
													<td colspan="4">
														<ezf:inputButton name="OpenWin_SalesCredit" value="Sales Credit" htmlClass="pBtn6" />
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="prcCatgNm_LK" ezfName="prcCatgNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PriceList" otherAttr=" id=\"prcCatgNm_LK\" ezfanchortext=\"\"">Price List</ezf:anchor></td>
													<td colspan="4">
														<ezf:inputText name="prcCatgNm" ezfName="prcCatgNm" value="FLOOR, CSA" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Customer PO</td>
													<td><ezf:inputText name="custIssPoNum" ezfName="custIssPoNum" value="BPR TEST 1" otherAttr=" size=\"10\" maxlength=\"35\" ezftoupper=\"\""/></td>
													<td class="stab" colspan="2">Lease Company PO</td>
													<td><ezf:inputText name="leaseCmpyPoNum" ezfName="leaseCmpyPoNum" value="121" otherAttr=" size=\"10\" maxlength=\"35\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab">Customer PO Date</td>
													<td><ezf:inputText name="custIssPoDt" ezfName="custIssPoDt" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												</tr>
												<tr>
													<td class="stab">Acquisition Number</td>
													<td colspan="2"><ezf:inputText name="aquNum" ezfName="aquNum" value="50012345" otherAttr=" size=\"24\" maxlength=\"35\" ezftoupper=\"\""/></td>
													<td class="stab">Log Type</td>
													<td><ezf:select name="ordLogTpCd" ezfName="ordLogTpCd" ezfBlank="1" ezfCodeName="ordLogTpCd_CD" ezfDispName="ordLogTpDescTxt_NM" otherAttr=" style=\"width: 112px\""/></td>
												</tr>
												<tr>
													<td class="stab">Interface Creation Date</td>
													<td colspan="4">
														<ezf:inputText name="xxCratDt" ezfName="xxCratDt" value="02/02/2015" otherAttr=" size=\"10\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Invoice Comments</td>
													<td colspan="4"><ezf:textArea name="invCmntTxt" ezfName="invCmntTxt" otherAttr=" rows=\"2\" cols=\"40\" ezftoupper=\"\""/></td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
							</table>
						</div>
					</c:when>
<%-- ######################################## HEADER TAB ######################################## --%>
<%-- ######################################## ADDL HEADER TAB ######################################## --%>
					<c:when test="${pageScope._ezddatabean.xxDplyTab=='Addl_Header'}">
						<script type="text/javascript">
							document.getElementById( "Header" ).className="pTab_OFF";
							document.getElementById( "Addl_Header" ).className="pTab_ON";
							document.getElementById( "Line_Config" ).className="pTab_OFF";
							document.getElementById( "RMA" ).className="pTab_OFF";
							document.getElementById( "Errors" ).className="pTab_OFF";
						</script>
						<div class="pTab_BODY_In" style="height:400px;">
							<table style="table-layout:fixed;">
								<col width="620" valign="top">
								<col width="480" valign="top">
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
													<td class="stab"><ezf:anchor name="frtCondDescTxt_LK" ezfName="frtCondDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_FreightTerms" otherAttr=" id=\"frtCondDescTxt_LK\" ezfanchortext=\"\"">Freight Terms</ezf:anchor></td>
													<td>
														<ezf:inputText name="frtCondDescTxt" ezfName="frtCondDescTxt" value="ESS-SUPPLIES-STANDARD" otherEvent1="OnBlur_DeriveFromFreightTerms" otherAttr=" size=\"30\" maxlength=\"50\" ezffocusout=\"OnBlur_DeriveFromFreightTerms\""/>
													</td>
													<td class="stab">Service Level</td>
													<td><ezf:select name="shpgSvcLvlCd" ezfName="shpgSvcLvlCd" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_CD" ezfDispName="shpgSvcLvlDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_ShpgSvcLvlCd')\"" otherAttr=" style=\"width: 145px\""/></td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="carrSvcLvlDescTxt_LK" ezfName="carrSvcLvlDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CarrierServiceLevel" otherAttr=" id=\"carrSvcLvlDescTxt_LK\" ezfanchortext=\"\"">Carrier Service Level</ezf:anchor></td>
													<td>
														<ezf:inputText name="carrSvcLvlDescTxt" ezfName="carrSvcLvlDescTxt" value="FedEx PR1 OVRNT" otherAttr=" size=\"30\" maxlength=\"50\""/>
													</td>
													<td class="stab">Carrier Acct Num</td>
													<td><ezf:inputText name="carrAcctNum" ezfName="carrAcctNum" value="FEDEX-0001-002" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td></td>
													<td></td>
													<td class="stab">Special Handling</td>
													<td><ezf:select name="spclHdlgTpCd" ezfName="spclHdlgTpCd" ezfBlank="1" ezfCodeName="spclHdlgTpCd_CD" ezfDispName="spclHdlgTpDescTxt_NM" otherAttr=" style=\"width: 145px\""/></td>
												</tr>
											</table>
										</fieldset>

										<fieldset>
											<legend style="font-size:12px;">Payment Information</legend>
											<table style="table-layout:fixed;">
												<col width="100">
												<col width="250">
												<col width="120">
												<col width="130">
												<tr>
													<td class="stab"><ezf:anchor name="pmtTermCashDiscDescTxt_LK" ezfName="pmtTermCashDiscDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PaymentTerms" otherAttr=" id=\"pmtTermCashDiscDescTxt_LK\" ezfanchortext=\"\"">Payment Terms</ezf:anchor></td>
													<td>
														<ezf:inputText name="pmtTermCashDiscDescTxt" ezfName="pmtTermCashDiscDescTxt" value="NET 30" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
													<!--
													<td class="stab">Credit Card Information</td>
													<td><input type="button" class="pBtn6" value="Credit Card" name="MoveWin_CreditCard" onclick="sendServer(this)"></td>
													-->
												</tr>
												<tr>
													<td class="stab">Payment Method</td>
													<td><ezf:select name="dsPmtMethCd" ezfName="dsPmtMethCd" ezfBlank="1" ezfCodeName="dsPmtMethCd_CD" ezfDispName="dsPmtMethDescTxt_NM" otherAttr=" style=\"width: 214px\""/></td>
												</tr>
											</table>
										</fieldset>

										<fieldset>
											<legend style="font-size:12px;">Prepayment</legend>
											<table style="table-layout:fixed;">
												<col width="100">
												<col width="250">
												<col width="100">
												<col width="150">
												<tr>
													<td class="stab">Number</td>
													<td>
														<ezf:inputText name="prePmtChkNum" ezfName="prePmtChkNum" value="10001" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
													<td class="stab">Prepayment Type</td>
													<td><ezf:select name="prePmtTpCd" ezfName="prePmtTpCd" ezfBlank="1" ezfCodeName="prePmtTpCd_CD" ezfDispName="prePmtTpDescTxt_NM" otherAttr=" style=\"width: 145px\""/></td>
												</tr>
												<tr>
													<td class="stab">Amount</td>
													<td>
														<ezf:inputText name="prePmtAmt" ezfName="prePmtAmt" value="1,000.00" otherAttr=" size=\"30\" maxlength=\"15\" style=\"text-align:right;\" ezftoupper=\"\""/>
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
												<col width="250">
												<tr>
													<td class="stab">Requested Delivery Date</td>
													<td><ezf:inputText name="rddDt" ezfName="rddDt" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												</tr>
												<tr>
													<td class="stab">Association Program</td>
													<td><ezf:inputText name="prcContrNum" ezfName="prcContrNum" value="XXXXXXXXXX" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="flPrcListDescTxt_LK" ezfName="flPrcListDescTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_FloorPriceList" otherAttr=" id=\"flPrcListDescTxt_LK\" ezfanchortext=\"\"">Floor Price List</ezf:anchor></td>
													<td>
														<ezf:inputText name="flPrcListDescTxt" ezfName="flPrcListDescTxt" value="FLOOR, CA" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Loan Period (Days)</td>
													<td><ezf:inputText name="loanPerDaysAot" ezfName="loanPerDaysAot" value="30" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="csmpContrNum_LK" ezfName="csmpContrNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CSMPNumber" otherAttr=" id=\"csmpContrNum_LK\" ezfanchortext=\"\"">CSMP Number</ezf:anchor></td>
													<td>
														<ezf:inputText name="csmpContrNum" ezfName="csmpContrNum" value="12345-ABCD02" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="dlrRefNum_LK" ezfName="dlrRefNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CSMPNumber" otherAttr=" id=\"dlrRefNum_LK\" ezfanchortext=\"\"">CSA Number(Dealer Ref#)</ezf:anchor></td>
													<td>
														<ezf:inputText name="dlrRefNum" ezfName="dlrRefNum" value="xxxxxxxx10xxxxxxxx20" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Customer Signed Date</td>
													<td><ezf:inputText name="ordSgnDt" ezfName="ordSgnDt" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												</tr>
												<tr>
													<td class="stab">Customer Declines Equipment Maintenance</td>
													<td><ezf:inputCheckBox name="dclnSvcCd" ezfName="dclnSvcCd" value="1" onClick="sendServer('OnChange_DclnSvcCdHdr')" otherAttr=" tabindex=\"1\""/></td>
												</tr>
											</table>
										</fieldset>

										<% boolean isLeaseDtlDisp = "Y".equals(((NWAL2200BMsg)databean.getEZDBMsg()).xxDplyCtrlFlg_LD.getValue()); %>
										<% if (isLeaseDtlDisp) { %>
										<fieldset>
											<legend style="font-size:12px;">Lease Details</legend>
											<table style="table-layout:fixed;">
												<col width="150">
												<col width="250">
												<tr>
													<td class="stab">End of Term Purchase Option</td>
													<td><ezf:select name="leasePrchOptCd" ezfName="leasePrchOptCd" ezfBlank="1" ezfCodeName="leasePrchOptCd_CD" ezfDispName="leasePrchOptDescTxt_NM" otherAttr=" style=\"width: 200px\""/></td>
												</tr>
												<tr>
													<td class="stab">Term</td>
													<td><ezf:inputText name="leaseTermMthAot" ezfName="leaseTermMthAot" otherAttr=" size=\"30\" style=\"width: 200px;text-align: right\""/></td>
												</tr>
												<tr>
													<td class="stab">Payment Frequency</td>
													<td><ezf:select name="leasePmtFreqCd" ezfName="leasePmtFreqCd" ezfBlank="1" ezfCodeName="leasePmtFreqCd_CD" ezfDispName="leasePmtFreqDescTxt_NM" otherAttr=" style=\"width: 200px\""/></td>
												</tr>
											</table>
										</fieldset>
										<% } %>

										<% boolean isEMSDSegmentDisp = "Y".equals(((NWAL2200BMsg)databean.getEZDBMsg()).xxDplyCtrlFlg_EM.getValue()); %>
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

										<% boolean isGLSegmentDisp = "Y".equals(((NWAL2200BMsg)databean.getEZDBMsg()).xxDplyCtrlFlg_GS.getValue()); %>
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
						</div>
					</c:when>
<%-- ######################################## ADDL HEADER TAB ######################################## --%>
<%-- ######################################## LINE CONFIG TAB ######################################## --%>
					<c:when test="${pageScope._ezddatabean.xxDplyTab=='Line_Config'}">
						<script type="text/javascript">
							document.getElementById( "Header" ).className="pTab_OFF";
							document.getElementById( "Addl_Header" ).className="pTab_OFF";
							document.getElementById( "Line_Config" ).className="pTab_ON";
							document.getElementById( "RMA" ).className="pTab_OFF";
							document.getElementById( "Errors" ).className="pTab_OFF";
						</script>
						<div class="pTab_BODY_In">
							<table>
								<tr>
									<td>
										<ezf:inputButton name="Line_All_Expand" value="All Expand" htmlClass="pBtn6" />
									</td>
									<td>
										<ezf:inputButton name="Line_All_Collapsed" value="All Collapse" htmlClass="pBtn6" />
									</td>
									<td>
										<ezf:inputButton name="OpenWin_Buyout" value="Buyout" htmlClass="pBtn4" />
									</td>
									<td>
										<ezf:inputButton name="OpenWin_PriceChanges" value="Price Changes" htmlClass="pBtn8" />
									</td>
									<td>
										<ezf:inputButton name="OpenWin_SalesCredit" value="Sales Credit" htmlClass="pBtn6" />
									</td>
									<td>
										<ezf:inputButton name="OpenWin_AddressMassApplyDtl" value="Addr Mass Apply" htmlClass="pBtn9" />
									</td>
									<td>
										<ezf:inputButton name="OpenWin_AddlLineDetails" value="Addl Line Details" htmlClass="pBtn9" />
									</td>
									<td>
										<ezf:inputButton name="Config_Check_All" value="Config Check All" htmlClass="pBtn9" />
									</td>
									<td>
										<ezf:inputButton name="Config_UnCheck_All" value="Config UnCheck All" htmlClass="pBtn9" />
									</td>
								</tr>
							</table>
							<table  width="1101" border="0"  border='1'>
								<col width="165">
								<col>
								<col width="300" align="right">
								<col width="550" align="right">
								<tr>
									<td>
										<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
									</td>
									<td>
									</td>
									<td>
										Config#
										<ezf:inputText name="dsOrdPosnNum_DL" ezfName="dsOrdPosnNum_DL" value="200" otherAttr=" size=\"4\" maxlength=\"3\""/>
										/
										<ezf:label name="dsOrdPosnNum_TL" ezfName="dsOrdPosnNum_TL" />
										<ezf:inputButton name="ConfigJump" value="Config Jump" htmlClass="pBtn8" />
									</td>
									<td>
										<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
										<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
										<jsp:param name="TableNm"     value="B" />
										<jsp:param name="ShowingFrom"       value="xxPageShowFromNum_LC" />
										<jsp:param name="ShowingTo"         value="xxPageShowToNum_LC" />
										<jsp:param name="ShowingOf"         value="xxPageShowOfNum_LC" />
										<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum_LC" />
										<jsp:param name="ShowingTotal"      value="xxPageShowTotNum_LC" />
										<jsp:param name="ViewMode"          value="FULL" />
										</jsp:include>
									</td>
								</tr>
							</table>
							
							<table>
								<tr>
									<td>
										<div id="parentBoxA">
											<div style="float:left; display:block"><!-- left table -->
												<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
												<div id="leftTbl" style="float:left; display:block; height:263px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
											</div><!-- left table -->
											<div style="float:left"><!-- right table -->
												<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" style="margin-right:20px">
														<col align="center" width="30">     <!-- +/-                        -->
														<col align="center" width="30">     <!-- checkconfig                -->
														<col align="center" width="30">     <!-- checkline                  -->
														<col align="center" width="30">     <!-- &nbsp                      -->
														<col align="center" width="50">     <!-- Line#                      -->
														<col align="center" width="130">    <!-- Line Source Reference      -->
														<col align="center" width="100">    <!-- Config Type                -->
														<col align="center" width="155">    <!-- Config ID / Item#(*)       -->
														<col align="center" width="155">    <!-- Model(*) / Description     -->
														<col align="center" width="55">     <!-- Address Group Label        --> <!-- Add 2016/08/29 Unit Test#202-->
														<col align="center" width="130">    <!-- Ship To                    -->
														<col align="center" width="30">     <!-- DS                         -->
														<col align="center" width="130">    <!-- Bill To                    -->
														<col align="center" width="50">     <!-- Customer Declines Equipment Maintenance --> <!-- Add 2016/08/29 Unit Test#202-->
														<col align="center" width="90">     <!-- Qty                        -->
														<col align="center" width="110">    <!-- UOM                        -->
														<col align="center" width="100">    <!-- Each Qty                   -->
														<col align="center" width="110">    <!-- Sell Price                 -->
														<col align="center" width="110">    <!-- Net Price                  -->
														<col align="center" width="150">    <!-- Sell Price List            -->
														<col align="center" width="100">    <!-- Price Config Number        -->
														<col align="center" width="200">    <!-- Line Category              -->
														<col align="center" width="200">    <!-- Source                     -->
														<col align="center" width="100">    <!-- Warehouse(*)               -->
														<col align="center" width="100">    <!-- Sub Warehouse              -->
														<col align="center" width="100">    <!-- Serial#                    -->
														<col align="center" width="100">    <!-- List Price                 -->
														<col align="center" width="150">    <!-- Floor Price List           -->
														<col align="center" width="100">    <!-- Floor Price                -->
														<col align="center" width="100">    <!-- Subtotal                   -->
														<col align="center" width="100">    <!-- Mdse Type                  -->
														<col align="center" width="100">    <!-- Product Code               -->
														<col align="center" width="130">    <!-- Line Reference             -->
														<col align="center" width="100">    <!-- Price Date                 -->
														<col align="center" width="100">    <!-- Customer Item#             -->
														<col align="center" width="100">    <!-- Request Date               -->
														<col align="center" width="60">     <!-- Import Line -->
														<col align="center" width="60">     <!-- Financed Item -->
														<tr height="38">
															<td class="pClothBs colFix" id="AH00">&nbsp</td>
															<td class="pClothBs colFix" id="AH01">&nbsp</td>
															<td class="pClothBs colFix" id="AH02">&nbsp</td>
															<td class="pClothBs colFix" id="AH03">&nbsp</td>
															<td class="pClothBs colFix" id="AH04">Line#</td>
															<td class="pClothBs" id="AH05">Line Source Reference</td>
															<td class="pClothBs" id="AH06">Config Type</td>
															<td class="pClothBs" id="AH07">Config ID / Item#(*)</td>
															<td class="pClothBs" id="AH08">Model(*) / Description</td>
															<td class="pClothBs" id="AH09">Address Group</td> <!-- Add 2016/08/29 Unit Test#202-->
															<td class="pClothBs" id="AH10">Ship To</td>
															<td class="pClothBs" id="AH11">DS</td>
															<td class="pClothBs" id="AH12">Bill To</td>
															<td class="pClothBs" id="AH13">Decline Maint</td>
															<td class="pClothBs" id="AH14">Qty</td>
															<td class="pClothBs" id="AH15">UOM</td>
															<td class="pClothBs" id="AH16">Each Qty</td>
															<td class="pClothBs" id="AH17">Sell Price</td>
															<td class="pClothBs" id="AH18">Net Price</td>
															<td class="pClothBs" id="AH19">Sell Price List</td>
															<td class="pClothBs" id="AH20">Price Config Number</td>
															<td class="pClothBs" id="AH21">Line Category</td>
															<td class="pClothBs" id="AH22">Source</td>
															<td class="pClothBs" id="AH23">Warehouse(*)</td>
															<td class="pClothBs" id="AH24">Sub Warehouse</td>
															<td class="pClothBs" id="AH25">Serial#</td>
															<td class="pClothBs" id="AH27">List Price</td>
															<td class="pClothBs" id="AH26">Floor Price List</td>
															<td class="pClothBs" id="AH37">Floor Price</td>
															<td class="pClothBs" id="AH28">Subtotal</td>
															<td class="pClothBs" id="AH29">Mdse Type</td>
															<td class="pClothBs" id="AH30">Product Code</td>
															<td class="pClothBs" id="AH31">Line Reference</td>
															<td class="pClothBs" id="AH32">Price Date</td>
															<td class="pClothBs" id="AH33">Customer Item#</td>
															<td class="pClothBs" id="AH34">Request Date</td>
															<td class="pClothBs" id="AH35">Import Line</td>
															<td class="pClothBs" id="AH36">Financed Item</td>
														</tr>
													</table>
												</div>

												<div id="rightTbl" style="width:1118px; height:280px; display:block; overflow:scroll; margin:0px; padding:0px;" >
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A">
														<col align="center" width="30">     <!-- +/-                        -->
														<col align="center" width="30">     <!-- checkconfig                -->
														<col align="center" width="30">     <!-- checkline                  -->
														<col align="center" width="30">     <!-- &nbsp                      -->
														<col align="center" width="50">     <!-- Line#                      -->
														<col align="center" width="130">    <!-- Line Source Reference      -->
														<col align="center" width="100">    <!-- Config Type                -->
														<col align="center" width="155">    <!-- Config ID / Item#(*)       -->
														<col align="center" width="155">    <!-- Model(*) / Description     -->
														<col align="center" width="55">     <!-- Address Group Label        --> <!-- Add 2016/08/29 Unit Test#202-->
														<col align="center" width="130">    <!-- Ship To                    -->
														<col align="center" width="30">     <!-- DS                         -->
														<col align="center" width="130">    <!-- Bill To                    -->
														<col align="center" width="50">     <!-- Customer Declines Equipment Maintenance --> <!-- Add 2016/08/29 Unit Test#202-->
														<col align="center" width="90">     <!-- Qty                        -->
														<col align="center" width="110">    <!-- UOM                        -->
														<col align="center" width="100">    <!-- Each Qty                   -->
														<col align="center" width="110">    <!-- Sell Price                 -->
														<col align="center" width="110">    <!-- Net Price                  -->
														<col align="center" width="150">    <!-- Sell Price List            -->
														<col align="center" width="100">    <!-- Price Config Number        -->
														<col align="center" width="200">    <!-- Line Category              -->
														<col align="center" width="200">    <!-- Source                     -->
														<col align="center" width="100">    <!-- Warehouse(*)               -->
														<col align="center" width="100">    <!-- Sub Warehouse              -->
														<col align="center" width="100">    <!-- Serial#                    -->
														<col align="center" width="100">    <!-- List Price                 -->
														<col align="center" width="150">    <!-- Floor Price List           -->
														<col align="center" width="100">    <!-- Floor Price                -->
														<col align="center" width="100">    <!-- Subtotal                   -->
														<col align="center" width="100">    <!-- Mdse Type                  -->
														<col align="center" width="100">    <!-- Product Code               -->
														<col align="center" width="130">    <!-- Line Reference             -->
														<col align="center" width="100">    <!-- Price Date                 -->
														<col align="center" width="100">    <!-- Customer Item#             -->
														<col align="center" width="100">    <!-- Request Date               -->
														<col align="center" width="60">     <!-- Import Line -->
														<col align="center" width="60">     <!-- Financed Item -->
														
														<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
														<%@ page import="business.servlet.NWAL2200.NWAL2200BMsg" %>
														<%@ page import="business.servlet.NWAL2200.NWAL2200_ABMsg" %>
														<%@ page import="business.servlet.NWAL2200.NWAL2200_BBMsg" %>
														<% NWAL2200BMsg bMsg = (NWAL2200BMsg)databean.getEZDBMsg(); 
															int lineIdx = 0;
														%>

														<% for ( int i = 0; i < bMsg.A.getValidCount(); i++ ) { %>

															<tr height="24">
																<% NWAL2200_ABMsg lineMsg = bMsg.A.no(i); %>
																<td bgcolor="#bfd8d8">
																	<% boolean isSmryLine = "Y".equals(lineMsg.xxSmryLineFlg_L.getValue()); %>
																	<% if (isSmryLine) { %>
																		<img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('Line_Expand','<%= i %>')" ezfHyo="A" height="14">
																	<% } else if (!isSmryLine) { %>
																		<img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('Line_Collapsed','<%= i %>')" ezfHyo="A" height="14">
																	<% } %>
																</td>
																<td bgcolor="#bfd8d8"><ezf:inputCheckBox name="xxChkBox_LC" ezfName="xxChkBox_LC" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" /></td>
																<td bgcolor="#bfd8d8">&nbsp</td>
																<td bgcolor="#bfd8d8">&nbsp</td>
																<td bgcolor="#bfd8d8"><ezf:label name="dsOrdPosnNum_LC" ezfName="dsOrdPosnNum_LC" ezfHyo="A" ezfArrayNo="<%= i %>" /></td>
																<td bgcolor="#bfd8d8">&nbsp;</td>
																<td bgcolor="#bfd8d8"><ezf:inputText name="configTpDescTxt_LC" ezfName="configTpDescTxt_LC" value="New" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"12\""/></td>
																<td bgcolor="#bfd8d8"><ezf:inputText name="svcConfigMstrPk_LC" ezfName="svcConfigMstrPk_LC" value="517601" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"20\"" /></td>
																<td bgcolor="#bfd8d8"><ezf:inputText name="t_MdlNm_LC" ezfName="t_MdlNm_LC" value="IRADV4245" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"20\"" /></td>
																<td bgcolor="#bfd8d8"><ezf:inputText name="addrLbTxt_LC" ezfName="addrLbTxt_LC" value="AAA" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"6\"" /></td> <!-- Add 2016/08/29 Unit Test#202-->
																<td bgcolor="#bfd8d8">
																	<table border="0" cellpadding="1" cellspacing="0">
																		<tr style="padding:0;">
																			<td><ezf:inputText name="shipToCustLocCd_LC" ezfName="shipToCustLocCd_LC" value="1316621" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromShipToLocation" otherAttr=" size=\"15\" ezffocusout=\"OnBlur_DeriveFromShipToLocation\"" /></td>
																			<td>
																				<ezf:anchor name="xxImageTxt_AS" ezfName="xxImageTxt_AS" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"xxImageTxt_AS\" ezfanchortext=\"\"">S</ezf:anchor>
																			</td>
																		</tr>
																	</table>
																</td>
																<td align="center" bgcolor="#bfd8d8">
																	<ezf:anchor name="xxImageTxt_AD" ezfName="xxImageTxt_AD" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToChange" >
																		<ezf:label name="dropShipFlg_LC" ezfName="dropShipFlg_LC" ezfHyo="A" ezfArrayNo="<%= i %>" />
																	</ezf:anchor>
																</td>
																<td bgcolor="#bfd8d8">
																	<table border="0" cellpadding="1" cellspacing="0">
																		<tr style="padding:0;">
																			<td><ezf:inputText name="billToCustLocCd_LC" ezfName="billToCustLocCd_LC" value="3647770" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromBillToLocation" otherAttr=" size=\"15\" ezffocusout=\"OnBlur_DeriveFromBillToLocation\"" /></td>
																			<td>
																				<ezf:anchor name="xxImageTxt_AB" ezfName="xxImageTxt_AB" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"xxImageTxt_AB\" ezfanchortext=\"\"">B</ezf:anchor>
																			</td>
																		</tr>
																	</table>
																</td>
																<td bgcolor="#bfd8d8" align="center">
																	<span id="<%= i %>">
																		<ezf:inputCheckBox name="dclnSvcCd_LC" ezfName="dclnSvcCd_LC" value="1" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr="tabindex=\"0\"" otherEvent1=" onclick=\"sendServer('OnChange_DclnSvcCdConfig',this.parentNode.id)\""/>
																	</span>
																</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
															
															<% for ( int j = lineIdx, addrIdx = lineIdx; j < bMsg.B.getValidCount(); j++ ) { %>
															
																<%
																	NWAL2200_ABMsg configLineMsg = bMsg.A.no(i);
																	String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_LC.getValue();
																	String lineNums = bMsg.B.no(j).dsOrdPosnNum_LL.getValue();
																	if (!dsOrdPosnNum.equals(lineNums)) {
																		break;
																	}
																	lineIdx++;
																	if ("Y".equals(configLineMsg.xxSmryLineFlg_L.getValue())) {
																		continue;
																	}
																%>
																
																<tr height="24">
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																	<td><ezf:inputCheckBox name="xxChkBox_LL" ezfName="xxChkBox_LL" value="Y" ezfHyo="B" ezfArrayNo="<%= j %>" /></td>
																	<td>&nbsp;</td>
																	<td><ezf:label name="xxLineNum_LL" ezfName="xxLineNum_LL" ezfHyo="B" ezfArrayNo="<%= j %>" /></td>
																	<td><ezf:inputText name="xxScrItem130Txt_LL" ezfName="xxScrItem130Txt_LL" value="1.1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" style=\"border:none; background-color:transparent\" size=\"17\" ezftoupper=\"\""/></td>
																	<td>&nbsp;</td>
																	<td><ezf:inputText value="8030B003" name="mdseCd_LL" ezfName="mdseCd_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"20\" ezftoupper=\"\"" /></td>
																	<td align="left"><ezf:inputText name="mdseDescShortTxt_LL" ezfName="mdseDescShortTxt_LL" value="IMAGERUNNER  ADVANCE" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"20\""/></td>
																	<td>&nbsp;</td>  <!-- Add 2016/08/29 Unit Test#202-->
																	<% if (j == addrIdx) { %>
																	<td colspan="2"><ezf:inputText name="xxAllLineAddr_LS" ezfName="xxAllLineAddr_LS" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"21\"" /></td>
																	<% } else { %>
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																	<% } %>
																	<% if (j == addrIdx) { %>
																	<td colspan="2"><ezf:inputText name="xxAllLineAddr_LB" ezfName="xxAllLineAddr_LB" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"24\"" /></td>
																	<% } else { %>
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																	<% } %>
																	<td><ezf:inputText name="ordCustUomQty_LL" ezfName="ordCustUomQty_LL" value="1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"11\" style=\"text-align:right;\""/></td>
																	<td>
																		<ezf:inputText name="pkgUomDescTxt_LL" ezfName="pkgUomDescTxt_LL" value="Each" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"14\""/>
																	</td>
																	<td><ezf:inputText name="ordQty_LL" ezfName="ordQty_LL" value="1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\" style=\"text-align:right;\""/></td>
																	<td><ezf:inputText name="cpoDtlDealSlsAmt_LL" ezfName="cpoDtlDealSlsAmt_LL" value="5,109.00" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"14\" style=\"text-align:right;\""/></td>
																	<td><ezf:inputText name="entDealNetUnitPrcAmt_LL" ezfName="entDealNetUnitPrcAmt_LL" value="5,109.00" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"14\" style=\"text-align:right;\""/></td>
																	<td><ezf:inputText name="prcCatgNm_LL" ezfName="prcCatgNm_LL" value="FLOOR PRICE LIST - CBS" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"20\""/></td>
																	<td><ezf:inputText name="prcListEquipConfigNum_LL" ezfName="prcListEquipConfigNum_LL" value="123" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\""/></td>
																	<td>
																		<div id="<%= j %>">
																			<ezf:select name="dsOrdLineCatgCd_LL" ezfName="dsOrdLineCatgCd_LL" ezfHyo="B" ezfArrayNo="<%= j %>" ezfBlank="1" ezfCodeName="dsOrdLineCatgCd_CD" ezfDispName="dsOrdLineCatgDescTxt_NM" otherAttr=" style=\"width:188px;\"">
																			</ezf:select>
																		</div>
																	</td>
																	<td>
																		<ezf:select name="ordLineSrcCd_LL" ezfName="ordLineSrcCd_LL" ezfHyo="B" ezfArrayNo="<%= j %>" ezfBlank="1" ezfCodeName="ordLineSrcCd_CD" ezfDispName="ordLineSrcNm_NM" otherAttr=" style=\"width:188px;\"">
																		</ezf:select>
																	</td>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0">
																			<tr style="padding:0;">
																				<td><ezf:inputText name="rtlWhNm_LL" ezfName="rtlWhNm_LL" ezfHyo="B" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_ChangeWH" otherAttr=" size=\"11\" maxlength=\"60\" ezffocusout=\"OnBlur_ChangeWH\" ezftoupper=\"\"" /></td>
																				<td>
																					<ezf:anchor name="xxImageTxt_BW" ezfName="xxImageTxt_BW" ezfHyo="B" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" id=\"xxImageTxt_BW\" ezfanchortext=\"\"">W</ezf:anchor>
																				</td>
																			</tr>
																		</table>
																	</td>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0">
																			<tr style="padding:0;">
																				<td>
																					<ezf:inputText name="rtlSwhNm_LL" ezfName="rtlSwhNm_LL" value="NEW" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"11\" ezftoupper=\"\""/>
																				</td>
																				<td>
																					<ezf:anchor name="xxImageTxt_BS" ezfName="xxImageTxt_BS" ezfHyo="B" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWarehouse" otherAttr=" id=\"xxImageTxt_BS\" ezfanchortext=\"\"">S</ezf:anchor>
																				</td>
																			</tr>
																		</table>
																	</td>
																	<td>
																		<ezf:inputText name="serNum_LL" ezfName="serNum_LL" value="12345" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\" ezftoupper=\"\""/>
																	</td>
																	<td><ezf:inputText name="dealPrcListPrcAmt_LL" ezfName="dealPrcListPrcAmt_LL" value="5,109.00" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\" style=\"text-align:right;\""/></td>
																	<td><ezf:inputText name="flPrcListDescTxt_LL" ezfName="flPrcListDescTxt_LL" value="FLOOR PRICE LIST - CBS" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"20\""/></td>
																	<td><ezf:inputText name="funcUnitFlPrcAmt_LL" ezfName="funcUnitFlPrcAmt_LL" value="5,109.00" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\" style=\"text-align:right;\""/></td>
																	<td><ezf:inputText name="lineDealSubTotAmt_LL" ezfName="lineDealSubTotAmt_LL" value="5,109.00" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\" style=\"text-align:right;\""/></td>
																	<td><ezf:inputText name="coaMdseTpDescTxt_LL" ezfName="coaMdseTpDescTxt_LL" value="MACHINE" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\""/></td>
																	<td><ezf:inputText name="coaProdDescTxt_LL" ezfName="coaProdDescTxt_LL" value="CS BW Copier" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"13\""/></td>
																	<td><ezf:inputText name="dplyLineRefNum_LL" ezfName="dplyLineRefNum_LL" value="1.1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"16\""/></td>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0">
																			<tr style="padding:0;">
																				<td><ezf:inputText name="prcBaseDt_LL" ezfName="prcBaseDt_LL" value="01/20/2016" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\""/></td>
																			</tr>
																		</table>
																	</td>
																	<td><ezf:inputText name="custMdseCd_LL" ezfName="custMdseCd_LL" value="8030B003" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\""/></td>
																	<td><ezf:inputText name="rddDt_LL" ezfName="rddDt_LL" value="01/20/2016" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\""/></td>
																	<td><ezf:inputCheckBox name="imptLineFlg_LL" ezfName="imptLineFlg_LL" value="Y" ezfHyo="B" ezfArrayNo="<%= j %>" /></td>
																	<td><ezf:inputCheckBox name="finItemLineFlg_LL" ezfName="finItemLineFlg_LL" value="Y" ezfHyo="B" ezfArrayNo="<%= j %>" /></td>
																</tr>
															<% } %>
														<% } %>
													</table>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</table>

							<script type="text/javascript" defer>
								S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, false, true);
							</script>

						</div>
					</c:when>
<%-- ######################################## LINE CONFIG TAB ######################################## --%>
<%-- ######################################## RMA TAB ######################################## --%>
					<c:when test="${pageScope._ezddatabean.xxDplyTab=='RMA'}">
						<script type="text/javascript">
							document.getElementById( "Header" ).className="pTab_OFF";
							document.getElementById( "Addl_Header" ).className="pTab_OFF";
							document.getElementById( "Line_Config" ).className="pTab_OFF";
							document.getElementById( "RMA" ).className="pTab_ON";
							document.getElementById( "Errors" ).className="pTab_OFF";
						</script>
						<div class="pTab_BODY_In">
							<table>
								<col>
								<col>
								<col>
								<col>
								<col>
								<col>
								<col>
								<col align="right" >
								<tr>
									<td>
										<ezf:inputButton name="Line_All_Expand" value="All Expand" htmlClass="pBtn6" />
									</td>
									<td>
										<ezf:inputButton name="Line_All_Collapsed" value="All Collapse" htmlClass="pBtn6" />
									</td>
									<td>
										<ezf:inputButton name="OpenWin_PriceChanges" value="Price Changes" htmlClass="pBtn8" />
									</td>
									<td>
										<ezf:inputButton name="OpenWin_SalesCredit" value="Sales Credit" htmlClass="pBtn6" />
									</td>
									<td>
										<ezf:inputButton name="OpenWin_AddressMassApplyRma" value="Addr Mass Apply" htmlClass="pBtn9" />
									</td>
									<td>
										<ezf:inputButton name="OpenWin_AddlLineDetails" value="Addl Line Details" htmlClass="pBtn9" />
									</td>
									<td>
										<ezf:inputButton name="Config_Check_All" value="Config Check All" htmlClass="pBtn9" />
									</td>
									<td>
										<ezf:inputButton name="Config_UnCheck_All" value="Config UnCheck All" htmlClass="pBtn9" />
									</td>
								</tr>
							</table>
							<table width="1101">
								<col width="165">
								<col>
								<col width="300" align="right">
								<col width="550" align="right">
								<tr>
									<td>
										<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
									</td>
									<td>
									</td>
									<td>
										Config#
										<ezf:inputText name="dsOrdPosnNum_DR" ezfName="dsOrdPosnNum_DR" value="200" otherAttr=" size=\"4\" maxlength=\"3\""/>
										/
										<ezf:label name="dsOrdPosnNum_TR" ezfName="dsOrdPosnNum_TR" />
										<ezf:inputButton name="ConfigJump" value="Config Jump" htmlClass="pBtn8" />
									</td>
									<td>
										<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
										<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
										<jsp:param name="TableNm"     value="D" />
										<jsp:param name="ShowingFrom"       value="xxPageShowFromNum_RC" />
										<jsp:param name="ShowingTo"         value="xxPageShowToNum_RC" />
										<jsp:param name="ShowingOf"         value="xxPageShowOfNum_RC" />
										<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum_RC" />
										<jsp:param name="ShowingTotal"      value="xxPageShowTotNum_RC" />
										<jsp:param name="ViewMode"          value="FULL" />
										</jsp:include>
									</td>
								</tr>
							</table>
							
							<table>
								<tr>
									<td>
										<div id="parentBoxC">
											<div style="float:left; display:block"><!-- left table -->
												<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
												<div id="leftTbl" style="float:left; display:block; height:263px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
											</div><!-- left table -->
											<div style="float:left"><!-- right table -->
												<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="CHEAD" width="3340px" style="margin-right:20px">
														<col align="center" width="30">     <!-- +/-                        -->
														<col align="center" width="30">     <!-- config check               -->
														<col align="center" width="30">     <!-- line check                 -->
														<col align="center" width="30">     <!-- &nbsp                      -->
														<col align="center" width="50">     <!-- Line#                      -->
														<col align="center" width="130">    <!-- Line Source Reference      -->
														<col align="center" width="100">    <!-- Config Type                -->
														<col align="center" width="155">    <!-- Config ID / Item#(*)       -->
														<col align="center" width="155">    <!-- Model(*) / Description     -->
														<col align="center" width="55">     <!-- Address Group Label        --> <!-- Add 2016/08/29 Unit Test#202-->
														<col align="center" width="130">    <!-- Ship To                    -->
														<col align="center" width="30">     <!-- DS                         -->
														<col align="center" width="130">    <!-- Bill To                    -->
														<col align="center" width="90">     <!-- Qty                        -->
														<col align="center" width="110">    <!-- UOM                        -->
														<col align="center" width="100">    <!-- Each Qty                   -->
														<col align="center" width="110">    <!-- Sell Price                 -->
														<col align="center" width="110">    <!-- Net Price                  -->
														<col align="center" width="150">    <!-- Sell Price List            -->
														<col align="center" width="190">    <!-- Line Category              -->
														<col align="center" width="190">    <!-- Source                     -->
														<col align="center" width="100">    <!-- Warehouse(*)               -->
														<col align="center" width="100">    <!-- Sub Warehouse              -->
														<col align="center" width="100">    <!-- Serial#                    -->
														<col align="center" width="100">    <!-- List Price                 -->
														<col align="center" width="150">    <!-- Floor Price List           -->
														<col align="center" width="100">    <!-- Floor Price                 -->
														<col align="center" width="100">    <!-- Subtotal                   -->
														<col align="center" width="100">    <!-- Mdse Type                  -->
														<col align="center" width="100">    <!-- Product Code               -->
														<col align="center" width="130">    <!-- Line Reference             -->
														<col align="center" width="100">    <!-- Price Date                 -->
														<col align="center" width="100">    <!-- Customer Item#             -->
														<col align="center" width="110">    <!-- Rqst PickUp Date           -->
														<col align="center" width="210">    <!-- Return Reason Code         -->
														<col align="center" width="110">    <!-- HDD Removal                -->
														<tr height="38">
															<td class="pClothBs colFix" id="CH00">&nbsp</td>
															<td class="pClothBs colFix" id="CH01">&nbsp</td>
															<td class="pClothBs colFix" id="CH02">&nbsp</td>
															<td class="pClothBs colFix" id="CH03">&nbsp</td>
															<td class="pClothBs colFix" id="CH04">Line#</td>
															<td class="pClothBs" id="CH05">Line Source Reference</td>
															<td class="pClothBs" id="CH06">Config Type</td>
															<td class="pClothBs" id="CH07">Config ID / Item#(*)</td>
															<td class="pClothBs" id="CH08">Model(*) / Description</td>
															<td class="pClothBs" id="CH09">Address Group</td> <!-- Add 2016/08/29 Unit Test#202-->
															<td class="pClothBs" id="CH10">Ship To</td>
															<td class="pClothBs" id="CH11">DS</td>
															<td class="pClothBs" id="CH12">Bill To</td>
															<td class="pClothBs" id="CH13">Qty</td>
															<td class="pClothBs" id="CH14">UOM</td>
															<td class="pClothBs" id="CH15">Each Qty</td>
															<td class="pClothBs" id="CH16">Sell Price</td>
															<td class="pClothBs" id="CH17">Net Price</td>
															<td class="pClothBs" id="CH18">Sell Price List</td>
															<td class="pClothBs" id="CH19">Line Category</td>
															<td class="pClothBs" id="CH20">Source</td>
															<td class="pClothBs" id="CH21">Warehouse(*)</td>
															<td class="pClothBs" id="CH22">Sub Warehouse</td>
															<td class="pClothBs" id="CH23">Serial#</td>
															<td class="pClothBs" id="CH25">List Price</td>
															<td class="pClothBs" id="CH24">Floor Price List</td>
															<td class="pClothBs" id="CH35">Floor Price</td>
															<td class="pClothBs" id="CH26">Subtotal</td>
															<td class="pClothBs" id="CH27">Mdse Type</td>
															<td class="pClothBs" id="CH28">Product Code</td>
															<td class="pClothBs" id="CH29">Line Reference</td>
															<td class="pClothBs" id="CH30">Price Date</td>
															<td class="pClothBs" id="CH31">Customer Item#</td>
															<td class="pClothBs" id="CH32">Rqst PickUp Date</td>
															<td class="pClothBs" id="CH33">Return Reason Code</td>
															<td class="pClothBs" id="CH34">HDD Removal</td>
														</tr>
													</table>
												</div>

												<div id="rightTbl" style="width:1118px; height:280px; display:block; overflow:scroll; margin:0px; padding:0px;" >
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="C" width="3340px">
														<col align="center" width="30">     <!-- +/-                        -->
														<col align="center" width="30">     <!-- config check               -->
														<col align="center" width="30">     <!-- line check                 -->
														<col align="center" width="30">     <!-- &nbsp                      -->
														<col align="center" width="50">     <!-- Line#                      -->
														<col align="center" width="130">    <!-- Line Source Reference      -->
														<col align="center" width="100">    <!-- Config Type                -->
														<col align="center" width="155">    <!-- Config ID / Item#(*)       -->
														<col align="center" width="155">    <!-- Model(*) / Description     -->
														<col align="center" width="55">     <!-- Address Group Label        --> <!-- Add 2016/08/29 Unit Test#202-->
														<col align="center" width="130">    <!-- Ship To                    -->
														<col align="center" width="30">     <!-- DS                         -->
														<col align="center" width="130">    <!-- Bill To                    -->
														<col align="center" width="90">     <!-- Qty                        -->
														<col align="center" width="110">    <!-- UOM                        -->
														<col align="center" width="100">    <!-- Each Qty                   -->
														<col align="center" width="110">    <!-- Sell Price                 -->
														<col align="center" width="110">    <!-- Net Price                  -->
														<col align="center" width="150">    <!-- Sell Price List            -->
														<col align="center" width="190">    <!-- Line Category              -->
														<col align="center" width="190">    <!-- Source                     -->
														<col align="center" width="100">    <!-- Warehouse(*)               -->
														<col align="center" width="100">    <!-- Sub Warehouse              -->
														<col align="center" width="100">    <!-- Serial#                    -->
														<col align="center" width="100">    <!-- List Price                 -->
														<col align="center" width="150">    <!-- Floor Price List           -->
														<col align="center" width="100">    <!-- Floor Price                -->
														<col align="center" width="100">    <!-- Subtotal                   -->
														<col align="center" width="100">    <!-- Mdse Type                  -->
														<col align="center" width="100">    <!-- Product Code               -->
														<col align="center" width="130">    <!-- Line Reference             -->
														<col align="center" width="100">    <!-- Price Date                 -->
														<col align="center" width="100">    <!-- Customer Item#             -->
														<col align="center" width="110">    <!-- Rqst PickUp Date           -->
														<col align="center" width="210">    <!-- Return Reason Code         -->
														<col align="center" width="110">    <!-- HDD Removal                -->
														
														<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
														<%@ page import="business.servlet.NWAL2200.NWAL2200BMsg" %>
														<%@ page import="business.servlet.NWAL2200.NWAL2200_CBMsg" %>
														<%@ page import="business.servlet.NWAL2200.NWAL2200_DBMsg" %>
														<% NWAL2200BMsg bMsg = (NWAL2200BMsg)databean.getEZDBMsg(); 
															int lineIdx = 0;
														%>

														<% for ( int i = 0; i < bMsg.C.getValidCount(); i++ ) { %>

															<tr height="24">
																<% NWAL2200_CBMsg lineMsg = bMsg.C.no(i); %>
																<td bgcolor="#bfd8d8">
																	<% boolean isSmryLine = "Y".equals(lineMsg.xxSmryLineFlg_R.getValue()); %>
																	<% if (isSmryLine) { %>
																		<img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('Line_Expand','<%= i %>')" ezfHyo="C" height="14">
																	<% } else if (!isSmryLine) { %>
																		<img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('Line_Collapsed','<%= i %>')" ezfHyo="C" height="14">
																	<% } %>
																</td>
																<td bgcolor="#bfd8d8"><ezf:inputCheckBox name="xxChkBox_RC" ezfName="xxChkBox_RC" value="Y" ezfHyo="C" ezfArrayNo="<%= i %>" /></td>
																<td bgcolor="#bfd8d8">&nbsp</td>
																<td bgcolor="#bfd8d8">&nbsp</td>
																<td bgcolor="#bfd8d8"><ezf:label name="dsOrdPosnNum_RC" ezfName="dsOrdPosnNum_RC" ezfHyo="C" ezfArrayNo="<%= i %>" /></td>
																<td bgcolor="#bfd8d8">&nbsp</td>
																<td bgcolor="#bfd8d8"><ezf:inputText name="configTpDescTxt_RC" ezfName="configTpDescTxt_RC" value="Return Existing IB from Customer" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr=" size=\"12\""/></td>
																<td bgcolor="#bfd8d8"><ezf:inputText name="svcConfigMstrPk_RC" ezfName="svcConfigMstrPk_RC" value="520346" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr=" size=\"20\""/></td>
																<td bgcolor="#bfd8d8"><ezf:inputText name="t_MdlNm_RC" ezfName="t_MdlNm_RC" value="XXXXXXXX" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr=" size=\"20\""/></td>
																<td bgcolor="#bfd8d8"><ezf:inputText name="addrLbTxt_RC" ezfName="addrLbTxt_RC" value="AAA" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr=" size=\"6\""/></td>
																<td bgcolor="#bfd8d8">
																	<table border="0" cellpadding="1" cellspacing="0">
																		<tr style="padding:0;">
																			<td><ezf:inputText name="shipToCustLocCd_RC" ezfName="shipToCustLocCd_RC" value="2633128" ezfHyo="C" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromShipToLocation" otherAttr=" size=\"15\" ezffocusout=\"OnBlur_DeriveFromShipToLocation\"" /></td>
																			<td>
																				<ezf:anchor name="xxImageTxt_CS" ezfName="xxImageTxt_CS" ezfHyo="C" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" id=\"xxImageTxt_CS\" ezfanchortext=\"\"">S</ezf:anchor>
																			</td>
																		</tr>
																	</table>
																</td>
																<td align="center" bgcolor="#bfd8d8">
																	<ezf:anchor name="xxImageTxt_CD" ezfName="xxImageTxt_CD" ezfHyo="C" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToChange" >
																		<ezf:label name="dropShipFlg_RC" ezfName="dropShipFlg_RC" ezfHyo="C" value="N" ezfArrayNo="<%= i %>" />
																	</ezf:anchor>
																</td>
																<td bgcolor="#bfd8d8">
																	<table border="0" cellpadding="1" cellspacing="0">
																		<tr style="padding:0;">
																			<td><ezf:inputText name="billToCustLocCd_RC" ezfName="billToCustLocCd_RC" value="2633175" ezfHyo="C" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromBillToLocation" otherAttr=" size=\"15\" ezffocusout=\"OnBlur_DeriveFromBillToLocation\"" /></td>
																			<td>
																				<ezf:anchor name="xxImageTxt_CB" ezfName="xxImageTxt_CB" ezfHyo="C" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"xxImageTxt_CB\" ezfanchortext=\"\"">B</ezf:anchor>
																			</td>
																		</tr>
																	</table>
																</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
															
															<% for ( int j = lineIdx, addrIdx = lineIdx; j < bMsg.D.getValidCount(); j++ ) { %>
															
																<%
																	NWAL2200_CBMsg configLineMsg = bMsg.C.no(i);
																	String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_RC.getValue();
																	
																	String lineNums = bMsg.D.no(j).dsOrdPosnNum_RL.getValue();
																	if (!dsOrdPosnNum.equals(lineNums)) {
																		break;
																	}
																	lineIdx++;
																	if ("Y".equals(configLineMsg.xxSmryLineFlg_R.getValue())) {
																		continue;
																	}
																%>
																
																<tr height="24">
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																	<td><ezf:inputCheckBox name="xxChkBox_RL" ezfName="xxChkBox_RL" value="Y" ezfHyo="D" ezfArrayNo="<%= j %>" /></td>
																	<td>&nbsp;</td>
																	<td><ezf:label name="xxLineNum_RL" ezfName="xxLineNum_RL" ezfHyo="D" ezfArrayNo="<%= j %>" /></td>
																	<td><ezf:inputText name="xxScrItem130Txt_RL" ezfName="xxScrItem130Txt_RL" value="3647770" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" style=\"border:none; background-color:transparent\" size=\"17\" ezftoupper=\"\""/></td>
																	<td>&nbsp;</td>
																	<td><ezf:inputText name="mdseCd_RL" ezfName="mdseCd_RL" value="5561B066AA" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"20\""/></td>
																	<td><ezf:inputText name="mdseDescShortTxt_RL" ezfName="mdseDescShortTxt_RL" value="IMAGERUNNER" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"20\""/></td>
																	<td>&nbsp;</td>
																	<% if (j == addrIdx) { %>
																	<td colspan="2"><ezf:inputText name="xxAllLineAddr_RS" ezfName="xxAllLineAddr_RS" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr=" size=\"21\"" /></td>
																	<% } else { %>
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																	<% } %>
																	<% if (j == addrIdx) { %>
																	<td><ezf:inputText name="xxAllLineAddr_RB" ezfName="xxAllLineAddr_RB" ezfHyo="C" ezfArrayNo="<%= i %>" otherAttr=" size=\"16\"" /></td>
																	<% } else { %>
																	<td>&nbsp;</td>
																	<% } %>
																	<td><ezf:inputText name="ordCustUomQty_RL" ezfName="ordCustUomQty_RL" value="-1" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"11\" style=\"text-align:right;\""/></td>
																	<td><ezf:inputText name="pkgUomDescTxt_RL" ezfName="pkgUomDescTxt_RL" value="Each" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"14\""/></td>
																	<td><ezf:inputText name="ordQty_RL" ezfName="ordQty_RL" value="-1" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\" style=\"text-align:right;\""/></td>
																	<td><ezf:inputText name="cpoDtlDealSlsAmt_RL" ezfName="cpoDtlDealSlsAmt_RL" value="0.00" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"14\" style=\"text-align:right;\""/></td>
																	<td><ezf:inputText name="entDealNetUnitPrcAmt_RL" ezfName="entDealNetUnitPrcAmt_RL" value="0.00" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"14\" style=\"text-align:right;\""/></td>
																	<td><ezf:inputText name="prcCatgNm_RL" ezfName="prcCatgNm_RL" value="SUPPLY TEST" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"20\""/></td>
																	<td>
																		<div id="<%= j %>">
																			<ezf:select name="dsCpoLineCatgCd_RL" ezfName="dsCpoLineCatgCd_RL" ezfHyo="D" ezfArrayNo="<%= j %>" ezfBlank="1" ezfCodeName="dsOrdLineCatgCd_CR" ezfDispName="dsOrdLineCatgDescTxt_NR" otherAttr=" style=\"width:180;\"">
																			</ezf:select>
																		</div>
																	</td>
																	<td>
																		<div id="<%= j %>">
																			<ezf:select name="ordLineSrcCd_RL" ezfName="ordLineSrcCd_RL" ezfHyo="D" ezfArrayNo="<%= j %>" ezfBlank="1" ezfCodeName="ordLineSrcCd_CD" ezfDispName="ordLineSrcNm_NM" otherAttr=" style=\"width:180px;\"">
																			</ezf:select>
																		</div>
																	</td>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0">
																			<tr style="padding:0;">
																				<td><ezf:inputText name="rtlWhNm_RL" ezfName="rtlWhNm_RL" ezfHyo="D" ezfArrayNo="<%= j %>" otherEvent1="OnBlur_ChangeWH" otherAttr=" size=\"11\" maxlength=\"60\" ezffocusout=\"OnBlur_ChangeWH\" ezftoupper=\"\"" /></td>
																				<td>
																					<ezf:anchor name="xxImageTxt_DW" ezfName="xxImageTxt_DW" ezfHyo="D" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" id=\"xxImageTxt_DW\" ezfanchortext=\"\"">W</ezf:anchor>
																				</td>
																			</tr>
																		</table>
																	</td>
																	<td>
																		<ezf:inputText name="rtlSwhNm_RL" ezfName="rtlSwhNm_RL" value="USED 70" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\" ezftoupper=\"\""/>
																	</td>
																	<td>
																		<ezf:inputText name="serNum_RL" ezfName="serNum_RL" value="12345678" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\" ezftoupper=\"\""/>
																	</td>
																	<td><ezf:inputText name="dealPrcListPrcAmt_RL" ezfName="dealPrcListPrcAmt_RL" value="0.00" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\""/></td>
																	<td><ezf:inputText name="flPrcListDescTxt_RL" ezfName="flPrcListDescTxt_RL" value="FLOOR PRICE LIST - CBS" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"20\""/></td>
																	<td><ezf:inputText name="funcUnitFlPrcAmt_RL" ezfName="funcUnitFlPrcAmt_RL" value="0.00" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\""/></td>
																	<td><ezf:inputText name="lineDealSubTotAmt_RL" ezfName="lineDealSubTotAmt_RL" value="0.00" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\""/></td>
																	<td><ezf:inputText name="coaMdseTpDescTxt_RL" ezfName="coaMdseTpDescTxt_RL" value="MACHINE" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\""/></td>
																	<td><ezf:inputText name="coaProdDescTxt_RL" ezfName="coaProdDescTxt_RL" value="CS Color Copier" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\""/></td>
																	<td><ezf:inputText name="dplyLineRefNum_RL" ezfName="dplyLineRefNum_RL" value="1.1" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"16\""/></td>
																	<td><ezf:inputText name="prcBaseDt_RL" ezfName="prcBaseDt_RL" value="01/20/2016" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\""/></td>
																	<td><ezf:inputText name="custMdseCd_RL" ezfName="custMdseCd_RL" value="5561B066AA" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\""/></td>
																	<td><ezf:inputText name="rqstPickUpDt_RL" ezfName="rqstPickUpDt_RL" value="01/20/2016" ezfHyo="D" ezfArrayNo="<%= j %>" otherAttr=" size=\"12\""/></td>
																	<td>
																		<div id="<%= j %>">
																			<ezf:select name="rtrnRsnCd_RL" ezfName="rtrnRsnCd_RL" ezfHyo="D" ezfArrayNo="<%= j %>" ezfBlank="1" ezfCodeName="rtrnRsnCd_CD" ezfDispName="rtrnRsnDescTxt_NM" otherAttr=" style=\"width:200px;\"">
																			</ezf:select>
																		</div>
																	</td>
																	<td>
																		<div id="<%= j %>">
																			<ezf:select name="hddRmvCd_RL" ezfName="hddRmvCd_RL" ezfHyo="D" ezfArrayNo="<%= j %>" ezfBlank="1" ezfCodeName="hddRmvCd_CD" ezfDispName="hddRmvDescTxt_NM" otherAttr=" style=\"width:100px;\"">
																			</ezf:select>
																		</div>
																	</td>
																</tr>
															<% } %>
														<% } %>
													</table>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</table>
							
							<script type="text/javascript" defer>
								S21TableUI.initialize("parentBoxC", "CHEAD", "C", -1, false, true);
							</script>

						</div>
					</c:when>
<%-- ######################################## RMA TAB ######################################## --%>
<%-- ######################################## ERRORS TAB ######################################## --%>
					<c:when test="${pageScope._ezddatabean.xxDplyTab=='Errors'}">
						<script type="text/javascript">
							document.getElementById( "Header" ).className="pTab_OFF";
							document.getElementById( "Addl_Header" ).className="pTab_OFF";
							document.getElementById( "Line_Config" ).className="pTab_OFF";
							document.getElementById( "RMA" ).className="pTab_OFF";
							document.getElementById( "Errors" ).className="pTab_ON";
						</script>
						<div class="pTab_BODY_In">
							<table width="1101">
								<col width="165">
								<col>
								<col width="550" align="right">
								<tr>
									<td>
										<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
									</td>
									<td>
									</td>
									<td>
										<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
										<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
										<jsp:param name="TableNm"     value="E" />
										<jsp:param name="ShowingFrom"       value="xxPageShowFromNum_EL" />
										<jsp:param name="ShowingTo"         value="xxPageShowToNum_EL" />
										<jsp:param name="ShowingOf"         value="xxPageShowOfNum_EL" />
										<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum_EL" />
										<jsp:param name="ShowingTotal"      value="xxPageShowTotNum_EL" />
										</jsp:include>
									</td>
								</tr>
							</table>
							
							<table>
								<tr>
									<td>
										<div id="parentBoxE">
											<div style="float:left; display:block"><!-- left table -->
												<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
												<div id="leftTbl" style="float:left; display:block; height:263px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
											</div><!-- left table -->
											<div style="float:left"><!-- right table -->
												<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="EHEAD" width="" style="margin-right:20px">
														<col align="center" width="80"  >    <!-- Line#                      -->
														<col align="center" width="155" >    <!-- Config ID / Item#(*)       -->
														<col align="center" width="96"  >    <!-- Level                      -->
														<col align="center" width="764" >    <!-- Error Text                 -->
														<tr height="24">
															<td class="pClothBs colFix"  id="EH0">Line#</td>
															<td class="pClothBs"         id="EH1">Config ID</td>
															<td class="pClothBs"         id="EH2">Level</td>
															<td class="pClothBs"         id="EH3">Error Text</td>
														</tr>
													</table>
												</div>

												<div id="rightTbl" style="width:1118px; height:340px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="E" width="">
														<col align="center" width="80" >    <!-- Line#                      -->
														<col align="center" width="155">    <!-- Config ID / Item#(*)       -->
														<col align="center" width="96" >    <!-- Level                      -->
														<col align="center" width="764">    <!-- Error Text                 -->
														<ezf:row ezfHyo="E">
															<tr height="24">
																<td><ezf:inputText name="xxLineNum_EL" ezfName="xxLineNum_EL" value="1" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"text-align:center;border:none;background-color:transparent;\""/></td>
																<td><ezf:inputText name="svcConfigMstrPk_EL" ezfName="svcConfigMstrPk_EL" value="123456" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;\""/></td>
																<td><ezf:inputText name="xxScrItem30Txt_EL" ezfName="xxScrItem30Txt_EL" value="Config" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none;background-color:transparent;\""/></td>
																<td class="pClothR"><ezf:inputText name="dsImptOrdErrTxt_EL" ezfName="dsImptOrdErrTxt_EL" value="Errors Message" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"107\" style=\"border:none;background-color:transparent;\""/></td>
															</tr>
														</ezf:row>
														<ezf:skip>
															<tr height="24">
																<td><input type="text" class="pPro" size="10"  value="1.1"       ezfHyo="E" style="text-align:center;" /></td>
																<td><input type="text" class="pPro" size="20"  value="123456"    ezfHyo="E"  /></td>
																<td><input type="text" class="pPro" size="12"   value="Line"     ezfHyo="E"  /></td>
																<td style="background-color:Yellow;"><input type="text" class="pPro" size="107" style="border:none;background-color:transparent;" value="Warning Message" ezfHyo="E"  /></td>
															</tr>
															<tr height="24">
																<td><input type="text" class="pPro" size="10"  value="1.1"       ezfHyo="E"  style="text-align:center;" /></td>
																<td><input type="text" class="pPro" size="20"  value="123456"    ezfHyo="E"  /></td>
																<td><input type="text" class="pPro" size="12"  value="RMA Line" ezfHyo="E"  /></td>
																<td style="background-color:Red;"><input type="text" class="pPro" size="107" style="border:none;background-color:transparent;" value="Error Message" ezfHyo="E"  /></td>
															</tr>
														</ezf:skip>
													</table>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</table>
							
							<script type="text/javascript" defer>
								S21TableUI.initialize("parentBoxE", "EHEAD", "E", -1, false, true);
							</script>

						</div>
					</c:when>
<%-- ######################################## ERRORS TAB ######################################## --%>
				</c:choose>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
