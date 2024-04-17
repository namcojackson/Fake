<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170419112254 --%>
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
			<input type="hidden" name="pageID" value="NSAL1180Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Branch Hierarchy Popup">

		<center>
			<div id="Top" style="overflow-x:hidden; width:657px; margin-top:20px;" align="left">
				<table border="1" cellpadding="1" cellspacing="0" style="width:640px;">
					<col width="320px" align="center">		<!-- Hierarchy -->
					<col width="160px" align="center">		<!-- Role Name -->
					<col width="160px" align="center">		<!-- TOC Name -->
					<tr>
						<td class="pClothBs">&nbsp;</td>
						<td class="pClothBs">Role Name</td>
						<td class="pClothBs">Toc Name</td>
					</tr>
				</table>                         
			</div>
			<div style="overflow-y:scroll; height:520px; overflow-x:none; width:657px;">
				<table border="1" cellpadding="1" cellspacing="0" style="word-break:break-all;" id="A">
					<col width="313px">		<!-- Hierarchy -->
					<col width="160px">		<!-- Role Name -->
					<col width="160px">		<!-- TOC Name -->
					<ezf:row ezfHyo="A">
					<tr>
						<td><ezf:inputText name="xxRecNmTxt_HI" ezfName="xxRecNmTxt_HI" value="Region Notrh East" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"43\" maxlength=\"500\""/></td>
						<td><ezf:label name="orgFuncRoleTpNm_A" ezfName="orgFuncRoleTpNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
						<td><ezf:label name="xxRecNmTxt_PS" ezfName="xxRecNmTxt_PS" ezfHyo="A" ezfArrayNo="0" /></td>
					</tr>
					</ezf:row>
					<ezf:skip>
					</ezf:skip>
				</table>
			</div>
		</center>

<%-- **** Task specific area ends here **** --%>
