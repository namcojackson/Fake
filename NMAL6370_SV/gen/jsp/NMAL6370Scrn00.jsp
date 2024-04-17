<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100730221536 --%>
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
<fmt:setBundle basename="I18N_NMAL6370Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NMAL6370Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL6370Scrn00.title" bundle="${I18N_SCREEN_ID}">Contact Type Maintenance</fmt:message>">

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
										<li title="<fmt:message key="i18n.NMAL6370Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Price Category</fmt:message>"			class="pTab_OFF" ><a href="./AMALxxxxScrn00.html"><fmt:message key="i18n.NMAL6370Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Contact Type</fmt:message></a></li>
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
<%@ page import="business.servlet.NMAL6370.NMAL6370BMsg" %>
<%@ page import="business.servlet.NMAL6370.NMAL6370_ABMsg" %>
<%@ page import="business.servlet.NMAL6370.common.NMAL6370CommonLogic" %>
<%  NMAL6370BMsg bMsg = (NMAL6370BMsg)databean.getEZDBMsg(); %>
								<table width="1120">
									<col valign="top">
									<tr>
										<td height="20"></td>
										<td width="954" align="right">
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
															<td class="stab"><fmt:message key="i18n.NMAL6370Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Showing</fmt:message></td>
															<td class="pOut">1</td>
															<td class="stab"><fmt:message key="i18n.NMAL6370Scrn00.label.3" bundle="${I18N_SCREEN_ID}">to</fmt:message></td>
															<td class="pOut">6</td>
															<td class="stab"><fmt:message key="i18n.NMAL6370Scrn00.label.4" bundle="${I18N_SCREEN_ID}">of</fmt:message></td>
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
											<table border="0" cellpadding="1" cellspacing="0" width='100%' align="center">
												<tr>
													<td>&nbsp;</td>
													<td valign="top">
														<div id="topTBL" style="overflow-x:hidden; width:983; overflow-y:none; height:19;">
															<table border="1" cellpadding="1" cellspacing="0" align="center">
																<col align="center" width="30">
																<col align="left" width="35">
																<col align="center" width="156">
																<col align="center" width="220">
																<col align="center" width="220">
																<col align="center" width="52">
																<col align="center" width="52">
																<col align="center" width="52">
																<col align="center" width="52">
																
																<tr>
																	<td class="pClothBs">&nbsp;</td>
																	<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacTpSortNum_A1' )"><fmt:message key="i18n.NMAL6370Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Sort<br>Num</fmt:message><img id="sortIMG.ctacTpSortNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctacTpCd_A1' )"><fmt:message key="i18n.NMAL6370Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Code</fmt:message><img id="sortIMG.ctacTpCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs"><fmt:message key="i18n.NMAL6370Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Name</fmt:message></td>
																	<td class="pClothBs"><fmt:message key="i18n.NMAL6370Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Description Text</fmt:message></td>
																	<td class="pClothBs"><fmt:message key="i18n.NMAL6370Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Technical Flag</fmt:message></td>
																	<td class="pClothBs"><fmt:message key="i18n.NMAL6370Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Active Flag</fmt:message></td>
																	
																	<td class="pClothBs"><fmt:message key="i18n.NMAL6370Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Execute Flag</fmt:message></td>
																	<td class="pClothBs"><fmt:message key="i18n.NMAL6370Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Service Indicator Flag</fmt:message></td>
																</tr>
															</table>
														</div>
														<%-- ### TOP TABLE E ### --%>
						
														<%-- ### BOTTOM TABLE S ### --%>
														<div id="bottomTBL" style="overflow-x:none; overflow-y:scroll; width:962; height:450;">
															<table border="1" cellpadding="1" cellspacing="0" id="A" align="right">
																<col align="center" width="30">
																<col align="left" width="35">
																<col align="center" width="156">
																<col align="center" width="220">
																<col align="center" width="220">
																<col align="center" width="52">
																<col align="center" width="52">
																<col align="center" width="52">
																<col align="center" width="52">
																<% int i = 0; %>
																<ezf:row ezfHyo="A">
																<% NMAL6370_ABMsg detailMsg = bMsg.A.no(i++); %>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td>
																		<% boolean disabledxxChkBox = detailMsg.xxChkBox_A1.isInputProtected(); %>
																		<% if(disabledxxChkBox) out.println("<span style='visibility:hidden'>"); %>
																		<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
																		<% if(disabledxxChkBox) out.println("</span>"); %>
																	</td>
																	<td><ezf:inputText name="ctacTpSortNum_A1" ezfName="ctacTpSortNum_A1" value="XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right\" ezftoupper=\"\""/></td>
																	<td><ezf:inputText name="ctacTpCd_A1" ezfName="ctacTpCd_A1" value="---------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
																	<td><ezf:inputText name="ctacTpNm_A1" ezfName="ctacTpNm_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
																	<td><ezf:inputText name="ctacTpDescTxt_A1" ezfName="ctacTpDescTxt_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
																	<td>
																		<ezf:select name="techFlg_A1" ezfName="techFlg_A1" ezfHyo="A" ezfArrayNo="0" >
																			<ezf:option value="Y" ><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></ezf:option>
																			<ezf:option value="N" ><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></ezf:option>
																		</ezf:select>
																	</td>
																	<td>
																		<ezf:select name="actvFlg_A1" ezfName="actvFlg_A1" ezfHyo="A" ezfArrayNo="0" >
																			<ezf:option value="Y" ><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></ezf:option>
																			<ezf:option value="N" ><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></ezf:option>
																		</ezf:select>
																	</td>
																	<td>
																		<ezf:select name="execFlg_A1" ezfName="execFlg_A1" ezfHyo="A" ezfArrayNo="0" >
																			<ezf:option value="Y" ><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></ezf:option>
																			<ezf:option value="N" ><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></ezf:option>
																		</ezf:select>
																	</td>
																	<td>
																		<ezf:select name="svcIndFlg_A1" ezfName="svcIndFlg_A1" ezfHyo="A" ezfArrayNo="0" >
																			<ezf:option value="Y" ><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></ezf:option>
																			<ezf:option value="N" ><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></ezf:option>
																		</ezf:select>
																	</td>
																</tr>
																</ezf:row>
																<ezf:skip>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A1" ezfname="prcCatgSortNum_A1" class="pHsu" size="3" maxlength="3" style="text-align:right" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1"  size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor">
																	<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
																	<td><input type="text" value="XXX" name="prcCatgSortNum_A" ezfname="prcCatgSortNum_A" class="pHsu" size="3" maxlength="3" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="---------1---------2" name="prcCatgCd_A1" ezfname="prcCatgCd_A1" class="pHsu" size="20" maxlength="20" ezftoupper ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgNm_A1" ezfname="prcCatgNm_A1" class="pHsu" size="30" maxlength="50" ezfhyo="A"></td>
																	<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="prcCatgDescTxt_A1" ezfname="prcCatgDescTxt_A1" size="30" maxlength="50" ezfhyo="A"></td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
																		</select>
																	</td>
																	<td>
																		<select class="pHsu" name="cstmReqFlg_A1" ezfname="cstmReqFlg_A1" ezfhyo="A">
																			<option value="Y"><fmt:message key="i18n.NMAL6370Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
																			<option value="N"><fmt:message key="i18n.NMAL6370Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
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
										<td width="62"></td>
									</tr>
									<tr>
										<td width="62"></td>
										<td width="954" align="right">
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
