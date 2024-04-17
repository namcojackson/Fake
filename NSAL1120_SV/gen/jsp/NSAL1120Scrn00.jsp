<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220822101309 --%>
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
			<input type="hidden" name="pageID" value="NSAL1120Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Meter and Pricing Details">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
			<ezf:skip>
			<div class="pTab_HEAD">
				<ul>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="96%">
								<div>
									<li title="Meter and Pricing Details" class="pTab_ON"><a href="#">Mtr Prc Dtl</a></li>
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
<!-- ################################################## HEADER - [START] ################################################## -->
				<table border="0" width="100%">
					<tr>
						<td>
							<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
							<colgroup>
								<col width="80px"><!-- Label -->
								<col width="100px">
								<col width="80px"><!-- Label -->
								<col width="160px">
								<col width="80px"><!-- Label -->
								<col width="250px">
							</colgroup>
							<tbody>
								<tr>
								<td class="stab" align="center">CI Number</td>
								<td class="stab" align="left"><ezf:inputText name="custIncdtId" ezfName="custIncdtId" otherAttr=" id=\"custIncdtId\" size=\"12\" ezftoupper=\"\""/>
								<td class="stab" align="center">Status</td>
								<td><ezf:inputText name="svcCrRebilStsDescTxt" ezfName="svcCrRebilStsDescTxt" value="Pending Approval" otherAttr=" size=\"16\" ezftoupper=\"\""/></td>
								<td class="stab" align="center">Description</td>
								<td><ezf:inputText name="svcCrRebilDescTxt" ezfName="svcCrRebilDescTxt" value="Billing - Incorrect Price" otherAttr=" size=\"25\" ezftoupper=\"\""/></td>
								</tr>
							</tbody>
							</table>
						</td>
					</tr>
				</table>
<!-- ################################################## HEADER - [END  ] ################################################## -->
<!-- ################################################## List1(Base Price Details) - [START] ################################################### -->
<%@ page import="business.servlet.NSAL1120.NSAL1120BMsg" %>
<%@ page import="business.servlet.NSAL1120.NSAL1120_CBMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;" %>
<%  NSAL1120BMsg bMsg = (NSAL1120BMsg)databean.getEZDBMsg(); %>
				<% if (!"1".equals(bMsg.xxModeCd.getValue())) out.println("<div style='display:none'>"); %>
				<table border="0" width="100%">
					<col width="100%">
						<tr>
							<td valign="top">
								<fieldset style="height:540;">
									<table border="0" cellpadding="1" cellspacing="0" width="100%">
									<col width="210px">
									<col>
										<tr>
											<td>
												<table border="0" cellpadding="0" cellspacing="0">
													<col width="10px">
													<col width="200px">	<!-- Label -->
													<tr>
														<td>&nbsp;</td>
														<td class="stab"><font size="2">Base Price Details</font></td>
													</tr>
												</table>
											</td>
											<td align="right">
												<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
												<table border="0" cellpadding="0" width="100%">
													<tr align="right">
														<td>
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
																	<col width="17">
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
																		<td></td>
																	</tr>
																</table>
															</ezf:skip>
															<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="A" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
															</jsp:include>
														</td>
													</tr>
										 		</table>
									 			<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div id="topTBL_A" style="overflow-x:hidden; overflow-y:none;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed" width="960px">
														<colgroup>
														<col align="middle" width="110px">	<!-- Machine Master -->
														<col align="middle" width="110px">	<!-- Item Code -->
														<col align="middle" width="110px">	<!-- Serial Number -->
														<col align="middle" width="110px">	<!-- Service Program -->
														<col align="middle" width="90px">	<!-- From/Billing Date -->
														<col align="middle" width="90px">	<!-- Thru/Billing Date -->
														<col align="middle" width="90px">	<!-- Old/Base Price -->
														<col align="middle" width="90px">	<!-- New/Base Price -->
														<col align="middle" width="90px">	<!-- Old/Base Price/Period -->
														<col align="middle" width="90px">	<!-- New/Base Price/Period -->
														</colgroup>
														<tbody>
															<tr>
																<td class="pClothBs" rowspan="2">Machine Master</td>
																<td class="pClothBs" rowspan="2">Item Code</td>
																<td class="pClothBs" rowspan="2">Serial Number</td>
																<td class="pClothBs" rowspan="2">Service Program</td>
																<td class="pClothBs" colspan="2">Billing Date</td>
																<td class="pClothBs" colspan="2">Base Price</td>
																<td class="pClothBs" colspan="2">Price/Period</td>
															</tr>
															<tr>
																<td class="pClothBs">From</td>
																<td class="pClothBs">Thru</td>
																<td class="pClothBs">Old</td>
																<td class="pClothBs">New</td>
																<td class="pClothBs">Old</td>
																<td class="pClothBs">New</td>
															</tr>
														</tbody>
													</table>
												</div>
												<div id="bottomTBL_A" style="overflow-x:hidden; overflow-y:scroll; width:1000px; height:440px;" onScroll="synchroScrollLeft(this.id, new Array('topTBL_A'));">
													<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;word-break:break-all" width="960px">
														<colgroup>
														<col align="middle" width="110px">	<!-- Machine Master -->
														<col align="middle" width="110px">	<!-- Item Code -->
														<col align="middle" width="110px">	<!-- Serial Number -->
														<col align="middle" width="110px">	<!-- Service Program -->
														<col align="middle" width="90px">	<!-- From/Billing Date -->
														<col align="middle" width="90px">	<!-- Thru/Billing Date -->
														<col align="right" width="90px">	<!-- Old/Base Price -->
														<col align="middle" width="90px">	<!-- New/Base Price -->
														<col align="right" width="90px">	<!-- Old/Base Price/Period -->
														<col align="middle" width="90px">	<!-- New/Base Price/Period -->
														</colgroup>
														<tbody>
														<ezf:row ezfHyo="A">
															<tr>
																<td><ezf:label name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td>
																	<ezf:inputText name="xxScrItem40Txt_A" ezfName="xxScrItem40Txt_A" value="QLL01658" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem40Txt_A\" size=\"13\" ezftoupper=\"\""/>
																</td>
																<td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="LEASE TONER EX/DRUM EX/CONSUMABLE IN" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"250\" style=\"border:none; background-color:transparent;\""/></td>
																<td><ezf:label name="baseBllgFromDt_A" ezfName="baseBllgFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="baseBllgThruDt_A" ezfName="baseBllgThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="oldBaseDealAmt_A" ezfName="oldBaseDealAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td>
																	<ezf:inputText name="newBaseDealAmt_A" ezfName="newBaseDealAmt_A" value="1,301.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right;\" id=\"newBaseDealAmt_BA\" size=\"10\""/>
																</td>
																<td><ezf:label name="oldBaseUnitAmt_A" ezfName="oldBaseUnitAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td>
																	<ezf:inputText name="newBaseUnitAmt_A" ezfName="newBaseUnitAmt_A" value="1,301.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right;\" id=\"newBaseDealAmt_BA\" size=\"10\""/>
																</td>
                                                                <td class="pAuditInfo">
                                                                    <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
                                                                    <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
                                                                    <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
                                                                    <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
                                                                    <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
                                                                </td>
															</tr>
															<ezf:skip>
															<tr>
																<td><label ezfout name="svcMachMstrPk_BA" ezfhyo="A">43560</label></td>
																<td><label ezfout name="mdseCd_BA" ezfhyo="A">5780B002AA</label></td>
																<td>
																	<input id="xxScrItem40Txt_A" type="text" size="13" class="pPro" value="QLL01658" name="xxScrItem40Txt_A" ezfhyo="A" ezftoupper>
																</td>
																<td><input type="text" class="pPro" readonly size="13" maxlength="250" value="LEASE TONER EX/DRUM EX/CONSUMABLE IN" name="mdseDescShortTxt_BA" ezfhyo="A" ezfname="mdseDescShortTxt_BA" style="border:none; background-color:transparent;"></td>
																<td><label ezfout name="baseBllgFromDt_BA" ezfhyo="A">01/01/2016</label></td>
																<td><label ezfout name="baseBllgThruDt_BA" ezfhyo="A">12/31/2016</label></td>
																<td><label ezfout name="oldBaseDealAmt_BA" ezfhyo="A">3000.00</label></td>
																<td>
																	<input style="text-align:right;" id="newBaseDealAmt_BA" type="text" size="10" class="pPro" value="1,401.00" name="newBaseDealAmt_BA" ezfhyo="A">
																</td>
																<td><label ezfout name="oldBaseDealAmt_BA" ezfhyo="A">1,234.56</label></td>
																<td>
																	<input style="text-align:right;" id="newBaseDealAmt_BA" type="text" size="10" class="pPro" value="1,234.78" name="newBaseDealAmt_BA" ezfhyo="A">
																</td>
															</tr>
															</ezf:skip>
														</ezf:row>
														</tbody>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
				</table><!-- Table_A END -->
				<% if (!"1".equals(bMsg.xxModeCd.getValue())) out.println("</div>"); %>
<!-- ################################################## List1 - [END  ] ################################################## -->
<!-- ################################################## List2(Meter Details) - [START] ################################################## -->
				<% if (!"2".equals(bMsg.xxModeCd.getValue())) out.println("<div style='display:none'>"); %>
				<table border="0" width="100%">
				<col width="100%">
					<tr>
						<td valign="top">
							<fieldset style="height:250;">
								<table border="0" cellpadding="1" cellspacing="0" width="100%">
								<col width="210px">
								<col >
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="10px">
												<col width="200px">	<!-- Label -->
												<tr>
													<td>&nbsp;</td>
													<td class="stab"><font size="2">Meter Details</font></td>
												</tr>
											</table>
										</td>
										<td align="right">
											<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
											<table border="0" cellpadding="0" width="100%">
												<tr align="right">
													<td>
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
																<col width="17">
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
																	<td></td>
																</tr>
															</table>
														</ezf:skip>
														<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="B" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum_B" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum_B" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum_B" />
														</jsp:include>
													</td>
												</tr>
									 		</table>
									 		<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div id="topTBL_B" style="overflow-x:hidden; overflow-y:none;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed" width="1095px">
													<colgroup>
													<col align="middle" width="25px">	<!-- (checkbox) -->
													<col align="middle" width="85px">	<!-- Serial Number -->
													<col align="middle" width="50px">	<!-- Start/Read Date -->
													<col align="middle" width="50px">	<!-- End/Read Date -->
													<col align="middle" width="95px">	<!-- Counter -->
													<col align="middle" width="70px">	<!-- Start Read/Old -->
													<col align="middle" width="70px">	<!-- End Read/Old -->
													<col align="middle" width="60px">	<!-- Test Copies/Old -->
													<col align="middle" width="70px">	<!-- Start Read/New -->
													<col align="middle" width="70px">	<!-- End Read/New -->
													<col align="middle" width="60px">	<!-- Test Copies/New -->
													</colgroup>
													<tbody>
														<tr>
															<td class="pClothBs" rowspan="2">&nbsp;</td>
															<td class="pClothBs" rowspan="2">Serial Number</td>
															<td class="pClothBs" colspan="2">Read Date</td>
															<td class="pClothBs" rowspan="2">Counter</td>
															<td class="pClothBs" colspan="3">Old</td>
															<td class="pClothBs" colspan="3">New</td>
														</tr>
														<tr>
															<td class="pClothBs">Start</td>
															<td class="pClothBs">End</td>
															<td class="pClothBs">Start Read</td>
															<td class="pClothBs">End Read</td>
															<td class="pClothBs">Test Copies</td>
															<td class="pClothBs">Start Read</td>
															<td class="pClothBs">End Read</td>
															<td class="pClothBs">Test Copies</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div id="bottomTBL_B" style="overflow-x:hidden; overflow-y:scroll; width:1115px; height:180px;" onScroll="synchroScrollLeft(this.id, new Array('topTBL_B'));">
												<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout:fixed;word-break:break-all" width="1095px">
													<colgroup>
													<col align="middle" width="25px">	<!-- (checkbox) -->
													<col align="middle" width="85px">	<!-- Serial Number -->
													<col align="middle" width="50px">	<!-- Start/Read Date -->
													<col align="middle" width="50px">	<!-- End/Read Date -->
													<col align="middle" width="95px">	<!-- Counter -->
													<col align="right" width="70px">	<!-- Start Read/Old -->
													<col align="right" width="70px">	<!-- End Read/Old -->
													<col align="right" width="60px">	<!-- Test Copies/Old -->
													<col align="middle" width="70px">	<!-- Start Read/New -->
													<col align="middle" width="70px">	<!-- End Read/New -->
													<col align="middle" width="60px">	<!-- Test Copies/New -->
													</colgroup>
													<tbody>
													<ezf:row ezfHyo="B">
														<tr>
															<td>
																<ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" />
															</td>
															<td align="center">
																<ezf:inputText name="serNum_B" ezfName="serNum_B" value="QLL01658" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"serNum_B\" size=\"9\" ezftoupper=\"\""/>
																<ezf:inputButton name="OpenWin_SerNumSrch" value="S" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" />
															</td>
															<td><ezf:label name="startMtrReadDt_B" ezfName="startMtrReadDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:label name="endMtrReadDt_B" ezfName="endMtrReadDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
															<td align="center">
																<ezf:inputText name="mtrLbDescTxt_B" ezfName="mtrLbDescTxt_B" value="Aggregate Black and White Small (Billing)" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"mtrLbDescTxt_B\" size=\"10\""/>
																<ezf:inputButton name="OpenWin_Counter" value="S" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" />
															</td>
															<td><ezf:label name="oldStartReadMtrCnt_B" ezfName="oldStartReadMtrCnt_B" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:label name="oldEndReadMtrCnt_B" ezfName="oldEndReadMtrCnt_B" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:label name="oldTestMtrCnt_B" ezfName="oldTestMtrCnt_B" ezfHyo="B" ezfArrayNo="0" /></td>
															<td>
																<ezf:inputText name="newStartReadMtrCnt_B" ezfName="newStartReadMtrCnt_B" value="3,000" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align:right;\" id=\"newStartReadMtrCnt_MB\" size=\"13\""/>
															</td>
															<td>
																<ezf:inputText name="newEndReadMtrCnt_B" ezfName="newEndReadMtrCnt_B" value="578,912" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align:right;\" id=\"newEndReadMtrCnt_MB\" size=\"13\""/>
															</td>
															<td>
																<ezf:inputText name="newTestMtrCnt_B" ezfName="newTestMtrCnt_B" value="300" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align:right;\" id=\"newTestMtrCnt_MB\" size=\"10\""/>
															</td>
                                                            <td class="pAuditInfo">
                                                                <ezf:inputHidden name="xxRecHistCratTs_B" ezfName="xxRecHistCratTs_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_B\""/>
                                                                <ezf:inputHidden name="xxRecHistCratByNm_B" ezfName="xxRecHistCratByNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_B\""/>
                                                                <ezf:inputHidden name="xxRecHistUpdTs_B" ezfName="xxRecHistUpdTs_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_B\""/>
                                                                <ezf:inputHidden name="xxRecHistUpdByNm_B" ezfName="xxRecHistUpdByNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_B\""/>
                                                                <ezf:inputHidden name="xxRecHistTblNm_B" ezfName="xxRecHistTblNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_B\""/>
                                                            </td>
														</tr>
														<ezf:skip>
														<tr>
															<td>
																<input type="checkbox" class="pPro" value="Y" name="xxChkBox_MB" ezfhyo="B">
															</td>
															<td align="center">
																<input id="serNum_MB" type="text" size="9" class="pPro" value="QLL01658" name="serNum_MB" ezfhyo="B" ezftoupper>
																<input type="button" class="pBtn0" value="S" name="OpenWin_SerNumSrch,'{EZF_ROW_NUMBER}')" ezfhyo="B">
															</td>
															<td><label ezfout name="startMtrReadDt_MB" ezfhyo="B">01/01/2016</label></td>
															<td><label ezfout name="endMtrReadDt_MB" ezfhyo="B">12/31/2016</label></td>
															<td align="center">
																<input id="mtrLbDescTxt_MB" type="text" size="10" class="pPro" value="Aggregate Black and White Small (Billing)" name="mtrLbDescTxt_MB" ezfhyo="B">
																<input type="button" class="pBtn0" value="S" name="OpenWin_Counter,'{EZF_ROW_NUMBER}')" ezfhyo="B">
															</td>
															<td><label ezfout name="oldStartReadMtrCnt_MB" ezfhyo="B">2000</label></td>
															<td><label ezfout name="oldEndReadMtrCnt_MB" ezfhyo="B">600000</label></td>
															<td><label ezfout name="oldTestMtrCnt_MB" ezfhyo="B">100</label></td>
															<td>
																<input style="text-align:right;" id="newStartReadMtrCnt_MB" type="text" size="13" class="pPro" value="3,000" name="newStartReadMtrCnt_MB" ezfhyo="B">
															</td>
															<td>
																<input style="text-align:right;" id="newEndReadMtrCnt_MB" type="text" size="13" class="pPro" value="578,912" name="newEndReadMtrCnt_MB" ezfhyo="B">
															</td>
															<td>
																<input style="text-align:right;" id="newTestMtrCnt_MB" type="text" size="10" class="pPro" value="300" name="newTestMtrCnt_MB" ezfhyo="B">
															</td>
														</tr>
														</ezf:skip>
													</ezf:row>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
									<tr>
										<td><ezf:inputButton name="Review" value="Review" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
				</table><!-- Table_B END -->
<!-- ################################################## List2 - [END  ] ################################################## -->
<!-- ################################################## List3(Pricing Details) - [START] ################################################## -->
				<table border="0" width="100%">
				<col width="100%">
					<tr>
						<td valign="top">
							<fieldset style="height:250;">
								<!-- <legend>Search Enforcement Actions</legend> -->
								<table border="0" cellpadding="1" cellspacing="0" width="100%">
								<col width="210px">
								<col >
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="10px">
												<col width="100px">	<!-- Label -->
												<tr>
													<td>&nbsp;</td>
													<td class="stab"><font size="2">Pricing Details</font></td>
												</tr>
											</table>
										</td>
										<td align="right">
											<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
											<table border="0" cellpadding="0" width="100%">
												<tr align="right">
													<td>
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
																<col width="17">
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
																	<td></td>
																</tr>
															</table>
														</ezf:skip>
														<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="C" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum_C" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum_C" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum_C" />
														</jsp:include>
													</td>
												</tr>
											</table>
											<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div id="topTBL_C" style="overflow-x:hidden; overflow-y:none;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed" width="1060px">
													<colgroup>
													<col align="middle" width="100px">	<!-- Serial Number -->
													<col align="middle" width="90px">	<!-- Start/Line -->
													<col align="middle" width="90px">	<!-- End/Line -->
													<col align="middle" width="100px">	<!-- Counter -->
													<col align="middle" width="80px">	<!-- Total Copies -->
													<col align="middle" width="80px">	<!-- Overage Amount -->
													<col align="middle" width="40px">	<!-- Tier -->
													<col align="middle" width="80px">	<!-- Copy Vol/Old -->
													<col align="middle" width="80px">	<!-- Rate/Old -->
													<col align="middle" width="80px">	<!-- Copy Vol/New -->
													<col align="middle" width="80px">	<!-- Rate/New -->
													<col align="middle" width="80px">	<!-- Copy Vol/Period/Old -->
													<col align="middle" width="80px">	<!-- Copy Vol/Period/New -->
													<col>
													</colgroup>
													<tbody>
														<tr>
															<td class="pClothBs" rowspan="2">Serial Number</td>
															<td class="pClothBs" colspan="2">Line</td>
															<td class="pClothBs" rowspan="2">Counter</td>
															<td class="pClothBs" rowspan="2">Total Copies</td>
															<td class="pClothBs" rowspan="2">Old Overage Amount</td>
															<td class="pClothBs" rowspan="2">Tier</td>
															<td class="pClothBs" colspan="2">Old</td>
															<td class="pClothBs" colspan="2">New</td>
															<td class="pClothBs" colspan="2">Copy Vol/Period</td>
														</tr>
														<tr>
															<td class="pClothBs">Start</td>
															<td class="pClothBs">End</td>
															<td class="pClothBs">Copy Vol</td>
															<td class="pClothBs">Rate</td>
															<td class="pClothBs">Copy Vol</td>
															<td class="pClothBs">Rate</td>
															<td class="pClothBs">Old</td>
															<td class="pClothBs">New</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div id="bottomTBL_C" style="overflow-x:hidden; overflow-y:scroll; width:1080px; height:180px;" onScroll="synchroScrollLeft(this.id, new Array('topTBL_C'));">
												<table border="1" cellpadding="1" cellspacing="0" id="C" style="table-layout:fixed;word-break:break-all" width="1060px">
													<colgroup>
													<col align="middle" width="100px">	<!-- Serial Number -->
													<col align="middle" width="90px">	<!-- Start/Line -->
													<col align="middle" width="90px">	<!-- End/Line -->
													<col align="middle" width="100px">	<!-- Counter -->
													<col align="middle" width="80px">	<!-- Total Copies -->
													<col align="middle" width="80px">	<!-- Overage Amount -->
													<col align="middle" width="40px">	<!-- Tier -->
													<col align="middle" width="80px">	<!-- Copy Vol/Old -->
													<col align="middle" width="80px">	<!-- Rate/Old -->
													<col align="middle" width="80px">	<!-- Copy Vol/New -->
													<col align="middle" width="80px">	<!-- Rate/New -->
													<col align="middle" width="80px">	<!-- Copy Vol/Period/Old -->
													<col align="middle" width="80px">	<!-- Copy Vol/Period/New -->
													<col>
													</colgroup>
													<tbody>
													<% int i = 0; %>
													<ezf:row ezfHyo="C">
														<% NSAL1120_CBMsg cbMsg = bMsg.C.no(i++); %>
														<% boolean isHidden1 = ZYPCommonFunc.hasValue(cbMsg.xxDplyCtrlFlg_C1); %>
														<% boolean isHidden2 = ZYPCommonFunc.hasValue(cbMsg.xxDplyCtrlFlg_C2); %>
														<tr>
															<td>
																<% if (isHidden1) out.println("<div style='visibility:hidden'>"); %>
																<ezf:inputText name="xxScrItem40Txt_C" ezfName="xxScrItem40Txt_C" value="QLL01658" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"serNum_PC\" size=\"12\" ezftoupper=\"\""/>
																<% if (isHidden1) out.println("</div>"); %>
															</td>
															<td align="center">
																<% if (isHidden1) out.println("<div style='visibility:hidden'>"); %>
																<ezf:label name="mtrBllgFromDt_C" ezfName="mtrBllgFromDt_C" ezfHyo="C" ezfArrayNo="0" />
																<% if (isHidden1) out.println("</div>"); %>
															</td>
															<td align="center">
																<% if (isHidden1) out.println("<div style='visibility:hidden'>"); %>
																<ezf:label name="mtrBllgThruDt_C" ezfName="mtrBllgThruDt_C" ezfHyo="C" ezfArrayNo="0" />
																<% if (isHidden1) out.println("</div>"); %>
															</td>
															<td align="center">
																<% if (isHidden2) out.println("<div style='visibility:hidden'>"); %>
																<ezf:inputText name="mtrLbDescTxt_C" ezfName="mtrLbDescTxt_C" value="Aggregate Black and White Small (Billing)" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"mtrLbDescTxt_MB\" size=\"12\""/>
																<% if (isHidden2) out.println("</div>"); %>
															</td>
															<td align="right">
																<% if (isHidden2) out.println("<div style='visibility:hidden'>"); %>
																<ezf:label name="newMtrCnt_C" ezfName="newMtrCnt_C" ezfHyo="C" ezfArrayNo="0" />
																<% if (isHidden2) out.println("</div>"); %>
															</td>
															<td align="right">
																<% if (isHidden2) out.println("<div style='visibility:hidden'>"); %>
																<ezf:label name="oldMtrChrgDealAmt_C" ezfName="oldMtrChrgDealAmt_C" ezfHyo="C" ezfArrayNo="0" />
																<% if (isHidden2) out.println("</div>"); %>
															</td>
															<td align="left"><ezf:label name="xxListNum_C" ezfName="xxListNum_C" ezfHyo="C" ezfArrayNo="0" /></td>
															<td align="right"><ezf:label name="oldXsCopyQty_C" ezfName="oldXsCopyQty_C" ezfHyo="C" ezfArrayNo="0" /></td>
															<td align="right"><ezf:label name="oldXsMtrAmtRate_C" ezfName="oldXsMtrAmtRate_C" ezfHyo="C" ezfArrayNo="0" /></td>
															<td>
																<ezf:inputText name="newXsCopyQty_C" ezfName="newXsCopyQty_C" value="1500" ezfHyo="C" ezfArrayNo="0" otherAttr=" style=\"text-align:right;\" id=\"newXsCopyQty_PC\" size=\"10\" ezftoupper=\"\""/>
															</td>
															<td>
																<ezf:inputText name="newXsMtrAmtRate_C" ezfName="newXsMtrAmtRate_C" value="0.15" ezfHyo="C" ezfArrayNo="0" otherAttr=" style=\"text-align:right;\" id=\"newXsMtrAmtRate_PC\" size=\"10\" ezftoupper=\"\""/>
															</td>
															<td align="right"><ezf:label name="oldUnitXsCopyQty_C" ezfName="oldUnitXsCopyQty_C" ezfHyo="C" ezfArrayNo="0" /></td>
															<td>
																<ezf:inputText name="newUnitXsCopyQty_C" ezfName="newUnitXsCopyQty_C" value="1200" ezfHyo="C" ezfArrayNo="0" otherAttr=" style=\"text-align:right;\" id=\"newXsMtrAmtRate_PC\" size=\"10\" ezftoupper=\"\""/>
															</td>
                                                            <td class="pAuditInfo">
                                                                <ezf:inputHidden name="xxRecHistCratTs_C" ezfName="xxRecHistCratTs_C" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_C\""/>
                                                                <ezf:inputHidden name="xxRecHistCratByNm_C" ezfName="xxRecHistCratByNm_C" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_C\""/>
                                                                <ezf:inputHidden name="xxRecHistUpdTs_C" ezfName="xxRecHistUpdTs_C" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_C\""/>
                                                                <ezf:inputHidden name="xxRecHistUpdByNm_C" ezfName="xxRecHistUpdByNm_C" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_C\""/>
                                                                <ezf:inputHidden name="xxRecHistTblNm_C" ezfName="xxRecHistTblNm_C" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_C\""/>
                                                            </td>
														</tr>
														<ezf:skip>
														</ezf:skip>
													</ezf:row>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
				</table><!-- Table_C END -->
				<% if (!"2".equals(bMsg.xxModeCd.getValue())) out.println("</div>"); %>

<!-- ################################################## List3 - [END  ] ################################################## -->
			</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
