<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20151105154405 --%>
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
			<input type="hidden" name="pageID" value="NSAL0760Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract Status History">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- ######################################## Header ######################################## --%>
				<table border="0" width="99%" align="center">
					<col width="17">
					<col width="140">
					<col width="140">
					<col width="600">
					<tr>
						<td><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" /></td>
						<td class="stab">Active Like Lines</td>
						<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
						<td></td>
					</tr>
					<tr>
						<td><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" /></td>
						<td class="stab">Include Accessories</td>
						<td></td>
						<td  align="right">
							<ezf:skip>
									<table border="0" cellpadding="1" cellspacing="0">
										<col >
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="10">
										<col width="0">
										<col width="1">
										<col width="0">
										<tr>
											<td class="stab">Showing</td>
											<td class="pOut">1</td>
											<td class="stab">to</td>
											<td class="pOut">3</td>
											<td class="stab">of</td>
											<td class="pOut">1000</td>
											<td>&nbsp;</td>
											<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
											<td></td>
											<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
										</tr>
									</table>
							</ezf:skip>

							<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
								<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"     value="A" />
								<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
								<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
							</jsp:include>
						</td>
					</tr>
				</table>
				<hr>
<%-- ######################################## DETAIL #################################### --%>
				<table border="0" cellpadding="0" cellspacing="0" width="99%">
					<col align="left" valign="top">
					<tr>
						<td>
							<div id="Top" style="width:990; text-align:center; overflow-x:hidden; overflow-y:hidden;">
								<table id="Top" border="1" cellpadding="1" cellspacing="0" height="23" >
									<col width="80"  align="center">          <!-- Contract#                     -->
									<col width="100"  align="center">          <!-- Serial# / Access#             -->
									<col width="110"  align="center">          <!-- Service Program               -->
									<col width="160"  align="center">          <!-- Base / Overage                -->
									<col width="50"  align="center">          <!-- PE Seq No                     -->
									<col width="60"  align="center">          <!-- Audit                         -->
									<col width="80"  align="center">          <!-- Start Date                    -->
									<col width="80"  align="center">          <!-- End Date                      -->
									<col width="80"  align="center">          <!-- Date Terminated               -->
									<col width="150"  align="center">          <!-- Status                        -->
									<tr>
										<td class="pClothBs">Contract#</td>
										<td class="pClothBs">Serial# / Access#</td>
										<td class="pClothBs">Service</br>Program</td>
										<td class="pClothBs">Base / Overage</td>
										<td class="pClothBs">PE Seq</br>No</td>
										<td class="pClothBs">&nbsp;</td>
										<td class="pClothBs">Start Date</td>
										<td class="pClothBs">End Date</td>
										<td class="pClothBs">Date Terminated</td>
										<td class="pClothBs">Status</td>
									</tr>
								</table>
							</div>
							<div id="Detail" style="width:1007; overflow-x:hidden; overflow-y:scroll; height:446;">
								<table id="A" border="1" cellpadding="1" cellspacing="0" height="23">
									<col width="80"  align="center">          <!-- Contract#                     -->
									<col width="100"  align="center">          <!-- Serial# / Access#             -->
									<col width="110"  align="center">          <!-- Service Program               -->
									<col width="160"  align="center">          <!-- Base / Overage                -->
									<col width="50"  align="center">        <!-- PE Seq No                     -->
									<col width="60"  align="center">        <!-- Audit                         -->
									<col width="80"  align="center">        <!-- Start Date                    -->
									<col width="80"  align="center">        <!-- End Date                      -->
									<col width="80"  align="center">        <!-- Date Terminated               -->
									<col width="150"  align="center">          <!-- Status                        -->
									<%@ page import="business.servlet.NSAL0760.NSAL0760BMsg" %>
									<%@ page import="java.math.BigDecimal" %>
									<% NSAL0760BMsg bMsg = (NSAL0760BMsg)databean.getEZDBMsg(); %>
									<% int idx=-1; %>
									<ezf:row ezfHyo="A">
										<% idx++; %>
										<tr height="23">
											<% if (bMsg.A.no(idx).xxRefCseNum.getValue().compareTo(BigDecimal.valueOf(10)) == 0) { %>
												<td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="1---------2---------3---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\""/></td>
											<% } else { %>
												<td>&nbsp;</td>
											<% } %>
											<% if (bMsg.A.no(idx).xxRefCseNum.getValue().compareTo(BigDecimal.valueOf(20)) == 0) { %>
												<td>
													<ezf:inputText name="serNum" ezfName="serNum" value="1---------2---------3---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\""/>
												</td>
												<td>
													<ezf:inputText name="mdseDescShortTxt_AS" ezfName="mdseDescShortTxt_AS" value="1---------2---------3---------4---------5---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/>
													<ezf:anchor name="xxBtnFlg_A1" ezfName="xxBtnFlg_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ChargeDisp" otherAttr=" id=\"xxBtnFlg_A1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
													<%if ("Y".equals(bMsg.A.no(idx).xxBtnFlg_A1.getValue())) { %>
														<img src="./img/biz/downarrow2.png" border="0" value="Y">
													<% } else { %>
														<img src="./img/biz/rightarrow2.png" border="0" value="N">
													<% } %>
													</ezf:anchor>
												</td>
											<% } else { %>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											<% } %>
											<% if (bMsg.A.no(idx).xxRefCseNum.getValue().compareTo(BigDecimal.valueOf(31)) == 0  ||
												   bMsg.A.no(idx).xxRefCseNum.getValue().compareTo(BigDecimal.valueOf(32)) == 0  ||
												   bMsg.A.no(idx).xxRefCseNum.getValue().compareTo(BigDecimal.valueOf(33)) == 0) { %>
												<td>
													<ezf:inputText name="xxDplyByCdNmCnctTxt" ezfName="xxDplyByCdNmCnctTxt" value="1---------2---------3---------4---------5---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"50\""/>
													<ezf:anchor name="xxBtnFlg_A2" ezfName="xxBtnFlg_A2" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="EffDisp" otherAttr=" id=\"xxBtnFlg_A2#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
													<%if ("Y".equals(bMsg.A.no(idx).xxBtnFlg_A2.getValue())) { %>
														<img src="./img/biz/downarrow2.png" border="0" value="Y">
													<% } else { %>
														<img src="./img/biz/rightarrow2.png" border="0" value="N">
													<% } %>
													</ezf:anchor>
												</td>
											<% } else { %>
												<td>&nbsp;</td>
											<% } %>
											<% if (bMsg.A.no(idx).xxRefCseNum.getValue().compareTo(BigDecimal.valueOf(41)) == 0  ||
												   bMsg.A.no(idx).xxRefCseNum.getValue().compareTo(BigDecimal.valueOf(42)) == 0  ||
												   bMsg.A.no(idx).xxRefCseNum.getValue().compareTo(BigDecimal.valueOf(43)) == 0) { %>
												<td><ezf:inputText name="dsContrPrcEffSqNum" ezfName="dsContrPrcEffSqNum" value="1---------2---------3---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"4\" maxlength=\"4\""/></td>
											<% } else { %>
												<td>&nbsp;</td>
											<% } %>
											<% if ("Y".equals(bMsg.A.no(idx).xxBtnFlg_A3.getValue())) { %>
												<td align="center"><ezf:inputButton name="OpenWin_AuditTrail" value="Audit" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn3" otherAttr=" id=\"OpenWin_AuditTrail#{EZF_ROW_NUMBER}\""/></td>
											<% } else { %>
												<td>&nbsp;</td>
											<% } %>
											<% if (bMsg.A.no(idx).xxRefCseNum.getValue().compareTo(BigDecimal.valueOf(20)) != 0) { %>
												<td><ezf:label name="xxFromDt" ezfName="xxFromDt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxThruDt" ezfName="xxThruDt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="contrCloDt" ezfName="contrCloDt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="dsContrStsDescTxt" ezfName="dsContrStsDescTxt" value="1---------2---------3---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
											<% } else { %>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											<% } %>
										</tr>
									<ezf:skip>
									</ezf:skip>
									</ezf:row>
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
