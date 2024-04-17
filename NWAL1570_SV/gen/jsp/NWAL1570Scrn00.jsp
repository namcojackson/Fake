<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240229095032 --%>
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
			<input type="hidden" name="pageID" value="NWAL1570Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Order Inquiry">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center">
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<%-- @Upper TAB --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Location Info" class="pTab_ON"><a href="#">Order Inquiry</a></li>
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

				<%-- @Upper TAB frame --%>
				<div class="pTab_BODY">
					<%------------------------------------%>
					<%-- Save Option					--%>
					<%------------------------------------%>
					<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="152">
						<col width="345">
						<col width="110">
						<col width="300">
						<col width="">
						<tr>
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk_H1" ezfName="srchOptPk_H1" ezfBlank="1" ezfCodeName="srchOptPk_L1" ezfDispName="srchOptNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_SavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td class="stab"><ezf:inputText name="srchOptNm_H1" ezfName="srchOptNm_H1" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td>
								<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
								<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					<hr style="height: 0px;" cellpadding="0">
					<table border="0" cellpadding="0" cellspacing="0" align="center">
						<tr valign="top">
<!-- ############################## START - Header Search Criteria -->
							<td align="center" width="640" height="265px">
								<fieldset>
									<legend>&nbsp;<font size="2" color="black">Header Search Criteria</font>&nbsp;</legend>
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="top" width="420">
												<table border="0" cellpadding="0" cellspacing="1">
													<col width="100">
													<col width="90">
													<col width="10">
													<col width="90">
													<col width="90">
													<tr>
														<!-- Order Number -->
														<td class="stab">Order# (*)</td>
														<td><ezf:inputText name="xxCpoOrdNumSrchTxt_H1" ezfName="xxCpoOrdNumSrchTxt_H1" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Orignal Order Number -->
														<td class="stab ">Orig. Order# (*)</td>
														<td><ezf:inputText name="xxCpoOrdNumSrchTxt_H2" ezfName="xxCpoOrdNumSrchTxt_H2" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Customer Perchase Order Number -->
														<td class="stab ">Cust. PO# (*)</td>
														<td><ezf:inputText name="custIssPoNumSrchTxt" ezfName="custIssPoNumSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Lease Perchase Order Number -->
														<td class="stab ">Lease PO# (*)</td>
														<td><ezf:inputText name="xxLeasePoNumSrchTxt" ezfName="xxLeasePoNumSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Sold To Name -->
														<td class="stab"><ezf:anchor name="soldToCustAcctNm_LK" ezfName="soldToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldToName" otherAttr=" id=\"soldToCustAcctNm_LK\" ezfanchortext=\"\"">Sold To Name (*)</ezf:anchor></td>
														<td colspan = "4"><ezf:inputText name="xxSoldToAcctNmSrchTxt" ezfName="xxSoldToAcctNmSrchTxt" value="CANON BULLS" otherAttr=" size=\"46\" maxlength=\"60\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Sold To Acct# -->
														<td class="stab"><ezf:anchor name="soldToCustAcctNm_LK" ezfName="soldToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldToAcctNumber" otherAttr=" id=\"soldToCustAcctNm_LK\" ezfanchortext=\"\"">Sold To Acct# (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxSoldToAcctCdSrchTxt" ezfName="xxSoldToAcctCdSrchTxt" value="11111" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
														<td></td>
														<!-- Sold To Loc# -->
														<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
														<!-- <td class="stab"><a href="#" id="soldToCustAcctNm_LK" name="soldToCustAcctNm_LK" ezfName="soldToCustAcctNm_LK" onclick="sendServer('OpenWin_SoldToLocNumber')" ezfanchortext>Sold To Loc# (*)</a></td> -->
														<td class="stab"><ezf:anchor name="soldToCustAcctNm_LK" ezfName="soldToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldToLocNumber" otherAttr=" id=\"soldToCustAcctNm_LK\" ezfanchortext=\"\"">Sold To Code (*)</ezf:anchor></td>
														<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
														<td><ezf:inputText name="xxSoldToLocCdSrchTxt" ezfName="xxSoldToLocCdSrchTxt" value="22222" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Ship To Name -->
														<td class="stab"><ezf:anchor name="ShipToCustAcctNm_LK" ezfName="ShipToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToName" otherAttr=" id=\"ShipToCustAcctNm_LK\" ezfanchortext=\"\"">Ship To Name (*)</ezf:anchor></td>
														<td colspan = "4"><ezf:inputText name="xxShipToAcctNmSrchTxt" ezfName="xxShipToAcctNmSrchTxt" value="CANON BULLS" otherAttr=" size=\"46\" maxlength=\"60\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Ship To Acct# -->
														<td class="stab"><ezf:anchor name="ShipToCustAcctNm_LK" ezfName="ShipToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToAcctNumber" otherAttr=" id=\"ShipToCustAcctNm_LK\" ezfanchortext=\"\"">Ship To Acct# (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxShipToAcctCdSrchTxt" ezfName="xxShipToAcctCdSrchTxt" value="11111" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
														<td></td>
														<!-- Ship To Loc# -->
														<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
														<!-- <td class="stab"><a href="#" id="ShipToCustAcctNm_LK" name="ShipToCustAcctNm_LK" ezfName="ShipToCustAcctNm_LK" onclick="sendServer('OpenWin_ShipToLoctNumber')" ezfanchortext>Ship To Loc# (*)</a></td> -->
														<td class="stab"><ezf:anchor name="ShipToCustAcctNm_LK" ezfName="ShipToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToLoctNumber" otherAttr=" id=\"ShipToCustAcctNm_LK\" ezfanchortext=\"\"">Ship To Code (*)</ezf:anchor></td>
														<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
														<td><ezf:inputText name="xxShipToLocCdSrchTxt" ezfName="xxShipToLocCdSrchTxt" value="22222" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Bill To Name -->
														<td class="stab"><ezf:anchor name="BillToCustAcctNm_LK" ezfName="BillToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToName" otherAttr=" id=\"BillToCustAcctNm_LK\" ezfanchortext=\"\"">Bill To Name (*)</ezf:anchor></td>
														<td colspan = "4"><ezf:inputText name="xxBillToAcctNmSrchTxt" ezfName="xxBillToAcctNmSrchTxt" value="CANON BULLS" otherAttr=" size=\"46\" maxlength=\"60\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Bill To Acct# -->
														<td class="stab"><ezf:anchor name="BillToCustAcctNm_LK" ezfName="BillToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToAcctNumber" otherAttr=" id=\"BillToCustAcctNm_LK\" ezfanchortext=\"\"">Bill To Acct# (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxBillToAcctCdSrchTxt" ezfName="xxBillToAcctCdSrchTxt" value="11111" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
														<td></td>
														<!-- Bill To Loc# -->
														<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
														<!-- <td class="stab"><a href="#" id="BillToCustAcctNm_LK" name="BillToCustAcctNm_LK" ezfName="BillToCustAcctNm_LK" onclick="sendServer('OpenWin_BillToLocNumber')" ezfanchortext>Bill To Loc# (*)</a></td> -->
														<td class="stab"><ezf:anchor name="BillToCustAcctNm_LK" ezfName="BillToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToLocNumber" otherAttr=" id=\"BillToCustAcctNm_LK\" ezfanchortext=\"\"">Bill To Code (*)</ezf:anchor></td>
														<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
														<td><ezf:inputText name="xxBillToLocCdSrchTxt" ezfName="xxBillToLocCdSrchTxt" value="22222" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Business Unit -->
														<td class="stab"><ezf:anchor name="BillToCustAcctNm_LK" ezfName="BillToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BusinessUnit" otherAttr=" id=\"BillToCustAcctNm_LK\" ezfanchortext=\"\"">Business Unit (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxCoaExtnSrchTxt" ezfName="xxCoaExtnSrchTxt" value="CANON BULLS" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
														<td></td>
														<!-- Branch -->
														<td class="stab"><ezf:anchor name="BillToCustAcctNm_LK" ezfName="BillToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Branch" otherAttr=" id=\"BillToCustAcctNm_LK\" ezfanchortext=\"\"">Branch (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxCoaBrSrchTxt" ezfName="xxCoaBrSrchTxt" value="CANON BULLS" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Sales Rep -->
														<td class="stab"><ezf:anchor name="BillToCustAcctNm_LK" ezfName="BillToCustAcctNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SalesRep" otherAttr=" id=\"BillToCustAcctNm_LK\" ezfanchortext=\"\"">Sales Rep (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxSlsRepTocSrchTxt" ezfName="xxSlsRepTocSrchTxt" value="00000195" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
														<td></td>
													</tr>
												</table>
											</td>
											<td width="10">
											</td>
											<td valign="top" width="210">
												<table border="0" cellpadding="0" cellspacing="2">
													<col width="100">
													<col width="100">
													<tr>
														<!-- Order Source -->
														<td class="stab"><ezf:anchor name="cpoSrcTpSrchTxt_LK" ezfName="cpoSrcTpSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderSource" otherAttr=" id=\"cpoSrcTpSrchTxt_LK\" ezfanchortext=\"\"">Order Source (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxCpoSrcTpSrchTxt" ezfName="xxCpoSrcTpSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- LOB -->
														<td class="stab"><ezf:anchor name="dsBizLineSrchTxt_LK" ezfName="dsBizLineSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_LOB" otherAttr=" id=\"dsBizLineSrchTxt_LK\" ezfanchortext=\"\"">LOB (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxDsBizLineSrchTxt" ezfName="xxDsBizLineSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Order Category -->
														<td class="stab"><ezf:anchor name="dsOrdCatgSrchTxt_LK" ezfName="dsOrdCatgSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderCategory" otherAttr=" id=\"dsOrdCatgSrchTxt_LK\" ezfanchortext=\"\"">Order Category (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxDsOrdCatgSrchTxt" ezfName="xxDsOrdCatgSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Order Reason -->
														<td class="stab"><ezf:anchor name="dsOrdTpSrchTxt_LK" ezfName="dsOrdTpSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderReason" otherAttr=" id=\"dsOrdTpSrchTxt_LK\" ezfanchortext=\"\"">Order Reason (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxDsOrdTpSrchTxt" ezfName="xxDsOrdTpSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Sub Reason -->
														<td class="stab"><ezf:anchor name="dsOrdRsnSrchTxt_LK" ezfName="dsOrdRsnSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SubReason" otherAttr=" id=\"dsOrdRsnSrchTxt_LK\" ezfanchortext=\"\"">Sub Reason (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxDsOrdRsnSrchTxt" ezfName="xxDsOrdRsnSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- CSMP# -->
														<td class="stab "><ezf:anchor name="csmpContrNumSrchTxt_LK" ezfName="csmpContrNumSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CSMPNumber" otherAttr=" id=\"csmpContrNumSrchTxt_LK\" ezfanchortext=\"\"">CSMP# (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxCsmpContrNumSrchTxt" ezfName="xxCsmpContrNumSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Association Program Name -->
														<td class="stab "><ezf:anchor name="prcContrNumSrchTxt_LK" ezfName="prcContrNumSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_AssnProgram" otherAttr=" id=\"prcContrNumSrchTxt_LK\" ezfanchortext=\"\"">Association Program Name (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxPrcContrNumSrchTxt" ezfName="xxPrcContrNumSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Import Source# -->
														<td class="stab ">Import Source# (*)</td>
														<td><ezf:inputText name="xxOrdSrcRefNumSrchTxt" ezfName="xxOrdSrcRefNumSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Acquisition# -->
														<td class="stab ">Acquisition# (*)</td>
														<td><ezf:inputText name="xxAquNumSrchTxt" ezfName="xxAquNumSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
							<td width = "10">
							</td>
<!-- ############################## START - Line Search Criteria -->
							<td align="center" width="430">
								<fieldset>
									<legend>&nbsp;<font size="2" color="black">Line Search Criteria</font>&nbsp;</legend>
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="top">
												<table border="0" cellpadding="0" cellspacing="2">
													<col width="90">
													<col width="100">
													<tr>
														<!-- Item Name -->
														<td class="stab"><ezf:anchor name="mdseSrchTxt_LK" ezfName="mdseSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ItemName" otherAttr=" id=\"mdseSrchTxt_LK\" ezfanchortext=\"\"">Item Name (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxMdseSrchTxt" ezfName="xxMdseSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Item Code -->
														<td class="stab"><ezf:anchor name="mdseForSlsSmrySrchTxt_LK" ezfName="mdseForSlsSmrySrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ItemCode" otherAttr=" id=\"mdseForSlsSmrySrchTxt_LK\" ezfanchortext=\"\"">Item Code (*)</ezf:anchor></td>
														<td><ezf:inputText name="mdseForSlsSmrySrchTxt" ezfName="mdseForSlsSmrySrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- PL1 -->
														<td class="stab"><ezf:anchor name="zerothProdCtrlSrchTxt_LK" ezfName="zerothProdCtrlSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PLGroup" otherAttr=" id=\"zerothProdCtrlSrchTxt_LK\" ezfanchortext=\"\"">PL1 (*)</ezf:anchor></td>
														<td><ezf:inputText name="zerothProdCtrlSrchTxt" ezfName="zerothProdCtrlSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- PL2 -->
														<td class="stab"><ezf:anchor name="firstProdCtrlSrchTxt_LK" ezfName="firstProdCtrlSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PL1" otherAttr=" id=\"firstProdCtrlSrchTxt_LK\" ezfanchortext=\"\"">PL2 (*)</ezf:anchor></td>
														<td><ezf:inputText name="firstProdCtrlSrchTxt" ezfName="firstProdCtrlSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- PL3 -->
														<td class="stab"><ezf:anchor name="scdProdCtrlSrchTxt_LK" ezfName="scdProdCtrlSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PL2" otherAttr=" id=\"scdProdCtrlSrchTxt_LK\" ezfanchortext=\"\"">PL3 (*)</ezf:anchor></td>
														<td><ezf:inputText name="scdProdCtrlSrchTxt" ezfName="scdProdCtrlSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- PL4 -->
														<td class="stab"><ezf:anchor name="thirdProdCtrlSrchTxt_LK" ezfName="thirdProdCtrlSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PL3" otherAttr=" id=\"thirdProdCtrlSrchTxt_LK\" ezfanchortext=\"\"">PL4 (*)</ezf:anchor></td>
														<td><ezf:inputText name="thirdProdCtrlSrchTxt" ezfName="thirdProdCtrlSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- PL5 -->
														<td class="stab"><ezf:anchor name="frthProdCtrlSrchTxt_LK" ezfName="frthProdCtrlSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PL4" otherAttr=" id=\"frthProdCtrlSrchTxt_LK\" ezfanchortext=\"\"">PL5 (*)</ezf:anchor></td>
														<td><ezf:inputText name="frthProdCtrlSrchTxt" ezfName="frthProdCtrlSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- COA Prod -->
														<td class="stab"><ezf:anchor name="coaProdSrchTxt_LK" ezfName="coaProdSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_COAProduct" otherAttr=" id=\"coaProdSrchTxt_LK\" ezfanchortext=\"\"">COA Prod (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxCoaProdSrchTxt" ezfName="xxCoaProdSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- COA MDSE -->
														<td class="stab"><ezf:anchor name="coaMdseTpSrchTxt_LK" ezfName="coaMdseTpSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_COAMdse" otherAttr=" id=\"coaMdseTpSrchTxt_LK\" ezfanchortext=\"\"">COA MDSE (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxCoaMdseTpSrchTxt" ezfName="xxCoaMdseTpSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Model -->
														<td class="stab"><ezf:anchor name="mdlSrchTxt_LK" ezfName="mdlSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Model" otherAttr=" id=\"mdlSrchTxt_LK\" ezfanchortext=\"\"">Model (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxMdlSrchTxt" ezfName="xxMdlSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Serial# -->
														<td class="stab ">Serial# (*)</td>
														<td><ezf:inputText name="xxSerNumSrchTxt" ezfName="xxSerNumSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
												</table>
											</td>
											<td width = "10">
											</td>
											<td valign="top">
												<table border="0" cellpadding="0" cellspacing="2">
													<col width="90">
													<col width="100">
													<tr>
														<!-- Line Category -->
														<td class="stab"><ezf:anchor name="lineCatgSrchTxt_LK" ezfName="lineCatgSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_LineCategory" otherAttr=" id=\"lineCatgSrchTxt_LK\" ezfanchortext=\"\"">Line Category (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxLineCatgSrchTxt" ezfName="xxLineCatgSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Line Source -->
														<td class="stab"><ezf:anchor name="ordLineSrcSrchTxt_LK" ezfName="ordLineSrcSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_LineSource" otherAttr=" id=\"ordLineSrcSrchTxt_LK\" ezfanchortext=\"\"">Line Source (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxOrdLineSrcSrchTxt" ezfName="xxOrdLineSrcSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- WH -->
														<td class="stab"><ezf:anchor name="rtlWhSrchTxt_LK" ezfName="rtlWhSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_WH" otherAttr=" id=\"rtlWhSrchTxt_LK\" ezfanchortext=\"\"">WH (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxRtlWhSrchTxt" ezfName="xxRtlWhSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- SUB WH -->
														<td class="stab"><ezf:anchor name="rtlSwhSrchTxt_LK" ezfName="rtlSwhSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWH" otherAttr=" id=\"rtlSwhSrchTxt_LK\" ezfanchortext=\"\"">SUB WH (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxRtlSwhSrchTxt" ezfName="xxRtlSwhSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- PO Vendor -->
														<td class="stab"><ezf:anchor name="vndSrchTxt_LK" ezfName="vndSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_POVendor" otherAttr=" id=\"vndSrchTxt_LK\" ezfanchortext=\"\"">PO Vendor (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxVndSrchTxt" ezfName="xxVndSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- P/O# -->
														<td class="stab ">P/O# (*)</td>
														<td><ezf:inputText name="xxCpoOrdNumSrchTxt" ezfName="xxCpoOrdNumSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- S/O# -->
														<td class="stab ">S/O# (*)</td>
														<td><ezf:inputText name="soNumSrchTxt" ezfName="soNumSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Invoice# -->
														<td class="stab ">Invoice# (*)</td>
														<td><ezf:inputText name="invNumSrchTxt" ezfName="invNumSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Contract# -->
														<td class="stab"><ezf:anchor name="dsContrNumSrchTxt_LK" ezfName="dsContrNumSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ContractNumber" otherAttr=" id=\"dsContrNumSrchTxt_LK\" ezfanchortext=\"\"">Contract# (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxDsContrNumSrchTxt" ezfName="xxDsContrNumSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Config# -->
														<td class="stab"><ezf:anchor name="svcConfigMstrSrchTxt_LK" ezfName="svcConfigMstrSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ConfigNumber" otherAttr=" id=\"svcConfigMstrSrchTxt_LK\" ezfanchortext=\"\"">Config# (*)</ezf:anchor></td>
														<td><ezf:inputText name="xxSvcConfigMstrSrchTxt" ezfName="xxSvcConfigMstrSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<!-- Install Base ID -->
														<td class="stab ">Install Base ID</td>
														<td><ezf:inputText name="xxSvcMachMstrSrchTxt" ezfName="xxSvcMachMstrSrchTxt" value="A,B,C,D,E" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td valign="top" colspan="5">
												<table border="0" cellpadding="0" cellspacing="2">
													<col width="90">
													<col width="300">
													<tr>
														<!-- Return Reason -->
														<td class="stab ">Return Reason</td>
														<td colspan="4">
															<ezf:select name="rtrnRsnCd" ezfName="rtrnRsnCd" ezfBlank="1" ezfCodeName="rtrnRsnCd_CD" ezfDispName="rtrnRsnDescTxt_NM" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
<!-- ############################## START - Order Team -->
						<tr>
							<td colspan="1" valign="top" height="40px">
								<fieldset>
									<legend>&nbsp;<font size="2" color="black">Order Team</font>&nbsp;</legend>
									<table border="0" cellpadding="0" cellspacing="0"  valign="top">
										<col width="">
										<col width="10">
										<col width="100">
										<col width="50">
										<col width="">
										<col width="10">
										<col width="100">
										<col width="50">
										<col width="">
										<col width="10">
										<col width="100">
										<tr valign="top">
											<!-- Team -->
											<td class="stab"><ezf:anchor name="xxOrdTeamSrchTxt_LK" ezfName="xxOrdTeamSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Team" otherAttr=" id=\"xxOrdTeamSrchTxt_LK\" ezfanchortext=\"\"">Team (*)</ezf:anchor></td>
											<td></td>
											<td><ezf:inputText name="xxOrdTeamSrchTxt" ezfName="xxOrdTeamSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
											<td></td>
											<!-- Zone -->
											<td class="stab"><ezf:anchor name="xxOrdZnSrchTxt_LK" ezfName="xxOrdZnSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Zone" otherAttr=" id=\"xxOrdZnSrchTxt_LK\" ezfanchortext=\"\"">Zone (*)</ezf:anchor></td>
											<td></td>
											<td><ezf:inputText name="xxOrdZnSrchTxt" ezfName="xxOrdZnSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
											<td></td>
											<!-- Created By -->
											<td class="stab">Created By (*)</td>
											<td></td>
											<td><ezf:inputText name="xxCratByUsrSrchTxt" ezfName="xxCratByUsrSrchTxt" value="Q08099" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
<!-- ############################## START - Mode -->
					<%
						String rsltModeCd = ((business.servlet.NWAL1570.NWAL1570Bean)databean).getXxRsltModeCd();
					%>
						<tr>
							<td colspan="1" height="10px">
								<table border="0" cellpadding="0" cellspacing="2">
									<col width="40">
									<col width="200">
									<col width="30">
									<col width="20" align="center">
									<col width="200">
									<col width="20" align="center">
									<col width="">
									<tr>
										<!-- Mode -->
										<td class="stab">Mode</td>
										<td>
											<ezf:select name="xxRsltModeCd" ezfName="xxRsltModeCd" ezfCodeName="xxRsltModeCd_CD" ezfDispName="xxRsltModeNm_NM" otherEvent1=" onchange=\"sendServer('Select_ResultMode',this.parentNode.id)\"" />
										</td>
										<td></td>
										<td><ezf:inputCheckBox name="xxInclPendImptOrdFlg" ezfName="xxInclPendImptOrdFlg" value="Y" otherAttr=" style=\"height:12; width:12;\""/></td>
										<td class="stab">Include Pending Import</td>
										<td><ezf:inputCheckBox name="xxOnlySlsOrdFlg" ezfName="xxOnlySlsOrdFlg" value="Y" otherAttr=" style=\"height:12; width:12;\""/></td>
										<td class="stab">Sales</td>
									</tr>
								</table>
							</td>
						</tr>
<!-- ############################## START - Status -->
					<%
						if(rsltModeCd.equals("1")){
					%>
						<tr>
							<td width="640" height="120px" valign="top">
								<fieldset>
									<legend>&nbsp;<font size="2" color="black">Status</font>&nbsp;</legend>
									<table border="0" cellpadding="0" cellspacing="2">
										<tr>
											<!-- Header Status -->
											<td>
												<table style="border-collapse:collapse;" border="1" cellpadding="1" cellspacing="0">
													<!-- col -->
													<col align="center">
													<col width="147">
													<!-- tr -->
													<tr>
														<td class="pClothBs"><ezf:inputCheckBox name="Select_All_Header_Status" ezfName="xxOrdHdrStsAllSelFlg" value="Y" onClick="sendServer(this)" otherAttr=" style=\"height:12; width:20;\""/></td>
														<td class="pClothBs">Header Status</td>
													</tr>
												</table>
												<div style="overflow-y:scroll; height:52; overflow-x:hidden; width:190;">
													<table style="border-collapse:collapse;" border="1" cellpadding="1" cellspacing="0" width="" bgcolor="#FFFFFF" id="H1">
														<col align="center">
														<col width="190">
														<tbody>
															<ezf:row ezfHyo="H">
																<tr>
																	<td><ezf:inputCheckBox name="xxOrdHdrStsSelFlg_HS" ezfName="xxOrdHdrStsSelFlg_HS" value="Y" ezfHyo="H" ezfArrayNo="0" otherAttr=" style=\"height:12; width:20;\""/></td>
																	<td><ezf:inputText name="ordHdrStsNm_HS" ezfName="ordHdrStsNm_HS" value="Entered" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"30\" style=\"height:12; border:none; text-align:left; border:none; background-color:transparent;\""/></td>
																</tr>
															</ezf:row>
															<ezf:skip>
																<tr>
																	<td><input style="height:12; width:15;" type="checkbox" value="Y" name="sortByShipCpltFlg" ezfname="sortByShipCpltFlg"></td>
																	<td><label ezfout>DI Hold</label></td>
																</tr>
																<tr>
																	<td><input style="height:12; width:15;" type="checkbox" value="Y" name="sortByShipCpltFlg" ezfname="sortByShipCpltFlg"></td>
																	<td><label ezfout>Validated Hold</label></td>
																</tr>
																<tr>
																	<td><input style="height:12; width:15;" type="checkbox" value="Y" name="sortByShipCpltFlg" ezfname="sortByShipCpltFlg"></td>
																	<td><label ezfout>Booked</label></td>
																</tr>
															</ezf:skip>
														</tbody>
													</table>
												</div>
											</td>
											<td width="10">
											</td>
											<!-- Line Status -->
											<td>
												<table style="border-collapse:collapse;" border="1" cellpadding="1" cellspacing="0">
													<!-- col -->
													<col align="center">
													<col width="147">
													<!-- tr -->
													<tr>
														<td class="pClothBs"><ezf:inputCheckBox name="Select_All_Line_Status" ezfName="xxLineStsAllSelFlg" value="Y" onClick="sendServer(this)" otherAttr=" style=\"height:12; width:20;\""/></td>
														<td class="pClothBs">Line Status</td>
													</tr>
												</table>
												<div style="overflow-y:scroll; height:52; overflow-x:hidden; width:190;">
													<table style="border-collapse:collapse;" border="1" cellpadding="1" cellspacing="0" width="" bgcolor="#FFFFFF" id="L1">
														<col align="center">
														<col width="190">
														<tbody>
															<ezf:row ezfHyo="L">
																<tr>
																	<td><ezf:inputCheckBox name="xxLineStsSelFlg_LS" ezfName="xxLineStsSelFlg_LS" value="Y" ezfHyo="L" ezfArrayNo="0" otherAttr=" style=\"height:12; width:20;\""/></td>
																	<td><ezf:inputText name="ordLineStsNm_LS" ezfName="ordLineStsNm_LS" value="Entered" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"30\" style=\"height:12; border:none; text-align:left; border:none; background-color:transparent;\""/></td>
																</tr>
															</ezf:row>
															<ezf:skip>
																<tr>
																	<td><input style="height:12; width:15;" type="checkbox" value="Y" name="sortByShipCpltFlg" ezfname="sortByShipCpltFlg"></td>
																	<td><label ezfout>Booked</label></td>
																</tr>
																<tr>
																	<td><input style="height:12; width:15;" type="checkbox" value="Y" name="sortByShipCpltFlg" ezfname="sortByShipCpltFlg"></td>
																	<td><label ezfout>Pending Allocation</label></td>
																</tr>
																<tr>
																	<td><input style="height:12; width:15;" type="checkbox" value="Y" name="sortByShipCpltFlg" ezfname="sortByShipCpltFlg"></td>
																	<td><label ezfout>Entered</label></td>
																</tr>
															</ezf:skip>
														</tbody>
													</table>
												</div>
											</td>
											<td width="10">
											</td>
											<!-- Return Line Status -->
											<td>
												<table style="border-collapse:collapse;" border="1" cellpadding="1" cellspacing="0">
													<!-- col -->
													<col align="center">
													<col width="147">
													<!-- tr -->
													<tr>
														<td class="pClothBs"><ezf:inputCheckBox name="Select_All_Return_Line_Status" ezfName="xxRtrnStsAllSelFlg" value="Y" onClick="sendServer(this)" otherAttr=" style=\"height:12; width:20;\""/></td>
														<td class="pClothBs">Return Line Status</td>
													</tr>
												</table>
												<div style="overflow-y:scroll; height:52; overflow-x:hidden; width:190;">
													<table style="border-collapse:collapse;" border="1" cellpadding="1" cellspacing="0" width="" bgcolor="#FFFFFF" id="R1">
														<col align="center">
														<col width="190">
														<tbody>
															<ezf:row ezfHyo="R">
																<tr>
																	<td><ezf:inputCheckBox name="xxRtrnLineStsSelFlg_RS" ezfName="xxRtrnLineStsSelFlg_RS" value="Y" ezfHyo="R" ezfArrayNo="0" otherAttr=" style=\"height:12; width:20;\""/></td>
																	<td><ezf:inputText name="rtrnLineStsNm_RS" ezfName="rtrnLineStsNm_RS" value="Entered" ezfHyo="R" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"30\" style=\"height:12; border:none; text-align:left; border:none; background-color:transparent;\""/></td>
																</tr>
															</ezf:row>
															<ezf:skip>
																<tr>
																	<td><input style="height:12; width:15;" type="checkbox" value="Y" name="sortByShipCpltFlg" ezfname="sortByShipCpltFlg"></td>
																	<td><label ezfout>Booked</label></td>
																</tr>
																<tr>
																	<td><input style="height:12; width:15;" type="checkbox" value="Y" name="sortByShipCpltFlg" ezfname="sortByShipCpltFlg"></td>
																	<td><label ezfout>Pending Return</label></td>
																</tr>
																<tr>
																	<td><input style="height:12; width:15;" type="checkbox" value="Y" name="sortByShipCpltFlg" ezfname="sortByShipCpltFlg"></td>
																	<td><label ezfout>Entered</label></td>
																</tr>
															</ezf:skip>
														</tbody>
													</table>
												</div>
											</td>
										</tr>
										<tr align="right">
											<td colspan="5" border="0">
												<table border="0" align="right">
													<col width="" align="right">
													<col width="" align="right">
													<col width="50" align="right">
													<col width="" align="right">
													<col width="" align="right">
													<tr>
														<td></td>
														<td></td>
														<td></td>
														<!-- All Open Order -->
														<td><ezf:inputCheckBox name="Select_All_Open_Order" ezfName="xxAllOpenOrdFlg" value="Y" onClick="sendServer(this)" otherAttr=" style=\"height:12; width:12;\""/></td>
														<td class="stab">All Open Order</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
							<td width="10">
							</td>
<!-- ############################## START - Date Criteria -->
							<td width="430" height="120px" valign="top">
								<fieldset>
									<legend>&nbsp;<font size="2" color="black">Date Criteria</font>&nbsp;</legend>
									<table border="0" cellpadding="0" cellspacing="2">
										<col width="100">
										<col width="">
										<col width="30" align="center">
										<col width="">
										<col width="">
										<tr>
											<!-- Order Date -->
											<td class="stab">Order Date</td>
											<td>
												<ezf:inputText name="ordFromDt" ezfName="ordFromDt" value="05/26/2009" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'ordFromDt', 4 );">
											</td>
											<td>-</td>
											<td>
												<ezf:inputText name="ordToDt" ezfName="ordToDt" value="05/31/2009" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'ordToDt', 4 );">
											</td>
										</tr>
										<tr>
											<!-- Booked Date -->
											<td class="stab">Booked Date</td>
											<td>
												<ezf:inputText name="xxBookFromDt" ezfName="xxBookFromDt" value="05/26/2009" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxBookFromDt', 4 );">
											</td>
											<td>-</td>
											<td>
												<ezf:inputText name="xxBookToDt" ezfName="xxBookToDt" value="05/31/2009" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxBookToDt', 4 );">
											</td>
										</tr>
										<tr>
											<!-- Shipped Date -->
											<td class="stab">Shipped Date</td>
											<td>
												<ezf:inputText name="xxActlShipFromDt" ezfName="xxActlShipFromDt" value="05/26/2009" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxActlShipFromDt', 4 );">
											</td>
											<td>-</td>
											<td>
												<ezf:inputText name="xxActlShipToDt" ezfName="xxActlShipToDt" value="05/31/2009" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxActlShipToDt', 4 );">
											</td>
										</tr>
										<tr>
											<!-- Invoice Date -->
											<td class="stab">Invoice Date</td>
											<td>
												<ezf:inputText name="invFromDt" ezfName="invFromDt" value="05/26/2009" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'invFromDt', 4 );">
											</td>
											<td>-</td>
											<td>
												<ezf:inputText name="invToDt" ezfName="invToDt" value="05/31/2009" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'invToDt', 4 );">
											</td>
										</tr>
										<tr>
											<!-- Import Date -->
											<td class="stab">Import Creation Date</td>
											<td>
												<ezf:inputText name="xxOrdSrcImptFromDt" ezfName="xxOrdSrcImptFromDt" value="05/26/2009" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxOrdSrcImptFromDt', 4 );">
											</td>
											<td>-</td>
											<td>
												<ezf:inputText name="xxOrdSrcImptToDt" ezfName="xxOrdSrcImptToDt" value="05/31/2009" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxOrdSrcImptToDt', 4 );">
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					<%
						} else {
					%>
						<tr>
							<td colspan="3" height="135px" valign="top">
							</td>
						</tr>
					<%
						}
					%>
<!-- ############################## START - Display Option -->
						<tr valign="top">
							<td colspan="3">
								<table border="0" cellpadding="0" cellspacing="0">
									<td>
									<fieldset>
										<legend>&nbsp;<font size="2" color="black">Display Option</font>&nbsp;</legend>
										<table border="0" cellpadding="0" cellspacing="0">
										<col width="80">
										<col width="170">
										<col width="20">
										<col width="60">
										<col width="30">
										<col width="170">
										<col width="20">
										<col width="50">
										<col width="30" align="center">
										<col width="170">
										<col width="20">
										<col width="20">
											<tr>
												<!-- Display By -->
												<td class="stab">Display By</td>
												<td>
													<ezf:select name="dplyBy01ItemNm" ezfName="dplyBy01ItemNm" ezfCodeName="dplyBy01ItemNm_CD" ezfDispName="dplyBy01ItemNm_NM" otherAttr=" style=\"width:160;\""/>
												</td>
												<td>
													<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" otherAttr=" style=\"height:12; width:12;\""/></td>
												</td>
												<td class="stab">Descending</td>
												<td class="stab">and</td>
												<td>
													<ezf:select name="dplyBy02ItemNm" ezfName="dplyBy02ItemNm" ezfBlank="1" ezfCodeName="dplyBy02ItemNm_CD" ezfDispName="dplyBy02ItemNm_NM" otherAttr=" style=\"width:160;\""/>
												</td>
												<td>
													<ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" otherAttr=" style=\"height:12; width:12;\""/></td>
												</td>
												<td class="stab">Descending</td>
												<td class="stab">and</td>
												<td>
													<ezf:select name="dplyBy03ItemNm" ezfName="dplyBy03ItemNm" ezfBlank="1" ezfCodeName="dplyBy03ItemNm_CD" ezfDispName="dplyBy03ItemNm_NM" otherAttr=" style=\"width:160;\""/>
												</td>
												<td>
													<ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" otherAttr=" style=\"height:12; width:12;\""/></td>
												</td>
												<td class="stab">Descending</td>
											</tr>
											<tr>
												<!-- Display Mode -->
												<td class="stab">Display Mode</td>
												<td colspan="7">
													<ezf:select name="grpByDnldCd" ezfName="grpByDnldCd" ezfCodeName="grpByDnldCd_CD" ezfDispName="dplyByItemNm_DM" />
												</td>
												<td align="right"><ezf:inputCheckBox name="xxPgFlg" ezfName="xxPgFlg" value="Y" otherAttr=" style=\"height:12; width:12;\""/>&nbsp;</td>
												<td class="stab">Real Time Inquiry</td>
											</tr>
										</table>
									</fieldset>
									<td></td>
									<td align="right" valign="middle" width="188">
										<table border="0" cellpadding="0" cellspacing="0">
											<tr align="right"><!-- ############################## START - Search Button -->
												<td>	<!-- Search Button -->
													<ezf:inputButton name="SearchOrder" value="Search" htmlClass="cBtn" />
												</td>
											</tr>
										</table>
									</td>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
