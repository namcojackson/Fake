<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160325134557 --%>
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
			<input type="hidden" name="pageID" value="NSAL0590Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Report Group">
<center>
	<table cellSpacing="0" cellPadding="0" width="100%" border="0">
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
										<li title="Report Group" class="pTab_ON"><a href="#">Report Group</a></li>
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
<!-- ################################################## Search Criteria - [START] ################################################## -->
					<table style="margin-top: 5px; margin-left: 5px" border="0">
						<colgroup>
							<col width="100px">	<!-- Label -->
							<col width="100px">
							<col width="80px">	<!-- Label -->
							<col width="150px">
							<col width="5px">
							<col width="80px">	<!-- Label -->
							<col width="111px">
							<col width="10px">	<!-- Label -->	
							<col width="111px">
							<col width="80px">
						</colgroup>
						<tbody>
							<tr>
							<td class="stab">Report Group#(*)</td>
							<td class="stab"><ezf:inputText name="dsContrRptGrpNum_H" ezfName="dsContrRptGrpNum_H" value="1234567890" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/></td>
							<td class="stab">Description(*)</td>
							<td class="stab"><ezf:inputText name="dsContrRptGrpDescTxt_H" ezfName="dsContrRptGrpDescTxt_H" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" htmlClass="stab" otherAttr=" maxlength=\"50\" size=\"50\" ezftoupper=\"\""/></td>
							<td />
							<td class="stab" align="right">Start From</td>
							<td class="stab"><ezf:inputText name="dsContrRptGrpStartDt_H" ezfName="dsContrRptGrpStartDt_H" value="20151029" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
							<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt_H', 4);" id="dsContrRptGrpStartDt_H"></td>
							<td class="stab">To</td>
							<td class="stab"><ezf:inputText name="dsContrRptGrpStartDt_HE" ezfName="dsContrRptGrpStartDt_HE" value="2015/10/29" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
							<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt_HE', 4);" id="dsContrRptGrpStartDt_HE"></td>
							<td></td>
							</tr>
							<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td class="stab" align="right">End From</td>
							<td class="stab"><ezf:inputText name="dsContrRptGrpEndDt_H" ezfName="dsContrRptGrpEndDt_H" value="2015/10/29" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
							<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt_H', 4);" id="dsContrRptGrpEndDt_H"></td>
							<td class="stab" >To</td>
							<td class="stab"><ezf:inputText name="dsContrRptGrpEndDt_HE" ezfName="dsContrRptGrpEndDt_HE" value="2015/10/29" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
							<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt_HE', 4);" id="dsContrRptGrpEndDt_HE"></td>
							<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
							</tr>
						</tbody>
					</table>	

<!-- ################################################## Search Criteria - [E N D] ################################################## -->
					<hr />
<!-- ################################################## Search Result   - [START] ################################################## -->
					<center>
					<table width="800px" border="0" cellpadding="1" cellspacing="0">
						<tr>
							<td align="right">
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
								<table border="0" cellpadding="0" width="100%">
									<tr align="right">
										<td>
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
									</tr>
						 		</table>
						 		
			 				<td>
			 			</tr>
			 		</table>
<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
					<table width="800px">
						<tr>
							<td>
								<div id="topTBL" style="overflow-x:hidden; overflow-y:none; width:783px;">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed" width="783px">
										<colgroup>
										<col align="middle" width="125px">	<!-- Report Group# -->
										<col align="middle" width="436px">	<!-- Description -->
										<col align="middle" width="111px">	<!-- Start Date -->
										<col align="middle" width="111px">	<!-- End Date -->
										</colgroup>
										<tbody>
											<tr>
												<td class="pClothBs">Report Group#</td>
												<td class="pClothBs">Description</td>
												<td class="pClothBs">Start Date</td>
												<td class="pClothBs">End Date</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div id="bottomTBL" style="overflow-x:hidden; overflow-y:scroll; width:800px; height:394px;" onScroll="synchroScrollLeft(this.id, new Array('topTBL'));">
									<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;word-break:break-all" width="783px">
										<colgroup>
										<col align="middle" width="125px">	<!-- Report Group# -->
										<col align="middle" width="436px">	<!-- Description -->
										<col align="middle" width="111px">	<!-- Start Date -->
										<col align="middle" width="111px">	<!-- End Date -->
										</colgroup>
										<tbody>
										<ezf:row ezfHyo="A">
											<tr height="28px">
												<td><ezf:label name="dsContrRptGrpNum" ezfName="dsContrRptGrpNum" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="dsContrRptGrpDescTxt" ezfName="dsContrRptGrpDescTxt" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"50\" size=\"50\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="dsContrRptGrpStartDt" ezfName="dsContrRptGrpStartDt" value="2015/01/01" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');"> 
												</td>
												<td><ezf:inputText name="dsContrRptGrpEndDt" ezfName="dsContrRptGrpEndDt" value="2015/01/01" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/>
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');"> 
												</td>
											</tr>
											<ezf:skip>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');"> 
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4), '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											<tr height="28px">
												<td><label ezfout name="dsContrRptGrpNum" ezfname="dsContrRptGrpNum" ezfhyo="A">ZZZZZZZZZZ</label></td>
												<td><input maxLength="50" size="50" name="dsContrRptGrpDescTxt" ezfname="dsContrRptGrpDescTxt" ezfToUpper ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5"></td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpStartDt" ezfname="dsContrRptGrpStartDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpStartDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><input maxLength="10" size="10" name="dsContrRptGrpEndDt" ezfname="dsContrRptGrpEndDt" ezfToUpper ezfhyo="A" value="2015/01/01">
													<img src="./img/calendar.gif"  class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrRptGrpEndDt', 4, '{EZF_ROW_NUMBER}');" >
												</td>
											</tr>
											</ezf:skip>
											</ezf:row>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<table>
									<tr>
										<td align="left"><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" /></td>
									</tr>
								</table>
							</td>
						</tr>
					
					</table>
					</center>
					
<!-- ################################################## Search Result   - [E N D] ################################################## -->


			</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
