<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170703164649 --%>
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
			<input type="hidden" name="pageID" value="NLGL0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="So Maintenance">
			<center>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<%-- ######################################## HEADER ######################################## --%>
					<%-- include S21BusinessProcessTAB --%>
					<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

					<div class="pTab_BODY">
					<table height="100" width="100%">
						<col valign="top">
						<tr>
							<td>
								<table height="30">
									<col width="60">
									<col width="100">
									<col width="3">

									<col width="10">
									<col width="30">
									<col width="10">
									<col width="30">

									<col width="5">
									<col width="52">
									<col width="1">

									<col width="60">
									<col width="50">
									<col width="480">

									<tr>
										<td class="stab">Wms Wh</td>
										<td align="left">
											<ezf:select name="whCd_02" ezfName="whCd_02" ezfBlank="1" ezfCodeName="whCd_01" ezfDispName="xxEdtCdNm_01" otherAttr=" style=\"width:120px\""/>
										</td>
										<td></td>

										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OnClick_RtlWh" >Wh</ezf:anchor></td>
										<td><ezf:inputText name="rtlWhCd_01" ezfName="rtlWhCd_01" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="OnClick_GetRtlWhNm" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlWhNm_01" ezfName="rtlWhNm_01" otherAttr=" size=\"15\" ezftoupper=\"\""/></td>

										<td class="stab" align="right">SO#</td>
										<td align="left"><ezf:inputText name="wmsSoId_01" ezfName="wmsSoId_01" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td></td>

										<td class="stab" align="right">Cust Ord#</td>
										<td align="left"><ezf:inputText name="custOrdNum_01" ezfName="custOrdNum_01" otherAttr=" size=\"12\" maxlength=\"35\""/></td>
										<td>
											<table>
												<col width="52">
												<col width="140">
												<col width="121">
												<col width="10" align="center">
												<col width="121">
												
												<tr>
													<td class="stab" align="right">Date</td>
													<td>
														<ezf:select name="dateEntryL14If_02" ezfName="dateEntryL14If_02" ezfCodeName="dateEntryL14If_01" ezfDispName="xxScrItem14Txt_01" />
													</td>
													<td>
														<ezf:inputText name="xxSoSrchFromDt_01" ezfName="xxSoSrchFromDt_01" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxSoSrchFromDt_01', 4 );">
													</td>
													<td class="stab">-</td>
													<td>
														<ezf:inputText name="xxSoSrchThruDt_01" ezfName="xxSoSrchThruDt_01" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxSoSrchThruDt_01', 4 );">
													</td>
													<td></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>

								<table height="30" border="0">
									<col width="60">
									<col width="100">
									<col width="5">

									<col width="80" align="left">
									<col width="100">
									<col width="5">

									<col width="60" align="right">
									<col width="104">
									<col width="5">

									<col width="50" align="right">
									<col width="62">
									<col width="5">

									<col width="50" align="right">
									<col width="62">
									<col width="5">

									<col width="60" align="right">
									<col width="160">
									<col width="5">

									<tr>
										<td class="stab">Owner</td>
										<td align="left">
											<ezf:select name="invtyOwnrCd_01" ezfName="invtyOwnrCd_01" ezfBlank="1" ezfCodeName="invtyOwnrCd_LC" ezfDispName="invtyOwnrDescTxt_LD" otherAttr=" style=\"width:100px\""/>
										</td>
										<td></td>
										<td class="stab" align="right">Order Type</td>
										<td align="left">
											<ezf:select name="sceOrdTpCd_02" ezfName="sceOrdTpCd_02" ezfBlank="1" ezfCodeName="sceOrdTpCd_01" ezfDispName="xxEdtCdNm_02" otherAttr=" style=\"width:200px\""/>
										</td>
										<td></td>
										<td class="stab">Freight</td>
										<td align="left">
											<ezf:select name="wmsFrtOutCd_02" ezfName="wmsFrtOutCd_02" ezfBlank="1" ezfCodeName="wmsFrtOutCd_01" ezfDispName="xxEdtCdNm_03" otherAttr=" style=\"width:150px\""/>
										</td>
										<td></td>

										<td class="stab">Ship To</td>
										<td><ezf:inputText name="shipToCustCd_01" ezfName="shipToCustCd_01" otherAttr=" size=\"7\" maxlength=\"7\" ezftoupper=\"\""/></td>
										<td></td>

										<td class="stab">Bill To</td>
										<td><ezf:inputText name="billToCustCd_01" ezfName="billToCustCd_01" otherAttr=" size=\"7\" maxlength=\"7\" ezftoupper=\"\""/></td>
										<td></td>

										<td class="stab">Trx</td>
										<td align="left">
											<ezf:select name="wmsTrxCd_02" ezfName="wmsTrxCd_02" ezfBlank="1" ezfCodeName="wmsTrxCd_01" ezfDispName="xxEdtCdNm_04" otherAttr=" style=\"width:200px\""/>
										</td>
										<td></td>
									</tr>
								</table>

								<table height="30">
									<col width="48" align="left">
									<col width="104">
									<col width="5">
								
									<col width="48" align="right">
									<col width="96">
									<col width="5">

									<col width="48" align="right">
									<col width="62">
									<col width="5">

									<col width="32" align="right">
									<col width="70">
									<col width="5">

									<col width="32" align="right">
									<col width="62">
									<col width="5">

									<col width="24" align="right">
									<col width="32">
									<col width="5">

									<col width="53" align="right">
									<col width="64">
									<col width="5">

									<tr>

										<td class="stab">Item</td>
										<td><ezf:inputText name="wmsMdseCd_01" ezfName="wmsMdseCd_01" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td></td>

										<td class="stab">Ship Via</td>
										<td align="left">
											<ezf:select name="wmsShipViaTpCd_02" ezfName="wmsShipViaTpCd_02" ezfBlank="1" ezfCodeName="wmsShipViaTpCd_01" ezfDispName="xxEdtCdNm_05" />
										</td>
										<td></td>

										<td class="stab">Shipment#</td>
										<td><ezf:inputText name="wmsShipId_01" ezfName="wmsShipId_01" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
										<td></td>

										<td class="stab">BOL#</td>
										<td><ezf:inputText name="bolNum_01" ezfName="bolNum_01" otherAttr=" size=\"17\" maxlength=\"17\" ezftoupper=\"\""/></td>
										<td></td>

										<td class="stab">Wave#</td>
										<td><ezf:inputText name="wmsWaveId_01" ezfName="wmsWaveId_01" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
										<td></td>

										<td><ezf:inputCheckBox name="xxChkBox_01" ezfName="xxChkBox_01" value="Y" otherAttr=" id=\"xxChkBox_01\""/></td>
										<td class="stab"><label for="OrderedCheck">Closed</labe></td>
										<td></td>

										<td><ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn6" /></td>

									</tr>
								</table>
							</td>
						</tr>
					</table>

					<hr>

					<%-- ######################################## DETAIL ######################################## --%>
					<table width="1126">
						<col valign="top">
						<tr>
							<td>
								<%-- ###TAB - HEAD --%>
								<div class="pTab_HEAD">
									<ul>
										<li id="tbSOList" title="SO List" class="pTab_ON">
											<ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="OnClick_SOListTab" >SO List</ezf:anchor>
										</li>
										<li id="tbSOStatus" title="SO Status" class="pTab_OFF">
											<ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="OnClick_SOStatusTab" >SO Status</ezf:anchor>
										</li>
										<li id="tbDnldEdt" title="Download Edit" class="pTab_OFF">
											<ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="OnClick_DownloadEditTab" >Download Edit</ezf:anchor>
										</li>
										<li id="tbUpldEdt" title="Upload Edit" class="pTab_OFF">
											<ezf:anchor name="" ezfName="xxTabProt_04" ezfEmulateBtn="1" ezfGuard="OnClick_UploadEditTab" >Upload Edit</ezf:anchor>
										</li>
									</ul>
								</div>
								<%-- ######################################## SO List TAB ######################################## --%>
								<c:choose>
								<c:when test="${pageScope._ezddatabean.xxDplyTab=='SOList'}">
									<script type="text/javascript">
										document.getElementById("tbSOList").className="pTab_ON";
										document.getElementById("tbSOStatus").className="pTab_OFF";
										document.getElementById("tbDnldEdt").className="pTab_OFF";
										document.getElementById("tbUpldEdt").className="pTab_OFF";
									</script>
                                                                        <%-- ###TAB - BODY --%>
									<div class="pTab_BODY_In" style="height: 400px">
										<table height="340" width="100%">
											<tr valign="top">
												<td>
													<table border="0" cellpadding="1" cellspacing="0" width="100%">
														<col width="750">
														<col width="54"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="10">
														<col>
														<col width="1">
														<col>
														<col width="17">
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
															<td align="left" valign="top">
																<table border="1" cellpadding="1" cellspacing="0" width="246">
																	<col width="30">
																	<col width="140" align="center">
																	<col width="64" align="center">
																	<tr height="37">
																		<td class="pClothBs"></td>
																		<td class="pClothBs">Print Date</td>
																		<td class="pClothBs">SO#</td>
																	</tr>
																</table>
																<%-- id:leftTBL --%>
																<div id="leftTBL" style="overflow-y:hidden; height:310; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));">
																	<table border="1" cellpadding="1" cellspacing="0" width="246" id="A_left">
																		<col width="30" align="center">
																		<col width="140" align="center">
																		<col width="64" align="left">
																		<ezf:row ezfHyo="A">
																			<tr height="28">
																				<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"chkBox#{EZF_ROW_NUMBER}\""/></td>
																				<td><ezf:label name="xxDtTm_A1" ezfName="xxDtTm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsSoId_A1" ezfName="wmsSoId_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																			</tr>
																		</ezf:row>
																	</table>
																</div>
															</td>
															<td valign="top">
																<%-- id:topTBL --%>
																<div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:847;" onScroll="synchroScrollLeft(this.id, new Array('rightTBL'));">
																	<table border="1" cellpadding="0" cellspacing="0" width="4212">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="100" align="center">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="140" align="center">
																		<col width="140" align="center">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="250" align="center">
																		<col width="250" align="center">
																		<col width="250" align="center">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="250" align="center">
																		<col width="90" align="center">

																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="200" align="center">
																		<col width="100" align="center">

																		<col width="90" align="center">
																		<tr height="37">
																			<td class="pClothBs">Consolidated<br>SO#</td>
																			<td class="pClothBs">Consolidated</td>
																			<td class="pClothBs">Host#</td>
																			<td class="pClothBs">PKT#</td>
																			<td class="pClothBs">Order Type</td>
																			<td class="pClothBs">Transaction<br>Code</td>
																			<td class="pClothBs">ASN</td>
																			<td class="pClothBs">Ship via</td>
																			<td class="pClothBs">Freight Code</td>
																			<td class="pClothBs">RSD</td>
																			<td class="pClothBs">RDD</td>
																			<td class="pClothBs">WMS Status</td>
																			<td class="pClothBs">PSD | PDD</td>
																			<td class="pClothBs">Total<br>Weight</td>
																			<td class="pClothBs">Cust Order#</td>
																			<td class="pClothBs">Charge To</td>
																			<td class="pClothBs">Ship To</td>
																			<td class="pClothBs">Name</td>
																			<td class="pClothBs">Address</td>
																			<td class="pClothBs">City</td>
																			<td class="pClothBs">State</td>
																			<td class="pClothBs">Zip</td>
																			<td class="pClothBs">Total<br>Price</td>
																			<td class="pClothBs">Comments</td>
																			<td class="pClothBs">Source</td>

																			<td class="pClothBs">Wh</td>
																			<td class="pClothBs">Owner</td>
																			<td class="pClothBs">Scheduled<br>Delivery</td>
																			<td class="pClothBs">Carrier Code</td>
																			<td class="pClothBs">Shipping Service Level</td>
																			<td class="pClothBs">Rtn Item Included</td>
																			<td class="pClothBs">Configuration ID</td>
																			<td class="pClothBs">Assembly Required</td>

																			<td class="pClothBs">MW Status</td>
																		</tr>
																	</table>
																</div>
																<%-- id:rightTBL --%>
																<div id="rightTBL" style="overflow-y:scroll; height:327; overflow-x:scroll; width:864;" onScroll="synchroScrollTop(this.id, new Array('leftTBL')); onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
																	<table border="1" cellpadding="0" cellspacing="0" width="4212" id="A_right">
																		<col width="90" align="left">
																		<col width="90" align="center">
																		<col width="90" align="left">
																		<col width="100" align="left">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="140" align="center">
																		<col width="140" align="center">
																		<col width="90" align="center">
																		<col width="90" align="left">
																		<col width="90" align="left">
																		<col width="100" align="left">
																		<col width="100" align="left">
																		<col width="100" align="left">
																		<col width="250" align="left">
																		<col width="250" align="left">
																		<col width="250" align="left">
																		<col width="90" align="center">
																		<col width="90" align="center">
																		<col width="90" align="left">
																		<col width="250" align="left">
																		<col width="90" align="left">

																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="200" align="center">
																		<col width="100" align="center">

																		<col width="90" align="center">

																		<ezf:row ezfHyo="A">
																			<tr height="28">
																				<td><ezf:label name="conslSoId_A1" ezfName="conslSoId_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsConslFlg_A1" ezfName="wmsConslFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsOrdNum_A1" ezfName="wmsOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td align="center"><ezf:inputText name="altDocNum_A1" ezfName="altDocNum_A1" value="SHS84207" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																				<td><ezf:label name="sceOrdTpCd_A1" ezfName="sceOrdTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsTrxCd_A1" ezfName="wmsTrxCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="indAsnFlg_A1" ezfName="indAsnFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="soShipViaCd_A1" ezfName="soShipViaCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="frtOutCd_A1" ezfName="frtOutCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxDtTm_A2" ezfName="xxDtTm_A2" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxDtTm_A3" ezfName="xxDtTm_A3" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsStsCd_A1" ezfName="wmsStsCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="fillerL14If_A1" ezfName="fillerL14If_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td align="right"><ezf:label name="totWtAmtNum_A1" ezfName="totWtAmtNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td align="center"><ezf:inputText name="custOrdNum_A1" ezfName="custOrdNum_A1" value="51390552" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																				<td align="center"><ezf:inputText name="chrgToCustCd_A1" ezfName="chrgToCustCd_A1" value="O003" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																				<td align="center"><ezf:inputText name="shipToCustCd_A1" ezfName="shipToCustCd_A1" value="51390552" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																				<td align="center"><ezf:inputText name="wmsShipToNm_A1" ezfName="wmsShipToNm_A1" value="999999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"33\""/></td>
																				<td align="center"><ezf:inputText name="firstLineAddr_A1" ezfName="firstLineAddr_A1" value="1165RANDOLPHST" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"33\""/></td>
																				<td align="center"><ezf:inputText name="ctyAddr_A1" ezfName="ctyAddr_A1" value="CANTON" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"33\""/></td>
																				<td><ezf:label name="stCd_A1" ezfName="stCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="postCd_A1" ezfName="postCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td align="right"><ezf:label name="totShipPrcAmtNum_A1" ezfName="totShipPrcAmtNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td align="center"><ezf:inputText name="xxMsgTxt_A1" ezfName="xxMsgTxt_A1" value="CANTON" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"33\""/></td>
																				<td><ezf:label name="wmsResrcTxt_A1" ezfName="wmsResrcTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="rtlWhCd_A1" ezfName="rtlWhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="invtyOwnrCd_A1" ezfName="invtyOwnrCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="schdDelyDt_A1" ezfName="schdDelyDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="carrCd_A1" ezfName="carrCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="shpgSvcLvlCd_A1" ezfName="shpgSvcLvlCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="rtrnItemInclFlg_A1" ezfName="rtrnItemInclFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="asmReqFlg_A1" ezfName="asmReqFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxDtlCd_A1" ezfName="xxDtlCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
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
								<%-- ######################################## SO Status TAB ######################################## --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab=='SOStatus'}">
									<script type="text/javascript">
										document.getElementById("tbSOList").className="pTab_OFF";
										document.getElementById("tbSOStatus").className="pTab_ON";
										document.getElementById("tbDnldEdt").className="pTab_OFF";
										document.getElementById("tbUpldEdt").className="pTab_OFF";
									</script>
									<div class="pTab_BODY_In">
										<div style="overflow-y: scroll; height: 400px">
											<table height="530" width="100%">
												<tr valign="top">
													<td>
														<table border="0" cellpadding="1" cellspacing="0" >
															<tr>
																<td valign="top">
																	<table border="0" width="100%">
																		<col width="1093">
																		<tr>
																			<td class="stab">Order Header</td>
																			<td></td>
																		</tr>
																	</table>
																	<%-- ### MEISAI - HEAD --%>
																	<div id="topTBL" style="overflow-x:scroll; width:1093;">
																		<table border="1" cellpadding="0" cellspacing="0"width="4198" >
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="140">
																			<col align="center" width="140">
																			<col align="center" width="140">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="140">
																			<col align="center" width="140">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">

																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="200" align="center">
																			<col width="100" align="center">

																			<tr>
																				<td class="pClothBs">SO#</td>
																				<td class="pClothBs">Order<br>Type</td>
																				<td class="pClothBs">Host#</td>
																				<td class="pClothBs">PKT#</td>
																				<td class="pClothBs">Print Date</td>
																				<td class="pClothBs">RDD</td>
																				<td class="pClothBs">RSD</td>
																				<td class="pClothBs">Charge To</td>
																				<td class="pClothBs">Ship To</td>
																				<td class="pClothBs">Cust Order#</td>
																				<td class="pClothBs">Freight Code</td>
																				<td class="pClothBs">Ship Via</td>
																				<td class="pClothBs">Transaction<br>Code</td>
																				<td class="pClothBs">Department<br>Code</td>
																				<td class="pClothBs">ASN</td>
																				<td class="pClothBs">SCC</td>
																				<td class="pClothBs">UCC</td>
																				<td class="pClothBs">Consolidated</td>
																				<td class="pClothBs">Consolidated<br>SO#</td>
																				<td class="pClothBs">Total<br>Price</td>
																				<td class="pClothBs">Source</td>
																				<td class="pClothBs">Ship<br>Date</td>
																				<td class="pClothBs">Cancel<br>Date</td>
																				<td class="pClothBs">Mix<br>Pack</td>
																				<td class="pClothBs">WMS<br>Ship Via</td>
																				<td class="pClothBs">WMS<br>Priority</td>
																				<td class="pClothBs">Sign?</td>
																				<td class="pClothBs">Return Label</td>

																				<td class="pClothBs">Wh</td>
																				<td class="pClothBs">Owner</td>
																				<td class="pClothBs">Scheduled<br>Delivery</td>
																				<td class="pClothBs">Carrier Code</td>
																				<td class="pClothBs">Shipping Service Level</td>
																				<td class="pClothBs">Rtn Item Included</td>
																				<td class="pClothBs">Configuration ID</td>
																				<td class="pClothBs">Assembly Required</td>

																			</tr>
																		</table>
																		<%-- ### MEISAI - DETAIL --%>
																		<div id="bottomTBL" style="overflow-x:none; width:1093;" >
																			<table border="1" cellpadding="0" cellspacing="0"width="4198" > 
																				<col width="100" align="left">
																				<col width="100" align="left">
																				<col width="100" align="left">
																				<col width="100" align="left">
																				<col width="140" align="center">
																				<col width="140" align="center">
																				<col width="140" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="right">
																				<col width="100" align="center">
																				<col width="140" align="center">
																				<col width="140" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">

																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="200" align="center">
																				<col width="100" align="center">

																				<ezf:row ezfHyo="B">
																					<tr>
																						<td><ezf:label name="wmsSoId_B1" ezfName="wmsSoId_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="fill40Txt_B1" ezfName="fill40Txt_B1" value="A-Regular" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:inputText name="wmsOrdNum_B1" ezfName="wmsOrdNum_B1" value="6834" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td align="center"><ezf:inputText name="altDocNum_B1" ezfName="altDocNum_B1" value="SHW43339" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="xxDtTm_B1" ezfName="xxDtTm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="xxDtTm_B2" ezfName="xxDtTm_B2" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="xxDtTm_B3" ezfName="xxDtTm_B3" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="chrgToCustCd_B1" ezfName="chrgToCustCd_B1" value="D114" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:inputText name="shipToCustCd_B1" ezfName="shipToCustCd_B1" value="O3CE52" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:inputText name="custOrdNum_B1" ezfName="custOrdNum_B1" value="2370860" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="frtOutCd_B1" ezfName="frtOutCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="soShipViaCd_B1" ezfName="soShipViaCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="ediTrnspTpCd_B1" ezfName="ediTrnspTpCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsDeptCd_B1" ezfName="wmsDeptCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="indAsnFlg_B1" ezfName="indAsnFlg_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="indScc14Flg_B1" ezfName="indScc14Flg_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="indUccFlg_B1" ezfName="indUccFlg_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsConslFlg_B1" ezfName="wmsConslFlg_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="conslSoId_B1" ezfName="conslSoId_B1" value="aa" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="totShipPrcAmtNum_B1" ezfName="totShipPrcAmtNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="wmsResrcTxt_B1" ezfName="wmsResrcTxt_B1" value="SHW43339" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="xxDtTm_B4" ezfName="xxDtTm_B4" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="xxDtTm_B5" ezfName="xxDtTm_B5" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="mixPltPltNoteTxt_B1" ezfName="mixPltPltNoteTxt_B1" value="1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:inputText name="asgShipViaCd_B1" ezfName="asgShipViaCd_B1" value="1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="asgPrtyCd_B1" ezfName="asgPrtyCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="indSgnReqFlg_B1" ezfName="indSgnReqFlg_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="rtrnLbCd_B1" ezfName="rtrnLbCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="rtlWhCd_B1" ezfName="rtlWhCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="invtyOwnrCd_B1" ezfName="invtyOwnrCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="schdDelyDt_B1" ezfName="schdDelyDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="carrCd_B1" ezfName="carrCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="shpgSvcLvlCd_B1" ezfName="shpgSvcLvlCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="rtrnItemInclFlg_B1" ezfName="rtrnItemInclFlg_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="svcConfigMstrPk_B1" ezfName="svcConfigMstrPk_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td><ezf:label name="asmReqFlg_B1" ezfName="asmReqFlg_B1" ezfHyo="B" ezfArrayNo="0" /></td>
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
																		<col width="1093">
																		<tr height="8px">
																			<td></td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td>
																	<table border="0" width="100%">
																		<col width="1108">
																		<tr>
																			<td class="stab">Address</td>
																			<td></td>
																		</tr>
																	</table>

																	<%-- ### MEISAI - HEAD --%>
																	<div id="topTBL" style="overflow-x:none; width:1108;">
																		<table border="1" cellpadding="0" cellspacing="0"width="1096" >
																			<col align="center" width="245">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="245">
																			<col align="center" width="160">
																			<col align="center" width="59">
																			<col align="center" width="59">
																			<col align="center" width="96">
																			<tr>
																				<td class="pClothBs">Name</td>
																				<td class="pClothBs">Name2</td>
																				<td class="pClothBs">Contact</td>
																				<td class="pClothBs">Address</td>
																				<td class="pClothBs">City</td>
																				<td class="pClothBs">State</td>
																				<td class="pClothBs">Zip</td>
																				<td class="pClothBs">Contact#</td>
																			</tr>
																		</table>
																	
																		<%-- ### MEISAI - DETAIL --%>
																		<div id="bottomTBL" style="overflow-x:none; width:1093;" >
																			<table border="1" cellpadding="0" cellspacing="0"width="1096" > 
																				<col width="245" align="center">
																				<col width="96"  align="center">
																				<col width="96"  align="center">
																				<col width="245" align="center">
																				<col width="160" align="center">
																				<col width="59"  align="center">
																				<col width="59"  align="center">
																				<col width="96"  align="center">
	                                                                                                                                                        <ezf:row ezfHyo="C">
																					<tr>
																						<td><ezf:inputText name="wmsShipToNm_C1" ezfName="wmsShipToNm_C1" value="JAPS-OLSON CO - PO #10363 X" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"33\""/></td>
																						<td><ezf:inputText name="wmsShipToNm_C2" ezfName="wmsShipToNm_C2" value="aa" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:inputText name="wmsShipToCtacNm_C1" ezfName="wmsShipToCtacNm_C1" value="0000503636" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:inputText name="fill256Txt_C1" ezfName="fill256Txt_C1" value="7500 EXCELSIOR BLVD,,," ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"33\""/></td>
																						<td><ezf:inputText name="ctyAddr_C1" ezfName="ctyAddr_C1" value="SAINT LOUIS PARK" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
																						<td><ezf:label name="stCd_C1" ezfName="stCd_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																						<td><ezf:label name="postCd_C1" ezfName="postCd_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																						<td><ezf:label name="shipToCtacNum_C1" ezfName="shipToCtacNum_C1" ezfHyo="C" ezfArrayNo="0" /></td>
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
																		<col width="250">
																		<tr height="8px">
																			<td></td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td>
																	<%-- ### MEISAI - HEAD --%>
																	<div id="topTBL" style="overflow-x:none; width:900;">
																		<table border="1" cellpadding="0" cellspacing="0"width="900" >
																			<col align="center" width="240">
																			<tr>
																				<td class="pClothBs">Shipping Instructions</td>
																			</tr>
																		</table>
																		<%-- ### MEISAI - DETAIL --%>
																		<div id="bottomTBL" style="overflow-x:none; width:900;" >
																			<table border="1" cellpadding="0" cellspacing="0"width="900" > 
																				<col width="240">
																				<ezf:row ezfHyo="D">
																					<tr>
																						<td align="center"><ezf:inputText name="xxMsgTxt_D1" ezfName="xxMsgTxt_D1" value="CBS P/O : 0000503636" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"127\""/></td>
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
																	<div id="topTBL" style="overflow-x:scroll; width:1093;">
																		<table border="1" cellpadding="0" cellspacing="0"width="3200" >
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">

																			<col width="100" align="center">
																			<col width="200" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="200" align="center">

																			<tr>
																				<td class="pClothBs">Line#</td>
																				<td class="pClothBs">Mdse<br>Code</td>
																				<td class="pClothBs">Stock Status</td>
																				<td class="pClothBs">Stock Status To</td>
																				<td class="pClothBs">Mdse Description</td>
																				<td class="pClothBs">Shipped<br>Quantity</td>
																				<td class="pClothBs">Indicator<br>Serial Id</td>
																				<td class="pClothBs">Set Mdse</td>
																				<td class="pClothBs">Shipped Set<br>Quantity</td>
																				<td class="pClothBs">Cust<br>Mdse Code</td>
																				<td class="pClothBs">Original<br>SO#</td>
																				<td class="pClothBs">Original<br>Line#</td>
																				<td class="pClothBs">Total<br>Weight</td>
																				<td class="pClothBs">Total<br>Volume</td>
																				<td class="pClothBs">CA Quantity</td>
																				<td class="pClothBs">PL Quantity</td>
																				<td class="pClothBs">Est.<br>#Cases</td>
																				<td class="pClothBs">Est.<br>#Pallets</td>
																				<td class="pClothBs">UPC</td>
																				<td class="pClothBs">NMFC</td>
																				<td class="pClothBs">Est.Total<br>#Cases</td>
																				<td class="pClothBs">Est.Total<br>#Pallets</td>

																				<td class="pClothBs">Package Code</td>
																				<td class="pClothBs">Pick Confirmation ID</td>
																				<td class="pClothBs">Back Order</td>
																				<td class="pClothBs">Back Order Impact Type</td>
																				<td class="pClothBs">Remove Config</td>
																				<td class="pClothBs">Config Flag</td>
																				<td class="pClothBs">Serial#</td>

																			</tr>
																		</table>
																		<%-- ### MEISAI - DETAIL --%>
																		<div id="bottomTBL" style="overflow-x:none; width:1093;" >
																			<table border="1" cellpadding="0" cellspacing="0"width="3200" > 
																				<col width="96" align="left">
																				<col width="96" align="center">
																				<col width="96" align="center">
																				<col width="96" align="center">
																				<col width="96" align="center">
																				<col width="96" align="right">
																				<col width="96" align="center">
																				<col width="96" align="center">
																				<col width="96" align="right">
																				<col width="96" align="center">
																				<col width="96" align="left">
																				<col width="96" align="left">
																				<col width="96" align="right">
																				<col width="96" align="right">
																				<col width="96" align="right">
																				<col width="96" align="right">
																				<col width="96" align="right">
																				<col width="96" align="right">
																				<col width="96" align="center">
																				<col width="96" align="center">
																				<col width="96" align="right">
																				<col width="96" align="right">

																			<col width="100" align="center">
																			<col width="200" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="200" align="center">

																				<ezf:row ezfHyo="E">
																					<tr>
																						<td><ezf:label name="wmsLineNum_E1" ezfName="wmsLineNum_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td align="center"><ezf:inputText name="wmsMdseCd_E1" ezfName="wmsMdseCd_E1" value="5816B001AA" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="s80StkStsCd_E1" ezfName="s80StkStsCd_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="s80StkStsCdToCd_E1" ezfName="s80StkStsCdToCd_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td align="center"><ezf:inputText name="mdseDescShortTxt_E2" ezfName="mdseDescShortTxt_E2" value="SCANNER EXPRESS" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="wmsShipQty_E1" ezfName="wmsShipQty_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="indSerId_E1" ezfName="indSerId_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td align="center"><ezf:inputText name="mdseCdSetCd_E1" ezfName="mdseCdSetCd_E1" value="XXXX" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="shipSetQty_E1" ezfName="shipSetQty_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td align="center"><ezf:inputText name="custMdseCd_E1" ezfName="custMdseCd_E1" value="XXXX" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="origSoId_E1" ezfName="origSoId_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="origLineNum_E1" ezfName="origLineNum_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="totWtAmtNum_E1" ezfName="totWtAmtNum_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="totVolAmtNum_E1" ezfName="totVolAmtNum_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="unitCrtnQty_E1" ezfName="unitCrtnQty_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="unitPltQty_E1" ezfName="unitPltQty_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="estCseAmtNum_E1" ezfName="estCseAmtNum_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="estPltAmtNum_E1" ezfName="estPltAmtNum_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td align="center"><ezf:inputText name="wmsUpcCd_E1" ezfName="wmsUpcCd_E1" value="XXXX" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="fill20Txt_E1" ezfName="fill20Txt_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="estCseAmtNum_E2" ezfName="estCseAmtNum_E2" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="estPltAmtNum_E2" ezfName="estPltAmtNum_E2" ezfHyo="E" ezfArrayNo="0" /></td>
																						<!-- <td><label ezfout ezfhyo="E" name="fill40Txt_E1" ezfname="fill40Txt_E1">0.1</label></td>-->
																						<td><ezf:inputText name="fill40Txt_E1" ezfName="fill40Txt_E1" value="XXXX" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"6\""/></td>
																						<td><ezf:label name="svcConfigMstrPk_E1" ezfName="svcConfigMstrPk_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="backOrdFlg_E1" ezfName="backOrdFlg_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="backOrdImpctTpCd_E1" ezfName="backOrdImpctTpCd_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="rmvConfigFlg_E1" ezfName="rmvConfigFlg_E1" ezfHyo="E" ezfArrayNo="0" /></td>
																						<td><ezf:label name="indConfigFlg_E1" ezfName="indConfigFlg_E1" ezfHyo="E" ezfArrayNo="0" /></td>
                                            <td><ezf:label name="serNum_E1" ezfName="serNum_E1" ezfHyo="E" ezfArrayNo="0" /></td>
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
																			<td class="stab">Upload Transaction</td>
																			<td></td>
																		</tr>
																	</table>
																	<%-- ### MEISAI - HEAD --%>
																	<div id="topTBL" style="overflow-x:scroll; width:1093;">
																		<table border="1" cellpadding="0" cellspacing="0"width="4002" >
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="140">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="150">
																			<col align="center" width="150">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="96">
																			<col align="center" width="140">
																			<tr>
																				<td class="pClothBs">Record Id</td>
																				<td class="pClothBs">WH</td>
																				<td class="pClothBs">Line#<br>(OTBD)</td>
																				<td class="pClothBs">Line#<br>(INBD)</td>
																				<td class="pClothBs">Task</td>
																				<td class="pClothBs">Order<br>Status</td>
																				<td class="pClothBs">Mdse Code</td>
																				<td class="pClothBs">Stock Status</td>
																				<td class="pClothBs">Quantity</td>
																				<td class="pClothBs">Operator ID</td>
																				<td class="pClothBs">User ID1</td>
																				<td class="pClothBs">Otbd<br>Ord Tp</td>
																				<td class="pClothBs">Otbd Ord Num</td>
																				<td class="pClothBs">Inbd<br>Ord Tp</td>
																				<td class="pClothBs">Inbd Ord Num</td>
																				<td class="pClothBs">Transaction<br>Date</td>
																				<td class="pClothBs">Wave#</td>
																				<td class="pClothBs">Total<br>Weight</td>
																				<td class="pClothBs">Carrier<br>Code</td>
																				<td class="pClothBs">Trailer ID</td>
																				<td class="pClothBs">Pro Bill</td>
																				<td class="pClothBs">BOL#</td>
																				<td class="pClothBs">Group1</td>
																				<td class="pClothBs">Group3</td>
																				<td class="pClothBs">Shipment#</td>
																				<td class="pClothBs">Freight-Terms</td>
																				<td class="pClothBs">Lot#</td>
																				<td class="pClothBs">Tag</td>
																				<td class="pClothBs">Modified By</td>
																				<td class="pClothBs">Container</td>
																				<td class="pClothBs">Most Outer<br>Container</td>
																				<td class="pClothBs">Freight<br>Charge</td>
																				<td class="pClothBs">Serial</td>
																				<td class="pClothBs">Shippable<br>Unit Weight</td>
																				<td class="pClothBs">Tms Freight<br>Charge</td>
																				<td class="pClothBs">Tms Freight<br>Cost</td>
																				<td class="pClothBs">CreatedOn</td>
																			</tr>
																		</table>

																		<%-- ### MEISAI - DETAIL --%>
																		<div id="bottomTBL" style="overflow-x:none; width:1093;" >
																			<table border="1" cellpadding="0" cellspacing="0"width="4002" > 
																				<col width="96"  align="center">
																				<col width="96"  align="center">
																				<col width="96"  align="left">
																				<col width="96"  align="left">
																				<col width="96"  align="center">
																				<col width="96"  align="center">
																				<col width="96"  align="left">
																				<col width="96"  align="center">
																				<col width="96"  align="right">
																				<col width="96"  align="center">
																				<col width="96"  align="left">
																				<col width="96"  align="center">
																				<col width="96"  align="center">
																				<col width="96"  align="center">
																				<col width="96"  align="center">
																				<col width="140" align="center">
																				<col width="96"  align="left">
																				<col width="96"  align="right">
																				<col width="96"  align="left">
																				<col width="96"  align="left">
																				<col width="96"  align="left">
																				<col width="96"  align="left">
																				<col width="96"  align="left">
																				<col width="96"  align="left">
																				<col width="96"  align="left">
																				<col width="96"  align="center">
																				<col width="96"  align="left">
																				<col width="96"  align="left">
																				<col width="96"  align="left">
																				<col width="150"  align="left">
																				<col width="150"  align="left">
																				<col width="96"  align="right">
																				<col width="96"  align="left">
																				<col width="96"  align="right">
																				<col width="96"  align="right">
																				<col width="96"  align="right">
																				<col width="140" align="center">
																				<ezf:row ezfHyo="F">
																					<tr>
																						<td><ezf:inputText name="wmsRecId_F1" ezfName="wmsRecId_F1" value="3578794" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="whCd_F1" ezfName="whCd_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="otbdOrdLineNum_F1" ezfName="otbdOrdLineNum_F1" value="aa" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:inputText name="inbdOrdLineNum_F1" ezfName="inbdOrdLineNum_F1" value="aa" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="wmsTaskCd_F1" ezfName="wmsTaskCd_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsOrdStsCd_F1" ezfName="wmsOrdStsCd_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="wmsMdseCd_F1" ezfName="wmsMdseCd_F1" value="aa" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="wmsStkStsCd_F1" ezfName="wmsStkStsCd_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsOrdQty_F1" ezfName="wmsOrdQty_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="wmsOpId_F1" ezfName="wmsOpId_F1" value="QE09541" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="usrId_F1" ezfName="usrId_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="otbdOrdTpCd_F1" ezfName="otbdOrdTpCd_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="otbdOrdNum_F1" ezfName="otbdOrdNum_F1" value="aa" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="inbdOrdTpCd_F1" ezfName="inbdOrdTpCd_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="inbdOrdNum_F1" ezfName="inbdOrdNum_F1" value="aa" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="xxDtTm_F1" ezfName="xxDtTm_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsWaveId_F1" ezfName="wmsWaveId_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsTotWt_F1" ezfName="wmsTotWt_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td align="center"><ezf:inputText name="wmsCarrCd_F1" ezfName="wmsCarrCd_F1" value="CALL" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="wmsTrailId_F1" ezfName="wmsTrailId_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="proNum_F1" ezfName="proNum_F1" value="aa" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="bolNum_F1" ezfName="bolNum_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsGrpId_F1" ezfName="wmsGrpId_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsGrpId_F2" ezfName="wmsGrpId_F2" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsShipId_F1" ezfName="wmsShipId_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsFrtTermCd_F1" ezfName="wmsFrtTermCd_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsLotNum_F1" ezfName="wmsLotNum_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsTagId_F1" ezfName="wmsTagId_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="modByTxt_F1" ezfName="modByTxt_F1" value="QE09541" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="wmsCntnrId_F1" ezfName="wmsCntnrId_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsOutCntnrNum_F1" ezfName="wmsOutCntnrNum_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsFrtChrgAmt_F1" ezfName="wmsFrtChrgAmt_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="serNum_F1" ezfName="serNum_F1" value="aa" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="shipUnitWt_F1" ezfName="shipUnitWt_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="tmsFrtChrgAmt_F1" ezfName="tmsFrtChrgAmt_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="tmsFrtChrgCostAmt_F1" ezfName="tmsFrtChrgCostAmt_F1" ezfHyo="F" ezfArrayNo="0" /></td>
																						<td><ezf:label name="xxDtTm_F3" ezfName="xxDtTm_F3" ezfHyo="F" ezfArrayNo="0" /></td>
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
																			<td class="stab">ASN Data</td>
																			<td></td>
																		</tr>
																	</table>
																	<%-- ### MEISAI - HEAD --%>
																	<div id="topTBL" style="overflow-x:scroll; width:1093;">
																		<table border="1" cellpadding="1" cellspacing="0"width="3510" >
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="140">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="160">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="150">
																			<col align="center" width="150">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="140">
																			<col align="center" width="100">
																			<col align="center" width="140">
																			<tr>
																				<td class="pClothBs">Asn Hdr ID</td>
																				<td class="pClothBs">BOL#</td>
																				<td class="pClothBs">Master<br>BOL#</td>
																				<td class="pClothBs">WH</td>
																				<td class="pClothBs">SO#</td>
																				<td class="pClothBs">LoadingID</td>
																				<td class="pClothBs">Shipment#</td>
																				<td class="pClothBs">Ship Date</td>
																				<td class="pClothBs">Total Weight</td>
																				<td class="pClothBs">Freight<br>Charge</td>
																				<td class="pClothBs">Carrier SCAC</td>
																				<td class="pClothBs">Carrier Service</td>
																				<td class="pClothBs">Carrier Pro ID</td>
																				<td class="pClothBs">Alt Doc Num</td>
																				<td class="pClothBs">Package Num</td>
																				<td class="pClothBs">Tracking Num</td>
																				<td class="pClothBs">UCC128</td>
																				<td class="pClothBs">Parent<br>UCC128</td>
																				<td class="pClothBs">UPC</td>
																				<td class="pClothBs">SCC14</td>
																				<td class="pClothBs">Mdse Code</td>
																				<td class="pClothBs">Stock<br>Status</td>
																				<td class="pClothBs">UOM</td>
																				<td class="pClothBs">UOM<br>Quantity</td>
																				<td class="pClothBs">Quantity</td>
																				<td class="pClothBs">Line#</td>
																				<td class="pClothBs">Cust<br>Account Num</td>
																				<td class="pClothBs">ASN Customer</td>
																				<td class="pClothBs">CreateOn</td>
																				<td class="pClothBs">Processed Flag</td>
																				<td class="pClothBs">Processed Date</td>
																			</tr>
																		</table>
																		<%-- ### MEISAI - DETAIL --%>
																		<div id="bottomTBL" style="overflow-x:none; width:1093;" >
																			<table border="1" cellpadding="1" cellspacing="0"width="3510" > 
																				<col width="100">
																				<col width="100">
																				<col width="100">
																				<col width="100" align="center">
																				<col width="100">
																				<col width="100">
																				<col width="100">
																				<col width="140" align="center">
																				<col width="100" align="right">
																				<col width="100" align="right">
																				<col width="100" align="center">
																				<col width="160" align="center">
																				<col width="100">
																				<col width="100">
																				<col width="100">
																				<col width="100">
																				<col width="150">
																				<col width="150">
																				<col width="100">
																				<col width="100">
																				<col width="100">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="right">
																				<col width="100" align="right">
																				<col width="100">
																				<col width="100">
																				<col width="100" align="center">
																				<col width="140" align="center">
																				<col width="100" align="center">
																				<col width="140" align="center">
																				<ezf:row ezfHyo="G">
																					<tr>
																						<td><ezf:label name="wmsSoAsnHdrId_G1" ezfName="wmsSoAsnHdrId_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td align="center"><ezf:inputText name="wmsBolNum_G1" ezfName="wmsBolNum_G1" value="aa" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:inputText name="mstrBolNum_G1" ezfName="mstrBolNum_G1" value="aa" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="whCd_G1" ezfName="whCd_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsSoId_G1" ezfName="wmsSoId_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsLoadId_G1" ezfName="wmsLoadId_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsSoShipId_G1" ezfName="wmsSoShipId_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="xxDtTm_G1" ezfName="xxDtTm_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsTotWt_G1" ezfName="wmsTotWt_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="frtChrgCostAmt_G1" ezfName="frtChrgCostAmt_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="carrScacCd_G1" ezfName="carrScacCd_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="carrSvcCd_G1" ezfName="carrSvcCd_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td align="center"><ezf:inputText name="carrProId_G1" ezfName="carrProId_G1" value="aa" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td align="center"><ezf:inputText name="altDocNum_G1" ezfName="altDocNum_G1" value="aa" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="wmsPkgNum_G1" ezfName="wmsPkgNum_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="wmsTrkNum_G1" ezfName="wmsTrkNum_G1" value="aa" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="wmsUcc128Cd_G1" ezfName="wmsUcc128Cd_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="prntUcc128Cd_G1" ezfName="prntUcc128Cd_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsUpcCd_G1" ezfName="wmsUpcCd_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsScc14Cd_G1" ezfName="wmsScc14Cd_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="wmsMdseCd_G1" ezfName="wmsMdseCd_G1" value="aa" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																						<td><ezf:label name="wmsStkStsCd_G1" ezfName="wmsStkStsCd_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsUomCd_G1" ezfName="wmsUomCd_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsUomQty_G1" ezfName="wmsUomQty_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsOrdQty_G1" ezfName="wmsOrdQty_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsLineNum_G1" ezfName="wmsLineNum_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="custAcctNum_G1" ezfName="custAcctNum_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="asnCustFlg_G1" ezfName="asnCustFlg_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="xxDtTm_G2" ezfName="xxDtTm_G2" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsProcFlg_G1" ezfName="wmsProcFlg_G1" ezfHyo="G" ezfArrayNo="0" /></td>
																						<td><ezf:label name="xxDtTm_G3" ezfName="xxDtTm_G3" ezfHyo="G" ezfArrayNo="0" /></td>
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
																			<td class="stab">ASN Data Errors</td>
																			<td></td>
																		</tr>
																	</table>
																	<%-- ### ASN Data Error - HEAD --%>
																	<table border="1" cellpadding="0" cellspacing="0"width="848" >
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="240">
																		<col align="center" width="140">
																		<col align="center" width="140">
																		<tr>
																			<td class="pClothBs">Record ID</td>
																			<td class="pClothBs">Shipment#</td>
																			<td class="pClothBs">BOL#</td>
																			<td class="pClothBs">Comments</td>
																			<td class="pClothBs">CreatedOn</td>
																			<td class="pClothBs">Processed Date</td>
																		</tr>
																	</table>
																	<%-- ### ASN Data Error - DETAIL --%>
																	<table border="1" cellpadding="0" cellspacing="0"width="848" > 
																		<col width="96" align="left">
																		<col width="96" align="center">
																		<col width="96" align="center">
																		<col width="240" align="center">
																		<col width="140" align="center">
																		<col width="140" align="center">
                                                                                                                                                <ezf:row ezfHyo="H">
																			<tr>
																				<td><ezf:label name="wmsRecId_H1" ezfName="wmsRecId_H1" ezfHyo="H" ezfArrayNo="0" /></td>
																				<td align="center"><ezf:inputText name="wmsShipId_H1" ezfName="wmsShipId_H1" value="aa" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																				<td align="center"><ezf:inputText name="bolNum_H1" ezfName="bolNum_H1" value="aa" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																				<td align="center"><ezf:inputText name="xxLogDtlTxt_H1" ezfName="xxLogDtlTxt_H1" value="aa" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"32\""/></td>
																				<td><ezf:label name="xxDtTm_H1" ezfName="xxDtTm_H1" ezfHyo="H" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxDtTm_H2" ezfName="xxDtTm_H2" ezfHyo="H" ezfArrayNo="0" /></td>
																			</tr>
																		</ezf:row>
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
								<%-- ######################################## Download Edit TAB ######################################## --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab=='DnldEdt'}">
									<script type="text/javascript">
										document.getElementById("tbSOList").className="pTab_OFF";
										document.getElementById("tbSOStatus").className="pTab_OFF";
										document.getElementById("tbDnldEdt").className="pTab_ON";
										document.getElementById("tbUpldEdt").className="pTab_OFF";
									</script>
									<div class="pTab_BODY_In">
										<div style="overflow-y: scroll; height: 400px">
											<table height="375" width="100%">
												<tr valign="top">
													<td>
														<table border="0" cellpadding="1" cellspacing="0" >
															<tr>
																<td valign="top">
																	<table border="0" width="100%">
																		<col width="1093">
																		<tr>
																			<td class="stab">Order Header</td>
																			<td></td>
																		</tr>
																	</table>
																	<%-- ### MEISAI - HEAD --%>
																	<div id="topTBL" style="overflow-x:scroll; width:1093;">
																		<table border="1" cellpadding="0" cellspacing="0"width="4198" >
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="140">
																			<col align="center" width="140">
																			<col align="center" width="140">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="140">
																			<col align="center" width="140">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">

																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="200" align="center">
																			<col width="100" align="center">

																			<tr>
																				<td class="pClothBs">SO#</td>
																				<td class="pClothBs">Order<br>Type</td>
																				<td class="pClothBs">Host#</td>
																				<td class="pClothBs">PKT#</td>
																				<td class="pClothBs">Print<br>Date</td>
																				<td class="pClothBs">RDD</td>
																				<td class="pClothBs">RSD</td>
																				<td class="pClothBs">Charge To</td>
																				<td class="pClothBs">Ship To</td>
																				<td class="pClothBs">Cust<br>Order#</td>
																				<td class="pClothBs">Freight Code</td>
																				<td class="pClothBs">Ship Via</td>
																				<td class="pClothBs">Transaction<br>Code</td>
																				<td class="pClothBs">Department<br>Code</td>
																				<td class="pClothBs">ASN</td>
																				<td class="pClothBs">SCC</td>
																				<td class="pClothBs">UCC</td>
																				<td class="pClothBs">Consolidated</td>
																				<td class="pClothBs">Consolidated<br>SO#</td>
																				<td class="pClothBs">Total<br>Price</td>
																				<td class="pClothBs">Source</td>
																				<td class="pClothBs">Ship<br>Date</td>
																				<td class="pClothBs">Cancel<br>Date</td>
																				<td class="pClothBs">Mix<br>pack</td>
																				<td class="pClothBs">WMS<br>Ship Via</td>
																				<td class="pClothBs">WMS<br>Prty</td>
																				<td class="pClothBs">Sign?</td>
																				<td class="pClothBs">RtnLbl</td>

																				<td class="pClothBs">Wh</td>
																				<td class="pClothBs">Owner</td>
																				<td class="pClothBs">Scheduled<br>Delivery</td>
																				<td class="pClothBs">Carrier Code</td>
																				<td class="pClothBs">Shipping Service Level</td>
																				<td class="pClothBs">Rtn Item Included</td>
																				<td class="pClothBs">Configuration ID</td>
																				<td class="pClothBs">Assembly Required</td>

																			</tr>
																		</table>
																		<%-- ### MEISAI - DETAIL --%>
																		<div id="bottomTBL" style="overflow-x:none; width:1093;" >
																			<table border="1" cellpadding="0" cellspacing="0"width="4198" > 
																				<col width="100">
																				<col width="100">
																				<col width="100">
																				<col width="100">
																				<col width="140">
																				<col width="140">
																				<col width="140">
																				<col width="100">
																				<col width="100">
																				<col width="100">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100">
																				<col width="100" align="right">
																				<col width="100">
																				<col width="140" align="center">
																				<col width="140">
																				<col width="100">
																				<col width="100">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">

																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="200" align="center">
																			<col width="100" align="center">
																				<ezf:row ezfHyo="I">
																					<tr>
																						<td><ezf:label name="wmsSoId_I1" ezfName="wmsSoId_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="fill40Txt_I1" ezfName="fill40Txt_I1" value="A-Regular" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:inputText name="wmsOrdNum_I1" ezfName="wmsOrdNum_I1" value="6834" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:inputText name="altDocNum_I1" ezfName="altDocNum_I1" value="SHW43339" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="xxDtTm_I1" ezfName="xxDtTm_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="xxDtTm_I2" ezfName="xxDtTm_I2" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="xxDtTm_I3" ezfName="xxDtTm_I3" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="chrgToCustCd_I1" ezfName="chrgToCustCd_I1" value="D114" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:inputText name="shipToCustCd_I1" ezfName="shipToCustCd_I1" value="O3CE52" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:inputText name="custOrdNum_I1" ezfName="custOrdNum_I1" value="2370860" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="frtOutCd_I1" ezfName="frtOutCd_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="soShipViaCd_I1" ezfName="soShipViaCd_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="ediTrnspTpCd_I1" ezfName="ediTrnspTpCd_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsDeptCd_I1" ezfName="wmsDeptCd_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="indAsnFlg_I1" ezfName="indAsnFlg_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="indScc14Flg_I1" ezfName="indScc14Flg_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="indUccFlg_I1" ezfName="indUccFlg_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="wmsConslFlg_I1" ezfName="wmsConslFlg_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="conslSoId_I1" ezfName="conslSoId_I1" value="aa" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="totShipPrcAmtNum_I1" ezfName="totShipPrcAmtNum_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="wmsResrcTxt_I1" ezfName="wmsResrcTxt_I1" value="SHW43339" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="xxDtTm_I4" ezfName="xxDtTm_I4" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="xxDtTm_I5" ezfName="xxDtTm_I5" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td aline="center"><ezf:inputText name="mixPltPltNoteTxt_I1" ezfName="mixPltPltNoteTxt_I1" value="1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td aline="center"><ezf:inputText name="asgShipViaCd_I1" ezfName="asgShipViaCd_I1" value="1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="asgPrtyCd_I1" ezfName="asgPrtyCd_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="indSgnReqFlg_I1" ezfName="indSgnReqFlg_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="rtrnLbCd_I1" ezfName="rtrnLbCd_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="rtlWhCd_I1" ezfName="rtlWhCd_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="invtyOwnrCd_I1" ezfName="invtyOwnrCd_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="schdDelyDt_I1" ezfName="schdDelyDt_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="carrCd_I1" ezfName="carrCd_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="shpgSvcLvlCd_I1" ezfName="shpgSvcLvlCd_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="rtrnItemInclFlg_I1" ezfName="rtrnItemInclFlg_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="svcConfigMstrPk_I1" ezfName="svcConfigMstrPk_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																						<td><ezf:label name="asmReqFlg_I1" ezfName="asmReqFlg_I1" ezfHyo="I" ezfArrayNo="0" /></td>
																					</tr>
																				</ezf:row>
																			</table>
																		</div>
																	</div>
																	<table border="0" width="100%">
																		<col>
																		<tr height="16px">
																			<td></td>
																		</tr>
																	</table>
																	<fieldset>
																	<legend>Download Edit</legend>
																	<table height="30">
																		<col width="64" align="left">
																		<col width="120">
																		<tr>
																			<td class="stab">Submit Type</td>
																			<td>
																				<ezf:select name="xxTpCd_J2" ezfName="xxTpCd_J2" ezfBlank="1" ezfCodeName="xxTpCd_J1" ezfDispName="xxEdtCdNm_J0" otherEvent1=" onchange=\"sendServer('OnChange_DNLD_SelectSendType')\"" />
																			</td>
																		</tr>
																	</table>
																	<table height="30" border="0">
																		<col width="60">
																		<col width="120">
																		<col width="10">
																		<col width="300">
																		<tr>
																			<td class="stab">Wms Wh</td>
																			<td align="left">
																				<ezf:select name="whCd_J2" ezfName="whCd_J2" ezfBlank="1" ezfCodeName="whCd_J1" ezfDispName="xxEdtCdNm_J1" otherEvent1=" onchange=\"sendServer('SelectWh')\"" />
																			</td>
																			<td></td>
																			<td>
																				<table>
																					<col width="32">
																					<col width="111">
																					<col width="32">
																					<col width="111">
																					<tr>
																						<td class="stab" align="right">RSD</td>
																						<td>
																							<ezf:inputText name="xxRsdDt_J1" ezfName="xxRsdDt_J1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
																							<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxRsdDt_J1', 4 );">
																						</td>
																						<td class="stab" align="right">RDD</td>
																						<td>
																							<ezf:inputText name="xxRddDt_J1" ezfName="xxRddDt_J1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
																							<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxRddDt_J1', 4 );">
																						</td>
																					</tr>
																				</table>
																			</td>
																			<td></td>
																			<td class="stab" align="right">Cust Order#</td>
																			<td align="left"><ezf:inputText name="custOrdNum_J1" ezfName="custOrdNum_J1" value="12345678901234567890123456789012345" otherAttr=" size=\"35\" maxlength=\"35\""/></td>
																			<td></td>
																		</tr>
																	</table>
																	<table height="30">
																		<col width="56" align="right">
																		<col width="96">
																		<col width="5">
																		<col width="104" align="right">
																		<col width="160">
																		<tr>
																			<td class="stab" align="left">Ship Via</td>
																			<td align="left">
																				<ezf:select name="tplSvcLvlCd_J2" ezfName="tplSvcLvlCd_J2" ezfBlank="1" ezfCodeName="tplSvcLvlCd_J1" ezfDispName="xxEdtCdNm_J2" otherEvent1=" onchange=\"sendServer('SelectShipVia')\"" />
																			</td>
																			<td></td>
																			<td class="stab">Freight Code</td>
																			<td align="left">
																				<ezf:select name="wmsFrtOutCd_J2" ezfName="wmsFrtOutCd_J2" ezfBlank="1" ezfCodeName="wmsFrtOutCd_J1" ezfDispName="xxEdtCdNm_J3" />
																			</td>
																		</tr>
																	</table>





																	<table height="30" border="0">
																		<col width="30">
																		<col width="170">
																		<col width="32">
																		<col width="150">

																		<col width="5">
																		<col width="160">
																		<col width="120">
																		<col width="5">
																		<col width="30">
																		<col width="140">
																		<col width="5">
																		<col width="96">
																		<col width="120">
																		<col width="5">
																		<col width="70">
																		<col width="96">
																		<tr>
																			<td class="stab">Wh</td>
																			<td align="left">
																				<ezf:select name="rtlWhCd_01" ezfName="rtlWhCd_01" ezfBlank="1" ezfCodeName="rtlWhCd_LC" ezfDispName="xxEdtCdNm_LD" />
																			</td>
																			<td class="stab">Owner</td>
																			<td align="left">
																				<ezf:select name="invtyOwnrCd_J1" ezfName="invtyOwnrCd_J1" ezfBlank="1" ezfCodeName="invtyOwnrCd_LC" ezfDispName="invtyOwnrDescTxt_LD" otherAttr=" style=\"width:100px\""/>
																			</td>
																			<td></td>
																			<td class="stab" align="right">Scheduled Delivery Date</td>
																			<td>
																				<ezf:inputText name="schdDelyDt_J1" ezfName="schdDelyDt_J1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
																				<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'schdDelyDt_J1', 4 );">
																			</td>

																			<td></td>
																			<td class="stab" align="right">Carrier</td>
																			<td align="left"><ezf:inputText name="carrCd_J1" ezfName="carrCd_J1" value="12345678901234567890123456789012345" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
																			<td></td>
																			<td class="stab" align="right">Shpg Svc Lvl</td>
																			<td align="left"><ezf:inputText name="shpgSvcLvlCd_J1" ezfName="shpgSvcLvlCd_J1" value="12345678901234567890123456789012345" otherAttr=" size=\"2\" maxlength=\"2\""/></td>
																		</tr>
																	</table>
																	<table height="30">
																		<col width="100" align="right">
																		<col width="50">
																		<col width="5">
																		<col width="100" align="right">
																		<col width="120">
																		<col width="5">
																		<col width="100" align="right">
																		<col width="160">
																		<tr>
																			<td class="stab" align="left">Return Item Includes</td>
																			<td align="left">
																				<ezf:select name="rtrnItemInclFlg_J1" ezfName="rtrnItemInclFlg_J1" ezfCodeName="xxPgFlg_LC" ezfDispName="xxPgFlg_LD" otherAttr=" style=\"width:50px\""/>
																			</td>
																			<td></td>
																			<td class="stab">Configuration ID</td>
																			<td align="left"><ezf:inputText name="svcConfigMstrPk_J1" ezfName="svcConfigMstrPk_J1" value="12345678901234567890123456789012345" otherAttr=" size=\"28\" maxlength=\"28\""/></td>

																			<td></td>
																			<td class="stab">Assembly Required</td>
																			<td align="left">
																				<ezf:select name="asmReqFlg_J1" ezfName="asmReqFlg_J1" ezfCodeName="xxPgFlg_LC" ezfDispName="xxPgFlg_LD" otherAttr=" style=\"width:50px\""/>
																			</td>
																		</tr>
																	</table>



																	<table border="0" width="100%">
																		<tr height="16px">
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
																			<td><ezf:inputButton name="OnClick_CheckAll" value="Check All" htmlClass="pBtn8" /></td>
																			<td><ezf:inputButton name="OnClick_UncheckAll" value="UnCheck All" htmlClass="pBtn8" /></td>
																		</tr>
																	</table>
																	<div id="topTBL" style="overflow-x:none; width:1075;  overflow-y:none;">
																		<table border="1" cellpadding="0" cellspacing="0" width="1090">
																			<col width="30"  align="center">
																			<col width="50" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="85" align="center">
																			<col width="85" align="center">
  																		<col width="85" align="center">
																			<col width="85" align="center">
																			<col width="100" align="center">
																			<col width="60" align="center">
																			<col width="50" align="center">
																			<col width="50" align="center">
																			<col width="160" align="center">

																			<tr height="28">
																				<td class="pClothBs"></td>
																				<td class="pClothBs">Line#</td>
																				<td class="pClothBs">Mdse Code</td>
																				<td class="pClothBs">Quantity/Units</td>
																				<td class="pClothBs">UOM</td>
																				<td class="pClothBs">Stock Status</td>
																				<td class="pClothBs">Stock Status To</td>

																				<td class="pClothBs">Package Code</td>
																				<td class="pClothBs">Pick Configuration ID</td>
																				<td class="pClothBs">BO Impact Type</td>
																				<td class="pClothBs">Remove Config</td>
																				<td class="pClothBs">Config Flg</td>
																				<td class="pClothBs">Serial#</td>


																			</tr>
																		</table>
																	</div>
																	<div id="bottomTBL" style="word-break:break-all; width:1075; overflow-y:auto;">
																		<table border="1" cellpadding="0" cellspacing="0" width="1090" id="A">
																			<col width="30"  align="center">
																			<col width="50" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="85" align="center">
																			<col width="85" align="center">
  																		<col width="85" align="center">
																			<col width="85" align="center">
																			<col width="100" align="center">
																			<col width="60" align="center">
																			<col width="50" align="center">
																			<col width="50" align="center">
																			<col width="160" align="center">

																			<ezf:row ezfHyo="K">
																				<tr height="28">
																					<td><ezf:inputCheckBox name="xxChkBox_K1" ezfName="xxChkBox_K1" value="Y" ezfHyo="K" ezfArrayNo="0" /></td>
																					<td><ezf:label name="wmsLineNum_K1" ezfName="wmsLineNum_K1" ezfHyo="K" ezfArrayNo="0" /></td>
																					<td><ezf:inputText name="wmsMdseCd_K1" ezfName="wmsMdseCd_K1" value="2534B005AA" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\" ezftoupper=\"\""/></td>
																					<td><ezf:inputText name="wmsShipQty_K1" ezfName="wmsShipQty_K1" value="123456789012345678901234567890" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
																					<td>
																						<ezf:select name="wmsUomCd_K2" ezfName="wmsUomCd_K2" ezfHyo="K" ezfArrayNo="0" ezfBlank="1" ezfCodeName="wmsUomCd_K1" ezfDispName="xxEdtCdNm_K1" ezfCodeDispHyo="K" otherAttr=" style=\"width:80px\""/>
																					</td>
																					<td>
																						<ezf:select name="wmsStkStsCd_K2" ezfName="wmsStkStsCd_K2" ezfHyo="K" ezfArrayNo="0" ezfBlank="1" ezfCodeName="wmsStkStsCd_K1" ezfDispName="xxEdtCdNm_K2" ezfCodeDispHyo="K" otherAttr=" style=\"width:80px\""/>
																					</td>
																					<td>
																						<ezf:select name="wmsStkStsCd_K4" ezfName="wmsStkStsCd_K4" ezfHyo="K" ezfArrayNo="0" ezfBlank="1" ezfCodeName="wmsStkStsCd_K3" ezfDispName="xxEdtCdNm_K4" ezfCodeDispHyo="K" otherAttr=" style=\"width:80px\""/>
																					</td>

																					<td>
																						<ezf:select name="packCdTxt_K1" ezfName="packCdTxt_K1" ezfHyo="K" ezfArrayNo="0" ezfBlank="1" ezfCodeName="fill40Txt_LC" ezfDispName="fill40Txt_LD" otherAttr=" style=\"width:80px\""/>
																					</td>

																					<td><ezf:inputText name="svcConfigMstrPk_K1" ezfName="svcConfigMstrPk_K1" value="2534B005AA" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>


																					<td><ezf:inputText name="backOrdImpctTpCd_K1" ezfName="backOrdImpctTpCd_K1" value="2534B005AA" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/></td>

																					<td>
																						<ezf:select name="rmvConfigFlg_K1" ezfName="rmvConfigFlg_K1" ezfHyo="K" ezfArrayNo="0" ezfCodeName="xxPgFlg_LC" ezfDispName="xxPgFlg_LD" otherAttr=" style=\"width:50px\""/>
																					</td>
																					<td>
																						<ezf:select name="indConfigFlg_K1" ezfName="indConfigFlg_K1" ezfHyo="K" ezfArrayNo="0" ezfCodeName="xxPgFlg_LC" ezfDispName="xxPgFlg_LD" otherAttr=" style=\"width:50px\""/>
																					</td>

																					<td><ezf:inputText name="serNum_K1" ezfName="serNum_K1" value="2534B005AA" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>









																				</tr>
																			</ezf:row>
																		</table>
																	</div>
																	<table cellpadding="0" cellspacing="0">
																		<col width="100" align="left">
																		<col width="100">
																		<col width="785">
																		<col width="100"  align="right">
																		<tr>
																			<td><ezf:inputButton name="OnClick_DNLD_DeleteRow" value="Delete Row" htmlClass="pBtn8" /></td>
																			<td><!--<input type="button" class="pBtn8" value="Copy Row" name="OnClick_DNLD_CopyRow" onclick="sendServer(this)">--></td>
																			<td></td>
																			<td><ezf:inputButton name="OnClick_DNLD_InsertRow" value="Insert Row" htmlClass="pBtn8" /></td>
																			<td></td>
																		</tr>
																	</table>
																	</fieldset>
																</td>
															</tr>

															<tr>
																<td valign="top">
																	<table border="0" width="100%">
																		<col width="1089">
																		<tr height="8px">
																			<td></td>
																		</tr>
																	</table>
															</tr>
															<tr>
																<td>
																	<table border="0" width="100%">
																		<col width="1093">
																		<tr>
																			<td class="stab">Address</td>
																			<td></td>
																		</tr>
																	</table>
																	<%-- ### MEISAI - HEAD --%>
																	<table border="1" cellpadding="0" cellspacing="0"width="1096" >
																		<col align="center" width="245">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="245">
																		<col align="center" width="160">
																		<col align="center" width="59">
																		<col align="center" width="59">
																		<col align="center" width="96">
																		<tr>
																			<td class="pClothBs">Name</td>
																			<td class="pClothBs">Name2</td>
																			<td class="pClothBs">Contact</td>
																			<td class="pClothBs">Address</td>
																			<td class="pClothBs">City</td>
																			<td class="pClothBs">State</td>
																			<td class="pClothBs">Zip</td>
																			<td class="pClothBs">Contact#</td>
																		</tr>
																	</table>
																	<%-- ### MEISAI - DETAIL --%>
																	<table border="1" cellpadding="0" cellspacing="0"width="1096" >
																		<col align="center" width="245">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="245">
																		<col align="center" width="160">
																		<col align="center" width="59">
																		<col align="center" width="59">
																		<col align="center" width="96">
																		<ezf:row ezfHyo="L">
																			<tr>
																				<td><ezf:inputText name="wmsShipToNm_L1" ezfName="wmsShipToNm_L1" value="JAPS-OLSON CO - PO #10363 X" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"33\""/></td>
																				<td><ezf:inputText name="wmsShipToNm_L2" ezfName="wmsShipToNm_L2" value="aa" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																				<td><ezf:inputText name="wmsShipToCtacNm_L1" ezfName="wmsShipToCtacNm_L1" value="0000503636" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																				<td><ezf:inputText name="fill256Txt_L1" ezfName="fill256Txt_L1" value="7500 EXCELSIOR BLVD,,," ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"33\""/></td>
																				<td><ezf:inputText name="ctyAddr_L1" ezfName="ctyAddr_L1" value="SAINT LOUIS PARK" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
																				<td><ezf:label name="stCd_L1" ezfName="stCd_L1" ezfHyo="L" ezfArrayNo="0" /></td>
																				<td><ezf:label name="postCd_L1" ezfName="postCd_L1" ezfHyo="L" ezfArrayNo="0" /></td>
																				<td><ezf:label name="shipToCtacNum_L1" ezfName="shipToCtacNum_L1" ezfHyo="L" ezfArrayNo="0" /></td>
																			</tr>
																		</ezf:row>
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
																<td>
																	<%-- ### MEISAI - HEAD --%>
																	<div id="topTBL" style="overflow-x:none; width:900;">
																		<table border="1" cellpadding="0" cellspacing="0"width="900" >
																			<col align="center" width="240">
																			<tr>
																				<td class="pClothBs">Shipping Instructions</td>
																			</tr>
																		</table>
																		<%-- ### MEISAI - DETAIL --%>
																		<div id="bottomTBL" style="overflow-x:none; width:900;" >
																			<table border="1" cellpadding="0" cellspacing="0"width="900" > 
																				<col width="240">
																				<ezf:row ezfHyo="M">
																				<tr>
																					<td><ezf:inputText name="xxMsgTxt_M1" ezfName="xxMsgTxt_M1" value="CBS P/O : 0000503636" ezfHyo="M" ezfArrayNo="0" otherAttr=" size=\"127\""/></td>
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
																		<col width="1093">
																		<tr>
																			<td class="stab">Order Detail</td>
																			<td></td>
																		</tr>
																	</table>
																	<%-- ### MEISAI - HEAD --%>
																	<div id="topTBL" style="overflow-x:scroll; width:1093;">
																		<table border="1" cellpadding="0" cellspacing="0"width="3182" >
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">
																			<col align="center" width="100">


																			<col width="100" align="center">
																			<col width="200" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="200" align="center">

																			<tr>
																				<td class="pClothBs">Line#</td>
																				<td class="pClothBs">Mdse<br>Code</td>
																				<td class="pClothBs">Stock Status</td>
																				<td class="pClothBs">Stock Status To</td>
																				<td class="pClothBs">Mdse Description</td>
																				<td class="pClothBs">Shipped<br>Quantity</td>
																				<td class="pClothBs">Indicator<br>Serial Id</td>
																				<td class="pClothBs">Set Mdse</td>
																				<td class="pClothBs">Shipped Set<br>Quantity</td>
																				<td class="pClothBs">Cust Mdse Code</td>
																				<td class="pClothBs">Original SO#</td>
																				<td class="pClothBs">Original Line#</td>
																				<td class="pClothBs">Total Weight</td>
																				<td class="pClothBs">Total Volume</td>
																				<td class="pClothBs">CA Quantity</td>
																				<td class="pClothBs">PL Quantity</td>
																				<td class="pClothBs">Est.<br>#Cases</td>
																				<td class="pClothBs">Est.<br>#Plts</td>
																				<td class="pClothBs">UPC</td>
																				<td class="pClothBs">NMFC</td>
																				<td class="pClothBs">Est.Total<br>#Cases</td>
																				<td class="pClothBs">Est.Total<br>#Plts</td>

																				<td class="pClothBs">Package Code</td>
																				<td class="pClothBs">Pick Configuration ID</td>
																				<td class="pClothBs">Back Order</td>
																				<td class="pClothBs">BO Impact Type</td>
																				<td class="pClothBs">Remove Config</td>
																				<td class="pClothBs">Config Flg</td>
																				<td class="pClothBs">Serial#</td>


																			</tr>
																		</table>
																		<%-- ### MEISAI - DETAIL --%>
																		<div id="bottomTBL" style="overflow-x:none; width:1093;" >
																			<table border="1" cellpadding="0" cellspacing="0"width="3182" > 
																				<col width="100">
																				<col width="100">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100">
																				<col width="100" align="right">
																				<col width="100" align="center">
																				<col width="100">
																				<col width="100" align="right">
																				<col width="100">
																				<col width="100">
																				<col width="100" align="left">
																				<col width="100" align="right">
																				<col width="100" align="right">
																				<col width="100" align="right">
																				<col width="100" align="right">
																				<col width="100" align="right">
																				<col width="100" align="right">
																				<col width="100">
																				<col width="100" align="center">
																				<col width="100" align="right">
																				<col width="100" align="right">


																				<col width="100" align="center">
																				<col width="200" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="100" align="center">
																				<col width="200" align="center">

																				<ezf:row ezfHyo="N">
																					<tr>
																						<td><ezf:label name="wmsLineNum_N1" ezfName="wmsLineNum_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="wmsMdseCd_N1" ezfName="wmsMdseCd_N1" value="5816B001AA" ezfHyo="N" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="s80StkStsCd_N1" ezfName="s80StkStsCd_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="s80StkStsCdToCd_N1" ezfName="s80StkStsCdToCd_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="mdseDescShortTxt_N1" ezfName="mdseDescShortTxt_N1" value="SCANNER EXPRESS" ezfHyo="N" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="wmsShipQty_N1" ezfName="wmsShipQty_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="indSerId_N1" ezfName="indSerId_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="mdseCdSetCd_N1" ezfName="mdseCdSetCd_N1" value="XXXX" ezfHyo="N" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="shipSetQty_N1" ezfName="shipSetQty_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="custMdseCd_N1" ezfName="custMdseCd_N1" value="XXXX" ezfHyo="N" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="origSoId_N1" ezfName="origSoId_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="origLineNum_N1" ezfName="origLineNum_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="totWtAmtNum_N1" ezfName="totWtAmtNum_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="totVolAmtNum_N1" ezfName="totVolAmtNum_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="unitCrtnQty_N1" ezfName="unitCrtnQty_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="unitPltQty_N1" ezfName="unitPltQty_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="estCseAmtNum_N1" ezfName="estCseAmtNum_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="estPltAmtNum_N1" ezfName="estPltAmtNum_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:inputText name="wmsUpcCd_N1" ezfName="wmsUpcCd_N1" value="XXXX" ezfHyo="N" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="fill20Txt_N1" ezfName="fill20Txt_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="estCseAmtNum_N2" ezfName="estCseAmtNum_N2" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="estPltAmtNum_N2" ezfName="estPltAmtNum_N2" ezfHyo="N" ezfArrayNo="0" /></td>
																						<!-- <td><label ezfout ezfhyo="N" name="fill40Txt_N1" ezfname="fill40Txt_N1">0.1</label></td> -->
																						<td><ezf:inputText name="fill40Txt_N1" ezfName="fill40Txt_N1" value="XXXX" ezfHyo="N" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
																						<td><ezf:label name="svcConfigMstrPk_N1" ezfName="svcConfigMstrPk_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="backOrdFlg_N1" ezfName="backOrdFlg_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="backOrdImpctTpCd_N1" ezfName="backOrdImpctTpCd_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="rmvConfigFlg_N1" ezfName="rmvConfigFlg_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="indConfigFlg_N1" ezfName="indConfigFlg_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																						<td><ezf:label name="serNum_N1" ezfName="serNum_N1" ezfHyo="N" ezfArrayNo="0" /></td>
																					</tr>
																				</ezf:row>
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
								<%-- ######################################## Upload Edit TAB ######################################## --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab=='UpldEdt'}">
									<script type="text/javascript">
										document.getElementById("tbSOList").className="pTab_OFF";
										document.getElementById("tbSOStatus").className="pTab_OFF";
										document.getElementById("tbDnldEdt").className="pTab_OFF";
										document.getElementById("tbUpldEdt").className="pTab_ON";
									</script>
									<div class="pTab_BODY_In" style="height: 400px">
										<table height="375" width="100%">
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
															<td><ezf:label name="wmsSoId_O1" ezfName="wmsSoId_O1" /></td>
															<td></td>
															<td class="stab">Task</td>
															<td>
																<ezf:select name="wmsTaskCd_O4" ezfName="wmsTaskCd_O4" ezfCodeName="wmsTaskCd_O3" ezfDispName="xxEdtCdNm_O6" />
															</td>
															<td></td>
															<td><ezf:inputCheckBox name="xxChkBox_O2" ezfName="xxChkBox_O2" value="Y" /></td>
															<td class="stab"><label for="OrderedCheck">Active</labe></td>
															<td><ezf:inputCheckBox name="xxChkBox_O3" ezfName="xxChkBox_O3" value="Y" /></td>
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
																<table border="1" cellpadding="0" cellspacing="0" width="468">
																	<col align="center" width="35">
																	<col align="center" width="35">
																	<col align="center" width="35">
																	<col align="center" width="85">
																	<col align="center" width="95">
																	<col align="center" width="50">
																	<col align="center" width="50">
																	<col align="center" width="75">
																	<tr height="37">
																		<td class="pClothBs">&nbsp;</td>
																		<td class="pClothBs">Actv</td>
																		<td class="pClothBs">Hist</td>
																		<td class="pClothBs">Processed</td>
																		<td class="pClothBs">Record ID</td>
																		<td class="pClothBs">Line#<br>(OTBD)</td>
																		<td class="pClothBs">Line#<br>(INBD)</td>
																		<td class="pClothBs">Task</td>
																	</tr>
																</table>
																<%-- id:leftTBL --%>
																<div id="leftTBL" style="overflow-y:hidden; height:282; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));">
																	<table border="1" cellpadding="0" cellspacing="0" width="468" id="O_left">
																		<col align="center" width="35">
																		<col align="center" width="35">
																		<col align="center" width="35">
																		<col align="center" width="85">
																		<col align="center" width="95">
																		<col align="center" width="50">
																		<col align="center" width="50">
																		<col align="center" width="75">
																		<ezf:row ezfHyo="O">
																			<tr height="28">
																				<td><ezf:inputCheckBox name="xxChkBox_O1" ezfName="xxChkBox_O1" value="Y" ezfHyo="O" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxExstFlg_O1" ezfName="xxExstFlg_O1" ezfHyo="O" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsUpdHistNum_O1" ezfName="wmsUpdHistNum_O1" ezfHyo="O" ezfArrayNo="0" /></td>
																				<td><ezf:label name="fill30Txt_O1" ezfName="fill30Txt_O1" ezfHyo="O" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsRecId_O1" ezfName="wmsRecId_O1" ezfHyo="O" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="otbdOrdLineNum_O1" ezfName="otbdOrdLineNum_O1" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\""/></td>
																				<td><ezf:inputText name="inbdOrdLineNum_O1" ezfName="inbdOrdLineNum_O1" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\""/></td>
																				<td>
																					<ezf:select name="wmsTaskCd_O2" ezfName="wmsTaskCd_O2" ezfHyo="O" ezfArrayNo="0" ezfBlank="1" ezfCodeName="wmsTaskCd_O1" ezfDispName="xxEdtCdNm_O1" ezfCodeDispHyo="O" otherAttr=" style=\"width:60px;\""/>
																				</td>
																			</tr>
																		</ezf:row>
																	</table>
																</div>
															</td>
															<%-- ########## Table:B --%>
															<td valign="top">
																<%-- id:topTBL --%>
																<div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:624;" onScroll="synchroScrollLeft(this.id, new Array('rightTBL'));">
																	<table border="1" cellpadding="0" cellspacing="0" width="3342">
																		<col align="center" width="60">
																		<col align="center" width="120">
																		<col align="center" width="60">
																		<col align="center" width="90">
																		<col align="center" width="50">
																		<col align="center" width="110">
																		<col align="center" width="60">
																		<col align="center" width="110">
																		<col align="center" width="140">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="120">
																		<col align="center" width="96">
																		<col align="center" width="110">
																		<col align="center" width="140">
																		<col align="center" width="60">
																		<tr height="37">
																			<td class="pClothBs">Order<br>Status</td>
																			<td class="pClothBs">Mdse Code</td>
																			<td class="pClothBs">Stock<br>Status</td>
																			<td class="pClothBs">Quantity</td>
																			<td class="pClothBs">Otbd<br>Ord Tp</td>
																			<td class="pClothBs">Otbd Ord Num</td>
																			<td class="pClothBs">Inbd<br>Ord Tp</td>
																			<td class="pClothBs">Inbd Ord Num</td>
																			<td class="pClothBs">Transaction<br>Date</td>
																			<td class="pClothBs">Total<br>Weight</td>
																			<td class="pClothBs">Carrier<br>Code</td>
																			<td class="pClothBs">Trailer ID</td>
																			<td class="pClothBs">Pro Bill</td>
																			<td class="pClothBs">BOL#</td>
																			<td class="pClothBs">Trk#</td>
																			<td class="pClothBs">Shipment#</td>
																			<td class="pClothBs">Container</td>
																			<td class="pClothBs">Most Outer<br>Container</td>
																			<td class="pClothBs">Freight<br>Charge</td>
																			<td class="pClothBs">TMS Freight<br>Charge</td>
																			<td class="pClothBs">WMS ORG<br>HOST ID</td>
																			<td class="pClothBs">Serial#</td>
																			<td class="pClothBs">Estimated<br>Dock Date</td>
																			<td class="pClothBs">CreatedOn</td>
																			<td class="pClothBs">Package Code</td>
																		</tr>
																	</table>
																</div>
																<%-- id:rightTBL --%>
																<div id="rightTBL" style="overflow-y:scroll; height:299; overflow-x:scroll; width:641;" onScroll="synchroScrollTop(this.id, new Array('leftTBL')); onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
																	<table border="1" cellpadding="0" cellspacing="0" id="O_right" width="3342">
																		<col align="center" width="60">
																		<col align="center" width="120">
																		<col align="center" width="60">
																		<col align="center" width="90">
																		<col align="center" width="50">
																		<col align="center" width="110">
																		<col align="center" width="60">
																		<col align="center" width="110">
																		<col align="center" width="140">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="96">
																		<col align="center" width="120">
																		<col align="center" width="96">
																		<col align="center" width="110">
																		<col align="center" width="140">
																		<col align="center" width="60">
																		<ezf:row ezfHyo="O">
																			<tr height="28">
																				<td><ezf:select name="wmsOrdStsCd_O2" ezfName="wmsOrdStsCd_O2" ezfHyo="O" ezfArrayNo="0" ezfBlank="1" ezfCodeName="wmsOrdStsCd_O1" ezfDispName="xxEdtCdNm_O2" ezfCodeDispHyo="O" otherAttr=" style=\"width:60px;\""/>
																				</td>
																				<td><ezf:inputText name="wmsMdseCd_O1" ezfName="wmsMdseCd_O1" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\""/></td>
																				<td><ezf:select name="wmsStkStsCd_O2" ezfName="wmsStkStsCd_O2" ezfHyo="O" ezfArrayNo="0" ezfBlank="1" ezfCodeName="wmsStkStsCd_O1" ezfDispName="xxEdtCdNm_O3" ezfCodeDispHyo="O" otherAttr=" style=\"width:30px;\""/>
																				</td>
																				<td><ezf:inputText name="wmsOrdQty_O1" ezfName="wmsOrdQty_O1" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\""/></td>
																				<td><ezf:select name="otbdOrdTpCd_O2" ezfName="otbdOrdTpCd_O2" ezfHyo="O" ezfArrayNo="0" ezfBlank="1" ezfCodeName="otbdOrdTpCd_O1" ezfDispName="xxEdtCdNm_O4" ezfCodeDispHyo="O" otherAttr=" style=\"width:40px;\""/></td>
																				<td><ezf:inputText name="otbdOrdNum_O1" ezfName="otbdOrdNum_O1" value="2" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"30\""/></td>
																				<td><ezf:select name="inbdOrdTpCd_O2" ezfName="inbdOrdTpCd_O2" ezfHyo="O" ezfArrayNo="0" ezfBlank="1" ezfCodeName="inbdOrdTpCd_O1" ezfDispName="xxEdtCdNm_O5" ezfCodeDispHyo="O" otherAttr=" style=\"width:40px;\""/></td>
																				<td><ezf:inputText name="inbdOrdNum_O1" ezfName="inbdOrdNum_O1" value="2" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"30\""/></td>
																				<td><ezf:label name="xxDtTm_O1" ezfName="xxDtTm_O1" ezfHyo="O" ezfArrayNo="0" /></td></td>
																				<td><ezf:inputText name="wmsTotWt_O1" ezfName="wmsTotWt_O1" value="1" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\""/></td>
																				<td><ezf:inputText name="wmsCarrCd_O1" ezfName="wmsCarrCd_O1" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
																				<td><ezf:inputText name="wmsTrailId_O1" ezfName="wmsTrailId_O1" value="2" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\""/></td>
																				<td><ezf:inputText name="proNum_O1" ezfName="proNum_O1" value="3" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\""/></td>
																				<td><ezf:inputText name="bolNum_O1" ezfName="bolNum_O1" value="4" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\""/></td>
																				<td><ezf:inputText name="tmsTrkNum_O1" ezfName="tmsTrkNum_O1" value="trk#" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"40\""/></td>
																				<td><ezf:inputText name="wmsShipId_O1" ezfName="wmsShipId_O1" value="5" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\""/></td>
																				<td><ezf:inputText name="wmsCntnrId_O1" ezfName="wmsCntnrId_O1" value="6" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																				<td><ezf:inputText name="wmsOutCntnrNum_O1" ezfName="wmsOutCntnrNum_O1" value="7" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																				<td><ezf:inputText name="wmsFrtChrgAmt_O1" ezfName="wmsFrtChrgAmt_O1" value="7" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
																				<td><ezf:inputText name="tmsFrtChrgAmt_O1" ezfName="tmsFrtChrgAmt_O1" value="7" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
																				<td><ezf:label name="wmsOrgHostId_O1" ezfName="wmsOrgHostId_O1" ezfHyo="O" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="serNum_O1" ezfName="serNum_O1" value="10" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																				<td>
																					<ezf:inputText name="xxDt10Dt_O1" ezfName="xxDt10Dt_O1" value="MM/DD/YYYY" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"xxDt10Dt_O1{EZF_ROW_NUMBER}\""/>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxDt10Dt_O1', 4, '{EZF_ROW_NUMBER}');">
																				</td>
																				<td><ezf:label name="xxDtTm_O2" ezfName="xxDtTm_O2" ezfHyo="O" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="packCdTxt_O1" ezfName="packCdTxt_O1" value="7" ezfHyo="O" ezfArrayNo="0" otherAttr=" size=\"6\""/></td>
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
														<col width="835">
														<col width="91">
														<tr height="30">
															<td><ezf:inputButton name="OnClick_UPD_CopyRow" value="Copy Row" htmlClass="pBtn8" /></td>
															<td><ezf:inputButton name="OnClick_UPD_DeleteRow" value="Delete Row" htmlClass="pBtn8" /></td>
															<td></td>
															<td><ezf:inputButton name="OnClick_UPD_InsertRow" value="Insert Row" htmlClass="pBtn8" /></td>
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
