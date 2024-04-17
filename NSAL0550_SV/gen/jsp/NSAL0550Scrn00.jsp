<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190612155330 --%>
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
			<input type="hidden" name="pageID" value="NSAL0550Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract Invoice Search">
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
										<li title="Contract Invoice Search" class="pTab_ON"><a href="#">Cont Inv Srch</a></li>
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
					<table style="table-layout:fixed;" width="98%" align="center">
						<col width="344">
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="72">
									<col width="118">
									<col width="5">
									<col width="72">
									<col width="118">
									<col width="5">
									<col width="72">
									<col width="118">
									<col width="5">
									<col width="22">
									<col width="118">
									<col width="5">
									<col width="25">
									<col width="118">
									<col width="5">
									<col width="5">
									<col width="5">
									<col width="22">
									<col width="60">
									<col width="5">
									<col width="64">
									<tr height="21">
										<td class="stab">Invoice Type</td>
										<td>
											<ezf:select name="invTpCd_H3" ezfName="invTpCd_H3" ezfBlank="1" ezfCodeName="invTpCd_H1" ezfDispName="invTpDescTxt_H2" />
										</td>
										<td>&nbsp;</td>
										<td class="stab">Contract#</td>
										<td><ezf:inputText name="dsContrNum_H1" ezfName="dsContrNum_H1" otherAttr=" size=\"13\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Invoice#</td>
										<td><ezf:inputText name="svcInvNum_H1" ezfName="svcInvNum_H1" otherAttr=" size=\"13\""/></td>
										<td>&nbsp;</td>
										<td><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" /></td>
										<td class="stab">Related Invoice#</td>
										<td>&nbsp;</td>
										<td class="stab">CI#</td>
										<td><ezf:inputText name="custCareTktNum_H1" ezfName="custCareTktNum_H1" otherAttr=" size=\"13\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="21">
										<td class="stab">Billed From</td>
										<td>
											<ezf:inputText name="bllgPerFromDt_H1" ezfName="bllgPerFromDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('bllgPerFromDt_H1', 4);">
										</td>
										<td>&nbsp;</td>
										<td class="stab">Billed To</td>
										<td>
											<ezf:inputText name="bllgPerToDt_H1" ezfName="bllgPerToDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('bllgPerToDt_H1', 4);">
										</td>
										<td>&nbsp;</td>
										<td class="stab">Invoice Date</td>
										<td>
											<ezf:inputText name="invDt_H1" ezfName="invDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('invDt_H1', 4);">
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" /></td>
										<td class="stab">Latest Only</td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<table width="99%">
						<tr align="right">
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
									<col width="200">
									<col width="40">
									<col width="218">
									<col width="120">
									<col width="520">
									<tr height="25px">
										<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td align="right" class="stab">
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
										</td>
									</tr>
								</table>
								<ezf:skip>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
									<col width="200">
									<col width="40">
									<col width="218">
									<col width="120">
									<col width="520">
									<tr height="25px">
										<td><input type="hidden" name="xxComnColOrdTxt" value=""  size="0" id="xxComnColOrdTxt"></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td align="right" class="stab">
											<table border="0" cellpadding="0" width="100%">
												<tr>
													<td align="left">
														<table border="0" cellpadding="0" align="left" cellspacing="0">
															<col>
															<tr>
																<td>Results 1 - 4 <label>of</label> 4</td>
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
																<td><input type="text" name="xxPageShowCurNum" value="1" size="4" maxlength="4" style="text-align:right" id="txtShowingCur" class="pPro" readOnly></td>
																<td class="stab">/</td>
																<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="1" class="pPro" style="text-align:right" id="txtShowingTot" readOnly></td>
																<td class="stab">Page</td>
																<td>&nbsp;</td>
																<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')" disabled></td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')" disabled></td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')" disabled></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								</ezf:skip>
							</td>
						</tr>
					</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
<%-- #################################################### DETAIL ################################################### --%>
<%@ page import="business.servlet.NSAL0550.NSAL0550BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<% NSAL0550BMsg bMsg = (NSAL0550BMsg)databean.getEZDBMsg(); %>
<%@ include file="../common/common.jsp" %>
					<table>
						<tr>
							<td>
								<div id="parentBoxA">
								<div style="float:left; display:block"> <!-- left table -->
									<div id='leftTblHead' style="display:block;overflow:hidden;margin:0px;padding:0px;">
									</div>
								<div id='leftTbl' style="float:left; display:block; height:393px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
								</div>
								</div>	<!-- left table -->
								<div style="float:left"> <!-- right table -->
									<div id='rightTblHead' style="width:1098px; display:block; overflow:hidden; margin:0px;padding:0px;">
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1098px" style="margin-right:20px" >
											<col width=" 20" align="center"> <!-- Contract#/Invoice# Select -->
											<col width=" 75" align="center"> <!-- Contract# -->
											<col width=" 75" align="center"> <!-- Invoice# -->
											<col width=" 85" align="center"> <!-- Invoice Date -->
											<col width="130" align="center"> <!-- Invoice Charge Type -->
											<col width=" 90" align="center"> <!-- Invoice Type -->
											<col width=" 80" align="center"> <!-- Invoice Source Type -->
											<col width="110" align="center"> <!-- Invoice Amount -->
											<col width="110" align="center"> <!-- Tax Amount -->
											<col width="110" align="center"> <!-- Total Amount -->
											<col width="110" align="center"> <!-- Open Amount -->
											<col width=" 85" align="center"> <!-- Date Billed From -->
											<col width=" 85" align="center"> <!-- Date Billed To -->
											<col width=" 85" align="center"> <!-- Invoice Due Date -->
											<col width=" 75" align="center"> <!-- CI Number# -->
											<col width=" 75" align="center"> <!-- Rebill Invoice# -->
											<col width=" 75" align="center"> <!-- Credit Memo Invoice# -->
											<col width=" 75" align="center"> <!-- Original Invoice# -->
											<tr height="40px">
												<td id="AH0" class="pClothBs colFix">&nbsp;</td>
												<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A1')">Contract#</a><img id="sortIMG.dsContrNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcInvNum_A1')">Invoice#</a><img id="sortIMG.svcInvNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invDt_A1')">Invoice Date</a><img id="sortIMG.invDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcInvChrgTpDescTxt_A1')">Invoice Charge Type</a><img id="sortIMG.svcInvChrgTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invTpDescTxt_A1')">Invoice Type</a><img id="sortIMG.invTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcInvSrcTpDescTxt_A1')">Invoice Source Type</a><img id="sortIMG.svcInvSrcTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invTotDealSlsAmt_A1')">Invoice Amount</a><img id="sortIMG.invTotDealSlsAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invTotDealTaxAmt_A1')">Tax Amount</a><img id="sortIMG.invTotDealTaxAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invTotDealNetAmt_A1')">Total Amount</a><img id="sortIMG.invTotDealNetAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dealRmngBalGrsAmt_A1')">Open Amount</a><img id="sortIMG.dealRmngBalGrsAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxMsgPrmTxt_SF')">Date Billed From</a><img id="sortIMG.xxMsgPrmTxt_SF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxMsgPrmTxt_ST')">Date Billed To</a><img id="sortIMG.xxMsgPrmTxt_ST" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invDueDt_A1')">Invoice Due Date</a><img id="sortIMG.invDueDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'custCareTktNum_A1')">CI Number</a><img id="sortIMG.custCareTktNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rebilSvcInvNum_A1')">Rebill Invoice#</a><img id="sortIMG.rebilSvcInvNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'crSvcInvNum_A1')">Credit Memo Invoice#</a><img id="sortIMG.crSvcInvNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'origSvcInvNum_A1')">Original Invoice#</a><img id="sortIMG.origSvcInvNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											</tr>
										</table>
									</div>
									<div id="rightTbl" style="width:1115px; height:410px; display:block; overflow:scroll; margin:0px; padding:0px;" >
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A" width="1098px">
											<col width=" 20" align="center"> <!-- Contract#/Invoice# Select -->
											<col width=" 75" align="left"> <!-- Contract# -->
											<col width=" 75" align="left"> <!-- Invoice# -->
											<col width=" 85" align="left"> <!-- Invoice Date -->
											<col width="130" align="left"> <!-- Invoice Charge Type -->
											<col width=" 90" align="left"> <!-- Invoice Type -->
											<col width=" 80" align="left"> <!-- Invoice Source Type -->
											<col width="110" align="right"> <!-- Invoice Amount -->
											<col width="110" align="right"> <!-- Tax Amount -->
											<col width="110" align="right"> <!-- Total Amount -->
											<col width="110" align="right"> <!-- Open Amount -->
											<col width=" 85" align="left"> <!-- Date Billed From -->
											<col width=" 85" align="left"> <!-- Date Billed To -->
											<col width=" 85" align="left"> <!-- Invoice Due Date -->
											<col width=" 75" align="left"> <!-- CI Number# -->
											<col width=" 75" align="left"> <!-- Rebill Invoice# -->
											<col width=" 75" align="left"> <!-- Credit Memo Invoice# -->
											<col width=" 75" align="left"> <!-- Original Invoice# -->
											<% int i = 0; %>
											<ezf:row ezfHyo="A">
												<tr>
													<td><ezf:inputRadio name="xxRowNum_H1" ezfName="xxRowNum_H1" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRowNum_H1{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:label name="dsContrNum_A1" ezfName="dsContrNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td>
														<ezf:anchor name="svcInvNum_A1" ezfName="svcInvNum_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_InvoiceEntry" otherAttr=" id=\"svcInvNum_A1#{EZF_ROW_NUMBER}\"">
															<ezf:label name="svcInvNum_A1" ezfName="svcInvNum_A1" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td><ezf:label name="invDt_A1" ezfName="invDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="svcInvChrgTpDescTxt_A1" ezfName="svcInvChrgTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="invTpDescTxt_A1" ezfName="invTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="svcInvSrcTpDescTxt_A1" ezfName="svcInvSrcTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="invTotDealSlsAmt_A1" ezfName="invTotDealSlsAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="invTotDealTaxAmt_A1" ezfName="invTotDealTaxAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="invTotDealNetAmt_A1" ezfName="invTotDealNetAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="dealRmngBalGrsAmt_A1" ezfName="dealRmngBalGrsAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td>
														<ezf:label name="xxMsgPrmTxt_FR" ezfName="xxMsgPrmTxt_FR" ezfHyo="A" ezfArrayNo="0" />
														<ezf:inputHidden name="xxMsgPrmTxt_SF" ezfName="xxMsgPrmTxt_SF" ezfHyo="A" ezfArrayNo="0" />
													</td>
													<td>
														<ezf:label name="xxMsgPrmTxt_TO" ezfName="xxMsgPrmTxt_TO" ezfHyo="A" ezfArrayNo="0" />
														<ezf:inputHidden name="xxMsgPrmTxt_ST" ezfName="xxMsgPrmTxt_ST" ezfHyo="A" ezfArrayNo="0" />
													</td>
													<td><ezf:label name="invDueDt_A1" ezfName="invDueDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td>
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).custCareTktNum_A1)) { %>
														<ezf:anchor name="custCareTktNum_A1" ezfName="custCareTktNum_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_CreditRebillMainScreen" otherAttr=" id=\"custCareTktNum_A1#{EZF_ROW_NUMBER}\"">
															<ezf:label name="custCareTktNum_A1" ezfName="custCareTktNum_A1" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
														<% } else { %>
															<ezf:label name="custCareTktNum_A1" ezfName="custCareTktNum_A1" ezfHyo="A" ezfArrayNo="0" />
														<% } %>
													</td>
													<td><ezf:label name="rebilSvcInvNum_A1" ezfName="rebilSvcInvNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="crSvcInvNum_A1" ezfName="crSvcInvNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="origSvcInvNum_A1" ezfName="origSvcInvNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
												</tr>
											<% i++; %>
											</ezf:row>
											<ezf:skip>
												<tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001306</a></td>
													<td>05/03/2017</td>
													<td>Meter Charge</td>
													<td>Invoice</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/03/2017</td>
													<td>05/03/2017</td>
													<td>05/03/2017</td>
													<td><a href="#">2001406</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr>
												<tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001311</a></td>
													<td>05/03/2017</td>
													<td>Additional Charge</td>
													<td>Invoice</td>
													<td>Contract</td>
													<td>-999,999,999.99</td>
													<td>-999,999,999.99</td>
													<td>-999,999,999.99</td>
													<td>-999,999,999.99</td>
													<td>05/03/2017</td>
													<td>05/03/2017</td>
													<td>05/03/2017</td>
													<td><a href="#">2001409</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr>
												<tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr><tr>
													<td><input type="radio" name="xxRowNum_A1" value="{EZF_ROW_NUMBER}"  ezfname="xxRowNum_A1"></td>
													<td>0000477</td>
													<td><a href="#">6001315</a></td>
													<td>05/11/2017</td>
													<td>Meter Charge</td>
													<td>Credit Memo</td>
													<td>Contract</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>999,999,999.99</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td>05/11/2017</td>
													<td><a href="#">2001410</a></td>
													<td>6001792</td>
													<td>6001793</td>
													<td>6001794</td>
												</tr>
											</ezf:skip>
										</table>
									</div>
								</div> <!-- right table -->
								</div> <!-- parent box -->
							</td>
						</tr>
					</table>
					<table border="0" width="99%">
						<tr align="right">
							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="0">
									<col width="0">
									<tr>
										<td><ezf:inputButton name="ViewPaperInvoice" value="View Paper Invoice" htmlClass="pBtn9" /></td>
										<td><ezf:inputButton name="CreditAndRebill" value="Credit And Rebill" htmlClass="pBtn8" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<style TYPE="text/css">
<!--
	tr.checkLineBGcolor{background-color:yellow;}
	a img{border-style:none;}
-->
</style>

<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", 4, false);
</script>

<script type="text/javascript">
function openCustCareTktHist(urlText) {
	var win = window.open(urlText, "CustomerCare");
	win.focus();
	win.moveTo( 0, 0 );
	urlText = null;
}

<%
String urlText = bMsg.xxSrvUrlTxt_H1.getValue();
if (ZYPCommonFunc.hasValue(urlText)) {
    urlText = getCustomAppURL("EXTNE193T020") + "?searchType=ticketNo&searchValue=" + urlText;
%>
	openCustCareTktHist('<%=urlText%>')
<% } 
bMsg.xxSrvUrlTxt_H1.setValue("");
%>
</script>

<%-- **** Task specific area ends here **** --%>
