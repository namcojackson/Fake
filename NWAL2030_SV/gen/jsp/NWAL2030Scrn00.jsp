<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160323103646 --%>
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
			<input type="hidden" name="pageID" value="NWAL2030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Hold Set Up Screen">
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<!-- ######################################## HEADER ######################################## -->
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Order Holds</a></li>
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
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" >
						<tr>
							<td>
								<table border="0" style="table-layout:fixed;" width="95%">
									<col width="5">		<!-- () -->
									<col width="60">	<!-- Hold ID(*),Hold Name(*) -->
									<col width="280">	<!-- Text Box -->
									<col width="5">		<!-- () -->
									<col width="440">	<!-- Text Box -->
									<tr height="24">
										<td></td>
										<td class="stab">Hold ID(*)</td>
										<td><ezf:inputText name="hldRsnCd" ezfName="hldRsnCd" value="710" otherAttr=" size=\"5\" maxlength=\"3\" ezftoupper=\"\""/></td>
										<td></td>
										<td></td>
									</tr>
									<tr height="24">
										<td></td>
										<td class="stab">Hold Name(*)</td>
										<td><ezf:inputText name="hldRsnNm" ezfName="hldRsnNm" value="Hold Name 710" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
										<td></td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
									<tr height="10">
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<!-- ######################################## DETAIL HEADER ######################################## -->
 					<table border="0" style="table-layout:fixed;width=96%;margin-left:20;">
						<col width="60">	<!-- Add line -->
						<col width="60">	<!-- Delete line -->
						<col width="160">
						<col width="350">	<!-- Paging -->
						<tr>
							<td><ezf:inputButton name="AddLine" ezfName="xxBtnFlg" value="Add line" htmlClass="pBtn7" otherAttr=" id=\"btnAddLine\""/></td>
							<td><ezf:inputButton name="DeleteLine" value="Delete line" htmlClass="pBtn7" otherAttr=" id=\"btnDeleteLine\""/></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<!-- ######################################## DETAIL ######################################## -->
					<table border="0" cellpadding="1" cellspacing="0" style="margin-left:15;">
						<col valign="top">
						<tr>
							<td>
								<div id="Top" style="overflow-y:hidden; width:1080;">
									<table border="1"width="1080" height="24" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed">
										<col width="30">	<!-- checkbox -->
										<col width="50">	<!-- Hold ID -->
										<col width="230">	<!-- Hold Name -->
										<col width="270">	<!-- Hold Description -->
										<col width="160">	<!-- Hold Level -->
										<col width="120">	<!-- Workflow Activity -->
										<col width="110">	<!-- Start Date -->
										<col width="110">	<!-- End Date -->
										<tr height="27" align="center" valign="middle">
											<td class="pClothBs"></td>
											<td class="pClothBs">Hold ID</td>
											<td class="pClothBs">Hold Name</td>
											<td class="pClothBs">Hold Description</td>
											<td class="pClothBs">Hold Level</td>
											<td class="pClothBs">Workflow Activity</td>
											<td class="pClothBs">Start Date</td>
											<td class="pClothBs">End Date</td>
										</tr>
									</table>
								</div>
								<div id="Detail" style="overflow-y:scroll; width:1100; height:430;">
									<table border="1" width="1080" height="24" id="A" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed">
										<col width="30">	<!-- checkbox -->
										<col width="50">	<!-- Hold ID -->
										<col width="230">	<!-- Hold Name -->
										<col width="270">	<!-- Hold Description -->
										<col width="160">	<!-- Hold Level -->
										<col width="120">	<!-- Workflow Activity -->
										<col width="110">	<!-- Start Date -->
										<col width="110">	<!-- End Date -->
										<ezf:row ezfHyo="A">
											<tr>
												<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="hldRsnCd_A" ezfName="hldRsnCd_A" value="710" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"3\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="hldRsnNm_A" ezfName="hldRsnNm_A" value="Hold Name 710" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"50\""/></td>
												<td><ezf:inputText name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" value="Hold Description 710" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"36\" maxlength=\"50\""/></td>
												<td align="left">
													<ezf:select name="hldLvlCd_SV" ezfName="hldLvlCd_SV" ezfHyo="A" ezfArrayNo="0" ezfCodeName="hldLvlCd_CD" ezfDispName="hldLvlDescTxt_SC" ezfCodeDispHyo="A" otherAttr=" style=\"width:150px;\""/>
												</td>
												<td align="left">
													<ezf:select name="ordProcNodeCd_SV" ezfName="ordProcNodeCd_SV" ezfHyo="A" ezfArrayNo="0" ezfCodeName="ordProcNodeCd_CD" ezfDispName="ordProcNodeDescTxt_SC" ezfCodeDispHyo="A" otherAttr=" style=\"width:110px;\""/>
												</td>
												<td align="left">
													<ezf:inputText name="hldEffFromDt_A" ezfName="hldEffFromDt_A" value="12/31/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');">
												</td>
												<td align="left">
													<ezf:inputText name="hldEffToDt_A" ezfName="hldEffToDt_A" value="12/31/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');">
												</td>
											</tr>
											<ezf:skip>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											<tr>
												<td align="center"><input type="checkbox" value="Y" name="xxChkBox_IN" ezfname="xxChkBox_IN"></td>
												<td><input type="text" size="5"  maxlength="3"  name="hldRsnCd_A" ezfName="hldRsnCd_A" ezfhyo="A" value="710" ezftoupper></td>
												<td><input type="text" size="30" maxlength="50" name="hldRsnNm_A" ezfName="hldRsnNm_A" ezfhyo="A" value="Hold Name 710"></td>
												<td><input type="text" size="36" maxlength="50" name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" ezfhyo="A" value="Hold Description 710"></td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:150px;">
														<option selected>CPO Level</option>
														<option>CPO Detail Level</option>
													</select>
												</td>
												<td align="left">
													<select name="hdlLvlCd_SV" ezfname="hdlLvlCd_SV" ezfhyo="A" ezflist="hdlLvlCd_CD,hdlLvlCd_SC,99, ," ezfCodeDispHyo="A" style="width:110px;">
														<option selected>Allocation</option>
														<option>SO Creation</option>
														<option>AR Staging</option>
													</select>
												</td>
												<td align="left">
													<input type="text" name="hldEffFromDt_A" ezfName="hldEffFromDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffFromDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffFromDt_A">
												</td>
												<td align="left">
													<input type="text" name="hldEffToDt_A" ezfName="hldEffToDt_A" size="10" maxlength="10" value="12/31/2016">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('hldEffToDt_A', 4, '{EZF_ROW_NUMBER}');" id="hldEffToDt_A">
												</td>
											</tr>
											</ezf:skip>
										</ezf:row>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
