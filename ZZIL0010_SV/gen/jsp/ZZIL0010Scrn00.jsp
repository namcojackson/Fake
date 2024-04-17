<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100127092015 --%>
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
			<input type="hidden" name="pageID" value="ZZIL0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Interface Transaction Record Monitor">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
			<!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
			<!--
			<div class="pTab_HEAD">
				<ul>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="96%">
								<div>
									<li title="Order Entry"				 class="pTab_ON" ><a href="#">Order Entry</a></li>
									<li title="Order Upload"			 class="pTab_OFF"><a href="#">Order Upload</a></li>
									<li title="Trial/Loan Request Entry" class="pTab_OFF"><a href="#">Trial/Loan Request Entry</a></li>
									<li title="Asset Management"		 class="pTab_OFF"><a href="#">Asset Management</a></li>
									<li title="Hard Allocation"			 class="pTab_OFF"><a href="#">Hard Allocation</a></li>
									<li title="Hold Release"			 class="pTab_OFF"><a href="#">Hold Release</a></li>
									<li title="Credit Order Release"	 class="pTab_OFF"><a href="#">Credit Order Release</a></li>
									<li title="TOP STOP Release" 		 class="pTab_OFF"><a href="#">TOP STOP Release</a></li>
									<li title="Export Lisence Entry"	 class="pTab_OFF"><a href="#">Export Lisence Entry</a></li>
									<li title="Disposition Order"		 class="pTab_OFF"><a href="#">Disposition Order</a></li>
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
			-->
			<div class="pTab_BODY">
<!-- **************************************** HEADER **************************************** -->
				<table border="0" cellpadding="1" cellspacing="0" align="center">
					<tr>
						<td>
							<!-- ********* 1 ********* -->
							<table border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0">
											<col width="72">
											<col width="240">
											<col width="5">
											<col width="86">
											<col width="240">
											<col width="5">
											<col width="92">
											<col width="123">
											<col width="">

											<tr>
												<td class="stab">Interface ID</td>
												<td><ezf:inputText name="interfaceId" ezfName="interfaceId" value="123456789012345678901234567890" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
												<td/>
												<td class="stab">Transaction ID</td>
												<td><ezf:inputText name="transactionId" ezfName="transactionId" value="123456789012345678901234567890" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
												<td/>
												<td class="stab">Processed Flag</td>
												<td>
													<ezf:select name="processedFlag_PS" ezfName="processedFlag_PS" ezfBlank="1" ezfCodeName="processedFlag_PC" ezfDispName="xxProcFlgNm_PC" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<!-- ********* 1 ********* -->
							<!-- ********* 2 ********* -->
							<table border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0">
											<col width="120">
											<col width="111">
											<col width="5">
											<col width="30">
											<col width="75">
											<col width="20">
											<col width="102">
											<col width="111">
											<col width="5">
											<col width="30">
											<col width="75">
											<col width="">

											<tr>
												<td class="stab">Register Date From</td>
												<td>
													<ezf:inputText name="xxFromDt_RF" ezfName="xxFromDt_RF" value="05/01/2008" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt_RF', 4);" >
												</td>
												<td/>
												<td class="stab">Time</td>
												<td>
													<ezf:select name="xxHrs_R1" ezfName="xxHrs_R1" ezfBlank="1" ezfCodeName="xxHrs_RF" ezfDispName="xxHrsMn_RF" />
												</td>
												<td/>
												<td class="stab">Register Date To</td>
												<td>
													<ezf:inputText name="xxToDt_RT" ezfName="xxToDt_RT" value="05/01/2008" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt_RT', 4);" >
												</td>
												<td/>
												<td class="stab">Time</td>
												<td>
													<ezf:select name="xxHrs_R2" ezfName="xxHrs_R2" ezfBlank="1" ezfCodeName="xxHrs_RT" ezfDispName="xxHrsMn_RT" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<!-- ********* 2 ********* -->
							<!-- ********* 3 ********* -->
							<table border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0">
											<col width="120">
											<col width="111">
											<col width="5">
											<col width="30">
											<col width="75">
											<col width="20">
											<col width="102">
											<col width="111">
											<col width="5">
											<col width="30">
											<col width="75">
											<col width="100">
											<col width="72">

											<tr>
												<td class="stab">Updated Date From</td>
												<td>
													<ezf:inputText name="xxFromDt_UF" ezfName="xxFromDt_UF" value="05/01/2008" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt_UF', 4);" >
												</td>
												<td/>
												<td class="stab">Time</td>
												<td>
													<ezf:select name="xxHrs_U1" ezfName="xxHrs_U1" ezfBlank="1" ezfCodeName="xxHrs_UF" ezfDispName="xxHrsMn_UF" />
												</td>
												<td/>
												<td class="stab">Updated Date To</td>
												<td>
													<ezf:inputText name="xxToDt_UT" ezfName="xxToDt_UT" value="05012008" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt_UT', 4);" >
												</td>
												<td/>
												<td class="stab">Time</td>
												<td>
													<ezf:select name="xxHrs_U2" ezfName="xxHrs_U2" ezfBlank="1" ezfCodeName="xxHrs_UT" ezfDispName="xxHrsMn_UT" />
												</td>
												<td/>
												<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<!-- ********* 3 ********* -->
						</td>
					</tr>
				</table>
<!-- **************************************** HEADER **************************************** -->
				<hr>
<!-- **************************************** DETAIL **************************************** -->
				<table border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td valign="top">
						<!-- ********* PAGENATION ********* -->
							<table width="100%">
								<tr align="right">
									<td>
										<!--
										<table border="1" cellpadding="1" cellspacing="0">
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
												<td class="pOut">40</td>
												<td class="stab">of</td>
												<td class="pOut">200</td>
												<td>&nbsp;</td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
											</tr>
										</table>
										-->
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
						<!-- ********* PAGENATION ********* -->
						<!-- ********* TITLE ********* -->
							<div style="overflow-y:hidden; overflow-x:hidden;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="240"  align="center">
									<col width="132"  align="center">
									<col width="132"  align="center">
									<col width="112"  align="center">
									<col width="112"  align="center">
									<col width="112"  align="center">
									<col width="118"  align="center">
									<col width="12"  align="center">

									<tr>
										<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'transactionId_A' )">Transaction ID<img id="sortIMG.transactionId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtTm_AR' )">Register Date<img id="sortIMG.xxDtTm_AR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtTm_AU' )">Updated Date<img id="sortIMG.xxDtTm_AU" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" rowspan="2">Processed Record Count</td>
										<td class="pClothBs" rowspan="2">Failed Record Count</td>
										<td class="pClothBs" rowspan="2">Processed Flag</td>
										<td class="pClothBs" rowspan="2">Change Status</td>
										</td>
									</tr>
								</table>
							</div>
						<!-- ********* TITLE ********* -->
						<!-- ********* DATA ********* -->
							<div style="overflow-y:scroll; height:354; overflow-x:hidden;width:1006" onscroll="synchroRightScroll();">
								<table border="1" cellpadding="1" cellspacing="0" id="A">
									<col width="240"  align="center">
									<col width="132"  align="center">
									<col width="132"  align="center">
									<col width="112"  align="center">
									<col width="112"  align="center">
									<col width="112"  align="center">
									<col width="118"  align="center">

									<tbody>
										<ezf:row ezfHyo="A">
											<tr height="27">
												<td><ezf:label name="transactionId_A" ezfName="transactionId_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxDtTm_AR" ezfName="xxDtTm_AR" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxDtTm_AU" ezfName="xxDtTm_AU" ezfHyo="A" ezfArrayNo="0" /></td>
												<td align="right"><ezf:label name="processedRecCnt_A" ezfName="processedRecCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td align="right"><ezf:label name="failedRecCnt_A" ezfName="failedRecCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxProcFlgNm_A" ezfName="xxProcFlgNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td>
													<ezf:select name="processedFlag_AS" ezfName="processedFlag_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="processedFlag_AU" ezfDispName="xxProcFlgNm_AU" ezfCodeDispHyo="A" />
												</td>
											</tr>
										</ezf:row>
										<ezf:skip>
										</ezf:skip>
									</tbody>
								</table>
							</div>
						<!-- ********* DATA ********* -->
						</td>
					</tr>
				</table>
<!-- **************************************** DETAIL **************************************** -->
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
