<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180417145914 --%>
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
			<input type="hidden" name="pageID" value="NSAL1360Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Shell Configuration Mass Update Popup">

<center>

	<br><br><br>
	<table>
		<tr>
			<td class="stab">
				Meter Method
			</td>
			<td>
				<ezf:select name="mtrReadMethCd" ezfName="mtrReadMethCd" ezfBlank="1" ezfCodeName="mtrReadMethCd_L" ezfDispName="mtrReadMethDescTxt_L" otherAttr=" style=\"width:250px\" id=\"mtrReadMethCd\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">
				Customer PO Number
			</td>
			<td>
				<ezf:inputText name="custIssPoNum" ezfName="custIssPoNum" value="123456789012345678901234567890ABCDE" otherAttr=" size=\"35\" maxlength=\"35\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">
				Customer PO Date
			</td>
			<td>
				<ezf:inputText name="custIssPoDt" ezfName="custIssPoDt" value="11/12/2015" otherAttr=" id=\"custIssPoDt\" size=\"10\" maxlength=\"10\""/>
				<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('custIssPoDt', 4);">
			</td>
		</tr>
		<tr>
			<td class="stab">
				Customer PO Expiration Date
			</td>
			<td>
				<ezf:inputText name="dsPoExprDt" ezfName="dsPoExprDt" value="11/12/2015" otherAttr=" id=\"dsPoExprDt\" size=\"10\" maxlength=\"10\""/>
				<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsPoExprDt', 4);">
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
