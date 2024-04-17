<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170929150834 --%>
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
			<input type="hidden" name="pageID" value="NWAL2220Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Order Import Search">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
                <ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Order Import Search" class="pTab_ON"><a href="#">Import Search</a></li>
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
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

<%-- ##### BODY(HEADER) ##### --%>
				<div class="pTab_BODY">
					<table>
						<col valign="top">
						<col valign="top">
						<col valign="top">
						<col valign="top">
						<tr>
							<td>
								<table border="0" cellspacing="0" cellpadding="0">
									<col width="12">
									<col width="133">
									<col width="345">
									<col width="117">
									<col width="300">
									<col width="">
									<tr>
										<td></td>
										<td class="stab">Saved Search Options</td>
										<td>
											<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
										</td>
										<td class="stab">Search Option Name</td>
										<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td>
											<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
											<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
										</td>
									</tr>
								</table>
								<hr style="width: 100%; height: 0px;" cellpadding="0">
							</td>
						</tr>
						<tr>
							<td>
								<fieldset>
									<legend style="font-size:12px;">Search for Import Order Data</legend>
									<table border="0" style="table-layout:fixed;width=98%;margin-left:20;">
										<col width="120">
										<col width="160">
										<col width="140">
										<col width="160">
										<col width="75">
										<col width="160">
										<col width="90">
										<col width="160">
										<col width="">
										<tr>
											<td class="stab">Source Name</td>
											<td>
												<ezf:select name="cpoSrcTpCd_SV" ezfName="cpoSrcTpCd_SV" ezfBlank="1" ezfCodeName="cpoSrcTpCd_L" ezfDispName="cpoSrcTpDescTxt_L" otherAttr=" style=\"width:146;\""/>
											</td>
											<td class="stab">Sales Business Unit</td>
											<td colspan="3">
												<ezf:select name="coaExtnCd_SV" ezfName="coaExtnCd_SV" ezfBlank="1" ezfCodeName="coaExtnCd_L" ezfDispName="coaExtnDescTxt_L" otherAttr=" style=\"width:385;\""/>
											</td>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_SlsBranch" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SlsBranch" >Sales Branch (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="coaBrDescTxt" ezfName="coaBrDescTxt" value="CHICAGO" otherAttr=" size=\"20\""/>
											</td>
										</tr>
										<tr>
											<td class="stab">Source Reference Num</td>
											<td>
												<ezf:inputText name="ordSrcRefNum" ezfName="ordSrcRefNum" value="S100111" otherAttr=" size=\"20\""/>
											</td>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_SoldToCust" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldToCust" >Sold To : Customer Num</ezf:anchor></td>
											<td>
												<ezf:inputText name="sellToCustCd" ezfName="sellToCustCd" value="10012345" otherAttr=" size=\"20\" ezftoupper=\"\""/>
											</td>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_SoldToCust" ezfArrayNo="1" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldToCust" >Name (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="dsAcctNm_OT" ezfName="dsAcctNm_OT" value="CHICAGO BULLS" otherAttr=" size=\"20\" ezftoupper=\"\""/>
											</td>
											<!-- Mod Start 2017/09/29 H.Sugawara QC#19922 -->
											<!-- <td class="stab"><a href="#" onclick="sendServer('OpenWin_SoldToCust')" ezfhyo="OpenWin_SoldToCust">Location</a></td> -->
											<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_SoldToCust" ezfArrayNo="2" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldToCust" >Code</ezf:anchor></td>
											<!-- Mod End   2017/09/29 H.Sugawara QC#19922 -->
											<td>
												<ezf:inputText name="soldToCustLocCd" ezfName="soldToCustLocCd" value="CHICAGO BULLS" otherAttr=" size=\"20\" ezftoupper=\"\""/>
											</td>
										</tr>
										<tr>
											<td class="stab">Import Status</td>
											<td>
												<ezf:select name="imptStsCd_SV" ezfName="imptStsCd_SV" ezfBlank="1" ezfCodeName="imptStsCd_L" ezfDispName="imptStsDescTxt_L" otherAttr=" style=\"width:146;\""/>
											</td>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_BillToCust" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToCust" >Bill To&nbsp;&nbsp;&nbsp; : Customer Num</ezf:anchor></td>
											<td>
												<ezf:inputText name="billToCustAcctCd" ezfName="billToCustAcctCd" value="5667" otherAttr=" size=\"20\" ezftoupper=\"\""/>
											</td>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_BillToCust" ezfArrayNo="1" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToCust" >Name (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="dsAcctNm_BT" ezfName="dsAcctNm_BT" value="CFS" otherAttr=" size=\"20\" ezftoupper=\"\""/>
											</td>
											<!-- Mod Start 2017/09/29 H.Sugawara QC#19922 -->
											<!-- <td class="stab"><a href="#" onclick="sendServer('OpenWin_BillToCust')" ezfhyo="OpenWin_BillToCust">Location</a></td> -->
											<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_BillToCust" ezfArrayNo="2" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToCust" >Code</ezf:anchor></td>
											<!-- Mod End   2017/09/29 H.Sugawara QC#19922 -->
											<td>
												<ezf:inputText name="billToCustCd" ezfName="billToCustCd" value="CHICAGO BULLS" otherAttr=" size=\"20\" ezftoupper=\"\""/>
											</td>
										</tr>
										<tr>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_SlsRep" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SlsRep" >Sales Rep (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="tocNm" ezfName="tocNm" value="DOE, JOHN" otherAttr=" size=\"20\""/>
											</td>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_ShipToCust" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCust" >Ship To : Customer Num</ezf:anchor></td>
											<td>
												<ezf:inputText name="shipToCustAcctCd" ezfName="shipToCustAcctCd" value="100029" otherAttr=" size=\"20\" ezftoupper=\"\""/>
											</td>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_ShipToCust" ezfArrayNo="1" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCust" >Name (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="dsAcctNm_HT" ezfName="dsAcctNm_HT" value="CHICAGO BULLS" otherAttr=" size=\"20\" ezftoupper=\"\""/>
											</td>
											<!-- Mod Start 2017/09/29 H.Sugawara QC#19922 -->
											<!-- <td class="stab"><a href="#" onclick="sendServer('OpenWin_ShipToCust')" ezfhyo="OpenWin_ShipToCust">Location</a></td> -->
											<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_ShipToCust" ezfArrayNo="2" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCust" >Code</ezf:anchor></td>
											<!-- Mod End   2017/09/29 H.Sugawara QC#19922 -->
											<td>
												<ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" value="CHICAGO BULLS" otherAttr=" size=\"20\" ezftoupper=\"\""/>
											</td>
										</tr>
										<tr>
											<td class="stab">CPO Order Num</td>
											<td>
												<ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="1" otherAttr=" size=\"20\" ezftoupper=\"\""/>
											</td>
											<td class="stab">Import Date From</td>
											<td>
												<ezf:inputText name="ordSrcImptDt_FM" ezfName="ordSrcImptDt_FM" value="MM/DD/YYYY" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
												<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('ordSrcImptDt_FM', 4);" >
											</td>
											<td class="stab">Import Date To</td>
											<td>
												<ezf:inputText name="ordSrcImptDt_TO" ezfName="ordSrcImptDt_TO" value="MM/DD/YYYY" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
												<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('ordSrcImptDt_TO', 4);" >
											</td>
										</tr>
										<tr>
											<td colspan="8" align="right">
												<ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" otherAttr=" tabindex=\"\""/>&nbsp;
												<ezf:inputButton name="CusaRtlRpt" value="CUSA Retail Report" htmlClass="pBtn10" otherAttr=" tabindex=\"\""/>&nbsp;
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>

					<table>
						<tr>
							<td>
								<fieldset>
									<legend style="font-size:12px;">Result - Order Interface Header</legend>
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
									<table border="0" style="table-layout:fixed;;margin-left:20;">
										<col width="350">
										<col width="710">
										<tr>
											<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
											<td align="right">
												<table cellpadding="0" cellspacing="0">
													<tr>
														<td>
															<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																<jsp:param name="beanId"         value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"        value="A" />
																<jsp:param name="ShowingFrom"    value="xxPageShowFromNum" />
																<jsp:param name="ShowingTo"      value="xxPageShowToNum" />
																<jsp:param name="ShowingOf"      value="xxPageShowOfNum" />
																<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
																<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
																<jsp:param name="ViewMode"       value="FULL" />
															</jsp:include>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>

<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
<!-- ######################################## DETAIL #################################### -->
									<%@ page import="business.servlet.NWAL2220.NWAL2220BMsg" %>
									<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
									<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS" %>
									<%  NWAL2220BMsg bMsg = (NWAL2220BMsg) databean.getEZDBMsg(); %>
									<% int i = 0; %>
									<table border="0" style="table-layout:fixed;width=98%;margin-left:20;">
										<tr>
											<td>

												<%--------------------------------------------%>
												<%---------------- List START ----------------%>
												<%--------------------------------------------%>
												<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
													<col align="left" valign="top">
														<td>
															<div id="parentBoxA">
																<div style="float:left; display:block"><!-- left table -->
																	<div id='leftTblHead' style="display:block;"></div>
																	<div id='leftTbl' style="float:left; display:block; height:270px; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px"></div>
																</div><!-- left table -->
																<div style="float:left;"><!-- right table -->
																	<div id='rightTblHead' style="width:1060px; display:block; overflow:hidden; margin:0px; padding:0px;">
																		<table style="table-layout:fixed; " border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1299px" style="margin-right:20px">
																			<col align="center" width="150"><!-- Source Name -->
																			<col align="center" width="150"><!-- Source Ref Num -->
																			<col align="center" width="150"><!-- Status -->
																			<col align="center" width="150"><!-- Import Date -->
																			<col align="center" width="150"><!-- Sales Rep -->

																			<col align="center" width="220"><!-- Business Unit -->
																			<col align="center" width="220"><!-- Branch -->
																			<col align="center" width="220"><!-- Order Category -->
																			<col align="center" width="220"><!-- Order Reason -->
																			<col align="center" width="100"><!-- CPO Order Num -->

																			<col align="center" width="100"><!-- Sold To Customer Num -->
																			<col align="center" width="220"><!-- Sold To Customer Name -->
																			<col align="center" width="100"><!-- Sold To Customer Location -->
																			<col align="center" width="100"><!-- Bill To Customer Num -->
																			<col align="center" width="220"><!-- Bill To Customer Name -->

																			<col align="center" width="100"><!-- Bill To Customer Location -->
																			<col align="center" width="100"><!-- Ship To Customer Num -->
																			<col align="center" width="220"><!-- Ship To Customer Name -->
																			<col align="center" width="100"><!-- Ship To Customer Location -->
																			<tr height="35">
																				<td id="AH0" class="pClothBs">Source Name</td>
																				<td id="AH1" class="pClothBs">Source Ref Num</td>
																				<td id="AH2" class="pClothBs">Status</td>
																				<td id="AH3" class="pClothBs">Import Date</td>
																				<td id="AH4" class="pClothBs">Sales Rep</td>

																				<td id="AH5" class="pClothBs">Business Unit</td>
																				<td id="AH6" class="pClothBs">Branch</td>
																				<td id="AH7" class="pClothBs">Order Category</td>
																				<td id="AH8" class="pClothBs">Order Reason</td>
																				<td id="AH9" class="pClothBs">CPO Order Num</td>

																				<td id="AH10" class="pClothBs">Sold To<br>Customer Num</td>
																				<td id="AH11" class="pClothBs">Sold To<br>Customer Name</td>
																				<!-- Mod Start 2017/09/29 H.Sugawara QC#19922 -->
																				<!-- <td id="AH12" class="pClothBs">Sold To<br>Location</td> -->
																				<td id="AH12" class="pClothBs">Sold To<br>Code</td>
																				<!-- Mod End   2017/09/29 H.Sugawara QC#19922 -->
																				<td id="AH13" class="pClothBs">Bill To<br>Customer Num</td>
																				<td id="AH14" class="pClothBs">Bill To<br>Customer Name</td>
																				<!-- Mod Start 2017/09/29 H.Sugawara QC#19922 -->
																				<!-- <td id="AH15" class="pClothBs">Bill To<br>Location</td> -->
																				<td id="AH15" class="pClothBs">Bill To<br>Code</td>
																				<!-- Mod End   2017/09/29 H.Sugawara QC#19922 -->

																				<td id="AH16" class="pClothBs">Ship To<br>Customer Num</td>
																				<td id="AH17" class="pClothBs">Ship To<br>Customer Name</td>
																				<!-- Mod Start 2017/09/29 H.Sugawara QC#19922 -->
																				<!-- <td id="AH18" class="pClothBs">Ship To<br>Location</td> -->
																				<td id="AH18" class="pClothBs">Ship To<br>Code</td>
																				<!-- Mod End   2017/09/29 H.Sugawara QC#19922 -->
																			</tr>
																		</table>
																	</div>
																	<div id="rightTbl" style="width:1077px; height:270px; display:block; overflow:scroll; margin:0px; padding: 0px;">
																		<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1299px" >
																			<col align="left" width="150"><!-- Source Name -->
																			<col align="left" width="150"><!-- Source Ref Num -->
																			<col align="left" width="150"><!-- Status -->
																			<col align="left" width="150"><!-- Import Date -->
																			<col align="left" width="150"><!-- Sales Rep -->

																			<col align="left" width="220"><!-- Business Unit -->
																			<col align="left" width="220"><!-- Branch -->
																			<col align="left" width="220"><!-- Order Category -->
																			<col align="left" width="220"><!-- Order Reason -->
																			<col align="left" width="100"><!-- CPO Order Num -->

																			<col align="left" width="100"><!-- Sold To Customer Num -->
																			<col align="left" width="220"><!-- Sold To Customer Name -->
																			<col align="left" width="100"><!-- Sold To Customer Location -->
																			<col align="left" width="100"><!-- Bill To Customer Num -->
																			<col align="left" width="220"><!-- Bill To Customer Name -->

																			<col align="left" width="100"><!-- Bill To Customer Location -->
																			<col align="left" width="100"><!-- Ship To Customer Num -->
																			<col align="left" width="220"><!-- Ship To Customer Name -->
																			<col align="left" width="100"><!-- Ship To Customer Location -->
																			<ezf:row ezfHyo="A">
																				<tr height="23">
																					<td><ezf:inputText name="cpoSrcTpDescTxt_A" ezfName="cpoSrcTpDescTxt_A" value="Deal Configurator" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent;\""/></td>
																					<td><ezf:anchor name="ordSrcRefNum_AC" ezfName="ordSrcRefNum_AC" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OnClick_SrcRefNum" ><ezf:label name="ordSrcRefNum_A" ezfName="ordSrcRefNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																					<!--<td><input type="text" class="pPro" size="20" name="imptStsDescTxt_A" ezfhyo="A" ezfname="imptStsDescTxt_A" value="Pending OM Review" readOnly style="border:none; background-color:transparent; color:red"></td>-->
																					<% if (IMPT_STS.ERROR.equals(bMsg.A.no(i).imptStsCd_A.getValue())) { %>
																						<td><ezf:inputText name="imptStsDescTxt_A" ezfName="imptStsDescTxt_A" value="Pending OM Review" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent; color:red\""/></td>
																					<% } else { %>
																						<td><ezf:inputText name="imptStsDescTxt_A" ezfName="imptStsDescTxt_A" value="Pending OM Review" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent; color:green\""/></td>
																					<% } %>
																					<td><ezf:inputText name="xxTsDsp19Txt_A" ezfName="xxTsDsp19Txt_A" value="2015/02/24 10:01:00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent;\""/></td>
																					<td><ezf:inputText name="tocNm_A" ezfName="tocNm_A" value="0--------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent;\""/></td>

																					<td><ezf:inputText name="coaExtnDescTxt_A" ezfName="coaExtnDescTxt_A" value="101/102 - Admin/Marketing & Budget/Finance" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
																					<td><ezf:inputText name="coaBrDescTxt_A" ezfName="coaBrDescTxt_A" value="Greater Chicago Regional Branch" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
																					<td><ezf:inputText name="dsOrdCatgDescTxt_A" ezfName="dsOrdCatgDescTxt_A" value="DEALER SERVICE EXCHANGE, CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
																					<td><ezf:inputText name="dsOrdTpDescTxt_A" ezfName="dsOrdTpDescTxt_A" value="Service Exchange Reasons" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
																					<td><ezf:inputText name="cpoOrdNum_A" ezfName="cpoOrdNum_A" value="0------8" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none; background-color:transparent;\""/></td>

																					<td><ezf:inputText name="sellToCustCd_A" ezfName="sellToCustCd_A" value="0--------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none; background-color:transparent;\""/></td>
																					<td><ezf:inputText name="dsAcctNm_AO" ezfName="dsAcctNm_AO" value="0--------1---------2---------3---------4---------5---------6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
																					<td><ezf:inputText name="soldToCustLocCd_A" ezfName="soldToCustLocCd_A" value="0--------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none; background-color:transparent;\""/></td>
																					<td><ezf:inputText name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" value="0--------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none; background-color:transparent;\""/></td>
																					<td><ezf:inputText name="dsAcctNm_AB" ezfName="dsAcctNm_AB" value="0--------1---------2---------3---------4---------5---------6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>

																					<td><ezf:inputText name="billToCustCd_A" ezfName="billToCustCd_A" value="0--------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none; background-color:transparent;\""/></td>
																					<td><ezf:inputText name="shipToCustAcctCd_A" ezfName="shipToCustAcctCd_A" value="0--------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none; background-color:transparent;\""/></td>
																					<td><ezf:inputText name="dsAcctNm_AH" ezfName="dsAcctNm_AH" value="0--------1---------2---------3---------4---------5---------6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
																					<td><ezf:inputText name="shipToCustCd_A" ezfName="shipToCustCd_A" value="0--------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none; background-color:transparent;\""/></td>
																				</tr>
																				<% i++; %>
																				<ezf:skip>
																				<tr height="23">
																					<td><label ezfout name="xxEdtDescTxt_10" ezfname="xxEdtDescTxt_10" index="90">SOM</label></td>
																					<td><a href="#" onclick="sendServer('Link_OrderImport')" name="xxEdtDescTxt_12" ezfname="xxEdtDescTxt_12"><label ezfout name="xxEdtDescTxt_12" ezfname="xxEdtDescTxt_12" index="90">S03455</label></a></td>
																					<td><font color="green"><label ezfout name="xxEdtDescTxt_14" ezfname="xxEdtDescTxt_14" index="90">Pending OM Review</label></font></td>
																					<td><label ezfout name="xxEdtDescTxt_16" ezfname="xxEdtDescTxt_16" index="90">2015/02/24  7:01:00</label></td>
																					<td><label ezfout name="xxEdtDescTxt_17" ezfname="xxEdtDescTxt_17" index="90">DOE, JOHN</label></td>

																					<td><label ezfout name="xxEdtDescTxt_18" ezfname="xxEdtDescTxt_18" index="90">113 - BPC</label></td>
																					<td><label ezfout name="xxEdtDescTxt_19" ezfname="xxEdtDescTxt_19" index="90">CHICAGO</label></td>
																					<td><label ezfout name="xxEdtDescTxt_20" ezfname="xxEdtDescTxt_20" index="90">PURCHASE, CSA</label></td>
																					<td><label ezfout name="xxEdtDescTxt_21" ezfname="xxEdtDescTxt_21" index="90">PPS CASH</label></td>
																					<td><label ezfout name="xxEdtDescTxt_36" ezfname="xxEdtDescTxt_36" index="90">1</label></td>

																					<td><label ezfout name="xxEdtDescTxt_22" ezfname="xxEdtDescTxt_22" index="90">10012345</label></td>
																					<td><label ezfout name="xxEdtDescTxt_23" ezfname="xxEdtDescTxt_23" index="90">CHICAGO BULLS</label></td>
																					<td><label ezfout name="xxEdtDescTxt_23" ezfname="xxEdtDescTxt_23" index="90">100465</label></td>
																					<td><label ezfout name="xxEdtDescTxt_24" ezfname="xxEdtDescTxt_24" index="90">5667</label></td>
																					<td><label ezfout name="xxEdtDescTxt_25" ezfname="xxEdtDescTxt_25" index="90">CFS</label></td>

																					<td><label ezfout name="xxEdtDescTxt_23" ezfname="xxEdtDescTxt_23" index="90">100333</label></td>
																					<td><label ezfout name="xxEdtDescTxt_26" ezfname="xxEdtDescTxt_26" index="90">100029</label></td>
																					<td><label ezfout name="xxEdtDescTxt_23" ezfname="xxEdtDescTxt_23" index="90">CHICAGO BULLS</label></td>
																					<td><label ezfout name="xxEdtDescTxt_23" ezfname="xxEdtDescTxt_23" index="90">100324</label></td>
																				</tr>
																				<tr height="23">
																					<td><label ezfout name="xxEdtDescTxt_10" ezfname="xxEdtDescTxt_10" index="90">SOM</label></td>
																					<td><a href="#" onclick="sendServer('Link_OrderImport')" name="xxEdtDescTxt_11" ezfname="xxEdtDescTxt_11"><label ezfout name="xxEdtDescTxt_11" ezfname="xxEdtDescTxt_11" index="90">S01234</label></a></td>
																					<td><font color="red"><label ezfout name="xxEdtDescTxt_14" ezfname="xxEdtDescTxt_14" index="90">Error</label></font></td>
																					<td><label ezfout name="xxEdtDescTxt_16" ezfname="xxEdtDescTxt_16" index="90">2015/02/24  7:01:00</label></td>
																					<td><label ezfout name="xxEdtDescTxt_17" ezfname="xxEdtDescTxt_17" index="90">DOE, JOHN</label></td>

																					<td><label ezfout name="xxEdtDescTxt_18" ezfname="xxEdtDescTxt_18" index="90">DEFAULT</label></td>
																					<td><label ezfout name="xxEdtDescTxt_19" ezfname="xxEdtDescTxt_19" index="90">CHICAGO</label></td>
																					<td><label ezfout name="xxEdtDescTxt_20" ezfname="xxEdtDescTxt_20" index="90">PURCHASE, CSA</label></td>
																					<td><label ezfout name="xxEdtDescTxt_21" ezfname="xxEdtDescTxt_21" index="90">PPS CASH</label></td>
																					<td><label ezfout name="xxEdtDescTxt_36" ezfname="xxEdtDescTxt_36" index="90">1</label></td>

																					<td><label ezfout name="xxEdtDescTxt_22" ezfname="xxEdtDescTxt_22" index="90">10012345</label></td>
																					<td><label ezfout name="xxEdtDescTxt_23" ezfname="xxEdtDescTxt_23" index="90">CHICAGO BULLS</label></td>
																					<td><label ezfout name="xxEdtDescTxt_23" ezfname="xxEdtDescTxt_23" index="90">100465</label></td>
																					<td><label ezfout name="xxEdtDescTxt_24" ezfname="xxEdtDescTxt_24" index="90">5667</label></td>
																					<td><label ezfout name="xxEdtDescTxt_25" ezfname="xxEdtDescTxt_25" index="90">CFS</label></td>
																					
																					<td><label ezfout name="xxEdtDescTxt_23" ezfname="xxEdtDescTxt_23" index="90">100333</label></td>
																					<td><label ezfout name="xxEdtDescTxt_26" ezfname="xxEdtDescTxt_26" index="90">100029</label></td>
																					<td><label ezfout name="xxEdtDescTxt_23" ezfname="xxEdtDescTxt_23" index="90">CHICAGO BULLS</label></td>
																					<td><label ezfout name="xxEdtDescTxt_23" ezfname="xxEdtDescTxt_23" index="90">100324</label></td>
																				</tr>
																				</ezf:skip>
																			</ezf:row>
																		</table>
																	</div><!-- rightTbl -->
																</div><!-- right table -->
															</div><!-- parent box -->
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
	<script type="text/javascript" defer>
	    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true);
	</script>
<!-- ###SCRIPT -->
<script type="text/javascript">
	function synchroHeaderRightScroll() {
		var topTBL    = document.getElementById( 'topHeaderTBL'    );
		var rightTBL  = document.getElementById( 'rightHeaderTBL'  );
		var leftTBL  = this.document.getElementById( 'leftHeaderTBL'  );
		topTBL.scrollLeft = rightTBL.scrollLeft;
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroScroll_fromHeaderLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftHeaderTBL'  );
		var rightTBL = this.document.getElementById( 'rightHeaderTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroLineRightScroll() {
		var topTBL    = document.getElementById( 'topLineTBL'    );
		var rightTBL  = document.getElementById( 'rightLineTBL'  );
		var leftTBL  = this.document.getElementById( 'leftLineTBL'  );
		topTBL.scrollLeft = rightTBL.scrollLeft;
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroScroll_fromLineLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftLineTBL'  );
		var rightTBL = this.document.getElementById( 'rightLineTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
</script>

<%-- **** Task specific area ends here **** --%>
