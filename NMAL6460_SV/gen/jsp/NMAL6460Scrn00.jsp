<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160520140610 --%>
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
<fmt:setBundle basename="I18N_NMAL6460Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NMAL6460Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL6460Scrn00.title" bundle="${I18N_SCREEN_ID}">Payment Term Cash Discount</fmt:message>">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

            
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<%--
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="<fmt:message key="i18n.NMAL6460Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Price Category</fmt:message>"			class="pTab_OFF" ><a href="./AMALxxxxScrn00.html"><fmt:message key="i18n.NMAL6460Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Price Category</fmt:message></a></li>
									</div>
								</td>
								<td></td>
							</tr>
						</table>
					</ul>
				</div>
				--%>
				<div class="pTab_BODY">
					<table height="525">
						<col valign="top">
						<tr>
							<td>

<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NMAL6460.NMAL6460BMsg" %>
<%@ page import="business.servlet.NMAL6460.NMAL6460_ABMsg" %>
<%@ page import="business.servlet.NMAL6460.common.NMAL6460CommonLogic" %>
<%  NMAL6460BMsg bMsg = (NMAL6460BMsg)databean.getEZDBMsg(); %>
								<table>
									<col valign="top">
									<tr>
										<td align="right">
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
															<td class="stab"><fmt:message key="i18n.NMAL6460Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Showing</fmt:message></td>
															<td class="pOut">1</td>
															<td class="stab"><fmt:message key="i18n.NMAL6460Scrn00.label.3" bundle="${I18N_SCREEN_ID}">to</fmt:message></td>
															<td class="pOut">6</td>
															<td class="stab"><fmt:message key="i18n.NMAL6460Scrn00.label.4" bundle="${I18N_SCREEN_ID}">of</fmt:message></td>
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
										<td>
											<table border="0" cellpadding="1" cellspacing="0" >
												<tr>
													<td>&nbsp;</td>
													<td valign="top">
														<div id="topTBL" style="overflow-x:hidden; overflow-y:none; height:19;">
															<table border="1" cellpadding="1" cellspacing="0" width="1067">
																<col align="center" width="30">
																<col align="left" width="35">
																<col align="center" width="35">
																<col align="center" width="174">
																<col align="center" width="160">
																<col align="center" width="247">
                                                                <col align="center" width="40">
                                                                <col align="center" width="40">
                                                                <col align="center" width="60">
                                                                <col align="center" width="40">
                                                                <col align="center" width="40">
                                                                <col align="center" width="40">
                                                                <col align="center" width="40">
																
																<tr>
																	<td class="pClothBs">&nbsp;</td>
																	<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pmtTermCashDiscSortNum_A1' )"><fmt:message key="i18n.NMAL6460Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Sort<br>Num</fmt:message><img id="sortIMG.pmtTermCashDiscSortNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pmtTermCashDiscCd_A1' )"><fmt:message key="i18n.NMAL6460Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Code</fmt:message><img id="sortIMG.pmtTermCashDiscCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs"><fmt:message key="i18n.NMAL6460Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Name</fmt:message></td>
																	<td class="pClothBs"><fmt:message key="i18n.NMAL6460Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Description Text</fmt:message></td>
																	<td class="pClothBs"><fmt:message key="i18n.NMAL6460Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Payment Term</fmt:message></td>
                                                                    <td class="pClothBs"><fmt:message key="i18n.NMAL6460Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Install<br>PT<br>Flag</fmt:message></td>
                                                                    <td class="pClothBs"><fmt:message key="i18n.NMAL6460Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Consl<br>Flag</fmt:message></td>
                                                                    <td class="pClothBs"><fmt:message key="i18n.NMAL6460Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Last Day<br>of Month<br>Flag</fmt:message></td>
                                                                    <td class="pClothBs"><fmt:message key="i18n.NMAL6460Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Day of<br>Month</fmt:message></td>
                                                                    <td class="pClothBs"><fmt:message key="i18n.NMAL6460Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Month<br>Ahead</fmt:message></td>
                                                                    <td class="pClothBs"><fmt:message key="i18n.NMAL6460Scrn00.label.15" bundle="${I18N_SCREEN_ID}">PT<br>Cash<br>Flag</fmt:message></td>
                                                                    <td class="pClothBs"><fmt:message key="i18n.NMAL6460Scrn00.label.16" bundle="${I18N_SCREEN_ID}">PT Cr<br>Card<br>Flag</fmt:message></td>
																</tr>
															</table>
														</div>
														<%-- ### TOP TABLE E ### --%>
						
														<%-- ### BOTTOM TABLE S ### --%>
														<div id="bottomTBL" style="overflow-x:none; overflow-y:scroll; height:450;">
															<table border="1" cellpadding="1" cellspacing="0" id="A" width="1067">
																<col align="center" width="30">
																<col align="center" width="35">
																<col align="center" width="35">
																<col align="center" width="174">
																<col align="center" width="160">
																<col align="center" width="247">
                                                                <col align="center" width="40">
                                                                <col align="center" width="40">
                                                                <col align="center" width="60">
                                                                <col align="center" width="40">
                                                                <col align="center" width="40">
                                                                <col align="center" width="40">
                                                                <col align="center" width="40">
																<% int i = 0; %>
																<ezf:row ezfHyo="A">
																<% NMAL6460_ABMsg detailMsg = bMsg.A.no(i++); %>
																<tr height="28" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td>
																		<% boolean disabledxxChkBox = detailMsg.xxChkBox_A1.isInputProtected(); %>
																		<% if(disabledxxChkBox) out.println("<span style='visibility:hidden'>"); %>
																		<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
																		<% if(disabledxxChkBox) out.println("</span>"); %>
																	</td>
																	<td><ezf:inputText name="pmtTermCashDiscSortNum_A1" ezfName="pmtTermCashDiscSortNum_A1" value="XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right\""/></td>
																	<td><ezf:inputText name="pmtTermCashDiscCd_A1" ezfName="pmtTermCashDiscCd_A1" value="-2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\" style=\"text-align:right\""/></td>
																	<td><ezf:inputText name="pmtTermCashDiscNm_A1" ezfName="pmtTermCashDiscNm_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"60\" ezftoupper=\"\""/></td>
																	<td><ezf:inputText name="pmtTermCashDiscDescTxt_A1" ezfName="pmtTermCashDiscDescTxt_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/></td>
																	<td>
																		<ezf:inputButton name="OpenWin_PaymentTerm" value="PT" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PaymentTerm#{EZF_ROW_NUMBER}\""/>
																		<ezf:inputText name="pmtTermCd_A1" ezfName="pmtTermCd_A1" value="--3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\" style=\"text-align:right\""/>
																		<ezf:inputButton name="SearchPaymentTermName" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SearchPaymentTermName#{EZF_ROW_NUMBER}\""/>
																		<ezf:inputText name="pmtTermNm_A1" ezfName="pmtTermNm_A1" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"18\""/></td>
																	</td>
                                                                    <td>
																		<ezf:select name="istlPmtTermFlg_A1" ezfName="istlPmtTermFlg_A1" ezfHyo="A" ezfArrayNo="0" >
																			<ezf:option value="Y" ><fmt:message key="i18n.NMAL6460Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></ezf:option>
																			<ezf:option value="N" ><fmt:message key="i18n.NMAL6460Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></ezf:option>
																		</ezf:select>
																	</td>
                                                                    <td>
																		<ezf:select name="pmtTermConslFlg_A1" ezfName="pmtTermConslFlg_A1" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onchange=\"sendServer('Change_ConslFlg','{EZF_ROW_NUMBER}')\"" >
																			<ezf:option value="Y" ><fmt:message key="i18n.NMAL6460Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></ezf:option>
																			<ezf:option value="N" ><fmt:message key="i18n.NMAL6460Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></ezf:option>
																		</ezf:select>
																	</td>
                                                                    <td>
																		<ezf:select name="pmtTermConslLastDomFlg_A1" ezfName="pmtTermConslLastDomFlg_A1" ezfHyo="A" ezfArrayNo="0" >
																			<ezf:option value="Y" ><fmt:message key="i18n.NMAL6460Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></ezf:option>
																			<ezf:option value="N" ><fmt:message key="i18n.NMAL6460Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></ezf:option>
																		</ezf:select>
																	</td>
                                                                    <td>
																		<ezf:inputText name="pmtTermConslDueDay_A1" ezfName="pmtTermConslDueDay_A1" value="-2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\" style=\"text-align:right\""/>
																	</td>
                                                                    <td>
																		<ezf:inputText name="pmtTermConslAddMthNum_A1" ezfName="pmtTermConslAddMthNum_A1" value="--3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\" style=\"text-align:right\""/>
																	</td>
                                                                    <td>
																		<ezf:select name="pmtCashFlg_A1" ezfName="pmtCashFlg_A1" ezfHyo="A" ezfArrayNo="0" >
																			<ezf:option value="Y" ><fmt:message key="i18n.NMAL6460Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></ezf:option>
																			<ezf:option value="N" ><fmt:message key="i18n.NMAL6460Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></ezf:option>
																		</ezf:select>
																	</td>
                                                                    <td>
																		<ezf:select name="pmtCcFlg_A1" ezfName="pmtCcFlg_A1" ezfHyo="A" ezfArrayNo="0" >
																			<ezf:option value="Y" ><fmt:message key="i18n.NMAL6460Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></ezf:option>
																			<ezf:option value="N" ><fmt:message key="i18n.NMAL6460Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></ezf:option>
																		</ezf:select>
																	</td>
																</tr>
																</ezf:row>
																<ezf:skip>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="pmtTermCashDiscSortNum_A1" ezfname="pmtTermCashDiscSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezfhyo="A"></td>
																	<td><input type="text" value="-2" name="pmtTermCashDiscCd_A1" ezfname="pmtTermCashDiscCd_A1" class="pHsu" size="2" maxlength="2" ezftoupper style="text-align:right" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" name="pmtTermCashDiscNm_A1" ezfname="pmtTermCashDiscNm_A1" class="pHsu" size="24" maxlength="60" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="pmtTermCashDiscDescTxt_A1" ezfname="pmtTermCashDiscDescTxt_A1" size="22" maxlength="50" ezftoupper ezfhyo="A"></td>
																	<td>
																		<input type="button" class="pBtn0" value="PT" id="OpenWin_PaymentTerm#{EZF_ROW_NUMBER}" name="OpenWin_PaymentTerm" onclick="sendServer(this)" ezfhyo="A">
																		<input type="text" value="--3" name="pmtTermCd_A1" ezfname="pmtTermCd_A1" class="pHsu" size="3" maxlength="3" ezftoupper style="text-align:right" ezfhyo="A">
																		<input type="button" class="pBtn0" value=">>" id="SearchPaymentTermName#{EZF_ROW_NUMBER}" name="SearchPaymentTermName" onclick="sendServer(this)" ezfhyo="A">
																		<input type="text" value="XXXXXXXXX1XXXXXXXXX2" name="pmtTermNm_A1" ezfname="pmtTermNm_A1" size="18" maxlength="18" ezfhyo="A"></td>
																	</td>
                                                                    <td>
																		<select class="pHsu" name="istlPmtTermFlg_A1" ezfname="istlPmtTermFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6460Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N" selected><fmt:message key="i18n.NMAL6460Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
                                                                    <td>
																		<select class="pHsu" name="pmtTermConslFlg_A1" ezfname="pmtTermConslFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6460Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N" selected><fmt:message key="i18n.NMAL6460Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
                                                                    <td>
																		<select class="pHsu" name="pmtTermConslLastDomFlg_A1" ezfname="pmtTermConslLastDomFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6460Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N" selected><fmt:message key="i18n.NMAL6460Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
                                                                    <td>
																		<input type="text" value="-2" name="pmtTermConslDueDay_A1" ezfname="pmtTermConslDueDay_A1" size="2" maxlength="2" ezftoupper style="text-align:right" ezfhyo="A">
																	</td>
                                                                    <td>
																		<input type="text" value="--3" name="pmtTermConslAddMthNum_A1" ezfname="pmtTermConslAddMthNum_A1" size="3" maxlength="3" ezftoupper style="text-align:right" ezfhyo="A">
																	</td>
                                                                    <td>
																		<select class="pHsu" name="pmtCashFlg_A1" ezfname="pmtCashFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6460Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N" selected><fmt:message key="i18n.NMAL6460Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
                                                                    <td>
																		<select class="pHsu" name="pmtCcFlg_A1" ezfname="pmtCcFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6460Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N" selected><fmt:message key="i18n.NMAL6460Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
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
										<td align="right">
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
