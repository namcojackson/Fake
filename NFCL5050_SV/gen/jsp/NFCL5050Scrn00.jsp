<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190921124515 --%>
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
			<input type="hidden" name="pageID" value="NFCL5050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Auto Cash Application - Invoice Inquiry">
<center>
	<table width="100%">
		<tr>
			<td>
				<table border="0">
					<col width="">
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<col width="70">
								<col width="98">
								<col width="15" align="center">
								<col width="98">
								<col width="18">
								<col width="70">
								<col width="40">
								<col width="20">
								<col width="40">
								<col width="20">
								<col width="40">
								<col width="20">
								<col width="80">
								<col width="3">
								<col width="250">
								<tr>
									<td class="stab">Trx Num</td>
									<td colspan="3">
										<ezf:inputText name="xxTrxNumSrchTxt" ezfName="xxTrxNumSrchTxt" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW35" otherAttr=" size=\"45\" id=\"xxTrxNumSrchTxt\" ezftoupper=\"\""/>
									</td>
									<td></td>
									<td class="stab">Trx Type</td>
									<td><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" otherAttr=" id=\"xxChkBox_H1\""/>INV</td>
									<td></td>
									<td><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" otherAttr=" id=\"xxChkBox_H2\""/>CRM</td>
									<td></td>
									<td><ezf:inputCheckBox name="xxChkBox_H3" ezfName="xxChkBox_H3" value="Y" otherAttr=" id=\"xxChkBox_H3\""/>ACC</td>
									<td></td>
									<td><ezf:inputCheckBox name="xxChkBox_H4" ezfName="xxChkBox_H4" value="Y" otherAttr=" id=\"xxChkBox_H4\""/>Late Fee</td>
									<td></td>
									<td><ezf:inputCheckBox name="xxChkBox_H5" ezfName="xxChkBox_H5" value="Y" otherAttr=" id=\"xxChkBox_H5\""/>Related Customer</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<col width="70">
								<col width="90">
								<col width="130">
								<col width="109">
								<col width="55">
								<col width="85">
								<col width="83">
								<col width="105">
								<col width="15" align="center">
								<col width="105" >
								<col width="">
								<tr>
									<!-- START 2016/04/19 S.Fujita [QC#6533,MOD] -->
									<!--
									<td class="stab">Cust Ref Num</td>
									<td>
										<input type="text" class="" size="20" maxlength="35" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW35" name="arCustRefNum" ezfname="arCustRefNum" id="arCustRefNum" ezftoupper>
									</td>
									<td>-</td>
									<td>
										<input type="text" class="" size="20" maxlength="35" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW35" name="arCustRefNum_H1" ezfname="arCustRefNum_H1" id="arCustRefNum_H1" ezftoupper>
									</td>
									-->
									<td class="stab">Cust PO Num</td>
									<td>
										<ezf:inputText name="custIssPoNum" ezfName="custIssPoNum" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW35" otherAttr=" size=\"29\" maxlength=\"35\" id=\"custIssPoNum\""/>
									</td>
									<!-- END 2016/04/19 S.Fujita [QC#6533,MOD] -->
									<td></td>
									<td class="stab">Shipping Num</td>
									<td>
										<ezf:inputText name="soNum" ezfName="soNum" value="WWWWWWW8" otherAttr=" size=\"8\" maxlength=\"8\" id=\"soNum\" ezftoupper=\"\""/>
									</td>
									<td></td>
									<td class="stab">Trx Date</td>
									<td>
										<ezf:inputText name="arTrxDt" ezfName="arTrxDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\" id=\"arTrxDt\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('arTrxDt', 4);" >
									</td>
									<td>-</td>
									<td>
										<ezf:inputText name="arTrxDt_H1" ezfName="arTrxDt_H1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\" id=\"arTrxDt_H1\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('arTrxDt_H1', 4);" >
									</td>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<col width="70">
								<col width="150">
								<col width="15" align="center">
								<col width="150">
								<col width="18">
								<col width="60">
								<col width="49">
								<col width="72">
								<col width="77">
								<col width="81">
								<col width="54">
								<col width="2">
								<col width="180">
								<col width="">
								<tr>
									<!-- START 2016/04/19 S.Fujita [QC#6533,MOD] -->
									<!--
									<td class="stab">Cust PO Num</td>
									<td>
										<input type="text" class="" size="29" maxlength="35" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW35" name="custIssPoNum" ezfname="custIssPoNum" id="custIssPoNum" ezftoupper>
									</td>
									-->
									<td class="stab">Gross Amt</td>
									<td>
										<ezf:inputText name="xxInpAmtNum" ezfName="xxInpAmtNum" value="-1,234,567,890,123.12" otherAttr=" size=\"21\" maxlength=\"21\" id=\"dealOrgGrsAmt\""/>
									</td>
									<td>-</td>
									<td>
										<ezf:inputText name="xxInpAmtNum_H1" ezfName="xxInpAmtNum_H1" value="-1,234,567,890,123.12" otherAttr=" size=\"21\" maxlength=\"21\" id=\"dealOrgGrsAmt_H1\""/>
									</td>
									<!-- END 2016/04/19 S.Fujita [QC#6533,MOD] -->
									<td></td>
									<td class="stab">Order Num</td>
									<td></td>
									<td>
										<ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="WWWWWWW8" otherAttr=" size=\"8\" maxlength=\"8\" id=\"cpoOrdNum\" ezftoupper=\"\""/>
									</td>
									<td></td>
									<td class="stab">Account</td>
									<td class="pOut"><ezf:label name="payerCustCd" ezfName="payerCustCd" otherAttr=" id=\"payerCustCd\""/></td>
									<td></td>
									<td align="center"><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW60" otherAttr=" size=\"26\""/></td>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<col width="70">
								<col width="72">
								<col width="495">
								<col width="110">
								<col width="150">
								<col width="72">
								<col width="">
								<tr>
									<!-- START 2016/04/19 S.Fujita [QC#6533,MOD] -->
									<!--
									<td class="stab">Gross Amt</td>
									<td>
										<input type="text" class="" size="21" maxlength="21" value="-1,234,567,890,123.12" name="xxInpAmtNum" ezfname="xxInpAmtNum" id="dealOrgGrsAmt">
									</td>
									<td>-</td>
									<td>
										<input type="text" class="" size="21" maxlength="21" value="-1,234,567,890,123.12" name="xxInpAmtNum_H1" ezfname="xxInpAmtNum_H1" id="dealOrgGrsAmt_H1">
									</td>
									<td></td>
									<td class="stab">Summary Billing Num</td>
									-->
									<td class="stab">Bill Number(*)</td>
									<!-- END 2016/04/19 S.Fujita [QC#6533,MOD] -->
									<td>
										<ezf:inputText name="grpInvNum" ezfName="grpInvNum" value="WWWWWWW8" otherAttr=" size=\"8\" maxlength=\"8\" id=\"grplnvNum\" ezftoupper=\"\""/>
									</td>
									<td></td>
									<td class="stab">Related Account No</td>
									<td>
										<ezf:inputText name="payerCustCd_H1" ezfName="payerCustCd_H1" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"8\" maxlength=\"20\" id=\"payerCustCd_H1\" ezftoupper=\"\""/>
									</td>
									<!-- START 2016/04/25 S.Fujita [QC#6528,MOD] -->
									<!--
									<td><input type="button" class="pBtn2" value="Calc" name="CalcGrossAmount" onclick="sendServer(this)"></td>
									<td></td>
									<td class="stab">Balance</td>
									<td class="pOut" align="right"><label ezfout name="xxBalApplyGrsAmt" ezfname="xxBalApplyGrsAmt">+9,999,999,999,999.99</label></td>
									<td></td>
									-->
									<!-- END 2016/04/25 S.Fujita [QC#6528,MOD] -->
									<td><ezf:inputButton name="SearchInvoice" value="Search" htmlClass="pBtn6" otherAttr=" id=\"SearchInvoice\""/></td>
									<td></td>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>

				<table border="0" width="1014">
					<tr>
							<col width="200">
							<col width="72" align="right">
							<col width="72" align="right">
							<col width="700">
							<col width="13">
							<td>
								<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
							</td>
							<td><ezf:inputButton name="Check_All" value="CheckAll" htmlClass="pBtn6" otherAttr=" id=\"Check_All\""/></td>
							<td><ezf:inputButton name="Un_Check_All" value="UncheckAll" htmlClass="pBtn6" otherAttr=" id=\"Un_Check_All\""/></td>
							<td align="right">
								<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"           value="A" />
									<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"          value="FULL" />
								</jsp:include>
							</td>
							<td>&nbsp;</td>
					</tr>
				</table>

			<div id="parentBoxA">
				<table border="0">
					<tr>
						<td valign="top">
							<div style="float:left; display:block"> <!-- left table -->
								<div id='leftTblHead' style="display:block;">
								</div>
								<div id='leftTbl' style="float:left; display:block; height:338px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
								</div>
							</div>  <!-- left table -->
							<div style="float:left"> <!-- right table -->
							<div id='rightTblHead' style="width:983px; display:block; overflow:hidden; margin:0px;padding:0px;">
								<table border="1" cellpadding="1" cellspacing="0" width="2400" id="AHEAD" style="margin-right:20px; table-layout:fixed">
									<col width="030" align="center">
									<col width="140" align="center">
									<col width="050" align="center">
									<col width="100" align="center">
									<col width="120" align="center">
									<col width="150" align="center">
									<col width="150" align="center">
									<col width="120" align="center">
									<col width="150" align="center">
									<col width="120" align="center">
									<col width="150" align="center">
									<col width="130" align="center">
									<col width="070" align="center">
									<col width="180" align="center">
									<col width="100" align="center">
									<col width="100" align="center">
									<col width="100" align="center">
									<col width="180" align="center">
									<col width="100" align="center">
									<col width="180" align="center">
									<tr height="24">
										<td id="AH0" class="pClothBs colFix">&nbsp;</td>
										<!-- START 2016/04/19 S.Fujita [QC#6533,MOD] -->
										<!--
										<td id="AH1" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxNum_A1' )">Trx Num<img id="sortIMG.arTrxNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										-->
										<td id="AH1" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arCustRefNum_A1' )">Trx Num<img id="sortIMG.arCustRefNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<!-- END 2016/04/19 S.Fujita [QC#6533,MOD] -->
										<td id="AH2" class="pClothBs colFix">Trx</td>
										<!-- START 2016/04/19 S.Fujita [QC#6533,MOD] -->
										<!--
										<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arCustRefNum_A1' )">Customer Ref Num<img id="sortIMG.arCustRefNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										-->
										<!-- END 2016/04/19 S.Fujita [QC#6533,MOD] -->
										<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxDt_A1' )">Trx Date<img id="sortIMG.arTrxDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH4" class="pClothBs">Applied</td>
										<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealRmngBalGrsAmt' )">Balance<img id="sortIMG.dealRmngBalGrsAmt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH6" class="pClothBs">Gross</td>
										<td id="AH7" class="pClothBs">Credited</td>
										<td id="AH8" class="pClothBs">Adjusted</td>
										<td id="AH9" class="pClothBs">Sales</td>
										<td id="AH10" class="pClothBs">Freight</td>
										<td id="AH11" class="pClothBs">Tax / Ins</td>
										<td id="AH12" class="pClothBs">Customer</td>
										<td id="AH13" class="pClothBs">Customer Name</td>
										<td id="AH14" class="pClothBs">Due Date</td>
										<td id="AH15" class="pClothBs">CC Order ID</td>
										<td id="AH16" class="pClothBs">Order Num</td>
										<td id="AH17" class="pClothBs">Customer PO#</td>
										<td id="AH18" class="pClothBs">Shipped Date</td>
										<!-- START 2016/04/19 S.Fujita [QC#6533,MOD] -->
										<!--
										<td id="AH21" class="pClothBs">Summary Billing#</td>
										-->
										<td id="AH19" class="pClothBs">Customer Ref Num</td>
										<!-- END 2016/04/19 S.Fujita [QC#6533,MOD] -->
									</tr>
								</table>
							</div>

							<!-- RIGHT-TABLE(MID) -->
							<div id="rightTbl" style="width:1000px; height:338px; display:block; overflow:scroll; margin:0px; padding:0px;" >
								<table border="1" cellpadding="1" cellspacing="0" width="2400" id="A" style="table-layout:fixed">
									<col width="030" align="center">
									<col width="140">
									<col width="050" align="center">
									<col width="100" align="center">
									<col width="120" align="right">
									<col width="150" align="right">
									<col width="150" align="right">
									<col width="120" align="right">
									<col width="150" align="right">
									<col width="120" align="right">
									<col width="150" align="right">
									<col width="130" align="right">
									<col width="70">
									<col width="180">
									<col width="100" align="center">
									<col width="100">
									<col width="100">
									<col width="180">
									<col width="100" align="center">
									<col width="180">
									<ezf:row ezfHyo="A">
										<tr height="24" id="id_row{EZF_ROW_NUMBER}">
											<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></td>
											<!-- START 2016/04/19 S.Fujita [QC#6533,MOD] -->
											<!--
											<td><label ezfout name="arTrxNum_A1" ezfname="arTrxNum_A1" ezfhyo="A" ezfArrayNo="0" id="arTrxNum_A1">WWWWWWWWWWW13</label></td>
											-->
											<td align="left"><ezf:inputText name="arCustRefNum_A1" ezfName="arCustRefNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW35" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"36\" id=\"arCustRefNum_A1#{EZF_ROW_NUMBER}\""/></td>
											<!-- END 2016/04/19 S.Fujita [QC#6533,MOD] -->
											<td align="center"><ezf:label name="arTrxTpCd_A1" ezfName="arTrxTpCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"arTrxTpCd_A1\""/></td>
											<!-- START 2016/04/19 S.Fujita [QC#6533,MOD] -->
											<!--
											<td align="center"><input type="text" class="pPro" size="15" readOnly value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW35" name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A" ezfArrayNo="0" id="arCustRefNum_A1#{EZF_ROW_NUMBER}"></td>
											-->
											<!-- END 2016/04/19 S.Fujita [QC#6533,MOD] -->
											<td><ezf:label name="arTrxDt_A1" ezfName="arTrxDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"arTrxDt_A1\""/></td>
											<td><ezf:label name="dealApplyGrsAmt" ezfName="dealApplyGrsAmt" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealApplyGrsAmt\""/></td>
											<td><ezf:label name="dealRmngBalGrsAmt" ezfName="dealRmngBalGrsAmt" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealRmngBalGrsAmt\""/></td>
											<td><ezf:label name="dealOrigGrsAmt_A1" ezfName="dealOrigGrsAmt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealOrigGrsAmt_A1\""/></td>
											<td><ezf:label name="dealApplyCrAmt" ezfName="dealApplyCrAmt" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealApplyCrAmt\""/></td>
											<td><ezf:label name="dealApplyAdjAmt" ezfName="dealApplyAdjAmt" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealApplyAdjAmt\""/></td>
											<td><ezf:label name="dealNetSlsAmt" ezfName="dealNetSlsAmt" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealNetSlsAmt\""/></td>
											<td><ezf:label name="dealFrtAmt" ezfName="dealFrtAmt" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealFrtAmt\""/></td>
											<td><ezf:label name="dealTaxAmt" ezfName="dealTaxAmt" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealTaxAmt\""/></td>
											<td><ezf:label name="billToCustAcctCd_A1" ezfName="billToCustAcctCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"billToCustAcctCd_A1\""/></td>
											<td align="left"><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW35" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"360\" id=\"dsAcctNm_A1\""/></td>
											<td><ezf:label name="invDueDt_A1" ezfName="invDueDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"invDueDt_A1\""/></td>
											<td><ezf:label name="crCardOrdNum_A1" ezfName="crCardOrdNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"crCardOrdNum_A1\""/></td>
											<td><ezf:label name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cpoOrdNum_A1\""/></td>
											<td><ezf:label name="custIssPoNum_A1" ezfName="custIssPoNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"custIssPoNum_A1\""/></td>
											<td><ezf:label name="shipDt_A1" ezfName="shipDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"shipDt_A1\""/></td>
											<td><ezf:label name="grpInvNum_A1" ezfName="grpInvNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"grpInvNum_A1\""/></td>
										</tr>
									</ezf:row>
									<ezf:skip>
									</ezf:skip>
								</table>
							</div> <!-- rightTbl -->
						</td>
					</tr>
				</table>
			<div> <!-- id="parentBoxA"-->
				<table border="0"  width="1000">
					<col align="right">
					<tr>
						<td><ezf:inputButton name="SelectInvoice" value="Select" htmlClass="pBtn6" otherAttr=" id=\"SelectInvoice\""/></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", 3, false);
</script>

<%-- **** Task specific area ends here **** --%>
