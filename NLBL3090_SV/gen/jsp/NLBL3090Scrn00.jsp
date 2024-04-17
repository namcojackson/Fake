<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160706145930 --%>
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
			<input type="hidden" name="pageID" value="NLBL3090Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Call Coordinator Assignment Setup">

            <%-- ********************** --%>
            <%-- *** Upper Tab Area *** --%>
            <%-- ********************** --%>
            <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<%-- ######################################## HEADER TAB ######################################## --%>
							<ezf:skip>
								<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
									<tr>
										<td width="1070px" height="28px" valign="bottom">
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
												<tr title="Call Coordinator Assignment Setup">
													<td width="107px" align="center" class="same"><a href="./NLBL3090Scrn00.html">Coord Asg</a></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</ezf:skip>

							<div class="pTab_BODY">
                                <table border="0" cellpadding="1" cellspacing="0" style="margin-left:5px;" >
                                    <col width="60px">
                                    <col width="">
                                    <col width="">
                                    <col width="240px">
                                    <tr height="20px">
                                        <td class="stab"><ezf:anchor name="rtlWhCd_H2" ezfName="rtlWhCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Wh" >Warehouse</ezf:anchor></td>
                                        <td><ezf:inputText name="rtlWhCd_H1" ezfName="rtlWhCd_H1" value="A01" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                        <td style="text-align: center;"><ezf:inputButton name="Search_WH" ezfName="WarehouseNm" value=">>" /></td>
                                        <td><ezf:inputText name="rtlWhNm_H1" ezfName="rtlWhNm_H1" value="MONROE" otherAttr=" size=\"30\" maxlength=\"45\" ezftoupper=\"\""/></td>
                                    </tr>
                                </table>
                                <table border="0" cellpadding="1" cellspacing="0" style="margin-left:5px;" >
                                    <col width="60px">
                                    <col width="">
                                    <col width="">
                                    <col width="105px">

                                    <col width="">
                                    <col width="">
                                    <col width="">
                                    <col width="105px">

                                    <col width="">
                                    <col width="">
                                    <col width="">
                                    <col width="105px">

                                    <col width="">
                                    <col width="">
                                    <col width="">
                                    <col width="">
                                    <col width="">
                                    <col width="30px">

                                    <col width="">
                                    <tr height="20px">

                                        <td class="stab"><ezf:anchor name="mgrPsnCd_H2" ezfName="mgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Manager" >Manager</ezf:anchor></td>
                                        <td><ezf:inputText name="mgrPsnCd_H1" ezfName="mgrPsnCd_H1" value="K00134" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                        <td style="text-align: center;"><ezf:inputButton name="Search_MgrNm" value=">>" /></td>
                                        <td><ezf:inputText name="fullPsnNm_HM" ezfName="fullPsnNm_HM" value="KNOWLES, BRUCE" otherAttr=" size=\"13\" maxlength=\"62\" ezftoupper=\"\""/></td>

                                        <td class="stab"><ezf:anchor name="supvPsnCd_H2" ezfName="supvPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Supervisor" >Supervisor</ezf:anchor></td>
                                        <td><ezf:inputText name="supvPsnCd_H1" ezfName="supvPsnCd_H1" value="J33456" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                        <td style="text-align: center;"><ezf:inputButton name="Search_SupvNm" value=">>" /></td>
                                        <td><ezf:inputText name="fullPsnNm_HS" ezfName="fullPsnNm_HS" value="KNOWLES, BRUCE" otherAttr=" size=\"13\" maxlength=\"62\" ezftoupper=\"\""/></td>

                                        <td class="stab"><ezf:anchor name="schdCoordPsnCd_H2" ezfName="schdCoordPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Coordinator" >Coordinator</ezf:anchor></td>
                                        <td><ezf:inputText name="schdCoordPsnCd_H1" ezfName="schdCoordPsnCd_H1" value="C88997" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                        <td style="text-align: center;"><ezf:inputButton name="Search_SchdCoordNm" value=">>" /></td>
                                        <td><ezf:inputText name="fullPsnNm_HC" ezfName="fullPsnNm_HC" value="KNOWLES, BRUCE" otherAttr=" size=\"13\" maxlength=\"62\" ezftoupper=\"\""/></td>

                                        <td class="stab">Effective Date</td>
                                        <td><ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="06/01/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                        <td class="stab"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effFromDt_H1', 4 );"></td>
                                        <td class="stab">-</td>
                                        <td><ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" value="06/30/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                        <td class="stab"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effThruDt_H1', 4 );"></td>

                                        <td style="text-align:right;">
                                            <ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
                                        </td>

                                    </tr>
                                </table>
								<hr>
								<%-- ######################################## DETAIL ######################################## --%>
								<%-- ###TAB - HEAD --%>
								<div class="pTab_HEAD">
									<ul>
										<table width="900" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td width="96%">
													<div>
													<li title="Assign" id="Assign" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_H1" ezfEmulateBtn="1" ezfGuard="TAB_Assign" >Assign</ezf:anchor></li>
													<li title="Coordination" id="Coordination" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_H2" ezfEmulateBtn="1" ezfGuard="TAB_Coordination" >Coordination</ezf:anchor></li>
													</div>
												</td>
											</tr>
										</table>
									</ul>
								</div>

								<div class="pTab_BODY_In">
									<c:choose>
										<%-- TAB_Assign --%>
										<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Assign'}">
											<div id="TabContent-Assign"><!-- Added -->
	                                            <script type="text/javascript">
	                                                document.getElementById("Assign").className = "pTab_ON";
	                                                document.getElementById("Coordination").className = "pTab_OFF";
	                                            </script>
												<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px; margin-top:0px;">
													<col width="350px"  align="left">
													<col width="250px"  align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
													<col width="506px"  align="right">
													<tr>
														<%-- =============== Control Button =============== --%>
														<td>
															<table border="0">
																<tr height="20px">
																	<td><ezf:inputButton name="AddNewMgr" value="Add New Mgr" htmlClass="pBtn6" otherAttr=" style=\"width:100px;\""/></td>
																	<td><ezf:inputButton name="AddNewSupr" value="Add New Supr" htmlClass="pBtn6" otherAttr=" style=\"width:100px;\""/></td>
																	<td><ezf:inputButton name="btn11" value="Add New Coord (F11)" htmlClass="pBtn6" otherAttr=" style=\"width:115px;\""/></td>
																</tr>
															</table>
														</td>
														<%-- The horizontal space between Fileters Parts and Paging Parts --%>
														<td>
														</td>
														<%-- =============== Paging Parts =============== --%>
														<td>
															<div align="right">
																<table width="100%">
																	<tr align="right">
																		<td>
																			<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																				<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																				<jsp:param name="TableNm"     value="A" />
																				<jsp:param name="ShowingFrom" value="xxPageShowFromNum_P1" />
																				<jsp:param name="ShowingTo"   value="xxPageShowToNum_P1" />
																				<jsp:param name="ShowingOf"   value="xxPageShowOfNum_P1" />
																			</jsp:include>
																		</td>
																	</tr>
																</table>
																<ezf:skip>
																<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
																	<tr>
																		<td>
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
																		</td>
																	</tr>
																</table>
																</ezf:skip>
															</div>
														</td>
													</tr>
												</table>

												<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td align="left" valign="top">
															<div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:1106;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('btmTBL'));">
																<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="38">
																	<col width="28"   align="center"><!-- Checkbox (M) -->
																	<col width="28"   align="center"><!-- Checkbox (S) -->
																	<col width="28"   align="center"><!-- Checkbox (C) -->
																	<col width="108"  align="center"><!-- Manager Code -->
																	<col width="136"  align="center"><!-- Manager Name -->
																	<col width="108"  align="center"><!-- Supervisor Code -->
																	<col width="136"  align="center"><!-- Supervisor Name -->
																	<col width="108"  align="center"><!-- Coordinator Code -->
																	<col width="136"  align="center"><!-- Coordinator Name -->
																	<col width="34"   align="center"><!-- State -->
																	<col width="84"   align="center"><!-- Effective Date From -->
																	<col width="84"   align="center"><!-- Effective Date To -->
																	<col width="70"   align="center"><!-- Carrier Code -->
																	<col width="136"  align="center"><!-- Carrier Name -->
																	<col width="180"  align="center"><!-- Carrier Contact -->
																	<col width="108"  align="center"><!-- Carrier Phone -->
																	<tr>
																		<td class="pClothBs" rowspan="2">M</td>
																		<td class="pClothBs" rowspan="2">S</td>
																		<td class="pClothBs" rowspan="2">C</td>
																		<td class="pClothBs" colspan="2">Manager</td>
																		<td class="pClothBs" colspan="2">Supervisor</td>
																		<td class="pClothBs" colspan="2">Coordinator</td>
																		<td class="pClothBs" rowspan="2">State</td>
																		<td class="pClothBs" colspan="2">Effective Date</td>
																		<td class="pClothBs" colspan="4">Carrier</td>
																	</tr>
																	<tr>
																		<td class="pClothBs">Code</td>
																		<td class="pClothBs">Name</td>
																		<td class="pClothBs">Code</td>
																		<td class="pClothBs">Name</td>
																		<td class="pClothBs">Code</td>
																		<td class="pClothBs">Name</td>
																		<td class="pClothBs">From</td>
																		<td class="pClothBs">To</td>
																		<td class="pClothBs">Code</td>
																		<td class="pClothBs">Name</td>
																		<td class="pClothBs">Contact</td>
																		<td class="pClothBs">Contact Phone</td>
																	</tr>
																</table>
															</div>
														</td>
													</tr>
													<tr valign="top">
														<td>
															<div id="btmTBL" style="overflow-y:scroll; height:378; overflow-x:scroll; width:1123;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
																<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Left">
																	<col width="28"   align="center"><!-- Checkbox (M) -->
																	<col width="28"   align="center"><!-- Checkbox (S) -->
																	<col width="28"   align="center"><!-- Checkbox (C) -->
																	<col width="108"  align="center"><!-- Manager Code -->
																	<col width="136"  align="center"><!-- Manager Name -->
																	<col width="108"  align="center"><!-- Supervisor Code -->
																	<col width="136"  align="center"><!-- Supervisor Name -->
																	<col width="108"  align="center"><!-- Coordinator Code -->
																	<col width="136"  align="center"><!-- Coordinator Name -->
																	<col width="34"   align="center"><!-- State -->
																	<col width="84"   align="center"><!-- Effective Date From -->
																	<col width="84"   align="center"><!-- Effective Date To -->
																	<col width="70"   align="center"><!-- Carrier Code -->
																	<col width="136"  align="left"><!-- Carrier Name -->
																	<col width="180"  align="center"><!-- Carrier Contact -->
																	<col width="108"  align="center"><!-- Carrier Phone -->
																	<ezf:row ezfHyo="A">
																		<!-- 1 -->
																		<tr height="28" id="id_A_row{EZF_ROW_NUMBER}">
																			<td><ezf:inputCheckBox name="xxChkBox_AM" ezfName="xxChkBox_AM" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_AM{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputCheckBox name="xxChkBox_AS" ezfName="xxChkBox_AS" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_AS{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputCheckBox name="xxChkBox_AC" ezfName="xxChkBox_AC" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_AC{EZF_ROW_NUMBER}\""/></td>
																			<td>
																				<ezf:inputText name="mgrPsnCd_AM" ezfName="mgrPsnCd_AM" value="K00134" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" id=\"mgrPsnCd_AM{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																				<ezf:inputButton name="OpenWin_S21PersonM" value="Mgr" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"btn_AM{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td><ezf:inputText name="fullPsnNm_AM" ezfName="fullPsnNm_AM" value="KEVIN RICH" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" id=\"fullPsnNm_AM{EZF_ROW_NUMBER}\""/></td>
																			<td>
																				<ezf:inputText name="supvPsnCd_AS" ezfName="supvPsnCd_AS" value="J33456" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" id=\"supvPsnCd_AS{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																				<ezf:inputButton name="OpenWin_S21PersonS" value="Supv" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"btn_AS{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td><ezf:inputText name="fullPsnNm_AS" ezfName="fullPsnNm_AS" value="JONE VAUGHN" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" id=\"fullPsnNm_AS{EZF_ROW_NUMBER}\""/></td>
																			<td>
																				<ezf:inputText name="schdCoordPsnCd_AC" ezfName="schdCoordPsnCd_AC" value="C88997" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" id=\"schdCoordPsnCd_AC{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																				<ezf:inputButton name="OpenWin_CdPerson" value="Coord" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"btn_AC{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td><ezf:inputText name="fullPsnNm_AC" ezfName="fullPsnNm_AC" value="CHRIS PILKINGTON" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" id=\"fullPsnNm_AC{EZF_ROW_NUMBER}\""/></td>
																			<td>
																				<ezf:inputText name="stCd_A" ezfName="stCd_A" value="MA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\""/>
																			</td>
																			<td>
																				<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																			</td>
																			<td>
																				<ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="9999/12/31" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																			</td>
																			<td>
																				<ezf:inputText name="carrCd_A" ezfName="carrCd_A" value="FDEG" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\""/>
																			</td>
																			<td><ezf:inputText name="locNm_A" ezfName="locNm_A" value="FEDEX GROUND" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\""/></td>
																			<td><ezf:inputText name="carrCtacEmlAddr_A" ezfName="carrCtacEmlAddr_A" value="FEDEX-EMAIL@fedex.com" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"30\""/></td>
																			<td><ezf:inputText name="carrCtacTelNum_A" ezfName="carrCtacTelNum_A" value="111-222-3333" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\""/></td>
																		</tr>
																	</ezf:row>
																</table>
															</div>
														</td>
													</tr>
												</table>
												<table>
													<colgroup>
														<col width="500" align="left">
														<col width="585" align="right">
													</colgroup>
													<tr>
														<td>
															<table border="0">
																<col width="">
																<tr>
																	<td align="stab"><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn6" /></td>
																</tr>
															</table>
														</td>
														<td>
														</td>
													</tr>
												</table>
											</div><!-- TabContent-Assign -->
										</c:when>

										<%-- TAB_Coordinator --%>
										<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Coordination'}">
											<div id="TabContent-Coordination"><!-- Added -->
												<script type="text/javascript">
													document.getElementById("Assign").className = "pTab_OFF";
													document.getElementById("Coordination").className = "pTab_ON";
												</script>
												<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px; margin-top:0px;">
													<col width="350px"  align="left">
													<col width="250px"  align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
													<col width="506px"  align="right">
													<tr>
														<%-- =============== Control Button =============== --%>
														<td>
															<table border="0">
																<tr height="20px">
																	<td><ezf:inputButton name="btn11" value="Add New Coord (F11)" htmlClass="pBtn6" otherAttr=" style=\"width:115px;\""/></td>
																	<td><ezf:inputButton name="btn12" value="Add New ST (F12)" htmlClass="pBtn6" otherAttr=" style=\"width:115px;\""/></td>
																</tr>
															</table>
														</td>
														<%-- The horizontal space between Fileters Parts and Paging Parts --%>
														<td>
														</td>
														<%-- =============== Paging Parts =============== --%>
														<td>
															<div align="right">
																<table width="100%">
																	<tr align="right">
																		<td>
																			<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																				<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																				<jsp:param name="TableNm"     value="B" />
																				<jsp:param name="ShowingFrom" value="xxPageShowFromNum_P2" />
																				<jsp:param name="ShowingTo"   value="xxPageShowToNum_P2" />
																				<jsp:param name="ShowingOf"   value="xxPageShowOfNum_P2" />
																			</jsp:include>
																		</td>
																	</tr>
																</table>
																<ezf:skip>
																<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
																	<tr>
																		<td>
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
																		</td>
																	</tr>
																</table>
																</ezf:skip>
															</div>
														</td>
													</tr>
												</table>

												<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td align="left" valign="top">
															<div id="topTBL_B" style="overflow-y:none; height:; overflow-x:hidden; width:1106;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('btmTBL_B'));">
																<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="38">
																	<col width="28"   align="center"><!-- Checkbox (C) -->
																	<col width="28"   align="center"><!-- Checkbox (ST) -->
																	<col width="108"  align="center"><!-- Coordinator Code -->
																	<col width="136"  align="center"><!-- Coordinator Name -->
																	<col width="64"  align="center"><!-- State -->
																	<col width="108"  align="center"><!-- Effective Date From -->
																	<col width="108"  align="center"><!-- Effective Date To -->
																	<col width="108"  align="center"><!-- Carrier Code -->
																	<col width="136"  align="center"><!-- Carrier Name -->
																	<col width="180"  align="center"><!-- Carrier Contact -->
																	<col width="108"  align="center"><!-- Carrier Phone -->
																	<tr>
																		<td class="pClothBs" rowspan="2">C</td>
																		<td class="pClothBs" rowspan="2">ST</td>
																		<td class="pClothBs" colspan="2">Coordinator</td>
																		<td class="pClothBs" rowspan="2">State</td>
																		<td class="pClothBs" colspan="2">Effective Date</td>
																		<td class="pClothBs" colspan="4">Carrier</td>
																	</tr>
																	<tr>
																		<td class="pClothBs">Code</td>
																		<td class="pClothBs">Name</td>
																		<td class="pClothBs">From</td>
																		<td class="pClothBs">To</td>
																		<td class="pClothBs">Code</td>
																		<td class="pClothBs">Name</td>
																		<td class="pClothBs">Contact</td>
																		<td class="pClothBs">Contact Phone</td>
																	</tr>
																</table>
															</div>
														</td>
													</tr>
													<tr valign="top">
														<td>
															<div id="btmTBL_B" style="overflow-y:scroll; height:378; overflow-x:scroll; width:1123;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL_B'));">
																<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="B_Left">
																	<col width="28"   align="center"><!-- Checkbox (C) -->
																	<col width="28"   align="center"><!-- Checkbox (ST) -->
																	<col width="108"  align="center"><!-- Coordinator Code -->
																	<col width="136"  align="center"><!-- Coordinator Name -->
																	<col width="64"   align="center"><!-- State -->
																	<col width="108"  align="center"><!-- Effective Date From -->
																	<col width="108"  align="center"><!-- Effective Date To -->
																	<col width="108"  align="center"><!-- Carrier Code -->
																	<col width="136"  align="left"><!-- Carrier Name -->
																	<col width="180"  align="center"><!-- Carrier Contact -->
																	<col width="108"  align="center"><!-- Carrier Phone -->
																	<ezf:row ezfHyo="B">
																		<!-- 1 -->
																		<tr height="28">
																			<td><ezf:inputCheckBox name="xxChkBox_BC" ezfName="xxChkBox_BC" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_BC{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputCheckBox name="xxChkBox_BS" ezfName="xxChkBox_BS" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																			<td>
																				<ezf:inputText name="schdCoordPsnCd_BC" ezfName="schdCoordPsnCd_BC" value="C88997" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" id=\"schdCoordPsnCd_BC{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																				<ezf:inputButton name="OpenWin_CdPerson" value="Coord" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"btn_BC{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td><ezf:inputText name="fullPsnNm_BC" ezfName="fullPsnNm_BC" value="CHRIS PILKINGTON" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" id=\"fullPsnNm_BC{EZF_ROW_NUMBER}\""/></td>
																			<td>
																				<ezf:inputText name="stCd_B" ezfName="stCd_B" value="MA" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>
																				<ezf:inputButton name="OpenWin_St" value="ST" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" />
																			</td>
																			<td>
																				<ezf:inputText name="effFromDt_B" ezfName="effFromDt_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effFromDt_B{EZF_ROW_NUMBER}\""/>
																				<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effFromDt_B{EZF_ROW_NUMBER}', 4 );">
																			</td>
																			<td>
																				<ezf:inputText name="effThruDt_B" ezfName="effThruDt_B" value="9999/12/31" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effThruDt_B{EZF_ROW_NUMBER}\""/>
																				<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effThruDt_B{EZF_ROW_NUMBER}', 4 );">
																			</td>
																			<td>
																				<ezf:inputText name="carrCd_B" ezfName="carrCd_B" value="FDEG" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
																				<ezf:inputButton name="OpenWin_Carrier" value="Carr" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" />
																			</td>
																			<td><ezf:inputText name="locNm_B" ezfName="locNm_B" value="FEDEX GROUND" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\""/></td>
																			<td><ezf:inputText name="carrCtacEmlAddr_B" ezfName="carrCtacEmlAddr_B" value="FEDEX-EMAIL@fedex.com" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"30\""/></td>
																			<td><ezf:inputText name="carrCtacTelNum_B" ezfName="carrCtacTelNum_B" value="111-222-3333" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\""/></td>
																		</tr>
																	</ezf:row>
																</table>
															</div>
														</td>
													</tr>
												</table>
												<table>
													<colgroup>
														<col width="500" align="left">
														<col width="585" align="right">
													</colgroup>
													<tr>
														<td>
															<table border="0">
																<col width="">
																<tr>
																	<td align="stab"><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn6" /></td>
																</tr>
															</table>
														</td>
														<td>
														</td>
													</tr>
												</table>
											</div><!-- TabContent-Coordinator -->
										</c:when>
									</c:choose>
								</div><!-- pTab_BODY_In -->
							</div>
						</td>
					</tr>
				</table>
			</center>
<div style="display:none;">
	<ezf:inputButton name="btn11" value="btn11" otherAttr=" id=\"btn11\""/>
	<ezf:inputButton name="btn12" value="btn12" otherAttr=" id=\"btn12\""/>
</div>
<script type="text/javascript">

	setKeyDownHandler();

	function setKeyDownHandler() {

		if( window.addEventListener ) {
		    window.addEventListener("keyup", emulateFuncKeyDown, false);
		} else if( document.attachEvent ) {
		    document.attachEvent("onkeyup", emulateFuncKeyDown);
		} else {
		    document.onkeyup = emulateFuncKeyDown;
		}
	}

	function emulateFuncKeyDown() {

		var code = event.keyCode;
		//alert("keyCode:["+event.keyCode+"]");

		if(event.keyCode >= 122 && event.keyCode <= 123) {
			event.keyCode = null;
			event.returnValue = false;
		}

		switch(code) {
		// F11
		case 122:
			//sendServer("Line_Config_Add");
			emulateOnClickEvent("btn11");
			break;
		// F12
		case 123:
			//sendServer("Line_Add");
			emulateOnClickEvent("btn12");
			break;
		default:
			break;
		}
		return;
	}

	function emulateOnClickEvent(elementId) {

		var elem = document.getElementById(elementId);
		if( /*@cc_on ! @*/ false ) {
			elem.fireEvent("onclick");
		} else {
			var evt = document.createEvent("MouseEvents");
			evt.initEvent("click", false, true);
			elem.dispatchEvent(evt);
		}
	}
</script>

<%-- **** Task specific area ends here **** --%>
