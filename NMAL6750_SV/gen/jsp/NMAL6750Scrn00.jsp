<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170816173204 --%>
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
			<input type="hidden" name="pageID" value="NMAL6750Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contact Details">
			
			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" />
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" />
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" />
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" />
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" />

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Location Info" class="pTab_ON"><a href="#">Contact Dtl</a></li>
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
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
<!--
					<table border="0" align="center">
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="130">
									<col width="250">
									<col width="70">
									<col width="100">
									<col width="400">
									<tr>
										<td class="stab">Contact ID</td>
										<td><label ezfout name="ctacPsnNum_H1" ezfname="ctacPsnNum_H1">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
										<td><br /></td>
										<td><br /></td>
										<td><br /></td>
									</tr>
									<tr>
										<td class="stab">Last Name</td>
										<td><input type="text" size="30" maxlength="30" name="ctacPsnLastNm_H1" ezfname="ctacPsnLastNm_H1"></td>
										<td><br /></td>
										<td class="stab">Department</td>
										<td><select name="dsCtacPsnDeptCd_H2" ezfname="dsCtacPsnDeptCd_H2" ezflist="dsCtacPsnDeptCd_H1,dsCtacPsnDeptNm_H1,99, ,blank"><option>IT</option></select></td>
									</tr>
									<tr>
										<td class="stab">First Name</td>
										<td><input type="text" size="30" maxlength="30" name="ctacPsnFirstNm_H1" ezfname="ctacPsnFirstNm_H1"></td>
										<td><br /></td>
										<td class="stab">Title</td>
										<td><select name="dsCtacPsnTtlCd_H2" ezfname="dsCtacPsnTtlCd_H2" ezflist="dsCtacPsnTtlCd_H1,dsCtacPsnTtlNm_H1,99, ,blank"><option>DIRECTOR</option><option>SR. DIRECTOR</option><option>EXECUTIVE ASSISTANT</option><option>MANAGER</option></select></td>
									</tr>
									<tr>
										<td class="stab">Salutation</td>
										<td><select name="dsCtacPsnSaltCd_H2" ezfname="dsCtacPsnSaltCd_H2" ezflist="dsCtacPsnSaltCd_H1,dsCtacPsnSaltNm_H1,99, ,blank"><option>Mr.</option></select></td>
										<td><br /></td>
										<td class="stab">Relationship to CSA</td>
										<td><select name="ctacTpCd_H2" ezfname="ctacTpCd_H2" ezflist="ctacTpCd_H1,ctacTpNm_H1,99, ,blank"><option>METER READS</option><option>KEY OPERATOR</option><option>IT CONTACT</option></select></td>
									</tr>
									<tr>
										<td class="stab">Preferred Contact Type</td>
										<td><select name="dsCtacPntTpCd_H2" ezfname="dsCtacPntTpCd_H2" ezflist="dsCtacPntTpCd_H1,dsCtacPntTpNm_H1,99, ,blank"><option>Phone-Work</option></select></td>
										<td><br /></td>
										<td class="stab">Active</td>
										<td><input type="checkbox" name="ctacPsnActvFlg_H1" ezfname="ctacPsnActvFlg_H1" value="Y"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
-->
<%-- #################################################### DETAIL ################################################### --%>
					<table width="100%">
						<col valign="top">
						<tr>
							<td>
								<fieldset style="margin-left:10; width:700;">
									<legend>Related Account/Location</legend>
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<!--
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
													<col width="50">
													<col width="108">
													<col width="35">
													<col width="215">
													<col width="65" align="right">
													<col width="108">
													<col width="35">
													<col width="215">
													<col width="60">
													<col width="60">
													<tr>
														<td class="stab">
															<a href="#" onclick="sendServer('OpenWin_Acct')" tabindex="11">Account#</a>
														</td>
														<td><input type="text" size="14" maxlength="28" name="dsAcctNum_H1" ezfname="dsAcctNum_H1" tabindex="12" onKeydown="var keyEvent=this.event || window.event;if(keyEvent.keyCode=='13'){sendServer('GetAcctNm');}" ezftoupper></td>
														<td><input type="button" class="pBtn0" value=">>" name="GetAccountName" onclick="sendServer(this)" tabindex="13"></td>
														<td><input type="text" size="29" maxlength="60" name="dsAcctNm_H1" ezfname="dsAcctNm_H1" tabindex="-1" ezftoupper></td>
														<td class="stab">
															<a href="#" onclick="sendServer('OpenWin_Loc')" tabindex="11">Location#</a>
														</td>
														<td><input type="text" size="14" maxlength="28" name="locNum_H1" ezfname="locNum_H1" tabindex="12" onKeydown="var keyEvent=this.event || window.event;if(keyEvent.keyCode=='13'){sendServer('GetLocName');}" ezftoupper></td>
														<td><input type="button" class="pBtn0" value=">>" name="GetLocationName" onclick="sendServer(this)" tabindex="13"></td>
														<td><input type="text" size="29" maxlength="60" name="locNm_H1" ezfname="locNm_H1" tabindex="-1" ezftoupper></td>
														<td><input type="button"  class="pBtn4" value="Add" name="AddAccount" onclick="sendServer(this)"></td>
														<td><input type="button"  class="pBtn4" value="Delete" name="DeleteAccount" onclick="sendServer(this)"></td>
													</tr>
												</table>
											</td>
										</tr>
										-->
										<tr>
											<td>
												<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:none; width:1070; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<!--col align="center" width="40">		<!-- Check -->
														<col align="center" width="150">	<!-- Account Name -->
														<col align="center" width="70">		<!-- Account# -->
														<col align="center" width="70">		<!-- Location# -->
														<col align="center" width="170">	<!-- Address1 -->
														<col align="center" width="170">	<!-- Address2 -->
														<col align="center" width="70">		<!-- City -->
														<col align="center" width="50">		<!-- State -->
														<col align="center" width="80">		<!-- Postal Code -->
														<col align="center" width="110">	<!-- Start Date -->
														<col align="center" width="110">	<!-- End Date -->
														<!--col align="center" width="60">		<!-- PRIMARY -->
														<tr>
															<!--td class="pClothBs"></td-->
															<td class="pClothBs">Account Name</td>
															<td class="pClothBs">Account#</td>
															<td class="pClothBs">Location#</td>
															<td class="pClothBs">Address1</td>
															<td class="pClothBs">Address2</td>
															<td class="pClothBs">City</td>
															<td class="pClothBs">State</td>
															<td class="pClothBs">Postal Code</td>
															<td class="pClothBs">Start Date</td>
															<td class="pClothBs">End Date</td>
															<!--td class="pClothBs">Primary</td-->
														</tr>
													</table>
												</div>
												<div id="LeftTable_A" style="overflow-y:none; overflow-x:hidden; width:1070; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
														<!--col align="center" width="40">		<!-- Check -->
														<col width="150">		<!-- Account Name -->
														<col width="70">		<!-- Account# -->
														<col width="70">		<!-- Location# -->
														<col width="170">		<!-- Address1 -->
														<col width="170">		<!-- Address2 -->
														<col width="70">		<!-- City -->
														<col width="50">		<!-- State -->
														<col width="80">		<!-- Postal Code -->
														<col align="center" width="110">	<!-- Start Date -->
														<col align="center" width="110">	<!-- End Date -->
														<!--col align="center" width="60">		<!-- PRIMARY -->
														<ezf:row ezfHyo="A">
															<tr>
																<!--td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" value="Y" id="xxChkBox_A1{EZF_ROW_NUMBER}"></td-->
																<td><ezf:label name="dsAcctNm_A1" ezfName="dsAcctNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="dsAcctNum_A1" ezfName="dsAcctNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="locNum_A1" ezfName="locNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="firstLineAddr_A1" ezfName="firstLineAddr_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="scdLineAddr_A1" ezfName="scdLineAddr_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="ctyAddr_A1" ezfName="ctyAddr_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="stCd_A1" ezfName="stCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:label name="postCd_A1" ezfName="postCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effFromDt_A1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1{EZF_ROW_NUMBER}', 4);"></td>
																<td><ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effThruDt_A1{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1{EZF_ROW_NUMBER}', 4);"></td>
																<!--td><input type="checkbox" name="dsPrimLocFlg_A1" ezfname="dsPrimLocFlg_A1" ezfhyo="A" value="Y"></td-->
																<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
															</tr>
															
														</ezf:row>
														<ezf:skip>
														<!--
															<tr>
																<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"  value="N"></td>
																<td><label ezfout name="dsAcctNm_A1" ezfname="dsAcctNm_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><label ezfout name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																<td><label ezfout name="locNum_A1" ezfname="locNum_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																<td><label ezfout name="firstLineAddr_A1" ezfname="firstLineAddr_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																<td><label ezfout name="scdLineAddr_A1" ezfname="scdLineAddr_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																<td><label ezfout name="ctyAddr_A1" ezfname="ctyAddr_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																<td><label ezfout name="stCd_A1" ezfname="stCd_A1" ezfhyo="A">WW</label></td>
																<td><label ezfout name="postCd_A1" ezfname="postCd_A1" ezfhyo="A">12345</label></td>
																<td><input type="text" size="10" maxlength="10" name="effFromDt_A1" ezfname="effFromDt_A1" ezftoupper><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4);"></td>
																<td><input type="text" size="10" maxlength="10" name="effThruDt_A1" ezfname="effThruDt_A1" ezftoupper><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4);"></td>
																<td><input type="checkbox" name="dsPrimLocFlg_A1" ezfname="dsPrimLocFlg_A1" ezfhyo="A" value="Y"></td>
															</tr>
															<tr>
																<td><input type="checkbox" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A" value="N"></td>
																<td><label ezfout name="dsAcctNm_A1" ezfname="dsAcctNm_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></td>
																<td><label ezfout name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																<td><label ezfout name="locNum_A1" ezfname="locNum_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																<td><label ezfout name="firstLineAddr_A1" ezfname="firstLineAddr_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																<td><label ezfout name="scdLineAddr_A1" ezfname="scdLineAddr_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																<td><label ezfout name="ctyAddr_A1" ezfname="ctyAddr_A1" ezfhyo="A">WWWWWWWWW1WWWWWWWWW2</label></td>
																<td><label ezfout name="stCd_A1" ezfname="stCd_A1" ezfhyo="A">WW</label></td>
																<td><label ezfout name="postCd_A1" ezfname="postCd_A1" ezfhyo="A">12345</label></td>
																<td><input type="text" size="10" maxlength="10" name="effFromDt_A1" ezfname="effFromDt_A1" ezftoupper><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4);"></td>
																<td><input type="text" size="10" maxlength="10" name="effThruDt_A1" ezfname="effThruDt_A1" ezftoupper><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4);"></td>
																<td><input type="checkbox" name="dsPrimLocFlg_A1" ezfname="dsPrimLocFlg_A1" ezfhyo="A" value="Y"></td>
															</tr>-->
														</ezf:skip>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
<!-- -->
					<table border="0" align="center">
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="130">
									<col width="100">
									<col width="300">
									<tr>
										<td class="stab">Contact ID</td>
										<td><ezf:label name="ctacPsnNum_H1" ezfName="ctacPsnNum_H1" /></td>
										<td><ezf:inputButton name="OpenWin_CtacPsn" value="Contact" htmlClass="pBtn4" /></td>
									</tr>
									<tr>
										<td class="stab">Salutation</td>
										<td colspan="2"><ezf:select name="dsCtacPsnSaltCd_H2" ezfName="dsCtacPsnSaltCd_H2" ezfBlank="1" ezfCodeName="dsCtacPsnSaltCd_H1" ezfDispName="dsCtacPsnSaltNm_H1" /></td>
									</tr>
									<tr>
										<td class="stab">First Name</td>
										<td colspan="2"><ezf:inputText name="ctacPsnFirstNm_H1" ezfName="ctacPsnFirstNm_H1" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
									</tr>
									<tr>
										<td class="stab">Last Name</td>
										<td colspan="2"><ezf:inputText name="ctacPsnLastNm_H1" ezfName="ctacPsnLastNm_H1" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
									</tr>
									<tr>
										<td class="stab">Title</td>
										<td colspan="2"><ezf:select name="dsCtacPsnTtlCd_H2" ezfName="dsCtacPsnTtlCd_H2" ezfBlank="1" ezfCodeName="dsCtacPsnTtlCd_H1" ezfDispName="dsCtacPsnTtlNm_H1" /></td>
									</tr>
									<tr>
										<td class="stab">Department</td>
										<td colspan="2"><ezf:select name="dsCtacPsnDeptCd_H2" ezfName="dsCtacPsnDeptCd_H2" ezfBlank="1" ezfCodeName="dsCtacPsnDeptCd_H1" ezfDispName="dsCtacPsnDeptNm_H1" /></td>
									</tr>
									<tr>
										<td class="stab">Active</td>
										<td colspan="2"><ezf:inputCheckBox name="ctacPsnActvFlg_H1" ezfName="ctacPsnActvFlg_H1" value="Y" /></td>
									</tr>
									<tr>
										<td class="stab">Preferred Contact Type</td>
										<td colspan="2"><ezf:select name="dsCtacPntTpCd_H2" ezfName="dsCtacPntTpCd_H2" ezfBlank="1" ezfCodeName="dsCtacPntTpCd_H1" ezfDispName="dsCtacPntTpNm_H1" /></td>
									</tr>

									<!--tr>
										<td><br /></td>
										<td class="stab">Relationship to CSA</td>
										<td><select name="ctacTpCd_H2" ezfname="ctacTpCd_H2" ezflist="ctacTpCd_H1,ctacTpNm_H1,99, ,blank"><option>METER READS</option><option>KEY OPERATOR</option><option>IT CONTACT</option></select></td>
									</tr-->
								</table>
							</td>
							</td>
							<td>
								<fieldset style="margin-left:10; width:400;">
									<legend>Contact Relationships with CSA</legend>
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<col width="450">
										<tr>
											<td>
												<div id="LeftTable_R_Top" style="overflow-x:none; overflow-y:none; width:400; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
														<col align="center" width="300">
														<col align="center" width="100">
														<tr>
															<td class="pClothBs">Contact Role</td>
															<td class="pClothBs">Available</td>
															<td>
															</td>
														</tr>
													</table>
												</div>
												<div id="LeftTable_R" style="overflow-y:scroll; height:170; overflow-x:hidden; width:420; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" id="R" style="table-layout: fixed">
														<col align="left" width="300">		<!-- Contact Role -->
														<col align="center" width="100">		<!-- Available -->
														<ezf:row ezfHyo="R">
															<tr>
																<td><ezf:label name="ctacTpDescTxt_R1" ezfName="ctacTpDescTxt_R1" ezfHyo="R" ezfArrayNo="0" /></td>
																<td><ezf:inputCheckBox name="xxChkBox_R1" ezfName="xxChkBox_R1" value="Y" ezfHyo="R" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_R1{EZF_ROW_NUMBER}\""/></td>
															</tr>
														</ezf:row>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
<!-- -->

					<table width="100%">
						<col valign="top">
						<tr>
							<td>
								<fieldset style="margin-left:10; width:700;">
									<legend>Contact Points</legend>
									<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="35">
													<col width="35">
													<col width="90">
													<col width="100">
													<col width="675">
													<tr>
														<td><ezf:inputButton name="AddContactPoint" value="Add" htmlClass="pBtn4" /></td>
														<td><ezf:inputButton name="DeleteContactPoint" value="Delete" htmlClass="pBtn4" /></td>
														<td></td>
														<td></td>
														<td align="right"><label>Telephone # Format : "999-999-9999" or "9999999999"</label></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<div id="LeftTable_B_Top" style="overflow-x:none; overflow-y:none; width:1050; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
															<col align="center" width="40">		<!-- Check -->
															<col align="center" width="150">	<!-- Contact Type -->
															<col align="center" width="70">		<!-- Format -->
															<col align="center" width="530">	<!-- Value -->
															<col align="center" width="100">	<!-- Extension -->
															<col align="center" width="80">		<!-- Opt Out -->
															<col align="center" width="80">		<!-- Active -->
															<tr>
																<td class="pClothBs"></td>
																<td class="pClothBs">Contact Type</td>
																<td class="pClothBs">Format</td>
																<td class="pClothBs">Value</td>
																<td class="pClothBs">Extension</td>
																<td class="pClothBs">Opt Out</td>
																<td class="pClothBs">Active</td>
															</tr>
													</table>
												</div>
												<div id="LeftTable_B" style="overflow-y:scroll; height:156; overflow-x:hidden; width:1070; float:left;">
													<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed">
															<col align="center" width="40">	<!-- Check -->
															<col width="150">	<!-- Contact Type -->
															<col width="70">	<!-- Format -->
															<col width="530">	<!-- Value -->
															<col width="100">	<!-- Extension -->
															<col align="center" width="80">		<!-- Opt Out -->
															<col align="center" width="80">		<!-- Active -->
														<ezf:row ezfHyo="B">
															<tr>
																<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B1{EZF_ROW_NUMBER}\""/></td>
																<td><ezf:select name="dsCtacPntTpCd_B1" ezfName="dsCtacPntTpCd_B1" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsCtacPntTpCd_L1" ezfDispName="dsCtacPntTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_ContactType', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width:140px;\""/></td>
																<td><ezf:select name="ctacPntFmtTxt_B1" ezfName="ctacPntFmtTxt_B1" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="ctacPntFmtTxt_CD" ezfDispName="ctacPntFmtTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_Format', {EZF_ROW_NUMBER})\"" otherAttr=" style=\"width:65px;\""/>
																</td>
																<td><ezf:inputText name="dsCtacPntValTxt_B1" ezfName="dsCtacPntValTxt_B1" ezfHyo="B" ezfArrayNo="0" otherEvent1=" onblur=\"sendServer('OnBlur_Value', {EZF_ROW_NUMBER})\"" otherAttr=" size=\"73\" maxlength=\"100\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="dsCtacPsnExtnNum_B1" ezfName="dsCtacPsnExtnNum_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"12\" ezftoupper=\"\""/></td>
																<td><ezf:inputCheckBox name="dsOpsOutFlg_B1" ezfName="dsOpsOutFlg_B1" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																<td><ezf:inputCheckBox name="dsCtacPntActvFlg_B1" ezfName="dsCtacPntActvFlg_B1" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_B1" ezfName="xxRecHistCratTs_B1" ezfHyo="B" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistCratByNm_B1" ezfName="xxRecHistCratByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdTs_B1" ezfName="xxRecHistUpdTs_B1" ezfHyo="B" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdByNm_B1" ezfName="xxRecHistUpdByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistTblNm_B1" ezfName="xxRecHistTblNm_B1" ezfHyo="B" ezfArrayNo="0" />
																</td>
															</tr>
														</ezf:row>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
					<table width="100%"  style="margin-left:10;">
						<col valign="top">
						<tr>
							<td>
								Contact Note<br />
								<ezf:textArea name="ctacPsnCmntTxt_H1" ezfName="ctacPsnCmntTxt_H1" otherAttr=" cols=\"150\" rows=\"3\""/>
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
	function onFocusEvent(item){
		if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
		}
	}

	function onBlurEvent(item){
		if(item.value.length === 1){
			item.value = '0' + item.value.charAt(0) + ':00';
		}else if(item.value.length === 2){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':00';
		}else if(item.value.length === 3){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + '0';
		}else if(item.value.length === 4){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}else if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}
	}
</script>

<%-- **** Task specific area ends here **** --%>
