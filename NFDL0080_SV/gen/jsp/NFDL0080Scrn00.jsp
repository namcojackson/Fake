<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240124180041 --%>
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
			<input type="hidden" name="pageID" value="NFDL0080Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Post Application">


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
				<table height="570" width="100%" border="0">
					<col valign="top">
					<tr>
						<td>
							<table width="100%" cellpadding="0" cellspacing="0">
								<col width="">
								<tr>
									<td>
										<table border="0">
											<col width="90">
											<col width="60">
											<col width="240">
											<col width="30">
											<col width="120">
											<col width="30">
											<col width="79>
											<col width="">
											<tr>
												<td class="stab" align="right">Account : </td>
												<td class="pOut"><ezf:label name="dsAcctNum_H1" ezfName="dsAcctNum_H1" /></td>
												<td class="pOut"><ezf:label name="dsAcctNm_H1" ezfName="dsAcctNm_H1" /></td>
												<td></td>
												<td class="stab" align="right">Related Customers : </td>
												<td><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" otherAttr=" id=\"xxChkBox_H1\""/></td>
												<td></td>
												<td class="stab" align="right">Bill Number(*) : </td>
												<td><ezf:inputText name="xxTrxNumSrchTxt_H2" ezfName="xxTrxNumSrchTxt_H2" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8WWWWWWWWW9WWWWWWWWW0WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8WWWWWWWWW9WWWWWWWWW0WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8WWWWWWWWW9WWWWWWWWW0WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"35\" maxlength=\"320\" ezftoupper=\"\""/></td>
											</tr>
										</table>
										<table border="0">
											<col width="90">
											<col width="100">
											<col width="238">
											<col width="120">
											<col width="80">
											<col width="10">
											<col width="90">
											<col width="250">
											<col width="10">
											<tr>
												<td class="stab" align="right">Transaction# : </td>
												<td class="pOut"><ezf:label name="arCustRefNum_H1" ezfName="arCustRefNum_H1" /></td>
												<td></td>
												<td class="stab" align="right">Related Account : </td>
												<td><ezf:inputText name="dsAcctNum_H2" ezfName="dsAcctNum_H2" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/></td>
												<td></td>
												<td class="stab" align="right">PO Number(*) :</td>
												<td><ezf:inputText name="custIssPoNum_H2" ezfName="custIssPoNum_H2" otherAttr=" size=\"35\" maxlength=\"35\""/></td>
												<td></td>
												<td><ezf:inputButton name="Refresh" value="Refresh" htmlClass="pBtn6" otherAttr=" id=\"Refresh\""/></td>
											</tr>
										</table>
										<table border="0">
											<col width="90">
											<col width="180">
											<col width="232">
											<col width="">
											<tr>
												<td class="stab" align="right">Amt to Apply : </td>
												<td class="pOut" align="right"><ezf:label name="dealRmngBalGrsAmt_H1" ezfName="dealRmngBalGrsAmt_H1" /></td>
												<td></td>
												<td class="stab" align="right">Invoices : </td>
												<td rowspan="2" align="left">
						 							<ezf:textArea name="xxTrxNumSrchTxt_H1" ezfName="xxTrxNumSrchTxt_H1" otherAttr=" cols=\"63\" rows=\"3\""/>
												</td>
											</tr>
											<tr>
												<td class="stab" align="right">Balance to Apply : </td>
												<td class="pOut" align="right"><ezf:label name="xxTotAmt_H1" ezfName="xxTotAmt_H1" /></td>
												<td></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<hr>
<%-- ######################################## DETAIL ######################################## --%>
							<%-- Pagenation --%>
							<table border="0" cellspacing="0" cellpadding="0" height="30">
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
													<div id="LeftTBL_Top" style="overflow-y:hidden; height:17; overflow-x:hidden; width:28;">
														<table border="1" cellpadding="1" cellspacing="0" width="28">
															<col width="28" align="center">
															<tr>
																<td class="pClothBs fontMin">&nbsp;</td>
															</tr>
														</table>
													</div>
													<%-- LEFT-TABLE(MID) --%>
													<div id="LeftTBL" style="overflow-y:hidden; height:338 ; overflow-x:hidden; width:28;" onScroll="synchroScroll( this.id, new Array( 'RightTBL' ) );">
														<table border="1" cellpadding="1" cellspacing="0" width="28" id="A1">
															<col width="28"  align="center">
															<ezf:row ezfHyo="A">
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('OnChange_ChkBoxA1', '{EZF_ROW_NUMBER}')" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></td>
																</tr>
															</ezf:row>
															<ezf:skip>
																<tr height="28" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="28" class="pEvenNumberBGcolor" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin"><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1#{EZF_ROW_NUMBER}"></td>
																</tr>
															</ezf:skip>
														</table>
													</div>
												</td>
												<td>
													<%-- RIGHT-TABLE(TOP) --%>
													<div id="RightTBL_Top" style="overflow-y:hidden; height:17; overflow-x:hidden; width:1064" onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ) );">
														<table border="1" cellpadding="1" cellspacing="0" width="1715" style="table-layout: fixed;">
															<col width="80" align="center">
															<col width="80" align="center">
															<col width="130" align="center">
															<col width="85" align="center">
															<col width="95" align="center">
															<col width="125" align="center"><!-- Orig Amt -->
															<col width="125" align="center"><!-- Remain Amt -->
															<col width="85" align="center"><!-- Due Date -->
															<col width="110" align="center"><!-- Days Past Due -->
															<col width="130" align="center"><!-- Amt To Apply -->
															<col width="115" align="center">
															<col width="70" align="center">
															<col width="285" align="center">
															<col width="100" align="center">
															<col width="100" align="center">
															<tr>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustAcctCd_A1' )">Account#<img id="sortIMG.billToCustAcctCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<!-- Mod Start 2017/10/06 H.Sugawara QC#19922 -->
																<!-- <td class="pClothBs fontMin">Bill-To-Loc</td> -->
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustCd_A1' )">Bill To<img id="sortIMG.billToCustCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<!-- Mod End   2017/10/06 H.Sugawara QC#19922 -->
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arCustRefNum_A1' )">Invoice#<img id="sortIMG.arCustRefNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxDt_A1' )">Invoice Date<img id="sortIMG.arTrxDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxTpNm_A1' )">Trx Type<img id="sortIMG.arTrxTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealOrigGrsAmt_A1' )">Orig Amt<img id="sortIMG.dealOrigGrsAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealRmngBalGrsAmt_A1' )">Remain Amt<img id="sortIMG.dealRmngBalGrsAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invDueDt_A1' )">Due Date<img id="sortIMG.invDueDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pastDtAot_A1' )">Days Past Due<img id="sortIMG.pastDtAot_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDealApplyAmtNum_A1' )">Amt To Apply<img id="sortIMG.xxDealApplyAmtNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cpoOrdNum_A1' )">Contract/Order#<img id="sortIMG.cpoOrdNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'grpInvNum_A1' )">ConBill#<img id="sortIMG.grpInvNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'custIssPoNum_A1' )">PO Number<img id="sortIMG.custIssPoNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'bllgPerFromDt_A1' )">Bill Period From<img id="sortIMG.bllgPerFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'bllgPerToDt_A1' )">Bill Period To<img id="sortIMG.bllgPerToDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															</tr>
														</table>
													</div>
													<%-- RIGHT-TABLE(MID) --%>
                                                    <div id="RightTBL" style="margin:0px;padding:0px;word-break:break-all; overflow-x:scroll; overflow-y:scroll; height:355; width:1081;" onScroll="synchroScroll(this.id, new Array('LeftTBL'));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ) );">
														<table border="1" cellpadding="1" cellspacing="0" width="1715" id="A2" style="table-layout: fixed;">
															<col width="80">
															<col width="80">
															<col width="130">
															<col width="85">
															<col width="95">
															<col width="125"><!-- Orig Amt -->
															<col width="125"><!-- Remain Amt -->
															<col width="85"><!-- Due Date -->
															<col width="110"><!-- Days Past Due -->
															<col width="130"><!-- Amt To Apply -->
															<col width="115">
															<col width="70">
															<col width="285">
															<col width="100">
															<col width="100">
															<ezf:row ezfHyo="A">
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><ezf:label name="billToCustAcctCd_A1" ezfName="billToCustAcctCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="billToCustCd_A1" ezfName="billToCustCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="arCustRefNum_A1" ezfName="arCustRefNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="arTrxDt_A1" ezfName="arTrxDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="arTrxTpNm_A1" ezfName="arTrxTpNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="right"><ezf:label name="dealOrigGrsAmt_A1" ezfName="dealOrigGrsAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="right"><ezf:label name="dealRmngBalGrsAmt_A1" ezfName="dealRmngBalGrsAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>																	
																	<td class="fontMin" align="center"><ezf:label name="invDueDt_A1" ezfName="invDueDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="pastDtAot_A1" ezfName="pastDtAot_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td align="center"><ezf:inputText name="xxDealApplyAmtNum_A1" ezfName="xxDealApplyAmtNum_A1" value="-123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnBlur_ChangeDealApplyAmtNum" htmlClass="fontMin" otherAttr=" size=\"15\" style=\"text-align:right\" id=\"xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}\" ezffocusout=\"OnBlur_ChangeDealApplyAmtNum\""/></td>
																	<td class="fontMin" align="center"><ezf:label name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="grpInvNum_A1" ezfName="grpInvNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="custIssPoNum_A1" ezfName="custIssPoNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="bllgPerFromDt_A1" ezfName="bllgPerFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td class="fontMin" align="center"><ezf:label name="bllgPerToDt_A1" ezfName="bllgPerToDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																</tr>
															</ezf:row>
															<ezf:skip>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><label ezfout name="billToCustAcctCd_A1" ezfname="billToCustAcctCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxDt_A1" ezfname="arTrxDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxTpNm_A1" ezfname="arTrxTpNm_A1" ezfhyo="A">Invoice</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealOrigGrsAmt_A1" ezfname="dealOrigGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealRmngBalGrsAmt_A1" ezfname="dealRmngBalGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="center"><label ezfout name="invDueDt_A1" ezfname="invDueDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="pastDtAot_A1" ezfname="pastDtAot_A1" ezfhyo="A">9999</label></td>
																	<td align="center"><input type="text" class="pHsu fontMin" size="15" style="text-align:right" value="-123,456,789.12" name="xxDealApplyAmtNum_A1" ezfname="xxDealApplyAmtNum_A1" ezfhyo="A" id="xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}"></td>
																	<td class="fontMin" align="center"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="grpInvNum_A1" ezfname="grpInvNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="custIssPoNum_A1" ezfname="custIssPoNum_A1" ezfhyo="A">12345678901234567890123456789012345</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillFromDt_A1" ezfname="arTrxBillFromDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillThruDt_A1" ezfname="arTrxBillThruDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><label ezfout name="billToCustAcctCd_A1" ezfname="billToCustAcctCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxDt_A1" ezfname="arTrxDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxTpNm_A1" ezfname="arTrxTpNm_A1" ezfhyo="A">Invoice</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealOrigGrsAmt_A1" ezfname="dealOrigGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealRmngBalGrsAmt_A1" ezfname="dealRmngBalGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="center"><label ezfout name="invDueDt_A1" ezfname="invDueDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="pastDtAot_A1" ezfname="pastDtAot_A1" ezfhyo="A">9999</label></td>
																	<td align="center"><input type="text" class="pHsu fontMin" size="15" style="text-align:right" value="-123,456,789.12" name="xxDealApplyAmtNum_A1" ezfname="xxDealApplyAmtNum_A1" ezfhyo="A" id="xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}"></td>
																	<td class="fontMin" align="center"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="grpInvNum_A1" ezfname="grpInvNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="custIssPoNum_A1" ezfname="custIssPoNum_A1" ezfhyo="A">12345678901234567890123456789012345</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillFromDt_A1" ezfname="arTrxBillFromDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillThruDt_A1" ezfname="arTrxBillThruDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><label ezfout name="billToCustAcctCd_A1" ezfname="billToCustAcctCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxDt_A1" ezfname="arTrxDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxTpNm_A1" ezfname="arTrxTpNm_A1" ezfhyo="A">Invoice</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealOrigGrsAmt_A1" ezfname="dealOrigGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealRmngBalGrsAmt_A1" ezfname="dealRmngBalGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="center"><label ezfout name="invDueDt_A1" ezfname="invDueDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="pastDtAot_A1" ezfname="pastDtAot_A1" ezfhyo="A">9999</label></td>
																	<td align="center"><input type="text" class="pHsu fontMin" size="15" style="text-align:right" value="-123,456,789.12" name="xxDealApplyAmtNum_A1" ezfname="xxDealApplyAmtNum_A1" ezfhyo="A" id="xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}"></td>
																	<td class="fontMin" align="center"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="grpInvNum_A1" ezfname="grpInvNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="custIssPoNum_A1" ezfname="custIssPoNum_A1" ezfhyo="A">12345678901234567890123456789012345</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillFromDt_A1" ezfname="arTrxBillFromDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillThruDt_A1" ezfname="arTrxBillThruDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><label ezfout name="billToCustAcctCd_A1" ezfname="billToCustAcctCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxDt_A1" ezfname="arTrxDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxTpNm_A1" ezfname="arTrxTpNm_A1" ezfhyo="A">Invoice</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealOrigGrsAmt_A1" ezfname="dealOrigGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealRmngBalGrsAmt_A1" ezfname="dealRmngBalGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="center"><label ezfout name="invDueDt_A1" ezfname="invDueDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="pastDtAot_A1" ezfname="pastDtAot_A1" ezfhyo="A">9999</label></td>
																	<td align="center"><input type="text" class="pHsu fontMin" size="15" style="text-align:right" value="-123,456,789.12" name="xxDealApplyAmtNum_A1" ezfname="xxDealApplyAmtNum_A1" ezfhyo="A" id="xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}"></td>
																	<td class="fontMin" align="center"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="grpInvNum_A1" ezfname="grpInvNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="custIssPoNum_A1" ezfname="custIssPoNum_A1" ezfhyo="A">12345678901234567890123456789012345</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillFromDt_A1" ezfname="arTrxBillFromDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillThruDt_A1" ezfname="arTrxBillThruDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><label ezfout name="billToCustAcctCd_A1" ezfname="billToCustAcctCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxDt_A1" ezfname="arTrxDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxTpNm_A1" ezfname="arTrxTpNm_A1" ezfhyo="A">Invoice</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealOrigGrsAmt_A1" ezfname="dealOrigGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealRmngBalGrsAmt_A1" ezfname="dealRmngBalGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="center"><label ezfout name="invDueDt_A1" ezfname="invDueDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="pastDtAot_A1" ezfname="pastDtAot_A1" ezfhyo="A">9999</label></td>
																	<td align="center"><input type="text" class="pHsu fontMin" size="15" style="text-align:right" value="-123,456,789.12" name="xxDealApplyAmtNum_A1" ezfname="xxDealApplyAmtNum_A1" ezfhyo="A" id="xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}"></td>
																	<td class="fontMin" align="center"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="grpInvNum_A1" ezfname="grpInvNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="custIssPoNum_A1" ezfname="custIssPoNum_A1" ezfhyo="A">12345678901234567890123456789012345</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillFromDt_A1" ezfname="arTrxBillFromDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillThruDt_A1" ezfname="arTrxBillThruDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><label ezfout name="billToCustAcctCd_A1" ezfname="billToCustAcctCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxDt_A1" ezfname="arTrxDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxTpNm_A1" ezfname="arTrxTpNm_A1" ezfhyo="A">Invoice</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealOrigGrsAmt_A1" ezfname="dealOrigGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealRmngBalGrsAmt_A1" ezfname="dealRmngBalGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="center"><label ezfout name="invDueDt_A1" ezfname="invDueDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="pastDtAot_A1" ezfname="pastDtAot_A1" ezfhyo="A">9999</label></td>
																	<td align="center"><input type="text" class="pHsu fontMin" size="15" style="text-align:right" value="-123,456,789.12" name="xxDealApplyAmtNum_A1" ezfname="xxDealApplyAmtNum_A1" ezfhyo="A" id="xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}"></td>
																	<td class="fontMin" align="center"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="grpInvNum_A1" ezfname="grpInvNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="custIssPoNum_A1" ezfname="custIssPoNum_A1" ezfhyo="A">12345678901234567890123456789012345</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillFromDt_A1" ezfname="arTrxBillFromDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillThruDt_A1" ezfname="arTrxBillThruDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><label ezfout name="billToCustAcctCd_A1" ezfname="billToCustAcctCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxDt_A1" ezfname="arTrxDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxTpNm_A1" ezfname="arTrxTpNm_A1" ezfhyo="A">Invoice</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealOrigGrsAmt_A1" ezfname="dealOrigGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealRmngBalGrsAmt_A1" ezfname="dealRmngBalGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="center"><label ezfout name="invDueDt_A1" ezfname="invDueDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="pastDtAot_A1" ezfname="pastDtAot_A1" ezfhyo="A">9999</label></td>
																	<td align="center"><input type="text" class="pHsu fontMin" size="15" style="text-align:right" value="-123,456,789.12" name="xxDealApplyAmtNum_A1" ezfname="xxDealApplyAmtNum_A1" ezfhyo="A" id="xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}"></td>
																	<td class="fontMin" align="center"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="grpInvNum_A1" ezfname="grpInvNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="custIssPoNum_A1" ezfname="custIssPoNum_A1" ezfhyo="A">12345678901234567890123456789012345</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillFromDt_A1" ezfname="arTrxBillFromDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillThruDt_A1" ezfname="arTrxBillThruDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><label ezfout name="billToCustAcctCd_A1" ezfname="billToCustAcctCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxDt_A1" ezfname="arTrxDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxTpNm_A1" ezfname="arTrxTpNm_A1" ezfhyo="A">Invoice</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealOrigGrsAmt_A1" ezfname="dealOrigGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealRmngBalGrsAmt_A1" ezfname="dealRmngBalGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="center"><label ezfout name="invDueDt_A1" ezfname="invDueDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="pastDtAot_A1" ezfname="pastDtAot_A1" ezfhyo="A">9999</label></td>
																	<td align="center"><input type="text" class="pHsu fontMin" size="15" style="text-align:right" value="-123,456,789.12" name="xxDealApplyAmtNum_A1" ezfname="xxDealApplyAmtNum_A1" ezfhyo="A" id="xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}"></td>
																	<td class="fontMin" align="center"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="grpInvNum_A1" ezfname="grpInvNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="custIssPoNum_A1" ezfname="custIssPoNum_A1" ezfhyo="A">12345678901234567890123456789012345</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillFromDt_A1" ezfname="arTrxBillFromDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillThruDt_A1" ezfname="arTrxBillThruDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><label ezfout name="billToCustAcctCd_A1" ezfname="billToCustAcctCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxDt_A1" ezfname="arTrxDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxTpNm_A1" ezfname="arTrxTpNm_A1" ezfhyo="A">Invoice</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealOrigGrsAmt_A1" ezfname="dealOrigGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealRmngBalGrsAmt_A1" ezfname="dealRmngBalGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="center"><label ezfout name="invDueDt_A1" ezfname="invDueDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="pastDtAot_A1" ezfname="pastDtAot_A1" ezfhyo="A">9999</label></td>
																	<td align="center"><input type="text" class="pHsu fontMin" size="15" style="text-align:right" value="-123,456,789.12" name="xxDealApplyAmtNum_A1" ezfname="xxDealApplyAmtNum_A1" ezfhyo="A" id="xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}"></td>
																	<td class="fontMin" align="center"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="grpInvNum_A1" ezfname="grpInvNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="custIssPoNum_A1" ezfname="custIssPoNum_A1" ezfhyo="A">12345678901234567890123456789012345</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillFromDt_A1" ezfname="arTrxBillFromDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillThruDt_A1" ezfname="arTrxBillThruDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><label ezfout name="billToCustAcctCd_A1" ezfname="billToCustAcctCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxDt_A1" ezfname="arTrxDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxTpNm_A1" ezfname="arTrxTpNm_A1" ezfhyo="A">Invoice</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealOrigGrsAmt_A1" ezfname="dealOrigGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealRmngBalGrsAmt_A1" ezfname="dealRmngBalGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="center"><label ezfout name="invDueDt_A1" ezfname="invDueDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="pastDtAot_A1" ezfname="pastDtAot_A1" ezfhyo="A">9999</label></td>
																	<td align="center"><input type="text" class="pHsu fontMin" size="15" style="text-align:right" value="-123,456,789.12" name="xxDealApplyAmtNum_A1" ezfname="xxDealApplyAmtNum_A1" ezfhyo="A" id="xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}"></td>
																	<td class="fontMin" align="center"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="grpInvNum_A1" ezfname="grpInvNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="custIssPoNum_A1" ezfname="custIssPoNum_A1" ezfhyo="A">12345678901234567890123456789012345</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillFromDt_A1" ezfname="arTrxBillFromDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillThruDt_A1" ezfname="arTrxBillThruDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><label ezfout name="billToCustAcctCd_A1" ezfname="billToCustAcctCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxDt_A1" ezfname="arTrxDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxTpNm_A1" ezfname="arTrxTpNm_A1" ezfhyo="A">Invoice</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealOrigGrsAmt_A1" ezfname="dealOrigGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealRmngBalGrsAmt_A1" ezfname="dealRmngBalGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="center"><label ezfout name="invDueDt_A1" ezfname="invDueDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="pastDtAot_A1" ezfname="pastDtAot_A1" ezfhyo="A">9999</label></td>
																	<td align="center"><input type="text" class="pHsu fontMin" size="15" style="text-align:right" value="-123,456,789.12" name="xxDealApplyAmtNum_A1" ezfname="xxDealApplyAmtNum_A1" ezfhyo="A" id="xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}"></td>
																	<td class="fontMin" align="center"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="grpInvNum_A1" ezfname="grpInvNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="custIssPoNum_A1" ezfname="custIssPoNum_A1" ezfhyo="A">12345678901234567890123456789012345</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillFromDt_A1" ezfname="arTrxBillFromDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillThruDt_A1" ezfname="arTrxBillThruDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																</tr>
																<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																	<td class="fontMin" align="center"><label ezfout name="billToCustAcctCd_A1" ezfname="billToCustAcctCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="billToCustCd_A1" ezfname="billToCustCd_A1" ezfhyo="A">WWWWWW7</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arCustRefNum_A1" ezfname="arCustRefNum_A1" ezfhyo="A">WWWWWWWWW1WWW</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxDt_A1" ezfname="arTrxDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxTpNm_A1" ezfname="arTrxTpNm_A1" ezfhyo="A">Invoice</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealOrigGrsAmt_A1" ezfname="dealOrigGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="right"><label ezfout name="dealRmngBalGrsAmt_A1" ezfname="dealRmngBalGrsAmt_A1" ezfhyo="A">-123,456,789.12</label></td>
																	<td class="fontMin" align="center"><label ezfout name="invDueDt_A1" ezfname="invDueDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="pastDtAot_A1" ezfname="pastDtAot_A1" ezfhyo="A">9999</label></td>
																	<td align="center"><input type="text" class="pHsu fontMin" size="15" style="text-align:right" value="-123,456,789.12" name="xxDealApplyAmtNum_A1" ezfname="xxDealApplyAmtNum_A1" ezfhyo="A" id="xxDealApplyAmtNum_A1#{EZF_ROW_NUMBER}"></td>
																	<td class="fontMin" align="center"><label ezfout name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="grpInvNum_A1" ezfname="grpInvNum_A1" ezfhyo="A">WWWWWWW8</label></td>
																	<td class="fontMin" align="center"><label ezfout name="custIssPoNum_A1" ezfname="custIssPoNum_A1" ezfhyo="A">12345678901234567890123456789012345</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillFromDt_A1" ezfname="arTrxBillFromDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																	<td class="fontMin" align="center"><label ezfout name="arTrxBillThruDt_A1" ezfname="arTrxBillThruDt_A1" ezfhyo="A">MM/DD/YYYY</label></td>
																</tr>
															</ezf:skip>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<div>
								<table border="0" cellspacing="0" cellpadding="0">
									<col width="80">
									<col width="830">
									<tr>
										<td class="stab">Comment</td>
										<td>
											<ezf:textArea name="dtlNoteTxt_H1" ezfName="dtlNoteTxt_H1" otherAttr=" rows=\"2\" cols=\"100\" maxlength=\"4000\""/>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<%-- ###SCRIPT --%>
<!-- **** Scroll **** -->
<ezf:inputHidden name="xxListNum_LY" ezfName="xxListNum_LY" value="0" otherAttr=" id=\"xxListNum_LY\""/>

<script type="text/javascript">
    setScroll();

    /**
     * Save scroll position.
     */
    function synchroScroll(id, ary) {
        if (id == 'RightTBL' || id == 'LeftTBL'){
            document.getElementById('xxListNum_LY').value = document.getElementById(id).scrollTop;
        }
        synchroScrollTop(id, ary);
    }

    /**
     * Set before event scroll position.
     */
    function setScroll() {
        var yVal = document.getElementById('xxListNum_LY').value;

        var leftTbl = document.getElementById('LeftTBL');
        var rightTbl = document.getElementById('RightTBL');

        if (yVal < rightTbl.scrollHeight || yVal < leftTbl.scrollHeight || yVal == rightTbl.scrollHeight) {
            rightTbl.scrollTop = yVal;
            leftTbl.scrollTop = yVal;
        }
    }
</script>
<!-- **** Scroll **** -->


<%-- **** Task specific area ends here **** --%>
