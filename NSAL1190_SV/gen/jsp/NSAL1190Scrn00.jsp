<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20200618113328 --%>
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
			<input type="hidden" name="pageID" value="NSAL1190Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Counters Setup">
<style type="text/css">
</style>
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Counter Setup" class="pTab_ON"><a href="#">Counter Setup</a></li>
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
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="100%" align="left">
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<col width="120" align="right">
												<col width="10">
												<col width="200">
												<col width="10">
												<col width="200">
												<tr>
													<td class="stab">Counter Name(*)</td>
													<td>&nbsp;</td>
													<td class="stab"><ezf:inputText name="mtrLbNm_H" ezfName="mtrLbNm_H" otherAttr=" size=\"25\" ezftoupper=\"\""/></td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
												</tr>
												<tr>
													<td class="stab">Counter Description(*)</td>
													<td>&nbsp;</td>
													<td class="stab"><ezf:inputText name="mtrLbDescTxt_H" ezfName="mtrLbDescTxt_H" otherAttr=" size=\"25\""/></td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
							<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
								<col width="5">
								<col width="80">
								<col width="80">
								<col width="180">
								<col width="225">
								<col width="550" align="right">
								<tr>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="AddLine" value="Add line" htmlClass="pBtn6" /></td>
									<td><ezf:inputButton name="DelLine" value="Delete line" htmlClass="pBtn6" /></td>
									<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
									<td>&nbsp;</td>
									<td align="right" class="stab">
										<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
											<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"          value="A" />
											<jsp:param name="ShowingFrom"      value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"        value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"        value="xxPageShowOfNum" />
											<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum" />
											<jsp:param name="ShowingTotal"     value="xxPageShowTotNum" />
											<jsp:param name="ViewMode"         value="FULL" />
										</jsp:include>
										<ezf:skip>
											<table border="0" cellpadding="0" width="100%">
												<tr>
													<td align="left">	
														<table border="0" cellpadding="0" align="left" cellspacing="0">
															<col>
															<tr>
																<td>Results 950 - 1000 of 1000</td>
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
																<td>20</td>
																<td class="stab">/</td>
																<td>20</td>
																<td class="stab">page</td>
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
										</ezf:skip>
									</td>
								</tr>
							</table>
<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
						</tr>
<!-- ######################################## DETAIL #################################### -->
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"><!-- left table -->
										<div id='leftTblHead' style="display:block;"></div>
										<div id='leftTbl' style="float:left; display:block; height:437px; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px"></div>
									</div><!-- left table -->
									<div style="float:left;"><!-- right table -->
										<div id='rightTblHead' style="width:1105px; display:block; overflow:hidden; margin:0px; padding:0px;">
											<table style="table-layout:fixed; " width="1195" border="1" cellpadding="1" cellspacing="0" id="AHEAD" style="margin-right:20px">
												<col width="25" align="center"><!-- Select -->
												<col width="120" align="center"><!-- Counter -->
												<col width="80" align="center"><!-- Type -->
												<col width="80" align="center"><!-- Ind -->
												<col width="80" align="center"><!-- Level -->
												<col width="120" align="center"><!-- Description -->
												<col width="50" align="center"><!-- Active -->
												<col width="100" align="center"><!-- Start Date -->
												<col width="100" align="center"><!-- End Date -->
												<col width="50" align="center"><!-- BW -->
												<col width="50" align="center"><!-- Color -->
												<col width="50" align="center"><!-- Total -->
												<col width="50" align="center"><!-- Corp -->
												<col width="120" align="center"><!-- CINC Counter ID -->
												<col width="120" align="center"><!-- Counter Item -->
												<col width="120" align="center"><!-- Invoice Print Name -->
												<tr height="35">
													<td id="AH0" class="pClothBs colFix"></td>
													<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrLbNm_A')" ezfhyo="A">Counter</a><img id="sortIMG.mtrLbNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrLbTpCd_A')" ezfhyo="A">Type</a><img id="sortIMG.mtrLbTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrIdxCd_A')" ezfhyo="A">Ind</a><img id="sortIMG.mtrIdxCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgMtrLvlNum_A')" ezfhyo="A">Level</a><img id="sortIMG.bllgMtrLvlNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrLbDescTxt_A')" ezfhyo="A">Description</a><img id="sortIMG.mtrLbDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'actvFlg_A')" ezfhyo="A">Active</a><img id="sortIMG.actvFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effFromDt_A')" ezfhyo="A">Start Date</a><img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effThruDt_A')" ezfhyo="A">End Date</a><img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bwMtrFlg_A')" ezfhyo="A">BW</a><img id="sortIMG.bwMtrFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'colorMtrFlg_A')" ezfhyo="A">Color</a><img id="sortIMG.colorMtrFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'totMtrFlg_A')" ezfhyo="A">Total</a><img id="sortIMG.totMtrFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'corpAdvtgFlg_A')" ezfhyo="A">Corp</a><img id="sortIMG.corpAdvtgFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrCntrId_A')" ezfhyo="A">CINC Counter ID</a><img id="sortIMG.mtrCntrId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'intgMdseCd_A')" ezfhyo="A">Counter Item</a><img id="sortIMG.intgMdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'invPrintMtrLbDescTxt_A')" ezfhyo="A">Invoice Print Name</a><img id="sortIMG.invPrintMtrLbDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div>
										<div id="rightTbl" style="width:1122px; height:454px; display:block; overflow:scroll; margin:0px; padding: 0px;">
											<table style="table-layout:fixed;" width="1195" border="1" cellpadding="1" cellspacing="0" id="A">
												<col width="25" align="center"><!-- Select -->
												<col width="120" align="center"><!-- Counter -->
												<col width="80" align="center"><!-- Type -->
												<col width="80" align="center"><!-- Ind -->
												<col width="80" align="center"><!-- Level -->
												<col width="120" align="center"><!-- Description -->
												<col width="50" align="center"><!-- Active -->
												<col width="100" align="center"><!-- Start Date -->
												<col width="100" align="center"><!-- End Date -->
												<col width="50" align="center"><!-- BW -->
												<col width="50" align="center"><!-- Color -->
												<col width="50" align="center"><!-- Total -->
												<col width="50" align="center"><!-- Corp -->
												<col width="120" align="center"><!-- CINC Counter ID -->
												<col width="120" align="center"><!-- Counter Item -->
												<col width="120" align="center"><!-- Invoice Print Name -->
												<ezf:row ezfHyo="A">
													<tr height="25" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
														<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="mtrLbNm_A" ezfName="mtrLbNm_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
														<td><ezf:select name="mtrLbTpCd_A" ezfName="mtrLbTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mtrLbTpCd_01" ezfDispName="mtrLbTpDescTxt_01" otherEvent1=" onchange=\"sendServerForPreferredView('OnChangeMtrLbTpCd', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75\" id=\"OnChangeMtrLbTpCd#{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:select name="mtrIdxCd_A" ezfName="mtrIdxCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mtrIdxCd_01" ezfDispName="mtrIdxDescTxt_01" otherEvent1=" onchange=\"sendServerForPreferredView('OnChangeMtrIdxCd', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75\" id=\"OnChangeMtrIdxCd#{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:select name="bllgMtrLvlNum_A" ezfName="bllgMtrLvlNum_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="xxDplyByCtrlItemCd_01" ezfDispName="xxDplyByCtrlItemCdNm_01" otherAttr=" style=\"width:75\""/></td>
														<td><ezf:inputText name="mtrLbDescTxt_A" ezfName="mtrLbDescTxt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
														<td><ezf:inputCheckBox name="actvFlg_A" ezfName="actvFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td>
															<table border="0" cellpadding="1" cellspacing="0">
																<tr style="padding:0;">
																	<td><ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="01/01/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																	<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
																</tr>
															</table>
														</td>
														<td>
															<table border="0" cellpadding="1" cellspacing="0">
																<tr style="padding:0;">
																	<td><ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="12/31/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																	<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
																</tr>
															</table>
														</td>
														<td><ezf:inputCheckBox name="bwMtrFlg_A" ezfName="bwMtrFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputCheckBox name="colorMtrFlg_A" ezfName="colorMtrFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputCheckBox name="totMtrFlg_A" ezfName="totMtrFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputCheckBox name="corpAdvtgFlg_A" ezfName="corpAdvtgFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="mtrCntrId_A" ezfName="mtrCntrId_A" value="XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"3\" ezftoupper=\"\""/></td>
														<td>
															<table border="0" cellpadding="1" cellspacing="0">
																<tr style="padding:0;">
																	<td><ezf:inputText name="intgMdseCd_A" ezfName="intgMdseCd_A" value="XXXXXXXXX1XXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"16\" ezftoupper=\"\""/></td>
																	<td><ezf:inputButton name="OpenWin_ItemMstrPop" value="I" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
																</tr>
															</table>
														</td>
														
														<td><ezf:inputText name="invPrintMtrLbDescTxt_A" ezfName="invPrintMtrLbDescTxt_A" value="XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
														<td class="pAuditInfo">
															<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														</td>
													</tr>
												</ezf:row>
												<ezf:skip>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_A" value="Y" ezfname="xxChkBox_A" ezfhyo="A"></td>
														<td><input type="text" size="15" maxlength="30" value="REG COLOR LARGE" name="mtrLbNm_A" ezftoupper ezfname="mtrLbNm_A" ezfhyo="A"></td>
														<td><select style="width:75" name="mtrLbTpCd_A" ezfname="mtrLbTpCd_A" ezflist="mtrLbTpCd_01,mtrLbTpDescTxt_01,99, ,blank" ezfhyo="A" onChange="sendServer('OnChangeMtrLbTpCd', '{EZF_ROW_NUMBER}')" id="OnChangeMtrLbTpCd#{EZF_ROW_NUMBER}"><option selected>XX</option><option>R</option><option>B</option></select></td>
														<td><select style="width:75" name="mtrIdxCd_A" ezfname="mtrIdxCd_A" ezflist="mtrIdxCd_01,mtrIdxDescTxt_01,99, ,blank" ezfhyo="A" onChange="sendServer('OnChangeMtrIdxCd', '{EZF_ROW_NUMBER}')" id="OnChangeMtrIdxCd#{EZF_ROW_NUMBER}"><option selected>XX</option><option>N</option><option>F</option><option>A</option></select></td>
														<td><select style="width:75" name="bllgMtrLvlNum_A" ezfname="bllgMtrLvlNum_A" ezflist="xxDplyByCtrlItemCd_01,xxDplyByCtrlItemCdNm_01,99, ,blank" ezfhyo="A"><option selected>XX</option><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option></select></td>
														<td><input type="text" size="15" maxlength="50" value="Color Large (Hard)" name="mtrLbDescTxt_A" ezfname="mtrLbDescTxt_A" ezfhyo="A"></td>
														<td><input type="checkbox" name="actvFlg_A" value="Y" ezfname="actvFlg_A" ezfhyo="A"></td>
														<td>
															<table border="0" cellpadding="1" cellspacing="0">
																<tr style="padding:0;">
																	<td><input type="text" size="10" maxlength="10" value="01/01/2016" name="effFromDt_A" ezfname="effFromDt_A" ezfhyo="A"></td>
																	<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
																</tr>
															</table>
														</td>
														<td>
															<table border="0" cellpadding="1" cellspacing="0">
																<tr style="padding:0;">
																	<td><input type="text" size="10" maxlength="10" value="12/31/2016" name="effThruDt_A" ezfname="effThruDt_A" ezfhyo="A"></td>
																	<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
																</tr>
															</table>
														</td>
														<td><input type="checkbox" name="bwMtrFlg_A" value="Y" ezfname="bwMtrFlg_A" ezfhyo="A"></td>
														<td><input type="checkbox" name="colorMtrFlg_A" value="Y" ezfname="colorMtrFlg_A" ezfhyo="A"></td>
														<td><input type="checkbox" name="totMtrFlg_A" value="Y" ezfname="totMtrFlg_A" ezfhyo="A"></td>
														<td><input type="checkbox" name="corpAdvtgFlg_A" value="Y" ezfname="corpAdvtgFlg_A" ezfhyo="A"></td>
														<td><input type="text" size="15" maxlength="3" value="XXX" name="mtrCntrId_A" ezftoupper ezfname="mtrCntrId_A" ezfhyo="A"></td>
														<td>
															<table border="0" cellpadding="1" cellspacing="0">
																<tr style="padding:0;">
																	<td><input type="text" size="10" maxlength="16" value="CLRLARGE" name="intgMdseCd_A" ezftoupper ezfname="intgMdseCd_A" ezfhyo="A"></td>
																	<td><input type="button" class="pBtn0" value="I" name="OpenWin_ItemMstrPop" onclick="sendServer('OpenWin_ItemMstrPop')" ezfhyo="A"></td>
																</tr>
															</table>
														</td>
														<td><input type="text" size="15" maxlength="50" value="XXX" name="invPrintMtrLbDescTxt_A" ezfname="invPrintMtrLbDescTxt_A" ezfhyo="A"></td>
													</tr>
												</ezf:skip>
											</table>
										</div><!-- rightTbl -->
									</div><!-- right table -->
								</div><!-- parent box -->
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
	<script type="text/javascript" defer>
		S21TableUI.initialize("parentBoxA", "AHEAD", "A");
	</script>

<%-- **** Task specific area ends here **** --%>
