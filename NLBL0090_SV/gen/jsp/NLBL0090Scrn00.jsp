<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20130531152140 --%>
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
<fmt:setBundle basename="I18N_NLBL0090Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NLBL0090Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NLBL0090Scrn00.title" bundle="${I18N_SCREEN_ID}">BOL Tracking</fmt:message>">

<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
			
<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<%--
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="<fmt:message key="i18n.NLBL0090Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Shipping Order</fmt:message>"			class="pTab_OFF" ><a href="./NLBL0020Scrn00.html"><fmt:message key="i18n.NLBL0090Scrn00.label.1" bundle="${I18N_SCREEN_ID}">SO</fmt:message></a></li>
										<li title="<fmt:message key="i18n.NLBL0090Scrn00.label.2" bundle="${I18N_SCREEN_ID}">B/L Tracking</fmt:message>"			class="pTab_ON"><a href="./NLBL0090Scrn00.html"><fmt:message key="i18n.NLBL0090Scrn00.label.2" bundle="${I18N_SCREEN_ID}">B/L Tracking</fmt:message></a></li>
										<li title="<fmt:message key="i18n.NLBL0090Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Routing Wave</fmt:message>"			class="pTab_OFF"><a href="./NLBL0010Scrn00.html"><fmt:message key="i18n.NLBL0090Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Routing Wave</fmt:message></a></li>
										<li title="<fmt:message key="i18n.NLBL0090Scrn00.title.4" bundle="${I18N_SCREEN_ID}">Customer PickUp</fmt:message>"			class="pTab_OFF"><a href="./NLBL0030Scrn00.html"><fmt:message key="i18n.NLBL0090Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Cust PickUp</fmt:message></a></li>
									</div>
								</td>
								<td></td>
							</tr>
						</table>
					</ul>
				</div>
				--%>

				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table>
						<col width="50">
						<col width="225">
						<col width="0">

						<col width="85">
						<col width="225">
						<col width="5">

						<col width="45">
						<col width="85">
						<col width="30">
						<col width="160">

						<tr>
							<td class="stab"><fmt:message key="i18n.NLBL0090Scrn00.label.5" bundle="${I18N_SCREEN_ID}">B/L#(*)</fmt:message></td>
							<td><ezf:inputText name="bolNum_H1" ezfName="bolNum_H1" value="123456789012345" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td></td>

							<td class="stab" align="right"><fmt:message key="i18n.NLBL0090Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Tracking/Pro#(*)</fmt:message></td>
							<td><ezf:inputText name="proNum_H1" ezfName="proNum_H1" value="123456789012345" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td></td>

							<td class="stab" align="right"><ezf:anchor name="carrCd_H1" ezfName="carrCd_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_Carrier" ><fmt:message key="i18n.NLBL0090Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Carrier</fmt:message></ezf:anchor></td>
							<td><ezf:inputText name="carrCd_H2" ezfName="carrCd_H2" value="1234567890" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
							<td><ezf:inputButton name="CarrierButton" value=">>" htmlClass="pBtn0" /></td>
							<td class="pOut"><ezf:label name="carrNm_H1" ezfName="carrNm_H1" /></td>

						</tr>
					</table>

					<table>
						<col width="93">
						<col width="131">
						<col width="95">

						<col width="41">
						<col width="64">
						<col width="30">
						<col width="120">
						<col width="25">

						<col width="45">
						<col width="64">
						<col width="30">
						<col width="120">

						<tr>
							<td class="stab"><ezf:anchor name="whCd" ezfName="whCd" ezfEmulateBtn="1" ezfGuard="OpenWin_WH" ><fmt:message key="i18n.NLBL0090Scrn00.label.8" bundle="${I18N_SCREEN_ID}">WH</fmt:message></ezf:anchor></td>
							<td>
								<ezf:inputText name="whCd_H1" ezfName="whCd_H1" value="1234567" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
							</td>
							<td></td>

							<td class="stab" align="right"><ezf:anchor name="sellToCustCd_H1" ezfName="sellToCustCd_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_SellTo" ><fmt:message key="i18n.NLBL0090Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Account#</fmt:message></ezf:anchor></td>
							<td><ezf:inputText name="sellToCustCd_H2" ezfName="sellToCustCd_H2" value="1234567" otherAttr=" size=\"7\" maxlength=\"7\" ezftoupper=\"\""/></td>
							<td><ezf:inputButton name="SellToButton" value=">>" htmlClass="pBtn0" /></td>
							<td class="pOut"><ezf:label name="dsAcctNm_H2" ezfName="dsAcctNm_H2" /></td>
							<td></td>

							<td class="stab" align="right"><ezf:anchor name="shipToCustCd_H1" ezfName="shipToCustCd_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" ><fmt:message key="i18n.NLBL0090Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Ship To</fmt:message></ezf:anchor></td>
							<td><ezf:inputText name="shipToCustCd_H2" ezfName="shipToCustCd_H2" value="1234567" otherAttr=" size=\"7\" maxlength=\"7\" ezftoupper=\"\""/></td>
							<td><ezf:inputButton name="ShipToButton" value=">>" htmlClass="pBtn0" /></td>
							<td class="pOut"><ezf:label name="dsAcctNm_H3" ezfName="dsAcctNm_H3" /></td>
						</tr>
					</table>

					<table>
						<col width="93">
						<col width="147">
						<col width="55">

						<col width="65">
						<col width="531">


						<tr>
							<td class="stab"><fmt:message key="i18n.NLBL0090Scrn00.label.11" bundle="${I18N_SCREEN_ID}">POD Status Type</fmt:message></td>
							<td>
								<ezf:select name="podStsTpForScrCd_H2" ezfName="podStsTpForScrCd_H2" ezfBlank="1" ezfCodeName="podStsTpForScrCd_H1" ezfDispName="xxEdtCdNm_H2" otherEvent1=" onchange=\"sendServer('PODStatusTypeForSearch')\"" />
							</td>
							<td></td>

							<td class="stab" align="right"><fmt:message key="i18n.NLBL0090Scrn00.label.12" bundle="${I18N_SCREEN_ID}">POD Status</fmt:message></td>
							<td>
								<ezf:select name="podStsCd_H2" ezfName="podStsCd_H2" ezfBlank="1" ezfCodeName="podStsCd_H1" ezfDispName="xxEdtCdNm_H3" />
							</td>
						</tr>
					</table>

					<table>
						<col width="99">
						<col width="72">
						<col width="167">

						<col width="41">
						<col width="72">
						<col width="93">

						<col width="40">
						<col width="93">
						<col width="111">
						<col width="10" align="center">
						<col width="111">
						<col width="90">

						<col width="82">

						<tr>
							<td class="stab"><fmt:message key="i18n.NLBL0090Scrn00.label.13" bundle="${I18N_SCREEN_ID}">CPO#(*)</fmt:message></td>
							<td><ezf:inputText name="trxHdrNum_H1" ezfName="trxHdrNum_H1" value="12345678" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
							<td></td>

							<td class="stab" align="right"><fmt:message key="i18n.NLBL0090Scrn00.label.14" bundle="${I18N_SCREEN_ID}">SO#(*)</fmt:message></td>
							<td><ezf:inputText name="soNum_H1" ezfName="soNum_H1" value="12345678" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
							<td></td>

							<td class="stab" align="right"><fmt:message key="i18n.NLBL0090Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Date</fmt:message></td>
							<td class="stab">
								<ezf:select name="xxDtTpCd_H2" ezfName="xxDtTpCd_H2" ezfBlank="1" ezfCodeName="xxDtTpCd_H1" ezfDispName="xxEdtCdNm_H4" />
							</td>
							<td>
								<ezf:inputText name="podStsDt_H1" ezfName="podStsDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'podStsDt_H1', 4 );">
							</td>
							<td class="stab">-</td>
							<td>
								<ezf:inputText name="podStsDt_H2" ezfName="podStsDt_H2" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'podStsDt_H2', 4 );">
							</td>
							<td></td>

							<td><ezf:inputButton name="BOLTrackingSearch" value="Search" htmlClass="pBtn6" /></td>
						</tr>
					</table>

					<hr>

<%-- ######################################## LIST ######################################## --%>
					<table width="1121">
						<col valign="top">
						<tr>
							<td>
<!-- Under tab [BOL Tracking List] Start here -->
								<%-- ###TAB - HEAD --%>
								<div class="pTab_HEAD">
									<ul>
										<li id="tbBOLTracking" title="<fmt:message key="i18n.NLBL0090Scrn00.label.2" bundle="${I18N_SCREEN_ID}">B/L Tracking</fmt:message>" class="pTab_ON">
											<ezf:anchor name="xxTabProt_A1" ezfName="xxTabProt_A1" ezfEmulateBtn="1" ezfGuard="TAB_BOLTracking" ><fmt:message key="i18n.NLBL0090Scrn00.label.2" bundle="${I18N_SCREEN_ID}">B/L Tracking</fmt:message></ezf:anchor>
										</li>
										<li id="tbBOLDetail" title="<fmt:message key="i18n.NLBL0090Scrn00.label.16" bundle="${I18N_SCREEN_ID}">B/L Detail</fmt:message>" class="pTab_OFF">
											<ezf:anchor name="xxTabProt_B1" ezfName="xxTabProt_B1" ezfEmulateBtn="1" ezfGuard="TAB_BOLDetail" ><fmt:message key="i18n.NLBL0090Scrn00.label.16" bundle="${I18N_SCREEN_ID}">B/L Detail</fmt:message></ezf:anchor>
										</li>
									</ul>
								</div>

								<c:choose>
								
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='tbBOLTracking'}">
										<script type="text/javascript">
											document.getElementById( "tbBOLTracking" ).className="pTab_ON";
											document.getElementById( "tbBOLDetail" ).className="pTab_OFF";
										</script>


										<%-- ###TAB - BODY --%>
										<div class="pTab_BODY_In">
											<table height="388" width="100%">
												<tr valign="top">
													<td>
														<%-- ###Pagenation --%>
														<table border="0" cellpadding="1" cellspacing="0">
															<col width="1094"  align="right">
															<tr>
																<td>
																	<%-- Pagenation --%>
																	<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																		<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																		<jsp:param name="TableNm"     value="A" />
																		<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A1" />
																		<jsp:param name="ShowingTo"   value="xxPageShowToNum_A1" />
																		<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A1" />
																	</jsp:include>
																</td>
															</tr>
														</table>

														<%-- ### Table:A --%>
														<table border="1" cellpadding="0" cellspacing="0">
															<tr>
																<td align="left" valign="top">
																	
																	<%-- ### A:Left Table HEADER--%>
																	<table border="1" cellpadding="1" cellspacing="0" width="288">

																		<col width="30">
																		<col width="240" align="center">
																		
																		<tr height="28">
																			<td class="pClothBs">&nbsp;</td>
																			<td class="pClothBs"><fmt:message key="i18n.NLBL0090Scrn00.label.17" bundle="${I18N_SCREEN_ID}">B/L#</fmt:message></td>
																		</tr>
																	</table>
																	
																	<%-- ### A:Left Table BODY--%>
																	<div id="leftTBL" style="overflow-y:hidden; height:283; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));">
																		<table border="1" cellpadding="1" cellspacing="0" width="288" id="A_left">

																			<col width="30" align="center">
																			<col width="240" align="left">

																			<ezf:row ezfHyo="A">
																				<tr id="id_row{EZF_ROW_NUMBER}" height="28">
																					<td><ezf:inputRadio name="xxRadioBtn_A1" ezfName="xxRadioBtn_A1" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"radioBtn#{EZF_ROW_NUMBER}\""/></td>
																					<td align="left"><ezf:label name="bolNum_A1" ezfName="bolNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																				</tr>
																			</ezf:row>
																			
																			<ezf:skip>
																				<tr height="28">
																					<td align="left"><label ezfout>&nbsp;</label></td>
																					<td align="left"><label ezfout>&nbsp;</label></td>
																				</tr>
																			</ezf:skip>

																		</table>
																	</div>
																</td>

																<td valign="top">
																	<%-- ### A:Right Table HEAD--%>
																	<div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:804;" onScroll="synchroScrollLeft(this.id, new Array('rightTBL'));">
																		<%-- 2013/05/29 R-OM025 Inventory Transaction Mod Start --%>
																		<%-- <table border="1" cellpadding="1" cellspacing="0" width="1942"> --%>
																		<table border="1" cellpadding="1" cellspacing="0" width="2018">
																		<%-- 2013/05/29 R-OM025 Inventory Transaction Mod End --%>
																			<col width="240" align="center">
																			<col width="120" align="center">
																			<%-- 2013/05/29 R-OM025 Inventory Transaction Mod Start --%>
																			<%-- <col width="24" align="center"> --%>
																			<col width="100" align="center">
																			<%-- 2013/05/29 R-OM025 Inventory Transaction Mod End --%>
																			<col width="56" align="center">
																			<col width="56" align="center">
																			<col width="64" align="center">
																			<col width="64" align="center">
																			<col width="88" align="center">
																			<col width="92" align="center">
																			<col width="480" align="center">
																			<col width="112" align="center">
																			<col width="480" align="center">

																			<tr height="28">
																				<td class="pClothBs"><fmt:message key="i18n.NLBL0090Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Tracking/Pro#</fmt:message></td>
																				<td class="pClothBs"><fmt:message key="i18n.NLBL0090Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Carrier</fmt:message></td>
																				<td class="pClothBs"><fmt:message key="i18n.NLBL0090Scrn00.label.8" bundle="${I18N_SCREEN_ID}">WH</fmt:message></td>
																				<td class="pClothBs"><fmt:message key="i18n.NLBL0090Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Account#</fmt:message></td>
																				<td class="pClothBs"><fmt:message key="i18n.NLBL0090Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Ship To</fmt:message></td>
																				<td class="pClothBs"><fmt:message key="i18n.NLBL0090Scrn00.label.19" bundle="${I18N_SCREEN_ID}">CPO#</fmt:message></td>
																				<td class="pClothBs"><fmt:message key="i18n.NLBL0090Scrn00.label.20" bundle="${I18N_SCREEN_ID}">SO#</fmt:message></td>
																				<td class="pClothBs"><fmt:message key="i18n.NLBL0090Scrn00.label.21" bundle="${I18N_SCREEN_ID}">POD Status Date</fmt:message></td>
																				<td class="pClothBs"><fmt:message key="i18n.NLBL0090Scrn00.label.22" bundle="${I18N_SCREEN_ID}">POD Status Cd</fmt:message></td>
																				<td class="pClothBs"><fmt:message key="i18n.NLBL0090Scrn00.label.23" bundle="${I18N_SCREEN_ID}">POD Status Nm</fmt:message></td>
																				<td class="pClothBs"><fmt:message key="i18n.NLBL0090Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Status Reason Cd</fmt:message></td>
																				<td class="pClothBs"><fmt:message key="i18n.NLBL0090Scrn00.label.25" bundle="${I18N_SCREEN_ID}">Status Reason Nm</fmt:message></td>
																			</tr>
																		</table>
																	</div>

																	<%-- ### A:Right Table BODY--%>
																		<div id="rightTBL" style="overflow-y:scroll; height:300; overflow-x:scroll; width:821;" onScroll="synchroScrollTop(this.id, new Array('leftTBL')); onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
																		<%-- 2013/05/29 R-OM025 Inventory Transaction Mod Start --%>
																		<%-- <table border="1" cellpadding="1" cellspacing="0" width="1942" id="A_right"> --%>
																		<table border="1" cellpadding="1" cellspacing="0" width="2018" id="A_right">
																		<%-- 2013/05/29 R-OM025 Inventory Transaction Mod End --%>
																			<col width="240" align="left">
																			<col width="120" align="left">
																			<%-- 2013/05/29 R-OM025 Inventory Transaction Mod Start --%>
																			<%-- <col width="24" align="left"> --%>
																			<col width="100" align="left">
																			<%-- 2013/05/29 R-OM025 Inventory Transaction Mod End --%>
																			<col width="56" align="center">
																			<col width="56" align="center">
																			<col width="64" align="center">
																			<col width="64" align="center">
																			<col width="88" align="center">
																			<col width="92" align="center">
																			<col width="480" align="center">
																			<col width="112" align="center">
																			<col width="480" align="center">
																			<tbody>
																				<ezf:row ezfHyo="A">
																					<tr height="28" id="rightTBL_A_tr_${EZF_ROW_NUMBER}">
																						<td align="left">
																							<ezf:anchor name="carrTrkUrl_A1" ezfName="carrTrkUrl_A1" ezfHyo="A" ezfArrayNo="0" onClick="window.open( this.href, '_blank', 'status=yes, toolbar=yes, location=yes, menubar=yes, scrollbars=yes, resizable=yes');return false;" otherAttr=" ezfanchortext=\"\"">
																							<ezf:label name="proNum_A1" ezfName="proNum_A1" ezfHyo="A" ezfArrayNo="0" />
																							</ezf:anchor>
																						</td>
																						<td align="left"><ezf:label name="locNm_A1" ezfName="locNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																						<td align="left"><ezf:label name="whCd_A1" ezfName="whCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																						<td align="left"><ezf:label name="sellToCustCd_A1" ezfName="sellToCustCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																						<td align="left"><ezf:label name="shipToCustCd_A1" ezfName="shipToCustCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																						<td align="left"><ezf:label name="trxHdrNum_A1" ezfName="trxHdrNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																						<td align="left"><ezf:label name="soNum_A1" ezfName="soNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																						<td align="left"><ezf:label name="podStsDt_A1" ezfName="podStsDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																						<td align="left"><ezf:label name="podStsCd_A2" ezfName="podStsCd_A2" ezfHyo="A" ezfArrayNo="0" /></td>
																						<td align="left"><ezf:label name="podStsNm_A1" ezfName="podStsNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																						<td align="left"><ezf:label name="podStsRsnCd_A2" ezfName="podStsRsnCd_A2" ezfHyo="A" ezfArrayNo="0" /></td>
																						<td align="left"><ezf:label name="podStsRsnNm_A1" ezfName="podStsRsnNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																					</tr>
																				</ezf:row>

																				<ezf:skip>
																					<tr height="28">
																						<td align="left"><label ezfout>&nbsp;</label></td>
																						<td align="left"><label ezfout>&nbsp;</label></td>
																						<td align="left"><label ezfout>&nbsp;</label></td>
																						<td align="left"><label ezfout>&nbsp;</label></td>
																						<td align="left"><label ezfout>12345678</label></td>
																						<td align="left"><label ezfout>12345678</label></td>
																						<td align="left"><label ezfout>&nbsp;</label></td>
																						<td align="left"><label ezfout>&nbsp;</label></td>
																						<td align="left"><label ezfout>&nbsp;</label></td>
																						<td align="left"><label ezfout>&nbsp;</label></td>
																						<td align="left"><label ezfout>&nbsp;</label></td>
																					</tr>

																				</ezf:skip>
																			</tbody>
																		</table>
																	</div>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td>
														<table border="0" cellpadding="0" cellspacing="0">
															<col width="1073">
															<tr>
																<td align="left"><ezf:inputButton name="OpenWin_BOLHistory" value="B/L History" htmlClass="pBtn6" /></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
<!-- Under tab [BOL Tracking List] End here -->


<!-- Under tab [BOL Tracking Detail] Start here -->
										
									</c:when>
									
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='tbBOLDetail'}">
										<script type="text/javascript">
											document.getElementById( "tbBOLTracking" ).className="pTab_OFF";
											document.getElementById( "tbBOLDetail" ).className="pTab_ON";
										</script>

										<%-- ###TAB - BODY --%>
										<div class="pTab_BODY_In">
											<table height="355" width="100%">
												<tr valign="top">
													<td>
														<%-- ###Pagenation --%>
														<table border="0" cellpadding="1" cellspacing="0">
															<col width="1065"  align="right">
															<tr>
																<td>
																	<%-- Pagenation --%>
																	<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																		<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																		<jsp:param name="TableNm"     value="B" />
																		<jsp:param name="ShowingFrom" value="xxPageShowFromNum_B1" />
																		<jsp:param name="ShowingTo"   value="xxPageShowToNum_B1" />
																		<jsp:param name="ShowingOf"   value="xxPageShowOfNum_B1" />
																	</jsp:include>
																</td>
															</tr>
														</table>

														<table border="1" cellpadding="0" cellspacing="0">
															<tr>
																<%-- ########## Table:B --%>
																<td valign="top">

																	<%-- ### B:Left Table HEADER--%>
																	<table border="1" cellpadding="1" cellspacing="0" width="1065">
																		<col width="64" align="center">
																		<col width="64" align="center">
																		<col width="112" align="center">
																		<col width="223" align="center">
																		<col width="24" align="center">
																		<col width="80" align="center">
																		<col width="64" align="center">
																		<col width="80" align="center">
																		<col width="300" align="center">

																		<tr height="28">
																			<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'soNum_B1' )"><fmt:message key="i18n.NLBL0090Scrn00.label.20" bundle="${I18N_SCREEN_ID}">SO#</fmt:message><img id="sortIMG.soNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'soSlpNum_B1' )"><fmt:message key="i18n.NLBL0090Scrn00.label.26" bundle="${I18N_SCREEN_ID}">SO Line#</fmt:message><img id="sortIMG.soSlpNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdseCd_B1' )"><fmt:message key="i18n.NLBL0090Scrn00.label.27" bundle="${I18N_SCREEN_ID}">Item#</fmt:message><img id="sortIMG.mdseCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdseNm_B1' )"><fmt:message key="i18n.NLBL0090Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Item Nm</fmt:message><img id="sortIMG.mdseNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'fromStkStsCd_B1' )"><fmt:message key="i18n.NLBL0090Scrn00.label.29" bundle="${I18N_SCREEN_ID}">SS</fmt:message><img id="sortIMG.fromStkStsCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'shpgQty_B1' )"><fmt:message key="i18n.NLBL0090Scrn00.label.30" bundle="${I18N_SCREEN_ID}">Quantity</fmt:message><img id="sortIMG.shpgQty_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'trxHdrNum_B1' )"><fmt:message key="i18n.NLBL0090Scrn00.label.19" bundle="${I18N_SCREEN_ID}">CPO#</fmt:message><img id="sortIMG.trxHdrNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'sceOrdTpCd_B1' )"><fmt:message key="i18n.NLBL0090Scrn00.label.31" bundle="${I18N_SCREEN_ID}">SO Type Cd</fmt:message><img id="sortIMG.sceOrdTpCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'sceOrdTpNm_B1' )"><fmt:message key="i18n.NLBL0090Scrn00.label.32" bundle="${I18N_SCREEN_ID}">SO Type Nm</fmt:message><img id="sortIMG.sceOrdTpNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		</tr>
																	</table>

																	<%-- ### B:Left Table BODY--%>
																	<div id="tbl" style="overflow-y:scroll; height:310;">
																		<table border="1" cellpadding="1" cellspacing="0" width="1065" id="B_left">
																			<col width="64" align="left">
																			<col width="64" align="left">
																			<col width="112" align="left">
																			<col width="223" align="center">
																			<col width="24" align="left">
																			<col width="80" align="right">
																			<col width="64" align="left">
																			<col width="80" align="left">
																			<col width="300" align="left">

																			<ezf:row ezfHyo="B">
																				<tr height="28">
																					<td><ezf:label name="soNum_B1" ezfName="soNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																					<td><ezf:label name="soSlpNum_B1" ezfName="soSlpNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																					<td><ezf:label name="mdseCd_B1" ezfName="mdseCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																					<td>
																						<!-- <ezf:label name="mdseDescShortTxt_B1" ezfName="mdseDescShortTxt_B1" ezfHyo="B" ezfArrayNo="0" /> -->
																						<ezf:inputText name="mdseDescShortTxt_B1" ezfName="mdseDescShortTxt_B1"  ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"50\" id=\"mdseDescShortTxt_B1\""/>
																					</td>
																					<td><ezf:label name="fromStkStsCd_B1" ezfName="fromStkStsCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																					<td><ezf:label name="shpgQty_B1" ezfName="shpgQty_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																					<td><ezf:label name="trxHdrNum_B1" ezfName="trxHdrNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																					<td><ezf:label name="sceOrdTpCd_B1" ezfName="sceOrdTpCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																					<td><ezf:label name="sceOrdTpNm_B1" ezfName="sceOrdTpNm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																				</tr>
																			</ezf:row>

																			<ezf:skip>
																				<tr height="28" class="pEvenNumberBGcolor">
																					<td><label ezfout>12345678</label></td>
																					<td><label ezfout>123</label></td>
																					<td><label ezfout>12345678901234</label></td>
																					<td><label ezfout>12345678901234567890</label></td>
																					<td><label ezfout>1</label></td>
																					<td><label ezfout>123,456,78</label></td>
																					<td><label ezfout>12345678</label></td>
																					<td><label ezfout>12</label></td>
																					<td><label ezfout>123456789012345678901234567890</label></td>
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
										
									</c:when>
									
								</c:choose>

<!-- Under tab [BOL Tracking Detail] End here -->
							</td>
						</tr>
					</table>
				</div>

<%-- ######################################## FOOTER ######################################## --%>

			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
