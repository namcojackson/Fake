<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161125091305 --%>
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
			<input type="hidden" name="pageID" value="NFCL3140Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Invoice Type Setup screen">
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<!-- ######################################## HEADER ######################################## -->
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Invoice Type</a></li>
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
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center">
						<tr height="10">
							<td></td>
						</tr>
						<tr>
							<td valign="top">
								<fieldset style="display:inline-block; width:1110px; margin-top:10px;"><legend></legend>
								<table border="0" style="table-layout:fixed;">
									<col width="480">
									<col width="600">
									<tr>
										<td valign="top">
											<table border="0" style="table-layout:fixed;">
												<col width="5">
												<col width="130">
												<col width="340">
												<tr height="24">
													<td></td>
													<td class="stab"><ezf:anchor name="xxLinkProt_DI" ezfName="xxLinkProt_DI" ezfEmulateBtn="1" ezfGuard="OpenWin_DsInvoiceType" otherAttr=" tabindex=\"-1\"">Name</ezf:anchor></td>
													<td><ezf:inputText name="dsInvTpNm" ezfName="dsInvTpNm" value="INVOICE-SUPPLIES" otherAttr=" size=\"35\" maxlength=\"60\""/></td>
												</tr>
												<tr height="24">
													<td></td>
													<td class="stab">Description</td>
													<td><ezf:inputText name="dsInvTpDescTxt" ezfName="dsInvTpDescTxt" value="INVOICE FOR SUPPLIES ORDER" otherAttr=" size=\"35\" maxlength=\"50\""/></td>
												</tr>
												<tr height="24">
													<td></td>
													<td class="stab">Class</td>
													<td>
														<ezf:select name="invTpCd_SV" ezfName="invTpCd_SV" ezfBlank="1" ezfCodeName="invTpCd_CD" ezfDispName="invTpDescTxt_SC" otherEvent1=" onchange=\"sendServer('OnChange_Class')\"" otherAttr=" style=\"width:250px;\""/>
													</td>
												</tr>
												<tr height="24">
													<td></td>
													<td class="stab"><ezf:anchor name="xxLinkProt_AC" ezfName="xxLinkProt_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_AssociatedCreditType" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">Associated Credit Type</ezf:anchor></td>
													<td><ezf:inputText name="dsInvTpNm_AC" ezfName="dsInvTpNm_AC" value="CREDIT-SUPPLIES" otherAttr=" size=\"35\" maxlength=\"15\""/></td>
												</tr>
												<tr height="24">
													<td></td>
													<td class="stab">Invoice Source</td>
													<td><ezf:inputText name="invSrcTxt" ezfName="invSrcTxt" value="OM SUPPLIES" otherAttr=" size=\"35\" maxlength=\"25\""/></td>
												</tr>
											</table>
										</td>
										<td valign="top">
											<table border="0" style="table-layout:fixed;">
												<col width="5">
												<col width="20">
												<col width="130">
												<col width="10">
												<col width="90">
												<col width="90">
												<col width="10">
												<col width="60">
												<col width="100">
												<tr height="24">
													<td></td>
													<td><ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="Y" /></td>
													<td class="stab">Active</td>
													<td></td>
													<td></td>
													<td rowspan="6" colspan="4" valign="top">
														<fieldset style="display:inline-block; width:230px;">
															<legend>Grouping Attributed</legend>
															<div id='rightTbl' style="width:220px; height:74px; display:block; overflow-x:hidden; overflow-y:scroll; margin:0px; padding:0px;">
																<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="210px" >
																	<col width="120" align="center">	<!-- Status -->
																	<ezf:row ezfHyo="A">
																	<tr height="24px">
																		<td align="left" >
																			<ezf:select name="invGrpAttrbTxt_SV" ezfName="invGrpAttrbTxt_SV" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="invGrpAttrbTxt_CD" ezfDispName="invGrpAttrbTxt_SC" ezfCodeDispHyo="A" otherAttr=" style=\"width:200px;\""/>
																		</td>
																	</tr>
																	<ezf:skip>
																	<tr height="24px"><td align="left" ><select name="invGrpAttrbTxt_SV" ezfname="invGrpAttrbTxt_SV" ezflist="invGrpAttrbTxt_CD,invGrpAttrbTxt_SC,99, ,blank" style="width:200px;">><option></option><option>SHIP_TO_CUST_ACCT_CD</option><option>SHIP_TO_CUST_CD</option><option>SHIP_TO_CUST_CTAC_PSN_PK</option></select></td></tr>
																	<tr height="24px"><td align="left" ><select name="invGrpAttrbTxt_SV" ezfname="invGrpAttrbTxt_SV" ezflist="invGrpAttrbTxt_CD,invGrpAttrbTxt_SC,99, ,blank" style="width:200px;">><option></option><option>SHIP_TO_CUST_ACCT_CD</option><option>SHIP_TO_CUST_CD</option><option>SHIP_TO_CUST_CTAC_PSN_PK</option></select></td></tr>
																	</ezf:skip>
																	</ezf:row>
																</table>
															</div>
														</fieldset>
													</td>
												</tr>
												<tr height="24">
													<td></td>
													<td><ezf:inputCheckBox name="postToGlFlg" ezfName="postToGlFlg" value="Y" /></td>
													<td class="stab">Post To GL</td>
												</tr>
												<tr height="24">
													<td></td>
													<td><ezf:inputCheckBox name="openArFlg" ezfName="openArFlg" value="Y" /></td>
													<td class="stab">Open Receivables</td>
												</tr>
												<tr height="24">
													<td></td>
													<td><ezf:inputCheckBox name="taxCalcFlg" ezfName="taxCalcFlg" value="Y" /></td>
													<td class="stab">Tax Calculation</td>
												</tr>
												<tr height="24">
													<td></td>
													<td><ezf:inputCheckBox name="taxExemFlg" ezfName="taxExemFlg" value="Y" /></td>
													<td class="stab">Tax Exempt</td>
												</tr>
												<tr height="24">
													<td></td>
													<td><ezf:inputCheckBox name="invPrintFlg" ezfName="invPrintFlg" value="Y" /></td>
													<td class="stab">Print</td>
												</tr>
												<tr height="24">
													<td></td>
													<td><ezf:inputCheckBox name="autoInvNumFlg" ezfName="autoInvNumFlg" value="Y" /></td>
													<td class="stab">Auto Invoice Numbering</td>
													<td></td>
													<td class="stab">Auto Sequence</td>
													<td>
														<ezf:select name="autoSeqCd_SV" ezfName="autoSeqCd_SV" ezfBlank="1" ezfCodeName="autoSeqCd_CD" ezfDispName="autoSeqCd_SC" otherEvent1=" onchange=\"sendServer('OnChange_AutoSequence')\"" otherAttr=" style=\"width:80px;\""/>
													</td>
													<td></td>
													<td class="stab">Current #</td>
													<td><ezf:inputText name="extCurSqNum" ezfName="extCurSqNum" value="6001590" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								</fieldset>
							</td>
						</tr>
					</table>
					<br>
					<!-- ######################################## DETAIL ######################################## -->
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center">
						<tr height="10">
							<td></td>
						</tr>
						<tr>
							<td valign="top">
								<fieldset style="display:inline-block; width:1110px; margin-top:10px;">
									<legend>Default Accounting</legend>
									<table style="table-layout:fixed;margin-top:10px;margin-left:10px;margin-bottom:10px;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1068px" style="margin-right:20px">
										<col width="195">
										<col width="97">
										<col width="97">
										<col width="97">
										<col width="97">
										<col width="97">
										<col width="97">
										<col width="97">
										<col width="97">
										<col width="97">
										<tr height="30px" valign="middle">
											<td align="center" class="pClothBs">Account Type</td>
											<td align="center" class="pClothBs">Company</td>
											<td align="center" class="pClothBs">Branch</td>
											<td align="center" class="pClothBs">Cost Center</td>
											<td align="center" class="pClothBs">Account</td>
											<td align="center" class="pClothBs">Product</td>
											<td align="center" class="pClothBs">Channel</td>
											<td align="center" class="pClothBs">Intercompany</td>
											<td align="center" class="pClothBs">M'dise Type</td>
											<td align="center" class="pClothBs">Business Unit</td>
										</tr>
										<tr height="30px" valign="middle">
											<td style="border-right-style:none;">Receivable</td>
											<td><ezf:inputText name="arCoaCmpyCd" ezfName="arCoaCmpyCd" value="ADB" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="arCoaBrCd" ezfName="arCoaBrCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="arCoaCcCd" ezfName="arCoaCcCd" value="342040" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="arCoaAcctCd" ezfName="arCoaAcctCd" value="11111001" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="arCoaProdCd" ezfName="arCoaProdCd" value="00" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="arCoaChCd" ezfName="arCoaChCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="arCoaAfflCd" ezfName="arCoaAfflCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="arCoaProjCd" ezfName="arCoaProjCd" value="10" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="arCoaExtnCd" ezfName="arCoaExtnCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										</tr>
										<tr height="30px" valign="middle">
											<td style="border-right-style:none;">Revenue</td>
											<td><ezf:inputText name="slsCoaCmpyCd" ezfName="slsCoaCmpyCd" value="ADB" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="slsCoaBrCd" ezfName="slsCoaBrCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="slsCoaCcCd" ezfName="slsCoaCcCd" value="342040" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="slsCoaAcctCd" ezfName="slsCoaAcctCd" value="11111001" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="slsCoaProdCd" ezfName="slsCoaProdCd" value="00" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="slsCoaChCd" ezfName="slsCoaChCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="slsCoaAfflCd" ezfName="slsCoaAfflCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="slsCoaProjCd" ezfName="slsCoaProjCd" value="10" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="slsCoaExtnCd" ezfName="slsCoaExtnCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										</tr>
										<tr height="30px" valign="middle">
											<td style="border-right-style:none;">Tax</td>
											<td><ezf:inputText name="taxCoaCmpyCd" ezfName="taxCoaCmpyCd" value="ADB" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="taxCoaBrCd" ezfName="taxCoaBrCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="taxCoaCcCd" ezfName="taxCoaCcCd" value="342040" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="taxCoaAcctCd" ezfName="taxCoaAcctCd" value="11111001" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="taxCoaProdCd" ezfName="taxCoaProdCd" value="00" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="taxCoaChCd" ezfName="taxCoaChCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="taxCoaAfflCd" ezfName="taxCoaAfflCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="taxCoaProjCd" ezfName="taxCoaProjCd" value="10" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="taxCoaExtnCd" ezfName="taxCoaExtnCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										</tr>
										<tr height="30px" valign="middle">
											<td style="border-right-style:none;">Unearned Revenue</td>
											<td><ezf:inputText name="unEarnCoaCmpyCd" ezfName="unEarnCoaCmpyCd" value="ADB" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="unEarnCoaBrCd" ezfName="unEarnCoaBrCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="unEarnCoaCcCd" ezfName="unEarnCoaCcCd" value="342040" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="unEarnCoaAcctCd" ezfName="unEarnCoaAcctCd" value="11111001" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="unEarnCoaProdCd" ezfName="unEarnCoaProdCd" value="00" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="unEarnCoaChCd" ezfName="unEarnCoaChCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="unEarnCoaAfflCd" ezfName="unEarnCoaAfflCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="unEarnCoaProjCd" ezfName="unEarnCoaProjCd" value="10" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="unEarnCoaExtnCd" ezfName="unEarnCoaExtnCd" value="000" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
