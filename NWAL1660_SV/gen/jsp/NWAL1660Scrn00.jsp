<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180911155523 --%>
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
			<input type="hidden" name="pageID" value="NWAL1660Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Changes">

			<center>
				<table style="width:1010px;table-layout:fixed;">
					<col width="600">
					<col width="100">
					<col>
					<col width="300">
					<tr>
						<td>
							<table style="width:600px;table-layout:fixed;">
								<colgroup>
									<col width="50">
									<col width="60">
									<col width="60">
									<col width="60">
									<col width="40">
									<col width="60">
									<col width="40">
								</colgroup>
								<tr>
									<td>Trx Number</td>
									<td class="pOut" colspan="2"><ezf:label name="ordSrcRefNum" ezfName="ordSrcRefNum" /></td>
									<td class="stab" align="right">Config Line#</td>
									<td class="pOut"><ezf:label name="dsOrdPosnNum" ezfName="dsOrdPosnNum" /></td>
									<td class="stab" align="right">Line Number</td>
									<td class="pOut"><ezf:label name="xxLineNum_DI" ezfName="xxLineNum_DI" /></td>
								</tr>
								<tr>
									<td class="stab">Item</td>
									<td class="pOut"><ezf:label name="mdseCd" ezfName="mdseCd" /></td>
									<td class="pOut" colspan="5"><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="IRC5255999999999999999999999999999999999999999999999888888888888888888888888888888888888888888888877777777777777777777999999999" otherAttr=" style=\"border:none;background-color:transparent;padding:0px;\" size=\"60\" maxlength=\"250\""/></td>
								</tr>
								<tr>
									<td colspan="7">
										<table border="1" cellpadding="1" cellspacing="0">
											<tr>
												<td class="pClothBs" align="center">Subtotal</td>
												<td class="pClothBs" align="center">Charges</td>
												<td class="pClothBs" align="center">Tax</td>
												<td class="pClothBs" align="center">Line Total</td>
												<td class="pClothBs" align="center">CCY</td>
												<td class="pClothBs" align="center">Qty</td>
												<td class="pClothBs" align="center">UOM</td>
												<td class="pClothBs" align="center">Each Qty</td>
											</tr>
											<tr>
												<td align="center"><ezf:inputText name="xxSubTotCalcPrcAmt" ezfName="xxSubTotCalcPrcAmt" value="10,000.00" otherAttr=" size=\"15\" maxlength=\"22\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/></td>
												<td align="center"><ezf:inputText name="xxTotChrgPrcAmt" ezfName="xxTotChrgPrcAmt" value="10,000.00" otherAttr=" size=\"15\" maxlength=\"22\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/></td>
												<td align="center"><ezf:inputText name="xxTotTaxAmt" ezfName="xxTotTaxAmt" value="10,000.00" otherAttr=" size=\"15\" maxlength=\"22\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/></td>
												<td align="center"><ezf:inputText name="xxLineTotPrcAmt" ezfName="xxLineTotPrcAmt" value="10,000.00" otherAttr=" size=\"15\" maxlength=\"22\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/></td>
												<td align="center"><ezf:inputText name="ccyCd" ezfName="ccyCd" value="USD" otherAttr=" size=\"3\" maxlength=\"3\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td align="center"><ezf:inputText name="ordCustUomQty" ezfName="ordCustUomQty" value="1" otherAttr=" size=\"5\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/></td>
												<td align="center"><ezf:inputText name="uomCd" ezfName="uomCd" value="CT" otherAttr=" size=\"4\" maxlength=\"4\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td align="center"><ezf:inputText name="inEachQty" ezfName="inEachQty" value="3" otherAttr=" size=\"5\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>
										<ezf:inputButton name="Calculate" value="Calculate" htmlClass="pBtn8" />
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>
										<ezf:inputButton name="Reset" value="Reset" htmlClass="pBtn8" />
									</td>
								</tr>
							</table>
						</td>
						<td>&nbsp</td>
						<td>
							<fieldset>
							<legend>Incentive Comp Adjustments</legend>
								<table cellpadding="0" cellspacing="0">
									<tr>
										<td>Svc Revenue Transfer[SRT]</td>
										<td>$</td>
										<td>
											<ezf:inputText name="dealSvcRevTrnsfAmt" ezfName="dealSvcRevTrnsfAmt" value="12,345.67" otherAttr=" size=\"12\" maxlength=\"22\" style=\"text-align:right;\""/>
										</td>
									</tr>
									<tr>
										<td>Svc Cost Transfer[Svc CT]</td>
										<td>$</td>
										<td>
											<ezf:inputText name="dealSvcCostTrnsfAmt" ezfName="dealSvcCostTrnsfAmt" value="12" otherAttr=" size=\"12\" maxlength=\"22\" style=\"text-align:right;\""/>
										</td>
									</tr>
									<tr>
										<td>Floor Adjustments</td>
										<td>$</td>
										<td>
											<ezf:inputText name="dealManFlAdjAmt" ezfName="dealManFlAdjAmt" value="12" otherAttr=" size=\"12\" maxlength=\"22\" style=\"text-align:right;\""/>
										</td>
									</tr>
									<tr>
										<td>Incentive Comp Rep Amount</td>
										<td>$</td>
										<td>
											<ezf:inputText name="dealManRepRevAdjAmt" ezfName="dealManRepRevAdjAmt" value="12" otherAttr=" size=\"12\" maxlength=\"22\" style=\"text-align:right;\""/>
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
				</table>
				<hr>


				<div style="width:980px; height:420px; overflow-y:scroll; margin:0px; padding:0px;">
					<table style="width:px;">
						<tr>
							<td>
								<fieldset>
								<legend>Sell Price</legend>
									<ezf:select name="specCondPrcPk_AS" ezfName="specCondPrcPk_AS" ezfBlank="1" ezfCodeName="specCondPrcPk_AL" ezfDispName="prcRuleDtlTxt_AL" otherAttr=" style=\"width:180px\""/>
									<ezf:inputButton name="AddSellPrice" value="Add" htmlClass="pBtn5" />
									<ezf:inputButton name="DeleteSellPrice" value="Delete" htmlClass="pBtn5" />
									<ezf:inputButton name="OpenWin_PriceAnalysisForSellPrice" value="Price Analysis" htmlClass="pBtn7" />

				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<col valign="top" align="right">
					<col valign="top" align="left">
					<tr>
						<td>

									<%-- LEFT-TABLE(TOP) --%>
									<div id="LeftTBL_Top" style="height:24; overflow-x:hidden; width:210;">
										<table border="1" cellpadding="1" cellspacing="0" width="210">
											<col align="center" width="025">
											<col align="center" width="185">
											<tr height="32">
												<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs">Price Rule / Adjustment Name</td>
											</tr>
										</table>
									</div>

									<%-- LEFT-TABLE(MID) --%>
									<div id="LeftTBL" style="overflow-x:hidden; width:210;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
										<table border="1" cellpadding="1" cellspacing="0" width="210" id="A1">
											<col align="center" width="025">
											<col align="center" width="185">
											<ezf:row ezfHyo="A">
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td>
														<ezf:inputCheckBox name="prcCondManDelFlg_A" ezfName="prcCondManDelFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"\""/>
													</td>
													<td>
														<ezf:inputText name="prcRuleNm_A" ezfName="prcRuleNm_A" value="Manual Price Override" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
													</td>
												</tr>
											</ezf:row>
											<ezf:skip>
												<tr height="25">
													<td>
														<input type="checkbox" id="" name="" ezfname="" value="Y">
													</td>
													<td>
														<input type="text" name="" ezfname="" value="Manual Price Override" size="25" maxlength="50" style="border:none;background-color:transparent;padding:0px;">
													</td>
												</tr>
												<tr height="25">
													<td>
														<input type="checkbox" id="" name="" ezfname="" value="Y">
													</td>
													<td>
														<input type="text" name="" ezfname="" value="Manual Price Override" size="25" maxlength="50" style="border:none;background-color:transparent;padding:0px;">
													</td>
												</tr>
											</ezf:skip>
										</table>
									</div>
						</td>
						<td>
									<%-- RIGHT-TABLE(TOP) --%>
									<div id="RightTBL_Top" style="overflow-x:hidden; width:760" onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ) );">
										<table style="width:1175px;table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
											<col align="center" width="065">
											<col align="center" width="100">
											<col align="center" width="150">
											<col align="center" width="150">
											<col align="center" width="180">
											<col align="center" width="100">
											<col align="center" width="070">
											<col align="center" width="120">
											<col align="center" width="120">
											<col align="center" width="120">
											<tr height="32">
												<td class="pClothBs">ID</td>
												<td class="pClothBs">Rule Category</td>
												<td class="pClothBs">Price Rule Line Detail<br>:Adjustment Name</td>
												<td class="pClothBs">Price Rule Line Detail<br>:Adjustment</td>
												<td class="pClothBs">Adjustment Type</td>
												<td class="pClothBs">Automatically Applied</td>
												<td class="pClothBs">Adjusted %</td>
												<td class="pClothBs">Adjustment Value</td>
												<td class="pClothBs">List Price</td>
												<td class="pClothBs">Final Sell Price</td>
											</tr>
										</table>
									</div>

									<%-- RIGHT-TABLE(MID) --%>
									<div id="RightTBL" style=" overflow-x:scroll; width:760" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ) );">
										<table style="width:1175px;table-layout:fixed;font-size:6" border="1" cellpadding="1" cellspacing="0" id="A2">
											<col align="center" width="065">
											<col align="center" width="100">
											<col align="center" width="150">
											<col align="center" width="150">
											<col align="center" width="180">
											<col align="center" width="100">
											<col align="center" width="070">
											<col align="center" width="120">
											<col align="center" width="120">
											<col align="center" width="120">
										<ezf:row ezfHyo="A">
											<tr height="25">
												<td>
													<ezf:inputText name="prcRuleHdrPk_A" ezfName="prcRuleHdrPk_A" value="5001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"28\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/>
												</td>
												<td>
													<ezf:inputText name="prcRuleCatgDescTxt_A" ezfName="prcRuleCatgDescTxt_A" value="Discount" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
												</td>
												<td>
													<ezf:inputText name="prcRuleDtlChrgNm_A" ezfName="prcRuleDtlChrgNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
												</td>
												<td>
													<ezf:inputText name="prcRuleAttrbDescTxt_A" ezfName="prcRuleAttrbDescTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;text-align:center;\""/>
												</td>
												<td>
													<ezf:inputText name="prcRuleAdjTpDescTxt_A" ezfName="prcRuleAdjTpDescTxt_A" value="Get Negotiated Deal" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
												</td>
												<td>
													<ezf:inputText name="applyAutoFlg_A" ezfName="applyAutoFlg_A" value="N" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\" maxlength=\"1\" style=\"border:none;background-color:transparent;padding:0px;\""/>
												</td>
												<td>
													<ezf:inputText name="manPrcAmtRate_A" ezfName="manPrcAmtRate_A" value="10.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"22\" style=\"text-align:right;\""/>
												</td>
												<td>
													<ezf:inputText name="unitPrcAmt_A" ezfName="unitPrcAmt_A" value="100.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"22\" style=\"text-align:right;\""/>
												</td>
												<td>
													<ezf:inputText name="funcUnitListPrcAmt_A" ezfName="funcUnitListPrcAmt_A" value="1,200.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"22\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/>
												</td>
												<td>
													<ezf:inputText name="funcNetSellPrcAmt_A" ezfName="funcNetSellPrcAmt_A" value="1,200.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"22\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/>
												</td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="25">
												<td>
													<input type="text" name="" ezfname="" value="5001" size="8" maxlength="28" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="Discount" size="15" maxlength="50" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="" size="20" maxlength="22" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="" size="20" maxlength="22" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="Get Negotiated Deal" size="25" maxlength="30" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="N" size="1" maxlength="1" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="10.00" size="6" maxlength="22" style="text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="100.00" size="14" maxlength="22" style="text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="1,200.00" size="14" maxlength="22" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="1,200.00" size="14" maxlength="22" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
											</tr>
											<tr height="25">
												<td>
													<input type="text" name="" ezfname="" value="5001" size="8" maxlength="17" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="Discount" size="15" maxlength="30" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="" size="20" maxlength="20" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="" size="20" maxlength="20" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="Get Negotiated Deal" size="25" maxlength="30" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="N" size="1" maxlength="1" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="10.00" size="6" maxlength="10" style="text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="100.00" size="14" maxlength="17" style="text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="1,200.00" size="14" maxlength="17" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="1,200.00" size="14" maxlength="17" style="border:none;background-color:transparent;padding:0px;text-align:right;">
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

						<tr>
							<td>
								<fieldset>
								<legend>Charges</legend>
									<ezf:select name="specCondPrcPk_BS" ezfName="specCondPrcPk_BS" ezfBlank="1" ezfCodeName="specCondPrcPk_BL" ezfDispName="prcRuleDtlTxt_BL" otherAttr=" style=\"width:180px\""/>
									<ezf:inputButton name="AddCharges" value="Add" htmlClass="pBtn5" />
									<ezf:inputButton name="DeleteCharges" value="Delete" htmlClass="pBtn5" />
									<ezf:inputButton name="OpenWin_PriceAnalysisForCharges" value="Price Analysis" htmlClass="pBtn7" />
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<col valign="top" align="right">
					<col valign="top" align="left">
					<tr>
						<td>

									<%-- LEFT-TABLE(TOP) --%>
									<div id="LeftTBL_TopB" style="height:24; overflow-x:hidden; width:210;">
										<table border="1" cellpadding="1" cellspacing="0" width="210">
											<col align="center" width="025">
											<col align="center" width="185">
											<tr height="32">
												<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs">Price Rule / Adjustment Name</td>
											</tr>
										</table>
									</div>

									<%-- LEFT-TABLE(MID) --%>
									<div id="LeftTBLB" style="overflow-x:hidden; width:210;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBLB' ) );">
										<table border="1" cellpadding="1" cellspacing="0" width="210" id="B1">
											<col align="center" width="025">
											<col align="center" width="185">
											<ezf:row ezfHyo="B">
												<tr height="25" id="id_row{EZF_ROW_NUMBER}">
													<td>
														<ezf:inputCheckBox name="prcCondManDelFlg_B" ezfName="prcCondManDelFlg_B" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"\""/>
													</td>
													<td>
														<ezf:inputText name="prcRuleNm_B" ezfName="prcRuleNm_B" value="Manual Price Override" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
													</td>
												</tr>
											</ezf:row>
											<ezf:skip>
												<tr height="25">
													<td>
														<input type="checkbox" id="" name="" ezfname="" value="Y">
													</td>
													<td>
														<input type="text" name="" ezfname="" value="Manual Price Override" size="25" maxlength="50" style="border:none;background-color:transparent;padding:0px;">
													</td>
												</tr>
												<tr height="25">
													<td>
														<input type="checkbox" id="" name="" ezfname="" value="Y">
													</td>
													<td>
														<input type="text" name="" ezfname="" value="Manual Price Override" size="25" maxlength="50" style="border:none;background-color:transparent;padding:0px;">
													</td>
												</tr>
												<tr height="25">
													<td>
														<input type="checkbox" id="" name="" ezfname="" value="Y">
													</td>
													<td>
														<input type="text" name="" ezfname="" value="Manual Price Override" size="25" maxlength="50" style="border:none;background-color:transparent;padding:0px;">
													</td>
												</tr>
											</ezf:skip>
										</table>
									</div>
						</td>
						<td>
									<%-- RIGHT-TABLE(TOP) --%>
									<div id="RightTBL_TopB" style="overflow-x:hidden; width:760" onScroll="synchroScrollLeft( this.id, new Array( 'RightTBLB' ) );">
										<table style="width:935px;table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
											<col align="center" width="065">
											<col align="center" width="100">
											<col align="center" width="150">
											<col align="center" width="150">
											<col align="center" width="180">
											<col align="center" width="100">
											<col align="center" width="070">
											<col align="center" width="120">
											<tr height="32">
												<td class="pClothBs" align="center">ID</td>
												<td class="pClothBs" align="center">Rule Category</td>
												<td class="pClothBs" align="center">Price Rule Line Detail<br>:Adjustment Name</td>
												<td class="pClothBs" align="center">Price Rule Line Detail<br>:Adjustment</td>
												<td class="pClothBs" align="center">Adjustment Type</td>
												<td class="pClothBs" align="center">Automatically Applied</td>
												<td class="pClothBs" align="center">Adjusted %</td>
												<td class="pClothBs" align="center">Adjustment Value</td>
											</tr>
										</table>
									</div>

									<%-- RIGHT-TABLE(MID) --%>
									<div id="RightTBLB" style=" overflow-x:scroll; width:760" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBLB' ) );synchroScrollLeft( this.id, new Array( 'RightTBL_TopB' ) );">
										<table style="width:935px;table-layout:fixed;font-size:6" border="1" cellpadding="1" cellspacing="0" id="B2">
											<col align="center" width="065">
											<col align="center" width="100">
											<col align="center" width="150">
											<col align="center" width="150">
											<col align="center" width="180">
											<col align="center" width="100">
											<col align="center" width="070">
											<col align="center" width="120">
										<ezf:row ezfHyo="B">
											<tr height="25">
												<td>
													<ezf:inputText name="prcRuleHdrPk_B" ezfName="prcRuleHdrPk_B" value="5001" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"28\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/>
												</td>
												<td>
													<ezf:inputText name="prcRuleCatgDescTxt_B" ezfName="prcRuleCatgDescTxt_B" value="Discount" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
												</td>
												<td>
													<ezf:inputText name="prcRuleDtlChrgNm_B" ezfName="prcRuleDtlChrgNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"22\" style=\"border:none;background-color:transparent;padding:0px;\""/>
												</td>
												<td>
													<ezf:inputText name="prcRuleAttrbDescTxt_B" ezfName="prcRuleAttrbDescTxt_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"22\" style=\"border:none;background-color:transparent;padding:0px;text-align:center;\""/>
												</td>
												<td>
													<ezf:inputText name="prcRuleAdjTpDescTxt_B" ezfName="prcRuleAdjTpDescTxt_B" value="Get Negotiated Deal" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
												</td>
												<td>
													<ezf:inputText name="applyAutoFlg_B" ezfName="applyAutoFlg_B" value="N" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"1\" maxlength=\"1\" style=\"border:none;background-color:transparent;padding:0px;\""/>
												</td>
												<td>
													<ezf:inputText name="manPrcAmtRate_B" ezfName="manPrcAmtRate_B" value="10.00" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"22\" style=\"text-align:right;\""/>
												</td>
												<td>
													<ezf:inputText name="unitPrcAmt_B" ezfName="unitPrcAmt_B" value="100.00" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"22\" style=\"text-align:right;\""/>
												</td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="25">
												<td>
													<input type="text" name="" ezfname="" value="5001" size="8" maxlength="17" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="Discount" size="15" maxlength="30" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="" size="20" maxlength="20" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="" size="20" maxlength="20" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="Get Negotiated Deal" size="25" maxlength="30" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="N" size="1" maxlength="1" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="10.00" size="6" maxlength="10" style="text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="100.00" size="14" maxlength="17" style="text-align:right;">
												</td>
											</tr>
											<tr height="25">
												<td>
													<input type="text" name="" ezfname="" value="5001" size="8" maxlength="17" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="Discount" size="15" maxlength="30" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="" size="20" maxlength="20" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="" size="20" maxlength="20" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="Get Negotiated Deal" size="25" maxlength="30" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="N" size="1" maxlength="1" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="10.00" size="6" maxlength="10" style="text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="100.00" size="14" maxlength="17" style="text-align:right;">
												</td>
											</tr>
											<tr height="25">
												<td>
													<input type="text" name="" ezfname="" value="5001" size="8" maxlength="17" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="Discount" size="15" maxlength="30" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="" size="20" maxlength="20" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="" size="20" maxlength="20" style="border:none;background-color:transparent;padding:0px;text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="Get Negotiated Deal" size="25" maxlength="30" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="N" size="1" maxlength="1" style="border:none;background-color:transparent;padding:0px;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="10.00" size="6" maxlength="10" style="text-align:right;">
												</td>
												<td>
													<input type="text" name="" ezfname="" value="100.00" size="14" maxlength="17" style="text-align:right;">
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

						<tr>
							<td>
								<fieldset>
								<legend>Tax</legend>
									<div style="width:970px; height:px; margin:0px; padding:0px;">
										<table style="width:px;" border="1" cellpadding="1" cellspacing="0" id="C">
											<col align="left" width="100">
											<col align="right" width="70">
											<col align="right" width="90">
											<tr>
												<td class="pClothBs" align="center">Tax Jurisdiction</td>
												<td class="pClothBs" align="center">Rate %</td>
												<td class="pClothBs" align="center">Amount</td>
											</tr>
										<ezf:row ezfHyo="C">
											<tr>
												<td align="center"><ezf:inputText name="prcCondTpDescTxt_C" ezfName="prcCondTpDescTxt_C" value="State-NJ" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;text-align:left;\""/></td>
												<td align="center"><ezf:inputText name="autoPrcAmtRate_C" ezfName="autoPrcAmtRate_C" value="10.00000" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/></td>
												<td align="center"><ezf:inputText name="calcPrcAmtRate_C" ezfName="calcPrcAmtRate_C" value="1,000.00" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"16\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/></td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr>
												<td align="center"><input type="text" name="" ezfname="" value="County-NJ" size="20" maxlength="30" style="border:none;background-color:transparent;padding:0px;text-align:left;"></td>
												<td align="center"><input type="text" name="" ezfname="" value="1.23000" size="8" maxlength="10" style="border:none;background-color:transparent;padding:0px;text-align:right;"></td>
												<td align="center"><input type="text" name="" ezfname="" value="123.00" size="12" maxlength="16" style="border:none;background-color:transparent;padding:0px;text-align:right;"></td>
											</tr>
											<tr>
												<td align="center"><input type="text" name="" ezfname="" value="City-NJ" size="20" maxlength="30" style="border:none;background-color:transparent;padding:0px;text-align:left;"></td>
												<td align="center"><input type="text" name="" ezfname="" value="3.21000" size="8" maxlength="10" style="border:none;background-color:transparent;padding:0px;text-align:right;"></td>
												<td align="center"><input type="text" name="" ezfname="" value="321.00" size="12" maxlength="16" style="border:none;background-color:transparent;padding:0px;text-align:right;"></td>
											</tr>
										</ezf:skip>
										</table>
									</div>
								</fieldset>
							</td>
						</tr>
					</table>
				</div>
				<div style="width:980px; height:20px; text-align:right; margin:0px; padding:0px;">
					<ezf:inputButton name="OpenWin_PriceHistory" value="Price History" htmlClass="pBtn7" />
				</div>
			</center>


<%-- **** Task specific area ends here **** --%>
