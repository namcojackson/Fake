<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20221130161624 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- I18N START --%>
<%@ page import="parts.i18n.*" %>

<%	pageContext.setAttribute("ezdi18nlocale", EZDI18NContext.getInstance().getI18NAccessor().getLocale()); %>
<fmt:setLocale value="${ezdi18nlocale}" scope="request" />
<fmt:setBundle basename="I18N_NFDL0090Scrn00" var="I18N_SCREEN_ID" scope="request" />
<fmt:setBundle basename="I18N" var="I18N_DEFAULT" scope="request" />
<%-- I18N END --%>

<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NFDL0090Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NFDL0090Scrn00.title" bundle="${I18N_SCREEN_ID}">Write Off</fmt:message>">

			<style TYPE="text/css">
			<!--
				td.fontMin{font-size:8pt;}
				input.fontMin{font-size:8pt;}
			-->
			</style>

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
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
										<li title="<fmt:message key="i18n.NFDL0090Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Actual Counters for Interface</fmt:message>" class="pTab_ON"><a href="#">Collenction</a></li>
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
				<table height="570" width="100%" border="0">
					<col valign="top">
					<tr>
						<td>
							<table width="100%" cellpadding="0" cellspacing="0">
								<col width="">
								<tr>
									<td>
										<table border="0">
											<col width="70">
											<col width="60">
											<col width="265">
											<col width="20">
											<col width="70">
											<col width="225">
											<col width="20">
											<col width="100">
											<col width="250">
											<col width="72">
											<tr>
												<td class="stab" align="right"><fmt:message key="i18n.NFDL0090Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Account :</fmt:message></td>
												<td class="pOut"><ezf:label name="dsAcctNum_H1" ezfName="dsAcctNum_H1" /></td>
												<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" otherAttr=" size=\"35\" maxlength=\"30\""/></td>
												<td>&nbsp;</td>
												<td class="stab"><fmt:message key="i18n.NFDL0090Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Transaction# :</fmt:message></td>
												<td><ezf:inputText name="xxQueryFltrTxt_H1" ezfName="xxQueryFltrTxt_H1" otherAttr=" size=\"30\" maxlength=\"200\" ezftoupper=\"\""/></td>
												<td>&nbsp;</td>
												<td class="stab"><fmt:message key="i18n.NFDL0090Scrn00.label.3" bundle="${I18N_SCREEN_ID}">PO Number(*) :</fmt:message></td>
												<td><ezf:inputText name="custIssPoNum_H1" ezfName="custIssPoNum_H1" otherAttr=" size=\"30\" maxlength=\"35\""/></td>
												<td><ezf:inputButton name="Click_Search" value="Search" htmlClass="pBtn6" /></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<hr>
<%-- ######################################## DETAIL ######################################## --%>
							<%-- Pagination --%>
<!-- Temp Pagination markup -->
							<table border="0" cellspacing="0" cellpadding="0" height="25">
								<col width="72" align="right">
								<col width="72" align="right">
								<col width="15">
								<col width="868">
								<col width="">
								<tr>
									<td><ezf:inputButton name="Check_All" value="CheckAll" htmlClass="pBtn6" otherAttr=" id=\"Check_All\""/></td>
									<td><ezf:inputButton name="Un_Check_All" value="UncheckAll" htmlClass="pBtn6" otherAttr=" id=\"Un_Check_All\""/></td>
									<td></td>
									<td align="right">
										<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
											<jsp:param name="beanId"         value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"        value="A" />
											<jsp:param name="ShowingFrom"    value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"      value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"      value="xxPageShowOfNum" />
											<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
											<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
											<jsp:param name="ViewMode"       value="FULL" />
										</jsp:include>
									</td>
								</tr>

<!-- Temp Pagenation markup 
								<ezf:skip>
								<table width="1098"	border="0" cellpadding="1" cellspacing="0">
									<col width="72" align="right">
									<col width="72" align="right">
									<col width="15">
									<col width="868">
									<col width="">
									<tr>
										<td><input type="button" class="pBtn6" value="CheckAll" onclick="sendServer(this)" name="Check_All" id="Check_All"></td>
										<td><input type="button" class="pBtn6" value="UncheckAll" onclick="sendServer(this)" name="Un_Check_All" id="Un_Check_All"></td>
										<td></td>
										<td align="left">
											<table border="0" cellpadding="1" width="100%">
												<tr>
													<td align="left">
														<table border="0" cellpadding="1" align="left" cellspacing="0">
															<col>
															<tr>
																<td>Result 9999 - 9999 of	9999</td>
															</tr>
														 </table>
													</td>
													<td	align="right">
														<table border="0" cellpadding="1" cellspacing="0">
															<col width="54"	 align="center">
															<col width="40"	 align="right">
															<col width="16"	 align="center">
															<col width="40"	 align="right">
															<col width="16"	 align="center">
															<col width="10">
															<col>
															<col width="20">
															<col>	 
															<col width="1">
															<col>
															<tr>
																<td	class="stab">Showing</td>
																<td><input type="text" name="key_ShowingCurrent" ezfName="key_ShowingCurrent" value="99999"	size="4" maxlength="4"/></td>
																<td	class="stab">/</td>
																<td><input type="text" name="key_ShowingTotal" ezfName="key_ShowingTotal" size="4" maxlength="4" value="99999" class="pPro"	readOnly></td>
																<td	class="stab">page</td>
																<td>&nbsp;</td>
																<td><input type="button" class="pBtn3" value="Jump"	name="PageJump"	onclick="tablePagenation(this.name,	'key_TableNm')"	></td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Prev"	name="PagePrev"	onclick="tablePagenation(this.name,	'key_TableNm')"	></td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Next"	name="PageNext"	onclick="tablePagenation(this.name,	'key_TableNm')"	></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</ezf:skip>
<!-- Temp Pagination markup -->

							</table>
							<table width="100%" border="0">
								<%-- DETAIL-MID --%>
								<tr>
									<td valign="top">
										<table width="100%" cellpadding="0" cellspacing="0" border="0">
											<col width="28">
											<col width="1091">
											<col width="">
											<tr>
												<td valign="top">
													<%-- LEFT-TABLE(TOP) --%>
													<div id="LeftTBL_Top" style="overflow-y:hidden; height:33; overflow-x:hidden; width:28;">
														<table border="1" cellpadding="1" cellspacing="0" width="28">
															<col width="28" align="center">
															<tr height="33">
																<td class="pClothBs fontMin">&nbsp;</td>
															</tr>
														</table>
													</div>
													<%-- LEFT-TABLE(MID) --%>
													<div id="LeftTBL" style="overflow-y:hidden; height:380 ; overflow-x:hidden; width:28;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
														<table border="1" cellpadding="1" cellspacing="0" width="28" id="A1">
															<col width="28"  align="center">
															<ezf:row ezfHyo="A">
																<tr height="27" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></td>
																</tr>
															</ezf:row>
															<ezf:skip>
																<tr height="27" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="27" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
															</ezf:skip>
														</table>
													</div>
												</td>
												<td>
													<%-- RIGHT-TABLE(TOP) --%>
													<div id="RightTBL_Top" style="overflow-y:hidden; height:33; overflow-x:hidden; width:1064" onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ) );">
													<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
													<!-- 	<table border="1" cellpadding="1" cellspacing="0" width="1295" style="table-layout: fixed;"> -->
													<!-- 		<col width="070" align="center"> --><!-- Bill-To-Loc -->
														<table border="1" cellpadding="1" cellspacing="0" width="1425" style="table-layout: fixed;">
															<col width="070" align="center"><!-- Bill-To-Loc -->
													<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
															<col width="105" align="center"><!-- Contract/Order No -->
															<col width="095" align="center"><!-- Invoice No -->
															<col width="090" align="center"><!-- Trx Type -->
															<col width="070" align="center"><!-- Bill No -->
															<col width="065" align="center"><!-- Line Type -->
															<col width="075" align="center"><!-- Bill From -->
															<col width="075" align="center"><!-- Bill To -->
															<col width="100" align="center"><!-- Orig Amt -->
															<col width="100" align="center"><!-- Remain Amt -->
															<col width="100" align="center"><!-- Pending Amt -->
															<col width="125" align="center"><!-- Write Off Amt -->
															<col width="080" align="center"><!-- Invoice Date -->
															<col width="080" align="center"><!-- Due Date -->
															<col width="090" align="center"><!-- Days Past Due -->
															<col width="105" align="center"><!-- PO Number -->
															<tr>
																<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
																<!-- <td class="pClothBs fontMin">Bill-To-Loc</td> -->
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustCd_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Bill To</fmt:message><img id="sortIMG.billToCustCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem30Txt_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Contract/Order#</fmt:message><img id="sortIMG.xxScrItem30Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arCustRefNum_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Transaction#</fmt:message><img id="sortIMG.arCustRefNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxTpNm_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Trx Type</fmt:message><img id="sortIMG.arTrxTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'grpInvNum_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.8" bundle="${I18N_SCREEN_ID}">ConBill#</fmt:message><img id="sortIMG.grpInvNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem20Txt_A2' )"><fmt:message key="i18n.NFDL0090Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Line Type</fmt:message><img id="sortIMG.xxScrItem20Txt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'bllgPerFromDt_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Bill Period From</fmt:message><img id="sortIMG.bllgPerFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'bllgPerToDt_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.11" bundle="${I18N_SCREEN_ID}">Bill Period To</fmt:message><img id="sortIMG.bllgPerToDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealOrigGrsAmt_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.12" bundle="${I18N_SCREEN_ID}">Orig Amt</fmt:message><img id="sortIMG.dealOrigGrsAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealRmngBalGrsAmt_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.13" bundle="${I18N_SCREEN_ID}">Remain Amt</fmt:message><img id="sortIMG.dealRmngBalGrsAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealApplyAdjRsvdAmt_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Pending Amt</fmt:message><img id="sortIMG.dealApplyAdjRsvdAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDealApplyAmtNum_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Write Off Amt</fmt:message><img id="sortIMG.xxDealApplyAmtNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxDt_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Invoice Date</fmt:message><img id="sortIMG.arTrxDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invDueDt_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.17" bundle="${I18N_SCREEN_ID}">Due Date</fmt:message><img id="sortIMG.invDueDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pastDtAot_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Days Past Due</fmt:message><img id="sortIMG.pastDtAot_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'custIssPoNum_A1' )"><fmt:message key="i18n.NFDL0090Scrn00.label.19" bundle="${I18N_SCREEN_ID}">PO Number</fmt:message><img id="sortIMG.custIssPoNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															</tr>
														</table>
													</div>
													<%-- RIGHT-TABLE(MID) --%>
													<div id="RightTBL" style="overflow-y:scroll; height:397; overflow-x:scroll; width:1081" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ) );">
													<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
													<!-- 	<table border="1" cellpadding="1" cellspacing="0" width="1295" id="A2" style="table-layout: fixed;"> -->
													<!-- 		<col width="070" align="center"> --><!-- Bill-To-Loc -->
														<table border="1" cellpadding="1" cellspacing="0" width="1425" id="A2" style="table-layout: fixed;">
															<col width="070" align="center"><!-- Bill-To-Loc -->
													<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
															<col width="105" align="center"><!-- Contract/Order No -->
															<col width="095" align="center"><!-- Invoice No -->
															<col width="090" align="center"><!-- Doc Type -->
															<col width="070" align="center"><!-- Bill No -->
															<col width="065" align="center"><!-- Line Type -->
															<col width="075" align="center"><!-- Bill From -->
															<col width="075" align="center"><!-- Bill To -->
															<col width="100" align="center"><!-- Orig Amt -->
															<col width="100" align="center"><!-- Remain Amt -->
															<col width="100" align="center"><!-- Pending Amt -->
															<col width="125" align="center"><!-- Write Off Amt -->
															<col width="080" align="center"><!-- Invoice Date -->
															<col width="080" align="center"><!-- Due Date -->
															<col width="090" align="center"><!-- Days Past Due -->
															<col width="105" align="center"><!-- PO Number -->
															<ezf:row ezfHyo="A">
																<tr height="27" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><ezf:label name="billToCustCd_A1" ezfName="billToCustCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="xxScrItem30Txt_A1" ezfName="xxScrItem30Txt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="arCustRefNum_A1" ezfName="arCustRefNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="arTrxTpNm_A1" ezfName="arTrxTpNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="grpInvNum_A1" ezfName="grpInvNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="xxScrItem20Txt_A2" ezfName="xxScrItem20Txt_A2" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="bllgPerFromDt_A1" ezfName="bllgPerFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="bllgPerToDt_A1" ezfName="bllgPerToDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="right"><ezf:label name="dealOrigGrsAmt_A1" ezfName="dealOrigGrsAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="right"><ezf:label name="dealRmngBalGrsAmt_A1" ezfName="dealRmngBalGrsAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="right"><ezf:label name="dealApplyAdjRsvdAmt_A1" ezfName="dealApplyAdjRsvdAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td align="center"><ezf:inputText name="xxDealApplyAmtNum_A1" ezfName="xxDealApplyAmtNum_A1" value="-123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"text-align:right\" id=\"xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}\""/></td>
																	<td class="fontMin" align="center"><ezf:label name="arTrxDt_A1" ezfName="arTrxDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="invDueDt_A1" ezfName="invDueDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="pastDtAot_A1" ezfName="pastDtAot_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="custIssPoNum_A1" ezfName="custIssPoNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																</tr>
															</ezf:row>
															<ezf:skip>
																<tr height="27" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="xxScrItem30Txt_A1" ezfname="xxScrItem30Txt_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxTpNm_A1" ezfname="arTrxTpNm_A1" ezfhyo="A">Invoice</label></td>
																	<td class="fontMin" align="center"><label ezfout name="grpInvNum_A1" ezfname="grpInvNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="xxScrItem20Txt_A2" ezfname="xxScrItem20Txt_A2" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="bllgPerFromDt_A1" ezfname="bllgPerFromDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="bllgPerToDt_A1" ezfname="bllgPerToDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealOrigGrsAmt_A1" ezfname="dealOrigGrsAmt_A1" ezfhyo="A">-3,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealRmngBalGrsAmt_A1" ezfname="dealRmngBalGrsAmt_A1" ezfhyo="A">-3,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealApplyAdjRsvdAmt_A1" ezfname="dealApplyAdjRsvdAmt_A1" ezfhyo="A">-3,456,789.12</label></td>
																	<td align="center"><input type="text" size="15" style="text-align:right" value="-123,456,789.12" name="xxDealApplyAmtNum_A1" ezfname="xxDealApplyAmtNum_A1" ezfhyo="A" id="xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}"></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxDt_A1" ezfname="arTrxDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="invDueDt_A1" ezfname="invDueDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="pastDtAot_A1" ezfname="pastDtAot_A1" ezfhyo="A">9999</label></td>
																	<td class="fontMin" align="center"><label ezfout name="custIssPoNum_A1" ezfname="custIssPoNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																</tr>
															</ezf:skip>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<table border="0">
										<tr>
											<td class="stab" align="right">Write Off Activity : </td>
											<td>
												<ezf:select name="arAdjTpCd_FS" ezfName="arAdjTpCd_FS" ezfCodeName="arAdjTpCd_FC" ezfDispName="arAdjTpDescTxt_FD" otherEvent1=" onchange=\"sendServer('Onchange_PD_WriteOffActivity')\"" otherAttr=" style=\"width: 424px\" id=\"arAdjTpCd_FS\""/>
											</td>
											<!-- Add Start 2019/02/04 H.Umeda QC#30162 -->
											<td class="stab" style="width: 382px" align="right">Write Off Amt Total : </td>
											<td>
												<ezf:inputText name="xxDealApplyAmtNum_H1" ezfName="xxDealApplyAmtNum_H1" value="0.00" otherAttr=" maxlength=\"20\" id=\"xxDealApplyAmtNum_H1\""/>
											</td>
											<td>
												<ezf:inputButton name="WrtOffTotalCalc" value="Calc" htmlClass="pBtn3" />
											</td>
											<!-- Add End   2019/02/04 H.Umeda QC#30162 -->
										</tr>
										<tr>
											<td class="stab" align="right">Explanation Code : </td>
											<td>
												<ezf:select name="arWrtOffNoteCd_FS" ezfName="arWrtOffNoteCd_FS" ezfCodeName="arWrtOffNoteCd_FC" ezfDispName="arWrtOffNoteDescTxt_FD" otherAttr=" style=\"width: 424px\" id=\"arWrtOffNoteCd_FS\""/>
											</td>
										</tr>
										<tr>
											<td class="stab" align="right">Note : </td>
											<td>
												<ezf:inputText name="arWrtOffNoteTxt_FS" ezfName="arWrtOffNoteTxt_FS" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" size=\"60\" id=\"arWrtOffNoteTxt_FS\""/>
											</td>
											<td class="stab" colspan="2">
												<ezf:inputButton name="OpenWin_AttachFile" value="Attachment" htmlClass="pBtn6" />
												&ensp;&ensp;&ensp;&ensp;Charge Account : 
												<ezf:inputText name="xxCmntTxt_FS" ezfName="xxCmntTxt_FS" value="110.210.343210.46543210.50.610.710.80.910" otherAttr=" size=\"41\" id=\"xxCmntTxt_FS\""/>
												<ezf:inputButton name="OpenWin_ChargeAccount" value="A" htmlClass="pBtn0" />
											</td>
										</tr>
									</table>
								</tr>

							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
