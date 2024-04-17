<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180316160810 --%>
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
			<input type="hidden" name="pageID" value="NPAL1360Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Kitting Work Order Entry">
			
			
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		
<%-- ######################################## HEADER ######################################## --%>
			<%-- ###TAB - HEAD --%>
			<!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
			<ezf:skip>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="background-image: url(./img/tab/uppertabbackground.jpg);">
					<tr>
						<td height="28px" width="1070px" valign="bottom">
							<table class="pTab_UPPER_ON" border="0" cellpadding="0" cellspacing="0" >
								<tr title="WO Entry">
									<td align="center" class="same">WO Entry</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</ezf:skip>

			<%-- ###TAB - BODY --%>
			<div class="pTab_BODY">
				<table border="0" cellpadding="1" cellspacing="1" style="margin-top:5px; margin-left:5px;">
					<col width="400">
					<col width="340">
					<col width="340">
					<col width="18">

					<tr height="5px"><td></td></tr><%-- ===== upper space ===== --%>

					<td valign="top">
						<table border="0" cellpadding="0" cellspacing="0">
							<col width="100">
							<col width="180">
							<col width="90">
							<col width="30">
							<!-- Work Order Number -->
							<tr height="20px">
								<td class="stab">Work Order#</td>
								<td style="text-align: left;"><ezf:inputText name="wrkOrdNum" ezfName="wrkOrdNum" value="WO999999" otherAttr=" size=\"23\" maxlength=\"8\" ezftoupper=\"\""/></td>
								<!-- Search Button -->
								<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
								<td></td>
							</tr>
							<!-- Work Order Type -->
							<tr height="20px">
								<td class="stab">Work Order Type</td>
								<td style="text-align: left;">
									<ezf:select name="dsWrkOrdTpCd_SL" ezfName="dsWrkOrdTpCd_SL" ezfCodeName="dsWrkOrdTpCd_CD" ezfDispName="dsWrkOrdTpDescTxt_NM" otherAttr=" style=\"width: 170px\""/>
								</td>
								<td></td>
							</tr>
						</table>
					</td>
					<td valign="top">
						<table border="0" cellpadding="0" cellspacing="0">
							<col width="100">
							<col width="230">
							<col width="10">
							<!-- Work Order Status -->
							<tr height="20px">
								<td class="stab">Work Order Status</td>
								<td style="text-align: left;"><ezf:inputText name="dsWrkOrdStsNm" ezfName="dsWrkOrdStsNm" value="In-Process" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"-1\""/></td>
								<td></td>
							</tr>
							<!-- Work Order Date -->
							<tr height="20px">
								<td class="stab">Work Order Date</td>
								<td style="text-align: left;">
									<ezf:inputText name="wrkOrdDt" ezfName="wrkOrdDt" value="MM/DD/YYYY" otherAttr=" size=\"12\" maxlength=\"10\" tabindex=\"-1\""/>
								</td>
								<td></td>
							</tr>
						</table>
					</td>
					<td valign="top">
						<table border="0" cellpadding="0" cellspacing="0">
							<col width="60">
							<col width="140">
							<col width="140">
							<!-- Request Number -->
							<tr height="20px">
								<td class="stab">Request#</td>
								<td style="text-align: left;"><ezf:inputText name="prchReqNum" ezfName="prchReqNum" value="PR999999" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\" tabindex=\"-1\""/></td>
								<td></td>
							</tr>
						</table>
					</td>
				</table>

				<hr>

				<table border="0" cellpadding="1" cellspacing="1" style="margin-left:5px;">
					<col width="500">
					<col width="598">

					<td valign="top">
						<table border="0" cellpadding="0" cellspacing="0">
							<col width="100">
							<col width="100">
							<col width="40" align="center">
							<col width="230">
							<col width="30">
							<!-- Warehouse -->
							<tr height="20px">
								<td class="stab"><ezf:anchor name="xxLinkAncr_WH" ezfName="xxLinkAncr_WH" ezfEmulateBtn="1" ezfGuard="OpenWin_Wh" >Warehouse</ezf:anchor></td>
								<td><ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="----+----1" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/></td>
								<td><ezf:inputButton name="SetWhName" value=">>" htmlClass="pBtn0" /></td>
								<td><ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="----+----1----+----2----+----3" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\""/></td>
								<td></td>
							</tr>
							<!-- Completion Sub Warehouse -->
							<tr height="20px">
								<td class="stab"><ezf:anchor name="xxLinkAncr_SW" ezfName="xxLinkAncr_SW" ezfEmulateBtn="1" ezfGuard="OpenWin_CompletionSwh" >Completion SWH</ezf:anchor></td>
								<td><ezf:inputText name="cpltRtlSwhCd" ezfName="cpltRtlSwhCd" value="----+----1" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/></td>
								<td><ezf:inputButton name="SetSwhName" value=">>" htmlClass="pBtn0" /></td>
								<td><ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" value="----+----1----+----2----+----3" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\""/></td>
								<td></td>
							</tr>
						</table>
					</td>
					<td valign="top">
						<table border="0" cellpadding="0" cellspacing="0">
							<col width="100">
							<col width="370">
							<col width="128">
							<!-- Request Completion Date -->
							<tr height="20px">
								<td class="stab">Request Comp Date</td>
								<td style="text-align: left;">
									<ezf:inputText name="rqstRcvDt" ezfName="rqstRcvDt" value="MM/DD/YYYY" otherAttr=" size=\"12\" maxlength=\"10\""/>
									<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rqstRcvDt', 4 );">
								</td>
								<td></td>
							</tr>
						</table>
					</td>
				</table>

				<hr>

				<table border="0" cellpadding="1" cellspacing="1" style="margin-left:5px;">
					<col width="240">
					<col width="858">

					<td valign="top">
						<table border="0" cellpadding="0" cellspacing="0">
							<col width="100">
							<col width="100">
							<col width="36" align="center">

							<!-- Kit Item -->
							<tr height="20px">
								<td class="stab"><ezf:anchor name="xxLinkAncr_KT" ezfName="xxLinkAncr_KT" ezfEmulateBtn="1" ezfGuard="OpenWin_KitItem" >Kit Item</ezf:anchor></td>
								<td><ezf:inputText name="fnshGoodsMdseCd" ezfName="fnshGoodsMdseCd" value="----+----1--" otherAttr=" size=\"14\" maxlength=\"16\" ezftoupper=\"\""/></td>
								<td><ezf:inputButton name="SetKitItemName" value=">>" htmlClass="pBtn0" /></td>
							</tr>

							<!-- Order Qty -->
							<tr height="20px">
								<td class="stab">Order Qty</td>
								<td><ezf:inputText name="fnshGoodsOrdQty" ezfName="fnshGoodsOrdQty" value="1,234,567,890" otherAttr=" size=\"14\" maxlength=\"13\" style=\"text-align:right;\""/></td>
								<td></td>
							</tr>

						</table>
					</td>

					<td valign="top">
						<table border="0" cellpadding="0" cellspacing="0">
							<col width="150">
							<col width="14">

							<col width="100">
							<col width="116">
							<col width="20">
							<col width="22">

							<col width="80">
							<col width="90">
							<col width="10">
							<col width="90">
							<col width="20">

							<col width="30">
							<col width="60">
							<col width="60">

							<tr height="20px">

								<!-- Kit Item Name -->
								<td><ezf:inputText name="fnshMdseDescShortTxt" ezfName="fnshMdseDescShortTxt" value="----+----1----+----2" otherAttr=" size=\"20\" maxlength=\"250\" tabindex=\"-1\""/></td>
								<td></td>

								<!-- Serial# -->
								<td class="stab">Serial#</td>
								<td><ezf:inputText name="serNum" ezfName="serNum" value="----+----1--" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
								<td><ezf:inputButton name="OpenWin_KitSerial" value="S" htmlClass="pBtn0" /></td>
								<td></td>

								<!-- Effective Date -->
								<td class="stab">Effective Date</td>
								<td><ezf:inputText name="effFromDt" ezfName="effFromDt" value="MM/DD/YYYY" otherAttr=" size=\"12\" maxlength=\"10\" tabindex=\"-1\""/></td>
								<td><label ezout>-</label></td>
								<td><ezf:inputText name="effThruDt" ezfName="effThruDt" value="MM/DD/YYYY" otherAttr=" size=\"12\" maxlength=\"10\" tabindex=\"-1\""/></td>
								<td></td>

								<!-- UOM -->
								<td class="stab">UOM</td>
								<td><ezf:inputText name="basePkgUomCd" ezfName="basePkgUomCd" value="EA" otherAttr=" size=\"6\" maxlength=\"4\" tabindex=\"-1\""/></td>
								<td></td>

							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0">
							<col width="5">

							<col width="80">
							<col width="60">
							<col width="20">

							<col width="100">
							<col width="120">
							<col width="56">

							<col width="80">
							<col width="120">
							<col width="48">
							<col width="203" align="right">

							<tr height="20px">
								<td></td>

								<!-- Received Qty -->
								<td class="stab">Received Qty</td>
								<td><ezf:inputText name="fnshGoodsRcvQty" ezfName="fnshGoodsRcvQty" value="1,234,567,890" otherAttr=" size=\"8\" maxlength=\"13\" style=\"text-align:right;\" tabindex=\"-1\""/></td>
								<td></td>

								<!-- Balance Qty -->
								<td class="stab">Balance Qty</td>
								<td><ezf:inputText name="fnshGoodsBalQty" ezfName="fnshGoodsBalQty" value="1,234,567,890" otherAttr=" size=\"8\" maxlength=\"13\" style=\"text-align:right;\" tabindex=\"-1\""/></td>
								<td></td>

								<!-- Cancelled Qty -->
								<td class="stab">Cancelled Qty</td>
								<td><ezf:inputText name="fnshGoodsCancQty" ezfName="fnshGoodsCancQty" value="1,234,567,890" otherAttr=" size=\"8\" maxlength=\"13\" style=\"text-align:right;\" tabindex=\"-1\""/></td>
								<td></td>

								<!-- Component Button -->
								<td><ezf:inputButton name="Component" value="Component" htmlClass="pBtn7" /></td>
							</tr>
						</table>
					</td>
				</table>

				<hr>

				<table border="0" cellpadding="1" cellspacing="1" style="margin-left:5px;">
					<col width="95">
					<col width="905">
					<col width="98">

					<tr>
						<!-- Message -->
						<td class="stab">Message</td>
						<td><ezf:inputText name="wrkOrdMsgTxt" ezfName="wrkOrdMsgTxt" value="----+----1----+----2----+----3----+----4----+----5----+----6" otherAttr=" size=\"120\" maxlength=\"120\""/></td>
						<td><ezf:inputButton name="OpenWin_BOM_Comment" value="Comment/Text" htmlClass="pBtn7" /></td>
					</tr>
				</table>

	<%-- ######################################## DETAIL ######################################## --%>
				<table border="0" cellpadding="0" cellspacing="0" style="margin-top:5px; margin-left:5px;">
					<col>
					<tr valign="top">
						<td>
							<%-- ### A: Table HEADER--%>
							<div style="overflow-x:none; overflow-y:none;">
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
									<col width="40"  align="center">
									<col width="40"  align="center">
									<col width="124" align="center">
									<col width="216" align="center">
									<col width="100" align="center">
									<col width="146" align="center">
									<col width="60"  align="center">
									<col width="98" align="center">
									<col width="98" align="center">
									<col width="180"  align="center">
									<tr height="28">
										<td class="pClothBs"></td>
										<td class="pClothBs">Line#</td>
										<td class="pClothBs">Component Item</td>
										<td class="pClothBs">Item Description</td>
										<td class="pClothBs">Item Type</td>
										<td class="pClothBs">Supply SWH</td>
										<td class="pClothBs">UOM</td>
										<td class="pClothBs">Component Qty</td>
										<td class="pClothBs">Available Qty</td>
										<td class="pClothBs">Serial #</td>
									</tr>
								</table>
							</div>
							
							<%-- ### A: Table BODY--%>
							<div style="overflow-x:none; overflow-y:scroll; height:290;">
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
									<col width="40"  align="center">
									<col width="40"  align="center">
									<col width="124" align="left">
									<col width="216" align="center">
									<col width="100" align="left">
									<col width="44"  align="center">
									<col width="102" align="center">
									<col width="60"  align="left">
									<col width="98" align="right">
									<col width="98" align="right">
									<col width="140"  align="center">
									<col width="40"  align="center">
									
									<ezf:row ezfHyo="A">
										<tr id="id_row{EZF_ROW_NUMBER}" height="28">
											<!-- check box -->
											<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/></td>
											<!-- Line# -->
											<td><ezf:label name="wrkOrdDtlLineNum_A1" ezfName="wrkOrdDtlLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<!-- Component Item -->
											<td><ezf:label name="origGoodsMdseCd_A1" ezfName="origGoodsMdseCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<!-- Item Description -->
											<td><ezf:inputText name="origMdseDescShortTxt_A1" ezfName="origMdseDescShortTxt_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"250\" tabindex=\"-1\""/></td>
											<!-- Item Type -->
											<td><ezf:label name="mdseTpDescTxt_A1" ezfName="mdseTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<!-- Supply SWH -->
											<td><ezf:inputButton name="OpenWin_SupplySwh" value="SWH" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" otherAttr=" id=\"OpenWin_SupplySwh{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="splyRtlSwhCd_A1" ezfName="splyRtlSwhCd_A1" value="----+----1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
											<!-- UOM -->
											<td><ezf:label name="basePkgUomCd_D1" ezfName="basePkgUomCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
											<!-- Component Qty -->
											<td><ezf:label name="origGoodsOrdQty_A1" ezfName="origGoodsOrdQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<!-- Available Qty -->
											<td><ezf:label name="invtyAvalQty_A1" ezfName="invtyAvalQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<!-- Serial # -->
											<td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="----+----1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/></td>
											<td><ezf:inputButton name="OpenWin_SupplySerial" value="S" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SupplySerial{EZF_ROW_NUMBER}\""/></td>
										</tr>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<div align="left">
					<table border="0">
						<tr height="5">
							<td></td>
						</tr>
						<tr height="20">
							<td valign="bottom">
								<table border="0" cellpadding="1" cellspacing="0" width="100%">
									<col width="10">
									<col width="40">
									<col width="10">
									<col width="40">
									<col width="10">
									<col width="40">
									<col width="10">
									<col width="40">
									<col width="898">
									<tr>
										<td></td>
										<td>
											<ezf:inputButton name="Cancel" value="Cancel" htmlClass="pBtn7" />
										</td>
										<td></td>
										<td>
											<ezf:inputButton name="Print" value="Print" htmlClass="pBtn7" />
										</td>
										<td></td>
										<td>
											<ezf:inputButton name="Attachments" value="Attachments" htmlClass="pBtn7" />
										</td>
										<td></td>
										<td>
											<ezf:inputButton name="OpenWin_SupplyDemand" value="Supply / Demand" htmlClass="pBtn8" />
										</td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>
<!-- ######################################## FOOTER ######################################## -->
		</td>
	</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
