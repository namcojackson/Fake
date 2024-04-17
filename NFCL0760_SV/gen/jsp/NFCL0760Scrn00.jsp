<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180910192053 --%>
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
			<input type="hidden" name="pageID" value="NFCL0760Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Auto Write-Off Result Inquiry Screen Detail">
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
										<li title="Auto Write-Off Result Inquiry Screen Detail" class="pTab_ON"><a href="#">Wrt Off Dtl</a></li>
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
								<table border="0" style="table-layout:fixed;">
									<col width="5">
									<col width="50">
									<col width="110">
									<col width="110">
									<col width="200">
									<col width="200">
									<col width="150">
									<col width="150">
									<tr height="24">
										<td>&nbsp;</td>
										<td class="stab">User ID</td>
										<td><ezf:inputText name="wrtOffRqstUsrId_H" ezfName="wrtOffRqstUsrId_H" value="0---------1-----E" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
										<td class="stab">Write Off Request#</td>
										<td><ezf:inputText name="wrtOffRqstGrpCd_H" ezfName="wrtOffRqstGrpCd_H" value="0--------1---------2---------3---------4" otherAttr=" size=\"25\" ezftoupper=\"\""/></td>
										<td class="stab">Total Amount Adjusted/To be Adjusted</td>
										<td><ezf:inputText name="xxTotAmt_H" ezfName="xxTotAmt_H" value="0--------1---------2---------3---------4" otherAttr=" size=\"15\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="OpenWin_AttachFile" value="Attachment" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<!-- ######################################## Parameter of the request ######################################## -->
					<table>
						<br>
						<col width="5">
						<col width="800">
						<col width="308">
						<tr>
							<td>&nbsp;</td>
							<td>
							<fieldset>
								<table width="1040" border="0" cellpadding="0" cellspacing="0" height="120">
									<col width="80">
									<col width="100">
									<col width="100">
									<col width="20">
									<col width="250">
									<col width="150">
									<col width="100">
									<col width="20">
									<col width="220">
									<tr>
										<td class="stab" colspan="10">Parameter of the request</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Reason</td>
										<td colspan="3"><ezf:inputText name="arAdjRsnDescTxt_H" ezfName="arAdjRsnDescTxt_H" value="0--------1---------2---------3---------4---------5" otherAttr=" size=\"50\" ezftoupper=\"\""/></td>
										<td class="stab">Activity</td>
										<td colspan="3"><ezf:inputText name="arAdjTpDescTxt_H" ezfName="arAdjTpDescTxt_H" value="0--------1---------2---------3---------4---------5" otherAttr=" size=\"50\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Note</td>
										<td colspan="7"><ezf:inputText name="arWrtOffNoteTxt_H" ezfName="arWrtOffNoteTxt_H" value="0--------1---------2---------3---------4---------5" otherAttr=" size=\"50\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Remaining Amount</td>
										<td><ezf:inputText name="lowRmngBalAmt_H" ezfName="lowRmngBalAmt_H" value="-123,456,789,012,345.99" otherAttr=" size=\"13\" ezftoupper=\"\""/></td>
										<td class="stab" align="center">-</td>
										<td><ezf:inputText name="highRmngBalAmt_H" ezfName="highRmngBalAmt_H" value="-123,456,789,012,345.99" otherAttr=" size=\"13\" ezftoupper=\"\""/></td>
										<td class="stab">Invoice#</td>
										<td><ezf:inputText name="lowInvNum_H" ezfName="lowInvNum_H" value="0--------1---" otherAttr=" size=\"13\" ezftoupper=\"\""/></td>
										<td class="stab" align="center">-</td>
										<td><ezf:inputText name="highInvNum_H" ezfName="highInvNum_H" value="0--------1---" otherAttr=" size=\"13\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Due Date</td>
										<td><ezf:inputText name="lowInvDueDt_H" ezfName="lowInvDueDt_H" value="MM/DD/YYYY" otherAttr=" size=\"13\" ezftoupper=\"\""/></td>
										<td class="stab" align="center">-</td>
										<td><ezf:inputText name="highInvDueDt_H" ezfName="highInvDueDt_H" value="MM/DD/YYYY" otherAttr=" size=\"13\" ezftoupper=\"\""/></td>
										<td class="stab">Customer Number</td>
										<td><ezf:inputText name="lowDsAcctNum_H" ezfName="lowDsAcctNum_H" value="0--------1---------2" otherAttr=" size=\"13\" ezftoupper=\"\""/></td>
										<td class="stab" align="center">-</td>
										<td><ezf:inputText name="highDsAcctNum_H" ezfName="highDsAcctNum_H" value="0--------1---------2" otherAttr=" size=\"13\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Invoice Class</td>
										<td><ezf:inputText name="invTpDescTxt_H" ezfName="invTpDescTxt_H" value="0--------1---------2---------3---------4---------5" otherAttr=" size=\"13\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab">Include Consolidated Invoice</td>
										<td><ezf:inputText name="inclConslInvFlg_H" ezfName="inclConslInvFlg_H" value="Y" otherAttr=" style=\"text-align:center\" size=\"2\" ezftoupper=\"\""/></td>
										<td colspan="3">&nbsp;</td>
									</tr>
								</table>
							</fieldset>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
					<hr>
					<!-- ######################################## DETAIL HEADER ######################################## -->
 					<table border="0" style="table-layout:fixed;width=96%;margin-left:20;">
						<col width="500">	<!-- PreferredView -->
						<col width="500">	<!-- Paging -->
						<tr>
							<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
							<!-- Pagination & Navigation--START-->
							<td  align="right">
								<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"           value="A" />
									<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"          value="FULL" />
								</jsp:include>
								<ezf:skip>
								<table border="0" cellpadding="0" width="100%">
									<tr>
										<td align="left">
											<table border="0" cellpadding="0" align="left" cellspacing="0">
												<col>
													<tr>
													<td>Results 999 - 999 of 999</td>
													</tr>
											</table>
										</td>
										<td align="right">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="center">
												<col width="16"  align="center">
												<col width="40"  align="center">
												<col width="26"  align="center">
												<col width="10">
												<col>
												<col width="20">
												<col>
												<col width="1">
												<col>
												<tr>
													<td class="stab">Showing</td>
													<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
													<td class="stab">/</td>
													<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
													<td class="stab">page</td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								</ezf:skip>
							</td>
							<!-- Pagination & Navigation--END-->
						</tr>
					</table>
					<!-- ######################################## DETAIL ######################################## -->
					<table style="margin-left:20;">
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:305px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1080px" style="margin-right:20px">
												<col width="120" align="center">	<!-- Adjustment Date -->
												<col width="100" align="center">	<!-- Cust Num -->
												<col width="200" align="center">	<!-- Customer Name -->
												<col width="95"  align="center">	<!-- Invoice# -->
												<col width="80"  align="center">	<!-- Inv Cls -->
												<col width="200" align="center">	<!-- Inv Type -->
												<col width="100" align="center">	<!-- Invoice Amount -->
												<col width="80"  align="center">	<!-- Due Date -->
												<col width="100" align="center">	<!-- WO Pdg AMT -->
												<col width="100" align="center">	<!-- Rmng Bal -->
												<col width="60"  align="center">	<!-- Adj Num -->
												<col width="100" align="center">	<!-- Adj Amt -->
												<col width="130"  align="center">	<!-- Wf Sts -->
												<col width="570" align="center">	<!-- Error Message -->
												<tr height="24px">
													<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'adjDt_A')">Adj / Req Date</a><img id="sortIMG.adjDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustAcctCd_A')">Cust Num</a><img id="sortIMG.billToCustAcctCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A')">Customer Name</a><img id="sortIMG.dsAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'arTrxNum_A')">Invoice#</a><img id="sortIMG.arTrxNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invTpDescTxt_A')">Inv Cls</a><img id="sortIMG.invTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsInvTpDescTxt_A')">Inv Type</a><img id="sortIMG.dsInvTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealOrigGrsAmt_A')">Invoice Amount</a><img id="sortIMG.dealOrigGrsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invDueDt_A')">Due Date</a><img id="sortIMG.invDueDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealApplyAdjRsvdAmt_A')">WO Pdg AMT</a><img id="sortIMG.dealApplyAdjRsvdAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealRmngBalGrsAmt_A')">Rmng Bal</a><img id="sortIMG.dealRmngBalGrsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'arAdjNum_A')">Adj Num</a><img id="sortIMG.arAdjNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealArAdjAmt_A')">Adj Amt</a><img id="sortIMG.dealArAdjAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'arDsWfStsDescTxt_A')">Wf Sts</a><img id="sortIMG.arDsWfStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'wrtOffErrMsgTxt_A')">Error Message</a><img id="sortIMG.wrtOffErrMsgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id="rightTbl" style="width:1080; height:305px; display:block; overflow:scroll; overflow-y:hidden; margin:0px; padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1080px" >
												<col width="120" align="center">	<!-- Adjustment Date -->
												<col width="100" align="center">	<!-- Cust Num -->
												<col width="200" align="center">	<!-- Customer Name -->
												<col width="95"  align="center">	<!-- Invoice# -->
												<col width="80"  align="center">	<!-- Inv Cls -->
												<col width="200" align="center">	<!-- Inv Type -->
												<col width="100" align="center">	<!-- Invoice Amount -->
												<col width="80"  align="center">	<!-- Due Date -->
												<col width="100" align="center">	<!-- WO Pdg AMT -->
												<col width="100" align="center">	<!-- Rmng Bal -->
												<col width="60"  align="center">	<!-- Adj Num -->
												<col width="100" align="center">	<!-- Adj Amt -->
												<col width="130"  align="center">	<!-- Wf Sts -->
												<col width="570" align="center">	<!-- Error Message -->
												<ezf:row ezfHyo="A">
												<tr height="24px">
													<td align="center"><ezf:label name="adjDt_A" ezfName="adjDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" value="0--------1---------2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="0--------1---------2---------3---------4--------5---------6---------7---------8--------9---------0--------1---------2---------3---------4--------5---------6---------7---------8--------9---------0--------1---------2---------3---------4--------5---------6---------7---------8--------9---------0--------1---------2---------3---------4--------5---------6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="arTrxNum_A" ezfName="arTrxNum_A" value="0--------1---" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="invTpDescTxt_A" ezfName="invTpDescTxt_A" value="0--------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" style=\"border:none; background-color:transparent;\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="dsInvTpDescTxt_A" ezfName="dsInvTpDescTxt_A" value="0--------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="dealOrigGrsAmt_A" ezfName="dealOrigGrsAmt_A" value="1,000.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="center"><ezf:label name="invDueDt_A" ezfName="invDueDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="dealApplyAdjRsvdAmt_A" ezfName="dealApplyAdjRsvdAmt_A" value="1,000.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="dealRmngBalGrsAmt_A" ezfName="dealRmngBalGrsAmt_A" value="1,000.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="arAdjNum_A" ezfName="arAdjNum_A" value="0-------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="dealArAdjAmt_A" ezfName="dealArAdjAmt_A" value="1,000.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="arDsWfStsDescTxt_A" ezfName="arDsWfStsDescTxt_A" value="0--------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:inputText name="wrtOffErrMsgTxt_A" ezfName="wrtOffErrMsgTxt_A" value="0--------1---------2---------3---------4--------5---------6---------7---------8--------9---------0---------1---------2---------3---------4--------5---------6---------7---------8--------9---------0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"79\" style=\"border:none; background-color:transparent;\""/></td>
												</tr>
												<ezf:skip>
												</ezf:skip>
												</ezf:row>
											</table>
										</div><!-- rightTbl -->
									</div><!-- right table -->
								</div><!-- parent box -->
							</td>
						</tr>
					</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>
	<script type="text/javascript" defer>
	    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true);
	</script>

<%-- **** Task specific area ends here **** --%>
