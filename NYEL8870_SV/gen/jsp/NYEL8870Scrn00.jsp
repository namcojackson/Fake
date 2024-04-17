<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160704115139 --%>
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
<fmt:setBundle basename="I18N_NYEL8870Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NYEL8870Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NYEL8870Scrn00.title" bundle="${I18N_SCREEN_ID}">User Preference</fmt:message>">


			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>

							<%-- ###TAB - HEAD --%>
							<div class="pTab_HEAD">
<%-- After Convert to JSP, this area suppose to be deleted [FROM] --%>
							<ezf:skip>
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="<fmt:message key="i18n.NYEL8870Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Work List</fmt:message>" class="pTab_OFF"><a href="#"><fmt:message key="i18n.NYEL8870Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Work List</fmt:message></a></li>
													<li title="<fmt:message key="i18n.NYEL8870Scrn00.title.2" bundle="${I18N_SCREEN_ID}">Delegation</fmt:message>" class="pTab_OFF"><a href="#"><fmt:message key="i18n.NYEL8870Scrn00.title.2" bundle="${I18N_SCREEN_ID}">Delegation</fmt:message></a></li>
													<li title="<fmt:message key="i18n.NYEL8870Scrn00.title.3" bundle="${I18N_SCREEN_ID}">User Prf</fmt:message>" class="pTab_ON"><a href="#"><fmt:message key="i18n.NYEL8870Scrn00.title.3" bundle="${I18N_SCREEN_ID}">User Prf</fmt:message></a></li>
												</div>
											</td>
											<td valign="bottom" align="center">
												<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
											</td>
											<td valign="bottom" align="center">
												<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
											</td>
										</tr>
									</table>
								</ul>
							</ezf:skip>
<%-- After Convert to JSP, this area suppose to be deleted [TO] --%>
								<!-- include S21BusinessProcessTAB --> 
								<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
							</div>

							<div class="pTab_BODY">
								<table border="0" cellpadding="1" cellspacing="1">
									<tr>
										<td>
											<table border="0" cellspacing="0" cellpadding="0" style="width:98%; text-align:left;">
												<col width="150">
												<col width="400">
												<col width="300">
												<tr>
													<td class="stab">
													<fmt:message key="i18n.NYEL8870Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Process Name</fmt:message>
													</td>
													<td>
														<ezf:select name="wfProcNm_V" ezfName="wfProcNm_V" ezfBlank="1" ezfCodeName="wfProcNm_L" ezfDispName="wfDescTxt_LD" otherAttr=" style=\"width: 300px\""/>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
													<td class="stab">
													<fmt:message key="i18n.NYEL8870Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Notification Type</fmt:message>
													</td>
													<td>
														<ezf:select name="wfNtfyTpCd_V" ezfName="wfNtfyTpCd_V" ezfBlank="1" ezfCodeName="wfNtfyTpCd_L" ezfDispName="wfNtfyTpDescTxt_LD" otherAttr=" style=\"width: 300px\""/>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
												<tr>
													<td class="stab">
													<fmt:message key="i18n.NYEL8870Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Email Notification</fmt:message>
													</td>
													<td>
														<ezf:select name="wfNtfyEmlTpCd_V" ezfName="wfNtfyEmlTpCd_V" ezfBlank="1" ezfCodeName="wfNtfyEmlTpCd_L" ezfDispName="wfNtfyEmlTpDescTxt_LD" otherAttr=" style=\"width: 300px\""/>
													</td>
													<td align="right">
														<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Add\""/>
													</td>
												</tr>
												<tr><td>&nbsp;</td></tr>
											</table>
										</td>
									</tr>
								</table>
								<hr>
								<table>
									<tr>
										<td>
											<table border="1" cellspacing="0" cellpadding="1" style="text-align:center;">
												<col width="300">    <!-- Process Name -->
												<col width="300">    <!-- Notification Type -->
												<col width="300">    <!-- Email Notification -->
												<tr>
													<td class="pClothBs"><fmt:message key="i18n.NYEL8870Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Process Name</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NYEL8870Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Notification Type</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NYEL8870Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Email Notification</fmt:message></td>
												</tr>
											</table>
											<div  id="rightTbl" style="width:930px; height:430px; display:block; overflow-y:scroll; margin:0px; padding:0px;">
												<table id="A" border="1" cellspacing="0" cellpadding="1">
													<col width="300" align="left">   <!-- Process Name -->
													<col width="300" align="left">   <!-- Notification Type -->
													<col width="300" align="left">   <!-- Email Notification -->
													<tbody>
														<ezf:row ezfHyo="A">
															<tr height="28px" id="id_row_left{EZF_ROW_NUMBER}">
																<td>
																	<ezf:label name="wfDescTxt_A" ezfName="wfDescTxt_A" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="wfNtfyTpDescTxt_A" ezfName="wfNtfyTpDescTxt_A" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:select name="wfNtfyEmlTpCd_A" ezfName="wfNtfyEmlTpCd_A" value="HTML mail" ezfHyo="A" ezfArrayNo="0" ezfCodeName="wfNtfyEmlTpCd_L" ezfDispName="wfNtfyEmlTpDescTxt_LD" otherAttr=" style=\"width: 300px\""/>
																</td>
															</tr>
														</ezf:row>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</div>
							<!-- pTab_BODY -->
						</td>
					</tr>
				</table>
			</center>




<%-- **** Task specific area ends here **** --%>
