<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20150328004952 --%>
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
<fmt:setBundle basename="I18N_NMAL6810Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NMAL6810Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL6810Scrn00.title" bundle="${I18N_SCREEN_ID}">Item Master Template Popup</fmt:message>">

			<table border="0" cellpadding="1" cellspacing="0" width="99%">
				<col width="80">
				<col width="160">
				<col width="160">
				<tr height="25px"> <!-- Item Type -->
					<td class="stab"><fmt:message key="i18n.NMAL6810Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Item&nbsp;Type</fmt:message></td>
					<td>
						<ezf:select name="mdseItemTpCd_HS" ezfName="mdseItemTpCd_HS" ezfBlank="1" ezfCodeName="mdseItemTpCd_HL" ezfDispName="mdseItemTpDescTxt_HL" otherAttr=" style=\"width:200px\""/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td class="stab"><fmt:message key="i18n.NMAL6810Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Template&nbsp;Name(*)</fmt:message></td>
					<td><ezf:inputText name="mdseCratTmplNm_H" ezfName="mdseCratTmplNm_H" value="123456789a12345789b12345789c12345789d12345789e12345789f" otherAttr=" size=\"60\" maxlength=\"50\" ezftoupper=\"\""/></td>
					<td class="stab" align="right"> <!-- Search -->
						<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
					</td>
				</tr>
			</table>
			<hr>
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
			<div align="right">
					<!-- Prev/Next Page-->
				<table>
					<tr align="right">
<ezf:skip>
                        <td>&nbsp;</td>
                        <td class="stab">Showing</td>
                        <td class="pOut">1</td>
                        <td class="stab">to</td>
                        <td class="pOut">200</td>
                        <td class="stab">of</td>
                        <td class="pOut">1000</td>
                        <td>&nbsp;</td>
                        <td><input class="pBtn3" disabled onclick="sendServer(this)" type="button" value="Prev" name="PagePrev"></td>
                        <td></td>
                        <td><input class="pBtn3" onclick="sendServer(this)" type="button" value="Next" name="PageNext"></td>
</ezf:skip>

						<td>
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
			</div>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
			<center>

				<div id="Top" style="overflow-x:hidden; width:657;" align="left">
					<table border="1" cellpadding="1" cellspacing="0" width="640px">
						<col width="80px">		<!-- PK -->
						<col width="160px">		<!-- Item Type -->
						<col width="400px">		<!-- Template Name -->
						<tr>
							<td class="pClothBs" align="center">
								<a href="#" class="pSortCol" onclick="columnSort('A','mdseCratTmplPk_A1')"><fmt:message key="i18n.NMAL6810Scrn00.label.3" bundle="${I18N_SCREEN_ID}">PK</fmt:message><img id="sortIMG.mdseCratTmplPk_A1" border="0" src="./img/tableSort/asc.gif" style="visibility: hidden">
								</a>
							</td>
							<td class="pClothBs" align="center">
								<a href="#" class="pSortCol" onclick="columnSort('A','mdseItemTpDescTxt_A1')"><fmt:message key="i18n.NMAL6810Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Item&nbsp;Name</fmt:message><img id="sortIMG.mdseItemTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="visibility: hidden">
								</a>
							</td>
							<td class="pClothBs" align="center">
								<a href="#" class="pSortCol" onclick="columnSort('A','mdseCratTmplNm_A1')"><fmt:message key="i18n.NMAL6810Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Template&nbsp;Name</fmt:message><img id="sortIMG.mdseCratTmplNm_A1" border="0" src="./img/tableSort/asc.gif" style="visibility: hidden">
								</a>
							</td>
						</tr>
					</table>                         
				</div>
				<div style="overflow-y:scroll; height:439px; overflow-x:none; width:657px;">
					<table border="1" cellpadding="1" cellspacing="0" style="word-break:break-all;" id="A">
						<col width="80px">		<!-- PK -->
						<col width="160px">		<!-- Item Type -->
						<col width="400px">		<!-- Template Name -->
						<ezf:row ezfHyo="A">
						<tr id="{EZF_ROW_NUMBER}">
							<td align="center"><ezf:anchor name="mdseCratTmplPk_A1" ezfName="mdseCratTmplPk_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Template" otherAttr=" ezfanchortext=\"\""><ezf:label name="mdseCratTmplPk_A1" ezfName="mdseCratTmplPk_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
							<td><ezf:label name="mdseItemTpDescTxt_A1" ezfName="mdseItemTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="mdseCratTmplNm_A1" ezfName="mdseCratTmplNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
						</tr>
						</ezf:row>
						<ezf:skip>    
						

						</ezf:skip>
					</table>
				</div>
			</center>


<%-- **** Task specific area ends here **** --%>
