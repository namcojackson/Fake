<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160809142716 --%>
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
			<input type="hidden" name="pageID" value="NMAL7030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Qty Discount Popup">

<!-- Qty Discount HTML start -->

<%@ page import="business.servlet.NMAL7030.NMAL7030BMsg" %>
<%@ page import="business.servlet.NMAL7030.NMAL7030_ABMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<% NMAL7030BMsg bMsg = (NMAL7030BMsg) databean.getEZDBMsg(); %>

			<center>
<!-- Header Start --->
				<br/>
				<table border="1" cellpadding="1" cellspacing="1">
					<col align="center" width="150" >
					<col align="center" width="150" >
					<col align="center" width="150" >
					<tr>
						<td class="pClothBs">Qualifier Type</td>
						<td class="pClothBs">Value</td>
						<td class="pClothBs">Description</td>
					</tr>
					<tr>
						<td>
							<ezf:select name="prcQlfyTpCd_H" ezfName="prcQlfyTpCd_H" ezfBlank="1" ezfCodeName="prcQlfyTpCd_L" ezfDispName="prcQlfyTpDescTxt_L" otherAttr=" style=\"width: 110px\""/>
						</td>
						<td><ezf:inputText name="prcQlfyValTxt_H" ezfName="prcQlfyValTxt_H" value="123456789012345678901234567890" otherAttr=" size=\"20\""/></td>
						<td><ezf:inputText name="mdseDescShortTxt_H" ezfName="mdseDescShortTxt_H" value="123456789012345678901234567890" otherAttr=" size=\"50\""/></td>
					</tr>
				</table>
<!-- Header End --->
				<hr/>
<!-- Detail Start --->
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="stab" height="24" align="left">
							Show Delete&nbsp;<ezf:inputCheckBox name="xxChkBox_D" ezfName="xxChkBox_D" value="Y" onClick="sendServer('ShowDel')" />
						</td>
						<td height="24" align="right" style="padding-right: 15px;">
							<ezf:inputButton name="Add_QtyDisc" value="Add" htmlClass="pBtn3" />
							<ezf:inputButton name="Del_QtyDisc" value="Del" htmlClass="pBtn3" />
						</td>
					</tr>
					<tr>
						<td valign="top" colspan="2">
							<table style="table-layout:fixed; " border="1" cellpadding="1" cellspacing="0" width="320px" >
								<col align="center" width="30" >	<!-- Delete/Update			-->
								<col align="center" width="100" >	<!-- Qty					-->
								<col align="center" width="120">	<!-- Price Break Amt 		-->
								<col align="center" width="70" >	<!-- Status			 		-->
								<tr>
									<td class="pClothBs">Del<br>Upd</td>
									<td class="pClothBs">Qty</td>
									<td class="pClothBs">Price Break Amt</td>
									<td class="pClothBs">Status</td>
								</tr>
							</table>
							<div id="RightTable_A" style="overflow-y:scroll; height:420; overflow-x:hidden; width:347; float:left;">
							<table style="table-layout:fixed; overflow-x:hidden; overflow-y:scroll;" border="1" cellpadding="1" cellspacing="0" width="250px" >
								<col align="center" width="30" >	<!-- Delete/Update			-->
								<col align="center" width="100" >	<!-- Qty					-->
								<col align="center" width="120">	<!-- Price Break Amt 		-->
								<col align="center" width="70" >	<!-- Status			 		-->
<% int i = 0; %>
								<ezf:row ezfHyo="A">
<% NMAL7030_ABMsg lineMsg = bMsg.A.no(i++); %>
<% if (ZYPCommonFunc.hasValue(bMsg.xxChkBox_D) || !"Y".equals(lineMsg.delFlg_A.getValue())) { %>
								<tr id="id_row{EZF_ROW_NUMBER}">
<% } else { %>
								<tr id="id_row{EZF_ROW_NUMBER}" style="display:none;">
<% } %>
									<td>
<% if (!"Y".equals(lineMsg.delFlg_A.getValue())) { %>
										<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
<% } else { %>
										<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="1" otherAttr=" style=\"display:none;\""/>
<% } %>
									</td>
									<td><ezf:inputText name="qtyDiscDtlQty_A" ezfName="qtyDiscDtlQty_A" value="1,234,567,890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"13\" style=\"text-align:right\""/></td>
									<td><ezf:inputText name="prcBreakAmt_A" ezfName="prcBreakAmt_A" value="-123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"19\" style=\"text-align:right\""/></td>
									<td><ezf:inputText name="xxScrStsTxt_A" ezfName="xxScrStsTxt_A" value="Active" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
								</tr>
								</ezf:row>
								<ezf:skip>
								</ezf:skip>
							</table>
							</div>
						</td>
					</tr>
				</table>
<!-- Detail End -->
			</center>
<!-- Qty Discount HTML end -->


<%-- **** Task specific area ends here **** --%>
