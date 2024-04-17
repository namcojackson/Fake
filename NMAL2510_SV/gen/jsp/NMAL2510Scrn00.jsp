<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161130142946 --%>
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
			<input type="hidden" name="pageID" value="NMAL2510Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Resource Maintenance">
            <jsp:include page="../tab/S21BusinessProcessTAB.jsp"></jsp:include>
            
            <ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" />
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" />
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" />
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" />
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" />
<center>
	<div class="pTab_BODY">
	<table width="100%">
		<col valign="top">
<%-- ######################################## HEADER ######################################## --%>
		<tr>
			<td>
				<table>
					<col valign="top" width="100">
					<col valign="top" width="500">
					<col valign="top" width="130">
					<col valign="top" width="250">
					<col valign="top">
					<tr>
						<td class="stab"><ezf:anchor name="psnNum_L1" ezfName="psnNum_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_ResourceLookup" otherAttr=" id=\"psnNum_L1\" ezfanchortext=\"\"">Resource #</ezf:anchor></td>
						<td><ezf:inputText name="psnNum_H1" ezfName="psnNum_H1" value="MO8209" otherEvent1="OnBlur_DeriveFromResource" otherAttr=" size=\"50\" maxlength=\"50\" ezffocusout=\"OnBlur_DeriveFromResource\""/>&nbsp<ezf:inputButton name="Search" value="Search" htmlClass="pBtn3" /></td>
						<td>&nbsp</td>
						<td>&nbsp</td>
						<td align="left"><ezf:inputButton name="OpenWin_Attachments" value="Attachments" htmlClass="pBtn6" /></td>
					</tr>
					<tr>
						<td class="stab">First Name</td>
						<td><ezf:inputText name="psnFirstNm_H1" ezfName="psnFirstNm_H1" value="RobertJ." otherAttr=" size=\"30\" maxlength=\"30\""/></td>
						<td class="stab">Type</td>
						<td colspan="2">
							<ezf:select name="psnTpCd_P1" ezfName="psnTpCd_P1" ezfCodeName="psnTpCd_H1" ezfDispName="psnTpNm_H1" />
						</td>
					</tr>
					<tr>
						<td class="stab">Last Name</td>
						<td><ezf:inputText name="psnLastNm_H1" ezfName="psnLastNm_H1" value="Berns" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
						<td class="stab">Employee ID</td>
						<td colspan="2"><ezf:inputText name="psnCd_H1" ezfName="psnCd_H1" value="MO8209" otherAttr=" size=\"25\" maxlength=\"30\" ezftoupper=\"\""/></td>
					</tr>
					<tr>
						<td class="stab">Company Name</td>
						<td><ezf:inputText name="hrPsnCmpyNm_H1" ezfName="hrPsnCmpyNm_H1" value="Canon Solutions America, inc." otherAttr=" size=\"60\" maxlength=\"30\""/></td>
						<td class="stab">Employment Date From</td>
						<td colspan="2">
							<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
							<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
						</td>
					<tr>
						<td class="stab">Job Code</td>
						<td><ezf:inputText name="jobTtlCd_H1" ezfName="jobTtlCd_H1" value="CB0210 EXECUTIVE, ACCOUNT SENIOR" otherAttr=" size=\"60\" maxlength=\"30\" ezftoupper=\"\""/></td>
						<td class="stab">Employment Date To</td>
						<td colspan="2">
							<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
							<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" >
						</td>
					</tr>
					<tr>
						<td class="stab">Job Name</td>
						<td><ezf:inputText name="hrTtlNm_H1" ezfName="hrTtlNm_H1" value="Holmes, Ricky Allan" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
						<td class="stab">Phone #</td>
						<td colspan="2"><ezf:inputText name="workTelNum_H1" ezfName="workTelNum_H1" otherAttr=" size=\"25\" maxlength=\"30\" ezftoupper=\"\""/></td>
					</tr>
					<tr>
						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ResourceLookup_BySupervisor" >Supervisor ID</ezf:anchor></td>
						<td><ezf:inputText name="hrSupvId_H1" ezfName="hrSupvId_H1" value="Holmes, Ricky Allan" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
						<td class="stab">Mobile #</td>
						<td colspan="2"><ezf:inputText name="cellTelNum_H1" ezfName="cellTelNum_H1" otherAttr=" size=\"25\" maxlength=\"30\" ezftoupper=\"\""/></td>
					</tr>
					
					<tr>
						<td class="stab">Supervisor Name</td>
						<td><ezf:inputText name="hrSupvNm_H1" ezfName="hrSupvNm_H1" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
						<td class="stab">EMail</td>
						<td colspan="2"><ezf:inputText name="emlAddr_H1" ezfName="emlAddr_H1" value="rburns@csa.canon.com" otherAttr=" size=\"30\" maxlength=\"320\" ezftoupper=\"\""/></td>
					</tr>
					<tr>
						<td class="stab">Line of Business</td>
						<td>
							<ezf:select name="lineBizTpCd_P1" ezfName="lineBizTpCd_P1" ezfBlank="1" ezfCodeName="lineBizTpCd_H1" ezfDispName="lineBizTpNm_H1" otherEvent1=" onchange=\"sendServer('OnChange_LineOfBusiness')\"" />
						</td>
						<td class="stab">Office Location Code</td>
						<td colspan="2"><ezf:inputText name="hrPsnIntfcLocCd_H1" ezfName="hrPsnIntfcLocCd_H1" value="MMT" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
					</tr>
					<tr>
						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Location" >Physical Location</ezf:anchor></td>
						<td colspan="4"><ezf:textArea name="xxAllLineAddr_H1" ezfName="xxAllLineAddr_H1" otherAttr=" rows=\"2\" cols=\"140\" style=\"background-color:#CCC;\" ezftoupper=\"\""/></td>
					</tr>
				</table>
				<%-- ##### BODY(TAB) ##### --%>
				<div class="pTab_HEAD">
					<ul>
						<li id="Hierarchy" title="Hierarchy" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Hierarchy" >Hierarchy</ezf:anchor></li>
						<li id="Territory" title="Territory" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="TAB_Territory" >Territory</ezf:anchor></li>
						<li id="Revenue" title="Revenue" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="TAB_Revenue" >Revenue Data</ezf:anchor></li>
						<li id="Misc" title="Misc" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_04" ezfEmulateBtn="1" ezfGuard="TAB_Misc" >Miscellaneous</ezf:anchor></li>
					</ul>
				</div>
				<%-- ##### TAB(Main) ##### --%>
				<c:choose>
					<c:when test="${pageScope._ezddatabean.xxDplyTab=='Hierarchy'}">
						<script type="text/javascript">
							document.getElementById( "Hierarchy" ).className="pTab_ON";
							document.getElementById( "Territory" ).className="pTab_OFF";
							document.getElementById( "Revenue" ).className="pTab_OFF";
							document.getElementById( "Misc" ).className="pTab_OFF";
						</script>
						<div class="pTab_BODY_In">
							<table style="table-layout:fixed;">
								<col valign="top">
								<tr>
									<td>
										<%-- ##### Checkbox ##### --%>
										<table style="table-layout:fixed;">
											<col width="980" valign="top">
											<col width="160" valign="top">
											<tr>
												<td class="stab">
													<label><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" />Show All Assignments</label>
												</td>
												<td colspan="4">
													<ezf:inputButton name="Insert_Row_Hierarchy" value="Add" htmlClass="pBtn5" />
													<ezf:inputButton name="Delete_Row_Hierarchy" value="Delete" htmlClass="pBtn5" />
												</td>
											</tr>
										</table>
										<%-- ##### Upper Table ##### --%>
										<table border="0" cellpadding="0" cellspacing="0">
											<col align="left" valign="top">
											<tr>
												<td>
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
														<col align="center" width="30">
														<col align="center" width="250">
														<col align="center" width="250">
														<col align="center" width="350">
														<col align="center" width="100">
														<col align="center" width="100">
														<tr height="18">
															<td class="pClothBs">&nbsp</td>
															<td class="pClothBs">Business Area</td>
															<td class="pClothBs">Role</td>
															<td class="pClothBs">Organization Name</td>
															<td class="pClothBs">Start Date</td>
															<td class="pClothBs">End Date</td>
														</tr>
													</table>
													<div style="overflow-x:hidden; width:1106; height:70px; overflow-y:scroll;">
														<table border="1" id="A" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
															<col width="30" align="center">
															<col width="250">
															<col width="250">
															<col width="350">
															<col width="100">
															<col width="100">
															<ezf:row ezfHyo="A">
															<tr>
																<td><ezf:inputRadio name="xxRadioBtn_A1" ezfName="xxRadioBtn_A1" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" size=\"3\""/></td>
																<td>
																	<ezf:select name="bizAreaOrgCd_P2" ezfName="bizAreaOrgCd_P2" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="bizAreaOrgCd_A1" ezfDispName="bizAreaOrgNm_A1" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServer('OnChange_BusinessAreaHierarchy', {EZF_ROW_NUMBER})\"" />
																</td>
																<td>
																	<ezf:select name="orgFuncRoleTpCd_P2" ezfName="orgFuncRoleTpCd_P2" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="orgFuncRoleTpCd_A2" ezfDispName="orgFuncRoleTpNm_A2" ezfCodeDispHyo="A" />
																</td>
																<td><ezf:inputText name="orgNm_A2" ezfName="orgNm_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"42\" maxlength=\"50\" ezftoupper=\"\""/>
																<ezf:inputButton name="OpenWin_OrganizationLookup" value="Org" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_OrganizationLookup#{EZF_ROW_NUMBER}\""/>
																</td>
																<td><ezf:inputText name="effFromDt_A2" ezfName="effFromDt_A2" value="02/26/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A2', 4, '{EZF_ROW_NUMBER}');" ></td>
																<td><ezf:inputText name="effThruDt_A2" ezfName="effThruDt_A2" value="02/26/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A2', 4, '{EZF_ROW_NUMBER}');" ></td>
																<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
															</tr>
															</ezf:row>
														</table>
													</div>
												</td>
											</tr>
										</table>
										<%-- ##### Lower Table ##### --%>
										<fieldset style="margin:5px 0px 0px 0px;">
											<legend style="font-size:12px;">Current Hierarchy View</legend>
											<table border="0" cellpadding="0" cellspacing="0">
												<col align="left" valign="top">
												<tr>
													<td>
														<div id="RightTop" style="overflow-x:hidden; width:1086;" onScroll="synchroScrollLeft( this.id, new Array( 'Right' ));">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																<col align="center" width="170">
																<col align="center" width="170">
																<col align="center" width="170">
																<col align="center" width="170">
																<col align="center" width="170">
																<col align="center" width="170">
																<col align="center" width="170">
																<col align="center" width="170">
																<col align="center" width="170">
																<col align="center" width="170">
																<tr height="18">
																	<td class="pClothBs">Level 1</td>
																	<td class="pClothBs">Level 2</td>
																	<td class="pClothBs">Level 3</td>
																	<td class="pClothBs">Level 4</td>
																	<td class="pClothBs">Level 5</td>
																	<td class="pClothBs">Level 6</td>
																	<td class="pClothBs">Level 7</td>
																	<td class="pClothBs">Level 8</td>
																	<td class="pClothBs">Level 9</td>
																	<td class="pClothBs">Level 10</td>
																</tr>
															</table>
														</div>
														<div id="Right" style="overflow-x:scroll; width:1106; height:94px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );">
															<table id="A_Right" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																<col width="170">
																<col width="170">
																<col width="170">
																<col width="170">
																<col width="170">
																<col width="170">
																<col width="170">
																<col width="170">
																<col width="170">
																<col width="170">
																<ezf:row ezfHyo="T">
																<tr>
																	<td><ezf:inputText name="orgNm_1" ezfName="orgNm_1" value="NORTHEAST ZONE" ezfHyo="T" ezfArrayNo="0" otherAttr=" size=\"23\""/>
																	<td><ezf:inputText name="orgNm_2" ezfName="orgNm_2" value="NORTHEAST ZONE" ezfHyo="T" ezfArrayNo="0" otherAttr=" size=\"23\""/>
																	<td><ezf:inputText name="orgNm_3" ezfName="orgNm_3" value="NORTHEAST ZONE" ezfHyo="T" ezfArrayNo="0" otherAttr=" size=\"23\""/>
																	<td><ezf:inputText name="orgNm_4" ezfName="orgNm_4" value="NORTHEAST ZONE" ezfHyo="T" ezfArrayNo="0" otherAttr=" size=\"23\""/>
																	<td><ezf:inputText name="orgNm_5" ezfName="orgNm_5" value="NORTHEAST ZONE" ezfHyo="T" ezfArrayNo="0" otherAttr=" size=\"23\""/>
																	<td><ezf:inputText name="orgNm_6" ezfName="orgNm_6" value="NORTHEAST ZONE" ezfHyo="T" ezfArrayNo="0" otherAttr=" size=\"23\""/>
																	<td><ezf:inputText name="orgNm_7" ezfName="orgNm_7" value="NORTHEAST ZONE" ezfHyo="T" ezfArrayNo="0" otherAttr=" size=\"23\""/>
																	<td><ezf:inputText name="orgNm_8" ezfName="orgNm_8" value="NORTHEAST ZONE" ezfHyo="T" ezfArrayNo="0" otherAttr=" size=\"23\""/>
																	<td><ezf:inputText name="orgNm_9" ezfName="orgNm_9" value="NORTHEAST ZONE" ezfHyo="T" ezfArrayNo="0" otherAttr=" size=\"23\""/>
																	<td><ezf:inputText name="orgNm_10" ezfName="orgNm_10" value="NORTHEAST ZONE" ezfHyo="T" ezfArrayNo="0" otherAttr=" size=\"23\""/>
																	<td class="pAuditInfo">
																		<ezf:inputHidden name="xxRecHistCratTs_T" ezfName="xxRecHistCratTs_T" ezfHyo="T" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistCratByNm_T" ezfName="xxRecHistCratByNm_T" ezfHyo="T" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistUpdTs_T" ezfName="xxRecHistUpdTs_T" ezfHyo="T" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistUpdByNm_T" ezfName="xxRecHistUpdByNm_T" ezfHyo="T" ezfArrayNo="0" />
																		<ezf:inputHidden name="xxRecHistTblNm_T" ezfName="xxRecHistTblNm_T" ezfHyo="T" ezfArrayNo="0" />
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
						</div>
					</c:when>
					<c:when test="${pageScope._ezddatabean.xxDplyTab=='Territory'}">
						<script type="text/javascript">
							document.getElementById( "Hierarchy" ).className="pTab_OFF";
							document.getElementById( "Territory" ).className="pTab_ON";
							document.getElementById( "Revenue" ).className="pTab_OFF";
							document.getElementById( "Misc" ).className="pTab_OFF";
						</script>
						<div class="pTab_BODY_In">
							<table style="table-layout:fixed;">
								<col valign="top">
								<tr>
									<td>
										<%-- ##### Checkbox ##### --%>
										<table style="table-layout:fixed;">
											<col width="980" valign="top">
											<col width="160" valign="top">
											<tr>
												<td class="stab">
													<label><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" />Show All Assignments</label>
												</td>
												<td colspan="4">
													<ezf:inputButton name="Insert_Row_Territory" value="Add" htmlClass="pBtn5" />
													<ezf:inputButton name="Delete_Row_Territory" value="Delete" htmlClass="pBtn5" />
												</td>
											</tr>
										</table>
										<%-- ##### Upper Table ##### --%>
										<table border="0" cellpadding="0" cellspacing="0">
											<col align="left" valign="top">
											<tr>
												<td>
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
														<col align="center" width="30">
														<col align="center" width="100">
														<col align="center" width="250">
														<col align="center" width="200">
														<col align="center" width="300">
														<col align="center" width="100">
														<col align="center" width="100">
														<tr height="18">
															<td class="pClothBs">&nbsp</td>
															<td class="pClothBs">Business Area</td>
															<td class="pClothBs">Territory Name</td>
															<td class="pClothBs">Role</td>
															<td class="pClothBs">Territory Description</td>
															<td class="pClothBs">Start Date</td>
															<td class="pClothBs">End Date</td>
														</tr>
													</table>
													<div style="overflow-x:hidden; width:1106; height:190px; overflow-y:scroll;">
														<table id="B" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
															<col width="30" align="center">
															<col width="100">
															<col width="250">
															<col width="200">
															<col width="300">
															<col width="100">
															<col width="100">
															<ezf:row ezfHyo="B">
															<tr>
																<td><ezf:inputCheckBox name="xxChkBox_B2" ezfName="xxChkBox_B2" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																<td>
																	<ezf:select name="bizAreaOrgCd_P3" ezfName="bizAreaOrgCd_P3" ezfHyo="B" ezfArrayNo="0" ezfCodeName="bizAreaOrgCd_B1" ezfDispName="bizAreaOrgNm_B1" />
																</td>
																
																<td><ezf:inputText name="orgNm_B2" ezfName="orgNm_B2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"50\" ezftoupper=\"\""/>
																<ezf:inputButton name="OpenWin_Territory" value="T" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_Territory#{EZF_ROW_NUMBER}\""/>
																</td>
																<td>
																	<ezf:select name="acctTeamRoleTpCd_P3" ezfName="acctTeamRoleTpCd_P3" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="acctTeamRoleTpCd_B1" ezfDispName="acctTeamRoleTpNm_B1" />
																</td>
																<td><ezf:inputText name="orgDescTxt_B2" ezfName="orgDescTxt_B2" value="PROVIDENCE MGR1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"41\""/>
																<td><ezf:inputText name="effFromDt_B2" ezfName="effFromDt_B2" value="02/26/2015" ezfHyo="B" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_B2', 4, '{EZF_ROW_NUMBER}');" ></td>
																<td><ezf:inputText name="effThruDt_B2" ezfName="effThruDt_B2" value="02/26/2015" ezfHyo="B" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_B2', 4, '{EZF_ROW_NUMBER}');" ></td>
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
									</td>
								</tr>
							</table>
						</div>
					</c:when>
					<c:when test="${pageScope._ezddatabean.xxDplyTab=='Revenue'}">
						<script type="text/javascript">
							document.getElementById( "Hierarchy" ).className="pTab_OFF";
							document.getElementById( "Territory" ).className="pTab_OFF";
							document.getElementById( "Revenue" ).className="pTab_ON";
							document.getElementById( "Misc" ).className="pTab_OFF";
						</script>
						<div class="pTab_BODY_In">
							<table style="table-layout:fixed;">
								<col valign="top">
								<tr>
									<td>
										<%-- ##### Checkbox ##### --%>
										<table style="table-layout:fixed;">
											<col width="110">
											<col width="145">
											<col width="725">
											<col width="160">
											<tr>
												<td class="stab"><ezf:anchor name="geoCd_L1" ezfName="geoCd_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_GeoCdLookup" otherAttr=" id=\"geoCd_L1\" ezfanchortext=\"\"">Sales Tax Geo Code</ezf:anchor></td>
												<td><ezf:inputText name="geoCd_C1" ezfName="geoCd_C1" value="400070120" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
												<td class="stab">
													<label>Inside City LImits<ezf:inputCheckBox name="dsInsdCtyLimitFlg_C1" ezfName="dsInsdCtyLimitFlg_C1" value="Y" /></label>
												</td>
												<td colspan="4">
													<ezf:inputButton name="Insert_Row_Revenue" value="Add" htmlClass="pBtn5" />
													<ezf:inputButton name="Delete_Row_Revenue" value="Delete" htmlClass="pBtn5" />
												</td>
											</tr>
										</table>
										<%-- ##### Upper Table ##### --%>
										<table border="0" cellpadding="0" cellspacing="0">
											<col align="left" valign="top">
											<tr>
												<td>
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
														<col align="center" width="30">
														<col align="center" width="200">
														<col align="center" width="115">
														<col align="center" width="115">
														<col align="center" width="115">
														<col align="center" width="115">
														<col align="center" width="40">
														<col align="center" width="100">
														<col align="center" width="100">
														<col align="center" width="50">
														<tr height="18">
															<td class="pClothBs" rowspan="2">&nbsp</td>
															<td class="pClothBs" rowspan="2">Revenue Account Type</td>
															<td class="pClothBs" colspan="5">(GL) String</td>
															<td class="pClothBs" rowspan="2">Start Date</td>
															<td class="pClothBs" rowspan="2">End Date</td>
															
															<td class="pClothBs" rowspan="2">Manual Update Flag</td>
														</tr>
														<tr height="18">
															<td class="pClothBs">Cmpy</td>
															<td class="pClothBs">BU</td>
															<td class="pClothBs">BR</td>
															<td class="pClothBs">CC</td>
															<td class="pClothBs">&nbsp</td>
														</tr>
													</table>
													<div style="overflow-x:hidden; width:1000; height:170px; overflow-y:scroll;">
														<table id="C" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
															<col width="30" align="center">
															<col width="200">
															<col width="115">
															<col width="115">
															<col width="115">
															<col width="115">
															<col width="40">
															<col width="100">
															<col width="100">
															<col width="50">
															<ezf:row ezfHyo="C">
															<tr>
																<td><ezf:inputCheckBox name="xxChkBox_C2" ezfName="xxChkBox_C2" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
																<td>
																	<ezf:select name="revAcctTpCd_P1" ezfName="revAcctTpCd_P1" ezfHyo="C" ezfArrayNo="0" ezfCodeName="revAcctTpCd_C1" ezfDispName="revAcctTpNm_C1" />
																</td>
																<td><ezf:inputText name="coaCmpyCd_C2" ezfName="coaCmpyCd_C2" value="800" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
																<td><ezf:inputText name="coaExtnCd_C2" ezfName="coaExtnCd_C2" value="200" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
																<td><ezf:inputText name="coaBrCd_C2" ezfName="coaBrCd_C2" value="254" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
																<td><ezf:inputText name="coaCcCd_C2" ezfName="coaCcCd_C2" value="21212" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
																<td><ezf:inputButton name="OpenWin_GLString" value="GL" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_GLString#{EZF_ROW_NUMBER}\""/></td>
																<td><ezf:inputText name="effFromDt_C2" ezfName="effFromDt_C2" value="02/26/2015" ezfHyo="C" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_C2', 4, '{EZF_ROW_NUMBER}');" ></td>
																<td><ezf:inputText name="effThruDt_C2" ezfName="effThruDt_C2" value="02/26/2015" ezfHyo="C" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_C2', 4, '{EZF_ROW_NUMBER}');" ></td>
																<td><ezf:inputCheckBox name="xxChkBox_C3" ezfName="xxChkBox_C3" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
																<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_C1" ezfName="xxRecHistCratTs_C1" ezfHyo="C" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistCratByNm_C1" ezfName="xxRecHistCratByNm_C1" ezfHyo="C" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdTs_C1" ezfName="xxRecHistUpdTs_C1" ezfHyo="C" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdByNm_C1" ezfName="xxRecHistUpdByNm_C1" ezfHyo="C" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistTblNm_C1" ezfName="xxRecHistTblNm_C1" ezfHyo="C" ezfArrayNo="0" />
																</td>
															</tr>
															</ezf:row>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</c:when>
					<c:when test="${pageScope._ezddatabean.xxDplyTab=='Misc'}">
						<script type="text/javascript">
							document.getElementById( "Hierarchy" ).className="pTab_OFF";
							document.getElementById( "Territory" ).className="pTab_OFF";
							document.getElementById( "Revenue" ).className="pTab_OFF";
							document.getElementById( "Misc" ).className="pTab_ON";
						</script>
						<div class="pTab_BODY_In">
							<fieldset>
								<legend style="font-size:12px;">Service</legend>
								<table>
									<col width="100">
									<col width="150">
									<tr>
										<td class="stab">Parts Request Limit</td>
										<td>
											<ezf:inputText name="moveOrdLimitAmt_D1" ezfName="moveOrdLimitAmt_D1" value="5" otherAttr=" size=\"30\" maxlength=\"22\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Time Zone</td>
										<td>
											<ezf:select name="tmZoneCd_P1" ezfName="tmZoneCd_P1" ezfBlank="1" ezfCodeName="tmZoneCd_D1" ezfDispName="tmZoneNm_D1" />
										</td>
									</tr>
									<tr>
										<td class="stab">Cost per Hour</td>
										<td>
											<ezf:inputText name="costPerHourAmt_D1" ezfName="costPerHourAmt_D1" value="5" otherAttr=" size=\"30\" maxlength=\"22\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Supplier Contact</td>
										<td>
											<ezf:inputText name="ctacPsnPk_D1" ezfName="ctacPsnPk_D1" value="5" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
										</td>
										<td>
											<ezf:inputText name="xxPsnNm_D1" ezfName="xxPsnNm_D1" value="5" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>&nbsp<ezf:inputButton name="OpenWin_Contract" value="C" htmlClass="pBtn1" /></td>
										</td>
										
									</tr>
									
								</table>
							</fieldset>
						</div>
					</c:when>
				</c:choose>
			</td>
		</tr>
	</table>
	</div>
</center>

<%-- **** Task specific area ends here **** --%>
