<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190328110806 --%>
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
			<input type="hidden" name="pageID" value="NWAL2290Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Order List Popup">
			
				<table align="center">
					<tr>
						<td>
							<div id="Hdr" style="overflow-x:hidden; width:924;" onScroll="synchroScrollLeft( this.id, new Array( 'Dtl' ));">
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
									<col width="120" align="center">
									<col width="200" align="center">
									<col width="200" align="center">
									<col width="600" align="center">
									<tr>
										<td class="pClothBs">Order Number</td>
										<td class="pClothBs">Order Source Reference</td>
										<td class="pClothBs">Order Creation Status</td>
										<td class="pClothBs">Creation Status Message</td>
									</tr>
								</table>
							</div>
							<div id="Dtl" style="overflow-x:scroll; overflow-y:scroll; height:515; width:940;" onScroll="synchroScrollLeft( this.id, new Array( 'Hdr' ));">
								<table id="A" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
									<col width="120" align="center">
									<col width="200" align="center">
									<col width="200" align="center">
									<col width="600" align="left">
									<tbody>
										<ezf:row ezfHyo="A">
											<tr id="id_row{EZF_ROW_NUMBER}">
												<td>
													<ezf:anchor name="cpoOrdNum_A" ezfName="cpoOrdNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_OrderEntry" otherAttr=" ezfanchortext=\"\""><ezf:label name="cpoOrdNum_A" ezfName="cpoOrdNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
												</td>
												<td>
													<ezf:label name="ordSrcRefNum_A" ezfName="ordSrcRefNum_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:label name="imptStsDescTxt_A" ezfName="imptStsDescTxt_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:label name="dsImptOrdErrTxt_A" ezfName="dsImptOrdErrTxt_A" ezfHyo="A" ezfArrayNo="0" />
												</td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr>
												<td>
													<a href="#" name="cpoOrdNum_A" ezfname="cpoOrdNum_A" onclick="sendServer('MoveWin_OrderEntry')" ezfanchortext ezfhyo="A"><label ezfout name="cpoOrdNum_A" ezfname="cpoOrdNum_A" ezfhyo="A">&nbsp;</label></a>
												</td>
												<td>
													<label ezfout name="ordSrcRefNum_A" ezfname="ordSrcRefNum_A" ezfhyo="A">E99000008 10-1 (B-1)</label>
												</td>
												<td>
													<label ezfout name="imptStsDescTxt_A" ezfname="imptStsDescTxt_A" ezfhyo="A">Error</label>
												</td>
												<td>
													<label ezfout name="dsImptOrdErrTxt_A" ezfname="dsImptOrdErrTxt_A" ezfhyo="A">NWZM2234E There is no valid "Model ID" being set.</label>
												</td>
											</tr>
										</ezf:skip>
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>

<%-- **** Task specific area ends here **** --%>
