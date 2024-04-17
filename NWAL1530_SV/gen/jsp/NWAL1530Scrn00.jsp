<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230418132957 --%>
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
			<input type="hidden" name="pageID" value="NWAL1530Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Order Shipping Infomation Screen">
<%@ page import="business.servlet.NWAL1530.NWAL1530BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<% NWAL1530BMsg bMsg = (NWAL1530BMsg)databean.getEZDBMsg(); %>

<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<%-- ######################################## HEADER ######################################## --%>
				<div class="pTab_BODY">
					<table border="0" width="99%" align="center">
						<col width="440">
						<col width="">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="70">
									<tr height="22">
										<td class="stab">Order#</td>
										<td colspan="3"><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="XXXXXXXX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td align="center"><ezf:inputButton name="Search_Order" value="Search" htmlClass="pBtn7" /></td>
									</tr>
									<tr height="25">
										<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div style="border-bottom:solid 1px #AAAAAA;padding-top:1px;margin-bottom:0;"></div>
				<br>

				<%-- ######################################## DETAIL ######################################## --%>
				<table width="98%">
					<col valign="top">
					<tr>
						<td>
							<div class="pTab_HEAD">
								<ul>
									<li id="ShippingDetail" title="Shipping Detail" class="pTab_ON" >
										<ezf:anchor name="" ezfName="xxTabProt_SD" ezfEmulateBtn="1" ezfGuard="TAB_ShpgDtl" >Shipping Detail</ezf:anchor>
									</li>
									<li id="SO" title="SO" class="pTab_OFF">
										<ezf:anchor name="" ezfName="xxTabProt_SO" ezfEmulateBtn="1" ezfGuard="TAB_So" >SO</ezf:anchor>
									</li>
									<li id="PO" title="PO" class="pTab_OFF">
										<ezf:anchor name="" ezfName="xxTabProt_PO" ezfEmulateBtn="1" ezfGuard="TAB_Po" >PO</ezf:anchor>
									</li>
									<li id="RWS" title="RWS" class="pTab_OFF">
										<ezf:anchor name="" ezfName="xxTabProt_RW" ezfEmulateBtn="1" ezfGuard="TAB_Rws" >RWS</ezf:anchor>
									</li>
									<li id="Invoice" title="Invoice" class="pTab_OFF">
										<ezf:anchor name="" ezfName="xxTabProt_IV" ezfEmulateBtn="1" ezfGuard="TAB_Inv" >Invoice</ezf:anchor>
									</li>
								</ul>
							</div>
							
							<c:choose>
								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++ Shipping Detail TAB +++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_ShpgDtl'}">
									<script type="text/javascript">
										document.getElementById( "ShippingDetail").className = "pTab_ON";
										document.getElementById( "SO").className = "pTab_OFF";
										document.getElementById( "PO").className = "pTab_OFF";
										document.getElementById( "RWS").className = "pTab_OFF";
										document.getElementById( "Invoice").className = "pTab_OFF";
									</script>
									<div class="pTab_BODY_In">
										<br>
										<table border ="0" cellpadding="1" cellspacing="0" width="100%">
											<col width="" align="" valign="top">
											<tr>
												<td align="left" valign="top">
													<div id="parentBoxA">
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
														<div style="float:left; display:block"> <!-- left table -->
															<div id="leftTblHead" style="display:block">
															</div>
															<div id="leftTbl"style="float:left; display:block; height:430; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
															</div>
														</div>  <!-- left table -->
													<%-- Right Table Header --%>
														<div style="float:left"> <!-- right table -->
															<div id="rightTblHead" style="overflow:hidden; width:1073; display:block;">
																<table border="1" cellpadding="1" cellspacing="0" id="AHEAD" style="margin-right:20px; table-layout :fixed;">
																	<col width="80" align="center">		<!-- Order -->
																	<col width="70" align="center">		<!-- Config -->
																	<col width="80" align="center">		<!-- Line Level-->
																	<col width="80" align="center">		<!-- Line -->
																	<col width="100" align="center">	<!-- Iten# -->
																	<col width="200" align="center">	<!-- Item Description -->
																	<col width="100" align="center">	<!-- Manufacture Item# -->
																	<col width="55" align="center">		<!-- Quantity -->
																	<col width="150" align="center">	<!-- Line Status -->
																	<col width="70" align="center">		<!-- SO -->
																	<col width="130" align="center">	<!-- PR -->
																	<col width="70" align="center">		<!-- PO -->
																	<col width="115" align="center">	<!-- RWS -->
																	<col width="115" align="center">	<!-- Invoice -->
																	<tr height="37">
																		<td id="AH0" class="pClothBs">Order#</td>
																		<td id="AH1" class="pClothBs">Config#</td>
																		<td id="AH2" class="pClothBs">Level</td>
																		<td id="AH3" class="pClothBs">Line#</td>
																		<td id="AH4" class="pClothBs">Item#</td>
																		<td id="AH5" class="pClothBs">Item Description</td>
																		<td id="AH6" class="pClothBs">Manufacture Item#</td>
																		<td id="AH7" class="pClothBs">QTY</td>
																		<td id="AH8" class="pClothBs">Line Status</td>
																		<td id="AH9" class="pClothBs">SO#</td>
																		<td id="AH10" class="pClothBs">PR#</td>
																		<td id="AH11" class="pClothBs">PO#</td>
																		<td id="AH12" class="pClothBs">RWS#</td>
																		<td id="AH13" class="pClothBs">Invoice#</td>
																	</tr>
																</table>
															</div>
															<%-- Right Table Detail --%>
															<div id="rightTbl" style="overflow:scroll; width:1090; height:430;">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
																	<col width="80">	<!-- Order -->
																	<col width="70">	<!-- Config -->
																	<col width="80" align="center">	<!-- Line Level -->
																	<col width="80">	<!-- Line -->
																	<col width="100">	<!-- Iten# -->
																	<col width="200">	<!-- Item Description -->
																	<col width="100">	<!-- Manufacture Item# -->
																	<col width="55" align="right">	<!-- Quantity -->
																	<col width="150">	<!-- Line Status -->
																	<col width="70">	<!-- SO -->
																	<col width="130">	<!-- PR -->
																	<col width="70">	<!-- PO -->
																	<col width="115">	<!-- RWS -->
																	<col width="115">	<!-- Invoice -->
																	<ezf:row ezfHyo="A">
																		<tr id="id_row{EZF_ROW_NUMBER}" height="28">
																			<td><ezf:label name="cpoOrdNum_A" ezfName="cpoOrdNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="dsOrdPosnNum_A" ezfName="dsOrdPosnNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="xxScrItem20Txt_A" ezfName="xxScrItem20Txt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="xxLineNum_A" ezfName="xxLineNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\" size=\"26\""/></td>
																			<td><ezf:label name="mnfItemCd_A" ezfName="mnfItemCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="ordQty_A" ezfName="ordQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="ordLineStsNm_A" ezfName="ordLineStsNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
																			
																			<td>
																				<ezf:anchor name="soNum_A" ezfName="soNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_So" otherAttr=" ezfanchortext=\"\"">
																				<ezf:label name="soNum_A" ezfName="soNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"soNum_A#{EZF_ROW_NUMBER}\""/>
																				</ezf:anchor>
																			</td>
																			<td>
																				<ezf:anchor name="prchReqNum_A" ezfName="prchReqNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_Pr" otherAttr=" ezfanchortext=\"\"">
																				<ezf:label name="prchReqNum_A" ezfName="prchReqNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"prchReqNum_A#{EZF_ROW_NUMBER}\""/>
																				</ezf:anchor>
																			</td>
																			<td>
																				<ezf:anchor name="poOrdNum_A" ezfName="poOrdNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_Po" otherAttr=" ezfanchortext=\"\"">
																				<ezf:label name="poOrdNum_A" ezfName="poOrdNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"poOrdNum_A#{EZF_ROW_NUMBER}\""/>
																				</ezf:anchor>
																			</td>
																			<td>
																				<ezf:anchor name="rwsNum_A" ezfName="rwsNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_Rws" otherAttr=" ezfanchortext=\"\"">
																				<ezf:label name="rwsNum_A" ezfName="rwsNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"rwsNum_A#{EZF_ROW_NUMBER}\""/>
																				</ezf:anchor>
																			</td>
																			<td>
																				<ezf:anchor name="invNum_A" ezfName="invNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_Inv" otherAttr=" ezfanchortext=\"\"">
																				<ezf:label name="invNum_A" ezfName="invNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"invNum_A#{EZF_ROW_NUMBER}\""/>
																				</ezf:anchor>
																			</td>
																			
																		</tr>
																	</ezf:row>
																	<ezf:skip>
																	</ezf:skip>
																</table>
															</div>
														</div>
													</div>
												</td>
											</tr>
										</table>
									</div>
								</c:when>

								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++ SO TAB ++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_So'}">
									<script type="text/javascript">
										document.getElementById( "ShippingDetail").className = "pTab_OFF";
										document.getElementById( "SO").className = "pTab_ON";
										document.getElementById( "PO").className = "pTab_OFF";
										document.getElementById( "RWS").className = "pTab_OFF";
										document.getElementById( "Invoice").className = "pTab_OFF";
									</script>
									<div class="pTab_BODY_In">
										<br>
										<table  border="0" cellpadding="0" cellspacing="0" width="100%">
											<col width="" align="" valign="top">
											<tr>
												<td align="left" valign="top">
													<div id="parentBoxB">
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
														<div style="float:left; display:block"> <!-- left table -->
															<div id="leftTblHead" style="display:block">
															</div>
															<div id="leftTbl"style="float:left; display:block; height:430; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
															</div>
														</div>  <!-- left table -->
													<%-- Right Table Header --%>
														<div style="float:left"> <!-- right table -->
															<div id="rightTblHead" style="overflow:hidden; width:1073; display:block;">
																<table border="1" cellpadding="1" cellspacing="0" id="BHEAD" style="margin-right:20px; table-layout :fixed;">
																	<col width="80"  align="center">	<!-- Order# -->
																	<col width="70"  align="center">	<!-- Config# -->
																	<col width="80"  align="center">	<!-- Line# -->
																	<col width="100" align="center">	<!-- Iten# -->
																	<col width="200" align="center">	<!-- Item Description -->
																	<col width="100" align="center">	<!-- Manufacture Item# -->
																	<col width="55"  align="center">	<!-- Quantity -->
																	<col width="150" align="center">	<!-- Line Status -->
																	<col width="70"  align="center">	<!-- SO# -->
																	<col width="180" align="center">	<!-- Serial# -->
																	<col width="180" align="center">	<!-- WH -->
																	<col width="200" align="center">	<!-- Ship Method -->
																	<col width="180" align="center">	<!-- Carrier -->
																	<col width="150" align="center">	<!-- CarrierAccoun# -->
																	<col width="220" align="center">	<!-- Tracking# -->
																	<col width="80" align="center">		<!-- RDD -->
																	<col width="100" align="center">	<!-- Schedule Delivery Date -->
																	<col width="80" align="center">		<!-- Shipped Date -->
																	<col width="150" align="center">	<!-- Delivery Confirmation -->
																	<tr height="37">
																		<td id="BH0" class="pClothBs">Order#</td>
																		<td id="BH1" class="pClothBs">Config#</td>
																		<td id="BH2" class="pClothBs">Line#</td>
																		<td id="BH3" class="pClothBs">Item#</td>
																		<td id="BH4" class="pClothBs">Item Description</td>
																		<td id="BH5" class="pClothBs">Manufacture Item#</td>
																		<td id="BH6" class="pClothBs">QTY</td>
																		<td id="BH7" class="pClothBs">Line Status</td>
																		<td id="BH8" class="pClothBs">SO#</td>
																		<td id="BH9" class="pClothBs">Serial#</td>
																		<td id="BH10" class="pClothBs">WH</td>
																		<td id="BH11" class="pClothBs">Ship Method</td>
																		<td id="BH12" class="pClothBs">Carrier</td>
																		<td id="BH13" class="pClothBs">Carrier<BR>Accoun#</td>
																		<td id="BH14" class="pClothBs">Tracking#</td>
																		<td id="BH15" class="pClothBs">RDD</td>
																		<td id="BH16" class="pClothBs">Schedule<BR>Delivery Date</td>
																		<td id="BH17" class="pClothBs">Shipped Date</td>
																		<td id="BH18" class="pClothBs">Delivery<BR>Confirmation</td>
																	</tr>
																</table>
															</div>
															<%-- Right Table Detail --%>
															<div id="rightTbl" style="overflow:scroll; width:1090; height:430;">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="B">
																	<col width="80">		<!-- Order# -->
																	<col width="70">		<!-- Config# -->
																	<col width="80">		<!-- Line# -->
																	<col width="100">		<!-- Iten# -->
																	<col width="200">		<!-- Item Description -->
																	<col width="100">		<!-- Manufacture Item# -->
																	<col width="55"  align="right">		<!-- Quantity -->
																	<col width="150">		<!-- Line Status -->
																	<col width="70">		<!-- SO# -->
																	<col width="180">		<!-- Serial# -->
																	<col width="180">		<!--  WH -->
																	<col width="200">		<!-- Ship Method -->
																	<col width="180">		<!-- Carrier -->
																	<col width="150">		<!-- CarrierAccoun# -->
																	<col width="220">		<!-- Tracking# -->
																	<col width="80">		<!-- RDD -->
																	<col width="100">		<!-- Schedule Delivery Date -->
																	<col width="80">		<!-- Shipped Date -->
																	<col width="150">		<!-- Delivery Confirmation -->
																	<% int indexB = -1; %>
																	<ezf:row ezfHyo="B">
																	<tr height="28">
																	<% indexB++; %>
																		<td><ezf:label name="cpoOrdNum_B" ezfName="cpoOrdNum_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="dsOrdPosnNum_B" ezfName="dsOrdPosnNum_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="xxLineNum_B" ezfName="xxLineNum_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="mdseCd_B" ezfName="mdseCd_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="mdseDescShortTxt_B" ezfName="mdseDescShortTxt_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\" size=\"26\""/></td>
																		<td><ezf:label name="mnfItemCd_B" ezfName="mnfItemCd_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="ordQty_B" ezfName="ordQty_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="ordLineStsNm_B" ezfName="ordLineStsNm_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td>
																			<ezf:anchor name="soNum_B" ezfName="soNum_B" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_So" otherAttr=" ezfanchortext=\"\"">
																			<ezf:label name="soNum_B" ezfName="soNum_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"soNum_B#{EZF_ROW_NUMBER}\""/>
																			</ezf:anchor>
																		</td>
																		<td>
																			<ezf:anchor name="serNum_LK" ezfName="serNum_LK" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_Ser" otherAttr=" id=\"serNum_LK\" ezfanchortext=\"\"">
																			<ezf:label name="serNum_B" ezfName="serNum_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"serNum_B#{EZF_ROW_NUMBER}\""/>
																			</ezf:anchor>
																		</td>
																		<td><ezf:label name="whCd_B" ezfName="whCd_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="frtCondNm_B" ezfName="frtCondNm_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="vndCd_B" ezfName="vndCd_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="carrAcctNum_B" ezfName="carrAcctNum_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td>
																			<% if (ZYPCommonFunc.hasValue(bMsg.B.no(indexB).carrTrkUrl_B)) { %>
																			<ezf:anchor name="carrTrkUrl_B" ezfName="carrTrkUrl_B" ezfHyo="B" ezfArrayNo="0" onClick="window.open( this.href, '_blank', 'status=yes, toolbar=yes, location=yes, menubar=yes, scrollbars=yes, resizable=yes');return false;" otherAttr=" id=\"carrTrk{EZF_ROW_NUMBER}\"">
																				<%=bMsg.B.no(indexB).proNum_B.getValue() %>
																			</ezf:anchor>
																			<% } else { %>
																			<ezf:label name="proNum_B" ezfName="proNum_B" ezfHyo="B" ezfArrayNo="0" />
																			<% } %>
																		</td>
																		<td><ezf:label name="rddDt_B" ezfName="rddDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="pddDt_B" ezfName="pddDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="actlShipDt_B" ezfName="actlShipDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="xxDtTm_B" ezfName="xxDtTm_B" ezfHyo="B" ezfArrayNo="0" /></td>
																	</tr>
																	</ezf:row>
																</table>
															</div>
														</div>
													</div>
												</td>
											</tr>
										</table>
									</div>
								</c:when>

								<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++ PO TAB +++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Po'}">
									<script type="text/javascript">
										document.getElementById( "ShippingDetail").className = "pTab_OFF";
										document.getElementById( "SO").className = "pTab_OFF";
										document.getElementById( "PO").className = "pTab_ON";
										document.getElementById( "RWS").className = "pTab_OFF";
										document.getElementById( "Invoice").className = "pTab_OFF";
									</script>
									<div class="pTab_BODY_In">
										<br>
										<table  border="0" cellpadding="0" cellspacing="0" width="100%">
											<col width="" align="" valign="top">
											<tr>
												<td align="left" valign="top">
													<div id="parentBoxC">
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
														<div style="float:left; display:block"> <!-- left table -->
															<div id="leftTblHead" style="display:block">
															</div>
															<div id="leftTbl"style="float:left; display:block; height:430; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
															</div>
														</div>  <!-- left table -->
													<%-- Right Table Header --%>
														<div style="float:left"> <!-- right table -->
															<div id="rightTblHead" style="overflow:hidden; width:1073; display:block;">
																<table border="1" cellpadding="1" cellspacing="0" id="CHEAD" style="margin-right:20px; table-layout :fixed;">
																	<col width="80" align="center">		<!-- Order# -->
																	<col width="70" align="center">		<!-- Config# -->
																	<col width="100" align="center">	<!-- Line# -->
																	<col width="100" align="center">	<!-- Iten# -->
																	<col width="200" align="center">	<!-- Item Description -->
																	<col width="100" align="center">	<!-- Manufacture Item# -->
																	<col width="55" align="center">		<!-- Quantity -->
																	<col width="150" align="center">	<!-- Line Status -->
																	<col width="80" align="center">		<!-- Requistion# -->
																	<col width="80" align="center">		<!-- PO# -->
																	<col width="480" align="center">	<!-- Vendor -->
																	<col width="80" align="center">		<!-- Vendor Order# -->
																	<col width="80" align="center">		<!-- Vendor PO# -->
																	<col width="180" align="center">	<!-- Vendor WH -->
																	<col width="180" align="center">	<!-- Ship To -->
																	<col width="200" align="center">	<!-- Freight Term -->
																	<col width="200" align="center">	<!-- Service Level -->
																	<col width="200" align="center">	<!-- Carrier -->
																	<col width="150" align="center">	<!-- Vendor Tracking# -->
																	<col width="150" align="center">	<!-- Vendor Order Status -->
																	<col width="80" align="center">		<!-- PSD -->
																	<col width="80" align="center">		<!-- PDD -->
																	<tr height="37">
																		<td id="CH0" class="pClothBs">Order#</td>
																		<td id="CH1" class="pClothBs">Config#</td>
																		<td id="CH2" class="pClothBs">Line#</td>
																		<td id="CH3" class="pClothBs">Item#</td>
																		<td id="CH4" class="pClothBs">Item Description</td>
																		<td id="CH5" class="pClothBs">Manufacture Item#</td>
																		<td id="CH6" class="pClothBs">QTY</td>
																		<td id="CH7" class="pClothBs">Line Status</td>
																		<td id="CH8" class="pClothBs">Requistion#</td>
																		<td id="CH9" class="pClothBs">PO#</td>
																		<td id="CH10" class="pClothBs">Vendor</td>
																		<td id="CH11" class="pClothBs">Vendor Order#</td>
																		<td id="CH12" class="pClothBs">Vendor PO#</td>
																		<td id="CH13" class="pClothBs">Vendor WH</td>
																		<td id="CH14" class="pClothBs">Ship To</td>
																		<td id="CH15" class="pClothBs">Freight Term</td>
																		<td id="CH16" class="pClothBs">Service Level</td>
																		<td id="CH17" class="pClothBs">Carrier</td>
																		<td id="CH18" class="pClothBs">Vendor Tracking#</td>
																		<td id="CH19" class="pClothBs">Vendor Order Status</td>
																		<td id="CH20" class="pClothBs">PSD</td>
																		<td id="CH21" class="pClothBs">PDD</td>
																	</tr>
																</table>
															</div>
															<%-- Right Table Detail --%>
															<div id="rightTbl" style="overflow:scroll; width:1090; height:430;">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="C">
																	<col width="80">		<!-- Order# -->
																	<col width="70">		<!-- Config# -->
																	<col width="100">		<!-- Line# -->
																	<col width="100">		<!-- Iten# -->
																	<col width="200">		<!-- Item Description -->
																	<col width="100">		<!-- Manufacture Item# -->
																	<col width="55" align="right">		<!-- Quantity -->
																	<col width="150">		<!-- Line Status -->
																	<col width="80">		<!-- Requistion# -->
																	<col width="80">		<!-- PO# -->
																	<col width="480">		<!-- Vendor -->
																	<col width="80">		<!-- Vendor Order# -->
																	<col width="80">		<!-- Vendor PO# -->
																	<col width="180">		<!-- Vendor WH -->
																	<col width="180">		<!-- Ship To -->
																	<col width="200">		<!-- Freight Term -->
																	<col width="200">		<!-- Service Level -->
																	<col width="200">		<!-- Carrier -->
																	<col width="150">		<!-- Vendor Tracking# -->
																	<col width="150">		<!-- Vendor Order Status# -->
																	<col width="80">		<!-- PSD# -->
																	<col width="80">		<!-- PDD# -->
																	<% int indexC = -1; %>
																	<ezf:row ezfHyo="C">
																	<tr height="28">
																		<% indexC++; %>
																		<td><ezf:label name="cpoOrdNum_C" ezfName="cpoOrdNum_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="dsOrdPosnNum_C" ezfName="dsOrdPosnNum_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="xxLineNum_C" ezfName="xxLineNum_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="mdseCd_C" ezfName="mdseCd_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="mdseDescShortTxt_C" ezfName="mdseDescShortTxt_C" ezfHyo="C" ezfArrayNo="0" otherAttr=" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\" size=\"26\""/></td>
																		<td><ezf:label name="mnfItemCd_C" ezfName="mnfItemCd_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="ordQty_C" ezfName="ordQty_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="ordLineStsNm_C" ezfName="ordLineStsNm_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td>
																			<ezf:anchor name="prchReqNum_C" ezfName="prchReqNum_C" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_Pr" otherAttr=" ezfanchortext=\"\"">
																			<ezf:label name="prchReqNum_C" ezfName="prchReqNum_C" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"prchReqNum_C#{EZF_ROW_NUMBER}\""/>
																			</ezf:anchor>
																		</td>
																		<td>
																			<ezf:anchor name="poOrdNum_C" ezfName="poOrdNum_C" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_Po" otherAttr=" ezfanchortext=\"\"">
																			<ezf:label name="poOrdNum_C" ezfName="poOrdNum_C" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"poOrdNum_C#{EZF_ROW_NUMBER}\""/>
																			</ezf:anchor>
																		</td>
																		<td><ezf:label name="dplyVndNm_C" ezfName="dplyVndNm_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="vndCpoOrdNum_C" ezfName="vndCpoOrdNum_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="vndIssPoOrdNum_C" ezfName="vndIssPoOrdNum_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="vndNm_C" ezfName="vndNm_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="shipToLocNm_C" ezfName="shipToLocNm_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="frtCondDescTxt_C" ezfName="frtCondDescTxt_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="shpgSvcLvlDescTxt_C" ezfName="shpgSvcLvlDescTxt_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="carrNm_C" ezfName="carrNm_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td>
																			<% if (ZYPCommonFunc.hasValue(bMsg.C.no(indexC).carrTrkUrl_C)) { %>
																			<ezf:anchor name="carrTrkUrl_C" ezfName="carrTrkUrl_C" ezfHyo="C" ezfArrayNo="0" onClick="window.open( this.href, '_blank', 'status=yes, toolbar=yes, location=yes, menubar=yes, scrollbars=yes, resizable=yes');return false;" otherAttr=" id=\"carrTrk{EZF_ROW_NUMBER}\"">
																				<%=bMsg.C.no(indexC).proNum_C.getValue() %>
																			</ezf:anchor>
																			<% } else { %>
																			<ezf:label name="proNum_C" ezfName="proNum_C" ezfHyo="C" ezfArrayNo="0" />
																			<% } %>
																		</td>
																		<td><ezf:label name="vndPoAckLineStsTxt_C" ezfName="vndPoAckLineStsTxt_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="psdDt_C" ezfName="psdDt_C" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:label name="pddDt_C" ezfName="pddDt_C" ezfHyo="C" ezfArrayNo="0" /></td>
																	</tr>
																	</ezf:row>
																</table>
															</div>
														</div>
													</div>
												</td>
											</tr>
										</table>
									</div>
								</c:when>
								
								<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++ RWS TAB ++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Rws'}">
									<script type="text/javascript">
										document.getElementById( "ShippingDetail").className = "pTab_OFF";
										document.getElementById( "SO").className = "pTab_OFF";
										document.getElementById( "PO").className = "pTab_OFF";
										document.getElementById( "RWS").className = "pTab_ON";
										document.getElementById( "Invoice").className = "pTab_OFF";
									</script>
									<div class="pTab_BODY_In">
										<br>
										<table  border="0" cellpadding="0" cellspacing="0" width="100%">
											<col width="" align="" valign="top">
											<tr>
												<td align="left" valign="top">
													<div id="parentBoxD">
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
														<div style="float:left; display:block"> <!-- left table -->
															<div id="leftTblHead" style="display:block">
															</div>
															<div id="leftTbl"style="float:left; display:block; height:430; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
															</div>
														</div>  <!-- left table -->
													<%-- Right Table Header --%>
														<div style="float:left"> <!-- right table -->
															<div id="rightTblHead" style="overflow:hidden; width:1073; display:block;">
																<table border="1" cellpadding="1" cellspacing="0" id="DHEAD" style="margin-right:20px; table-layout :fixed;">
																	<col width="85"  align="center">	<!-- Order# -->
																	<col width="70" align="center">		<!-- Config# -->
																	<col width="100" align="center">	<!-- Line# -->
																	<col width="100" align="center">	<!-- Iten# -->
																	<col width="200" align="center">	<!-- Item Description -->
																	<col width="100" align="center">	<!-- Manufacture Item# -->
																	<col width="55" align="center">		<!-- Quantity -->
																	<col width="120" align="center">	<!-- Return Quantity -->
																	<col width="155" align="center">	<!-- Line Status -->
																	<col width="180" align="center">	<!-- RWS# -->
																	<col width="180" align="center">	<!-- Request Pick Up Date -->
																	<col width="200" align="center">	<!-- Schedule PickUp -->
																	<col width="180" align="center">	<!-- Received Date -->
																	<tr height="37">
																		<td id="DH0" class="pClothBs">Order#</td>
																		<td id="DH1" class="pClothBs">Config#</td>
																		<td id="DH2" class="pClothBs">Line#</td>
																		<td id="DH3" class="pClothBs">Item#</td>
																		<td id="DH4" class="pClothBs">Item Description</td>
																		<td id="DH5" class="pClothBs">Manufacture Item#</td>
																		<td id="DH6" class="pClothBs">QTY</td>
																		<td id="DH7" class="pClothBs">Return Quantity</td>
																		<td id="DH8" class="pClothBs">Line Status</td>
																		<td id="DH9" class="pClothBs">RWS#</td>
																		<td id="DH10" class="pClothBs">Request Pick Up Date</td>
																		<td id="DH11" class="pClothBs">Schedule PickUp</td>
																		<td id="DH12" class="pClothBs">Received Date</td>
																	</tr>
																</table>
															</div>
															<%-- Right Table Detail --%>
															<div id="rightTbl" style="overflow:scroll; width:1090; height:430;">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="D">
																	<col width="85">		<!-- Order# -->
																	<col width="70">		<!-- Config# -->
																	<col width="100">		<!-- Line# -->
																	<col width="100">		<!-- Iten# -->
																	<col width="200">		<!-- Item Description -->
																	<col width="100">		<!-- Manufacture Item# -->
																	<col width="55" align="right">		<!-- Quantity -->
																	<col width="120" align="right">		<!-- Return Quantity -->
																	<col width="155">		<!-- Line Status -->
																	<col width="180">		<!-- RWS# -->
																	<col width="180">		<!-- Request Pick Up Date -->
																	<col width="200">		<!-- Schedule PickUp -->
																	<col width="180">		<!-- Received Date -->
																	<ezf:row ezfHyo="D">
																	<tr height="28">
																		<td><ezf:label name="cpoOrdNum_D" ezfName="cpoOrdNum_D" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td><ezf:label name="dsOrdPosnNum_D" ezfName="dsOrdPosnNum_D" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td><ezf:label name="xxLineNum_D" ezfName="xxLineNum_D" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td><ezf:label name="mdseCd_D" ezfName="mdseCd_D" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="mdseDescShortTxt_D" ezfName="mdseDescShortTxt_D" ezfHyo="D" ezfArrayNo="0" otherAttr=" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\" size=\"26\""/></td>
																		<td><ezf:label name="mnfItemCd_D" ezfName="mnfItemCd_D" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td><ezf:label name="ordQty_D" ezfName="ordQty_D" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td><ezf:label name="rtrnQty_D" ezfName="rtrnQty_D" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td><ezf:label name="ordLineStsNm_D" ezfName="ordLineStsNm_D" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td>
																			<ezf:anchor name="rwsNum_D" ezfName="rwsNum_D" ezfHyo="D" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_Rws" otherAttr=" ezfanchortext=\"\"">
																			<ezf:label name="rwsNum_D" ezfName="rwsNum_D" ezfHyo="D" ezfArrayNo="0" />
																			</ezf:anchor>
																		</td>
																		<td><ezf:label name="rqstPickUpDt_D" ezfName="rqstPickUpDt_D" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td><ezf:label name="schdPickUpDt_D" ezfName="schdPickUpDt_D" ezfHyo="D" ezfArrayNo="0" /></td>
																		<td><ezf:label name="rcvTsDplyTxt_D" ezfName="rcvTsDplyTxt_D" ezfHyo="D" ezfArrayNo="0" /></td>
																	</tr>
																	</ezf:row>
																	<ezf:skip>
																	</ezf:skip>
																</table>
															</div>
												</td>
											</tr>
										</table>
									</div>
								</c:when>
								
								<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++ Invoice TAB ++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Inv'}">
									<script type="text/javascript">
										document.getElementById( "ShippingDetail").className = "pTab_OFF";
										document.getElementById( "SO").className = "pTab_OFF";
										document.getElementById( "PO").className = "pTab_OFF";
										document.getElementById( "RWS").className = "pTab_OFF";
										document.getElementById( "Invoice").className = "pTab_ON";
									</script>
									<div class="pTab_BODY_In">
										<br>
										<table border ="0" cellpadding="1" cellspacing="0" width="100%">
											<tr>
												<td align="top">
													<div id="TopTBL" style="overflow-y:none; overflow-x:none; width:410;">
														<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
															<col width="90" align="center">		<!-- Order# -->
															<col width="120" align="center">	<!-- Invoice# -->
															<col width="120" align="center">	<!-- Amount -->
															<col width="80" align="center">		<!-- Date -->
															<col width="120" align="center">    <!-- Balance -->
															<tr height="37">
																<td class="pClothBs">Order#</td>
																<td class="pClothBs">Invoice#</td>
																<td class="pClothBs">Amount</td>
																<td class="pClothBs">Date</td>
																<td class="pClothBs">Balance</td>
															</tr>
														</table>
													</div>
													<div id="TBL" style="overflow-y:scroll; overflow-x:none; height:440; width:427; word-break: break-all;">
														<table border="1" cellpadding="1" cellspacing="0" id="E" style="table-layout: fixed;">
															<col width="90">		<!-- Order# -->
															<col width="120">		<!-- Invoice# -->
															<col width="120" align="right">		<!-- Amount -->
															<col width="80">		<!-- Date -->
															<col width="120" align="right">     <!-- Balance -->
															<ezf:row ezfHyo="E">
																<tr id="id_row{EZF_ROW_NUMBER}" height="28">
																	<td><ezf:label name="cpoOrdNum_E" ezfName="cpoOrdNum_E" ezfHyo="E" ezfArrayNo="0" /></td>
																	<td>
																		<ezf:anchor name="invNum_E" ezfName="invNum_E" ezfHyo="E" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_Inv" otherAttr=" ezfanchortext=\"\"">
																		<ezf:label name="invNum_E" ezfName="invNum_E" ezfHyo="E" ezfArrayNo="0" otherAttr=" id=\"invNum_E#{EZF_ROW_NUMBER}\""/>
																		</ezf:anchor>
																	</td>
																	<td><ezf:label name="invTotFuncNetAmt_E" ezfName="invTotFuncNetAmt_E" ezfHyo="E" ezfArrayNo="0" /></td>
																	<td><ezf:label name="invDt_E" ezfName="invDt_E" ezfHyo="E" ezfArrayNo="0" /></td>
																	<td><ezf:label name="dealRmngBalGrsAmt_E" ezfName="dealRmngBalGrsAmt_E" ezfHyo="E" ezfArrayNo="0" /></td>
																</tr>
															</ezf:row>
															<ezf:skip>
															</ezf:skip>
														</table>
													</div>	
												</td>
											</tr>
										</table>
									</div>
								</c:when>
							</c:choose>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<script type="text/javascript" defer>
<c:choose>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_ShpgDtl'}">
	S21TableUI.initialize("parentBoxA", "AHEAD", "A");
</c:when>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_So'}">
	S21TableUI.initialize("parentBoxB", "BHEAD", "B");
</c:when>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Po'}">
	S21TableUI.initialize("parentBoxC", "CHEAD", "C");
</c:when>
<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Rws'}">
	S21TableUI.initialize("parentBoxD", "DHEAD", "D");
</c:when>
</c:choose>
</script>

<%-- **** Task specific area ends here **** --%>
