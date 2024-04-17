<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170310140606 --%>
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
<fmt:setBundle basename="I18N_NSAL0700Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSAL0700Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0700Scrn00.title" bundle="${I18N_SCREEN_ID}">Contract On Billing Hold</fmt:message>">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="<fmt:message key="i18n.NSAL0700Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Contract On Billing Hold</fmt:message>" class="pTab_ON"><a href="#">ContrOnBllgHld</a></li>
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
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
									<col width="80">
									<col width="600">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0700Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Reason Code</fmt:message></td>
										<td><ezf:select name="svcMemoRsnCd_H3" ezfName="svcMemoRsnCd_H3" ezfBlank="1" ezfCodeName="svcMemoRsnCd_H1" ezfDispName="svcMemoRsnNm_H2" /></td>
									</tr>
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0700Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Notes</fmt:message></td>
										<td><ezf:textArea name="svcCmntTxt_H1" ezfName="svcCmntTxt_H1" otherAttr=" cols=\"80\" rows=\"4\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td>
								<hr/>
							</td>
						</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>
					<table border="0" width="100%">
						<tr>
							<td>
								<table  border="0" cellpadding="0" cellspacing="0" width="1090">
									<col width="" align="right" valign="top">
									<tr>
										<td>
											<ezf:skip>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="54"  align="center">
													<col width="32"  align="right">
													<col width="16"  align="center">
													<col width="32"  align="right">
													<col width="16"  align="center">
													<col width="32"  align="right">
													<col width="10">
													<col>
													<col width="1">
													<col>
													<tr>
														<td class="stab">Showing</td>
														<td class="pOut">0</td>
														<td class="stab">to</td>
														<td class="pOut">0</td>
														<td class="stab">of</td>
														<td class="pOut">0</td>
														<td>&nbsp;</td>
														<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
													</tr>
												</table>
											</ezf:skip>
											<table width="500">
												<tr align="right">
													<td>
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
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width=" 95" align="center"><!-- Contract# -->
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width=" 90" align="center"><!-- Serial# -->
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width=" 120" align="center"><!-- Base/Overage -->
									<col width=" 70" align="center"><!-- Status -->
									<col width=" 80" align="center"><!-- Start Date -->
									<col width=" 80" align="center"><!-- End Date -->
									<col width=" 80" align="center"><!-- Base Amt -->
									<col width=" 80" align="center"><!-- UOM -->
									<col width=" 80" align="center"><!-- Next Sche -->
									<col width="260" align="center"><!-- Return Message -->
									<tr height=" 35">
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" onClick="sendServer('SelectAllContract')" /></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0700Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Contract#</fmt:message></td>
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" onClick="sendServer('SelectAllSerial')" /></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0700Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></td>
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_H3" ezfName="xxChkBox_H3" value="Y" onClick="sendServer('SelectAllBaseOverage')" /></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0700Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Base/Overage</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0700Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Status</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0700Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Start Date</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0700Scrn00.label.8" bundle="${I18N_SCREEN_ID}">End Date</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0700Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Base Amt</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0700Scrn00.label.10" bundle="${I18N_SCREEN_ID}">UOM</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0700Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Next Sche</fmt:message></td>
										<td class="pClothBs"><fmt:message key="i18n.NSAL0700Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Return Message</fmt:message></td>
									</tr>
								</table>
								<div style="width:1057; height:385; display:block; overflow-x:none; overflow-y:scroll;">
									<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width=" 24" align="center"><!-- CheckBox -->
										<col width=" 95" align="center"><!-- Contract# -->
										<col width=" 24" align="center"><!-- CheckBox -->
										<col width=" 90" align="center"><!-- Serial# -->
										<col width=" 24" align="center"><!-- CheckBox -->
										<col width=" 120" align="center"><!-- Base/Overage -->
										<col width=" 70" align="center"><!-- Status -->
										<col width=" 80" align="center"><!-- Start Date -->
										<col width=" 80" align="center"><!-- End Date -->
										<col width=" 80" align="center"><!-- Base Amt -->
										<col width=" 80" align="center"><!-- UOM -->
										<col width=" 80" align="center"><!-- Next Sche -->
										<col width="260" align="center"><!-- Return Message -->
										<ezf:row ezfHyo="A">
											<tr height="25">
												<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="xxScrItem34Txt_A1" ezfName="xxScrItem34Txt_A1" value="NFLT-5874312" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" id=\"xxScrItem34Txt_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A2#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="QHS05087" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" id=\"serNum_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputCheckBox name="xxChkBox_A3" ezfName="xxChkBox_A3" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A3#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="mtrLbDescTxt_A1" ezfName="mtrLbDescTxt_A1" value="BW Billing Counter" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" id=\"mtrLbDescTxt_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="dsContrCtrlStsNm_A1" ezfName="dsContrCtrlStsNm_A1" value="ACTIVE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" id=\"dsContrCtrlStsNm_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="contrVrsnEffFromDt_A1" ezfName="contrVrsnEffFromDt_A1" value="02/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"contrVrsnEffFromDt_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="contrVrsnEffThruDt_A1" ezfName="contrVrsnEffThruDt_A1" value="02/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"contrVrsnEffThruDt_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="basePrcDealAmt_A1" ezfName="basePrcDealAmt_A1" value="100.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"basePrcDealAmt_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="bllgCycleUomNm_A1" ezfName="bllgCycleUomNm_A1" value="MTH" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"bllgCycleUomNm_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="nextBllgDt_A1" ezfName="nextBllgDt_A1" value="02/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" id=\"nextBllgDt_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="xxGenlFldAreaTxt_A1" ezfName="xxGenlFldAreaTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"36\" id=\"xxGenlFldAreaTxt_A1#{EZF_ROW_NUMBER}\""/></td>
                                                <td class="pAuditInfo">
                                                    <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
                                                </td>
											</tr>
										</ezf:row>
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
<ezf:skip>
<script type="text/javascript" src="js/html_preview.js" charset="UTF-8"></script>
</ezf:skip>

<%-- **** Task specific area ends here **** --%>
