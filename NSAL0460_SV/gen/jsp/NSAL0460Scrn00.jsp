<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220822135920 --%>
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
			<input type="hidden" name="pageID" value="NSAL0460Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Start Read Capture">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Start Read Capture" class="pTab_ON"><a href="#">Start Rd Cptr</a></li>
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
				</ezf:skip>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
									<col width="100">
									<col width="600">
									<tr>
										<td class="stab">Reason Code</td>
										<td><ezf:select name="svcMemoRsnCd_PS" ezfName="svcMemoRsnCd_PS" ezfBlank="1" ezfCodeName="svcMemoRsnCd_PC" ezfDispName="svcMemoRsnNm_PC" /></td>
									</tr>
									<tr>
										<td class="stab">Notes</td>
										<td><ezf:textArea name="svcCmntTxt" ezfName="svcCmntTxt" otherAttr=" cols=\"80\" rows=\"4\""/></td>
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
					<table border="0" width="100%">
						<tr>
							<td>
								<table  border="0" cellpadding="0" cellspacing="0" width="1090">
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
									<col width=" 95" align="center"><!-- Contract# -->
									<col width=" 24" align="center"><!-- CheckBox -->
									<col width=" 80" align="center"><!-- Serial# -->
									<col width=" 80" align="center"><!-- Start Date -->
									<col width="220" align="center"><!-- Counter Name -->
									<col width="110" align="center"><!-- Read Date -->
									<col width="130" align="center"><!-- Read -->
									<col width="360" align="center"><!-- Comments -->
									<tr height="25">
										<td class="pClothBs">Contract#</td>
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_H" ezfName="xxChkBox_H" value="Y" onClick="sendServer('CheckAll')" /></td>
										<td class="pClothBs">Serial#</td>
										<td class="pClothBs">Start Date</td>
										<td class="pClothBs">Counter Name</td>
										<td class="pClothBs">Read Date</td>
										<td class="pClothBs">Read</td>
										<td class="pClothBs">Comments</td>
									</tr>
								</table>
								<div style="width:1057; height:410; display:block; overflow-x:none; overflow-y:scroll;">
									<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width=" 95" align="center"><!-- Contract# -->
										<col width=" 24" align="left"><!-- CheckBox -->
										<col width=" 80" align="center"><!-- Serial# -->
										<col width=" 80" align="center"><!-- Start Date -->
										<col width="220" align="center"><!-- Counter Name -->
										<col width="110" align="center"><!-- Read Date -->
										<col width="130" align="center"><!-- Read -->
										<col width="360" align="center"><!-- Comments -->
										<%@ page import="business.servlet.NSAL0460.NSAL0460BMsg" %>
										<%@ page import="java.math.BigDecimal" %>
										<% NSAL0460BMsg bMsg = (NSAL0460BMsg)databean.getEZDBMsg(); %>
										<% int idx=-1; %>
										<ezf:row ezfHyo="A">
										<% idx++; %>
											<tr height="25">
												<td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="NFL-12038992" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"12\""/></td>
											<% if (bMsg.A.no(idx).xxRowNum_D.getValue().compareTo(BigDecimal.valueOf(1)) == 0) { %>
												<td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" ezfHyo="A" ezfArrayNo="${idx}" /></td>
											<% } else { %>
												<td>&nbsp;<ezf:inputHidden name="xxChkBox" ezfName="xxChkBox" ezfHyo="A" ezfArrayNo="${idx}" /></td>
											<% } %>
												<td><ezf:inputText name="serNum" ezfName="serNum" value="MSK101010" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="contrEffFromDt" ezfName="contrEffFromDt" value="01/01/2014" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="mtrLbDescTxt" ezfName="mtrLbDescTxt" value="BW Counter" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"30\""/></td>
											<% if (bMsg.A.no(idx).xxRowNum_D.getValue().compareTo(BigDecimal.valueOf(1)) == 0) { %>
												<td><ezf:inputText name="mtrReadDt" ezfName="mtrReadDt" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrReadDt', 4, '<%=idx%>');"></td>
											<% } else { %>
												<td>&nbsp;<ezf:inputHidden name="mtrReadDt" ezfName="mtrReadDt" ezfHyo="A" ezfArrayNo="${idx}" /></td>
											<% } %>
												<td><ezf:inputText name="readMtrCnt" ezfName="readMtrCnt" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"text-align:right\" maxlength=\"10\""/> <ezf:inputButton name="OpenMeterRead" value="Read" ezfHyo="A" ezfArrayNo="${idx}" htmlClass="pBtn2" /></td>
												<td><ezf:inputText name="svcCmntTxt_A" ezfName="svcCmntTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"53\""/></td>
												<td class="pAuditInfo">
													<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" ezfHyo="A" ezfArrayNo="0" />
													<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" ezfHyo="A" ezfArrayNo="0" />
													<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" ezfHyo="A" ezfArrayNo="0" />
													<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" ezfHyo="A" ezfArrayNo="0" />
													<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" ezfHyo="A" ezfArrayNo="0" />
												</td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="25">
												<td><input type="text" class="pPro" size="12" value="" name="dsContrNum_A1" ezfname="dsContrNum_A1" ezfhyo="A"></td>
												<td>&nbsp;</td>
												<td><input type="text" class="pPro" size="10" value="" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="Total Counter" name="mtrLbNm_A1" ezfname="mtrLbNm_A1" ezfhyo="A"></td>
												<td>&nbsp;</td>
												<td><input type="text" size="8" maxlength="10" value="" name="mtrCnt_A1" ezfname="mtrCnt_A1" ezfhyo="A"> <input type="button" class="pBtn2" value="Read" name="OpenMeterRead"></td>
												<td><input type="text" class="pPro" size="53" value="" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>
											<tr height="25">
												<td><input type="text" class="pPro" size="12" value="" name="dsContrNum_A1" ezfname="dsContrNum_A1" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A#{EZF_ROW_NUMBER}" checked></td>
												<td><input type="text" class="pPro" size="10" value="MNB05459" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="01/01/2014" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="BW Counter" name="mtrLbNm_A1" ezfname="mtrLbNm_A1" ezfhyo="A"></td>
												<td><input type="text" size="10" maxlength="10" value="" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);"></td>
												<td><input type="text" size="8" maxlength="10" value="" name="mtrCnt_A1" ezfname="mtrCnt_A1" ezfhyo="A"> <input type="button" class="pBtn2" value="Read" name="OpenMeterRead"></td>
												<td><input type="text" class="pPro" size="53" value="" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>
											<tr height="25">
												<td><input type="text" class="pPro" size="12" value="" name="dsContrNum_A1" ezfname="dsContrNum_A1" ezfhyo="A"></td>
												<td>&nbsp;</td>
												<td><input type="text" class="pPro" size="10" value="" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="Total Counter" name="mtrLbNm_A1" ezfname="mtrLbNm_A1" ezfhyo="A"></td>
												<td>&nbsp;</td>
												<td><input type="text" size="8" maxlength="10" value="" name="mtrCnt_A1" ezfname="mtrCnt_A1" ezfhyo="A"> <input type="button" class="pBtn2" value="Read" name="OpenMeterRead"></td>
												<td><input type="text" class="pPro" size="53" value="" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>
											
											
											
											
											<tr height="25">
												<td><input type="text" class="pPro" size="12" value="NFL-12038993" name="dsContrNum_A1" ezfname="dsContrNum_A1" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A#{EZF_ROW_NUMBER}" checked></td>
												<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="01/01/2014" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="BW Counter" name="mtrLbNm_A1" ezfname="mtrLbNm_A1" ezfhyo="A"></td>
												<td><input type="text" size="10" maxlength="10" value="" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);"></td>
												<td><input type="text" size="8" maxlength="10" value="" name="mtrCnt_A1" ezfname="mtrCnt_A1" ezfhyo="A"> <input type="button" class="pBtn2" value="Read" name="OpenMeterRead"></td>
												<td><input type="text" class="pPro" size="53" value="" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>
											<tr height="25">
												<td><input type="text" class="pPro" size="12" value="" name="dsContrNum_A1" ezfname="dsContrNum_A1" ezfhyo="A"></td>
												<td>&nbsp;</td>
												<td><input type="text" class="pPro" size="10" value="" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="Total Counter" name="mtrLbNm_A1" ezfname="mtrLbNm_A1" ezfhyo="A"></td>
												<td>&nbsp;</td>
												<td><input type="text" size="8" maxlength="10" value="" name="mtrCnt_A1" ezfname="mtrCnt_A1" ezfhyo="A"> <input type="button" class="pBtn2" value="Read" name="OpenMeterRead"></td>
												<td><input type="text" class="pPro" size="53" value="" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>


											<tr height="25">
												<td><input type="text" class="pPro" size="12" value="FLT-5874312" name="dsContrNum_A1" ezfname="dsContrNum_A1" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A#{EZF_ROW_NUMBER}" checked></td>
												<td><input type="text" class="pPro" size="10" value="MSK101010" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="01/01/2014" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="BW Counter" name="mtrLbNm_A1" ezfname="mtrLbNm_A1" ezfhyo="A"></td>
												<td><input type="text" size="10" maxlength="10" value="" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);"></td>
												<td><input type="text" size="8" maxlength="10" value="" name="mtrCnt_A1" ezfname="mtrCnt_A1" ezfhyo="A"> <input type="button" class="pBtn2" value="Read" name="OpenMeterRead"></td>
												<td><input type="text" class="pPro" size="53" value="" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>
											<tr height="25">
												<td><input type="text" class="pPro" size="12" value="" name="dsContrNum_A1" ezfname="dsContrNum_A1" ezfhyo="A"></td>
												<td>&nbsp;</td>
												<td><input type="text" class="pPro" size="10" value="" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="Total Counter" name="mtrLbNm_A1" ezfname="mtrLbNm_A1" ezfhyo="A"></td>
												<td>&nbsp;</td>
												<td><input type="text" size="8" maxlength="10" value="" name="mtrCnt_A1" ezfname="mtrCnt_A1" ezfhyo="A"> <input type="button" class="pBtn2" value="Read" name="OpenMeterRead"></td>
												<td><input type="text" class="pPro" size="53" value="" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>
											
											
											
											
											<tr height="25">
												<td><input type="text" class="pPro" size="12" value="" name="dsContrNum_A1" ezfname="dsContrNum_A1" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A#{EZF_ROW_NUMBER}" checked></td>
												<td><input type="text" class="pPro" size="10" value="MNB05459" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="01/01/2014" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="BW Counter" name="mtrLbNm_A1" ezfname="mtrLbNm_A1" ezfhyo="A"></td>
												<td><input type="text" size="10" maxlength="10" value="" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);"></td>
												<td><input type="text" size="8" maxlength="10" value="" name="mtrCnt_A1" ezfname="mtrCnt_A1" ezfhyo="A"> <input type="button" class="pBtn2" value="Read" name="OpenMeterRead"></td>
												<td><input type="text" class="pPro" size="53" value="" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>
											<tr height="25">
												<td><input type="text" class="pPro" size="12" value="" name="dsContrNum_A1" ezfname="dsContrNum_A1" ezfhyo="A"></td>
												<td>&nbsp;</td>
												<td><input type="text" class="pPro" size="10" value="" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="Total Counter" name="mtrLbNm_A1" ezfname="mtrLbNm_A1" ezfhyo="A"></td>
												<td>&nbsp;</td>
												<td><input type="text" size="8" maxlength="10" value="" name="mtrCnt_A1" ezfname="mtrCnt_A1" ezfhyo="A"> <input type="button" class="pBtn2" value="Read" name="UpdateReadMethod"></td>
												<td><input type="text" class="pPro" size="53" value="" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>
											
											
											
											
											<tr height="25">
												<td><input type="text" class="pPro" size="12" value="" name="dsContrNum_A1" ezfname="dsContrNum_A1" ezfhyo="A"></td>
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A#{EZF_ROW_NUMBER}" checked></td>
												<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="01/01/2014" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="BW Counter" name="mtrLbNm_A1" ezfname="mtrLbNm_A1" ezfhyo="A"></td>
												<td><input type="text" size="10" maxlength="10" value="" name="mtrDt_A1" ezfname="mtrDt_A1" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrDt_A1', 4);"></td>
												<td><input type="text" size="8" maxlength="10" value="" name="mtrCnt_A1" ezfname="mtrCnt_A1" ezfhyo="A"> <input type="button" class="pBtn2" value="Read" name="OpenMeterRead"></td>
												<td><input type="text" class="pPro" size="53" value="" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>
											<tr height="25">
												<td><input type="text" class="pPro" size="12" value="" name="dsContrNum_A1" ezfname="dsContrNum_A1" ezfhyo="A"></td>
												<td>&nbsp;</td>
												<td><input type="text" class="pPro" size="10" value="" name="serNum_A1" ezfname="serNum_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="10" value="" name="contrVrsnEffFromDt_A1" ezfname="contrVrsnEffFromDt_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="30" value="Total Counter" name="mtrLbNm_A1" ezfname="mtrLbNm_A1" ezfhyo="A"></td>
												<td>&nbsp;</td>
												<td><input type="text" size="8" maxlength="10" value="" name="mtrCnt_A1" ezfname="mtrCnt_A1" ezfhyo="A"> <input type="button" class="pBtn2" value="Read" name="OpenMeterRead"></td>
												<td><input type="text" class="pPro" size="53" value="" name="msgTxt_A1" ezfname="msgTxt_A1" ezfhyo="A"></td>
											</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>

					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
