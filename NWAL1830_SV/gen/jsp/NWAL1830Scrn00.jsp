<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170530141607 --%>
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
			<input type="hidden" name="pageID" value="NWAL1830Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Loan Order Conversion Entry">
			


<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td>
		<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%"><li title = "Loan Order Conversion Entry" class="pTab_ON" ><a href="#">Loan Conv</a></li></td>
								<td valign="bottom" align="center"><a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a></td>
								<td valign="bottom" align="center"><a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a></td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

			<!-- Body of Selected Tab -->

			<div class="pTab_BODY" style="width:100%; text-align:left;" >
				<table border="0" cellspacing="0">
					<tr>
						<td valign="top">
							<fieldset>
							<legend style="font-size:12px;">Order Header</legend>
							<table border="0" cellspacing="0" width="100%">
								<tr>
									<col width="">
									<col width="10">
									<col width="">
									<td>
										<table border="0" cellspacing="0">
											<col width="10">
											<col width="">
											<tr>
												<td></td>
												<td>
													<table border="0" cellspacing="0">
														<col width="100">
														<col width="100">
														<col width="20">
														<col width="90">
														<col width="100">
														<col width="20">
														<col width="120">
														<col width="100">
														<tr>
															<td class="stab">Order Number</td>
															<td><ezf:inputText name="cpoOrdNum_OH" ezfName="cpoOrdNum_OH" value="S10012345" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
															<td></td>
															<td class="stab">Order Date</td>
															<td><ezf:inputText name="ordDt_OH" ezfName="ordDt_OH" value="07/01/2014" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
															<td></td>
															<td class="stab">Salesrep</td>
															<td><ezf:inputText name="slsRepTocNm_OH" ezfName="slsRepTocNm_OH" value="J. SMITH" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
														</tr>
														<tr>
															<td class="stab">Order Category</td>
															<td><ezf:inputText name="dsOrdCatgDescTxt_OH" ezfName="dsOrdCatgDescTxt_OH" value="LOAN, CSA" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
															<td></td>
															<td class="stab">Order Status</td>
															<td><ezf:inputText name="ordHdrStsNm_OH" ezfName="ordHdrStsNm_OH" value="Booked" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
															<td></td>
															<td class="stab">Sales Branch</td>
															<td><ezf:inputText name="coaBrCd_OH" ezfName="coaBrCd_OH" value="100" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
														</tr>
														<tr>
															<td class="stab">Order Reason</td>
															<td><ezf:inputText name="dsOrdTpDescTxt_OH" ezfName="dsOrdTpDescTxt_OH" value="ESS-LOAN" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
															<td></td>
															<td class="stab">Acquisition#</td>
															<td><ezf:inputText name="aquNum_OH" ezfName="aquNum_OH" value="12323-3" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
															<td></td>
															<td class="stab">Sales Branch Name</td>
															<td><ezf:inputText name="coaBrDescTxt_OH" ezfName="coaBrDescTxt_OH" value="PHILLY" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
														</tr>
														<tr>
															<td class="stab">Sub Reason</td>
															<td><ezf:inputText name="dsOrdRsnDescTxt_OH" ezfName="dsOrdRsnDescTxt_OH" value="MERKETTING EVENT" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
															<td></td>
															<td class="stab">Loan Period</td>
															<td><ezf:inputText name="loanPerDaysAot_OH" ezfName="loanPerDaysAot_OH" value="15" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
									<td></td>
									<td valign="top">
										<fieldset>
											<table cellspacing="0" cellspacing="0">
												<col width="60">
												<col width="213">
												<tr>
													<td class="stab" colspan="3">Ship To Customer</td>
												</tr>
												<tr>
													<td class="stab">Account#</td>
													<td><ezf:inputText name="shipToCustAcctCd_OH" ezfName="shipToCustAcctCd_OH" value="100253" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
												</tr>
												<tr>
													<td class="stab">Name</td>
													<td><ezf:inputText name="shipToCustAcctNm_OH" ezfName="shipToCustAcctNm_OH" value="ABC INC." otherAttr=" size=\"30\" tabindex=\"-1\""/></td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
							</table>
							</fieldset>
						</td>
					</tr>
				</table>
				<table border="0" cellpadding="1" cellspacing="1" style="table-layout:fixed;">
					<tr>
						<td valign="top">
							<fieldset style="height=450px">
							<legend style="font-size:12px;">Order Line</legend>
							<table border="0" cellspacing="0">
								<tr>
									<td>
										<table border="0" cellspacing="0">
											<col width="10">
											<col width="">
											<col width="">
											<tr>
												<td></td>
												<td valign="top">
													<fieldset style="height=75px">
													<table border="0" cellspacing="0">
														<col width="60">
														<col width="100">
														<col width="10">
														<col width="60">
														<col width="40">
														<tr>
															<td class="stab">Action</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<td></td>
															<td><ezf:select name="dsOrdLineCatgCd_OL" ezfName="dsOrdLineCatgCd_OL" ezfBlank="1" ezfCodeName="dsOrdLineCatgCd_CD" ezfDispName="xxScrItem50Txt_NM" otherAttr=" style=\"width: 145px; \""/>
															</td>
															<td></td>
															<td class="stab">* Select ALL</td>
															<td><ezf:inputCheckBox name="xxChkBox_OL" ezfName="xxChkBox_OL" value="Y" onClick="sendServer('OnChange_SelectAll')" otherAttr=" size=\"20\""/></td>
														</tr>
														<tr>
															<td></td>
															<td></td>
															<td></td>
															<td colspan="2"><ezf:inputButton name="MassApply" value="Mass Apply" htmlClass="pBtn7" /></td>
														</tr>
													</table>
												</td>
												<td>
													<fieldset style="height=75px">
														<table cellspacing="0">
															<col width="130">
															<col width="10">
															<col width="100">
															<col width="120">
															<col width="20">
															<col width="80">
															<col width="200">
															<tr>
																<td><ezf:inputRadio name="xxRadioBtn_OL" ezfName="xxRadioBtn_OL" value="0" otherAttr=" tabindex=\"0\" onclick=\"sendServer('OnChange_RadioBtnNewExistingOrder');\""/>New Order</input></td>
																<td></td>
																<td class="stab">Order Category</td>
																<td><ezf:select name="dsOrdCatgCd_OL" ezfName="dsOrdCatgCd_OL" ezfBlank="1" ezfCodeName="dsOrdCatgCd_CD" ezfDispName="dsOrdCatgDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_OrderCategory')\"" otherAttr=" style=\"width: 160px; \""/>
																</td>
																<td></td>
																<td class="stab">Order Reason</td>
																<td><ezf:select name="dsOrdTpCd_OL" ezfName="dsOrdTpCd_OL" ezfBlank="1" ezfCodeName="dsOrdTpCd_CD" ezfDispName="dsOrdTpDescTxt_NM" otherAttr=" style=\"width: 180px; \""/>
																</td>
															</tr>
															<tr>
																<td></td>
																<td></td>
																<td class="stab"><ezf:anchor name="slsRepTocNm_LK" ezfName="slsRepTocNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SalesrepSearch" otherAttr=" id=\"slsRepTocNm_LK\" tabindex=\"0\" ezfanchortext=\"\"">Salesrep</ezf:anchor></td>
																<td colspan="3"><ezf:inputText name="slsRepTocNm_OL" ezfName="slsRepTocNm_OL" value="ENGESETHER, EVAN LAWRENCE" otherAttr=" size=\"35\""/></td>
																<td></td>
															</tr>
															<tr>
																<td><ezf:inputRadio name="xxRadioBtn_OL" ezfName="xxRadioBtn_OL" value="1" otherAttr=" tabindex=\"0\" onclick=\"sendServer('OnChange_RadioBtnNewExistingOrder');\""/>Existing Order</input></td>
																<td></td>
																<td class="stab"><ezf:anchor name="cpoOrdNum_LK" ezfName="cpoOrdNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderSearch" otherAttr=" id=\"cpoOrdNum_LK\" ezfanchortext=\"\"">Order Number</ezf:anchor></td>
																<td><ezf:inputText name="cpoOrdNum_OL" ezfName="cpoOrdNum_OL" value="S10012345" otherAttr=" size=\"22\" tabindex=\"-1\""/></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
														</table>
													</fieldset>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="15"><i>Please select eligible lines, and take an action:</i></td>
								</tr>
							</table>
							
							<div style="width:100%; height:320px; overflow-y:scroll; margin:0px; padding:0px;">
							
							<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
							<%@ page import="business.servlet.NWAL1830.NWAL1830BMsg" %>
							<%@ page import="business.servlet.NWAL1830.NWAL1830_ABMsg" %>
							<%@ page import="business.servlet.NWAL1830.NWAL1830_BBMsg" %>
							<% NWAL1830BMsg bMsg = (NWAL1830BMsg)databean.getEZDBMsg(); 
								int lineIdx = 0;
							%>
							<% for ( int i = 0; i < bMsg.A.getValidCount(); i++ ) { %> 
							
							<table border="0" cellspacing="0">
								<col width="40"  align="left">
								<col width="50" align="left">
								<col width="150" align="left">
								<col width="10"  align="left">
								<col width="40" align="left">
								<col width="200" align="left">
								<col width="10"  align="left">
								<col width="50" align="left">
								<col width="240" align="left">
								<col width="10"  align="left">
								<col width="50" align="left">
								<col width="240" align="left">
								<tr>
									<td>
										<div id="<%= i %>">
										<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>"  onClick="sendServer('OnChange_ConfigCheck', this.parentNode.id)"  />
										</div>
									</td>
									<td class="stab">Config ID</td>
									<td><ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>
									<td></td>
									<td class="stab">Model</td>
									<td><ezf:inputText name="mdlNm_A1" ezfName="mdlNm_A1" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"27\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td></td>
									<td class="stab">Ship To</td>
									<td><ezf:inputText name="shipToCustLocAddr_A1" ezfName="shipToCustLocAddr_A1" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"33\" maxlength=\"4000\" ezftoupper=\"\""/></td>
									<td></td>
									<td class="stab">Bill To</td>
									<td><ezf:inputText name="billToCustLocAddr_A1" ezfName="billToCustLocAddr_A1" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabindex=\"-1\" size=\"33\" maxlength=\"4000\" ezftoupper=\"\""/></td>
								</tr>
							</table>
							<%--------------------------------------------%>
							<%---------------- List START ----------------%>
							<%--------------------------------------------%>
							<table border="0" cellspacing="0" cellpadding = "0">
								<tr>
									<!-- *** Left Table-->
									<td align="left" valign="top" cellpadding = "0">
										<div id="leftTBL" style="overflow-y:hidden; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));">
										<!-- Left Label-->
										<table border="1" cellpadding="1" cellspacing="0" width="530">
											<col width="110" align="center">
											<col width="20"  align="center" >
											<col width="50"  align="center" >
											<col width="120"  align="center" >
											<col width="200" align="center" >
											
											<tr height="25">
												<td class="pClothBs">Action</td>
												<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs">Line#</td>
												<td class="pClothBs">Item#</td>
												<td class="pClothBs">Item Despriction</td>
											</tr>
										</table>
										<!-- Left Data-->
										
										<table border="1" cellpadding="1" cellspacing="0" width="530">
											<col width="110" align="center">
											<col width="20"  align="left">
											<col width="50"  align="left">
											<col width="120"  align="left">
											<col width="200" align="center">
											<% int rowNumLeftB = 0; %>
											<% for ( int j = lineIdx; j < bMsg.B.getValidCount(); j++ ) { %>
																										
											<%
												NWAL1830_ABMsg configLineMsg = bMsg.A.no(i);
												String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_A1.getValue();
												String dsOrdPosnNum_Line = bMsg.B.no(j).dsOrdPosnNum_B1.getValue();
												
												String[] lineNums = bMsg.B.no(j).dplyLineNum_B1.getValue().split("\\.", 0);
												if (!dsOrdPosnNum.equals(dsOrdPosnNum_Line)) {
													break;
												}
											%>
												<tr height="25" id=<%= "leftB" + "#" + dsOrdPosnNum + "_" + rowNumLeftB %>>
													<%
													String xxDplyCtrlFlg = bMsg.B.no(j).xxDplyCtrlFlg_B1.getValue();
													if ("Y".equals(xxDplyCtrlFlg)) {
													%>
														
														<td>
														<div id="<%= j %>">
														<ezf:select name="dsOrdLineCatgCd_BA" ezfName="dsOrdLineCatgCd_BA" ezfBlank="1" ezfHyo="B" ezfArrayNo="<%= j %>" ezfCodeName="dsOrdLineCatgCd_CD" ezfDispName="xxScrItem50Txt_NM" otherEvent1=" onchange=\"sendServer('OnChange_LineAction',this.parentNode.id)\"" otherAttr="tabindex=\"0\" style=\"width:110\""/>
														</div>
														</td>
													<% } else {%>
														<td><ezf:inputText name="xxScrItem50Txt_B1" ezfName="xxScrItem50Txt_B1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"15\" maxlength=\"55\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
														<ezf:inputHidden name="dsOrdLineCatgCd_BA" ezfName="dsOrdLineCatgCd_BA" ezfHyo="B" ezfArrayNo="<%= j %>"/>
													<%} %>
													<%if ("Y".equals(xxDplyCtrlFlg)) { %>
														<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="<%= j %>"/></td>
													<% } else { %>
														<ezf:inputHidden name="xxChkBox_B1" ezfName="xxChkBox_B1" ezfHyo="B" ezfArrayNo="<%= j %>"/>
														<td>&nbsp;</td>
													<% } %>
													<td><ezf:label name="dplyLineNum_B1" ezfName="dplyLineNum_B1" ezfHyo="B" ezfArrayNo="<%= j %>" /></td>
													<td><ezf:label name="mdseCd_B1" ezfName="mdseCd_B1" ezfHyo="B" ezfArrayNo="<%= j %>" /></td>
													<td><ezf:inputText name="mdseDescShortTxt_B1" ezfName="mdseDescShortTxt_B1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"27\" maxlength=\"250\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
												</tr>
											
											<% rowNumLeftB++;
											} %>
										</table>
										</div>
									</td>
									
									<!-- *** Right Table-->
									<td valign="top" cellpadding = "0">
									<div id="rightTBL" style="overflow-y:hidden; overflow-x:scroll; width:550;" onScroll="synchroScrollTop(this.id, new Array('leftTBL')); onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
										<!-- Right Label-->
										<table border="1" cellpadding="1" cellspacing="0" width="1550" style="table-layout:fixed;" >
											<col align="center" width="70" > <!-- Order Qty -->
											<col align="center" width="70" > <!-- Shipped Qty -->
											<col align="center" width="140"> <!-- Model -->
											<col align="center" width="150"> <!-- Status -->
											<col align="center" width="150"> <!-- Line Category -->
											<col align="center" width="150"> <!-- MDSE Type -->
											<col align="center" width="150"> <!-- COA Product -->
											<col align="center" width="150"> <!-- WH -->
											<col align="center" width="120"> <!-- Serial -->
											<col align="center" width="70" > <!-- Process Flag -->
											<col align="center" width="100" > <!-- Loan Conv*Order# -->
											<col align="center" width="80" > <!-- Loan Conv*Line# -->
											<col align="center" width="150"> <!-- Loan Conv*Status -->
											<tr height="25">
												<td class="pClothBs">Order Qty</td>
												<td class="pClothBs">Shipped Qty</td>
												<td class="pClothBs">Model</td>
												<td class="pClothBs">Status</td>
												<td class="pClothBs">Line Category</td>
												<td class="pClothBs">Merch Type</td>
												<td class="pClothBs">Product</td>
												<td class="pClothBs">Warehouse</td>
												<td class="pClothBs">Serial#</td>
												<td class="pClothBs">Processed</td>
												<td class="pClothBs">SELL/RMA Order#</td>
												<td class="pClothBs">Line#</td>
												<td class="pClothBs">Line Status</td>
											</tr>
										</table>
										<table border="1" cellpadding="1" cellspacing="0" width="1550" style="table-layout: fixed;">
											<col align="right"  width="70" > <!-- Qty -->
											<col align="right"  width="70" > <!-- Qty -->
											<col align="left"   width="140"> <!-- Model -->
											<col align="left"   width="150"> <!-- Status -->
											<col align="left"   width="150"> <!-- Line Category -->
											<col align="left"   width="150"> <!-- MDSE Type -->
											<col align="left"   width="150"> <!-- COA Product -->
											<col align="left"   width="150"> <!-- WH -->
											<col align="left"   width="120"> <!-- Serial -->
											<col align="center" width="70" > <!-- Process Flag -->
											<col align="center" width="100"> <!-- Loan Conv*Order# -->
											<col align="left"   width="80" > <!-- Loan Conv*Line# -->
											<col align="left"   width="150"> <!-- Loan Conv*Status -->
											<% int rowNumRightB = 0; %>
											<% for ( int j = lineIdx; j < bMsg.B.getValidCount(); j++ ) { %>
																										
											<%
												NWAL1830_ABMsg configLineMsg = bMsg.A.no(i);
												String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_A1.getValue();
												String dsOrdPosnNum_Line = bMsg.B.no(j).dsOrdPosnNum_B1.getValue();
												
												String[] lineNums = bMsg.B.no(j).dplyLineNum_B1.getValue().split("\\.", 0);
												if (!dsOrdPosnNum.equals(dsOrdPosnNum_Line)) {
													break;
												}
												lineIdx++;
												
											%>
											<tr height="25" id=<%= "rightB" + "#" + dsOrdPosnNum + "_" + rowNumRightB %>>
												<td><ezf:inputText name="ordQty_B1" ezfName="ordQty_B1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"8\" maxlength=\"15\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="shipQty_B1" ezfName="shipQty_B1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"8\" maxlength=\"15\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="mdlNm_B1" ezfName="mdlNm_B1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"18\" maxlength=\"360\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="ordLineStsDescTxt_B1" ezfName="ordLineStsDescTxt_B1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"20\" maxlength=\"50\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="dsOrdLineCatgDescTxt_B1" ezfName="dsOrdLineCatgDescTxt_B1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"20\" maxlength=\"50\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="coaMdseTpDescTxt_B1" ezfName="coaMdseTpDescTxt_B1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"20\" maxlength=\"50\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="coaProdDescTxt_B1" ezfName="coaProdDescTxt_B1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"20\" maxlength=\"50\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
												<td>
													<table border="0" cellpadding="1" cellspacing="0">
														<tr style="padding:0;">	
															<td><ezf:inputText name="rtlWhNm_B1" ezfName="rtlWhNm_B1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"0\" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/></td>
															<td>
																<ezf:anchor name="rtlWhNm_LK" ezfName="rtlWhNm_LK" ezfHyo="B" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" tabindex=\"0\" id=\"rtlWhNm_LK\"    ezfanchortext=\"\"">W</ezf:anchor>
															</td>
														</tr>
													</table>
												</td>
												<td><ezf:inputText name="serNum_B1" ezfName="serNum_B1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"16\" maxlength=\"25\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="procFlg_B1" ezfName="procFlg_B1" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"1\" maxlength=\"1\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
												<% String cpoOrdNumConv = bMsg.B.no(j).cpoOrdNum_B2.getValue();%>
											    <% if ("".equals(cpoOrdNumConv)) {%>
											    	<td><ezf:label name="cpoOrdNum_B2" ezfName="cpoOrdNum_B2" ezfHyo="B" ezfArrayNo="<%= j %>" /></td>
											    <% } else {%>
													<td><ezf:anchor name="" ezfName="" ezfHyo="B" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="MoveWin_OrderEntry" otherAttr=" tabindex=\"0\" id=\"MoveWin_OrderEntry\""><ezf:label name="cpoOrdNum_B2" ezfName="cpoOrdNum_B2" ezfHyo="B" ezfArrayNo="<%= j %>" /></ezf:anchor></td>
												<% } %>
												<td><ezf:inputText name="dplyLineNum_B2" ezfName="dplyLineNum_B2" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"10\" maxlength=\"15\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="ordLineStsDescTxt_B2" ezfName="ordLineStsDescTxt_B2" ezfHyo="B" ezfArrayNo="<%= j %>" otherAttr=" tabindex=\"-1\" size=\"20\" maxlength=\"15\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
											</tr>
											<% rowNumRightB++;
											} %>
										</table>
									</div>
									</td>
								</tr>
							</table>
							<% } %>
							</div>
							</fieldset>
						</td>
					</tr>
				</table>
				<br>
			</div><!-- pTab_BODY -->
		</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
