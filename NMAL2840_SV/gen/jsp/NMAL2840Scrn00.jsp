<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160713092014 --%>
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
			<input type="hidden" name="pageID" value="NMAL2840Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="DNB Cleansing">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		<tr>
			<td>
			<%-- include S21BusinessProcessTAB --%>
<ezf:skip>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
					<tr>
						<td width="1070px" height="28px" valign="bottom">
							<div id='S21BusinessProcessTabPage_1' style="display:none">
								<table border="0" cellpadding="0" cellspacing="0">
									<td>
										<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
											<tr title='tab'>
												<td width="90px">
													<a href="#" onclick="onClickBusinessProcessTAB( '' ); return;">aaa</a>
												</td>
												<td width="17px" align="center">
													<a href="#" onclick="onClickBusinessProcessMS( '', '' ); return false;"><img src="./img/tab/multiscreen.png" border="0" onmouseover="this.src='./img/tab/multiscreenmouseover.png'" onmouseout="this.src='./img/tab/multiscreen.png'"></a>
												<td width="90px" align="center" class="disabled">
													abbrev
												</td>
												<td width="17px" align="center">
													<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
												</td>
												<td width="107px" align="center" class="same">
													<c:out value="${taskInfo.abbreviation}"/>
												</td>
											</tr>
										</table>
									</td>
								</table>
							</div>
						</td>
					</tr>
				</table>
</ezf:skip>

<!-- ######################################## HEADER ######################################## -->
 
				<%-- ###TAB - HEAD --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
 
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table height="350">
						<col valign="top">
						<tr>
							<td>
								<table width="1120">
									<col valign="top">
									<tr>
										<td>
											<table>
												<col valign="top" width="555">
												<col valign="top" width="555">
												<tr>
													<td class="stab">
							 							<fieldset>
							 								<legend><b>Extract for DNB Cleansing</b></legend>
															<table width="100%" cellpadding="0" cellspacing="0" border="0">
																<col valign="top">
																<tr>
																	<td>
																		<table width="500" border="1" rules="none" cellpadding="0" cellspacing="0">
																			<tr>
																				<!-- Last extract send to DNB -->
																				<td class="stab"><center>Last extract send to DNB on : <ezf:label name="xxDtTm_1" ezfName="xxDtTm_1" /></center></td>
																	
																			</tr>
																		</table>

																		<table width="500" border="0" cellpadding="0" cellspacing="0">
																			<col width="">
																			<tr>
																				<td class="stab"><b><u>Extract Filters:</u></b></td>
																			</tr>
																		</table>

																		<!-- 1st line of Extract for DNB Cleansing -->
																		<table width="500" border="0" cellpadding="0" cellspacing="0">
																			<col width="10">
																			<col width="10">
																			<col width="150">
																			<tr>
																				<!-- Extra Mode -->
																				<td></td>
																				<td class="stab">Extract Mode:</td>
																				<td class="stab">
																					<ezf:select name="dunsCritCd_PS" ezfName="dunsCritCd_PS" ezfCodeName="dunsCritCd_PC" ezfDispName="dunsCritDescTxt_PC" otherEvent1=" onchange=\"sendServer('OnChange_PullDown_Screen_Protect')\"" otherAttr=" style=\"width:100\""/>
																				</td>
																				<td></td>
																			</tr>
																		</table>

																		<table width="500" border="1" rules="none" cellpadding="0" cellspacing="0">
																			<!-- Include Customers -->
																			<col width="10">
																			<col width="">
																			<!-- Include Prospects -->
																			<col width="10">
																			<col width="">
																			<tr>
																				<!-- Include Customers -->
																				<td class="stab"><ezf:inputCheckBox name="dunsCritDefValFlg_11" ezfName="dunsCritDefValFlg_11" value="Y" /></td>
																				<td class="stab">Include Customers</td>
																				<!-- Include Prospects -->
																				<td class="stab"><ezf:inputCheckBox name="dunsCritDefValFlg_12" ezfName="dunsCritDefValFlg_12" value="Y" /></td>
																				<td class="stab">Include Prospects</td>
																			</tr>
																		</table>

																		<table width="500" border="1" rules="none" cellpadding="0" cellspacing="0">
																			<col width="20">
																			<col width="">
																			<tr>
																				<!-- Include Records Never Cleansed Before -->
																				<td class="stab"><ezf:inputCheckBox name="dunsCritDefValFlg_21" ezfName="dunsCritDefValFlg_21" value="Y" /></td>
																				<td class="stab">Include Records Never Cleansed Before</td>
																			</tr>
																		</table>

																		<table width="500" border="1" rules="none" cellpadding="0" cellspacing="0">
																			<col width="10">
																			<col width="300">
																			<col width="140">
																			<tr>
																				<!-- Include Records with Name Change since Last Extract -->
																				<td class="stab"><ezf:inputCheckBox name="dunsCritDefValFlg_31" ezfName="dunsCritDefValFlg_31" value="Y" /></td>
																				<td class="stab">Include Records with Name Change since Last Extract</td>
																				<td></td>
																			</tr>
																			<tr>
																				<!-- Include Records with Address Change since Last Extract -->
																				<td class="stab"><ezf:inputCheckBox name="dunsCritDefValFlg_32" ezfName="dunsCritDefValFlg_32" value="Y" /></td>
																				<td class="stab">Include Records with Address Change since Last Extract</td>
																				<td></td>
																			</tr>
																			<tr>
																				<!-- Include Records with Phone# Change since Last Extract -->
																				<td class="stab"><ezf:inputCheckBox name="dunsCritDefValFlg_33" ezfName="dunsCritDefValFlg_33" value="Y" /></td>
																				<td class="stab">Include Records with Phone# Change since Last Extract</td>
																				<td></td>
																			</tr>
																			<tr>
																				<!-- Extra Mode -->
																				<td></td>
																				<td class="stab">Include records cleansed before:</td>
																				<td>
																					<ezf:inputText name="effFromDt" ezfName="effFromDt" value="05/25/2016" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >
																				</td>
																			</tr>
																		</table>

																		<table width="540" border="0">
																			<col width="10">
																			<col width="450">
																			<col width="80">
																			<tr>
																				<td><br><br><br><br></td>
																				<td><ezf:inputButton name="ExtractSend" value="Extract & Send" htmlClass="pBtn8" /></td>
																				<td><ezf:inputButton name="ExtractReset" value="Reset Filters" htmlClass="pBtn8" /></td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
							 							</fieldset>
													</td>

													<td class="stab">
							 							<fieldset>
							 								<legend><b>Review and Process DNB Data</b></legend>
															<table width="100%" cellpadding="0" cellspacing="0" border="0">
																<col valign="top">
																<tr>
																	<td>
																		<table width="500" border="1" rules="none" cellpadding="0" cellspacing="0">
																			<tr>
																				<!-- Last DNB file uploaded -->
																				<td class="stab"><center>Last DNB file uploaded on : <ezf:label name="xxDtTm_2" ezfName="xxDtTm_2" /></center></td>
																			</tr>
																		</table>

																		<table width="500" border="0" cellpadding="0" cellspacing="0">
																			<tr>
																				<td class="stab"><b><u>Export Filters:</u></b></td>
																			</tr>
																		</table>

																		<table width="500" cellpadding="0" cellspacing="0" border="0">
																			<col width="10">
																			<col width="180">
																			<col width="200">
																			<col width="10">
																			<col width="30">
																			<tr>
																				<td></td>
																				<td class="stab" height="10">Imported DNB Date:</td>
																				<td class="stab">
																					<ezf:select name="xxDtTm_PS" ezfName="xxDtTm_PS" ezfBlank="1" ezfCodeName="xxDtTm_PC" ezfDispName="xxDtTm_PN" otherAttr=" style=\"width:170\""/>
																				</td>
																				<td></td>
																				<td></td>
																			</tr>
																		</table>

																		<table width="500" cellpadding="0" cellspacing="0" border="0">
																			<col width="10">
																			<col width="200">
																			<col width="200">
																			<col width="10">
																			<col width="30">
																			<tr>
																				<td></td>
																				<td class="stab">DNB Name Profile Code(*)</td>
																				<td><ezf:inputText name="dunsCritDescTxt_51" ezfName="dunsCritDescTxt_51" value="00,02,05" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
																				<td class="stab"><ezf:inputCheckBox name="dunsCritDefValFlg_51" ezfName="dunsCritDefValFlg_51" value="Y" onClick="sendServer('OnClick_CheckBox_Screen_Protect')" /></td>
																				<td class="stab">Is Null</td>
																			</tr>
																			<tr>
																				<td></td>
																				<td class="stab">DNB Street No Profile Code(*)</td>
																				<td><ezf:inputText name="dunsCritDescTxt_52" ezfName="dunsCritDescTxt_52" value="00" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
																				<td class="stab"><ezf:inputCheckBox name="dunsCritDefValFlg_52" ezfName="dunsCritDefValFlg_52" value="Y" onClick="sendServer('OnClick_CheckBox_Screen_Protect')" /></td>
																				<td class="stab">Is Null</td>
																			</tr>
																			<tr>
																				<td></td>
																				<td class="stab">DNB Street Name Profile Code(*)</td>
																				<td><ezf:inputText name="dunsCritDescTxt_53" ezfName="dunsCritDescTxt_53" value="00" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
																				<td class="stab"><ezf:inputCheckBox name="dunsCritDefValFlg_53" ezfName="dunsCritDefValFlg_53" value="Y" onClick="sendServer('OnClick_CheckBox_Screen_Protect')" /></td>
																				<td class="stab">Is Null</td>
																			</tr>
																			<tr>
																				<td></td>
																				<td class="stab">DNB Match Grade(*)</td>
																				<td><ezf:inputText name="dunsCritDescTxt_54" ezfName="dunsCritDescTxt_54" value="AA%" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
																				<td class="stab"><ezf:inputCheckBox name="dunsCritDefValFlg_54" ezfName="dunsCritDefValFlg_54" value="Y" onClick="sendServer('OnClick_CheckBox_Screen_Protect')" /></td>
																				<td class="stab">Is Null</td>
																			</tr>
																			<tr>
																				<td></td>
																				<td class="stab">DNB BEMFAB Code(*)</td>
																				<td><ezf:inputText name="dunsCritDescTxt_55" ezfName="dunsCritDescTxt_55" value="M" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
																				<td class="stab"><ezf:inputCheckBox name="dunsCritDefValFlg_55" ezfName="dunsCritDefValFlg_55" value="Y" onClick="sendServer('OnClick_CheckBox_Screen_Protect')" /></td>
																				<td class="stab">Is Null</td>
																			</tr>
																			<tr>
																				<td></td>
																				<td class="stab">DNB Nixie A(*)</td>
																				<td><ezf:inputText name="dunsCritDescTxt_56" ezfName="dunsCritDescTxt_56" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
																				<td class="stab"><ezf:inputCheckBox name="dunsCritDefValFlg_56" ezfName="dunsCritDefValFlg_56" value="Y" onClick="sendServer('OnClick_CheckBox_Screen_Protect')" /></td>
																				<td class="stab">Is Null</td>
																			</tr>
																			<tr>
																				<td></td>
																				<td class="stab">DNB Confidence Code(*)</td>
																				<td><ezf:inputText name="dunsCritDescTxt_57" ezfName="dunsCritDescTxt_57" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
																				<td class="stab"><ezf:inputCheckBox name="dunsCritDefValFlg_57" ezfName="dunsCritDefValFlg_57" value="Y" onClick="sendServer('OnClick_CheckBox_Screen_Protect')" /></td>
																				<td class="stab">Is Null</td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>

															<table>
																<col width="10">
																<col width="180">
																<col width="100">
																<col width="100">
																<col width="80">
																<tr>
																	<td></td>
																	<td><ezf:inputButton name="ReviewReset" value="Reset Filters" htmlClass="pBtn8" /></td>
																	<td></td>
																	<td><ezf:inputButton name="ExpForReview" value="Export For Review" htmlClass="pBtn10" /></td>
																	<td><ezf:inputButton name="UpdateS21" value="Update S21" htmlClass="pBtn8" /></td>
																</tr>
																</tr>
															</table>

															<table>
																<col width="100">
																<col width="460"align="right">
																<tr>
																	<td></td>
																	<td class="stab">*The maximum number of about <ezf:label name="xxRowNum" ezfName="xxRowNum" htmlClass="stab" /> rows cannot be uploaded.</td>
																	<td></td>
																	<td></td>
																	<td></td>
																</tr>
															</table>
														</fieldset>
													</td>
												</tr>
											</table>
										</td>
									</tr>

									<tr>
										<td class="stab">
											<fieldset height="280" width="1000">
				 								<legend><b>Audit Information</b></legend>
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
												<ezf:skip>
												<table>
													<col width="730">
													<tr>
														<td></td>
														<td>
															<table border="0" cellpadding="1" cellspacing="0">
																<col >
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
																	<td></td>
																	<td class="stab">Showing</td>
																	<td class="pOut">1</td>
																	<td class="stab">to</td>
																	<td class="pOut">40</td>
																	<td class="stab">of</td>
																	<td class="pOut">200</td>
																	<td>&nbsp;</td>
																	<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
												</ezf:skip>
												<table border="0" cellpadding="1" cellspacing="0" width="99%">
													<tr align="right">
														<td>
															<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"     value="A" />
																<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
																<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
																<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
																<jsp:param name="ViewMode"    value="FULL" />
															</jsp:include>
														</td>
													</tr>
												</table>
<!-- ######################################## TO (COMMON)PAGENATION ###################################### -->
<!-- ######################################## Search Result   - [START] ################################## -->
												<table>
													<tr>
														<td valign="top">
															<!-- Right TBL Header -->
															<div style="overflo:visible; width:1060; display:block; overflow:hidden; margin:0px;padding:0px;">
																<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1053px" style="margin-right:20px">
																	<col width="173" align="center"><!-- Process -->
																	<col width="173" align="center"><!-- File Name -->
																	<col width="93"  align="center"><!-- No. of Recoreds -->
																	<col width="153"  align="center"><!-- Processed On -->
																	<col width="153" align="center"><!-- Processed By -->
																	<col width="313" align="center"><!-- Comments -->

																	<tr height="37">
																		<td id="AH0" class="pClothBs">Process</td>
																		<td id="AH1" class="pClothBs">File Name</td>
																		<td id="AH2" class="pClothBs">No. of Records</td>
																		<td id="AH3" class="pClothBs">Processed On</td>
																		<td id="AH4" class="pClothBs">Processed By</td>
																		<td id="AH5" class="pClothBs">Comments</td>
																	</tr>
																</table>
															</div>

															<div id="rightTBL" style="overflow-x:hidden; overflow-y:scroll; height:200; width:1075; display:block; margin:0px; padding:0px;">
																<!-- Right TBL Main -->
																<table border="1" cellpadding="1" cellspacing="0" id="A2" style="table-layout: fixed" width="1050px">
																	<col width="173" align="center"><!-- Process -->
																	<col width="173" align="center"><!-- File Name -->
																	<col width="93"  align="right"><!-- No. of Recoreds -->
																	<col width="153" align="center"><!-- Processed On -->
																	<col width="153" align="center"><!-- Processed By -->
																	<col width="313" align="center"><!-- Comments -->

																	<ezf:row ezfHyo="A">
																	<tr id="id_row{EZF_ROW_NUMBER}">
																		<td><ezf:inputText name="dunsProcTpDescTxt" ezfName="dunsProcTpDescTxt" value="1234567890123456789012345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"50\""/></td>
																		<td><ezf:inputText name="dunsFileNm_2" ezfName="dunsFileNm_2" value="1234567890123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"256\""/></td>
																		<td><ezf:label name="dunsFileLineNum" ezfName="dunsFileLineNum" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:label name="xxDtTm_4" ezfName="xxDtTm_4" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="fill103Txt" ezfName="fill103Txt" value="1234567890123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"30\""/></td>
																		<td><ezf:inputText name="xxDunsProcCmntTxt" ezfName="xxDunsProcCmntTxt" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"51\" maxlength=\"4000\""/></td>
																	</tr>
																	</ezf:row>
																	<ezf:skip>
																	<tr class="pEvenNumberBGcolor" height="22">
																		<td><input type="text" size="24" maxlength="50" value="1234567890123456789012345678" name="dunsProcTpDescTxt_A1" ezfname="dunsProcTpDescTxt_A1" readonly></td>
																		<td><input type="text" size="24" maxlength="256" value="1234567890123456789012345" name="dunsFileNm_2" ezfname="dunsFileNm_2" readonly ezfhyo="A"></td>
																		<td><label name="dunsFileLineNum" ezfname="dunsFileLineNum" readonly ezfhyo="A">123456789</label></td>
																		<td><label name="dunsProcTs_4" ezfname="dunsProcTs_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
																		<td><input type="text" size="21" maxlength="30" value="1234567890123456789012345" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																		<td><input type="text" size="51" maxlength="4000" value="12345678901234567890123456789012345678901234567890" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																	</tr>
																	<tr class="pEvenNumberBGcolor" height="22">
																		<td><input type="text" size="24" maxlength="50" value="1234567890123456789012345678" name="dunsProcTpDescTxt_A1" ezfname="dunsProcTpDescTxt_A1" readonly></td>
																		<td><input type="text" size="24" maxlength="256" value="1234567890123456789012345" name="dunsFileNm_A1" ezfname="dunsFileNm_A1" readonly></td>
																		<td><label name="dunsFileLineNum" ezfname="dunsFileLineNum" readonly ezfhyo="A">123456789</label></td>
																		<td><label name="dunsProcTs_4" ezfname="dunsProcTs_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
																		<td><input type="text" size="21" maxlength="30" value="1234567890123456789012345" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																		<td><input type="text" size="51" maxlength="4000" value="12345678901234567890123456789012345678901234567890" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																	</tr>
																	<tr class="pEvenNumberBGcolor" height="22">
																		<td><input type="text" size="24" maxlength="50" value="1234567890123456789012345678" name="dunsProcTpDescTxt_A1" ezfname="dunsProcTpDescTxt_A1" readonly></td>
																		<td><input type="text" size="24" maxlength="256" value="1234567890123456789012345" name="dunsFileNm_A1" ezfname="dunsFileNm_A1" readonly></td>
																		<td><label name="dunsFileLineNum" ezfname="dunsFileLineNum" readonly ezfhyo="A">123456789</label></td>
																		<td><label name="dunsProcTs_4" ezfname="dunsProcTs_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
																		<td><input type="text" size="21" maxlength="30" value="1234567890123456789012345" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																		<td><input type="text" size="51" maxlength="4000" value="12345678901234567890123456789012345678901234567890" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																	</tr>
																	<tr class="pEvenNumberBGcolor" height="22">
																		<td><input type="text" size="24" maxlength="50" value="1234567890123456789012345678" name="dunsProcTpDescTxt_A1" ezfname="dunsProcTpDescTxt_A1" readonly></td>
																		<td><input type="text" size="24" maxlength="256" value="1234567890123456789012345" name="dunsFileNm_A1" ezfname="dunsFileNm_A1" readonly></td>
																		<td><label name="dunsFileLineNum" ezfname="dunsFileLineNum" readonly ezfhyo="A">123456789</label></td>
																		<td><label name="dunsProcTs_4" ezfname="dunsProcTs_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
																		<td><input type="text" size="21" maxlength="30" value="1234567890123456789012345" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																		<td><input type="text" size="51" maxlength="4000" value="12345678901234567890123456789012345678901234567890" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																	</tr>
																	<tr class="pEvenNumberBGcolor" height="22">
																		<td><input type="text" size="24" maxlength="50" value="1234567890123456789012345678" name="dunsProcTpDescTxt_A1" ezfname="dunsProcTpDescTxt_A1" readonly></td>
																		<td><input type="text" size="24" maxlength="256" value="1234567890123456789012345" name="dunsFileNm_A1" ezfname="dunsFileNm_A1" readonly></td>
																		<td><label name="dunsFileLineNum" ezfname="dunsFileLineNum" readonly ezfhyo="A">123456789</label></td>
																		<td><label name="dunsProcTs_4" ezfname="dunsProcTs_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
																		<td><input type="text" size="21" maxlength="30" value="1234567890123456789012345" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																		<td><input type="text" size="51" maxlength="4000" value="12345678901234567890123456789012345678901234567890" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																	</tr>
																	<tr class="pEvenNumberBGcolor" height="22">
																		<td><input type="text" size="24" maxlength="50" value="1234567890123456789012345678" name="dunsProcTpDescTxt_A1" ezfname="dunsProcTpDescTxt_A1" readonly></td>
																		<td><input type="text" size="24" maxlength="256" value="1234567890123456789012345" name="dunsFileNm_A1" ezfname="dunsFileNm_A1" readonly></td>
																		<td><label name="dunsFileLineNum" ezfname="dunsFileLineNum" readonly ezfhyo="A">123456789</label></td>
																		<td><label name="dunsProcTs_4" ezfname="dunsProcTs_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
																		<td><input type="text" size="21" maxlength="30" value="1234567890123456789012345" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																		<td><input type="text" size="51" maxlength="4000" value="12345678901234567890123456789012345678901234567890" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																	</tr>
																	<tr class="pEvenNumberBGcolor" height="22">
																		<td><input type="text" size="24" maxlength="50" value="1234567890123456789012345678" name="dunsProcTpDescTxt_A1" ezfname="dunsProcTpDescTxt_A1" readonly></td>
																		<td><input type="text" size="24" maxlength="256" value="1234567890123456789012345" name="dunsFileNm_A1" ezfname="dunsFileNm_A1" readonly></td>
																		<td><label name="dunsFileLineNum" ezfname="dunsFileLineNum" readonly ezfhyo="A">123456789</label></td>
																		<td><label name="dunsProcTs_4" ezfname="dunsProcTs_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
																		<td><input type="text" size="21" maxlength="30" value="1234567890123456789012345" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																		<td><input type="text" size="51" maxlength="4000" value="12345678901234567890123456789012345678901234567890" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																	</tr>
																	<tr class="pEvenNumberBGcolor" height="22">
																		<td><input type="text" size="24" maxlength="50" value="1234567890123456789012345678" name="dunsProcTpDescTxt_A1" ezfname="dunsProcTpDescTxt_A1" readonly></td>
																		<td><input type="text" size="24" maxlength="256" value="1234567890123456789012345" name="dunsFileNm_A1" ezfname="dunsFileNm_A1" readonly></td>
																		<td><label name="dunsFileLineNum" ezfname="dunsFileLineNum" readonly ezfhyo="A">123456789</label></td>
																		<td><label name="dunsProcTs_4" ezfname="dunsProcTs_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
																		<td><input type="text" size="21" maxlength="30" value="1234567890123456789012345" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																		<td><input type="text" size="51" maxlength="4000" value="12345678901234567890123456789012345678901234567890" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																	</tr>
																	<tr class="pEvenNumberBGcolor" height="22">
																		<td><input type="text" size="24" maxlength="50" value="1234567890123456789012345678" name="dunsProcTpDescTxt_A1" ezfname="dunsProcTpDescTxt_A1" readonly></td>
																		<td><input type="text" size="24" maxlength="256" value="1234567890123456789012345" name="dunsFileNm_A1" ezfname="dunsFileNm_A1" readonly></td>
																		<td><label name="dunsFileLineNum" ezfname="dunsFileLineNum" readonly ezfhyo="A">123456789</label></td>
																		<td><label name="dunsProcTs_4" ezfname="dunsProcTs_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
																		<td><input type="text" size="21" maxlength="30" value="1234567890123456789012345" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																		<td><input type="text" size="51" maxlength="4000" value="12345678901234567890123456789012345678901234567890" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																	</tr>
																	<tr class="pEvenNumberBGcolor" height="22">
																		<td><input type="text" size="24" maxlength="50" value="1234567890123456789012345678" name="dunsProcTpDescTxt_A1" ezfname="dunsProcTpDescTxt_A1" readonly></td>
																		<td><input type="text" size="24" maxlength="256" value="1234567890123456789012345" name="dunsFileNm_A1" ezfname="dunsFileNm_A1" readonly></td>
																		<td><label name="dunsFileLineNum" ezfname="dunsFileLineNum" readonly ezfhyo="A">123456789</label></td>
																		<td><label name="dunsProcTs_4" ezfname="dunsProcTs_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
																		<td><input type="text" size="21" maxlength="30" value="1234567890123456789012345" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																		<td><input type="text" size="51" maxlength="4000" value="12345678901234567890123456789012345678901234567890" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																	</tr>
																	<tr class="pEvenNumberBGcolor" height="22">
																		<td><input type="text" size="24" maxlength="50" value="1234567890123456789012345678" name="dunsProcTpDescTxt_A1" ezfname="dunsProcTpDescTxt_A1" readonly></td>
																		<td><input type="text" size="24" maxlength="256" value="1234567890123456789012345" name="dunsFileNm_A1" ezfname="dunsFileNm_A1" readonly></td>
																		<td><label name="dunsFileLineNum" ezfname="dunsFileLineNum" readonly ezfhyo="A">123456789</label></td>
																		<td><label name="dunsProcTs_4" ezfname="dunsProcTs_4" readonly ezfhyo="A">2016/03/30 17:04:45</label></td>
																		<td><input type="text" size="21" maxlength="30" value="1234567890123456789012345" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																		<td><input type="text" size="51" maxlength="4000" value="12345678901234567890123456789012345678901234567890" name="dunsProcById" ezfname="dunsProcById" readonly></td>
																	</tr>
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
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
