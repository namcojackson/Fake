<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20151006031523 --%>
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
<fmt:setBundle basename="I18N_NLBL0110Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NLBL0110Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NLBL0110Scrn00.title" bundle="${I18N_SCREEN_ID}">WH End Of Month</fmt:message>">



<center>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
        <tr>
            <td>
            
<!-- ######################################## HEADER ######################################## -->
                <!-- ###TAB - HEAD -->
		<%-- include S21BusinessProcessTAB --%>
		<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

                <!-- ###TAB - BODY -->
                <div class="pTab_BODY">
			<table>
				<col width="16">
				<%-- 2013/05/23 R-OM025 Inventory Transaction Add Start --%>
				<col width="48">
				<%-- 2013/05/23 R-OM025 Inventory Transaction Add End --%>
				<col width="231">
				<col width="724">
				<col width="0">

				<tr height="30">
					<%-- 2013/05/23 R-OM025 Inventory Transaction Mod Start --%>
					<%-- <td class="stab">WH</td> --%>
					<%-- <td> --%>
						<%-- <ezf:select name="whCd_H2" ezfName="whCd_H2" ezfCodeName="whCd_H1" ezfDispName="xxEdtCdNm_H1" /> --%>
					<%-- </td> --%>
					<td class="stab"><ezf:anchor name="whCd_HL" ezfName="whCd_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_RetailWarehouse" ><fmt:message key="i18n.NLBL0110Scrn00.label.1" bundle="${I18N_SCREEN_ID}">WH</fmt:message></ezf:anchor></td>
					<td><ezf:inputText name="whCd_H2" ezfName="whCd_H2" value="WWWWWWWWW0WWWWWWWWW1" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/></td>
					<td><ezf:inputText name="locNm_H2" ezfName="locNm_H2" value="WWWWWWWWW0WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW1" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
					<%-- 2013/05/23 R-OM025 Inventory Transaction Mod End --%>
					<td></td>
					<td align="left"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
				</tr>
			</table>
			
			<hr>


					<table border="0" cellpadding="0" cellspacing="0">
						<col width="10">
						<col width="285">
						<col width="120" align="center">
						<col width="500">
						<tr>
							<td>&nbsp;</td>
							<td valign="top">

								<table border="1" cellpadding="0" cellspacing="0">
									<tr height="508" valign="top">
										<td>
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="10">
												<col width="275">

												<tr height="10">
													<td />
													<td />
												</tr>
												<tr>
													<td />
													<td>
														<table border="1" cellpadding="1" cellspacing="0">
															<col width="34" align="center">
															<col width="80" align="center">
															<col width="126" align="center">

															<tr height="34">
																<td class="pClothBs">&nbsp;</td>
																<td class="pClothBs"><fmt:message key="i18n.NLBL0110Scrn00.label.1" bundle="${I18N_SCREEN_ID}">WH</fmt:message></td>
																<td class="pClothBs">Bussiness Days OF<BR> End Of Month</td>
															</tr>
														</table>

														<div style="overflow-y:scroll; height:459;">
															<table border="1" cellpadding="1" cellspacing="0" id="A_left">
																<col width="34" align="center">
																<col width="80" align="left">
																<col width="126" align="right">

																<ezf:row ezfHyo="A">
																	<tr height="28">
																		<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"radio#{EZF_ROW_NUMBER}\""/></td>
																		<td><ezf:label name="whCd_A1" ezfName="whCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="endMthBizDaysAot_A1" ezfName="endMthBizDaysAot_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right\" size=\"2\" maxlength=\"2\""/></td>
																	</tr>
																</ezf:row>
																<ezf:skip>
																	<tr height="28">
																		<td><input type="radio" name="selectRadio" value="2"></td>
																		<td><label ezfout>37</label></td>
																		<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
																	</tr>
																	<tr height="28">
																		<td><input type="radio" name="selectRadio" value="3"></td>
																		<td><label ezfout>55</label></td>
																		<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
																	</tr>
																	<tr height="28">
																		<td><input type="radio" name="selectRadio" value="10"></td>
																		<td><label ezfout>123456</label></td>
																		<td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="3"></td>
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

							<td>
								<table>
									<tr>
										<td align="center" valign="middle">
											<ezf:inputButton name="View" value=">>  View  >>" htmlClass="pBtn8" />
										</td>
									</tr>
								</table>
							</td>

							<td valign="top">

								<table border="1" cellpadding="0" cellspacing="0" width="500">
									<tr height="508" valign="top">
										<td>
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="10">
												<col width="460">
												<tr height="10">
													<td />
													<td />
												</tr>
												<tr height="10">
													<td />
													<td>
														<table border="0" cellpadding="1" cellspacing="0" width="80">
															<col width="30" align="left">
															<col width="50" align="left">
															<tr>
																<td class="stab"><fmt:message key="i18n.NLBL0110Scrn00.label.1" bundle="${I18N_SCREEN_ID}">WH</fmt:message></td>
																<td class="pOut"><ezf:label name="whCd_D1" ezfName="whCd_D1" /></td>
															<tr>
														</table>
													<td>
												</tr>
												<tr height="10">
													<td />
													<td />
												</tr>
												<tr>
													<td />
													<td>
														<table border="1" cellpadding="1" cellspacing="0" width="458">
															<col width="128" align="center">
															<col width="160" align="center">
															<col width="160" align="center">

															<tr height="17">
																<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NLBL0110Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Month</fmt:message></td>
																<td class="pClothBs" colspan="2"><fmt:message key="i18n.NLBL0110Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Date Of End Of Month</fmt:message></td>
															</tr>
															<tr height="17">
																<td class="pClothBs"><fmt:message key="i18n.NLBL0110Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Start</fmt:message></td>
																<td class="pClothBs"><fmt:message key="i18n.NLBL0110Scrn00.label.6" bundle="${I18N_SCREEN_ID}">End</fmt:message></td>
															</tr>
														</table>

														<div>
															<table border="1" cellpadding="1" cellspacing="0" width="458" id="B_right">
																<col width="128" align="left">
																<col width="160" align="left">
																<col width="160" align="left">

																<ezf:row ezfHyo="B">
																	<tr height="28">
																		<td><ezf:label name="xxYrMth_B1" ezfName="xxYrMth_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="xxFromDt_B1" ezfName="xxFromDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="xxToDt_B1" ezfName="xxToDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																	</tr>
																</ezf:row>
																<ezf:skip>
																	<tr height="28">
																		<td><label ezfout>2010/03</label></td>
																		<td><label ezfout>2010/03/29</label></td>
																		<td><label ezfout>2010/03/31</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfout>2010/04</label></td>
																		<td><label ezfout>2010/04/28</label></td>
																		<td><label ezfout>2010/04/30</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfout>2010/05</label></td>
																		<td><label ezfout>2010/05/26</label></td>
																		<td><label ezfout>2010/05/31</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfout>2010/06</label></td>
																		<td><label ezfout>2010/06/28</label></td>
																		<td><label ezfout>2010/06/30</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfout>2010/07</label></td>
																		<td><label ezfout>2010/07/28</label></td>
																		<td><label ezfout>2010/07/31</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfout>2010/08</label></td>
																		<td><label ezfout>2010/08/27</label></td>
																		<td><label ezfout>2010/08/31</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfout>2010/09</label></td>
																		<td><label ezfout>2010/09/28</label></td>
																		<td><label ezfout>2010/09/30</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfout>2010/10</label></td>
																		<td><label ezfout>2010/10/27</label></td>
																		<td><label ezfout>2010/10/31</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfout>2010/11</label></td>
																		<td><label ezfout>2010/11/24</label></td>
																		<td><label ezfout>2010/11/30</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfout>2010/12</label></td>
																		<td><label ezfout>2010/12/29</label></td>
																		<td><label ezfout>2010/12/31</label></td>
																	</tr>
																	<tr height="28">
																		<td><label ezfout>2011/01</label></td>
																		<td><label ezfout>2011/01/27</label></td>
																		<td><label ezfout>2011/01/31</label></td>
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
    				</table>
				</div>
			</td>
		</tr>
	</table>
</center>












<%-- **** Task specific area ends here **** --%>
