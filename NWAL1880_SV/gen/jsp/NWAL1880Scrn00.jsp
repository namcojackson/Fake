<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170530164658 --%>
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
			<input type="hidden" name="pageID" value="NWAL1880Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Order Workbench">
			<!-- Event Name -->
			<ezf:inputHidden name="xxScrEventNm" ezfName="xxScrEventNm" otherAttr=" id=\"xxScrEventNm\""/>

			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<%-- ###TAB - HEAD --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Order Workbench" class="pTab_ON"><a href="#">Ord WB</a></li>
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
<%--------------------------------%>
<%-- Header						--%>
<%--------------------------------%>
							<div class="pTab_BODY">
							<br>
							<table border="0" cellpadding="0" cellspacing="0">
								<col width="10">
								<col width="80">
								<col width="100">
								<col width="30">
								<col width="80">
								<col width="100">
								<col width="30">
								<col width="100">
								<col width="100">
								<tr>
									<td></td>
									<td class="stab"><ezf:anchor name="xxOrdTeamSrchTxt_LK" ezfName="xxOrdTeamSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Team" otherAttr=" id=\"xxOrdTeamSrchTxt_LK\" ezfanchortext=\"\"">Team (*)</ezf:anchor></td>
									<td><ezf:inputText name="xxOrdTeamSrchTxt" ezfName="xxOrdTeamSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
									<td></td>
									<td class="stab"><ezf:anchor name="xxOrdZnSrchTxt_LK" ezfName="xxOrdZnSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Zone" otherAttr=" id=\"xxOrdZnSrchTxt_LK\" ezfanchortext=\"\"">Zone (*)</ezf:anchor></td>
									<td><ezf:inputText name="xxOrdZnSrchTxt" ezfName="xxOrdZnSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
									<td></td>
									<td class="stab"><ezf:anchor name="xxCratByUsrSrchTxt_LK" ezfName="xxCratByUsrSrchTxt_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderProcessor" otherAttr=" id=\"xxCratByUsrSrchTxt_LK\" ezfanchortext=\"\"">Order Processor(*)</ezf:anchor></td>
									<td><ezf:inputText name="xxCratByUsrSrchTxt" ezfName="xxCratByUsrSrchTxt" otherAttr=" size=\"15\" maxlength=\"1000\" ezftoupper=\"\""/></td>
								</tr>
							</table>
							<br>
							<table border="0" cellpadding="0" cellspacing="0">
								<col width="10">
								<col width="80">
								<col width="160">
								<col width="30">
								<col width="80">
								<tr>
									<td></td>
									<!-- Display By -->
									<td class="stab">Display By</td>
									<td>
										<ezf:select name="xxScrItem50Txt" ezfName="xxScrItem50Txt" ezfCodeName="xxScrItem50Txt_CD" ezfDispName="xxScrItem50Txt_NM" otherAttr=" style=\"width:160;\""/>
									</td>
									<td></td>
									<!-- Filter -->
									<td><ezf:inputButton name="SearchOrder" value="Filter" htmlClass="cBtn" /></td>
								</tr>
							</table>
							<br>
							<table border="0" cellpadding="0" cellspacing="0">
								<col width="10">
								<col align="Left" width="660">
								<col align="Left" width="10">
								<col align="Left" width="405">
								<tr>
									<td></td>
									<td>
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="85">
											<col align="center" width="85">
											<col align="center" width="85">
											<col align="center" width="85">
											<col align="center" width="85">
											<col align="center" width="85">
											<tr height="22">
												<td class="pClothBs" colSpan="7" align="left">My Dashboard : Order Counts</td>
											</tr>
											<tr height="22">
												<td class="pClothBs" rowSpan="2" valign="bottom">
													<ezf:label name="dplyByItemLbNm" ezfName="dplyByItemLbNm" />
												</td>
												<td class="pClothBs" align="center" rowSpan="2" valign="bottom">Entered</td>
												<td class="pClothBs" align="center" colSpan="4">Submitted To Workflow</td>
												<td class="pClothBs" rowSpan="2" valign="bottom">Booked</td>
											</tr>
											<tr height="22">
												<td class="pClothBs" align="center">Credit Hold</td>
												<td class="pClothBs">Profit Hold</td>
												<td class="pClothBs">Validation Hold</td>
												<td class="pClothBs">All Other</td>
												
											</tr>
										</table>
										<div style="overflow-y:scroll; height:80">
										<table table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="85">
											<col align="center" width="85">
											<col align="center" width="85">
											<col align="center" width="85">
											<col align="center" width="85">
											<col align="center" width="85">
											<ezf:row ezfHyo="A">
												<tr style="height:25">
													<td align="left">
														<ezf:inputText name="xxDplyByItemNm_A1" ezfName="xxDplyByItemNm_A1" value="C11590(Bloom,Joshua)" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"21\""/>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="A" onclick="moveWinOrderInquiry('OrdCntEnt',{EZF_ROW_NUMBER});" ezfanchortext id="xxEntCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxEntCnt_A1" ezfName="xxEntCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="A" onclick="moveWinOrderInquiry('OrdCntCrHld',{EZF_ROW_NUMBER});" ezfanchortext id="xxCrHldCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxCrHldCnt_A1" ezfName="xxCrHldCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="A" onclick="moveWinOrderInquiry('OrdCntPrftHld',{EZF_ROW_NUMBER});" ezfanchortext id="xxPrftCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxPrftCnt_A1" ezfName="xxPrftCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="A" onclick="moveWinOrderInquiry('OrdCntVldHld',{EZF_ROW_NUMBER});" ezfanchortext id="xxVldCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxVldCnt_A1" ezfName="xxVldCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="A" onclick="moveWinOrderInquiry('OrdCntAllOth',{EZF_ROW_NUMBER});" ezfanchortext id="xxAllOtherCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxAllOtherCnt_A1" ezfName="xxAllOtherCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="A" onclick="moveWinOrderInquiry('OrdCntBook',{EZF_ROW_NUMBER});" ezfanchortext id="xxBookCnt_A1#{EZF_ROW_NUMBER}"><ezf:label name="xxBookCnt_A1" ezfName="xxBookCnt_A1" ezfHyo="A" ezfArrayNo="0" /></a>
													</td>
												</tr>
											</ezf:row>
										</table>
										</div>
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="85">
											<col align="center" width="85">
											<col align="center" width="85">
											<tr height="22">
												<td class="pClothBs" colSpan="4" align="left">My Dashboard : Orders Invoiced</td>
											</tr>
											<tr height="22">
												<td class="pClothBs" rowSpan="2" valign="bottom">
													<ezf:label name="dplyByItemLbNm" ezfName="dplyByItemLbNm" />
												</td>
												<td class="pClothBs" align="center" colSpan="2">Invoiced</td>
												<td class="pClothBs" rowSpan="2" valign="bottom">TOTAL</td>
											</tr>
											<tr height="22">
												<td class="pClothBs">Today</td>
												<td class="pClothBs">Current Month</td>
											</tr>
										</table>
										<div style="overflow-y:scroll; height:80">
										<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="85">
											<col align="center" width="85">
											<col align="center" width="85">
											<ezf:row ezfHyo="B">
												<tr style="height:25">
													<td align="left">
														<ezf:inputText name="xxDplyByItemNm_B1" ezfName="xxDplyByItemNm_B1" value="C11590(Bloom,Joshua)" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"21\""/>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="B" onclick="moveWinOrderInquiry('OrdInvToday',{EZF_ROW_NUMBER});" ezfanchortext id="xxInvdCnt_B1#{EZF_ROW_NUMBER}"><ezf:label name="xxInvdCnt_B1" ezfName="xxInvdCnt_B1" ezfHyo="B" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="B" onclick="moveWinOrderInquiry('OrdInvCurMth',{EZF_ROW_NUMBER});" ezfanchortext id="xxInvdCnt_B2#{EZF_ROW_NUMBER}"><ezf:label name="xxInvdCnt_B2" ezfName="xxInvdCnt_B2" ezfHyo="B" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="B" onclick="moveWinOrderInquiry('OrdInvTot',{EZF_ROW_NUMBER});" ezfanchortext id="xxInvdCnt_B3#{EZF_ROW_NUMBER}"><ezf:label name="xxInvdCnt_B3" ezfName="xxInvdCnt_B3" ezfHyo="B" ezfArrayNo="0" /></a>
													</td>
												</tr>
											</ezf:row>
										</table>
										<div>
									</td>
								</tr>
								
								<tr>
									<td></td>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="72">
											<col align="center" width="73">
											<col align="center" width="73">
											<col align="center" width="73">
											<col align="center" width="73">
											<col align="center" width="73">
											<col align="center" width="73">
											<tr height="22">
												<td class="pClothBs" colSpan="8" align="left">My Dashboard : Order Aging Entered</td>
											</tr>
											<tr height="22">
												<td class="pClothBs" rowSpan="2" valign="bottom">
													<ezf:label name="dplyByItemLbNm" ezfName="dplyByItemLbNm" />
												</td>
												<td class="pClothBs" align="center" colSpan="6">Entered (Aging Days)</td>
												<td class="pClothBs" rowSpan="2" valign="bottom">TOTAL</td>
											</tr>
											<tr height="22">
												<td class="pClothBs" align="center">Today</td>
												<td class="pClothBs" align="center">1-2</td>
												<td class="pClothBs">3-10</td>
												<td class="pClothBs">10-30</td>
												<td class="pClothBs">31-50</td>
												<td class="pClothBs">51+</td>
												
											</tr>
										</table>
										<div style="overflow-y:scroll; height:80">
										<table border="1" cellpadding="1" cellspacing="0" id="C" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="72">
											<col align="center" width="73">
											<col align="center" width="73">
											<col align="center" width="73">
											<col align="center" width="73">
											<col align="center" width="73">
											<col align="center" width="73">
											<ezf:row ezfHyo="C">
												<tr style="height:25">
													<td align="left">
														<ezf:inputText name="xxDplyByItemNm_C1" ezfName="xxDplyByItemNm_C1" value="C11590(Bloom,Joshua)" ezfHyo="C" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"21\""/>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="C" onclick="moveWinOrderInquiry('OrdAgingToday',{EZF_ROW_NUMBER});" ezfanchortext id="xxAgingCnt_C1#{EZF_ROW_NUMBER}"><ezf:label name="xxAgingCnt_C1" ezfName="xxAgingCnt_C1" ezfHyo="C" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="C" onclick="moveWinOrderInquiry('OrdAging1_2',{EZF_ROW_NUMBER});" ezfanchortext id="xxAgingCnt_C2#{EZF_ROW_NUMBER}"><ezf:label name="xxAgingCnt_C2" ezfName="xxAgingCnt_C2" ezfHyo="C" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="C" onclick="moveWinOrderInquiry('OrdAging3_10',{EZF_ROW_NUMBER});" ezfanchortext id="xxAgingCnt_C3#{EZF_ROW_NUMBER}"><ezf:label name="xxAgingCnt_C3" ezfName="xxAgingCnt_C3" ezfHyo="C" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="C" onclick="moveWinOrderInquiry('OrdAging11_30',{EZF_ROW_NUMBER});" ezfanchortext id="xxAgingCnt_C4#{EZF_ROW_NUMBER}"><ezf:label name="xxAgingCnt_C4" ezfName="xxAgingCnt_C4" ezfHyo="C" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="C" onclick="moveWinOrderInquiry('OrdAging31_50',{EZF_ROW_NUMBER});" ezfanchortext id="xxAgingCnt_C5#{EZF_ROW_NUMBER}"><ezf:label name="xxAgingCnt_C5" ezfName="xxAgingCnt_C5" ezfHyo="C" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="C" onclick="moveWinOrderInquiry('OrdAgingOrver50',{EZF_ROW_NUMBER});" ezfanchortext id="xxAgingCnt_C6#{EZF_ROW_NUMBER}"><ezf:label name="xxAgingCnt_C6" ezfName="xxAgingCnt_C6" ezfHyo="C" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="C" onclick="moveWinOrderInquiry('OrdAgingTot',{EZF_ROW_NUMBER});" ezfanchortext id="xxAgingCnt_C7#{EZF_ROW_NUMBER}"><ezf:label name="xxAgingCnt_C7" ezfName="xxAgingCnt_C7" ezfHyo="C" ezfArrayNo="0" /></a>
													</td>
												</tr>
											</ezf:row>
										</table>
										</div>
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="85">
											<col align="center" width="85">
											<col align="center" width="85">
											<tr height="22">
												<td class="pClothBs" colSpan="4" align="left">My Dashboard : Credit & Rebills </td>
											</tr>
											<tr height="22">
												<td class="pClothBs" rowSpan="2" valign="bottom">
													<ezf:label name="dplyByItemLbNm" ezfName="dplyByItemLbNm" />
												</td>
												<td class="pClothBs" align="center" colSpan="2">C/R</td>
												<td class="pClothBs" rowSpan="2" valign="bottom">TOTAL</td>
											</tr>
											<tr height="22">
												<td class="pClothBs">Today</td>
												<td class="pClothBs">Current Month</td>
											</tr>
										</table>
										<div style="overflow-y:scroll; height:80">
										<table border="1" cellpadding="1" cellspacing="0" id="D" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="85">
											<col align="center" width="85">
											<col align="center" width="85">
											<ezf:row ezfHyo="D">
												<tr style="height:25">
													<td align="left">
														<ezf:inputText name="xxDplyByItemNm_D1" ezfName="xxDplyByItemNm_D1" value="C11590(Bloom,Joshua)" ezfHyo="D" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"21\""/>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="D" onclick="moveWinOrderInquiry('CrRebilToday',{EZF_ROW_NUMBER});" ezfanchortext id="xxCrRebilCnt_D1#{EZF_ROW_NUMBER}"><ezf:label name="xxCrRebilCnt_D1" ezfName="xxCrRebilCnt_D1" ezfHyo="D" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="D" onclick="moveWinOrderInquiry('CrRebilCurMth',{EZF_ROW_NUMBER});" ezfanchortext id="xxCrRebilCnt_D2#{EZF_ROW_NUMBER}"><ezf:label name="xxCrRebilCnt_D2" ezfName="xxCrRebilCnt_D2" ezfHyo="D" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="D" onclick="moveWinOrderInquiry('CrRebilTot',{EZF_ROW_NUMBER});" ezfanchortext id="xxCrRebilCnt_D3#{EZF_ROW_NUMBER}"><ezf:label name="xxCrRebilCnt_D3" ezfName="xxCrRebilCnt_D3" ezfHyo="D" ezfArrayNo="0" /></a>
													</td>
												</tr>
											</ezf:row>
											<ezf:skip>
												<tr style="height:25">
													<td align="left">C11590(Bloom,Joshua)</td>
													<td align="center"><a href="">2</a></td>
													<td align="center"><a href="">28</a></td>
													<td align="center"><a href="">30</a></td>
												</tr>
											</ezf:skip>
										</table>
										</div>
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										&nbsp;
									</td>
								</tr>
							</table>
							<table border="0" cellpadding="0" cellspacing="0">
								<col width="10">
								<col align="Left" width="">
								<col align="Left" width="30">
								<col align="Left" width="">
								<col align="Left" width="30">
								<col align="Left" width="">
								<tr>
									<td></td>
									<td>
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="70">
											<col align="center" width="120">
											<tr height="22">
												<td class="pClothBs" colSpan="3" align="left">My Dashboard : Orders on Bill Hold </td>
											</tr>
											<tr height="22">
												<td class="pClothBs" rowSpan="2" valign="bottom">
													<ezf:label name="dplyByItemLbNm" ezfName="dplyByItemLbNm" />
												</td>
												<td class="pClothBs" align="center" colSpan="2">Active Bill Hold</td>
												
											</tr>
											<tr height="22">
												<td class="pClothBs" align="center">Count</td>
												<td class="pClothBs" align="center">Value</td>
											</tr>
										</table>
										<div style="overflow-y:scroll; height:80">
										<table border="1" cellpadding="1" cellspacing="0" id="E" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="70">
											<col align="center" width="120">
											<ezf:row ezfHyo="E">
												<tr style="height:25">
													<td align="left">
														<ezf:inputText name="xxDplyByItemNm_E1" ezfName="xxDplyByItemNm_E1" value="C11590(Bloom,Joshua)" ezfHyo="E" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"21\""/>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="E" onclick="moveWinOrderInquiry('BillHldCnt',{EZF_ROW_NUMBER});" ezfanchortext id="xxBllgHldCnt_E1#{EZF_ROW_NUMBER}"><ezf:label name="xxBllgHldCnt_E1" ezfName="xxBllgHldCnt_E1" ezfHyo="E" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="E" onclick="moveWinOrderInquiry('BillHldAmt',{EZF_ROW_NUMBER});" ezfanchortext id="xxBllgHldAmt_E1#{EZF_ROW_NUMBER}"><ezf:label name="xxBllgHldAmt_E1" ezfName="xxBllgHldAmt_E1" ezfHyo="E" ezfArrayNo="0" /></a>
													</td>
												</tr>
											</ezf:row>
											<ezf:skip>
												<tr style="height:25">
													<td align="left">C11590(Bloom,Joshua)</td>
													<td align="center"><a href="">2</a></td>
													<td align="right"><a href="">28</a></td>
												</tr>
											</ezf:skip>
										</table>
										</div>
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="85">
											<col align="center" width="85">
											
											<tr height="22">
												<td class="pClothBs" colSpan="3" align="left">My Dashboard : Order Sources to Order </td>
											</tr>
											<tr height="22">
												<td class="pClothBs" rowSpan="2" valign="bottom">
													<ezf:label name="dplyByItemLbNm" ezfName="dplyByItemLbNm" />
												</td>
												<td class="pClothBs" align="center" colSpan="2">In Review</td>
											</tr>
											<tr height="22">
												<td class="pClothBs" align="center">Deal Config</td>
												<td class="pClothBs" align="center">SOM</td>
											</tr>
										</table>
										<div style="overflow-y:scroll; height:80">
										<table border="1" cellpadding="0" cellspacing="0" id="F" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="85">
											<col align="center" width="85">
											<ezf:row ezfHyo="F">
												<tr style="height:25">
													<td align="left">
														<ezf:inputText name="xxDplyByItemNm_F1" ezfName="xxDplyByItemNm_F1" value="C11590(Bloom,Joshua)" ezfHyo="F" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"21\""/>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="F" onclick="moveWinOrderInquiry('OrdSrcRvwDealConfig',{EZF_ROW_NUMBER});" ezfanchortext id="xxRvwDealConfigCnt_F1#{EZF_ROW_NUMBER}"><ezf:label name="xxRvwDealConfigCnt_F1" ezfName="xxRvwDealConfigCnt_F1" ezfHyo="F" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="F" onclick="moveWinOrderInquiry('OrdSrcRvwSom',{EZF_ROW_NUMBER});" ezfanchortext id="xxRvwSomCnt_F1#{EZF_ROW_NUMBER}"><ezf:label name="xxRvwSomCnt_F1" ezfName="xxRvwSomCnt_F1" ezfHyo="F" ezfArrayNo="0" /></a>
													</td>
												</tr>
											</ezf:row>
											<ezf:skip>
												<tr style="height:25">
														<td align="left">C11591(Bloom,Joshua)</td>
														<td align="center"><a href="">2</a></td>
														<td align="center"><a href="">28</a></td>
														
												</tr>
											</ezf:skip>
										</table>
										</div>
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="80">
											<col align="center" width="80">
											<tr height="22">
												<td class="pClothBs" colSpan="3" align="left">My Dashboard : Order Sources to Order </td>
											</tr>
											<tr height="22">
												<td class="pClothBs" rowSpan="2" valign="bottom">
													<ezf:label name="dplyByItemLbNm" ezfName="dplyByItemLbNm" />
												</td>
												<td class="pClothBs" align="center" colSpan="2">Accepted-Current Month</td>
												
											</tr>
											<tr height="22">
												<td class="pClothBs">Deal Config</td>
												<td class="pClothBs">SOM</td>
											</tr>
										</table>
										<div style="overflow-y:scroll; height:80">
										<table border="1" cellpadding="1" cellspacing="0" id="G" style="table-layout:fixed;">
											<col align="center" width="150">
											<col align="center" width="80">
											<col align="center" width="80">
											<ezf:row ezfHyo="G">
												<tr style="height:25">
													<td align="left">
														<ezf:inputText name="xxDplyByItemNm_G1" ezfName="xxDplyByItemNm_G1" value="C11590(Bloom,Joshua)" ezfHyo="G" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"21\""/>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="G" onclick="moveWinOrderInquiry('OrdSrcAcptDealConfig',{EZF_ROW_NUMBER});" ezfanchortext id="xxAcptDealConfigCnt_G1#{EZF_ROW_NUMBER}"><ezf:label name="xxAcptDealConfigCnt_G1" ezfName="xxAcptDealConfigCnt_G1" ezfHyo="G" ezfArrayNo="0" /></a>
													</td>
													<td align="center">
														<a href="#" name="" ezfName="" ezfHyo="G" onclick="moveWinOrderInquiry('OrdSrcAcptSom',{EZF_ROW_NUMBER});" ezfanchortext id="xxAcptSomCnt_G1#{EZF_ROW_NUMBER}"><ezf:label name="xxAcptSomCnt_G1" ezfName="xxAcptSomCnt_G1" ezfHyo="G" ezfArrayNo="0" /></a>
													</td>
												</tr>
											</ezf:row>
											<ezf:skip>
												<tr style="height:25">
													<td align="left">C11590(Bloom,Joshua)</td>
													<td align="center"><a href="">2</a></td>
													<td align="center"><a href="">28</a></td>
												</tr>
											</ezf:skip>
										</table>
										</div>
									</td>
								</tr>
							</table>
							</div>
						</td>
					</tr>
				</table>
			</center>
<script type="text/javascript" defer>

function moveWinOrderInquiry(selectType, i)
{
        document.getElementById("xxScrEventNm").value = selectType;

        sendServer('MoveWin_OrderInquiry', i);
}
</script>

<%-- **** Task specific area ends here **** --%>
