<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180821173517 --%>
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
			<input type="hidden" name="pageID" value="NFDL0170Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Customer Account Search Popup">
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<div class="pTab_BODY">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="left" width="980">
							<table border="0" align="left">
								<td valign="top" width="980">
								<div style="border-style: solid ; border-width: 1px; width:100%;">
								<table border="0" align="center">
								<col width="170">
								<col width="280">
								<col width="170">
								<col width="280">
								<col width="80">
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" >Bill To Customer Name(*)</ezf:anchor></td>
										<td><ezf:inputText name="billToCustAcctNm_H" ezfName="billToCustAcctNm_H" value="1234567890123456" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" >Bill To Customer Number(*)</ezf:anchor></td>
										<td><ezf:inputText name="billToCustAcctCd_H" ezfName="billToCustAcctCd_H" value="1234567890123456" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" >Ship To Customer Name(*)</ezf:anchor></td>
										<td><ezf:inputText name="shipToLocNm_H" ezfName="shipToLocNm_H" value="1234567890123456" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" >Ship To Customer Number(*)</ezf:anchor></td>
										<td><ezf:inputText name="shipToCustCd_H" ezfName="shipToCustCd_H" value="1234567890123456" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td></td>
									</tr>
									<tr>
										<td class="stab">Invoice Number From</td>
										<td><ezf:inputText name="invNum_FR" ezfName="invNum_FR" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td class="stab">Invoice Number To</td>
										<td><ezf:inputText name="invNum_TO" ezfName="invNum_TO" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td></td>
									</tr>
									<tr>
										<td class="stab">Source Number(*)</td>
										<td><ezf:inputText name="srcSysDocNum" ezfName="srcSysDocNum" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
										<td class="stab">Serial Number(*)</td>
										<td><ezf:inputText name="serNum_H" ezfName="serNum_H" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td></td>
									</tr>
									<tr>
										<td class="stab">Model Number(*)</td>
										<td><ezf:inputText name="mdlNm_H" ezfName="mdlNm_H" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td class="stab">Bill Number(*)</td>
										<td><ezf:inputText name="grpInvNum_H" ezfName="grpInvNum_H" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">Customer PO Number(*)</td>
										<td><ezf:inputText name="custIssPoNum_H" ezfName="custIssPoNum_H" otherAttr=" size=\"30\" maxlength=\"35\""/></td>
										<td class="stab">Include Closed Invoices</td>
										<td><ezf:inputCheckBox name="xxChkBox_CL" ezfName="xxChkBox_CL" value="Y" /></td>
										<td class="stab">
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" otherAttr=" id=\"btn11\""/>
										</td>
									</tr>
								</table>
								</div>
							</td>
							</table>
							</td>
						</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>
					<table width="100%">
						<col valign="top">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="1000">
									<tr>
										<td align="right">
											<ezf:skip>
												<table border="0" cellpadding="0" width="100%">
													<tr>
														<td align="left">	
															<table border="0" cellpadding="0" align="left" cellspacing="0">
																<col>
																<tr>
																	<td>Results 990 - 1000 of 1000</td>
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
																			
																	<td><input type="text" name="xxPageShowCurNum_31" value="1" size="4" maxlength="4" style="text-align:right" id="txtShowingCur" class="pPro" readOnly></td>
																	<td class="stab">/</td>
																	<td><input type="text" name="xxPageShowTotNum_31" ezfName="xxPageShowTotNum_31" size="4" maxlength="4" value="1" class="pPro" style="text-align:right" id="txtShowingTot" readOnly></td>
																	<td class="stab">page</td>
																	<td>&nbsp;</td>
																	<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'C')" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'C')" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'C')" disabled></td>
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
											<jsp:param name="ShowingCurrent"	value="xxPageShowCurNum" />
											<jsp:param name="ShowingTotal"	value="xxPageShowTotNum" />
											<jsp:param name="ViewMode"	value="FULL" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td valign="Top" width="1007">
								<div style="float:left"> <!-- right table -->
									<div id='rightTblHead' style="width:980px; overflow-x:none; overflow-y:none; ">
										<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD">
											<col align="center" width="150">
											<col align="center" width="520">
											<col align="center" width="300">
											<tr height="24">
												<td id="AH0" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustAcctCd_A' )">Customer Number<img id="sortIMG.billToCustAcctCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustAcctNm_A' )">Customer Name<img id="sortIMG.billToCustAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'locNum_A' )">Location<img id="sortIMG.locNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											</tr>
										</table>
									</div><!-- rightTblHead -->
									<div id="rightTbl" style="width:997px; height:362px; word-break:break-all; overflow-y:scroll; onscroll="synchroBottomScroll();">
										<table style="table-layout:fixed;"  border="1" cellpadding="0" cellspacing="0" id="A">
											<col align="left" width="150">
											<col align="left" width="520">
											<col align="left" width="300">
											<ezf:row ezfHyo="A">
											<tr height="24">
												<td><ezf:anchor name="SelectAcct" ezfName="SelectAcct" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectAcct" otherAttr=" ezfanchortext=\"\" id=\"billToCustAcctCd_A#{EZF_ROW_NUMBER}\"">
													<ezf:label name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
												</td>
												<td><ezf:inputText name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"70\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											</tr>
											</ezf:row>
											<ezf:skip>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											<tr height="24">
												<td><a href="#" name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A" ezfanchortext onclick="sendServer('SelectAcct')" id="billToCustAcctCd_A#{EZF_ROW_NUMBER}">
													<label ezfout name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" ezfHyo="A">1234567890</label></a>
												</td>
												<td><input type="text" name="billToCustAcctNm_A" ezfName="billToCustAcctNm_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="70" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
												<td><input type="text" name="locNum_A" ezfName="locNum_A" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="40" readonly style="border:none;background-color:transparent;padding:0px;"/></td>
											</tr>
											</ezf:skip>
										</table>
									</div><!-- rightTbl -->
								</div>
							</td>
						</tr>
					</table>
<%-- #################################################### Quick View Area ################################################### --%>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- ###SCRIPT --%>
<script type="text/javascript">
	function synchroBottomScroll() {
		var bottomTBL = document.getElementById( 'rightTbl' );
		var topTBL    = document.getElementById( 'rightTblHead'    );
		topTBL.scrollLeft = bottomTBL.scrollLeft;
	}
</script>

<%-- **** Task specific area ends here **** --%>
