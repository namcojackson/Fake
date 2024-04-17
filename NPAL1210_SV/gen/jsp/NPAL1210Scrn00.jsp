<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160701151418 --%>
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
			<input type="hidden" name="pageID" value="NPAL1210Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="PO/Inventory Approval History">

			<table border="0" cellpadding="1" cellspacing="1" style="margin-top:10px; margin-left:22px;">
				<col width="120px" align="left"><%-- Document Source Type (Label) --%>
				<col width="146px" align="left"><%-- Document Source Type --%>
				<col width="10px"  align="left"><%-- ===== space ===== --%>
				<col width="75px"  align="left"><%-- Order Number (L) --%>
				<col width="146px" align="left"><%-- Order Number --%>
				<tr>
					<td class="stab">Document Source Type</td>
					<td><ezf:inputText name="apvlHistSrcTpDescTxt" ezfName="apvlHistSrcTpDescTxt" value="PO Requition" otherAttr=" size=\"20\""/></td>
					<td>&nbsp;</td>
					<td class="stab">Order Number</td>
					<td><ezf:inputText name="trxRefNum" ezfName="trxRefNum" value="11331" otherAttr=" size=\"20\""/></td>
				</tr>
			</table>
			
			<hr>

			<table border="0" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:22px">
				<tr>
					<td valign="top">
						<!--<div style="overflow-x:none; overflow-y:none;">-->
						<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
							<col width="40px"  align="center">
							<col width="80px"  align="center">
							<col width="180px" align="center">
							<col width="80px"  align="center">
							<col width="120px" align="center">
							<col width="245px" align="center">
							<tr height="28px">
								<td class="pClothBs">Seq</td>
								<td class="pClothBs" colspan="2">Who</td>
								<td class="pClothBs">Action</td>
								<td class="pClothBs">Date</td>
								<td class="pClothBs">Note</td>
							</tr>
						</table>
						<!--</div>-->
						<div style="overflow-x:none; overflow-y:auto; height:455px;">
							<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;">
								<col width="40px"  align="right">
								<col width="80px"  align="center">
								<col width="180px" align="left">
								<col width="80px"  align="left">
								<col width="120px" align="left">
								<col width="245px" align="left">
								<ezf:row ezfHyo="A">
									<tr height="28px">
										<td><ezf:label name="seqNumber_A1" ezfName="seqNumber_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="apvlByPsnCd_A1" ezfName="apvlByPsnCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="apvlFullPsnNm_A1" ezfName="apvlFullPsnNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="apvlHistActTpDescTxt_A1" ezfName="apvlHistActTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="xxDtTm_A1" ezfName="xxDtTm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:inputText name="apvlHistTxt_A1" ezfName="apvlHistTxt_A1" value="note" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"border-width:0px;border-style:None; background-color:#fcfcfc;\" size=\"33\" id=\"note{EZF_ROW_NUMBER}\""/></td>
									</tr>
								</ezf:row>
									<!--
									<%-- ----- line 1 ----- --%>
									<tr height="28px">
										<td><label>3</label></td>
										<td><label>Jakimas, Amy Cathleen</label></td>
										<td><label>Approve</label></td>
										<td><label>05/15/2015</label></td>
										<td><label>&nbsp;</label></td>
									</tr>
									<%-- ----- line 2 ----- --%>
									<tr height="28px">
										<td><label>2</label></td>
										<td><label>Miller, Debbie Lynn</label></td>
										<td><label>Forward</label></td>
										<td><label>05/15/2015</label></td>
										<td><label>&nbsp;</label></td>
									</tr>
									<%-- ----- line 3 ----- --%>
									<tr height="28px">
										<td><label>1</label></td>
										<td><label>Miller, Debbie Lynn</label></td>
										<td><label>Submit</label></td>
										<td><label>05/15/2015</label></td>
										<td><label>&nbsp;</label></td>
									</tr>
									-->
							</table>
						</div>
					</td>
				</tr>
			</table>

<%-- **** Task specific area ends here **** --%>
