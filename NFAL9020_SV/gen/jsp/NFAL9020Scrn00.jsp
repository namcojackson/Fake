<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100922025045 --%>
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
<fmt:setBundle basename="I18N_NFAL9020Scrn00" var="I18N_SCREEN_ID" scope="request" />
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

<table width="95%" align="center">
	<tr>
		<td>
			<table width="733px" border="0" cellspacing="0">
				<col width="70">
				<col width="60">
				<col width="60">
				<col width="40">
				<col width="60">
				<col width="70">
				<col width="60">
				<col width="313">
				<tr>
					<td class="stab"></td>
					<td class="stab" align="left"><fmt:message key="i18n.NFAL9020Scrn00.label.1" bundle="${I18N_SCREEN_ID}">AJE ID</fmt:message></td>
					<td colspan="5" class="stab">
						<ezf:inputText name="ajeId" ezfName="ajeId" value="AWB-080-02" otherAttr=" size=\"11\" maxlength=\"10\" ezftoupper=\"\""/>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="stab"></td>
					<td class="stab" align="left"><fmt:message key="i18n.NFAL9020Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Sys Src</fmt:message></td>
					<td align="left" class="stab">
						<ezf:select name="sysSrcCd_3" ezfName="sysSrcCd_3" ezfBlank="1" ezfCodeName="sysSrcCd_1" ezfDispName="sysSrcCd_2" otherEvent1=" onchange=\"sendServer('OnChange_SYS_SRC_CD')\"" otherAttr=" style=\"width:52px;\""/>
					</td>
					<td class="stab" align="center"><fmt:message key="i18n.NFAL9020Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Trx</fmt:message></td>
					<td align="center" class="stab">
						<ezf:select name="trxCd_3" ezfName="trxCd_3" ezfBlank="1" ezfCodeName="trxCd_1" ezfDispName="trxCd_2" otherEvent1=" onchange=\"sendServer('OnChange_TRX_CD')\"" otherAttr=" style=\"width:52px;\""/>
					</td>
					<td class="stab" align="center"><fmt:message key="i18n.NFAL9020Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Trx Reason</fmt:message></td>
					<td align="center" class="stab">
						<ezf:select name="trxRsnCd_3" ezfName="trxRsnCd_3" ezfBlank="1" ezfCodeName="trxRsnCd_1" ezfDispName="trxRsnCd_2" otherEvent1=" onchange=\"sendServer('OnChange_TRX_RSN_CD')\"" otherAttr=" style=\"width:52px;\""/>
					</td>
					<td align="right" class="stab">
						<ezf:inputButton name="SearchBtn" value="Search" htmlClass="pBtn5" otherAttr=" style=\"width:100px;height:25px\""/>
					</td>
				</tr>
			</table>
			
			<hr size="1" noshade>
			<%-- Pagenation --%>
			<table width="733px" border="0" cellpadding="1" cellspacing="0">
				<tr align="right" height="35">
					<td valign="middle">
<%-- Pagenation --%>
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
										<col width="1">
										<col>
										<tr>
											<td class="stab"><fmt:message key="i18n.NFAL9020Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Showing</fmt:message></td>
											<td class="pOut">1</td>
											<td class="stab"><fmt:message key="i18n.NFAL9020Scrn00.label.6" bundle="${I18N_SCREEN_ID}">to</fmt:message></td>
											<td class="pOut">15</td>
											<td class="stab"><fmt:message key="i18n.NFAL9020Scrn00.label.7" bundle="${I18N_SCREEN_ID}">of</fmt:message></td>
											<td class="pOut">200</td>
											<td>&nbsp;</td>
											<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
											<td></td>
											<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
										</tr>
									</table>
							--%>

						<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
							<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
							<jsp:param name="TableNm"     value="A" />
							<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
							<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
							<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
							<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum" />
							<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
							<jsp:param name="ViewMode"   value="LEFT_NONE" />
						</jsp:include>
					</td>
				</tr>
			</table>
		<%--------------------------------------------------detail start -------------------------------------------------------%>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
		<%-- ########## Table:A --%>
				<td align="left" valign="top" >
					<table border="1" cellpadding="1" cellspacing="0">
						<col width="70">
						<col width="100">
							<tr height="30">
									<td align="center" class="pClothBs">&nbsp;</td>
									<td align="center" class="pClothBs"><fmt:message key="i18n.NFAL9020Scrn00.label.1" bundle="${I18N_SCREEN_ID}">AJE ID</fmt:message></td>
							</tr>
					</table>
					<!-- id:leftTBL -->
					<div id="leftTBL" style="overflow-y:hidden; height:380; overflow-x:hidden; width:170;"  onscroll="synchroRightScroll();">
						<table border="1" cellpadding="1" cellspacing="0" id="A">
						<col width="70">
						<col width="100">
						<ezf:row ezfHyo="A">
							<tr height="20">
								<td align="center">
									<ezf:inputButton name="SelectBtn" value="Select" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn4" />
								</td>
								<td align="left" class="stab">
									<ezf:label name="ajeId_A" ezfName="ajeId_A" ezfHyo="A" ezfArrayNo="0" />
								</td>
							</tr>
						</ezf:row>
						<ezf:skip>

						</ezf:skip>
						</table>
					</div>
				</td>
		<%-- ### Table:B --%>
				<td valign="top">
					<!-- id:topTBL -->
					<div id="topTBL" style="overflow-y:none; height:20; overflow-x:hidden; width:580;">
						<table border="1" cellpadding="1" cellspacing="0" width="1850">
							<col width="200">
							<col width="200">
							<col width="200">
							<col width="200">
							<col width="150">
							<col width="200">
							<col width="150">
							<col width="200">
							<col width="150">
							<col width="200">
							<tr height="30">
								<td align="center" class="pClothBs"><fmt:message key="i18n.NFAL9020Scrn00.label.8" bundle="${I18N_SCREEN_ID}">System Source</fmt:message></td>
								<td align="center" class="pClothBs"><fmt:message key="i18n.NFAL9020Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Transaction</fmt:message></td>
								<td align="center" class="pClothBs"><fmt:message key="i18n.NFAL9020Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Transaction Reason</fmt:message></td>
								<td align="center" class="pClothBs"><fmt:message key="i18n.NFAL9020Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Indicator Type 1</fmt:message></td>
								<td align="center" class="pClothBs"><fmt:message key="i18n.NFAL9020Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Value 1</fmt:message></td>
								<td align="center" class="pClothBs"><fmt:message key="i18n.NFAL9020Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Indicator Type 2</fmt:message></td>
								<td align="center" class="pClothBs"><fmt:message key="i18n.NFAL9020Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Value 2</fmt:message></td>
								<td align="center" class="pClothBs"><fmt:message key="i18n.NFAL9020Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Indicator Type 3</fmt:message></td>
								<td align="center" class="pClothBs"><fmt:message key="i18n.NFAL9020Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Value 3</fmt:message></td>
								<td align="center" class="pClothBs"><fmt:message key="i18n.NFAL9020Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Journal Category</fmt:message></td>
							</tr>
						</table>
					</div>
					<!-- id:rightTBL -->
					<div id="rightTBL" style="overflow-y:hidden; height:380; overflow-x:scroll; width:580;" onscroll="synchroRightScroll();">
						<table border="1" cellpadding="1" cellspacing="0" width="1850" id="B">
							<col width="200">
							<col width="200">
							<col width="200">
							<col width="200">
							<col width="150">
							<col width="200">
							<col width="150">
							<col width="200">
							<col width="150">
							<col width="200">
							<ezf:row ezfHyo="A">
								<tr height="20">
									<td align="center"><ezf:inputText name="sysSrcNm_A" ezfName="sysSrcNm_A" value="S21 Order" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:18px;width:190px;font-size:9pt;\""/></td>
									<td align="center"><ezf:inputText name="trxNm_A" ezfName="trxNm_A" value="Description" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:18px;width:190px;font-size:9pt;\""/></td>
									<td align="center"><ezf:inputText name="trxRsnNm_A" ezfName="trxRsnNm_A" value="Loan Depreciation" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:18px;width:190px;font-size:9pt;\""/></td>
									<td align="center"><ezf:inputText name="ajePtrnIndTpNm_01" ezfName="ajePtrnIndTpNm_01" value="Credit Debit Reason" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:18px;width:190px;font-size:9pt;\""/></td>
									<td align="center"><ezf:inputText name="ajePtrnActlNm_01" ezfName="ajePtrnActlNm_01" value="FREIGHT OUT" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:18px;width:140px;font-size:9pt;\""/></td>
									<td align="center"><ezf:inputText name="ajePtrnIndTpNm_02" ezfName="ajePtrnIndTpNm_02" value="Credit Debit Reason" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:18px;width:190px;font-size:9pt;\""/></td>
									<td align="center"><ezf:inputText name="ajePtrnActlNm_02" ezfName="ajePtrnActlNm_02" value="IMPORT" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:18px;width:140px;font-size:9pt;\""/></td>
									<td align="center"><ezf:inputText name="ajePtrnIndTpNm_03" ezfName="ajePtrnIndTpNm_03" value="Credit Debit Reason" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:18px;width:190px;font-size:9pt;\""/></td>
									<td align="center"><ezf:inputText name="ajePtrnActlNm_03" ezfName="ajePtrnActlNm_03" value="IMPORT" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:18px;width:140px;font-size:9pt;\""/></td>
									<td align="center"><ezf:inputText name="jrnlCatgNm_A" ezfName="jrnlCatgNm_A" value="WWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:18px;width:190px;font-size:9pt;\""/></td>
								</tr>
							</ezf:row>
							<ezf:skip>

							</ezf:skip>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<%--------------------------------------------------detail end -------------------------------------------------------%>
		</td>
	</tr>
</table>

<!-- Set Page ID  -->
<input type="hidden" name="pageID" value="NFAL9020Scrn00">
<!-- Set Page Name -->
<input type="hidden" name="pageName" value="<fmt:message key="i18n.NFAL9020Scrn00.title" bundle="${I18N_SCREEN_ID}">AJE Pattern Search</fmt:message>">

<script type="text/javascript">
function synchroRightScroll() {
	var rightTBL = document.getElementById( 'rightTBL' );
	var topTBL    = document.getElementById( 'topTBL'    );
	var leftTBL  = document.getElementById( 'leftTBL'  );
	leftTBL.scrollTop = rightTBL.scrollTop;
	rightTBL.scrollLeft = topTBL.scrollLeft = rightTBL.scrollLeft;
}
</script>

<%-- **** Task specific area ends here **** --%>
