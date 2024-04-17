<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20191224143146 --%>
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
			<input type="hidden" name="pageID" value="NWAL1610Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mass Update PopUp">
			<BR>
			<BR>
<%-- ##### TAB(Line) ##### --%>
				<%@ page import="business.servlet.NWAL1610.NWAL1610BMsg" %>
				<%@ page import="business.servlet.NWAL1610.common.NWAL1610CommonLogic" %>
				<% NWAL1610BMsg bizMsg = (NWAL1610BMsg)databean.getEZDBMsg(); %>
				<% boolean logi = NWAL1610CommonLogic.isLogistics(bizMsg); %>
					<%if ("01".equals(bizMsg.xxModeCd.getValue())) {%>
						<center>
							<table style="width:300px;">
								<tr>
									<td>
										<fieldset>
											<legend style="font-size:12px;">Configuration Line Update</legend>
											<table style="table-layout:fixed;" cellspacing="0">
												<col width="10">
												<col width="120">
												<col width="180">
												<tr height="28">
													<td>&nbsp</td>
													<td>
														<% if (logi) {%>
														Ship To Location
														<% } else {%>
														<ezf:anchor name="stCd_LK" ezfName="stCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipTo" otherAttr=" ezfanchortext=\"\"">
															Ship To Location
														</ezf:anchor>
														<% } %>
													</td>
													<td><ezf:inputText name="shipToCustLocCd" ezfName="shipToCustLocCd" value="SH0001" otherAttr=" size=\"21\" maxlength=\"20\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="28">
													<td>&nbsp</td>
													<td>
														<% if (logi) {%>
														Bill To Location
														<% } else {%>
														<ezf:anchor name="stCd_LK" ezfName="stCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" ezfanchortext=\"\"">
															Bill To Location
														</ezf:anchor>
														<% } %>
													</td>
													<td><ezf:inputText name="billToCustLocCd" ezfName="billToCustLocCd" value="BI0001" otherAttr=" size=\"21\" maxlength=\"20\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="28">
													<td>&nbsp</td>
													<td>
														<% if (logi) {%>
														CSMP Number
														<% } else {%>
														<ezf:anchor name="stCd_LK" ezfName="stCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CSMPNumber" otherAttr=" ezfanchortext=\"\"">
															CSMP Number
														</ezf:anchor>
														<% } %>
													</td>
													<td><ezf:inputText name="csmpContrNum" ezfName="csmpContrNum" value="BI0001" otherAttr=" size=\"18\" maxlength=\"15\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="28">
													<td>&nbsp</td>
													<td>
														<% if (logi) {%>
														CSA Dealer Ref#
														<% } else {%>
														<ezf:anchor name="stCd_LK" ezfName="stCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CSMPNumber" otherAttr=" ezfanchortext=\"\"">
															CSA Dealer Ref#
														</ezf:anchor>
														<% } %>
													</td>
													<td><ezf:inputText name="dlrRefNum" ezfName="dlrRefNum" value="BI0001" otherAttr=" size=\"23\" maxlength=\"20\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="28">
													<td colspan = "3" align="center">
														<ezf:inputButton name="CMN_OK" value="OK" htmlClass="pBtn4" />
														&nbsp
														<ezf:inputButton name="CMN_Cancel" value="Cancel" htmlClass="pBtn4" />
													</td>
												</tr>	
											</table>
										</fieldset>
									</td>
								</tr>
							</table>
						</center>
					<%} else {%>
						<center>
							<table style="width:530px;">
								<tr>
									<td>
										<fieldset>
											<% if ("02".equals(bizMsg.xxModeCd.getValue())) {%>
												<legend style="font-size:12px;">Order Line Update</legend>
											<% } else {%>
												<legend style="font-size:12px;">RMA Line Update</legend>
											<% } %>
											<table style="table-layout:fixed;">
												<col width="95">
												<col width="150">
												<col width="20">
												<col width="90">
												<col width="160">
												<tr>
													<td class="stab">Order Qty</td>
													<td><ezf:inputText name="ordQty" ezfName="ordQty" value="ABCDS-1234" otherAttr=" size=\"20\" maxlength=\"10\" ezftoupper=\"\""/></td>
													<% if ("02".equals(bizMsg.xxModeCd.getValue())) {%>
														<td>&nbsp</td>
														<td class="stab">
															<% if (logi) {%>
															Substitute Item
															<% } else {%>
															<ezf:anchor name="stCd_LK" ezfName="stCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SubstituteItem" otherAttr=" ezfanchortext=\"\"">
																Substitute Item
															</ezf:anchor>
															<% } %>
														</td>
														<td><ezf:inputText name="xxScrItem20Txt" ezfName="xxScrItem20Txt" value="ABCDS-1234" otherAttr=" size=\"20\" maxlength=\"16\" ezftoupper=\"\""/></td>
													<% } else {%>
 														<td>&nbsp</td>
                                                    	<td class="stab">
                                                        	Req. Pick Up Date
                                                    	</td>
                                                    	<td>
															<ezf:inputText name="rqstPickUpDt" ezfName="rqstPickUpDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"8\""/>
															<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstPickUpDt', 4);" >
                                                    	</td>
													<% } %>
												</tr>
												<tr>
													<td class="stab">Line Category</td>
													<td>
														<ezf:select name="dsOrdLineCatgCd" ezfName="dsOrdLineCatgCd" ezfBlank="1" ezfCodeName="dsOrdLineCatgCd_CD" ezfDispName="dsOrdLineCatgNm_DI" otherAttr=" style=\"width : 140px;\""/>
													</td>
													<td>&nbsp</td>
													<td class="stab">
														<% if (logi) {%>
														Price List
														<% } else {%>
														<ezf:anchor name="prcCatgNm_LK" ezfName="prcCatgNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_PriceList" otherAttr=" ezfanchortext=\"\"">
															Price List
														</ezf:anchor>
														<% } %>
													</td>
													<td><ezf:inputText name="prcCatgNm" ezfName="prcCatgNm" value="ABCDS-1234" otherAttr=" size=\"20\" maxlength=\"75\""/></td>
												</tr>
												<tr>
													<% if ("02".equals(bizMsg.xxModeCd.getValue())) {%>
														<td class="stab">Line Source</td>
														<td>
														<ezf:select name="ordLineSrcCd" ezfName="ordLineSrcCd" ezfBlank="1" ezfCodeName="ordLineSrcCd_CD" ezfDispName="ordLineSrcNm_DI" otherAttr=" style=\"width : 140px;\""/>
														</td>
													<% } else {%>
														<td class="stab">Return Reason Code</td>
														<td>
															<ezf:select name="rtrnRsnCd" ezfName="rtrnRsnCd" ezfBlank="1" ezfCodeName="rtrnRsnCd_CD" ezfDispName="rtrnRsnDescTxt_DI" otherAttr=" style=\"width : 140px;\""/>
														</td>
													<% } %>
													<td>&nbsp</td>
													<td class="stab">
														<% if (logi) {%>
														Floor Price List
														<% } else {%>
														<ezf:anchor name="flPrcListNm_LK" ezfName="flPrcListNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_FloorPriceList" otherAttr=" ezfanchortext=\"\"">
															Floor Price List
														</ezf:anchor>
														<% } %>
													</td>
													<td><ezf:inputText name="flPrcListNm" ezfName="flPrcListNm" value="ABCDS-1234" otherAttr=" size=\"20\" maxlength=\"75\""/></td>
												<tr>
													<td class="stab">
														<% if (logi && "03".equals(bizMsg.xxModeCd.getValue())) {%>
														Warehouse
														<% } else {%>
														<ezf:anchor name="rtlWhNm_LK" ezfName="rtlWhNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" ezfanchortext=\"\"">
															Warehouse
														</ezf:anchor>
														<% } %>
													</td>
													<td><ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="ABCDS-1234" otherEvent1="OnBlur_ChangeWH" otherAttr=" size=\"20\" maxlength=\"60\" ezffocusout=\"OnBlur_ChangeWH\""/></td>
													<td>&nbsp</td>
													<td class="stab">Pricing Date</td>
													<td>
														<ezf:inputText name="prcBaseDt" ezfName="prcBaseDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"8\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('prcBaseDt', 4);">
													</td>
												</tr>
												<tr>
													<td class="stab">
														Sub Warehouse
													</td>
													<td><ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" value="ABCDS-1234" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
													<% if ("02".equals(bizMsg.xxModeCd.getValue())) {%>
														<td colspan="3">&nbsp</td>
													<% } else { %>
														<td>&nbsp</td>
														<td class="stab">Hard Drive Flag</td>
														<td>
															<ezf:select name="hddRmvCd" ezfName="hddRmvCd" ezfBlank="1" ezfCodeName="hddRmvCd_CD" ezfDispName="hddRmvNm_DI" otherAttr=" style=\"width : 130px;\""/>
														</td>
													<% } %>
												</tr>
												<tr>
													<% if ("02".equals(bizMsg.xxModeCd.getValue())) {%>
                                                    <td class="stab">
                                                        Req. Delivery Date
                                                    </td>
                                                    <td>
														<ezf:inputText name="rddDt" ezfName="rddDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"8\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rddDt', 4);" >
                                                    </td>
													<% } else { %>
                                                    <td class="stab">&nbsp;
                                                    </td>
                                                    <td>&nbsp;
                                                    </td>
													<% } %>
													<td colspan="3">&nbsp;</td>
												</tr>
												<tr>
													<td colspan = "5" align="center">
														<ezf:inputButton name="CMN_OK" value="OK" htmlClass="pBtn4" />
														&nbsp
														<ezf:inputButton name="CMN_Cancel" value="Cancel" htmlClass="pBtn4" />
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
							</table>
						</center>
					<%}%>


<%-- **** Task specific area ends here **** --%>
