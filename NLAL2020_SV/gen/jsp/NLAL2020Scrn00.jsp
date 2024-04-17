<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230904090421 --%>
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
			<input type="hidden" name="pageID" value="NLAL2020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Receiving Orders Receipt">

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
										<li title="Location Info" class="pTab_ON"><a href="#">Recv Recpt</a></li>
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
					<table border="0" cellpadding="0" cellspacing="0" height="100">
						<tr>
							<td valign="top">
                            </td>
							<td valign="top" width="">
								<table width="" cellpadding="0" border="0" height="100">
									<col align="left" width="5">
									<col align="left" width="90">
									<col align="left" width="125">
									<col align="left" width="5">
									<col align="left" width="130">
									<col align="left" width="125">
									<col align="left" width="5">
									<col align="left" width="65">
									<col align="left" width="120">
									<col align="left" width="33">
									<col align="left" width="185">
									<!-- START 2023/08/31 TZ.Win [QC#61792, MOD] -->
									<col align="left" width="150">
									<!-- END 2023/08/31 TZ.Win [QC#61792, MOD] -->
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Shipment Number</td>
										<td><ezf:inputText name="rwsRefNum_H" ezfName="rwsRefNum_H" value="SHA12345" otherAttr=" size=\"16\" maxlength=\"15\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Source Document Number</td>
										<td><ezf:inputText name="srcOrdNum_H" ezfName="srcOrdNum_H" otherAttr=" size=\"16\" maxlength=\"15\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_WR" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfo" >Warehouse/Tech</ezf:anchor></td>
										<td><ezf:inputText name="shipToRtlWhCd_H" ezfName="shipToRtlWhCd_H" value="H00165" otherAttr=" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_RtlWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlWhNm_H" ezfName="rtlWhNm_H" value="TECH TAK" otherAttr=" size=\"25\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
										<!-- START 2023/08/31 TZ.Win [QC#61792, MOD] -->
										<td class="stab"><ezf:inputCheckBox name="xxChkBox_CL" ezfName="xxChkBox_CL" value="Y" />Include Closed Line</td>
										<!-- END 2023/08/31 TZ.Win [QC#61792, MOD] -->
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Tracking Number</td>
										<td><ezf:inputText name="bolNum_H" ezfName="bolNum_H" value="1234567890000" otherAttr=" size=\"16\" maxlength=\"25\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Source Document Type</td>
										<td>
											<ezf:select name="sceOrdTpCd_H" ezfName="sceOrdTpCd_H" ezfBlank="1" ezfCodeName="sceOrdTpCd_P" ezfDispName="sceOrdTpNm_P" otherAttr=" style=\"width:117px\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_RR" ezfEmulateBtn="1" ezfGuard="OpenWin_RcvLocInfo" >Received WH</ezf:anchor></td>
										<td><ezf:inputText name="rtlWhCd_H" ezfName="rtlWhCd_H" value="XX" otherAttr=" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_RcvRtlWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<!-- START 2023/08/31 TZ.Win [QC#61792, MOD] -->
										<td><ezf:inputText name="rmaSlsWhNm_H" ezfName="rmaSlsWhNm_H" value="XX" otherAttr=" size=\"25\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:inputCheckBox name="xxChkBox_WW" ezfName="xxChkBox_WW" value="Y" />Include WMS Warehouse</td>
										<!-- END 2023/08/31 TZ.Win [QC#61792, MOD] -->
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">RWS Number</td>
										<td><ezf:inputText name="rwsNum_H" ezfName="rwsNum_H" value="RA123456" otherAttr=" size=\"16\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_WR" ezfEmulateBtn="1" ezfGuard="OpenWin_ConfigID" >Config ID</ezf:anchor></td>
										<td><ezf:inputText name="svcConfigMstrPk_H" ezfName="svcConfigMstrPk_H" otherAttr=" size=\"16\" maxlength=\"28\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_WS" ezfEmulateBtn="1" ezfGuard="OpenWin_PartyCode" >Party</ezf:anchor></td>
										<td><ezf:inputText name="shipLocCd_H" ezfName="shipLocCd_H" value="XX" otherAttr=" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_PartyInfo" value=">>" htmlClass="pBtn0" /></td>
										<td colspan = "2"><ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" value="XX" otherAttr=" size=\"25\" maxlength=\"360\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">RWS Status</td>
										<td>
											<ezf:select name="rwsStsCd_H" ezfName="rwsStsCd_H" ezfBlank="1" ezfCodeName="rwsStsCd_P" ezfDispName="rwsStsDescTxt_P" otherAttr=" style=\"width:117px\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab">Serial Number</td>
										<td><ezf:inputText name="serNum_H" ezfName="serNum_H" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">ETA Date</td>
										<td colspan = "3">
											<ezf:inputText name="whInEtaDt_FR" ezfName="whInEtaDt_FR" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'whInEtaDt_FR', 4 );">
											-
											<ezf:inputText name="whInEtaDt_TO" ezfName="whInEtaDt_TO" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'whInEtaDt_TO', 4 );">
										</td>
										<td align="Center" class="stab"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
<%-- #################################################### DETAIL ################################################### --%>
					<hr style="height: 0px;" cellpadding="0">
					<table width="100%">
						<col valign="top">
						<tr>
							<td>
								<table border="0" cellpadding="1" cellspacing="0" width="100%" align="center">
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
																		<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
																		<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
																		<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
																		<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
																		<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
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
																<div id='leftTbl' style="float:left; display:block; height:290px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
																</div>
															</div>  <!-- left table -->
															<%-- LEFT-TABLE(TOP) --%>
															<div style="float:left"> <!-- right table -->
																<div id='rightTblHead' style="width:1085px; overflow-y:hidden; height:30; overflow-x:hidden;">
																	<table border="1" cellpadding="1" cellspacing="0" height="30" style="table-layout: fixed" id="AHEAD" width="3390px" style="margin-right:20px">
																		<col align="center" width="30">		<!-- Checkbox -->
																		<col align="center" width="30">		<!-- Checkbox -->
																		<col align="center" width="80">		<!-- RWS Number -->
																		<col align="center" width="60"> 	<!-- RWS LineNumber -->
																		<col align="center" width="139">	<!-- RWS Status-->
																		<col align="center" width="223">	<!-- WH/Tech-->
																		<col align="center" width="121">	<!-- Item Number-->
																		<col align="center" width="121">	<!-- Altenate Item Number-->
																		<col align="center" width="188">	<!-- Item Name-->
																		<col align="center" width="45">		<!-- MT -->
																		<col align="center" width="80">		<!-- Request Qty -->
																		<col align="center" width="80">		<!-- Received Qty -->
																		<col align="center" width="250">	<!-- Received WH -->
																		<col align="center" width="80">		<!-- SS -->
																		<col align="center" width="80">		<!-- Receiving Qty -->
																		<col align="center" width="230">	<!-- Serial -->
																		<col align="center" width="155">	<!-- Config ID -->
																		<col align="center" width="80">		<!-- SWH -->
																		<col align="center" width="80">		<!-- Party Site -->
																		<col align="center" width="160">	<!-- Party -->
																		<col align="center" width="240">	<!-- Party Address -->
																		<col align="center" width="150">	<!-- Shipment#-->
																		<col align="center" width="250">	<!-- Tracking#-->
																		<col align="center" width="160">	<!-- Source Doc Type-->
																		<col align="center" width="150">	<!-- Source Doc#-->
																		<col align="center" width="70">		<!-- Source Doc Line#-->
																		<col align="center" width="160">	<!-- Pick Service Level-->
																		<col align="center" width="160">	<!-- Carrier-->
																		<tr>
																			<td id="AH0"  class="pClothBs colFix"></td>
																			<td id="AH1"  class="pClothBs colFix"></td>
																			<td id="AH2"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rwsNum_A1' )">RWS Number<img id="sortIMG.rwsNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH3"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rwsDtlLineNum_A1' )">RWS Line<img id="sortIMG.rwsDtlLineNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rwsStsDescTxt_A1' )">RWS Status<img id="sortIMG.rwsStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhNm_A1' )">Warehouse/Tech<img id="sortIMG.rtlWhNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'origMdseCd_A1' )">Item Number<img id="sortIMG.origMdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'flipMdseCd_A1' )">Alternate Item<img id="sortIMG.flipMdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescShortTxt_A1' )">Item Description<img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaMdseTpCd_A1' )">MT<img id="sortIMG.coaMdseTpCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rwsQty_A1' )">Request Qty<img id="sortIMG.rwsQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rwsStkQty_A1' )">Received Qty<img id="sortIMG.rwsStkQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhCd_A1' )">Received WH<img id="sortIMG.rtlWhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyStkStsCd_A1' )">Receiving SS<img id="sortIMG.invtyStkStsCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poBalQty_A1' )">Receiving Qty<img id="sortIMG.poBalQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'serNum_A1' )">Serial<img id="sortIMG.serNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcConfigMstrPk_A1' )">Config ID<img id="sortIMG.svcConfigMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlSwhCd_A1' )">Sub WH<img id="sortIMG.rtlSwhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipLocCd_A1' )">Party Site<img id="sortIMG.shipLocCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipFromAcctNm_A1' )">Party<img id="sortIMG.shipFromAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'fromLocCtyAddr_A1' )">Party Address<img id="sortIMG.fromLocCtyAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rwsRefNum_A1' )">Shipment Number<img id="sortIMG.rwsRefNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'imptInvBolNum_A1' )">Tracking Number<img id="sortIMG.imptInvBolNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'sceOrdTpNm_A1' )">Source Document Type<img id="sortIMG.sceOrdTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'srcOrdNum_A1' )">Source Document#<img id="sortIMG.srcOrdNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dplyLineNum_A1' )">Src Line#<img id="sortIMG.dplyLineNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcLvlDescTxt_A1' )"> Pick Service Level<img id="sortIMG.svcLvlDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="AH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'carrNm_A1' )">Carrier<img id="sortIMG.carrNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		</tr>
																	</table>
																</div>
																<div id="rightTBL" style="width:1102px; height:310; display:block; overflow:scroll; margin:0px; padding:0px;" >
																	<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed"  width="3390px" >
																		<col align="center" width="30">		<!-- Checkbox -->
																		<col align="center" width="30">		<!-- Checkbox -->
																		<col align="left"   width="80">		<!-- RWS Number -->
																		<col align="center" width="60"> 	<!-- RWS LineNumber -->
																		<col align="center" width="139">	<!-- RWS Status-->
																		<col align="center" width="223">	<!-- WH/Tech-->
																		<col align="center" width="121">	<!-- Item Number-->
																		<col align="center" width="121">	<!-- Altenate Item Number-->
																		<col align="center" width="188">	<!-- Item Name-->
																		<col align="center" width="45">		<!-- MT -->
																		<col align="center" width="80">		<!-- Request Qty -->
																		<col align="center" width="80">		<!-- Received Qty -->
																		<col align="center" width="250">	<!-- Received WH -->
																		<col align="center" width="80">		<!-- SS -->
																		<col align="center" width="80">		<!-- Receiving Qty -->
																		<col align="center" width="230">	<!-- Serial -->
																		<col align="center" width="155">	<!-- Config ID -->
																		<col align="center" width="80">		<!-- SWH -->
																		<col align="center" width="80">		<!-- Party Site -->
																		<col align="center" width="160">	<!-- Party -->
																		<col align="center" width="240">	<!-- Party Address -->
																		<col align="center" width="150">	<!-- Shipment#-->
																		<col align="center" width="250">	<!-- Tracking#-->
																		<col align="center" width="160">	<!-- Source Doc Type-->
																		<col align="center" width="150">	<!-- Source Doc#-->
																		<col align="center" width="70">		<!-- Source Doc Line#-->
																		<col align="center" width="160">	<!-- Pick Service Level-->
																		<col align="center" width="160">	<!-- Carrier -->
																		<ezf:row ezfHyo="A">
																			<tr height="28" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
																				<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServerForPreferredView('OnChange_ChkBoxRMANum', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"OnChange_ChkBoxRMANum{EZF_ROW_NUMBER}\""/></td>
																				<td><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServerForPreferredView('OnChange_ChkBoxRMALine', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"OnChange_ChkBoxRMALine{EZF_ROW_NUMBER}\""/></td>
																				<td><div id="rwsNum_A1{EZF_ROW_NUMBER}"><ezf:label name="rwsNum_A1" ezfName="rwsNum_A1" ezfHyo="A" ezfArrayNo="0" /></div></td>
																				<td><ezf:label name="rwsDtlLineNum_A1" ezfName="rwsDtlLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="rwsStsDescTxt_A1" ezfName="rwsStsDescTxt_A1" value="Partially Received" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/></td>
																				<td>
																					<ezf:inputText name="shipToRtlWhCd_A1" ezfName="shipToRtlWhCd_A1" value="0000XX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/>
																					<ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="TECH TAK" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
																				</td>
																				<td><ezf:inputText name="origMdseCd_A1" ezfName="origMdseCd_A1" value="1060075550" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="flipMdseCd_A1" ezfName="flipMdseCd_A1" value="1060075566" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="OCE-PCK, CONVEYORBELT TRIDENT" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"250\" ezftoupper=\"\""/></td>
																				<td><ezf:label name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="rwsQty_A1" ezfName="rwsQty_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align: right\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="rwsStkQty_A1" ezfName="rwsStkQty_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align: right\" ezftoupper=\"\""/></td>
																				<td>
																					<ezf:inputButton name="OpenWin_RcvRtlWhInfo" value="Rcv" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_RcvRtlWhInfo#{EZF_ROW_NUMBER}\""/>
																					<ezf:inputText name="rtlWhCd_A1" ezfName="rtlWhCd_A1" value="H00166" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/>
																					<ezf:inputButton name="Search_RcvRtlWh" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"Search_RcvRtlWh#{EZF_ROW_NUMBER}\""/>
																					<ezf:inputText name="rmaSlsWhNm_A1" ezfName="rmaSlsWhNm_A1" value="YASUHIKO IMAZU" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/>
																				</td>
																				<td><ezf:select name="invtyStkStsCd_A1" ezfName="invtyStkStsCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="stkStsCd_P" ezfDispName="stkStsDescTxt_P" otherAttr=" style=\"width:70;\""/></td>
																					
																				<td><ezf:inputText name="poBalQty_A1" ezfName="poBalQty_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"sendServer('OnChange_RecvQty', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align: right\" id=\"OnChange_RecvQty{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																				<td>
																					<ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="SER00001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"30\" ezftoupper=\"\""/>
																					<ezf:inputButton name="OpenWin_SerEntry" value="S" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_SerEntry{EZF_ROW_NUMBER}\""/>
																				</td>
																				<td><ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" value="G" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="shipLocCd_A1" ezfName="shipLocCd_A1" value="5Z1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="shipFromAcctNm_A1" ezfName="shipFromAcctNm_A1" value="SAN PEDRO WH - CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"360\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="fromLocCtyAddr_A1" ezfName="fromLocCtyAddr_A1" value="San Pedro" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"32\" maxlength=\"50\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="rwsRefNum_A1" ezfName="rwsRefNum_A1" value="SHA12345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="imptInvBolNum_A1" ezfName="imptInvBolNum_A1" value="1234567890000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"30\" ezftoupper=\"\""/>
																					<ezf:inputButton name="OpenWin_TrackingNumber" value="Tracking#" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" id=\"OpenWin_TrackingNumber{EZF_ROW_NUMBER}\""/>
																				</td>
																				<td><ezf:inputText name="sceOrdTpNm_A1" ezfName="sceOrdTpNm_A1" value="Return" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="srcOrdNum_A1" ezfName="srcOrdNum_A1" value="00011654" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:label name="dplyLineNum_A1" ezfName="dplyLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="svcLvlDescTxt_A1" ezfName="svcLvlDescTxt_A1" value="Ground Service" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="carrNm_A1" ezfName="carrNm_A1" value="APEX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td class="pAuditInfo">
																					<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																					<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																					<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																					<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																					<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
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
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="left" width="100">
												<col align="left" width="">
												<col align="right" width="100">
												<col align="right" width="100">
												<col align="left" width="10">
												<tr>
													<td valign="center"><ezf:inputButton name="RWS_Close" value="RWS Close" htmlClass="pBtn7" /></td>
													<td>&nbsp;</td>
													<td valign="center"><ezf:inputButton name="Open_MtrEnt" value="Meter Read" htmlClass="pBtn7" /></td>
													<td valign="center"><ezf:inputButton name="Receive" value="Receive" htmlClass="pBtn7" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, false, true);
</script>

<%-- **** Task specific area ends here **** --%>
