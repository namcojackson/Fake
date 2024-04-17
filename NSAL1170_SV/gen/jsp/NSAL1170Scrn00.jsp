<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161202171003 --%>
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
			<input type="hidden" name="pageID" value="NSAL1170Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Model Escalation Rules Maintenance">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>&nbsp;</td>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
					<tr>
						<td width="1070px" height="28px" valign="bottom">
							<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
								<tr title="Mdl Escl Rule Mnt">
									<td width="107px" align="center" class="same">Mdl Escl Rule Mnt</td>
								</tr>
							</table>
						</td>
						<td width="10px" valign="bottom" align="center">
							<a href="#" id="PrevPageTabIndex"><img id="PrevPageBtn" src="./img/tab/tabbackbutton.png" alt="Prev" border="0" onclick="prevTabPage()"></a>
						</td>
						<td width="10px" valign="bottom" align="center">
							<a href="#" id="NextPageTabIndex"><img id="NextPageBtn" src="./img/tab/tabnextbutton.png" alt="Next" border="0" onclick="nextTabPage()"></a>
						</td>
					</tr>
				</table>
				</ezf:skip>
				<div class="pTab_BODY" align="center">
					<table border="0" width="90%">
						<tr>
							<td width ="100%">
								<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
									<col width="100">
									<col width="700">
									<tr>
										<td class="stab">Model Name</td>
										<td><ezf:inputText name="t_MdlNm" ezfName="t_MdlNm" value="IPC810" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<hr/>
							</td>
						</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>
					<table border="0" width="80%" >
						<tr>
							<td align="center"  >
								<table  border="0" cellpadding="0" cellspacing="0" width="890">
									<col width="" align="right" valign="top">
									<tr>
										<td>
											<ezf:skip>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="54"  align="center">
													<col width="32"  align="right">
													<col width="16"  align="center">
													<col width="32"  align="right">
													<col width="16"  align="center">
													<col width="32"  align="right">
													<col width="10">
													<col>
													<col width="1">
													<col>
													<tr>
														<td class="stab">Showing</td>
														<td class="pOut">0</td>
														<td class="stab">to</td>
														<td class="pOut">0</td>
														<td class="stab">of</td>
														<td class="pOut">0</td>
														<td>&nbsp;</td>
														<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
													</tr>
												</table>
											</ezf:skip>
											<table width="500">
												<tr align="right">
													<td>
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
										</td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td>
								<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width=" 60" align="center"><!-- Seq -->
									<col width="120" align="center"><!-- Service<br>Percentage -->
									<col width="400" align="center"><!-- Billing Counter -->
									<col width="120" align="center"><!-- Billing Counter Percentage -->
									<col width="120" align="center"><!-- Start Date -->
									<col width="120" align="center"><!-- End Date -->
									<tr height="35">
										<td class="pClothBs">Seq</td>
										<td class="pClothBs">Service<br>Percentage</td>
										<td class="pClothBs">Billing Counter</td>
										<td class="pClothBs">Billing Counter<br>Percentage</td>
										<td class="pClothBs">Start Date</td>
										<td class="pClothBs">End Date</td>
									</tr>
								</table>
								<div style="width:960px; height:410px; display:block; overflow-x:none; overflow-y:scroll;">
									<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width=" 60" align="center"><!-- Seq -->
										<col width="120" align="center"><!-- Service<br>Percentage -->
										<col width="400"><!-- Billing Counter -->
										<col width="120" align="center"><!-- Billing Counter Percentage -->
										<col width="120" align="center"><!-- Start Date -->
										<col width="120" align="center"><!-- End Date -->
										<%@ page import="business.servlet.NSAL1170.NSAL1170BMsg" %>
										<%@ page import="java.math.BigDecimal" %>
										<% NSAL1170BMsg bMsg = (NSAL1170BMsg)databean.getEZDBMsg(); %>
										<% int idx=-1; %>
										<ezf:row ezfHyo="A">
										<% idx++; %>
											<tr height="35">
												<% if (bMsg.A.no(idx).xxRowNum_D.getValue().compareTo(BigDecimal.valueOf(1)) == 0) { %>
													<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" />
													<ezf:label name="xxRowNum" ezfName="xxRowNum" ezfHyo="A" ezfArrayNo="0" /></td>
												<% } else{  %>
												<td>&nbsp;</td>
												<% } %>
												<% if (bMsg.A.no(idx).xxRowNum_D.getValue().compareTo(BigDecimal.valueOf(1)) == 0) { %>
												<td>
													<ezf:inputText name="uplftBasePrcUpRatio" ezfName="uplftBasePrcUpRatio" value="10" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"8\" style=\"text-align:right\""/>
												</td>
												<% } else{  %>
												<td>&nbsp;<ezf:inputHidden name="uplftBasePrcUpRatio" ezfName="uplftBasePrcUpRatio" ezfHyo="A" ezfArrayNo="1" /></td>
												<% } %>
												<td class="stab">
													<ezf:label name="mtrLbDescTxt" ezfName="mtrLbDescTxt" ezfHyo="A" ezfArrayNo="0" />
												</td>
												<td>
													<ezf:inputText name="uplftMtrPrcUpRatio" ezfName="uplftMtrPrcUpRatio" value="10" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"8\" style=\"text-align:right\""/>
												<% if (bMsg.A.no(idx).xxRowNum_D.getValue().compareTo(BigDecimal.valueOf(1)) == 0) { %>
												<td>
													<ezf:inputText name="effFromDt" ezfName="effFromDt" value="01/01/2017" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4,'<%=idx%>');">
												</td>
												<% } else{  %>
												<td>&nbsp;<ezf:inputHidden name="effFromDt" ezfName="effFromDt" ezfHyo="A" ezfArrayNo="1" /></td>
												<% } %>
												<% if (bMsg.A.no(idx).xxRowNum_D.getValue().compareTo(BigDecimal.valueOf(1)) == 0) { %>
												<td>
													<ezf:inputText name="effThruDt" ezfName="effThruDt" value="12/31/2017" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4 ,'<%=idx%>');">
												</td>
												<% } else{  %>
												<td>&nbsp;<ezf:inputHidden name="effThruDt" ezfName="effThruDt" ezfHyo="A" ezfArrayNo="1" /></td>
												<% } %>
												<td class="pAuditInfo">
													<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												</td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="35">
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">REG COLOR</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="15" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											
											

											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													2
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="10" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">REG BW</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="10" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="01/01/2016" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="12/31/2016" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>
											<tr height="35">
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">REG COLOR</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="10" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											
											

											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													3
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="5" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">REG BW</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="10" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="01/01/2015" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="12/31/2015" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>
											<tr height="35">
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">REG COLOR</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="10" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>											


											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
											<tr height="35">
												<td>
													<input type="radio" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="2" onClick="sendServer('OnChangeRadio');">
													XXX
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td class="stab">
													<label ezfout name="othContrAbuseFlg_A" ezfname="othContrAbuseFlg_A" ezfhyo="A">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</label>
												</td>
												<td>
													<input type="text" size="9" maxlength="8" value="99.99999" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A" style="text-align:right">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);">
												</td>
												<td>
													<input type="text" size="10" maxlength="10" value="09/09/9999" name="mtrDt_A2" ezfname="mtrDt_A2" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A21', 4);">
												</td>
											</tr>											
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<table>
									<tr>
										<td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" /></td>
										<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" /></td>
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
