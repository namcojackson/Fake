<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190218120000 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- I18N START --%>
<%@ page import="parts.i18n.*" %>

<%	pageContext.setAttribute("ezdi18nlocale", EZDI18NContext.getInstance().getI18NAccessor().getLocale()); %>
<fmt:setLocale value="${ezdi18nlocale}" scope="request" />
<fmt:setBundle basename="I18N_NSAL0410Scrn00" var="I18N_SCREEN_ID" scope="request" />
<fmt:setBundle basename="I18N" var="I18N_DEFAULT" scope="request" />
<%-- I18N END --%>

<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NSAL0410Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0410Scrn00.title" bundle="${I18N_SCREEN_ID}">Additional Charge</fmt:message>">
<center>
	<table table bordercolor="blue" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
			<div class="pTab_BODY">
				<table>
				</table>
			<%-- ##### Conrract Header ##### --%>
				<table bordercolor="pink" border="0" cellpadding="1" cellspacing="0" width="100%">
					<col width="680"  align="left">
					<col width=""  align="center">
					<tr>
						<td>
							<fieldset>
							<legend><fmt:message key="i18n.NSAL0410Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Contract Header</fmt:message></legend>
								<table bordercolor="blue" border="0" cellpadding="1" cellspacing="0">
									<col width="110"  align="right">
									<col width="210" align="right">
									<col width="120" align="right">
									<col width="210" align="right">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0410Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Contract#</fmt:message></td>
										<td>
											<ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="WWWWWWWWW1WWWWWWWWW2WW" otherAttr=" size=\"28\""/>
										</td>
										<td class="stab"><fmt:message key="i18n.NSAL0410Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Status</fmt:message></td>
										<td>
											<ezf:inputText name="dsContrCtrlStsNm" ezfName="dsContrCtrlStsNm" value="WWWWWWWWW1WWWWWWWWW2WW" otherAttr=" size=\"28\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0410Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Branch</fmt:message></td>
										<td>
											<ezf:inputText name="svcContrBrDescTxt" ezfName="svcContrBrDescTxt" otherAttr=" size=\"28\""/>
										</td>
										<td class="stab"><fmt:message key="i18n.NSAL0410Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Short Description</fmt:message></td>
										<td>
											<ezf:inputText name="dsContrNm" ezfName="dsContrNm" value="WWWWWWWWW1WWWWWWWWW2WW" otherAttr=" size=\"28\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0410Scrn00.label.6" bundle="${I18N_SCREEN_ID}">End Customer Name</fmt:message></td>
										<td>
											<ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" otherAttr=" size=\"28\""/>
										</td>
										<td class="stab"><fmt:message key="i18n.NSAL0410Scrn00.label.7" bundle="${I18N_SCREEN_ID}">End Customer Number</fmt:message></td>
										<td>
											<ezf:inputText name="dsAcctNum" ezfName="dsAcctNum" otherAttr=" size=\"28\""/>
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
						<td>
						</td>
					</tr>
				</table>
			</div>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<%-- ##### Additional Charge #####--%>
		<tr>
			<td>
				<table bordercolor="green" border="0" cellpadding="1" cellspacing="0" width="100%">
					<col width="5"  align="center">
					<col width="450"  align="left">
					<tr>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td class="stab"><fmt:message key="i18n.NSAL0410Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Additional Billing Charge</fmt:message></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="0" cellpadding="1" cellspacing="0"  width="100%">
					<tr>
						<td valign="top">
							<%-- HEAD --%>
							<div id="topTBL" 
							     style="overflow-x:hidden; overflow-y:none; width:1096; word-break:break-all;">
								<table border="1" cellpadding="1" cellspacing="0" width="2220">
									<col align="center" width="40">
									<col align="center" width="100">
									<col align="center" width="120">
									<col align="center" width="50">
									<col align="center" width="160">
									<col align="center" width="250">
									<col align="center" width="100">
									<col align="center" width="100">
									<col align="center" width="100">
									<col align="center" width="180">
									<col align="center" width="130">
									<col align="center" width="150">
									<col align="center" width="70">
									<col align="center" width="130">
									<col align="center" width="60">
									<col align="center" width="130">
									<col align="center" width="90">
									<tr height="28">
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Del</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Contract#</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Machine<br>Master</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Service Program</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Charge Type</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Start</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.15" bundle="${I18N_SCREEN_ID}">End</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Termination<br>Date</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Frequency</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Bill By</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Flat Price</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Applicable%</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Invoice Type</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.22" bundle="${I18N_SCREEN_ID}">Print Detail<br>on Invoice</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.23" bundle="${I18N_SCREEN_ID}">Item</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0410Scrn00.label.24" bundle="${I18N_SCREEN_ID}">SLA Time</fmt:message></td>
									</tr>
								</table>
							</div>
							<%-- DETAIL --%>
							<div id="bottomTBL" 
							     style="overflow-x:scroll; overflow-y:scroll; width:1113; height:159; word-break:break-all;"
							     onScroll="synchroScroll(this.id, new Array('topTBL'));">
								<table border="1" cellpadding="1" cellspacing="0" width="2220" id="A">
									<col align="center" width="40">
									<col align="center" width="100">
									<col align="center" width="120">
									<col align="center" width="50">
									<col align="center" width="160">
									<col align="center" width="250">
									<col align="center" width="100">
									<col align="center" width="100">
									<col align="center" width="100">
									<col align="center" width="180">
									<col align="center" width="130">
									<col align="center" width="150">
									<col align="center" width="70">
									<col align="center" width="130">
									<col align="center" width="60">
									<col align="left" width="130">
									<col align="right" width="90">
									<ezf:row ezfHyo="A">
									<tr height="28">
										<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td>
											<ezf:inputText name="xxScrItem34Txt_A" ezfName="xxScrItem34Txt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<ezf:inputButton name="OnChange_SerNum" value="..." ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:17px;width:16px;font-family:'Arial',sans-serif;font-size:8pt;\""/>
										</td>
										<td>
											<ezf:label name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" ezfHyo="A" ezfArrayNo="0" />
										</td>
										<td>
											<ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\""/>
										</td>
										<td><ezf:select name="addlChrgTpCd_SE" ezfName="addlChrgTpCd_SE" ezfHyo="A" ezfArrayNo="0" ezfCodeName="addlChrgTpCd_CD" ezfDispName="addlChrgTpNm_DI" otherEvent1=" onchange=\"sendServer('OnChange_AddlChrgTpCd','{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:240\""/>
										</td>
										<td><ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="2015/01/01" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');">
										</td>
										<td><ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');"> 
										</td>
										<td><ezf:inputText name="trmnDt_A" ezfName="trmnDt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('trmnDt_A', 4, '{EZF_ROW_NUMBER}');"> 
										</td>
										<td><ezf:select name="bllgCycleCd_SE" ezfName="bllgCycleCd_SE" ezfHyo="A" ezfArrayNo="0" ezfCodeName="bllgCycleCd_CD" ezfDispName="bllgCycleNm_DI" otherAttr=" style=\"width:170\""/>
										</td>
 										<td><ezf:select name="svcBillByTpCd_SE" ezfName="svcBillByTpCd_SE" ezfHyo="A" ezfArrayNo="0" ezfCodeName="svcBillByTpCd_AC" ezfDispName="svcBillByTpNm_AD" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServer('OnChange_BillBy','{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:120\""/>
										</td>
										<td><ezf:inputText name="addlChrgFlatDealPrcAmt_A" ezfName="addlChrgFlatDealPrcAmt_A" value="12345.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"19\" style=\"text-align:right\""/></td>
										<td><ezf:inputText name="addlChrgAplcPct_A" ezfName="addlChrgAplcPct_A" value="10" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" style=\"text-align:right\""/></td>
										<td><ezf:select name="addlChrgInvTpCd_SE" ezfName="addlChrgInvTpCd_SE" ezfHyo="A" ezfArrayNo="0" ezfCodeName="addlChrgInvTpCd_CD" ezfDispName="addlChrgInvTpNm_DI" otherEvent1=" onchange=\"sendServer('OnChange_AddlChrgInvTpCd','{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:120\""/>
										</td>
										<td><ezf:inputCheckBox name="printDtlFlg_A" ezfName="printDtlFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="intgMdseCd_A" ezfName="intgMdseCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="termCondOptValTxt_A" ezfName="termCondOptValTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td class="pAuditInfo">
											<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" />
											<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" />
											<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" />
											<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" />
											<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" />
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
			</td>
		</tr>
			<td></td>
		<tr>
		</tr>
			<td></td>
		<tr>
			<td>
				<table bordercolor="green" border="0" cellpadding="1" cellspacing="0" width="100%">
					<col width="900" align="right">
					<col width="80"  align="left">
					<col width="100"  align="left">
					<tr>
						<td></td>
						<td>
							<ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" />
						</td>
						<td>
							<ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn6" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
			<hr></hr>
			</td>
		</tr>
		<%-- ##### Additional Charge History #####--%>
		<tr>
			<td>
				<table bordercolor="green" border="0" cellpadding="1" cellspacing="0" width="100%">
					<col width="5"  align="center">
					<col width="450"  align="left">
					<tr>
						<td></td>
						<td class="stab"><fmt:message key="i18n.NSAL0410Scrn00.label.25" bundle="${I18N_SCREEN_ID}">Additional Billing Charge - Billing History</fmt:message></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="0" cellpadding="1" cellspacing="0"  width="100%">
					<tr>
						<td valign="top">
							<%-- HEAD --%>
							<div id="topTBLHist" 
							     style="overflow-x:hidden; overflow-y:none; width:1096; word-break:break-all;">
								<table border="1" cellpadding="1" cellspacing="0" width="1471" id="B">
									<col align="center" width="90">
									<col align="center" width="90">
									<col align="center" width="90">
									<col align="center" width="100">
									<col align="center" width="240">
									<col align="center" width="90">
									<col align="center">
									<col align="center" width="90">
									<col align="center" width="150">
									<col align="center" width="150">
									<col align="center" width="90">
									<col align="center" width="60">
									<tr height="28">
										<td class="pClothBs">
											<a href="#" class="pSortCol" onclick="columnSort('B', 'bllgPerFromDt_B')" id="tHead.Order"><fmt:message key="i18n.NSAL0410Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Period Start<br>Date</fmt:message></a>
											<img id="sortIMG.bllgPerFromDt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs">
											<a href="#" class="pSortCol" onclick="columnSort('B', 'bllgPerThruDt_B')" id="tHead.Order"><fmt:message key="i18n.NSAL0410Scrn00.label.27" bundle="${I18N_SCREEN_ID}">Period End<br>Date</fmt:message></a>
											<img id="sortIMG.bllgPerThruDt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs">
											<a href="#" class="pSortCol" onclick="columnSort('B', 'custIssPoDt_B')" id="tHead.Order"><fmt:message key="i18n.NSAL0410Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Interface Date</fmt:message></a>
											<img id="sortIMG.custIssPoDt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs">
											<a href="#" class="pSortCol" onclick="columnSort('B', 'xxScrItem34Txt_B')" id="tHead.Order"><fmt:message key="i18n.NSAL0410Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></a>
											<img id="sortIMG.xxScrItem34Txt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs">
											<a href="#" class="pSortCol" onclick="columnSort('B', 'addlChrgTpDescTxt_B')" id="tHead.Order"><fmt:message key="i18n.NSAL0410Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Charge Type</fmt:message></a>
											<img id="sortIMG.addlChrgTpDescTxt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs">
											<a href="#" class="pSortCol" onclick="columnSort('B', 'svcBillByTpDescTxt_B')" id="tHead.Order"><fmt:message key="i18n.NSAL0410Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Bill By</fmt:message></a>
											<img id="sortIMG.svcBillByTpDescTxt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs">
											<a href="#" class="pSortCol" onclick="columnSort('B', 'xxScrItem50Txt_B')" id="tHead.Order"><fmt:message key="i18n.NSAL0410Scrn00.label.29" bundle="${I18N_SCREEN_ID}">Reference</fmt:message></a>
											<img id="sortIMG.xxScrItem50Txt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs">
											<a href="#" class="pSortCol" onclick="columnSort('B', 'addlChrgAplcPct_B')" id="tHead.Order"><fmt:message key="i18n.NSAL0410Scrn00.label.30" bundle="${I18N_SCREEN_ID}">Applicable %</fmt:message></a>
											<img id="sortIMG.addlChrgAplcPct_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs">
											<a href="#" class="pSortCol" onclick="columnSort('B', 'addlChrgFlatDealPrcAmt_B')" id="tHead.Order"><fmt:message key="i18n.NSAL0410Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Flat Price</fmt:message></a>
											<img id="sortIMG.addlChrgFlatDealPrcAmt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs">
											<a href="#" class="pSortCol" onclick="columnSort('B', 'addlChrgFlatDealPrcAmt_B')" id="tHead.Order"><fmt:message key="i18n.NSAL0410Scrn00.label.31" bundle="${I18N_SCREEN_ID}">Bill Amount</fmt:message></a>
											<img id="sortIMG.addlChrgFlatDealPrcAmt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs">
											<a href="#" class="pSortCol" onclick="columnSort('B', 'addlChrgInvTpDescTxt_B')" id="tHead.Order"><fmt:message key="i18n.NSAL0410Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Invoice Type</fmt:message></a>
											<img id="sortIMG.addlChrgInvTpDescTxt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs">
											<a href="#" class="pSortCol" onclick="columnSort('B', 'printDtlFlg_B')" id="tHead.Order"><fmt:message key="i18n.NSAL0410Scrn00.label.32" bundle="${I18N_SCREEN_ID}">Print on<br>Invoice</fmt:message></a>
											<img id="sortIMG.printDtlFlg_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
									</tr>
								</table>
							</div>
							<%-- DETAIL --%>
							<div id="bottomTBLHist" 
							     style="overflow-x:scroll; overflow-y:scroll; width:1113; height:187; word-break:break-all;"
							     onScroll="synchroScroll(this.id, new Array('topTBLHist'));">
								<table border="1" cellpadding="1" cellspacing="0" width="1471" id="B">
									<col align="center" width="90">
									<col align="center" width="90">
									<col align="center" width="90">
									<col align="center" width="100">
									<col align="center" width="240">
									<col align="center" width="90">
									<col align="center">
									<col align="center" width="90">
									<col align="center" width="150">
									<col align="center" width="150">
									<col align="center" width="90">
									<col align="center" width="60">
									<ezf:row ezfHyo="B">
									<tr height="28">
										<td><ezf:label name="bllgPerFromDt_B" ezfName="bllgPerFromDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:label name="bllgPerThruDt_B" ezfName="bllgPerThruDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:label name="custIssPoDt_B" ezfName="custIssPoDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:inputText name="xxScrItem34Txt_B" ezfName="xxScrItem34Txt_B" value="012345678901234567890123456789" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="addlChrgTpDescTxt_B" ezfName="addlChrgTpDescTxt_B" value="012345678901234567890123456789" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="svcBillByTpDescTxt_B" ezfName="svcBillByTpDescTxt_B" value="01234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:inputText name="xxScrItem50Txt_B" ezfName="xxScrItem50Txt_B" value="01234567890123456789012345678901234567890123456789" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"24\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:label name="addlChrgAplcPct_B" ezfName="addlChrgAplcPct_B" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:label name="addlChrgFlatDealPrcAmt_B" ezfName="addlChrgFlatDealPrcAmt_B" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:label name="invLineDealNetAmt_B" ezfName="invLineDealNetAmt_B" ezfHyo="B" ezfArrayNo="0" /></td>
										<td><ezf:inputText name="addlChrgInvTpDescTxt_B" ezfName="addlChrgInvTpDescTxt_B" value="012345678" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
										<td><ezf:label name="printDtlFlg_B" ezfName="printDtlFlg_B" ezfHyo="B" ezfArrayNo="0" /></td>
									</tr>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<!--***Scroll****************-->
<ezf:inputHidden name="xxListNum_AY" ezfName="xxListNum_AY" value="0" otherAttr=" id=\"xxListNum_AY\""/>
<ezf:inputHidden name="xxListNum_AX" ezfName="xxListNum_AX" value="0" otherAttr=" id=\"xxListNum_AX\""/>

<script type="text/javascript">
	setScroll();

	/**
	 * Save scroll position.
	 */
	function synchroScroll(id, ary) {
		if (id == 'bottomTBL') {
			document.getElementById('xxListNum_AY').value = document.getElementById(id).scrollTop;
			document.getElementById('xxListNum_AX').value = document.getElementById(id).scrollLeft;
		}
		synchroScrollLeft(id, ary);
	}

	/**
	 * Set before event scroll position.
	 */
	function setScroll() {
		var yVal = document.getElementById('xxListNum_AY').value;
		var xVal = document.getElementById('xxListNum_AX').value;

		var topTbl = document.getElementById('topTBL');
		var btmTbl = document.getElementById('bottomTBL');

		if (yVal < btmTbl.scrollHeight) {
			btmTbl.scrollTop = yVal;
		}
		if (xVal < topTbl.scrollWidth) {
			topTbl.scrollLeft = xVal;
			btmTbl.scrollLeft = xVal;
		}
	}
</script>
<!--***Scroll****************-->



<%-- **** Task specific area ends here **** --%>
