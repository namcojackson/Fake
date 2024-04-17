<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161128101536 --%>
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
<fmt:setBundle basename="I18N_NSAL0530Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSAL0530Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0530Scrn00.title" bundle="${I18N_SCREEN_ID}">Solution Search</fmt:message>">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="<fmt:message key="i18n.NSAL0530Scrn00.title" bundle="${I18N_SCREEN_ID}">Solution Search</fmt:message>" class="pTab_ON"><a href="#">Sln Search</a></li>
									</div>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<div class="pTab_BODY">
				<table border="0" width="95%" height="190px" align="center">
					<col width="70%"  align="left">
					<col width="30%"  align="left">
					<tr>
						<td>
							<fieldset style="margin:5px 0px 0px 0px; height:95%;">
								<legend style="font-size:12px;"><fmt:message key="i18n.NSAL0530Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Search Filters</fmt:message></legend>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="100"  align="left">
									<col width="200"  align="left">
									<col width="110"  align="left">
									<col align="left">
									<tr>
										<td class="stab"><ezf:anchor name="serNum_LK" ezfName="serNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Serial" ><fmt:message key="i18n.NSAL0530Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Serial#(*)</fmt:message></ezf:anchor></td>
										<td class="stab"><ezf:inputText name="serNum_H" ezfName="serNum_H" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
										<td class="stab"><fmt:message key="i18n.NSAL0530Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Account Owner(*)</fmt:message></td>
										<td class="stab">
											<ezf:select name="dsAcctSrchTpCd_H1" ezfName="dsAcctSrchTpCd_H1" ezfCodeName="dsAcctSrchTpCd_H" ezfDispName="dsAcctSrchTpDescTxt_H" otherAttr=" style=\"width:128px\""/>
											<ezf:inputText name="xxLocNm_H1" ezfName="xxLocNm_H1" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0530Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Solution#(*)</fmt:message></td>
										<td class="stab"><ezf:inputText name="xxScrItem29Txt_HS" ezfName="xxScrItem29Txt_HS" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>
										<td class="stab"><fmt:message key="i18n.NSAL0530Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Account Bill To(*)</fmt:message></td>
										<td class="stab">
											<ezf:select name="dsAcctSrchTpCd_H2" ezfName="dsAcctSrchTpCd_H2" ezfCodeName="dsAcctSrchTpCd_H" ezfDispName="dsAcctSrchTpDescTxt_H" otherAttr=" style=\"width:128px\""/>
											<ezf:inputText name="xxLocNm_H2" ezfName="xxLocNm_H2" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0530Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Solution Name(*)</fmt:message></td>
										<td class="stab"><ezf:inputText name="svcSlnNm_H" ezfName="svcSlnNm_H" otherAttr=" size=\"20\" maxlength=\"100\" ezftoupper=\"\""/></td>
										<td class="stab"><fmt:message key="i18n.NSAL0530Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Account Ship To(*)</fmt:message></td>
										<td class="stab">
											<ezf:select name="dsAcctSrchTpCd_H3" ezfName="dsAcctSrchTpCd_H3" ezfCodeName="dsAcctSrchTpCd_H" ezfDispName="dsAcctSrchTpDescTxt_H" otherAttr=" style=\"width:128px\""/>
											<ezf:inputText name="xxLocNm_H3" ezfName="xxLocNm_H3" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0530Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Configuration#(*)</fmt:message></td>
										<td class="stab"><ezf:inputText name="xxScrItem29Txt_HC" ezfName="xxScrItem29Txt_HC" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>
										<td class="stab"><fmt:message key="i18n.NSAL0530Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Install Date</fmt:message></td>
										<td class="stab">
											<ezf:inputText name="istlDt_H" ezfName="istlDt_H" value="09/15/2014" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('istlDt_H', 4);">
										</td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="svcMachMstrPk_LK" ezfName="svcMachMstrPk_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SvcMachMstr" ><fmt:message key="i18n.NSAL0530Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Install Base ID</fmt:message></ezf:anchor></td>
										<td class="stab"><ezf:inputText name="svcMachMstrPk_H" ezfName="svcMachMstrPk_H" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>
										<td class="stab">&nbsp;</td>
										<td class="stab">&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">&nbsp;</td>
										<td class="stab">&nbsp;</td>
										<td class="stab">&nbsp;</td>
										<td class="stab">&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">&nbsp;</td>
										<td class="stab">&nbsp;</td>
										<td class="stab">&nbsp;</td>
										<td class="stab">&nbsp;</td>
									</tr>
								</table>
							</fieldset>
						</td>
						<td>
							<fieldset style="margin:5px 0px 0px 0px; height:95%;">
								<legend style="font-size:12px;"><fmt:message key="i18n.NSAL0530Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Advanced Search</fmt:message></legend>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="100"  align="left">
									<col width="180"  align="left">
									<tr>
										<td class="stab"><ezf:anchor name="mdlNm_LK" ezfName="mdlNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Mdl" ><fmt:message key="i18n.NSAL0530Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Svc Model Name(*)</fmt:message></ezf:anchor></td>
										<td class="stab"><ezf:inputText name="mdlNm_H" ezfName="mdlNm_H" otherAttr=" size=\"23\" maxlength=\"50\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="mdseCd_LK" ezfName="mdseCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Mdse" ><fmt:message key="i18n.NSAL0530Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Item Code(*)</fmt:message></ezf:anchor></td>
										<td class="stab"><ezf:inputText name="mdseCd_H" ezfName="mdseCd_H" otherAttr=" size=\"23\" maxlength=\"16\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0530Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Mdse Type</fmt:message></td>
										<td class="stab">
											<ezf:select name="coaMdseTpCd_HS" ezfName="coaMdseTpCd_HS" ezfBlank="1" ezfCodeName="coaMdseTpCd_H" ezfDispName="coaMdseTpDescTxt_H" otherAttr=" style=\"width:170px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0530Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Sales Order#(*)</fmt:message></td>
										<td class="stab"><ezf:inputText name="cpoOrdNum_H" ezfName="cpoOrdNum_H" otherAttr=" size=\"23\" maxlength=\"8\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0530Scrn00.label.16" bundle="${I18N_SCREEN_ID}">External Ref#(*)</fmt:message></td>
										<td class="stab"><ezf:inputText name="custMachCtrlNum_H" ezfName="custMachCtrlNum_H" otherAttr=" size=\"23\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0530Scrn00.label.17" bundle="${I18N_SCREEN_ID}">PO#(*)</fmt:message></td>
										<td class="stab"><ezf:inputText name="custIssPoNum_H" ezfName="custIssPoNum_H" otherAttr=" size=\"23\" maxlength=\"35\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0530Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Contract#(*)</fmt:message></td>
										<td class="stab"><ezf:inputText name="dsContrNum_H" ezfName="dsContrNum_H" otherAttr=" size=\"23\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
						<td class="stab">&nbsp;</td>
						<td class="stab" align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search\""/></td>
					<tr>
					</tr>
				</table>
				<hr>

<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
				<table width="99%" border="0">
					<tr>
						<td width="70"><ezf:inputButton name="SolutionCreateNew" value="New" htmlClass="pBtn3" otherAttr=" id=\"SolutionCreateNew\""/></td>
						<td>
							<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
						</td>
						<td  align="right">
							<ezf:skip>
									<table border="0" cellpadding="1" cellspacing="0">
										<col >
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="10">
										<col width="0">
										<col width="1">
										<col width="0">
										<tr>
											<td class="stab">Showing</td>
											<td class="pOut">1</td>
											<td class="stab">to</td>
											<td class="pOut">3</td>
											<td class="stab">of</td>
											<td class="pOut">1000</td>
											<td>&nbsp;</td>
											<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
											<td></td>
											<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
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
					</tr>
				</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- ######################################## DETAIL ######################################## --%>
				<table>
					<tr>
						<td>
							<div id="parentBoxA">
							<div style="float:left; display:block"> <!-- left table -->
								<div id='leftTblHead' style="display:block;overflow:hidden;margin:0px;padding:0px;">
								</div>
								<div id='leftTbl' style="float:left; display:block; height:280px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
								</div>
							</div>  <!-- left table -->
							<div style="float:left"> <!-- right table -->
								<div id='rightTblHead' style="width:1102px; display:block; overflow:hidden; margin:0px;padding:0px;">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1103px" height="35px" style="margin-right:20px">
										<col width="160"  align="center">		<!-- Solution# -->
										<col width="192" align="center">		<!-- Solution Name -->
										<col width="150"  align="center">		<!-- Acct Name - Owner -->
										<col width="150" align="center">		<!-- Acct Name - Bill To -->
										<col width="150"  align="center">		<!-- Acct Name - Current Location -->
										<col width="92"  align="center">		<!-- Creation Date -->
										<col width="110"  align="center">		<!-- Creation By -->
										<col width="92"  align="center">		<!-- Last Update Date -->
										<tr height="34px">
											<td id="AH0" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcSlnSq_A')"><fmt:message key="i18n.NSAL0530Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Solution#</fmt:message></a><img id="sortIMG.svcSlnSq_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcSlnNm_A')"><fmt:message key="i18n.NSAL0530Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Solution Name</fmt:message></a><img id="sortIMG.svcSlnNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxLocNm_A1')"><fmt:message key="i18n.NSAL0530Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Acct Name - Owner</fmt:message></a><img id="sortIMG.xxLocNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxLocNm_A2')"><fmt:message key="i18n.NSAL0530Scrn00.label.22" bundle="${I18N_SCREEN_ID}">Acct Name - Bill To</fmt:message></a><img id="sortIMG.xxLocNm_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxLocNm_A3')"><fmt:message key="i18n.NSAL0530Scrn00.label.23" bundle="${I18N_SCREEN_ID}">Acct Name - Current Location</fmt:message></a><img id="sortIMG.xxLocNm_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcSlnCratDt_A')"><fmt:message key="i18n.NSAL0530Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Creation Date</fmt:message></a><img id="sortIMG.svcSlnCratDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcSlnCratPsnCd_A')"><fmt:message key="i18n.NSAL0530Scrn00.label.25" bundle="${I18N_SCREEN_ID}">Creation By</fmt:message></a><img id="sortIMG.svcSlnCratPsnCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcSlnUpdDt_A')"><fmt:message key="i18n.NSAL0530Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Last Update Date</fmt:message></a><img id="sortIMG.svcSlnUpdDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										</tr>
									</table>
								</div>
								<div id="rightTbl" style="width:1119px; height:280px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" width="1102px" id="A">
										<col width="160"  align="center">		<!-- Solution# -->
										<col width="192" align="center">		<!-- Solution Name -->
										<col width="150"  align="center">		<!-- Acct Name - Owner -->
										<col width="150" align="center">		<!-- Acct Name - Bill To -->
										<col width="150"  align="center">		<!-- Acct Name - Current Location -->
										<col width="92"  align="center">		<!-- Creation Date -->
										<col width="110"  align="center">		<!-- Creation By -->
										<col width="92"  align="center">		<!-- Last Update Date -->
										<ezf:row ezfHyo="A">
											<tr height="23px" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
												<td><ezf:anchor name="svcSlnSq_AL" ezfName="svcSlnSq_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SolutionCreateEdit" otherAttr=" id=\"svcSlnSq_AL#{EZF_ROW_NUMBER}\""><ezf:label name="svcSlnSq_A" ezfName="svcSlnSq_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
												<td><ezf:inputText name="svcSlnNm_A" ezfName="svcSlnNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcSlnNm_A#{EZF_ROW_NUMBER}\" size=\"25\" maxlength=\"100\" tabindex=\"-1\""/></td>
												<td><ezf:inputText name="xxLocNm_A1" ezfName="xxLocNm_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"billToLocNm_A#{EZF_ROW_NUMBER}\" size=\"18\" maxlength=\"60\" tabindex=\"-1\""/></td>
												<td><ezf:inputText name="xxLocNm_A2" ezfName="xxLocNm_A2" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"sellToLocNm_A#{EZF_ROW_NUMBER}\" size=\"18\" maxlength=\"60\" tabindex=\"-1\""/></td>
												<td><ezf:inputText name="xxLocNm_A3" ezfName="xxLocNm_A3" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"shipToLocNm_A#{EZF_ROW_NUMBER}\" size=\"18\" maxlength=\"60\" tabindex=\"-1\""/></td>
												<td><ezf:label name="svcSlnCratDt_A" ezfName="svcSlnCratDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="svcSlnCratPsnCd_A" ezfName="svcSlnCratPsnCd_A" value="XXXXXXXXX1XXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcSlnCratPsnCd_A#{EZF_ROW_NUMBER}\" size=\"13\" maxlength=\"32\" tabindex=\"-1\""/></td>
												<td><ezf:label name="svcSlnUpdDt_A" ezfName="svcSlnUpdDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td class="pAuditInfo">
													<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
													<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
													<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
													<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
													<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
												</td>
											</tr>
											<ezf:skip>
											</ezf:skip>
										</ezf:row>
									</table>
								</div>
							</div> <!-- right table -->
							</div> <!-- parent box -->
						</td>
					</tr>
				</table>
			</div>
			</td>
		</tr>
	</table>
</center>

<style TYPE="text/css">
<!--
	tr.checkLineBGcolor{background-color:yellow;}
	a img{border-style:none;}
-->
</style>

<script type="text/javascript">

	function clickImg(prntObj, idx) {
		var eventNm = prntObj.all(0).value;
		sendServer(eventNm, idx);
	}

	function changeBGColor(chkObj, idx) {
		var bgColorCls  = "";
		var origBgColor = document.getElementById("xxTblItemTxt_A#" + idx).value;
		
		if (chkObj.checked) {
			bgColorCls = "checkLineBGcolor";
			if (bgColorCls != origBgColor) {
				document.getElementById("xxTblItemTxt_A#" + idx).value =
					document.getElementById("A_RightTr#"  + idx).className;
			}
		} else {
			bgColorCls = origBgColor;
		}
		document.getElementById("A_RightTr#" + idx).className = bgColorCls;
	}

	S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true);
</script>


<%-- **** Task specific area ends here **** --%>
