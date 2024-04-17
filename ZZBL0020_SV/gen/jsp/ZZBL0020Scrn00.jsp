<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100115044952 --%>
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
			<input type="hidden" name="pageID" value="ZZBL0020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Request Control Master Monitor">
<center>
	<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
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
					<table width="100%" align="center">
						<tbody>
							<tr>
								<td height="24" align="right" class="stab">Request JOB NET ID(*)</td>
								<td width="150" align="left">
									<ezf:inputText name="ezReqBusinessID" ezfName="ezReqBusinessID" otherAttr=" size=\"20\" ezftoupper=\"\""/>
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td height="24" align="right" class="stab">Request Control JOB NET ID(*)</td>
								<td width="150" align="left">
									<ezf:inputText name="ezReqJobCtlNetID" ezfName="ezReqJobCtlNetID" otherAttr=" size=\"20\" ezftoupper=\"\""/>
								</td>
								<td align="right">
									<ezf:inputButton name="Search" value="Search" htmlClass="cBtn" otherAttr=" style=\"width: 100px\""/>
								</td>
								<td align="right" width="57">
								</td>
							</tr>
							<tr>
								<td colspan="6" ><hr width="90%"></td>
							<tr>
							</tr>
						</tbody>
					</table>

					<table width="1015" align="center">
						<tr align="right">
							<td align="left" >
									<ezf:inputButton name="Add" value="Add" htmlClass="cBtn" otherAttr=" style=\"width: 100px\""/>
							</td>
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
										<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
										<td></td>
										<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
									</tr>
								</table>		-->					
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

					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td valign="top">
								<div id="topTBL">
									<table align="center" cellpadding="1" cellspacing="0" border="1" width="1010">
										<col width="20" align="center">
										<col width="40" align="center">
										<col width="100" align="center">
										<col width="200" align="center">
										<col width="100" align="center">
										<col width="100" align="center">
										<col width="120" align="center">
										<col width="100" align="center">
										<col width="90" align="center">
										<col width="" align="center">
										<tr>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxYesNoCd_A' )" ><img id="sortIMG.xxYesNoCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>&nbsp;</td>
											<td align="center" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxRowNum_A' )"><img id="sortIMG.xxRowNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a><b>No</b></td>
											<td align="center" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqBusinessID_A' )"><b>JOBNET ID</b><img id="sortIMG.ezReqBusinessID_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td align="center" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqBusinessName_A' )"><b>JOB NET Name</b><img id="sortIMG.ezReqBusinessName_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td align="center" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqJobCtlNetID_A' )"><b>Request Control<br> JOB NET ID</b><img id="sortIMG.ezReqJobCtlNetID_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td align="center" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqJobConcurrency_A' )"><b>Concurrency</b><img id="sortIMG.ezReqJobConcurrency_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td align="center" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqJobStopFlag_A' )"><b>Job Blocking Flag</b><img id="sortIMG.ezReqJobStopFlag_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td align="center" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqJobErrorCtlFlag_A' )"><b>Job Error Flag</b><img id="sortIMG.ezReqJobErrorCtlFlag_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td align="center" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqCountPerJob_A' )"><b>Max Counter</b><img id="sortIMG.ezReqCountPerJob_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs">&nbsp;</td>
										</tr>
										</tr>
									</table>
								</div>
								
								<%-- ### MEISAI - DETAIL --%>
								
								<div id="bottomTBL" style="overflow-y:scroll; width:1088; height:430; overflow-x:none" onscroll="synchroBottomScroll();">
									<table ID="A" align="right" border="1" cellpadding="1" cellspacing="0" width="1010">
										<col width="20" align="center">
										<col width="40" align="center">
										<col width="100" align="center">
										<col width="200" align="left">
										<col width="100" align="center">
										<col width="100" align="center">
										<col width="120" align="center">
										<col width="100" align="center">
										<col width="90" align="center">
										<col width="" align="center">
										<ezf:row ezfHyo="A">
											<tr>
												<td><ezf:inputCheckBox name="xxYesNoCd_A" ezfName="xxYesNoCd_A" value="3" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxRowNum_A" ezfName="xxRowNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="ezReqBusinessID_A" ezfName="ezReqBusinessID_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td style="WORD-BREAK:BREAK-ALL"><ezf:label name="ezReqBusinessName_A" ezfName="ezReqBusinessName_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="ezReqJobCtlNetID_A" ezfName="ezReqJobCtlNetID_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="ezReqJobConcurrency_A" ezfName="ezReqJobConcurrency_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxJobBlockFlgNm_A1" ezfName="xxJobBlockFlgNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxJobErrCtrlFlgNm_A2" ezfName="xxJobErrCtrlFlgNm_A2" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="ezReqCountPerJob_A" ezfName="ezReqCountPerJob_A" ezfHyo="A" ezfArrayNo="0" /></td>
												
												<td align="center">
													<ezf:inputButton name="Edit" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="cBtn" />
												</td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr>
												<td><input type="checkbox" name="xxYesNoCd_A" ezfname="xxYesNoCd_A" ezfHyo="A" value="3"></td>
												<td><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfHyo="A">XXX</label></td>
												<td><label ezfout name="ezReqBusinessID_A" ezfname="ezReqBusinessID_A" ezfHyo="A">XXX</label></td>
												<td style="WORD-BREAK:BREAK-ALL"><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfHyo="A">XXX</label></td>
												<td><label ezfout name="ezReqJobCtlNetID_A" ezfname="ezReqJobCtlNetID_A" ezfHyo="A">XXX</label></td>
												<td><label ezfout name="ezReqJobConcurrency_A" ezfname="ezReqJobConcurrency_A" ezfHyo="A">XXX</label></td>
												<td><label ezfout name="xxJobBlockFlgNm_A1" ezfname="xxJobBlockFlgNm_A1" ezfHyo="A">XXX</label></td>
												<td><label ezfout name="xxJobErrCtrlFlgNm_A2" ezfname="xxJobErrCtrlFlgNm_A2" ezfHyo="A">XXX</label></td>
												<td><label ezfout name="ezReqCountPerJob_A" ezfname="ezReqCountPerJob_A" ezfHyo="A" >XXX</label></td>
												
												<td align="center">
													<input type="button" class="cBtn" name="Edit" value="Edit" onclick="sendServer(this)" ezfHyo="A">
												</td>
											</tr>
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
