<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20090716055902 --%>
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

<div id="main_BODY">
<table border="0" width="100%" width="100%" cellpadding="0" cellspacing="0" style="border-collapse:collapse;border-color: silver; border-width: 1px; border-style: outset">
	<tbody>
		<tr>
			<td colspan="5" height="24" class="pClothB"><b><font
				color="white"><ezf:label name="xxScrNm" ezfName="xxScrNm" />&nbsp;Lookup Screen</font></b></td>
		</tr>
		<tr>
			<td width="122" align="right" height="33">Code:</td>
			<td width="216" height="33">
				<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" otherAttr=" size=\"20\""/>
			</td>
			<td width="54" align="right" height="33">Name:</td>
			<td width="216" height="33">
				<ezf:inputText name="glblCmpyNm" ezfName="glblCmpyNm" otherAttr=" size=\"20\""/>
			</td>
			<td width="418" height="33">
				<ezf:inputButton name="Refresh" value="Refresh" htmlClass="cBtn" />
			</td>
		</tr>
		<tr>
			<td colspan="5">
			<table border="0" width="100%"
				style="border-collapse:collapse;border-color: silver; border-width: 1px; border-style: outset">
				<tbody>
					<tr>
						<th style="border-width: 1px; border-style: inset" class="pClothB" height="24" width="86"></th>
						<th align="center" style="border-width: 1px; border-style: inset" class="pClothB" height="24" width="200">
							<font color="white"><b>Code</b></font>
						</th>
						<th align="center" style="border-width: 1px; border-style: inset" class="pClothB" height="24">
							<font color="white"><b>Name/Description</b></font>
						</th>
					</tr>
				</tbody>
			</table>
			<div style="overflow-Y: scroll ; height:450;">
			<table border="0" width="100%"
				style="border-collapse:collapse;border-color: silver; border-width: 1px; border-style: outset">
				<tbody>
					<% int rowNum = 0; %>
					<ezf:row ezfHyo="A">
						<tr class='<%=rowNum%2==0?"pEvenNumberBGcolor":""%>'>
							<td width="86" align="center" style="border-width: 1px; border-style: inset">
								<ezf:inputButton name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" htmlClass="cBtn" />
								<ezf:inputHidden name="glblCmpyCd_A" ezfName="glblCmpyCd_A" ezfHyo="A" ezfArrayNo="0" />
							</td>
							<td width="200" align="center" style="border-width: 1px; border-style: inset">
								<ezf:label name="glblCmpyCd_A" ezfName="glblCmpyCd_A" ezfHyo="A" ezfArrayNo="0" />
							</td>
							<td style="border-width: 1px; border-style: inset">
								<ezf:label name="glblCmpyNm_A" ezfName="glblCmpyNm_A" ezfHyo="A" ezfArrayNo="0" />
							</td>
						</tr>
						<%rowNum++;%>
					</ezf:row>
				</tbody>
			</table>
			</div>
			</td>
		</tr>
		<tr>
		<td align="right" valign="bottom" colspan="5">
			<table border="0" width="100%">
				<tbody>
					<tr align="right" valign="bottom">
						<td width="131" align="center">
						</td>
						<td width="691"><ezf:inputButton name="Prev" value="Prev" htmlClass="cBtn" otherAttr=" width=\"50px\""/></td>
						<td width="95" align="center"><ezf:inputButton name="Next" value="Next" htmlClass="cBtn" otherAttr=" width=\"50px\""/></td>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</td>
		</tr>
	</tbody>
</table>
</div>

<!-- Set Page ID  --> 
<input type="hidden" name="pageID"value="ZZSL1110Scrn00"> 
<!-- Set Page Name --> 
<input type="hidden" name="pageName" value="Lookup Screen"> 


<%-- **** Task specific area ends here **** --%>
