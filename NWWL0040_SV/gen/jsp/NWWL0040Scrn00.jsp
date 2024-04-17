<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160901101024 --%>
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
			<input type="hidden" name="pageID" value="NWWL0040Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Distribution List Search">
			
			
			
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
										<li title="Distribution List Search" class="pTab_ON"><a href="#">Dist List</a></li>
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
<%-- #################################################### FROM HEADER ################################################### --%>
				<div class="pTab_BODY_In">
					<table border="0" cellspacing="0" cellpadding="0" height="120" style="text-align:left;margin-left:3px;">
						<tr>
							<td>
								<table border="0" cellspacing="1" cellpadding="1"  style="text-align:left;margin-left:3px;">
									<col width="100">
									<col width="500">
									<tr>
										<td class="stab">Dist Name(*)</td>
										<td class="stab"><ezf:inputText name="ntfyDistListNm" ezfName="ntfyDistListNm" value="1234567890" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
									</tr>
									<tr>
										<td class="stab">Dist Desc(*)</td>
										<td colspan="2" class="stab"><ezf:inputText name="ntfyDistListDescTxt" ezfName="ntfyDistListDescTxt" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"60\" maxlength=\"240\""/></td>
									</tr>
									<tr>
										<td class="stab">Business Area</td>
										<td>
											<ezf:select name="ntfyBizAreaTpCd_D" ezfName="ntfyBizAreaTpCd_D" ezfBlank="1" ezfCodeName="ntfyBizAreaTpCd_P" ezfDispName="ntfyBizAreaTpDescTxt_P" otherEvent1=" onchange=\"sendServer('OnChange_DistBizArea')\"" otherAttr=" style=\"width:200px\" id=\"ntfyBizAreaTpCd_D\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Sub Area</td>
										<td>
											<ezf:select name="ntfySubAreaTpCd_D" ezfName="ntfySubAreaTpCd_D" ezfBlank="1" ezfCodeName="ntfySubAreaTpCd_DP" ezfDispName="ntfySubAreaTpDescTxt_DP" otherAttr=" style=\"width:200px\" id=\"ntfySubAreaTpCd_SL\""/>
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table border="0" cellspacing="1" cellpadding="1" style="text-align:left;margin-left:3px;">
									<col width="100">
									<col width="500">
									<tr>
										<td class="stab">Notif Name(*)</td>
										<td class="stab"><ezf:inputText name="ntfyHdrNm" ezfName="ntfyHdrNm" value="1234567890" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
									</tr>
									<tr>
										<td class="stab">Notif Desc(*)</td>
										<td colspan="2" class="stab"><ezf:inputText name="ntfyHdrDescTxt" ezfName="ntfyHdrDescTxt" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"60\" maxlength=\"240\""/></td>
									</tr>
									<tr>
										<td class="stab">Business Area</td>
										<td>
											<ezf:select name="ntfyBizAreaTpCd_N" ezfName="ntfyBizAreaTpCd_N" ezfBlank="1" ezfCodeName="ntfyBizAreaTpCd_P" ezfDispName="ntfyBizAreaTpDescTxt_P" otherEvent1=" onchange=\"sendServer('OnChange_NotifBizArea')\"" otherAttr=" style=\"width:200px\" id=\"ntfyBizAreaTpCd_N\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Sub Area</td>
										<td>
											<ezf:select name="ntfySubAreaTpCd_N" ezfName="ntfySubAreaTpCd_N" ezfBlank="1" ezfCodeName="ntfySubAreaTpCd_NP" ezfDispName="ntfySubAreaTpDescTxt_NP" otherAttr=" style=\"width:200px\" id=\"ntfySubAreaTpCd_N\""/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table border="0" cellspacing="0" cellpadding="0"  style="text-align:left;margin-left:4px;">
									<col width="82">
									<col width="110">
									<col width="50">
									<col width="110">
									<col width="100">
									<tr>
										<td class="stab">Start Date</td>
										<td>
											<ezf:inputText name="effFromDt_D" ezfName="effFromDt_D" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_D', 4);" >
										</td>
										<td class="stab">End Date</td>
										<td>
											<ezf:inputText name="effThruDt_D" ezfName="effThruDt_D" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_D', 4);" >
										</td>
										<td class="stab">Enabled<ezf:inputCheckBox name="ntfyDistListActvFlg" ezfName="ntfyDistListActvFlg" value="Y" otherAttr=" id=\"ntfyDistListActvFlg\""/></td>
									</tr>
								</table>
							</td>
							<td>
								<table border="0" cellspacing="0" cellpadding="0"  style="text-align:left;margin-left:4px;">
									<col width="82">
									<col width="110">
									<col width="50">
									<col width="110">
									<col width="100">
									<tr>
										<td class="stab">Start Date</td>
										<td>
											<ezf:inputText name="effFromDt_N" ezfName="effFromDt_N" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_N', 4);" >
										</td>
										<td class="stab">End Date</td>
										<td>
											<ezf:inputText name="effThruDt_N" ezfName="effThruDt_N" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_N', 4);" >
										</td>
										<td class="stab">Enabled<ezf:inputCheckBox name="ntfyHdrActvFlg" ezfName="ntfyHdrActvFlg" value="Y" otherAttr=" id=\"ntfyHdrActvFlg\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<table border="0" cellspacing="0" cellpadding="0"  style="text-align:left;margin-left:4px;">
								<col width="440">
								<col width="50">
									<tr>
										<td></td>
										<td>
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br>
<%-- #################################################### TO HEADER ################################################### --%>

<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<!-- Prev/Next Page-->
					<table width="1120" height="35">
						<tr>
							<td width="3">&nbsp;</td>
							<td width="500" align="left">
								<ezf:inputButton name="MoveWin_Setup" value="Create New" htmlClass="pBtn8" />
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
							<!-- @@@@@ Left -->
							<td style="padding-left:3px;">
								<div>
									<div id="TopTBL" style="overflow-y:none; overflow-x:none;">
										<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1105px" style="margin-right:20px" >
											<col align="center" width="047">	<!-- Dist ID -->
											<col align="center" width="153">	<!-- Name -->
											<col align="center" width="200">	<!-- Description -->
											<col align="center" width="070">	<!-- Business Area -->
											<col align="center" width="070">	<!-- Sub Area -->
											<col align="center" width="050">	<!-- Start Date -->
											<col align="center" width="050">	<!-- End Date -->

											<tr id="id_row${EZF_ROW_NUMBER}" height="30">
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyDistListId_A' )">Dist ID
														<img id="sortIMG.ntfyDistListId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyDistListNm_A' )">Name
														<img id="sortIMG.ntfyDistListNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyDistListDescTxt_A' )">Description
														<img id="sortIMG.ntfyDistListDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyBizAreaTpDescTxt_A' )">Business Area
														<img id="sortIMG.ntfyBizAreaTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfySubAreaTpDescTxt_A' )">Sub Area
														<img id="sortIMG.ntfySubAreaTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A' )">Start Date
														<img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A' )">End Date
														<img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
											</tr>
										</table>
									</div>
									
									<div id="RowTBL" style="width:1122px; height:360px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
										<table border="1" cellpadding="1" cellspacing="0" id="A" width="1105" style="table-layout:fixed;">
											<col align="center" width="047">	<!-- Dist ID -->
											<col align="center" width="153">	<!-- Name -->
											<col align="center" width="200">	<!-- Description -->
											<col align="center" width="070">	<!-- Business Area -->
											<col align="center" width="070">	<!-- Sub Area -->
											<col align="center" width="050">	<!-- Start Date -->
											<col align="center" width="050">	<!-- End Date -->
											<tbody>
											<ezf:row ezfHyo="A">
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td align="left">
														<ezf:anchor name="ntfyDistListId_A" ezfName="ntfyDistListId_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_Setup" otherAttr=" id=\"ntfyDistListId_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
															<ezf:label name="ntfyDistListId_A" ezfName="ntfyDistListId_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td align="center"><ezf:inputText name="ntfyDistListNm_A" ezfName="ntfyDistListNm_A" value="1234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"36\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="center"><ezf:inputText name="ntfyDistListDescTxt_A" ezfName="ntfyDistListDescTxt_A" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"48\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="center"><ezf:inputText name="ntfyBizAreaTpDescTxt_A" ezfName="ntfyBizAreaTpDescTxt_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="center"><ezf:inputText name="ntfySubAreaTpDescTxt_A" ezfName="ntfySubAreaTpDescTxt_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												</tr>
											</ezf:row>
											<ezf:skip>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												
												
												
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000002"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000017"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td><input type="text" ezfhyo="A"  name="ntfyHdrId_A0" ezfname="ntfyHdrId_A0" class="pPro" size="10" value="0000000018"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrNm_A0" ezfname="ntfyHdrNm_A0" class="pPro" size="36" value="1234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyHdrDescTxt_A0" ezfname="ntfyHdrDescTxt_A0" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfyBizAreaTpNm_A0" ezfname="ntfyBizAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="ntfySubAreaTpNm_A0" ezfname="ntfySubAreaTpNm_A0" class="pPro" size="16" value="123456789012345678901234567890"></td>
													<td><input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" value="12/23/2016"></td>
													<td><input type="text" ezfhyo="A"  name="effToDt_A0" ezfname="effToDt_A0" class="pPro" size="10" value="12/23/2016"></td>
												</tr>
											</ezf:skip>
											</tbody>
										</table>
									</div>
								</div>
							</td>
						</tr>
					</table>
					</div>
<%-- #################################################### DETAIL ################################################### --%>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
