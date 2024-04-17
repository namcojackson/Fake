<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180322114438 --%>
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
			<input type="hidden" name="pageID" value="NPAL1350Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Kitting WO Search">
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
								<tr title="Kitting WO Search">
									<td align="center" class="same">Kitting WO Search</td>
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
                                    <ezf:select name="srchOptPk_SL" ezfName="srchOptPk_SL" ezfBlank="1" ezfCodeName="srchOptPk_PD" ezfDispName="srchOptNm_PD" otherEvent1=" onchange=\"sendServer('OnChangeSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
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
					
					<table border="0" cellpadding="0" cellspacing="0" style="margin-left: 5px;">
						<col width="370">
						<col width="420">
						<col width="308">
						<tr>
							<td valign="top">
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="115">
									<col width="225">
									<col width="30">
									<!-- 1 -->
									<tr height="20px">
										<td class="stab">Work Order Number (*)</td>
										<td style="text-align: left;"><ezf:inputText name="wrkOrdNum" ezfName="wrkOrdNum" value="----+---" otherAttr=" size=\"31\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td></td>
									</tr>
									<!-- 2 -->
									<tr height="20px">
										<td class="stab">Work Order Type</td>
										<td style="text-align: left;">
											<ezf:select name="dsWrkOrdTpCd_PL" ezfName="dsWrkOrdTpCd_PL" ezfBlank="1" ezfCodeName="dsWrkOrdTpCd_PD" ezfDispName="dsWrkOrdTpDescTxt_PD" otherAttr=" style=\"width: 223px\""/>
										</td>
										<td></td>
									</tr>
									<!-- 3 -->
									<tr height="20px">
										<td class="stab">Work Order Status</td>
										<td style="text-align: left;">
											<ezf:select name="dsWrkOrdStsNm_PL" ezfName="dsWrkOrdStsNm_PL" ezfBlank="1" ezfCodeName="dsWrkOrdStsNm_PC" ezfDispName="dsWrkOrdStsNm_PD" otherAttr=" style=\"width: 223px\""/>
										</td>
										<td></td>
									</tr>
									<!-- 4 -->
									<tr height="20px">
										<td class="stab">Work Order Date</td>
										<td style="text-align: left;">
											<ezf:inputText name="wrkOrdDt_FM" ezfName="wrkOrdDt_FM" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'wrkOrdDt_FM', 4 );">
											-
											<ezf:inputText name="wrkOrdDt_TO" ezfName="wrkOrdDt_TO" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'wrkOrdDt_TO', 4 );">
										</td>
										<td></td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="110">
									<col width="150">
									<col width="160">
									<!-- 1 -->
									<tr height="20px">
										<td class="stab"><ezf:anchor name="kitItmCd" ezfName="kitItmCd" ezfEmulateBtn="1" ezfGuard="OpenWin_Kit" >Kit Item (*)</ezf:anchor></td>
										<td><ezf:inputText name="prntMdseCd" ezfName="prntMdseCd" value="----+----1----+-" otherAttr=" size=\"20\" maxlength=\"16\" ezftoupper=\"\""/></td>
										<td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/></td>
									</tr>
									<!-- 2 -->
									<tr height="20px">
										<td class="stab"><ezf:anchor name="cmpWhCd" ezfName="cmpWhCd" ezfEmulateBtn="1" ezfGuard="OpenWin_WH" >Warehouse</ezf:anchor></td>
										<td><ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="----+----1----+----2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="----+----1----+----2----+----3" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<!-- 3 -->
									<tr height="20px">
										<td class="stab"><ezf:anchor name="cmpWhCd" ezfName="cmpSwhCd" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWH" >Completion Sub WH</ezf:anchor></td>
										<td><ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="----+----1----+----2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" value="----+----1----+----2----+----3" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<!-- 4 -->
									<tr height="20px">
										<td class="stab">Request Comp Date</td>
										<td style="text-align: left;" colspan="2">
											<ezf:inputText name="rqstRcvDt_FM" ezfName="rqstRcvDt_FM" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rqstRcvDt_FM', 4 );">
											-
											<ezf:inputText name="rqstRcvDt_TO" ezfName="rqstRcvDt_TO" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rqstRcvDt_TO', 4 );">
										</td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="308">
									<!-- 1 -->
									<tr height="20px">
										<td></td>
									</tr>
									<!-- 2 -->
									<tr height="20px">
										<td></td>
									</tr>
									<!-- 3 -->
									<tr height="20px">
										<td></td>
									</tr>
									<!-- 4 -->
									<tr height="20px">
										<td style="text-align: right">
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
											<ezf:inputButton name="New_WorkOrderEntry" value="New" htmlClass="pBtn6" />
										</td>
									</tr>
								</table>
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
									<col width="300px"  align="right">
									<tr>
										<%-- =============== Filters Parts =============== --%>
										<td>
											<table>
												<ezf:skip>
												<tr height="20px">
													<td>
														<select>
															<option>Default</option>
															<option>WWWWWWWWW1WWWWWWWWW2</option>
														</select>
													</td>
												</tr>
												</ezf:skip>
												<tr>
													<td>
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
											<ezf:skip>
											<div align="right">
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
											</div>
											</ezf:skip>
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

								<div id="parentBoxA"> <!-- parent box -->
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td align="left" valign="top">
											<div style="float:left; display:block"> <!-- left table -->
												<div id='leftTblHead' style="display:block;">
												</div>
												<div id='leftTbl' style="float:left; display:block; height:352px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
												</div>
											</div> <!-- left table -->
											<div style="float:left"> <!-- right table -->

											<%-- ### A:Right Table HEAD--%>
											<div id='rightTblHead' style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
												<table border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1900" style="table-layout:fixed;" style="margin-right:20px">
													<col width="25" align="middle" >
													<col width="80" align="center">
													<col width="150" align="Center">
													<col width="150" align="Center">
													<col width="80"  align="Center">
													<col width="100"  align="Center">
													<col width="120" align="Center">
													<col width="150" align="Center">
													<col width="150"  align="Center">
													<col width="150" align="Center">
													<col width="150"  align="Center">
													<col width="104" align="Center">
													<col width="104" align="Center">
													<col width="104" align="Center">
													<col width="104" align="Center">
													<col width="104" align="Center">
													<col width="150" align="Center">

													<tr height="28">
														<td class="pClothBs"></td>
														<td id="AH0" class="pClothBs">WO Number</td>
														<td id="AH1" class="pClothBs">WO Type</td>
														<td id="AH2" class="pClothBs">WO Status</td>
														<td id="AH3" class="pClothBs">WO Date</td>
														<td id="AH4" class="pClothBs">Req Comp Date</td>
														<td id="AH5" class="pClothBs">Kit Item</td>
														<td id="AH6" class="pClothBs">Item Description</td>
														<td id="AH7" class="pClothBs">WH</td>
														<td id="AH8" class="pClothBs">WH Name</td>
														<td id="AH9" class="pClothBs">Completion Sub WH</td>
														<td id="AH10" class="pClothBs">Order Qty</td>
														<td id="AH11" class="pClothBs">Receive Qty</td>
														<td id="AH12" class="pClothBs">Balance Qty</td>
														<td id="AH13" class="pClothBs">Cancelled Qty</td>
														<td id="AH14" class="pClothBs">Old WO Number</td>
														<td id="AH15" class="pClothBs">Completion Sub WH Name</td>
													</tr>
												</table>
											</div>

											<%-- ### Right Table BODY--%>
											<div  id="rightTbl" style="width:1097px; height:352px; display:block; overflow:scroll; margin:0px; padding:0px;" >
												<table border="1" cellpadding="1" cellspacing="0" id="A" width="1900" style="table-layout:fixed;">
													<col width="25" align="middle">
													<col width="80"  align="left">
													<col width="150" align="left">
													<col width="150" align="left">
													<col width="80"  align="left">
													<col width="100"  align="left">
													<col width="120" align="left">
													<col width="150" align="left">
													<col width="150"  align="left">
													<col width="150" align="left">
													<col width="150"  align="left">
													<col width="104" align="right">
													<col width="104" align="right">
													<col width="104" align="right">
													<col width="104" align="right">
													<col width="104" align="left">
													<col width="150" align="left">
													<tbody>
														<ezf:row ezfHyo="A">
														<tr height="28">
															<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Move_WO_Entry" otherAttr=" id=\"wrkOrdNum_Ancher{EZF_ROW_NUMBER}\""><ezf:label name="wrkOrdNum_A1" ezfName="wrkOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
															<td><ezf:label name="dsWrkOrdTpDescTxt_A1" ezfName="dsWrkOrdTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="dsWrkOrdStsNm_A1" ezfName="dsWrkOrdStsNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="wrkOrdDt_A1" ezfName="wrkOrdDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="rqstRcvDt_A1" ezfName="rqstRcvDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="fnshGoodsMdseCd_A1" ezfName="fnshGoodsMdseCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="fnshMdseDescShortTxt_A1" ezfName="fnshMdseDescShortTxt_A1" value="----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
															<td><ezf:label name="rtlWhCd_A1" ezfName="rtlWhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
															<td><ezf:label name="cpltRtlSwhCd_A1" ezfName="cpltRtlSwhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="fnshGoodsOrdQty_A1" ezfName="fnshGoodsOrdQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="fnshGoodsRcvQty_A1" ezfName="fnshGoodsRcvQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="fnshGoodsBalQty_A1" ezfName="fnshGoodsBalQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="fnshGoodsCancQty_A1" ezfName="fnshGoodsCancQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:label name="oldWrkOrdNum_A1" ezfName="oldWrkOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
														</tr>
														</ezf:row>
													</tbody>
												</table>
											</div>
											</div> <!-- right table -->
										</td>
									</tr>
								</table>
								</div> <!-- parent box -->
							</td>
						</tr>
					</table>
					<table width="100%">
						<col width="60">
						<col width="5">
						<col width="60">
						<col width="5">
						<col width="60">
						<col width="">
						<tr>
							<td><ezf:inputButton name="CheckAll" value="Check All" htmlClass="pBtn8" /></td>
							<td></td>
							<td><ezf:inputButton name="UncheckAll" value="Un Check All" htmlClass="pBtn8" /></td>
							<td></td>
							<td><ezf:inputButton name="Print" value="Print" htmlClass="pBtn8" /></td>
							<td></td>
						</tr>
					</table>
				</div>
<!-- ######################################## FOOTER ######################################## -->
			</td>
		</tr>
	</table>
</center>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A",-1,true,false);
</script>


<%-- **** Task specific area ends here **** --%>
