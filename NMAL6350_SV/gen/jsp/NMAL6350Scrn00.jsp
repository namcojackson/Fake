<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180904152248 --%>
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
<fmt:setBundle basename="I18N_NMAL6350Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NMAL6350Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL6350Scrn00.title" bundle="${I18N_SCREEN_ID}">Package UoM Maintenance</fmt:message>">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

<%--
                <div class="pTab_HEAD">
                    <ul>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="96%">
                                    <div>
                                        <li title="<fmt:message key="i18n.NMAL6350Scrn00.title" bundle="${I18N_SCREEN_ID}">Package UoM Maintenance</fmt:message>" class="pTab_ON"><a href="#"><fmt:message key="i18n.NMAL6350Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Package UoM</fmt:message></a></li>
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
--%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<div class="pTab_BODY">
					<table height="525">
						<col valign="top">
						<tr>
							<td>

<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NMAL6350.NMAL6350BMsg" %>
<%@ page import="business.servlet.NMAL6350.NMAL6350_ABMsg" %>
<%@ page import="business.servlet.NMAL6350.common.NMAL6350CommonLogic" %>
<%  NMAL6350BMsg bMsg = (NMAL6350BMsg)databean.getEZDBMsg(); %>

								<table width="1120">
									<col valign="top">
									<tr>
										<td height="20"></td>
										<td width="1023" align="right">
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
															<td class="stab"><fmt:message key="i18n.NMAL6350Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Showing</fmt:message></td>
															<td class="pOut">1</td>
															<td class="stab"><fmt:message key="i18n.NMAL6350Scrn00.label.3" bundle="${I18N_SCREEN_ID}">to</fmt:message></td>
															<td class="pOut">6</td>
															<td class="stab"><fmt:message key="i18n.NMAL6350Scrn00.label.4" bundle="${I18N_SCREEN_ID}">of</fmt:message></td>
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
										<td width="50"></td>
										<td>

							<table border="0" cellpadding="1" cellspacing="0" width='100%' align="center">
								<tr>
									<td>&nbsp;</td>
									<td valign="top">
										<div id="topTBL" style="overflow-x:hidden; width:1098; overflow-y:none; height:19;">
											<table border="1" cellpadding="1" cellspacing="0" align="center">
												<col align="center" width="30">
												<col align="center" width="40">
												<col align="center" width="50">
												<col align="center" width="220">
												<col align="center" width="220">
												<col align="center" width="70">
												<col align="center" width="70">
												<col align="center" width="70">
												<col align="center" width="120">
												
												<tr>
													<td class="pClothBs">&nbsp;</td>
													<!-- **** Mod Start 2013/11/19 WDS Defect#2852 **** -->
													<!-- <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pkgUomSortNum_A1' )">Sort<BR>Num<img id="sortIMG.pkgUomSortNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></A></td> -->
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pkgUomSortNum_A1' )"><fmt:message key="i18n.NMAL6350Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Sort<br>Num</fmt:message><img id="sortIMG.pkgUomSortNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></A></td>
													<!-- **** Mod End 2013/11/19 WDS Defect#2852 **** -->
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pkgUomCd_A1' )"><fmt:message key="i18n.NMAL6350Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Code</fmt:message><img id="sortIMG.pkgUomCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></A></td>
													<td class="pClothBs"><fmt:message key="i18n.NMAL6350Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Name</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NMAL6350Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Description Text</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NMAL6350Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Standard Flag</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NMAL6350Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Mandatory Flag</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NMAL6350Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Package Level Num</fmt:message></td>
													<td class="pClothBs"><fmt:message key="i18n.NMAL6350Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Unit of Measure Class</fmt:message></td>
												</tr>
											</table>
										</div>
										<%-- ### TOP TABLE E ### --%>
		
										<%-- ### BOTTOM TABLE S ### --%>
										<div id="bottomTBL" style="overflow-x:none; overflow-y:scroll; width:1030; height:450;">
											<table border="1" cellpadding="1" cellspacing="0" id="A" align="right">
												<col align="center" width="30">
												<col align="center" width="40">
												<col align="center" width="50">
												<col align="center" width="220">
												<col align="center" width="220">
												<col align="center" width="70">
												<col align="center" width="70">
												<col align="center" width="70">
												<col align="center" width="120">
												
												<% int i = 0; %>
												<ezf:row ezfHyo="A">
												<% NMAL6350_ABMsg detailMsg = bMsg.A.no(i++); %>
												<tr height="28" id="id_row{EZF_ROW_NUMBER}">
													<td>
														<% boolean disabledxxChkBox = detailMsg.xxChkBox_A1.isInputProtected(); %>
														<% if(disabledxxChkBox) out.println("<span style='visibility:hidden'>"); %>
														<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
<%--
														<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
--%>
														<% if(disabledxxChkBox) out.println("</span>"); %>
													</td>
													<td><ezf:inputText name="pkgUomSortNum_A1" ezfName="pkgUomSortNum_A1" value="XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="pkgUomCd_A1" ezfName="pkgUomCd_A1" value="XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/></td>
													<td><ezf:inputText name="pkgUomNm_A1" ezfName="pkgUomNm_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
													<td><ezf:inputText name="pkgUomDescTxt_A1" ezfName="pkgUomDescTxt_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"50\""/></td>
													<td>
														<ezf:select name="pkgUomStdFlg_A1" ezfName="pkgUomStdFlg_A1" ezfHyo="A" ezfArrayNo="0" >
															<ezf:option value="Y" ><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></ezf:option>
															<ezf:option value="N" ><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></ezf:option>
														</ezf:select>
													</td>
													<td>
														<ezf:select name="pkgUomMndFlg_A1" ezfName="pkgUomMndFlg_A1" ezfHyo="A" ezfArrayNo="0" >
															<ezf:option value="Y" ><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></ezf:option>
															<ezf:option value="N" ><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></ezf:option>
														</ezf:select>
													</td>
													<td><ezf:inputText name="pkgUomPkgLvlNum_A1" ezfName="pkgUomPkgLvlNum_A1" value="X" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\" maxlength=\"1\""/></td>
													<td>
													<ezf:select name="pkgUomClsCd_A1" ezfName="pkgUomClsCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="pkgUomClsCd_L1" ezfDispName="pkgUomClsNm_L1" otherAttr=" style=\"width:120px\""/>

													</td>
												</tr>
												</ezf:row>
												<ezf:skip>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="50" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
														</select>
													</td>
												</tr>

												<tr height="28">
													<td><input type="checkbox" value="Y"></td>
													<td><input type="text" value="XXX" name="" ezfname="" class="pHsu" size="3" maxlength="3" ezftoupper></td>
													<td><input type="text" value="XXXX" name="" ezfname="" class="pHsu" size="4" maxlength="4" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="" ezfname="" class="pHsu" size="30" maxlength="30" ezftoupper></td>
													<td><input type="text" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="" ezfname="" size="30" maxlength="30" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option value="Y"><fmt:message key="i18n.NMAL6350Scrn00.option.1" bundle="${I18N_SCREEN_ID}">Y</fmt:message></option>
															<option value="N"><fmt:message key="i18n.NMAL6350Scrn00.option.2" bundle="${I18N_SCREEN_ID}">N</fmt:message></option>
														</select>
													</td>
													<td><input type="text" value="X" name="" ezfname="" size="1" maxlength="1" ezftoupper></td>
													<td>
														<select class="pHsu" name="" ezfname="" ezfhyo="A">
															<option>XXXXXXXXX1XXXXXXXXX2</option>
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
								</table>
							</td>
						</tr>

						<tr>
							<td valign="bottom">
<%-- ######################################## FOOTER ######################################## --%>
							<table border="0">
								<col width="838">
								<col width="82">
								<col width="82">
								<col>
								
								<tr>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="Insert_Row" value="Insert Row" htmlClass="pBtn8" /></td>
									<td><ezf:inputButton name="Delete_Row" value="Delete Row" htmlClass="pBtn8" /></td>
									<td>&nbsp;</td>
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
