<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230421095748 --%>
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
			<input type="hidden" name="pageID" value="NMAL7440Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Group Filter Popup">

<center>
	<div style="height:560px; table-layout:fixed; overflow:scroll;">
	<table cellpadding="1" border="0" style="margin-top:4px;" id="mainTbl">
		<col align="left" width="130">
		<col align="left" width="480">
		<tr>
			<td class="stab">Pricing Group ID</td>
			<td><ezf:inputText name="prcGrpPk" ezfName="prcGrpPk" value="1" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align:left\" ezftoupper=\"\""/></td>
		</tr>
		<tr>
			<td class="stab">Pricing Group Name</td>
			<td><ezf:inputText name="prcGrpNm" ezfName="prcGrpNm" value="PriceGroup" otherAttr=" size=\"50\" maxlength=\"75\" ezftoupper=\"\""/></td>
		</tr>
		<tr>
			<td class="stab">Target Context</td>
			<td>
				<ezf:select name="prcGrpTrgtTpCd" ezfName="prcGrpTrgtTpCd" ezfBlank="1" ezfCodeName="prcGrpTrgtTpCd_P" ezfDispName="prcGrpTrgtTpDescTxt_P" otherEvent1=" onchange=\"sendServer('OnChange_TargetContext')\"" otherAttr=" style=\"width:210px\" id=\"prcGrpTrgtTpCd1\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Target Attribute Name</td>
			<td>
				<ezf:select name="prcGrpTrgtAttrbCd" ezfName="prcGrpTrgtAttrbCd" ezfBlank="1" ezfCodeName="prcGrpTrgtAttrbCd_P" ezfDispName="prcGrpTrgtAttrbDescTxt_P" otherAttr=" style=\"width:210px\" id=\"prcGrpTrgtAttrbCd\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Target From(*)</td>
			<td>
				<ezf:inputText name="xxPrcQlfyValSrchTxt_TF" ezfName="xxPrcQlfyValSrchTxt_TF" otherAttr=" size=\"50\" maxlength=\"10000\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Description(*)</td>
			<td>
				<ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" otherAttr=" size=\"50\" maxlength=\"360\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab"></td>
			<td class="stab">* Available only when Target Attribute Name is Account Number</td>
		</tr>
		<tr>
			<td class="stab">Target To(*)</td>
			<td>
				<ezf:inputText name="xxPrcQlfyValSrchTxt_TT" ezfName="xxPrcQlfyValSrchTxt_TT" otherAttr=" size=\"50\" maxlength=\"10000\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Precedence#</td>
			<td>
				<ezf:inputText name="prcGrpPrcdNum" ezfName="prcGrpPrcdNum" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
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
		<tr>
			<td class="stab">Date To</td>
			<td>
				<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);">
				-
				<ezf:inputText name="effThruDt_H2" ezfName="effThruDt_H2" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H2', 4);">
			</td>
		</tr>
	</table>
	</div>
</center>


<%-- **** Task specific area ends here **** --%>
