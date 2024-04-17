<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160901103300 --%>
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
			<input type="hidden" name="pageID" value="NWWL0050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Distribution List Setup">
			
			
			
			
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
										<li title="Distribution List Setup" class="pTab_ON"><a href="#">Distribution List Setup</a></li>
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
					<table border="0" cellspacing="0" cellpadding="0" height="100" style="text-align:left;margin-left:3px;">
						<tr>
							<td>
								<table border="0" cellspacing="1" cellpadding="1"  style="text-align:left;margin-left:3px;">
									<col width="80">
									<col width="280">
									<col width="70">
									<col width="150">
									<tr>
										<td class="stab">Name</td>
										<td class="stab"><ezf:inputText name="ntfyDistListNm" ezfName="ntfyDistListNm" value="1234567890" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
										<td class="stab">Dist List ID</td>
										<td class="stab"><ezf:inputText name="ntfyDistListId" ezfName="ntfyDistListId" value="12345678" otherAttr=" size=\"8\" maxlength=\"8\""/></td>

									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table border="0" cellspacing="1" cellpadding="1"  style="text-align:left;margin-left:3px;">
									<col width="80">
									<col width="500">
									<tr>
										<td class="stab">Description</td>
										<td colspan="2" class="stab"><ezf:inputText name="ntfyDistListDescTxt" ezfName="ntfyDistListDescTxt" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"60\" maxlength=\"240\""/></td>
									</tr>
									<tr>
										<td class="stab">Business Area</td>
										<td>
											<ezf:select name="ntfyBizAreaTpCd_D" ezfName="ntfyBizAreaTpCd_D" ezfBlank="1" ezfCodeName="ntfyBizAreaTpCd_P" ezfDispName="ntfyBizAreaTpDescTxt_P" otherEvent1=" onchange=\"sendServer('OnChange_BizArea')\"" otherAttr=" style=\"width:200px\" id=\"ntfyBizAreaTpCd_D\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Sub Area</td>
										<td>
											<ezf:select name="ntfySubAreaTpCd_D" ezfName="ntfySubAreaTpCd_D" ezfBlank="1" ezfCodeName="ntfySubAreaTpCd_P" ezfDispName="ntfySubAreaTpDescTxt_P" otherAttr=" style=\"width:200px\" id=\"ntfySubAreaTpCd_D\""/>
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
						</tr>
					</table>
					<br>
<%-- #################################################### TO HEADER ################################################### --%>
					
<%-- #################################################### FROM Assignments ################################################### --%>
					<hr width="1130">
					<label> Assignment </label>
					<table border="0" cellspacing="0" cellpadding="0" height="80"  style="text-align:left;margin-left:3px;">
						<col width ="10">
						<col width ="600">
						<tr >
							<td>
								<table border="0" cellspacing="0" cellpadding="0" style="text-align:left;margin-left:3px;margin-bottom:70px;">
									<tr>
										<td>
											<ezf:inputButton name="Add_Line" value="+" htmlClass="pBtn2" />
										</td>
										<td>
											<ezf:inputButton name="Del_Line" value="-" htmlClass="pBtn2" />
										</td>
									</tr>
								</table>
							</td>
							<td>
								<div id="AssignTopTBL" style="overflow-y:none; overflow-x:none;">
									<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="600" style="margin-right:20px" >
										<col align="center" width="030">	<!-- raddio -->
										<col align="center" width="070">	<!-- AssignType -->
										<col align="center" width="100">	<!-- Value -->
										<col align="center" width="040">	<!-- Active -->

										<tr id="id_row${EZF_ROW_NUMBER}" height="24">
											<td class="pClothBs">
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyDistQlfyCd_A' )">Assign Type
													<img id="sortIMG.ntfyDistQlfyCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
												</a>
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyDistListAsgValTxt_A' )">Value
													<img id="sortIMG.ntfyDistListAsgValTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
												</a>
											</td>
											<td class="pClothBs">
												<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ntfyDistListAsgActvFlg_A' )">Active
													<img id="sortIMG.ntfyDistListAsgActvFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
												</a>
											</td>
										</tr>
									</table>
								</div>
								<div id="AssignRowTBL" style="width:600px; height:75px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
									<table border="1" cellpadding="1" cellspacing="0" id="A" width="600" style="table-layout:fixed;">
										<col align="center" width="030">	<!-- raddio -->
										<col align="center" width="070">	<!-- AssignType -->
										<col align="center" width="100">	<!-- Value -->
										<col align="center" width="040">	<!-- Active -->
										<tbody>
										<ezf:row ezfHyo="A">
											<tr id="id_row{EZF_ROW_NUMBER}" height="24">
												<td align="center">
													<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"Radio{EZF_ROW_NUMBER}\""/>
												</td>
												<td align="center">
													<ezf:select name="ntfyDistQlfyCd_A" ezfName="ntfyDistQlfyCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="ntfyDistQlfyCd_P" ezfDispName="ntfyDistQlfyDescTxt_P" otherAttr=" style=\"width:155px\" id=\"ntfySubAreaTpCd_A\""/>
												</td>
												<td align="center"><ezf:inputText name="ntfyDistListAsgValTxt_A" ezfName="ntfyDistListAsgValTxt_A" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"33\""/></td>
												<td align="center"><ezf:inputCheckBox name="ntfyDistListAsgActvFlg_A" ezfName="ntfyDistListAsgActvFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr id="id_row{EZF_ROW_NUMBER}" height="24">
												<td align="center">
													<input type="Radio" name="xxRadioBtn"  id="Radio{EZF_ROW_NUMBER}" value="{EZF_ROW_NUMBER}" ezfname="xxRadioBtn" ezfHyo="A" >
												</td>
												<td align="center">
													<select style="width:155px" id="ntfySubAreaTpCd_SL" name="ntfySubAreaTpCd_D" ezfname="ntfySubAreaTpCd_D" ezflist="ntfySubAreaTpCd_DP,ntfySubAreaTpDescTxt_DP,99, ,blank">
														<option value=""></option>
														<option value="XXX">JOB CODE</option>
														<option value="011">YYY Sub Area 02</option>
														<option value="012">ZZZ Sub Area 03</option>
													</select>
												</td>
												<td align="center"><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="40" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
												<td align="center"><input type="checkBox" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" ></td>
											</tr>
											<tr id="id_row{EZF_ROW_NUMBER}" height="24">
												<td align="center">
													<input type="Radio" name="xxRadioBtn"  id="Radio{EZF_ROW_NUMBER}" value="{EZF_ROW_NUMBER}" ezfname="xxRadioBtn" ezfHyo="A" >
												</td>
												<td align="center">
													<select style="width:155px" id="ntfySubAreaTpCd_SL" name="ntfySubAreaTpCd_D" ezfname="ntfySubAreaTpCd_D" ezflist="ntfySubAreaTpCd_DP,ntfySubAreaTpDescTxt_DP,99, ,blank">
														<option value=""></option>
														<option value="XXX">JOB CODE</option>
														<option value="011">YYY Sub Area 02</option>
														<option value="012">ZZZ Sub Area 03</option>
													</select>
												</td>
												<td align="center"><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
												<td align="center"><input type="checkBox" ezfhyo="A"  name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" class="pPro" size="16" ></td>
											</tr>
											<tr id="id_row{EZF_ROW_NUMBER}" height="24">
												<td align="center">
													<input type="Radio" name="xxRadioBtn"  id="Radio{EZF_ROW_NUMBER}" value="{EZF_ROW_NUMBER}" ezfname="xxRadioBtn" ezfHyo="A" >
												</td>
												<td align="center">
													<select style="width:155px" id="ntfySubAreaTpCd_SL" name="ntfySubAreaTpCd_D" ezfname="ntfySubAreaTpCd_D" ezflist="ntfySubAreaTpCd_DP,ntfySubAreaTpDescTxt_DP,99, ,blank">
														<option value=""></option>
														<option value="XXX">JOB CODE</option>
														<option value="011">YYY Sub Area 02</option>
														<option value="012">ZZZ Sub Area 03</option>
													</select>
												</td>
												<td align="center"><input type="text" ezfhyo="A"  name="ntfyDistListDescTxt_A" ezfname="ntfyDistListDescTxt_A" class="pPro" size="48" value="12345678901234567890123456789012345678901234567890" style="border:none; background-color:transparent;"></td>
												<td align="center"><input type="checkBox" ezfhyo="A" name="ntfyBizAreaTpDescTxt_A" ezfname="ntfyBizAreaTpDescTxt_A" value="Y" ></td>
											</tr>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
					</table>
<%-- #################################################### To Assignments ################################################### --%>

<%-- #################################################### To Existing Referenced Notification ################################################### --%>
					<hr width="1130">
					<label> Existing Referenced Notification </label>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<!-- @@@@@ Left -->
							<td style="padding-left:3px;">
								<div>
									<div id="NotifTopTBL" style="overflow-y:none; overflow-x:none;">
										<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="BHEAD" width="1105px" style="margin-right:20px" >
											<col align="center" width="047">	<!-- Notif ID -->
											<col align="center" width="150">	<!-- Name -->
											<col align="center" width="153">	<!-- Description -->
											<col align="center" width="070">	<!-- Business Area -->
											<col align="center" width="070">	<!-- Sub Area -->
											<col align="center" width="050">	<!-- Start Date -->
											<col align="center" width="050">	<!-- End Date -->
											<col align="center" width="050">	<!-- Activ Flag -->

											<tr id="id_row${EZF_ROW_NUMBER}" height="30">
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'B', 'ntfyHdrId_B' )">Notif ID
														<img id="sortIMG.ntfyHdrId_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'B', 'ntfyHdrNm_B' )">Name
														<img id="sortIMG.ntfyHdrNm_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'B', 'ntfyHdrDescTxt_B' )">Description
														<img id="sortIMG.ntfyHdrDescTxt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'B', 'ntfyBizAreaTpDescTxt_B' )">Business Area
														<img id="sortIMG.ntfyBizAreaTpDescTxt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'B', 'ntfySubAreaTpDescTxt_B' )">Sub Area
														<img id="sortIMG.ntfySubAreaTpDescTxt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'B', 'effFromDt_B' )">Start Date
														<img id="sortIMG.effFromDt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'B', 'effThruDt_B' )">End Date
														<img id="sortIMG.effThruDt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												<td class="pClothBs">
													<a href="#" class="pSortCol" onclick="columnSort( 'B', 'ntfyHdrActvFlg_B' )">Active Flag
														<img id="sortIMG.ntfyHdrActvFlg_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
													</a>
												</td>
												
											</tr>
										</table>
									</div>
									
									<div id="NotifRowTBL" style="width:1122px; height:265px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
										<table border="1" cellpadding="1" cellspacing="0" id="B" width="1105" style="table-layout:fixed;">
											<col align="center" width="047">	<!-- Notif ID -->
											<col align="center" width="150">	<!-- Name -->
											<col align="center" width="153">	<!-- Description -->
											<col align="center" width="070">	<!-- Business Area -->
											<col align="center" width="070">	<!-- Sub Area -->
											<col align="center" width="050">	<!-- Start Date -->
											<col align="center" width="050">	<!-- End Date -->
											<col align="center" width="050">	<!-- Activ Flag -->
											<tbody>
											<ezf:row ezfHyo="B">
												<tr id="id_row{EZF_ROW_NUMBER}" height="24">
													<td align="left">
														<ezf:anchor name="ntfyDistListId_A" ezfName="ntfyDistListId_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_NotifSetup" otherAttr=" id=\"ntfyDistListId_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
															<ezf:label name="ntfyHdrId_B" ezfName="ntfyHdrId_B" ezfHyo="B" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td align="center"><ezf:inputText name="ntfyHdrNm_B" ezfName="ntfyHdrNm_B" value="1234567890123456789012345678901234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"36\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="center"><ezf:inputText name="ntfyHdrDescTxt_B" ezfName="ntfyHdrDescTxt_B" value="12345678901234567890123456789012345678901234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"48\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="center"><ezf:inputText name="ntfyBizAreaTpDescTxt_B" ezfName="ntfyBizAreaTpDescTxt_B" value="123456789012345678901234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/></td>
													<td align="center"><ezf:inputText name="ntfySubAreaTpDescTxt_B" ezfName="ntfySubAreaTpDescTxt_B" value="123456789012345678901234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/></td>
													<td><ezf:label name="effFromDt_B" ezfName="effFromDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
													<td><ezf:label name="effThruDt_B" ezfName="effThruDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
													<td align="center"><ezf:inputCheckBox name="ntfyHdrActvFlg_B" ezfName="ntfyHdrActvFlg_B" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
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
											</ezf:skip>
											</tbody>
										</table>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
			

<%-- **** Task specific area ends here **** --%>
