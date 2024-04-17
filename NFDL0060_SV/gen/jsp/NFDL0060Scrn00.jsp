<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170829085843 --%>
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
			<input type="hidden" name="pageID" value="NFDL0060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Open Status Item List Inquiry">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table>
						<tr valign="top">
							<td>
								<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed; margin-left:10px">
									<col width="70">
									<col width="200">
									<col width="30">
									<col width="100">
									<col width="200">
									<col width="30">
									<col width="100">
									<col width="200">
									<col width="100">
									<tr>
										<td class="stab">Display</td>
										<td><ezf:select name="cltDispTpCd_H3" ezfName="cltDispTpCd_H3" ezfBlank="1" ezfCodeName="cltDispTpCd_H1" ezfDispName="cltDispTpDescTxt_H2" otherEvent1=" onchange=\"sendServer('OnChangeDispTp');\"" otherAttr=" style=\"width:180px;\""/></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="xxLinkProt_H1" ezfName="xxLinkProt_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_Collector" otherAttr=" ezfanchortext=\"\"">Collector</ezf:anchor></td>
										<td><ezf:inputText name="xxQueryFltrTxt_H1" ezfName="xxQueryFltrTxt_H1" otherAttr=" size=\"25\" maxlength=\"200\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Collector Name(*)</td>
										<td><ezf:inputText name="cltPsnNm_H1" ezfName="cltPsnNm_H1" otherAttr=" size=\"25\" maxlength=\"150\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab" colspan="3">
											Start Date From
											&nbsp;&nbsp;&nbsp;&nbsp;
											<ezf:inputText name="xxFromDt_H1" ezfName="xxFromDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt_H1', 4);">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											To
											&nbsp;&nbsp;&nbsp;&nbsp;
											<ezf:inputText name="xxToDt_H1" ezfName="xxToDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt_H1', 4);">
										</td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td class="stab">Portfolio Name(*)</td>
										<td><ezf:inputText name="cltPtfoNm_H1" ezfName="cltPtfoNm_H1" otherAttr=" size=\"25\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="xxLinkProt_H2" ezfName="xxLinkProt_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Account" >Account#</ezf:anchor></td>
										<td><ezf:inputText name="xxQueryFltrTxt_H2" ezfName="xxQueryFltrTxt_H2" otherAttr=" size=\"25\" maxlength=\"200\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Account Name(*)</td>
										<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" otherAttr=" size=\"25\" maxlength=\"60\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">Item Type</td>
										<td><ezf:select name="cltItemTpCd_H3" ezfName="cltItemTpCd_H3" ezfBlank="1" ezfCodeName="cltItemTpCd_H1" ezfDispName="cltItemTpDescTxt_H2" /></td>
										<td></td>
										<td class="stab">Only Open Status</td>
										<td><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" /></td>
										<td></td>
										<td></td>
										<td></td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<hr>

					<table width="1121">
						<col valign="top">
						<tr>
							<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
							<td align="right">
								<ezf:skip>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="54"  align="center">
									<col width="40"  align="right">
									<col width="16"  align="center">
									<col width="40"  align="right">
									<col width="16"  align="center">
									<col width="40"  align="right">
									<col width="10">
									<col width="50">
									<col width="50">
									<tr>
										<td class="stab">Showing</td>
										<td class="pOut">1</td>
										<td class="stab">to</td>
										<td class="pOut">20</td>
										<td class="stab">of</td>
										<td class="pOut">20</td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
										<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
									</tr>
								</table>
									</ezf:skip>


								<%-- Pagenation --%>
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

					<table>
							<tr>
								<td>
									<div id="parentBoxA">
										<div style="float:left; display:block"><!-- left table -->
											<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
											<div id="leftTbl" style="float:left; display:block; height:380px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
										</div><!-- left table -->
										<div style="float:left"><!-- right table -->
											<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;" >
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1200px" style="margin-right:20px">
													<col width="70"  align="center">		<!-- Item Type -->
													<col width="80"  align="center">		<!-- Work Item# -->
													<col width="80"  align="center">		<!-- Date -->
													<col width="120" align="center">		<!-- Strategy Name -->
													<col width="150" align="center">		<!-- Item Name -->
													<col width="150" align="center">		<!-- Owner -->
													<col width="150" align="center">		<!-- Portfolio Name -->
													<col width="150"  align="center">		<!-- Type -->
													<col width="80"  align="center">		<!-- Status -->
													<col width="55"  align="center">		<!-- Priority -->
													<col width="80"  align="center">		<!-- Account# -->
													<col width="125" align="center">		<!-- Account Name -->
													<col width="150" align="center">		<!-- Created By -->
													<col width="115" align="center">		<!-- $Amount Overdue -->
													<tr height="32">
														<td class="pClothBs" id="A3H0"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltItemTpDescTxt_A1')">Item Type</a><img id="sortIMG.cltItemTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs" id="A3H1"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltTaskPk_A1')">Work Item#</a><img id="sortIMG.cltTaskPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs" id="A3H2"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltTaskRwsdDt_A1')">Start Date</a><img id="sortIMG.cltTaskRwsdDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs" id="A3H3"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltStrgyNm_A1')">Strategy Name</a><img id="sortIMG.cltStrgyNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs" id="A3H4"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrItem130Txt_A1')">Item Name</a><img id="sortIMG.xxScrItem130Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs" id="A3H5"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltPsnNm_A1')">Owner</a><img id="sortIMG.cltPsnNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs" id="A3H6"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltPtfoNm_A1')">Portfolio Name</a><img id="sortIMG.cltPtfoNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs" id="A3H7"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltTaskTpDescTxt_A1')">Type</a><img id="sortIMG.cltTaskTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs" id="A3H8"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltTaskStsDescTxt_A1')">Status</a><img id="sortIMG.cltTaskStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs" id="A3H9"><a href="#" class="pSortCol" onclick="columnSort('A', 'cltTaskPrtyDescTxt_A1')">Priority</a><img id="sortIMG.cltTaskPrtyDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs" id="A3H10"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNum_A1')">Account#</a><img id="sortIMG.dsAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs" id="A3H11"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A1')">Account Name</a><img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs" id="A3H12"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrItem130Txt_A3')">Created By</a><img id="sortIMG.xxScrItem130Txt_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs" id="A3H13"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxTotAmt_A1')"> $Amount Overdue</a><img id="sortIMG.xxTotAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													</tr>
													</table>
													</div>
   												<div id="rightTbl" style="width:1118px; height:380px; display:block; overflow:scroll; margin:0px; padding:0px;" >
      											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1200px">
															<col width="70">		<!-- Item Type -->
															<col width="80">		<!-- Work Item# -->
															<col width="80">		<!-- Date -->
															<col width="120">		<!-- Strategy Name -->
															<col width="150">		<!-- Item Name -->
															<col width="150">		<!-- Owner -->
															<col width="150">		<!-- Portfolio Name -->
															<col width="150">		<!-- Type -->
															<col width="80">		<!-- Status -->
															<col width="55">		<!-- Priority -->
															<col width="80">		<!-- Account# -->
															<col width="125">		<!-- Account Name -->
															<col width="150">		<!-- Created By -->
															<col width="115" align="right">		<!-- $Amount Overdue -->
															<ezf:row ezfHyo="A">
															<tr id="A#{EZF_ROW_NUMBER}" height="25">
																<td>
																	<ezf:label name="cltItemTpDescTxt_A1" ezfName="cltItemTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:anchor name="MoveWin_Detail" ezfName="MoveWin_Detail" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_Detail" otherAttr=" id=\"cltTaskPk_A1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
																		<ezf:label name="cltTaskPk_A1" ezfName="cltTaskPk_A1" ezfHyo="A" ezfArrayNo="0" />
																	</ezf:anchor>
																</td>
																<td>
																	<ezf:label name="cltTaskRwsdDt_A1" ezfName="cltTaskRwsdDt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:inputText name="cltStrgyNm_A1" ezfName="cltStrgyNm_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/>
																</td>
																<td>
																	<ezf:inputText name="xxScrItem130Txt_A1" ezfName="xxScrItem130Txt_A1" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent;\""/>
																</td>
																<td>
																	<ezf:inputText name="cltPsnNm_A1" ezfName="cltPsnNm_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent;\""/>
																</td>
																<td>
																	<ezf:inputText name="cltPtfoNm_A1" ezfName="cltPtfoNm_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent;\""/>
																</td>
																<td>
																	<ezf:inputText name="cltTaskTpDescTxt_A1" ezfName="cltTaskTpDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none; background-color:transparent;\""/>
																</td>
																<td>
																	<ezf:inputText name="cltTaskStsDescTxt_A1" ezfName="cltTaskStsDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/>
																</td>
																<td>
																	<ezf:inputText name="cltTaskPrtyDescTxt_A1" ezfName="cltTaskPrtyDescTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" style=\"border:none; background-color:transparent;\""/>
																</td>
																<td>
																	<ezf:inputText name="dsAcctNum_A1" ezfName="dsAcctNum_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/>
																</td>
																<td>
																	<ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" style=\"border:none; background-color:transparent;\""/>
																</td>
																<td>
																	<ezf:inputText name="xxScrItem130Txt_A3" ezfName="xxScrItem130Txt_A3" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" style=\"border:none; background-color:transparent;\""/>
																</td>
																<td>
																	<ezf:label name="xxTotAmt_A1" ezfName="xxTotAmt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
															</tr>
														</ezf:row>
												</table>
											</div>
										</div>
									</div>
								</td>
							</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
  S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0,  true, false);
</script>

<%-- **** Task specific area ends here **** --%>
