<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100204013158 --%>
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
			<input type="hidden" name="pageID" value="ZZLL0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Data Trace Viewer">


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
						<!-- Parts or S21 -->
			<table width="100%" height="2" border="0">
				<tr valign="middle">
					<td></td>
				</tr>
			</table>
				<table align="center" border="0">
					<col width="40">
					<col width="120">
					<col width="160">
					<col width="120">
					<col width="160">
					<col width="70">
					<col width="160">
					<col width="90">
					<tr>
						<td></td>
						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >User Company CD</ezf:anchor></td>
						<!--<td class="stab"><label>User Company CD</label></td>-->
						<td><ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td class="stab"><label>User ID</label></td>
						<td><ezf:inputText name="ezInUserID" ezfName="ezInUserID" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
						<td class="stab"><label>Application ID (*)</label></td>
						<td><ezf:inputText name="ezInAplID" ezfName="ezInAplID" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td class="stab"><label>Access Type</label></td>
						<td>
							<ezf:select name="mntTrxTp" ezfName="mntTrxTp" ezfBlank="1" ezfCodeName="mntTrxTp_L1" ezfDispName="mntTrxTp_L2" />
						</td>
						<td class="stab"><label>Table ID (*)</label></td>
						<td><ezf:inputText name="mntTblNm" ezfName="mntTblNm" otherAttr=" size=\"15\" ezftoupper=\"\""/></td>
						<td class="stab"><label>Column</label></td>
						<td><ezf:inputText name="mntColumnNm" ezfName="mntColumnNm" otherAttr=" size=\"15\" ezftoupper=\"\""/></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td class="stab"><label>Old Value</label></td>
						<td><ezf:inputText name="mntOldVal" ezfName="mntOldVal" otherAttr=" size=\"15\""/></td>
						<td class="stab"><label>New Value</label></td>
						<td><ezf:inputText name="mntNewVal" ezfName="mntNewVal" otherAttr=" size=\"15\""/></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				
				<table align="center" border="0">
					<col width="40">
					<col width="120">
					<col width="775">
					<tr>
						<td></td>
						<td class="stab"><label>Primary Key (*)</label></td>
						<td><ezf:inputText name="mntPrimaryKey" ezfName="mntPrimaryKey" otherAttr=" size=\"81\""/></td>
						<td></td>
					</tr>
				</table>
				<table align="center" border="0">
					<col width="40">
					<col width="82">
					<col width="34">
					<col width="111">
					<col width="32">
					<col width="8">
					<col width="32">
					<col width="64">
					<col width="111">
					<col width="32">
					<col width="8">
					<col width="32">
					<col width="216">	
					<col width="54">
					<tr>
						<td></td>
						<td class="stab"><label>Log Date Time</label></td>
						<td class="stab" align="right"><label>From</label></td>
						<td><ezf:inputText name="xxFromDt" ezfName="xxFromDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
							<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt', 4);" >
						</td>
						<td>
							<ezf:select name="xxHrs_FS" ezfName="xxHrs_FS" ezfBlank="1" ezfCodeName="xxHrs_FC" ezfDispName="xxHrs_FD" />
						</td>
						<td>:</td>
						<td>
							<ezf:select name="xxMn_FS" ezfName="xxMn_FS" ezfBlank="1" ezfCodeName="xxMn_FC" ezfDispName="xxMn_FD" /></td>
						</td>
						<td class="stab" align="right"><label>To</label></td>
						<td><ezf:inputText name="xxToDt" ezfName="xxToDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
							<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt', 4);" >
						</td>
						<td>
							<ezf:select name="xxHrs_TS" ezfName="xxHrs_TS" ezfBlank="1" ezfCodeName="xxHrs_TC" ezfDispName="xxHrs_TD" />
						</td>
						<td>:</td>
						<td>
							<ezf:select name="xxMn_TS" ezfName="xxMn_TS" ezfBlank="1" ezfCodeName="xxMn_TC" ezfDispName="xxMn_TD" />
						</td>
						<td></td>
						<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" /></td>
					</tr>
				</table>
				<hr>
				<%-- Pagenation --%>
				<table width="1075">
					<tr align="right">
						<td>
							<!--<table border="0" cellpadding="1" cellspacing="0">
								<col width="54"  align="center">
								<col width="40"  align="right">
								<col width="16"  align="center">
								<col width="40"  align="right">
								<col width="16"  align="center">
								<col width="40"  align="right">
								<col width="10">
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
									<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
								</tr>
							</table> 	-->					
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
				
				<table align="center" height="35" border="1" cellpadding="1" cellspacing="0">
					<col width="153">
					<col width="25">
					<col width="34">
					<col width="57">
					<col width="106">
					<col width="66">
					<col width="90">
					<col width="74">
					<col width="74">
					<col width="74">
					<col width="208">
					<tr>
						<td class="pClothBs" align="center">
							<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxFromDt_TR' )">Date Time
							<img id="sortIMG.xxFromDt_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
						</td>
						<td class="pClothBs" align="center">
							<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezInTimeZone_TR' )">TZ
							<img id="sortIMG.ezInTimeZone_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
						</td>
						<td class="pClothBs" align="center">
							<a href="#" class="pSortCol" onclick="columnSort( 'A', 'glblCmpyCd_TR' )">UCC
							<img id="sortIMG.glblCmpyCd_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
						</td>
						<td class="pClothBs" align="center">
							<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezInUserID_TR' )">UID
							<img id="sortIMG.ezInUserID_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
						</td>
						<td class="pClothBs" align="center">
							<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezInAplID_TR' )">App ID
							<img id="sortIMG.ezInAplID_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
						</td>
						<td class="pClothBs" align="center">
							<a href="#" class="pSortCol" onclick="columnSort( 'A', 'mntTrxTp_TR' )">Acc Type
							<img id="sortIMG.mntTrxTp_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
						</td>
						<td class="pClothBs" align="center">
							<a href="#" class="pSortCol" onclick="columnSort( 'A', 'mntTblNm_TR' )">Table ID
							<img id="sortIMG.mntTblNm_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
						</td>
						<td class="pClothBs" align="center">
							<a href="#" class="pSortCol" onclick="columnSort( 'A', 'mntColumnNm_TR' )">Column
							<img id="sortIMG.mntColumnNm_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
						</td>
						<td class="pClothBs" align="center">
							<a href="#" class="pSortCol" onclick="columnSort( 'A', 'mntOldVal_TR' )">Old Value
							<img id="sortIMG.mntOldVal_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
						</td>
						<td class="pClothBs" align="center">
							<a href="#" class="pSortCol" onclick="columnSort( 'A', 'mntNewVal_TR' )">New Value
							<img id="sortIMG.mntNewVal_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
						</td>
						<td class="pClothBs" align="center">
							<a href="#" class="pSortCol" onclick="columnSort( 'A', 'mntPrimaryKey_TR' )">Primary Key
							<img id="sortIMG.mntPrimaryKey_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
						</td>
					</tr>
				</table>
				
				<div align="center" style="OVERFLOW-Y: scroll; OVERFLOW-X: none; height:315; WIDTH: 1087;">
				<table align="right" border="1" cellpadding="1" cellspacing="0" id="A">
					<col width="82">
					<col width="67">
					<col width="25">
					<col width="34">
					<col width="57">
					<col width="106">
					<col width="66">
					<col width="90">
					<col width="74">
					<col width="74">
					<col width="74">
					<col width="208">
					<tbody>
						<ezf:row ezfHyo="A">
						<tr>
							<td><ezf:label name="xxFromDt_TR" ezfName="xxFromDt_TR" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="xxHrsMnScd_TR" ezfName="xxHrsMnScd_TR" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="ezInTimeZone_TR" ezfName="ezInTimeZone_TR" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="glblCmpyCd_TR" ezfName="glblCmpyCd_TR" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:inputText name="ezInUserID_TR" ezfName="ezInUserID_TR" value="Q99999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\""/></td>
							<td><ezf:inputText name="ezInAplID_TR" ezfName="ezInAplID_TR" value="ZZZZ9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
							<td><ezf:label name="mntTrxTp_TR" ezfName="mntTrxTp_TR" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:inputText name="mntTblNm_TR" ezfName="mntTblNm_TR" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
							<td><ezf:inputText name="mntColumnNm_TR" ezfName="mntColumnNm_TR" value="XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
							<td><ezf:inputText name="mntOldVal_TR" ezfName="mntOldVal_TR" value="XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
							<td><ezf:inputText name="mntNewVal_TR" ezfName="mntNewVal_TR" value="YYYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
							<td><ezf:inputText name="mntPrimaryKey_TR" ezfName="mntPrimaryKey_TR" value="GGG" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"28\""/></td>
						</tr>
						</ezf:row>
						<ezf:skip>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>
							<td><label ezfOut name="xxFromDt_TR" ezfname="xxFromDt_TR" ezfhyo="A">MM/DD/YYYY</label></td>
							<td><label ezfOut name="xxHrsMnScd_TR" ezfname="xxHrsMnScd_TR" ezfhyo="A">HH:MM:SS</label></td>
							<td><label ezfOut name="ezInTimeZone_TR" ezfname="ezInTimeZone_TR" ezfhyo="A">EDT</label></td>
							<td><label ezfOut name="glblCmpyCd_TR" ezfname="glblCmpyCd_TR" ezfhyo="A">ABR</label></td>
							<td><input type="text" name="ezInUserID_TR" ezfname="ezInUserID_TR" ezfhyo="A"size="6" value="Q99999"></td>
							<td><input type="text" name="ezInAplID_TR" ezfname="ezInAplID_TR" ezfhyo="A" size="12" value="ZZZZ9999"></td>
							<td><label ezfOut name="mntTrxTp_TR" ezfname="mntTrxTp_TR" ezfhyo="A">L_DELETE</label></td>
							<td><input type="text" name="mntTblNm_TR" ezfname="mntTblNm_TR" ezfhyo="A" size="10"></td>
							<td><input type="text" name="mntColumnNm_TR" ezfname="mntColumnNm_TR" ezfhyo="A" value="XXXXX" size="8"></td>
							<td><input type="text" name="mntOldVal_TR" ezfname="mntOldVal_TR" ezfhyo="A"value="XXXXX" size="8"></td>
							<td><input type="text" name="mntNewVal_TR" ezfname="mntNewVal_TR" ezfhyo="A" value="YYYYY" size="8"></td>
							<td><input type="text" name="mntPrimaryKey_TR" ezfname="mntPrimaryKey_TR" ezfhyo="A" value="GGG" size="28"></td>
						</tr>																																																
						</ezf:skip>
					</tbody>
				</table>
				</div>
			</div>

<%-- **** Task specific area ends here **** --%>
