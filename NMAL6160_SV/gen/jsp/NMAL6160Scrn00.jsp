<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160616135443 --%>
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
			<input type="hidden" name="pageID" value="NMAL6160Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Multi Candinate Popup">
<%@ page import="business.servlet.NMAL6160.NMAL6160BMsg" %>
<% NMAL6160BMsg bMsg = (NMAL6160BMsg) databean.getEZDBMsg(); %>


<center>
	<table width="100%">
		<col valign="top">
		<tr>
			<td>
				<table border="0" cellpadding="2" cellspacing="0" width="95%" style="margin-left:40; table-layout:fixed;" >
					<col width="30">
					<tr>
						<td class="stab"><ezf:inputText name="xxModeNm23Txt" ezfName="xxModeNm23Txt" value="Candidate Terroitory" otherAttr=" size=\"30\" ezftoupper=\"\""/></td>
					</tr>
				</table>
				<hr>
				<table border="0" cellpadding="1" cellspacing="0" width="90%" align="center">
					<col width="300">
					<col width="150">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="190">
								<col width="60">
								<tr>
									<td><ezf:inputText name="xxDsMsgEntryTxt" ezfName="xxDsMsgEntryTxt" otherAttr=" size=\"25\" maxlength=\"200\""/></td>
									<td><ezf:inputButton name="Filter" value="Filter" htmlClass="pBtn3" /></td>
								</tr>
							</table>
						</td>
						
						<td>
							<div align="right">
								<ezf:skip>
									<table border="0" cellpadding="0" cellspacing="0">
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
											<td class="pOut">20</td>
											<td class="stab">of</td>
											<td class="pOut">200</td>
											<td> </td>
											<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
											<td></td>
											<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
											<td></td>
										</tr>
									</table>
								</ezf:skip>
							</div>
							<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
								<jsp:param name="beanId"	  value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"	 value='<%= bMsg.xxTblNm.getValue() %>' />
								<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
								<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
							</jsp:include>
						</td>
					</tr>
				</table>
				
<%------------------------------------%>
<%-- Detail							--%>
<%------------------------------------%>
				<table align="center">
					<tr>
						<td>
							<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
								<col width="680" align="center">
								<tr>
									<td class="pClothBs"><ezf:label name="xxScrItem61Txt" ezfName="xxScrItem61Txt" /></td>
								</tr>
							</table>
							<div style="overflow-y:scroll; height:452; width:699;">
								<c:choose>
								<c:when test="${pageScope._ezddatabean.bizId=='NMAL6760'}">
								<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;">
									<col width="680" align="left">
									<tbody>
										<ezf:row ezfHyo="A">
											<tr id="id_row_a{EZF_ROW_NUMBER}">
												<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_Loc" ><ezf:label name="xxDsMsgEntryTxt_A" ezfName="xxDsMsgEntryTxt_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
											</tr>
										</ezf:row>
									</tbody>
								</table>
								</c:when>
								<c:when test="${pageScope._ezddatabean.bizId=='NMAL2630'}">
								<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout:fixed;">
									<col width="680" align="left">
									<tbody>
										<ezf:row ezfHyo="B">
											<tr id="id_row_b{EZF_ROW_NUMBER}">
												<td><ezf:anchor name="" ezfName="" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_Trty" ><ezf:label name="xxDsMsgEntryTxt_B" ezfName="xxDsMsgEntryTxt_B" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></td>
											</tr>
										</ezf:row>
									</tbody>
								</table>
								</c:when>
								<c:otherwise>
								<table border="1" cellpadding="1" cellspacing="0" id="C" style="table-layout:fixed;">
									<col width="680" align="left">
									<tbody>
										<ezf:row ezfHyo="C">
											<tr id="id_row_c{EZF_ROW_NUMBER}">
												<td><ezf:label name="xxDsMsgEntryTxt_C" ezfName="xxDsMsgEntryTxt_C" ezfHyo="C" ezfArrayNo="0" /></td>
											</tr>
										</ezf:row>
									</tbody>
								</table>
								</c:otherwise>
								</c:choose>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
