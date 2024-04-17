<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161128153209 --%>
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
<fmt:setBundle basename="I18N_NSAL0320Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSAL0320Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0320Scrn00.title" bundle="${I18N_SCREEN_ID}">Change Billing Counters</fmt:message>">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="<fmt:message key="i18n.NSAL0320Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Contract Maintenance</fmt:message>" class="pTab_ON"><a href="#">Bllg Meter</a></li>
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
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table style="table-layout:fixed; margin-left:4;" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="90" align="left">
									<col width="80" align="left">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0320Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Contract#</fmt:message></td>
										<td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="WWWWWWWWW" otherAttr=" size=\"9\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="90" align="left">
									<col width="80" align="left">
									<col width="5">
									<col width="60" align="left">
									<col width="80" align="left">
									<col width="320" align="left">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0320Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></td>
										<td><ezf:inputText name="serNum" ezfName="serNum" value="WWWWWWWWW" otherAttr=" size=\"9\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><fmt:message key="i18n.NSAL0320Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Item</fmt:message></td>
										<td><ezf:inputText name="mdseCd" ezfName="mdseCd" value="WWWWWWWWW1" otherAttr=" size=\"10\""/></td>
										<td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4" otherAttr=" size=\"40\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="90" align="left">
									<col width="360" align="left">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0320Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Model Name</fmt:message></td>
										<td><ezf:inputText name="mdlNm" ezfName="mdlNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"50\""/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<hr>
					<table style="table-layout:fixed; margin-left:4;" border="0" cellpadding="0" cellspacing="0">
						<tr height="25px">
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="90" align="left">
									<col width="245" align="left">
									<col width="100" align="left">
									<col width="190" align="left">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0320Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Service Package</fmt:message></td>
										<td><ezf:select name="prcMtrPkgPk_B" ezfName="prcMtrPkgPk_B" ezfBlank="1" ezfCodeName="prcMtrPkgPk_BC" ezfDispName="prcMtrPkgDescTxt_BN" otherEvent1=" onchange=\"sendServer('OnChange_ServiceProgram', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:235\""/></td>
										<td class="stab"><fmt:message key="i18n.NSAL0320Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Billing Meter Level</fmt:message></td>
										<td><ezf:select name="bllgMtrMapLvlNum_C" ezfName="bllgMtrMapLvlNum_C" ezfBlank="1" ezfCodeName="bllgMtrMapLvlNum_CC" ezfDispName="bllgMtrMapLvlNum_CN" otherEvent1=" onchange=\"sendServer('OnChange_BillingMeterLevel', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:50\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table style="table-layout:fixed; width:100%;" border="0" cellpadding="0" cellspacing="0">
									<col align="center">
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td>
														<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
															<col width="325" align="center">
															<col width="50" align="center">
															<col width="80" align="center">
															<col width="345" align="center">
															<tr>
																<td class="pClothBs"><fmt:message key="i18n.NSAL0320Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Counter Name</fmt:message></td>
																<td class="pClothBs"><fmt:message key="i18n.NSAL0320Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Billable</fmt:message></td>
																<td class="pClothBs"><fmt:message key="i18n.NSAL0320Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Multiplier</fmt:message></td>
																<td class="pClothBs"><fmt:message key="i18n.NSAL0320Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Billing Counter</fmt:message></td>
															</tr>
														</table>
														<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
															<col width="325" align="left">
															<col width="50" align="center">
															<col width="80" align="right">
															<col width="345" align="left">
															<ezf:row ezfHyo="A">
															<tr>
																<td><ezf:inputText name="mtrLbDescTxt_AP" ezfName="mtrLbDescTxt_AP" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"45\""/></td>
																<td><ezf:inputCheckBox name="bllblFlg" ezfName="bllblFlg" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('OnClick_BillableFlag', '{EZF_ROW_NUMBER}')" /></td>
																<td><ezf:inputText name="contrMtrMultRate" ezfName="contrMtrMultRate" value="9999999999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
																<td><ezf:select name="bllgMtrLbCd" ezfName="bllgMtrLbCd" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgMtrLbCd_AB" ezfDispName="mtrLbDescTxt_AB" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServer('OnChange_BillingCounter', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:340\""/></td>
																<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
															</tr>
															</ezf:row>
														</table>
													<td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
