<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100126035412 --%>
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
<fmt:setBundle basename="I18N_NFAL0080Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NFAL0080Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NFAL0080Scrn00.title" bundle="${I18N_SCREEN_ID}">Eligible Coa Segment Pattern Maintenance</fmt:message>">
		            <!-- Upper Tab Start -->
		            <%--
		            <div class="pTab_HEAD">
		                    <ul>
		                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
		                                    <tr>
		                                            <td width="96%">
		                                                    <div>
		                                                            <li title="<fmt:message key="i18n.NFAL0080Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Prst Mnl Ent</fmt:message>" class="pTab_OFF"><a href="#"><fmt:message key="i18n.NFAL0080Scrn00.label.1" bundle="${I18N_SCREEN_ID}">AcctMstr Mnt</fmt:message></a></li>
		                                                            <li title="<fmt:message key="i18n.NFAL0080Scrn00.title.2" bundle="${I18N_SCREEN_ID}">Prst Mnl Cmp</fmt:message>" class="pTab_OFF"><a href="#"><fmt:message key="i18n.NFAL0080Scrn00.label.2" bundle="${I18N_SCREEN_ID}">MthExchR Mnt</fmt:message></a></li>
		                                                            <li title="<fmt:message key="i18n.NFAL0080Scrn00.title.3" bundle="${I18N_SCREEN_ID}">Non Prst Mnt</fmt:message>" class="pTab_OFF"><a href="#"><fmt:message key="i18n.NFAL0080Scrn00.label.3" bundle="${I18N_SCREEN_ID}">DlyExchR Mnt</fmt:message></a></li>
		                                                            <li title="<fmt:message key="i18n.NFAL0080Scrn00.title.4" bundle="${I18N_SCREEN_ID}">StkinErr Mnt</fmt:message>" class="pTab_OFF"><a href="#"><fmt:message key="i18n.NFAL0080Scrn00.label.4" bundle="${I18N_SCREEN_ID}">BankMstr Mnt</fmt:message></a></li>
		                                                            <li title="<fmt:message key="i18n.NFAL0080Scrn00.title.5" bundle="${I18N_SCREEN_ID}">Actl Inv Mnt</fmt:message>" class="pTab_OFF"><a href="#"><fmt:message key="i18n.NFAL0080Scrn00.label.5" bundle="${I18N_SCREEN_ID}">AJEPttrn Mnt</fmt:message></a></li>
		                                                            <li title="<fmt:message key="i18n.NFAL0080Scrn00.label.6" bundle="${I18N_SCREEN_ID}">P Ind Tp Mnt</fmt:message>" class="pTab_ON"><a href="#"><fmt:message key="i18n.NFAL0080Scrn00.label.6" bundle="${I18N_SCREEN_ID}">P Ind Tp Mnt</fmt:message></a></li>
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
                    --%>
			<!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
			<!-- Application Parts Start -->
			<div class="pTab_BODY">
				<table height="94%" width="98%">
					<col valign="top">
						<tr>
							<td>
								<table height="85" width="1082"  border="0">
									<col valign="top">
									<tr>
										<td>
											<table cellpadding="1" cellspacing="0" border="1" width="400" align="center">
												<col width="200">
												<col width="200">
													<tr>
														<td class="pClothBs" align="center"><fmt:message key="i18n.NFAL0080Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Eligible Coa Segment Pattern</fmt:message></td>
														<td class="pClothBs" align="center"><fmt:message key="i18n.NFAL0080Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Coa Segment Lookup Type</fmt:message></td>
													</tr>
											</table>
											
											<table cellpadding="1" cellspacing="0" border="1" width="400" align="center" >
												<col width="200">
												<col width="200">
												<tbody>
													<tr height="24">
														<td class="stab" align="center">
															<ezf:inputText name="eligCoaSegPtrnCd" ezfName="eligCoaSegPtrnCd" value="COA_PROD_CD" otherAttr=" size=\"22\" maxlength=\"20\" ezftoupper=\"\""/>
														</td>
														<td class="stab" align="center">
															<ezf:select name="coaSegLkupTpCd_3" ezfName="coaSegLkupTpCd_3" ezfBlank="1" ezfCodeName="coaSegLkupTpCd_1" ezfDispName="coaSegLkupTpCd_2" otherEvent1=" onchange=\"sendServer('OnChange_COA_SEG_LKUP_TP_CD_TP')\"" otherAttr=" style=\"width:190px;\""/>
														</td>
													</tr>
												</tbody>
											</table>
											<table cellpadding="0" cellspacing="0" border="0" align="center" >
												<col width="1080">
												<tbody>
													<tr height="24">
														<td class="stab" align="right"><ezf:inputButton name="AddRow" value="Add Row" htmlClass="pBtn6" otherAttr=" style=\"width:120px;height:25px\""/></td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</table>
								<hr size="1" noshade>
								<table height="85" border="0" width="100%" cellpadding="0" cellspacing="0">
									<col valign="top">
									
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="1" align="center"  width="96%">
												<tr>
<%-- ########## Table:A --%>
													<td align="left" valign="top" width="1082">
														<table border="1" cellpadding="1" cellspacing="0" align="center">
															<col width="20">
															<col width="200">
															<col width="200">
															<tr>
																<td class="pClothBs" align="center">&nbsp;</td>
																<td class="pClothBs" align="center"><fmt:message key="i18n.NFAL0080Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Eligible Coa Segment Pattern2</fmt:message></td>
																<td class="pClothBs" align="center"><fmt:message key="i18n.NFAL0080Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Coa Segment Lookup Type</fmt:message></td>
															</tr>
														</table>

														<!-- id:leftTBL -->
														<div id="leftTBL" style="overflow-y:scroll; height:392; overflow-x:hidden; width:1100;">
															<table border="1" cellpadding="1" cellspacing="0" id="A" align="center">
																<col width="20">
																<col width="200">
																<col width="200">
																<ezf:row ezfHyo="A">
																	<tr id="id_row_{EZF_ROW_NUMBER}" height="24">
																		<td class="stab" align="center">
																			<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('OnClick_XX_CHK_BOX')" />
																		</td>
																		<td class="stab" align="center">
																			<ezf:inputText name="eligCoaSegPtrnCd_A" ezfName="eligCoaSegPtrnCd_A" value="COA_PROD_CD" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"20\" ezftoupper=\"\""/>
																		</td>
																		<td class="stab" align="center">
																			<ezf:select name="coaSegLkupTpCd_A3" ezfName="coaSegLkupTpCd_A3" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="coaSegLkupTpCd_A1" ezfDispName="coaSegLkupTpCd_A2" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServer('OnChange_COA_SEG_LKUP_TP_CD')\"" otherAttr=" style=\"width:190px;\""/>
																		</td>
																	</tr>
																</ezf:row>
																<ezf:skip>

																</ezf:skip>
															</table>
														</div>
													</td>
												</tr>
											</table>

											<hr size="1" noshade>
											<table cellpadding="0" width="1082" cellspacing="0" border="0">
												<col width="500">
												<col width="480">
												<tbody>
													<tr height="30">
														<td align = "left">
														</td>
														<td align = "right">
															<ezf:inputButton name="DeleteRows" value="Delete Rows" htmlClass="pBtn7" otherAttr=" style=\"width:120px;height:25px\""/>
														</td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
				</table>
			</div>

<%-- **** Task specific area ends here **** --%>
