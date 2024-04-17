<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160322170929 --%>
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
			<input type="hidden" name="pageID" value="NSBL0350Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Group Level Report">
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
										<li title="Group Level Report" class="pTab_ON"><a href="#">Grp Lvl Rpt</a></li>
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
								<table border="0" style="table-layout:fixed;" width="1080">
									<tr align="left">
									<col width="3">	<!-- () -->
									<col width="50">	<!-- Service Group -->
									<col width="60">	<!-- Service Group(Text input) -->
									<col width="20">	<!-- >> -->
									<col width="100">	<!-- Service Group Name(Text output) -->
									<col width="10">	<!-- () -->
									<col width="20">	<!-- As of -->
									<col width="75">	<!-- yyyy/MM/dd -->
									<col width="280">	<!-- () -->
									<col width="55">	<!-- Search -->
										<td></td>
										<td class="stab"><ezf:anchor name="orgCd_HL" ezfName="orgCd_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_OrgLkupPop" >Service Group</ezf:anchor></td>
										<td><ezf:inputText name="orgCd_HT" ezfName="orgCd_HT" value="100000" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_SvcGrp" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="orgDescTxt_H" ezfName="orgDescTxt_H" value="NORTH CENTRAL" otherAttr=" size=\"21\""/></td>
										<td></td>
										<td class="stab">As Of</td>
										<td><ezf:inputText name="xxFromDt_H" ezfName="xxFromDt_H" value="01/01/2016" otherAttr=" size=\"12\" maxlength=\"10\""/>
										<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxFromDt_H', 4);" id="xxFromDt_H">
										</td>
										<td></td>
										<td >
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"btnSrch\""/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><hr></td>
						</tr>
					</table>
					<!-- ######################################## DETAIL HEADER ######################################## -->
 					<table border="0" style="table-layout:fixed;width:1080px; margin-left:25;">
						<col width="300">	<!-- Group Level -->
						<col width="700">	<!-- Paging -->
						<tr>
							<td class="stab">Group Level</td>
							<!-- Pagination & Navigation--START-->
							<td align="right">
								<ezf:skip>
									<table border="0" cellpadding="1" cellspacing="0">
											<col >
											<col width="40"  align="right">
											<col width="16"  align="center">
											<col width="40"  align="right">
											<col width="16"  align="center">
											<col width="40"  align="right">
											<col width="10">
											<col width="0">
											<col width="1">
											<col width="0">
											<col width="1">
											<tr>
												<td class="stab">Showing</td>
												<td class="pOut">1</td>
												<td class="stab">to</td>
												<td class="pOut">3</td>
												<td class="stab">of</td>
												<td class="pOut">1000</td>
												<td>&nbsp;</td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
												<td></td>
											</tr>
									</table>
								</ezf:skip>
								<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="A" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
								</jsp:include>
							</td>
							<!-- Pagination & Navigation--END-->
						</tr>
					</table>
					<!-- ######################################## DETAIL ######################################## -->
					<table border="0" style="margin-left:20;">
						<tr>
							<td>
								<!-- total table -->
								<table border="0" cellpadding="0" cellspacing="0" width="1080" height="450" rules="none">
									<tr>
										<!-- Left -->
										<td align="center" valign="top">
											<!-- Left TBL Top -->
											<div id="LeftTable_Top" style="overflow-x:none; overflow-y:none; width:150; float:none;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col width="170">
													<tr height="32">
														<td class="pClothBs">&nbsp;</td>
													</tr>
												</table>
											</div>
											<!-- Left TBL -->
											<div id="LeftTable" style="overflow-x:none; overflow-y:none; width:150; height:450; float:none;" onScroll="synchroScrollTop(this.id, new Array('RightTBL'));">
												<table border="1" cellpadding="1" cellspacing="0" id="L" style="table-layout: fixed">
													<col align="left" width="170">
													<ezf:row ezfHyo="L">
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_L1" ezfName="xxScrItem130Txt_L1" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_L2" ezfName="xxScrItem130Txt_L2" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_L3" ezfName="xxScrItem130Txt_L3" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_L4" ezfName="xxScrItem130Txt_L4" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_L5" ezfName="xxScrItem130Txt_L5" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_L6" ezfName="xxScrItem130Txt_L6" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_L7" ezfName="xxScrItem130Txt_L7" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_L8" ezfName="xxScrItem130Txt_L8" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_L9" ezfName="xxScrItem130Txt_L9" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_LA" ezfName="xxScrItem130Txt_LA" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_LB" ezfName="xxScrItem130Txt_LB" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_LC" ezfName="xxScrItem130Txt_LC" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_LD" ezfName="xxScrItem130Txt_LD" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_LE" ezfName="xxScrItem130Txt_LE" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_LF" ezfName="xxScrItem130Txt_LF" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_LG" ezfName="xxScrItem130Txt_LG" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													<tr height="24"><td class="stab" align="left">
														<ezf:label name="xxScrItem130Txt_LH" ezfName="xxScrItem130Txt_LH" ezfHyo="L" ezfArrayNo="0" />
													</td></tr>
													</ezf:row>
												</table>
											</div>
										</td>
										<!-- Right -->
										<td align="left" valign="top">
											<!-- Right TBL Top -->
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:900;height:34; float:left;" onScroll="synchroScrollLeft(this.id, new Array('RightTBL'));">
												<table border="1" cellpadding="1" cellspacing="0" id="" style="table-layout: fixed;">
													<ezf:row ezfHyo="A">
													<col align="center" width="100">
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
													<tr height="32" valign="middle">
														<ezf:row ezfHyo="A">
														<td class="pClothBs"><ezf:anchor name="orgDescTxt_AL" ezfName="orgDescTxt_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_SubGrpRpt" otherAttr=" id=\"orgDescTxt_AL#{EZF_ROW_NUMBER}\""><font color="#ffffff"><ezf:label name="orgDescTxt_A" ezfName="orgDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
														</ezf:row>
														<ezf:skip>
														<td class="pClothBs"><a href="#" class="stab" name="orgDescTxt" onclick="sendServer('MoveWin_SubGrpRpt')">Branch01</a></td>
														<td class="pClothBs"><a href="#" class="stab" name="orgDescTxt" onclick="sendServer('MoveWin_SubGrpRpt')">Branch02</a></td>
														<td class="pClothBs"><a href="#" class="stab" name="orgDescTxt" onclick="sendServer('MoveWin_SubGrpRpt')">Branch03</a></td>
														<td class="pClothBs"><a href="#" class="stab" name="orgDescTxt" onclick="sendServer('MoveWin_SubGrpRpt')">Branch04</a></td>
														<td class="pClothBs"><a href="#" class="stab" name="orgDescTxt" onclick="sendServer('MoveWin_SubGrpRpt')">Branch05</a></td>
														<td class="pClothBs"><a href="#" class="stab" name="orgDescTxt" onclick="sendServer('MoveWin_SubGrpRpt')">Branch06</a></td>
														<td class="pClothBs"><a href="#" class="stab" name="orgDescTxt" onclick="sendServer('MoveWin_SubGrpRpt')">Branch07</a></td>
														<td class="pClothBs"><a href="#" class="stab" name="orgDescTxt" onclick="sendServer('MoveWin_SubGrpRpt')">Branch08</a></td>
														<td class="pClothBs"><a href="#" class="stab" name="orgDescTxt" onclick="sendServer('MoveWin_SubGrpRpt')">Branch09</a></td>
														<td class="pClothBs"><a href="#" class="stab" name="orgDescTxt" onclick="sendServer('MoveWin_SubGrpRpt')">Branch10</a></td>
														</ezf:skip>
													</tr>
													
												</table>
											</div>
											<!-- Right TBL -->
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:hidden; width:900; height:428; float:left;" onScroll="synchroScrollLeft(this.id, new Array('RightTBL_Top')); synchroScrollTop(this.id, new Array('LeftTBL'));">
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed" width="100">
													<ezf:row ezfHyo="A">
													<col width="100">
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
														<tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="totInProcTaskCnt_A" ezfName="totInProcTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="totInProcTaskCnt">50</label></td>
															<td><label ezfout name="totInProcTaskCnt">60</label></td>
															<td><label ezfout name="totInProcTaskCnt">60</label></td>
															<td><label ezfout name="totInProcTaskCnt">60</label></td>
															<td><label ezfout name="totInProcTaskCnt">60</label></td>
															<td><label ezfout name="totInProcTaskCnt">60</label></td>
															<td><label ezfout name="totInProcTaskCnt">60</label></td>
															<td><label ezfout name="totInProcTaskCnt">60</label></td>
															<td><label ezfout name="totInProcTaskCnt">60</label></td>
															<td><label ezfout name="totInProcTaskCnt">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="prtWaitTaskCnt_A" ezfName="prtWaitTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="prtWaitTaskCnt">50</label></td>
															<td><label ezfout name="prtWaitTaskCnt">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt">60</label></td>
															</ezf:skip>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="spclWaitTaskCnt_A" ezfName="spclWaitTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="spclWaitTaskCnt">50</label></td>
															<td><label ezfout name="spclWaitTaskCnt">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="othOpenTaskCnt_A" ezfName="othOpenTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="othOpenTaskCnt">50</label></td>
															<td><label ezfout name="othOpenTaskCnt">60</label></td>
															<td><label ezfout name="othOpenTaskCnt">60</label></td>
															<td><label ezfout name="othOpenTaskCnt">60</label></td>
															<td><label ezfout name="othOpenTaskCnt">60</label></td>
															<td><label ezfout name="othOpenTaskCnt">60</label></td>
															<td><label ezfout name="othOpenTaskCnt">60</label></td>
															<td><label ezfout name="othOpenTaskCnt">60</label></td>
															<td><label ezfout name="othOpenTaskCnt">60</label></td>
															<td><label ezfout name="othOpenTaskCnt">60</label></td>
															</ezf:skip>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="custTaskCnt_A" ezfName="custTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="custTaskCnt">50</label></td>
															<td><label ezfout name="custTaskCnt">60</label></td>
															<td><label ezfout name="custTaskCnt">60</label></td>
															<td><label ezfout name="custTaskCnt">60</label></td>
															<td><label ezfout name="custTaskCnt">60</label></td>
															<td><label ezfout name="custTaskCnt">60</label></td>
															<td><label ezfout name="custTaskCnt">60</label></td>
															<td><label ezfout name="custTaskCnt">60</label></td>
															<td><label ezfout name="custTaskCnt">60</label></td>
															<td><label ezfout name="custTaskCnt">60</label></td>
															</ezf:skip>
														</tr>
														<tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="esclTaskCnt_A" ezfName="esclTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="esclTaskCnt">50</label></td>
															<td><label ezfout name="esclTaskCnt">60</label></td>
															<td><label ezfout name="esclTaskCnt">60</label></td>
															<td><label ezfout name="esclTaskCnt">60</label></td>
															<td><label ezfout name="esclTaskCnt">60</label></td>
															<td><label ezfout name="esclTaskCnt">60</label></td>
															<td><label ezfout name="esclTaskCnt">60</label></td>
															<td><label ezfout name="esclTaskCnt">60</label></td>
															<td><label ezfout name="esclTaskCnt">60</label></td>
															<td><label ezfout name="esclTaskCnt">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="cratTaskCnt_A" ezfName="cratTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="cratTaskCnt">50</label></td>
															<td><label ezfout name="cratTaskCnt">60</label></td>
															<td><label ezfout name="cratTaskCnt">60</label></td>
															<td><label ezfout name="cratTaskCnt">60</label></td>
															<td><label ezfout name="cratTaskCnt">60</label></td>
															<td><label ezfout name="cratTaskCnt">60</label></td>
															<td><label ezfout name="cratTaskCnt">60</label></td>
															<td><label ezfout name="cratTaskCnt">60</label></td>
															<td><label ezfout name="cratTaskCnt">60</label></td>
															<td><label ezfout name="cratTaskCnt">60</label></td>
															</ezf:skip>
														</tr>
														<tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="cratTaskPerTechRate_A" ezfName="cratTaskPerTechRate_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="cratTaskPerTechRate">50</label></td>
															<td><label ezfout name="cratTaskPerTechRate">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="aftHourTaskCnt_A" ezfName="aftHourTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="aftHourTaskCnt">50</label></td>
															<td><label ezfout name="aftHourTaskCnt">60</label></td>
															<td><label ezfout name="aftHourTaskCnt">60</label></td>
															<td><label ezfout name="aftHourTaskCnt">60</label></td>
															<td><label ezfout name="aftHourTaskCnt">60</label></td>
															<td><label ezfout name="aftHourTaskCnt">60</label></td>
															<td><label ezfout name="aftHourTaskCnt">60</label></td>
															<td><label ezfout name="aftHourTaskCnt">60</label></td>
															<td><label ezfout name="aftHourTaskCnt">60</label></td>
															<td><label ezfout name="aftHourTaskCnt">60</label></td>
															</ezf:skip>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="aftHourTaskPerTechRate_A" ezfName="aftHourTaskPerTechRate_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="aftHourTaskPerTechRate">50</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="cloTaskCnt_A" ezfName="cloTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="cloTaskCnt">50</label></td>
															<td><label ezfout name="cloTaskCnt">60</label></td>
															<td><label ezfout name="cloTaskCnt">60</label></td>
															<td><label ezfout name="cloTaskCnt">60</label></td>
															<td><label ezfout name="cloTaskCnt">60</label></td>
															<td><label ezfout name="cloTaskCnt">60</label></td>
															<td><label ezfout name="cloTaskCnt">60</label></td>
															<td><label ezfout name="cloTaskCnt">60</label></td>
															<td><label ezfout name="cloTaskCnt">60</label></td>
															<td><label ezfout name="cloTaskCnt">60</label></td>
															</ezf:skip>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="cloTaskPerTechRate_A" ezfName="cloTaskPerTechRate_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="cloTaskPerTechRate">50</label></td>
															<td><label ezfout name="cloTaskPerTechRate">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="prtFailCnt_A" ezfName="prtFailCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="prtFailCnt">50</label></td>
															<td><label ezfout name="prtFailCnt">60</label></td>
															<td><label ezfout name="prtFailCnt">60</label></td>
															<td><label ezfout name="prtFailCnt">60</label></td>
															<td><label ezfout name="prtFailCnt">60</label></td>
															<td><label ezfout name="prtFailCnt">60</label></td>
															<td><label ezfout name="prtFailCnt">60</label></td>
															<td><label ezfout name="prtFailCnt">60</label></td>
															<td><label ezfout name="prtFailCnt">60</label></td>
															<td><label ezfout name="prtFailCnt">60</label></td>
															</ezf:skip>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="postEntryTaskCnt_A" ezfName="postEntryTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="postEntryTaskCnt">50</label></td>
															<td><label ezfout name="postEntryTaskCnt">60</label></td>
															<td><label ezfout name="postEntryTaskCnt">60</label></td>
															<td><label ezfout name="postEntryTaskCnt">60</label></td>
															<td><label ezfout name="postEntryTaskCnt">60</label></td>
															<td><label ezfout name="postEntryTaskCnt">60</label></td>
															<td><label ezfout name="postEntryTaskCnt">60</label></td>
															<td><label ezfout name="postEntryTaskCnt">60</label></td>
															<td><label ezfout name="postEntryTaskCnt">60</label></td>
															<td><label ezfout name="postEntryTaskCnt">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="avalTechCnt_A" ezfName="avalTechCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="avalTechCnt">50</label></td>
															<td><label ezfout name="avalTechCnt">60</label></td>
															<td><label ezfout name="avalTechCnt">60</label></td>
															<td><label ezfout name="avalTechCnt">60</label></td>
															<td><label ezfout name="avalTechCnt">60</label></td>
															<td><label ezfout name="avalTechCnt">60</label></td>
															<td><label ezfout name="avalTechCnt">60</label></td>
															<td><label ezfout name="avalTechCnt">60</label></td>
															<td><label ezfout name="avalTechCnt">60</label></td>
															<td><label ezfout name="avalTechCnt">60</label></td>
															</ezf:skip>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="xxDtTm_A1" ezfName="xxDtTm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="rspTmCustTaskRate">50</label></td>
															<td><label ezfout name="rspTmCustTaskRate">60</label></td>
															<td><label ezfout name="rspTmCustTaskRate">60</label></td>
															<td><label ezfout name="rspTmCustTaskRate">60</label></td>
															<td><label ezfout name="rspTmCustTaskRate">60</label></td>
															<td><label ezfout name="rspTmCustTaskRate">60</label></td>
															<td><label ezfout name="rspTmCustTaskRate">60</label></td>
															<td><label ezfout name="rspTmCustTaskRate">60</label></td>
															<td><label ezfout name="rspTmCustTaskRate">60</label></td>
															<td><label ezfout name="rspTmCustTaskRate">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="xxDtTm_A2" ezfName="xxDtTm_A2" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="rspTmAllTaskRate">50</label></td>
															<td><label ezfout name="rspTmAllTaskRate">60</label></td>
															<td><label ezfout name="rspTmAllTaskRate">60</label></td>
															<td><label ezfout name="rspTmAllTaskRate">60</label></td>
															<td><label ezfout name="rspTmAllTaskRate">60</label></td>
															<td><label ezfout name="rspTmAllTaskRate">60</label></td>
															<td><label ezfout name="rspTmAllTaskRate">60</label></td>
															<td><label ezfout name="rspTmAllTaskRate">60</label></td>
															<td><label ezfout name="rspTmAllTaskRate">60</label></td>
															<td><label ezfout name="rspTmAllTaskRate">60</label></td>
															</ezf:skip>
														</tr>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<%-- ## TABLE_DEFINITION ## --%>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
