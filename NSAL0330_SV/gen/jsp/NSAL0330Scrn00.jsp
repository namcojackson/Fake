<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170803142814 --%>
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
			<input type="hidden" name="pageID" value="NSAL0330Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Charge View Pricing - Base">

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
										<li title="View Prc Base" class="pTab_ON"><a href="#">View Prc Base</a></li>
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
						<col width="320">
						<col width="320">
						<col width="320">
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="90">
									<col width="120">
									<tr height="21">
										<td class="stab">Contract#</td>
										<td colspan="2"><ezf:inputText name="dsContrNum_H1" ezfName="dsContrNum_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"30\" maxlength=\"30\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
									<tr height="21">
										<td class="stab">Serial#</td>
										<td><ezf:inputText name="serNum_H1" ezfName="serNum_H1" value="XXXXXXXXX1XXXXX" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
									<tr height="21">
										<td class="stab"><br /></td>
										<td ><br /></td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="80">
									<col width="80">
									<col width="10">
									<col width="80">
									<col width="65">
									<tr height="21">
										<td class="stab">Effective Date</td>
										<td><ezf:inputText name="contrEffFromDt_H1" ezfName="contrEffFromDt_H1" value="12/31/2015" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/></td>
										<td>-</td>
										<td><ezf:inputText name="contrEffThruDt_H1" ezfName="contrEffThruDt_H1" value="12/31/2015" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/></td>
										<td><br/></td>
									</tr>
									<tr height="21">
										<td class="stab">Billing Timing</td>
										<td colspan="4"><ezf:select name="baseBllgTmgCd_H1" ezfName="baseBllgTmgCd_H1" ezfBlank="1" ezfCodeName="bllgTmgTpCd_H1" ezfDispName="bllgTmgTpNm_H2" /></td>
									</tr>
									<tr height="21">
										<td class="stab">Period End Date</td>
										<td colspan="4">
											<ezf:select name="baseDplyPerEndDay_H1" ezfName="baseDplyPerEndDay_H1" ezfCodeName="baseDplyPerEndDay_BC" ezfDispName="xxEdtDescTxt_BC" otherAttr=" style=\"width:90\""/>
										</td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="75">
									<col width="150">
									<col width="90">
									<tr height="21">
										<td class="stab">Billed Upto</td>
										<td colspan="2"><ezf:inputText name="baseBllgLastBllgDt_H1" ezfName="baseBllgLastBllgDt_H1" value="12/31/2015" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
									<tr height="21">
										<td class="stab">Term Amount</td>
										<td><ezf:inputText name="invTotAmt_H1" ezfName="invTotAmt_H1" otherAttr=" size=\"20\""/></td>
										<td><ezf:inputText name="ccyCd_H1" ezfName="ccyCd_H1" otherAttr=" size=\"3\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
									<tr height="21">
										<td class="stab">Invoice Date</td>
										<td colspan="2">
											<ezf:select name="contrBllgDay_H1" ezfName="contrBllgDay_H1" ezfCodeName="contrBllgDay_BB" ezfDispName="xxEdtDescTxt_BB" otherAttr=" style=\"width:90\""/>
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
								<table border="0" cellpadding="1" cellspacing="0" width="100%" align="center">
									<tr>
										<td>
											<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:none; width:1100; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col align="center" width="25">
														<col align="center" width="60">		<!-- PE Seq# -->
														<col align="center" width="50">		<!-- TS Seq# -->
														<col align="center" width="60">		<!-- Periods -->
														<col align="center" width="110">	<!-- Sched.UOM -->
														<col align="center" width="110">	<!-- Sched.From Date -->
														<col align="center" width="110">	<!-- Sched.Thru Date -->
														<col align="center" width="175">	<!-- Billing Cycle -->
														<col align="center" width="170">	<!-- Price/Period -->
														<col align="center" width="170">	<!-- Scheduled Amount -->
														<col align="center" width="70">		<!-- Currency -->
														<tr>
															<td class="pClothBs"><br /></td>
															<td class="pClothBs">PE Seq#</td>
															<td class="pClothBs">TS Seq#</td>
															<td class="pClothBs">Periods</td>
															<td class="pClothBs">Sched.UOM</td>
															<td class="pClothBs">Sched.From Date</td>
															<td class="pClothBs">Sched.Thru Date</td>
															<td class="pClothBs">Billing Cycle</td>
															<td class="pClothBs">Price/Period</td>
															<td class="pClothBs">Scheduled Amount</td>
															<td class="pClothBs">Currency</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_A" style="overflow-y:scroll; height:310; overflow-x:hidden; width:1129; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
														<col align="center" width="25">
														<col align="center" width="60">		<!-- PE Seq# -->
														<col align="center" width="50">		<!-- TS Seq# -->
														<col align="center" width="60">		<!-- Periods -->
														<col align="center" width="110">	<!-- Sched.UOM -->
														<col align="center" width="110">	<!-- Sched.From Date -->
														<col align="center" width="110">	<!-- Sched.Thru Date -->
														<col align="center" width="175">	<!-- Billing Cycle -->
														<col align="center" width="170">	<!-- Price/Period -->
														<col align="center" width="170">	<!-- Scheduled Amount -->
														<col align="center" width="70">		<!-- Currency -->
													<ezf:row ezfHyo="A">
														<tr ezfhyo="A" id="id_leftA_row{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputText name="dsContrPrcEffSqNum_A1" ezfName="dsContrPrcEffSqNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td><ezf:inputText name="dsContrBllgSchdSqNum_A1" ezfName="dsContrBllgSchdSqNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td><ezf:inputText name="perSchdNum_A1" ezfName="perSchdNum_A1" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnChangePeriods" otherAttr=" size=\"4\" maxlength=\"4\" ezffocusout=\"OnChangePeriods\""/></td>
															<td><ezf:select name="perBllgCycleCd_A1" ezfName="perBllgCycleCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleUomCd_A1" ezfDispName="bllgCycleUomNm_A2" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServer('OnChangeUOM', '{EZF_ROW_NUMBER}')\"" /></td>
															<td><ezf:inputText name="bllgSchdFromDt_A1" ezfName="bllgSchdFromDt_A1" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnChangeFromDate" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdFromDt_A1#{EZF_ROW_NUMBER}\" ezffocusout=\"OnChangeFromDate\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdFromDt_A1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><ezf:inputText name="bllgSchdThruDt_A1" ezfName="bllgSchdThruDt_A1" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnChangeThruDate" otherAttr=" size=\"10\" maxlength=\"10\" id=\"bllgSchdThruDt_A1#{EZF_ROW_NUMBER}\" ezffocusout=\"OnChangeThruDate\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgSchdThruDt_A1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><ezf:inputText name="bllgCycleDescTxt_A1" ezfName="bllgCycleDescTxt_A1" value="Contract Period" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="basePrcDealAmt_A1" ezfName="basePrcDealAmt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="baseSubTotPrcDealAmt_A1" ezfName="baseSubTotPrcDealAmt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"23\""/></td>
															<td><ezf:inputText name="ccyCd_A1" ezfName="ccyCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
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
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="70">
												<col align="left" width="70">
												<col align="left" width="70">
												<col align="left" width="70">
												<col>
												<col>
												<col align="right" width="70">
												<col align="right" width="70">
												<tr>
													<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn6" /></td>
													<td><br /></td>
													<td><br /></td>
													<td><ezf:inputButton name="Schedules" value="Schedules" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="SkipMonth" value="Skip Month" htmlClass="pBtn6" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
					<table width="98%" border="0" align="center" style="table-layout:fixed;">
						<col width="80">
						<col width="900">
						<tr>
							<td class="stab">Reason Code</td>
							<td><ezf:select name="svcMemoRsnCd_F3" ezfName="svcMemoRsnCd_F3" ezfBlank="1" ezfCodeName="svcMemoRsnCd_F1" ezfDispName="svcMemoRsnNm_F2" /></td>
						</tr>
						<tr>
							<td class="stab">Comment</td>
							<td><ezf:textArea name="svcCmntTxt_F1" ezfName="svcCmntTxt_F1" otherAttr=" cols=\"130\" rows=\"5\""/></td>
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
