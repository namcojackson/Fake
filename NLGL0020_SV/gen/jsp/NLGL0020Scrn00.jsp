<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170704090736 --%>
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
			<input type="hidden" name="pageID" value="NLGL0020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="PO Maintenance">
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table height="560" width="100%">
						<col valign="top">
						<tr>
							<td>
								<table height="30">

									<col width="48">
									<col width="104">
									<col width="35">

									<col width="48">
									<col width="104">
									<col width="35">

									<col width="35">
									<col width="62">

									<col width="43">

									<col width="25">
									<col width="120">
									<col width="111">
									<col width="10" align="left">
									<col width="111">
									<col width="5">

									<tr>
										<td class="stab">Wms Wh</td>
										<td align="left">
											<ezf:select name="whCd_02" ezfName="whCd_02" ezfBlank="1" ezfCodeName="whCd_01" ezfDispName="xxEdtCdNm_01" otherAttr=" style=\"width:150px;\""/>
										</td>
										<td></td>
										
										<td class="stab" align="right"><ezf:anchor name="rtlWhCd_AK" ezfName="rtlWhCd_AK" ezfEmulateBtn="1" ezfGuard="OpenWin_RtlWh" >WH</ezf:anchor></td>
										<td align="left">
											<table>
												<tr>
													<td><ezf:inputText name="rtlWhCd_01" ezfName="rtlWhCd_01" value="X---10---XX---10---X" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
													<td><ezf:inputButton name="OnClick_GetRtlWhNm" ezfName="OnClick_GetRtlWhNm" value=">>" htmlClass="pBtn0" /></td>
													<td><ezf:inputText name="rtlWhNm_01" ezfName="rtlWhNm_01" value="CANON SOLUTIONS AMERICA" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
												</tr>
											</table>
										</td>
										<td></td>

										<td class="stab" align="right">PO#</td>
										<td align="left"><ezf:inputText name="wmsPoId_01" ezfName="wmsPoId_01" value="X---10---XX---10---X" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td></td>

										<td class="stab">Date</td>
										<td>
											<ezf:select name="xxSrchRqstDtTpCd_02" ezfName="xxSrchRqstDtTpCd_02" ezfCodeName="xxSrchRqstDtTpCd_01" ezfDispName="xxEdtCdNm_02" />
										</td>
										<td>
											<td><ezf:inputText name="xxSoSrchFromDt_01" ezfName="xxSoSrchFromDt_01" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
											<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxSoSrchFromDt_01', 4 );"></td>
										</td>
										<td class="stab">-</td>
										<td>
											<td><ezf:inputText name="xxSoSrchThruDt_01" ezfName="xxSoSrchThruDt_01" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
											<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxSoSrchThruDt_01', 4 );"></td>
										</td>
										<td></td>

									</tr>
								</table>

								<table height="30">
									<col width="48" align="left">
									<col width="104">
									<col width="5">

									<col width="64">
									<col width="160">
									<col width="5">

									<col width="64">
									<col width="160">
									<col width="5">

									<col width="86">
									<col width="160">
									<col width="5">

									<col width="24" align="right">
									<col width="32">
									<col width="5">
									<col width="151">
									
									<tr>
										<td class="stab">Item Code</td>
										<td><ezf:inputText name="wmsMdseCd_01" ezfName="wmsMdseCd_01" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td></td>

										<td class="stab">Order Type</td>
										<td>
											<ezf:select name="wmsPrchTpCd_02" ezfName="sceOrdTpCd_02" ezfBlank="1" ezfCodeName="sceOrdTpCd_01" ezfDispName="fill40Txt_01" />
										</td>
										<td></td>

										<td class="stab">Inventory Owner</td>
										<td>
											<ezf:select name="invtyOwnrCd_01" ezfName="invtyOwnrCd_01" ezfBlank="1" ezfCodeName="invtyOwnrCd_LC" ezfDispName="invtyOwnrDescTxt_LD" />
										</td>
										<td></td>

										<td class="stab">Transaction Code</td>
										<td align="left">
											<ezf:select name="wmsTrxCd_02" ezfName="wmsTrxCd_02" ezfBlank="1" ezfCodeName="wmsTrxCd_01" ezfDispName="fill40Txt_02" />
										</td>
										<td></td>

										<td><ezf:inputCheckBox name="xxChkBox_01" ezfName="xxChkBox_01" value="Y" otherAttr=" id=\"OrderedCheck\""/></td>
										<td class="stab"><label for="OrderedCheck">Closed</labe></td>
										<td></td>


										<td><ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
								<hr>
<%-- ######################################## DETAIL ######################################## --%>
								<%-- ###TAB - HEAD --%>
								<div class="pTab_HEAD">
									<ul>
										<li id="POList" title="PO List" class="pTab_ON">
											<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OnClick_POListTab" >PO List</ezf:anchor>
										</li>
										<li id="POStatus" title="PO Status" class="pTab_OFF">
											<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OnClick_POStatusTab" >PO Status</ezf:anchor>
										</li>
										<li id="DnldEdt" title="Download Edit" class="pTab_OFF">
											<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OnClick_DownloadEditTab" >Download Edit</ezf:anchor>
										</li>
										<li id="UpldEdt" title="Upload Edit" class="pTab_OFF">
											<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OnClick_UploadEditTab" >Upload Edit</ezf:anchor>
										</li>
									</ul>
								</div>

								<c:choose>

<%-- ######################################## PO List TAB ######################################## --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab=='POList'}">
									<script type="text/javascript">
										document.getElementById("POList").className="pTab_ON";
										document.getElementById("POStatus").className="pTab_OFF";
										document.getElementById("DnldEdt").className="pTab_OFF";
										document.getElementById("UpldEdt").className="pTab_OFF";
									</script>

									<%-- ###TAB - BODY --%>
									<div class="pTab_BODY_In">
										<table height="357" width="100%">
											<tr valign="top">
												<td>
													<%-- Pagination --%>
													<table width="100%">
														<col width="750"  align="right">
														<tr align="right">
															<td>
															<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="A" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A1" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum_A1" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A1" />
															</jsp:include>
															</td>
														</tr>
													</table>

													<table border="1" cellpadding="0" cellspacing="0">
														<tr>
															<%-- ########## Table:A --%>
															<td align="left" valign="top">
																<table border="1" cellpadding="1" cellspacing="0" width="202">
																	<col width="40">
																	<col width="140" align="center">
																	
																	<tr height="37">
																		<td class="pClothBs">&nbsp;</td>
																		<td class="pClothBs">PO#</td>
																	</tr>
																</table>
																
																<%-- id:leftTBL --%>
																<div id="leftTBL" style="overflow-y:hidden; height:359; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));">
																	<table border="1" cellpadding="1" cellspacing="0" width="202" id="A_left">
																		<col width="40" align="center">
																		<col width="140" align="left">

																		<ezf:row ezfHyo="A">
																			<tr height="28">
																				<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"chkBox#{EZF_ROW_NUMBER}\""/></td>
																				<td><ezf:label name="wmsPoId_A1" ezfName="wmsPoId_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																			</tr>
																		</ezf:row>

																		<ezf:skip>
																			<tr height="28" class="pEvenNumberBGcolor">
																				<td><input type="checkbox" name="checkBox"value="Y"></td>
																				<td><label>APHU6891917</label></td>
																			</tr>

																			<tr height="28">
																				<td><input type="checkbox" name="checkBox"value="Y"></td>
																				<td><label>N-AX-10/53947</label></td>
																			</tr>
																		</ezf:skip>
																	</table>
																</div>
															</td>

															<%-- ########## Table:B --%>
															<td valign="top">
																<%-- id:topTBL --%>
																<div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:892;" onScroll="synchroScrollLeft(this.id, new Array('rightTBL'));">
																	<table border="1" cellpadding="1" cellspacing="0" width="1800">
																		<col width="48"  align="center">
																		<col width="80"  align="center">
																		<col width="180" align="center">
																		<col width="104" align="center">
																		<col width="64"  align="center">
																		<col width="180" align="center">
																		<col width="240" align="center">
																		<col width="240" align="center">
																		<col width="180" align="center">

																		<col width="50" align="center">
																		<col width="50" align="center">
																		<col width="150" align="center">

																		<tr height="37">
																			<td class="pClothBs">Order Type</td>
																			<td class="pClothBs">Transaction<br>Code</td>
																			<td class="pClothBs">From Date</td>
																			<td class="pClothBs">Vendor</td>
																			<td class="pClothBs">#Lines</td>
																			<td class="pClothBs">Download Date</td>
																			<td class="pClothBs">Comments</td>
																			<td class="pClothBs">Source</td>
																			<td class="pClothBs">MW Status</td>
																			<td class="pClothBs">Wh</td>
																			<td class="pClothBs">Owner</td>
																			<td class="pClothBs">Configuration ID</td>
																		</tr>
																	</table>
																</div>

																<%-- id:rightTBL --%>
																<div id="rightTBL" style="overflow-y:scroll; height:376; overflow-x:scroll; width:909;" onScroll="synchroScrollTop(this.id, new Array('leftTBL')); onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
																	<table border="1" cellpadding="1" cellspacing="0" width="1800" id="A_right">
																		<col width="48"  align="center">
																		<col width="80"  align="center">
																		<col width="180" align="center">
																		<col width="104" align="center">
																		<col width="64"  align="right">
																		<col width="180" align="center">
																		<col width="240" align="center">
																		<col width="240" align="center">
																		<col width="180" align="center">
																		<col width="50" align="center">
																		<col width="50" align="center">
																		<col width="150" align="center">

																		<ezf:row ezfHyo="A">
																			<tr height="28">
																				<td><ezf:label name="sceOrdTpCd_A1" ezfName="sceOrdTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsTrxCd_A1" ezfName="wmsTrxCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxDtTm_A1" ezfName="xxDtTm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="vndCd_A1" ezfName="vndCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxIntfcLineNum_A1" ezfName="xxIntfcLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxDtTm_A2" ezfName="xxDtTm_A2" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="xxMsgTxt_A1" ezfName="xxMsgTxt_A1" value="test" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"263\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="wmsResrcTxt_A1" ezfName="wmsResrcTxt_A1" value="test" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"250\" ezftoupper=\"\""/></td>
																				<td><ezf:label name="xxDtlCd_A1" ezfName="xxDtlCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>

																				<td><ezf:inputText name="rtlWhCd_A1" ezfName="rtlWhCd_A1" value="test" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"20\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="invtyOwnrCd_A1" ezfName="invtyOwnrCd_A1" value="test" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" value="test" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>

																			</tr>
																		</ezf:row>
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

<%-- ######################################## PO Status TAB ######################################## --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab=='POStatus'}">
									<script type="text/javascript">
										document.getElementById("POList").className="pTab_OFF";
										document.getElementById("POStatus").className="pTab_ON";
										document.getElementById("DnldEdt").className="pTab_OFF";
										document.getElementById("UpldEdt").className="pTab_OFF";
									</script>

									<div class="pTab_BODY_In">
										<div style="overflow-y: scroll; height: 415px">
											<table height="375" width="100%">
												<tr valign="top">
													<td>
														<table border="0" cellpadding="1" cellspacing="0" >
															<tr>
																<td valign="top">
																	<table border="0" width="100%">
																		<col width="1113">
																		<tr>
																			<td class="stab">Order Header</td>
																			<td></td>
																		</tr>
																	</table>

																	<%-- ### MEISAI - HEAD --%>
																	<div id="topTBL" style="overflow-x:scroll; width:1100;">
																		<table border="1" cellpadding="1" cellspacing="0"width="2300" >
																			<col align="center" width="120">
																			<col align="center" width="120">
																			<col align="center" width="160">
																			<col align="center" width="210">
																			<col align="center" width="140">
																			<col align="center" width="130">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="140">
																			<col align="center" width="185">
																			<col align="center" width="100">
																			<col align="center" width="140">
																			<col align="center" width="100">
																			<col align="center" width="260">
																			<col width="50" align="center">
																			<col width="50" align="center">
																			<col width="150" align="center">

																			<tr>
																				<td class="pClothBs">PO#</td>
																				<td class="pClothBs">Order Type</td>
																				<td class="pClothBs">Vendor</td>
																				<td class="pClothBs">Vessel</td>
																				<td class="pClothBs">BOL#</td>
																				<td class="pClothBs">Seal#</td>
																				<td class="pClothBs">Status</td>
																				<td class="pClothBs">Transaction<br>Code</td>
																				<td class="pClothBs">CreatedOn</td>
																				<td class="pClothBs">Source</td>
																				<td class="pClothBs">SeqId</td>
																				<td class="pClothBs">Closed Date</td>
																				<td class="pClothBs">NumPO</td>
																				<td class="pClothBs">Comments</td>
																				<td class="pClothBs">Wh</td>
																				<td class="pClothBs">Owner</td>
																				<td class="pClothBs">Configuration ID</td>
																			</tr>
																		</table>
																	
																	<%-- ### MEISAI - DETAIL --%>
																		<div id="bottomTBL" style="overflow-x:none; width:1100;" >
																		<table border="1" cellpadding="1" cellspacing="0"width="2300" > 
																			<col align="left" width="120">
																			<col align="center" width="120">
																			<col align="center" width="160">
																			<col align="center" width="210">
																			<col align="left" width="140">
																			<col align="left" width="130">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="140">
																			<col align="center" width="185">
																			<col align="left" width="100">
																			<col align="center" width="140">
																			<col align="right" width="100">
																			<col align="center" width="260">
																			<col width="50" align="center">
																			<col width="50" align="center">
																			<col width="150" align="center">
																			<ezf:row ezfHyo="B">
																			<tr>
																				<td><ezf:label name="wmsPoId_B2" ezfName="wmsPoId_B2" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="sceOrdTpCd_B1" ezfName="sceOrdTpCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="vndCd_B1" ezfName="vndCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="wmsVeslNm_B1" ezfName="wmsVeslNm_B1" value="test" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"23\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:label name="wmsBolNum_B1" ezfName="wmsBolNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsSerNum_B1" ezfName="wmsSerNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxIntfcItemStsDescTxt_B1" ezfName="xxIntfcItemStsDescTxt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsTrxCd_B1" ezfName="wmsTrxCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxDtTm_B1" ezfName="xxDtTm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="wmsResrcTxt_B1" ezfName="wmsResrcTxt_B1" value="test" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"23\" maxlength=\"250\" ezftoupper=\"\""/></td>
																				<td><ezf:label name="wmsSqNum_B2" ezfName="wmsSqNum_B2" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxDtTm_B2" ezfName="xxDtTm_B2" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="fill2Txt_B1" ezfName="fill2Txt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="xxMsgTxt_B1" ezfName="xxMsgTxt_B1" value="test" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"263\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="rtlWhCd_B1" ezfName="rtlWhCd_B1" value="test" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"20\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="invtyOwnrCd_B1" ezfName="invtyOwnrCd_B1" value="test" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="svcConfigMstrPk_B1" ezfName="svcConfigMstrPk_B1" value="test" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>
																			</tr>
																			</ezf:row>
																		</table>
																		</div>
																	</div>
																</td>
															</tr>



															<tr>
																<td valign="top">
																	<table border="0" width="100%">
																		<col width="1113">
																		<tr>
																			<td class="stab">Order Detail</td>
																			<td></td>
																		</tr>
																	</table>

																	<%-- ### MEISAI - HEAD --%>
																	<div id="topTBL" style="overflow-x:scroll; width:1100;">
																		<table border="1" cellpadding="1" cellspacing="0"width="1300" >
																			<col align="center" width="48">
																			<col align="center" width="120">
																			<col align="center" width="48">
																			<col align="center" width="120">
																			<col align="center" width="120">
																			<col align="center" width="96">
																			<col align="center" width="96">

																			<col align="center" width="100">
																			<col align="center" width="50">
																			<col align="center" width="100">
																			<col align="center" width="150">
																			<col align="center" width="200">

																			<tr>
																				<td class="pClothBs">Line#</td>
																				<td class="pClothBs">Item Code</td>
																				<td class="pClothBs">Stock<br>Status</td>
																				<td class="pClothBs">Invoice</td>
																				<td class="pClothBs">DO ID</td>
																				<td class="pClothBs">Open Quantity</td>
																				<td class="pClothBs">Received Quantity</td>


																				<td class="pClothBs">Package Code</td>
																				<td class="pClothBs">Owner</td>
																				<td class="pClothBs">Ser<br>Aprv Req</td>
																				<td class="pClothBs">Disposition Code</td>
																				<td class="pClothBs">Serial#</td>
																			</tr>
																		</table>
																	
																	<%-- ### MEISAI - DETAIL --%>
																		<div id="bottomTBL" style="overflow-x:none; width:1100;" >
																		<table border="1" cellpadding="1" cellspacing="0"width="1300" > 
																			<col align="left" width="48">
																			<col align="center" width="120">
																			<col align="center" width="48">
																			<col align="left" width="120">
																			<col align="left" width="120">
																			<col align="right" width="96">
																			<col align="right" width="96">
																			<col align="center" width="100">
																			<col align="center" width="50">
																			<col align="center" width="100">
																			<col align="center" width="150">
																			<col align="center" width="200">
																			<ezf:row ezfHyo="C">
																			<tr>
																				<td><ezf:label name="wmsLineNum_C1" ezfName="wmsLineNum_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsMdseCd_C1" ezfName="wmsMdseCd_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																				<td><ezf:label name="s80StkStsCd_C1" ezfName="s80StkStsCd_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsInvId_C1" ezfName="wmsInvId_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsDoId_C1" ezfName="wmsDoId_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsOpenQty_C1" ezfName="wmsOpenQty_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxIntfcOrdQty_C1" ezfName="xxIntfcOrdQty_C1" ezfHyo="C" ezfArrayNo="0" /></td>

																				<!-- <td><label ezfout ezfhyo="C" name="packCdTxt_C1" ezfname="packCdTxt_C1">A01NEW</label></td> -->
																				<td><ezf:inputText name="fill40Txt_C1" ezfName="fill40Txt_C1" value="A01NEW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"6\" ezftoupper=\"\""/></td>
																				<td><ezf:label name="invtyOwnrCd_C1" ezfName="invtyOwnrCd_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																				<td><ezf:label name="serApvlReqFlg_C1" ezfName="serApvlReqFlg_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																				<td><ezf:label name="usrCdRefTxt_C1" ezfName="usrCdRefTxt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																				<td><ezf:label name="serNum_C1" ezfName="serNum_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																			</tr>
																			</ezf:row>
<!--
																			<ezf:skip>
																			<tr>
																				<td><label ezfout ezfhyo="C">2</label></td>
																				<td><label ezfout ezfhyo="C">123456789012345</label></td>
																				<td><label ezfout ezfhyo="C">S1</label></td>
																				<td><label ezfout ezfhyo="C">N-TG-10/51717</label></td>
																				<td><label ezfout ezfhyo="C">IK3R029-01</label></td>
																				<td><label ezfout ezfhyo="C">1234567890</label></td>
																				<td><label ezfout ezfhyo="C">1234567890</label></td>
																			</tr>
																			</ezf:skip>
-->
																		</table>
																		</div>
																	</div>
																</td>
															</tr>

															<tr>
																<td valign="top">
																	<table border="0" width="100%">
																		<col width="250">
																		<tr height="8px">
																			<td></td>
																		</tr>
																	</table>
																</td>
															</tr>

															<tr>
																<td valign="top">
																	<table border="0" width="100%">
																		<col width="1113">
																		<tr>
																			<td class="stab">Upload Transaction </td>
																		</tr>
																		<tr>
																			<td class="stab">Receipts and Close (between PO create date in MW & 7 days after closed date)</td>
																		</tr>
																	</table>

																	<div id="topTBL" style="overflow-x:scroll; width:1113;">
																		<table border="1" cellpadding="1" cellspacing="0"width="1539" >
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="64">
																			<col align="center" width="120">
																			<col align="center" width="48">
																			<col align="center" width="120">
																			<col align="center" width="160">
																			<col align="center" width="160">
																			<col align="center" width="96">
																			<col align="center" width="120">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="135">

																			<tr>
																				<td class="pClothBs">Record ID</td>
																				<td class="pClothBs">Task</td>
																				<td class="pClothBs">Line#</td>
																				<td class="pClothBs">Item Code</td>
																				<td class="pClothBs">Stock Status</td>
																				<td class="pClothBs">Order Quantity</td>
																				<td class="pClothBs">Receipt Date</td>
																				<td class="pClothBs">OrderType</td>
																				<td class="pClothBs">Status</td>
																				<td class="pClothBs">Lot#</td>
																				<td class="pClothBs">Receiver</td>
																				<td class="pClothBs">User</td>
																				<td class="pClothBs">CreatedOn</td>
																			</tr>
																		</table>
																	<div id="bottomTBL" style="overflow-x:none; width:1113;" >
																		<table border="1" cellpadding="1" cellspacing="0"width="1539" > 
																			<col align="left" width="96">
																			<col align="center" width="96">
																			<col align="left" width="64">
																			<col align="center" width="120">
																			<col align="center" width="48">
																			<col align="right" width="120">
																			<col align="center" width="160">
																			<col align="center" width="160">
																			<col align="center" width="96">
																			<col align="left" width="120">
																			<col align="left" width="96">
																			<col align="left" width="96">
																			<col align="center" width="135">
																			<ezf:row ezfHyo="D">
																			<tr>
																				<td><ezf:label name="wmsRecId_D1" ezfName="wmsRecId_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsTaskCd_D1" ezfName="wmsTaskCd_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																				<td><ezf:label name="inbdOrdLineNum_D1" ezfName="inbdOrdLineNum_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsMdseCd_D1" ezfName="wmsMdseCd_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsStkStsCd_D1" ezfName="wmsStkStsCd_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsOrdQty_D1" ezfName="wmsOrdQty_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxDtTm_D1" ezfName="xxDtTm_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																				<td><ezf:label name="sceOrdTpCd_D1" ezfName="sceOrdTpCd_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsOrdStsCd_D1" ezfName="wmsOrdStsCd_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsLotNum_D1" ezfName="wmsLotNum_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsRcptId_D1" ezfName="wmsRcptId_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsOpId_D1" ezfName="wmsOpId_D1" ezfHyo="D" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxDtTm_D2" ezfName="xxDtTm_D2" ezfHyo="D" ezfArrayNo="0" /></td>
																			</tr>
																			</ezf:row>
																			<ezf:skip>
																			<tr>
																				<td><label ezfout ezfhyo="D">3578795</label></td>
																				<td><label ezfout ezfhyo="D">RCVD</label></td>
																				<td><label ezfout ezfhyo="D">1</label></td>
																				<td><label ezfout ezfhyo="D">3702B001AA</label></td>
																				<td><label ezfout ezfhyo="D">S1</label></td>
																				<td><label ezfout ezfhyo="D">1234567890</label></td>
																				<td><label ezfout ezfhyo="D">03/13/2013 11:17</label></td>
																				<td><label ezfout ezfhyo="D">1234</label></td>
																				<td><label ezfout ezfhyo="D">1234</label></td>
																				<td><label ezfout ezfhyo="D">123456789</label></td>
																				<td><label ezfout ezfhyo="D">1234567890</label></td>
																				<td><label ezfout ezfhyo="D">XXXXXXXXXX</label></td>
																				<td><label ezfout ezfhyo="D">03/13/2013 11:17</label></td>
																			</tr>
																			</ezf:skip>
																		</table>
																		</div>
																	</div>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
									</div>
								</c:when>

<%-- ######################################## Download Edit TAB ######################################## --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab=='DnldEdt'}">
									<script type="text/javascript">
										document.getElementById("POList").className="pTab_OFF";
										document.getElementById("POStatus").className="pTab_OFF";
										document.getElementById("DnldEdt").className="pTab_ON";
										document.getElementById("UpldEdt").className="pTab_OFF";
									</script>

									<div class="pTab_BODY_In">
										<div style="overflow-y: scroll; height: 415px">
											<table height="375" width="100%">
												<tr valign="top">
													<td>

																	<table border="0" width="100%">
																		<col width="1113">
																		<tr>
																			<td class="stab">Order Header</td>
																			<td></td>
																		</tr>
																	</table>
																	<%-- ### MEISAI - HEAD --%>
																	<div id="topTBL" style="overflow-x:scroll; width:1113;">
																		<table border="1" cellpadding="1" cellspacing="0"width="2180" >
																			<col align="center" width="120">
																			<col align="center" width="96">
																			<col align="center" width="120">
																			<col align="center" width="160">
																			<col align="center" width="200">
																			<col align="center" width="140">
																			<col align="center" width="130">
																			<col align="center" width="96">
																			<col align="center" width="100">
																			<col align="center" width="135">
																			<col align="center" width="185">
																			<col align="center" width="135">
																			<col align="center" width="96">
																			<col width="50" align="center">
																			<col width="50" align="center">
																			<col width="150" align="center">

																			<tr>
																				<td class="pClothBs">PO#</td>
																				<td class="pClothBs">SeqId</td>
																				<td class="pClothBs">Order Type</td>
																				<td class="pClothBs">Vendor</td>
																				<td class="pClothBs">Vessel</td>
																				<td class="pClothBs">BOL#</td>
																				<td class="pClothBs">Seal#</td>
																				<td class="pClothBs">Status</td>
																				<td class="pClothBs">Transaction<br>Code</td>
																				<td class="pClothBs">CreatedOn</td>
																				<td class="pClothBs">Source</td>
																				<td class="pClothBs">Closed Date</td>
																				<td class="pClothBs">NumPO</td>
																				<td class="pClothBs">Wh</td>
																				<td class="pClothBs">Owner</td>
																				<td class="pClothBs">Configuration ID</td>
																			</tr>
																		</table>
																	
																	<%-- ### MEISAI - DETAIL --%>
																		<div id="bottomTBL" style="overflow-x:none; width:1113;" >
																		<table border="1" cellpadding="1" cellspacing="0"width="2180" > 
																			<col align="left" width="120">
																			<col align="left" width="96">
																			<col align="center" width="120">
																			<col align="center" width="160">
																			<col align="center" width="200">
																			<col align="left" width="140">
																			<col align="left" width="130">
																			<col align="center" width="96">
																			<col align="center" width="100">
																			<col align="center" width="135">
																			<col align="center" width="185">
																			<col align="center" width="135">
																			<col align="right" width="96">
																			<col width="50" align="center">
																			<col width="50" align="center">
																			<col width="150" align="center">
																			<ezf:row ezfHyo="G">
																			<tr>
																				<td><ezf:label name="wmsPoId_G2" ezfName="wmsPoId_G2" ezfHyo="G" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsSqNum_G2" ezfName="wmsSqNum_G2" ezfHyo="G" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsPrchTpNm_G1" ezfName="wmsPrchTpNm_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																				<td><ezf:label name="vndCd_G1" ezfName="vndCd_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="wmsVeslNm_G1" ezfName="wmsVeslNm_G1" value="1234567890" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"23\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:label name="wmsBolNum_G1" ezfName="wmsBolNum_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsSerNum_G1" ezfName="wmsSerNum_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxIntfcItemStsDescTxt_G1" ezfName="xxIntfcItemStsDescTxt_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsTrxCd_G1" ezfName="wmsTrxCd_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxDtTm_G1" ezfName="xxDtTm_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="wmsResrcTxt_G1" ezfName="wmsResrcTxt_G1" value="1234567890" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"23\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:label name="xxDtTm_G2" ezfName="xxDtTm_G2" ezfHyo="G" ezfArrayNo="0" /></td>
																				<td><ezf:label name="fill2Txt_G1" ezfName="fill2Txt_G1" ezfHyo="G" ezfArrayNo="0" /></td>


																				<td><ezf:inputText name="rtlWhCd_G1" ezfName="rtlWhCd_G1" value="test" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"20\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="invtyOwnrCd_G1" ezfName="invtyOwnrCd_G1" value="test" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="svcConfigMstrPk_G1" ezfName="svcConfigMstrPk_G1" value="test" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>

																			</tr>
																			</ezf:row>
																			<ezf:skip>
																			<tr>
																				<td><label ezfout ezfhyo="G">FSCU4733005</label></td>
																				<td><label ezfout ezfhyo="G">&nbsp;</label></td>
																				<td><label ezfout ezfhyo="G">1131833</label></td>
																				<td><label ezfout ezfhyo="G">1234567890</label></td>
																				<td><label ezfout ezfhyo="G">1234567890</label></td>
																				<td><label ezfout ezfhyo="G">BR852/30</label></td>
																				<td><label ezfout ezfhyo="G">1234567890</label></td>
																				<td><label ezfout ezfhyo="G">1234567890</label></td>
																				<td><label ezfout ezfhyo="G">XXXXXXXXXX</label></td>
																				<td><label ezfout ezfhyo="G">12345678</label></td>
																				<td><label ezfout ezfhyo="G">&nbsp;</label></td>
																				<td><label ezfout ezfhyo="G">&nbsp;</label></td>
																				<td><label ezfout ezfhyo="G">&nbsp;</label></td>
																			</tr>
																			</ezf:skip>
																		</table>
																		</div>
																	</div>
														<table border="0" cellpadding="1" cellspacing="0" >
															<tr>
																<td valign="top">
																	<table border="0" width="100%">
																		<col>
																		<tr height="8px">
																			<td></td>
																		</tr>
																	</table>

																	<fieldset>
																	<legend>Download Edit</legend>
																	<table height="30">
																		<col width="64" align="left">
																		<col width="200">
																		<tr>
																			<td class="stab">Submit Type</td>
																			<td>
																				<ezf:select name="xxSrchRqstDtTpCd_E2" ezfName="xxSrchRqstDtTpCd_E2" ezfBlank="1" ezfCodeName="xxSrchRqstDtTpCd_E1" ezfDispName="xxEdtCdNm_E2" otherEvent1=" onchange=\"sendServer('OnChange_SubmitList')\"" />
																			</td>
																		</tr>
																	</table>

																	<table height="30" border="0">
																		<col width="64">
																		<col width="150">
																		<col width="5">
																		<col width="64">
																		<col width="150">
																		<col width="5">
																		<col width="64">
																		<col width="100">
																		<col width="5">
																		<col width="80">
																		<col width="200">
																		<tr>
																			<td class="stab">Wms Wh</td>
																			<td align="left">
																				<ezf:select name="whCd_E2" ezfName="whCd_E2" ezfBlank="1" ezfCodeName="whCd_E1" ezfDispName="xxEdtCdNm_E1" otherEvent1=" onchange=\"sendServer('SelectWh')\"" otherAttr=" style=\"width:150px;\""/>
																			</td>
																			<td></td>
																			<td class="stab">Wh</td>
																			<td align="left">
																				<ezf:select name="rtlWhCd_E1" ezfName="rtlWhCd_E1" ezfBlank="1" ezfCodeName="rtlWhCd_LC" ezfDispName="xxEdtCdNm_LD" otherEvent1=" onchange=\"sendServer('SelectRetailWh')\"" otherAttr=" style=\"width:150px;\""/>
																			</td>
																			<td></td>
																			<td class="stab">Owner</td>
																			<td align="left">
                                        										<ezf:select name="invtyOwnrCd_E1" ezfName="invtyOwnrCd_E1" ezfBlank="1" ezfCodeName="invtyOwnrCd_LC" ezfDispName="invtyOwnrDescTxt_LD" otherAttr=" style=\"width:60px;\""/>
																			</td>
																			<td></td>
																			<td class="stab">Configuration ID</td>
																			<td><ezf:inputText name="svcConfigMstrPk_E1" ezfName="svcConfigMstrPk_E1" value="test" otherAttr=" size=\"28\" maxlength=\"28\" ezftoupper=\"\""/></td>
																		</tr>
																	</table>

																	<table border="0" width="100%">
																		<col>
																		<tr height="8px">
																			<td></td>
																		</tr>
																	</table>

																	<table border="0" width="100%">
																		<col width="48">
																		<tr>
																			<td class="stab">Item Input</td>
																		</tr>
																	</table>

																	<table cellpadding="1" cellspacing="0">
																		<col width="91">
																		<col width="91">
																		<tr>
																			<td><ezf:inputButton name="OnClick_CheckAll" value="Check All" htmlClass="pBtn6" /></td>
																			<td><ezf:inputButton name="OnClick_UncheckAll" value="UnCheck All" htmlClass="pBtn6" /></td>
																		</tr>
																	</table>

																	<table border="1" cellpadding="1" cellspacing="0" width="1100">
																		<col width="40"  align="center">
																		<col width="56" align="center">
																		<col width="120" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="130">
																			<col align="center" width="180">
																		<tr height="28">
																			<td class="pClothBs">&nbsp;</td>
																			<td class="pClothBs">Line#</td>
																			<td class="pClothBs">Item Code</td>
																			<td class="pClothBs">Quantity/Units</td>
																			<td class="pClothBs">UOM</td>
																			<td class="pClothBs">Stock Status</td>

																				<td class="pClothBs">Package Code</td>
																				<td class="pClothBs">Ser<br>Aprv Req</td>
																				<td class="pClothBs">Disposition Code</td>
																				<td class="pClothBs">Serial#</td>
																		</tr>
																	</table>
																	<table border="1" cellpadding="1" cellspacing="0" width="1100" id="F">
																		<col align="center" width="40">
																		<col align="left" width="56">
																		<col align="center" width="120">
																		<col align="center" width="100">
																		<col align="center" width="100" align="center">
																		<col align="center" width="100" align="center">

																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="130">
																			<col align="center" width="180">


																		<ezf:row ezfHyo="F">
																		<tr height="28">
																			<td><ezf:inputCheckBox name="xxChkBox_F1" ezfName="xxChkBox_F1" value="Y" ezfHyo="F" ezfArrayNo="0" otherAttr=" id=\"chkBox#{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:label name="wmsLineNum_F1" ezfName="wmsLineNum_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																			<td><ezf:inputText name="wmsMdseCd_F1" ezfName="wmsMdseCd_F1" value="2534B005AA" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="rwsQty_F1" ezfName="rwsQty_F1" value="123456789" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"9\" ezftoupper=\"\""/></td>
																			<td>
																				<ezf:select name="wmsUomCd_F2" ezfName="wmsUomCd_F2" ezfHyo="F" ezfArrayNo="0" ezfBlank="1" ezfCodeName="wmsUomCd_F1" ezfDispName="wmsUomDescTxt_F1" ezfCodeDispHyo="F" otherAttr=" style=\"width:100px\""/>
																			</td>
																			<td>
																				<ezf:select name="wmsStkStsCd_F2" ezfName="wmsStkStsCd_F2" ezfHyo="F" ezfArrayNo="0" ezfBlank="1" ezfCodeName="wmsStkStsCd_F1" ezfDispName="wmsStkStsNm_F1" ezfCodeDispHyo="F" otherAttr=" style=\"width:100px\""/>
																			</td>
																			<td>
																				<ezf:select name="packCdTxt_F1" ezfName="packCdTxt_F1" ezfHyo="F" ezfArrayNo="0" ezfBlank="1" ezfCodeName="fill40Txt_LC" ezfDispName="fill40Txt_LD" otherAttr=" style=\"width:90px\""/>
																			</td>
																			<td>
																				<ezf:select name="serApvlReqFlg_F1" ezfName="serApvlReqFlg_F1" ezfHyo="F" ezfArrayNo="0" ezfCodeName="serApvlReqFlg_LC" ezfDispName="serApvlReqFlg_LD" otherAttr=" style=\"width:60px\""/>
																			</td>
																			<td>
																				<ezf:select name="usrCdRefTxt_F1" ezfName="usrCdRefTxt_F1" ezfHyo="F" ezfArrayNo="0" ezfBlank="1" ezfCodeName="thirdPtyDspTpCd_LC" ezfDispName="thirdPtyDspTpDescTxt_LD" otherAttr=" style=\"width:100px\""/>
																			</td>
																			<td><ezf:inputText name="serNum_F1" ezfName="serNum_F1" value="123456789" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"30\" ezftoupper=\"\""/></td>

																		</tr>
																		</ezf:row>

																	</table>

																	<table cellpadding="1" cellspacing="0">
																		<col width="182">
																		<col width="710">
																		<col width="91"  align="right">
																		<tr>
																			<td><ezf:inputButton name="OnClick_DNLD_DeleteRow" value="Delete Row" htmlClass="pBtn6" /></td>
																			<td></td>
																			<td><ezf:inputButton name="OnClick_DNLD_InsertRow" value="Insert Row" htmlClass="pBtn8" /></td>
																		</tr>
																	</table>
																	</fieldset>
																	<table border="0" width="100%">
																		<col>
																		<tr height="16px">
																			<td></td>
																		</tr>
																	</table>
																</td>
															</tr>

															<tr>
																<td valign="top">
																	<table border="0" width="100%">
																		<col width="250">
																		<tr height="8px">
																			<td></td>
																		</tr>
																	</table>
																</td>
															</tr>












															<tr>
																<td valign="top">
																	<table border="0" width="100%">
																		<col width="1113">
																		<tr>
																			<td class="stab">Order Detail</td>
																			<td></td>
																		</tr>
																	</table>

																	<%-- ### MEISAI - HEAD --%>
																	<div id="topTBL" style="overflow-x:scroll; width:1100;">
																		<table border="1" cellpadding="1" cellspacing="0"width="1400" >
																			<col align="center" width="48">
																			<col align="center" width="120">
																			<col align="center" width="48">
																			<col align="center" width="120">
																			<col align="center" width="120">
																			<col align="center" width="96">
																			<col align="center" width="150">

																			<col align="center" width="100">
																			<col align="center" width="50">
																			<col align="center" width="100">
																			<col align="center" width="150">
																			<col align="center" width="200">

																			<tr>
																				<td class="pClothBs">Line#</td>
																				<td class="pClothBs">Item Code</td>
																				<td class="pClothBs">Stock Staus</td>
																				<td class="pClothBs">Invoice</td>
																				<td class="pClothBs">DO ID</td>
																				<td class="pClothBs">Open Quantity</td>
																				<td class="pClothBs">Received Quantity</td>

																				<td class="pClothBs">Package Code</td>
																				<td class="pClothBs">Owner</td>
																				<td class="pClothBs">Ser<br>Aprv Req</td>
																				<td class="pClothBs">Disposition Code</td>
																				<td class="pClothBs">Serial#</td>
																			</tr>
																		</table>
																	
																	<%-- ### MEISAI - DETAIL --%>
																		<div id="bottomTBL" style="overflow-x:none; width:1100;" >
																		<table border="1" cellpadding="1" cellspacing="0"width="1400" > 
																			<col align="left" width="48">
																			<col align="center" width="120">
																			<col align="center" width="48">
																			<col align="left" width="120">
																			<col align="left" width="120">
																			<col align="right" width="96">
																			<col align="right" width="150">

																			<col align="center" width="100">
																			<col align="center" width="50">
																			<col align="center" width="100">
																			<col align="center" width="150">
																			<col align="center" width="200">
																			<ezf:row ezfHyo="H">
																			<tr>
																				<td><ezf:label name="wmsLineNum_H1" ezfName="wmsLineNum_H1" ezfHyo="H" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsMdseCd_H1" ezfName="wmsMdseCd_H1" ezfHyo="H" ezfArrayNo="0" /></td>
																				<td><ezf:label name="s80StkStsCd_H1" ezfName="s80StkStsCd_H1" ezfHyo="H" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsInvId_H1" ezfName="wmsInvId_H1" ezfHyo="H" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsDoId_H1" ezfName="wmsDoId_H1" ezfHyo="H" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsOpenQty_H1" ezfName="wmsOpenQty_H1" ezfHyo="H" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxIntfcOrdQty_H1" ezfName="xxIntfcOrdQty_H1" ezfHyo="H" ezfArrayNo="0" /></td>

																				<!-- <td><label ezfout ezfhyo="H" name="fill40Txt_H1" ezfname="fill40Txt_H1">A01NEW</label></td>-->
																				<td><ezf:inputText name="fill40Txt_H1" ezfName="fill40Txt_H1" value="A01NEW" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"6\" ezftoupper=\"\""/></td>
																				<td><ezf:label name="invtyOwnrCd_H1" ezfName="invtyOwnrCd_H1" ezfHyo="H" ezfArrayNo="0" /></td>
																				<td><ezf:label name="serApvlReqFlg_H1" ezfName="serApvlReqFlg_H1" ezfHyo="H" ezfArrayNo="0" /></td>
																				<td><ezf:label name="usrCdRefTxt_H1" ezfName="usrCdRefTxt_H1" ezfHyo="H" ezfArrayNo="0" /></td>
																				<td><ezf:label name="serNum_H1" ezfName="serNum_H1" ezfHyo="H" ezfArrayNo="0" /></td>
																			</tr>
																			</ezf:row>
																			<ezf:skip>
																			<tr>
																				<td><label ezfout ezfhyo="H">2</label></td>
																				<td><label ezfout ezfhyo="H">123456789012345</label></td>
																				<td><label ezfout ezfhyo="H">S1</label></td>
																				<td><label ezfout ezfhyo="H">N-TG-10/51717</label></td>
																				<td><label ezfout ezfhyo="H">IK3R029-01</label></td>
																				<td><label ezfout ezfhyo="H">1234567890</label></td>
																				<td><label ezfout ezfhyo="H">1234567890</label></td>
																			</tr>
																			</ezf:skip>
																		</table>
																		</div>
																	</div>
																</td>
															</tr>
























<!--

															<tr>
																<td valign="top">
																	<table border="0" width="100%">
																		<col width="1113">
																		<tr>
																			<td class="stab">Order Detail</td>
																			<td></td>
																		</tr>
																	</table>

																	<div id="topTBL" style="overflow-x:none; width:1100;">
																		<table border="1" cellpadding="1" cellspacing="0"width="1100" >
																			<col align="center" width="48">
																			<col align="center" width="120">
																			<col align="center" width="48">
																			<col align="center" width="120">
																			<col align="center" width="120">
																			<col align="center" width="96">
																			<col align="center" width="150">

																			<col align="center" width="100">
																			<col align="center" width="50">
																			<col align="center" width="100">
																			<col align="center" width="150">
																			<col align="center" width="200">

																			<tr>
																				<td class="pClothBs">Line#</td>
																				<td class="pClothBs">Item Code</td>
																				<td class="pClothBs">Stock Staus</td>
																				<td class="pClothBs">Invoice</td>
																				<td class="pClothBs">DO ID</td>
																				<td class="pClothBs">Open Quantity</td>
																				<td class="pClothBs">Received Quantity</td>

																				<td class="pClothBs">Package Code</td>
																				<td class="pClothBs">Owner</td>
																				<td class="pClothBs">Ser<br>Aprv Req</td>
																				<td class="pClothBs">Disposition Code</td>
																				<td class="pClothBs">Serial#</td>
																			</tr>
																		</table>
																	</div>

																	<div id="bottomTBL" style="overflow-x:none; width:1100;" >
																		<table border="1" cellpadding="1" cellspacing="0"width="1100" > 
																			<col align="left" width="48">
																			<col align="center" width="120">
																			<col align="center" width="48">
																			<col align="left" width="120">
																			<col align="left" width="120">
																			<col align="right" width="96">
																			<col align="right" width="150">

																			<col align="center" width="100">
																			<col align="center" width="50">
																			<col align="center" width="100">
																			<col align="center" width="150">
																			<col align="center" width="200">
																			<ezf:row ezfHyo="H">
																			<tr>
																				<td><label ezfout ezfhyo="H" name="wmsLineNum_H1" ezfname="wmsLineNum_H1">1</label></td>
																				<td><label ezfout ezfhyo="H" name="wmsMdseCd_H1" ezfname="wmsMdseCd_H1">123456789012345</label></td>
																				<td><label ezfout ezfhyo="H" name="s80StkStsCd_H1" ezfname="s80StkStsCd_H1">S1</label></td>
																				<td><label ezfout ezfhyo="H" name="wmsInvId_H1" ezfname="wmsInvId_H1">N-TG-10/51717</label></td>
																				<td><label ezfout ezfhyo="H" name="wmsDoId_H1" ezfname="wmsDoId_H1">IK3R029-01</label></td>
																				<td><label ezfout ezfhyo="H" name="wmsOpenQty_H1" ezfname="wmsOpenQty_H1">1234567890</label></td>
																				<td><label ezfout ezfhyo="H" name="xxIntfcOrdQty_H1" ezfname="xxIntfcOrdQty_H1">1234567890</label></td>

																				<td><label ezfout ezfhyo="C" name="s80StkStsCd_C1" ezfname="s80StkStsCd_C1">A01NEW</label></td>
																				<td><label ezfout ezfhyo="C" name="wmsInvId_C1" ezfname="wmsInvId_C1">CSA</label></td>
																				<td><label ezfout ezfhyo="C" name="wmsDoId_C1" ezfname="wmsDoId_C1">Y</label></td>
																				<td><label ezfout ezfhyo="C" name="wmsOpenQty_C1" ezfname="wmsOpenQty_C1">Ross</label></td>
																				<td><label ezfout ezfhyo="C" name="xxIntfcOrdQty_C1" ezfname="xxIntfcOrdQty_C1">1234567890</label></td>
																			</tr>
																			</ezf:row>
																			<ezf:skip>
																			<tr>
																				<td><label ezfout ezfhyo="H">2</label></td>
																				<td><label ezfout ezfhyo="H">123456789012345</label></td>
																				<td><label ezfout ezfhyo="H">S1</label></td>
																				<td><label ezfout ezfhyo="H">N-TG-10/51717</label></td>
																				<td><label ezfout ezfhyo="H">IK3R029-01</label></td>
																				<td><label ezfout ezfhyo="H">1234567890</label></td>
																				<td><label ezfout ezfhyo="H">1234567890</label></td>
																			</tr>
																			</ezf:skip>
																		</table>
																	</div>
																</td>
															</tr>
-->
															<tr>
																<td valign="top">
																	<table border="0" width="100%">
																		<col width="250">
																		<tr height="8px">
																			<td></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
									</div>
								</c:when>

<%-- ######################################## Upload Edit TAB ######################################## --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab=='UpldEdt'}">
									<script type="text/javascript">
										document.getElementById("POList").className="pTab_OFF";
										document.getElementById("POStatus").className="pTab_OFF";
										document.getElementById("DnldEdt").className="pTab_OFF";
										document.getElementById("UpldEdt").className="pTab_ON";
									</script>
									<div class="pTab_BODY_In" style="height: 400px">
										<table height="365" width="100%">
											<tr valign="top">
												<td>
													<table border="0">
														<col width="80">
														<col width="80">
														<col width="25">
														<col width="48" align="right">
														<col width="48" align="left">
														<col width="160" align="right">
														<col width="50" align="right">
														<col width="50" align="left">
														<col width="225" align="left">
														<col width="25" align="right">
														<col width="30" align="left">
														<col width="25" align="right">
														<col width="30" align="left">
														<col width="10" align="center">
														<col width="50" align="right">
														<col width="17" align="left">
														<tr height="20px">
															<td><ezf:inputButton name="OnClick_UPD_CheckAll" value="Check All" htmlClass="pBtn8" /></td>
															<td><ezf:inputButton name="OnClick_UPD_UncheckAll" value="UnCheck All" htmlClass="pBtn8" /></td>
															<td></td>
															<td class="stab">Order#</td>
															<td><ezf:label name="wmsPoId_I1" ezfName="wmsPoId_I1" /></td>
															<td></td>
															<td class="stab">Task</td>
															<td>
																<ezf:select name="wmsTaskCd_I4" ezfName="wmsTaskCd_I4" ezfCodeName="wmsTaskCd_I3" ezfDispName="crsSvcTaskNm_I3" />
															</td>
															<td></td>
															<td><ezf:inputCheckBox name="xxChkBox_I2" ezfName="xxChkBox_I2" value="Y" /></td>
															<td class="stab"><label for="OrderedCheck">Active</labe></td>
															<td><ezf:inputCheckBox name="xxChkBox_I3" ezfName="xxChkBox_I3" value="Y" /></td>
															<td class="stab"><label for="OrderedCheck">InActive</labe></td>
															<td></td>
															<td><ezf:inputButton name="OnClick_UPD_Search" value="Search" htmlClass="pBtn6" /></td>
															<td></td>
														</tr>
													</table>
													<table border="1" cellpadding="0" cellspacing="0">
														<tr>
															<%-- ########## Table:A --%>
															<td align="left" valign="top">
																<table border="1" cellpadding="0" cellspacing="0" width="470">
																	<col align="center" width="40">
																	<col align="center" width="35">
																	<col align="center" width="35">
																	<col align="center" width="85">
																	<col align="center" width="95">
																	<col align="center" width="50">
																	<col align="center" width="75">
																	<tr height="37">
																		<td class="pClothBs">&nbsp;</td>
																		<td class="pClothBs">Actv</td>
																		<td class="pClothBs">Hist</td>
																		<td class="pClothBs">Processed</td>
																		<td class="pClothBs">Record ID</td>
																		<td class="pClothBs">Line#</td>
																		<td class="pClothBs">Task</td>
																	</tr>
																</table>
																<%-- id:leftTBL --%>
																<div id="leftTBLI" style="overflow-y:hidden; height:339; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBLI'));">
																<table border="1" cellpadding="0" cellspacing="0" width="470" id="I_left">
																	<col align="center" width="40">
																	<col align="center" width="35">
																	<col align="center" width="35">
																	<col align="center" width="85">
																	<col align="center" width="95">
																	<col align="center" width="50">
																	<col align="center" width="75">
																	<ezf:row ezfHyo="I">
																	<tr height="28">
																		<td><ezf:inputCheckBox name="xxChkBox_I1" ezfName="xxChkBox_I1" value="Y" ezfHyo="I" ezfArrayNo="0" otherAttr=" id=\"chkBox#{EZF_ROW_NUMBER}\""/></td>
																		<td><ezf:label name="xxExstFlg_I1" ezfName="xxExstFlg_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																		<td><ezf:label name="wmsUpdHistNum_I1" ezfName="wmsUpdHistNum_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																		<td><ezf:label name="xxProcFlgNm_I1" ezfName="xxProcFlgNm_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																		<td><ezf:label name="wmsRecId_I1" ezfName="wmsRecId_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="inbdOrdLineNum_I1" ezfName="inbdOrdLineNum_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
																		<td>
																			<ezf:select name="wmsTaskCd_I2" ezfName="wmsTaskCd_I2" ezfHyo="I" ezfArrayNo="0" ezfBlank="1" ezfCodeName="wmsTaskCd_I1" ezfDispName="crsSvcTaskNm_I1" ezfCodeDispHyo="I" otherAttr=" style=\"width:60px;\""/>
																		</td>
																	</tr>
																	</ezf:row>
																</table>
																</div>
															</td>
															<td valign="top">
																<%-- id:topTBL --%>
																<div id="topTBLI" style="overflow-y:none; height:; overflow-x:hidden; overflow-y:hidden; width:665;" onScroll="synchroScrollLeft(this.id, new Array('rightTBLI'));">
																<table border="1" cellpadding="1" cellspacing="0" width="1150">
																	<col align="center" width="155">
																	<col align="center" width="100">
																	<col align="center" width="155">
																	<col align="center" width="100">
																	<col align="center" width="155">
																	<col align="center" width="155">
																	<col align="center" width="100">
																	<col align="center" width="155">
																	<tr height="37">	
																		<td class="pClothBs">Item Code</td>
																		<td class="pClothBs">Stock<br>Status</td>
																		<td class="pClothBs">Quantity</td>
																		<td class="pClothBs">Ord Tp</td>
																		<td class="pClothBs">Transaction<br>Date</td>
																		<td class="pClothBs">CreatedOn</td>
																		<td class="pClothBs">Package Code</td>
																		<td class="pClothBs">Serial Number</td>
																	</tr>
																</table>
																</div>
																
																<%-- id:rightTBL --%>
																<div id="rightTBLI" style="overflow-y:scroll; height:356; overflow-x:scroll; width:682;" onScroll="synchroScrollTop(this.id, new Array('leftTBLI')); onScroll=synchroScrollLeft(this.id, new Array('topTBLI'));">
																<table border="1" cellpadding="1" cellspacing="0" width="1150" id="A_right">
																	<col align="center" width="155">
																	<col align="center" width="100">
																	<col align="center" width="155">
																	<col align="center" width="100">
																	<col align="center" width="155">
																	<col align="center" width="155">
																	<col align="center" width="100">
																	<col align="center" width="155">
																	<ezf:row ezfHyo="I">
																	<tr height="28">
																		<td><ezf:inputText name="wmsMdseCd_I1" ezfName="wmsMdseCd_I1" value="2534B005AA" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\""/></td>
																		<td>
																			<ezf:select name="wmsStkStsCd_I2" ezfName="wmsStkStsCd_I2" ezfHyo="I" ezfArrayNo="0" ezfBlank="1" ezfCodeName="wmsStkStsCd_I1" ezfDispName="wmsStkStsNm_I1" ezfCodeDispHyo="I" otherAttr=" style=\"width:40px;\""/>
																		</td>
																		<td><ezf:inputText name="wmsOrdQty_I1" ezfName="wmsOrdQty_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\""/></td>
																		<td>
																			<ezf:select name="sceOrdTpCd_I2" ezfName="sceOrdTpCd_I2" ezfHyo="I" ezfArrayNo="0" ezfBlank="1" ezfCodeName="sceOrdTpCd_I1" ezfDispName="wmsPrchTpNm_I2" ezfCodeDispHyo="I" otherAttr=" style=\"width:40px;\""/>
																		</td>
																		<td><ezf:label name="xxDtTm_I1" ezfName="xxDtTm_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																		<td><ezf:label name="xxDtTm_I2" ezfName="xxDtTm_I2" ezfHyo="I" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="packCdTxt_I1" ezfName="packCdTxt_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\""/></td>
																		<td><ezf:inputText name="serNum_I1" ezfName="serNum_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\""/></td>
																	</tr>
																	</ezf:row>
																</table>
																</div>
															</td>
														</tr>
													</table>
													<table cellpadding="1" cellspacing="0">
														<col width="91">
														<col width="91">
														<col width="840">
														<col width="91">
														<tr>
															<td><ezf:inputButton name="OnClick_UPL_CopyRow" value="Copy Row" htmlClass="pBtn8" /></td>
															<td><ezf:inputButton name="OnClick_UPL_DeleteRow" value="Delete Row" htmlClass="pBtn8" /></td>
															<td></td>
															<td><ezf:inputButton name="OnClick_UPL_InsertRow" value="Insert Row" htmlClass="pBtn8" /></td>
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

<%-- **** Task specific area ends here **** --%>
