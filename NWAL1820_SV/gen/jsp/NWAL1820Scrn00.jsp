<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170921110436 --%>
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
			<input type="hidden" name="pageID" value="NWAL1820Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Loan Order Search">
			


<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td>
		<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%"><li title = "Search for Scheduling Agreement" class="pTab_ON" ><a href="#">Loan Search</a></li></td>
								<td valign="bottom" align="center"><a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a></td>
								<td valign="bottom" align="center"><a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a></td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

			<!-- Body of Selected Tab -->

			<div class="pTab_BODY" style="width:100%; text-align:left;" >
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
						<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
						<td>
							<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
							<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
						</td>
					</tr>
				</table>
				<hr>
				<table border="0" cellspacing="0">
					<tr>
						<td valign="top">
							<fieldset>
							<table border="0" cellspacing="0">
								<col width="60">
								<col width="">
								<tr>
									<td valign="top" class="stab"><u><legend style="font-size:12px;">Search</legend></u></td>
									<td>
										<table border="0" cellspacing="0">
											<col width="130">
											<col width="100">
											<col width="30">
											<col width="100">
											<col width="210">
											<col width="30">
											<col width="105">
											<col width="200">
											<tr>
												<td class="stab">Order Number</td>
												<td><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
												<td></td>
												<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SerialSearch" >Serial Number</ezf:anchor></td>
												<td><ezf:inputText name="serNum" ezfName="serNum" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
												<td></td>
												<td class="stab">Category</td>
												<td><ezf:select name="dsOrdCatgCd" ezfName="dsOrdCatgCd" ezfBlank="1" ezfCodeName="dsOrdCatgCd_CD" ezfDispName="dsOrdCatgDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_OrderCategory')\"" otherAttr=" style=\"width: 145px; \""/></td>
											</tr>
											<tr>
												<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" >Customer Number</ezf:anchor></td>
												<td><ezf:inputText name="shipToCustAcctCd" ezfName="shipToCustAcctCd" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
												<td></td>
												<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_BranchSearch" >Sales Branch</ezf:anchor></td>
												<td><ezf:inputText name="coaBrDescTxt" ezfName="coaBrDescTxt" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
												<td></td>
												<td class="stab">Reason</td>
												<td><ezf:select name="dsOrdTpCd" ezfName="dsOrdTpCd" ezfBlank="1" ezfCodeName="dsOrdTpCd_CD" ezfDispName="dsOrdTpDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_OrderReason')\"" otherAttr=" style=\"width: 145px; \""/></td>
											</tr>
											<tr>
												<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" >Customer Name (*)</ezf:anchor></td>
												<td><ezf:inputText name="shipToCustAcctNm" ezfName="shipToCustAcctNm" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
												<td></td>
												<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SalesrepSearch" >Sales Rep</ezf:anchor></td>
												<td><ezf:inputText name="psnNum" ezfName="psnNum" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
												<td></td>
												<td class="stab">Sub Reason</td>
												<td><ezf:select name="dsOrdRsnCd" ezfName="dsOrdRsnCd" ezfBlank="1" ezfCodeName="dsOrdRsnCd_CD" ezfDispName="dsOrdRsnDescTxt_NM" otherAttr=" style=\"width: 145px; \""/></td>
											</tr>
											<tr>
												<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
												<!-- <td class="stab"><a href="#" onclick="sendServer('OpenWin_ShipTo')">Customer Location</a></td> -->
												<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" >Customer Ship To Code</ezf:anchor></td>
												<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
												<td><ezf:inputText name="addShipToCustCd" ezfName="addShipToCustCd" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
												<td></td>
												<td class="stab">Shipped Date</td>
												<td>
													<table border="0" cellpadding="1" cellspacing="0">
														<tr style="padding:0;">
															<td><ezf:inputText name="actlShipDt_FR" ezfName="actlShipDt_FR" value="01/04/2016" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
															<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('actlShipDt_FR', 4);"></td>
															<td>-</td>
															<td><ezf:inputText name="actlShipDt_TO" ezfName="actlShipDt_TO" value="01/04/2016" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
															<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('actlShipDt_TO', 4);"></td>
														</tr>
													</table>
												</td>
												<td></td>
												<td class="stab">Loan Due Date</td>
												<td>
													<table border="0" cellpadding="1" cellspacing="0">
														<tr style="padding:0;">
															<td><ezf:inputText name="xxTrxDt_FR" ezfName="xxTrxDt_FR" value="01/04/2016" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
															<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxTrxDt_FR', 4);"></td>
															<td>-</td>
															<td><ezf:inputText name="xxTrxDt_TO" ezfName="xxTrxDt_TO" value="01/04/2016" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
															<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxTrxDt_TO', 4);"></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td class="stab">Acquisition Number</td>
												<td><ezf:inputText name="aquNum" ezfName="aquNum" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							</fieldset>
						</td>
					</tr>
					<tr>
						<td class="stab"> Please perform a search to select an eligible sales transaction for conversion purposes.</td>
					</tr>
				</table>
				<hr></hr>
				<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
					<tr>
						<td>
							<div id="parentBoxA">
								<table border ="0" cellpadding="0" cellspacing="0" width="100%">
									<col width="5">
									<col width="">
									<tr>
										<td></td>
										<td>
											<table cellpadding="0" cellspacing="0">
												<col width="003">
												<col width="200">
												<col width="897">
												<tr>
													<td></td>
													<td>
													<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
													</td>
													<td>
														<div align="right">
															<%-- Pagenation --%>
															<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="A" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
															</jsp:include>
															<ezf:skip>
															<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
																<col>
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
																	<td></td>
																	<td class="stab">Showing</td>
																	<td><input type="text" value="1" size="4" maxlength="4" style="text-align:right" class="pPro" readOnly name="xxPageShowFromNum" ezfname="xxPageShowFromNum"></td>
																	<td class="stab">/</td>
																	<td><input type="text" size="4" maxlength="4" value="1" class="pPro" style="text-align:right" id="txtShowingTot" readOnly name="xxPageShowToNum" ezfname="xxPageShowToNum"></td>
																	<td class="stab">page</td>
																	<td>&nbsp;</td>
																	<td></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" disabled></td>
																</tr>
															</table>
															</ezf:skip>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr></tr>
									<tr>
										<td></td>
										<td>
											<div style="float:left; display:block">
												<div id='leftTblHead' style="display:block;"></div>
												<div id='leftTbl' style="float:left; display:block; height:298px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
											</div>
											<div style="float:left">
												<div id='rightTblHead' style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" id="AHEAD" width="2470" style="margin-right:20px">
														<col width="80" align="center">
														<col width="80" align="center">
														<col width="190" align="center">
														<col width="190" align="center">
														<col width="190" align="center">
														<col width="160" align="center">
														<col width="260" align="center">
														<col width="120" align="center">
														<col width="220" align="center">
														<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
														<!-- <col width="120" align="center"> -->
														<col width="150" align="center">
														<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
														<col width="190" align="center">
														<col width="80" align="center">
														<col width="260" align="center">
														<col width="85" align="center">
														<col width="85" align="center">
														<col width="80" align="center">
														<col width="80" align="center">
														
														<tr height="24">
															<td id="AH0"  class="pClothBs colFix">Order #</td>
															<td id="AH1"  class="pClothBs">Order Date</td>
															<td id="AH2"  class="pClothBs">Order Category</td>
															<td id="AH3"  class="pClothBs">Order Reason</td>
															<td id="AH4"  class="pClothBs">Sub Reason</td>
															<td id="AH5"  class="pClothBs">Order Status</td>
															<td id="AH6"  class="pClothBs">Acquisition Number</td>
															<td id="AH7"  class="pClothBs">Customer Number</td>
															<td id="AH8"  class="pClothBs">Customer Name</td>
															<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
															<!-- <td id="AH9"  class="pClothBs">Customer Location</td> -->
															<td id="AH9"  class="pClothBs">Customer Ship To Code</td>
															<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
															<td id="AH10" class="pClothBs">Salesrep</td>
															<td id="AH11" class="pClothBs">Sales Branch</td>
															<td id="AH12" class="pClothBs">Branch Name</td>
															<td id="AH13" class="pClothBs">Shipped Date</td>
															<td id="AH14" class="pClothBs">Loan Due Date</td>
															<td id="AH15" class="pClothBs">Loan Period</td>
															<td id="AH16" class="pClothBs">Loan Lines</td>
														</tr>
													</table>
												</div>
												<div id="rightTbl" style="width:1117px; height:315px; display:block; overflow:scroll; margin:0px; padding:0px;">
													<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed;">
														<col width="80"  align="center">
														<col width="80"  align="center">
														<col width="190" align="center">
														<col width="190" align="center">
														<col width="190" align="center">
														<col width="160" align="center">
														<col width="260" align="center">
														<col width="120" align="center">
														<col width="220" align="center">
														<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
														<!-- <col width="120" align="center"> -->
														<col width="150" align="center">
														<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
														<col width="190" align="center">
														<col width="80"  align="left">
														<col width="260" align="center">
														<col width="85"  align="center">
														<col width="85"  align="center">
														<col width="80"  align="right">
														<col width="80"  align="right">
														
														<ezf:row ezfHyo="A">
															<tr height="23">
																<td>
																	<ezf:anchor name="cpoOrdNum_LK" ezfName="cpoOrdNum_LK" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_LoanOrderConversionSearch" otherAttr=" id=\"cpoOrdNum_LK\" ezfanchortext=\"\""><ezf:label name="cpoOrdNum_A" ezfName="cpoOrdNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																</td>
																<td><ezf:inputText name="xxCpoOrdDt_A" ezfName="xxCpoOrdDt_A" value="07/01/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"8\" style=\"border:none; background-color:transparent\""/>
																</td>
																<td><ezf:inputText name="dsOrdCatgDescTxt_A" ezfName="dsOrdCatgDescTxt_A" value="LOAN, CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"20\" style=\"border:none; background-color:transparent\""/>
																</td>
																<td><ezf:inputText name="dsOrdTpDescTxt_A" ezfName="dsOrdTpDescTxt_A" value="ESS-LOAN" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"20\" style=\"border:none; background-color:transparent\""/>
																</td>
																<td><ezf:inputText name="dsOrdRsnDescTxt_A" ezfName="dsOrdRsnDescTxt_A" value="MARKETING" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"360\" style=\"border:none; background-color:transparent\""/>
																</td>
																<td><ezf:inputText name="ordHdrStsNm_A" ezfName="ordHdrStsNm_A" value="Booked" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"21\" style=\"border:none; background-color:transparent\""/>
																</td>
																<td><ezf:inputText name="aquNum_A" ezfName="aquNum_A" value="SOM1234" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\" maxlength=\"20\" style=\"border:none; background-color:transparent\""/>
																</td>
																<td><ezf:inputText name="shipToCustAcctCd_A" ezfName="shipToCustAcctCd_A" value="12321" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" style=\"border:none; background-color:transparent\""/>
																</td>
																<td><ezf:inputText name="shipToCustAcctNm_A" ezfName="shipToCustAcctNm_A" value="OMNICARE INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"360\" style=\"border:none; background-color:transparent\""/>
																</td>
																<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
																<!-- <td><input type="text" class="pPro" size="15" maxlength="35" value="1234567890" name="addShipToCustCd_A" ezfname="addShipToCustCd_A" ezfhyo="A" style="border:none; background-color:transparent"> -->
																<td><ezf:inputText name="addShipToCustCd_A" ezfName="addShipToCustCd_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"35\" style=\"border:none; background-color:transparent\""/>
																<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
																</td>
																<td><ezf:inputText name="slsRepTocNm_A" ezfName="slsRepTocNm_A" value="XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"25\" style=\"border:none; background-color:transparent\""/>
																</td>
																<td><ezf:inputText name="coaBrCd_A" ezfName="coaBrCd_A" value="701" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"border:none; background-color:transparent\""/>
																</td>
																<td><ezf:inputText name="coaBrDescTxt_A" ezfName="coaBrDescTxt_A" value="PHILLY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\" maxlength=\"35\" style=\"border:none; background-color:transparent\""/>
																</td>
																<td><ezf:inputText name="actlShipDt_A" ezfName="actlShipDt_A" value="07/01/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent\""/>
																</td>
																<td><ezf:inputText name="xxTrxDt_A" ezfName="xxTrxDt_A" value="07/01/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent\""/>
																</td>
																<td><ezf:inputText name="loanPerDaysAot_A" ezfName="loanPerDaysAot_A" value="   12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"5\" style=\"border:none; background-color:transparent\""/>
																</td>
																<td><ezf:inputText name="xxDtlCnt_A" ezfName="xxDtlCnt_A" value="    3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"5\" style=\"border:none; background-color:transparent\""/>
																</td>
															</tr>
														</ezf:row>
														<ezf:skip>
														<tr height="23">
															<td><label ezfout><a href="#">1234567890</a></label></td>
															<td><label ezfout>07/01/2014</label></td>
															<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXX5</label></td>
															<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXX5</label></td>
															<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXX5</label></td>
															<td><label ezfout>XXXXXXXXX1XXXXXXXXX2</label></td>
															<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXX5</label></td>
															<td><label ezfout>XXXXXXXXX1XXXXXXXXX2</label></td>
															<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
															<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3</label></td>
															<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXX5</label></td>
															<td><label ezfout>122</label></td>
															<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXX5</label></td>
															<td><label ezfout>07/01/2014</label></td>
															<td><label ezfout>07/01/2014</label></td>
															<td><label ezfout>1,234</label></td>
															<td><label ezfout>1,234</label></td>
														</tr>
														</ezf:skip>
													</table>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<script type="text/javascript" defer>
					S21TableUI.initialize("parentBoxA", "AHEAD", "A", 1, false, false);
				</script>
				<br>
			</div><!-- pTab_BODY -->
		</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
