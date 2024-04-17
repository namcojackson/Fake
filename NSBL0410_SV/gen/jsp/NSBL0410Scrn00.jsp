<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190109101924 --%>
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
			<input type="hidden" name="pageID" value="NSBL0410Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mods Detail">

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
													<li title="Mods Detail" class="pTab_ON"><a href="#">Mod Dtl</a></li>
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

							<div class="pTab_BODY_In">
								<%-- ## Hearder ## --%>
								<table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="5">
												<col width="120" valign="top">
												<col width="830" valign="top">
												<col width="" valign="top">
												<tr>
													<td class="stab">Mod Plan ID</td>
													<td><ezf:inputText name="svcModPlnId" ezfName="svcModPlnId" value="1234567890123456" otherAttr=" size=\"18\""/></td>
													<td><ezf:inputButton name="MoveWin_SerialAssinment" value="Serial# Assign" htmlClass="pBtn9" /></td>
												</tr>
												<tr>
													<td class="stab">Description</td>
													<td>
														<ezf:inputText name="svcModNm" ezfName="svcModNm" value="12345678901234567901234567890123456789X" otherAttr=" size=\"42\""/>
														<ezf:inputButton name="EditDescription" value="Edit Description" htmlClass="pBtn9" />
													</td>
													<td><ezf:inputButton name="MoveWin_MachineStatus" value="Machine Status" htmlClass="pBtn9" /></td>
												</tr>
												<tr>
													<td class="stab">Comment</td>
													<td>
														<ezf:inputText name="svcModCmntTxt" ezfName="svcModCmntTxt" value="1234567890123456790123456789012345678901234567901234567890123456789012345679012345678901234567890123456790123456789X" otherAttr=" size=\"42\""/>
													</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">Estimated Labor</td>
													<td class="stab">
														<ezf:inputText name="xxEndDplyTmTxt" ezfName="xxEndDplyTmTxt" value="12:34" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" maxlength=\"5\""/>(HH:MM)
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														Position to row Item Code
														<ezf:inputText name="mdseCd_F" ezfName="mdseCd_F" value="123456789012345X" otherAttr=" size=\"18\" maxlength=\"16\" ezftoupper=\"\""/>
														<ezf:inputButton name="Filter" value="Filter" htmlClass="pBtn6" />
													</td>
													<td>&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>

<%-- #################### DETAIL HEADER #################### --%>
								<hr width="98%" align="center">
								<table border="0" width="96%" align="center">
									<col width="" align="left" valign="">
									<td class="stab">Default Set</td>
								</table>
								<table border="0" width="96%" align="center">
									<col width="110" align="left" valign="center">
									<col width="225" align="left" valign="center">
									<col width="165" align="left" valign="center">
									<col width="150" align="left" valign="center">
									<col width="90" align="left" valign="center">
									<col width="170" align="left" valign="center">
									<col width="170" align="left" valign="center">
									<tr>
										<td class="stab">MU
											<ezf:select name="svcMnfCd" ezfName="svcMnfCd" ezfBlank="1" ezfCodeName="svcMnfCd_01" ezfDispName="xxDplyByCdNmCnctTxt_01" otherAttr=" style=\"width:80;\""/>
										</td>
										<td class="stab">Man Mod#
											<ezf:inputText name="svcMnfModNum" ezfName="svcMnfModNum" value="1234567890123456789X" otherAttr=" size=\"22\""/>
										</td>
										<td class="stab">Doc#
											<ezf:inputText name="svcModDocNum" ezfName="svcModDocNum" value="123456789X12345" otherAttr=" size=\"17\""/>
										</td>
										<td class="stab">Priority
											<ezf:select name="svcModPrtyCd" ezfName="svcModPrtyCd" ezfBlank="1" ezfCodeName="svcModPrtyCd_01" ezfDispName="svcModPrtyDescTxt_01" otherAttr=" style=\"width:100;\""/>
										</td>
										<td class="stab">Report
											<ezf:select name="svcModRptTrkFlg" ezfName="svcModRptTrkFlg" ezfBlank="1" ezfCodeName="xxDplyByCtrlItemCd_01" ezfDispName="xxDplyByCtrlItemCdNm_01" otherAttr=" style=\"width:40;\""/>
										</td>
										<td class="stab">Issue Date
											<ezf:inputText name="svcModIssDt" ezfName="svcModIssDt" value="YYYY/MM/DD" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('svcModIssDt', 4);">
										</td>
										<td class="stab">Start Date
											<ezf:inputText name="svcModStartDt" ezfName="svcModStartDt" value="YYYY/MM/DD" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('svcModStartDt', 4);">
										</td>
								</table>
								<table border="0" width="96%" align="center">
									<col width="" align="right" valign="">
									<td>
										<ezf:inputButton name="Apply" value="Apply" htmlClass="pBtn6" otherAttr=" id=\"btnApply\""/>
									</td>
								</table>

<%-- #################### DETAIL #################### --%>
								<%-- ## HEAD ## --%>

								<hr style="height: 0px;" cellpadding="0" width="98%">

								<table border="0" style="table-layout:fixed;width=1082px;margin-left:20;">
									<col width="180">
									<col width="557">
									<col width="">
									<tr>
										<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
										<td></td>
										<!-- Pagination & Navigation--START-->
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"           value="A" />
												<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
											</jsp:include>
											<ezf:skip>
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="right" class="pPro">
												<col width="16"  align="center">
												<col width="40"  align="right" class="pPro">
												<col width="16"  align="center">
												<col width="40"  align="right" class="pPro">
												<col width="10">
												<col>
												<col width="1">
												<col>
												<tr>
													<td class="stab">Showing</td>
													<td class="pOut">1</td>
													<td class="stab">to</td>
													<td class="pOut">3</td>
													<td class="stab">of</td>
													<td class="pOut">1000</td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')" disabled></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')" ></td>
												</tr>
											</table>
											</ezf:skip>
										</td>
										<!-- Pagination & Navigation--END-->
									</tr>
								</table>
								<table border="0" style="table-layout:fixed;width=1082px;margin-left:20;">
									<col width="200">
									<col width="20">
									<col width="100">
									<col width="120">
									<col width="35">
									<col width="220">
									<col width="20">
									<col width="60">
									<col width="120">
									<col width="60">
									<tr>
										<td class="stab">Item Type
											<ezf:select name="mdseItemTpCd" ezfName="mdseItemTpCd" ezfBlank="1" ezfCodeName="mdseItemTpCd_01" ezfDispName="mdseItemTpDescTxt_01" otherAttr=" style=\"width:140;\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="mktMdlNm" ezfName="mktMdlNm" ezfEmulateBtn="1" ezfGuard="OpenWin_MarketingModel" >Marketing Model</ezf:anchor></td>
										<td><ezf:inputText name="mktMdlCd" ezfName="mktMdlCd" value="WWWWWWWWW1WWWWW6" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
										<td><ezf:inputButton name="SearchMarketingModel" value=">>" htmlClass="pBtn0" /></td>
										<td class="pOut"><ezf:label name="mktMdlDescTxt" ezfName="mktMdlDescTxt" /></td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="MdseCodePopup" >Item Code</ezf:anchor></td>
										<td>
											<ezf:inputText name="mdseCd" ezfName="mdseCd" value="0000006" otherAttr=" id=\"mdseCd\" size=\"16\" ezftoupper=\"\""/>
										</td>
										<td >
											<ezf:inputButton name="Add" value="Add" htmlClass="pBtn3" otherAttr=" id=\"btnAdd\""/>
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>
								<table>
									<tr>
										<td>
											<div id="parentBoxA">
												<div style="float:left; display:block"> <!-- left table -->
													<div id='leftTblHead' style="display:block;">
													</div>
													<div id='leftTbl' style="float:left; display:block; height:220px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
													</div>
												</div>  <!-- left table -->
												<div style="float:left"> <!-- right table -->
													<div id='rightTblHead' style="width:1090px; display:block; overflow:hidden; margin:0px;padding:0px;">
														<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1745px" style="margin-right:20px" >
															<col width="30" align="center">	<!-- CheckBox -->
															<col width="90" align="center">	<!-- MU -->
															<col width="170" align="center">	<!-- Manufacture Mod# -->
															<col width="135" align="center">	<!-- Document# -->
															<col width="170" align="center">	<!-- Kit/Parts Num -->
															<col width="150" align="center">	<!-- Mdse Code -->
															<col width="135" align="center">	<!-- Priority -->
															<col width="70" align="center">	<!-- Report Tracking -->
															<col width="125" align="center">	<!-- Issue Date -->
															<col width="125" align="center">	<!-- Start Date -->
															<col width="125" align="center">	<!-- On-Hold Date -->
															<col width="125" align="center">	<!-- Cancel Date -->
															<col width="125" align="center">	<!-- Obsoleted Date -->
															<col width="170" align="center">	<!-- Obsoleted By Mod# -->
															<tr height="34px">
																<td id="AH0" class="pClothBs colFix">&nbsp;</td>
																<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMnfCd_A')">MU</a><img id="sortIMG.svcMnfCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMnfModNum_A')">Manufacture Mod#</a><img id="sortIMG.svcMnfModNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcModDocNum_A')">Document#</a><img id="sortIMG.svcModDocNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxMdseCdAncr_A')">Kit/Parts Num</a><img id="sortIMG.xxMdseCdAncr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A')">Item Code</a><img id="sortIMG.mdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcModPrtyDescTxt_A')">Priority</a><img id="sortIMG.svcModPrtyDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcModRptTrkFlg_A')">Report Tracking</a><img id="sortIMG.svcModRptTrkFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcModIssDt_A')">Issue Date</a><img id="sortIMG.svcModIssDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'startDt_A')">Start Date</a><img id="sortIMG.startDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcModStartDt_A')">On-Hold Date</a><img id="sortIMG.svcModStartDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcModOnHldDt_A')">Cancel Date</a><img id="sortIMG.svcModOnHldDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcModObslDt_A')">Obsoleted Date</a><img id="sortIMG.svcModObslDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcModObslNum_A')">Obsoleted By Mod#</a><img id="sortIMG.svcModObslNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														</table>
													</div><!-- 'rightTblHead' -->
													<div id="rightTbl" style="width:1107px; height:225px; display:block; overflow:scroll; margin:0px; padding:0px;" >
														<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1745px" >
															<col width="30" align="center">	<!-- CheckBox -->
															<col width="90" align="center">	<!-- MU -->
															<col width="170" align="center">	<!-- Manufacture Mod# -->
															<col width="135" align="center">	<!-- Document# -->
															<col width="170" align="center">	<!-- Kit/Parts Num -->
															<col width="150" align="center">	<!-- Mdse Code -->
															<col width="135" align="center">	<!-- Priority -->
															<col width="70" align="center">	<!-- Report Tracking -->
															<col width="125" align="center">	<!-- Issue Date -->
															<col width="125" align="center">	<!-- Start Date -->
															<col width="125" align="center">	<!-- On-Hold Date -->
															<col width="125" align="center">	<!-- Cancel Date -->
															<col width="125" align="center">	<!-- Obsoleted Date -->
															<col width="170" align="center">	<!-- Obsoleted By Mod# -->
															<ezf:row ezfHyo="A">
															<tr height="23px" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
																<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
																<td align="left"><ezf:select name="svcMnfCd_A" ezfName="svcMnfCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcMnfCd_01" ezfDispName="xxDplyByCdNmCnctTxt_01" otherAttr=" style=\"width:85;\""/></td>
																<td align="center"><ezf:inputText name="svcMnfModNum_A" ezfName="svcMnfModNum_A" value="1234567890123456789X" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\""/></td>
																<td align="center"><ezf:inputText name="svcModDocNum_A" ezfName="svcModDocNum_A" value="123456789X12345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\""/></td>
																<td align="left">
																	<span style="width:100px">
																		<ezf:label name="xxMdseCdAncr_A" ezfName="xxMdseCdAncr_A" ezfHyo="A" ezfArrayNo="0" /></span>
																	<span style="width:20px" align="left">
																		<ezf:label name="xxDplyByCtrlItemCd_A" ezfName="xxDplyByCtrlItemCd_A" ezfHyo="A" ezfArrayNo="0" /></span>
																	<span style="width:30px" align="right">
																	<ezf:inputButton name="MoveWin_PartsEntry" value="P" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" align=\"right\""/></td></span>
																<td align="left"><ezf:label name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
																<td align="left"><ezf:select name="svcModPrtyDescTxt_A" ezfName="svcModPrtyDescTxt_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcModPrtyCd_01" ezfDispName="svcModPrtyDescTxt_01" otherAttr=" style=\"width:125;\""/></td>
																<td align="left"><ezf:select name="svcModRptTrkFlg_A" ezfName="svcModRptTrkFlg_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="xxDplyByCtrlItemCd_01" ezfDispName="xxDplyByCtrlItemCdNm_01" otherAttr=" style=\"width:60;\""/></td>
																<td align="center"><ezf:inputText name="svcModIssDt_A" ezfName="svcModIssDt_A" value="01/22/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('svcModIssDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
																<td align="center"><ezf:inputText name="svcModStartDt_A" ezfName="svcModStartDt_A" value="01/22/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('svcModStartDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
																<td align="center"><ezf:inputText name="svcModOnHldDt_A" ezfName="svcModOnHldDt_A" value="01/22/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('svcModOnHldDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
																<td align="center"><ezf:inputText name="svcModCancDt_A" ezfName="svcModCancDt_A" value="01/22/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('svcModCancDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
																<td align="center"><ezf:inputText name="svcModObslDt_A" ezfName="svcModObslDt_A" value="01/22/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('svcModObslDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
																<td align="center"><ezf:inputText name="svcModObslNum_A" ezfName="svcModObslNum_A" value="1234567890123456789X" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\""/></td>
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
															</ezf:skip>
														</table>
													</div><!-- rightTbl -->
												</div><!-- right table -->
											</div><!-- parent box -->
										</td>
									</tr>
								</table>
								<table  border="0" style="table-layout:fixed;width=96%;">
									<tr>
										<td>
											<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn8" />
											<ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn8" />
											<ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn8" />
										</td>
									</tr>
								</table>

							</div><!--pTab_BODY_In-->
						</td>
					</tr>
				</table>
			</center>

<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, false, false);
</script>

<script type="text/javascript">
	function onFocusEvent(item){
		if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
		}
		item.select();
	}

	function onBlurEvent(item){
		var items = item.value.split(':',2);
		if(items.length === 1){
			if(item.value.length === 1){
				item.value = '0' + item.value.charAt(0) + ':00';
			}else if(item.value.length === 2){
				item.value = item.value.charAt(0) + item.value.charAt(1) + ':00';
			}else if(item.value.length === 3){
				item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + '0';
			}else if(item.value.length === 4){
				item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
			}else if(item.value.length === 5){
				item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
			}
		}else if(items.length === 2){
			var hh = '00';
			if(items[0].length === 1){
				hh = '0' + items[0];
			}else if(items[0].length > 1){
				hh = items[0].substring(0,2);
			}
			var mm = '00';
			if(items[1].length === 1){
				mm = items[1] + '0';
			}else if(items[1].length > 1){
				mm = items[1].substring(0,2);
			}
			item.value = hh + ':' + mm;
		}
	}
</script>


<%-- **** Task specific area ends here **** --%>
