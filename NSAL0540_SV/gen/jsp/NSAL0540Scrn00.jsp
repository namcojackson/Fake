<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160921144850 --%>
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
<fmt:setBundle basename="I18N_NSAL0540Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSAL0540Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0540Scrn00.title" bundle="${I18N_SCREEN_ID}">Solution Maintenance</fmt:message>">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="<fmt:message key="i18n.NSAL0540Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Solution Maintenance</fmt:message>" class="pTab_ON"><a href="#">Sln Maint</a></li>
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
				<div class="pTab_BODY">
<!-- ######################################## Header ######################################## -->
					<table border="0">
						<col width="450">
						<col width="670">
						<tr>
							<td>
								<table border="0">
									<col width="80">
									<col width="360">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0540Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Solution#</fmt:message></td>
										<td><ezf:inputText name="svcSlnSq" ezfName="svcSlnSq" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWW" otherAttr=" size=\"15\" maxlength=\"28\""/></td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0540Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Solution Name</fmt:message></td>
										<td><ezf:inputText name="svcSlnNm" ezfName="svcSlnNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8WWWWWWWWW9WWWWWWWWWA" otherAttr=" size=\"50\" maxlength=\"100\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>
							<td>
								<table border="0">
									<col width="80">
									<col width="590">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0540Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Solution Note</fmt:message></td>
										<td><ezf:textArea name="svcCmntTxt" ezfName="svcCmntTxt" otherAttr=" cols=\"80\" rows=\"4\" maxlength=\"4000\""/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<hr>

<!-- ######################################## Detail ######################################## -->
					<table>
						<cal width="70">
						<cal width="70">
						<cal width="980" align="right">
						<cal width="100">
						<tr>
							<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn7" /></td>
							<td><ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn7" /></td>
							<td align="right" width="935">
								<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
								</jsp:include>
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
										<td class="pOut">1000</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
									</tr>
								</table>
								</ezf:skip>
							</td>
						</tr>
					</table>

					<table  border="0" cellpadding="0" cellspacing="0" width="100%">
						<col width="" align="" valign="top">
						<tr>
							<td align="right" valign="top" width="592">
								<%-- Left Table Header --%>
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
									<col width="25"  align="center">	<!-- &nbsp; -->
									<col width="120" align="center">	<!-- Solution# -->
									<col width="80" align="center">		<!-- Config# -->
									<col width="80" align="center">		<!-- Serial# -->
									<col width="80" align="center">		<!-- Mdse Code -->
									<col width="120" align="center">	<!-- Mdse Name -->
									<col width="80" align="center">		<!-- Svc Model Name -->
									<tr height="37">
										<td class="pClothBs">&nbsp;</td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0540Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Solution#</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0540Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Config#</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0540Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0540Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Item Code</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0540Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Item Name</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0540Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Svc Model Name</fmt:message></td>
									</tr>
								</table>
								<%-- Left Table Detail --%>
								<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:377;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A1" >
										<col width="25"  align="center">	<!-- &nbsp; -->
										<col width="120" align="center">	<!-- Solution# -->
										<col width="80" align="center">		<!-- Config# -->
										<col width="80" align="center">		<!-- Serial# -->
										<col width="80" align="center">		<!-- Mdse Code -->
										<col width="120" align="center">	<!-- Mdse Name -->
										<col width="80" align="center">		<!-- Svc Model Name -->
										<ezf:row ezfHyo="A">
										<tr height="25">
											<td><ezf:inputCheckBox name="xxChkBox_A0" ezfName="xxChkBox_A0" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A0#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="svcSlnSq_A0" ezfName="svcSlnSq_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"28\""/></td>
											<td><ezf:inputText name="svcConfigMstrPk_A0" ezfName="svcConfigMstrPk_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\""/></td>
											<td><ezf:inputText name="serNum_A0" ezfName="serNum_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\""/></td>
											<td><ezf:inputText name="mdseCd_A0" ezfName="mdseCd_A0" value="WWWWWWWWW1WWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"16\""/></td>
											<td><ezf:inputText name="mdseDescShortTxt_A0" ezfName="mdseDescShortTxt_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"250\""/></td>
											<td><ezf:inputText name="t_MdlNm_A0" ezfName="t_MdlNm_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" id=\"t_MdlNm_A0#{EZF_ROW_NUMBER}\""/></td>
										</tr>
										</ezf:row>
										<ezf:skip>
										</ezf:skip>
									</table>
								</div>
							</td>
							<td align="left" valign="top">
								<%-- Right Table Header --%>
								<div id="topRightTBL" style="overflow-x:hidden; width:510; overflow-y:none; height:20;">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
										<col width="80" align="center">		<!-- IB ID -->
										<col width="80" align="center">		<!-- Parent IB ID -->
										<col width="80" align="center">		<!-- Install Date -->
										<col width="120" align="center">	<!-- IB Owner -->
										<col width="120" align="center">	<!-- IB Current Location -->
										<col width="120" align="center">	<!-- IB Bill To -->
										<tr height="37">
											<td class="pClothBs"><fmt:message key="i18n.NSAL0540Scrn00.label.9" bundle="${I18N_SCREEN_ID}">IB ID</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSAL0540Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Parent IB ID</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSAL0540Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Install Date</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSAL0540Scrn00.label.12" bundle="${I18N_SCREEN_ID}">IB Owner</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSAL0540Scrn00.label.13" bundle="${I18N_SCREEN_ID}">IB Current Location</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NSAL0540Scrn00.label.14" bundle="${I18N_SCREEN_ID}">IB Bill To</fmt:message></td>
										</tr>
									</table>
								</div>
								<%-- Right Table Detail --%>
								<div id="RightTBL" style="overflow-x:scroll; width:527; overflow-y:scroll; height:395;" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) ); synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A2">
										<col width="80" align="center">		<!-- IB ID -->
										<col width="80" align="center">		<!-- Parent IB ID -->
										<col width="80">					<!-- Install Date -->
										<col width="120" align="center">	<!-- IB Owner -->
										<col width="120" align="center">	<!-- IB Current Location -->
										<col width="120" align="center">	<!-- IB Bill To -->
										<ezf:row ezfHyo="A">
										<tr height="25">
											<td><ezf:inputText name="svcMachMstrPk_A0" ezfName="svcMachMstrPk_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\" id=\"svcMachMstrPk_A0#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="svcMachMstrPk_AP" ezfName="svcMachMstrPk_AP" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\""/></td>
											<td><ezf:label name="istlDt_A0" ezfName="istlDt_A0" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="locNm_A0" ezfName="locNm_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
											<td><ezf:inputText name="locNm_A1" ezfName="locNm_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
											<td><ezf:inputText name="locNm_A2" ezfName="locNm_A2" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\" id=\"locNm_A2#{EZF_ROW_NUMBER}\""/></td>
										</tr>
										</ezf:row>
										<ezf:skip>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
					</table>
					
					<table>
						<cal width="120">
						<cal width="120">
						<tr>
							<td><ezf:inputButton name="LinkConfig" value="Link Configuration(s)" htmlClass="pBtn11" /></td>
							<td><ezf:inputButton name="DeleteConfig" value="Delete Configuration(s)" htmlClass="pBtn11" /></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
