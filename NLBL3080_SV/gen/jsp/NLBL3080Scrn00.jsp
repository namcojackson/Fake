<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180723130616 --%>
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
			<input type="hidden" name="pageID" value="NLBL3080Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mng B/O and Allocations">

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
										<li title="Location Info" class="pTab_ON"><a href="#">Mng BO</a></li>
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
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top" width="5"></td>
							<td valign="top" width="">
								<table cellpadding="0" style="height: 90px;">
									<col align="left" width="120">
									<col align="left" width="170">
									<tr>
										<td class="stab">Sales Order Number(*)</td>
										<td><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="XX" otherAttr=" size=\"20\" maxlength=\"8\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">Order Category</td>
										<td>
											<ezf:select name="dsOrdCatgCd" ezfName="dsOrdCatgCd" ezfBlank="1" ezfCodeName="dsOrdCatgCd_P" ezfDispName="dsOrdCatgDescTxt_P" otherAttr=" style=\"width:146px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Order Reason</td>
										<td>
											<ezf:select name="dsOrdTpCd" ezfName="dsOrdTpCd" ezfBlank="1" ezfCodeName="dsOrdTpCd_P" ezfDispName="dsOrdTpDescTxt_P" otherAttr=" style=\"width:146px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Serial</td>
										<td><ezf:inputText name="serNum" ezfName="serNum" value="XX" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>
							<td valign="top" width="">
								<table cellpadding="0" style="height: 90px;">
									<col align="left" width="80">
									<col align="left" width="65">
									<col align="center" width="35">
									<col align="left" width="154">
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_WR" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfo" >Warehouse</ezf:anchor></td>
										<td><ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="12345678" otherAttr=" size=\"12\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_RtlWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="123456789012345" otherAttr=" size=\"18\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_SP" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCustCode" >Ship To</ezf:anchor></td>
										<td><ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" value="XX" otherAttr=" size=\"12\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_ShipToCustInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="XX" otherAttr=" size=\"18\" maxlength=\"360\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_SP" ezfEmulateBtn="1" ezfGuard="OpenWin_SlsRepCode" >Sales Rep</ezf:anchor></td>
										<td><ezf:inputText name="tocCd" ezfName="tocCd" value="XX" otherAttr=" size=\"12\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_SlsRepInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="tocNm" ezfName="tocNm" value="XX" otherAttr=" size=\"18\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_CA" ezfEmulateBtn="1" ezfGuard="OpenWin_MdseInfo" >Item Number</ezf:anchor></td>
										<td><ezf:inputText name="mdseCd" ezfName="mdseCd" value="XX" otherAttr=" size=\"12\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_MdseInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="XX" otherAttr=" size=\"18\" maxlength=\"250\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
								</table>
							</td>
							<td valign="top" width="">
								<table cellpadding="0" style="height: 90px;">
									<col align="left" width="80">
									<col align="left" width="110">
									<col align="left" width="10">
									<col align="left" width="110">
									<tr>
										<td class="stab">Order Date</td>
										<td>
											<ezf:inputText name="xxOrdDt_FR" ezfName="xxOrdDt_FR" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxOrdDt_FR', 4 );">
										</td>
										<td class="stab">-</td>
										<td>
											<ezf:inputText name="xxOrdDt_TO" ezfName="xxOrdDt_TO" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxOrdDt_TO', 4 );">
										</td>
									</tr>
									<tr>
										<td class="stab">Request Date</td>
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
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_CF" ezfEmulateBtn="1" ezfGuard="OpenWin_ConfigID" >Config ID</ezf:anchor></td>
										<td colspan="3"><ezf:inputText name="svcConfigMstrPk" ezfName="svcConfigMstrPk" value="XX" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_ML" ezfEmulateBtn="1" ezfGuard="OpenWin_ModelName" >Model</ezf:anchor></td>
										<td colspan="3"><ezf:inputText name="t_MdlNm" ezfName="t_MdlNm" value="XX" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>
							<td valign="top" width="">
								<table cellpadding="0" style="height: 90px;">
									<col align="left" width="148">
									<tr>
										<td>
											<ezf:inputCheckBox name="xxChkBox_BO" ezfName="xxChkBox_BO" value="Y" /> Actual BO 
										</td>
									</tr>
									<tr>
										<td>
											<ezf:inputCheckBox name="xxChkBox_AL" ezfName="xxChkBox_AL" value="Y" /> To Be Allocate 
										</td>
									</tr>
									<tr>
										<td>
											<ezf:inputCheckBox name="xxChkBox_NS" ezfName="xxChkBox_NS" value="Y" /> Waiting for SO 
										</td>
									</tr>
									<tr height="30">
										<td valign="bottom" align="right" class="stab"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
<%-- #################################################### DETAIL ################################################### --%>
<%@ page import="business.servlet.NLBL3080.NLBL3080BMsg" %>
<%@ page import="business.servlet.NLBL3080.NLBL3080_ABMsg" %>
<%@ page import="business.servlet.NLBL3080.NLBL3080_BBMsg" %>
<%  NLBL3080BMsg bMsg = (NLBL3080BMsg)databean.getEZDBMsg(); %>
					<table width="100%">
						<col valign="top">
						<tr>
							<td>
								<div class="pTab_HEAD">
									<ul>
										<li class="pTab_ON"  id="Order" title="Order"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Tab_Ord" >Order</ezf:anchor></li>
										<li class="pTab_OFF" id="OrderLine" title="OrderLine"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Tab_OrdLine" >Order Line</ezf:anchor></li>
									</ul>
								</div>
								<c:choose>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Order'}">
								<script type="text/javascript">
									document.getElementById( "Order" ).className = "pTab_ON";
									document.getElementById( "OrderLine" ).className = "pTab_OFF";
								</script>
								<div class="pTab_BODY_In">
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="left" width="100">
													<col align="left" width="100">
													<col align="left" width="100">
													<col align="left" width="100">
													<col align="left" width="">
													<col align="right" width="550">
													<tr>
														<td><ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn8" /></td>
														<td><ezf:inputButton name="UnSelect_All" value="Unselect All" htmlClass="pBtn8" /></td>
														<td><ezf:inputButton name="Expand_All" value="All Expand" htmlClass="pBtn8" /></td>
														<td><ezf:inputButton name="Collapse_All" value="All Collapse" htmlClass="pBtn8" /></td>
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
															<%-- TABLE(TOP) --%>
															<div id="leftTopTBL_A" style="overflow-y:hidden; height:42; overflow-x:none;">
																<table border="1" cellpadding="1" cellspacing="0" height="42" style="table-layout: fixed">
																	<col align="center" width="30">		<!-- Button -->
																	<col align="center" width="30">		<!-- Checkbox -->
																	<col align="center" width="30">		<!-- Checkbox -->
																	<col align="center" width="80">		<!-- Sales Order Number -->
																	<col align="center" width="100">	<!-- Order Category -->
																	<col align="center" width="120">	<!-- Order Reason -->
																	<col align="center" width="50">		<!-- Line -->
																	<col align="center" width="50">		<!-- All Alloc -->
																	<col align="center" width="80">		<!-- Model -->
																	<col align="center" width="80">		<!-- Pick Config -->
																	<col align="center" width="80">		<!-- Ship To Cust Code -->
																	<col align="center" width="100">	<!-- Ship To Cust Name -->
																	<col align="center" width="120">	<!-- Ship To Cust Address -->
																	<col align="center" width="80">		<!-- Ship To Cust City -->
																	<col align="center" width="50">		<!-- Ship To Cust State -->
																	<tr>
																		<td class="pClothBs" rowspan="2"></td>
																		<td class="pClothBs" rowspan="2"></td>
																		<td class="pClothBs" rowspan="2"></td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cpoOrdNum_A1' )">Order<br>Number<img id="sortIMG.cpoOrdNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsOrdCatgDescTxt_A1' )">Order Category<img id="sortIMG.dsOrdCatgDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsOrdTpDescTxt_A1' )">Order Reason<img id="sortIMG.dsOrdTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dplyLineNum_A1' )">Line<img id="sortIMG.dplyLineNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxSetFlg_A1' )">All<br>Alloc<img id="sortIMG.xxSetFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 't_MdlNm_A1' )">Model<img id="sortIMG.t_MdlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pickSvcConfigMstrPk_A1' )">Pick Config<img id="sortIMG.pickSvcConfigMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs" colspan="5">Ship To</a></td>
																	</tr>
																	<tr>
																		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToCustLocCd_A1' )">Code<img id="sortIMG.shipToCustLocCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_A1' )">Name<img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxAllLineAddr_A1' )">Address<img id="sortIMG.xxAllLineAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToCtyAddr_A1' )">City<img id="sortIMG.shipToCtyAddr_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToStCd_A1' )">State<img id="sortIMG.shipToStCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	</tr>
																</table>
															</div>
															<%-- TABLE(BTM) --%>
															<div id="leftTBL_A" style="overflow-y:scroll; height:280; overflow-x:none;" onScroll="synchroScrollTop(this.id, new Array('rightTBL_A'))">
																<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed" >
																	<col align="center" width="30">		<!-- Button -->
																	<col align="center" width="30">		<!-- Checkbox -->
																	<col align="center" width="30">		<!-- Checkbox -->
																	<col align="center" width="80">		<!-- Sales Order Number -->
																	<col align="center" width="100">	<!-- Order Category -->
																	<col align="center" width="120">	<!-- Order Reason -->
																	<col align="center" width="50">		<!-- Line -->
																	<col align="center" width="50">		<!-- All Alloc -->
																	<col align="center" width="80">		<!-- Model -->
																	<col align="center" width="80">		<!-- Pick Config -->
																	<col align="center" width="80">		<!-- Ship To Cust Code -->
																	<col align="center" width="100">	<!-- Ship To Cust Name -->
																	<col align="center" width="120">	<!-- Ship To Cust Address -->
																	<col align="center" width="80">		<!-- Ship To Cust City -->
																	<col align="center" width="50">		<!-- Ship To Cust State -->
																	<% int i = 0; %>
																	<ezf:row ezfHyo="A">
																		<% NLBL3080_ABMsg orderMsg = bMsg.A.no(i++); %>
																		<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																			<td>
																				<% boolean isSmryLine = "Y".equals(orderMsg.xxSmryLineFlg_A1.getValue()); %>
																				<% if (isSmryLine) { %>
																						<img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('ExpandHolds',{EZF_ROW_NUMBER})" ezfhyo="A" height="14" id="xxSmryLineFlg#{EZF_ROW_NUMBER}">
																				<% } else if (!isSmryLine) { %>
																						<img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('ContractHolds',{EZF_ROW_NUMBER})" ezfhyo="A" height="14" id="xxSmryLineFlg#{EZF_ROW_NUMBER}">
																				<% } %>
																			</td>
																			<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('OnChange_ChkBoxCpoNum', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"OnChange_ChkBoxCpoNum{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('OnChange_ChkBoxCpoSlipNum', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"OnChange_ChkBoxCpoSlipNum{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_OrdEntry" otherAttr=" tabindex=\"-1\" id=\"Open_OrdEntry{EZF_ROW_NUMBER}\""><ezf:label name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																			<td><ezf:inputText name="dsOrdCatgDescTxt_A1" ezfName="dsOrdCatgDescTxt_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\" id=\"dsOrdCatgDescTxt{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputText name="dsOrdTpDescTxt_A1" ezfName="dsOrdTpDescTxt_A1" value="ZZZZZZZZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\" id=\"dsOrdTpDescTxt{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:label name="dplyLineNum_A1" ezfName="dplyLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="xxSetFlg_A1" ezfName="xxSetFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:inputText name="t_MdlNm_A1" ezfName="t_MdlNm_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\" id=\"t_MdlNm{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputText name="pickSvcConfigMstrPk_A1" ezfName="pickSvcConfigMstrPk_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\" ezftoupper=\"\" id=\"pickSvcConfigMstrPk{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputText name="shipToCustLocCd_A1" ezfName="shipToCustLocCd_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\" id=\"shipToCustLocCd{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"360\" ezftoupper=\"\" id=\"dsAcctNm{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputText name="xxAllLineAddr_A1" ezfName="xxAllLineAddr_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"400\" ezftoupper=\"\" id=\"xxAllLineAddr{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputText name="shipToCtyAddr_A1" ezfName="shipToCtyAddr_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"25\" ezftoupper=\"\" id=\"shipToCtyAddr{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:label name="shipToStCd_A1" ezfName="shipToStCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																		</tr>
																	</ezf:row>
																</table>
															</div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="left" width="">
													<tr>
														<td valign="center"><ezf:inputButton name="Open_CoordWrkBench" value="Cood Wrk Bench" htmlClass="pBtn8" /></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								</c:when>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'OrderLine'}">
								<script type="text/javascript">
									document.getElementById( "Order" ).className = "pTab_OFF";
									document.getElementById( "OrderLine" ).className = "pTab_ON";
								</script>
								<div class="pTab_BODY_In">
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="left" width="180">
													<col align="left" width="80">
													<col align="left" width="80">
													<col align="left" width="80">
													<col align="left" width="80">
													<col align="left" width="">
													<col align="right" width="550">
													<tr>
														<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
														<td><ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="UnSelect_All" value="Unselect All" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="Expand_All" value="All Expand" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="Collapse_All" value="All Collapse" htmlClass="pBtn6" /></td>
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
																	<div id='leftTbl' style="float:left; display:block; height:258px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
																	</div>
																</div>  <!-- left table -->
																<div style="float:left"> <!-- right table -->
																	<%-- LEFT-TABLE(TOP) --%>
																	<div id='rightTblHead' style="width:1070px; overflow-y:hidden; height:45; overflow-x:hidden;">
																		<table border="1" cellpadding="1" cellspacing="0" height="45" style="table-layout: fixed" id="BHEAD" width="1920px" style="margin-right:20px">
																			<col align="center" width="30">		<!-- Button -->
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="75">		<!-- Order Number -->
																			<col align="center" width="50">		<!-- Line -->
																			<col align="center" width="120">	<!-- Item -->
																			<col align="center" width="140">	<!-- Item Description -->
																			<col align="center" width="40">		<!-- MT -->
																			<col align="center" width="40">		<!-- Sup -->
																			<col align="center" width="120">	<!-- Set Item -->
																			<col align="center" width="80">		<!-- Essencial Critical -->
																			<col align="center" width="40">		<!-- SS -->
																			<col align="center" width="80">		<!-- Back Order Qty -->
																			<col align="center" width="80">		<!-- Allocated Qty -->
																			<col align="center" width="80">		<!-- Current Available -->
																			<col align="center" width="140">	<!-- Serial -->
																			<col align="center" width="120">	<!-- Model -->
																			<col align="center" width="80">		<!-- Ship Config -->
																			<col align="center" width="80">		<!-- Pick Config -->
																			<col align="center" width="85">		<!-- Request Date -->
																			<col align="center" width="100">	<!-- Warehouse -->
																			<col align="center" width="80">		<!-- SWH -->
																			<col align="center" width="100">	<!-- Line Category -->
																			<col align="center" width="100">	<!-- Line Status -->
																			<tr>
																				<td id="BH0"  class="pClothBs colFix"></td>
																				<td id="BH1"  class="pClothBs colFix"></td>
																				<td id="BH2"  class="pClothBs colFix"></td>
																				<td id="BH3"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'cpoOrdNum_B1' )">Order<br>Number<img id="sortIMG.cpoOrdNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH4"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'dplyLineNum_B1' )">Line<img id="sortIMG.dplyLineNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdseCd_B1' )">Item<img id="sortIMG.mdseCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdseDescShortTxt_B1' )">Item Description<img id="sortIMG.mdseDescShortTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'coaMdseTpCd_B1' )">MT<img id="sortIMG.coaMdseTpCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxSupdFlg_B1' )">Sup<img id="sortIMG.xxSupdFlg_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'setMdseCd_B1' )">Set Item<img id="sortIMG.setMdseCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'backOrdImpctTpDescTxt_B1' )">Essential<br>Critical<img id="sortIMG.backOrdImpctTpDescTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'stkStsCd_B1' )">SS<img id="sortIMG.stkStsCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'ordQty_B1' )">Back Order<br>Qty<img id="sortIMG.ordQty_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'ordQty_B2' )">Allocated<br>Qty<img id="sortIMG.ordQty_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'invtyAvalQty_B1' )">Current<br>Available<img id="sortIMG.invtyAvalQty_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'serNum_B1' )">Serial<img id="sortIMG.serNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 't_MdlNm_B1' )">Model<img id="sortIMG.t_MdlNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'svcConfigMstrPk_B1' )">Ship Config<img id="sortIMG.svcConfigMstrPk_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'pickSvcConfigMstrPk_B1' )">Pick Config<img id="sortIMG.pickSvcConfigMstrPk_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rddDt_B1' )">Request Date<img id="sortIMG.rddDt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rtlWhNm_B1' )">Warehouse<img id="sortIMG.rtlWhNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rtlSwhCd_B1' )">SWH<img id="sortIMG.rtlSwhCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'dsOrdLineCatgDescTxt_B1' )">Line Category<img id="sortIMG.dsOrdLineCatgDescTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																				<td id="BH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'ordLineStsNm_B1' )">Line Status<img id="sortIMG.ordLineStsNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			</tr>
																		</table>
																	</div>
																	<%-- LEFT-TABLE(MID) --%>
																	<div id="rightTBL" style="width:1087px; height:278px; display:block; overflow:scroll; margin:0px; padding:0px;" >
																		<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed"  width="1920px" >
																			<col align="center" width="30">		<!-- Button -->
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="75">		<!-- Order Number -->
																			<col align="center" width="50">		<!-- Line -->
																			<col align="center" width="120">	<!-- Item -->
																			<col align="center" width="140">	<!-- Item Description -->
																			<col align="center" width="40">		<!-- MT -->
																			<col align="center" width="40">		<!-- Sup -->
																			<col align="center" width="120">	<!-- Set Item -->
																			<col align="center" width="80">		<!-- Essencial Critical -->
																			<col align="center" width="40">		<!-- SS -->
																			<col align="center" width="80">		<!-- Back Order Qty -->
																			<col align="center" width="80">		<!-- Allocated Qty -->
																			<col align="center" width="80">		<!-- Current Available -->
																			<col align="center" width="140">	<!-- Serial -->
																			<col align="center" width="120">	<!-- Model -->
																			<col align="center" width="80">		<!-- Ship Config -->
																			<col align="center" width="80">		<!-- Pick Config -->
																			<col align="center" width="85">		<!-- Request Date -->
																			<col align="center" width="100">	<!-- Warehouse -->
																			<col align="center" width="80">		<!-- SWH -->
																			<col align="center" width="100">	<!-- Line Category -->
																			<col align="center" width="100">	<!-- Line Status -->
																		<% int i = 0; %>
																		<ezf:row ezfHyo="B">
																			<% NLBL3080_BBMsg ordLineMsg = bMsg.B.no(i++); %>
																			<tr height="28" id="id_leftB_row{EZF_ROW_NUMBER}">
																				<td>
																					<% boolean isSmryLine = "Y".equals(ordLineMsg.xxSmryLineFlg_B1.getValue()); %>
																					<% if (isSmryLine) { %>
																							<img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('ExpandHolds',{EZF_ROW_NUMBER})" ezfhyo="B" height="14" id="xxSmryLineFlg#{EZF_ROW_NUMBER}">
																					<% } else if (!isSmryLine) { %>
																							<img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('ContractHolds',{EZF_ROW_NUMBER})" ezfhyo="B" height="14" id="xxSmryLineFlg#{EZF_ROW_NUMBER}">
																					<% } %>
																				</td>
																				<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" onClick="sendServerForPreferredView('OnChange_ChkBoxCpoNum', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"OnChange_ChkBoxCpoNum{EZF_ROW_NUMBER}\""/></td>
																				<td><ezf:inputCheckBox name="xxChkBox_B2" ezfName="xxChkBox_B2" value="Y" ezfHyo="B" ezfArrayNo="0" onClick="sendServerForPreferredView('OnChange_ChkBoxCpoSlipNum', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"OnChange_ChkBoxCpoSlipNum{EZF_ROW_NUMBER}\""/></td>
																				<td><ezf:anchor name="" ezfName="" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_OrdEntry" otherAttr=" tabindex=\"-1\" id=\"Open_OrdEntry{EZF_ROW_NUMBER}\""><ezf:label name="cpoOrdNum_B1" ezfName="cpoOrdNum_B1" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></td>
																				<td><ezf:label name="dplyLineNum_B1" ezfName="dplyLineNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="mdseCd_B1" ezfName="mdseCd_B1" value="3200" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="mdseDescShortTxt_B1" ezfName="mdseDescShortTxt_B1" value="IR-ADV C2225 UL" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"250\" ezftoupper=\"\""/></td>
																				<td><ezf:label name="coaMdseTpCd_B1" ezfName="coaMdseTpCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:anchor name="xxAncrCtrlFlg_B1" ezfName="xxAncrCtrlFlg_B1" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SupdInvtySearch" otherAttr=" tabindex=\"-1\" id=\"OpenWin_SupdInvtySearch{EZF_ROW_NUMBER}\""><ezf:label name="xxSupdFlg_B1" ezfName="xxSupdFlg_B1" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></td>
																				<td><ezf:inputText name="setMdseCd_B1" ezfName="setMdseCd_B1" value="0377A006AA" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="backOrdImpctTpDescTxt_B1" ezfName="backOrdImpctTpDescTxt_B1" value="Essential" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"60\" ezftoupper=\"\""/></td>
																				<td><ezf:label name="stkStsCd_B1" ezfName="stkStsCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="ordQty_B1" ezfName="ordQty_B1" value="1" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align: right\" size=\"10\" maxlength=\"25\""/></td>
																				<td><ezf:inputText name="ordQty_B2" ezfName="ordQty_B2" value="0" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align: right\" size=\"10\" maxlength=\"28\""/></td>
																				<td><ezf:inputText name="invtyAvalQty_B1" ezfName="invtyAvalQty_B1" value="0" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align: right\" size=\"10\" maxlength=\"28\""/></td>
																				<td><ezf:inputText name="serNum_B1" ezfName="serNum_B1" value="SR38391014" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"30\""/></td>
																				<td><ezf:inputText name="t_MdlNm_B1" ezfName="t_MdlNm_B1" value="IR-ADV C2225 UL" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="svcConfigMstrPk_B1" ezfName="svcConfigMstrPk_B1" value="1000" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align: right\" size=\"10\" maxlength=\"50\""/></td>
																				<td><ezf:inputText name="pickSvcConfigMstrPk_B1" ezfName="pickSvcConfigMstrPk_B1" value="1000" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align: right\" size=\"10\" maxlength=\"50\""/></td>
																				<td><ezf:inputText name="rddDt_B1" ezfName="rddDt_B1" value="10/11/2015" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"20\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="rtlWhNm_B1" ezfName="rtlWhNm_B1" value="MONROE" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"10\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="rtlSwhCd_B1" ezfName="rtlSwhCd_B1" value="NEW" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="dsOrdLineCatgDescTxt_B1" ezfName="dsOrdLineCatgDescTxt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="ordLineStsNm_B1" ezfName="ordLineStsNm_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\""/></td>
																			</tr>
																			</ezf:row>
																			<ezf:skip>
																			</ezf:skip>
																		</table>
																	</div><!-- rightTbl -->
																</div> <!-- right table -->
															</div> <!-- parent box -->
															<script type="text/javascript" defer>
		   														 S21TableUI.initialize("parentBoxB", "BHEAD", "B", -1, true, true);
															</script>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col align="right" width="">
													<col align="right" width="5">
													<tr>
														<td>
															<ezf:inputButton name="Alloc_Cancel" value="Alloc Cancel" htmlClass="pBtn7" />
														</td>
														<td>&nbsp;</td>
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
