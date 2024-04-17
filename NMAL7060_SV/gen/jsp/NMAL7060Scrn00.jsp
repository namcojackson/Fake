<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170216170558 --%>
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
			<input type="hidden" name="pageID" value="NMAL7060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Service Meter Package Set Up">

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
											<tr title="Service Meter Pacage Set Up">
												<td width="107px" align="center" class="same">Mtr Pkg Set</td>
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
												<col width="80"  align="left">
												<col width="120" align="left">
												<col width="120" align="left">

												<tr height="20">
													<td class="stab">Package Name</td>
													<td><ezf:inputText name="prcMtrPkgNm" ezfName="prcMtrPkgNm" value="Commercial Ledgen x2" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" /></td>
													<td class="stab">Effective Date From</td>
													<td>
													    <ezf:inputText name="effFromDt" ezfName="effFromDt" value="01/01/2012" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                                        <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >
                                                    </td>
                                                    <td class="stab" rowspan="2">Corporate Advantage Pricing<ezf:inputCheckBox name="corpAdvPrcFlg" ezfName="corpAdvPrcFlg" value="Y" /></td>
												</tr>
												<tr height="20">
													<td class="stab">Package Description</td>
													<td><ezf:inputText name="prcMtrPkgDescTxt" ezfName="prcMtrPkgDescTxt" value="Commercial Double Click Corporate Advantage Plan" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td class="stab">Effective Date To</td>
													<td>
													    <ezf:inputText name="effThruDt" ezfName="effThruDt" value="01/01/2012" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                                        <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" >
                                                    </td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>

							<%-- =============== TOP TABLE HEADER =============== --%>
							<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;margin-top:0px;margin-right:0px;width:880">
								<tr>
									<td>
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
											<col width="580" align="left"><!-- Associated Service Models -->
											<col width="330" align="left"><!-- Models(*) -->
											<col width="170" align="center"><!-- Buttons -->
											<tr height="28">
												<td class="pClothBs">Associated Service Models</td>
												<td class="pClothBs">
													<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;margin-top:0px;margin-right:0px;">
														<col width="70" align="left">
														<col width="80" align="left">
														<col width="5" align="left">
														<col width="60" align="center">
														<tr>
															<td class="pClothBs">Model(*)</td>
															<td><ezf:inputText name="mdlNm_F1" ezfName="mdlNm_F1" value="Commercial Ledgen x2" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/></td>
															<td>&nbsp;</td>
															<td><ezf:inputButton name="FilterSearch" value="Filter" htmlClass="pBtn7" /></td>
														</tr>
													</table>
												</td>
												<td class="pClothBs">
													<ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" />
													<ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" />
												</td>
											</tr>
										</table>
									<td>
								</tr>
							</table>

							<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;margin-top:0px;margin-right:0px;">
								<tr>
									<td>
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
											<col width="30" align="center"><!-- Radio Button -->
											<col width="60" align="center"><!-- Service Model Name -->
											<col width="150" align="center"><!-- Service Model Name -->
											<col width="120" align="center"><!-- Start Date -->
											<col width="120" align="center"><!-- End Date -->
											<col width="100" align="center"><!-- Create Date -->
											<col width="200" align="center"><!-- Create By -->
											<col width="100" align="center"><!-- Last Update Date -->
											<col width="200" align="center"><!-- Last Updat By -->
											<tr height="28">
												<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs" colspan="2">Service Model Name</td>
												<td class="pClothBs">Start Date</td>
												<td class="pClothBs">End Date</td>
												<td class="pClothBs">Create Date</td>
												<td class="pClothBs">Create By</td>
												<td class="pClothBs">Last Update Date</td>
												<td class="pClothBs">Last Update By</td>
											</tr>
										</table>
									</td>
								</tr>

								<%-- =============== TOP TABLE DETAIL =============== --%>
								<tr>
									<td style="vertical-align:top;">
										<div style="overflow-x:auto; overflow-y:scroll;width:1100; height:150;">
											<table id="A" style="table-layout:fixed" border="1" cellpadding="0" cellspacing="0">
											<col width="30" align="center"><!-- Radio Button -->
											<col width="60" align="left"><!-- Service Model Name -->
											<col width="150" align="left"><!-- Service Model Name -->
											<col width="120" align="left"><!-- Start Date -->
											<col width="120" align="left"><!-- End Date -->
											<col width="100" align="left"><!-- Create Date -->
											<col width="200" align="left"><!-- Create By -->
											<col width="100" align="left"><!-- Last Update Date -->
											<col width="200" align="left"><!-- Last Updat By -->
												<ezf:row ezfHyo="A">
													<tr id="id_row${EZF_ROW_NUMBER}">
														<td>
															<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="${EZF_ROW_NUMBER}" ezfGetLineNoHyo="B" ezfGetLineNoOffset="0" otherAttr="onClick=\"sendServer('OnChange_RadioBtn');\""/>
														</td>
														<td><ezf:inputButton name="OpenWin_Model" value="Model" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn4" otherAttr=" id=\"OpenWin_Model{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:anchor name="xxLinkAncr_AM" ezfName="xxLinkAncr_AM" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ServiceMeterPack" otherAttr=" id=\"xxLinkAncr_AM\" ezfanchortext=\"\""><ezf:label name="mdlNm_A1" ezfName="mdlNm_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
														<td>
														    <ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" value="01/01/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
	                                                        <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4, '{EZF_ROW_NUMBER}');" >
                                                        </td>
														<td>
														    <ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
	                                                        <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4, '{EZF_ROW_NUMBER}');" >
                                                        </td>
														<td>
														    <ezf:inputText name="xxDt10Dt_AI" ezfName="xxDt10Dt_AI" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                                        </td>
														<td>
														    <ezf:inputText name="xxFullNm_AI" ezfName="xxFullNm_AI" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"61\""/>
                                                        </td>
														<td>
														    <ezf:inputText name="xxDt10Dt_AU" ezfName="xxDt10Dt_AU" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                                        </td>
														<td>
														    <ezf:inputText name="xxFullNm_AU" ezfName="xxFullNm_AU" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"61\""/>
                                                        </td>
													</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
										</div>
									</td>
								</tr>
							</table>
							<table>
								<tr>
									<td>
										<table border="0" cellpadding="0" cellspacing="0" style="margin-left:0px;margin-top:0px;">
											<%-- =============== LEFT TABLE HEADER =============== --%>
											<tr>
												<td>
													<table style="table-layout:fixed; width:632;" border="1" cellpadding="1" cellspacing="0">
													<col width="450" align="left"><!-- Billing Counters for the Models Selected -->
													<col width="170" align="left"><!-- Buttons -->
														<tr height="28">
															<td class="pClothBs">Billing Counters for the Models Selected</td>
															<td class="pClothBs">
																<ezf:inputButton name="InsertRow_Bllg" value="Insert Row" htmlClass="pBtn7" />
																<ezf:inputButton name="DeleteRow_Bllg" value="Delete Row" htmlClass="pBtn7" />
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
														<col width="30" align="center"><!-- Radio Button -->
														<col width="40" align="center"><!-- Level -->
														<col width="340" align="center"><!-- Billing Counter Name -->
														<col width="100" align="center"><!-- Meter Display -->
														<col width="120" align="center"><!-- Usage Item Code -->
														<tr height="28">
															<td class="pClothBs">&nbsp;</td>
															<td class="pClothBs">Level</td>
															<td class="pClothBs">Billing Counter Name</td>
															<td class="pClothBs">Meter Display</td>
															<td class="pClothBs">Usage Item Code</td>
														</tr>
													</table>
												</td>
											</tr>

											<%-- =============== LEFT TABLE DETAIL =============== --%>
											<tr>
												<td style="vertical-align:top;">
													<div style="overflow-x:auto; overflow-y:scroll; height:230;">
														<table id="B" style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0">
														<col width="30" align="center"><!-- Radio Button -->
														<col width="40" align="center"><!-- Level -->
														<col width="340" align="left"><!-- Billing Counter Name -->
														<col width="100" align="left"><!-- Meter Display -->
														<col width="120" align="left"><!-- Usage Item Code -->
															<ezf:row ezfHyo="B">
																<tr id="id_row${EZF_ROW_NUMBER}">
																	<td>
																		<ezf:inputRadio name="xxRadioBtn_B1" ezfName="xxRadioBtn_B1" value="${EZF_ROW_NUMBER}" ezfGetLineNoHyo="B" ezfGetLineNoOffset="0" otherAttr="onClick=\"sendServer('OnChange_RadioBtn_Bllg');\""/>
																	</td>
																	<td>
																	    <ezf:inputText name="bllgMtrLvlNum_B1" ezfName="bllgMtrLvlNum_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\""/>
			                                                        </td>
																	<td>
																	    <ezf:inputText name="mtrLbDescTxt_B1" ezfName="mtrLbDescTxt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"50\""/>
																		<ezf:inputButton name="OpenWin_PrcMtrPkgBllgMtrNm" value="B" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_PrcMtrPkgBllgMtrNm{EZF_ROW_NUMBER}\""/>
			                                                        </td>
																	<td>
																	    <ezf:inputText name="mtrLbNm_B1" ezfName="mtrLbNm_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\""/>
			                                                        </td>
																	<td>
																	    <ezf:inputText name="mdseCd_B1" ezfName="mdseCd_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"16\""/>
			                                                        </td>
																</tr>
															</ezf:row>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
									<td>
										<%-- =============== RIGHT TABLE HEADER =============== --%>
										<table border="0" cellpadding="0" cellspacing="0" style="margin-left:0px;margin-top:0px;margin-right:0px;width:442px">
											<tr>
												<td>
													<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0">
														<tr height="28">
															<td class="pClothBs">Distinct Hard Counters for Billing Selected</td>
														</tr>
													</table>
												</td>	
											</tr>
										</table>
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td style="vertical-align:top;">
													<div style="overflow-y:scroll; height:258; width:420px;">
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td>
																	<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0">
																		<col width="360" align="center"><!-- Hard Counter -->
																		<col width="80" align="center"><!-- Multiplier -->
																		<tr height="28" style="margin:0px;">
																			<td class="pClothBs">Hard Counter</td>
																			<td class="pClothBs">Multiplier</td>
																		</tr>
																	</table>
																</td>
															</tr>
												<%-- =============== RIGHT TABLE DETAIL =============== --%>
															<tr>
																<td style="vertical-align:top;">
																	<table id="C" style="table-layout:fixed" border="1" cellpadding="0" cellspacing="0">
																		<col width="360" align="left"><!-- Hard Counter -->
																		<col width="80" align="left"><!-- Multiplier -->
																		<ezf:row ezfHyo="C">
																			<tr id="id_row${EZF_ROW_NUMBER}">
																				<td>
																				    <ezf:inputText name="mtrLbDescTxt_C1" ezfName="mtrLbDescTxt_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"50\""/>
						                                                        </td>
																				<td>
																				    <ezf:inputText name="mtrMultRate_C1" ezfName="mtrMultRate_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"text-align:right;\""/>
						                                                        </td>
																			</tr>
																		</ezf:row>
																	</table>
																</td>
															</tr>
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
			</center>

<%-- **** Task specific area ends here **** --%>
