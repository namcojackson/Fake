<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230302155910 --%>
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
			<input type="hidden" name="pageID" value="NPAL1280Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="PO Requisition Entry">

<center>
	<table height="95%" cellSpacing="0" cellPadding="0" width="100%" border="0">
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
										<tr title="PO Requisition Entry">
											<td width="107px" align="center" class="same">PO Req Entry</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</ezf:skip>

					<div class="pTab_BODY">
						<!-- ################################################## Header - [START] ################################################## -->
						<table style="width: 100%; margin-bottom: 5px; border-bottom: ridge 1px;">
							<col width=""  align="left">
							<tr valign="top">
								<td>
									<table border="0" cellpadding="0" cellspacing="0" style="margin-left: 5px;">
										<col width="105">
										<col width="230">
										<col width="20">
										<col width="5">
										<col width="125">
										<!-- START 2023/02/01 T.Kuroda [QC#60966, MOD] -->
										<!--<col width="135">-->
										<!--<col width="70">-->
										<col width="185">
										<col width="20">
										<!-- END   2023/02/01 T.Kuroda [QC#60966, MOD] -->
										<col width="10">
										<col width="110">
										<col width="0">
										<!-- 1 -->
										<tr height="20">
											<td class="stab">Requisition Number</td>
											<td><ezf:inputText name="prchReqNum" ezfName="prchReqNum" value="50123456" otherAttr=" size=\"19\" maxlength=\"8\" ezftoupper=\"\""/>
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" />
											</td>
											<td style="text-align:right;"></td>
											<td></td>
											<td class="stab">Document Source Type</td>
											<td colspan="2"><ezf:inputText name="prchReqSrcTpNm" ezfName="prchReqSrcTpNm" value="Sales Order" otherAttr=" size=\"33\" maxlength=\"30\" ezftoupper=\"\""/></td>
											<td></td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_A0" ezfName="xxLinkAncr_A0" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier</ezf:anchor></td>
											<!--<td colspan="2"><input type="text" size="40" maxlength="130" value="CANON USA" name="prntVndNm" ezfname="prntVndNm" ezftoupper ></td>-->
											<td colspan="2">
												<ezf:inputText name="prntVndCd" ezfName="prntVndCd" value="11331" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
												<ezf:inputButton name="Get_SupplierName" ezfName="Get_SupplierName" value=">>" htmlClass="pBtn0" />
												<ezf:inputText name="prntVndNm" ezfName="prntVndNm" value="CANON USA NP / CLC" otherAttr=" size=\"21\" maxlength=\"240\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 2 -->
										<tr height="20">
											<td class="stab">Requisition Type</td>
											<td>
												<ezf:select name="prchReqTpCd_SL" ezfName="prchReqTpCd_SL" ezfCodeName="prchReqTpCd_PD" ezfDispName="prchReqTpNm_PD" otherAttr=" style=\"width:136\""/>
											</td>
											<td style="text-align:right;"></td>
											<td></td>
											<td class="stab">Source Doc# / Qualifier</td>
											<td class="stab">
												<!-- START 2023/02/01 T.Kuroda [QC#60966, MOD] -->
												<!--<input type="text" class="pPro" size="19" maxlength="15" value="CP000001" name="trxRefNum" ezfname="trxRefNum" ezftoupper >-->
												<ezf:inputText name="trxRefNum" ezfName="trxRefNum" value="CP000001" otherAttr=" size=\"28\" maxlength=\"15\" ezftoupper=\"\""/>
												<!-- END   2023/02/01 T.Kuroda [QC#60966, MOD] -->
											</td>
											<td class="stab"  style="text-align:left;">
												<!-- START 2023/02/01 T.Kuroda [QC#60966, MOD] -->
												<!--<input type="text" class="pPro" size="13" maxlength="2" value="DS" name="poQlfyCd" ezfname="poQlfyCd" ezftoupper >-->
												<ezf:inputText name="poQlfyCd" ezfName="poQlfyCd" value="DS" otherAttr=" size=\"4\" maxlength=\"2\" ezftoupper=\"\""/>
												<!-- END   2023/02/01 T.Kuroda [QC#60966, MOD] -->
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_A1" ezfName="xxLinkAncr_A1" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier Site</ezf:anchor></td>
											<!--
											<td class="stab">
												<input type="text" size="10" maxlength="20" value="08607" name="vndCd" ezfname="vndCd" ezftoupper >
											</td>
											-->
											<td colspan="2">
												<!--<input type="text" size="28" maxlength="60" value="ONE CANON PLAZA" name="locNm" ezfname="locNm"  ezftoupper>-->
												<ezf:inputText name="vndCd" ezfName="vndCd" value="08607" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
												<ezf:inputButton name="Get_SupplierSiteName" ezfName="Get_SupplierSiteName" value=">>" htmlClass="pBtn0" />
												<ezf:inputText name="locNm" ezfName="locNm" value="ONE CANON PLAZA" otherAttr=" size=\"21\" maxlength=\"60\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 3 -->
										<tr height="20">
											<td class="stab">Requisition Status</td>
											<td colspan="2">
												<ezf:inputText name="prchReqStsNm" ezfName="prchReqStsNm" value="Open" otherAttr=" size=\"19\" maxlength=\"30\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">Plan Name</td>
											<td colspan="2"><ezf:inputText name="mrpPlnNm" ezfName="mrpPlnNm" value="Min Max Plan 1" otherAttr=" size=\"33\" maxlength=\"70\" ezftoupper=\"\""/></td>
											<td></td>
											<td class="stab" ><ezf:anchor name="xxLinkAncr_A2" ezfName="xxLinkAncr_A2" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" >Destination WH</ezf:anchor></td>
											<!--
											<td class="stab">
												<input class="pHsu" type="text" size="10" maxlength="20" value="C01" name="destRtlWhCd" ezfname="destRtlWhCd" ezftoupper >&nbsp;
											</td>
											-->
											<td colspan="2">
												<ezf:inputText name="destRtlWhCd" ezfName="destRtlWhCd" value="C01" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
												<ezf:inputButton name="Get_ShipToInfo" ezfName="Get_ShipToInfo" value=">>" htmlClass="pBtn0" />
												<ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="C01 WOOD, SPARES" otherAttr=" size=\"21\" maxlength=\"30\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 4 -->
										<tr height="20">
											<td class="stab">Approval Status</td>
											<td colspan="2">
												<ezf:inputText name="prchReqApvlStsNm" ezfName="prchReqApvlStsNm" value="Awaiting Approval" otherAttr=" size=\"19\" maxlength=\"50\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">Buyer</td>
											<td colspan="2"><ezf:inputText name="prchReqCratByNm" ezfName="prchReqCratByNm" value="BOB PAGAN" otherAttr=" size=\"33\" maxlength=\"60\" ezftoupper=\"\""/></td>
											<td></td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_A4" ezfName="xxLinkAncr_A4" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" >Destination SWH</ezf:anchor></td>
											<!--
											<td class="stab">
												<input class="pHsu" type="text" size="10" maxlength="20" value="NEW" name="destRtlSwhCd" ezfname="destRtlSwhCd" ezftoupper >
											</td>
											-->
											<td><ezf:inputText name="destRtlSwhCd" ezfName="destRtlSwhCd" value="NEW" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td style="text-align:right;"><ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" value="NEW 100%" otherAttr=" size=\"21\" maxlength=\"30\" ezftoupper=\"\""/></td>
										</tr>
										<!-- 5 -->
										<tr height="20">
											<td class="stab">Date Created</td>
											<td><ezf:inputText name="prchReqCratDt" ezfName="prchReqCratDt" value="05/15/2015" otherAttr=" maxlength=\"10\" size=\"10\""/></td>
											<td ></td>
											<td></td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_A3" ezfName="xxLinkAncr_A3" ezfEmulateBtn="1" ezfGuard="OpenWin_Requester" >Requester</ezf:anchor></td>
											<td colspan="2">
												<ezf:inputText name="fullPsnNm" ezfName="fullPsnNm" value="JOEL C MITCHELL" otherAttr=" size=\"33\" maxlength=\"62\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_A6" ezfName="xxLinkAncr_A6" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToLocation" >Ship To Customer</ezf:anchor></td>
											<!--
											<td class="stab">
												<input class="pHsu" type="text" size="10" maxlength="20" value="12345" name="shipToCustCd" ezfname="shipToCustCd" ezftoupper >
											</td>
											-->
											<td colspan="2">
												<ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" value="12345" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
												<ezf:inputButton name="Get_ShipToInfoCustomer" ezfName="Get_ShipToInfoCustomer" value=">>" htmlClass="pBtn0" />
												<ezf:inputText name="xxLocNm" ezfName="xxLocNm" value="Ship to Customer" otherAttr=" size=\"21\" maxlength=\"30\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 6 -->
										<tr height="20">
											<td class="stab">Date & Time Needed</td>
											<td class="stab" colspan="2"><ezf:inputText name="rqstRcvDt" ezfName="rqstRcvDt" value="05/15/2015" otherAttr=" maxlength=\"10\" size=\"10\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt', 4);"/>
												<ezf:inputText name="xxDtTm" ezfName="xxDtTm" value="11:00" otherAttr=" maxlength=\"5\" size=\"5\""/>
				                                    <ezf:select name="xxTpCd_SL" ezfName="xxTpCd_SL" ezfCodeName="xxTpCd_PD" ezfDispName="xxTpNm_PD" htmlClass="stab" otherAttr=" style=\"width:50\""/>
				                                <ezf:inputButton name="Apply" value="Apply" htmlClass="pBtn3" />
				                            </td>
											<td></td>
											<td class="stab">Planning Group</td>
											<td colspan="2">
												<ezf:select name="prchGrpCd_SL" ezfName="prchGrpCd_SL" ezfBlank="1" ezfCodeName="prchGrpCd_PD" ezfDispName="prchGrpDescTxt_PD" otherAttr=" style=\"width:235\""/>
											</td>
											<td ></td>
											<td class="stab">Copy Order#/Config#</td>
											<td colspan="2">
												<ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="----+----1" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/>
												<ezf:inputText name="dsOrdPosnNum" ezfName="dsOrdPosnNum" value="----+----1" otherAttr=" size=\"3\" maxlength=\"8\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 7 -->
										<tr height="20">
										<!-- START 2023/02/01 T.Kuroda [QC#60966, MOD] -->
											<!--<td class="stab">Description</td>-->
											<!--<td colspan="2">-->
												<!--<input type="text" size="28" maxlength="240" value="REPLACES MOVE ORDER 10028862" name="prchReqCmntTxt" ezfname="prchReqCmntTxt" ezftoupper >-->
											<!--</td>-->
											<!--<td></td>-->
											<!--<td></td>-->
											<!--<td colspan="1">-->
												<!--<input type="text" size="33" maxlength="240" value="Deliver to 3rd Floor" name="spclInstnCmntTxt" ezfname="spclInstnCmntTxt" ezftoupper > -->
											<!--</td>-->
											<td class="stab">Vendor Ship Date</td>
											<td class="stab">
												<ezf:inputText name="rqstShipDt" ezfName="rqstShipDt" value="05/15/2015" otherAttr=" maxlength=\"10\" size=\"10\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstShipDt', 4);"/>
												<ezf:inputButton name="RqstShipDt_Apply" value="Apply" htmlClass="pBtn3" />
											</td>
											<td></td>
											<td></td>
											<td class="stab">Description</td>
											<td>
												<ezf:inputText name="prchReqCmntTxt" ezfName="prchReqCmntTxt" value="REPLACES MOVE ORDER 10028862" otherAttr=" size=\"28\" maxlength=\"240\" ezftoupper=\"\""/>
											</td>
										<!-- END   2023/02/01 T.Kuroda [QC#60966, MOD] -->
											<td colspan="8" style="text-align:right;">
												<ezf:inputButton name="HeaderCancel" value="Cancel" htmlClass="pBtn6" />
												<ezf:inputButton name="Copy" value="Copy" htmlClass="pBtn6" />
												<ezf:inputButton name="OpenWin_TextEntry" value="Comment/Text" htmlClass="pBtn7" />
												<ezf:inputButton name="OpenWin_ApprovalHist" value="Approval Hist" htmlClass="pBtn7" />
												<ezf:inputButton name="Attachments" value="Attachments" htmlClass="pBtn7" />
											</td>
                                            <td class="pAuditInfo">
                                                <ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" />
                                                <ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" />
                                                <ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" />
                                                <ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" />
                                                <ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" />
                                            </td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<!-- ################################################## Header - [E N D] ################################################## -->

												<%-- #### TAB - HEAD #### --%>
												<div class="pTab_HEAD">
													<ul>
														<table border="0" cellpadding="0" cellspacing="0" width="900px">
															<tr>
																<td width="96%">
																	<li id="Detail" title="Detail" class="pTab_OFF">
																		<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TAB_Detail" >Detail</ezf:anchor>
																	</li>
																	<li id="AddlHeader" title="Addl Header" class="pTab_ON">
																		<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TAB_AddlHeader" >Addl Header</ezf:anchor>
																	</li>
																</td>
															</tr>
														</table>
													</ul>
												</div>
												<div class="pTab_BODY_In">
													<!-- START 2023/02/01 T.Kuroda [QC#60966, MOD] -->
													<!-- <div style="height: 390px" > -->
													<div style="height: 380px" >
													<!-- END   2023/02/01 T.Kuroda [QC#60966, MOD] -->
													<c:choose>
														<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Detail'}">
															<div id="TabContent-Detail">
																<script type="text/javascript">
																	document.getElementById("Detail").className = "pTab_ON";
																	document.getElementById("AddlHeader").className = "pTab_OFF";
																</script>

												<%-- ########## Detail Tab [START] ########## --%>
                        <!-- ######################################## Add - [S T A R T] #################################### -->
                        <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
                            <col width=""  align="left">
                            <tr>
                                <td valign="top">
                                    <table border="0">
                                        <col width="200" align="left">
                                        <col width="300" align="left">
                                        <col width="580" align="right">
                                        <!-- 1 -->
                                        <tr height="20">
                                            <td>
                                                <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
                                            </td>
                                            <td>
                                                <ezf:inputButton name="Add_NewLine" value="Add New Line (F11)" htmlClass="pBtn10" />
                                            </td>
                                            <td class="stab">Total Amount&nbsp;
                                                <ezf:inputText name="xxTotAmt" ezfName="xxTotAmt" value="9,999,999,999.99" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\" style=\"text-align: right;\""/>
                                                <ezf:inputText name="ccyCd" ezfName="ccyCd" value="USD" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <!-- ################################################## Add - [E N D] ################################################## -->

                        <!-- ################################################## Detail   - [START] ################################################## -->
                        <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td align="left" valign="top">
                                    <%-- ******************************* --%>
                                    <%-- *** Left Table Area(Header) *** --%>
                                    <%-- ******************************* --%>
                                    <div id="parentBoxA">
                                        <div style="float:left; display:block"><!-- left table -->
                                            <div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
                                            <!-- START 2023/02/01 T.Kuroda [QC#60966, MOD] -->
                                            <!-- <div id="leftTbl" style="float:left; display:block; height:253px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div> -->
                                            <div id="leftTbl" style="float:left; display:block; height:243px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
                                            <!-- END   2023/02/01 T.Kuroda [QC#60966, MOD] -->
                                        </div><!-- left table -->
                                        <div style="float:left"><!-- right table -->
                                            <div id="rightTblHead" style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
                                                <!-- START 2023/02/01 T.Kuroda [QC#60966, MOD] -->
                                                <!--<table style="table-layout:fixed; margin-right:20px" id="AHEAD" width="1494px" border="1" cellpadding="1" cellspacing="0">-->
                                                <table style="table-layout:fixed; margin-right:20px" id="AHEAD" width="1602px" border="1" cellpadding="1" cellspacing="0">
                                                <!-- END   2023/02/01 T.Kuroda [QC#60966, MOD] -->
                                                    <col width="40"  align="center"><!-- check box -->
                                                    <col width="45"  align="center"><!-- Line# -->
                                                    <col width="120"  align="center"><!-- Line Type -->
                                                    <col width="185" align="center"><!-- Item# -->
                                                    <col width="120" align="center"><!-- Vendor Mdse -->
                                                    <col width="150" align="center"><!-- Item Description -->
                                                    <col width="93" align="center"><!-- Order Qty -->
                                                    <col width="50"  align="center"><!-- UOM -->
                                                    <col width="200" align="center"><!-- Supplier -->
                                                    <col width="155" align="center"><!-- Site -->
                                                    <col width="120" align="center"><!-- Status -->
                                                    <col width="120" align="center"><!-- List Price -->
                                                    <col width="120" align="center"><!-- Ext. Price -->
                                                    <col width="108" align="center"><!-- Date Needed -->
                                                    <!-- START 2023/02/01 T.Kuroda [QC#60966, ADD] -->
                                                    <col width="108" align="center"><!-- Vendor Ship Date -->
                                                    <!-- END   2023/02/01 T.Kuroda [QC#60966, ADD] -->
                                                    <col width="200" align="center"><!-- Charge Account -->
                                                    <col width="46"  align="center"><!-- MT -->
                                                    <col width="46"  align="center"><!-- PC -->
                                                    <col width="185" align="center"><!-- Special Instruction -->
                                                    <col width="185" align="center"><!-- Text -->
                                                    <col width="88"  align="center"><!-- Source Line # -->
                                                    <col width="88"  align="center"><!-- Add to PO -->
                                                    <col width="105" align="center"><!-- Seduled Release Date -->
                                                    <col width="88"  align="center"><!-- PO # -->
                                                    <col width="88"  align="center"><!-- PO Line # -->
                                                    <col width="120" align="center"><!-- Released Date-->
                                                    <col width="200" align="center"><!-- Released Error-->
                                                    <tr>
                                                        <td class="pClothBs colFix" id="AH0"></td>
                                                        <td class="pClothBs colFix" id="AH1">Line#</td>
                                                        <td class="pClothBs colFix" id="AH2">Line Type</td>
                                                        <td class="pClothBs colFix" id="AH3">Item Number</td>
                                                        <td class="pClothBs" id="AH4">Supplier Item#</td>
                                                        <td class="pClothBs" id="AH5">Item Description</td>
                                                        <td class="pClothBs" id="AH6">Order Qty</td>
                                                        <td class="pClothBs" id="AH7">UOM</td>
                                                        <td class="pClothBs" id="AH8">Supplier</td>
                                                        <td class="pClothBs" id="AH9">Site</td>
                                                        <td class="pClothBs" id="AH10">Status</td>
                                                        <td class="pClothBs" id="AH11">Unit Price</td>
                                                        <td class="pClothBs" id="AH12">Ext. Price</td>
                                                        <td class="pClothBs" id="AH13">Date Needed</td>
                                                        <!-- START 2023/02/01 T.Kuroda [QC#60966, ADD] -->
                                                        <td class="pClothBs" id="AH14">Vendor Ship Date</td>
                                                        <!-- END   2023/02/01 T.Kuroda [QC#60966, ADD] -->
                                                        <td class="pClothBs" id="AH15">Charge Account</td>
                                                        <td class="pClothBs" id="AH16">MT</td>
                                                        <td class="pClothBs" id="AH17">PC</td>
                                                        <td class="pClothBs" id="AH18">Special Instruction</td>
                                                        <td class="pClothBs" id="AH19">Text</td>
                                                        <td class="pClothBs" id="AH20">Source Line #</td>
                                                        <td class="pClothBs" id="AH21">Add to PO</td>
                                                        <td class="pClothBs" id="AH22">Scheduled<br>Create PO Date</td>
                                                        <td class="pClothBs" id="AH23">PO #</td>
                                                        <td class="pClothBs" id="AH24">PO Line #</td>
                                                        <td class="pClothBs" id="AH25">PO Created Date</td>
                                                        <td class="pClothBs" id="AH26">Release Error</td>
                                                    </tr>
                                                </table>
                                            </div>
	                                        <%-- ******************************* --%>
	                                        <%-- *** Left Table Area(Detail) *** --%>
	                                        <%-- ******************************* --%>
	                                        <!-- START 2023/02/01 T.Kuroda [QC#60966, MOD] -->
	                                        <!-- <div id="rightTbl" style="width:1118px; height:270px; display:block; overflow:scroll; margin:0px; padding:0px;" > -->
	                                        <div id="rightTbl" style="width:1118px; height:260px; display:block; overflow:scroll; margin:0px; padding:0px;" >
	                                            <!--<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1494">-->
	                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1602">
	                                        <!-- END   2023/02/01 T.Kuroda [QC#60966, MOD] -->
	                                                <col width="40"  align="center"><!-- check box -->
	                                                <col width="45"  align="left"><!-- Line# -->
	                                                <col width="120"  align="left"><!-- Line Type -->
	                                                <col width="185" align="center"><!-- Item# -->
	                                                <col width="120" align="center"><!-- Supplier Item# -->
	                                                <col width="150" align="left"><!-- Item Description -->
	                                                <col width="93" align="left"><!-- Order Qty -->
	                                                <col width="50"  align="left"><!-- UOM -->
	                                                <col width="200" align="left"><!-- Supplier -->
	                                                <col width="155" align="left"><!-- Site -->
	                                                <col width="120" align="center"><!-- Status -->
	                                                <col width="120" align="center"><!-- Unit Price -->
	                                                <col width="120" align="right"><!-- Ext. Price -->
	                                                <col width="108" align="center"><!-- Date Needed -->
	                                                <!-- START 2023/02/01 T.Kuroda [QC#60966, ADD] -->
	                                                <col width="108" align="center"><!-- Vendor Ship Date -->
	                                                <!-- END   2023/02/01 T.Kuroda [QC#60966, ADD] -->
	                                                <col width="200" align="left"><!-- Charge Account -->
	                                                <col width="46"  align="left"><!-- MT -->
	                                                <col width="46"  align="left"><!-- PC -->
	                                                <col width="185" align="left"><!-- Special Instruction -->
	                                                <col width="185" align="left"><!-- Text -->
	                                                <col width="88"  align="left"><!-- Source Line # -->
	                                                <col width="88"  align="center"><!-- Add to PO -->
	                                                <col width="105" align="center"><!-- Scheduled Rel Date -->
	                                                <col width="88"  align="left"><!-- PO # -->
	                                                <col width="88"  align="left"><!-- PO Line # -->
	                                                <col width="120" align="left"><!-- Released Date -->
	                                                <col width="200" align="center"><!-- Released Error -->
	                                                <ezf:row ezfHyo="A">
	                                                    <tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
		                                                    <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/></td>
		                                                    <td><ezf:label name="xxLineNum_A1" ezfName="xxLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
		                                                    <td>
		                                                        <ezf:select name="prchReqLineTpCd_A1" ezfName="prchReqLineTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prchReqLineTpCd_PD" ezfDispName="prchReqLineTpNm_PD" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServerForPreferredView('OnChange_LineType', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:110px\""/>
		                                                    </td>
		                                                    <td>
		                                                        <ezf:inputButton name="OpenWin_Item" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Item{EZF_ROW_NUMBER}\""/>
		                                                        <ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"30\" ezftoupper=\"\""/>
		                                                        <ezf:inputButton name="Get_MdseInfo" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"Get_MdseInfo#{EZF_ROW_NUMBER}\""/>
		                                                    </td>
		                                                    <td><ezf:inputText name="aslMdseCd_A1" ezfName="aslMdseCd_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/></td>
		                                                    <td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="IMAGERUNNER 1025IF" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
		                                                    <td><ezf:inputText name="prchReqDispQty_A1" ezfName="prchReqDispQty_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" style=\"text-align: right;\""/></td>
		                                                    <td><ezf:inputText name="prchReqDsplUomCd_A1" ezfName="prchReqDsplUomCd_A1" value="EA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"4\""/></td>
		                                                    <td><ezf:inputText name="prntVndNm_A1" ezfName="prntVndNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"130\" ezftoupper=\"\""/>
		                                                    	<ezf:inputButton name="OpenWin_Supplier" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Supplier#{EZF_ROW_NUMBER}\""/></td>
		                                                    <td><ezf:inputText name="locNm_A1" ezfName="locNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/>
		                                                    	<ezf:inputButton name="OpenWin_Supplier" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Supplier2#{EZF_ROW_NUMBER}\""/></td>
		                                                    <td><ezf:inputText name="prchReqLineStsNm_A1" ezfName="prchReqLineStsNm_A1" value="Open" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
		                                                    <td><ezf:inputText name="entDealNetUnitPrcAmt_A1" ezfName="entDealNetUnitPrcAmt_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\" style=\"text-align: right;\""/>
		                                                    <td><ezf:label name="xxUnitPrc_A1" ezfName="xxUnitPrc_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \""/></td>
                                                            <td><ezf:inputText name="rqstRcvDt_A1" ezfName="rqstRcvDt_A1" value="05/15/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                                                <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_A1', 4, {EZF_ROW_NUMBER});"/>
                                                            </td>
                                                            <!-- START 2023/02/01 T.Kuroda [QC#60966, ADD] -->
                                                            <td><ezf:inputText name="rqstShipDt_A1" ezfName="rqstShipDt_A1" value="05/15/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                                                <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstShipDt_A1', 4, {EZF_ROW_NUMBER});"/>
                                                            </td>
                                                            <!-- END   2023/02/01 T.Kuroda [QC#60966, ADD] -->
		                                                    <td class="stab">
		                                                    	<ezf:inputText name="xxScrItem130Txt_A1" ezfName="xxScrItem130Txt_A1" value="800.101.211.11201.14016.00.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"130\" ezftoupper=\"\""/>
		                                                    	<ezf:inputButton name="OpenWin_Account" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Account#{EZF_ROW_NUMBER}\""/>
		                                                    </td>
		                                                    <td><ezf:label name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
		                                                    <td><ezf:label name="coaProdCd_A1" ezfName="coaProdCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
		                                                    <td><ezf:inputText name="spclInstnCmntTxt_A1" ezfName="spclInstnCmntTxt_A1" value="Special Instruction" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"240\""/></td>
		                                                    <td><ezf:inputText name="prchReqLineCmntTxt_A1" ezfName="prchReqLineCmntTxt_A1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"240\""/></td>
		                                                    <td><ezf:label name="xxLineNum_TR" ezfName="xxLineNum_TR" ezfHyo="A" ezfArrayNo="0" /></td>
		                                                    <td><ezf:inputText name="relRqstToPoOrdNum_A1" ezfName="relRqstToPoOrdNum_A1" value="----+----1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
		                                                    <td><ezf:inputText name="poSchdRelDt_A1" ezfName="poSchdRelDt_A1" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
		                                                        <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('poSchdRelDt_A1', 4, {EZF_ROW_NUMBER});"/>
		                                                    </td>
		                                                    <td><ezf:label name="poOrdNum_A1" ezfName="poOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
		                                                    <td><ezf:label name="poOrdDtlLineNum_A1" ezfName="poOrdDtlLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
		                                                    <td><ezf:label name="xxDtTm_A1" ezfName="xxDtTm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
		                                                    <td><ezf:inputText name="prchReqRelErrMsgTxt_A1" ezfName="prchReqRelErrMsgTxt_A1" value="Item does not exist in master." ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"400\""/></td>
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
                                    <script type="text/javascript" defer>
                                        S21TableUI.initialize("parentBoxA", "AHEAD", "A", 4, true, true);
                                    </script>
                                </td>
                            </tr>
                        </table>
                        <!-- ################################################## Search Result   - [E N D] ################################################## -->


                        <!-- footer buttons -->
                        <TABLE style="margin-left: 8px; position: relative" border="0" cellpadding="1" cellspacing="1" align="left">
                            <colgroup>
                                <col width="" align="left">
                                <col width="" align="left">
                                <col width="">
                                <col width="126">
                            </colgroup>
                            <tr>
                                <td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" /></td>
                                <td><ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="pBtn6" /></td>
                                <td><ezf:inputButton name="OpenWin_SupplyDemand" value="Supply / Demand" htmlClass="pBtn8" /></td>
                            </tr>
                            <tr>
                                <td><ezf:inputButton name="LineCancel" value="Cancel Line" htmlClass="pBtn7" /></td>
                                <td><ezf:inputButton name="AutoCreatePO" value="Auto Create PO" htmlClass="pBtn8" /></td>
                                <td><ezf:inputButton name="OpenWin_InvInq" value="On Hand Inv" htmlClass="pBtn7" /></td>
                                <td><ezf:inputButton name="OpenWin_InvTrxHist" value="Transaction Hist" htmlClass="pBtn8" /></td>
                                <td></td>
                            </tr>
                        </TABLE>
                        <TABLE style="margin-top: 6px; z-index: 0; position: relative; float: right" border="0" cellpadding="1" cellspacing="1" align="left">
                            <colgroup>
                                <col width="">
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td>
                                        <%-- Pagenation --%>
										<table width="100%">
											<tr align="right">
												<td>
													<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
														<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
														<jsp:param name="TableNm"     value="A" />
														<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
														<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
														<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
													</jsp:include>
												</td>
											</tr>
										</table>
                                    </td>
                                </tr>
                            </tbody>
                        </TABLE>
															</div>
														</c:when>
												<%-- ########## Detail Tab [END] ########## --%>

												<%-- ########## Addl Header Tab [START] ########## --%>
														<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_AddlHeader'}">
															<div id="TabContent-AddlHeader">
																<script type="text/javascript">
																document.getElementById("Detail").className = "pTab_OFF";
																document.getElementById("AddlHeader").className = "pTab_ON";
																</script>
																<table border="0" cellpadding="0" cellspacing="0" style="margin-top:3px;margin-left:10px;">
																	<col width="360">
																	<col width="10">
																	<col width="360">
																	<col width="10">
																	<col width="360">
																	<tr>
																		<td style="vertical-align: top;">
																			<!-- Ship To Location -->
																			<fieldset>
																				<legend><font color="black">Ship To Location</font></legend>
																				<table border="0" cellpadding="0" cellspacing="0" style="margin-top: 5px; margin-left: 5px;">
																					<col width="85">
																					<col width="255">
																					<!-- 1 -->
																					<tr style="height: 24px;">
																						<td class="stab">Additional Name</td>
																						<td><ezf:inputText name="shipToAddlLocNm_HS" ezfName="shipToAddlLocNm_HS" otherAttr=" size=\"35\" maxlength=\"60\" ezftoupper=\"\""/></td>
																					</tr>
																					<!-- 2 -->
																					<tr style="height: 24px;">
																						<td class="stab">Address</td>
																						<td>
																							<ezf:textArea name="xxAllLineAddr_HS" ezfName="xxAllLineAddr_HS" otherAttr=" rows=\"3\" cols=\"33\""/>
																							<ezf:skip>
																							<textarea class="pPro" name="xxAllLineAddr_HS" rows="3" cols="33" ezfname="xxAllLineAddr_HS" >ALLIED PRINTING SERVICES INCORPORATED 1 ALLIED WAY</textarea>
																							</ezf:skip>
																						</td>
																					</tr>
																					<!-- 4 -->
																					<tr style="height: 24px;">
							                                                            <td class="stab"><ezf:anchor name="xxLinkAncr_AL" ezfName="xxLinkAncr_AL" ezfEmulateBtn="1" ezfGuard="OpenWin_PostCd" >Postal Code</ezf:anchor></td>
																						<td>
																						    <ezf:inputText name="shipToPostCd_HS" ezfName="shipToPostCd_HS" value="06042-8933" otherAttr=" size=\"10\" maxlength=\"15\" ezftoupper=\"\""/>
																						    <ezf:inputButton name="GetAddress" value="Get" htmlClass="pBtn2" />
																						</td>
																					</tr>
																					<!-- 5 -->
																					<tr style="height: 24px;">
                                                            							<td class="stab"><ezf:anchor name="xxLinkAncr_AL" ezfName="xxLinkAncr_AL" ezfEmulateBtn="1" ezfGuard="OpenWin_City" >City</ezf:anchor></td>
																						<td><ezf:inputText name="shipToCtyAddr_HS" ezfName="shipToCtyAddr_HS" value="MANCHESTER" otherAttr=" size=\"35\" maxlength=\"25\" ezftoupper=\"\""/></td>
																					</tr>
																					<!-- 6 -->
																					<tr style="height: 24px;">
                                                            						<td class="stab"><ezf:anchor name="xxLinkAncr_AL" ezfName="xxLinkAncr_AL" ezfEmulateBtn="1" ezfGuard="OpenWin_Cnty" >County</ezf:anchor></td>
																						<td><ezf:inputText name="shipToCntyNm_HS" ezfName="shipToCntyNm_HS" otherAttr=" size=\"35\" maxlength=\"30\" ezftoupper=\"\""/></td>
																					</tr>
																					<!-- 7 -->
																					<tr style="height: 24px;">
                                                            							<td class="stab"><ezf:anchor name="xxLinkAncr_AL" ezfName="xxLinkAncr_AL" ezfEmulateBtn="1" ezfGuard="OpenWin_ST" >State</ezf:anchor></td>
																						<td><ezf:inputText name="shipToStCd_HS" ezfName="shipToStCd_HS" value="CT" otherAttr=" size=\"10\" maxlength=\"2\" ezftoupper=\"\""/></td>
																					</tr>
																					<!-- 8 -->
																					<tr style="height: 24px;">
																						<td class="stab">Province</td>
																						<td><ezf:inputText name="shipToProvNm_HS" ezfName="shipToProvNm_HS" otherAttr=" size=\"35\" maxlength=\"25\" ezftoupper=\"\""/></td>
																					</tr>
																					<!-- 9 -->
																					<tr style="height: 24px;">
																						<td class="stab"><ezf:anchor name="xxLinkAncr_AL" ezfName="xxLinkAncr_AL" ezfEmulateBtn="1" ezfGuard="OpenWin_CTRY" >Country</ezf:anchor></td>
																						<td class="stab">
							                                                                <ezf:inputText name="shipToCtryCd_HS" ezfName="shipToCtryCd_HS" value="US" otherAttr=" size=\"5\" maxlength=\"50\" ezftoupper=\"\""/>&nbsp;&nbsp;
																							<ezf:inputText name="ctryNm_HS" ezfName="ctryNm_HS" value="UNITED STATES AMERICA" otherAttr=" size=\"28\" maxlength=\"30\""/>
																						</td>
																					</tr>
																					<!-- 10 -->
																					<!-- 11 -->
																					<tr style="height: 24px;">
																						<td class="stab">Contact Person</td>
																						<td class="stab">
																							<ezf:inputText name="ctacPsnNm_HS" ezfName="ctacPsnNm_HS" value="Kazumichi Narui" otherAttr=" size=\"35\" maxlength=\"90\""/>
																						</td>
																					</tr>
																					<tr style="height: 15px;"><td>&nbsp;</td></tr>
																				</table>
																			</fieldset>
																		</td>
																		<td>&nbsp;</td>
																		<td style="vertical-align: top;">
																			<!-- Bill To Customer -->
																			<fieldset>
																				<legend><font color="black">Bill To Customer</font></legend>
																				<table border="0" cellpadding="0" cellspacing="0" style="margin-top: 5px; margin-left: 5px;">
																					<col width="85">
																					<col width="255">
																					<!-- 1 -->
																					<!-- 2 -->
																					<tr style="height: 24px;">
																						<td class="stab">Name</td>
																						<td><ezf:inputText name="locNm_HB" ezfName="locNm_HB" value="CANON SOLUTIONS AMERICA" otherAttr=" size=\"35\" maxlength=\"60\""/></td>
																					</tr>
																					<!-- 3 -->
																					<tr style="height: 24px;">
																						<td class="stab">Additional Name</td>
																						<td><ezf:inputText name="addlLocNm_HB" ezfName="addlLocNm_HB" otherAttr=" size=\"35\" maxlength=\"60\""/></td>
																					</tr>
																					<!-- 4 -->
																					<tr style="height: 24px;">
																						<td class="stab">Address</td>
																						<td>
																							<ezf:textArea name="xxAllLineAddr_HB" ezfName="xxAllLineAddr_HB" otherAttr=" rows=\"3\" cols=\"33\""/>
																							<ezf:skip>
																							<textarea class="pPro" name="xxAllLineAddr_HB" rows="3" cols="33" ezfname="xxAllLineAddr_HB">300 COMMERCE SQ. DRIVE </textarea>
																							</ezf:skip>
																						</td>
																					</tr>
																					<!-- 5 -->
																					<!-- 6 -->
																					<tr style="height: 24px;">
																						<td class="stab">Post Code</td>
																						<td><ezf:inputText name="postCd_HB" ezfName="postCd_HB" value="08016" otherAttr=" size=\"10\" maxlength=\"15\""/></td>
																					</tr>
																					<!-- 7 -->
																					<tr style="height: 24px;">
																						<td class="stab">City</td>
																						<td><ezf:inputText name="ctyAddr_HB" ezfName="ctyAddr_HB" value="BURLINGTON" otherAttr=" size=\"35\" maxlength=\"30\""/></td>
																					</tr>
																					<!-- 8 -->
																					<tr style="height: 24px;">
																						<td class="stab">County</td>
																						<td><ezf:inputText name="cntyNm_HB" ezfName="cntyNm_HB" otherAttr=" size=\"35\" maxlength=\"30\""/></td>
																					</tr>
																					<!-- 9 -->
																					<tr style="height: 24px;">
																						<td class="stab">State</td>
																						<td><ezf:inputText name="stCd_HB" ezfName="stCd_HB" value="NJ" otherAttr=" size=\"10\" maxlength=\"2\""/></td>
																					</tr>
																					<!-- 10 -->
																					<tr style="height: 24px;">
																						<td class="stab">Province</td>
																						<td><ezf:inputText name="provNm_HB" ezfName="provNm_HB" otherAttr=" size=\"35\" maxlength=\"25\""/></td>
																					</tr>
																					<!-- 11 -->
																					<tr style="height: 24px;">
																						<td class="stab">Country</td>
																						<td class="stab">
																							<ezf:inputText name="ctryNm_HB" ezfName="ctryNm_HB" value="UNITED STATES AMERICA" otherAttr=" size=\"35\" maxlength=\"30\""/>
																						</td>
																					</tr>
																					<tr style="height: 24px;"><td>&nbsp;</td></tr>
																					<tr style="height: 15px;"><td>&nbsp;</td></tr>
																				</table>
																			</fieldset>
																		</td>
																		<td>&nbsp;</td>
																		<td style="vertical-align: top;">
																			<!-- Freight Information -->
																			<fieldset>
																				<legend><font color="black">Freight Information</font></legend>
																				<table border="0" cellpadding="0" cellspacing="0" style="margin-top: 5px; margin-left: 5px;">
																					<col width="85">
																					<col width="255">
																					<!-- 1 -->
																					<tr style="height: 24px;">
																						<td class="stab">Freight Term</td>
																						<td>
																							<ezf:select name="frtCondCd_SL" ezfName="frtCondCd_SL" ezfBlank="1" ezfCodeName="frtCondCd_PD" ezfDispName="frtCondDescTxt_PD" otherAttr=" style=\"width:252\""/>
																						</td>
																					</tr>
																					<!-- 2 -->
																					<tr style="height: 24px;">
																						<td class="stab">Service Level</td>
																						<td>
																							<ezf:select name="shpgSvcLvlCd_SL" ezfName="shpgSvcLvlCd_SL" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_PD" ezfDispName="shpgSvcLvlDescTxt_PD" otherAttr=" style=\"width:252\""/>
																						</td>
																					</tr>
																					<!-- 3 -->
																					<tr style="height: 24px;">
																						<td class="stab"><ezf:anchor name="xxLinkAncr_A5" ezfName="xxLinkAncr_A5" ezfEmulateBtn="1" ezfGuard="OpenWin_Carrier" >Carrier</ezf:anchor></td>
																						<td>
																							<ezf:inputText name="carrCd_HF" ezfName="carrCd_HF" value="UPSN" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/>
																							<ezf:inputText name="locNm_HF" ezfName="locNm_HF" value="1232423" otherAttr=" size=\"25\" maxlength=\"60\" ezftoupper=\"\""/>
																						</td>
																						</td>
																					</tr>
																					<!-- 4 -->
																					<tr style="height: 24px;">
																						<td class="stab">Carrier Account</td>
																						<td><ezf:inputText name="carrAcctNum_HF" ezfName="carrAcctNum_HF" value="1232423" otherAttr=" size=\"35\" maxlength=\"20\" ezftoupper=\"\""/></td>
																					</tr>
																				</table>
																			</fieldset>
																			<%--
																			<!-- Header Details -->
																			<br/>
																			<fieldset>
																				<legend><font color="black">CSA Return Address</font></legend>
																				<table border="0" cellpadding="0" cellspacing="0" style="margin-top: 5px; margin-left: 5px;">
																					<col width="85">
																					<col width="255">
																					<!-- 1 -->
																					<tr style="height: 24px;">
																						<td class="stab">Name</td>
																						<td><ezf:inputText name="rtlWhNm_HC" ezfName="rtlWhNm_HC" value="CSA SAN PEDRO Warehouse" otherAttr=" size=\"35\" maxlength=\"30\""/></td>
																					</tr>
																					<tr style="height: 24px;">
																						<td class="stab">Address</td>
																						<td>
																							<ezf:textArea name="xxAllLineAddr_HC" ezfName="xxAllLineAddr_HC"/>
																							<ezf:skip>
																							<textarea class="pPro" name="xxAllLineAddr_HC" rows="3" cols="33" ezfname="xxAllLineAddr_HC">123 MAIN STREET SAN PEDRO CA 12345</textarea>
																							</ezf:skip>
																						</td>
																					</tr>
																					<!-- 7 -->
																					<tr style="height: 24px;">
																						<td class="stab">City</td>
																						<td><ezf:inputText name="ctyAddr_HC" ezfName="ctyAddr_HC" value="BURLINGTON" otherAttr=" size=\"35\" maxlength=\"25\""/></td>
																					</tr>
																					<!-- 9 -->
																					<tr style="height: 24px;">
																						<td class="stab">State</td>
																						<td><ezf:inputText name="stCd_HC" ezfName="stCd_HC" value="NJ" otherAttr=" size=\"10\" maxlength=\"2\""/></td>
																					</tr>
																					<tr style="height: 24px;">
																						<td class="stab">Post Code</td>
																						<td><ezf:inputText name="postCd_HC" ezfName="postCd_HC" value="08016" otherAttr=" size=\"10\" maxlength=\"15\""/></td>
																					</tr>
																					<tr style="height: 4px;"><td></td></tr>
																				</table>
																			</fieldset>
                                                                            --%>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="3">
																			<!-- PO Release -->
																			<fieldset>
																				<legend><font color="black">PO Release</font></legend>
																				<table border="0" cellpadding="0" cellspacing="0" style="margin-top: 5px; margin-left: 5px; margin-bottom: 4px;">
																					<col width="124">
																					<col width="100">
																					<col width="250">
																					<col width="">
																					<tr style="height: 24px;">
																						<td class="stab">PO Release Date</td>
																						<td colspan="3">
																							<ezf:inputText name="xxDtTm_HP" ezfName="xxDtTm_HP" value="05/15/2015 01:10 PM" otherAttr=" maxlength=\"10\" size=\"20\""/>
																						</td>
																					</tr>
																					<tr style="height: 24px;">
																						<td class="stab">Error Message</td>
																						<td colspan="3">
																							<ezf:select name="prchReqRelErrMsgTxt_SL" ezfName="prchReqRelErrMsgTxt_SL" ezfCodeName="prchReqRelErrMsgTxt_P0" ezfDispName="prchReqRelErrMsgTxt_P1" otherAttr=" style=\"width:580\""/>
																						</td>
																					</tr>
																				</table>
																			</fieldset>
																		</td>
																	</tr>
																</table>
															</div>
														</c:when>
														<%-- ########## Addl Header Tab [END] ########## --%>
													</c:choose>
													</div>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</center>
<div style="display:none;">
	<ezf:inputButton name="btn11" value="btn11" otherAttr=" id=\"btn11\""/>
</div>
<%-- ###SCRIPT --%>
<script type="text/javascript">
             setKeyDownHandler();

	function setKeyDownHandler() {

		if( window.addEventListener ) {
		    window.addEventListener("keyup", emulateFuncKeyDown, false);
		} else if( document.attachEvent ) {
		    document.attachEvent("onkeyup", emulateFuncKeyDown);
		} else {
		    document.onkeyup = emulateFuncKeyDown;
		}
	}

	function emulateFuncKeyDown() {

		var code = event.keyCode;
		//alert("keyCode:["+event.keyCode+"]");

		if(event.keyCode == 122 ) {
			event.keyCode = null;
			event.returnValue = false;
		}

		switch(code) {
		// F11
		case 122:
			//sendServer("Line_Add");
			emulateOnClickEvent("btn11");
			break;
		default:
			break;
		}
		return;
	}

	function emulateOnClickEvent(elementId) {

		var elem = document.getElementById(elementId);
		if( /*@cc_on ! @*/ false ) {
			elem.fireEvent("onclick");
		} else {
			var evt = document.createEvent("MouseEvents");
			evt.initEvent("click", false, true);
			elem.dispatchEvent(evt);
		}
	}


</script>


<%-- **** Task specific area ends here **** --%>
