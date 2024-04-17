<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161128172631 --%>
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
			<input type="hidden" name="pageID" value="NSAL0850Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Sales Credit for Interface">

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
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Sls cr</a></li>
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
					<%-- ######################################## DETAIL ######################################## --%>
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
										<div id='rightTblHead' style="width:1082px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1678px" style="margin-right:20px" >
												<col width=" 30" align="center">	<!-- CheckBox -->
												<col width="105" align="center">	<!-- Interface Bat# -->
												<col width=" 75" align="center">	<!-- Source Ref# -->
												<col width="135" align="center">	<!-- Source Type -->
												<col width=" 90" align="center">	<!-- Contract# -->
												<col width="150" align="center">	<!-- Action -->
												<col width=" 93" align="center">	<!-- Allocation Type -->
												<col width="120" align="center">	<!-- Serial# -->
												<col width=" 90" align="center">	<!-- IB ID -->
												<col width="120" align="center">	<!-- Mdse Code -->
												<col width="120" align="center">	<!-- Rep Code -->
												<col width="190" align="center">	<!-- Rep Name -->
												<col width=" 60" align="center">	<!-- Split% -->
												<col width=" 60" align="center">	<!-- Proc Flag -->
												<col width="150" align="center">	<!-- Proc Message -->
												<col width=" 90" align="center">	<!-- Proc Status -->
												<tr height="34px">
													<td id="AH0" class="pClothBs colFix">&nbsp;</td>
													<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcBatNum_A')">Interface Bat#</a><img id="sortIMG.dsContrIntfcBatNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrSrcRefNum_A')">Source</br>Ref#</a><img id="sortIMG.dsContrSrcRefNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrIntfcSrcTpCd_A')">Source Type</a><img id="sortIMG.contrIntfcSrcTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')">Contract#</a><img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcActDescTxt_A')">Action</a><img id="sortIMG.dsContrIntfcActDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'prcAllocIntfcTpCd_A')">Allocation</br>Type</a><img id="sortIMG.prcAllocIntfcTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')">Serial#</a><img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachMstrPk_A')">IB ID</a><img id="sortIMG.svcMachMstrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A')">Item Code</a><img id="sortIMG.mdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'tocCd_A')">Rep Code</a><img id="sortIMG.tocCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'tocNm_A')">Rep Name</a><img id="sortIMG.tocNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'prcAllocRate_A')">Split%</a><img id="sortIMG.prcAllocRate_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcStsCd_A')">Proc</br>Flag</a><img id="sortIMG.dsContrIntfcStsCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'intfcErrMsgTxt_A')">Proc Message</a><img id="sortIMG.intfcErrMsgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrProcStsDescTxt_A')">Proc Status</a><img id="sortIMG.dsContrProcStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id="rightTbl" style="width:1099px; height:400px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1678px" >
												<col width=" 30" align="center">	<!-- CheckBox -->
												<col width="105" align="center">	<!-- Interface Bat# -->
												<col width=" 75" align="center">	<!-- Source Ref# -->
												<col width="135" align="center">	<!-- Source Type -->
												<col width=" 90" align="center">	<!-- Contract# -->
												<col width="150" align="center">	<!-- Action -->
												<col width=" 93" align="center">	<!-- Allocation Type -->
												<col width="120" align="center">	<!-- Serial# -->
												<col width=" 90" align="center">	<!-- IB ID -->
												<col width="120" align="center">	<!-- Mdse Code -->
												<col width="120" align="center">	<!-- Rep Code -->
												<col width="190" align="center">	<!-- Rep Name -->
												<col width=" 60" align="center">	<!-- Split% -->
												<col width=" 60" align="center">	<!-- Proc Flag -->
												<col width="150" align="center">	<!-- Proc Message -->
												<col width=" 90" align="center">	<!-- Proc Status -->
												<ezf:row ezfHyo="A">
												<tr height="23px" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
													<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A_#{EZF_ROW_NUMBER}\""/></td>
													<td align="left"><ezf:label name="dsContrIntfcBatNum_A" ezfName="dsContrIntfcBatNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="dsContrSrcRefNum_A" ezfName="dsContrSrcRefNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
													<td><ezf:select name="contrIntfcSrcTpCd_A" ezfName="contrIntfcSrcTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="contrIntfcSrcTpCd_AC" ezfDispName="contrIntfcSrcTpDescTxt_AC" /></td>
													<td align="left"><ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="left"><ezf:label name="dsContrIntfcActDescTxt_A" ezfName="dsContrIntfcActDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:select name="prcAllocIntfcTpCd_A" ezfName="prcAllocIntfcTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcAllocIntfcTpCd_AP" ezfDispName="prcAllocIntfcTpDescTxt_AP" /></td>
													<td align="center"><ezf:inputText name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Machine" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="center"><ezf:inputText name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right;\" size=\"11\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Merchandise" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="center"><ezf:inputText name="tocCd_A" ezfName="tocCd_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Resource" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="center"><ezf:inputText name="tocNm_A" ezfName="tocNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\""/></td>
													<td align="center"><ezf:inputText name="prcAllocRate_A" ezfName="prcAllocRate_A" value="100" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right;\" size=\"6\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:label name="dsContrIntfcStsCd_A" ezfName="dsContrIntfcStsCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="left"><ezf:inputText name="intfcErrMsgTxt_A" ezfName="intfcErrMsgTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
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
													<td align="center"><input type="checkbox" value="Y" name="xxChkBox_A" ezfhyo="A"></td>
													<td align="left"><label ezfout name="dsContrIntfcBatNum_A" ezfhyo="A">20160107000003</label></td>
													<td align="center"><input type="text" class="" size="8" value="0000030" name="dsContrSrcRefNum_A" ezfhyo="A" ezftoupper></td>
													<td><select name="contrIntfcSrcTpCd_A"><option>Contract Maintenace</option><option>Copy Contract</option></select></td>
													<td align="left"><label ezfout name="dsContrNum_A" ezfhyo="A">1234567890</label></td>
													<td align="left"><label ezfout name="dsContrIntfcActDescTxt_A" ezfhyo="A">Add to Contract</label></td>
													<td><select name="prcAllocIntfcTpCd_A"><option>Contract</option><option>Machine</option></select></td>
													<td align="center"><input type="text" class="" size="10" value="TST99003" name="serNum_A" ezfhyo="A"><input type="button" class="pBtn0" value="Go" name="OpenWin_Machine"></td>
													<td align="center"><input type="text" class="" size="11" value="9999999999" name="svcMachMstrPk_A" ezfhyo="A"></td>
													<td align="center"><input type="text" class="" size="11" value="8030B003AA" name="mdseCd_A" ezfhyo="A"><input type="button" class="pBtn0" value="Go" name="OpenWin_Merchandise"></td>
													<td align="center"><input type="text" class="" size="11" value="1234567890" name="tocCd_A" ezfhyo="A"><input type="button" class="pBtn0" value="Go" name="OpenWin_Resource"></td>
													<td align="center"><input type="text" class="pPro" size="25" value="SUBSCRIPTION SERVICES - 657, NEWPORT NEWS" name="tocNm_A" ezfhyo="A" ezftoupper></td>
													<td align="center"><input style="text-align:right;" type="text" class="" size="6" value="100" name="prcAllocRate_A" ezfhyo="A"></td>
													<td align="center"><label ezfout name="dsContrIntfcStsCd_A" ezfhyo="A">E</label></td>
													<td align="left"><input type="text" class="" size="20" value="Status Not Eligible." name="intfcErrMsgTxt_A" ezfname="intfcErrMsgTxt_A" ezfhyo="A"></td>
													<td align="left"><label ezfout name="dsContrProcStsDescTxt_A" ezfhyo="A">Compleated</label></td>
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
