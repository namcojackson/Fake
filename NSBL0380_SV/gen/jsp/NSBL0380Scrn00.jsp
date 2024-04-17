<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160322172436 --%>
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
			<input type="hidden" name="pageID" value="NSBL0380Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="History Level Report">
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
										<li title="History Level Report" class="pTab_ON"><a href="#">Hist Lvl Rpt</a></li>
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
										<td><ezf:inputText name="xxScrItem7Txt_H" ezfName="xxScrItem7Txt_H" value="03/2016" otherAttr=" size=\"12\""/></td>
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
						<col width="300">	<!-- History Level -->
						<col width="700">	<!-- Paging -->
						<tr>
							<td class="stab">History Level</td>
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
											<div id="Right1TBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:170; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" id="" width="170">
													<ezf:row ezfHyo="B">
													<col align="center" width="170">
													</ezf:row>
													<tr height="32" valign="middle">
														<ezf:row ezfHyo="B">
														<td class="pClothBs"><font color="#ffffff">Average</font></a></td>
														</ezf:row>
													</tr>
												</table>
											</div>
											<!-- Right TBL -->
											<div id="Right1TBL" style="overflow-x:hidden; overflow-y:hidden; width:170; height:430; float:left;" >
												<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed" width="170">
													<ezf:row ezfHyo="B">
													<col width="170">
													</ezf:row>
														<tr height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_B1" ezfName="xxDtlAmt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_B2" ezfName="xxDtlAmt_B2" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_B3" ezfName="xxDtlAmt_B3" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_B4" ezfName="xxDtlAmt_B4" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_B5" ezfName="xxDtlAmt_B5" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr>
														<tr  height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_B6" ezfName="xxDtlAmt_B6" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_B7" ezfName="xxDtlAmt_B7" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr>
														<tr  height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_B8" ezfName="xxDtlAmt_B8" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_B9" ezfName="xxDtlAmt_B9" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_BA" ezfName="xxDtlAmt_BA" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_BB" ezfName="xxDtlAmt_BB" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_BC" ezfName="xxDtlAmt_BC" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_BD" ezfName="xxDtlAmt_BD" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_BE" ezfName="xxDtlAmt_BE" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtlAmt_BF" ezfName="xxDtlAmt_BF" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtTm_B1" ezfName="xxDtTm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="B">
															<td><ezf:label name="xxDtTm_B2" ezfName="xxDtTm_B2" ezfHyo="B" ezfArrayNo="0" /></td>
															</ezf:row>
														</tr>
												</table>
											</div>
										</td>
										<!-- Right -->
										<td align="left" valign="top">
											<!-- Right TBL Top -->
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:730;height:34; float:left;" onScroll="synchroScrollLeft(this.id, new Array('RightTBL'));">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" id="" width="100">
													<ezf:row ezfHyo="A">
													<col align="center" width="100">
													</ezf:row>
													<ezf:skip>
													<col align="center" width="100">
													<col align="center" width="100">
													<col align="center" width="100">
													<col align="center" width="100">
													<col align="center" width="100">
													<col align="center" width="100">
													<col align="center" width="100">
													<col align="center" width="100">
													<col align="center" width="100">
													<col align="center" width="100">
													</ezf:skip>
													<tr height="32" valign="middle">
														<ezf:row ezfHyo="A">
														<td class="pClothBs"><font color="#ffffff"><ezf:label name="cratDt_A" ezfName="cratDt_A" ezfHyo="A" ezfArrayNo="0" /></a></td>
														</ezf:row>
														<ezf:skip>
															<td><label ezfout name="cratDt_A">03/02/2016</label></td>
															<td><label ezfout name="cratDt_A">03/02/2016</label></td>
															<td><label ezfout name="cratDt_A">03/02/2016</label></td>
															<td><label ezfout name="cratDt_A">03/02/2016</label></td>
															<td><label ezfout name="cratDt_A">03/02/2016</label></td>
															<td><label ezfout name="cratDt_A">03/02/2016</label></td>
															<td><label ezfout name="cratDt_A">03/02/2016</label></td>
															<td><label ezfout name="cratDt_A">03/02/2016</label></td>
															<td><label ezfout name="cratDt_A">03/02/2016</label></td>
															<td><label ezfout name="cratDt_A">03/02/2016</label></td>
														</ezf:skip>
													</tr>
													
												</table>
											</div>
											<!-- Right TBL -->
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:hidden; width:730; height:428; float:left;" onScroll="synchroScrollLeft(this.id, new Array('RightTBL_Top')); synchroScrollTop(this.id, new Array('LeftTBL'));">
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed" width="100">
													<ezf:row ezfHyo="A">
													<col width="100">
													</ezf:row>
													<ezf:skip>
													<col width="100">
													<col width="100">
													<col width="100">
													<col width="100">
													<col width="100">
													<col width="100">
													<col width="100">
													<col width="100">
													<col width="100">
													<col width="100">
													</ezf:skip>
														<tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="totInProcTaskCnt_A" ezfName="totInProcTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="totInProcTaskCnt_A" ezfHyo="A" ezfname="totInProcTaskCnt_A">6,789,012,345</label></td>
															<td><label ezfout name="totInProcTaskCnt_A" ezfHyo="A" ezfname="totInProcTaskCnt_A">60</label></td>
															<td><label ezfout name="totInProcTaskCnt_A" ezfHyo="A" ezfname="totInProcTaskCnt_A">60</label></td>
															<td><label ezfout name="totInProcTaskCnt_A" ezfHyo="A" ezfname="totInProcTaskCnt_A">60</label></td>
															<td><label ezfout name="totInProcTaskCnt_A" ezfHyo="A" ezfname="totInProcTaskCnt_A">60</label></td>
															<td><label ezfout name="totInProcTaskCnt_A" ezfHyo="A" ezfname="totInProcTaskCnt_A">60</label></td>
															<td><label ezfout name="totInProcTaskCnt_A" ezfHyo="A" ezfname="totInProcTaskCnt_A">60</label></td>
															<td><label ezfout name="totInProcTaskCnt_A" ezfHyo="A" ezfname="totInProcTaskCnt_A">60</label></td>
															<td><label ezfout name="totInProcTaskCnt_A" ezfHyo="A" ezfname="totInProcTaskCnt_A">60</label></td>
															<td><label ezfout name="totInProcTaskCnt_A" ezfHyo="A" ezfname="totInProcTaskCnt_A">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="prtWaitTaskCnt_A" ezfName="prtWaitTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="prtWaitTaskCnt_A">50</label></td>
															<td><label ezfout name="prtWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="prtWaitTaskCnt_A">60</label></td>
															</ezf:skip>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="spclWaitTaskCnt_A" ezfName="spclWaitTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="spclWaitTaskCnt_A">50</label></td>
															<td><label ezfout name="spclWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt_A">60</label></td>
															<td><label ezfout name="spclWaitTaskCnt_A">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="othOpenTaskCnt_A" ezfName="othOpenTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="othOpenTaskCnt_A">50</label></td>
															<td><label ezfout name="othOpenTaskCnt_A">60</label></td>
															<td><label ezfout name="othOpenTaskCnt_A">60</label></td>
															<td><label ezfout name="othOpenTaskCnt_A">60</label></td>
															<td><label ezfout name="othOpenTaskCnt_A">60</label></td>
															<td><label ezfout name="othOpenTaskCnt_A">60</label></td>
															<td><label ezfout name="othOpenTaskCnt_A">60</label></td>
															<td><label ezfout name="othOpenTaskCnt_A">60</label></td>
															<td><label ezfout name="othOpenTaskCnt_A">60</label></td>
															<td><label ezfout name="othOpenTaskCnt_A">60</label></td>
															</ezf:skip>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="custTaskCnt_A" ezfName="custTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="custTaskCnt_A">50</label></td>
															<td><label ezfout name="custTaskCnt_A">60</label></td>
															<td><label ezfout name="custTaskCnt_A">60</label></td>
															<td><label ezfout name="custTaskCnt_A">60</label></td>
															<td><label ezfout name="custTaskCnt_A">60</label></td>
															<td><label ezfout name="custTaskCnt_A">60</label></td>
															<td><label ezfout name="custTaskCnt_A">60</label></td>
															<td><label ezfout name="custTaskCnt_A">60</label></td>
															<td><label ezfout name="custTaskCnt_A">60</label></td>
															<td><label ezfout name="custTaskCnt_A">60</label></td>
															</ezf:skip>
														</tr>
														<tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="esclTaskCnt_A" ezfName="esclTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="esclTaskCnt_A">50</label></td>
															<td><label ezfout name="esclTaskCnt_A">60</label></td>
															<td><label ezfout name="esclTaskCnt_A">60</label></td>
															<td><label ezfout name="esclTaskCnt_A">60</label></td>
															<td><label ezfout name="esclTaskCnt_A">60</label></td>
															<td><label ezfout name="esclTaskCnt_A">60</label></td>
															<td><label ezfout name="esclTaskCnt_A">60</label></td>
															<td><label ezfout name="esclTaskCnt_A">60</label></td>
															<td><label ezfout name="esclTaskCnt_A">60</label></td>
															<td><label ezfout name="esclTaskCnt_A">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="cratTaskCnt_A" ezfName="cratTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="cratTaskCnt_A">50</label></td>
															<td><label ezfout name="cratTaskCnt_A">60</label></td>
															<td><label ezfout name="cratTaskCnt_A">60</label></td>
															<td><label ezfout name="cratTaskCnt_A">60</label></td>
															<td><label ezfout name="cratTaskCnt_A">60</label></td>
															<td><label ezfout name="cratTaskCnt_A">60</label></td>
															<td><label ezfout name="cratTaskCnt_A">60</label></td>
															<td><label ezfout name="cratTaskCnt_A">60</label></td>
															<td><label ezfout name="cratTaskCnt_A">60</label></td>
															<td><label ezfout name="cratTaskCnt_A">60</label></td>
															</ezf:skip>
														</tr>
														<tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="cratTaskPerTechRate_A" ezfName="cratTaskPerTechRate_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="cratTaskPerTechRate_A">50</label></td>
															<td><label ezfout name="cratTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cratTaskPerTechRate_A">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="aftHourTaskCnt_A" ezfName="aftHourTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="aftHourTaskCnt_A">50</label></td>
															<td><label ezfout name="aftHourTaskCnt_A">60</label></td>
															<td><label ezfout name="aftHourTaskCnt_A">60</label></td>
															<td><label ezfout name="aftHourTaskCnt_A">60</label></td>
															<td><label ezfout name="aftHourTaskCnt_A">60</label></td>
															<td><label ezfout name="aftHourTaskCnt_A">60</label></td>
															<td><label ezfout name="aftHourTaskCnt_A">60</label></td>
															<td><label ezfout name="aftHourTaskCnt_A">60</label></td>
															<td><label ezfout name="aftHourTaskCnt_A">60</label></td>
															<td><label ezfout name="aftHourTaskCnt_A">60</label></td>
															</ezf:skip>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="aftHourTaskPerTechRate_A" ezfName="aftHourTaskPerTechRate_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="aftHourTaskPerTechRate_A">50</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="aftHourTaskPerTechRate_A">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="cloTaskCnt_A" ezfName="cloTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="cloTaskCnt_A">50</label></td>
															<td><label ezfout name="cloTaskCnt_A">60</label></td>
															<td><label ezfout name="cloTaskCnt_A">60</label></td>
															<td><label ezfout name="cloTaskCnt_A">60</label></td>
															<td><label ezfout name="cloTaskCnt_A">60</label></td>
															<td><label ezfout name="cloTaskCnt_A">60</label></td>
															<td><label ezfout name="cloTaskCnt_A">60</label></td>
															<td><label ezfout name="cloTaskCnt_A">60</label></td>
															<td><label ezfout name="cloTaskCnt_A">60</label></td>
															<td><label ezfout name="cloTaskCnt_A">60</label></td>
															</ezf:skip>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="cloTaskPerTechRate_A" ezfName="cloTaskPerTechRate_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="cloTaskPerTechRate_A">50</label></td>
															<td><label ezfout name="cloTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate_A">60</label></td>
															<td><label ezfout name="cloTaskPerTechRate_A">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="prtFailCnt_A" ezfName="prtFailCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="prtFailCnt_A">50</label></td>
															<td><label ezfout name="prtFailCnt_A">60</label></td>
															<td><label ezfout name="prtFailCnt_A">60</label></td>
															<td><label ezfout name="prtFailCnt_A">60</label></td>
															<td><label ezfout name="prtFailCnt_A">60</label></td>
															<td><label ezfout name="prtFailCnt_A">60</label></td>
															<td><label ezfout name="prtFailCnt_A">60</label></td>
															<td><label ezfout name="prtFailCnt_A">60</label></td>
															<td><label ezfout name="prtFailCnt_A">60</label></td>
															<td><label ezfout name="prtFailCnt_A">60</label></td>
															</ezf:skip>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="postEntryTaskCnt_A" ezfName="postEntryTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="postEntryTaskCnt_A">50</label></td>
															<td><label ezfout name="postEntryTaskCnt_A">60</label></td>
															<td><label ezfout name="postEntryTaskCnt_A">60</label></td>
															<td><label ezfout name="postEntryTaskCnt_A">60</label></td>
															<td><label ezfout name="postEntryTaskCnt_A">60</label></td>
															<td><label ezfout name="postEntryTaskCnt_A">60</label></td>
															<td><label ezfout name="postEntryTaskCnt_A">60</label></td>
															<td><label ezfout name="postEntryTaskCnt_A">60</label></td>
															<td><label ezfout name="postEntryTaskCnt_A">60</label></td>
															<td><label ezfout name="postEntryTaskCnt_A">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="avalTechCnt_A" ezfName="avalTechCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="avalTechCnt_A">50</label></td>
															<td><label ezfout name="avalTechCnt_A">60</label></td>
															<td><label ezfout name="avalTechCnt_A">60</label></td>
															<td><label ezfout name="avalTechCnt_A">60</label></td>
															<td><label ezfout name="avalTechCnt_A">60</label></td>
															<td><label ezfout name="avalTechCnt_A">60</label></td>
															<td><label ezfout name="avalTechCnt_A">60</label></td>
															<td><label ezfout name="avalTechCnt_A">60</label></td>
															<td><label ezfout name="avalTechCnt_A">60</label></td>
															<td><label ezfout name="avalTechCnt_A">60</label></td>
															</ezf:skip>
														</tr><tr  height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="xxDtTm_A1" ezfName="xxDtTm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="xxDtTm_A1">50</label></td>
															<td><label ezfout name="xxDtTm_A1">60</label></td>
															<td><label ezfout name="xxDtTm_A1">60</label></td>
															<td><label ezfout name="xxDtTm_A1">60</label></td>
															<td><label ezfout name="xxDtTm_A1">60</label></td>
															<td><label ezfout name="xxDtTm_A1">60</label></td>
															<td><label ezfout name="xxDtTm_A1">60</label></td>
															<td><label ezfout name="xxDtTm_A1">60</label></td>
															<td><label ezfout name="xxDtTm_A1">60</label></td>
															<td><label ezfout name="xxDtTm_A1">60</label></td>
															</ezf:skip>
														</tr>
														<tr height="24" align="right">
															<ezf:row ezfHyo="A">
															<td><ezf:label name="xxDtTm_A2" ezfName="xxDtTm_A2" ezfHyo="A" ezfArrayNo="0" /></td>
															</ezf:row>
															<ezf:skip>
															<td><label ezfout name="xxDtTm_A2">50</label></td>
															<td><label ezfout name="xxDtTm_A2">60</label></td>
															<td><label ezfout name="xxDtTm_A2">60</label></td>
															<td><label ezfout name="xxDtTm_A2">60</label></td>
															<td><label ezfout name="xxDtTm_A2">60</label></td>
															<td><label ezfout name="xxDtTm_A2">60</label></td>
															<td><label ezfout name="xxDtTm_A2">60</label></td>
															<td><label ezfout name="xxDtTm_A2">60</label></td>
															<td><label ezfout name="xxDtTm_A2">60</label></td>
															<td><label ezfout name="xxDtTm_A2">60</label></td>
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
