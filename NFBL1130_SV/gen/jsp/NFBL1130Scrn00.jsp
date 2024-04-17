<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220812103829 --%>
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
			<input type="hidden" name="pageID" value="NFBL1130Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="AP Accrual Write-off Search">

<%@ page import="business.servlet.NFBL1130.NFBL1130BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>

<% NFBL1130BMsg  bMsg = (NFBL1130BMsg)databean.getEZDBMsg(); %>
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<!-- ###TAB - HEAD -->
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Mach Mst Inq" class="pTab_ON"><a href="#">Invoice Entry</a></li>
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
					<table border="0" cellpadding="0" cellspacing="0" width="1100" style="margin-left:20px;">
						<col width="115" align="left">
						<col width="305" align="left">
						<col width="105" align="left">
						<col width="300" align="left">
						<col width="83"  align="left">
						<col width="83"  align="left">
						<col width="">
						<tr height="25">
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_H" ezfDispName="srchOptNm_H" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:290px;\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td><ezf:inputButton name="SaveSearch" ezfName="SaveSearch" value="SaveSearch" htmlClass="pBtn7" /></td>
							<td><ezf:inputButton name="DeleteSearch" ezfName="DeleteSearch" value="DeleteSearch" htmlClass="pBtn7" /></td>
							<td>&nbsp;</td>
						</tr>
					</table>
					<hr size="1" noshade>
					<div align="center" style="margin-top:5px;">
					<table border="0" cellpadding="0" cellspacing="0" width="99%">
						<col width="120">
						<col width="140">
						<col width="75">
						<col width="125">
						<col width="50">
						<col width="50">
						<col width="165">
						<col width="90">
						<col width="130">
						<col width="20">
						<col width="120">
						<col>
						<tr>
							<td class="stab">PO Number</td>
							<td><ezf:inputText name="poNum" ezfName="poNum" value="12345678901234567890123456789012345" otherAttr=" size=\"15\" maxlength=\"35\" ezftoupper=\"\""/></td>
							<td class="stab">Item</td>
							<td><ezf:inputText name="mdseCd" ezfName="mdseCd" value="1234567890123456" otherAttr=" size=\"15\" maxlength=\"16\" ezftoupper=\"\""/></td>
							<td class="stab" colspan="2">Receipt Number</td>
							<td><ezf:inputText name="delyOrdNum" ezfName="delyOrdNum" value="1234567890123456" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
							<td class="stab">Receipt Quantity</td>
							<td><ezf:inputText name="invRcvQty" ezfName="invRcvQty" value="1234567890123456" otherAttr=" size=\"10\" maxlength=\"20\" style=\"text-align:right\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:inputCheckBox name="xxChkBox_WO" ezfName="xxChkBox_WO" value="Y" /></td>
							<td class="stab">Include Write Off</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="stab">Invoice Number</td>
							<td><ezf:inputText name="apVndInvNum" ezfName="apVndInvNum" value="12345678901234567890123456789012345" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="prntVndCd_L" ezfName="" ezfEmulateBtn="1" ezfGuard="OnClick_SupplierLink" otherAttr=" id=\"prntVndCd_L\"">Supplier</ezf:anchor></td>
							<td><ezf:inputText name="prntVndCd" ezfName="prntVndCd" value="12345678901234567890" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:inputButton name="SearchSupplierName" value=">>" htmlClass="pBtn1" /></td>
							<td colspan="2"><ezf:inputText name="prntVndNm" ezfName="prntVndNm" value="123456789012345678901234567890" otherAttr=" size=\"26\" maxlength=\"240\" ezftoupper=\"\""/></td>
							<td class="stab">Invoice Quantity</td>
							<td><ezf:inputText name="invQty" ezfName="invQty" value="12345678901234567890" otherAttr=" size=\"10\" maxlength=\"20\" style=\"text-align:right\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:inputCheckBox name="xxChkBox_PM" ezfName="xxChkBox_PM" value="Y" /></td>
							<td class="stab">Include PO matched</td>
						</tr>
						<tr>
							<td class="stab">Accrual Account Code</td>
							<td><ezf:select name="acrlCoaAcctCd_S" ezfName="acrlCoaAcctCd_S" ezfBlank="1" ezfCodeName="acrlCoaAcctCd_C" ezfDispName="xxDplyByItemNm_D" otherAttr=" style=\"width:111px;\""/></td>
							<td class="stab">RWS Number</td>
							<td><ezf:inputText name="rwsNum" ezfName="rwsNum" value="12345678" otherAttr=" size=\"15\" maxlength=\"8\" ezftoupper=\"\""/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td class="stab"><ezf:inputCheckBox name="xxChkBox_CV" ezfName="xxChkBox_CV" value="Y" /></td>
							<td class="stab">Cost Change Variances</td>
						</tr>
						<tr>
							<td class="stab">Invoice Date From</td>
							<td>
								<ezf:inputText name="invDt_FR" ezfName="invDt_FR" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('invDt_FR', 4);" >
							</td>
							<td class="stab">To</td>
							<td>
								<ezf:inputText name="invDt_TO" ezfName="invDt_TO" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('invDt_TO', 4);" >
							</td>
							<td class="stab" colspan="2">Receipt Date From</td>
							<td>
								<ezf:inputText name="stkInDt_FR" ezfName="stkInDt_FR" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('stkInDt_FR', 4);" >
							</td>
							<td class="stab">To</td>
							<td>
								<ezf:inputText name="stkInDt_TO" ezfName="stkInDt_TO" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('stkInDt_TO', 4);" >
							</td>
							<td>&nbsp;</td>
							<td>
								<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
							</td>
						</tr>
					</table>
					</div>

					<hr size="1" noshade>

					<table border="0" cellpadding="0" cellspacing="0"width="1100" style="margin-left:20px;">
						<tr>
							<td align="left" class="stab"><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
							<td align="right">
                            	<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
								<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"     value="A" />
								<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
								<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
		    					</jsp:include>
							</td>
							<td width="25">
							</td>
						</tr>
					</table>

					<table border="0" cellpadding="0" cellspacing="0"width="1100">
						<tr>
							<td align="left"></td>
						</tr>
					</table>

					<div id="parentBoxA">
					<table border="0" cellpadding="1" cellspacing="0" width="1100" align="center">
						<tr>
							<td>
								<div style="float:left; display:block"><!-- left table -->
								    <div id='leftTblHead' style="display:block;"></div>
								    <div id='leftTbl' style="float:left; display:block; height:350; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px"></div>
								</div><!-- left table -->
								<div style="float:left"><!-- right table -->
								<div id='rightTblHead' style="display:block; overflow:hidden; margin:0px;padding:0px; width:1080;">
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="2385" id="AHEAD" style="margin-right:20px">
										<col align="center" width="98" >
										<col align="center" width="98" >
										<col align="center" width="80" >
										<col align="center" width="80" >
										<col align="center" width="80" >
										<col align="center" width="150" >
										<col align="center" width="40" >
										<col align="center" width="80" >
										<col align="center" width="80" >
										<col align="center" width="100" >
										<col align="center" width="100" >
										<col align="center" width="100" >
										<col align="center" width="100" >
										<col align="center" width="100" >
										<col align="center" width="100" >
										<col align="center" width="100" >
										<col align="center" width="80" >
										<col align="center" width="80" >
										<col align="center" width="95" >
										<col align="center" width="95" >
										<col align="center" width="60" >
										<col align="center" width="100" >
										<col align="center" width="120" >
										<col align="center" width="100" >
										<col align="center" width="330" >
										<tr height="40">
											<td id="AH0" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poNum_A1' )">PO#<img id="sortIMG.poNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH1" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'delyOrdNum_A1' )">Receipt#<img id="sortIMG.delyOrdNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'stkInDt_A1' )">Receipt Date<img id="sortIMG.stkInDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rwsNum_A1' )">RWS Number<img id="sortIMG.rwsNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prntVndCd_A1' )">Supplier Number<img id="sortIMG.prntVndCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dplyVndNm_A1' )">Supplier Name<img id="sortIMG.dplyVndNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poOrdDtlLineNum_A1' )">Line<img id="sortIMG.poOrdDtlLineNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invRcvQty_A1' )">Receipt Qty<img id="sortIMG.invRcvQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'thisMthFobCostAmt_A1' )">Unit Price<img id="sortIMG.thisMthFobCostAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'acInvJrnlCostAmt_A1' )">Receipt Amount<img id="sortIMG.acInvJrnlCostAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaAcctCd_A1' )">Account Accrual<img id="sortIMG.coaAcctCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCd_A1' )">PO Item<img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescShortTxt_A1' )">PO Description<img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCd_A2' )">Invoice Item<img id="sortIMG.mdseCd_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescShortTxt_A2' )">Invoice Description<img id="sortIMG.mdseDescShortTxt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'apVndInvNum_A1' )">Payable Invoice<img id="sortIMG.apVndInvNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invDt_A1' )">Invoice Date<img id="sortIMG.invDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invQty_A1' )">Invoice Qty<img id="sortIMG.invQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'acInvJrnlCostAmt_A2' )">Invoice Price<img id="sortIMG.acInvJrnlCostAmt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'acInvJrnlCostAmt_A3' )">Invoice Amt<img id="sortIMG.acInvJrnlCostAmt_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'wrtOffFlg_A1' )">Write off<img id="sortIMG.wrtOffFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'wrtOffDt_A1' )">Write off Date<img id="sortIMG.wrtOffDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'acrlWrtOffRsnCd_S' )">Write off Reason<img id="sortIMG.acrlWrtOffRsnCd_S" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'acrlWrtOffCmntTxt_A1' )">Remarks<img id="sortIMG.acrlWrtOffCmntTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxCmntTxt_A1' )">Write-off Account<img id="sortIMG.xxCmntTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										</tr>
									</table>
								</div>
								<div id='rightTbl' style="display:block; overflow:scroll; margin:0px; padding:0px; width:1097;height:367;" >
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="2385" id="A">
										<col align="center" width="98">
										<col align="center" width="98">
										<col align="center" width="80" >
										<col align="center" width="80" >
										<col align="center" width="80" >
										<col align="center" width="150" >
										<col align="center" width="40" >
										<col align="center" width="80" >
										<col align="center" width="80" >
										<col align="center" width="100" >
										<col align="center" width="100" >
										<col align="center" width="100" >
										<col align="center" width="100" >
										<col align="center" width="100" >
										<col align="center" width="100" >
										<col align="center" width="100" >
										<col align="center" width="80" >
										<col align="center" width="80" >
										<col align="center" width="95" >
										<col align="center" width="95" >
										<col align="center" width="60" >
										<col align="center" width="100" >
										<col align="center" width="120" >
										<col align="center" width="100" >
										<col align="center" width="330" >
										<ezf:row ezfHyo="A">
											<tr height="26">
												<td><ezf:inputText name="poNum_A1" ezfName="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"35\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="delyOrdNum_A1" ezfName="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="stkInDt_A1" ezfName="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="rwsNum_A1" ezfName="rwsNum_A1" value="12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="prntVndCd_A1" ezfName="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="dplyVndNm_A1" ezfName="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"240\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="poOrdDtlLineNum_A1" ezfName="poOrdDtlLineNum_A1" value="WWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="invRcvQty_A1" ezfName="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"15\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="thisMthFobCostAmt_A1" ezfName="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"25\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="acInvJrnlCostAmt_A1" ezfName="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"25\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="coaAcctCd_A1" ezfName="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"250\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="mdseCd_A2" ezfName="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="mdseDescShortTxt_A2" ezfName="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"250\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="apVndInvNum_A1" ezfName="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"15\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="invDt_A1" ezfName="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="invQty_A1" ezfName="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"15\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="acInvJrnlCostAmt_A2" ezfName="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"25\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="acInvJrnlCostAmt_A3" ezfName="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"25\" ezftoupper=\"\""/></td>
												<td><ezf:inputCheckBox name="wrtOffFlg_A1" ezfName="wrtOffFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
												<td>
													<ezf:inputText name="wrtOffDt_A1" ezfName="wrtOffDt_A1" value="10/01/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('wrtOffDt_A1', 4, {EZF_ROW_NUMBER});" >
												</td>
												<td>
													<ezf:select name="acrlWrtOffRsnCd_S" ezfName="acrlWrtOffRsnCd_S" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="acrlWrtOffRsnCd_C" ezfDispName="acrlWrtOffRsnNm_D" ezfCodeDispHyo="A" otherAttr=" style=\"width:111\""/>
												</td>
												<td><ezf:inputText name="acrlWrtOffCmntTxt_A1" ezfName="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"4000\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="xxCmntTxt_A1" ezfName="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"45\" maxlength=\"100\" ezftoupper=\"\""/></td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="26">
												<td><input type="text" name="poNum_A1" size="12" maxlength="35" ezftoupper="" ezfname="poNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"></td>
												<td><input type="text" name="delyOrdNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="20" ezftoupper="" ezfname="delyOrdNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="stkInDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="stkInDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="rwsNum_A1" value="12345678" size="10" maxlength="10" ezftoupper="" ezfname="rwsNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="prntVndCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="20" ezftoupper="" ezfname="prntVndCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="dplyVndNm_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="20" maxlength="60" ezftoupper="" ezfname="dplyVndNm_A1" class="pPro" readOnly></td>
												<td><input type="text" name="poOrdDtlLineNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="3" maxlength="3" ezftoupper="" ezfname="poOrdDtlLineNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invRcvQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invRcvQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="thisMthFobCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="25" ezftoupper="" ezfname="thisMthFobCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="coaAcctCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="8" maxlength="8" ezftoupper="" ezfname="coaAcctCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="mdseCd_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="16" ezftoupper="" ezfname="mdseCd_A2" class="pPro" readOnly></td>
												<td><input type="text" name="mdseDescShortTxt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="13" maxlength="250" ezftoupper="" ezfname="mdseDescShortTxt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="apVndInvNum_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="15" ezftoupper="" ezfname="apVndInvNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="invDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="invQty_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="15" ezftoupper="" ezfname="invQty_A1" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A2" class="pPro" readOnly></td>
												<td><input type="text" name="acInvJrnlCostAmt_A3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="12" maxlength="25" ezftoupper="" ezfname="acInvJrnlCostAmt_A3" class="pPro" readOnly></td>
												<td><input type="checkbox" name="wrtOffFlg_A1" value="Y" ezfname="wrtOffFlg_A1" class="pPro" readOnly></td>
												<td><input type="text" name="wrtOffDt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="10" maxlength="10" ezftoupper="" ezfname="wrtOffDt_A1" class="pPro" readOnly></td>
												<td>
													<select name="" ezfname="" ezflist=",,99, ,blank" style="width:111px;">
														<option>Qty Difference</option>
														<option>Wrong Account</option>
														<option>Invoice</option>
													</select>
												</td>
												<td><input type="text" name="acrlWrtOffCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" size="13" maxlength="4000" ezftoupper="" ezfname="acrlWrtOffCmntTxt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="xxCmntTxt_A1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" size="45" maxlength="100" ezftoupper="" ezfname="xxCmntTxt_A1" class="pPro" readOnly></td>
											</tr>
										</ezf:skip>
									</table>
								</div> <!-- rightTbl-->
								</div> <!-- right table -->
							</td>
						</tr>
					</table>
					</div> <!--parentBoxA-->
					<script type="text/javascript" defer>
                        S21TableUI.initialize("parentBoxA", "AHEAD", "A", 2, true);
                    </script>
				</div>
			</td>
		</tr>
	</table>
</center>

							

<%-- **** Task specific area ends here **** --%>
