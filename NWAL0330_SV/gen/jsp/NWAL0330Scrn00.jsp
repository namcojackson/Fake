<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20110503102709 --%>
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
<fmt:setBundle basename="I18N_NWAL0330Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NWAL0330Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NWAL0330Scrn00.title" bundle="${I18N_SCREEN_ID}">Credit Profile Inquiry</fmt:message>">
			
<center>
	<table>
		<tr>
			<td>
				
<%-- ######################################## HEADER ######################################## --%>
				<table>
					<col width="100">
					<tr>
						<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Payer</fmt:message></td>
					</tr>
				</table>
				<table>
					<col width="100">
					<col width="5">
					<col width="250">
					<tr>
						<td class="pOut"><ezf:label name="billToCustCd" ezfName="billToCustCd" /></td>
						<td></td>
						<td class="pOut"><ezf:label name="locNm" ezfName="locNm" /></td>
					</tr>
				</table>
				<table>
					<col width="380">
					<col width="10">
					<col width="380">
					
					<tr>
							<td valign="top">
								<fieldset>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="130">
										<col width="176" align="right">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Credit Limit</fmt:message></td>
											<td class="pOut"><ezf:label name="crLimitAmt" ezfName="crLimitAmt" /></td>
										</tr>
									</table>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="130">
										<col width="209">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Credit Risk Class</fmt:message></td>
											<td class="pOut"><ezf:label name="crRiskClsNm" ezfName="crRiskClsNm" /></td>
										</tr>
									</table>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="130">
										<col width="24" align="right">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Release Orders Manually</fmt:message></td>
											<td class="pOut"><ezf:label name="crChkReqTpCd" ezfName="crChkReqTpCd" /></td>
									</table>
								</fieldset>
								<br>
								<fieldset>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="200">
										<col width="88">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Date Credit Limit Changed</fmt:message></td>
											<td class="pOut"><ezf:label name="crLimitChngDt" ezfName="crLimitChngDt" /></td>

										</tr>
									</table>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="200">
										<col width="88">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Date of Last Credit Check</fmt:message></td>
											<td class="pOut"><ezf:label name="lastCrRvwDt" ezfName="lastCrRvwDt" /></td>

										</tr>
									</table>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="200">
										<col width="88">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Date of Next Credit Check</fmt:message></td>
											<td class="pOut"><ezf:label name="nextCrRvwDueDt" ezfName="nextCrRvwDueDt" /></td>
										</tr>
									</table>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="200">
										<col width="16">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Hold due to Credit Check Date</fmt:message></td>
											<td class="pOut"><ezf:label name="crRvwDtChkReqFlg" ezfName="crRvwDtChkReqFlg" /></td>
										</tr>
									</table>
								</fieldset>
							</td>
							<td></td>
							<td valign="top">
								<fieldset>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="174">
										<col width="176" align="right">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Current A/R Balance Due</fmt:message></td>
											<td class="pOut"><ezf:label name="arBalAmt" ezfName="arBalAmt" /></td>
										</tr>
									</table>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="174">
										<col width="176" align="right">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.10" bundle="${I18N_SCREEN_ID}">In Process Amount</fmt:message></td>
											<td class="pOut"><ezf:label name="inProcAmt" ezfName="inProcAmt" /></td>
										</tr>
									</table>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="174">
										<col width="176" align="right">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Remaining Credit Amount</fmt:message></td>
											<td class="pOut"><ezf:label name="crBalAmt" ezfName="crBalAmt" /></td>
										</tr>
									</table>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="174">
										<col width="176" align="right">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.12" bundle="${I18N_SCREEN_ID}">On Credit Hold Amount</fmt:message></td>
											<td class="pOut"><ezf:label name="inProcAmt_CR" ezfName="inProcAmt_CR" /></td>
										</tr>
									</table>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="174">
										<col width="176" align="right">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Approved Credits</fmt:message></td>
											<td class="pOut"><ezf:label name="inProcAmt_AC" ezfName="inProcAmt_AC" /></td>
										</tr>
									</table>
								</fieldset>
								<br>
								<fieldset>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="200">
										<col width="88">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Last Invoice Date</fmt:message></td>
											<td class="pOut"><ezf:label name="lastInvDt" ezfName="lastInvDt" /></td>
										</tr>
									</table>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="200">
										<col width="16">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Hold due to Last Invoice Date</fmt:message></td>
											<td class="pOut"><ezf:label name="invDtChkReqFlg" ezfName="invDtChkReqFlg" /></td>
										</tr>
									</table>
								</fieldset>
							</td>
					</tr>
				</table>
				<table>
					<col width="700">
					
					<tr>
							<td>
								<fieldset>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="200">
										<col width="88">
										<col width="5">
										<col width="200">
										<col width="88">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Next Revision Date</fmt:message></td>
											<td class="pOut"><ezf:label name="nextRevnDt" ezfName="nextRevnDt" /></td>
											<td></td>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.17" bundle="${I18N_SCREEN_ID}">UCCI Revision Date</fmt:message></td>
											<td class="pOut"><ezf:label name="ucc1RevnDt" ezfName="ucc1RevnDt" /></td>
										</tr>
									</table>
									<table border="0" cellpadding="1" cellspacing="5">
										<col width="200">
										<col width="16">
										<tr>
											<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Personal Guarantee</fmt:message></td>
											<td class="pOut"><ezf:label name="psnGtdFlg" ezfName="psnGtdFlg" /></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</table>
								</fieldset>
							</td>
					</tr>
				</table>
				
				
				<table>
					<col width="360">
					<tr>
						<td>
							<fieldset>
								<table border="0" cellpadding="1" cellspacing="5">
									<col width="200">
									<col width="10">
									<col width="150" align="right">
									<tr>
										<td class="stab"><fmt:message key="i18n.NWAL0330Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Past Due</fmt:message></td>
										<td></td>
										<td class="stab" align="center"><fmt:message key="i18n.NWAL0330Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Amount</fmt:message></td>
									</tr>
									<tr>
										<td class="pOut"><fmt:message key="i18n.NWAL0330Scrn00.label.21" bundle="${I18N_SCREEN_ID}">&nbsp;1&nbsp;-&nbsp;&nbsp;15&nbsp;Days</fmt:message></td>
										<td></td>
										<td class="pOut"><ezf:label name="func01AgingAmt_01" ezfName="func01AgingAmt_01" /></td>
									</tr>
									<tr>
										<td class="pOut"><fmt:message key="i18n.NWAL0330Scrn00.label.22" bundle="${I18N_SCREEN_ID}">16&nbsp;-&nbsp;&nbsp;30&nbsp;Days</fmt:message></td>
										<td></td>
										<td class="pOut"><ezf:label name="func01AgingAmt_02" ezfName="func01AgingAmt_02" /></td>
									</tr>
									<tr>
										<td class="pOut"><fmt:message key="i18n.NWAL0330Scrn00.label.23" bundle="${I18N_SCREEN_ID}">31&nbsp;-&nbsp;&nbsp;60&nbsp;Days</fmt:message></td>
										<td></td>
										<td class="pOut"><ezf:label name="func01AgingAmt_03" ezfName="func01AgingAmt_03" /></td>
									</tr>
									<tr>
										<td class="pOut"><fmt:message key="i18n.NWAL0330Scrn00.label.24" bundle="${I18N_SCREEN_ID}">61&nbsp;-&nbsp;&nbsp;90&nbsp;Days</fmt:message></td>
										<td></td>
										<td class="pOut"><ezf:label name="func01AgingAmt_04" ezfName="func01AgingAmt_04" /></td>
									</tr>
									<tr>
										<td class="pOut"><fmt:message key="i18n.NWAL0330Scrn00.label.25" bundle="${I18N_SCREEN_ID}">91&nbsp;-&nbsp;999&nbsp;Days</fmt:message></td>
										<td></td>
										<td class="pOut"><ezf:label name="func01AgingAmt_05" ezfName="func01AgingAmt_05" /></td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
				</table>
<%-- ######################################## DETAIL ######################################## --%>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
