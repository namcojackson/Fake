<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161128165527 --%>
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
			<input type="hidden" name="pageID" value="NSAL0840Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Additional Charge for Interface">

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
										<li title="Additional Charge for Interface" class="pTab_ON"><a href="#">Add Charg</a></li>
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
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" >
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;" width="95%" align="center">
									<col width="20">
									<col width="80">
									<col width="150">
									<col width="40">
									<col width="80">
									<col width="150">
									<col width="40">
									<col width="80">
									<col width="150">
									<col align="right">
									<tr height="21">
										<td></td>
										<td class="stab">Source Type</td>
										<td><ezf:select name="contrIntfcSrcTpCd_SS" ezfName="contrIntfcSrcTpCd_SS" ezfBlank="1" ezfCodeName="contrIntfcSrcTpCd_SC" ezfDispName="contrIntfcSrcTpDescTxt_SC" /></td>
										<td></td>
										<td class="stab">Interface Bat#</td>
										<td><ezf:inputText name="dsContrIntfcBatNum_S" ezfName="dsContrIntfcBatNum_S" value="200215%" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Source Ref#</td>
										<td><ezf:inputText name="dsContrSrcRefNum_S" ezfName="dsContrSrcRefNum_S" value="200215%" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td></td>
									</tr>
									<tr height="21">
										<td></td>
										<td class="stab">Contract#</td>
										<td><ezf:inputText name="dsContrNum_S" ezfName="dsContrNum_S" value="200215%" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Errors Only</td>
										<td><ezf:inputCheckBox name="xxErrFlg_S" ezfName="xxErrFlg_S" value="Y" /></td>
										<td></td>
										<td></td>
										<td></td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />&nbsp;<ezf:inputButton name="OpenForCorrection" value="Open For Correction" htmlClass="pBtn12" />&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><hr></td>
						</tr>
					</table>
					<br>
					<!-- ######################################## Detail ######################################## -->
 					<table border="0" style="table-layout:fixed;width:1085px;margin-left:20;">
						<col width="180">
						<col width="20">
						<col width="110">
						<col width="370">
						<col width="">
						<tr>
							<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
							<td></td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TemplateDownload" >Template Download</ezf:anchor></td>
							<td><ezf:inputFile name="xxFileData_U" ezfName="xxFileData_U" otherAttr=" size=\"25\" maxlength=\"9999\""/><ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn6" /></td>
							<!-- Pagination & Navigation--START-->
							<td>
								<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"           value="A" />
									<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"          value="LEFT_NONE" />
								</jsp:include>
								<ezf:skip>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="54"  align="center">
									<col width="40"  align="center">
									<col width="16"  align="center">
									<col width="40"  align="center">
									<col width="16"  align="center">
									<col width="10">
									<col>
									<col width="20">
									<col>	
									<col width="1">
									<col>
									<tr>
										<td class="stab">Showing</td>
										<td><input type="text" name="xxPageShowCurNum" value="1" size="4" maxlength="4" style="text-align:right" id="txtShowingCur" ></td>
										<td class="stab">/</td>
										<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="3" class="pPro" style="text-align:right" id="txtShowingTot" readOnly></td>
										<td class="stab">page</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')" ></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')" ></td>
									</tr>
								</table>
								</ezf:skip>
							</td>
							<!-- Pagination & Navigation--END-->
						</tr>
					</table>
					<table>
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:383px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1065px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="2090px" style="margin-right:20px" >
												<col width="30" align="center">		<!-- CheckBox -->
												<col width="105" align="center">	<!-- Interface Bat# -->
												<col width="75" align="center">		<!-- Source Ref# -->
												<col width="135" align="center">	<!-- Source Type -->
												<col width="75" align="center">		<!-- Contract# -->
												<col width="115" align="center">	<!-- Action -->
												<col width="125" align="center">	<!-- Serial# -->
												<col width="120" align="center">	<!-- IB ID -->
												<col width="125" align="center">	<!-- Mdse Code -->
												<col width="100" align="center">	<!-- Charge Level -->
												<col width="180" align="center">	<!-- Charge Type -->
												<col width="100" align="center">	<!-- Start Date -->
												<col width="100" align="center">	<!-- End Date -->
												<col width="125" align="center">	<!-- Frequency -->
												<col width="90" align="center">		<!-- Flat Rate -->
												<col width="70" align="center">		<!-- Applicable% -->
												<col width="100" align="center">	<!-- Invoice Type -->
												<col width="60" align="center">		<!-- Print on Invoice -->
												<col width="50" align="center">		<!-- Proc Flag -->
												<col width="120" align="center">	<!-- Proc Message -->
												<col width="90" align="center">		<!-- Proc Status -->
												<tr height="34px">
													<td id="AH0"  class="pClothBs colFix">&nbsp;</td>
													<td id="AH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcBatNum_A')">Interface Bat#</a><img id="sortIMG.dsContrIntfcBatNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrSrcRefNum_A')">Source Ref#</a><img id="sortIMG.dsContrSrcRefNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrIntfcSrcTpCd_A')">Source Type</a><img id="sortIMG.contrIntfcSrcTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')">Contract#</a><img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcActDescTxt_A')">Action</a><img id="sortIMG.dsContrIntfcActDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')">Serial#</a><img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachMstrPk_A')">IB ID</a><img id="sortIMG.svcMachMstrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A')">Item Code</a><img id="sortIMG.mdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'chrgLvlTpCd_A')">Charge Level</a><img id="sortIMG.chrgLvlTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'addlChrgTpCd_A')">Charge Type</a><img id="sortIMG.addlChrgTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effFromDt_A')">Start Date</a><img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effThruDt_A')">End Date</a><img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgCycleCd_A')">Frequency</a><img id="sortIMG.bllgCycleCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'addlChrgFlatDealPrcAmt_A')">Flat Rate</a><img id="sortIMG.addlChrgFlatDealPrcAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'addlChrgAplcPct_A')">Applicable%</a><img id="sortIMG.addlChrgAplcPct_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'addlChrgInvTpCd_A')">Invoice Type</a><img id="sortIMG.addlChrgInvTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'printDtlFlg_A')">Print on Invoice</a><img id="sortIMG.printDtlFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcStsCd_A')">Proc Flag</a><img id="sortIMG.dsContrIntfcStsCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'intfcErrMsgTxt_A')">Proc Message</a><img id="sortIMG.intfcErrMsgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrProcStsDescTxt_A')">Proc Status</a><img id="sortIMG.dsContrProcStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id="rightTbl" style="width:1082px; height:400px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="2090px" >
												<col width="30" align="center">		<!-- CheckBox -->
												<col width="105" align="center">	<!-- Interface Bat# -->
												<col width="75" align="center">		<!-- Source Ref# -->
												<col width="135" align="center">	<!-- Source Type -->
												<col width="75" align="center">		<!-- Contract# -->
												<col width="115" align="center">	<!-- Action -->
												<col width="125" align="center">	<!-- Serial# -->
												<col width="120" align="center">	<!-- IB ID -->
												<col width="125" align="center">	<!-- Mdse Code -->
												<col width="100" align="center">	<!-- Charge Level -->
												<col width="180" align="center">	<!-- Charge Type -->
												<col width="100" align="center">	<!-- Start Date -->
												<col width="100" align="center">	<!-- End Date -->
												<col width="125" align="center">	<!-- Frequency -->
												<col width="90" align="center">		<!-- Flat Rate -->
												<col width="70" align="center">		<!-- Applicable% -->
												<col width="100" align="center">	<!-- Invoice Type -->
												<col width="60" align="center">		<!-- Print on Invoice -->
												<col width="50" align="center">		<!-- Proc Flag -->
												<col width="120" align="center">	<!-- Proc Message -->
												<col width="90" align="center">		<!-- Proc Status -->
												<ezf:row ezfHyo="A">
												<tr height="23px" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
													<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A_#{EZF_ROW_NUMBER}\""/></td>
													<td align="left"><ezf:label name="dsContrIntfcBatNum_A" ezfName="dsContrIntfcBatNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="dsContrSrcRefNum_A" ezfName="dsContrSrcRefNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:select name="contrIntfcSrcTpCd_A" ezfName="contrIntfcSrcTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="contrIntfcSrcTpCd_AC" ezfDispName="contrIntfcSrcTpDescTxt_AC" otherAttr=" style=\"width:130;\""/></td>
													<td align="left"><ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="left"><ezf:label name="dsContrIntfcActDescTxt_A" ezfName="dsContrIntfcActDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Machine" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="center"><ezf:inputText name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right;\" size=\"15\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Merchandise" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="center"><ezf:select name="chrgLvlTpCd_A" ezfName="chrgLvlTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="chrgLvlTpCd_AL" ezfDispName="chrgLvlTpDescTxt_AL" otherAttr=" style=\"width:95;\""/></td>
													<td align="center"><ezf:select name="addlChrgTpCd_A" ezfName="addlChrgTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="addlChrgTpCd_AT" ezfDispName="addlChrgTpDescTxt_AT" otherAttr=" style=\"width:175;\""/></td>
													<td align="center"><ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effFromDt_A\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
													<td align="center"><ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effThruDt_A\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
													<td align="center"><ezf:select name="bllgCycleCd_A" ezfName="bllgCycleCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleCd_AB" ezfDispName="bllgCycleDescTxt_AB" otherAttr=" style=\"width:120;\""/></td>
													<td align="center"><ezf:inputText name="addlChrgFlatDealPrcAmt_A" ezfName="addlChrgFlatDealPrcAmt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="addlChrgAplcPct_A" ezfName="addlChrgAplcPct_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:select name="addlChrgInvTpCd_A" ezfName="addlChrgInvTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="addlChrgInvTpCd_AI" ezfDispName="addlChrgInvTpDescTxt_AI" otherAttr=" style=\"width:75;\""/></td>
													<td align="center"><ezf:inputCheckBox name="printDtlFlg_A" ezfName="printDtlFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:label name="dsContrIntfcStsCd_A" ezfName="dsContrIntfcStsCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="left" class="stab"><ezf:inputText name="intfcErrMsgTxt_A" ezfName="intfcErrMsgTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\""/></td>
													<td align="left"><ezf:label name="dsContrProcStsDescTxt_A" ezfName="dsContrProcStsDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td class="pAuditInfo">
														<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													</td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr height="23px">
													<td align="center"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
													<td align="left"><label ezfout name="dsContrIntfcBatNum_A" ezfname="dsContrIntfcBatNum_A" ezfhyo="A">2015121200001</label></td>
													<td align="center"><input type="text" class="" size="9" value="12345678" name="dsContrSrcRefNum_A" ezfname="dsContrSrcRefNum_A" ezfhyo="A"></td>
													<td align="center"><select name="contrIntfcSrcTpCd_A" ezfname="" ezflist="" ezfhyo="A" style="width:130;"><option>Data Conversion</option></select></td>
													<td align="left"><label ezfout name="dsContrNum_A" ezfname="dsContrNum_A" ezfhyo="A">12345678</label></td>
													<td align="left"><label ezfout name="dsContrIntfcActDescTxt_A" ezfname="dsContrIntfcActDescTxt_A" ezfhyo="A">Add to Contract</label></td>
													<td align="center"><input type="text" class="" size="11" value="W2345678" name="serNum_A" ezfname="serNum_A" ezfhyo="A"><input type="button" class="pBtn0" value="Go" name="OpenWin_NWAL1130"></td>
													<td align="center"><input type="text" class="" size="15" value="W2345" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A"></td>
													<td align="center"><input type="text" class="" size="11" value="1234567890" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A"><input type="button" class="pBtn0" value="Go" name="OpenWin_NMAL6800"></td>
													<td align="center"><select name="chrgLvlTpCd_A" ezfname="" ezflist="" ezfhyo="A" style="width:95;"><option>Contract</option></select></td>
													<td align="center"><select name="addlChrgTpCd_A" ezfname="" ezflist="" ezfhyo="A" style="width:175;"><option>ADDITIONAL CHARGES - SERVICE</option></select></td>
													<td align="center"><input type="text" class="" size="10" value="12/12/2015" name="effFromDt_A" ezfname="effFromDt_A" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4);"></td>
													<td align="center"><input type="text" class="" size="10" value="12/12/2016" name="effThruDt_A" ezfname="effThruDt_A" ezfhyo="A"><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4);"></td>
													<td align="center"><select name="bllgCycleCd_A" ezfname="" ezflist="" ezfhyo="A" style="width:120;"><option>Contract Period</option></select></td>
													<td align="center"><input type="text" class="" size="10" value="1234567890" name="addlChrgFlatDealPrcAmt_A" ezfname="addlChrgFlatDealPrcAmt_A" ezfhyo="A" style="text-align:right"></td>
													<td align="center"><input type="text" class="" size="5" value="12345" name="addlChrgAplcPct_A" ezfname="addlChrgAplcPct_A" ezfhyo="A" style="text-align:right"></td>
													<td align="center"><select name="addlChrgInvTpCd_A" ezfname="" ezflist="" ezfhyo="A" style="width:75;"><option>USAGE</option></select></td>
													<td align="center"><input type="checkbox" value="Y" name="printDtlFlg_A" ezfname="printDtlFlg_A" ezfhyo="A"></td>
													<td align="center"><label ezfout ezfname="dsContrIntfcStsCd_A" ezfhyo="A">N </label></td>
													<td align="left" class="stab"><input type="text" class="" size="15" value="NAAM0000E:Error XXXXXXXXXXXXXX" name="intfcErrMsgTxt_A" ezfname="intfcErrMsgTxt_A" ezfhyo="A"></td>
													<td align="left"><label ezfout name="dsContrProcStsDescTxt_A" ezfname="dsContrProcStsDescTxt_A" ezfhyo="A"></label>Compleated</td>
												</tr>
												</ezf:skip>
											</table>
										</div><!-- rightTbl -->
									</div><!-- right table -->
								</div><!-- parent box -->
							</td>
						</tr>
					</table>
					<table  border="0" style="table-layout:fixed;width=96%;">
						<col align="left" width="300">
						<col align="right">
						<tr>
							<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn8" /><ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn8" />&nbsp;<ezf:inputButton name="AddRow" value="Add Row" htmlClass="pBtn8" /></td>
							<td><ezf:inputButton name="ValidateData" value="Validate Data" htmlClass="pBtn8" /></td>
						</tr>
					</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>
	<script type="text/javascript" defer>
	    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1);
	</script>


<%-- **** Task specific area ends here **** --%>