<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230704123036 --%>
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
			<input type="hidden" name="pageID" value="NFCL3030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Receipt Entry">

			<ezf:inputHidden name="xxRecHistCratTs_RC" ezfName="xxRecHistCratTs_RC" otherAttr=" id=\"xxRecHistCratTs\""/>
			<ezf:inputHidden name="xxRecHistCratByNm_RC" ezfName="xxRecHistCratByNm_RC" otherAttr=" id=\"xxRecHistCratByNm\""/>
			<ezf:inputHidden name="xxRecHistUpdTs_RC" ezfName="xxRecHistUpdTs_RC" otherAttr=" id=\"xxRecHistUpdTs\""/>
			<ezf:inputHidden name="xxRecHistUpdByNm_RC" ezfName="xxRecHistUpdByNm_RC" otherAttr=" id=\"xxRecHistUpdByNm\""/>
			<ezf:inputHidden name="xxRecHistTblNm_RC" ezfName="xxRecHistTblNm_RC" otherAttr=" id=\"xxRecHistTblNm\""/>
<center>
	<table width="1128" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

<%-- ######################################## HEADER ######################################## --%>
<%@ page import="business.servlet.NFCL3030.NFCL3030BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC" %>
<%  NFCL3030BMsg bMsg = (NFCL3030BMsg)databean.getEZDBMsg(); %>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">

					<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="1" align="center">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" height="20" width="1100" rules="none"  style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
									<tr>
										<td valign="top">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="100" align="left">
												<col width="120" align="left">
												<col width="120" align="left">
												<col width="120" align="left">
												<col width="080" align="left">
												<col width="500" align="left">
												<col width="060" align="left">
												<tr>
													<td class="stab">Receipt Number</td>
													<td class="stab"><ezf:inputText name="rcptChkNum_H" ezfName="rcptChkNum_H" value="RCB08254" otherAttr=" size=\"15\" maxlength=\"15\" tabindex=\"1519\" ezftoupper=\"\""/></td>
													<td class="stab">Receipt Doc Number</td>
													<td class="stab"><ezf:inputText name="rcptNum_H" ezfName="rcptNum_H" value="RCB08254" otherAttr=" size=\"15\" maxlength=\"8\" tabindex=\"-1\" ezftoupper=\"\""/></td>
												<% if (AR_RCPT_SRC.BANK_OF_AMERICA_LOCKBOX.equals(bMsg.arRcptSrcCd_H.getValue())) { %>
													<td class="stab"><ezf:anchor name="arBatRcptNm_L1" ezfName="arBatRcptNm_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBatchName" >Batch Name</ezf:anchor></td>
												<% } else { %>
													<td class="stab">Batch Name</td>
												<% } %>
													<td class="stab"><ezf:inputText name="arBatRcptNm_H" ezfName="arBatRcptNm_H" value="BOA_CHI_20150303_017" otherAttr=" size=\"55\" maxlength=\"55\" tabindex=\"-1\" ezftoupper=\"\""/></td>
													<td><ezf:inputButton name="OpenWin_AttachFile" value="Attachment" htmlClass="pBtn6" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="0" cellspacing="0" height="20" width="1100" rules="none"  style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
									<tr>
										<td valign="top">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="100" align="left">
												<col width="250" align="left">
												<col width="020" align="left">
												<col width="080" align="left">
												<col width="150" align="left">
												<col width="020" align="left">
												<col width="150" align="left">
												<col width="300" align="left">
												<tr>
													<td class="stab" align="left"><b>Receipt</b></td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td class="stab" align="left"><b>Amount</b></td>
													<td>&nbsp;</td><td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">Receipt Date:</td>
													<td class="stab">
														<ezf:inputText name="rcptDt_H" ezfName="rcptDt_H" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1519\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rcptDt_H', 4);" >
													</td>
													<td>&nbsp;</td>
													<td class="stab">Receipt:</td>
													<td><ezf:inputText name="xxTotAmt_H1" ezfName="xxTotAmt_H1" value="100.00" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"1519\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">Receipt Type:</td>
													<td>
															<ezf:select name="arRcptTrxTpCd_H" ezfName="arRcptTrxTpCd_H" ezfBlank="1" ezfCodeName="arRcptTrxTpCd_LC" ezfDispName="arRcptTrxTpNm_LD" otherAttr=" style=\"width:220px;\" tabindex=\"1519\""/>
													</td>
													<td>&nbsp;</td>
													<td class="stab">Applied:</td>
													<td><ezf:inputText name="xxTotAmt_H2" ezfName="xxTotAmt_H2" value="0.00" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">Receipt Status:</td>
													<td>
														<ezf:select name="arRcptStsCd_H" ezfName="arRcptStsCd_H" ezfBlank="1" ezfCodeName="arRcptStsCd_LC" ezfDispName="arRcptStsNm_LD" otherAttr=" style=\"width:220px;\" tabindex=\"-1\""/>
													</td>	
													<td>&nbsp;</td>
													<td class="stab">On Account:</td>
													<td><ezf:inputText name="xxTotAmt_H3" ezfName="xxTotAmt_H3" value="0.00" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">Receipt Source:</td>
													<td>
														<ezf:select name="arRcptSrcCd_H" ezfName="arRcptSrcCd_H" ezfBlank="1" ezfCodeName="arRcptSrcCd_LC" ezfDispName="arRcptSrcNm_LD" otherAttr=" style=\"width:220px;\" tabindex=\"1519\""/>
													</td>	
													<td>&nbsp;</td>
													<td class="stab">Unapplied:</td>
													<td><ezf:inputText name="xxTotAmt_H4" ezfName="xxTotAmt_H4" value="0.00" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">Accounting Date:</td>
													<td class="stab">
														<ezf:inputText name="glDt_H" ezfName="glDt_H" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1519\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('glDt_H', 4);" >
													</td>	
													<td>&nbsp;</td>
													<td class="stab">Unidentified:</td>
													<td><ezf:inputText name="xxTotAmt_H5" ezfName="xxTotAmt_H5" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>	
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">Application Note:</td>
													<td colspan="4"><ezf:inputText name="arRcptNoteTxt_H" ezfName="arRcptNoteTxt_H" value="Test Comment" otherAttr=" size=\"66\" maxlength=\"1000\" tabindex=\"1519\" ezftoupper=\"\""/></td>	
													<td>&nbsp;</td>		
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
	<%-- ######################################## DETAIL ######################################## --%>
					<%-- ###TAB - HEAD --%>
					<div class="pTab_HEAD">
						<ul>
							<li  id="Customer" title="Customer" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_A" ezfEmulateBtn="1" ezfGuard="TAB_Customer" >Customer</ezf:anchor></li>
							<li  id="Reversal" title="Reversal" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_B" ezfEmulateBtn="1" ezfGuard="TAB_Reversal" >Reversal</ezf:anchor></li>
							<li  id="AddInfo"  title="AddInfo"  class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_C" ezfEmulateBtn="1" ezfGuard="TAB_AddInfo" >Add info</ezf:anchor></li>
						</ul>
					</div>
					<c:choose>
						<%-- Customer TAB --%>
						<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Customer'}">
							<script type="text/javascript">
								document.getElementById( "Customer").className = "pTab_ON";
								document.getElementById( "Reversal").className = "pTab_OFF";
								document.getElementById( "AddInfo" ).className = "pTab_OFF";
							</script>
							<%-- ###TAB - BODY --%>	
							<div class="pTab_BODY_In">
								<table width="100%">
									<tr>
										<td>
											<table width="1100" height="330" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top">
														<table border="0">
															<col width="120" align="left">
															<col width="500" align="left">
															<col width="380" align="right">
															<tr>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td></td>
															</tr>
																<td class="stab"><ezf:anchor name="payerCustCd_L" ezfName="payerCustCd_L" ezfEmulateBtn="1" ezfGuard="Click_LinkCustomer" otherAttr=" tabindex=\"1519\"">Customer Number(*):</ezf:anchor></td>
																<td><ezf:inputText name="payerCustCd_H" ezfName="payerCustCd_H" value="1015536" otherAttr=" size=\"12\" maxlength=\"8\" tabindex=\"1519\" ezftoupper=\"\""/><ezf:inputButton name="CustomerName" value=">>" otherAttr=" tabindex=\"1519\""/></td>
																<td></td>
															<tr>
																<td class="stab"><ezf:anchor name="payerCustCd_L" ezfName="payerCustCd_L" ezfEmulateBtn="1" ezfGuard="Click_LinkCustomer" otherAttr=" tabindex=\"1519\"">Customer Name(*):</ezf:anchor></td>
																<td><ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" value="NJ DEPT OF LAW & PUBLIC SAFETY" otherAttr=" size=\"33\" maxlength=\"360\" tabindex=\"1519\" ezftoupper=\"\""/></td>
																<td></td>
															</tr>
															<tr>
																<td class="stab"><ezf:anchor name="payerCustCd_L" ezfName="payerCustCd_L" ezfEmulateBtn="1" ezfGuard="Click_LinkCustomer" otherAttr=" tabindex=\"1519\"">Location:</ezf:anchor></td>
																<td><ezf:inputText name="locNum_H" ezfName="locNum_H" value="42886" otherAttr=" size=\"12\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																<td></td>
															</tr>
															<tr>
																<td><ezf:inputButton name="OpenWin_SearchAccountByTrxPopup" value="Search By Trx" htmlClass="pBtn8" otherAttr=" tabindex=\"1519\""/></td>
															</tr>
															<tr>
																<td class="stab"><ezf:anchor name="dsBankAcctNm_L2" ezfName="dsBankAcctNm_L2" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_21" otherAttr=" tabindex=\"1519\"">Bank Name:</ezf:anchor></td>
																<td><ezf:inputText name="dsBankAcctNm_H2" ezfName="dsBankAcctNm_H2" otherAttr=" size=\"31\" maxlength=\"80\" tabindex=\"-1\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td class="stab"><ezf:anchor name="dsBankBrNm_L2" ezfName="dsBankBrNm_L2" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_22" otherAttr=" tabindex=\"1519\"">Branch Name:</ezf:anchor></td>
																<td><ezf:inputText name="dsBankBrNm_H2" ezfName="dsBankBrNm_H2" otherAttr=" size=\"31\" maxlength=\"80\" tabindex=\"-1\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td class="stab"><ezf:anchor name="bankRteNum_L2" ezfName="bankRteNum_L2" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_23" otherAttr=" tabindex=\"1519\"">Routing Number:</ezf:anchor></td>
																<td><ezf:inputText name="bankRteNum_H2" ezfName="bankRteNum_H2" otherAttr=" size=\"12\" maxlength=\"10\" tabindex=\"-1\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td class="stab"><ezf:anchor name="dsBankAcctNum_L2" ezfName="dsBankAcctNum_L2" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_23" otherAttr=" tabindex=\"1519\"">Account Number:</ezf:anchor></td>
																<td><ezf:inputText name="dsBankAcctNum_M1" ezfName="dsBankAcctNum_M1" otherAttr=" size=\"31\" maxlength=\"14\" tabindex=\"-1\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td class="stab">Reference:</td>
																<td><ezf:inputText name="arRcptRefTxt_H" ezfName="arRcptRefTxt_H" otherAttr=" size=\"100\" maxlength=\"50\" tabindex=\"1519\" ezftoupper=\"\""/></td>	
																<td></td>
															</tr>
															<tr>
																<td class="stab">Comment:</td>
																<td><ezf:inputText name="arRcptCmntTxt_H" ezfName="arRcptCmntTxt_H" value="100814 MO ONA MIS D FUNDS" otherAttr=" size=\"100\" maxlength=\"1000\" tabindex=\"1519\" ezftoupper=\"\""/></td>	
																<td></td>
															</tr>
															<tr>
																<td class="stab">Note:</td>
																<td><ezf:inputText name="arRcptMiscCmntTxt_H" ezfName="arRcptMiscCmntTxt_H" value="112219 POST APPLIED PER L BEACH" otherAttr=" size=\"100\" maxlength=\"1000\" tabindex=\"1519\" ezftoupper=\"\""/></td>	
																<td></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</c:when>
						<%-- Reversal TAB --%>
						<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Reversal'}">
							<script type="text/javascript">
								document.getElementById( "Customer").className = "pTab_OFF";
								document.getElementById( "Reversal").className = "pTab_ON";
								document.getElementById( "AddInfo" ).className = "pTab_OFF";
							</script>
							<%-- ###TAB - BODY --%>	
							<div class="pTab_BODY_In">
								<table width="1100">
									<tr>
										<td>
											<table width="1100" height="330" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top">
														<table border="0">
															<col width="100" align="left">
															<col width="600" align="left">
															<col width="" align="right">
															<tr>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															<tr>
																<td class="stab">Date:</td>
																<td class="stab">
																	<ezf:inputText name="voidDt_H" ezfName="voidDt_H" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1519\""/>
																	<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('voidDt_H', 4);" >
																</td>	
																<td rowspan="4">
																	<table border="0">
																		<col width="5" align="left">
																		<col width="300" align="left">
																		<col width="" align="left">
																		<tr>
																			<td>&nbsp;</td>
																			<td class="stab">
																				[&nbsp;Note&nbsp;]<br>Reversal&nbsp; Information&nbsp; will&nbsp; not&nbsp; be 
																								  <br>registered&nbsp; or&nbsp; updated&nbsp; by&nbsp; [Submit]&nbsp; Button.
																								  <br>Please&nbsp; use&nbsp; [Reverse]&nbsp; button.
																			</td>
																			<td>&nbsp;</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td class="stab">Category:</td>
																<td>
																	<ezf:select name="arRcptVoidRsnCd_H" ezfName="arRcptVoidRsnCd_H" ezfBlank="1" ezfCodeName="arRcptVoidRsnCd_LC" ezfDispName="arRcptVoidRsnNm_LD" otherAttr=" style=\"width:220px;\" tabindex=\"1519\""/>
																</td>
															</tr>
															<tr>
																<td class="stab">Reason:</td>
																<td>
																	<ezf:select name="arRcptRvrsRsnCd_H" ezfName="arRcptRvrsRsnCd_H" ezfBlank="1" ezfCodeName="arRcptRvrsRsnCd_LC" ezfDispName="arRcptRvrsRsnNm_LD" otherAttr=" style=\"width:220px;\" tabindex=\"1519\""/>
																</td>
															</tr>
															<tr>
																<td class="stab">GL Date:</td>
																<td class="stab">
																	<ezf:inputText name="voidGlDt_H" ezfName="voidGlDt_H" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1519\""/>
																	<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('voidGlDt_H', 4);" >
																</td>	
															</tr>
															<tr>
																<td class="stab">Comment:</td>
																<td><ezf:inputText name="arRcptRvrsCmntTxt_H" ezfName="arRcptRvrsCmntTxt_H" otherAttr=" size=\"100\" maxlength=\"1000\" tabindex=\"1519\" ezftoupper=\"\""/></td>	
																<td><ezf:inputButton name="Click_Reverse" value="Reverse" htmlClass="pBtn9" otherAttr=" tabindex=\"1519\""/></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</c:when>
						<%-- Add Info TAB --%>
						<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_AddInfo'}">
							<script type="text/javascript">
								document.getElementById( "Customer").className = "pTab_OFF";
								document.getElementById( "Reversal").className = "pTab_OFF";
								document.getElementById( "AddInfo" ).className = "pTab_ON";
							</script>
							<%-- ###TAB - BODY --%>	
							<div class="pTab_BODY_In">
								<table width="1100">
									<tr>
										<td>
											<table width="1100" height="330" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top">
														<table border="0">
															<col width="160" align="left">
															<col width="300" align="left">
															<tr>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
																<td class="stab">Remittance Date:</td>
																<td class="stab">
																	<ezf:inputText name="arRcptRemDt_H" ezfName="arRcptRemDt_H" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1519\""/>
																	<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('arRcptRemDt_H', 4);" >
																</td>	
															<tr>
																<td class="stab"><ezf:anchor name="dsBankAcctNm_L1" ezfName="dsBankAcctNm_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_11" otherAttr=" tabindex=\"1519\"">Remittance Bank Name:</ezf:anchor></td>
																<td><ezf:inputText name="dsBankAcctNm_H1" ezfName="dsBankAcctNm_H1" otherAttr=" size=\"31\" maxlength=\"80\" tabindex=\"-1\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td class="stab"><ezf:anchor name="dsBankBrNm_L1" ezfName="dsBankBrNm_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_12" otherAttr=" tabindex=\"1519\"">Remittance Branch Name:</ezf:anchor></td>
																<td><ezf:inputText name="dsBankBrNm_H1" ezfName="dsBankBrNm_H1" otherAttr=" size=\"31\" maxlength=\"80\" tabindex=\"-1\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td class="stab"><ezf:anchor name="dsBankAcctNum_L1" ezfName="dsBankAcctNum_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_13" otherAttr=" tabindex=\"1519\"">Remittance Account Number:</ezf:anchor></td>
																<td><ezf:inputText name="dsBankAcctNum_H1" ezfName="dsBankAcctNum_H1" otherAttr=" size=\"31\" maxlength=\"14\" tabindex=\"-1\" ezftoupper=\"\""/></td>
															</tr>
															<tr>
																<td class="stab">Approval Code:</td>
																<td><ezf:inputText name="crCardApvlCd_H" ezfName="crCardApvlCd_H" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"1519\" ezftoupper=\"\""/></td>	
															</tr>
															<tr>
																<td class="stab">Payment Server Order Number:</td>
																<td><ezf:inputText name="pmtSvcOrdNum_H" ezfName="pmtSvcOrdNum_H" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"1519\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</c:when>
					</c:choose>
				</div>
			</td>
		</tr>
	</table>
	<table width ="100%">
		<tr>
			<td align="right">
                <ezf:inputButton name="Click_CreditCardRefund" value="Credit Card Refund" htmlClass="pBtn12" otherAttr=" tabindex=\"1519\""/>
                <ezf:inputButton name="Click_JournalInquiry" value="Journal Inquiry" htmlClass="pBtn12" otherAttr=" tabindex=\"1519\""/>
                <ezf:inputButton name="Click_CashApplication" value="Application" htmlClass="pBtn12" otherAttr=" tabindex=\"1519\""/>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
