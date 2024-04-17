<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180515190618 --%>
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
			<input type="hidden" name="pageID" value="NWAL2420Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Return Authorization Popup">


<%@ page import="business.servlet.NWAL2420.NWAL2420BMsg" %>
<%  NWAL2420BMsg bMsg = (NWAL2420BMsg)databean.getEZDBMsg(); %>

			<center>
			<table width="100%">
				<div class="pTab_BODY">
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" >
								<col align="center">
								<tr>
									<td>
										<br>
										<label><b>Please Select Authorization Method (Select All That Apply)</b></label>
										<br>
										<br>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td>
							<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed; margin-left:80px" >
								<col align="left" width="80">
								<col align="left" width="30">
								<col align="left" width="80">
								<col align="left" width="30">
								<tr height="22" align="left">
									<td class="pClothBs">Mail Send</td>
									<td><ezf:inputCheckBox name="xxRqstFlg_ML" ezfName="xxRqstFlg_ML" value="Y" /></td>
									<td class="pClothBs">Print</td>
									<td><ezf:inputCheckBox name="xxRqstFlg_PR" ezfName="xxRqstFlg_PR" value="Y" /></td>
								</tr>
							</table>
						</td>
					</tr>
<%-- ######################################## DETAIL ######################################## --%>

					<tr>
							<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
								<col align="center" width="30">
								<col align="center" width="600">
								<tr height="22">
									<td class="pClothBs">&nbsp;</td>
									<td class="pClothBs" align="center">EMail  Address</td>
								</tr>
							</table>
						<div style="width=630; height:310; overflow-x:none; overflow-y:scroll;margin-left:17px">
							<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
								<col align="center" width="30">
								<col align="left" width="600">
								<ezf:row ezfHyo="A">
								<tr height="22">
									<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="ctacPsnEmlAddr_A" ezfName="ctacPsnEmlAddr_A" value="wwwwwwwwww1wwwwwwwww2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"80\" maxlength=\"320\""/></td>
								</tr>
								</ezf:row>
							</table>
						</div>
						<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" >
								<col align="left" width="630">
							<tr>
								<td><br><label><b>Supplies To Be Return By (Select One)</b></label></td>
							</tr>
							<tr>
								<td class="stab">
									<ezf:inputRadio name="rmaRptTpCd" ezfName="rmaRptTpCd" value="1" />&nbsp;<b>Customer</b>
									<br>
									<ezf:inputRadio name="rmaRptTpCd" ezfName="rmaRptTpCd" value="2" />&nbsp;<b>CSA</b>
								</td>
							</tr>
							<tr>
								<td><br><label><b>Comments To Add To The Return Authorization</b></label></td>
							</tr>
							<tr>
								<td>
									<ezf:textArea name="rtrnAuthCmntTxt_01" ezfName="rtrnAuthCmntTxt_01" otherAttr=" maxlength=\"250\" rows=\"5\" cols=\"70\""/>
								</td>
							</tr>
						</div>
					</tr>
				</div>
			</table>
			</center>



<%-- **** Task specific area ends here **** --%>
