<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20200109133921 --%>
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
			<input type="hidden" name="pageID" value="NMAL7100Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Marketing Programs Setup">
			
<%@ page import="business.servlet.NMAL7100.NMAL7100BMsg" %>
<%@ page import="business.servlet.NMAL7100.NMAL7100_ABMsg" %>
<% NMAL7100BMsg bMsg = (NMAL7100BMsg) databean.getEZDBMsg(); %>

<center>
	<table width="1130" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<!-- ###TAB - HEAD -->
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="1130" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="1120">
									<div>
										<li title="Mkt PGM Set Up" class="pTab_ON"><a href="#">Mkt PGM Set Up</a></li>
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

				<!-- ###TAB - BODY -->
				<div class="pTab_BODY">
					<table border="1" cellpadding="0" cellspacing="0" height="120">
						<col width="500">
						<col width="620">
						<tr>
							<td valign="top" >
								<table cellpadding="0" border="0">
									<col align="left" width="140">
									<col align="left" width="160">
									<col align="left" width="100">
									<col align="left" width="100">
									<tr>
										<td class="stab"><ezf:anchor name="prcMktPrmoPk_LK" ezfName="subMktPrgId_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_MktPrmo" >Marketing Program ID</ezf:anchor></td>
										<td colspan="3">
											<ezf:inputText name="prcMktPrmoPk_H1" ezfName="prcMktPrmoPk_H1" value="445" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/>
											&nbsp;&nbsp;
											<ezf:inputButton name="Search_MktPgm" value="Search" htmlClass="pBtn6" otherAttr=" id=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Marketing Program Name</td>
										<td colspan="3">
											<ezf:inputText name="prcMktPrmoNm_H1" ezfName="prcMktPrmoNm_H1" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Marketing Program Description</td>
										<td colspan="3">
											<ezf:inputText name="prcMktPrmoDescTxt_H1" ezfName="prcMktPrmoDescTxt_H1" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Comments/Notes List</td>
										<td colspan="3">
											<ezf:inputText name="prcMktPrmoCmntTxt_H1" ezfName="prcMktPrmoCmntTxt_H1" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Marketing Program Type</td>
										<td>
											<ezf:select name="prcMktPrmoTpCd_H1" ezfName="prcMktPrmoTpCd_H1" ezfCodeName="prcMktPrmoTpCd_L1" ezfDispName="prcMktPrmoTpDescTxt_L1" otherAttr=" style=\"width: 150px\""/>
										</td>
										<td class="stab">Effective Date From</td>
										<td>
											<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="01/01/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
										</td>
									</tr>
									<tr>
										<td class="stab"></td>
										<td class="stab"></td>
										<td class="stab">Effective Date To</td>
										<td>
											<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" value="01/01/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" >
										</td>
									</tr>
									<tr>
										<td class="stab"></td>
										<td class="stab"></td>
										<td class="stab">Active</td>
										<td>
											<ezf:inputCheckBox name="actvFlg_H1" ezfName="actvFlg_H1" value="Y" />
										</td>
									</tr>
								</table>
							</td>

<!-- <%-- ######################################## FROM Marketing Program Audience ######################################## --%> -->
							<td valign="top">
								<table width="610">
									<col valign="top">
									<tr>
										<td class="pClothBs" align="center">Marketing Program Audience</td>
									</tr>
									<tr>
										<td>
											<div class="pTab_HEAD">
											<ul>
												<li class="pTab_ON"  id="MktAud" title="MktAud"><ezf:anchor name="" ezfName="xxTabProt_H1" ezfEmulateBtn="1" ezfGuard="TAB_MktAud" >Mkt Aud</ezf:anchor></li>
												<li class="pTab_OFF" id="CanNotBeUsed"  title="CanNotBeUsed"><ezf:anchor name="" ezfName="xxTabProt_H2" ezfEmulateBtn="1" ezfGuard="TAB_CanNotBeUsed" >CanNotBeUsed</ezf:anchor></li>
											</ul>
											</div>
<!-- <%-- ######################################## FROM HEADER Marketing Program Audidence ######################################## --%> -->
											<c:choose>
											<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'MktAud'}">
											<script type="text/javascript">
												document.getElementById( "MktAud").className = "pTab_ON";
												document.getElementById( "CanNotBeUsed" ).className = "pTab_OFF";
											</script>

											<div class="pTab_BODY_In"  id="MktAud_div">
												<table border="0" cellpadding="1" cellspacing="0" width="610">
													<col width="80" align="right" valign="top">
													<col width="260" align="center" valign="top">
													<col width="90" align="center" valign="top">
													<col width="80" align="center" valign="top">
													<col width="80" align="center" valign="top">
													<tr>
														<td class="stab" valign="middle">File Upload</td>
														<td><ezf:inputFile name="xxFileData_HC" ezfName="xxFileData_HC" otherAttr=" size=\"20\" maxlength=\"9999\""/></td>
														<td><ezf:inputButton name="Upload_MktAud" value="Upload Customer" htmlClass="pBtn8" /></td>
														<td><ezf:inputButton name="Add_MktAud" value="Insert Row" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="Del_MktAud" value="Delete Row" htmlClass="pBtn6" otherAttr=" id=\"delMktAud\""/></td>
													</tr>
												</table>
												<table border="0" cellpadding="1" cellspacing="0" width="610">
													<tr>
														<td>
															<%-- ### Marketing Program Audidence - Left TBL - TOP --%>
															<div id="LeftTBL_Top_MktAud" style="overflow-x:none; overflow-y:none; width:25; height:25; float:left;">
																<table border="1" cellpadding="1" cellspacing="0" width="25" height="25">
																	<col align="center" width="25">
																	<tr>
																		<td class="pClothBs"></td>
																	</tr>
																</table>
															</div>
															<%-- ### Marketing Program Audidence - Right TBL - TOP --%>
															<div id="RightTBL_Top_MktAud" style="overflow-x:hidden; overflow-y:hidden; width:565; height:25; float:left;">
																<table border="1" cellpadding="1" cellspacing="0" width="1840px" height="25">
																	<col align="center" width="150">
																	<col align="center" width="150">
																	<col align="center" width="180">
																	<col align="center" width="150">
																	<col align="center" width="150">
																	<col align="center" width="180">
																	<col align="center" width="150">
																	<col align="center" width="150">
																	<col align="center" width="180">
																	<col align="center" width="110">
																	<col align="center" width="110">
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
																		<td class="pClothBs">Date From</td>
																		<td class="pClothBs">Date To</td>
																	</tr>
																</table>
															</div>
															<%-- ### Marketing Program Audidence - Left TBL - BOTTOM --%>
															<div id="LeftTBL_MktAud" style="overflow-x:hidden; overflow-y:hidden; height:103; width:25; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL_MktAud' ))" >
																<table border="1" cellpadding="1" cellspacing="0" width="25" id="HC1">
																	<col align="center" width="25">
																	<ezf:row ezfHyo="X">
																		<tr id="id_X_row{EZF_ROW_NUMBER}" height="25">
																			<td><ezf:inputCheckBox name="xxChkBox_MX" ezfName="xxChkBox_MX" value="Y" ezfHyo="X" ezfArrayNo="0" /></td>
																		</tr>
																	</ezf:row>
																	<ezf:skip>
																	</ezf:skip>
																</table>
															</div>
															<%-- ### Marketing Program Audidence - Right TBL - BOTTOM --%>
															<div id="RightTBL_MktAud" style="overflow-x:scroll; overflow-y:scroll; height:120; width:582; float:left; table-layout: fixed;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL_MktAud' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top_MktAud' ));" >
																<table border="1" cellpadding="1" cellspacing="0" width="1840px" id="HC2">
																	<col align="center" width="150">
																	<col align="center" width="120">
																	<col align="center" width="30">
																	<col align="center" width="180">
																	<col align="center" width="140">
																	<col align="center" width="120">
																	<col align="center" width="30">
																	<col align="center" width="180">
																	<col align="center" width="140">
																	<col align="center" width="120">
																	<col align="center" width="30">
																	<col align="center" width="180">
																	<col align="center" width="110">
																	<col align="center" width="110">
																	<ezf:row ezfHyo="X">
																		<tr id="id_X_row{EZF_ROW_NUMBER}" height="25">
																			<td>
																				<ezf:select name="mktPrmoAudcCatgCd_X1" ezfName="mktPrmoAudcCatgCd_X1" ezfHyo="X" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mktPrmoAudcCatgCd_L1" ezfDispName="mktPrmoAudcCatgDescTxt_L1" otherEvent1=" onchange=\"sendServer('OnChange_MktPrmoAudcCatg_01', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 150px\""/>
																			</td>
																			<td style="border-right-style:none;">
																				<ezf:inputText name="xxScrItem30Txt_X1" ezfName="xxScrItem30Txt_X1" value="ESS" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"28\" id=\"xxScrItem30Txt_X1#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																				<!--input type="checkbox" value="Y" name="pubFlg_CX" ezfname="pubFlg_CX" ezfHyo="X" id="pubFlg_CX#{EZF_ROW_NUMBER}" -->
																			</td>
																			<td style="border-left-style:none; ">
																				<ezf:inputButton name="OpenWin_MktAudVal_01_Cmn" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MktAudVal_01_Cmn#{EZF_ROW_NUMBER}\""/>
																				<ezf:inputButton name="OpenWin_MktAudVal_01_Acc" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MktAudVal_01_Acc#{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td><ezf:inputText name="xxRecNmTxt_X1" ezfName="xxRecNmTxt_X1" value="Enterprise Service & Solution" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
																			<td>
																				<ezf:select name="mktPrmoAudcCatgCd_X2" ezfName="mktPrmoAudcCatgCd_X2" ezfHyo="X" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mktPrmoAudcCatgCd_L2" ezfDispName="mktPrmoAudcCatgDescTxt_L2" otherEvent1=" onchange=\"sendServer('OnChange_MktPrmoAudcCatg_02', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 150px\""/>
																			</td>
																			<td style="border-right-style:none;">
																				<ezf:inputText name="xxScrItem30Txt_X2" ezfName="xxScrItem30Txt_X2" value="228" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/>
																			</td>
																			<td style="border-left-style:none; ">
																				<ezf:inputButton name="OpenWin_MktAudVal_02_Cmn" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MktAudVal_02_Cmn#{EZF_ROW_NUMBER}\""/>
																				<ezf:inputButton name="OpenWin_MktAudVal_02_Acc" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MktAudVal_02_Acc#{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td><ezf:inputText name="xxRecNmTxt_X2" ezfName="xxRecNmTxt_X2" value="MIDTOWN_WEST" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>	
																			<td>
																				<ezf:select name="mktPrmoAudcCatgCd_X3" ezfName="mktPrmoAudcCatgCd_X3" ezfHyo="X" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mktPrmoAudcCatgCd_L3" ezfDispName="mktPrmoAudcCatgDescTxt_L3" otherEvent1=" onchange=\"sendServer('OnChange_MktPrmoAudcCatg_03', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 150px\""/>
																			</td>
																			<td style="border-right-style:none;">
																				<ezf:inputText name="xxScrItem30Txt_X3" ezfName="xxScrItem30Txt_X3" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/>
																			</td>
																			<td style="border-left-style:none; ">
																				<ezf:inputButton name="OpenWin_MktAudVal_03_Cmn" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MktAudVal_03_Cmn#{EZF_ROW_NUMBER}\""/>
																				<ezf:inputButton name="OpenWin_MktAudVal_03_Acc" value="..." ezfHyo="X" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MktAudVal_03_Acc#{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td><ezf:inputText name="xxRecNmTxt_X3" ezfName="xxRecNmTxt_X3" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\""/></td>
																			<td>
																				<ezf:inputText name="effFromDt_MX" ezfName="effFromDt_MX" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_MX', 4, '{EZF_ROW_NUMBER}');" >
																			</td>
																			<td>
																				<ezf:inputText name="effThruDt_MX" ezfName="effThruDt_MX" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_MX', 4, '{EZF_ROW_NUMBER}');" >
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
<!-- <%-- ######################################## TO HEADER Marketing Program Audidence ######################################## --%> -->
											</c:when>

											<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'CanNotBeUsed'}">
<!-- <%-- ######################################## FROM HEADER CanNotBeUsed ######################################## --%> -->
											<script type="text/javascript">
												document.getElementById( "MktAud").className = "pTab_OFF";
												document.getElementById( "CanNotBeUsed" ).className = "pTab_ON";
											</script>
											<div class="pTab_BODY_In"  id="CanNotBeUsed_div">
												<table border="0" cellpadding="1" cellspacing="0" width="610" >
													<col width="" align="right" valign="top">
													<col width="100" align="right" valign="top">
													<col width="260" align="center" valign="top">
													<col width="90" align="center" valign="top">
													<col width="80" align="center" valign="top">
													<col width="80" align="center" valign="top">
													<tr>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td><ezf:inputButton name="Add_CanNotBeUsed" value="Insert Row" htmlClass="pBtn6" /></td>
														<td><ezf:inputButton name="Del_CanNotBeUsed" value="Delete Row" htmlClass="pBtn6" otherAttr=" id=\"delCanNotBeUsed\""/></td>
													</tr>
												</table>
												<table border="0" cellpadding="1" cellspacing="0" width="610">
													<tr>
														<td>
															<%-- ### CanNotBeUsed - Left TBL - TOP --%>
															<div id="LeftTBL_Top_CanNotBeUsed" style="overflow-x:none; overflow-y:none; width:25; height:25; float:left;">
																<table border="1" cellpadding="1" cellspacing="0" width="25" height="25">
																	<col align="center" width="25">
																	<tr>
																		<td class="pClothBs"></td>
																	</tr>
																</table>
															</div>
															<%-- ### CanNotBeUsed - Right TBL - TOP --%>
															<div id="RightTBL_Top_CanNotBeUsed" style="overflow-x:hidden; overflow-y:hidden; width:565; height:25; float:left;">
																<table border="1" cellpadding="1" cellspacing="0" width="740" height="25">
																	<col align="center" width="220">
																	<col align="center" width="260">
																	<col align="center" width="120">
																	<col align="center" width="120">
																	<tr>
																		<td class="pClothBs">Price List Type</td>
																		<td class="pClothBs">Price List Name</td>
																		<td class="pClothBs">Date From</td>
																		<td class="pClothBs">Date To</td>
																	</tr>
																</table>
															</div>
															<%-- ### CanNotBeUsed - Left TBL - BOTTOM --%>
															<div id="LeftTBL_CanNotBeUsed" style="overflow-x:hidden; overflow-y:hidden; height:103; width:25; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL_CanNotBeUsed' ))" >
																<table border="1" cellpadding="1" cellspacing="0" width="25" id="HT1">
																	<col align="center" width="25">
																	<ezf:row ezfHyo="Y">
																		<tr id="id_Y_row{EZF_ROW_NUMBER}" height="25">
																			<td><ezf:inputCheckBox name="xxChkBox_CX" ezfName="xxChkBox_CX" value="Y" ezfHyo="Y" ezfArrayNo="0" /></td>
																		</tr>
																	</ezf:row>
																	<ezf:skip>
																	</ezf:skip>
																</table>
															</div>
															<%-- ### CanNotBeUsed - Right TBL - BOTTOM --%>
															<div id="RightTBL_CanNotBeUsed" style="overflow-x:scroll; overflow-y:scroll; height:120; width:582; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL_CanNotBeUsed' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top_CanNotBeUsed' ));" >
																<table border="1" cellpadding="1" cellspacing="0" width="740" id="HT2">
																	<col align="center" width="220">
																	<col align="center" width="260">
																	<col align="center" width="120">
																	<col align="center" width="120">
																	<ezf:row ezfHyo="Y">
																		<tr id="id_Y_row{EZF_ROW_NUMBER}" height="25">
																			<td>
																				<ezf:select name="prcListTpCd_CX" ezfName="prcListTpCd_CX" ezfHyo="Y" ezfArrayNo="0" ezfCodeName="prcListTpCd_L1" ezfDispName="prcListTpDescTxt_L1" otherAttr=" style=\"width: 200px\""/>
																			</td>
																			<td>
																				<ezf:inputText name="prcCatgNm_CX" ezfName="prcCatgNm_CX" value="LEASE" ezfHyo="Y" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"75\" ezftoupper=\"\""/>
																				<ezf:inputButton name="OpenWin_PrcCatg" value="..." ezfHyo="Y" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcCatg#{EZF_ROW_NUMBER}\""/>
																			</td>
																			<td>
																				<ezf:inputText name="effFromDt_CX" ezfName="effFromDt_CX" ezfHyo="Y" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_CX', 4, '{EZF_ROW_NUMBER}');" >
																			</td>
																			<td>
																				<ezf:inputText name="effThruDt_CX" ezfName="effThruDt_CX" ezfHyo="Y" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_CX', 4, '{EZF_ROW_NUMBER}');" >
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
<!-- <%-- ######################################## TO HEADER CanNotBeUsed ######################################## --%> -->
											</c:when>
											</c:choose>
										</td>
									</tr>
								</table>
<!-- <%-- ######################################## TO Marketing Program Audience ######################################## --%> -->
							</td>
						</tr>
					</table>

<!-- <%-- ######################################## DETAIL ######################################## --%> -->
<!-- <%-- ######################################## FROM DETAIL HEADER ########################################## --%> -->

					<table width="1120">
						<col valign="top">
						<tr>
							<td>
								<div border="0">
									<table  border="0" cellpadding="0" cellspacing="0" width="1110" style="table-layout: fixed;">
										<col width="230" align="left" valign="top">
										<col width="100" align="left" valign="top">
										<col width="250" align="left" valign="top">
										<col width="500" align="right" valign="top">
										<tr>
											<td>
												<table border="0" cellpadding="1" cellspacing="0">
													<tr>
														<td class="stab" valign="middle">Show Records</td>
														<td valign="middle">
															<ezf:select name="prcMktPrmoStsCd_DH" ezfName="prcMktPrmoStsCd_DH" ezfCodeName="prcMktPrmoStsCd_L1" ezfDispName="prcMktPrmoStsDescTxt_L1" otherAttr=" style=\"width: 150px\""/>
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
														<td>
															<table border="0" cellpadding="1" cellspacing="0">
																<col width="15">
																<tr>
																	<td>&nbsp;</td>
																	<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn7" /></Td>
																	<td><ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn7" /></Td>
																</tr>
															</table>
														</td>
														<td>&nbsp;</td>
													</tr>
												</table>
											</td>
											<td>
												<table border="0" cellpadding="1" cellspacing="0">
													<tr>
														<td><ezf:inputButton name="Add_Detail" value="Insert Row" htmlClass="pBtn6" /></Td>
														<td></td>
														<td><ezf:inputButton name="Del_Detail" value="Delete Row" htmlClass="pBtn6" otherAttr=" id=\"delDetail\""/></Td>
														<td>&nbsp;</td>
														<td class="stab" valign="middle">Eff Date To</td>
														<td>
															<ezf:inputText name="effThruDt_DH" ezfName="effThruDt_DH" otherAttr=" size=\"10\" maxlength=\"10\""/>
															<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_DH', 4);" >
														</td>
														<td><ezf:inputButton name="MassUpd_MktList" value="MASS Update" htmlClass="pBtn7" /></Td>
													</tr>
												</table>
											</td>
										</tr>
									</table>

									<table  border="0" cellpadding="0" width="100%" style="table-layout: fixed;">
										<col width="200" align="left"   valign="top">
										<col width="850" align="left" valign="top">
										<col width="50" align="right"  valign="top">
										<tr>
											<td valign="middle">
												<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
											</td>
											<td>
												<table border="0" cellpadding="1" cellspacing="0">
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td class="stab" valign="middle">File Upload</td>
													<td><ezf:inputFile name="xxFileData_DH" ezfName="xxFileData_DH" otherAttr=" size=\"20\" maxlength=\"9999\""/></td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="Upload_MktPgm" value="Upload MrkPgm" htmlClass="pBtn7" /></td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="Download_Temlate" value="Template" htmlClass="pBtn6" /></td>
												</table>
											</td>
											<td>
											</td>
										</tr>
									</table>

									<table  border="0" cellpadding="0" cellspacing="0" width="100%" style="table-layout: fixed;">
										<col width="570" align="left"   valign="top">
										<col width="530" align="right"  valign="top">
										<tr>
											<td>&nbsp;</td>
											<td>
												<ezf:skip>
												<table border="0" cellpadding="0" cellspacing="0" width="100%">
													<tr>
														<td align="right">
															<table border="0" cellpadding="0" align="left" cellspacing="0">
																<col>
																	<tr>
																		<td>Results 999 - 999 of 999</td>
																	</tr>
															</table>
														</td>
														<td align="right">
															<table border="0" cellpadding="0" cellspacing="0">
																<col width="54"  align="center">
																<col width="40"  align="center">
																<col width="16"  align="center">
																<col width="40"  align="center">
																<col width="26"  align="center">
																<col width="10">
																<col>
																<col width="20">
																<col>
																<col width="1">
																<col>
																<tr>
																	<td class="stab">Showing</td>
																	<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right" ezfname="xxPageShowCurNum"></td>
																	<td class="stab">/</td>
																	<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																	<td class="stab">page</td>
																	<td>&nbsp;</td>
																	<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'F')"></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'F')"></td>
																	<td></td>
																	<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'F')"></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
												</ezf:skip>
												<table cellpadding="0" cellspacing="0" width="100%">
													<tr align="right">
														<td>
															<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
																<jsp:param name="TableNm"           value="A" />
																<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
																<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
																<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
																<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
																<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
																<jsp:param name="ViewMode"          value="FULL" />
															</jsp:include>
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

<!-- <%-- ######################################## TO DETAIL HEADER ########################################## --%> -->
<!-- <%-- ######################################## FROM DETAIL ######################################## --%> -->
					<table width="1120">
						<tr>
							<td width="10"></td>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id="leftTblHead" style="display:block;">
										</div>
										<div id="leftTbl" style="float:left; display:block; height:196px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id="rightTblHead" style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0"  id="AHEAD" width="1530px" style="margin-right:20px" >
											<col align="center" width="50" >	<!-- Delete/Update			-->
											<col align="center" width="135">	<!-- Marketing Program Code -->
											<col align="center" width="170">	<!-- Promotion Qualifier	-->
											<col align="center" width="195">	<!-- Qualifier Value		-->
											<col align="center" width="165">	<!-- Promotion Item Code	-->
											<col align="center" width="160">	<!-- Item Description		-->
											<col align="center" width="105">	<!-- Promotion Value		-->
											<col align="center" width="120">	<!-- Date From				-->
											<col align="center" width="120">	<!-- Date To				-->
											<col align="center" width="170">	<!-- Created By				-->
											<col align="center" width="100">	<!-- Created Date			-->
											<col align="center" width="170">	<!-- Last Update By			-->
											<col align="center" width="100">	<!-- Last Update Date		-->
											<tr height="36">
												<td id="AH0"  class="pClothBs" align="center">Delete/<br>Update</td>
												<td id="AH1"  class="pClothBs">Marketing Program Code</td>
												<td id="AH2"  class="pClothBs">Promotion Qualifier</td>
												<td id="AH3"  class="pClothBs">Qualifier Value</td>
												<td id="AH4"  class="pClothBs">Promotion Item Code</td>
												<td id="AH5"  class="pClothBs">Item Description</td>
												<td id="AH6"  class="pClothBs">Promotion Value</td>
												<td id="AH7"  class="pClothBs">Date From</td>
												<td id="AH8"  class="pClothBs">Date To</td>
												<td id="AH9"  class="pClothBs">Created By</td>
												<td id="AH10"  class="pClothBs">Created Date</td>
												<td id="AH11" class="pClothBs">Update By</td>
												<td id="AH12" class="pClothBs">Update Date</td>
											</tr>
											</table>
										</div>
										<div id="rightTbl" style="width:1097px; height:213px; display:block; overflow:scroll; margin:0px; padding:0px;" >
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1530px" >
											<col align="center" width="50" >	<!-- Delete/Update			-->
											<col align="left" width="135">	<!-- Marketing Program Code -->
											<col align="left" width="170">	<!-- Promotion Qualifier	-->
											<col align="left" width="195">	<!-- Qualifier Value		-->
											<col align="left" width="165">	<!-- Promotion Item Code	-->
											<col align="left" width="160">	<!-- Item Description		-->
											<col align="left" width="105">	<!-- Promotion Value		-->
											<col align="left" width="120">	<!-- Date From				-->
											<col align="left" width="120">	<!-- Date To				-->
											<col align="left" width="170">	<!-- Created By				-->
											<col align="left" width="100">	<!-- Created Date			-->
											<col align="left" width="170">	<!-- Last Update By			-->
											<col align="left" width="100">	<!-- Last Update Date		-->
											<% int i = 0; %>
											<ezf:row ezfHyo="A">
											<% NMAL7100_ABMsg lineMsg = bMsg.A.no(i++); %>
											<tr id="id_row{EZF_ROW_NUMBER}" height="24">
												<td><ezf:inputCheckBox name="xxChkBox_DA" ezfName="xxChkBox_DA" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_DA#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="prcMktPrmoCd_DA" ezfName="prcMktPrmoCd_DA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
												<td>
													<ezf:select name="prcPrmoQlfyTpCd_DA" ezfName="prcPrmoQlfyTpCd_DA" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prcPrmoQlfyTpCd_L1" ezfDispName="prcPrmoQlfyTpDescTxt_L1" otherEvent1=" onchange=\"sendServerForPreferredView('OnChange_PrmoQlfy', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 150px\""/>
                                                </td>
												<td>
													<ezf:inputText name="prcQlfyValTxt_DA" ezfName="prcQlfyValTxt_DA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
													<% boolean isDispItemBtn = "I".equals(lineMsg.xxBtnFlg_DA.getValue()); %>
													<% boolean isDispMdlBtn = "M".equals(lineMsg.xxBtnFlg_DA.getValue()); %>
													<% if (isDispItemBtn) { %>
	                                                    <ezf:inputButton name="OpenWin_PrmoQlfy_Item" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrmoQlfy_Item#{EZF_ROW_NUMBER}\""/>
													<% } else if (isDispMdlBtn) { %>
                                                    	<ezf:inputButton name="OpenWin_PrmoQlfy_Mdl" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrmoQlfy_Mdl#{EZF_ROW_NUMBER}\""/>
													<% } %>
                                                </td>
                                                <td>
                                                	<ezf:inputText name="mdseCd_DA" ezfName="mdseCd_DA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
                                                    <ezf:inputButton name="OpenWin_Prmo_Item" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Prmo_Item#{EZF_ROW_NUMBER}\""/>
                                                </td>
												<td><ezf:inputText name="mdseDescShortTxt_DA" ezfName="mdseDescShortTxt_DA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="prmoAmt_DA" ezfName="prmoAmt_DA" value="-123,456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"19\" style=\"text-align:right\""/></td>
												<td>
													<ezf:inputText name="effFromDt_DA" ezfName="effFromDt_DA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_DA', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td>
													<ezf:inputText name="effThruDt_DA" ezfName="effThruDt_DA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_DA', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td><ezf:inputText name="xxFullNm_D1" ezfName="xxFullNm_D1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"99\""/></td>
												<td><ezf:inputText name="xxCratDt_DA" ezfName="xxCratDt_DA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												<td><ezf:inputText name="xxFullNm_D2" ezfName="xxFullNm_D2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"99\""/></td>
												<td><ezf:inputText name="xxLastEntryDt_DA" ezfName="xxLastEntryDt_DA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
											</tr>
											</ezf:row>
										</table>
										</div>
									</div> <!-- right table -->
								</div> <!-- parentBoxA -->
							</td>
						</tr>
					</table>
					<script type="text/javascript" defer>
					    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, false);
					</script>
				</div>
			</td>
		</tr>
	</table>
</center>

<script type="text/javascript">

function delConfirm() {
    outputMsg = '<%= bMsg.exMsgTxt_01.getValue() %>';
    if(window.confirm(outputMsg)) {
      return true;
    } else {
      return false;
    }
}

function addConfirmScript() {
    var elem = document.getElementById('delMktAud');

    if(elem != null) {
        elem.onclick = new Function("if (delConfirm()) {sendServer(this);} else {return false;}");
    }
    
    var elem = document.getElementById('delCanNotBeUsed');
    if(elem != null) {
        elem.onclick = new Function("if (delConfirm()) {sendServer(this);} else {return false;}");
    }

    var elem = document.getElementById('delDetail');
    if(elem != null) {
        elem.onclick = new Function("if (delConfirm()) {sendServer(this);} else {return false;}");
    }
}

    addConfirmScript();

</script>




<%-- **** Task specific area ends here **** --%>
