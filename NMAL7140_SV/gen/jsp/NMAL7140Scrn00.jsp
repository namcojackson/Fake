<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20200109143840 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NMAL7140Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Marketing Programs Setup Filter Popup">
<!-- Marketing Programs Setup Filter Popup HTML start -->
<%@ page import="business.servlet.NMAL7140.NMAL7140BMsg" %>
<% NMAL7140BMsg bMsg = (NMAL7140BMsg) databean.getEZDBMsg(); %>
<center>
	<div style="height:560px; table-layout:fixed; overflow:scroll;">
	<table cellpadding="1" border="0" style="margin-top:4px;" id="mainTbl">
		<col align="left" width="130">
		<col align="left" width="450">
		<tr>
			<td class="stab">Marketing Program Code(*)</td>
			<td>
				<ezf:inputText name="prcMktPrmoCd" ezfName="prcMktPrmoCd" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Promotion Qualifier</td>
			<td>
				<ezf:select name="prcPrmoQlfyTpCd" ezfName="prcPrmoQlfyTpCd" ezfBlank="1" ezfCodeName="prcPrmoQlfyTpCd_L" ezfDispName="prcPrmoQlfyTpDescTxt_L" otherEvent1=" onchange=\"sendServer('OnChange_PrcPrmoQlfyTp')\"" />
			</td>
		</tr>
		<tr>
			<td class="stab">Qualifier Value(*)</td>
			<td>
				<ezf:inputText name="prcQlfyValTxt" ezfName="prcQlfyValTxt" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/>
				<% boolean isDispItemBtn = "I".equals(bMsg.xxBtnFlg.getValue()); %>
				<% boolean isDispMdlBtn = "M".equals(bMsg.xxBtnFlg.getValue()); %>
				<% if (isDispItemBtn) { %>
				<ezf:inputButton name="OpenWin_PrmoQlfy_Item" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrmoQlfy_Item\""/>
				<% } else if (isDispMdlBtn) { %>
				<ezf:inputButton name="OpenWin_PrmoQlfy_Mdl" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrmoQlfy_Mdl\""/>
				<% } %>
			</td>
		</tr>
		<tr>
			<td class="stab">Promotion Item Code#(*)</td>
			<td>
				<ezf:inputText name="mdseCd" ezfName="mdseCd" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_Prmo_Item" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Prmo_Item\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Item Description(*)</td>
			<td>
				<ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Promotion Value</td>
			<td>
				<ezf:inputText name="prmoAmt" ezfName="prmoAmt" value="-123,456,789.12" otherAttr=" size=\"20\" maxlength=\"19\" style=\"text-align:right\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Date From</td>
			<td>
				<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);">
				-
				<ezf:inputText name="effFromDt_H2" ezfName="effFromDt_H2" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H2', 4);">
			</td>
		</tr>

	</table>
	</div>
</center>
<!-- Marketing Programs Setup Filter Popup HTML end -->

<%-- **** Task specific area ends here **** --%>
