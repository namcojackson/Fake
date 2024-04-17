<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230207154729 --%>
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
			<input type="hidden" name="pageID" value="NMAL6730Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Bill To Details">
			
			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" />
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" />
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" />
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" />
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" />
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Location Info" class="pTab_ON"><a href="#">Bill To Dtl</a></li>
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
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table width="98%" border="0" align="center">
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="500">
									<col width="50">
									<col width="500">
									<tr>
									<td>
									<table>
										<col width="80">
										<col width="420">
										<tr>
										<td class="stab">Account Name</td>
										<td ><ezf:label name="dsAcctNm_H1" ezfName="dsAcctNm_H1" /></td>
										</tr>
										<tr>
										<td class="stab">Address</td>
										<td>
											<ezf:label name="xxAllLineAddr_H1" ezfName="xxAllLineAddr_H1" />
										</td>
										</tr>
										<tr>
										<td class="stab">City</td>
										<td><ezf:label name="ctyAddr_H1" ezfName="ctyAddr_H1" /></td>
										</tr>
										<tr>
										<td class="stab">State</td>
										<td><ezf:label name="stCd_H1" ezfName="stCd_H1" /></td>
										</tr>
										<tr>
										<td class="stab">Postal Code</td>
										<td><ezf:label name="postCd_H1" ezfName="postCd_H1" /></td>
										</tr>
										<tr>
										<td></td>
										<td><br/></td>
										</tr>
									</table>
									</td>
									<td><br/></t>
									<td>
									<table>
										<col width="100">
										<col width="400">
										<tr>
											<td class="stab">Location Number</td>
											<td><ezf:label name="locNum_H1" ezfName="locNum_H1" /></td>
										</tr>
										<tr>
											<td class="stab">Bill To Code</td>
											<td><ezf:label name="billToCustCd_H1" ezfName="billToCustCd_H1" /></td>
										</tr>
										<tr>
										<td colspan="2"><table border="0" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
										<col width="420">
										<tr>
											<td>
												<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
													<col width="130">
													<col width="35">
													<col width="35">
													<col width="170">
													<tr height="21">
														<td class="stab"><ezf:anchor name="coaChCd_H1" ezfName="coaChCd_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_Coa" >GL Sales Channel</ezf:anchor></td>
														<td><ezf:inputText name="coaChCd_H1" ezfName="coaChCd_H1" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
														<td><ezf:inputButton name="GetCoaChNm" value=">>" htmlClass="pBtn0" /></td>
														<td><ezf:inputText name="coaChNm_H1" ezfName="coaChNm_H1" value="WWWWWWWWW1WWWWWWWWW2WWWW" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
													</tr>
													<!--
													<tr height="21">
														<td class="stab"><a href="#" name="coaAfflCd_H1" ezfName="coaAfflCd_H1" onclick="sendServer('OpenWin_Coa2')">GL Intercompany Code</a></td>
														<td><input type="text" size="3" maxlength="3" name="coaAfflCd_H1" ezfname="coaAfflCd_H1" ezftoupper></td>
														<td><input type="button" class="pBtn0" value=">>" name="GetInterCompanyNm" onclick="sendServer(this)" ></td>
														<td><input type="text" size="20" maxlength="20" value="WWWWWWWWW1WWWWWWWWW2WWWW" name="coaAfflNm_H1" ezfname="coaAfflNm_H1"></td>
													</tr>
													-->
												</table>
											</td>
											
										</tr>
										
									</table>
									</tr>
									</table>
									</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>
					<table width="100%">
						<col valign="top">
						<tr>
							<td>
							<div class="pTab_HEAD">
								<ul>
									<li class="pTab_ON" id="Financial" title="Financial"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Financial" >Financials</ezf:anchor></li>
									<li class="pTab_OFF" id="InvBllg" title="InvBllg"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="TAB_InvBllg" >Invoice/Billing</ezf:anchor></li>
									<li class="pTab_OFF" id="Taxing" title="Taxing"><ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="TAB_Taxing" >Taxing</ezf:anchor></li>
								</ul>
							</div>
							<c:choose>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Financial'}">
							<script type="text/javascript">
								document.getElementById( "Financial" ).className = "pTab_ON";
								document.getElementById( "InvBllg" ).className = "pTab_OFF";
								document.getElementById( "Taxing" ).className = "pTab_OFF";
							</script>
							<div class="pTab_BODY_In">
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center" style="table-layout: fixed">
									<col width="370">
									<col width="15">
									<col width="370">
									<col width="15">
									<col width="330">
									<tr>
										<td colspan="5" align="right">
											<table border="0" cellpadding="1" cellspacing="0">
												<tr>
													<td class="stab"><ezf:anchor name="xxLinkProt_F1" ezfName="xxLinkProt_F1" ezfEmulateBtn="1" ezfGuard="OpenWin_Template" >Template</ezf:anchor></td>
													<td><ezf:inputText name="dsCustArTmplNm_F1" ezfName="dsCustArTmplNm_F1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
													<td><ezf:inputButton name="ApplyTemplate" value="Apply" htmlClass="pBtn3" />
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td valign="top">
											<fieldset style="height:200;">
												<legend>CREDIT</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="130">
													<col width="240">
													<tr>
														<td class="stab">Currency</td>
														<td><ezf:select name="ccyCd_P1" ezfName="ccyCd_P1" ezfBlank="1" ezfCodeName="ccyCd_F1" ezfDispName="ccyNm_F1" /></td>
													</tr>
													<tr>
														<td class="stab">Credit Rating</td>
														<td><ezf:select name="custCrRtgCd_P1" ezfName="custCrRtgCd_P1" ezfBlank="1" ezfCodeName="custCrRtgCd_F1" ezfDispName="custCrRtgNm_F1" /></td>
													</tr>
													<tr>
														<td class="stab">Credit Limit</td>
														<td><ezf:inputText name="crLimitAmt_F1" ezfName="crLimitAmt_F1" otherAttr=" size=\"22\" maxlength=\"22\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">Credit Review</td>
														<td><ezf:select name="crChkReqTpCd_P1" ezfName="crChkReqTpCd_P1" ezfBlank="1" ezfCodeName="crChkReqTpCd_F1" ezfDispName="crChkReqTpNm_F1" /></td>
													</tr>
													<tr>
														<td class="stab">Grace Period (Days)</td>
														<td><ezf:select name="crRiskClsCd_P1" ezfName="crRiskClsCd_P1" ezfBlank="1" ezfCodeName="crRiskClsCd_F1" ezfDispName="crRiskClsNm_F1" otherAttr=" style=\"width: 100px\""/></td>
													</tr>
													<tr>
														<td class="stab">Contract Grace Period</td>
														<td><ezf:select name="contrCrRiskClsCd_P1" ezfName="contrCrRiskClsCd_P1" ezfBlank="1" ezfCodeName="contrCrRiskClsCd_F1" ezfDispName="contrCrRiskClsNm_F1" otherAttr=" style=\"width: 230px\""/></td>
													</tr>
													<tr>
														<td class="stab">Payment Term</td>
														<td><ezf:select name="pmtTermCashDiscCd_P1" ezfName="pmtTermCashDiscCd_P1" ezfBlank="1" ezfCodeName="pmtTermCashDiscCd_F1" ezfDispName="pmtTermCashDiscNm_F1" otherAttr=" style=\"width: 100px\""/></td>
													</tr>
													<tr>
														<td class="stab">Override Payment Term</td>
														<td><ezf:inputCheckBox name="ovrdPmtTermFlg_F1" ezfName="ovrdPmtTermFlg_F1" value="Y" /></td>
													</tr>
													<tr>
														<td class="stab">CWO or CC Required</td>
														<td><ezf:inputCheckBox name="cashOrCcReqFlg_F1" ezfName="cashOrCcReqFlg_F1" value="Y" />(Order Payment Method)</td>
													</tr>
													<tr>
														<td class="stab">Hard Hold</td>
														<td><ezf:inputCheckBox name="custHardHldFlg_F1" ezfName="custHardHldFlg_F1" value="Y" /></td>
													</tr>
													<tr>
														<td class="stab"><ezf:anchor name="xxLinkProt_F2" ezfName="xxLinkProt_F2" ezfEmulateBtn="1" ezfGuard="OpenWin_RemTo" >Rem ID</ezf:anchor></td>
														<td><ezf:inputText name="remId_F1" ezfName="remId_F1" otherAttr=" size=\"22\" maxlength=\"22\" ezftoupper=\"\""/></td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td>
											<br />
										</td>
										<td valign="top">
											<fieldset style="height:200;">
												<legend>COLLECTIONS</legend>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="130">
													<col width="40">
													<col width="130">
													<col width="40">
													<tr>
														<td class="stab">Send Statements</td>
														<td><ezf:inputCheckBox name="arStmtFlg_F1" ezfName="arStmtFlg_F1" value="Y" />
															<ezf:inputHidden name="xxRecHistCratTs_F1" ezfName="xxRecHistCratTs_F1" />
															<ezf:inputHidden name="xxRecHistCratByNm_F1" ezfName="xxRecHistCratByNm_F1" />
															<ezf:inputHidden name="xxRecHistUpdTs_F1" ezfName="xxRecHistUpdTs_F1" />
															<ezf:inputHidden name="xxRecHistUpdByNm_F1" ezfName="xxRecHistUpdByNm_F1" />
															<ezf:inputHidden name="xxRecHistTblNm_F1" ezfName="xxRecHistTblNm_F1" />
														</td>
														<td class="stab">Send Credit Balance</td>
														<td><ezf:inputCheckBox name="sendCrBalStmtFlg_F1" ezfName="sendCrBalStmtFlg_F1" value="Y" /></td>
													</tr>
													<tr>
														<td class="stab">Statements Issue Day</td>
														<td><ezf:select name="arStmtIssCycleCd_P1" ezfName="arStmtIssCycleCd_P1" ezfBlank="1" ezfCodeName="arStmtIssCycleCd_F1" ezfDispName="arStmtIssCycleNm_F1" />
															<ezf:inputHidden name="xxRecHistCratTs_F1" ezfName="xxRecHistCratTs_F1" />
															<ezf:inputHidden name="xxRecHistCratByNm_F1" ezfName="xxRecHistCratByNm_F1" />
															<ezf:inputHidden name="xxRecHistUpdTs_F1" ezfName="xxRecHistUpdTs_F1" />
															<ezf:inputHidden name="xxRecHistUpdByNm_F1" ezfName="xxRecHistUpdByNm_F1" />
															<ezf:inputHidden name="xxRecHistTblNm_F1" ezfName="xxRecHistTblNm_F1" />
														</td>
													</tr>
													<tr>
														<td class="stab"><ezf:anchor name="xxLinkProt_F3" ezfName="xxLinkProt_F3" ezfEmulateBtn="1" ezfGuard="OpenWin_CltCustTp" >Customer Collection Type</ezf:anchor></td>
														<td><ezf:inputText name="cltCustTpCd_F1" ezfName="cltCustTpCd_F1" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
															<ezf:inputHidden name="xxRecHistCratTs_F1" ezfName="xxRecHistCratTs_F1" />
															<ezf:inputHidden name="xxRecHistCratByNm_F1" ezfName="xxRecHistCratByNm_F1" />
															<ezf:inputHidden name="xxRecHistUpdTs_F1" ezfName="xxRecHistUpdTs_F1" />
															<ezf:inputHidden name="xxRecHistUpdByNm_F1" ezfName="xxRecHistUpdByNm_F1" />
															<ezf:inputHidden name="xxRecHistTblNm_F1" ezfName="xxRecHistTblNm_F1" />
														</td>
														<td colspan="2"><ezf:inputButton name="GetCltCustTpNm" value=">>" htmlClass="pBtn0" />
															<ezf:inputText name="cltCustTpNm_F1" ezfName="cltCustTpNm_F1" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
														
													</tr>
													<tr>
														<td class="stab"><ezf:anchor name="xxLinkProt_F" ezfName="xxLinkProt_F4" ezfEmulateBtn="1" ezfGuard="OpenWin_CltPtfo" >Default Collector</ezf:anchor></td>
														<td><ezf:inputText name="cltPtfoCd_F1" ezfName="cltPtfoCd_F1" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
															<ezf:inputHidden name="xxRecHistCratTs_F1" ezfName="xxRecHistCratTs_F1" />
															<ezf:inputHidden name="xxRecHistCratByNm_F1" ezfName="xxRecHistCratByNm_F1" />
															<ezf:inputHidden name="xxRecHistUpdTs_F1" ezfName="xxRecHistUpdTs_F1" />
															<ezf:inputHidden name="xxRecHistUpdByNm_F1" ezfName="xxRecHistUpdByNm_F1" />
															<ezf:inputHidden name="xxRecHistTblNm_F1" ezfName="xxRecHistTblNm_F1" />
														</td>
														<td colspan="2">
															<ezf:inputButton name="GetCltPtfoNm" value=">>" htmlClass="pBtn0" />
															<ezf:inputText name="cltPtfoNm_F1" ezfName="cltPtfoNm_F1" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/>
														</td>
													</tr>
													<tr>
														<td colspan="4"><br /></td>
														
													</tr>
													<tr>
														<td class="stab">Account Status</td>
														<td colspan="3"><ezf:select name="dsCltAcctStsCd_P1" ezfName="dsCltAcctStsCd_P1" ezfBlank="1" ezfCodeName="dsCltAcctStsCd_F1" ezfDispName="dsCltAcctStsNm_F1" />
															<ezf:inputHidden name="xxRecHistCratTs_F1" ezfName="xxRecHistCratTs_F1" />
															<ezf:inputHidden name="xxRecHistCratByNm_F1" ezfName="xxRecHistCratByNm_F1" />
															<ezf:inputHidden name="xxRecHistUpdTs_F1" ezfName="xxRecHistUpdTs_F1" />
															<ezf:inputHidden name="xxRecHistUpdByNm_F1" ezfName="xxRecHistUpdByNm_F1" />
															<ezf:inputHidden name="xxRecHistTblNm_F1" ezfName="xxRecHistTblNm_F1" />
														</td>
													</tr>
													<tr>
														<td class="stab">Calculate Late Fee</td>
														<td colspan="3"><ezf:inputCheckBox name="lateFeeFlg_F1" ezfName="lateFeeFlg_F1" value="Y" onClick="sendServer('OnChange_lateFeeFlg')" />
															<ezf:inputHidden name="xxRecHistCratTs_F1" ezfName="xxRecHistCratTs_F1" />
															<ezf:inputHidden name="xxRecHistCratByNm_F1" ezfName="xxRecHistCratByNm_F1" />
															<ezf:inputHidden name="xxRecHistUpdTs_F1" ezfName="xxRecHistUpdTs_F1" />
															<ezf:inputHidden name="xxRecHistUpdByNm_F1" ezfName="xxRecHistUpdByNm_F1" />
															<ezf:inputHidden name="xxRecHistTblNm_F1" ezfName="xxRecHistTblNm_F1" />
														</td>
													</tr>
													<tr>
														<td class="stab">Minimum Balance to Calculate Late Fee</td>
														<td colspan="3"><ezf:inputText name="lateFeeAmt_F1" ezfName="lateFeeAmt_F1" otherAttr=" size=\"22\" maxlength=\"22\" ezftoupper=\"\""/>
															<ezf:inputHidden name="xxRecHistCratTs_F1" ezfName="xxRecHistCratTs_F1" />
															<ezf:inputHidden name="xxRecHistCratByNm_F1" ezfName="xxRecHistCratByNm_F1" />
															<ezf:inputHidden name="xxRecHistUpdTs_F1" ezfName="xxRecHistUpdTs_F1" />
															<ezf:inputHidden name="xxRecHistUpdByNm_F1" ezfName="xxRecHistUpdByNm_F1" />
															<ezf:inputHidden name="xxRecHistTblNm_F1" ezfName="xxRecHistTblNm_F1" />
														</td>
													</tr>
													<tr>
														<td class="stab">Monthly Late Fee Rate(%)</td>
														<td colspan="3"><ezf:inputText name="mlyLateFeeRate_F1" ezfName="mlyLateFeeRate_F1" otherAttr=" size=\"22\" maxlength=\"22\" ezftoupper=\"\""/>
															<ezf:inputHidden name="xxRecHistCratTs_F1" ezfName="xxRecHistCratTs_F1" />
															<ezf:inputHidden name="xxRecHistCratByNm_F1" ezfName="xxRecHistCratByNm_F1" />
															<ezf:inputHidden name="xxRecHistUpdTs_F1" ezfName="xxRecHistUpdTs_F1" />
															<ezf:inputHidden name="xxRecHistUpdByNm_F1" ezfName="xxRecHistUpdByNm_F1" />
															<ezf:inputHidden name="xxRecHistTblNm_F1" ezfName="xxRecHistTblNm_F1" />
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
										<td>
											<br />
										</td>
										<td valign="top">
											<fieldset style="height:90;">
												<legend>TAXING</legend>
												<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
													<col width="100">
													<col width="220">
													<tr>
														<td class="stab">Tax Exempt</td>
														<td colspan="3"><ezf:inputCheckBox name="dsTaxExemFlg_F1" ezfName="dsTaxExemFlg_F1" value="Y" /></td>
													</tr>
													<tr>
														<td class="stab">Exempt Exp Date</td>
														<td><ezf:inputText name="dsExemExprDt_F1" ezfName="dsExemExprDt_F1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsExemExprDt_F1', 4);"></td>
													</tr>
													<tr>
														<td class="stab">Tax Printing</td>
														<td><ezf:select name="dsTaxPrntTpCd_P1" ezfName="dsTaxPrntTpCd_P1" ezfBlank="1" ezfCodeName="dsTaxPrntTpCd_F1" ezfDispName="dsTaxPrntTpNm_F1" /></td>
													</tr>
												</table>
											</fieldset>
											<fieldset style="height:40;">
												<legend>RECEIPTS</legend>
												<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
													<col width="100">
													<col width="220">
													<tr>
														<td class="stab">Auto Cash Rule</td>
														<td><ezf:select name="autoCashHrchCd_P1" ezfName="autoCashHrchCd_P1" ezfBlank="1" ezfCodeName="autoCashHrchCd_F1" ezfDispName="autoCashHrchNm_F1" /></td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
								</table>
								<br />
							</div>
							</c:when>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'InvBllg'}">
							<script type="text/javascript">
								document.getElementById( "Financial" ).className = "pTab_OFF";
								document.getElementById( "InvBllg" ).className = "pTab_ON";
								document.getElementById( "Taxing" ).className = "pTab_OFF";
							</script>
							<div class="pTab_BODY_In" border="0" >
								<table border="0" cellpadding="1" cellspacing="0">
									<tr>
										<td colspan="2">
								<fieldset style="margin-left:10; width:1100">
									<legend>INVOICE GROUPING</legend>
									
										<table border="0" cellpadding="1" cellspacing="0">
											<col width="940">
											<col width="50">
											<col width="50">
											<tr>
												<td class="stab">&nbsp</td>
												<td><ezf:inputButton name="AddInvoiceSource" value="Add" htmlClass="pBtn4" /></td>
												<td><ezf:inputButton name="DeleteInvoiceSource" value="Delete" htmlClass="pBtn4" /></td>
											</tr>
										</table>
									<div id="LeftTable_A_Top" style="overflow:hidden; width:1070; display:block;">
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col align="center" width="25">		<!-- Check Box -->
												<col align="center" width="100">	<!-- Invoice Source -->
												<col align="center" width="120">	<!-- Bill Type -->
												<col align="center" width="120">	<!-- Consolidated Term -->
												<col align="center" width="130">	<!-- Bill Vehicle -->
												<col align="center" width="130">	<!-- Internal Email Review -->
												<col align="center" width="130">	<!-- Internal Email Review Name -->
												<col align="center" width="130">	<!-- External Email Contact -->
												<col align="center" width="130">	<!-- External Email Contact Name -->
												<col align="center" width="110">	<!-- Custom Email Subject -->
												<col align="center" width="160">	<!-- Grouping -->
												<col align="center" width="60">		<!-- Group# -->
												<tr>
													<td class="pClothBs"></td>
													<td class="pClothBs">Invoice Source</td>
													<td class="pClothBs">Bill Type</td>
													<td class="pClothBs">Consolidated Term</td>
													<td class="pClothBs">Bill Vehicle</td>
													<td class="pClothBs">Internal Email Review</td>
													<td class="pClothBs">Internal Email Review Name</td>
													<td class="pClothBs">External Email Contact</td>
													<td class="pClothBs">External Email Contact Name</td>
													<td class="pClothBs">Custom Email<br />Subject</td>
													<td class="pClothBs">Default Grouping</td>
													<td class="pClothBs">Invoice<br />Group#</td>
												</tr>
										</table>
									</div>
									<div id="LeftTable_A" style="overflow:scroll; height:120; width:1089; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'LeftTable_A_Top' ))">
										<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
														<col align="center" width="25">	<!-- Check Box -->
														<col align="center" width="100">	<!-- Invoice Source -->
														<col align="center" width="120">	<!-- Bill Type -->
														<col align="center" width="120">	<!-- Consolidated Term -->
														<col align="center" width="130">	<!-- Bill Vehicle -->
														<col align="center" width="130">	<!-- Internal Email Review -->
														<col align="center" width="130">	<!-- Internal Email Review Name -->
														<col align="center" width="130">	<!-- External Email Review -->
														<col align="center" width="130">	<!-- External Email Review Name -->
														<col align="center" width="110">	<!-- Custom Email Subject -->
														<col align="center" width="160">	<!-- Default Grouping -->
														<col align="center" width="60">		<!-- Invoice Group# -->
											<ezf:row ezfHyo="A">
														<tr id="id_leftG_row{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:select name="custInvSrcCd_P1" ezfName="custInvSrcCd_P1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="custInvSrcCd_A1" ezfDispName="custInvSrcNm_A1" ezfCodeDispHyo="A" otherAttr=" style=\"width: 95px\""/></td>
															<td><ezf:select name="custBllgTpCd_P1" ezfName="custBllgTpCd_P1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="custBllgTpCd_A1" ezfDispName="custBllgTpNm_A1" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServer('OnChange_CustBllgTp', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width: 115px\""/></td>
															<td><ezf:select name="custConslTermCd_P1" ezfName="custConslTermCd_P1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="pmtTermCashDiscCd_A1" ezfDispName="pmtTermCashDiscNm_A1" ezfCodeDispHyo="A" otherAttr=" style=\"width: 115px\""/></td>
															<td><ezf:select name="custBllgVcleCd_P1" ezfName="custBllgVcleCd_P1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="custBllgVcleCd_A1" ezfDispName="custBllgVcleDescTxt_A1" ezfCodeDispHyo="A" otherAttr=" style=\"width: 120px\""/></td>
															<td><ezf:inputText name="xxGenlFldAreaTxt_A1" ezfName="xxGenlFldAreaTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"200\""/>
																<ezf:inputButton name="OpenWin_Resrc" value="R" ezfHyo="G" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_Resrc{EZF_ROW_NUMBER}\" style=\"width=15px\""/>
															</td>
															<td><ezf:inputText name="xxCustInvRuleRcpntTxt_A1" ezfName="xxCustInvRuleRcpntTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\""/>
															<td><ezf:inputText name="xxGenlFldAreaTxt_A2" ezfName="xxGenlFldAreaTxt_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"400\""/>
																<ezf:inputButton name="OpenWin_CtacPsn" value="C" ezfHyo="G" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_CtacPsn{EZF_ROW_NUMBER}\" style=\"width=15px\""/>
															</td>
															<td><ezf:inputText name="xxCustInvRuleRcpntTxt_A2" ezfName="xxCustInvRuleRcpntTxt_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\""/>
															<td><ezf:inputText name="custEmlMsgTxt_A1" ezfName="custEmlMsgTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"240\""/></td>
															<td><ezf:select name="defInvGrpCd_P1" ezfName="defInvGrpCd_P1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="defInvGrpCd_A1" ezfDispName="defInvGrpNm_A1" ezfCodeDispHyo="A" otherAttr=" style=\"width: 155px\""/></td>
															<td><ezf:inputText name="invGrpNum_A1" ezfName="invGrpNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
															</td>
														</tr>
											</ezf:row>
											
										</table>
									</div>
								</fieldset>
							</td>
						</tr>
						<tr>
							<td>
								<fieldset style="margin-left:10; height:160; width:550;">
									<legend>CUSTOMER REFERENCE ATTRIBUTES</legend>
									<table border="0" cellpadding="1" cellspacing="0" align="center">
										<col valign="top" width="520">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col width="390">
													<col width="60">
													<col width="60">
													<tr>
														<td><br /></td>
														<td><ezf:inputButton name="AddAttribute" value="Add" htmlClass="pBtn4" /></td>
														<td><ezf:inputButton name="DeleteAttribute" value="Delete" htmlClass="pBtn4" /></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<div id="LeftTable_K_Top" style="overflow-x:none; overflow-y:none; width:515; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
															<col align="center" width="25">		<!-- Chek Box -->
															<col align="center" width="70">		<!-- Control -->
															<col align="center" width="150">	<!-- Billing Attribute Name -->
															<col align="center" width="150">	<!-- Default Value -->
															<col align="center" width="60">		<!-- Enabled -->
															<col align="center" width="60">		<!-- Required -->
															<tr>
																<td class="pClothBs"></td>							<!-- Chek Box -->
																<td class="pClothBs">Control</td>					<!-- Control -->
																<td class="pClothBs">Billing Attribute Name</td>	<!-- Billing Attribute Name -->
																<td class="pClothBs">Default Value</td>				<!-- Default Value -->
																<td class="pClothBs">Enabled</td>					<!-- Enabled -->
																<td class="pClothBs">Required</td>					<!-- Required -->
															</tr>
													</table>
												</div>
												<div id="LeftTable_K" style="overflow-y:scroll; height:98; overflow-x:hidden; width:534; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" id="K" style="table-layout: fixed">
															<col align="center" width="25">
															<col align="center" width="70">
															<col align="center" width="150">
															<col align="center" width="150">
															<col align="center" width="60">
															<col align="center" width="60">
														<ezf:row ezfHyo="K">
															<tr id="id_leftK_row{EZF_ROW_NUMBER}">
																<td><ezf:inputCheckBox name="xxChkBox_K1" ezfName="xxChkBox_K1" value="Y" ezfHyo="K" ezfArrayNo="0" /></td>
																<td><ezf:label name="xxCtlNm_K1" ezfName="xxCtlNm_K1" ezfHyo="K" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="bllgAttrbNm_K1" ezfName="bllgAttrbNm_K1" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
																<td><ezf:inputText name="bllgAttrbValTxt_K1" ezfName="bllgAttrbValTxt_K1" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
																<td><ezf:inputCheckBox name="bllgAttrbEnblFlg_K1" ezfName="bllgAttrbEnblFlg_K1" value="Y" ezfHyo="K" ezfArrayNo="0" /></td>
																<td><ezf:inputCheckBox name="bllgAttrbReqFlg_K1" ezfName="bllgAttrbReqFlg_K1" value="Y" ezfHyo="K" ezfArrayNo="0" /></td>
																<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_K1" ezfName="xxRecHistCratTs_K1" ezfHyo="K" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistCratByNm_K1" ezfName="xxRecHistCratByNm_K1" ezfHyo="K" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdTs_K1" ezfName="xxRecHistUpdTs_K1" ezfHyo="K" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdByNm_K1" ezfName="xxRecHistUpdByNm_K1" ezfHyo="K" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistTblNm_K1" ezfName="xxRecHistTblNm_K1" ezfHyo="K" ezfArrayNo="0" />
																</td>
															</tr>
														</ezf:row>
														<ezf:skip>
															<tr id="id_leftK_row{EZF_ROW_NUMBER}">
																<td><input type="checkbox" name="xxChkBox_K1" ezfname="xxChkBox_K1" value="Y" ezfhyo="K"></td>
																<td><label ezfout name="xxCtlNm_K1" ezfname="xxCtlNm_K1" ezfhyo="K">Control 1</label></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbNm_K1" ezfname="bllgAttrbNm_K1" ezfhyo="K"></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbValTxt_K1" ezfname="bllgAttrbValTxt_K1" ezfhyo="K"></td>
																<td><input type="checkbox" name="bllgAttrbEnblFlg_K1" ezfname="bllgAttrbEnblFlg_K1" ezfhyo="K" value="Y"></td>
																<td><input type="checkbox" name="bllgAttrbReqFlg_K1" ezfname="bllgAttrbReqFlg_K1" ezfhyo="K" value="Y"></td>
															</tr>
															<tr id="id_leftK_row{EZF_ROW_NUMBER}">
																<td><input type="checkbox" name="xxChkBox_K1" ezfname="xxChkBox_K1" value="Y" ezfhyo="K"></td>
																<td><label ezfout name="xxCtlNm_K1" ezfname="xxCtlNm_K1" ezfhyo="K">Control 1</label></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbNm_K1" ezfname="bllgAttrbNm_K1" ezfhyo="K"></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbValTxt_K1" ezfname="bllgAttrbValTxt_K1" ezfhyo="K"></td>
																<td><input type="checkbox" name="bllgAttrbEnblFlg_K1" ezfname="bllgAttrbEnblFlg_K1" ezfhyo="K" value="Y"></td>
																<td><input type="checkbox" name="bllgAttrbReqFlg_K1" ezfname="bllgAttrbReqFlg_K1" ezfhyo="K" value="Y"></td>
															</tr>
															<tr id="id_leftK_row{EZF_ROW_NUMBER}">
																<td><input type="checkbox" name="xxChkBox_K1" ezfname="xxChkBox_K1" value="Y" ezfhyo="K"></td>
																<td><label ezfout name="xxCtlNm_K1" ezfname="xxCtlNm_K1" ezfhyo="K">Control 1</label></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbNm_K1" ezfname="bllgAttrbNm_K1" ezfhyo="K"></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbValTxt_K1" ezfname="bllgAttrbValTxt_K1" ezfhyo="K"></td>
																<td><input type="checkbox" name="bllgAttrbEnblFlg_K1" ezfname="bllgAttrbEnblFlg_K1" ezfhyo="K" value="Y"></td>
																<td><input type="checkbox" name="bllgAttrbReqFlg_K1" ezfname="bllgAttrbReqFlg_K1" ezfhyo="K" value="Y"></td>
															</tr>
															<tr id="id_leftK_row{EZF_ROW_NUMBER}">
																<td><input type="checkbox" name="xxChkBox_K1" ezfname="xxChkBox_K1" value="Y" ezfhyo="K"></td>
																<td><label ezfout name="xxCtlNm_K1" ezfname="xxCtlNm_K1" ezfhyo="K">Control 1</label></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbNm_K1" ezfname="bllgAttrbNm_K1" ezfhyo="K"></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbValTxt_K1" ezfname="bllgAttrbValTxt_K1" ezfhyo="K"></td>
																<td><input type="checkbox" name="bllgAttrbEnblFlg_K1" ezfname="bllgAttrbEnblFlg_K1" ezfhyo="K" value="Y"></td>
																<td><input type="checkbox" name="bllgAttrbReqFlg_K1" ezfname="bllgAttrbReqFlg_K1" ezfhyo="K" value="Y"></td>
															</tr>
															<tr id="id_leftK_row{EZF_ROW_NUMBER}">
																<td><input type="checkbox" name="xxChkBox_K1" ezfname="xxChkBox_K1" value="Y" ezfhyo="K"></td>
																<td><label ezfout name="xxCtlNm_K1" ezfname="xxCtlNm_K1" ezfhyo="K">Control 1</label></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbNm_K1" ezfname="bllgAttrbNm_K1" ezfhyo="K"></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbValTxt_K1" ezfname="bllgAttrbValTxt_K1" ezfhyo="K"></td>
																<td><input type="checkbox" name="bllgAttrbEnblFlg_K1" ezfname="bllgAttrbEnblFlg_K1" ezfhyo="K" value="Y"></td>
																<td><input type="checkbox" name="bllgAttrbReqFlg_K1" ezfname="bllgAttrbReqFlg_K1" ezfhyo="K" value="Y"></td>
															</tr>
															<tr id="id_leftK_row{EZF_ROW_NUMBER}">
																<td><input type="checkbox" name="xxChkBox_K1" ezfname="xxChkBox_K1" value="Y" ezfhyo="K"></td>
																<td><label ezfout name="xxCtlNm_K1" ezfname="xxCtlNm_K1" ezfhyo="K">Control 1</label></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbNm_K1" ezfname="bllgAttrbNm_K1" ezfhyo="K"></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbValTxt_K1" ezfname="bllgAttrbValTxt_K1" ezfhyo="K"></td>
																<td><input type="checkbox" name="bllgAttrbEnblFlg_K1" ezfname="bllgAttrbEnblFlg_K1" ezfhyo="K" value="Y"></td>
																<td><input type="checkbox" name="bllgAttrbReqFlg_K1" ezfname="bllgAttrbReqFlg_K1" ezfhyo="K" value="Y"></td>
															</tr>
															<tr id="id_leftK_row{EZF_ROW_NUMBER}">
																<td><input type="checkbox" name="xxChkBox_K1" ezfname="xxChkBox_K1" value="Y" ezfhyo="K"></td>
																<td><label ezfout name="xxCtlNm_K1" ezfname="xxCtlNm_K1" ezfhyo="K">Control 1</label></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbNm_K1" ezfname="bllgAttrbNm_K1" ezfhyo="K"></td>
																<td><input type="text" size="20" maxlength="50" name="bllgAttrbValTxt_K1" ezfname="bllgAttrbValTxt_K1" ezfhyo="K"></td>
																<td><input type="checkbox" name="bllgAttrbEnblFlg_K1" ezfname="bllgAttrbEnblFlg_K1" ezfhyo="K" value="Y"></td>
																<td><input type="checkbox" name="bllgAttrbReqFlg_K1" ezfname="bllgAttrbReqFlg_K1" ezfhyo="K" value="Y"></td>
															</tr>
														</ezf:skip>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
							<td valign="top">
								<fieldset style="margin-left:10; width:350;">
									<legend>DEFAULT CONTRACT PREFERENCES</legend>
									<table border="0" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
										<col width="30">
										<col width="320">
										<tr>
											<td>&nbsp</td>
											<td>
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													<col width="150" align="center">
													<col width="150" align="center">
													<tr>
														<td class="pClothBs">Adv/Arrears</td>
														<td class="pClothBs">Cycle</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>Base</td>
											<td>
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													<col width="150">
													<col width="150">
													<tr>
														<td><ezf:select name="defBaseTpCd_P1" ezfName="defBaseTpCd_P1" ezfBlank="1" ezfCodeName="defBaseTpCd_I1" ezfDispName="defBaseTpNm_I1" otherAttr=" style=\"width:147px;\""/></td>
														<td><ezf:select name="defBaseCycleCd_P1" ezfName="defBaseCycleCd_P1" ezfBlank="1" ezfCodeName="defBaseCycleCd_I1" ezfDispName="defBaseCycleNm_I1" otherAttr=" style=\"width:147px;\""/></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>Usage</td>
											<td>
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													<col width="150">
													<col width="150">
													<tr>
														<td><ezf:select name="defUsgTpCd_P1" ezfName="defUsgTpCd_P1" ezfBlank="1" ezfCodeName="defUsgTpCd_I1" ezfDispName="defUsgTpNm_I1" otherAttr=" style=\"width:147px;\""/></td>
														<td><ezf:select name="defUsgCycleCd_P1" ezfName="defUsgCycleCd_P1" ezfBlank="1" ezfCodeName="defUsgCycleCd_I1" ezfDispName="defUsgCycleNm_I1" otherAttr=" style=\"width:147px;\""/></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<BR />
									<table border="0" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
										<col width="150">
										<col width="150">
										<tr>
											<td class="stab">Bill Base & Usage Together</td>
											<td><ezf:inputCheckBox name="dsBillTgtrFlg_I1" ezfName="dsBillTgtrFlg_I1" value="Y" /></td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
					<br />
							</div>
							</c:when>

							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Taxing'}">
							<script type="text/javascript">
								document.getElementById( "Financial" ).className = "pTab_OFF";
								document.getElementById( "InvBllg" ).className = "pTab_OFF";
								document.getElementById( "Taxing" ).className = "pTab_ON";
							</script>
							<div class="pTab_BODY_In">
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center" height="250" style="table-layout: fixed">
									<br/>
									<col width="100" valign="top">
									<col width="200" valign="top">
									<col width="500" valign="top">
									<tr>
										<td class="stab">Vertex Group Exemption</td>
										<td><ezf:select name="dsTaxGrpExemCd_P1" ezfName="dsTaxGrpExemCd_P1" ezfBlank="1" ezfCodeName="dsTaxGrpExemCd_F1" ezfDispName="dsTaxGrpExemNm_F1" /></td>
										<td></td>
									</tr>
								</table>
								<br />
							</div>
							</c:when>

							</c:choose>
							</td>
						</tr>

					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<%-- ###SCRIPT --%>
<script type="text/javascript">
	function onFocusEvent(item){
		if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
		}
	}

	function onBlurEvent(item){
		if(item.value.length === 1){
			item.value = '0' + item.value.charAt(0) + ':00';
		}else if(item.value.length === 2){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':00';
		}else if(item.value.length === 3){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + '0';
		}else if(item.value.length === 4){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}else if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}
	}
</script>

<%-- **** Task specific area ends here **** --%>
