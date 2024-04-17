<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180614095045 --%>
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
			<input type="hidden" name="pageID" value="NWAL2000Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Cancel Popup">

				<%
					String xxModeCd= ((business.servlet.NWAL2000.NWAL2000Bean)databean).getXxModeCd();
				%>
						<center>
							<table style="width:400px">
								<tr>
									<td>
										<fieldset>
											<legend style="font-size:12px;">Cancel Sales Order</legend>
											<table style="table-layout:fixed;" cellspacing="0">
												<col width="10">
												<col width="160">
												<col width="230">
												<tr height="28">
													<td>&nbsp;</td>
													<td>Order Number</td>
													<td><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="ORD001" otherAttr=" size=\"15\" maxlength=\"6\" ezftoupper=\"\""/></td>
												</tr>
											<%if (!xxModeCd.equals("03")) {%>
												<tr height="28">
													<td>&nbsp;</td>
													<td>Config Category</td>
													<td>
														<ezf:select name="configCatgCd" ezfName="configCatgCd" ezfCodeName="configCatgCd_CD" ezfDispName="configCatgDescTxt_DI" otherAttr=" style=\"width : 110px;\""/>
													</td>
												</tr>
											<% } %>
											<%if (xxModeCd.equals("01")) {%>
												<tr height="28">
													<td>&nbsp;</td>
													<td>Config Number</td>
													<td><ezf:inputText name="xxPopPrm_DI" ezfName="xxPopPrm_DI" value="1" otherAttr=" size=\"15\" maxlength=\"300\" ezftoupper=\"\""/></td>
												</tr>
											<% } else if (xxModeCd.equals("02")) { %>
												<tr height="28">
													<td>&nbsp;</td>
													<td>Line Number</td>
													<td><ezf:inputText name="xxPopPrm_DI" ezfName="xxPopPrm_DI" value="1.1" otherAttr=" size=\"15\" maxlength=\"300\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="28">
													<td>&nbsp;</td>
													<td>Cancel QTY</td>
													<td><ezf:inputText name="cancQty" ezfName="cancQty" value="2" otherAttr=" size=\"15\" maxlength=\"10\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="28">
													<td>&nbsp;</td>
													<td>Order QTY Remaining</td>
													<td><ezf:inputText name="xxPopPrm" ezfName="xxPopPrm" value="1" otherAttr=" size=\"15\" maxlength=\"10\" ezftoupper=\"\""/></td>
												</tr>
											<% } %>
												<tr height="28">
													<td>&nbsp;</td>
													<td>Reason</td>
													<td>
														<ezf:select name="chngRsnTpCd" ezfName="chngRsnTpCd" ezfCodeName="chngRsnTpCd_L0" ezfDispName="chngRsnTpDescTxt_L0" otherAttr=" style=\"width : 226px;\""/>
													</td>
												</tr>
												<tr height="60">
													<td>&nbsp;</td>
													<td valign="top"><p style="margin-top: 5pt">Comment</p></td>
													<td><ezf:textArea name="bizProcCmntTxt" ezfName="bizProcCmntTxt" otherAttr=" rows=\"4\" cols=\"29\" ezftoupper=\"\""/></td>
												</tr>
											<%if (!xxModeCd.equals("03")) {%>
												<tr height="28">
													<td>&nbsp;</td>
													<td colspan="2">Cancel all remaining open lines</td>
												</tr>
											<% } %>
												<tr height="28">
													<td colspan = "3" align="center">
														<ezf:inputButton name="CMN_OK" value="OK" htmlClass="pBtn4" />	
														&nbsp;
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
