<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20151201083602 --%>
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
			<input type="hidden" name="pageID" value="NSAL0880Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Meter Reading Counter for Interface Popup">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<!-- ######################################## Header ######################################## -->
				<table border="0" width="99%" align="center">
					<col width="275">
					<col width="50">
					<col width="120">
					<col width="230">
					<tr>
						<td></td>
						<td class="stab">Serial#</td>
						<td><ezf:inputText name="serNum" ezfName="serNum" value="1---------2---------3---------" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
						<td></td>
					</tr>
				</table>
				<hr>

<!-- ######################################## DETAIL #################################### -->
				<table border="0" cellpadding="0" cellspacing="0" align="center">
					<col align="left" valign="top">
					<tr>
						<td>
							<div id="Top" style="width:700; text-align:left; overflow-x:hidden; overflow-y:hidden;">
								<table border="1" cellpadding="1" cellspacing="0" height="23" align="center">
									<col width="180" align="center">
									<col width="110"  align="center">
									<col width="300"  align="center">
									<col width="110" align="center">
									<tr>
										<td class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrLbDescTxt_A')">Counter Name</a><img id="sortIMG.mtrLbDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'readMtrCnt_A')">Meter Read</a><img id="sortIMG.readMtrCnt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsMsgTxt_A')">Message</a><img id="sortIMG.dsMsgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsMtrProcStsDescTxt_A')">Status</a><img id="sortIMG.dsMtrProcStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
									</tr>
								</table>
							</div>
							<div id="Detail" style="width:717; overflow-x:hidden; overflow-y:scroll; height:446;">
								<table id="A" border="1" cellpadding="1" cellspacing="0" align="center">
									<col width="180" align="center">
									<col width="110"  align="center">
									<col width="300"  align="center">
									<col width="110" align="center">
									<ezf:row ezfHyo="A">
										<tr height="23">
											<td><ezf:inputText name="mtrLbDescTxt_A" ezfName="mtrLbDescTxt_A" value="1---------2---------3---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"50\" id=\"mtrLbDescTxt_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="readMtrCnt_A" ezfName="readMtrCnt_A" value="1---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"10\" id=\"readMtrCnt_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="dsMsgTxt_A" ezfName="dsMsgTxt_A" value="1---------2---------3---------4---------5---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"1000\" id=\"dsMsgTxt_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="dsMtrProcStsDescTxt_A" ezfName="dsMtrProcStsDescTxt_A" value="1---------2----" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"15\" id=\"dsMtrProcStsDescTxt_A#{EZF_ROW_NUMBER}\""/></td>
										</tr>
									<ezf:skip>
									</ezf:skip>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
