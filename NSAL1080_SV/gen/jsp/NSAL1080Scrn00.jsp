<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160129150359 --%>
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
			<input type="hidden" name="pageID" value="NSAL1080Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Credit Rebill Main Screen">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Credit Rebill Main Screen" class="pTab_ON"><a href="#">Cr Rbl Main</a></li>
									</div>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<div class="pTab_BODY">
				<table border="0" width="99%" align="center">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="200" align="left">
								<tr height="24">
									<td class="stab"><b>Customer Incident</b></td>
								</tr>
							</table>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="20">
								<col width="110" align="left">
								<col width="100">
								<col width="20">
								<col width="60" align="left">
								<col width="90">
								<col width="20">
								<col width="70" align="left">
								<col width="150">
								<tr height="24">
									<td>&nbsp;</td>
									<td class="stab">Customer Incident#</td>
									<td><ezf:inputText name="custIncdtId" ezfName="custIncdtId" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab">CR Status</td>
									<td><ezf:inputText name="svcCrRebilStsDescTxt" ezfName="svcCrRebilStsDescTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
									<td>&nbsp;</td>
									<td class="stab">Description</td>
									<td><ezf:inputText name="svcCrRebilDescTxt" ezfName="svcCrRebilDescTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"50\" maxlength=\"4000\""/></td>
								</tr>
							</table>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="850">
								<col width="">
								<col width="2">
								<col width="">
								<tr height="24">
									<td>&nbsp;</td>
									<td><ezf:inputButton name="Preview" value="Preview" htmlClass="pBtn8" otherAttr=" id=\"Preview\""/></td>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="CancelCI" value="Cancel CI" htmlClass="pBtn8" otherAttr=" id=\"CancelCI\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>
				<table border="0" width="99%" align="center">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="200" align="left">
								<tr height="24">
									<td class="stab"><b>Research Invoices</b></td>
								</tr>
							</table>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="20">
								<col width="60" align="left">
								<col width="150">
								<col width="30">
								<col width="100" align="left">
								<col width="95">
								<col width="55">
								<col width="30">
								<col width="100" align="left">
								<col width="95">
								<col width="55">
								<col width="40">
								<col width="">
								<tr height="24">
									<td>&nbsp;</td>
									<td class="stab">Invoice#</td>
									<td><ezf:inputText name="svcInvNum" ezfName="svcInvNum" value="XXXXXXXXX1XXX" otherAttr=" size=\"20\" maxlength=\"13\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab">Contract#</td>
									<td colspan="2"><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab">Consolidated Bill#</td>
									<td colspan="2"><ezf:inputText name="conslBillPk" ezfName="conslBillPk" value="9999999991999999999299999999" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>
									<td colspan="3">&nbsp;</td>
								</tr>
								<tr height="24">
									<td>&nbsp;</td>
									<td class="stab">Serial#</td>
									<td><ezf:inputText name="serNum" ezfName="serNum" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td class="stab">Billed From Date</td>
									<td><ezf:inputText name="bllgPerFromDt" ezfName="bllgPerFromDt" value="MM/DD/YYYY" otherAttr=" size=\"12\" maxlength=\"10\""/></td>
									<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgPerFromDt', 4);" ></td>
									<td>&nbsp;</td>
									<td class="stab">Billed Thru Date</td>
									<td><ezf:inputText name="bllgPerToDt" ezfName="bllgPerToDt" value="MM/DD/YYYY" otherAttr=" size=\"12\" maxlength=\"10\""/></td>
									<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgPerToDt', 4);" ></td>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" otherAttr=" id=\"Search\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>
				</div>
			</td>
		</tr>
	</table>
</center>

<style TYPE="text/css">
<!--
	tr.checkLineBGcolor{background-color:yellow;}
	a img{border-style:none;}
-->
</style>


<%-- **** Task specific area ends here **** --%>
