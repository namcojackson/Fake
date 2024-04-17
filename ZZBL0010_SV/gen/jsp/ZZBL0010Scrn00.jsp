<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20140623231211 --%>
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
			<input type="hidden" name="pageID" value="ZZBL0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Batch Process Log Monitor">
<center>

	<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<!-- <table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
					<tr>
						<td width="1100px" height="28px" valign="bottom">
								<div>
									<table border="0" cellpadding="0" cellspacing="0">
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
												<tr title='Order Entry'>
													<td width="107px" align="center" class="same">
														Archive View
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Hold Release'>
													<td width="90px" align="center" class="disabled">
														Hld Rlse
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Soft Allocation'>
													<td width="90px" align="center" class="disabled">
														Soft Alloc
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Hard Allocation'>
													<td width="90px" align="center" class="disabled">
														Hard Alloc
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Order Inquiry by Status'>
													<td width="90px" align="center" class="disabled">
														Ordr Inq
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Sales Summary'>
													<td width="90px" align="center" class="disabled">
														Sales Sum
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='ATP Inquiry by Order'>
													<td width="90px" align="center" class="disabled">
														ATP Inq
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='iW Remote Upload'>
													<td width="90px" align="center" class="disabled">
														iW Rmt Upld
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='iW Remote Inquiry'>
													<td width="90px" align="center" class="disabled">
														iW Rmt Inq
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Upload Screen for LAN Data from Canon Inc.'>
													<td width="90px" align="center" class="disabled">
														LAN Upld
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
									</table>
								</div>
						</td>
						<td valign="bottom" align="center">
								<a href="#" id="PrevPageTabIndex"><img id="PrevPageBtn" src="./img/tab/tabbackbutton.png" alt="Prev" border="0"></a>
						</td>
						<td valign="bottom" align="center">
							<a href="#" id="NextPageTabIndex"><img id="NextPageBtn" src="./img/tab/tabnextbutton.png" alt="Next" border="0"></a>
						</td>
					</tr>
				</table> 	-->
				<div class="pTab_BODY">


						<table width="1100px" align="center">
							<tbody>
								<tr>
									<td height="24" class="stab" align="left"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor></td>
									<td width="150" align="left">
										<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/>
									</td>
									<td height="24" class="stab" align="left">Batch Process Key</td>
									<td width="150" align="left">
										<ezf:inputText name="batProcLogPk" ezfName="batProcLogPk" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
									</td>
									<td height="24" class="stab" align="left">Subsystem CD</td>
									<td width="150" align="left">
										<ezf:inputText name="batProcSubSysCd" ezfName="batProcSubSysCd" otherAttr=" size=\"10\" ezftoupper=\"\""/>
									</td>
									<td></td>
								</tr>
								<tr>
									<td height="24" class="stab" align="left">JOB Net ID</td>
									<td width="150" align="left">
										<ezf:inputText name="batProcJbntId" ezfName="batProcJbntId" otherAttr=" size=\"12\" ezftoupper=\"\""/>
									</td>
									<td height="24" class="stab" align="left">Job ID</td>
									<td width="150" align="left">
										<ezf:inputText name="batProcJobId" ezfName="batProcJobId" otherAttr=" size=\"10\" ezftoupper=\"\""/>
									</td>
									<td height="24" class="stab" align="left">Program ID</td>
									<td width="150" align="left">
										<ezf:inputText name="batProcPgmId" ezfName="batProcPgmId" otherAttr=" size=\"10\" ezftoupper=\"\""/>
									</td>
								</tr>

								<tr>
									<td class="stab" align="left">
										Status
									</td>
									<td align="left" >
										<ezf:select name="batProcTrmCd" ezfName="batProcTrmCd" ezfCodeName="batProcTrmCd_DP" ezfDispName="xxBatProcTrmNm_DP" otherAttr=" width=\"20\""/>
									</td>

									<td class="stab" align="left">Start Date</td>
									<td align="left">
										<ezf:inputText name="effFromDt" ezfName="effFromDt" otherAttr=" size=\"10\" maxlength=\"12\""/>
										<img src="./img/calendar.gif" class="pCal" onclick="calendar('effFromDt', 4);">
									</td>

									<td class="stab" align="left">End Date</td>
									<td align="left">
										<ezf:inputText name="effToDt" ezfName="effToDt" otherAttr=" size=\"10\" maxlength=\"12\""/>
										<img src="./img/calendar.gif" class="pCal" onclick="calendar('effToDt', 4);">
									</td>
									<td align="right">
										<ezf:inputButton name="Search" value="Search" htmlClass="cBtn" otherAttr=" style=\"width: 100px\""/>
									</td>
								</tr>
								<tr>
									<td colspan="7" ><hr></td>
								</tr>
							</tbody>
						</table>

				<table width="1105px">
					<tr align="right" >
						<td>

							<!-- <table border="0" cellpadding="1" cellspacing="0">
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
									<td class="pOut">10</td>
									<td class="stab">of</td>
									<td class="pOut">200</td>
									<td>&nbsp;</td>
									<td><input type="button" class="PagePrev" value="Prev" name="PagePrev" disabled></td>
									<td></td>
									<td><input type="button" class="PageNext" value="Next" name="PageNext"></td>
								</tr>
							</table> -->

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

						<table align="center" cellpadding="0" cellspacing="0" border="0" style="Width:1000px;">
							<tr>
								<td valign="top">

									<%-- ### MEISAI - HEAD --%>
									<div id="topTBL" style="overflow-x:hidden; width:1084; overflow-y:none;">
										<table align="center" cellpadding="1" cellspacing="0" border="1" width="3270">
											<col width="60" align="center">
											<col width="100" align="center">
											<col width="130" align="center">
											<col width="130" align="center">
											<col width="130" align="center">
											<col width="130" align="center">
											<col width="90" align="center">
											<col width="100" align="center">
											<col width="180" align="center">
											<col width="180" align="center">
											<col width="110" align="center">
											<col width="180" align="center">
											<col width="110" align="center">
											<col width="120" align="center">
											<col width="130" align="center">
											<col width="100" align="center">
											<col width="100" align="center">
											<col width="100" align="center">
											<col width="100" align="center">
											<col width="">
											<tr align="center">
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><b>No</b></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcLogPk_A' )"><b>Process Key</b><img id="sortIMG.batProcLogPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'glblCmpyCd_A' )"><b>Global Company CD</b><img id="sortIMG.glblCmpyCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcSubSysCd_A' )"><b>Subsystem CD </b><img id="sortIMG.batProcSubSysCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcJbntId_A' )"><b>JOB NET ID</b><img id="sortIMG.batProcJbntId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcJobId_A' )"><b>JOB ID</b><img id="sortIMG.batProcJobId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcPgmId_A' )"><b>Program ID</b><img id="sortIMG.batProcPgmId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxBatProcTrmNm_A' )"><b>Status</b><img id="sortIMG.xxBatProcTrmNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcHostNm_A' )"><b>Host Name</b><img id="sortIMG.batProcHostNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtTm_ST' )"><b>Start Time</b><img id="sortIMG.xxDtTm_ST" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcStartTz_A' )"><b>Start Time Zone</b><img id="sortIMG.batProcStartTz_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtTm_EN' )"><b>End Time</b><img id="sortIMG.xxDtTm_EN" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcEndTz_A' )"><b>End Time Zone</b><img id="sortIMG.batProcEndTz_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcTmCnt_A' )"><b>Execution Time</b><img id="sortIMG.batProcTmCnt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcNormRecCnt_A' )"><b>Normal Records</b><img id="sortIMG.batProcNormRecCnt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcErrRecCnt_A' )"><b>Error Records</b><img id="sortIMG.batProcErrRecCnt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcTotRecCnt_A' )"><b>Total Records</b><img id="sortIMG.batProcTotRecCnt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcDeadLockRtryCnt_A' )"><b>Dead Lock</b><img id="sortIMG.batProcDeadLockRtryCnt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcRecLockRtryCnt_A' )"><b>Record Lock</b><img id="sortIMG.batProcRecLockRtryCnt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs" style="WORD-BREAK;WORD-ALL" ><a href="#" class="pSortCol" onclick="columnSort( 'A', 'batProcJobLogPathNm_A' )"><b>Job Log File Path</b><img id="sortIMG.batProcJobLogPathNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											</tr>
										</table>
									</div>

									<%-- ### MEISAI - DETAIL --%>

									<div id="bottomTBL" style="overflow:scroll; width:1101; height:400;" onscroll="synchroBottomScroll();">
										<table ID="A" cellpadding="1" cellspacing="0" border="1" width="3270">
											<col width="60" align="center">
											<col width="100" align="center">
											<col width="130" align="center">
											<col width="130" align="center">
											<col width="130" align="center">
											<col width="130" align="center">
											<col width="90" align="center">
											<col width="100" align="center">
											<col width="180" align="center">
											<col width="180" align="center">
											<col width="110" align="center">
											<col width="180" align="center">
											<col width="110" align="center">
											<col width="120" align="center">
											<col width="130" align="center">
											<col width="100" align="center">
											<col width="100" align="center">
											<col width="100" align="center">
											<col width="100" align="center">
											<col width="">
											<ezf:row ezfHyo="A">
												<tr>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="xxRowNum_A" ezfName="xxRowNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcLogPk_A" ezfName="batProcLogPk_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="glblCmpyCd_A" ezfName="glblCmpyCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcSubSysCd_A" ezfName="batProcSubSysCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcJbntId_A" ezfName="batProcJbntId_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcJobId_A" ezfName="batProcJobId_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcPgmId_A" ezfName="batProcPgmId_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="xxBatProcTrmNm_A" ezfName="xxBatProcTrmNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcHostNm_A" ezfName="batProcHostNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="xxDtTm_ST" ezfName="xxDtTm_ST" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcStartTz_A" ezfName="batProcStartTz_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="xxDtTm_EN" ezfName="xxDtTm_EN" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcEndTz_A" ezfName="batProcEndTz_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcTmCnt_A" ezfName="batProcTmCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcNormRecCnt_A" ezfName="batProcNormRecCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcErrRecCnt_A" ezfName="batProcErrRecCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcTotRecCnt_A" ezfName="batProcTotRecCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcDeadLockRtryCnt_A" ezfName="batProcDeadLockRtryCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="center"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcRecLockRtryCnt_A" ezfName="batProcRecLockRtryCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td align="left"  class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="batProcJobLogPathNm_A" ezfName="batProcJobLogPathNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
												</tr>
											</ezf:row>
											<ezf:skip>
											</ezf:skip>
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
	function synchroBottomScroll() {
		var bottomTBL = document.getElementById( 'bottomTBL' );
		var topTBL    = document.getElementById( 'topTBL'    );
		topTBL.scrollLeft = bottomTBL.scrollLeft;
	}
</script>

<%-- **** Task specific area ends here **** --%>
