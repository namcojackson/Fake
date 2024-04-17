<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160607074152 --%>
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
			<input type="hidden" name="pageID" value="NMAL0260Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Bill of Material Popup">
<center>
	<table width="80%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>You are creating a New Revision for the below Item. 
				<br>Please populate the effective start date for new Revision and Press Continue.
			</td>
		</tr>
		<tr>
			<td><br><br>
				<table width="80%" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td>BOM Item#</td>
						<td><ezf:inputText name="mdseCd" ezfName="mdseCd" value="1234567890123456" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
					</tr>
					<tr>
						<td>BOM Item Description</td>
						<td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="123456789012345XXXXXXXXXX12345" otherAttr=" size=\"30\" maxlength=\"250\" ezftoupper=\"\""/></td>
					</tr>
					<tr>
						<td>Effective Start Date</td>
						<td>
							<ezf:inputText name="effFromDt" ezfName="effFromDt" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effFromDt\""/>
							<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);">
						</td>
					</tr>
				</table>
				<br>
			</td>
		</tr>
		<tr>
			<td><hr></td>
		</tr>
		<tr>
			<td align="right"><br>
				<ezf:inputButton name="Continue" value="Continue" htmlClass="pBtn6" />
				<ezf:inputButton name="CMN_Close" value="Cancel" htmlClass="pBtn6" />
				&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
