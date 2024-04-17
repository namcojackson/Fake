<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20150327064804 --%>
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
<fmt:setBundle basename="I18N_NMAL6830Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NMAL6830Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL6830Scrn00.title" bundle="${I18N_SCREEN_ID}">Item Cost Update Group Popup</fmt:message>">

			<table border="0" cellpadding="1" cellspacing="0" width="99%">
				<col width="140">
				<col width="170">
				<col width="10">
				<col width="100">
				<col width="170">
				<col width="70">
				<tr>
					<td class="stab">Cost Update Group(*)</td><!-- MDSE_CST_UPD_GRP_TXT -->
					<td><ezf:inputText name="mdseCstUpdGrpTxt_H1" ezfName="mdseCstUpdGrpTxt_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
					<td></td>
					<td class="stab">Cost Update Type</td><!--MDSE_CST_UPD_TP_CD -->
					<td>
						<ezf:select name="mdseCstUpdTpCd_H1" ezfName="mdseCstUpdTpCd_H1" ezfBlank="1" ezfCodeName="mdseCstUpdTpCd_HL" ezfDispName="mdseCstUpdTpNm_HL" otherAttr=" style=\"width:215px\""/>
					</td>
					<td></td>
				</tr>
				<tr height="25px"> 
					<td class="stab" >Cost Update Description(*)</td><!-- MDSE_CST_UPD_DESC_TXT -->
					<td><ezf:inputText name="mdseCstUpdDescTxt_H1" ezfName="mdseCstUpdDescTxt_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
					<td></td>
					<td class="stab" >Reference(*)</td><!-- MDSE_CST_UPD_REF_TXT -->
					<td><ezf:inputText name="mdseCstUpdRefTxt_H1" ezfName="mdseCstUpdRefTxt_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
					<td></td>
				</tr>
				<tr>
					<td class="stab" >Reference Id(*)</td><!-- MDSE_CST_UPD_REF_ID -->
					<td><ezf:inputText name="mdseCstUpdRefId_H1" ezfName="mdseCstUpdRefId_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
					<td></td>
					<td></td>
					<td></td>
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

				<div id="topTBL" style="overflow-x:hidden; width:780;" align="left">
					<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="942px">
						<col width="200px">		<!-- MDSE_CST_UPD_GRP_TXT -->
						<col width="120px">		<!-- MDSE_CST_UPD_TP_NM -->
						<col width="200px">		<!-- MDSE_CST_UPD_DESC_TXT -->
						<col width="200px">		<!-- MDSE_CST_UPD_REF_TXT -->
						<col width="200px">		<!-- MDSE_CST_UPD_REF_ID -->
						<col width="23px">		<!-- Scroll -->
						<tr>
							<td class="pClothBs" align="center">
								<a href="#" class="pSortCol" onclick="columnSort('A','mdseCstUpdGrpTxt_A1')"><fmt:message key="i18n.NMAL6830Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Cost Update Group</fmt:message><img id="sortIMG.mdseCstUpdGrpTxt_A1" border="0" src="./img/tableSort/asc.gif" style="visibility: hidden">
								</a>
							</td>
							<td class="pClothBs" align="center">
								<a href="#" class="pSortCol" onclick="columnSort('A','mdseCstUpdTpNm_A1')"><fmt:message key="i18n.NMAL6830Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Cost Update Type</fmt:message><img id="sortIMG.mdseCstUpdTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="visibility: hidden">
								</a>
							</td>
							<td class="pClothBs" align="center">
								<a href="#" class="pSortCol" onclick="columnSort('A','mdseCstUpdDescTxt_A1')"><fmt:message key="i18n.NMAL6830Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Cost Update Description</fmt:message><img id="sortIMG.mdseCstUpdDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="visibility: hidden">
								</a>
							</td>
							<td class="pClothBs" align="center">
								<a href="#" class="pSortCol" onclick="columnSort('A','mdseCstUpdRefTxt_A1')"><fmt:message key="i18n.NMAL6830Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Cost Update Reference</fmt:message><img id="sortIMG.mdseCstUpdRefTxt_A1" border="0" src="./img/tableSort/asc.gif" style="visibility: hidden">
								</a>
							</td>
							<td class="pClothBs" align="center">
								<a href="#" class="pSortCol" onclick="columnSort('A','mdseCstUpdRefId_A1')"><fmt:message key="i18n.NMAL6830Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Cost Update Reference Id</fmt:message><img id="sortIMG.mdseCstUpdRefId_A1" border="0" src="./img/tableSort/asc.gif" style="visibility: hidden">
								</a>
							</td>
						</tr>
					</table>                         
				</div>
				<div id="bottomTBL" style="overflow-x:scroll; overflow-y:scroll; height:439px; width:780px;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
					<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" style="word-break:break-all;" id="A" width="920px">
						<col width="200px">		<!-- MDSE_CST_UPD_GRP_TXT -->
						<col width="120px">		<!-- MDSE_CST_UPD_TP_NM -->
						<col width="200px">		<!-- MDSE_CST_UPD_DESC_TXT -->
						<col width="200px">		<!-- MDSE_CST_UPD_REF_TXT -->
						<col width="200px">		<!-- MDSE_CST_UPD_REF_ID -->
						<ezf:row ezfHyo="A">
						<tr id="{EZF_ROW_NUMBER}">
							<td align="center"><ezf:anchor name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_Group" otherAttr=" ezfanchortext=\"\""><ezf:label name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
							<td><ezf:label name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="mdseCstUpdDescTxt_A1" ezfName="mdseCstUpdDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="mdseCstUpdRefTxt_A1" ezfName="mdseCstUpdRefTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
							<td><ezf:label name="mdseCstUpdRefId_A1" ezfName="mdseCstUpdRefId_A1" ezfHyo="A" ezfArrayNo="0" /></td>
						</tr>
						</ezf:row>
						<ezf:skip>    
						

						</ezf:skip>
					</table>
				</div>
			</center>


<%-- **** Task specific area ends here **** --%>
