<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230227194939 --%>
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
			<input type="hidden" name="pageID" value="NLAL2030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Receiving Orders Lookup">

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
										<li title="Location Info" class="pTab_ON"><a href="#">Rcv Ord Lkup</a></li>
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
					<table border="0" cellpadding="0" cellspacing="0" height="100">
						<tr>
							<td valign="top"></td>
							<td valign="top" width="">
								<table width="" cellpadding="0" border="0" height="100">
									<col align="left" width="5">
									<col align="left" width="132">
									<col align="left" width="125">
									<col align="left" width="72">
									<col align="left" width="125">
									<col align="left" width="80">
									<col align="left" width="60">
									<col align="center" width="35">
									<col align="left" width="125">
									<col align="left" width="50">
									<col align="left" width="60">
									<col align="center" width="35">
									<col align="left" width="125">
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Source Document Number</td>
										<td><ezf:inputText name="trxOrdNum" ezfName="trxOrdNum" value="XX" otherAttr=" size=\"15\" maxlength=\"10\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_W1" ezfEmulateBtn="1" ezfGuard="OpenWin_Mdse" >Item Number</ezf:anchor></td>
										<td><ezf:inputText name="mdseCd" ezfName="mdseCd" value="XX" otherAttr=" size=\"15\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_W4" ezfEmulateBtn="1" ezfGuard="OpenWin_Wh" >Warehouse</ezf:anchor></td>
										<td><ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="OnClick_WhNm" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="XX" otherAttr=" size=\"15\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_W2" ezfEmulateBtn="1" ezfGuard="OpenWin_Party" >Party</ezf:anchor></td>
										<td><ezf:inputText name="fromLocCd" ezfName="fromLocCd" value="12345678" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="OnClick_PartyNm" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="123456789012345" otherAttr=" size=\"15\" maxlength=\"360\" tabindex=\"-1\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Source Document Type</td>
										<td>
											<ezf:select name="sceOrdTpCd" ezfName="sceOrdTpCd" ezfBlank="1" ezfCodeName="sceOrdTpCd_P" ezfDispName="sceOrdTpNm_P" otherAttr=" style=\"width:110px\""/>
										</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_W7" ezfEmulateBtn="1" ezfGuard="OpenWin_FlipMdse" >Alternate Item</ezf:anchor></td>
										<td><ezf:inputText name="flipMdseCd" ezfName="flipMdseCd" value="XX" otherAttr=" size=\"15\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_W5" ezfEmulateBtn="1" ezfGuard="OpenWin_Swh" >Sub Warehouse</ezf:anchor></td>
										<td><ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="OnClick_SwhNm" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" value="XX" otherAttr=" size=\"15\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_W6" ezfEmulateBtn="1" ezfGuard="OpenWin_Carr" >Carrier</ezf:anchor></td>
										<td><ezf:inputText name="carrCd" ezfName="carrCd" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="OnClick_CarrNm" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="carrNm" ezfName="carrNm" value="XX" otherAttr=" size=\"15\" maxlength=\"20\" tabindex=\"-1\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Shipment Number</td>
										<td><ezf:inputText name="rwsRefNum" ezfName="rwsRefNum" value="XX" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
										<td class="stab">RWS Number</td>
										<td><ezf:inputText name="rwsNum" ezfName="rwsNum" value="XX" otherAttr=" size=\"15\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_W8" ezfEmulateBtn="1" ezfGuard="OpenWin_RcvWh" >Received WH</ezf:anchor></td>
										<td><ezf:inputText name="whCd" ezfName="whCd" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="OnClick_RcvWhNm" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="whNm" ezfName="whNm" value="XX" otherAttr=" size=\"15\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
										<td class="stab">Serial</td>
										<td colspan="3"><ezf:inputText name="serNum" ezfName="serNum" value="XX" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Tracking Number</td>
										<td><ezf:inputText name="imptInvBolNum" ezfName="imptInvBolNum" value="XX" otherAttr=" size=\"15\" maxlength=\"25\" ezftoupper=\"\""/></td>
										<td class="stab">RWS Status</td>
										<td>
											<ezf:select name="rwsStsCd" ezfName="rwsStsCd" ezfBlank="1" ezfCodeName="rwsStsCd_P" ezfDispName="rwsStsDescTxt_P" otherAttr=" style=\"width:110px\""/>
										</td>
										<td class="stab">ETA Date</td>
										<td colspan="3">
											<ezf:inputText name="whInEtaDt_FR" ezfName="whInEtaDt_FR" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'whInEtaDt_FR', 4 );">
											-
											<ezf:inputText name="whInEtaDt_TO" ezfName="whInEtaDt_TO" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'whInEtaDt_TO', 4 );">
										</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_W3" ezfEmulateBtn="1" ezfGuard="OpenWin_ConfigID" >Config ID</ezf:anchor></td>
										<td colspan="2"><ezf:inputText name="svcConfigMstrPk" ezfName="svcConfigMstrPk" value="XX" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
										<td align="right" class="stab"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>
					<table width="100%">
						<col valign="top">
						<tr>
							<td>
								<div class="pTab_HEAD">
									<ul>
										<li class="pTab_ON"  id="Tab_Ord" title="Order"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Tab_Ord" >Order</ezf:anchor></li>
										<li class="pTab_OFF" id="Tab_Rws" title="RWS List"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Tab_Rws" >RWS List</ezf:anchor></li>
									</ul>
								</div>
								<c:choose>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Tab_Ord'}">
								<script type="text/javascript">
									document.getElementById( "Tab_Ord" ).className = "pTab_ON";
									document.getElementById( "Tab_Rws" ).className = "pTab_OFF";
								</script>
								<div class="pTab_BODY_In">
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="left" width="180">
													<col align="left" width="80"><!-- Select All -->
													<col align="left" width="80"><!-- Unselect All -->
													<col align="right" width="">
													<col align="right" width="550">
													<tr>
														<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
														<td><ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="UnSelect_All" value="Unselect All" htmlClass="pBtn6" /></td>
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
																	<div id='leftTbl' style="float:left; display:block; height:255px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
																	</div>
																</div>  <!-- left table -->
																<%-- LEFT-TABLE(TOP) --%>
																<div style="float:left"> <!-- right table -->
																	<div id='rightTblHead' style="width:1065px; overflow-y:hidden; height:28; overflow-x:hidden;">
																		<table border="1" cellpadding="1" cellspacing="0" height="28" style="table-layout: fixed" id="AHEAD" width="2380px" style="margin-right:20px">
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="130">	<!-- Source Doc Type -->
																			<col align="center" width="130">	<!-- Source Doc#-->
																			<col align="center" width="80">		<!-- Source Doc Line -->
																			<col align="center" width="80">		<!-- Party Site -->
																			<col align="center" width="165">	<!-- Party -->
																			<col align="center" width="152">	<!-- Warehouse/Tech -->
																			<col align="center" width="121">	<!-- Config Id -->
																			<col align="center" width="121">	<!-- Item Number-->
																			<col align="center" width="121">	<!-- Alternate Item Number-->
																			<col align="center" width="194">	<!-- Item Name-->
																			<col align="center" width="80">		<!-- Request Qty-->
																			<col align="center" width="80">		<!-- Open Qty-->
																			<col align="center" width="230">	<!-- Serial-->
																			<col align="center" width="80">		<!-- RWS Open Flg -->
																			<col align="center" width="50">		<!-- MT -->
																			<col align="center" width="121">	<!-- Supplier Item-->
																			<col align="center" width="80">		<!-- Sub WH -->
																			<tr>
																				<td id="AH0"  class="pClothBs colFix"></td>
																				<td id="AH1"  class="pClothBs colFix"></td>
																				<td id="AH2"  class="pClothBs colFix">Source Doc Type</td>
																				<td id="AH3"  class="pClothBs colFix">Source Document#</td>
																				<td id="AH4"  class="pClothBs colFix">Src Doc Line</td>
																				<td id="AH5"  class="pClothBs">Party Site</td>
																				<td id="AH6"  class="pClothBs">Party</td>
																				<td id="AH7"  class="pClothBs">Warehouse/Tech</td>
																				<td id="AH8"  class="pClothBs">Config ID</td>
																				<td id="AH9"  class="pClothBs">Item Number</td>
																				<td id="AH10" class="pClothBs">Alternate Item</td>
																				<td id="AH11" class="pClothBs">Item Description</td>
																				<td id="AH12" class="pClothBs">Request Qty</td>
																				<td id="AH13" class="pClothBs">Open Qty</td>
																				<td id="AH14" class="pClothBs">Serial</td>
																				<td id="AH15" class="pClothBs">Open RWS</td>
																				<td id="AH16" class="pClothBs">MT</td>
																				<td id="AH17" class="pClothBs">Supplier Item</td>
																				<td id="AH18" class="pClothBs">Sub WH</td>
																			</tr>
																		</table>
																	</div>
																	<div id="rightTBL" style="width:1082px; height:275; display:block; overflow:scroll; margin:0px; padding:0px;" >
																		<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed"  width="2380px" >
																				<col align="center" width="30">		<!-- Checkbox -->
																				<col align="center" width="30">		<!-- Checkbox -->
																				<col align="center" width="130">	<!-- Source Doc Type -->
																				<col align="left"   width="130">	<!-- Source Doc#-->
																				<col align="center" width="80">		<!-- Source Doc Line -->
																				<col align="center" width="80">		<!-- Party Site -->
																				<col align="center" width="165">	<!-- Party -->
																				<col align="center" width="152">	<!-- Warehouse/Tech -->
																				<col align="center" width="121">	<!-- Config Id -->
																				<col align="center" width="121">	<!-- Item Number-->
																				<col align="center" width="121">	<!-- Alternate Item Number-->
																				<col align="center" width="194">	<!-- Item Name-->
																				<col align="center" width="80">		<!-- Request Qty-->
																				<col align="center" width="80">		<!-- Open Qty-->
																				<col align="center" width="230">	<!-- Serial-->
																				<col align="center" width="80">		<!-- RWS Open Flg -->
																				<col align="center" width="50">		<!-- MT -->
																				<col align="center" width="121">	<!-- Supplier Item-->
																				<col align="center" width="80">		<!-- Sub WH -->
																			<ezf:row ezfHyo="A">
																				<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																					<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServerForPreferredView('OnChange_ChkBoxHdr', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/></td>
																					<td><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServerForPreferredView('OnChange_ChkBoxLine', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"xxChkBox_A2{EZF_ROW_NUMBER}\""/></td>
																					<td><ezf:inputText name="sceOrdTpNm_A1" ezfName="sceOrdTpNm_A1" value="Return" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\" id=\"sceOrdTpNm_A1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_OrdEntry" otherAttr=" tabindex=\"-1\" id=\"Open_OrdEntry{EZF_ROW_NUMBER}\""><ezf:label name="trxOrdNum_A1" ezfName="trxOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																					<td><ezf:label name="dplyLineNum_A1" ezfName="dplyLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:inputText name="fromLocCd_A1" ezfName="fromLocCd_A1" value="A12280" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="Party Name" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"360\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="Retail WH" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" value="123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="1060075555" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="flipMdseCd_A1" ezfName="flipMdseCd_A1" value="1060075566" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="IR200-1111111" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"250\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="rwsQty_A1" ezfName="rwsQty_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align: right\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="xxQty10Num_A1" ezfName="xxQty10Num_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align: right\" ezftoupper=\"\""/></td>
																					<td>
																						<ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="SER00001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"30\" ezftoupper=\"\""/>
																						<ezf:inputButton name="OpenWin_SerEntry" value="S" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_SerEntry{EZF_ROW_NUMBER}\""/>
																					</td>
																					<td><ezf:label name="rwsOpenFlg_A1" ezfName="rwsOpenFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:label name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					<td><ezf:inputText name="aslMdseCd_A1" ezfName="aslMdseCd_A1" value="1060075566" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" value="NEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
																				</tr>
																			</ezf:row>
																			<ezf:skip>
																				<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																					<td>&nbsp;</td>
																					<td><input type="checkbox" name="xxChkBox_A2" ezfname="xxChkBox_A2" ezfhyo="A" value="Y" id="xxChkBox_A2{EZF_ROW_NUMBER}" ></td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td><label ezfout name="dplyLineNum_A1" ezfname="dplyLineNum_A1" ezfhyo="A">1.2</label></td>
																					<td><input type="text" class="pPro" size="10"  maxlength="20"  name="fromLocCd_A1" ezfname="fromLocCd_A1" value="A12280" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="22"  maxlength="360"  name="dsAcctNm_A1" ezfname="dsAcctNm_A1" value="Party Name" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="20"  maxlength="30"  name="rtlWhNm_A1" ezfname="rtlWhNm_A1" value="Retail WH" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="16"  maxlength="30"  name="svcConfigMstrPk_A1" ezfname="svcConfigMstrPk_A1" value="123456789" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="16"  maxlength="20"  name="mdseCd_A1" ezfname="mdseCd_A1" value="1060075555" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="16"  maxlength="20"  name="flipMdseCd_A1" ezfname="flipMdseCd_A1" value="1060075566" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="26"  maxlength="250"  name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" value="IR200-1111111" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="10" maxlength="10" name="rwsQty_A1" ezfname="rwsQty_A1" value="1234567890"  style="text-align: right" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="10" maxlength="10" name="xxQty10Num_A1" ezfname="xxQty10Num_A1" value="1234567890"  style="text-align: right" ezfhyo="A" ezftoupper ></td>
																					<td>
																						<input type="text" size="24" maxlength="30" value="SER00001" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" ezftoupper>
																						<input type="button" class="pBtn1" value="S" name="OpenWin_SerEntry" onClick="sendServer(this)" id="OpenWin_SerEntry{EZF_ROW_NUMBER}" ezfhyo="A">
																					</td>
																					<td><label ezfout name="rwsOpenFlg_A1" ezfname="rwsOpenFlg_A1" ezfhyo="A">N</label></td>
																					<td><label ezfout name="coaMdseTpCd_A1" ezfname="coaMdseTpCd_A1" ezfhyo="A">10</label></td>
																					<td><input type="text" class="pPro" size="16"  maxlength="20"  name="aslMdseCd_A1" ezfname="aslMdseCd_A1" value="1060075566" ezfhyo="A" ezftoupper ></td>
																					<td><input type="text" class="pPro" size="10"  maxlength="20"  name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" value="NEW" ezfhyo="A" ezftoupper ></td>
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
												<table>
													<col width="500">
													<col width="">
													<col width="2">
													<col width="">
													<col width="2">
													<col width="">
													<col width="2">
													<col width="">
													<col width="5">
													<col width="">
													<col width="10">
													<tr>
														<td>&nbsp;</td>
														<td class="stab">Shipment Number</td>
														<td>&nbsp;</td>
														<td><ezf:inputText name="rwsRefNum_AP" ezfName="rwsRefNum_AP" value="XXX" otherAttr=" size=\"12\" maxlength=\"15\" ezftoupper=\"\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Tracking Number</td>
														<td><ezf:inputText name="imptInvBolNum_AP" ezfName="imptInvBolNum_AP" value="XX" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
														<td>&nbsp;</td>
														<td class="stab"><ezf:inputButton name="RWS_Create" value="Create RWS" htmlClass="pBtn8" /></td>
														<td>&nbsp;</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								</c:when>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Tab_Rws'}">
								<script type="text/javascript">
									document.getElementById( "Tab_Ord" ).className = "pTab_OFF";
									document.getElementById( "Tab_Rws" ).className = "pTab_ON";
								</script>
								<div class="pTab_BODY_In">
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="left" width="180">
													<col align="left" width="80">
													<col align="left" width="80">
													<col align="left" width="">
													<col align="right" width="550">
													<tr>
														<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
														<td><ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="UnSelect_All" value="Unselect All" htmlClass="pBtn6" /></td>
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
																	<div id='leftTbl' style="float:left; display:block; height:251px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
																	</div>
																</div>  <!-- left table -->
																<div style="float:left"> <!-- right table -->
																	<%-- LEFT-TABLE(TOP) --%>
																	<div id='rightTblHead' style="width:1065px; overflow-y:hidden; height:28; overflow-x:hidden;">
																		<!-- START 2023/02/21 TZ.Win [QC#61161, MOD] -->
																		<table border="1" cellpadding="1" cellspacing="0" height="28" style="table-layout: fixed" id="BHEAD" width="2965px" style="margin-right:20px">
																		<!-- END 2023/02/21 TZ.Win [QC#61161, MOD] -->
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="100">	<!-- RWS Status -->
																			<col align="center" width="85">		<!-- RWS# -->
																			<!-- START 2023/02/21 TZ.Win [QC#61161, ADD] -->
																			<col align="center" width="105">	<!-- ASN Create Date -->
																			<!-- END 2023/02/21 TZ.Win [QC#61161, ADD] -->
																			<col align="center" width="65">		<!-- RWS Line -->
																			<col align="center" width="250">	<!-- Warehouse/Tech -->
																			<col align="center" width="120">	<!-- Shipment# -->
																			<col align="center" width="150">	<!-- RMA Disposition -->
																			<col align="center" width="200">	<!-- Tracking# -->
																			<col align="center" width="130">	<!-- Source Doc Type -->
																			<col align="center" width="140">	<!-- Source Doc#-->
																			<col align="center" width="80">		<!-- Source Doc Line -->
																			<col align="center" width="80">		<!-- Party Site -->
																			<col align="center" width="165">	<!-- Party -->
																			<col align="center" width="121">	<!-- Config ID -->
																			<col align="center" width="121">	<!-- Item Number-->
																			<col align="center" width="121">	<!-- Alternate Item Number-->
																			<col align="center" width="194">	<!-- Item Name-->
																			<col align="center" width="80">		<!-- Request Qty-->
																			<col align="center" width="80">		<!-- Open Qty-->
																			<col align="center" width="230">	<!-- Serial-->
																			<col align="center" width="80">		<!-- Sub WH -->
																			<col align="center" width="165">	<!-- Received WH -->
																			<tr>
																				<td id="BH0"  class="pClothBs colFix"></td>
																				<td id="BH1"  class="pClothBs colFix">RWS Status</td>
																				<td id="BH2"  class="pClothBs colFix">RWS Number</td>
																				<!-- START 2023/02/21 TZ.Win [QC#61161, ADD] -->
																				<td id="BH3"  class="pClothBs colFix">ASN Creation Date</td>
																				<!-- END 2023/02/21 TZ.Win [QC#61161, ADD] -->
																				<!-- START 2023/02/21 TZ.Win [QC#61161, MOD] -->
																				<td id="BH4"  class="pClothBs colFix">RWS Line</td>
																				<td id="BH5"  class="pClothBs">Warehouse/Tech</td>
																				<td id="BH6"  class="pClothBs">Shipment Number</td>
																				<td id="BH7"  class="pClothBs">RMA Disposition</td>
																				<td id="BH8"  class="pClothBs">Tracking Number</td>
																				<td id="BH9"  class="pClothBs">Source Document Type</td>
																				<td id="BH10"  class="pClothBs">Source Document#</td>
																				<td id="BH11"  class="pClothBs">Src Doc Line</td>
																				<td id="BH12" class="pClothBs">Party Site</td>
																				<td id="BH13" class="pClothBs">Party</td>
																				<td id="BH14" class="pClothBs">Config ID</td>
																				<td id="BH15" class="pClothBs">Item Number</td>
																				<td id="BH16" class="pClothBs">Alternate Item</td>
																				<td id="BH17" class="pClothBs">Item Description</td>
																				<td id="BH18" class="pClothBs">Request Qty</td>
																				<td id="BH19" class="pClothBs">Open Qty</td>
																				<td id="BH20" class="pClothBs">Serial</td>
																				<td id="BH21" class="pClothBs">Sub WH</td>
																				<td id="BH22" class="pClothBs">Received WH</td>
																				<!-- END 2023/02/21 TZ.Win [QC#61161, MOD] -->
																			</tr>
																		</table>
																	</div>
																	<%-- LEFT-TABLE(MID) --%>
																	<div id="rightTBL" style="width:1082px; height:268; display:block; overflow:scroll; margin:0px; padding:0px;" >
																		<!-- START 2023/02/21 TZ.Win [QC#61161, MOD] -->
																		<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed"  width="2965px" >
																		<!-- END 2023/02/21 TZ.Win [QC#61161, MOD] -->
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="100">	<!-- RWS Status -->
																			<col align="left"   width="85">		<!-- RWS# -->
																			<!-- START 2023/02/21 TZ.Win [QC#61161, ADD] -->
																			<col align="center" width="105">	<!-- ASN Create Date -->
																			<!-- END 2023/02/21 TZ.Win [QC#61161, ADD] -->
																			<col align="center" width="65">		<!-- RWS Line -->
																			<col align="center" width="250">	<!-- Warehouse/Tech -->
																			<col align="center" width="120">	<!-- Shipment# -->
																			<col align="center" width="150">	<!-- RMA Disposition -->
																			<col align="center" width="200">	<!-- Tracking# -->
																			<col align="center" width="130">	<!-- Source Doc Type -->
																			<col align="left"   width="140">	<!-- Source Doc#-->
																			<col align="center" width="80">		<!-- Source Doc Line -->
																			<col align="center" width="80">		<!-- Party Site -->
																			<col align="center" width="165">	<!-- Party -->
																			<col align="center" width="121">	<!-- Config ID -->
																			<col align="center" width="121">	<!-- Item Number-->
																			<col align="center" width="121">	<!-- Alternate Item Number-->
																			<col align="center" width="194">	<!-- Item Name-->
																			<col align="center" width="80">		<!-- Request Qty-->
																			<col align="center" width="80">		<!-- Open Qty-->
																			<col align="center" width="230">	<!-- Serial-->
																			<col align="center" width="80">		<!-- Sub WH -->
																			<col align="center" width="165">	<!-- Received WH -->
																			<ezf:row ezfHyo="B">
																				<tr height="28" id="id_leftB_row{EZF_ROW_NUMBER}">
																					<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B1{EZF_ROW_NUMBER}\""/></td>
																					<td><ezf:inputText name="rwsStsDescTxt_B1" ezfName="rwsStsDescTxt_B1" value="Printed" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" id=\"rwsStsDescTxt_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:anchor name="" ezfName="" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_RcvEntry" otherAttr=" tabindex=\"-1\" id=\"Open_RcvEntry{EZF_ROW_NUMBER}\""><ezf:label name="rwsNum_B1" ezfName="rwsNum_B1" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></td>
																					<!-- START 2023/02/21 TZ.Win [QC#61161, ADD] -->
																					<td><ezf:inputText name="xxCratDt_B1" ezfName="xxCratDt_B1" value="02/01/2023" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\" id=\"xxCratDt_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<!-- END 2023/02/21 TZ.Win [QC#61161, ADD] -->
																					<td><ezf:label name="rwsDtlLineNum_B1" ezfName="rwsDtlLineNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																					<td>
																						<ezf:inputButton name="Open_Win_WhFromRwsList" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"Open_Win_WhFromRwsList{EZF_ROW_NUMBER}\""/>
																						<ezf:inputText name="rtlWhCd_B1" ezfName="rtlWhCd_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"5\" ezftoupper=\"\""/>
																						<ezf:inputButton name="getWhNameFromRwsList" value=">>" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"getWhNameFromRwsList{EZF_ROW_NUMBER}\""/>
																						<ezf:inputText name="rtlWhNm_B1" ezfName="rtlWhNm_B1" value="Retail WH" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" id=\"rtlWhNm_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																					</td>
																					<td><ezf:inputText name="rwsRefNum_B1" ezfName="rwsRefNum_B1" value="SH00001" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"15\" id=\"rwsRefNum_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td>
																						<ezf:select name="thirdPtyDspTpCd_B1" ezfName="thirdPtyDspTpCd_B1" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="thirdPtyDspTpCd" ezfDispName="thirdPtyDspTpDescTxt" otherAttr=" style=\"width:140px\""/>
																					</td>
																					<td><ezf:inputText name="imptInvBolNum_B1" ezfName="imptInvBolNum_B1" value="12345678901234" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"25\" id=\"imptInvBolNum_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																						<ezf:inputButton name="OpenWin_TrackingNumber" value="Tracking#" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" id=\"OpenWin_TrackingNumber{EZF_ROW_NUMBER}\""/>
																					</td>
																					<td><ezf:inputText name="sceOrdTpNm_B1" ezfName="sceOrdTpNm_B1" value="Return" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" id=\"sceOrdTpNm_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:anchor name="" ezfName="" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_OrdEntry" otherAttr=" tabindex=\"-1\" id=\"Open_OrdEntry{EZF_ROW_NUMBER}\""><ezf:label name="trxOrdNum_B1" ezfName="trxOrdNum_B1" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></td>
																					<td><ezf:label name="dplyLineNum_B1" ezfName="dplyLineNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																					<td><ezf:inputText name="fromLocCd_B1" ezfName="fromLocCd_B1" value="A12280" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" id=\"fromLocCd_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="dsAcctNm_B1" ezfName="dsAcctNm_B1" value="Party Name" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"360\" id=\"dsAcctNm_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="svcConfigMstrPk_B1" ezfName="svcConfigMstrPk_B1" value="1111" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" id=\"svcConfigMstrPk_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="mdseCd_B1" ezfName="mdseCd_B1" value="1060075555" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"20\" id=\"mdseCd_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="flipMdseCd_B1" ezfName="flipMdseCd_B1" value="1060075566" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"20\" id=\"flipMdseCd_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="mdseDescShortTxt_B1" ezfName="mdseDescShortTxt_B1" value="IR200-1111111" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"250\" id=\"mdseDescShortTxt_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="rwsQty_B1" ezfName="rwsQty_B1" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align: right\" id=\"rwsQty_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="xxQty10Num_B1" ezfName="xxQty10Num_B1" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align: right\" id=\"xxQty10Num_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td>
																						<ezf:inputText name="serNum_B1" ezfName="serNum_B1" value="SER00001" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"30\" id=\"serNum_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																						<ezf:inputButton name="OpenWin_SerEntry" value="S" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_SerEntry{EZF_ROW_NUMBER}\""/>
																					</td>
																					<td><ezf:inputText name="rtlSwhCd_B1" ezfName="rtlSwhCd_B1" value="U90" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" id=\"rtlSwhCd_B1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="xxRtlWhSrchTxt_B1" ezfName="xxRtlWhSrchTxt_B1" value="XX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"30\" ezftoupper=\"\""/></td>
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
											<td>
												<table border="0" cellpadding="1" cellspacing="0" width="98%">
												    <!-- START 2023/02/28 TZ.Win [QC#61160, MOD] -->
													<col align="left" width="870">
													<!-- END 2023/02/28 TZ.Win [QC#61160, MOD] -->
													<col align="right" valign="middle">
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
																	<!-- START 2023/02/28 TZ.Win [QC#61160, MOD] -->
																	<col width="65">
																	<col width="210">
																	<!-- END 2023/02/28 TZ.Win [QC#61160, MOD] -->
																	<!-- START 2023/02/28 TZ.Win [QC#61160, ADD] -->
																	<col width="85">
																	<col width="210">
																	<!-- END 2023/02/28 TZ.Win [QC#61160, ADD] -->
																	<col width="80">
																	<col width="150">
																	<col width="50">
																	<tr>
																		<td class="stab"><ezf:anchor name="xxLinkAncr_AP" ezfName="xxLinkAncr_AP" ezfEmulateBtn="1" ezfGuard="Open_Win_WhFromApply" >Warehouse</ezf:anchor></td>
																		<td>
																			<ezf:inputText name="rtlWhCd_AP" ezfName="rtlWhCd_AP" otherAttr=" size=\"5\" ezftoupper=\"\""/>
																			<ezf:inputButton name="getWhNameFromApply" value=">>" htmlClass="pBtn1" />
																			<ezf:inputText name="rtlWhNm_AP" ezfName="rtlWhNm_AP" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
																		</td>
																		<!-- START 2023/02/28 TZ.Win [QC#61160, ADD] -->
																		<td class="stab"><ezf:anchor name="xxLinkAncr_AS" ezfName="xxLinkAncr_AS" ezfEmulateBtn="1" ezfGuard="Open_Win_SwhFromApply" >Sub Warehouse</ezf:anchor></td>
																		<td>
																			<ezf:inputText name="rtlSwhCd_AP" ezfName="rtlSwhCd_AP" otherAttr=" size=\"5\" ezftoupper=\"\""/>
																			<ezf:inputButton name="getSwhNameFromApply" value=">>" htmlClass="pBtn1" />
																			<ezf:inputText name="rtlSwhNm_AP" ezfName="rtlSwhNm_AP" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
																		</td>
																		<!-- END 2023/02/28 TZ.Win [QC#61160, ADD] -->
																		<td class="stab"><label>RMA Disposition</label></td>
																		<td>
																			<ezf:select name="thirdPtyDspTpCd_AP" ezfName="thirdPtyDspTpCd_AP" ezfBlank="1" ezfCodeName="thirdPtyDspTpCd" ezfDispName="thirdPtyDspTpDescTxt" otherAttr=" style=\"width:140px\""/>
																		</td>
																		<td>
																			<ezf:inputButton name="Apply" value="Apply" htmlClass="pBtn4" />
																		</td>
																	</tr>
																</table>
															</fieldset>
														</td>
														<td>
															<ezf:inputButton name="Print" value="Print" htmlClass="pBtn7" />
															<ezf:inputButton name="RWS_Cancel" value="Cancel" htmlClass="pBtn7" />
														</td>
													</tr>
												</table>
											</td>
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
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Tab_Ord'}">
<script type="text/javascript" defer>
S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true, false);
</script>
</c:when>
</c:choose>

<c:choose>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Tab_Rws'}">
<script type="text/javascript" defer>
S21TableUI.initialize("parentBoxB", "BHEAD", "B", -1, true, false);
</script>
</c:when>
</c:choose>

<%-- **** Task specific area ends here **** --%>
