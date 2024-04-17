<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20221130135433 --%>
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
			<input type="hidden" name="pageID" value="NWAL1810Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="View Change Log Popup">
			

<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<%-- ######################################## HEADER ######################################## --%>
				<div class="pTab_BODY">
					<table border="0" width="99%" align="center">

						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="050">
									<col width="155">
									<col width="020">
									<col width="040">
									<col width="060">
									<col width="045">
									<col width="080">
									<col width="020">
									<col width="010">
									<col width="080">
									<col width="020">
									<col width="063">
									<col width="250">
									<tr height="23">
										<td class="stab">&nbsp;Number</td>
										<td>
											<ezf:inputText name="xxViewChngLogSrchNum" ezfName="xxViewChngLogSrchNum" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
										</td>
										<td class="stab"></td>
										<td class="stab">Level</td>
										<td>
											<ezf:inputText name="xxAudLvlNm" ezfName="xxAudLvlNm" value="WWWWWWWWW1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
										</td>
										<td class="stab" >&nbsp;Date</td>
										<td><ezf:inputText name="xxFromDt" ezfName="xxFromDt" value="04/01/2022" otherAttr=" size=\"10\" ezftoupper=\"\""/></td>
										<td><img src="./img/calendar.gif" class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxFromDt', 4);" ></td>
										<td class="stab" >&nbsp;-</td>
										<td><ezf:inputText name="xxThruDt" ezfName="xxThruDt" value="03/31/2023" otherAttr=" size=\"10\" ezftoupper=\"\""/></td>
										<td><img src="./img/calendar.gif" class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxThruDt', 4);" ></td>
										<td class="stab" >&nbsp;Event</td>
										<td><ezf:select name="eventId" ezfName="eventId" ezfBlank="1" ezfCodeName="eventNm_PL" ezfDispName="eventNm_PL" otherAttr=" style=\"width:250\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table cellpadding="0" cellspacing="0">
									<col width="035">
									<col width="040">
									<col width="045">
									<col width="101">
									<col width="045">
									<col width="075">
									<col width="087">
									<col width="168">
									<col width="064">
									<col width="260">
									<tr height="23">
										<td class="stab" >&nbsp;I/O/S</td>
										<td><ezf:select name="ordPrftTrxCatgCd" ezfName="ordPrftTrxCatgCd" ezfBlank="1" ezfCodeName="ordPrftTrxCatgCd_PL" ezfDispName="ordPrftTrxCatgNm_PL" otherAttr=" style=\"width:35\""/></td>
										<td class="stab" >&nbsp;Line(*)</td>
										<td><ezf:inputText name="docId" ezfName="docId" value="1.1" otherAttr=" size=\"12\""/></td>
										<td class="stab" >&nbsp;User ID</td>
										<td><ezf:inputText name="xxSrUsrId" ezfName="xxSrUsrId" value="123456" otherAttr=" size=\"6\" ezftoupper=\"\""/></td>
										<td class="stab" >&nbsp;User Name(*)</td>
										<td><ezf:inputText name="xxPsnNm" ezfName="xxPsnNm" value="test User" otherAttr=" size=\"20\""/></td>
										<td class="stab" >&nbsp;Memo(*)</td>
										<td><ezf:inputText name="bizProcCmntTxt" ezfName="bizProcCmntTxt" value="123456789a123456789b123456789c12345" otherAttr=" size=\"35\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table cellpadding="0" cellspacing="0">
									<col width="188">
									<col width="261">
									<col width="138">
									<col width="188">
									<col width="100">
									<col width="180">
									<col width="210">
									<col width="080">
									<tr height="23">
										<td>Attribute(*)</td>
										<td><ezf:inputText name="recDbItemAttrbNm" ezfName="recDbItemAttrbNm" value="Price Calc Date" otherAttr=" size=\"25\""/></td>
										<td>&nbsp;Before(*)</td>
										<td><ezf:inputText name="xxRecValBefTxt" ezfName="xxRecValBefTxt" value="123456789a123456789b123456789c12345" otherAttr=" size=\"20\""/></td>
										<td>&nbsp;After(*)</td>
										<td><ezf:inputText name="xxRecValAftTxt" ezfName="xxRecValAftTxt" value="123456789a123456789b123456789c12345" otherAttr=" size=\"20\""/></td>
										<td></td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div style="border-bottom:solid 1px #AAAAAA;padding-top:1px;margin-bottom:0;"></div>
				<br>

				<%-- ######################################## DETAIL ######################################## --%>
				<table width="98%">
					<col valign="top">
					<tr>
						<td>
							<div class="pTab_HEAD">
								<ul>
									<li id="Summary" title="Summary" class="pTab_ON" >
										<ezf:anchor name="" ezfName="xxTabProt_SU" ezfEmulateBtn="1" ezfGuard="TAB_Summary" >Summary</ezf:anchor>
									</li>
									<li id="Detail" title="Detail" class="pTab_OFF">
										<ezf:anchor name="" ezfName="xxTabProt_DE" ezfEmulateBtn="1" ezfGuard="TAB_Detail" >Detail</ezf:anchor>
									</li>
								</ul>
							</div>
							
							<c:choose>
								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++ Summary TAB +++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Summary'}">
									<script type="text/javascript">
										document.getElementById( "Summary").className = "pTab_ON";
										document.getElementById( "Detail").className = "pTab_OFF";
									</script>
									<div class="pTab_BODY_In">
										<%-- Pagenation --%>
										<table width="100%">
											<tr>
												<td colspan="1" align="left">
													<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
												</td>
												<td align="right">
												
<%-- After Convert to JSP, this area suppose to be deleted [FROM] 
													<table border="0" cellpadding="1" cellspacing="0">
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
													</table>
 After Convert to JSP, this area suppose to be deleted [TO] --%>
													
													<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
														<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
														<jsp:param name="TableNm"     value="A" />
														<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
														<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
														<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
													</jsp:include>
													
												</td>
											</tr>
										</table>
										<div id="parentBoxA">
										<table border ="0" cellpadding="1" cellspacing="0" width="998">
											<tr>
												<td align="top">
												
												<div style="float:left; display:block"> <!-- left table -->
													<div id='leftTblHead' style="display:block;">
													</div>
													<div id='leftTbl' style="float:left; display:block; height:398px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
													</div>
												</div>  <!-- left table -->
												<div style="float:left"> <!-- right table -->
													<div id='rightTblHead' style="width:990px; display:block; overflow:hidden; margin:0px;padding:0px;">
												
														<table border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="973" style="table-layout:fixed; margin-right:20px">
															<col width="180" align="center">		<!-- Event -->
															<col width="028" align="center">		<!-- I/O/S -->
															<col width="145" align="center">		<!-- Timestamp -->
															<col width="100" align="center">		<!-- Line -->
															<col width="080" align="center">		<!-- User ID -->
															<col width="180" align="center">		<!-- User Name -->
															<col width="260" align="center">		<!-- Memo -->
															<tr height="24">
																<td id="AH0" class="pClothBs">Event</td>
																<td id="AH1" class="pClothBs">I/O/S</td>
																<td id="AH2" class="pClothBs">Timestamp</td>
																<td id="AH3" class="pClothBs">Line</td>
																<td id="AH4" class="pClothBs">User ID</td>
																<td id="AH5" class="pClothBs">User Name</td>
																<td id="AH6" class="pClothBs">Memo</td>
															</tr>
														</table>
													</div>
													<div id="rightTbl" style="width:990px; height:348px; display:block; overflow-y:scroll; margin:0px; padding:0px;">
														<table border="1" cellpadding="1" cellspacing="0" id="A" width="973" style="table-layout:fixed;">
															<col width="180" align="left">		<!-- Event -->
															<col width="028" align="center">	<!-- I/O/S -->
															<col width="145" align="left">		<!-- Timestamp -->
															<col width="100" align="left">		<!-- Line -->
															<col width="080" align="left">		<!-- User ID -->
															<col width="180" align="left">		<!-- User Name -->
															<col width="260" align="left">		<!-- Memo -->
															<ezf:row ezfHyo="A">
																<tr id="id_row{EZF_ROW_NUMBER}" height="22">
																	<td><ezf:inputText name="eventNm_A0" ezfName="eventNm_A0" value="1WWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"60\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:label name="ordPrftTrxCatgCd_A0" ezfName="ordPrftTrxCatgCd_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:label name="xxDtTm_A0" ezfName="xxDtTm_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:label name="docId_A0" ezfName="docId_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:label name="xxSrUsrId_A0" ezfName="xxSrUsrId_A0" ezfHyo="A" ezfArrayNo="0" /></td>
																	<td><ezf:inputText name="xxPsnNm_A0" ezfName="xxPsnNm_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"40\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																	<td><ezf:inputText name="bizProcCmntTxt_A0" ezfName="bizProcCmntTxt_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"43\" maxlength=\"100\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																</tr>
															</ezf:row>
															<ezf:skip>
																<tr class="pEvenNumberBGcolor" height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">2WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">3WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr class="pEvenNumberBGcolor" height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">4WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">5WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr class="pEvenNumberBGcolor" height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">6WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">7WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																
																<tr class="pEvenNumberBGcolor" height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">8WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">9WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr class="pEvenNumberBGcolor" height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">10WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">11WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr class="pEvenNumberBGcolor" height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">12WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">13WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr class="pEvenNumberBGcolor" height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">14WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">15WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr class="pEvenNumberBGcolor" height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">16WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">17WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr class="pEvenNumberBGcolor" height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">18WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">19WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>
																<tr class="pEvenNumberBGcolor" height="22">
																	<td><label ezfout ezfhyo="A" name="eventNm_A0" ezfname="eventNm_A0">20WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																	<td><label ezfout ezfhyo="A" name="_A0" ezfname="_A0">O</label></td>
																	<td><label ezfout ezfhyo="A" name="xxDtTm_A0" ezfname="xxDtTm_A0">mm/dd/yyyy hh:MM:ss</label></td>
																	<td><label ezfout ezfhyo="A" name="docId_A0" ezfname="docId_A0">WWWWWWWWW1</label></td>
																	<td><label ezfout ezfhyo="A" name="xxSrUsrId_A0" ezfname="xxSrUsrId_A0">WWWWWWWW</label></td>
																	<td><input type="text" ezfhyo="A" name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="24" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																	<td><input type="text" ezfhyo="A" name="bizProcCmntTxt_A0" ezfname="bizProcCmntTxt_A0" size="43" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" class="pPro" style="border:none;background-color:transparent;padding:0px;"></td>
																</tr>

															</ezf:skip>
														</table>
													</div>
													<script type="text/javascript" defer>
														S21TableUI.initialize("parentBoxA", "AHEAD", "A");
													</script>
												</div>
												</td>
											</tr>
										</table>
									</div>
								</c:when>
							
								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++ Detail TAB ++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
								<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Detail'}">
									<script type="text/javascript">
										document.getElementById( "Summary").className = "pTab_OFF";
										document.getElementById( "Detail").className = "pTab_ON";
									</script>
									<div class="pTab_BODY_In">
										<%-- Pagenation --%>
										<table width="100%">
											<tr>
												<td colspan="1" align="left">
													<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
												</td>

												<td align="right">
												
<%-- After Convert to JSP, this area suppose to be deleted [FROM] 
													<table border="0" cellpadding="1" cellspacing="0">
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
													</table>
 After Convert to JSP, this area suppose to be deleted [TO] --%>
													
													<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
														<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
														<jsp:param name="TableNm"     value="B" />
														<jsp:param name="ShowingFrom" value="xxPageShowFromNum_B" />
														<jsp:param name="ShowingTo"   value="xxPageShowToNum_B" />
														<jsp:param name="ShowingOf"   value="xxPageShowOfNum_B" />
													</jsp:include>
													
												</td>
											</tr>
										</table>
										<div id="parentBoxB">
										<table border ="0" cellpadding="1" cellspacing="0" width="998">
											<tr>
												<td align="top">
												<div style="float:left; display:block;"> <!-- left table -->
													<div id='leftTblHead' style="display:block;">
													</div>
													<div id='leftTbl' style="float:left; display:block; height:376px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
													</div>
												</div>  <!-- left table -->
												<div style="float:left"> <!-- right table -->
													<div id='rightTblHead' style="width:980px; display:block; overflow:hidden; margin:0px;padding:0px;">
														<table border="1" cellpadding="1" cellspacing="0" id="BHEAD" width="1648" style="table-layout:fixed; margin-right:20px">
															<col width="180" align="center">	<!-- Event -->
															<col width="028" align="center">	<!-- I/O/S -->
															<col width="145" align="center">	<!-- Timestamp -->
															<col width="100" align="center">	<!-- Line -->
															<col width="080" align="center">	<!-- User ID -->
															<col width="180" align="center">	<!-- User Name -->
															<col width="215" align="center">	<!-- Attribute -->
															<col width="360" align="center">	<!-- Before -->
															<col width="360" align="center">	<!-- After -->
															<tr height="24">
																<td id="BH0" class="pClothBs">Event</td>
																<td id="BH1" class="pClothBs">I/O/S</td>
																<td id="BH2" class="pClothBs">Timestamp</td>
																<td id="BH3" class="pClothBs">Line</td>
																<td id="BH4" class="pClothBs">User ID</td>
																<td id="BH5" class="pClothBs">User Name</td>
																<td id="BH6" class="pClothBs">Attribute</td>
																<td id="BH7" class="pClothBs">Before</td>
																<td id="BH8" class="pClothBs">After</td>
															</tr>
														</table>
													</div>
													<%-- Left Table Detail --%>
													<div id="rightTbl" style="width:997px; height:360px; display:block; overflow:scroll; margin:0px; padding:0px;">
														<table border="1" cellpadding="1" cellspacing="0" id="B" width="1648" style="table-layout:fixed;">
															<col width="180" align="left">		<!-- Event -->
															<col width="028" align="center">	<!-- I/O/S -->
															<col width="145" align="left">		<!-- Timestamp -->
															<col width="100" align="left">		<!-- Line -->
															<col width="080" align="left">		<!-- User ID -->
															<col width="180" align="center">	<!-- User Name -->
															<col width="215" align="left">		<!-- Attribute -->
															<col width="360" align="center">	<!-- Before -->
															<col width="360" align="center">	<!-- After -->
															<ezf:row ezfHyo="B">
															<tr height="22">
																<td><ezf:inputText name="eventNm_B0" ezfName="eventNm_B0" value="1WWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"60\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																<td><ezf:label name="ordPrftTrxCatgCd_B0" ezfName="ordPrftTrxCatgCd_B0" ezfHyo="B" ezfArrayNo="0" /></td>
																<td><ezf:label name="xxDtTm_B0" ezfName="xxDtTm_B0" ezfHyo="B" ezfArrayNo="0" /></td>
																<td><ezf:label name="docId_B0" ezfName="docId_B0" ezfHyo="B" ezfArrayNo="0" /></td>
																<td><ezf:label name="xxSrUsrId_B0" ezfName="xxSrUsrId_B0" ezfHyo="B" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="xxPsnNm_B0" ezfName="xxPsnNm_B0" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"40\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																<td><ezf:label name="recDbItemAttrbNm_B0" ezfName="recDbItemAttrbNm_B0" ezfHyo="B" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="xxRecValBefTxt_B0" ezfName="xxRecValBefTxt_B0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"100\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
																<td><ezf:inputText name="xxRecValAftTxt_B0" ezfName="xxRecValAftTxt_B0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"100\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															</tr>
															</ezf:row>
															<ezf:skip>
															<tr class="pEvenNumberBGcolor" height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >2WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >3WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr class="pEvenNumberBGcolor" height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >4WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >5WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr class="pEvenNumberBGcolor" height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >6WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >7WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr class="pEvenNumberBGcolor" height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >8WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >9WWWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr class="pEvenNumberBGcolor" height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >10WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >11WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr class="pEvenNumberBGcolor" height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >12WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >13WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr class="pEvenNumberBGcolor" height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >14WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >15WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr class="pEvenNumberBGcolor" height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >16WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >17WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr class="pEvenNumberBGcolor" height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >18WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >19WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															<tr class="pEvenNumberBGcolor" height="22">
																<td><label ezfout name="eventNm_B0" ezfname="eventNm_B0" ezfhyo="B" >20WWWWWWW1WWWWWWWWW2WWWWW</label></td>
																<td><label ezfout ezfhyo="B" name="ordPrftTrxCatgCd_B0" ezfname="ordPrftTrxCatgCd_B0">O</label></td>
																<td><label ezfout name="xxDtTm_B0" ezfname="xxDtTm_B0" ezfhyo="B" >mm/dd/yyyy hh:MM:ss</label></td>
																<td><label ezfout name="docId_B0" ezfname="docId_B0" ezfhyo="B" >WWWWWWWWW1</label></td>
																<td><label ezfout name="xxSrUsrId_B0" ezfname="xxSrUsrId_B0" ezfhyo="B" >WWWWWWWW</label></td>
																<td><input type="text" ezfhyo="B"  name="xxPsnNm_B0" ezfname="xxPsnNm_B0" class="pPro" size="25" maxlength="40" value="WWWWWWWWW1WWWWWWWWW2WWWWW" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><label ezfout name="recDbItemAttrbNm_B0" ezfname="recDbItemAttrbNm_B0" ezfhyo="B" >WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValBefTxt_B0" ezfname="xxRecValBefTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
																<td><input type="text" ezfhyo="B"  name="xxRecValAftTxt_B0" ezfname="xxRecValAftTxt_B0" class="pPro" size="50" maxlength="100" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" style="border:none;background-color:transparent;padding:0px;"></td>
															</tr>
															</ezf:skip>
														</table>
													</div>
													<script type="text/javascript" defer>
														S21TableUI.initialize("parentBoxB", "BHEAD", "B");
													</script>
												</div>
												</td>
											</tr>
										</table>
									</div>
								</c:when>

							</c:choose>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
