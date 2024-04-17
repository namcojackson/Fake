<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170816105839 --%>
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
			<input type="hidden" name="pageID" value="NWAL1890Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Order Line Filter Popup">

<center>
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td align="center">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0" align="left">
								<tr>
									<td class="stab">Order Number</td>
									<td>&nbsp;</td>
									<td><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="20000000" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
								</tr>
							</table>
							<table border="0" cellpadding="0" cellspacing="0" align="right">
								<tr>
									<td class="stab">(s) : It's able to multiple selection.</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td align="center" width="1010">
<!-- ############################## START - Header Search Criteria -->
							<fieldset>
								<legend>&nbsp;<font size="2" color="black">Config Level Information</font>&nbsp;</legend>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign="top" width="1000">
											<table border="0" cellpadding="0" cellspacing="1">
												<col width="250">
												<col width="250">
												<col width="250">
												<col width="250">
												<tr>
													<!-- Config Number -->
													<td class="stab">Config Number</td>
													<td><ezf:inputText name="dsOrdPosnNum" ezfName="dsOrdPosnNum" value="1" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													<!-- Config ID -->
													<td class="stab"><ezf:anchor name="xxConfigIdSrchTxt_LK" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ConfigID" otherAttr=" id=\"xxConfigIdSrchTxt_LK\" ezfanchortext=\"\"">Config ID</ezf:anchor></td>
													<td><ezf:inputText name="xxConfigIdSrchTxt" ezfName="xxConfigIdSrchTxt" value="1000123" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<!-- Model -->
													<td class="stab"><ezf:anchor name="xxMdlSrchTxt_LK" ezfName="xxMdlSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Model" otherAttr=" id=\"xxMdlSrchTxt_LK\" ezfanchortext=\"\"">Model&nbsp;(s)</ezf:anchor></td>
													<td><ezf:inputText name="xxMdlSrchTxt" ezfName="xxMdlSrchTxt" value="IR123,IR555" otherAttr=" size=\"15\" maxlength=\"1000\""/></td>
												</tr>
												<tr>
													<!-- Bill To Cust Name -->
													<td class="stab"><ezf:anchor name="xxBillToAcctNmSrchTxt_LK" ezfName="xxBillToAcctNmSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToName" otherAttr=" id=\"xxBillToAcctNmSrchTxt_LK\" ezfanchortext=\"\"">Bill To Cust Name</ezf:anchor></td>
													<td colspan = "4"><ezf:inputText name="xxBillToAcctNmSrchTxt" ezfName="xxBillToAcctNmSrchTxt" value="ABC Company" otherAttr=" size=\"46\" maxlength=\"60\""/></td>
												</tr>
												<tr>
													<!-- Bill To Account Number -->
													<td class="stab"><ezf:anchor name="xxBillToAcctCdSrchTxt_LK" ezfName="xxBillToAcctCdSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToAcctNumber" otherAttr=" id=\"xxBillToAcctCdSrchTxt_LK\" ezfanchortext=\"\"">Bill To Account Number</ezf:anchor></td>
													<td><ezf:inputText name="xxBillToAcctCdSrchTxt" ezfName="xxBillToAcctCdSrchTxt" value="12345678" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
													<!-- Location# -->
													<td class="stab"><ezf:anchor name="xxBillToLocCdSrchTxt_LK" ezfName="xxBillToLocCdSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToLocNumber" otherAttr=" id=\"xxBillToLocCdSrchTxt_LK\" ezfanchortext=\"\"">Location#</ezf:anchor></td>
													<td><ezf:inputText name="xxBillToLocCdSrchTxt" ezfName="xxBillToLocCdSrchTxt" value="12345678" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<!-- Ship To Cust Name -->
													<td class="stab"><ezf:anchor name="xxShipToAcctNmSrchTxt_LK" ezfName="xxShipToAcctNmSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToName" otherAttr=" id=\"xxShipToAcctNmSrchTxt_LK\" ezfanchortext=\"\"">Ship To Cust Name</ezf:anchor></td>
													<td colspan = "4"><ezf:inputText name="xxShipToAcctNmSrchTxt" ezfName="xxShipToAcctNmSrchTxt" value="ABC Company" otherAttr=" size=\"46\" maxlength=\"60\""/></td>
												</tr>
												<tr>
													<!-- Ship To Account Number -->
													<td class="stab"><ezf:anchor name="xxShipToAcctCdSrchTxt_LK" ezfName="xxShipToAcctCdSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToAcctNumber" otherAttr=" id=\"xxShipToAcctCdSrchTxt_LK\" ezfanchortext=\"\"">Ship To Account Number</ezf:anchor></td>
													<td><ezf:inputText name="xxShipToAcctCdSrchTxt" ezfName="xxShipToAcctCdSrchTxt" value="12345678" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
													<!-- Location# -->
													<td class="stab"><ezf:anchor name="xxShipToLocCdSrchTxt_LK" ezfName="xxShipToLocCdSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToLocNumber" otherAttr=" id=\"xxShipToLocCdSrchTxt_LK\" ezfanchortext=\"\"">Location#</ezf:anchor></td>
													<td><ezf:inputText name="xxShipToLocCdSrchTxt" ezfName="xxShipToLocCdSrchTxt" value="12345678" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<!-- Sold To Cust Name -->
													<td class="stab"><ezf:anchor name="xxSoldToAcctNmSrchTxt_LK" ezfName="xxSoldToAcctNmSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldToName" otherAttr=" id=\"xxSoldToAcctNmSrchTxt_LK\" ezfanchortext=\"\"">Sold To Cust Name</ezf:anchor></td>
													<td colspan = "4"><ezf:inputText name="xxSoldToAcctNmSrchTxt" ezfName="xxSoldToAcctNmSrchTxt" value="ABC Company" otherAttr=" size=\"46\" maxlength=\"60\""/></td>
												</tr>
												<tr>
													<!-- Sold To Account Number -->
													<td class="stab"><ezf:anchor name="xxSoldToAcctCdSrchTxt_LK" ezfName="xxSoldToAcctCdSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldToAcctNumber" otherAttr=" id=\"xxSoldToAcctCdSrchTxt_LK\" ezfanchortext=\"\"">Sold To Account Number</ezf:anchor></td>
													<td><ezf:inputText name="xxSoldToAcctCdSrchTxt" ezfName="xxSoldToAcctCdSrchTxt" value="12345678" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
													<!-- Location# -->
													<td class="stab"><ezf:anchor name="xxSoldToLocCdSrchTxt_LK" ezfName="xxSoldToLocCdSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldToLocNumber" otherAttr=" id=\"xxSoldToLocCdSrchTxt_LK\" ezfanchortext=\"\"">Location#</ezf:anchor></td>
													<td><ezf:inputText name="xxSoldToLocCdSrchTxt" ezfName="xxSoldToLocCdSrchTxt" value="12345678" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">Show Include Line</td>
													<td><ezf:inputCheckBox name="xxShowInclLineFlg" ezfName="xxShowInclLineFlg" value="Y" otherAttr=" style=\"height:12; width:12;\""/></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" align="center">
<!-- ############################## START - Line Level Information -->
					<tr>
						<td colspan="1" valign="top">
							<fieldset style="height=260px">
								<legend>&nbsp;<font size="2" color="black">Line Level Information</font>&nbsp;</legend>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign="top">
											<table border="0" cellpadding="0" cellspacing="2">
												<col width="250">
												<col width="250">
												<tr>
													<!-- Line Number -->
													<td class="stab">Line Number</td>
													<td><ezf:inputText name="xxLineNum" ezfName="xxLineNum" value="1.1" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<!-- Line Status -->
													<td class="stab"><ezf:anchor name="xxLineStsSrchTxt_LK" ezfName="xxLineStsSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_LineStatus" otherAttr=" id=\"xxLineStsSrchTxt_LK\" ezfanchortext=\"\"">Line Status&nbsp;(s)</ezf:anchor></td>
													<td><ezf:inputText name="xxLineStsSrchTxt" ezfName="xxLineStsSrchTxt" value="BOOKED,SHIPED" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<!-- Ordered Item -->
													<td class="stab"><ezf:anchor name="xxOrdItemSrchTxt_LK" ezfName="xxOrdItemSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderedItem" otherAttr=" id=\"xxOrdItemSrchTxt_LK\" ezfanchortext=\"\"">Ordered Item&nbsp;(s)</ezf:anchor></td>
													<td><ezf:inputText name="xxOrdItemSrchTxt" ezfName="xxOrdItemSrchTxt" value="XXXXA001,XXXXA002" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<!-- Warehouse -->
													<td class="stab"><ezf:anchor name="xxRtlWhSrchTxt_LK" ezfName="xxRtlWhSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_WH" otherAttr=" id=\"xxRtlWhSrchTxt_LK\" ezfanchortext=\"\"">Warehouse</ezf:anchor></td>
													<td><ezf:inputText name="xxRtlWhSrchTxt" ezfName="xxRtlWhSrchTxt" value="MONROE" otherAttr=" size=\"15\" maxlength=\"1000\""/></td>
												</tr>
												<tr>
													<!-- Sub Warehouse -->
													<td class="stab"><ezf:anchor name="xxRtlSwhSrchTxt_LK" ezfName="xxRtlSwhSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWH" otherAttr=" id=\"xxRtlSwhSrchTxt_LK\" ezfanchortext=\"\"">Sub Warehouse&nbsp;(s)</ezf:anchor></td>
													<td><ezf:inputText name="xxRtlSwhSrchTxt" ezfName="xxRtlSwhSrchTxt" value="NEW,USED70" otherAttr=" size=\"15\" maxlength=\"1000\""/></td>
												</tr>
												<tr>
													<!-- Source Type -->
													<td class="stab"><ezf:anchor name="xxCpoSrcTpSrchTxt_LK" ezfName="xxCpoSrcTpSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SourceType" otherAttr=" id=\"xxCpoSrcTpSrchTxt_LK\" ezfanchortext=\"\"">Source Type</ezf:anchor></td>
													<td><ezf:inputText name="xxCpoSrcTpSrchTxt" ezfName="xxCpoSrcTpSrchTxt" value="CUSA DROP SHIP" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<!-- Line Source Ref -->
													<td class="stab"><ezf:anchor name="xxOrdSrcRefNumSrchTxt_LK" ezfName="xxOrdSrcRefNumSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_LineSourceRef" otherAttr=" id=\"xxOrdLineSrcSrchTxt_LK\" ezfanchortext=\"\"">Line Source Ref</ezf:anchor></td>
													<td><ezf:inputText name="xxOrdSrcRefNumSrchTxt" ezfName="xxOrdSrcRefNumSrchTxt" value="1.1" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<!-- Substitute Item -->
													<td class="stab"><ezf:anchor name="xxSbstItemSrchTxt_LK" ezfName="xxSbstItemSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SubstituteItem" otherAttr=" id=\"xxSbstItemSrchTxt_LK\" ezfanchortext=\"\"">Substitute Item</ezf:anchor></td>
													<td><ezf:inputText name="xxSbstItemSrchTxt" ezfName="xxSbstItemSrchTxt" value="XXXXA999" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<!-- Serial Number -->
													<td class="stab "><ezf:anchor name="xxSerNumSrchTxt_LK" ezfName="xxSerNumSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SerialNumber" otherAttr=" id=\"xxSerNumSrchTxt_LK\" ezfanchortext=\"\"">Serial#</ezf:anchor></td>
													<td><ezf:inputText name="xxSerNumSrchTxt" ezfName="xxSerNumSrchTxt" value="1234567890" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">Show Include Closed Lines</td>
													<td><ezf:inputCheckBox name="xxShowInclCloLineFlg" ezfName="xxShowInclCloLineFlg" value="Y" otherAttr=" style=\"height:12; width:12;\""/></td>
												</tr>
												<tr>
													<td class="stab">Show Include Cancelled Lines</td>
													<td><ezf:inputCheckBox name="xxShowInclCancLineFlg" ezfName="xxShowInclCancLineFlg" value="Y" otherAttr=" style=\"height:12; width:12;\""/></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
						<td colspan="1" valign="top">
							<fieldset style="height=260px">
								<legend>&nbsp;<font size="2" color="black">RMA Line Level Information</font>&nbsp;</legend>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign="top">
											<table border="0" cellpadding="0" cellspacing="2">
												<col width="250">
												<col width="250">
												<tr>
													<!-- Line Number -->
													<td class="stab">Line Number</td>
													<td><ezf:inputText name="xxLineNum_R" ezfName="xxLineNum_R" value="1.1" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<!-- Line Status -->
													<td class="stab"><ezf:anchor name="xxLineStsSrchTxt_RL" ezfName="xxLineStsSrchTxt_RL" ezfEmulateBtn="1" ezfGuard="OpenWin_LineStatus" otherAttr=" id=\"xxLineStsSrchTxt_RL\" ezfanchortext=\"\"">Line Status&nbsp;(s)</ezf:anchor></td>
													<td><ezf:inputText name="xxLineStsSrchTxt_R" ezfName="xxLineStsSrchTxt_R" value="BOOKED,SHIPED" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<!-- Ordered Item -->
													<td class="stab"><ezf:anchor name="xxOrdItemSrchTxt_RL" ezfName="xxOrdItemSrchTxt_RL" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderedItem" otherAttr=" id=\"xxOrdItemSrchTxt_RL\" ezfanchortext=\"\"">Ordered Item&nbsp;(s)</ezf:anchor></td>
													<td><ezf:inputText name="xxOrdItemSrchTxt_R" ezfName="xxOrdItemSrchTxt_R" value="XXXXA001,XXXXA002" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<!-- Retrurn Reason -->
													<td class="stab"><ezf:anchor name="xxRtrnRsnSrchTxt_RL" ezfName="xxRtrnRsnSrchTxt_RL" ezfEmulateBtn="1" ezfGuard="OpenWin_ReturnReason" otherAttr=" id=\"xxRtrnRsnSrchTxt_RL\" ezfanchortext=\"\"">Return Reason</ezf:anchor></td>
													<td><ezf:inputText name="xxRtrnRsnSrchTxt_R" ezfName="xxRtrnRsnSrchTxt_R" value="CFS Lease Return" otherAttr=" size=\"15\" maxlength=\"1000\""/></td>
												</tr>
												<tr>
													<!-- Warehouse -->
													<td class="stab"><ezf:anchor name="xxRtlWhSrchTxt_RL" ezfName="xxRtlWhSrchTxt_RL" ezfEmulateBtn="1" ezfGuard="OpenWin_WH" otherAttr=" id=\"xxRtlWhSrchTxt_RL\" ezfanchortext=\"\"">Warehouse</ezf:anchor></td>
													<td><ezf:inputText name="xxRtlWhSrchTxt_R" ezfName="xxRtlWhSrchTxt_R" value="MONROE" otherAttr=" size=\"15\" maxlength=\"1000\""/></td>
												</tr>
												<tr>
													<!-- Sub Warehouse -->
													<td class="stab"><ezf:anchor name="xxRtlSwhSrchTxt_RL" ezfName="xxRtlSwhSrchTxt_RL" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWH" otherAttr=" id=\"xxRtlSwhSrchTxt_RL\" ezfanchortext=\"\"">Sub Warehouse&nbsp;(s)</ezf:anchor></td>
													<td><ezf:inputText name="xxRtlSwhSrchTxt_R" ezfName="xxRtlSwhSrchTxt_R" value="NEW,USED70" otherAttr=" size=\"15\" maxlength=\"1000\""/></td>
												</tr>
												<tr>
													<!-- Line Source Ref -->
													<td class="stab"><ezf:anchor name="xxOrdSrcRefNumSrchTxt_RL" ezfName="xxOrdSrcRefNumSrchTxt_RL" ezfEmulateBtn="1" ezfGuard="OpenWin_LineSourceRef" otherAttr=" id=\"xxOrdSrcRefNumSrchTxt_RL\" ezfanchortext=\"\"">Line Source Ref</ezf:anchor></td>
													<td><ezf:inputText name="xxOrdSrcRefNumSrchTxt_R" ezfName="xxOrdSrcRefNumSrchTxt_R" value="1.1" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<!-- Serial Number -->
													<td class="stab "><ezf:anchor name="xxSerNumSrchTxt_RL" ezfName="xxSerNumSrchTxt_RL" ezfEmulateBtn="1" ezfGuard="OpenWin_SerialNumber" otherAttr=" id=\"xxSerNumSrchTxt_RL\" ezfanchortext=\"\"">Serial#</ezf:anchor></td>
													<td><ezf:inputText name="xxSerNumSrchTxt_R" ezfName="xxSerNumSrchTxt_R" value="1234567890" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>	
												</tr>
												<tr>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">Show Include Closed Lines</td>
													<td><ezf:inputCheckBox name="xxShowInclCloLineFlg_R" ezfName="xxShowInclCloLineFlg_R" value="Y" otherAttr=" style=\"height:12; width:12;\""/></td>
												</tr>
												<tr>
													<td class="stab">Show Include Cancelled Lines</td>
													<td><ezf:inputCheckBox name="xxShowInclCancLineFlg_R" ezfName="xxShowInclCancLineFlg_R" value="Y" otherAttr=" style=\"height:12; width:12;\""/></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
				</table>
<!-- ############################## START - Search Button -->
				<table border="0" cellpadding="0" cellspacing="0" height="20px" align="right">
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<!-- Search Button -->
						<td><ezf:inputButton name="Search" value="Search" htmlClass="cBtn" />&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
