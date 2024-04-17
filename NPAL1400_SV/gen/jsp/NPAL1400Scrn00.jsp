<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161219172839 --%>
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
			<input type="hidden" name="pageID" value="NPAL1400Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Reman Order List">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
		
<%-- ######################################## HEADER ######################################## --%>
			<%-- ###TAB - HEAD --%>
			<!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
			<ezf:skip>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="background-image: url(./img/tab/uppertabbackground.jpg);">
					<tr>
						<td height="28px" width="1070px" valign="bottom">
							<table class="pTab_UPPER_ON" border="0" cellpadding="0" cellspacing="0" >
								<tr title="WO Search">
									<td align="center" class="same">Reman Ord List</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
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
                                    <ezf:select name="srchOptPk_SL" ezfName="srchOptPk_SL" ezfBlank="1" ezfCodeName="srchOptPk_PD" ezfDispName="srchOptNm_PD" otherEvent1=" onchange=\"sendServer('OnChange_SearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
                                </td>
                                <td class="stab">Search Option Name</td>
                                <td class="stab"><ezf:inputText name="srchOptNm_TX" ezfName="srchOptNm_TX" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
                                <td>
                                    <ezf:inputButton name="SaveSearchOption" value="Save Search" htmlClass="pBtn8" />
                                    <ezf:inputButton name="DeleteSearchOption" value="Delete Search" htmlClass="pBtn8" />
                                </td>
                            </tr>
                        </table>

					<hr>
					<!-- ################################################## HEADER - [START] ################################################## -->
					
					<table border="0" cellpadding="0" cellspacing="0" style="margin-left: 10px;">
						<col width="135">
						<col width="250">
						<col width="15">
						<col width="120">
						<col width="98">
						<col width="250">
						<col width="230">
						<!-- 1 -->
						<tr height="20px">
							<td class="stab"><ezf:anchor name="kitItmCd" ezfName="kitItmCd" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" >Reman Warehouse (*)</ezf:anchor></td>
							<td style="text-align: left;">
								<ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="1Z1" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/>
								<ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="MONROE PLAN" otherAttr=" size=\"21\" maxlength=\"30\" ezftoupper=\"\""/>
							</td>
							<td></td>
							<td class="stab">Main Unit Serial</td>
							<td colspan="2"><ezf:inputText name="rmnfMainUnitSerNum" ezfName="rmnfMainUnitSerNum" value="SK1000000" otherAttr=" size=\"40\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td></td>
						</tr>
						<!-- 2 -->
						<tr height="20px">
							<td class="stab"><ezf:anchor name="kitItmCd" ezfName="kitItmCd" ezfEmulateBtn="1" ezfGuard="OpenWin_Sub_Warehouse" >Reman Sub Warehouse (*)</ezf:anchor></td>
							<td style="text-align: left;">
								<ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="NEW" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/>
								<ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" value="NEW 100%" otherAttr=" size=\"21\" maxlength=\"30\" ezftoupper=\"\""/>
							</td>
							<td></td>
							<td class="stab">Model (*)</td>
							<td colspan="2"><ezf:inputText name="t_MdlNm" ezfName="t_MdlNm" value="IPC7000" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td></td>
						</tr>
						<!-- 3 -->
						<tr height="20px">
							<td class="stab">Reman Order #</td>
							<td><ezf:inputText name="rmnfOrdNum" ezfName="rmnfOrdNum" value="IPC7000" otherAttr=" size=\"31\" maxlength=\"10\" ezftoupper=\"\""/></td>
							<td></td>
							<td class="stab"><ezf:anchor name="kitItmCd" ezfName="kitItmCd" ezfEmulateBtn="1" ezfGuard="OpenWin_Item" >Main Unit Item (*)</ezf:anchor></td>
							<td><ezf:inputText name="rmnfMainUnitMdseCd" ezfName="rmnfMainUnitMdseCd" value="1000B001AA" otherAttr=" size=\"12\" maxlength=\"16\" ezftoupper=\"\""/></td>
							<td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="IPC7000 MAIN ENGINE" otherAttr=" size=\"26\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td></td>
						</tr>
						<!-- 4 -->
						<tr height="20px">
							<td class="stab">Reman Order Status</td>
							<td style="text-align: left;">
								<ezf:select name="rmnfOrdStsCd_SL" ezfName="rmnfOrdStsCd_SL" ezfBlank="1" ezfCodeName="rmnfOrdStsCd_PD" ezfDispName="rmnfOrdStsNm_PD" otherAttr=" style=\"width: 223px\""/>
							</td>
							<td></td>
							<td class="stab"><ezf:anchor name="kitItmCd" ezfName="kitItmCd" ezfEmulateBtn="1" ezfGuard="OpenWin_Tech" >Reman Tech Owner (*)</ezf:anchor></td>
							<td><ezf:inputText name="techTocCd" ezfName="techTocCd" value="Q05649" otherAttr=" size=\"12\" maxlength=\"8\" ezftoupper=\"\""/></td>
							<td><ezf:inputText name="techNm" ezfName="techNm" value="Bob Smith" otherAttr=" size=\"26\" maxlength=\"45\""/></td>
							<td></td>
						</tr>
						<tr height="20px">
							<td class="stab">Reman Start Date</td>
							<td style="text-align: left;">
								<ezf:inputText name="rmnfStartDt_FR" ezfName="rmnfStartDt_FR" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rmnfStartDt_FR', 4 );">
								-
								<ezf:inputText name="rmnfStartDt_TO" ezfName="rmnfStartDt_TO" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rmnfStartDt_TO', 4 );">
							</td>
							<td></td>
							<td class="stab">Reman End Date</td>
							<td style="text-align: left;" colspan="2">
								<ezf:inputText name="rmnfEndDt_FR" ezfName="rmnfEndDt_FR" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rmnfEndDt_FR', 4 );">
								-
								<ezf:inputText name="rmnfEndDt_TO" ezfName="rmnfEndDt_TO" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rmnfEndDt_TO', 4 );">
							</td>
							<td style="text-align: right">
								<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
								<ezf:inputButton name="OpenWin_NewRemanWorkbench" value="New" htmlClass="pBtn6" />
							</td>
						</tr>
					</table>
					<hr>
					
	<%-- ######################################## DETAIL ######################################## --%>
					<table border="0" cellpadding="1" cellspacing="0" width="100%" style="margin-left:5px;">
						<tr>
							<td>

<%-- Pagenation area start ============================================================== --%>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="186px"  align="left">
									<col width="620px"  align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
									<col width="296px"  align="right">
									<tr>
										<%-- =============== Filters Parts =============== --%>
										<td>
											<table>
												<tr height="20px">
													<td>
<!--
														<select>
															<option>Default</option>
															<option>WWWWWWWWW1WWWWWWWWW2</option>
														</select>
-->
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
													</td>
												</tr>
											</table>
										</td>
										<%-- The horizontal space between Fileters Parts and Paging Parts --%>
										<td>
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
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											</jsp:include>
										</td>
									</tr>
								</table>
<%-- Pagenation area end ================================================================ --%>
							<!-- Preferred View -->
							<div id="parentBoxA">
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign="top">
											<div style="float:left; display:block"> <!-- left table -->
												<div id='leftTblHead' style="display:block;"></div>
												<div id='leftTbl' style="float:left; display:block; height:333px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
											</div>
											<div style="float:left"> <!-- right table -->
												<div id='rightTblHead' style="width:1100; height: 28; display:block; overflow:hidden; margin:0px;padding:0px;">
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0"  id="AHEAD" width="1299px" style="margin-right:20px" >
														<col width="120" align="Center">
														<col width="120" align="Center">
														<col width="160" align="Center">
														<col width="100" align="Center">
														<col width="100" align="Center">
														<col width="150" align="Center">
														<col width="150" align="Center">
														<col width="120" align="Center">
														<col width="150" align="Center">
														<col width="150" align="Center">
														<col width="105" align="Center">
														<col width="105" align="Center">
														<col width="105" align="Center">
														<col width="105" align="Center">

														<tr height="26">
															<td id="AH0" class="pClothBs colFix">Reman Order #</td>
															<td id="AH1" class="pClothBs">Reman Tech Owner</td>
															<td id="AH2" class="pClothBs">Reman Tech Owner Name</td>
															<td id="AH3" class="pClothBs">Start Date</td>
															<td id="AH4" class="pClothBs">End Date</td>
															<td id="AH5" class="pClothBs">Main Unit Serial</td>
															<td id="AH6" class="pClothBs">Model</td>
															<td id="AH7" class="pClothBs">Item Number</td>
															<td id="AH8" class="pClothBs">Item Name</td>
															<td id="AH9" class="pClothBs">Status</td>
															<td id="AH10" class="pClothBs">Part Cost</td>
															<td id="AH11" class="pClothBs">Labor Cost</td>
															<td id="AH12" class="pClothBs">Other Cost</td>
															<td id="AH13" class="pClothBs">Total Cost</td>
														</tr>
													</table>
												</div>
												<div id="rightTbl" style="width:1117px; height:350px; display:block; overflow:scroll; margin:0px; padding:0px;" >
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="700">
														<col width="120" align="Center">
														<col width="120" align="Center">
														<col width="160" align="Center">
														<col width="100" align="Center">
														<col width="100" align="Center">
														<col width="150" align="Center">
														<col width="150" align="Center">
														<col width="120" align="Center">
														<col width="150" align="Center">
														<col width="150" align="Center">
														<col width="105" align="Center">
														<col width="105" align="Center">
														<col width="105" align="Center">
														<col width="105" align="Center">
														<tbody>
															<ezf:row ezfHyo="A">
															<tr height="25">
																<td align="left">
																<ezf:anchor name="rmnfOrdNum_A1" ezfName="rmnfOrdNum_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_RemanWorkbench" otherAttr=" id=\"rmnfOrdNum_{EZF_ROW_NUMBER}\"">
																<ezf:label name="rmnfOrdNum_A1" ezfName="rmnfOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																<td><ezf:inputText name="ownrTechTocCd_A1" ezfName="ownrTechTocCd_A1" value="Q05649" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"8\""/></td>
																<td><ezf:inputText name="techNm_A1" ezfName="techNm_A1" value="Mike Nagle" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"45\""/></td>
																<td><ezf:label name="rmnfStartDt_A1" ezfName="rmnfStartDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="rmnfEndDt_A1" ezfName="rmnfEndDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="rmnfMainUnitSerNum_A1" ezfName="rmnfMainUnitSerNum_A1" value="SK100001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
																<td><ezf:inputText name="t_MdlNm_A1" ezfName="t_MdlNm_A1" value="IPC7000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
																<td><ezf:inputText name="rmnfMainUnitMdseCd_A1" ezfName="rmnfMainUnitMdseCd_A1" value="1000B001AA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\""/></td>
																<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="IPC7000 MAIN ENGINE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
																<td><ezf:inputText name="rmnfOrdStsDescTxt_A1" ezfName="rmnfOrdStsDescTxt_A1" value="In Process" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
																<td><ezf:label name="rmnfPrtUsgCostAmt_A1" ezfName="rmnfPrtUsgCostAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="rmnfTotLborCostAmt_A1" ezfName="rmnfTotLborCostAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="rmnfOthCostAmt_A1" ezfName="rmnfOthCostAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="rmnfTotCostAmt_A1" ezfName="rmnfTotCostAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															</tr>
															</ezf:row>
														</tbody>
													</table>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
<!-- ######################################## FOOTER ######################################## -->
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", 1, true);
</script>


<%-- **** Task specific area ends here **** --%>
