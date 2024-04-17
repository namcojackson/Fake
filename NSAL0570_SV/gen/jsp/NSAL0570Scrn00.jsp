<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170801150750 --%>
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
<fmt:setBundle basename="I18N_NSAL0570Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSAL0570Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0570Scrn00.title" bundle="${I18N_SCREEN_ID}">Overage Pricing Effectivity</fmt:message>">
<%@ page import="business.servlet.NSAL0570.NSAL0570BMsg" %>
<%@ page import="business.servlet.NSAL0570.common.NSAL0570CommonLogic" %>
<%@ page import="business.servlet.NSAL0570.constant.NSAL0570Constant" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPConstant" %>
<%@ page import="java.math.BigDecimal" %>
<% NSAL0570BMsg bMsg = (NSAL0570BMsg) databean.getEZDBMsg(); %>
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="<fmt:message key="i18n.NSAL0570Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Overage Pricing Effectivity</fmt:message>" class="pTab_ON"><a href="#">Ovr Prc Efftv</a></li>
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
					<table width="98%" border="0" align="center">
						<col>
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="80">
									<col width="120">
									<col width="120">
									<tr height="21">
										<td class="stab"><fmt:message key="i18n.NSAL0570Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Contract#</fmt:message></td>
										<td colspan="2"><ezf:inputText name="dsContrNum_H1" ezfName="dsContrNum_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"30\" maxlength=\"30\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
									<tr height="21">
										<td class="stab"><fmt:message key="i18n.NSAL0570Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></td>
										<td><ezf:inputText name="serNum_H1" ezfName="serNum_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;\""/></td>
										<td><ezf:inputText name="t_MdlNm_H1" ezfName="t_MdlNm_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="90">
									<col width="80">
									<col width="10">
									<col width="80">
									<col width="70">
									<tr height="21">
										<td class="stab"><fmt:message key="i18n.NSAL0570Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Effective Date</fmt:message></td>
										<td><ezf:inputText name="contrEffFromDt_H1" ezfName="contrEffFromDt_H1" value="01/31/2014" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/></td>
										<td>-</td>
										<td><ezf:inputText name="contrEffThruDt_H1" ezfName="contrEffThruDt_H1" value="12/31/2014" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/></td>
										<td><br/></td>
									</tr>
									<tr height="21">
										<td class="stab"><fmt:message key="i18n.NSAL0570Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Service Program</fmt:message></td>
										<td colspan="4"><ezf:inputText name="mdseDescShortTxt_H1" ezfName="mdseDescShortTxt_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="90">
									<col width="150">
									<col width="90">
									<tr height="21">
										<td class="stab"><fmt:message key="i18n.NSAL0570Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Billed Upto</fmt:message></td>
										<td colspan="2"><ezf:inputText name="baseBllgLastBllgDt_H1" ezfName="baseBllgLastBllgDt_H1" value="12/31/2014" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
									<tr height="21">
										<td class="stab"><fmt:message key="i18n.NSAL0570Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Bill To Customer</fmt:message></td>
										<td colspan="2"><ezf:inputText name="xxFirstScdCtyStAddr_H1" ezfName="xxFirstScdCtyStAddr_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXX10XXXXXXXX11XXXXXXXX12XXXXXXXX13XXXXXXXX14XXXXXXXX15" otherAttr=" size=\"34\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>
					<table width="100%">
						<col valign="top">
						<tr>
							<td>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td class="stab"><ezf:label name="mtrLbNm_H1" ezfName="mtrLbNm_H1" /></td>
									</tr>
									<tr>
										<td>
											<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:none; width:1050; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col align="center" width=" 25">
														<col align="center" width=" 60">	<!-- PE Seq No -->
														<col align="center" width="110">	<!-- Start Date -->
														<col align="center" width="110">	<!-- End Date -->
														<col align="center" width=" 80">	<!-- Frequency -->
														<col align="center" width=" 70">	<!-- Min Vol -->
														<col align="center" width=" 70">	<!-- Min Amt -->
														<col align="center" width=" 60">	<!-- Rollover% -->
														<col align="center" width=" 70">	<!-- Free Copies -->
														<col align="center" width=" 28">
														<col align="center" width="112">	<!-- Allowance -->
														<col align="center" width=" 65">	<!-- Price -->
														<col align="center" width=" 90">	<!-- Status -->
														<col align="center" width="100">	<!-- Creation Date -->
														<tr>
															<td rowspan="2" class="pClothBs"><br /></td>
															<td rowspan="2" class="pClothBs"><fmt:message key="i18n.NSAL0570Scrn00.label.7" bundle="${I18N_SCREEN_ID}">PE Seq No</fmt:message></td>
															<td rowspan="2" class="pClothBs"><fmt:message key="i18n.NSAL0570Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Start Date</fmt:message></td>
															<td rowspan="2" class="pClothBs"><fmt:message key="i18n.NSAL0570Scrn00.label.9" bundle="${I18N_SCREEN_ID}">End Date</fmt:message></td>
															<td rowspan="2" class="pClothBs"><fmt:message key="i18n.NSAL0570Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Frequency</fmt:message></td>
															<td rowspan="2" class="pClothBs"><fmt:message key="i18n.NSAL0570Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Min Vol</fmt:message></td>
															<td rowspan="2" class="pClothBs"><fmt:message key="i18n.NSAL0570Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Min Amt</fmt:message></td>
															<td rowspan="2" class="pClothBs">Rollover<br />%</td>
															<td rowspan="2" class="pClothBs"><fmt:message key="i18n.NSAL0570Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Free Copies</fmt:message></td>
															<td colspan="3" class="pClothBs"><fmt:message key="i18n.NSAL0570Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Price & Allowance</fmt:message></td>
															<td rowspan="2" class="pClothBs"><fmt:message key="i18n.NSAL0570Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Status</fmt:message></td>
															<td rowspan="2" class="pClothBs"><fmt:message key="i18n.NSAL0570Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Creation Date</fmt:message></td>
														</tr>
														<tr height="16">
															<td class="pClothBs">&nbsp;</td>
															<td class="pClothBs"><fmt:message key="i18n.NSAL0570Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Allowance</fmt:message></td>
															<td class="pClothBs"><fmt:message key="i18n.NSAL0570Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Price</fmt:message></td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_A" style="overflow-y:scroll; height:335; overflow-x:hidden; width:1069; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
														<col align="center" width=" 25">
														<col align="center" width=" 60">	<!-- PE Seq No -->
														<col align="center" width="110">	<!-- Start Date -->
														<col align="center" width="110">	<!-- End Date -->
														<col align="center" width=" 80">	<!-- Frequency -->
														<col align="center" width=" 70">	<!-- Min Vol -->
														<col align="center" width=" 70">	<!-- Min Amt -->
														<col align="center" width=" 60">	<!-- Rollover% -->
														<col align="center" width=" 70">	<!-- Free Copies -->
														<col align="center" width="205">	<!-- Price & Allowance -->
														<col align="center" width=" 90">	<!-- Status -->
														<col align="center" width="100">	<!-- Creation Date -->
<% int i = 0; %>
<% BigDecimal preDsContrPrcEffSqNum = NSAL0570Constant.INVLD_DS_CONTR_PRC_EFF_SQ_NUM; %>
<% BigDecimal curDsContrPrcEffSqNum = NSAL0570Constant.INVLD_DS_CONTR_PRC_EFF_SQ_NUM; %>
<% BigDecimal nxtDsContrPrcEffSqNum = NSAL0570Constant.INVLD_DS_CONTR_PRC_EFF_SQ_NUM; %>
													<ezf:row ezfHyo="A">
<% curDsContrPrcEffSqNum = bMsg.A.no(i).dsContrPrcEffSqNum_A1.getValue(); %>
<% if (i + 1 == bMsg.A.getValidCount()) { %>
<%     nxtDsContrPrcEffSqNum = NSAL0570Constant.INVLD_DS_CONTR_PRC_EFF_SQ_NUM; %>
<% } else { %>
<%     nxtDsContrPrcEffSqNum = bMsg.A.no(i+1).dsContrPrcEffSqNum_A1.getValue(); %>
<% } %>
<% if (NSAL0570CommonLogic.isEqualNum(preDsContrPrcEffSqNum, curDsContrPrcEffSqNum)) { %>
<ezf:inputHidden name="dsContrPrcEffSqNum_A1" ezfName="dsContrPrcEffSqNum_A1" ezfHyo="A" ezfArrayNo="${i}" />
<ezf:inputHidden name="contrPrcEffFromDt_A1" ezfName="contrPrcEffFromDt_A1" ezfHyo="A" ezfArrayNo="${i}" />
<ezf:inputHidden name="contrPrcEffThruDt_A1" ezfName="contrPrcEffThruDt_A1" ezfHyo="A" ezfArrayNo="${i}" />
<ezf:inputHidden name="bllgCycleCd_A3" ezfName="bllgCycleCd_A3" ezfHyo="A" ezfArrayNo="${i}" />
<ezf:inputHidden name="bllgMinCnt_A1" ezfName="bllgMinCnt_A1" ezfHyo="A" ezfArrayNo="${i}" />
<ezf:inputHidden name="bllgMinAmtRate_A1" ezfName="bllgMinAmtRate_A1" ezfHyo="A" ezfArrayNo="${i}" />
<ezf:inputHidden name="bllgRollOverRatio_A1" ezfName="bllgRollOverRatio_A1" ezfHyo="A" ezfArrayNo="${i}" />
<ezf:inputHidden name="bllgFreeCopyCnt_A1" ezfName="bllgFreeCopyCnt_A1" ezfHyo="A" ezfArrayNo="${i}" />
<% } else { %>
														<tr ezfhyo="A">
<ezf:inputHidden name="dsContrDtlStsNm_A1" ezfName="dsContrDtlStsNm_A1" ezfHyo="A" ezfArrayNo="${i}" />
<ezf:inputHidden name="cratDt_A1" ezfName="cratDt_A1" ezfHyo="A" ezfArrayNo="${i}" />
															<td><ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" /></td>
															<td><ezf:inputText name="dsContrPrcEffSqNum_A1" ezfName="dsContrPrcEffSqNum_A1" value="1" ezfHyo="A" ezfArrayNo="${i}" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td><ezf:inputText name="contrPrcEffFromDt_A1" ezfName="contrPrcEffFromDt_A1" value="01/01/2014" ezfHyo="A" ezfArrayNo="${i}" otherAttr=" size=\"10\" maxlength=\"10\" id=\"contrPrcEffFromDt_A1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrPrcEffFromDt_A1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><ezf:inputText name="contrPrcEffThruDt_A1" ezfName="contrPrcEffThruDt_A1" value="01/31/2014" ezfHyo="A" ezfArrayNo="${i}" otherAttr=" size=\"10\" maxlength=\"10\" id=\"contrPrcEffThruDt_A1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrPrcEffThruDt_A1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><ezf:select name="bllgCycleCd_A3" ezfName="bllgCycleCd_A3" ezfHyo="A" ezfArrayNo="${i}" ezfBlank="1" ezfCodeName="bllgCycleCd_A1" ezfDispName="bllgCycleNm_A2" otherAttr=" style=\"width:75;\""/></td>
															<td><ezf:inputText name="bllgMinCnt_A1" ezfName="bllgMinCnt_A1" value="200" ezfHyo="A" ezfArrayNo="${i}" otherAttr=" size=\"8\""/></td>
															<td><ezf:inputText name="bllgMinAmtRate_A1" ezfName="bllgMinAmtRate_A1" value="100" ezfHyo="A" ezfArrayNo="${i}" otherAttr=" size=\"8\""/></td>
															<td><ezf:inputText name="bllgRollOverRatio_A1" ezfName="bllgRollOverRatio_A1" value="95" ezfHyo="A" ezfArrayNo="${i}" otherAttr=" size=\"3\""/></td>
															<td><ezf:inputText name="bllgFreeCopyCnt_A1" ezfName="bllgFreeCopyCnt_A1" value="100" ezfHyo="A" ezfArrayNo="${i}" otherAttr=" size=\"8\""/></td>
															<td>
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col width="24">
																	<col width="113">
																	<col width="68">
<% } %>
																	<tr>
																		<td><ezf:inputRadio name="xxRadioBtn_B" ezfName="xxRadioBtn_B" value="{EZF_ROW_NUMBER}" /></td>
																		<td><ezf:inputText name="xsMtrCopyQty_A1" ezfName="xsMtrCopyQty_A1" value="1000" ezfHyo="A" ezfArrayNo="${i}" otherAttr=" size=\"13\""/></td>
																		<td><ezf:inputText name="xsMtrAmtRate_A1" ezfName="xsMtrAmtRate_A1" value="0.01" ezfHyo="A" ezfArrayNo="${i}" otherAttr=" size=\"8\""/></td>
																	</tr>
<% if (!NSAL0570CommonLogic.isEqualNum(curDsContrPrcEffSqNum, nxtDsContrPrcEffSqNum)) { %>
																</table>
																<table style="table-layout:fixed;">
																	<tr>
																		<td><ezf:inputButton name="InsertPrcAllowRow" value="Insert Row" ezfHyo="A" ezfArrayNo="${i}" htmlClass="pBtn8" /></td>
																		<td><ezf:inputButton name="DeletePrcAllowRow" value="Delete Row" ezfHyo="A" ezfArrayNo="${i}" htmlClass="pBtn8" /> </td>
																	</tr>
																</table>
															</td>
															<td><ezf:inputText name="dsContrDtlStsNm_A1" ezfName="dsContrDtlStsNm_A1" value="ACTIVE" ezfHyo="A" ezfArrayNo="${i}" otherAttr=" size=\"10\""/></td>
															<td><ezf:inputText name="cratDt_A1" ezfName="cratDt_A1" value="01/01/2014" ezfHyo="A" ezfArrayNo="${i}" otherAttr=" size=\"10\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
															</td>
														</tr>
<% } %>
<%     preDsContrPrcEffSqNum = curDsContrPrcEffSqNum; %>
<% i = i + 1; %>
													</ezf:row>
												</table>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="70">
												<col align="left" width="70">
												<col>
												<col>
												<col align="right" width="70">
												<col align="right" width="70">
												<tr>
													<td><ezf:inputButton name="InsertRow" value="Insert Row" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="DeleteRow" value="Delete Row" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" /></td>
													<td><br /></td>
													<td><br /></td>
													<td><ezf:inputButton name="TopSchedules" value="Top Scheduling" htmlClass="pBtn8" /></td>
													<td><ezf:inputButton name="MoveWin_SkipMonths" value="Skip Month" htmlClass="pBtn6" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
					<table width="98%" border="0" align="center" style="table-layout:fixed;">
						<col width="80">
						<col width="900">
						<tr>
							<td class="stab"><fmt:message key="i18n.NSAL0570Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Reason Code</fmt:message></td>
							<td><ezf:select name="svcMemoRsnCd_H3" ezfName="svcMemoRsnCd_H3" ezfBlank="1" ezfCodeName="svcMemoRsnCd_A1" ezfDispName="svcMemoRsnNm_A2" /></td>
						</tr>
						<tr>
							<td class="stab"><fmt:message key="i18n.NSAL0570Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Comment</fmt:message></td>
							<td><ezf:textArea name="svcCmntTxt_H1" ezfName="svcCmntTxt_H1" otherAttr=" cols=\"130\" rows=\"5\""/></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
