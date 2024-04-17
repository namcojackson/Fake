<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180213150329 --%>
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
			<input type="hidden" name="pageID" value="NMAL0160Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Pending Cost Rollups">

			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" otherAttr=" id=\"xxRecHistCratTs\""/>
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" otherAttr=" id=\"xxRecHistCratByNm\""/>
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" otherAttr=" id=\"xxRecHistUpdTs\""/>
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" otherAttr=" id=\"xxRecHistUpdByNm\""/>
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" otherAttr=" id=\"xxRecHistTblNm\""/>

			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="2">
							<%-- ###TAB - HEAD --%>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<!-- include S21BusinessProcessTAB --> 
													<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
												</div>
											</td>
										</tr>
									</table>
								</ul>
							</div>
							<div class="pTab_BODY_In">
								<table border="0" cellpadding="0" cellspacing="0" height="75">
									<tr>
										<td valign="top">
                                        </td>
										<td valign="top" width="420">
											<table>
												<tr>
													<td class="stab">Cost Update Type</td><!-- MDSE_CST_UPD_TP_CD -->
													<td><ezf:select name="mdseCstUpdTpCd_H1" ezfName="mdseCstUpdTpCd_H1" ezfBlank="1" ezfCodeName="mdseCstUpdTpCd_L1" ezfDispName="mdseCstUpdTpNm_L1" /></td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_01" ezfEmulateBtn="1" ezfGuard="CostUpdGrp_Link" otherAttr=" tabindex=\"-1\"">Cost Update Group</ezf:anchor></td><!-- MDSE_CST_UPD_GRP_TXT -->
													<td><ezf:inputText name="mdseCstUpdGrpTxt_H1" ezfName="mdseCstUpdGrpTxt_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
												</tr>
											</table>
										</td>
										<td valign="top" width="640">
											<table >
												<tr>
													<td class="stab"># of Items</td><!-- XX_PAGE_SHOW_TOT_NUM -->
													<td><ezf:inputText name="xxPageShowTotNum_H1" ezfName="xxPageShowTotNum_H1" value="9,999" otherAttr=" size=\"17\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"-1\""/></td>
												</tr>
												<tr>
													<td class="stab"># of OnHand</td><!-- INVTY_QTY -->
													<td><ezf:inputText name="invtyQty_H1" ezfName="invtyQty_H1" value="9,999" otherAttr=" size=\"17\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"-1\""/></td>
												</tr>
												<tr>
													<td class="stab">Estimated Valuation Impact(As Of Yesterday)</td><!-- XX_EST_SC_TOT_COST_AMT -->
													<td><ezf:inputText name="xxEstScTotCostAmt_H1" ezfName="xxEstScTotCostAmt_H1" value="9,999" otherAttr=" size=\"17\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"-1\""/></td>
												</tr>
											</table>
										</td>
										<td valign="top" align="right" width="80" height="75">
											<table border="0" width="100%" height="100%">
												<tr align="right" valign="bottom">
													<td colspan="2" valign="bottom" align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />&nbsp;&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<hr>
								<table border="0" cellpadding="1" cellspacing="0" height="24">
									<col width="1">
									<col width="935">
									<col width="180">
									<tr>
										<td></td>
										<td  align="right">
											<table>
												<tr>
<ezf:skip>
                        <td>&nbsp;</td>
                        <td class="stab">Showing</td>
                        <td class="pOut">1</td>
                        <td class="stab">to</td>
                        <td class="pOut">200</td>
                        <td class="stab">of</td>
                        <td class="pOut">1000</td>
                        <td>&nbsp;</td>
                        <td><input class="pBtn3" disabled onclick="sendServer(this)" type="button" value="Prev" name="PagePrev"></td>
                        <td></td>
                        <td><input class="pBtn3" onclick="sendServer(this)" type="button" value="Next" name="PageNext"></td>
</ezf:skip>
													<td>
														<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
														<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
														<jsp:param name="TableNm"     value="A" />
														<jsp:param name="ShowingFrom" value="xxPageShowFromNum_10" />
														<jsp:param name="ShowingTo"   value="xxPageShowToNum_10" />
														<jsp:param name="ShowingOf"   value="xxPageShowOfNum_10" />
														<jsp:param name="ShowingCurrent" value="xxPageShowCurNum_10" />
														<jsp:param name="ShowingTotal"   value="xxPageShowTotNum_10" />
														<jsp:param name="ViewMode"       value="FULL" />
														</jsp:include>

													</td>
												</tr>
											</table>
										</td>
                                        <td align="right">
											<!--MDSE_CST_UPD_STS_CD-->
											<ezf:select name="mdseCstUpdStsCd_H1" ezfName="mdseCstUpdStsCd_H1" ezfCodeName="mdseCstUpdStsCd_AL" ezfDispName="mdseCstUpdStsNm_AL" otherAttr=" ezftoupper=\"\" style=\"width:90;\""/>&nbsp;<ezf:inputButton name="Apply" value="Apply All" htmlClass="pBtn6" />&nbsp;
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0" >
									<tr>
										<td valign="Top" width="1132">
											<%-- ### MEISAI - LEFT TBL - TOP --%>
											<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none; width:245; height:48; float:left;">
												<table border="1" style="table-layout:fixed;"  cellpadding="1" cellspacing="0" width="245" height="48">
													<col align="center" width="100">
													<col align="center" width="145">
													<tr>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCd_A1' )">Item Number<img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescShortTxt_A1' )">Item Description<img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - Right TBL - TOP --%>
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:865; height:48; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
												<table border="1" style="table-layout:fixed;"  cellpadding="1" cellspacing="0" width="1095" height="48">
													<col align="center" width="115">
													<col align="center" width="155">
													<col align="center" width="50">
													<col align="center" width="50">
													<col align="center" width="95">
													<col align="center" width="95">
													<col align="center" width="70">
													<col align="center" width="135">
													<col align="center" width="110">
													<col align="center" width="110">
													<col align="center" width="110">
													<tr>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCstUpdTpNm_A1' )">Cost Type<img id="sortIMG.mdseCstUpdTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCstUpdGrpTxt_A1' )">Cost Group<img id="sortIMG.mdseCstUpdGrpTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaMdseTpCd_A1' )">Merch Type<img id="sortIMG.coaMdseTpCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaProdCd_A1' )">Prod Code<img id="sortIMG.coaProdCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'thisMthTotStdCostAmt_A1' )">Current Cost<img id="sortIMG.thisMthTotStdCostAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rqstTotStdCostAmt_A1' )">Pending Cost<img id="sortIMG.rqstTotStdCostAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyQty_A1' )">On Hand<img id="sortIMG.invtyQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxEstScTotCostAmt_A1' )">Estimated Valuation<img id="sortIMG.xxEstScTotCostAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCstUpdStsCd_A1' )">Status<img id="sortIMG.mdseCstUpdStsCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCstUpdEffFromDt_A1' )">Effective Date<img id="sortIMG.mdseCstUpdEffFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCstUpdCratDt_A1' )">Submission Date<img id="sortIMG.mdseCstUpdCratDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - LEFT TBL - BOTTOM --%>
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:375; width:245; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL' ))" >
												<table border="1" style="table-layout:fixed;"  cellpadding="1" cellspacing="0" width="245" id="A1">
													<col align="left" width="100">
													<col align="left" width="145">
													<ezf:row ezfHyo="A">
													<tr height="24" id="A_leftTBLRow#{EZF_ROW_NUMBER}">
                                                        <!-- MDSE_CD -->
														<td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
                                                        <!-- MDSE_NM -->
														<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													<tr height="24">
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
													</tr>
													</ezf:skip>
												</table>
											</div>
											<%-- ### MEISAI - RIGHT TBL - BOTTOM --%>
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:392; width:885; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
												<table border="1" style="table-layout:fixed;"  cellpadding="1" cellspacing="0" width="1095" id="A2">
													<col align="left" width="117">
													<col align="left" width="155">
													<col align="center" width="50">
													<col align="center" width="50">
													<col align="right" width="95">
													<col align="right" width="95">
													<col align="right" width="70">
													<col align="right" width="135">
													<col align="center" width="110">
													<col align="left" width="110">
													<col align="left" width="110">
													<ezf:row ezfHyo="A">
													<tr height="24" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
                                                        <!-- MDSE_CST_UPD_TP_NM -->
														<td><ezf:inputText name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
                                                        <!-- MDSE_CST_UPD_GRP_TXT -->
														<td><ezf:inputText name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
                                                        <!-- COA_MDSE_TP_CD -->
														<td><ezf:inputText name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
                                                        <!-- CPA_PROD_CD -->
														<td><ezf:inputText name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
                                                        <!-- THIS_MTH_TOT_STD_COST_AMT -->
														<td><ezf:inputText name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
                                                        <!-- RQST_TOT_STD_COST_AMT -->
														<td><ezf:inputText name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
                                                        <!-- INVTY_QTY -->
														<td><ezf:inputText name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
                                                        <!-- XX_EST_SC_TOT_COST_AMT -->
														<td><ezf:inputText name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<ezf:select name="mdseCstUpdStsCd_A1" ezfName="mdseCstUpdStsCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="mdseCstUpdStsCd_AL" ezfDispName="mdseCstUpdStsNm_AL" otherAttr=" ezftoupper=\"\" style=\"width:100%;\""/>
														</td>
                                                        <!-- MDSE_CST_UPD_EFF_FROM_DT -->
														<td><ezf:inputText name="mdseCstUpdEffFromDt_A1" ezfName="mdseCstUpdEffFromDt_A1" value="mm/dd/yyyy" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
														    <img src="img/calendar.gif" class="pCalendar" onclick="calendar('mdseCstUpdEffFromDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
                                                        <!-- MDSE_CST_UPD_CRAT_DT -->
														<td><ezf:inputText name="mdseCstUpdCratDt_A1" ezfName="mdseCstUpdCratDt_A1" value="mm/dd/yyyy" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td class="pAuditInfo">
															<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
															<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
															<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
															<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
															<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
														</td>
                                                    </tr>
													</ezf:row>
													<ezf:skip>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													<tr height="24">
														<td><input type="text" name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1WWWWW6" ezfHyo="A" ezfArrayNo="0" size="19" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="21" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="thisMthTotStdCostAmt_A1" ezfName="thisMthTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="13" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="invtyQty_A1" ezfName="invtyQty_A1" value="999,999" ezfHyo="A" ezfArrayNo="0" size="8" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxEstScTotCostAmt_A1" ezfName="xxEstScTotCostAmt_A1" value="999,999,999,999.99" ezfHyo="A" ezfArrayNo="0" size="17" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><!--MDSE_CST_UPD_STS_CD-->
															<select name="mdseCstUpdStsCd_A1" ezfname="mdseCstUpdStsCd_A1" ezflist="mdseCstUpdStsCd_AL,mdseCstUpdStsNm_AL,99, ,noblank" ezfhyo="A" ezftoupper style="width:100%;">
															<option>Approved</option>
															<option>Active</option>
															<option>Archived</option>
															<option>Cancelled</option>
															<option selected>Pending</option>
															</select>
														</td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdCratDt_A1" ezfname="mdseCstUpdCratDt_A1" ezfhyo="A" ezftoupper style="border:none;background-color:transparent;padding:0px;"></td>
                                                    </tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<table width="1050" align="center" border="0">
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
