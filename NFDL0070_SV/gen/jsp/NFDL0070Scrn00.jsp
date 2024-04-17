<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180823083939 --%>
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
			<input type="hidden" name="pageID" value="NFDL0070Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Post Application Search">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Post Application Search" class="pTab_ON"><a href="#">Post Apl</a></li>
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

				<div class="pTab_BODY">
				<table border="0">
					<col width="">
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<col width="90">
								<col width="54">
								<col width="500">
								<col width="30">
								<col width="90">
								<col width="300">
								<col width="40">
								<tr>
									<td class="stab">Account</td>
									<td class="pOut"><ezf:label name="billToCustAcctCd" ezfName="billToCustAcctCd" otherAttr=" id=\"billToCustAcctCd\""/></td>
									<td align="center"><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW60" otherAttr=" size=\"60\""/></td>
									<td>&nbsp;</td>
									<td class="stab">Transaction#(*)</td>
									<td><ezf:inputText name="arCustRefNum" ezfName="arCustRefNum" otherAttr=" size=\"35\" maxlength=\"35\" ezftoupper=\"\""/></td>
									<td><ezf:inputButton name="Click_Search" value="Search" htmlClass="pBtn8" /></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td class="stab">PO Number(*)</td>
									<td><ezf:inputText name="custIssPoNum" ezfName="custIssPoNum" otherAttr=" size=\"35\" maxlength=\"35\""/></td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>
<%-- ######################################## DETAIL ######################################## --%>
<!-- Temp Pagenation markup -->
				<table border="0" cellspacing="0" cellpadding="0" height="25">
					<tr>
							<col width="1090">
							<col width="13">
							<td align="right">
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
							<td>&nbsp;</td>
					</tr>
				</table>

<!-- Temp Pagenation markup 
								<ezf:skip>
								<table width="1098"	border="0" cellpadding="1" cellspacing="0">
									<tr>
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
<!-- Temp Pagenation markup -->

				<table border="0">
					<tr>
						<td valign="top" colspan="2">
							<table width="100%" cellpadding="0" cellspacing="0" border="0">
								<col width="24">
								<col width="1086">
								<col width="">
								<tr>
									<td valign="top">
										<%-- LEFT-TABLE(TOP) --%>
										<div id="LeftTBL_Top" style="overflow-y:hidden; height:18; overflow-x:hidden; width:26;">
											<table border="1" cellpadding="1" cellspacing="0" width="26" style="table-layout: fixed;">
												<col width="24" align="center">
												<tr>
													<td class="pClothBs">&nbsp;<br>&nbsp;</td>
												</tr>
											</table>
										</div>
										<%-- LEFT-TABLE(MID) --%>
										<div id="LeftTBL" style="overflow-y:hidden; height:424; overflow-x:hidden; width:26;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
											<table border="1" cellpadding="1" cellspacing="0" width="24" id="A1" style="table-layout: fixed;">
												<col width="24">
												<ezf:row ezfHyo="A">
													<tr height="28" id="id_row{EZF_ROW_NUMBER}">
														<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn\""/></td>
													</tr>
												</ezf:row>
												<ezf:skip>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><input type="radio"></td>
													</tr>
													<tr height="28">
														<td><input type="radio"></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><input type="radio"></td>
													</tr>
													<tr height="28">
														<td><input type="radio"></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><input type="radio"></td>
													</tr>
													<tr height="28">
														<td><input type="radio"></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><input type="radio"></td>
													</tr>
													<tr height="28">
														<td><input type="radio"></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><input type="radio"></td>
													</tr>
													<tr height="28">
														<td><input type="radio"></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><input type="radio"></td>
													</tr>
													<tr height="28">
														<td><input type="radio"></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><input type="radio"></td>
													</tr>
													<tr height="28">
														<td><input type="radio"></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><input type="radio"></td>
													</tr>
													<tr height="28">
														<td><input type="radio"></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><input type="radio"></td>
													</tr>
													<tr height="28">
														<td><input type="radio"></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><input type="radio"></td>
													</tr>
													<tr height="28">
														<td><input type="radio"></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><input type="radio"></td>
													</tr>
													<tr height="28">
														<td><input type="radio"></td>
													</tr>
												</ezf:skip>
											</table>
										</div>
									</td>
									<td valign="top">
										<%-- RIGHT-TABLE(TOP) --%>
										<div id="RightTBL_Top" style="overflow-y:hidden; height:18; overflow-x:hidden; width:1064" onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ) );">
											<table border="1" cellpadding="1" cellspacing="0" width="1305" style="table-layout: fixed;">
												<col width="80" align="center">
												<col width="130" align="center">
												<col width="90" align="center">
												<col width="85" align="center">
												<col width="80" align="center"><!-- Contract -->
												<col width="80" align="center"><!-- Cons Bill No -->
												<col width="200" align="center">
												<col width="100" align="center"><!-- Bill From -->
												<col width="100" align="center"><!-- Bill To -->
												<col width="120" align="center">
												<col width="120" align="center">
												<col width="120" align="center"><!-- Amt To Apply -->
												<tr>
													<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
													<!-- <td class="pClothBs">Bill-To-Loc</td> -->
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustCd_A1' )">Bill To<img id="sortIMG.billToCustCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arCustRefNum_A1' )">Transaction#<img id="sortIMG.arCustRefNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxTpNm_A1' )">Trx Type<img id="sortIMG.arTrxTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxDt_A1' )">Trx/Bill Date<img id="sortIMG.arTrxDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsContrNum_A1' )">Contract#<img id="sortIMG.dsContrNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'grpInvNum_A1' )">ConBill#<img id="sortIMG.grpInvNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'custIssPoNum_A1' )">PO Number<img id="sortIMG.custIssPoNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'bllgPerFromDt_A1' )">Bill Period From<img id="sortIMG.bllgPerFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'bllgPerToDt_A1' )">Bill Period To<img id="sortIMG.bllgPerToDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealOrigGrsAmt_A1' )">Orig Amt<img id="sortIMG.dealOrigGrsAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealRmngBalGrsAmt_A1' )">Unapplied Amt<img id="sortIMG.dealRmngBalGrsAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealRmngBalGrsAmt_A2' )">Amt To Apply<img id="sortIMG.dealRmngBalGrsAmt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
											</table>
										</div>

										<%-- RIGHT-TABLE(MID) --%>
										<div id="RightTBL" style="overflow-y:scroll; height:440; overflow-x:scroll; width:1081" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ) );">
											<table border="1" cellpadding="1" cellspacing="0" width="1305" id="A2" style="table-layout: fixed; font-size:6;">
												<col width="80" align="left">
												<col width="130">
												<col width="90" align="center">
												<col width="85" align="center">
												<col width="80"><!-- Contract -->
												<col width="80"><!-- Cons Bill No -->
												<col width="200">
												<col width="100" align="center"><!-- Bill From -->
												<col width="100" align="center"><!-- Bill To -->
												<col width="120" align="right">
												<col width="120" align="right">
												<col width="120" align="right"><!-- Amt To Apply -->
												<ezf:row ezfHyo="A">
													<tr height="28" id="id_row{EZF_ROW_NUMBER}">
														<td><ezf:label name="billToCustCd_A1" ezfName="billToCustCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"billToCustCd_A1\""/></td>
														<td><ezf:label name="arCustRefNum_A1" ezfName="arCustRefNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"arCustRefNum_A1\""/></td>
														<td align="center"><ezf:label name="arTrxTpNm_A1" ezfName="arTrxTpNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"arTrxTpNm_A1\""/></td>
														<td><ezf:label name="arTrxDt_A1" ezfName="arTrxDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"arTrxDt_A1\""/></td>
														<td><ezf:label name="dsContrNum_A1" ezfName="dsContrNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsContrNum_A1\""/></td>
														<td><ezf:label name="grpInvNum_A1" ezfName="grpInvNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"grpInvNum_A1\""/></td>
														<td><ezf:label name="custIssPoNum_A1" ezfName="custIssPoNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"custIssPoNum_A1\""/></td>
														<td><ezf:label name="bllgPerFromDt_A1" ezfName="bllgPerFromDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"bllgPerFromDt_A1\""/></td>
														<td><ezf:label name="bllgPerToDt_A1" ezfName="bllgPerToDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"bllgPerToDt_A1\""/></td>
														<td><ezf:label name="dealOrigGrsAmt_A1" ezfName="dealOrigGrsAmt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealOrigGrsAmt_A1\""/></td>
														<td><ezf:label name="dealRmngBalGrsAmt_A1" ezfName="dealRmngBalGrsAmt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealRmngBalGrsAmt_A1\""/></td>
														<td><ezf:label name="dealRmngBalGrsAmt_A2" ezfName="dealRmngBalGrsAmt_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealRmngBalGrsAmt_A2\""/></td>
													</tr>
												</ezf:row>
												<ezf:skip>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
													</tr>
													<tr height="28">
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
														<td><label ezfout>&nbsp;</label></td>
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
						<td colspan="2" align="right">
							<table cellpadding="0" cellspacing="0">
								<col width="72">
								<col width="13">
								<tr>
									<td><ezf:inputButton name="Continue" value="Continue" htmlClass="pBtn6" otherAttr=" id=\"Continue\""/></td>
									<td></td>
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


<%-- **** Task specific area ends here **** --%>
