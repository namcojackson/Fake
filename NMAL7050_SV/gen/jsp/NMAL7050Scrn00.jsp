<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170202144738 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NMAL7050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Service Meter Package Search">


			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<ezf:skip>
							<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
								<tr>
									<td width="1070px" height="28px" valign="bottom">
										<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
											<tr title="Supplier Search Screen">
												<td width="107px" align="center" class="same">Mtr Pkg Srch</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							</ezf:skip>

							<div class="pTab_BODY">
								<table  style="margin-left:5; margin-top:0;">
									<col width="" align="left">
									<tr>
										<td>
											<table border="0">
												<col width="165" align="left">
												<col width="200"  align="left">
												<col width="5"   align="left">
												<col width="165"  align="left">
												<col width="200" align="left">

												<tr height="20">
													<td class="stab">Meter Package Name(*)</td>
													<td><ezf:inputText name="prcMtrPkgNm" ezfName="prcMtrPkgNm" value="1284147" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab">Contracts Billing Counter Name(*)</td>
													<td><ezf:inputText name="mtrLbDescTxt_BG" ezfName="mtrLbDescTxt_BG" value="AZERTY INC." otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="20">
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_ML" ezfEmulateBtn="1" ezfGuard="OpenWin_Model" >Service Model Name(*)</ezf:anchor></td>
													<td><ezf:inputText name="xxDsMultMsgDplyTxt" ezfName="xxDsMultMsgDplyTxt" value="1284147" otherAttr=" size=\"50\" maxlength=\"4000\" ezftoupper=\"\""/></td>
													<td></td>
													<td class="stab">Contracts Hard Counter Name(*)</td>
													<td><ezf:inputText name="mtrLbDescTxt_PH" ezfName="mtrLbDescTxt_PH" value="AZERTY INC." otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="20">
													<td class="stab">Effective Date</td>
													<td><ezf:inputText name="effFromDt" ezfName="effFromDt" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                                    <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" > - 
													<ezf:inputText name="effThruDt" ezfName="effThruDt" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                                    <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" ></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table border="0">
												<col width="865" align="left">
												<col>

													<td>&nbsp;</td>
													<td>
														<ezf:inputButton name="Search" value="Search" htmlClass="pBtn10" />
														<ezf:inputButton name="MoveWin_MeterPackSetNew" value="Create New" htmlClass="pBtn10" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>

							<hr>

<%@ page import="business.servlet.NMAL7050.NMAL7050BMsg" %>
<%@ page import="business.servlet.NMAL7050.NMAL7050_ABMsg" %>
<%  NMAL7050BMsg bMsg = (NMAL7050BMsg)databean.getEZDBMsg(); %>
							<%-- =============== PAGING =============== --%>
							<table width="98%" border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
								<col align="left" width="180">
								<col align="left" width="">
								<col align="right" width="550">
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td align="right">
										<ezf:skip>
										<table border="0" cellpadding="0" width="100%">
											<tr>
												<td align="left">
													<table border="0" cellpadding="0" align="left" cellspacing="0">
														<col>
															<tr>
																<td>Results 990 - 1000 of 1000</td>
															</tr>
													</table>
												</td>
												<td align="right">
													<table border="0" cellpadding="0" cellspacing="0">
														<col width="54"  align="center">
														<col width="40"  align="center">
														<col width="16"  align="center">
														<col width="40"  align="center">
														<col width="26"  align="center">
														<col width="10">
														<col>
														<col width="20">
														<col>
														<col width="1">
														<col>
														<tr>
															<td class="stab">Showing</td>
															<td><input type="text" name="xxPageShowCurNum" value="1" size="4" maxlength="4" style="text-align:right" ezfname="xxPageShowCurNum"></td>
															<td class="stab">/</td>
															<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="1" class="pPro" style="text-align:right" readOnly></td>
															<td class="stab">page</td>
															<td>&nbsp;</td>
															<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
															<td></td>
															<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
															<td></td>
															<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										</ezf:skip>
										<table width="100%">
											<tr align="right">
												<td>
													<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
														<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
														<jsp:param name="TableNm"           value="A" />
														<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
														<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
														<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
														<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
														<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
														<jsp:param name="ViewMode"          value="FULL" />
													</jsp:include>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;margin-top:5px;">
								<%-- =============== TABLE HEADER =============== --%>
								<tr>
									<td>
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
											<col width="210" align="center">
											<col width="210" align="center">
											<col width="424" align="center">
											<col width="120" align="center">
											<col width="120" align="center">
											<tr height="28">
												<td class="pClothBs">Service Model Name</td>
												<td class="pClothBs">Meter Package Display Name</td>
												<td class="pClothBs">Meter Package Description</td>
												<td class="pClothBs">Start Date</td>
												<td class="pClothBs">End Date</td>
											</tr>
										</table>
									</td>
								</tr>

								<%-- =============== TABLE DETAIL =============== --%>
								<tr>
									<td style="vertical-align:top;">
										<div style="overflow-x:auto; overflow-y:scroll; height:380;">
											<table id="A" style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0">
											<col width="210" align="left">
											<col width="210" align="left">
											<col width="424" align="left">
											<col width="120" align="center">
											<col width="120" align="center">
												<ezf:row ezfHyo="A">
													<tr id="id_row${EZF_ROW_NUMBER}">
														<td><ezf:anchor name="" ezfName="xxLinkAncr_AM" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ServiceModelName" ><ezf:label name="mdlNm_A1" ezfName="mdlNm_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
														<td><ezf:anchor name="" ezfName="xxLinkAncr_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_MeterPackSet" ><ezf:label name="prcMtrPkgNm_A1" ezfName="prcMtrPkgNm_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
														<td><ezf:label name="prcMtrPkgDescTxt_A1" ezfName="prcMtrPkgDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													</tr>
												</ezf:row>
												<ezf:skip>
													<tr>
														<td><a href="#" onclick="sendServer('OpenWin_ServiceModelName')" ezfhyo="A"><label ezfout name="mdlNm_A1" ezfname="mdlNm_A1" ezfhyo="A">ADVC5325</label></a></td>
														<td><label ezfout name="prcMtrPkgNm_A1" ezfname="prcMtrPkgNm_A1" ezfhyo="A">ADVC5325</label></td>
														<td><label ezfout name="prcMtrPkgDescTxt_A1" ezfname="prcMtrPkgDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
														<td><label ezfout name="effFromDt_A1" ezfname="effFromDt_A1" ezfhyo="A">01/01/2000</label></td>
														<td><label ezfout name="effThruDt_A1" ezfname="effThruDt_A1" ezfhyo="A">12/31/2015</label></td>
													</tr>
													<tr>
														<td><a href="#" onclick="sendServer('OpenWin_ServiceModelName')" ezfhyo="A"><label ezfout name="mdlNm_A1" ezfname="mdlNm_A1" ezfhyo="A">ADVC5325</label></a></td>
														<td><label ezfout name="prcMtrPkgNm_A1" ezfname="prcMtrPkgNm_A1" ezfhyo="A">ADVC5325</label></td>
														<td><label ezfout name="prcMtrPkgDescTxt_A1" ezfname="prcMtrPkgDescTxt_A1" ezfhyo="A">&nbsp;</label></td>
														<td><label ezfout name="effFromDt_A1" ezfname="effFromDt_A1" ezfhyo="A">01/01/2000</label></td>
														<td><label ezfout name="effThruDt_A1" ezfname="effThruDt_A1" ezfhyo="A">12/31/2015</label></td>
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
			</center>

<%-- **** Task specific area ends here **** --%>
