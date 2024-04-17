<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230110160754 --%>
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
			<input type="hidden" name="pageID" value="NYEL8840Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Delegation">

			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<!-- ###TAB - HEAD -->
<!-- After Convert to JSP, this area suppose to be deleted [FROM] -->
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Work List" class="pTab_OFF"><a href="#">Work List</a></li>
													<li title="Delegation" class="pTab_ON"><a href="#">Delegation</a></li>
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
<!-- After Convert to JSP, this area suppose to be deleted [TO] -->
							<!-- include S21BusinessProcessTAB -->
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

							<div class="pTab_BODY">
								<table border="0" cellpadding="1" cellspacing="1">
									<tr>
										<td>
											<table border="0" cellspacing="0" cellpadding="0" style="width:98%; text-align:left;">
												<col width="100">
												<col width="100">
												<col width="400">
												<col width="100">
												<col width="100">
												
												
												<tr>
													<td class="stab">
													Process Name
													</td>
													<td colspan="3">
														<ezf:select name="wfBizAppId" ezfName="wfBizAppId" ezfCodeName="wfBizAppId_L" ezfDispName="wfDescTxt_L" otherAttr=" style=\"width: 200px\""/>
													</td>
												</tr>

												<tr>
													<td class="stab">
													<ezf:anchor name="xxWfAsgCd_FK" ezfName="xxWfAsgCd_FK" ezfEmulateBtn="1" ezfGuard="OpenWin_CmnBigAssigner" >
													Assigner
													</ezf:anchor>
													</td>
													<td>
														<ezf:inputText name="xxWfAsgCd_F" ezfName="xxWfAsgCd_F" otherAttr=" size=\"7\" maxlength=\"7\" ezftoupper=\"\""/>
														<ezf:inputButton name="Get_AssignerName" value=">>" htmlClass="pBtn1" otherAttr=" id=\"Get_AssignerName\""/>
													</td>
													<td class="pOut">
														<ezf:label name="xxWfAsgNm_F" ezfName="xxWfAsgNm_F" />
													</td>
													<td valign="bottom" align="center">
														<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">
													<ezf:anchor name="axxWfAsgCd_LK" ezfName="axxWfAsgCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CmnBigAssignee" >
													Assignee
													</ezf:anchor>
													</td>
													<td>
														<ezf:inputText name="xxWfAsgCd" ezfName="xxWfAsgCd" otherAttr=" size=\"7\" maxlength=\"7\" ezftoupper=\"\""/>
														<ezf:inputButton name="Get_AssigneeName" value=">>" htmlClass="pBtn1" otherAttr=" id=\"Get_AssigneeName\""/>
													</td>
													<td class="pOut">
														<ezf:label name="xxWfAsgNm" ezfName="xxWfAsgNm" />
													</td>
													<td></td>
												</tr>
												
												<tr>
													<td class="stab">
													Period
													</td>
													<td colspan="2">
														<ezf:inputText name="effFromDt" ezfName="effFromDt" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);">
														<ezf:select name="effFromHourMn_H" ezfName="effFromHourMn_H" ezfCodeName="effFromHourMn_FH" ezfDispName="wfDescTxt_FH" />
														:
														<ezf:select name="effFromHourMn_M" ezfName="effFromHourMn_M" ezfCodeName="effFromHourMn_FM" ezfDispName="wfDescTxt_FM" />
														-
														<ezf:inputText name="effThruDt" ezfName="effThruDt" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);">
														<ezf:select name="effThruHourMn_H" ezfName="effThruHourMn_H" ezfCodeName="effThruHourMn_TH" ezfDispName="wfDescTxt_TH" />
														:
														<ezf:select name="effThruHourMn_M" ezfName="effThruHourMn_M" ezfCodeName="effThruHourMn_TM" ezfDispName="wfDescTxt_TM" />
													</td>
												</tr>

												<tr>
													<td class="stab">
													Comment
													</td>
													<td colspan="2">
														<ezf:textArea name="wfDescTxt" ezfName="wfDescTxt" otherAttr=" rows=\"4\" cols=\"61\""/>
													</td>
													<td valign="bottom" align="center">
														<ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" otherAttr=" id=\"Add\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<hr>
							<!-- ********* PAGENATION ********* -->
								<table width="98%">
									<tr align="right">
										<td>
											<ezf:skip>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="5">
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
													<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
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
							<!-- ********* PAGENATION ********* -->
								<table>
									<tr>
										<td>
										<table border="1" cellspacing="0" cellpadding="1" style="text-align:center; table-layout:fixed;" width="1105px">
											<col width="28">	<!-- check -->
											<col width="80">	<!-- Assignee Cd -->
											<col width="230">	<!-- Assignee Nm -->
											<col width="220">	<!-- Process Nm -->
											<col width="120">	<!-- From -->
											<col width="120">	<!-- To -->
											<col width="265">	<!-- Details -->
											<tr>
												<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs">Assignee Cd</td>
												<td class="pClothBs">Assignee Name</td>
												<td class="pClothBs">Process Name</td>
												<td class="pClothBs">From</td>
												<td class="pClothBs">To</td>
												<td class="pClothBs">Comment</td>
											</tr>
										</table>
										<div id="rightTbl" class="breakWord" style="width:1122px; height:296px; display:block; overflow-y:scroll; overflow-x:hidden;">
											<table id="A" border="1" cellspacing="0" cellpadding="1" width="1105px" style="table-layout:fixed;">
												<col width="28" align="center">	<!-- check -->
												<col width="80" align="left">		<!-- Assignee Cd -->
												<col width="230" align="left">		<!-- Assignee Name -->
												<col width="220" align="left">		<!-- Process Name -->
												<col width="120">	<!-- From -->
												<col width="120">	<!-- To -->
												<col width="265" align="left">		<!-- Details -->
												<tbody>
												<ezf:row ezfHyo="A">
												<tr height="42px">
													<td>
														<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A\""/>
													</td>
													<td>
														<ezf:inputText name="xxWfAsgCd_A1" ezfName="xxWfAsgCd_A1" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"7\" style=\"border:none;background-color:transparent;padding:0px;\""/>
													</td>
													<td>
														<ezf:inputText name="xxWfAsgNm_A1" ezfName="xxWfAsgNm_A1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"255\" style=\"border:none;background-color:transparent;padding:0px;\""/>
													</td>
													<td>
														<ezf:inputText name="wfDescTxt_AP" ezfName="wfDescTxt_AP" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"255\" style=\"border:none;background-color:transparent;padding:0px;\""/>
													</td>
													<td>
														<ezf:label name="wfDescTxt_F1" ezfName="wfDescTxt_F1" ezfHyo="A" ezfArrayNo="0" />
													</td>
													<td>
														<ezf:label name="wfDescTxt_T1" ezfName="wfDescTxt_T1" ezfHyo="A" ezfArrayNo="0" />
													</td>
													<td>
														<ezf:textArea name="wfDescTxt_A1" ezfName="wfDescTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" rows=\"3\" cols=\"38\" style=\"border:none;background-color:transparent;padding:0px;overflow:auto;\""/>
													</td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												<tr height="42px">
													<td>
														<input type="checkBox" id="xxChkBox_A" name="xxChkBox_A" ezfname="xxChkBox_A" ezfGetLineNoHyo="A">
													</td>
													<td>
														<input type="text" name="assigneeCd_A" value="H99900" size="7" maxlength="7" ezfname="assigneeCd_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<input type="text" name="assigneeNm_A" value="Richard" size="40" maxlength="40" ezfname="assigneeNm_A" style="border:none;background-color:transparent;padding:0px;">
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														User</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														mm/dd/yyyy</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														HH:MM</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														12/31/9999</label>
													</td>
													<td>
														<label ezfout ezfhyo="A" name="type_A" ezfname="type_A">
														23:59</label>
													</td>
													<td>
														<textarea rows="3" cols="38" name="dtlTxt_A" ezfname="dtlTxt_A" style="border:none;background-color:transparent;padding:0px;overflow:auto;" readonly>xxx</textarea>
													</td>
												</tr>
												</tr>
												</ezf:skip>
												</tbody>
											</table>
										</div>
										<table>
											<tr>
												<td>
													<ezf:inputButton name="Delete" value="Delete Row" htmlClass="pBtn6" otherAttr=" id=\"Delete\""/>
												</td>
											</tr>
										</table>
										</td>
									</tr>
								</table>
							</div><!-- pTab_BODY -->
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
