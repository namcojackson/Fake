<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20101125021839 --%>
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
			<input type="hidden" name="pageID" value="ZZZL0032Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="EventID Loopkup">
			
<div id="main_BODY">
<table align="center" width="430" border="0" cellpadding="1" cellspacing="0">
	<tbody>
		<col width="120">
		<col width="120">
		<col width="60">
		<tr>
			<td class="stab" align="left">Global company code
			</td>
			<td align="left">
				<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="stab" align="left">Business ID(*)
			</td>
			<td align="left">
			<ezf:inputText name="bizId" ezfName="bizId" otherAttr=" size=\"10\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab" align="left">Screen Event ID(*)
			</td>
			<td align="left">
			<ezf:inputText name="scrAppId" ezfName="scrAppId" otherAttr=" size=\"30\""/>
			</td>
			<td align="right">
				<ezf:inputButton name="Search" value="Search" htmlClass="cBtn" />
			</td>
			
		</tr>
	</tbody>
</table>
<hr width="780">
<table align="center" width="328" border="1" cellpadding="1" cellspacing="0">
	<col width="24">
	<col width="24">
	<col width="265">
	<tr align="center">
		<td colspan ="2" class="pClothBs">&nbsp;</td>
		<td class="pClothBs">BusinessID
		</td>
	</tr>
	<tr align="center">
		<td class="pClothBs">&nbsp;</td>
		<td class="pClothBs">&nbsp;</td>
		<td class="pClothBs">Screen EventID
		</td>
	</tr>	
</table>
<div style="OVERFLOW-Y:scroll; OVERFLOW-X:none; HEIGHT:420; WIDTH:579;">
<table align="right" width="328" border="1" cellpadding="1" cellspacing="0">
	<tbody>
	<col width="24">
	<col width="24">
	<col width="265">
		<!--<tr class="pEvenNumberBGcolor">
			<td align="center">
				<input type="checkbox" value="Y" name="xxChkBox_01" ezfname="xxChkBox_01" ezfhyo="A" ></td>
			</td>
			<td>&nbsp;
			</td>
			<td align="left">
				<label ezfout ezfhyo="A" ezfArrayNo="0" name="bizId_A" ezfname="bizId_A">XXXXXXXXXX</label>
			</td>
		</tr>
		<tr>
			<td>&nbsp;
			</td>
			<td align="center">
				<input type="checkbox" value="Y" name="xxChkBox_02" ezfname="xxChkBox_02" ezfhyo="A" >
			</td>
			<td align="left">
				<label ezfout ezfhyo="A" ezfArrayNo="0" name="scrAppId_A" ezfname="scrAppId_A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
			</td>
		</tr>
		<tr>
			<td>&nbsp;
			</td>
			<td align="center">
				<input type="checkbox" value="Y" name="xxChkBox_02" ezfname="xxChkBox_02" ezfhyo="A" >
			</td>
			<td align="left">
				<label ezfout ezfhyo="A" ezfArrayNo="0" name="scrAppId_A" ezfname="scrAppId_A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
			</td>
		</tr>
		<tr>
			<td>&nbsp;
			</td>
			<td align="center">
				<input type="checkbox" value="Y" name="xxChkBox_02" ezfname="xxChkBox_02" ezfhyo="A" >
			</td>
			<td align="left">
				<label ezfout ezfhyo="A" ezfArrayNo="0" name="scrAppId_A" ezfname="scrAppId_A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
			</td>
		</tr>
		<tr class="pEvenNumberBGcolor">
			<td align="center">
				<input type="checkbox" value="Y" name="xxChkBox_01" ezfname="xxChkBox_01" ezfhyo="A" ></td>
			</td>
			<td>&nbsp;
			</td>
			<td align="left">
				<label ezfout ezfhyo="A" ezfArrayNo="0" name="bizId_A" ezfname="bizId_A">XXXXXXXXXX</label>
			</td>
		</tr>
		<tr>
			<td>&nbsp;
			</td>
			<td align="center">
				<input type="checkbox" value="Y" name="xxChkBox_02" ezfname="xxChkBox_02" ezfhyo="A" >
			</td>
			<td align="left">
				<label ezfout ezfhyo="A" ezfArrayNo="0" name="scrAppId_A" ezfname="scrAppId_A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
			</td>
		</tr>
		<tr>
			<td>&nbsp;
			</td>
			<td align="center">
				<input type="checkbox" value="Y" name="xxChkBox_02" ezfname="xxChkBox_02" ezfhyo="A" >
			</td>
			<td align="left">
				<label ezfout ezfhyo="A" ezfArrayNo="0" name="scrAppId_A" ezfname="scrAppId_A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label></td>
			</td>
		</tr>	-->									
		<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
		<%@ page import="business.servlet.ZZZL0032.ZZZL0032BMsg" %>
		<% ZZZL0032BMsg bMsg = (ZZZL0032BMsg)databean.getEZDBMsg(); %>
		<% String bizId_Pre = ""; %>
		<% String bizId     = ""; %>

		<% for( int i = 0; i < bMsg.A.getValidCount(); i++) { %>

			<% bizId = bMsg.A.no( i ).bizId_A.getValue(); %>
	
			<% if( !bizId_Pre.equals( bizId ) ) { %>

			<%-- Header (BusinessId) --%>
			<tr class="pEvenNumberBGcolor">
				<td align="center">
					<ezf:inputCheckBox name="xxChkBox_01" ezfName="xxChkBox_01" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" onclick=\"sendServer('SelectAll')\""/>
				</td>
				<td>&nbsp;
				</td>
				<td align="left">
					<ezf:label name="bizId_A" ezfName="bizId_A" ezfHyo="A" ezfArrayNo="<%= i %>" />
				</td>
			</tr>
			<% } else { %>
				<ezf:inputHidden name="xxChkBox_01" ezfName="xxChkBox_01" ezfHyo="A" ezfArrayNo="<%= i %>"/>
				<%--<ezf:inputHidden name="bizId_A" ezfName="bizId_A" ezfHyo="A" ezfArrayNo="<%= i %>"/>--%>
			<% } %>

		<%-- Detail (ScreenAppId) --%>
		<tr>
			<td>&nbsp;
			</td>
			<td align="center">
				<ezf:inputCheckBox name="xxChkBox_02" ezfName="xxChkBox_02" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" />
			</td>
			<td align="left">
				<ezf:label name="scrAppId_A" ezfName="scrAppId_A" ezfHyo="A" ezfArrayNo="<%= i %>" />
			</td>
		</tr>
		<% bizId_Pre = bizId; %>
		<% } %>
		<%-- +++++ [END] Programming JSP for Nesting Records Table +++++ --%>
	</tbody>
</table>
</div>
</div>

<%-- **** Task specific area ends here **** --%>
