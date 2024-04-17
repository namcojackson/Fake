<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161128143527 --%>
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
			<input type="hidden" name="pageID" value="NSAL0820Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Actual Counters for Interface">

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
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Actl Cntr</a></li>
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
 					<table border="0" style="table-layout:fixed;width=1082px;margin-left:20;">
						<col width="180">
						<col width="50">
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
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1790px" style="margin-right:20px" >
												<col width="30" align="center">	<!-- CheckBox -->
												<col width="105" align="center">	<!-- Interface Bat# -->
												<col width="75" align="center">	<!-- Source Ref# -->
												<col width="135" align="center">	<!-- Source Type -->
												<col width="75" align="center">	<!-- Contract# -->
												<col width="115" align="center">	<!-- Action -->
												<col width="125" align="center">	<!-- Serial# -->
												<col width="120" align="center">	<!-- IB ID -->
												<col width="125" align="center">	<!-- Mdse Code -->
												<col width="185" align="center">	<!-- Counter Name -->
												<col width="60" align="center">	<!-- Billable -->
												<col width="70" align="center">	<!-- Multiplier -->
												<col width="185" align="center">	<!-- Billing Counter -->
												<col width="125" align="center">	<!-- Intangible Mdse Code -->
												<col width="50" align="center">	<!-- Proc Flag -->
												<col width="120" align="center">	<!-- Proc Message -->
												<col width="90" align="center">	<!-- Proc Status -->
												<tr height="34px">
													<td id="AH0" class="pClothBs colFix">&nbsp;</td>
													<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcBatNum_A')">Interface Bat#</a><img id="sortIMG.dsContrIntfcBatNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrSrcRefNum_A')">Source Ref#</a><img id="sortIMG.dsContrSrcRefNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrIntfcSrcTpCd_A')">Source Type</a><img id="sortIMG.contrIntfcSrcTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')">Contract#</a><img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcActDescTxt_A')">Action</a><img id="sortIMG.dsContrIntfcActDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')">Serial#</a><img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachMstrPk_A')">IB ID</a><img id="sortIMG.svcMachMstrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A')">Item Code</a><img id="sortIMG.mdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrLbDescTxt_AP')">Counter Name</a><img id="sortIMG.mtrLbDescTxt_AP" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllblFlg_A')">Billable</a><img id="sortIMG.bllblFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrMtrMultRate_A')">Multiplier</a><img id="sortIMG.contrMtrMultRate_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrLbDescTxt_AB')">Billing Counter</a><img id="sortIMG.mtrLbDescTxt_AB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'intgMdseCd_A')">Intangible Item Code</a><img id="sortIMG.intgMdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcStsCd_A')">Proc Flag</a><img id="sortIMG.dsContrIntfcStsCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'intfcErrMsgTxt_A')">Proc Message</a><img id="sortIMG.intfcErrMsgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrProcStsDescTxt_A')">Proc Status</a><img id="sortIMG.dsContrProcStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id="rightTbl" style="width:1082px; height:400px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1790px" >
												<col width="30" align="center">	<!-- CheckBox -->
												<col width="105" align="center">	<!-- Interface Bat# -->
												<col width="75" align="center">	<!-- Source Ref# -->
												<col width="135" align="center">	<!-- Source Type -->
												<col width="75" align="center">	<!-- Contract# -->
												<col width="115" align="center">	<!-- Action -->
												<col width="125" align="center">	<!-- Serial# -->
												<col width="120" align="center">	<!-- IB ID -->
												<col width="125" align="center">	<!-- Mdse Code -->
												<col width="185" align="center">	<!-- Counter Name -->
												<col width="60" align="center">	<!-- Billable -->
												<col width="70" align="center">	<!-- Multiplier -->
												<col width="185" align="center">	<!-- Billing Counter -->
												<col width="125" align="center">	<!-- Intangible Mdse Code -->
												<col width="50" align="center">	<!-- Proc Flag -->
												<col width="120" align="center">	<!-- Proc Message -->
												<col width="90" align="center">	<!-- Proc Status -->
												<ezf:row ezfHyo="A">
												<tr height="23px" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
													<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A_#{EZF_ROW_NUMBER}\""/></td>
													<td align="left"><ezf:label name="dsContrIntfcBatNum_A" ezfName="dsContrIntfcBatNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="dsContrSrcRefNum_A" ezfName="dsContrSrcRefNum_A" value="12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:select name="contrIntfcSrcTpCd_A" ezfName="contrIntfcSrcTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="contrIntfcSrcTpCd_AC" ezfDispName="contrIntfcSrcTpDescTxt_AC" otherAttr=" style=\"width:130;\""/></td>
													<td align="left"><ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="left"><ezf:label name="dsContrIntfcActDescTxt_A" ezfName="dsContrIntfcActDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="serNum_A" ezfName="serNum_A" value="W2345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Machine" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="center"><ezf:inputText name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" value="W2345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Merchandise" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="center"><ezf:inputText name="mtrLbDescTxt_AP" ezfName="mtrLbDescTxt_AP" value="BIL COLOR LARGE AGG" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/><ezf:inputButton name="OpenWin_PhysMtr" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="center"><ezf:inputCheckBox name="bllblFlg_A" ezfName="bllblFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="contrMtrMultRate_A" ezfName="contrMtrMultRate_A" value="123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="mtrLbDescTxt_AB" ezfName="mtrLbDescTxt_AB" value="BIL COLOR LARGE AGG" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/><ezf:inputButton name="OpenWin_BllgMtr" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="center"><ezf:inputText name="intgMdseCd_A" ezfName="intgMdseCd_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:label name="dsContrIntfcStsCd_A" ezfName="dsContrIntfcStsCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="left" class="stab"><ezf:inputText name="intfcErrMsgTxt_A" ezfName="intfcErrMsgTxt_A" value="NAAM0000E:Error XXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" ezftoupper=\"\""/></td>
													<td align="left"><ezf:label name="dsContrProcStsDescTxt_A" ezfName="dsContrProcStsDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td class="pAuditInfo">
														<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
														<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
														<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
														<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
														<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
													</td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr height="23px">
													<td align="center"><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A_#{EZF_ROW_NUMBER}"></td>
													<td align="left"><label ezfout name="dsContrIntfcBatNum_A" ezfname="dsContrIntfcBatNum_A" ezfhyo="A">2015121200001</label></td>
													<td align="center"><input type="text" class="" size="9" value="12345678" name="dsContrSrcRefNum_A" ezfname="dsContrSrcRefNum_A" ezfhyo="A" ezftoupper></td>
													<td align="center"><select name="contrIntfcSrcTpCd_A" ezfname="contrIntfcSrcTpCd_A" style="width:130;" ezflist="contrIntfcSrcTpCd_AC,contrIntfcSrcTpDescTxt_AC,99, ,blank" ezfHyo="A" ezfArrayNo="0"><option>Data Conversion</option></select></td>
													<td align="left"><label ezfout name="dsContrNum_A" ezfname="dsContrNum_A" ezfhyo="A">12345678</label></td>
													<td align="left"><label ezfout name="dsContrIntfcActDescTxt_A" ezfname="dsContrIntfcActDescTxt_A" ezfhyo="A">Add to Contract</label></td>
													<td align="center"><input type="text" class="" size="11" value="W2345678" name="serNum_A" ezfname="serNum_A" ezfhyo="A" ezftoupper><input type="button" class="pBtn0" value="Go" name="OpenWin_Machine" onclick="sendServer(this)" ezfhyo="A"></td>
													<td align="center"><input type="text" class="" size="15" value="W2345" name="svcMachMstrPk_A" ezfname="svcMachMstrPk_A" ezfhyo="A" style="text-align:right" ezftoupper></td>
													<td align="center"><input type="text" class="" size="11" value="1234567890" name="mdseCd_A" ezfname="mdseCd_A" ezfhyo="A" ezftoupper><input type="button" class="pBtn0" value="Go" name="OpenWin_Merchandise" onclick="sendServer(this)" ezfhyo="A"></td>
													<td align="center"><input type="text" class="" size="20" value="BIL COLOR LARGE AGG" name="mtrLbDescTxt_AP" ezfname="mtrLbDescTxt_AP" ezfhyo="A"><input type="button" class="pBtn0" value="Go" name="OpenWin_PhysMtr" onclick="sendServer(this)" ezfhyo="A"></td>
													<td align="center"><input type="checkbox" value="Y" name="bllblFlg_A" ezfname="bllblFlg_A" ezfhyo="A"></td>
													<td align="center"><input type="text" class="" size="8" value="123456789" name="contrMtrMultRate_A" ezfname="contrMtrMultRate_A" ezfhyo="A"  style="text-align:right" ezftoupper></td>
													<td align="center"><input type="text" class="" size="20" value="BIL COLOR LARGE AGG" name="mtrLbDescTxt_AB" ezfname="mtrLbDescTxt_AB" ezfhyo="A"><input type="button" class="pBtn0" value="Go" name="OpenWin_BllgMtr" onclick="sendServer(this)" ezfhyo="A"></td>
													<td align="center"><input type="text" class="" size="16" value="1234567890" name="intgMdseCd_A" ezfname="intgMdseCd_A" ezfhyo="A" ezftoupper></td>
													<td align="center"><label ezfout name="dsContrIntfcStsCd_A" ezfname="dsContrIntfcStsCd_A" ezfhyo="A">N </label></td>
													<td align="left" class="stab"><input type="text" class="" size="15" value="NAAM0000E:Error XXXXXXXXXXXXXX" name="intfcErrMsgTxt_A" ezfname="intfcErrMsgTxt_A" ezfhyo="A" ezftoupper></td>
													<td align="left"><label ezfout name="dsContrProcStsDescTxt_A" ezfname="dsContrProcStsDescTxt_A" ezfhyo="A">Compleated </label></td>
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
