<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230808192903 --%>
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
			<input type="hidden" name="pageID" value="NLBL2020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Manage Shipping Orders">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<!-- #################################################### HEADER ################################################### -->
				<!-- ###TAB - HEAD -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Manage Shipping Order" class="pTab_ON"><a href="#">Mng Shpg Ord</a></li>
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
				<!-- ###TAB - BODY -->
				<div class="pTab_BODY">
					<!-- Search Option -->
					<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="152">
						<col width="345">
						<col width="110">
						<col width="300">
						<col width="">
						<tr>
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk_PS" ezfName="srchOptPk_PS" ezfBlank="1" ezfCodeName="srchOptPk_PD" ezfDispName="srchOptNm_PD" otherEvent1=" onchange=\"sendServer('Select_Search')\"" otherAttr=" style=\"width:320px\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td class="stab"><ezf:inputText name="srchOptNm_H1" ezfName="srchOptNm_H1" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
							<td>
								<ezf:inputButton name="Save_Search" value="Save Search" htmlClass="pBtn8" />
								<ezf:inputButton name="Delete_Search" value="Delete Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					<!-- Search Header -->
					<hr style="height: 0px;" cellpadding="0">
					<table border="0" cellpadding="0" cellspacing="0" height="">
						<tr>
							<td valign="top">
                            </td>
							<td valign="top" width="">
								<table cellpadding="0" height="132">
									<col align="left" width="112">
									<col align="left" width="136">
									<tr>
										<td class="stab">Source Order Number</td>
										<td><ezf:inputText name="trxHdrNum_H1" ezfName="trxHdrNum_H1" value="XX" otherAttr=" size=\"18\" maxlength=\"8\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">Shipping Order Number</td>
										<td><ezf:inputText name="soNum_H1" ezfName="soNum_H1" value="XX" otherAttr=" size=\"18\" maxlength=\"8\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">Shipping Order Status</td>
										<td>
											<ezf:select name="dsSoLineStsCd_PS" ezfName="dsSoLineStsCd_PS" ezfBlank="1" ezfCodeName="dsSoLineStsCd_PD" ezfDispName="dsSoLineStsDescTxt_PD" otherAttr=" style=\"width:132px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Shipping Order Source</td>
										<td>
											<ezf:select name="sceOrdTpCd_PS" ezfName="sceOrdTpCd_PS" ezfBlank="1" ezfCodeName="sceOrdTpCd_PD" ezfDispName="sceOrdTpNm_PD" otherAttr=" style=\"width:132px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Service Level</td>
										<td>
											<ezf:select name="shpgSvcLvlCd_PS" ezfName="shpgSvcLvlCd_PS" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_PD" ezfDispName="shpgSvcLvlDescTxt_PD" otherAttr=" style=\"width:132px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_CF" ezfEmulateBtn="1" ezfGuard="OpenWin_ConfigID" >Config ID</ezf:anchor></td>
										<td>
											<ezf:inputText name="svcConfigMstrPk_H1" ezfName="svcConfigMstrPk_H1" value="XX" otherAttr=" size=\"18\" maxlength=\"28\" ezftoupper=\"\""/>
										</td>
									</tr>
								</table>
							</td>
							<td valign="top" width="">
								<table cellpadding="0" width="">
									<col align="left" width="100">
									<col align="left" width="80">
									<col align="left" width="35">
									<col align="left" width="120">
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_WR" ezfEmulateBtn="1" ezfGuard="Open_Location_Info_Popup" >Source WH(*)</ezf:anchor></td>
										<td><ezf:inputText name="xxRtrnInvtyLocSrchTxt_RW" ezfName="xxRtrnInvtyLocSrchTxt_RW" value="12345678" otherAttr=" size=\"10\" maxlength=\"1000\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_RtlWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlWhNm_H1" ezfName="rtlWhNm_H1" value="123456789012345" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_WS" ezfEmulateBtn="1" ezfGuard="Open_Location_Info_Popup_Swh" >Source SWH(*)</ezf:anchor></td>
										<td><ezf:inputText name="xxRtrnInvtyLocSrchTxt_SW" ezfName="xxRtrnInvtyLocSrchTxt_SW" value="XX" otherAttr=" size=\"10\" maxlength=\"1000\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_RtlSWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlSwhNm_H1" ezfName="rtlSwhNm_H1" value="XX" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_SP" ezfEmulateBtn="1" ezfGuard="Open_Ship_To_Customer_Popup" >Ship To Customer(*)</ezf:anchor></td>
										<td><ezf:inputText name="shipToCustCd_H1" ezfName="shipToCustCd_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_ShipToCustInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" value="XX" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_CA" ezfEmulateBtn="1" ezfGuard="Open_Carrier_Popup" >Carrier(*)</ezf:anchor></td>
										<td><ezf:inputText name="carrCd_H1" ezfName="carrCd_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_CarrInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="carrNm_H1" ezfName="carrNm_H1" value="XX" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab">Tracking#</td>
										<td colspan="3"><ezf:inputText name="proNum_H1" ezfName="proNum_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_SC" ezfEmulateBtn="1" ezfGuard="Open_MDSE_Info_Popup" >Item Number(*)</ezf:anchor></td>
										<td><ezf:inputText name="mdseCd_H1" ezfName="mdseCd_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_MdseInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="mdseDescShortTxt_H1" ezfName="mdseDescShortTxt_H1" value="XX" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
								</table>
							</td>
							<td valign="top" width="">
								<table cellpadding="0" width="">
									<col align="left" width="">
									<col align="left" width="195">
									<col align="left" width="">
									<col align="left" width="195">
									<tr>
										<td class="stab">Shipping Creation Date</td>
										<td>
											<ezf:inputText name="xxTrxDt_FR" ezfName="xxTrxDt_FR" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxTrxDt_FR', 4 );">
											<ezf:inputText name="xxDtNm_F1" ezfName="xxDtNm_F1" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/>
											<ezf:select name="rqstRcvDtTxt_F1" ezfName="rqstRcvDtTxt_F1" ezfBlank="1" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" htmlClass="stab" otherAttr=" style=\"width:39px;\""/>
										</td>
										<td class="stab" align="center">-</td>
										<td>
											<ezf:inputText name="xxTrxDt_TO" ezfName="xxTrxDt_TO" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxTrxDt_TO', 4 );">
											<ezf:inputText name="xxDtNm_T1" ezfName="xxDtNm_T1" value="05:00" otherAttr=" size=\"5\" maxlength=\"5\""/>
											<ezf:select name="rqstRcvDtTxt_T1" ezfName="rqstRcvDtTxt_T1" ezfBlank="1" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" htmlClass="stab" otherAttr=" style=\"width:39px;\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Request Delivery Date</td>
										<td>
											<ezf:inputText name="rddDt_FR" ezfName="rddDt_FR" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rddDt_FR', 4 );">
											<ezf:inputText name="xxDtNm_F2" ezfName="xxDtNm_F2" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/>
											<ezf:select name="rqstRcvDtTxt_F2" ezfName="rqstRcvDtTxt_F2" ezfBlank="1" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" htmlClass="stab" otherAttr=" style=\"width:39px;\""/>
										</td>
										<td class="stab" align="center">-</td>
										<td>
											<ezf:inputText name="rddDt_TO" ezfName="rddDt_TO" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rddDt_TO', 4 );">
											<ezf:inputText name="xxDtNm_T2" ezfName="xxDtNm_T2" value="05:00" otherAttr=" size=\"5\" maxlength=\"5\""/>
											<ezf:select name="rqstRcvDtTxt_T2" ezfName="rqstRcvDtTxt_T2" ezfBlank="1" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" htmlClass="stab" otherAttr=" style=\"width:39px;\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Need By Date</td>
										<td>
											<ezf:inputText name="xxTrxDt_F3" ezfName="xxTrxDt_F3" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxTrxDt_F3', 4 );">
											<ezf:inputText name="xxDtNm_F3" ezfName="xxDtNm_F3" otherAttr=" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/>
											<ezf:select name="rqstRcvDtTxt_F3" ezfName="rqstRcvDtTxt_F3" ezfBlank="1" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" htmlClass="stab" otherAttr=" style=\"width:39px;\""/>
										</td>
										<td class="stab" align="center">-</td>
										<td>
											<ezf:inputText name="xxTrxDt_T3" ezfName="xxTrxDt_T3" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxTrxDt_T3', 4 );">
											<ezf:inputText name="xxDtNm_T3" ezfName="xxDtNm_T3" value="05:00" otherAttr=" size=\"5\" maxlength=\"5\""/>
											<ezf:select name="rqstRcvDtTxt_T3" ezfName="rqstRcvDtTxt_T3" ezfBlank="1" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_DI" htmlClass="stab" otherAttr=" style=\"width:39px;\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">WMS Dropped</td>
										<td colspan="3">
											<ezf:inputCheckBox name="wmsDropFlg_Y" ezfName="wmsDropFlg_Y" value="Y" />Yes
											<ezf:inputCheckBox name="wmsDropFlg_N" ezfName="wmsDropFlg_N" value="Y" />No
										</td>
									</tr>
									<tr>
										<td class="stab">Include Back Order Line</td>
										<td colspan="3" class="stab">
											<ezf:inputCheckBox name="xxExstFlg_BO" ezfName="xxExstFlg_BO" value="Y" />Yes
										</td>
									</tr>
									<tr>
										<td class="stab">Include Closed Line</td>
										<td colspan="2" class="stab">
											<ezf:inputCheckBox name="xxExstFlg_NO" ezfName="xxExstFlg_NO" value="Y" />Yes
										</td>
										<td align="right" class="stab"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<!-- #################################################### DETAIL ################################################### -->
<%@ page import="business.servlet.NLBL2020.NLBL2020BMsg" %>
<%@ page import="business.servlet.NLBL2020.NLBL2020_ABMsg" %>
<%  NLBL2020BMsg bMsg = (NLBL2020BMsg)databean.getEZDBMsg(); %>
					<table width="100%">
						<col valign="top">
						<tr>
							<td>
								<div class="pTab_HEAD">
									<ul>
										<li class="pTab_ON"  id="SoList" title="SoList"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="Tab_SoList" >SO List</ezf:anchor></li>
										<li class="pTab_OFF" id="PickList" title="PickList"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="Tab_PickList" >Pick List</ezf:anchor></li>
									</ul>
								</div>
								<c:choose>


								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'SoList'}">
								<script type="text/javascript">
									document.getElementById( "SoList" ).className = "pTab_ON";
									document.getElementById( "PickList" ).className = "pTab_OFF";
								</script>
								<div class="pTab_BODY_In">
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="left" width="200">
													<col align="left" width="100">
													<col align="left" width="100">
													<col align="left" width="">
													<col align="right" width="550">
													<tr>
														<td width="200">
															<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
														</td>
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
											<!-- Preferred View -->
												<div id="parentBoxA">

													<table border="0" cellpadding="0" cellspacing="0" style="margin: 5px;">
														<tr>
															<td valign="top">

																<div style="float:left; display:block"> <!-- left table -->
																	<div id='leftTblHead' style="display:block;"></div>
																	<div id='leftTbl' style="float:left; display:block; height:202px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
																</div>  <!-- left table -->
																<div style="float:left"> <!-- right table -->
																	<div id='rightTblHead' style="width:1070px; display:block; overflow:hidden; margin:0px;padding:0px;">

																		<table border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="" style="table-layout: fixed">
																			<col align="center" width="25">		<!-- Check Box 1 -->
																			<col align="center" width="25">		<!-- Check Box 2 -->
																			<col align="center" width="65">		<!-- Source Order# -->
																			<col align="center" width="55">		<!-- Line# -->
																			<col align="center" width="60">		<!-- Shipping Order# -->
																			<col align="center" width="50">		<!-- Ship Line# -->
																			<col align="center" width="115">	<!-- Warehouse -->
																			<col align="center" width="78">		<!-- Sub-WH -->
																			<col align="center" width="55">		<!-- Ship-To Address -->
																			<col align="center" width="135">	<!-- Customer Contact -->
																			<col align="center" width="114">	<!-- Item# -->
																			<col align="center" width="115">	<!-- Item Description -->
																			<col align="center" width="100">	<!-- Essential Critical -->
																			<col align="center" width="78">		<!-- Ship Order Qty -->
																			<col align="center" width="66">		<!-- UOM -->
																			<col align="center" width="115">	<!-- Serial# -->
																			<col align="center" width="135">	<!-- Shipping Creation Date/Time -->
																			<col align="center" width="75">		<!-- Requested Date -->
																			<col align="center" width="135">	<!-- Need By Date/Time -->
																			<col align="center" width="78">		<!-- Picked Qty -->
																			<col align="center" width="78">		<!-- Ship Qty -->
																			<col align="center" width="110">	<!-- Pack Slip# -->
																			<col align="center" width="135">	<!-- Pick Confirm Date/Time -->
																			<col align="center" width="135">	<!-- Pack Confirm Date/Time -->
																			<col align="center" width="135">	<!-- Ship Confirm Date/Time -->
																			<col align="center" width="140">	<!-- Requested Carrier -->
																			<col align="center" width="140">	<!-- Requested Service Level -->
																			<col align="center" width="114">	<!-- Ship Cost -->
																			<col align="center" width="114">	<!-- Carrier account# -->
																			<col align="center" width="135">	<!-- Sales Rep -->
																			<col align="center" width="115">	<!-- Shipping Order Status -->
																			<col align="center" width="115">	<!-- Scheduling Status -->
																			<col align="center" width="160">	<!-- Actual Carrier -->
																			<col align="center" width="140">	<!-- Actual Service Level -->
																			<col align="center" width="115">	<!-- Master Tracking# -->
																			<col align="center" width="215">	<!-- Tracking# -->
																			<col align="center" width="70">		<!-- Shipping Instructions -->
																			<col align="center" width="115">	<!-- Shipping Order Source -->
																			<col align="center" width="100">	<!-- Config ID -->
																			<col align="center" width="100">	<!-- Pick Config ID -->
																			<col align="center" width="20">		<!-- SS -->
																			<col align="center" width="78">		<!-- UOM Each Quantity -->
																			<col align="center" width="133">	<!-- Ship to Customer -->

																			<tr height="42">
																				<td id="AH0"  class="pClothBs"></td>
																				<td id="AH1"  class="pClothBs"></td>
																				<td id="AH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'trxHdrNum_A1' )">Source<br/>Order#<img id="sortIMG.trxHdrNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dplyLineNum_A1' )">Line#<img id="sortIMG.dplyLineNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'soNum_A1' )">Shipping<br/>Order#<img id="sortIMG.soNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'soSlpNum_A1' )">Ship<br/>Line#<img id="sortIMG.soSlpNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhNm_A1' )">Warehouse<img id="sortIMG.rtlWhNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipFromRtlSwhCd_A1' )">Sub-WH<img id="sortIMG.shipFromRtlSwhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToStCd_A1' )">Ship-To<br/>Address<img id="sortIMG.shipToStCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToCtacPsnNm_A1' )">Customer Contact<img id="sortIMG.shipToCtacPsnNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCd_A1' )">Item#<img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescShortTxt_A1' )">Item Description<img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'backOrdImpctTpDescTxt_A1' )">Essential<br/>Critical<img id="sortIMG.backOrdImpctTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shpgQty_A1' )">Ship Order<br/>Qty<img id="sortIMG.shpgQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'basePkgUomCd_A1' )">UOM<img id="sortIMG.basePkgUomCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH15" class="pClothBs">Serial#</td>
																				<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'soCratTs_A1' )">Shipping Creation<br/>Date/Time<img id="sortIMG.soCratTs_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rddDt_A1' )">Requested<br/>Date<img id="sortIMG.rddDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'schdDelyDt_A1' )">Need By<br/>Date<img id="sortIMG.schdDelyDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pickConfQty_A1' )">Picked Qty<img id="sortIMG.pickConfQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipQty_A1' )">Ship Qty<img id="sortIMG.shipQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem19Txt_A1' )">Pack Slip#<img id="sortIMG.xxScrItem19Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxTsDsp19Txt_A2' )">Pick Confirm<br/>Date/Time<img id="sortIMG.xxTsDsp19Txt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxTsDsp19Txt_A5' )">Pack Confirm<br/>Date/Time<img id="sortIMG.xxTsDsp19Txt_A5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxTsDsp19Txt_A3' )">Ship Confirm<br/>Date/Time<img id="sortIMG.xxTsDsp19Txt_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'carrNm_A2' )">Requested Carrier<img id="sortIMG.carrNm_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shpgSvcLvlDescTxt_A1' )">Requested <br/>Service Level<img id="sortIMG.shpgSvcLvlDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'totFrtAmt_A1' )">Ship Cost<img id="sortIMG.totFrtAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'carrAcctNum_A1' )">Carrier Account#<img id="sortIMG.carrAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH29" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'tocNm_A1' )">Sales Rep<img id="sortIMG.tocNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH30" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsSoLineStsDescTxt_A1' )">Shipping Order<br/>Status<img id="sortIMG.dsSoLineStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH31" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'schdCoordStsDescTxt_A1' )">Scheduling Status<img id="sortIMG.schdCoordStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH32" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'carrNm_A1' )">Actual Carrier<img id="sortIMG.carrNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH33" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'actlShpgSvcLvlCd_A1' )">Actual Service Level<img id="sortIMG.actlShpgSvcLvlCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH34" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'proNum_A2' )">Master Tracking#<img id="sortIMG.proNum_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH35" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'proNum_A1' )">Tracking#<img id="sortIMG.proNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH36" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxYesNoCd_A2' )">Shipping<br/>Instructions<img id="sortIMG.xxYesNoCd_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH37" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'sceOrdTpNm_A1' )">Shipping Order<br/>Source<img id="sortIMG.sceOrdTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH38" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcConfigMstrPk_A1' )">Config ID<img id="sortIMG.svcConfigMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH39" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pickSvcConfigMstrPk_A1' )">Pick Config ID<img id="sortIMG.pickSvcConfigMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH40" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'fromStkStsCd_A1' )">SS<img id="sortIMG.fromStkStsCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH41" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'inEachQty_A1' )">UOM Each<br/>Qty<img id="sortIMG.inEachQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="AH42" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToLocNm_A1' )">Ship to Customer<img id="sortIMG.shipToLocNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			</tr>
																		</table>

																	</div>
																	<div  id="rightTbl" style="width:1087px; height:220px; display:block; overflow:scroll; margin:0px; padding:0px;">

																		<table border="1" cellpadding="0" cellspacing="0" id="A" width="4245px" style="table-layout: fixed">
																			<col align="center" width="25">		<!-- Check Box 1 -->
																			<col align="center" width="25">		<!-- Check Box 2 -->
																			<col align="center" width="65">		<!-- Source Order# -->
																			<col align="center" width="55">		<!-- Line# -->
																			<col align="center" width="60">		<!-- Shipping Order# -->
																			<col align="center" width="50">		<!-- Ship Line# -->
																			<col align="center" width="115">	<!-- WH -->
																			<col align="center" width="78">		<!-- Sub WH -->
																			<col align="center" width="55">		<!-- Ship-To Address -->
																			<col align="center" width="135">	<!-- Customer Contact -->
																			<col align="center" width="114">	<!-- Item# -->
																			<col align="center" width="115">	<!-- Item Description -->
																			<col align="center" width="100">	<!-- Essential Critical -->
																			<col align="center" width="78">		<!-- Ship Order Qty -->
																			<col align="center" width="66">		<!-- UOM -->
																			<col align="center" width="115">	<!-- Serial# -->
																			<col align="center" width="135">	<!-- Shipping Creation Date/Time -->
																			<col align="center" width="75">		<!-- Requested Date -->
																			<col align="center" width="135">	<!-- Need By Date/Time -->
																			<col align="center" width="78">		<!-- Picked Qty -->
																			<col align="center" width="78">		<!-- Ship Qty -->
																			<col align="center" width="110">	<!-- Pack Slip# -->
																			<col align="center" width="135">	<!-- Pick Confirm Date/Time -->
																			<col align="center" width="135">	<!-- Pack Confirm Date/Time -->
																			<col align="center" width="135">	<!-- Ship Confirm Date/Time -->
																			<col align="center" width="140">	<!-- Requested Carrier -->
																			<col align="center" width="140">	<!-- Requested Service Level -->
																			<col align="center" width="114">	<!-- Ship Cost -->
																			<col align="center" width="114">	<!-- Carrier account# -->
																			<col align="center" width="135">	<!-- Sales Rep -->
																			<col align="center" width="115">	<!-- Shipping Order Status -->
																			<col align="center" width="115">	<!-- Scheduling Status -->
																			<col align="center" width="160">	<!-- Actual Carrier -->
																			<col align="center" width="140">	<!-- Actual Service Level -->
																			<col align="center" width="115">	<!-- Master Tracking# -->
																			<col align="center" width="215">	<!-- Tracking# -->
																			<col align="center" width="70">		<!-- Shipping Instructions -->
																			<col align="center" width="115">	<!-- Shipping Order Source -->
																			<col align="center" width="100">	<!-- Config ID-->
																			<col align="center" width="100">	<!-- Pick Config ID-->
																			<col align="center" width="20">		<!-- SS -->
																			<col align="center" width="78">		<!-- UOM Each Qty -->
																			<col align="center" width="133">	<!-- Ship to Customer -->

																			<ezf:row ezfHyo="A">
																				<tr height="28" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
																					<td><ezf:inputCheckBox name="xxExstFlg_A1" ezfName="xxExstFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServerForPreferredView('OnChange_ChkBoxHdr','{EZF_ROW_NUMBER}')" otherAttr=" id=\"headChkBox{EZF_ROW_NUMBER}\""/></td>
																					<td><ezf:inputCheckBox name="xxExstFlg_A2" ezfName="xxExstFlg_A2" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServerForPreferredView('OnChange_ChkBoxLine','{EZF_ROW_NUMBER}')" otherAttr=" id=\"subChkBox{EZF_ROW_NUMBER}\""/></td>
																					<td>
																						<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_SrcOrdEntry" otherAttr=" ezfanchortext=\"\" id=\"openSrcOrdEntry{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_A1" ezfName="trxHdrNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																					</td>
																					<td><ezf:label name="dplyLineNum_A1" ezfName="dplyLineNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"srcOrdLineNum{EZF_ROW_NUMBER}\""/></td>
																					<td><ezf:label name="soNum_A1" ezfName="soNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"soNum{EZF_ROW_NUMBER}\""/></td>
																					<td><ezf:label name="soSlpNum_A1" ezfName="soSlpNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"soLineNum{EZF_ROW_NUMBER}\""/></td>
																					<td><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="MONROE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" id=\"rtlWhNm{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="shipFromRtlSwhCd_A1" ezfName="shipFromRtlSwhCd_A1" value="U90" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" id=\"rtlSWhNm{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:label name="shipToStCd_A1" ezfName="shipToStCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:inputText name="shipToCtacPsnNm_A1" ezfName="shipToCtacPsnNm_A1" value="GARY HIMMERT" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"25\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="8456B003AA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"16\" id=\"mdseCd{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="IR-ADV C350IF 1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"mdseNm{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="backOrdImpctTpDescTxt_A1" ezfName="backOrdImpctTpDescTxt_A1" value="Critical" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" id=\"backOrdImpct{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="shpgQty_A1" ezfName="shpgQty_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"shpgQty{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:label name="basePkgUomCd_A1" ezfName="basePkgUomCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"uom{EZF_ROW_NUMBER}\""/></td>
																					<td>
																						<ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="XYZ00566" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/>
																						<ezf:inputButton name="OpenWin_SerEntry" value="S" ezfHyo="A" ezfArrayNo="0" />
																					</td>
																					<td><ezf:label name="xxTsDsp19Txt_A1" ezfName="xxTsDsp19Txt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:label name="rddDt_A1" ezfName="rddDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:label name="xxTsDsp19Txt_A4" ezfName="xxTsDsp19Txt_A4" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:inputText name="pickConfQty_A1" ezfName="pickConfQty_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"pickConfQty{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="shipQty_A1" ezfName="shipQty_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"shipQty{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:label name="xxScrItem19Txt_A1" ezfName="xxScrItem19Txt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:label name="xxTsDsp19Txt_A2" ezfName="xxTsDsp19Txt_A2" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:label name="xxTsDsp19Txt_A5" ezfName="xxTsDsp19Txt_A5" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:label name="xxTsDsp19Txt_A3" ezfName="xxTsDsp19Txt_A3" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td>
																						<ezf:inputText name="carrNm_A2" ezfName="carrNm_A2" value="Actual Carrier" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
																					</td>	
																					<td><ezf:inputText name="shpgSvcLvlDescTxt_A1" ezfName="shpgSvcLvlDescTxt_A1" value="Ground Service" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="totFrtAmt_A1" ezfName="totFrtAmt_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"22\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="carrAcctNum_A1" ezfName="carrAcctNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td> <!-- QC#10302 -->
																					<td><ezf:inputText name="tocNm_A1" ezfName="tocNm_A1" value="YASUHIKO IMAZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="dsSoLineStsDescTxt_A1" ezfName="dsSoLineStsDescTxt_A1" value="Allocated" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"shpgOrdSts{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="schdCoordStsDescTxt_A1" ezfName="schdCoordStsDescTxt_A1" value="Awaiting Scheduling" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/></td>
																					<td>
																						<ezf:inputText name="carrNm_A1" ezfName="carrNm_A1" value="UNITED PARCEL SER" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/>
																						<ezf:inputButton name="OpenWin_CarrInfo" value="Carr" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_CarrInfo{EZF_ROW_NUMBER}\""/>
																					</td> <!-- QC#10302 -->
																					<td>
																						<ezf:select name="actlShpgSvcLvlCd_A1" ezfName="actlShpgSvcLvlCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_PD" ezfDispName="shpgSvcLvlDescTxt_PD" otherAttr=" style=\"width:132px\""/>
																					</td> <!-- QC#10302 -->
																					<td><ezf:inputText name="proNum_A2" ezfName="proNum_A2" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
																					<td><!-- QC#21913-Sol#500 -->
																						<ezf:inputText name="proNum_A1" ezfName="proNum_A1" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
																						<ezf:inputButton name="OpenWin_Tracking" value="Tracking#" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn5" otherAttr=" id=\"OpenWin_Tracking{EZF_ROW_NUMBER}\""/>
																					</td>
																					<td>
																						<ezf:anchor name="xxLinkAncr_A1" ezfName="xxLinkAncr_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ShpgInstn" otherAttr=" id=\"xxLinkAncr_A1#{EZF_ROW_NUMBER}\""><ezf:label name="xxYesNoCd_A2" ezfName="xxYesNoCd_A2" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																					</td>
																					<td><ezf:inputText name="sceOrdTpNm_A1" ezfName="sceOrdTpNm_A1" value="Sales Order" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" id=\"shpgOrdTp{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" value="5000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"28\" id=\"shipConf{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="pickSvcConfigMstrPk_A1" ezfName="pickSvcConfigMstrPk_A1" value="1000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"28\" id=\"pickConf{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:label name="fromStkStsCd_A1" ezfName="fromStkStsCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"stkStsCd{EZF_ROW_NUMBER}\""/></td>
																					<td><ezf:inputText name="inEachQty_A1" ezfName="inEachQty_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"inEachQty{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="shipToLocNm_A1" ezfName="shipToLocNm_A1" value="MEGARAM COMPUTER" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"360\" ezftoupper=\"\""/></td>
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

															</td>
														</tr>
													</table>
												</div>
												<!-- Preferred View End -->
											</td>
										</tr>
										<tr>
											<td>
												<table border="0" cellpadding="0" cellspacing="0" width="100%">
													<col align="left" width="">
													<col align="right" width="">
													<tr>
														<td>
															<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																<col align="left" width="680">
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
																					<col width=""><!-- Carrier -->
																					<col width="">
																					<col width="10">
																					<col width=""><!-- Ship Cost -->
																					<col width="">
																					<col width="10">
																					<col width="45"><!-- Tracking# -->
																					<col width="">
																					<col width="10">
																					<col width="60"><!-- Apply -->
																				<tr>
																					<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_D1" ezfEmulateBtn="1" ezfGuard="OpenWin_AplyCarrCode" >Carrier</ezf:anchor></td>
																					<td><ezf:inputText name="carrNm_D1" ezfName="carrNm_D1" value="FEDEX" otherAttr=" size=\"14\" maxlength=\"50\" ezftoupper=\"\""/></td>
																					<td>&nbsp;</td>
																					<td class="stab"><label>Ship Cost</labe></td>
																					<td><ezf:inputText name="totFrtAmt_D1" ezfName="totFrtAmt_D1" value="1,200.55" otherAttr=" size=\"15\" maxlength=\"22\""/></td>
																					<td>&nbsp;</td>
																					<td class="stab"><label>Tracking#</labe></td>
																					<td><ezf:inputText name="proNum_D1" ezfName="proNum_D1" value="123456789012345678901234567890" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
																					<td>&nbsp;</td>
																					<td><ezf:inputButton name="OnClick_Apply" value="Apply" htmlClass="pBtn7" /></td>
																				</tr>
																			</table>
																		</fieldset>
																	</td>
																</tr>
															</table>
														</td>
														<td>
															<table border="0" cellpadding="1" cellspacing="1">
																<col align="right" width="">
																<col align="right" width="">
																<col align="right" width="">
																<tr>
																	<td>
																		<ezf:inputButton name="Print" value="Print" htmlClass="pBtn7" />
																	</td>
																	<td colspan = "2" align="left">
																		<ezf:inputButton name="CustomDocPrint" value="Custom Doc Print" htmlClass="pBtn9" />
																	</td>
																</tr>
																<tr>
																	<td>
																		<ezf:inputButton name="Cancel" value="Cancel" htmlClass="pBtn7" />
																	</td>
																	<td>
																		<ezf:inputButton name="Ship" value="Ship Confirm" htmlClass="pBtn7" />
																	</td>
																	<td>
																		<ezf:inputButton name="SO_Close" value="SO Close" htmlClass="pBtn7" />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								</c:when>


								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'PickList'}">
								<script type="text/javascript">
									document.getElementById( "SoList" ).className = "pTab_OFF";
									document.getElementById( "PickList" ).className = "pTab_ON";
								</script>
								<div class="pTab_BODY_In">
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="left" width="`">
													<col align="left" width="`">
													<col align="left" width="">
													<col align="right" width="550">
													<tr>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
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

															<div id="topTBL_B" style="width:1070px; display:block; overflow-x:hidden; height:42; overflow-y:hidden;" onScroll="synchroScrollLeft( this.id, new Array( 'bottomTBL_B' ) );">
																<table border="1" cellpadding="1" cellspacing="0" height="42" style="table-layout: fixed">
															
																	<col align="center" width="65">		<!-- Source Order # -->
																	<col align="center" width="53">		<!-- Source Order Line# -->
																	<col align="center" width="65">		<!-- Shipping Order # -->
																	<col align="center" width="41">		<!-- Shipping Order Line# -->
																	<col align="center" width="100">	<!-- WH Name -->
																	<col align="center" width="55">	    <!-- Sub WH -->
																	<col align="center" width="100">	<!-- Item# -->
																	<col align="center" width="110">	<!-- Item Description -->
																	<col align="center" width="70">		<!-- Pick Qty -->
																	<col align="center" width="100">	<!-- Serial# -->
																	<col align="center" width="78">		<!-- Pick Config -->
																	<col align="center" width="70">		<!-- OrderQty -->
																	<col align="center" width="78">		<!-- Picked Qty -->
																	<col align="center" width="80">		<!-- Shipped Qty -->

																	<tr>
																		<td class="pClothBs" colspan="2">Source Order</td>
																		<td class="pClothBs" colspan="2">Shipping Order</td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rtlWhNm_B1' )">WH Name<img id="sortIMG.rtlWhNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'shipFromRtlSwhCd_B1' )">Sub WH<img id="sortIMG.shipFromRtlSwhCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" colspan="2">Item</td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'pickConfQty_B1' )">Pick Qty<img id="sortIMG.pickConfQty_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'serNum_B1' )">Serial#<img id="sortIMG.serNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'pickSvcConfigMstrPk_B1' )">Pick Config<img id="sortIMG.pickSvcConfigMstrPk_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'shpgQty_B1' )">Order Qty<img id="sortIMG.shpgQty_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'pickCpltQty_B1' )">Picked Qty<img id="sortIMG.pickCpltQty_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'shipQty_B1' )">Shipped Qty<img id="sortIMG.shipQty_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	</tr>
																	<tr>
																		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'trxHdrNum_B1' )">Number<img id="sortIMG.trxHdrNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'dplyLineNum_B1' )">Line#<img id="sortIMG.dplyLineNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'soNum_B1' )">Number<img id="sortIMG.soNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'soSlpNum_B1' )">Line#<img id="sortIMG.soSlpNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdseCd_B1' )">Number<img id="sortIMG.mdseCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdseDescShortTxt_B1' )">Description<img id="sortIMG.mdseDescShortTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	</tr>
																</table>
															</div>
															<div id="bottomTBL_B" style="display:block; width:1087px; height:274; overflow:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'topTBL_B' ) );">
																<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed" >
																	<col align="center" width="65">		<!-- Source Order # -->
																	<col align="center" width="53">		<!-- Source Order Line# -->
																	<col align="center" width="65">		<!-- Shipping Order # -->
																	<col align="center" width="41">		<!-- Shipping Order Line# -->
																	<col align="center" width="100">	<!-- WH Name -->
																	<col align="center" width="55">	    <!-- Sub WH -->
																	<col align="center" width="100">	<!-- Item# -->
																	<col align="center" width="110">	<!-- Item Description -->
																	<col align="center" width="70">		<!-- Pick Qty -->
																	<col align="center" width="100">	<!-- Serial# -->
																	<col align="center" width="78">		<!-- Pick Config -->
																	<col align="center" width="70">		<!-- OrderQty -->
																	<col align="center" width="78">		<!-- Picked Qty -->
																	<col align="center" width="80">		<!-- Shipped Qty -->

																	<ezf:row ezfHyo="B">
																		<tr height="28" id="B_rightTBLRow#{EZF_ROW_NUMBER}">
																			<td><ezf:label name="trxHdrNum_B1" ezfName="trxHdrNum_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"trxHdrNum_B1{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:label name="dplyLineNum_B1" ezfName="dplyLineNum_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"dplyLineNum_B1{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:label name="soNum_B1" ezfName="soNum_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"soNum_B1{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:label name="soSlpNum_B1" ezfName="soSlpNum_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"soSlpNum_B1{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputText name="rtlWhNm_B1" ezfName="rtlWhNm_B1" value="MONROE" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" id=\"rtlWhNm_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipFromRtlSwhCd_B1" ezfName="shipFromRtlSwhCd_B1" value="U90" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" id=\"shipFromRtlSwhCd_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="mdseCd_B1" ezfName="mdseCd_B1" value="8456B003AA" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"16\" id=\"mdseCd_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="mdseDescShortTxt_B1" ezfName="mdseDescShortTxt_B1" value="IR-ADV C350IF 1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"mdseDescShortTxt_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="pickConfQty_B1" ezfName="pickConfQty_B1" value="0" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"10\" id=\"pickConfQty_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="serNum_B1" ezfName="serNum_B1" value="XYZ00566" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="pickSvcConfigMstrPk_B1" ezfName="pickSvcConfigMstrPk_B1" value="5000" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"28\" id=\"pickSvcConfigMstrPk_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shpgQty_B1" ezfName="shpgQty_B1" value="1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"10\" id=\"shpgQty_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="pickCpltQty_B1" ezfName="pickCpltQty_B1" value="0" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"10\" id=\"pickCpltQty_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipQty_B1" ezfName="shipQty_B1" value="0" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"10\" id=\"shipQty_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																			<td class="pAuditInfo">
																				<ezf:inputHidden name="xxRecHistCratTs_B1" ezfName="xxRecHistCratTs_B1" ezfHyo="B" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistCratByNm_B1" ezfName="xxRecHistCratByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistUpdTs_B1" ezfName="xxRecHistUpdTs_B1" ezfHyo="B" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistUpdByNm_B1" ezfName="xxRecHistUpdByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistTblNm_B1" ezfName="xxRecHistTblNm_B1" ezfHyo="B" ezfArrayNo="0" />
																			</td>
																		</tr>
																	</ezf:row>
																	<ezf:skip>
																	</ezf:skip>
																</table>
															</div>
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

<!-- Priferred View -->
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true, true);
</script>



<%-- **** Task specific area ends here **** --%>
