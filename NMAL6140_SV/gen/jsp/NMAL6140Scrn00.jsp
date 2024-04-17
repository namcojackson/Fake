<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160217173930 --%>
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
			<input type="hidden" name="pageID" value="NMAL6140Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Address Search Popup">
			
<center>
	<table border="0" cellspacing="0" cellpadding="0">
		<col width="100">
		<col width="40">
		<col width="400">
		<tr>
			<td class="stab">Address1(*)</td>
			<td colspan="2"><ezf:inputText name="firstLineAddr" ezfName="firstLineAddr" otherAttr=" size=\"50\" maxlength=\"60\" ezftoupper=\"\""/></td>
		</tr>
		<tr>
			<td class="stab">Address2(*)</td>
			<td colspan="2"><ezf:inputText name="scdLineAddr" ezfName="scdLineAddr" otherAttr=" size=\"50\" maxlength=\"60\" ezftoupper=\"\""/></td>
		</tr>
		<tr>
			<td class="stab">Address3(*)</td>
			<td colspan="2"><ezf:inputText name="thirdLineAddr" ezfName="thirdLineAddr" otherAttr=" size=\"50\" maxlength=\"60\" ezftoupper=\"\""/></td>
		</tr>
		<tr>
			<td class="stab">Address4(*)</td>
			<td colspan="2"><ezf:inputText name="frthLineAddr" ezfName="frthLineAddr" otherAttr=" size=\"50\" maxlength=\"60\" ezftoupper=\"\""/></td>
		</tr>
		<tr>
			<td class="stab">City(*)</td>
			<td colspan="2"><ezf:inputText name="ctyAddr" ezfName="ctyAddr" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
		</tr>
		<tr>
			<td class="stab">State</td>
			<td colspan="2">
				<ezf:select name="stCd" ezfName="stCd" ezfBlank="1" ezfCodeName="stCd_H1" ezfDispName="stCd_H2" />
			</td>
		</tr>
		<tr>
			<td class="stab">Postal Code(*)</td>
			<td colspan="2"><ezf:inputText name="postCd" ezfName="postCd" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
		</tr>
		<tr>
			<td class="stab">Province(*)</td>
			<td colspan="2"><ezf:inputText name="provNm" ezfName="provNm" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
		</tr>
		<tr>
			<td class="stab">County(*)</td>
			<td colspan="2"><ezf:inputText name="cntyNm" ezfName="cntyNm" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
		</tr>
		<tr>
			<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Ctry" >Country</ezf:anchor></td>
			<td><ezf:inputText name="ctryCd" ezfName="ctryCd" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
			<td><ezf:inputText name="ctryNm" ezfName="ctryNm" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
		</tr>
		<tr>
			<td class="stab">Bill To's Only</td>
			<td><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" /></td>
		</tr>
		<tr>
			<td class="stab">Ship To's Only</td>
			<td class="stab"><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" /></td>
		</tr>
		<tr>
			<td class="stab">Include inactive</td>
			<td><ezf:inputCheckBox name="xxChkBox_H3" ezfName="xxChkBox_H3" value="Y" /></td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
