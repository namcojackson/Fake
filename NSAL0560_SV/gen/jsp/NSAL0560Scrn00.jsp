<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170821115116 --%>
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
			<input type="hidden" name="pageID" value="NSAL0560Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Base Pricing Effectivity">

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
										<li title="Base Pricing Effectivity" class="pTab_ON"><a href="#">Base Prc Efftv</a></li>
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
						<col>
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="80">
									<col width="120">
									<col width="120">
									<tr height="21">
										<td class="stab">Contract#</td>
										<td colspan="2"><ezf:inputText name="dsContrNum_H1" ezfName="dsContrNum_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"30\" maxlength=\"30\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
									<tr height="21">
										<td class="stab">Serial#</td>
										<td><ezf:inputText name="serNum_H1" ezfName="serNum_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;\""/></td>
										<td><ezf:inputText name="t_MdlNm_H1" ezfName="t_MdlNm_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="90">
									<col width="80">
									<col width="10">
									<col width="80">
									<col width="70">
									<tr height="21">
										<td class="stab">Effective Date</td>
										<td><ezf:inputText name="contrEffFromDt_H1" ezfName="contrEffFromDt_H1" value="01/01/2014" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/></td>
										<td>-</td>
										<td><ezf:inputText name="contrEffThruDt_H1" ezfName="contrEffThruDt_H1" value="12/31/2014" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/></td>
										<td><br/></td>
									</tr>
									<tr height="21">
										<td class="stab">Service Program</td>
										<td colspan="4"><ezf:inputText name="mdseDescShortTxt_H1" ezfName="mdseDescShortTxt_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
								</table>
							</td>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="90">
									<col width="150">
									<col width="90">
									<tr height="21">
										<td class="stab">Billed Upto</td>
										<td colspan="2"><ezf:inputText name="baseBllgLastBllgDt_H1" ezfName="baseBllgLastBllgDt_H1" value="12/31/2014" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/></td>
									</tr>
									<tr height="21">
										<td class="stab">Bill To Customer</td>
										<td colspan="2"><ezf:inputText name="xxFirstScdCtyStAddr_H1" ezfName="xxFirstScdCtyStAddr_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXX10XXXXXXXX11XXXXXXXX12XXXXXXXX13XXXXXXXX14XXXXXXXX15" otherAttr=" size=\"34\" style=\"border:none; background-color:transparent;\""/></td>
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
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:none; width:890; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col align="center" width=" 30">
														<col align="center" width=" 60">	<!-- PE Seq No -->
														<col align="center" width="120">	<!-- Start Date -->
														<col align="center" width="120">	<!-- End Date -->
														<col align="center" width="100">	<!-- Frequency -->
														<col align="center" width="140">	<!-- Price/Period -->
														<col align="center" width="140">	<!-- Term Amount -->
														<col align="center" width=" 80">	<!-- Status -->
														<col align="center" width="100">	<!-- Creation Date -->
														<tr>
															<td class="pClothBs"><br /></td>
															<td class="pClothBs">PE Seq No</td>
															<td class="pClothBs">Start Date</td>
															<td class="pClothBs">End Date</td>
															<td class="pClothBs">Frequency</td>
															<td class="pClothBs">Price/Period</td>
															<td class="pClothBs">Term Amount</td>
															<td class="pClothBs">Status</td>
															<td class="pClothBs">Creation Date</td>
														</tr>
												</table>
											</div>
											<div id="LeftTable_A" style="overflow-y:scroll; height:345; overflow-x:hidden; width:909; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
														<col align="center" width=" 30">
														<col align="center" width=" 60">	<!-- PE Seq No -->
														<col align="center" width="120">	<!-- Start Date -->
														<col align="center" width="120">	<!-- End Date -->
														<col align="center" width="100">	<!-- Frequency -->
														<col align="center" width="140">	<!-- Price/Period -->
														<col align="center" width="140">	<!-- Term Amount -->
														<col align="center" width=" 80">	<!-- Status -->
														<col align="center" width="100">	<!-- Creation Date -->
													<ezf:row ezfHyo="A">
														<tr ezfhyo="A">
															<td><ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" /></td>
															<td><ezf:inputText name="dsContrPrcEffSqNum_A1" ezfName="dsContrPrcEffSqNum_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td><ezf:inputText name="contrPrcEffFromDt_A1" ezfName="contrPrcEffFromDt_A1" value="01/01/2014" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnChange_RecalcTermAmount" otherAttr=" size=\"10\" maxlength=\"10\" id=\"contrPrcEffFromDt_A1#{EZF_ROW_NUMBER}\" ezftoupper=\"\" ezffocusout=\"OnChange_RecalcTermAmount\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrPrcEffFromDt_A1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><ezf:inputText name="contrPrcEffThruDt_A1" ezfName="contrPrcEffThruDt_A1" value="01/31/2014" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnChange_RecalcTermAmount" otherAttr=" size=\"10\" maxlength=\"10\" id=\"contrPrcEffThruDt_A1#{EZF_ROW_NUMBER}\" ezftoupper=\"\" ezffocusout=\"OnChange_RecalcTermAmount\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrPrcEffThruDt_A1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><ezf:select name="bllgCycleCd_A3" ezfName="bllgCycleCd_A3" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleCd_A1" ezfDispName="bllgCycleNm_A2" otherEvent1=" onchange=\"sendServer('OnChange_RecalcTermAmount','{EZF_ROW_NUMBER}');\"" otherAttr=" style=\"width:95;\""/></td>
															<td><ezf:inputText name="basePrcDealAmt_A1" ezfName="basePrcDealAmt_A1" value="100.00" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnChange_RecalcTermAmount" otherAttr=" style=\"text-align:right\" size=\"16\" maxlength=\"23\" ezffocusout=\"OnChange_RecalcTermAmount\""/></td>
															<td><ezf:inputText name="basePrcTermDealAmtRate_A1" ezfName="basePrcTermDealAmtRate_A1" value="100.00" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnChange_RecalcTotalTermAmount" otherAttr=" style=\"text-align:right\" size=\"16\" maxlength=\"23\" ezffocusout=\"OnChange_RecalcTotalTermAmount\""/></td>
															<td><ezf:inputText name="dsContrDtlStsNm_A1" ezfName="dsContrDtlStsNm_A1" value="ACTIVE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
															<td><ezf:inputText name="cratDt_A1" ezfName="cratDt_A1" value="01/01/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\""/></td>
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
														<tr ezfhyo="A">
															<td><input type="radio" value="Y" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfArrayNo="0"></td>
															<td><input type="text" class="pPro" size="3" maxlength="3" value="2" name="dsContrPrcEffSqNum_A1" ezfname="dsContrPrcEffSqNum_A1" ezfhyo="A" value="123"></td>
															<td><input type="text" size="10" maxlength="10" value="02/01/2014" name="contrPrcEffFromDt_A1" ezfname="contrPrcEffFromDt_A1" ezftoupper ezfhyo="A" onblur="sendServer('OnChange_RecalcTermAmount','{EZF_ROW_NUMBER}');"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrPrcEffFromDt_A1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><input type="text" size="10" maxlength="10" value="03/31/2014" name="contrPrcEffThruDt_A1" ezfname="contrPrcEffThruDt_A1" ezftouppers ezfhyo="A" onblur="sendServer('OnChange_RecalcTermAmount','{EZF_ROW_NUMBER}');"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrPrcEffThruDt_A1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><select class="pHsu" name="bllgCycleCd_A3" ezfname="bllgCycleCd_A3" ezflist="bllgCycleCd_A1,bllgCycleNm_A2,99, ,blank" ezfhyo="A" onChange="sendServer('OnChange_RecalcTermAmount','{EZF_ROW_NUMBER}');"><option>DAYS</option><option selected>MTH</option><option>YR</option></select></td>
															<td><input type="text" size="16" maxlength="23" value="110.00" name="basePrcDealAmt_A1" ezfname="basePrcDealAmt_A1" ezfhyo="A" onChange="sendServer('OnChange_RecalcTermAmount','{EZF_ROW_NUMBER}');"></td>
															<td><input type="text" size="16" maxlength="23" value="220.00" name="basePrcTermDealAmtRate_A1" ezfname="basePrcTermDealAmtRate_A1" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="ACTIVE" name="dsContrDtlStsNm_A1" ezfname="dsContrDtlStsNm_A1" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="01/01/2014" name="cratDt_A1" ezfname="cratDt_A1" ezftoupper ezfhyo="A"></td>
														</tr>
														<tr ezfhyo="A">
															<td><input type="radio" value="Y" name="xxRadioBtn_A" ezfname="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfArrayNo="0"></td>
															<td><input type="text" class="pPro" size="3" maxlength="3" value="3" name="dsContrPrcEffSqNum_A1" ezfname="dsContrPrcEffSqNum_A1" ezfhyo="A" value="123"></td>
															<td><input type="text" size="10" maxlength="10" value="04/01/2014" name="contrPrcEffFromDt_A1" ezfname="contrPrcEffFromDt_A1" ezftoupper ezfhyo="A" onblur="sendServer('OnChange_RecalcTermAmount','{EZF_ROW_NUMBER}');"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrPrcEffFromDt_A1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><input type="text" size="10" maxlength="10" value="12/31/2014" name="contrPrcEffThruDt_A1" ezfname="contrPrcEffThruDt_A1" ezftoupper ezfhyo="A" onblur="sendServer('OnChange_RecalcTermAmount','{EZF_ROW_NUMBER}');"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrPrcEffThruDt_A1#{EZF_ROW_NUMBER}', 4);"></td>
															<td><select class="pHsu" name="bllgCycleCd_A3" ezfname="bllgCycleCd_A3" ezflist="bllgCycleCd_A1,bllgCycleNm_A2,99, ,blank" ezfhyo="A" onChange="sendServer('OnChange_RecalcTermAmount','{EZF_ROW_NUMBER}');"><option>DAYS</option><option selected>MTH</option><option>YR</option></select></td>
															<td><input type="text" size="16" maxlength="23" value="100.00" name="basePrcDealAmt_A1" ezfname="basePrcDealAmt_A1" ezfhyo="A" onChange="sendServer('OnChange_RecalcTermAmount','{EZF_ROW_NUMBER}');"></td>
															<td><input type="text" size="16" maxlength="23" value="900.00" name="basePrcTermDealAmtRate_A1" ezfname="basePrcTermDealAmtRate_A1" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="ACTIVE" name="dsContrDtlStsNm_A1" ezfname="dsContrDtlStsNm_A1" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="10" value="01/01/2014" name="cratDt_A1" ezfname="cratDt_A1" ezftoupper ezfhyo="A"></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div style="overflow-y:hidden; height:25; overflow-x:hidden; width:909; float:left;">
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout:fixed">
														<col align="center" width="710">
														<col align="center" width=" 80">
														<col align="center" width="100">
														<tr>
															<td></td>
															<td style="text-align:right">Total</td>
															<td><ezf:inputText name="basePrcTermDealAmtRate_H1" ezfName="basePrcTermDealAmtRate_H1" value="1,220.00" otherAttr=" style=\"text-align:right\" size=\"10\" style=\"border:none; background-color:transparent;\""/></td>
														</tr>
												</table>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%">
												<col align="left" width="70">
												<col align="left" width="70">
												<col>
												<col>
												<col align="right" width="70">
												<col align="right" width="70">
												<col width="80">
												<tr>
													<td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn6" /></td>
													<td><br /></td>
													<td><br /></td>
													<td><ezf:inputButton name="TopSchedules" value="Top Scheduling" htmlClass="pBtn8" /></td>
													<td><ezf:inputButton name="MoveWin_SkipMonths" value="Skip Month" htmlClass="pBtn6" /></td>
													<td><br /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" style="table-layout:fixed;">
						<col width="80">
						<col width="900">
						<tr>
							<td class="stab">Reason Code</td>
							<td><ezf:select name="svcMemoRsnCd_H3" ezfName="svcMemoRsnCd_H3" ezfBlank="1" ezfCodeName="svcMemoRsnCd_A1" ezfDispName="svcMemoRsnNm_A2" /></td>
						</tr>
						<tr>
							<td class="stab">Comment</td>
							<td><ezf:textArea name="svcCmntTxt_H1" ezfName="svcCmntTxt_H1" otherAttr=" cols=\"130\" rows=\"5\""/></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
