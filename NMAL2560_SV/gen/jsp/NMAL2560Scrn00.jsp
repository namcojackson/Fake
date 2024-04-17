<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170530160430 --%>
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
<fmt:setBundle basename="I18N_NMAL2560Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NMAL2560Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL2560Scrn00.title" bundle="${I18N_SCREEN_ID}">Org Hierarchy Structure</fmt:message>">
			
			
			
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
													<li title="<fmt:message key="i18n.NMAL2560Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Org Hierarchy Structure</fmt:message>" class="pTab_ON"><a href="#">Org Stru Mnt</a></li>
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
								<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
									<col width="5">
									<col width="80">
									<col width="260">
									<col width="110">
									<col width="">
									<tr>
										<td>&nbsp;</td>
										<td class="stab"><fmt:message key="i18n.NMAL2560Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Business Area</fmt:message></td>
										<td>
											<ezf:select name="bizAreaOrgCd" ezfName="bizAreaOrgCd" ezfBlank="1" ezfCodeName="bizAreaOrgCd_L" ezfDispName="bizAreaOrgDescTxt_L" otherAttr=" style=\"width:240px\""/>
										</td>
										<td class="stab">
											<fmt:message key="i18n.NMAL2560Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Show Inactive</fmt:message>
											<ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" />
										</td>
										<td><ezf:inputButton name="Search_OrgHierarchyStructure" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
								<br>
								<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
									<tr>
										<td align="right">
											<ezf:inputButton name="AddBusinessArea" value="Add" htmlClass="pBtn6" />
											<ezf:inputButton name="DelBusinessArea" value="Delete" htmlClass="pBtn6" />
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>

<%--------------------------------%>
<%-- Detail						--%>
<%--------------------------------%>

								<table>
									<tr>
										<td align="top" width="1130">
											


											<%-- ### MEISAI - TBL - TOP --%>
											<div id="TBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:1100; height:17; float:left;word-break: break-all;" onscroll="synchroScrollLeft( this.id, new Array( 'TBL' ));" >
												<table border="1" cellpadding="1" cellspacing="0" height="34" style="table-layout: fixed;">
													<col align="center" width="20">
													<col align="center" width="250"><!--Business Area -->
													<col align="center" width="230"><!--Structure Name -->
													<col align="center" width="110"><!--Effective From -->
													<col align="center" width="110"><!--Effective To -->
													<col align="center" width="140"><!--Tier1 -->
													<col align="center" width="140"><!--Tier2 -->
													<col align="center" width="140"><!--Tier3 -->
													<col align="center" width="140"><!--Tier4 -->
													<col align="center" width="140"><!--Tier5 -->
													<col align="center" width="140"><!--Tier6 -->
													<col align="center" width="140"><!--Tier7 -->
													<col align="center" width="140"><!--Tier8 -->
													<col align="center" width="140"><!--Tier9 -->
													<col align="center" width="140"><!--Tier10 -->
													<col align="center" width="150"><!--Created By -->
													<col align="center" width="150"><!--Created On -->
													<col align="center" width="150"><!--Last Updated By -->
													<col align="center" width="150"><!--Last Updated On -->
													
													<tr height="18">
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Business Area</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Structure Name</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Effective From</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Effective To</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Tier1</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Tier2</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Tier3</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Tier4</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Tier5</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Tier6</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Tier7</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Tier8</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Tier9</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Tier10</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Created By</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Created On</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Last Updated By</fmt:message></td>
														<td class="pClothBs"><fmt:message key="i18n.NMAL2560Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Last Updated On</fmt:message></td>
													</tr>
												</table>
											</div>


											<%-- ### MEISAI - TBL - BOTTOM --%>
											<div id="TBL" style="overflow-x:scroll; overflow-y:scroll; height:487; width:1117; float:left;word-break: break-all;" onscroll="synchroScrollLeft( this.id, new Array( 'TBL_Top' ));" >
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed;">
													<col align="center" width="20">
													<col align="center" width="250"><!--Business Area -->
													<col align="center" width="230"><!--Structure Name -->
													<col align="center" width="110"><!--Effective From -->
													<col align="center" width="110"><!--Effective To -->
													<col align="center" width="140"><!--Tier1 -->
													<col align="center" width="140"><!--Tier2 -->
													<col align="center" width="140"><!--Tier3 -->
													<col align="center" width="140"><!--Tier4 -->
													<col align="center" width="140"><!--Tier5 -->
													<col align="center" width="140"><!--Tier6 -->
													<col align="center" width="140"><!--Tier7 -->
													<col align="center" width="140"><!--Tier8 -->
													<col align="center" width="140"><!--Tier9 -->
													<col align="center" width="140"><!--Tier10 -->
													<col align="center" width="150"><!--Created By -->
													<col align="center" width="150"><!--Created On -->
													<col align="center" width="150"><!--Last Updated By -->
													<col align="center" width="150"><!--Last Updated On -->
													
													<ezf:row ezfHyo="A">
														<tr height="28">
															<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
															<td>
																<ezf:select name="bizAreaOrgCd_A" ezfName="bizAreaOrgCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bizAreaOrgCd_L" ezfDispName="bizAreaOrgDescTxt_L" otherAttr=" style=\"width:240px\""/>
															</td>
															<td>
																<ezf:inputText name="orgHrchStruDfnNm_A" ezfName="orgHrchStruDfnNm_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
															</td>
															<td><ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<ezf:select name="struDfnCd_1" ezfName="struDfnCd_1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="struDfnCd_L" ezfDispName="struDfnDescTxt_L" otherAttr=" style=\"width:130px\""/>
															</td>
															<td>
																<ezf:select name="struDfnCd_2" ezfName="struDfnCd_2" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="struDfnCd_L" ezfDispName="struDfnDescTxt_L" otherAttr=" style=\"width:130px\""/>
															</td>
															<td>
																<ezf:select name="struDfnCd_3" ezfName="struDfnCd_3" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="struDfnCd_L" ezfDispName="struDfnDescTxt_L" otherAttr=" style=\"width:130px\""/>
															</td>
															<td>
																<ezf:select name="struDfnCd_4" ezfName="struDfnCd_4" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="struDfnCd_L" ezfDispName="struDfnDescTxt_L" otherAttr=" style=\"width:130px\""/>
															</td>
															<td>
																<ezf:select name="struDfnCd_5" ezfName="struDfnCd_5" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="struDfnCd_L" ezfDispName="struDfnDescTxt_L" otherAttr=" style=\"width:130px\""/>
															</td>
															<td>
																<ezf:select name="struDfnCd_6" ezfName="struDfnCd_6" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="struDfnCd_L" ezfDispName="struDfnDescTxt_L" otherAttr=" style=\"width:130px\""/>
															</td>
															<td>
																<ezf:select name="struDfnCd_7" ezfName="struDfnCd_7" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="struDfnCd_L" ezfDispName="struDfnDescTxt_L" otherAttr=" style=\"width:130px\""/>
															</td>
															<td>
																<ezf:select name="struDfnCd_8" ezfName="struDfnCd_8" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="struDfnCd_L" ezfDispName="struDfnDescTxt_L" otherAttr=" style=\"width:130px\""/>
															</td>
															<td>
																<ezf:select name="struDfnCd_9" ezfName="struDfnCd_9" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="struDfnCd_L" ezfDispName="struDfnDescTxt_L" otherAttr=" style=\"width:130px\""/>
															</td>
															<td>
																<ezf:select name="struDfnCd_10" ezfName="struDfnCd_10" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="struDfnCd_L" ezfDispName="struDfnDescTxt_L" otherAttr=" style=\"width:130px\""/>
															</td>
															<td>
																<ezf:inputText name="xxChkMaxDescTxt_IN" ezfName="xxChkMaxDescTxt_IN" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputText name="xxDtTm_IN" ezfName="xxDtTm_IN" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputText name="xxChkMaxDescTxt_UP" ezfName="xxChkMaxDescTxt_UP" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputText name="xxDtTm_UP" ezfName="xxDtTm_UP" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\" tabindex=\"-1\""/>
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
														<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:240px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345678901234567890</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="123456789012345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effFromDt_A" ezfname="effFromDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td><input type="text" size="10" maxlength="10" value="01/01/2014" name="effThruDt_A" ezfname="effThruDt_A" ezfHyo="A">
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4,{EZF_ROW_NUMBER});" >
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<select style="width:130px" name="dsAcctDlrCd_A" ezfname="dsAcctDlrCd_A" ezflist="dsAcctDlrCd_L,dsAcctDlrDescTxt_L,99, ,blank" ezfHyo="A">
																	<option>123456789012345</option>
																</select>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" size="30" maxlength="30" value="12345678901234567890" name="mktMdlCd_A" ezfname="mktMdlCd_A"  ezfHyo="A" class="pPro" tabindex="-1" readOnly>
															</td>
															<td>
																<input type="text" value="01/01/2014 10:00:01" name="upldUserId_A" ezfName="upldUserId_A" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;" tabindex="-1" readOnly>
															</td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
