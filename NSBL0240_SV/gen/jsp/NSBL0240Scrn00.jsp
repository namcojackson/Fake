<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170412110556 --%>
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
			<input type="hidden" name="pageID" value="NSBL0240Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Travel Charge Maintenance">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%">
									<li title = "Travel Charge Maintenance" class="pTab_ON" ><a href="#">Trvl Chrg</a></li>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
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
												<col width=" 10">
												<col width=" 78">
												<col width=" 10">
												<col width="168">
												<col width=" 10">
												<col width="106">
												<col width=" 10">
												<col width=" 80">
												<col width=" 30">
												<col width=" 72">
												<tr>
													<td>&nbsp;</td>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ModelGroupHeader" >Model Group</ezf:anchor></td>
													<td>&nbsp;</td>
													<td><ezf:inputText name="mdlGrpNm_H" ezfName="mdlGrpNm_H" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
													<td>&nbsp;</td>
													<td class="stab">Line of Business</td>
													<td>&nbsp;</td>
													<td><ezf:select name="svcLineBizCd_H" ezfName="svcLineBizCd_H" ezfBlank="1" ezfCodeName="lineBizTpCd_L" ezfDispName="lineBizTpDescTxt_L" otherAttr=" style=\"width:80px;\""/></td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><hr></td>
						</tr>
						<tr>
							<table width="100%">
								<tr>
									<td>
										<table width="98%">
											<col width="  0">
											<col width=" 72">
											<col width=" 72">
											<col width="20">
											<col width="110">
											<col width="370">
											<col align="right">
											<tr>
												<td><ezf:inputHidden name="xxSfxKeyTxt" ezfName="xxSfxKeyTxt" otherAttr=" id=\"xxSfxKeyTxt\""/></td>
												<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn8" /></td>
												<td><ezf:inputButton name="UnSelectAll" value="Unselect All" htmlClass="pBtn8" /></td>
												<td></td>
												<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TemplateDownload" >Template Download</ezf:anchor></td>
												<td><ezf:inputFile name="xxFileData_U" ezfName="xxFileData_U" otherAttr=" size=\"25\" maxlength=\"9999\""/><ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn6" /></td>
												<td>
													<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
														<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
														<jsp:param name="TableNm"     value="A" />
														<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
														<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
														<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
													</jsp:include>
													<ezf:skip>
													<table border="0" cellpadding="1" cellspacing="0">
														<col width="54"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="10">
														<col>
														<col width="1">
														<col>
														<tr>
															<td class="stab">Showing</td>
															<td class="pOut">1</td>
															<td class="stab">to</td>
															<td class="pOut">50</td>
															<td class="stab">of</td>
															<td class="pOut">100</td>
															<td>&nbsp;</td>
															<td><input type="button" class="pBtn3" value="Prev" name="PagePrev"></td>
															<td></td>
															<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
														</tr>
													</table>
													</ezf:skip>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<%-- ######################################## DETAIL ######################################## --%>
							<table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
								<col align="left" valign="top">
								<tr>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
											<col width="152">
											<tr>
												<td align="left" valign="top">
													<div id="TopLeftTBL" style="overflow-x:none; overflow-y:none; width:746px; float:left;">
														<table border="1" cellpadding="1" cellspacing="0"  style="table-layout: fixed;">
															<col width=" 34" align="center">	<!-- CheckBox -->
															<col width="155" align="center">	<!-- Model Group -->
															<col width="150" align="center">	<!-- Description -->
															<col width=" 72" align="center">	<!-- Line of Business -->
															<col width="185" align="center">	<!-- Default Intangible Item# -->
															<col width="150" align="center">	<!-- Default Intangible Item Description -->
															<tr height="28">
																<td class="pClothBs" rowspan="2">&nbsp;</td>
																<td class="pClothBs" rowspan="2">Model Group</td>
																<td class="pClothBs" rowspan="2">Description</td>
																<td class="pClothBs" rowspan="2">Line of Business</td>
																<td class="pClothBs" rowspan="2">Default Intangible Item#</td>
																<td class="pClothBs" rowspan="2">Default Intangible Item Description</td>
															</tr>
															<tr height="28">
															</tr>
														</table>
													</div>
													<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:367px; width:746px; float:left;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
														<table border="1" cellpadding="1" cellspacing="0" id="A1" style="table-layout: fixed;">
															<col width=" 34" align="center">	<!-- CheckBox -->
															<col width="155" align="left">		<!-- Model Group -->
															<col width="150" align="left">		<!-- Description -->
															<col width=" 72" align="left">		<!-- Line of Business -->
															<col width="185" align="left">		<!-- Default Intangible Item# -->
															<col width="150" align="left">		<!-- Default Intangible Item Description -->
															<ezf:row ezfHyo="A">
															<tr height="28" id="A_leftTBLRow#{EZF_ROW_NUMBER}">
																<td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox#{EZF_ROW_NUMBER}\""/></td>
																<td><ezf:inputButton name="OpenWin_ModelGroupDetail" value="M" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /><ezf:inputText name="mdlGrpNm" ezfName="mdlGrpNm" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="ApplyModelGroup" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
																<td><ezf:inputText name="mdlGrpDescTxt" ezfName="mdlGrpDescTxt" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
																<td><ezf:select name="svcLineBizCd" ezfName="svcLineBizCd" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="lineBizTpCd_L" ezfDispName="lineBizTpDescTxt_L" otherAttr=" style=\"width:70px;\""/></td>
																<td><ezf:inputButton name="OpenWin_IntgItemPopup" value="M" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /><ezf:inputText name="intgMdseCd" ezfName="intgMdseCd" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/><ezf:inputButton name="ApplyIntgItem" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"ApplyIntgItem#{EZF_ROW_NUMBER}\""/></td>
																<td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
															</tr>
															</ezf:row>
														</table>
													</div>
												</td>
												<td align="left" valign="top">
													<div id="topRightTBL" style="overflow-x:hidden; width:352px; overflow-y:none;">
														<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<%-- +++++ [START] Programming JSP for Variable Length Column +++++ --%>
														<%@ page import="business.servlet.NSBL0240.NSBL0240BMsg" %>
														<% NSBL0240BMsg bMsg = (NSBL0240BMsg) databean.getEZDBMsg(); %>
														<% for (int i = 0; i < bMsg.C.getValidCount(); i++ ) { %>
															<col width=" 70" align="center">	<!-- Rate -->
															<col width="105" align="center">	<!-- UOM -->
														<% } %>
															<tr height="28">
														<% for (int i = 0; i < bMsg.C.getValidCount(); i++ ) { %>
																<td colspan="2" class="pClothBs"><%= bMsg.C.no(i).xxScrItem50Txt.getValue() %></td>
														<% } %>
															</tr>
															<tr height="28">
														<% for (int i = 0; i < bMsg.C.getValidCount(); i++ ) { %>
																<td class="pClothBs">Rate</td>
																<td class="pClothBs">UOM</td>
														<% } %>
															</tr>
														<%-- +++++ [END] Programming JSP for Variable Length Column +++++ --%>
														</table>
													</div>
													<div id="RightTBL" style="overflow-x:scroll; width:369px; overflow-y:scroll; height:384px;" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) ); synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
														<table border="1" cellpadding="1" cellspacing="0" id="A2" style="table-layout: fixed;">
														<%-- +++++ [START] Programming JSP for Variable Length Column +++++ --%>
														<% for (int i = 0; i < bMsg.C.getValidCount(); i++ ) { %>
															<col width=" 70" align="left">	<!-- Rate -->
															<col width="105" align="left">	<!-- UOM -->
														<% } %>
														<%-- +++++ [END] Programming JSP for Variable Length Column +++++ --%>
															<ezf:row ezfHyo="A">
															<tr height="28" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
															<%-- +++++ [START] Programming JSP for Variable Length Column +++++ --%>
															<% for (int i = 0; i < bMsg.C.getValidCount(); i++ ) { %>
																<%-- Zone01 - Zone10 --%>
																<% if (i == 0) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_00" ezfName="svcTrvlUnitAmt_00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_00#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_00" ezfName="svcTrvlChrgTpCd_00" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_00#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 1) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_01" ezfName="svcTrvlUnitAmt_01" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_01#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_01" ezfName="svcTrvlChrgTpCd_01" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_01#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 2) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_02" ezfName="svcTrvlUnitAmt_02" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_02#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_02" ezfName="svcTrvlChrgTpCd_02" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_02#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 3) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_03" ezfName="svcTrvlUnitAmt_03" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_03#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_03" ezfName="svcTrvlChrgTpCd_03" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_03#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 4) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_04" ezfName="svcTrvlUnitAmt_04" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_04#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_04" ezfName="svcTrvlChrgTpCd_04" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_04#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 5) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_05" ezfName="svcTrvlUnitAmt_05" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_05#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_05" ezfName="svcTrvlChrgTpCd_05" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_05#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 6) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_06" ezfName="svcTrvlUnitAmt_06" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_06#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_06" ezfName="svcTrvlChrgTpCd_06" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_06#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 7) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_07" ezfName="svcTrvlUnitAmt_07" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_07#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_07" ezfName="svcTrvlChrgTpCd_07" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_07#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 8) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_08" ezfName="svcTrvlUnitAmt_08" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_08#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_08" ezfName="svcTrvlChrgTpCd_08" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_08#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 9) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_09" ezfName="svcTrvlUnitAmt_09" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_09#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_09" ezfName="svcTrvlChrgTpCd_09" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_09#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<%-- Zone11 - Zone20 --%>
																<% if (i == 10) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_10" ezfName="svcTrvlUnitAmt_10" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_10#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_10" ezfName="svcTrvlChrgTpCd_10" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_10#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 11) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_11" ezfName="svcTrvlUnitAmt_11" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_11#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_11" ezfName="svcTrvlChrgTpCd_11" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_11#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 12) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_12" ezfName="svcTrvlUnitAmt_12" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_12#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_12" ezfName="svcTrvlChrgTpCd_12" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_12#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 13) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_13" ezfName="svcTrvlUnitAmt_13" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_13#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_13" ezfName="svcTrvlChrgTpCd_13" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_13#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 14) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_14" ezfName="svcTrvlUnitAmt_14" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_14#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_14" ezfName="svcTrvlChrgTpCd_14" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_14#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 15) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_15" ezfName="svcTrvlUnitAmt_15" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_15#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_15" ezfName="svcTrvlChrgTpCd_15" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_15#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 16) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_16" ezfName="svcTrvlUnitAmt_16" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_16#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_16" ezfName="svcTrvlChrgTpCd_16" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_16#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 17) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_17" ezfName="svcTrvlUnitAmt_17" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_17#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_17" ezfName="svcTrvlChrgTpCd_17" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_17#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 18) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_18" ezfName="svcTrvlUnitAmt_18" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_18#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_18" ezfName="svcTrvlChrgTpCd_18" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_18#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 19) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_19" ezfName="svcTrvlUnitAmt_19" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_19#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_19" ezfName="svcTrvlChrgTpCd_19" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_19#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<%-- Zone21 - Zone30 --%>
																<% if (i == 20) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_20" ezfName="svcTrvlUnitAmt_20" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_20#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_20" ezfName="svcTrvlChrgTpCd_20" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_20#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 21) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_21" ezfName="svcTrvlUnitAmt_21" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_21#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_21" ezfName="svcTrvlChrgTpCd_21" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_21#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 22) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_22" ezfName="svcTrvlUnitAmt_22" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_22#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_22" ezfName="svcTrvlChrgTpCd_22" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_22#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 23) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_23" ezfName="svcTrvlUnitAmt_23" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_23#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_23" ezfName="svcTrvlChrgTpCd_23" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_23#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 24) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_24" ezfName="svcTrvlUnitAmt_24" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_24#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_24" ezfName="svcTrvlChrgTpCd_24" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_24#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 25) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_25" ezfName="svcTrvlUnitAmt_25" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_25#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_25" ezfName="svcTrvlChrgTpCd_25" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_25#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 26) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_26" ezfName="svcTrvlUnitAmt_26" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_26#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_26" ezfName="svcTrvlChrgTpCd_26" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_26#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 27) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_27" ezfName="svcTrvlUnitAmt_27" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_27#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_27" ezfName="svcTrvlChrgTpCd_27" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_27#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 28) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_28" ezfName="svcTrvlUnitAmt_28" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_28#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_28" ezfName="svcTrvlChrgTpCd_28" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_28#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 29) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_29" ezfName="svcTrvlUnitAmt_29" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_29#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_29" ezfName="svcTrvlChrgTpCd_29" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_29#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<%-- Zone31 - Zone40 --%>
																<% if (i == 30) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_30" ezfName="svcTrvlUnitAmt_30" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_30#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_30" ezfName="svcTrvlChrgTpCd_30" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_30#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 31) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_31" ezfName="svcTrvlUnitAmt_31" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_31#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_31" ezfName="svcTrvlChrgTpCd_31" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_31#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 32) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_32" ezfName="svcTrvlUnitAmt_32" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_32#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_32" ezfName="svcTrvlChrgTpCd_32" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_32#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 33) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_33" ezfName="svcTrvlUnitAmt_33" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_33#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_33" ezfName="svcTrvlChrgTpCd_33" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_33#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 34) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_34" ezfName="svcTrvlUnitAmt_34" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_34#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_34" ezfName="svcTrvlChrgTpCd_34" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_34#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 35) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_35" ezfName="svcTrvlUnitAmt_35" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_35#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_35" ezfName="svcTrvlChrgTpCd_35" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_35#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 36) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_36" ezfName="svcTrvlUnitAmt_36" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_36#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_36" ezfName="svcTrvlChrgTpCd_36" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_36#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 37) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_37" ezfName="svcTrvlUnitAmt_37" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_37#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_37" ezfName="svcTrvlChrgTpCd_37" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_37#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 38) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_38" ezfName="svcTrvlUnitAmt_38" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_38#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_38" ezfName="svcTrvlChrgTpCd_38" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_38#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 39) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_39" ezfName="svcTrvlUnitAmt_39" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_39#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_39" ezfName="svcTrvlChrgTpCd_39" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_39#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<%-- Zone41 - Zone50 --%>
																<% if (i == 40) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_40" ezfName="svcTrvlUnitAmt_40" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_40#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_40" ezfName="svcTrvlChrgTpCd_40" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_40#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 41) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_41" ezfName="svcTrvlUnitAmt_41" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_41#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_41" ezfName="svcTrvlChrgTpCd_41" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_41#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 42) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_42" ezfName="svcTrvlUnitAmt_42" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_42#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_42" ezfName="svcTrvlChrgTpCd_42" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_42#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 43) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_43" ezfName="svcTrvlUnitAmt_43" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_43#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_43" ezfName="svcTrvlChrgTpCd_43" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_43#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 44) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_44" ezfName="svcTrvlUnitAmt_44" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_44#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_44" ezfName="svcTrvlChrgTpCd_44" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_44#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 45) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_45" ezfName="svcTrvlUnitAmt_45" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_45#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_45" ezfName="svcTrvlChrgTpCd_45" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_45#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 46) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_46" ezfName="svcTrvlUnitAmt_46" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_46#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_46" ezfName="svcTrvlChrgTpCd_46" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_46#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 47) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_47" ezfName="svcTrvlUnitAmt_47" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_47#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_47" ezfName="svcTrvlChrgTpCd_47" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_47#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 48) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_48" ezfName="svcTrvlUnitAmt_48" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_48#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_48" ezfName="svcTrvlChrgTpCd_48" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_48#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
																<% if (i == 49) { %>
																	<td><ezf:inputText name="svcTrvlUnitAmt_49" ezfName="svcTrvlUnitAmt_49" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcTrvlUnitAmt_49#{EZF_ROW_NUMBER}\" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
																	<td><ezf:select name="svcTrvlChrgTpCd_49" ezfName="svcTrvlChrgTpCd_49" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcTrvlChrgTpCd_L" ezfDispName="svcTrvlChrgTpDescTxt_L" otherAttr=" id=\"svcTrvlChrgTpCd_49#{EZF_ROW_NUMBER}\" style=\"width:100px;\""/></td>
																<% } %>
															<% } %>
															<%-- +++++ [END] Programming JSP for Variable Length Column +++++ --%>
                                                                <td class="pAuditInfo">
                                                                    <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
                                                                    <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
                                                                    <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
                                                                    <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
                                                                    <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
                                                                </td>
															</tr>
															</ezf:row>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table border="0" style="table-layout:fixed;width=98%;">
											<col align="left" width=" 90">
											<col align="left" width=" 90" >
											<col align="right">
											<tr>
												<td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn8" /></td>
												<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn8" /></td>
												<td>&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript">
function setSfxKeyTxt(sfxKeyTxt) {
	document.getElementById ("xxSfxKeyTxt").value = sfxKeyTxt;
	return true;
}
</script>

<%-- **** Task specific area ends here **** --%>
