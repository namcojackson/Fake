<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220822172843 --%>
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
			<input type="hidden" name="pageID" value="NFBL1110Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="AP Maintenance Invoice Batch Entry">

			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" />
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" />
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" />
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" />
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" />

<%@ page import="business.servlet.NFBL1110.NFBL1110BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>

<% NFBL1110BMsg  bMsg = (NFBL1110BMsg)databean.getEZDBMsg(); %>
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
										<li title="Maint Inv Ent" class="pTab_ON"><a href="#">Maint Inv Ent</a></li>
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
					<table border="0" cellpadding="0" cellspacing="0" width="1100" align="center">
						<col width="90">
						<col width="160">
						<col width="140">
						<col width="390">
						<col width="120">
						<col width="180">
						<tr height="22">
							<td class="stab">Batch #</td>
							<td><ezf:inputText name="apBatNum_BA" ezfName="apBatNum_BA" value="1234567890123" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/>
								<ezf:inputHidden name="xxRecHistCratTs_BA" ezfName="xxRecHistCratTs_BA" />
								<ezf:inputHidden name="xxRecHistCratByNm_BA" ezfName="xxRecHistCratByNm_BA" />
								<ezf:inputHidden name="xxRecHistUpdTs_BA" ezfName="xxRecHistUpdTs_BA" />
								<ezf:inputHidden name="xxRecHistUpdByNm_BA" ezfName="xxRecHistUpdByNm_BA" />
								<ezf:inputHidden name="xxRecHistTblNm_BA" ezfName="xxRecHistTblNm_BA" />
							</td>
							<td class="stab">Control Amount</td>
							<td><ezf:inputText name="apCtrlAmt_BA" ezfName="apCtrlAmt_BA" value="123,456,789,012,345.0000" otherAttr=" size=\"24\" maxlength=\"24\""/>
								<ezf:inputHidden name="xxRecHistCratTs_BA" ezfName="xxRecHistCratTs_BA" />
								<ezf:inputHidden name="xxRecHistCratByNm_BA" ezfName="xxRecHistCratByNm_BA" />
								<ezf:inputHidden name="xxRecHistUpdTs_BA" ezfName="xxRecHistUpdTs_BA" />
								<ezf:inputHidden name="xxRecHistUpdByNm_BA" ezfName="xxRecHistUpdByNm_BA" />
								<ezf:inputHidden name="xxRecHistTblNm_BA" ezfName="xxRecHistTblNm_BA" />
							</td>
							<td class="stab">Control Count</td>
							<td><ezf:inputText name="apCtrlCnt_BA" ezfName="apCtrlCnt_BA" value="123,456,789,012" otherAttr=" size=\"15\" maxlength=\"15\""/>
								<ezf:inputHidden name="xxRecHistCratTs_BA" ezfName="xxRecHistCratTs_BA" />
								<ezf:inputHidden name="xxRecHistCratByNm_BA" ezfName="xxRecHistCratByNm_BA" />
								<ezf:inputHidden name="xxRecHistUpdTs_BA" ezfName="xxRecHistUpdTs_BA" />
								<ezf:inputHidden name="xxRecHistUpdByNm_BA" ezfName="xxRecHistUpdByNm_BA" />
								<ezf:inputHidden name="xxRecHistTblNm_BA" ezfName="xxRecHistTblNm_BA" />
							</td>
						</tr>
						<tr height="22">
							<td class="stab">Batch Date</td>
							<td><ezf:inputText name="apBatDt_BA" ezfName="apBatDt_BA" value="12/31/2016" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'apBatDt_BA', 4 );">
								<ezf:inputHidden name="xxRecHistCratTs_BA" ezfName="xxRecHistCratTs_BA" />
								<ezf:inputHidden name="xxRecHistCratByNm_BA" ezfName="xxRecHistCratByNm_BA" />
								<ezf:inputHidden name="xxRecHistUpdTs_BA" ezfName="xxRecHistUpdTs_BA" />
								<ezf:inputHidden name="xxRecHistUpdByNm_BA" ezfName="xxRecHistUpdByNm_BA" />
								<ezf:inputHidden name="xxRecHistTblNm_BA" ezfName="xxRecHistTblNm_BA" />
							</td>
							<td class="stab">Running Total</td>
							<td><ezf:inputText name="apRunTotAmt_BA" ezfName="apRunTotAmt_BA" value="123,456,789,012,345.0000" otherAttr=" size=\"24\" maxlength=\"24\""/>
								<ezf:inputHidden name="xxRecHistCratTs_BA" ezfName="xxRecHistCratTs_BA" />
								<ezf:inputHidden name="xxRecHistCratByNm_BA" ezfName="xxRecHistCratByNm_BA" />
								<ezf:inputHidden name="xxRecHistUpdTs_BA" ezfName="xxRecHistUpdTs_BA" />
								<ezf:inputHidden name="xxRecHistUpdByNm_BA" ezfName="xxRecHistUpdByNm_BA" />
								<ezf:inputHidden name="xxRecHistTblNm_BA" ezfName="xxRecHistTblNm_BA" />
							</td>
							<td class="stab">Running Count</td>
							<td><ezf:inputText name="apRunTotCnt_BA" ezfName="apRunTotCnt_BA" value="123,456,789,012" otherAttr=" size=\"15\" maxlength=\"15\""/>
								<ezf:inputHidden name="xxRecHistCratTs_BA" ezfName="xxRecHistCratTs_BA" />
								<ezf:inputHidden name="xxRecHistCratByNm_BA" ezfName="xxRecHistCratByNm_BA" />
								<ezf:inputHidden name="xxRecHistUpdTs_BA" ezfName="xxRecHistUpdTs_BA" />
								<ezf:inputHidden name="xxRecHistUpdByNm_BA" ezfName="xxRecHistUpdByNm_BA" />
								<ezf:inputHidden name="xxRecHistTblNm_BA" ezfName="xxRecHistTblNm_BA" />
							</td>
						</tr>
					</table>
					<hr size="1" noshade>
					<table border="0" cellpadding="0" cellspacing="0" width="1100" align="center">
						<col width="90">
						<col width="160">
						<col width="140">
						<col width="390">
						<col width="120">
						<col width="180">
						<tr height="22">
							<td class="stab"><ezf:anchor name="xxLinkAncr_ID" ezfName="xxLinkAncr_ID" ezfEmulateBtn="1" ezfGuard="OpenWin_WorkFolder" otherAttr=" id=\"xxLinkAncr_ID\"">Therefore</ezf:anchor></td>
							<td><ezf:anchor name="xxSrvUrlTxt" ezfName="xxSrvUrlTxt" onClick="window.open( this.href, 'therefore', 'width=1000,height=700,scrollbars=yes,status=yes' );return false;" otherAttr=" ezfanchortext=\"\""><ezf:label name="docMgtDocId_IH" ezfName="docMgtDocId_IH" /></ezf:anchor></td>
							<td class="stab">Current Approver</td>
							<td><ezf:inputText name="varCharConstVal_IH" ezfName="varCharConstVal_IH" value="3rd Party Approve Group" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td class="stab">Invoice Amount</td>
							<td><ezf:inputText name="apInvAmt_IH" ezfName="apInvAmt_IH" value="123,456,789,012,345.0000" otherAttr=" size=\"24\" maxlength=\"24\""/></td>
						</tr>
						<tr height="22">
							<td class="stab">Workflow Status</td>
							<td><ezf:inputText name="apDsWfStsNm_IH" ezfName="apDsWfStsNm_IH" value="123456789012345678901234567890" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="prntVndCd_L" ezfName="prntVndCd_L" ezfEmulateBtn="1" ezfGuard="OpenWin_PrntVnd" otherAttr=" id=\"prntVndCd_L\"">Supplier</ezf:anchor></td>
							<td><ezf:inputText name="prntVndCd_IH" ezfName="prntVndCd_IH" value="123456789012345678901234567890" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
							    <ezf:inputButton name="SearchSupplierName" value=">>" htmlClass="pBtn1" />
							    <ezf:inputText name="prntVndNm_IH" ezfName="prntVndNm_IH" value="123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"30\" maxlength=\"240\" ezftoupper=\"\""/></td>
							<td class="stab">Misc. Header Amount</td>
							<td><ezf:inputText name="apMiscAmt_IH" ezfName="apMiscAmt_IH" value="123,456,789,012,345.0000" otherAttr=" size=\"24\" maxlength=\"24\""/></td>
						</tr>
						<tr height="22">
							<td class="stab">Invoice #</td>
							<td><ezf:inputText name="apInvNum_IH" ezfName="apInvNum_IH" value="123456789012345" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="vndSiteNm_L" ezfName="vndSiteNm_L" ezfEmulateBtn="1" ezfGuard="OpenWin_Vnd" otherAttr=" id=\"vndSiteNm_L\"">Supplier Site Name</ezf:anchor></td>
							<td><ezf:inputText name="vndSiteNm_IH" ezfName="vndSiteNm_IH" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
							<td class="stab">Tax Amount</td>
							<td><ezf:inputText name="apTaxAmt_IH" ezfName="apTaxAmt_IH" value="123,456,789,012,345.0000" otherAttr=" size=\"24\" maxlength=\"24\""/><td>
						</tr>
						<tr height="22">
							<td class="stab">Invoice Date</td>
							<td><ezf:inputText name="invDt_IH" ezfName="invDt_IH" value="12/31/2016" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'invDt_IH', 4 );"></td>
							<td class="stab">Maintenance Invoice Status</td>
							<td><ezf:inputText name="apMaintInvStsNm_IH" ezfName="apMaintInvStsNm_IH" value="Invoice Batch Entry Completed" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
							<td class="stab">Late Fee Amount</td>
							<td><ezf:inputText name="lateFeeAmt_IH" ezfName="lateFeeAmt_IH" value="123,456,789,012,345.0000" otherAttr=" size=\"24\" maxlength=\"24\""/><td>
						</tr>
					</table>
					<hr size="1" noshade>
					<table border="0" cellpadding="0" cellspacing="0" width="1100" align="center">
						<col width="135">
						<col width="58">
						<col width="70">
						<col width="147">
						<col width="80">
						<col width="130">
						<col width="80">
						<col width="130">
						<col width="50">
						<col width="200">
						<col width="90">
						<tr height="22">
							<td class="stab">Override Serial flg</td>
							<td><ezf:inputCheckBox name="ovrdSerNum_AD" ezfName="ovrdSerNum_AD" value="Y" /></td>
							<td class="stab"><ezf:anchor name="serNum_L" ezfName="serNum_L" ezfEmulateBtn="1" ezfGuard="OpenWin_Serial" otherAttr=" id=\"serNum_L\"">Serial #</ezf:anchor></td>
							<td><ezf:inputText name="serNum_AD" ezfName="serNum_AD" value="123456789012345678901234567890" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
							<td class="stab">Start Date</td>
							<td><ezf:inputText name="startDt_AD" ezfName="startDt_AD" value="12/31/2016" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'startDt_AD', 4 );"></td>
							<td class="stab">End Date</td>
							<td><ezf:inputText name="endDt_AD" ezfName="endDt_AD" value="12/31/2016" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'endDt_AD', 4 );"></td>
							<td class="stab">Base</td>
							<td><ezf:inputText name="baseAmt_AD" ezfName="baseAmt_AD" value="123,456,789,012,345.0000" otherAttr=" size=\"24\" maxlength=\"24\""/></td>
							<td align="right"><ezf:inputButton name="AddSerial" value="Add Serial" htmlClass="pBtn7" /></td>
						</tr>
						<tr height="22">
							<td colspan="11">
								<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn7" />
								<ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn7" />
								<ezf:inputButton name="AddCounterTp" value="Add Counter" htmlClass="pBtn7" />
							</td>
						</tr>
					</table>

					<table border="0" cellpadding="0" cellspacing="0"width="1100">
						<tr>
							<td align="left"></td>
						</tr>
					</table>

					<table border="0" cellpadding="1" cellspacing="0" width="1100" align="center">
						<tr>
							<td>
								<div id="TBL_Top">
									<table border="1" cellpadding="1" cellspacing="0" width="1083" height="34">
										<col align="center" width="25" >
										<col align="center" width="94" >
										<col align="center" width="108" >
										<col align="center" width="94" >
										<col align="center" width="94" >
										<col align="center" width="94" >
										<col align="center" width="96" >
										<col align="center" width="101" >
										<col align="center" width="101" >
										<col align="center" width="94" >
										<col align="center" width="94" >
										<tr valign="middle">
											<td class="pClothBs">&nbsp;</td>
											<td class="pClothBs">Serial#</td>
											<td class="pClothBs">Override Serial flg</td>
											<td class="pClothBs">Start Date</td>
											<td class="pClothBs">End Date</td>
											<td class="pClothBs">Base</td>
											<td class="pClothBs">Counter Type</td>
											<td class="pClothBs">Start MTR</td>
											<td class="pClothBs">End MTR</td>
											<td class="pClothBs">Count</td>
											<td class="pClothBs">Overage</td>
										</tr>
									</table>
								</div>
								<div id="TBL_Bottom" style="overflow-y:scroll; height:242; width:1100;">
									<table border="1" cellpadding="1" cellspacing="0" width="1083" id="A">
										<col align="left" width="25" >
										<col align="left" width="94" >
										<col align="center" width="108" >
										<col align="left" width="94" >
										<col align="left" width="94" >
										<col align="left" width="94" >
										<col align="left" width="96" >
										<col align="left" width="101" >
										<col align="left" width="101" >
										<col align="left" width="94" >
										<col align="left" width="94" >
										<ezf:row ezfHyo="A">
											<tr height="24">
												<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
												<td><ezf:inputCheckBox name="ovrdSerNum_A1" ezfName="ovrdSerNum_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="startDt_A1" ezfName="startDt_A1" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'startDt_A1', 4, '{EZF_ROW_NUMBER}');"></td>
												<td><ezf:inputText name="endDt_A1" ezfName="endDt_A1" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'endDt_A1', 4, '{EZF_ROW_NUMBER}');"></td>
												<td><ezf:inputText name="baseAmt_A1" ezfName="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"24\" ezftoupper=\"\""/></td>
												<td><ezf:select name="cntrTpCd_A1" ezfName="cntrTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="cntrTpCd_C" ezfDispName="cntrTpNm_D" ezfCodeDispHyo="A" otherAttr=" style=\"width:90px;\""/></td>
												<td><ezf:inputText name="startReadMtrCnt_A1" ezfName="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
												<td><ezf:inputText name="endReadMtrCnt_A1" ezfName="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
												<td><ezf:inputText name="readMtrCnt_A1" ezfName="readMtrCnt_A1" value="W,WWW,WWW,WWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"13\""/></td>
												<td><ezf:inputText name="apTolAmt_A1" ezfName="apTolAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"24\" ezftoupper=\"\""/></td>
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
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td>&nbsp;</td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox#{EZF_ROW_NUMBER}"></td>
												<td><input type="text" name="serNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="serNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="ovrdSerNum_A1" value="WWWWWWWWWWWWWWWWWWWW" size="12" maxlength="30" ezftoupper="" ezfHyo="A" ezfname="ovrdSerNum_A1" class="pPro" readOnly></td>
												<td><input type="text" name="startDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="startDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endDt_A1" value="MM/DD/YYYY" size="10" maxlength="10" ezfHyo="A" ezfname="endDt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="baseAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="baseAmt_A1" class="pPro" readOnly></td>
												<td><select name="cntrTpCd_A1" ezfname="cntrTpCd_A1" ezfHyo="A" ezflist="cntrTpCd_C,cntrTpNm_D,99, ,blank" style="width:105px;">
													<option></option>
													<option>BW</option>
													<option>CLR</option>
												    </select></td>
												<td><input type="text" name="startReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="startReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="endReadMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="endReadMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="readMtrCnt_A1" value="W,WWW,WWW,WWW" size="12" maxlength="13" ezfHyo="A" ezfname="readMtrCnt_A1" class="pPro" readOnly></td>
												<td><input type="text" name="apTotAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" size="12" maxlength="24" ezftoupper="" ezfHyo="A" ezfname="apTotAmt_A1" class="pPro" readOnly></td>
											</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
					</table>

					<table border="0" cellpadding="0" cellspacing="0" width="1100" align="center">
						<col width="550">
						<col width="550">
						<tr height="22">
							<td>
								<ezf:inputButton name="DeleteSerial" value="Delete Serial" htmlClass="pBtn7" />
								<ezf:inputButton name="DeleteCounterTp" value="Delete Counter" htmlClass="pBtn7" />
							</td>
							<td align="right">
								<ezf:inputButton name="PrevInvoice" value="Prev Invoice" htmlClass="pBtn7" />
								<ezf:inputButton name="NextInvoice" value="Next Invoice" htmlClass="pBtn7" />
								<ezf:inputButton name="DeleteInvoice" value="Delete Invoice" htmlClass="pBtn7" />
								<ezf:inputButton name="Summary" value="Summary" htmlClass="pBtn7" />
							</td>
						</tr>
					</table>
					<hr size="1" noshade>
					<table border="0" cellpadding="0" cellspacing="0" width="1100" align="center">
						<col width="60">
						<col width="680">
						<col width="100">
						<col width="90">
						<col width="50">
						<col width="220">
						<tr height="22">
							<td class="stab" valign="top" rowspan="3">Comments</td>
							<td rowspan="3">
								<ezf:textArea name="invCmntTxt_CO" ezfName="invCmntTxt_CO" otherAttr=" cols=\"80\" rows=\"3\" maxlength=\"260\""/>
							</td>
							<td class="stab" colspan="3">Adjusted Invoice Amount</td>
							<td class="stab" align="right"><ezf:inputText name="apAdjAmt_CO" ezfName="apAdjAmt_CO" value="123,456,789,012,345.0000" otherAttr=" size=\"24\" maxlength=\"24\""/></td>
						</tr>
						<tr height="22">
							<td class="stab">Adjusted Reason</td>
							<td class="stab" colspan="3" align="right">
							    <ezf:select name="apAdjRsnCd_CO" ezfName="apAdjRsnCd_CO" ezfBlank="1" ezfCodeName="apAdjRsnCd_C" ezfDispName="apAdjRsnNm_D" otherAttr=" style=\"width:230px;\""/>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<ezf:inputHidden name="docMgtDocId" ezfName="docMgtDocId" otherAttr=" size=\"0\" id=\"docMgtDocId\""/>
<ezf:inputHidden name="docMgtCatgCd" ezfName="docMgtCatgCd" otherAttr=" size=\"0\" id=\"docMgtCatgCd\""/>

							

<%-- **** Task specific area ends here **** --%>
