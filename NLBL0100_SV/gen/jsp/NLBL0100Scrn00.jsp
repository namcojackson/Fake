<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20151015012249 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- I18N START --%>
<%@ page import="parts.i18n.*" %>

<%	pageContext.setAttribute("ezdi18nlocale", EZDI18NContext.getInstance().getI18NAccessor().getLocale()); %>
<fmt:setLocale value="${ezdi18nlocale}" scope="request" />
<fmt:setBundle basename="I18N_NLBL0100Scrn00" var="I18N_SCREEN_ID" scope="request" />
<fmt:setBundle basename="I18N" var="I18N_DEFAULT" scope="request" />
<%-- I18N END --%>

<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NLBL0100Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NLBL0100Scrn00.title" bundle="${I18N_SCREEN_ID}">BOL Tracking History</fmt:message>">

			<center>
				<table width="100%">
					<tr>
						<td>
						
			<%-- ######################################## HEADER ######################################## --%>
							<table style="height:534;" cellpadding="0" cellspacing="0" border="0">
								<tr valign="top" height="30">
									<td colspan="2">
										<table>
											<col width="50">
											<col width="256">
											<col width="30">

											<col width="80">
											<col width="256">
											<col width="30">

											<col width="41">
											<col width="128">

											<tr>
												<td class="stab"><fmt:message key="i18n.NLBL0100Scrn00.label.1" bundle="${I18N_SCREEN_ID}">B/L#</fmt:message></td>
												<td class="pOut"><ezf:label name="bolNum" ezfName="bolNum" /></td>
												<td></td>
												<td class="stab"><fmt:message key="i18n.NLBL0100Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Tracking/Pro#</fmt:message></td>
												<td class="pOut"><ezf:label name="proNum" ezfName="proNum" /></td>
												<td></td>
												<td class="stab"><fmt:message key="i18n.NLBL0100Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Carrier&nbsp;</fmt:message></td>
												<td class="pOut"><ezf:label name="carrNm" ezfName="carrNm" /></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr valign="top" height="50">
									<td colspan="2">
										<table border="1" cellpadding="0" cellspacing="0">
											<col width="150" align="center">
											<col width="150" align="center">
											<col width="100" align="center">
											<tr>
												<td class="pClothBs"><fmt:message key="i18n.NLBL0100Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Origin</fmt:message></td>
												<td class="pClothBs"><fmt:message key="i18n.NLBL0100Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Destination</fmt:message></td>
												<td class="pClothBs"><fmt:message key="i18n.NLBL0100Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Date Received</fmt:message></td>
											</tr>
											<tr>
												<td align="left"><ezf:label name="shpprCtyNm" ezfName="shpprCtyNm" /></td>
												<td align="left"><ezf:label name="cnsgnCtyNm" ezfName="cnsgnCtyNm" /></td>
												<td><ezf:label name="ezInTime" ezfName="ezInTime" /></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr valign="top" height="100">
									<td align="left">
										<table border="1" cellpadding="0" cellspacing="0" width="495">
											<col width="70" align="left">
											<col width="95" align="left">
											<col width="165" align="left">
											<col width="165" align="left">
											<tr>
												<td class="pClothBs" colspan="4" align="center"><fmt:message key="i18n.NLBL0100Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Shipper</fmt:message></td>
											</tr>
											<tr>
												<td class="pClothBs"><fmt:message key="i18n.NLBL0100Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Name</fmt:message></td>
												<td colspan="3"><ezf:label name="shpprNm" ezfName="shpprNm" /></td>
											</tr>
											<tr>
												<td class="pClothBs"><fmt:message key="i18n.NLBL0100Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Address</fmt:message></td>
												<td colspan="3"><ezf:label name="shpprAddr" ezfName="shpprAddr" /></td>
											</tr>
										</table>
										<table border="1" cellpadding="0" cellspacing="0" width="495">
											<col width="70" align="left">
											<col width="95" align="left">
											<col width="180" align="left">
											<col width="150" align="left">
											<tr>
												<td class="pClothBs" colspan="2"><fmt:message key="i18n.NLBL0100Scrn00.label.10" bundle="${I18N_SCREEN_ID}">City</fmt:message></td>
												<td class="pClothBs"><fmt:message key="i18n.NLBL0100Scrn00.label.11" bundle="${I18N_SCREEN_ID}">State</fmt:message></td>
												<td class="pClothBs"><fmt:message key="i18n.NLBL0100Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Post Code</fmt:message></td>
											</tr>
											<tr>
												<td colspan="2"><ezf:label name="shpprCtyNm" ezfName="shpprCtyNm" /></td>
												<td><ezf:label name="stNm_SP" ezfName="stNm_SP" /></td>
												<td><ezf:label name="shpprPostCd" ezfName="shpprPostCd" /></td>
											</tr>
										</table>
									</td>
									<td align="left">
										<table border="1" cellpadding="0" cellspacing="0" width="495">
											<col width="70" align="left">
											<col width="95" align="left">
											<col width="180" align="left">
											<col width="150" align="left">
											<tr>
												<td class="pClothBs" colspan="4" align="center"><fmt:message key="i18n.NLBL0100Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Consignee</fmt:message></td>
											</tr>
											<tr>
												<td class="pClothBs"><fmt:message key="i18n.NLBL0100Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Name</fmt:message></td>
												<td colspan="3"><ezf:label name="podCnsgnNm" ezfName="podCnsgnNm" /></td>
											</tr>
											<tr>
												<td class="pClothBs"><fmt:message key="i18n.NLBL0100Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Address</fmt:message></td>
												<td colspan="3"><ezf:label name="cnsgnAddr" ezfName="cnsgnAddr" /></td>
											</tr>
										</table>
										<table border="1" cellpadding="0" cellspacing="0" width="495">
											<col width="70" align="left">
											<col width="95" align="left">
											<col width="165" align="left">
											<col width="165" align="left">
											<tr>
												<td class="pClothBs" colspan="2"><fmt:message key="i18n.NLBL0100Scrn00.label.10" bundle="${I18N_SCREEN_ID}">City</fmt:message></td>
												<td class="pClothBs"><fmt:message key="i18n.NLBL0100Scrn00.label.11" bundle="${I18N_SCREEN_ID}">State</fmt:message></td>
												<td class="pClothBs"><fmt:message key="i18n.NLBL0100Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Post Code</fmt:message></td>
											</tr>
											<tr>
												<td colspan="2"><ezf:label name="cnsgnCtyNm" ezfName="cnsgnCtyNm" /></td>
												<td><ezf:label name="stNm_CS" ezfName="stNm_CS" /></td>
												<td><ezf:label name="cnsgnPostCd" ezfName="cnsgnPostCd" /></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr valign="middle" height="20"><td colspan="2" width="1007"><hr></td></tr>
								<tr>
									<td colspan="2">
										<%-- ###Showing --%>
										<table border="0" cellpadding="1" cellspacing="0">
										<col width="974"  align="right">
										<tr>
											<td>
												<%-- Pagenation --%>
												<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
													<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
													<jsp:param name="TableNm"     value="A" />
													<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
													<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
													<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
												</jsp:include>
											</td>
										</tr>
										</table>
									</td>
								</tr>
								<tr valign="top">
									<td colspan="2">
										<table border="1" cellpadding="1" cellspacing="0">
											<tr>
												<td>
													<table border="1" cellpadding="1" cellspacing="0" width="974">
														<col width="40" align="center">
														<col width="220" align="center">
														<col width="80" align="center">
														<col width="100" align="center">
														<col width="112" align="center">
														<col width="72" align="center">
														<col width="40" align="center">
														<col width="72" align="center">
														<col width="40" align="center">
														<col width="144" align="center">

														<tr>
															<td class="pClothBs" colspan="3"><fmt:message key="i18n.NLBL0100Scrn00.label.17" bundle="${I18N_SCREEN_ID}">POD Status</fmt:message></td>
															<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NLBL0100Scrn00.label.18" bundle="${I18N_SCREEN_ID}">POD Source Type</fmt:message></td>
															<td class="pClothBs" colspan="3"><fmt:message key="i18n.NLBL0100Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Loading</fmt:message></td>
															<td class="pClothBs" colspan="3"><fmt:message key="i18n.NLBL0100Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Exception</fmt:message></td>
														</tr>
														<tr>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'podStsCd' )"><fmt:message key="i18n.NLBL0100Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Code</fmt:message><img id="sortIMG.podStsCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'podStsNm' )"><fmt:message key="i18n.NLBL0100Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Name</fmt:message><img id="sortIMG.podStsNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'podStsDt' )"><fmt:message key="i18n.NLBL0100Scrn00.label.22" bundle="${I18N_SCREEN_ID}">Date</fmt:message><img id="sortIMG.podStsDt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'podWt' )"><fmt:message key="i18n.NLBL0100Scrn00.label.23" bundle="${I18N_SCREEN_ID}">Weight</fmt:message><img id="sortIMG.podWt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'podLdgQty' )"><fmt:message key="i18n.NLBL0100Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Q'ty</fmt:message><img id="sortIMG.podLdgQty" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'podPkgCd' )"><fmt:message key="i18n.NLBL0100Scrn00.label.25" bundle="${I18N_SCREEN_ID}">UOM</fmt:message><img id="sortIMG.podPkgCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'podExLdgQty' )"><fmt:message key="i18n.NLBL0100Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Q'ty</fmt:message><img id="sortIMG.podExLdgQty" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'podExCd' )"><fmt:message key="i18n.NLBL0100Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Code</fmt:message><img id="sortIMG.podExCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'podExNm' )"><fmt:message key="i18n.NLBL0100Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Name</fmt:message><img id="sortIMG.podExNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														</tr>
													</table>
													<div id="aTbl" style="overflow-y:scroll; overflow-x:none; height:281;">
														<table border="1" cellpadding="1" cellspacing="0" width="974" id="A_Table">

															<col width="40" align="left">
															<col width="220" align="left">
															<col width="80" align="left">
															<col width="100" align="left">
															<col width="112" align="right">
															<col width="72" align="right">
															<col width="40" align="left">
															<col width="72" align="right">
															<col width="40" align="left">
															<col width="144" align="left">

															<ezf:row ezfHyo="A">
																<tr>
																	<td><ezf:label name="podStsCd" ezfName="podStsCd" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:inputText name="podStsNm" ezfName="podStsNm" value="1234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" tabindex=\"-1\""/></td>
																	<td><ezf:label name="podStsDt" ezfName="podStsDt" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:inputText name="podSrcTpNm" ezfName="podSrcTpNm" value="1234567890123456789012345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\""/></td>
																	<td><ezf:label name="podWt" ezfName="podWt" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:label name="podLdgQty" ezfName="podLdgQty" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:label name="podPkgCd" ezfName="podPkgCd" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:label name="podExLdgQty" ezfName="podExLdgQty" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:label name="podExCd" ezfName="podExCd" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:inputText name="podExNm" ezfName="podExNm" value="123456789012345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" tabindex=\"-1\""/></td>
																</tr>
															</ezf:row>

															<ezf:skip>
																<tr>
																	<td><label ezfout>12</label></td>
																	<td><input type="text" readonly class="pPro" size="30" value="1234567890123456789012345678901234567890"></td>
																	<td><label ezfout>mm/dd/yyyy</label></td>
																	<td><input type="text" readonly class="pPro" size="13" tabindex="-1" value="1234567890123456789012345678901234567890123456789012345678901234567890"></td>
																	<td><label ezfout>1,234,567.1234</label></td>
																	<td><label ezfout>1,234,567</label></td>
																	<td><label ezfout>123</label></td>
																	<td><label ezfout>1,234,567</label></td>
																	<td><label ezfout>123</label></td>
																	<td><input type="text" readonly class="pPro" size="19" tabindex="-1" value="123456789012345678"></td>
																</tr>
															</ezf:skip>

														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>

			<%-- ######################################## FOOTER ######################################## --%>

						</td>
					</tr>
				</table>
			</center>



<%-- **** Task specific area ends here **** --%>
