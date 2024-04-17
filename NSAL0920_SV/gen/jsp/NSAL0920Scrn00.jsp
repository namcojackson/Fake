<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220812155325 --%>
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
			<input type="hidden" name="pageID" value="NSAL0920Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract Billing Search">
<center>
	<table cellSpacing="0" cellPadding="0" width="100%" border="0">
		<tr>
			<td>
				<%-- ********************** --%>
				<%-- *** Upper Tab Area *** --%>
				<%-- ********************** --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
					<tr>
						<td width="1070px" height="28px" valign="bottom">
							<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
								<tr title="Memo Entry Screen">
									<td width="107px" align="center" class="same">Report Group</td>
								</tr>
							</table>
						</td>
						<td width="10px" valign="bottom" align="center">
							<a href="#" id="PrevPageTabIndex"><img id="PrevPageBtn" src="./img/tab/tabbackbutton.png" alt="Prev" border="0" onclick="prevTabPage()"></a>
						</td>
						<td width="10px" valign="bottom" align="center">
							<a href="#" id="NextPageTabIndex"><img id="NextPageBtn" src="./img/tab/tabnextbutton.png" alt="Next" border="0" onclick="nextTabPage()"></a>
						</td>
					</tr>
				</table>
				</ezf:skip>
				<div class="pTab_BODY">
<!-- ################################################## Search Criteria - [START] ################################################## -->
					<table style="margin-top: 0px; margin-left: 5px" border="0">
						<tr>
							<td>
								<table cellSpacing="0" cellPadding="1" border="0">
									<colgroup>
										<col width="200px">
										<col width="200px">
										<col width="5px">
										<col width="200px">
										<col width="400px">
									</colgroup>
									<tbody>
										<tr>
											<td class="stab"><Div Align="right"><ezf:anchor name="dsContrNum_AR" ezfName="dsContrNum_AR" ezfEmulateBtn="1" ezfGuard="OpenWin_Contract" >Contract#(*)</ezf:anchor></Div></td>
											<td>
												<ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="9999999" otherAttr=" size=\"30\" maxlength=\"30\""/>
											</td>
											<td></td>
											<td class="stab"><Div Align="right">Region</Div></td>
											<td>
												<ezf:select name="svcRgPk_H3" ezfName="svcRgPk_H3" ezfBlank="1" ezfCodeName="svcRgPk_H1" ezfDispName="svcRgDescTxt_H2" />
											</td>
										</tr>
										<tr>
											<td class="stab"><Div Align="right"><ezf:anchor name="dsAcctNum_AR" ezfName="dsAcctNum_AR" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctCust" >Customer#(*)</ezf:anchor></Div></td>
											<td>
												<ezf:inputText name="dsAcctNum" ezfName="dsAcctNum" value="9999999" otherAttr=" size=\"30\" maxlength=\"30\""/>
											</td>
											<td></td>
											<td class="stab"><Div Align="right">
												<ezf:anchor name="svcContrBrCd_LK" ezfName="svcContrBrCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Branch" otherAttr=" id=\"svcContrBrCd_LK\" ezfanchortext=\"\"">Branch</ezf:anchor></td>
											<td><ezf:inputText name="svcContrBrCd_H3" ezfName="svcContrBrCd_H3" value="XX3" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/><ezf:inputText name="svcContrBrDescTxt_H2" ezfName="svcContrBrDescTxt_H2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"21\" maxlength=\"50\""/></td>
										</tr>
										<tr>
											<td class="stab"><Div Align="right"><ezf:anchor name="dsAcctNm_AR" ezfName="dsAcctNm_AR" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctCustNm" >Customer Name(*)</ezf:anchor></Div></td>
											<td>
												<ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="9999999" otherAttr=" size=\"30\" maxlength=\"30\""/>
											</td>
											<td></td>
											<td class="stab"><Div Align="right">
												<ezf:anchor name="psnCd_LK" ezfName="psnCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Supv" otherAttr=" id=\"psnCd_LK\" ezfanchortext=\"\"">Supervisor</ezf:anchor></td>
											<td><ezf:inputText name="psnCd_H3" ezfName="psnCd_H3" value="XXXXXXX8" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/><ezf:inputText name="xxAllPsnNm_H2" ezfName="xxAllPsnNm_H2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3X" otherAttr=" size=\"30\" maxlength=\"61\""/></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table cellSpacing="0" cellPadding="1" border="0">
									<colgroup>
										<col width="200px">
										<col width="200px">
										<col width="5px">
										<col width="200px">
										<col width="5px">
										<col width="10px">
										<col width="120px">
										<col width="10px">
										<col width="10px">
										<col width="120px">
										<col width="5px">
										<col width="150px">
									</colgroup>
									<tbody>
										<tr>
											<td class="stab"><Div Align="right"><ezf:anchor name="locNum_AR" ezfName="locNum_AR" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToLoc" >Bill To Code(*)</ezf:anchor></Div></td>
											<td>
												<ezf:inputText name="locNum" ezfName="locNum" value="9999999" otherAttr=" size=\"30\" maxlength=\"30\""/>
											</td>
											<td></td>
											<td class="stab"><Div Align="right">Interface Date</Div></td>
											<td></td>
											<td class="stab">From</td>
											<td>
												<ezf:inputText name="xxFromDt" ezfName="xxFromDt" value="99/99/9999" otherAttr=" size=\"12\" maxlength=\"10\""/>
												<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt', 4);">
											</td>
											<td class="stab">-</td>
											<td class="stab">To</td>
											<td>
												<ezf:inputText name="xxThruDt" ezfName="xxThruDt" value="99/99/9999" otherAttr=" size=\"12\" maxlength=\"10\""/>
												<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxThruDt', 4);">
											</td>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table cellSpacing="0" cellPadding="1" border="0">
									<colgroup>
										<col width="200px">
										<col width="200px">
										<col width="5px">
										<col width="200px">
										<col width="10px">
										<col width="10px">
										<col width="10px">
										<col width="5px">
										<col width="10px">
										<col width="10px">
										<col width="120px">
										<col width="100px">
										<col width="160px">
									</colgroup>
									<tbody>
										<tr>
											<td class="stab"><Div Align="right"><ezf:anchor name="locNm_AR" ezfName="locNm_AR" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToLocNm" >Bill To Location Name(*)</ezf:anchor></Div></td>
											<td>
												<ezf:inputText name="locNm" ezfName="locNm" value="9999999" otherAttr=" size=\"30\" maxlength=\"30\""/>
											</td>
											<td></td>
											<td class="stab"><Div Align="right">Billing Approval Request</Div></td>
											<td></td>
											<td class="stab"><Div Align="right">Yes</Div></td>
											<td><ezf:inputCheckBox name="xxChkBox_HY" ezfName="xxChkBox_HY" value="Y" /></td>
											<td></td>
											<td class="stab"><Div Align="right">No</Div></td>
											<td><ezf:inputCheckBox name="xxChkBox_HN" ezfName="xxChkBox_HN" value="Y" /></td>
											<td>
											<td></td>
											<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn4" /></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
<!-- ################################################## Search Criteria - [E N D] ################################################## -->
					<hr />
<!-- ################################################## Search Result   - [START] ################################################## -->
<%@ page import="business.servlet.NSAL0920.NSAL0920BMsg" %>
<%@ page import="business.servlet.NSAL0920.NSAL0920_ABMsg" %>
<%  NSAL0920BMsg bMsg = (NSAL0920BMsg)databean.getEZDBMsg(); %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG" %>
					<div align="center">
					<table style="margin-top: 0px; margin-left: 5px" border="0" width="800px">
						<tr>
							<td>
								<table border="0" width="100%">
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
												<col width="200">
												<col width="40">
												<col width="218">
												<col width="80">
												<col width="560">
											<ezf:skip>
											</ezf:skip>

											<tr height='25px'>
												<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td align="right" class="stab">
													<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
														<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
														<jsp:param name="TableNm"           value="A" />
														<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
														<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
														<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
														<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
														<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
														<jsp:param name="ViewMode"          value="FULL" />
													</jsp:include>
												</td>
											</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:350px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1082px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1440px" style="margin-right:20px">
												<colgroup>
													<col align="center" width="80px"><!-- Interface Date -->
													<col align="center" width="80px"><!-- Branch -->
													<col align="center" width="80px"><!-- Contract# -->
													<col align="center" width="80px"><!-- Type -->
													<col align="center" width="120px"><!-- Status -->
													<col align="center" width="120px"><!-- Machine -->
													<col align="center" width="120px"><!-- Located Party -->
													<col align="center" width="120px"><!-- Counter Name -->
													<col align="center" width="80px"><!-- Period Start -->
													<col align="center" width="80px"><!-- Period End -->
													<col align="center" width="80px"><!-- Allowance -->
													<col align="center" width="80px"><!-- Rate -->
													<col align="center" width="120px"><!-- Start Read -->
													<col align="center" width="120px"><!-- End Read -->
													<col align="center" width="80px"><!-- Amount -->
													<col align="center" width="80px"><!-- Notification -->
												</colgroup>
												<tbody>
													<tr align="center" height='23px'>
														<td id="AH0" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'ovrdNextBllgDt_A')">Interface Date<img id="sortIMG.ovrdNextBllgDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcContrBrDescTxt_A')">Branch<img id="sortIMG.svcContrBrDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')">Contract#<img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrCatgDescTxt_A')">Type<img id="sortIMG.dsContrCatgDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrCtrlStsNm_A')">Status<img id="sortIMG.dsContrCtrlStsNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')">Machine<img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'locNm_A')">Located Party<img id="sortIMG.locNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrLbDescTxt_A')">Counter Name<img id="sortIMG.mtrLbDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrBllgFromDt_A')">Period Start<img id="sortIMG.mtrBllgFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrBllgThruDt_A')">Period End<img id="sortIMG.mtrBllgThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xsMtrFromCopyQty_A')">Allowance<img id="sortIMG.xsMtrFromCopyQty_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xsMtrAmtRate_A')">Rate<img id="sortIMG.xsMtrAmtRate_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'readMtrCnt_AS')">Start Read<img id="sortIMG.readMtrCnt_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'readMtrCnt_AE')">End Read<img id="sortIMG.readMtrCnt_AE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrChrgDealAmt_A')">Amount<img id="sortIMG.mtrChrgDealAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxYesNoNm_A')">Notification<img id="sortIMG.xxYesNoNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
												</tbody>
											</table>
										</div>

										<div id="rightTbl" style="width:1099px; height:350px; display:block; overflow:scroll; margin:0px; padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1457px">
												<colgroup>
													<col align="center" width="80px"><!-- Interface Date -->
													<col align="center" width="80px"><!-- Branch -->
													<col align="center" width="80px"><!-- Contract# -->
													<col align="center" width="80px"><!-- Type -->
													<col align="center" width="120px"><!-- Status -->
													<col align="center" width="120px"><!-- Machine -->
													<col align="center" width="120px"><!-- Located Party -->
													<col align="center" width="120px"><!-- Counter Name -->
													<col align="center" width="80px"><!-- Period Start -->
													<col align="center" width="80px"><!-- Period End -->
													<col align="center" width="80px"><!-- Allowance -->
													<col align="center" width="80px"><!-- Rate -->
													<col align="center" width="120px"><!-- Start Read -->
													<col align="center" width="120px"><!-- End Read -->
													<col align="center" width="80px"><!-- Amount -->
													<col align="center" width="80px"><!-- Notification -->
												</colgroup>
												<tbody>
												<% int i = 0; %>
													<ezf:row ezfHyo="A">
														<% NSAL0920_ABMsg abMsg = bMsg.A.no(i++); %>
														<% boolean isDplyCtrlFlg1 = DS_CONTR_CATG.FLEET.equals(abMsg.dsContrCatgCd_A.getValue()); %>
														<% boolean isDplyCtrlFlg2 = "YES".equals(abMsg.xxYesNoNm_A.getValue()); %>
														<tr align="center" height='23px' id="A_rightTBLRow#{EZF_ROW_NUMBER}">
															<td><ezf:inputText name="ovrdNextBllgDt_A" ezfName="ovrdNextBllgDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"ovrdNextBllgDt_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="svcContrBrDescTxt_A" ezfName="svcContrBrDescTxt_A" value="---------1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" id=\"svcContrBrDescTxt_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="dsContrNum_A" ezfName="dsContrNum_A" value="---------1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" id=\"dsContrNum_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="dsContrCatgDescTxt_A" ezfName="dsContrCatgDescTxt_A" value="---------1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" id=\"dsContrCatgDescTxt_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="dsContrCtrlStsNm_A" ezfName="dsContrCtrlStsNm_A" value="---------1----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"dsContrCtrlStsNm_A#{EZF_ROW_NUMBER}\""/></td>
															<td align="center">
																<% if(isDplyCtrlFlg1) { %>
																	<ezf:anchor name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_Mach" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
																		<ezf:label name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" />
																	</ezf:anchor>
																<% } else { %>
																	<ezf:label name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" />
																<% } %>
															</td>
															<td><ezf:inputText name="locNm_A" ezfName="locNm_A" value="---------1----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" id=\"locNm_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="mtrLbDescTxt_A" ezfName="mtrLbDescTxt_A" value="---------1----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"mtrLbDescTxt_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="mtrBllgFromDt_A" ezfName="mtrBllgFromDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"mtrBllgFromDt_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="mtrBllgThruDt_A" ezfName="mtrBllgThruDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"mtrBllgThruDt_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="xsMtrFromCopyQty_A" ezfName="xsMtrFromCopyQty_A" value="12,345,678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"13\""/></td>
															<td><ezf:inputText name="xsMtrAmtRate_A" ezfName="xsMtrAmtRate_A" value="123.123456" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"26\""/></td>
															<td><ezf:inputText name="readMtrCnt_AS" ezfName="readMtrCnt_AS" value="12,345,678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"13\" id=\"readMtrCnt_AS#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="readMtrCnt_AE" ezfName="readMtrCnt_AE" value="12,345,678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"13\" id=\"readMtrCnt_AE#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="mtrChrgDealAmt_A" ezfName="mtrChrgDealAmt_A" value="345,678.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"22\" id=\"mtrChrgDealAmt_A#{EZF_ROW_NUMBER}\""/></td>
															<td align="center">
																<% if(isDplyCtrlFlg2) { %>
																	<ezf:anchor name="xxYesNoNm_A" ezfName="xxYesNoNm_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_PrvBllgWFDtl" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
																		<ezf:label name="xxYesNoNm_A" ezfName="xxYesNoNm_A" ezfHyo="A" ezfArrayNo="0" />
																	</ezf:anchor>
																<% } else { %>
																	<ezf:label name="xxYesNoNm_A" ezfName="xxYesNoNm_A" ezfHyo="A" ezfArrayNo="0" />
																<% } %>
															</td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
																<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</tbody>
											</table>
										</div>
									</div> <!-- right table -->
								</div> <!--parent box -->
							</td>
						</tr>
					</table>
					</div>
<!-- ################################################## Search Result   - [E N D] ################################################## -->
				</div>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true);
</script>

<%-- **** Task specific area ends here **** --%>
