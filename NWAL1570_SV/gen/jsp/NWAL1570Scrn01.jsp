<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190508141540 --%>
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
			<input type="hidden" name="pageID" value="NWAL1570Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Order Inquiry by Status">

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
																			<div id='rightTblHead' style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" height="40" style="table-layout: fixed" id="SCRN01HEAD" width="6320px" style="margin-right:20px">
																					<col align="left" width="200">		<!-- Display By -->
																					<col align="center" width="100">	<!-- Order Number -->
																					<col align="center" width="70">		<!-- Order Line Number -->
																					<col align="center" width="100">	<!-- Qty -->
																					<col align="center" width="150">	<!-- Amount -->
																					<col align="center" width="150">	<!-- Header Status  -->
																					<col align="center" width="150">	<!-- Line Status -->
																					<col align="center" width="100">	<!-- Item CD -->
																					<col align="center" width="100">	<!-- Item Name -->
																					<col align="center" width="100">	<!-- WH CD -->
																					<col align="center" width="100">	<!-- WH Name -->
																					<col align="center" width="100">	<!-- Sub WH -->
																					<col align="center" width="100">	<!-- Ship To Account Code -->
																					<col align="center" width="100">	<!-- Ship To Account Name -->
																					<col align="center" width="100">	<!-- Ship To Location Code -->
																					<col align="center" width="100">	<!-- Bill To Account Code -->
																					<col align="center" width="100">	<!-- Bill To Account Name -->
																					<col align="center" width="100">	<!-- Bill To Location Code -->
																					<col align="center" width="100">	<!-- Sold To Account Code -->
																					<col align="center" width="100">	<!-- Sold To Account Name -->
																					<col align="center" width="100">	<!-- Sold To Location Code -->
																					<col align="center" width="100">	<!-- Ordered Date -->
																					<col align="center" width="100">	<!-- Booked Date -->
																					<col align="center" width="100">	<!-- RDD -->
																					<col align="center" width="100">	<!-- PSD -->
																					<col align="center" width="100">	<!-- PDD -->
																					<col align="center" width="100">	<!-- Ship Date -->
																					<col align="center" width="100">	<!-- Install Date -->
																					<col align="center" width="100">	<!-- Invoice Date -->
																					<col align="center" width="100">	<!-- Order Category -->
																					<col align="center" width="100">	<!-- Order Reason -->
																					<col align="center" width="100">	<!-- Order Sub Reason -->
																					<col align="center" width="100">	<!-- Order Source -->
																					<col align="center" width="100">	<!-- Source # -->
																					<col align="center" width="100">	<!-- Line Cateogry -->
																					<col align="center" width="100">	<!-- Line Source -->
																					<col align="center" width="100">	<!-- Price List -->
																					<col align="center" width="100">	<!-- Cust PO# -->
																					<col align="center" width="100">	<!-- Lease PO# -->
																					<col align="center" width="100">	<!-- Business Unit -->
																					<col align="center" width="100">	<!-- Branch -->
																					<col align="center" width="100">	<!-- Sales Rep -->
																					<col align="center" width="100">	<!-- Sales Rep Name -->
																					<col align="center" width="100">	<!-- CSMP# -->
																					<col align="center" width="100">	<!-- Association Program Name -->
																					<col align="center" width="100">	<!-- Product Group -->
																					<col align="center" width="100">	<!-- PL1 -->
																					<col align="center" width="100">	<!-- PL2 -->
																					<col align="center" width="100">	<!-- PL3 -->
																					<col align="center" width="100">	<!-- PL4 -->
																					<col align="center" width="100">	<!-- Model -->
																					<col align="center" width="100">	<!-- COA Prod -->
																					<col align="center" width="100">	<!-- COA MDSE -->
																					<col align="center" width="100">	<!-- Config ID -->
																					<col align="center" width="100">	<!-- Contract# -->
																					<col align="center" width="100">	<!-- Serial# -->
																					<col align="center" width="100">	<!-- PR# -->
																					<col align="center" width="100">	<!-- PO# -->
																					<col align="center" width="100">	<!-- SO# -->
																					<col align="center" width="100">	<!-- Invoice# -->
																					<col align="center" width="100">	<!-- Vendor Code -->
																					<col align="center" width="100">	<!-- Acquisition# -->
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
																						<td id="AH1"  class="pClothBs">Order#</td>
																						<td id="AH2"  class="pClothBs">Line#</td>
																						<td id="AH3"  class="pClothBs">Qty</td>
																						<td id="AH4"  class="pClothBs">Amount</td>
																						<td id="AH5"  class="pClothBs">Header Status</td>
																						<td id="AH6"  class="pClothBs">Line Status</td>
																						<td id="AH7"  class="pClothBs">Item Code</td>
																						<td id="AH8" class="pClothBs">Item Description</td>
																						<td id="AH9" class="pClothBs">WH CD</td>
																						<td id="AH10" class="pClothBs">WH Name</td>
																						<td id="AH11" class="pClothBs">Sub WH Name</td>
																						<td id="AH12" class="pClothBs">Ship To Account Code</td>
																						<td id="AH13" class="pClothBs">Ship To Account Name</td>
																						<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
																						<!-- <td id="AH14" class="pClothBs">Ship To Location Code</td> -->
																						<td id="AH14" class="pClothBs">Ship To Code</td>
																						<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
																						<td id="AH15" class="pClothBs">Bill To Account Code</td>
																						<td id="AH16" class="pClothBs">Bill To Account Name</td>
																						<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
																						<!-- <td id="AH17" class="pClothBs">Bill To Location Code</td> -->
																						<td id="AH17" class="pClothBs">Bill To Code</td>
																						<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
																						<td id="AH18" class="pClothBs">Sold To Account Code</td>
																						<td id="AH19" class="pClothBs">Sold To Account Name</td>
																						<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
																						<!-- <td id="AH20" class="pClothBs">Sold To Location Code</td> -->
																						<td id="AH20" class="pClothBs">Sold To Code</td>
																						<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
																						<td id="AH21" class="pClothBs">Ordered Date</td>
																						<td id="AH22" class="pClothBs">Booked Date</td>
																						<td id="AH23" class="pClothBs">RDD</td>
																						<td id="AH24" class="pClothBs">PSD</td>
																						<td id="AH25" class="pClothBs">PDD</td>
																						<td id="AH26" class="pClothBs">Ship Date</td>
																						<td id="AH27" class="pClothBs">Install Date</td>
																						<td id="AH28" class="pClothBs">Invoice Date</td>
																						<td id="AH29" class="pClothBs">Order Category</td>
																						<td id="AH30" class="pClothBs">Order Reason</td>
																						<td id="AH31" class="pClothBs">Order Sub Reason</td>
																						<td id="AH32" class="pClothBs">Order Source</td>
																						<td id="AH33" class="pClothBs">Source #</td>
																						<td id="AH34" class="pClothBs">Line Cateogry</td>
																						<td id="AH35" class="pClothBs">Line Source</td>
																						<td id="AH36" class="pClothBs">Price List</td>
																						<td id="AH37" class="pClothBs">Cust PO#</td>
																						<td id="AH38" class="pClothBs">Lease PO#</td>
																						<td id="AH39" class="pClothBs">Business Unit</td>
																						<td id="AH40" class="pClothBs">Branch</td>
																						<td id="AH41" class="pClothBs">Sales Rep</td>
																						<td id="AH42" class="pClothBs">Sales Rep Name</td>
																						<td id="AH43" class="pClothBs">CSMP#</td>
																						<td id="AH44" class="pClothBs">Association Program</td>
																						<td id="AH45" class="pClothBs"><ezf:label name="mdseStruElmntTpNm_L1" ezfName="mdseStruElmntTpNm_L1" /></td>
																						<td id="AH46" class="pClothBs"><ezf:label name="mdseStruElmntTpNm_L2" ezfName="mdseStruElmntTpNm_L2" /></td>
																						<td id="AH47" class="pClothBs"><ezf:label name="mdseStruElmntTpNm_L3" ezfName="mdseStruElmntTpNm_L3" /></td>
																						<td id="AH48" class="pClothBs"><ezf:label name="mdseStruElmntTpNm_L4" ezfName="mdseStruElmntTpNm_L4" /></td>
																						<td id="AH49" class="pClothBs"><ezf:label name="mdseStruElmntTpNm_L5" ezfName="mdseStruElmntTpNm_L5" /></td>
																						<td id="AH50" class="pClothBs">Model</td>
																						<td id="AH51" class="pClothBs">COA Prod</td>
																						<td id="AH52" class="pClothBs">COA MDSE</td>
																						<td id="AH53" class="pClothBs">Config ID</td>
																						<td id="AH54" class="pClothBs">Contract#</td>
																						<td id="AH55" class="pClothBs">Serial#</td>
																						<td id="AH56" class="pClothBs">PR#</td>
																						<td id="AH57" class="pClothBs">PO#</td>
																						<td id="AH58" class="pClothBs">SO#</td>
																						<td id="AH59" class="pClothBs">Invoice#</td>
																						<td id="AH60" class="pClothBs">Vendor</td>
																						<td id="AH61" class="pClothBs">Acquisition#</td>
																					</tr>
																				</table>
																			</div>
																			<div id="rightTBL" style="width:1097px; height:407px; display:block; overflow:scroll; margin:0px; padding:0px;" >
																				<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed"  width="6320px" >
																					<col align="left" width="200">	<!-- Display By -->
																					<col align="left" width="100">	<!-- Order Number -->
																					<col align="left" width="70">	<!-- Order Line Number -->
																					<col align="left" width="100">	<!-- Qty -->
																					<col align="left" width="150">	<!-- Amount -->
																					<col align="left" width="150">	<!-- Header Status  -->
																					<col align="left" width="150">	<!-- Line Status -->
																					<col align="left" width="100">	<!-- Item CD -->
																					<col align="left" width="100">	<!-- Item Name -->
																					<col align="left" width="100">	<!-- WH CD -->
																					<col align="left" width="100">	<!-- WH Name -->
																					<col align="left" width="100">	<!-- Sub WH -->
																					<col align="left" width="100">	<!-- Ship To Account Code -->
																					<col align="left" width="100">	<!-- Ship To Account Name -->
																					<col align="left" width="100">	<!-- Ship To Location Code -->
																					<col align="left" width="100">	<!-- Bill To Account Code -->
																					<col align="left" width="100">	<!-- Bill To Account Name -->
																					<col align="left" width="100">	<!-- Bill To Location Code -->
																					<col align="left" width="100">	<!-- Sold To Account Code -->
																					<col align="left" width="100">	<!-- Sold To Account Name -->
																					<col align="left" width="100">	<!-- Sold To Location Code -->
																					<col align="left" width="100">	<!-- Ordered Date -->
																					<col align="left" width="100">	<!-- Booked Date -->
																					<col align="left" width="100">	<!-- RDD -->
																					<col align="left" width="100">	<!-- PSD -->
																					<col align="left" width="100">	<!-- PDD -->
																					<col align="left" width="100">	<!-- Ship Date -->
																					<col align="left" width="100">	<!-- Install Date -->
																					<col align="left" width="100">	<!-- Invoice Date -->
																					<col align="left" width="100">	<!-- Order Category -->
																					<col align="left" width="100">	<!-- Order Reason -->
																					<col align="left" width="100">	<!-- Order Sub Reason -->
																					<col align="left" width="100">	<!-- Order Source -->
																					<col align="left" width="100">	<!-- Source # -->
																					<col align="left" width="100">	<!-- Line Cateogry -->
																					<col align="left" width="100">	<!-- Line Source -->
																					<col align="left" width="100">	<!-- Price List -->
																					<col align="left" width="100">	<!-- Cust PO# -->
																					<col align="left" width="100">	<!-- Lease PO# -->
																					<col align="left" width="100">	<!-- Business Unit -->
																					<col align="left" width="100">	<!-- Branch -->
																					<col align="left" width="100">	<!-- Sales Rep -->
																					<col align="left" width="100">	<!-- Sales Rep Name -->
																					<col align="left" width="100">	<!-- CSMP# -->
																					<col align="left" width="100">	<!-- Association Program Name -->
																					<col align="left" width="100">	<!-- Product Group -->
																					<col align="left" width="100">	<!-- PL1 -->
																					<col align="left" width="100">	<!-- PL2 -->
																					<col align="left" width="100">	<!-- PL3 -->
																					<col align="left" width="100">	<!-- PL4 -->
																					<col align="left" width="100">	<!-- Model -->
																					<col align="left" width="100">	<!-- COA Prod -->
																					<col align="left" width="100">	<!-- COA MDSE -->
																					<col align="left" width="100">	<!-- Config ID -->
																					<col align="left" width="100">	<!-- Contract# -->
																					<col align="left" width="100">	<!-- Serial# -->
																					<col align="left" width="100">	<!-- PR# -->
																					<col align="left" width="100">	<!-- PO# -->
																					<col align="left" width="100">	<!-- SO# -->
																					<col align="left" width="100">	<!-- Invoice# -->
																					<col align="left" width="100">	<!-- Vendor Code -->
																					<col align="left" width="100">	<!-- Acquisition# -->
																					<tbody>
																					<%
																						int i = 0;
																					%>
																					<%@ page import="business.servlet.NWAL1570.constant.NWAL1570Constant"%>
																					<ezf:row ezfHyo="A">
																					<%
																						String dispByCtrlAncrLvlCd        = ((business.servlet.NWAL1570.NWAL1570Bean)databean).getXxDplyByCtrlAncrLvlCd_A1(i);
																						String ordNumAncrCtrlFlg          = ((business.servlet.NWAL1570.NWAL1570Bean)databean).getXxAncrCtrlFlg_P0(i);
																						String hdrStsAncrCtrlFlg          = ((business.servlet.NWAL1570.NWAL1570Bean)databean).getXxAncrCtrlFlg_P1(i);
																						String lineStsAncrCtrlFlg         = ((business.servlet.NWAL1570.NWAL1570Bean)databean).getXxAncrCtrlFlg_P2(i);
																						String soNumAncrCtrlFlg           = ((business.servlet.NWAL1570.NWAL1570Bean)databean).getXxAncrCtrlFlg_P3(i);
																						String prchReqNumAncrCtrlFlg      = ((business.servlet.NWAL1570.NWAL1570Bean)databean).getXxAncrCtrlFlg_P4(i);
																						String poNumAncrCtrlFlg           = ((business.servlet.NWAL1570.NWAL1570Bean)databean).getXxAncrCtrlFlg_P5(i);
																						String invNumAncrCtrlFlg          = ((business.servlet.NWAL1570.NWAL1570Bean)databean).getXxAncrCtrlFlg_P6(i);
																						String dsContrNumAncrCtrlFlg      = ((business.servlet.NWAL1570.NWAL1570Bean)databean).getXxAncrCtrlFlg_P7(i);
																						String svcConfigMstrPkAncrCtrlFlg = ((business.servlet.NWAL1570.NWAL1570Bean)databean).getXxAncrCtrlFlg_P8(i);
																						String hdrDplyStsNm               = ((business.servlet.NWAL1570.NWAL1570Bean)databean).getXxHdrDplyStsNm_A1(i);
																						i =i +1;
																					%>
																						<tr height="20">
																							<td class=""><nobr>
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
																								<ezf:inputText name="xxDplyByItemNm_01" ezfName="xxDplyByItemNm_01" value="Overall Total" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"25\""/>
																							</nobr></td>
																							<td>
																							<%if(ordNumAncrCtrlFlg.equals("Y")){%>
																								<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="TransitionByOrderNumber" otherAttr=" ezfanchortext=\"\" id=\"xxDplyOrdInqRefNum_A1#{EZF_ROW_NUMBER}\""><ezf:label name="xxDplyOrdInqRefNum_A1" ezfName="xxDplyOrdInqRefNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																							<%}else{%>
																								<ezf:inputText name="xxDplyOrdInqRefNum_A1" ezfName="xxDplyOrdInqRefNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"20\""/>
																							<%}%>
																							</td>
																							<td><ezf:inputText name="xxDplyOrdLineNum_A1" ezfName="xxDplyOrdLineNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"20\""/></td>
																							<td><ezf:inputText name="ordQty_A1" ezfName="ordQty_A1" value="21" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right;border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="xxTotAmt_A1" ezfName="xxTotAmt_A1" value="33,000" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right;border:none;background-color:transparent;\" size=\"18\""/></td>
																							<td>
																							<%if(hdrStsAncrCtrlFlg.equals("Y")){%>
																								<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="TransitionByHeaderStatus" otherAttr=" ezfanchortext=\"\" id=\"xxHdrDplyStsNm_A1#{EZF_ROW_NUMBER}\""><ezf:label name="xxHdrDplyStsNm_A1" ezfName="xxHdrDplyStsNm_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																							<%}else{%>
																								<ezf:inputText name="xxHdrDplyStsNm_A1" ezfName="xxHdrDplyStsNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"20\""/>
																							<%}%>
																							</td>
																							<td>
																							<%if(lineStsAncrCtrlFlg.equals("Y")){%>
																								<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="TransitionByDetailStatus" otherAttr=" ezfanchortext=\"\" id=\"xxLineDplyStsNm_A1#{EZF_ROW_NUMBER}\""><ezf:label name="xxLineDplyStsNm_A1" ezfName="xxLineDplyStsNm_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																							<%}else{%>
																								<ezf:inputText name="xxLineDplyStsNm_A1" ezfName="xxLineDplyStsNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"20\""/>
																							<%}%>
																							</td>
																							<td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="rtlWhCd_A1" ezfName="rtlWhCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="rtlWhDescTxt_A1" ezfName="rtlWhDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="shipToCustAcctCd_A1" ezfName="shipToCustAcctCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="shipToCustAcctNm_A1" ezfName="shipToCustAcctNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="shipToCustLocCd_A1" ezfName="shipToCustLocCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="billToCustAcctCd_A1" ezfName="billToCustAcctCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="billToCustAcctNm_A1" ezfName="billToCustAcctNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="billToCustLocCd_A1" ezfName="billToCustLocCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="soldToCustAcctCd_A1" ezfName="soldToCustAcctCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="soldToCustAcctNm_A1" ezfName="soldToCustAcctNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="soldToCustLocCd_A1" ezfName="soldToCustLocCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="xxCpoOrdDt_A1" ezfName="xxCpoOrdDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="xxBookDt_A1" ezfName="xxBookDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="rddDt_A1" ezfName="rddDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="psdDt_A1" ezfName="psdDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="pddDt_A1" ezfName="pddDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="actlShipDt_A1" ezfName="actlShipDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="istlDt_A1" ezfName="istlDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="invDt_A1" ezfName="invDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="dsOrdCatgDescTxt_A1" ezfName="dsOrdCatgDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="dsOrdTpDescTxt_A1" ezfName="dsOrdTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="dsOrdRsnDescTxt_A1" ezfName="dsOrdRsnDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="cpoSrcTpDescTxt_A1" ezfName="cpoSrcTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="ordSrcRefNum_A1" ezfName="ordSrcRefNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="dsOrdLineCatgDescTxt_A1" ezfName="dsOrdLineCatgDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="ordLineSrcNm_A1" ezfName="ordLineSrcNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="prcCatgDescTxt_A1" ezfName="prcCatgDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="custIssPoNum_A1" ezfName="custIssPoNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="leaseCmpyPoNum_A1" ezfName="leaseCmpyPoNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="coaExtnDescTxt_A1" ezfName="coaExtnDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="coaBrDescTxt_A1" ezfName="coaBrDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="slsRepPsnNum_A1" ezfName="slsRepPsnNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="tocNm_A1" ezfName="tocNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="csmpContrNum_A1" ezfName="csmpContrNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="prcContrNm_A1" ezfName="prcContrNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="zerothProdCtrlNm_A1" ezfName="zerothProdCtrlNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="firstProdCtrlNm_A1" ezfName="firstProdCtrlNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="scdProdCtrlNm_A1" ezfName="scdProdCtrlNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="thirdProdCtrlNm_A1" ezfName="thirdProdCtrlNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="frthProdCtrlNm_A1" ezfName="frthProdCtrlNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="t_MdlNm_A1" ezfName="t_MdlNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="coaProdDescTxt_A1" ezfName="coaProdDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="coaMdseTpDescTxt_A1" ezfName="coaMdseTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td>
																							<%if(svcConfigMstrPkAncrCtrlFlg.equals("Y")){%>
																								<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="TransitionByConfigNumber" otherAttr=" ezfanchortext=\"\" id=\"svcConfigMstrPk_A1#{EZF_ROW_NUMBER}\""><ezf:label name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																							<%}else{%>
																								<ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/>
																							<%}%>
																							</td>
																							<td>
																							<%if(dsContrNumAncrCtrlFlg.equals("Y")){%>
																								<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="TransitionByContractNumber" otherAttr=" ezfanchortext=\"\" id=\"dsContrNum_A1#{EZF_ROW_NUMBER}\""><ezf:label name="dsContrNum_A1" ezfName="dsContrNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																							<%}else{%>
																								<ezf:inputText name="dsContrNum_A1" ezfName="dsContrNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/>
																							<%}%>
																							</td>
																							<td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td>
																							<%if(prchReqNumAncrCtrlFlg.equals("Y")){%>
																								<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="TransitionByPRNumber" otherAttr=" ezfanchortext=\"\" id=\"prchReqNum_A1#{EZF_ROW_NUMBER}\""><ezf:label name="prchReqNum_A1" ezfName="prchReqNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																							<%}else{%>
																								<ezf:inputText name="prchReqNum_A1" ezfName="prchReqNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/>
																							<%}%>
																							</td>
																							<td>
																							<%if(poNumAncrCtrlFlg.equals("Y")){%>
																								<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="TransitionByPONumber" otherAttr=" ezfanchortext=\"\" id=\"poNum_A1#{EZF_ROW_NUMBER}\""><ezf:label name="poNum_A1" ezfName="poNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																							<%}else{%>
																								<ezf:inputText name="poNum_A1" ezfName="poNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/>
																							<%}%>
																							</td>
																							<td>
																							<%if(soNumAncrCtrlFlg.equals("Y")){%>
																								<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="TransitionBySONumber" otherAttr=" ezfanchortext=\"\" id=\"soNum_A1#{EZF_ROW_NUMBER}\""><ezf:label name="soNum_A1" ezfName="soNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																							<%}else{%>
																								<ezf:inputText name="soNum_A1" ezfName="soNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/>
																							<%}%>
																							</td>
																							<td>
																							<%if(invNumAncrCtrlFlg.equals("Y")){%>
																								<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="TransitionByInvoiceNumber" otherAttr=" ezfanchortext=\"\" id=\"invNum_A1#{EZF_ROW_NUMBER}\""><ezf:label name="invNum_A1" ezfName="invNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																							<%}else{%>
																								<ezf:inputText name="invNum_A1" ezfName="invNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/>
																							<%}%>
																							</td>
																							<td><ezf:inputText name="dplyVndNm_A1" ezfName="dplyVndNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																							<td><ezf:inputText name="aquNum_A1" ezfName="aquNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:left; border:none;background-color:transparent;\" size=\"13\""/></td>
																						</tr>
																					</ezf:row>
																					<ezf:skip>
																						<tr height="20" id="id_leftA_row1">
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value="2Z1 - MONROE WH - CSA"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value="1602600 - OMNICARE INC"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="10000000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="1.1.1"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="13" value="1"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="13" value="1,000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="Booked"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="Shipped"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="XXXXA001AA"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="IR 12345"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="MONROE"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="MONROE WAREHOUSE"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="NEW"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="123456"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ABC MART"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="0002"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="0008"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CO"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="10"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="50239"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CSA-010122"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="123/45612"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="1087835"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="HITCHENS, GLEN R."></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS1"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS2"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS3"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS4"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS5"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="100022"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="518927"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="6000168"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																						</tr>
																						<tr height="20" id="id_leftA_row2">
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="10000000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="1.2.1"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="13" value="2"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="13" value="2,000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="Booked"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="Shipped"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="XXXXA001AA"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="IR 12345"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="MONROE"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="MONROE WAREHOUSE"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="NEW"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="123456"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ABC MART"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="0002"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="0008"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CO"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="10"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="50239"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CSA-010122"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="123/45612"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="1087835"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="HITCHENS, GLEN R."></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS1"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS2"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS3"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS4"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS5"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="100022"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="518927"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="6000168"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																						</tr>
																						<tr height="20" id="id_leftA_row3">
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value="0000043 - BPR COPY SHOP INC."></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="10000001"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="1.1.1"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="13" value="3"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="13" value="3,000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="Booked"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="Shipped"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="XXXXA001AA"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="IR 12345"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="MONROE"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="MONROE WAREHOUSE"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="NEW"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="123456"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ABC MART"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="0002"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="0008"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CO"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="10"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="50239"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CSA-010122"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="123/45612"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="1087835"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="HITCHENS, GLEN R."></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS1"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS2"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS3"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS4"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS5"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="100022"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="518927"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="6000168"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																						</tr>
																						<tr height="20" id="id_leftA_row4">
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value="2Z6 - MONROE WH - CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value="1602600 - OMNICARE INC"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="10000002"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="1.1.1"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="13" value="4"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="13" value="10,000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="Booked"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="Shipped"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="XXXXA001AA"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="IR 12345"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="MONROE"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="MONROE WAREHOUSE"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="NEW"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="123456"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ABC MART"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="0002"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="0008"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CO"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="10"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="50239"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CSA-010122"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="123/45612"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="1087835"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="HITCHENS, GLEN R."></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS1"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS2"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS3"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS4"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS5"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="100022"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="518927"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="6000168"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																						</tr>
																						<tr height="20" id="id_leftA_row5">
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="10000002"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="1.2-1"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="13" value="5"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="13" value="20,000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="Booked"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="Shipped"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="XXXXA001AA"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="IR 12345"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="MONROE"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="MONROE WAREHOUSE"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="NEW"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="123456"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ABC MART"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="0002"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="0008"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CO"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="10"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="50239"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CSA-010122"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="123/45612"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="1087835"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="HITCHENS, GLEN R."></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS1"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS2"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS3"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS4"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS5"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="100022"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="518927"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="6000168"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																						</tr>
																						<tr height="20" id="id_leftA_row6">
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value="0000043 - BPR COPY SHOP INC."></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="20" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="10000003"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="1.1.1"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="13" value="6"></td>
																							<td><input style="text-align:right;border:none;background-color:transparent;" size="13" value="30,000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="Booked"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="Shipped"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="XXXXA001AA"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="IR 12345"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="MONROE"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="MONROE WAREHOUSE"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="NEW"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="123456"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ABC MART"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="20000"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CFS"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="01/01/2016"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="0002"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="0008"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CO"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="10"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="50239"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="CSA-010122"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="123/45612"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="1087835"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="HITCHENS, GLEN R."></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS1"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS2"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS3"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS4"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="ESS5"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="100022"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="518927"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value="6000168"></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																							<td><input style="text-align:left; border:none;background-color:transparent;" size="13" value=""></td>
																						</tr>
																					</ezf:skip>
																					</tbody>
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
	S21TableUI.initialize("parentBoxA", "SCRN01HEAD", "A", 1, true);
</script>

<%-- **** Task specific area ends here **** --%>
