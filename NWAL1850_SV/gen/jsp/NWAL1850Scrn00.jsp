<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220603144539 --%>
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
			<input type="hidden" name="pageID" value="NWAL1850Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Search for Scheduling Agreement">
			


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
								<td width="96%"><li title = "Search for Scheduling Agreement" class="pTab_ON" ><a href="#">Schd Search</a></li></td>
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
			<div style="float:left">
			<div id='headInfo' style="width:1100px; display:block; margin:0px;padding:0px;">
				<table>
					<tr>
						<td>
							<table  border ="0">
								<col align="left" width="133">
								<col align="left" width="65">
								<col align="right" width="83">
								<col align="left" width="65">
								<tr>
									<td class="stab">Schd.Agreement Number(*)</td>
									<td><ezf:inputText name="schdAgmtNum" ezfName="schdAgmtNum" value="12345678" otherAttr=" size=\"8\" maxlength=\"10\" ezftoupper=\"\""/>
									</td>
									<td class="stab">Source Ref #(*)</td>
									<td><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="12345678" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
										<!-- <fieldset style="height:60px; width:363px;"> -->
										<fieldset style="height:60px; width:393px;">
										<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
										<legend>Sold To Customer</legend>
											<table>
												<col align="left" width="60">
												<col align="left" width="100">
												<col align="left" width="10">
												<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
												<!-- <col align="left" width="60"> -->
												<col align="left" width="88">
												<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
												<col align="left" width="100">
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" >Number(*)</ezf:anchor></td>
													<td>
														<ezf:inputText name="sellToCustCd" ezfName="sellToCustCd" value="1234567890" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/>
													</td>
													<td></td>
													<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
													<!-- <td class="stab"><a href="#" onclick="sendServer('OpenWin_SoldTo')">Location(*)</a></td> -->
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" >Sold To Code(*)</ezf:anchor></td>
													<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
													<td>
														<ezf:inputText name="soldToCustLocCd" ezfName="soldToCustLocCd" value="1234567890" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SoldTo" >Name(*)</ezf:anchor></td>
													<td colspan="4">
														<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
														<!-- <input type="text" name="dsAcctNm_SO" ezfname="dsAcctNm_SO" value="123456789012345678901234567890" size="38" maxlength="360" ezftoupper> -->
														<ezf:inputText name="dsAcctNm_SO" ezfName="dsAcctNm_SO" value="123456789012345678901234567890" otherAttr=" size=\"42\" maxlength=\"360\" ezftoupper=\"\""/>
														<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
										<!-- <fieldset style="height:60px; width:363px;"> -->
										<fieldset style="height:60px; width:393px;">
										<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
										<legend>Ship To Customer</legend>
											<table>
												<col align="left" width="60">
												<col align="left" width="100">
												<col align="left" width="10">
												<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
												<!-- <col align="left" width="60"> -->
												<col align="left" width="88">
												<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
												<col align="left" width="100">
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" >Number(*)</ezf:anchor></td>
													<td>
														<ezf:inputText name="shipToCustAcctCd" ezfName="shipToCustAcctCd" value="1234567890" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/>
													</td>
													<td></td>
													<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
													<!-- <td class="stab"><a href="#" onclick="sendServer('OpenWin_ShipTo')">Location(*)</a></td> -->
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" >Ship To Code(*)</ezf:anchor></td>
													<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
													<td>
														<ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" value="1234567890" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" >Name(*)</ezf:anchor></td>
													<td colspan="4	">
														<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
														<!-- <input type="text" name="dsAcctNm_SI" ezfname="dsAcctNm_SI" value="123456789012345678901234567890" size="38" maxlength="360" ezftoupper> -->
														<ezf:inputText name="dsAcctNm_SI" ezfName="dsAcctNm_SI" value="123456789012345678901234567890" otherAttr=" size=\"42\" maxlength=\"360\" ezftoupper=\"\""/>
														<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>	
							</table>
						</td>
						<td>
							<table>
								<col align="left" width="10">
								<col align="left" width="100">
								<col align="left" width="100">
								<col align="left" width="20">
								<col align="left" width="100">
								<col align="left" width="10">
								<col align="left" width="100">
								<col align="left" width="100">
								<col align="left" width="10">
								<col align="left" width="100">
								<tr>
									<td></td>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Merchandise" >Item Number(*)</ezf:anchor></td>
									<td colspan="3">
										<ezf:inputText name="mdseCd" ezfName="mdseCd" value="1234567890" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
									</td>
									<td></td>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Config" >Serial #(*)</ezf:anchor></td>
									<td colspan="3">
										<ezf:inputText name="serNum" ezfName="serNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
									</td>
								</tr>
								<tr>
									<td></td>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderCategory" >Category</ezf:anchor></td>
									<td colspan="3">
										<ezf:inputText name="dsOrdCatgDescTxt" ezfName="dsOrdCatgDescTxt" value="WWWWWWWWW1WWWWWWWWW2" otherEvent1="OnBlur_DeriveFromCategory" otherAttr=" size=\"31\" maxlength=\"50\" ezffocusout=\"OnBlur_DeriveFromCategory\" ezftoupper=\"\""/>
									</td>
									<td></td>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Config" >Configuration ID(*)</ezf:anchor></td>
									<td colspan="3">
										<ezf:inputText name="xxScrItem30Txt" ezfName="xxScrItem30Txt" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWW" otherAttr=" size=\"30\" maxlength=\"28\""/>
									</td>
								</tr>
								<tr>
									<td></td>
									<td class="stab">Reason</td>
									<td  colspan="3">
										<ezf:select name="dsOrdTpCd" ezfName="dsOrdTpCd" ezfBlank="1" ezfCodeName="dsOrdTpCd_CD" ezfDispName="dsOrdTpDescTxt_NM" otherAttr=" style=\"width: 223px; \""/>
									</td>
									<td></td>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Contract" >Contract #(*)</ezf:anchor></td>
									<td colspan="3">
										<ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
									</td>
								</tr>
								<tr>
									<td></td>
									<td class="stab">Schd. Create Date</td>
									<td>
										<table border="0" cellpadding="1" cellspacing="0">
											<tr style="padding:0;">
												<td><ezf:inputText name="schdAgmtCratDt_FR" ezfName="schdAgmtCratDt_FR" value="01/04/2016" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
												<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('schdAgmtCratDt_FR', 4);"></td>
											</tr>
										</table>
									</td>
									<td>-</td>
									
									<td>
										<table border="0" cellpadding="1" cellspacing="0">
											<tr style="padding:0;">
												<td><ezf:inputText name="schdAgmtCratDt_TO" ezfName="schdAgmtCratDt_TO" value="01/04/2016" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
												<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('schdAgmtCratDt_TO', 4);"></td>
											</tr>
										</table>
									</td>
									<td></td>
									<td class="stab">Delivery Hold</td>
									<td  colspan="3">
										<ezf:select name="schdAgmtDelyHldCd" ezfName="schdAgmtDelyHldCd" ezfCodeName="schdAgmtDelyHldCd_CD" ezfDispName="schdAgmtDelyHldDescTxt_NM" otherAttr=" style=\"width: 202px; \""/>
									</td>
								</tr>
								<tr>
									<td></td>
									<td class="stab">Schedule Status</td>
									<td  colspan="3">
										<ezf:select name="schdAgmtStsCd" ezfName="schdAgmtStsCd" ezfBlank="1" ezfCodeName="schdAgmtStsCd_CD" ezfDispName="schdAgmtStsDescTxt_NM" otherAttr=" style=\"width: 100px; \""/>
									</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td class="stab">Cust PO Number(*)</td>
									<td  colspan="3">
										<ezf:inputText name="custIssPoNum" ezfName="custIssPoNum" value="1234567890" otherAttr=" size=\"12\" maxlength=\"35\" style=\"width: 100px; \" ezftoupper=\"\""/>
									</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td class="stab">Created By(*)</td>
									<td  colspan="3">
										<ezf:inputText name="adminPsnCd" ezfName="adminPsnCd" value="1234567890" otherAttr=" size=\"12\" maxlength=\"8\" style=\"width: 100px; \" ezftoupper=\"\""/>
									</td>
									<td  colspan="5">
										<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			</div>
			</td>
			</tr>
			<tr>
			<td>
			<hr>

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
													<td align="right">
														<table cellpadding="0" cellspacing="0">
															<tr>
																<td>
																	<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																	<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																	<jsp:param name="TableNm"     value="A" />
																	<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
																	<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
																	<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
																	<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
																	<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
																	<jsp:param name="ViewMode"       value="FULL" />
																	</jsp:include>
																<ezf:skip>
																	<table border="0" cellpadding="0" cellspacing="0">
																		<col width="44"  align="center">
																		<col width="20"  align="center">
																		<col width="20"  align="center">
																		<col width="20"  align="center">
																		<col width="20"  align="center">
																		<col width="20"  align="center">
																		<col width="10"  align="center">
																		<col width="46"  align="center">
																		<col width="40"  align="right">
																		<col width="16"  align="center">
																		<col width="40"  align="right">
																		<col width="30"  align="center">
																		<col width="10">
																		<col width="50">
																		<col width="20">
																		<col width="50">
																		<col width="50">
																		<tr>
																			<td class="stab">Results</td>
																			<td class="stab">1</td>
																			<td class="stab">-</td>
																			<td class="stab">40</td>
																			<td class="stab">of</td>
																			<td class="stab">41</td>
																			<td>&nbsp;</td>
																			<td class="stab">Showing</td>
																			<td class="pOut">1</td>
																			<td class="stab">/</td>
																			<td class="pOut">2</td>
																			<td class="stab">page</td>
																			<td>&nbsp;</td>
																			<td><input type="button" class="pBtn3" value="Jump" name="PageJump" ezfname="PageJump" onclick="sendServer(this)"></td>
																			<td>&nbsp;</td>
																			<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" ezfname="PagePrev" onclick="sendServer(this)"></td>
																			<td><input type="button" class="pBtn3" value="Next" name="PageNext" ezfname="PageNext" onclick="sendServer(this)"></td>
																		</tr>
																	</table>
																</ezf:skip>	
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td></td>
										<td>
											<div style="float:left; display:block">
												<div id='leftTblHead' style="display:block;"></div>
												<div id='leftTbl' style="float:left; display:block; height:258px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
											</div>
											<div style="float:left">
												<div id='rightTblHead' style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" id="AHEAD" width="2295x" style="margin-right:20px">
														<col width="70" align="center">
														<col width="75" align="center">
														<col width="110" align="center">
														<col width="110" align="center">
														<col width="170" align="center">
														<col width="300" align="center">
														<col width="110" align="center">
														<col width="110" align="center">
														<col width="170" align="center">
														<col width="300" align="center">
														<col width="130" align="center">
														<col width="130" align="center">
														<col width="110" align="center">
														<col width="110" align="center">
														<col width="110" align="center">
														<col width="170" align="center">
														<col width="110" align="center">
														<col width="100" align="center">
														<col width="100" align="center">
														<col width="200" align="center">
														<tr height="24">
															<td id="AH0"  class="pClothBs colFix">Schedule #</td>
															<td id="AH1"  class="pClothBs">Source Ref #</td>
															<td id="AH2"  class="pClothBs">Sold To Acct #</td>
															<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
															<!-- <td id="AH3"  class="pClothBs">Sold To Loc #</td> -->
															<td id="AH3"  class="pClothBs">Sold To Code</td>
															<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
															<td id="AH4"  class="pClothBs">Sold To Acct Name</td>
															<td id="AH5"  class="pClothBs">Sold To Address</td>
															<td id="AH6"  class="pClothBs">Ship To Acct #</td>
															<!-- Mod Start 2017/09/21 H.Sugawara QC#19922 -->
															<!-- <td id="AH7"  class="pClothBs">Ship To Loc #</td> -->
															<td id="AH7"  class="pClothBs">Ship To Code</td>
															<!-- Mod End 2017/09/21 H.Sugawara QC#19922 -->
															<td id="AH8"  class="pClothBs">Ship To Acct Name</td>
															<td id="AH9"  class="pClothBs">Ship To Address</td>
															<td id="AH10" class="pClothBs">Category</td>
															<td id="AH11" class="pClothBs">Reason</td>
															<td id="AH12" class="pClothBs">Serial #</td>
															<td id="AH13" class="pClothBs">Configuration ID</td>
															<td id="AH14" class="pClothBs">Contract #</td>
															<td id="AH15" class="pClothBs">Delivery Hold</td>
															<td id="AH16" class="pClothBs">Schd. Create Date</td>
															<td id="AH17" class="pClothBs">Schedule Status</td>
															<td id="AH18" class="pClothBs">Cust PO #</td>
															<td id="AH19" class="pClothBs">Created By</td>
														</tr>
													</table>
												</div>
												<div id="rightTbl" style="width:1117px; height:275px; display:block; overflow:scroll; margin:0px; padding:0px;">
													<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed;">
														<col width="70" align="center">
														<col width="75" align="center">
														<col width="110" align="center">
														<col width="110" align="center">
														<col width="170" align="center">
														<col width="300" align="center">
														<col width="110" align="center">
														<col width="110" align="center">
														<col width="170" align="center">
														<col width="300" align="center">
														<col width="130" align="center">
														<col width="130" align="center">
														<col width="110" align="center">
														<col width="110" align="center">
														<col width="110" align="center">
														<col width="170" align="center">
														<col width="110" align="center">
														<col width="100" align="center">
														<col width="100" align="center">
														<col width="200" align="center">
														
														<ezf:row ezfHyo="A">
															<tr height="23">
																<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_SchdAgmtEntry" ><ezf:label name="schdAgmtNum_A" ezfName="schdAgmtNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																<td><ezf:inputText name="cpoOrdNum_A" ezfName="cpoOrdNum_A" value="01234567" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"8\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="sellToCustCd_A" ezfName="sellToCustCd_A" value="2633533" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="soldToCustLocCd_A" ezfName="soldToCustLocCd_A" value="4265271" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="dsAcctNm_AO" ezfName="dsAcctNm_AO" value="OMNICARE INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"360\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="xxAllLineAddr_AO" ezfName="xxAllLineAddr_AO" value="201 E CHESTNUT ST ARCHER CITY NURSING CENTER ARCHER CITY,TX 76351-0000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"290\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="shipToCustAcctCd_A" ezfName="shipToCustAcctCd_A" value="123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="shipToCustCd_A" ezfName="shipToCustCd_A" value="2633533" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="dsAcctNm_AH" ezfName="dsAcctNm_AH" value="OMNICARE INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"360\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="xxAllLineAddr_AH" ezfName="xxAllLineAddr_AH" value="201 E CHESTNUT ST ARCHER CITY NURSING CENTER ARCHER CITY,TX 76351-0000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"290\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="dsOrdCatgDescTxt_A" ezfName="dsOrdCatgDescTxt_A" value="ESS-SUPPLY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="dsOrdTpDescTxt_A" ezfName="dsOrdTpDescTxt_A" value="ESS-CASH" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="serNum_A" ezfName="serNum_A" value="331207175" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="svcConfigMstrPk_A" ezfName="svcConfigMstrPk_A" value="501007199" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"28\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="dsContrNum_A" ezfName="dsContrNum_A" value="0001016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="schdAgmtDelyHldDescTxt_A" ezfName="schdAgmtDelyHldDescTxt_A" value="Delivery Hold" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="schdAgmtCratDt_A" ezfName="schdAgmtCratDt_A" value="01/01/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"8\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="schdAgmtStsDescTxt_A" ezfName="schdAgmtStsDescTxt_A" value="ACTIVE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"60\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="custIssPoNum_A" ezfName="custIssPoNum_A" value="123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"35\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="xxPsnNm_A" ezfName="xxPsnNm_A" value="Yoko Taoka" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"60\" ezftoupper=\"\""/>
																</td>
															</tr>
														</ezf:row>
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
