<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161130084057 --%>
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
			<input type="hidden" name="pageID" value="NSAL0960Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract Validation List Setup">

			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" otherAttr=" size=\"0\" id=\"xxRecHistCratTs\""/>
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm\""/>
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs\""/>
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm\""/>
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" otherAttr=" size=\"0\" id=\"xxRecHistTblNm\""/>
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<%-- ######################################## SearchCriteria Start ############################################## --%>
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Contract Validation List Setup" class="pTab_ON"><a href="#">VldLst Setup</a></li>
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
								<table border="0" style="table-layout:fixed;" width="95%">
									<col width="70">
									<col width="10">
									<col width="120">
									<col width="70">
									<col width="120">
									<col width="40">
									<col width="90">
									<col width="10">
									<col width="480">
									<tr>
										<td class="stab"><ezf:anchor name="dsContrVldListNm_HL" ezfName="dsContrVldListNm_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_ListNm" >List Name:</ezf:anchor></td>
										<td></td>
										<td colspan="3"><ezf:inputText name="dsContrVldListNm_H" ezfName="dsContrVldListNm_H" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6" otherAttr=" size=\"43\" maxlength=\"60\""/></td>
										<td></td>
										<td class="stab"">Description(*):</td>
										<td></td>
										<td><ezf:inputText name="dsContrVldListDescTxt_H" ezfName="dsContrVldListDescTxt_H" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxxx0xxxxxxxx1xxxxxxxxx2" otherAttr=" size=\"68\" maxlength=\"120\""/></td>
									</tr>
									<tr>
										<td class="stab">Start Date:</td>
										<td></td>
										<td><ezf:inputText name="effFromDt_H" ezfName="effFromDt_H" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H', 4);"></td>
										<td class="stab">End Date:</td>
										<td><ezf:inputText name="effToDt_H" ezfName="effToDt_H" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effToDt_H', 4);"></td>
										<td colspan="4"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<%-- ######################################## SearchCriteria End   ############################################## --%>
					<br>
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" height="24">
						<tr>
							<td>
								<ezf:inputButton name="AddLine" value="Add Line" htmlClass="pBtn6" otherAttr=" id=\"AddLine\""/>
								<ezf:inputButton name="DeleteLine" value="Delete Line" htmlClass="pBtn6" otherAttr=" id=\"DeleteLine\""/>
							</td>
						</tr>
					</table>
					<%-- ######################################## SearchResultList Start ############################################## --%>
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center">
						<col align="left" valign="top">
						<tr>
							<td>
								<div id="Top" style="overflow-x:hidden; width:1083;">
									<table border="1" cellpadding="1" cellspacing="0" width="" height="24">
										<col width="25"  align="center">		<!-- Radio Button -->
										<col width="40"  align="center">		<!-- Seq -->
										<col width="200"  align="center">		<!-- Category -->
										<col width="240"  align="center">		<!-- Validation -->
										<col width="240"  align="center">		<!-- Description -->
										<col width="90"  align="center">		<!-- Action -->
										<col width="100"  align="center">		<!-- Start Date -->
										<col width="100"  align="center">		<!-- End Date -->
										<tr>
											<td class="pClothBs"></td>
											<td id="AH0" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'vldSortNum_A')">Seq</a><img id="sortIMG.vldSortNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs">Category</td>
											<td class="pClothBs">Validation</td>
											<td class="pClothBs">Description</td>
											<td class="pClothBs">Action</td>
											<td class="pClothBs">Start Date</td>
											<td class="pClothBs">End Date</td>
										</tr>
									</table>
								</div>
								<div id="Detail" style="width:1100; overflow-y:scroll; height:450;">
									<table id="A" border="1" cellpadding="1" cellspacing="0">
										<col width="25"  align="center">		<!-- Radio Button -->
										<col width="40"  align="center">		<!-- Seq -->
										<col width="200"  align="center">		<!-- Category -->
										<col width="240"  align="center">		<!-- Validation -->
										<col width="240"  align="center">		<!-- Description -->
										<col width="90"  align="center">		<!-- Action -->
										<col width="100"  align="center">		<!-- Start Date -->
										<col width="100"  align="center">		<!-- End Date -->
										<ezf:row ezfHyo="A">
											<tr>
												<td>
													<ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" />
												</td>
												<td>
													<ezf:inputText name="vldSortNum_A" ezfName="vldSortNum_A" value="123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/>
												</td>
												<td>
													<ezf:inputButton name="OpenWin_VldCategory" value="Catg" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
													<ezf:inputText name="dsContrVldCatgDescTxt_A" ezfName="dsContrVldCatgDescTxt_A" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/>
												</td>
												<td>
													<ezf:inputText name="dsContrVldNm_A" ezfName="dsContrVldNm_A" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"60\""/>
												</td>
												<td>
													<ezf:inputText name="dsContrVldDescTxt_A" ezfName="dsContrVldDescTxt_A" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxxx0xxxxxxxx1xxxxxxxxx2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"120\""/>
												</td>
												<td>
													<ezf:select name="vldActCd_AS" ezfName="vldActCd_AS" ezfHyo="A" ezfArrayNo="0" ezfCodeName="vldActCd_HC" ezfDispName="vldActDescTxt_HC" otherAttr=" style=\"width:80;\""/>
												</td>
												<td>
													<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});">
												</td>
												<td>
													<ezf:inputText name="effToDt_A" ezfName="effToDt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effToDt_A', 4, {EZF_ROW_NUMBER});">
												</td>
												<td class="pAuditInfo">
													<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												</td>
											</tr>
											<ezf:skip>
											</ezf:skip>
										</ezf:row>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<%-- ######################################## SearchResultList End   ######################################## --%>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
