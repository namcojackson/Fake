<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160927130517 --%>
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
<fmt:setBundle basename="I18N_NSAL0430Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NSAL0430Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL0430Scrn00.title" bundle="${I18N_SCREEN_ID}">Meter Estimation Popup</fmt:message>">
<%@ page import="business.servlet.NSAL0430.NSAL0430BMsg" %>
<%@ page import="business.servlet.NSAL0430.common.NSAL0430CommonLogic" %>
<%@ page import="business.servlet.NSAL0430.constant.NSAL0430Constant" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%@ page import="java.math.BigDecimal" %>
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<div class="pTab_BODY">
					<table style="table-layout:fixed; margin-left:4;" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><%-- Header --%>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="90" align="left">
									<col width="80" align="left">
									<col width="5">
									<col width="60" align="left">
									<col width="80" align="left">
									<col width="320" align="left">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0430Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Serial#</fmt:message></td>
										<td><ezf:inputText name="serNum" ezfName="serNum" value="WWWWWWWWW" otherAttr=" size=\"9\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><fmt:message key="i18n.NSAL0430Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Item</fmt:message></td>
										<td><ezf:inputText name="mdseCd" ezfName="mdseCd" value="WWWWWWWWW1" otherAttr=" size=\"10\""/></td>
										<td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4" otherAttr=" size=\"40\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><%-- Header --%>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="90" align="left">
									<col width="80" align="left">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0430Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Model Name</fmt:message></td>
										<td><ezf:inputText name="mdlNm" ezfName="mdlNm" value="WWWWWWWWW" otherAttr=" size=\"9\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><%-- Counters --%>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign="top"><%-- Estimation --%>
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
												<col width="150" align="center">
												<col width="100" align="center">
												<col width="100" align="center">
												<col width="100" align="center">
												<tr height="32">
													<td class="pClothBs"><fmt:message key="i18n.NSAL0430Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Counter Name</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NSAL0430Scrn00.label.5" bundle="${I18N_SCREEN_ID}">History (ADCV)</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NSAL0430Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Selected (ADCV)</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NSAL0430Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Estimated Read</fmt:message></td>
												</tr>
											</table>
											<div style="width:450; height:94; overflow-y:scroll;">
												<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
													<col width="150" align="left">
													<col width="100" align="right">
													<col width="100" align="right">
													<col width="100" align="right">
													<ezf:row ezfHyo="A">
													<tr height="23">
														<td><ezf:inputText name="mtrLbDescTxt_A" ezfName="mtrLbDescTxt_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"\""/></td>
														<td><ezf:label name="xxCopyUnitDaysQty_AH" ezfName="xxCopyUnitDaysQty_AH" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="xxCopyUnitDaysQty_AE" ezfName="xxCopyUnitDaysQty_AE" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="readMtrCnt_AE" ezfName="readMtrCnt_AE" ezfHyo="A" ezfArrayNo="0" /></td>
													</tr>
													</ezf:row>
													<ezf:skip>
														<tr>
															<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
															<td><label>9,999,999,999</label></td>
															<td><label>9,999,999,999</label></td>
															<td><label>9,999,999,999</label></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
															<td><label>9,999,999,999</label></td>
															<td><label>9,999,999,999</label></td>
															<td><label>9,999,999,999</label></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
															<td><label>9,999,999,999</label></td>
															<td><label>9,999,999,999</label></td>
															<td><label>9,999,999,999</label></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
															<td><label>9,999,999,999</label></td>
															<td><label>9,999,999,999</label></td>
															<td><label>9,999,999,999</label></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
										<td valign="bottom"><%-- Populate --%>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<col width="100" align="center">
												<tr>
													<td><ezf:inputButton name="Populate" value="Populate" htmlClass="pBtn6" /></td>
												</tr>
											</table>
										</td>
										<td>&nbsp;</td>
										<td valign="top"><%-- Legend --%>
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
												<col width="150" align="center">
												<tr height="32">
													<td class="pClothBs"><fmt:message key="i18n.NSAL0430Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Legend for Reading Type</fmt:message></td>
												</tr>
											</table>
											<div style="width:150; height:94; overflow-y:scroll;">
												<table id="B" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
													<col width="150" align="center">
													<ezf:row ezfHyo="B">
													<tr>
														<td><ezf:inputText name="xxGenlFldAreaTxt_B" ezfName="xxGenlFldAreaTxt_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"\""/></td>
													</tr>
													</ezf:row>
													<ezf:skip>
														<tr>
															<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
														</tr>
														<tr>
															<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><%-- Estimation Date --%>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="90" align="left">
									<col width="80" align="left">
									<col width="20" align="left">
									<col width="600" align="left">
									<tr>
										<td class="stab"><fmt:message key="i18n.NSAL0430Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Estimation Date</fmt:message></td>
										<td class="pOut"><ezf:label name="mtrEstProcDt" ezfName="mtrEstProcDt" /></td>
										<td>&nbsp;</td>
										<td class="stab"><fmt:message key="i18n.NSAL0430Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Please select 2 reads (The last billed read is auto selected by default)</fmt:message></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><%-- Meter Read --%>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="100">
									<col align="left" valign="top">
									<col align="left" valign="top">
									<tr>
										<td>&nbsp;</td>
										<td><%-- Left --%>
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
												<col width="40" align="center">
												<col width="80" align="center">
												<col width="100" align="center">
												<col width="150" align="center">
												<tr height="32">
													<td class="pClothBs"><fmt:message key="i18n.NSAL0430Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Select</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NSAL0430Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Date</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NSAL0430Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Reading Type</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NSAL0430Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Meter Type</fmt:message></td>
												</tr>
											</table>
											<div id="Left" style="width:372; overflow-x:hidden; height:278; overflow-y:hidden;" onScroll="synchroScrollTop(this.id, new Array('Right')" >
												<table id="D_Left" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
													<col width="40" align="center">
													<col width="80" align="center">
													<col width="100" align="center">
													<col width="150" align="center">
													<% NSAL0430BMsg bMsg = (NSAL0430BMsg) databean.getEZDBMsg(); %>
													<% int i = 0; %>
													<% BigDecimal preSvcPhysMtrReadGrpSq = NSAL0430Constant.INVLD_SVC_PHYS_MTR_READ_GRP_SQ; %>
													<% BigDecimal curSvcPhysMtrReadGrpSq = NSAL0430Constant.INVLD_SVC_PHYS_MTR_READ_GRP_SQ; %>
													<ezf:row ezfHyo="D">
													<% curSvcPhysMtrReadGrpSq = bMsg.D.no(i).svcPhysMtrReadGrpSq_D.getValue(); %>
													<% if (NSAL0430CommonLogic.isEqualNum(preSvcPhysMtrReadGrpSq, curSvcPhysMtrReadGrpSq)) { %>
													<ezf:inputHidden name="xxChkBox_D" ezfName="xxChkBox_D" ezfHyo="D" ezfArrayNo="${i}" />
													<% } else { %>
													<% preSvcPhysMtrReadGrpSq = curSvcPhysMtrReadGrpSq; %>
													<tbody id="dl#<%= curSvcPhysMtrReadGrpSq %>">
													<tr height="23">
														<td rowspan="2"><ezf:inputCheckBox name="xxChkBox_D" ezfName="xxChkBox_D" value="Y" ezfHyo="D" ezfArrayNo="${i}" onClick="sendServer('OnChange_MeterReadGroup', '{EZF_ROW_NUMBER}')" /></td>
														<td rowspan="2"><ezf:label name="mtrReadDt_D" ezfName="mtrReadDt_D" ezfHyo="D" ezfArrayNo="${i}" /></td>
														<td><ezf:label name="dsMtrReadTpGrpCd_D" ezfName="dsMtrReadTpGrpCd_D" ezfHyo="D" ezfArrayNo="${i}" /></td>
														<td><ezf:inputText name="dsMtrReadTpDescTxt_D" ezfName="dsMtrReadTpDescTxt_D" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="D" ezfArrayNo="${i}" otherAttr=" size=\"\""/></td>
													</tr>
													<tr height="23">
														<td><fmt:message key="i18n.NSAL0430Scrn00.label.15" bundle="${I18N_SCREEN_ID}">ADCV</fmt:message></td>
														<td>&nbsp;</td>
													</tr>
													</tbody>
													<% } %>
													<% i = i + 1; %>
													</ezf:row>
													<ezf:skip>
													<tr height="23">
														<td rowspan="2"><input type="checkbox" value="Y"></td>
														<td rowspan="2"><label>01/01/2015</label></td>
														<td><label>WWWWWWWWW1</label></td>
														<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2"></td>
													</tr>
													<tr height="23">
														<td><label><fmt:message key="i18n.NSAL0430Scrn00.label.15" bundle="${I18N_SCREEN_ID}">ADCV</fmt:message></label></td>
														<td>&nbsp;</td>
													</tr>
													<tr height="23">
														<td rowspan="2"><input type="checkbox" value="Y"></td>
														<td rowspan="2"><label>01/01/2015</label></td>
														<td><label>WWWWWWWWW1</label></td>
														<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2"></td>
													</tr>
													<tr height="23">
														<td><label><fmt:message key="i18n.NSAL0430Scrn00.label.15" bundle="${I18N_SCREEN_ID}">ADCV</fmt:message></label></td>
														<td>&nbsp;</td>
													</tr>
													<tr height="23">
														<td rowspan="2"><input type="checkbox" value="Y"></td>
														<td rowspan="2"><label>01/01/2015</label></td>
														<td><label>WWWWWWWWW1</label></td>
														<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2"></td>
													</tr>
													<tr height="23">
														<td><label><fmt:message key="i18n.NSAL0430Scrn00.label.15" bundle="${I18N_SCREEN_ID}">ADCV</fmt:message></label></td>
														<td>&nbsp;</td>
													</tr>
													<tr height="23">
														<td rowspan="2"><input type="checkbox" value="Y"></td>
														<td rowspan="2"><label>01/01/2015</label></td>
														<td><label>WWWWWWWWW1</label></td>
														<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2"></td>
													</tr>
													<tr height="23">
														<td><label><fmt:message key="i18n.NSAL0430Scrn00.label.15" bundle="${I18N_SCREEN_ID}">ADCV</fmt:message></label></td>
														<td>&nbsp;</td>
													</tr>
													<tr height="23">
														<td rowspan="2"><input type="checkbox" value="Y"></td>
														<td rowspan="2"><label>01/01/2015</label></td>
														<td><label>WWWWWWWWW1</label></td>
														<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2"></td>
													</tr>
													<tr height="23">
														<td><label><fmt:message key="i18n.NSAL0430Scrn00.label.15" bundle="${I18N_SCREEN_ID}">ADCV</fmt:message></label></td>
														<td>&nbsp;</td>
													</tr>
													<tr height="23">
														<td rowspan="2"><input type="checkbox" value="Y"></td>
														<td rowspan="2"><label>01/01/2015</label></td>
														<td><label>WWWWWWWWW1</label></td>
														<td><input type="text" class="pPro" size="" value="WWWWWWWWW1WWWWWWWWW2"></td>
													</tr>
													<tr height="23">
														<td><label><fmt:message key="i18n.NSAL0430Scrn00.label.15" bundle="${I18N_SCREEN_ID}">ADCV</fmt:message></label></td>
														<td>&nbsp;</td>
													</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
										<td><%-- Right --%>
											<div id="RightTop" style="width:202; overflow-x:hidden;" onScroll="synchroScrollLeft(this.id, new Array('Right'));">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
													<ezf:row ezfHyo="C">
													<col width="100" align="center">
													</ezf:row>
													<ezf:skip>
													<col width="100" align="center">
													<col width="100" align="center">
													<col width="100" align="center">
													</ezf:skip>
													<tr height="32">
														<ezf:row ezfHyo="C">
														<td class="pClothBs" style="word-break:break-all;"><ezf:label name="mtrLbDescTxt_C" ezfName="mtrLbDescTxt_C" ezfHyo="C" ezfArrayNo="0" /></td>
														</ezf:row>
														<ezf:skip>
														<td class="pClothBs">BW (Hard Read)</td>
														<td class="pClothBs">CLR Small (Hard Read)</td>
														<td class="pClothBs">CLR Large (Hard Read)</td>
														</ezf:skip>
													</tr>
												</table>
											</div>
											<div id="Right" style="width:219; overflow-x:scroll; height:295; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array('RightTop') ); synchroScrollTop(this.id, new Array('Left') );">
												<table id="D_Right" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
													<ezf:row ezfHyo="C">
													<col width="100" align="right">
													</ezf:row>
													<ezf:skip>
													<col width="100" align="right">
													<col width="100" align="right">
													<col width="100" align="right">
													</ezf:skip>
													<% i = 0; %>
													<% preSvcPhysMtrReadGrpSq = NSAL0430Constant.INVLD_SVC_PHYS_MTR_READ_GRP_SQ; %>
													<% curSvcPhysMtrReadGrpSq = NSAL0430Constant.INVLD_SVC_PHYS_MTR_READ_GRP_SQ; %>
													<% BigDecimal nxtSvcPhysMtrReadGrpSq = NSAL0430Constant.INVLD_SVC_PHYS_MTR_READ_GRP_SQ; // for screen design support tool %>
													<ezf:row ezfHyo="D">
													<% curSvcPhysMtrReadGrpSq = bMsg.D.no(i).svcPhysMtrReadGrpSq_D.getValue(); %>
													<% if (i + 1 == bMsg.D.getValidCount()) { %>
													<%     nxtSvcPhysMtrReadGrpSq = NSAL0430Constant.INVLD_SVC_PHYS_MTR_READ_GRP_SQ; %>
													<% } else { %>
													<%     nxtSvcPhysMtrReadGrpSq = bMsg.D.no(i+1).svcPhysMtrReadGrpSq_D.getValue(); %>
													<% } %>
													<% if (!NSAL0430CommonLogic.isEqualNum(preSvcPhysMtrReadGrpSq, curSvcPhysMtrReadGrpSq)) { %>
													<tbody id="dr#<%= curSvcPhysMtrReadGrpSq %>">
													<tr height="46">
													<% } %>
													<% preSvcPhysMtrReadGrpSq = curSvcPhysMtrReadGrpSq; %>
														<td>
															<table style="table-layout:fixed; margin:0; padding:0" border="0" cellpadding="0" cellspacing="0">
																<col width="98" align="right">
																<tr height="22">
																	<td style="border-bottom:ridge 2; padding:0 2 0 0; margin:0"><ezf:label name="readMtrCnt_D" ezfName="readMtrCnt_D" ezfHyo="D" ezfArrayNo="${i}" /></td>
																</tr>
																<tr height="23">
																	<td style="border:none; padding:0 2 0 0; margin:0"><ezf:label name="xxCopyUnitDaysQty_DH" ezfName="xxCopyUnitDaysQty_DH" ezfHyo="D" ezfArrayNo="${i}" /></td>
																</tr>
															</table>
														</td>
														<ezf:skip>
														<td>
															<table style="table-layout:fixed; margin:0; padding:0" border="0" cellpadding="0" cellspacing="0">
																<col width="98" align="right">
																<tr height="22">
																	<td style="border-bottom:ridge 2; padding:0 2 0 0; margin:0"><label>9,999,999,999</label></td>
																</tr>
																<tr height="23">
																	<td style="border:none; padding:0 2 0 0; margin:0"><label>9,999,999,999</label></td>
																</tr>
															</table>
														</td>
														<td>
															<table style="table-layout:fixed; margin:0; padding:0" border="0" cellpadding="0" cellspacing="0">
																<col width="98" align="right">
																<tr height="22">
																	<td style="border-bottom:ridge 2; padding:0 2 0 0; margin:0"><label>9,999,999,999</label></td>
																</tr>
																<tr height="23">
																	<td style="border:none; padding:0 2 0 0; margin:0"><label>9,999,999,999</label></td>
																</tr>
															</table>
														</td>
														<td>
															<table style="table-layout:fixed; margin:0; padding:0" border="0" cellpadding="0" cellspacing="0">
																<col width="98" align="right">
																<tr height="22">
																	<td style="border-bottom:ridge 2; padding:0 2 0 0; margin:0"><label>9,999,999,999</label></td>
																</tr>
																<tr height="23">
																	<td style="border:none; padding:0 2 0 0; margin:0"><label>9,999,999,999</label></td>
																</tr>
															</table>
														</td>
														</ezf:skip>
													<% if (!NSAL0430CommonLogic.isEqualNum(curSvcPhysMtrReadGrpSq, nxtSvcPhysMtrReadGrpSq)) { %>
													</tr>
													</tbody>
													<% } %>
													<% i = i + 1; %>
													</ezf:row>
													<ezf:skip>
													<tr height="23">
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
													</tr>
													<tr height="23">
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
													</tr>
													<tr height="23">
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
													</tr>
													<tr height="23">
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
													</tr>
													<tr height="23">
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
													</tr>
													<tr height="23">
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
													</tr>
													<tr height="23">
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
													</tr>
													<tr height="23">
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
													</tr>
													<tr height="23">
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
													</tr>
													<tr height="23">
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
													</tr>
													<tr height="23">
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
													</tr>
													<tr height="23">
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
														<td><label>9,999,999,999</label></td>
													</tr>
													</ezf:skip>
												</table>
											</div>
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
