<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220215154256 --%>
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
			<input type="hidden" name="pageID" value="NFBL2040Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Invoice Entry">

			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" />
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" />
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" />
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" />
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" />

<%@ page import="business.servlet.NFBL2040.NFBL2040BMsg" %>
<%@ page import="business.servlet.NFBL2040.common.NFBL2040CommonLogic" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_INV_CATG" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>

<% NFBL2040BMsg  bMsg = (NFBL2040BMsg)databean.getEZDBMsg(); %>
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<!-- ###TAB - HEAD -->
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Mach Mst Inq" class="pTab_ON"><a href="#">Invoice Entry</a></li>
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
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<!-- ###TAB - BODY -->
				<div class="pTab_BODY">
					<table border="0" cellpadding="0" cellspacing="0"width="1130">
						<col width="170">
						<col width="150">
						<col width="80">
						<col width="120">
						<col width="80">
						<col width="120">
						<col width="80">
						<col width="120">
						<col width="250">
						<col width="120">
						<col width="220">
						<col width="100">
						<col width="">
						<tr>
							<td class="stab">Invoice Type</td>
							<td>
								<ezf:select name="cmApInvTpCd_S" ezfName="cmApInvTpCd_S" ezfBlank="1" ezfCodeName="cmApInvTpCd_C" ezfDispName="cmApInvTpDescTxt_D" otherEvent1=" onchange=\"sendServer('OnChange_CM_AP_INV_TP_CD')\"" otherAttr=" style=\"width:110\""></ezf:select>
							</td>
							<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_V1" ezfEmulateBtn="1" ezfGuard="OpenWin_PurchaseOrder1" >PO#</ezf:anchor></td>
							<td><ezf:inputText name="poNum" ezfName="poNum" value="3366245" otherAttr=" size=\"14\" maxlength=\"35\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="" ezfName="delyOrdNum_HA" ezfEmulateBtn="1" ezfGuard="OpenWin_Receipt1" >Receipt#</ezf:anchor></td>
							<td><ezf:inputText name="delyOrdNum_H" ezfName="delyOrdNum_H" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="" ezfName="rwsNum_HA" ezfEmulateBtn="1" ezfGuard="OpenWin_Receipt1" >RWS#</ezf:anchor></td>
							<td><ezf:inputText name="rwsNum_H" ezfName="rwsNum_H" otherAttr=" size=\"14\" maxlength=\"8\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="" ezfName="vndRtrnNum_HA" ezfEmulateBtn="1" ezfGuard="OpenWin_VndRtrn1" >Vendor Return#</ezf:anchor></td>
							<td><ezf:inputText name="vndRtrnNum" ezfName="vndRtrnNum" otherAttr=" size=\"14\" maxlength=\"8\" ezftoupper=\"\""/></td>
							<td>
								<ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" />
							</td>
							<td><ezf:inputButton name="OpenWin_WorkingFolder" value="Working Folder" htmlClass="pBtn8" /></td>
							<td><ezf:inputButton name="OpenWin_AttachFile" value="Attachment" htmlClass="pBtn8" /></td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" height="160" width="1130">
						<tr>
							<td valign="top">
								<table border="0" cellpadding="0" cellspacing="0" height="160" width="1130">
									<col valign="top">
									<tr>
										<td>
											<div class="pTab_HEAD">
												<ul>
													<li class="pTab_ON"  id="Header" title="Header"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Header" otherAttr=" tabindex=\"-1\"">Header</ezf:anchor></li>
													<li class="pTab_OFF" id="Holds" title="Holds"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="TAB_Hold" otherAttr=" tabindex=\"-1\"">Holds</ezf:anchor></li>
												</ul>
											</div>
											<c:choose>
											<c:when test="${pageScope._ezddatabean.xxDplyTab_01 == 'Header'}">
											<script type="text/javascript">
												document.getElementById( "Header" ).className = "pTab_ON";
												document.getElementById( "Holds" ).className = "pTab_OFF";
											</script>
											<div class="pTab_BODY_In"  id="Header_div">
												<table border="0" cellpadding="1" cellspacing="0" width="1130">
													<col width="190" align="left">
													<col width="10" align="left">
													<col width="10" align="left">
													<col width="220" align="left">
													<col width="10" align="left">
													<col width="10" align="left">
													<col width="180" align="left">
													<col width="10" align="left">
													<col width="30" align="left">
													<col width="90" align="left">
													<col width="140" align="left">
													<col width="10" align="left">
													<col width="150" align="left">
													<col width="200" align="left">
													<col width="330" align="left">
													<tr>
														<td class="stab"><ezf:anchor name="" ezfName="dplyVndNm_L" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier Name</ezf:anchor></td>
														<td><ezf:inputText name="dplyVndNm" ezfName="dplyVndNm" value="TEST AZERTY" otherAttr=" size=\"20\" maxlength=\"240\" ezftoupper=\"\""/></td>
														<td></td>
														<td class="stab"><ezf:anchor name="" ezfName="apVndCd_L" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier Site Code</ezf:anchor></td>
														<td><ezf:inputText name="apVndCd" ezfName="apVndCd" value="423651" otherEvent1=" onchange=\"sendServer('OnChange_Location')\"" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/></td>
														<td></td>
														<td class="stab">Invoice#</td>
														<td><ezf:inputText name="apVndInvNum" ezfName="apVndInvNum" value="50KXYDD" otherAttr=" size=\"10\" maxlength=\"15\" ezftoupper=\"\""/></td>
														<td></td>
														<td class="stab">Invoice Date</td>
														<td>
															<ezf:inputText name="invDt" ezfName="invDt" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
															<img src="img/calendar.gif" class="pCalendar" onclick="calendar('invDt', 4);" >
														</td>
														<td><ezf:inputCheckBox name="xxChkBox_PA" ezfName="xxChkBox_PA" value="Y" /></td>
														<td class="stab">Pay Alone</td>
														<td class="stab">Payment Number</td>
														<td><ezf:inputText name="apPmtChkNum" ezfName="apPmtChkNum" value="40047461" otherAttr=" size=\"14\" maxlength=\"50\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab"><ezf:anchor name="" ezfName="prntVndCd_L" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier Number</ezf:anchor></td>
														<td colspan="2"><ezf:inputText name="prntVndCd" ezfName="prntVndCd" value="423651" otherAttr=" size=\"8\" maxlength=\"30\" ezftoupper=\"\""/></td>
														<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_V2" ezfEmulateBtn="1" ezfGuard="OpenWin_PurchaseOrder2" >PO#</ezf:anchor></td>
														<td colspan="2"><ezf:inputText name="poNum_HT" ezfName="poNum_HT" value="3366245" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
														<td class="stab">Invoice Amount</td>
														<td><ezf:inputText name="invAmt" ezfName="invAmt" value="52.18" otherAttr=" size=\"10\" maxlength=\"19\" ezftoupper=\"\""/></td>
														<td></td>
														<td class="stab"><ezf:anchor name="" ezfName="vndPmtTermDescTxt_A" ezfEmulateBtn="1" ezfGuard="OnClick_TermLink" otherAttr=" id=\"vndPmtTermDescTxt_A\"">Terms</ezf:anchor></td>
														<td><ezf:inputText name="vndPmtTermDescTxt" ezfName="vndPmtTermDescTxt" value="30 NET" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/></td>
														<td><ezf:inputCheckBox name="xxChkBox_HO" ezfName="xxChkBox_HO" value="Y" /></td>
														<td class="stab">Holds</td>
														<td class="stab">Payment Method</td>
														<td><ezf:inputText name="vndPmtMethDescTxt" ezfName="vndPmtMethDescTxt" value="ELECTRONIC" otherAttr=" size=\"14\" maxlength=\"50\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">PO Date</td>
														<td colspan="2">
															<ezf:inputText name="poApvlDt" ezfName="poApvlDt" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
															<img src="img/calendar.gif" class="pCalendar" onclick="calendar('poApvlDt', 4);" >
														</td>
														<td class="stab">PO Amount</td>
														<td colspan="2"><ezf:inputText name="entPoDtlDealNetAmt_TO" ezfName="entPoDtlDealNetAmt_TO" value="52.18" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/></td>
														<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_V3" ezfEmulateBtn="1" ezfGuard="OpenWin_VndRtrn2" >Vendor Return#</ezf:anchor></td>
														<td><ezf:inputText name="vndRtrnNum_H" ezfName="vndRtrnNum_H" value="1401213" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
														<td></td>
														<td class="stab">Description</td>
														<td><ezf:inputText name="invHdrDescTxt" ezfName="invHdrDescTxt" value="PO 3366245" otherAttr=" size=\"12\" maxlength=\"240\" ezftoupper=\"\""/></td>
														<td><ezf:inputCheckBox name="xxChkBox_PO" ezfName="xxChkBox_PO" value="Y" /></td>
														<td class="stab">PO Variance</td>
														<td class="stab">Status</td>
														<td><ezf:inputText name="acctInvStsDescTxt" ezfName="acctInvStsDescTxt" value="ACCOUNTED" otherAttr=" size=\"14\" maxlength=\"50\" ezftoupper=\"\""/></td>
													</tr>
													<tr>
														<td class="stab">Payment Date</td>
														<td colspan="2">
															<ezf:inputText name="pmtDt" ezfName="pmtDt" otherAttr=" size=\"10\" maxlength=\"10\""/>
														</td>
														<td class="stab">Amount Paid</td>
														<td colspan="2"><ezf:inputText name="hdrPmtAmt" ezfName="hdrPmtAmt" value="52.18" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/></td>
														<td class="stab"><ezf:anchor name="" ezfName="delyOrdNum_DA" ezfEmulateBtn="1" ezfGuard="OpenWin_Receipt2" >Receipt#</ezf:anchor></td>
														<td colspan="2"><ezf:inputText name="delyOrdNum_DH" ezfName="delyOrdNum_DH" value="1401213" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
														<td></td>
														<td></td>
														<td><ezf:inputCheckBox name="xxChkBox_CR" ezfName="xxChkBox_CR" value="Y" /></td>
														<td class="stab">Corrected</td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td class="stab">Source</td>
														<td colspan="2"><ezf:inputText name="apInvCatgDescTxt" ezfName="apInvCatgDescTxt" value="EDI GATEWAY" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/></td>
														<td class="stab">Account Date</td>
														<td colspan="2">
															<ezf:inputText name="acctDt" ezfName="acctDt" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
														</td>
														<td class="stab"><ezf:anchor name="" ezfName="rwsNum_DA" ezfEmulateBtn="1" ezfGuard="OpenWin_Receipt2" >RWS Number</ezf:anchor></td>
														<td><ezf:inputText name="rwsNum_DH" ezfName="rwsNum_DH" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
														<td></td>
														<td></td>
														<td></td>
														<td><ezf:inputCheckBox name="xxChkBox_MP" ezfName="xxChkBox_MP" value="Y" /></td>
														<td colspan="2" class="stab">Multiple PO/Vendor Return#</td>
														<td></td>
													</tr>
													<tr>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<% if(   ACCT_INV_STS.ENTERED.equals(bMsg.acctInvStsCd.getValue())
														      || ACCT_INV_STS.AUTHORIZED.equals(bMsg.acctInvStsCd.getValue())
														) { %>
														<td></td>
														<td></td>
														<td colspan="4" align="right">
															<ezf:inputButton name="InvoiceCancel" value="InvoiceCancel" htmlClass="pBtn7" />
															<ezf:inputButton name="Correction" value="PO Correction" htmlClass="pBtn7" />
															<ezf:inputButton name="PO_Line_Correction" value="PO Line Correction" htmlClass="pBtn9" />
															<ezf:inputButton name="Refresh" value="Refresh" htmlClass="pBtn5" />
														</td>
														<% } else if ( ACCT_INV_STS.CANCEL.equals(bMsg.acctInvStsCd.getValue())
														      && AP_INV_CATG.MANUAL_INVOICE_ENTRY.equals(bMsg.apInvCatgCd.getValue()) ) { %>
														<td colspan="6" align="right">
															<ezf:inputButton name="Regenerate_Invoice" value="Regenerate Invoice" htmlClass="pBtn10" />
															<ezf:inputButton name="Correction" value="PO Correction" htmlClass="pBtn7" />
															<ezf:inputButton name="PO_Line_Correction" value="PO Line Correction" htmlClass="pBtn9" />
															<ezf:inputButton name="Refresh" value="Refresh" htmlClass="pBtn5" />
														</td>
														<% } else { %>
														<td></td>
														<td></td>
														<td colspan="5" align="right">
															<ezf:inputButton name="Correction" value="PO Correction" htmlClass="pBtn7" />
															<ezf:inputButton name="PO_Line_Correction" value="PO Line Correction" htmlClass="pBtn9" />
															<ezf:inputButton name="Refresh" value="Refresh" htmlClass="pBtn5" />
														</td>
														<% } %>
													</tr>
												</table>
											</div>
											</c:when>
											</c:choose>
											<c:choose>
											<c:when test="${pageScope._ezddatabean.xxDplyTab_01 == 'Holds'}">
											<script type="text/javascript">
												document.getElementById( "Header" ).className = "pTab_OFF";
												document.getElementById( "Holds" ).className = "pTab_ON";
											</script>
											<div class="pTab_BODY_In"  id="Holds_div">
												<table border="0" cellpadding="1" cellspacing="0" width="1130">
													<col width="100" align="left">
													<col width="100" align="left">
													<col width="100" align="left">
													<col width="100" align="left">
													<col width="100" align="left">
													<col width="100" align="left">
													<col width="530" align="right">
													<tr>
														<td class="stab">Invoice Number</td>
														<td><ezf:inputText name="apVndInvNum_HH" ezfName="apVndInvNum_HH" value="50KXYDD" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/></td>
														<td class="stab">Invoice Amount</td>
														<td><ezf:inputText name="invAmt_HH" ezfName="invAmt_HH" value="52.18" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/></td>
														<td><ezf:inputButton name="InsertRowHold" value="Insert Row" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="DeleteRowHold" value="Delete Row" htmlClass="pBtn6" /></td>
														<td>
															<!-- <input type="button" class="pBtn6" value="Hold" name="Hold" onclick="sendServer(this)" > -->
															<ezf:inputButton name="Release" value="Release" htmlClass="pBtn6" />
															<ezf:inputButton name="HoldTabDownloadButton" value="Download" htmlClass="pBtn6" />
														</td>
													</tr>
												</table>
												<div id="parentBoxH">
													<div style="float:left; display:block">
														<div id="leftTblHeadH" style="display:block; overflow:hidden; margin:0px; padding:0px;"></div>
														<div id="leftTblH" style="float:left; display:block; height:67px; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px;"></div>
													</div>
													<div style="float:left">
														<div id="rightTblHeadH" style="width:1103px; display:block; overflow-x:hidden; overflow-y:hidden; margin:0px; padding:0px;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTblH' ) );" >
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" height="24" id="HHEAD" width="1217px" style="margin-right:20px" >
																<col align="center" width="30">
																<col align="center" width="100">
																<col align="center" width="110">
																<col align="center" width="100">
																<col align="center" width="170">
																<col align="center" width="100">
																<col align="center" width="90">
																<col align="center" width="100">
																<col align="center" width="100">
																<col align="center" width="150">
																<col align="center" width="167">
																<tr height="24" valign="middle">
																	<td class="pClothBs">&nbsp;</td>
																	<td class="pClothBs">Line Number</td>
																	<td class="pClothBs">Hold Name</td>
																	<td class="pClothBs">Hold Date</td>
																	<td class="pClothBs">Hold Reason</td>
																	<td class="pClothBs">Hold By</td>
																	<td class="pClothBs">Hold Release</td>
																	<td class="pClothBs">Released By</td>
																	<td class="pClothBs">Released Date</td>
																	<td class="pClothBs">Release Reason</td>
																	<td class="pClothBs">Release Note</td>
																</tr>
															</table>
														</div>
														<div id="rightTblH" style="width:1120px; height:67px; display:block; overflow-y:scroll; overflow-x:scroll; margin:0px; padding:0px;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTblHeadH' ) );">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed" id="H" width="1217px">
																<col align="center" width="30">
																<col align="center" width="100">
																<col align="center" width="110">
																<col align="center" width="100">
																<col align="center" width="170">
																<col align="center" width="100">
																<col align="center" width="90">
																<col align="center" width="100">
																<col align="center" width="100">
																<col align="center" width="150">
																<col align="center" width="167">
																<ezf:row ezfHyo="H">
																	<tr id="id_leftA_row{EZF_ROW_NUMBER}" height="24">
																		<td><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" ezfHyo="H" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="apVndInvLineNum_H1" ezfName="apVndInvLineNum_H1" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"4\" maxlength=\"4\" id=\"apVndInvLineNum_H1#{EZF_ROW_NUMBER}\""/></td>
																		<td><ezf:select name="pmtHldCd_H1" ezfName="pmtHldCd_H1" ezfHyo="H" ezfArrayNo="0" ezfBlank="1" ezfCodeName="pmtHldCd_HC" ezfDispName="pmtHldDescTxt_H1" otherAttr=" style=\"width:85\""></ezf:select></td>
																		<td><ezf:inputText name="pmtHldDt_H1" ezfName="pmtHldDt_H1" value="08/11/2015" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																		<td><ezf:select name="pmtHldRsnCd_H1" ezfName="pmtHldRsnCd_H1" ezfHyo="H" ezfArrayNo="0" ezfBlank="1" ezfCodeName="pmtHldRsnCd_HC" ezfDispName="pmtHldRsnDescTxt_H1" otherAttr=" style=\"width:145\""></ezf:select></td>
																		<td><ezf:inputText name="pmtHldPsnCd_H1" ezfName="pmtHldPsnCd_H1" value="D02653" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\""/></td>
																		<td><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" ezfHyo="H" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="pmtHldRelPsnCd_H1" ezfName="pmtHldRelPsnCd_H1" value="D02653" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\""/></td>
																		<td><ezf:inputText name="pmtHldRelDt_H1" ezfName="pmtHldRelDt_H1" value="08/11/2015" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																		<td><ezf:select name="pmtHldRelRsnCd_H1" ezfName="pmtHldRelRsnCd_H1" ezfHyo="H" ezfArrayNo="0" ezfBlank="1" ezfCodeName="pmtHldRelRsnCd_C" ezfDispName="pmtHldRelRsnDescTxt_D" ezfCodeDispHyo="H" otherAttr=" style=\"width:140\""></ezf:select></td>
																		<td><ezf:inputText name="pmtHldRelCmntTxt_H1" ezfName="pmtHldRelCmntTxt_H1" value="170_SYSTEMS_RELEASE" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"240\" ezftoupper=\"\""/></td>
																		<td class="pAuditInfo">
																			<ezf:inputHidden name="xxRecHistCratTs_H1" ezfName="xxRecHistCratTs_H1" ezfHyo="H" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistCratByNm_H1" ezfName="xxRecHistCratByNm_H1" ezfHyo="H" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdTs_H1" ezfName="xxRecHistUpdTs_H1" ezfHyo="H" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdByNm_H1" ezfName="xxRecHistUpdByNm_H1" ezfHyo="H" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistTblNm_H1" ezfName="xxRecHistTblNm_H1" ezfHyo="H" ezfArrayNo="0" />
																		</td>
																	</tr>
																</ezf:row>
																<ezf:skip>
																</ezf:skip>
															</table>
														</div>
													</div>
												</div>
											</div>
											</c:when>
											</c:choose>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

<%-- ######################################## DETAIL ######################################## --%>

					<table width="100%" cellpadding="0" cellspacing="0" height="160" >
						<col valign="top">
						<tr>
							<td>
							<div class="pTab_HEAD">
								<ul>
									<li class="pTab_ON" id="Lines"             title="Lines"        ><ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="TAB_Lines" otherAttr=" tabindex=\"-1\"">Lines</ezf:anchor></li>
									<li class="pTab_OFF" id="Distributions"    title="Distributions"><ezf:anchor name="" ezfName="xxTabProt_04" ezfEmulateBtn="1" ezfGuard="TAB_Distributions" otherAttr=" tabindex=\"-1\"">Distributions</ezf:anchor></li>
								</ul>
							</div>
							<c:choose>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_02 == 'Lines'}">
							<script type="text/javascript">
								document.getElementById( "Lines" ).className = "pTab_ON";
								document.getElementById( "Distributions" ).className = "pTab_OFF";
							</script>
							<div class="pTab_BODY_In" border="0"   id="Lines_div">
								<table border="0" cellpadding="1" cellspacing="0" width="1130" align="center">
									<col align="left" width="72">
									<col align="left" width="72">
									<col align="left" width="170">
									<col align="left" width="30">
									<col align="left" width="80">
									<col align="left" width="45">
									<col align="left" width="80">
									<col align="left" width="40">
									<col align="left" width="80">
									<col align="left" width="80">
									<col align="left" width="80">
									<col align="left" width="80">
									<col align="right" width="90">
									<tr>
										<td>
											<ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn6" />
										</td>
										<td>
											<ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn6" />
										</td>
										<td>
											<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
										</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_V4" ezfEmulateBtn="1" ezfGuard="OpenWin_PurchaseOrder3" >PO#</ezf:anchor></td>
										<td><ezf:inputText name="poNum_L" ezfName="poNum_L" value="3366245" otherAttr=" size=\"14\" maxlength=\"35\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="delyOrdNum_LA" ezfEmulateBtn="1" ezfGuard="OpenWin_Receipt3" >Receipt#</ezf:anchor></td>
										<td><ezf:inputText name="delyOrdNum_L" ezfName="delyOrdNum_L" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="rwsNum_LA" ezfEmulateBtn="1" ezfGuard="OpenWin_Receipt3" >RWS#</ezf:anchor></td>
										<td><ezf:inputText name="rwsNum_L" ezfName="rwsNum_L" otherAttr=" size=\"14\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="vndRtrnNum_LA" ezfEmulateBtn="1" ezfGuard="OpenWin_VndRtrn3" >Vendor Return#</ezf:anchor></td>
										<td><ezf:inputText name="vndRtrnNum_L" ezfName="vndRtrnNum_L" otherAttr=" size=\"14\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td>
											<ezf:inputButton name="Line_Search" value="Search" htmlClass="pBtn6" />
										</td>
										<td>
<!--
											<input type="button" class="pBtn6" value="Authorize" name="" onclick="sendServer(this)" >
-->
											<ezf:inputButton name="LinesTabDownloadButton" value="Download" htmlClass="pBtn6" />
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0" width="1130" align="center">
									<tr>
										<td>
											<div id="parentBoxA">
												<div style="float:left; display:block"><!-- left table -->
													<div id='leftTblHead' style="display:block;"></div>
													<div id='leftTbl' style="float:left; display:block; height:253px; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px"></div>
												</div><!-- left table -->
												<div style="float:left;"><!-- right table -->
													<div id='rightTblHead' style="width:1101px; height:37px; display:block; overflow:hidden; margin:0px; padding:0px;">
														<table style="table-layout:fixed; height:37px; margin-right:20px;" border="1" cellpadding="1" cellspacing="0" width="2650px" id="AHEAD">
															<col align="center" width="30" >
															<col align="center" width="55" >
															<col align="center" width="100" >
															<col align="center" width="173" >
															<col align="center" width="136" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="102" >
															<col align="center" width="102" >
															<col align="center" width="102" >
															<!--<col align="center" width="102" >-->
															<col align="center" width="250" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<tr valign="middle">
																<td id="AH0"  class="pClothBs">&nbsp;</td>
																<td id="AH1"  class="pClothBs">Line Number</td>
																<td id="AH2"  class="pClothBs">Line Type</td>
																<td id="AH3"  class="pClothBs">Item</td>
																<td id="AH4"  class="pClothBs">Line Description</td>
																<td id="AH5"  class="pClothBs">Line Amount</td>
																<td id="AH6"  class="pClothBs">Unit Price</td>
																<td id="AH7"  class="pClothBs">Invoiced Qty</td>
																<td id="AH8"  class="pClothBs">Already Invoiced Qty</td>
																<td id="AH9"  class="pClothBs">Invoice Qty on HOLD</td>
																<td id="AH10" class="pClothBs">Order Qty</td>
																<td id="AH11" class="pClothBs">Received Qty</td>
																<td id="AH12" class="pClothBs">Cancel Qty</td>
																<!--<td class="pClothBs">UOM</td>-->
																<td id="AH13" class="pClothBs">Charge Account</td>
																<td id="AH14" class="pClothBs">Account<br>Description</td>
																<td id="AH15" class="pClothBs">Contract</td>
																<td id="AH16" class="pClothBs">Dealer Code</td>
																<td id="AH17" class="pClothBs">Serial Number</td>
																<td id="AH18" class="pClothBs">CSMP Invoice</td>
																<td id="AH19" class="pClothBs">Install Location</td>
																<td id="AH20" class="pClothBs">Orig PO Price</td>
																<td id="AH21" class="pClothBs">PO#</td>
																
																<td id="AH22" class="pClothBs">PO Date</td>
																<td id="AH23" class="pClothBs">PO Amount</td>
																<td id="AH24" class="pClothBs">Receipt#</td>
																<td id="AH25" class="pClothBs">RWS Number</td>
																
																<td id="AH26" class="pClothBs">Order Line #</td>
																<td id="AH27" class="pClothBs">Vendor Return#</td>
																<td id="AH28" class="pClothBs">Correction Date</td>
																<td id="AH29" class="pClothBs">Received WH</td>
															</tr>
														</table>
													</div>
													<div id="rightTbl" style="width:1118px; height:270px; display:block; overflow:scroll; margin:0px; padding: 0px;" onscroll="setScrollPosition()">
														<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="2650px" id="A">
															<col align="center" width="30">
															<col align="center" width="55">
															<col align="center" width="100" >
															<col align="center" width="173" >
															<col align="center" width="136" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="102" >
															<col align="center" width="102" >
															<col align="center" width="102" >
															<!--<col align="center" width="102" >-->
															<col align="center" width="250" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
															<col align="center" width="100" >
														<% int aIdx = 0; %>
														<ezf:row ezfHyo="A">
															<tr id="id_row{EZF_ROW_NUMBER}" height="25">
																<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="xxDtlLineNum_A1" ezfName="xxDtlLineNum_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"11\" ezftoupper=\"\" id=\"xxDtlLineNum_A1#{EZF_ROW_NUMBER}\" tabindex=\"-1\""/></td>
																<td><ezf:select name="apLineTpCd_A1" ezfName="apLineTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="apLineTpCd_C" ezfDispName="apLineTpDescTxt_D" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServer('OnChange_ApLineTpCd', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:85\" id=\"apLineTpCd_A1#{EZF_ROW_NUMBER}\" tabindex=\"-1\""></ezf:select></td>
																<td><ezf:inputButton name="MDSE" value="I" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\""/>
																	<ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="2026V920" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"16\" ezftoupper=\"\" tabindex=\"-1\""/>
																	<ezf:inputButton name="ItemDescription" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="2026V920" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="xxInvAmt_A1" ezfName="xxInvAmt_A1" value="39.93" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"19\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="dealGrsUnitPrcAmt_A1" ezfName="dealGrsUnitPrcAmt_A1" value="39.93" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"19\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="apBillQty_A1" ezfName="apBillQty_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="xxInvQty_A1" ezfName="xxInvQty_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="slsHldQty_A1" ezfName="slsHldQty_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="poQty_A1" ezfName="poQty_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="invRcvQty_A1" ezfName="invRcvQty_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="apRejQty_A1" ezfName="apRejQty_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<!--<td><input type="text" name="vndUomCd_A1" value="EACH" size="12" maxlength="4" ezftoupper="" ezfname="vndUomCd_A1" ezfHyo="A" ezfArrayNo="0" class="pPro" readOnly></td>-->
																<td><ezf:inputText name="xxCmntTxt_A1" ezfName="xxCmntTxt_A1" value="800.000.000.00000.31001.00.00.000.00000.00000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\" tabindex=\"-1\""/><ezf:inputButton name="OpenWin_ChargeAccount" value="A" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="apAcctDescTxt_A1" ezfName="apAcctDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="dsContrNum_A1" ezfName="dsContrNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="custDlrCd_A1" ezfName="custDlrCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="csmpInvNum_A1" ezfName="csmpInvNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"100\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="xxInstlFullAddr_A1" ezfName="xxInstlFullAddr_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"243\" ezftoupper=\"\" id=\"xxInstlFullAddr_A1#{EZF_ROW_NUMBER}\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="entPoDtlDealNetAmt_A1" ezfName="entPoDtlDealNetAmt_A1" value="99.99" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" tabindex=\"-1\""/></td>
															<% if (NFBL2040CommonLogic.isValidPoNumAnchor(bMsg, bMsg.A.no(aIdx))) { %>
																<td><ezf:anchor name="" ezfName="poNum_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OnClick_PoNumLink" otherAttr=" tabindex=\"-1\""><ezf:label name="poNum_A1" ezfName="poNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
															<% } else { %>
																<td><ezf:label name="poNum_A1" ezfName="poNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<% } %>
																<td><ezf:inputText name="poApvlDt_A1" ezfName="poApvlDt_A1" value="2010/12/12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="entPoDtlDealNetAmt_A3" ezfName="entPoDtlDealNetAmt_A3" value="99.99" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="delyOrdNum_A2" ezfName="delyOrdNum_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="rwsNum_A1" ezfName="rwsNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:label name="dispPoDtlLineNum_A1" ezfName="dispPoDtlLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(aIdx).vndRtrnNum_A1)) { %>
																<td><ezf:anchor name="" ezfName="vndRtrnNum_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OnClick_VndRtrnLink" otherAttr=" tabindex=\"-1\""><ezf:label name="vndRtrnNum_A1" ezfName="vndRtrnNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
															<% } else { %>
																<td><ezf:label name="vndRtrnNum_A1" ezfName="vndRtrnNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<% } %>
																<td><ezf:inputText name="invCrctDt_A1" ezfName="invCrctDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td><ezf:inputText name="locNm_A1" ezfName="locNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"60\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
															</tr>
														<% aIdx++; %>
														</ezf:row>
														</table>
													</div>
												</div>
											</div>
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0" width="1130" align="center">
									<col align="left" width="50" >
									<col align="left" width="100" >
									<col align="left" width="100" >
									<col align="right" width="150" >
									<col align="center" width="100" >
									<col align="left" width="100" >
									<col align="right" width="530" >
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td class="stab">Line Total</td>
										<td>
											<ezf:inputText name="xxTotPrcAmt_F" ezfName="xxTotPrcAmt_F" value="52.18" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
										</td>
										<td>
										    <ezf:inputButton name="Amount_Calc" value="Calc" htmlClass="pBtn6" />
										</td>
										<td></td>
									</tr>
								</table>
							</div>
							</c:when>
							</c:choose>
							<c:choose>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_02 == 'Distributions'}">
							<script type="text/javascript">
								document.getElementById( "Lines" ).className = "pTab_OFF";
								document.getElementById( "Distributions" ).className = "pTab_ON";
							</script>
								<div class="pTab_BODY_In"  id="Distributions_div">
									<table border="0" cellpadding="1" cellspacing="0" width="1130">
										<col align="right">
										<tr>
											<td><ezf:inputButton name="DistributionTabDownloadButton" value="Download" htmlClass="pBtn6" /></td>
										</tr>
									</table>
									<table border="0" cellpadding="1" cellspacing="0" width="1130">
										<col width="970">
										<col width="260">
										<tr>
											<td>
												<div id="LeftTable_D_Top" style="overflow-x:none; overflow-y:none; width:950; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" height="32">
														<col align="center" width="80">
														<col align="center" width="80">
														<col align="center" width="80">
														<col align="center" width="90">
														<col align="center" width="90">
														<col align="center" width="200">
														<col align="center" width="330">
														<tr valign="middle">
															<td class="pClothBs">Line Number</td>
															<td class="pClothBs">Distribution Line Number</td>
															<td class="pClothBs">Date</td>
															<td class="pClothBs">Debit</td>
															<td class="pClothBs">Credit</td>
															<td class="pClothBs">Account Description</td>
															<td class="pClothBs">Account Code</td>
														</tr>
													</table>
												</div>
												<div id="LeftTable_D" style="overflow-y:scroll; height:277; overflow-x:hidden; width:970; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" id="D" style="table-layout: fixed">
														<col align="center" width="80">
														<col align="center" width="80">
														<col align="center" width="80">
														<col align="center" width="90">
														<col align="center" width="90">
														<col align="center" width="200">
														<col align="center" width="330">
														<ezf:row ezfHyo="D">
															<tr id="id_leftD_row{EZF_ROW_NUMBER}">
																<td><ezf:inputText name="xxDtlLineNum_D1" ezfName="xxDtlLineNum_D1" value="001" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"10\""/></td>
																<td><ezf:inputText name="xxDtlLineNum_D2" ezfName="xxDtlLineNum_D2" value="001" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
																<td><ezf:inputText name="invDt_D1" ezfName="invDt_D1" value="11/15/2016" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																<td><ezf:inputText name="jrnlFuncDrAmt_D1" ezfName="jrnlFuncDrAmt_D1" value="56,000.00" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"19\""/></td>
																<td><ezf:inputText name="jrnlFuncCrAmt_D1" ezfName="jrnlFuncCrAmt_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"19\""/></td>
																<td><ezf:inputText name="apAcctDescTxt_D1" ezfName="apAcctDescTxt_D1" value="COST VARIANCE (PRICE DIFF)" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"30\""/></td>
																<td><ezf:inputText name="xxCmntTxt_D1" ezfName="xxCmntTxt_D1" value="800.000.000.00000.31201.00.00.000.00000.00000" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"45\" maxlength=\"100\""/></td>
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
														</ezf:skip>
													</table>
												</div>
											</td>
											<td align="center">
												<table border="0" cellpadding="1" cellspacing="0" width="120">
													<tr>
														<td>
															<fieldset>
																<legend style="font-size:12px;">Distribution</legend>
																<table border="0" cellpadding="1" cellspacing="0" width="120">
																	<tr>
																		<td class="stab"><ezf:inputRadio name="xxAllocTpCd_D" ezfName="xxAllocTpCd_D" value="A" />All Lines</td>
																	</tr>
																	<tr>
																		<td class="stab"><ezf:inputRadio name="xxAllocTpCd_D" ezfName="xxAllocTpCd_D" value="S" />Selected Lines</td>
																	</tr>
																</table>
															</fieldset>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								</c:when>
								</c:choose>
							</td>
						</tr>
					</table>

<%-- ######################################## FOOTER ######################################## --%>

				</div>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", 2, false);
</script>
<ezf:inputHidden name="docMgtDocId" ezfName="docMgtDocId" otherAttr=" size=\"0\" id=\"docMgtDocId\""/>
<ezf:inputHidden name="docMgtCatgCd" ezfName="docMgtCatgCd" otherAttr=" size=\"0\" id=\"docMgtCatgCd\""/>

<% pageContext.setAttribute("xxListNum_LY", ((NFBL2040BMsg)databean.getEZDBMsg()).xxListNum_LY.getValue()); %>
<ezf:inputHidden name="xxListNum_LY" ezfName="xxListNum_LY" otherAttr=" otherattr=\" id=\\\" =\"\""/>

<script type="text/javascript">

	// setScroll(); 
	setTimeout(setScroll,10);

	<%@ page import="com.fujitsu.uji.util.Parameters" %>
	<%@ page import="com.fujitsu.uji.http.HttpDispatchContext" %>
	function setScroll() {
		<%
		Parameters params = (Parameters) pageContext.getAttribute(HttpDispatchContext.PARAMETERS_KEY, PageContext.REQUEST_SCOPE);
		String ezdEvent = params.getSingleValue("ezd.event");
		%>
		var xxListNum_LY = this.document.getElementById( 'xxListNum_LY' );
		var rightTBL = this.document.getElementById( 'rightTBL' );

		<% if (ezdEvent!=null && ezdEvent.startsWith("TAB_")) { %>
			xxListNum_LY.value = 0;
		<% } %>
	    var LY = xxListNum_LY.value

		if (xxListNum_LY.value > 0) {
			rightTBL.scrollTop = LY;
		}
	}

	function setScrollPosition() {
		var rightTBL    = this.document.getElementById( 'rightTBL' );
		this.document.getElementById( 'xxListNum_LY' ).value = rightTBL.scrollTop;
	}

	function setTab(tabId) {

		document.getElementById( "Lines" ).className = "pTab_OFF";
		document.getElementById( "Distributions" ).className = "pTab_OFF";

		document.getElementById( tabId ).className = "pTab_ON";

		document.getElementById( "Lines_div" ).style.display="none";
		document.getElementById( "Distributions_div" ).style.display="none";

		document.getElementById( tabId + "_div" ).style.display="block";
		
		return true;
	}

	function setTab2(tabId) {

		document.getElementById( "Header" ).className = "pTab_OFF";
		document.getElementById( "Holds" ).className = "pTab_OFF";

		document.getElementById( tabId ).className = "pTab_ON";

		document.getElementById( "Header_div" ).style.display="none";
		document.getElementById( "Holds_div" ).style.display="none";

		document.getElementById( tabId + "_div" ).style.display="block";
		
		return true;
	}

	
</script>
							

<%-- **** Task specific area ends here **** --%>
