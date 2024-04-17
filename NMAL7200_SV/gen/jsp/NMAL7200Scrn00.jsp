<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160311115232 --%>
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
<fmt:setBundle basename="I18N_NMAL7200Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NMAL7200Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL7200Scrn00.title" bundle="${I18N_SCREEN_ID}">Price Group Usage</fmt:message>">
			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<%-- ###TAB - HEAD --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="<fmt:message key="i18n.NMAL7200Scrn00.title" bundle="${I18N_SCREEN_ID}">Price Group Usage</fmt:message>" class="pTab_ON"><a href="#">Prc Grp Cond</a></li>
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
							</div>
							</ezf:skip>
							<div class="pTab_BODY_In">
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign="top">
											<table cellpadding="0" border="0">
												<col align="left" width="24">
												<col align="left" width="150">
												<col align="left" width="100">
												<col align="center" width="5">
												<col align="left" width="110">
												<tr>
													<td>&nbsp;</td>
													<td class="stab"><fmt:message key="i18n.NMAL7200Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Pricing Group ID(*)</fmt:message></td>
													<td><ezf:inputText name="xxScrItem29Txt" ezfName="xxScrItem29Txt" otherAttr=" size=\"28\" maxlength=\"28\" ezftoupper=\"\""/></td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td class="stab"><fmt:message key="i18n.NMAL7200Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Pricing Group Name(*)</fmt:message></td>
													<td><ezf:inputText name="prcGrpNm" ezfName="prcGrpNm" otherAttr=" size=\"28\" maxlength=\"50\" ezftoupper=\"\""/></td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td class="stab"><fmt:message key="i18n.NMAL7200Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Pricing Group Description(*)</fmt:message></td>
													<td><ezf:inputText name="prcGrpDescTxt" ezfName="prcGrpDescTxt" otherAttr=" size=\"28\" maxlength=\"50\" ezftoupper=\"\""/></td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="Search_PrcGrpUge" value="Search" htmlClass="pBtn6" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<hr style="height: 0px;" cellpadding="0">
<%--------------------------------%>
<%-- Detail						--%>
<%--------------------------------%>
								<table>
									<tr>
										<td width="20"></td>
										<td align="top" width="987">
											<%-- ### MEISAI - Right TBL - TOP --%>
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:970; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="970" style="table-layout:fixed; ">
													<col align="center" width="40">
													<col align="center" width="130">
													<col align="center" width="540">
													<col align="center" width="180">
													<col align="center" width="80">
													<tr height="28">
														<td class="pClothBs"></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL7200Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Usage Type</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL7200Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Usage Value</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL7200Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Usage ID</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL7200Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Usage Active</fmt:message></td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - RIGHT TBL - BOTTOM --%>
											<div id="RightTBL" style="overflow-y:scroll; height:422; width:987; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="970" id="A1" style="table-layout:fixed; ">
													<col align="center" width="40">
													<col align="center" width="130">
													<col align="center" width="540">
													<col align="center" width="180">
													<col align="center" width="80">
													<ezf:row ezfHyo="A">
														<tr height="28">
															<td>
																<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="1" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" />
															</td>
															<td>
																<Div Align="left">
																	<ezf:label name="xxScrItem10Txt" ezfName="xxScrItem10Txt" ezfHyo="A" ezfArrayNo="0" otherAttr=" align=\"left\""/>
																</Div>
															</td>
															
															<td>
																<Div Align="left">
																	<ezf:label name="prcCatgNm" ezfName="prcCatgNm" ezfHyo="A" ezfArrayNo="0" otherAttr=" align=\"left\""/>
																</Div>
															</td>
															<td>
																<Div Align="left">
																	<ezf:label name="xxScrItem30Txt" ezfName="xxScrItem30Txt" ezfHyo="A" ezfArrayNo="0" otherAttr=" align=\"left\""/>
																</Div>
															</td>
															<td>
																<Div Align="left">
																	<ezf:label name="xxScrItem7Txt" ezfName="xxScrItem7Txt" ezfHyo="A" ezfArrayNo="0" otherAttr=" align=\"left\""/>
																</Div>	
															</td>
														</tr>	
													</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<table width="1000" border="0" cellpadding="0" cellspacing="0">
								    <tr>
								        <td align="right">
								            <ezf:inputButton name="ViewUsageRecord" value="View Usage Record" htmlClass="pBtn6" otherAttr=" style=\"WIDTH: 130px\""/>
								        </td>
								    </tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
