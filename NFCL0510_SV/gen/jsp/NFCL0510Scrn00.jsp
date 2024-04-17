<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230704183115 --%>
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

<%@ page import="business.servlet.NFCL0510.NFCL0510BMsg" %>
<%@ page import="business.servlet.NFCL0510.NFCL0510_BBMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%  NFCL0510BMsg bMsg = (NFCL0510BMsg)databean.getEZDBMsg(); %>
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NFCL0510Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Lockbox Correction Screen">
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

                <div class="pTab_BODY" style="WIDTH: 1133px; word-break:kepp-all">
                    <table width="1121" height = "50" cellSpacing="0" cellPadding="0" border="0" style="margin-top:3px">
                        <col width="5">
                        <col width="110">
                        <col width="180">
                        <col width="15">
                        <col width="120">
                        <col width="180">
                        <col width="15">
                        <col width="100">
                        <col width="220">
                        <col width="170">
                        <col width="">
                        <tr>
                            <td>&nbsp;</td>
                            <td class="stab">Lockbox File Name(*)</td>
                            <td><ezf:inputText name="arLockBoxFileNm_H" ezfName="arLockBoxFileNm_H" value="123456789A123456789B" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\""/></td>
                            <td>&nbsp;</td>
                            <td class="stab">Lockbox Name</td>
                            <td>
								<ezf:select name="arLockBoxCd_H" ezfName="arLockBoxCd_H" ezfBlank="1" ezfCodeName="arLockBoxCd_LC" ezfDispName="arLockBoxNm_LD" otherAttr=" style=\"width:180px;\""/>
                            </td>
                            <td>&nbsp;</td>
                            <td class="stab">Lockbox Status</td>
                            <td>
                                <ezf:select name="arLockBoxStsCd_H" ezfName="arLockBoxStsCd_H" ezfBlank="1" ezfCodeName="arLockBoxStsCd_LC" ezfDispName="arLockBoxStsDescTxt_LD" otherAttr=" style=\"width:180px;\""/>
                            </td>
                            <td class="stab"><ezf:inputCheckBox name="xxChkBox_H" ezfName="xxChkBox_H" value="Y" /> Include Trasferred Record</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td class="stab">Batch#</td>
                            <td><ezf:inputText name="arLockBoxBatNum_H" ezfName="arLockBoxBatNum_H" value="123456789A123456789B" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
                            <td>&nbsp;</td>
                            <td class="stab">Status Message Detail(*)</td>
                            <td><ezf:inputText name="arBatInfoMsgTxt_H" ezfName="arBatInfoMsgTxt_H" value="123456789A123456789B" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\""/></td>
                            <td>&nbsp;</td>
                            <td class="stab">Receipt Date</td>
                            <td><ezf:inputText name="rcvDt_H1" ezfName="rcvDt_H1" value="2013/05/02" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rcvDt_H1', 4 );">
                                &nbsp;-&nbsp;<ezf:inputText name="rcvDt_H2" ezfName="rcvDt_H2" value="2013/05/02" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rcvDt_H2', 4 );">
                            </td>
							 <td align="right"><ezf:inputButton name="Click_Search" value="Search" htmlClass="pBtn6" /></td>
							 <td></td>
                        </tr>
                    </table>
                </div>
                <hr>
								<table border="0" cellpadding="1" cellspacing="0" width="1003">
									<tr>
										<td>
											<table width="100%">
												<col valign="top">
												<tr>
													<td align="right">
														<ezf:skip>
														<table border="0" cellpadding="1" cellspacing="0">
															<col width="54"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="10">
															<col width="50">
															<col width="50">
															<tr>
																<td class="stab">Showing</td>
																<td class="pOut">1</td>
																<td class="stab">to</td>
																<td class="pOut">20</td>
																<td class="stab">of</td>
																<td class="pOut">20</td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
																<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
															</tr>
														</table>
														</ezf:skip>

														<%-- Pagenation --%>
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
										</td>

									</tr>
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0">
												<tr>
													<td>
													<div id="A_TBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:1127;float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'A_TBL' ));" >
															<table border="1" cellpadding="0" cellspacing="0" style="width:1127px;">
																<col width="200" align="center">
																<col width="110" align="center">
																<col width="80" align="center">
																<col width="120" align="center">
																<col width="75" align="center">
																<col width="105" align="center">
																<col width="50" align="center">
																<col width="125" align="center">
																<col width="30" align="center">
																<col width="20" align="center">
																<col width="50" align="center">
																<col width="125" align="center">
																<col width="17" align="center">

																<tr>
																	<td class="pClothBs">Lockbox File Name</td>
																	<td class="pClothBs">Lockbox Name</td>
																	<td class="pClothBs">Receipt Date</td>
																	<td class="pClothBs">Status</td>
																	<td class="pClothBs">Rem<br>Routing#</td>
																	<td class="pClothBs">Rem Bank<br> Account#</td>
																	<td class="pClothBs">Lockbox<br>Count</td>
																	<td class="pClothBs">Lockbox Amount</td>
																	<td class="pClothBs">Bat#</td>
																	<td class="pClothBs">Err</td>
																	<td class="pClothBs">Rcpt<br>Count</td>
																	<td class="pClothBs">Receipt Amount</td>
																	<td class="pClothBs"></td>
																</tr>
															</table>
														</div>
<!--
                                                        <div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:150; width:1127; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
-->
														<div id="A_TBL" style="overflow-y:scroll; height:145; width:1127; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'A_TBL_Top' ));" >
															<table border="1" cellpadding="0" cellspacing="0" style="width:1110px;word-break:break-all;" id="B">
 																<col width="200" align="left">
																<col width="110" align="left">
																<col width="80" align="center">
																<col width="120" align="center">
																<col width="75" align="center">
																<col width="105" align="center">
																<col width="50" align="right">
																<col width="125" align="center">
																<col width="30" align="center">
																<col width="20" align="center">
																<col width="50" align="center">
																<col width="125" align="center">
																<tbody>
																	<ezf:row ezfHyo="A">
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><div id="arLockBoxFileNm_A#{EZF_ROW_NUMBER}"><ezf:anchor name="Click_Link_LockboxFileName" ezfName="Click_Link_LockboxFileName" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_Link_LockboxFileName" otherAttr=" ezfanchortext=\"\""><ezf:label name="arLockBoxFileNm_A" ezfName="arLockBoxFileNm_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></div></td>
																		<td><div id="arLockBoxNm_A#{EZF_ROW_NUMBER}"><ezf:label name="arLockBoxNm_A" ezfName="arLockBoxNm_A" ezfHyo="A" ezfArrayNo="0" /></div></td>
																		<td><div id="rcvDt_A#{EZF_ROW_NUMBER}"><ezf:label name="rcvDt_A" ezfName="rcvDt_A" ezfHyo="A" ezfArrayNo="0" /></div></td>
																		<td><div id="arLockBoxStsCd_A#{EZF_ROW_NUMBER}"><ezf:select name="arLockBoxStsCd_A" ezfName="arLockBoxStsCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="arLockBoxStsCd_AC" ezfDispName="arLockBoxStsDescTxt_AD" ezfCodeDispHyo="A" otherAttr=" style=\"width:110px;\""/></div></td>
																		<td><div id="remBankRteNum_A#{EZF_ROW_NUMBER}"><ezf:label name="remBankRteNum_A" ezfName="remBankRteNum_A" ezfHyo="A" ezfArrayNo="0" /></div></td>
																		<td><div id="remDsBankAcctNum_A#{EZF_ROW_NUMBER}"><ezf:label name="remDsBankAcctNum_A" ezfName="remDsBankAcctNum_A" ezfHyo="A" ezfArrayNo="0" /></div></td>
																		<td><div id="lockBoxRecCnt_A#{EZF_ROW_NUMBER}"><ezf:label name="lockBoxRecCnt_A" ezfName="lockBoxRecCnt_A" ezfHyo="A" ezfArrayNo="0" /></div></td>
																		<td><div id="lockBoxTotAmt_A#{EZF_ROW_NUMBER}"><ezf:inputText name="lockBoxTotAmt_A" ezfName="lockBoxTotAmt_A" value="812.25" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\" style=\"text-align:right\""/></div></td>
																		<td><ezf:anchor name="Click_Link_BatchNumber" ezfName="Click_Link_BatchNumber" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_Link_BatchNumber" otherAttr=" id=\"arBatRcptNm_B#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="arLockBoxBatNum_A" ezfName="arLockBoxBatNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																		<td><ezf:label name="arRcptRcvErrFlg_A" ezfName="arRcptRcvErrFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="batRcptRecCnt_A" ezfName="batRcptRecCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="batRcptTotAmt_A" ezfName="batRcptTotAmt_A" value="812.25" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"1502\" ezftoupper=\"\" style=\"text-align:right\""/></td>
																		
																	</tr>
																	</ezf:row>
																</tbody>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
                    <%-- ###TAB - HEAD --%>
					<div class="pTab_HEAD">
						<ul>
							<li class="pTab_On" id="Detail" ><ezf:anchor name="" ezfName="xxTabProt_01" >Detail</ezf:anchor></li>
						</ul>
					</div>

                    <%-- ###TAB - Header --%>
                    <c:choose>
                    
                    <%-- ###TAB - Detail --%>
                    <c:when test="${pageScope._ezddatabean.xxDplyTab=='Detail'}">
                        <script type="text/javascript">
                          document.getElementById("Detail").className = "pTab_ON";
                        </script>

                        <div class="pTab_BODY">
                         		<table border="0" cellpadding="1" cellspacing="0" width="1003">
									<tr>
										<td>
											<table width="100%">
											    <col width="100" align="left">
											    <col width="300" align="left">
											    <col width="40" align="left">
											    <col width="50" align="left">
												<col valign="top">
												<tr>
													<td class="stab">Lockbox File Name</td>
													<td><ezf:inputText name="arLockBoxFileNm_BH" ezfName="arLockBoxFileNm_BH" value="BOA_CHICAGO_200150309" otherAttr=" size=\"40\""/></td>
													<td class="stab">Batch#</td>
													<td><ezf:inputText name="arLockBoxBatNum_BH" ezfName="arLockBoxBatNum_BH" value="999" otherAttr=" size=\"3\""/></td>
													<td align="right">
														<ezf:skip>
														<table border="0" cellpadding="1" cellspacing="0">
															<col width="54"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="10">
															<col width="50">
															<col width="50">
															<tr>
																<td class="stab">Showing</td>
																<td class="pOut">1</td>
																<td class="stab">to</td>
																<td class="pOut">20</td>
																<td class="stab">of</td>
																<td class="pOut">20</td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
																<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
															</tr>
														</table>
														</ezf:skip>

														<%-- Pagenation --%>
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
										</td>
									</tr>
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0">
												<tr>
													<td>
														<div id="B_TBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:1127;float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'B_TBL' ));" >
															<table border="1" cellpadding="0" cellspacing="0" style="width:1127px;">
 																<col width="30" align="center">
 																<col width="40" align="center">
																<col width="110" align="center">
 																<col width="160" align="center">
																<col width="125" align="center">
																<col width="75" align="center">
																<col width="105" align="center">
																<col width="80" align="center">
																<col width="115" align="center">
																<col width="115" align="center">
																<col width="125" align="center">
																<col width="17" align="center">
																<tr>
																    <td class="pClothBs">Bat#</td>
																	<td class="pClothBs">Line#</td>
																	<td class="pClothBs">Status Msg Detail</td>
																	<td class="pClothBs">Receipt#</td>
																	<td class="pClothBs">Amount</td>
																	<td class="pClothBs">Transit<br>Routing#</td>
																	<td class="pClothBs">Cust Bank<br> Account#</td>
																	<td class="pClothBs">Customer</td>
																	<td class="pClothBs">Invoice#</td>
																	<td class="pClothBs">Bill Type</td>
																	<td class="pClothBs">Applied Amount</td>
																	<td class="pClothBs"></td>
																</tr>
															</table>
														</div>
														<div id="B_TBL" style="overflow-y:scroll; height:180; width:1127; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'B_TBL_Top'));">
															<table border="1" cellpadding="0" cellspacing="0" style="width:1110px;word-break:break-all;" id="B">
 																<col width="30" align="center">
 																<col width="40" align="center">
																<col width="110" align="center">
 																<col width="160" align="left">
																<col width="125" align="right">
																<col width="75" align="center">
																<col width="105" align="center">
																<col width="80" align="left">
																<col width="115" align="left">
																<col width="115" align="left">
																<col width="125" align="right">
																<tbody>
																	<% int bIdx = 0; %>
																	<ezf:row ezfHyo="B">
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><div id="arLockBoxBatNum_B#{EZF_ROW_NUMBER}"><ezf:label name="arLockBoxBatNum_B" ezfName="arLockBoxBatNum_B" ezfHyo="B" ezfArrayNo="0" /></div></td>
																		<td><div id="arLockBoxBatLineNum_B#{EZF_ROW_NUMBER}"><ezf:label name="arLockBoxBatLineNum_B" ezfName="arLockBoxBatLineNum_B" ezfHyo="B" ezfArrayNo="0" /></div></td>
																		<td><div id="arBatInfoLvlCd_B#{EZF_ROW_NUMBER}"><ezf:select name="arBatInfoLvlCd_B" ezfName="arBatInfoLvlCd_B" ezfHyo="B" ezfArrayNo="0" ezfCodeName="arBatInfoLvlCd_BC" ezfDispName="arBatInfoMsgTxt_BD" ezfCodeDispHyo="B" otherAttr=" style=\"width:110px;\""/></div></td>
																	<% if (ZYPCommonFunc.hasValue(bMsg.B.no(bIdx).rcptNum_B)) { %>
																		<td><div id="custRcptNum_B#{EZF_ROW_NUMBER}"><ezf:anchor name="custRcptNum_L1" ezfName="custRcptNum_L1" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_Link_ReceiptNumber" otherAttr=" id=\"custRcptNum_B#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="custRcptNum_B" ezfName="custRcptNum_B" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></div></td>
 																	<% } else { %>
																		<td><div id="custRcptNum_B#{EZF_ROW_NUMBER}"><ezf:inputText name="custRcptNum_B" ezfName="custRcptNum_B" value="RCB12ABCDE1234512345" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"22\""/></div></td>
																	<% } %>
																		<td><div id="custRcptAmt_B#{EZF_ROW_NUMBER}"><ezf:label name="custRcptAmt_B" ezfName="custRcptAmt_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align:right\""/></div></td>
																		<td><div id="custBankRteNum_B#{EZF_ROW_NUMBER}"><ezf:label name="custBankRteNum_B" ezfName="custBankRteNum_B" ezfHyo="B" ezfArrayNo="0" /></div></td>
																		<td><div id="custDsBankAcctNum_BM#{EZF_ROW_NUMBER}"><ezf:label name="custDsBankAcctNum_BM" ezfName="custDsBankAcctNum_BM" ezfHyo="B" ezfArrayNo="0" /></div></td>
																		<td><div id="payerCustCd_B#{EZF_ROW_NUMBER}"><ezf:label name="payerCustCd_B" ezfName="payerCustCd_B" ezfHyo="B" ezfArrayNo="0" /></div></td>
																		<td><ezf:label name="custInvNum_B" ezfName="custInvNum_B" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td><ezf:label name="xxScrItem30Txt_B" ezfName="xxScrItem30Txt_B" ezfHyo="B" ezfArrayNo="0" /></td>
																        <td><ezf:label name="custInvAmt_B" ezfName="custInvAmt_B" ezfHyo="B" ezfArrayNo="0" /></td>
																	</tr>
																	<% bIdx++; %>
																	</ezf:row>
																</tbody>
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
				
							

<%-- **** Task specific area ends here **** --%>
