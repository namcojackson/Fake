<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100915070103 --%>
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


		<table align="center" cellpadding="1" cellspacing="0" border="1" width="1000px" style="table-layout:fixed">
				<tr valign="middle">
					&nbsp;
				</tr>
				<col width="20%" align="center">
				<col width="20%" align="center">
				<col width="30%" align="center">
				<col width="30%" align="center">
				<tr align="center">
					<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><b>Global Company CD</b><img id="sortIMG.glblCmpyCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><b>Business Application ID</b><img id="sortIMG.bizAppId" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><b>Business Application Name</b><img id="sortIMG.bizAppNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><b>Application Description</b><img id="sortIMG.appDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
				</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0">
					<tr>
				<td align="center">

							<div style="OVERFLOW-Y:scroll; OVERFLOW-X:none; WIDTH:1083px; HEIGHT:535px; ">
								<table align="right" cellpadding="1" cellspacing="0" border="1" style="WIDTH:1000px;" id="A" style="table-layout:fixed">
									<col width="20%">
									<col width="20%">
									<col width="30%">
									<col width="30%">
									<tbody>
									<ezf:row ezfHyo="A">
										<tr align="center" id="id_row${EZF_ROW_NUMBER}">
											<td align="center">
												<ezf:label name="glblCmpyCd" ezfName="glblCmpyCd" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<td align="center">
												<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="SelectBizId" ><ezf:label name="bizAppId" ezfName="bizAppId" ezfHyo="A" ezfArrayNo="0" />
												</ezf:anchor>
											</td>
											<td align="left">
												<ezf:label name="bizAppNm" ezfName="bizAppNm" ezfHyo="A" ezfArrayNo="0" />
											</td>
											<td align="left">
												<ezf:label name="appDescTxt" ezfName="appDescTxt" ezfHyo="A" ezfArrayNo="0" />
											</td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr>
											<td align="center">
												<label ezfout name="glblCmpyCd" ezfhyo="A" ezfname="glblCmpyCd">XXXXXXXXXX</label>
											</td>
											<td align="center">
												<a href="#" onclick="sendServer('SelectBizId')" ezfhyo="A"><label ezfout name="bizAppId" ezfhyo="A" ezfname="bizAppId">XXXXXXXXXXXXXXXXXXXX</label>
												</a>
											</td>
											<td align="left">
												<label ezfout name="bizAppNm" ezfhyo="A" ezfname="bizAppNm">XXXXXXXXXX</label>
											</td>
											<td align="left">
												<label ezfout name="appDescTxt" ezfhyo="A" ezfname="appDescTxt">XXXXXXXXXXXXXXXXX</label>
											</td>
										</tr>
										<tr>
											<td align="center">
												<label ezfout name="glblCmpyCd" ezfhyo="A" ezfname="glblCmpyCd">XXXXXXXXXX</label>
											</td>
											<td align="center">
												<a href="#" onclick="sendServer('SelectBizId')" ezfhyo="A"><label ezfout name="bizAppId" ezfhyo="A" ezfname="bizAppId">XXXXXXXXXXXXXXXXXXXX</label>
												</a>
											</td>
											<td align="left">
												<label ezfout name="bizAppNm" ezfhyo="A" ezfname="bizAppNm">XXXXXXXXXX</label>
											</td>
											<td align="left">
												<label ezfout name="appDescTxt" ezfhyo="A" ezfname="appDescTxt">XXXXXXXXXXXXXXXXX</label>
											</td>
										</tr>
										<tr>
											<td align="center">
												<label ezfout name="glblCmpyCd" ezfhyo="A" ezfname="glblCmpyCd">XXXXXXXXXX</label>
											</td>
											<td align="center">
												<a href="#" onclick="sendServer('SelectBizId')" ezfhyo="A"><label ezfout name="bizAppId" ezfhyo="A" ezfname="bizAppId">XXXXXXXXXXXXXXXXXXXX</label>
												</a>
											</td>
											<td align="left">
												<label ezfout name="bizAppNm" ezfhyo="A" ezfname="bizAppNm">XXXXXXXXXX</label>
											</td>
											<td align="left">
												<label ezfout name="appDescTxt" ezfhyo="A" ezfname="appDescTxt">XXXXXXXXXXXXXXXXX</label>
											</td>
										</tr>
										<tr>
											<td align="center">
												<label ezfout name="glblCmpyCd" ezfhyo="A" ezfname="glblCmpyCd">XXXXXXXXXX</label>
											</td>
											<td align="center">
												<a href="#" onclick="sendServer('SelectBizId')" ezfhyo="A"><label ezfout name="bizAppId" ezfhyo="A" ezfname="bizAppId">XXXXXXXXXXXXXXXXXXXX</label>
												</a>
											</td>
											<td align="left">
												<label ezfout name="bizAppNm" ezfhyo="A" ezfname="bizAppNm">XXXXXXXXXX</label>
											</td>
											<td align="left">
												<label ezfout name="appDescTxt" ezfhyo="A" ezfname="appDescTxt">XXXXXXXXXXXXXXXXX</label>
											</td>
										</tr>
										<tr>
											<td align="center">
												<label ezfout name="glblCmpyCd" ezfhyo="A" ezfname="glblCmpyCd">XXXXXXXXXX</label>
											</td>
											<td align="center">
												<a href="#" onclick="sendServer('SelectBizId')" ezfhyo="A"><label ezfout name="bizAppId" ezfhyo="A" ezfname="bizAppId">XXXXXXXXXXXXXXXXXXXX</label>
												</a>
											</td>
											<td align="left">
												<label ezfout name="bizAppNm" ezfhyo="A" ezfname="bizAppNm">XXXXXXXXXX</label>
											</td>
											<td align="left">
												<label ezfout name="appDescTxt" ezfhyo="A" ezfname="appDescTxt">XXXXXXXXXXXXXXXXX</label>
											</td>
										</tr>
										<tr>
											<td align="center">
												<label ezfout name="glblCmpyCd" ezfhyo="A" ezfname="glblCmpyCd">XXXXXXXXXX</label>
											</td>
											<td align="center">
												<a href="#" onclick="sendServer('SelectBizId')" ezfhyo="A"><label ezfout name="bizAppId" ezfhyo="A" ezfname="bizAppId">XXXXXXXXXXXXXXXXXXXX</label>
												</a>
											</td>
											<td align="left">
												<label ezfout name="bizAppNm" ezfhyo="A" ezfname="bizAppNm">XXXXXXXXXX</label>
											</td>
											<td align="left">
												<label ezfout name="appDescTxt" ezfhyo="A" ezfname="appDescTxt">XXXXXXXXXXXXXXXXX</label>
											</td>
										</tr>
										<tr>
											<td align="center">
												<label ezfout name="glblCmpyCd" ezfhyo="A" ezfname="glblCmpyCd">XXXXXXXXXX</label>
											</td>
											<td align="center">
												<a href="#" onclick="sendServer('SelectBizId')" ezfhyo="A"><label ezfout name="bizAppId" ezfhyo="A" ezfname="bizAppId">XXXXXXXXXXXXXXXXXXXX</label>
												</a>
											</td>
											<td align="left">
												<label ezfout name="bizAppNm" ezfhyo="A" ezfname="bizAppNm">XXXXXXXXXX</label>
											</td>
											<td align="left">
												<label ezfout name="appDescTxt" ezfhyo="A" ezfname="appDescTxt">XXXXXXXXXXXXXXXXX</label>
											</td>
										</tr>
										<tr>
											<td align="center">
												<label ezfout name="glblCmpyCd" ezfhyo="A" ezfname="glblCmpyCd">XXXXXXXXXX</label>
											</td>
											<td align="center">
												<a href="#" onclick="sendServer('SelectBizId')" ezfhyo="A"><label ezfout name="bizAppId" ezfhyo="A" ezfname="bizAppId">XXXXXXXXXXXXXXXXXXXX</label>
												</a>
											</td>
											<td align="left">
												<label ezfout name="bizAppNm" ezfhyo="A" ezfname="bizAppNm">XXXXXXXXXX</label>
											</td>
											<td align="left">
												<label ezfout name="appDescTxt" ezfhyo="A" ezfname="appDescTxt">XXXXXXXXXXXXXXXXX</label>
											</td>
										</tr>
									</ezf:skip>
									</tbody>
								</table>
							</div>
					</td>
				</tr>
			</table>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NYEL0050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Code Master Menu">

<%-- **** Task specific area ends here **** --%>
