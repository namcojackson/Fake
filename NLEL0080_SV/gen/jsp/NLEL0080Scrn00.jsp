<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180724093557 --%>
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
			<input type="hidden" name="pageID" value="NLEL0080Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="DS Asset Manual Entry">
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
													<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Asset Entry</a></li>
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
							<!-- include S21BusinessProcessTAB -->
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
							<div class="pTab_BODY">
								<table border="0" cellpadding="0" cellspacing="0" style="margin-left:2px;">
									<col width="100px"  align="left">
									<col width="160px"  align="left">
									<col width="100px"  align="left">
									<col width="584px"  align="left">
									<col width="70px"  align="right">
									<col width="5px">
									<col width="70px"  align="right">
									<col width="10px">
									<tr height="24px">
										<td>&nbsp;&nbsp;Book Type</td>
										<td>
											<ezf:select name="assetTpNm_S" ezfName="assetTpNm_S" ezfCodeName="assetTpNm_C" ezfDispName="assetTpDescTxt_D" otherAttr=" style=\"width:160px;\""/>
										</td>
										<td>&nbsp;&nbsp;Asset Type</td>
										<td>
											<ezf:select name="assetSrcTpCd_S" ezfName="assetSrcTpCd_S" ezfCodeName="assetSrcTpCd_C" ezfDispName="assetSrcTpDescTxt_D" otherEvent1=" onchange=\"sendServer('OnChangeAssetType')\"" otherAttr=" style=\"width:100px;\""/>
										</td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr height="24px">
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" /></td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn6" /></td>
										<td></td>
									</tr>
								</table>

                                <table style="MARGIN-LEFT: 0px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td align="left" valign="top">
                                            <%-- ******************************* --%>
                                            <%-- *** Left Table Area(Header) *** --%>
                                            <%-- ******************************* --%>
                                            <table style="table-layout:fixed; margin-left: 5px;" border="1" cellpadding="1" cellspacing="0">
                                                <col width="24"  align="center">
                                                <col width="120" align="center">
                                                <col width="100" align="center">
                                                <col width="45"  align="center">
                                                <col width="45"  align="center">
                                                <col width="150" align="center">
                                                <col width="100" align="center">
                                                <tr height="35">
                                                    <td class="pClothBs">&nbsp;</td>
                                                    <td class="pClothBs">Asset Number</td>
                                                    <td class="pClothBs">Description</td>
                                                    <td class="pClothBs">Unit</td>
                                                    <td class="pClothBs">Life In Month</td>
                                                    <td class="pClothBs">Serial Num</td>
                                                    <td class="pClothBs">Asset Value</td>
                                                </tr>
                                            </table>
                                            <%-- ******************************* --%>
                                            <%-- *** Left Table Area(Detail) *** --%>
                                            <%-- ******************************* --%>
                                            <div id="LeftTBL_A" style="overflow-y:hidden; height:452; overflow-x:hidden; width:; margin-left: 5px;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBL_A' ) );">
                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A1">
                                                    <col width="24"  align="center">
                                                    <col width="120" align="center">
                                                    <col width="100" align="center">
                                                    <col width="45"  align="center">
                                                    <col width="45"  align="center">
                                                    <col width="150" align="center">
                                                    <col width="100" align="center">
                                                    <ezf:row ezfHyo="A">
                                                        <tr height="25">
                                                            <td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox#{EZF_ROW_NUMBER}\""/></td>
                                                            <td><ezf:inputText name="dsAssetMstrPk" ezfName="dsAssetMstrPk" value="XXXXXXXXX1XXXXXXXXX2XXXXXXX8" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"28\" id=\"dsAssetMstrPk#{EZF_ROW_NUMBER}\" style=\"border:none; background-color:transparent;\""/></td>
                                                            <td><ezf:inputText name="dsAssetDescTxt" ezfName="dsAssetDescTxt" value="0--------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" id=\"dsAssetDescTxt#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="totAssetQty" ezfName="totAssetQty" value="12345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"5\" id=\"totAssetQty#{EZF_ROW_NUMBER}\""/></td>
                                                            <td><ezf:inputText name="depcMthNum" ezfName="depcMthNum" value="123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" id=\"depcMthNum#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="serNum" ezfName="serNum" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" id=\"serNum#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="curValAmt" ezfName="curValAmt" value="XXXXXXXXX1XXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"19\" id=\"curValAmt#{EZF_ROW_NUMBER}\""/></td>
                                                        </tr>
                                                    </ezf:row>
                                                    <ezf:skip>
                                                        <tr height="25">
                                                            <td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
                                                            <td><input type="text" size="28" maxlength="28" value="123456789012345678" name="dsAssetMstrPk" ezfname="dsAssetMstrPk" id="dsAssetMstrPk#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="25" maxlength="50" value="" name="dsAssetDescTxt" ezfname="dsAssetDescTxt" id="dsAssetDescTxt#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="5" maxlength="5" value="12345" name="totAssetQty" ezfname="totAssetQty" id="totAssetQty#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="3" maxlength="3" value="123" name="depcMthNum" ezfname="depcMthNum" id="depcMthNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="20" maxlength="30" value="" name="serNum" ezfname="serNum"  id="serNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="13" maxlength="19" value="XXXXXXXXX1XXXXXXXXX" name="curValAmt" ezfname="curValAmt" id="curValAmt#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                        </tr>
                                                        <tr height="25">
                                                            <td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
                                                            <td><input type="text" size="28" maxlength="28" value="123456789012345678" name="dsAssetMstrPk" ezfname="dsAssetMstrPk" id="dsAssetMstrPk#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="25" maxlength="50" value="" name="dsAssetDescTxt" ezfname="dsAssetDescTxt" id="dsAssetDescTxt#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="5" maxlength="5" value="12345" name="totAssetQty" ezfname="totAssetQty" id="totAssetQty#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="3" maxlength="3" value="123" name="depcMthNum" ezfname="depcMthNum" id="depcMthNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="20" maxlength="30" value="" name="serNum" ezfname="serNum"  id="serNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="13" maxlength="19" value="XXXXXXXXX1XXXXXXXXX" name="curValAmt" ezfname="curValAmt" id="curValAmt#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                        </tr>
                                                        <tr height="25">
                                                            <td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
                                                            <td><input type="text" size="28" maxlength="28" value="123456789012345678" name="dsAssetMstrPk" ezfname="dsAssetMstrPk" id="dsAssetMstrPk#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="25" maxlength="50" value="" name="dsAssetDescTxt" ezfname="dsAssetDescTxt" id="dsAssetDescTxt#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="5" maxlength="5" value="12345" name="totAssetQty" ezfname="totAssetQty" id="totAssetQty#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="3" maxlength="3" value="123" name="depcMthNum" ezfname="depcMthNum" id="depcMthNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="20" maxlength="30" value="" name="serNum" ezfname="serNum"  id="serNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="13" maxlength="19" value="XXXXXXXXX1XXXXXXXXX" name="curValAmt" ezfname="curValAmt" id="curValAmt#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                        </tr>
                                                    </ezf:skip>
                                                </table>
                                            </div>
                                        </td>
                                        <td valign="top">
                                            <%-- ******************************** --%>
                                            <%-- *** Right Table Area(Header) *** --%>
                                            <%-- ******************************** --%>
                                            <div id="topRightTBL_B" style="overflow-y:none; overflow-x:hidden; width:522;">
                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
                                                    <col width="130" align="center">
                                                    <col width="215" align="center">
                                                    <col width="100" align="center">
                                                    <col width="70"  align="center">
                                                    <col width="70"  align="center">
                                                    <col width="100"  align="center">
                                                    <col width="120" align="center">
                                                    <col width="100" align="center">
                                                    <col width="100" align="center">
                                                    <col width="100" align="center">
                                                    <col width="80" align="center">
                                                    <col width="100" align="center">
                                                    <col width="100" align="center">
                                                    <col width="180" align="center">
                                                    <col width="180" align="center">
                                                    <col width="300" align="center">
                                                    <col width="120" align="center">
                                                    <col width="35"  align="center">
                                                    <col width="100" align="center">
                                                    <tr height="35">
                                                        <td class="pClothBs">Parent</td>
                                                        <td class="pClothBs">Expense Acct</td>
                                                        <td class="pClothBs">Sales Rep</td>
                                                        <td class="pClothBs">Order Num</td>
                                                        <td class="pClothBs">Order Line</td>
                                                        <td class="pClothBs">Billing Num</td>
                                                        <td class="pClothBs">Contract Num</td>
                                                        <td class="pClothBs">Contract Start</td>
                                                        <td class="pClothBs">Contract End</td>
                                                        <td class="pClothBs">Last Bill</td>
                                                        <td class="pClothBs">PO Num</td>
                                                        <td class="pClothBs">Invoice Num</td>
                                                        <td class="pClothBs">Invoice Dt</td>
                                                        <td class="pClothBs">Customer Code</td>
                                                        <td class="pClothBs">Vendor Name</td>
                                                        <td class="pClothBs">Address Line</td>
                                                        <td class="pClothBs">City</td>
                                                        <td class="pClothBs">State</td>
                                                        <td class="pClothBs">Zip Code</td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <%-- ******************************** --%>
                                            <%-- *** Right Table Area(Detail) *** --%>
                                            <%-- ******************************** --%>
                                            <div id="RightTBL_A" style="overflow-y:scroll; height:469; overflow-x:scroll; width:539;" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL_A' ) ); synchroScrollLeft( this.id, new Array( 'topRightTBL_B' ) );">
                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A2">
                                                    <col width="130" align="center">
                                                    <col width="215" align="left">
                                                    <col width="100" align="left">
                                                    <col width="70"  align="center">
                                                    <col width="70"  align="center">
                                                    <col width="100"  align="center">
                                                    <col width="120" align="center">
                                                    <col width="100" align="left">
                                                    <col width="100" align="left">
                                                    <col width="100" align="center">
                                                    <col width="80" align="center">
                                                    <col width="100" align="center">
                                                    <col width="100" align="center">
                                                    <col width="180" align="left">
                                                    <col width="180" align="center">
                                                    <col width="300" align="center">
                                                    <col width="120" align="center">
                                                    <col width="35"  align="center">
                                                    <col width="100" align="center">
                                                    <ezf:row ezfHyo="A">
                                                        <tr height="25">
                                                            <td align="left"><ezf:inputText name="prntDsAssetMstrPk" ezfName="prntDsAssetMstrPk" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"28\" id=\"prntDsAssetMstrPk#{EZF_ROW_NUMBER}\""/></td>
                                                            <td><ezf:inputText name="xxScrItem81Txt" ezfName="xxScrItem81Txt" value="---------1---------212345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"81\" id=\"xxScrItem81Txt#{EZF_ROW_NUMBER}\""/><ezf:inputButton name="OpenWin_GL" value="GL" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_GL#{EZF_ROW_NUMBER}\""/></td>
                                                            <td><ezf:inputText name="slsRepTocCd" ezfName="slsRepTocCd" value="12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" id=\"slsRepTocCd#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_SR" value="SR" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SR#{EZF_ROW_NUMBER}\""/></td>
                                                            <td><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" id=\"cpoOrdNum#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="cpoDplyLineNum" ezfName="cpoDplyLineNum" value="123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"16\" id=\"cpoDtlLineNum#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="bllgInvNum" ezfName="bllgInvNum" value="---------1--3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" id=\"bllgInvNum#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" id=\"dsContrNum#{EZF_ROW_NUMBER}\""/></td>
                                                            <td><ezf:inputText name="contrEffFromDt" ezfName="contrEffFromDt" value="01/01/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"contrEffFromDt#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('contrEffFromDt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><ezf:inputText name="contrEffThruDt" ezfName="contrEffThruDt" value="01/01/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"contrEffThruDt#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('contrEffThruDt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><ezf:inputText name="lastBillDt" ezfName="lastBillDt" value="01/01/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"lastBillDt#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('lastBillDt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><ezf:inputText name="custIssPoNum" ezfName="custIssPoNum" value="12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"35\" id=\"custIssPoNum#{EZF_ROW_NUMBER}\""/></td>
                                                            <td><ezf:inputText name="invNum" ezfName="invNum" value="---------1--3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" id=\"invNum#{EZF_ROW_NUMBER}\""/></td>
                                                            <td><ezf:inputText name="invDt" ezfName="invDt" value="01/01/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"invDt#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('invDt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><ezf:inputText name="sellToCustCd" ezfName="sellToCustCd" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"20\" id=\"sellToCustCd#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Customer" value="C" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Customer#{EZF_ROW_NUMBER}\""/></td>
                                                            <td><ezf:inputText name="vndTpDescTxt" ezfName="vndTpDescTxt" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" id=\"vndTpDescTxt#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_V" value="V" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_V#{EZF_ROW_NUMBER}\""/></td>
                                                            <td><ezf:inputText name="firstLineAddr" ezfName="firstLineAddr" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"41\" maxlength=\"60\" id=\"firstLineAddr#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="ctyAddr" ezfName="ctyAddr" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"25\" id=\"ctyAddr#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="stCd" ezfName="stCd" value="12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\" id=\"stCd#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="postCd" ezfName="postCd" value="123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"15\" id=\"postCd#{EZF_ROW_NUMBER}\""/></td>
                                                        </tr>
                                                    </ezf:row>
                                                    <ezf:skip>
                                                        <tr height="25">
                                                            <td align="left"><input type="text" size="17" maxlength="28" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" name="prntDsAssetMstrPk" id="prntDsAssetMstrPk#{EZF_ROW_NUMBER}" ezfname="prntDsAssetMstrPk" ezfhyo="A"></td>
                                                            <td><input type="text" size="25" maxlength="81" value="---------1---------212345" name="xxScrItem81Txt" id="xxScrItem81Txt#{EZF_ROW_NUMBER}" ezfname="xxScrItem81Txt" ezfhyo="A"><input type="button" class="pBtn0" value="GL" name="OpenWin_GL" id="OpenWin_GL#{EZF_ROW_NUMBER}" onclick="sendServer('OpenWin_GL', '{EZF_ROW_NUMBER}')" ezfhyo="A"></td>
                                                            <td><input type="text" size="8" maxlength="8" value="12345678" name="slsRepTocCd" ezfname="slsRepTocCd"  id="slsRepTocCd#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"><input type="button" class="pBtn0" value="SR" name="OpenWin_SR" id="OpenWin_SR#{EZF_ROW_NUMBER}" onclick="sendServer('OpenWin_SR', '{EZF_ROW_NUMBER}')" ezfhyo="A"></td>
                                                            <td><input type="text" size="8" maxlength="8" value="12345678" name="cpoOrdNum" ezfname="cpoOrdNum" id="cpoOrdNum#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="8" maxlength="16" value="123" name="cpoDplyLineNum" ezfname="cpoDplyLineNum" id="cpoDtlLineNum#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="13" maxlength="8" value="12345678" name="bllgInvNum" ezfname="bllgInvNum" id="bllgInvNum#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="15" maxlength="30" value="123456789012345" name="dsContrNum" ezfname="dsContrNum" id="dsContrNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="10" maxlength="10" value="01/01/2016" name="contrEffFromDt" ezfname="contrEffFromDt" id="contrEffFromDt#{EZF_ROW_NUMBER}" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('contrEffFromDt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><input type="text" size="10" maxlength="10" value="01/01/2016" name="contrEffThruDt" ezfname="contrEffThruDt" id="contrEffThruDt#{EZF_ROW_NUMBER}" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('contrEffThruDt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><input type="text" size="10" maxlength="10" value="01/01/2016" name="lastBillDt" ezfname="lastBillDt" id="lastBillDt#{EZF_ROW_NUMBER}" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxDt10Dt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><input type="text" size="10" maxlength="35" value="1234567890" name="custIssPoNum" ezfname="custIssPoNum" id="custIssPoNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="13" maxlength="10" value="1234567890" name="invNum" ezfname="invNum" id="invNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="10" maxlength="10" value="01/01/2016" name="invDt" ezfname="invDt" id="invDt#{EZF_ROW_NUMBER}" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('invDt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><input type="text" size="20" maxlength="20" value="" name="shipToCustCd" ezfname="shipToCustCd" id="shipToCustCd#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"><input type="button" class="pBtn0" value="C" name="OpenWin_Customer"  id="OpenWin_Customer#{EZF_ROW_NUMBER}" onclick="sendServer('OpenWin_Customer', '{EZF_ROW_NUMBER}')" ezfhyo="A"></td>
                                                            <td><input type="text" size="20" maxlength="20" value="" name="vndTpDescTxt" ezfname="vndTpDescTxt" id="vndTpDescTxt#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"><input type="button" class="pBtn0" value="V" name="OpenWin_V"  id="OpenWin_V#{EZF_ROW_NUMBER}" onclick="sendServer('OpenWin_V', '{EZF_ROW_NUMBER}')" ezfhyo="A"></td>
                                                            <td><input type="text" size="41" maxlength="60" value="" name="firstLineAddr" ezfname="firstLineAddr" id="firstLineAddr#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="15" maxlength="25" value="" name="ctyAddr" ezfname="ctyAddr" id="ctyAddr#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="2" maxlength="2" value="12" name="stCd" ezfname="stCd" id="stCd#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="13" maxlength="15" value="123456789012345" name="postCd" ezfname="postCd" id="postCd#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                        </tr>
                                                        <tr height="25">
                                                            <td align="left"><input type="text" size="17" maxlength="28" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" name="prntDsAssetMstrPk" id="prntDsAssetMstrPk#{EZF_ROW_NUMBER}" ezfname="prntDsAssetMstrPk" ezfhyo="A"></td>
                                                            <td><input type="text" size="25" maxlength="81" value="---------1---------212345" name="xxScrItem81Txt" id="xxScrItem81Txt#{EZF_ROW_NUMBER}" ezfname="xxScrItem81Txt" ezfhyo="A"><input type="button" class="pBtn0" value="GL" name="OpenWin_GL" id="OpenWin_GL#{EZF_ROW_NUMBER}" onclick="sendServer('OpenWin_GL', '{EZF_ROW_NUMBER}')" ezfhyo="A"></td>
                                                            <td><input type="text" size="8" maxlength="8" value="12345678" name="slsRepTocCd" ezfname="slsRepTocCd"  id="slsRepTocCd#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"><input type="button" class="pBtn0" value="SR" name="OpenWin_SR" id="OpenWin_SR#{EZF_ROW_NUMBER}" onclick="sendServer('OpenWin_SR', '{EZF_ROW_NUMBER}')" ezfhyo="A"></td>
                                                            <td><input type="text" size="8" maxlength="8" value="12345678" name="cpoOrdNum" ezfname="cpoOrdNum" id="cpoOrdNum#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="8" maxlength="16" value="123" name="cpoDplyLineNum" ezfname="cpoDplyLineNum" id="cpoDtlLineNum#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="13" maxlength="8" value="12345678" name="bllgInvNum" ezfname="bllgInvNum" id="bllgInvNum#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="15" maxlength="30" value="123456789012345" name="dsContrNum" ezfname="dsContrNum" id="dsContrNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="10" maxlength="10" value="01/01/2016" name="contrEffFromDt" ezfname="contrEffFromDt" id="contrEffFromDt#{EZF_ROW_NUMBER}" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('contrEffFromDt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><input type="text" size="10" maxlength="10" value="01/01/2016" name="contrEffThruDt" ezfname="contrEffThruDt" id="contrEffThruDt#{EZF_ROW_NUMBER}" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('contrEffThruDt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><input type="text" size="10" maxlength="10" value="01/01/2016" name="lastBillDt" ezfname="lastBillDt" id="lastBillDt#{EZF_ROW_NUMBER}" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxDt10Dt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><input type="text" size="10" maxlength="35" value="1234567890" name="custIssPoNum" ezfname="custIssPoNum" id="custIssPoNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="13" maxlength="10" value="1234567890" name="invNum" ezfname="invNum" id="invNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="10" maxlength="10" value="01/01/2016" name="invDt" ezfname="invDt" id="invDt#{EZF_ROW_NUMBER}" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('invDt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><input type="text" size="20" maxlength="20" value="" name="shipToCustCd" ezfname="shipToCustCd" id="shipToCustCd#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"><input type="button" class="pBtn0" value="C" name="OpenWin_Customer"  id="OpenWin_Customer#{EZF_ROW_NUMBER}" onclick="sendServer('OpenWin_Customer', '{EZF_ROW_NUMBER}')" ezfhyo="A"></td>
                                                            <td><input type="text" size="20" maxlength="20" value="" name="vndTpDescTxt" ezfname="vndTpDescTxt" id="vndTpDescTxt#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"><input type="button" class="pBtn0" value="V" name="OpenWin_V"  id="OpenWin_V#{EZF_ROW_NUMBER}" onclick="sendServer('OpenWin_V', '{EZF_ROW_NUMBER}')" ezfhyo="A"></td>
                                                            <td><input type="text" size="41" maxlength="60" value="" name="firstLineAddr" ezfname="firstLineAddr" id="firstLineAddr#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="15" maxlength="25" value="" name="ctyAddr" ezfname="ctyAddr" id="ctyAddr#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="2" maxlength="2" value="12" name="stCd" ezfname="stCd" id="stCd#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="13" maxlength="15" value="123456789012345" name="postCd" ezfname="postCd" id="postCd#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                        </tr>
                                                        <tr height="25">
                                                            <td align="left"><input type="text" size="17" maxlength="28" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXX" name="prntDsAssetMstrPk" id="prntDsAssetMstrPk#{EZF_ROW_NUMBER}" ezfname="prntDsAssetMstrPk" ezfhyo="A"></td>
                                                            <td><input type="text" size="25" maxlength="81" value="---------1---------212345" name="xxScrItem81Txt" id="xxScrItem81Txt#{EZF_ROW_NUMBER}" ezfname="xxScrItem81Txt" ezfhyo="A"><input type="button" class="pBtn0" value="GL" name="OpenWin_GL" id="OpenWin_GL#{EZF_ROW_NUMBER}" onclick="sendServer('OpenWin_GL', '{EZF_ROW_NUMBER}')" ezfhyo="A"></td>
                                                            <td><input type="text" size="8" maxlength="8" value="12345678" name="slsRepTocCd" ezfname="slsRepTocCd"  id="slsRepTocCd#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"><input type="button" class="pBtn0" value="SR" name="OpenWin_SR" id="OpenWin_SR#{EZF_ROW_NUMBER}" onclick="sendServer('OpenWin_SR', '{EZF_ROW_NUMBER}')" ezfhyo="A"></td>
                                                            <td><input type="text" size="8" maxlength="8" value="12345678" name="cpoOrdNum" ezfname="cpoOrdNum" id="cpoOrdNum#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="8" maxlength="16" value="123" name="cpoDplyLineNum" ezfname="cpoDplyLineNum" id="cpoDtlLineNum#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="13" maxlength="8" value="12345678" name="bllgInvNum" ezfname="bllgInvNum" id="bllgInvNum#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="15" maxlength="30" value="123456789012345" name="dsContrNum" ezfname="dsContrNum" id="dsContrNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="10" maxlength="10" value="01/01/2016" name="contrEffFromDt" ezfname="contrEffFromDt" id="contrEffFromDt#{EZF_ROW_NUMBER}" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('contrEffFromDt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><input type="text" size="10" maxlength="10" value="01/01/2016" name="contrEffThruDt" ezfname="contrEffThruDt" id="contrEffThruDt#{EZF_ROW_NUMBER}" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('contrEffThruDt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><input type="text" size="10" maxlength="10" value="01/01/2016" name="lastBillDt" ezfname="lastBillDt" id="lastBillDt#{EZF_ROW_NUMBER}" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxDt10Dt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><input type="text" size="10" maxlength="35" value="1234567890" name="custIssPoNum" ezfname="custIssPoNum" id="custIssPoNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="13" maxlength="10" value="1234567890" name="invNum" ezfname="invNum" id="invNum#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="10" maxlength="10" value="01/01/2016" name="invDt" ezfname="invDt" id="invDt#{EZF_ROW_NUMBER}" ezfhyo="A"><img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('invDt', 4, '{EZF_ROW_NUMBER}');"></td>
                                                            <td><input type="text" size="20" maxlength="20" value="" name="shipToCustCd" ezfname="shipToCustCd" id="shipToCustCd#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"><input type="button" class="pBtn0" value="C" name="OpenWin_Customer"  id="OpenWin_Customer#{EZF_ROW_NUMBER}" onclick="sendServer('OpenWin_Customer', '{EZF_ROW_NUMBER}')" ezfhyo="A"></td>
                                                            <td><input type="text" size="20" maxlength="20" value="" name="vndTpDescTxt" ezfname="vndTpDescTxt" id="vndTpDescTxt#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"><input type="button" class="pBtn0" value="V" name="OpenWin_V"  id="OpenWin_V#{EZF_ROW_NUMBER}" onclick="sendServer('OpenWin_V', '{EZF_ROW_NUMBER}')" ezfhyo="A"></td>
                                                            <td><input type="text" size="41" maxlength="60" value="" name="firstLineAddr" ezfname="firstLineAddr" id="firstLineAddr#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="15" maxlength="25" value="" name="ctyAddr" ezfname="ctyAddr" id="ctyAddr#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="2" maxlength="2" value="12" name="stCd" ezfname="stCd" id="stCd#{EZF_ROW_NUMBER}" ezfToUpper ezfhyo="A"></td>
                                                            <td><input type="text" size="13" maxlength="15" value="123456789012345" name="postCd" ezfname="postCd" id="postCd#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                        </tr>

                                                    </ezf:skip>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
