<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161202131828 --%>
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
<fmt:setBundle basename="I18N_NSAL0060Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSAL0060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0060Scrn00.title" bundle="${I18N_SCREEN_ID}">Model Group Maintenance</fmt:message>">

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
								<tr title="Backup / Standby Schedule Screen">
									<td width="107px" align="center" class="same">Mdl Grp Mnt</td>
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
					<table style="margin-top: 5px; margin-left: 5px" border="0">
						<colgroup>
							<col width="80px">	<!-- Label -->
							<col width="5px">
							<col width="152px">	<!-- Input -->
							<col width="5px">
							<col width="72px">	<!-- Search -->
						</colgroup>
						<tbody>
							<tr>
								<td class="stab"><fmt:message key="i18n.NSAL0060Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Model Group (*)</fmt:message></td>
								<td />
								<td class="stab"><ezf:inputText name="mdlGrpNm_SC" ezfName="mdlGrpNm_SC" value="12345678" otherAttr=" maxlength=\"20\" size=\"20\" ezftoupper=\"\""/></td>
							</tr>
							<tr>
								<td class="stab"><fmt:message key="i18n.NSAL0060Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Description (*)</fmt:message></td>
								<td />
								<td class="stab"><ezf:inputText name="mdlGrpDescTxt_SC" ezfName="mdlGrpDescTxt_SC" value="ZZZZZZZZZ1ZZZZZZZZZ2" htmlClass="stab" otherAttr=" maxlength=\"90\" size=\"50\" ezftoupper=\"\""/></td>
								<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
							</tr>
						</tbody>
					</table>
<!-- ################################################## Search Criteria - [E N D] ################################################## -->
					<hr />
<!-- ################################################## Search Result   - [START] ################################################## -->
					<center>
						<table width="592px" border="0" cellpadding="1" cellspacing="0">
							<tr>
								<td align="right">
<ezf:skip>
									<table border="0" cellpadding="0" width="100%">
										<tr>
											<td align="left">
												<table border="0" cellpadding="0" align="left" cellspacing="0">
													<col>
														<tr>
														<td>Results 999 - 999 of 999</td>
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
														<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
														<td class="stab">/</td>
														<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
														<td class="stab">page</td>
														<td>&nbsp;</td>
														<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
</ezf:skip>
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
									<table width="100%">
										<tr align="right">
											<td>
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
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
								</td>
							</tr>
						</table>

						<table width="592px">
							<tr>
								<td align="left">
									<div id="topTBL" style="overflow-x:hidden; overflow-y:none; width:575px;">
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed" width="575px">
											<colgroup>
												<col align="middle" width="25px">	<!-- checkbox -->
												<col align="middle" width="158px">	<!-- Model Group -->
												<col align="middle" width="392px">	<!-- Description -->
											</colgroup>
											<tbody>
												<tr height="28px">
													<td class="pClothBs">&nbsp;</td>
													<td class="pClothBs"><fmt:message key="i18n.NSAL0060Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Model Group</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NSAL0060Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Description</fmt:message></td>
												</tr>
											</tbody>
										</table>
									</div>
									<div id="bottomTBL" style="overflow-x:hidden; overflow-y:scroll; width:592px; height:394px;" onScroll="synchroScrollLeft(this.id, new Array('topTBL'));">
										<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;word-break:break-all" width="575px">
											<colgroup>
												<col align="middle" width="25px">	<!-- checkbox -->
												<col align="middle" width="158px">	<!-- Model Group -->
												<col align="middle" width="392px">	<!-- Description -->
											</colgroup>
											<tbody>
											<ezf:row ezfHyo="A">
												<tr height="28px">
													<td><ezf:inputCheckBox name="xxChkBox_SR" ezfName="xxChkBox_SR" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox\""/></td>
													<td><ezf:inputText name="mdlGrpNm_SR" ezfName="mdlGrpNm_SR" value="ZZZZZZZZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"20\" size=\"20\" ezftoupper=\"\""/></td>
													<td><ezf:inputText name="mdlGrpDescTxt_SR" ezfName="mdlGrpDescTxt_SR" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"90\" size=\"50\" ezftoupper=\"\""/></td>
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
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
												<tr height="28px">
													<td><input type="checkbox" ezfname="xxChkBox_SR" name="xxChkBox_SR" value="Y" ezfhyo="A" id="xxChkBox"></td>
													<td><input maxLength="20" size="20" name="mdlGrpNm_SR" ezfname="mdlGrpNm_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZ"></td>
													<td><input maxLength="90" size="50" name="mdlGrpDescTxt_SR" ezfname="mdlGrpDescTxt_SR" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6ZZZZZZZZZ7ZZZZZZZZZ8ZZZZZZZZZ9"></td>
												</tr>
											</ezf:skip>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
											<td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" /></td>
											<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" /></td>
										</tr>
									</table>
								</td>
							</td>
						</table>
					</center>
<!-- ################################################## Search Result   - [E N D] ################################################## -->
				</div>
			</td>
		</tr>
	</table>
</center>



<%-- **** Task specific area ends here **** --%>
