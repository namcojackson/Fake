<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180402130957 --%>
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
			<input type="hidden" name="pageID" value="NWAL1540Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Profitability Review">

	<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td>
<%-- ######################################## HEADER ######################################## --%>
<!--
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Profit Review" class="pTab_ON"><a href="#">Profit Review</a></li>
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
 -->
			<!-- Header Tabs -->
			<div class="pTab_HEAD">
				<ul>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="96%">
								<div>
									<!-- include S21BusinessProcessTAB --> 
									<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
								</div>
							</td>
						</tr>
					</table>
				</ul>
			</div>

			<!-- Body of Selected Tab -->

			<div class="pTab_BODY" style="width:100%; text-align:left;" >
				<table height="" border="0" cellspacing="5px" cellpadding="0">
					<tr>
						<td>
							<ezf:select name="xxModeCd" ezfName="xxModeCd" ezfCodeName="xxModeCd_CD" ezfDispName="xxScrItem8Txt_NM" />
						</td>
						<td class="stab">Transaction Number</td>
						<td>
							<ezf:inputText name="trxHdrNum" ezfName="trxHdrNum" value="1234567890" otherAttr=" size=\"12\" maxlength=\"20\""/>
						</td>
						<td>
							<ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" />
						</td>
					</tr>
				</table>
				<hr style="margin-top:-5;">

			<div style="float:left">
			<div id='headInfo' style="width:1100px; display:block; margin:0px;padding:0px;">
				<table>
					<tr>
						<td>
							<fieldset style="height:180px; width:460px;">
							<legend>Profitability Header Information</legend>
								<table>
									<col align="left" width="">
									<col align="left" width="130">
									<col align="left" width="">
									<col align="left" width="120">
									<tr>
										<td class="stab">Version Number</td>
										<td>
											<ezf:select name="ordPrftVrsnNum" ezfName="ordPrftVrsnNum" ezfCodeName="ordPrftVrsnNum_CD" ezfDispName="xxScrItem13Txt_NM" otherEvent1=" onchange=\"sendServer('OnChange_VrsnNum')\"" />
										<!--
										-->
										</td>
									</tr>
									<tr>
										<td class="stab">Negotiated Deal</td>
										<td>
											<ezf:inputText name="funcNegoDealAmt" ezfName="funcNegoDealAmt" value="123,456.12" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
										<td class="stab">CUSA GMD/NAD</td>
										<td>
											<ezf:inputText name="altGrsPrftPct" ezfName="altGrsPrftPct" value="5.55" otherAttr=" style=\"text-align:right;\" size=\"6\" maxlength=\"6\""/>%
										</td>
									</tr>
									<tr>
										<td class="stab">Invoice Total</td>
										<td>
											<ezf:inputText name="totInvAmt" ezfName="totInvAmt" value="123,456.12" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
										<td class="stab">Finders Fee</td>
										<td>
											<ezf:inputText name="xxOrdPrftFndrFeeAmt" ezfName="xxOrdPrftFndrFeeAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
										<td class="stab">Ext. Final Rep Rev</td>
										<td>
											<ezf:inputText name="totFuncRepRevAmt" ezfName="totFuncRepRevAmt" value="123,456.12" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
										<td class="stab">Install Cr</td>
										<td>
											<ezf:inputText name="xxOrdPrftIstlCrAmt" ezfName="xxOrdPrftIstlCrAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									<tr>
										<td class="stab">Ext. Final Rep Floor</td>
										<td>
											<ezf:inputText name="totFuncFinalFlAmt" ezfName="totFuncFinalFlAmt" value="123,456.12" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
										<td>Dealer Cr</td>
										<td>
											<ezf:inputText name="totFuncDlrCrAmt" ezfName="totFuncDlrCrAmt" value="123,456.12" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Rep Rev Adj</td>
										<td>
											<ezf:inputText name="totFuncRepRevAdjAmt" ezfName="totFuncRepRevAdjAmt" value="123,456.12" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
										<td class="stab">GP</td>
										<td>
											<ezf:inputText name="funcGrsPrftAmt" ezfName="funcGrsPrftAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab">GP%</td>
										<td>
											<ezf:inputText name="grsPrftPct" ezfName="grsPrftPct" value="5.55" otherAttr=" style=\"text-align:right;\" size=\"10\" maxlength=\"6\""/>%
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
						<td>
							<fieldset style="height:180px; width:185px;">
							<legend>Profit Recalculate</legend>
								<table>
									<tr style="height:86px">
										<td colspan="2" width="">
											If there is any change
											<br>between online current
											<br>version and the last
											<br>calculated version,
											<br>please Recalculate Profit.
											<br>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>
											<ezf:inputButton name="Recalculate" value="Recalculate" htmlClass="pBtn8" />
										</td>
									</tr>
									<tr>
										<td class="stab">GP</td>
										<td>
											<ezf:inputText name="funcAltGrsPrftAmt_RE" ezfName="funcAltGrsPrftAmt_RE" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">GP%</td>
										<td>
											<ezf:inputText name="altGrsPrftPct_RE" ezfName="altGrsPrftPct_RE" value="5.55" otherAttr=" style=\"text-align:right;\" size=\"10\" maxlength=\"6\""/>%
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
						<td>
							<fieldset style="height:180px; width:200px;">
							<legend>MSRP/CSMP</legend>
								<table>
									<tr>
										<td class="stab">MSRP</td>
										<td>
											<ezf:inputText name="totFuncMsrpAmt" ezfName="totFuncMsrpAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Total Cost</td>
										<td>
											<ezf:inputText name="totCostAmt" ezfName="totCostAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Std Fl</td>
										<td>
											<ezf:inputText name="totFuncStdFlAmt" ezfName="totFuncStdFlAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Fl Adj</td>
										<td>
											<ezf:inputText name="totFuncFlAdjAmt" ezfName="totFuncFlAdjAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">CSMP Flag</td>
										<td>
											<ezf:inputText name="csmpOrdFlg" ezfName="csmpOrdFlg" value="Y" otherAttr=" size=\"1\" maxlength=\"1\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">CSMP Cr</td>
										<td>
											<ezf:inputText name="totFuncCsmpCrAmt" ezfName="totFuncCsmpCrAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">CSMP#</td>
										<td>
											<ezf:inputText name="csmpContrNum" ezfName="csmpContrNum" value="12345-ABY12" otherAttr=" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
						<td>
							<fieldset style="height:180px; width:200px;">
							<legend>Other Information</legend>
								<table>
									<tr>
										<td class="stab">Buyouts</td>
										<td>
											<ezf:inputText name="totFuncByotAmt" ezfName="totFuncByotAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">SRT</td>
										<td>
											<ezf:inputText name="totFuncSvcRevTrnsfAmt" ezfName="totFuncSvcRevTrnsfAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Svc CT</td>
										<td>
											<ezf:inputText name="totFuncSvcCostTrnsfAmt" ezfName="totFuncSvcCostTrnsfAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Prof Svc</td>
										<td>
											<ezf:inputText name="totFuncProSvcAmt" ezfName="totFuncProSvcAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Service</td>
										<td>
											<ezf:inputText name="xxOrdPrftSvcAmt" ezfName="xxOrdPrftSvcAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Supplies</td>
										<td>
											<ezf:inputText name="xxOrdPrftSplyAmt" ezfName="xxOrdPrftSplyAmt" value="1,355.55" otherAttr=" style=\"text-align:right;\" size=\"15\" maxlength=\"17\""/>
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<fieldset style="margin-top:-5;height:; width:;">
							<legend>QA Check</legend>
							<table>
								<col align="left" width="">
								<col align="left" width="300">
								<col align="left" width="">
								<col align="left" width="">
								<tr>
									<td class="stab">User ID</td>
									<td colspan="3">
										<ezf:inputText name="xxScrItem81Txt" ezfName="xxScrItem81Txt" value="Cxxxxx:BLOOM, JOSH" otherAttr=" size=\"35\" maxlength=\"81\""/>
									</td>
									<td class="stab">Date/Time</td>
									<td>
										<ezf:inputText name="xxScrItem19Txt" ezfName="xxScrItem19Txt" value="MM/DD/YYYY hr:mi:ss" otherAttr=" size=\"19\" maxlength=\"19\""/>
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

		<tr>
		<td>
			<hr>
		</td>
		</tr>

		<tr>
		<td>
			<table cellpadding="0" cellspacing="0">
				<col width="003">
				<col width="200">
				<col width="900">
				<tr>
					<td></td>
					<td>
						<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
					</td>
					<td align="right">
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"       value="FULL" />
									</jsp:include>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

		<div id="parentBoxA">

			<table border="0">
				<tr>
					<td valign="Top" width="1132">
						<div style="float:left; display:block"> <!-- left table -->
							<div id='leftTblHead' style="display:block;">
							</div>
							<div id='leftTbl' style="float:left; display:block; height:206px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 18px">
							</div>
						</div> <!-- left table -->
						<div style="float:left"> <!-- right table -->
							<div id='rightTblHead' style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">

								<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="3825">
									<col align="center" width="040"><!-- I/O/S -->
									<col align="center" width="075"><!-- Line# -->
									<col align="center" width="060"><!-- Config# -->
									<col align="center" width="110"><!-- Item -->
									<col align="center" width="160"><!-- Description -->
									<col align="center" width="130"><!-- Mdse Type -->
									<col align="center" width="080"><!-- Qty -->
									<col align="center" width="130"><!-- MSRP -->
									<col align="center" width="130"><!-- Unit CSMP Floor -->
									<col align="center" width="130"><!-- Unit Sell Price -->
									<col align="center" width="130"><!-- Extended Sell Price -->
									<col align="center" width="130"><!-- Unit Final Rep Floor -->
									<col align="center" width="130"><!-- Unit Final Rep Revenue -->
									<col align="center" width="130"><!-- Extended Final Floor -->
									<col align="center" width="130"><!-- Ext. Final Rep Revenue -->
									<col align="center" width="130"><!-- CSMP Credits -->
									<col align="center" width="150"><!-- CSMP# -->
									<col align="center" width="150"><!-- Dealer Ref# -->
									<col align="center" width="150"><!-- CSMP Price List -->
									<col align="center" width="130"><!-- Unit List Price -->
									<col align="center" width="150"><!-- Sell Price List -->
									<col align="center" width="130"><!-- Unit Floor Price -->
									<col align="center" width="150"><!-- Floor Price List -->
									<col align="center" width="080"><!-- Line WT(%) -->
									<col align="center" width="130"><!-- Floor Adjustment -->
									<col align="center" width="130"><!-- Revenue Adjustment -->
									<col align="center" width="130"><!-- Inventory Cost -->
									<col align="center" width="130"><!-- SRT -->
									<col align="center" width="130"><!-- CT -->
									<col align="center" width="130"><!-- Misc Floor -->
									<col align="center" width="130"><!-- Misc Revenue -->

									<tr height="24">
										<td id="AH0" class="pClothBs colFix">I/O/S</td>
										<td id="AH1" class="pClothBs colFix">Line#</td>
										<td id="AH2" class="pClothBs">Config#</td>
										<td id="AH3" class="pClothBs">Item</td>
										<td id="AH4" class="pClothBs">Description</td>
										<td id="AH5" class="pClothBs">Mdse Type</td>
										<td id="AH6" class="pClothBs">Qty</td>
										<td id="AH7" class="pClothBs">MSRP</td>
										<td id="AH8" class="pClothBs">Unit CSMP Floor</td>
										<td id="AH9" class="pClothBs">Unit Sell Price</td>
										<td id="AH10" class="pClothBs">Extended Sell Price</td>
										<td id="AH11" class="pClothBs">Unit Final Rep Floor</td>
										<td id="AH12" class="pClothBs">Unit Final Rep Revenue</td>
										<td id="AH13" class="pClothBs">Extended Final Floor</td>
										<td id="AH14" class="pClothBs">Ext. Final Rep Revenue</td>
										<td id="AH15" class="pClothBs">CSMP Credits</td>
										<td id="AH16" class="pClothBs">CSMP#</td>
										<td id="AH17" class="pClothBs">Dealer Ref#</td>
										<td id="AH18" class="pClothBs">CSMP Price List</td>
										<td id="AH19" class="pClothBs">Unit List Price</td>
										<td id="AH20" class="pClothBs">Sell Price List</td>
										<td id="AH21" class="pClothBs">Unit Floor Price</td>
										<td id="AH22" class="pClothBs">Floor Price List</td>
										<td id="AH23" class="pClothBs">Line WT(%)</td>
										<td id="AH24" class="pClothBs">Floor Adjustment</td>
										<td id="AH25" class="pClothBs">Revenue Adjustment</td>
										<td id="AH26" class="pClothBs">Inventory Cost</td>
										<td id="AH27" class="pClothBs">SRT</td>
										<td id="AH28" class="pClothBs">CT</td>
										<td id="AH29" class="pClothBs">Misc Floor</td>
										<td id="AH30" class="pClothBs">Misc Revenue</td>
									</tr>
								</table>
							</div>

							<div id="rightTbl" style="width:1117px; height:223px; display:block; overflow:scroll; margin:0px; padding:0px;">
								<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="3825">
									<col align="center" width="040"><!-- I/O/S -->
									<col align="center" width="075"><!-- Line# -->
									<col align="center" width="060"><!-- Config# -->
									<col align="center" width="110"><!-- Item -->
									<col align="center" width="160"><!-- Description -->
									<col align="center" width="130"><!-- Mdse Type -->
									<col align="center" width="080"><!-- Qty -->
									<col align="center" width="130"><!-- MSRP -->
									<col align="center" width="130"><!-- Unit CSMP Floor -->
									<col align="center" width="130"><!-- Unit Sell Price -->
									<col align="center" width="130"><!-- Extended Sell Price -->
									<col align="center" width="130"><!-- Unit Final Rep Floor -->
									<col align="center" width="130"><!-- Unit Final Rep Revenue -->
									<col align="center" width="130"><!-- Extended Final Floor -->
									<col align="center" width="130"><!-- Ext. Final Rep Revenue -->
									<col align="center" width="130"><!-- CSMP Credits -->
									<col align="center" width="150"><!-- CSMP# -->
									<col align="center" width="150"><!-- Dealer Ref# -->
									<col align="center" width="150"><!-- CSMP Price List -->
									<col align="center" width="130"><!-- Unit List Price -->
									<col align="center" width="150"><!-- Sell Price List -->
									<col align="center" width="130"><!-- Unit Floor Price -->
									<col align="center" width="150"><!-- Floor Price List -->
									<col align="center" width="080"><!-- Line WT(%) -->
									<col align="center" width="130"><!-- Floor Adjustment -->
									<col align="center" width="130"><!-- Revenue Adjustment -->
									<col align="center" width="130"><!-- Inventory Cost -->
									<col align="center" width="130"><!-- SRT -->
									<col align="center" width="130"><!-- CT -->
									<col align="center" width="130"><!-- Misc Floor -->
									<col align="center" width="130"><!-- Misc Revenue -->

									<ezf:row ezfHyo="A">
									<tr>
										<td><ezf:inputText name="ordPrftTrxCatgCd_A" ezfName="ordPrftTrxCatgCd_A" value="W" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\" maxlength=\"1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="xxLineNum_A" ezfName="xxLineNum_A" value="WWW.WWW.WWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="dsOrdPosnNum_A" ezfName="dsOrdPosnNum_A" value="123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"7\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"250\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="coaProjNm_A" ezfName="coaProjNm_A" value="MACHINE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="ordQty_A" ezfName="ordQty_A" value="9999999999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"19\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="funcUnitMsrpAmt_A" ezfName="funcUnitMsrpAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcCsmpFlPrcAmt_A" ezfName="funcCsmpFlPrcAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcNetUnitPrcAmt_A" ezfName="funcNetUnitPrcAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcNetSellPrcAmt_A" ezfName="funcNetSellPrcAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcFinalUnitFlPrcAmt_A" ezfName="funcFinalUnitFlPrcAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcFinalUnitRevAmt_A" ezfName="funcFinalUnitRevAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcFinalFlPrcAmt_A" ezfName="funcFinalFlPrcAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcFinalRepRevAmt_A" ezfName="funcFinalRepRevAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcCsmpUnitCrAmt_A" ezfName="funcCsmpUnitCrAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="csmpContrNum_A" ezfName="csmpContrNum_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="dlrRefNum_A" ezfName="dlrRefNum_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="prcCatgNm_A" ezfName="prcCatgNm_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"75\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="funcUnitListPrcAmt_A" ezfName="funcUnitListPrcAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="prcCatgNm_A2" ezfName="prcCatgNm_A2" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"75\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="funcUnitFlPrcAmt_A" ezfName="funcUnitFlPrcAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="flPrcListNm_A" ezfName="flPrcListNm_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"75\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="lineWtAmtRate_A" ezfName="lineWtAmtRate_A" value="1,234,567" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcFlAdjAmt_A" ezfName="funcFlAdjAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcRepRevAdjAmt_A" ezfName="funcRepRevAdjAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcUnitStdCostAmt_A" ezfName="funcUnitStdCostAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcWtSvcRevTrnsfAmt_A" ezfName="funcWtSvcRevTrnsfAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcWtSvcCostTrnsfAmt_A" ezfName="funcWtSvcCostTrnsfAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcWtManFlAdjAmt_A" ezfName="funcWtManFlAdjAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
										<td><ezf:inputText name="funcWtManRevAdjAmt_A" ezfName="funcWtManRevAdjAmt_A" value="1,234,567,890.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"17\" style=\"border:none;background-color:transparent;padding:0px; text-align:right;\""/></td>
									</tr>
									</ezf:row>
<!--
									<ezf:skip>
									<tr>
										<td><input type="text" name="" ezfName="" value="O" ezfHyo="A" ezfArrayNo="0" size="1" style="border:none;background-color:transparent;padding:0px;"/></td>
										<td><input type="text" name="" ezfName="" value="1.1" ezfHyo="A" ezfArrayNo="0" size="16" style="border:none;background-color:transparent;padding:0px;"/></td>
										<td><input type="text" name="mdseCd" ezfName="mdseCd" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="16" style="border:none;background-color:transparent;padding:0px;"/></td>
										<td><input type="text" name="" ezfName="" value="IR500" ezfHyo="A" ezfArrayNo="0" size="16" style="border:none;background-color:transparent;padding:0px;"/></td>
										<td><input type="text" name="" ezfName="" value="1,234,567,890.34" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px; text-align:right;"/></td>
										<td><input type="text" name="" ezfName="" value="1,234.56" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px; text-align:right;"/></td>
										<td><input type="text" name="" ezfName="" value="1,234.56" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px; text-align:right;"/></td>
										<td><input type="text" name="" ezfName="" value="1,234.56" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px; text-align:right;"/></td>
										<td><input type="text" name="" ezfName="" value="1,234.56" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px; text-align:right;"/></td>
										<td><input type="text" name="" ezfName="" value="12345-ABY12XXXX" ezfHyo="A" ezfArrayNo="0" size="15" style="border:none;background-color:transparent;padding:0px;"/></td>
										<td><input type="text" name="dlrRefNum" ezfName="dlrRefNum" value="DR1234-5678XXXX" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
										<td><input type="text" name="" ezfName="" value="CSPL" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/></td>
										<td><input type="text" name="" ezfName="" value="FLPL123456" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px;"/></td>
										<td><input type="text" name="" ezfName="" value="1,234.56" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px; text-align:right;"/></td>
										<td><input type="text" name="" ezfName="" value="1,234.56" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px; text-align:right;"/></td>
										<td><input type="text" name="" ezfName="" value="4.56" ezfHyo="A" ezfArrayNo="0" size="10" style="border:none;background-color:transparent;padding:0px; text-align:right;"/></td>
										<td><input type="text" name="" ezfName="" value="1,234.56" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px; text-align:right;"/></td>
										<td><input type="text" name="" ezfName="" value="1,234.56" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px; text-align:right;"/></td>
										<td><input type="text" name="" ezfName="" value="1,234.56" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px; text-align:right;"/></td>
									</tr>
									</ezf:skip>
-->
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div><!-- parentBoxA -->

			</div><!-- pTab_BODY -->
		</td>
		</tr>
	</table>
	</center>

<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", 2, false);
</script>

<%-- **** Task specific area ends here **** --%>
