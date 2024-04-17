<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160913153719 --%>
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
			<input type="hidden" name="pageID" value="NWWL0030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Notification History">


<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
<%-- #################################################### FROM HEADER ################################################### --%>
				<div class="pTab_BODY_In">
					<div id="TBL" style="width:1117px; height:575px; display:block; overflow-y:scroll; margin-left:0px; padding-right:0px;" >
						<table border="0" cellspacing="0" cellpadding="0" style="text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
							<col width="85">
							<col width="300">
							<col width="200">
							<col width="80">
							<col width="317">
							<col width="060">
							<tr>
								<td class="stab">
									<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_NotifNm" >Name(*)</ezf:anchor>
								</td>
								<td class="stab"><ezf:inputText name="ntfyHdrNm" ezfName="ntfyHdrNm" value="1234567890" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td class="stab">Description(*)</td>
								<td colspan="2" class="stab"><ezf:inputText name="ntfyHdrDescTxt" ezfName="ntfyHdrDescTxt" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"60\" maxlength=\"240\""/></td>
								<td class="stab">Date Range</td>
								<td>
									<ezf:inputText name="effFromDt" ezfName="effFromDt" otherAttr=" size=\"10\" maxlength=\"10\""/>
									<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >&nbsp;-&nbsp;
									<ezf:inputText name="effThruDt" ezfName="effThruDt" otherAttr=" size=\"10\" maxlength=\"10\""/>
									<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" >
								</td>
								<td></td>
							</tr>
							<tr>
								<td class="stab">Business Area</td>
								<td>
									<ezf:select name="ntfyBizAreaTpCd" ezfName="ntfyBizAreaTpCd" ezfBlank="1" ezfCodeName="ntfyBizAreaTpCd_P" ezfDispName="ntfyBizAreaTpDescTxt_P" otherEvent1=" onchange=\"sendServer('OnChange_BizArea')\"" otherAttr=" style=\"width:200px\""/>
								</td>
								<td></td>
								<td class="stab">Job ID</td>
								<td><ezf:inputText name="ntfyRunJobId" ezfName="ntfyRunJobId" value="12345" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
								<td></td>
							</tr>
							<tr>
								<td class="stab">Sub Area</td>
								<td>
									<ezf:select name="ntfySubAreaTpCd" ezfName="ntfySubAreaTpCd" ezfBlank="1" ezfCodeName="ntfySubAreaTpCd_P" ezfDispName="ntfySubAreaTpDescTxt_P" otherAttr=" style=\"width:200px\""/>
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" />
								</td>
								
							</tr>
						</table>
						<br>
	<%-- #################################################### TO HEADER ################################################### --%>

	<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
						<!-- Prev/Next Page-->
						<table width="1100" height="35">
							<tr>
								<td width="3">&nbsp;</td>
								<td width="500" align="left">
								</td>
								<td align="right" style="padding-right:10px;">
									<ezf:skip> 
									<table width="990" border="0" cellpadding="1" cellspacing="0">
										<tr>
											<td align="right">
									<table border="0" cellpadding="0" width="100%">
										<tr>
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
														<td><input type="text" name="xxPageShowCurNum" ezfName="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
														<td class="stab">/</td>
														<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
														<td class="stab">page</td>
														<td>&nbsp;</td>
														<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
											</td>
										</tr>
									</table>
									</ezf:skip>
									<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
										<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
										<jsp:param name="TableNm"     value="A" />
										<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
										<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
										<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
										<jsp:param name="ShowingCurrent"  value="xxPageShowCurNum" />
										<jsp:param name="ShowingTotal"    value="xxPageShowTotNum" />
										<jsp:param name="ViewMode"        value="FULL" />
									</jsp:include>
								</td>
							</tr>
						</table>
	<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

	<%-- #################################################### FROM DETAIL ################################################### --%>
						<div>
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td style="padding-left:3px;">
										<div>
											<div id="TopTBL" style="overflow-y:none; overflow-x:none;">
												<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1085px" style="margin-right:25px" >
													<col align="center" width="040">	<!-- Notif ID -->
													<col align="center" width="153">	<!-- Name -->
													<col align="center" width="190">	<!-- Description -->
													<col align="center" width="070">	<!-- Business Area -->
													<col align="center" width="070">	<!-- Sub Area -->
													<col align="center" width="040">	<!-- Job ID -->
													<col align="center" width="090">	<!-- Run Time -->

													<tr height="30">
														<td class="pClothBs">
															<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyHdrId_A0' )">Notif ID
																<img id="sortIMG.ntfyHdrId_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
															</a>
														</td>
														<td class="pClothBs">
															<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyHdrNm_A0' )">Name
																<img id="sortIMG.ntfyHdrNm_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
															</a>
														</td>
														<td class="pClothBs">
															<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyHdrDescTxt_A0' )">Description
																<img id="sortIMG.ntfyHdrDescTxt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
															</a>
														</td>
														<td class="pClothBs">
															<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyBizAreaTpDescTxt_A0' )">Business Area
																<img id="sortIMG.ntfyBizAreaTpDescTxt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
															</a>
														</td>
														<td class="pClothBs">
															<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfySubAreaTpDescTxt_A0' )">Sub Area
																<img id="sortIMG.ntfySubAreaTpDescTxt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
															</a>
														</td>
														<td class="pClothBs">
															<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyRunJobId_A0' )">Job ID
																<img id="sortIMG.ntfyRunJobId_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
															</a>
														</td>
														<td class="pClothBs">
															<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem19Txt_A0' )">Run Time
																<img id="sortIMG.xxScrItem19Txt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
															</a>
														</td>
													</tr>
												</table>
											</div>
											<div id="RowTBL" style="width:1102px; height:122px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
												<table border="1" cellpadding="1" cellspacing="0" id="A" width="1085" style="table-layout:fixed;">
													<col align="center" width="040">	<!-- Notif ID -->
													<col align="center" width="153">	<!-- Name -->
													<col align="center" width="190">	<!-- Description -->
													<col align="center" width="070">	<!-- Business Area -->
													<col align="center" width="070">	<!-- Sub Area -->
													<col align="center" width="040">	<!-- Job ID -->
													<col align="center" width="090">	<!-- Run Time -->
													<tbody>
													<ezf:row ezfHyo="A">
														<tr id="id_row{EZF_ROW_NUMBER}" height="24">
															<td align="left">
																<ezf:anchor name="ntfyHdrId_A0" ezfName="ntfyHdrId_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewActRslt" otherAttr=" id=\"ntfyHdrId_A0#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
																	<ezf:label name="ntfyHdrId_A0" ezfName="ntfyHdrId_A0" ezfHyo="A" ezfArrayNo="0" />
																</ezf:anchor>
															</td>
															<td align="center"><ezf:inputText name="ntfyHdrNm_A0" ezfName="ntfyHdrNm_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\" style=\"border:none; background-color:transparent;\""/></td>
															<td align="center"><ezf:inputText name="ntfyHdrDescTxt_A0" ezfName="ntfyHdrDescTxt_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"44\" style=\"border:none; background-color:transparent;\""/></td>
															<td align="center"><ezf:inputText name="ntfyBizAreaTpDescTxt_A0" ezfName="ntfyBizAreaTpDescTxt_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;\""/></td>
															<td align="center"><ezf:inputText name="ntfySubAreaTpDescTxt_A0" ezfName="ntfySubAreaTpDescTxt_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"border:none; background-color:transparent;\""/></td>
															<td><ezf:label name="ntfyRunJobId_A0" ezfName="ntfyRunJobId_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"ntfyRunJobId_A0#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:label name="xxScrItem19Txt_A0" ezfName="xxScrItem19Txt_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem19Txt_A0#{EZF_ROW_NUMBER}\""/></td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr id="id_row{EZF_ROW_NUMBER}" height="24">
															<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyRunJobId_A0" ezfname="ntfyRunJobId_A0" class="pPro" size="10" value="12/23/2016"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyRunTs_A0" ezfname="ntfyRunTs_A0" class="pPro" size="22" value="12/31/2016 12:00:00 AM"></td>
														</tr>
														<tr id="id_row{EZF_ROW_NUMBER}" height="24">
															<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyRunJobId_A0" ezfname="ntfyRunJobId_A0" class="pPro" size="10" value="12/23/2016"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyRunTs_A0" ezfname="ntfyRunTs_A0" class="pPro" size="22" value="12/31/2016 12:00:00 AM"></td>
														</tr>
														<tr id="id_row{EZF_ROW_NUMBER}" height="24">
															<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyRunJobId_A0" ezfname="ntfyRunJobId_A0" class="pPro" size="10" value="12/23/2016"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyRunTs_A0" ezfname="ntfyRunTs_A0" class="pPro" size="22" value="12/31/2016 12:00:00 AM"></td>
														</tr>
														<tr id="id_row{EZF_ROW_NUMBER}" height="24">
															<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyRunJobId_A0" ezfname="ntfyRunJobId_A0" class="pPro" size="10" value="12/23/2016"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyRunTs_A0" ezfname="ntfyRunTs_A0" class="pPro" size="22" value="12/31/2016 12:00:00 AM"></td>
														</tr>
														<tr id="id_row{EZF_ROW_NUMBER}" height="24">
															<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyRunJobId_A0" ezfname="ntfyRunJobId_A0" class="pPro" size="10" value="12/23/2016"></td>
															<td><input type="text" ezfhyo="A"  name="ntfyRunTs_A0" ezfname="ntfyRunTs_A0" class="pPro" size="22" value="12/31/2016 12:00:00 AM"></td>
														</tr>
													</ezf:skip>
													</tbody>
												</table>
											</div>
											<hr>
											<label>Action Result</label>
											<div id="DtlTopTBL" style="overflow-y:none; overflow-x:none;">
												<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="600" style="margin-right:20px" >
													<col align="center" width="050">	<!-- Notif ID -->
													<col align="center" width="050">	<!-- Job ID -->
													<col align="center" width="050">	<!-- Action# -->
													<col align="center" width="200">	<!-- Action Name -->
													<col align="center" width="90">	<!-- Action Dtl Msg ID -->

													<tr id="id_row${EZF_ROW_NUMBER}" height="30">
														<td class="pClothBs">
															<a href="#" class="pSortCol" onclick="columnSort( 'B', 'ntfyHdrId_B0' )">Notif ID
																<img id="sortIMG.ntfyHdrId_B0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
															</a>
														</td>
														<td class="pClothBs">
															<a href="#" class="pSortCol" onclick="columnSort( 'B', 'ntfyRunJobId_B0' )">Job ID
																<img id="sortIMG.ntfyRunJobId_B0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
															</a>
														</td>
														<td class="pClothBs">
															<a href="#" class="pSortCol" onclick="columnSort( 'B', 'ntfyActId_B0' )">Action ID
																<img id="sortIMG.ntfyActId_B0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
															</a>
														</td>
														<td class="pClothBs">
															<a href="#" class="pSortCol" onclick="columnSort( 'B', 'ntfyActNm_B0' )">Action Name
																<img id="sortIMG.ntfyActNm_B0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
															</a>
														</td>
														<td class="pClothBs">
															<a href="#" class="pSortCol" onclick="columnSort( 'B', 'ntfyActDtlHistId_B0' )">Action Dtl Msg ID
																<img id="sortIMG.ntfyActDtlHistId_B0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
															</a>
														</td>
													</tr>
												</table>
											</div>
											<div id="DtlRowTBL" style="width:617px; height:75px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
												<table border="1" cellpadding="1" cellspacing="0" id="B" width="600" style="table-layout:fixed;">
													<col align="center" width="050">	<!-- Notif ID -->
													<col align="center" width="050">	<!-- Job ID -->
													<col align="center" width="050">	<!-- Action# -->
													<col align="center" width="200">	<!-- Action Name -->
													<col align="center" width="90">	<!-- Action Dtl Msg ID -->
													<tbody>
													<ezf:row ezfHyo="B">
														<tr id="id_row{EZF_ROW_NUMBER}" height="24">
															<td align="center"><ezf:inputText name="ntfyHdrId_B0" ezfName="ntfyHdrId_B0" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"border:none; background-color:transparent;\""/></td>
															<td align="center"><ezf:inputText name="ntfyRunJobId_B0" ezfName="ntfyRunJobId_B0" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"border:none; background-color:transparent;\""/></td>
															<td align="center"><ezf:inputText name="ntfyActId_B0" ezfName="ntfyActId_B0" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" style=\"border:none; background-color:transparent;\""/></td>
															<td align="center"><ezf:inputText name="ntfyActNm_B0" ezfName="ntfyActNm_B0" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"37\" style=\"border:none; background-color:transparent;\""/></td>
															<td align="left">
																<ezf:anchor name="ntfyActDtlHistId_B0" ezfName="ntfyActDtlHistId_B0" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ViewActDtl" otherAttr=" id=\"ntfyHdrId_B0#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
																	<ezf:label name="ntfyActDtlHistId_B0" ezfName="ntfyActDtlHistId_B0" ezfHyo="B" ezfArrayNo="0" />
																</ezf:anchor>
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr id="id_row{EZF_ROW_NUMBER}" height="24">
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="00000001"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="99999999"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="99999999"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="Action Name"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="99999999"></td>
														</tr>
														<tr id="id_row{EZF_ROW_NUMBER}" height="24">
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="00000001"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="99999999"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="99999999"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="Action Name"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="99999999"></td>
														</tr>
														<tr id="id_row{EZF_ROW_NUMBER}" height="24">
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="00000001"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="99999999"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="99999999"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="Action Name"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="99999999"></td>
														</tr>
														<tr id="id_row{EZF_ROW_NUMBER}" height="24">
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="00000001"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="99999999"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="99999999"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="Action Name"></td>
															<td><input type="text" ezfhyo="B"  name="xxxxxxxxxxxx" ezfname="xxxxxxxxxxxx" class="pPro" size="10" value="99999999"></td>
														</tr>
													</ezf:skip>
													</tbody>
												</table>
											</div>
											<hr>
											<label>Notification Action Detail</label>
											<div>
												<table style="table-layout:fixed;" border="0" cellspacing="0">
													<col width="90">
													<col width="450">
													<col width="90">
													<col width="450">
													<tr>
														<td class="stab">Action Type</td>
														<td class="stab"><ezf:inputText name="ntfyActTpDescTxt" ezfName="ntfyActTpDescTxt" value="Messsage" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
														<td class="stab">To</td>
														<td class="stab"><ezf:inputText name="ntfyEmlToAddr" ezfName="ntfyEmlToAddr" value="1234567890" otherAttr=" size=\"55\" maxlength=\"2000\""/></td>
													</tr>
													<tr>
														<td class="stab">Output Type</td>
														<td class="stab"><ezf:inputText name="ntfyOtptTpDescTxt" ezfName="ntfyOtptTpDescTxt" value="Email" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
														<td class="stab">Cc</td>
														<td class="stab"><ezf:inputText name="ntfyEmlCcAddr" ezfName="ntfyEmlCcAddr" value="1234567890" otherAttr=" size=\"55\" maxlength=\"2000\""/></td>
													</tr>
													<tr>
														<td class="stab">Reply To</td>
														<td class="stab"><ezf:inputText name="ntfyEmlRpyToAddr" ezfName="ntfyEmlRpyToAddr" value="1234567890" otherAttr=" size=\"55\" maxlength=\"2000\""/></td>
														<td class="stab">Bcc</td>
														<td class="stab"><ezf:inputText name="ntfyEmlBccAddr" ezfName="ntfyEmlBccAddr" value="1234567890" otherAttr=" size=\"55\" maxlength=\"2000\""/></td>
													</tr>
													<tr>
														<td></td>
														<td></td>
														<td class="stab">Distribution List</td>
														<td class="stab"><ezf:inputText name="ntfyDistListNmListTxt" ezfName="ntfyDistListNmListTxt" value="1234567890" otherAttr=" size=\"55\" maxlength=\"2000\""/></td>
													</tr>
													<tr>
														<td class="stab">Subject</td>
														<td class="stab" colspan="3"><ezf:inputText name="ntfyEmlSubjTxt" ezfName="ntfyEmlSubjTxt" value="1234567890" otherAttr=" size=\"132\" maxlength=\"200\""/></td>
													</tr>
													<tr>
														<td class="stab" valign="top">Body</td>
														<td colspan="3">
															<ezf:textArea name="xxNtfyEmlBodyTxt" ezfName="xxNtfyEmlBodyTxt" otherAttr=" rows=\"20\" cols=\"130\""/>
														</td>
													</tr>
													
												</table>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
<%-- #################################################### DETAIL ################################################### --%>
			</td>
		</tr>
	</table>
</center>











<%-- **** Task specific area ends here **** --%>
