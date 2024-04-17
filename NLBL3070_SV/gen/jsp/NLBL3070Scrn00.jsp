<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180323131823 --%>
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
			<input type="hidden" name="pageID" value="NLBL3070Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Delivery Scheduling / Manage Deliveries">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Location Info" class="pTab_ON"><a href="#">Sch/Mng Dely</a></li>
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
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="152">
						<col width="345">
						<col width="110">
						<col width="300">
						<col width="">
						<tr>
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
							<td>
								<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
								<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					<hr style="height: 0px;" cellpadding="0">
					<table border="0" cellpadding="0" cellspacing="0" height="">
						<tr>
							<td valign="top" width="5">&nbsp;</td>
							<td valign="top" width="">
								<table cellpadding="0" height="112">
									<col align="left" width="80">
									<col align="left" width="125">
									<tr>
										<td class="stab">Source Order</td>
										<td><ezf:inputText name="trxHdrNum" ezfName="trxHdrNum" value="XX" otherAttr=" size=\"16\" maxlength=\"8\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">Order Category</td>
										<td>
											<ezf:select name="dsOrdCatgCd" ezfName="dsOrdCatgCd" ezfBlank="1" ezfCodeName="dsOrdCatgCd_P" ezfDispName="dsOrdCatgDescTxt_P" otherAttr=" style=\"width:117px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Order Reason</td>
										<td>
											<ezf:select name="dsOrdTpCd" ezfName="dsOrdTpCd" ezfBlank="1" ezfCodeName="dsOrdTpCd_P" ezfDispName="dsOrdTpDescTxt_P" otherAttr=" style=\"width:117px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_ML" ezfEmulateBtn="1" ezfGuard="OpenWin_ModelName" >Model</ezf:anchor></td>
										<td><ezf:inputText name="t_MdlNm" ezfName="t_MdlNm" value="XX" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_CF" ezfEmulateBtn="1" ezfGuard="OpenWin_ConfigID" >Config ID</ezf:anchor></td>
										<td><ezf:inputText name="svcConfigMstrPk" ezfName="svcConfigMstrPk" value="XX" otherAttr=" size=\"16\" maxlength=\"28\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>
							<td valign="top" width="">
								<table width="" cellpadding="0" border="0" height="112">
									<col align="left" width="110">
									<col align="left" width="125">
									<tr>
										<td class="stab">Shipping Order</td>
										<td><ezf:inputText name="soNum" ezfName="soNum" value="XX" otherAttr=" size=\"16\" maxlength=\"8\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">Shipping Order Status</td>
										<td>
											<ezf:select name="dsSoLineStsCd" ezfName="dsSoLineStsCd" ezfBlank="1" ezfCodeName="dsSoLineStsCd_P" ezfDispName="dsSoLineStsDescTxt_P" otherAttr=" style=\"width:117px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Service Level</td>
										<td>
											<ezf:select name="shpgSvcLvlCd" ezfName="shpgSvcLvlCd" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_P" ezfDispName="shpgSvcLvlDescTxt_P" otherAttr=" style=\"width:117px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Scheduling Status</td>
										<td>
											<ezf:select name="schdCoordStsCd" ezfName="schdCoordStsCd" ezfBlank="1" ezfCodeName="schdCoordStsCd_P" ezfDispName="schdCoordStsDescTxt_P" otherAttr=" style=\"width:117px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Tracking Number</td>
										<td><ezf:inputText name="bolNum" ezfName="bolNum" value="XX" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>
							<td valign="top" width="">
								<table cellpadding="0" width="">
									<col align="left" width="88">
									<col align="left" width="65">
									<col align="left" width="35">
									<col align="left" width="122">
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_WR" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfoRtlWh" >Source WH</ezf:anchor></td>
										<td><ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="12345678" otherAttr=" size=\"8\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_RtlWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="123456789012345" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_WS" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfoRtlSWh" >Source SWH</ezf:anchor></td>
										<td><ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="XX" otherAttr=" size=\"8\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_RtlSWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" value="XX" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_SP" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCustCode" >Ship-to Customer</ezf:anchor></td>
										<td><ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" value="XX" otherAttr=" size=\"8\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_ShipToCustInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="shipToCustNm" ezfName="shipToCustNm" value="XX" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_CA" ezfEmulateBtn="1" ezfGuard="OpenWin_CarrCode" >Carrier</ezf:anchor></td>
										<td><ezf:inputText name="carrCd" ezfName="carrCd" value="XX" otherAttr=" size=\"8\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_CarrInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="carrNm" ezfName="carrNm" value="XX" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_SC" ezfEmulateBtn="1" ezfGuard="OpenWin_CoordSearchCode" >Call Coordinator</ezf:anchor></td>
										<td><ezf:inputText name="schdCoordPsnCd" ezfName="schdCoordPsnCd" value="XX" otherAttr=" size=\"8\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_CoordInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="xxPsnFirstMidLastNm" ezfName="xxPsnFirstMidLastNm" value="XX" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
								</table>
							</td>
							<td valign="top" width="">
								<table cellpadding="0" width="">
									<col align="left" width="125">
									<col align="left" width="110">
									<col align="left" width="10">
									<col align="left" width="110">
									<tr>
										<td class="stab">Request Delivery Date</td>
										<td>
											<ezf:inputText name="rddDt_FR" ezfName="rddDt_FR" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rddDt_FR', 4 );">
										</td>
										<td class="stab">-</td>
										<td>
											<ezf:inputText name="rddDt_TO" ezfName="rddDt_TO" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rddDt_TO', 4 );">
										</td>
									</tr>
									<tr>
										<td class="stab">Scheduled Carrier Date</td>
										<td>
											<ezf:inputText name="schdCarrPickUpDt_FR" ezfName="schdCarrPickUpDt_FR" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdCarrPickUpDt_FR', 4 );">
										</td>
										<td class="stab">-</td>
										<td>
											<ezf:inputText name="schdCarrPickUpDt_TO" ezfName="schdCarrPickUpDt_TO" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdCarrPickUpDt_TO', 4 );">
										</td>
									</tr>
									<tr>
										<td class="stab">Scheduled Delivery Date</td>
										<td>
											<ezf:inputText name="schdDelyDt_FR" ezfName="schdDelyDt_FR" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdDelyDt_FR', 4 );">
										</td>
										<td class="stab">-</td>
										<td>
											<ezf:inputText name="schdDelyDt_TO" ezfName="schdDelyDt_TO" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdDelyDt_TO', 4 );">
										</td>
									</tr>
									<tr>
										<td class="stab">Actual Delivery Date</td>
										<td>
											<ezf:inputText name="actlDelyDt_FR" ezfName="actlDelyDt_FR" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlDelyDt_FR', 4 );">
										</td>
										<td class="stab">-</td>
										<td>
											<ezf:inputText name="actlDelyDt_TO" ezfName="actlDelyDt_TO" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlDelyDt_TO', 4 );">
										</td>
									</tr>
									<tr>
										<td class="stab">WMS Dropped</td>
										<td colspan="2">
											<ezf:inputCheckBox name="wmsDropFlg_Y" ezfName="wmsDropFlg_Y" value="Y" /> Yes
											<ezf:inputCheckBox name="wmsDropFlg_N" ezfName="wmsDropFlg_N" value="Y" /> No 
										</td>
									</tr>
									<tr>
										<td class="stab">Carrier Requested</td>
										<td colspan="2">
											<ezf:inputCheckBox name="delyReqFlg_Y" ezfName="delyReqFlg_Y" value="Y" /> Yes
											<ezf:inputCheckBox name="delyReqFlg_N" ezfName="delyReqFlg_N" value="Y" /> No 
										</td>
										<td align="Center" class="stab"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
<%-- #################################################### DETAIL ################################################### --%>
<%@ page import="business.servlet.NLBL3070.NLBL3070BMsg" %>
<%@ page import="business.servlet.NLBL3070.NLBL3070_BBMsg" %>
<%  NLBL3070BMsg bMsg = (NLBL3070BMsg)databean.getEZDBMsg(); %>
					<table width="100%">
						<col valign="top">
						<tr>
							<td>
								<div class="pTab_HEAD">
									<ul>
										<li class="pTab_ON"  id="Scheduling" title="Scheduling"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Tab_Schd" >Scheduling</ezf:anchor></li>
										<li class="pTab_OFF" id="Deliveries" title="Deliveries"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Tab_Dely" >Deliveries</ezf:anchor></li>
									</ul>
								</div>
								<%-- #################################################### Scheduling ################################################### --%>
								<c:choose>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Scheduling'}">
								<script type="text/javascript">
									document.getElementById( "Scheduling" ).className = "pTab_ON";
									document.getElementById( "Deliveries" ).className = "pTab_OFF";
								</script>
								<div class="pTab_BODY_In">
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="left" width="180">
													<col align="left" width="100">
													<col align="left" width="100">
													<col align="left" width="">
													<col align="right" width="550">
													<tr>
														<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
														<td><ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn8" /></td>
														<td><ezf:inputButton name="UnSelect_All" value="Unselect All" htmlClass="pBtn8" /></td>
														<td>&nbsp;</td>
														<td align="right">
															<ezf:skip>
															<table border="0" cellpadding="0" width="100%">
																<tr>
																	<td align="left">
																		<table border="0" cellpadding="0" align="left" cellspacing="0">
																			<col>
																				<tr>
																				<td>Results 999 - 999 of 999</td>
																				</tr>
																		</table>
																	</td>
																	<td align="right">
																		<table border="0" cellpadding="0" cellspacing="0">
																			<col width="54"  align="center">
																			<col width="40"  align="center">
																			<col width="16"  align="center">
																			<col width="40"  align="center">
																			<col width="26"  align="center">
																			<col width="10">
																			<col>
																			<col width="20">
																			<col>
																			<col width="1">
																			<col>
																			<tr>
																				<td class="stab">Showing</td>
																				<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																				<td class="stab">/</td>
																				<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																				<td class="stab">page</td>
																				<td>&nbsp;</td>
																				<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
																				<td></td>
																				<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
																				<td></td>
																				<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
															</ezf:skip>
															<table width="100%">
																<tr align="right">
																	<td>
																		<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																			<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
																			<jsp:param name="TableNm"           value="A" />
																			<jsp:param name="ShowingFrom"       value="xxPageShowFromNum_A" />
																			<jsp:param name="ShowingTo"         value="xxPageShowToNum_A" />
																			<jsp:param name="ShowingOf"         value="xxPageShowOfNum_A" />
																			<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum_A" />
																			<jsp:param name="ShowingTotal"      value="xxPageShowTotNum_A" />
																			<jsp:param name="ViewMode"          value="FULL" />
																		</jsp:include>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table border="0" cellpadding="0" cellspacing="0" style="margin: 5px;">
													<tr>
														<td valign="top">
															<div id="parentBoxA">
																<div style="float:left; display:block"> <!-- left table -->
																	<div id='leftTblHead' style="display:block;">
																	</div>
																	<div id='leftTbl' style="float:left; display:block; height:184px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
																	</div>
																</div>  <!-- left table -->
																<%-- LEFT-TABLE(TOP) --%>
																<div style="float:left"> <!-- right table -->
																	<div id='rightTblHead' style="width:1065px; overflow-y:hidden; height:40; overflow-x:hidden;">
																		<table border="1" cellpadding="1" cellspacing="0" height="40" style="table-layout: fixed" id="AHEAD" width="3885px" style="margin-right:20px">
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="85">		<!-- Sales Order Number -->
																			<col align="center" width="100">	<!-- Configuration PK -->
																			<col align="center" width="80">		<!-- Back Order Type -->
																			<col align="center" width="80">		<!-- Shipping Order Number -->
																			<col align="center" width="100">	<!-- Model Name -->
																			<col align="center" width="95">		<!-- Request Delivery Date -->
																			<col align="center" width="110">	<!-- Scheduled Carrier Pickup Date -->
																			<col align="center" width="200">	<!-- Scheduled Delivery Date -->
																			<col align="center" width="100">	<!-- Tech Meet the Truck -->
																			<col align="center" width="110">	<!-- Next Action Date -->
																			<col align="center" width="160">	<!-- Assigned Carrier/Name -->
																			<col align="center" width="80">		<!-- Call Duration -->
																			<col align="center" width="80">		<!-- Call Creation Details/FSR Number -->
																			<col align="center" width="100">	<!-- Call Creation Details/Date -->
																			<col align="center" width="120">	<!-- Call Creation Details/Technician -->
																			<col align="center" width="110">	<!-- Schedule Status -->
																			<col align="center" width="100">	<!-- Ship To/Contact -->
																			<col align="center" width="120">	<!-- Sales Rep -->
																			<col align="center" width="120">	<!-- Telephone -->
																			<col align="center" width="120">	<!-- Ship To/Name -->
																			<col align="center" width="150">	<!-- Ship To/Address1 -->
																			<col align="center" width="150">	<!-- Ship To/Address2 -->
																			<col align="center" width="100">	<!-- Ship To/City -->
																			<col align="center" width="40">		<!-- Ship To/State -->
																			<col align="center" width="80">		<!-- Zip -->
																			<col align="center" width="80">		<!-- Delivery Instruction -->
																			<col align="center" width="120">	<!-- Source WH -->
																			<col align="center" width="50">		<!-- WMS Drop -->
																			<col align="center" width="50">		<!-- Drop Ready -->
																			<col align="center" width="50">		<!-- Carrier Request -->
																			<col align="center" width="110">	<!-- Shipping Service Level -->
																			<col align="center" width="120">	<!-- Carrier Account# -->
																			<col align="center" width="120">	<!-- Coordinator Name-->
																			<col align="center" width="130">	<!-- Waiting Customer Commitment/Status -->
																			<col align="center" width="130">	<!-- Waiting Customer Commitment/Comment -->
																			<col align="center" width="120">	<!-- Order Category -->
																			<col align="center" width="120">	<!-- Order Reason -->
																			<col align="center" width="80">		<!-- Scheduling History -->
																			<tr>
																				<td id="AH0"  class="pClothBs colFix"></td>
																				<td id="AH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'trxHdrNum_A1' )">Source<br>Order<img id="sortIMG.trxHdrNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcConfigMstrPk_A1' )">Config ID<img id="sortIMG.svcConfigMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'backOrdImpctTpDescTxt_A1' )">Backorder<br>Status<img id="sortIMG.backOrdImpctTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'soNum_A1' )">Shipping<br>Order<img id="sortIMG.soNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 't_MdlNm_A1' )">Model<img id="sortIMG.t_MdlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rddDt_A1' )">Requested<br>Delivery Date<img id="sortIMG.rddDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'schdCarrPickUpDt_A1' )">Scheduled Carrier<br>Pickup Date<img id="sortIMG.schdCarrPickUpDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'schdDelyDt_A1' )">Scheduled<br>Delivery Date<img id="sortIMG.schdDelyDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'techMeetTruckFlg_A1' )">Tech Meet The<br>Truck<img id="sortIMG.techMeetTruckFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'nextActDt_A1' )">Next Action<br>Date<img id="sortIMG.nextActDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'carrNm_A1' )">Carrier Assigned<img id="sortIMG.carrNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'schdDurnTmNum_A1' )">Call Duration<br>(Min)<img id="sortIMG.schdDurnTmNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'fsrNum_A1' )">FSR Number<img id="sortIMG.fsrNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcTaskSchdDt_A1' )">FSR<br>Scheduled Date<img id="sortIMG.svcTaskSchdDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'techNm_A1' )">Technician<img id="sortIMG.techNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'schdCoordStsDescTxt_A1' )">Scheduling<br>Status<img id="sortIMG.schdCoordStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxPsnFirstMidLastNm_A2' )">Customer<br>Contact<img id="sortIMG.xxPsnFirstMidLastNm_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'tocNm_A1' )">Sales Rep<img id="sortIMG.tocNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacPsnTelNum_A1' )">Telephone<img id="sortIMG.ctacPsnTelNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_A1' )">Customer Name<img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'firstLineAddr_A1' )">Customer Address1<img id="sortIMG.firstLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'scdLineAddr_A1' )">Customer Address2<img id="sortIMG.scdLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctyAddr_A1' )">City<img id="sortIMG.ctyAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'stCd_A1' )">State<img id="sortIMG.stCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'postCd_A1' )">Zip<img id="sortIMG.postCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH26" class="pClothBs">Delivery<br>Instruction</td>
																				<td id="AH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhNm_A1' )">Source WH<img id="sortIMG.rtlWhNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'wmsDropFlg_A1' )">WMS<br>Drop<img id="sortIMG.wmsDropFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH29" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'wmsDropRqstFlg_A1' )">Drop<br>Ready<img id="sortIMG.wmsDropRqstFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH30" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'sendRqstFlg_A1' )">Carr<br>Rqst<img id="sortIMG.sendRqstFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH31" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shpgSvcLvlCd_A1' )">Service Level<img id="sortIMG.shpgSvcLvlCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH32" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'carrAcctNum_A1' )">Carrier Account#<img id="sortIMG.carrAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH33" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxPsnFirstMidLastNm_A1' )">Coordinator<img id="sortIMG.xxPsnFirstMidLastNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH34" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'tempSchdRsnCd_A1' )">Scheduling Notes<img id="sortIMG.tempSchdRsnCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH35" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'tempSchdCmntTxt_A1' )">Scheduling Notes Comment<img id="sortIMG.tempSchdCmntTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH36" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsOrdCatgDescTxt_A1' )">Order Category<img id="sortIMG.dsOrdCatgDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH37" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsOrdTpDescTxt_A1' )">Order Reason<img id="sortIMG.dsOrdTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH38" class="pClothBs">Schedule<br>History</td>
																			</tr>
																		</table>
																	</div>
																	<div id="rightTBL" style="width:1082px; height:204; display:block; overflow:scroll; margin:0px; padding:0px;" >
																		<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed"  width="3885px" >
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="left"   width="85">		<!-- Sales Order Number -->
																			<col align="center" width="100">	<!-- Configuration PK -->
																			<col align="left"   width="80">		<!-- Back Order Type -->
																			<col align="left"   width="80">		<!-- Shipping Order Number -->
																			<col align="center" width="100">	<!-- Model Name -->
																			<col align="left"   width="95">		<!-- Request Delivery Date -->
																			<col align="center" width="110">	<!-- Scheduled Carrier Pickup Date -->
																			<col align="center" width="200">	<!-- Scheduled Delivery Date -->
																			<col align="center" width="100">	<!-- Tech Meet the Truck -->
																			<col align="center" width="110">	<!-- Next Action Date -->
																			<col align="center" width="160">	<!-- Assigned Carrier/Name -->
																			<col align="center" width="80">		<!-- Call Duration -->
																			<col align="left"   width="80">		<!-- Call Creation Details/FSR Number -->
																			<col align="left"   width="100">	<!-- Call Creation Details/Date -->
																			<col align="center" width="120">	<!-- Call Creation Details/Technician -->
																			<col align="center" width="110">	<!-- Schedule Status -->
																			<col align="center" width="100">	<!-- Ship To/Contact -->
																			<col align="center" width="120">	<!-- Sales Rep -->
																			<col align="center" width="120">	<!-- Telephone -->
																			<col align="center" width="120">	<!-- Ship To/Name -->
																			<col align="center" width="150">	<!-- Ship To/Address1 -->
																			<col align="center" width="150">	<!-- Ship To/Address2 -->
																			<col align="center" width="100">	<!-- Ship To/City -->
																			<col align="center" width="40">		<!-- Ship To/State -->
																			<col align="center" width="80">		<!-- Zip -->
																			<col align="center" width="80">		<!-- Delivery Instruction -->
																			<col align="center" width="120">	<!-- Source WH -->
																			<col align="center" width="50">		<!-- WMS Drop -->
																			<col align="center" width="50">		<!-- Drop Ready -->
																			<col align="center" width="50">		<!-- Carrier Request -->
																			<col align="center" width="110">	<!-- Shipping Service Level -->
																			<col align="center" width="120">	<!-- Carrier Account# -->
																			<col align="center" width="120">	<!-- Coordinator Name-->
																			<col align="center" width="130">	<!-- Waiting Customer Commitment/Status -->
																			<col align="center" width="130">	<!-- Waiting Customer Commitment/Comment -->
																			<col align="center" width="120">	<!-- Order Category -->
																			<col align="center" width="120">	<!-- Order Reason -->
																			<col align="center" width="80">		<!-- Scheduling History -->
																			<ezf:row ezfHyo="A">
																				<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																					<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/></td>
																					<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_OrdEntry" otherAttr=" tabindex=\"-1\" id=\"Open_OrdEntry{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_A1" ezfName="trxHdrNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																					<td><ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
																					<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngBo" otherAttr=" tabindex=\"-1\" id=\"Open_MngBo{EZF_ROW_NUMBER}\""><ezf:label name="backOrdImpctTpDescTxt_A1" ezfName="backOrdImpctTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																					<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngSO" otherAttr=" tabindex=\"-1\" id=\"Open_MngSO{EZF_ROW_NUMBER}\""><ezf:label name="soNum_A1" ezfName="soNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																					<td><ezf:inputText name="t_MdlNm_A1" ezfName="t_MdlNm_A1" value="ZZZZZZZZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
																					<td><ezf:label name="rddDt_A1" ezfName="rddDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td>
																						<ezf:inputText name="schdCarrPickUpDt_A1" ezfName="schdCarrPickUpDt_A1" value="01/22/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																						<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdCarrPickUpDt_A1', 4, {EZF_ROW_NUMBER} );">
																					</td>
																					<td>
																						<ezf:inputText name="schdDelyDt_A1" ezfName="schdDelyDt_A1" value="01/22/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																						<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdDelyDt_A1', 4, {EZF_ROW_NUMBER} );">
																						<ezf:inputText name="schdDelyTsDplyTxt_A2" ezfName="schdDelyTsDplyTxt_A2" value="11:00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/>
																						<ezf:select name="rqstRcvDtTxt_S2" ezfName="rqstRcvDtTxt_S2" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" otherAttr=" style=\"width:39;\""/>
																					</td>
																					<td><ezf:inputCheckBox name="techMeetTruckFlg_A1" ezfName="techMeetTruckFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"techMeetTruckFlg_A1{EZF_ROW_NUMBER}\""/></td>
																					<td>
																						<ezf:inputText name="nextActDt_A1" ezfName="nextActDt_A1" value="01/22/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																						<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'nextActDt_A1', 4, {EZF_ROW_NUMBER} );">
																					</td>
																					<td>
																						<ezf:inputText name="carrNm_A1" ezfName="carrNm_A1" value="FEDEX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/>
																						<ezf:inputButton name="OpenWin_CarrInfo" value="Carr" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_CarrInfo{EZF_ROW_NUMBER}\""/>
																					</td>
																					<td><ezf:inputText name="schdDurnTmNum_A1" ezfName="schdDurnTmNum_A1" value="40" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"12\" style=\"text-align: right\" ezftoupper=\"\""/></td>
																					<td><ezf:label name="fsrNum_A1" ezfName="fsrNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:label name="svcTaskSchdDt_A1" ezfName="svcTaskSchdDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:inputText name="techNm_A1" ezfName="techNm_A1" value="TECH TARO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/></td>
																					<td>
																						<ezf:select name="schdCoordStsCd_A1" ezfName="schdCoordStsCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="0" ezfCodeName="schdCoordStsCd_AL" ezfDispName="schdCoordStsDescTxt_AL" ezfCodeDispHyo="A" otherAttr=" style=\"width:100;\""/>
																					</td>
																					<td><ezf:inputText name="xxPsnFirstMidLastNm_A2" ezfName="xxPsnFirstMidLastNm_A2" value="HANAKO CANON" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"25\""/></td>
																					<td><ezf:inputText name="tocNm_A1" ezfName="tocNm_A1" value="REPs SUZUKI" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="ctacPsnTelNum_A1" ezfName="ctacPsnTelNum_A1" value="111-222-3333" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="FEDEX123456" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"360\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="firstLineAddr_A1" ezfName="firstLineAddr_A1" value="Address1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\""/></td>
																					<td><ezf:inputText name="scdLineAddr_A1" ezfName="scdLineAddr_A1" value="Address2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\""/></td>
																					<td><ezf:inputText name="ctyAddr_A1" ezfName="ctyAddr_A1" value="NEW YORK" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"25\" ezftoupper=\"\""/></td>
																					<td><ezf:label name="stCd_A1" ezfName="stCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:inputText name="postCd_A1" ezfName="postCd_A1" value="11050-0023" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"15\" style=\"text-align: right\" ezftoupper=\"\""/></td>
																					<td><ezf:inputButton name="Open_DelyInstn" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn5" otherAttr=" id=\"Open_DelyInstn{EZF_ROW_NUMBER}\""/></td>
																					<td><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="ATLANTA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
																					<td><ezf:label name="wmsDropFlg_A1" ezfName="wmsDropFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:label name="wmsDropRqstFlg_A1" ezfName="wmsDropRqstFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:label name="sendRqstFlg_A1" ezfName="sendRqstFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td>
																						<ezf:select name="shpgSvcLvlCd_A1" ezfName="shpgSvcLvlCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_P" ezfDispName="shpgSvcLvlDescTxt_P" otherAttr=" style=\"width:100;\""/>
																					</td>
																					<td><ezf:inputText name="carrAcctNum_A1" ezfName="carrAcctNum_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="xxPsnFirstMidLastNm_A1" ezfName="xxPsnFirstMidLastNm_A1" value="CANON HANAKO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"25\""/></td>
																					<td>
																						<ezf:select name="tempSchdRsnCd_A1" ezfName="tempSchdRsnCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="tempSchdRsnCd_P" ezfDispName="tempSchdRsnDescTxt_P" otherAttr=" style=\"width:110;\""/>
																					</td>
																					<td><ezf:inputText name="tempSchdCmntTxt_A1" ezfName="tempSchdCmntTxt_A1" value="Comment1111" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"180\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="dsOrdCatgDescTxt_A1" ezfName="dsOrdCatgDescTxt_A1" value="LEASE, CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="dsOrdTpDescTxt_A1" ezfName="dsOrdTpDescTxt_A1" value="TARO YAMADA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/></td>
																					<td><ezf:inputButton name="OpenWin_SchdHist" value="History" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn5" otherAttr=" id=\"OpenWin_SchdHist{EZF_ROW_NUMBER}\""/></td>
																				</tr>
																			</ezf:row>
																			<ezf:skip>
																				<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																					<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" value="Y" id="xxChkBox_A1{EZF_ROW_NUMBER}" ></td>
																					<td><a href="#" ezfhyo="A" onclick="sendServer('Open_OrdEntry')" tabindex="-1" id="Open_OrdEntry{EZF_ROW_NUMBER}" ><label ezfout name="trxHdrNum_A1" ezfname="trxHdrNum_A1" ezfhyo="A">CP002000</label></a></td>
																					<td><input type="text" class="pPro" size="13"  maxlength="30"  name="svcConfigMstrPk_A1" ezfname="svcConfigMstrPk_A1" value="1234567890" ezfhyo="A" ezftoupper ></td>
																					<td><a href="#" ezfhyo="A" onclick="sendServer('Open_MngBo')" tabindex="-1" id="Open_MngBo{EZF_ROW_NUMBER}" ><label ezfout name="backOrdImpctTpDescTxt_A1" ezfname="backOrdImpctTpDescTxt_A1" ezfhyo="A">Critical</label></a></td>
																					<td><a href="#" ezfhyo="A" onclick="sendServer('Open_MngSO')" tabindex="-1" id="Open_MngSO{EZF_ROW_NUMBER}" ><label ezfout name="soNum_A1" ezfname="soNum_A1" ezfhyo="A">SHA00200</label></a></td>
																					<td><input type="text" class="pPro" size="13"  maxlength="30"  name="t_MdlNm_A1" ezfname="t_MdlNm_A1" value="ZZZZZZZZ" ezfhyo="A" ezftoupper ></td>
																					<td><label ezfout name="rddDt_A1" ezfname="rddDt_A1" ezfhyo="A">11/22/2015</label></td>
																					<td>
																						<input type="text" class="pPro" size="10" maxlength="10" value="01/22/2015" name="schdDelyDt_A1" ezfname="schdDelyDt_A1" ezfhyo="A" ezftoupper>
																						<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdDelyDt_A1', 4, {EZF_ROW_NUMBER} );">
																					</td>
																					<td>
																						<input type="text" class="pPro" size="10" maxlength="10" value="01/22/2015" name="schdDelyDt_A1" ezfname="schdDelyDt_A1" ezfhyo="A" ezftoupper>
																						<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdDelyDt_A1', 4, {EZF_ROW_NUMBER} );">
																						<input type="text" class="pPro" size="5" maxlength="5" value="11:00" name="schdDelyTsDplyTxt_A2" ezfname="schdDelyTsDplyTxt_A2" ezfhyo="A" ezftoupper>
																						<select name="rqstRcvDtTxt_S2" ezfname="rqstRcvDtTxt_S2" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" ezfBlank="1" ezfhyo="A" style="width:39;" ezflist="rqstRcvDtTxt_CD,rqstRcvDtTxt_DI,3, ,blank" disabled>
																							<option>AM</option>
																							<option>PM</option>
																						</select>
																					</td>
																					<td><input type="checkbox" name="techMeetTruckFlg_A1" ezfname="techMeetTruckFlg_A1" ezfhyo="A" value="Y" id="techMeetTruckFlg_A1{EZF_ROW_NUMBER}" ></td>
																					<td>
																						<input type="text" class="pPro" size="10" maxlength="10" value="01/22/2015" name="nextActDt_A1" ezfname="nextActDt_A1" ezfhyo="A" ezftoupper>
																						<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'nextActDt_A1', 4, {EZF_ROW_NUMBER} );">
																					</td>
																					<td>
																						<input type="text" class="pPro" size="15" maxlength="50" value="FEDEX" name="carrNm_A1" ezfname="carrNm_A1" ezfhyo="A" ezftoupper>
																						<input type="button" class="pBtn1" value="Carr" name="OpenWin_CarrInfo" onClick="sendServer(this)" id="OpenWin_CarrInfo{EZF_ROW_NUMBER}" ezfhyo="A" disabled>
																					</td>
																					<td><input type="text" class="pPro" size="10"  maxlength="12"  name="schdDurnTmNum_A1" ezfname="schdDurnTmNum_A1" value="40" style="text-align: right" ezfhyo="A" ezftoupper ></td>
																					<td><label ezfout name="fsrNum_A1" ezfname="fsrNum_A1" ezfhyo="A">1234567890</label></td>
																					<td><label ezfout name="svcTaskSchdDt_A1" ezfname="svcTaskSchdDt_A1" ezfhyo="A">10/22/1015</label></td>
																					<td><input type="text" class="pPro" size="15" maxlength="50" name="techNm_A1" ezfname="techNm_A1" value="TECH TARO" ezfhyo="A" ezftoupper ></td>
																					<td>
																						<select name="schdCoordStsCd_A1" ezfname="schdCoordStsCd_A1" ezfCodeName="schdCoordStsCd_P" ezfDispName="schdCoordStsDescTxt_P" ezfBlank="1" ezfhyo="A" style="width:100;" ezflist="schdCoordStsCd_P,schdCoordStsDescTxt_P,99, ,blank" disabled>
																							<option>Closed</option>
																						</select>
																					</td>
																					<td><input type="text" class="pPro" size="13" maxlength="25" name="xxPsnFirstMidLastNm_A2" ezfname="xxPsnFirstMidLastNm_A2" value="HANAKO CANON" ezfhyo="A"></td>
																					<td><input type="text" class="pPro" size="15" maxlength="50" name="tocNm_A1" ezfname="tocNm_A1" value="REPs SUZUKI" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="15" maxlength="20" name="ctacPsnTelNum_A1" ezfname="ctacPsnTelNum_A1" value="111-222-3333" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="15"  maxlength="360"  name="dsAcctNm_A1" ezfname="dsAcctNm_A1" value="FEDEX123456" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="20" maxlength="60" name="firstLineAddr_A1" ezfname="firstLineAddr_A1" value="Address1" ezfhyo="A"></td>
																					<td><input type="text" class="pPro" size="20" maxlength="60" name="scdLineAddr_A1" ezfname="scdLineAddr_A1" value="Address2" ezfhyo="A"></td>
																					<td><input type="text" class="pPro" size="13"  maxlength="25"  name="ctyAddr_A1" ezfname="ctyAddr_A1" value="NEW YORK" ezfhyo="A" ezftoupper ></td>
																					<td><label ezfout name="stCd_A1" ezfname="stCd_A1" ezfhyo="A">NY</label></td>
																					<td><input type="text" class="pPro" size="10"  maxlength="15"  name="postCd_A1" ezfname="postCd_A1" value="11050-0023" style="text-align: right" ezfhyo="A" ezftoupper ></td>
																					<td><input type="button" class="pBtn5" value="Edit" name="Open_DelyInstn" onClick="sendServer(this)" id="Open_DelyInstn{EZF_ROW_NUMBER}" ezfhyo="A"></td>
																					<td><input type="text" class="pPro" size="15" maxlength="30" name="rtlWhNm_A1" ezfname="rtlWhNm_A1" value="ATLANTA" ezfhyo="A" ezftoupper ></td>
																					<td><label ezfout name="wmsDropFlg_A1" ezfname="wmsDropFlg_A1" ezfhyo="A">Y</label></td>
																					<td><label ezfout name="wmsDropRqstFlg_A1" ezfname="wmsDropRqstFlg_A1" ezfhyo="A">Y</label></td>
																					<td><label ezfout name="sendRqstFlg_A1" ezfname="sendRqstFlg_A1" ezfhyo="A">Y</label></td>
																					<td>
																						<select name="shpgSvcLvlCd_A1" ezfname="shpgSvcLvlCd_A1" ezfCodeName="shpgSvcLvlCd_P" ezfDispName="shpgSvcLvlDescTxt_P" ezfBlank="1" ezfhyo="A" style="width:100;" ezflist="shpgSvcLvlCd_P,shpgSvcLvlDescTxt_P,99, ,blank" disabled>
																							<option>Ground Service</option>
																						</select>
																					</td>
																					<td><input type="text" class="pPro" size="15"  maxlength="20"  name="carrAcctNum_A1" ezfname="carrAcctNum_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="15" maxlength="30" name="xxPsnFirstMidLastNm_A1" ezfname="xxPsnFirstMidLastNm_A1" value="CANON HANAKO" style="text-align: right" ezfhyo="A" ezftoupper ></td>
																					<td>
																						<select name="tempSchdRsnCd_A1" ezfname="tempSchdRsnCd_A1" ezfCodeName="tempSchdRsnCd_P" ezfDispName="tempSchdRsnDescTxt_P" ezfBlank="1" ezfhyo="A" style="width:110;" ezflist="tempSchdRsnCd_P,tempSchdRsnDescTxt_P,99, ,blank" disabled>
																							<option></option>
																						</select>
																					</td>
																					<td><input type="text" class="pPro" size="16" maxlength="180" value="" name="tempSchdCmntTxt_A1" ezfname="tempSchdCmntTxt_A1" ezfhyo="A" ezftoupper></td>
																					<td><input type="text" class="pPro" size="15"  maxlength="50"  name="dsOrdCatgDescTxt_A1" ezfname="dsOrdCatgDescTxt_A1" value="LEASE, CSA" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="15"  maxlength="50"  name="dsOrdTpDescTxt_A1" ezfname="dsOrdTpDescTxt_A1" value="TARO YAMADA" ezfhyo="A" ezftoupper ></td>
																					<td><input type="button" class="pBtn5" value="History" name="OpenWin_SchdHist" onClick="sendServer(this)" id="OpenWin_SchdHist{EZF_ROW_NUMBER}" ezfhyo="A"></td>
																				</tr>
																			</ezf:skip>
																		</table>
																	</div><!-- rightTbl -->
																</div> <!-- right table -->
															</div> <!-- parent box -->
															
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table border="0" cellpadding="0" cellspacing="0" width="100%">
													<col align="left" width="840">
													<col align="left" width="70">
													<col align="right" width="">
													<col align="right" width="">
													<tr>
														<td>
															<fieldset>
																<legend>
																	<table>
																		<tr>
																			<td class="stab">Apply to Detail Lines</td>
																		</tr>
																	</table>
																</legend>
																<table border="0" cellpadding="0" cellspacing="0">
																	<col width="150">
																	<col width="150">
																	<col width="120">
																	<col width="150">
																	<col width="85">
																	<col width="120">
																	<tr>
																		<td class="stab"><label>Scheduled Carrier Pickup Date</labe></td>
																		<td>
																			<ezf:inputText name="schdCarrPickUpDt_AP" ezfName="schdCarrPickUpDt_AP" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
																			<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdCarrPickUpDt_AP', 4 );">
																		</td>
																		<td class="stab"><label>Service Level</labe></td>
																		<td>
																			<ezf:select name="shpgSvcLvlCd_AP" ezfName="shpgSvcLvlCd_AP" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_P" ezfDispName="shpgSvcLvlDescTxt_P" otherAttr=" style=\"width:145px\""/>
																		</td>
																		<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_AP" ezfEmulateBtn="1" ezfGuard="OpenWin_AplyCarrCode" >Carrier Assigned</ezf:anchor></td>
																		<td><ezf:inputText name="carrNm_AP" ezfName="carrNm_AP" value="FEDEX" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/></td>
																	</tr>
																	<tr>
																		<td class="stab"><label>Scheduling Status</labe></td>
																		<td>
																			<ezf:select name="schdCoordStsCd_AP" ezfName="schdCoordStsCd_AP" ezfBlank="1" ezfCodeName="schdCoordStsCd_BP" ezfDispName="schdCoordStsDescTxt_BP" otherAttr=" style=\"width:145px\""/>
																		</td>
																		<td class="stab"><label>Scheduled Delivery Date</labe></td>
																		<td colspan="2">
																			<ezf:inputText name="schdDelyDt_AP" ezfName="schdDelyDt_AP" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
																			<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdDelyDt_AP', 4 );">
																			<ezf:inputText name="schdDelyTsDplyTxt_AP" ezfName="schdDelyTsDplyTxt_AP" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/>
																			<ezf:select name="rqstRcvDtTxt_P2" ezfName="rqstRcvDtTxt_P2" ezfBlank="1" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" otherAttr=" style=\"width:39;\""/>
																		</td>
																		<td align="right"><ezf:inputButton name="Apply" value="Apply" htmlClass="pBtn4" /></td>
																	</tr>
																</table>
															</fieldset>
														</td>
														<td>&nbsp;</td>
														<td valign="center"><ezf:inputButton name="Release_SO" value="Drop Shipping Order" htmlClass="pBtn10" /></td>
														<td valign="center"><ezf:inputButton name="Send_Rqst" value="Send Carrier Request" htmlClass="pBtn10" /></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								</c:when>
								<%-- #################################################### Deliveries ################################################### --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Deliveries'}">
								<script type="text/javascript">
									document.getElementById( "Scheduling" ).className = "pTab_OFF";
									document.getElementById( "Deliveries" ).className = "pTab_ON";
								</script>
								<div class="pTab_BODY_In">
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="left" width="180">
													<col align="left" width="100">
													<col align="left" width="100">
													<col align="left" width="">
													<col align="right" width="550">
													<tr>
														<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
														<td><ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn8" /></td>
														<td><ezf:inputButton name="UnSelect_All" value="Unselect All" htmlClass="pBtn8" /></td>
														<td>&nbsp;</td>
														<td align="right">
															<ezf:skip>
															<table border="0" cellpadding="0" width="100%">
																<tr>
																	<td align="left">
																		<table border="0" cellpadding="0" align="left" cellspacing="0">
																			<col>
																				<tr>
																				<td>Results 999 - 999 of 999</td>
																				</tr>
																		</table>
																	</td>
																	<td align="right">
																		<table border="0" cellpadding="0" cellspacing="0">
																			<col width="54"  align="center">
																			<col width="40"  align="center">
																			<col width="16"  align="center">
																			<col width="40"  align="center">
																			<col width="26"  align="center">
																			<col width="10">
																			<col>
																			<col width="20">
																			<col>
																			<col width="1">
																			<col>
																			<tr>
																				<td class="stab">Showing</td>
																				<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																				<td class="stab">/</td>
																				<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																				<td class="stab">page</td>
																				<td>&nbsp;</td>
																				<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'B')"></td>
																				<td></td>
																				<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'B')"></td>
																				<td></td>
																				<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'B')"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
															</ezf:skip>
															<table width="100%">
																<tr align="right">
																	<td>
																		<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																			<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
																			<jsp:param name="TableNm"           value="B" />
																			<jsp:param name="ShowingFrom"       value="xxPageShowFromNum_B" />
																			<jsp:param name="ShowingTo"         value="xxPageShowToNum_B" />
																			<jsp:param name="ShowingOf"         value="xxPageShowOfNum_B" />
																			<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum_B" />
																			<jsp:param name="ShowingTotal"      value="xxPageShowTotNum_B" />
																			<jsp:param name="ViewMode"          value="FULL" />
																		</jsp:include>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table border="0" cellpadding="0" cellspacing="0" style="margin: 5px;">
													<tr>
														<td valign="top">
															<div id="parentBoxB">
																<div style="float:left; display:block"> <!-- left table -->
																	<div id='leftTblHead' style="display:block;">
																	</div>
																	<div id='leftTbl' style="float:left; display:block; height:175px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
																	</div>
																</div>  <!-- left table -->
																<div style="float:left"> <!-- right table -->
																	<%-- LEFT-TABLE(TOP) --%>
																	<div id='rightTblHead' style="width:1065px; overflow-y:hidden; height:45; overflow-x:hidden;">
																		<table border="1" cellpadding="1" cellspacing="0" height="45" style="table-layout: fixed" id="BHEAD" width="4320px" style="margin-right:20px">
																			<col align="center" width="30">		<!-- Button -->
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="80">		<!-- Delivery Instruction -->
																			<col align="center" width="100">	<!-- Config ID -->
																			<col align="center" width="75">		<!-- Sales Order/Number -->
																			<col align="center" width="60">		<!-- Sales Order/Line -->
																			<col align="center" width="75">		<!-- Shipping Order/Number -->
																			<col align="center" width="40">		<!-- Shipping Order/Line -->
																			<col align="center" width="120">	<!-- Coordinator/Name -->
																			<col align="center" width="120">	<!-- Source WH -->
																			<col align="center" width="80">		<!-- Source SWH -->
																			<col align="center" width="80">		<!-- Ship To/State -->
																			<col align="center" width="120">	<!-- Ship To/Contact -->
																			<col align="center" width="120">	<!-- Item Number -->
																			<col align="center" width="145">	<!-- Item Description -->
																			<col align="center" width="100">	<!-- Essential Critical -->
																			<col align="center" width="100">	<!-- Qty -->
																			<col align="center" width="140">	<!-- Serial -->
																			<col align="center" width="80">		<!-- Request Delivery Date -->
																			<col align="center" width="140">	<!-- Schedule Delivery Date -->
																			<col align="center" width="140">	<!-- Order Released Date -->
																			<col align="center" width="140">	<!-- Pick Confirm Date -->
																			<col align="center" width="140">	<!-- Delivery Shop Date -->
																			<col align="center" width="140">	<!-- Tech Assign Date -->
																			<col align="center" width="140">	<!-- Delivered to Stage Date -->
																			<col align="center" width="140">	<!-- Ship Confirm Date -->
																			<col align="center" width="110">	<!-- Carrier Reason -->
																			<col align="center" width="180">	<!-- Actual Delivery Date Time -->
																			<col align="center" width="80">		<!-- Installation Date -->
																			<col align="center" width="120">	<!-- Install Call Status -->
																			<col align="center" width="120">	<!-- Shipping Order Status -->
																			<col align="center" width="160">	<!-- Carrier/Name -->
																			<col align="center" width="170">	<!-- Tracking Number -->
																			<col align="center" width="120">	<!-- Shipping Service Level -->
																			<col align="center" width="120">	<!-- Scheduling Status -->
																			<col align="center" width="140">	<!-- Send Request Date -->
																			<col align="center" width="90">		<!-- Ship Cost -->
																			<col align="center" width="120">	<!-- Ship To/Name -->
																			<col align="center" width="80">		<!-- Ship To/City -->
																			<col align="center" width="100">	<!-- Pick Config Id -->
																			<col align="center" width="30">		<!-- Stock Status -->
																			<col align="center" width="120">	<!-- Order Category -->
																			<tr>
																				<td id="BH0"  class="pClothBs colFix"></td>
																				<td id="BH1"  class="pClothBs colFix"></td>
																				<td id="BH2"  class="pClothBs colFix"></td>
																				<td id="BH3"  class="pClothBs">Delivery<br>Instruction</td>
																				<td id="BH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'shipSvcConfigMstrPk_B1' )">Config ID<img id="sortIMG.shipSvcConfigMstrPk_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'trxHdrNum_B1' )">Sales<br>Order<img id="sortIMG.trxHdrNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'dplyLineNum_B1' )">Order<br>Line<img id="sortIMG.dplyLineNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'soNum_B1' )">Shipping<br>Order<img id="sortIMG.soNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'soSlpNum_B1' )">SO<br>Line<img id="sortIMG.soSlpNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxPsnFirstMidLastNm_B1' )">Scheduling<br>Coordinator<img id="sortIMG.xxPsnFirstMidLastNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rtlWhNm_B1' )">Source WH<img id="sortIMG.rtlWhNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rtlSwhCd_B1' )">Source SWH<img id="sortIMG.rtlSwhCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'stCd_B1' )">Customer<br>Ship-To<img id="sortIMG.stCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxPsnFirstMidLastNm_B2' )">Customer<br>Contact<img id="sortIMG.xxPsnFirstMidLastNm_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdseCd_B1' )">Item<img id="sortIMG.mdseCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdseDescShortTxt_B1' )">Item Description<img id="sortIMG.mdseDescShortTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'backOrdImpctTpDescTxt_B1' )">Essential<br>Critical<img id="sortIMG.backOrdImpctTpDescTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxShipQty_B1' )">Qty<img id="sortIMG.xxShipQty_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'serNum_B1' )">Serial<img id="sortIMG.serNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rddDt_B1' )">Requested<br>Date<img id="sortIMG.rddDt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'schdDelyTsDplyTxt_B1' )">Scheduled<br>Delivery Date<img id="sortIMG.schdDelyTsDplyTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxTsDsp19Txt_OR' )">Order Released<img id="sortIMG.xxTsDsp19Txt_OR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxTsDsp19Txt_PC' )">Pick Confirm Date<img id="sortIMG.xxTsDsp19Txt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxTsDsp19Txt_DS' )">Delivered To Shop<img id="sortIMG.xxTsDsp19Txt_DS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxTsDsp19Txt_TA' )">Tech Assigned Date<img id="sortIMG.xxTsDsp19Txt_TA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxTsDsp19Txt_DD' )">Delivered to<br>Distribution Stage<img id="sortIMG.xxTsDsp19Txt_DD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxTsDsp19Txt_SC' )">Ship Confirm Date<img id="sortIMG.xxTsDsp19Txt_SC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'carrRsnCd_B1' )">Carrier Reason<img id="sortIMG.carrRsnCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'actlDelyDt_B1' )">Actual Delivery Date<img id="sortIMG.actlDelyDt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH29" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'svcTaskCpltDt_B1' )">Installation<br>Date<img id="sortIMG.svcTaskCpltDt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH30" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'svcTaskStsDescTxt_B1' )">Install Call Status<img id="sortIMG.svcTaskStsDescTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH31" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'dsSoLineStsDescTxt_B1' )">Shipping Order<br>Status<img id="sortIMG.dsSoLineStsDescTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH32" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'carrNm_B1' )">Carrier<img id="sortIMG.carrNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH33" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'proNum_B1' )">Tracking Number<img id="sortIMG.proNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH34" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'shpgSvcLvlDescTxt_B1' )">Service Level<img id="sortIMG.shpgSvcLvlDescTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH35" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'schdCoordStsDescTxt_B1' )">Scheduling Status<img id="sortIMG.schdCoordStsDescTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH36" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxTsDsp19Txt_SR' )">Send Request Date<img id="sortIMG.xxTsDsp19Txt_SR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH37" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'totFrtAmt_B1' )">Ship Cost<img id="sortIMG.totFrtAmt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH38" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'dsAcctNm_B1' )">Ship To Name<img id="sortIMG.dsAcctNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH39" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'ctyAddr_B1' )">Ship To City<img id="sortIMG.ctyAddr_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH40" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'pickSvcConfigMstrPk_B1' )">Pick Config ID<img id="sortIMG.pickSvcConfigMstrPk_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH41" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'stkStsCd_B1' )">SS<img id="sortIMG.stkStsCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH42" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'dsOrdCatgDescTxt_B1' )">Order Category<img id="sortIMG.dsOrdCatgDescTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			</tr>
																		</table>
																	</div>
																	<%-- LEFT-TABLE(MID) --%>
																	<div id="rightTBL" style="width:1082px; height:195; display:block; overflow:scroll; margin:0px; padding:0px;" >
																		<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed"  width="4320px" >
																			<col align="center" width="30">		<!-- Button -->
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="80">		<!-- Delivery Instruction -->
																			<col align="center" width="100">	<!-- Config ID -->
																			<col align="left"   width="75">		<!-- Sales Order/Number -->
																			<col align="center" width="60">		<!-- Sales Order/Line -->
																			<col align="left"   width="75">		<!-- Shipping Order/Number -->
																			<col align="center" width="40">		<!-- Shipping Order/Line -->
																			<col align="center" width="120">	<!-- Coordinator/Name -->
																			<col align="center" width="120">	<!-- Source WH -->
																			<col align="center" width="80">		<!-- Source SWH -->
																			<col align="center" width="80">		<!-- Ship To/State -->
																			<col align="center" width="120">	<!-- Ship To/Contact -->
																			<col align="center" width="120">	<!-- Item Number -->
																			<col align="center" width="145">	<!-- Item Description -->
																			<col align="center" width="100">	<!-- Essential Critical -->
																			<col align="center" width="100">	<!-- Qty -->
																			<col align="center" width="140">	<!-- Serial -->
																			<col align="left"   width="80">		<!-- Request Delivery Date -->
																			<col align="left"   width="140">	<!-- Schedule Delivery Date -->
																			<col align="left"   width="140">	<!-- Order Released Date -->
																			<col align="left"   width="140">	<!-- Pick Confirm Date -->
																			<col align="left"   width="140">	<!-- Delivery Shop Date -->
																			<col align="left"   width="140">	<!-- Tech Assign Date -->
																			<col align="left"   width="140">	<!-- Delivered to Stage Date -->
																			<col align="left"   width="140">	<!-- Ship Confirm Date -->
																			<col align="center" width="110">	<!-- Carrier Reason -->
																			<col align="left"   width="180">	<!-- Actual Delivery Date Time -->
																			<col align="left"   width="80">		<!-- Installation Date -->
																			<col align="center" width="120">	<!-- Install Call Status -->
																			<col align="center" width="120">	<!-- Shipping Order Status -->
																			<col align="center" width="160">	<!-- Carrier/Name -->
																			<col align="center" width="170">	<!-- Tracking Number -->
																			<col align="center" width="120">	<!-- Shipping Service Level -->
																			<col align="center" width="120">	<!-- Scheduling Status -->
																			<col align="left"   width="140">	<!-- Send Request Date -->
																			<col align="center" width="90">		<!-- Ship Cost -->
																			<col align="center" width="120">	<!-- Ship To/Name -->
																			<col align="center" width="80">		<!-- Ship To/City -->
																			<col align="center" width="100">	<!-- Pick Config Id -->
																			<col align="center" width="30">		<!-- Stock Status -->
																			<col align="center" width="120">	<!-- Order Category -->
																			<% int i = 0; %>
																			<ezf:row ezfHyo="B">
																			<% NLBL3070_BBMsg lineMsg = bMsg.B.no(i++); %>
																			<tr height="28" id="id_leftB_row{EZF_ROW_NUMBER}">
																				<td>
																					<% boolean isSmryLine = "Y".equals(lineMsg.xxSmryLineFlg_B1.getValue()); %>
																					<% if (isSmryLine) { %>
																						<img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('ExpandHolds',{EZF_ROW_NUMBER})" ezfhyo="B" height="14" id="xxSmryLineFlg#{EZF_ROW_NUMBER}">
																					<% } else if (!isSmryLine) { %>
																						<img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('ContractHolds',{EZF_ROW_NUMBER})" ezfhyo="B" height="14" id="xxSmryLineFlg#{EZF_ROW_NUMBER}">
																					<% } %>
																				</td>
																				<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" onClick="sendServerForPreferredView('OnChange_ChkBoxSoNum', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"OnChange_ChkBoxSoNum{EZF_ROW_NUMBER}\""/></td>
																				<td><ezf:inputCheckBox name="xxChkBox_B2" ezfName="xxChkBox_B2" value="Y" ezfHyo="B" ezfArrayNo="0" onClick="sendServerForPreferredView('OnChange_ChkBoxSoSlipNum', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"OnChange_ChkBoxSoSlipNum{EZF_ROW_NUMBER}\""/></td>
																				<td><ezf:inputButton name="Open_DelyInstn" value="Edit" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn5" otherAttr=" id=\"Open_DelyInstn{EZF_ROW_NUMBER}\""/></td>
																				<td><ezf:inputText name="shipSvcConfigMstrPk_B1" ezfName="shipSvcConfigMstrPk_B1" value="12345678" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"28\""/></td>
																				<td><ezf:anchor name="" ezfName="" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_OrdEntry" otherAttr=" tabindex=\"-1\" id=\"Open_OrdEntry{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_B1" ezfName="trxHdrNum_B1" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></td>
																				<td><ezf:label name="dplyLineNum_B1" ezfName="dplyLineNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:anchor name="" ezfName="" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngSO" otherAttr=" tabindex=\"-1\" id=\"Open_MngSO{EZF_ROW_NUMBER}\""><ezf:label name="soNum_B1" ezfName="soNum_B1" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></td>
																				<td><ezf:label name="soSlpNum_B1" ezfName="soSlpNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="xxPsnFirstMidLastNm_B1" ezfName="xxPsnFirstMidLastNm_B1" value="TARO YAMADA" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="rtlWhNm_B1" ezfName="rtlWhNm_B1" value="ATLANTA" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="rtlSwhCd_B1" ezfName="rtlSwhCd_B1" value="NEW" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="stCd_B1" ezfName="stCd_B1" value="NY" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="xxPsnFirstMidLastNm_B2" ezfName="xxPsnFirstMidLastNm_B2" value="HANAKO CANON" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"25\""/></td>
																				<td><ezf:inputText name="mdseCd_B1" ezfName="mdseCd_B1" value="0397B003AA" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"20\""/></td>
																				<td><ezf:inputText name="mdseDescShortTxt_B1" ezfName="mdseDescShortTxt_B1" value="IPQ-1 TONER NEGRO I.PRESS-C1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\""/></td>
																				<td><ezf:inputText name="backOrdImpctTpDescTxt_B1" ezfName="backOrdImpctTpDescTxt_B1" value="Essential" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"50\""/></td>
																				<td><ezf:inputText name="xxShipQty_B1" ezfName="xxShipQty_B1" value="1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"10\" ezftoupper=\"\""/></td>
																				<td>
																					<ezf:inputText name="serNum_B1" ezfName="serNum_B1" value="SER00001" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/>
																					<ezf:inputButton name="OpenWin_SerEntry" value="S" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_SerEntry{EZF_ROW_NUMBER}\""/>
																				</td>
																				<td><ezf:label name="rddDt_B1" ezfName="rddDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="schdDelyTsDplyTxt_B1" ezfName="schdDelyTsDplyTxt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxTsDsp19Txt_OR" ezfName="xxTsDsp19Txt_OR" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxTsDsp19Txt_PC" ezfName="xxTsDsp19Txt_PC" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxTsDsp19Txt_DS" ezfName="xxTsDsp19Txt_DS" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxTsDsp19Txt_TA" ezfName="xxTsDsp19Txt_TA" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxTsDsp19Txt_DD" ezfName="xxTsDsp19Txt_DD" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxTsDsp19Txt_SC" ezfName="xxTsDsp19Txt_SC" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td>
																					<ezf:select name="carrRsnCd_B1" ezfName="carrRsnCd_B1" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="carrRsnCd_P" ezfDispName="carrRsnDescTxt_P" otherAttr=" style=\"width:100;\""/>
																				</td>
																				<td>
																					<ezf:inputText name="actlDelyDt_B1" ezfName="actlDelyDt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlDelyDt_B1', 4 , {EZF_ROW_NUMBER});">
																					<ezf:label name="schdDelyTsDplyTxt_B2" ezfName="schdDelyTsDplyTxt_B2" ezfHyo="B" ezfArrayNo="0" />
																				</td>
																				<td><ezf:label name="svcTaskCpltDt_B1" ezfName="svcTaskCpltDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="svcTaskStsDescTxt_B1" ezfName="svcTaskStsDescTxt_B1" value="Scheduled" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/></td>
																				<td><ezf:inputText name="dsSoLineStsDescTxt_B1" ezfName="dsSoLineStsDescTxt_B1" value="Allocated" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\""/></td>
																				<td>
																					<ezf:inputText name="carrNm_B1" ezfName="carrNm_B1" value="FEDEX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/>
																					<ezf:inputButton name="OpenWin_CarrInfo" value="Carr" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_CarrInfo{EZF_ROW_NUMBER}\""/>
																				</td>
																				<td>
																					<ezf:inputText name="proNum_B1" ezfName="proNum_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/>
																					<ezf:inputButton name="OpenWin_Tracking" value="Tracking#" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn5" otherAttr=" id=\"OpenWin_Tracking{EZF_ROW_NUMBER}\""/>
																				</td>
																				<td><ezf:inputText name="shpgSvcLvlDescTxt_B1" ezfName="shpgSvcLvlDescTxt_B1" value="Ground Service" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\""/></td>
																				<td><ezf:inputText name="schdCoordStsDescTxt_B1" ezfName="schdCoordStsDescTxt_B1" value="Scheduled" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\""/></td>
																				<td><ezf:label name="xxTsDsp19Txt_SR" ezfName="xxTsDsp19Txt_SR" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="totFrtAmt_B1" ezfName="totFrtAmt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="dsAcctNm_B1" ezfName="dsAcctNm_B1" value="FEDEX123456" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"360\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="ctyAddr_B1" ezfName="ctyAddr_B1" value="NEW YORK" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"25\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="pickSvcConfigMstrPk_B1" ezfName="pickSvcConfigMstrPk_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"28\" ezftoupper=\"\""/></td>
																				<td><ezf:label name="stkStsCd_B1" ezfName="stkStsCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="dsOrdCatgDescTxt_B1" ezfName="dsOrdCatgDescTxt_B1" value="LEASE, CSA" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\""/></td>
																			</tr>
																			</ezf:row>
																			<ezf:skip>
																			<tr height="28">
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td><input type="checkbox" name="xxChkBox_B2" ezfname="xxChkBox_B2" ezfhyo="B" value="Y" id="OnChange_ChkBoxSoSlipNum{EZF_ROW_NUMBER}" onClick="sendServer('OnChange_ChkBoxSoSlipNum', '{EZF_ROW_NUMBER}')"></td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td><label ezfout name="dplyLineNum_B1" ezfname="dplyLineNum_B1" ezfhyo="B">1.2</label></td>
																				<td>&nbsp;</td>
																				<td><label ezfout name="soSlpNum_B1" ezfname="soSlpNum_B1" ezfhyo="B">002</label></td>
																				<td><input type="text" class="pPro" size="16" maxlength="60" name="xxPsnFirstMidLastNm_B1" ezfname="xxPsnFirstMidLastNm_B1" value="TARO YAMADA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="30" name="rtlWhNm_B1" ezfname="rtlWhNm_B1" value="ATLANTA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="rtlSwhCd_B1" ezfname="rtlSwhCd_B1" value="NEW" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="stCd_B1" ezfname="stCd_B1" value="NY" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="25" name="xxPsnFirstMidLastNm_B2" ezfname="xxPsnFirstMidLastNm_B2" value="HANAKO CANON" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="mdseCd_B1" ezfname="mdseCd_B1" value="0456B003AA" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="20" maxlength="30" name="mdseDescShortTxt_B1" ezfname="mdseDescShortTxt_B1" value="IPQ-1 TONER NEGRO I.PRESS-C1" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="14" maxlength="50" name="backOrdImpctTpDescTxt_B1" ezfname="backOrdImpctTpDescTxt_B1" value="Essential" ezfhyo="B"></td>
																				<td><input type="text" size="13" maxlength="10" value="2" name="xxShipQty_B1" ezfname="xxShipQty_B1" ezfhyo="B" ezftoupper></td>
																				<td>
																					<input type="text" class="pPro" size="12" maxlength="30" value="" name="serNum_B1" ezfname="serNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="S" name="OpenWin_SerEntry" onClick="sendServer(this)" id="OpenWin_SerEntry{EZF_ROW_NUMBER}" ezfhyo="B">
																				</td>
																				<td><label ezfout name="rddDt_B1" ezfname="rddDt_B1" ezfhyo="B">10/11/2015</label></td>
																				<td><label ezfout name="schdDelyTsDplyTxt_B1" ezfname="schdDelyTsDplyTxt_B1" ezfhyo="B">10/11/2015</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_OR" ezfname="xxTsDsp19Txt_OR" ezfhyo="B">10/13/2015 12:34:56</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_PC" ezfname="xxTsDsp19Txt_PC" ezfhyo="B">10/12/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_DS" ezfname="xxTsDsp19Txt_DS" ezfhyo="B">10/13/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_TA" ezfname="xxTsDsp19Txt_TA" ezfhyo="B">10/14/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_DD" ezfname="xxTsDsp19Txt_DD" ezfhyo="B">10/16/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_SC" ezfname="xxTsDsp19Txt_SC" ezfhyo="B">&nbsp;</label></td>
																				<td>
																					<select name="carrRsnCd_B1" ezfname="carrRsnCd_B1" ezfCodeName="carrRsnCd_P" ezfDispName="carrRsnDescTxt_P" ezfBlank="1" ezfhyo="B" style="width:100;" ezflist="carrRsnCd_P,carrRsnDescTxt_P,99, ,blank" disabled>
																						<option></option>
																						<option>Delivered</option>
																						<option>Not Delivered</option>
																						<option>Flat Tire</option>
																						<option>Bad Weather</option>
																					</select>
																				</td>
																				<td>
																					<input type="text" class="pPro" size="10" maxlength="10" value="" name="actlDelyDt_B1" ezfname="actlDelyDt_B1" ezfhyo="B" ezftoupper>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlDelyDt_B1', 4 , {EZF_ROW_NUMBER});">
																					<input type="text" class="pPro" size="4" maxlength="4" value="" name="schdDelyTsDplyTxt_B2" ezfname="schdDelyTsDplyTxt_B2" ezfhyo="B" ezftoupper>
																					<select name="rqstRcvDtTxt_B2" ezfname="rqstRcvDtTxt_B2" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" ezfBlank="1" ezfhyo="B" style="width:39;" ezflist="rqstRcvDtTxt_CD,rqstRcvDtTxt_DI,3, ,blank" disabled>
																						<option>AM</option>
																						<option>PM</option>
																					</select>
																				</td>
																				<td><label ezfout name="svcTaskCpltDt_B1" ezfname="svcTaskCpltDt_B1" ezfhyo="B">&nbsp;</label></td>
																				<td><input type="text" class="pPro" size="16" maxlength="60" name="svcTaskStsDescTxt_B1" ezfname="svcTaskStsDescTxt_B1" value="Scheduled" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="50" name="dsSoLineStsDescTxt_B1" ezfname="dsSoLineStsDescTxt_B1" value="Allocated" ezfhyo="B"></td>
																				<td>
																					<input type="text" size="15" maxlength="50" value="FEDEX" name="carrNm_B1" ezfname="carrNm_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="Carr" name="OpenWin_CarrInfo" onClick="sendServer(this)" id="OpenWin_CarrInfo{EZF_ROW_NUMBER}" ezfhyo="B">
																				</td>
																				<td>
																					<input type="text" size="13" maxlength="30" value="" name="ProNum_B1" ezfname="ProNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn5" value="Tracking#" name="OpenWin_Tracking" onClick="sendServer(this)" id="OpenWin_Tracking{EZF_ROW_NUMBER}" ezfhyo="B">
																				</td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="shpgSvcLvlDescTxt_B1" ezfname="shpgSvcLvlDescTxt_B1" value="Ground Service" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="schdCoordStsDescTxt_B1" ezfname="schdCoordStsDescTxt_B1" value="Scheduled" ezfhyo="B" ezftoupper ></td>
																				<td><label ezfout name="xxTsDsp19Txt_SR" ezfname="xxTsDsp19Txt_SR" ezfhyo="B">10/17/2015 11:11:11</label></td>
																				<td><input type="text" size="11" maxlength="30" value="" name="totFrtAmt_B1" ezfname="totFrtAmt_B1" ezfhyo="B" ezftoupper></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="360"  name="dsAcctNm_B1" ezfname="dsAcctNm_B1" value="FEDEX123456" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="11"  maxlength="25"  name="ctyAddr_B1" ezfname="ctyAddr_B1" value="NEW YORK" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="13" maxlength="28" value="" name="pickSvcConfigMstrPk_B1" ezfname="pickSvcConfigMstrPk_B1" ezfhyo="B" ezftoupper></td>
																				<td><label ezfout name="stkStsCd_B1" ezfname="stkStsCd_B1" ezfhyo="B">1</label></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="dsOrdCatgDescTxt_B1" ezfname="dsOrdCatgDescTxt_B1" value="LEASE, CSA" ezfhyo="B" ezftoupper ></td>
																			</tr>
																			<tr height="28">
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td><input type="checkbox" name="xxChkBox_B2" ezfname="xxChkBox_B2" ezfhyo="B" value="Y" id="OnChange_ChkBoxSoSlipNum{EZF_ROW_NUMBER}" onClick="sendServer('OnChange_ChkBoxSoSlipNum', '{EZF_ROW_NUMBER}')"></td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td><label ezfout name="dplyLineNum_B1" ezfname="dplyLineNum_B1" ezfhyo="B">1.3</label></td>
																				<td>&nbsp;</td>
																				<td><label ezfout name="soSlpNum_B1" ezfname="soSlpNum_B1" ezfhyo="B">003</label></td>
																				<td><input type="text" class="pPro" size="16" maxlength="60" name="xxPsnFirstMidLastNm_B1" ezfname="xxPsnFirstMidLastNm_B1" value="TARO YAMADA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="30" name="rtlWhNm_B1" ezfname="rtlWhNm_B1" value="ATLANTA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="rtlSwhCd_B1" ezfname="rtlSwhCd_B1" value="NEW" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="stCd_B1" ezfname="stCd_B1" value="NY" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="25" name="xxPsnFirstMidLastNm_B2" ezfname="xxPsnFirstMidLastNm_B2" value="HANAKO CANON" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="mdseCd_B1" ezfname="mdseCd_B1" value="1234B003AC" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="20" maxlength="30" name="mdseDescShortTxt_B1" ezfname="mdseDescShortTxt_B1" value="IPQ-1 TONER NEGRO I.PRESS-C1" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="14" maxlength="50" name="backOrdImpctTpDescTxt_B1" ezfname="backOrdImpctTpDescTxt_B1" value="" ezfhyo="B"></td>
																				<td><input type="text" size="13" maxlength="10" value="5" name="xxShipQty_B1" ezfname="xxShipQty_B1" ezfhyo="B" ezftoupper></td>
																				<td>
																					<input type="text"  class="pPro" size="12" maxlength="30" value="" name="serNum_B1" ezfname="serNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="S" name="OpenWin_SerEntry" onClick="sendServer(this)" id="OpenWin_SerEntry{EZF_ROW_NUMBER}" ezfhyo="B" disabled>
																				</td>
																				<td><label ezfout name="rddDt_B1" ezfname="rddDt_B1" ezfhyo="B">10/11/2015</label></td>
																				<td><label ezfout name="schdDelyTsDplyTxt_B1" ezfname="schdDelyTsDplyTxt_B1" ezfhyo="B">10/11/2015</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_OR" ezfname="xxTsDsp19Txt_OR" ezfhyo="B">10/13/2015 12:34:56</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_PC" ezfname="xxTsDsp19Txt_PC" ezfhyo="B">10/12/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_DS" ezfname="xxTsDsp19Txt_DS" ezfhyo="B">10/13/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_TA" ezfname="xxTsDsp19Txt_TA" ezfhyo="B">10/14/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_DD" ezfname="xxTsDsp19Txt_DD" ezfhyo="B">10/16/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_SC" ezfname="xxTsDsp19Txt_SC" ezfhyo="B">&nbsp;</label></td>
																				<td>
																					<select name="carrRsnCd_B1" ezfname="carrRsnCd_B1" ezfCodeName="carrRsnCd_P" ezfDispName="carrRsnDescTxt_P" ezfBlank="1" ezfhyo="B" style="width:100;" ezflist="carrRsnCd_P,carrRsnDescTxt_P,99, ,blank" disabled>
																						<option></option>
																						<option>Delivered</option>
																						<option>Not Delivered</option>
																						<option>Flat Tire</option>
																						<option>Bad Weather</option>
																					</select>
																				</td>
																				<td>
																					<input type="text" class="pPro" size="10" maxlength="10" value="" name="actlDelyDt_B1" ezfname="actlDelyDt_B1" ezfhyo="B" ezftoupper>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlDelyDt_B1', 4 , {EZF_ROW_NUMBER});">
																					<input type="text" class="pPro" size="4" maxlength="4" value="" name="schdDelyTsDplyTxt_B2" ezfname="schdDelyTsDplyTxt_B2" ezfhyo="B" ezftoupper>
																					<select name="rqstRcvDtTxt_B2" ezfname="rqstRcvDtTxt_B2" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" ezfBlank="1" ezfhyo="B" style="width:39;" ezflist="rqstRcvDtTxt_CD,rqstRcvDtTxt_DI,3, ,blank" disabled>
																						<option>AM</option>
																						<option>PM</option>
																					</select>
																				</td>
																				<td><label ezfout name="svcTaskCpltDt_B1" ezfname="svcTaskCpltDt_B1" ezfhyo="B">&nbsp;</label></td>
																				<td><input type="text" class="pPro" size="16" maxlength="60" name="svcTaskStsDescTxt_B1" ezfname="svcTaskStsDescTxt_B1" value="Scheduled" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="50" name="dsSoLineStsDescTxt_B1" ezfname="dsSoLineStsDescTxt_B1" value="Allocated" ezfhyo="B"></td>
																				<td>
																					<input type="text" size="15" maxlength="50" value="FEDEX" name="carrNm_B1" ezfname="carrNm_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="Carr" name="OpenWin_CarrInfo" onClick="sendServer(this)" id="OpenWin_CarrInfo{EZF_ROW_NUMBER}" ezfhyo="B">
																				</td>
																				<td>
																					<input type="text" size="13" maxlength="30" value="" name="ProNum_B1" ezfname="ProNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn5" value="Tracking#" name="OpenWin_Tracking" onClick="sendServer(this)" id="OpenWin_Tracking{EZF_ROW_NUMBER}" ezfhyo="B">
																				</td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="shpgSvcLvlDescTxt_B1" ezfname="shpgSvcLvlDescTxt_B1" value="Ground Service" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="schdCoordStsDescTxt_B1" ezfname="schdCoordStsDescTxt_B1" value="Scheduled" ezfhyo="B" ezftoupper ></td>
																				<td><label ezfout name="xxTsDsp19Txt_SR" ezfname="xxTsDsp19Txt_SR" ezfhyo="B">10/17/2015 11:11:11</label></td>
																				<td><input type="text" size="11" maxlength="30" value="" name="totFrtAmt_B1" ezfname="totFrtAmt_B1" ezfhyo="B" ezftoupper></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="360"  name="dsAcctNm_B1" ezfname="dsAcctNm_B1" value="FEDEX123456" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="11"  maxlength="25"  name="ctyAddr_B1" ezfname="ctyAddr_B1" value="NEW YORK" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="13" maxlength="28" value="" name="pickSvcConfigMstrPk_B1" ezfname="pickSvcConfigMstrPk_B1" ezfhyo="B" ezftoupper></td>
																				<td><label ezfout name="stkStsCd_B1" ezfname="stkStsCd_B1" ezfhyo="B">1</label></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="dsOrdCatgDescTxt_B1" ezfname="dsOrdCatgDescTxt_B1" value="LEASE, CSA" ezfhyo="B" ezftoupper ></td>
																			</tr>
																			<tr height="28">
																				<td><img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('ContractHolds',{EZF_ROW_NUMBER})" ezfhyo="B" height="14" id="xxSmryLineFlg#{EZF_ROW_NUMBER}"></td>
																				<td><input type="checkbox" name="xxChkBox_B1" ezfname="xxChkBox_B1" ezfhyo="B" value="Y" id="OnChange_ChkBoxSoNum{EZF_ROW_NUMBER}" onClick="sendServer('OnChange_ChkBoxSoNum', '{EZF_ROW_NUMBER}')"></td>
																				<td><input type="checkbox" name="xxChkBox_B2" ezfname="xxChkBox_B2" ezfhyo="B" value="Y" id="OnChange_ChkBoxSoSlipNum{EZF_ROW_NUMBER}" onClick="sendServer('OnChange_ChkBoxSoSlipNum', '{EZF_ROW_NUMBER}')"></td>
																				<td><input type="button" class="pBtn5" value="Edit" name="Open_DelyInstn" onClick="sendServer(this)" id="Open_DelyInstn{EZF_ROW_NUMBER}" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="13" maxlength="28" name="shipSvcConfigMstrPk_B1" ezfname="shipSvcConfigMstrPk_B1" value="987654321" ezfhyo="B"></td>
																				<td><a href="#" ezfhyo="B" onclick="sendServer('Open_OrdEntry')" tabindex="-1" id="Open_OrdEntry{EZF_ROW_NUMBER}" ><label ezfout name="trxHdrNum_B1" ezfname="trxHdrNum_B1" ezfhyo="B">CP002000</label></a></td>
																				<td><label ezfout name="dplyLineNum_B1" ezfname="dplyLineNum_B1" ezfhyo="B">1.1</label></td>
																				<td><a href="#" ezfhyo="B" onclick="sendServer('Open_MngSO')" tabindex="-1" id="Open_MngSO{EZF_ROW_NUMBER}" ><label ezfout name="soNum_B1" ezfname="soNum_B1" ezfhyo="B">SH020000</label></a></td>
																				<td><label ezfout name="soSlpNum_B1" ezfname="soSlpNum_B1" ezfhyo="B">001</label></td>
																				<td><input type="text" class="pPro" size="16" maxlength="60"  name="xxPsnFirstMidLastNm_B1" ezfname="xxPsnFirstMidLastNm_B1" value="TARO YAMADA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="30" name="rtlWhNm_B1" ezfname="rtlWhNm_B1" value="ATLANTA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="rtlSwhCd_B1" ezfname="rtlSwhCd_B1" value="NEW" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="stCd_B1" ezfname="stCd_B1" value="NY" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="25" name="xxPsnFirstMidLastNm_B2" ezfname="xxPsnFirstMidLastNm_B2" value="HANAKO CANON" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="mdseCd_B1" ezfname="mdseCd_B1" value="0397B003AA" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="20" maxlength="30" name="mdseDescShortTxt_B1" ezfname="mdseDescShortTxt_B1" value="IPQ-1 TONER NEGRO I.PRESS-C1" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="14" maxlength="50" name="backOrdImpctTpDescTxt_B1" ezfname="backOrdImpctTpDescTxt_B1" value="Essential" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="13" maxlength="10" value="1" name="xxShipQty_B1" ezfname="xxShipQty_B1" ezfhyo="B" ezftoupper></td>
																				<td>
																					<input type="text" class="pPro" size="12" maxlength="30" value="SER00001" name="serNum_B1" ezfname="serNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="S" name="OpenWin_SerEntry" onClick="sendServer(this)" id="OpenWin_SerEntry{EZF_ROW_NUMBER}" ezfhyo="B"  disabled>
																				</td>
																				<td><label ezfout name="rddDt_B1" ezfname="rddDt_B1" ezfhyo="B">10/11/2015</label></td>
																				<td><label ezfout name="schdDelyTsDplyTxt_B1" ezfname="schdDelyTsDplyTxt_B1" ezfhyo="B">10/11/2015</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_OR" ezfname="xxTsDsp19Txt_OR" ezfhyo="B">10/13/2015 12:34:56</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_PC" ezfname="xxTsDsp19Txt_PC" ezfhyo="B">10/12/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_DS" ezfname="xxTsDsp19Txt_DS" ezfhyo="B">10/13/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_TA" ezfname="xxTsDsp19Txt_TA" ezfhyo="B">10/14/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_DD" ezfname="xxTsDsp19Txt_DD" ezfhyo="B">10/16/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_SC" ezfname="xxTsDsp19Txt_SC" ezfhyo="B">10/16/2015 11:11:11</label></td>
																				<td>
																					<select name="carrRsnCd_B1" ezfname="carrRsnCd_B1" ezfCodeName="carrRsnCd_P" ezfDispName="carrRsnDescTxt_P" ezfBlank="1" ezfhyo="B" style="width:100;" ezflist="carrRsnCd_P,carrRsnDescTxt_P,99, ,blank">
																						<option></option>
																						<option>Delivered</option>
																						<option>Not Delivered</option>
																						<option>Flat Tire</option>
																						<option>Bad Weather</option>
																					</select>
																				</td>
																				<td>
																					<input type="text" size="10" maxlength="10" value="10/17/2015" name="actlDelyDt_B1" ezfname="actlDelyDt_B1" ezfhyo="B" ezftoupper>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlDelyDt_B1', 4 , {EZF_ROW_NUMBER});">
																					<input type="text" size="4" maxlength="4" value="1000" name="schdDelyTsDplyTxt_B2" ezfname="schdDelyTsDplyTxt_B2" ezfhyo="B" ezftoupper>
																					<select name="rqstRcvDtTxt_B2" ezfname="rqstRcvDtTxt_B2" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" ezfBlank="1" ezfhyo="B" style="width:39;" ezflist="rqstRcvDtTxt_CD,rqstRcvDtTxt_DI,3, ,blank" disabled>
																						<option>AM</option>
																						<option>PM</option>
																					</select>
																				</td>
																				<td><label ezfout name="svcTaskCpltDt_B1" ezfname="svcTaskCpltDt_B1" ezfhyo="B">&nbsp;</label></td>
																				<td><input type="text" class="pPro" size="16" maxlength="60" name="svcTaskStsDescTxt_B1" ezfname="svcTaskStsDescTxt_B1" value="Scheduled" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="50" name="dsSoLineStsDescTxt_B1" ezfname="dsSoLineStsDescTxt_B1" value="Shipped" ezfhyo="B"></td>
																				<td>
																					<input type="text" class="pPro" size="15" maxlength="50" value="FEDEX" name="carrNm_B1" ezfname="carrNm_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="Carr" name="OpenWin_CarrInfo" onClick="sendServer(this)" id="OpenWin_CarrInfo{EZF_ROW_NUMBER}" ezfhyo="B" disabled>
																				</td>
																				<td>
																					<input type="text" class="pPro" size="13" maxlength="30" value="TEST12345" name="ProNum_B1" ezfname="ProNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn5" value="Tracking#" name="OpenWin_Tracking" onClick="sendServer(this)" id="OpenWin_Tracking{EZF_ROW_NUMBER}" ezfhyo="B">
																				</td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="shpgSvcLvlDescTxt_B1" ezfname="shpgSvcLvlDescTxt_B1" value="Ground Service" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="schdCoordStsDescTxt_B1" ezfname="schdCoordStsDescTxt_B1" value="Closed" ezfhyo="B" ezftoupper ></td>
																				<td><label ezfout name="xxTsDsp19Txt_SR" ezfname="xxTsDsp19Txt_SR" ezfhyo="B">10/17/2015 11:11:11</label></td>
																				<td><input type="text" class="pPro" size="11" maxlength="30" value="" name="totFrtAmt_B1" ezfname="totFrtAmt_B1" ezfhyo="B" ezftoupper></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="360"  name="dsAcctNm_B1" ezfname="dsAcctNm_B1" value="JOSH COPIERS" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="11"  maxlength="25"  name="ctyAddr_B1" ezfname="ctyAddr_B1" value="NEW YORK" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="13" maxlength="28" value="" name="pickSvcConfigMstrPk_B1" ezfname="pickSvcConfigMstrPk_B1" ezfhyo="B" ezftoupper></td>
																				<td><label ezfout name="stkStsCd_B1" ezfname="stkStsCd_B1" ezfhyo="B">1</label></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="dsOrdCatgDescTxt_B1" ezfname="dsOrdCatgDescTxt_B1" value="LEASE, CSA" ezfhyo="B" ezftoupper ></td>
																			</tr>
																			
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td><input type="checkbox" name="xxChkBox_B2" ezfname="xxChkBox_B2" ezfhyo="B" value="Y" id="OnChange_ChkBoxSoSlipNum{EZF_ROW_NUMBER}" onClick="sendServer('OnChange_ChkBoxSoSlipNum', '{EZF_ROW_NUMBER}')"></td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td><label ezfout name="dplyLineNum_B1" ezfname="dplyLineNum_B1" ezfhyo="B">1.2</label></td>
																				<td>&nbsp;</td>
																				<td><label ezfout name="soSlpNum_B1" ezfname="soSlpNum_B1" ezfhyo="B">002</label></td>
																				<td><input type="text" class="pPro" size="16" maxlength="60" name="xxPsnFirstMidLastNm_B1" ezfname="xxPsnFirstMidLastNm_B1" value="TARO YAMADA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="30" name="rtlWhNm_B1" ezfname="rtlWhNm_B1" value="ATLANTA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="rtlSwhCd_B1" ezfname="rtlSwhCd_B1" value="NEW" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="stCd_B1" ezfname="stCd_B1" value="NY" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="25" name="xxPsnFirstMidLastNm_B2" ezfname="xxPsnFirstMidLastNm_B2" value="HANAKO CANON" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="mdseCd_B1" ezfname="mdseCd_B1" value="0456B003AA" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="20" maxlength="30" name="mdseDescShortTxt_B1" ezfname="mdseDescShortTxt_B1" value="IPQ-1 TONER NEGRO I.PRESS-C1" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="14" maxlength="50" name="backOrdImpctTpDescTxt_B1" ezfname="backOrdImpctTpDescTxt_B1" value="Essential" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="13" maxlength="10" value="2" name="xxShipQty_B1" ezfname="xxShipQty_B1" ezfhyo="B" ezftoupper></td>
																				<td>
																					<input type="text" class="pPro" class="pPro" size="12" maxlength="30" value="" name="serNum_B1" ezfname="serNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="S" name="OpenWin_SerEntry" onClick="sendServer(this)" id="OpenWin_SerEntry{EZF_ROW_NUMBER}" ezfhyo="B" disabled>
																				</td>
																				<td><label ezfout name="rddDt_B1" ezfname="rddDt_B1" ezfhyo="B">10/11/2015</label></td>
																				<td><label ezfout name="schdDelyTsDplyTxt_B1" ezfname="schdDelyTsDplyTxt_B1" ezfhyo="B">10/11/2015</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_OR" ezfname="xxTsDsp19Txt_OR" ezfhyo="B">10/14/2015 12:34:56</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_PC" ezfname="xxTsDsp19Txt_PC" ezfhyo="B">10/12/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_DS" ezfname="xxTsDsp19Txt_DS" ezfhyo="B">10/13/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_TA" ezfname="xxTsDsp19Txt_TA" ezfhyo="B">10/14/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_DD" ezfname="xxTsDsp19Txt_DD" ezfhyo="B">10/16/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_SC" ezfname="xxTsDsp19Txt_SC" ezfhyo="B">10/16/2015 11:11:11</label></td>
																				<td>
																					<select name="carrRsnCd_B1" ezfname="carrRsnCd_B1" ezfCodeName="carrRsnCd_P" ezfDispName="carrRsnDescTxt_P" ezfBlank="1" ezfhyo="B" style="width:100;" ezflist="carrRsnCd_P,carrRsnDescTxt_P,99, ,blank">
																						<option></option>
																						<option>Delivered</option>
																						<option>Not Delivered</option>
																						<option>Flat Tire</option>
																						<option>Bad Weather</option>
																					</select>
																				</td>
																				<td>
																					<input type="text" size="10" maxlength="10" value="10/17/2015" name="actlDelyDt_B1" ezfname="actlDelyDt_B1" ezfhyo="B" ezftoupper>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlDelyDt_B1', 4 , {EZF_ROW_NUMBER});">
																					<input type="text" size="4" maxlength="4" value="1000" name="schdDelyTsDplyTxt_B2" ezfname="schdDelyTsDplyTxt_B2" ezfhyo="B" ezftoupper>
																					<select name="rqstRcvDtTxt_B2" ezfname="rqstRcvDtTxt_B2" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" ezfBlank="1" ezfhyo="B" style="width:39;" ezflist="rqstRcvDtTxt_CD,rqstRcvDtTxt_DI,3, ,blank" disabled>
																						<option>AM</option>
																						<option>PM</option>
																					</select>
																				</td>
																				<td><label ezfout name="svcTaskCpltDt_B1" ezfname="svcTaskCpltDt_B1" ezfhyo="B">&nbsp;</label></td>
																				<td><input type="text" class="pPro" size="16" maxlength="60" name="svcTaskStsDescTxt_B1" ezfname="svcTaskStsDescTxt_B1" value="Scheduled" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="50" name="dsSoLineStsDescTxt_B1" ezfname="dsSoLineStsDescTxt_B1" value="Shipped" ezfhyo="B"></td>
																				<td>
																					<input type="text" class="pPro" size="15" maxlength="50" value="FEDEX" name="carrNm_B1" ezfname="carrNm_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="Carr" name="OpenWin_CarrInfo" onClick="sendServer(this)" id="OpenWin_CarrInfo{EZF_ROW_NUMBER}" ezfhyo="B" disabled>
																				</td>
																				<td>
																					<input type="text" class="pPro" size="13" maxlength="30" value="TEST12345" name="ProNum_B1" ezfname="ProNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn5" value="Tracking#" name="OpenWin_Tracking" onClick="sendServer(this)" id="OpenWin_Tracking{EZF_ROW_NUMBER}" ezfhyo="B">
																				</td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="shpgSvcLvlDescTxt_B1" ezfname="shpgSvcLvlDescTxt_B1" value="Ground Service" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="schdCoordStsDescTxt_B1" ezfname="schdCoordStsDescTxt_B1" value="Closed" ezfhyo="B" ezftoupper ></td>
																				<td><label ezfout name="xxTsDsp19Txt_SR" ezfname="xxTsDsp19Txt_SR" ezfhyo="B">10/17/2015 11:11:11</label></td>
																				<td><input type="text" class="pPro" size="11" maxlength="30" value="" name="totFrtAmt_B1" ezfname="totFrtAmt_B1" ezfhyo="B" ezftoupper></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="360"  name="dsAcctNm_B1" ezfname="dsAcctNm_B1" value="JOSH COPIERS" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="11"  maxlength="25"  name="ctyAddr_B1" ezfname="ctyAddr_B1" value="NEW YORK" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="13" maxlength="28" value="" name="pickSvcConfigMstrPk_B1" ezfname="pickSvcConfigMstrPk_B1" ezfhyo="B" ezftoupper></td>
																				<td><label ezfout name="stkStsCd_B1" ezfname="stkStsCd_B1" ezfhyo="B">1</label></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="dsOrdCatgDescTxt_B1" ezfname="dsOrdCatgDescTxt_B1" value="LEASE, CSA" ezfhyo="B" ezftoupper ></td>
																			</tr>
																			<tr height="28">
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td><input type="checkbox" name="xxChkBox_B2" ezfname="xxChkBox_B2" ezfhyo="B" value="Y" id="OnChange_ChkBoxSoSlipNum{EZF_ROW_NUMBER}" onClick="sendServer('OnChange_ChkBoxSoSlipNum', '{EZF_ROW_NUMBER}')"></td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td><label ezfout name="dplyLineNum_B1" ezfname="dplyLineNum_B1" ezfhyo="B">1.3</label></td>
																				<td>&nbsp;</td>
																				<td><label ezfout name="soSlpNum_B1" ezfname="soSlpNum_B1" ezfhyo="B">003</label></td>
																				<td><input type="text" class="pPro" size="16" maxlength="60" name="xxPsnFirstMidLastNm_B1" ezfname="xxPsnFirstMidLastNm_B1" value="TARO YAMADA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="30" name="rtlWhNm_B1" ezfname="rtlWhNm_B1" value="ATLANTA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="rtlSwhCd_B1" ezfname="rtlSwhCd_B1" value="NEW" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="stCd_B1" ezfname="stCd_B1" value="NY" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="25" name="xxPsnFirstMidLastNm_B2" ezfname="xxPsnFirstMidLastNm_B2" value="HANAKO CANON" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="mdseCd_B1" ezfname="mdseCd_B1" value="1234B003AC" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="20" maxlength="30" name="mdseDescShortTxt_B1" ezfname="mdseDescShortTxt_B1" value="IPQ-1 TONER NEGRO I.PRESS-C1" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="14" maxlength="50" name="backOrdImpctTpDescTxt_B1" ezfname="backOrdImpctTpDescTxt_B1" value="" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="13" maxlength="10" value="5" name="xxShipQty_B1" ezfname="xxShipQty_B1" ezfhyo="B" ezftoupper></td>
																				<td>
																					<input type="text"  class="pPro" size="12" maxlength="30" value="" name="serNum_B1" ezfname="serNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="S" name="OpenWin_SerEntry" onClick="sendServer(this)" id="OpenWin_SerEntry{EZF_ROW_NUMBER}" ezfhyo="B" disabled>
																				</td>
																				<td><label ezfout name="rddDt_B1" ezfname="rddDt_B1" ezfhyo="B">10/11/2015</label></td>
																				<td><label ezfout name="schdDelyTsDplyTxt_B1" ezfname="schdDelyTsDplyTxt_B1" ezfhyo="B">10/11/2015</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_OR" ezfname="xxTsDsp19Txt_OR" ezfhyo="B">10/13/2015 12:34:56</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_PC" ezfname="xxTsDsp19Txt_PC" ezfhyo="B">10/12/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_DS" ezfname="xxTsDsp19Txt_DS" ezfhyo="B">10/13/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_TA" ezfname="xxTsDsp19Txt_TA" ezfhyo="B">10/14/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_DD" ezfname="xxTsDsp19Txt_DD" ezfhyo="B">10/16/2015 11:11:11</label></td>
																				<td><label ezfout name="xxTsDsp19Txt_SC" ezfname="xxTsDsp19Txt_SC" ezfhyo="B">10/16/2015 11:11:11</label></td>
																				<td>
																					<select name="carrRsnCd_B1" ezfname="carrRsnCd_B1" ezfCodeName="carrRsnCd_P" ezfDispName="carrRsnDescTxt_P" ezfBlank="1" ezfhyo="B" style="width:100;" ezflist="carrRsnCd_P,carrRsnDescTxt_P,99, ,blank">
																						<option></option>
																						<option>Delivered</option>
																						<option>Not Delivered</option>
																						<option>Flat Tire</option>
																						<option>Bad Weather</option>
																					</select>
																				</td>
																				<td>
																					<input type="text" size="10" maxlength="10" value="10/17/2015" name="actlDelyDt_B1" ezfname="actlDelyDt_B1" ezfhyo="B" ezftoupper>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlDelyDt_B1', 4 , {EZF_ROW_NUMBER});">
																					<input type="text" size="4" maxlength="4" value="1000" name="schdDelyTsDplyTxt_B2" ezfname="schdDelyTsDplyTxt_B2" ezfhyo="B" ezftoupper>
																					<select name="rqstRcvDtTxt_B2" ezfname="rqstRcvDtTxt_B2" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" ezfBlank="1" ezfhyo="B" style="width:39;" ezflist="rqstRcvDtTxt_CD,rqstRcvDtTxt_DI,3, ,blank" disabled>
																						<option>AM</option>
																						<option>PM</option>
																					</select>
																				</td>
																				<td><label ezfout name="svcTaskCpltDt_B1" ezfname="svcTaskCpltDt_B1" ezfhyo="B">&nbsp;</label></td>
																				<td><input type="text" class="pPro" size="16" maxlength="60" name="svcTaskStsDescTxt_B1" ezfname="svcTaskStsDescTxt_B1" value="Scheduled" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="50" name="dsSoLineStsDescTxt_B1" ezfname="dsSoLineStsDescTxt_B1" value="Shipped" ezfhyo="B"></td>
																				<td>
																					<input type="text" class="pPro" size="15" maxlength="50" value="FEDEX" name="carrNm_B1" ezfname="carrNm_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="Carr" name="OpenWin_CarrInfo" onClick="sendServer(this)" id="OpenWin_CarrInfo{EZF_ROW_NUMBER}" ezfhyo="B" disabled>
																				</td>
																				<td>
																					<input type="text" class="pPro" size="13" maxlength="30" value="TEST12345" name="ProNum_B1" ezfname="ProNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn5" value="Tracking#" name="OpenWin_Tracking" onClick="sendServer(this)" id="OpenWin_Tracking{EZF_ROW_NUMBER}" ezfhyo="B">
																				</td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="shpgSvcLvlDescTxt_B1" ezfname="shpgSvcLvlDescTxt_B1" value="Ground Service" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="schdCoordStsDescTxt_B1" ezfname="schdCoordStsDescTxt_B1" value="Closed" ezfhyo="B" ezftoupper ></td>
																				<td><label ezfout name="xxTsDsp19Txt_SR" ezfname="xxTsDsp19Txt_SR" ezfhyo="B">10/17/2015 11:11:11</label></td>
																				<td><input type="text" class="pPro" size="11" maxlength="30" value="" name="totFrtAmt_B1" ezfname="totFrtAmt_B1" ezfhyo="B" ezftoupper></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="360"  name="dsAcctNm_B1" ezfname="dsAcctNm_B1" value="JOSH COPIERS" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="11"  maxlength="25"  name="ctyAddr_B1" ezfname="ctyAddr_B1" value="NEW YORK" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="13" maxlength="28" value="" name="pickSvcConfigMstrPk_B1" ezfname="pickSvcConfigMstrPk_B1" ezfhyo="B" ezftoupper></td>
																				<td><label ezfout name="stkStsCd_B1" ezfname="stkStsCd_B1" ezfhyo="B">1</label></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="dsOrdCatgDescTxt_B1" ezfname="dsOrdCatgDescTxt_B1" value="LEASE, CSA" ezfhyo="B" ezftoupper ></td>
																			</tr>
																			<tr height="28">
																				<td><img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('ExpandHolds',{EZF_ROW_NUMBER})" ezfhyo="B" height="14" id="xxSmryLineFlg#{EZF_ROW_NUMBER}"></td>
																				<td><input type="checkbox" name="xxChkBox_B1" ezfname="xxChkBox_B1" ezfhyo="B" value="Y" id="OnChange_ChkBoxSoNum{EZF_ROW_NUMBER}" onClick="sendServer('OnChange_ChkBoxSoNum', '{EZF_ROW_NUMBER}')"></td>
																				<td><input type="checkbox" name="xxChkBox_B2" ezfname="xxChkBox_B2" ezfhyo="B" value="Y" id="OnChange_ChkBoxSoSlipNum{EZF_ROW_NUMBER}" onClick="sendServer('OnChange_ChkBoxSoSlipNum', '{EZF_ROW_NUMBER}')"></td>
																				<td><input type="button" class="pBtn5" value="Edit" name="Open_DelyInstn" onClick="sendServer(this)" id="Open_DelyInstn{EZF_ROW_NUMBER}" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="13" maxlength="28" name="shipSvcConfigMstrPk_B1" ezfname="shipSvcConfigMstrPk_B1" value="4444444" ezfhyo="B"></td>
																				<td><a href="#" ezfhyo="B" onclick="sendServer('Open_OrdEntry')" tabindex="-1" id="Open_OrdEntry{EZF_ROW_NUMBER}" ><label ezfout name="trxHdrNum_B1" ezfname="trxHdrNum_B1" ezfhyo="B">CP003000</label></a></td>
																				<td>&nbsp;</td>
																				<td><a href="#" ezfhyo="B" onclick="sendServer('Open_MngSO')" tabindex="-1" id="Open_MngSO{EZF_ROW_NUMBER}" ><label ezfout name="soNum_B1" ezfname="soNum_B1" ezfhyo="B">SH003000</label></a></td>
																				<td>&nbsp;</td>
																				<td><input type="text" class="pPro" size="16" maxlength="60" name="xxPsnFirstMidLastNm_B1" ezfname="xxPsnFirstMidLastNm_B1" value="TARO YAMADA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="30" name="rtlWhNm_B1" ezfname="rtlWhNm_B1" value="ATLANTA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="rtlSwhCd_B1" ezfname="rtlSwhCd_B1" value="" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="stCd_B1" ezfname="stCd_B1" value="NY" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="25" name="xxPsnFirstMidLastNm_B2" ezfname="xxPsnFirstMidLastNm_B2" value="HANAKO CANON" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="mdseCd_B1" ezfname="mdseCd_B1" value="" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="20" maxlength="30" name="mdseDescShortTxt_B1" ezfname="mdseDescShortTxt_B1" value="" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="14" maxlength="50" name="backOrdImpctTpDescTxt_B1" ezfname="backOrdImpctTpDescTxt_B1" value="" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="13" maxlength="10" value="" name="xxShipQty_B1" ezfname="xxShipQty_B1" ezfhyo="B" ezftoupper></td>
																				<td>
																					<input type="text"  class="pPro" size="12" maxlength="30" value="" name="serNum_B1" ezfname="serNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="S" name="OpenWin_SerEntry" onClick="sendServer(this)" id="OpenWin_SerEntry{EZF_ROW_NUMBER}" ezfhyo="B" disabled>
																				</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>
																					<select name="carrRsnCd_B1" ezfname="carrRsnCd_B1" ezfCodeName="carrRsnCd_P" ezfDispName="carrRsnDescTxt_P" ezfBlank="1" ezfhyo="B" style="width:100;" ezflist="carrRsnCd_P,carrRsnDescTxt_P,99, ,blank" disabled>
																						<option></option>
																						<option>Delivered</option>
																						<option>Not Delivered</option>
																						<option>Flat Tire</option>
																						<option>Bad Weather</option>
																					</select>
																				</td>
																				<td>
																					<input type="text" class="pPro" size="10" maxlength="10" value="" name="actlDelyDt_B1" ezfname="actlDelyDt_B1" ezfhyo="B" ezftoupper>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlDelyDt_B1', 4 , {EZF_ROW_NUMBER});">
																					<input type="text" class="pPro" size="4" maxlength="4" value="" name="schdDelyTsDplyTxt_B2" ezfname="schdDelyTsDplyTxt_B2" ezfhyo="B" ezftoupper>
																					<select name="rqstRcvDtTxt_B2" ezfname="rqstRcvDtTxt_B2" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" ezfBlank="1" ezfhyo="B" style="width:39;" ezflist="rqstRcvDtTxt_CD,rqstRcvDtTxt_DI,3, ,blank" disabled>
																						<option>AM</option>
																						<option>PM</option>
																					</select>
																				</td>
																				<td><label ezfout name="svcTaskCpltDt_B1" ezfname="svcTaskCpltDt_B1" ezfhyo="B">&nbsp;</label></td>
																				<td><input type="text" class="pPro" size="16" maxlength="60" name="svcTaskStsDescTxt_B1" ezfname="svcTaskStsDescTxt_B1" value="" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="50" name="dsSoLineStsDescTxt_B1" ezfname="dsSoLineStsDescTxt_B1" value="" ezfhyo="B"></td>
																				<td>
																					<input type="text" class="pPro" size="15" maxlength="50" value="" name="carrNm_B1" ezfname="carrNm_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="Carr" name="OpenWin_CarrInfo" onClick="sendServer(this)" id="OpenWin_CarrInfo{EZF_ROW_NUMBER}" ezfhyo="B" disabled>
																				</td>
																				<td>
																					<input type="text" class="pPro" size="13" maxlength="30" value="" name="ProNum_B1" ezfname="ProNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn5" value="Tracking#" name="OpenWin_Tracking" onClick="sendServer(this)" id="OpenWin_Tracking{EZF_ROW_NUMBER}" ezfhyo="B">
																				</td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="shpgSvcLvlDescTxt_B1" ezfname="shpgSvcLvlDescTxt_B1" value="" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="schdCoordStsDescTxt_B1" ezfname="schdCoordStsDescTxt_B1" value="" ezfhyo="B" ezftoupper ></td>
																				<td>&nbsp;</td>
																				<td><input type="text" class="pPro" size="11" maxlength="30" value="" name="totFrtAmt_B1" ezfname="totFrtAmt_B1" ezfhyo="B" ezftoupper></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="360"  name="dsAcctNm_B1" ezfname="dsAcctNm_B1" value="FEDEX123456" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="11"  maxlength="25"  name="ctyAddr_B1" ezfname="ctyAddr_B1" value="NEW YORK" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="13" maxlength="28" value="" name="pickSvcConfigMstrPk_B1" ezfname="pickSvcConfigMstrPk_B1" ezfhyo="B" ezftoupper></td>
																				<td>&nbsp;</td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="dsOrdCatgDescTxt_B1" ezfname="dsOrdCatgDescTxt_B1" value="LEASE, CSA" ezfhyo="B" ezftoupper ></td>
																			</tr>
																			<tr height="28">
																				<td><img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('ExpandHolds',{EZF_ROW_NUMBER})" ezfhyo="B" height="14" id="xxSmryLineFlg#{EZF_ROW_NUMBER}"></td>
																				<td><input type="checkbox" name="xxChkBox_B1" ezfname="xxChkBox_B1" ezfhyo="B" value="Y" id="OnChange_ChkBoxSoNum{EZF_ROW_NUMBER}" onClick="sendServer('OnChange_ChkBoxSoNum', '{EZF_ROW_NUMBER}')"></td>
																				<td><input type="checkbox" name="xxChkBox_B2" ezfname="xxChkBox_B2" ezfhyo="B" value="Y" id="OnChange_ChkBoxSoSlipNum{EZF_ROW_NUMBER}" onClick="sendServer('OnChange_ChkBoxSoSlipNum', '{EZF_ROW_NUMBER}')"></td>
																				<td><input type="button" class="pBtn5" value="Edit" name="Open_DelyInstn" onClick="sendServer(this)" id="Open_DelyInstn{EZF_ROW_NUMBER}" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="13" maxlength="28" name="shipSvcConfigMstrPk_B1" ezfname="shipSvcConfigMstrPk_B1" value="563856" ezfhyo="B"></td>
																				<td><a href="#" ezfhyo="B" onclick="sendServer('Open_OrdEntry')" tabindex="-1" id="Open_OrdEntry{EZF_ROW_NUMBER}" ><label ezfout name="trxHdrNum_B1" ezfname="trxHdrNum_B1" ezfhyo="B">CP004000</label></a></td>
																				<td>&nbsp;</td>
																				<td><a href="#" ezfhyo="B" onclick="sendServer('Open_MngSO')" tabindex="-1" id="Open_MngSO{EZF_ROW_NUMBER}" ><label ezfout name="soNum_B1" ezfname="soNum_B1" ezfhyo="B">SH004000</label></a></td>
																				<td>&nbsp;</td>
																				<td><input type="text" class="pPro" size="16" maxlength="60" name="xxPsnFirstMidLastNm_B1" ezfname="xxPsnFirstMidLastNm_B1" value="TARO YAMADA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="30" name="rtlWhNm_B1" ezfname="rtlWhNm_B1" value="ATLANTA" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="rtlSwhCd_B1" ezfname="rtlSwhCd_B1" value="" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="10" maxlength="20" name="stCd_B1" ezfname="stCd_B1" value="NY" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="16" maxlength="25" name="xxPsnFirstMidLastNm_B2" ezfname="xxPsnFirstMidLastNm_B2" value="HANAKO CANON" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="mdseCd_B1" ezfname="mdseCd_B1" value="" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="20" maxlength="30" name="mdseDescShortTxt_B1" ezfname="mdseDescShortTxt_B1" value="" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="14" maxlength="50" name="backOrdImpctTpDescTxt_B1" ezfname="backOrdImpctTpDescTxt_B1" value="" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="13" maxlength="10" value="" name="xxShipQty_B1" ezfname="xxShipQty_B1" ezfhyo="B" ezftoupper></td>
																				<td>
																					<input type="text"  class="pPro" size="12" maxlength="30" value="" name="serNum_B1" ezfname="serNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="S" name="OpenWin_SerEntry" onClick="sendServer(this)" id="OpenWin_SerEntry{EZF_ROW_NUMBER}" ezfhyo="B" disabled>
																				</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																				<td>
																					<select name="carrRsnCd_B1" ezfname="carrRsnCd_B1" ezfCodeName="carrRsnCd_P" ezfDispName="carrRsnDescTxt_P" ezfBlank="1" ezfhyo="B" style="width:100;" ezflist="carrRsnCd_P,carrRsnDescTxt_P,99, ,blank" disabled>
																						<option></option>
																						<option>Delivered</option>
																						<option>Not Delivered</option>
																						<option>Flat Tire</option>
																						<option>Bad Weather</option>
																					</select>
																				</td>
																				<td>
																					<input type="text" class="pPro" size="10" maxlength="10" value="" name="actlDelyDt_B1" ezfname="actlDelyDt_B1" ezfhyo="B" ezftoupper>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlDelyDt_B1', 4 , {EZF_ROW_NUMBER});">
																					<input type="text" class="pPro" size="4" maxlength="4" value="" name="schdDelyTsDplyTxt_B2" ezfname="schdDelyTsDplyTxt_B2" ezfhyo="B" ezftoupper>
																					<select name="rqstRcvDtTxt_B2" ezfname="rqstRcvDtTxt_B2" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" ezfBlank="1" ezfhyo="B" style="width:39;" ezflist="rqstRcvDtTxt_CD,rqstRcvDtTxt_DI,3, ,blank" disabled>
																						<option>AM</option>
																						<option>PM</option>
																					</select>
																				</td>
																				<td><label ezfout name="svcTaskCpltDt_B1" ezfname="svcTaskCpltDt_B1" ezfhyo="B">&nbsp;</label></td>
																				<td><input type="text" class="pPro" size="16" maxlength="60" name="svcTaskStsDescTxt_B1" ezfname="svcTaskStsDescTxt_B1" value="" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16" maxlength="50" name="dsSoLineStsDescTxt_B1" ezfname="dsSoLineStsDescTxt_B1" value="" ezfhyo="B"></td>
																				<td>
																					<input type="text" class="pPro" size="15" maxlength="50" value="" name="carrNm_B1" ezfname="carrNm_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn1" value="Carr" name="OpenWin_CarrInfo" onClick="sendServer(this)" id="OpenWin_CarrInfo{EZF_ROW_NUMBER}" ezfhyo="B" disabled>
																				</td>
																				<td>
																					<input type="text" class="pPro" size="13" maxlength="30" value="" name="proNum_B1" ezfname="proNum_B1" ezfhyo="B" ezftoupper>
																					<input type="button" class="pBtn5" value="Tracking#" name="OpenWin_Tracking" onClick="sendServer(this)" id="OpenWin_Tracking{EZF_ROW_NUMBER}" ezfhyo="B">
																				</td>
																				<td><input type="text" class="pPro" size="16" maxlength="20" name="shpgSvcLvlDescTxt_B1" ezfname="shpgSvcLvlDescTxt_B1" value="" ezfhyo="B"></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="schdCoordStsDescTxt_B1" ezfname="schdCoordStsDescTxt_B1" value="" ezfhyo="B" ezftoupper ></td>
																				<td>&nbsp;</td>
																				<td><input type="text" class="pPro" size="11" maxlength="30" value="" name="totFrtAmt_B1" ezfname="totFrtAmt_B1" ezfhyo="B" ezftoupper></td>
																				<td><input type="text" class="pPro" size="16"  maxlength="360"  name="dsAcctNm_B1" ezfname="dsAcctNm_B1" value="AMEX ACCOUNT" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="11"  maxlength="25"  name="ctyAddr_B1" ezfname="ctyAddr_B1" value="PORT WASHINGTON" ezfhyo="B" ezftoupper ></td>
																				<td><input type="text" class="pPro" size="13" maxlength="28" value="34234234" name="pickSvcConfigMstrPk_B1" ezfname="pickSvcConfigMstrPk_B1" ezfhyo="B" ezftoupper></td>
																				<td>&nbsp;</td>
																				<td><input type="text" class="pPro" size="16"  maxlength="50"  name="dsOrdCatgDescTxt_B1" ezfname="dsOrdCatgDescTxt_B1" value="LEASE, CSA" ezfhyo="B" ezftoupper ></td>
																			</tr>
																			</ezf:skip>
																		</table>
																	</div><!-- rightTbl -->
																</div> <!-- right table -->
															</div> <!-- parent box -->
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table border="0" cellpadding="0" cellspacing="0" width="100%">
													<col align="left" width="">
													<col align="right" width="">
													<col align="right" width="">
													<col align="right" width="">
													<col align="right" width="">
													<col align="right" width="">
													<tr>
														<td>
															<fieldset>
																<legend>
																	<table>
																		<tr>
																			<td class="stab">Apply to Detail Lines</td>
																		</tr>
																	</table>
																</legend>
																<table border="0" cellpadding="0" cellspacing="0">
																	<col width="45">
																	<col width="7">
																	<col width="78">
																	<col width="110">
																	<col width="103">
																	<col width="145">
																	<col width="54">
																	<col width="83">
																	<col width="">
																	<tr height="24">
																		<td class="stab"><label>Shipping</label></td>
																		<td class="stab"><label>:</label></td>
																		<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_BP" ezfEmulateBtn="1" ezfGuard="OpenWin_AplyCarrCode" >Carrier</ezf:anchor></td>
																		<td><ezf:inputText name="carrNm_BP" ezfName="carrNm_BP" value="FEDEX" otherAttr=" size=\"14\" maxlength=\"50\" ezftoupper=\"\""/></td>
																		<td class="stab"><label>Tracking Number</label></td>
																		<td>
																			<ezf:inputText name="proNum_BP" ezfName="proNum_BP" otherAttr=" size=\"19\" maxlength=\"30\" ezftoupper=\"\""/>
																		</td>
																		<td class="stab"><label>Ship Cost</label></td>
																		<td colspan="3"><ezf:inputText name="totFrtAmt_BP" ezfName="totFrtAmt_BP" otherAttr=" size=\"11\" maxlength=\"30\" ezftoupper=\"\""/></td>
																	</tr>
																	<tr>
																		<td class="stab"><label>Delivery</label></td>
																		<td class="stab"><label>:</label></td>
																		<td class="stab"><label>Carrier Reason</label></td>
																		<td>
																			<ezf:select name="carrRsnCd_BP" ezfName="carrRsnCd_BP" ezfBlank="1" ezfCodeName="carrRsnCd_P" ezfDispName="carrRsnDescTxt_P" otherAttr=" style=\"width:104px\""/>
																		</td>
																		<td class="stab"><label>Actual Delivery Date</label></td>
																		<td colspan="3">
																			<ezf:inputText name="actlDelyDt_BP" ezfName="actlDelyDt_BP" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																			<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'actlDelyDt_BP', 4);">
																		</td>
																		<td>&nbsp;</td>
																		<td align="right"><ezf:inputButton name="Apply" value="Apply" htmlClass="pBtn4" /></td>
																	</tr>
																</table>
															</fieldset>
														</td>
														<td>&nbsp;</td>
														<td align="right">
															<ezf:inputButton name="Ship" value="Ship Confirm" htmlClass="pBtn10" />
														</td>
														<td align="right">
															<ezf:inputButton name="SO_Close" value="SO Close" htmlClass="pBtn10" />
														</td>
														<td align="right">
															<ezf:inputButton name="Delivery_Conf" value="Delivery Confirm" htmlClass="pBtn10" />
														</td>
														<td align="right">
															<ezf:inputButton name="SO_Cancel" value="Cancel" htmlClass="pBtn10" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								</c:when>
								</c:choose>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<c:choose>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Scheduling'}">
<script type="text/javascript" defer>
S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true, true);
</script>
</c:when>
</c:choose>

<c:choose>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Deliveries'}">
<script type="text/javascript" defer>
S21TableUI.initialize("parentBoxB", "BHEAD", "B", -1, true, true);
</script>
</c:when>
</c:choose>

<%-- **** Task specific area ends here **** --%>
