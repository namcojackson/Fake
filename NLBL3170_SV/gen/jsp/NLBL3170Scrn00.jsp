<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20201119091355 --%>
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
			<input type="hidden" name="pageID" value="NLBL3170Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Tracking Number Popup">
<center>
	<table width="100%">
		<tr>
			<td valign="top">
<%-- ######################################## HEADER ######################################## --%>
				<table cellpadding="1" cellspacing="1" border="0" bordercolor="red">
					<col width="235"  align="left">
					<col width="235"  align="left">
					<tr height="20">
					<tr>
						<td class="stab">Source Number</td>
						<td><ezf:inputText name="xxDplyOrdNum_TH" ezfName="xxDplyOrdNum_TH" value="SHM48895" otherAttr=" size=\"\" maxlength=\"8\" ezftoupper=\"\" style=\"width:100%\""/></td>
					</tr>
					<tr>
						<td class="stab">Carrier Code</td>
						<td><ezf:inputText name="wmsCarrCd" ezfName="wmsCarrCd" value="FDXE" otherAttr=" size=\"\" maxlength=\"40\" ezftoupper=\"\" style=\"width:100%\""/></td>
					</tr>
					<tr>
						<td class="stab">Warehouse</td>
						<td><ezf:inputText name="xxMsgTxt" ezfName="xxMsgTxt" value="ITASCA WH - CSA" otherAttr=" size=\"\" maxlength=\"30\" ezftoupper=\"\" ezftoupper=\"\" style=\"width:100%\""/></td>
					</tr>
				</table>
				<hr>
<%-- ######################################## DETAIL ######################################## --%>
				<table border="1" cellpadding="1" cellspacing="0" id="A" >
					<col width="30"  align="center">
					<col width="240" align="center">
					<col width="240" align="center">
					<tr>
						<td class="pClothBs">Line</td>
						<td class="pClothBs">Master Tracking Number</td>
						<td class="pClothBs">Tracking Number</td>
					</tr>
				</table>

				<div style="overflow-x:none; overflow-y:scroll; width:557; height:350;">
					<table border="1" cellpadding="1" cellspacing="0">
						<col width="30"  align="center">
						<col width="240" align="center">
						<col width="240" align="center">
						<tbody>
						<%@ page import="business.servlet.NLBL3170.NLBL3170BMsg" %>
						<% NLBL3170BMsg bMsg = (NLBL3170BMsg)databean.getEZDBMsg(); %>
						<ezf:row ezfHyo="A">
							<% if ("Y".equals(bMsg.xxValUpdFlg.getValue())) { %>
							<tr id="id_row{EZF_ROW_NUMBER}">
								<td><ezf:inputText name="trxLineNum_A1" ezfName="trxLineNum_A1" value="001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" tabindex=\"-1\""/></td>
								<td><ezf:inputText name="mstrProNum_A1" ezfName="mstrProNum_A1" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"32\" maxlength=\"30\" tabindex=\"-1\""/></td>
								<td><ezf:inputText name="proNum_A1" ezfName="proNum_A1" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"32\" maxlength=\"30\""/></td>
							</tr>
							<% } else { %>
							<tr id="id_row{EZF_ROW_NUMBER}">
								<td><ezf:inputText name="trxLineNum_A1" ezfName="trxLineNum_A1" value="001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" tabindex=\"-1\""/></td>
<!-- START 2020/11/18 [QC#57659,MOD] -->
								<td>
									<ezf:anchor name="carrEncTrkUrl_A1" ezfName="carrEncTrkUrl_A1" ezfHyo="A" ezfArrayNo="0" onClick="window.open( this.href, '_blank', 'status=yes, toolbar=yes, location=yes, menubar=yes, scrollbars=yes, resizable=yes');return false;" otherAttr=" id=\"carrTrk1{EZF_ROW_NUMBER}\"">
										<ezf:label name="mstrProNum_A1" ezfName="mstrProNum_A1" ezfHyo="A" ezfArrayNo="0" />
									</ezf:anchor>
								</td>
								<td>
									<ezf:anchor name="carrEncTrkUrl_A2" ezfName="carrEncTrkUrl_A2" ezfHyo="A" ezfArrayNo="0" onClick="window.open( this.href, '_blank', 'status=yes, toolbar=yes, location=yes, menubar=yes, scrollbars=yes, resizable=yes');return false;" otherAttr=" id=\"carrTrk2{EZF_ROW_NUMBER}\"">
										<ezf:label name="proNum_A1" ezfName="proNum_A1" ezfHyo="A" ezfArrayNo="0" />
									</ezf:anchor>
								</td>
<!-- END 2020/11/18 [QC#57659,MOD] -->
							</tr>
							<% } %>
						</ezf:row>
						<ezf:skip>
						</ezf:skip>
						</tbody>
				</table>
				</div>
			
			<table border="0" cellpadding="1" cellspacing="1" id="A">
				<col width="520" align="right">
				<tr height="34">
				<TD><ezf:inputButton name="AddLine" value="Add New Line" htmlClass="pBtn9" /></TD>
				</tr>
			</table>

<%-- ######################################## Footer ######################################## --%>
			</td>
		</tr>
	</table>
</center>



<%-- **** Task specific area ends here **** --%>
