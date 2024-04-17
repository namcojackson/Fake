<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20130816052131 --%>
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
<fmt:setBundle basename="I18N_NMAL6670Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NMAL6670Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL6670Scrn00.title" bundle="${I18N_SCREEN_ID}">Installment Payment Term Maintenance</fmt:message>">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

            
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table height="525">
						<col valign="top">
						<tr>
							<td>

<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NMAL6670.NMAL6670BMsg" %>
<%@ page import="business.servlet.NMAL6670.NMAL6670_ABMsg" %>
<%@ page import="business.servlet.NMAL6670.common.NMAL6670CommonLogic" %>
<%  NMAL6670BMsg bMsg = (NMAL6670BMsg)databean.getEZDBMsg(); %>
								<table width="1120">
									<col valign="top">
									<tr>
										<td height="20"></td>
										<td width="692" align="right">
										<%--
													<table border="0" cellpadding="0" cellspacing="0">
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
															<td class="stab"><fmt:message key="i18n.NMAL6670Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Showing</fmt:message></td>
															<td class="pOut">1</td>
															<td class="stab"><fmt:message key="i18n.NMAL6670Scrn00.label.2" bundle="${I18N_SCREEN_ID}">to</fmt:message></td>
															<td class="pOut">6</td>
															<td class="stab"><fmt:message key="i18n.NMAL6670Scrn00.label.3" bundle="${I18N_SCREEN_ID}">of</fmt:message></td>
															<td class="pOut">6</td>
															<td>&nbsp;</td>
															<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" otherAttr=" id=\"btnPrev\""/></td>
															<td></td>
															<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" otherAttr=" id=\"btnNext\""/></td>
														</tr>
													</table>
										--%>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											</jsp:include>
										</td>
 									</tr>
									<tr>
										<td width="62"></td>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width='100%'>
												<tr>
													<td>&nbsp;</td>
													<td valign="top">
														<div id="topTBL" style="overflow-x:hidden; width:683; overflow-y:none; height:19;">
															<table border="1" cellpadding="1" cellspacing="0" align="right">
																<col align="center" width="30">
																<col align="center" width="100">
																<col align="center" width="100">
																<col align="center" width="100">
																<col align="center" width="100">
																<tr>
																	<td class="pClothBs">&nbsp;</td>
																	<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'istlPmtTermCd_A1' )"><fmt:message key="i18n.NMAL6670Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Code</fmt:message><img id="sortIMG.istlPmtTermCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs"><fmt:message key="i18n.NMAL6670Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Detail Number</fmt:message></td>
																	<td class="pClothBs"><fmt:message key="i18n.NMAL6670Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Amount of Time</fmt:message></td>
																	<td class="pClothBs"><fmt:message key="i18n.NMAL6670Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Pmt Pct</fmt:message></td>
																	
																</tr>
															</table>
														</div>
														<%-- ### TOP TABLE E ### --%>
						
														<%-- ### BOTTOM TABLE S ### --%>
														<div id="bottomTBL" style="overflow-x:none; overflow-y:scroll; width:700; height:450;">
															<table border="1" cellpadding="1" cellspacing="0" id="A" align="right">
																<col align="center" width="30">
																<col align="center" width="100">
																<col align="center" width="100">
																<col align="center" width="100">
																<col align="center" width="100">
																<% int i = 0; %>
																<ezf:row ezfHyo="A">
																<% NMAL6670_ABMsg detailMsg = bMsg.A.no(i++); %>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td>
																		<% boolean disabledxxChkBox = detailMsg.xxChkBox_A1.isInputProtected(); %>
																		<% if(disabledxxChkBox) out.println("<span style='visibility:hidden'>"); %>
																		<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
																		<% if(disabledxxChkBox) out.println("</span>"); %>
																	</td>
																	<td><ezf:inputText name="istlPmtTermCd_A1" ezfName="istlPmtTermCd_A1" value="--3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\" style=\"text-align:left\""/></td>
                                                                                                                                        <td><ezf:inputText name="istlPmtTermDtlNum_A1" ezfName="istlPmtTermDtlNum_A1" value="-2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\" style=\"text-align:left\""/></td>
																	<td><ezf:inputText name="istlPmtTermAot_A1" ezfName="istlPmtTermAot_A1" value="--3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\" style=\"text-align:right\""/></td>
                                                                                                                                        <td><ezf:inputText name="istlPmtTermPct_A1" ezfName="istlPmtTermPct_A1" value="100.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\" style=\"text-align:right\""/></td>
																	</td>
																</tr>
																</ezf:row>
																<ezf:skip>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="--3" name="istlPmtTermCd_A1" ezfname="istlPmtTermCd_A1" class="pHsu" size="3" maxlength="3" ezftoupper style="text-align:left" ezfhyo="A"></td>
                                                                                                                                        <td><input type="text" value="-2" name="istlPmtTermDtlNum_A1" ezfname="istlPmtTermDtlNum_A1" class="pHsu" size="2" maxlength="2" ezftoupper style="text-align:left" ezfhyo="A"></td>
																	<td><input type="text" value="--3" name="istlPmtTermAot_A1" ezfname="istlPmtTermAot_A1" class="pHsu" size="3" maxlength="3" ezftoupper style="text-align:right" ezfhyo="A"></td>
                                                                                                                                        <td><input type="text" value="100.12" name="istlPmtTermPct_A1" ezfname="istlPmtTermPct_A1" class="pHsu" size="6" maxlength="6" ezftoupper style="text-align:right" ezfhyo="A"></td>
																</tr>
																</ezf:skip>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="62"></td>
									</tr>
									<tr>
										<td width="62"></td>
										<td width="692" align="right">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="81">
												<col width="10">
												<col width="81">
												<tr>
													<td align="left"><ezf:inputButton name="Insert_Row" value="Insert Row" htmlClass="pBtn7" /></td>
													<td align="left"><ezf:inputButton name="Delete_Row" value="Delete Row" htmlClass="pBtn7" /></td>
												</tr>
											</table>
										</td>
										<td width="62"></td>
									</tr>
								</table>
							</td>
						</tr>

<%-- ######################################## FOOTER ######################################## --%>

					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<%-- ###SCRIPT --%>
<script type="text/javascript">
	function synchroScroll_fromRightTblAction() {
		var rightTopTBL = this.document.getElementById( 'rightTopTBL' );
		var rightTBL    = this.document.getElementById( 'rightTBL'     );
		var leftTBL     = this.document.getElementById( 'leftTBL' );

		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
		// synchronize scroll - X
		rightTopTBL.scrollLeft = rightTBL.scrollLeft;
	}

	function synchroScroll_fromLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftTBL'  );
		var rightTBL = this.document.getElementById( 'rightTBL' );

		// synchronize scroll - Y
		rightTBL.scrollTop = leftTBL.scrollTop;
	}
</script>
<%-- **** Task specific area ends here **** --%>
