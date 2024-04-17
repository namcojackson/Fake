<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220603103540 --%>
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
			<input type="hidden" name="pageID" value="NMAL0150Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Item Cost Update">

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
								<table border="0" cellpadding="0" cellspacing="0" height="120">
									<tr>
										<td valign="top">
                                        </td>
										<td valign="top" width="420">
											<table>
												<tr>
													<td class="stab">Cost Type</td>
													<td><ezf:select name="mdseCstUpdTpCd_H1" ezfName="mdseCstUpdTpCd_H1" ezfBlank="1" ezfCodeName="mdseCstUpdTpCd_L1" ezfDispName="mdseCstUpdTpNm_L1" otherAttr=" style=\"width:150\""/></td>
												</tr>
												<tr>
													<td class="stab">Cost Update Group</td>
													<td><ezf:inputText name="mdseCstUpdGrpTxt_H1" ezfName="mdseCstUpdGrpTxt_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab">Cost Update Description</td>
										            <td><ezf:textArea name="mdseCstUpdDescTxt_H1" ezfName="mdseCstUpdDescTxt_H1" otherAttr=" size=\"30\" maxlength=\"90\" rows=\"5\" cols=\"28\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab">Reference(*)</td>
													<td><ezf:inputText name="mdseCstUpdRefTxt_H1" ezfName="mdseCstUpdRefTxt_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\" tabindex=\"-1\""/></td>
												</tr>
												<tr>
													<td class="stab">Reference Id(*)</td>
													<td><ezf:inputText name="mdseCstUpdRefId_H1" ezfName="mdseCstUpdRefId_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\" tabindex=\"-1\""/></td>
												</tr>
											</table>
										</td>
										<td valign="top" width="420">
											<table >
												<tr>
													<td class="stab">Submitted By</td>
													<td><ezf:inputText name="xxFullNm_H1" ezfName="xxFullNm_H1" value="XXXXXXXX" otherAttr=" size=\"30\" maxlength=\"100\" tabindex=\"-1\""/></td>
												</tr>
												<tr>
													<td class="stab">Submitted Date</td>
													<td><ezf:inputText name="mdseCstUpdCratDt_H1" ezfName="mdseCstUpdCratDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/></td>
												</tr>
											</table>
										</td>

									</tr>
								</table>
								<hr>
								<table border="0" cellpadding="1" cellspacing="0" height="24">
									<col align="center" width="25">
									<col width="380">
									<col width="710">
									<tr>
										<td><ezf:inputCheckBox name="xxChkBox_AL" ezfName="xxChkBox_AL" value="Y" onClick="sendServer('Click_SelAll_UnSelAll_Checkbox')" /></td>
										<td>Select All/Unselect All</td>
										<td  align="right">
											<table>
												<tr>
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
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0" >
									<tr>
										<td valign="Top" width="1132">
											<%-- ### MEISAI - LEFT TBL - TOP --%>
											<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none; width:1100; height:24; float:left;">
												<table style="table-layout:fixed;"  border="1" cellpadding="1" cellspacing="0" width="1100" height="24">
													<col align="center" width="25">
													<col align="center" width="155">
													<col align="center" width="175">
													<col align="center" width="130">
													<col align="center" width="145">
													<col align="center" width="90">
													<col align="center" width="110">
													<col align="center" width="50">
													<col align="center" width="110">
													<col align="center" width="110">
													<tr>
														<td class="pClothBs">&nbsp;&nbsp;</td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCd_A1' )">Item Number<img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescShortTxt_A1' )">Item Description<img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseItemTpNm_A1' )">Item Type<img id="sortIMG.mdseItemTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem54Txt_A1' )">Merchandise Type<img id="sortIMG.xxScrItem54Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaProdCd_A1' )">Prod Code<img id="sortIMG.coaProdCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rqstTotStdCostAmt_A1' )">Unit Cost<img id="sortIMG.rqstTotStdCostAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pkgUomCd_A1' )">UOM<img id="sortIMG.pkgUomCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCstUpdEffFromDt_A1' )">Effective Date<img id="sortIMG.mdseCstUpdEffFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCstUpdStsNm_A1' )">Status<img id="sortIMG.mdseCstUpdStsNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - LEFT TBL - BOTTOM --%>
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:scroll; height:310; width:1120; float:left;" >
												<table style="table-layout:fixed;"  border="1" cellpadding="1" cellspacing="0" width="1100" id="A">
													<col align="center" width="25">
													<col align="left" width="155">
													<col align="left" width="175">
													<col align="left" width="130">
													<col align="left" width="145">
													<col align="left" width="90">
													<col align="right" width="110">
													<col align="left" width="50">
													<col align="left" width="110">
													<col align="left" width="110">
													<ezf:row ezfHyo="A">
													<tr height="24">
														<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"14\""/>
                                                        <ezf:inputButton name="LineItem" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
														<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxScrItem54Txt_A1" ezfName="xxScrItem54Txt_A1" value="WW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\""/></td>
														<td><ezf:inputText name="pkgUomCd_A1" ezfName="pkgUomCd_A1" value="WWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"4\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseCstUpdEffFromDt_A1" ezfName="mdseCstUpdEffFromDt_A1" value="mm/dd/yyyy" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
															<img src="img/calendar.gif" class="pCalendar" onclick="calendar('mdseCstUpdEffFromDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><ezf:inputText name="mdseCstUpdStsNm_A1" ezfName="mdseCstUpdStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
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
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A"></td>
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14"/>
                                                        <input type="button" class="pBtn1" value="..." name="LineItem" onclick="sendServer(this)" ezfhyo="A"></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="16" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxScrItem54Txt_A1" ezfName="xxScrItem54Txt_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14"/></td>
														<td><input type="text" name="pkgUomCd_A1" ezfName="pkgUomCd_A1" value="WWW" ezfHyo="A" ezfArrayNo="0" size="4" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper>
															<img src="img/calendar.gif" class="pCalendar" onclick="calendar('mdseCstUpdEffFromDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><input type="text" name="mdseCstUpdStsNm_A1" ezfName="mdseCstUpdStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14" style="border:none;background-color:transparent;padding:0px;"/></td>
                                                    </tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A"></td>
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14"/>
                                                        <input type="button" class="pBtn1" value="..." name="LineItem" onclick="sendServer(this)" ezfhyo="A"></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="16" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxScrItem54Txt_A1" ezfName="xxScrItem54Txt_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14"/></td>
														<td><input type="text" name="pkgUomCd_A1" ezfName="pkgUomCd_A1" value="WWW" ezfHyo="A" ezfArrayNo="0" size="4" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper>
															<img src="img/calendar.gif" class="pCalendar" onclick="calendar('mdseCstUpdEffFromDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><input type="text" name="mdseCstUpdStsNm_A1" ezfName="mdseCstUpdStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14" style="border:none;background-color:transparent;padding:0px;"/></td>
                                                    </tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A"></td>
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14"/>
                                                        <input type="button" class="pBtn1" value="..." name="LineItem" onclick="sendServer(this)" ezfhyo="A"></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="16" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxScrItem54Txt_A1" ezfName="xxScrItem54Txt_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14"/></td>
														<td><input type="text" name="pkgUomCd_A1" ezfName="pkgUomCd_A1" value="WWW" ezfHyo="A" ezfArrayNo="0" size="4" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper>
															<img src="img/calendar.gif" class="pCalendar" onclick="calendar('mdseCstUpdEffFromDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><input type="text" name="mdseCstUpdStsNm_A1" ezfName="mdseCstUpdStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14" style="border:none;background-color:transparent;padding:0px;"/></td>
                                                    </tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A"></td>
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14"/>
                                                        <input type="button" class="pBtn1" value="..." name="LineItem" onclick="sendServer(this)" ezfhyo="A"></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="16" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxScrItem54Txt_A1" ezfName="xxScrItem54Txt_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14"/></td>
														<td><input type="text" name="pkgUomCd_A1" ezfName="pkgUomCd_A1" value="WWW" ezfHyo="A" ezfArrayNo="0" size="4" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper>
															<img src="img/calendar.gif" class="pCalendar" onclick="calendar('mdseCstUpdEffFromDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><input type="text" name="mdseCstUpdStsNm_A1" ezfName="mdseCstUpdStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14" style="border:none;background-color:transparent;padding:0px;"/></td>
                                                    </tr>
													<tr height="24">
														<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" value="Y" ezfhyo="A"></td>
														<td><input type="text" name="mdseCd_A1" ezfName="mdseCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14"/>
                                                        <input type="button" class="pBtn1" value="..." name="LineItem" onclick="sendServer(this)" ezfhyo="A"></td>
														<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="22" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="mdseItemTpNm_A1" ezfName="mdseItemTpNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="16" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="xxScrItem54Txt_A1" ezfName="xxScrItem54Txt_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="20" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="coaProdCd_A1" ezfName="coaProdCd_A1" value="WW" ezfHyo="A" ezfArrayNo="0" size="2" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14"/></td>
														<td><input type="text" name="pkgUomCd_A1" ezfName="pkgUomCd_A1" value="WWW" ezfHyo="A" ezfArrayNo="0" size="4" style="border:none;background-color:transparent;padding:0px;"/></td>
														<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCstUpdEffFromDt_A1" ezfname="mdseCstUpdEffFromDt_A1" ezfhyo="A" ezftoupper>
															<img src="img/calendar.gif" class="pCalendar" onclick="calendar('mdseCstUpdEffFromDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
														<td><input type="text" name="mdseCstUpdStsNm_A1" ezfName="mdseCstUpdStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" size="14" style="border:none;background-color:transparent;padding:0px;"/></td>
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
					<tr>
						<td align="left" height="28">
						    &nbsp;<ezf:inputFile name="xxFileData_H1" ezfName="xxFileData_H1" otherAttr=" size=\"60\" maxlength=\"9999\""/>
                            <ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn5" otherAttr=" style=\"width:90px;height:20px\""/>
						</td>
						<td align="right" height="28">
                            <ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" />&nbsp;<ezf:inputButton name="Del" value="Del" htmlClass="pBtn6" />&nbsp;&nbsp;&nbsp;
                        </td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
