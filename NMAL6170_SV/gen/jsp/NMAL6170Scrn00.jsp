<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170713133916 --%>
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
			<input type="hidden" name="pageID" value="NMAL6170Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Relationships Filter Popup">

<!-- Relationships List Filter Popup HTML start -->

<center>
	<div>
	<table cellpadding="1" border="0" style="margin-top:4px;" id="mainTbl">
		<col align="left" width="180">
		<col align="left" width="340">
		<tr>
			<td class="stab">Relationship Type</td>
			<td><ezf:select name="dsAcctRelnTpCd_H" ezfName="dsAcctRelnTpCd_H" ezfBlank="1" ezfCodeName="dsAcctRelnTpCd_C" ezfDispName="dsAcctRelnTpNm_F" otherAttr=" style=\"width:180px;\""/></td>
		</tr>
		<tr>
			<td class="stab">Related Account#</td>
			<td>
				<ezf:inputButton name="OpenWin_ShowAcct" value="R" htmlClass="pBtn0" />
				<ezf:inputButton name="OpenWin_AcctSrch" value="A" htmlClass="pBtn0" />&nbsp;<ezf:inputText name="dsAcctNum_F" ezfName="dsAcctNum_F" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Related Account Name(*)</td>
			<td>
				<ezf:inputText name="dsAcctNm_F" ezfName="dsAcctNm_F" otherAttr=" size=\"31\" maxlength=\"31\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Bill To</td>
			<td><ezf:inputCheckBox name="xxChkBox_FB" ezfName="xxChkBox_FB" value="Y" /></td>
		</tr>
		<tr>
			<td class="stab">Ship To</td>
			<td><ezf:inputCheckBox name="xxChkBox_FS" ezfName="xxChkBox_FS" value="Y" /></td>
		</tr>
		<tr>
			<td class="stab">Reciprocal</td>
			<td><ezf:inputCheckBox name="xxChkBox_FR" ezfName="xxChkBox_FR" value="Y" /></td>
		</tr>
		<tr>
			<td class="stab">Start Date</td>
			<td>
				<ezf:inputText name="effFromDt_F1" ezfName="effFromDt_F1" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_F1', 4);">
				-
				<ezf:inputText name="effFromDt_F2" ezfName="effFromDt_F2" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_F2', 4);">
			</td>
		</tr>
		<tr>
			<td class="stab">End Date</td>
			<td>
				<ezf:inputText name="effThruDt_F1" ezfName="effThruDt_F1" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_F1', 4);">
				-
				<ezf:inputText name="effThruDt_F2" ezfName="effThruDt_F2" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_F2', 4);">
			</td>
		</tr>
	</table>
	</div>
</center>

<!-- Relationships List Filter Popup HTML end -->

<%-- **** Task specific area ends here **** --%>
