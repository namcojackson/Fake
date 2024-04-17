<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20130716204914 --%>
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
<fmt:setBundle basename="I18N_NLBL3030Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NLBL3030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NLBL3030Scrn00.title" bundle="${I18N_SCREEN_ID}">Message Entry</fmt:message>">
			
			<center>
			<br>
<table width="80%">
<tr>
<td>
	<table>
		<col width="380">
		<col width="320"  align="right">
		<tr>
		<td>
			<b>
			<ezf:label name="msgCtrlTpDescTxt" ezfName="msgCtrlTpDescTxt" />
			</b>
		</td>
		<td>

		</td>
		</tr>
	</table>
</td></tr>
<tr><td>
	<ezf:textArea name="xxDsMultMsgDplyTxt" ezfName="xxDsMultMsgDplyTxt" otherAttr=" cols=\"100\" rows=\"40\""/>

</td></tr>
<tr><td>
	<table border="0" cellpadding="1" cellspacing="0">
		<col width="410">
		<col width="155" align="right">
		<col width="25" align="right">
		<col width="5">
		<col width="25" align="right">
		<col width="8">
		<col width="50">

		<tr>
		<td><ezf:inputButton name="OK_Message" value="OK" htmlClass="pBtn3" /></td>
		<td class="stab"><fmt:message key="i18n.NLBL3030Scrn00.label.1" bundle="${I18N_SCREEN_ID}">The number of letters:&nbsp;</fmt:message></td>
		<td class="pOut"><ezf:label name="xxDsInputTxtNum" ezfName="xxDsInputTxtNum" /></td>
		<td>/</td>
		<td class="pOut"><ezf:label name="msgMaxTxtNum" ezfName="msgMaxTxtNum" /></td>
		<td/>
		<td><ezf:inputButton name="Chk_Message" value="Check" htmlClass="pBtn3" /></td>
		</tr>
	</table>
</td></tr>
</table>
</center>

<%-- **** Task specific area ends here **** --%>
