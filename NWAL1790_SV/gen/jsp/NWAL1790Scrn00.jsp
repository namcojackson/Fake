<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160524163727 --%>
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
			<input type="hidden" name="pageID" value="NWAL1790Scrn00">

<%@ page import="business.servlet.NWAL1790.NWAL1790BMsg" %>
<%  NWAL1790BMsg bMsg = (NWAL1790BMsg)databean.getEZDBMsg(); %>

			<!-- Set Page Name -->
			<% String procMd = bMsg.xxProcMd.getValue(); %>
			<% if("1".equals(procMd)){ %>
				<input type="hidden" name="pageName" value="Confirmation Popup">
			<% } %>
			<% if("2".equals(procMd)) { %>
				<input type="hidden" name="pageName" value="Tracking Notification Popup">
			<% } %>

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
											<% if("1".equals(procMd)){ %>
												<label><b>Please Select Confirmation Method (Select All That Apply)</b></label>
											<% } %>
											<% if("2".equals(procMd)) { %>
												<label><b>Please Select Emails to Provide Tracking Notification (Select All That Apply)</b></label>
											<% } %>
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
								<% if("1".equals(procMd)){ %>
								<col align="left" width="80">
								<col align="left" width="30">
								<% }%>
								<tr height="22" align="left">
									<td class="pClothBs">Mail Send</td>
									<td><ezf:inputCheckBox name="xxRqstFlg_ML" ezfName="xxRqstFlg_ML" value="Y" /></td>
									<% if("1".equals(procMd)){ %>
									<td class="pClothBs">Print</td>
									<td><ezf:inputCheckBox name="xxRqstFlg_PR" ezfName="xxRqstFlg_PR" value="Y" /></td>
									<% } %>
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
						<div style="width:630; height:310">
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
					</tr>
				</div>
			</table>
			</center>



<%-- **** Task specific area ends here **** --%>
