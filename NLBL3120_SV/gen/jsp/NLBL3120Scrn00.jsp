<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170628094656 --%>
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
			<input type="hidden" name="pageID" value="NLBL3120Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Workload Balancing and Monitor">

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
										<li title="Location Info" class="pTab_ON"><a href="#">Mng Dely</a></li>
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
                        <!-- ################################################## Search Criteria - [START] ################################################## -->
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
							<td valign="top">
                            </td>
							<td valign="top" width="">
								<table cellpadding="0" height="112">
									<col align="left" width="90">
									<col align="left" width="125">
									<tr>
										<td class="stab">Order Number(*)</td>
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
										<td class="stab">
											<ezf:anchor name="xxLinkAncr_ML" ezfName="xxLinkAncr_ML" ezfEmulateBtn="1" ezfGuard="OpenWin_ModelName" otherAttr=" id=\"xxLinkAncr_ML\" ezfanchortext=\"\"">Model</ezf:anchor>
										</td>
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
									<col align="left" width="135">
									<col align="left" width="125">
									<tr>
										<td class="stab">Shipping Order Number(*)</td>
										<td><ezf:inputText name="soNum" ezfName="soNum" value="XX" otherAttr=" size=\"16\" maxlength=\"8\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">Shipping Order Status</td>
										<td>
											<ezf:select name="dsSoLineStsCd" ezfName="dsSoLineStsCd" ezfBlank="1" ezfCodeName="dsSoLineStsCd_P" ezfDispName="dsSoLineStsDescTxt_P" otherAttr=" style=\"width:117px\""/>
										</td>
									</tr>
								
									<tr>
										<td class="stab">RWS Number(*)</td>
										<td>
											<ezf:inputText name="rwsNum" ezfName="rwsNum" value="XX" otherAttr=" size=\"16\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">RWS Status</td>
										<td>
											<ezf:select name="rwsStsCd" ezfName="rwsStsCd" ezfBlank="1" ezfCodeName="rwsStsCd_P" ezfDispName="rwsStsDescTxt_P" otherAttr=" style=\"width:117px\""/>
										</td>
									</tr>
	
									<tr>
										<td class="stab">Scheduling Status</td>
										<td>
											<ezf:select name="schdCoordStsCd" ezfName="schdCoordStsCd" ezfBlank="1" ezfCodeName="schdCoordStsCd_P" ezfDispName="schdCoordStsDescTxt_P" otherAttr=" style=\"width:117px\""/>
										</td>
									</tr>
						
								</table>
							</td>
							<td valign="top" width="" cellpadding="0">
								<table cellpadding="0" width="">
									<col align="left" width="65">
									<col align="left" width="65">
									<col align="left" width="35">
									<col align="left" width="105">
									<tr>
										<td class="stab">
											<ezf:anchor name="xxLinkAncr_WR" ezfName="xxLinkAncr_WR" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfoRtlWh" otherAttr=" id=\"xxLinkAncr_WR\" ezfanchortext=\"\"">Warehouse</ezf:anchor>
										</td>
										<td><ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="12345678" otherAttr=" size=\"8\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_RtlWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="123456789012345" otherAttr=" size=\"16\" maxlength=\"20\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab">
											<ezf:anchor name="xxLinkAncr_SC" ezfName="xxLinkAncr_SC" ezfEmulateBtn="1" ezfGuard="OpenWin_CoordSearchCode" otherAttr=" id=\"xxLinkAncr_SC\" ezfanchortext=\"\"">Coordinator</ezf:anchor>
										</td>
										<td><ezf:inputText name="schdCoordPsnCd" ezfName="schdCoordPsnCd" value="XX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_CoordInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="schdCoordPsnNm" ezfName="schdCoordPsnNm" value="XX" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab">
											<ezf:anchor name="xxLinkAncr_SV" ezfName="xxLinkAncr_SV" ezfEmulateBtn="1" ezfGuard="OpenWin_CoordSearchCode" otherAttr=" id=\"xxLinkAncr_SV\" ezfanchortext=\"\"">Supervisor</ezf:anchor>
										</td>
										<td><ezf:inputText name="supvPsnCd" ezfName="supvPsnCd" value="XX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_SupvInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="xxPsnFirstMidLastNm_SV" ezfName="xxPsnFirstMidLastNm_SV" value="XX" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab">
											<ezf:anchor name="xxLinkAncr_MG" ezfName="xxLinkAncr_MG" ezfEmulateBtn="1" ezfGuard="OpenWin_CoordSearchCode" otherAttr=" id=\"xxLinkAncr_MG\" ezfanchortext=\"\"">Manager</ezf:anchor>
										</td>
										<td><ezf:inputText name="mgrPsnCd" ezfName="mgrPsnCd" value="XX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_MgrInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="xxPsnFirstMidLastNm_MG" ezfName="xxPsnFirstMidLastNm_MG" value="XX" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab">
											<ezf:anchor name="xxLinkAncr_SR" ezfName="xxLinkAncr_SR" ezfEmulateBtn="1" ezfGuard="OpenWin_SlsRepCode" otherAttr=" id=\"xxLinkAncr_SR\" ezfanchortext=\"\"">Sales Rep</ezf:anchor>
										</td>
										<td><ezf:inputText name="slsRepOrSlsTeamTocCd" ezfName="slsRepOrSlsTeamTocCd" value="XX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_SlsRepInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="xxPsnFirstMidLastNm_SR" ezfName="xxPsnFirstMidLastNm_SR" value="XX" otherAttr=" size=\"16\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
								</table>
							</td>
							<td valign="top" width="">
								<table cellpadding="0" width="" style="padding-left:5px;padding-right:8px;">
									<tr>
										<td>
											<table cellpadding="0" width="">
											<col align="left" width="90">
											<col align="left" width="110">
											<col align="left" width="30">
											<col align="left" width="110">
												<tr>
													<td class="stab">Request Date</td>
													<td>
														<ezf:inputText name="rddDt_FR" ezfName="rddDt_FR" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rddDt_FR', 4 );" style="margin-left:-5px;margin-top:-7px;">
													</td>
													<td class="stab">-</td>
													<td>
														<ezf:inputText name="rddDt_TO" ezfName="rddDt_TO" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rddDt_TO', 4 );" style="margin-left:-5px;margin-top:-7px;">
													</td>
												</tr>
												<tr>
													<td class="stab">Schedule Date</td>
													<td>
														<ezf:inputText name="schdCoordDt_FR" ezfName="schdCoordDt_FR" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdCoordDt_FR', 4 );" style="margin-left:-5px;margin-top:-7px;">
													</td>
													<td class="stab">-</td>
													<td>
														<ezf:inputText name="schdCoordDt_TO" ezfName="schdCoordDt_TO" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdCoordDt_TO', 4 );" style="margin-left:-5px;margin-top:-7px;">
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table cellpadding="0" cellspacing="0" style="padding-right:8px;">
												<tr>
													<td>
														<fieldset>
															<legend>Display</legend>
															<table cellpadding="0" cellspacing="0">
																<tr><td><ezf:inputCheckBox name="xxChkBox_DS" ezfName="xxChkBox_DS" value="Y" />Shipping Order</td></tr>
																<tr><td><ezf:inputCheckBox name="xxChkBox_DR" ezfName="xxChkBox_DR" value="Y" />RWS </td></tr>
															</table>
														</fieldset>
													</td>
													<td>
														<fieldset>
															<legend>Coordinator</legend>
															<table cellpadding="0" cellspacing="0">
																<tr><td><ezf:inputCheckBox name="xxChkBox_CA" ezfName="xxChkBox_CA" value="Y" />Assigned</td></tr>
																<tr><td><ezf:inputCheckBox name="xxChkBox_CN" ezfName="xxChkBox_CN" value="Y" />Not Assigned</td></tr>
															</table>
														</fieldset>
													</td>
													<td>
														<table cellpadding="0" cellspacing="0">
															<tr><td>&nbsp;</td></tr>
															<tr>
																<td align="Center" class="stab">
																	<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<HR>
<%-- #################################################### DETAIL ################################################### --%>
<%@ page import="business.servlet.NLBL3120.NLBL3120BMsg" %>
<%@ page import="business.servlet.NLBL3120.NLBL3120_ABMsg" %>
<%  NLBL3120BMsg bMsg = (NLBL3120BMsg) databean.getEZDBMsg(); %>
					<table border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-top:-10px;">
						<tr>
							<td>
								<!--div class="pTab_BODY_In"-->
									<table border="0" cellpadding="1" cellspacing="0" width="100%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="left"  width="76">
													<col align="left"  width="76">
													<col align="left"  width="76">
													<col align="left"  width="76">
													<col align="left"  width="115">
													<col align="left"  width="76">
													<col align="left"  width="25">
													<col align="right" width="580">
													<tr>
														<td><ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="UnSelect_All" value="Unselect All" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="All_Expand" value="All Expand" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="All_Collapse" value="All Collapse" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="MoveWin_CoordWrkBench" value="Coord Work Bench" htmlClass="pBtn6" otherAttr=" style=\"width:110px\""/></td>
														<td><ezf:inputButton name="MoveWin_MngBO" value="Manage BO" htmlClass="pBtn6" /></td>
														<td>&nbsp;</td>
														<td align="right">
															<ezf:skip>
															<table border="0" cellpadding="0" width="100%" style="margin-bottom:2px;">
																<tr>
																	<td align="left">
																		<table border="0" cellpadding="0" align="left" cellspacing="0">
																			<col>
																				<tr>
																				<td>Results 1901 - 2000 of 2000</td>
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
													<tr width="180">
														<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td valign="top">
												<div id="parentBoxA">
													<div style="float:left; display:block"> <!-- left table -->
														<div id='leftTblHead' style="display:block;">
														</div>
														<div id='leftTbl' style="float:left; display:block; height:249; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
														</div>
													</div>  <!-- left table -->
													<%-- LEFT-TABLE(TOP) --%>
													<div style="float:left"> <!-- right table -->
														<div id='rightTblHead' style="width:1111px; overflow-y:hidden; height:40; overflow-x:hidden;">
															<table border="1" cellpadding="1" cellspacing="0" height="40" style="table-layout: fixed" id="AHEAD" width="3865px" style="margin-right:20px">
																<col align="center" width="30">		<!-- Button -->
																<col align="center" width="30">		<!-- Checkbox -->
																<col align="center" width="30">		<!-- Checkbox -->
																<col align="center" width="75">		<!-- Sales Order/Number -->
																<col align="center" width="120">	<!-- Order Category -->
																<col align="center" width="80">	    <!-- Order Reason -->
																
																<col align="center" width="30">		<!-- E/C -->
																<col align="center" width="75">		<!-- Shipping Order/Number -->
																<col align="center" width="75">		<!-- RWS/Number --> 
																<col align="center" width="40">		<!-- WMS Drop ---->
																<col align="center" width="100">	<!-- Configuration PK -->
																<col align="center" width="100">	<!-- Model Name -->
																<col align="center" width="100">	<!-- Pick Config Id -->
																<col align="center" width="120">	<!-- Ware house -->
																<col align="center" width="126">	<!-- Coordinator/Code -->
																<col align="center" width="120">	<!-- Coordinator/Name -->
																
																<col align="center" width="80">		<!-- Customer/Code -->
																<col align="center" width="120">	<!-- Customer/Name -->
																<col align="center" width="120">	<!-- Customer/Address1 -->
																<col align="center" width="100">	<!-- City -->
																<col align="center" width="40">		<!-- State -->
																<col align="center" width="120">	<!-- Scheduling  Status -->
																<col align="center" width="120">	<!-- SO/RWS Status -->
																<col align="center" width="120">	<!-- Shipping/Pickup ServiceLevel -->
																
																<col align="center" width="125">	<!-- Carrier/Code -->
																<col align="center" width="120">	<!-- Carrier/Name -->
																
																<col align="center" width="120">	<!-- Carrer Contact/Phone -->
																<col align="center" width="120">	<!-- Carrier Account# -->
																<col align="center" width="120">	<!-- Sales Rep -->
																<col align="center" width="90">		<!-- Request Delivery Date -->
																<col align="center" width="103">	<!-- Schedule Delivery Date -->
																<col align="center" width="140">		<!-- Ship Conf/RWS Close Date -->

																<tr>
																	<td id="AH0"  class="pClothBs colFix"></td>
																	<td id="AH1"  class="pClothBs colFix"></td>
																	<td id="AH2"  class="pClothBs colFix"></td>
																	<td id="AH3"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'trxHdrNum_A1' )">Sales Order#<img id="sortIMG.trxHdrNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsOrdCatgDescTxt_A1' )">Order Category<img id="sortIMG.dsOrdCatgDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsOrdTpDescTxt_A1' )">Order Reason<img id="sortIMG.dsOrdTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'backOrdImpctTpDescTxt_A1' )">E/C<img id="sortIMG.backOrdImpctTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'soNum_A1' )">Shipping<br>Order#<img id="sortIMG.soNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rwsNum_A1' )">RWS#<img id="sortIMG.rwsNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'wmsDropFlg_A1' )">WMS<br>Drop<img id="sortIMG.wmsDropFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	
																	<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcConfigMstrPk_A1' )">Config Count <BR>/ID<img id="sortIMG.svcConfigMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 't_MdlNm_A1' )">Model<img id="sortIMG.t_MdlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pickSvcConfigMstrPk_A1' )">Pick Config Id<img id="sortIMG.pickSvcConfigMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhNm_A1' )">Warehouse <img id="sortIMG.rtlWhNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'schdCoordPsnCd_A1' )">Coordinator Code<img id="sortIMG.schdCoordPsnCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxPsnFirstMidLastNm_A1' )">Coordinator Name<img id="sortIMG.xxPsnFirstMidLastNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'fromLocCd_A1' )">Customer Code<img id="sortIMG.fromLocCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_A1' )">Customer Name<img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'fromLocAddr_A1' )">Customer Address<img id="sortIMG.fromLocAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'fromLocCtyAddr_A1' )">City<img id="sortIMG.fromLocCtyAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'fromLocStCd_A1' )">State<img id="sortIMG.fromLocStCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'schdCoordStsDescTxt_A1' )">Scheduling Status<img id="sortIMG.schdCoordStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rwsStsDescTxt_A1' )">SO/RWS Status<img id="sortIMG.rwsStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shpgSvcLvlCd_A1' )">Shipping/Pickup Service Level<img id="sortIMG.shpgSvcLvlCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	
																	<td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'carrCd_A1' )">Carrier Code<img id="sortIMG.carrCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'locNm_A1' )">Carrier Name<img id="sortIMG.locNm_A11" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem130Txt_A1' )">Carrie Contact/Phone<img id="sortIMG.xxScrItem130Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	
																	<td id="AH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'carrAcctNum_A1' )">Carrier Account#<img id="sortIMG.carrAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'tocNm_A1' )">Sales Rep<img id="sortIMG.tocNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH29" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rddDt_A1' )">Request<br>Date<img id="sortIMG.rddDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH30" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'schdPickUpDt_A1' )">Scheduled<br>Date<img id="sortIMG.schdPickUpDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td id="AH31" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxTsDsp19Txt_A1' )">Ship Conf/RWS<br>Close Date<img id="sortIMG.xxTsDsp19Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																</tr>
															</table>
														</div>
														<div id="rightTBL" style="width:1127px; height:265; display:block; overflow:scroll; margin:0px; padding:0px;" >
															<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed"  width="3865px" >
																	<col align="center" width="30">		<!-- Button -->
																	<col align="center" width="30">		<!-- Checkbox -->
																	<col align="center" width="30">		<!-- Checkbox -->
																	<col align="left  " width="75">		<!-- Sales Order/Number -->
																	<col align="center" width="120">	<!-- Order Category -->
																	<col align="center" width="80">	    <!-- Order Pearson -->
																	<col align="center" width="30">	    <!-- Essential Critical -->
																	<col align="left"   width="75">		<!-- Shipping Order/Number -->
																	<col align="left"   width="75">		<!-- RWS/Number -->
																	<col align="center" width="40">		<!-- WMS drop -->
																	<col align="center" width="100">	<!-- Configuration PK -->
																	<col align="center" width="100">	<!-- Model Name -->
																	<col align="center" width="100">	<!-- Pick Config Id -->
																	<col align="center" width="120">	<!-- Source WH -->
																	<col align="center" width="126">	<!-- Coordinator/Code -->
																	<col align="center" width="120">	<!-- Coordinator/Name -->
																	<col align="center" width="80">		<!-- Ship To/Code -->
																	<col align="center" width="120">	<!-- Ship To/Name -->
																	<col align="center" width="120">	<!-- Ship To/Address1 -->
																	<col align="center" width="100">	<!-- Ship To/City -->
																	<col align="center" width="40">		<!-- Ship To/State -->
																	<col align="center" width="120">	<!-- Scheduling  Status -->
																	<col align="center" width="120">	<!-- SO/RWS Status -->
																	<col align="center" width="120">	<!-- Shipping/Pickup ServiceLevel -->
																	<col align="center" width="125">	<!-- Carrier/Code -->
																	<col align="center" width="120">	<!-- Carrier/Name -->
																	<col align="center" width="120">	<!-- Carrer Contact/Phone -->
																	<col align="center" width="120">	<!-- Carrier Account# -->
																	<col align="center" width="120">	<!-- Sales Rep -->
																	<col align="center" width="90">		<!-- Request Delivery Date -->
																	<col align="center" width="103">	<!-- Schedule Delivery Date -->
																	<col align="left" width="140">		<!-- Ship Conf/RWS Close Date -->
																<% int i = 0; %>
																<ezf:row ezfHyo="A">
																    <% NLBL3120_ABMsg lineMsg = bMsg.A.no(i++); %>
																   	<% boolean isHdr = "Y".equals(lineMsg.xxPgFlg_A1.getValue()); %>
																	<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																		<td>
																			<% if (isHdr) { %>
																				<% boolean isSmryLine = "Y".equals(lineMsg.xxSmryLineFlg_A1.getValue()); %>
																				<% if (isSmryLine) { %>
																					<img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('All_Expand',{EZF_ROW_NUMBER})" ezfhyo="A" height="14">
																				<% } else if (!isSmryLine) { %>
																					<img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('All_Collapse',{EZF_ROW_NUMBER})" ezfhyo="A" height="14">
																				<% } %>
																			<% } else { %>
																				&nbsp;
																			<% } %>
																		</td>
																		<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServerForPreferredView('OnChange_ChkBoxTrxHdrNum', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"OnChange_ChkBoxTrxHdrNum{EZF_ROW_NUMBER}\""/></td>
																		<td><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServerForPreferredView('OnChange_ChkBoxSoNum', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"OnChange_ChkBoxSoNum{EZF_ROW_NUMBER}\""/></td>
																		<td>
																			<% if (isHdr) { %>
																				<span>
																			<% } else { %>
																				<span style="visibility:hidden;">
																			<% } %>
																				<ezf:anchor name="xxLinkAncr_AT" ezfName="xxLinkAncr_AT" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_OrdEntry" otherAttr=" tabindex=\"-1\"">
																				<ezf:label name="trxHdrNum_A1" ezfName="trxHdrNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																				</span>
																		</td>
																		
																		<td>
																			<% if (isHdr) { %>
																				<span>
																			<% } else { %>
																				<span style="visibility:hidden;">
																			<% } %>
																				<ezf:inputText name="dsOrdCatgDescTxt_A1" ezfName="dsOrdCatgDescTxt_A1" value="LEASE, CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span>
																			<% } else { %>
																				<span style="visibility:hidden;">
																			<% } %>
																				<ezf:inputText name="dsOrdTpDescTxt_A1" ezfName="dsOrdTpDescTxt_A1" value="ESS-CASH" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/>
																				</span>
																		</td>
																		<td><ezf:label name="xxExstFlg_A1" ezfName="xxExstFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td>
																			<ezf:anchor name="xxLinkAncr_AS" ezfName="xxLinkAncr_AS" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_MngDely" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
																			<ezf:label name="soNum_A1" ezfName="soNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																		</td>
																		<td>
																			<ezf:anchor name="xxLinkAncr_AR" ezfName="xxLinkAncr_AR" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_MngRMA" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
																			<ezf:label name="rwsNum_A1" ezfName="rwsNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																		</td>
																		<td><ezf:label name="wmsDropFlg_A1" ezfName="wmsDropFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td>
																			<% if (isHdr) { %>
																				<ezf:inputText name="xxDtlCnt_A1" ezfName="xxDtlCnt_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/>
																			<% } else { %>
																				<ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/>
																			<% } %>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="t_MdlNm_A1" ezfName="t_MdlNm_A1" value="ZZZZZZZZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="pickSvcConfigMstrPk_A1" ezfName="pickSvcConfigMstrPk_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"28\" ezftoupper=\"\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="ATLANTA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="schdCoordPsnCd_A1" ezfName="schdCoordPsnCd_A1" value="C12344" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/>
																				<ezf:inputButton name="OpenWin_CoordSearchInfo" value="Coord" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" otherAttr=" id=\"OpenWin_CoordSearchInfo{EZF_ROW_NUMBER}\" style=\"margin-left:-5px;\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="xxPsnFirstMidLastNm_A1" ezfName="xxPsnFirstMidLastNm_A1" value="TARO YAMADA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"90\" ezftoupper=\"\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="fromLocCd_A1" ezfName="fromLocCd_A1" value="A12345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="FEDEX123456" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"360\" ezftoupper=\"\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="fromLocAddr_A1" ezfName="fromLocAddr_A1" value="Address1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="fromLocCtyAddr_A1" ezfName="fromLocCtyAddr_A1" value="NEW YORK" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"25\" ezftoupper=\"\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:label name="fromLocStCd_A1" ezfName="fromLocStCd_A1" ezfHyo="A" ezfArrayNo="0" />
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:select name="schdCoordStsCd_A1" ezfName="schdCoordStsCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="0" ezfCodeName="schdCoordStsCd_AL" ezfDispName="schdCoordStsDescTxt_AL" ezfCodeDispHyo="A" otherAttr=" style=\"width:100;\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="rwsStsDescTxt_A1" ezfName="rwsStsDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"\" ezftoupper=\"\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:select name="shpgSvcLvlCd_A1" ezfName="shpgSvcLvlCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_AP" ezfDispName="shpgSvcLvlDescTxt_AP" otherAttr=" style=\"width:115px\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="carrCd_A1" ezfName="carrCd_A1" value="APEX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\""/>
																				<ezf:inputButton name="OpenWin_CarrInfo" value="Carr" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" otherAttr=" id=\"OpenWin_CarrInfo{EZF_ROW_NUMBER}\" style=\"margin-left:-5px;\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="locNm_A1" ezfName="locNm_A1" value="APEX0001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="xxScrItem130Txt_A1" ezfName="xxScrItem130Txt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="carrAcctNum_A1" ezfName="carrAcctNum_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="tocNm_A1" ezfName="tocNm_A1" value="REPs SUZUKI" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"10\" ezftoupper=\"\""/>
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:label name="rddDt_A1" ezfName="rddDt_A1" ezfHyo="A" ezfArrayNo="0" />
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:inputText name="schdPickUpDt_A1" ezfName="schdPickUpDt_A1" value="07/09/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"schdPickUpDt_A1{EZF_ROW_NUMBER}\""/>
																				<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdPickUpDt_A1{EZF_ROW_NUMBER}', 4 );" style="margin-left:-5px;margin-top:-7px;">
																				</span>
																		</td>
																		<td>
																			<% if (isHdr) { %>
																				<span style="visibility:hidden;">
																			<% } else { %>
																				<span>
																			<% } %>
																				<ezf:label name="xxTsDsp19Txt_A1" ezfName="xxTsDsp19Txt_A1" ezfHyo="A" ezfArrayNo="0" />
																				</span>
																		</td>
																	</tr>
																</ezf:row>
																<ezf:skip>
																</ezf:skip>
															</table>
														</div><!-- rightTbl -->
													</div> <!-- right table -->
												</div> <!-- parent box -->
											</td>
										</tr>
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="left" width="1000">
													<col align="left" width="10">
													<col align="right" width="90">
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
																<col width="75"><!-- Assigned Carrier -->
																<col width="80">
																<col width="20">
																<col width="110"><!-- Schedule Date -->
																<col width="">
																<col width="">
																<col width="20">
																<col width="190"><!-- Shipping Service Level -->
																<col width="80">
																<col width="20">
																<col width="130"><!-- Assigned Carrier -->
																<col width="80">
																<col width="10">
																<col width="60"><!-- Apply -->
																<tr>
																	<td class="stab">
																		<ezf:anchor name="xxLinkAncr_BC" ezfName="xxLinkAncr_BC" ezfEmulateBtn="1" ezfGuard="OpenWin_AplyCoordSearchCode" otherAttr=" id=\"xxLinkAncr_BC\" ezfanchortext=\"\"">Coordinator</ezf:anchor>
																	</td>
																	<td><ezf:inputText name="schdCoordPsnCd_BT" ezfName="schdCoordPsnCd_BT" value="XX" otherAttr=" size=\"20\" maxlength=\"8\" ezftoupper=\"\""/></td>
																	<td>&nbsp;</td>
																	<td class="stab"><label>Schedule Date</labe></td>
																	<td><ezf:inputText name="schdCoordDt_BT" ezfName="schdCoordDt_BT" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																	<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdCoordDt_BT', 4 );"></td>
																	<td>&nbsp;</td>
																	<td class="stab"><label>Shipping Service Level</labe></td>
																	<td>
																		<ezf:select name="shpgSvcLvlCd_BT" ezfName="shpgSvcLvlCd_BT" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_BP" ezfDispName="shpgSvcLvlDescTxt_BP" otherAttr=" style=\"width:140px\""/>
																	</td>
																	<td>&nbsp;</td>
																	<td class="stab">
																		<ezf:anchor name="xxLinkAncr_BA" ezfName="xxLinkAncr_BA" ezfEmulateBtn="1" ezfGuard="OpenWin_AplyCarrName" otherAttr=" id=\"xxLinkAncr_BA\" ezfanchortext=\"\"">Assigned Carrier</ezf:anchor>
																	</td>
																	<td><ezf:inputText name="carrNm_BT" ezfName="carrNm_BT" value="XX" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
																	<td>&nbsp;</td>
																	<td><ezf:inputButton name="Apply" value="Apply" htmlClass="pBtn4" /></td>
																</tr>
															</table>
															</fieldset>
														</td>
														<td>&nbsp;</td>
														<td valign="bottom" style="padding-bottom:6px;">
															<ezf:inputButton name="Release_SO" value="Drop SO" htmlClass="pBtn7" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								<!--/div -->
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true, true);
</script>

<%-- **** Task specific area ends here **** --%>
