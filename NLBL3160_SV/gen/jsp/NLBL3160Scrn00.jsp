<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20221107145940 --%>
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
			<input type="hidden" name="pageID" value="NLBL3160Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Distribution Coordinator Work Bench">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<!-- #################################################### HEADER ################################################### -->
				<!-- ###TAB - HEAD -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Coord Bench" class="pTab_ON"><a href="#">Coord Bench</a></li>
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
				<!-- ###TAB - BODY -->
				<div class="pTab_BODY">
                        <!-- ################################################## Search Criteria - [START] ################################################## -->
                    <%@ page import="business.servlet.NLBL3160.NLBL3160BMsg" %>
					<%  NLBL3160BMsg bMsg = (NLBL3160BMsg)databean.getEZDBMsg(); %>
                    <!-- Main Search Creteria -->
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top">
								<table cellpadding="0">
									<col align="left" width="10">
									<col align="left" width="100">
									<col align="left" width="253">
									<col align="left" width="80">
									<col align="left" width="70">
									<col align="left" width="27">
									<col align="left" width="150">
									<col align="left" width="80">
									<col align="left" width="70">
									<col align="left" width="27">
									<col align="left" width="155">
									<tr>
										<td>
											&nbsp;
										</td>
										<td class="stab">
											Sales Order(*)
										</td>
										<td>
											<ezf:inputText name="cpoNum" ezfName="cpoNum" value="12345678" otherAttr=" size=\"20\" maxlength=\"20\""/>
										</td>
										<!--<td class="stab">
											<a href="#" onclick="sendServer('OpenWin_Warehouse')" >Warehouse(*)</a>
										</td>
										<td>
											<input type="text"value="12345678"  size="15" maxlength="10" ezftoupper name="" ezfname="">
										</td>
										<td>
											<input type="button" class="pBtn0" value=">>" name="Get_Warehouse" onclick="sendServer(this)" >
										</td>
										<td>
											<input type="text" size="15" maxlength="30" value="xxxxxxx" ezftoupper name="" ezfname="" disabled>
										</td>-->
										<td class="stab">
											<ezf:anchor name="" ezfName="xxLinkAncr_CP" ezfEmulateBtn="1" ezfGuard="OpenWin_CoordSearchCode" >Coordinator</ezf:anchor>
										</td>
										<td>
											<ezf:inputText name="psnCd" ezfName="psnCd" value="12345678" otherAttr=" size=\"15\" maxlength=\"50\""/>
										</td>
										<td>
											<ezf:inputButton name="Search_CoordInfo" value=">>" htmlClass="pBtn0" />
										</td>
										<td>
											<ezf:inputText name="xxPsnFirstMidLastNm" ezfName="xxPsnFirstMidLastNm" value="123456789012345" otherAttr=" size=\"20\" maxlength=\"50\" tabindex=\"-1\""/>
										</td>
										<td class="stab">
											<ezf:inputButton name="Refresh" value="Refresh" htmlClass="pBtn5" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<ezf:inputHidden name="xxSelFlg_H" ezfName="xxSelFlg_H" value="" otherAttr=" id=\"xxSelFlg_H\" "/>
					<!-- Contents Control -->
					<table border="0" cellpadding="0" cellspacing="1">
						<tr>
							<td>
								<% if (bMsg.xxSelFlg_H.getValue() != null && "N".equals(bMsg.xxSelFlg_H.getValue())) { %>
									<div id="PlagOpen1">
								<% } else { %>
									<div id="PlagOpen1" style="display: none;">
								<% } %>
									&nbsp;
									<a href="#" onclick="showPlagin(1);return false;"><img src="./img/biz/rightarrow2.png" border="0"></a>
									<img src="./img/calendarHead.png" width="1090" height="1" value="line">
								</div>
							</td>
						</tr>
					</table>

					<table border="0" cellpadding="0" cellspacing="1">
						<tr>
							<td>
								<% if (bMsg.xxSelFlg_H.getValue() != null && "N".equals(bMsg.xxSelFlg_H.getValue())) { %>
									<div id="PlagClose1" style="display: none;">
								<% } else { %>
									<div id="PlagClose1">
								<% } %>
										&nbsp;
										<a href="#" onclick="showPlagin(1);return false;"><img src="./img/biz/downarrow2.png" border="0"></a>
										<img src="./img/calendarHead.png" width="1090" height="1" value="line">
                                        <!-- Search Header -->
                                        <table style="margin-top: 5px; margin-left: 11px;" border="0">
                                            <colgroup>
                                                <col width="119" />
                                                <col width="" />
                                                <col width="50" />
                                                <col width="105" />
                                                <col width="" />
                                                <col width="50" />
                                                <col width="50" />
                                                <col width="50" />
                                            </colgroup>
                                            <tbody>
                                                <tr>
                                                    <td class="stab">Saved Search Options</td>
                                                    <td>
                                                        <ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" id=\"srchOptPk\" style=\"width:320px\""/>
                                                    </td>
                                                    <td></td>
                                                    <td class="stab">Saved Option Name</td>
                                                    <td><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
                                                    <td></td>
                                                    <td><ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" /></td>
                                                    <td><ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" /></td>
                                                </tr>
                                            </tbody>
                                        </table>

                                        <hr>

									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="top">
												<table cellpadding="0" height="178">
													<col align="left" width="20">
													<col align="left" width="140">
													<col align="left" width="210">
													<tr>
														<td rowspan="9">&nbsp;</td>
														<td class="stab">
														<ezf:anchor name="" ezfName="xxLinkAncr_OG" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderCategory" >Order Category (*)</ezf:anchor>
														</td>
														<td class="stab">
															<ezf:inputText name="dsOrdCatgDescTxt" ezfName="dsOrdCatgDescTxt" value="LEASE, CSA" otherAttr=" size=\"20\" maxlength=\"20\""/>
															</td>
														</td>
													</tr>
													<tr>
														<td class="stab">Scheduling Status</td>
                                        <td colspan="3">
                                            <ezf:select name="schdCoordStsCd" ezfName="schdCoordStsCd" ezfBlank="1" ezfCodeName="schdCoordStsCd_P" ezfDispName="schdCoordStsDescTxt_P" otherAttr=" style=\"width:215px;\""/>
                                        </td>
													</tr>
													<tr>
														<td class="stab">Config ID</td>
														<td colspan="3">
															<ezf:inputText name="svcConfigMstrPk" ezfName="svcConfigMstrPk" value="xx" otherAttr=" size=\"20\" maxlength=\"20\""/>
														</td>
													</tr>
													<tr>
														<td class="stab">Service Level</td>
																					<td align="left">
								<ezf:select name="shpgSvcLvlCd" ezfName="shpgSvcLvlCd" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_P" ezfDispName="shpgSvcLvlDescTxt_P" otherAttr=" style=\"width:215px;\""/>
							</td>
                        </tr>
                        <tr>
                            <td class="stab">LOB</td>
                            <td colspan="3">
                                <ezf:select name="lineBizTpCd" ezfName="lineBizTpCd" ezfBlank="1" ezfCodeName="lineBizTpCd_P" ezfDispName="lineBizTpDescTxt_P" otherAttr=" style=\"width:100px;\""/>
                            </td>
													</tr>
													<tr>
														<td rowspan="3" colspan="3" valign="top">
															<div id="Select_Orders" style="width:300; height:125;">
															<table border="1" cellpadding="1" cellspacing="0">
																<col align="center" width="30">		<!-- CheckBox -->
																<col align="left" width="200">		<!-- Data Status -->
																<col align="right"  width="30">		<!-- Count -->
																<tr>
																	<td><ezf:inputCheckBox name="xxChkBox_S" ezfName="xxChkBox_S" value="Y" /></td>
																	<td class="stab">Orders to Schedule</td>
																	<td class="stab"><ezf:label name="xxTotCnt_S" ezfName="xxTotCnt_S" /></td>
																</tr>
																<tr>
																	<td><ezf:inputCheckBox name="xxChkBox_DC" ezfName="xxChkBox_DC" value="Y" />
																	<td class="stab">Orders Scheduled</td>
																	<td class="stab"><ezf:label name="xxTotCnt_DC" ezfName="xxTotCnt_DC" /></td>
																</tr>
																<tr>
																	<td><ezf:inputCheckBox name="xxChkBox_NA" ezfName="xxChkBox_NA" value="Y" />
																	<td class="stab">Orders not Avaliable to Schedule</td>
																	<td class="stab"><ezf:label name="xxTotCnt_NA" ezfName="xxTotCnt_NA" /></td>
																</tr>
																<tr>
																	<td><ezf:inputCheckBox name="xxChkBox_F" ezfName="xxChkBox_F" value="Y" />
																	<td class="stab">Future Orders</td>
																	<td class="stab"><ezf:label name="xxTotCnt_F" ezfName="xxTotCnt_F" /></td>
																</tr>
																<tr>
																	<td><ezf:inputCheckBox name="xxChkBox_RS" ezfName="xxChkBox_RS" value="Y" />
																	<td class="stab">RMA Scheduled Orders</td>
																	<td class="stab"><ezf:label name="xxTotCnt_RS" ezfName="xxTotCnt_RS" /></td>
																</tr>
															</table>
															</div>
														</td>
													</tr>
												</table>
											</td>
											<td valign="top">
												<table cellpadding="0" >
													<col align="left" width="150">
													<col align="left" width="120">
													<col align="left" width="40">
													<col align="left" width="40">
													<col align="left" width="150">
													<col align="left" width="150">
													<tr height="20">
														<td class="stab" colspan="1">
															<ezf:anchor name="" ezfName="xxLinkAncr_MD" ezfEmulateBtn="1" ezfGuard="OpenWin_MdseInfo" >Item Number</ezf:anchor>
														</td>
														<td class="stab" colspan="2">
															<ezf:inputText name="mdseCd" ezfName="mdseCd" value="xx" otherAttr=" size=\"15\" maxlength=\"16\""/>
														</td>
														<td colspan="1">
															<ezf:inputButton name="Get_MdseInfo" value=">>" htmlClass="pBtn0" />
														</td>
														<td colspan="2">
															<ezf:inputText name="mdseNm" ezfName="mdseNm" value="xx" otherAttr=" size=\"15\" maxlength=\"16\""/>
														</td>
													</tr>
													<tr height="20">
														<td class="stab" colspan="1">
															<ezf:anchor name="" ezfName="xxLinkAncr_AN" ezfEmulateBtn="1" ezfGuard="OpenWin_Customer" >Customer</ezf:anchor>
														</td>
														<td  class="stab" colspan="2">
															<ezf:inputText name="dsAcctNum" ezfName="dsAcctNum" value="xx" otherAttr=" size=\"15\" maxlength=\"16\""/>
														</td>
														<td colspan="1">
															<ezf:inputButton name="Get_Customer" value=">>" htmlClass="pBtn0" />
														</td>
														<td colspan="2">
															<ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="xx" otherAttr=" size=\"15\" maxlength=\"16\""/>
														</td>
													</tr>
													<tr height="20">
														<td class="stab" colspan="1">
															<ezf:anchor name="" ezfName="xxLinkAncr_ST" ezfEmulateBtn="1" ezfGuard="OpenWin_State" >Deliver to ST</ezf:anchor>
														</td>
														<td  class="stab" colspan="2">
															<ezf:inputText name="stCd" ezfName="stCd" value="xx" otherAttr=" size=\"15\" maxlength=\"16\""/>
														</td>
														<td colspan="1">
															<ezf:inputButton name="Get_State" value=">>" htmlClass="pBtn0" />
														</td>
														<td class="stab" colspan="2">
															<ezf:inputText name="stNm" ezfName="stNm" value="xx" otherAttr=" size=\"15\" maxlength=\"16\""/>
														</td>
													</tr>
													<tr height="20">
														<td class="stab" colspan="1">
															<ezf:anchor name="" ezfName="xxLinkAncr_BR" ezfEmulateBtn="1" ezfGuard="OpenWin_Branch" >Deliver to BR</ezf:anchor>
														</td>
														<td  class="stab" colspan="2">
															<ezf:inputText name="svcBrCd" ezfName="svcBrCd" value="xx" otherAttr=" size=\"15\" maxlength=\"16\""/>
														</td>
														<td colspan="1">
															<ezf:inputButton name="Get_Branch" value=">>" htmlClass="pBtn0" />
														</td>
														<td colspan="2">
															<ezf:inputText name="svcBrCdDescTxt" ezfName="svcBrCdDescTxt" value="xx" otherAttr=" size=\"15\" maxlength=\"16\""/>
														</td>
													</tr>
													<tr height="20">
														<td class="stab">Include Closed SOs</td>
														<td>
															<ezf:inputCheckBox name="xxChkBox_I" ezfName="xxChkBox_I" value="Y" otherAttr=" tabindex=\"4\""/>
														</td>
													</tr>
												</table>
											</td>

											<td valign="top">
												<table cellpadding="0">
													<col align="left" width="100">
													<col align="left" width="150">
													<col align="left" width="40">
													<col align="left" width="30">
													<col align="left" width="100">
													<col align="left" width="100">
													<tr>
														<td class="stab" colspan="1">
															<ezf:anchor name="" ezfName="xxLinkAncr_WR" ezfEmulateBtn="1" ezfGuard="OpenWin_RtlWHInfo" >Warehouse(*)</ezf:anchor>
														</td>
														<td colspan="2">
															<ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="XX" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/>
														</td>
														<td colspan="1">
															<ezf:inputButton name="Get_RtlWHInfo" value=">>" htmlClass="pBtn0" />
														</td>
														<td colspan="2">
															<ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="XX" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\""/>
														</td>
													</tr>
													<tr>
														<td class="stab"  colspan="1"><ezf:anchor name="" ezfName="xxLinkAncr_WS" ezfEmulateBtn="1" ezfGuard="OpenWin_RtlSWHInfo" >Sub Warehouse(*)</ezf:anchor></td>
														<td colspan="2"><ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="XX" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
														<td colspan="1"><ezf:inputButton name="Get_RtlSWHInfo" value=">>" htmlClass="pBtn0" /></td>
														<td colspan="2"><ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" value="XX" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\""/><td>
													</tr>
													<tr height="20">
														<td class="stab" colspan="1">Req. Del.Date</td>
														<td class="stab"  class="stab" colspan="2"><ezf:inputText name="rddDt_FR" ezfName="rddDt_FR" value="01/01/2015" otherAttr=" size=\"12\" maxlength=\"90\""/>
															<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rddDt_FR', 4);"/><td colspan="1">&nbsp - </td>
														<td colspan="2"><ezf:inputText name="rddDt_TO" ezfName="rddDt_TO" value="01/01/2015" otherAttr=" size=\"12\" maxlength=\"90\""/>
															<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rddDt_TO', 4);"/></td>
													</tr>
													<tr height="20">
													<td class="stab" colspan="1">Next Action Date</td>
													<td class="stab" colspan="2"><ezf:inputText name="nextActDt_FR" ezfName="nextActDt_FR" value="01/01/2015" otherAttr=" size=\"12\" maxlength=\"90\""/>
														<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('nextActDt_FR', 4);"/><td colspan="1">&nbsp - </td>
													<td colspan="2"><ezf:inputText name="nextActDt_TO" ezfName="nextActDt_TO" value="01/01/2015" otherAttr=" size=\"12\" maxlength=\"90\""/>
														<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('nextActDt_TO', 4);"/></td>
												  </tr>
												</table>
											</td>
										</tr>
									</table>
									<hr>
								</div>
							</td>
						</tr>
					</table>
					<!-- ######################################## TO (COMMON)PAGENATION ###################################### -->
					<table cellpadding="0" cellspacing="0">
						<col width="003">
						<col width="200">
						<col width="345">
						<col width="555">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td align="right">
								<ezf:skip>
								<table border="0" cellpadding="0" width="100%">
									<tr>
										<td align="left">
											<table border="0" cellpadding="0" align="left" cellspacing="0">
												<col>
													<tr>
													<td>Results 1 - 200 of 1200</td>
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
													<td><input type="text" name="xxPageShowCurNum" value="1" size="4" maxlength="4" style="text-align:right"></td>
													<td class="stab">/</td>
													<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="6" class="pPro" style="text-align:right" readOnly></td>
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
												<jsp:param name="ShowingFrom"       value="xxPageShowFromNum_A" />
												<jsp:param name="ShowingTo"         value="xxPageShowToNum_A" />
												<jsp:param name="ShowingOf"         value="xxPageShowOfNum_A" />
												<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum_A" />
												<jsp:param name="ShowingTotal"      value="xxPageShowTotNum_A" />
												<jsp:param name="ViewMode"          value="FULL" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<!-- ######################################## DETAIL ######################################## -->
					<div>
						<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:1106;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('btmTBL'));">
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
											<col align="center" width="75">		<!-- 	Sales Order	 -->
											<col align="center" width="75">     <!-- 	Config ID	 -->
											<col align="center" width="75">		<!-- 	Shipping Order #/RWS#	 -->
											<col align="center" width="120">    <!-- 	Model	 -->
											<col align="center" width="25">     <!-- 	B/O C	 -->
											<col align="center" width="25">	    <!-- 	B/O E	 -->
											<col align="center" width="50">	    <!-- 	Sub Warehouse	 -->
											<col align="center" width="90">	    <!-- 	Booked Date	 -->
											<col align="center" width="30">	    <!-- 	Age	 -->
											<col align="center" width="90">		<!-- 	Req. Del. Date	 -->
											<col align="center" width="90">		<!-- 	Next Action Date	 -->
											<col align="center" width="120">	<!-- 	Scheduling Status	 -->
											<col align="center" width="90">	    <!-- 	Delivery Scheduled Date	 -->
											<col align="center" width="120">	<!-- 	Customer Name	 -->
											<col align="center" width="50">	    <!-- 	City	 -->
											<col align="center" width="25">	    <!-- 	St	 -->
											<col align="center" width="80">	    <!-- 	WMS Status	 -->
											<col align="center" width="80">	    <!-- 	Tot Mach on Sales Order	 -->
											<col align="center" width="80">	    <!-- 	Tot Returns on Sales Order	 -->
											<col align="center" width="140">	<!-- 	De-Install Date	 -->
											<col align="center" width="140">	<!-- 	Earliest Install Date	 -->
											<col align="center" width="120">	<!-- 	Branch	 -->
											<col align="center" width="75">	    <!-- 	FSR Number	 -->
											<col align="center" width="80">	    <!-- 	Carrier	 -->
											<col align="center" width="120">	<!-- 	Warehouse	 -->
											<col align="center" width="100">	<!-- 	Order Total	 -->
											<tr height="40">
												<td id="AH1"   class="pClothBs">Sales Order</td>
												<td id="AH2"   class="pClothBs">Config ID</td> 
												<td id="AH3"   class="pClothBs">Shipping Order#/RWS#</td>
												<td id="AH4"   class="pClothBs">Model</td>
												<td id="AH5"   class="pClothBs">B/O<br> C</td>
												<td id="AH6"   class="pClothBs">B/O<br> E</td>
												<td id="AH7"  class="pClothBs">Sub-WH</td>
												<td id="AH8"   class="pClothBs">Booked Date</td>
												<td id="AH9"  class="pClothBs">Age</td>
												<td id="AH10"   class="pClothBs">Req. Del.<br> Date</td>
												<td id="AH11"  class="pClothBs">Next Action<br> Date</td>
												<td id="AH12"  class="pClothBs">Scheduling<br> Status</td>
												<td id="AH13"  class="pClothBs">Delivery Scheduled Date</td>
												<td id="AH14"  class="pClothBs">Customer Name</td>
												<td id="AH15"  class="pClothBs">City</td>
												<td id="AH16"  class="pClothBs">St</td>
												<td id="AH17"  class="pClothBs">WMS Status</td>
												<td id="AH18"  class="pClothBs">Tot Mach on<br> Sales Order</td>
												<td id="AH19"  class="pClothBs">Tot Ret. on<br> Sales Order</td>
												<td id="AH20"  class="pClothBs">De-Install Date</td>
												<td id="AH21"  class="pClothBs">Earliest Install Date</td>
												<td id="AH22"  class="pClothBs">Branch</td>
												<td id="AH23"  class="pClothBs">FSR Number</td>
												<td id="AH24"  class="pClothBs">Carrier</td>
												<td id="AH25"  class="pClothBs">Warehouse</td>
												<td id="AH26"  class="pClothBs">Order<br>  Total</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td style="vertical-align:top;">
									<% if (bMsg.xxSelFlg_H.getValue() != null && "N".equals(bMsg.xxSelFlg_H.getValue())) { %>
									<div id="btmTBL" style="overflow-y:scroll; height:453; overflow-x:scroll; width:1125;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
									<% } else{ %>
									<div id="btmTBL" style="overflow-y:scroll; height:150; overflow-x:scroll; width:1125;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
									<% } %>
										<table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" id="A">
											<col align="center" width="75">	<!-- 	Sales Order	 -->
											<col align="center" width="75">	<!-- 	Config ID	 -->
											<col align="center" width="75">	<!-- 	Shipping Order #/RWS#	 -->
											<col align="left" width="120">	<!-- 	Model	 -->
											<col align="right" width="25">	<!-- 	B/O C	 -->
											<col align="right" width="25">	<!-- 	B/O E	 -->
											<col align="left" width="50">	<!-- 	Sub Warehouse	 -->
											<col align="center" width="90">	<!-- 	Booked Date	 -->
											<col align="center" width="30">	<!-- 	Age	 -->
											<col align="center" width="90">	<!-- 	Req. Del. Date	 -->
											<col align="center" width="90">	<!-- 	Next Action Date	 -->
											<col align="left" width="120">	<!-- 	Scheduling Status	 -->
											<col align="center" width="90">	<!-- 	Delivery Scheduled Date	 -->
											<col align="left" width="120">	<!-- 	Customer Name	 -->
											<col align="left" width="50">	<!-- 	City	 -->
											<col align="center" width="25">	<!-- 	St	 -->
											<col align="left" width="80">	<!-- 	WMS Status	 -->
											<col align="right" width="80">	<!-- 	Tot Mach on Sales Order	 -->
											<col align="right" width="80">	<!-- 	Tot Returns on Sales Order	 -->
											<col align="center" width="140"><!-- 	De-Install Date	 -->
											<col align="center" width="140"><!-- 	Earliest Install Date	 -->
											<col align="left" width="120">	<!-- 	Branch	 -->
											<col align="center" width="75">	<!-- 	FSR Number	 -->
											<col align="left" width="80">	<!-- 	Carrier	 -->
											<col align="left" width="120">	<!-- 	Warehouse	 -->
											<col align="right" width="100">	<!-- 	Order Total	 -->
											<% int indexA = 0; %>
											<ezf:row ezfHyo="A">
												<tr height="23" id="id_row{EZF_ROW_NUMBER}">
													<!-- 	Sales Order	 -->
													<td>
														<% if (bMsg.A.no(indexA).cpoOrdNum_A1.getValue() != null && !"".equals(bMsg.A.no(indexA).cpoOrdNum_A1.getValue())) { %>
															<% if (bMsg.A.no(indexA).xxSelFlg_A1.getValue() != null && "Y".equals(bMsg.A.no(indexA).xxSelFlg_A1.getValue())) { %>
																<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngDely" ><ezf:label name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
															<% } else { %>
																<ezf:label name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" />
															<% } %>
														<% } else { %>
															<ezf:label name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" />
														<% } %>
													</td>
													<!-- 	Config ID	 -->
													<td>
														<% if (bMsg.A.no(indexA).svcConfigMstrPk_A1.getValue() != null && !"".equals(bMsg.A.no(indexA).svcConfigMstrPk_A1.getValue())) { %>
															<% if ("O".equals(bMsg.A.no(indexA).configCatgCd_A1.getValue())) { %>
																<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngDely" ><ezf:label name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
															<% } else if ("I".equals(bMsg.A.no(indexA).configCatgCd_A1.getValue())) { %>
																<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngRMA" ><ezf:label name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
															<% } %>
														<% } else { %>
															<ezf:label name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" ezfHyo="A" ezfArrayNo="0" />
														<% } %>
													</td>
													<!-- 	Shipping Order #/RWS#	 -->
													<td>
														<% if (bMsg.A.no(indexA).soNum_A1.getValue() != null && !"".equals(bMsg.A.no(indexA).soNum_A1.getValue())) { %>
															<% if ("O".equals(bMsg.A.no(indexA).configCatgCd_A1.getValue())) { %>
																<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngDely" ><ezf:label name="soNum_A1" ezfName="soNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
															<% } else if ("I".equals(bMsg.A.no(indexA).configCatgCd_A1.getValue())) { %>
																<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngRMA" ><ezf:label name="soNum_A1" ezfName="soNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
															<% } %>
														<% } else { %>
															<ezf:label name="soNum_A1" ezfName="soNum_A1" ezfHyo="A" ezfArrayNo="0" />
														<% } %>
													</td>
													<!-- 	Model	 -->
													<td><ezf:inputText name="mdlDescTxt_A1" ezfName="mdlDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"width:120;\""/></td>
													<!-- 	B/O C	 -->
													<td>
														<% if (bMsg.A.no(indexA).xxTotCnt_BC.getValue() != null && !"".equals(bMsg.A.no(indexA).xxTotCnt_BC.getValue())) { %>
															<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngBO" ><ezf:label name="xxTotCnt_BC" ezfName="xxTotCnt_BC" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
														<% } else { %>
															<ezf:label name="xxTotCnt_BC" ezfName="xxTotCnt_BC" ezfHyo="A" ezfArrayNo="0" />
														<% } %>
													</td>
													<!-- 	B/O E	 -->
													<td>
														<% if (bMsg.A.no(indexA).xxTotCnt_BC.getValue() != null && !"".equals(bMsg.A.no(indexA).xxTotCnt_BC.getValue())) { %>
															<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MngBO" ><ezf:label name="xxTotCnt_BE" ezfName="xxTotCnt_BE" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
														<% } else { %>
															<ezf:label name="xxTotCnt_BE" ezfName="xxTotCnt_BE" ezfHyo="A" ezfArrayNo="0" />
														<% } %>
													</td>
													<!-- 	Sub Warehouse	 -->
													<td><ezf:label name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- 	Booked Date	 -->
													<td><ezf:label name="xxDt10Dt_A1" ezfName="xxDt10Dt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- 	Age	 -->
													<td><ezf:label name="xxOrdTs_A1" ezfName="xxOrdTs_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- 	Req. Del. Date	 -->
													<td><ezf:label name="rddDt_A1" ezfName="rddDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- 	Next Action Date	 -->
													<td><ezf:label name="nextActDt_A1" ezfName="nextActDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- 	Scheduling Status	 -->
													<td><ezf:inputText name="schdCoordStsDescTxt_A1" ezfName="schdCoordStsDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"width:120;\""/></td>
													<!-- 	Delivery Scheduled Date	 -->
													<td><ezf:label name="schdDelyDt_A1" ezfName="schdDelyDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- 	Customer Name	 -->
													<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_Customer" ><ezf:label name="dsAcctNm_A1" ezfName="dsAcctNm_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
													<!-- 	City	 -->
													<td><ezf:inputText name="shipToCtyAddr_A1" ezfName="shipToCtyAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"width:50;\""/></td>
													<!-- 	St	 -->
													<td><ezf:label name="shipToStCd_A1" ezfName="shipToStCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- 	WMS Status	 -->
													<td><ezf:inputText name="dsSoLineStsDescTxt_A1" ezfName="dsSoLineStsDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"width:80;\""/></td>
													<!-- 	Tot Mach on Sales Order	 -->
													<td><ezf:label name="xxTotCnt_TM" ezfName="xxTotCnt_TM" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- 	Tot Returns on Sales Order	 -->
													<td><ezf:label name="xxTotCnt_TR" ezfName="xxTotCnt_TR" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- 	De-Install Date	 -->
													<td><ezf:label name="schdDelyTsDplyTxt_DI" ezfName="schdDelyTsDplyTxt_DI" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- 	Earliest Install Data	 -->
													<td><ezf:label name="schdDelyTsDplyTxt_EI" ezfName="schdDelyTsDplyTxt_EI" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- 	Branch	 -->
													<td><ezf:inputText name="svcBrCdDescTxt_A1" ezfName="svcBrCdDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"width:120;\""/></td>
													<!-- 	Service Request #	 -->
													<td><ezf:label name="fsrNum_A1" ezfName="fsrNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- 	Carrier	 -->
													<td><ezf:inputText name="locNm_A1" ezfName="locNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"width:80;\""/></td>
													<!-- 	Warehouse	 -->
													<td><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"width:120;\""/></td>
													<!-- 	Order Total	 -->
													<td><ezf:label name="cpoTotDealNetAmt_A1" ezfName="cpoTotDealNetAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
												</tr>
												<% indexA++; %>
											</ezf:row>
											<ezf:skip>
												<tr height="23">
													<!-- 	Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Config ID	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A"><label ezfout name="" ezfname="" ezfhyo="A">12345678</label></a></td>
													<!-- 	Shipping Order #/RWS#	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A"><label ezfout name="" ezfname="" ezfhyo="A">SHM123XX</label></a></td>
													<!-- 	Model	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >DR5080</label></td>
													<!-- 	B/O C	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	B/O E	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Sub Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >NEW</label></td>
													<!-- 	Booked Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Age	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Req. Del. Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/24/2017</label></td>
															<!-- 	Next Action Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Scheduling Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Awaiting_schdule</label></td>
													<!-- 	Delivery Scheduled Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Customer Name	 -->
													<td><a href="#" onclick="sendServer('Open_Customer')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >Fedex</label></a></td>
													<!-- 	City	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Newark</label></td>
													<!-- 	St	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >DE</label></td>
													<!-- 	WMS Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Unbox</label></td>
													<!-- 	Tot Mach on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Tot Returns on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	De-Install Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Earliest Install Data	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Branch	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Service Request #	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12345678</label></td>
													
														<!-- 	Carrier	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
														<!-- 	Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >ITASCA WH - CSA</label></td>
													<!-- 	Order Total	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12,345,678.00</label></td>
												</tr>
												<tr height="23">
													<!-- 	Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Config ID	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >12345678</label></a></td>
													<!-- 	Shipping Order #/RWS#	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >SHM123XX</label></a></td>
													<!-- 	Model	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >DR5080</label></td>
													<!-- 	B/O C	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	B/O E	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Sub Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >NEW</label></td>
													<!-- 	Booked Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Age	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Req. Del. Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/24/2017</label></td>
														<!-- 	Next Action Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Scheduling Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Awaiting_schdule</label></td>
													<!-- 	Delivery Scheduled Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Customer Name	 -->
													<td><a href="#" onclick="sendServer('Open_Customer')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >Fedex</label></a></td>
													<!-- 	City	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Newark</label></td>
													<!-- 	St	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >DE</label></td>
													<!-- 	WMS Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Unbox</label></td>
													<!-- 	Tot Mach on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Tot Returns on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	De-Install Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Earliest Install Data	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Branch	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Service Request #	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													
														<!-- 	Carrier	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
														<!-- 	Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >ITASCA WH - CSA</label></td>
													<!-- 	Order Total	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12,345,678.00</label></td>
												</tr>
												<tr height="23">
													<!-- 	Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Config ID	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >12345678</label></a></td>
													<!-- 	Shipping Order #/RWS#	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >SHM123XX</label></a></td>
													<!-- 	Model	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >DR5080</label></td>
													<!-- 	B/O C	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	B/O E	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Sub Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >NEW</label></td>
													<!-- 	Booked Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Age	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Req. Del. Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/24/2017</label></td>
														<!-- 	Next Action Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Scheduling Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Awaiting_schdule</label></td>
													<!-- 	Delivery Scheduled Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Customer Name	 -->
													<td><a href="#" onclick="sendServer('Open_Customer')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >Fedex</label></a></td>
													<!-- 	City	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Newark</label></td>
													<!-- 	St	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >DE</label></td>
													<!-- 	WMS Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Unbox</label></td>
													<!-- 	Tot Mach on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Tot Returns on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	De-Install Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Earliest Install Data	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Branch	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Service Request #	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Carrier	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >ITASCA WH - CSA</label></td>
													<!-- 	Order Total	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12,345,678.00</label></td>
												</tr>												<tr height="23">
													<!-- 	Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Config ID	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >12345678</label></a></td>
													<!-- 	Shipping Order #/RWS#	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >SHM123XX</label></a></td>
													<!-- 	Model	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >DR5080</label></td>
													<!-- 	B/O C	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	B/O E	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Sub Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >NEW</label></td>
													<!-- 	Booked Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Age	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Req. Del. Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/24/2017</label></td>
														<!-- 	Next Action Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Scheduling Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Awaiting_schdule</label></td>
													<!-- 	Delivery Scheduled Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Customer Name	 -->
													<td><a href="#" onclick="sendServer('Open_Customer')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >Fedex</label></a></td>
													<!-- 	City	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Newark</label></td>
													<!-- 	St	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >DE</label></td>
													<!-- 	WMS Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Unbox</label></td>
													<!-- 	Tot Mach on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Tot Returns on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	De-Install Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Earliest Install Data	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Branch	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Service Request #	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													
														<!-- 	Carrier	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
														<!-- 	Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >ITASCA WH - CSA</label></td>
													<!-- 	Order Total	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12,345,678.00</label></td>
												</tr>
												<tr height="23">
													<!-- 	Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Config ID	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >12345678</label></a></td>
													<!-- 	Shipping Order #/RWS#	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >RWS123XX</label></a></td>
													<!-- 	Model	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >DR5080</label></td>
													<!-- 	B/O C	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	B/O E	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Sub Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >NEW</label></td>
													<!-- 	Booked Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Age	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Req. Del. Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/24/2017</label></td>
														<!-- 	Next Action Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Scheduling Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Awaiting_schdule</label></td>
													<!-- 	Delivery Scheduled Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Customer Name	 -->
													<td><a href="#" onclick="sendServer('Open_Customer')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >Fedex</label></a></td>
													<!-- 	City	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Newark</label></td>
													<!-- 	St	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >DE</label></td>
													<!-- 	WMS Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Tot Mach on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Tot Returns on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	De-Install Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Earliest Install Data	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Branch	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Service Request #	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													
														<!-- 	Carrier	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
														<!-- 	Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >ITASCA WH - CSA</label></td>
													<!-- 	Order Total	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12,345,678.00</label></td>
												</tr>
																								<tr height="23">
													<!-- 	Sales Order	 -->
													<td><a href="#" onclick="sendServer('Open_MngDelyEntry')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >23234569</label></a></td>
													<!-- 	Config ID	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >&nbsp;</label></td>
													<!-- 	Shipping Order #/RWS#	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >&nbsp;</label></td>
													<!-- 	Model	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >&nbsp;</label></td>
													<!-- 	B/O C	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >0</label></td>
													<!-- 	B/O E	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >0</label></td>
													<!-- 	Sub Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Booked Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Age	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >2</label></td>
													<!-- 	Req. Del. Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/24/2017</label></td>
														<!-- 	Next Action Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Scheduling Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Delivery Scheduled Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Customer Name	 -->
													<td><a href="#" onclick="sendServer('Open_Customer')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >Fedex</label></a></td>
													<!-- 	City	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Newark</label></td>
													<!-- 	St	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >DE</label></td>
													<!-- 	WMS Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Tot Mach on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >1</label></td>
													<!-- 	Tot Returns on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >0</label></td>
													<!-- 	De-Install Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Earliest Install Data	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Branch	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Service Request #	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													
														<!-- 	Carrier	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
														<!-- 	Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Order Total	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12,345,678.00</label></td>
												</tr>
												<tr height="23">
													<!-- 	Sales Order	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >&nbsp;</label></td>
													<!-- 	Config ID	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >12341910</label></a></td>
													<!-- 	Shipping Order #/RWS#	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >SHM123XX</label></a></td>
													<!-- 	Model	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >DR5080</label></td>
													<!-- 	B/O C	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	B/O E	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Sub Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >NEW</label></td>
													<!-- 	Booked Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Age	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Req. Del. Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/24/2017</label></td>
														<!-- 	Next Action Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Scheduling Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Scheduled</label></td>
													<!-- 	Delivery Scheduled Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/24/2017</label></td>
													<!-- 	Customer Name	 -->
													<td><a href="#" onclick="sendServer('Open_Customer')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >Fedex</label></a></td>
													<!-- 	City	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Newark</label></td>
													<!-- 	St	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >DE</label></td>
													<!-- 	WMS Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Deliver to Shop</label></td>
													<!-- 	Tot Mach on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Tot Returns on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	De-Install Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Earliest Install Data	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Branch	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Itasca - Bruning</label></td>
													<!-- 	Service Request #	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12345678</label></td>
													
														<!-- 	Carrier	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >APEX</label></td>
														<!-- 	Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >ITASCA WH - CSA</label></td>
													<!-- 	Order Total	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12,345,678.00</label></td>
												</tr>
												<tr height="23">
													<!-- 	Sales Order	 -->
													<td><a href="#" onclick="sendServer('Open_MngDelyEntry')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >23234550</label></a></td>
													<!-- 	Config ID	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >&nbsp;</label></td>
													<!-- 	Shipping Order #/RWS#	 -->
													<td>&nbsp;</a></td>
													<!-- 	Model	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >&nbsp;</label></td>
													<!-- 	B/O C	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >1</label></td>
													<!-- 	B/O E	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >1</label></td>
													<!-- 	Sub Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >NEW</label></td>
													<!-- 	Booked Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Age	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >10</label></td>
													<!-- 	Req. Del. Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/24/2017</label></td>
														<!-- 	Next Action Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Scheduling Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Delivery Scheduled Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Customer Name	 -->
													<td><a href="#" onclick="sendServer('Open_Customer')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >Fedex</label></a></td>
													<!-- 	City	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Newark</label></td>
													<!-- 	St	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >DE</label></td>
													<!-- 	WMS Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Tot Mach on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >1</label></td>
													<!-- 	Tot Returns on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >0</label></td>
													<!-- 	De-Install Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Earliest Install Data	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Branch	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Itasca - Bruning</label></td>
													<!-- 	Service Request #	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													
														<!-- 	Carrier	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
														<!-- 	Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >ITASCA WH - CSA</label></td>
													<!-- 	Order Total	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12,345,678.00</label></td>
												</tr>
												<tr height="23">
													<!-- 	Sales Order	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >&nbsp;</label></td>
													<!-- 	Config ID	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >12341910</label></a></td>
													<!-- 	Shipping Order #/RWS#	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >SH0123XX</label></a></td>
													<!-- 	Model	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >DR5080</label></td>
													<!-- 	B/O C	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	B/O E	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Sub Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >NEW</label></td>
													<!-- 	Booked Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Age	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Req. Del. Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/24/2017</label></td>
														<!-- 	Next Action Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Scheduling Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Awaiting_Backorder_Fulfillment</label></td>
													<!-- 	Delivery Scheduled Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/24/2017</label></td>
													<!-- 	Customer Name	 -->
													<td><a href="#" onclick="sendServer('Open_Customer')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >Fedex</label></a></td>
													<!-- 	City	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Newark</label></td>
													<!-- 	St	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >DE</label></td>
													<!-- 	WMS Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Deliver to Shop</label></td>
													<!-- 	Tot Mach on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Tot Returns on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	De-Install Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Earliest Install Data	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Branch	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Itasca - Bruning</label></td>
													<!-- 	Service Request #	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12345678</label></td>
													
														<!-- 	Carrier	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >APEX</label></td>
														<!-- 	Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >ITASCA WH - CSA</label></td>
													<!-- 	Order Total	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12,345,678.00</label></td>
												</tr>
												<tr height="23">
													<!-- 	Sales Order	 -->
													<td><a href="#" onclick="sendServer('Open_MngDelyEntry')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >23234550</label></a></td>
													<!-- 	Config ID	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >&nbsp;</label></td>
													<!-- 	Shipping Order #/RWS#	 -->
													<td>&nbsp;</a></td>
													<!-- 	Model	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >&nbsp;</label></td>
													<!-- 	B/O C	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >0</label></td>
													<!-- 	B/O E	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >0</label></td>
													<!-- 	Sub Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >NEW</label></td>
													<!-- 	Booked Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Age	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >10</label></td>
													<!-- 	Req. Del. Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/24/2017</label></td>
														<!-- 	Next Action Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Scheduling Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Delivery Scheduled Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Customer Name	 -->
													<td><a href="#" onclick="sendServer('Open_Customer')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >Fedex</label></a></td>
													<!-- 	City	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Newark</label></td>
													<!-- 	St	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >DE</label></td>
													<!-- 	WMS Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Tot Mach on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >0</label></td>
													<!-- 	Tot Returns on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >1</label></td>
													<!-- 	De-Install Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Earliest Install Data	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Branch	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Itasca - Bruning</label></td>
													<!-- 	Service Request #	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													
														<!-- 	Carrier	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
														<!-- 	Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >ITASCA WH - CSA</label></td>
													<!-- 	Order Total	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12,345,678.00</label></td>
												</tr>
												<tr height="23">
													<!-- 	Sales Order	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >&nbsp;</label></td>
													<!-- 	Config ID	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >&nbsp;</label></td>
													<!-- 	Shipping Order #/RWS#	 -->
													<td><a href="#" onclick="sendServer('Open_MngDely')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >RWS123XX</label></a></td>
													<!-- 	Model	 -->
													<td><label ezfout name="" ezfname="" ezfhyo="A" >DR5080</label></td>
													<!-- 	B/O C	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	B/O E	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Sub Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >NEW</label></td>
													<!-- 	Booked Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Age	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Req. Del. Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/24/2017</label></td>
														<!-- 	Next Action Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Scheduling Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Scheduled</label></td>
													<!-- 	Delivery Scheduled Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >03/14/2017</label></td>
													<!-- 	Customer Name	 -->
													<td><a href="#" onclick="sendServer('Open_Customer')" ezfname="" ezfhyo="A" ><label ezfout name="" ezfname="" ezfhyo="A" >Fedex</label></a></td>
													<!-- 	City	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Newark</label></td>
													<!-- 	St	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >DE</label></td>
													<!-- 	WMS Status	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Tot Mach on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >1</label></td>
													<!-- 	Tot Returns on Sales Order	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >0</label></td>
													<!-- 	De-Install Date	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Earliest Install Data	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Branch	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >Itasca - Bruning</label></td>
													<!-- 	Service Request #	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >22345678</label></td>
													<!-- 	Carrier	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >&nbsp;</label></td>
													<!-- 	Warehouse	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >ITASCA WH - CSA</label></td>
													<!-- 	Order Total	 -->
													<td><label ezfout ezfhyo="A" name="" ezfname="" >12,345,678.00</label></td>
												</tr>
											</ezf:skip>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>
</center>

<script type="text/javascript">
function showPlagin(idno){
    pc = ('PlagClose' + (idno));
    po = ('PlagOpen' + (idno));
    rt = ('btmTBL');
    xxSelFlg_H = this.document.getElementById( 'xxSelFlg_H' );
    if( document.getElementById(pc).style.display == "none" ) {
      document.getElementById(pc).style.display = "block";
      document.getElementById(po).style.display = "none";
      document.getElementById(rt).style.height = "150";
      xxSelFlg_H.value = "Y";
    }
    else {
      document.getElementById(pc).style.display = "none";
      document.getElementById(po).style.display = "block";
      document.getElementById(rt).style.height = "453";
      xxSelFlg_H.value = "N";
    }
}
</script>


<%-- **** Task specific area ends here **** --%>
