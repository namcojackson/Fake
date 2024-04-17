<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160404141818 --%>
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
			<input type="hidden" name="pageID" value="NWAL1710Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Order Category Search Screen">

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
													<li title="Role Maintenance" class="pTab_ON"><a href="#">Rle Mnt</a></li>
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
							<div class="pTab_BODY_In">
					
					<table border="0" cellpadding="0" cellspacing="0" height="80">
						<tr>
							<td valign="top">
                            </td>
							<td valign="top" width="">
								<table width="" cellpadding="0" border="0" >
									<col align="left" width="5">
									<col align="left" width="100">
									<col align="left" width="130">
									<col align="left" width="120">
									<col align="left" width="160">
									<col align="left" width="100">
									<col align="left" width="120">
									<col align="left" width="100">
									<col align="left" width="100">
									<col align="left" width="100">
									<tr>
										<td class="stab">&nbsp;</td>
										<td class="stab">Order Category(*)</td>
										<td>
											<ezf:inputText name="dsOrdCatgNm" ezfName="dsOrdCatgNm" value="WWWWWWWWWW" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\""/>
										    
										</td>
										<td class="stab">Workflow </td>
										<td>
										    <ezf:select name="ordProcTpCd" ezfName="ordProcTpCd" ezfBlank="1" ezfCodeName="ordProcTpCd_P" ezfDispName="ordProcTpDescTxt_P" otherAttr=" style=\"width:139px\""/>
										</td>
										<td class="stab">
										    Effective From
										</td>
										<td>
											<ezf:inputText name="effFromDt" ezfName="effFromDt" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
										    <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >
										</td>
										<td class="stab">Effective Thru</td>
										<td>
											<ezf:inputText name="effThruDt" ezfName="effThruDt" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
										    <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" >
										</td>
										<td class="stab">&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">&nbsp;</td>
										<td class="stab">Reason Code(*)</td>
										<td><ezf:inputText name="dsOrdTpNm" ezfName="dsOrdTpNm" value="12345678" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td class="stab">Line of Business </td>
										<td>
										    <ezf:select name="lineBizTpCd" ezfName="lineBizTpCd" ezfBlank="1" ezfCodeName="lineBizTpCd_P" ezfDispName="lineBizTpDescTxt_P" otherAttr=" style=\"width:139px\""/>
										</td>
										<td class="stab">
										    Active Only
										</td>
										<td><ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="Y" /></td>
										<td>
											
										</td>
										<td>
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
										</td>
										<td>
											<ezf:inputButton name="Create_New" value="Create New" htmlClass="pBtn6" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
								<hr style="height: 0px;" cellpadding="0">

<%--------------------------------%>
<%-- Detail						--%>
<%--------------------------------%>
								<table border="0" cellpadding="0" cellspacing="0" width="1100">
									<tr align="right">
											<td>
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
								<ezf:skip>
								<table border="0" cellpadding="0" width="1100">
									<tr>
										<td align="left">
											<table border="0" cellpadding="0" align="left" cellspacing="0">
												<col>
												<tr>
													<td><br></td>
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
											<col width="20">
											<col>
											<col width="1">
											<col>
											<tr>
												<td class="stab">Showing</td>
												<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
												<td class="stab">to</td>
												<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
												<td class="stab">of</td>
												<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
											
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

								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td align="top" width="1130">
											<%-- ### MEISAI - LEFT TBL - TOP --%>
											<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none; width:440; height:30; float:left;word-break: break-all;">
												<table border="1" cellpadding="1" cellspacing="0" width="440" height="30" style="table-layout: fixed;">
													<col align="center" width="70">
													<col align="center" width="150">
													<col align="center" width="210">
													<tr>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsOrdCatgCd_A' )">Catg #<img id="sortIMG.dsOrdCatgCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsOrdCatgNm_A' )">Order Category<img id="sortIMG.dsOrdCatgNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsOrdTpNm_A' )">Reason Code<img id="sortIMG.dsOrdTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - Right TBL - TOP --%>
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:665; height:30; float:left;word-break: break-all;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="1000" height="30" style="table-layout: fixed;">
												    <col align="center" width="70">
													<col align="center" width="70">
													<col align="center" width="70">
													<col align="center" width="70">
													<col align="center" width="35">
													<col align="center" width="35">
													<col align="center" width="35">
													
													<tr>
													    <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ordProcTpNm_A' )">Workflow<img id="sortIMG.ordProcTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsOrdRsnGrpNm_A' )">Sub Reason<img id="sortIMG.dsOrdRsnGrpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsOrdTpDescTxt_A' )">Description<img id="sortIMG.dsOrdTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'lineBizTpNm_A' )">LOB<img id="sortIMG.lineBizTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A' )">Start Date<img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A' )">End Date<img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxYesNoNm_A' )">Active<img id="sortIMG.xxYesNoNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - LEFT TBL - BOTTOM --%>
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:411; width:440; float:left;word-break: break-all;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL' ))" >
												<table border="1" cellpadding="1" cellspacing="0" width="440" id="A1" style="table-layout: fixed;">
													<col align="center" width="70">
													<col align="Left" width="150">
													<col align="Left" width="210">
													<ezf:row ezfHyo="A">
														<tr height="20">
															<td>
															    <ezf:inputText name="dsOrdCatgCd_A" ezfName="dsOrdCatgCd_A" value="1000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"4\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td>
															    <ezf:inputText name="dsOrdCatgNm_A" ezfName="dsOrdCatgNm_A" value="CASH,CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td>
															    <ezf:anchor name="" ezfName="Select_Reason" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Reason" >
															        <ezf:label name="dsOrdTpNm_A" ezfName="dsOrdTpNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" align=\"left\""/>
															    </ezf:anchor>
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
													
													</ezf:skip>
												</table>
											</div>
											<%-- ### MEISAI - RIGHT TBL - BOTTOM --%>
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:428; width:682; float:left;word-break: break-all;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="1000" id="A2" style="table-layout: fixed;">
												    <col align="Left" width="70">
													<col align="Left" width="70"><!--Manager -->
													<col align="Left" width="70"><!--Specialist -->
													<col align="Left" width="70"><!--Commisionable -->
													<col align="Left" width="35"><!--Admin -->
													<col align="Left" width="35"><!--Golden Eagle Specialis -->
													<col align="center" width="35"><!--Credit/Rebill Approval Limit -->
													
													
													<ezf:row ezfHyo="A">
														<tr height="20">
														    <td>
															    <ezf:inputText name="ordProcTpNm_A" ezfName="ordProcTpNm_A" value="WWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td>
																<ezf:inputText name="dsOrdRsnGrpNm_A" ezfName="dsOrdRsnGrpNm_A" value="WWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td>
																<ezf:inputText name="dsOrdTpDescTxt_A" ezfName="dsOrdTpDescTxt_A" value="WWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td>
																<ezf:inputText name="lineBizTpNm_A" ezfName="lineBizTpNm_A" value="WWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" style=\"border:none;background-color:transparent;padding:0px;\""/>
																
															</td>
															<td>
																<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="12/12/9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td>
																<ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="12/12/9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td>
																<ezf:inputText name="xxYesNoNm_A" ezfName="xxYesNoNm_A" value="Yes" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
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

<%-- **** Task specific area ends here **** --%>
