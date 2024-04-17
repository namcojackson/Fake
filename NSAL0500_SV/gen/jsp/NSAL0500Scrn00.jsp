<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220823132643 --%>
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
<fmt:setBundle basename="I18N_NSAL0500Scrn00" var="I18N_SCREEN_ID" scope="request" />
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

<%@ page import="business.servlet.NSAL0500.NSAL0500BMsg" %>
<% NSAL0500BMsg bMsg = (NSAL0500BMsg)databean.getEZDBMsg(); %>
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NSAL0500Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0500Scrn00.title" bundle="${I18N_SCREEN_ID}">Sub Contract Maintenance</fmt:message>">

			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" otherAttr=" size=\"0\" id=\"xxRecHistCratTs\""/>
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm\""/>
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs\""/>
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm\""/>
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" otherAttr=" size=\"0\" id=\"xxRecHistTblNm\""/>

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="<fmt:message key="i18n.NSAL0500Scrn00.title" bundle="${I18N_SCREEN_ID}">Sub Contract Maintenance</fmt:message>" class="pTab_ON"><a href="#">Sub Cont Mnt</a></li>
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
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table width="100%" border="0">

						<%-- #################### Sub Contract Details #################### --%>
						<tr>
							<td class="stab">
								<fieldset>
									<legend>
										<fmt:message key="i18n.NSAL0500Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Sub Contract Details</fmt:message>
									</legend>
									<table>
										<col width="55">
										<col width="230">
										<col width="130">
										<col width="175">
										<col width="90">
										<col width="330">
										<col width="80">
										<tr>
											<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></td>
											<td><ezf:inputText name="serNum" ezfName="serNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
											<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Model Name</fmt:message></td>
											<td><ezf:inputText name="mdlNm" ezfName="mdlNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
											<td class="stab"><ezf:anchor name="vndCd_AC" ezfName="vndCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Vendor" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NSAL0500Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Supplier Site</fmt:message></ezf:anchor></td>
											<td>
												<table border="0" cellpadding="0" cellspacing="0" >
													<col width="60">
													<col width="35">
													<col width="120">
													<tr>
														<td><ezf:inputText name="vndCd" ezfName="vndCd" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"7\" maxlength=\"20\" ezftoupper=\"\""/></td>
														<td><ezf:inputButton name="Search_VndNm" value=">>" htmlClass="pBtn0" /></td>
														<td><ezf:inputText name="prntVndNm" ezfName="prntVndNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" size=\"30\" maxlength=\"240\""/></td>
													</tr>
												</table>
											</td>
											<td></td>
										</tr>
										<tr>
											<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Contract#</fmt:message></td>
											<td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
											<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Contract Status</fmt:message></td>
											<td><ezf:inputText name="dsContrCtrlStsNm" ezfName="dsContrCtrlStsNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
											<td class="stab"><ezf:anchor name="techTocCd_AC" ezfName="techTocCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Tech" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NSAL0500Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Technician Code</fmt:message></ezf:anchor></td>
											<td><ezf:inputText name="techTocCd" ezfName="techTocCd" value="WWWWWWWW" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
											<td><ezf:inputButton name="FileAttach" value="Attachment" htmlClass="pBtn6" /></td>
										</tr>
										<tr>
											<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Account</fmt:message></td>
											<td>
												<table border="0" cellpadding="0" cellspacing="0" >
													<col width="60">
													<col width="120">
													<tr>
														<td><ezf:inputText name="dsAcctNum" ezfName="dsAcctNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWW" otherAttr=" size=\"7\" maxlength=\"28\""/></td>
														<td><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" size=\"20\" maxlength=\"60\""/></td>
													</tr>
												</table>
											</td>
											<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Sub Contract Terminated</fmt:message></td>
											<td><ezf:inputCheckBox name="contrTrmnFlg" ezfName="contrTrmnFlg" value="Y" /></td>
											<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Effective Date</fmt:message></td>
											<td>
												<table border="0" cellpadding="0" cellspacing="0" >
													<col width="60">
													<col width="20">
													<col width="10">
													<col width="60">
													<col width="20">
													<tr>
														<td><ezf:inputText name="effFromDt" ezfName="effFromDt" value="12/31/9999" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
														<td><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);"></td>
														<td class="stab">-</td>
														<td><ezf:inputText name="effThruDt" ezfName="effThruDt" value="12/31/9999" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
														<td><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);"></td>
													</tr>
												</table>
											</td>
											<td><ezf:inputButton name="CreateNew" value="Create New" htmlClass="pBtn6" /></td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>

						<%-- #################### Comment #################### --%>
						<tr>
							<td>
								<table border="0">
									<col width="60">
									<col width="500">
									<col width="60">
									<col width="490">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Existing<br>Comment</fmt:message></td>
										<td>
											<%
												StringBuilder sbSvcCmntTxtWk = new StringBuilder();
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_71.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_72.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_73.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_74.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_75.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_76.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_77.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_78.getValue());
												
												String toHtml =  EZDCommonFunc.toHTMLString(sbSvcCmntTxtWk.toString());
												
												StringBuilder sbSvcCmntTxt = new StringBuilder();
												sbSvcCmntTxt.append("<textarea name=\"svcCmntTxt\" class=\"pPro\" readOnly cols=\"65\" rows=\"4\">");
												sbSvcCmntTxt.append(toHtml);
												sbSvcCmntTxt.append("</textarea>");
											%>
											<%= sbSvcCmntTxt.toString() %>
										</td>
										<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Additional<br>Comment</fmt:message></td>
										<td><ezf:textArea name="svcCmntTxt_AD" ezfName="svcCmntTxt_AD" otherAttr=" cols=\"65\" rows=\"4\""/></td>
									<tr>
								</table>
							</td>
						</tr>

						<%-- #################### Sub Contract Pricing #################### --%>
						<tr>
							<td class="stab">
								<fieldset>
									<legend>
										<fmt:message key="i18n.NSAL0500Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Sub Contract Pricing</fmt:message>
									</legend>
									<table>
										<col width="285">
										<col width="315">
										<col width="400">
										<tr valign="top">
											<td>
												<table border="0" cellpadding="0" cellspacing="0" >
													<col width="120">
													<col width="150">
													<tr>
														<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Base($)</fmt:message></td>
														<td><ezf:inputText name="basePrcDealAmt" ezfName="basePrcDealAmt" value="1234567890.12" otherAttr=" size=\"13\" maxlength=\"22\""/></td>
													</tr>
													<tr>
														<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Admin($)</fmt:message></td>
														<td><ezf:inputText name="adminPrcDealAmt" ezfName="adminPrcDealAmt" value="1234567890.12" otherAttr=" size=\"13\" maxlength=\"22\""/></td>
													</tr>
													<tr>
														<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Pre Paid Maintenance</fmt:message></td>
														<td><ezf:inputCheckBox name="prepdMaintFlg" ezfName="prepdMaintFlg" value="Y" /></td>
													</tr>
												</table>
											</td>
											<td>
												<table border="0" cellpadding="0" cellspacing="0" >
													<tr>
														<td>
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																<col align="center" width=" 150">
																<col align="center" width=" 90">
																<col align="center" width=" 90">
																<tr>
																	<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Billing Counter</fmt:message></td>
																	<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Allowance</fmt:message></td>
																	<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Rate($)</fmt:message></td>
																</tr>
															</table>
															<div style="overflow-x:none; overflow-y:scroll; height:80;" >
																<table id="E" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed">
																	<col align="center" width=" 150">
																	<col align="center" width=" 90">
																	<col align="center" width=" 90">
																	<ezf:row ezfHyo="E">
																	<tr height="23">
																		<td><ezf:inputText name="mtrLbDescTxt_E0" ezfName="mtrLbDescTxt_E0" value="WWWWWWWWW1WWW" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"32\""/></td>
																		<td><ezf:inputText name="mtrAlwncCnt_E0" ezfName="mtrAlwncCnt_E0" value="1234567890" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
																		<td><ezf:inputText name="prcMtrRate_E0" ezfName="prcMtrRate_E0" value="12345.6789" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
																	</tr>
																	</ezf:row>
																	<ezf:skip>
																	<tr height="23">
																		<td><input type="text" size="32" maxlength="32" value="WWWWWWWWW2WWW" name="mtrLbDescTxt_E0" ezfname="mtrLbDescTxt_E0" ezfhyo="E" class="pPro"></td>
																		<td><input type="text" size="10" maxlength="13" value="1234567890" name="mtrAlwncCnt_E0" ezfname="mtrAlwncCnt_E0" ezfhyo="E"></td>
																		<td><input type="text" size="10" maxlength="22" value="12345.6789" name="prcMtrRate_E0" ezfname="prcMtrRate_E0" ezfhyo="E"></td>
																	</tr>
																	<tr height="23">
																		<td><input type="text" size="32" maxlength="32" value="WWWWWWWWW3WWW" name="mtrLbDescTxt_E0" ezfname="mtrLbDescTxt_E0" ezfhyo="E" class="pPro"></td>
																		<td><input type="text" size="10" maxlength="13" value="1234567890" name="mtrAlwncCnt_E0" ezfname="mtrAlwncCnt_E0" ezfhyo="E"></td>
																		<td><input type="text" size="10" maxlength="22" value="12345.6789" name="prcMtrRate_E0" ezfname="prcMtrRate_E0" ezfhyo="E"></td>
																	</tr>
																	<tr height="23">
																		<td><input type="text" size="32" maxlength="32" value="WWWWWWWWW4WWW" name="mtrLbDescTxt_E0" ezfname="mtrLbDescTxt_E0" ezfhyo="E" class="pPro"></td>
																		<td><input type="text" size="10" maxlength="13" value="1234567890" name="mtrAlwncCnt_E0" ezfname="mtrAlwncCnt_E0" ezfhyo="E"></td>
																		<td><input type="text" size="10" maxlength="22" value="12345.6789" name="prcMtrRate_E0" ezfname="prcMtrRate_E0" ezfhyo="E"></td>
																	</tr>
																	<tr height="23">
																		<td><input type="text" size="32" maxlength="32" value="WWWWWWWWW5WWW" name="mtrLbDescTxt_E0" ezfname="mtrLbDescTxt_E0" ezfhyo="E" class="pPro"></td>
																		<td><input type="text" size="10" maxlength="13" value="1234567890" name="mtrAlwncCnt_E0" ezfname="mtrAlwncCnt_E0" ezfhyo="E"></td>
																		<td><input type="text" size="10" maxlength="22" value="12345.6789" name="prcMtrRate_E0" ezfname="prcMtrRate_E0" ezfhyo="E"></td>
																	</tr>
																	<tr height="23">
																		<td><input type="text" size="32" maxlength="32" value="WWWWWWWWW6WWW" name="mtrLbDescTxt_E0" ezfname="mtrLbDescTxt_E0" ezfhyo="E" class="pPro"></td>
																		<td><input type="text" size="10" maxlength="13" value="1234567890" name="mtrAlwncCnt_E0" ezfname="mtrAlwncCnt_E0" ezfhyo="E"></td>
																		<td><input type="text" size="10" maxlength="22" value="12345.6789" name="prcMtrRate_E0" ezfname="prcMtrRate_E0" ezfhyo="E"></td>
																	</tr>
																	</ezf:skip>
																</table>
															</div>
														</td>
													</tr>
												</table>
											</td>
											<td>
												<table border="0" cellpadding="0" cellspacing="0" >
													<col width="95">
													<col width="60">
													<col width="90">
													<col width="150">
													<tr>
														<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Supply Inclusive</fmt:message></td>
														<td><ezf:inputCheckBox name="splyInclFlg" ezfName="splyInclFlg" value="Y" /></td>
														<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Billing Cycle</fmt:message></td>
														<td>
															<ezf:select name="bllgCycleCd" ezfName="bllgCycleCd" ezfCodeName="bllgCycleCd_L0" ezfDispName="bllgCycleNm_L0" otherAttr=" style=\"width:140;\""/>
														</td>
													</tr>
													<tr>
														<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.22" bundle="${I18N_SCREEN_ID}">Fleet Flag</fmt:message></td>
														<td><ezf:inputCheckBox name="dlrFleetFlg" ezfName="dlrFleetFlg" value="Y" onClick="sendServer('Change_FleetFlag')" /></td>
														<td class="stab"><fmt:message key="i18n.NSAL0500Scrn00.label.23" bundle="${I18N_SCREEN_ID}">Fleet#</fmt:message></td>
														<td><ezf:inputText name="dlrFleetNum" ezfName="dlrFleetNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>

						<%-- #################### Billing History #################### --%>
						<tr>
							<td class="stab">
								<fieldset>
									<legend>
										<fmt:message key="i18n.NSAL0500Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Billing History</fmt:message>
									</legend>
									<table>
										<col width="275">
										<col width="305">
										<col width="400">
										<tr valign="top">
											<!-- Customer Billing -->
											<td class="stab">
												<fieldset>
													<legend>
														<fmt:message key="i18n.NSAL0500Scrn00.label.25" bundle="${I18N_SCREEN_ID}">Customer Billing</fmt:message>
													</legend>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td>
																<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																	<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																	<jsp:param name="TableNm"     value="A" />
																	<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A0" />
																	<jsp:param name="ShowingTo"   value="xxPageShowToNum_A0" />
																	<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A0" />
																</jsp:include>
															</td>
														</tr>
													</table>
													<ezf:skip>
													<table>
														<col width="54"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="10">
														<col>
														<col width="1">
														<col>
														<tr>
															<td class="stab">Showing</td>
															<td class="pOut">1</td>
															<td class="stab">to</td>
															<td class="pOut">20</td>
															<td class="stab">of</td>
															<td class="pOut">200</td>
															<td>&nbsp;</td>
															<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
															<td></td>
															<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
														</tr>
													</table>
													</ezf:skip>
													<table border="0" cellpadding="0" cellspacing="0" >
														<tr>
															<td>
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																	<col align="center" width="80">
																	<col align="center" width="25">
																	<col align="center" width="40">
																	<col align="center" width="65">
																	<col align="center" width="65">
																	<col align="center" width="85">
																	<tr>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Inv#</fmt:message></td>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.27" bundle="${I18N_SCREEN_ID}">Flt</fmt:message></td>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Type</fmt:message></td>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.29" bundle="${I18N_SCREEN_ID}">From</fmt:message></td>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.30" bundle="${I18N_SCREEN_ID}">To</fmt:message></td>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.31" bundle="${I18N_SCREEN_ID}">Amount</fmt:message></td>
																	</tr>
																</table>
																<div style="overflow-x:none; overflow-y:scroll; height:170;" >
																	<table id="A" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed">
																		<col align="center" width="80">
																		<col align="center" width="25">
																		<col align="center" width="40">
																		<col align="center" width="65">
																		<col align="center" width="65">
																		<col align="center" width="85">
																		<ezf:row ezfHyo="A">
																		<tr height="23">
																			<td><ezf:inputText name="svcInvNum_A0" ezfName="svcInvNum_A0" value="WWWWWWWWW1WWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
																			<td><ezf:label name="fleetLineFlg_A0" ezfName="fleetLineFlg_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:inputText name="svcInvChrgTpDescTxt_A0" ezfName="svcInvChrgTpDescTxt_A0" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"4\""/></td>
																			<td><ezf:label name="bllgPerFromDt_A0" ezfName="bllgPerFromDt_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="bllgPerThruDt_A0" ezfName="bllgPerThruDt_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:inputText name="invLineDealNetAmt_A0" ezfName="invLineDealNetAmt_A0" value="123,456.78" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
																		</tr>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="23">
																			<td><input type="text" size="10" maxlength="13" value="WWWWWWWWW1WWW" name="invNum_A0" ezfname="invNum_A0" ezfhyo="A" class="pPro"></td>
																			<td><label ezfout name="refNum_A0">Y</label></td>
																			<td><input type="text" size="4" maxlength="4" value="WWWWWWWWW1" name="refNum_A0" class="pPro"></td>
																			<td><label ezfout name="refNum_A0">12/31/99</label></td>
																			<td><label ezfout name="refNum_A0">12/31/99</label></td>
																			<td><input type="text" size="10" maxlength="24" value="123,456.78" name="refNum_A0" class="pPro"></td>
																		</tr>
																		<tr height="23">
																			<td><input type="text" size="10" maxlength="13" value="WWWWWWWWW1WWW" name="invNum_A0" ezfname="invNum_A0" ezfhyo="A" class="pPro"></td>
																			<td><label ezfout name="refNum_A0">Y</label></td>
																			<td><input type="text" size="4" maxlength="4" value="WWWWWWWWW1" name="refNum_A0" class="pPro"></td>
																			<td><label ezfout name="refNum_A0">12/31/99</label></td>
																			<td><label ezfout name="refNum_A0">12/31/99</label></td>
																			<td><input type="text" size="10" maxlength="24" value="123,456.78" name="refNum_A0" class="pPro"></td>
																		</tr>
																		</ezf:skip>
																	</table>
																</div>
															</td>
														</tr>
													</table>
												</fieldset>
											</td>

											<!-- Meter History -->
											<td class="stab">
												<fieldset>
													<legend>
														<fmt:message key="i18n.NSAL0500Scrn00.label.32" bundle="${I18N_SCREEN_ID}">Meter History</fmt:message>
													</legend>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td>
																<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																	<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																	<jsp:param name="TableNm"     value="B" />
																	<jsp:param name="ShowingFrom" value="xxPageShowFromNum_B0" />
																	<jsp:param name="ShowingTo"   value="xxPageShowToNum_B0" />
																	<jsp:param name="ShowingOf"   value="xxPageShowOfNum_B0" />
																</jsp:include>
															</td>
														</tr>
													</table>
													<ezf:skip>
													<table>
														<col width="54"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="10">
														<col>
														<col width="1">
														<col>
														<tr>
															<td class="stab">Showing</td>
															<td class="pOut">1</td>
															<td class="stab">to</td>
															<td class="pOut">20</td>
															<td class="stab">of</td>
															<td class="pOut">200</td>
															<td>&nbsp;</td>
															<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
															<td></td>
															<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
														</tr>
													</table>
													</ezf:skip>
													<table border="0" cellpadding="0" cellspacing="0" >
														<tr>
															<td>
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																	<col align="center" width="65">
																	<col align="center" width="55">
																	<col align="center" width="55">
																	<col align="center" width="50">
																	<col align="center" width="80">
																	<tr>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.33" bundle="${I18N_SCREEN_ID}">Date</fmt:message></td>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.34" bundle="${I18N_SCREEN_ID}">Label</fmt:message></td>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.35" bundle="${I18N_SCREEN_ID}">Count</fmt:message></td>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.36" bundle="${I18N_SCREEN_ID}">Test</fmt:message></td>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.37" bundle="${I18N_SCREEN_ID}">TOTAL</fmt:message></td>
																	</tr>
																</table>
																<div style="overflow-x:none; overflow-y:scroll; height:170;" >
																	<table id="B" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed">
																		<col align="center" width="65">
																		<col align="center" width="55">
																		<col align="center" width="55">
																		<col align="center" width="50">
																		<col align="center" width="80">
																		<ezf:row ezfHyo="B">
																		<tr height="23">
																			<td><ezf:label name="mtrReadDt_B0" ezfName="mtrReadDt_B0" ezfHyo="B" ezfArrayNo="0" /></td>
																			<td><ezf:inputText name="mtrLbDescTxt_B0" ezfName="mtrLbDescTxt_B0" value="12,345,678" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"6\""/></td>
																			<td><ezf:inputText name="bllgCopyQty_B0" ezfName="bllgCopyQty_B0" value="12,345,678" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"6\""/></td>
																			<td><ezf:inputText name="testCopyQty_B0" ezfName="testCopyQty_B0" value="12,345,678" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"5\""/></td>
																			<td><ezf:inputText name="totCopyQty_B0" ezfName="totCopyQty_B0" value="12,345,678" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
																		</tr>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="23">
																			<td><label ezfout name="mtrReadDt_B0" ezfname="mtrReadDt_B0" ezfhyo="B">12/31/99</label></td>
																			<td><input type="text" size="6" maxlength="13" value="12,345,678" name="readMtrCnt_B0" ezfname="readMtrCnt_B0" ezfhyo="B" class="pPro"></td>
																			<td><input type="text" size="6" maxlength="13" value="12,345,678" name="readMtrCnt_B0" ezfname="readMtrCnt_B0" ezfhyo="B" class="pPro"></td>
																			<td><input type="text" size="5" maxlength="13" value="12,345,678" name="readMtrCnt_B0" class="pPro"></td>
																			<td><input type="text" size="10" maxlength="13" value="12,345,678" name="readMtrCnt_B0" class="pPro"></td>
																		</tr>
																		<tr height="23">
																			<td><label ezfout name="mtrReadDt_B0" ezfname="mtrReadDt_B0" ezfhyo="B">12/31/99</label></td>
																			<td><input type="text" size="6" maxlength="13" value="12,345,678" name="readMtrCnt_B0" ezfname="readMtrCnt_B0" ezfhyo="B" class="pPro"></td>
																			<td><input type="text" size="6" maxlength="13" value="12,345,678" name="readMtrCnt_B0" class="pPro"></td>
																			<td><input type="text" size="5" maxlength="13" value="12,345,678" name="readMtrCnt_B0" class="pPro"></td>
																			<td><input type="text" size="10" maxlength="13" value="12,345,678" name="readMtrCnt_B0" class="pPro"></td>
																		</tr>
																		</ezf:skip>
																	</table>
																</div>
															</td>
														</tr>
													</table>
												</fieldset>
											</td>
											
											<!-- Dealer Billing -->
											<td class="stab">
												<fieldset>
													<legend>
														<fmt:message key="i18n.NSAL0500Scrn00.label.38" bundle="${I18N_SCREEN_ID}">Dealer Billing</fmt:message>
													</legend>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td>
																<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																	<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																	<jsp:param name="TableNm"     value="C" />
																	<jsp:param name="ShowingFrom" value="xxPageShowFromNum_C0" />
																	<jsp:param name="ShowingTo"   value="xxPageShowToNum_C0" />
																	<jsp:param name="ShowingOf"   value="xxPageShowOfNum_C0" />
																</jsp:include>
															</td>
														</tr>
													</table>
													<ezf:skip>
													<table>
														<col width="54"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="10">
														<col>
														<col width="1">
														<col>
														<tr>
															<td class="stab">Showing</td>
															<td class="pOut">1</td>
															<td class="stab">to</td>
															<td class="pOut">20</td>
															<td class="stab">of</td>
															<td class="pOut">200</td>
															<td>&nbsp;</td>
															<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
															<td></td>
															<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
														</tr>
													</table>
													</ezf:skip>
													<table border="0" cellpadding="0" cellspacing="0" >
														<tr>
															<td>
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																	<col align="center" width="80">
																	<col align="center" width="40">
																	<col align="center" width="65">
																	<col align="center" width="65">
																	<col align="center" width="85">
																	<tr>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.26" bundle="${I18N_SCREEN_ID}">Inv#</fmt:message></td>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Type</fmt:message></td>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.29" bundle="${I18N_SCREEN_ID}">From</fmt:message></td>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.30" bundle="${I18N_SCREEN_ID}">To</fmt:message></td>
																		<td class="pClothBs"><fmt:message key="i18n.NSAL0500Scrn00.label.31" bundle="${I18N_SCREEN_ID}">Amount</fmt:message></td>
																	</tr>
																</table>
																<div style="overflow-x:none; overflow-y:scroll; height:170;" >
																	<table id="C" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed">
																		<col align="center" width="80">
																		<col align="center" width="40">
																		<col align="center" width="65">
																		<col align="center" width="65">
																		<col align="center" width="85">
																		<ezf:row ezfHyo="C">
																		<tr height="23">
																			<td><ezf:inputText name="apInvNum_C0" ezfName="apInvNum_C0" value="WWWWWWWWW1WWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
																			<td><ezf:inputText name="xxDsplTpTxt_C0" ezfName="xxDsplTpTxt_C0" value="WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"4\""/></td>
																			<td><ezf:label name="startDt_C0" ezfName="startDt_C0" ezfHyo="C" ezfArrayNo="0" /></td>
																			<td><ezf:label name="endDt_C0" ezfName="endDt_C0" ezfHyo="C" ezfArrayNo="0" /></td>
																			<td><ezf:inputText name="baseAmt_C0" ezfName="baseAmt_C0" value="123,456.78" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
																		</tr>
																		</ezf:row>
																		<ezf:skip>
																		<tr height="23">
																			<td><input type="text" size="10" maxlength="13" value="WWWWWWWWW1WWW" name="invNum_C0" ezfname="invNum_C0" ezfhyo="C" class="pPro"></td>
																			<td><input type="text" size="4" maxlength="4" value="WWWWWWWWW1" name="refNum_C0" class="pPro"></td>
																			<td><label ezfout name="refNum_C0" >12/31/99</label></td>
																			<td><label ezfout name="refNum_C0" >12/31/99</label></td>
																			<td><input type="text" size="10" maxlength="24" value="123,456.78" name="refNum_C0" class="pPro"></td>
																		</tr>
																		<tr height="23">
																			<td><input type="text" size="10" maxlength="13" value="WWWWWWWWW1WWW" name="invNum_C0" ezfname="invNum_C0" ezfhyo="C" class="pPro"></td>
																			<td><input type="text" size="4" maxlength="4" value="WWWWWWWWW1" name="refNum_C0" class="pPro"></td>
																			<td><label ezfout name="refNum_C0" >12/31/99</label></td>
																			<td><label ezfout name="refNum_C0" >12/31/99</label></td>
																			<td><input type="text" size="10" maxlength="24" value="123,456.78" name="refNum_C0" class="pPro"></td>
																		</tr>
																		</ezf:skip>
																	</table>
																</div>
															</td>
														</tr>
													</table>
												</fieldset>
											</td>

										</tr>
									</table>
								</fieldset>
							</td>
						</tr>

					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
