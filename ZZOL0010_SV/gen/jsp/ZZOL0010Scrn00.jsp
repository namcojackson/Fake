<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100128014852 --%>
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

<center>
			<!-- include S21BusinessProcessTAB -->
						<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

						<!-- <table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
							<tr>
								<td width="1100px" height="28px" valign="bottom">
										<div>
											<table border="0" cellpadding="0" cellspacing="0">
												<td>
													<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
														<tr title='Order Entry'>
															<td width="107px" align="center" class="same">
																Archive View
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
						</table> -->
		<div class="pTab_BODY">
	<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<!-- Header Area Starts Here -->
				<table align="center" height="72" width="980" border="0">
					<col valign="top">
					<tr>
						<td width="32">
						</td>
						<td>
							<table border="0">
								<col width="200">
								<col width="72">
								<col width="40">
								<col width="168">
								<col width="32">
								<col width="280">
								<col width="64">
								<tr>
									<td class="stab"><label>Business Application ID(*)</label></td>
									<td class="stab"><ezf:inputText name="ezBusinessID_00" ezfName="ezBusinessID_00" value="XXXXXXXX" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
									<td class="stab"></td>
									<td class="stab"><label>Online Stop Flag</label></td>
									<td class="stab">
										<ezf:select name="ezOnlStopFlag_SV" ezfName="ezOnlStopFlag_SV" ezfBlank="1" ezfCodeName="ezOnlStopFlag_CD" ezfDispName="xxFlgNm_OB" />
									</td>
									<td class="stab"></td>
									<td class="stab"></td>
								</tr>
							</table>
							<table border="0">
								<col width="200">
								<col width="72">
								<col width="40">
								<col width="168">
								<col width="32">
								<col width="280">
								<col width="64">
								<tr>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >User Company CD</ezf:anchor></td>
									<td class="stab"><ezf:inputText name="ezCompanyCode" ezfName="ezCompanyCode" value="XXXX" otherAttr=" size=\"4\" ezftoupper=\"\""/></td>
									<td class="stab"></td>
									<td class="stab"><label>Online Event Process Log</label></td>
									<td class="stab">
										<ezf:select name="ezAcctInfoMode_SV" ezfName="ezAcctInfoMode_SV" ezfBlank="1" ezfCodeName="ezAcctInfoMode_CD" ezfDispName="xxFlgNm_BI" />
									</td>
									<td class="stab"></td>
									<td class="stab"></td>
								</tr>
							</table>
							<table border="0">
								<col width="200">
								<col width="72">
								<col width="40">
								<col width="168">
								<col width="32">
								<col width="280">
								<col width="64">
								<tr>
									<td class="stab"></td>
									<td class="stab"></td>
									<td class="stab"></td>
									<td class="stab"><label>Debug Log</label></td>
									<td class="stab">
										<ezf:select name="ezDebugLevel_SV" ezfName="ezDebugLevel_SV" ezfBlank="1" ezfCodeName="ezDebugLevel_CD" ezfDispName="xxFlgNm_DL" />
									</td>
									<td class="stab"></td>
									<td align="right" class="stab">
										<ezf:inputButton name="Search" value="Search" htmlClass="pBtn4" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- Detail Header Area Starts Here-->
		<tr>
			<td>
			<table width="1100" border="0" cellpadding="1" cellspacing="0">
			<tr>
			<col width="5%">
			<col cidth="95%">
			<td></td>
			<td>
			<hr align="center" width="92%">
			</td>
			</tr>
			</table>
				<table width="978" align="center" cellpadding="0" border="0">
					<tr>
						<td width="32">
						</td>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="54">
								<col width="498">
								<col>
								<tr>
									<td align="center">
										<ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" />
									</td>
									<td></td>
									<td align="right">
									<!--<table border="0" cellpadding="1" cellspacing="0">
									<col width="54"  align="center">
									<col width="40"  align="right">
									<col width="16"  align="center">
									<col width="40"  align="right">
									<col width="16"  align="center">
									<col width="40"  align="right">
									<col width="5">
									<col>
									<col width="1">
									<col>
									<tr>
										<td class="stab">Showing</td>
										<td class="pOut">1</td>
										<td class="stab">to</td>
										<td class="pOut">10</td>
										<td class="stab">of</td>
										<td class="pOut">200</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
									</tr>
								</table> -->
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
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- Detail Area Starts Here -->
		<tr>
			<td valign="top">
				<table align="center" cellpadding="0" border="0">
					<tr>
						<td width="32">
						</td>
						<td>
							<table>
								<col width="86">
								<col width="152">
								<col width="136">
								<col width="90">
								<col width="90">
								<col width="108">
								<col width="140">
								<col width="84">
								<tr height="32">
									<td class="pClothBs"></td>
									<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezBusinessID_A1' )">Business Application ID<img id="sortIMG.ezBusinessID_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezCompanyCode_A1' )">User Company CD<img id="sortIMG.ezCompanyCode_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxHrsMnScd_AS' )">Start Time<img id="sortIMG.xxHrsMnScd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxHrsMnScd_AE' )">End Time<img id="sortIMG.xxHrsMnScd_AE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxEzOnlStopFlagDisp_A1' )">Online Stop Flag<img id="sortIMG.xxEzOnlStopFlagDisp_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxEzAcctInfoModeDisp_A1' )">Onl Event Proc Log<img id="sortIMG.xxEzAcctInfoModeDisp_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									<td class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxEzDebugLevelDisp_A1' )">Debug Log<img id="sortIMG.xxEzDebugLevelDisp_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="32">
						</td>
						<td>
							<div style="OVERFLOW-Y: scroll; HEIGHT: 388px; OVERFLOW-X: none; WIDTH: 938px">
							<table cellpadding="1" cellspacing="0" border="1" id="A">
								<col width="30">
								<col width="54">
								<col width="152">
								<col width="136">
								<col width="90">
								<col width="90">
								<col width="108">
								<col width="140">
								<col width="84">
								<tbody>
									<ezf:row ezfHyo="A">
										<tr>
											<td class="stab" align="center"><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
											<td align="center"><ezf:inputButton name="Edit" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn4" /></td>
											<td class="stab"><ezf:label name="ezBusinessID_A1" ezfName="ezBusinessID_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="stab"><ezf:label name="ezCompanyCode_A1" ezfName="ezCompanyCode_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="stab"><ezf:label name="xxHrsMnScd_AS" ezfName="xxHrsMnScd_AS" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="stab"><ezf:label name="xxHrsMnScd_AE" ezfName="xxHrsMnScd_AE" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="stab"><ezf:label name="xxEzOnlStopFlagDisp_A1" ezfName="xxEzOnlStopFlagDisp_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="stab"><ezf:label name="xxEzAcctInfoModeDisp_A1" ezfName="xxEzAcctInfoModeDisp_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<td class="stab"><ezf:label name="xxEzDebugLevelDisp_A1" ezfName="xxEzDebugLevelDisp_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
										<tr>
											<td class="stab" align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
											<td align="center"><input class="pBtn4" type="button" name="Edit" value="Edit" onclick="sendServer(this)" ezfhyo="A"></td>
											<td class="stab"><label ezfout name="ezBusinessID_A1" ezfname="ezBusinessID_A1" ezfhyo="A">NWAL0010</label></td>
											<td class="stab"><label ezfout name="ezCompanyCode_A1" ezfname="ezCompanyCode_A1" ezfhyo="A">ABR</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AS" ezfname="xxHrsMnScd_AS" ezfhyo="A">00:00:00</label></td>
											<td class="stab"><label ezfout name="xxHrsMnScd_AE" ezfname="xxHrsMnScd_AE" ezfhyo="A">24:00:00</label></td>
											<td class="stab"><label ezfout name="xxEzOnlStopFlagDisp_A1" ezfname="xxEzOnlStopFlagDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzAcctInfoModeDisp_A1" ezfname="xxEzAcctInfoModeDisp_A1" ezfhyo="A">Run</label></td>
											<td class="stab"><label ezfout name="xxEzDebugLevelDisp_A1" ezfname="xxEzDebugLevelDisp_A1" ezfhyo="A">Run</label></td>
										</tr>
									</ezf:skip>
								</tbody>
							</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</table>
	</div>
</center>
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="ZZOL0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Online Service Maintenance Search">

<%-- **** Task specific area ends here **** --%>
