<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190508141541 --%>
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
			<input type="hidden" name="pageID" value="NWAL1570Scrn02">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Status Summary">
			<!-- Status Name -->
			<ezf:inputHidden name="xxDplyStsNm" ezfName="xxDplyStsNm" otherAttr=" id=\"xxDplyStsNm\""/>

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
										<li title="Location Info" class="pTab_ON"><a href="#">Order Inquiry</a></li>
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
					<%------------------------------------%>
					<%-- Save Option					--%>
					<%------------------------------------%>
					<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="152">
						<col width="345">
						<col width="110">
						<col width="400">
						<col width="">
						<tr>
							<td class="stab">Search Options</td>
							<td>
								<ezf:select name="srchOptPk_H1" ezfName="srchOptPk_H1" ezfBlank="1" ezfCodeName="srchOptPk_L1" ezfDispName="srchOptNm_L1" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
							</td>
							<td class="stab"></td>
							<td class="stab"></td>
							<td>
								<ezf:anchor name="reSearchOrder_LK" ezfName="reSearchOrder_LK" ezfEmulateBtn="1" ezfGuard="ReSearchOrder" otherAttr=" id=\"reSearchOrder_LK\" ezfanchortext=\"\"">Refresh</ezf:anchor>
							</td>
						</tr>
					</table>
					<hr style="height: 0px;" cellpadding="0">
					<table>
						<tr>
							<td>
<%-- #################################################### DETAIL ################################################### --%>
								<table width="100%">
									<col valign="top">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%" align="center">
												<tr>
													<td>
														<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
															<col align="left" width="350">
															<col align="left" width="">
															<col align="right" width="580">
															<tr>
																<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
																<td>&nbsp;</td>
																<td align="right">
																	<ezf:skip>
																	<table border="0" cellpadding="0" width="100%">
																		<tr>
																			<td align="left">
																				<table border="0" cellpadding="0" align="left" cellspacing="0">
																					<col>
																						<tr>
																						<td>Results 1999 - 1999 of 2000</td>
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
																			<div id='leftTbl' style="float:left; display:block; height:390px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
																			</div>
																		</div>  <!-- left table -->
																		<%-- LEFT-TABLE(TOP) --%>
																		<div style="float:left"> <!-- right table -->
																			<div id='rightTblHead' style="width:1080px; overflow-y:hidden; overflow-x:hidden;">
																				<table border="1" cellpadding="1" cellspacing="0" height="40" style="table-layout: fixed" id="SCRN02HEAD" width="4160px" style="margin-right:20px">
																					<col align="left" width="200">	<!-- Display By -->
																					<col align="center" width="120">	<!-- Pending Import -->
																					<col align="center" width="120">	<!-- Entered -->
																					<col align="center" width="120">	<!-- DI Hold -->
																					<col align="center" width="120">	<!-- Validation -->
																					<col align="center" width="120">	<!-- Profitability -->
																					<col align="center" width="120">	<!-- Credit Hold -->
																					<col align="center" width="120">	<!-- Supply Enforcement -->
																					<col align="center" width="120">	<!-- Pending Re Submission -->
																					<col align="center" width="120">	<!-- Awaiting Drop Ship -->
																					<col align="center" width="120">	<!-- Pending Re Allocation -->
																					<col align="center" width="120">	<!-- PO Cancelled -->
																					<col align="center" width="120">	<!-- Pending Fulfillment -->
																					<col align="center" width="120">	<!-- Pending Allocation -->
																					<col align="center" width="120">	<!-- Back Ordered -->
																					<col align="center" width="120">	<!-- Pending Pick -->
																					<col align="center" width="120">	<!-- Delivered to Shop -->
																					<col align="center" width="120">	<!-- In Shop/ Config -->
																					<col align="center" width="120">	<!-- Pending Shipment -->
																					<col align="center" width="120">	<!-- Shipped -->
																					<col align="center" width="120">	<!-- Pending Delivery Confirmation -->
																					<col align="center" width="120">	<!-- Pending Installation -->
																					<col align="center" width="120">	<!-- On Loan -->
																					<col align="center" width="120">	<!-- Waiting Receipt -->
																					<col align="center" width="120">	<!-- Pending Return -->
																					<col align="center" width="120">	<!-- Pending Inspection -->
																					<col align="center" width="120">	<!-- RWS Cancelled -->
																					<col align="center" width="120">	<!-- Partial Received -->
																					<col align="center" width="120">	<!-- Pending Invoice -->
																					<col align="center" width="120">	<!-- Billing Hold -->
																					<col align="center" width="120">	<!-- Pending Dealer Install -->
																					<col align="center" width="120">	<!-- Closed -->
																					<col align="center" width="120">	<!-- Closed Loan Return-->
																					<col align="center" width="120">	<!-- Closed Loan Sold-->
																					<col align="center" width="120">	<!-- Cancelled -->
																					<tr height="50">
																						<td id="AH0"  class="pClothBs">
																							<ezf:label name="dplyByItemLbNm_01" ezfName="dplyByItemLbNm_01" />
																							<span id="span_dplyByItemNm_02"><br>
																								<ezf:label name="dplyByItemLbNm_02" ezfName="dplyByItemLbNm_02" />
																							</span>
																							<span id="span_dplyByItemNm_03"><br>
																								<ezf:label name="dplyByItemLbNm_03" ezfName="dplyByItemLbNm_03" />
																							</span>
																						</td>
																						<td id="AH1"  class="pClothBs">Pending Import</td>
																						<td id="AH2"  class="pClothBs">Entered</td>
																						<td id="AH3"  class="pClothBs">DI Hold</td>
																						<td id="AH4"  class="pClothBs">Validation</td>
																						<td id="AH5"  class="pClothBs">Profitability</td>
																						<td id="AH6"  class="pClothBs">Credit Review</td>
																						<td id="AH7"  class="pClothBs">Supply Enforcement</td>
																						<td id="AH8"  class="pClothBs">Pending Re Submission</td>
																						<td id="AH9" class="pClothBs">Awaiting Drop Ship</td>
																						<td id="AH10" class="pClothBs">Pending Re Allocation</td>
																						<td id="AH11" class="pClothBs">PO Cancelled</td>
																						<td id="AH12" class="pClothBs">Pending Fulfillment</td>
																						<td id="AH13" class="pClothBs">Pending Allocation</td>
																						<td id="AH14" class="pClothBs">Back Ordered</td>
																						<td id="AH15" class="pClothBs">Pending Pick</td>
																						<td id="AH16" class="pClothBs">Delivered to Shop</td>
																						<td id="AH17" class="pClothBs">In Shop/ Config</td>
																						<td id="AH18" class="pClothBs">Pending Shipment</td>
																						<td id="AH19" class="pClothBs">Shipped</td>
																						<td id="AH20" class="pClothBs">Pending Delivery Confirmation</td>
																						<td id="AH21" class="pClothBs">Pending Installation</td>
																						<td id="AH22" class="pClothBs">On Loan</td>
																						<td id="AH23" class="pClothBs">Waiting Receipt</td>
																						<td id="AH24" class="pClothBs">Pending Return</td>
																						<td id="AH25" class="pClothBs">Pending Inspection</td>
																						<td id="AH26" class="pClothBs">RWS Cancelled</td>
																						<td id="AH27" class="pClothBs">Partial Received</td>
																						<td id="AH28" class="pClothBs">Pending Invoice</td>
																						<td id="AH29" class="pClothBs">Billing Hold</td>
																						<td id="AH30" class="pClothBs">Pending Dealer Install</td>
																						<td id="AH31" class="pClothBs">Closed</td>
																						<td id="AH32" class="pClothBs">Closed Loan Return</td>
																						<td id="AH33" class="pClothBs">Closed Loan Sold</td>
																						<td id="AH34" class="pClothBs">Cancelled</td>
																					</tr>
																				</table>
																			</div>
																			<div id="rightTBL" style="width:1097px; height:407px; display:block; overflow:scroll; margin:0px; padding:0px;" >
																				<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed"  width="4160px" >
																					<col align="left"  width="200">	<!-- Display By -->
																					<col align="right" width="120">	<!-- Pending Import -->
																					<col align="right" width="120">	<!-- Entered -->
																					<col align="right" width="120">	<!-- DI Hold -->
																					<col align="right" width="120">	<!-- Validation -->
																					<col align="right" width="120">	<!-- Profitability -->
																					<col align="right" width="120">	<!-- Credit Hold -->
																					<col align="right" width="120">	<!-- Supply Enforcement -->
																					<col align="right" width="120">	<!-- Pending Re Submission -->
																					<col align="right" width="120">	<!-- Awaiting Drop Ship -->
																					<col align="right" width="120">	<!-- Pending Re Allocation -->
																					<col align="right" width="120">	<!-- PO Cancelled -->
																					<col align="right" width="120">	<!-- Pending Fulfillment -->
																					<col align="right" width="120">	<!-- Pending Allocation -->
																					<col align="right" width="120">	<!-- Back Ordered -->
																					<col align="right" width="120">	<!-- Pending Pick -->
																					<col align="right" width="120">	<!-- Delivered to Shop -->
																					<col align="right" width="120">	<!-- In Shop/ Config -->
																					<col align="right" width="120">	<!-- Pending Shipment -->
																					<col align="right" width="120">	<!-- Shipped -->
																					<col align="right" width="120">	<!-- Pending Delivery Confirmation -->
																					<col align="right" width="120">	<!-- Pending Installation -->
																					<col align="right" width="120">	<!-- On Loan -->
																					<col align="right" width="120">	<!-- Waiting Receipt -->
																					<col align="right" width="120">	<!-- Pending Return -->
																					<col align="right" width="120">	<!-- Pending Inspection -->
																					<col align="right" width="120">	<!-- RWS Cancelled -->
																					<col align="right" width="120">	<!-- Partial Received -->
																					<col align="right" width="120">	<!-- Pending Invoice -->
																					<col align="right" width="120">	<!-- Billing Hold -->
																					<col align="right" width="120">	<!-- Pending Dealer Install -->
																					<col align="right" width="120">	<!-- Closed -->
																					<col align="right" width="120">	<!-- Closed Loan Return-->
																					<col align="right" width="120">	<!-- Closed Loan Sold-->
																					<col align="right" width="120">	<!-- Cancelled -->
																					<%
																						int i = 0;
																					%>
																					<ezf:row ezfHyo="A">
																					<%
																						String dispByCtrlAncrLvlCd = ((business.servlet.NWAL1570.NWAL1570Bean)databean).getXxDplyByCtrlAncrLvlCd_A1(i);
																						i =i +1;
																					%>
																						<tr height="20" id="id_leftA_row{EZF_ROW_NUMBER}">
																							<td style="text-align:left; vertical-align:text-top;" rowspan="2"><nobr>
																							<%if(dispByCtrlAncrLvlCd.equals("1")){%>
																								&nbsp;
																							<%}else if(dispByCtrlAncrLvlCd.equals("2")){%>
																								&nbsp;&nbsp;
																							<%}else if(dispByCtrlAncrLvlCd.equals("3")){%>
																								&nbsp;&nbsp;&nbsp;
																							<%}else if(dispByCtrlAncrLvlCd.equals("4")){%>
																								&nbsp;&nbsp;&nbsp;&nbsp;
																							<%}else{%>
																							<%}%>
																								<ezf:inputText name="xxDplyByItemNm_01" ezfName="xxDplyByItemNm_01" value="Overall Total" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; vertical-align:text-top; border:none;background-color:transparent;\" size=\"25\""/>
																							</nobr></td>

																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING IMPORT',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendImptSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendImptSmryCnt_A1" ezfName="xxPendImptSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('ENTERED',{EZF_ROW_NUMBER});" ezfanchortext id="xxEntSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxEntSmryCnt_A1" ezfName="xxEntSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('DI CHECK HOLD',{EZF_ROW_NUMBER});" ezfanchortext id="xxDiHldSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxDiHldSmryCnt_A1" ezfName="xxDiHldSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('VALIDATION HOLD',{EZF_ROW_NUMBER});" ezfanchortext id="xxVldSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxVldSmryCnt_A1" ezfName="xxVldSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PROFITABILITY HOLD',{EZF_ROW_NUMBER});" ezfanchortext id="xxPrftSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPrftSmryCnt_A1" ezfName="xxPrftSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('CREDIT REVIEW',{EZF_ROW_NUMBER});" ezfanchortext id="xxCrHldSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCrHldSmryCnt_A1" ezfName="xxCrHldSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('SUPPLY ENFORCEMENT HOLD',{EZF_ROW_NUMBER});" ezfanchortext id="xxSplyAbuseSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxSplyAbuseSmryCnt_A1" ezfName="xxSplyAbuseSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING RE-SUBMISSION',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendReSubmtSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendReSubmtSmryCnt_A1" ezfName="xxPendReSubmtSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('AWAITING DROP SHIP',{EZF_ROW_NUMBER});" ezfanchortext id="xxAwaitDropShipSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxAwaitDropShipSmryCnt_A1" ezfName="xxAwaitDropShipSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING RE-ALLOCATION',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendReAllocSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendReAllocSmryCnt_A1" ezfName="xxPendReAllocSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PO CANCELLED',{EZF_ROW_NUMBER});" ezfanchortext id="xxPoCancSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPoCancSmryCnt_A1" ezfName="xxPoCancSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING FULFILLMENT',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendFuflSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendFuflSmryCnt_A1" ezfName="xxPendFuflSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING ALLOCATION',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendAllocSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendAllocSmryCnt_A1" ezfName="xxPendAllocSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('BACK ORDER',{EZF_ROW_NUMBER});" ezfanchortext id="xxBoSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxBoSmryCnt_A1" ezfName="xxBoSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('WAITING PICK',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendPickSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendPickSmryCnt_A1" ezfName="xxPendPickSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('DELIVERED TO SHOP',{EZF_ROW_NUMBER});" ezfanchortext id="xxDelyToShopSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxDelyToShopSmryCnt_A1" ezfName="xxDelyToShopSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('IN SHOP/CONFIG',{EZF_ROW_NUMBER});" ezfanchortext id="xxInShopConfigSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxInShopConfigSmryCnt_A1" ezfName="xxInShopConfigSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING SHIP',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendShipSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendShipSmryCnt_A1" ezfName="xxPendShipSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('SHIPPED',{EZF_ROW_NUMBER});" ezfanchortext id="xxShipSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxShipSmryCnt_A1" ezfName="xxShipSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING DELIVERY CONFIRMATION',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendDelyConfSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendDelyConfSmryCnt_A1" ezfName="xxPendDelyConfSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING INSTALLATION',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendIstlSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendIstlSmryCnt_A1" ezfName="xxPendIstlSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('ON LOAN',{EZF_ROW_NUMBER});" ezfanchortext id="xxOnLoanSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxOnLoanSmryCnt_A1" ezfName="xxOnLoanSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('WAITING RECEIPT',{EZF_ROW_NUMBER});" ezfanchortext id="xxWaitRcptSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxWaitRcptSmryCnt_A1" ezfName="xxWaitRcptSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING RETURN',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendRtrnSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendRtrnSmryCnt_A1" ezfName="xxPendRtrnSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING INSPECTION',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendInspSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendInspSmryCnt_A1" ezfName="xxPendInspSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('RWS CANCELLED',{EZF_ROW_NUMBER});" ezfanchortext id="xxRwsCancSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxRwsCancSmryCnt_A1" ezfName="xxRwsCancSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PARTIAL RECEIVED',{EZF_ROW_NUMBER});" ezfanchortext id="xxPrtlRcvSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPrtlRcvSmryCnt_A1" ezfName="xxPrtlRcvSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING INVOICE',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendInvSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendInvSmryCnt_A1" ezfName="xxPendInvSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('BILLING HOLD',{EZF_ROW_NUMBER});" ezfanchortext id="xxBllgHldSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxBllgHldSmryCnt_A1" ezfName="xxBllgHldSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING DEALER INSTALL',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendDlrIstlSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendDlrIstlSmryCnt_A1" ezfName="xxPendDlrIstlSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('CLOSED',{EZF_ROW_NUMBER});" ezfanchortext id="xxCloSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCloSmryCnt_A1" ezfName="xxCloSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('CLOSED LOAN RETURN',{EZF_ROW_NUMBER});" ezfanchortext id="xxCloLoanRtrnSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCloLoanRtrnSmryCnt_A1" ezfName="xxCloLoanRtrnSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('CLOSED LOAN SOLD',{EZF_ROW_NUMBER});" ezfanchortext id="xxCloLoanSoldSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCloLoanSoldSmryCnt_A1" ezfName="xxCloLoanSoldSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('CANCELLED',{EZF_ROW_NUMBER});" ezfanchortext id="xxCancSmryCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCancSmryCnt_A1" ezfName="xxCancSmryCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>

																						</tr>
																						<tr height="20" id="id_leftA_amt_row{EZF_ROW_NUMBER}">

																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING IMPORT',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendImptSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendImptSmryAmt_A1" ezfName="xxPendImptSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('ENTERED',{EZF_ROW_NUMBER});" ezfanchortext id="xxEntSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxEntSmryAmt_A1" ezfName="xxEntSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('DI CHECK HOLD',{EZF_ROW_NUMBER});" ezfanchortext id="xxDiHldSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxDiHldSmryAmt_A1" ezfName="xxDiHldSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('VALIDATION HOLD',{EZF_ROW_NUMBER});" ezfanchortext id="xxVldSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxVldSmryAmt_A1" ezfName="xxVldSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PROFITABILITY HOLD',{EZF_ROW_NUMBER});" ezfanchortext id="xxPrftSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPrftSmryAmt_A1" ezfName="xxPrftSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('CREDIT REVIEW',{EZF_ROW_NUMBER});" ezfanchortext id="xxCrHldSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCrHldSmryAmt_A1" ezfName="xxCrHldSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('SUPPLY ENFORCEMENT HOLD',{EZF_ROW_NUMBER});" ezfanchortext id="xxSplyAbuseSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxSplyAbuseSmryAmt_A1" ezfName="xxSplyAbuseSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING RE-SUBMISSION',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendReSubmtSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendReSubmtSmryAmt_A1" ezfName="xxPendReSubmtSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('AWAITING DROP SHIP',{EZF_ROW_NUMBER});" ezfanchortext id="xxAwaitDropShipSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxAwaitDropShipSmryAmt_A1" ezfName="xxAwaitDropShipSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING RE-ALLOCATION',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendReAllocSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendReAllocSmryAmt_A1" ezfName="xxPendReAllocSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PO CANCELLED',{EZF_ROW_NUMBER});" ezfanchortext id="xxPoCancSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPoCancSmryAmt_A1" ezfName="xxPoCancSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING FULFILLMENT',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendFuflSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendFuflSmryAmt_A1" ezfName="xxPendFuflSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING ALLOCATION',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendAllocSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendAllocSmryAmt_A1" ezfName="xxPendAllocSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('BACK ORDER',{EZF_ROW_NUMBER});" ezfanchortext id="xxBoSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxBoSmryAmt_A1" ezfName="xxBoSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('WAITING PICK',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendPickSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendPickSmryAmt_A1" ezfName="xxPendPickSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('DELIVERED TO SHOP',{EZF_ROW_NUMBER});" ezfanchortext id="xxDelyToShopSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxDelyToShopSmryAmt_A1" ezfName="xxDelyToShopSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('IN SHOP/CONFIG',{EZF_ROW_NUMBER});" ezfanchortext id="xxInShopConfigSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxInShopConfigSmryAmt_A1" ezfName="xxInShopConfigSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING SHIP',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendShipSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendShipSmryAmt_A1" ezfName="xxPendShipSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('SHIPPED',{EZF_ROW_NUMBER});" ezfanchortext id="xxShipSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxShipSmryAmt_A1" ezfName="xxShipSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING DELIVERY CONFIRMATION',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendDelyConfSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendDelyConfSmryAmt_A1" ezfName="xxPendDelyConfSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING INSTALLATION',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendIstlSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendIstlSmryAmt_A1" ezfName="xxPendIstlSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('ON LOAN',{EZF_ROW_NUMBER});" ezfanchortext id="xxOnLoanSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxOnLoanSmryAmt_A1" ezfName="xxOnLoanSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('WAITING RECEIPT',{EZF_ROW_NUMBER});" ezfanchortext id="xxWaitRcptSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxWaitRcptSmryAmt_A1" ezfName="xxWaitRcptSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING RETURN',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendRtrnSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendRtrnSmryAmt_A1" ezfName="xxPendRtrnSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING INSPECTION',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendInspSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendInspSmryAmt_A1" ezfName="xxPendInspSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('RWS CANCELLED',{EZF_ROW_NUMBER});" ezfanchortext id="xxRwsCancSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxRwsCancSmryAmt_A1" ezfName="xxRwsCancSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PARTIAL RECEIVED',{EZF_ROW_NUMBER});" ezfanchortext id="xxPrtlRcvSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPrtlRcvSmryAmt_A1" ezfName="xxPrtlRcvSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING INVOICE',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendInvSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendInvSmryAmt_A1" ezfName="xxPendInvSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('BILLING HOLD',{EZF_ROW_NUMBER});" ezfanchortext id="xxBllgHldSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxBllgHldSmryAmt_A1" ezfName="xxBllgHldSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('PENDING DEALER INSTALL',{EZF_ROW_NUMBER});" ezfanchortext id="xxPendDlrIstlSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendDlrIstlSmryAmt_A1" ezfName="xxPendDlrIstlSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('CLOSED',{EZF_ROW_NUMBER});" ezfanchortext id="xxCloSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCloSmryAmt_A1" ezfName="xxCloSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('CLOSED LOAN RETURN',{EZF_ROW_NUMBER});" ezfanchortext id="xxCloLoanRtrnSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCloLoanRtrnSmryAmt_A1" ezfName="xxCloLoanRtrnSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('CLOSED LOAN SOLD',{EZF_ROW_NUMBER});" ezfanchortext id="xxCloLoanSoldSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCloLoanSoldSmryAmt_A1" ezfName="xxCloLoanSoldSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" onclick="selectStatus('CANCELLED',{EZF_ROW_NUMBER});" ezfanchortext id="xxCancSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCancSmryAmt_A1" ezfName="xxCancSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>

																						</tr>
																					</ezf:row>
																					<ezf:skip>
																						<tr height="20" id="id_leftA_row1-1">
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPendImptSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendImptSmryAmt_A1" ezfName="xxPendImptSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxEntSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxEntSmryAmt_A1" ezfName="xxEntSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxDiHldSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxDiHldSmryAmt_A1" ezfName="xxDiHldSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxVldSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxVldSmryAmt_A1" ezfName="xxVldSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPrftSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPrftSmryAmt_A1" ezfName="xxPrftSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxCrHldSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCrHldSmryAmt_A1" ezfName="xxCrHldSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxSplyAbuseSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxSplyAbuseSmryAmt_A1" ezfName="xxSplyAbuseSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPendReSubmtSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendReSubmtSmryAmt_A1" ezfName="xxPendReSubmtSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxBookSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxBookSmryAmt_A1" ezfName="xxBookSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxAwaitDropShipSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxAwaitDropShipSmryAmt_A1" ezfName="xxAwaitDropShipSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPendReAllocSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendReAllocSmryAmt_A1" ezfName="xxPendReAllocSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPoCancSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPoCancSmryAmt_A1" ezfName="xxPoCancSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPendAllocSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendAllocSmryAmt_A1" ezfName="xxPendAllocSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxBoSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxBoSmryAmt_A1" ezfName="xxBoSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPendPickSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendPickSmryAmt_A1" ezfName="xxPendPickSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxDelyToShopSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxDelyToShopSmryAmt_A1" ezfName="xxDelyToShopSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxInShopConfigSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxInShopConfigSmryAmt_A1" ezfName="xxInShopConfigSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPendShipSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendShipSmryAmt_A1" ezfName="xxPendShipSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxShipSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxShipSmryAmt_A1" ezfName="xxShipSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPendDelyConfSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendDelyConfSmryAmt_A1" ezfName="xxPendDelyConfSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPendIstlSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendIstlSmryAmt_A1" ezfName="xxPendIstlSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxWaitRcptSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxWaitRcptSmryAmt_A1" ezfName="xxWaitRcptSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPendRtrnSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendRtrnSmryAmt_A1" ezfName="xxPendRtrnSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPendInspSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendInspSmryAmt_A1" ezfName="xxPendInspSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxRwsCancSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxRwsCancSmryAmt_A1" ezfName="xxRwsCancSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPendInvSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendInvSmryAmt_A1" ezfName="xxPendInvSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxBllgHldSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxBllgHldSmryAmt_A1" ezfName="xxBllgHldSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxPendDlrIstlSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPendDlrIstlSmryAmt_A1" ezfName="xxPendDlrIstlSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxShipCloSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxShipCloSmryAmt_A1" ezfName="xxShipCloSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxInvdSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxInvdSmryAmt_A1" ezfName="xxInvdSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxCloSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCloSmryAmt_A1" ezfName="xxCloSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																							<td><a href="#" name="" ezfName="" ezfHyo="A" style="text-align:right; border:none;background-color:transparent;" onclick="sendServer('toInquiryByStatus')" ezfanchortext id="xxCancSmryAmt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCancSmryAmt_A1" ezfName="xxCancSmryAmt_A1" ezfHyo="A" ezfArrayNo="0" /></a></td>
																						</tr>
																						<tr height="20" id="id_leftA_row1-2">
																							<td><input style="text-align:left; border:none;background-color:transparent;" value=""></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																						</tr>
																						<tr height="20" id="id_leftA_row2-1">
																							<td><input style="text-align:left; border:none;background-color:transparent;" value="0000043 - BPR COPY SHOP INC."></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																						</tr>
																						<tr height="20" id="id_leftA_row2-2">
																							<td><input style="text-align:left; border:none;background-color:transparent;" value=""></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																						</tr>
																						<tr height="20" id="id_leftA_row3-1">
																							<td><input style="text-align:left; border:none;background-color:transparent;" value="1602600 - OMNICARE INC"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																						</tr>
																						<tr height="20" id="id_leftA_row3-2">
																							<td><input style="text-align:left; border:none;background-color:transparent;" value=""></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																						</tr>
																						<tr height="20" id="id_leftA_row_qty4-1">
																							<td><input style="text-align:left; border:none;background-color:transparent;" value="0000043 - BPR COPY SHOP INC."></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="5"></td>
																						</tr>
																						<tr height="20" id="id_leftA_row_amt4-2">
																							<td><input style="text-align:left; border:none;background-color:transparent;" value=""></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="15" value="100,000.00"></td>
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
	S21TableUI.initialize("parentBoxA", "SCRN02HEAD", "A", 1, true);

function selectStatus(selectStatusNm, i)
{
        document.getElementById("xxDplyStsNm").value = selectStatusNm;

        sendServer('toInquiryByStatus', i);
}

</script>

<%-- **** Task specific area ends here **** --%>
