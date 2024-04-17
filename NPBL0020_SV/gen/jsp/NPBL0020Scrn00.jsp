<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230703094107 --%>
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
			<input type="hidden" name="pageID" value="NPBL0020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Inventory Request Entry">

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
                                            <td width="107px" align="center" class="same">Invty Req Entry</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </ezf:skip>

                    <div class="pTab_BODY">
                        <!-- ################################################## Header - [START] ################################################## -->
						<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
							<col width=""  align="left">
							<tr valign="top">
								<td>
									<table border="0" cellpadding="0" cellspacing="0" >
										<col width="100">
										<col width="115">
										<col width="60">
										<col width="15">
										<col width="125">
										<col width="225">
										<col width="15">
										<col width="110">
										<col width="80">
										<col width="35">
										<col width="30">
										<!-- 1 -->
										<tr height="20">
											<td class="stab">Request Number</td>
											<td><ezf:inputText name="prchReqNum" ezfName="prchReqNum" value="12345678" otherAttr=" size=\"19\" maxlength=\"8\" ezftoupper=\"\""/></td>
											<td style="text-align:right;"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" /></td>
											<td></td>
											<td class="stab">Document Source Type</td>
											<td><ezf:inputText name="prchReqSrcTpDescTxt" ezfName="prchReqSrcTpDescTxt" value="Min-Max for WH" otherAttr=" size=\"31\" maxlength=\"50\" ezftoupper=\"\""/></td>
											<td></td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_SW" ezfName="xxLinkAncr_SW" ezfEmulateBtn="1" ezfGuard="OpenWin_SrcWH" >Source WH</ezf:anchor></td>
											<td><ezf:inputText name="srcRtlWhCd" ezfName="srcRtlWhCd" value="1Z1" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputButton name="Get_SrcWhH" value=">>" htmlClass="pBtn0" /></td>
											<td><ezf:inputText name="rtlWhNm_SW" ezfName="rtlWhNm_SW" value="NORCROSS WH - CSA" otherAttr=" size=\"28\" maxlength=\"30\" ezftoupper=\"\""/></td>
										</tr>
										<!-- 2 -->
										<tr height="20">
											<td class="stab">Request Type</td>
											<td>
												<ezf:select name="prchReqTpCd_SL" ezfName="prchReqTpCd_SL" ezfBlank="1" ezfCodeName="prchReqTpCd_PD" ezfDispName="prchReqTpDescTxt_PD" otherEvent1=" onchange=\"sendServer('OnChange_PRType')\"" otherAttr=" style=\"width:136\""/>
											</td>
											<td style="text-align:right;"></td>
											<td></td>
											<td class="stab">Source Doc#</td>
											<td><ezf:inputText name="trxRefNum" ezfName="trxRefNum" otherAttr=" size=\"31\" maxlength=\"15\" ezftoupper=\"\""/></td>
											<td></td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_SS" ezfName="xxLinkAncr_SS" ezfEmulateBtn="1" ezfGuard="OpenWin_SrcSWH" >Source SWH</ezf:anchor></td>
											<td><ezf:inputText name="srcRtlSwhCd" ezfName="srcRtlSwhCd" value="New" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputButton name="Get_SrcSwhH" value=">>" htmlClass="pBtn0" /></td>
											<td><ezf:inputText name="rtlSwhNm_SS" ezfName="rtlSwhNm_SS" value="NEW" otherAttr=" size=\"28\" maxlength=\"30\" ezftoupper=\"\""/></td>
										</tr>
										<!-- 3 -->
										<tr height="20">
											<td class="stab">Header Status</td>
											<td colspan="2"><ezf:inputText name="prchReqStsDescTxt" ezfName="prchReqStsDescTxt" value="Open" otherAttr=" size=\"19\" maxlength=\"50\" ezftoupper=\"\""/></td>
											<td></td>
											<td class="stab">Plan Name</td>
											<td><ezf:inputText name="mrpPlnNm" ezfName="mrpPlnNm" value="Min Max Plan 1" otherAttr=" size=\"31\" maxlength=\"70\" ezftoupper=\"\""/></td>
											<td></td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_DW" ezfName="xxLinkAncr_DW" ezfEmulateBtn="1" ezfGuard="OpenWin_DestWH" >Destination WH</ezf:anchor></td>
											<td><ezf:inputText name="destRtlWhCd" ezfName="destRtlWhCd" value="2Z1" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputButton name="Get_DestWhH" value=">>" htmlClass="pBtn0" /></td>
											<td><ezf:inputText name="rtlWhNm_DW" ezfName="rtlWhNm_DW" value="MONROE WH - CSA" otherAttr=" size=\"28\" maxlength=\"30\" ezftoupper=\"\""/></td>
										</tr>
										<!-- 4 -->
										<tr height="20">
											<td class="stab">Approval Status</td>
											<td colspan="2"><ezf:inputText name="prchReqApvlStsDescTxt" ezfName="prchReqApvlStsDescTxt" value="Approved" otherAttr=" size=\"19\" maxlength=\"50\" ezftoupper=\"\""/></td>
											<td></td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_RL" ezfName="xxLinkAncr_RL" ezfEmulateBtn="1" ezfGuard="OpenWin_Requester" >Requester</ezf:anchor></td>
											<td><ezf:inputText name="fullPsnNm" ezfName="fullPsnNm" value="Caruros" otherAttr=" size=\"31\" maxlength=\"62\""/></td>
											<td></td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_DS" ezfName="xxLinkAncr_DS" ezfEmulateBtn="1" ezfGuard="OpenWin_DestSWH" >Destination SWH</ezf:anchor></td>
											<td><ezf:inputText name="destRtlSwhCd" ezfName="destRtlSwhCd" value="NEW" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputButton name="Get_DestSwhH" value=">>" htmlClass="pBtn0" /></td>
											<td><ezf:inputText name="rtlSwhNm_DS" ezfName="rtlSwhNm_DS" value="NEW" otherAttr=" size=\"28\" maxlength=\"30\" ezftoupper=\"\""/></td>
										</tr>
										<!-- 5 -->
										<tr height="20">
											<td class="stab">Date Created</td>
											<td><ezf:inputText name="prchReqCratDt" ezfName="prchReqCratDt" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\""/></td>
											<td ></td>
											<td></td>
											<td class="stab">Service Level</td>
											<td>
												<ezf:select name="shpgSvcLvlCd_SL" ezfName="shpgSvcLvlCd_SL" ezfCodeName="shpgSvcLvlCd_PD" ezfDispName="shpgSvcLvlDescTxt_PD" otherAttr=" style=\"width:225\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_SL" ezfName="xxLinkAncr_SL" ezfEmulateBtn="1" ezfGuard="OpenWin_SupplierSite" >Ship To Supplier</ezf:anchor></td>
											<td><ezf:inputText name="vndCd" ezfName="vndCd" value="235" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputButton name="Get_ShipToSplyH" value=">>" htmlClass="pBtn0" /></td>
											<td><ezf:inputText name="dplyVndNm" ezfName="dplyVndNm" value="CANON USA NP / CLC DIVISION" otherAttr=" size=\"28\" maxlength=\"320\" ezftoupper=\"\""/></td>
										</tr>
										<!-- 6 -->
										<tr height="20">
											<td class="stab">Need By Date</td>
											<td class="stab"><ezf:inputText name="rqstRcvDt" ezfName="rqstRcvDt" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\""/>
												<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt', 4);"/>
											<td></td>
											<td></td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_CL" ezfName="xxLinkAncr_CL" ezfEmulateBtn="1" ezfGuard="OpenWin_Carrier" >Carrier</ezf:anchor></td>
											<td><ezf:inputText name="carrNm" ezfName="carrNm" value="UPS" otherAttr=" size=\"31\" maxlength=\"60\" ezftoupper=\"\""/></td>
											<td></td>
											<td class="stab">
												<ezf:anchor name="xxLinkAncr_OS" ezfName="xxLinkAncr_OS" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCustH" >Ship To Customer</ezf:anchor>
											</td>
											<td>
												<ezf:inputText name="shipToCustCd_EO" ezfName="shipToCustCd_EO" value="5127470" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
											</td>
											<td><ezf:inputButton name="Get_ShipToCustH" value=">>" htmlClass="pBtn0" /></td>
											<td><ezf:inputText name="shipToLocNm_EO" ezfName="shipToLocNm_EO" value="EXP01-BURLINGTON" otherAttr=" size=\"28\" maxlength=\"60\" ezftoupper=\"\""/></td>
										</tr>
										<!-- 6 -->
										<tr height="20">
											<td class="stab">Description</td>
											<td colspan="2"><ezf:inputText name="prchReqCmntTxt" ezfName="prchReqCmntTxt" otherAttr=" size=\"27\" maxlength=\"70\" ezftoupper=\"\""/></td>
											<td></td>
											<td class="stab">Attention</td>
											<td><ezf:inputText name="ctacPsnNm" ezfName="ctacPsnNm" otherAttr=" size=\"31\" maxlength=\"90\" ezftoupper=\"\""/></td>
											<td></td>
											<td style="text-align:right;" colspan="5">
												<ezf:inputButton name="Copy" value="Copy" htmlClass="pBtn6" />
												<ezf:inputButton name="HeaderCancel" value="Cancel" htmlClass="pBtn6" />
												<ezf:inputButton name="HeaderClose" value="Close" htmlClass="pBtn6" />
												<ezf:inputButton name="MoveTo_ApprovalHist" value="Apprvl Hist" htmlClass="pBtn6" />
												<ezf:inputButton name="Attachments" value="Attachments" htmlClass="pBtn6" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
                        <!-- ################################################## Header - [E N D] ################################################## -->

                        <hr>
												<%-- #### TAB - HEAD #### --%>
												<div class="pTab_HEAD">
													<ul>
														<table border="0" cellpadding="0" cellspacing="0" width="900px">
															<tr>
																<td width="96%">
																	<li id="Detail" title="Detail" class="pTab_ON">
																		<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TAB_Detail" >Detail</ezf:anchor>
																	</li>
																	<li id="Header" title="Header" class="pTab_OFF">
																		<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TAB_Header" >Header</ezf:anchor>
																	</li>
																</td>
															</tr>
														</table>
													</ul>
												</div>
												<div class="pTab_BODY_In">
													<div style="height: 380px" >
													
													<c:choose>
														<%-- ########## Header Tab [START] ########## --%>
														<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Header'}">
															<div id="TabContent-Header">
																<script type="text/javascript">
																document.getElementById("Header").className = "pTab_ON";
																document.getElementById("Detail").className = "pTab_OFF";
																</script>
																<table border="0" cellpadding="0" cellspacing="0" style="margin-top:3px;margin-left:10px;">
																	<col width="360">
																	<col width="10">
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
																						<td class="stab">Name</td>
																						<td><ezf:inputText name="shipToLocNm" ezfName="shipToLocNm" value="MONROE WH - CSA" otherAttr=" size=\"35\" maxlength=\"60\" ezftoupper=\"\""/></td>
																					</tr>
																					<tr style="height: 24px;">
																						<td class="stab">Additional Name</td>
																						<td><ezf:inputText name="shipToAddlLocNm" ezfName="shipToAddlLocNm" otherAttr=" size=\"35\" maxlength=\"60\" ezftoupper=\"\""/></td>
																					</tr>
																					<!-- 2 -->
																					<tr style="height: 24px;">
																						<td class="stab">Address</td>
																						<td>
																							<ezf:textArea name="xxShipVndAddr" ezfName="xxShipVndAddr" otherAttr=" rows=\"2\" cols=\"33\""/>
																						</td>
																					</tr>
																					<!-- 4 -->
																					<tr style="height: 24px;">
																						<td class="stab"><ezf:anchor name="xxLinkAncr_AD" ezfName="xxLinkAncr_AD" ezfEmulateBtn="1" ezfGuard="OpenWin_PostCd" >Postal Code</ezf:anchor></td>
																						<td>
																						<ezf:inputText name="shipToPostCd" ezfName="shipToPostCd" value="08831" otherAttr=" size=\"10\" maxlength=\"15\" ezftoupper=\"\""/>
																						<ezf:inputButton name="GetAddress" value="Get" htmlClass="pBtn2" />
																						</td>
																					</tr>
																					<!-- 5 -->
																					<tr style="height: 24px;">
							                                                            <td class="stab"><ezf:anchor name="xxLinkAncr_AD" ezfName="xxLinkAncr_AD" ezfEmulateBtn="1" ezfGuard="OpenWin_City" >City</ezf:anchor></td>
																						<td><ezf:inputText name="shipToCtyAddr" ezfName="shipToCtyAddr" value="Monroe Township" otherAttr=" size=\"35\" maxlength=\"25\" ezftoupper=\"\""/></td>
																					</tr>
																					<!-- 6 -->
																					<tr style="height: 24px;">
                                                            							<td class="stab"><ezf:anchor name="xxLinkAncr_AD" ezfName="xxLinkAncr_AD" ezfEmulateBtn="1" ezfGuard="OpenWin_Cnty" >County</ezf:anchor></td>
																						<td><ezf:inputText name="shipToCntyNm" ezfName="shipToCntyNm" otherAttr=" size=\"35\" maxlength=\"30\" ezftoupper=\"\""/></td>
																					</tr>
																					<!-- 7 -->
																					<tr style="height: 24px;">
                                                            							<td class="stab"><ezf:anchor name="xxLinkAncr_AD" ezfName="xxLinkAncr_AD" ezfEmulateBtn="1" ezfGuard="OpenWin_ST" >State</ezf:anchor></td>
																						<td><ezf:inputText name="shipToStCd" ezfName="shipToStCd" value="NJ" otherAttr=" size=\"10\" maxlength=\"2\" ezftoupper=\"\""/></td>
																					</tr>
																					<!-- 8 -->
																					<tr style="height: 24px;">
																						<td class="stab">Province</td>
																						<td><ezf:inputText name="shipToProvNm" ezfName="shipToProvNm" otherAttr=" size=\"35\" maxlength=\"25\" ezftoupper=\"\""/></td>
																					</tr>
																					<!-- 9 -->
																					<tr style="height: 24px;">
                                                            							<td class="stab"><ezf:anchor name="xxLinkAncr_CT" ezfName="xxLinkAncr_CT" ezfEmulateBtn="1" ezfGuard="OpenWin_CTRY" >Country</ezf:anchor></td>
																						<td class="stab">
                                                                							<ezf:inputText name="shipToCtryCd" ezfName="shipToCtryCd" value="US" otherAttr=" size=\"5\" maxlength=\"50\" ezftoupper=\"\""/>&nbsp;&nbsp;
																							<ezf:inputText name="ctryNm" ezfName="ctryNm" value="UNITED STATES AMERICA" otherAttr=" size=\"28\" maxlength=\"50\" ezftoupper=\"\""/>
																						</td>
																					</tr>
																					<!-- 10 -->
																					<tr style="height: 15px;"><td>&nbsp;</td></tr>
																				</table>
																			</fieldset>
																		</td>
																		<td>
																		</td>
																		<td style="vertical-align: top; text-align: left;">
																			<!-- Header Details -->
																			<fieldset>
																				<legend><font color="black">Vendor Return</font></legend>
																				<table border="0" cellpadding="0" cellspacing="0" style="margin-top: 5px; margin-left: 5px;">
																					<col width="85">
																					<col width="255">
																					<!-- 1 -->
																					<tr style="height: 24px;">
																						<td class="stab">Return Reason</td>
																						<td>
																							<ezf:select name="vndRtrnRsnCd_SL" ezfName="vndRtrnRsnCd_SL" ezfBlank="1" ezfCodeName="vndRtrnRsnCd_PD" ezfDispName="vndRtrnRsnDescTxt_PD" otherAttr=" style=\"width:255\""/>
																						</td>
																					</tr>
																				</table>
																			</fieldset>
																		</td>
																		<td>&nbsp;</td>
																	</tr>
																</table>
															</div>
														</c:when>
														<%-- ########## Header Tab [END] ########## --%>

													
														<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Detail'}">
															<div id="TabContent-Detail">
																<script type="text/javascript">
																	document.getElementById("Header").className = "pTab_OFF";
																	document.getElementById("Detail").className = "pTab_ON";
																</script>

														<%-- ########## Detail Tab [START] ########## --%>

                                                                <!-- ######################################## Add - [S T A R T] #################################### -->
                                                                <table style="MARGIN-LEFT: 8px; MARGIN-TOP: 0px" border="0" width="1088">
                                                                    <tr>
																		<td align="left" width="160">
                                                                            <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
																		</td>
																		<td>
							                                                <ezf:inputButton name="Add_NewLine" value="Add New Line (F11)" htmlClass="pBtn10" />
							                                                <ezf:inputButton name="Add_NewConfig" value="Add New Config" htmlClass="pBtn9" />
							                                            </td>
							                                            <td>
																		</td>
                                                                        <td class="stab" align="left">
																			<ezf:anchor name="xxLinkAncr_SC" ezfName="xxLinkAncr_SC" ezfEmulateBtn="1" ezfGuard="OpenWin_ConfigID" >Select from CONFIG</ezf:anchor> &nbsp;
                                                                        	<ezf:inputText name="svcConfigMstrPk" ezfName="svcConfigMstrPk" value="----+----1" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/>
                                                                        	<ezf:inputButton name="Add_ExistingConfig" value="Add" htmlClass="pBtn5" />
																		</td>
                                                                        <td align="right">Total Cost &nbsp;
																			<ezf:inputText name="xxTotAmt" ezfName="xxTotAmt" value="9,999,999,999.99" otherAttr=" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                </table>
                                                                <!-- ################################################## Add - [E N D] ################################################## -->

                                                                <!-- ################################################## Detail   - [START] ################################################## -->
                                                                <div id="parentBoxA">
                                                                <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                                                                    <tr>
                                                                        <td align="left" valign="top">
								                                        <div style="float:left; display:block"> <!-- left table -->
								                                            <div id='leftTblHead' style="display:block;">
								                                            </div>
								                                            <div id='leftTbl' style="float:left; display:block; height:270px; overflow:hidden; margin-left: 0px; padding:0px;">
								                                            </div>
								                                        </div>  <!-- left table -->
								                                        <div style="float:left"> <!-- right table -->
								                                            <div id='rightTblHead' style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
                                                                            <%-- ******************************** --%>
                                                                            <%-- *** Right Table Area(Header) *** --%>
                                                                            <%-- ******************************** --%>
                                                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="28" id="AHEAD" width="2136px" style="margin-right:20px" >
	                                                                                <col width="40"  align="center"><!-- check box -->
	                                                                                <col width="46"  align="center"><!-- Line# -->
	                                                                                <col width="95" align="center"><!-- Config Type-->
	                                                                                <col width="100"  align="center"><!-- Line Type -->
	                                                                                <col width="190" align="center"><!-- Item# -->
                                                                                    <col width="150" align="center"><!-- Item Description -->
                                                                                    <col width="120" align="center"><!-- Config -->
                                                                                    <col width="108" align="center"><!-- Transfer Qty -->
                                                                                    <col width="108" align="center"><!-- Delivered Qty -->
                                                                                    <col width="150" align="center"><!-- Source Warehouse Name -->
                                                                                    <col width="125" align="center"><!-- Source SWH Name -->
                                                                                    <col width="180" align="center"><!-- Stock status -->
                                                                                    <col width="150" align="center"><!-- Destination Warehouse Name -->
                                                                                    <col width="125" align="center"><!-- Destination SWH Name -->
                                                                                    <col width="150" align="center"><!-- Ship To Supplier -->
                                                                                    <col width="190" align="center"><!-- Ship To Customer -->
                                                                                    <col width="120" align="center"><!-- Status -->
                                                                                    <col width="120" align="center"><!-- Unit Price -->
                                                                                    <col width="120" align="center"><!-- Total Cost -->
                                                                                    <col width="350" align="center"><!-- Charge Account -->
                                                                                    <col width="46"  align="center"><!-- MT -->
                                                                                    <col width="46"  align="center"><!-- PC -->
                                                                                    <col width="155"  align="center"><!-- Serial -->
                                                                                    <col width="76"  align="center"><!-- Source Line# -->
                                                                                    <col width="76"  align="center"><!-- CPO# -->
                                                                                    <col width="76"  align="center"><!-- SO# -->
                                                                                    <col width="76"  align="center"><!-- RWS# -->
                                                                                    <col width="200" align="center"><!-- Text -->
                                                                                    <col width="70" align="center"><!-- Ship To -->
                                                                                    <tr>
	                                                                                    <td class="pClothBs colFix" id="AH0" class="pClothBs"></td>
	                                                                                    <td class="pClothBs colFix" id="AH1" class="pClothBs">Line#</td>
	                                                                                    <td id="AH2" class="pClothBs">Config Type</td>
	                                                                                    <td id="AH3" class="pClothBs">Line Type</td>
	                                                                                    <td id="AH4" class="pClothBs">Item#</td>
                                                                                        <td id="AH5" class="pClothBs">Item Description</td>
                                                                                        <td id="AH6" class="pClothBs">Config#</td>
                                                                                        <td id="AH7" class="pClothBs">Transfer Qty</td>
                                                                                        <td id="AH8" class="pClothBs">Delivered Qty</td>
                                                                                        <td id="AH9" class="pClothBs">Source WH</td>
                                                                                        <td id="AH10" class="pClothBs">Source SWH</td>
                                                                                        <td id="AH11" class="pClothBs">Stock Status</td>
                                                                                        <td id="AH12" class="pClothBs">Dest WH</td>
                                                                                        <td id="AH13" class="pClothBs">Dest SWH</td>
                                                                                        <td id="AH14" class="pClothBs">Ship To Supplier</td>
                                                                                        <td id="AH15" class="pClothBs">Ship To Customer</td>
                                                                                        <td id="AH16" class="pClothBs">Status</td>
                                                                                        <td id="AH17" class="pClothBs">Unit Price</td>
                                                                                        <td id="AH18" class="pClothBs">Total Cost</td>
                                                                                        <td id="AH19" class="pClothBs">Charge Account</td>
                                                                                        <td id="AH20" class="pClothBs">MT</td>
                                                                                        <td id="AH21" class="pClothBs">PC</td>
                                                                                        <td id="AH22" class="pClothBs">Serial</td>
                                                                                        <td id="AH23" class="pClothBs">Source Line#</td>
                                                                                        <td id="AH24" class="pClothBs">CPO#</td>
                                                                                        <td id="AH25" class="pClothBs">SO#</td>
                                                                                        <td id="AH26" class="pClothBs">RWS#</td>
                                                                                        <td id="AH27" class="pClothBs">Text</td>
                                                                                        <td id="AH28" class="pClothBs">Ship To</td>
                                                                                    </tr>
                                                                                </table>
                                                                            </div> <!-- rightTblHead -->
                                                                            <%-- ******************************** --%>
                                                                            <%-- *** Right Table Area(Detail) *** --%>
                                                                            <%-- ******************************** --%>
                                                                            <div  id="rightTbl" style="width:1117px; height:270px; display:block; overflow:scroll; margin:0px; padding:0px;" >
                                                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="2136px" >
                                                                                    <col width="40"  align="center"><!-- check box -->
                                                                                    <col width="46"  align="left"><!-- Line# -->
                                                                                    <col width="95" align="center"><!-- Config Type-->
                                                                                    <col width="100"  align="left"><!-- Line Type -->
                                                                                    <col width="190" align="left"><!-- Item# -->
                                                                                    <col width="150" align="center"><!-- Item Description -->
                                                                                    <col width="120" align="center"><!-- Config -->
                                                                                    <col width="108" align="center"><!-- Transfer Qty -->
                                                                                    <col width="108" align="center"><!-- Delivered Qty -->
                                                                                    <col width="150" align="left"><!-- Source Warehouse Name -->
                                                                                    <col width="125" align="left"><!-- Source SWH Name -->
                                                                                    <col width="180" align="center"><!-- Stock status -->
                                                                                    <col width="150" align="left"><!-- Destination Warehouse Name -->
                                                                                    <col width="125" align="left"><!-- Destination SWH Name -->
                                                                                    <col width="150" align="left"><!-- Ship To Supplier -->
                                                                                    <col width="190" align="left"><!-- Ship To Customer -->
                                                                                    <col width="120" align="left"><!-- Status -->
                                                                                    <col width="120" align="right"><!-- Unit Price -->
                                                                                    <col width="120" align="right"><!-- Total Cost -->
                                                                                    <col width="350" align="left"><!-- Charge Account -->
                                                                                    <col width="46"  align="left"><!-- MT -->
                                                                                    <col width="46"  align="left"><!-- PC -->
                                                                                    <col width="155"  align="left"><!-- Serial -->
                                                                                    <col width="76"  align="left"><!-- Source Line# -->
                                                                                    <col width="76"  align="left"><!-- CPO# -->
                                                                                    <col width="76"  align="left"><!-- SO# -->
                                                                                    <col width="76"  align="left"><!-- RWS# -->
                                                                                    <col width="200" align="left"><!-- Text -->
                                                                                    <col width="70" align="center"><!-- Ship To -->
                                                                                    <ezf:row ezfHyo="A">
                                                                                        <tr height="28">
                                                                                            <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/></td>
                                                                                            <td><ezf:label name="xxScrItem20Txt_A1" ezfName="xxScrItem20Txt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                            <td><ezf:inputText name="configTpDescTxt_A1" ezfName="configTpDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\""/></td>
																							<td>
		                                                                                        <ezf:select name="prchReqLineTpCd_A1" ezfName="prchReqLineTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prchReqLineTpCd_PD" ezfDispName="prchReqLineTpDescTxt_PD" otherAttr=" style=\"width:95\""/>
																							</td>
		                                                                                    <td class="stab">
		                                                                                    	<ezf:inputButton name="OpenWin_Item" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
		                                                                                    	<ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
										                                                        <ezf:inputButton name="GetItemInfo" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"GetItemInfo#{EZF_ROW_NUMBER}\""/>
										                                                    </td>
                                                                                            <td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\" ezftoupper=\"\""/></td>
                                                                                            <td><ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
                                                                                            <td><ezf:inputText name="prchReqDispQty_A1" ezfName="prchReqDispQty_A1" value="&nbsp;" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"13\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                                                            <td><ezf:inputText name="shipQty_A1" ezfName="shipQty_A1" value="&nbsp;" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"13\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                                                            <td class="stab">
                                                                                            	<ezf:inputButton name="OpenWin_SrcWHForLine" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
																								<ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
																							</td>
                                                                                            <td>
                                                                                            	<ezf:inputButton name="OpenWin_SrcSWH" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                                                            	<ezf:inputText name="srcRtlSwhCd_A1" ezfName="srcRtlSwhCd_A1" value="----+----1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
                                                                                            </td>
                                                                                            <td>
																								<ezf:select name="fromStkStsCd_A1" ezfName="fromStkStsCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="fromStkStsCd_PD" ezfDispName="stkStsDescTxt_PD" otherAttr=" style=\"width:180px\""/>
																							</td>
                                                                                            <td class="stab">
                                                                                            	<ezf:inputButton name="OpenWin_DestWHForLine" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
																								<ezf:inputText name="rtlWhNm_A2" ezfName="rtlWhNm_A2" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
																							</td>
                                                                                            <td>
                                                                                            	<ezf:inputButton name="OpenWin_DestSWH" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                                                            	<ezf:inputText name="destRtlSwhCd_A1" ezfName="destRtlSwhCd_A1" value="----+----1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
                                                                                            </td>
                                                                                            <td><ezf:inputText name="dplyVndNm_A1" ezfName="dplyVndNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"320\" ezftoupper=\"\""/></td>
                                                                                            <td>
                                                                                            	<ezf:inputButton name="OpenWin_ShipToCustD" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                                                            	<ezf:inputText name="shipToLocNm_E1" ezfName="shipToLocNm_E1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/>
                                                                                            </td>
                                                                                            <td><ezf:inputText name="prchReqLineStsDescTxt_A1" ezfName="prchReqLineStsDescTxt_A1" value="Open" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                            <td><ezf:inputText name="entDealNetUnitPrcAmt_A1" ezfName="entDealNetUnitPrcAmt_A1" value="8715.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" style=\"text-align: right;\""/></td>
                                                                                            <td><ezf:label name="entPoDtlDealNetAmt_A1" ezfName="entPoDtlDealNetAmt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\""/></td>
                                                                                            <td class="stab">
                                                                                            	<ezf:inputButton name="OpenWin_Account" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
																								<ezf:inputText name="xxScrItem50Txt_A1" ezfName="xxScrItem50Txt_A1" value="800.101.211.11201.14016.00.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"43\" maxlength=\"50\" ezftoupper=\"\""/>
                                                                                            </td>
                                                                                            <td><ezf:label name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                            <td><ezf:label name="coaProdCd_A1" ezfName="coaProdCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                            <td class="stab">
																								<ezf:inputText name="xxLogDtlTxt_A1" ezfName="xxLogDtlTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"10000\" ezftoupper=\"\""/>
										                                                    	<ezf:inputButton name="OpenWin_SerEnt" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                                                            </td>
                                                                                            <td><ezf:label name="trxRefLineNum_A1" ezfName="trxRefLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                            <td>
  												                                                <ezf:anchor name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_CPO', '{EZF_ROW_NUMBER}" otherAttr=" ezfanchortext=\"\"">
  												                                                <ezf:label name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"cursor: pointer\""/>
  												                                                </ezf:anchor>
  												                                            </td>
                                                                                            <td><ezf:label name="soNum_A1" ezfName="soNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                            <td><ezf:label name="rwsNum_A1" ezfName="rwsNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                            <td><ezf:inputText name="prchReqLineCmntTxt_A1" ezfName="prchReqLineCmntTxt_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"240\" ezftoupper=\"\""/></td>
                                                                                            <td><ezf:inputButton name="OpenWin_ShipToAddr" value="Address" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn5" /></td>
                                                                                        </tr>
                                                                                    </ezf:row>
                                                                                </table>
                                                                            </div> <!-- rightTbl -->
                                                                        </div> <!-- right table -->
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                                </div> <!-- parentBoxA -->
                                                                <!-- ################################################## Search Result   - [E N D] ################################################## -->

                                                                <!-- footer buttons -->
                                                                <table style="margin-left: 8px; margin-top: 6px; z-index: 2; position: relative" border="0" cellpadding="1" cellspacing="1" align="left">
                                                                    <colgroup>
                                                                        <col width="">
                                                                        <col width="">
                                                                        <col width="">
                                                                        <col width="">
                                                                        <col width="">
                                                                        <col width="">
                                                                    </colgroup>
                                                                    <tr>
                                                                        <td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" /></td>
                                                                        <td><ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="pBtn6" /></td>
                                                                        <td><ezf:inputButton name="MoveTo_KittingWOEntry" value="Kitting" htmlClass="pBtn6" /></td>
                                                                        <td><ezf:inputButton name="MoveTo_SupplyDemand" value="Supply / Demand" htmlClass="pBtn8" /></td>
                                                                        <td></td>
                                                                        <td></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><ezf:inputButton name="LineCancel" value="Line Cancel" htmlClass="pBtn6" /></td>
                                                                        <td><ezf:inputButton name="LineClose" value="Line Close" htmlClass="pBtn6" /></td>
                                                                        <td><ezf:inputButton name="MoveTo_OnHandInv" value="On Hand Inv" htmlClass="pBtn6" /></td>
                                                                        <td class="stab"><ezf:anchor name="xxLinkAncr_IL" ezfName="xxLinkAncr_IL" ezfEmulateBtn="1" ezfGuard="OnClick_TemplateDownload" >Import File Link</ezf:anchor></td>
                                                                        <td><ezf:inputFile name="xxFileData" ezfName="xxFileData" otherAttr=" size=\"50\" maxlength=\"9999\""/></td>
                                                                        <td><ezf:inputButton name="Upload" value="Import" htmlClass="pBtn6" /></td>
                                                                    </tr>
                                                                </table>
                                                                <table style="margin-top: 6px; z-index: 0; position: relative; float: right" border="0" cellpadding="1" cellspacing="1" align="left">
                                                                    <colgroup>
                                                                        <col width="">
                                                                    </colgroup>
                                                                    <tbody>
                                                                        <tr>
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
                                                                                                <td class="pOut"><label ezfout name="xxPageShowFromNum" ezfname="xxPageShowFromNum">1</label></td>
                                                                                                <td class="stab"><label>to</label></td>
                                                                                                <td class="pOut"><label ezfout name="xxPageShowToNum" ezfname="xxPageShowToNum">20</label></td>
                                                                                                <td class="stab"><label>of</label></td>
                                                                                                <td class="pOut"><label ezfout name="xxPageShowOfNum" ezfname="xxPageShowOfNum">200</label></td>
                                                                                                <td>&nbsp;</td>
                                                                                                <td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></td>
                                                                                                <td></td>
                                                                                                <td><input onclick="tablePagenation(this.name, 'A')" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></td>
                                                                                            </tr>
                                                                                        </tbody>
                                                                                    </table>
                                                                                </ezf:skip>
                                                                                <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 30px;">
                                                                                    <tbody>
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
                                                                                    </tbody>
                                                                                </table>
                                                                            </td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </c:when>
                                                <%-- ########## Detail Tab [END] ########## --%>
                        
                        </c:choose>

                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</center>
<div style="display:none;">
	<ezf:inputButton name="btn11" value="btn11" otherAttr=" id=\"btn11\""/>
</div>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1,false,true);
</script>

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
