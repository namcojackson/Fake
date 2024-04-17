<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170120084536 --%>
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
			<input type="hidden" name="pageID" value="NLAL0070Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="WH Schedule">
			
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		<tr>
			<td>
			
<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<div class="pTab_BODY">
					<tr>
						<td>
						<c:choose>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_BK == 'Schedule'}">
							<table>
								<col width="60">
								<col width="162">
								<col width="5">
								<col width="70">
								<col width="101">
								<col width="10" align="center">
								<col width="106">
								<col width="68">
								<col width="111">
								<col width="5">
								<col width="50">
								<col width="118">
								<col width="30">
								<col width="160">
								<tr>
									<td class="stab"><ezf:anchor name="xxLinkAncr_P1" ezfName="xxLinkAncr_P1" ezfEmulateBtn="1" ezfGuard="OpenSubWin_Inventory_Location" >WH</ezf:anchor></td>
									<td>
										<ezf:inputText name="invtyLocCd_P1" ezfName="invtyLocCd_P1" value="123456" otherAttr=" maxlength=\"6\" size=\"6\" ezftoupper=\"\""/>
										<ezf:inputText name="invtyLocNm_P1" ezfName="invtyLocNm_P1" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"14\" maxlength=\"60\""/>
									</td>
									<td></td>
									<td class="stab">WH ETA</td>
									<td>
										<ezf:inputText name="xxOrdDt_F1" ezfName="xxOrdDt_F1" value="10/20/2008" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxOrdDt_F1', 4 );">
									</td>
									<td class="stab">-</td>
									<td>
										<ezf:inputText name="xxOrdDt_T1" ezfName="xxOrdDt_T1" value="10/22/2008" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxOrdDt_T1', 4 );">
									</td>
									<td class="stab">Schedule#(*)</td>
									<td><ezf:inputText name="rwsRefNum_P1" ezfName="rwsRefNum_P1" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
									<td></td>
									<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenSubWin_Mdse" >Item#</ezf:anchor></td>
									<td><ezf:inputText name="mdseCd_P1" ezfName="mdseCd_P1" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
									<td><ezf:inputButton name="OnClick_MdseNmSearch" value=">>" htmlClass="pBtn0" /></td>
									<td class="pOut"><ezf:label name="mdseDescShortTxt" ezfName="mdseDescShortTxt" /></td>
								</tr>
							</table>
							
							<table>
								<col width="60">
								<col width="162">
								<col width="5">
								<col width="70">
								<col width="110">
								<col width="111">
								<col width="68">
								<col width="110">
								<col width="5">
								<col width="20" align="center">
								<col width="45">
								<col width="5">
								<col width="20" align="center">
								<col width="40">
								<col width="5">
								<col width="20" align="center">
								<col width="30">
								<col width="86">
								<col width="72">
								<tr>
									<td class="stab">Sche Type</td>
									<td>
										<ezf:select name="schdTpCd_P1" ezfName="schdTpCd_P1" ezfBlank="1" ezfCodeName="schdTpCd" ezfDispName="xxScrItem61Txt" otherEvent1=" onchange=\"chgSchdTp(this.value)\"" />
									</td>
									<td></td>
									<td class="stab">Invoice#(*)</td>
									<td><ezf:inputText name="imptInvNum_P1" ezfName="imptInvNum_P1" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
									<td></td>
									<td class="stab">Carrier</td>
									<td>
										<ezf:select name="carrCd_P1" ezfName="carrCd_P1" ezfBlank="1" ezfCodeName="carrCd" ezfDispName="xxEdtCdNm" />
									</td>
									<td></td>
									<td><ezf:inputCheckBox name="asnNotRcvFlg_P1" ezfName="asnNotRcvFlg_P1" value="Y" /></td>
									<td class="stab"><label for="OrderedCheck">W Order</labe></td>
									<td></td>
									<td><ezf:inputCheckBox name="whInPrtyFlg_P1" ezfName="whInPrtyFlg_P1" value="Y" /></td>
									<td class="stab"><label for="OrderedCheck">Priority</labe></td>
									<td></td>
									<td><ezf:inputCheckBox name="imptInvLclFlg_P1" ezfName="imptInvLclFlg_P1" value="Y" /></td>
									<td class="stab"><label for="OrderedCheck">LCL</labe></td>
									<td></td>
									<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
								</tr>
							</table>
							</c:when>

							<c:when test="${pageScope._ezddatabean.xxDplyTab_BK == 'Summary'}">
							<table>
								<col width="60">
								<col width="162">
								<col width="5">
								<col width="70">
								<col width="111">
								
								<tr>
									<td class="stab"><ezf:anchor name="xxLinkAncr_P1" ezfName="xxLinkAncr_P1" ezfEmulateBtn="1" ezfGuard="OpenSubWin_Inventory_Location" >WH</ezf:anchor></td>
									<td>
										<ezf:inputText name="invtyLocCd_P1" ezfName="invtyLocCd_P1" value="123456" otherAttr=" maxlength=\"6\" size=\"6\" ezftoupper=\"\""/>
										<ezf:inputText name="invtyLocNm_P1" ezfName="invtyLocNm_P1" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"14\" maxlength=\"60\""/>
									</td>
									
									<td></td>
									<td class="stab">WH ETA</td>
									<td>
										<ezf:inputText name="xxOrdDt_F1" ezfName="xxOrdDt_F1" value="10/20/2008" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxOrdDt_F1', 4);">
									</td>
								</tr>
							</table>
							<table>
								<col width="60">
								<col width="162">
								<col width="3">
								<col width="20">
								<col width="90">
								<col width="687">
								<col width="72">
									<tr>
										<td class="stab">Sche Type</td>
										<td>
											<ezf:select name="schdTpCd_P1" ezfName="schdTpCd_P1" ezfBlank="1" ezfCodeName="schdTpCd" ezfDispName="xxScrItem61Txt" />
										</td>
										<td></td>
										<td><ezf:inputCheckBox name="asnNotRcvFlg_P1" ezfName="asnNotRcvFlg_P1" value="Y" /></td>
										<td class="stab"><label for="OrderedCheck">W Order</labe></td>
										<td></td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
							</table>
							</c:when>
						</c:choose>
						<hr>

<%-- ######################################## DETAIL ######################################## --%>
<%-- ########## Table:A --%>
								<table width="1126">
									<col valign="top">
									<tr>
										<td>
											<%-- ###TAB - HEAD --%>
											<div class="pTab_HEAD">
												<ul>
													<li id="Schedule" title="Schedule" class="pTab_OFF">
														<ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Schedule" >Schedule</ezf:anchor>
													</li>
													<li id="Summary" title="Summary" class="pTab_OFF">
														<ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Summary" >Summary</ezf:anchor>
													</li>
												</ul>
											</div>
											
											<c:choose>
											<c:when test="${pageScope._ezddatabean.xxDplyTab_BK=='Schedule'}">
											
											<script type="text/javascript">
												document.getElementById("Schedule").className = "pTab_ON";
												document.getElementById("Summary").className  = "pTab_OFF";
											</script>
											
											<%-- ###TAB - BODY --%>
											<div class="pTab_BODY_In">
												<table height="450" width="100%">
													<col valign="top">
													<tr>
														<td>
															<table border="0" cellpadding="1" cellspacing="0">
																<col width="1">
																<col width="90">
																<col width="90">
																<col width="560">
																<col width="340" align="right">
																<tr>
																	<td></td>
																	<td><ezf:inputButton name="CheckAll" value="Check All" htmlClass="pBtn8" otherAttr=" id=\"CheckAllId\""/></td>
																	<td><ezf:inputButton name="UncheckAll" value="UnCheck All" htmlClass="pBtn8" otherAttr=" id=\"UncheckAllId\""/></td>
																	<td></td>
																	<td>
																		<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																			<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																			<jsp:param name="TableNm"     value="A" />
																			<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A1" />
																			<jsp:param name="ShowingTo"   value="xxPageShowToNum_A1" />
																			<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A1" />
																		</jsp:include>
																	</td>
																</tr>
															</table>
															<table border="0" cellpadding="1" cellspacing="0">
																<col width="64%">
																<col width="36%">
																<tr valign="top">
																	<td>
																		<table border="1" cellpadding="0" cellspacing="0">
																			<tr valign="top">
																				<td>
																					<%-- START 2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																					<%-- <table border="1" cellpadding="1" cellspacing="0"> --%>
																					<table border="1" cellpadding="1" cellspacing="0" width="210">
																					<%-- END   2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																						<col width="20"  align="center">
																						<%-- START 2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																						<%-- <col width="24"  align="center"> --%>
																						<col width="50"  align="center">
																						<%-- END   2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																						<col width="118" align="center">
																						<tr height="36">
																							<td class="pClothBs">&nbsp;</td>
																							<td class="pClothBs">WH</td>
																							<td class="pClothBs">Schedule#</td>
																						</tr>
																					</table>

																					<%-- START 2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																					<%-- <div id="LeftTBL" style="overflow-y:hidden; height:338; overflow-x:hidden; width:;" onScroll="synchroScrollTop( this.id, new Array('RightTBL') )">
																						<table border="1" cellpadding="1" cellspacing="0" id="A_Table_Left"> --%>
																					<div id="LeftTBL" style="overflow-y:hidden; height:338; overflow-x:hidden;" onScroll="synchroScrollTop( this.id, new Array('RightTBL') )">
																						<table border="1" cellpadding="1" cellspacing="0" id="A_Table_Left" width="210">
																					<%-- END   2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																							<col width="20"  align="center">
																							<%-- START 2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																							<%-- <col width="24"  align="left"> --%>
																							<col width="50"  align="left">
																							<%-- END   2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																							<col width="118" align="left">

																							<tbody>
																								<ezf:row ezfHyo="A">
																									<tr height="28">
																										<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="invtyLocCd_A1" ezfName="invtyLocCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="rwsRefNum_A1" ezfName="rwsRefNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																									</tr>
																								</ezf:row>

																								<ezf:skip>
																									<tr height="28" class="pEvenNumberBGcolor">
																										<td><input type="checkbox" name="selectDtl" class="" value=""></td>
																										<td><label ezfout>37</label></td>
																										<td><label ezfout>KKFU1000001</label></td>
																									</tr>

																									<tr height="28">
																										<td><input type="checkbox" name="selectDtl" class="" value=""></td>
																										<td><label ezfout>37</label></td>
																										<td><label ezfout>KKFU1000005</label></td>
																									</tr>

																									<tr height="28" class="pEvenNumberBGcolor">
																										<td><input type="checkbox" name="selectDtl" class="" value=""></td>
																										<td><label ezfout>37</label></td>
																										<td><label ezfout>KKFU1000010</label></td>
																									</tr>

																									<tr height="28">
																										<td><input type="checkbox" name="selectDtl" class="" value=""></td>
																										<td><label ezfout>37</label></td>
																										<td><label ezfout>KKFU1000030</label></td>
																									</tr>

																									<tr height="28" class="pEvenNumberBGcolor">
																										<td><input type="checkbox" name="selectDtl" class="" value=""></td>
																										<td><label ezfout>37</label></td>
																										<td><label ezfout>KKFU1000041</label></td>
																									</tr>

																									<tr height="28">
																										<td><input type="checkbox" name="selectDtl" class="" value=""></td>
																										<td><label ezfout>37</label></td>
																										<td><label ezfout>KKFU1000030</label></td>
																									</tr>

																									<tr height="28" class="pEvenNumberBGcolor">
																										<td><input type="checkbox" name="selectDtl" class="" value=""></td>
																										<td><label ezfout>37</label></td>
																										<td><label ezfout>KKFU1000041</label></td>
																									</tr>

																								</ezf:skip>
																							</tbody>

																						</table>
																					</div>
																				</td>
																				<td>
																					<%-- START 2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																					<%-- <div id="RightTBL_Top" style="overflow-y:hidden; height:; overflow-x:hidden; width:913;" onScroll="synchroScrollLeft(this.id, new Array( 'RightTBL' )); getScrollFunction();"> --%>
																					<div id="RightTBL_Top" style="overflow-y:hidden; height:; overflow-x:hidden; width:879;" onScroll="synchroScrollLeft(this.id, new Array( 'RightTBL' )); getScrollFunction();">
																					<%-- END   2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																						<table border="1" cellpadding="1" cellspacing="0" width="3697">
																							<col width="56"  align="center">
																							<col width="24"  align="center">
																							<col width="56"  align="center">
																							<col width="220" align="center">
																							<col width="48"  align="center">
																							<col width="80"  align="center">
																							<col width="80"  align="center">
																							<col width="115" align="center">
																							<col width="115" align="center">
																							<col width="40"  align="center">
																							<col width="115" align="center">
																							<col width="40"  align="center">
																							<col width="115"  align="center">
																							<col width="68"  align="center">
																							<col width="227"  align="center">
																							<col width="112"  align="center">
																							<col width="240" align="center">
																							<col width="60"  align="center">
																							<col width="80" align="center">
																							<col width="42" align="center">
																							<col width="120" align="center">
																							<col width="240" align="center">
																							<col width="200"  align="center">
																							<col width="120"  align="center">
																							<col width="40" align="center">
																							<col width="88" align="center">
																							<col width="80" align="center">
																							<col width="80"  align="center">
																							<col width="640" align="center">
																							<tr>
																								<td class="pClothBs" rowspan="2">Delivery<br>Type</td>
																								<td class="pClothBs" rowspan="2">SM</td>
																								<td class="pClothBs" rowspan="2">Priority</td>
																								<td class="pClothBs" colspan="4">Original Visibility Information</td>
																								<td class="pClothBs" rowspan="2">TETA Memo</td>
																								<td class="pClothBs" colspan="2">Schedule (HQ)</td>
																								<td class="pClothBs" colspan="2">Schedule (Revised)</td>
																								<td class="pClothBs" rowspan="2">Rail Available<br>Date</td>
																								<td class="pClothBs" colspan="2">Appointment</td>
																								<td class="pClothBs" colspan="4">Item</td>
																								<td class="pClothBs" rowspan="2">Multi.<br>WH</td>
																								<td class="pClothBs" rowspan="2">Carrier</td>
																								<td class="pClothBs" rowspan="2">Vessel</td>
																								<td class="pClothBs" rowspan="2">B/L#</td>
																								<td class="pClothBs" rowspan="2">RWS#</td>
																								<td class="pClothBs" rowspan="2">RWS<br>Type</td>
																								<td class="pClothBs" rowspan="2">Sche<br>Type</td>
																								<td class="pClothBs" colspan="2">Visibility Information</td>
																								<td class="pClothBs" rowspan="2">Comment</td>
																							</tr>
																							
																							<tr>
																								<td class="pClothBs">Event Name</td>
																								<td class="pClothBs">TM</td>
																								<td class="pClothBs">TETA</td>
																								<td class="pClothBs">WETA</td>
																								<td class="pClothBs">WETA</td>
																								<td class="pClothBs">IB Vis</td>
																								<td class="pClothBs">WETA</td>
																								<td class="pClothBs">IB Vis</td>
																								<td class="pClothBs">Time</td>
																								<td class="pClothBs">Drayage Carrier</td>
																								<td class="pClothBs">Code</td>
																								<td class="pClothBs">Name</td>
																								<td class="pClothBs">Q&#39;ty</td>
																								<td class="pClothBs">PKG Master</td>
																								<td class="pClothBs">Scheduled</td>
																								<td class="pClothBs">Final</td>
																							</tr>
																						
																						</table>
																					</div>

																					<%-- START 2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																					<%-- <div id="RightTBL" style="overflow-y:scroll; height:355; overflow-x:scroll; width:930;" onScroll="synchroScrollTop( this.id, new Array('LeftTBL')); synchroScrollLeft(this.id, new Array('RightTBL_Top')); getScrollFunction();"> --%>
																					<div id="RightTBL" style="overflow-y:scroll; height:355; overflow-x:scroll; width:896;" onScroll="synchroScrollTop( this.id, new Array('LeftTBL')); synchroScrollLeft(this.id, new Array('RightTBL_Top')); getScrollFunction();">
																					<%-- END   2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																						<table border="1" cellpadding="1" cellspacing="0" width="3697" id="A_Table_Right">
																							<col width="56"  align="left">
																							<col width="24"  align="left">
																							<col width="56" align="center">
																							<col width="220"  align="left">
																							<col width="48"  align="left">
																							<col width="80"  align="left">
																							<col width="80"  align="left">
																							<col width="115" align="left">
																							<col width="115" align="left">
																							<col width="40"  align="center">
																							<col width="115" align="left">
																							<col width="40"  align="center">
																							<col width="115"  align="right">
																							<col width="68"  align="center">
																							<col width="227"  align="left">
																							<col width="112"  align="left">
																							<col width="240" align="left">
																							<col width="60"  align="right">
																							<col width="80" align="left">
																							<col width="42" align="left">
																							<col width="120" align="left">
																							<col width="240" align="left">
																							<col width="200"  align="left">
																							<col width="120"  align="left">
																							<col width="40" align="left">
																							<col width="88" align="left">
																							<col width="80" align="left">
																							<col width="80"  align="left">
																							<col width="640" align="left">
																							<tbody>
																								<ezf:row ezfHyo="A">
																									<tr height="28">
																										<td><ezf:label name="lgscDelyTpNm_A1" ezfName="lgscDelyTpNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="inlndShpgMethCd_A1" ezfName="inlndShpgMethCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:inputCheckBox name="whInPrtyFlg_A1" ezfName="whInPrtyFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="getScrollFunction(); sendServer('Onchange_PRIORITY_ROW', '{EZF_ROW_NUMBER}');" otherAttr=" id=\"whInPrtyFlg_A1#{EZF_ROW_NUMBER}\""/></td>
																										<td><ezf:label name="inbdVisEventNm_A1" ezfName="inbdVisEventNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="imptTrmNm_A1" ezfName="imptTrmNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="imptTrmEtaDt_A1" ezfName="imptTrmEtaDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="orgWhInEtaDt_A1" ezfName="orgWhInEtaDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td>
																											<ezf:inputText name="tempWhInEtaDt_A1" ezfName="tempWhInEtaDt_A1" value="10/10/2000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('tempWhInEtaDt_A1', 4, {EZF_ROW_NUMBER});">
																										</td>
																										<td>
																											<ezf:inputText name="schdWhInEtaDt_A1" ezfName="schdWhInEtaDt_A1" value="10/21/2008" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('schdWhInEtaDt_A1', 4, {EZF_ROW_NUMBER});">
																										</td>
																										<td><ezf:inputCheckBox name="schdEtaChkFlg_A1" ezfName="schdEtaChkFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="getScrollFunction(); sendServer('Onchange_SCHD_ROW','{EZF_ROW_NUMBER}')" otherAttr=" id=\"schdEtaChkFlg_A1#{EZF_ROW_NUMBER}\""/></td>
																										<td>
																											<ezf:inputText name="finalWhInEtaDt_A1" ezfName="finalWhInEtaDt_A1" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('finalWhInEtaDt_A1', 4, {EZF_ROW_NUMBER});">
																										</td>
																										<td><ezf:inputCheckBox name="finalEtaChkFlg_A1" ezfName="finalEtaChkFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="getScrollFunction(); sendServer('Onchange_FINAL_ROW','{EZF_ROW_NUMBER}')" otherAttr=" id=\"finalEtaChkFlg_A1#{EZF_ROW_NUMBER}\""/></td>
																										<td>
																											<ezf:inputText name="railAvalDt_A1" ezfName="railAvalDt_A1" value="10/10/2000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('railAvalDt_A1', 4, {EZF_ROW_NUMBER});">
																										</td>
																										<td><ezf:inputText name="apptTmTxt_A1" ezfName="apptTmTxt_A1" value="12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
																										<td>
																											<ezf:select name="apptDrygCarrCd_A2" ezfName="apptDrygCarrCd_A2" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="apptDrygCarrCd_A1" ezfDispName="xxEdtCdNm_A1" otherAttr=" id=\"apptDrygCarrCd_A2#{EZF_ROW_NUMBER}\""/>
																										</td>
																										<td><ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"250\" id=\"mdseDescShortTxt_A1#{EZF_ROW_NUMBER}\" style=\"border-width:0px;background-color:transparent;\" tabindex=\"-1\""/></td>
																										<td><ezf:label name="reqStkInQty_A1" ezfName="reqStkInQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="qtyPkgApvlStsCd_A1" ezfName="qtyPkgApvlStsCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										
																										<td><ezf:label name="whCd_A1" ezfName="whCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="locNm_A1" ezfName="locNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="imptInvVeslNm_A1" ezfName="imptInvVeslNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="imptInvBolNum_A1" ezfName="imptInvBolNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="rwsRefNum_A2" ezfName="rwsRefNum_A2" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="sceOrdTpCd_A1" ezfName="sceOrdTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="schdTpNm_A1" ezfName="schdTpNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																										
																										<td><ezf:label name="schdWhInEtaDt_A2" ezfName="schdWhInEtaDt_A2" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:label name="finalWhInEtaDt_A2" ezfName="finalWhInEtaDt_A2" ezfHyo="A" ezfArrayNo="0" /></td>
																										<td><ezf:inputText name="whSchdCmntTxt_A1" ezfName="whSchdCmntTxt_A1" value="1234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"90\" maxlength=\"90\""/></td>
																									</tr>
																								</ezf:row>
																								
																								<ezf:skip>
																									<tr height="28" class="pEvenNumberBGcolor">
																										<td><label ezfout name="lgscDelyTpNm_A1" ezfname="lgscDelyTpNm_A1" ezfhyo="A">DI/TL</label></td>
																										<td><label ezfout name="inlndShpgMethCd_A1" ezfname="inlndShpgMethCd_A1" ezfhyo="A">RA</label></td>
																										<td><input type="checkbox" name="whInPrtyFlg_A1" value="Y" ezfname="whInPrtyFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_PRIORITY_ROW', '{EZF_ROW_NUMBER}');" id="whInPrtyFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td><label ezfout name="inbdVisEventNm_A1" ezfname="inbdVisEventNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptTrmNm_A1" ezfname="imptTrmNm_A1" ezfhyo="A">123456</label></td>
																										<td><label ezfout name="imptTrmEtaDt_A1" ezfname="imptTrmEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td><label ezfout name="orgWhInEtaDt_A1" ezfname="orgWhInEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td>
																											<input type="text" name="tempWhInEtaDt_A1" class="" size="10" maxlength="10" value="10/10/2000" ezfname="tempWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'tempWhInEtaDt_A1', 4 );">
																										</td>
																										<td>
																											<input type="text" name="schdWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="10/21/2008" ezfname="schdWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('schdWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="schdEtaChkFlg_A1" value="Y" ezfname="schdEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_SCHD_ROW','{EZF_ROW_NUMBER}')" id="schdEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="finalWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="1234567890" ezfname="finalWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('finalWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="finalEtaChkFlg_A1" class="pPro" value="Y" ezfname="finalEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_FINAL_ROW','{EZF_ROW_NUMBER}')" id="finalEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="railAvalDt_A1" class="pPro" size="10" maxlength="10" value="10/10/2000" ezfname="railAvalDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('railAvalDt_A1', 4);">
																										</td>
																										<td><input type="text" name="apptTmTxt_A1" class="" size="8" maxlength="8" value="12345678" ezfname="apptTmTxt_A1" ezfhyo="A"></td>
																										<td>
																											<select class="pHsu" name="apptDrygCarrCd_A2" ezfname="apptDrygCarrCd_A2" ezfhyo="A" ezflist="apptDrygCarrCd_A1,xxEdtCdNm_A1,99, ,blank" id="apptDrygCarrCd_A2#{EZF_ROW_NUMBER}">
																												<option selected>12345678:123456789012345&nbsp;</option>
																												<option>0002:AG Logisti</option>
																												<option>0003:R&M Trucki</option>
																												<option>0004:Classic Tr</option>
																											</select>
																										</td>
																										<td><label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">12345678901234</label></td>
																										<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" ezfHyo="A" size="30" maxlength="250" id="mdseDescShortTxt_A1#{EZF_ROW_NUMBER}" style="border-width:0px;background-color:transparent;" readonly value="12345678901234567890" /></td>
																										<td><label ezfout name="reqStkInQty_A1" ezfname="reqStkInQty_A1" ezfhyo="A">123,456</label></td>
																										<td><label ezfout name="qtyPkgApvlStsCd_A1" ezfname="qtyPkgApvlStsCd_A1" ezfhyo="A">12</label></td>
																										
																										<td><label ezfout name="whCd_A1" ezfname="whCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="locNm_A1" ezfName="locNm_A1" ezfHyo="A" ezfArrayNo="0" />1234567890</label></td>
																										<td><label ezfout name="imptInvVeslNm_A1" ezfname="imptInvVeslNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptInvBolNum_A1" ezfname="imptInvBolNum_A1" ezfhyo="A">1234567890123456789012345</label></td>
																										<td><label ezfout name="rwsRefNum_A2" ezfname="rwsRefNum_A2" ezfhyo="A">12345678</label></td>
																										<td><label ezfout name="sceOrdTpCd_A1" ezfname="sceOrdTpCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="schdTpNm_A1" ezfname="schdTpNm_A1" ezfhyo="A">12345678901</label></td>
																										
																										<td><label ezfout name="schdWhInEtaDt_A2" ezfname="schdWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><label ezfout name="finalWhInEtaDt_A2" ezfname="finalWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><input type="text" name="whSchdCmntTxt_A1" class="" size="90" maxlength="90" value="1234567890123456789012345678901234567890" ezfname="whSchdCmntTxt_A1" ezfhyo="A"></td>
																									</tr>
																									<tr height="28">
																										<td><label ezfout name="lgscDelyTpNm_A1" ezfname="lgscDelyTpNm_A1" ezfhyo="A">DI/TL</label></td>
																										<td><label ezfout name="inlndShpgMethCd_A1" ezfname="inlndShpgMethCd_A1" ezfhyo="A">RA</label></td>
																										<td><input type="checkbox" name="whInPrtyFlg_A1" value="Y" ezfname="whInPrtyFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_PRIORITY_ROW', '{EZF_ROW_NUMBER}');" id="whInPrtyFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td><label ezfout name="inbdVisEventNm_A1" ezfname="inbdVisEventNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptTrmNm_A1" ezfname="imptTrmNm_A1" ezfhyo="A">123456</label></td>
																										<td><label ezfout name="imptTrmEtaDt_A1" ezfname="imptTrmEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td><label ezfout name="orgWhInEtaDt_A1" ezfname="orgWhInEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td>
																											<input type="text" name="tempWhInEtaDt_A1" class="" size="10" maxlength="10" value="10/10/2000" ezfname="tempWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'tempWhInEtaDt_A1', 4 );">
																										</td>
																										<td>
																											<input type="text" name="schdWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="10/21/2008" ezfname="schdWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('schdWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="schdEtaChkFlg_A1" value="Y" ezfname="schdEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_SCHD_ROW','{EZF_ROW_NUMBER}')" id="schdEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="finalWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="1234567890" ezfname="finalWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('finalWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="finalEtaChkFlg_A1" class="pPro" value="Y" ezfname="finalEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_FINAL_ROW','{EZF_ROW_NUMBER}')" id="finalEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="railAvalDt_A1" class="pPro" size="10" maxlength="10" value="10/10/2000" ezfname="railAvalDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('railAvalDt_A1', 4);">
																										</td>
																										<td><input type="text" name="apptTmTxt_A1" class="" size="8" maxlength="8" value="12345678" ezfname="apptTmTxt_A1" ezfhyo="A"></td>
																										<td>
																											<select class="pHsu" name="apptDrygCarrCd_A2" ezfname="apptDrygCarrCd_A2" ezfhyo="A" ezflist="apptDrygCarrCd_A1,xxEdtCdNm_A1,99, ,blank" id="apptDrygCarrCd_A2#{EZF_ROW_NUMBER}">
																												<option selected>12345678:123456789012345&nbsp;</option>
																												<option>0002:AG Logisti</option>
																												<option>0003:R&M Trucki</option>
																												<option>0004:Classic Tr</option>
																											</select>
																										</td>
																										<td><label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">12345678901234</label></td>
																										<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" ezfHyo="A" size="30" maxlength="250" id="mdseDescShortTxt_A1#{EZF_ROW_NUMBER}" style="border-width:0px;background-color:transparent;" readonly value="12345678901234567890" /></td>
																										<td><label ezfout name="reqStkInQty_A1" ezfname="reqStkInQty_A1" ezfhyo="A">123,456</label></td>
																										<td><label ezfout name="qtyPkgApvlStsCd_A1" ezfname="qtyPkgApvlStsCd_A1" ezfhyo="A">12</label></td>
																										
																										<td><label ezfout name="whCd_A1" ezfname="whCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="locNm_A1" ezfName="locNm_A1" ezfHyo="A" ezfArrayNo="0" />1234567890</label></td>
																										<td><label ezfout name="imptInvVeslNm_A1" ezfname="imptInvVeslNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptInvBolNum_A1" ezfname="imptInvBolNum_A1" ezfhyo="A">1234567890123456789012345</label></td>
																										<td><label ezfout name="rwsRefNum_A2" ezfname="rwsRefNum_A2" ezfhyo="A">12345678</label></td>
																										<td><label ezfout name="sceOrdTpCd_A1" ezfname="sceOrdTpCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="schdTpNm_A1" ezfname="schdTpNm_A1" ezfhyo="A">12345678901</label></td>
																										
																										<td><label ezfout name="schdWhInEtaDt_A2" ezfname="schdWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><label ezfout name="finalWhInEtaDt_A2" ezfname="finalWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><input type="text" name="whSchdCmntTxt_A1" class="" size="90" maxlength="90" value="1234567890123456789012345678901234567890" ezfname="whSchdCmntTxt_A1" ezfhyo="A"></td>
																									</tr>
																									<tr height="28" class="pEvenNumberBGcolor">
																										<td><label ezfout name="lgscDelyTpNm_A1" ezfname="lgscDelyTpNm_A1" ezfhyo="A">DI/TL</label></td>
																										<td><label ezfout name="inlndShpgMethCd_A1" ezfname="inlndShpgMethCd_A1" ezfhyo="A">RA</label></td>
																										<td><input type="checkbox" name="whInPrtyFlg_A1" value="Y" ezfname="whInPrtyFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_PRIORITY_ROW', '{EZF_ROW_NUMBER}');" id="whInPrtyFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td><label ezfout name="inbdVisEventNm_A1" ezfname="inbdVisEventNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptTrmNm_A1" ezfname="imptTrmNm_A1" ezfhyo="A">123456</label></td>
																										<td><label ezfout name="imptTrmEtaDt_A1" ezfname="imptTrmEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td><label ezfout name="orgWhInEtaDt_A1" ezfname="orgWhInEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td>
																											<input type="text" name="tempWhInEtaDt_A1" class="" size="10" maxlength="10" value="10/10/2000" ezfname="tempWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'tempWhInEtaDt_A1', 4 );">
																										</td>
																										<td>
																											<input type="text" name="schdWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="10/21/2008" ezfname="schdWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('schdWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="schdEtaChkFlg_A1" value="Y" ezfname="schdEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_SCHD_ROW','{EZF_ROW_NUMBER}')" id="schdEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="finalWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="1234567890" ezfname="finalWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('finalWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="finalEtaChkFlg_A1" class="pPro" value="Y" ezfname="finalEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_FINAL_ROW','{EZF_ROW_NUMBER}')" id="finalEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="railAvalDt_A1" class="pPro" size="10" maxlength="10" value="10/10/2000" ezfname="railAvalDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('railAvalDt_A1', 4);">
																										</td>
																										<td><input type="text" name="apptTmTxt_A1" class="" size="8" maxlength="8" value="12345678" ezfname="apptTmTxt_A1" ezfhyo="A"></td>
																										<td>
																											<select class="pHsu" name="apptDrygCarrCd_A2" ezfname="apptDrygCarrCd_A2" ezfhyo="A" ezflist="apptDrygCarrCd_A1,xxEdtCdNm_A1,99, ,blank" id="apptDrygCarrCd_A2#{EZF_ROW_NUMBER}">
																												<option selected>12345678:123456789012345&nbsp;</option>
																												<option>0002:AG Logisti</option>
																												<option>0003:R&M Trucki</option>
																												<option>0004:Classic Tr</option>
																											</select>
																										</td>
																										<td><label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">12345678901234</label></td>
																										<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" ezfHyo="A" size="30" maxlength="250" id="mdseDescShortTxt_A1#{EZF_ROW_NUMBER}" style="border-width:0px;background-color:transparent;" readonly value="12345678901234567890" /></td>
																										<td><label ezfout name="reqStkInQty_A1" ezfname="reqStkInQty_A1" ezfhyo="A">123,456</label></td>
																										<td><label ezfout name="qtyPkgApvlStsCd_A1" ezfname="qtyPkgApvlStsCd_A1" ezfhyo="A">12</label></td>
																										
																										<td><label ezfout name="whCd_A1" ezfname="whCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="locNm_A1" ezfName="locNm_A1" ezfHyo="A" ezfArrayNo="0" />1234567890</label></td>
																										<td><label ezfout name="imptInvVeslNm_A1" ezfname="imptInvVeslNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptInvBolNum_A1" ezfname="imptInvBolNum_A1" ezfhyo="A">1234567890123456789012345</label></td>
																										<td><label ezfout name="rwsRefNum_A2" ezfname="rwsRefNum_A2" ezfhyo="A">12345678</label></td>
																										<td><label ezfout name="sceOrdTpCd_A1" ezfname="sceOrdTpCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="schdTpNm_A1" ezfname="schdTpNm_A1" ezfhyo="A">12345678901</label></td>
																										
																										<td><label ezfout name="schdWhInEtaDt_A2" ezfname="schdWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><label ezfout name="finalWhInEtaDt_A2" ezfname="finalWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><input type="text" name="whSchdCmntTxt_A1" class="" size="90" maxlength="90" value="1234567890123456789012345678901234567890" ezfname="whSchdCmntTxt_A1" ezfhyo="A"></td>
																									</tr>
																									<tr height="28">
																										<td><label ezfout name="lgscDelyTpNm_A1" ezfname="lgscDelyTpNm_A1" ezfhyo="A">DI/TL</label></td>
																										<td><label ezfout name="inlndShpgMethCd_A1" ezfname="inlndShpgMethCd_A1" ezfhyo="A">RA</label></td>
																										<td><input type="checkbox" name="whInPrtyFlg_A1" value="Y" ezfname="whInPrtyFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_PRIORITY_ROW', '{EZF_ROW_NUMBER}');" id="whInPrtyFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td><label ezfout name="inbdVisEventNm_A1" ezfname="inbdVisEventNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptTrmNm_A1" ezfname="imptTrmNm_A1" ezfhyo="A">123456</label></td>
																										<td><label ezfout name="imptTrmEtaDt_A1" ezfname="imptTrmEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td><label ezfout name="orgWhInEtaDt_A1" ezfname="orgWhInEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td>
																											<input type="text" name="tempWhInEtaDt_A1" class="" size="10" maxlength="10" value="10/10/2000" ezfname="tempWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'tempWhInEtaDt_A1', 4 );">
																										</td>
																										<td>
																											<input type="text" name="schdWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="10/21/2008" ezfname="schdWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('schdWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="schdEtaChkFlg_A1" value="Y" ezfname="schdEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_SCHD_ROW','{EZF_ROW_NUMBER}')" id="schdEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="finalWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="1234567890" ezfname="finalWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('finalWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="finalEtaChkFlg_A1" class="pPro" value="Y" ezfname="finalEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_FINAL_ROW','{EZF_ROW_NUMBER}')" id="finalEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="railAvalDt_A1" class="pPro" size="10" maxlength="10" value="10/10/2000" ezfname="railAvalDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('railAvalDt_A1', 4);">
																										</td>
																										<td><input type="text" name="apptTmTxt_A1" class="" size="8" maxlength="8" value="12345678" ezfname="apptTmTxt_A1" ezfhyo="A"></td>
																										<td>
																											<select class="pHsu" name="apptDrygCarrCd_A2" ezfname="apptDrygCarrCd_A2" ezfhyo="A" ezflist="apptDrygCarrCd_A1,xxEdtCdNm_A1,99, ,blank" id="apptDrygCarrCd_A2#{EZF_ROW_NUMBER}">
																												<option selected>12345678:123456789012345&nbsp;</option>
																												<option>0002:AG Logisti</option>
																												<option>0003:R&M Trucki</option>
																												<option>0004:Classic Tr</option>
																											</select>
																										</td>
																										<td><label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">12345678901234</label></td>
																										<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" ezfHyo="A" size="30" maxlength="250" id="mdseDescShortTxt_A1#{EZF_ROW_NUMBER}" style="border-width:0px;background-color:transparent;" readonly value="12345678901234567890" /></td>
																										<td><label ezfout name="reqStkInQty_A1" ezfname="reqStkInQty_A1" ezfhyo="A">123,456</label></td>
																										<td><label ezfout name="qtyPkgApvlStsCd_A1" ezfname="qtyPkgApvlStsCd_A1" ezfhyo="A">12</label></td>
																										
																										<td><label ezfout name="whCd_A1" ezfname="whCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="locNm_A1" ezfName="locNm_A1" ezfHyo="A" ezfArrayNo="0" />1234567890</label></td>
																										<td><label ezfout name="imptInvVeslNm_A1" ezfname="imptInvVeslNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptInvBolNum_A1" ezfname="imptInvBolNum_A1" ezfhyo="A">1234567890123456789012345</label></td>
																										<td><label ezfout name="rwsRefNum_A2" ezfname="rwsRefNum_A2" ezfhyo="A">12345678</label></td>
																										<td><label ezfout name="sceOrdTpCd_A1" ezfname="sceOrdTpCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="schdTpNm_A1" ezfname="schdTpNm_A1" ezfhyo="A">12345678901</label></td>
																										
																										<td><label ezfout name="schdWhInEtaDt_A2" ezfname="schdWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><label ezfout name="finalWhInEtaDt_A2" ezfname="finalWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><input type="text" name="whSchdCmntTxt_A1" class="" size="90" maxlength="90" value="1234567890123456789012345678901234567890" ezfname="whSchdCmntTxt_A1" ezfhyo="A"></td>
																									</tr>
																									<tr height="28" class="pEvenNumberBGcolor">
																										<td><label ezfout name="lgscDelyTpNm_A1" ezfname="lgscDelyTpNm_A1" ezfhyo="A">DI/TL</label></td>
																										<td><label ezfout name="inlndShpgMethCd_A1" ezfname="inlndShpgMethCd_A1" ezfhyo="A">RA</label></td>
																										<td><input type="checkbox" name="whInPrtyFlg_A1" value="Y" ezfname="whInPrtyFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_PRIORITY_ROW', '{EZF_ROW_NUMBER}');" id="whInPrtyFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td><label ezfout name="inbdVisEventNm_A1" ezfname="inbdVisEventNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptTrmNm_A1" ezfname="imptTrmNm_A1" ezfhyo="A">123456</label></td>
																										<td><label ezfout name="imptTrmEtaDt_A1" ezfname="imptTrmEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td><label ezfout name="orgWhInEtaDt_A1" ezfname="orgWhInEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td>
																											<input type="text" name="tempWhInEtaDt_A1" class="" size="10" maxlength="10" value="10/10/2000" ezfname="tempWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'tempWhInEtaDt_A1', 4 );">
																										</td>
																										<td>
																											<input type="text" name="schdWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="10/21/2008" ezfname="schdWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('schdWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="schdEtaChkFlg_A1" value="Y" ezfname="schdEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_SCHD_ROW','{EZF_ROW_NUMBER}')" id="schdEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="finalWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="1234567890" ezfname="finalWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('finalWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="finalEtaChkFlg_A1" class="pPro" value="Y" ezfname="finalEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_FINAL_ROW','{EZF_ROW_NUMBER}')" id="finalEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="railAvalDt_A1" class="pPro" size="10" maxlength="10" value="10/10/2000" ezfname="railAvalDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('railAvalDt_A1', 4);">
																										</td>
																										<td><input type="text" name="apptTmTxt_A1" class="" size="8" maxlength="8" value="12345678" ezfname="apptTmTxt_A1" ezfhyo="A"></td>
																										<td>
																											<select class="pHsu" name="apptDrygCarrCd_A2" ezfname="apptDrygCarrCd_A2" ezfhyo="A" ezflist="apptDrygCarrCd_A1,xxEdtCdNm_A1,99, ,blank" id="apptDrygCarrCd_A2#{EZF_ROW_NUMBER}">
																												<option selected>12345678:123456789012345&nbsp;</option>
																												<option>0002:AG Logisti</option>
																												<option>0003:R&M Trucki</option>
																												<option>0004:Classic Tr</option>
																											</select>
																										</td>
																										<td><label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">12345678901234</label></td>
																										<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" ezfHyo="A" size="30" maxlength="250" id="mdseDescShortTxt_A1#{EZF_ROW_NUMBER}" style="border-width:0px;background-color:transparent;" readonly value="12345678901234567890" /></td>
																										<td><label ezfout name="reqStkInQty_A1" ezfname="reqStkInQty_A1" ezfhyo="A">123,456</label></td>
																										<td><label ezfout name="qtyPkgApvlStsCd_A1" ezfname="qtyPkgApvlStsCd_A1" ezfhyo="A">12</label></td>
																										
																										<td><label ezfout name="whCd_A1" ezfname="whCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="locNm_A1" ezfName="locNm_A1" ezfHyo="A" ezfArrayNo="0" />1234567890</label></td>
																										<td><label ezfout name="imptInvVeslNm_A1" ezfname="imptInvVeslNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptInvBolNum_A1" ezfname="imptInvBolNum_A1" ezfhyo="A">1234567890123456789012345</label></td>
																										<td><label ezfout name="rwsRefNum_A2" ezfname="rwsRefNum_A2" ezfhyo="A">12345678</label></td>
																										<td><label ezfout name="sceOrdTpCd_A1" ezfname="sceOrdTpCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="schdTpNm_A1" ezfname="schdTpNm_A1" ezfhyo="A">12345678901</label></td>
																										
																										<td><label ezfout name="schdWhInEtaDt_A2" ezfname="schdWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><label ezfout name="finalWhInEtaDt_A2" ezfname="finalWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><input type="text" name="whSchdCmntTxt_A1" class="" size="90" maxlength="90" value="1234567890123456789012345678901234567890" ezfname="whSchdCmntTxt_A1" ezfhyo="A"></td>
																									</tr>
																									<tr height="28">
																										<td><label ezfout name="lgscDelyTpNm_A1" ezfname="lgscDelyTpNm_A1" ezfhyo="A">DI/TL</label></td>
																										<td><label ezfout name="inlndShpgMethCd_A1" ezfname="inlndShpgMethCd_A1" ezfhyo="A">RA</label></td>
																										<td><input type="checkbox" name="whInPrtyFlg_A1" value="Y" ezfname="whInPrtyFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_PRIORITY_ROW', '{EZF_ROW_NUMBER}');" id="whInPrtyFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td><label ezfout name="inbdVisEventNm_A1" ezfname="inbdVisEventNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptTrmNm_A1" ezfname="imptTrmNm_A1" ezfhyo="A">123456</label></td>
																										<td><label ezfout name="imptTrmEtaDt_A1" ezfname="imptTrmEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td><label ezfout name="orgWhInEtaDt_A1" ezfname="orgWhInEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td>
																											<input type="text" name="tempWhInEtaDt_A1" class="" size="10" maxlength="10" value="10/10/2000" ezfname="tempWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'tempWhInEtaDt_A1', 4 );">
																										</td>
																										<td>
																											<input type="text" name="schdWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="10/21/2008" ezfname="schdWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('schdWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="schdEtaChkFlg_A1" value="Y" ezfname="schdEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_SCHD_ROW','{EZF_ROW_NUMBER}')" id="schdEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="finalWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="1234567890" ezfname="finalWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('finalWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="finalEtaChkFlg_A1" class="pPro" value="Y" ezfname="finalEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_FINAL_ROW','{EZF_ROW_NUMBER}')" id="finalEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="railAvalDt_A1" class="pPro" size="10" maxlength="10" value="10/10/2000" ezfname="railAvalDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('railAvalDt_A1', 4);">
																										</td>
																										<td><input type="text" name="apptTmTxt_A1" class="" size="8" maxlength="8" value="12345678" ezfname="apptTmTxt_A1" ezfhyo="A"></td>
																										<td>
																											<select class="pHsu" name="apptDrygCarrCd_A2" ezfname="apptDrygCarrCd_A2" ezfhyo="A" ezflist="apptDrygCarrCd_A1,xxEdtCdNm_A1,99, ,blank" id="apptDrygCarrCd_A2#{EZF_ROW_NUMBER}">
																												<option selected>12345678:123456789012345&nbsp;</option>
																												<option>0002:AG Logisti</option>
																												<option>0003:R&M Trucki</option>
																												<option>0004:Classic Tr</option>
																											</select>
																										</td>
																										<td><label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">12345678901234</label></td>
																										<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" ezfHyo="A" size="30" maxlength="250" id="mdseDescShortTxt_A1#{EZF_ROW_NUMBER}" style="border-width:0px;background-color:transparent;" readonly value="12345678901234567890" /></td>
																										<td><label ezfout name="reqStkInQty_A1" ezfname="reqStkInQty_A1" ezfhyo="A">123,456</label></td>
																										<td><label ezfout name="qtyPkgApvlStsCd_A1" ezfname="qtyPkgApvlStsCd_A1" ezfhyo="A">12</label></td>
																										
																										<td><label ezfout name="whCd_A1" ezfname="whCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="locNm_A1" ezfName="locNm_A1" ezfHyo="A" ezfArrayNo="0" />1234567890</label></td>
																										<td><label ezfout name="imptInvVeslNm_A1" ezfname="imptInvVeslNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptInvBolNum_A1" ezfname="imptInvBolNum_A1" ezfhyo="A">1234567890123456789012345</label></td>
																										<td><label ezfout name="rwsRefNum_A2" ezfname="rwsRefNum_A2" ezfhyo="A">12345678</label></td>
																										<td><label ezfout name="sceOrdTpCd_A1" ezfname="sceOrdTpCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="schdTpNm_A1" ezfname="schdTpNm_A1" ezfhyo="A">12345678901</label></td>
																										
																										<td><label ezfout name="schdWhInEtaDt_A2" ezfname="schdWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><label ezfout name="finalWhInEtaDt_A2" ezfname="finalWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><input type="text" name="whSchdCmntTxt_A1" class="" size="90" maxlength="90" value="1234567890123456789012345678901234567890" ezfname="whSchdCmntTxt_A1" ezfhyo="A"></td>
																									</tr>
																									<tr height="28" class="pEvenNumberBGcolor">
																										<td><label ezfout name="lgscDelyTpNm_A1" ezfname="lgscDelyTpNm_A1" ezfhyo="A">DI/TL</label></td>
																										<td><label ezfout name="inlndShpgMethCd_A1" ezfname="inlndShpgMethCd_A1" ezfhyo="A">RA</label></td>
																										<td><input type="checkbox" name="whInPrtyFlg_A1" value="Y" ezfname="whInPrtyFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_PRIORITY_ROW', '{EZF_ROW_NUMBER}');" id="whInPrtyFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td><label ezfout name="inbdVisEventNm_A1" ezfname="inbdVisEventNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptTrmNm_A1" ezfname="imptTrmNm_A1" ezfhyo="A">123456</label></td>
																										<td><label ezfout name="imptTrmEtaDt_A1" ezfname="imptTrmEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td><label ezfout name="orgWhInEtaDt_A1" ezfname="orgWhInEtaDt_A1" ezfhyo="A">10/20/2008</label></td>
																										<td>
																											<input type="text" name="tempWhInEtaDt_A1" class="" size="10" maxlength="10" value="10/10/2000" ezfname="tempWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'tempWhInEtaDt_A1', 4 );">
																										</td>
																										<td>
																											<input type="text" name="schdWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="10/21/2008" ezfname="schdWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('schdWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="schdEtaChkFlg_A1" value="Y" ezfname="schdEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_SCHD_ROW','{EZF_ROW_NUMBER}')" id="schdEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="finalWhInEtaDt_A1" class="pHsu" size="10" maxlength="10" value="1234567890" ezfname="finalWhInEtaDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('finalWhInEtaDt_A1', 4);">
																										</td>
																										<td><input type="checkbox" name="finalEtaChkFlg_A1" class="pPro" value="Y" ezfname="finalEtaChkFlg_A1" ezfhyo="A" onClick="getScrollFunction(); sendServer('Onchange_FINAL_ROW','{EZF_ROW_NUMBER}')" id="finalEtaChkFlg_A1#{EZF_ROW_NUMBER}"></td>
																										<td>
																											<input type="text" name="railAvalDt_A1" class="pPro" size="10" maxlength="10" value="10/10/2000" ezfname="railAvalDt_A1" ezfhyo="A">
																											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('railAvalDt_A1', 4);">
																										</td>
																										<td><input type="text" name="apptTmTxt_A1" class="" size="8" maxlength="8" value="12345678" ezfname="apptTmTxt_A1" ezfhyo="A"></td>
																										<td>
																											<select class="pHsu" name="apptDrygCarrCd_A2" ezfname="apptDrygCarrCd_A2" ezfhyo="A" ezflist="apptDrygCarrCd_A1,xxEdtCdNm_A1,99, ,blank" id="apptDrygCarrCd_A2#{EZF_ROW_NUMBER}">
																												<option selected>12345678:123456789012345&nbsp;</option>
																												<option>0002:AG Logisti</option>
																												<option>0003:R&M Trucki</option>
																												<option>0004:Classic Tr</option>
																											</select>
																										</td>
																										<td><label ezfout name="mdseCd_A1" ezfname="mdseCd_A1" ezfhyo="A">12345678901234</label></td>
																										<td><input type="text" name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" ezfHyo="A" size="30" maxlength="250" id="mdseDescShortTxt_A1#{EZF_ROW_NUMBER}" style="border-width:0px;background-color:transparent;" readonly value="12345678901234567890" /></td>
																										<td><label ezfout name="reqStkInQty_A1" ezfname="reqStkInQty_A1" ezfhyo="A">123,456</label></td>
																										<td><label ezfout name="qtyPkgApvlStsCd_A1" ezfname="qtyPkgApvlStsCd_A1" ezfhyo="A">12</label></td>
																										
																										<td><label ezfout name="whCd_A1" ezfname="whCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="locNm_A1" ezfName="locNm_A1" ezfHyo="A" ezfArrayNo="0" />1234567890</label></td>
																										<td><label ezfout name="imptInvVeslNm_A1" ezfname="imptInvVeslNm_A1" ezfhyo="A">123456789012345</label></td>
																										<td><label ezfout name="imptInvBolNum_A1" ezfname="imptInvBolNum_A1" ezfhyo="A">1234567890123456789012345</label></td>
																										<td><label ezfout name="rwsRefNum_A2" ezfname="rwsRefNum_A2" ezfhyo="A">12345678</label></td>
																										<td><label ezfout name="sceOrdTpCd_A1" ezfname="sceOrdTpCd_A1" ezfhyo="A">12</label></td>
																										<td><label ezfout name="schdTpNm_A1" ezfname="schdTpNm_A1" ezfhyo="A">12345678901</label></td>
																										
																										<td><label ezfout name="schdWhInEtaDt_A2" ezfname="schdWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><label ezfout name="finalWhInEtaDt_A2" ezfname="finalWhInEtaDt_A2" ezfhyo="A">10/10/2000</label></td>
																										<td><input type="text" name="whSchdCmntTxt_A1" class="" size="90" maxlength="90" value="1234567890123456789012345678901234567890" ezfname="whSchdCmntTxt_A1" ezfhyo="A"></td>
																									</tr>
																									
																								</ezf:skip>
																							</tbody>

																						</table>
																					</div>
																				</td>
																			</tr>
																		</table>
																	</td>

																</tr>
															</table>

															<table border="0" cellpadding="1" cellspacing="0">
																<col width="520">
																<col width="42">
																<col width="50">
																<col width="5">
																<col width="60">
																<col width="318">
																<col width="90">
																<tr>
																	<td></td>
																	<td class="stab">DL Type</td>
																	<td class="stab"><ezf:inputRadio name="xxRadioBtn_A1" ezfName="xxRadioBtn_A1" value="1" />ITEM</td>
																	<td />
																	<td class="stab"><ezf:inputRadio name="xxRadioBtn_A1" ezfName="xxRadioBtn_A1" value="2" />CNTNR/INV</td>
																	<td />
																	<!-- START DEL 2013/08/26 QC1560>
																	<td><input type="button" class="pBtn8" value="Inbound Inquiry" name="OpenSubWin_Inbound_Inquiry" onclick="sendServer(this)" id="Inbound_InquiryId"></td>
																	<END DEL 2013/08/26 QC1560 -->
																</tr>
															</table>
															
														</td>
													</tr>
												</table>
											</div>
											</c:when>

											<c:when test="${pageScope._ezddatabean.xxDplyTab_BK=='Summary'}">
											<script type="text/javascript">
												document.getElementById("Schedule").className = "pTab_OFF";
												document.getElementById("Summary").className  = "pTab_ON";
											</script>
											
											<%-- ###TAB - BODY --%>
											<div class="pTab_BODY_In">
												<table height="450" width="100%">
													<col valign="top">
													<tr>
														<td>
															<table border="0" cellpadding="1" cellspacing="0">
																<col width="1">
																<col width="90">
																<col width="90">
																<col width="560">
																<col width="340" align="right">
																<tr>
																	<td></td>
																	<td></td>
																	<td></td>
																	<td></td>
																	<td>
																		<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																			<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																			<jsp:param name="TableNm"     value="B" />
																			<jsp:param name="ShowingFrom" value="xxPageShowFromNum_B1" />
																			<jsp:param name="ShowingTo"   value="xxPageShowToNum_B1" />
																			<jsp:param name="ShowingOf"   value="xxPageShowOfNum_B1" />
																		</jsp:include>
																	</td>
																</tr>
															</table>
															
															<table border="0" cellpadding="1" cellspacing="0">

																<tr valign="top">
																	<td>
																		<table border="1" cellpadding="0" cellspacing="0">
																			<tr valign="top">
																				<td>
																					<%-- START 2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																					<%-- <table border="1" cellpadding="1" cellspacing="0">
																						<col width="24"  align="center"> --%>
																					<table border="1" cellpadding="1" cellspacing="0" width="140">
																						<col width="52"  align="center">
																					<%-- END   2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																						<col width="62" align="center">
																						<tr height="36">
																							<td class="pClothBs">WH</td>
																							<td class="pClothBs">Sche<br>Type</td>
																						</tr>
																					</table>
																					
																					<%-- START 2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																					<%-- <div id="LeftTBL" style="overflow-y:hidden; height:337; overflow-x:hidden; width:;" onScroll="synchroScrollTop( this.id, new Array('RightTBL') )">
																						<table border="1" cellpadding="1" cellspacing="0" id="B_Table_Left">
																							<col width="24" align="left"> --%>
																					<div id="LeftTBL" style="overflow-y:hidden; height:337; overflow-x:hidden;" onScroll="synchroScrollTop( this.id, new Array('RightTBL') )">
																						<table border="1" cellpadding="1" cellspacing="0" id="B_Table_Left" width="140">
																							<col width="52" align="left">
																					<%-- END   2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																							<col width="62" align="left">

																							<tbody>
																								<ezf:row ezfHyo="B">
																									<tr height="28">
																										<td><ezf:label name="invtyLocCd_B1" ezfName="invtyLocCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:inputText name="schdTpNm_B1" ezfName="schdTpNm_B1" value="12345678901" ezfHyo="B" ezfArrayNo="0" htmlClass="lbTxt" otherAttr=" size=\"8\" maxlength=\"30\" id=\"schdTpNm_B1#{EZF_ROW_NUMBER}\" tabindex=\"-1\""/></td>
																									</tr>
																								</ezf:row>

																								<ezf:skip>
																									<tr height="28" class="pEvenNumberBGcolor">
																										<td><label ezfout>37</label></td>
																										<td><input type="text" name="schdTpNm_B1" ezfname="schdTpNm_B1" ezfhyo="B" size="8" maxlength="30" class="lbTxt" id="schdTpNm_B1#{EZF_ROW_NUMBER}" value="KKFU1000001" tabindex="-1"/></td>
																									</tr>

																									<tr height="28">
																										<td><label ezfout>37</label></td>
																										<td><input type="text" name="schdTpNm_B1" ezfname="schdTpNm_B1" ezfhyo="B" size="8" maxlength="30" class="lbTxt" id="schdTpNm_B1#{EZF_ROW_NUMBER}" value="KKFU1000005" tabindex="-1"/></td>
																									</tr>

																									<tr height="28" class="pEvenNumberBGcolor">
																										<td><label ezfout>37</label></td>
																										<td><input type="text" name="schdTpNm_B1" ezfname="schdTpNm_B1" ezfhyo="B" size="8" maxlength="30" class="lbTxt" id="schdTpNm_B1#{EZF_ROW_NUMBER}" value="KKFU1000010" tabindex="-1"/></td>
																									</tr>
																								</ezf:skip>
																							</tbody>
																						</table>
																					</div>
																				</td>
																				<td>
																					<%-- START 2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																					<%-- <div id="RightTBL_Top" style="overflow-y:hidden; height:; overflow-x:hidden; width:1011;" onScroll="synchroScrollLeft(this.id, new Array( 'RightTBL' ))">
																						<table border="1" cellpadding="1" cellspacing="0" width="994">
																							<col width="36"  align="center">
																							<col width="44"  align="center">
																							<col width="44"  align="center">
																							<col width="36"  align="center">
																							<col width="44"  align="center">
																							<col width="44"  align="center">
																							<col width="36"  align="center">
																							<col width="44"  align="center">
																							<col width="44"  align="center">
																							<col width="36"  align="center">
																							<col width="44"  align="center">
																							<col width="44"  align="center">
																							<col width="36"  align="center">
																							<col width="44"  align="center">
																							<col width="44"  align="center">
																							<col width="36"  align="center">
																							<col width="44"  align="center">
																							<col width="44"  align="center">
																							<col width="36"  align="center">
																							<col width="44"  align="center">
																							<col width="44"  align="center"> --%>
																					<div id="RightTBL_Top" style="overflow-y:hidden; height:; overflow-x:hidden; width:966;" onScroll="synchroScrollLeft(this.id, new Array( 'RightTBL' ))">
																						<table border="1" cellpadding="1" cellspacing="0" width="949">
																							<col width="36"  align="center">
																							<col width="42"  align="center">
																							<col width="42"  align="center">
																							<col width="36"  align="center">
																							<col width="42"  align="center">
																							<col width="42"  align="center">
																							<col width="36"  align="center">
																							<col width="42"  align="center">
																							<col width="42"  align="center">
																							<col width="36"  align="center">
																							<col width="42"  align="center">
																							<col width="42"  align="center">
																							<col width="36"  align="center">
																							<col width="42"  align="center">
																							<col width="42"  align="center">
																							<col width="36"  align="center">
																							<col width="42"  align="center">
																							<col width="42"  align="center">
																							<col width="36"  align="center">
																							<col width="42"  align="center">
																							<col width="42"  align="center">
																					<%-- END   2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																							<tr>
																								<td class="pClothBs" colspan="3"><ezf:label name="xxRefTblDtTxt_B1" ezfName="xxRefTblDtTxt_B1" /></td>
																								<td class="pClothBs" colspan="3"><ezf:label name="xxRefTblDtTxt_B2" ezfName="xxRefTblDtTxt_B2" /></td>
																								<td class="pClothBs" colspan="3"><ezf:label name="xxRefTblDtTxt_B3" ezfName="xxRefTblDtTxt_B3" /></td>
																								<td class="pClothBs" colspan="3"><ezf:label name="xxRefTblDtTxt_B4" ezfName="xxRefTblDtTxt_B4" /></td>
																								<td class="pClothBs" colspan="3"><ezf:label name="xxRefTblDtTxt_B5" ezfName="xxRefTblDtTxt_B5" /></td>
																								<td class="pClothBs" colspan="3"><ezf:label name="xxRefTblDtTxt_B6" ezfName="xxRefTblDtTxt_B6" /></td>
																								<td class="pClothBs" colspan="3"><ezf:label name="xxRefTblDtTxt_B7" ezfName="xxRefTblDtTxt_B7" /></td>
																							</tr>
																							
																							<tr>
																								<td class="pClothBs">Num</td>
																								<td class="pClothBs">Pallet</td>
																								<td class="pClothBs">Case</td>
																								<td class="pClothBs">Num</td>
																								<td class="pClothBs">Pallet</td>
																								<td class="pClothBs">Case</td>
																								<td class="pClothBs">Num</td>
																								<td class="pClothBs">Pallet</td>
																								<td class="pClothBs">Case</td>
																								<td class="pClothBs">Num</td>
																								<td class="pClothBs">Pallet</td>
																								<td class="pClothBs">Case</td>
																								<td class="pClothBs">Num</td>
																								<td class="pClothBs">Pallet</td>
																								<td class="pClothBs">Case</td>
																								<td class="pClothBs">Num</td>
																								<td class="pClothBs">Pallet</td>
																								<td class="pClothBs">Case</td>
																								<td class="pClothBs">Num</td>
																								<td class="pClothBs">Pallet</td>
																								<td class="pClothBs">Case</td>
																							</tr>
																						
																						</table>
																					</div>

																					<%-- START 2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																					<%-- <div id="RightTBL" style="overflow-y:scroll; height:337; overflow-x:hidden; width:1011;" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' )); synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ) );">
																						<table border="1" cellpadding="1" cellspacing="0" width="994" id="B_Table_Right">
																							<col width="36"  align="right">
																							<col width="44"  align="right">
																							<col width="44"  align="right">
																							<col width="36"  align="right">
																							<col width="44"  align="right">
																							<col width="44"  align="right">
																							<col width="36"  align="right">
																							<col width="44"  align="right">
																							<col width="44"  align="right">
																							<col width="36"  align="right">
																							<col width="44"  align="right">
																							<col width="44"  align="right">
																							<col width="36"  align="right">
																							<col width="44"  align="right">
																							<col width="44"  align="right">
																							<col width="36"  align="right">
																							<col width="44"  align="right">
																							<col width="44"  align="right">
																							<col width="36"  align="right">
																							<col width="44"  align="right">
																							<col width="44"  align="right"> --%>
																					<div id="RightTBL" style="overflow-y:scroll; height:337; overflow-x:hidden; width:966;" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' )); synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ) );">
																						<table border="1" cellpadding="1" cellspacing="0" width="949" id="B_Table_Right">
																							<col width="36"  align="right">
																							<col width="42"  align="right">
																							<col width="42"  align="right">
																							<col width="36"  align="right">
																							<col width="42"  align="right">
																							<col width="42"  align="right">
																							<col width="36"  align="right">
																							<col width="42"  align="right">
																							<col width="42"  align="right">
																							<col width="36"  align="right">
																							<col width="42"  align="right">
																							<col width="42"  align="right">
																							<col width="36"  align="right">
																							<col width="42"  align="right">
																							<col width="42"  align="right">
																							<col width="36"  align="right">
																							<col width="42"  align="right">
																							<col width="42"  align="right">
																							<col width="36"  align="right">
																							<col width="42"  align="right">
																							<col width="42"  align="right">
																					<%-- END   2013/08/21 A.Shinohara [Defect#1306 Mod] --%>
																							<tbody>
																								<ezf:row ezfHyo="B">
																									<tr height="28">
																										<td><ezf:label name="rwsQty_B1" ezfName="rwsQty_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="pltQty_B1" ezfName="pltQty_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="cseQty_B1" ezfName="cseQty_B1" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="rwsQty_B2" ezfName="rwsQty_B2" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="pltQty_B2" ezfName="pltQty_B2" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="cseQty_B2" ezfName="cseQty_B2" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="rwsQty_B3" ezfName="rwsQty_B3" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="pltQty_B3" ezfName="pltQty_B3" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="cseQty_B3" ezfName="cseQty_B3" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="rwsQty_B4" ezfName="rwsQty_B4" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="pltQty_B4" ezfName="pltQty_B4" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="cseQty_B4" ezfName="cseQty_B4" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="rwsQty_B5" ezfName="rwsQty_B5" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="pltQty_B5" ezfName="pltQty_B5" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="cseQty_B5" ezfName="cseQty_B5" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="rwsQty_B6" ezfName="rwsQty_B6" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="pltQty_B6" ezfName="pltQty_B6" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="cseQty_B6" ezfName="cseQty_B6" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="rwsQty_B7" ezfName="rwsQty_B7" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="pltQty_B7" ezfName="pltQty_B7" ezfHyo="B" ezfArrayNo="0" /></td>
																										<td><ezf:label name="cseQty_B7" ezfName="cseQty_B7" ezfHyo="B" ezfArrayNo="0" /></td>
																									</tr>
																								</ezf:row>
																								
																								<ezf:skip>
																									<tr height="28" class="pEvenNumberBGcolor">
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																									</tr>
																									
																									<tr height="28">
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																									</tr>
																									
																									<tr height="28" class="pEvenNumberBGcolor">
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>999</label></td>
																										<td><label ezfout>99999</label></td>
																										<td><label ezfout>99999</label></td>
																								</ezf:skip>

																							</tbody>
																						</table>
																					</div>
																				</td>
																			</tr>
																		</table>
																	</td>

																</tr>
															</table>

															<table border="0" cellpadding="1" cellspacing="0">
																<col width="1010">
																<col width="1">
																<col width="90">
																<col width="90">
																<tr>
																	<td></td>
																	<td><ezf:inputButton name="PrevWeek" value="Pre Week" htmlClass="pBtn8" /></td>
																	<td><ezf:inputButton name="NextWeek" value="Next Week" htmlClass="pBtn8" /></td>
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

							</td>
						</tr>
					</table>
				</div>
<%-- ######################################## FOOTER ######################################## --%>

			</td>
		</tr>
	</table>
</center>

<ezf:inputHidden name="xxListNum_LX" ezfName="xxListNum_LX" value="0" otherAttr=" id=\"xxListNum_LX\""/>
<ezf:inputHidden name="xxListNum_LY" ezfName="xxListNum_LY" value="0" otherAttr=" id=\"xxListNum_LY\""/>

<style TYPE="text/css">
<!--
	INPUT.lbTxt{border-style:none; background-color:transparent;}
-->
</style>

<%@ page import="static com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_TP.AIR" %>
<%@ page import="business.servlet.NLAL0070.NLAL0070BMsg" %>
<% NLAL0070BMsg bMsg = (NLAL0070BMsg) databean.getEZDBMsg(); %>

<script type="text/javascript">
<% if (bMsg.A.getValidCount() > 0) { %>

	// ISF List tab only.
	if (document.getElementById('Schedule').className.indexOf('ON') >= 0) {
		setScrollFunction();
	}

	function setScrollFunction() {
		var CheckAll              = document.getElementById('CheckAllId');
		var UncheckAll            = document.getElementById('UncheckAllId');
		var Inbound_Inquiry       = document.getElementById('Inbound_InquiryId');
		var xxListNumLX           = document.getElementById('xxListNum_LX');
		var xxListNumLY           = document.getElementById('xxListNum_LY');
		var rightTBL              = document.getElementById('RightTBL');
		rightTBL.scrollLeft  = parseInt(xxListNumLX.value);
		rightTBL.scrollTop   = parseInt(xxListNumLY.value);
		
		CheckAll.setAttribute("onclick", new Function("getScrollFunction();sendServer(this);"));
		UncheckAll.setAttribute("onclick", new Function("getScrollFunction();sendServer(this);"));
		Inbound_Inquiry.setAttribute("onclick", new Function("getScrollFunction();sendServer(this);"));
	}

	function getScrollFunction() {
		var rightTBL   = document.getElementById('RightTBL');
		var xxListNumLX = document.getElementById('xxListNum_LX');
		var xxListNumLY = document.getElementById('xxListNum_LY');
		xxListNumLX.value = rightTBL.scrollLeft;
		xxListNumLY.value = rightTBL.scrollTop;
	}

<% } else { %>

	// ISF List tab only.
	if (document.getElementById('Schedule').className.indexOf('ON') >= 0) {
		setScrollINITFunction();
	}
	
	function setScrollINITFunction(){
		var xxListNumLX = document.getElementById('xxListNum_LX');
		var xxListNumLY = document.getElementById('xxListNum_LY');
		xxListNumLX.value = parseInt(0);
		xxListNumLY.value = parseInt(0);
	}

<% } %>

	chgSchdTp(this.document.getElementsByName('schdTpCd_P1')[0].value);

	function synchroScroll_fromRightTblAction(){
		var rightTopTBL = this.document.getElementById('RightTBL_Top');
		var rightTBL    = this.document.getElementById('RightTBL');
		var leftTBL     = this.document.getElementById('LeftTBL');
		
		// sychronaize scroll -X
		rightTopTBL.scrollLeft = rightTBL.scrollLeft;
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function syncronizeScroll_fromLeftTblAction(){
		var leftTBL  = this.document.getElementById('LeftTBL');
		var rightTBL = this.document.getElementById('RightTBL');
		
		// synchronize scroll - Y
		rightTBL.scrollTop = leftTBL.scrollTop;
	}

	function chgSchdTp(selVal) {

		var lclChkBox = this.document.getElementsByName('imptInvLclFlg_P1')[0];
		var disabledClsNm = 'pPro';

		if (lclChkBox == null) {
			return;
		}

		if (selVal == <%= AIR %>) {
			lclChkBox.checked = true;
			
			var lclChkBoxClsNm = lclChkBox.className;
			if (lclChkBoxClsNm.indexOf(disabledClsNm) < 0) {
				lclChkBox.className = lclChkBoxClsNm + disabledClsNm;
			}
			
			lclChkBox.onclick = new Function('return false;');
			
		} else {
			var lclChkBoxClsNm = lclChkBox.className;
			if (lclChkBoxClsNm.indexOf(disabledClsNm) >= 0) {
				lclChkBox.className = lclChkBoxClsNm.replace(disabledClsNm, '');
			}
			lclChkBox.onclick = null;
		}
	}
</script>

<%-- **** Task specific area ends here **** --%>
