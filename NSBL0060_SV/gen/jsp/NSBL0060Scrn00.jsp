<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161024113359 --%>
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
<fmt:setBundle basename="I18N_NSBL0060Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSBL0060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSBL0060Scrn00.title" bundle="${I18N_SCREEN_ID}">Repair History by Serial# Popup</fmt:message>">
			<table>
				<tr>
					<td>
						<table border="1" cellpadding="1" cellspacing="0">
							<col width="80" align="center">
							<col width="80" align="center">
							<col width="110" align="center">
							<col width="80" align="center">
							<col width="130" align="center">
							<col width="185" align="center">
							<col width="80" align="center">
							<col width="220" align="center">
							<tr> 
								<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0060Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></td>
								<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0060Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Machine#</fmt:message></td>
								<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0060Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Merchandise Code</fmt:message></td>
								<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0060Scrn00.label.4" bundle="${I18N_SCREEN_ID}">LOB</fmt:message></td>
								<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0060Scrn00.label.5" bundle="${I18N_SCREEN_ID}">TEAM</fmt:message></td>
								<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0060Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Model Name</fmt:message></td>
								<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0060Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Ship To Code</fmt:message></td>
								<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0060Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Ship To Name</fmt:message></td>
							</tr>
						</table>
						<table border="1" cellpadding="1" cellspacing="0">
							<col width="80" align="center">
							<col width="80" align="center">
							<col width="110" align="center">
							<col width="80" align="center">
							<col width="130" align="center">
							<col width="185" align="center">
							<col width="80" align="center">
							<col width="220" align="center">
							<tr height="28">
								<td><ezf:inputText name="serNum" ezfName="serNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"10\" maxlength=\"30\""/></td>
								<td><ezf:inputText name="custMachCtrlNum" ezfName="custMachCtrlNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"10\" maxlength=\"30\""/></td>
								<td><ezf:label name="mdseCd" ezfName="mdseCd" /></td>
								<td><ezf:inputText name="svcByLineBizTpCd" ezfName="svcByLineBizTpCd" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"10\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="svcTeamTxt" ezfName="svcTeamTxt" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"10\" maxlength=\"64\""/></td>
								<td><ezf:inputText name="mdlNm" ezfName="mdlNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
								<td><ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"10\" maxlength=\"20\""/></td>
								<td><ezf:inputText name="locNm" ezfName="locNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			<br>
			
			<table>
				<tr>
					<td>
						<fieldset>
							<table>
								<col width="100">
								<col width="120">
								<col width="120">
								<col width="80">
								<col width="30">
								<tr>
									<td class="stab"><fmt:message key="i18n.NSBL0060Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Install Date</fmt:message></td>
									<td><ezf:inputText name="istlDt" ezfName="istlDt" value="12/31/9999" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
									<td class="stab"><fmt:message key="i18n.NSBL0060Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Average Meter Usage</fmt:message></td>
									<td><ezf:inputText name="mtrCnt" ezfName="mtrCnt" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
									<td class="stab"><fmt:message key="i18n.NSBL0060Scrn00.label.11" bundle="${I18N_SCREEN_ID}">/ Day</fmt:message></td>
								</tr>
								<tr>
									<td class="stab"><fmt:message key="i18n.NSBL0060Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Total Complete Call</fmt:message></td>
									<td><ezf:inputText name="xxTotCnt" ezfName="xxTotCnt" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
									<td class="stab"><fmt:message key="i18n.NSBL0060Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Average Response</fmt:message></td>
									<td><ezf:inputText name="xxDtTm_HD" ezfName="xxDtTm_HD" value="999:59" otherAttr=" size=\"6\" maxlength=\"6\""/></td>
									<td></td>
								</tr>
							</table>
						</fieldset>
					
					</td>
					<td>
						<table>
							<col width="20">
							<col width="40">
							<col width="95">
							<col width="10">
							<col width="100">
							<col width="110">
							<col width="90">
							<tr>
								<td></td>
								<td class="stab"><fmt:message key="i18n.NSBL0060Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Period</fmt:message></td>
								<td><ezf:inputText name="xxFromDt" ezfName="xxFromDt" value="12/31/9999" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxFromDt', 4 );"></td>
								<td class="stab">-</td>
								<td><ezf:inputText name="xxToDt" ezfName="xxToDt" value="12/31/9999" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxToDt', 4 );"></td>
								<td><ezf:inputText name="tmZoneCd" ezfName="tmZoneCd" value="EST" otherAttr=" size=\"4\""/></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			<hr>
			
			<table border="0" cellpadding="1" cellspacing="0">
				<col width="650">
				<tr>
					<td></td>
					<td>
						<table>
							<tr align="right">
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
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<table><tr><td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td align="center" valign="top" width="270">
							<!-- Left TBL Header -->
							<table border="1" cellpadding="1" cellspacing="0">
								<col width="75" align="center">
								<col width="75" align="center">
								<col width="120" align="center">
								<tr class="pEvenNumberBGcolor" height="24">
									<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0060Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Task#</fmt:message></td>
									<td class="pClothBs" ><fmt:message key="i18n.NSBL0060Scrn00.label.16" bundle="${I18N_SCREEN_ID}">FSR#</fmt:message></td>
									<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0060Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Status</fmt:message></td>
								</tr>
								<tr class="pEvenNumberBGcolor" height="24">
									<td class="pClothBs" ><fmt:message key="i18n.NSBL0060Scrn00.label.18" bundle="${I18N_SCREEN_ID}">FSR Visit#</fmt:message></td>
								</tr>
							</table>
							<!-- Left TBL Main -->
							<div id="leftTBL" style="overflow-y:hidden; height:290; overflow-x:none; width:270;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));">
								<table border="1" cellpadding="1" cellspacing="0" id="A1">
									<coL width="75">
									<col width="75">
									<col width="120" align="center">
									<ezf:row ezfHyo="A">
									<tr height="24">
										<td rowspan="2"><ezf:label name="svcTaskNum" ezfName="svcTaskNum" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="fsrNum" ezfName="fsrNum" ezfHyo="A" ezfArrayNo="0" /></td>
										<td rowspan="2"><ezf:inputText name="xxEdtCdNm_ST" ezfName="xxEdtCdNm_ST" value="WW:WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"33\" id=\"xxEdtCdNm_ST#{EZF_ROW_NUMBER}\""/></td>
									</tr>
									<tr height="24">
										<td><ezf:label name="fsrVisitNum" ezfName="fsrVisitNum" ezfHyo="A" ezfArrayNo="0" /></td>
									</tr>
									</ezf:row>
									<ezf:skip>
									</ezf:skip>
								</table>
							</div>
						</td>
						
						<td valign="top">
							<!-- Right TBL Header -->
							<div id="rightTopTBL" style="overflow-y:none; height:37; overflow-x:hidden; width:710;" onscroll="synchroScrollLeft(this.id, new Array('rightTBL'));" >
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
									<col width="150" align="center">
									<col width="150" align="center">
									<col width="150" align="center">
									<col width="115" align="center">
									<col width="110" align="center">
									<col width="50" align="center">
									<col width="100" align="center">
									<col width="150" align="center">
									<col width="130" align="center">
									<col width="130" align="center">
									<col width="130" align="center">
									<tr class="pEvenNumberBGcolor" height="24">
										<td class="pClothBs"><fmt:message key="i18n.NSBL0060Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Call Tp</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0060Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Problem Cd</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0060Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Location Cd</fmt:message></td>
										<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0060Scrn00.label.22" bundle="${I18N_SCREEN_ID}">Technician</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0060Scrn00.label.23" bundle="${I18N_SCREEN_ID}">Received Dt</fmt:message></td>
										<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0060Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Rsp<br>Time</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0060Scrn00.label.25" bundle="${I18N_SCREEN_ID}">Meter Cnt</fmt:message></td>
										<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NSBL0060Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Bill Tp</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0060Scrn00.label.27" bundle="${I18N_SCREEN_ID}">Parts Amt</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0060Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Labor Amt</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0060Scrn00.label.29" bundle="${I18N_SCREEN_ID}">Travel Amt</fmt:message></td>
									</tr>
									<tr class="pEvenNumberBGcolor" height="24">
										<td class="pClothBs"><fmt:message key="i18n.NSBL0060Scrn00.label.30" bundle="${I18N_SCREEN_ID}">Symptom Cd</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0060Scrn00.label.31" bundle="${I18N_SCREEN_ID}">Reason Cd</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0060Scrn00.label.32" bundle="${I18N_SCREEN_ID}">Correction Cd</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0060Scrn00.label.33" bundle="${I18N_SCREEN_ID}">Completed Dt</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSBL0060Scrn00.label.34" bundle="${I18N_SCREEN_ID}">Test Cnt</fmt:message></td>
										<td class="pClothBs" colspan="3"><fmt:message key="i18n.NSBL0060Scrn00.label.35" bundle="${I18N_SCREEN_ID}">CCY</fmt:message></td>
									</tr>
								</table>
							</div>
							<!-- Right TBL Main -->
							<div id="rightTBL" style="overflow-y:scroll; height:307; overflow-x:scroll; width:727;" onscroll="synchroScrollTop(this.id, new Array('leftTBL')); synchroScrollLeft(this.id, new Array('rightTopTBL'));">
								<table border="1" cellpadding="1" cellspacing="0" id="A2" style="table-layout:fixed;">
									<col width="150" align="center">
									<col width="150" align="center">
									<col width="150" align="center">
									<col width="115" align="center">
									<col width="110" align="center">
									<col width="50" align="center">
									<col width="100" align="right">
									<col width="150" align="center">
									<col width="130" align="center">
									<col width="130" align="center">
									<col width="130" align="center">
									<ezf:row ezfHyo="A">
									<tr height="24">
										<td><ezf:inputText name="xxEdtCdNm_CA" ezfName="xxEdtCdNm_CA" value="WW:WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"33\" id=\"xxEdtCdNm_CA#{EZF_ROW_NUMBER}\""/></td>
										<td><ezf:inputText name="xxEdtCdNm_PR" ezfName="xxEdtCdNm_PR" value="WWWW:WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"35\" id=\"xxEdtCdNm_PR#{EZF_ROW_NUMBER}\""/></td>
										<td><ezf:inputText name="xxEdtCdNm_LO" ezfName="xxEdtCdNm_LO" value="WWWW:WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"35\" id=\"xxEdtCdNm_LO#{EZF_ROW_NUMBER}\""/></td>
										<td rowspan="2"><ezf:inputText name="xxEdtCdNm_TE" ezfName="xxEdtCdNm_TE" value="WWWWWWWWW1WWWWWWWWW2:WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"66\" id=\"xxEdtCdNm_TE#{EZF_ROW_NUMBER}\""/></td>
										<td><ezf:label name="svcTaskRcvDt" ezfName="svcTaskRcvDt" ezfHyo="A" ezfArrayNo="0" />&nbsp;<ezf:label name="tmZoneCd_A1" ezfName="tmZoneCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td rowspan="2"><ezf:label name="xxDtTm_DL" ezfName="xxDtTm_DL" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="readMtrCnt" ezfName="readMtrCnt" ezfHyo="A" ezfArrayNo="0" /></td>
										<td rowspan="2"><ezf:inputText name="xxEdtCdNm_BI" ezfName="xxEdtCdNm_BI" value="WW:WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"33\" id=\"xxEdtCdNm_BI#{EZF_ROW_NUMBER}\""/></td>
										<td><ezf:inputText name="xxInpAmtNum_PA" ezfName="xxInpAmtNum_PA" value="99,999,999,999.99" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"24\" id=\"xxInpAmtNum_PA#{EZF_ROW_NUMBER}\""/></td>
										<td><ezf:inputText name="xxInpAmtNum_LA" ezfName="xxInpAmtNum_LA" value="99,999,999,999.99" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"24\" id=\"xxInpAmtNum_LA#{EZF_ROW_NUMBER}\""/></td>
										<td><ezf:inputText name="xxInpAmtNum_TR" ezfName="xxInpAmtNum_TR" value="99,999,999,999.99" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"24\" id=\"xxInpAmtNum_TR#{EZF_ROW_NUMBER}\""/></td>
									</tr>
									<tr height="24">
										<td><ezf:inputText name="xxEdtCdNm_SY" ezfName="xxEdtCdNm_SY" value="WWW:WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"34\" id=\"xxEdtCdNm_SY#{EZF_ROW_NUMBER}\""/></td>
										<td><ezf:inputText name="xxEdtCdNm_RE" ezfName="xxEdtCdNm_RE" value="WWW:WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"34\" id=\"xxEdtCdNm_RE#{EZF_ROW_NUMBER}\""/></td>
										<td><ezf:inputText name="xxEdtCdNm_CO" ezfName="xxEdtCdNm_CO" value="WWW:WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"34\" id=\"xxEdtCdNm_CO#{EZF_ROW_NUMBER}\""/></td>
										<td><ezf:label name="svcTaskCpltDt" ezfName="svcTaskCpltDt" ezfHyo="A" ezfArrayNo="0" />&nbsp;<ezf:label name="tmZoneCd_A2" ezfName="tmZoneCd_A2" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="testMtrCnt" ezfName="testMtrCnt" ezfHyo="A" ezfArrayNo="0" /></td>
										<td colspan="3" align="center"><ezf:label name="invCcyCd" ezfName="invCcyCd" ezfHyo="A" ezfArrayNo="0" /></td>
									</tr>
									</ezf:row>
									<ezf:skip>
									</ezf:skip>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td></tr></table>


<%-- **** Task specific area ends here **** --%>
