<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20150312043836 --%>
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
<fmt:setBundle basename="I18N_NMAL6040Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NMAL6040Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL6040Scrn00.title" bundle="${I18N_SCREEN_ID}">P&L Product Structure Pop Up</fmt:message>">
<%@ page import="business.servlet.NMAL6040.NMAL6040BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<% NMAL6040BMsg bMsg = (NMAL6040BMsg)databean.getEZDBMsg(); %>
<center>
	<table>
		<tr>
			<td align="center">
<%-- ######################################## HEADER ######################################## --%>
<%-- MOD START 2013/08/22 Defect#1290 --%>
				<table border="0" cellpadding="1" cellspacing="0">
					<col width="140">
					<col width="110">
					<col width="5">
					<col width="143">
					<col width="150">
					<col width="5">
					<col width="90">
					<col width="20">
					<col width="5">
					<tr>
						<td class="stab"><ezf:label name="mdseStruElmntTpNm_L1" ezfName="mdseStruElmntTpNm_L1" /> Code (*)</td>
						<td><ezf:inputText name="zerothProdCtrlCd" ezfName="zerothProdCtrlCd" otherAttr=" size=\"16\" maxlength=\"8\" ezftoupper=\"\""/></td>
						<td>&nbsp;</td>
						<td class="stab"><ezf:label name="mdseStruElmntTpNm_L1" ezfName="mdseStruElmntTpNm_L1" /> Name (*)</td>
						<td><ezf:inputText name="zerothProdCtrlNm" ezfName="zerothProdCtrlNm" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
						<td>&nbsp;</td>
						<td class="stab"></td>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td class="stab"><ezf:label name="mdseStruElmntTpNm_L2" ezfName="mdseStruElmntTpNm_L2" /> Code (*)</td>
						<td><ezf:inputText name="firstProdCtrlCd" ezfName="firstProdCtrlCd" otherAttr=" size=\"16\" maxlength=\"8\" ezftoupper=\"\""/></td>
						<td>&nbsp;</td>
						<td class="stab"><ezf:label name="mdseStruElmntTpNm_L2" ezfName="mdseStruElmntTpNm_L2" /> Name (*)</td>
						<td><ezf:inputText name="firstProdCtrlNm" ezfName="firstProdCtrlNm" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
						<td>&nbsp;</td>
						<td class="stab"></td>
						<td colspan="2">
						</td>
					</tr>
					<tr>
						<td class="stab"><ezf:label name="mdseStruElmntTpNm_L3" ezfName="mdseStruElmntTpNm_L3" /> Code (*)</td>
						<td><ezf:inputText name="scdProdCtrlCd" ezfName="scdProdCtrlCd" otherAttr=" size=\"16\" maxlength=\"8\" ezftoupper=\"\""/></td>
						<td>&nbsp;</td>
						<td class="stab"><ezf:label name="mdseStruElmntTpNm_L3" ezfName="mdseStruElmntTpNm_L3" /> Name (*)</td>
						<td><ezf:inputText name="scdProdCtrlNm" ezfName="scdProdCtrlNm" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
						<td>&nbsp;</td>
						<td class="stab"></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="stab"><ezf:label name="mdseStruElmntTpNm_L4" ezfName="mdseStruElmntTpNm_L4" /> Code (*)</td>
						<td><ezf:inputText name="thirdProdCtrlCd" ezfName="thirdProdCtrlCd" otherAttr=" size=\"16\" maxlength=\"8\" ezftoupper=\"\""/></td>
						<td>&nbsp;</td>
						<td class="stab"><ezf:label name="mdseStruElmntTpNm_L4" ezfName="mdseStruElmntTpNm_L4" /> Name (*)</td>
						<td><ezf:inputText name="thirdProdCtrlNm" ezfName="thirdProdCtrlNm" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
						<td>&nbsp;</td>
						<td class="stab"></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="stab"><ezf:label name="mdseStruElmntTpNm_L5" ezfName="mdseStruElmntTpNm_L5" /> Code (*)</td>
						<td><ezf:inputText name="frthProdCtrlCd" ezfName="frthProdCtrlCd" otherAttr=" size=\"16\" maxlength=\"8\" ezftoupper=\"\""/></td>
						<td>&nbsp;</td>
						<td class="stab"><ezf:label name="mdseStruElmntTpNm_L5" ezfName="mdseStruElmntTpNm_L5" /> Name (*)</td>
						<td><ezf:inputText name="frthProdCtrlNm" ezfName="frthProdCtrlNm" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
						<td>&nbsp;</td>
						<td class="stab"></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="stab"><fmt:message key="i18n.NMAL6040Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Merchandise Code (*)</fmt:message></td>
						<td><ezf:inputText name="mdseCd" ezfName="mdseCd" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
						<td>&nbsp;</td>
						<td class="stab"><fmt:message key="i18n.NMAL6040Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Merchandise Name (*)</fmt:message></td>
						<td ><ezf:inputText name="mdseNm" ezfName="mdseNm" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
						<td colspan="3"><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" /><fmt:message key="i18n.NMAL6040Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Include MDSE Code</fmt:message></td>
						<td><ezf:inputButton name="Search_Merchandise" value="Search" htmlClass="pBtn6" /></td>
					</tr>
				</table>
<%-- MOD END 2013/08/22 Defect#1290 --%>
				<hr>
<%-- ######################################## PAGE ######################################## --%>
				<table width="100%">
					<tr align="right">
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
<%-- ######################################## DETAIL ######################################## --%>
				<table border="0" cellpadding="1" cellspacing="0" width="">
					<tr>
						<td valign="top">
							
							<%-- ### MEISAI - HEAD --%>
							<div id="topTBL" style="overflow-y:none; height:20; overflow-x:hidden; width:980;" onScroll="synchroScrollLeft(this.id, new Array('bottomTBL'));">
								<table border="1" cellpadding="1" cellspacing="0" width="980" style="table-layout: fixed;">
									<col width="127" align="center">
									<col width="127" align="center">
									<col width="127" align="center">
									<col width="127" align="center">
									<col width="127" align="center">
									<col width="127" align="center">
									<col width="91"  align="center">
									<col width="160" align="center">
									
									<tr>
										<td class="pClothBs"><ezf:label name="mdseStruElmntTpNm_L1" ezfName="mdseStruElmntTpNm_L1" /></td>
										<td class="pClothBs"><ezf:label name="mdseStruElmntTpNm_L2" ezfName="mdseStruElmntTpNm_L2" /></td>
										<td class="pClothBs"><ezf:label name="mdseStruElmntTpNm_L3" ezfName="mdseStruElmntTpNm_L3" /></td>
										<td class="pClothBs"><ezf:label name="mdseStruElmntTpNm_L4" ezfName="mdseStruElmntTpNm_L4" /></td>
										<td class="pClothBs"><ezf:label name="mdseStruElmntTpNm_L5" ezfName="mdseStruElmntTpNm_L5" /></td>
										<td class="pClothBs"><fmt:message key="i18n.NMAL6040Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Merchandise</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NMAL6040Scrn00.label.25" bundle="${I18N_SCREEN_ID}">UPC</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NMAL6040Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Merchandise</fmt:message></td>
									</tr>
									<tr>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'zerothProdCtrlCd_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Code</fmt:message><img id="sortIMG.zerothProdCtrlCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'firstProdCtrlCd_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Code</fmt:message><img id="sortIMG.firstProdCtrlCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'scdProdCtrlCd_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Code</fmt:message><img id="sortIMG.scdProdCtrlCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'thirdProdCtrlCd_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Code</fmt:message><img id="sortIMG.thirdProdCtrlCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'frthProdCtrlCd_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Code</fmt:message><img id="sortIMG.frthProdCtrlCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCd_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Code</fmt:message><img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'upcCd_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Code</fmt:message><img id="sortIMG.upcCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCatgNm_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.27" bundle="${I18N_SCREEN_ID}">Category</fmt:message><img id="sortIMG.mdseCatgNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									</tr>
									<tr>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'zerothProdCtrlNm_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Name</fmt:message><img id="sortIMG.zerothProdCtrlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'firstProdCtrlNm_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Name</fmt:message><img id="sortIMG.firstProdCtrlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'scdProdCtrlNm_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Name</fmt:message><img id="sortIMG.scdProdCtrlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'thirdProdCtrlNm_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Name</fmt:message><img id="sortIMG.thirdProdCtrlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'frthProdCtrlNm_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Name</fmt:message><img id="sortIMG.frthProdCtrlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseNm_A1' )"><fmt:message key="i18n.NMAL6040Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Name</fmt:message><img id="sortIMG.mdseNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									</tr>
								</table>
							</div>

							<%-- ### MEISAI - DETAIL --%>
							<div id="bottomTBL" style="word-break:break-all; height:282; overflow-y:scroll; overflow-x:scroll; width:1000;" onscroll="synchroScrollLeft(this.id, new Array('topTBL'));">
								<table border="1" cellpadding="1" cellspacing="0" width="980" id="A" style="table-layout: fixed;">
									<col width="127" align="center">
									<col width="127" align="center">
									<col width="127" align="center">
									<col width="127" align="center">
									<col width="127" align="center">
									<col width="127" align="center">
									<col width="91"  align="center">
									<col width="160" align="center">

									<ezf:row ezfHyo="A">
									<tr>
									<% if (ZYPCommonFunc.hasValue(bMsg.xxModeCd)) { %>
										<td><ezf:label name="zerothProdCtrlCd_A1"ezfName="zerothProdCtrlCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="firstProdCtrlCd_A1" ezfName="firstProdCtrlCd_A1"  ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="scdProdCtrlCd_A1"   ezfName="scdProdCtrlCd_A1"    ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="thirdProdCtrlCd_A1" ezfName="thirdProdCtrlCd_A1"  ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="frthProdCtrlCd_A1"  ezfName="frthProdCtrlCd_A1"   ezfHyo="A" ezfArrayNo="0" /></td>
									<% } else { %>
										<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_ProductLineGroupCD" ><ezf:label name="zerothProdCtrlCd_A1" ezfName="zerothProdCtrlCd_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
										<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_ProductLineCD"      ><ezf:label name="firstProdCtrlCd_A1"  ezfName="firstProdCtrlCd_A1"  ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
										<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_ProductLevel2CD"    ><ezf:label name="scdProdCtrlCd_A1"    ezfName="scdProdCtrlCd_A1"    ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
										<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_ProductLevel3CD"    ><ezf:label name="thirdProdCtrlCd_A1"  ezfName="thirdProdCtrlCd_A1"  ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
										<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_ProductLevel4CD"    ><ezf:label name="frthProdCtrlCd_A1"   ezfName="frthProdCtrlCd_A1"   ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
									<% } %>
									<% if (ZYPCommonFunc.hasValue(bMsg.xxChkBox)) {
										  if (ZYPCommonFunc.hasValue(bMsg.xxModeCd)) { %>
											<td><ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0"/></td>
										<% } else { %>
											<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_MerchandiseCD" ><ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0"/></ezf:anchor></td>
										<% }
									   } else { %>
										<td>&nbsp;</td>
									<% } %>
										<td rowspan="2">
										    <ezf:label name="upcCd_A1"          ezfName="upcCd_A1"         ezfHyo="A" ezfArrayNo="0" /></td>
										<td rowspan="2">
										    <ezf:label name="mdseCatgNm_A1"     ezfName="mdseCatgNm_A1"    ezfHyo="A" ezfArrayNo="0" /></td>
									</tr>
									<tr>
										<td><ezf:label name="zerothProdCtrlNm_A1" ezfName="zerothProdCtrlNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="firstProdCtrlNm_A1"  ezfName="firstProdCtrlNm_A1"  ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="scdProdCtrlNm_A1"    ezfName="scdProdCtrlNm_A1"    ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="thirdProdCtrlNm_A1"  ezfName="thirdProdCtrlNm_A1"  ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="frthProdCtrlNm_A1"   ezfName="frthProdCtrlNm_A1"   ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="mdseNm_A1"           ezfName="mdseNm_A1"           ezfHyo="A" ezfArrayNo="0" /></td>
									</tr>
									</ezf:row>
									<ezf:skip>
									<tr class="pEvenNumberBGcolor">
										<td><a href="#"><label ezfout>111</label></a></td>
										<td><a href="#"><label ezfout>AONS</label></a></td>
										<td><a href="#"><label ezfout>Z001</label></a></td>
										<td><a href="#"><label ezfout>POL00</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td rowspan="2"><label ezfout>123456789012</label></td>
										<td rowspan="2"><label ezfout>Machine</label></td>
										
									</tr>
									<tr class="pEvenNumberBGcolor">
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>Memory</label></td>
										<td><label ezfout>123456</label></td>
										<td><label ezfout>123456</label></td>
									</tr>
									<tr>
										<td><a href="#"><label ezfout>111</label></a></td>
										<td><a href="#"><label ezfout>AONS</label></a></td>
										<td><a href="#"><label ezfout>Z001</label></a></td>
										<td><a href="#"><label ezfout>POL00</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td rowspan="2"><label ezfout>123456789012</label></td>
										<td rowspan="2"><label ezfout>Machine</label></td>
									</tr>
									<tr>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>Memory</label></td>
										<td><label ezfout>123456</label></td>
										<td><label ezfout>123456</label></td>
									</tr>
									<tr class="pEvenNumberBGcolor">
										<td><a href="#"><label ezfout>111</label></a></td>
										<td><a href="#"><label ezfout>AONS</label></a></td>
										<td><a href="#"><label ezfout>Z001</label></a></td>
										<td><a href="#"><label ezfout>POL00</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td rowspan="2"><label ezfout>123456789012</label></td>
										<td rowspan="2"><label ezfout>Machine</label></td>
									</tr>
									<tr class="pEvenNumberBGcolor">
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>Memory</label></td>
										<td><label ezfout>123456</label></td>
										<td><label ezfout>123456</label></td>
									</tr>
									<tr>
										<td><a href="#"><label ezfout>111</label></a></td>
										<td><a href="#"><label ezfout>AONS</label></a></td>
										<td><a href="#"><label ezfout>Z001</label></a></td>
										<td><a href="#"><label ezfout>POL00</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td rowspan="2"><label ezfout>123456789012</label></td>
										<td rowspan="2"><label ezfout>Machine</label></td>
									</tr>
									<tr>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>Memory</label></td>
										<td><label ezfout>123456</label></td>
										<td><label ezfout>123456</label></td>
									</tr>
									<tr class="pEvenNumberBGcolor">
										<td><a href="#"><label ezfout>111</label></a></td>
										<td><a href="#"><label ezfout>AONS</label></a></td>
										<td><a href="#"><label ezfout>Z001</label></a></td>
										<td><a href="#"><label ezfout>POL00</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td rowspan="2"><label ezfout>123456789012</label></td>
										<td rowspan="2"><label ezfout>Machine</label></td>
									</tr>
									<tr class="pEvenNumberBGcolor">
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>Memory</label></td>
										<td><label ezfout>123456</label></td>
										<td><label ezfout>123456</label></td>
									</tr>
									<tr>
										<td><a href="#"><label ezfout>111</label></a></td>
										<td><a href="#"><label ezfout>AONS</label></a></td>
										<td><a href="#"><label ezfout>Z001</label></a></td>
										<td><a href="#"><label ezfout>POL00</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td rowspan="2"><label ezfout>123456789012</label></td>
										<td rowspan="2"><label ezfout>Machine</label></td>
									</tr>
									<tr>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>Memory</label></td>
										<td><label ezfout>123456</label></td>
										<td><label ezfout>123456</label></td>
									</tr>
									<tr class="pEvenNumberBGcolor">
										<td><a href="#"><label ezfout>111</label></a></td>
										<td><a href="#"><label ezfout>AONS</label></a></td>
										<td><a href="#"><label ezfout>Z001</label></a></td>
										<td><a href="#"><label ezfout>POL00</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td rowspan="2"><label ezfout>123456789012</label></td>
										<td rowspan="2"><label ezfout>Machine</label></td>
									</tr>
									<tr class="pEvenNumberBGcolor">
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>Memory</label></td>
										<td><label ezfout>123456</label></td>
										<td><label ezfout>123456</label></td>
									</tr>
									<tr>
										<td><a href="#"><label ezfout>111</label></a></td>
										<td><a href="#"><label ezfout>AONS</label></a></td>
										<td><a href="#"><label ezfout>Z001</label></a></td>
										<td><a href="#"><label ezfout>POL00</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td rowspan="2"><label ezfout>123456789012</label></td>
										<td rowspan="2"><label ezfout>Machine</label></td>
									</tr>
									<tr>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>Memory</label></td>
										<td><label ezfout>123456</label></td>
										<td><label ezfout>123456</label></td>
									</tr>
									<tr class="pEvenNumberBGcolor">
										<td><a href="#"><label ezfout>111</label></a></td>
										<td><a href="#"><label ezfout>AONS</label></a></td>
										<td><a href="#"><label ezfout>Z001</label></a></td>
										<td><a href="#"><label ezfout>POL00</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td><a href="#"><label ezfout>KIU7</label></a></td>
										<td rowspan="2"><label ezfout>123456789012</label></td>
										<td rowspan="2"><label ezfout>Machine</label></td>
									</tr>
									<tr class="pEvenNumberBGcolor">
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>WWWWWW</label></td>
										<td><label ezfout>Memory</label></td>
										<td><label ezfout>123456</label></td>
										<td><label ezfout>123456</label></td>
									</tr>
									<tr>
										<td><a href="#"><label ezfout>01234567</label></a></td>
										<td><a href="#"><label ezfout>01234567</label></a></td>
										<td><a href="#"><label ezfout>01234567</label></a></td>
										<td><a href="#"><label ezfout>01234567</label></a></td>
										<td><a href="#"><label ezfout>01234567</label></a></td>
										<td><a href="#"><label ezfout>0123456701234567</label></a></td>
										<td rowspan="2"><label ezfout>123456789012</label></td>
										<td rowspan="2"><label ezfout>012345678901234567890</label></td>
									</tr>
									<tr>
										<td><label ezfout>01234567890123456789012345678901234567890123456789</label></td>
										<td><label ezfout>01234567890123456789012345678901234567890123456789</label></td>
										<td><label ezfout>01234567890123456789012345678901234567890123456789</label></td>
										<td><label ezfout>01234567890123456789012345678901234567890123456789</label></td>
										<td><label ezfout>01234567890123456789012345678901234567890123456789</label></td>
										<td><label ezfout>012345678901234567890123456789</label></td>
									</tr>
									</ezf:skip>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
