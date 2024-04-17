<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160530135519 --%>
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
			<input type="hidden" name="pageID" value="NMAL2660Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Resource Assignment Popup">
<!-- **** Aplication Main Contents area starts hear **** -->

	<div>
		<table border="0" cellpadding="5" cellspacing="0">
			<tr><td width="782">
			<!-- Body area -->
				<table border="1" cellpadding="1" cellspacing="0" width="760" style="table-layout: fixed;">
					<col width="80"  align="left">
					<col width="120" align="left">
					<col width="100" align="left">
					<col width="100" align="left">
					<col width="100" align="left">
					<col width="100" align="left">
					<col width="80"  align="left">
					<col width="80"  align="left">
					<tr>
						<td class="pClothBs">Employee ID</td>
						<td class="pClothBs">Name</td>
						<td class="pClothBs">Business <BR>Area</td>
						<td class="pClothBs">Role</td>
						<td class="pClothBs">Organization</td>
						<td class="pClothBs">Territory<BR>Resource Role</td>
						<td class="pClothBs">Start Date</td>
						<td class="pClothBs">End Date</td>
					</tr>
				</table>
				<div style="overflow:auto; height:498;word-break: break-all;">
					<table border="1" cellpadding="1" cellspacing="0" id="A" width="760" style="table-layout: fixed;">
					<col width="80"  align="left">
					<col width="120" align="left">
					<col width="100" align="left">
					<col width="100" align="left">
					<col width="100" align="left">
					<col width="100" align="left">
					<col width="80"  align="left">
					<col width="80"  align="left">
						<tbody>
							<ezf:row ezfHyo="A">
								<tr id="id_row{EZF_ROW_NUMBER}" style="height:45;">
									<td><ezf:label name="psnNum" ezfName="psnNum" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="xxPsnFirstMidLastNm" ezfName="xxPsnFirstMidLastNm" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="bizAreaOrgDescTxt" ezfName="bizAreaOrgDescTxt" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="orgFuncRoleTpDescTxt" ezfName="orgFuncRoleTpDescTxt" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="orgNm" ezfName="orgNm" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="acctTeamRoleTpDescTxt" ezfName="acctTeamRoleTpDescTxt" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="effFromDt" ezfName="effFromDt" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="effThruDt" ezfName="effThruDt" ezfHyo="A" ezfArrayNo="0" /></td>
								</tr>
							</ezf:row>
							<ezf:skip>
								<tr style="height:45;">
									<td><label ezfout>12345678</label></td>
									<td><label ezfout>AAAAAAAAAABBBBBBBBBBCCCCCCCCCC,AAAAAAAAAABBBBBBBBBBCCCCCCCCCC</label></td>
									<td><label ezfout>AAAAAAAAAABBBBBBBBBBCCCCCCCCCCAAAAAAAAAABBBBBBBBBB</label></td>
									<td><label ezfout>AAAAAAAAAABBBBBBBBBBCCCCCCCCCCAAAAAAAAAABBBBBBBBBB</label></td>
									<td><label ezfout>AAAAAAAAAABBBBBBBBBBCCCCCCCCCCAAAAAAAAAABBBBBBBBBB</label></td>
									<td><label ezfout>AAAAAAAAAABBBBBBBBBBCCCCCCCCCCAAAAAAAAAABBBBBBBBBB</label></td>
									<td><label ezfout>2016/12/2</label></td>
									<td><label ezfout>2016/12/2</label></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
								</tr>						
								<tr>
									<td><label ezfout>A</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
								</tr>
								<tr>
									<td><label ezfout>A</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
								</tr>
								<tr>
									<td><label ezfout>A</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
									<td><label ezfout>Ws</label></td>
								</tr>
								
							</ezf:skip>
						</tbody>
					</table>
				</div>
			</td></tr>
		</table>
	</div>


<%-- **** Task specific area ends here **** --%>
