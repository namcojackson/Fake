<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20191203170526 --%>
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
			<input type="hidden" name="pageID" value="NWCL0050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Invoice Search">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- ########## Menu ########## --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%">
									<li title = "Invoice Search" class="pTab_ON" ><a href="#">InvBill Srch</a></li>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<%-- ########## Header ########## --%>
				<div class="pTab_BODY">
					<table border="0" width="99%" align="center">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="105">
									<col width="255">
									<col width="110">
									<col width="255">
									<col width="120">
									<col width="255">
									<tr height="27">
										<td class="stab">Invoice Number (*)</td>
										<td><ezf:inputText name="invNum" ezfName="invNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"34\" maxlength=\"13\" ezftoupper=\"\""/></td>
										<td class="stab">Order Number (*)</td>
										<td><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"34\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td class="stab">Bill Number (*)</td>
										<td><ezf:inputText name="conslBillNum" ezfName="conslBillNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"34\" maxlength=\"20\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="27">
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctSearch" otherAttr=" ezfanchortext=\"\"">Account Number</ezf:anchor> (*)</td>
										<td><ezf:inputText name="billToDsAcctNum" ezfName="billToDsAcctNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"34\" maxlength=\"28\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctSearch" otherAttr=" ezfanchortext=\"\"">Customer Name</ezf:anchor> (*)</td>
										<td><ezf:inputText name="billToDsAcctNm" ezfName="billToDsAcctNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"34\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctSearch" otherAttr=" ezfanchortext=\"\"">Bill To Location</ezf:anchor> (*)</td>
										<td><ezf:inputText name="billToLocNum" ezfName="billToLocNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"34\" maxlength=\"60\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="27">
										<td class="stab">Serial Number (*)</td>
										<td><ezf:inputText name="xxSerNumSrchTxt" ezfName="xxSerNumSrchTxt" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"34\" maxlength=\"70\" ezftoupper=\"\""/></td>
										<td class="stab">Contract Number (*)</td>
										<td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"34\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td class="stab">Aggregate Contract# (*)</td>
										<td><ezf:inputText name="invAvgGrpNum" ezfName="invAvgGrpNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"34\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="27">
										<td class="stab">URN Number (*)</td>
										<td><ezf:inputText name="xxUrnNum" ezfName="xxUrnNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"34\" maxlength=\"200\" ezftoupper=\"\""/></td>
										<td class="stab">Batch Name</td>
										<td>
											<ezf:select name="invPrtBatTpCd" ezfName="invPrtBatTpCd" ezfBlank="1" ezfCodeName="invPrtBatTpCd_PL" ezfDispName="invPrtBatTpDescTxt_PL" otherAttr=" style=\"width:244px;\""/>
										</td>
										<td class="stab">Print Branch Name</td>
										<td>
											<ezf:select name="invPrtBrCd" ezfName="invPrtBrCd" ezfBlank="1" ezfCodeName="invPrtBrCd_PL" ezfDispName="invPrtBrDescTxt_PL" otherAttr=" style=\"width:244px;\""/>
										</td>
									</tr>
									<tr height="27">
										<td class="stab">Category</td>
										<td>
											<ezf:select name="invProcTpCd" ezfName="invProcTpCd" ezfBlank="1" ezfCodeName="invProcTpCd_PL" ezfDispName="invProcTpDescTxt_PL" otherAttr=" style=\"width:244px;\""/>
										</td>
										<td class="stab">Invoice Source</td>
										<td>
											<ezf:select name="ordClsCd" ezfName="ordClsCd" ezfBlank="1" ezfCodeName="ordClsCd_PL" ezfDispName="ordClsDescTxt_PL" otherAttr=" style=\"width:244px;\""/>
										</td>
										<td class="stab">Product Type</td>
										<td>
											<ezf:select name="invSmryLineTpCd" ezfName="invSmryLineTpCd" ezfBlank="1" ezfCodeName="invSmryLineTpCd_PL" ezfDispName="invSmryLineTpDescTxt_PL" otherAttr=" style=\"width:244px;\""/>
										</td>
									</tr>
									<tr height="27">
										<td class="stab">Bill Date From</td>
										<td><ezf:inputText name="conslBillInvDt_FR" ezfName="conslBillInvDt_FR" value="MM/DD/YYYY" otherAttr=" maxlength=\"10\" size=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('conslBillInvDt_FR', 4);"></td>
										<td class="stab">Bill Date To</td>
										<td><ezf:inputText name="conslBillInvDt_TO" ezfName="conslBillInvDt_TO" value="MM/DD/YYYY" otherAttr=" maxlength=\"10\" size=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('conslBillInvDt_TO', 4);"></td>
										<td class="stab">Customer PO#(*)</td>
										<td><ezf:inputText name="custIssPoNum" ezfName="custIssPoNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" otherAttr=" size=\"34\" maxlength=\"35\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="27">
										<td class="stab">Creation Date From</td>
										<td><ezf:inputText name="xxCratDt_FR" ezfName="xxCratDt_FR" value="MM/DD/YYYY" otherAttr=" maxlength=\"10\" size=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxCratDt_FR', 4);"></td>
										<td class="stab">Creation Date To</td>
										<td><ezf:inputText name="xxCratDt_TO" ezfName="xxCratDt_TO" value="MM/DD/YYYY" otherAttr=" maxlength=\"10\" size=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxCratDt_TO', 4);"></td>
										<td class="stab">&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="27">
										<td class="stab">Records Per Page</td>
										<td>
											<ezf:select name="keyInfoCd" ezfName="keyInfoCd" ezfCodeName="keyInfoCd_PL" ezfDispName="xxRptTpTxt_PL" />
										</td>
										<td class="stab">Show Bills Only</td>
										<td><ezf:inputCheckBox name="xxChkBox_SB" ezfName="xxChkBox_SB" value="Y" /></td>
										<td class="stab">Show Printed Invoice Only</td>
										<td>
										  <table border="0" width="100%">
										  <tr>
										    <td><ezf:inputCheckBox name="xxChkBox_SP" ezfName="xxChkBox_SP" value="Y" /></td>
											<td align="right"><ezf:inputButton name="Search_Invoice" value="Search" htmlClass="pBtn7" />&nbsp;</td>
										  </tr>
										  </table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<div style="border-bottom:solid 1px #AAAAAA;padding-top:1px;margin-bottom:0;"></div>
					<br>

					<%-- ########## Detail ########## --%>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%@ page import="business.servlet.NWCL0050.NWCL0050BMsg" %>
<%@ page import="business.servlet.NWCL0050.NWCL0050_ABMsg" %>
<%  NWCL0050BMsg bMsg = (NWCL0050BMsg)databean.getEZDBMsg(); %>
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>&nbsp;</td>
							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="725">
									<col align="right">
									<tr>
										<td>&nbsp;</td>
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"			value='<%= request.getParameter("beanId") %>' />
												<jsp:param name="TableNm"			value="A" />
												<jsp:param name="ShowingFrom"		value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"			value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"			value="xxPageShowOfNum" />
											</jsp:include>
										</td>
									</tr>
								</table>
								<ezf:skip>
									<table border="0" cellpadding="1" cellspacing="0">
										<col width="725">
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
											<td></td>
											<td class="stab">Showing</td>
											<td class="pOut">99999</td>
											<td class="stab">to</td>
											<td class="pOut">99999</td>
											<td class="stab">of</td>
											<td class="pOut">99999</td>
											<td>&nbsp;</td>
											<td><input type="button" class="pBtn3" value="Prev" name="PagePrev"></td>
											<td></td>
											<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
										</tr>
									</table>
								</ezf:skip>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<div id="LeftTBL_Top" style="overflow-x:hidden; verflow-y:hidden;">
									<table border="1" cellpadding="0" cellspacing="0" width="25">
										<col align="center" width="25">			<!-- Select -->
										<tr height="26">
											<td class="pClothBs"><label>&nbsp;</label></td>
										</tr>
									</table>
								</div>
								<div id="LeftTBL"  style="overflow-x:hidden; overflow-y:hidden; height:221; width:25;"  onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
									<table border="1" cellpadding="0" cellspacing="0" id="A" style="table-layout: fixed">
										<col width="25" align="center">		<!-- Select -->
										<ezf:row ezfHyo="A">
										<tr id="id_row{EZF_ROW_NUMBER}" height="24">
											<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
										</tr>
										</ezf:row>
										<ezf:skip>
										<tr height="24">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="24">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="24">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="24">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="24">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="24">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="24">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="24">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="24">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="24">
											<td><input type="checkbox"></td>
										</tr>
										<tr height="24">
											<td><input type="checkbox"></td>
										</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
							<td>
								<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:1067" onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ) );">
									<table style="width:1075px;table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col align="center" width="65">		<!-- Category -->
										<col align="center" width="100">	<!-- URN# -->
										<col align="center" width="95">		<!-- Bill# -->
										<col align="center" width="105">	<!-- Invoice# -->
										<col align="center" width="110">	<!-- Account# -->
										<col align="center" width="200">	<!-- Customer Name -->
										<col align="center" width="95">		<!-- Customer PO# -->
										<col align="center" width="85">		<!-- Invoice Date -->
										<col align="center" width="120">	<!-- Creation Date -->
										<col align="center" width="90">		<!-- Amount -->
										<col align="center" width="95">		<!-- Orig. Bill# -->
										<tr height="26">
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'invProcTpCd_A')" id="tHead.Order">Category</a>
												<img id="sortIMG.invProcTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'xxUrnNum_A')" id="tHead.Order">URN Number</a>
												<img id="sortIMG.xxUrnNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'conslBillNum_A')" id="tHead.Order">Bill Number</a>
												<img id="sortIMG.conslBillNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'invNum_A')" id="tHead.Order">Invoice Number</a>
												<img id="sortIMG.invNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'billToDsAcctNum_A')" id="tHead.Order">Account Number</a>
												<img id="sortIMG.billToDsAcctNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'billToDsAcctNm_A')" id="tHead.Order">Customer Name</a>
												<img id="sortIMG.billToDsAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'custIssPoNum_A')" id="tHead.Order">Customer PO#</a>
												<img id="sortIMG.custIssPoNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'conslBillInvDt_A')" id="tHead.Order">Invoice Date</a>
												<img id="sortIMG.conslBillInvDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'xxDtTm_A')" id="tHead.Order">Creation Date</a>
												<img id="sortIMG.xxDtTm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'conslBillTotAmt_A')" id="tHead.Order">Amount</a>
												<img id="sortIMG.conslBillTotAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort('A', 'origConslBillNum_A')" id="tHead.Order">Original Bill#</a>
												<img id="sortIMG.origConslBillNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
											</td>
										</tr>
									</table>
								</div>
								<div id="RightTBL" style=" overflow-x:scroll; overflow-y:scroll;  height:235; width:1084" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ) );">
									<table id="A_Right" style="width:810px;table-layout:fixed;font-size:6" border="1" cellpadding="1" cellspacing="0">
										<col width="65">				<!-- Category -->
										<col width="100">				<!-- URN# -->
										<col width="95">				<!-- Bill# -->
										<col width="105">				<!-- Invoice# -->
										<col width="110">				<!-- Account# -->
										<col width="200">				<!-- Customer Name -->
										<col width="95">				<!-- Customer PO# -->
										<col width="85">				<!-- Invoice Date -->
										<col width="120">				<!-- Creation Date -->
										<col width="90" align="right">	<!-- Amount -->
										<col width="95">				<!-- Orig. Bill# -->
										<% int i = 0; %>
										<ezf:row ezfHyo="A">
											<% NWCL0050_ABMsg abMsg = bMsg.A.no(i++); %>
											<% boolean linkUrlFlg_AU = ZYPCommonFunc.hasValue(abMsg.invFileUrl_AU); %>
											<% boolean linkUrlFlg_AB = ZYPCommonFunc.hasValue(abMsg.invFileUrl_AB); %>
											<% boolean linkUrlFlg_AI = ZYPCommonFunc.hasValue(abMsg.invFileUrl_AI); %>
											<% boolean billNumFlg = ZYPCommonFunc.hasValue(abMsg.conslBillNum_A); %>
											<% boolean invNumFlg = ZYPCommonFunc.hasValue(abMsg.invNum_A); %>
											<% boolean rqstFlg = ZYPCommonFunc.hasValue(abMsg.eipRptRqstPk_A); %>
											<% boolean billRqstFlg = ZYPCommonFunc.hasValue(abMsg.eipRptRqstPk_BL); %>
											<tr height="24">
												<td><ezf:label name="invProcTpCd_A" ezfName="invProcTpCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td>
													<ezf:anchor name="invFileUrl_AU" ezfName="invFileUrl_AU" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_FileDownload', '{EZF_ROW_NUMBER}" otherAttr=" ezfanchortext=\"\"">
														<ezf:label name="xxUrnNum_A" ezfName="xxUrnNum_A" ezfHyo="A" ezfArrayNo="0" />
													</ezf:anchor>
												</td>
												<td>
													<% if(linkUrlFlg_AB || !linkUrlFlg_AB && invNumFlg) { %>
														<% if (billRqstFlg) {%>
													<ezf:anchor name="invFileUrl_AB1" ezfName="invFileUrl_AB" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_EIPPrintBill', '{EZF_ROW_NUMBER}" >
														<ezf:label name="conslBillNum_A1" ezfName="conslBillNum_A" ezfHyo="A" ezfArrayNo="0" />
													</ezf:anchor>
														<% } else {%>
														<ezf:label name="conslBillNum_A3" ezfName="conslBillNum_A" ezfHyo="A" ezfArrayNo="0" />
														<% } %>
													<% } else { %>
														<% if (billRqstFlg) {%>
													<ezf:anchor name="invFileUrl_AB2" ezfName="invFileUrl_AB" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_EIPPrintBill', '{EZF_ROW_NUMBER}" >
														<ezf:label name="conslBillNum_A2" ezfName="conslBillNum_A" ezfHyo="A" ezfArrayNo="0" />
													</ezf:anchor>
														<% } else {%>
														<ezf:label name="conslBillNum_A4" ezfName="conslBillNum_A" ezfHyo="A" ezfArrayNo="0" />
														<% } %>
													<% } %>
												</td>
												<td>
													<% if(linkUrlFlg_AI || !linkUrlFlg_AI && !invNumFlg ) { %>
													<ezf:anchor name="invFileUrl_AI1" ezfName="invFileUrl_AI" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_FileDownload', '{EZF_ROW_NUMBER}" otherAttr=" ezfanchortext=\"\"">
														<ezf:label name="invNum_A1" ezfName="invNum_A" ezfHyo="A" ezfArrayNo="0" />
													</ezf:anchor>
													<% } else { %>
														<% if (rqstFlg) {%>
													<ezf:anchor name="invFileUrl_AI2" ezfName="invFileUrl_AI" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_EIPPrint', '{EZF_ROW_NUMBER}" >
														<ezf:label name="invNum_A2" ezfName="invNum_A" ezfHyo="A" ezfArrayNo="0" />
													</ezf:anchor>
														<% } else {%>
														<ezf:label name="invNum_A3" ezfName="invNum_A" ezfHyo="A" ezfArrayNo="0" />
														<% } %>
													<% } %>
												</td>
												<td><ezf:label name="billToDsAcctNum_A" ezfName="billToDsAcctNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="billToDsAcctNm_A" ezfName="billToDsAcctNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="custIssPoNum_A" ezfName="custIssPoNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="conslBillInvDt_A" ezfName="conslBillInvDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxDtTm_A" ezfName="xxDtTm_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="conslBillTotAmt_A" ezfName="conslBillTotAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="origConslBillNum_A" ezfName="origConslBillNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="24">
												<td><label>NCB</label></td><td><label>NA</label></td><td><label>NA</label></td><td><label>4008072808</label></td>
												<td><label>1290147</label></td><td><label>YWT LLC</label></td><td><label>9999999999</label></td><td><label>01/01/2015</label></td><td><label>01/01/2015 15:30</label></td><td><label>5215.50</label></td><td><label>NA</label></td>
											</tr>
											<tr height="24">
												<td><label>NCB</label></td><td><label>NA</label></td><td><label>NA</label></td><td><label>4008072808</label></td>
												<td><label>1290147</label></td><td><label>YWT LLC</label></td><td><label>9999999999</label></td><td><label>01/01/2015</label></td><td><label>01/01/2015 15:30</label></td><td><label>5215.50</label></td><td><label>NA</label></td>
											</tr>
											<tr height="24">
												<td><label>NCB</label></td><td><label>NA</label></td><td><label>NA</label></td><td><label>4008072808</label></td>
												<td><label>1290147</label></td><td><label>YWT LLC</label></td><td><label>9999999999</label></td><td><label>01/01/2015</label></td><td><label>01/01/2015 15:30</label></td><td><label>5215.50</label></td><td><label>NA</label></td>
											</tr>
											<tr height="24">
												<td><label>NCB</label></td><td><label>NA</label></td><td><label>NA</label></td><td><label>4008072808</label></td>
												<td><label>1290147</label></td><td><label>YWT LLC</label></td><td><label>9999999999</label></td><td><label>01/01/2015</label></td><td><label>01/01/2015 15:30</label></td><td><label>5215.50</label></td><td><label>NA</label></td>
											</tr>
											<tr height="24">
												<td><label>NCB</label></td><td><label>NA</label></td><td><label>NA</label></td><td><label>4008072808</label></td>
												<td><label>1290147</label></td><td><label>YWT LLC</label></td><td><label>9999999999</label></td><td><label>01/01/2015</label></td><td><label>01/01/2015 15:30</label></td><td><label>5215.50</label></td><td><label>NA</label></td>
											</tr>
											<tr height="24">
												<td><label>NCB</label></td><td><label>NA</label></td><td><label>NA</label></td><td><label>4008072808</label></td>
												<td><label>1290147</label></td><td><label>YWT LLC</label></td><td><label>9999999999</label></td><td><label>01/01/2015</label></td><td><label>01/01/2015 15:30</label></td><td><label>5215.50</label></td><td><label>NA</label></td>
											</tr>
											<tr height="24">
												<td><label>NCB</label></td><td><label>NA</label></td><td><label>NA</label></td><td><label>4008072808</label></td>
												<td><label>1290147</label></td><td><label>YWT LLC</label></td><td><label>9999999999</label></td><td><label>01/01/2015</label></td><td><label>01/01/2015 15:30</label></td><td><label>5215.50</label></td><td><label>NA</label></td>
											</tr>
											<tr height="24">
												<td><label>NCB</label></td><td><label>NA</label></td><td><label>NA</label></td><td><label>4008072808</label></td>
												<td><label>1290147</label></td><td><label>YWT LLC</label></td><td><label>9999999999</label></td><td><label>01/01/2015</label></td><td><label>01/01/2015 15:30</label></td><td><label>5215.50</label></td><td><label>NA</label></td>
											</tr>
											<tr height="24">
												<td><label>NCB</label></td><td><label>NA</label></td><td><label>NA</label></td><td><label>4008072808</label></td>
												<td><label>1290147</label></td><td><label>YWT LLC</label></td><td><label>9999999999</label></td><td><label>01/01/2015</label></td><td><label>01/01/2015 15:30</label></td><td><label>5215.50</label></td><td><label>NA</label></td>
											</tr>
											<tr height="24">
												<td><label>NCB</label></td><td><label>NA</label></td><td><label>NA</label></td><td><label>4008072808</label></td>
												<td><label>1290147</label></td><td><label>YWT LLC</label></td><td><label>9999999999</label></td><td><label>01/01/2015</label></td><td><label>01/01/2015 15:30</label></td><td><label>5215.50</label></td><td><label>NA</label></td>
											</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<br>
					<table border="0" style="table-layout:fixed;margin:0px 25px 0px 0px;">
						<tr align="right">
							<td><ezf:inputButton name="OpenWin_ManualInv" value="Display" htmlClass="pBtn7" />
							<ezf:inputButton name="OpenWin_Payment" value="Pay" htmlClass="pBtn7" />
							<ezf:inputButton name="OpenWin_MailEntry" value="EMail" htmlClass="pBtn7" /></td>
						</tr>
					</table>

				</div>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript">
	function clickLink(prntObj, idx) {
		var eventNm = prntObj.all(0).value;
		sendServer(eventNm, idx);
	}
</script>


<%-- **** Task specific area ends here **** --%>
