<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100120073614 --%>
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
			<input type="hidden" name="pageID" value="ZZBL0030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Request Batch Status Monitor">
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
					<table width="1080" align="center">
						<tr >
							<td height="24" align="right" class="stab">Request JOBNET ID(*)</td>
							<td width="200" align="left">
								<ezf:inputText name="ezReqBusinessName" ezfName="ezReqBusinessName" otherAttr=" size=\"10\" ezftoupper=\"\""/>
							</td>
							
							<td align="right" class="stab">
								Request Status&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="left" class="stab">
								<ezf:select name="ezReqJobStatus" ezfName="ezReqJobStatus" ezfCodeName="ezReqJobStatus_DP" ezfDispName="xxBatProcTrmNm_DP" otherAttr=" width=\"20\""/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td align="right" class="stab">Registered Date From</td>
							<td align="left" class="stab">
								<ezf:inputText name="ezReqInputDate_ST" ezfName="ezReqInputDate_ST" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCal" onclick="calendar('ezReqInputDate_ST', 4);">
								Time
								<ezf:select name="ezReqInputTime_ST" ezfName="ezReqInputTime_ST" ezfCodeName="ezReqInputTime_P1" ezfDispName="xxHrsMn_P1" otherAttr=" width=\"5\""/>
							</td>
							<td align="right" class="stab">Registered Date To</td>
							<td align="left" class="stab">
								<ezf:inputText name="ezReqInputDate_EN" ezfName="ezReqInputDate_EN" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCal" onclick="calendar('ezReqInputDate_EN', 4);">
								Time
								<ezf:select name="ezReqInputTime_EN" ezfName="ezReqInputTime_EN" ezfCodeName="ezReqInputTime_P2" ezfDispName="xxHrsMn_P2" otherAttr=" width=\"5\""/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td align="right" class="stab">Processed Date From</td>
							<td align="left" class="stab">
								<ezf:inputText name="ezReqJobStartDate" ezfName="ezReqJobStartDate" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCal" onclick="calendar('ezReqJobStartDate', 4);">
								Time 
								<ezf:select name="ezReqJobStartTime" ezfName="ezReqJobStartTime" ezfCodeName="ezReqJobStartTime_P3" ezfDispName="xxHrsMn_P3" otherAttr=" width=\"5\""/>
							</td>
							<td align="right" class="stab">Processed Date To</td>
							<td align="left" class="stab">
								<ezf:inputText name="ezReqJobEndDate" ezfName="ezReqJobEndDate" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCal" onclick="calendar('ezReqJobEndDate', 4);">
								Time 
								<ezf:select name="ezReqJobEndTime" ezfName="ezReqJobEndTime" ezfCodeName="ezReqJobEndTime_P4" ezfDispName="xxHrsMn_P4" otherAttr=" width=\"5\""/>
							</td>
							<td align="right">
								<ezf:inputButton name="Search" value="Search" htmlClass="cBtn" otherAttr=" style=\"width: 100px\""/>
							</td>
						</tr>
						<tr><td colspan="6"><hr></td></tr>
						
					</table>
					<table width="1080" align="center">
						<tr align="right">
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
					
					<table width="1100" align="center" border="0" cellpadding="1" cellspacing="0">
						<tr>
							<td valign="top">
								<%-- ### MEISAI - HEAD --%>
								<div id="topTBL" style="overflow-x:hidden; overflow-y:hidden; width:1085;" onscroll="synchroScrollLeft( this.id, new Array('bottomTBL') );">
									<table border="1" cellpadding="1" cellspacing="0" width="1398">
										<col width="40px" align="center">
										<col width="100px" align="center">
										<col width="100px" align="center">
										<col width="150px" align="center">
										<col width="120px" align="center">
										<col width="140px" align="center">
										<col width="100px" align="center">
										<col width="160px" align="center">
										<col width="130px" align="center">
										<col width="160px" align="center">
										<col width="160px" align="center">
										<tr>
											<td class="pClothBs"><b>No</b></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqBusinessName_A' )"><b>JOBNET ID</b><img id="sortIMG.ezReqBusinessName_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqInputUserID_A' )"><b>User ID</b><img id="sortIMG.ezReqInputUserID_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtTm_RG' )"><b>Register Date</b><img id="sortIMG.xxDtTm_RG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqUserKeyItem_A' )"><b>User Key</b><img id="sortIMG.ezReqUserKeyItem_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqJobStatus_A' )"><b>Status</b><img id="sortIMG.ezReqJobStatus_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqJobSeqNo_A' )"><b>JOB SEQ No</b><img id="sortIMG.ezReqJobSeqNo_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtTm_ST' )"><b>JOB Start Time</b><img id="sortIMG.xxDtTm_ST" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqTransactionCount_A' )"><b>Transaction Count</b><img id="sortIMG.ezReqTransactionCount_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtTm_EN' )"><b>JOB End Time</b><img id="sortIMG.xxDtTm_EN" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezReqCondition_A' )"><b>Conditions</b><img id="sortIMG.ezReqCondition_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										</tr>
									</table>
								</div>
								
								<%-- ### MEISAI - DETAIL --%>
								
								<div id="bottomTBL" style="overflow-x:scroll; overflow-y:scroll; height:410; width:1102;" onscroll="synchroScrollLeft( this.id, new Array('topTBL') );">
									<table id="A" border="1" cellpadding="1" cellspacing="0" width="1398">
										<col width="40px" align="center">
										<col width="100px" align="center">
										<col width="100px" align="center">
										<col width="150px" align="center">
										<col width="120px" align="center">
										<col width="140px" align="center">
										<col width="100px" align="center">
										<col width="160px" align="center">
										<col width="130px" align="center">
										<col width="160px" align="center">
										<col width="160px" align="left">
										<tbody>
											<ezf:row ezfHyo="A">
												<tr>
													<td class="stab"><ezf:label name="xxRowNum_A" ezfName="xxRowNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td class="stab" ><ezf:label name="ezReqBusinessName_A" ezfName="ezReqBusinessName_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td class="stab" style="WORD-BREAK;WORD-ALL"><ezf:label name="ezReqInputUserID_A" ezfName="ezReqInputUserID_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td class="stab"><ezf:label name="xxDtTm_RG" ezfName="xxDtTm_RG" ezfHyo="A" ezfArrayNo="0" /></td>
													<td class="stab"><ezf:label name="ezReqUserKeyItem_A" ezfName="ezReqUserKeyItem_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td class="stab">
														<ezf:select name="ezReqJobStatus_A" ezfName="ezReqJobStatus_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="ezReqJobStatus_AD" ezfDispName="xxBatProcTrmNm_AD" ezfCodeDispHyo="A" otherAttr=" width=\"20\""/>
													</td>
													<td class="stab"><ezf:label name="ezReqJobSeqNo_A" ezfName="ezReqJobSeqNo_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td class="stab"><ezf:label name="xxDtTm_ST" ezfName="xxDtTm_ST" ezfHyo="A" ezfArrayNo="0" /></td>
													<td class="stab"><ezf:label name="ezReqTransactionCount_A" ezfName="ezReqTransactionCount_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td class="stab"><ezf:label name="xxDtTm_EN" ezfName="xxDtTm_EN" ezfHyo="A" ezfArrayNo="0" /></td>
													<td class="stab"><ezf:label name="ezReqCondition_A" ezfName="ezReqCondition_A" ezfHyo="A" ezfArrayNo="0" /></td>
												</tr>
											</ezf:row>
										</tbody>
										<ezf:skip>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" ></label>XXX</td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqTransactionCount_A" ezfname="ezReqTransactionCount_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" ></label>XXX</td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqTransactionCount_A" ezfname="ezReqTransactionCount_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" ></label>XXX</td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqTransactionCount_A" ezfname="ezReqTransactionCount_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" ></label>XXX</td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqTransactionCount_A" ezfname="ezReqTransactionCount_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" ></label>XXX</td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqTransactionCount_A" ezfname="ezReqTransactionCount_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" ></label>XXX</td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqTransactionCount_A" ezfname="ezReqTransactionCount_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" ></label>XXX</td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqTransactionCount_A" ezfname="ezReqTransactionCount_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" ></label>XXX</td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqTransactionCount_A" ezfname="ezReqTransactionCount_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout name="xxRowNum_A" ezfname="xxRowNum_A" ezfhyo="A" >1</label></td>
												<td class="stab" ><label ezfout name="ezReqBusinessName_A" ezfname="ezReqBusinessName_A" ezfhyo="A" ></label>XXX</td>
												<td class="stab" style="WORD-BREAK;WORD-ALL"><label ezfout name="ezReqInputUserID_A" ezfname="ezReqInputUserID_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_RG" ezfname="xxDtTm_RG" ezfhyo="A">MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqUserKeyItem_A" ezfname="ezReqUserKeyItem_A" ezfhyo="A" >XXXXXX</label></td>
												<td class="stab">
													<select name="ezReqJobStatus_A" width="20" ezfhyo="A" ezfname="ezReqJobStatus_A" ezflist="ezReqJobStatus_AD,xxBatProcTrmNm_AD,5,A,noblank">
														<option>Not Started</option>
													</select>
												</td>
												<td class="stab"><label ezfout name="ezReqJobSeqNo_A" ezfname="ezReqJobSeqNo_A" ezfhyo="A" >XXXXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_ST" ezfname="xxDtTm_ST" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqTransactionCount_A" ezfname="ezReqTransactionCount_A" ezfhyo="A" >XXX</label></td>
												<td class="stab"><label ezfout name="xxDtTm_EN" ezfname="xxDtTm_EN" ezfhyo="A" >MM/DD/YYYY HH:mm:ss</label></td>
												<td class="stab"><label ezfout name="ezReqCondition_A" ezfname="ezReqCondition_A" ezfhyo="A" >XXXX</label></td>
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
