<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181203135949 --%>
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
			<input type="hidden" name="pageID" value="NMAL7010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price List Setup">

<%@ page import="business.servlet.NMAL7010.NMAL7010BMsg" %>
<% NMAL7010BMsg bMsg = (NMAL7010BMsg) databean.getEZDBMsg(); %>

<center>
	<table width="1130" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<!-- ###TAB - HEAD -->
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="1130" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="1120">
									<div>
										<li title="Mach Mst Inq" class="pTab_ON"><a href="#">Price List</a></li>
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
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<!-- ###TAB - BODY -->
				<div class="pTab_BODY">
					<div class="pTab_HEAD">
						<ul>
							<li class="pTab_ON"  id="TrxDrv" title="Header"><ezf:anchor name="" ezfName="xxTabProt_D2" ezfEmulateBtn="1" ezfGuard="TAB_TrxDrv" >Header</ezf:anchor></li>
							<li class="pTab_OFF" id="PrcListVal" title="PrcListVal"><ezf:anchor name="" ezfName="xxTabProt_D1" ezfEmulateBtn="1" ezfGuard="TAB_PrcListVal" >Prc List Val</ezf:anchor></li>
						</ul>
					</div>

					<div class="pTab_BODY_In" border="0" id="Header_div">
					<c:choose>
					<c:when test="${(pageScope._ezddatabean.xxTabProt_D1 != 'Y')}">
					<script type="text/javascript">
						document.getElementById( "TrxDrv" ).className = "pTab_ON";
						document.getElementById( "PrcListVal" ).className = "pTab_OFF";
					</script>

					<table border="1" cellpadding="0" cellspacing="0" height="120" style="table-layout:fixed;">
						<col width="550">
						<col width="570">
						<tr>
							<td valign="top" >
								<table cellpadding="0" border="0" style="table-layout:fixed;">
									<col align="left" width="115">
									<col align="left" width="98">
									<col align="left" width="32">
									<col align="left" width="30">
									<col align="left" width="100">
									<col align="left" width="155">
									<tr>
										<td class="stab">Price List ID</td>
										<td colspan="5">
											<ezf:inputText name="prcCatgCd_H1" ezfName="prcCatgCd_H1" value="445" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											&nbsp;&nbsp;
											<ezf:inputButton name="Search_PrcList" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search_PrcList\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Price List Name</td>
										<td colspan="5">
											<ezf:inputText name="prcCatgNm_H1" ezfName="prcCatgNm_H1" value="E-National IPA" otherAttr=" size=\"50\" maxlength=\"75\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Price List Display Name</td>
										<td colspan="5">
											<ezf:inputText name="prcListDplyNm_H1" ezfName="prcListDplyNm_H1" otherAttr=" size=\"50\" maxlength=\"100\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Description/Notes</td>
										<td colspan="5">
											<ezf:inputText name="prcCatgDescTxt_H1" ezfName="prcCatgDescTxt_H1" otherAttr=" size=\"50\" maxlength=\"2000\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Price List Type</td>
										<td colspan="3">
											<c:choose>
											<c:when test="${pageScope._ezddatabean.xxDplyCtrlFlg_H1 == 'Y'}">
											<ezf:select name="prcListTpCd_H1" ezfName="prcListTpCd_H1" ezfBlank="1" ezfCodeName="prcListTpCd_L1" ezfDispName="prcListTpDescTxt_L1" otherEvent1=" onchange=\"sendServer('OnChange_PrcListTp');\"" otherAttr=" style=\"width: 150px\""/>
											</c:when>

											<c:when test="${pageScope._ezddatabean.xxDplyCtrlFlg_H1 == 'N'}">
											<ezf:select name="prcListTpCd_H1" ezfName="prcListTpCd_H1" ezfCodeName="prcListTpCd_L1" ezfDispName="prcListTpDescTxt_L1" otherEvent1=" onchange=\"sendServer('OnChange_PrcListTp');\"" otherAttr=" style=\"width: 150px\""/>
											</c:when>
											</c:choose>
										</td>
										<td class="stab">Effective Date From</td>
										<td>
											<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="01/01/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
										</td>
									</tr>
									<tr>
										<td class="stab">Customer Regist Req</td>
										<td>
											<ezf:inputCheckBox name="custRgtnReqFlg_H1" ezfName="custRgtnReqFlg_H1" value="Y" />
										</td>
										<td class="stab">Active</td>
										<td><ezf:inputCheckBox name="actvFlg_H1" ezfName="actvFlg_H1" value="Y" /></td>
										</td>
										<td class="stab">Effective Date To</td>
										<td>
											<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" >
										</td>
									</tr>
									<tr>
										<td class="stab">Sales Visibility</td>
										<td class="stab" colspan="3">
											<ezf:select name="prcSlsVisTpCd_H1" ezfName="prcSlsVisTpCd_H1" ezfBlank="1" ezfCodeName="prcSlsVisTpCd_L1" ezfDispName="prcSlsVisTpDescTxt_L1" />
										</td>
										<td class="stab">Price List Group</td>
										<td>
											<ezf:select name="prcListGrpCd_H1" ezfName="prcListGrpCd_H1" ezfBlank="1" ezfCodeName="prcListGrpCd_L1" ezfDispName="prcListGrpDescTxt_L1" otherAttr=" style=\"width:100px;\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="prcContrNum_LK" ezfName="prcContrNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ContrNum" >Related Contract#</ezf:anchor></td>
										<td colspan="3">
											<ezf:inputText name="prcContrNum_H1" ezfName="prcContrNum_H1" value="NIPA-2015" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/>
										</td>
										<td colspan="2">
											<ezf:inputText name="prcContrNm_H1" ezfName="prcContrNm_H1" otherAttr=" size=\"28\" maxlength=\"150\""/>
										</td>
									</tr>
									<tr>
										<td valign="top">
											<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
												<tr>
													<td class="stab">Sub Price List</td>
												</tr>
												<tr>
													<td height="55" valign="bottom" align="right">
														<ezf:inputButton name="Add_SubPrc" value="Add" htmlClass="pBtn0" />
														<ezf:inputButton name="Del_SubPrc" value="Del" htmlClass="pBtn0" />
														<span style="width:2px;"></span>
													</td>
												</tr>
											</table>
										</td>
										<td colspan="5">
											<%-- ### Sub Price List - TOP --%>
											<div style="overflow-x:hidden; overflow-y:hidden; width:405; height:20; float:left;">
												<table border="1" cellpadding="0" cellspacing="0" height="20" style="table-layout:fixed;">
													<col align="center" width="23">
													<col align="center" width="162">
													<col align="center" width="95">
													<col align="center" width="95">
													<col align="center" width="25">
													<tr>
														<td class="pClothBs"></td>
														<td class="pClothBs">Name</td>
														<td class="pClothBs">From</td>
														<td class="pClothBs">To</td>
														<td class="pClothBs">Seq</td>
													</tr>
												</table>
											</div>
											<%-- ### Sub Price List - BOTTOM --%>
											<div style="overflow-x:hidden; overflow-y:scroll; height:52; width:422; float:left; table-layout:fixed;">
												<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="W">
													<col align="center" width="23">
													<col align="center" width="162">
													<col align="center" width="95">
													<col align="center" width="95">
													<col align="center" width="25">
													<ezf:row ezfHyo="W">
														<tr height="25">
															<td><ezf:inputCheckBox name="xxChkBox_SW" ezfName="xxChkBox_SW" value="Y" ezfHyo="W" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="prcCatgNm_SW" ezfName="prcCatgNm_SW" value="Enterprise Service & Solution" ezfHyo="W" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"75\""/><ezf:inputButton name="OpenWin_SubPrcList" value="..." ezfHyo="W" ezfArrayNo="0" htmlClass="pBtn0" /></td>
															<td><ezf:inputText name="effFromDt_SW" ezfName="effFromDt_SW" ezfHyo="W" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><span style="display:inline-block; vertical-align:top;"><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_SW', 4, '{EZF_ROW_NUMBER}');"></span></td>
															<td><ezf:inputText name="effThruDt_SW" ezfName="effThruDt_SW" ezfHyo="W" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><span style="display:inline-block; vertical-align:top;"><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_SW', 4, '{EZF_ROW_NUMBER}');"></span></td>
															<td><ezf:inputText name="subPrcPrtyNum_SW" ezfName="subPrcPrtyNum_SW" value="10" ezfHyo="W" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\""/></td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr height="25">
															<td><input type="checkbox" value="Y"></td>
															<td><input class="pPro" type="text" size="17" maxlength="50" value="Enterprise Service & Solution" readOnly><input type="button" value="..." class="pBtn0" ></td>
															<td><input type="text" size="10" maxlength="10" value=""><span style="display:inline-block; vertical-align:top;"><img src="img/calendar.gif" class="pCalendar"></span></td>
															<td><input type="text" size="10" maxlength="10" value=""><span style="display:inline-block; vertical-align:top;"><img src="img/calendar.gif" class="pCalendar"></span></td>
															<td><input class="pPro" type="text" size="2" maxlength="2" value="10"></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</td>

<%-- ######################################## Price List Audience ######################################## --%>
							<td valign="top">
								<table width="560">
									<col valign="top">
									<tr>
										<td class="pClothBs" align="center">Price List Audience</td>
									</tr>
									<tr>
										<td>
											<div class="pTab_HEAD">
											<ul>
												<li class="pTab_ON"  id="CustAudc" title="CustAudc"><ezf:anchor name="" ezfName="xxTabProt_H1" ezfEmulateBtn="1" ezfGuard="TAB_CustAudc" >Cust Audc</ezf:anchor></li>
												<li class="pTab_OFF" id="TrxAudc"  title="TrxAudc"><ezf:anchor name="" ezfName="xxTabProt_H2" ezfEmulateBtn="1" ezfGuard="TAB_TrxAudc" >Tran Audc</ezf:anchor></li>
											</ul>
											</div>
<%-- ######################################## Customer Audience ######################################## --%>
											<c:choose>
											<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'CustAudc'}">
											<script type="text/javascript">
												document.getElementById( "CustAudc").className = "pTab_ON";
												document.getElementById( "TrxAudc" ).className = "pTab_OFF";
											</script>

											<div class="pTab_BODY_In"  id="CustAudc_div">
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													<col width="60">
													<col width="250">
													<col width="80">
													<col width="80">
													<col width="80">
													<tr>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td><ezf:inputButton name="MoveWin_UploadCustAudc" value="Upload" htmlClass="pBtn5" /></td>
														<td><ezf:inputButton name="Add_CustAudc" value="Insert Row" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="Del_CustAudc" value="Delete Row" htmlClass="pBtn6" otherAttr=" id=\"delCustAudc\""/></td>
													</tr>
												</table>
												<table border="0" cellpadding="1" cellspacing="0" width="560">
													<tr>
														<td>
															<%-- ### Customer Audience - Left TBL - TOP --%>
															<div id="LeftTBL_Top_CustAudc" style="overflow-x:none; overflow-y:none; width:25; height:25; float:left;">
																<table border="1" cellpadding="1" cellspacing="0" width="25" height="25">
																	<col align="center" width="25">
																	<tr>
																		<td class="pClothBs"></td>
																	</tr>
																</table>
															</div>
															<%-- ### Customer Audience - Right TBL - TOP --%>
															<div id="RightTBL_Top_CustAudc" style="overflow-x:hidden; overflow-y:hidden; width:515; height:25; float:left;">
																<table border="1" cellpadding="1" cellspacing="0" width="2240px" height="25">
																	<col align="center" width="170">
																	<col align="center" width="180">
																	<col align="center" width="220">
																	<col align="center" width="170">
																	<col align="center" width="180">
																	<col align="center" width="220">
																	<col align="center" width="170">
																	<col align="center" width="180">
																	<col align="center" width="220">
																	<col align="center" width="90" >
																	<col align="center" width="130">
																	<col align="center" width="130">
																	<col align="center" width="120">
																	<tr>
																		<td class="pClothBs">Audience Category 1</td>
																		<td class="pClothBs">Audience Value 1</td>
																		<td class="pClothBs">Value 1 Name</td>
																		<td class="pClothBs">Audience Category 2</td>
																		<td class="pClothBs">Audience Value 2</td>
																		<td class="pClothBs">Value 2 Name</td>
																		<td class="pClothBs">Audience Category 3</td>
																		<td class="pClothBs">Audience Value 3</td>
																		<td class="pClothBs">Value 3 Name</td>
																		<td class="pClothBs">Required</td>
																		<td class="pClothBs">Date From</td>
																		<td class="pClothBs">Date To</td>
																		<td class="pClothBs">Default Price List</td>
																	</tr>
																</table>
															</div>
															<%-- ### Customer Audience - Left TBL - BOTTOM --%>
															<div id="LeftTBL_CustAudc" style="overflow-x:hidden; overflow-y:hidden; height:128; width:25; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL_CustAudc' ))" >
																<table border="1" cellpadding="1" cellspacing="0" width="25" id="HC1">
																	<col align="center" width="25">
																	<ezf:row ezfHyo="X">
																		<tr id="id_X_row{EZF_ROW_NUMBER}" height="25">
																			<td><ezf:inputCheckBox name="xxChkBox_CX" ezfName="xxChkBox_CX" value="Y" ezfHyo="X" ezfArrayNo="0" /></td>
																		</tr>
																	</ezf:row>
																	<ezf:skip>
																	</ezf:skip>
																</table>
															</div>
															<%-- ### Customer Audience - Right TBL - BOTTOM --%>
															<div id="RightTBL_CustAudc" style="overflow-x:scroll; overflow-y:scroll; height:145; width:532; float:left; table-layout: fixed;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL_CustAudc' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top_CustAudc' ));" >
																<table border="1" cellpadding="1" cellspacing="0" width="2240px" id="HC2">
																	<col align="center" width="170">
																	<col align="center" width="140">
																	<col align="center" width="40">
																	<col align="center" width="220">
																	<col align="center" width="170">
																	<col align="center" width="140">
																	<col align="center" width="40">
																	<col align="center" width="220">
																	<col align="center" width="170">
																	<col align="center" width="140">
																	<col align="center" width="40">
																	<col align="center" width="220">
																	<col align="center" width="90 ">
																	<col align="center" width="130">
																	<col align="center" width="130">
																	<col align="center" width="120">
																	<ezf:row ezfHyo="X">
																		<tr id="id_X_row{EZF_ROW_NUMBER}" height="25">
																			<td>
																				<ezf:select name="prcCustAudcCatgCd_X1" ezfName="prcCustAudcCatgCd_X1" ezfHyo="X" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcCustAudcCatgCd_L1" ezfDispName="prcCustAudcCatgDescTxt_L1" otherEvent1=" onchange=\"sendServer('OnChange_PrcCustAudcCatg_01', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 150px\""/>
																			</td>
																			<td style="border-right-style:none;">
																				<ezf:inputText name="xxScrItem30Txt_X1" ezfName="xxScrItem30Txt_X1" value="ESS" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"28\" id=\"xxScrItem30Txt_X1#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																				<ezf:inputCheckBox name="pubFlg_CX" ezfName="pubFlg_CX" value="Y" ezfHyo="X" ezfArrayNo="0" otherAttr=" id=\"pubFlg_CX#{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td style="border-left-style:none; ">
																				<ezf:inputButton name="OpenWin_CustAudcVal_01_Cmn" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CustAudcVal_01_Cmn#{EZF_ROW_NUMBER}\""/>
																				<ezf:inputButton name="OpenWin_CustAudcVal_01_Gen" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CustAudcVal_01_Gen#{EZF_ROW_NUMBER}\""/>
																				<ezf:inputButton name="OpenWin_CustAudcVal_01_Acc" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CustAudcVal_01_Acc#{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td><ezf:inputText name="xxRecNmTxt_X1" ezfName="xxRecNmTxt_X1" value="Enterprise Service & Solution" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
																			<td>
																				<ezf:select name="prcCustAudcCatgCd_X2" ezfName="prcCustAudcCatgCd_X2" ezfHyo="X" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcCustAudcCatgCd_L2" ezfDispName="prcCustAudcCatgDescTxt_L2" otherEvent1=" onchange=\"sendServer('OnChange_PrcCustAudcCatg_02', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 150px\""/>
																			</td>
																			<td style="border-right-style:none;">
																				<ezf:inputText name="xxScrItem30Txt_X2" ezfName="xxScrItem30Txt_X2" value="228" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/>
																			</td>
																			<td style="border-left-style:none; ">
																				<ezf:inputButton name="OpenWin_CustAudcVal_02_Cmn" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CustAudcVal_02_Cmn#{EZF_ROW_NUMBER}\""/>
																				<ezf:inputButton name="OpenWin_CustAudcVal_02_Gen" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CustAudcVal_02_Gen#{EZF_ROW_NUMBER}\""/>
																				<ezf:inputButton name="OpenWin_CustAudcVal_02_Acc" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CustAudcVal_02_Acc#{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td><ezf:inputText name="xxRecNmTxt_X2" ezfName="xxRecNmTxt_X2" value="MIDTOWN_WEST" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>	
																			<td>
																				<ezf:select name="prcCustAudcCatgCd_X3" ezfName="prcCustAudcCatgCd_X3" ezfHyo="X" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcCustAudcCatgCd_L3" ezfDispName="prcCustAudcCatgDescTxt_L3" otherEvent1=" onchange=\"sendServer('OnChange_PrcCustAudcCatg_03', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 150px\""/>
																			</td>
																			<td style="border-right-style:none;">
																				<ezf:inputText name="xxScrItem30Txt_X3" ezfName="xxScrItem30Txt_X3" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/>
																			</td>
																			<td style="border-left-style:none; ">
																				<ezf:inputButton name="OpenWin_CustAudcVal_03_Cmn" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CustAudcVal_03_Cmn#{EZF_ROW_NUMBER}\""/>
																				<ezf:inputButton name="OpenWin_CustAudcVal_03_Gen" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CustAudcVal_03_Gen#{EZF_ROW_NUMBER}\""/>
																				<ezf:inputButton name="OpenWin_CustAudcVal_03_Acc" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CustAudcVal_03_Acc#{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td><ezf:inputText name="xxRecNmTxt_X3" ezfName="xxRecNmTxt_X3" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
																			<td><ezf:inputCheckBox name="reqFlg_CX" ezfName="reqFlg_CX" value="Y" ezfHyo="X" ezfArrayNo="0" /></td>
																			<td>
																				<ezf:inputText name="effFromDt_CX" ezfName="effFromDt_CX" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_CX', 4, '{EZF_ROW_NUMBER}');" >
																			</td>
																			<td>
																				<ezf:inputText name="effThruDt_CX" ezfName="effThruDt_CX" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_CX', 4, '{EZF_ROW_NUMBER}');" >
																			</td>
																			<td><ezf:inputCheckBox name="defPrcListFlg_CX" ezfName="defPrcListFlg_CX" value="Y" ezfHyo="X" ezfArrayNo="0" /></td>
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
											
<%-- ######################################## Transaction Audience ######################################## --%>
											<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'TrxAudc'}">
											<script type="text/javascript">
												document.getElementById( "CustAudc").className = "pTab_OFF";
												document.getElementById( "TrxAudc" ).className = "pTab_ON";
											</script>
											<div class="pTab_BODY_In"  id="TrxAudc_div">
												<table border="0" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													<col width="60">
													<col width="250">
													<col width="80">
													<col width="80">
													<col width="80">
													<tr>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td><ezf:inputButton name="Add_TrxAudc" value="Insert Row" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="Del_TrxAudc" value="Delete Row" htmlClass="pBtn6" otherAttr=" id=\"delTrxAudc\""/></td>
													</tr>
												</table>
												<table border="0" cellpadding="1" cellspacing="0" width="560">
													<tr>
														<td>
															<%-- ### Transaction Audience - Left TBL - TOP --%>
															<div id="LeftTBL_Top_TrxAudc" style="overflow-x:none; overflow-y:none; width:25; height:25; float:left;">
																<table border="1" cellpadding="1" cellspacing="0" width="25" height="25">
																	<col align="center" width="25">
																	<tr>
																		<td class="pClothBs"></td>
																	</tr>
																</table>
															</div>
															<%-- ### Transaction Audience - Right TBL - TOP --%>
															<div id="RightTBL_Top_TrxAudc" style="overflow-x:hidden; overflow-y:hidden; width:515; height:25; float:left;">
																<table border="1" cellpadding="1" cellspacing="0" width="1380" height="25">
																	<col align="center" width="170">
																	<col align="center" width="170">
																	<col align="center" width="220">
																	<col align="center" width="170">
																	<col align="center" width="170">
																	<col align="center" width="220">
																	<col align="center" width="120">
																	<col align="center" width="120">
																	<tr>
																		<td class="pClothBs">Transaction Qualifier 1</td>
																		<td class="pClothBs">Transaction Value 1</td>
																		<td class="pClothBs">Value 1 Name</td>
																		<td class="pClothBs">Transaction Qualifier 2</td>
																		<td class="pClothBs">Transaction Value 2</td>
																		<td class="pClothBs">Value 2 Name</td>
																		<td class="pClothBs">Date From</td>
																		<td class="pClothBs">Date To</td>
																	</tr>
																</table>
															</div>
															<%-- ### Transaction Audience - Left TBL - BOTTOM --%>
															<div id="LeftTBL_TrxAudc" style="overflow-x:hidden; overflow-y:hidden; height:128; width:25; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL_TrxAudc' ))" >
																<table border="1" cellpadding="1" cellspacing="0" width="25" id="HT1">
																	<col align="center" width="25">
																	<ezf:row ezfHyo="Y">
																		<tr id="id_Y_row{EZF_ROW_NUMBER}" height="25">
																			<td><ezf:inputCheckBox name="xxChkBox_TY" ezfName="xxChkBox_TY" value="Y" ezfHyo="Y" ezfArrayNo="0" /></td>
																		</tr>
																	</ezf:row>
																	<ezf:skip>
																	</ezf:skip>
																</table>
															</div>
															<%-- ### Customer Audience - Right TBL - BOTTOM --%>
															<div id="RightTBL_TrxAudc" style="overflow-x:scroll; overflow-y:scroll; height:145; width:532; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL_TrxAudc' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top_TrxAudc' ));" >
																<table border="1" cellpadding="1" cellspacing="0" width="1380" id="HT2">
																	<col align="center" width="170">
																	<col align="center" width="170">
																	<col align="center" width="220">
																	<col align="center" width="170">
																	<col align="center" width="170">
																	<col align="center" width="220">
																	<col align="center" width="120">
																	<col align="center" width="120">
																	<ezf:row ezfHyo="Y">
																		<tr id="id_Y_row{EZF_ROW_NUMBER}" height="25">
																			<td>
																				<ezf:select name="prcTrxAudcCatgCd_Y1" ezfName="prcTrxAudcCatgCd_Y1" ezfHyo="Y" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcTrxAudcCatgCd_L1" ezfDispName="prcTrxAudcCatgDescTxt_L1" otherEvent1=" onchange=\"sendServer('OnChange_PrcTrxAudcCatg_01', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 150px\""/>
																			</td>
																			<td>
																				<ezf:inputText name="xxScrItem30Txt_Y1" ezfName="xxScrItem30Txt_Y1" value="LEASE" ezfHyo="Y" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/>
																				<ezf:inputButton name="OpenWin_TrxAudcVal_01_Cmn" value="..." ezfHyo="Y" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TrxAudcVal_01_Cmn#{EZF_ROW_NUMBER}\""/>
																				<ezf:inputButton name="OpenWin_TrxAudcVal_01_Gen" value="..." ezfHyo="Y" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TrxAudcVal_01_Gen#{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td><ezf:inputText name="xxRecNmTxt_Y1" ezfName="xxRecNmTxt_Y1" ezfHyo="Y" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
																			<td>
																				<ezf:select name="prcTrxAudcCatgCd_Y2" ezfName="prcTrxAudcCatgCd_Y2" ezfHyo="Y" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcTrxAudcCatgCd_L2" ezfDispName="prcTrxAudcCatgDescTxt_L2" otherEvent1=" onchange=\"sendServer('OnChange_PrcTrxAudcCatg_02', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 150px\""/>
																			</td>
																			<td>
																				<ezf:inputText name="xxScrItem30Txt_Y2" ezfName="xxScrItem30Txt_Y2" ezfHyo="Y" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/>
																				<ezf:inputButton name="OpenWin_TrxAudcVal_02_Cmn" value="..." ezfHyo="Y" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TrxAudcVal_02_Cmn#{EZF_ROW_NUMBER}\""/>
																				<ezf:inputButton name="OpenWin_TrxAudcVal_02_Gen" value="..." ezfHyo="Y" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TrxAudcVal_02_Gen#{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td><ezf:inputText name="xxRecNmTxt_Y2" ezfName="xxRecNmTxt_Y2" ezfHyo="Y" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
																			<td>
																				<ezf:inputText name="effFromDt_TY" ezfName="effFromDt_TY" ezfHyo="Y" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_TY', 4, '{EZF_ROW_NUMBER}');" >
																			</td>
																			<td>
																				<ezf:inputText name="effThruDt_TY" ezfName="effThruDt_TY" ezfHyo="Y" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_TY', 4, '{EZF_ROW_NUMBER}');" >
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
											
											</c:choose>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>


					<table border="0" cellpadding="0" cellspacing="0" width="99%" align="center" style="table-layout:fixed;">
						<tr>
							<td valign="top" style="height:280">
<%------------------------%>
<%-- Trx Drv Equipment	--%>
<%------------------------%>
							<c:choose>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_H2 == 'TrxDrvEquipment'}">
							<fieldset id="trxDrvField" name="trxDrvField" style="border: #000000 1px solid;">
							<legend>Transaction Driver</legend>
								<table border="0" cellpadding="1" cellspacing="0" width="97%" align="center"  height="223">
									<tr>
										<td valign="top">
											<table  border="0" cellpadding="0" cellspacing="0" width="100%" align="top">
											<col width="50%">
											<col width="50%">
												<tr>
													<td class="stab">General Attributes</td>
													<td class="stab">Equipment Contract Attributes</td>
												</tr>
												<tr>
													<td>
														<div id="LeftTable_GA_Top" style="overflow-x:none; overflow-y:none; width:500; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																<col align="center" width="350">	<!-- Attribute Type	-->
																<col align="center" width="150">	<!-- Check			-->
																<tr height="24">
																	<td class="pClothBs">Attribute Type</td>
																	<td class="pClothBs">Check</td>
																</tr>
															</table>
														</div>
														<div id="LeftTable_GA" style="overflow-y:scroll; height:215; overflow-x:hidden; width:519; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed">
																<col width="350">					<!-- Attribute Type	-->
																<col width="150" align="center">	<!-- Check			-->
																<tr>
																	<td>Not to Exceed</td>
																	<td><ezf:inputCheckBox name="notToExcdFlg_GA" ezfName="notToExcdFlg_GA" value="Y" /></td>
																</tr>
																<tr>
																	<td>Bid Number</td>
																	<td><ezf:inputText name="custBidNum_GA" ezfName="custBidNum_GA" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
																</tr>
																<tr>
																	<td>Allow Price Deviation</td>
																	<td><ezf:inputCheckBox name="allwPrcDevnFlg_GA" ezfName="allwPrcDevnFlg_GA" value="Y" /></td>
																</tr>
															</table>
														</div>
													</td>
													<td>
														<div id="RightTable_CA_Top" style="overflow-x:none; overflow-y:none; width:500; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																<col align="center" width="350">	<!-- Attribute Type	-->
																<col align="center" width="150">	<!-- Check			-->
																<tr height="24">
																	<td class="pClothBs">Attribute Type</td>
																	<td class="pClothBs">Check</td>
																</tr>
															</table>
														</div>
														<div id="RightTable_CA" style="overflow-y:scroll; height:215; overflow-x:hidden; width:519; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed">
															<col width="350">					<!-- Attribute Type	-->
															<col width="150" align="center">	<!-- Check			-->
																<tr>
																	<td>Lease Type</td>
																	<td>
																		<ezf:select name="prcLeaseTpCd_CA" ezfName="prcLeaseTpCd_CA" ezfBlank="1" ezfCodeName="prcLeaseTpCd_LA" ezfDispName="prcLeaseTpDescTxt_LA" otherAttr=" style=\"width: 100px\""/>
																	</td>
																</tr>
																<tr>
																	<td>Lease Return Fee Included</td>
																	<td><ezf:inputCheckBox name="leaseRtrnFeeInclFlg_CA" ezfName="leaseRtrnFeeInclFlg_CA" value="Y" /></td>
																</tr>
																<tr>
																	<td>Additional Shipping Fee Included</td>
																	<td><ezf:inputCheckBox name="addlShpgFeeInclFlg_CA" ezfName="addlShpgFeeInclFlg_CA" value="Y" /></td>
																</tr>
																<tr>
																	<td>Relocation Included</td>
																	<td><ezf:inputCheckBox name="reloInclFlg_CA" ezfName="reloInclFlg_CA" value="Y" /></td>
																</tr>
																<tr>
																	<td>Hard Drive Erase Included</td>
																	<td><ezf:inputCheckBox name="hardDriveEraseInclFlg_CA" ezfName="hardDriveEraseInclFlg_CA" value="Y" /></td>
																</tr>
																<tr>
																	<td>Hard Drive Removal Included</td>
																	<td><ezf:inputCheckBox name="hardDriveRmvInclFlg_CA" ezfName="hardDriveRmvInclFlg_CA" value="Y" /></td>
																</tr>
																<tr>
																	<td>Total Price Cannot Exceed Included</td>
																	<td><ezf:inputCheckBox name="notExcdContrPrcFlg_CA" ezfName="notExcdContrPrcFlg_CA" value="Y" /></td>
																</tr>
																<tr>
																	<td>Special CSMP Exclude from AR Name</td>
																	<td><ezf:inputText name="spclCsmpExclArNm_CA" ezfName="spclCsmpExclArNm_CA" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/></td>
																</tr>
																<tr>
																	<td>SOM Transaction Type Eligible</td>
																	<td>
																		<ezf:select name="somEligTrxTpCd_CA" ezfName="somEligTrxTpCd_CA" ezfBlank="1" ezfCodeName="somEligTrxTpCd_LA" ezfDispName="somEligTrxTpDescTxt_LA" otherAttr=" style=\"width: 100px\""/>
																	</td>
																</tr>
																<tr>
																	<td>Related Lease Price List Name</td>
																	<td><ezf:inputText name="prcCatgNm_CA" ezfName="prcCatgNm_CA" otherAttr=" size=\"15\" maxlength=\"75\""/><ezf:inputButton name="OpenWin_LeaseRatePrcList" value="..." htmlClass="pBtn0" /></td>
																</tr>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</fieldset>
							</c:when>

<%------------------------%>
<%-- Trx Drv Service	--%>
<%------------------------%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_H2 == 'TrxDrvService'}">
							<fieldset id="trxDrvField" name="trxDrvField" style="border: #000000 1px solid;">
							<legend>Transaction Driver</legend>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center"  height="223">
									<tr>
										<td valign="top">
											<table  border="0" cellpadding="0" cellspacing="0" width="100%" align="top">
											<col width="50%">
											<col width="50%">
												<tr>
													<td class="stab">General Attributes</td>
													<td class="stab">Service Contract Attributes</td>
												</tr>
												<tr>
													<td>
														<div id="LeftTable_GB_Top" style="overflow-x:none; overflow-y:none; width:500; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																<col align="center" width="350">	<!-- Attribute Type	-->
																<col align="center" width="150">	<!-- Check			-->
																<tr height="24">
																	<td class="pClothBs">Attribute Type</td>
																	<td class="pClothBs">Check</td>
																</tr>
															</table>
														</div>
														<div id="LeftTable_GB" style="overflow-y:scroll; height:215; overflow-x:hidden; width:519; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed">
																<col width="350">					<!-- Attribute Type	-->
																<col width="150" align="center">	<!-- Check			-->
																<tr>
																	<td>Not to Exceed</td>
																	<td><ezf:inputCheckBox name="notToExcdFlg_GB" ezfName="notToExcdFlg_GB" value="Y" /></td>
																</tr>
																<tr>
																	<td>Allow Price Deviation</td>
																	<td><ezf:inputCheckBox name="allwPrcDevnFlg_GB" ezfName="allwPrcDevnFlg_GB" value="Y" /></td>
																</tr>
															</table>
														</div>
													</td>
													<td>
														<div id="RightTable_CB_Top" style="overflow-x:none; overflow-y:none; width:500; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																<col align="center" width="350">	<!-- Attribute Type	-->
																<col align="center" width="150">	<!-- Check			-->
																<tr height="24">
																	<td class="pClothBs">Attribute Type</td>
																	<td class="pClothBs">Check</td>
																</tr>
															</table>
														</div>
														<div id="RightTable_CB" style="overflow-y:scroll; height:215; overflow-x:hidden; width:519; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed">
																<col width="350">					<!-- Attribute Type	-->
																<col width="150" align="center">	<!-- Check			-->
																<tr>
																	<td>Waive CT/SRT when Using Book Price</td>
																	<td><ezf:inputCheckBox name="wavBookPrcFlg_CB" ezfName="wavBookPrcFlg_CB" value="Y" /></td>
																</tr>
																<tr>
																	<td>Waive CT/SRT Entirely</td>
																	<td><ezf:inputCheckBox name="wavAllFlg_CB" ezfName="wavAllFlg_CB" value="Y" /></td>
																</tr>
																<tr>
																	<td>Allow New Aggregate Contract</td>
																	<td><ezf:inputCheckBox name="allowNewAggrContrFlg_CB" ezfName="allowNewAggrContrFlg_CB" value="Y" /></td>
																</tr>
																<tr>
																	<td>Allow New Fleet Contract</td>
																	<td><ezf:inputCheckBox name="allowNewFleetContrFlg_CB" ezfName="allowNewFleetContrFlg_CB" value="Y" /></td>
																</tr>
																<tr>
																	<td>Allow Add to Existing Fleet Contract</td>
																	<td><ezf:inputCheckBox name="allowAddFleetContrFlg_CB" ezfName="allowAddFleetContrFlg_CB" value="Y" /></td>
																</tr>
																<tr>
																	<td>Allow Add to Existing Aggregate Contract</td>
																	<td><ezf:inputCheckBox name="allowAddAggrContrFlg_CB" ezfName="allowAddAggrContrFlg_CB" value="Y" /></td>
																</tr>
																<tr>
																	<td>Allow Service to be Declined</td>
																	<td><ezf:inputCheckBox name="allowSvcToDclnFlg_CB" ezfName="allowSvcToDclnFlg_CB" value="Y" /></td>
																</tr>
																<tr>
																	<td>Cap SRT (%)</td>
																	<td><ezf:inputText name="grsPrcPct_CB" ezfName="grsPrcPct_CB" value="1,234.12345" otherAttr=" size=\"11\" maxlength=\"11\" style=\"text-align:right\""/></td>
																</tr>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</fieldset>
							</c:when>

<%----------------------------%>
<%-- Trx Drv Service Tiers	--%>
<%----------------------------%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_H2 == 'TrxDrvServiceTiers'}">
							<fieldset id="trxDrvField" name="trxDrvField" style="border: #000000 1px solid;">
							<legend>Transaction Driver</legend>
								<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center"  height="223">
									<tr>
										<td valign="top">
											<table  border="0" cellpadding="0" cellspacing="0" width="100%" align="top">
											<col width="50%">
											<col width="50%">
												<tr>
													<td class="stab">General Attributes</td>
													<td class="stab">Service Contract Attributes</td>
												</tr>
												<tr>
													<td>
														<div id="LeftTable_GC_Top" style="overflow-x:none; overflow-y:none; width:500; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																<col align="center" width="350">	<!-- Attribute Type	-->
																<col align="center" width="150">	<!-- Check			-->
																<tr height="24">
																	<td class="pClothBs">Attribute Type</td>
																	<td class="pClothBs">Check</td>
																</tr>
															</table>
														</div>
														<div id="LeftTable_GC" style="overflow-y:scroll; height:215; overflow-x:hidden; width:519; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" id="TE" style="table-layout: fixed">
																<col width="350">					<!-- Attribute Type	-->
																<col width="150" align="center">	<!-- Check			-->
															</table>
														</div>
													</td>
													<td>
														<div id="RightTable_CC_Top" style="overflow-x:none; overflow-y:none; width:500; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
																<col align="center" width="350">	<!-- Attribute Type	-->
																<col align="center" width="150">	<!-- Check			-->
																<tr height="24">
																	<td class="pClothBs">Attribute Type</td>
																	<td class="pClothBs">Check</td>
																</tr>
															</table>
														</div>
														<div id="RightTable_CC" style="overflow-y:scroll; height:215; overflow-x:hidden; width:519; float:left;">
															<table border="1" cellpadding="1" cellspacing="0"style="table-layout: fixed">
																<col width="350">					<!-- Attribute Type	-->
																<col width="150" align="center">	<!-- Check			-->
																<tr>
																	<td>Tiered Service Type</td>
																	<td>
																		<ezf:select name="prcTierSvcOfferCd_CC" ezfName="prcTierSvcOfferCd_CC" ezfBlank="1" ezfCodeName="prcTierSvcOfferCd_LC" ezfDispName="prcTierSvcOfferDescTxt_LC" otherAttr=" style=\"width: 100px\""/>
																	</td>
																</tr>
																<tr>
																	<td>Allow Service to be Declined</td>
																	<td><ezf:inputCheckBox name="allowSvcToDclnFlg_CC" ezfName="allowSvcToDclnFlg_CC" value="Y" /></td>
																</tr>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</fieldset>
							</c:when>
							<c:otherwise>
							</c:otherwise>
							</c:choose>
						</td>
					</table>

					</c:when>

<%-- ######################################## DETAIL ######################################## --%>
					<c:when test="${(pageScope._ezddatabean.xxTabProt_D1 == 'Y')}">

					<table width="1120">
						<col valign="top">
						<tr>
							<td>
							<table cellpadding="0" border="0" style="table-layout:fixed;">
								<col align="left" width="115">
								<col align="left" width="500">
								<tr>
									<td class="stab">Price List ID</td>
									<td>
										<ezf:inputText name="prcCatgCd_H2" ezfName="prcCatgCd_H2" value="445" otherAttr=" size=\"10\" maxlength=\"10\""/>
									</td>
								</tr>
								<tr>
									<td class="stab">Price List Name</td>
									<td>
										<ezf:inputText name="prcCatgNm_H2" ezfName="prcCatgNm_H2" value="E-National IPA" otherAttr=" size=\"50\" maxlength=\"75\""/>
									</td>
								</tr>
							</table>
							<fieldset id="prcListValFld" name="prcListValFld" style="border: #000000 1px solid;">
								<table  border="0" cellpadding="0" cellspacing="0" width="1110" style="table-layout: fixed;">
									<col width="180"  align="left" valign="top">
									<col width="260" align="left" valign="top">
									<col width="70"  align="left" valign="top">
									<col width="600" align="right" valign="top">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<td>
													<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<tr>
													<td class="stab" valign="middle">Show Records</td>
													<td valign="middle">
														<ezf:select name="prcDispRecTpCd_DH" ezfName="prcDispRecTpCd_DH" ezfCodeName="prcDispRecTpCd_L1" ezfDispName="prcDispRecTpDescTxt_L1" otherEvent1=" onchange=\"sendServer('OnChange_PrcDispRecTp')\"" otherAttr=" style=\"width: 150px\""/>
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<tr>
													<td><ezf:inputButton name="OpenWin_Filter" value="Filter" htmlClass="pBtn5" /></td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<tr>
													<td><ezf:inputButton name="DwldAsTemplate_PrcList" value="Download as Template" htmlClass="pBtn12" /></td>
													<td></td>
													<td><ezf:inputButton name="MoveWin_UploadPrcList" value="Upload" htmlClass="pBtn5" /></td>
													<td>&nbsp;</td>
													<td class="stab" valign="middle">Eff Date To</td>
													<td>
														<ezf:inputText name="effThruDt_DH" ezfName="effThruDt_DH" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_DH', 4);" >
													</td>
													<td><ezf:inputButton name="MassUpd_PrcList" value="MASS Update" htmlClass="pBtn7" /></Td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="Add_PrcList" value="Add" htmlClass="pBtn2" /></Td>
													<td></td>
													<td><ezf:inputButton name="Del_PrcList" value="Del" htmlClass="pBtn2" otherAttr=" id=\"delPrcList\""/></Td>
													<td></td>
													<td><ezf:inputButton name="Copy_PrcList" value="Copy" htmlClass="pBtn2" /></Td>
													<td>&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</fieldset>

							<table  border="0" cellpadding="0" cellspacing="3" width="100%" style="table-layout: fixed;">
								<col width="450" align="left"   valign="top">
								<col width="130" align="left"   valign="top">
								<col width="350" align="right"  valign="top">
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0">
											<tr>
												<td class="stab" valign="middle"><ezf:label name="xxAbendMsgTxt_H1" ezfName="xxAbendMsgTxt_H1" /></td>
											</tr>
										</table>
									</td>
									<td>
										<table border="0" cellpadding="1" cellspacing="0">
											<tr>
												<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" /></td>
												<td></td>
												<td><ezf:inputButton name="UnselectAll" value="Un Select All" htmlClass="pBtn6" /></td>
											</tr>
										</table>
									</td>
									<td>
									<ezf:skip>
										<table border="0" cellpadding="1" cellspacing="0">
											<col width="54"  align="center">
											<col width="32"  align="right">
											<col width="16"  align="center">
											<col width="32"  align="right">
											<col width="16"  align="center">
											<col width="32"  align="right">
											<col width="10">
											<col>
											<col width="1">
											<col>
											<tr>
												<td class="stab">Showing</td>
												<td class="pOut">1</td>
												<td class="stab">to</td>
												<td class="pOut">1</td>
												<td class="stab">of</td>
												<td class="pOut">1</td>
												<td>&nbsp;</td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev"></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
												<td>&nbsp;</td>
											</tr>
										</table>
									</ezf:skip>
									<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
										<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
										<jsp:param name="TableNm"     value='<%= bMsg.xxTblNm_DH.getValue() %>' />
										<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
										<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
										<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
									</jsp:include>
									</td>
								</tr>
							</table>

							<c:choose>
<%--------------------------------%>
<%-- Equipment 					--%>
<%--------------------------------%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_D1 == 'PrcListValEquip'}">
							<script type="text/javascript">
								document.getElementById( "PrcListVal" ).className = "pTab_ON";
								document.getElementById( "TrxDrv" ).className = "pTab_OFF";
							</script>

							<table>
								<tr>
									<td width="10"></td>
									<td>
										<div id="parentBoxA">
										<div style="float:left; display:block"> <!-- left table -->
											<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;">
											</div>
											<div id="leftTbl" style="float:left; display:block; height:364px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
											</div>
										</div>  <!-- left table -->
										<div style="float:left"> <!-- right table -->
											<div id="rightTblHead" style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="4635px" style="margin-right:20px">
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="60" >	<!-- Config#				-->
												<col align="center" width="170">	<!-- Config Name			-->
												<col align="center" width="180">	<!-- Qualifier Type			-->
												<col align="center" width="170">	<!-- Value					-->
												<col align="center" width="170">	<!-- Manufacture#			-->
												<col align="center" width="230">	<!-- Description			-->
												<col align="center" width="170">	<!-- Merchandise Type		-->
												<col align="center" width="170">	<!-- Item Type				-->
												<col align="center" width="170">	<!-- Prodct Code			-->
												<col align="center" width="170">	<!-- Model					-->
												<col align="center" width="170">	<!-- Zeroth Product Control Code	-->
												<col align="center" width="170">	<!-- First Product Control Code		-->
												<col align="center" width="170">	<!-- Second Product Control Code	-->
												<col align="center" width="170">	<!-- Third Product Control Code		-->
												<col align="center" width="170">	<!-- Fourth Product Control Code	-->
												<col align="center" width="120">	<!-- UOM					-->
												<col align="center" width="100">	<!-- Price					-->
												<col align="center" width="100">	<!-- Min Price				-->
												<col align="center" width="100">	<!-- Max Price				-->
												<col align="center" width="100">	<!-- Term					-->
												<col align="center" width="80" >	<!-- Term UOM				-->
												<col align="center" width="100">	<!-- Bid Qty				-->
												<col align="center" width="100">	<!-- Mothly Payment Amount	-->
												<col align="center" width="100">	<!-- Lease Payment Min		-->
												<col align="center" width="100">	<!-- Lease Payment Max		-->
												<col align="center" width="280">	<!-- Price Formula			-->
												<col align="center" width="100">	<!-- Calc Price				-->
												<col align="center" width="50" >	<!-- Open Market			-->
												<col align="center" width="100">	<!-- Quota Rev				-->
												<col align="center" width="140">	<!-- Comp Code				-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="center" width="70" >	<!-- Status					-->
												<col align="center" width="170">	<!-- Created By				-->
												<col align="center" width="100">	<!-- Created Date			-->
												<col align="center" width="170">	<!-- Last Update By			-->
												<col align="center" width="100">	<!-- Last Update Date		-->
												<col align="center" width="50" >	<!-- Qty Brk				-->
												<tr style="height:34px;">
													<td id="AH"  class="pClothBs" align="center"></td>
													<td id="AH0"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxChkBox_PA' )" tabindex="-1">Delete/<br>Update<img id="sortIMG.xxChkBox_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcListEquipConfigNum_PA' )" tabindex="-1">Config#<img id="sortIMG.prcListEquipConfigNum_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcListEquipConfigNm_PA' )" tabindex="-1">Config Name<img id="sortIMG.prcListEquipConfigNm_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcQlfyTpCd_PA' )" tabindex="-1">Qualifier Type<img id="sortIMG.prcQlfyTpCd_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcQlfyValTxt_PA' )" tabindex="-1">Value<img id="sortIMG.prcQlfyValTxt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mnfItemCd_PA' )" tabindex="-1">Manufacture#<img id="sortIMG.mnfItemCd_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescShortTxt_PA' )" tabindex="-1">Description<img id="sortIMG.mdseDescShortTxt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaProjCd_PA' )" tabindex="-1">Mdse<br>Type<img id="sortIMG.coaProjCd_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseItemTpNm_PA' )" tabindex="-1">Item<br>Type<img id="sortIMG.mdseItemTpNm_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaProdNm_PA' )" tabindex="-1">Prod<br>Code<img id="sortIMG.coaProdNm_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH10"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 't_MdlNm_PA' )" tabindex="-1">Model<img id="sortIMG.t_MdlNm_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'zerothProdCtrlCd_PA' )" tabindex="-1"><ezf:label name="mdseStruElmntTpDescTxt_T0" ezfName="mdseStruElmntTpDescTxt_T1" /><img id="sortIMG.zerothProdCtrlCd_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'firstProdCtrlCd_PA' )" tabindex="-1"><ezf:label name="mdseStruElmntTpDescTxt_T1" ezfName="mdseStruElmntTpDescTxt_T2" /><img id="sortIMG.firstProdCtrlCd_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'scdProdCtrlCd_PA' )" tabindex="-1"><ezf:label name="mdseStruElmntTpDescTxt_T2" ezfName="mdseStruElmntTpDescTxt_T3" /><img id="sortIMG.scdProdCtrlCd_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'thirdProdCtrlCd_PA' )" tabindex="-1"><ezf:label name="mdseStruElmntTpDescTxt_T3" ezfName="mdseStruElmntTpDescTxt_T4" /><img id="sortIMG.thirdProdCtrlCd_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'frthProdCtrlCd_PA' )" tabindex="-1"><ezf:label name="mdseStruElmntTpDescTxt_T4" ezfName="mdseStruElmntTpDescTxt_T5" /><img id="sortIMG.frthProdCtrlCd_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pkgUomCd_PA' )" tabindex="-1">UOM<img id="sortIMG.pkgUomCd_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcListEquipPrcAmt_PA' )" tabindex="-1">Price<img id="sortIMG.prcListEquipPrcAmt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'minPrcAmt_PA' )" tabindex="-1">Min Price<img id="sortIMG.minPrcAmt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'maxPrcAmt_PA' )" tabindex="-1">Max Price<img id="sortIMG.maxPrcAmt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcTermAot_PA' )" tabindex="-1">Term<img id="sortIMG.prcTermAot_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcTermUomCd_PA' )" tabindex="-1">Term UOM<img id="sortIMG.prcTermUomCd_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'custBidQty_PA' )" tabindex="-1">Bid Qty<img id="sortIMG.custBidQty_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mlyPmtAmt_PA' )" tabindex="-1">Mothly Payment<br>Amount<img id="sortIMG.mlyPmtAmt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'minLeasePmtAmt_PA' )" tabindex="-1">Lease Payment<br>Min<img id="sortIMG.minLeasePmtAmt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'maxLeasePmtAmt_PA' )" tabindex="-1">Lease Payment<br>Max<img id="sortIMG.maxLeasePmtAmt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcFmlaNm_PA' )" tabindex="-1">Price Formula<img id="sortIMG.prcFmlaNm_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxCalcTotPrcAmt_PA' )" tabindex="-1">Calc Price<img id="sortIMG.xxCalcTotPrcAmt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'openMktFlg_PA' )" tabindex="-1">Open<br>Market<img id="sortIMG.openMktFlg_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH29" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'quotRevAmt_PA' )" tabindex="-1">Quota Rev<img id="sortIMG.quotRevAmt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH30" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'compCd_PA' )" tabindex="-1">Comp Code<img id="sortIMG.compCd_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH31" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_PA' )" tabindex="-1">Date From<img id="sortIMG.effFromDt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH32" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_PA' )" tabindex="-1">Date To<img id="sortIMG.effThruDt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH33" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrStsTxt_A1' )" tabindex="-1">Status<img id="sortIMG.xxScrStsTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH34" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxFullNm_A1' )" tabindex="-1">Created By<img id="sortIMG.xxFullNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH35" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cratDt_PA' )" tabindex="-1">Created Date<img id="sortIMG.cratDt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH36" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxFullNm_A1' )" tabindex="-1">Update By<img id="sortIMG.xxFullNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH37" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'lastUpdDt_PA' )" tabindex="-1">Update Date<img id="sortIMG.lastUpdDt_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH38" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxYesNoCd_PA' )" tabindex="-1">Qty Brk<img id="sortIMG.xxYesNoCd_PA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
												</table>
											</div>
											<div id="rightTbl" style="width:1097px; height:381px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="4635px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="60" >	<!-- Config#				-->
												<col align="center" width="170">	<!-- Config Name			-->
												<col align="center" width="180">	<!-- Qualifier Type			-->
												<col align="center" width="170">	<!-- Value					-->
												<col align="left"   width="170">	<!-- Manufacture#			-->
												<col align="left"   width="230">	<!-- Description			-->
												<col align="left"   width="170">	<!-- Merchandise Type		-->
												<col align="left"   width="170">	<!-- Item Type				-->
												<col align="left"   width="170">	<!-- Prodct Code			-->
												<col align="left"   width="170">	<!-- Model					-->
												<col align="left"   width="170">	<!-- Zeroth Product Control Code	-->
												<col align="left"   width="170">	<!-- First Product Control Code		-->
												<col align="left"   width="170">	<!-- Second Product Control Code	-->
												<col align="left"   width="170">	<!-- Third Product Control Code		-->
												<col align="left"   width="170">	<!-- Fourth Product Control Code	-->
												<col align="center" width="120">	<!-- UOM					-->
												<col align="center" width="100">	<!-- Price					-->
												<col align="center" width="100">	<!-- Min Price				-->
												<col align="center" width="100">	<!-- Max Price				-->
												<col align="center" width="100">	<!-- Term					-->
												<col align="center" width="80" >	<!-- Term UOM				-->
												<col align="center" width="100">	<!-- Bid Qty				-->
												<col align="center" width="100">	<!-- Mothly Payment Amount	-->
												<col align="center" width="100">	<!-- Lease Payment Min		-->
												<col align="center" width="100">	<!-- Lease Payment Max		-->
												<col align="left"   width="280">	<!-- Price Formula			-->
												<col align="right"  width="100">	<!-- Calc Price				-->
												<col align="center" width="50" >	<!-- Open Market			-->
												<col align="center" width="100">	<!-- Quota Rev				-->
												<col align="center" width="140">	<!-- Comp Code				-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="left"   width="70" >	<!-- Status					-->
												<col align="left"   width="170">	<!-- Created By				-->
												<col align="left"   width="100">	<!-- Created Date			-->
												<col align="left"   width="170">	<!-- Last Update By			-->
												<col align="left"   width="100">	<!-- Last Update Date		-->
												<col align="center" width="50" >	<!-- Qty Brk				 -->
												<ezf:row ezfHyo="A">
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><ezf:inputCheckBox name="xxChkBox_SA" ezfName="xxChkBox_SA" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_SA#{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputCheckBox name="xxChkBox_PA" ezfName="xxChkBox_PA" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_PA#{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputText name="prcListEquipConfigNum_PA" ezfName="prcListEquipConfigNum_PA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
													<td><ezf:inputText name="prcListEquipConfigNm_PA" ezfName="prcListEquipConfigNm_PA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
													<td>
														<ezf:select name="prcQlfyTpCd_PA" ezfName="prcQlfyTpCd_PA" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcQlfyTpCd_LA" ezfDispName="prcQlfyTpDescTxt_LA" otherEvent1=" onchange=\"sendServerForPreferredView('OnChange_PrcQlfyTp', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 160px\""/>
													</td>
													<td>
														<ezf:inputText name="prcQlfyValTxt_PA" ezfName="prcQlfyValTxt_PA" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnBlur_OpenWin_QualifierValueItem" otherAttr=" size=\"15\" maxlength=\"50\" ezffocusout=\"OnBlur_OpenWin_QualifierValueItem\""/>
														<ezf:inputButton name="OpenWin_QualifierValueItem" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_QualifierValueItem#{EZF_ROW_NUMBER}\""/>
														<ezf:inputButton name="OpenWin_QualifierValueProd" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_QualifierValueProd#{EZF_ROW_NUMBER}\""/>
														<ezf:inputButton name="OpenWin_QualifierValueMdseTp" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_QualifierValueMdseTp#{EZF_ROW_NUMBER}\""/>
													</td>
													<td><span><ezf:label name="mnfItemCd_PA" ezfName="mnfItemCd_PA" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="mdseDescShortTxt_PA" ezfName="mdseDescShortTxt_PA" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="coaProjNm_PA" ezfName="coaProjNm_PA" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="mdseItemTpNm_PA" ezfName="mdseItemTpNm_PA" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="coaProdNm_PA" ezfName="coaProdNm_PA" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="t_MdlNm_PA" ezfName="t_MdlNm_PA" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxScrItem61Txt_P0" ezfName="xxScrItem61Txt_P0" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxScrItem61Txt_P1" ezfName="xxScrItem61Txt_P1" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxScrItem61Txt_P2" ezfName="xxScrItem61Txt_P2" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxScrItem61Txt_P3" ezfName="xxScrItem61Txt_P3" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxScrItem61Txt_P4" ezfName="xxScrItem61Txt_P4" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td>
														<ezf:select name="pkgUomCd_PA" ezfName="pkgUomCd_PA" ezfHyo="A" ezfArrayNo="0" ezfCodeName="pkgUomCd_LA" ezfDispName="pkgUomDescTxt_LA" ezfCodeDispHyo="A" otherAttr=" style=\"width:110px\""/>
													</td>
													<td><ezf:inputText name="prcListEquipPrcAmt_PA" ezfName="prcListEquipPrcAmt_PA" value="-123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="minPrcAmt_PA" ezfName="minPrcAmt_PA" value="-123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="maxPrcAmt_PA" ezfName="maxPrcAmt_PA" value="-123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="prcTermAot_PA" ezfName="prcTermAot_PA" value="1,234,567" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"checkTerm(this,'#{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"9\" maxlength=\"9\" style=\"text-align:right\""/></td>
													<td>
														<ezf:select name="prcTermUomCd_PA" ezfName="prcTermUomCd_PA" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcTermUomCd_LA" ezfDispName="prcTermUomDescTxt_LA" otherAttr=" style=\"width: 70px\" id=\"prcTermUomCd_PA#{EZF_ROW_NUMBER}\""/>
													</td>
													<td><ezf:inputText name="custBidQty_PA" ezfName="custBidQty_PA" value="1,234,567" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"7\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="mlyPmtAmt_PA" ezfName="mlyPmtAmt_PA" value="-123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="minLeasePmtAmt_PA" ezfName="minLeasePmtAmt_PA" value="-123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="maxLeasePmtAmt_PA" ezfName="maxLeasePmtAmt_PA" value="-123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td>
														<ezf:inputText name="prcFmlaPk_PA" ezfName="prcFmlaPk_PA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\""/>
														<span class="pkNm"><ezf:label name="prcFmlaNm_PA" ezfName="prcFmlaNm_PA" ezfHyo="A" ezfArrayNo="0" /></span>
														<ezf:inputButton name="OpenWin_PrcFormula" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcFormula#{EZF_ROW_NUMBER}\""/>
													</td>
													<td><span><ezf:label name="xxCalcTotPrcAmt_PA" ezfName="xxCalcTotPrcAmt_PA" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><ezf:inputCheckBox name="openMktFlg_PA" ezfName="openMktFlg_PA" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="quotRevAmt_PA" ezfName="quotRevAmt_PA" value="-123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="compCd_PA" ezfName="compCd_PA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
													<td>
														<ezf:inputText name="effFromDt_PA" ezfName="effFromDt_PA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_PA', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td>
														<ezf:inputText name="effThruDt_PA" ezfName="effThruDt_PA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_PA', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td><span><ezf:label name="xxScrStsTxt_A1" ezfName="xxScrStsTxt_A1" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_A1" ezfName="xxFullNm_A1" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="cratDt_PA" ezfName="cratDt_PA" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_A2" ezfName="xxFullNm_A2" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><span><ezf:label name="lastUpdDt_PA" ezfName="lastUpdDt_PA" ezfHyo="A" ezfArrayNo="0" /></span></td>
													<td class="pOut">
														<ezf:anchor name="xxYesNoCd_PA" ezfName="xxYesNoCd_PA" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_QtyDisc" otherAttr=" id=\"xxYesNoCd_PA#{EZF_ROW_NUMBER}\"">
															<ezf:label name="xxYesNoCd_PA" ezfName="xxYesNoCd_PA" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
											</div>
										</div> <!-- right table -->
										</div> <!-- parent box -->
									</td>
								</tr>
							</table>

							<script type="text/javascript" defer>
							    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, false, true);
							</script>
							</c:when>

<%--------------------------------%>
<%-- Service					--%>
<%--------------------------------%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_D1 == 'PrcListValService'}">
							<script type="text/javascript">
								document.getElementById( "PrcListVal" ).className = "pTab_ON";
								document.getElementById( "TrxDrv" ).className = "pTab_OFF";
							</script>

							<table>
								<tr>
									<td width="10"></td>
									<td>
										<div id="parentBoxB">
										<div style="float:left; display:block"> <!-- left table -->
											<div id="leftTblHead" style="display:block;">
											</div>
											<div id="leftTbl" style="float:left; display:block; height:364px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
											</div>
										</div>  <!-- left table -->
										<div style="float:left"> <!-- right table -->
											<div id="rightTblHead" style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0"  id="BHEAD" width="4365px" style="margin-right:20px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="95" >	<!-- Rate Type				-->
												<col align="center" width="210">	<!-- Service Model Name		-->
												<col align="center" width="210">	<!-- Meter Package Name		-->
												<col align="center" width="170">	<!-- Item#					-->
												<col align="center" width="230">	<!-- Item Description		-->
												<col align="center" width="100">	<!-- Plan Type				-->
												<col align="center" width="160">	<!-- Contract Type			-->
												<col align="center" width="230">	<!-- Meter Type				-->
												<col align="center" width="100">	<!-- Min Vol				-->
												<col align="center" width="100">	<!-- Max Vol				-->
												<col align="center" width="100" >	<!-- Band					-->
												<col align="center" width="110">	<!-- Base Amount			-->
												<col align="center" width="100">	<!-- Min Base				-->
												<col align="center" width="100">	<!-- Max Base				-->
												<col align="center" width="100">	<!-- Cost Per Copy(OVERAGE)	-->
												<col align="center" width="100">	<!-- Min CPC				-->
												<col align="center" width="100">	<!-- Max CPC				-->
												<col align="center" width="80" >	<!-- Term From(MTH)			-->
												<col align="center" width="80" >	<!-- Term To(MTH)			-->
												<col align="center" width="170">	<!-- Service Zone(s) Included-->
												<col align="center" width="170">	<!-- Service Item			-->
												<col align="center" width="230">	<!-- Service Item Description-->
												<col align="center" width="170">	<!-- Billing Counter Name	-->
												<col align="center" width="100">	<!-- Quota Rev				-->
												<col align="center" width="140">	<!-- Comp Code				-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="center" width="70" >	<!-- Status					-->
												<col align="center" width="170">	<!-- Created By				-->
												<col align="center" width="100">	<!-- Created Date			-->
												<col align="center" width="170">	<!-- Last Update By			-->
												<col align="center" width="100">	<!-- Last Update Date		-->
												<tr style="height:34px;">
													<td id="BH"  class="pClothBs" align="center"></td>
													<td id="BH0"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxChkBox_PB' )" tabindex="-1">Delete/<br>Update<img id="sortIMG.xxChkBox_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRateTpCd_PB' )" tabindex="-1">Rate Type<img id="sortIMG.prcRateTpCd_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdlNm_PB' )" tabindex="-1">Service Model Name<img id="sortIMG.mdlNm_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcMtrPkgNm_PB' )" tabindex="-1">Meter Package Name<img id="sortIMG.prcMtrPkgNm_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcListMdseCd_PB' )" tabindex="-1">Item#<img id="sortIMG.prcListMdseCd_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdseDescShortTxt_PB' )" tabindex="-1">Item Description<img id="sortIMG.mdseDescShortTxt_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcSvcPlnTpCd_PB' )" tabindex="-1">Plan Type<img id="sortIMG.prcSvcPlnTpCd_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcSvcContrTpCd_PB' )" tabindex="-1">Contract Type<img id="sortIMG.prcSvcContrTpCd_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mtrLbNm_PB' )" tabindex="-1">Meter Type<img id="sortIMG.mtrLbNm_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'minCopyVolCnt_PB' )" tabindex="-1">Min Vol<img id="sortIMG.minCopyVolCnt_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'maxCopyVolCnt_PB' )" tabindex="-1">Max Vol<img id="sortIMG.maxCopyVolCnt_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcListBandDescTxt_PB' )" tabindex="-1">Band<img id="sortIMG.prcListBandDescTxt_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'baseAmt_PB' )" tabindex="-1">Base Amount<img id="sortIMG.baseAmt_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'minBaseAmt_PB' )" tabindex="-1">Min Base<img id="sortIMG.minBaseAmt_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'maxBaseAmt_PB' )" tabindex="-1">Max Base<img id="sortIMG.maxBaseAmt_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'cpcAmtRate_PB' )" tabindex="-1">Cost Per Copy<br>OVERAGE<img id="sortIMG.cpcAmtRate_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'minCpcAmtRate_PB' )" tabindex="-1">Min CPC<img id="sortIMG.minCpcAmtRate_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'maxCpcAmtRate_PB' )" tabindex="-1">Max CPC<img id="sortIMG.maxCpcAmtRate_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'termFromMthAot_PB' )" tabindex="-1">Term From(MTH)<img id="sortIMG.termFromMthAot_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'termThruMthAot_PB' )" tabindex="-1">Term To(MTH)<img id="sortIMG.termThruMthAot_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcSvcZoneCd_PB' )" tabindex="-1">Service Zone(s) Included<img id="sortIMG.prcSvcZoneCd_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdseCd_PB' )" tabindex="-1">Service Item<img id="sortIMG.mdseCd_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdseDescShortTxt_PZ' )" tabindex="-1">Service Item Description<img id="sortIMG.mdseDescShortTxt_PZ" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mtrLbDescTxt_PB' )" tabindex="-1">Billing Counter Name<img id="sortIMG.mtrLbDescTxt_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'quotRevAmt_PB' )" tabindex="-1">Quota Rev<img id="sortIMG.quotRevAmt_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'compCd_PB' )" tabindex="-1">Comp Code<img id="sortIMG.compCd_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'effFromDt_PB' )" tabindex="-1">Date From<img id="sortIMG.effFromDt_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'effThruDt_PB' )" tabindex="-1">Date To<img id="sortIMG.effThruDt_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxScrStsTxt_B1' )" tabindex="-1">Status<img id="sortIMG.xxScrStsTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH29" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxFullNm_B1' )" tabindex="-1">Created By<img id="sortIMG.xxFullNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH30" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'cratDt_PB' )" tabindex="-1">Created Date<img id="sortIMG.cratDt_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH31" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxFullNm_B2' )" tabindex="-1">Update By<img id="sortIMG.xxFullNm_B2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="BH32" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'lastUpdDt_PB' )" tabindex="-1">Update Date<img id="sortIMG.lastUpdDt_PB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
												</table>
											</div>
											<div id="rightTbl" style="width:1097px; height:381px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="B" width="4365px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="95" >	<!-- Rate Type				-->
												<col align="center" width="210">	<!-- Service Model Name		-->
												<col align="center" width="210">	<!-- Meter Package Name		-->
												<col align="center" width="170">	<!-- Item#					-->
												<col align="left"   width="230">	<!-- Item Description		-->
												<col align="center" width="100">	<!-- Plan Type				-->
												<col align="center" width="160">	<!-- Contract Type			-->
												<col align="center" width="230">	<!-- Meter Type				-->
												<col align="center" width="100">	<!-- Min Vol				-->
												<col align="center" width="100">	<!-- Max Vol				-->
												<col align="center" width="100" >	<!-- Band					-->
												<col align="center" width="110">	<!-- Base Amount			-->
												<col align="center" width="100">	<!-- Min Base				-->
												<col align="center" width="100">	<!-- Max Base				-->
												<col align="center" width="100">	<!-- Cost Per Copy(OVERAGE)-->
												<col align="center" width="100">	<!-- Min CPC				-->
												<col align="center" width="100">	<!-- Max CPC				-->
												<col align="center" width="80" >	<!-- Term From(MTH)			-->
												<col align="center" width="80" >	<!-- Term To(MTH)			-->
												<col align="center" width="170">	<!-- Service Zone(s) Included-->
												<col align="center" width="170">	<!-- Service Item			-->
												<col align="left"   width="230">	<!-- Service Item Description-->
												<col align="left"   width="170">	<!-- Billing Counter Name	-->
												<col align="center" width="100">	<!-- Quota Rev				-->
												<col align="center" width="140">	<!-- Comp Code				-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="left"   width="70" >	<!-- Status					-->
												<col align="left"   width="170">	<!-- Created By				-->
												<col align="left"   width="100">	<!-- Created Date			-->
												<col align="left"   width="170">	<!-- Last Update By			-->
												<col align="left"   width="100">	<!-- Last Update Date		-->
												<ezf:row ezfHyo="B">
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><ezf:inputCheckBox name="xxChkBox_SB" ezfName="xxChkBox_SB" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_SB#{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputCheckBox name="xxChkBox_PB" ezfName="xxChkBox_PB" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_PB#{EZF_ROW_NUMBER}\""/></td>
													<td>
														<ezf:select name="prcRateTpCd_PB" ezfName="prcRateTpCd_PB" ezfHyo="B" ezfArrayNo="0" ezfCodeName="prcRateTpCd_LB" ezfDispName="prcRateTpDescTxt_LB" otherEvent1=" onchange=\"sendServerForPreferredView('OnChange_RateType', '{EZF_ROW_NUMBER}');\"" otherAttr=" style=\"width: 88px\""/>
													</td>
													<td>
														<ezf:inputText name="mdlNm_PB" ezfName="mdlNm_PB" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
														<ezf:inputButton name="OpenWin_Model" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Model#{EZF_ROW_NUMBER}\""/>
													</td>
													<td>
														<ezf:inputText name="prcMtrPkgNm_PB" ezfName="prcMtrPkgNm_PB" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
														<ezf:inputButton name="OpenWin_MtrPkg" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MtrPkg#{EZF_ROW_NUMBER}\""/>
													</td>
													<td>
														<ezf:inputText name="prcListMdseCd_PB" ezfName="prcListMdseCd_PB" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_OpenWin_PrcListMdse" otherAttr=" size=\"16\" maxlength=\"16\" ezffocusout=\"OnBlur_OpenWin_PrcListMdse\""/>
														<ezf:inputButton name="OpenWin_PrcListMdse" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcListMdse#{EZF_ROW_NUMBER}\""/>
													</td>
													<td><span><ezf:label name="mdseDescShortTxt_PB" ezfName="mdseDescShortTxt_PB" ezfHyo="B" ezfArrayNo="0" /></span></td>
													<td>
														<ezf:select name="prcSvcPlnTpCd_PB" ezfName="prcSvcPlnTpCd_PB" ezfHyo="B" ezfArrayNo="0" ezfCodeName="prcSvcPlnTpCd_LB" ezfDispName="prcSvcPlnTpDescTxt_LB" otherAttr=" style=\"width: 80px\""/>
													</td>
													<td>
														<ezf:select name="prcSvcContrTpCd_PB" ezfName="prcSvcContrTpCd_PB" ezfHyo="B" ezfArrayNo="0" ezfCodeName="prcSvcContrTpCd_LB" ezfDispName="prcSvcContrTpDescTxt_LB" otherAttr=" style=\"width: 140px\""/>
													</td>
													<td>
                                                        <ezf:inputText name="mtrLbNm_PB" ezfName="mtrLbNm_PB" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"24\""/>
                                                        <ezf:inputButton name="OpenWin_MtrLb" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" />
														<!--<select style="width: 210px" name="mtrLbCd_PB" ezfname="mtrLbCd_PB" ezflist="mtrLbCd_LB, mtrLbDescTxt_LB,99, ,noblank" ezfHyo="B">-->
														<!--	<option selected>XXXXX</option>-->
														<!--	<option>XXXXXXXXXX</option>-->
														<!--</select>-->
													</td>
													<td><ezf:inputText name="minCopyVolCnt_PB" ezfName="minCopyVolCnt_PB" value="123,456,789,000" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"11\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="maxCopyVolCnt_PB" ezfName="maxCopyVolCnt_PB" value="123,456,789,000" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"11\" style=\"text-align:right\""/></td>
													<td>
														<ezf:inputText name="prcListBandDescTxt_PB" ezfName="prcListBandDescTxt_PB" value="BAND" ezfHyo="B" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"5\""/>
                                                        <ezf:inputButton name="OpenWin_PrcListBand" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" />
														<!--<select style="width: 50px" name="prcListBandCd_PB" ezfname="prcListBandCd_PB" ezflist="prcListBandCd_LB, prcListBandDescTxt_LB,99, ,blank" ezfhyo="B">-->
														<!--	<option selected>XXX</option>-->
														<!--	<option>XXXXXXXXXX</option>-->
														<!--</select>-->
													</td>
													<td><ezf:inputText name="baseAmt_PB" ezfName="baseAmt_PB" value="-123,456,789.12" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="minBaseAmt_PB" ezfName="minBaseAmt_PB" value="-123,456,789.12" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="maxBaseAmt_PB" ezfName="maxBaseAmt_PB" value="-123,456,789.12" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="cpcAmtRate_PB" ezfName="cpcAmtRate_PB" value="-123,456,789.12" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"7\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="minCpcAmtRate_PB" ezfName="minCpcAmtRate_PB" value="-123,456,789.12" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"7\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="maxCpcAmtRate_PB" ezfName="maxCpcAmtRate_PB" value="-123,456,789.12" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"7\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="termFromMthAot_PB" ezfName="termFromMthAot_PB" value="123" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="termThruMthAot_PB" ezfName="termThruMthAot_PB" value="123" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right\""/></td>
													<td>
														<ezf:select name="prcSvcZoneCd_PB" ezfName="prcSvcZoneCd_PB" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcSvcZoneCd_LB" ezfDispName="prcSvcZoneDescTxt_LB" otherAttr=" style=\"width: 150px\""/>
													</td>
													<td>
														<ezf:inputText name="mdseCd_PB" ezfName="mdseCd_PB" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_OpenWin_Mdse" otherAttr=" size=\"16\" maxlength=\"16\" ezffocusout=\"OnBlur_OpenWin_Mdse\""/>
														<ezf:inputButton name="OpenWin_Mdse" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Mdse#{EZF_ROW_NUMBER}\""/>
													</td>
													<td><span><ezf:label name="mdseDescShortTxt_PZ" ezfName="mdseDescShortTxt_PZ" ezfHyo="B" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="mtrLbDescTxt_PB" ezfName="mtrLbDescTxt_PB" ezfHyo="B" ezfArrayNo="0" /></span></td>
													<td><ezf:inputText name="quotRevAmt_PB" ezfName="quotRevAmt_PB" value="-123,456,789.12" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="compCd_PB" ezfName="compCd_PB" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
													<td>
														<ezf:inputText name="effFromDt_PB" ezfName="effFromDt_PB" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_PB', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td>
														<ezf:inputText name="effThruDt_PB" ezfName="effThruDt_PB" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_PB', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td><span><ezf:label name="xxScrStsTxt_B1" ezfName="xxScrStsTxt_B1" ezfHyo="B" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_B1" ezfName="xxFullNm_B1" ezfHyo="B" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="cratDt_PB" ezfName="cratDt_PB" ezfHyo="B" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_B2" ezfName="xxFullNm_B2" ezfHyo="B" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="lastUpdDt_PB" ezfName="lastUpdDt_PB" ezfHyo="B" ezfArrayNo="0" /></span></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
											</div>
										</div> <!-- right table -->
										</div> <!-- parentBoxB -->
									</td>
								</tr>
							</table>
							<script type="text/javascript" defer>
							    S21TableUI.initialize("parentBoxB", "BHEAD", "B", -1, false, true);
							</script>
							</c:when>

<%--------------------------------%>
<%-- Service Tiers				--%>
<%--------------------------------%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_D1 == 'PrcListValServiceTiers'}">
							<script type="text/javascript">
								document.getElementById( "PrcListVal" ).className = "pTab_ON";
								document.getElementById( "TrxDrv" ).className = "pTab_OFF";
							</script>

							<table>
								<tr>
									<td width="10"></td>
									<td>
										<div id="parentBoxC">
										<div style="float:left; display:block"> <!-- left table -->
											<div id="leftTblHead" style="display:block;">
											</div>
											<div id="leftTbl" style="float:left; display:block; height:364px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
											</div>
										</div>  <!-- left table -->
										<div style="float:left"> <!-- right table -->
											<div id="rightTblHead" style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0"  id="CHEAD" width="3640px" style="margin-right:20px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="210">	<!-- Model Name				-->
												<col align="center" width="90" >	<!-- Tier Type				-->
												<col align="center" width="210">	<!-- Meter Package Name		-->
												<col align="center" width="100">	<!-- Plan Type				-->
												<col align="center" width="160">	<!-- Service Program		-->
												<col align="center" width="230">	<!-- Meter Type				-->
												<col align="center" width="100">	<!-- Ave Copy Volume		-->
												<col align="center" width="100">	<!-- Min Vol				-->
												<col align="center" width="100">	<!-- Max Vol				-->
												<col align="center" width="100" >	<!-- Band					-->
												<col align="center" width="100">	<!-- Base Amount			-->
												<col align="center" width="100">	<!-- Min Base				-->
												<col align="center" width="100">	<!-- Max Base				-->
												<col align="center" width="100">	<!-- Cost Per Copy(OVERAGE)-->
												<col align="center" width="100">	<!-- Min CPC				-->
												<col align="center" width="100">	<!-- Max CPC				-->
												<col align="center" width="80" >	<!-- Term From(MTH)			-->
												<col align="center" width="80" >	<!-- Term To(MTH)			-->
												<col align="center" width="170">	<!-- Service Item			-->
												<col align="center" width="230">	<!-- Service Item Description-->
												<col align="center" width="170">	<!-- Billing Counter Name	-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="center" width="70" >	<!-- Status					-->
												<col align="center" width="170">	<!-- Created By				-->
												<col align="center" width="100">	<!-- Created Date			-->
												<col align="center" width="170">	<!-- Last Update By			-->
												<col align="center" width="100">	<!-- Last Update Date		-->
												<tr style="height:34px;">
													<td id="CH"  class="pClothBs" align="center"></td>
													<td id="CH0"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxChkBox_PC' )" tabindex="-1">Delete/<br>Update<img id="sortIMG.xxChkBox_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'mdlNm_PC' )" tabindex="-1">Model Name<img id="sortIMG.mdlNm_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'prcSvcTierTpCd_PC' )" tabindex="-1">Tier Type<img id="sortIMG.prcSvcTierTpCd_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'prcMtrPkgNm_PC' )" tabindex="-1">Meter Package Name<img id="sortIMG.prcMtrPkgNm_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'prcSvcPlnTpCd_PC' )" tabindex="-1">Plan Type<img id="sortIMG.prcSvcPlnTpCd_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'prcSvcContrTpCd_PC' )" tabindex="-1">Service Program<img id="sortIMG.prcSvcContrTpCd_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'mtrLbNm_PC' )" tabindex="-1">Meter Type<img id="sortIMG.mtrLbNm_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'avgCopyVolCnt_PC' )" tabindex="-1">Ave Copy Volume<img id="sortIMG.avgCopyVolCnt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'minCopyVolCnt_PC' )" tabindex="-1">Min Vol<img id="sortIMG.minCopyVolCnt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'maxCopyVolCnt_PC' )" tabindex="-1">Max Vol<img id="sortIMG.maxCopyVolCnt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'prcListBandDescTxt_PC' )" tabindex="-1">Band<img id="sortIMG.prcListBandDescTxt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'baseAmt_PC' )" tabindex="-1">Base Amount<img id="sortIMG.baseAmt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'minBaseAmt_PC' )" tabindex="-1">Min Base<img id="sortIMG.minBaseAmt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'maxBaseAmt_PC' )" tabindex="-1">Max Base<img id="sortIMG.maxBaseAmt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'cpcAmtRate_PC' )" tabindex="-1">Cost Per Copy<br>OVERAGE<img id="sortIMG.cpcAmtRate_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'minCpcAmtRate_PC' )" tabindex="-1">Min CPC<img id="sortIMG.minCpcAmtRate_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'maxCpcAmtRate_PC' )" tabindex="-1">Max CPC<img id="sortIMG.maxCpcAmtRate_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'termFromMthAot_PC' )" tabindex="-1">Term From(MTH)<img id="sortIMG.termFromMthAot_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'termThruMthAot_PC' )" tabindex="-1">Term To(MTH)<img id="sortIMG.termThruMthAot_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'mdseCd_PC' )" tabindex="-1">Service Item<img id="sortIMG.mdseCd_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'mdseDescShortTxt_PC' )" tabindex="-1">Service Item Description<img id="sortIMG.mdseDescShortTxt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'mtrLbDescTxt_PC' )" tabindex="-1">Billing Counter Name<img id="sortIMG.mtrLbDescTxt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'effFromDt_PC' )" tabindex="-1">Date From<img id="sortIMG.effFromDt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'effThruDt_PC' )" tabindex="-1">Date To<img id="sortIMG.effThruDt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxScrStsTxt_C1' )" tabindex="-1">Status<img id="sortIMG.xxScrStsTxt_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxFullNm_C1' )" tabindex="-1">Created By<img id="sortIMG.xxFullNm_C1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'cratDt_PC' )" tabindex="-1">Created Date<img id="sortIMG.cratDt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'xxFullNm_C2' )" tabindex="-1">Update By<img id="sortIMG.xxFullNm_C2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="CH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'C', 'lastUpdDt_PC' )" tabindex="-1">Update Date<img id="sortIMG.lastUpdDt_PC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
												</table>
											</div>
											<div id="rightTbl" style="width:1097px; height:381px; display:block; overflow:scroll; margin:0px; padding:0px;"  >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="C" width="3640px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="210">	<!-- Model Name				-->
												<col align="center" width="90" >	<!-- Tier Type				-->
												<col align="center" width="210">	<!-- Meter Package Name		-->
												<col align="center" width="100">	<!-- Plan Type				-->
												<col align="center" width="160">	<!-- Service Program		-->
												<col align="center" width="230">	<!-- Meter Type				-->
												<col align="center" width="100">	<!-- Ave Copy Volume		-->
												<col align="center" width="100">	<!-- Min Vol				-->
												<col align="center" width="100">	<!-- Max Vol				-->
												<col align="center" width="100" >	<!-- Band					-->
												<col align="center" width="100">	<!-- Base Amount			-->
												<col align="center" width="100">	<!-- Min Base				-->
												<col align="center" width="100">	<!-- Max Base				-->
												<col align="center" width="100">	<!-- Cost Per Copy(OVERAGE)-->
												<col align="center" width="100">	<!-- Min CPC				-->
												<col align="center" width="100">	<!-- Max CPC				-->
												<col align="center" width="80" >	<!-- Term From(MTH)			-->
												<col align="center" width="80" >	<!-- Term To(MTH)			-->
												<col align="center" width="170">	<!-- Service Item			-->
												<col align="left"   width="230">	<!-- Service Item Description-->
												<col align="left"   width="170">	<!-- Billing Counter Name	-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="left"   width="70" >	<!-- Status					-->
												<col align="left"   width="170">	<!-- Created By				-->
												<col align="left"   width="100">	<!-- Created Date			-->
												<col align="left"   width="170">	<!-- Last Update By			-->
												<col align="left"   width="100">	<!-- Last Update Date		-->
												<ezf:row ezfHyo="C">
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><ezf:inputCheckBox name="xxChkBox_SC" ezfName="xxChkBox_SC" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_SC#{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputCheckBox name="xxChkBox_PC" ezfName="xxChkBox_PC" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_PC#{EZF_ROW_NUMBER}\""/></td>
													<td>
														<ezf:inputText name="mdlNm_PC" ezfName="mdlNm_PC" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
														<ezf:inputButton name="OpenWin_Model" value="..." ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Model#{EZF_ROW_NUMBER}\""/>
													</td>
													<td>
														<ezf:select name="prcSvcTierTpCd_PC" ezfName="prcSvcTierTpCd_PC" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcSvcTierTpCd_LC" ezfDispName="prcSvcTierTpDescTxt_LC" otherAttr=" style=\"width: 70px\""/>
													</td>
													<td>
														<ezf:inputText name="prcMtrPkgNm_PC" ezfName="prcMtrPkgNm_PC" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
														<ezf:inputButton name="OpenWin_MtrPkg" value="..." ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MtrPkg#{EZF_ROW_NUMBER}\""/>
													</td>
													<td>
														<ezf:select name="prcSvcPlnTpCd_PC" ezfName="prcSvcPlnTpCd_PC" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcSvcPlnTpCd_LC" ezfDispName="prcSvcPlnTpDescTxt_LC" otherAttr=" style=\"width: 80px\""/>
													</td>
													<td>
														<ezf:select name="prcSvcContrTpCd_PC" ezfName="prcSvcContrTpCd_PC" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcSvcContrTpCd_LC" ezfDispName="prcSvcContrTpDescTxt_LC" otherAttr=" style=\"width: 140px\""/>
													</td>
													<td>
                                                        <ezf:inputText name="mtrLbNm_PC" ezfName="mtrLbNm_PC" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"24\""/>
                                                        <ezf:inputButton name="OpenWin_MtrLb" value="..." ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn1" />
														<!--<select style="width: 210px" name="mtrLbCd_PC" ezfname="mtrLbCd_PC" ezflist="mtrLbCd_LC, mtrLbDescTxt_LC,99, ,blank" ezfHyo="C">-->
														<!--	<option selected>XXXXX</option>-->
														<!--	<option>XXXXXXXXXX</option>-->
														<!--</select>-->
													</td>
													<td><ezf:inputText name="avgCopyVolCnt_PC" ezfName="avgCopyVolCnt_PC" value="123,456,789,000" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"11\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="minCopyVolCnt_PC" ezfName="minCopyVolCnt_PC" value="123,456,789,000" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"11\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="maxCopyVolCnt_PC" ezfName="maxCopyVolCnt_PC" value="123,456,789,000" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"11\" style=\"text-align:right\""/></td>
													<td>
														<ezf:inputText name="prcListBandDescTxt_PC" ezfName="prcListBandDescTxt_PC" value="BNAD" ezfHyo="C" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"5\""/>
                                                        <ezf:inputButton name="OpenWin_PrcListBand" value="..." ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn1" />
														<!--<select style="width: 50px" name="prcListBandCd_PC" ezfname="prcListBandCd_PC" ezflist="prcListBandCd_LC, prcListBandDescTxt_LC,99, ,blank" ezfHyo="C">-->
														<!--	<option selected>XXXXX</option>-->
														<!--	<option>XXXXXXXXXX</option>-->
														<!--</select>-->
													</td>
													<td><ezf:inputText name="baseAmt_PC" ezfName="baseAmt_PC" value="-123,456,789.12" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="minBaseAmt_PC" ezfName="minBaseAmt_PC" value="-123,456,789.12" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="maxBaseAmt_PC" ezfName="maxBaseAmt_PC" value="-123,456,789.12" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="cpcAmtRate_PC" ezfName="cpcAmtRate_PC" value="-123,456,789.12" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"7\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="minCpcAmtRate_PC" ezfName="minCpcAmtRate_PC" value="-123,456,789.12" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"7\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="maxCpcAmtRate_PC" ezfName="maxCpcAmtRate_PC" value="-123,456,789.12" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"7\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="termFromMthAot_PC" ezfName="termFromMthAot_PC" value="123" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="termThruMthAot_PC" ezfName="termThruMthAot_PC" value="123" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right\""/></td>
													<td>
														<ezf:inputText name="mdseCd_PC" ezfName="mdseCd_PC" ezfHyo="C" ezfArrayNo="0" otherEvent1="OnBlur_OpenWin_Mdse" otherAttr=" size=\"16\" maxlength=\"16\" ezffocusout=\"OnBlur_OpenWin_Mdse\""/>
														<ezf:inputButton name="OpenWin_Mdse" value="..." ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Mdse#{EZF_ROW_NUMBER}\""/>
													</td>
													<td><span><ezf:label name="mdseDescShortTxt_PC" ezfName="mdseDescShortTxt_PC" ezfHyo="C" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="mtrLbDescTxt_PC" ezfName="mtrLbDescTxt_PC" ezfHyo="C" ezfArrayNo="0" /></span></td>
													<td>
														<ezf:inputText name="effFromDt_PC" ezfName="effFromDt_PC" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_PC', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td>
														<ezf:inputText name="effThruDt_PC" ezfName="effThruDt_PC" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_PC', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td><span><ezf:label name="xxScrStsTxt_C1" ezfName="xxScrStsTxt_C1" ezfHyo="C" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_C1" ezfName="xxFullNm_C2" ezfHyo="C" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="cratDt_PC" ezfName="cratDt_PC" ezfHyo="C" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_C2" ezfName="xxFullNm_C2" ezfHyo="C" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="lastUpdDt_PC" ezfName="lastUpdDt_PC" ezfHyo="C" ezfArrayNo="0" /></span></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
											</div>
										</div> <!-- right table -->
										</div> <!-- parentBoxC -->
									</td>
								</tr>
							</table>
							<script type="text/javascript" defer>
							    S21TableUI.initialize("parentBoxC", "CHEAD", "C", -1, false, true);
							</script>
							</c:when>

<%--------------------------------%>
<%-- Service Special Charges	--%>
<%--------------------------------%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_D1 == 'PrcListValServiceSpecialCharges'}">
							<script type="text/javascript">
								document.getElementById( "PrcListVal" ).className = "pTab_ON";
								document.getElementById( "TrxDrv" ).className = "pTab_OFF";
							</script>

							<table>
								<tr>
									<td width="10"></td>
									<td>
										<div id="parentBoxD">
										<div style="float:left; display:block"> <!-- left table -->
											<div id="leftTblHead" style="display:block;">
											</div>
											<div id="leftTbl" style="float:left; display:block; height:364px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
											</div>
										</div>  <!-- left table -->
										<div style="float:left"> <!-- right table -->
											<div id="rightTblHead" style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="DHEAD" width="1810px" style="margin-right:20px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="170">	<!-- Item Code				-->
												<col align="center" width="230">	<!-- Item Description		-->
												<col align="center" width="170">	<!-- Charge Type			-->
												<col align="center" width="100">	<!-- Item Segment			-->
												<col align="center" width="210">	<!-- Service Model Name		-->
												<col align="center" width="100">	<!-- Price					-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="center" width="70" >	<!-- Status					-->
												<col align="center" width="170">	<!-- Created By				-->
												<col align="center" width="100">	<!-- Created Date			-->
												<col align="center" width="170">	<!-- Last Update By			-->
												<col align="center" width="100">	<!-- Last Update Date		-->
												<tr style="height:34px;">
													<td id="DH"  class="pClothBs" align="center"></td>
													<td id="DH0"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'xxChkBox_PD' )" tabindex="-1">Delete/<br>Update<img id="sortIMG.xxChkBox_PD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="DH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'mdseCd_PD' )" tabindex="-1">Service Item<img id="sortIMG.mdseCd_PD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="DH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'mdseDescShortTxt_PD' )" tabindex="-1">Service Item Description<img id="sortIMG.mdseDescShortTxt_PD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="DH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'prcAddlChrgTpCd_PD' )" tabindex="-1">Charge Type<img id="sortIMG.prcAddlChrgTpCd_PD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="DH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'mktMdlSegCd_PD' )" tabindex="-1">Item Segment<img id="sortIMG.mktMdlSegCd_PD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="DH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'mdlNm_PD' )" tabindex="-1">Service Model Name<img id="sortIMG.mdlNm_PD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="DH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'addlChrgPrcAmt_PD' )" tabindex="-1">Price<img id="sortIMG.addlChrgPrcAmt_PD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="DH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'effFromDt_PD' )" tabindex="-1">Date From<img id="sortIMG.effFromDt_PD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="DH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'effThruDt_PD' )" tabindex="-1">Date To<img id="sortIMG.effThruDt_PD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="DH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'xxScrStsTxt_D1' )" tabindex="-1">Status<img id="sortIMG.xxScrStsTxt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="DH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'xxFullNm_D1' )" tabindex="-1">Created By<img id="sortIMG.xxFullNm_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="DH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'cratDt_PD' )" tabindex="-1">Created Date<img id="sortIMG.cratDt_PD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="DH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'xxFullNm_D2' )" tabindex="-1">Update By<img id="sortIMG.xxFullNm_D2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="DH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'lastUpdDt_PD' )" tabindex="-1">Update Date<img id="sortIMG.lastUpdDt_PD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
												</table>
											</div>
											<div id="rightTbl" style="width:1097px; height:381px; display:block; overflow:scroll; margin:0px; padding:0px;"  >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="D" width="1810px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="170">	<!-- Item Code				-->
												<col align="left"   width="230">	<!-- Item Description		-->
												<col align="center" width="170">	<!-- Charge Type			-->
												<col align="center" width="100">	<!-- Item Segment			-->
												<col align="center" width="210">	<!-- Service Model Name		-->
												<col align="center" width="100">	<!-- Price					-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="left"   width="70" >	<!-- Status					-->
												<col align="left"   width="170">	<!-- Created By				-->
												<col align="left"   width="100">	<!-- Created Date			-->
												<col align="left"   width="170">	<!-- Last Update By			-->
												<col align="left"   width="100">	<!-- Last Update Date		-->
												<ezf:row ezfHyo="D">
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><ezf:inputCheckBox name="xxChkBox_SD" ezfName="xxChkBox_SD" value="Y" ezfHyo="D" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_SD#{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputCheckBox name="xxChkBox_PD" ezfName="xxChkBox_PD" value="Y" ezfHyo="D" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_PD#{EZF_ROW_NUMBER}\""/></td>
													<td>
														<ezf:inputText name="mdseCd_PD" ezfName="mdseCd_PD" ezfHyo="D" ezfArrayNo="0" otherEvent1="OnBlur_OpenWin_Mdse" otherAttr=" size=\"16\" maxlength=\"16\" ezffocusout=\"OnBlur_OpenWin_Mdse\""/>
														<ezf:inputButton name="OpenWin_Mdse" value="..." ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Mdse#{EZF_ROW_NUMBER}\""/>
													</td>
													<td><span><ezf:label name="mdseDescShortTxt_PD" ezfName="mdseDescShortTxt_PD" ezfHyo="D" ezfArrayNo="0" /></span></td>

													<td>
														<ezf:select name="prcAddlChrgTpCd_PD" ezfName="prcAddlChrgTpCd_PD" ezfHyo="D" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcAddlChrgTpCd_LD" ezfDispName="prcAddlChrgTpDescTxt_LD" otherAttr=" style=\"width: 150px\""/>
													</td>
													<td>
														<ezf:select name="mktMdlSegCd_PD" ezfName="mktMdlSegCd_PD" ezfHyo="D" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mktMdlSegCd_LD" ezfDispName="mktMdlSegDescTxt_LD" otherAttr=" style=\"width: 80px\""/>
													</td>
													<td>
														<ezf:inputText name="mdlNm_PD" ezfName="mdlNm_PD" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
														<ezf:inputButton name="OpenWin_Model" value="..." ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Model#{EZF_ROW_NUMBER}\""/>
													</td>
													<td><ezf:inputText name="addlChrgPrcAmt_PD" ezfName="addlChrgPrcAmt_PD" value="123,456,789,000" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td>
														<ezf:inputText name="effFromDt_PD" ezfName="effFromDt_PD" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_PD', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td>
														<ezf:inputText name="effThruDt_PD" ezfName="effThruDt_PD" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_PD', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td><span><ezf:label name="xxScrStsTxt_D1" ezfName="xxScrStsTxt_D1" ezfHyo="D" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_D1" ezfName="xxFullNm_D1" ezfHyo="D" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="cratDt_PD" ezfName="cratDt_PD" ezfHyo="D" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_D2" ezfName="xxFullNm_D2" ezfHyo="D" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="lastUpdDt_PD" ezfName="lastUpdDt_PD" ezfHyo="D" ezfArrayNo="0" /></span></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
											</div>
										</div> <!-- right table -->
										</div> <!-- parentBoxD -->
									</td>
								</tr>
							</table>
							<script type="text/javascript" defer>
							    S21TableUI.initialize("parentBoxD", "DHEAD", "D", -1, false, true);
							</script>
							</c:when>

<%--------------------------------%>
<%-- Supply Program				--%>
<%--------------------------------%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_D1 == 'PrcListValSupplyProgram'}">
							<script type="text/javascript">
								document.getElementById( "PrcListVal" ).className = "pTab_ON";
								document.getElementById( "TrxDrv" ).className = "pTab_OFF";
							</script>

							<table>
								<tr>
									<td width="10"></td>
									<td>
										<div id="parentBoxE">
										<div style="float:left; display:block"> <!-- left table -->
											<div id="leftTblHead" style="display:block;">
											</div>
											<div id="leftTbl" style="float:left; display:block; height:364px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
											</div>
										</div>  <!-- left table -->
										<div style="float:left"> <!-- right table -->
											<div id="rightTblHead" style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0"  id="EHEAD" width="3840px" style="margin-right:20px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="210">	<!-- Service Model Name		-->
												<col align="center" width="210">	<!-- Meter Package Name		-->
												<col align="center" width="100">	<!-- Plan Type				-->
												<col align="center" width="160">	<!-- Contract Type			-->
												<col align="center" width="230">	<!-- Meter Type				-->
												<col align="center" width="100">	<!-- Min Vol				-->
												<col align="center" width="100">	<!-- Max Vol				-->
												<col align="center" width="100" >	<!-- Band					-->
												<col align="center" width="100">	<!-- Total Base Amount		-->
												<col align="center" width="100">	<!-- Supply Base Amount		-->
												<col align="center" width="100">	<!-- Cost Per Copy(OVERAGE)-->
												<col align="center" width="100">	<!-- Min CPC				-->
												<col align="center" width="100">	<!-- Max CPC				-->
												<col align="center" width="80" >	<!-- Term From(MTH)			-->
												<col align="center" width="80" >	<!-- Term To(MTH)			-->
												<col align="center" width="170">	<!-- Service Item			-->
												<col align="center" width="230">	<!-- Service Item Description-->
												<col align="center" width="170">	<!-- Billing Counter Name	-->
												<col align="center" width="170">	<!-- Service Zone(s) Included-->
												<col align="center" width="320">	<!-- Supply Plan 			-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="center" width="70" >	<!-- Status					-->
												<col align="center" width="170">	<!-- Created By				-->
												<col align="center" width="100">	<!-- Created Date			-->
												<col align="center" width="170">	<!-- Last Update By			-->
												<col align="center" width="100">	<!-- Last Update Date		-->
												<tr style="height:34px;">
													<td id="EH"  class="pClothBs" align="center"></td>
													<td id="EH0"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'xxChkBox_PE' )" tabindex="-1">Delete/<br>Update<img id="sortIMG.xxChkBox_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'mdlNm_PE' )" tabindex="-1">Service Model Name<img id="sortIMG.mdlNm_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'prcMtrPkgNm_PE' )" tabindex="-1">Meter Package Name<img id="sortIMG.prcMtrPkgNm_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'prcSvcPlnTpCd_PE' )" tabindex="-1">Plan Type<img id="sortIMG.prcSvcPlnTpCd_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'prcSvcContrTpCd_PE' )" tabindex="-1">Contract Type<img id="sortIMG.prcSvcContrTpCd_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'mtrLbNm_PE' )" tabindex="-1">Meter Type<img id="sortIMG.mtrLbNm_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'minCopyVolCnt_PE' )" tabindex="-1">Min Vol<img id="sortIMG.minCopyVolCnt_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'maxCopyVolCnt_PE' )" tabindex="-1">Max Vol<img id="sortIMG.maxCopyVolCnt_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'prcListBandDescTxt_PE' )" tabindex="-1">Band<img id="sortIMG.prcListBandDescTxt_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'totBaseAmt_PE' )" tabindex="-1">Total Base Amount<img id="sortIMG.totBaseAmt_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'splyBaseAmt_PE' )" tabindex="-1">Supply Base Amount<img id="sortIMG.splyBaseAmt_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'cpcAmtRate_PE' )" tabindex="-1">Cost Per Copy<br>OVERAGE<img id="sortIMG.cpcAmtRate_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'minCpcAmtRate_PE' )" tabindex="-1">Min CPC<img id="sortIMG.minCpcAmtRate_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'maxCpcAmtRate_PE' )" tabindex="-1">Max CPC<img id="sortIMG.maxCpcAmtRate_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'termFromMthAot_PE' )" tabindex="-1">Term From(MTH)<img id="sortIMG.termFromMthAot_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'termThruMthAot_PE' )" tabindex="-1">Term To(MTH)<img id="sortIMG.termThruMthAot_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'mdseCd_PE' )" tabindex="-1">Service Item<img id="sortIMG.mdseCd_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'mdseDescShortTxt_PE' )" tabindex="-1">Service Item Description<img id="sortIMG.mdseDescShortTxt_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'mtrLbDescTxt_PE' )" tabindex="-1">Billing Counter Name<img id="sortIMG.mtrLbDescTxt_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'prcSvcZoneCd_PE' )" tabindex="-1">Service Zone(s) Included<img id="sortIMG.prcSvcZoneCd_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'splyAgmtPlnPk_PE' )" tabindex="-1">Supply Plan<img id="sortIMG.splyAgmtPlnPk_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'effFromDt_PE' )" tabindex="-1">Date From<img id="sortIMG.effFromDt_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'effThruDt_PE' )" tabindex="-1">Date To<img id="sortIMG.effThruDt_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'xxScrStsTxt_E1' )" tabindex="-1">Status<img id="sortIMG.xxScrStsTxt_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'xxFullNm_E1' )" tabindex="-1">Created By<img id="sortIMG.xxFullNm_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'cratDt_PE' )" tabindex="-1">Created Date<img id="sortIMG.cratDt_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'xxFullNm_E2' )" tabindex="-1">Update By<img id="sortIMG.xxFullNm_E2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="EH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'lastUpdDt_PE' )" tabindex="-1">Update Date<img id="sortIMG.lastUpdDt_PE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
												</table>
											</div>
											<div id="rightTbl" style="width:1097px; height:381px; display:block; overflow:scroll; margin:0px; padding:0px;"  >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="E" width="3840px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="210">	<!-- Service Model Name		-->
												<col align="center" width="210">	<!-- Meter Package Name		-->
												<col align="center" width="100" >	<!-- Plan Type				-->
												<col align="center" width="160">	<!-- Contract Type			-->
												<col align="center" width="230">	<!-- Meter Type				-->
												<col align="center" width="100">	<!-- Min Vol				-->
												<col align="center" width="100">	<!-- Max Vol				-->
												<col align="center" width="100" >	<!-- Band					-->
												<col align="center" width="100">	<!-- Total Base Amount		-->
												<col align="center" width="100">	<!-- Supply Base Amount		-->
												<col align="center" width="100">	<!-- Cost Per Copy(OVERAGE)-->
												<col align="center" width="100">	<!-- Min CPC				-->
												<col align="center" width="100">	<!-- Max CPC				-->
												<col align="center" width="80" >	<!-- Term From(MTH)			-->
												<col align="center" width="80" >	<!-- Term To(MTH)			-->
												<col align="center" width="170">	<!-- Service Item			-->
												<col align="left"   width="230">	<!-- Service Item Description-->
												<col align="left"   width="170">	<!-- Billing Counter Name	-->
												<col align="center" width="170">	<!-- Service Zone(s) Included-->
												<col align="center" width="320">	<!-- Supply Plan 			-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="left"   width="70" >	<!-- Status					-->
												<col align="left"   width="170">	<!-- Created By				-->
												<col align="left"   width="100">	<!-- Created Date			-->
												<col align="left"   width="170">	<!-- Last Update By			-->
												<col align="left"   width="100">	<!-- Last Update Date		-->
												<ezf:row ezfHyo="E">
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><ezf:inputCheckBox name="xxChkBox_SE" ezfName="xxChkBox_SE" value="Y" ezfHyo="E" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_SE#{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputCheckBox name="xxChkBox_PE" ezfName="xxChkBox_PE" value="Y" ezfHyo="E" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_PE#{EZF_ROW_NUMBER}\""/></td>
													<td>
														<ezf:inputText name="mdlNm_PE" ezfName="mdlNm_PE" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
														<ezf:inputButton name="OpenWin_Model" value="..." ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Model#{EZF_ROW_NUMBER}\""/>
													</td>
													<td>
														<ezf:inputText name="prcMtrPkgNm_PE" ezfName="prcMtrPkgNm_PE" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
														<ezf:inputButton name="OpenWin_MtrPkg" value="..." ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MtrPkg#{EZF_ROW_NUMBER}\""/>
													</td>
													<td>
														<ezf:select name="prcSvcPlnTpCd_PE" ezfName="prcSvcPlnTpCd_PE" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcSvcPlnTpCd_LE" ezfDispName="prcSvcPlnTpDescTxt_LE" otherAttr=" style=\"width: 80px\""/>
													</td>
													<td>
														<ezf:select name="prcSvcContrTpCd_PE" ezfName="prcSvcContrTpCd_PE" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcSvcContrTpCd_LE" ezfDispName="prcSvcContrTpDescTxt_LE" otherAttr=" style=\"width: 140px\""/>
													</td>
													<td>
                                                        <ezf:inputText name="mtrLbNm_PE" ezfName="mtrLbNm_PE" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"24\""/>
                                                        <ezf:inputButton name="OpenWin_MtrLb" value="..." ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn1" />
														<!--<select style="width: 210px" name="mtrLbCd_PE" ezfname="mtrLbCd_PE" ezflist="mtrLbCd_LE, mtrLbDescTxt_LE,99, ,blank" ezfHyo="E">-->
														<!--	<option selected>XXXXX</option>-->
														<!--	<option>XXXXXXXXXX</option>-->
														<!--</select>-->
													</td>
													<td><ezf:inputText name="minCopyVolCnt_PE" ezfName="minCopyVolCnt_PE" value="123,456,789,000" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"11\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="maxCopyVolCnt_PE" ezfName="maxCopyVolCnt_PE" value="123,456,789,000" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"11\" style=\"text-align:right\""/></td>
													<td>
														<ezf:inputText name="prcListBandDescTxt_PE" ezfName="prcListBandDescTxt_PE" value="BAND" ezfHyo="E" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"5\""/>
                                                        <ezf:inputButton name="OpenWin_PrcListBand" value="..." ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn1" />
														<!--<select style="width: 50px" name="prcListBandCd_PE" ezfname="prcListBandCd_PE" ezflist="prcListBandCd_LE, prcListBandDescTxt_LE,99, ,blank" ezfHyo="E">-->
														<!--	<option selected>XXXXX</option>-->
														<!--	<option>XXXXXXXXXX</option>-->
														<!--</select>-->
													</td>
													<td><ezf:inputText name="totBaseAmt_PE" ezfName="totBaseAmt_PE" value="-123,456,789.12" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="splyBaseAmt_PE" ezfName="splyBaseAmt_PE" value="-123,456,789.12" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="cpcAmtRate_PE" ezfName="cpcAmtRate_PE" value="-123,456,789.12" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"7\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="minCpcAmtRate_PE" ezfName="minCpcAmtRate_PE" value="-123,456,789.12" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"7\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="maxCpcAmtRate_PE" ezfName="maxCpcAmtRate_PE" value="-123,456,789.12" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"7\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="termFromMthAot_PE" ezfName="termFromMthAot_PE" value="123" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="termThruMthAot_PE" ezfName="termThruMthAot_PE" value="123" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right\""/></td>
													<td>
														<ezf:inputText name="mdseCd_PE" ezfName="mdseCd_PE" ezfHyo="E" ezfArrayNo="0" otherEvent1="OnBlur_OpenWin_Mdse" otherAttr=" size=\"16\" maxlength=\"16\" ezffocusout=\"OnBlur_OpenWin_Mdse\""/>
														<ezf:inputButton name="OpenWin_Mdse" value="..." ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Mdse#{EZF_ROW_NUMBER}\""/>
													</td>
													<td><span><ezf:label name="mdseDescShortTxt_PE" ezfName="mdseDescShortTxt_PE" ezfHyo="E" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="mtrLbDescTxt_PE" ezfName="mtrLbDescTxt_PE" ezfHyo="E" ezfArrayNo="0" /></span></td>
													<td>
														<ezf:select name="prcSvcZoneCd_PE" ezfName="prcSvcZoneCd_PE" ezfHyo="E" ezfArrayNo="0" ezfCodeName="prcSvcZoneCd_LE" ezfDispName="prcSvcZoneDescTxt_LE" otherAttr=" style=\"width: 150px\""/>
													</td>
													<td>
														<ezf:inputText name="splyAgmtPlnPk_PE" ezfName="splyAgmtPlnPk_PE" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"28\""/>
														<span class="pkNm"><ezf:label name="splyAgmtPlnNm_PE" ezfName="splyAgmtPlnNm_PE" ezfHyo="E" ezfArrayNo="0" /></span>
														<ezf:inputButton name="OpenWin_SupplyPlan" value="..." ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SupplyPlan#{EZF_ROW_NUMBER}\""/>
													</td>
													<td>
														<ezf:inputText name="effFromDt_PE" ezfName="effFromDt_PE" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_PE', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td>
														<ezf:inputText name="effThruDt_PE" ezfName="effThruDt_PE" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_PE', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td><span><ezf:label name="xxScrStsTxt_E1" ezfName="xxScrStsTxt_E1" ezfHyo="E" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_E1" ezfName="xxFullNm_E1" ezfHyo="E" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="cratDt_PE" ezfName="cratDt_PE" ezfHyo="E" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_E2" ezfName="xxFullNm_E2" ezfHyo="E" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="lastUpdDt_PE" ezfName="lastUpdDt_PE" ezfHyo="E" ezfArrayNo="0" /></span></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
											</div>
										</div> <!-- right table -->
										</div> <!-- parentBoxE -->
									</td>
								</tr>
							</table>
							<script type="text/javascript" defer>
							    S21TableUI.initialize("parentBoxE", "EHEAD", "E", -1, false, true);
							</script>
							</c:when>

<%--------------------%>
<%-- Lease Rate		--%>
<%--------------------%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_D1 == 'PrcListValLeaseRate'}">
							<script type="text/javascript">
								document.getElementById( "PrcListVal" ).className = "pTab_ON";
								document.getElementById( "TrxDrv" ).className = "pTab_OFF";
							</script>

							<table>
								<tr>
									<td width="10"></td>
									<td>
										<div id="parentBoxF">
										<div style="float:left; display:block"> <!-- left table -->
											<div id="leftTblHead" style="display:block;">
											</div>
											<div id="leftTbl" style="float:left; display:block; height:364px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
											</div>
										</div>  <!-- left table -->
										<div style="float:left"> <!-- right table -->
											<div id="rightTblHead" style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0"  id="FHEAD" width="2210px" style="margin-right:20px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="100">	<!-- Lease Company Abbreviation-->
												<col align="center" width="170">	<!-- Lease Company			-->
												<col align="center" width="170">	<!-- Program Name			-->
												<col align="center" width="170">	<!-- Equipment Type			-->
												<col align="center" width="210">	<!-- Service Model Name		-->
												<col align="center" width="100">	<!-- Total Financed Min		-->
												<col align="center" width="100">	<!-- Total Financed Max		-->
												<col align="center" width="130">	<!-- Qualifiying Term From	-->
												<col align="center" width="130">	<!-- Qualifiying Term To	-->
												<col align="center" width="100">	<!-- Lease Rate				-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="center" width="70" >	<!-- Status					-->
												<col align="center" width="170">	<!-- Created By				-->
												<col align="center" width="100">	<!-- Created Date			-->
												<col align="center" width="170">	<!-- Last Update By			-->
												<col align="center" width="100">	<!-- Last Update Date		-->
												<tr style="height:34px;">
													<td id="FH"  class="pClothBs" align="center"></td>
													<td id="FH0"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'xxChkBox_PF' )" tabindex="-1">Delete/<br>Update<img id="sortIMG.xxChkBox_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'prcLeaseCmpyAbbrNm_PF' )" tabindex="-1">Lease Company Abbreviation<img id="sortIMG.prcLeaseCmpyAbbrNm_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'dsAcctNm_PF' )" tabindex="-1">Lease Company<img id="sortIMG.dsAcctNm_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'prcPgmTpCd_PF' )" tabindex="-1">Program Name<img id="sortIMG.prcPgmTpCd_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'prcEquipTpCd_PF' )" tabindex="-1">Equipment Type<img id="sortIMG.prcEquipTpCd_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'mdlNm_PF' )" tabindex="-1">Service Model Name<img id="sortIMG.mdlNm_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'minTotFinAmt_PF' )" tabindex="-1">Total Financed Min<img id="sortIMG.minTotFinAmt_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'maxTotFinAmt_PF' )" tabindex="-1">Total Financed Max<img id="sortIMG.maxTotFinAmt_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'termFromMthAot_PF' )" tabindex="-1">Qualifiying Term From<img id="sortIMG.termFromMthAot_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'termThruMthAot_PF' )" tabindex="-1">Qualifiying Term To<img id="sortIMG.termThruMthAot_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'leaseRate_PF' )" tabindex="-1">Lease Rate<img id="sortIMG.leaseRate_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'effFromDt_PF' )" tabindex="-1">Date From<img id="sortIMG.effFromDt_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'effThruDt_PF' )" tabindex="-1">Date To<img id="sortIMG.effThruDt_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'xxScrStsTxt_F1' )" tabindex="-1">Status<img id="sortIMG.xxScrStsTxt_F1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'xxFullNm_F1' )" tabindex="-1">Created By<img id="sortIMG.xxFullNm_F1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'cratDt_PF' )" tabindex="-1">Created Date<img id="sortIMG.cratDt_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'xxFullNm_F2' )" tabindex="-1">Update By<img id="sortIMG.xxFullNm_F2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="FH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'F', 'lastUpdDt_PF' )" tabindex="-1">Update Date<img id="sortIMG.lastUpdDt_PF" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
												</table>
											</div>
											<div id="rightTbl" style="width:1097px; height:381px; display:block; overflow:scroll; margin:0px; padding:0px;"  >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="F" width="2210px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="100">	<!-- Lease Company Abbreviation-->
												<col align="center" width="170">	<!-- Lease Company			-->
												<col align="center" width="170">	<!-- Program Name			-->
												<col align="center" width="170">	<!-- Equipment Type			-->
												<col align="center" width="210">	<!-- Service Model Name		-->
												<col align="center" width="100">	<!-- Total Financed Min		-->
												<col align="center" width="100">	<!-- Total Financed Max		-->
												<col align="center" width="130">	<!-- Qualifiying Term From	-->
												<col align="center" width="130">	<!-- Qualifiying Term To	-->
												<col align="center" width="100">	<!-- Lease Rate				-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="left"   width="70" >	<!-- Status					-->
												<col align="left"   width="170">	<!-- Created By				-->
												<col align="left"   width="100">	<!-- Created Date			-->
												<col align="left"   width="170">	<!-- Last Update By			-->
												<col align="left"   width="100">	<!-- Last Update Date		-->
												<ezf:row ezfHyo="F">
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><ezf:inputCheckBox name="xxChkBox_SF" ezfName="xxChkBox_SF" value="Y" ezfHyo="F" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_SF#{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputCheckBox name="xxChkBox_PF" ezfName="xxChkBox_PF" value="Y" ezfHyo="F" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_PF#{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputText name="prcLeaseCmpyAbbrNm_PF" ezfName="prcLeaseCmpyAbbrNm_PF" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
													<td>
														<ezf:inputText name="dsAcctNm_PF" ezfName="dsAcctNm_PF" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
														<ezf:inputButton name="OpenWin_DsAcctCust" value="..." ezfHyo="F" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_DsAcctCust#{EZF_ROW_NUMBER}\""/>
													</td>
													<td>
														<ezf:select name="prcPgmTpCd_PF" ezfName="prcPgmTpCd_PF" ezfHyo="F" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcPgmTpCd_LF" ezfDispName="prcPgmTpDescTxt_LF" otherAttr=" style=\"width: 150px\""/>
													</td>
													<td>
														<ezf:select name="prcEquipTpCd_PF" ezfName="prcEquipTpCd_PF" ezfHyo="F" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcEquipTpCd_LF" ezfDispName="prcEquipTpDescTxt_LF" otherAttr=" style=\"width: 150px\""/>
													</td>
													<td>
														<ezf:inputText name="mdlNm_PF" ezfName="mdlNm_PF" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
														<ezf:inputButton name="OpenWin_Model" value="..." ezfHyo="F" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Model#{EZF_ROW_NUMBER}\""/>
													</td>
													<td><ezf:inputText name="minTotFinAmt_PF" ezfName="minTotFinAmt_PF" value="-123,456,789.12" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="maxTotFinAmt_PF" ezfName="maxTotFinAmt_PF" value="-123,456,789.12" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="termFromMthAot_PF" ezfName="termFromMthAot_PF" value="123" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="termThruMthAot_PF" ezfName="termThruMthAot_PF" value="123" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="leaseRate_PF" ezfName="leaseRate_PF" value="123.45" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"7\" style=\"text-align:right\""/></td>

													<td>
														<ezf:inputText name="effFromDt_PF" ezfName="effFromDt_PF" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_PF', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td>
														<ezf:inputText name="effThruDt_PF" ezfName="effThruDt_PF" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_PF', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td><span><ezf:label name="xxScrStsTxt_F1" ezfName="xxScrStsTxt_F1" ezfHyo="F" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_F1" ezfName="xxFullNm_F1" ezfHyo="F" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="cratDt_PF" ezfName="cratDt_PF" ezfHyo="F" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_F2" ezfName="xxFullNm_F2" ezfHyo="F" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="lastUpdDt_PF" ezfName="lastUpdDt_PF" ezfHyo="F" ezfArrayNo="0" /></span></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
											</div>
										</div> <!-- right table -->
										</div> <!-- parentBoxF -->
									</td>
								</tr>
							</table>
							<script type="text/javascript" defer>
							    S21TableUI.initialize("parentBoxF", "FHEAD", "F", -1, false, true);
							</script>
							</c:when>

<%------------------------%>
<%-- Lease Return Fees	--%>
<%------------------------%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_D1 == 'PrcListValLeaseReturnFees'}">
							<script type="text/javascript">
								document.getElementById( "PrcListVal" ).className = "pTab_ON";
								document.getElementById( "TrxDrv" ).className = "pTab_OFF";
							</script>

							<table>
								<tr>
									<td width="10"></td>
									<td>
										<div id="parentBoxG">
										<div style="float:left; display:block"> <!-- left table -->
											<div id="leftTblHead" style="display:block;">
											</div>
											<div id="leftTbl" style="float:left; display:block; height:347px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
											</div>
										</div>  <!-- left table -->
										<div style="float:left"> <!-- right table -->
											<div id="rightTblHead" style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0"  id="GHEAD" width="1470px" style="margin-right:20px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="100">	<!-- Return to Lease Company Abbreviation-->
												<col align="center" width="170">	<!-- Machine Segment		-->
												<col align="center" width="170">	<!-- In or Out of Region	-->
												<col align="center" width="100">	<!-- Distance(Miles)		-->
												<col align="center" width="100">	<!-- Return Fee				-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="center" width="70" >	<!-- Status					-->
												<col align="center" width="170">	<!-- Created By				-->
												<col align="center" width="100">	<!-- Created Date			-->
												<col align="center" width="170">	<!-- Last Update By			-->
												<col align="center" width="100">	<!-- Last Update Date		-->
												<tr style="height:51px;">
													<td id="GH"  class="pClothBs" align="center"></td>
													<td id="GH0"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'xxChkBox_PG' )" tabindex="-1">Delete/<br>Update<img id="sortIMG.xxChkBox_PG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="GH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'prcLeaseCmpyAbbrNm_PG' )" tabindex="-1">Return to Lease Company Abbreviation<img id="sortIMG.prcLeaseCmpyAbbrNm_PG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="GH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'svcSegCd_PG' )" tabindex="-1">Machine Segment<img id="sortIMG.svcSegCd_PG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="GH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'prcInOutRgCd_PG' )" tabindex="-1">In or Out of Region<img id="sortIMG.prcInOutRgCd_PG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="GH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'dstMileAmt_PG' )" tabindex="-1">Distance(Miles)<img id="sortIMG.dstMileAmt_PG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="GH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'rtrnFeeAmt_PG' )" tabindex="-1">Return Fee<img id="sortIMG.rtrnFeeAmt_PG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="GH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'effFromDt_PG' )" tabindex="-1">Date From<img id="sortIMG.effFromDt_PG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="GH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'effThruDt_PG' )" tabindex="-1">Date To<img id="sortIMG.effThruDt_PG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="GH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'xxScrStsTxt_G1' )" tabindex="-1">Status<img id="sortIMG.xxScrStsTxt_G1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="GH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'xxFullNm_G1' )" tabindex="-1">Created By<img id="sortIMG.xxFullNm_G1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="GH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'cratDt_PG' )" tabindex="-1">Created Date<img id="sortIMG.cratDt_PG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="GH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'xxFullNm_G2' )" tabindex="-1">Update By<img id="sortIMG.xxFullNm_G2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="GH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'G', 'lastUpdDt_PG' )" tabindex="-1">Update Date<img id="sortIMG.lastUpdDt_PG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
												</table>
											</div>
											<div id="rightTbl" style="width:1097px; height:364px; display:block; overflow:scroll; margin:0px; padding:0px;"  >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="G" width="1470px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="100">	<!-- Return to Lease Company Abbreviation-->
												<col align="center" width="170">	<!-- Machine Segment		-->
												<col align="center" width="170">	<!-- In or Out of Region	-->
												<col align="center" width="100">	<!-- Distance(Miles)		-->
												<col align="center" width="100">	<!-- Return Fee				-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="left"   width="70" >	<!-- Status					-->
												<col align="left"   width="170">	<!-- Created By				-->
												<col align="left"   width="100">	<!-- Created Date			-->
												<col align="left"   width="170">	<!-- Last Update By			-->
												<col align="left"   width="100">	<!-- Last Update Date		-->
												<ezf:row ezfHyo="G">
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><ezf:inputCheckBox name="xxChkBox_SG" ezfName="xxChkBox_SG" value="Y" ezfHyo="G" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_SG#{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputCheckBox name="xxChkBox_PG" ezfName="xxChkBox_PG" value="Y" ezfHyo="G" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_PG#{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputText name="prcLeaseCmpyAbbrNm_PG" ezfName="prcLeaseCmpyAbbrNm_PG" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
													<td>
														<ezf:select name="svcSegCd_PG" ezfName="svcSegCd_PG" ezfHyo="G" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcSegCd_LG" ezfDispName="svcSegDescTxt_LG" otherAttr=" style=\"width: 150px\""/>
													</td>
													<td>
														<ezf:select name="prcInOutRgCd_PG" ezfName="prcInOutRgCd_PG" ezfHyo="G" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcInOutRgCd_LG" ezfDispName="prcInOutRgDescTxt_LG" otherAttr=" style=\"width: 150px\""/>
													</td>
													<td><ezf:inputText name="dstMileAmt_PG" ezfName="dstMileAmt_PG" value="-123,456,789.12" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="rtrnFeeAmt_PG" ezfName="rtrnFeeAmt_PG" value="-123,456,789.12" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td>
														<ezf:inputText name="effFromDt_PG" ezfName="effFromDt_PG" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_PG', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td>
														<ezf:inputText name="effThruDt_PG" ezfName="effThruDt_PG" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_PG', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td><span><ezf:label name="xxScrStsTxt_G1" ezfName="xxScrStsTxt_G1" ezfHyo="G" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_G1" ezfName="xxFullNm_G1" ezfHyo="G" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="cratDt_PG" ezfName="cratDt_PG" ezfHyo="G" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_G2" ezfName="xxFullNm_G2" ezfHyo="G" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="lastUpdDt_PG" ezfName="lastUpdDt_PG" ezfHyo="G" ezfArrayNo="0" /></span></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
											</div>
										</div> <!-- right table -->
										</div> <!-- parentBoxG -->
									</td>
								</tr>
							</table>
							<script type="text/javascript" defer>
							    S21TableUI.initialize("parentBoxG", "GHEAD", "G", -1, false, true);
							</script>
							</c:when>

<%------------------------%>
<%-- Trade In			--%>
<%------------------------%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_D1 == 'PrcListValTradeIn'}">
							<script type="text/javascript">
								document.getElementById( "PrcListVal" ).className = "pTab_ON";
								document.getElementById( "TrxDrv" ).className = "pTab_OFF";
							</script>

							<table>
								<tr>
									<td width="10"></td>
									<td>
										<div id="parentBoxH">
										<div style="float:left; display:block"> <!-- left table -->
											<div id="leftTblHead" style="display:block;">
											</div>
											<div id="leftTbl" style="float:left; display:block; height:364px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
											</div>
										</div>  <!-- left table -->
										<div style="float:left"> <!-- right table -->
											<div id="rightTblHead" style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="HHEAD" width="1440px" style="margin-right:20px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="210">	<!-- Service Model Name		-->
												<col align="center" width="100">	<!-- Trade In Value			-->
												<col align="center" width="100">	<!-- Meter Range Required	-->
												<col align="center" width="100">	<!-- Meter From 			-->
												<col align="center" width="100">	<!-- Meter To	 			-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="center" width="70" >	<!-- Status					-->
												<col align="center" width="170">	<!-- Created By				-->
												<col align="center" width="100">	<!-- Created Date			-->
												<col align="center" width="170">	<!-- Last Update By			-->
												<col align="center" width="100">	<!-- Last Update Date		-->
												<tr style="height:34px;">
													<td id="HH"  class="pClothBs" align="center"></td>
													<td id="HH0"  class="pClothBs" align="center"><a href="#" class="pSortCol" onclick="columnSort( 'H', 'xxChkBox_PH' )" tabindex="-1">Delete/<br>Update<img id="sortIMG.xxChkBox_PH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="HH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'H', 'mdlNm_PH' )" tabindex="-1">Service Model Name<img id="sortIMG.mdlNm_PH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="HH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'H', 'tiAmt_PH' )" tabindex="-1">Trade In Value<img id="sortIMG.tiAmt_PH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="HH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'H', 'mtrRngReqFlg_PH' )" tabindex="-1">Meter Range Required<img id="sortIMG.mtrRngReqFlg_PH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="HH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'H', 'fromMtrCopyVolCnt_PH' )" tabindex="-1">Meter From<img id="sortIMG.fromMtrCopyVolCnt_PH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="HH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'H', 'thruMtrCopyVolCnt_PH' )" tabindex="-1">Meter To<img id="sortIMG.thruMtrCopyVolCnt_PH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="HH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'H', 'effFromDt_PH' )" tabindex="-1">Date From<img id="sortIMG.effFromDt_PH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="HH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'H', 'effThruDt_PH' )" tabindex="-1">Date To<img id="sortIMG.effThruDt_PH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="HH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'H', 'xxScrStsTxt_H1' )" tabindex="-1">Status<img id="sortIMG.xxScrStsTxt_H1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="HH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'H', 'xxFullNm_H1' )" tabindex="-1">Created By<img id="sortIMG.xxFullNm_H1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="HH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'H', 'cratDt_PH' )" tabindex="-1">Created Date<img id="sortIMG.cratDt_PH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="HH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'H', 'xxFullNm_H2' )" tabindex="-1">Update By<img id="sortIMG.xxFullNm_H2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="HH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'H', 'lastUpdDt_PH' )" tabindex="-1">Update Date<img id="sortIMG.lastUpdDt_PH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
												</table>
											</div>
											<div id="rightTbl" style="width:1097px; height:381px; display:block; overflow:scroll; margin:0px; padding:0px;"  >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="H" width="1440px" >
												<col align="center" width="25" >	<!-- Select					-->
												<col align="center" width="50" >	<!-- Delete/Update			-->
												<col align="center" width="210">	<!-- Service Model Name		-->
												<col align="center" width="100">	<!-- Trade In Value			-->
												<col align="center" width="100">	<!-- Meter Range Required	-->
												<col align="center" width="100">	<!-- Meter From 			-->
												<col align="center" width="100">	<!-- Meter To	 			-->
												<col align="center" width="120">	<!-- Date From				-->
												<col align="center" width="120">	<!-- Date To				-->
												<col align="left"   width="70" >	<!-- Status					-->
												<col align="left"   width="170">	<!-- Created By				-->
												<col align="left"   width="100">	<!-- Created Date			-->
												<col align="left"   width="170">	<!-- Last Update By			-->
												<col align="left"   width="100">	<!-- Last Update Date		-->
												<ezf:row ezfHyo="H">
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><ezf:inputCheckBox name="xxChkBox_SH" ezfName="xxChkBox_SH" value="Y" ezfHyo="H" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_SH#{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputCheckBox name="xxChkBox_PH" ezfName="xxChkBox_PH" value="Y" ezfHyo="H" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_PH#{EZF_ROW_NUMBER}\""/></td>
													<td>
														<ezf:inputText name="mdlNm_PH" ezfName="mdlNm_PH" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
														<ezf:inputButton name="OpenWin_Model" value="..." ezfHyo="H" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Model#{EZF_ROW_NUMBER}\""/>
													</td>
													<td><ezf:inputText name="tiAmt_PH" ezfName="tiAmt_PH" value="-123,456,789.12" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
													<td><ezf:inputCheckBox name="mtrRngReqFlg_PH" ezfName="mtrRngReqFlg_PH" value="Y" ezfHyo="H" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="fromMtrCopyVolCnt_PH" ezfName="fromMtrCopyVolCnt_PH" value="123,456,789" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"11\" style=\"text-align:right\""/></td>
													<td><ezf:inputText name="thruMtrCopyVolCnt_PH" ezfName="thruMtrCopyVolCnt_PH" value="123,456,789" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"11\" style=\"text-align:right\""/></td>
													<td>
														<ezf:inputText name="effFromDt_PH" ezfName="effFromDt_PH" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_PH', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td>
														<ezf:inputText name="effThruDt_PH" ezfName="effThruDt_PH" ezfHyo="H" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_PH', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td><span><ezf:label name="xxScrStsTxt_H1" ezfName="xxScrStsTxt_H1" ezfHyo="H" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_H1" ezfName="xxFullNm_H1" ezfHyo="H" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="cratDt_PH" ezfName="cratDt_PH" ezfHyo="H" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="xxFullNm_H2" ezfName="xxFullNm_H2" ezfHyo="H" ezfArrayNo="0" /></span></td>
													<td><span><ezf:label name="lastUpdDt_PH" ezfName="lastUpdDt_PH" ezfHyo="H" ezfArrayNo="0" /></span></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
											</div>
										</div> <!-- right table -->
										</div> <!-- parentBoxH -->
									</td>
								</tr>
							</table>
							<script type="text/javascript" defer>
							    S21TableUI.initialize("parentBoxH", "HHEAD", "H", -1, false, true);
							</script>
							</c:when>

<%------------------------%>
<%-- Qty Discount		--%>
<%------------------------%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab_D1 == 'PrcListValQtyDiscount'}">
							<script type="text/javascript">
								document.getElementById( "PrcListVal" ).className = "pTab_ON";
								document.getElementById( "TrxDrv" ).className = "pTab_OFF";
							</script>

								<table border="0" cellpadding="0" cellspacing="0" width="">
									<col width="20" >
									<col width="100">
									<col width="180">
									<col width="45" >
									<col width="130">
									<col width="45" >
									<col width="70" >
									<col width="200">
									<col width="280">
									<tr>
										<td></td>
										<td class="stab">Qualifier Type</td>
										<td>
											<ezf:select name="prcQlfyTpCd_D2" ezfName="prcQlfyTpCd_D2" ezfBlank="1" ezfCodeName="prcQlfyTpCd_LA" ezfDispName="prcQlfyTpDescTxt_LA" otherEvent1=" onchange=\"sendServer('OnChange_PrcQlfyTp')\"" otherAttr=" style=\"width: 160px\""/>
										</td>
										<td class="stab">Value</td>
										<td>
											<ezf:inputText name="prcQlfyValTxt_D2" ezfName="prcQlfyValTxt_D2" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/>
										</td>
										<td>
											<ezf:inputButton name="OpenWin_QualifierValueItem" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_QualifierValueItem\""/>
											<ezf:inputButton name="OpenWin_QualifierValueProd" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_QualifierValueProd\""/>
											<ezf:inputButton name="OpenWin_QualifierValueMdseTp" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_QualifierValueMdseTp\""/>
										</td>
										<td>
											<ezf:inputButton name="DetailApply" value="Apply" htmlClass="pBtn5" />
										</td>
										<td>&nbsp;</td>
										<td height="24" align="right">
											<ezf:inputButton name="Add_QtyDisc" value="Add" htmlClass="pBtn3" />
											<ezf:inputButton name="Del_QtyDisc" value="Del" htmlClass="pBtn3" />
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<table>
												<tr>
													<td width="10"></td>
													<td>
														<div id="parentBoxI">
														<div style="float:left; display:block"> <!-- left table -->
															<div id="leftTblHead" style="display:block;">
															</div>
															<div id="leftTbl" style="float:left; display:block; height:337px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
															</div>
														</div>  <!-- left table -->
														<div style="float:left"> <!-- right table -->
															<div id="rightTblHead" style="width:730px; display:block; overflow:hidden; margin:0px;padding:0px;">
																<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="IHEAD" width="2390px" style="margin-right:20px" >
																	<col align="center" width="30" >	<!-- Select					-->
																	<col align="center" width="50" >	<!-- Delete/Update			-->
																	<col align="center" width="180">	<!-- Qualifier Type			-->
																	<col align="center" width="170">	<!-- Value					-->
																	<col align="center" width="170">	<!-- Description 			-->
																	<col align="center" width="170">	<!-- Mdse Type	 			-->
																	<col align="center" width="170">	<!-- Item Type				-->
																	<col align="center" width="170">	<!-- Prod Code				-->
																	<col align="center" width="170">	<!-- Model					-->
																	<col align="center" width="100">	<!-- Price					-->
																	<col align="center" width="130">	<!-- UOM					-->
																	<col align="center" width="100">	<!-- Price Break Enabled	-->
																	<col align="center" width="120">	<!-- Date From				-->
																	<col align="center" width="120">	<!-- Date To				-->
																	<col align="center" width="170">	<!-- Created By				-->
																	<col align="center" width="100">	<!-- Created Date			-->
																	<col align="center" width="170">	<!-- Last Update By			-->
																	<col align="center" width="100">	<!-- Last Update Date		-->
																	<tr height="34">
																		<td id="IH0"  class="pClothBs" align="center">&nbsp;</td>
																		<td id="IH1"  class="pClothBs" align="center">Delete/<br>Update</td>
																		<td id="IH2"  class="pClothBs">Qualifier Type</td>
																		<td id="IH3"  class="pClothBs">Value</td>
																		<td id="IH4"  class="pClothBs">Description</td>
																		<td id="IH5"  class="pClothBs">Mdse Type</td>
																		<td id="IH6"  class="pClothBs">Item Type</td>
																		<td id="IH7"  class="pClothBs">Prod Code</td>
																		<td id="IH8"  class="pClothBs">Model</td>
																		<td id="IH9"  class="pClothBs">Price</td>
																		<td id="IH11" class="pClothBs">UOM</td>
																		<td id="IH12" class="pClothBs">Price Break Enabled</td>
																		<td id="IH13" class="pClothBs">Date From</td>
																		<td id="IH14" class="pClothBs">Date To</td>
																		<td id="IH15" class="pClothBs">Created By</td>
																		<td id="IH16" class="pClothBs">Created Date</td>
																		<td id="IH17" class="pClothBs">Update By</td>
																		<td id="IH18" class="pClothBs">Update Date</td>
																	</tr>
																</table>
															</div>
															<div id="rightTbl" style="width:747px; height:354px; display:block; overflow:scroll; margin:0px; padding:0px;"  >
															<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="I" width="2390px" >
																<col align="center" width="30" >	<!-- Select					-->
																<col align="center" width="50" >	<!-- Delete/Update			-->
																<col align="center" width="180">	<!-- Qualifier Type			-->
																<col align="center" width="170">	<!-- Value					-->
																<col align="center" width="170">	<!-- Description 			-->
																<col align="center" width="170">	<!-- Mdse Type	 			-->
																<col align="center" width="170">	<!-- Item Type				-->
																<col align="center" width="170">	<!-- Prod Code				-->
																<col align="center" width="170">	<!-- Model					-->
																<col align="center" width="100">	<!-- Price					-->
																<col align="center" width="130">	<!-- UOM					-->
																<col align="center" width="100">	<!-- Price Break Enabled	-->
																<col align="center" width="120">	<!-- Date From				-->
																<col align="center" width="120">	<!-- Date To				-->
																<col align="center" width="170">	<!-- Created By				-->
																<col align="center" width="100">	<!-- Created Date			-->
																<col align="center" width="170">	<!-- Last Update By			-->
																<col align="center" width="100">	<!-- Last Update Date		-->
																<% int valueOfxxRadioBtn_LI = 0; %>
																<ezf:row ezfHyo="I">
																<tr id="id_row{EZF_ROW_NUMBER}">
																	<td><ezf:inputRadio name="xxRadioBtn_LI" ezfName="xxRadioBtn_LI" value="<%= Integer.toString(valueOfxxRadioBtn_LI) %>" otherAttr=" onclick=\"sendServer('OnChange_QtyDisc', {EZF_ROW_NUMBER});\"" /></td>
																	<td><ezf:inputCheckBox name="xxChkBox_PI" ezfName="xxChkBox_PI" value="Y" ezfHyo="I" ezfArrayNo="0" /></td>
																	<td>
																		<ezf:select name="prcQlfyTpCd_PI" ezfName="prcQlfyTpCd_PI" ezfHyo="I" ezfArrayNo="0" ezfCodeName="prcQlfyTpCd_LI" ezfDispName="prcQlfyTpDescTxt_LI" otherEvent1=" onchange=\"sendServer('OnChange_PrcQlfyTp', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 160px\""/>
																	</td>
																	<td>
																		<ezf:inputText name="prcQlfyValTxt_PI" ezfName="prcQlfyValTxt_PI" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\""/>
																		<ezf:inputButton name="OpenWin_QualifierValueItem" value="..." ezfHyo="I" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_QualifierValueItem#{EZF_ROW_NUMBER}\""/><ezf:inputButton name="OpenWin_QualifierValueProd" value="..." ezfHyo="I" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_QualifierValueProd#{EZF_ROW_NUMBER}\""/><ezf:inputButton name="OpenWin_QualifierValueMdseTp" value="..." ezfHyo="I" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_QualifierValueMdseTp#{EZF_ROW_NUMBER}\""/>
																	</td>
																	<td><ezf:inputText name="prodCtrlNm_PI" ezfName="prodCtrlNm_PI" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
																	<td><ezf:inputText name="coaProjNm_PI" ezfName="coaProjNm_PI" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"40\""/></td>
																	<td><ezf:inputText name="mdseItemTpNm_PI" ezfName="mdseItemTpNm_PI" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"40\""/></td>
																	<td><ezf:inputText name="coaProdNm_PI" ezfName="coaProdNm_PI" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
																	<td><ezf:inputText name="t_MdlNm_PI" ezfName="t_MdlNm_PI" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
																	<td><ezf:inputText name="qtyDiscPrcAmt_PI" ezfName="qtyDiscPrcAmt_PI" value="-123,456,789.12" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
																	<td>
																		<ezf:select name="pkgUomCd_PI" ezfName="pkgUomCd_PI" ezfHyo="I" ezfArrayNo="0" ezfCodeName="pkgUomCd_LI" ezfDispName="pkgUomDescTxt_LI" otherAttr=" style=\"width: 110px\""/>
																	</td>
																	<td><ezf:inputCheckBox name="prcBreakFlg_PI" ezfName="prcBreakFlg_PI" value="Y" ezfHyo="I" ezfArrayNo="0" /></td>
																	<td>
																		<ezf:inputText name="effFromDt_PI" ezfName="effFromDt_PI" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																		<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_PI', 4, '{EZF_ROW_NUMBER}');" >
																	</td>
																	<td>
																		<ezf:inputText name="effThruDt_PI" ezfName="effThruDt_PI" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																		<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_PI', 4, '{EZF_ROW_NUMBER}');" >
																	</td>
																	<td><ezf:inputText name="xxFullNm_I1" ezfName="xxFullNm_I1" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"99\""/></td>
																	<td><ezf:inputText name="cratDt_PI" ezfName="cratDt_PI" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																	<td><ezf:inputText name="xxFullNm_I2" ezfName="xxFullNm_I2" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"99\""/></td>
																	<td><ezf:inputText name="lastUpdDt_PI" ezfName="lastUpdDt_PI" ezfHyo="I" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																</tr>
																<% valueOfxxRadioBtn_LI++; %>
																</ezf:row>
																<ezf:skip>
																</ezf:skip>
															</table>
															</div>
														</div> <!-- right table -->
														</div> <!-- parentBoxI -->
													</td>
												</tr>
											</table>
										</td>
										<td valign="top">
											<table style="table-layout:fixed; " border="1" cellpadding="1" cellspacing="0" width="330px" >
												<col align="center" width="30" >	<!-- Delete/Update			-->
												<col align="center" width="70" >	<!-- Qty					-->
												<col align="center" width="120">	<!-- UOM					-->
												<col align="center" width="110">	<!-- Price Break Amt 		-->
												<tr>
													<td class="pClothBs">Del<br>Upd</td>
													<td class="pClothBs">Qty</td>
													<td class="pClothBs">UOM</td>
													<td class="pClothBs">Price Break Amt</td>
												</tr>
											</table>
											<div id="RightTable_PJ" style="overflow-y:scroll; height:151; overflow-x:hidden; width:347; float:left;">
											<table style="table-layout:fixed; overflow-x:hidden; overflow-y:scroll;" border="1" cellpadding="1" cellspacing="0" width="330px" >
												<col align="center" width="30" >	<!-- Delete/Update			-->
												<col align="center" width="70" >	<!-- Qty					-->
												<col align="center" width="120">	<!-- UOM					-->
												<col align="center" width="110">	<!-- Price Break Amt 		-->
												<ezf:row ezfHyo="J">
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><ezf:inputCheckBox name="xxChkBox_PJ" ezfName="xxChkBox_PJ" value="Y" ezfHyo="J" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="qtyDiscDtlQty_PJ" ezfName="qtyDiscDtlQty_PJ" value="1,234,567,890" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"7\" style=\"text-align:right\""/></td>
													<td>
														<ezf:select name="pkgUomCd_PJ" ezfName="pkgUomCd_PJ" ezfHyo="J" ezfArrayNo="0" ezfCodeName="pkgUomCd_LJ" ezfDispName="pkgUomDescTxt_LJ" otherAttr=" style=\"width: 110px\""/>
													</td>
													<td><ezf:inputText name="prcBreakAmt_PJ" ezfName="prcBreakAmt_PJ" value="-123,456,789.12" ezfHyo="J" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align:right\""/></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
											</div>
										</td>
									</tr>
								</table>
							<script type="text/javascript" defer>
							    S21TableUI.initialize("parentBoxI", "IHEAD", "I", -1, false, true);
							</script>
							</c:when>
							</c:choose>
							</td>
						</tr>
					</table>
					</c:when>
					</c:choose>
					</div>
				</div>
			</td>
		</tr>
	</table>
</center>

<ezf:inputHidden name="xxCellIdx_X1" ezfName="xxCellIdx_X1" otherAttr=" id=\"xxCellIdx_X1\""/>
<ezf:inputHidden name="xxCellIdx_Y1" ezfName="xxCellIdx_Y1" otherAttr=" id=\"xxCellIdx_Y1\""/>

<% if (!"Y".equals(bMsg.xxTabProt_D1.getValue())) { %>
<script type="text/javascript">

// var before = document.forms["mainForm"].elements["prcListTpCd_H1"].selectedIndex;
// function sendCheck(obj) {
// 
//     outputMsg = '<%= bMsg.exMsgTxt_01.getValue() %>';
//     if(window.confirm(outputMsg)) {
//       return true;
//     } else {
//       obj.selectedIndex = before;
//       return false;
//     }
// }

function delConfirm() {
    outputMsg = '<%= bMsg.exMsgTxt_02.getValue() %>';
    if(window.confirm(outputMsg)) {
      return true;
    } else {
      return false;
    }
}

function addCheck() {
    ptab = '<%= bMsg.xxDplyTab_H1.getValue() %>';
    if (ptab == 'CustAudc'
        || ptab == '') {
        elem1 = document.getElementById('delCustAudc');
        elem1.onclick = new Function("if (delConfirm()) {sendServer(this);} else {return false;}");
    } else {
        elem2 = document.getElementById('delTrxAudc');
        elem2.onclick = new Function("if (delConfirm()) {sendServer(this);} else {return false;}");
    }
//    elem3 = document.getElementById('delPrcList');
//    elem3.onclick = new Function("if (delConfirm()) {sendServer(this);} else {return false;}");
}

    addCheck();
</script>
<% } %>

<% if ("Y".equals(bMsg.xxTabProt_D1.getValue())) { %>
<script type="text/javascript" defer>

	<!-- //
	window.attachEvent('onload', setScrollLocation);
	document.getElementById('rightTbl').attachEvent('onscroll', tablescroll1);
    document.getElementById('delPrcList').onclick = new Function("if (delConfirm()) {sendServer(this);} else {return false;}");

	function setScrollLocation() {
		document.getElementById('rightTbl').scrollLeft = <%= bMsg.xxCellIdx_X1.getValueInt() %>;
		document.getElementById('rightTbl').scrollTop  = <%= bMsg.xxCellIdx_Y1.getValueInt() %>;
	}

	function tablescroll1()
	{
		var obj = document.getElementById('rightTbl');
	    document.getElementById('xxCellIdx_X1').value = obj.scrollLeft;
	    document.getElementById('xxCellIdx_Y1').value = obj.scrollTop;
	}

	function checkTerm(obj, idNum) {
		var idName = 'prcTermUomCd_PA' + idNum;
	    var listObj = document.getElementById(idName);
	    if (obj.value.length > 0 && listObj.selectedIndex == 0) {
	        for(i=0; i<listObj.options.length;i++){
	            if(listObj.options[i].text == '<%= bMsg.xxSfxKeyTxt_H1.getValue() %>'){
	                listObj.options[i].selected = true;
	                break;
	            }
	        }
	   }
	}

	function delConfirm() {
		outputMsg = '<%= bMsg.exMsgTxt_02.getValue() %>';
		if(window.confirm(outputMsg)) {
			return true;
		} else {
			return false;
		}
	}
	// -->
</script>

<style type="text/css">
	#rightTbl tr {
		height: 25px;
	}

	#rightTbl td {
		overflow: hidden;
		white-space: nowrap;
	}

	#rightTbl span {
		white-space: nowrap;
	}

	#rightTbl span.pkNm {
		overflow: hidden;
		width: 150px;
		padding: 2px;
		margin-bottom: -4px;
		border: 1px solid #888;
		background-color: #d3d3d3;
	}
</style>
<% } %>


<%-- **** Task specific area ends here **** --%>
