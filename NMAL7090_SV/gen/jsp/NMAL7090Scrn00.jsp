<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160726172849 --%>
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
			<input type="hidden" name="pageID" value="NMAL7090Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Item Supersession">

<center>
	<table width="1133" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ###################### HEAD ################################################################################################################ --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Price Item Supersession" class="pTab_ON"><a href="#">Price Item Supersession</a></li>
									</div>
								</td>
								<td valign="bottom" align="center">
									<a href="#">
										<img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0">
									</a>
								</td>
								<td valign="bottom" align="center">
									<a href="#">
										<img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0">
									</a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
<%-- ###################### BODY ################################################################################################################ --%>
				<div class="pTab_BODY">
					<fieldset style="margin-left:3px; width:1110px;">
	<%-- ###################### SEARCH ########################################################################################################## --%>
						<table border="0" cellpadding="1" cellspacing="0" width="540" height="15"  rules="none"  style="padding: 1px; margin-bottom: 2px;">
							<tr>
								<td valign="top">
									<table cellpadding="0" border="0">
										<col align="left" width="80">
										<col align="left" width="100">
										<col align="left" width="30">
										<col align="left" width="65">
										<col align="left" width="100">
										<col align="left" width="50">
										<col align="left" width="80">
										<tr>
											<td class="stab">Add Date From</td>
											<td>
												<ezf:inputText name="supdCratDt_FR" ezfName="supdCratDt_FR" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" id=\"supdCratDt_FR\" ezftoupper=\"\""/>
												<img src="img/calendar.gif" class="pCalendar" onclick="calendar('supdCratDt_FR', 4);" >
											</td>
											<td>&nbsp;</td>
											<td class="stab">Add Date To</td>
											<td>
												<ezf:inputText name="supdCratDt_TO" ezfName="supdCratDt_TO" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" id=\"supdCratDt_TO\" ezftoupper=\"\""/>
												<img src="img/calendar.gif" class="pCalendar" onclick="calendar('supdCratDt_TO', 4);" >
											</td>
											<td>&nbsp;</td>
											<td>
												<ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" otherAttr=" id=\"Search\""/>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
	<%-- ###################### ITEM SUPERSESSION LIST ########################################################################################## --%>
						<table border="0" cellpadding="1" cellspacing="0" width="990" height="110"  rules="none"  style="padding: 1px; margin-bottom: 2px;">
							<tr>
								<td align="top">
									<table border="0" width="990">
										<col align="left" width="990">
										<tr>
											<td>
<%-- Pagenation area start ============================================================== --%>
												<table border="0" cellpadding="0" cellspacing="0">
													<col width="330px" align="left">
													<col width="230px" align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
													<col width="124x" align="right">
													<col width="" align="right">
													<col width="296px" align="right">
													<tr>
														<td class="stab"><b>Item Supersession List (defaulted From Inventory Control)</b></td>
														<%-- =============== Filters Parts =============== --%>
														<td>
															<table>
																<tr height="20px">
																	<td>
																		<ezf:inputHidden name="xxComnColOrdTxt_A" ezfName="xxComnColOrdTxt_A" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt_A\""/>
																	</td>
																</tr>
															</table>
														</td>
														<td></td>
														<td></td>
														<%-- =============== Paging Parts =============== --%>
														<td>
															<div align="right">
																<ezf:skip>
																	<table border="0" cellpadding="0" cellspacing="0">
																		<col >
																		<col width="40"  align="right">
																		<col width="16"  align="center">
																		<col width="40"  align="right">
																		<col width="16"  align="center">
																		<col width="40"  align="right">
																		<col width="10">
																		<col width="0">
																		<col width="1">
																		<col width="0">
																		<tr>
																			<td class="stab">Showing</td>
																			<td class="pOut">1</td>
																			<td class="stab">to</td>
																			<td class="pOut">20</td>
																			<td class="stab">of</td>
																			<td class="pOut">200</td>
																			<td> </td>
																			<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
																			<td></td>
																			<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
																			<td></td>
																		</tr>
																	</table>
																</ezf:skip>
															</div>
															<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="A" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
															</jsp:include>
														</td>
													</tr>
												</table>
<%-- Pagenation area end ================================================================ --%>
												<div id="rightFilterTBL_Item" style="width:1000; display:block; overflow:hidden;">
													<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0" id="AHEAD" style="margin-right:0px">
														<col width="120" align="center">
														<col width="120" align="center">
														<col width="145" align="center">
														<col width="120" align="center">
														<col width="120" align="center">
														<col width="145" align="center">
														<col width="110" align="left">
														<col width="90" align="left">
														<tbody>
															<tr height="22">
																<td>
																	<ezf:inputText name="supdFromMdseCd_FA" ezfName="supdFromMdseCd_FA" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:inputText name="mdseDescShortTxt_FO" ezfName="mdseDescShortTxt_FO" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"16\" maxlength=\"250\""/>
																</td>
																<td>
																</td>
																<td>
																	<ezf:inputText name="supdToMdseCd_FA" ezfName="supdToMdseCd_FA" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:inputText name="mdseDescShortTxt_FN" ezfName="mdseDescShortTxt_FN" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"16\" maxlength=\"250\""/>
																</td>
																<td>
																</td>
																<td>
																	<ezf:inputText name="supdCratDt_FA" ezfName="supdCratDt_FA" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" id=\"supdCratDt_FA\" ezftoupper=\"\""/>
																	<img src="img/calendar.gif" class="pCalendar" onclick="calendar('supdCratDt_FA', 4);" >
																</td>
																<td>
																	<ezf:inputButton name="Apply_A" value="Apply" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" ezftoupper=\"\""/>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
												<!-- Right TBL Header -->
												<div id="rightTopTBL_Item" style="width:1000; display:block; overflow:hidden;" >
													<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" style="margin-right:0px">
														<col width="120" align="center">
														<col width="120" align="center">
														<col width="145" align="center">
														<col width="120" align="center">
														<col width="120" align="center">
														<col width="145" align="center">
														<col width="80" align="center">
														<col width="100" align="center">
														<tr class="pEvenNumberBGcolor" height="34">
															<td class="pClothBs">Old Item Code</td>
															<td class="pClothBs">Old Item Code Description</td>
															<td class="pClothBs">Old Item Cost</td>
															<td class="pClothBs">New Item Code</td>
															<td class="pClothBs">New Item Code Description</td>
															<td class="pClothBs">New Item Cost</td>
															<td class="pClothBs">Add Date</td>
															<td class="pClothBs">Action</td>
														</tr>
													</table>
												</div>
												<!-- Right TBL Main -->
												<div id="rightTBL_Item" style="width:960; height:68px; display:block; overflow-y:scroll;">
													<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" style="margin-right:0px">
														<col width="120" align="center">
														<col width="120" align="center">
														<col width="145" align="center">
														<col width="120" align="center">
														<col width="120" align="center">
														<col width="145" align="center">
														<col width="80" align="center">
														<col width="100" align="center">
														<tbody>
															<ezf:row ezfHyo="A">
															<tr height="22">
																<td align="left">
																	<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Link_ItemMstrOld" ><ezf:label name="supdFromMdseCd_A" ezfName="supdFromMdseCd_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																</td>
																<td>
																	<ezf:inputText name="mdseDescShortTxt_AO" ezfName="mdseDescShortTxt_AO" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"250\""/>
																</td>
																<td>
																	<ezf:inputText name="thisMthTotStdCostAmt_AO" ezfName="thisMthTotStdCostAmt_AO" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td align="left">
																	<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Link_ItemMstrNew" ><ezf:label name="supdToMdseCd_A" ezfName="supdToMdseCd_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																</td>
																<td>
																	<ezf:inputText name="mdseDescShortTxt_AN" ezfName="mdseDescShortTxt_AN" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"250\""/>
																</td>
																<td>
																	<ezf:inputText name="thisMthTotStdCostAmt_AN" ezfName="thisMthTotStdCostAmt_AN" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:inputText name="supdCratDt_A" ezfName="supdCratDt_A" value="mm/dd/yyyy" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"supdRelnStageDt_A1\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:inputButton name="UpdatePriceList" value="Update Price List" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn8" otherAttr=" ezftoupper=\"\""/>
																</td>
															</tr>
															</ezf:row>
														</tbody>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
	<%-- ###################### New Requests Control ########################################################################################## --%>
						<table border="0" cellpadding="1" cellspacing="0" width="1100" height="180"  rules="none"  style="padding: 1px; margin-bottom: 2px;">
							<tr>
								<td align="top">
									<table border="0" width="1100">
										<col align="left" width="1100">
										<tr>
											<td>
<%-- Pagenation area start ============================================================== --%>
												<table border="0" cellpadding="0" cellspacing="0">
													<!--<col width="186px" align="left">-->
													<col width="180px" align="left">
													<col width="20px" align="left">
													<col width="5px" align="left">
													<col width="40px" align="left">
													<col width="5px" align="left">
													<col width="20px" align="left">
													<col width="5px" align="left">
													<col width="20px" align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
													<col width="5px"  align="right">
													<col width="5px"  align="right">
													<col width="296px"  align="right">
													<tr>
														<td class="stab"><b>New Requests</b></td>
														<td>
															<ezf:inputButton name="InsertRow" value="Insert Row" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn7" otherAttr=" ezftoupper=\"\""/>
														</td>
														<td></td>
														<td>
															<ezf:inputFile name="xxFileData" ezfName="xxFileData" otherAttr=" size=\"40\" maxlength=\"9999\""/>
														</td>
														<td></td>
														<td>
															<ezf:inputButton name="Upload" value="Upload" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn6" />
														</td>
														<td></td>
														<td>
															<ezf:inputButton name="Template" value="Template" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn6" />
														</td>
														<td></td>
														<%-- =============== Filters Parts =============== --%>
														<td>
															<table>
																<tr height="20px">
																	<td>
																		<ezf:inputHidden name="xxComnColOrdTxt_B" ezfName="xxComnColOrdTxt_B" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt_B\""/>
																	</td>
																</tr>
															</table>
														</td>
														<%-- =============== Paging Parts =============== --%>
														<td>
															<div align="right">
																<ezf:skip>
																	<table border="0" cellpadding="0" cellspacing="0">
																		<col >
																		<col width="40"  align="right">
																		<col width="16"  align="center">
																		<col width="40"  align="right">
																		<col width="16"  align="center">
																		<col width="40"  align="right">
																		<col width="10">
																		<col width="0">
																		<col width="1">
																		<col width="0">
																		<tr>
																			<td class="stab">Showing</td>
																			<td class="pOut">1</td>
																			<td class="stab">to</td>
																			<td class="pOut">20</td>
																			<td class="stab">of</td>
																			<td class="pOut">200</td>
																			<td> </td>
																			<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
																			<td></td>
																			<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
																			<td></td>
																		</tr>
																	</table>
																</ezf:skip>
															</div>
															<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="B" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum_B" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum_B" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum_B" />
															</jsp:include>
														</td>
													</tr>
												</table>
<%-- Pagenation area end ================================================================ --%>
												<div id="rightFilterTBL_New" style="width:1084; display:block; overflow:hidden;" onScroll="synchroScrolLeft( this.id, new Array( 'rightTBL_New' ) );">
													<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0" id="B" style="margin-right:0px">
														<col width="90" align="center"><!-- Copy -->
														<col width="160" align="center"><!-- Old Item Code -->
														<col width="120" align="center"><!-- Old Item Code Description -->
														<col width="145" align="center"><!-- Old Item Cost -->
														<col width="160" align="center"><!-- New Item Code -->
														<col width="120" align="center"><!-- New Item Code Description -->
														<col width="145" align="center"><!-- New Item Cost -->
														<col width="100" align="center"><!-- Update Price List Type -->
														<col width="100" align="center"><!-- Update Price List Action -->
														<col width="2000" align="center"><!-- BLANK -->
														<tbody>
															<tr height="22">
																<td>
																	<ezf:inputButton name="Apply_B" value="Apply" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Old Item Code -->
																	<ezf:inputText name="oldMdseCd_FB" ezfName="oldMdseCd_FB" value="WWWWWWWWW1WWWWWX" otherAttr=" size=\"22\" maxlength=\"16\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Old Item Code Description -->
																	<ezf:inputText name="oldMdseDescShortTxt_FB" ezfName="oldMdseDescShortTxt_FB" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"16\" maxlength=\"250\""/>
																</td>
																<td>
																	<!-- Old Item Cost -->
																</td>
																<td>
																	<!-- New Item Code -->
																	<ezf:inputText name="newMdseCd_FB" ezfName="newMdseCd_FB" value="WWWWWWWWW1WWWWWX" otherAttr=" size=\"22\" maxlength=\"16\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Item Code Description -->
																	<ezf:inputText name="newMdseDescShortTxt_FB" ezfName="newMdseDescShortTxt_FB" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"16\" maxlength=\"250\""/>
																</td>
																<td>
																	<!-- New Item Cost -->
																</td>
																<td>
																	<!-- Update Price List Type -->
																	<ezf:select name="prcListTpCd_FS" ezfName="prcListTpCd_FS" ezfBlank="1" ezfCodeName="prcListTpCd_FP" ezfDispName="prcListTpDescTxt_FP" otherAttr=" style=\"width: 100px\""/>
																</td>
																<td>
																	<!-- Update Price List Action -->
																	<ezf:select name="prcListActTpCd_FS" ezfName="prcListActTpCd_FS" ezfBlank="1" ezfCodeName="prcListActTpCd_FP" ezfDispName="prcListActTpDescTxt_FP" otherAttr=" style=\"width: 100px\""/>
																</td>
																<td/>
															</tr>
														</tbody>
													</table>
												</div>
												<!-- Right TBL Header -->
												<div id="rightTopTBL_New" style="width:1084; display:block; overflow:hidden;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTBL_New' ) );">
													<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" style="margin-right:0px">
														<col width="40" align="center"><!-- Copy -->
														<col width="50" align="center"><!-- ID -->
														<col width="160" align="center"><!-- Old Item Code -->
														<col width="120" align="center"><!-- Old Item Code Description -->
														<col width="145" align="center"><!-- Old Item Cost -->
														<col width="160" align="center"><!-- New Item Code -->
														<col width="120" align="center"><!-- New Item Code Description -->
														<col width="145" align="center"><!-- New Item Cost -->
														<col width="100" align="center"><!-- Update Price List Type -->
														<col width="100" align="center"><!-- Update Price List Action -->
														<col width="160" align="center"><!-- Price List ID's -->
														<col width="80" align="center"><!-- Retain Original Price -->
														<col width="145" align="center"><!-- New Price -->
														<col width="145" align="center"><!-- New Min Price -->
														<col width="145" align="center"><!-- New Max Price -->
														<col width="145" align="center"><!-- New Lease Payment -->
														<col width="145" align="center"><!-- New Min Lease Payment -->
														<col width="145" align="center"><!-- New Max Lease Payment -->
														<col width="50" align="center"><!-- Submit -->
														<col width="50" align="center"><!-- discard -->
														<tr class="pEvenNumberBGcolor" height="34">
															<td class="pClothBs">Copy</td>
															<td class="pClothBs">ID</td>
															<td class="pClothBs">Old Item Code</td>
															<td class="pClothBs">Old Item Code Description</td>
															<td class="pClothBs">Old Item Cost</td>
															<td class="pClothBs">New Item Code</td>
															<td class="pClothBs">New Item Code Description</td>
															<td class="pClothBs">New Item Cost</td>
															<td class="pClothBs">Update Price List Type</td>
															<td class="pClothBs">Update Price List Action</td>
															<td class="pClothBs">Price List ID's</td>
															<td class="pClothBs">Retain Original Price</td>
															<td class="pClothBs">New Price</td>
															<td class="pClothBs">New Min Price</td>
															<td class="pClothBs">New Max Price</td>
															<td class="pClothBs">New Lease Payment</td>
															<td class="pClothBs">New Min Lease Payment</td>
															<td class="pClothBs">New Max Lease Payment</td>
															<td class="pClothBs">Submit
																<ezf:inputCheckBox name="submtFlg_BA" ezfName="submtFlg_BA" value="Y" onClick="sendServer('OnChange_SubmitAll')" otherAttr=" id=\"submtFlg_BA\""/>
															</td>
															<td class="pClothBs">Discard
																<ezf:inputCheckBox name="rqstDscdFlg_BA" ezfName="rqstDscdFlg_BA" value="Y" onClick="sendServer('OnChange_DiscardAll')" otherAttr=" id=\"rqstDscdFlg_BA\""/>
															</td>
														</tr>
													</table>
												</div>
												<!-- Right TBL Main -->
												<div id="rightTBL_New" style="width:1100; height:105px; display:block; overflow:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTopTBL_New' ) );synchroScrollLeft( this.id, new Array( 'rightFilterTBL_New' ) );">
													<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="B" style="margin-right:0px">
														<col width="40" align="center"><!-- Copy -->
														<col width="50" align="center"><!-- ID -->
														<col width="160" align="center"><!-- Old Item Code -->
														<col width="120" align="center"><!-- Old Item Code Description -->
														<col width="145" align="center"><!-- Old Item Cost -->
														<col width="160" align="center"><!-- New Item Code -->
														<col width="120" align="center"><!-- New Item Code Description -->
														<col width="145" align="center"><!-- New Item Cost -->
														<col width="100" align="center"><!-- Update Price List Type -->
														<col width="100" align="center"><!-- Update Price List Action -->
														<col width="160" align="center"><!-- Price List ID's -->
														<col width="80" align="center"><!-- Retain Original Price -->
														<col width="145" align="center"><!-- New Price -->
														<col width="145" align="center"><!-- New Min Price -->
														<col width="145" align="center"><!-- New Max Price -->
														<col width="145" align="center"><!-- New Lease Payment -->
														<col width="145" align="center"><!-- New Min Lease Payment -->
														<col width="145" align="center"><!-- New Max Lease Payment -->
														<col width="50" align="center"><!-- Submit -->
														<col width="50" align="center"><!-- discard -->
														<tbody>
															<ezf:row ezfHyo="B">
															<tr height="22">
																<td>
																	<!-- Copy -->
																	<ezf:inputButton name="Copy" value="." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- ID -->
																	<ezf:inputText name="prcListEquipRqstPk_B" ezfName="prcListEquipRqstPk_B" value="WWWWWX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"28\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Old Item Code -->
																	<ezf:inputText name="oldMdseCd_B" ezfName="oldMdseCd_B" value="WWWWWWWWW1WWWWWX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
																	<ezf:inputButton name="OpenWin_ItemOld" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Old Item Code Description -->
																	<ezf:inputText name="oldMdseDescShortTxt_B" ezfName="oldMdseDescShortTxt_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"250\""/>
																</td>
																<td>
																	<!-- Old Item Cost -->
																	<ezf:inputText name="oldTmthTotStdCostAmt_B" ezfName="oldTmthTotStdCostAmt_B" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Item Code -->
																	<ezf:inputText name="newMdseCd_B" ezfName="newMdseCd_B" value="WWWWWWWWW1WWWWWX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
																	<ezf:inputButton name="OpenWin_ItemNew" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Item Code Description -->
																	<ezf:inputText name="newMdseDescShortTxt_B" ezfName="newMdseDescShortTxt_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"250\""/>
																</td>
																<td>
																	<!-- New Item Cost -->
																	<ezf:inputText name="newTmthTotStdCostAmt_B" ezfName="newTmthTotStdCostAmt_B" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Update Price List Type -->
																	<ezf:select name="prcListTpCd_BS" ezfName="prcListTpCd_BS" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcListTpCd_BP" ezfDispName="prcListTpDescTxt_BP" ezfCodeDispHyo="B" otherAttr=" style=\"width: 100px\""/>
																</td>
																<td>
																	<!-- Update Price List Action -->
																	<ezf:select name="prcListActTpCd_BS" ezfName="prcListActTpCd_BS" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcListActTpCd_BP" ezfDispName="prcListActTpDescTxt_BP" ezfCodeDispHyo="B" otherAttr=" style=\"width: 100px\""/>
																</td>
																<td>
																	<!-- Price List ID's -->
																	<ezf:inputText name="prcListsDescTxt_B" ezfName="prcListsDescTxt_B" value="WWWWWWWWW1WWWWWW" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"4000\" ezftoupper=\"\""/>
																	<ezf:inputButton name="OpenWin_PrcList" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Retain Original Price -->
																	<ezf:inputCheckBox name="retanOrigPrcFlg_B" ezfName="retanOrigPrcFlg_B" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxSupdFlg_FW\""/>
																</td>
																<td>
																	<!-- New Price -->
																	<ezf:inputText name="newPrcAmt_B" ezfName="newPrcAmt_B" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Min Price -->
																	<ezf:inputText name="newMinPrcAmt_B" ezfName="newMinPrcAmt_B" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Max Price -->
																	<ezf:inputText name="newMaxPrcAmt_B" ezfName="newMaxPrcAmt_B" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Lease Payment -->
																	<ezf:inputText name="newMlyPmtAmt_B" ezfName="newMlyPmtAmt_B" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Min Lease Payment -->
																	<ezf:inputText name="newMinLeasePmtAmt_B" ezfName="newMinLeasePmtAmt_B" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Max Lease Payment -->
																	<ezf:inputText name="newMaxLeasePmtAmt_B" ezfName="newMaxLeasePmtAmt_B" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Submit -->
																	<ezf:inputCheckBox name="submtFlg_B" ezfName="submtFlg_B" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxSupdFlg_FW\""/>
																</td>
																<td>
																	<!-- discard -->
																	<ezf:inputCheckBox name="rqstDscdFlg_B" ezfName="rqstDscdFlg_B" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxSupdFlg_FW\""/>
																</td>
															</tr>
															</ezf:row>
														</tbody>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
	<%-- ###################### Historical Requests Control ########################################################################################## --%>
						<table border="0" cellpadding="1" cellspacing="0" width="1100" height="180"  rules="none"  style="padding: 1px; margin-bottom: 2px;">
							<tr>
								<td align="top">
									<table border="0" width="1100">
										<col align="left" width="1100">
										<tr>
											<td>
<%-- Pagenation area start ============================================================== --%>
												<table border="0" cellpadding="0" cellspacing="0">
													<col width="186px" align="left">
													<col width="395px" align="left">
													<col width="15px" align="left">
													<col width="40px" align="left">
													<col width="15px" align="left">
													<col width="20px" align="left">
													<col width="10px" align="left">
													<col width="10px"  align="right">
													<col width="15px"  align="right">
													<col width="296px"  align="left">
													<tr>
														<td class="stab"><b>Historical Requests</b></td>
														<td></td>
														<td></td>
														<td>
															<ezf:inputButton name="Apply_C" value="Apply" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" ezftoupper=\"\""/>
														</td>
														<td></td>
														<td>
															<ezf:inputButton name="Refresh" value="Refresh" htmlClass="pBtn6" />
														</td>
														<td></td>
														<td></td>
														<%-- =============== Filters Parts =============== --%>
														<td>
															<table>
																<tr height="20px">
																	<td>
																		<ezf:inputHidden name="xxComnColOrdTxt_C" ezfName="xxComnColOrdTxt_C" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt_C\""/>
																	</td>
																</tr>
															</table>
														</td>
														<%-- =============== Paging Parts =============== --%>
														<td>
															<div align="right">
																<ezf:skip>
																	<table border="0" cellpadding="0" cellspacing="0">
																		<col >
																		<col width="40"  align="right">
																		<col width="16"  align="center">
																		<col width="40"  align="right">
																		<col width="16"  align="center">
																		<col width="40"  align="right">
																		<col width="10">
																		<col width="0">
																		<col width="1">
																		<col width="0">
																		<tr>
																			<td class="stab">Showing</td>
																			<td class="pOut">1</td>
																			<td class="stab">to</td>
																			<td class="pOut">20</td>
																			<td class="stab">of</td>
																			<td class="pOut">200</td>
																			<td> </td>
																			<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
																			<td></td>
																			<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
																			<td></td>
																		</tr>
																	</table>
																</ezf:skip>
															</div>
															<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="C" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum_C" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum_C" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum_C" />
															</jsp:include>
														</td>
													</tr>
												</table>
<%-- Pagenation area end ================================================================ --%>
												<div id="leftFilterTBL_His" style="width:625; display:block; overflow:hidden; float:left;">
													<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0" id="C" style="margin-right:0px">
														<col width="120" align="center"><!-- Old Item Code -->
														<col width="120" align="center"><!-- Old Item Code Description -->
														<col width="145" align="center"><!-- Old Item Cost -->
														<col width="120" align="center"><!-- New Item Code -->
														<col width="120" align="center"><!-- New Item Code Description -->
														<tbody>
															<tr height="22">
																<td>
																	<!-- Old Item Code -->
																	<ezf:inputText name="oldMdseCd_FC" ezfName="oldMdseCd_FC" value="WWWWWWWWW1WWWWWX" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Old Item Code Description -->
																	<ezf:inputText name="oldMdseDescShortTxt_FC" ezfName="oldMdseDescShortTxt_FC" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"16\" maxlength=\"250\""/>
																</td>
																<td>
																	<!-- Old Item Cost -->
																</td>
																<td>
																	<!-- New Item Code -->
																	<ezf:inputText name="newMdseCd_FC" ezfName="newMdseCd_FC" value="WWWWWWWWW1WWWWWX" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Item Code Description -->
																	<ezf:inputText name="newMdseDescShortTxt_FC" ezfName="newMdseDescShortTxt_FC" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"16\" maxlength=\"250\""/>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
												<div id="rightFilterTBL_His" style="width:458; display:block; overflow:hidden; float:left;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTBL_His' ) );">
													<table style="width:458;table-layout:fixed;" border="0" cellpadding="0" cellspacing="0" id="C" style="margin-right:0px">
														<col width="145" align="center"><!-- New Item Cost -->
														<col width="120" align="center"><!-- Update Price List Type -->
														<col width="120" align="center"><!-- Update Price List Action -->
														<col width="120" align="center"><!-- Price List ID's -->
														<col width="80" align="center"> <!-- Retain Original Price -->
														<col width="145" align="center"><!-- New Price -->
														<col width="145" align="center"><!-- New Min Price -->
														<col width="145" align="center"><!-- New Max Price -->
														<col width="145" align="center"><!-- New Lease Payment -->
														<col width="145" align="center"><!-- New Min Lease Payment -->
														<col width="145" align="center"><!-- New Max Lease Payment -->
														<col width="120" align="center"><!-- Status -->
														<col width="120" align="center"><!-- Process Log File -->
														<col width="1000" align="center"><!-- BLANK -->
														<tbody>
															<tr height="22">
																<td>
																	<!-- New Item Cost -->
																</td>
																<td>
																	<!-- Update Price List Type -->
																	<ezf:select name="prcListTpCd_CS" ezfName="prcListTpCd_CS" ezfBlank="1" ezfCodeName="prcListTpCd_CP" ezfDispName="prcListTpDescTxt_CP" otherAttr=" style=\"width: 100px\""/>
																</td>
																<td>
																	<!-- Update Price List Action -->
																	<ezf:select name="prcListActTpCd_CS" ezfName="prcListActTpCd_CS" ezfBlank="1" ezfCodeName="prcListActTpCd_CP" ezfDispName="prcListActTpDescTxt_CP" otherAttr=" style=\"width: 100px\""/>
																</td>
																<td>
																	<!-- Price List ID's -->
																	<ezf:inputText name="newPrcAmt_FC" ezfName="newPrcAmt_FC" value="WWWWWWWWW1WWWWWW" otherAttr=" size=\"16\" maxlength=\"4000\" ezftoupper=\"\""/>
																</td>
																<td>
																</td>
																<td>
																	<!-- New Price -->
																</td>
																<td>
																	<!-- New Min Price -->
																</td>
																<td>
																	<!-- New Max Price -->
																</td>
																<td>
																	<!-- New Lease Payment -->
																</td>
																<td>
																	<!-- New Min Lease Payment -->
																</td>
																<td>
																	<!-- New Max Lease Payment -->
																</td>
																<td>
																	<!-- Status -->
																	<ezf:select name="rqstStsTpCd_CS" ezfName="rqstStsTpCd_CS" ezfBlank="1" ezfCodeName="rqstStsTpCd_CP" ezfDispName="rqstStsTpDescTxt_CP" otherAttr=" style=\"width: 100px\""/>
																</td>
																<td>
																	<!-- Process Log File -->
																	<ezf:inputText name="xxAuthByNm_FC" ezfName="xxAuthByNm_FC" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWW" otherAttr=" size=\"16\" maxlength=\"28\" ezftoupper=\"\""/>
																</td>
																<td/>
															</tr>
														</tbody>
													</table>
												</div>
												<!-- Left TBL Header -->
												<div id="leftTopTBL_His" style="width:625; display:block; overflow:hidden; float:left;">
													<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" style="margin-right:0px">
														<col width="120" align="center"><!-- Old Item Code -->
														<col width="120" align="center"><!-- Old Item Code Description -->
														<col width="145" align="center"><!-- Old Item Cost -->
														<col width="120" align="center"><!-- New Item Code -->
														<col width="120" align="center"><!-- New Item Code Description -->
														<tr class="pEvenNumberBGcolor" height="34">
															<td class="pClothBs">Old Item Code</td>
															<td class="pClothBs">Old Item Code Description</td>
															<td class="pClothBs">Old Item Cost</td>
															<td class="pClothBs">New Item Code</td>
															<td class="pClothBs">New Item Code Description</td>
														</tr>
													</table>
												</div>
												<!-- Right TBL Header -->
												<div id="rightTopTBL_His" style="width:458; display:block; overflow:hidden; float:left;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTBL_His' ) );">
													<table style="width:458; table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" style="margin-right:0px">
														<col width="145" align="center"><!-- New Item Cost -->
														<col width="120" align="center"><!-- Update Price List Type -->
														<col width="120" align="center"><!-- Update Price List Action -->
														<col width="120" align="center"><!-- Price List ID's -->
														<col width="80" align="center"> <!-- Retain Original Price -->
														<col width="145" align="center"><!-- New Price -->
														<col width="145" align="center"><!-- New Min Price -->
														<col width="145" align="center"><!-- New Max Price -->
														<col width="145" align="center"><!-- New Lease Payment -->
														<col width="145" align="center"><!-- New Min Lease Payment -->
														<col width="145" align="center"><!-- New Max Lease Payment -->
														<col width="120" align="center"><!-- Status -->
														<col width="120" align="center"><!-- Process Log File -->
														<col width="120" align="center"><!-- Created By -->
														<col width="80" align="center"> <!-- Created Date -->
														<col width="120" align="center"><!-- Last Updated By -->
														<col width="80" align="center"> <!-- Last Updated Date -->
														<col width="1000" align="center"> <!-- BLANK -->
														<tr class="pEvenNumberBGcolor" height="34">
															<td class="pClothBs">New Item Cost</td>
															<td class="pClothBs">Update Price List Type</td>
															<td class="pClothBs">Update Price List Action</td>
															<td class="pClothBs">Price List ID's</td>
															<td class="pClothBs">Retain Original Price</td>
															<td class="pClothBs">New Price</td>
															<td class="pClothBs">New Min Price</td>
															<td class="pClothBs">New Max Price</td>
															<td class="pClothBs">New Lease Payment</td>
															<td class="pClothBs">New Min Lease Payment</td>
															<td class="pClothBs">New Max Lease Payment</td>
															<td class="pClothBs">Status</td>
															<td class="pClothBs">Process Log File</td>
															<td class="pClothBs">Created By</td>
															<td class="pClothBs">Created Date</td>
															<td class="pClothBs">Last Updated By</td>
															<td class="pClothBs">Last Updated Date</td>
															<td/>
														</tr>
													</table>
												</div>

												<!-- Left TBL Main -->
 												<div id="leftTBL_His" style="width:625; height:68px; display:block; overflow:hidden; float:left;" onScroll="synchroScrollTop( this.id, new Array( 'rightTBL_His' ) );">
													<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="C" style="margin-right:0px">
														<col width="120" align="center"><!-- Old Item Code -->
														<col width="120" align="center"><!-- Old Item Code Description -->
														<col width="145" align="center"><!-- Old Item Cost -->
														<col width="120" align="center"><!-- New Item Code -->
														<col width="120" align="center"><!-- New Item Code Description -->
														<tbody>
															<ezf:row ezfHyo="C">
															<tr height="22">
																<td>
																	<!-- Old Item Code -->
																	<ezf:inputText name="oldMdseCd_C" ezfName="oldMdseCd_C" value="WWWWWWWWW1WWWWWX" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Old Item Code Description -->
																	<ezf:inputText name="oldMdseDescShortTxt_C" ezfName="oldMdseDescShortTxt_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"250\""/>
																</td>
																<td>
																	<!-- Old Item Cost -->
																	<ezf:inputText name="oldTmthTotStdCostAmt_C" ezfName="oldTmthTotStdCostAmt_C" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="C" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Item Code -->
																	<ezf:inputText name="newMdseCd_C" ezfName="newMdseCd_C" value="WWWWWWWWW1WWWWWX" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Item Code Description -->
																	<ezf:inputText name="newMdseDescShortTxt_C" ezfName="newMdseDescShortTxt_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"250\""/>
																</td>
															</tr>
															</ezf:row>
														</tbody>
													</table>
												</div>

												<!-- Right TBL Main -->
												<div id="rightTBL_His" style="width:475; height:85px; display:block; overflow:scroll; float:left;"
												onScroll="synchroScrollTop( this.id, new Array( 'leftTBL_His' ) );synchroScrollLeft( this.id, new Array( 'rightFilterTBL_His' ) );synchroScrollLeft( this.id, new Array( 'rightTopTBL_His' ) );">
													<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="C" style="margin-right:0px">
														<col width="145" align="center"><!-- New Item Cost -->
														<col width="120" align="center"><!-- Update Price List Type -->
														<col width="120" align="center"><!-- Update Price List Action -->
														<col width="120" align="center"><!-- Price List ID's -->
														<col width="80" align="center"> <!-- Retain Original Price -->
														<col width="145" align="center"><!-- New Price -->
														<col width="145" align="center"><!-- New Min Price -->
														<col width="145" align="center"><!-- New Max Price -->
														<col width="145" align="center"><!-- New Lease Payment -->
														<col width="145" align="center"><!-- New Min Lease Payment -->
														<col width="145" align="center"><!-- New Max Lease Payment -->
														<col width="120" align="center"><!-- Status -->
														<col width="120" align="center"><!-- Process Log File -->
														<col width="120" align="center"><!-- Created By -->
														<col width="80" align="center"> <!-- Created Date -->
														<col width="120" align="center"><!-- Last Updated By -->
														<col width="80" align="center"> <!-- Last Updated Date -->
														<tbody>
															<ezf:row ezfHyo="C">
															<tr height="22" id="id_rightTBL_His_row{EZF_ROW_NUMBER}">
																<td>
																	<!-- New Item Cost -->
																	<ezf:inputText name="newTmthTotStdCostAmt_C" ezfName="newTmthTotStdCostAmt_C" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="C" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Update Price List Type -->
																	<ezf:inputText name="prcListTpDescTxt_C" ezfName="prcListTpDescTxt_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Update Price List Action -->
																	<ezf:inputText name="prcListActTpDescTxt_C" ezfName="prcListActTpDescTxt_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Price List ID's -->
																	<ezf:inputText name="prcListsDescTxt_C" ezfName="prcListsDescTxt_C" value="WWWWWWWWW1WWWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"4000\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Retain Original Price -->
																	<ezf:inputCheckBox name="retanOrigPrcFlg_C" ezfName="retanOrigPrcFlg_C" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"xxSupdFlg_FW\""/>
																</td>
																<td>
																	<!-- New Price -->
																	<ezf:inputText name="newPrcAmt_C" ezfName="newPrcAmt_C" value="123456" ezfHyo="C" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Min Price -->
																	<ezf:inputText name="newMinPrcAmt_C" ezfName="newMinPrcAmt_C" value="1234" ezfHyo="C" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Max Price -->
																	<ezf:inputText name="newMaxPrcAmt_C" ezfName="newMaxPrcAmt_C" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="C" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Lease Payment -->
																	<ezf:inputText name="newMlyPmtAmt_C" ezfName="newMlyPmtAmt_C" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="C" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Min Lease Payment -->
																	<ezf:inputText name="newMinLeasePmtAmt_C" ezfName="newMinLeasePmtAmt_C" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- New Max Lease Payment -->
																	<ezf:inputText name="newMaxLeasePmtAmt_C" ezfName="newMaxLeasePmtAmt_C" value="WWWWWWWWW1WWWWWWWWX" ezfHyo="C" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Status -->
																	<ezf:inputText name="rqstStsTpDescTxt_C" ezfName="rqstStsTpDescTxt_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\""/>
																</td>
																<td align="left">
																	<!-- Process Log File -->
																	<ezf:anchor name="" ezfName="" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="DownloadHistoricalRequestResult" otherAttr=" id=\"prcListEquipRqstPk_C#{EZF_ROW_NUMBER}\""><ezf:label name="prcListEquipRqstPk_C" ezfName="prcListEquipRqstPk_C" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor>
																</td>
																<td>
																	<!-- Created By -->
																	<ezf:inputText name="xxAllPsnNm_CC" ezfName="xxAllPsnNm_CC" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7X" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"71\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Created Date -->
																	<ezf:inputText name="cratDt_C" ezfName="cratDt_C" value="mm/dd/yyyy" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Last Updated By -->
																	<ezf:inputText name="xxAllPsnNm_CU" ezfName="xxAllPsnNm_CU" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7X" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"71\" ezftoupper=\"\""/>
																</td>
																<td>
																	<!-- Last Updated Date -->
																	<ezf:inputText name="lastUpdDt_C" ezfName="lastUpdDt_C" value="mm/dd/yyyy" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																</td>
															</tr>
															</ezf:row>
														</tbody>
													</table>
												</div>
												<div id="dummy" style="width:1100; height:1; display:block; overflow:hidden; float:left;">
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</fieldset>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
