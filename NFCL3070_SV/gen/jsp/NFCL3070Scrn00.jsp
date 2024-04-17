<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180829110731 --%>
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
			<input type="hidden" name="pageID" value="NFCL3070Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Credit and Rebill">

			<!-- include S21BusinessProcessTAB -->
			<!-- MOCK -->
			<ezf:skip>
			<div class="pTab_HEAD">
				<ul>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="96%">
								<div>
									<li title="Credit and Rebill" class="pTab_ON">
										<a href="#">Credit&Rebill</a>
									</li>
								</div>
							</td>
						</tr>
					</table>
				</ul>
			</div>
			</ezf:skip>
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

			<%@ page import="business.servlet.NFCL3070.NFCL3070BMsg" %>
			<% NFCL3070BMsg bizMsg = (NFCL3070BMsg)databean.getEZDBMsg(); %>
			<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>

		<%-- #################### REQUEST TYPE #################### --%>
			<div class="pTab_BODY">
			<center>
			<BR>
				<table>
					<tr>
						<td>
							<table style="table-layout:fixed;" cellspacing="0">
								<col width="210">
								<col width="90">
								<col width="40">
								<col width="110">
								<col width="150">
								<col width="150">
								<col width="170">
								<tr height="28">
									<td>&nbsp</td>
									<td>Request Type</td>
									<td>&nbsp</td>
									<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="1" otherEvent1=" onchange=\"sendServer('OnChange_RequestType')\"" />&nbsp;Both</td>
									<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="2" otherEvent1=" onchange=\"sendServer('OnChange_RequestType')\"" />&nbsp;Credit Only</td>
									<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="3" otherEvent1=" onchange=\"sendServer('OnChange_RequestType')\"" />&nbsp;Rebill Only</td>
									<td>&nbsp</td>
								</tr>
							</table>
						</td>
					</tr>

		<%-- #################### PARAMETERS #################### --%>
					<tr>
						<td>
							<fieldset>
								<legend style="font-size:12px;">Parameters</legend>
									<table style="table-layout:fixed;" cellspacing="0">
										<col width="60">
										<col width="150">
										<col width="225">
										<col width="35">
										<col width="150">
										<col width="220">
										<col width="60">
										<tr height="28">
											<td>&nbsp</td>
											<td>CI Number</td>
											<td><ezf:inputText name="custIncdtId" ezfName="custIncdtId" value="CI00000" otherAttr=" size=\"30\" maxlength=\"10\" ezftoupper=\"\""/></td>
											<td>&nbsp</td>
											<td>Credit Memo #</td>
											<td class="pOut">
											<% if (ZYPCommonFunc.hasValue(bizMsg.invNum_C)) { %>
											<ezf:anchor name="MoveWin_InvoiceCredit" ezfName="MoveWin_InvoiceCredit" ezfEmulateBtn="1" ezfGuard="MoveWin_InvoiceCredit" otherAttr=" id=\"invNum_C\" ezfanchortext=\"\""><ezf:label name="invNum_C" ezfName="invNum_C" /></ezf:anchor>
											<% } else { %>
											<ezf:label name="invNum_C" ezfName="invNum_C" />
											<% } %>
											</td>
											<td>&nbsp</td>
										</tr>
										<tr height="28">
											<td>&nbsp</td>
											<td>Customer Invoice</td>
											<td><ezf:inputText name="origInvNum" ezfName="origInvNum" value="1000108" otherAttr=" size=\"30\" maxlength=\"13\" ezftoupper=\"\""/></td>
											<td>&nbsp</td>
											<td>Rebill Invoice #</td>
											<td class="pOut">
											<% if (ZYPCommonFunc.hasValue(bizMsg.invNum_R)) { %>
											<ezf:anchor name="MoveWin_InvoiceRebill" ezfName="MoveWin_InvoiceRebill" ezfEmulateBtn="1" ezfGuard="MoveWin_InvoiceRebill" otherAttr=" id=\"invNum_R\" ezfanchortext=\"\""><ezf:label name="invNum_R" ezfName="invNum_R" /></ezf:anchor>
											<% } else { %>
											<ezf:label name="invNum_R" ezfName="invNum_R" />
											<% } %>
											</td>
											<td>&nbsp</td>
										</tr>
										<tr height="28">
											<td>&nbsp</td>
											<td><ezf:anchor name="xxLinkProt" ezfName="xxLinkProt" ezfEmulateBtn="1" ezfGuard="OpenWin_Reason" otherAttr=" id=\"xxLinkProt\" ezfanchortext=\"\"">Reason</ezf:anchor></td>
											<td><ezf:inputText name="arCrRebilRsnNm" ezfName="arCrRebilRsnNm" value="002" otherAttr=" size=\"30\" maxlength=\"70\" ezftoupper=\"\""/></td>
											<td>&nbsp</td>
											<td>Request ID</td>
											<td><ezf:inputText name="arCrRebilPk_C" ezfName="arCrRebilPk_C" value="70000003" otherAttr=" size=\"30\" maxlength=\"28\" ezftoupper=\"\""/></td>
											<td>&nbsp</td>
										</tr>
										<tr height="28">
											<td>&nbsp</td>
											<td>Comments</td>
											<td><ezf:inputText name="arCrRebilCmntTxt" ezfName="arCrRebilCmntTxt" value="comment" otherAttr=" size=\"30\" maxlength=\"200\""/></td>
											<td>&nbsp</td>
											<td rowspan="10" colspan="3">
												<fieldset>
													<table style="table-layout:fixed;" cellspacing="0">
														<col width="70">
														<col width="95">
														<col width="250">
														<tr height="28">
															<td colspan="3">
																One or more credits are already applied on the invoice.<BR>
																Do you want to continue?
															</td>
														</tr>
														<tr>
															<td rowspan="2" valign="top">Comments: </td>
															<td colspan="2">
																<ezf:textArea name="arCrRebilAddlCmntTxt" ezfName="arCrRebilAddlCmntTxt" otherAttr=" rows=\"4\" cols=\"50\" maxlength=\"200\""/></td>
															</td>
														</tr>
														<tr height="28">
															<td colspan="3" align="right">
																<ezf:inputButton name="CMN_No" value="No" htmlClass="pBtn11" />
																<ezf:inputButton name="CMN_Yes" value="Yes" htmlClass="pBtn11" />
															</td>
														</tr>
													</table>
												</fieldset>
											</td>
										</tr>
										<tr height="28">
											<td rowspan="9" colspan="4">&nbsp</td>
										</tr>
									</table>
							</fieldset>
						</td>
					</tr>
				</table>
			<BR>

		<%-- #################### CREDIT ONLY #################### --%>
				<table>
					<tr>
						<td>
							<fieldset>
								<legend style="font-size:12px;">Credit Only</legend>
								<table style="table-layout:fixed;" cellspacing="0">
									<col width="60">
									<col width="150">
									<col width="225">
									<col width="35">
									<col width="150">
									<col width="230">
									<col width="60">
									<tr height="28">
										<td>&nbsp</td>
										<td>Line Number</td>
										<td><ezf:inputText name="invLineNum" ezfName="invLineNum" value="00001" otherAttr=" size=\"30\" maxlength=\"5\" ezftoupper=\"\""/></td>
										<td>&nbsp</td>
										<td><ezf:inputCheckBox name="arAutoCashAppFlg" ezfName="arAutoCashAppFlg" value="Y" /> Auto Apply</td>
										<td colspan="2">&nbsp</td>
									</tr>
									<tr height="28">
										<td>&nbsp</td>
										<td>Type of Credit</td>
										<td>
											<ezf:select name="arCrTpCd" ezfName="arCrTpCd" ezfBlank="1" ezfCodeName="arCrTpCd_CD" ezfDispName="arCrTpDescTxt_DI" otherAttr=" style=\"width: 215px\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp</td>
									<%if ("Y".equals(bizMsg.actvFlg.getValue())) {%>
										<td>Ship Line Number</td>
										<td><ezf:inputText name="invBolLineNum" ezfName="invBolLineNum" value="001" otherAttr=" size=\"30\" maxlength=\"3\" ezftoupper=\"\""/></td>
										<td>&nbsp</td>
									<%} else {%>
										<td colspan="3">&nbsp</td>
									<% } %>
									</tr>
									<tr height="28">
										<td>&nbsp</td>
										<td>Percentage of Credit</td>
										<td><ezf:inputText name="arCrPct" ezfName="arCrPct" value="12.12" otherAttr=" size=\"30\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td>&nbsp</td>
										<td colspan="3">&nbsp</td>
									</tr>
									<tr height="28">
										<td>&nbsp</td>
										<td>Credit Amount</td>
										<td><ezf:inputText name="crRebilAmt_CO" ezfName="crRebilAmt_CO" value="1234.12" otherAttr=" size=\"30\" maxlength=\"24\" ezftoupper=\"\""/></td>
										<td>&nbsp</td>
										<td colspan="3">&nbsp</td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
				</table>
				<BR>
				<table>
					<tr>
						<td>
							<table style="table-layout:fixed;" cellspacing="0">
								<tr height="28">
									<td>&nbsp</td>
									<%if ("Y".equals(bizMsg.actvFlg_E.getValue())) {%>
										<td colspan="3">
											<font size="3" color="#ff0000">Tax rate is different from the original tax rate.</font>
										</td>
									<% } %>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</center>
			</div>

<%-- **** Task specific area ends here **** --%>
