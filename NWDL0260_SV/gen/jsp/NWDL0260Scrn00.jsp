<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20131113231628 --%>
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
<fmt:setBundle basename="I18N_NWDL0260Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
			<input type="hidden" name="pageID" value="NWDL0260Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NWDL0260Scrn00.title" bundle="${I18N_SCREEN_ID}">Special Allocation</fmt:message>">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr align="top">
            <td>

				<!--
                <div class="pTab_HEAD">
                    <ul>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="96%">
                                    <div>
                                        <li title="Hiding Invty" class="pTab_ON"><a href="#">Hiding Invty</a></li>
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
                -->

                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                
                <div class="pTab_BODY">
                
<!-- ################################################## Search Criteria - [START] ################################################## -->
					<table width="100%" border="0" cellspacing="0" cellpadding="1">
						<col align="right"  width="400">
 						<col align="center" width="85">
 						<col align="center" width="30">
						<col align="left"   width="200">
						<col align="left">

						<tr align="center">
							<td>
								<table border="0" cellspacing="0" cellpadding="1">
									<tr>
										<!-- Merchandise Code -->
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Merchandise" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NWDL0260Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Merchandise Code</fmt:message></ezf:anchor></td>
										<!-- 2013/08/21 Defect#1290 Mod Start -->
										<!-- <td><input type="text" size="10" maxlength="" ezfToUpper name="mdseCd" ezfname="mdseCd"></td> -->
										<td><ezf:inputText name="mdseCd" ezfName="mdseCd" otherAttr=" size=\"16\" maxlength=\"\" ezftoupper=\"\""/></td>
										<!-- 2013/08/21 Defect#1290 Mod End -->
										<td width="5"></td>
									</tr>
									<tr>
										<!-- Product Line -->
										<td class="stab" align="right"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ProductLine" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NWDL0260Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Product Line</fmt:message></ezf:anchor></td>
										<td><ezf:inputText name="firstProdCtrlCd" ezfName="firstProdCtrlCd" otherAttr=" size=\"2\" maxlength=\"\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>

							<td>
								<table border="0" cellspacing="0" cellpadding="1">
									<tr>
										<!-- WH -->
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_WH" otherAttr=" tabindex=\"-1\""><fmt:message key="i18n.NWDL0260Scrn00.label.3" bundle="${I18N_SCREEN_ID}">WH</fmt:message></ezf:anchor></td>
										<td><ezf:inputText name="invtyLocCd" ezfName="invtyLocCd" otherAttr=" size=\"8\" maxlength=\"\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>

							<td>
								<table border="0" cellspacing="0" cellpadding="1">
									<tr>
										<!-- >> (button) -->
										<td><ezf:inputButton name="SearchAvailableQty" value=">>" htmlClass="pBtn1" /></td>
									</tr>
								</table>
							</td>
							
							<td>
								<table border="1" cellspacing="0" cellpadding="0">
									<col width="70"  align="center">
									<col width=""    align="right">
									<col width=""    align="right">
								
									<tr height="">
										<td class="pClothBs"><fmt:message key="i18n.NWDL0260Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Qty</fmt:message></td>
										<td align="center" class="pClothBs"><fmt:message key="i18n.NWDL0260Scrn00.label.5" bundle="${I18N_SCREEN_ID}">On Hand</fmt:message></td>
										<td align="center" class="pClothBs"><fmt:message key="i18n.NWDL0260Scrn00.label.6" bundle="${I18N_SCREEN_ID}">In Transit</fmt:message></td>
									</tr>
									<tr height="23">
										<td class="pClothBs"><fmt:message key="i18n.NWDL0260Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Available</fmt:message></td>
										<td align="right"><ezf:label name="invtyAdvcOnHandQty_AV" ezfName="invtyAdvcOnHandQty_AV" /></td>
										<td align="right"><ezf:label name="invtyAdvcInTrnstQty_AV" ezfName="invtyAdvcInTrnstQty_AV" /></td>
									</tr>
									<tr>
										<td class="pClothBs"><fmt:message key="i18n.NWDL0260Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Allocation</fmt:message></td>
										<td><ezf:inputText name="invtyAdvcOnHandQty_AL" ezfName="invtyAdvcOnHandQty_AL" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
										<td><ezf:inputText name="invtyAdvcInTrnstQty_AL" ezfName="invtyAdvcInTrnstQty_AL" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr align="center">
							<td align="right" colspan="4" class="stab">
								<table border="0" cellspacing="1" cellpadding="0">
									<tr >
									<!-- Hidding Memo -->
										<td align="center" class="stab"><fmt:message key="i18n.NWDL0260Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Memo</fmt:message></td>
										<td align="right"  class="stab">
											<ezf:textArea name="hidInvtyMemoTxt" ezfName="hidInvtyMemoTxt" otherAttr=" cols=\"70\" rows=\"2\" size=\"40\" maxlength=\"260\" tabindex=\"7\""/>
										</td>
									</tr>
								</table>
							</td>
							<td align="center" class="stab">
								<!-- Allocate (button) -->
								<ezf:inputButton name="Allocate" value="Allocate" htmlClass="cBtn" />
								&nbsp;&nbsp;<ezf:inputButton name="Search" value="Search" htmlClass="cBtn" />
								&nbsp;&nbsp;<ezf:inputCheckBox name="xxChkBox_HD" ezfName="xxChkBox_HD" value="Y" /><fmt:message key="i18n.NWDL0260Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Hidden by me</fmt:message>
							</td>
						</tr>
					</table>

<!-- ################################################## Search Criteria - [E N D] ################################################## -->

					<hr>
					
<!-- ################################################## Search Result - [START] ################################################## -->
					<table border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td colspan="2" align="right">
								<!--
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
										<td class="stab">Showing</td>
										<td class="pOut">1</td>
										<td class="stab">to</td>
										<td class="pOut">15</td>
										<td class="stab">of</td>
										<td class="pOut">200</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Prev"  id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next"  id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')" disabled></td>
									</tr>
								</table>
								-->
								<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"			value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"			value="A" />
									<jsp:param name="ShowingFrom"		value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"			value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"			value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent"	value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"		value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"			value="FULL" />
								</jsp:include>
							</td>
						</tr>
						<tr>
							<td align="right" valign="top" align="right">
								<div style="height:390">
									<table id="A_LEFT" border="1" cellspacing="0" cellpadding="1" align="center" style="margin-top:3px;">
										<col align="center" width="30">
										<col align="center" width="55">		<!-- # -->
										<col align="center" width="120">	<!-- Date -->
										<col align="center" width="65">		<!-- User -->
										<col align="center" width="60">		<!-- WH -->
										<col align="center" width="110">	<!-- Merchandise Code -->
										<col align="center" width="180">	<!-- Merchandise Name -->

										<tr>
											<td class="pClothBs" rowspan="2">&nbsp;</td>
											<td class="pClothBs" rowspan="2">#</td>
											<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'hidTs_A1' )"><fmt:message key="i18n.NWDL0260Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Date</fmt:message><img id="sortIMG.hidTs_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NWDL0260Scrn00.label.12" bundle="${I18N_SCREEN_ID}">User</fmt:message></td>
											<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NWDL0260Scrn00.label.3" bundle="${I18N_SCREEN_ID}">WH</fmt:message></td>
											<td class="pClothBs" colspan="2"><fmt:message key="i18n.NWDL0260Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Merchandise</fmt:message></td>
										</tr>

										<tr>
											<td class="pClothBs"><fmt:message key="i18n.NWDL0260Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Code</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NWDL0260Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Name</fmt:message></td>
										</tr>
										
										<%@ page import="business.servlet.NWDL0260.NWDL0260BMsg" %>
										<%@ page import="business.servlet.NWDL0260.NWDL0260_ABMsgArray" %>
										
										<%! String FMT_TIME_STAMP = "yyyyMMddHHmmsssss"; %>
										<% NWDL0260_ABMsgArray bMsgArray = ((NWDL0260BMsg)databean.getEZDBMsg()).A; %>
										<% int i = 0; %>
										
										<ezf:row ezfHyo="A">
											<tr id="A_LEFT_TR_#{EZF_ROW_NUMBER}" height="24" >
												<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('OnClick_LineCheckbox', '{EZF_ROW_NUMBER}')" /></td>
												<td align="right"><ezf:label name="hidInvtyPk_A1" ezfName="hidInvtyPk_A1" ezfHyo="A" ezfArrayNo="0" /></td>
												
												<!-- Date(MM/dd/yyyy HH:mm) -->
												<td>
													<% String hidTs = bMsgArray.no(i++).hidTs_A1.getValue(); %>
													<% if (hidTs.length() == FMT_TIME_STAMP.length()) { %>
														<% try { %>
															<!-- START 2013/10/30 A.Wada [Def#2852, MOD] -->
															<%-- <%= com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil.DateFormatter(hidTs, FMT_TIME_STAMP, "MM/dd/yyyy HH:mm") %> --%>
															<%= com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil.formatEzd8ToDisp(hidTs.substring(0,8)) + 
															    com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil.DateFormatter(hidTs, FMT_TIME_STAMP, " HH:mm") %>
															<!-- END   2013/10/30 A.Wada [Def#2852, MOD] -->
														<% } catch (Exception e) { %>
															&nbsp;
														<% } %>
													<% } else { %>
														&nbsp;
													<% } %>
												</td>
												
												<td align="left"><ezf:label name="hidUserId_A1" ezfName="hidUserId_A1" ezfHyo="A" ezfArrayNo="0" /></td>
												<td align="left"><ezf:label name="invtyLocCd_A1" ezfName="invtyLocCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
												<td align="left"><ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
												<td align="left"><ezf:label name="mdseNm_A1" ezfName="mdseNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="24" >
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
											<tr height="24" >
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
											<tr>
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
											<tr height="24" >
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
											<tr height="24" >
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
											<tr height="24" >
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
											<tr height="24" >
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
											<tr height="24" >
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
											<tr height="24" >
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
											<tr height="24" >
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
											<tr height="24" >
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
											<tr height="24" >
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
											<tr height="24" >
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
											<tr height="24" >
												<td><input type="checkbox" value="Y"></td>
												<td align="right"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>12345678</label></td>
												<td align="left"><label ezfOut>1234567890</label></td>
												<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											</tr>
										</ezf:skip>
									</table>
								</div>
							</td>

<%-- ++++++++++++++++++++++++++++++ @Right Table ++++++++++++++++++++++++++++++ --%>

							<td rowspan="2" valign="top" align="left">
								<div id="RightTable"
									style="overflow-y:hidden; height:420; overflow-x:auto; width:450">
									<table id="A_RIGHT" border="1" cellspacing="0" cellpadding="1" width="2250" align="center" style="margin-top:3px;">

										<col align="center" width="40" >		<!-- Product Line Code -->
										<col align="center" width="180">	<!-- Product Line Name -->
										<col align="center" width="100">	<!-- On Hand Qty -->
										<col align="center" width="100">	<!-- In Transit Qty -->
										<col align="center" width="1830">	<!-- Memo -->

										<tr>
											<td class="pClothBs" colspan="2"><fmt:message key="i18n.NWDL0260Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Product Line</fmt:message></td>
											<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyAdvcOnHandQty_A1' )"><fmt:message key="i18n.NWDL0260Scrn00.label.16" bundle="${I18N_SCREEN_ID}">On Hand Qty</fmt:message><img id="sortIMG.invtyAdvcOnHandQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyAdvcInTrnstQty_A1' )"><fmt:message key="i18n.NWDL0260Scrn00.label.17" bundle="${I18N_SCREEN_ID}">In Transit Qty</fmt:message><img id="sortIMG.invtyAdvcInTrnstQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs" rowspan="2"><fmt:message key="i18n.NWDL0260Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Memo</fmt:message></td>
										</tr>

										<tr>
											<td class="pClothBs"><fmt:message key="i18n.NWDL0260Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Code</fmt:message></td>
											<td class="pClothBs"><fmt:message key="i18n.NWDL0260Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Name</fmt:message></td>
										</tr>
										<ezf:row ezfHyo="A">
										<tr id="A_RIGHT_TR_#{EZF_ROW_NUMBER}" height="24" >
											<td align="left"><ezf:label name="firstProdCtrlCd_A1" ezfName="firstProdCtrlCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<td align="left"><ezf:label name="firstProdCtrlNm_A1" ezfName="firstProdCtrlNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<td align="right"><ezf:label name="invtyAdvcOnHandQty_A1" ezfName="invtyAdvcOnHandQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<td align="right"><ezf:label name="invtyAdvcInTrnstQty_A1" ezfName="invtyAdvcInTrnstQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<td align="left"><ezf:label name="hidInvtyMemoTxt_A1" ezfName="hidInvtyMemoTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
										</tr>
										</ezf:row>
										<ezf:skip>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										<tr height="24" >
											<td align="left"><label ezfOut>xx</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxx</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="right"><label ezfOut>1,234,567,890</label></td>
											<td align="left"><label ezfOut>xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxx10xxxxxxxx11xxxxxxxx12xxxxxxxx13xxxxxxxx14xxxxxxxx15xxxxxxxx16xxxxxxxx17xxxxxxxx18xxxxxxxx19xxxxxxxx20xxxxxxxx21xxxxxxxx22xxxxxxxx23xxxxxxxx24xxxxxxxx25xxxxxxxx26</label></td>
										</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<ezf:inputButton name="Cancel" value="Cancel" htmlClass="cBtn" />
							</td>
						</tr>
					</table>
					
<!-- ################################################## Search Result - [E N D] ################################################## -->
				</div>

			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
