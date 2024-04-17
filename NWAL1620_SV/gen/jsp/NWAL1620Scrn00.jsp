<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170920173451 --%>
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
			<input type="hidden" name="pageID" value="NWAL1620Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Copy / Copy From Popup">

				<%@ page import="business.servlet.NWAL1620.NWAL1620BMsg" %>
				<% NWAL1620BMsg bizMsg = (NWAL1620BMsg)databean.getEZDBMsg(); %>
						<center>
							<table style="width:330px;">
								<tr>
									<td>
										<fieldset>
											<% if ("01".equals(bizMsg.xxModeCd.getValue())) {%>
												<legend style="font-size:12px;">Copy Level : ORDER</legend>
											<% } else if ("02".equals(bizMsg.xxModeCd.getValue())) { %>
												<legend style="font-size:12px;">Copy Level : CONFIG</legend>
											<% } else { %>
												<legend style="font-size:12px;">Copy Level : LINE</legend>
											<% } %>
											<table style="table-layout:fixed;" cellspacing="0">
												<col width="20">
												<col width="150">
												<col width="160">
											<% if ("01".equals(bizMsg.xxModeCd_CP.getValue())) {%>
												<tr>
													<td colspan="3">Copy From:</td>
												</tr>
											<% } %>
												<tr height="28">
													<td>&nbsp</td>
													<td>Order Number:</td>
													<td><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="ORD001" otherAttr=" size=\"20\" maxlength=\"8\" ezftoupper=\"\""/></td>
												</tr>
											<%if ("01".equals(bizMsg.xxModeCd.getValue())) {%>
												<tr height="28">
													<td>&nbsp</td>
													<td>Copy Only Header :</td>
													<td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" otherAttr=" id=\"xxChkBox\""/></td>
												</tr>
												<tr height="28">
													<td>&nbsp</td>
													<td>Create Return Order From Outbound :</td>
													<td><ezf:inputCheckBox name="xxChkBox_R" ezfName="xxChkBox_R" value="Y" otherAttr=" id=\"xxChkBox_R\""/></td>
												</tr>
											<% } else { %>
												<tr height="28">
													<td>&nbsp</td>
													<td>Config Category</td>
													<td>
														<ezf:select name="configCatgCd" ezfName="configCatgCd" ezfCodeName="configCatgCd_CD" ezfDispName="configCatgDescTxt_DI" otherAttr=" style=\"width : 145px;\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr height="28">
													<td>&nbsp</td>
													<td>Config Number</td>
													<td><ezf:inputText name="dsOrdPosnNum" ezfName="dsOrdPosnNum" value="1.1" otherAttr=" size=\"20\" maxlength=\"6\" ezftoupper=\"\""/></td>
												</tr>
												<%if ("03".equals(bizMsg.xxModeCd.getValue())) {%>
													<tr height="28">
														<td>&nbsp</td>
														<td>Line Number</td>
														<td><ezf:inputText name="xxLineNum_DI" ezfName="xxLineNum_DI" value="1.1" otherAttr=" style=\"text-align: left;\" size=\"20\" maxlength=\"11\" ezftoupper=\"\""/></td>
													</tr>
												<% } %>
												<tr height="28">
													<td>&nbsp</td>
													<td>Number of Copies</td>
													<td><ezf:inputText name="xxQty10Num" ezfName="xxQty10Num" value="1.1" otherAttr=" size=\"20\" maxlength=\"4\" ezftoupper=\"\""/></td>
												</tr>
											<% } %>
												<tr height="28">
												<td colspan = "3" align="center">
														<ezf:inputButton name="CMN_OK" value="OK" htmlClass="pBtn4" />	
														&nbsp
														<ezf:inputButton name="CMN_Cancel" value="Cancel" htmlClass="pBtn4" />
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
							</table>
						</center>

<%-- **** Task specific area ends here **** --%>
