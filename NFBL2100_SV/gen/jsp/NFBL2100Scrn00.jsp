<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171222140407 --%>
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
			<input type="hidden" name="pageID" value="NFBL2100Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Lease Buyout Approve List">
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
								<table border="0" style="table-layout:fixed;">
									<col width=  "5">	<!--  1 () -->
									<col width="100">	<!--  2 Workflow Status -->
									<col width="120">	<!--  3 list box or Textfield -->
									<col width= "25">	<!--  4 () -->
									<col width= "90">	<!--  5 customer and thrudate -->
									<col width="115">	<!--  6 (box1) -->
									<col width= "35">	<!--  7 (>> box2) -->
									<col width= "60">	<!--  8 order# -->
									<col width="150">	<!--  9 ordernum text -->
									<col width="250">	<!-- 10 () -->
									<col width="100">	<!-- 11 Search button -->
									<col width=  "5">	<!-- 12 () -->
									<tr height="24">
										<td></td>
										<td class="stab">Workflow Status</td>
										<td>
											<ezf:select name="apDsWfStsCd_SV" ezfName="apDsWfStsCd_SV" ezfBlank="1" ezfCodeName="apDsWfStsCd_CD" ezfDispName="apDsWfStsDescTxt_SC" otherAttr=" style=\"width:110px;\""/>
										</td>
										<td></td>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_CustomerSearch" >Supplier Site</ezf:anchor></td>
										<td><ezf:inputText name="vndCd" ezfName="vndCd" value="XXXXXXXX" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_custNm" value=">>" htmlClass="pBtn0" /></td>
										<td align="left" colspan="3">
											<ezf:inputText name="dplyVndNm" ezfName="dplyVndNm" value="AZERTY, A DIVISION OF UNITED STATIONERS SUPPLY CO. - 952418" otherAttr=" size=\"60\""/>
										</td>
										<td></td>
										<td></td>
									</tr>
									<tr height="24">
										<td></td>
										<td class="stab">Order Date(From)</td>
										<td>
											<ezf:inputText name="xxFromDt" ezfName="xxFromDt" value="01/01/2016" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxFromDt', 4);" id="xxFromDt">
										</td>
										<td></td>
										<td class="stab">Order Date(To)</td>
										<td>
											<ezf:inputText name="xxToDt" ezfName="xxToDt" value="12/31/2016" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxToDt', 4);" id="xxToDt">
										</td>
										<td></td>
										<td class="stab">Order Num</td>
										<td><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="XXXXXXXXX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td></td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br>
					<!-- ######################################## DETAIL HEADER ######################################## -->
 					<table border="0" style="table-layout:fixed;width=96%;margin-left:20;">
						<col width="280">	<!-- PreferredView -->
						<col width="350">	<!-- Paging -->
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
									<table border="0" cellpadding="1" cellspacing="0">
											<col >
											<col width="40"  align="right">
											<col width="16"  align="center">
											<col width="40"  align="right">
											<col width="16"  align="center">
											<col width="40"  align="right">
											<col width="10">
											<col width="0">
											<col width="1">
											<col width="0">
											<col width="1">
											<tr>
												<td class="stab">Showing</td>
												<td class="pOut">1</td>
												<td class="stab">to</td>
												<td class="pOut">3</td>
												<td class="stab">of</td>
												<td class="pOut">1000</td>
												<td>&nbsp;</td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
												<td></td>
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
										<div id='leftTbl' style="float:left; display:block; height:427px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0p x;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1080px" style="margin-right:20px">
												<col width="120" align="center">	<!-- Status -->
												<col width="80"  align="center">	<!-- Order Num -->
												<col width="70"  align="center">	<!-- Order LN -->
												<col width="100" align="center">	<!-- Invoice Num -->
												<col width="120" align="center">	<!-- Amount -->
												<col width="220" align="center">	<!-- Approver Name -->
												<col width="80"  align="center">	<!-- Order Date -->
												<col width="220" align="center">	<!-- Order TP -->
												<col width="70"  align="center">	<!-- BOL Num -->
												<col width="100" align="center">	<!-- Inv Line -->
												<col width="120" align="center">	<!-- Item Code -->
												<col width="220" align="center">	<!-- Item Name -->
												<col width="280" align="center">	<!-- Approver's Responsibility -->
												<col width="120" align="center">	<!-- Approver Limit -->
												<col width="80"  align="center">	<!-- Date Notification -->
												<col width="220" align="center">	<!-- Notification# -->
												<col width="400" align="center">	<!-- Comments from Responder -->
												<col width="80"  align="center">	<!-- Date Response -->
												<col width="80"  align="center">	<!-- Pay To -->
												<col width="120" align="center">	<!-- A/R Invoice -->
												<col width="120" align="center">	<!-- A/P Invoice -->
												<col width="17">	<!-- Scroll -->
												<tr height="40px" valign="middle">
													<td id="AH0"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'apDsWfStsDescTxt_A')">Workflow Status</a><img id="sortIMG.apDsWfStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'cpoOrdNum_A')">Order Num</a><img id="sortIMG.cpoOrdNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dplyLineNum_A')">Order LN</a><img id="sortIMG.dplyLineNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invNum_A')">Invoice Num</a><img id="sortIMG.invNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'cpoDtlFuncNetAmt_A')">Amount</a><img id="sortIMG.cpoDtlFuncNetAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'apvlUsrNm_A')">Approver Name</a><img id="sortIMG.apvlUsrNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxToDt_A')">Order Date</a><img id="sortIMG.xxToDt_A"   border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsOrdCatgDescTxt_A')">Order TP</a><img id="sortIMG.dsOrdCatgDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invBolNum_A')">BOL Num</a><img id="sortIMG.invBolNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invLineNum_A')">Invioce Line Number</a><img id="sortIMG.invLineNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A')">Item Code</a><img id="sortIMG.mdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseDescShortTxt_A')">Item Name</a><img id="sortIMG.mdseDescShortTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'apvlRspbNm_A')">Approval Responsibility</a><img id="sortIMG.apvlRspbNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'apvlLimitAmt_A')">Approver Limit</a><img id="sortIMG.apvlLimitAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'apWfMlNtfyDt_A')">Date Notification</a><img id="sortIMG.apWfMlNtfyDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'apWfMlNtfyNum_A')">Notification#</a><img id="sortIMG.apWfMlNtfyNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'apWfRqstCmntTxt_A')">Comments from Responder</a><img id="sortIMG.apWfRqstCmntTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'apWfRqstRspDt_A')">Date Response</a><img id="sortIMG.apWfRqstRspDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'vndCd_A')">Supplier Site Code</a><img id="sortIMG.vndCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'crArInvNum_A')">Org A/R Invoice</a><img id="sortIMG.crArInvNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'apInvNum_A')">A/P Invoice</a><img id="sortIMG.apInvNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id='rightTbl' style="width:1080; height:427px; display:block; overflow-x:scroll; overflow-y:hidden; margin:0px; padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1080px" >
												<col width="120" align="center">	<!-- Status -->
												<col width="80"  align="center">	<!-- Order Num -->
												<col width="70"  align="center">	<!-- Order LN -->
												<col width="100" align="center">	<!-- Invoice Num -->
												<col width="120" align="center">	<!-- Amount -->
												<col width="220" align="center">	<!-- Approver Name -->
												<col width="80"  align="center">	<!-- Order Date -->
												<col width="220" align="center">	<!-- Order TP -->
												<col width="70"  align="center">	<!-- BOL Num -->
												<col width="100" align="center">	<!-- Inv Line -->
												<col width="120" align="center">	<!-- MDSE CD -->
												<col width="220" align="center">	<!-- MDSE NM -->
												<col width="280" align="center">	<!-- Approver's Responsibility -->
												<col width="120" align="center">	<!-- Approver Limit -->
												<col width="80"  align="center">	<!-- Date Notification -->
												<col width="220" align="center">	<!-- Notification# -->
												<col width="400" align="center">	<!-- Comments from Responder -->
												<col width="80"  align="center">	<!-- Date Response -->
												<col width="80" align="center">	<!-- Pay To -->
												<col width="120" align="center">	<!-- A/R Invoice -->
												<col width="120" align="center">	<!-- A/P Invoice -->
												<ezf:row ezfHyo="A">
												<tr height="24px">
													<td align="left" ><ezf:inputText name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" value="Completed" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" >
														<ezf:anchor name="cpoOrdNum_A" ezfName="cpoOrdNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_CPO', '{EZF_ROW_NUMBER}" otherAttr=" ezfanchortext=\"\"">
  															<ezf:label name="cpoOrdNum_A" ezfName="cpoOrdNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"border:none; background-color:transparent;\""/>
  														</ezf:anchor>
													</td>
													<td align="left" ><ezf:inputText name="dplyLineNum_A" ezfName="dplyLineNum_A" value="001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" ><ezf:inputText name="invNum_A" ezfName="invNum_A" value="1234567890123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="right"><ezf:inputText name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" value="123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;text-align:right;\""/></td>
													<td align="left" ><ezf:inputText name="apvlUsrNm_A" ezfName="apvlUsrNm_A" value="Taro,Yamada" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" ><ezf:inputText name="xxToDt_A" ezfName="xxToDt_A" value="11/16/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" ><ezf:inputText name="dsOrdCatgDescTxt_A" ezfName="dsOrdCatgDescTxt_A" value="DEALER SERVICE EXCHANGE, CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" ><ezf:inputText name="invBolNum_A" ezfName="invBolNum_A" value="001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" ><ezf:inputText name="invLineNum_A" ezfName="invLineNum_A" value="00001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" ><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="010ZZ999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" ><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="LEASE BUYOUT/REIMBURSEMENT" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" ><ezf:inputText name="apvlRspbNm_A" ezfName="apvlRspbNm_A" value="Buyout Approver - Accounting Director" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="right"><ezf:inputText name="apvlLimitAmt_A" ezfName="apvlLimitAmt_A" value="123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;text-align:right;\""/></td>
													<td align="left" ><ezf:inputText name="apWfMlNtfyDt_A" ezfName="apWfMlNtfyDt_A" value="01/01/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" ><ezf:inputText name="apWfMlNtfyNum_A" ezfName="apWfMlNtfyNum_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\" style=\"text-align:right;\""/></td>
													<td align="left" ><ezf:inputText name="apWfRqstCmntTxt_A" ezfName="apWfRqstCmntTxt_A" value="**Warning** The original invoice has already been paid." ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"55\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" ><ezf:inputText name="apWfRqstRspDt_A" ezfName="apWfRqstRspDt_A" value="11/17/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" ><ezf:inputText name="vndCd_A" ezfName="vndCd_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" ><ezf:inputText name="crArInvNum_A" ezfName="crArInvNum_A" value="INVNUM01" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="left" >
														<ezf:anchor name="xxLinkAncr_A" ezfName="xxLinkAncr_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ApInv', '{EZF_ROW_NUMBER}" otherAttr=" id=\"openApInv{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
  															<ezf:label name="apInvNum_A" ezfName="apInvNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"border:none; background-color:transparent;\""/>
  														</ezf:anchor>
													</td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												<tr height="24px">
													<td align="left" ><input type="text" class="pPro" size="15" name="apDsWfStsDescTxt_A" ezfName="apDsWfStsDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Completed"></td>
													<td align="left" ><input type="text" class="pPro" size="8"  name="cpoOrdNum_A"        ezfName="cpoOrdNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="12345678"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="dplyLineNum_A"      ezfName="dplyLineNum_A"      ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="invNum_A"           ezfName="invNum_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890123"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="cpoDtlFuncNetAmt_A" ezfName="cpoDtlFuncNetAmt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apvlUsrNm_A"        ezfName="apvlUsrNm_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Taro,Yamada"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="xxToDt_A"           ezfName="xxToDt_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/16/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="dsOrdCatgDescTxt_A" ezfname="dsOrdCatgDescTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="DEALER SERVICE EXCHANGE, CSA"></td>
													<td align="left" ><input type="text" class="pPro" size="3"  name="invBolNum_A"        ezfName="invBolNum_A"        ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="001"></td>
													<td align="left" ><input type="text" class="pPro" size="5"  name="invLineNum_A"       ezfName="invLineNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="00001"></td>
													<td align="left" ><input type="text" class="pPro" size="16" name="mdseCd_A"           ezfName="mdseCd_A"           ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="010ZZ999"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="LEASE BUYOUT/REIMBURSEMENT"></td>
													<td align="left" ><input type="text" class="pPro" size="40" name="apvlRspbNm_A"       ezfName="apvlRspbNm_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="Manager"></td>
													<td align="right"><input type="text" class="pPro" size="15" name="apvlLimitAmt_A"     ezfName="apvlLimitAmt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;text-align:right;" value="123,456,789.12"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfMlNtfyDt_A"     ezfName="apWfMlNtfyDt_A"     ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="01/01/2016"></td>
													<td align="left" ><input type="text" class="pPro" size="30" name="apWfMlNtfyNum_A"    ezfName="apWfMlNtfyNum_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="123456789012345678901234567890" style="text-align:right;"></td>
													<td align="left" ><input type="text" class="pPro" size="55" name="apWfRqstCmntTxt_A"  ezfName="apWfRqstCmntTxt_A"  ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="**Warning** The original invoice has already been paid."></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="apWfRqstRspDt_A"    ezfName="apWfRqstRspDt_A"    ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="11/17/2015"></td>
													<td align="left" ><input type="text" class="pPro" size="10" name="vndCd_A"            ezfName="vndCd_A"            ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="1234567890"></td>
													<td align="left" ><input type="text" class="pPro" size="13" name="crArInvNum_A"       ezfName="crArInvNum_A"       ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM01"></td>
													<td align="left" ><input type="text" class="pPro" size="15" name="apInvNum_A"         ezfName="apInvNum_A"         ezfhyo="A" readOnly style="border:none; background-color:transparent;"                  value="INVNUM02"></td>
												</tr>
												</ezf:skip>
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
   S21TableUI.initialize("parentBoxA", "AHEAD", "A", 3, false);
</script>

<%-- **** Task specific area ends here **** --%>
