<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220822092615 --%>
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
			<input type="hidden" name="pageID" value="NSAL1130Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Counter History Popup">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## FROM (COMMON)HEADER ######################################## --%>
				<center>
				<table style="margin-top: 5px; margin-left: 5px" cellSpacing="0" cellPadding="1" border="0">
					<colgroup>
						<!-- Meter Label -->
						<col align="left" width="60px">
						<col align="center" width="320px">
						<col width="5px">
						<!-- Read Date From -->
						<col align="left" width="80px">
						<col align="center" width="110px">
						<col width="5px">
						<!-- Read Date Thru -->
						<col align="left" width="80px">
						<col align="center" width="110px">
						<col width="5px">
						<!-- Search -->
						<col align="center" width="50px">
					</colgroup>
					<tbody>
						<tr>
							<!-- Meter Label -->
							<td class="stab">Meter Label</td>
							<td>
								<ezf:select name="mtrLbCd_SV" ezfName="mtrLbCd_SV" ezfBlank="1" ezfCodeName="mtrLbCd_CD" ezfDispName="mtrLbDescTxt_SC" />
							</td>
							<td />
							<!-- Read Date From -->
							<td class="stab">Read Date From</td>
							<td>
								<ezf:inputText name="effFromDt_SC" ezfName="effFromDt_SC" value="01/01/2016" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_SC', 4);">
							</td>
							<td />
							<!-- Read Date Thru -->
							<td class="stab">Read Date Thru</td>
							<td>
								<ezf:inputText name="effThruDt_SC" ezfName="effThruDt_SC" value="01/01/2016" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_SC', 4);">
							</td>
							<td />
							<!-- Search -->
							<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
						</tr>
					</tbody>
				</table>
				</center>
<%-- ######################################## TO   (COMMON)HEADER ######################################## --%>
				<hr />
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
<ezf:skip>
				<table width="900px">
					<tr>
						<td align="right">
							<TABLE cellSpacing="0" cellPadding="1" border="0">
								<COLGROUP>
									<COL align="left" width="80">
									<COL align="left" width="80">
									<COL align="left" width="700">
									<COL align="right" width="40">
									<COL align="middle" width="16">
									<COL align="right" width="40">
									<COL align="middle" width="16">
									<COL align="right" width="40">
									<COL width="10">
									<COL width="0">
									<COL width="1">
									<COL width="0">
								</COLGROUP>
								<TBODY>
									<TR>
										<TD></TD>
										<TD></TD>
										<TD>&nbsp;</TD>
										<TD class="stab">Showing</TD>
										<TD class="pOut">1</TD>
										<TD class="stab">to</TD>
										<TD class="pOut">50</TD>
										<TD class="stab">of</TD>
										<TD class="pOut">200</TD>
										<TD>&nbsp;</TD>
										<TD><INPUT class="pBtn3" disabled onclick="sendServer(this)" type="button" value="Prev" name="PagePrev"></TD>
										<TD></TD>
										<TD><INPUT class="pBtn3" onclick="sendServer(this)" type="button" value="Next" name="PageNext"></TD>
									</TR>
								</TBODY>
							</TABLE>
						</td>
					</tr>
				</table>
</ezf:skip>
				<table width="900px">
					<tr>
						<td align="right">
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
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- ######################################## FROM (COMMON)DETAIL ######################################## --%>
				<center>
					<table width="800px">
						<tr>
							<td align="left">
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed" ><!-- width="754px" -->
									<colgroup>
										<col align="center" width="100px">	<!-- Read Date -->
										<col align="center" width="300px">	<!-- Meter Label -->
										<col align="center" width="100px">	<!-- OLD -->
										<col align="center" width="90px">	<!-- OLD -->
										<col align="center" width="100px">	<!-- NEW -->
										<col align="center" width="90px">	<!-- NEW -->
									</colgroup>
									<tbody>
										<tr height="19px">
											<td class="pClothBs" rowspan="2">
												<a onclick="columnSort('A', 'endMtrReadDt')" href="#" class="pSortCol" >
													Read Date<img id="sortIMG.endMtrReadDt"   border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></label>
												</a>
											</td>
											<td class="pClothBs" rowspan="2">
												<a onclick="columnSort('A', 'mtrLbDescTxt')" href="#" class="pSortCol" >
													Meter Label<img id="sortIMG.mtrLbDescTxt"   border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></label>
												</a>
											</td>
											<td class="pClothBs" colspan="2">OLD</td>
											<td class="pClothBs" colspan="2">NEW</td>
										</tr>
										<tr height="18px">
											<td class="pClothBs">
												<a onclick="columnSort('A', 'oldEndReadMtrCnt')" href="#" class="pSortCol" >
													Read<img id="sortIMG.oldEndReadMtrCnt"   border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></label>
												</a>
											</td>
											<td class="pClothBs">
												<a onclick="columnSort('A', 'oldTestMtrCnt')" href="#" class="pSortCol" >
													Test<img id="sortIMG.oldTestMtrCnt"   border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></label>
												</a>
											</td>
											<td class="pClothBs">
												<a onclick="columnSort('A', 'newEndReadMtrCnt')" href="#" class="pSortCol" >
													Read<img id="sortIMG.newEndReadMtrCnt"   border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></label>
												</a>
											</td>
											<td class="pClothBs">
												<a onclick="columnSort('A', 'newTestMtrCnt')" href="#" class="pSortCol" >
													Test<img id="sortIMG.newTestMtrCnt"   border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></label>
												</a>
											</td>
										</tr>
									</tbody>
								</table>
								<div id="bottomTBL" style="overflow-x:hidden; overflow-y:scroll; height:430px;" >
									<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;word-break:break-all">
										<colgroup>
										<col align="center" width="100px">	<!-- Read Date -->
										<col align="left" width="300px">	<!-- Meter Label -->
										<col align="right" width="100px">	<!-- OLD -->
										<col align="right" width="90px">	<!-- OLD -->
										<col align="right" width="100px">	<!-- NEW -->
										<col align="right" width="90px">	<!-- NEW -->
										</colgroup>
										<tbody>
										<ezf:row ezfHyo="A">
											<tr height="24px">
												<td><ezf:label name="endMtrReadDt" ezfName="endMtrReadDt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="mtrLbDescTxt" ezfName="mtrLbDescTxt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="oldEndReadMtrCnt" ezfName="oldEndReadMtrCnt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="oldTestMtrCnt" ezfName="oldTestMtrCnt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="newEndReadMtrCnt" ezfName="newEndReadMtrCnt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="newTestMtrCnt" ezfName="newTestMtrCnt" ezfHyo="A" ezfArrayNo="0" /></td>
											</tr>
										</ezf:row>
										<ezf:skip>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="neTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="olTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="olTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
											<tr height="24px">
												<td><label ezfout name="endMtrReadDt" ezfname="endMtrReadDt" ezfhyo="A">09/30/2014</label></td>
												<td><label ezfout name="mtrLbDescTxt" ezfname="mtrLbDescTxt" ezfhyo="A">Aggregate Black and White Small (Billing)</label></td>
												<td><label ezfout name="oldEndReadMtrCnt" ezfname="oldEndReadMtrCnt" ezfhyo="A">58,037</label></td>
												<td><label ezfout name="oldTestMtrCnt" ezfname="oldTestMtrCnt" ezfhyo="A">0</label></td>
												<td><label ezfout name="newEndReadMtrCnt" ezfname="newEndReadMtrCnt" ezfhyo="A">66,593</label></td>
												<td><label ezfout name="newTestMtrCnt" ezfname="newTestMtrCnt" ezfhyo="A">0</label></td>
											</tr>
										</ezf:skip>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</center>
<%-- ######################################## TO   (COMMON)DETAIL ######################################## --%>
			</td>
		</tr>
	</table>
</center>



<%-- **** Task specific area ends here **** --%>
