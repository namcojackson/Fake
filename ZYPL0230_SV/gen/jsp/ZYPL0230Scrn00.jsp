<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20131127091747 --%>
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
<fmt:setBundle basename="I18N_ZYPL0230Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="ZYPL0230Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.ZYPL0230Scrn00.title" bundle="${I18N_SCREEN_ID}">CSV Upload Sub</fmt:message>">
			
			<center>
				<table>
					<tr>
						<td>
							
			<%-- ######################################## DETAIL ######################################## --%>
							
							<table border="1" cellpadding="1" cellspacing="0">
								<col width="180"  align="center">
								<col width="570" align="center">
								
								<tr>
									<td class="pClothB" nowrap><fmt:message key="i18n.ZYPL0230Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Name</fmt:message></td>
									<td class="pClothB" nowrap><fmt:message key="i18n.ZYPL0230Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Value</fmt:message></td>
								</tr>
							</table>
							
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="180"  align="center">
									<col width="570" align="left">
										
									<tbody>
										<tr>
											<td nowrap><fmt:message key="i18n.ZYPL0230Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Upload CSV Request PK</fmt:message></td>
											<td><ezf:label name="upldCsvRqstPk" ezfName="upldCsvRqstPk" /></td>
										</tr>
										
										<tr class="pEvenNumberBGcolor">
											<td><fmt:message key="i18n.ZYPL0230Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Date</fmt:message></td>
											<td><ezf:label name="xxUpldCsvDt" ezfName="xxUpldCsvDt" /></td>
										</tr>
										
										<tr>
											<td><fmt:message key="i18n.ZYPL0230Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Time</fmt:message></td>
											<td><ezf:label name="xxUpldCsvTm" ezfName="xxUpldCsvTm" /></td>
										</tr>
										
										<tr class="pEvenNumberBGcolor">
											<td><fmt:message key="i18n.ZYPL0230Scrn00.label.6" bundle="${I18N_SCREEN_ID}">User</fmt:message></td>
											<td><ezf:label name="xxUpldCsvUsrTxt" ezfName="xxUpldCsvUsrTxt" /></td>
										</tr>
										
										<tr>
											<td><fmt:message key="i18n.ZYPL0230Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Status</fmt:message></td>
											<td><ezf:label name="upldCsvStsNm" ezfName="upldCsvStsNm" /></td>
										</tr>
										
										<tr class="pEvenNumberBGcolor">
											<td><fmt:message key="i18n.ZYPL0230Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Result</fmt:message></td>
											<td><ezf:label name="upldCsvRsltNm" ezfName="upldCsvRsltNm" /></td>
										</tr>
										
										<tr>
											<td><fmt:message key="i18n.ZYPL0230Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Message</fmt:message></td>
											<td>
												<div style="height:200; overflow-y:scroll; width:570;">
													<table border="0" cellpadding="1" cellspacing="0" >
														<tbody>
														<ezf:row ezfHyo="A">
															<tr>
																<td>
																	<ezf:label name="upldCsvMsgTxt" ezfName="upldCsvMsgTxt" ezfHyo="A" ezfArrayNo="0" />
																</td>
															</tr>
														</ezf:row>
														<ezf:skip>
															<tr>
																<td>
																	<label ezfout>ZZZM9999E xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5xxxx</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>ZZZM9999E xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5xxxx</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>ZZZM9999E xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5xxxx</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>ZZZM9999E xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5xxxx</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>ZZZM9999E xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5xxxx</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>ZZZM9999E xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5xxxx</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>ZZZM9999E xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5xxxx</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>ZZZM9999E xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5xxxx</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>ZZZM9999E xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5xxxx</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>ZZZM9999E xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5xxxx</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>ZZZM9999E xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5xxxx</label>
																</td>
															</tr>
														</ezf:skip>
													</table>
												</div>
											</td>
										</tr>
										
										<tr class="pEvenNumberBGcolor">
											<td><fmt:message key="i18n.ZYPL0230Scrn00.label.10" bundle="${I18N_SCREEN_ID}">CSV File Name</fmt:message></td>
											<td>
												<div style="height:75; overflow-y:scroll; width:570;">
													<table border="0" cellpadding="1" cellspacing="0" >
														<tbody>
														<ezf:row ezfHyo="B">
															<tr>
																<td>
																	<ezf:label name="upldCsvFileNm" ezfName="upldCsvFileNm" ezfHyo="B" ezfArrayNo="0" />
																</td>
															</tr>
														</ezf:row>
														<ezf:skip>
															<tr>
																<td>
																	<label ezfout>xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5 xxxxxxxx6 xxx</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5 xxxxxxxx6 xxx</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5 xxxxxxxx6 xxx</label>
																</td>
															</tr>
														</ezf:skip>
													</table>
												</div>
											</td>
										</tr>
										
										<tr>
											<td><fmt:message key="i18n.ZYPL0230Scrn00.label.11" bundle="${I18N_SCREEN_ID}">File Description</fmt:message></td>
											<td>
												<div style="height:75; overflow-y:scroll; width:570;">
													<table border="0" cellpadding="1" cellspacing="0" >
														<tbody>
														<ezf:row ezfHyo="C">
															<tr>
																<td>
																	<ezf:label name="upldCsvFileDescTxt" ezfName="upldCsvFileDescTxt" ezfHyo="C" ezfArrayNo="0" />
																</td>
															</tr>
														</ezf:row>
														<ezf:skip>
															<tr>
																<td>
																	<label ezfout>xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5 xxxxxxxx61234</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5 xxxxxxxx61234</label>
																</td>
															</tr>
															<tr>
																<td>
																	<label ezfout>xxxxxxxxx1 xxxxxxxx2 xxxxxxxx3 xxxxxxxx4 xxxxxxxx5 xxxxxxxx61234</label>
																</td>
															</tr>
														</ezf:skip>
													</table>
												</div>
											</tr>
										
									</tbody>
								</table>

							
						</td>
					</tr>
				</table>	
			</center>			


<%-- **** Task specific area ends here **** --%>
