<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20231113081233 --%>
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
			<input type="hidden" name="pageID" value="NMAL6720Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Add New Location">
			
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
										<li title="Location Info" class="pTab_ON"><a href="#">Add New Loc</a></li>
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
						<col width="360">
						<col width="600">
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="90">
									<col width="30">
									<col width="240">
									<tr height="20">
										<td class="stab" colspan="2">Account Name</td>
										<td><ezf:label name="dsAcctNm_H1" ezfName="dsAcctNm_H1" /></td>
									</tr>
									<tr height="8">
										<td>&nbsp</td>
										<td>&nbsp</td>
									</tr>
									<tr height="20">
										<td class="stab">Country</td>
										<td colspan="2">
											<ezf:select name="ctryCd_P1" ezfName="ctryCd_P1" ezfCodeName="ctryCd_H1" ezfDispName="ctryNm_H1" otherEvent1=" onchange=\"sendServer('Select_Country')\"" />
										</td>
									</tr>
									<tr height="8">
										<td><br /></td>
										<td><br /></td>
									</tr>
									<tr height="22">
										<td class="stab"><span>Address1</span></td>
										<td colspan="2"><ezf:inputText name="firstLineAddr_H1" ezfName="firstLineAddr_H1" otherAttr=" style=\"width: 255px\" maxlength=\"60\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="20">
										<td class="stab">Address2</td>
										<td colspan="2"><ezf:inputText name="scdLineAddr_H1" ezfName="scdLineAddr_H1" otherAttr=" style=\"width: 255px\" maxlength=\"60\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="20">
										<td class="stab">Address3</td>
										<td colspan="2"><ezf:inputText name="thirdLineAddr_H1" ezfName="thirdLineAddr_H1" otherAttr=" style=\"width: 255px\" maxlength=\"60\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="20">
										<td class="stab">Address4</td>
										<td colspan="2"><ezf:inputText name="frthLineAddr_H1" ezfName="frthLineAddr_H1" otherAttr=" style=\"width: 255px\" maxlength=\"60\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="6">
										<td><br /></td>
										<td><br /></td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="ctyAddr_H1" ezfName="ctyAddr_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_City" >City</ezf:anchor></td>
										<td colspan="2"><ezf:inputText name="ctyAddr_H1" ezfName="ctyAddr_H1" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="20">
										<td class="stab"><ezf:anchor name="stCd_P1" ezfName="stCd_P1" ezfEmulateBtn="1" ezfGuard="OpenWin_St" >State</ezf:anchor></td>
										<td colspan="2"><ezf:select name="stCd_P1" ezfName="stCd_P1" ezfBlank="1" ezfCodeName="stCd_H1" ezfDispName="stNm_H1" /></td>
									</tr>
									<tr height="22">
										<td class="stab"><ezf:anchor name="postCd_H1" ezfName="postCd_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_Post" >Postal Code</ezf:anchor></td>
										<td colspan="2">
											<ezf:inputText name="postCd_H1" ezfName="postCd_H1" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/>
											<ezf:inputButton name="ValidateAddress" value="1.Validate" htmlClass="pBtn4" otherAttr=" style=\"margin-left:15px;\""/>
											<ezf:inputButton name="GetAddress" value="2.Get" htmlClass="pBtn4" otherAttr=" style=\"margin-left:5px;\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab"><ezf:anchor name="cntyNm_H1" ezfName="cntyNm_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_Cnty" >County</ezf:anchor></td>
										<td colspan="2"><ezf:inputText name="cntyNm_H1" ezfName="cntyNm_H1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="20">
										<td class="stab">Province</td>
										<td colspan="2"><ezf:inputText name="provNm_H1" ezfName="provNm_H1" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="120">
									<col width="240">
									<col width="120">
									<col width="240">
									<tr height="20">
										<td class="stab">Location Number</td>
										<td><ezf:label name="locNum_H1" ezfName="locNum_H1" /></td>
									</tr>
									<tr>
										<td colspan="4" width="630">
											<fieldset>
												<legend>USAGE</legend>
												<table border="0" style="table-layout:fixed;">
													<col width="80">
													<col width="600">
													<tr>
														<td>
															<table border="0" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																<col width="50">
																<col width="30">
																<tr height="23">
																	<td><br /></td>
																	<td><br /></td>
																</tr>
																<tr height="23">
																	<td class="stab">Bill To</td>
																	<td><ezf:inputCheckBox name="dsAcctRelnBillToFlg_BI" ezfName="dsAcctRelnBillToFlg_BI" value="Y" onClick="sendServer('OnChange_BillTo')" />
																		<ezf:inputHidden name="xxRecHistCratTs_BI" ezfName="xxRecHistCratTs_BI" />
																		<ezf:inputHidden name="xxRecHistCratByNm_BI" ezfName="xxRecHistCratByNm_BI" />
																		<ezf:inputHidden name="xxRecHistUpdTs_BI" ezfName="xxRecHistUpdTs_BI" />
																		<ezf:inputHidden name="xxRecHistUpdByNm_BI" ezfName="xxRecHistUpdByNm_BI" />
																		<ezf:inputHidden name="xxRecHistTblNm_BI" ezfName="xxRecHistTblNm_BI" />
																	</td>
																</tr>
																<tr height="23">
																	<td class="stab">Ship To</td>
																	<td><ezf:inputCheckBox name="dsAcctRelnShipToFlg_SH" ezfName="dsAcctRelnShipToFlg_SH" value="Y" onClick="sendServer('OnChange_ShipTo')" />
																		<ezf:inputHidden name="xxRecHistCratTs_SH" ezfName="xxRecHistCratTs_SH" />
																		<ezf:inputHidden name="xxRecHistCratByNm_SH" ezfName="xxRecHistCratByNm_SH" />
																		<ezf:inputHidden name="xxRecHistUpdTs_SH" ezfName="xxRecHistUpdTs_SH" />
																		<ezf:inputHidden name="xxRecHistUpdByNm_SH" ezfName="xxRecHistUpdByNm_SH" />
																		<ezf:inputHidden name="xxRecHistTblNm_SH" ezfName="xxRecHistTblNm_SH" />
																	</td>
																</tr>
															</table>
														</td>
														<td>
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																<col width="105">
																<col width="105">
																<col width="100">
																<col width="235" align="left">
																<col width="50">
																<tr height="23">
																	<td align="center" class="pClothBs">Start Date</td>
																	<td align="center" class="pClothBs">End Date</td>
																	<td align="center" class="pClothBs">Code</td>
																	<td align="center" class="pClothBs">Related Bill To</td>
																	<td align="center" class="pClothBs">Primary</td>
																</tr>
																<%
																	String billToCustCd_BI = ((business.servlet.NMAL6720.NMAL6720Bean)databean).getBillToCustCd_BI();
																	String shipToCustCd_SH = ((business.servlet.NMAL6720.NMAL6720Bean)databean).getShipToCustCd_SH();
																	String billToCustCd_SH = ((business.servlet.NMAL6720.NMAL6720Bean)databean).getBillToCustCd_SH();
																%>
																<tr height="23">
																	<td><ezf:inputText name="effFromDt_BI" ezfName="effFromDt_BI" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_BI', 4);"></td>
																	<td><ezf:inputText name="effThruDt_BI" ezfName="effThruDt_BI" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_BI', 4);"></td>
																	<td>
																		<% if (billToCustCd_BI == null || billToCustCd_BI.length() == 0) { %>
																			&nbsp
																		<% } else { %>
																		<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="ShowBillToDetails" otherAttr=" tabindex=\"-1\""><ezf:label name="billToCustCd_BI" ezfName="billToCustCd_BI" /></ezf:anchor>
																		<% } %>
																	</td>
																	<td>&nbsp</td>
																	<td align="center"><ezf:inputCheckBox name="primUsgFlg_BI" ezfName="primUsgFlg_BI" value="Y" /></td>
																	<td class="pAuditInfo">
																		<ezf:inputHidden name="xxRecHistCratTs_BI" ezfName="xxRecHistCratTs_BI" />
																		<ezf:inputHidden name="xxRecHistCratByNm_BI" ezfName="xxRecHistCratByNm_BI" />
																		<ezf:inputHidden name="xxRecHistUpdTs_BI" ezfName="xxRecHistUpdTs_BI" />
																		<ezf:inputHidden name="xxRecHistUpdByNm_BI" ezfName="xxRecHistUpdByNm_BI" />
																		<ezf:inputHidden name="xxRecHistTblNm_BI" ezfName="xxRecHistTblNm_BI" />
																	</td>
																</tr>
																<tr height="23">
																	<td><ezf:inputText name="effFromDt_SH" ezfName="effFromDt_SH" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_SH', 4);"></td>
																	<td><ezf:inputText name="effThruDt_SH" ezfName="effThruDt_SH" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_SH', 4);"></td>
																	<td>
																		<% if (shipToCustCd_SH == null || shipToCustCd_SH.length() == 0) { %>
																			&nbsp
																		<% } else { %>
																		<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="ShowShipToDetails" otherAttr=" tabindex=\"-1\""><ezf:label name="shipToCustCd_SH" ezfName="shipToCustCd_SH" /></ezf:anchor>
																		<% } %>
																	</td>
																	<td>
																		<ezf:inputText name="billToCustCd_SH" ezfName="billToCustCd_SH" otherAttr=" size=\"10\" maxlength=\"7\" ezftoupper=\"\""/>
																		<ezf:inputButton name="OpenWin_BillToSearch" value="A" htmlClass="pBtn2" />
																		<ezf:inputButton name="ShowRelatedBillToDetails" value="Bill To Detail" htmlClass="pBtn2" />
																	</td>
																	<td align="center"><ezf:inputCheckBox name="primUsgFlg_SH" ezfName="primUsgFlg_SH" value="Y" /></td>
																	<td class="pAuditInfo">
																		<ezf:inputHidden name="xxRecHistCratTs_SH" ezfName="xxRecHistCratTs_SH" />
																		<ezf:inputHidden name="xxRecHistCratByNm_SH" ezfName="xxRecHistCratByNm_SH" />
																		<ezf:inputHidden name="xxRecHistUpdTs_SH" ezfName="xxRecHistUpdTs_SH" />
																		<ezf:inputHidden name="xxRecHistUpdByNm_SH" ezfName="xxRecHistUpdByNm_SH" />
																		<ezf:inputHidden name="xxRecHistTblNm_SH" ezfName="xxRecHistTblNm_SH" />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</fieldset>
										</td>
									</tr>
									<tr height="8">
										<td><br /></td>
										<td><br /></td>
									</tr>
									<tr height="20">
										<td class="stab">Location Name</td>
										<td><ezf:inputText name="dsLocNm_H1" ezfName="dsLocNm_H1" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td class="stab">DPL Screening Status</td>
										<td><ezf:select name="dplStsCd_P1" ezfName="dplStsCd_P1" ezfBlank="1" ezfCodeName="dplStsCd_H1" ezfDispName="dplStsNm_H1" /></td>
									</tr>
									<tr height="20">
										<td class="stab">Additional Name</td>
										<td><ezf:inputText name="addlLocNm" ezfName="addlLocNm" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
										<td class="stab">Embargo</td>
										<td><ezf:inputCheckBox name="embgoFlg_H1" ezfName="embgoFlg_H1" value="Y" /></td>
									</tr>
									<tr height="20">
										<td class="stab">Main Office Phone#</td>
										<td><ezf:inputText name="telNum_H1" ezfName="telNum_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td class="stab">Last Screened</td>
										<td><ezf:inputText name="xxDtTxt_H1" ezfName="xxDtTxt_H1" otherAttr=" size=\"14\" maxlength=\"14\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="20">
										<td class="stab">Primary Address</td>
										<td><ezf:inputCheckBox name="prinCustFlg_PR" ezfName="prinCustFlg_PR" value="Y" /></td>
										<td class="stab">DPL Screening Remarks</td>
										<td><ezf:inputText name="dplRsnTxt_H1" ezfName="dplRsnTxt_H1" otherAttr=" size=\"30\" maxlength=\"1000\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="21">
										<td class="stab">Active</td>
										<td colspaln="2">
											<ezf:inputCheckBox name="actvFlg_H1" ezfName="actvFlg_H1" value="Y" otherAttr=" size=\"20\""/>
											<ezf:inputButton name="OpenTrx_Download" value="Download Open Trx" htmlClass="pBtn10" />
										</td>
										<td class="stab"></td>
										<td></td>
									</tr>
									<tr height="20">
										<td class="stab">Source Business Unit</td>
										<td><ezf:select name="lineBizTpCd_P1" ezfName="lineBizTpCd_P1" ezfBlank="1" ezfCodeName="lineBizTpCd_H1" ezfDispName="lineBizTpNm_H1" /></td>
										<td><br /></td>
										<td><br /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
<%-- #################################################### DETAIL ################################################### --%>
<%
    String AccountlFlg = ((business.servlet.NMAL6720.NMAL6720Bean)databean).getXxTabProt_AC();
    String ClassificationsFlg = ((business.servlet.NMAL6720.NMAL6720Bean)databean).getXxTabProt_CL();
    String ContactsFlg = ((business.servlet.NMAL6720.NMAL6720Bean)databean).getXxTabProt_CT();
    String TransactionsFlg = ((business.servlet.NMAL6720.NMAL6720Bean)databean).getXxTabProt_TR();
    String SrvAttrbFlg = ((business.servlet.NMAL6720.NMAL6720Bean)databean).getXxTabProt_SA();
    String InstructionsFlg = ((business.servlet.NMAL6720.NMAL6720Bean)databean).getXxTabProt_IN();
    String ShippingFlg = ((business.servlet.NMAL6720.NMAL6720Bean)databean).getXxTabProt_SH();
%>
					<table width="100%">
						<col valign="top">
						<tr>
							<td>
								<div class="pTab_HEAD">
									<ul>
<%if(AccountlFlg.equals("Y")){%>
										<li class="pTab_ON"  id="Account" title="Account"><ezf:anchor name="xxTabProt_AC" ezfName="xxTabProt_AC" ezfEmulateBtn="1" ezfGuard="TAB_Account" >Account</ezf:anchor></li>
<%}%>
<%if(ClassificationsFlg.equals("Y")){%>
										<li class="pTab_OFF" id="Classifications" title="Classifications"><ezf:anchor name="xxTabProt_CL" ezfName="xxTabProt_CL" ezfEmulateBtn="1" ezfGuard="TAB_Classifications" >Classifications</ezf:anchor></li>
<%}%>
<%if(ContactsFlg.equals("Y")){%>
										<li class="pTab_OFF" id="Contacts" title="Contacts"><ezf:anchor name="xxTabProt_CT" ezfName="xxTabProt_CT" ezfEmulateBtn="1" ezfGuard="TAB_Contacts" >Contacts</ezf:anchor></li>
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
										<li class="pTab_OFF" id="Transactions" title="Transactions"><ezf:anchor name="xxTabProt_TR" ezfName="xxTabProt_TR" ezfEmulateBtn="1" ezfGuard="TAB_Transactions" >Transactions</ezf:anchor></li>
<%}%>
<%if(SrvAttrbFlg.equals("Y")){%>
										<li class="pTab_OFF" id="SrvAttrb" title="Service Attrb"><ezf:anchor name="xxTabProt_SA" ezfName="xxTabProt_SA" ezfEmulateBtn="1" ezfGuard="TAB_SrvAttrb" >Service Attrb</ezf:anchor></li>
<%}%>
<%if(InstructionsFlg.equals("Y")){%>
										<li class="pTab_OFF" id="Instructions" title="Instructions"><ezf:anchor name="xxTabProt_IN" ezfName="xxTabProt_IN" ezfEmulateBtn="1" ezfGuard="TAB_Instructions" >Instructions</ezf:anchor></li>
<%}%>
<%if(ShippingFlg.equals("Y")){%>
										<li class="pTab_OFF" id="Shipping" title="Shipping"><ezf:anchor name="xxTabProt_SH" ezfName="xxTabProt_SH" ezfEmulateBtn="1" ezfGuard="TAB_Shipping" >Shipping</ezf:anchor></li>
<%}%>
									</ul>
								</div>
								<c:choose>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Account'}">
								<script type="text/javascript">
<%if(AccountlFlg.equals("Y")){%>
									document.getElementById( "Account" ).className = "pTab_ON";
<%}%>
<%if(ClassificationsFlg.equals("Y")){%>
									document.getElementById( "Classifications" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
									document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
									document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(SrvAttrbFlg.equals("Y")){%>
									document.getElementById( "SrvAttrb" ).className = "pTab_OFF";
<%}%>
<%if(InstructionsFlg.equals("Y")){%>
									document.getElementById( "Instructions" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
									document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
								</script>
								<div class="pTab_BODY_In">
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col width="890">
													<col width="60">
													<col width="60">
													<tr>
														<td>Show Inactive<ezf:inputCheckBox name="xxChkBox_AI" ezfName="xxChkBox_AI" value="Y" onClick="sendServer('OnChange_ShowInactive')" /></td>
														<td><ezf:inputButton name="AddAccount" value="Add" htmlClass="pBtn4" /></td>
														<td><ezf:inputButton name="DeleteAccount" value="Delete" htmlClass="pBtn4" /></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<div id="LeftTableA_Top" style="overflow-x:none; overflow-y:none; width:1010; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
															<col align="center" width="30">	    <!-- Checkbox -->
															<col align="center" width="150">	<!-- Relationship Type -->
															<col align="center" width="200">	<!-- Related Acct# -->
															<col align="center" width="350">	<!-- Account Name -->
															<col align="center" width="100">	<!-- Start Date -->
															<col align="center" width="100">	<!-- End Date -->
															<col align="center" width="80">	    <!-- Status -->
															<tr>
																<td class="pClothBs"></td>
																<td class="pClothBs">Relationship Type</td>
																<td class="pClothBs">Related Acct#</td>
																<td class="pClothBs">Account Name</td>
																<td class="pClothBs">Start Date</td>
																<td class="pClothBs">End Date</td>
																<td class="pClothBs">Status</td>
															</tr>
													</table>
												</div>
												<div id="LeftTableA" style="overflow-y:scroll; height:198; overflow-x:hidden; width:1029; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
															<col width="30">		<!-- Checkbox -->
															<col width="150">		<!-- Relationship Type -->
															<col width="200">		<!-- Related Acct# -->
															<col width="350">		<!-- Account Name -->
															<col width="100">		<!-- Start Date -->
															<col width="100">		<!-- End Date -->
															<col width="80">		<!-- Status -->
														<ezf:row ezfHyo="A">
															<tr id="id_leftA_row{EZF_ROW_NUMBER}">
																<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="dsAcctRelnTpNm_A1" ezfName="dsAcctRelnTpNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td>
																	<ezf:inputButton name="OpenWin_Acct" value="R" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Acct{EZF_ROW_NUMBER}\""/>
																	<ezf:inputButton name="OpenWin_AcctSrch" value="A" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_AcctSrch{EZF_ROW_NUMBER}\""/>
																	<ezf:inputText name="dsAcctNum_A1" ezfName="dsAcctNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
																	<ezf:inputButton name="GetAcctNm" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"GetAcctNm{EZF_ROW_NUMBER}\""/>
																</td>
																<td><ezf:label name="dsAcctNm_A1" ezfName="dsAcctNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effFromDt_A1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1{EZF_ROW_NUMBER}', 4);"></td>
																<td><ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effThruDt_A1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1{EZF_ROW_NUMBER}', 4);"></td>
																<td><ezf:label name="dsAcctStsNm_A1" ezfName="dsAcctStsNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
															</tr>
														</ezf:row>
														<ezf:skip>
															<tr id="id_leftA_row{EZF_ROW_NUMBER}">
																<td><input type="checkbox" name="xxChkBox_A1" value="Y"></td>
																<td><label ezfout name="dsAcctRelnTpNm_A1">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><a href="#" onclick="sendServer('ShowContactDetails')" tabindex="-1">WWWWWWWWW1WWWWWWWWW2</a></td>
																<td><label ezfout name="dsAcctNm_A1">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><label ezfout name="effFromDt_A1">2014/01/22</label></td>
																<td><input type="text" size="10" maxlength="10"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4);"></td>
																<td><label ezfout name="dsAcctStsNm_A1">WWWWWWWW</label></td>
															</tr>
															<tr id="id_leftA_row{EZF_ROW_NUMBER}">
																<td><input type="checkbox" name="xxChkBox_A1" value="Y"></td>
																<td><label ezfout name="dsAcctNm_A1">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><label ezfout name="dsAcctRelnTpNm_A1">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><a href="#" onclick="sendServer('ShowContactDetails')" tabindex="-1">WWWWWWWWW1WWWWWWWWW2</a></td>
																<td><label ezfout name="effFromDt_A1">2014/01/22</label></td>
																<td><input type="text" size="10" maxlength="10"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4);"></td>
																<td><label ezfout name="dsAcctStsNm_A1">WWWWWWWW</label></td>
															</tr>
														</ezf:skip>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</div>
								</c:when>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Classifications'}">
								<script type="text/javascript">
<%if(AccountlFlg.equals("Y")){%>
									document.getElementById( "Account" ).className = "pTab_OFF";
<%}%>
<%if(ClassificationsFlg.equals("Y")){%>
									document.getElementById( "Classifications" ).className = "pTab_ON";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
									document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
									document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(SrvAttrbFlg.equals("Y")){%>
									document.getElementById( "SrvAttrb" ).className = "pTab_OFF";
<%}%>
<%if(InstructionsFlg.equals("Y")){%>
									document.getElementById( "Instructions" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
									document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
								</script>
								<div class="pTab_BODY_In" border="0" >
									<div style="OVERFLOW-Y:scroll;height:250px" >
										<table border="0" cellpadding="1" cellspacing="0"  style="margin-left:20;">
											<col valign="top" width="300">
											<col valign="top" width="40">
											<col valign="top" width="720">
											<tr>
												<td>
													<table border="0" cellpadding="1" cellspacing="0">
														<col width="140">
														<col width="200">
														<tr>
															<td class="stab">Global Location#</td>
															<td><ezf:inputText name="glnNum_H1" ezfName="glnNum_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">DUNS#</td>
															<td><ezf:inputText name="dunsNum_H1" ezfName="dunsNum_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">Ultimate DUNS#</td>
															<td><ezf:inputText name="dsUltDunsNum_H1" ezfName="dsUltDunsNum_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">HQ DUNS#</td>
															<td><ezf:inputText name="hqDunsNum_H1" ezfName="hqDunsNum_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">Parent DUNS#</td>
															<td><ezf:inputText name="dsPrntDunsNum_H1" ezfName="dsPrntDunsNum_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab"># of Employees</td>
															<td><ezf:inputText name="dsLocEmpNum_H1" ezfName="dsLocEmpNum_H1" otherAttr=" size=\"20\" maxlength=\"10\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">Annual Revenue</td>
															<td><ezf:inputText name="dsLocRevAmt_H1" ezfName="dsLocRevAmt_H1" otherAttr=" size=\"20\" maxlength=\"22\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">SIC Code</td>
															<td><ezf:inputText name="dsCustSicCd_H1" ezfName="dsCustSicCd_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">Industry</td>
															<td><ezf:inputText name="dsCustSicDescTxt_H1" ezfName="dsCustSicDescTxt_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">DNB Trade Style Name</td>
															<td><ezf:inputText name="dunsTradeStyleNm_H1" ezfName="dunsTradeStyleNm_H1" otherAttr=" size=\"20\" maxlength=\"100\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">DNB BEMFAB Code</td>
															<td><ezf:inputText name="dunsActvCd_H1" ezfName="dunsActvCd_H1" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">DNB Address</td>
															<td><ezf:inputText name="dunsLineAddr_H1" ezfName="dunsLineAddr_H1" otherAttr=" size=\"20\" maxlength=\"240\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">Last DNB Update Date</td>
															<td><ezf:inputText name="dsLastUpdDunsDt_H1" ezfName="dsLastUpdDunsDt_H1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">DNB Business Name</td>
															<td><ezf:inputText name="dunsBizNm_H1" ezfName="dunsBizNm_H1" otherAttr=" size=\"20\" maxlength=\"150\" ezftoupper=\"\""/></td>
														</tr>
														<tr>
															<td class="stab">DNB Identifier</td>
															<td><ezf:inputText name="dsLastRcvDunsTxt_H1" ezfName="dsLastRcvDunsTxt_H1" otherAttr=" size=\"20\" maxlength=\"120\" ezftoupper=\"\""/></td>
														</tr>
													</table>
												</td>
												<td>
													<br />
												</td>
												<td>
													<fieldset style="width:300;">
														<legend>TAX</legend>
														<table border="0" cellpadding="1" cellspacing="0">
															<col width="100">
															<col width="200">
															<tr>
																<td class="stab">Inside City Limits</td>
																<td><ezf:inputCheckBox name="dsInsdCtyLimitFlg_H1" ezfName="dsInsdCtyLimitFlg_H1" value="Y" /></td>
															</tr>
															<tr>
																<td class="stab"><ezf:anchor name="geoCd_L1" ezfName="geoCd_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_GeoCdSearch" otherAttr=" id=\"geoCd_L1\" ezfanchortext=\"\"">Geo Override</ezf:anchor></td>
																<td><ezf:inputText name="geoCd_H1" ezfName="geoCd_H1" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
															</tr>
														</table>
													</fieldset>
													<fieldset>
														<legend>ACCOUNT CROSS REFERENCES</legend>
														<table border="0" cellpadding="1" cellspacing="0" width="95%">
															<col align="right">
															<tr>
																<td><ezf:inputButton name="AddRef" value="Add" htmlClass="pBtn4" />
																	<ezf:inputButton name="DeleteRef" value="Delete" htmlClass="pBtn4" />
																</td>
															</tr>
														</table>
														<div id="LeftTableB_Top" style="overflow-x:none; overflow-y:none; width:680; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																	<col align="center" width="30">	<!-- Cross Reference Type -->
																	<col align="center" width="150">	<!-- Cross Reference Type -->
																	<col align="center" width="250">	<!-- Account Name -->
																	<col align="center" width="150">	<!-- Ref# -->
																	<col align="center" width="100">	<!-- Last Update Date -->
																	<tr>
																		<td class="pClothBs"></td>
																		<td class="pClothBs">Cross Reference Type</td>
																		<td class="pClothBs">Account Name</td>
																		<td class="pClothBs">Ref#</td>
																		<td class="pClothBs">Last Update Date</td>
																	</tr>
															</table>
														</div>
														<div id="LeftTableB" style="overflow-y:scroll; height:125; overflow-x:hidden; width:700; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed">
																	<col width="30">		<!-- Checkbox -->
																	<col width="150">		<!-- Cross Reference Type -->
																	<col width="250">		<!-- Account Name -->
																	<col width="150">		<!-- Ref# -->
																	<col width="100">		<!-- Last Update Date -->
																<ezf:row ezfHyo="B">
																	<tr>
																		<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B1{EZF_ROW_NUMBER}\""/></td>
																		<td><ezf:select name="dsXrefAcctTpCd_P1" ezfName="dsXrefAcctTpCd_P1" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsXrefAcctTpCd_B1" ezfDispName="dsXrefAcctTpNm_B1" otherAttr=" style=\"width:130px;\""/></td>
																		<td><ezf:inputText name="dsXrefAcctNm_B1" ezfName="dsXrefAcctNm_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"33\" maxlength=\"360\" ezftoupper=\"\""/></td>
																		<td><ezf:inputText name="dsXrefAcctCd_B1" ezfName="dsXrefAcctCd_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
																		<td><ezf:label name="lastUpdDt_B1" ezfName="lastUpdDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td class="pAuditInfo">
																			<ezf:inputHidden name="xxRecHistCratTs_B1" ezfName="xxRecHistCratTs_B1" ezfHyo="B" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistCratByNm_B1" ezfName="xxRecHistCratByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdTs_B1" ezfName="xxRecHistUpdTs_B1" ezfHyo="B" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdByNm_B1" ezfName="xxRecHistUpdByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistTblNm_B1" ezfName="xxRecHistTblNm_B1" ezfHyo="B" ezfArrayNo="0" />
																		</td>
																	</tr>
																</ezf:row>
																<ezf:skip>
																	<tr id="id_leftB_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_B1" ezfname="xxChkBox_B1" value="Y" ezfhyo="B"></td>
																		<td><select name="dsAcctRelnTpCd_B3" ezfname="dsAcctRelnTpCd_B3" ezflist="dsAcctRelnTpCd_B1,dsAcctRelnTpNm_B2,99, B,blank" ezfhyo="B"><option>CUSA</option><option>CFS</option></select></td>
																		<td><label ezfout name="dsAcctNm_B1" ezfname="dsAcctNm_B1" ezfhyo="B">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																		<td><label ezfout name="effFromDt_B1" ezfname="effFromDt_B1" ezfhyo="B">WWWWWWWWW1WWWWWWWWW2</label></td>
																		<td><label ezfout name="effThruDt_B1" ezfname="effThruDt_B1" ezfhyo="B">2014/01/22</label></td>
																	</tr>
																	<tr id="id_leftB_row{EZF_ROW_NUMBER}">
																		<td><input type="checkbox" name="xxChkBox_B1" ezfname="xxChkBox_B1" value="Y" ezfhyo="B"></td>
																		<td><select name="dsAcctRelnTpCd_B3" ezfname="dsAcctRelnTpCd_B3" ezflist="dsAcctRelnTpCd_B1,dsAcctRelnTpNm_B2,99, B,blank" ezfhyo="B"><option>CUSA</option><option>CFS</option></select></td>
																		<td><label ezfout name="dsAcctNm_B1" ezfname="dsAcctNm_B1" ezfhyo="B">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																		<td><label ezfout name="effFromDt_B1" ezfname="effFromDt_B1" ezfhyo="B">WWWWWWWWW1WWWWWWWWW2</label></td>
																		<td><label ezfout name="effThruDt_B1" ezfname="effThruDt_B1" ezfhyo="B">2014/01/22</label></td>
																	</tr>
																</ezf:skip>
															</table>
														</div>
													</fieldset>
												</td>
											</tr>
										</table>
									</div>
								</div>
								</c:when>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Contacts'}">
								<script type="text/javascript">
<%if(AccountlFlg.equals("Y")){%>
									document.getElementById( "Account" ).className = "pTab_OFF";
<%}%>
<%if(ClassificationsFlg.equals("Y")){%>
									document.getElementById( "Classifications" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
									document.getElementById( "Contacts" ).className = "pTab_ON";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
									document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(SrvAttrbFlg.equals("Y")){%>
									document.getElementById( "SrvAttrb" ).className = "pTab_OFF";
<%}%>
<%if(InstructionsFlg.equals("Y")){%>
									document.getElementById( "Instructions" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
									document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
								</script>
								<div class="pTab_BODY_In" border="0" >
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="210">
													<col width="150">
													<col width="75">
													<col width="75">
													<col width="75">
													<col width="570" align="right">
													<tr>
														<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
														<td>Show Inactive<ezf:inputCheckBox name="xxChkBox_CI" ezfName="xxChkBox_CI" value="Y" onClick="sendServer('OnChange_ShowInactive')" /></td>
														<td><ezf:inputButton name="CreateContact" value="Create" htmlClass="pBtn4" /></td>
														<td><ezf:inputButton name="OpenWin_CtacSearch" value="Filter" htmlClass="pBtn4" /></td>
														<td><ezf:inputButton name="OpenWin_UploadContact" value="Upload" htmlClass="pBtn4" /></td>
														<td>
															<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="C" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum_C" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum_C" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum_C" />
																<jsp:param name="ShowingCurrent" value="xxPageShowCurNum_C" />
																<jsp:param name="ShowingTotal"   value="xxPageShowTotNum_C" />
																<jsp:param name="ViewMode"       value="FULL" />
															</jsp:include>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
											<div id="parentBoxC">
												<div style="float:left; display:block"><!-- left table -->
													<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
													<div id="leftTbl" style="float:left; display:block; height:180px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
												</div><!-- left table -->
												<div style="float:left"><!-- right table -->
													<div id="rightTblHead" style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
														<table border="1" cellpadding="1" cellspacing="0" id="CHEAD" width="1100px" style="table-layout: fixed; margin-right:20px">
																<col align="center" width="80">		<!-- Contact ID -->
																<col align="center" width="100">	<!-- Relationship to CSA -->
																<col align="center" width="100">	<!-- First Name -->
																<col align="center" width="100">	<!-- Last Name -->
																<col align="center" width="160">	<!-- Dept -->
																<col align="center" width="90">		<!-- Email-Work -->
																<col align="center" width="90">		<!-- Phone-Work -->
																<col align="center" width="40">		<!-- Ext -->
																<col align="center" width="100">	<!-- START DATE -->
																<col align="center" width="100">	<!-- END DATE -->
																<col align="center" width="60">		<!-- Primary -->
																<col align="center" width="60">		<!-- Active -->
																<tr height="35">
																	<td class="pClothBs" id="CH0">Contact ID</td>
																	<td class="pClothBs" id="CH1">Relationship To CSA</td>
																	<td class="pClothBs" id="CH2">First Name</td>
																	<td class="pClothBs" id="CH3">Last Name</td>
																	<td class="pClothBs" id="CH4">Department</td>
																	<td class="pClothBs" id="CH5">Email-Work</td>
																	<td class="pClothBs" id="CH6">Phone-Work</td>
																	<td class="pClothBs" id="CH7">Ext</td>
																	<td class="pClothBs" id="CH8">Start Date</td>
																	<td class="pClothBs" id="CH9">End Date</td>
																	<td class="pClothBs" id="CH10">Primary</td>
																	<td class="pClothBs" id="CH11">Active</td>
																</tr>
														</table>
													</div>
													<div id="rightTbl" style="width:1103px; height:180px; display:block; overflow:scroll; margin:0px; padding:0px;" >
														<table border="1" cellpadding="1" cellspacing="0" id="C" style="table-layout: fixed">
																<col width="80">		<!-- Contact ID -->
																<col width="100">		<!-- Relationship To CSA-->
																<col width="100">		<!-- First Name -->
																<col width="100">		<!-- Last Name -->
																<col width="160">		<!-- Dept -->
																<col width="90">		<!-- Email-Work -->
																<col width="90">		<!-- Phone-Work -->
																<col width="40">		<!-- Ext -->
																<col width="100">		<!-- START DATE -->
																<col width="100">		<!-- END DATE -->
																<col align="center" width="60">		<!-- Primary -->
																<col align="center" width="60">		<!-- Active -->
															<ezf:row ezfHyo="C">
																<tr height="24" id="id_leftC_row{EZF_ROW_NUMBER}">
																	<!-- Mod Start 2018/01/22 Hd.Sugawara QC#20291(Sol#348) -->
																	<!-- <td><a href="#" ezfhyo="C" onclick="sendServer('ShowContactDetails')" tabindex="-1"><label ezfout ezfname="ctacPsnNum_C1" name="ctacPsnNum_C1"  ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></a></td> -->
																	<td><ezf:anchor name="ctacPsnPk_C1" ezfName="ctacPsnPk_C1" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ShowContactDetails" otherAttr=" tabindex=\"-1\""><ezf:label name="ctacPsnNum_C1" ezfName="ctacPsnNum_C1" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor></td>
																	<!-- Mod End   2018/01/22 Hd.Sugawara QC#20291(Sol#348) -->
																	<!-- Add Start 2017/12/06 Hd.Sugawara QC#21897 -->
																	<!-- <td><input type="text" name="ctacTpNm_C1" ezfName="ctacTpNm_C1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="C" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td> -->
																	<td><ezf:inputText name="ctacTpDescTxt_C1" ezfName="ctacTpDescTxt_C1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<!-- Add End   2017/12/06 Hd.Sugawara QC#21897 -->
																	<td><ezf:inputText name="ctacPsnFirstNm_C1" ezfName="ctacPsnFirstNm_C1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:inputText name="ctacPsnLastNm_C1" ezfName="ctacPsnLastNm_C1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:inputText name="dsCtacPsnDeptNm_C1" ezfName="dsCtacPsnDeptNm_C1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"18\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:inputText name="dsCtacPntValTxt_C2" ezfName="dsCtacPntValTxt_C2" value="WWWWWWWWW@WWWWWWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:inputText name="dsCtacPntValTxt_C1" ezfName="dsCtacPntValTxt_C1" value="1234567890" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:inputText name="dsCtacPsnExtnNum_C1" ezfName="dsCtacPsnExtnNum_C1" value="WWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"5\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:label name="effFromDt_C1" ezfName="effFromDt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																	<td><ezf:label name="effThruDt_C1" ezfName="effThruDt_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																	<td><ezf:inputCheckBox name="dsPrimLocFlg_C1" ezfName="dsPrimLocFlg_C1" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
																	<td><ezf:label name="dsAcctStsNm_C1" ezfName="dsAcctStsNm_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																	<td class="pAuditInfo">
																		<ezf:inputHidden name="xxRecHistCratTs_C1" ezfName="xxRecHistCratTs_C1" ezfHyo="C" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistCratByNm_C1" ezfName="xxRecHistCratByNm_C1" ezfHyo="C" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistUpdTs_C1" ezfName="xxRecHistUpdTs_C1" ezfHyo="C" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistUpdByNm_C1" ezfName="xxRecHistUpdByNm_C1" ezfHyo="C" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistTblNm_C1" ezfName="xxRecHistTblNm_C1" ezfHyo="C" ezfArrayNo="0" />
																	</td>
																</tr>
															</ezf:row>
															<ezf:skip>
																<tr height="24" id="id_leftC_row{EZF_ROW_NUMBER}">
																	<td><label ezfout name="svcInvLineNum_C1" ezfname="svcInvLineNum_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<!-- Add Start 2017/12/06 Hd.Sugawara QC#21897 -->
																	<!-- <td><label ezfout name="ctacTpNm_C1" ezfname="ctacTpNm_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td> -->
																	<td><label ezfout name="ctacTpDescTxt_C1" ezfname="ctacTpDescTxt_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<!-- Add End   2017/12/06 Hd.Sugawara QC#21897 -->
																	<td><label ezfout name="addr_C1" ezfname="addr_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<td><label ezfout name="addr2_C1" ezfname="addr2_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<td><label ezfout name="dsCtacPsnDeptNm_C1" ezfname="dsCtacPsnDeptNm_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<td><label ezfout ezfname="dsCtacPntValTxt_C2" name="dsCtacPntValTxt_C2"  ezfhyo="C">WWWWWWWWW@WWWWWWWWW</label></td>
																	<td><label ezfout name="dsCtacPntValTxt_C1" ezfname="dsCtacPntValTxt_C1" ezfhyo="C">1234567890</label></td>
																	<td><label ezfout name="dsCtacPsnExtnNum_C1" ezfname="dsCtacPsnExtnNum_C1" ezfhyo="C">WWWW</label></td>
																	<td><label ezfout name="effFromDt_C1" ezfname="effFromDt_C1" ezfhyo="C">9999/12/31</label></td>
																	<td><label ezfout name="effThruDt_C1" ezfname="effThruDt_C1" ezfhyo="C">9999/12/31</label></td>
																	<td><input type="checkbox" name="xxChkBox_C1" ezfname="xxChkBox_C1" value="Y"></td>
																	<td><label ezfout name="dsCtacPntActvFlg_C1" ezfname="dsCtacPntActvFlg_C1" ezfhyo="C">Active</label></td>
																</tr>
																<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																	<td><label ezfout name="svcInvLineNum_C1" ezfname="svcInvLineNum_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<!-- Add Start 2017/12/06 Hd.Sugawara QC#21897 -->
																	<!-- <td><label ezfout name="ctacTpNm_C1" ezfname="ctacTpNm_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td> -->
																	<td><label ezfout name="ctacTpDescTxt_C1" ezfname="ctacTpDescTxt_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<!-- Add End   2017/12/06 Hd.Sugawara QC#21897 -->
																	<td><label ezfout name="addr_C1" ezfname="addr_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<td><label ezfout name="addr2_C1" ezfname="addr2_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<td><label ezfout name="dsCtacPsnDeptNm_C1" ezfname="dsCtacPsnDeptNm_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
																	<td><label ezfout ezfname="dsCtacPntValTxt_C2" name="dsCtacPntValTxt_C2"  ezfhyo="C">WWWWWWWWW@WWWWWWWWW</label></td>
																	<td><label ezfout name="dsCtacPntValTxt_C1" ezfname="dsCtacPntValTxt_C1" ezfhyo="C">1234567890</label></td>
																	<td><label ezfout name="dsCtacPsnExtnNum_C1" ezfname="dsCtacPsnExtnNum_C1" ezfhyo="C">WWWW</label></td>
																	<td><label ezfout name="effFromDt_C1" ezfname="effFromDt_C1" ezfhyo="C">9999/12/31</label></td>
																	<td><label ezfout name="effThruDt_C1" ezfname="effThruDt_C1" ezfhyo="C">9999/12/31</label></td>
																	<td><input type="checkbox" name="xxChkBox_C1" ezfname="xxChkBox_C1" value="Y"></td>
																	<td><label ezfout name="dsCtacPntActvFlg_C1" ezfname="dsCtacPntActvFlg_C1" ezfhyo="C">Active</label></td>
																</tr>
															</ezf:skip>
														</table>
													</div>
												</div><!-- right table -->
												<script type="text/javascript" defer>
													S21TableUI.initialize("parentBoxC", "CHEAD", "C", -1, true, true);
												</script>
											</td>
										</tr>
									</table>
								</div>
								</c:when>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Transactions'}">
								<script type="text/javascript">
<%if(AccountlFlg.equals("Y")){%>
									document.getElementById( "Account" ).className = "pTab_OFF";
<%}%>
<%if(ClassificationsFlg.equals("Y")){%>
									document.getElementById( "Classifications" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
									document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
									document.getElementById( "Transactions" ).className = "pTab_ON";
<%}%>
<%if(SrvAttrbFlg.equals("Y")){%>
									document.getElementById( "SrvAttrb" ).className = "pTab_OFF";
<%}%>
<%if(InstructionsFlg.equals("Y")){%>
									document.getElementById( "Instructions" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
									document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
								</script>
								<div class="pTab_BODY_In" border="0" >
									<table border="0" cellpadding="1" cellspacing="0" style="margin-left:10;">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" width="98%">
													<col align="right">
													<tr>
														<td><ezf:inputButton name="AddTransaction" value="Add" htmlClass="pBtn4" />
															<ezf:inputButton name="DeleteTransaction" value="Delete" htmlClass="pBtn4" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td align="left" valign="top">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																<col align="center" width="50">		<!-- Checkbox -->
																<col align="center" width="120">	<!-- Transaction Type -->
																<col align="center" width="80">		<!-- PO Required -->
																<col align="center" width="160">	<!-- Blanket PO# -->
																<col align="center" width="110">	<!-- PO Exp Date -->
																<col align="center" width="150">	<!-- Default Bill To -->
																<col align="center" width="150">	<!-- Default Ship To -->
																<col align="center" width="80">		<!-- Credit Card Reqd -->
																<col align="center" width="80">		<!-- Overnight Allowed -->
																<tr>
																	<td class="pClothBs"></td>
																	<td class="pClothBs">Transaction Type</td>
																	<td class="pClothBs">PO Required</td>
																	<td class="pClothBs">Blanket PO#</td>
																	<td class="pClothBs">PO Exp Date</td>
																	<td class="pClothBs">Default Bill To</td>
																	<td class="pClothBs">Default Ship To</td>
																	<td class="pClothBs">Credit Card<br />Reqd</td>
																	<td class="pClothBs">Overnight Allowed</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr align="left">
														<td align="left" valign="top">
															<div style="overflow-y:scroll; height:190; width:990;">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="D">
																	<col align="center" width="50">	    <!-- Checkbox -->
																	<col align="center" width="120">	<!-- Transaction Type -->
																	<col align="center" width="80">		<!-- PO Required -->
																	<col align="center" width="160">	<!-- Blanket PO# -->
																	<col align="center" width="110">	<!-- PO Exp Date -->
																	<col width="150">	<!-- Default Bill To -->
																	<col width="150">	<!-- Default Ship To -->
																	<col align="center" width="80">		<!-- Credit Card Reqd -->
																	<col align="center" width="80">		<!-- Overnight Allowed -->
																	<ezf:row ezfHyo="D">
																		<tr>
																			<td><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="D" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_D1{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:select name="dsTrxRuleTpCd_P1" ezfName="dsTrxRuleTpCd_P1" ezfHyo="D" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsTrxRuleTpCd_D1" ezfDispName="dsTrxRuleTpNm_D1" /></td>
																			<td><ezf:inputCheckBox name="dsPoReqFlg_D1" ezfName="dsPoReqFlg_D1" value="Y" ezfHyo="D" ezfArrayNo="0" /></td>
																			<td><ezf:inputText name="dsBlktPoNum_D1" ezfName="dsBlktPoNum_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="dsPoExprDt_D1" ezfName="dsPoExprDt_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"dsPoExprDt_D1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsPoExprDt_D1{EZF_ROW_NUMBER}', 4);"></td>
																			<td>
																				<ezf:inputText name="dsDefBillToCd_D1" ezfName="dsDefBillToCd_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\" id=\"dsDefBillToCd_D1{EZF_ROW_NUMBER}\""/> 
																				<ezf:inputButton name="OpenWin_BillToSearchTrx" value="BillTo" ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn3" otherAttr=" id=\"OpenWin_BillToSearchTrx{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td>
																				<ezf:inputText name="dsDefShipToCd_D1" ezfName="dsDefShipToCd_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\" id=\"dsDefShipToCd_D1{EZF_ROW_NUMBER}\""/> 
																				<ezf:inputButton name="OpenWin_ShipToSearch" value="ShipTo" ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn3" otherAttr=" id=\"OpenWin_ShipToSearch{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td><ezf:inputCheckBox name="dsCrCardReqFlg_D1" ezfName="dsCrCardReqFlg_D1" value="Y" ezfHyo="D" ezfArrayNo="0" /></td>
																			<td><ezf:inputCheckBox name="dsOvngtAllwFlg_D1" ezfName="dsOvngtAllwFlg_D1" value="Y" ezfHyo="D" ezfArrayNo="0" /></td>
																			<td class="pAuditInfo">
																				<ezf:inputHidden name="xxRecHistCratTs_D1" ezfName="xxRecHistCratTs_D1" ezfHyo="D" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistCratByNm_D1" ezfName="xxRecHistCratByNm_D1" ezfHyo="D" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistUpdTs_D1" ezfName="xxRecHistUpdTs_D1" ezfHyo="D" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistUpdByNm_D1" ezfName="xxRecHistUpdByNm_D1" ezfHyo="D" ezfArrayNo="0" />
																				<ezf:inputHidden name="xxRecHistTblNm_D1" ezfName="xxRecHistTblNm_D1" ezfHyo="D" ezfArrayNo="0" />
																			</td>
																		</tr>
																	</ezf:row>
																	<ezf:skip>
																		<tr>
																			<td><input type="checkbox" name="xChkBox_D1" ezfname="xChkBox_D1" value="Y" ezfhyo="D"></td>
																			<td><label ezfout name="addr_C1" ezfname="addr_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
																			<td><input type="checkbox" name="dsContrPoReqFlg_D1" ezfname="dsContrPoReqFlg_D1" value="Y"></td>
																			<td><input type="text" size="20" maxlength="20" name="dsContrBlktPoNum_D1" ezfname="dsContrBlktPoNum_D1" ezftoupper></td>
																			<td><input type="text" size="10" maxlength="10" name="dsContrPoExprDt_D1" ezfname="dsContrPoExprDt_D1" ezftoupper><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsContrPoExprDt_D1', 4);"></td>
																			<td><input type="text" size="10" maxlength="20" name="dsDefBillToCd_D1" ezfname="dsDefBillToCd_D1" ezftoupper ezfhyo="D"> <input type="button" class="pBtn3" value="BillTo" name="OpenWin_BillShip" onclick="sendServer(this)"></td>
																			<td><input type="text" size="10" maxlength="20" name="dsDefShipToCd_D1" ezfname="dsDefShipToCd_D1" ezftoupper ezfhyo="D"> <input type="button" class="pBtn3" value="ShipTo" name="OpenWin_BillShip" onclick="sendServer(this)"></td>
																			<td><input type="checkbox" name="xxChkBox_D3" ezfname="xxChkBox_D3" value="Y"></td>
																			<td><input type="checkbox" name="xxChkBox_D4" ezfname="xxChkBox_D4" value="Y"></td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" name="xChkBox_D1" ezfname="xChkBox_D1" value="Y" ezfhyo="D"></td>
																			<td><label ezfout name="addr_C1" ezfname="addr_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
																			<td><input type="checkbox" name="dsSvcPoReqFlg_D1" ezfname="dsSvcPoReqFlg_D1" value="Y"></td>
																			<td><input type="text" size="20" maxlength="20" name="dsSvcBlktPoNum_D1" ezfname="dsSvcBlktPoNum_D1" ezftoupper></td>
																			<td><input type="text" size="10" maxlength="10" name="dsSvcPoExprDt_D1" ezfname="dsSvcPoExprDt_D1" ezftoupper><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsSvcPoExprDt_D1', 4);"></td>
																			<td><input type="text" size="10" maxlength="20" name="dsDefBillToCd_D1" ezfname="dsDefBillToCd_D1" ezftoupper ezfhyo="D"> <input type="button" class="pBtn3" value="BillTo" name="OpenWin_BillShip" onclick="sendServer(this)"></td>
																			<td><input type="text" size="10" maxlength="20" name="dsDefShipToCd_D1" ezfname="dsDefShipToCd_D1" ezftoupper ezfhyo="D"> <input type="button" class="pBtn3" value="ShipTo" name="OpenWin_BillShip" onclick="sendServer(this)"></td>
																			<td><input type="checkbox" name="xxChkBox_D3" ezfname="xxChkBox_D5" value="Y"></td>
																			<td><input type="checkbox" name="xxChkBox_D4" ezfname="xxChkBox_D6" value="Y"></td>
																		</tr>
																		<tr>
																			<td><input type="checkbox" name="xChkBox_D1" ezfname="xChkBox_D1" value="Y" ezfhyo="D"></td>
																			<td><label ezfout name="addr_C1" ezfname="addr_C1" ezfhyo="C">WWWWWWWWW1WWWWWWWWW2</label></td>
																			<td><input type="checkbox" name="dsSplyPoReqFlg_D1" ezfname="dsSplyPoReqFlg_D1" value="Y"></td>
																			<td><input type="text" size="20" maxlength="20" name="dsSplyBlktPoNum_D1" ezfname="dsSplyBlktPoNum_D1" ezftoupper></td>
																			<td><input type="text" size="10" maxlength="10" name="dsSplyPoExprDt_D1" ezfname="dsSplyPoExprDt_D1" ezftoupper><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsSplyPoExprDt_D1', 4);"></td>
																			<td><input type="text" size="10" maxlength="20" name="dsDefBillToCd_D1" ezfname="dsDefBillToCd_D1" ezftoupper ezfhyo="D"> <input type="button" class="pBtn3" value="BillTo" name="OpenWin_BillShip" onclick="sendServer(this)"></td>
																			<td><input type="text" size="10" maxlength="20" name="dsDefShipToCd_D1" ezfname="dsDefShipToCd_D1" ezftoupper ezfhyo="D"> <input type="button" class="pBtn3" value="ShipTo" name="OpenWin_BillShip" onclick="sendServer(this)"></td>
																			<td><input type="checkbox" name="xxChkBox_D7" ezfname="xxChkBox_D7" value="Y"></td>
																			<td><input type="checkbox" name="xxChkBox_D8" ezfname="xxChkBox_D8" value="Y"></td>
																		</tr>
																	</ezf:skip>
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
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'SrvAttrb'}">
								<script type="text/javascript">
<%if(AccountlFlg.equals("Y")){%>
									document.getElementById( "Account" ).className = "pTab_OFF";
<%}%>
<%if(ClassificationsFlg.equals("Y")){%>
									document.getElementById( "Classifications" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
									document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
									document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(SrvAttrbFlg.equals("Y")){%>
									document.getElementById( "SrvAttrb" ).className = "pTab_ON";
<%}%>
<%if(InstructionsFlg.equals("Y")){%>
									document.getElementById( "Instructions" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
									document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
								</script>
								<div class="pTab_BODY_In" border="0" >
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td valign="top" width="520">
												<fieldset>
													<legend>CERTIFICATION REQUIRED</legend>
													<table border="0" cellpadding="1" cellspacing="0">
														<tr>
															<td>
																<table border="0" cellpadding="1" cellspacing="0">
																	<col width="480" align="right">
																	<tr>
																		<td><ezf:inputButton name="AddCertReq" value="Add" htmlClass="pBtn4" />
																			<ezf:inputButton name="DeleteCertReq" value="Delete" htmlClass="pBtn4" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td>
																<div style="overflow-x:none; overflow-y:none; float:left;">
																	<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="70">		<!-- No -->
																			<col align="center" width="180">	<!-- Access Permit Description -->
																			<col align="center" width="100">	<!-- Start Date -->
																			<col align="center" width="100">	<!-- End Date -->
																			<tr>
																				<td class="pClothBs"></td>
																				<td class="pClothBs">No</td>
																				<td class="pClothBs">Access Permit Description</td>
																				<td class="pClothBs">Start Date</td>
																				<td class="pClothBs">End Date</td>
																			</tr>
																	</table>
																</div>
																<div id="LeftTableF" style="overflow-y:scroll; height:120; overflow-x:hidden; width:500; float:left;">
																	<table border="1" cellpadding="1" cellspacing="0" id="F" style="table-layout: fixed">
																			<col align="center" width="30">	<!-- Checkbox -->
																			<col width="70">		<!-- No -->
																			<col width="180">		<!-- Access Permit Description-->
																			<col width="100">		<!-- Start Date -->
																			<col width="100">		<!-- End Date -->
																		<ezf:row ezfHyo="F">
																			<tr>
																				<td><ezf:inputCheckBox name="xxChkBox_F1" ezfName="xxChkBox_F1" value="Y" ezfHyo="F" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_F1{EZF_ROW_NUMBER}\""/></td>
																				<td><ezf:inputText name="svcAccsPmitNum_F1" ezfName="svcAccsPmitNum_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\" id=\"svcAccsPmitPk_F1{EZF_ROW_NUMBER}\""/>
																					<ezf:inputButton name="OpenWin_AccsPmit" value="AP" ezfHyo="F" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_AccsPmit{EZF_ROW_NUMBER}\""/>
																				</td>
																				<td><ezf:inputText name="svcAccsPmitDescTxt_F1" ezfName="svcAccsPmitDescTxt_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"100\" id=\"svcAccsPmitDescTxt_F1{EZF_ROW_NUMBER}\""/></td>
																				<td><ezf:inputText name="effFromDt_F1" ezfName="effFromDt_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effFromDt_F1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_F1{EZF_ROW_NUMBER}', 4);"></td>
																				<td><ezf:inputText name="effToDt_F1" ezfName="effToDt_F1" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effThruDt_F1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_F1{EZF_ROW_NUMBER}', 4);"></td>
																				<td class="pAuditInfo">
																					<ezf:inputHidden name="xxRecHistCratTs_F1" ezfName="xxRecHistCratTs_F1" ezfHyo="F" ezfArrayNo="0" />
																					<ezf:inputHidden name="xxRecHistCratByNm_F1" ezfName="xxRecHistCratByNm_F1" ezfHyo="F" ezfArrayNo="0" />
																					<ezf:inputHidden name="xxRecHistUpdTs_F1" ezfName="xxRecHistUpdTs_F1" ezfHyo="F" ezfArrayNo="0" />
																					<ezf:inputHidden name="xxRecHistUpdByNm_F1" ezfName="xxRecHistUpdByNm_F1" ezfHyo="F" ezfArrayNo="0" />
																					<ezf:inputHidden name="xxRecHistTblNm_F1" ezfName="xxRecHistTblNm_F1" ezfHyo="F" ezfArrayNo="0" />
																				</td>
																			</tr>
																		</ezf:row>
																		<ezf:skip>
																			<tr id="id_leftF_row{EZF_ROW_NUMBER}">
																				<td><input type="checkbox" name="xxChkBox_F1" ezfname="xxChkBox_F1" value="Y" ezfhyo="F"></td>
																				<td><input type="text" size="3" maxlength="3" name="svcAccsPmitNum_F1" ezfname="srvAccsPmitNum_F1" ezftoupper ezfhyo="F"><input type="button" class="pBtn0" value="AP" name="OpenWin_AccsPmit" onclick="sendServer(this)"></td>
																				<td><input type="text" size="20" maxlength="100" name="svcAccsPmitTxt_F1" ezfname="svcAccsPmitTxt_F1" ezfhyo="F"></td>
																				<td><input type="text" size="10" maxlength="10" name="effFromDt_F1" ezfname="effFromDt_F1" id="effFromDt_F1{EZF_ROW_NUMBER}" ezftoupper ezfhyo="F"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_F1{EZF_ROW_NUMBER}', 4);"></td>
																				<td><input type="text" size="10" maxlength="10" name="effThruDt_F1" ezfname="effThruDt_F1" id="effThruDt_F1{EZF_ROW_NUMBER}" ezftoupper ezfhyo="F"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_F1{EZF_ROW_NUMBER}', 4);"></td>
																			</tr>
																			<tr id="id_leftF_row{EZF_ROW_NUMBER}">
																				<td><input type="checkbox" name="xxChkBox_F1" ezfname="xxChkBox_F1" value="Y" ezfhyo="F"></td>
																				<td><input type="text" size="3" maxlength="3" name="svcAccsPmitNum_F1" ezfname="srvAccsPmitNum_F1" ezftoupper ezfhyo="F"><input type="button" class="pBtn0" value="AP" name="OpenWin_AccsPmit" onclick="sendServer(this)"></td>
																				<td><input type="text" size="20" maxlength="100" name="svcAccsPmitTxt_F1" ezfname="svcAccsPmitTxt_F1" ezfhyo="F"></td>
																				<td><input type="text" size="10" maxlength="10" name="effFromDt_F1" ezfname="effFromDt_F1" id="effFromDt_F1{EZF_ROW_NUMBER}" ezftoupper ezfhyo="F"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_F1{EZF_ROW_NUMBER}', 4);"></td>
																				<td><input type="text" size="10" maxlength="10" name="effThruDt_F1" ezfname="effThruDt_F1" id="effThruDt_F1{EZF_ROW_NUMBER}" ezftoupper ezfhyo="F"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_F1{EZF_ROW_NUMBER}', 4);"></td>
																			</tr>
																		</ezf:skip>
																	</table>
																</div>
															</td>
														</tr>
													</table>
												</fieldset>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="130">
													<col width="">
													<col width="">
													<col width="">
													<tr>
														<!-- Mod Start 2018/01/22 Hd.Sugawara QC#20291(Sol#348) -->
														<!-- <td class="stab"><a href="#" onclick="sendServer('OpenWin_PrimTech')">Primary Technician</a></td> -->
														<td class="stab"><ezf:anchor name="prfTechCd_SA" ezfName="prfTechCd_SA" ezfEmulateBtn="1" ezfGuard="OpenWin_PrimTech" >Primary Technician</ezf:anchor></td>
														<!-- Mod End   2018/01/22 Hd.Sugawara QC#20291(Sol#348) -->
														<td><ezf:inputText name="prfTechCd_SA" ezfName="prfTechCd_SA" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
														<td><ezf:inputButton name="Search_PrimTechNm" value=">>" htmlClass="pBtn1" /></td>
														<td><ezf:inputText name="xxAllPsnNm_S1" ezfName="xxAllPsnNm_S1" otherAttr=" size=\"40\" tabindex=\"-1\""/></td>
													</tr>
													<tr>
														<!-- Mod Start 2018/01/22 Hd.Sugawara QC#20291(Sol#348) -->
														<!-- <td class="stab"><a href="#" onclick="sendServer('OpenWin_ReqTech')">Requested Technician</a></td> -->
														<td class="stab"><ezf:anchor name="reqTechCd_SA" ezfName="reqTechCd_SA" ezfEmulateBtn="1" ezfGuard="OpenWin_ReqTech" >Requested Technician</ezf:anchor></td>
														<!-- Mod End   2018/01/22 Hd.Sugawara QC#20291(Sol#348) -->
														<td><ezf:inputText name="reqTechCd_SA" ezfName="reqTechCd_SA" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
														<td><ezf:inputButton name="Search_ReqTechNm" value=">>" htmlClass="pBtn1" /></td>
														<td><ezf:inputText name="xxAllPsnNm_S2" ezfName="xxAllPsnNm_S2" otherAttr=" size=\"40\" tabindex=\"-1\""/></td>
													</tr>
												</table>
											</td>
											<td width="20"></td>
											<td valign="top" width="550">
												<fieldset>
													<legend>DO NOT SEND TECHNICIAN(S)</legend>
													<table border="0" cellpadding="1" cellspacing="0">
														<tr>
															<td>
																<table border="0" cellpadding="1" cellspacing="0">
																	<col width="520" align="right">
																	<tr>
																		<td><ezf:inputButton name="AddTech" value="Add" htmlClass="pBtn4" />
																			<ezf:inputButton name="DeleteTech" value="Delete" htmlClass="pBtn4" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td>
																<div style="overflow-x:none; overflow-y:none; float:left;">
																	<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																			<col align="center" width="30">		<!-- Checkbox -->
																			<col align="center" width="130">	<!-- Employee ID -->
																			<col align="center" width="160">	<!-- Employee Name -->
																			<col align="center" width="100">	<!-- End Date -->
																			<col align="center" width="120">	<!-- Audit -->
																			<tr>
																				<td class="pClothBs"></td>
																				<td class="pClothBs">Employee ID</td>
																				<td class="pClothBs">Employee Name</td>
																				<td class="pClothBs">End Date</td>
																				<td class="pClothBs">Audit</td>
																			</tr>
																	</table>
																</div>
																<div id="LeftTableG" style="overflow-y:scroll; height:185; overflow-x:hidden; width:560; float:left;">
																	<table border="1" cellpadding="1" cellspacing="0" id="G" style="table-layout: fixed">
																			<col align="center" width="30">	<!-- Checkbox -->
																			<col width="130">		<!-- Employee ID -->
																			<col width="160">		<!-- Employee Name-->
																			<col width="100">		<!-- End Date -->
																			<col width="120">		<!-- Audit -->
																		<ezf:row ezfHyo="G">
																			<tr>
																				<td><ezf:inputCheckBox name="xxChkBox_G1" ezfName="xxChkBox_G1" value="Y" ezfHyo="G" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_G1{EZF_ROW_NUMBER}\""/></td>
																				<td><ezf:inputText name="nonPrfTechCd_G1" ezfName="nonPrfTechCd_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\" id=\"nonPrfTechCd_G1{EZF_ROW_NUMBER}\""/>
																					<ezf:inputButton name="OpenWin_Tech" value="TECH" ezfHyo="G" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_Tech{EZF_ROW_NUMBER}\""/>
																				</td>
																				<td><ezf:inputText name="techNm_G1" ezfName="techNm_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"45\""/></td>
																				<td><ezf:inputText name="effThruDt_G1" ezfName="effThruDt_G1" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effThruDt_G1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_G1{EZF_ROW_NUMBER}', 4);"></td>
																				<td><ezf:label name="ezUpUserID_G1" ezfName="ezUpUserID_G1" ezfHyo="G" ezfArrayNo="0" />
																					<ezf:label name="ezUpTime_G2" ezfName="ezUpTime_G2" ezfHyo="G" ezfArrayNo="0" />
																				</td>
																				<td class="pAuditInfo">
																					<ezf:inputHidden name="xxRecHistCratTs_G1" ezfName="xxRecHistCratTs_G1" ezfHyo="G" ezfArrayNo="0" />
																					<ezf:inputHidden name="xxRecHistCratByNm_G1" ezfName="xxRecHistCratByNm_G1" ezfHyo="G" ezfArrayNo="0" />
																					<ezf:inputHidden name="xxRecHistUpdTs_G1" ezfName="xxRecHistUpdTs_G1" ezfHyo="G" ezfArrayNo="0" />
																					<ezf:inputHidden name="xxRecHistUpdByNm_G1" ezfName="xxRecHistUpdByNm_G1" ezfHyo="G" ezfArrayNo="0" />
																					<ezf:inputHidden name="xxRecHistTblNm_G1" ezfName="xxRecHistTblNm_G1" ezfHyo="G" ezfArrayNo="0" />
																				</td>
																			</tr>
																		</ezf:row>
																		<ezf:skip>
																			<tr id="id_leftF_row{EZF_ROW_NUMBER}">
																				<td><input type="checkbox" name="xxChkBox_G1" ezfname="xxChkBox_G1" value="Y" ezfhyo="G"></td>
																				<td><input type="text" size="10" maxlength="20" name="nonPrfTechCd_G1" ezfname="nonPrfTechCd_G1" ezftoupper ezfhyo="G"><input type="button" class="pBtn1" value="TECH" name="OpenWin_Tech" onclick="sendServer(this)"></td>
																				<td><input type="text" size="20" maxlength="45" name="techNm_G1" ezfname="techNm_G1" ezfhyo="G"></td>
																				<td><input type="text" size="10" maxlength="10" name="effThruDt_G1" ezfname="effThruDt_G1" id="effThruDt_G1{EZF_ROW_NUMBER}" ezftoupper ezfhyo="G"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_G1{EZF_ROW_NUMBER}', 4);"></td>
																				<td><label ezfout name="ezUpUserID_G1" ezfname="ezUpUserID_G1" ezfhyo="G">WWWWWWWWW1WWWWWWWWW2</label>
																					<label ezfout name="ezUpTime_G1" ezfname="ezUpTime_G1" ezfhyo="G">YYYY/MM/DD</label>
																				</td>
																			</tr>
																			<tr id="id_leftF_row{EZF_ROW_NUMBER}">
																				<td><input type="checkbox" name="xxChkBox_G1" ezfname="xxChkBox_G1" value="Y" ezfhyo="G"></td>
																				<td><input type="text" size="10" maxlength="20" name="nonPrfTechCd_G1" ezfname="nonPrfTechCd_G1" ezftoupper ezfhyo="G"><input type="button" class="pBtn1" value="TECH" name="OpenWin_Tech" onclick="sendServer(this)"></td>
																				<td><input type="text" size="20" maxlength="45" name="techNm_G1" ezfname="techNm_G1" ezfhyo="G"></td>
																				<td><input type="text" size="10" maxlength="10" name="effThruDt_G1" ezfname="effThruDt_G1" id="effThruDt_G1{EZF_ROW_NUMBER}" ezftoupper ezfhyo="G"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_G1{EZF_ROW_NUMBER}', 4);"></td>
																				<td><label ezfout name="ezUpUserID_G1" ezfname="ezUpUserID_G1" ezfhyo="G">WWWWWWWWW1WWWWWWWWW2</label>
																					<label ezfout name="ezUpTime_G1" ezfname="ezUpTime_G1" ezfhyo="G">YYYY/MM/DD</label>
																				</td>
																			</tr>
																		</ezf:skip>
																	</table>
																</div>
															</td>
														</tr>
													</table>
												</fieldset>
											</td>
											<td></td>
										</tr>
									</table>
								</div>
								</c:when>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Instructions'}">
								<script type="text/javascript">
<%if(AccountlFlg.equals("Y")){%>
									document.getElementById( "Account" ).className = "pTab_OFF";
<%}%>
<%if(ClassificationsFlg.equals("Y")){%>
									document.getElementById( "Classifications" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
									document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
									document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(SrvAttrbFlg.equals("Y")){%>
									document.getElementById( "SrvAttrb" ).className = "pTab_OFF";
<%}%>
<%if(InstructionsFlg.equals("Y")){%>
									document.getElementById( "Instructions" ).className = "pTab_ON";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
									document.getElementById( "Shipping" ).className = "pTab_OFF";
<%}%>
								</script>
								<div class="pTab_BODY_In" border="0" >
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="400" align="right">
													<col width="560" align="right">
													<tr>
														<td><ezf:inputButton name="AddMsgNote" value="Add" htmlClass="pBtn4" />
															<ezf:inputButton name="DeleteMsgNote" value="Delete" htmlClass="pBtn4" />
														</td>
														<td>
															<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="E" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum_E" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum_E" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum_E" />
																<jsp:param name="ShowingCurrent" value="xxPageShowCurNum_E" />
																<jsp:param name="ShowingTotal"   value="xxPageShowTotNum_E" />
																<jsp:param name="ViewMode"       value="FULL" />
															</jsp:include>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<div id="LeftTableE_Top" style="overflow-x:none; overflow-y:none; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
															<col align="center" width="50">		<!-- Checkbox -->
															<col align="center" width="90">		<!-- Line Of Business -->
															<col align="center" width="160">	<!-- Business Area -->
															<col align="center" width="130">	<!-- Type -->
															<col align="center" width="270">	<!-- Message/Note/Body -->
															<col align="center" width="60">		<!-- Print on Invoice -->
															<col align="center" width="110">	<!-- End Date -->
															<col align="center" width="100">	<!-- Attachment -->
															<tr>
																<td class="pClothBs"></td>
																<td class="pClothBs">Line Of<br />Business</td>
																<td class="pClothBs">Business Area</td>
																<td class="pClothBs">Type</td>
																<td class="pClothBs">Message Body</td>
																<td class="pClothBs">Print on<br />Invoice</td>
																<td class="pClothBs">End Date</td>
																<td class="pClothBs">Attachment</td>
															</tr>
													</table>
												</div>
												<div id="LeftTableE" style="overflow-y:scroll; height:180; overflow-x:hidden; width:990; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" id="E" style="table-layout: fixed">
															<col align="center" width="50">	<!-- Checkbox -->
															<col width="90">		<!-- Line Of Business -->
															<col width="160">		<!-- Business Area -->
															<col width="130">		<!-- Type -->
															<col width="270">		<!-- Message/Note/Body -->
															<col align="center" width="60">	<!-- Print on Invoice -->
															<col width="110">		<!-- End Date -->
															<col align="center" width="100">		<!-- Attachment -->
														<ezf:row ezfHyo="E">
															<tr id="id_leftE_row{EZF_ROW_NUMBER}">
																<td><ezf:inputCheckBox name="xxChkBox_E1" ezfName="xxChkBox_E1" value="Y" ezfHyo="E" ezfArrayNo="0" /></td>
																<td><ezf:select name="lineBizTpCd_P2" ezfName="lineBizTpCd_P2" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="lineBizTpCd_E1" ezfDispName="lineBizTpDescTxt_E1" /></td>
																<td><ezf:select name="dsBizAreaCd_P1" ezfName="dsBizAreaCd_P1" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsBizAreaCd_E1" ezfDispName="dsBizAreaNm_E1" /></td>
																<td><ezf:select name="dsCustMsgTpCd_P1" ezfName="dsCustMsgTpCd_P1" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsCustMsgTpCd_E1" ezfDispName="dsCustMsgTpNm_E1" /></td>
																<td><ezf:textArea name="dsCustMsgTxt_E1" ezfName="dsCustMsgTxt_E1" ezfHyo="E" ezfArrayNo="0" otherAttr=" cols=\"35\" rows=\"1\" maxlength=\"4000\" ezftoupper=\"\""/></td>
																<td><ezf:inputCheckBox name="dsPrintOnInvFlg_E1" ezfName="dsPrintOnInvFlg_E1" value="Y" ezfHyo="E" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="effThruDt_E1" ezfName="effThruDt_E1" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effThruDt_E1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_E1{EZF_ROW_NUMBER}', 4);"></td>
																<td><ezf:inputButton name="OpenWin_Attachments" value="Attachments" ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn7" otherAttr=" id=\"OpenWin_Attachments{EZF_ROW_NUMBER}\""/></td>
																<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_E1" ezfName="xxRecHistCratTs_E1" ezfHyo="E" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistCratByNm_E1" ezfName="xxRecHistCratByNm_E1" ezfHyo="E" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdTs_E1" ezfName="xxRecHistUpdTs_E1" ezfHyo="E" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdByNm_E1" ezfName="xxRecHistUpdByNm_E1" ezfHyo="E" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistTblNm_E1" ezfName="xxRecHistTblNm_E1" ezfHyo="E" ezfArrayNo="0" />
																</td>
															</tr>
														</ezf:row>
														<ezf:skip>
															<tr id="id_leftE_row{EZF_ROW_NUMBER}">
																<td><input type="checkbox" name="xxChkBox_E1" ezfname="xxChkBox_E1" value="Y"></td>
																<td><select></select></td>
																<td><select></select></td>
																<td><select></select></td>
																<td><input type="text" size="80" maxlength="200" name="dsSplyBlktPoNum_D1" ezfname="dsSplyBlktPoNum_D1" ezftoupper></td>
																<td><input type="checkbox" name="dsPrintOnInvFlg_E1" ezfname="dsPrintOnInvFlg_E1" value="Y"></td>
																<td><input type="text" size="10" maxlength="10" name="effThruDt_E1" ezfname="effThruDt_E1" ezftoupper><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_E1', 4);"></td>
																<td>&nbsp</td>
															</tr>
															<tr id="id_leftE_row{EZF_ROW_NUMBER}">
																<td><input type="checkbox" name="xxChkBox_E1" ezfname="xxChkBox_E1" value="Y"></td>
																<td><select></select></td>
																<td><select></select></td>
																<td><select></select></td>
																<td><input type="text" size="80" maxlength="200" name="dsSplyBlktPoNum_D1" ezfname="dsSplyBlktPoNum_D1" ezftoupper></td>
																<td><input type="checkbox" name="xxChkBox_E1" ezfname="xxChkBox_E1" value="Y"></td>
																<td><input type="text" size="10" maxlength="10" name="effThruDt_E1" ezfname="effThruDt_E1" ezftoupper><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_E1', 4);"></td>
																<td>&nbsp</td>
															</tr>
														</ezf:skip>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</div>
								</c:when>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Shipping'}">
								<script type="text/javascript">
<%if(AccountlFlg.equals("Y")){%>
									document.getElementById( "Account" ).className = "pTab_OFF";
<%}%>
<%if(ClassificationsFlg.equals("Y")){%>
									document.getElementById( "Classifications" ).className = "pTab_OFF";
<%}%>
<%if(ContactsFlg.equals("Y")){%>
									document.getElementById( "Contacts" ).className = "pTab_OFF";
<%}%>
<%if(TransactionsFlg.equals("Y")){%>
									document.getElementById( "Transactions" ).className = "pTab_OFF";
<%}%>
<%if(SrvAttrbFlg.equals("Y")){%>
									document.getElementById( "SrvAttrb" ).className = "pTab_OFF";
<%}%>
<%if(InstructionsFlg.equals("Y")){%>
									document.getElementById( "Instructions" ).className = "pTab_OFF";
<%}%>
<%if(ShippingFlg.equals("Y")){%>
									document.getElementById( "Shipping" ).className = "pTab_ON";
<%}%>
								</script>
								<div class="pTab_BODY_In" border="0" >
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="200" align="left">
													<col width="560" align="left">
													<tr>
														<td><ezf:inputButton name="AddShipping" value="Add" htmlClass="pBtn4" />
															<ezf:inputButton name="DeleteShipping" value="Delete" htmlClass="pBtn4" />
														</td>
														<td>
															<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="I" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum_I" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum_I" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum_I" />
																<jsp:param name="ShowingCurrent" value="xxPageShowCurNum_I" />
																<jsp:param name="ShowingTotal"   value="xxPageShowTotNum_I" />
																<jsp:param name="ViewMode"       value="FULL" />
															</jsp:include>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<div id="LeftTableI_Top" style="overflow-x:hidden; width:1090; overflow-y:hidden; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col align="center" width="20">		<!-- Check Box -->
														<col align="center" width="50">		<!-- Default -->
														<col align="center" width="80">	<!-- Line Of Business -->
														<col align="center" width="110">	<!-- Business Area -->
														<col align="center" width="160">	<!-- Freight Term -->
														<col align="center" width="130">	<!-- Service Level -->
														<col align="center" width="200">	<!-- Carrier -->
														<col align="center" width="120">	<!-- Account Number -->
														<col align="center" width="100">	<!-- Start Date -->
														<col align="center" width="100">	<!-- End Date -->
														<tr>
															<td class="pClothBs"></td>
															<td class="pClothBs">Default</td>
															<td class="pClothBs">Line Of<br />Business</td>
															<td class="pClothBs">Business Area</td>
															<td class="pClothBs">Freight Term</td>
															<td class="pClothBs">Service Level</td>
															<td class="pClothBs">Carrier</td>
															<td class="pClothBs">Account Number</td>
															<td class="pClothBs">Start Date</td>
															<td class="pClothBs">End Date</td>
														</tr>
													</table>
												</div>
												<div id="LeftTableI" style="overflow-y:scroll; height:180; width:1090; overflow-x:scroll:none; float:left;" >
													<table border="1" cellpadding="1" cellspacing="0" id="I" style="table-layout: fixed">
														<col width="20">				<!-- Check Box -->
														<col align="center" width="50">	<!-- Default -->
														<col width="80">				<!-- Line Of Business -->
														<col width="110">				<!-- Business Area -->
														<col width="160">				<!-- Freight Term -->
														<col width="130">				<!-- Service Level -->
														<col width="200">				<!-- Carrier -->
														<col width="120">				<!-- Account Number -->
														<col width="100">				<!-- Start Date -->
														<col width="100">				<!-- End Date -->
													<ezf:row ezfHyo="I">
														<tr id="id_IeftI_row{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_I1" ezfName="xxChkBox_I1" value="Y" ezfHyo="I" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_I1#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputCheckBox name="xxChkBox_ID" ezfName="xxChkBox_ID" value="Y" ezfHyo="I" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_ID#{EZF_ROW_NUMBER}\""/></td>
														    <td><ezf:select name="lineBizTpCd_P3" ezfName="lineBizTpCd_P3" ezfHyo="I" ezfArrayNo="0" ezfBlank="1" ezfCodeName="lineBizTpCd_I1" ezfDispName="lineBizTpNm_I1" otherEvent1=" onchange=\"sendServer('OnChange_lineBizTp', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width: 78px\""/></td>
															<td><ezf:select name="dsBizAreaCd_P2" ezfName="dsBizAreaCd_P2" ezfHyo="I" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsBizAreaCd_I1" ezfDispName="dsBizAreaNm_I1" otherAttr=" style=\"width: 108px\""/></td>
															<td><ezf:select name="frtCondCd_P1" ezfName="frtCondCd_P1" ezfHyo="I" ezfArrayNo="0" ezfBlank="1" ezfCodeName="frtCondCd_I1" ezfDispName="frtCondNm_I1" ezfCodeDispHyo="I" otherEvent1=" onchange=\"sendServer('OnChange_frtCond', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width: 158px\""/></td>
															<td><ezf:select name="shpgSvcLvlCd_P1" ezfName="shpgSvcLvlCd_P1" ezfHyo="I" ezfArrayNo="0" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_I1" ezfDispName="shpgSvcLvlNm_I1" ezfCodeDispHyo="I" otherAttr=" style=\"width: 128px\""/></td>
															<td>
																<span><ezf:anchor name="" ezfName="xxLinkAncr_CA" ezfEmulateBtn="1" ezfGuard="OpenWin_CarrInfo" ezfHyo="I" >Carrier</ezf:anchor></span>
																<ezf:inputText name="carrNm_I3" ezfName="carrNm_I3" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"60\""/>
															</td>
															<td><ezf:inputText name="dsCarrAcctNum_I1" ezfName="dsCarrAcctNum_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
															<td><ezf:inputText name="effFromDt_I1" ezfName="effFromDt_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" id=\"effFromDt_I1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_I1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><ezf:inputText name="effThruDt_I1" ezfName="effThruDt_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" id=\"effThruDt_I1#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_I1#{EZF_ROW_NUMBER}', 4);"></td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_I1" ezfName="xxRecHistCratTs_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_I1" ezfName="xxRecHistCratByNm_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_I1" ezfName="xxRecHistUpdTs_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_I1" ezfName="xxRecHistUpdByNm_I1" ezfHyo="I" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_I1" ezfName="xxRecHistTblNm_I1" ezfHyo="I" ezfArrayNo="0" />
															</td>
														</tr>
													</ezf:row>
													</table>
												</div>
											</td>
										</tr>
									</table>
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

<%-- **** Task specific area ends here **** --%>
