<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161006103716 --%>
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
			<input type="hidden" name="pageID" value="NLBL3050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Distribution Coordinator Work Bench">

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
													<li title="Location Info" class="pTab_ON"><a href="#">Coord Bench</a></li>
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
								<table border="0" cellpadding="0" cellspacing="0" height="">
									<col align="left" width="5">
									<col align="left" width="115">
									<col align="left" width="150">
									<col align="left" width="75">
									<col align="left" width="150">
									<col align="left" width="75">
									<col align="left" width="320">
									<col align="left" width="100">
									<tr colspan = "8">
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Sales Order Number(*)</td>
										<td><ezf:inputText name="cpoNum_H" ezfName="cpoNum_H" value="XX" otherAttr=" size=\"16\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_WH" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfo" >Warehouse(*)</ezf:anchor></td>
										<td><ezf:inputText name="xxRtlWhSrchTxt_H" ezfName="xxRtlWhSrchTxt_H" value="12345678" otherAttr=" size=\"16\" maxlength=\"1000\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_CP" ezfEmulateBtn="1" ezfGuard="OpenWin_CoordSearchCode" >Coordinator(*)</ezf:anchor></td>
										<td>
											<ezf:inputText name="psnCd_H" ezfName="psnCd_H" value="Q99999" otherAttr=" size=\"16\" maxlength=\"8\" ezftoupper=\"\""/>
											<ezf:inputButton name="Search_CoordInfo" value=">>" htmlClass="pBtn1" />
											<ezf:inputText name="xxPsnFirstMidLastNmt_H" ezfName="xxPsnFirstMidLastNm_H" value="12345678" otherAttr=" size=\"16\" maxlength=\"90\" ezftoupper=\"\""/>
										</td>
										<td class="stab"><ezf:inputButton name="Search" value="Refresh" htmlClass="pBtn6" /></td>
									</tr>
								</table>

<%-- #################################################### DETAIL ################################################### --%>
<%@ page import="business.servlet.NLBL3050.NLBL3050BMsg" %>
<%@ page import="business.servlet.NLBL3050.NLBL3050_BBMsg" %>
<%  NLBL3050BMsg bMsg = (NLBL3050BMsg)databean.getEZDBMsg(); %>

								<hr style="height: 0px;" cellpadding="0">
								<div id="headerTBL" style="word-break:break-all; scroll; height:520; overflow-y:scroll;" onScroll="setScrollPositionHdr();">
								<table border="0" cellpadding="0" width="100%">
									<tr>
										<%-- ####################### ORDERS AVAILABLE TO SCHEDULE (A) ####################### --%>
										<td valign="top">
											<fieldset>
												<legend>
													<table>
														<tr>
															<td class="stab">ORDERS AVAILABLE TO SCHEDULE : </td>
															<td><ezf:label name="xxTotCnt_A" ezfName="xxTotCnt_A" /></td>
														</tr>
													</table>
												</legend>
												<table border="0" cellpadding="0" cellspacing="0" width="100%">
													<tr height="24">
														<td align="left" valign="center">
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="5"></td>
																	<td class="stab"><ezf:inputButton name="Search_Today" value="Today's Task" htmlClass="pBtn6" /></td>
																	<td width="5"></td>
																	<td class="stab"><ezf:inputButton name="Search_All" value="All Task" htmlClass="pBtn6" /></td>
																</tr>
															</table>
														</td>
														<td align="right" valign="center">
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td>
																		<table border="0" cellpadding="0" cellspacing="0">
																			<col width="35">
																			<col width="50">
																			<col width="58">
																			<col width="44">
																			<col width="51">
																			<tr>
																				<td class="stab">Total:</td>
																				<td class="stab">Outbound</td>
																				<td><ezf:inputText name="xxTotCnt_OA" ezfName="xxTotCnt_OA" value="10" otherAttr=" size=\"6\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align:right;\""/></td>
																				<td class="stab">Inbound</td>
																				<td><ezf:inputText name="xxTotCnt_IA" ezfName="xxTotCnt_IA" value="10" otherAttr=" size=\"6\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align:right;\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</td>
														<td width="17"></td>
													</tr>
												</table>
												<div>
												<table border="0" cellpadding="0" cellspacing="0">
													<tr style="vertical-align=top;">
														<td>
															<!-- Left TBL Header -->
															<table border="1" cellpadding="1" cellspacing="0">
																<col align="center" width="90">	<!-- Sales Order# -->
																<tr height="26">
																	<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxTblSortColNm_AS' )" id="xxTblSortColNm_AS" >Sales Order#<img id="sortIMG.xxTblSortColNm_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																</tr>
															</table>
															<!-- Left TBL Main -->
															<div id="leftTBLA" style="overflow-y:hidden; height:170; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBLA'));">
																<table border="1" cellpadding="1" cellspacing="0" width="" id="A1">
																	<col align="left" width="90">	<!-- Sales Order# -->
																	<tbody>
																		<% int indexA = 0; %>
																		<ezf:row ezfHyo="A">
																		<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																			<% if ("O".equals(bMsg.A.no(indexA).trxHdrNumTpCd_A1.getValue()) && bMsg.A.no(indexA).xxTotCnt_AO.getValueInt() == 0) { %>
																			<td align="left"><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_OrdEntryRma" otherAttr=" ezfanchortext=\"\" id=\"trxHdrNum_A1{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_A1" ezfName="trxHdrNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																			<% } else if ("O".equals(bMsg.A.no(indexA).trxHdrNumTpCd_A1.getValue())) { %>
																			<td align="left"><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_OrdEntry" otherAttr=" ezfanchortext=\"\" id=\"trxHdrNum_A1{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_A1" ezfName="trxHdrNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																			<% } else if ("S".equals(bMsg.A.no(indexA).trxHdrNumTpCd_A1.getValue())) { %>
																			<td align="center"><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngDely" otherAttr=" ezfanchortext=\"\" id=\"trxHdrNum_A1{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_A1" ezfName="trxHdrNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																			<% } else if ("R".equals(bMsg.A.no(indexA).trxHdrNumTpCd_A1.getValue())) { %>
																			<td align="center"><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngRMA" otherAttr=" ezfanchortext=\"\" id=\"trxHdrNum_A1{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_A1" ezfName="trxHdrNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																			<% } else { %>
																			<td>&nbsp;</td>
																			<% } %>
																		</tr>
																		<% indexA++; %>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="28">
																			<td align="center"><a href="#" ezfhyo="A" onclick="sendServer('Open_OrdEntry')" tabindex="-1" id="trxHdrNum_A1{EZF_ROW_NUMBER}" ><label>SO000001</label></a></td>
																		</tr>
																		<tr height="28">
																			<td align="center"><a href="#" ezfhyo="A" onclick="sendServer('Open_OrdEntry')" tabindex="-1" id="trxHdrNum_A1{EZF_ROW_NUMBER}" ><label>RWS00001</label></a></td>
																		</tr>
																		<tr height="28">
																			<td align="center"><a href="#" ezfhyo="A" onclick="sendServer('Open_OrdEntry')" tabindex="-1" id="trxHdrNum_A1{EZF_ROW_NUMBER}" ><label>RWS00002</label></a></td>
																		</tr>
																		<tr height="28">
																			<td><label>CP002000</label></td>
																		</tr>
																		<tr height="28">
																			<td align="center"><a href="#" ezfhyo="A" onclick="sendServer('Open_OrdEntry')" tabindex="-1" id="trxHdrNum_A1{EZF_ROW_NUMBER}" ><label>SO000002</label></a></td>
																		</tr>
																		<tr height="28">
																			<td align="center"><a href="#" ezfhyo="A" onclick="sendServer('Open_OrdEntry')" tabindex="-1" id="trxHdrNum_A1{EZF_ROW_NUMBER}" ><label>RWS00003</label></a></td>
																		</tr>
																		</ezf:skip>
																	</tbody>
																</table>
															</div>
														</td>
														<td>
															<!-- Right TBL Header -->
															<div id="rightTopTBLA" style="overflow-y:none; overflow-x:hidden; width=430" onScroll="synchroScrollLeft(this.id, new Array('rightTBLA'));">
																<table border="1" cellpadding="1" cellspacing="0" width="1050">
																	<col align="center" width="80">		<!-- Order Aging -->
																	<col align="center" width="120">	<!-- Scheduling Status -->
																	<col align="center" width="189">	<!-- Customer Name -->
																	<col align="center" width="189">	<!-- Address -->
																	<col align="center" width="120">	<!-- City -->
																	<col align="center" width="55">		<!-- State -->
																	<col align="center" width="100">	<!-- Total Outbound -->
																	<col align="center" width="100">	<!-- Total Inbound -->
																	<tr height="26">
																		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cutOffAot_AS' )" id="cutOffAot_AS">Order Aging<img id="sortIMG.cutOffAot_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs">Scheduling Status</td>
																		<td class="pClothBs">Customer Name</td>
																		<td class="pClothBs">Address Line 1</td>
																		<td class="pClothBs">City</td>
																		<td class="pClothBs">State</td>
																		<td class="pClothBs">Total Outbound</td>
																		<td class="pClothBs">Total Inbound</td>
																	</tr>
																</table>
															</div>
															<!-- Right TBL Main -->
															<div id="rightTBLA" style="overflow:scroll; height:187; width:447" onScroll="setScrollPositionA(); synchroScrollTop(this.id, new Array('leftTBLA')); onScroll=synchroScrollLeft(this.id, new Array('rightTopTBLA'));">
																<table border="1" cellpadding="1" cellspacing="0" width="1050" id="A2">
																	<col align="right"  width="80">		<!-- Order Aging -->
																	<col align="center" width="120">	<!-- Scheduling Status -->
																	<col align="center" width="189">	<!-- Customer Name -->
																	<col align="center" width="189">	<!-- Address -->
																	<col align="center" width="120">	<!-- City -->
																	<col align="center" width="55">		<!-- State -->
																	<col align="right"  width="100">	<!-- Total Outbound -->
																	<col align="right"  width="100">	<!-- Total Inbound -->
																	<tbody>
																		<ezf:row ezfHyo="A">
																		<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																			<td><ezf:inputText name="xxOrdTs_A1" ezfName="xxOrdTs_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" id=\"xxOrdTs_A1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="delyCoordStsDescTxt_A1" ezfName="delyCoordStsDescTxt_A1" value="Awaiting Scheduling" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\" id=\"delyCoordStsDescTxt_A1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToAcctNm_A1" ezfName="shipToAcctNm_A1" value="HEWLETT PACKARD CO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"360\" id=\"shipToAcctNm_A1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToAddr_A1" ezfName="shipToAddr_A1" value="55 MAIN STREET" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"60\" id=\"shipToAddr_A1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToCtyAddr_A1" ezfName="shipToCtyAddr_A1" value="PORT WASHINGTON" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\" id=\"shipToCtyAddr_A1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToStCd_A1" ezfName="shipToStCd_A1" value="NY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"60\" id=\"shipToStCd_A1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:center;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="xxTotCnt_AO" ezfName="xxTotCnt_AO" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"60\" id=\"xxTotCnt_AO{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="xxTotCnt_AI" ezfName="xxTotCnt_AI" value="2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"60\" id=\"xxTotCnt_AI{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\" ezftoupper=\"\""/></td>
																		</tr>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																			<td><input type="text" size="10" maxlength="30"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="50"  value="Awaiting Scheduling" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="360" value="HEWLETT PACKARD CO" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="60"  value="55 MAIN STREET" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="60"  value="PORT WASHINGTON" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="6"  maxlength="60"  value="NY" style="border:none;background-color:transparent;padding:0px;text-align:center;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																		</tr>
																		<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																			<td><input type="text" size="10" maxlength="30"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="50"  value="Awaiting Scheduling" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="360" value="HEWLETT PACKARD CO" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="60"  value="55 MAIN STREET" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="60"  value="PORT WASHINGTON" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="6"  maxlength="60"  value="NY" style="border:none;background-color:transparent;padding:0px;text-align:center;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																		</tr>
																		<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																			<td><input type="text" size="10" maxlength="30"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="50"  value="Awaiting Scheduling" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="360" value="HEWLETT PACKARD CO" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="60"  value="55 MAIN STREET" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="60"  value="PORT WASHINGTON" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="6"  maxlength="60"  value="NY" style="border:none;background-color:transparent;padding:0px;text-align:center;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																		</tr>
																		<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																			<td><input type="text" size="10" maxlength="30"  value="1" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="50"  value="" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="360" value="" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="6"  maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:center;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="1" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="1" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																		</tr>
																		<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																			<td><input type="text" size="10" maxlength="30"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="50"  value="Awaiting Scheduling" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="360" value="HEWLETT PACKARD CO" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="60"  value="55 MAIN STREET" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="60"  value="PORT WASHINGTON" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="6"  maxlength="60"  value="NY" style="border:none;background-color:transparent;padding:0px;text-align:center;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																		</tr>
																		<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
																			<td><input type="text" size="10" maxlength="30"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="50"  value="Awaiting Customer Commitment" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="360" value="OMNICARE INC" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="60"  value="99 MAIN STREET" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="60"  value="PORT WASHINGTON" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="6"  maxlength="60"  value="NY" style="border:none;background-color:transparent;padding:0px;text-align:center;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																		</tr>
																		</ezf:skip>
																	</tbody>
																</table>
															</div>
														</td>
													</tr>
												</table>
												</div>
											</fieldset>
										</td>
										<%-- ####################### ORDERS NOT AVAILABLE TO SCHEDULE (B) ####################### --%>
										<td valign="top">
											<fieldset>
												<legend>
													<table>
														<tr>
															<td class="stab">ORDERS NOT AVAILABLE TO SCHEDULE : </td>
															<td><ezf:label name="xxTotCnt_B" ezfName="xxTotCnt_B" /></td>
														</tr>
													</table>
												</legend>
												<table border="0" cellpadding="0" cellspacing="0" width="100%">
													<tr height="24">
														<td align="right" valign="center">
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td>
																		<table border="0" cellpadding="0" cellspacing="0">
																			<col width="35">
																			<col width="50">
																			<col width="58">
																			<col width="44">
																			<col width="51">
																			<tr>
																				<td class="stab">Total:</td>
																				<td class="stab">Outbound</td>
																				<td><ezf:inputText name="xxTotCnt_OB" ezfName="xxTotCnt_OB" value="10" otherAttr=" size=\"6\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align:right;\""/></td>
																				<td class="stab">Inbound</td>
																				<td><ezf:inputText name="xxTotCnt_IB" ezfName="xxTotCnt_IB" value="10" otherAttr=" size=\"6\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align:right;\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</td>
														<td width="17"></td>
													</tr>
												</table>
												<div>
												<table border="0" cellpadding="0" cellspacing="0">
													<tr style="vertical-align=top;">
														<td>
															<!-- Left TBL Header -->
															<table border="1" cellpadding="1" cellspacing="0">
																<col align="center" width="90">	<!-- Sales Order# -->
																<tr height="26">
																	<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'trxHdrNum_B1' )" id="trxHdrNum_B1">Sales Order#<img id="sortIMG.trxHdrNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																</tr>
															</table>
															<!-- Left TBL Main -->
															<div id="leftTBLB" style="overflow-y:hidden; height:170; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBLB'));">
																<table border="1" cellpadding="1" cellspacing="0" width="" id="B1">
																	<col align="left" width="90">	<!-- Sales Order# -->
																	<tbody>
																		<% int indexB = 0; %>
																		<ezf:row ezfHyo="B">
																		<tr height="28" id="id_leftB_row{EZF_ROW_NUMBER}">
																			<% if ("Y".equals(bMsg.B.no(indexB).xxAncrCtrlFlg_B1.getValue())) { %>
																			<td><ezf:anchor name="xxAncrCtrlFlg_B1" ezfName="xxAncrCtrlFlg_B1" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngBO" otherAttr=" ezfanchortext=\"\" id=\"trxHdrNum_B1{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_B1" ezfName="trxHdrNum_B1" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></td>
																			<% } else { %>
																			<td><ezf:label name="trxHdrNum_B1" ezfName="trxHdrNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																			<% } %>
																		</tr>
																		<% ++indexB; %>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="28">
																			<td><a href="#"><label>CP002000</label></a></td>
																		</tr>
																		</ezf:skip>
																	</tbody>
																</table>
															</div>
														</td>
														<td>
															<!-- Right TBL Header -->
															<div id="rightTopTBLB" style="overflow-y:none; overflow-x:hidden; width=430" onScroll="synchroScrollLeft(this.id, new Array('rightTBLB'));">
																<table border="1" cellpadding="1" cellspacing="0" width="1050">
																	<col align="center" width="80">		<!-- Order Aging -->
																	<col align="center" width="120">	<!-- Scheduling Status -->
																	<col align="center" width="189">	<!-- Customer Name -->
																	<col align="center" width="189">	<!-- Address -->
																	<col align="center" width="120">	<!-- City -->
																	<col align="center" width="55">		<!-- State -->
																	<col align="center" width="100">	<!-- Total Outbound -->
																	<col align="center" width="100">	<!-- Total Inbound -->
																	<tr height="26">
																		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'cutOffAot_BS' )" id="cutOffAot_BS">Order Aging<img id="sortIMG.cutOffAot_BS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		<td class="pClothBs">Scheduling Status</td>
																		<td class="pClothBs">Customer Name</td>
																		<td class="pClothBs">Address Line 1</td>
																		<td class="pClothBs">City</td>
																		<td class="pClothBs">State</td>
																		<td class="pClothBs">Total Outbound</td>
																		<td class="pClothBs">Total Inbound</td>
																	</tr>
																</table>
															</div>
															<!-- Right TBL Main -->
															<div id="rightTBLB" style="overflow:scroll; height:187; width:447" onScroll="setScrollPositionB(); synchroScrollTop(this.id, new Array('leftTBLB')); onScroll=synchroScrollLeft(this.id, new Array('rightTopTBLB'));">
																<table border="1" cellpadding="1" cellspacing="0" width="1050" id="B2">
																	<col align="right"  width="80">		<!-- Order Aging -->
																	<col align="center" width="120">	<!-- Scheduling Status -->
																	<col align="center" width="189">	<!-- Customer Name -->
																	<col align="center" width="189">	<!-- Address -->
																	<col align="center" width="120">	<!-- City -->
																	<col align="center" width="55">		<!-- State -->
																	<col align="right"  width="100">	<!-- Total Outbound -->
																	<col align="right"  width="100">	<!-- Total Inbound -->
																	<tbody>
																		<ezf:row ezfHyo="B">
																		<tr height="28" id="id_leftB_row{EZF_ROW_NUMBER}">
																			<td><ezf:inputText name="xxOrdTs_B1" ezfName="xxOrdTs_B1" value="1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" id=\"xxOrdTs_B1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="delyCoordStsDescTxt_B1" ezfName="delyCoordStsDescTxt_B1" value="Awaiting Backorder Fulfillment" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\" id=\"delyCoordStsDescTxt_B1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToAcctNm_B1" ezfName="shipToAcctNm_B1" value="HEWLETT PACKARD CO" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"360\" id=\"shipToAcctNm_B1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToAddr_B1" ezfName="shipToAddr_B1" value="55 MAIN STREET" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"60\" id=\"shipToAddr_B1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToCtyAddr_B1" ezfName="shipToCtyAddr_B1" value="PORT WASHINGTON" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\" id=\"shipToCtyAddr_B1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToStCd_B1" ezfName="shipToStCd_B1" value="NY" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"60\" id=\"shipToStCd_B1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:center;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="xxTotCnt_BO" ezfName="xxTotCnt_BO" value="1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"60\" id=\"xxTotCnt_BO{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="xxTotCnt_BI" ezfName="xxTotCnt_BI" value="0" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"60\" id=\"xxTotCnt_BI{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\" ezftoupper=\"\""/></td>
																		</tr>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="28">
																			<td><input type="text" size="10" maxlength="30"  value="1" ezfhyo="B" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="50"  value="Awaiting Backorder Fulfillment" ezfhyo="B" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="360" value="HEWLETT PACKARD CO" ezfhyo="B" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="26" maxlength="60"  value="55 MAIN STREET" ezfhyo="B" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="16" maxlength="60"  value="PORT WASHINGTON" ezfhyo="B" style="border:none;background-color:transparent;padding:0px;" ezftoupper ></td>
																			<td><input type="text" size="6"  maxlength="60"  value="NY" ezfhyo="B" style="border:none;background-color:transparent;padding:0px;text-align:center;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="2" ezfhyo="B" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																			<td><input type="text" size="13" maxlength="60"  value="1" ezfhyo="B" style="border:none;background-color:transparent;padding:0px;text-align:right;" ezftoupper ></td>
																		</tr>
																		</ezf:skip>
																	</tbody>
																</table>
															</div>
														</td>
													</tr>
												</table>
												</div>
											</fieldset>
										</td>
									</tr>
									<tr>
										<%-- ####################### ORDERS DELIVERY CONFIRMED (C) ####################### --%>
										<td valign="top">
											<fieldset>
												<legend>
													<table>
														<tr>
															<td class="stab">ORDERS DELIVERY CONFIRMED FOR : </td>
															<td><ezf:label name="slsDt" ezfName="slsDt" /></td>
														</tr>
													</table>
												</legend>
												<div>
												<table border="0" cellpadding="0" cellspacing="0">
													<tr style="vertical-align=top;">
														<td>
															<!-- Left TBL Header -->
															<table border="1" cellpadding="1" cellspacing="0">
																<col align="center" width="90">	<!-- Sales Order# -->
																<tr height="26">
																	<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxTblSortColNm_CS' )" id="xxTblSortColNm_CS">Sales Order#<img id="sortIMG.xxTblSortColNm_CS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																</tr>
															</table>
															<!-- Left TBL Main -->
															<div id="leftTBLC" style="overflow-y:hidden; height:170; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBLC'));">
																<table border="1" cellpadding="1" cellspacing="0" width="" id="C1">
																	<col align="left" width="90">	<!-- Sales Order# -->
																	<tbody>
																		<% int indexC = 0; %>
																		<ezf:row ezfHyo="C">
																		<tr height="28" id="id_leftC_row{EZF_ROW_NUMBER}">
																			<% if ("O".equals(bMsg.C.no(indexC).trxHdrNumTpCd_C1.getValue())) { %>
																			<td align="left"><ezf:anchor name="" ezfName="" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_OrdEntrySchdSo" otherAttr=" ezfanchortext=\"\" id=\"trxHdrNum_C1{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_C1" ezfName="trxHdrNum_C1" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor></td>
																			<% } else if ("S".equals(bMsg.C.no(indexC).trxHdrNumTpCd_C1.getValue())) { %>
																			<td align="center"><ezf:anchor name="" ezfName="" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngDelySchdSo" otherAttr=" ezfanchortext=\"\" id=\"trxHdrNum_C1{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_C1" ezfName="trxHdrNum_C1" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor></td>
																			<% } else { %>
																			<td>&nbsp;</td>
																			<% } %>
																		</tr>
																		<% indexC++; %>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="28">
																				<td align="center"><a href="#"><label>SO001000</label></a></td>
																		</tr>
																		<tr height="28">
																				<td align="center"><a href="#"><label>SO002000</label></a></td>
																		</tr>
																		</ezf:skip>
																	</tbody>
																</table>
															</div>
														</td>
														<td>
															<!-- Right TBL Header -->
															<div id="rightTopTBLC" style="overflow-y:none; overflow-x:hidden; width=430" onScroll="synchroScrollLeft(this.id, new Array('rightTBLC'));">
																<table border="1" cellpadding="1" cellspacing="0" width="884">
																	<col align="center" width="189">	<!-- Customer Name -->
																	<col align="center" width="120">	<!-- City -->
																	<col align="center" width="55">		<!-- State -->
																	<col align="center" width="100">	<!-- Model -->
																	<col align="center" width="81">		<!-- Total Units -->
																	<col align="center" width="150">	<!-- Delivery Scheduled Date -->
																	<col align="center" width="120">	<!-- Carrier Name -->
																	<tr height="26">
																		<td class="pClothBs">Customer Name</td>
																		<td class="pClothBs">City</td>
																		<td class="pClothBs">State</td>
																		<td class="pClothBs">Model</td>
																		<td class="pClothBs">Total Units</td>
																		<td class="pClothBs">Delivery Scheduled Date</td>
																		<td class="pClothBs">Carrier</td>
																	</tr>
																</table>
															</div>
															<!-- Right TBL Main -->
															<div id="rightTBLC" style="overflow:scroll; height:187; width:447" onScroll="setScrollPositionC(); synchroScrollTop(this.id, new Array('leftTBLC')); onScroll=synchroScrollLeft(this.id, new Array('rightTopTBLC'));">
																<table border="1" cellpadding="1" cellspacing="0" width="884" id="C2">
																	<col align="left" width="189">	<!-- Customer Name -->
																	<col align="left" width="120">	<!-- City -->
																	<col align="center" width="55">	<!-- State -->
																	<col align="left" width="100">	<!-- Model -->
																	<col align="right" width="81">	<!-- Total Units -->
																	<col align="left" width="150">	<!-- Delivery Scheduled Date -->
																	<col align="left" width="120">	<!-- Carrier Name -->
																	<tbody>
																		<ezf:row ezfHyo="C">
																		<tr height="28" id="id_leftC_row{EZF_ROW_NUMBER}">
																			<td><ezf:inputText name="shipToAcctNm_C1" ezfName="shipToAcctNm_C1" value="HEWLETT PACKARD CO" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"360\" id=\"shipToAcctNm_C1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToCtyAddr_C1" ezfName="shipToCtyAddr_C1" value="NEW YORK CITY" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"shipToCtyAddr_C1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToStCd_C1" ezfName="shipToStCd_C1" value="NY" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"50\" id=\"shipToStCd_C1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:center;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="mdlNm_C1" ezfName="mdlNm_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" id=\"mdlNm_C1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="xxTotCnt_CO" ezfName="xxTotCnt_CO" value="2" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"60\" id=\"xxTotCnt_CO{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="xxDt10Dt_C1" ezfName="xxDt10Dt_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"xxDt10Dt_C1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="carrNm_C1" ezfName="carrNm_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"carrNm_C1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																		</tr>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="28">
																			<td><input type="text" size="25" maxlength="360" value="Config# 528" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="6"  maxlength="50"  value="" style="border:none;background-color:transparent;padding:0px;text-align:center;"></td>
																			<td><input type="text" size="13" maxlength="30"  value="IR4225" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="10" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="01/20/2016" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="FEDEX" style="border:none;background-color:transparent;padding:0px;"></td>
																		</tr>
																		<tr height="28">
																			<td><input type="text" size="25" maxlength="360" value="Config# 1010" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="6"  maxlength="50"  value="" style="border:none;background-color:transparent;padding:0px;text-align:center;"></td>
																			<td><input type="text" size="13" maxlength="30"  value="IR4225" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="10" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="01/20/2016" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="FEDEX" style="border:none;background-color:transparent;padding:0px;"></td>
																		</tr>
																		</ezf:skip>
																	</tbody>
																</table>
															</div>
														</td>
													</tr>
												</table>
												</div>
											</fieldset>
										</td>
										<%-- ####################### SCHEDULED ORDERES WITH FUTURE DATE (D) ####################### --%>
										<td owspan="2" valign="top">
											<fieldset>
												<legend>
													<table>
														<tr>
															<td class="stab">SCHEDULED ORDERES WITH FUTURE DATE</td>
														</tr>
													</table>
												</legend>
												<div>
												<table border="0" cellpadding="0" cellspacing="0">
													<tr style="vertical-align=top;">
														<td>
															<!-- Left TBL Header -->
															<table border="1" cellpadding="1" cellspacing="0">
																<col align="center" width="90">	<!-- Sales Order# -->
																<tr height="26">
																	<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'xxTblSortColNm_DS' )" id="xxTblSortColNm_DS">Sales Order#<img id="sortIMG.xxTblSortColNm_DS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																</tr>
															</table>
															<!-- Left TBL Main -->
															<div id="leftTBLD" style="overflow-y:hidden; height:170; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBLD'));">
																<table border="1" cellpadding="1" cellspacing="0" width="" id="D1">
																	<col align="left" width="90">	<!-- Sales Order# -->
																	<tbody>
																		<% int indexD = 0; %>
																		<ezf:row ezfHyo="D">
																		<tr height="28" id="id_leftD_row{EZF_ROW_NUMBER}">
																			<% if ("O".equals(bMsg.D.no(indexD).trxHdrNumTpCd_D1.getValue())) { %>
																			<td align="left"><ezf:anchor name="" ezfName="" ezfHyo="D" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_OrdEntrySchdSoFtu" otherAttr=" ezfanchortext=\"\" id=\"trxHdrNum_D1{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_D1" ezfName="trxHdrNum_D1" ezfHyo="D" ezfArrayNo="0" /></ezf:anchor></td>
																			<% } else if ("S".equals(bMsg.D.no(indexD).trxHdrNumTpCd_D1.getValue())) { %>
																			<td align="center"><ezf:anchor name="" ezfName="" ezfHyo="D" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngDelySchdSoFtu" otherAttr=" ezfanchortext=\"\" id=\"trxHdrNum_D1{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_D1" ezfName="trxHdrNum_D1" ezfHyo="D" ezfArrayNo="0" /></ezf:anchor></td>
																			<% } else { %>
																			<td>&nbsp;</td>
																			<% } %>
																		</tr>
																		<% indexD++; %>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="28">
																			<td align="center"><a href="#"><label>SO003000</label></a></td>
																		</tr>
																		<tr height="28">
																			<td align="center"><a href="#"><label>SO004000</label></a></td>
																		</tr>
																		<tr height="28">
																			<td align="center"><a href="#"><label>SO005000</label></a></td>
																		</tr>
																		</ezf:skip>
																	</tbody>
																</table>
															</div>
														</td>
														<td>
															<!-- Right TBL Header -->
															<div id="rightTopTBLD" style="overflow-y:none; overflow-x:hidden; width=430" onScroll="synchroScrollLeft(this.id, new Array('rightTBLD'));">
																<table border="1" cellpadding="1" cellspacing="0" width="884">
																	<col align="center" width="189">	<!-- Customer Name -->
																	<col align="center" width="120">	<!-- City -->
																	<col align="center" width="55">		<!-- State -->
																	<col align="center" width="100">	<!-- Model -->
																	<col align="center" width="81">	<!-- Total Units -->
																	<col align="center" width="150">	<!-- Delivery Scheduled Date -->
																	<col align="center" width="120">	<!-- Carrier Name -->
																	<tr height="26">
																		<td class="pClothBs">Customer Name</td>
																		<td class="pClothBs">City</td>
																		<td class="pClothBs">State</td>
																		<td class="pClothBs">Model</td>
																		<td class="pClothBs">Total Units</td>
																		<td class="pClothBs">Delivery Scheduled Date</td>
																		<td class="pClothBs">Carrier</td>
																	</tr>
																</table>
															</div>
															<!-- Right TBL Main -->
															<div id="rightTBLD" style="overflow:scroll; height:187; width:447" onScroll="setScrollPositionD(); synchroScrollTop(this.id, new Array('leftTBLD')); onScroll=synchroScrollLeft(this.id, new Array('rightTopTBLD'));">
																<table border="1" cellpadding="1" cellspacing="0" width="884" id="D2">
																	<col align="left" width="189">	<!-- Customer Name -->
																	<col align="left" width="120">	<!-- City -->
																	<col align="center" width="55">	<!-- State -->
																	<col align="left" width="100">	<!-- Model -->
																	<col align="right" width="81">	<!-- Total Units -->
																	<col align="left" width="150">	<!-- Delivery Scheduled Date -->
																	<col align="left" width="120">	<!-- Carrier Name -->
																	<tbody>
																		<ezf:row ezfHyo="D">
																		<tr height="28" id="id_leftD_row{EZF_ROW_NUMBER}">
																			<td><ezf:inputText name="shipToAcctNm_D1" ezfName="shipToAcctNm_D1" value="HEWLETT PACKARD CO" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"360\" id=\"shipToAcctNm_D1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToCtyAddr_D1" ezfName="shipToCtyAddr_D1" value="NEW YORK CITY" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"shipToCtyAddr_D1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToStCd_D1" ezfName="shipToStCd_D1" value="NY" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"50\" id=\"shipToStCd_D1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:center;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="mdlNm_D1" ezfName="mdlNm_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" id=\"mdlNm_D1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="xxTotCnt_DO" ezfName="xxTotCnt_DO" value="3" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"60\" id=\"xxTotCnt_DO{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="xxDt10Dt_D1" ezfName="xxDt10Dt_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"xxDt10Dt_D1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="carrNm_D1" ezfName="carrNm_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"carrNm_D1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																		</tr>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="28">
																			<td><input type="text" size="25" maxlength="360" value="Config# 565528" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="6"  maxlength="50"  value="" style="border:none;background-color:transparent;padding:0px;text-align:center;"></td>
																			<td><input type="text" size="13" maxlength="30"  value="IR4225" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="10" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="01/31/2016" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="FEDEX" style="border:none;background-color:transparent;padding:0px;"></td>
																		</tr>
																		<tr height="28">
																			<td><input type="text" size="25" maxlength="360" value="Config# 633" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="6"  maxlength="50"  value="" style="border:none;background-color:transparent;padding:0px;text-align:center;"></td>
																			<td><input type="text" size="13" maxlength="30"  value="IR4225" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="10" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="02/20/2016" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="FEDEX" style="border:none;background-color:transparent;padding:0px;"></td>
																		</tr>
																		<tr height="28">
																			<td><input type="text" size="25" maxlength="360" value="Config# 1234123" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="6"  maxlength="50"  value="" style="border:none;background-color:transparent;padding:0px;text-align:center;"></td>
																			<td><input type="text" size="13" maxlength="30"  value="IR4225" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="10" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="08/20/2016" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="FEDEX" style="border:none;background-color:transparent;padding:0px;"></td>
																		</tr>
																		</ezf:skip>
																	</tbody>
																</table>
															</div>
														</td>
													</tr>
												</table>
												</div>
											</fieldset>
										</td>
									</tr>
									<tr>
										<%-- ####################### RMA ORDERES SCHEDULED FOR PICKUP (F) ####################### --%>
										<td valign="top">
											<fieldset>
												<legend>
													<table>
														<tr>
															<td class="stab">RMA ORDERES SCHEDULED FOR PICKUP</td>
														</tr>
													</table>
												</legend>
												<div>
												<table border="0" cellpadding="0" cellspacing="0">
													<tr style="vertical-align=top;">
														<td>
															<!-- Left TBL Header -->
															<table border="1" cellpadding="1" cellspacing="0">
																<col align="center" width="90">	<!-- Sales Order# -->
																<tr height="26">
																	<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'xxTblSortColNm_FS' )" id="xxTblSortColNm_FS">Sales Order#<img id="sortIMG.xxTblSortColNm_FS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																</tr>
															</table>
															<!-- Left TBL Main -->
															<div id="leftTBLF" style="overflow-y:hidden; height:170; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBLF'));">
																<table border="1" cellpadding="1" cellspacing="0" width="" id="F1">
																	<col align="left" width="90">	<!-- Sales Order# -->
																	<tbody>
																		<% int indexF = 0; %>
																		<ezf:row ezfHyo="F">
																		<tr height="28" id="id_leftF_row{EZF_ROW_NUMBER}">
																			<% if ("O".equals(bMsg.F.no(indexF).trxHdrNumTpCd_F1.getValue())) { %>
																			<td align="left"><ezf:anchor name="" ezfName="" ezfHyo="F" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_OrdEntrySchdRws" otherAttr=" ezfanchortext=\"\" id=\"trxHdrNum_F1{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_F1" ezfName="trxHdrNum_F1" ezfHyo="F" ezfArrayNo="0" /></ezf:anchor></td>
																			<% } else if ("R".equals(bMsg.F.no(indexF).trxHdrNumTpCd_F1.getValue())) { %>
																			<td align="center"><ezf:anchor name="" ezfName="" ezfHyo="F" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngRmaSchdRws" otherAttr=" ezfanchortext=\"\" id=\"trxHdrNum_F1{EZF_ROW_NUMBER}\""><ezf:label name="trxHdrNum_F1" ezfName="trxHdrNum_F1" ezfHyo="F" ezfArrayNo="0" /></ezf:anchor></td>
																			<% } else { %>
																			<td>&nbsp;</td>
																			<% } %>
																		</tr>
																		<% indexF++; %>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="28">
																			<td align="center"><a href="#"><label>RWS04000</label></a></td>
																		</tr>
																		</ezf:skip>
																	</tbody>
																</table>
															</div>
														</td>
														<td>
															<!-- Right TBL Header -->
															<div id="rightTopTBLF" style="overflow-y:none; overflow-x:hidden; width=430" onScroll="synchroScrollLeft(this.id, new Array('rightTBLF'));">
																<table border="1" cellpadding="1" cellspacing="0" width="884">
																	<col align="center" width="189">	<!-- Customer Name -->
																	<col align="center" width="120">	<!-- City -->
																	<col align="center" width="55">		<!-- State -->
																	<col align="center" width="100">	<!-- Model -->
																	<col align="center" width="81">		<!-- Total Units -->
																	<col align="center" width="150">	<!-- Delivery Scheduled Date -->
																	<col align="center" width="120">	<!-- Carrier Name -->
																	<tr height="26">
																		<td class="pClothBs">Customer Name</td>
																		<td class="pClothBs">City</td>
																		<td class="pClothBs">State</td>
																		<td class="pClothBs">Model</td>
																		<td class="pClothBs">Total Units</td>
																		<td class="pClothBs">Scheduled Pick Up Date</td>
																		<td class="pClothBs">Carrier</td>
																	</tr>
																</table>
															</div>
															<!-- Right TBL Main -->
															<div id="rightTBLF" style="overflow:scroll; height:187; width:447" onScroll="setScrollPositionF(); synchroScrollTop(this.id, new Array('leftTBLF')); onScroll=synchroScrollLeft(this.id, new Array('rightTopTBLF'));">
																<table border="1" cellpadding="1" cellspacing="0" width="884" id="F2">
																	<col align="left" width="189">	<!-- Customer Name -->
																	<col align="left" width="120">	<!-- City -->
																	<col align="center" width="55">	<!-- State -->
																	<col align="left" width="100">	<!-- Model -->
																	<col align="right" width="81">	<!-- Total Units -->
																	<col align="left" width="150">	<!-- Delivery Scheduled Date -->
																	<col align="left" width="120">	<!-- Carrier Name -->
																	<tbody>
																		<ezf:row ezfHyo="F">
																		<tr height="28" id="id_leftF_row{EZF_ROW_NUMBER}">
																			<td><ezf:inputText name="shipToAcctNm_F1" ezfName="shipToAcctNm_F1" value="HEWLETT PACKARD CO" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"360\" id=\"shipToAcctNm_F1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToCtyAddr_F1" ezfName="shipToCtyAddr_F1" value="NEW YORK CITY" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"shipToCtyAddr_F1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="shipToStCd_F1" ezfName="shipToStCd_F1" value="NY" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"50\" id=\"shipToStCd_F1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:center;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="mdlNm_F1" ezfName="mdlNm_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" id=\"mdlNm_F1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="xxTotCnt_FI" ezfName="xxTotCnt_FI" value="1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"60\" id=\"xxTotCnt_FI{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="xxDt10Dt_F1" ezfName="xxDt10Dt_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"xxDt10Dt_F1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="carrNm_F1" ezfName="carrNm_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"carrNm_F1{EZF_ROW_NUMBER}\" style=\"border:none;background-color:transparent;padding:0px;\" ezftoupper=\"\""/></td>
																		</tr>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="28">
																			<td><input type="text" size="25" maxlength="360" value="Config# 666" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="6"  maxlength="50"  value="" style="border:none;background-color:transparent;padding:0px;text-align:center;"></td>
																			<td><input type="text" size="13" maxlength="30"  value="IR4225" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="10" maxlength="60"  value="" style="border:none;background-color:transparent;padding:0px;text-align:right;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="01/31/2016" style="border:none;background-color:transparent;padding:0px;"></td>
																			<td><input type="text" size="15" maxlength="50"  value="FEDEX" style="border:none;background-color:transparent;padding:0px;"></td>
																		</tr>
																		</ezf:skip>
																	</tbody>
																</table>
															</div>
														</td>
													</tr>
												</table>
												</div>
											</fieldset>
										</td>
										<td></td>
									</tr>
								</table>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</center>
<ezf:inputHidden name="xxListNum_A1" ezfName="xxListNum_A1" value="" otherAttr=" id=\"xxListNum_A1\" "/>
<ezf:inputHidden name="xxListNum_B1" ezfName="xxListNum_B1" value="" otherAttr=" id=\"xxListNum_B1\" "/>
<ezf:inputHidden name="xxListNum_C1" ezfName="xxListNum_C1" value="" otherAttr=" id=\"xxListNum_C1\" "/>
<ezf:inputHidden name="xxListNum_D1" ezfName="xxListNum_D1" value="" otherAttr=" id=\"xxListNum_D1\" "/>
<ezf:inputHidden name="xxListNum_F1" ezfName="xxListNum_F1" value="" otherAttr=" id=\"xxListNum_F1\" "/>
<ezf:inputHidden name="xxListNum_H1" ezfName="xxListNum_H1" value="" otherAttr=" id=\"xxListNum_H1\" "/>
<script type="text/javascript">

	// setScroll(); 
	setTimeout(setScroll,10);

	function synchroScroll_fromRightTblAction() {
		var rightTBLA = this.document.getElementById( 'rightTBLA' );
		var leftTBLA  = this.document.getElementById( 'leftTBLA' );

		var rightTBLB = this.document.getElementById( 'rightTBLB' );
		var leftTBLB  = this.document.getElementById( 'leftTBLB' );

		var rightTBLC = this.document.getElementById( 'rightTBLC' );
		var leftTBLC  = this.document.getElementById( 'leftTBLC' );

		var rightTBLD = this.document.getElementById( 'rightTBLD' );
		var leftTBLD  = this.document.getElementById( 'leftTBLD' );

		var rightTBLF = this.document.getElementById( 'rightTBLF' );
		var leftTBLF  = this.document.getElementById( 'leftTBLF' );

		// synchronize scroll
		leftTBLA.scrollTop = rightTBLA.scrollTop;
		leftTBLB.scrollTop = rightTBLB.scrollTop;
		leftTBLC.scrollTop = rightTBLC.scrollTop;
		leftTBLD.scrollTop = rightTBLD.scrollTop;
		leftTBLF.scrollTop = rightTBLF.scrollTop;
	}

	function synchroScroll_fromLeftTblAction() {
		var leftTBLA  = this.document.getElementById( 'leftTBLA' );
		var rightTBLA = this.document.getElementById( 'rightTBLA' );

		var leftTBLB  = this.document.getElementById( 'leftTBLB' );
		var rightTBLB = this.document.getElementById( 'rightTBLB' );

		var leftTBLC  = this.document.getElementById( 'leftTBLC' );
		var rightTBLC = this.document.getElementById( 'rightTBLC' );

		var leftTBLD  = this.document.getElementById( 'leftTBLD' );
		var rightTBLD = this.document.getElementById( 'rightTBLD' );

		var leftTBLF  = this.document.getElementById( 'leftTBLF' );
		var rightTBLF = this.document.getElementById( 'rightTBLF' );

		// synchronize scroll
		rightTBLA.scrollTop = leftTBLA.scrollTop;
		rightTBLB.scrollTop = leftTBLB.scrollTop;
		rightTBLC.scrollTop = leftTBLC.scrollTop;
		rightTBLD.scrollTop = leftTBLD.scrollTop;
		rightTBLF.scrollTop = leftTBLF.scrollTop;
	}

	<%@ page import="com.fujitsu.uji.util.Parameters" %>
	<%@ page import="com.fujitsu.uji.http.HttpDispatchContext" %>
	function setScroll() {
		<%
		Parameters params = (Parameters) pageContext.getAttribute(HttpDispatchContext.PARAMETERS_KEY, PageContext.REQUEST_SCOPE);
		String ezdEvent = params.getSingleValue("ezd.event");
		%>
		var xxListNum_A1 = this.document.getElementById( 'xxListNum_A1' );
		var leftTBLA = this.document.getElementById( 'leftTBLA' );
		var rightTBLA = this.document.getElementById( 'rightTBLA' );

		var xxListNum_B1 = this.document.getElementById( 'xxListNum_B1' );
		var leftTBLB = this.document.getElementById( 'leftTBLB' );
		var rightTBLB = this.document.getElementById( 'rightTBLB' );

		var xxListNum_C1 = this.document.getElementById( 'xxListNum_C1' );
		var leftTBLC = this.document.getElementById( 'leftTBLC' );
		var rightTBLC = this.document.getElementById( 'rightTBLC' );

		var xxListNum_D1 = this.document.getElementById( 'xxListNum_D1' );
		var leftTBLD = this.document.getElementById( 'leftTBLD' );
		var rightTBLD = this.document.getElementById( 'rightTBLD' );

		var xxListNum_F1 = this.document.getElementById( 'xxListNum_F1' );
		var leftTBLF = this.document.getElementById( 'leftTBLF' );
		var rightTBLF = this.document.getElementById( 'rightTBLF' );

		var xxListNum_H1 = this.document.getElementById( 'xxListNum_H1' );
		var headerTBL = this.document.getElementById( 'headerTBL' );


		<% if (ezdEvent!=null && ezdEvent.startsWith("TAB_")) { %>
			xxListNum_A1.value = 0;
			xxListNum_B1.value = 0;
			xxListNum_C1.value = 0;
			xxListNum_D1.value = 0;
			xxListNum_F1.value = 0;
			xxListNum_H1.value = 0;
		<% } %>

		var LA = xxListNum_A1.value
		var LB = xxListNum_B1.value
		var LC = xxListNum_C1.value
		var LD = xxListNum_D1.value
	    var LF = xxListNum_F1.value
	    var LH = xxListNum_H1.value

		if (xxListNum_A1.value > 0) {
			leftTBLA.scrollTop = LA;
			rightTBLA.scrollTop = LA;
		}
		if (xxListNum_B1.value > 0) {
			leftTBLB.scrollTop = LB;
			rightTBLB.scrollTop = LB;
		}
		if (xxListNum_C1.value > 0) {
			leftTBLC.scrollTop = LC;
			rightTBLC.scrollTop = LC;
		}
		if (xxListNum_D1.value > 0) {
			leftTBLD.scrollTop = LD;
			rightTBLD.scrollTop = LD;
		}
		if (xxListNum_F1.value > 0) {
			leftTBLF.scrollTop = LF;
			rightTBLF.scrollTop = LF;
		}
		if (xxListNum_H1.value > 0) {
			headerTBL.scrollTop = LH;
		}
	}

	function setScrollPositionA() {
		var rightTBLA = this.document.getElementById( 'rightTBLA' );
		this.document.getElementById( 'xxListNum_A1' ).value = rightTBLA.scrollTop;
	}

	function setScrollPositionB() {
		var rightTBLB = this.document.getElementById( 'rightTBLB' );
		this.document.getElementById( 'xxListNum_B1' ).value = rightTBLB.scrollTop;
	}

	function setScrollPositionC() {
		var rightTBLC = this.document.getElementById( 'rightTBLC' );
		this.document.getElementById( 'xxListNum_C1' ).value = rightTBLC.scrollTop;
	}

	function setScrollPositionD() {
		var rightTBLD = this.document.getElementById( 'rightTBLD' );
		this.document.getElementById( 'xxListNum_D1' ).value = rightTBLD.scrollTop;
	}

	function setScrollPositionF() {
		var rightTBLF = this.document.getElementById( 'rightTBLF' );
		this.document.getElementById( 'xxListNum_F1' ).value = rightTBLF.scrollTop;
	}

	function setScrollPositionHdr() {
		var headerTBL = this.document.getElementById( 'headerTBL' );
		this.document.getElementById( 'xxListNum_H1' ).value = headerTBL.scrollTop;
	}
</script>

<%-- **** Task specific area ends here **** --%>
