<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240312133510 --%>
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
			<input type="hidden" name="pageID" value="NFCL3050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Customer Account Search">
			
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- ###TAB - HEAD --%>
				<div class="pTab_HEAD">
					<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				</div>
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY" style="padding-top:5px;" align="center">
					<fieldset style="width:98%">
					<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;margin-top:3">
						<col width="120">
						<col width="340">
						<col width="110">
						<col width="300">
						<col width="">
						<tr>
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_H" ezfDispName="srchOptNm_H" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width: 320px\" id=\"srchOptPk\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td>
								<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
							</td>
							<td>
								<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					</fieldset>
					<br><br>
					<fieldset style="width:98%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" >Bill To Customer Name(*)</ezf:anchor></td>
							<td><ezf:inputText name="billToCustAcctNm_H" ezfName="billToCustAcctNm_H" value="1234567890123456" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" >Bill To Customer Number(*)</ezf:anchor></td>
							<td><ezf:inputText name="billToCustAcctCd_H" ezfName="billToCustAcctCd_H" value="1234567890123456" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td></td>
						</tr>
						<tr>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" >Ship To Customer Name(*)</ezf:anchor></td>
							<td><ezf:inputText name="shipToLocNm_H" ezfName="shipToLocNm_H" value="1234567890123456" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" >Ship To Customer Number(*)</ezf:anchor></td>
							<td><ezf:inputText name="shipToCustCd_H" ezfName="shipToCustCd_H" value="1234567890123456" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Invoice Number From</td>
							<td><ezf:inputText name="invNum_FR" ezfName="invNum_FR" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td class="stab">Invoice Number To</td>
							<td><ezf:inputText name="invNum_TO" ezfName="invNum_TO" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Invoice Date</td>
							<td>
								<ezf:inputText name="xxFromDt_FR" ezfName="xxFromDt_FR" otherAttr=" size=\"10\" maxlength=\"10\" id=\"xxFromDt_FR\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt_FR', 4);">
								-
								<ezf:inputText name="xxToDt_TO" ezfName="xxToDt_TO" otherAttr=" size=\"10\" maxlength=\"10\" id=\"xxToDt_TO\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt_TO', 4);">
							</td>
							<td class="stab">Due Date</td>
							<td>
								<ezf:inputText name="dueDt_FR" ezfName="dueDt_FR" otherAttr=" size=\"10\" maxlength=\"10\" id=\"dueDt_FR\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dueDt_FR', 4);">
								-
								<ezf:inputText name="dueDt_TO" ezfName="dueDt_TO" otherAttr=" size=\"10\" maxlength=\"10\" id=\"dueDt_TO\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dueDt_TO', 4);">
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Invoice Balance Low</td>
							<td><ezf:inputText name="dealRmngBalGrsAmt_LO" ezfName="dealRmngBalGrsAmt_LO" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td class="stab">Invoice Balance High</td>
							<td><ezf:inputText name="dealRmngBalGrsAmt_HI" ezfName="dealRmngBalGrsAmt_HI" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Invoice Amount Low</td>
							<td><ezf:inputText name="invTotDealNetAmt_LO" ezfName="invTotDealNetAmt_LO" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td class="stab">Invoice Amount High</td>
							<td><ezf:inputText name="invTotDealNetAmt_HI" ezfName="invTotDealNetAmt_HI" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Invoice Type</td>
							<td>
								<ezf:select name="dsInvTpCd_L" ezfName="dsInvTpCd_L" ezfBlank="1" ezfCodeName="dsInvTpCd_H" ezfDispName="dsInvTpNm_H" otherAttr=" style=\"width:217px;\""/>
							</td>
							<td class="stab">Invoice Class</td>
							<td>
								<ezf:select name="invTpCd_L" ezfName="invTpCd_L" ezfBlank="1" ezfCodeName="invTpCd_H" ezfDispName="invTpNm_H" otherAttr=" style=\"width:217px;\""/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Source</td>
							<td>
								<ezf:select name="sysSrcCd_L" ezfName="sysSrcCd_L" ezfBlank="1" ezfCodeName="sysSrcCd_H" ezfDispName="sysSrcNm_H" otherAttr=" style=\"width:217px;\""/>
							</td>
							<td class="stab">GL Date</td>
							<td>
								<ezf:inputText name="glDt_FR" ezfName="glDt_FR" otherAttr=" size=\"10\" maxlength=\"10\" id=\"glDt_FR\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('glDt_FR', 4);">
								-
								<ezf:inputText name="glDt_TO" ezfName="glDt_TO" otherAttr=" size=\"10\" maxlength=\"10\" id=\"glDt_TO\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('glDt_TO', 4);">
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Source Number(*)</td>
							<td><ezf:inputText name="srcSysDocNum" ezfName="srcSysDocNum" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
							<td class="stab">Bill Number(*)</td>
							<td><ezf:inputText name="grpInvNum_H" ezfName="grpInvNum_H" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Model Number(*)</td>
							<td><ezf:inputText name="mdlNm_H" ezfName="mdlNm_H" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td class="stab">Include Closed Invoices</td>
							<td><ezf:inputCheckBox name="xxChkBox_CL" ezfName="xxChkBox_CL" value="Y" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Serial Number(*)</td>
							<td><ezf:inputText name="serNum_H" ezfName="serNum_H" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td class="stab">Include Consolidated Invoices</td>
							<td><ezf:inputCheckBox name="xxChkBox_CN" ezfName="xxChkBox_CN" value="Y" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Customer PO Number(*)</td>
							<td><ezf:inputText name="custIssPoNum_H" ezfName="custIssPoNum_H" otherAttr=" size=\"30\" maxlength=\"35\""/></td>
							<td class="stab">Completed /Incompleted Invoices</td>
							<td rowspan="3" class="stab" valign="top">
								<ezf:inputRadio name="xxRadioBtn_H" ezfName="xxRadioBtn_H" value="1" /></input>Completed Only
								<ezf:inputRadio name="xxRadioBtn_H" ezfName="xxRadioBtn_H" value="2" /></input>Incompleted Only
								<ezf:inputRadio name="xxRadioBtn_H" ezfName="xxRadioBtn_H" value="3" /></input>All
							</td>
						</tr>
					</table>
					<table border="0" width="100%" cellpadding="1" cellspacing="0">
						<col width="187">
						<col width="326">
						<col width="230">
						<col>
						<col>
						<tr>
							<td class="stab">CI Number</td>
							<td><ezf:inputText name="custIncdtId" ezfName="custIncdtId" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td class="stab">Creation Date</td>
							<td>
								<ezf:inputText name="xxCratDt_FR" ezfName="xxCratDt_FR" otherAttr=" size=\"10\" maxlength=\"10\" id=\"xxCratDt_FR\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxCratDt_FR', 4);">
								-
								<ezf:inputText name="xxCratDt_TO" ezfName="xxCratDt_TO" otherAttr=" size=\"10\" maxlength=\"10\" id=\"xxCratDt_TO\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxCratDt_TO', 4);">
							</td>
							<td rowspan ="2"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
						</tr>
						<tr>
							<td>
								<fieldset id="invoiceError">
									<table>
										<col width="140">
										<col width="20">
									<td class="stab">Invoice Error</td>
									<td>&nbsp;</td>
									<td><ezf:inputCheckBox name="xxChkBox_ER" ezfName="xxChkBox_ER" value="Y" /></td>
									</table>
								</fieldset>
							</td>
							<td>&nbsp;</td>
							<td class="stab">Created by</td>
							<td><ezf:inputText name="ezInUserID" ezfName="ezInUserID" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
						</tr>
					</table>
					</fieldset>
					<table border="0" cellpadding="0" cellspacing="0">
						<col width="220">
						<col width="170">
						<col width="660">
						<tr>
							<td>
								<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
							</td>
							<td align="left">
								<ezf:inputButton name="btnSlectAll" value="Select All" htmlClass="pBtn6" otherAttr=" id=\"btnSlectAll\""/>
								<ezf:inputButton name="btnUnslctAll" value="Unselect All" htmlClass="pBtn6" otherAttr=" id=\"btnUnslctAll\""/>
							</td>
							<td align="right">
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
<c:choose>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'VisibilityError'}">
					<div id="parentBoxA">
</c:when>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'InvisibilityError'}">
					<div id="parentBoxB">
</c:when>
</c:choose>
						<table>
							<tr>
								<td width="10"></td>
								<td>
									<div style="float:left; display:block"> <!-- left table -->
										<div id="leftTblHead" style="display:block;"></div>
										<div id="leftTbl" style="float:left; display:block; height:130px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id="rightTblHead" style="width:1083px; display:block; overflow:hidden; margin:0px;padding:0px;">
<c:choose>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'VisibilityError'}">
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0"  id="AHEAD" width="1299px" style="margin-right:20px">
												<col width="22" align="center">
												<col width="22" align="center">
												<col width="300" align="left">
												<col width="120" align="left">
												<col width="100" align="left">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="128" align="right">
												<col width="128" align="right">
												<col width="150" align="left">
												<col width="300" align="left">
												<col width="150" align="left">
												<col width="150" align="left">
												<col width="150" align="left">
												<col width="150" align="left">
												<col width="200" align="left">
												<col width="90" align="left">
												<col width="128" align="right">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="150" align="left">
												<col width="500" align="left">
												<col width="300" align="left">
												<tr height="24">
													<td id="AH0"  class="pClothBs">&nbsp;</td>
													<td id="AH1"  class="pClothBs">&nbsp;</td>
													<td id="AH2"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustAcctNm_A')">Customer Name<img id="sortIMG.billToCustAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH3"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustAcctCd_A')">Cust Num<img id="sortIMG.billToCustAcctCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH4"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invNum_A')">Invoice Num<img id="sortIMG.invNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH5"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invDt_A' )">Invoice Dt<img id="sortIMG.invDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH6"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invDueDt_A' )">Due Date<img id="sortIMG.invDueDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH7"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invTotDealNetAmt_A' )">Invoice Amount<img id="sortIMG.invTotDealNetAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH8"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealRmngBalGrsAmt_A' )">Balance<img id="sortIMG.dealRmngBalGrsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH9"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A' )">Sales Order/Contract#<img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH10" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrItem61Txt_B' )">Sales Rep<img id="sortIMG.xxScrItem61Txt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH11" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltPsnNm_A' )">Collector Name<img id="sortIMG.cltPsnNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH12" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsInvTpNm_A' )">Invoice Type<img id="sortIMG.dsInvTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH13" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'sysSrcNm_A' )">Source<img id="sortIMG.sysSrcNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH14" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invTpNm_A' )">Invoice Class<img id="sortIMG.invTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH15" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invSrcTxt_A' )">Batch Source<img id="sortIMG.invSrcTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH16" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arCashApplyStsNm_A' )">Status<img id="sortIMG.arCashApplyStsNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH17" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealCltDsptAmt_A' )">Dispute Amt<img id="sortIMG.dealCltDsptAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH18" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltDsptDt_A' )">Dispute Date<img id="sortIMG.cltDsptDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH19" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'glDt_A' )">GL Date<img id="sortIMG.glDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH20" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'fnlzInvFlg_A' )">Cmp<img id="sortIMG.fnlzInvFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH21" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'custIssPoNum_A' )">Customer PO#<img id="sortIMG.custIssPoNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH22" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invErrMsgTxt_A' )">Invoice Error Message Text<img id="sortIMG.invErrMsgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH23" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invldValTxt_A' )">Invalid Value Text<img id="sortIMG.invldValTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
											</table>
</c:when>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'InvisibilityError'}">
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0"  id="BHEAD" width="1299px" style="margin-right:20px">
												<col width="22" align="center">
												<col width="300" align="left">
												<col width="120" align="left">
												<col width="100" align="left">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="128" align="right">
												<col width="128" align="right">
												<col width="150" align="left">
												<col width="300" align="left">
												<col width="150" align="left">
												<col width="150" align="left">
												<col width="150" align="left">
												<col width="150" align="left">
												<col width="200" align="left">
												<col width="90" align="left">
												<col width="128" align="right">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="150" align="left">
												<tr height="24">
													<td id="AH0"  class="pClothBs">&nbsp;</td>
													<td id="AH1"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustAcctNm_A')">Customer Name<img id="sortIMG.billToCustAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH2"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustAcctCd_A')">Cust Num<img id="sortIMG.billToCustAcctCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH3"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invNum_A')">Invoice Num<img id="sortIMG.invNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH4"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invDt_A' )">Invoice Dt<img id="sortIMG.invDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH5"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invDueDt_A' )">Due Date<img id="sortIMG.invDueDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH6"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invTotDealNetAmt_A' )">Invoice Amount<img id="sortIMG.invTotDealNetAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH7"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealRmngBalGrsAmt_A' )">Balance<img id="sortIMG.dealRmngBalGrsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH8"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A' )">Sales Order/Contract#<img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH9"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrItem61Txt_B' )">Sales Rep<img id="sortIMG.xxScrItem61Txt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH10" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltPsnNm_A' )">Collector Name<img id="sortIMG.cltPsnNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH11" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsInvTpNm_A' )">Invoice Type<img id="sortIMG.dsInvTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH12" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'sysSrcNm_A' )">Source<img id="sortIMG.sysSrcNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH13" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invTpNm_A' )">Invoice Class<img id="sortIMG.invTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH14" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'invSrcTxt_A' )">Batch Source<img id="sortIMG.invSrcTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH15" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'arCashApplyStsNm_A' )">Status<img id="sortIMG.arCashApplyStsNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH16" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealCltDsptAmt_A' )">Dispute Amt<img id="sortIMG.dealCltDsptAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH17" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltDsptDt_A' )">Dispute Date<img id="sortIMG.cltDsptDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH18" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'glDt_A' )">GL Date<img id="sortIMG.glDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH19" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'fnlzInvFlg_A' )">Cmp<img id="sortIMG.fnlzInvFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH20" class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort('A', 'custIssPoNum_A' )">Customer PO#<img id="sortIMG.custIssPoNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
											</table>
</c:when>
</c:choose>
										</div><!-- rightTblHead-->
										<div id="rightTbl" style="width:1096px; height:137px; display:block; overflow:scroll; margin:0px; padding:0px;" >
<c:choose>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'VisibilityError'}">
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="1300px" >
												<col width="22" align="center">
												<col width="22" align="center">
												<col width="300" align="left">
												<col width="120" align="left">
												<col width="100" align="left">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="128" align="right">
												<col width="128" align="right">
												<col width="150" align="left">
												<col width="300" align="left">
												<col width="150" align="left">
												<col width="150" align="left">
												<col width="150" align="left">
												<col width="150" align="left">
												<col width="200" align="left">
												<col width="90" align="left">
												<col width="128" align="right">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="150" align="left">
												<col width="500" align="left">
												<col width="300" align="left">
</c:when>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'InvisibilityError'}">
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="B" width="1300px" >
												<col width="22" align="center">
												<col width="300" align="left">
												<col width="120" align="left">
												<col width="100" align="left">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="128" align="right">
												<col width="128" align="right">
												<col width="150" align="left">
												<col width="300" align="left">
												<col width="150" align="left">
												<col width="150" align="left">
												<col width="150" align="left">
												<col width="150" align="left">
												<col width="200" align="left">
												<col width="90" align="left">
												<col width="128" align="right">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="90" align="center">
												<col width="150" align="left">
</c:when>
</c:choose>
												<ezf:row ezfHyo="A">
													<tr id="A_rightTBLRow#{EZF_ROW_NUMBER}">
<c:choose>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'VisibilityError'}">
														<td style="height:25px; text-align:center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
</c:when>
</c:choose>
														<td style="height:25px; text-align:center"><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"radio#{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:label name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="invNum_A" ezfName="invNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="invDt_A" ezfName="invDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="invDueDt_A" ezfName="invDueDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="invTotDealNetAmt_A" ezfName="invTotDealNetAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="dealRmngBalGrsAmt_A" ezfName="dealRmngBalGrsAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="xxScrItem61Txt_B" ezfName="xxScrItem61Txt_B" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="cltPsnNm_A" ezfName="cltPsnNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="dsInvTpNm_A" ezfName="dsInvTpNm_A" value="1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
														<td><ezf:label name="sysSrcNm_A" ezfName="sysSrcNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="invTpNm_A" ezfName="invTpNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="invSrcTxt_A" ezfName="invSrcTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="arCashApplyStsNm_A" ezfName="arCashApplyStsNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="dealCltDsptAmt_A" ezfName="dealCltDsptAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="cltDsptDt_A_d" ezfName="cltDsptDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="glDt_A" ezfName="glDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="fnlzInvFlg_A" ezfName="fnlzInvFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="custIssPoNum_A" ezfName="custIssPoNum_A" value="1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
<c:choose>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'VisibilityError'}">
														<td><ezf:inputText name="invErrMsgTxt_A" ezfName="invErrMsgTxt_A" value="1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"70\""/></td>
														<td><ezf:inputText name="invldValTxt_A" ezfName="invldValTxt_A" value="1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\""/></td>
</c:when>
</c:choose>

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
					<table width="97%">
						<col width="200" align="left">
						<col align="right">
						<tr>
							<td><ezf:inputButton name="btnRegen" value="Regenerate Acct Dist" htmlClass="pBtn6" otherAttr=" style=\"width:170px;\" id=\"btnRegen\""/></td>
							<td><ezf:inputButton name="Activity" value="Activity" htmlClass="pBtn6" />
							   <ezf:inputButton name="Details" value="Details" htmlClass="pBtn6" /></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<c:choose>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'VisibilityError'}">
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", 3, true);
</script>
</c:when>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'InvisibilityError'}">
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxB", "BHEAD", "B", 2, true);
</script>
</c:when>
</c:choose>

<%-- **** Task specific area ends here **** --%>
