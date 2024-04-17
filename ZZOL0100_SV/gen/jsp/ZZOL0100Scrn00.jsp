<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20110408233903 --%>
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
			<input type="hidden" name="pageID" value="ZZOL0100Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Menu Information Maintenance">
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<!-- include S21BusinessProcessTAB -->
		<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
		<%--
		<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
			<tr>
				<td width="1100px" height="28px" valign="bottom">
						<div>
							<table border="0" cellpadding="0" cellspacing="0">
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
										<tr title='Order Entry'>
											<td width="107px" align="center" class="same">
												Order Entry
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='Hold Release'>
											<td width="90px" align="center" class="disabled">
												Hld Rlse
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='Soft Allocation'>
											<td width="90px" align="center" class="disabled">
												Soft Alloc
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='Hard Allocation'>
											<td width="90px" align="center" class="disabled">
												Hard Alloc
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='Order Inquiry by Status'>
											<td width="90px" align="center" class="disabled">
												Ordr Inq
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='Sales Summary'>
											<td width="90px" align="center" class="disabled">
												Sales Sum
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='ATP Inquiry by Order'>
											<td width="90px" align="center" class="disabled">
												ATP Inq
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='iW Remote Upload'>
											<td width="90px" align="center" class="disabled">
												iW Rmt Upld
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='iW Remote Inquiry'>
											<td width="90px" align="center" class="disabled">
												iW Rmt Inq
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
										<tr title='Upload Screen for LAN Data from Canon Inc.'>
											<td width="90px" align="center" class="disabled">
												LAN Upld
											</td>
											<td width="17px" align="center">
												<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
											</td>
										</tr>
									</table>
								</td>
							</table>
						</div>
				</td>
				<td valign="bottom" align="center">
						<a href="#" id="PrevPageTabIndex"><img id="PrevPageBtn" src="./img/tab/tabbackbutton.png" alt="Prev" border="0"></a>
				</td>
				<td valign="bottom" align="center">
					<a href="#" id="NextPageTabIndex"><img id="NextPageBtn" src="./img/tab/tabnextbutton.png" alt="Next" border="0"></a>
				</td>
			</tr>
		</table>
		--%>
		<div class="pTab_BODY">
			<table width="90%" border="0" align="center">
				<col valign="top">
				<tr>
					<td>
						<table width="100%" height="5" border="0">
							<tr valign="middle">
								<td>&nbsp;</td>
							</tr>
						</table>
						<table width="100%" height="35" border="0">
							<col width="50">
							<col width="130">
							<col width="40">
							<col width="100">
							<col width="580">
							<tr valign="middle">
								<td>&nbsp;</td>
								<td class="stab" align="left"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor></td>
								<td><ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="ABR" otherAttr=" size=\"4\" ezftoupper=\"\""/></td>
								<td class="pout" id="fcNmField"><ezf:label name="glblCmpyNm" ezfName="glblCmpyNm" /></td>
								<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" /></td>
							</tr>
							<tr>
								<td colspan="5"><hr></td>
							</tr>
						</table>
						<table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" >
							<col width="360" align="center">
							<col width="50"  align="center">
							<col width="50" align="center">
							<col width="200" align="center">
							<col width="200" align="center">
							<col width="120" align="center">
							<tr height="24">
								<td class="pClothBs">Information Text</td>
								<td class="pClothBs">Usable</td>
								<td class="pClothBs">Order</td>
								<td class="pClothBs">Effective Date From</td>
								<td class="pClothBs">Effective Date To</td>
								<td class="pClothBs">&nbsp;</td>
							</tr>
							<tr height="75">
								<td><ezf:textArea name="menuInfoTxt" ezfName="menuInfoTxt" otherAttr=" rows=\"5\" cols=\"43\" size=\"43\""/></td>
								<td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" /></td>
								<td><ezf:inputText name="menuInfoSortNum" ezfName="menuInfoSortNum" value="99999" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
								<td><ezf:inputText name="menuEffFromDt" ezfName="menuEffFromDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
									<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('menuEffFromDt', 4);" >
									<ezf:select name="menuEffFromTm_F3" ezfName="menuEffFromTm_F3" ezfBlank="1" ezfCodeName="menuEffFromTm_F1" ezfDispName="xxHrsMn_F2" otherAttr=" style=\"width:70px;\""/>
								</td>
								<td><ezf:inputText name="menuEffThruDt" ezfName="menuEffThruDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
									<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('menuEffThruDt', 4);" >
									<ezf:select name="menuEffThruTm_T3" ezfName="menuEffThruTm_T3" ezfBlank="1" ezfCodeName="menuEffThruTm_T1" ezfDispName="xxHrsMn_T2" otherAttr=" style=\"width:70px;\""/>
								</td>
								<td><ezf:inputButton name="Add" value="Add" htmlClass="pBtn4" /><ezf:inputButton name="Upd" value="Upd" htmlClass="pBtn4" /></td>
							</tr>
						</table>
						<%-- Pagenation --%>
						<table width="100%">
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr align="right">
								<td>
									<%--
									<table border="0" cellpadding="1" cellspacing="0">
										<col width="54"  align="center">
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="10">
										<col>
										<col width="10">
										<col width="50">
										<tr>
											<td class="stab">Showing</td>
											<td class="pOut">1</td>
											<td class="stab">to</td>
											<td class="pOut">3</td>
											<td class="stab">of</td>
											<td class="pOut">3</td>
											<td>&nbsp;</td>
											<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
											<td></td>
											<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
										</tr>
									</table>
									--%>
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
						
						<table border="0" >
							<td width="100%" valign="top">
								<tr>
									<table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" >
										<col width="360" align="center">
										<col width="50"  align="center">
										<col width="50" align="center">
										<col width="200" align="center">
										<col width="200" align="center">
										<col width="120" align="center">
										<tr height="24">
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'menuInfoTxt_A1' )">Information Text<img id="sortIMG.menuInfoTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'menuInfoVwbleFlg_A1' )">Usable<img id="sortIMG.menuInfoVwbleFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'menuInfoSortNum_A1' )">Order<img id="sortIMG.menuInfoSortNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxFromDt_A1' )">Effective Date From<img id="sortIMG.xxFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxThruDt_A2' )">Effective Date To<img id="sortIMG.xxThruDt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs">&nbsp;</td>
										</tr>
									</table>
									<div style="overflow-y:scroll; height:210; width:990;">
										<table id="A" style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0">
											<col width="30" align="center">
											<col width="330" align="left">
											<col width="50" align="center">
											<col width="50" align="right">
											<col width="200" align="center">
											<col width="200" align="center">
											<col width="120" align="center">
											<ezf:row ezfHyo="A">
												<tr height="48">
													<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td class="stab">
														<ezf:textArea name="menuInfoTxt_A1" ezfName="menuInfoTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" rows=\"3\" cols=\"43\" size=\"43\" style=\"background-color:transparent;scrollbar-base-color:#939393;scrollbar-face-color:#939393;scrollbar-arrow-color:#2A2A2A;scrollbar-shadow-color:#2A2A2A;scrollbar-darkshadow-color:#939393;scrollbar-highlight-color:#2A2A2A;scrollbar-3dlight-color:#939393;scrollbar-track-color:#939393;border:1px solid #737373;\""/>
													</td>
													<td><ezf:label name="menuInfoVwbleFlg_A1" ezfName="menuInfoVwbleFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="menuInfoSortNum_A1" ezfName="menuInfoSortNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td>
														<table border="0" cellpadding="0" cellspacing="0" >
															<col width="30" align="center">
															<col width="70" align="center">
															<col width="70" align="center">
															<col width="30" align="center">
															<td>&nbsp;</td>
															<td>
																<ezf:label name="xxFromDt_A1" ezfName="xxFromDt_A1" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>
																<ezf:label name="xxHrsMn_A1" ezfName="xxHrsMn_A1" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>&nbsp;</td>
														</table>
													</td>
													<td>
														<table border="0" cellpadding="0" cellspacing="0" >
															<col width="30" align="center">
															<col width="70" align="center">
															<col width="70" align="center">
															<col width="30" align="center">
															<td>&nbsp;</td>
															<td>
																<ezf:label name="xxThruDt_A2" ezfName="xxThruDt_A2" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>
																<ezf:label name="xxHrsMn_A2" ezfName="xxHrsMn_A2" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>&nbsp;</td>
														</table>
													</td>
													<td><ezf:inputButton name="Edit" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn4" /></td>
												</tr>
											</ezf:row>
											<ezf:skip>
											<tr height="48">
												<td ><input type="checkbox"></td>
												<td>
													<textarea  ezfhyo="A" rows="3" cols="43" name="menuInfoTxt_A1" ezfname="menuInfoTxt_P1" style="background-color:transparent;scrollbar-base-color:#939393;scrollbar-face-color:#939393;scrollbar-arrow-color:#2A2A2A;scrollbar-shadow-color:#2A2A2A;scrollbar-darkshadow-color:#939393;scrollbar-highlight-color:#2A2A2A;scrollbar-3dlight-color:#939393;scrollbar-track-color:#939393;border:1px solid #737373;">between 0am and 7am(EST) Mon - Sat and the whole day on Sunday.
													</textarea>
												</td>
												<td ><label ezfout>Y</label></td>
												<td ><label ezfout>   2</label></td>
												<td >
													<table border="0" cellpadding="0" cellspacing="0" >
														<col width="30" align="center">
														<col width="70" align="center">
														<col width="70" align="center">
														<col width="30" align="center">
														<td>&nbsp;</td>
														<td>
															<label ezfout ezfhyo="A" name="xxDtTm_A1" ezfname="xxDtTm_A1">08/01/2009</label>
														</td>
														<td>
															<label ezfout ezfhyo="A" name="xxDtTm_A1" ezfname="xxDtTm_A1">10:20</label>
														</td>
														<td>&nbsp;</td>
													</table>
												</td>
												<td ><label ezfout>&nbsp;</label></td>
												<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
											</tr>
											<tr height="48">
												<td ><input type="checkbox"></td>
												<td>
													<textarea  ezfhyo="A" rows="3" cols="43" name="menuInfoTxt_A1" ezfname="menuInfoTxt_P1" style="background-color:transparent;scrollbar-base-color:#939393;scrollbar-face-color:#939393;scrollbar-arrow-color:#2A2A2A;scrollbar-shadow-color:#2A2A2A;scrollbar-darkshadow-color:#939393;scrollbar-highlight-color:#2A2A2A;scrollbar-3dlight-color:#939393;scrollbar-track-color:#939393;border:1px solid #737373;">-Attention-
													</textarea>
												</td>
												<td ><label ezfout>N</label></td>
												<td ><label ezfout>   3</label></td>
												<td>&nbsp;</td>
												<td >
													<table border="0" cellpadding="0" cellspacing="0" >
														<col width="30" align="center">
														<col width="70" align="center">
														<col width="70" align="center">
														<col width="30" align="center">
														<td>&nbsp;</td>
														<td>
															<label ezfout ezfhyo="A" name="xxDtTm_A1" ezfname="xxDtTm_A1">08/01/2001</label>
														</td>
														<td>
															<label ezfout ezfhyo="A" name="xxDtTm_A1" ezfname="xxDtTm_A1">&nbsp;</label>
														</td>
														<td>&nbsp;</td>
													</table>
												</td>
												<td><input type="Button" name="Edit" value="Edit" class="pBtn4" onclick="sendServer(this)" /></td>
											</tr>
											</ezf:skip>
										</table>
									</div>
								</tr>
							</td>
						</table>
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td height="10">&nbsp;</td>
							</tr>
							<tr>
								<col width="90" align="left" valign="top">
								<col width="200" align="left" valign="top">
								<col width="80" align="left" valign="top">
								<col width="450" align="left" valign="top">
								<td class="stab">Standard Date</td>
								<td><ezf:inputText name="menuEffFromDt_P0" ezfName="menuEffFromDt_P0" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
									<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('menuEffFromDt_P0', 4);" >
									<ezf:select name="menuEffFromTm_P3" ezfName="menuEffFromTm_P3" ezfBlank="1" ezfCodeName="menuEffFromTm_P1" ezfDispName="xxHrsMn_P2" otherAttr=" style=\"width:70px;\""/>
								</td>
								<td><ezf:inputButton name="Preview" value="Preview" htmlClass="pBtn4" /></td>
								<td>
									<table border="0">
										<tr>
											<td width="24%" height="94" align="center" valign="top"><img src="img/menu/information.png"></td>
											<td width="76%" height="94" align="left" valign="top">
												<ezf:textArea name="menuInfoTxt" ezfName="menuInfoTxt_P1" otherAttr=" rows=\"5\" cols=\"50\" style=\"background-color:transparent;scrollbar-base-color:#939393;scrollbar-face-color:#939393;scrollbar-arrow-color:#2A2A2A;scrollbar-shadow-color:#2A2A2A;scrollbar-darkshadow-color:#939393;scrollbar-highlight-color:#2A2A2A;scrollbar-3dlight-color:#939393;scrollbar-track-color:#939393;border:1px solid #737373;\""/>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
