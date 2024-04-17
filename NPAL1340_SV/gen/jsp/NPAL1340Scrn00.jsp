<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230413105645 --%>
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
			<input type="hidden" name="pageID" value="NPAL1340Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Drop Ship Release">

<center>
	<table cellSpacing="0" cellPadding="0" width="100%" border="0">
		<tbody>
			<tr>
				<td>
					<%-- ********************** --%>
					<%-- *** Upper Tab Area *** --%>
					<%-- ********************** --%>
					<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
					<ezf:skip>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
							<tr>
								<td width="1070px" height="28px" valign="bottom">
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
										<tr title="Drop Ship Release">
											<td width="107px" align="center" class="same">DS Release</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</ezf:skip>

					<div class="pTab_BODY">
						<!-- ################################################## Header - [START] ################################################## -->
						<table style="width: 100%; MARGIN-LEFT: 5px;margin-bottom: 5px; border-bottom: ridge 1px;">
							<col width=""  align="left">
							<tr valign="top">
								<td>
									<table border="0" cellpadding="0" cellspacing="0" style="margin-left: 5px;">
										<col width="115">
                                        <col width="195" align="left">
										<col width="110">
										<col width="192">
										<col width="110">
										<col width="200">
										<col width="168" align="right">
										<!-- 1 -->
										<tr height="20">
											<td class="stab">PO Number</td>
											<td><ezf:inputText name="poOrdNum_H1" ezfName="poOrdNum_H1" value="VP000001" otherAttr=" size=\"12\" maxlength=\"8\" ezftoupper=\"\""/></td>
											<td class="stab">Order Number</td>
											<td><ezf:inputText name="cpoOrdNum_H1" ezfName="cpoOrdNum_H1" value="CPB56038" otherAttr=" size=\"12\" maxlength=\"8\" ezftoupper=\"\""/></td>
											<td class="stab">Only Open PO</td>
											<td><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" /></td>
										</tr>
										<tr height="20">
											<td class="stab">Supplier Name (*)</td>
											<td><ezf:inputText name="prntVndNm_H1" ezfName="prntVndNm_H1" value="CANON USA%" otherAttr=" size=\"20\" maxlength=\"240\" ezftoupper=\"\""/></td>
											<td class="stab">Supplier Site Name (*)</td>
											<td><ezf:inputText name="vndNm_H1" ezfName="vndNm_H1" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
											<td class="stab">Customer Name (*)</td>
											<td><ezf:inputText name="shipToAcctNm_H1" ezfName="shipToAcctNm_H1" value="HP ENTERPRISES%" otherAttr=" size=\"25\" maxlength=\"360\" ezftoupper=\"\""/></td>
											<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<!-- ################################################## Header - [E N D] ################################################## -->
                        <!-- ######################################## Add - [S T A R T] #################################### -->
						<table style="width: 100%; MARGIN-LEFT: 5px;margin-bottom: 5px; border-bottom: ridge 1px;">
                            <col width=""  align="left">
                            <tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" style="margin-left: 5px;">
										<col width="115">
                                        <col width="195" align="left">
										<col width="110">
                                        <col width="192" align="left">
                                        <col width="110" align="left">
                                        <col width="50" align="left">
                                        <col width="60" align="left">
                                        <col width="100" align="left">
                                        <!-- 1 -->
                                        <tr height="21">
											<td class="stab">PO Number</td>
                                            <td>
																								<ezf:select name="poOrdNum_H2" ezfName="poOrdNum_H2" ezfCodeName="poOrdNum_LC" ezfDispName="poOrdNum_LD" otherEvent1=" onchange=\"sendServer('OnChange_PONum')\"" otherAttr=" style=\"width:145px;\" tabindex=\"-1\""/>
                                            </td>
											<td class="stab">Order Number</td>
											<td><ezf:inputText name="cpoOrdNum_H2" ezfName="cpoOrdNum_H2" value="CPB56038" otherAttr=" size=\"12\" maxlength=\"8\" ezftoupper=\"\""/>
												<ezf:inputButton name="OpenWin_OrdEntry" value="Ord Entry" htmlClass="pBtn5" /></td>
											<td class="stab">Supplier / Site</td>
											<td colspan="3"><ezf:inputText name="prntVndNm_H2" ezfName="prntVndNm_H2" value="CANON USA" otherAttr=" size=\"25\" maxlength=\"8\" ezftoupper=\"\""/>
												<ezf:inputText name="vndNm_H2" ezfName="vndNm_H2" value="Supply" otherAttr=" size=\"25\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                        </tr>
                                        <tr height="21">
											<td class="stab">Invoice Number</td>
											<td><ezf:inputText name="vndInvNum_H2" ezfName="vndInvNum_H2" value="50123456" otherAttr=" size=\"20\" maxlength=\"13\" ezftoupper=\"\""/></td>
											<td class="stab">Invoice Date</td>
											<td><ezf:inputText name="invDt_H2" ezfName="invDt_H2" value="MM/DD/YYYY" otherAttr=" size=\"12\" maxlength=\"8\" ezftoupper=\"\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('invDt_H2', 4);"/></td>
											<td class="stab">Customer</td>
											<td colspan="3"><ezf:inputText name="sellToCustCd_H2" ezfName="sellToCustCd_H2" value="H044CC" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/>
												<ezf:inputText name="dsAcctNm_H2" ezfName="dsAcctNm_H2" value="HP ENTERPRISES SERVICES" otherAttr=" size=\"40\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                        </tr>
                                        <tr height="21">
											<td class="stab">Cust PO Number</td>
											<td><ezf:inputText name="custIssPoNum_H2" ezfName="custIssPoNum_H2" value="EPO005948" otherAttr=" size=\"20\" maxlength=\"8\" ezftoupper=\"\""/></td>
											<td class="stab">Cust PO Date</td>
											<td><ezf:inputText name="custIssPoDt_H2" ezfName="custIssPoDt_H2" value="MM/DD/YYYY" otherAttr=" size=\"12\" maxlength=\"8\" ezftoupper=\"\""/></td>
											<td class="stab">Ship To Location</td>
											<td colspan="3"><ezf:inputText name="xxAllLineAddr_H2" ezfName="xxAllLineAddr_H2" value="950 N. MERIDIAN STREET, , INDIANAPOLIS, IN, 46204, MARION" otherAttr=" size=\"52\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                        </tr>
                                        <tr height="21">
											<td class="stab">Supplier Order Number</td>
											<td><ezf:inputText name="vndIssPoOrdNum_H2" ezfName="vndIssPoOrdNum_H2" value="CP123456" otherAttr=" size=\"20\" maxlength=\"8\" ezftoupper=\"\""/></td>
											<td class="stab">PO Status</td>
											<td><ezf:inputText name="poHdrStsDescTxt_H2" ezfName="poHdrStsDescTxt_H2" value="Open" otherAttr=" size=\"12\" maxlength=\"8\" ezftoupper=\"\""/></td>
											<td class="stab">Release All Lines</td>
											<td><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" onClick="sendServer('OnCheck_AllRelease')" /></td>
											<td class="stab">Cancel All Lines</td>
											<td><ezf:inputCheckBox name="xxChkBox_H3" ezfName="xxChkBox_H3" value="Y" onClick="sendServer('OnCheck_AllCancel')" /></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <!-- ################################################## Add - [E N D] ################################################## -->

                        <!-- ################################################## Detail   - [START] ################################################## -->
						<table style="width: 100%; MARGIN-LEFT: 13px; margin-top: 10px; margin-bottom: 5px;" border="0" cellpadding="0" cellspacing="0">
							  <col width="160">
				              <col width="72"  align="right"><!-- text : Freight Charge  -->
				              <col width="285">
				              <col width="40"  align="right"><!-- text : Tracking# -->
				              <col width="90">
				              <col width="32"  align="right"><!-- text : Carrier -->
				              <col width="90">
				              <col width="101">
				              <col width="64" align="right"><!-- text : Total Amount -->
				              <col width=""  >
              				  <tr height="21">
									<td>
										<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
									</td>
   				                  <td class="stab">Freight Charge</td>
   				                  <td>
   				                      <ezf:inputText name="spTotFuncFrtAmt_H2" ezfName="spTotFuncFrtAmt_H2" value="0.0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\" style=\"text-align: right;\""/>
   				                      <ezf:inputText name="frtCondDescTxt_H2" ezfName="frtCondDescTxt_H2" value="Delivery and Install" otherAttr=" size=\"24\" maxlength=\"20\" ezftoupper=\"\""/>
   				                  </td>
   				                  <td class="stab">Tracking#</td>
   				                  <td>
   				                      <ezf:inputText name="proNum_H2" ezfName="proNum_H2" value="AP1049859" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/>
   				                  </td>
   				                  <td class="stab">Carrier</td>
   				                  <td>
   				                      <ezf:inputText name="carrNm_H2" ezfName="carrNm_H2" value="Apex express" otherAttr=" size=\"12\" maxlength=\"60\" ezftoupper=\"\""/>
   				                  </td>
   				                  <td>
   				                      <ezf:inputButton name="OpenWin_Carrier" value="Carr" htmlClass="pBtn2" />
   				                      <ezf:inputButton name="Apply" value="Apply" htmlClass="pBtn3" />
   				                  </td>
   				                  <td class="stab">Total Amount</td>
   				                  <td><ezf:inputText name="entPoDtlDealNetAmt_H2" ezfName="entPoDtlDealNetAmt_H2" value="9,999,999,999.99" otherAttr=" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/>
   				                      <ezf:inputText name="ccyCd_H2" ezfName="ccyCd_H2" value="USD" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
   				                  </td>
         					  </tr>
						</table>
						<table>
							<tr>
								<td>
									<div id="parentBoxA">
										<div style="float:left; display:block"><!-- left table -->
											<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
											<div id="leftTbl" style="float:left; display:block; height:315px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
										</div><!-- left table -->
										<div style="float:left"><!-- right table -->
											<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;" >
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="2467px" style="margin-right:20px">

                              <col width="50"  align="center"><!-- check box -->				
                              <col width="50"  align="center"><!-- check box -->
                              <col width="60"  align="center"><!-- Line# -->
                              <col width="73"  align="center"><!-- Line# -->
                              <col width="120" align="center"><!-- Item Number -->
                              <col width="200" align="center"><!-- Item Description -->
                              <col width="40" align="center"><!-- Set -->
                              <col width="100" align="center"><!-- Unit Price -->
                              <col width="100" align="center"><!-- Vnd UOM Qty -->
                              <col width="100" align="center"><!-- PO Qty -->
                              <col width="100" align="center"><!-- Balance Qty -->
                              <col width="100" align="center"><!-- Release Qty -->
                              <col width="155"  align="center"><!-- Serial# -->
                              <col width="150" align="center"><!-- Trancking# -->
                              <col width="190" align="center"><!-- Carrier Name -->
                              <col width="500" align="center"><!-- Comment -->
                              <col width="110" align="center"><!-- Vnd PO# -->
                              <col width="85" align="center"><!-- Invoice Date -->
                              <col width="100" align="center"><!-- Invoice# -->
                              <tr height="35">
                                  <td class="pClothBs colFix"  id="AH0"  >Release<br>Line</td>
                                  <td class="pClothBs colFix"  id="AH1"  >Cancel<br>Line</td>
                                  <td class="pClothBs colFix"  id="AH2"  >PO Line#</td>
                                  <td class="pClothBs"  id="AH3"  >Order Line#</td>
                                  <td class="pClothBs"  id="AH4"  >Item Number</td>
                                  <td class="pClothBs"  id="AH5"  >Item Description</td>
                                  <td class="pClothBs"  id="AH6"  >Set</td>
                                  <td class="pClothBs"  id="AH7"  >Unit Price</td>
                                  <td class="pClothBs"  id="AH8"  >Supplier<br>UOM Qty</td>
                                  <td class="pClothBs"  id="AH9"  >PO Qty</td>
                                  <td class="pClothBs"  id="AH10"  >Balance Qty</td>
                                  <td class="pClothBs"  id="AH11">Release Qty</td>
                                  <td class="pClothBs"  id="AH12"  >Serial#</td>
                                  <td class="pClothBs"  id="AH13"  >Tracking#</td>
                                  <td class="pClothBs"  id="AH14"  >Carrier Name</td>
                                  <td class="pClothBs"  id="AH15"  >Comment</td>
                                  <td class="pClothBs"  id="AH16"  >Vendor Issue PO#</td>
                                  <td class="pClothBs"  id="AH17"  >Invoice Date</td>
                                  <td class="pClothBs"  id="AH18"  >Invoice #</td>
														</tr>
													</table>
													</div>
												

												<div id="rightTbl" style="width:1118px; height:315px; display:block; overflow:scroll; margin:0px; padding:0px;" >
														<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="2467px">
                              <col width="50"  align="center"><!-- check box -->
                              <col width="50"  align="center"><!-- check box -->
                              <col width="60"  align="left"><!-- Line# -->
                              <col width="73"  align="left"><!-- Line# -->
                              <col width="120" align="left"><!-- Item Number -->
                              <col width="200" align="center"><!-- Item Description -->
                              <col width="40" align="center"><!-- Set -->
                              <col width="100" align="right"><!-- Unit Price -->
                              <col width="100" align="right"><!-- VND UOM Qty -->
                              <col width="100" align="right"><!-- PO Qty -->
                              <col width="100" align="right"><!-- Balance Qty -->
                              <col width="100" align="right"><!-- Release Qty -->
                              <col width="155"  align="left"><!-- Serial# -->
                              <col width="150" align="left"><!-- Trancking# -->
                              <col width="190" align="left"><!-- Carrier Name -->
                              <col width="500" align="left"><!-- Comment -->
                              <col width="110" align="left"><!-- Vendor PO# -->
                              <col width="85" align="center"><!-- Invoice Date -->
                              <col width="100" align="center"><!-- Invoice # -->
                              <ezf:row ezfHyo="A">
                                  <tr height="25" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
               											<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/></td>
               											<td><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A2{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:label name="dispPoDtlLineNum_A1" ezfName="dispPoDtlLineNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dispPoDtlLineNum_A1{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:label name="xxScrItem8Txt_A1" ezfName="xxScrItem8Txt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem8Txt_A1{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mdseCd_A1{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="IW EMC AM PLUG-IN SUB" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"30\" id=\"mdseDescShortTxt_A1{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:label name="xxScrItem8Txt_A2" ezfName="xxScrItem8Txt_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem8Txt_A2{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:label name="entDealNetUnitPrcAmt_A1" ezfName="entDealNetUnitPrcAmt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"entDealNetUnitPrcAmt_A1{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:label name="poDispQty_A1" ezfName="poDispQty_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"poDispQty_A1{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:label name="poQty_A1" ezfName="poQty_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"poQty_A1{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:label name="poBalQty_A1" ezfName="poBalQty_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"poBalQty_A1{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:inputText name="poRcvQty_A1" ezfName="poRcvQty_A1" value="9,999,999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"13\" maxlength=\"30\" id=\"poRcvQty_A1{EZF_ROW_NUMBER}\""/></td>
                                    <td class="stab"><ezf:inputText name="xxScrItem20Txt_A1" ezfName="xxScrItem20Txt_A1" value="No Entry" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" id=\"xxScrItem20Txt_A1{EZF_ROW_NUMBER}\""/>
                                      	<ezf:inputButton name="OpenWin_Serial" value="" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Serial{EZF_ROW_NUMBER}\""/></td>
<!-- START 2020/06/25 [QC#56979,MOD] -->
                               <!-- <td><input type="text" size="16" maxlength="30" value="----+----1----+" name="proNum_A1" ezfname="proNum_A1" ezfhyo="A" id="proNum_A1{EZF_ROW_NUMBER}"></td> -->
                                    <td>
                                        <ezf:anchor name="carrTrkUrl_A1" ezfName="carrTrkUrl_A1" ezfHyo="A" ezfArrayNo="0" onClick="window.open( this.href, '_blank', 'status=yes, toolbar=yes, location=yes, menubar=yes, scrollbars=yes, resizable=yes');return false;" otherAttr=" id=\"carrTrk{EZF_ROW_NUMBER}\"">
                                            <ezf:label name="proNum_A1" ezfName="proNum_A1" ezfHyo="A" ezfArrayNo="0" />
                                        </ezf:anchor>
                                    </td>
<!-- END   2020/06/25 [QC#56979,MOD] -->
                                    <td class="stab"><ezf:inputText name="carrNm_A1" ezfName="carrNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16x\" maxlength=\"60\" id=\"carrNm_A1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
                                      	<ezf:inputButton name="OpenWin_Carrier" value="" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Carrier{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:inputText name="poOrdDtlCmntTxt_A1" ezfName="poOrdDtlCmntTxt_A1" value="Cancelled Reason:confirmed with CVI - cancelled on their end " ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"70\" maxlength=\"100\" id=\"poOrdDtlCmntTxt_A1{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:inputText name="vndIssPoOrdNum_A1" ezfName="vndIssPoOrdNum_A1" value="VP000001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" id=\"vndIssPoOrdNum_A1{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:inputText name="poRcvEtaDt_A1" ezfName="poRcvEtaDt_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" id=\"poRcvEtaDt_A1{EZF_ROW_NUMBER}\""/></td>
                                    <td><ezf:inputText name="domInvNum_A1" ezfName="domInvNum_A1" value="INV000001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" id=\"domInvNum_A1{EZF_ROW_NUMBER}\""/></td>
                                    <td class="pAuditInfo">
                                        <ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
                                        <ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
                                        <ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
                                        <ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
                                        <ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
                                    </td>
																</tr>
                   				</ezf:row>
												</table>
											</div>


										</div>
									</div>
									
								</td>
							</tr>
					</table>

										<script type="text/javascript" defer>
										  S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0,  false, true);
										</script>

                        <!-- footer buttons -->
                        <TABLE style="margin-left: 8px; margin-top: 10px; z-index: 2; position: relative" border="0" cellpadding="1" cellspacing="1" align="left">
                            <colgroup>
                                <col width="110" align="left">
                                <col>
                            </colgroup>
                            <tr>
                                <td class="stab">Cancellation Reason</td>
                                <td><ezf:inputText name="poOrdDtlCmntTxt_H2" ezfName="poOrdDtlCmntTxt_H2" otherAttr=" size=\"60\" maxlength=\"90\""/></td>
                            </tr>
                        </TABLE>
                        <TABLE style="margin-top: 6px; z-index: 0; position: relative; float: right" border="0" cellpadding="1" cellspacing="1" align="left">
                            <colgroup>
                                <col width="">
                                <col width="400" align="left">
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td></td>
                                    <td>
                                        <ezf:skip>
                                            <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 30px;">
                                                <colgroup>
                                                    <col align="center" width="54">
                                                    <col align="right" width="40">
                                                    <col align="center" width="16">
                                                    <col align="right" width="40">
                                                    <col align="center" width="16">
                                                    <col align="right" width="40">
                                                    <col width="10">
                                                    <col>
                                                    <col width="1">
                                                    <col>
                                                </colgroup>
                                                <tbody>
                                                    <tr>
                                                        <td class="stab"><label>Showing</label></td>
                                                        <td class="pOut"><label ezfout name="xxPageShowFromNum_A" ezfname="xxPageShowFromNum_A">1</label></td>
                                                        <td class="stab"><label>to</label></td>
                                                        <td class="pOut"><label ezfout name="xxPageShowToNum_A" ezfname="xxPageShowToNum_A">40</label></td>
                                                        <td class="stab"><label>of</label></td>
                                                        <td class="pOut"><label ezfout name="xxPageShowOfNum_A" ezfname="xxPageShowOfNum_A">999</label></td>
                                                        <td>&nbsp;</td>
                                                        <td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></td>
                                                        <td></td>
                                                        <td><input onclick="tablePagenation(this.name, 'A')" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></td>
                                                    </tr>
                                                </tbody>
                                            </table>
		                                    </td>
                                        </ezf:skip>
																				<%-- Pagenation --%>
																				<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																					<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																					<jsp:param name="TableNm"     value="A" />
																					<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
																					<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
																					<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
																				</jsp:include>
                                </tr>
                            </tbody>
                        </TABLE>
												<%-- ########## Detail Tab [END] ########## --%>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</center>

<%-- **** Task specific area ends here **** --%>
