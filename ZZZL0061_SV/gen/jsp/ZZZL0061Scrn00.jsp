<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20110114061301 --%>
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
			<input type="hidden" name="pageID" value="ZZZL0061Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="JobID Lookup">
			
<div id="main_BODY">
<table align="center" width="470" border="0" cellpadding="1" cellspacing="1">
	<tbody>
		<col width="80">
		<col width="80">
		<col width="80">
		<tr>
			<td class="stab" align="left">Global company code
			</td>
			<td colspan="2" align="left">
				<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab" align="left">Job ID(*)
			</td>
			<td colspan="2" align="left">
				<ezf:inputText name="batProcJobId" ezfName="batProcJobId" value="XXXXXXXXXXX" otherAttr=" size=\"15\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab" align="left">Program ID(*)
			</td>
			<td align="left">
			<ezf:inputText name="batProcPgmId" ezfName="batProcPgmId" value="XXXXXXXXXXX" otherAttr=" size=\"15\" ezftoupper=\"\""/>
			</td>
			<td align="right">
				<ezf:inputButton name="Search" value="Search" htmlClass="cBtn" />
			</td>
		</tr>
	</tbody>
</table>
<hr width="470">
<table align="center" width="470" border="0" cellpadding="1" cellspacing="0">
	<tr align="right">
		<td>
			<%-- Pagenation --%>
				<!--<table border="0" cellpadding="1" cellspacing="0">
					<col width="54"  align="center">
					<col width="40"  align="right">
					<col width="16"  align="center">
					<col width="40"  align="right">
					<col width="16"  align="center">
					<col width="40"  align="right">
					<col width="10">
					<col>
					<col width="10">
					<col width="50">
					<tr>
						<td class="stab">Showing</td>
						<td class="pOut">1</td>
						<td class="stab">to</td>
						<td class="pOut">3</td>
						<td class="stab">of</td>
						<td class="pOut">3</td>
						<td>&nbsp;</td>
						<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
						<td></td>
						<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
					</tr>
				</table>-->
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
<table align="center" width="470" border="1" cellpadding="1" cellspacing="0">
	<col width="71">
	<col width="80">
	<col width="166">
	<col width="165">
	<tr align="center">
		<td class="pClothBs">
		</td>
		<td class="pClothBs">Global Company Code
		</td>
		<td class="pClothBs">Job ID
		</td>
		<td class="pClothBs">Program ID
		</td>
	</tr>
</table>
<div style="OVERFLOW-Y:scroll; OVERFLOW-X:none; HEIGHT:400; WIDTH:650;">
<table align="right" width="470" border="1" cellpadding="1" cellspacing="0">
	<col width="66">
	<col width="85">
	<col width="162">
	<col width="163">
		<ezf:row ezfHyo="A">
		<tr id="id_row_{EZF_ROW_NUMBER}">
			<td align="center">
				<ezf:inputButton name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn4" />
			</td>
			<td align="center">
				<ezf:label name="glblCmpyCd_A" ezfName="glblCmpyCd_A" ezfHyo="A" ezfArrayNo="0" />
			</td>
			<td align="center">
				<ezf:label name="batProcJobId_A" ezfName="batProcJobId_A" ezfHyo="A" ezfArrayNo="0" />
			</td>
			<td align="center">
				<ezf:label name="batProcPgmId_A" ezfName="batProcPgmId_A" ezfHyo="A" ezfArrayNo="0" />
			</td>
		</tr>
		</ezf:row>
</table>
</div>
</div>

<%-- **** Task specific area ends here **** --%>
