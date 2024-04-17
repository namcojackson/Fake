<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160907103046 --%>
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
			<input type="hidden" name="pageID" value="NWWL0060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Subscription Setup">
			
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Distribution List Search" class="pTab_ON"><a href="#">Dist List</a></li>
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
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<!-- Prev/Next Page-->
					<table width="1120" height="35">
						<tr>
							<td width="3">&nbsp;</td>
							<td width="500" align="left" class="stab">
								Please subscription to those Notifications you are eligible for.
							</td>
							<td align="right" style="padding-right:10px;">
								<ezf:skip> 
								<table width="590" border="0" cellpadding="1" cellspacing="0">
									<tr>
										<td align="right">
								<table border="0" cellpadding="0" width="100%">
									<tr>
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
													<td><input type="text" name="xxPageShowCurNum" ezfName="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
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
										</td>
									</tr>
								</table>
								</ezf:skip>
								<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent"  value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"    value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"        value="FULL" />
								</jsp:include>
							</td>
						</tr>
					</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
				
<%-- #################################################### FROM DETAIL ################################################### --%>
					<div>
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<!-- @@@@@ Left -->
								<td style="padding-left:3px;">
									<div>
										<div id="TopTBL" style="overflow-y:none; overflow-x:none;">
											<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1105px" style="margin-right:20px" >
												<col align="center" width="029">	<!-- Sbscr -->
												<col align="center" width="050">	<!-- Notif ID -->
												<col align="center" width="150">	<!-- Name -->
												<col align="center" width="200">	<!-- Description -->
												<col align="center" width="070">	<!-- Business Area -->
												<col align="center" width="070">	<!-- Sub Area -->
												<col align="center" width="150">	<!-- Dist Name -->

												<tr id="id_row${EZF_ROW_NUMBER}" height="30">
													<td class="pClothBs">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfySbscrFlg_A' )">Sbscr
															<img id="sortIMG.ntfySbscrFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td class="pClothBs">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyHdrId_A' )">Notif ID
															<img id="sortIMG.ntfyHdrId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td class="pClothBs">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyHdrNm_A' )">Name
															<img id="sortIMG.ntfyHdrNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td class="pClothBs">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyHdrDescTxt_A' )">Description
															<img id="sortIMG.ntfyHdrDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td class="pClothBs">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyBizAreaTpDescTxt_A' )">Business Area
															<img id="sortIMG.ntfyBizAreaTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td class="pClothBs">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfySubAreaTpDescTxt_A' )">Sub Area
															<img id="sortIMG.ntfySubAreaTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td class="pClothBs">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyDistListNmListTxt_A' )">Dist Name
															<img id="sortIMG.ntfyDistListNmListTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
												</tr>
											</table>
										</div>
										
										<div id="RowTBL" style="width:1122px; height:480px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
											<table border="1" cellpadding="1" cellspacing="0" id="A" width="1105" style="table-layout:fixed;">
												<col align="center" width="029">	<!-- Sbscr -->
												<col align="center" width="050">	<!-- Notif ID -->
												<col align="center" width="150">	<!-- Name -->
												<col align="center" width="200">	<!-- Description -->
												<col align="center" width="070">	<!-- Business Area -->
												<col align="center" width="070">	<!-- Sub Area -->
												<col align="center" width="150">	<!-- Dist Name -->
												<tbody>
												<ezf:row ezfHyo="A">
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><ezf:inputCheckBox name="ntfySbscrFlg_A" ezfName="ntfySbscrFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"ntfySbscrFlg_A{EZF_ROW_NUMBER}\""/></td>
														<td align="left">
															<ezf:anchor name="ntfyHdrId_A" ezfName="ntfyHdrId_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_Setup" otherAttr=" id=\"ntfyHdrId_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
																<ezf:label name="ntfyHdrId_A" ezfName="ntfyHdrId_A" ezfHyo="A" ezfArrayNo="0" />
															</ezf:anchor>
														</td>
														<td><ezf:inputText name="ntfyHdrNm_A" ezfName="ntfyHdrNm_A" value="1234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"32\" style=\"border:none; background-color:transparent;\""/></td>
														<td><ezf:inputText name="ntfyHdrDescTxt_A" ezfName="ntfyHdrDescTxt_A" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"48\" style=\"border:none; background-color:transparent;\""/></td>
														<td><ezf:inputText name="ntfyBizAreaTpDescTxt_A" ezfName="ntfyBizAreaTpDescTxt_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/></td>
														<td><ezf:inputText name="ntfySubAreaTpDescTxt_A" ezfName="ntfySubAreaTpDescTxt_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/></td>
														<td><ezf:inputText name="ntfyDistListNmListTxt_A" ezfName="ntfyDistListNmListTxt_A" value="1234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"32\" style=\"border:none; background-color:transparent;\""/></td>
													</tr>
												</ezf:row>
												<ezf:skip>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
													<tr id="id_row{EZF_ROW_NUMBER}" height="24">
														<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
														<td align="left">
															<a href="#" onclick="sendServer('MoveWin_Setup')" id="ntfyDistListId_A#{EZF_ROW_NUMBER}" name="ntfyDistListId_A" ezfname="ntfyDistListId_A" ezfhyo="A" ezfanchortext>
																<label ezfout ezfhyo="A" name="ntfyDistListId_A" ezfname="ntfyDistListId_A">12345678</label>
															</a>
														</td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListNm_A" ezfname="ntfyDistListNm_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="16" value="123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
														<td align="left"><input type="text" ezfhyo="A"  name="ntfySubAreaTpDescTxt_A" ezfname="ntfySubAreaTpDescTxt_A" class="pPro" size="36" value="1234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
													</tr>
												</ezf:skip>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
<%-- #################################################### DETAIL ################################################### --%>
			</td>
		</tr>
	</table>
</center>
				

<%-- **** Task specific area ends here **** --%>
