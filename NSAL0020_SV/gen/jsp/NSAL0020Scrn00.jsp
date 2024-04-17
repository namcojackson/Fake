<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240229170459 --%>
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
<fmt:setBundle basename="I18N_NSAL0020Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSAL0020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0020Scrn00.title" bundle="${I18N_SCREEN_ID}">Machine Master Inquiry Screen</fmt:message>">

<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<!-- ######################################## HEADER ######################################## -->
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="<fmt:message key="i18n.NSAL0020Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Mach Mst Inq</fmt:message>" class="pTab_ON"><a href="#"><fmt:message key="i18n.NSAL0020Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Mach Mst Inq</fmt:message></a></li>
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
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<!-- ######################################## Search Criteria ######################################## -->
				<div class="pTab_BODY">
					<table border="0" cellspacing="0" cellpadding="0" style="width:98%; text-align:left;margin-top:3">
						<col width="120">
						<col width="340">
						<col width="110">
						<col width="300">
						<col width="">
						<tr>
							<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Saved Search Options</fmt:message></td>
							<td>
								<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width: 320px\" id=\"srchOptPk\""/>
							</td>
							<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Search Option Name</fmt:message></td>
							<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td>
								<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
								<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					<table border="0" width="95%" height="190px">
						<col width="56%"  align="left">
						<col width="44%"  align="left">
						<tr>
							<td>
								<fieldset style="margin:5px 0px 0px 0px; height:95%;">
								<legend style="font-size:12px;"><fmt:message key="i18n.NSAL0020Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Search Filters</fmt:message></legend>
								<table width="100%" border="0" cellpadding="0" cellspacing="0" height="160">
									<col width="50">
									<col width="15">
									<col width="5">
									<col width="5">
									<col width="15">
									<col width="5">
									<col width="5">
									<col width="70">
									<col width="80">
									<col width="50">
									
									<tr>
										<td colspan="2" class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Serial#/Tag#(*)</fmt:message></td>
										<td colspan="4"><ezf:inputText name="serNum" ezfName="serNum" value="123456789012345" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td colspan="2" class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Account Owner Name(*)</fmt:message></td>
										<td><ezf:inputText name="xxComnScrColValTxt_O" ezfName="xxComnScrColValTxt_O" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" size=\"15\" maxlength=\"360\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td colspan="2" class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Solution#(*)</fmt:message></td>
										<td colspan="4"><ezf:inputText name="xxScrItem30Txt_01" ezfName="xxScrItem30Txt_01" value="123456789012345" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Account#(*)</fmt:message></td>
										<td><ezf:inputText name="ownrAcctNum" ezfName="ownrAcctNum" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td colspan="2" class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Solution Name(*)</fmt:message></td>
										<td colspan="4"><ezf:inputText name="svcSlnNm" ezfName="svcSlnNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8WWWWWWWWW9WWWWWWWWW0" otherAttr=" size=\"15\" maxlength=\"100\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td colspan="2" class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Account Bill To Name/Address(*)</fmt:message></td>
										<td><ezf:inputText name="xxComnScrColValTxt_B" ezfName="xxComnScrColValTxt_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" size=\"15\" maxlength=\"649\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td colspan="2" class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Configuration#(*)</fmt:message></td>
										<td colspan="4"><ezf:inputText name="xxScrItem30Txt_02" ezfName="xxScrItem30Txt_02" value="123456789012345" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Account#(*)</fmt:message></td>
										<td><ezf:inputText name="billToAcctNum" ezfName="billToAcctNum" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td colspan="2" class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Install Base ID(*)</fmt:message></td>
										<td colspan="4"><ezf:inputText name="xxScrItem30Txt_03" ezfName="xxScrItem30Txt_03" value="123456789012345" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Address(Loc#)(*)</fmt:message></td>
										<td><ezf:inputText name="indBillToLocNum" ezfName="indBillToLocNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td colspan="2" class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Show Terminated Products</fmt:message></td>
										<td colspan="4"><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" /></td>
										<td>&nbsp;</td>
										<td colspan="2" class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Current Location Name/Address(*)</fmt:message></td>
										<td><ezf:inputText name="xxComnScrColValTxt_C" ezfName="xxComnScrColValTxt_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" size=\"15\" maxlength=\"649\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Install Date</fmt:message></td>
										<td><ezf:inputText name="istlDt_FR" ezfName="istlDt_FR" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
										<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('istlDt_FR', 4);" ></td>
										<td>-</td>
										<td><ezf:inputText name="istlDt_TO" ezfName="istlDt_TO" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
										<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('istlDt_TO', 4);" ></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Account#(*)</fmt:message></td>
										<td><ezf:inputText name="curLocAcctNum" ezfName="curLocAcctNum" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td colspan="2" class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Install Base Status</fmt:message></td>
										<td colspan="4">
											<ezf:select name="svcMachMstrStsCd_PS" ezfName="svcMachMstrStsCd_PS" ezfBlank="1" ezfCodeName="svcMachMstrStsCd_P" ezfDispName="svcMachMstrStsDescTxt_P" otherAttr=" style=\"width:110;\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Address(Loc#)(*)</fmt:message></td>
										<td><ezf:inputText name="indCurLocNum" ezfName="indCurLocNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td colspan="2" class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Software License#(*)</fmt:message></td>
										<td colspan="4"><ezf:inputText name="xxScrItem30Txt_04" ezfName="xxScrItem30Txt_04" value="123456789012345" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
								</fieldset>
							</td>
							<td>
							<fieldset style="margin:5px 0px 0px 0px; height:75%;">
								<legend style="font-size:12px;"><fmt:message key="i18n.NSAL0020Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Advanced Search</fmt:message></legend>
								<table width="100%" border="0" cellpadding="0" cellspacing="0" height="135">
									<col width="130">
									<col width="100">
									<col width="5">
									<col width="130">
									<col width="100">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Service Model Name(*)</fmt:message></td>
										<td ><ezf:inputText name="t_MdlNm" ezfName="t_MdlNm" value="123456789012345" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.20" bundle="${I18N_SCREEN_ID}">End Customer PO#(*)</fmt:message></td>
										<td><ezf:inputText name="custIssPoNum" ezfName="custIssPoNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Merchandise" ><fmt:message key="i18n.NSAL0020Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Item#(*)</fmt:message></ezf:anchor></td>
										<td ><ezf:inputText name="mdseCd" ezfName="mdseCd" value="123456789012345" otherAttr=" size=\"15\" maxlength=\"25\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.22" bundle="${I18N_SCREEN_ID}">Contract#(*)</fmt:message></td>
										<td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.23" bundle="${I18N_SCREEN_ID}">Item Description(*)</fmt:message></td>
										<td ><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="123456789012345" otherAttr=" size=\"15\" maxlength=\"250\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_CtacPsnLastNm" ><fmt:message key="i18n.NSAL0020Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Contact Last Name(*)</fmt:message></ezf:anchor></td>
										<td><ezf:inputText name="ctacPsnLastNm" ezfName="ctacPsnLastNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.25" bundle="${I18N_SCREEN_ID}">Mdse Type</fmt:message></td>
										<td >
											<ezf:select name="coaMdseTpCd_PS" ezfName="coaMdseTpCd_PS" ezfBlank="1" ezfCodeName="coaMdseTpCd_P" ezfDispName="coaMdseTpDescTxt_P" otherAttr=" style=\"width:110;\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.26" bundle="${I18N_SCREEN_ID}">IB Contact Type</fmt:message></td>
										<td >
											<ezf:select name="svcCtacTpCd_PS" ezfName="svcCtacTpCd_PS" ezfBlank="1" ezfCodeName="svcCtacTpCd_P" ezfDispName="svcCtacTpNm_P" otherAttr=" style=\"width:110;\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.27" bundle="${I18N_SCREEN_ID}">Order#(*)</fmt:message></td>
										<td ><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="12345678" otherAttr=" size=\"15\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><fmt:message key="i18n.NSAL0020Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Sales Rep Name(*)</fmt:message></td>
										<td><ezf:inputText name="tocNm" ezfName="tocNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
									</tr>
								</table>
							</fieldset>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
						</tr>
					</table>
					<!-- ######################################## Detail ######################################## -->
 					<table border="0" style="table-layout:fixed;width=100%;margin-left:20;">
						<col width="180">
						<col width="440">
						<col width="353">
						<col width="">
						<tr>
							<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
							<td></td>
							<!-- Pagination & Navigation--START-->
							<td>
								<ezf:skip>
									<table border="0" cellpadding="1" cellspacing="0">
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
										<td class="pOut">20</td>
										<td class="stab">of</td>
										<td class="pOut">200</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
									</tr>
									</table>
								</ezf:skip>
									<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
									</jsp:include>
							</td>
							<!-- Pagination & Navigation--END-->
							<td align="left"><ezf:inputButton name="NewMachine" value="New Machine" htmlClass="pBtn6" /></td>
						</tr>
					</table>
					<table>
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:220px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1065px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1540px" style="margin-right:20px" >
												<col width="55"  align="center">	<!-- Config Button -->
												<col width="55"  align="center">	<!-- SvcCall Button -->
												<col width="55"  align="center">	<!-- Meter Button -->
												<col width="90"  align="center">	<!-- Serial# -->
												<col width="90"  align="center">	<!-- Mdse Cd -->
												<col width="115" align="center">	<!-- Mdse Name -->
												<col width="95"  align="center">	<!-- Machine Status -->
												<col width="90"  align="center">	<!-- Service Model -->
												<col width="90"  align="center">	<!-- Config ID -->
												<col width="90"  align="center">	<!-- Solution# -->
												<col width="80"  align="center">	<!-- Install Date -->
												<col width="90"  align="center">	<!-- Machine ID -->
												<col width="115" align="center">	<!-- IB Owner -->
												<col width="115" align="center">	<!-- IB Current Location -->
												<col width="90"  align="center">	<!-- Current Loc# -->
												<col width="115" align="center">	<!-- IB Bill To -->
												<col width="90"  align="center">	<!-- IB Bill To Loc# -->
												<tr>
													<td id="AH0" class="pClothBs"><fmt:message key="i18n.NSAL0020Scrn00.label.29" bundle="${I18N_SCREEN_ID}">Config Button</fmt:message></td>
													<td id="AH1" class="pClothBs"><fmt:message key="i18n.NSAL0020Scrn00.label.30" bundle="${I18N_SCREEN_ID}">SvcCall Button</fmt:message></td>
													<td id="AH2" class="pClothBs"><fmt:message key="i18n.NSAL0020Scrn00.label.31" bundle="${I18N_SCREEN_ID}">Meter Button</fmt:message></td>
													<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A0')"><fmt:message key="i18n.NSAL0020Scrn00.label.32" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></a><img id="sortIMG.serNum_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A0')"><fmt:message key="i18n.NSAL0020Scrn00.label.33" bundle="${I18N_SCREEN_ID}">Item Cd</fmt:message></a><img id="sortIMG.mdseCd_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseDescShortTxt_A0')"><fmt:message key="i18n.NSAL0020Scrn00.label.34" bundle="${I18N_SCREEN_ID}">Item Name</fmt:message></a><img id="sortIMG.mdseDescShortTxt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachMstrStsDescTxtt_A0')"><fmt:message key="i18n.NSAL0020Scrn00.label.35" bundle="${I18N_SCREEN_ID}">Machine Status</fmt:message></a><img id="sortIMG.svcMachMstrStsDescTxtt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 't_MdlNm_A0')"><fmt:message key="i18n.NSAL0020Scrn00.label.36" bundle="${I18N_SCREEN_ID}">Service Model</fmt:message></a><img id="sortIMG.t_MdlNm_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcConfigMstrPk_A0')"><fmt:message key="i18n.NSAL0020Scrn00.label.37" bundle="${I18N_SCREEN_ID}">Config ID</fmt:message></a><img id="sortIMG.svcConfigMstrPk_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcSlnSq_A0')"><fmt:message key="i18n.NSAL0020Scrn00.label.38" bundle="${I18N_SCREEN_ID}">Solution#</fmt:message></a><img id="sortIMG.svcSlnSq_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'istlDt_A0')"><fmt:message key="i18n.NSAL0020Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Install Date</fmt:message></a><img id="sortIMG.istlDt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachMstrPk_A0')"><fmt:message key="i18n.NSAL0020Scrn00.label.39" bundle="${I18N_SCREEN_ID}">Machine ID</fmt:message></a><img id="sortIMG.svcMachMstrPk_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxComnScrColValTxt_AO')"><fmt:message key="i18n.NSAL0020Scrn00.label.40" bundle="${I18N_SCREEN_ID}">IB Owner</fmt:message></a><img id="sortIMG.xxComnScrColValTxt_AO" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxComnScrColValTxt_AC')"><fmt:message key="i18n.NSAL0020Scrn00.label.41" bundle="${I18N_SCREEN_ID}">IB Current<br>Location</fmt:message></a><img id="sortIMG.xxComnScrColValTxt_AC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'indCurLocNum_A0')"><fmt:message key="i18n.NSAL0020Scrn00.label.42" bundle="${I18N_SCREEN_ID}">Current Loc#</fmt:message></a><img id="sortIMG.indCurLocNum_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxComnScrColValTxt_AB')"><fmt:message key="i18n.NSAL0020Scrn00.label.43" bundle="${I18N_SCREEN_ID}">IB Bill To</fmt:message></a><img id="sortIMG.xxComnScrColValTxt_AB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'indBillToLocNum_A0')"><fmt:message key="i18n.NSAL0020Scrn00.label.44" bundle="${I18N_SCREEN_ID}">IB Bill To Loc#</fmt:message></a><img id="sortIMG.indBillToLocNum_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id="rightTbl" style="width:1082px; height:237px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1540px" >
												<col width="55"  align="center">	<!-- Config Button -->
												<col width="55"  align="center">	<!-- SvcCall Button -->
												<col width="55"  align="center">	<!-- Meter Button -->
												<col width="90"  align="center">	<!-- Serial# -->
												<col width="90"  align="center">	<!-- Mdse Cd -->
												<col width="115" align="center">	<!-- Mdse Name -->
												<col width="95"  align="center">	<!-- Machine Status -->
												<col width="90"  align="center">	<!-- Service Model -->
												<col width="90"  align="center">	<!-- Config ID -->
												<col width="90"  align="center">	<!-- Solution# -->
												<col width="80"  align="center">	<!-- Install Date -->
												<col width="90"  align="center">	<!-- Machine ID -->
												<col width="115" align="center">	<!-- IB Owner -->
												<col width="115" align="center">	<!-- IB Current Location -->
												<col width="90"  align="center">	<!-- Current Loc# -->
												<col width="115" align="center">	<!-- IB Bill To -->
												<col width="90"  align="center">	<!-- IB Bill To Loc# -->
												<ezf:row ezfHyo="A">
												<tr id="A_rightTBLRow#{EZF_ROW_NUMBER}">
													<td align="center"><ezf:inputButton name="OpenWin_NSAL0520" value="Config" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn3" /></td>
													<td align="center"><ezf:inputButton name="OpenWin_Repair_History" value="SvcCall" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn3" /></td>
													<td align="center"><ezf:inputButton name="OpenWin_NSAL0150" value="Meter" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn3" /></td>
													<td style="word-break: break-all;">
													<ezf:anchor name="serNum_A0" ezfName="serNum_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Disp_Machine_Master_Maintenance" otherAttr=" ezfanchortext=\"\"">
														<ezf:label name="serNum_A0" ezfName="serNum_A0" ezfHyo="A" ezfArrayNo="0" />
													</ezf:anchor>
													</td>
													<td><ezf:label name="mdseCd_A0" ezfName="mdseCd_A0" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="mdseDescShortTxt_A0" ezfName="mdseDescShortTxt_A0" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="svcMachMstrStsDescTxt_A0" ezfName="svcMachMstrStsDescTxt_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\""/></td>
													<td style="word-break: break-all;">
														<ezf:anchor name="t_MdlNm_A0" ezfName="t_MdlNm_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Disp_NSAL0490_Model_Maintenance" otherAttr=" ezfanchortext=\"\"">
															<ezf:label name="t_MdlNm_A0" ezfName="t_MdlNm_A0" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td style="word-break: break-all;">
														<ezf:anchor name="svcConfigMstrPk_A0" ezfName="svcConfigMstrPk_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Disp_Machine_Master_Maintenance" otherAttr=" ezfanchortext=\"\"">
															<ezf:label name="svcConfigMstrPk_A0" ezfName="svcConfigMstrPk_A0" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td style="word-break: break-all;">
														<ezf:anchor name="svcSlnSq_A0" ezfName="svcSlnSq_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Disp_NSAL0540_Solution_Maintenance" otherAttr=" ezfanchortext=\"\"">
															<ezf:label name="svcSlnSq_A0" ezfName="svcSlnSq_A0" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td><ezf:label name="istlDt_A0" ezfName="istlDt_A0" ezfHyo="A" ezfArrayNo="0" /></td>
													<td style="word-break: break-all;">
														<ezf:anchor name="svcMachMstrPk_A0" ezfName="svcMachMstrPk_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Disp_Machine_Master_Maintenance" otherAttr=" ezfanchortext=\"\"">
															<ezf:label name="svcMachMstrPk_A0" ezfName="svcMachMstrPk_A0" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td><ezf:inputText name="xxComnScrColValTxt_AO" ezfName="xxComnScrColValTxt_AO" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"360\""/></td>
													<td><ezf:inputText name="xxComnScrColValTxt_AC" ezfName="xxComnScrColValTxt_AC" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"649\""/></td>
													<td style="word-break: break-all;">
														<ezf:anchor name="indCurLocNum_A0" ezfName="indCurLocNum_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Disp_NMAL6720_Add_New_Location_Screen_Current" otherAttr=" ezfanchortext=\"\"">
															<ezf:label name="indCurLocNum_A0" ezfName="indCurLocNum_A0" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td><ezf:inputText name="xxComnScrColValTxt_AB" ezfName="xxComnScrColValTxt_AB" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"649\""/></td>
													<td style="word-break: break-all;">
														<ezf:anchor name="indBillToLocNum_A0" ezfName="indBillToLocNum_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Disp_NMAL6720_Add_New_Location_Screen_Bill" otherAttr=" ezfanchortext=\"\"">
															<ezf:label name="indBillToLocNum_A0" ezfName="indBillToLocNum_A0" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td class="pAuditInfo">
														<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
														<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
														<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
														<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
														<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
													</td>
												</tr>
												</ezf:row>
											</table>
										</div><!-- rightTbl -->
									</div><!-- right table -->
								</div><!-- parent box -->
							</td>
						</tr>
					</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>
	<script type="text/javascript" defer>
	    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true);
	</script>
<!-- ###SCRIPT -->
<script type="text/javascript">
	function synchroHeaderRightScroll() {
		var topTBL    = document.getElementById( 'topHeaderTBL'    );
		var rightTBL  = document.getElementById( 'rightHeaderTBL'  );
		var leftTBL  = this.document.getElementById( 'leftHeaderTBL'  );
		topTBL.scrollLeft = rightTBL.scrollLeft;
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroScroll_fromHeaderLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftHeaderTBL'  );
		var rightTBL = this.document.getElementById( 'rightHeaderTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroLineRightScroll() {
		var topTBL    = document.getElementById( 'topLineTBL'    );
		var rightTBL  = document.getElementById( 'rightLineTBL'  );
		var leftTBL  = this.document.getElementById( 'leftLineTBL'  );
		topTBL.scrollLeft = rightTBL.scrollLeft;
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroScroll_fromLineLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftLineTBL'  );
		var rightTBL = this.document.getElementById( 'rightLineTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
</script>

<%-- **** Task specific area ends here **** --%>
