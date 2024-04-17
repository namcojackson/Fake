<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190124160055 --%>
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

			<!-- QC#18799(L3#127) Set Default Rule And Add New # Days  -->
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NSAL0740Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Change Annual Escalation">
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
										<li title="Change Annual Escalation" class="pTab_ON"><a href="#">Chng Ann Escl</a></li>
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
									<col width="125">
									<col width="190">
									<col width=" 90">
									<col width=" 70">
									<col width=" 70">
									<col width="450">
									<tr>
										<td class="stab">New Escalation Type</td>
										<td><ezf:select name="contrUplftTpCd_H3" ezfName="contrUplftTpCd_H3" ezfBlank="1" ezfCodeName="contrUplftTpCd_H1" ezfDispName="contrUplftTpNm_H2" otherEvent1=" onchange=\"sendServer('OnChangeNewEsclTpCd')\"" /></td>
										<td class="stab">New Base%</td>
										<td><ezf:inputText name="uplftBasePrcUpRatio_H1" ezfName="uplftBasePrcUpRatio_H1" otherAttr=" style=\"text-align:right\" size=\"5\" maxlength=\"5\""/></td>
										<td class="stab">Reason Code</td>
										<td><ezf:select name="svcMemoRsnCd_H3" ezfName="svcMemoRsnCd_H3" ezfBlank="1" ezfCodeName="svcMemoRsnCd_H1" ezfDispName="svcMemoRsnNm_H2" /></td>
									</tr>
									<tr>
										<td class="stab">New Escalation Method</td>
										<td><ezf:select name="uplftPrcMethCd_H3" ezfName="uplftPrcMethCd_H3" ezfBlank="1" ezfCodeName="uplftPrcMethCd_H1" ezfDispName="uplftPrcMethNm_H2" otherEvent1=" onchange=\"sendServer('OnChangeNewEsclMethod')\"" /></td>
										<td class="stab">New Overage%</td>
										<td><ezf:inputText name="uplftMtrPrcUpRatio_H1" ezfName="uplftMtrPrcUpRatio_H1" otherAttr=" style=\"text-align:right\" size=\"5\" maxlength=\"5\""/></td>
										<td rowspan="3" class="stab">Notes</td>
										<td rowspan="3"><ezf:textArea name="svcCmntTxt_H1" ezfName="svcCmntTxt_H1" otherAttr=" cols=\"60\" rows=\"4\""/></td>
									</tr>
									<tr>
										<td class="stab">New # Days</td>
										<td><ezf:inputText name="befEndUplftDaysAot_H1" ezfName="befEndUplftDaysAot_H1" value="123" otherAttr=" size=\"4\" maxlength=\"3\""/></td>
										<td colspan="2" rowspan="2"><ezf:inputButton name="ApplyToAll" value="APPLY TO ALL" htmlClass="pBtn8" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>

					</table>
<%-- #################################################### DETAIL ################################################### --%>
					<%@ page import="business.servlet.NSAL0740.NSAL0740BMsg" %>
					<% NSAL0740BMsg bMsg = (NSAL0740BMsg)databean.getEZDBMsg(); %>
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
					<table>
						<tr>
							<td>
								<table  border="0" cellpadding="0" cellspacing="0" width="1090">
									<col width="" align="right" valign="top">
									<tr>
										<td>
											<ezf:skip>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="54"  align="center">
													<col width="32"  align="right">
													<col width="16"  align="center">
													<col width="32"  align="right">
													<col width="16"  align="center">
													<col width="32"  align="right">
													<col width="10">
													<col>
													<col width="1">
													<col>
													<tr>
														<td class="stab">Showing</td>
														<td class="pOut">0</td>
														<td class="stab">to</td>
														<td class="pOut">0</td>
														<td class="stab">of</td>
														<td class="pOut">0</td>
														<td>&nbsp;</td>
														<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
													</tr>
												</table>
											</ezf:skip>
											<table width="500">
												<tr align="right">
													<td>
														<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="A" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
														</jsp:include>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
					<table border="0" width="100%">
						<tr>
							<td>
								<div id="Top" style="width:1083; display:block; overflow:hidden; margin:0px; padding:0px;">
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width=" 20" align="center"><!-- icon -->
										<col width=" 90" align="center"><!-- Contract# -->
										<col width=" 24" align="center"><!-- CheckBox -->
										<col width=" 80" align="center"><!-- Serial# -->
										<col width="66" align="center"></col>	<!-- Machine Master -->
										<col width="80" align="center"></col>	<!-- Service Program -->
										<col width=" 24" align="center"><!-- CheckBox -->
										<col width=" 65" align="center"><!-- Base/Overage -->
										<col width=" 80" align="center"><!-- Orig Start -->
										<col width=" 80" align="center"><!-- Orig End -->
										<col width="115" align="center"><!-- Type -->
										<col width="115" align="center"><!-- Method -->
										<col width=" 35" align="center"><!-- #Days -->
										<col width=" 45" align="center"><!-- Max% -->
										<col width=" 45" align="center"><!-- Base% -->
										<col width=" 45" align="center"><!-- Overage% -->
										<col width=" 38" align="center"><!-- Fixed Month -->
										<col width="100" align="center"><!-- Hold Price Until Date -->
										<col width=" 80" align="center"><!-- Policy Date -->
										<col width="132" align="center"><!-- Return Message -->
										<tr>
											<td class="pClothBs" rowspan="2">&nbsp;</td>
											<td class="pClothBs" rowspan="2">Contract#</td>
											<td class="pClothBs" rowspan="2"><ezf:inputCheckBox name="xxChkBox_X1" ezfName="xxChkBox_X1" value="Y" onClick="sendServer('SelectAllContract')" /></td>
											<td class="pClothBs" rowspan="2">Serial#</td>
											<td class="pClothBs" rowspan="2">Machine Master</td>
											<td class="pClothBs" rowspan="2">Service Program</td>
											<td class="pClothBs" rowspan="2"><ezf:inputCheckBox name="xxChkBox_X2" ezfName="xxChkBox_X2" value="Y" onClick="sendServer('SelectAllSerial')" /></td>
											<td class="pClothBs" rowspan="2">Base/<br />Overage</td>
											<td class="pClothBs" rowspan="2">Orig Start</td>
											<td class="pClothBs" rowspan="2">Orig End</td>
											<td class="pClothBs" colspan="6">Annual Escalation</td>
											<td class="pClothBs" rowspan="2">Fixed<br />Month</td>
											<td class="pClothBs" rowspan="2">Hold Price<br />Until Date</td>
											<td class="pClothBs" rowspan="2">Policy<br />Date</td>
											<td class="pClothBs" rowspan="2">Return Message</td>
										</tr>
										<tr>
											<td class="pClothBs">Type</td>
											<td class="pClothBs">Method</td>
											<td class="pClothBs">#Days</td>
											<td class="pClothBs">Max<br />%</td>
											<td class="pClothBs">Base<br />%</td>
											<td class="pClothBs">Ovrg<br />%</td>
										</tr>
									</table>
								</div>
								<div id ="Detail" style="width:1100; height:394; display:block; overflow:scroll;" onscroll="synchroScrollLeft(this.id, new Array('Top'));">
									<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width=" 20" align="center"><!-- icon -->
										<col width=" 90" align="center"><!-- Contract# -->
										<col width=" 24" align="center"><!-- CheckBox -->
										<col width=" 80" align="center"><!-- Serial# -->
										<col width="66" align="right"></col>	<!-- Machine Master -->
										<col width="80" align="center"></col>	<!-- Service Program -->
										<col width=" 24" align="center"><!-- CheckBox -->
										<col width=" 65" align="center"><!-- Base/Overage -->
										<col width=" 80" align="center"><!-- Orig Start -->
										<col width=" 80" align="center"><!-- Orig End -->
										<col width="115" align="center"><!-- Type -->
										<col width="115" align="center"><!-- Method -->
										<col width=" 35" align="center"><!-- #Days -->
										<col width=" 45" align="center"><!-- Max% -->
										<col width=" 45" align="center"><!-- Base% -->
										<col width=" 45" align="center"><!-- Overage% -->
										<col width=" 38" align="center"><!-- Fixed Month -->
										<col width="100" align="center"><!-- Hold Price Until Date -->
										<col width=" 80" align="center"><!-- Policy Date -->
										<col width="132" align="center"><!-- Return Message -->
										<% int idx = -1; %>
										<ezf:row ezfHyo="A">
										<% idx++; %>
											<tr height="25">
												<td>
												<% if ("1".equals((String)bMsg.A.no(idx).dsContrMachLvlNum_D1.getValue()) || "2".equals((String)bMsg.A.no(idx).dsContrMachLvlNum_D1.getValue())) { %>
													<ezf:anchor name="xxBtnFlg_D1" ezfName="xxBtnFlg_D1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Collapse">
													<%if ("Y".equals(bMsg.A.no(idx).xxBtnFlg_D1.getValue())) { %>
														<img src="./img/biz/downarrow2.png" border="0" value="Y">
													<% } else { %>
														<img src="./img/biz/rightarrow2.png" border="0" value="N">
													<% } %>
													</ezf:anchor>
												<% } else { %>
													&nbsp;
												<% } %>
												</td>
												<td><ezf:inputText name="xxScrItem34Txt_D1" ezfName="xxScrItem34Txt_D1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\""/></td>
												<td><ezf:inputCheckBox name="xxChkBox_S1" ezfName="xxChkBox_S1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_S1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="serNum_D1" ezfName="serNum_D1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="svcMachMstrPk_D1" ezfName="svcMachMstrPk_D1" value="10001023" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"28\""/></td>
												<td><ezf:inputText name="mdseDescShortTxt_D1" ezfName="mdseDescShortTxt_D1" value="RENTAL SUPPLY INCLUSIVE SERVIC" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"250\""/></td>
												<td><ezf:inputCheckBox name="xxChkBox_S2" ezfName="xxChkBox_S2" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_S2#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="dsContrBaseUsgNm_D1" ezfName="dsContrBaseUsgNm_D1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
												<td><ezf:inputText name="contrEffFromDt_D1" ezfName="contrEffFromDt_D1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
												<td><ezf:inputText name="contrEffThruDt_D1" ezfName="contrEffThruDt_D1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
												<td><ezf:select name="contrUplftTpCd_D3" ezfName="contrUplftTpCd_D3" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="contrUplftTpCd_H1" ezfDispName="contrUplftTpNm_H2" otherEvent1=" onchange=\"sendServer('OnChangeEsclTpCd', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:110\" id=\"OnChangeEsclTpCd#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:select name="uplftPrcMethCd_D3" ezfName="uplftPrcMethCd_D3" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="uplftPrcMethCd_H1" ezfDispName="uplftPrcMethNm_H2" otherEvent1=" onchange=\"sendServer('OnChangeEsclMethod', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:110\" id=\"OnChangeEsclMethod#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="uplftBefEndRnwDaysAot_D1" ezfName="uplftBefEndRnwDaysAot_D1" value="3" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onchange=\"sendServer('OnChangeEsclDays', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"text-align:right\" size=\"3\" maxlength=\"3\" id=\"OnChangeEsclDays#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="maxPrcUpRatio_D1" ezfName="maxPrcUpRatio_D1" value="10" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right\" size=\"5\" maxlength=\"5\""/></td>
												<td><ezf:inputText name="uplftBasePrcUpRatio_D1" ezfName="uplftBasePrcUpRatio_D1" value="10" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onchange=\"sendServer('OnChangeEsclBase', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"text-align:right\" size=\"5\" maxlength=\"5\" id=\"OnChangeEsclBase#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="uplftMtrPrcUpRatio_D1" ezfName="uplftMtrPrcUpRatio_D1" value="5" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onchange=\"sendServer('OnChangeEsclOvrg', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"text-align:right\" size=\"5\" maxlength=\"5\" id=\"OnChangeEsclOvrg#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="fixTermInMthAot_D1" ezfName="fixTermInMthAot_D1" value="3" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnChangeFixMth" otherAttr=" style=\"text-align:right\" size=\"3\" maxlength=\"3\" ezfnoupperfocusout=\"OnChangeFixMth\""/></td>
												<td><ezf:inputText name="uplftFixedDt_D1" ezfName="uplftFixedDt_D1" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnChangeHldPrcUtilDt" otherAttr=" size=\"10\" maxlength=\"10\" ezfnoupperfocusout=\"OnChangeHldPrcUtilDt\" id=\"uplftFixedDt_D1#{EZF_ROW_NUMBER}\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('uplftFixedDt_D1', 4, '{EZF_ROW_NUMBER}');"></td>
												<td><ezf:inputText name="uplftPcyDt_D1" ezfName="uplftPcyDt_D1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												<td><ezf:inputText name="xxGenlFldAreaTxt_D1" ezfName="xxGenlFldAreaTxt_D1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\""/></td>
                                                <td class="pAuditInfo">
                                                    <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
                                                </td>
											</tr>
										</ezf:row>
									</table>
								</div>
							</td>
						</tr>
					</table>
					
				</div>
			</td>
		</tr>
	</table>
</center>

<script type="text/javascript">
	function clickImg(prntObj, idx) {
		var eventNm = prntObj.all(0).value;
		sendServer(eventNm, idx);
	}
</script>


<%-- **** Task specific area ends here **** --%>
