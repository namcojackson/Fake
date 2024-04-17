<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171205173642 --%>
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
			<input type="hidden" name="pageID" value="NMAL2520Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Org Structure Maintenance">
			<%-- include S21BusinessProcessTAB --%>
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"></jsp:include>
			
			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" />
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" />
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" />
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" />
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" />
			
<center>
<%-- ######################################## HEADER ######################################## --%>
	<div class="pTab_BODY" style="width:100%;">
	<table width="100%">
		<col valign="top">
		<tr>
			<td>
				<table style="table-layout:fixed;">
					<col valign="top" width="130">
					<col valign="top" width="100">
					<col valign="top" width="300">
					<col valign="top" width="370">
					<col valign="top" width="100">
					<tr>
						<td class="stab">Organization ID</td>
						<td class="pOut"><ezf:label name="orgCd_H1" ezfName="orgCd_H1" /></td>
						<td><ezf:inputButton name="OpenWin_OrganizationLookup" value="Org" htmlClass="pBtn1" /></td>
						<td>&nbsp;</td>
						<td align="left"><ezf:inputButton name="OpenWin_Attachments" value="Attachments" htmlClass="pBtn6" /></td>
					</tr>
				</table>
				<table style="table-layout:fixed;">
					<col valign="top" width="130">
					<col valign="top" width="150">
					<col valign="top" width="100">
					<col valign="top" width="130">
					<tr>
					 	<td class="stab">Business Area</td>
						<td>
							<ezf:select name="bizAreaOrgCd_P1" ezfName="bizAreaOrgCd_P1" ezfCodeName="bizAreaOrgCd_H1" ezfDispName="bizAreaOrgNm_H1" otherEvent1=" onchange=\"sendServer('OnChange_BusinessArea')\"" />
						</td>
					
						<td class="stab">Line of Business</td>
						<td>
							<ezf:select name="lineBizTpCd_P1" ezfName="lineBizTpCd_P1" ezfBlank="1" ezfCodeName="lineBizTpCd_H1" ezfDispName="lineBizTpNm_H1" />
						</td>
					</tr>
				</table>
				<table style="table-layout:fixed;">
					<col valign="top" width="130">
					<col valign="top" width="180">
					<col valign="top" width="300">
					<col valign="top" width="70">
					<col valign="top" width="200">

					<tr>
						<td class="stab">Organization Name</td>
						<td colspan="4">
							<ezf:inputText name="orgNm_H1" ezfName="orgNm_H1" otherAttr=" ezftoupper=\"\" size=\"60\" maxlength=\"50\""/>
						</td>
					</tr>
					<tr>
						<td class="stab">Organization Short Name</td>
						<td colspan="4">
							<ezf:inputText name="orgShortNm_H1" ezfName="orgShortNm_H1" otherAttr=" size=\"60\" maxlength=\"50\""/>
						</td>
					</tr>
					<tr>
						<td class="stab">Description</td>
						<td colspan="4">
							<ezf:inputText name="orgDescTxt_H1" ezfName="orgDescTxt_H1" otherAttr=" size=\"120\" maxlength=\"50\""/>
						</td>
					</tr>
					<tr>
						<td class="stab">Tier (LEVEL)</td>
						<td>
							<ezf:select name="orgTierCd_P1" ezfName="orgTierCd_P1" ezfCodeName="orgTierCd_H1" ezfDispName="orgTierNm_H1" otherEvent1=" onchange=\"sendServer('OnChange_Tier')\"" />
						</td>
						
						
						
						<td>&nbsp</td>
						
						<td class="stab">Start Date</td>
						<td>
							<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
							<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
						</td>
					</tr>
					<tr>
						<td class="stab">Tier Description</td>
						<td class="pOut"><ezf:label name="struDfnDescTxt_H1" ezfName="struDfnDescTxt_H1" /></td>
						<td>&nbsp</td>
						<td class="stab">End Date</td>
						<td>
							<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
							<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" >
						</td>
					</tr>
				</table>
				<table style="table-layout:fixed;">
					<col valign="top" width="130">
					<col valign="top" width="200">
					<col valign="top" width="130">
					<col valign="top" width="430">
					<col valign="top" width="100">
					<tr>
						<td class="stab">CSR Region</td>
						<td>
							<ezf:select name="csrRgTpCd_P1" ezfName="csrRgTpCd_P1" ezfBlank="1" ezfCodeName="csrRgTpCd_H1" ezfDispName="csrRgTpNm_H1" />
						</td>
						<td class="stab"><label><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" />Auto Estimate</label></td>
						<td class="stab">&nbsp</td>
						<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" /></td>
					</tr>
				</table>

				<%-- ##### BODY(TAB) ##### --%>
				<div class="pTab_HEAD">
					<ul>
						<li id="Build" title="Build" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Build" >Build Hierarchy</ezf:anchor></li>
						<li id="View" title="View" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="TAB_View" >View Hierarchy</ezf:anchor></li>
						<li id="Asign" title="Asign" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="TAB_Asign" >Resrc Asign</ezf:anchor></li>
					</ul>
				</div>

				<%-- ##### TAB(Main) ##### --%>
				<c:choose>
					<c:when test="${pageScope._ezddatabean.xxDplyTab=='Build'}">
						<script type="text/javascript">
							document.getElementById( "Build" ).className="pTab_ON";
							document.getElementById( "View" ).className="pTab_OFF";
							document.getElementById( "Asign" ).className="pTab_OFF";
						</script>
						<div class="pTab_BODY_In">
							<table style="table-layout:fixed;">
								<col valign="top">
								<tr>
									<td>
										<%-- ##### Upper Table ##### --%>
										<table style="table-layout:fixed;">
											<col width="650" valign="middle">
											<col width="160" valign="middle">
											<tr>
												<td class="stab">
													<label><ezf:inputCheckBox name="xxChkBox_F1" ezfName="xxChkBox_F1" value="Y" onClick="sendServer('OnChange_OrgActvFlg')" />Show All Assignments</label>
												</td>
												<td class="stab">
													<ezf:inputButton name="Insert_Row_Hierarchy" value="Add" htmlClass="pBtn5" />
													<ezf:inputButton name="Delete_Row_Hierarchy" value="Delete" htmlClass="pBtn5" />
												</td>
											</tr>
										</table>
										<table border="0" cellpadding="0" cellspacing="0">
											<col align="left" valign="top">
											<tr>
												<td>
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
														<col align="center" width="30">
														<col align="center" width="100">
														<col align="center" width="450">
														<col align="center" width="100">
														<col align="center" width="100">
														<tr height="18">
															<td class="pClothBs">&nbsp</td>
															<td class="pClothBs">Org ID</td>
															<td class="pClothBs">Parent Organization Name</td>
															<td class="pClothBs">Start Date</td>
															<td class="pClothBs">End Date</td>
														</tr>
													</table>
													<div style="overflow-x:hidden; width:798; height:120px; overflow-y:scroll;">
														<table id="A" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
															<col width="30" align="center">
															<col width="100">
															<col width="450">
															<col width="100">
															<col width="100">
															
															<ezf:row ezfHyo="A">
															<tr>
																<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></td>
																<td><ezf:anchor name="orgCd_L1" ezfName="orgCd_L1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenMultiModeScreen_Parent" ><ezf:label name="orgCd_A1" ezfName="orgCd_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
																
																<td>
																	<ezf:inputText name="orgNm_A1" ezfName="orgNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"57\" maxlength=\"50\" ezftoupper=\"\""/>
																	<ezf:inputButton name="OpenWin_OrganizationLookup_Detail" value="Org" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_OrganizationLookup#{EZF_ROW_NUMBER}\""/>
																</td>
																<td><ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" value="02/26/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
																<td><ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" value="02/26/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
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
										<table style="table-layout:fixed;">
											<col width="500" valign="middle">
											<tr>
												<td class="stab"></td>
											</tr>
										</table>
										<table border="0" cellpadding="0" cellspacing="0">
											<col align="left" valign="top">
											<tr>
												<td>
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
														<col align="center" width="30">
														<col align="center" width="100">
														<col align="center" width="450">
														<col align="center" width="100">
														<col align="center" width="100">
														<tr height="18">
															<td class="pClothBs">&nbsp</td>
															<td class="pClothBs">Org ID</td>
															<td class="pClothBs">Child Organization Name</td>
															<td class="pClothBs">Start Date</td>
															<td class="pClothBs">End Date</td>
														</tr>
													</table>
													<div style="overflow-x:hidden; width:798; height:120px; overflow-y:scroll;">
														<table id="B" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
															<col width="30" align="center">
															<col width="100">
															<col width="450">
															<col width="100">
															<col width="100">
															<ezf:row ezfHyo="B">
															<tr>
																<td>&nbsp</td>
																<td><ezf:anchor name="orgCd_L2" ezfName="orgCd_L2" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenMultiModeScreen_Child" ><ezf:label name="orgCd_B1" ezfName="orgCd_B1" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></td>
																<td class="pOut"><ezf:label name="orgNm_B1" ezfName="orgNm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="effFromDt_B1" ezfName="effFromDt_B1" value="02/26/2015" ezfHyo="B" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_B1', 4, '{EZF_ROW_NUMBER}');" ></td>
																<td><ezf:inputText name="effThruDt_B1" ezfName="effThruDt_B1" value="02/26/2015" ezfHyo="B" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_B1', 4, '{EZF_ROW_NUMBER}');" ></td>
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

					<c:when test="${pageScope._ezddatabean.xxDplyTab=='View'}">
						<script type="text/javascript">
							document.getElementById( "Build" ).className="pTab_OFF";
							document.getElementById( "View" ).className="pTab_ON";
							document.getElementById( "Asign" ).className="pTab_OFF";
						</script>
						<div class="pTab_BODY_In">
							<div style="overflow-x:hidden; overflow-y:scroll; width:1100; height:330;" >
								<%@ page import="business.servlet.NMAL2520.NMAL2520Bean" %>
								<% if( ((NMAL2520Bean)databean).getTreeView() != null ) { %>
								<uji:treeView
									bean		= "${__screenName__}"
									indentIcon	= "./img/treeView/child.gif;./img/treeView/lastchild.gif;./img/treeView/hasmorechild.gif;./img/treeView/nomorechild.gif;"
									insets		= "0"
									cellSpacing	= "0"
									background	= "#FFFFFF"
									stripeBackground	= "#D7E2E2"
									css		= "pTreeView"
									noWrap		= "true"
									borderWidth	= "1"
									ruleWidth	= "1"
									rules		= "all"
									dataEscape	= "false;"
									property			= "treeView"
									dataCellType		= "data;radio;"
									columnWidth		= ";30;"
									dataAlignmentHorizontal	= ";center;"
								/>
								<% } %>
							</div>
						</div>
					</c:when>

					<c:when test="${pageScope._ezddatabean.xxDplyTab=='Asign'}">
						<script type="text/javascript">
							document.getElementById( "Build" ).className="pTab_OFF";
							document.getElementById( "View" ).className="pTab_OFF";
							document.getElementById( "Asign" ).className="pTab_ON";
						</script>
						<div class="pTab_BODY_In">
							<table style="table-layout:fixed;">
								<col valign="top">
								<tr>
									<td>
										<%-- ##### Checkbox ##### --%>
										<table style="table-layout:fixed;">
											<col width="150" valign="middle">
											<col width="160" valign="middle">
											<col width="200">
											<col width="565">
											<tr>
												<td class="stab">
													<label><ezf:inputCheckBox name="xxChkBox_F2" ezfName="xxChkBox_F2" value="Y" onClick="sendServer('OnChange_ResrcActvFlg')" />Show All Assignments</label>
												</td>
												<td>
													<ezf:inputButton name="Insert_Row_Resrc" value="Add" htmlClass="pBtn5" />
													<ezf:inputButton name="Delete_Row_Resrc" value="Delete" htmlClass="pBtn5" />
												</td>
												<td></td>
												<td>
												  <TABLE cellSpacing="0" cellPadding="1" width="99%" border="0">
													  <TBODY>
													  <tr align="right">
														  <td>
																<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																	<jsp:param name="beanId"          value='<%= request.getParameter( "beanId" ) %>' />
																	<jsp:param name="TableNm"         value="C" />
																	<jsp:param name="ShowingFrom"     value="xxPageShowFromNum" />
																	<jsp:param name="ShowingTo"       value="xxPageShowToNum" />
																	<jsp:param name="ShowingOf"       value="xxPageShowOfNum" />
																	<jsp:param name="ShowingCurrent"  value="xxPageShowCurNum" />
																	<jsp:param name="ShowingTotal"    value="xxPageShowTotNum" />
																	<jsp:param name="ViewMode"        value="FULL" />
																</jsp:include>
														  </td>
													  </tr>
													  </TBODY>
												  </TABLE>
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
														<col align="center" width="140">
														<col align="center" width="180">
														<col align="center" width="140">
														<col align="center" width="140">
														<col align="center" width="120">
														<col align="center" width="120">
														<col align="center" width="100">
														<col align="center" width="100">
														<tr height="48">
															<td class="pClothBs">&nbsp</td>
															<td class="pClothBs">Resource#</td>
															<td class="pClothBs">Role</td>
															<td class="pClothBs">Last Name</td>
															<td class="pClothBs">First Name</td>
															<td class="pClothBs">Contract Assign Customer Name Starting From</td>
															<td class="pClothBs">Contract Assign Customer Name Starting To</td>
															<td class="pClothBs">Start Date</td>
															<td class="pClothBs">End Date</td>
														</tr>
													</table>
													<div style="overflow-x:hidden; width:1090; height:250px; overflow-y:scroll;">
														<table id="C" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
															<col width="30" align="center">
															<col width="140">
															<col width="180">
															<col width="140">
															<col width="140">
															<col width="120">
															<col width="120">
															<col width="100">
															<col width="100">
															<ezf:row ezfHyo="C">
															<tr>
																<td><ezf:inputCheckBox name="xxChkBox_C1" ezfName="xxChkBox_C1" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_C1#{EZF_ROW_NUMBER}\""/></td>
																<td class="stab">
																	<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
																		<col width="100">
																		<col width="40">
																		<tr>
																			<td><ezf:anchor name="psnNum_L1" ezfName="psnNum_L1" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Link_Resource" ><ezf:label name="psnNum_C1" ezfName="psnNum_C1" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor></td>
																			
																			<td><ezf:inputButton name="OpenWin_ResourceLookup" value="Rsc" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_ResourceLookup#{EZF_ROW_NUMBER}\""/></td>
																		</tr>
																	</table>
																</td>
																
																<td>
																	<ezf:select name="orgFuncRoleTpCd_P1" ezfName="orgFuncRoleTpCd_P1" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="orgFuncRoleTpCd_C1" ezfDispName="orgFuncRoleTpNm_C1" otherAttr=" style=\"width:178px\""/>
																</td>
																<td><ezf:label name="psnLastNm_C1" ezfName="psnLastNm_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																<td><ezf:label name="psnFirstNm_C1" ezfName="psnFirstNm_C1" ezfHyo="C" ezfArrayNo="0" /></td>
																<td><ezf:inputText name="asgCustFromNm_C1" ezfName="asgCustFromNm_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="asgCustToNm_C1" ezfName="asgCustToNm_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
																<td><ezf:inputText name="effFromDt_C1" ezfName="effFromDt_C1" value="02/26/2015" ezfHyo="C" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_C1', 4, '{EZF_ROW_NUMBER}');" ></td>
																<td><ezf:inputText name="effThruDt_C1" ezfName="effThruDt_C1" value="02/26/2015" ezfHyo="C" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_C1', 4, '{EZF_ROW_NUMBER}');" ></td>
																<td class="pAuditInfo">
																	<ezf:inputHidden name="xxRecHistCratTs_C1" ezfName="xxRecHistCratTs_C1" ezfHyo="C" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistCratByNm_C1" ezfName="xxRecHistCratByNm_C1" ezfHyo="C" ezfArrayNo="0" />
																	<ezf:inputHidden name="xxRecHistUpdTsC1" ezfName="xxRecHistUpdTs_C1" ezfHyo="C" ezfArrayNo="0" />
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
				</c:choose>
			</td>
		</tr>
	</table>
	</div>
</center>
<% if( ((NMAL2520Bean)databean).getTreeView() != null ) { %>
<%-- include S21TreeView.jsp --%>
<%@ page import="business.servlet.NMAL2520.NMAL2520Bean" %>
<jsp:include page="../treeView/S21TreeView.jsp">
<jsp:param name="treeView" value="<%= ((NMAL2520Bean)databean).getTreeView().isDisableAllFields() %>" />
<jsp:param name="TreeViewPropertyNameList" value="treeView" />
<jsp:param name="isFocusTreeView" value="<%= ((NMAL2520Bean)databean).getTreeView().isSetFocusTreeView() %>" />
<jsp:param name="setFocusTreeViewName" value="treeView" />
</jsp:include>
<% } %>


<%-- **** Task specific area ends here **** --%>
