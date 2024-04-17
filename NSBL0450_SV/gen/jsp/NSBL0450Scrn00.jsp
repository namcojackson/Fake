<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170310111630 --%>
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
			<input type="hidden" name="pageID" value="NSBL0450Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supplemental Task">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Supplemental Task" class="pTab_ON"><a href="#">Suppl Task V</a></li>
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
							<td valign="top">
								<table border="0" style="table-layout:fixed;" width="95%" align="center">
									<col width="120">
									<col width="150">
									<col width="10">
									<col width="120">
									<col width="150">
									<col width="10">
									<col width="80">
									<col width="250">
									<col width="10">
									<col width="65">
									<col width="100">
									<tr height="21">
										<td class="stab">Supplemental Task#(*) :</td>
										<td><ezf:inputText name="svcSupplTaskNum_S" ezfName="svcSupplTaskNum_S" value="200215%" otherAttr=" size=\"20\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Technician" >Technician(*)</ezf:anchor>&nbsp;:</td>
										<td><ezf:inputText name="techPsnNm_S" ezfName="techPsnNm_S" value="0010111" otherAttr=" size=\"20\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Task Name :</td>
										<td><ezf:select name="svcSupplTaskTpCd_SS" ezfName="svcSupplTaskTpCd_SS" ezfBlank="1" ezfCodeName="svcSupplTaskTpCd_SC" ezfDispName="svcSupplTaskTpDescTxt_SC" /></td>
										<td>&nbsp;</td>
										<td class="stab">Local Time :</td>
										<td><ezf:inputCheckBox name="xxChkBox_S" ezfName="xxChkBox_S" value="Y" /></td>
									</tr>
									<tr height="21">
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Manager" >Resource Manager(*)</ezf:anchor>&nbsp;:</td>
										<td><ezf:inputText name="mgrNm_S" ezfName="mgrNm_S" otherAttr=" size=\"20\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Creation Date :</td>
										<td><ezf:inputText name="cratDt_S" ezfName="cratDt_S" value="01/01/2014" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('cratDt_S', 4);"></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br><br>
<%-- #################################################### DETAIL ################################################### --%>
					<ezf:skip>
					<table border="0" cellpadding="1" cellspacing="0">
						<col width="740">
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
							<td></td>
							<td class="stab">Showing</td>
							<td class="pOut">1</td>
							<td class="stab">to</td>
							<td class="pOut">18</td>
							<td class="stab">of</td>
							<td class="pOut">18</td>
							<td>&nbsp;</td>
							<td><input type="button" class="pBtn3" value="Prev" name="PagePrev"></td>
							<td></td>
							<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
						</tr>
					</table>
					</ezf:skip>
					<table width="100%">
						<tr>
							<td>
								<table width="98%">
									<tr align="right">
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; " width="98%" align="center" >
						<col valign="top">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" >
									<tr>
										<td>
											<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col width="30" align="center"><!-- Radio -->
													<col width="100" align="center"><!-- Suppl Task Num -->
													<col width="120" align="center"><!-- Task Name -->
													<col width="150" align="center"><!-- Technician Name -->
													<col width="150" align="center"><!-- Resource Manager -->
													<col width="120" align="center"><!-- Category -->
													<col width="150" align="center"><!-- Start Time -->
													<col width="150" align="center"><!-- End Time -->
													<col width="60" align="center"><!-- Travel -->
													<col width="60" align="center"><!-- Suppl -->
													<tr height="25">
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcSupplTaskNum_A')">Suppl Task Num</a><img id="sortIMG.svcSupplTaskNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcSupplTaskTpDescTxt_A')">Task Name</a><img id="sortIMG.svcSupplTaskTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'techPsnNm_A')">Technician Name</a><img id="sortIMG.techPsnNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mgrNm_A')">Resource Manager</a><img id="sortIMG.mgrNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxCmntTxt_A')">Category</a><img id="sortIMG.xxCmntTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxDtTm_ST')">Start Time</a><img id="sortIMG.xxDtTm_ST" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxDtTm_EN')">End Time</a><img id="sortIMG.xxDtTm_EN" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxDtTm_A3')">Travel</a><img id="sortIMG.xxDtTm_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxDtTm_A4')">Suppl</a><img id="sortIMG.xxDtTm_A4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													</tr>
												</table>
											</div>
											<div id="LeftTable_A" style="overflow-y:scroll; height:252; overflow-x:hidden; width:1109; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
													<col width="30" align="center"><!-- Radio -->
													<col width="100" align="center"><!-- Suppl Task Num -->
													<col width="120" align="center"><!-- Task Name -->
													<col width="150" align="center"><!-- Technician Name -->
													<col width="150" align="center"><!-- Resource Manager -->
													<col width="120" align="center"><!-- Category -->
													<col width="90" align="center"><!-- Start Time -->
													<col width="60" align="center"><!-- Start Time -->
													<col width="90" align="center"><!-- End Time -->
													<col width="60" align="center"><!-- End Time -->
													<col width="60" align="center"><!-- Travel -->
													<col width="60" align="center"><!-- Suppl -->
													<ezf:row ezfHyo="A">
														<tr height="25">
															<td><ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0"  otherAttr="onclick=\"sendServer('RadioSelect');\""/></td>
															<td><ezf:inputText name="svcSupplTaskNum_A" ezfName="svcSupplTaskNum_A" value="1000215415" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\""/></td>
															<td><ezf:inputText name="svcSupplTaskTpDescTxt_A" ezfName="svcSupplTaskTpDescTxt_A" value="5SL-NON CUSTOMER RELATED TRAVEL" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
															<td><ezf:inputText name="techPsnNm_A" ezfName="techPsnNm_A" value="Moravek, Michael C" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
															<td><ezf:inputText name="mgrNm_A" ezfName="mgrNm_A" value="Whelan, Joel A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
															<td><ezf:inputText name="xxCmntTxt_A" ezfName="xxCmntTxt_A" value="Suppplemental" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
															<td><ezf:inputText name="svcSupplFromDt_A" ezfName="svcSupplFromDt_A" value="10/01/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
															<td><ezf:inputText name="xxDtTm_A1" ezfName="xxDtTm_A1" value="12:05" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
															<td><ezf:inputText name="svcSupplToDt_A" ezfName="svcSupplToDt_A" value="10/01/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
															<td><ezf:inputText name="xxDtTm_A2" ezfName="xxDtTm_A2" value="12:50" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
															<td><ezf:inputText name="xxDtTm_A3" ezfName="xxDtTm_A3" value="00:00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
															<td><ezf:inputText name="xxDtTm_A4" ezfName="xxDtTm_A4" value="00:45" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr height="25">
															<td><input type="radio" value="Y" name="xxRadioBtn_A1"></td>
															<td><input type="text" class="pPro" size="15" value="1000215415"></td>
															<td><input type="text" class="pPro" size="20" value="5SA-MEAL"></td>
															<td><input type="text" class="pPro" size="20" value="Moravek, Michael C"></td>
															<td><input type="text" class="pPro" size="20" value="Whelan, Joel A"></td>
															<td><input type="text" class="pPro" size="20" value="Suppplemental"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:05"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:50"></td>
															<td><input type="text" class="pPro" size="8" value="00:00"></td>
															<td><input type="text" class="pPro" size="8" value="00:45"></td>
														</tr>
														<tr height="25">
															<td><input type="radio" value="Y" name="xxRadioBtn_A1"></td>
															<td><input type="text" class="pPro" size="15" value="1000215415"></td>
															<td><input type="text" class="pPro" size="20" value="5SA-MEAL"></td>
															<td><input type="text" class="pPro" size="20" value="Moravek, Michael C"></td>
															<td><input type="text" class="pPro" size="20" value="Whelan, Joel A"></td>
															<td><input type="text" class="pPro" size="20" value="Suppplemental"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:05"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:50"></td>
															<td><input type="text" class="pPro" size="8" value="00:00"></td>
															<td><input type="text" class="pPro" size="8" value="00:45"></td>
														</tr>
														<tr height="25">
															<td><input type="radio" value="Y" name="xxRadioBtn_A1"></td>
															<td><input type="text" class="pPro" size="15" value="1000215415"></td>
															<td><input type="text" class="pPro" size="20" value="5SA-MEAL"></td>
															<td><input type="text" class="pPro" size="20" value="Moravek, Michael C"></td>
															<td><input type="text" class="pPro" size="20" value="Whelan, Joel A"></td>
															<td><input type="text" class="pPro" size="20" value="Suppplemental"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:05"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:50"></td>
															<td><input type="text" class="pPro" size="8" value="00:00"></td>
															<td><input type="text" class="pPro" size="8" value="00:45"></td>
														</tr>
														<tr height="25">
															<td><input type="radio" value="Y" name="xxRadioBtn_A1"></td>
															<td><input type="text" class="pPro" size="15" value="1000215415"></td>
															<td><input type="text" class="pPro" size="20" value="5SA-MEAL"></td>
															<td><input type="text" class="pPro" size="20" value="Moravek, Michael C"></td>
															<td><input type="text" class="pPro" size="20" value="Whelan, Joel A"></td>
															<td><input type="text" class="pPro" size="20" value="Suppplemental"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:05"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:50"></td>
															<td><input type="text" class="pPro" size="8" value="00:00"></td>
															<td><input type="text" class="pPro" size="8" value="00:45"></td>
														</tr>
														<tr height="25">
															<td><input type="radio" value="Y" name="xxRadioBtn_A1"></td>
															<td><input type="text" class="pPro" size="15" value="1000215415"></td>
															<td><input type="text" class="pPro" size="20" value="5SA-MEAL"></td>
															<td><input type="text" class="pPro" size="20" value="Moravek, Michael C"></td>
															<td><input type="text" class="pPro" size="20" value="Whelan, Joel A"></td>
															<td><input type="text" class="pPro" size="20" value="Suppplemental"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:05"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:50"></td>
															<td><input type="text" class="pPro" size="8" value="00:00"></td>
															<td><input type="text" class="pPro" size="8" value="00:45"></td>
														</tr>
														<tr height="25">
															<td><input type="radio" value="Y" name="xxRadioBtn_A1"></td>
															<td><input type="text" class="pPro" size="15" value="1000215415"></td>
															<td><input type="text" class="pPro" size="20" value="5SA-MEAL"></td>
															<td><input type="text" class="pPro" size="20" value="Moravek, Michael C"></td>
															<td><input type="text" class="pPro" size="20" value="Whelan, Joel A"></td>
															<td><input type="text" class="pPro" size="20" value="Suppplemental"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:05"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:50"></td>
															<td><input type="text" class="pPro" size="8" value="00:00"></td>
															<td><input type="text" class="pPro" size="8" value="00:45"></td>
														</tr>
														<tr height="25">
															<td><input type="radio" value="Y" name="xxRadioBtn_A1"></td>
															<td><input type="text" class="pPro" size="15" value="1000215415"></td>
															<td><input type="text" class="pPro" size="20" value="5SA-MEAL"></td>
															<td><input type="text" class="pPro" size="20" value="Moravek, Michael C"></td>
															<td><input type="text" class="pPro" size="20" value="Whelan, Joel A"></td>
															<td><input type="text" class="pPro" size="20" value="Suppplemental"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:05"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:50"></td>
															<td><input type="text" class="pPro" size="8" value="00:00"></td>
															<td><input type="text" class="pPro" size="8" value="00:45"></td>
														</tr>
														<tr height="25">
															<td><input type="radio" value="Y" name="xxRadioBtn_A1"></td>
															<td><input type="text" class="pPro" size="15" value="1000215415"></td>
															<td><input type="text" class="pPro" size="20" value="5SA-MEAL"></td>
															<td><input type="text" class="pPro" size="20" value="Moravek, Michael C"></td>
															<td><input type="text" class="pPro" size="20" value="Whelan, Joel A"></td>
															<td><input type="text" class="pPro" size="20" value="Suppplemental"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:05"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:50"></td>
															<td><input type="text" class="pPro" size="8" value="00:00"></td>
															<td><input type="text" class="pPro" size="8" value="00:45"></td>
														</tr>
														<tr height="25">
															<td><input type="radio" value="Y" name="xxRadioBtn_A1"></td>
															<td><input type="text" class="pPro" size="15" value="1000215415"></td>
															<td><input type="text" class="pPro" size="20" value="5SA-MEAL"></td>
															<td><input type="text" class="pPro" size="20" value="Moravek, Michael C"></td>
															<td><input type="text" class="pPro" size="20" value="Whelan, Joel A"></td>
															<td><input type="text" class="pPro" size="20" value="Suppplemental"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:05"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:50"></td>
															<td><input type="text" class="pPro" size="8" value="00:00"></td>
															<td><input type="text" class="pPro" size="8" value="00:45"></td>
														</tr>
														<tr height="25">
															<td><input type="radio" value="Y" name="xxRadioBtn_A1"></td>
															<td><input type="text" class="pPro" size="15" value="1000215415"></td>
															<td><input type="text" class="pPro" size="20" value="5SA-MEAL"></td>
															<td><input type="text" class="pPro" size="20" value="Moravek, Michael C"></td>
															<td><input type="text" class="pPro" size="20" value="Whelan, Joel A"></td>
															<td><input type="text" class="pPro" size="20" value="Suppplemental"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:05"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:50"></td>
															<td><input type="text" class="pPro" size="8" value="00:00"></td>
															<td><input type="text" class="pPro" size="8" value="00:45"></td>
														</tr>
														<tr height="25">
															<td><input type="radio" value="Y" name="xxRadioBtn_A1"></td>
															<td><input type="text" class="pPro" size="15" value="1000215415"></td>
															<td><input type="text" class="pPro" size="20" value="5SA-MEAL"></td>
															<td><input type="text" class="pPro" size="20" value="Moravek, Michael C"></td>
															<td><input type="text" class="pPro" size="20" value="Whelan, Joel A"></td>
															<td><input type="text" class="pPro" size="20" value="Suppplemental"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:05"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:50"></td>
															<td><input type="text" class="pPro" size="8" value="00:00"></td>
															<td><input type="text" class="pPro" size="8" value="00:45"></td>
														</tr>
														<tr height="25">
															<td><input type="radio" value="Y" name="xxRadioBtn_A1"></td>
															<td><input type="text" class="pPro" size="15" value="1000215415"></td>
															<td><input type="text" class="pPro" size="20" value="5SA-MEAL"></td>
															<td><input type="text" class="pPro" size="20" value="Moravek, Michael C"></td>
															<td><input type="text" class="pPro" size="20" value="Whelan, Joel A"></td>
															<td><input type="text" class="pPro" size="20" value="Suppplemental"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:05"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:50"></td>
															<td><input type="text" class="pPro" size="8" value="00:00"></td>
															<td><input type="text" class="pPro" size="8" value="00:45"></td>
														</tr>
														<tr height="25">
															<td><input type="radio" value="Y" name="xxRadioBtn_A1"></td>
															<td><input type="text" class="pPro" size="15" value="1000215415"></td>
															<td><input type="text" class="pPro" size="20" value="5SA-MEAL"></td>
															<td><input type="text" class="pPro" size="20" value="Moravek, Michael C"></td>
															<td><input type="text" class="pPro" size="20" value="Whelan, Joel A"></td>
															<td><input type="text" class="pPro" size="20" value="Suppplemental"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:05"></td>
															<td><input type="text" class="pPro" size="12" value="10/01/2014"></td>
															<td><input type="text" class="pPro" size="8" value="12:50"></td>
															<td><input type="text" class="pPro" size="8" value="00:00"></td>
															<td><input type="text" class="pPro" size="8" value="00:45"></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><br></td>
						</tr>
						<tr>
							<td>
								<div id="Detail" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
									<fieldset style="margin:5px 0px 0px 0px; height:100%;">
									<legend style="font-size:12px;">Supplemental Task Detail</legend>
									<table width="100%" border="0">
										<tr>
											<td>
												<table border="0" style="table-layout:fixed;" width="85%" align="center">
													<col width="120">
													<col width="150">
													<col width="40">
													<col width="110">
													<col width="180">
													<col width="40">
													<col width="90">
													<col width="180">
													<tr height="21">
														<td class="stab">Supplemental Task# :</td>
														<td><ezf:inputText name="svcSupplTaskNum_D" ezfName="svcSupplTaskNum_D" value="100215446" otherAttr=" size=\"20\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Start Time :</td>
														<td><ezf:inputText name="svcSupplFromDt_D" ezfName="svcSupplFromDt_D" value="01/01/2014" otherEvent1="ChangeTime" otherAttr=" size=\"12\" maxlength=\"10\" ezffocusout=\"ChangeTime\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('svcSupplFromDt_D', 4);"><ezf:inputText name="xxDtTm_D1" ezfName="xxDtTm_D1" value="11:45" otherEvent1="ChangeTime" otherAttr=" size=\"6\" maxlength=\"5\" ezffocusout=\"ChangeTime\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Created By :</td>
														<td><ezf:inputText name="xxAllPsnNm_D1" ezfName="xxAllPsnNm_D1" value="Moravek, Michael C" otherAttr=" size=\"20\""/></td>
													</tr>
													<tr height="21">
														<td class="stab">Task Name :</td>
														<td><ezf:inputText name="svcSupplTaskTpDescTxt_D" ezfName="svcSupplTaskTpDescTxt_D" value="100215446" otherAttr=" size=\"20\""/></td>
														<td>&nbsp;</td>
														<td class="stab">End Time :</td>
														<td><ezf:inputText name="svcSupplToDt_D" ezfName="svcSupplToDt_D" value="01/01/2014" otherEvent1="ChangeTime" otherAttr=" size=\"12\" maxlength=\"10\" ezffocusout=\"ChangeTime\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('svcSupplToDt_D', 4);"><ezf:inputText name="xxDtTm_D2" ezfName="xxDtTm_D2" value="11:45" otherEvent1="ChangeTime" otherAttr=" size=\"6\" maxlength=\"5\" ezffocusout=\"ChangeTime\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Creation Date :</td>
														<td><ezf:inputText name="cratDt_D" ezfName="cratDt_D" value="01/01/2014" otherAttr=" size=\"11\""/><ezf:inputText name="xxDtTm_D6" ezfName="xxDtTm_D6" value="11:45:00" otherAttr=" size=\"8\""/></td>
													</tr>
													<tr height="21">
														<td class="stab">Techinician :</td>
														<td><ezf:inputText name="techPsnNm_D" ezfName="techPsnNm_D" value="100215446" otherAttr=" size=\"20\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Supplemental Time :</td>
														<td><ezf:inputText name="xxDtTm_D4" ezfName="xxDtTm_D4" value="11:45" otherAttr=" size=\"6\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Update By :</td>
														<td><ezf:inputText name="xxAllPsnNm_D2" ezfName="xxAllPsnNm_D2" value="Moravek, Michael C" otherAttr=" size=\"20\""/></td>
													</tr>
													<tr height="21">
														<td class="stab">Resource Manager :</td>
														<td><ezf:inputText name="mgrNm_D" ezfName="mgrNm_D" value="100215446" otherAttr=" size=\"20\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Travel Time :</td>
														<td><ezf:inputText name="xxDtTm_D3" ezfName="xxDtTm_D3" value="11:45" otherAttr=" size=\"6\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Last Update :</td>
														<td><ezf:inputText name="updDt_D" ezfName="updDt_D" value="01/01/2014" otherAttr=" size=\"11\""/><ezf:inputText name="xxDtTm_D7" ezfName="xxDtTm_D7" value="11:45:00" otherAttr=" size=\"8\""/></td>
													</tr>
													<tr height="21">
														<td class="stab">Category :</td>
														<td><ezf:inputText name="xxCmntTxt_D" ezfName="xxCmntTxt_D" value="100215446" otherAttr=" size=\"20\""/></td>
														<td>&nbsp;</td>
														<td class="stab">Duration :</td>
														<td><ezf:inputText name="xxDtTm_D5" ezfName="xxDtTm_D5" value="11:45" otherAttr=" size=\"6\""/></td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
													</tr>
													<tr height="21">
														<td class="stab">Sourse Task# :</td>
														<td><ezf:inputText name="svcTaskNum_D" ezfName="svcTaskNum_D" value="100215446" otherAttr=" size=\"20\""/></td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
													</tr>
												</table>
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
<script type="text/javascript">
	function onFocusEvent(item){
		if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
		}
	}

	function onBlurEvent(item){
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
	}
</script>

<%-- **** Task specific area ends here **** --%>
