<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220325163339 --%>
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
			<input type="hidden" name="pageID" value="NSAL0810Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Interface Maintenance">

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
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Infc Mnt</a></li>
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
									<col width="220">
									<col width="15">
									<col width="80">
									<col width="150">
									<col width="20">
									<col width="80">
									<col width="150">
									<col>
									<tr height="21">
										<td></td>
										<td class="stab">Source Type</td>
										<td><ezf:select name="contrIntfcSrcTpCd_SS" ezfName="contrIntfcSrcTpCd_SS" ezfBlank="1" ezfCodeName="contrIntfcSrcTpCd_SC" ezfDispName="contrIntfcSrcTpDescTxt_SC" /></td>
										<td></td>
										<td class="stab">Interface Bat#</td>
										<td><ezf:inputText name="dsContrIntfcBatNum_S" ezfName="dsContrIntfcBatNum_S" value="200215" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Source Ref#</td>
										<td><ezf:inputText name="dsContrSrcRefNum_S" ezfName="dsContrSrcRefNum_S" value="200215" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td></td>
									</tr>
									<tr height="21">
										<td></td>
										<td class="stab">Interface Date</td>
										<td><ezf:inputText name="dsContrIntfcDt_SF" ezfName="dsContrIntfcDt_SF" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsContrIntfcDt_SF', 4);"> - <ezf:inputText name="dsContrIntfcDt_ST" ezfName="dsContrIntfcDt_ST" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsContrIntfcDt_ST', 4);"></td>
										<td></td>
										<td class="stab">Errors Only</td>
										<td><ezf:inputCheckBox name="xxErrFlg_S" ezfName="xxErrFlg_S" value="Y" /></td>
										<td></td>
										<td></td>
										<td></td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><hr></td>
						</tr>
						<tr>
							<td>
								<table>
									<col width="160">
									<col width="770">
									<col>
									<tr>
										<td></td>
										<td>
											<div id="Detail" style="overflow-x:none; overflow-y:none; width:550; float:left;">
												<fieldset style="margin:5px 0px 0px 0px; height:100%;">
												<legend style="font-size:12px;">Search Result Summary</legend>
												<table width="100%" border="0">
													<tr>
														<td>
															<table border="0" style="table-layout:fixed;" width="85%" align="center">
																<col width="20">
																<col width="185">
																<col width="50">
																<col width="40">
																<col width="170">
																<col width="50">
																<col width="30">
																<tr height="21">
																	<td>&nbsp;</td>
																	<td class="stab">Num Of Processed Transactions :</td>
																	<td><ezf:inputText name="xxSrchCnt_SP" ezfName="xxSrchCnt_SP" value="12345" otherAttr=" size=\"5\" style=\"text-align:right\""/></td>
																	<td>&nbsp;</td>
																	<td class="stab">Num Of Records In Error Status :</td>
																	<td><ezf:inputText name="xxSrchCnt_SR" ezfName="xxSrchCnt_SR" value="12345" otherAttr=" size=\"5\" style=\"text-align:right\""/></td>
																	<td>&nbsp;</td>
																</tr>
																<tr height="21">
																	<td>&nbsp;</td>
																	<td class="stab">Num Of Unprocessed Transactions :</td>
																	<td><ezf:inputText name="xxSrchCnt_SU" ezfName="xxSrchCnt_SU" value="12345" otherAttr=" size=\"5\" style=\"text-align:right\""/></td>
																	<td>&nbsp;</td>
																	<td class="stab">Num Of Invalid Records :</td>
																	<td><ezf:inputText name="xxSrchCnt_SI" ezfName="xxSrchCnt_SI" value="12345" otherAttr=" size=\"5\" style=\"text-align:right\""/></td>
																	<td>&nbsp;</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</div>
										</td>
										<td>
											<table>
												<tr>
													<td><ezf:inputButton name="OpenForCorrection" value="Open For Correction" htmlClass="pBtn12" />&nbsp;</td>
												</tr>
												<tr>
													<td><ezf:inputButton name="ResubmitBatInterface" value="Resubmit Bat Interface" htmlClass="pBtn12" />&nbsp;</td>
												</tr>
												<tr>
													<td><ezf:inputButton name="SubmitBatValidation" value="Submit Bat Validation" htmlClass="pBtn12" />&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br>
					<!-- ######################################## Detail ######################################## -->
 					<table border="0" style="table-layout:fixed;width=1082px;margin-left:20;">
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
										<div id='leftTbl' style="float:left; display:block; height:293px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1065px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="8404px" style="margin-right:20px" >
												<col width="30" align="center">	<!-- CheckBox -->
												<col width="105" align="center">	<!-- Interface Bat# -->
												<col width="75" align="center">	<!-- Source Ref# -->
												<col width="135" align="center">	<!-- Source Type -->
												<col width="75" align="center">	<!-- Contract# -->
												<col width="135" align="center">	<!-- Action -->
												<col width="125" align="center">	<!-- Serial# -->
												<col width="120" align="center">	<!-- IB ID -->
												<col width="100" align="center">	<!-- Line Type -->
												<col width="50" align="center">	<!-- Proc Flag -->
												<col width="120" align="center">	<!-- Proc Message -->
												<col width="100" align="center">	<!-- Proc Status -->
												<col width="125" align="center">	<!-- Cust Acct Num -->
												<col width="125" align="center">	<!-- Cust Acct Name -->
												<col width="125" align="center">	<!-- Bill To Customer Number -->
												<col width="125" align="center">	<!-- Lease Company Num -->
												<col width="50" align="center">	<!-- Contract Branch -->
												<col width="70" align="center">	<!-- Contract Rep -->
												<col width="70" align="center">	<!-- Sales Rep -->
												<col width="70" align="center">	<!-- PO# -->
												<col width="100" align="center">	<!-- PO Exp Date -->
												<col width="150" align="center">	<!-- CC Reference# -->
												<col width="70" align="center">	<!-- CC Exp Date -->
												<col width="100" align="center">	<!-- Auto Estimation Code -->
												<col width="100" align="center">	<!-- Service Program -->
												<col width="125" align="center">	<!-- Mdse Code -->
												<col width="125" align="center">	<!-- Model Name -->
												<col width="100" align="center">	<!-- Start Date -->
												<col width="100" align="center">	<!-- End Date -->
												<col width="125" align="center">	<!-- Frequency -->
												<col width="90" align="center">	<!-- Alloc Across Machines -->
												<col width="150" align="center">	<!-- Renewal Type -->
												<col width="125" align="center">	<!-- Renewal Method -->
												<col width="50" align="center">	<!-- # Days Before -->
												<col width="80" align="center">	<!-- Renewal % -->
												<col width="150" align="center">	<!-- Annual Escalation Type -->
												<col width="125" align="center">	<!-- Escalation Method -->
												<col width="90" align="center">	<!-- Escalation Percent -->
												<col width="150" align="center">	<!-- Meter Read Method -->
												<col width="90" align="center">	<!-- Price Per Period -->
												<col width="70" align="center">	<!-- Period End Date Ctrl -->
												<col width="50" align="center">	<!-- Invoice Date Ctrl -->
												<col width="100" align="center">	<!-- Bill Through Date -->
												<col width="80" align="center">	<!-- Overage Counter Code -->
												<col width="150" align="center">	<!-- Overage Counter Name -->
												<col width="90" align="center">	<!-- Start Reading -->
												<col width="90" align="center">	<!-- Counter Rollover % -->
												<col width="90" align="center">	<!-- Contract Category -->
												<col width="90" align="center">	<!-- Contract Status -->
												<col width="90" align="center">	<!-- Excess Charge Type -->
												<col width="90" align="center">	<!-- Excess Meter Copy Qty -->
												<col width="90" align="center">	<!-- Excess Meter Amount Rate -->
												<col width="180" align="center">	<!-- Additional Charge Type -->
												<col width="90" align="center">	<!-- Additional Charge Amount -->
												<col width="100" align="center">	<!-- Additional Charge Applicable % -->
												<col width="100" align="center">	<!-- Charge Level -->
												<col width="100" align="center">	<!-- Additional Charge Invoice Type -->
												<col width="60" align="center">	<!-- Print on Invoice -->
												<col width="90" align="center">	<!-- Term Amount Rate -->
												<col width="100" align="center">	<!-- Contract Class -->
												<col width="100" align="center">	<!-- Contact Person ID -->
												<col width="125" align="center">	<!-- Contact Person Name -->
												<col width="200" align="center">	<!-- Invoicing Option -->
												<col width="100" align="center">	<!-- Contract Close Date -->
												<col width="90" align="center">	<!-- Duration -->
												<col width="90" align="center">	<!-- Payment Term Discount -->
												<col width="90" align="center">	<!-- Line of Business -->
												<col width="90" align="center">	<!-- Billing Timing -->
												<col width="60" align="center">	<!-- Contract EDI -->
												<col width="150" align="center">	<!-- Contract Description -->
												<col width="100" align="center">	<!-- Base Charge To Lease Company -->
												<col width="100" align="center">	<!-- Usage Charge To Lease Company -->
												<col width="90" align="center">	<!-- Intangible Mdse Code -->
												<col width="90" align="center">	<!-- Cap BW Original Qty -->
												<col width="90" align="center">	<!-- Cap Color Original Qty -->
												<col width="90" align="center">	<!-- Cap Total Original Qty -->
												<col width="90" align="center">	<!-- Cap BW Running Qty -->
												<col width="90" align="center">	<!-- Cap Color Running Qty -->
												<col width="90" align="center">	<!-- Cap Total Running Qty -->
												<col width="120" align="center">	<!-- Manual Contract Override -->
												<tr height="34px">
													<td id="AH0" class="pClothBs colFix" colFix>&nbsp;</td>
													<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcBatNum_A')">Interface Bat#</a><img id="sortIMG.dsContrIntfcBatNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrSrcRefNum_A')">Source Ref#</a><img id="sortIMG.dsContrSrcRefNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrIntfcSrcTpCd_AS')">Source Type</a><img id="sortIMG.contrIntfcSrcTpCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')">Contract#</a><img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcActCd_AS')">Action</a><img id="sortIMG.dsContrIntfcActCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')">Serial#</a><img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachMstrPk_A')">IB ID</a><img id="sortIMG.svcMachMstrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrIntfcDtlTpCd_AS')">Line Type</a><img id="sortIMG.contrIntfcDtlTpCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrIntfcStsCd_A')">Proc Flag</a><img id="sortIMG.dsContrIntfcStsCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'intfcErrMsgTxt_A')">Proc Message</a><img id="sortIMG.intfcErrMsgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrProcStsCd_AS')">Proc Status</a><img id="sortIMG.dsContrProcStsCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNum_A')">Cust Acct Num</a><img id="sortIMG.dsAcctNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_AC')">Cust Acct Name</a><img id="sortIMG.dsAcctNm_AC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd_A')">Bill To Customer Number</a><img id="sortIMG.billToCustCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'leaseCmpyCd_A')">Lease Company Num</a><img id="sortIMG.leaseCmpyCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcContrBrCd_A')">Contract Branch</a><img id="sortIMG.svcContrBrCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrAdminPsnCd_A')">Contract Rep</a><img id="sortIMG.contrAdminPsnCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'tocCd_A')">Sales Rep</a><img id="sortIMG.tocCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'custPoNum_A')">PO#</a><img id="sortIMG.custPoNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'poDt_A')">PO Exp Date</a><img id="sortIMG.poDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'crCardCustRefNum_A')">CC Reference#</a><img id="sortIMG.crCardCustRefNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'crCardExprYrMth_A')">CC Exp Date</a><img id="sortIMG.crCardExprYrMth_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrEstTpCd_AS')">Auto Estimation Code</a><img id="sortIMG.mtrEstTpCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcPgmMdseCd_A')">Service Program</a><img id="sortIMG.svcPgmMdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A')">Item Code</a><img id="sortIMG.mdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdlNm_A')">Model Name</a><img id="sortIMG.mdlNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrFromDt_A')">Start Date</a><img id="sortIMG.contrFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrThruDt_A')">End Date</a><img id="sortIMG.contrThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH29" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgCycleCd_AS')">Frequency</a><img id="sortIMG.bllgCycleCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH30" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'prcAllocByMachQtyFlg_A')">Alloc Across Machines</a><img id="sortIMG.prcAllocByMachQtyFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH31" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrAutoRnwTpCd_AS')">Renewal Type</a><img id="sortIMG.contrAutoRnwTpCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH32" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rnwPrcMethCd_AS')">Renewal Method</a><img id="sortIMG.rnwPrcMethCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH33" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'befEndRnwDaysAot_A')"># Days Before</a><img id="sortIMG.befEndRnwDaysAot_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH34" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rnwPrcUpRatio_A')">Renewal %</a><img id="sortIMG.rnwPrcUpRatio_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH35" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrUplftTpCd_AS')">Annual Escalation Type</a><img id="sortIMG.contrUplftTpCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH36" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'uplftPrcMethCd_AS')">Escalation Method</a><img id="sortIMG.uplftPrcMethCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH37" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'uplftPrcUpRatio_A')">Escalation Percent</a><img id="sortIMG.uplftPrcUpRatio_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH38" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrReadMethCd_AS')">Meter Read Method</a><img id="sortIMG.mtrReadMethCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH39" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'basePrcDealAmt_A')">Price Per Period</a><img id="sortIMG.basePrcDealAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH40" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrCloDay_A')">Period End Date Ctrl</a><img id="sortIMG.contrCloDay_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH41" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrBllgDay_A')">Invoice Date Ctrl</a><img id="sortIMG.contrBllgDay_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH42" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgThruDt_A')">Bill Through Date</a><img id="sortIMG.bllgThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH43" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgMtrLbCd_A')">Overage Counter Code</a><img id="sortIMG.bllgMtrLbCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH45" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrLbDescTxt_A')">Overage Counter Name</a><img id="sortIMG.mtrLbDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH46" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'startMtrCnt_A')">Start Reading</a><img id="sortIMG.startMtrCnt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH47" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgRollOverRatio_A')">Counter Rollover %</a><img id="sortIMG.bllgRollOverRatio_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH48" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrCatgCd_AS')">Contract Category</a><img id="sortIMG.dsContrCatgCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH49" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrStsCd_AS')">Contract Status</a><img id="sortIMG.dsContrStsCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH50" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xsChrgTpCd_AS')">Excess Charge Type</a><img id="sortIMG.xsChrgTpCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH51" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xsMtrCopyQty_A')">Excess Meter Copy Qty</a><img id="sortIMG.xsMtrCopyQty_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH52" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xsMtrAmtRate_A')">Excess Meter Amount Rate</a><img id="sortIMG.xsMtrAmtRate_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH53" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'addlChrgTpCd_AS')">Additional Charge Type</a><img id="sortIMG.addlChrgTpCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH54" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'addlChrgFlatDealPrcAmt_A')">Additional Charge Amount</a><img id="sortIMG.addlChrgFlatDealPrcAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH55" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'addlChrgAplcPct_A')">Additional Charge Applicable</a><img id="sortIMG.addlChrgAplcPct_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH56" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'chrgLvlTpCd_AS')">Charge Level</a><img id="sortIMG.chrgLvlTpCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH57" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'addlChrgInvTpCd_AS')">Additional Charge Invoice Type</a><img id="sortIMG.addlChrgInvTpCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH58" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'printDtlFlg_A')">Print on Invoice</a><img id="sortIMG.printDtlFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH59" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'basePrcTermDealAmtRate_A')">Term Amount Rate</a><img id="sortIMG.basePrcTermDealAmtRate_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH60" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrClsCd_AS')">Contract Class</a><img id="sortIMG.dsContrClsCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH61" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'ctacPsnPk_A')">Contact Person ID</a><img id="sortIMG.ctacPsnPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH62" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'ctacPsnNm_A')">Contact Person Name</a><img id="sortIMG.ctacPsnNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH63" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsInvTgtrTpCd_AS')">Invoicing Option</a><img id="sortIMG.dsInvTgtrTpCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH64" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrCloDt_A')">Contract Close Date</a><img id="sortIMG.contrCloDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH65" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrDurnAot_A')">Duration</a><img id="sortIMG.contrDurnAot_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH66" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'pmtTermCashDiscCd_A')">Payment Term Discount</a><img id="sortIMG.pmtTermCashDiscCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH67" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcLineBizCd_AS')">Line of Business</a><img id="sortIMG.svcLineBizCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH68" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgTmgTpCd_AS')">Billing Timing</a><img id="sortIMG.bllgTmgTpCd_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH69" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrEdiCd_A')">Contract EDI</a><img id="sortIMG.dsContrEdiCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH70" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrDescTxt_A')">Contract Description</a><img id="sortIMG.dsContrDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH71" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'baseChrgToLeaseCmpyFlg_A')">Base Charge To Lease Company</a><img id="sortIMG.baseChrgToLeaseCmpyFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH72" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'usgChrgToLeaseCmpyFlg_A')">Usage Charge To Lease Company</a><img id="sortIMG.usgChrgToLeaseCmpyFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH73" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'intgMdseCd_A')">Intangible Item Code</a><img id="sortIMG.intgMdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH74" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'capBwOrigQty_A')">Cap BW Original Qty</a><img id="sortIMG.capBwOrigQty_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH75" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'capColorOrigQty_A')">Cap Color Original Qty</a><img id="sortIMG.capColorOrigQty_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH76" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'capTotOrigQty_A')">Cap Total Original Qty</a><img id="sortIMG.capTotOrigQty_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH77" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'capBwRunQty_A')">Cap BW Running Qty</a><img id="sortIMG.capBwRunQty_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH78" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'capColorRunQty_A')">Cap Color Running Qty</a><img id="sortIMG.capColorRunQty_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH79" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'capTotRunQty_A')">Cap Total Running Qty</a><img id="sortIMG.capTotRunQty_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH80" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'manContrOvrdFlg_A')">Manual Contract Override</a><img id="sortIMG.manContrOvrdFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id="rightTbl" style="width:1082px; height:310px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="8404px" >
												<col width="30" align="center">	<!-- CheckBox -->
												<col width="105" align="center">	<!-- Interface Bat# -->
												<col width="75" align="center">	<!-- Source Ref# -->
												<col width="135" align="center">	<!-- Source Type -->
												<col width="75" align="center">	<!-- Contract# -->
												<col width="135" align="center">	<!-- Action -->
												<col width="125" align="center">	<!-- Serial# -->
												<col width="120" align="center">	<!-- IB ID -->
												<col width="100" align="center">	<!-- Line Type -->
												<col width="50" align="center">	<!-- Proc Flag -->
												<col width="120" align="center">	<!-- Proc Message -->
												<col width="100" align="center">	<!-- Proc Status -->
												<col width="125" align="center">	<!-- Cust Acct Num -->
												<col width="125" align="center">	<!-- Cust Acct Name -->
												<col width="125" align="center">	<!-- Bill To Customer Number -->
												<col width="125" align="center">	<!-- Lease Company Num -->
												<col width="50" align="center">	<!-- Contract Branch -->
												<col width="70" align="center">	<!-- Contract Rep -->
												<col width="70" align="center">	<!-- Sales Rep -->
												<col width="70" align="center">	<!-- PO# -->
												<col width="100" align="center">	<!-- PO Exp Date -->
												<col width="150" align="center">	<!-- CC Reference# -->
												<col width="70" align="center">	<!-- CC Exp Date -->
												<col width="100" align="center">	<!-- Auto Estimation Code -->
												<col width="100" align="center">	<!-- Service Program -->
												<col width="125" align="center">	<!-- Mdse Code -->
												<col width="125" align="center">	<!-- Model Name -->
												<col width="100" align="center">	<!-- Start Date -->
												<col width="100" align="center">	<!-- End Date -->
												<col width="125" align="center">	<!-- Frequency -->
												<col width="90" align="center">	<!-- Alloc Across Machines -->
												<col width="150" align="center">	<!-- Renewal Type -->
												<col width="125" align="center">	<!-- Renewal Method -->
												<col width="50" align="center">	<!-- # Days Before -->
												<col width="80" align="center">	<!-- Renewal % -->
												<col width="150" align="center">	<!-- Annual Escalation Type -->
												<col width="125" align="center">	<!-- Escalation Method -->
												<col width="90" align="center">	<!-- Escalation Percent -->
												<col width="150" align="center">	<!-- Meter Read Method -->
												<col width="90" align="center">	<!-- Price Per Period -->
												<col width="70" align="center">	<!-- Period End Date Ctrl -->
												<col width="50" align="center">	<!-- Invoice Date Ctrl -->
												<col width="100" align="center">	<!-- Bill Through Date -->
												<col width="80" align="center">	<!-- Overage Counter Code -->
												<col width="150" align="center">	<!-- Overage Counter Name -->
												<col width="90" align="center">	<!-- Start Reading -->
												<col width="90" align="center">	<!-- Counter Rollover % -->
												<col width="90" align="center">	<!-- Contract Category -->
												<col width="90" align="center">	<!-- Contract Status -->
												<col width="90" align="center">	<!-- Excess Charge Type -->
												<col width="90" align="center">	<!-- Excess Meter Copy Qty -->
												<col width="90" align="center">	<!-- Excess Meter Amount Rate -->
												<col width="180" align="center">	<!-- Additional Charge Type -->
												<col width="90" align="center">	<!-- Additional Charge Amount -->
												<col width="100" align="center">	<!-- Additional Charge Applicable % -->
												<col width="100" align="center">	<!-- Charge Level -->
												<col width="100" align="center">	<!-- Additional Charge Invoice Type -->
												<col width="60" align="center">	<!-- Print on Invoice -->
												<col width="90" align="center">	<!-- Term Amount Rate -->
												<col width="100" align="center">	<!-- Contract Class -->
												<col width="100" align="center">	<!-- Contact Person ID -->
												<col width="125" align="center">	<!-- Contact Person Name -->
												<col width="200" align="center">	<!-- Invoicing Option -->
												<col width="100" align="center">	<!-- Contract Close Date -->
												<col width="90" align="center">	<!-- Duration -->
												<col width="90" align="center">	<!-- Payment Term Discount -->
												<col width="90" align="center">	<!-- Line of Business -->
												<col width="90" align="center">	<!-- Billing Timing -->
												<col width="60" align="center">	<!-- Contract EDI -->
												<col width="150" align="center">	<!-- Contract Description -->
												<col width="100" align="center">	<!-- Base Charge To Lease Company -->
												<col width="100" align="center">	<!-- Usage Charge To Lease Company -->
												<col width="90" align="center">	<!-- Intangible Mdse Code -->
												<col width="90" align="center">	<!-- Cap BW Original Qty -->
												<col width="90" align="center">	<!-- Cap Color Original Qty -->
												<col width="90" align="center">	<!-- Cap Total Original Qty -->
												<col width="90" align="center">	<!-- Cap BW Running Qty -->
												<col width="90" align="center">	<!-- Cap Color Running Qty -->
												<col width="90" align="center">	<!-- Cap Total Running Qty -->
												<col width="120" align="center">	<!-- Manual Contract Override -->
												<ezf:row ezfHyo="A">
												<tr height="23px" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
													<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A_#{EZF_ROW_NUMBER}\""/></td>
													<td align="left"><ezf:label name="dsContrIntfcBatNum_A" ezfName="dsContrIntfcBatNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="dsContrSrcRefNum_A" ezfName="dsContrSrcRefNum_A" value="12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:select name="contrIntfcSrcTpCd_AS" ezfName="contrIntfcSrcTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="contrIntfcSrcTpCd_AC" ezfDispName="contrIntfcSrcTpDescTxt_AC" otherAttr=" style=\"width:130;\""/></td>
													<td align="center"><ezf:inputText name="dsContrNum_A" ezfName="dsContrNum_A" value="12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:select name="dsContrIntfcActCd_AS" ezfName="dsContrIntfcActCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsContrIntfcActCd_AC" ezfDispName="dsContrIntfcActDescTxt_AC" otherAttr=" style=\"width:130;\""/></td>
													<td align="center"><ezf:inputText name="serNum_A" ezfName="serNum_A" value="W2345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Machine" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="right"><ezf:inputText name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" value="12345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="left"><ezf:select name="contrIntfcDtlTpCd_AS" ezfName="contrIntfcDtlTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="contrIntfcDtlTpCd_AC" ezfDispName="contrIntfcDtlTpDescTxt_AC" otherAttr=" style=\"width:95;\""/></td>
													<td align="center"><ezf:label name="dsContrIntfcStsCd_A" ezfName="dsContrIntfcStsCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="left" class="stab"><ezf:inputText name="intfcErrMsgTxt_A" ezfName="intfcErrMsgTxt_A" value="NAAM0000E:Error XXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" ezftoupper=\"\""/></td>
													<td align="left"><ezf:select name="dsContrProcStsCd_AS" ezfName="dsContrProcStsCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsContrProcStsCd_AC" ezfDispName="dsContrProcStsDescTxt_AC" otherAttr=" style=\"width:95;\""/></td>
													<td align="center"><ezf:inputText name="dsAcctNum_A" ezfName="dsAcctNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_CustAcctNum" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="left" class="stab"><ezf:inputText name="dsAcctNm_AC" ezfName="dsAcctNm_AC" value="Cust nameXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="billToCustCd_A" ezfName="billToCustCd_A" value="billcust" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_BillToCustNum" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="center"><ezf:inputText name="leaseCmpyCd_A" ezfName="leaseCmpyCd_A" value="lease" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_LeaseCustNum" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="center"><ezf:inputText name="svcContrBrCd_A" ezfName="svcContrBrCd_A" value="XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="contrAdminPsnCd_A" ezfName="contrAdminPsnCd_A" value="XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="tocCd_A" ezfName="tocCd_A" value="XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="custPoNum_A" ezfName="custPoNum_A" value="12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="poDt_A" ezfName="poDt_A" value="12/12/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\" id=\"poDt_A#{EZF_ROW_NUMBER}\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('poDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
													<td align="center"><ezf:inputText name="crCardCustRefNum_A" ezfName="crCardCustRefNum_A" value="cc reference" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="crCardExprYrMth_A" ezfName="crCardExprYrMth_A" value="12/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:select name="mtrEstTpCd_AS" ezfName="mtrEstTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mtrEstTpCd_AC" ezfDispName="mtrEstTpDescTxt_AC" otherAttr=" style=\"width:95;\""/></td>
													<td align="center"><ezf:inputText name="svcPgmMdseCd_A" ezfName="svcPgmMdseCd_A" value="1565491" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="1565491" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Merchandise" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="center"><ezf:inputText name="mdlNm_A" ezfName="mdlNm_A" value="XXXXXXXXname" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="contrFromDt_A" ezfName="contrFromDt_A" value="12/12/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\" id=\"contrFromDt_A#{EZF_ROW_NUMBER}\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrFromDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
													<td align="center"><ezf:inputText name="contrThruDt_A" ezfName="contrThruDt_A" value="12/12/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\" id=\"contrThruDt_A#{EZF_ROW_NUMBER}\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrThruDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
													<td align="center"><ezf:select name="bllgCycleCd_AS" ezfName="bllgCycleCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgCycleCd_AC" ezfDispName="bllgCycleDescTxt_AC" otherAttr=" style=\"width:120;\""/></td>
													<td align="center"><ezf:inputCheckBox name="prcAllocByMachQtyFlg_A" ezfName="prcAllocByMachQtyFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:select name="contrAutoRnwTpCd_AS" ezfName="contrAutoRnwTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="contrAutoRnwTpCd_AC" ezfDispName="contrAutoRnwTpDescTxt_AC" otherAttr=" style=\"width:145;\""/></td>
													<td align="center"><ezf:select name="rnwPrcMethCd_AS" ezfName="rnwPrcMethCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="rnwPrcMethCd_AC" ezfDispName="rnwPrcMethDescTxt_AC" otherAttr=" style=\"width:120;\""/></td>
													<td align="center"><ezf:inputText name="befEndRnwDaysAot_A" ezfName="befEndRnwDaysAot_A" value="123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="rnwPrcUpRatio_A" ezfName="rnwPrcUpRatio_A" value="123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:select name="contrUplftTpCd_AS" ezfName="contrUplftTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="contrUplftTpCd_AC" ezfDispName="contrUplftTpDescTxt_AC" otherAttr=" style=\"width:145;\""/></td>
													<td align="center"><ezf:select name="uplftPrcMethCd_AS" ezfName="uplftPrcMethCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="uplftPrcMethCd_AC" ezfDispName="uplftPrcMethDescTxt_AC" otherAttr=" style=\"width:120;\""/></td>
													<td align="center"><ezf:inputText name="uplftPrcUpRatio_A" ezfName="uplftPrcUpRatio_A" value="123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:select name="mtrReadMethCd_AS" ezfName="mtrReadMethCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mtrReadMethCd_AC" ezfDispName="mtrReadMethDescTxt_AC" otherAttr=" style=\"width:145;\""/></td>
													<td align="center"><ezf:inputText name="basePrcDealAmt_A" ezfName="basePrcDealAmt_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="contrCloDay_A" ezfName="contrCloDay_A" value="31" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="contrBllgDay_A" ezfName="contrBllgDay_A" value="31" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="bllgThruDt_A" ezfName="bllgThruDt_A" value="12/12/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\" id=\"bllgThruDt_A#{EZF_ROW_NUMBER}\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgThruDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
													<td align="center"><ezf:inputText name="bllgMtrLbCd_A" ezfName="bllgMtrLbCd_A" value="77" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_BllgMtr" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="center"><ezf:inputText name="mtrLbDescTxt_A" ezfName="mtrLbDescTxt_A" value="BIL COLOR LARGE AGG" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
													<td align="center"><ezf:inputText name="startMtrCnt_A" ezfName="startMtrCnt_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="bllgRollOverRatio_A" ezfName="bllgRollOverRatio_A" value="123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:select name="dsContrCatgCd_AS" ezfName="dsContrCatgCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsContrCatgCd_AC" ezfDispName="dsContrCatgDescTxt_AC" otherAttr=" style=\"width:85;\""/></td>
													<td align="center"><ezf:select name="dsContrStsCd_AS" ezfName="dsContrStsCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsContrStsCd_AC" ezfDispName="dsContrStsDescTxt_AC" otherAttr=" style=\"width:85;\""/></td>
													<td align="center"><ezf:select name="xsChrgTpCd_AS" ezfName="xsChrgTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="xsChrgTpCd_AC" ezfDispName="xsChrgTpDescTxt_AC" otherAttr=" style=\"width:85;\""/></td>
													<td align="center"><ezf:inputText name="xsMtrCopyQty_A" ezfName="xsMtrCopyQty_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="xsMtrAmtRate_A" ezfName="xsMtrAmtRate_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"text-align:right\""/></td>
													<td align="center"><ezf:select name="addlChrgTpCd_AS" ezfName="addlChrgTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="addlChrgTpCd_AC" ezfDispName="addlChrgTpDescTxt_AC" otherAttr=" style=\"width:175;\""/></td>
													<td align="center"><ezf:inputText name="addlChrgFlatDealPrcAmt_A" ezfName="addlChrgFlatDealPrcAmt_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="addlChrgAplcPct_A" ezfName="addlChrgAplcPct_A" value="12345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:select name="chrgLvlTpCd_AS" ezfName="chrgLvlTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="chrgLvlTpCd_AC" ezfDispName="chrgLvlTpDescTxt_AC" otherAttr=" style=\"width:95;\""/></td>
													<td align="center"><ezf:select name="addlChrgInvTpCd_AS" ezfName="addlChrgInvTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="addlChrgInvTpCd_AC" ezfDispName="addlChrgInvTpDescTxt_AC" otherAttr=" style=\"width:95;\""/></td>
													<td align="center"><ezf:inputCheckBox name="printDtlFlg_A" ezfName="printDtlFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="basePrcTermDealAmtRate_A" ezfName="basePrcTermDealAmtRate_A" value="123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:select name="dsContrClsCd_AS" ezfName="dsContrClsCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsContrClsCd_AC" ezfDispName="dsContrClsDescTxt_AC" otherAttr=" style=\"width:95;\""/></td>
													<td align="center"><ezf:inputText name="ctacPsnPk_A" ezfName="ctacPsnPk_A" value="XXXXXXX8" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"text-align:right\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_NMAL6770" value="Go" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
													<td align="left" class="stab"><ezf:inputText name="ctacPsnNm_A" ezfName="ctacPsnNm_A" value="ctak nameXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:select name="dsInvTgtrTpCd_AS" ezfName="dsInvTgtrTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsInvTgtrTpCd_AC" ezfDispName="dsInvTgtrTpDescTxt_AC" otherAttr=" style=\"width:195;\""/></td>
													<td align="center"><ezf:inputText name="contrCloDt_A" ezfName="contrCloDt_A" value="12/12/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\" id=\"contrCloDt_A#{EZF_ROW_NUMBER}\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrCloDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
													<td align="center"><ezf:inputText name="contrDurnAot_A" ezfName="contrDurnAot_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="pmtTermCashDiscCd_A" ezfName="pmtTermCashDiscCd_A" value="12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:select name="svcLineBizCd_AS" ezfName="svcLineBizCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="lineBizTpCd_AC" ezfDispName="lineBizTpDescTxt_AC" otherAttr=" style=\"width:85;\""/></td>
													<td align="center"><ezf:select name="bllgTmgTpCd_AS" ezfName="bllgTmgTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bllgTmgTpCd_AC" ezfDispName="bllgTmgTpDescTxt_AC" otherAttr=" style=\"width:85;\""/></td>
													<td align="center"><ezf:inputText name="dsContrEdiCd_A" ezfName="dsContrEdiCd_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"1\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="dsContrDescTxt_A" ezfName="dsContrDescTxt_A" value="123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\""/></td>
													<td align="center"><ezf:inputCheckBox name="baseChrgToLeaseCmpyFlg_A" ezfName="baseChrgToLeaseCmpyFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputCheckBox name="usgChrgToLeaseCmpyFlg_A" ezfName="usgChrgToLeaseCmpyFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputText name="intgMdseCd_A" ezfName="intgMdseCd_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="capBwOrigQty_A" ezfName="capBwOrigQty_A" value="123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="capColorOrigQty_A" ezfName="capColorOrigQty_A" value="123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="capTotOrigQty_A" ezfName="capTotOrigQty_A" value="123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="capBwRunQty_A" ezfName="capBwRunQty_A" value="123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="capColorRunQty_A" ezfName="capColorRunQty_A" value="123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputText name="capTotRunQty_A" ezfName="capTotRunQty_A" value="123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" style=\"text-align:right\" ezftoupper=\"\""/></td>
													<td align="center"><ezf:inputCheckBox name="manContrOvrdFlg_A" ezfName="manContrOvrdFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
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
												</ezf:skip>
											</table>
										</div><!-- rightTbl -->
									</div><!-- right table -->
								</div><!-- parent box -->
							</td>
						</tr>
					</table>
					<table  border="0" style="table-layout:fixed;width=96%;">
						<col align="left" width="250">
						<col>
						<col align="right">
						<tr>
							<td>
								<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn8" />
								<ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn8" />
							</td>
							<td>
								<ezf:inputButton name="ActualCounters" value="Actual Counters" htmlClass="pBtn8" />
								<ezf:inputButton name="Tiers" value="Tiers" htmlClass="pBtn8" />
								<ezf:inputButton name="AdditionalCharges" value="Additional Charges" htmlClass="pBtn9" />
								<ezf:inputButton name="SalesCredits" value="Sales Credits" htmlClass="pBtn8" />
							</td>
							<td>
								<ezf:inputButton name="ValidateData" value="Validate Data" htmlClass="pBtn8" />
								<ezf:inputButton name="ResubmitInterface" value="Resubmit Interface" htmlClass="pBtn9" />
							</td>
						</tr>
					</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>
	<script type="text/javascript" defer>
	    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, false);
	</script>


<%-- **** Task specific area ends here **** --%>
