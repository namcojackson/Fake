<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160929104846 --%>
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
			<input type="hidden" name="pageID" value="NLCL1010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Serial Transaction Inquiry">


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

<%-- ######################################## HEADER ######################################## --%>
 
				<%-- ###TAB - HEAD --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
 
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table height="576">
						<col valign="top">
						<tr>
						
							<td>
								<table width="100%" cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td>
											<table border="0">
												<!-- Serial# -->
												<col width="48">
												<col width="">
												<col width="">
												<col width="8">

												<!-- Merhcandise Code -->
												<col width="">
												<col width="">
												<col width="">
												<col width="8">

												<!-- Serial Event -->
												<col width="">
												<col width="">
												<col width="">
												<col width="8">

												<!-- Error Status -->
												<col width="">
												<col width="">
												<col width="">
												<col width="">

												<tr>
													<!-- Serial# -->
													<td class="stab">Serial#</td>
													<td></td>
													<td class="stab"><ezf:inputText name="serNum_C0" ezfName="serNum_C0" value="123456789012345678901234567890" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
													<td></td>

													<!-- Merhcandise Code -->
													<td class="stab"><ezf:anchor name="mdseCd_CL" ezfName="mdseCd_CL" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchMdseCd" otherAttr=" id=\"mdseCd_CL\" ezfanchortext=\"\"">Item Num</ezf:anchor></td>
													<td></td>
													<td class="stab"><ezf:inputText name="mdseCd_C0" ezfName="mdseCd_C0" value="1234567890123456" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
													<td></td>

													<!-- Serial Event -->
													<td class="stab">Event</td>
													<td></td>
													<td class="stab">
														<ezf:select name="serTrxEventCd_P0" ezfName="serTrxEventCd_P0" ezfBlank="1" ezfCodeName="serTrxEventCd_C0" ezfDispName="serTrxEventDescTxt_C0" />
													</td>
													<td></td>

													<!-- Error Status -->
													<td class="stab">Error Status</td>
													<td></td>
													<td class="stab">
														<ezf:select name="serErrStsCd_P0" ezfName="serErrStsCd_P0" ezfBlank="1" ezfCodeName="serErrStsCd_C0" ezfDispName="serErrStsDescTxt_C0" />
													</td>
													<td></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table border="0">
												<!-- Trx Term From -->
												<col width="48">
												<col width="">
												<col width="">
												<col width="">
												<col width="10" align="center">

												<!-- Trx Term To -->
												<col width="">
												<col width="">
												<col width="16">

												<!-- Loc From -->
												<col width="">
												<col width="">
												<col width="">
												<col width="">

												<!-- To -->
												<col width="">
												<col width="">
												<col width="">
												<col width="20">

												<!-- Latest Only -->
												<col width="20">
												<col width="">
												<col width="">

												<!-- Error Only -->
												<col width="20">
												<col width="">
												<col width="">


												<tr>
													<!-- Trx Term From -->
													<td class="stab">Trx Term</td>
													<td></td>
													<td class="stab"><ezf:inputText name="xxFromDt_C0" ezfName="xxFromDt_C0" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
													<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxFromDt_C0', 4 );"></td>
													<td>-</td>

													<!-- Trx Term To -->
													<td class="stab"><ezf:inputText name="xxThruDt_C0" ezfName="xxThruDt_C0" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
													<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxThruDt_C0', 4 );"></td>
													<td></td>

													<!-- Loc From -->
													<td class="stab"><ezf:anchor name="fromLocCd_CL" ezfName="fromLocCd_CL" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchLocFromCd" otherAttr=" id=\"fromLocCd_CL\" ezfanchortext=\"\"">Loc From</ezf:anchor></td>
													<td></td>
													<td class="stab"><ezf:inputText name="fromLocCd_C0" ezfName="fromLocCd_C0" value="123456" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
													<td></td>

													<!-- To -->
													<td class="stab"><ezf:anchor name="toLocCd_CL" ezfName="toLocCd_CL" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchLocToCd" otherAttr=" id=\"toLocCd_CL\" ezfanchortext=\"\"">To</ezf:anchor></td>
													<td></td>
													<td class="stab"><ezf:inputText name="toLocCd_C0" ezfName="toLocCd_C0" value="123456" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
													<td></td>

													<!-- Latest Only -->
													<td class="stab"><ezf:inputCheckBox name="serTrxLtstFlg_C0" ezfName="serTrxLtstFlg_C0" value="Y" /></td>
													<td class="stab">Latest Only</td>
													<td></td>

													<!-- Error Only -->
													<td class="stab"><ezf:inputCheckBox name="xxRsltFlg_C0" ezfName="xxRsltFlg_C0" value="Y" /></td>
													<td class="stab">Error Only</td>
													<td></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table border="0">
												<!-- Serial Tranasction Source Type -->
												<col width="48">
												<col width="">
												<col width="">
												<col width="8">

												<!-- Serial Transaction Source Header# -->
												<col width="">
												<col width="">
												<col width="">
												<col width="8">

												<!-- Serial Transaction Reference# -->
												<col width="">
												<col width="">
												<col width="">
												<col width="8">

												<!-- Original Merchandise Code -->
												<col width="">
												<col width="">
												<col width="">
												<col width="220">

												<!-- Search Button -->
												<col width="">

												<tr>
													<!-- Serial Tranasction Source Type -->
													<td class="stab">Trx SRC</td>
													<td></td>
													<td class="stab">
														<ezf:select name="serTrxSrcTpCd_P0" ezfName="serTrxSrcTpCd_P0" ezfBlank="1" ezfCodeName="serTrxSrcTpCd_C0" ezfDispName="serTrxSrcTpDescTxt_C0" />
													</td>
													<td></td>

													<!-- Serial Transaction Source Header# -->
													<td class="stab">Trx SRC Header#</td>
													<td></td>
													<td class="stab"><ezf:inputText name="serTrxSrcHdrNum_C0" ezfName="serTrxSrcHdrNum_C0" value="1234567890" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td><!--08/12/2013 MOD DEFECT#1627 -->
													<td></td>

													<!-- Serial Transaction Reference# -->
													<td class="stab">Serial Trx Ref#</td>
													<td></td>
													<td class="stab"><ezf:inputText name="serTrxRefNum_C0" ezfName="serTrxRefNum_C0" value="123456789012345" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
													<td></td>

													<!-- Original Merchandise Code -->
													<td class="stab"><ezf:anchor name="origMdseCd_CL" ezfName="origMdseCd_CL" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchOrigMdseCd" otherAttr=" id=\"origMdseCd_CL\" ezfanchortext=\"\"">Orig Item</ezf:anchor></td>
													<td></td>
													<td class="stab"><ezf:inputText name="origMdseCd_C0" ezfName="origMdseCd_C0" value="1234567890123456" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
													<td></td>

													<!-- Search Button -->
													<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
  
								<table width="1124">
									<col valign="top">
									<tr>
										<td>
				 							<fieldset>
				 								<legend>Add Serial Trx</legend>
												<table width="100%" cellpadding="0" cellspacing="0" border="0">
													<col valign="top">
													<tr>
														<td>
															<!-- 1st line of Add Serial Trx -->
															<table border="0">
																<!-- Serial# -->
																<col width="48">
																<col width="">
																<col width="">
																<col width="8">

																<!-- Merhcandise Code -->
																<col width="">
																<col width="">
																<col width="">
																<col width="8">

																<!-- Serial Event -->
																<col width="">
																<col width="">
																<col width="">
																<col width="">

																<!-- Loc From -->
																<col width="">
																<col width="">
																<col width="">
																<col width="">

																<!-- Loc To -->
																<col width="">
																<col width="">
																<col width="">
																<col width="">

																<!-- From Stock Status --> 
																<col width="">
																<col width="">
																<col width="">
																<col width="">

																<!-- Stock Status --> 
																<col width="">
																<col width="">
																<col width="">
																<col width="">

																<tr>
																	<!-- Serial# -->
																	<td class="stab">Serial#</td>
																	<td></td>
																	<td class="stab"><ezf:inputText name="serNum_H0" ezfName="serNum_H0" value="123456789012345678901234567890" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
																	<td></td>

																	<!-- Merhcandise Code -->
																	<td class="stab"><ezf:anchor name="mdseCd_HL" ezfName="mdseCd_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_AddMdseCd" otherAttr=" id=\"mdseCd_HL\" ezfanchortext=\"\"">Item Num</ezf:anchor></td>
																	<td></td>
																	<td class="stab"><ezf:inputText name="mdseCd_H0" ezfName="mdseCd_H0" value="1234567890123456" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
																	<td></td>

																	<!-- Serial Event -->
																	<td class="stab">Event</td>
																	<td></td>
																	<td class="stab">
																		<ezf:select name="serTrxEventCd_P1" ezfName="serTrxEventCd_P1" ezfBlank="1" ezfCodeName="serTrxEventCd_H0" ezfDispName="serTrxEventDescTxt_H0" otherEvent1=" onchange=\"sendServer('Select_AddSerEvt')\"" otherAttr=" style=\"width:110px\""/>
																	</td>
																	<td></td>

																	<!-- Loc From -->
																	<td class="stab"><ezf:anchor name="fromLocCd_HL" ezfName="fromLocCd_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_AddLocFromCd" otherAttr=" id=\"fromLocCd_HL\" ezfanchortext=\"\"">Loc From</ezf:anchor></td>
																	<td></td>
																	<td class="stab"><ezf:inputText name="fromLocCd_H0" ezfName="fromLocCd_H0" value="123456" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
																	<td></td>

																	<!-- Loc To -->
																	<td class="stab"><ezf:anchor name="toLocCd_HL" ezfName="toLocCd_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_AddLocToCd" otherAttr=" id=\"toLocCd_HL\" ezfanchortext=\"\"">To</ezf:anchor></td>
																	<td></td>
																	<td class="stab"><ezf:inputText name="toLocCd_H0" ezfName="toLocCd_H0" value="123456" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
																	<td></td>

																	<!-- From Stock Status -->
																	<td class="stab">From SS</td>
																	<td></td>
																	<td class="stab">
																		<ezf:select name="stkStsCd_F1" ezfName="stkStsCd_F1" ezfBlank="1" ezfCodeName="stkStsCd_F0" ezfDispName="stkStsDescTxt_F0" otherAttr=" style=\"width:100px\""/>
																	</td>
																	<td></td>

																	<!-- Stock Status -->
																	<td class="stab">SS</td>
																	<td></td>
																	<td class="stab">
																		<ezf:select name="stkStsCd_T1" ezfName="stkStsCd_T1" ezfBlank="1" ezfCodeName="stkStsCd_T0" ezfDispName="stkStsDescTxt_T0" otherAttr=" style=\"width:100px\""/>
																	</td>
																	<td></td>

																</tr>
															</table>
														</td>
													</tr>
												</table>

												<table width="100%" cellpadding="0" cellspacing="0" border="0">
													<col valign="top">
													<tr>
														<td>
															<!-- 2nd and 3rd line of Add Serial Trx -->
															<table border="0">
																<!-- Serial Tranasction Source Type -->
																<col width="48">
																<col width="">
																<col width="">
																<col width="8">

																<!-- Serial Transaction Source Header# -->
																<col width="">
																<col width="">
																<col width="">
																<col width="8">

																<!-- Serial Transaction Source Line# -->
																<col width="">
																<col width="">
																<col width="">
																<col width="8">

																<!-- Serial Transaction Source Line Sub# -->
																<col width="">
																<col width="">
																<col width="">
																<col width="28">

																<!-- comment -->
																<col width="">

																<tr>
																	<!-- Serial Tranasction Source Type -->
																	<td class="stab">Trx SRC</td>
																	<td></td>
																	<td class="stab">
																		<ezf:select name="serTrxSrcTpCd_P1" ezfName="serTrxSrcTpCd_P1" ezfBlank="1" ezfCodeName="serTrxSrcTpCd_H0" ezfDispName="serTrxSrcTpDescTxt_H0" />
																	</td>
																	<td></td>

																	<!-- Serial Transaction Source Header# -->
																	<td class="stab">Trx SRC Header#</td>
																	<td></td>
																	<td class="stab"><ezf:inputText name="serTrxSrcHdrNum_H0" ezfName="serTrxSrcHdrNum_H0" value="1234567890" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td><!--08/12/2013 MOD DEFECT#1627 -->
																	<td></td>

																	<!-- Serial Transaction Source Line# -->
																	<td class="stab">Line</td>
																	<td></td>
																	<td class="stab"><ezf:inputText name="serTrxSrcLineNum_H0" ezfName="serTrxSrcLineNum_H0" value="123" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
																	<td></td>

																	<!-- Serial Transaction Source Line Sub# -->
																	<td class="stab">Line Sub</td>
																	<td></td>
																	<td class="stab"><ezf:inputText name="serTrxSrcLineSubNum_H0" ezfName="serTrxSrcLineSubNum_H0" value="123" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
																	<td></td>

																	<td>Comment</td>

																</tr>
															</table>

															<table border="0">

																<!-- Serial Transaction Reference# -->
																<col width="">
																<col width="">
																<col width="">
																<col width="8">

																<!-- Old Merchandise Code -->
																<col width="">
																<col width="">
																<col width="">
																<col width="8">

																<!-- Transaction Date Time -->
																<col width="">
																<col width="">
																<col width="">
																<col width="">
																<col width="8">

																<tr>
																	<!-- Serial Transaction Reference# -->
																	<td class="stab">Serial Trx Ref#</td>
																	<td></td>
																	<td class="stab"><ezf:inputText name="serTrxRefNum_H0" ezfName="serTrxRefNum_H0" value="123456789012345" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
																	<td></td>

																	<!-- Old Merchandise Code -->
																	<td class="stab"><ezf:anchor name="oldMdseCd_HL" ezfName="oldMdseCd_HL" ezfEmulateBtn="1" ezfGuard="OpenWin_AddOldMdseCd" otherAttr=" id=\"oldMdseCd_HL\" ezfanchortext=\"\"">Old Item Num</ezf:anchor></td>
																	<td></td>
																	<td class="stab"><ezf:inputText name="oldMdseCd_H0" ezfName="oldMdseCd_H0" value="1234567890123456" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
																	<td></td>

																        <!-- Transaction Date Time -->
																	<td class="stab">Trx Date Time</td>
																	<td></td>
																	<td class="stab"><ezf:inputText name="xxDt10Dt_H0" ezfName="xxDt10Dt_H0" value="10/14/2015" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																	<td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxDt10Dt_H0', 4);"></td>
																	<td class="stab"><ezf:inputText name="xxSvcFromHourMnTxt_H0" ezfName="xxSvcFromHourMnTxt_H0" value="15:22" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
																</tr>
															</table>
														</td>

														<!-- Comment -->
														<td rowspan="2" style="vertical-align: top;">
															<table cellpadding="0" cellspacing="0">
																<col width="">
																<col width="8">
																<col width="">
																<tr>
																	<td><ezf:textArea name="serTrxCmntTxt_H0" ezfName="serTrxCmntTxt_H0" otherAttr=" cols=\"50\" maxlength=\"1000\" rows=\"4\""/></td>
																	<!-- Add Button -->
																	<td></td>
																</tr>

															</table>
														</td>
														
														<!-- Add -->
														<td rowspan="2" style="vertical-align: bottom;">
															<table cellpadding="0" cellspacing="0">
																<tr>
																	<td valign="bottom"><ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" /></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</fieldset>

											<table height="388" width="100%" cellpadding="0" cellspacing="0" border="0">
												<tr valign="bottom" height="36">
													<td>
														<table border="0">
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
														<ezf:skip>
															<table>
																<col width="780">
																<col width="">
																<tr>
																	<td>
																	</td>
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
																		</jsp:include>
																	</td>
																</tr>
															</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

															<tr>
																<td valign="top">
<!-- ################################################## Search Result   - [START] ################################################## -->

																	<table border="1" cellpadding="0" cellspacing="0">
																		<tr style="vertical-align=top;">
																			<td>
																				<!-- Left TBL Header -->
																				<table border="1" cellpadding="1" cellspacing="0">
																					<col width="24" align="center">
																					<col width="215" align="center">
																					<col width="120" align="center">

																					<tr height="37">
																						<td class="pClothBs">D</td>
																						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A1')">Serial#</a><img id="sortIMG.serNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A1')">Item Number</a><img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
																					</tr>
																				</table>

																				<div id="leftTBL" style="overflow-y:hidden; height:282; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));">
																					<table border="1" cellpadding="1" cellspacing="0" width="" id="A1">
																						<col width="24" align="center"><!-- Check -->
																						<col width="215" align="left"><!-- Serial# -->
																						<col width="120" align="left"><!-- Item Num -->

																						<tbody>
																							<ezf:row ezfHyo="A">
																								<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Delete Check Button -->
																									<td>
																										<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/>
																									</td>
																									<!-- Serial# -->
																									<td>
																										<ezf:label name="serNum_A1" ezfName="serNum_A1" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																									<!-- Merchandise Code -->
																									<td>
																										<ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																								</tr>
																							</ezf:row>
																							<ezf:skip>
																								<tr class="pEvenNumberBGcolor" height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Delete Check Button -->
																									<td>
																										<input type="checkbox" value="Y" name="xxChkBox_R" ezfname="xxChkBox_R" ezfhyo="A">
																									</td>
																									<!-- Serial# -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Merchandise Code -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">1234567890123456</label>
																									</td>
																								</tr>
																								<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Delete Check Button -->
																									<td>
																										<input type="checkbox" value="Y" name="xxChkBox_R" ezfname="xxChkBox_R" ezfhyo="A">
																									</td>
																									<!-- Serial# -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Merchandise Code -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">1234567890123456</label>
																									</td>
																								</tr>
																								<tr class="pEvenNumberBGcolor" height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Delete Check Button -->
																									<td>
																										<input type="checkbox" value="Y" name="xxChkBox_R" ezfname="xxChkBox_R" ezfhyo="A">
																									</td>
																									<!-- Serial# -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Merchandise Code -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">1234567890123456</label>
																									</td>
																								</tr>
																								<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Delete Check Button -->
																									<td>
																										<input type="checkbox" value="Y" name="xxChkBox_R" ezfname="xxChkBox_R" ezfhyo="A">
																									</td>
																									<!-- Serial# -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Merchandise Code -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">1234567890123456</label>
																									</td>
																								</tr>
																								<tr class="pEvenNumberBGcolor" height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Delete Check Button -->
																									<td>
																										<input type="checkbox" value="Y" name="xxChkBox_R" ezfname="xxChkBox_R" ezfhyo="A">
																									</td>
																									<!-- Serial# -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Merchandise Code -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">1234567890123456</label>
																									</td>
																								</tr>
																								<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Delete Check Button -->
																									<td>
																										<input type="checkbox" value="Y" name="xxChkBox_R" ezfname="xxChkBox_R" ezfhyo="A">
																									</td>
																									<!-- Serial# -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Merchandise Code -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">1234567890123456</label>
																									</td>
																								</tr>
																								<tr class="pEvenNumberBGcolor" height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Delete Check Button -->
																									<td>
																										<input type="checkbox" value="Y" name="xxChkBox_R" ezfname="xxChkBox_R" ezfhyo="A">
																									</td>
																									<!-- Serial# -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Merchandise Code -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">1234567890123456</label>
																									</td>
																								</tr>
																								<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Delete Check Button -->
																									<td>
																										<input type="checkbox" value="Y" name="xxChkBox_R" ezfname="xxChkBox_R" ezfhyo="A">
																									</td>
																									<!-- Serial# -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Merchandise Code -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">1234567890123456</label>
																									</td>
																								</tr>
																								<tr class="pEvenNumberBGcolor" height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Delete Check Button -->
																									<td>
																										<input type="checkbox" value="Y" name="xxChkBox_R" ezfname="xxChkBox_R" ezfhyo="A">
																									</td>
																									<!-- Serial# -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Merchandise Code -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">1234567890123456</label>
																									</td>
																								</tr>
																								<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Delete Check Button -->
																									<td>
																										<input type="checkbox" value="Y" name="xxChkBox_R" ezfname="xxChkBox_R" ezfhyo="A">
																									</td>
																									<!-- Serial# -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Merchandise Code -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">1234567890123456</label>
																									</td>
																								</tr>
																								<tr class="pEvenNumberBGcolor" height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Delete Check Button -->
																									<td>
																										<input type="checkbox" value="Y" name="xxChkBox_R" ezfname="xxChkBox_R" ezfhyo="A">
																									</td>
																									<!-- Serial# -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Merchandise Code -->
																									<td>
																										<label ezfout name="xxxx" ezfname="xxxx" ezfhyo="A">1234567890123456</label>
																									</td>
																								</tr>
																							</ezf:skip>
																						</tbody>
																					</table>
																				</div>
																			</td>
																			
																			<td>
																				<!-- Right TBL Header -->
																				<div id="rightTopTBL" style="overflow-y:none; overflow-x:hidden; width=722" onScroll="synchroScrollLeft(this.id, new Array('rightTBL'));">
																					<table border="1" cellpadding="1" cellspacing="0" width="2465">

																						<col width="210" align="center"><!-- Item Name -->
																						<col width="180" align="center"><!-- Event -->
																						<col width="130" align="center"><!-- Trx Date Time -->
																						<col width="40" align="center"><!-- MAN -->
																						<col width="210" align="center"><!-- Location (From) -->
																						<col width="210" align="center"><!-- Location (To) -->
																						<col width="140" align="center"><!-- Trx Source -->
																						<col width="69" align="center"><!-- Trx SRC Header# --><!--08/12/2013 MOD DEFECT#1627 -->
																						<col width="48" align="center"><!-- Line -->
																						<col width="64" align="center"><!-- Line Sub -->
																						<col width="112" align="center"><!-- Serial Trx Ref# -->
																						<col width="215" align="center"><!-- From SS -->
																						<col width="215" align="center"><!-- SS -->
																						<col width="120" align="center"><!-- Original Item -->
																						<col width="64" align="center"><!-- Comment -->
																						<col width="314" align="center"><!-- Error Status -->

																						<tr height="37">
																							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseDescShortTxt_A1')">Item Name</a><img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serTrxEventDescTxt_A1')">Event</a><img id="sortIMG.serTrxEventDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serTrxTs_A1')">Trx Date Time</a><img id="sortIMG.serTrxTs_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'manCratFlg_A1')">MAN</a><img id="sortIMG.manCratFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxDplyByItemCnctLbNm_A1')">Location (From)</a><img id="sortIMG.xxDplyByItemCnctLbNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxDplyByItemCnctLbNm_A2')">Location (To)</a><img id="sortIMG.xxDplyByItemCnctLbNm_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serTrxSrcTpDescTxt_A1')">Trx Source</a><img id="sortIMG.serTrxSrcTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serTrxSrcHdrNum_A1')">Trx SRC Header#</a><img id="sortIMG.serTrxSrcHdrNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																							<td class="pClothBs">Line</td>
																							<td class="pClothBs">Line Sub</td>
																							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serTrxRefNum_A1')">Serial Trx Ref#</a><img id="sortIMG.serTrxRefNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																						        <!-- From SS --><td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'stkStsDescTxt_A1')">From Stock Status</a><img id="sortIMG.stkStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																						        <!-- SS --><td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'stkStsDescTxt_A2')">Stock Status</a><img id="sortIMG.stkStsDescTxt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'origMdseCd_A1')">Original Item</a><img id="sortIMG.origMdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																							<td class="pClothBs">Comment</td>
																							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxDplyByItemCnctLbNm_A3')">Error Status</a><img id="sortIMG.xxDplyByItemCnctLbNm_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																						</tr>
																					</table>
																				</div>

																				<div id="rightTBL" style="overflow:scroll; height:299; width:739" onScroll="synchroScrollTop(this.id, new Array('leftTBL')); onScroll=synchroScrollLeft(this.id, new Array('rightTopTBL'));">
																					<!-- Right TBL Main -->
																					<table border="1" cellpadding="1" cellspacing="0" width="2465" id="A2">
																						<col width="210" align="center"><!-- Item Name -->
																						<col width="180" align="left"><!-- Event -->
																						<col width="130" align="center"><!-- Trx Date Time-->
																						<col width="40" align="center"><!-- MAN -->
																						<col width="210" align="left"><!-- Location (From) -->
																						<col width="210" align="left"><!-- Location (To) -->
																						<col width="140" align="left"><!-- Trx Source -->
																						<col width="69" align="left"><!-- Trx SRC Header# --><!--08/12/2013 MOD DEFECT#1627 -->
																						<col width="48" align="left"><!-- Line -->
																						<col width="64" align="left"><!-- Line Sub -->
																						<col width="112" align="left"><!-- Serial Trx Ref# -->
																						<col width="215" align="left"><!-- From SS -->
																						<col width="215" align="left"><!-- SS -->
																						<col width="120" align="left"><!-- Original Item -->
																						<col width="64" align="center"><!-- Comment -->
																						<col width="314" align="left"><!-- Error Status -->

																						<tbody>
																							<ezf:row ezfHyo="A">
																								<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Merchandise Name -->
																									<td>
																										<!-- <label ezfout name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A">123456789012345678901234567890</label> -->
																										<ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"29\" maxlength=\"50\" id=\"mdseDescShortTxt_A1\""/>
																									</td>
																									<!-- Serial Event -->
																									<td>
																										<ezf:label name="serTrxEventDescTxt_A1" ezfName="serTrxEventDescTxt_A1" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																									<!-- Transaction Date Time -->
																									<td>
																										<ezf:label name="xxDtNm_A1" ezfName="xxDtNm_A1" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																									<!-- Manual Creation -->
																									<td>
																										<ezf:label name="manCratFlg_A1" ezfName="manCratFlg_A1" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																									<!-- Location (From) -->
																									<td>
																										<ezf:inputText name="xxScrItem500Txt_A1" ezfName="xxScrItem500Txt_A1" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"29\" maxlength=\"500\" id=\"xxScrItem500Txt_A1\""/>
																									</td>
																									<!-- Location (To) -->
																									<td>
																										<ezf:inputText name="xxScrItem500Txt_A2" ezfName="xxScrItem500Txt_A2" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"29\" maxlength=\"500\" id=\"xxScrItem500Txt_A2\""/>
																									</td>
																									<!-- Serial Tranasction Source Type -->
																									<td>
																										<ezf:label name="serTrxSrcTpDescTxt_A1" ezfName="serTrxSrcTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																									<!-- Serial Transaction Source Header# -->
																									<td>
																										<ezf:label name="serTrxSrcHdrNum_A1" ezfName="serTrxSrcHdrNum_A1" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																									<!-- Serial Transaction Source Liner# -->
																									<td>
																										<ezf:label name="serTrxSrcLineNum_A1" ezfName="serTrxSrcLineNum_A1" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																									<!-- Serial Transaction Source Line Sub# -->
																									<td>
																										<ezf:label name="serTrxSrcLineSubNum_A1" ezfName="serTrxSrcLineSubNum_A1" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																									<!-- Serial Transaction Reference# -->
																									<td>
																										<ezf:label name="serTrxRefNum_A1" ezfName="serTrxRefNum_A1" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																									<!-- From SS-->
																									<td>
																										<ezf:label name="stkStsDescTxt_A1" ezfName="stkStsDescTxt_A1" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																									<!-- SS-->
																									<td>
																										<ezf:label name="stkStsDescTxt_A2" ezfName="stkStsDescTxt_A2" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																									<!-- Original Merchandise Code -->
																									<td>
																										<ezf:label name="origMdseCd_A1" ezfName="origMdseCd_A1" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																									<!-- Comment -->
																									<td>
																										<ezf:anchor name="xxLinkAncr_A1" ezfName="xxLinkAncr_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_DtlCmt" otherAttr=" id=\"xxLinkAncr_A1#{EZF_ROW_NUMBER}\""><ezf:label name="xxYesNoCd_A1" ezfName="xxYesNoCd_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
																									</td>
																									<!-- Error Status -->
																									<td>
																										<ezf:label name="xxDplyByItemCnctLbNm_A3" ezfName="xxDplyByItemCnctLbNm_A3" ezfHyo="A" ezfArrayNo="0" />
																									</td>
																								</tr>
																							</ezf:row>
																							<ezf:skip>
																								<tr class="pEvenNumberBGcolor" height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Merchandise Name -->
																									<td>
																										<label ezfout name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Serial Event -->
																									<td>
																										<label ezfout name="serTrxEventDescTxt_A1" ezfname="serTrxEventDescTxt_A1" ezfhyo="A">1234567890123456789012345</label>
																									</td>
																									<!-- Transaction Date Time -->
																									<td>
																										<label ezfout name="xxDtNm_A1" ezfname="xxDtNm_A1" ezfhyo="A">10/14/2015 11:11</label>
																									</td>
																									<!-- Manual Creation -->
																									<td>
																										<label ezfout name="manCratFlg_A1" ezfname="manCratFlg_A1" ezfhyo="A">Y</label>
																									</td>
																									<!-- Location (From) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A1" ezfname="xxDplyByCdNmCnctTxt_A1" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Location (To) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A2" ezfname="xxDplyByCdNmCnctTxt_A2" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Serial Tranasction Source Type -->
																									<td>
																										<label ezfout name="serTrxSrcTpDescTxt_A1" ezfname="serTrxSrcTpDescTxt_A1" ezfhyo="A">12345678901234567890</label>
																									</td>
																									<!-- Serial Transaction Source Header# -->
																									<td>
																										<label ezfout name="serTrxSrcHdrNum_A1" ezfname="serTrxSrcHdrNum_A1" ezfhyo="A">1234567890</label>
																									</td>
																									<!-- Serial Transaction Source Liner# -->
																									<td>
																										<label ezfout name="serTrxSrcLineNum_A1" ezfname="serTrxSrcLineNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Source Line Sub# -->
																									<td>
																										<label ezfout name="serTrxSrcLineSubNum_A1" ezfname="serTrxSrcLineSubNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Reference# -->
																									<td>
																										<label ezfout name="serTrxRefNum_A1" ezfname="serTrxRefNum_A1" ezfhyo="A">123456789012345</label>
																									</td>
																									<!-- From SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A1" ezfname="stkStsDescTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A2" ezfname="stkStsDescTxt_A2" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Original Merchandise Code -->
																									<td>
																										<label ezfout name="origMdseCd_A1" ezfname="origMdseCd_A1" ezfhyo="A">1234567890123456</label>
																									</td>
																									<!-- Comment -->
																									<td>
																										<a href="#" onclick="sendServer('OpenWin_DtlCmt')"><label ezfout name="xxYesNoCd_A1" ezfname="xxYesNoCd_A1" ezfhyo="A">Y</label></a>
																									</td>
																									<!-- Error Status -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A3" ezfname="xxDplyByCdNmCnctTxt_A3" ezfhyo="A">123:1234567890123456789012345678901234567890</label>
																									</td>
																								</tr>
																								<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Merchandise Name -->
																									<td>
																										<label ezfout name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Serial Event -->
																									<td>
																										<label ezfout name="serTrxEventDescTxt_A1" ezfname="serTrxEventDescTxt_A1" ezfhyo="A">1234567890123456789012345</label>
																									</td>
																									<!-- Transaction Date Time -->
																									<td>
																										<label ezfout name="xxDtNm_A1" ezfname="xxDtNm_A1" ezfhyo="A">mm/dd/yyyy HH:MM</label>
																									</td>
																									<!-- Manual Creation -->
																									<td>
																										<label ezfout name="manCratFlg_A1" ezfname="manCratFlg_A1" ezfhyo="A">Y</label>
																									</td>
																									<!-- Location (From) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A1" ezfname="xxDplyByCdNmCnctTxt_A1" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Location (To) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A2" ezfname="xxDplyByCdNmCnctTxt_A2" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Serial Tranasction Source Type -->
																									<td>
																										<label ezfout name="serTrxSrcTpDescTxt_A1" ezfname="serTrxSrcTpDescTxt_A1" ezfhyo="A">12345678901234567890</label>
																									</td>
																									<!-- Serial Transaction Source Header# -->
																									<td>
																										<label ezfout name="serTrxSrcHdrNum_A1" ezfname="serTrxSrcHdrNum_A1" ezfhyo="A">1234567890</label>
																									</td>
																									<!-- Serial Transaction Source Liner# -->
																									<td>
																										<label ezfout name="serTrxSrcLineNum_A1" ezfname="serTrxSrcLineNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Source Line Sub# -->
																									<td>
																										<label ezfout name="serTrxSrcLineSubNum_A1" ezfname="serTrxSrcLineSubNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Reference# -->
																									<td>
																										<label ezfout name="serTrxRefNum_A1" ezfname="serTrxRefNum_A1" ezfhyo="A">123456789012345</label>
																									</td>
																									<!-- From SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A1" ezfname="stkStsDescTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A2" ezfname="stkStsDescTxt_A2" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Original Merchandise Code -->
																									<td>
																										<label ezfout name="origMdseCd_A1" ezfname="origMdseCd_A1" ezfhyo="A">1234567890123456</label>
																									</td>
																									<!-- Comment -->
																									<td>
																										<a href="#" onclick="sendServer('OpenWin_DtlCmt')"><label ezfout name="xxYesNoCd_A1" ezfname="xxYesNoCd_A1" ezfhyo="A">Y</label></a>
																									</td>
																									<!-- Error Status -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A3" ezfname="xxDplyByCdNmCnctTxt_A3" ezfhyo="A">123:1234567890123456789012345678901234567890</label>
																									</td>
																								</tr>
																								<tr class="pEvenNumberBGcolor" height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Merchandise Name -->
																									<td>
																										<label ezfout name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Serial Event -->
																									<td>
																										<label ezfout name="serTrxEventDescTxt_A1" ezfname="serTrxEventDescTxt_A1" ezfhyo="A">1234567890123456789012345</label>
																									</td>
																									<!-- Transaction Date Time -->
																									<td>
																										<label ezfout name="xxDtNm_A1" ezfname="xxDtNm_A1" ezfhyo="A">mm/dd/yyyy HH:MM</label>
																									</td>
																									<!-- Manual Creation -->
																									<td>
																										<label ezfout name="manCratFlg_A1" ezfname="manCratFlg_A1" ezfhyo="A">Y</label>
																									</td>
																									<!-- Location (From) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A1" ezfname="xxDplyByCdNmCnctTxt_A1" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Location (To) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A2" ezfname="xxDplyByCdNmCnctTxt_A2" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Serial Tranasction Source Type -->
																									<td>
																										<label ezfout name="serTrxSrcTpDescTxt_A1" ezfname="serTrxSrcTpDescTxt_A1" ezfhyo="A">12345678901234567890</label>
																									</td>
																									<!-- Serial Transaction Source Header# -->
																									<td>
																										<label ezfout name="serTrxSrcHdrNum_A1" ezfname="serTrxSrcHdrNum_A1" ezfhyo="A">1234567890</label>
																									</td>
																									<!-- Serial Transaction Source Liner# -->
																									<td>
																										<label ezfout name="serTrxSrcLineNum_A1" ezfname="serTrxSrcLineNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Source Line Sub# -->
																									<td>
																										<label ezfout name="serTrxSrcLineSubNum_A1" ezfname="serTrxSrcLineSubNum_A1" ezfhyo="A">123</label>
																									</td>

																									<!-- Serial Transaction Reference# -->
																									<td>
																										<label ezfout name="serTrxRefNum_A1" ezfname="serTrxRefNum_A1" ezfhyo="A">123456789012345</label>
																									</td>
																									<!-- From SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A1" ezfname="stkStsDescTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A2" ezfname="stkStsDescTxt_A2" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Original Merchandise Code -->
																									<td>
																										<label ezfout name="origMdseCd_A1" ezfname="origMdseCd_A1" ezfhyo="A">1234567890123456</label>
																									</td>
																									<!-- Comment -->
																									<td>
																										<a href="#" onclick="sendServer('OpenWin_DtlCmt')"><label ezfout name="xxYesNoCd_A1" ezfname="xxYesNoCd_A1" ezfhyo="A">Y</label></a>
																									</td>
																									<!-- Error Status -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A3" ezfname="xxDplyByCdNmCnctTxt_A3" ezfhyo="A">123:1234567890123456789012345678901234567890</label>
																									</td>
																								</tr>
																								<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Merchandise Name -->
																									<td>
																										<label ezfout name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Serial Event -->
																									<td>
																										<label ezfout name="serTrxEventDescTxt_A1" ezfname="serTrxEventDescTxt_A1" ezfhyo="A">1234567890123456789012345</label>
																									</td>
																									<!-- Transaction Date Time -->
																									<td>
																										<label ezfout name="xxDtNm_A1" ezfname="xxDtNm_A1" ezfhyo="A">mm/dd/yyyy HH:MM</label>
																									</td>
																									<!-- Manual Creation -->
																									<td>
																										<label ezfout name="manCratFlg_A1" ezfname="manCratFlg_A1" ezfhyo="A">Y</label>
																									</td>
																									<!-- Location (From) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A1" ezfname="xxDplyByCdNmCnctTxt_A1" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Location (To) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A2" ezfname="xxDplyByCdNmCnctTxt_A2" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Serial Tranasction Source Type -->
																									<td>
																										<label ezfout name="serTrxSrcTpDescTxt_A1" ezfname="serTrxSrcTpDescTxt_A1" ezfhyo="A">12345678901234567890</label>
																									</td>
																									<!-- Serial Transaction Source Header# -->
																									<td>
																										<label ezfout name="serTrxSrcHdrNum_A1" ezfname="serTrxSrcHdrNum_A1" ezfhyo="A">1234567890</label>
																									</td>
																									<!-- Serial Transaction Source Liner# -->
																									<td>
																										<label ezfout name="serTrxSrcLineNum_A1" ezfname="serTrxSrcLineNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Source Line Sub# -->
																									<td>
																										<label ezfout name="serTrxSrcLineSubNum_A1" ezfname="serTrxSrcLineSubNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Reference# -->
																									<td>
																										<label ezfout name="serTrxRefNum_A1" ezfname="serTrxRefNum_A1" ezfhyo="A">123456789012345</label>
																									</td>
																									<!-- From SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A1" ezfname="stkStsDescTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A2" ezfname="stkStsDescTxt_A2" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Original Merchandise Code -->
																									<td>
																										<label ezfout name="origMdseCd_A1" ezfname="origMdseCd_A1" ezfhyo="A">1234567890123456</label>
																									</td>
																									<!-- Comment -->
																									<td>
																										<a href="#" onclick="sendServer('OpenWin_DtlCmt')"><label ezfout name="xxYesNoCd_A1" ezfname="xxYesNoCd_A1" ezfhyo="A">Y</label></a>
																									</td>
																									<!-- Error Status -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A3" ezfname="xxDplyByCdNmCnctTxt_A3" ezfhyo="A">123:1234567890123456789012345678901234567890</label>
																									</td>
																								</tr>
																								<tr class="pEvenNumberBGcolor" height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Merchandise Name -->
																									<td>
																										<label ezfout name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Serial Event -->
																									<td>
																										<label ezfout name="serTrxEventDescTxt_A1" ezfname="serTrxEventDescTxt_A1" ezfhyo="A">1234567890123456789012345</label>
																									</td>
																									<!-- Transaction Date Time -->
																									<td>
																										<label ezfout name="xxDtNm_A1" ezfname="xxDtNm_A1" ezfhyo="A">mm/dd/yyyy HH:MM</label>
																									</td>
																									<!-- Manual Creation -->
																									<td>
																										<label ezfout name="manCratFlg_A1" ezfname="manCratFlg_A1" ezfhyo="A">Y</label>
																									</td>
																									<!-- Location (From) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A1" ezfname="xxDplyByCdNmCnctTxt_A1" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Location (To) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A2" ezfname="xxDplyByCdNmCnctTxt_A2" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Serial Tranasction Source Type -->
																									<td>
																										<label ezfout name="serTrxSrcTpDescTxt_A1" ezfname="serTrxSrcTpDescTxt_A1" ezfhyo="A">12345678901234567890</label>
																									</td>
																									<!-- Serial Transaction Source Header# -->
																									<td>
																										<label ezfout name="serTrxSrcHdrNum_A1" ezfname="serTrxSrcHdrNum_A1" ezfhyo="A">1234567890</label>
																									</td>
																									<!-- Serial Transaction Source Liner# -->
																									<td>
																										<label ezfout name="serTrxSrcLineNum_A1" ezfname="serTrxSrcLineNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Source Line Sub# -->
																									<td>
																										<label ezfout name="serTrxSrcLineSubNum_A1" ezfname="serTrxSrcLineSubNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Reference# -->
																									<td>
																										<label ezfout name="serTrxRefNum_A1" ezfname="serTrxRefNum_A1" ezfhyo="A">123456789012345</label>
																									</td>
																									<!-- From SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A1" ezfname="stkStsDescTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A2" ezfname="stkStsDescTxt_A2" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Original Merchandise Code -->
																									<td>
																										<label ezfout name="origMdseCd_A1" ezfname="origMdseCd_A1" ezfhyo="A">1234567890123456</label>
																									</td>
																									<!-- Comment -->
																									<td>
																										<a href="#" onclick="sendServer('OpenWin_DtlCmt')"><label ezfout name="xxYesNoCd_A1" ezfname="xxYesNoCd_A1" ezfhyo="A">Y</label></a>
																									</td>
																									<!-- Error Status -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A3" ezfname="xxDplyByCdNmCnctTxt_A3" ezfhyo="A">123:1234567890123456789012345678901234567890</label>
																									</td>
																								</tr>
																								<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Merchandise Name -->
																									<td>
																										<label ezfout name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Serial Event -->
																									<td>
																										<label ezfout name="serTrxEventDescTxt_A1" ezfname="serTrxEventDescTxt_A1" ezfhyo="A">1234567890123456789012345</label>
																									</td>
																									<!-- Transaction Date Time -->
																									<td>
																										<label ezfout name="xxDtNm_A1" ezfname="xxDtNm_A1" ezfhyo="A">mm/dd/yyyy HH:MM</label>
																									</td>
																									<!-- Manual Creation -->
																									<td>
																										<label ezfout name="manCratFlg_A1" ezfname="manCratFlg_A1" ezfhyo="A">Y</label>
																									</td>
																									<!-- Location (From) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A1" ezfname="xxDplyByCdNmCnctTxt_A1" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Location (To) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A2" ezfname="xxDplyByCdNmCnctTxt_A2" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Serial Tranasction Source Type -->
																									<td>
																										<label ezfout name="serTrxSrcTpDescTxt_A1" ezfname="serTrxSrcTpDescTxt_A1" ezfhyo="A">12345678901234567890</label>
																									</td>
																									<!-- Serial Transaction Source Header# -->
																									<td>
																										<label ezfout name="serTrxSrcHdrNum_A1" ezfname="serTrxSrcHdrNum_A1" ezfhyo="A">1234567890</label>
																									</td>
																									<!-- Serial Transaction Source Liner# -->
																									<td>
																										<label ezfout name="serTrxSrcLineNum_A1" ezfname="serTrxSrcLineNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Source Line Sub# -->
																									<td>
																										<label ezfout name="serTrxSrcLineSubNum_A1" ezfname="serTrxSrcLineSubNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Reference# -->
																									<td>
																										<label ezfout name="serTrxRefNum_A1" ezfname="serTrxRefNum_A1" ezfhyo="A">123456789012345</label>
																									</td>
																									<!-- From SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A1" ezfname="stkStsDescTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A2" ezfname="stkStsDescTxt_A2" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Original Merchandise Code -->
																									<td>
																										<label ezfout name="origMdseCd_A1" ezfname="origMdseCd_A1" ezfhyo="A">1234567890123456</label>
																									</td>
																									<!-- Comment -->
																									<td>
																										<a href="#" onclick="sendServer('OpenWin_DtlCmt')"><label ezfout name="xxYesNoCd_A1" ezfname="xxYesNoCd_A1" ezfhyo="A">Y</label></a>
																									</td>
																									<!-- Error Status -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A3" ezfname="xxDplyByCdNmCnctTxt_A3" ezfhyo="A">123:1234567890123456789012345678901234567890</label>
																									</td>
																								</tr>
																								<tr class="pEvenNumberBGcolor" height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Merchandise Name -->
																									<td>
																										<label ezfout name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Serial Event -->
																									<td>
																										<label ezfout name="serTrxEventDescTxt_A1" ezfname="serTrxEventDescTxt_A1" ezfhyo="A">1234567890123456789012345</label>
																									</td>
																									<!-- Transaction Date Time -->
																									<td>
																										<label ezfout name="xxDtNm_A1" ezfname="xxDtNm_A1" ezfhyo="A">mm/dd/yyyy HH:MM</label>
																									</td>
																									<!-- Manual Creation -->
																									<td>
																										<label ezfout name="manCratFlg_A1" ezfname="manCratFlg_A1" ezfhyo="A">Y</label>
																									</td>
																									<!-- Location (From) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A1" ezfname="xxDplyByCdNmCnctTxt_A1" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Location (To) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A2" ezfname="xxDplyByCdNmCnctTxt_A2" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Serial Tranasction Source Type -->
																									<td>
																										<label ezfout name="serTrxSrcTpDescTxt_A1" ezfname="serTrxSrcTpDescTxt_A1" ezfhyo="A">12345678901234567890</label>
																									</td>
																									<!-- Serial Transaction Source Header# -->
																									<td>
																										<label ezfout name="serTrxSrcHdrNum_A1" ezfname="serTrxSrcHdrNum_A1" ezfhyo="A">1234567890</label>
																									</td>
																									<!-- Serial Transaction Source Liner# -->
																									<td>
																										<label ezfout name="serTrxSrcLineNum_A1" ezfname="serTrxSrcLineNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Source Line Sub# -->
																									<td>
																										<label ezfout name="serTrxSrcLineSubNum_A1" ezfname="serTrxSrcLineSubNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Reference# -->
																									<td>
																										<label ezfout name="serTrxRefNum_A1" ezfname="serTrxRefNum_A1" ezfhyo="A">123456789012345</label>
																									</td>
																									<!-- From SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A1" ezfname="stkStsDescTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A2" ezfname="stkStsDescTxt_A2" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Original Merchandise Code -->
																									<td>
																										<label ezfout name="origMdseCd_A1" ezfname="origMdseCd_A1" ezfhyo="A">1234567890123456</label>
																									</td>
																									<!-- Comment -->
																									<td>
																										<a href="#" onclick="sendServer('OpenWin_DtlCmt')"><label ezfout name="xxYesNoCd_A1" ezfname="xxYesNoCd_A1" ezfhyo="A">Y</label></a>
																									</td>
																									<!-- Error Status -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A3" ezfname="xxDplyByCdNmCnctTxt_A3" ezfhyo="A">123:1234567890123456789012345678901234567890</label>
																									</td>
																								</tr>
																								<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Merchandise Name -->
																									<td>
																										<label ezfout name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Serial Event -->
																									<td>
																										<label ezfout name="serTrxEventDescTxt_A1" ezfname="serTrxEventDescTxt_A1" ezfhyo="A">1234567890123456789012345</label>
																									</td>
																									<!-- Transaction Date Time -->
																									<td>
																										<label ezfout name="xxDtNm_A1" ezfname="xxDtNm_A1" ezfhyo="A">mm/dd/yyyy HH:MM</label>
																									</td>
																									<!-- Manual Creation -->
																									<td>
																										<label ezfout name="manCratFlg_A1" ezfname="manCratFlg_A1" ezfhyo="A">Y</label>
																									</td>
																									<!-- Location (From) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A1" ezfname="xxDplyByCdNmCnctTxt_A1" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Location (To) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A2" ezfname="xxDplyByCdNmCnctTxt_A2" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Serial Tranasction Source Type -->
																									<td>
																										<label ezfout name="serTrxSrcTpDescTxt_A1" ezfname="serTrxSrcTpDescTxt_A1" ezfhyo="A">12345678901234567890</label>
																									</td>
																									<!-- Serial Transaction Source Header# -->
																									<td>
																										<label ezfout name="serTrxSrcHdrNum_A1" ezfname="serTrxSrcHdrNum_A1" ezfhyo="A">1234567890</label>
																									</td>
																									<!-- Serial Transaction Source Liner# -->
																									<td>
																										<label ezfout name="serTrxSrcLineNum_A1" ezfname="serTrxSrcLineNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Source Line Sub# -->
																									<td>
																										<label ezfout name="serTrxSrcLineSubNum_A1" ezfname="serTrxSrcLineSubNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Reference# -->
																									<td>
																										<label ezfout name="serTrxRefNum_A1" ezfname="serTrxRefNum_A1" ezfhyo="A">123456789012345</label>
																									</td>
																									<!-- From SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A1" ezfname="stkStsDescTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A2" ezfname="stkStsDescTxt_A2" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Original Merchandise Code -->
																									<td>
																										<label ezfout name="origMdseCd_A1" ezfname="origMdseCd_A1" ezfhyo="A">1234567890123456</label>
																									</td>
																									<!-- Comment -->
																									<td>
																										<a href="#" onclick="sendServer('OpenWin_DtlCmt')"><label ezfout name="xxYesNoCd_A1" ezfname="xxYesNoCd_A1" ezfhyo="A">Y</label></a>
																									</td>
																									<!-- Error Status -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A3" ezfname="xxDplyByCdNmCnctTxt_A3" ezfhyo="A">123:1234567890123456789012345678901234567890</label>
																									</td>
																								</tr>
																								<tr class="pEvenNumberBGcolor" height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Merchandise Name -->
																									<td>
																										<label ezfout name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Serial Event -->
																									<td>
																										<label ezfout name="serTrxEventDescTxt_A1" ezfname="serTrxEventDescTxt_A1" ezfhyo="A">1234567890123456789012345</label>
																									</td>
																									<!-- Transaction Date Time -->
																									<td>
																										<label ezfout name="xxDtNm_A1" ezfname="xxDtNm_A1" ezfhyo="A">mm/dd/yyyy HH:MM</label>
																									</td>
																									<!-- Manual Creation -->
																									<td>
																										<label ezfout name="manCratFlg_A1" ezfname="manCratFlg_A1" ezfhyo="A">Y</label>
																									</td>
																									<!-- Location (From) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A1" ezfname="xxDplyByCdNmCnctTxt_A1" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Location (To) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A2" ezfname="xxDplyByCdNmCnctTxt_A2" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Serial Tranasction Source Type -->
																									<td>
																										<label ezfout name="serTrxSrcTpDescTxt_A1" ezfname="serTrxSrcTpDescTxt_A1" ezfhyo="A">12345678901234567890</label>
																									</td>
																									<!-- Serial Transaction Source Header# -->
																									<td>
																										<label ezfout name="serTrxSrcHdrNum_A1" ezfname="serTrxSrcHdrNum_A1" ezfhyo="A">1234567890</label>
																									</td>
																									<!-- Serial Transaction Source Liner# -->
																									<td>
																										<label ezfout name="serTrxSrcLineNum_A1" ezfname="serTrxSrcLineNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Source Line Sub# -->
																									<td>
																										<label ezfout name="serTrxSrcLineSubNum_A1" ezfname="serTrxSrcLineSubNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Reference# -->
																									<td>
																										<label ezfout name="serTrxRefNum_A1" ezfname="serTrxRefNum_A1" ezfhyo="A">123456789012345</label>
																									</td>
																									<!-- From SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A1" ezfname="stkStsDescTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A2" ezfname="stkStsDescTxt_A2" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Original Merchandise Code -->
																									<td>
																										<label ezfout name="origMdseCd_A1" ezfname="origMdseCd_A1" ezfhyo="A">1234567890123456</label>
																									</td>
																									<!-- Comment -->
																									<td>
																										<a href="#" onclick="sendServer('OpenWin_DtlCmt')"><label ezfout name="xxYesNoCd_A1" ezfname="xxYesNoCd_A1" ezfhyo="A">Y</label></a>
																									</td>
																									<!-- Error Status -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A3" ezfname="xxDplyByCdNmCnctTxt_A3" ezfhyo="A">123:1234567890123456789012345678901234567890</label>
																									</td>
																								</tr>
																								<tr height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Merchandise Name -->
																									<td>
																										<label ezfout name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Serial Event -->
																									<td>
																										<label ezfout name="serTrxEventDescTxt_A1" ezfname="serTrxEventDescTxt_A1" ezfhyo="A">1234567890123456789012345</label>
																									</td>
																									<!-- Transaction Date Time -->
																									<td>
																										<label ezfout name="xxDtNm_A1" ezfname="xxDtNm_A1" ezfhyo="A">mm/dd/yyyy HH:MM</label>
																									</td>
																									<!-- Manual Creation -->
																									<td>
																										<label ezfout name="manCratFlg_A1" ezfname="manCratFlg_A1" ezfhyo="A">Y</label>
																									</td>
																									<!-- Location (From) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A1" ezfname="xxDplyByCdNmCnctTxt_A1" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Location (To) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A2" ezfname="xxDplyByCdNmCnctTxt_A2" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Serial Tranasction Source Type -->
																									<td>
																										<label ezfout name="serTrxSrcTpDescTxt_A1" ezfname="serTrxSrcTpDescTxt_A1" ezfhyo="A">12345678901234567890</label>
																									</td>
																									<!-- Serial Transaction Source Header# -->
																									<td>
																										<label ezfout name="serTrxSrcHdrNum_A1" ezfname="serTrxSrcHdrNum_A1" ezfhyo="A">1234567890</label>
																									</td>
																									<!-- Serial Transaction Source Liner# -->
																									<td>
																										<label ezfout name="serTrxSrcLineNum_A1" ezfname="serTrxSrcLineNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Source Line Sub# -->
																									<td>
																										<label ezfout name="serTrxSrcLineSubNum_A1" ezfname="serTrxSrcLineSubNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Reference# -->
																									<td>
																										<label ezfout name="serTrxRefNum_A1" ezfname="serTrxRefNum_A1" ezfhyo="A">123456789012345</label>
																									</td>
																									<!-- From SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A1" ezfname="stkStsDescTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A2" ezfname="stkStsDescTxt_A2" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Original Merchandise Code -->
																									<td>
																										<label ezfout name="origMdseCd_A1" ezfname="origMdseCd_A1" ezfhyo="A">1234567890123456</label>
																									</td>
																									<!-- Comment -->
																									<td>
																										<a href="#" onclick="sendServer('OpenWin_DtlCmt')"><label ezfout name="xxYesNoCd_A1" ezfname="xxYesNoCd_A1" ezfhyo="A">Y</label></a>
																									</td>
																									<!-- Error Status -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A3" ezfname="xxDplyByCdNmCnctTxt_A3" ezfhyo="A">123:1234567890123456789012345678901234567890</label>
																									</td>
																								</tr>
																								<tr class="pEvenNumberBGcolor" height="28" id="id_row{EZF_ROW_NUMBER}">
																									<!-- Merchandise Name -->
																									<td>
																										<label ezfout name="mdseDescShortTxt_A1" ezfname="mdseDescShortTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Serial Event -->
																									<td>
																										<label ezfout name="serTrxEventDescTxt_A1" ezfname="serTrxEventDescTxt_A1" ezfhyo="A">1234567890123456789012345</label>
																									</td>
																									<!-- Transaction Date Time -->
																									<td>
																										<label ezfout name="xxDtNm_A1" ezfname="xxDtNm_A1" ezfhyo="A">mm/dd/yyyy HH:MM</label>
																									</td>
																									<!-- Manual Creation -->
																									<td>
																										<label ezfout name="manCratFlg_A1" ezfname="manCratFlg_A1" ezfhyo="A">Y</label>
																									</td>
																									<!-- Location (From) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A1" ezfname="xxDplyByCdNmCnctTxt_A1" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Location (To) -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A2" ezfname="xxDplyByCdNmCnctTxt_A2" ezfhyo="A">123456:12345678901234567890</label>
																									</td>
																									<!-- Serial Tranasction Source Type -->
																									<td>
																										<label ezfout name="serTrxSrcTpDescTxt_A1" ezfname="serTrxSrcTpDescTxt_A1" ezfhyo="A">12345678901234567890</label>
																									</td>
																									<!-- Serial Transaction Source Header# -->
																									<td>
																										<label ezfout name="serTrxSrcHdrNum_A1" ezfname="serTrxSrcHdrNum_A1" ezfhyo="A">1234567890</label>
																									</td>
																									<!-- Serial Transaction Source Liner# -->
																									<td>
																										<label ezfout name="serTrxSrcLineNum_A1" ezfname="serTrxSrcLineNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Source Line Sub# -->
																									<td>
																										<label ezfout name="serTrxSrcLineSubNum_A1" ezfname="serTrxSrcLineSubNum_A1" ezfhyo="A">123</label>
																									</td>
																									<!-- Serial Transaction Reference# -->
																									<td>
																										<label ezfout name="serTrxRefNum_A1" ezfname="serTrxRefNum_A1" ezfhyo="A">123456789012345</label>
																									</td>
																									<!-- From SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A1" ezfname="stkStsDescTxt_A1" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- SS -->
																									<td>
																										<label ezfout name="stkStsDescTxt_A2" ezfname="stkStsDescTxt_A2" ezfhyo="A">123456789012345678901234567890</label>
																									</td>
																									<!-- Original Merchandise Code -->
																									<td>
																										<label ezfout name="origMdseCd_A1" ezfname="origMdseCd_A1" ezfhyo="A">1234567890123456</label>
																									</td>
																									<!-- Comment -->
																									<td>
																										<a href="#" onclick="sendServer('OpenWin_DtlCmt')"><label ezfout name="xxYesNoCd_A1" ezfname="xxYesNoCd_A1" ezfhyo="A">Y</label></a>
																									</td>
																									<!-- Error Status -->
																									<td>
																										<label ezfout name="xxDplyByCdNmCnctTxt_A3" ezfname="xxDplyByCdNmCnctTxt_A3" ezfhyo="A">123:1234567890123456789012345678901234567890</label>
																									</td>
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
													</td>
												</tr>
											</table>
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
