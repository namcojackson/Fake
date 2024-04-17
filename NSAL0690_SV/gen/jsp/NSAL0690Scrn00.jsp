<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180531103437 --%>
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
			<input type="hidden" name="pageID" value="NSAL0690Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Renew Contract or Machine on Contract">
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
										<li title="Renew Contract or Machine on Contract" class="pTab_ON"><a href="#">Renew</a></li>
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
									<col width=" 75">
									<col width="180">
									<col width="420">
									<col width="100">
									<tr>
										<td colspan="4">
											<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0">
												<col width=" 75">
												<col width="150">
												<col width="50">
												<col width="100">
												<tr>
													<td class="stab">Duration</td>
													<td><ezf:inputText name="xxNum_H1" ezfName="xxNum_H1" value="3" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
													<td class="stab">Period</td>
													<td><ezf:select name="bllgCycleUomCd_H3" ezfName="bllgCycleUomCd_H3" ezfBlank="1" ezfCodeName="bllgCycleUomCd_H1" ezfDispName="bllgCycleUomDescTxt_H2" /></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td class="stab">Reason Code</td>
										<td colspan="3"><ezf:select name="svcMemoRsnCd_H3" ezfName="svcMemoRsnCd_H3" ezfBlank="1" ezfCodeName="svcMemoRsnCd_H1" ezfDispName="svcMemoRsnNm_H2" /></td>
									</tr>
									<tr>
										<td class="stab">Notes</td>
										<td colspan="2"><ezf:textArea name="svcCmntTxt_H1" ezfName="svcCmntTxt_H1" otherAttr=" cols=\"80\" rows=\"4\""/></td>
										<td><ezf:inputButton name="ApplyToAll" value="Apply To All" htmlClass="pBtn8" /></td>
									</tr>
								</table>
							</td>
						</tr>

					</table>
<%-- #################################################### DETAIL ################################################### --%>
					<table border="0" width="100%">
						<tr>
							<td>
								<div id="tableTop" style="overflow-y:scroll; height:440; overflow-x:hidden; width:100%; float:left;">
								<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
								<%@ page import="business.servlet.NSAL0690.NSAL0690BMsg" %>
								<%@ page import="java.math.BigDecimal" %>
								<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG" %>
								<% NSAL0690BMsg bMsg = (NSAL0690BMsg)databean.getEZDBMsg(); %>
								<% BigDecimal dsContrPkA = null; %>
								<% String dsContrCatgCdA = null; %>
								
								<% int indexA = -1; %>
								<ezf:row ezfHyo="A">
								<% indexA++; %>
								<% dsContrPkA = bMsg.A.no( indexA ).dsContrPk_A1.getValue(); %>
								<% dsContrCatgCdA = bMsg.A.no( indexA ).dsContrCatgCd_A1.getValue(); %>
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="100%">
										<col width="50%">
										<col width="50%">
										<tr>
											<td class="pClothBs" align="center">Contract Details</td>
											<td class="pClothBs" align="center">Renewal Details</td>
										</tr>
										<tr>
											<td>
												<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td>
															<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																<col width="90">
																<col width="150">
																<tr height="20">
																	<td class="stab">Contract#</td>
																	<td><ezf:inputText name="xxScrItem34Txt_A1" ezfName="xxScrItem34Txt_A1" value="NFL-12038992" ezfHyo="A" ezfArrayNo="<%=indexA%>" otherAttr=" size=\"20\""/></td>
																</tr>
																<tr height="20">
																	<td class="stab">Start Date</td>
																	<td><ezf:inputText name="contrVrsnEffFromDt_A1" ezfName="contrVrsnEffFromDt_A1" value="01/01/2014" ezfHyo="A" ezfArrayNo="<%=indexA%>" otherAttr=" size=\"10\""/></td>
																</tr>
																<tr height="20">
																	<td class="stab">End Date</td>
																	<td><ezf:inputText name="contrVrsnEffThruDt_A1" ezfName="contrVrsnEffThruDt_A1" value="12/31/2014" ezfHyo="A" ezfArrayNo="<%=indexA%>" otherAttr=" size=\"10\""/></td>
																</tr>
																<tr height="20">
																	<td class="stab">Customer Name</td>
																	<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="CHICAGO BULLS" ezfHyo="A" ezfArrayNo="<%=indexA%>" otherAttr=" size=\"20\""/></td>
																</tr>
															</table>
														</td>
														<td>
															<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																<col width="60">
																<col width="150">
																<tr height="20">
																	<td class="stab">&nbsp;</td>
																	<td>&nbsp;</td>
																</tr>
																<tr height="20">
																	<td class="stab">&nbsp;</td>
																	<td>&nbsp;</td>
																</tr>
																<tr height="20">
																	<td class="stab">&nbsp;</td>
																	<td>&nbsp;</td>
																</tr>
																<tr height="20">
																	<td class="stab">&nbsp;</td>
																	<td>&nbsp;</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
											
											<td>
												<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
													<col width="50%">
													<col width="50%">
													<tr>
														<td>
															<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																<col width="90">
																<col width="150">
																<tr height="20">
																	<td class="stab">Duration</td>
																	<td><ezf:inputText name="xxNum_A1" ezfName="xxNum_A1" value="3" ezfHyo="A" ezfArrayNo="<%=indexA%>" otherEvent1=" onchange=\"sendServer('OnChangeDurationPeriod','{EZF_ROW_NUMBER}');\"" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
																</tr>
																<tr height="20">
																	<td class="stab">Amount</td>
																	<td><ezf:inputText name="basePrcDealAmt_A1" ezfName="basePrcDealAmt_A1" value="600.00" ezfHyo="A" ezfArrayNo="<%=indexA%>" otherAttr=" size=\"20\""/></td>
																</tr>
																<tr height="20">
																	<td class="stab">New Amount</td>
																	<td><ezf:inputText name="newBaseDealAmt_A1" ezfName="newBaseDealAmt_A1" value="660.00" ezfHyo="A" ezfArrayNo="<%=indexA%>" otherAttr=" size=\"20\""/></td>
																</tr>
																<tr height="20">
																	<td class="stab">&nbsp;</td>
																	<td>&nbsp;</td>
																</tr>
															</table>
														</td>
														<td>
															<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																<col width="60">
																<col width="150">
																<tr height="20">
																	<td class="stab">Period</td>
																	<td><ezf:select name="bllgCycleUomCd_A3" ezfName="bllgCycleUomCd_A3" ezfHyo="A" ezfArrayNo="<%=indexA%>" ezfBlank="1" ezfCodeName="bllgCycleUomCd_H1" ezfDispName="bllgCycleUomDescTxt_H2" otherEvent1=" onchange=\"sendServer('OnChangeDurationPeriod','{EZF_ROW_NUMBER}');\"" /></td>
																</tr>
																<tr height="20">
																	<td>&nbsp;</td>
																	<% if( !DS_CONTR_CATG.REGULAR.equals( dsContrCatgCdA )) { %>
																		<td><ezf:inputButton name="OpenWin_OveragePricing" value="Overage Pricing" ezfHyo="A" ezfArrayNo="<%=indexA%>" htmlClass="pBtn12" /></td>
																	<% } else { %>
																		<td>&nbsp;</td>
																	<% } %>
																</tr>
																<tr height="20">
																	<td class="stab">&nbsp;</td>
																	<td>&nbsp;</td>
																</tr>
																<tr height="20">
																	<td class="stab">&nbsp;</td>
																	<td>&nbsp;</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
													<col width=" 24" align="center"><!-- CheckBox -->
													<col width=" 80" align="center"><!-- Product# -->
													<col width=" 80" align="center"><!-- Serial# -->
													<col width="110" align="center"><!-- Model -->
													<col width=" 65" align="center"><!-- Type -->
													<col width=" 80" align="center"><!-- Status -->
													<col width=" 80" align="center"><!-- Start Date -->
													<col width=" 45" align="center"><!-- % -->
													<col width=" 80" align="center"><!-- End Date -->
													<col width=" 100" align="center"><!-- New Period End -->
													<col width=" 90" align="center"><!-- Amount -->
													<col width=" 90" align="center"><!-- New Amount -->
													<col width="160" align="center"><!-- Return Message -->
													<tr height=" 35">
														<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_AL" ezfName="xxChkBox_AL" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('SelectAll')" /></td>
														<td class="pClothBs">Product#</td>
														<td class="pClothBs">Serial#</td>
														<td class="pClothBs">Model</td>
														<td class="pClothBs">Type</td>
														<td class="pClothBs">Status</td>
														<td class="pClothBs">Start Date</td>
														<td class="pClothBs">%</td>
														<td class="pClothBs">End Date</td>
														<td class="pClothBs">New Period<br />End</td>
														<td class="pClothBs">Amount</td>
														<td class="pClothBs">New<br />Amount</td>
														<td class="pClothBs">Return Message</td>
													</tr>
												</table>
												<div style="width:1089; height:110; display:block; overflow-x:none; overflow-y:scroll;">
													<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
													<col width=" 24" align="center"><!-- CheckBox -->
													<col width=" 80" align="left"><!-- Product# -->
													<col width=" 80" align="left"><!-- Serial# -->
													<col width="110" align="left"><!-- Model -->
													<col width=" 65" align="left"><!-- Type -->
													<col width=" 80" align="left"><!-- Status -->
													<col width=" 80" align="left"><!-- Start Date -->
													<col width=" 45" align="left"><!-- % -->
													<col width=" 80" align="left"><!-- End Date -->
													<col width=" 100" align="left"><!-- New Period End -->
													<col width=" 90" align="left"><!-- Amount -->
													<col width=" 90" align="left"><!-- New Amount -->
													<col width="160" align="left"><!-- Return Message -->
													<% BigDecimal dsContrPkB = null; %>
													<% String dsContrCatgCdB = null; %>
													<% String baseUsg = null; %>
													
													<% int indexB = -1; %>
														<ezf:row ezfHyo="B">
														<% indexB++; %>
															<% dsContrPkB = bMsg.B.no( indexB ).dsContrPk_B1.getValue(); %>
															<% dsContrCatgCdB = bMsg.B.no( indexB ).dsContrCatgCd_B1.getValue(); %>
															<% baseUsg = bMsg.B.no( indexB ).xxScrItem8Txt_B1.getValue(); %>
															<% if( dsContrPkA.equals( dsContrPkB )) { %>
																<tr height="25">
																	<% if( DS_CONTR_CATG.REGULAR.equals( dsContrCatgCdB ) &&  "Overage".equals( baseUsg )) { %>
																		<td>&nbsp;<ezf:inputHidden name="xxChkBox_B1" ezfName="xxChkBox_B1" ezfHyo="B" ezfArrayNo="${indexB}" /></td>
																		<td>&nbsp;<ezf:inputHidden name="mdseCd_B1" ezfName="mdseCd_B1" ezfHyo="B" ezfArrayNo="${indexB}" /></td>
																		<td>&nbsp;<ezf:inputHidden name="serNum_B1" ezfName="serNum_B1" ezfHyo="B" ezfArrayNo="${indexB}" /></td>
																	<% } else { %>
																		<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="${indexB}" /></td>
																		<td><ezf:inputText name="mdseCd_B1" ezfName="mdseCd_B1" value="3609B003AA" ezfHyo="B" ezfArrayNo="${indexB}" otherAttr=" size=\"10\""/></td>
																		<td><ezf:inputText name="serNum_B1" ezfName="serNum_B1" value="MSK101010" ezfHyo="B" ezfArrayNo="${indexB}" otherAttr=" size=\"10\""/></td>
																	<% } %>
																	<td><ezf:inputText name="t_MdlNm_B1" ezfName="t_MdlNm_B1" value="ADVC5055" ezfHyo="B" ezfArrayNo="${indexB}" otherAttr=" size=\"14\""/></td>
																	<td><ezf:inputText name="xxScrItem8Txt_B1" ezfName="xxScrItem8Txt_B1" value="Base" ezfHyo="B" ezfArrayNo="${indexB}" otherAttr=" size=\"8\""/></td>
																	<td><ezf:inputText name="dsContrCtrlStsNm_B1" ezfName="dsContrCtrlStsNm_B1" value="ACTIVE" ezfHyo="B" ezfArrayNo="${indexB}" otherAttr=" size=\"10\""/></td>
																	<td><ezf:inputText name="contrEffFromDt_B1" ezfName="contrEffFromDt_B1" value="01/01/2014" ezfHyo="B" ezfArrayNo="${indexB}" otherAttr=" size=\"10\""/></td>
																	<td><ezf:inputText name="xxDiscRatio_B1" ezfName="xxDiscRatio_B1" value="10" ezfHyo="B" ezfArrayNo="${indexB}" otherAttr=" size=\"5\""/></td>
																	<td><ezf:inputText name="contrEffThruDt_B1" ezfName="contrEffThruDt_B1" value="12/31/2014" ezfHyo="B" ezfArrayNo="${indexB}" otherAttr=" size=\"10\""/></td>
																	<% if( DS_CONTR_CATG.REGULAR.equals( dsContrCatgCdB ) &&  "Overage".equals( baseUsg )) { %>
																		<td>&nbsp;<ezf:inputHidden name="xxThruDt_B1" ezfName="xxThruDt_B1" ezfHyo="B" ezfArrayNo="${indexB}" /></td>
																	<% } else { %>
																		<td><ezf:inputText name="xxThruDt_B1" ezfName="xxThruDt_B1" value="3/31/2015" ezfHyo="B" ezfArrayNo="${indexB}" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxThruDt_B1', 4, '<%=indexB%>');"></td>
																	<% } %>
																	
																	<% if( DS_CONTR_CATG.REGULAR.equals( dsContrCatgCdB ) &&  "Overage".equals( baseUsg )) { %>
																		<td><ezf:inputButton name="OpenWin_Pricing" value="Pricing" ezfHyo="B" ezfArrayNo="${indexB}" htmlClass="pBtn5" /></td>
																		<td><ezf:inputButton name="OpenWin_Pricing" value="Pricing" ezfHyo="B" ezfArrayNo="${indexB}" htmlClass="pBtn5" /></td>
																	<% } else { %>
																		<td><ezf:inputText name="basePrcDealAmt_B1" ezfName="basePrcDealAmt_B1" value="200.00" ezfHyo="B" ezfArrayNo="${indexB}" otherAttr=" size=\"11\""/></td>
																		<td><ezf:inputText name="newBaseDealAmt_B1" ezfName="newBaseDealAmt_B1" value="220.00" ezfHyo="B" ezfArrayNo="${indexB}" otherAttr=" size=\"11\""/></td>
																	<% } %>
																	<td><ezf:inputText name="xxGenlFldAreaTxt_B1" ezfName="xxGenlFldAreaTxt_B1" ezfHyo="B" ezfArrayNo="${indexB}" otherAttr=" size=\"23\""/></td>
                                                                    <td class="pAuditInfo">
                                                                        <ezf:inputHidden name="xxRecHistCratTs_B" ezfName="xxRecHistCratTs_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_B\""/>
                                                                        <ezf:inputHidden name="xxRecHistCratByNm_B" ezfName="xxRecHistCratByNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_B\""/>
                                                                        <ezf:inputHidden name="xxRecHistUpdTs_B" ezfName="xxRecHistUpdTs_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_B\""/>
                                                                        <ezf:inputHidden name="xxRecHistUpdByNm_B" ezfName="xxRecHistUpdByNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_B\""/>
                                                                        <ezf:inputHidden name="xxRecHistTblNm_B" ezfName="xxRecHistTblNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_B\""/>
                                                                    </td>
																</tr>
															<% } %>
														</ezf:row>
														<ezf:skip>
															<tr height="25" ezfHyo="B">
																<td><input type="checkbox" value="Y" name="xxChkBox_B1" ezfname="xxChkBox_B1" ezfhyo="B" id="xxChkBox_B#{EZF_ROW_NUMBER}" checked></td>
																<td><input type="text" class="pPro" size="10" value="3609B003AA" name="mdseCd_B1" ezfname="mdseCd_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="MNB05459" name="serNum_B1" ezfname="serNum_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="15" value="ADVC5055" name="t_MdlNm_B1" ezfname="t_MdlNm_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="8" value="Base" name="xxScrItem8Txt_B1" ezfname="xxScrItem8Txt_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="ACTIVE" name="dsContrCtrlStsNm_B1" ezfname="dsContrCtrlStsNm_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="01/01/2014" name="contrEffFromDt_B1" ezfname="contrEffFromDt_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="3" value="10" name="xxDiscRatio_B1" ezfname="xxDiscRatio_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="12/31/2014" name="contrEffThruDt_B1" ezfname="contrEffThruDt_B1" ezfhyo="B"></td>
																<td><input type="text" size="10" value="3/31/2015" name="xxThruDt_B1" ezfname="xxThruDt_B1" ezfhyo="B"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxThruDt_B1', 4);"></td>
																<td><input type="text" class="pPro" size="12" value="200.00" name="basePrcDealAmt_B1" ezfname="basePrcDealAmt_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="12" value="220.00" name="newBaseDealAmt_B1" ezfname="newBaseDealAmt_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="25" value="" name="xxGenlFldAreaTxt_B1" ezfname="xxGenlFldAreaTxt_B1" ezfhyo="B"></td>
															</tr>
															<tr height="25" ezfHyo="B">>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td><input type="text" class="pPro" size="8" value="Overage" name="xxScrItem8Txt_B1" ezfname="xxScrItem8Txt_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="ACTIVE" name="dsContrCtrlStsNm_B1" ezfname="dsContrCtrlStsNm_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="01/01/2014" name="contrEffFromDt_B1" ezfname="contrEffFromDt_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="3" value="5" name="xxDiscRatio_B1" ezfname="xxDiscRatio_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="12/31/2014" name="contrEffThruDt_B1" ezfname="contrEffThruDt_B1" ezfhyo="B"></td>
																<td><input type="text" size="10" value="3/31/2015" name="xxThruDt_B1" ezfname="xxThruDt_B1" ezfhyo="B"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxThruDt_B1', 4);"></td>
																<td><input type="button" class="pBtn5" value="Pricing" name="OpenWin_OveragePricing" onclick="sendServer(this)"></td>
																<td><input type="button" class="pBtn5" value="Pricing" name="OpenWin_OveragePricing" onclick="sendServer(this)"></td>
																<td><input type="text" class="pPro" size="25" value="" name="xxGenlFldAreaTxt_B1" ezfname="xxGenlFldAreaTxt_B1" ezfhyo="B"></td>
															</tr>
															<tr height="25" ezfHyo="B">>
																<td><input type="checkbox" value="Y" name="xxChkBox_B1" ezfname="xxChkBox_B1" ezfhyo="B" id="xxChkBox_B#{EZF_ROW_NUMBER}" checked></td>
																<td><input type="text" class="pPro" size="10" value="3609B003AA" name="mdseCd_B1" ezfname="mdseCd_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="TSTG38989" name="serNum_B1" ezfname="serNum_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="15" value="ADVC5055" name="t_MdlNm_B1" ezfname="t_MdlNm_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="8" value="Base" name="xxScrItem8Txt_B1" ezfname="xxScrItem8Txt_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="ACTIVE" name="dsContrCtrlStsNm_B1" ezfname="dsContrCtrlStsNm_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="01/01/2014" name="contrEffFromDt_B1" ezfname="contrEffFromDt_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="3" value="10" name="xxDiscRatio_B1" ezfname="xxDiscRatio_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="12/31/2014" name="contrEffThruDt_B1" ezfname="contrEffThruDt_B1" ezfhyo="B"></td>
																<td><input type="text" size="10" value="3/31/2015" name="xxThruDt_B1" ezfname="xxThruDt_B1" ezfhyo="B"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxThruDt_B1', 4);"></td>
																<td><input type="text" class="pPro" size="12" value="200.00" name="basePrcDealAmt_B1" ezfname="basePrcDealAmt_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="12" value="220.00" name="newBaseDealAmt_B1" ezfname="newBaseDealAmt_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="25" value="" name="xxGenlFldAreaTxt_B1" ezfname="xxGenlFldAreaTxt_B1" ezfhyo="B"></td>
															</tr>
															<tr height="25" ezfHyo="B">>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td><input type="text" class="pPro" size="8" value="Overage" name="xxScrItem8Txt_B1" ezfname="xxScrItem8Txt_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="ACTIVE" name="dsContrCtrlStsNm_B1" ezfname="dsContrCtrlStsNm_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="01/01/2014" name="contrEffFromDt_B1" ezfname="contrEffFromDt_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="3" value="5" name="xxDiscRatio_B1" ezfname="xxDiscRatio_B1" ezfhyo="B"></td>
																<td><input type="text" class="pPro" size="10" value="12/31/2014" name="contrEffThruDt_B1" ezfname="contrEffThruDt_B1" ezfhyo="B"></td>
																<td><input type="text" size="10" value="3/31/2015" name="xxThruDt_B1" ezfname="xxThruDt_B1" ezfhyo="B"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxThruDt_B1', 4);"></td>
																<td><input type="button" class="pBtn5" value="Pricing" name="OpenWin_OveragePricing" onclick="sendServer(this)"></td>
																<td><input type="button" class="pBtn5" value="Pricing" name="OpenWin_OveragePricing" onclick="sendServer(this)"></td>
																<td><input type="text" class="pPro" size="25" value="" name="xxGenlFldAreaTxt_B1" ezfname="xxGenlFldAreaTxt_B1" ezfhyo="B"></td>
															</tr>
														</ezf:skip>
													</table>
												</div>
											</td>
										</tr>
									</table>
									<br />
								</ezf:row>
								
								
							</td>
						</tr>
					</table>
					
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
