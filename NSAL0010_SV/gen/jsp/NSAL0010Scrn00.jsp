<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20231011155432 --%>
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
			<input type="hidden" name="pageID" value="NSAL0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Machine Master Maintenance">

<%@ page import="business.servlet.NSAL0010.NSAL0010BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP" %>

<% NSAL0010BMsg  bMsg = (NSAL0010BMsg)databean.getEZDBMsg(); %>
<%@ include file="../common/common.jsp" %>

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<!-- ###TAB - HEAD -->
<!-- 
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Mach Mnt" class="pTab_ON"><a href="#">Mach Mnt</a></li>
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
-->
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<!-- ###TAB - BODY -->
				<div class="pTab_BODY">
				<table width="99%" border="0" cellpadding="1" cellspacing="0" align="center">
					<col valign="top" width="900px">
					<col valign="top" width="280px">
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
											<col width="50">	<!-- Serial# -->
											<col width="90">
											<col width="50">	<!-- IB ID -->
											<col width="90">
											<col width="90">	<!-- Search Button -->
											<col width="90">	<!-- Item Number -->
											<col width="125">
											<col width="35">
											<col width="220">
											<tr height="24">
												<td class="stab"><ezf:anchor name="serNum_AC" ezfName="serNum_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_SerialNum" otherAttr=" tabindex=\"-1\"">Serial#</ezf:anchor></td>
												<td><ezf:inputText name="serNum_H1" ezfName="serNum_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
												<td>IB ID</td>
												<td><ezf:inputText name="svcMachMstrPk_H1" ezfName="svcMachMstrPk_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWW" otherAttr=" size=\"10\" maxlength=\"28\" tabindex=\"-1\" ezftoupper=\"\""/></td>
												<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" /></td>
												<td class="stab"><ezf:anchor name="mdseCd_AC" ezfName="mdseCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_MdseCode" otherAttr=" tabindex=\"-1\"">Item Number</ezf:anchor></td>
												<td><ezf:inputText name="mdseCd_H1" ezfName="mdseCd_H1" value="WWWWWWWWW1WWWWW6" otherEvent1="OnBlur_MdseCd" otherAttr=" size=\"16\" maxlength=\"16\" ezffocusout=\"OnBlur_MdseCd\""/></td>
												<td><ezf:inputButton name="SearchMdseName" value=">>" htmlClass="pBtn0" /></td>
												<td class="pOut"><ezf:label name="mdseDescShortTxt_H1" ezfName="mdseDescShortTxt_H1" /></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
											<col width="100">	<!-- Marketing Model -->
											<col width="170">
											<col width="10">
											<col width="90">	<!-- Service Model -->
											<col width="205">
											<col width="10">
											<col width="115">	<!-- Quantity -->
											<col width="145">
											<col width="">
											<tr height="24">
												<td class="stab">Marketing Model</td>
												<td><ezf:inputText name="mktMdlNm_H1" ezfName="mktMdlNm_H1" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"50\" size=\"22\" ezftoupper=\"\""/></td>
												<td><br /></td>
												<td class="stab">Service Model</td>
												<td><ezf:inputText name="t_MdlNm_H1" ezfName="t_MdlNm_H1" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"50\" size=\"22\" ezftoupper=\"\""/></td>
												<td><br /></td>
												<td class="stab">Quantity</td>
												<td><ezf:inputText name="svcMachQty_H1" ezfName="svcMachQty_H1" value="1234567890" otherAttr=" maxlength=\"10\" size=\"10\""/></td>
												<td><br /></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
											<col width="100">	<!-- Installation Date -->
											<col width="80">
											<col width="20">
											<col width="20">
											<col width="60">	<!-- Start Date -->
											<col width="80">
											<col width="20">
											<col width="20">
											<col width="60">	<!-- End Date -->
											<col width="80">
											<col width="20">
											<col width="25">
											<col width="115">	<!-- Transaction Type -->
											<col width="145">
											<tr height="24">
												<td class="stab">Install Date</td>
												<td><ezf:inputText name="istlDt_H1" ezfName="istlDt_H1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('istlDt_H1', 4);"></td>
												<td><br /></td>
												<td class="stab">Start Date</td>
												<td><ezf:inputText name="startDt_H1" ezfName="startDt_H1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('startDt_H1', 4);"></td>
												<td><br /></td>
												<td class="stab">End Date</td>
												<td><ezf:inputText name="svcMachRmvDt_H1" ezfName="svcMachRmvDt_H1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('svcMachRmvDt_H1', 4);"></td>
												<td><br /></td>
												<td class="stab">Transaction Type</td>
												<td>
													<ezf:select name="svcMachTrxTpCd_H3" ezfName="svcMachTrxTpCd_H3" ezfBlank="1" ezfCodeName="svcMachTrxTpCd_H1" ezfDispName="svcMachTrxTpDescTxt_H2" otherAttr=" style=\"width:140px\""/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
											<col width="100">	<!-- IB Status -->
											<col width="170">
											<col width="10">
											<col width="90">	<!-- IB Usage -->
											<col width="205">
											<col width="10">
											<col width="115">	<!-- Stock Status -->
											<col width="145">
											<col width="">
											<tr height="24">
												<td class="stab">IB Status</td>
												<td>
													<ezf:select name="svcMachMstrStsCd_H3" ezfName="svcMachMstrStsCd_H3" ezfBlank="1" ezfCodeName="svcMachMstrStsCd_H1" ezfDispName="svcMachMstrStsDescTxt_H2" otherAttr=" style=\"width:160px\""/>
												</td>
												<td><br /></td>
												<td class="stab">IB Usage</td>
												<td>
													<ezf:select name="svcMachUsgStsCd_H3" ezfName="svcMachUsgStsCd_H3" ezfBlank="1" ezfCodeName="svcMachUsgStsCd_H1" ezfDispName="svcMachUsgStsDescTxt_H2" otherAttr=" style=\"width:160px\""/>
												</td>
												<td><br /></td>
												<td class="stab"><ezf:label name="stkStsNm_TL" ezfName="stkStsNm_TL" /></td>
												<td>
													 <ezf:select name="stkStsCd_H3" ezfName="stkStsCd_H3" ezfBlank="1" ezfCodeName="stkStsCd_H1" ezfDispName="stkStsDescTxt_H2" otherAttr=" style=\"width:140px;\""/>
												</td>
												<td><br /></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
											<col width="100">	<!-- Parent Serial# -->
											<col width="170">
											<col width="10">
											<col width="90">	<!-- External Ref# -->
											<col width="205">
											<col width="10">
											<col width="115">	<!-- Location Status -->
											<col width="145">
											<col width="">
											<tr height="24">
												<td class="stab"><ezf:anchor name="prntSerNum_AC" ezfName="prntSerNum_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_ParentSerialNum" otherAttr=" tabindex=\"-1\"">Parent Serial#</ezf:anchor></td>
												<td><ezf:inputText name="prntSerNum_H1" ezfName="prntSerNum_H1" value="---------1---------2---------3" otherAttr=" size=\"22\" maxlength=\"30\" ezftoupper=\"\""/></td> 
												<td><br /></td>
												<td class="stab">External Ref#</td>
												<td><ezf:inputText name="custMachCtrlNum_H1" ezfName="custMachCtrlNum_H1" value="---------1---------2---------3" otherAttr=" maxlength=\"30\" size=\"22\" ezftoupper=\"\""/></td>
												<td><br /></td>
												<td class="stab"><ezf:label name="locStsNm_TL" ezfName="locStsNm_TL" /></td>
												<td>
													<ezf:select name="svcMachMstrLocStsCd_H3" ezfName="svcMachMstrLocStsCd_H3" ezfBlank="1" ezfCodeName="locStsCd_H1" ezfDispName="locStsDescTxt_H2" otherAttr=" style=\"width:140px\""/> 
												</td> 
												<td><br /></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
											<col width="100">	<!-- Serial# Change -->
											<col width="40">
											<col width="30">
											<col width="90">	<!-- New Serial# -->
											<col width="205">
											<col width="10">
											<col width="80">	<!-- Software ID -->
											<col width="300">
											<col width="">
											<tr height="24">
												<td class="stab">Serial# Change</td>
												<td><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" onClick="sendServer('ChangeSerNum')" /></td>
												<td><br /></td>
												<td class="stab">New Serial#</td>
												<td><ezf:inputText name="serNum_H2" ezfName="serNum_H2" value="---------1---------2---------3" otherAttr=" maxlength=\"30\" size=\"22\" ezftoupper=\"\""/></td>
												<td><br /></td>
												<td class="stab">Software ID</td>
												<td> <ezf:inputText name="swLicId_H1" ezfName="swLicId_H1" value="---------1---------2---------3---------4" otherAttr=" maxlength=\"40\" size=\"40\""/></td>
												<td><br /></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<table border="0" cellpadding="1" cellspacing="0" align="center">
								<col valign="top">
								<tr>
									<td>
										<fieldset>
											<legend>Machine Master Account Information</legend>
											<table border="1" cellpadding="1" cellspacing="0" width="700" style="table-layout: fixed;">
												<col width="150" align="center">
												<col width="100" align="center">
												<col width="150" align="center">
												<col width="200" align="center">
												<col width="100" align="center">
												<tr>
													<td class="pClothBs">Relationship</td>
													<td class="pClothBs">Account#</td>
													<td class="pClothBs">Account Name</td>
													<td class="pClothBs">Address</td>
													<td class="pClothBs">Loc#</td>
												</tr>
											</table>
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col width="150" align="center">
												<col width="20" align="center">
												<col width="80" align="center">
												<col width="150" align="center">
												<col width="200" align="center">
												<col width="100" align="center">
												<tr>
													<td>
														<ezf:inputText name="xxLocRoleTpCdListTxt_M1" ezfName="xxLocRoleTpCdListTxt_M1" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" maxlength=\"30\""/>
													</td>
													<td>
														<ezf:inputButton name="OpenWin_OwnerAcct" value="A" otherAttr=" htmlclass=\"pBtn1\""/>
													</td>
													<td>
														<ezf:inputText name="ownrAcctNum_M1" ezfName="ownrAcctNum_M1" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"9\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="dsAcctNm_M1" ezfName="dsAcctNm_M1" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"18\" maxlength=\"360\""/>
													</td>
													<td>
														&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
												<tr>
													<td>
														<ezf:inputText name="xxLocRoleTpCdListTxt_M2" ezfName="xxLocRoleTpCdListTxt_M2" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" maxlength=\"30\""/>
													</td>
													<td>
														<ezf:inputButton name="OpenWin_BillToCust" value="A" otherAttr=" htmlclass=\"pBtn1\""/>
													</td>
													<td>
														<ezf:inputText name="billToAcctNum_M2" ezfName="billToAcctNum_M2" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"9\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="dsAcctNm_M2" ezfName="dsAcctNm_M2" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"18\" maxlength=\"360\""/>
													</td>
													<td>
														<ezf:inputText name="xxComnScrColValTxt_M2" ezfName="xxComnScrColValTxt_M2" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"24\" maxlength=\"4000\""/>
													</td>
													<td>
														<ezf:inputText name="indBillToLocNum_M2" ezfName="indBillToLocNum_M2" value="12345678901234567890" otherAttr=" size=\"11\" maxlength=\"20\" ezftoupper=\"\""/>
													</td>
												</tr>
	    										<tr>
													<td>
														<ezf:inputText name="xxLocRoleTpCdListTxt_M3" ezfName="xxLocRoleTpCdListTxt_M3" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" maxlength=\"30\""/>
													</td>
													<td>
														<ezf:inputButton name="OpenWin_CurLoc" value="A" otherAttr=" htmlclass=\"pBtn1\""/>
													</td>
													<td>
														<ezf:inputText name="curLocAcctNum_M3" ezfName="curLocAcctNum_M3" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"9\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="dsAcctNm_M3" ezfName="dsAcctNm_M3" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"18\" maxlength=\"360\""/>
													</td>
													<td>
														<ezf:inputText name="xxComnScrColValTxt_M3" ezfName="xxComnScrColValTxt_M3" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"24\" maxlength=\"4000\""/>
													</td>
													<td>
														<ezf:inputText name="indCurLocNum_M3" ezfName="indCurLocNum_M3" value="12345678901234567890" otherAttr=" size=\"11\" maxlength=\"20\" ezftoupper=\"\""/>
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<fieldset>
								<legend>Image Ware Remote Meter Details</legend>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col width="10">
												<col width="145">	
												<col width="">
												<tr height="24">
													<td><br /><!-- Enabled --></td>
													<td class="stab">Enabled</td>
													<td><ezf:inputText name="xxYesNoNm_H1" ezfName="xxYesNoNm_H1" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"35\" size=\"10\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="24">
													<td><br /><!-- Status --></td>
													<td class="stab">Status</td>
													<td><ezf:inputText name="iwrCondDescTxt_H1" ezfName="iwrCondDescTxt_H1" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"35\" size=\"10\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="24">
													<td><br /><!-- Last Communication Date --></td>
													<td class="stab">Last Communication Date</td>
													<td><ezf:inputText name="lastUpdDt_H1" ezfName="lastUpdDt_H1" value="MM/DD/YYYY" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="24">
													<td><br /><!-- Registration Date --></td>
													<td class="stab">Registration Date</td>
													<td><ezf:inputText name="iwrRgtnDt_H1" ezfName="iwrRgtnDt_H1" value="MM/DD/YYYY" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/></td>
												</tr>
												<tr height="24">
													<td><br /><!-- Deregistration Date --></td>
													<td class="stab">Deregistration Date</td>
													<td><ezf:inputText name="iwrDeinsDt_H1" ezfName="iwrDeinsDt_H1" value="MM/DD/YYYY" otherAttr=" maxlength=\"10\" size=\"10\" ezftoupper=\"\""/></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</fieldset>
							<table align="center">
								<tr><td><ezf:inputButton name="OpenWin_ConfigHist" value="View Configuration History" otherAttr=" htmlclass=\"pBtn7\" style=\"width:240px\""/></td></tr>
								<tr><td><ezf:inputButton name="MoveWin_MtrReadHist" value="View Meter Read History/Upload" otherAttr=" htmlclass=\"pBtn7\" style=\"width:240px\""/></td></tr>
								<tr><td><ezf:inputButton name="MoveWin_UpldContact" value="Upload Contacts" otherAttr=" htmlclass=\"pBtn7\" style=\"width:240px\""/></td></tr>
								<tr><td><ezf:inputButton name="OpenWin_Attach" value="Attachment" otherAttr=" htmlclass=\"pBtn7\" style=\"width:240px\""/></td></tr>
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
											<col width="100">	<!-- Create Warranty -->
											<col width="30">
											<tr height="24">
												<td class="stab">Create Warranty</td>
												<td><ezf:inputCheckBox name="wtyAutoCratFlg_H1" ezfName="wtyAutoCratFlg_H1" value="Y" /></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>	
				<hr>
<%-- ######################################## DETAIL ######################################## --%>
				<table width="100%">
					<col valign="top">
					<tr>
						<td>
							<div class="pTab_HEAD">
								<ul>
									<li class="pTab_OFF" id="MachConfig"    title="Mach Config"><ezf:anchor name="" ezfName="xxTabProt_11" ezfEmulateBtn="1" ezfGuard="TAB_MachConfig" >Mach Config</ezf:anchor></li>
									<li class="pTab_OFF" id="Solution" title="Solution"><ezf:anchor name="" ezfName="xxTabProt_21" ezfEmulateBtn="1" ezfGuard="TAB_Solution" >Solution</ezf:anchor></li>
									<li class="pTab_OFF" id="Contacts" title="Contacts"><ezf:anchor name="" ezfName="xxTabProt_31" ezfEmulateBtn="1" ezfGuard="TAB_Contacts" >Contacts</ezf:anchor></li>
									<li class="pTab_OFF" id="AddlAttrb" title="Addl Attrb"><ezf:anchor name="" ezfName="xxTabProt_41" ezfEmulateBtn="1" ezfGuard="TAB_AddlAttrb" >Addl Attrb</ezf:anchor></li>
									<!-- START 2023/07/10 Y.Nagasawa [QC#61524, DEL] -->
									<!-- <li class="pTab_OFF" id="CurrentLoc" title="Current Loc"><a href="#" ezfname="xxTabProt_51" onclick="sendServer('TAB_CurrentLoc')">Current Loc</a></li> -->
									<!-- END 2023/07/10 Y.Nagasawa [QC#61524, DEL] -->
									<li class="pTab_OFF" id="SlsOrdHist" title="Sls Ord Hist"><ezf:anchor name="" ezfName="xxTabProt_61" ezfEmulateBtn="1" ezfGuard="TAB_SlsOrdHist" >Sls Ord Hist</ezf:anchor></li>
									<li class="pTab_OFF" id="IBHistory" title="IB History"><ezf:anchor name="" ezfName="xxTabProt_71" ezfEmulateBtn="1" ezfGuard="TAB_IBHistory" >IB History</ezf:anchor></li>
									<li class="pTab_OFF" id="ContrSmry" title="Contr Smry"><ezf:anchor name="" ezfName="xxTabProt_81" ezfEmulateBtn="1" ezfGuard="TAB_ContrSmry" >Contr Smry</ezf:anchor></li>
									<li class="pTab_OFF" id="SvcCallHist" title="SvcCall Hist"><ezf:anchor name="" ezfName="xxTabProt_91" ezfEmulateBtn="1" ezfGuard="TAB_SvcCallHist" >SvcCall Hist</ezf:anchor></li>
									<li class="pTab_OFF" id="InvoiceHist" title="Invoice Hist"><ezf:anchor name="" ezfName="xxTabProt_A1" ezfEmulateBtn="1" ezfGuard="TAB_InvoiceHist" >Invoice Hist</ezf:anchor></li>
								</ul>
							</div>
<%-- ##################################### Mach Config ######################################## --%>

							<c:choose>

							<c:when test="${pageScope._ezddatabean.xxDplyTab_01 == 'MachConfig'}">
							<script type="text/javascript">
								document.getElementById( "MachConfig" ).className = "pTab_ON";
								document.getElementById( "Solution" ).className = "pTab_OFF";
								document.getElementById( "Contacts" ).className = "pTab_OFF";
								document.getElementById( "AddlAttrb" ).className = "pTab_OFF";
								// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								// document.getElementById( "CurrentLoc" ).className = "pTab_OFF";
								// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								document.getElementById( "SlsOrdHist" ).className = "pTab_OFF";
								document.getElementById( "IBHistory" ).className = "pTab_OFF";
								document.getElementById( "ContrSmry" ).className = "pTab_OFF";
								document.getElementById( "SvcCallHist" ).className = "pTab_OFF";
								document.getElementById( "InvoiceHist" ).className = "pTab_OFF";
							</script>
							<div class="pTab_BODY_In">
								<!-- Prev/Next Page-->
								<table width="99%">
									<tr align="right">
										<td>
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
								<table border="0" cellpadding="1" cellspacing="0" align="center">
									<col valign="top">
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" width="1079" style="table-layout: fixed;">
												<col width="50" align="center">
												<col width="70" align="center">
												<col width="80" align="center">
												<col width="164" align="center">
												<col width="100" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="85" align="center">
												<col width="90" align="center">
												<col width="80" align="center">
												<col width="100" align="center">
												<col width="100" align="center">
												<tr>
													<td class="pClothBs"><br /></td>
													<td class="pClothBs">Config ID</td>
													<td class="pClothBs">Item Number</td>
													<td class="pClothBs">Item Description</td>
													<td class="pClothBs">Marketing Model</td>
													<td class="pClothBs">Serial#</td>
													<td class="pClothBs">Install Date</td>
													<td class="pClothBs">IB Status</td>
													<td class="pClothBs">IB ID</td>
													<td class="pClothBs">Parent ID</td>
													<td class="pClothBs">Sche Install Date</td>
													<td class="pClothBs">Remove Date</td>
												</tr>
											</table>

											<div style="overflow-y:scroll; height:180; width:1096;">
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col width="25" align="center">
												<col width="25" align="center">
												<col width="70" align="center">
												<col width="80" align="center">
												<col width="164" align="left">
												<col width="100" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="85" align="center">
												<col width="90" align="center">
												<col width="80" align="center">
												<col width="100" align="center">
												<col width="80" align="center">
												<col width="20" align="center">
												<% int a = 0; %>
												<ezf:row ezfHyo="A">
												<% String svcMachTpCd = bMsg.A.no(a).svcMachTpCd_A.getValue(); %>
												<tr>
													<td>
														<ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" />
													</td>
													<td>
														<ezf:inputButton name="OpenWin_MachIdA" value="M" ezfHyo="A" ezfArrayNo="0" otherAttr=" htmlclass=\"pBtn1\""/>
													</td>
													<td>
														<ezf:inputText name="svcConfigMstrPk_A" ezfName="svcConfigMstrPk_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"20\" ezftoupper=\"\""/>
													</td>
													<td>
														<% if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd)) { %>
															&nbsp;
														<% } %>
														<ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="mktMdlNm_A" ezfName="mktMdlNm_A" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="serNum_A" ezfName="serNum_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="istlDt_A" ezfName="istlDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="svcMachMstrStsDescTxt_A" ezfName="svcMachMstrStsDescTxt_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td align="center">
														<ezf:anchor name="svcMachMstrPk_A1" ezfName="svcMachMstrPk_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="RefreshMachineInfo" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
															<ezf:label name="svcMachMstrPk_A1" ezfName="svcMachMstrPk_A1" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td>
														<ezf:inputText name="svcMachMstrPk_A2" ezfName="svcMachMstrPk_A2" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="rddDt_A" ezfName="rddDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="svcMachRmvDt_A" ezfName="svcMachRmvDt_A" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													</td>
													<td>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'svcMachRmvDt_A', 4 ,{EZF_ROW_NUMBER});">
													</td>
												</tr>
												<% a = a + 1; %>
												</ezf:row>
											</table>
											</div>
											<table>
												<tr>
													<td>
														<ezf:inputButton name="InsertParentMachineLine" value="Insert Parent" otherAttr=" htmlclass=\"pBtn7\""/>
														<ezf:inputButton name="DeleteMachineLine" value="Delete Row" otherAttr=" htmlclass=\"pBtn7\""/>
														<ezf:inputButton name="InsertChildMachineLine" value="Insert Child" otherAttr=" htmlclass=\"pBtn7\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							</c:when>
<%-- ##################################### Solution ######################################## --%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_01 == 'Solution'}">
							<script type="text/javascript">
								document.getElementById( "MachConfig" ).className = "pTab_OFF";
								document.getElementById( "Solution" ).className = "pTab_ON";
								document.getElementById( "Contacts" ).className = "pTab_OFF";
								document.getElementById( "AddlAttrb" ).className = "pTab_OFF";
								// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								// document.getElementById( "CurrentLoc" ).className = "pTab_OFF";
								// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								document.getElementById( "SlsOrdHist" ).className = "pTab_OFF";
								document.getElementById( "IBHistory" ).className = "pTab_OFF";
								document.getElementById( "ContrSmry" ).className = "pTab_OFF";
								document.getElementById( "SvcCallHist" ).className = "pTab_OFF";
								document.getElementById( "InvoiceHist" ).className = "pTab_OFF";
							</script>
							<div class="pTab_BODY_In">
								<!-- Prev/Next Page-->
								<table width="99%">
									<tr align="right">
										<td>
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
								<table border="0" cellpadding="1" cellspacing="0" align="center">
									<col valign="top">
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" width="1065" style="table-layout: fixed;">
												<col width="25" align="center">
												<col width="70" align="center">
												<col width="70" align="center">
												<col width="80" align="center">
												<col width="150" align="center">
												<col width="100" align="center">
												<col width="110" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="100" align="center">
												<col width="100" align="center">
												<col width="100" align="center">
												<tr>
													<td class="pClothBs"><br /></td>
													<td class="pClothBs">Solution#</td>
													<td class="pClothBs">Config ID</td>
													<td class="pClothBs">Item Number</td>
													<td class="pClothBs">Item Description</td>
													<td class="pClothBs">Service Model</td>
													<td class="pClothBs">IB ID</td>
													<td class="pClothBs">Parent ID</td>
													<td class="pClothBs">Install Date</td>
													<td class="pClothBs">Owner</td>
													<td class="pClothBs">Bill To</td>
													<td class="pClothBs">Ship To</td>
												</tr>
											</table>

											<div style="overflow-y:scroll; height:180; width:1082;">
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col width="25" align="center">
												<col width="70" align="center">
												<col width="70" align="center">
												<col width="80" align="center">
												<col width="150" align="center">
												<col width="100" align="center">
												<col width="110" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="100" align="center">
												<col width="100" align="center">
												<col width="100" align="center">
												<ezf:row ezfHyo="B">
												<tr>
													<td>
														<ezf:inputRadio name="xxRadioBtn_B" ezfName="xxRadioBtn_B" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="B" ezfGetLineNoOffset="0" />
													</td>
													<td>
														<ezf:inputText name="svcSlnSq_B" ezfName="svcSlnSq_B" value="12345678901234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="svcConfigMstrPk_B" ezfName="svcConfigMstrPk_B" value="12345678901234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="mdseCd_B" ezfName="mdseCd_B" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="mdseDescShortTxt_B" ezfName="mdseDescShortTxt_B" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="t_MdlNm_B" ezfName="t_MdlNm_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"1000\" ezftoupper=\"\""/>
													</td>
													<td align="center"><ezf:anchor name="svcMachMstrPk_B1" ezfName="svcMachMstrPk_B1" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="RefreshMachineInfo" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
													<ezf:label name="svcMachMstrPk_B1" ezfName="svcMachMstrPk_B1" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></td>
													<td>
														<ezf:inputText name="svcMachMstrPk_B2" ezfName="svcMachMstrPk_B2" value="12345678901234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="istlDt_B" ezfName="istlDt_B" value="MM/DD/YYYY" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="dsAcctNm_B1" ezfName="dsAcctNm_B1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"360\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="dsAcctNm_B2" ezfName="dsAcctNm_B2" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"360\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="dsAcctNm_B3" ezfName="dsAcctNm_B3" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"360\" ezftoupper=\"\""/>
													</td>
												</tr>
												</ezf:row>
											</table>
											</div>
											<table>
												<tr>
													<td>
														<ezf:inputButton name="OpenWin_LinkNewConfig" value="Link New Config" otherAttr=" htmlclass=\"pBtn7\""/>
														<ezf:inputButton name="RemoveConfig" value="Remove Config" otherAttr=" htmlclass=\"pBtn7\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							</c:when>
<%-- ##################################### Contacts ######################################## --%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_01 == 'Contacts'}">
							<script type="text/javascript">
								document.getElementById( "MachConfig" ).className = "pTab_OFF";
								document.getElementById( "Solution" ).className = "pTab_OFF";
								document.getElementById( "Contacts" ).className = "pTab_ON";
								document.getElementById( "AddlAttrb" ).className = "pTab_OFF";
								// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								// document.getElementById( "CurrentLoc" ).className = "pTab_OFF";
								// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								document.getElementById( "SlsOrdHist" ).className = "pTab_OFF";
								document.getElementById( "IBHistory" ).className = "pTab_OFF";
								document.getElementById( "ContrSmry" ).className = "pTab_OFF";
								document.getElementById( "SvcCallHist" ).className = "pTab_OFF";
								document.getElementById( "InvoiceHist" ).className = "pTab_OFF";
							</script>
							<div class="pTab_BODY_In">
								<table>
									<col width="15">
									<col width="75">
									<col width="50">
									<col width="70">
									<col width="70">
									<col width="810" align="right">
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Show Inactive</td>
										<td><ezf:inputCheckBox name="xxChkBox_CH" ezfName="xxChkBox_CH" value="Y" onClick="sendServer('InactiveContacts')" /></td>
										<td><ezf:inputButton name="InsertContactLine" value="Add" htmlClass="pBtn5" /></td>
										<td><ezf:inputButton name="DeleteContactLine" value="Del" htmlClass="pBtn5" /></td>
										<!-- Prev/Next Page-->
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="C" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_C" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_C" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_C" />
											</jsp:include>
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="1" cellspacing="0" align="center">
									<col valign="top">
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" width="1065" style="table-layout: fixed;">
												<col width="25" align="center">
												<col width="130" align="center">
												<col width="100" align="center">
												<col width="120" align="center">
												<col width="150" align="center">
												<col width="50" align="center">
												<col width="120" align="center">
												<col width="120" align="center">
												<col width="50" align="center">
												<col width="100" align="center">
												<col width="100" align="center">
												<tr>
													<td class="pClothBs"><br /></td>
													<td class="pClothBs">Last Name</td>
													<td class="pClothBs">First Name</td>
													<td class="pClothBs">Contact Point</td>
													<td class="pClothBs">Contact Value</td>
													<td class="pClothBs">Ext</td>
													<td class="pClothBs">IB Contact Type</td>
													<td class="pClothBs">Contact ID</td>
													<td class="pClothBs">Primary</td>
													<td class="pClothBs">Start Date</td>
													<td class="pClothBs">End Date</td>
												</tr>
											</table>

											<div style="overflow-y:scroll; height:180; width:1082;">
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col width="25" align="center">
												<col width="20" align="center">
												<col width="110" align="center">
												<col width="100" align="center">
												<col width="120" align="center">
												<col width="150" align="center">
												<col width="50" align="center">
												<col width="120" align="center">
												<col width="120" align="center">
												<col width="50" align="center">
												<col width="80" align="center">
												<col width="20" align="center">
												<col width="80" align="center">
												<col width="20" align="center">
												<ezf:row ezfHyo="C">
												<tr>
													<td>
														<ezf:inputRadio name="xxRadioBtn_C" ezfName="xxRadioBtn_C" value="{EZF_ROW_NUMBER}" />
													</td>
													<td>
														<ezf:inputButton name="OpenWin_Contact" value="C" ezfHyo="C" ezfArrayNo="0" otherAttr=" htmlclass=\"pBtn1\""/>
													</td>
													<td>
														<ezf:inputText name="ctacPsnLastNm_C" ezfName="ctacPsnLastNm_C" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"30\""/>
													</td>
													<td>
														<ezf:inputText name="ctacPsnFirstNm_C" ezfName="ctacPsnFirstNm_C" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/>
													</td>
													<td>
														<ezf:select name="dsCtacPntTpCd_CS" ezfName="dsCtacPntTpCd_CS" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsCtacPntTpCd_CC" ezfDispName="dsCtacPntTpNm_CD" otherAttr=" style=\"width:100;\""/>
													</td>
													<td>
														<ezf:inputText name="dsCtacPntValTxt_C" ezfName="dsCtacPntValTxt_C" value="12345678901234567890" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"320\""/>
													</td>
													<td>
														<ezf:inputText name="dsCtacPsnExtnNum_C" ezfName="dsCtacPsnExtnNum_C" value="12345678901234567890" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"20\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:select name="svcCtacTpCd_CS" ezfName="svcCtacTpCd_CS" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcCtacTpCd_CC" ezfDispName="svcCtacTpDescTxt_CD" otherAttr=" style=\"width:100;\""/>
													</td>
													<td align="center"><ezf:anchor name="ctacPsnPk_C" ezfName="ctacPsnPk_C" ezfHyo="C" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_ContactDetail" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
													<ezf:label name="ctacPsnPk_C" ezfName="ctacPsnPk_C" ezfHyo="C" ezfArrayNo="0" /></ezf:anchor></td>
													<td>
														<ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" ezfHyo="C" ezfArrayNo="0" />
													</td>
													<td>
														<ezf:inputText name="effFromDt_C" ezfName="effFromDt_C" value="MM/DD/YYYY" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													</td>
													<td>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effFromDt_C', 4 ,{EZF_ROW_NUMBER});">
													</td>
													<td>
														<ezf:inputText name="effThruDt_C" ezfName="effThruDt_C" value="MM/DD/YYYY" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													</td>
													<td>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effThruDt_C', 4 ,{EZF_ROW_NUMBER});">
													</td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
											</div>
										</td>
									</tr>
								</table>
							</div>
							</c:when>
<%-- ##################################### Addl Attrb ######################################## --%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_01 == 'AddlAttrb'}">
							<script type="text/javascript">
								document.getElementById( "MachConfig" ).className = "pTab_OFF";
								document.getElementById( "Solution" ).className = "pTab_OFF";
								document.getElementById( "Contacts" ).className = "pTab_OFF";
								document.getElementById( "AddlAttrb" ).className = "pTab_ON";
								// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								// document.getElementById( "CurrentLoc" ).className = "pTab_OFF";
								// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								document.getElementById( "SlsOrdHist" ).className = "pTab_OFF";
								document.getElementById( "IBHistory" ).className = "pTab_OFF";
								document.getElementById( "ContrSmry" ).className = "pTab_OFF";
								document.getElementById( "SvcCallHist" ).className = "pTab_OFF";
								document.getElementById( "InvoiceHist" ).className = "pTab_OFF";
							</script>
							<div class="pTab_BODY_In">
								<div style="height:250; width:100%;" style="overflow-y:scroll;" >
								<table width="98%" border="0" cellpadding="1" cellspacing="0" align="center">
									<tr>
										<td>
											<table border="0">
											<col valign="top" width="22%">
											<col valign="top" width="1%">
											<col valign="top" width="25%">
											<col valign="top" width="25%">
											<col valign="top" width="25%">
												<tr>
													<td>
														<fieldset>
															<legend>Customer Reference Attributes</legend>
															<table width="22%" border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																			<col width="99" align="center">
																			<col width="2" align="center">
																			<col width="99" align="center">
																			<tr>
																				<td class="stab">Control Field1</td>
																				<td><br /></td>
																				<td><ezf:inputText name="ctrlFldTxt_01" ezfName="ctrlFldTxt_01" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"35\" size=\"13\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																			<col width="99" align="center">
																			<col width="2" align="center">
																			<col width="99" align="center">
																			<tr>
																				<td class="stab">Control Field2</td>
																				<td><br /></td>
																				<td><ezf:inputText name="ctrlFldTxt_02" ezfName="ctrlFldTxt_02" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"35\" size=\"13\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																			<col width="99" align="center">
																			<col width="2" align="center">
																			<col width="99" align="center">
																			<tr>
																				<td class="stab">Control Field3</td>
																				<td><br /></td>
																				<td><ezf:inputText name="ctrlFldTxt_03" ezfName="ctrlFldTxt_03" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"35\" size=\"13\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																			<col width="99" align="center">
																			<col width="2" align="center">
																			<col width="99" align="center">
																			<tr>
																				<td class="stab">Control Field4</td>
																				<td><br /></td>
																				<td><ezf:inputText name="ctrlFldTxt_04" ezfName="ctrlFldTxt_04" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"35\" size=\"13\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																			<col width="99" align="center">
																			<col width="2" align="center">
																			<col width="99" align="center">
																			<tr>
																				<td class="stab">Control Field5</td>
																				<td><br /></td>
																				<td><ezf:inputText name="ctrlFldTxt_05" ezfName="ctrlFldTxt_05" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"35\" size=\"13\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																			<col width="99" align="center">
																			<col width="2" align="center">
																			<col width="99" align="center">
																			<tr>
																				<td class="stab">Control Field6</td>
																				<td><br /></td>
																				<td><ezf:inputText name="ctrlFldTxt_06" ezfName="ctrlFldTxt_06" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"35\" size=\"13\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</fieldset>
													</td>
													<td><br /></td>
													<td>
														<fieldset>
															<legend>Pricing</legend>
															<table width="25%" border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="149" align="left">
																			<col width="2" align="center">
																			<col width="99" align="center">
																			<tr>
																				<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_PrcContr" otherAttr=" tabindex=\"-1\"">Pricing Contract#</ezf:anchor></td>
																				<td><br /></td>
																				<td><ezf:inputText name="prcContrNum_D" ezfName="prcContrNum_D" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"35\" size=\"13\" ezftoupper=\"\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="149" align="left">
																			<col width="2" align="center">
																			<col width="99" align="center">
																			<tr>
																				<td class="stab">Corporate Advantage</td>
																				<td><br /></td>
																				<td>
																					<ezf:select name="corpAdvtgStsCd_DS" ezfName="corpAdvtgStsCd_DS" ezfBlank="1" ezfCodeName="corpAdvtgStsCd_DC" ezfDispName="svcCorpAdvtgStsDescTxt_DD" otherAttr=" style=\"width:95px\""/>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="149" align="left">
																			<col width="2" align="center">
																			<col width="99" align="center">
																			<tr>
																				<td class="stab">End Customer PO#</td>
																				<td><br /></td>
																				<td><ezf:inputText name="custIssPoNum_D" ezfName="custIssPoNum_D" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"35\" size=\"13\" ezftoupper=\"\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="149" align="left">
																			<col width="2" align="center">
																			<col width="80" align="center">
																			<col width="19" align="center">
																			<tr>
																				<td class="stab">PO Expiration Date</td>
																				<td><br /></td>
																				<td><ezf:inputText name="dsPoExprDt_D" ezfName="dsPoExprDt_D" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																				<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsPoExprDt_D', 4);"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="159" align="left">
																			<col width="2" align="center">
																			<col width="89" align="left">
																			<tr>
																				<td class="stab">Hard Drive Removal Paid/Included</td>
																				<td><br /></td>
																				<td><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" /></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="159" align="left">
																			<col width="2" align="center">
																			<col width="89" align="left">
																			<tr>
																				<td class="stab">Hard Drive Erase Paid/Included</td>
																				<td><br /></td>
																				<td><ezf:inputCheckBox name="xxChkBox_D2" ezfName="xxChkBox_D2" value="Y" /></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="159" align="left">
																			<col width="2" align="center">
																			<col width="89" align="left">
																			<tr>
																				<td class="stab">Lease Return Fee Paid/Included</td>
																				<td><br /></td>
																				<td><ezf:inputCheckBox name="xxChkBox_D3" ezfName="xxChkBox_D3" value="Y" /></td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</fieldset>
													</td>
													<td>
														<fieldset>
															<legend>Service & Contracts</legend>
															<table width="25%" border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="285" style="table-layout: fixed;">
																			<col width="110" align="left">
																			<col width="2" align="center">
																			<col align="left">
																			<tr>
																				<td class="stab"><ezf:anchor name="svcBrCd_DA" ezfName="svcBrCd_DA" ezfEmulateBtn="1" ezfGuard="OpenWin_FieldServiceBranch" otherAttr=" tabindex=\"-1\"">Service Branch</ezf:anchor></td>
																				<td><br /></td>
																				<td><ezf:inputText name="svcBrCd_D" ezfName="svcBrCd_D" value="123" otherAttr=" maxlength=\"3\" size=\"3\" ezftoupper=\"\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="285" style="table-layout: fixed;">
																			<col width="110" align="left">
																			<col width="2" align="center">
																			<col align="left">
																			<tr>
																				<td class="stab">VIP Customer</td>
																				<td><br /></td>
																				<td><ezf:inputCheckBox name="xxChkBox_D4" ezfName="xxChkBox_D4" value="Y" /></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="285" style="table-layout: fixed;">
																			<col width="110" align="left">
																			<col width="2" align="center">
																			<col align="left">
																			<tr>
																				<td class="stab">iwRemote Status</td>
																				<td><br /></td>
																				
																				<td>
																					<ezf:select name="iwrCondCd_DS" ezfName="iwrCondCd_DS" ezfBlank="1" ezfCodeName="iwrCondCd_DC" ezfDispName="iwrCondDescTxt_DD" otherAttr=" style=\"width:164\""/>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="285" style="table-layout: fixed;">
																			<col width="110" align="left">
																			<col width="2" align="center">
																			<col align="left">
																			<tr>
																				<td class="stab">Network Connect</td>
																				<td><br /></td>
																				
																				<td>
																					<ezf:select name="svcNtwkConnStsCd_DS" ezfName="svcNtwkConnStsCd_DS" ezfBlank="1" ezfCodeName="svcNtwkConnStsCd_DC" ezfDispName="svcNtwkConnStsDescTxt_DD" otherAttr=" style=\"width:164\""/>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="285" style="table-layout: fixed;">
																			<col width="110" align="left">
																			<col width="2" align="center">
																			<col align="left">
																			<tr>
																				<td class="stab">Sold By BU</td>
																				<td><br /></td>
																				
																				<td>
																					<ezf:select name="sldByLineBizTpCd_DS" ezfName="sldByLineBizTpCd_DS" ezfBlank="1" ezfCodeName="sldByLineBizTpCd_DC" ezfDispName="lineBizTpDescTxt_D1" otherAttr=" style=\"width:164\""/>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="285" style="table-layout: fixed;">
																			<col width="110" align="left">
																			<col width="2" align="center">
																			<col align="left">
																			<tr>
																				<td class="stab">To Be Installed By</td>
																				<td><br /></td>
																				
																				<td>
																					<ezf:select name="istlBySvcPrvdPtyCd_DS" ezfName="istlBySvcPrvdPtyCd_DS" ezfBlank="1" ezfCodeName="istlBySvcPrvdPtyCd_DC" ezfDispName="svcPrvdPtyDescTxt_D1" otherAttr=" style=\"width:164\""/>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="285" style="table-layout: fixed;">
																			<col width="110" align="left">
																			<col width="2" align="center">
																			<col align="left">
																			<tr>
																				<td class="stab">Service Provided By</td>
																				<td><br /></td>
																				
																				<td>
																					<ezf:select name="svcBySvcPrvdPtyCd_DS" ezfName="svcBySvcPrvdPtyCd_DS" ezfBlank="1" ezfCodeName="svcBySvcPrvdPtyCd_DC" ezfDispName="svcPrvdPtyDescTxt_D2" otherAttr=" style=\"width:164\""/>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="285" style="table-layout: fixed;">
																			<col width="110" align="left">
																			<col width="2" align="center">
																			<col width="65" align="left">
																			<col width="2" align="center">
																			<col align="left">
																			<tr>
																				<td class="stab"><ezf:anchor name="prfTechCd_DA" ezfName="prfTechCd_DA" ezfEmulateBtn="1" ezfGuard="OpenWin_PreferredTech" otherAttr=" tabindex=\"-1\"">Preferred Tech</ezf:anchor></td>
																				<td><br /></td>
																				<td><ezf:inputText name="prfTechCd_D" ezfName="prfTechCd_D" value="12345678" otherAttr=" maxlength=\"8\" size=\"8\" ezftoupper=\"\""/></td>
																				<td><br /></td>
																				<td><ezf:inputText name="techNm_D1" ezfName="techNm_D1" value="---------1---------2---------3---------4-----5" otherAttr=" maxlength=\"45\" size=\"13\" ezftoupper=\"\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="285" style="table-layout: fixed;">
																			<col width="110" align="left">
																			<col width="2" align="center">
																			<col width="65" align="left">
																			<col width="2" align="center">
																			<col align="left">
																			<tr>
																				<td class="stab"><ezf:anchor name="reqTechCd_DA" ezfName="reqTechCd_DA" ezfEmulateBtn="1" ezfGuard="OpenWin_RequestTech" otherAttr=" tabindex=\"-1\"">Requested Tech</ezf:anchor></td>
																				<td><br /></td>
																				<td><ezf:inputText name="reqTechCd_D" ezfName="reqTechCd_D" value="87654321" otherAttr=" maxlength=\"8\" size=\"8\" ezftoupper=\"\""/></td>
																				<td><br /></td>
																				<td><ezf:inputText name="techNm_D2" ezfName="techNm_D2" value="---------1---------2---------3---------4-----5" otherAttr=" maxlength=\"45\" size=\"13\" ezftoupper=\"\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</fieldset>
													</td>
													<td>
														<fieldset>
															<legend>Miscellaneous</legend>
															<table width="25%" border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="149" align="left">
																			<col width="2" align="center">
																			<col width="99" align="center">
																			<tr>
																				<td class="stab"># of Licenses</td>
																				<td><br /></td>
																				<td><ezf:inputText name="svcLicCnt_D" ezfName="svcLicCnt_D" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"28\" size=\"13\" ezftoupper=\"\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="149" align="left">
																			<col width="2" align="center">
																			<col width="99" align="center">
																			<tr>
																				<td class="stab">Financial Branch</td>
																				<td><br /></td>
																				<td><ezf:inputText name="finBrCd_D" ezfName="finBrCd_D" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"35\" size=\"13\" ezftoupper=\"\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="149" align="left">
																			<col width="1" align="center">
																			<col width="100" align="left">
																			<tr>
																				<td class="stab">eManage Pilot</td>
																				<td><br /></td>
																				<td><ezf:inputCheckBox name="xxChkBox_D5" ezfName="xxChkBox_D5" value="Y" /></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="149" align="left">
																			<col width="2" align="center">
																			<col width="99" align="center">
																			<tr>
																				<td class="stab">eManage Primary Note</td>
																				<td><br /></td>
																				<td><ezf:inputText name="enetCmntTxt_01" ezfName="enetCmntTxt_01" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"35\" size=\"13\" ezftoupper=\"\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="149" align="left">
																			<col width="2" align="center">
																			<col width="99" align="center">
																			<tr>
																				<td class="stab">eManage Secondary Note</td>
																				<td><br /></td>
																				<td><ezf:inputText name="enetCmntTxt_02" ezfName="enetCmntTxt_02" value="---------1---------2---------3-----5" otherAttr=" maxlength=\"35\" size=\"13\" ezftoupper=\"\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="149" align="left">
																			<col width="2" align="center">
																			<col width="45" align="center">
																			<col width="9" align="center">
																			<col width="45" align="center">
																			<tr>
																				<td class="stab">Business Hour Sun</td>
																				<td><br /></td>
																				<td><ezf:inputText name="xxSvcFromHourMnTxt_D1" ezfName="xxSvcFromHourMnTxt_D1" value="12:34" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" maxlength=\"5\" size=\"5\" ezftoupper=\"\""/></td>
																				<td class="stab">-</td>
																				<td><ezf:inputText name="xxSvcToHourMnTxt_D1" ezfName="xxSvcToHourMnTxt_D1" value="21:21" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" maxlength=\"5\" size=\"5\" ezftoupper=\"\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="149" align="left">
																			<col width="2" align="center">
																			<col width="45" align="center">
																			<col width="9" align="center">
																			<col width="45" align="center">
																			<tr>
																				<td class="stab">Business Hour Mon-Fri</td>
																				<td><br /></td>
																				<td><ezf:inputText name="xxSvcFromHourMnTxt_D2" ezfName="xxSvcFromHourMnTxt_D2" value="12:34" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" maxlength=\"5\" size=\"5\" ezftoupper=\"\""/></td>
																				<td class="stab">-</td>
																				<td><ezf:inputText name="xxSvcToHourMnTxt_D2" ezfName="xxSvcToHourMnTxt_D2" value="21:21" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" maxlength=\"5\" size=\"5\" ezftoupper=\"\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table border="0" cellpadding="1" cellspacing="0" width="250" style="table-layout: fixed;">
																			<col width="149" align="left">
																			<col width="2" align="center">
																			<col width="45" align="center">
																			<col width="9" align="center">
																			<col width="45" align="center">
																			<tr>
																				<td class="stab">Business Hour Sat</td>
																				<td><br /></td>
																				<td><ezf:inputText name="xxSvcFromHourMnTxt_D3" ezfName="xxSvcFromHourMnTxt_D3" value="12:34" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" maxlength=\"5\" size=\"5\" ezftoupper=\"\""/></td>
																				<td class="stab">-</td>
																				<td><ezf:inputText name="xxSvcToHourMnTxt_D3" ezfName="xxSvcToHourMnTxt_D3" value="21:21" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" maxlength=\"5\" size=\"5\" ezftoupper=\"\""/></td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</fieldset>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table border="0">
											<col valign="top" width="22%">
											<col valign="top" width="1%">
											<col valign="top" width="25%">
											<col valign="top" width="45%">
											<col valign="top" width="5%">
												<tr>
													<td><br /></td>
													<td><br /></td>
													<td><br /></td>
													<td>
														<fieldset>
															<legend>Do Not Send Tech(s)</legend>
															<table>
																<tr>
																	<td>
																		
																		<table border="1" cellpadding="1" cellspacing="0" width="345" style="table-layout: fixed;">
																			<col width="25" align="center">
																			<col width="120" align="center">
																			<col width="100" align="center">
																			<col width="120" align="center">
																			<tr>
																				<td class="pClothBs"><br /></td>
																				<td class="pClothBs">Tech Code</td>
																				<td class="pClothBs">Tech Name</td>
																				<td class="pClothBs">Date</td>
																			</tr>
																		</table>
																		
																		<div style="overflow-y:scroll; height:100; width:362;">
																		<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																			<col width="25" align="center">
																			<col width="100" align="center">
																			<col width="20" align="center">
																			<col width="100" align="center">
																			<col width="100" align="center">
																			<col width="" align="center">
																			<col width="20" align="center">
																			<ezf:row ezfHyo="E">
																			<tr>
																				<td>
																					<ezf:inputRadio name="xxRadioBtn_E" ezfName="xxRadioBtn_E" value="{EZF_ROW_NUMBER}" />
																				</td>
																				<td>
																					<ezf:inputText name="nonPrfTechCd_E" ezfName="nonPrfTechCd_E" value="---------1---------2" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"1000\" ezftoupper=\"\""/>
																				</td>
																				<td>
																					<ezf:inputButton name="OpenWin_NonPreferredTech" value="T" ezfHyo="E" ezfArrayNo="0" otherAttr=" htmlclass=\"pBtn7\""/>
																				</td>
																				<td>
																					<ezf:inputText name="techNm_E" ezfName="techNm_E" value="---------1---------2" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"1000\" ezftoupper=\"\""/>
																				</td>
																				<td>
																					<ezf:inputText name="effThruDt_E" ezfName="effThruDt_E" value="MM/DD/YYYY" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																				</td>
																				<td></td>
																				<td>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effThruDt_E', 4 ,{EZF_ROW_NUMBER});">
																				</td>
																			</tr>
																			</ezf:row>
																		</table>
																		</div>
																	</td>
																	<td>
																		<table>
																			<ezf:inputButton name="InsertDoNotSendTech" value="Insert" otherAttr=" htmlclass=\"pBtn4\""/>
																			<ezf:inputButton name="DeleteDoNotSendTech" value="Delete" otherAttr=" htmlclass=\"pBtn4\""/>
																		</table>
																	</td>
																</tr>
															</table>
														</fieldset>
													</td>
													<td><br /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								</div>
							</div>
							</c:when>
<%-- ##################################### Current Loc ######################################## --%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_01 == 'CurrentLoc'}">
<!-- START 2023/07/10 Y.Nagasawa [QC#61524, DEL] -->
<!--                            <script type="text/javascript"> -->
<!--                                document.getElementById( "MachConfig" ).className = "pTab_OFF"; -->
<!--                                document.getElementById( "Solution" ).className = "pTab_OFF"; -->
<!--                                document.getElementById( "Contacts" ).className = "pTab_OFF"; -->
<!--                                document.getElementById( "AddlAttrb" ).className = "pTab_OFF"; -->
<!--                                document.getElementById( "CurrentLoc" ).className = "pTab_ON"; -->
<!--                                document.getElementById( "SlsOrdHist" ).className = "pTab_OFF"; -->
<!--                                document.getElementById( "IBHistory" ).className = "pTab_OFF"; -->
<!--                                document.getElementById( "ContrSmry" ).className = "pTab_OFF"; -->
<!--                                document.getElementById( "SvcCallHist" ).className = "pTab_OFF"; -->
<!--                                document.getElementById( "InvoiceHist" ).className = "pTab_OFF"; -->
<!--                            </script> -->
<!--                            <div class="pTab_BODY_In"> -->
<!--                                <div style="height:250; width:1082;"> -->
<!--                                <table width="98%" border="0" cellpadding="1" cellspacing="0" align="center"> -->
<!--                                    <tr> -->
<!--                                        <td><br /></td> -->
<!--                                    </tr> -->
<!--                                    <tr> -->
<!--                                        <td> -->
<!--                                            <table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;"> -->
<!--                                                <col width="15" align="left"> -->
<!--                                                <col width="150" align="left"> -->
<!--                                                <col width="2" align="left"> -->
<!--                                                <col width="350" align="left"> -->
<!--                                                <col width="580" align="left"> -->
<!--                                                <tr> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td class="stab">Location Name</td> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td><input type="text" name="locNm_F" ezfName="locNm_F" value="---------1---------2---------3-----5" maxlength="35" size="35" ezftoupper></td> -->
<!--                                                </tr> -->
<!--                                            </table> -->
<!--                                        </td> -->
<!--                                    </tr> -->
<!--                                    <tr> -->
<!--                                        <td> -->
<!--                                            <table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;"> -->
<!--                                                <col width="15" align="left"> -->
<!--                                                <col width="150" align="left"> -->
<!--                                                <col width="2" align="left"> -->
<!--                                                <col width="350" align="left"> -->
<!--                                                <col width="580" align="left"> -->
<!--                                                <tr> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td class="stab">Addtional Location Name</td> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td><input type="text" name="addlLocNm_F" ezfName="addlLocNm_F" value="---------1---------2---------3-----5" maxlength="35" size="35" ezftoupper></td> -->
<!--                                                </tr> -->
<!--                                            </table> -->
<!--                                        </td> -->
<!--                                    </tr> -->
<!--                                    <tr> -->
<!--                                        <td> -->
<!--                                            <table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;"> -->
<!--                                                <col width="15" align="left"> -->
<!--                                                <col width="150" align="left"> -->
<!--                                                <col width="2" align="left"> -->
<!--                                                <col width="350" align="left"> -->
<!--                                                <col width="2" align="left"> -->
<!--                                                <col width="100" align="left"> -->
<!--                                                <col width="50" align="left"> -->
<!--                                                <col width="40" align="left"> -->
<!--                                                <tr> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td class="stab">Country</td> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td> -->
<!--                                                        <select name="ctryCd_FS" style="width:216px" ezfname="ctryCd_FS" ezflist="ctryCd_FC,ctryDescTxt_FD,99, ,blank"> -->
<!--                                                            <option> </option> -->
<!--                                                            <option>---------1</option> -->
<!--                                                            <option>---------1---------2</option> -->
<!--                                                            <option>---------1---------2---------3</option> -->
<!--                                                        </select> -->
<!--                                                    </td> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td class="stab">Postal Code</td> -->
<!--                                                    <td><input type="text" name="postCd_F" ezfName="postCd_F" value="---------1----5" maxlength="5" size="5" ezftoupper></td> -->
<!--                                                    <td><input type="button" class="pBtn0" name="SelectPostalCode" value=">>" onclick="sendServer(this)"></td> -->
<!--                                                </tr> -->
<!--                                            </table> -->
<!--                                        </td> -->
<!--                                    </tr> -->
<!--                                    <tr> -->
<!--                                        <td> -->
<!--                                            <table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;"> -->
<!--                                                <col width="15" align="left"> -->
<!--                                                <col width="150" align="left"> -->
<!--                                                <col width="2" align="left"> -->
<!--                                                <col width="350" align="left"> -->
<!--                                                <col width="2" align="left"> -->
<!--                                                <col width="100" align="left"> -->
<!--                                                <col width="350" align="left"> -->
<!--                                                <tr> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td class="stab">Address1</td> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td><input type="text" name="firstLineAddr_F" ezfName="firstLineAddr_F" value="---------1---------2---------3-----5" maxlength="35" size="35" ezftoupper></td> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td class="stab">City</td> -->
<!--                                                    <td><input type="text" name="ctyAddr_F" ezfName="ctyAddr_F" value="---------1---------2---------3-----5" maxlength="35" size="35" ezftoupper></td> -->
<!--                                                </tr> -->
<!--                                            </table> -->
<!--                                        </td> -->
<!--                                    </tr> -->
<!--                                    <tr> -->
<!--                                        <td> -->
<!--                                            <table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;"> -->
<!--                                                <col width="15" align="left"> -->
<!--                                                <col width="150" align="left"> -->
<!--                                                <col width="2" align="left"> -->
<!--                                                <col width="350" align="left"> -->
<!--                                                <col width="2" align="left"> -->
<!--                                                <col width="100" align="left"> -->
<!--                                                <col width="60" align="left"> -->
<!--                                                <tr> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td class="stab">Address2</td> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td><input type="text" name="scdLineAddr_F" ezfName="scdLineAddr_F" value="---------1---------2---------3-----5" maxlength="35" size="35" ezftoupper></td> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td class="stab">State</td> -->
<!--                                                    <td><input type="text" name="stCd_F" ezfName="stCd_F" value="---------1---------2---------3-----5" maxlength="2" size="2" ezftoupper></td> -->
<!--                                                </tr> -->
<!--                                            </table> -->
<!--                                        </td> -->
<!--                                    </tr> -->
<!--                                    <tr> -->
<!--                                        <td> -->
<!--                                            <table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;"> -->
<!--                                                <col width="15" align="left"> -->
<!--                                                <col width="150" align="left"> -->
<!--                                                <col width="2" align="left"> -->
<!--                                                <col width="350" align="left"> -->
<!--                                                <col width="2" align="left"> -->
<!--                                                <col width="100" align="left"> -->
<!--                                                <col width="350" align="left"> -->
<!--                                                <tr> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td class="stab">Address3</td> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td><input type="text" name="thirdLineAddr_F" ezfName="thirdLineAddr_F" value="---------1---------2---------3-----5" maxlength="35" size="35" ezftoupper></td> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td class="stab">County</td> -->
<!--                                                    <td><input type="text" name="cntyNm_F" ezfName="cntyNm_F" value="---------1---------2---------3-----5" maxlength="30" size="30" ezftoupper></td> -->
<!--                                                </tr> -->
<!--                                            </table> -->
<!--                                        </td> -->
<!--                                    </tr> -->
<!--                                    <tr> -->
<!--                                        <td> -->
<!--                                            <table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;"> -->
<!--                                                <col width="15" align="left"> -->
<!--                                                <col width="150" align="left"> -->
<!--                                                <col width="2" align="left"> -->
<!--                                                <col width="350" align="left"> -->
<!--                                                <col width="2" align="left"> -->
<!--                                                <col width="100" align="left"> -->
<!--                                                <col width="350" align="left"> -->
<!--                                                <tr> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td class="stab">Address4</td> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td><input type="text" name="frthLineAddr_F" ezfName="frthLineAddr_F" value="---------1---------2---------3-----5" maxlength="35" size="35" ezftoupper></td> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td class="stab">Province</td> -->
<!--                                                    <td><input type="text" name="provNm_F" ezfName="provNm_F" value="---------1---------2---------3-----5" maxlength="35" size="35" ezftoupper></td> -->
<!--                                                </tr> -->
<!--                                            </table> -->
<!--                                        </td> -->
<!--                                    </tr> -->
<!--                                    <tr> -->
<!--                                        <td> -->
<!--                                            <table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;"> -->
<!--                                                <col width="15" align="left"> -->
<!--                                                <col width="150" align="left"> -->
<!--                                                <col width="2" align="left"> -->
<!--                                                <col width="350" align="left"> -->
<!--                                                <tr> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td class="stab">Floor Name</td> -->
<!--                                                    <td><br /></td> -->
<!--                                                    <td><input type="text" name="svcMachFlNm_F" ezfName="svcMachFlNm_F" value="---------1---------2---------3-----5" maxlength="35" size="35" ezftoupper></td> -->
<!--                                                </tr> -->
<!--                                            </table> -->
<!--                                        </td> -->
<!--                                    </tr> -->
<!--                                </table> -->
<!--                                </div> -->
<!--                            </div> -->
<!-- END 2023/07/10 Y.Nagasawa [QC#61524, DEL] -->
							</c:when>
<%-- ##################################### Sls Ord Hist ######################################## --%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_01 == 'SlsOrdHist'}">
							<script type="text/javascript">
								document.getElementById( "MachConfig" ).className = "pTab_OFF";
								document.getElementById( "Solution" ).className = "pTab_OFF";
								document.getElementById( "Contacts" ).className = "pTab_OFF";
								document.getElementById( "AddlAttrb" ).className = "pTab_OFF";
								// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								// document.getElementById( "CurrentLoc" ).className = "pTab_OFF";
								// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								document.getElementById( "SlsOrdHist" ).className = "pTab_ON";
								document.getElementById( "IBHistory" ).className = "pTab_OFF";
								document.getElementById( "ContrSmry" ).className = "pTab_OFF";
								document.getElementById( "SvcCallHist" ).className = "pTab_OFF";
								document.getElementById( "InvoiceHist" ).className = "pTab_OFF";
							</script>
							<div class="pTab_BODY_In">
								<!-- Prev/Next Page-->
								<table width="99%">
									<tr align="right">
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="G" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_G" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_G" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_G" />
											</jsp:include>
										</td>
									</tr>
								</table>
								
								<table border="0" cellpadding="1" cellspacing="0" align="center">
									<col valign="top">
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" width="1065" style="table-layout: fixed;">
												<col width="15" align="center">
												<col width="90" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="160" align="center">
												<col width="160" align="center">
												<col width="160" align="center">
												<col width="160" align="center">
												<tr>
													<td class="pClothBs"><br /></td>
													<td class="pClothBs">Order Number</td>
													<td class="pClothBs">Order Date</td>
													<td class="pClothBs">Order Type</td>
													<td class="pClothBs">Order Reason</td>
													<td class="pClothBs">Line Type</td>
													<td class="pClothBs">Account Sold To Name</td>
													<td class="pClothBs">Account Bill To Name</td>
													<td class="pClothBs">Account Ship To Name</td>
													<td class="pClothBs">Ship To Address</td>
												</tr>
											</table>

											<div style="overflow-y:scroll; height:210; width:1082;">
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col width="15" align="center">
												<col width="90" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="160" align="center">
												<col width="160" align="center">
												<col width="160" align="center">
												<col width="160" align="center">
												<ezf:row ezfHyo="G">
												<tr>
													<td><br /></td>
													<td align="center"><ezf:anchor name="cpoOrdNum_G" ezfName="cpoOrdNum_G" ezfHyo="G" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_OrderEntry" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
													<ezf:label name="cpoOrdNum_G" ezfName="cpoOrdNum_G" ezfHyo="G" ezfArrayNo="0" /></ezf:anchor></td>
													<td>
														<ezf:inputText name="cpoOrdDt_G" ezfName="cpoOrdDt_G" value="MM/DD/YYYY" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="dsOrdCatgDescTxt_G" ezfName="dsOrdCatgDescTxt_G" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="dsOrdTpDescTxt_G" ezfName="dsOrdTpDescTxt_G" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="dsOrdLineCatgDescTxt_G" ezfName="dsOrdLineCatgDescTxt_G" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td align="center"><ezf:anchor name="locNm_G1" ezfName="locNm_G1" ezfHyo="G" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_AcctSoldTo" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
													<ezf:label name="locNm_G1" ezfName="locNm_G1" ezfHyo="G" ezfArrayNo="0" /></ezf:anchor></td>
													<td align="center"><ezf:anchor name="locNm_G2" ezfName="locNm_G2" ezfHyo="G" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_AcctBillTo" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
													<ezf:label name="locNm_G2" ezfName="locNm_G2" ezfHyo="G" ezfArrayNo="0" /></ezf:anchor></td>
													<td align="center"><ezf:anchor name="shipToLocNm_G" ezfName="shipToLocNm_G" ezfHyo="G" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_AcctShipTo" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
													<ezf:label name="shipToLocNm_G" ezfName="shipToLocNm_G" ezfHyo="G" ezfArrayNo="0" /></ezf:anchor></td>
													<td>
														<ezf:inputText name="shipToAddr_G" ezfName="shipToAddr_G" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
											</div>
										</td>
									</tr>
								</table>
							</div>
							</c:when>
<%-- ##################################### IB Hist ######################################## --%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_01 == 'IBHistory'}">
							<script type="text/javascript">
								document.getElementById( "MachConfig" ).className = "pTab_OFF";
								document.getElementById( "Solution" ).className = "pTab_OFF";
								document.getElementById( "Contacts" ).className = "pTab_OFF";
								document.getElementById( "AddlAttrb" ).className = "pTab_OFF";
								// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								// document.getElementById( "CurrentLoc" ).className = "pTab_OFF";
								// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								document.getElementById( "SlsOrdHist" ).className = "pTab_OFF";
								document.getElementById( "IBHistory" ).className = "pTab_ON";
								document.getElementById( "ContrSmry" ).className = "pTab_OFF";
								document.getElementById( "SvcCallHist" ).className = "pTab_OFF";
								document.getElementById( "InvoiceHist" ).className = "pTab_OFF";
							</script>
							<div class="pTab_BODY_In">
								<!-- Prev/Next Page-->
								<table width="99%">
									<tr align="right">
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="I" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_I" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_I" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_I" />
											</jsp:include>
										</td>
									</tr>
								</table>
								
								<table border="0" cellpadding="1" cellspacing="0" align="center">
									<col valign="top">
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" width="765" style="table-layout: fixed;">
												<col width="15" align="center">
												<col width="100" align="center">
												<col width="150" align="center">
												<col width="150" align="center">
												<col width="150" align="center">
												<col width="150" align="center">
												<col width="50" align="center">
												<tr>
													<td class="pClothBs"><br /></td>
													<td class="pClothBs">Transaction Date</td>
													<td class="pClothBs">Field Update</td>
													<td class="pClothBs">Old Value</td>
													<td class="pClothBs">New Value</td>
													<td class="pClothBs">Updated By</td>
													<td class="pClothBs">Notes</td>
												</tr>
											</table>

											<div style="overflow-y:scroll; height:210; width:782;">
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col width="15" align="center">
												<col width="100" align="center">
												<col width="150" align="center">
												<col width="150" align="center">
												<col width="150" align="center">
												<col width="150" align="center">
												<col width="50" align="center">
												<ezf:row ezfHyo="I">
												<tr>
													<td><br /></td>
													<td>
														<ezf:inputText name="trxRgtnDt_I" ezfName="trxRgtnDt_I" value="MM/DD/YYYY" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="machHistAttrbLongNm_I" ezfName="machHistAttrbLongNm_I" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"200\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="oldValTxt_I" ezfName="oldValTxt_I" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="newValTxt_I" ezfName="newValTxt_I" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="updUsrId_I" ezfName="updUsrId_I" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td align="center"><ezf:anchor name="xxYesNoCd_I" ezfName="xxYesNoCd_I" ezfHyo="I" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_SvcMemo" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
													<ezf:label name="xxYesNoCd_I" ezfName="xxYesNoCd_I" ezfHyo="I" ezfArrayNo="0" /></ezf:anchor></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
											</div>
										</td>
									</tr>
								</table>
							</div>
							</c:when>
<%-- ##################################### Contr Smry ######################################## --%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_01 == 'ContrSmry'}">
							<script type="text/javascript">
								document.getElementById( "MachConfig" ).className = "pTab_OFF";
								document.getElementById( "Solution" ).className = "pTab_OFF";
								document.getElementById( "Contacts" ).className = "pTab_OFF";
								document.getElementById( "AddlAttrb" ).className = "pTab_OFF";
								// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								// document.getElementById( "CurrentLoc" ).className = "pTab_OFF";
								// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								document.getElementById( "SlsOrdHist" ).className = "pTab_OFF";
								document.getElementById( "IBHistory" ).className = "pTab_OFF";
								document.getElementById( "ContrSmry" ).className = "pTab_ON";
								document.getElementById( "SvcCallHist" ).className = "pTab_OFF";
								document.getElementById( "InvoiceHist" ).className = "pTab_OFF";
							</script>
							<div class="pTab_BODY_In">
								<!-- Prev/Next Page-->
								<table width="99%">
									<tr align="right">
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="J" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_J" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_J" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_J" />
											</jsp:include>
										</td>
									</tr>
								</table>
								
								<table border="0" cellpadding="1" cellspacing="0" align="center">
									<col valign="top">
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" width="1065" style="table-layout: fixed;">
												<col width="15" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="90" align="center">
												<col width="100" align="center">
												<col width="80" align="center">
												<col width="120" align="center">
												<col width="250" align="center">
												<col width="90" align="center">
												<tr>
													<td class="pClothBs"><br /></td>
													<td class="pClothBs">Contract#</td>
													<td class="pClothBs">Status</td>
													<td class="pClothBs">Start Date</td>
													<td class="pClothBs">End Date</td>
													<td class="pClothBs">Contract Type</td>
													<td class="pClothBs">Supply Inclusive</td>
													<td class="pClothBs">Billing Cycle</td>
													<td class="pClothBs">Meter Read Method</td>
													<td class="pClothBs">Line Description</td>
													<td class="pClothBs">Service Branch</td>
												</tr>
											</table>

											<div style="overflow-y:scroll; height:210; width:1082;">
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col width="15" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="90" align="center">
												<col width="100" align="center">
												<col width="80" align="center">
												<col width="120" align="center">
												<col width="250" align="center">
												<col width="90" align="center">
												<ezf:row ezfHyo="J">
												<tr>
													<td><br /></td>
													<td align="center"><ezf:anchor name="dsContrNum_J" ezfName="dsContrNum_J" ezfHyo="J" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_ContrNumJ" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
													<ezf:label name="dsContrNum_J" ezfName="dsContrNum_J" ezfHyo="J" ezfArrayNo="0" /></ezf:anchor></td>
													<td>
														<ezf:inputText name="dsContrCtrlStsNm_J" ezfName="dsContrCtrlStsNm_J" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="contrEffFromDt_J" ezfName="contrEffFromDt_J" value="MM/DD/YYYY" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="contrEffThruDt_J" ezfName="contrEffThruDt_J" value="MM/DD/YYYY" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="dsContrCatgDescTxt_J" ezfName="dsContrCatgDescTxt_J" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputCheckBox name="xxChkBox_J" ezfName="xxChkBox_J" value="Y" ezfHyo="J" ezfArrayNo="0" />
													</td>
													<td>
														<ezf:inputText name="bllgCycleDescTxt_J" ezfName="bllgCycleDescTxt_J" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="mtrReadMethDescTxt_J" ezfName="mtrReadMethDescTxt_J" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="svcContrRefCmntTxt_J" ezfName="svcContrRefCmntTxt_J" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"32\" maxlength=\"300\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="svcContrBrDescTxt_J" ezfName="svcContrBrDescTxt_J" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
											</div>
										</td>
									</tr>
								</table>
							</div>
							</c:when>
<%-- ##################################### Svc Call Hist ######################################## --%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_01 == 'SvcCallHist'}">
							<script type="text/javascript">
								document.getElementById( "MachConfig" ).className = "pTab_OFF";
								document.getElementById( "Solution" ).className = "pTab_OFF";
								document.getElementById( "Contacts" ).className = "pTab_OFF";
								document.getElementById( "AddlAttrb" ).className = "pTab_OFF";
								// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								// document.getElementById( "CurrentLoc" ).className = "pTab_OFF";
								// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								document.getElementById( "SlsOrdHist" ).className = "pTab_OFF";
								document.getElementById( "IBHistory" ).className = "pTab_OFF";
								document.getElementById( "ContrSmry" ).className = "pTab_OFF";
								document.getElementById( "SvcCallHist" ).className = "pTab_ON";
								document.getElementById( "InvoiceHist" ).className = "pTab_OFF";
							</script>
							<div class="pTab_BODY_In">
								<!-- Prev/Next Page-->
								<table width="99%">
									<tr align="right">
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="K" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_K" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_K" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_K" />
											</jsp:include>
										</td>
									</tr>
								</table>
								<div style="padding-left:15; padding-right:15; height:232;">
								<table border="0" cellpadding="1" cellspacing="0" align="center">
									<col valign="top">
									<tr>
										<td>
										<div id="Top"' style="width:1076; display:block; overflow:hidden; margin:0px; padding:0px;"
										onscroll="synchroScrollLeft( this.id, new Array( 'Top' ));" >
											<table border="1" cellpadding="1" cellspacing="0" width="1065" style="table-layout: fixed;">
												<col width="15" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="160" align="center">
												<col width="160" align="center">
												<col width="80" align="center">
												<col width="110" align="center">
												<col width="155" align="center">
												<col width="155" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="150" align="center">
												<tr>
													<td class="pClothBs"><br /></td>
													<td class="pClothBs">FSR#</td>
													<td class="pClothBs">Task#</td>
													<td class="pClothBs">Task Type</td>
													<td class="pClothBs">Customer Name</td>
													<td class="pClothBs">Status</td>
													<td class="pClothBs">Date Reserved</td>
													<td class="pClothBs">Problem Description</td>
													<td class="pClothBs">Resolution</td>
													<td class="pClothBs">Start Date</td>
													<td class="pClothBs">End Date</td>
													<td class="pClothBs">Service Manager</td>
												</tr>
											</table>
										</div>
											
										<div id ="Detail" style="overflow:scroll; height:210; width:1093;"
										onscroll="synchroScrollLeft( this.id, new Array( 'Top' ));" >
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
												<col width="15" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="160" align="center">
												<col width="160" align="center">
												<col width="80" align="center">
												<col width="110" align="center">
												<col width="155" align="center">
												<col width="155" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="150" align="center">
												<% int idx = 0; %>
												<ezf:row ezfHyo="K">
												<tr>
													<td><br /></td>
													<td align="center">
													<a href="<%= getCustomAppURL("EXTNE307T020") %>?taskNum=<%=bMsg.K.no(idx).svcTaskNum_K.getValue()%>" target="A" border="0">
													<ezf:label name="fsrNum_K" ezfName="fsrNum_K" ezfHyo="K" ezfArrayNo="0" /></a></td>
													<% idx++; %>
													<td>
														<ezf:inputText name="svcTaskNum_K" ezfName="svcTaskNum_K" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="xxScrItem54Txt_K" ezfName="xxScrItem54Txt_K" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="locNm_K" ezfName="locNm_K" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="svcTaskStsNm_K" ezfName="svcTaskStsNm_K" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="svcTaskRcvDt_K" ezfName="svcTaskRcvDt_K" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="svcPblmTpDescTxt_K" ezfName="svcPblmTpDescTxt_K" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="svcPblmCrctTpDescTxt_K" ezfName="svcPblmCrctTpDescTxt_K" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="svcTaskSchdDt_K" ezfName="svcTaskSchdDt_K" value="MM/DD/YYYY" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="svcTaskCloDt_K" ezfName="svcTaskCloDt_K" value="MM/DD/YYYY" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="xxFullNm_K" ezfName="xxFullNm_K" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"300\" ezftoupper=\"\""/>
													</td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
										</td>
									</tr>
								</table>
								</div>
							</div>
							</c:when>
<%-- ##################################### Invoice Hist ######################################## --%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_01 == 'InvoiceHist'}">
							<script type="text/javascript">
								document.getElementById( "MachConfig" ).className = "pTab_OFF";
								document.getElementById( "Solution" ).className = "pTab_OFF";
								document.getElementById( "Contacts" ).className = "pTab_OFF";
								document.getElementById( "AddlAttrb" ).className = "pTab_OFF";
								// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								// document.getElementById( "CurrentLoc" ).className = "pTab_OFF";
								// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
								document.getElementById( "SlsOrdHist" ).className = "pTab_OFF";
								document.getElementById( "IBHistory" ).className = "pTab_OFF";
								document.getElementById( "ContrSmry" ).className = "pTab_OFF";
								document.getElementById( "SvcCallHist" ).className = "pTab_OFF";
								document.getElementById( "InvoiceHist" ).className = "pTab_ON";
							</script>
							<div class="pTab_BODY_In">
								<!-- Prev/Next Page-->
								<table style="table-layout: fixed;" width="1082px">
									<tr>
										<td>
											<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
										</td>
										<td  align="right">
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="L" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_L" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_L" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_L" />
											</jsp:include>
										</td>
									</tr>
								</table>
								
								<table border="0" cellpadding="1" cellspacing="0" align="center">
									<col valign="top">
									<tr>
										<td>
<div id="parentBoxL">
	<div style="float:left; display:block"> <!-- left table -->
		<div id='leftTblHead' style="display:block;">
		</div>
		<div id='leftTbl' style="float:left; display:block; height:204px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
		</div>
	</div>  <!-- left table -->
	<div style="float:left"> <!-- right table -->
		<div id='rightTblHead' style="width:1082px; display:block; overflow:hidden; margin:0px; padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="LHEAD" width="1082px" style="margin-right:20px" >
												<col width="75" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="95" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="90" align="center">
												<tr height="24px">
													<td id="LH0" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'L', 'invNum_L' )">Invoice#<img id="sortIMG.invNum_L" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="LH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'L', 'sysSrcDescTxt_L' )">Source Type<img id="sortIMG.sysSrcDescTxt_L" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="LH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'L', 'dsContrNum_L' )">Contract#<img id="sortIMG.dsContrNum_L" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="LH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'L', 'locNm_L1' )">Bill To<img id="sortIMG.locNm_L1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="LH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'L', 'locNm_L2' )">Ship To<img id="sortIMG.locNm_L2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="LH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'L', 'invTpDescTxt_L' )">Invoice Type<img id="sortIMG.'" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="LH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'L', 'invTotDealNetAmt_L' )">Total<img id="sortIMG.invTotDealNetAmt_L" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="LH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'L', 'dealRmngBalGrsAmt_L' )">Due<img id="sortIMG.dealRmngBalGrsAmt_L" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="LH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'L', 'xxApplyGrsAmt_L' )">Applied<img id="sortIMG.xxApplyGrsAmt_L" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="LH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'L', 'netDueDt_L' )">Due Date<img id="sortIMG.netDueDt_L" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="LH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'L', 'cpoOrdNum_L' )">Source#<img id="sortIMG.cpoOrdNum_L" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="LH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'L', 'invDt_L' )">Invoiced Date<img id="sortIMG.invDt_L" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="LH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'L', 'acctDt_L' )">GL Posted Date<img id="sortIMG.acctDt_L" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
											</table>
		</div>

		<div id="rightTbl" style="width:1082px; height:204px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="L" width="1082px" >
												<col width="75" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="95" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="90" align="center">
												<% int count = 0; %>
												<ezf:row ezfHyo="L">
												<tr>
													<td align="center">
														<% if (ZYPCommonFunc.hasValue(bMsg.L.no(count).eipRptRqstPk_L)) { %>
														<ezf:anchor name="invNum_L" ezfName="invNum_L" ezfHyo="L" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="printInvoice" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\""><ezf:label name="invNum_L" ezfName="invNum_L" ezfHyo="L" ezfArrayNo="0" /></ezf:anchor>
														<% } else { %>
														<ezf:label name="invNum_L" ezfName="invNum_L" ezfHyo="L" ezfArrayNo="0" />
														<% } %>
													</td>
													<td>
														<ezf:inputText name="sysSrcDescTxt_L" ezfName="sysSrcDescTxt_L" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td align="center"><ezf:anchor name="dsContrNum_L" ezfName="dsContrNum_L" ezfHyo="L" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_ContrNumL" otherAttr=" tabindex=\"-1\" ezfanchortext=\"\"">
													<ezf:label name="dsContrNum_L" ezfName="dsContrNum_L" ezfHyo="L" ezfArrayNo="0" /></ezf:anchor></td>
													<td>
														<ezf:inputText name="locNm_L1" ezfName="locNm_L1" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="locNm_L2" ezfName="locNm_L2" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="invTpDescTxt_L" ezfName="invTpDescTxt_L" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="invTotDealNetAmt_L" ezfName="invTotDealNetAmt_L" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="dealRmngBalGrsAmt_L" ezfName="dealRmngBalGrsAmt_L" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="xxApplyGrsAmt_L" ezfName="xxApplyGrsAmt_L" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="netDueDt_L" ezfName="netDueDt_L" value="MM/DD/YYYY" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"300\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="cpoOrdNum_L" ezfName="cpoOrdNum_L" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="invDt_L" ezfName="invDt_L" value="MM/DD/YYYY" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
													<td>
														<ezf:inputText name="acctDt_L" ezfName="acctDt_L" value="MM/DD/YYYY" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
													</td>
												</tr>
												<ezf:skip>
												</ezf:skip>
												<% count++; %>
												</ezf:row>
											</table>
		</div><!-- rightTbl -->
	</div> <!-- right table -->
</div> <!-- parent box -->
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
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxL", "LHEAD", "L");
</script>


<%-- **** Task specific area ends here **** --%>
