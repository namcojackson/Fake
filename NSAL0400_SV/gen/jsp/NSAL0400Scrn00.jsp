<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220209093735 --%>
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
			<input type="hidden" name="pageID" value="NSAL0400Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Termination">

	<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td>

				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%">
									<li title = "Termination" class="pTab_ON" ><a href="#">Termination</a></li>
								</td>

								<td valign="bottom" align="center">
									<a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>

							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NSAL0400.NSAL0400BMsg" %>
<%@ page import="business.servlet.NSAL0400.NSAL0400_ABMsg" %>
<%@ page import="business.servlet.NSAL0400.NSAL0400_BBMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%  NSAL0400BMsg bMsg = (NSAL0400BMsg)databean.getEZDBMsg(); %>

			<div class="pTab_BODY" style="width:100%; text-align:center;" >
				<table height="" border="0" cellspacing="0" cellpadding="0">
					<col width="5">
					<col valign="top">
					<tr>
					<td />
					<td>
						<!-- Header -->
						<table border="0" style="table-layout:fixed;">
							<col width="90">	<!-- Termination Date -->
							<col width="100">
							<col width="50">
							<col width="30">	<!-- Suppress Credit -->
							<col width="105">
							<col width="50">
							<col width="30">	<!-- All Period Terminate -->
							<col width="105">
							<col width="">
							<tr><td height="10"></td></tr>
							<tr>
								<td class="stab">Termination Date</td>
								<td class="stab"><ezf:inputText name="contrCloDt_H" ezfName="contrCloDt_H" otherAttr=" size=\"10\" maxlength=\"10\""/>
								&nbsp;<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrCloDt_H', 4);"></td>
								<td>&nbsp;</td>
								<td class="stab"><ezf:inputCheckBox name="supprCrFlg_H" ezfName="supprCrFlg_H" value="Y" /></td>
								<td class="stab">Suppress Credit</td>
								<td>&nbsp;</td>
								<td class="stab"><ezf:inputCheckBox name="contrTrmnFlg_H" ezfName="contrTrmnFlg_H" value="Y" /></td>
								<td class="stab">All Period Terminate</td>
							</tr>
						</table>
						<table border="0" style="table-layout:fixed;">
							<col width="90">
							<col width="560">
							<col width="110">
							<col width="110">
							<tr>
								<td class="stab">Reason Code</td>
								<td>
									<ezf:select name="svcMemoRsnCd_FS" ezfName="svcMemoRsnCd_FS" ezfBlank="1" ezfCodeName="svcMemoRsnCd_FC" ezfDispName="xxScrItem130Txt_FD" otherAttr=" id=\"svcMemoRsnCd\""></ezf:select>
								</td>
							</tr>
							<tr>
								<td class="stab">Comment</td>
								<td>
									<ezf:textArea name="svcCmntTxt_F" ezfName="svcCmntTxt_F" otherAttr=" cols=\"76\" rows=\"5\""/>
								</td>
								<td class="stab"><ezf:inputButton name="Apply" value="Apply to All" htmlClass="pBtn8" /></td>
								<td class="stab"><ezf:inputButton name="Review" value="Review" htmlClass="pBtn8" /></td>
							</tr>
						</table>

						<div style="overflow-x:hidden; overflow-y:scroll; height:430px;">
							<table border="0" style="table-layout:fixed;">
								<tr>
									<% int hdrIdx = 0; %>
									<ezf:row ezfHyo="B">
									<c:set var="hdrDsContrPk" scope="page" value="<%= bMsg.B.no(hdrIdx).dsContrPk_B.getValue()%>" />
									<!-- Detail -->
									<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0">
										<col width="480">
										<col width="600">
										<tr height="20">
											<td class="pClothBs">Contract Details</td>
											<td class="pClothBs">Termination Details</td>
										</tr>
										<tr>
											<td>
												<!-- Contract Details -->
												<table border="0" style="table-layout:fixed;">
													<col width="104">
													<col width="168">
													<col width="40">
													<col width="168">
													<tr>
														<td class="stab">Contract#</td>
														<td class="stab"><ezf:inputText name="xxScrItem40Txt_B" ezfName="xxScrItem40Txt_B" ezfHyo="B" ezfArrayNo="<%=hdrIdx %>" otherAttr=" size=\"20\" maxlength=\"30\""/>
													</tr>
													<tr>
														<td class="stab">Start Date</td>
														<td class="stab"><ezf:inputText name="contrVrsnEffFromDt_B" ezfName="contrVrsnEffFromDt_B" ezfHyo="B" ezfArrayNo="<%=hdrIdx %>" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<td class="stab">Status</td>
														<td class="stab"><ezf:inputText name="dsContrCtrlStsNm_B" ezfName="dsContrCtrlStsNm_B" ezfHyo="B" ezfArrayNo="<%=hdrIdx %>" otherAttr=" size=\"20\" maxlength=\"50\""/>
													</tr>
													<tr>
														<td class="stab">End Date</td>
														<td class="stab"><ezf:inputText name="contrVrsnEffThruDt_B" ezfName="contrVrsnEffThruDt_B" ezfHyo="B" ezfArrayNo="<%=hdrIdx %>" otherAttr=" size=\"10\" maxlength=\"10\""/>
													</tr>
													<tr>
														<td class="stab">Customer Name</td>
														<td class="stab" colspan="3"><ezf:inputText name="dsAcctNm_B" ezfName="dsAcctNm_B" ezfHyo="B" ezfArrayNo="<%=hdrIdx %>" otherAttr=" size=\"45\" maxlength=\"360\""/>
													</tr>
												</table>
											</td>
											
											<td>
												<!-- Termination Details -->
												<table border="0" style="table-layout:fixed;">
													<col width="128">
													<col width="168">
													<col width="30">
													<col width="168">
													<tr>
														<td class="stab">Termination Date</td>
														<td class="stab"><ezf:inputText name="contrCloDt_B" ezfName="contrCloDt_B" ezfHyo="B" ezfArrayNo="<%=hdrIdx %>" otherAttr=" size=\"10\" maxlength=\"10\""/>
														&nbsp;<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrCloDt_B', 4, <%=hdrIdx %>);"></td>
													</tr>
													<tr>
														<td class="stab">Amount</td>
														<td class="stab"><ezf:inputText name="trmnTotAmt_B" ezfName="trmnTotAmt_B" ezfHyo="B" ezfArrayNo="<%=hdrIdx %>" otherAttr=" size=\"20\" maxlength=\"25\""/>
														<td class="stab"><ezf:inputCheckBox name="supprCrFlg_B" ezfName="supprCrFlg_B" value="Y" ezfHyo="B" ezfArrayNo="<%=hdrIdx %>" /></td>
														<td class="stab">Suppress Credit</td>
													</tr>
													<tr>
														<td class="stab">Override Amount</td>
														<td class="stab"><ezf:inputText name="trmnOvrdTotAmt_B" ezfName="trmnOvrdTotAmt_B" ezfHyo="B" ezfArrayNo="<%=hdrIdx %>" otherAttr=" size=\"20\" maxlength=\"25\""/>
														<td class="stab"><ezf:inputCheckBox name="contrTrmnFlg_B" ezfName="contrTrmnFlg_B" value="Y" ezfHyo="B" ezfArrayNo="<%=hdrIdx %>" /></td>
														<td class="stab">All Period Terminate</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									
									<table  border="0" cellpadding="0" cellspacing="0">
										<col width="" align="" valign="top">
										<tr>
											<td valign="top">
												<!-- Left Table Header -->
												<table style="table-layout:fixed; width:339px;" border="1" cellpadding="1" cellspacing="0" >
													<col width="24" align="center">	<!-- chkBox -->
													<col width="95" align="center">	<!-- MdseCd -->
													<col width="100" align="center">	<!-- serNum -->
													<col width="120" align="center">	<!-- Model -->
													<tr height="37">
														<td class="pClothBs">ALL<br><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="<%=hdrIdx %>" onClick="sendServer('ClickAll', '{EZF_ROW_NUMBER}')" /></td>
														<td class="pClothBs">Item Cd</td>
														<td class="pClothBs">Serial#</td>
														<td class="pClothBs">Model</td>
													</tr>
												</table>
												<!-- Left Table Detail -->
												<div id="LeftTBL#{EZF_ROW_NUMBER}" style="overflow-x:hidden; overflow-y:hidden;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBL#{EZF_ROW_NUMBER}' ) );">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A1" >
														<col width="24" align="center">	<!-- chkBox -->
														<col width="95" align="center">	<!-- MdseCd -->
														<col width="100" align="center">	<!-- serNum -->
														<col width="120" align="center">	<!-- Model -->
														<% int leftIdx = 0; %>
														<ezf:row ezfHyo="A">
															<c:set var="dsContrPk" scope="page" value="<%= bMsg.A.no(leftIdx).dsContrPk_AH.getValue()%>" />
															<% leftIdx++; %>
															<c:choose>
															    <c:when test="${dsContrPk == hdrDsContrPk}">
																	<tr height="25" id="A_leftTBLRow#{EZF_ROW_NUMBER}">
																		<td align="center"><ezf:inputCheckBox name="xxChkBox_AD" ezfName="xxChkBox_AD" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_AD#{EZF_ROW_NUMBER}\""/></td>
																		<td align="center"><ezf:inputText name="mdseCd_AD" ezfName="mdseCd_AD" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"16\""/></td>
																		<td align="center"><ezf:inputText name="serNum_AD" ezfName="serNum_AD" value="123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/></td>
																		<td align="center"><ezf:inputText name="t_MdlNm_AD" ezfName="t_MdlNm_AD" value="WWWWWWWW10WWWWWWWW20" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
																	</tr>
																</c:when>
															</c:choose>
														</ezf:row>
														<ezf:skip>
														</ezf:skip>
													</table>
												</div>
											</td>
											<td valign="top">
												<!-- Right Table Header -->
												<div id="topRightTBL#{EZF_ROW_NUMBER}" style="overflow-x:hidden; width:741; overflow-y:none;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
														<col width="130" align="center">	<!-- Loc -->
														<col width="95" align="center">	<!-- Status -->
														<col width="83" align="center">	<!-- StartDt -->
														<col width="83" align="center">	<!-- EndDt -->
														<col width="83" align="center">	<!-- Last Read -->
														<col width="105" align="center">	<!-- TerminationDt -->
														<col width="80" align="center">	<!-- Amount -->
														<col width="80" align="center">	<!-- OverrideAmount -->
														<col width="80" align="center">	<!-- SprsCR -->
														<col width="35" align="center">	<!-- Note -->
														<col width="80" align="center">	<!-- All Period Terminate -->
														<col width="112" align="center">	<!-- Return Message -->
														<tr height="37">
															<td class="pClothBs">Location</td>
															<td class="pClothBs">Status</td>
															<td class="pClothBs">Start Date</td>
															<td class="pClothBs">End Date</td>
															<td class="pClothBs">Last Read</td>
															<td class="pClothBs">Termination Date</td>
															<td class="pClothBs">Amount</td>
															<td class="pClothBs">Override Amount</td>
															<td class="pClothBs">Suppress<BR>Credit</td>
															<td class="pClothBs">Note</td>
															<td class="pClothBs">All Period<BR>Terminate</td>
															<td class="pClothBs">Return Message</td>
														</tr>
													</table>
												</div>
												<!-- Right Table Detail -->
												<div id="RightTBL#{EZF_ROW_NUMBER}" style="overflow-x:scroll; width:741; overflow-y:hidden;" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL#{EZF_ROW_NUMBER}' ) ); synchroScrollLeft( this.id, new Array( 'topRightTBL#{EZF_ROW_NUMBER}' ) );">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A2">
														<col width="130" align="center">	<!-- Loc -->
														<col width="95" align="center">	<!-- Status -->
														<col width="83" align="center">	<!-- StartDt -->
														<col width="83" align="center">	<!-- EndDt -->
														<col width="83" align="center">	<!-- Last Read -->
														<col width="105" align="center">	<!-- TerminationDt -->
														<col width="80" align="center">	<!-- Amount -->
														<col width="80" align="center">	<!-- OverrideAmount -->
														<col width="80" align="center">	<!-- SprsCR -->
														<col width="35" align="center">	<!-- Note -->
														<col width="80" align="center">	<!-- All Period Terminate -->
														<col width="112" align="center">	<!-- Return Message -->
														<% int rightIdx = 0; %>
														<ezf:row ezfHyo="A">
															<c:set var="dsContrPk" scope="page" value="<%= bMsg.A.no(rightIdx).dsContrPk_AH.getValue()%>" />
															<% rightIdx++; %>
															<c:choose>
															    <c:when test="${dsContrPk == hdrDsContrPk}">
																	<tr height="25" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
																		<td align="center"><ezf:inputText name="xxComnScrColValTxt_AD" ezfName="xxComnScrColValTxt_AD" value="935 W.CHESTNUT STREET, SUITE 301 CHICAGO,IL XX 60642" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"51\""/></td>
																		<td align="center"><ezf:inputText name="dsContrCtrlStsNm_AD" ezfName="dsContrCtrlStsNm_AD" value="Active" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"105\""/></td>
																		<td align="center"><ezf:inputText name="contrEffFromDt_AD" ezfName="contrEffFromDt_AD" value="11/22/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																		<td align="center"><ezf:inputText name="contrEffThruDt_AD" ezfName="contrEffThruDt_AD" value="04/30/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																		<td align="center"><ezf:inputText name="mtrReadDt_AD" ezfName="mtrReadDt_AD" value="04/30/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																		<td align="center"><ezf:inputText name="contrCloDt_AD" ezfName="contrCloDt_AD" value="04/30/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"contrCloDt_AD#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\""/>
																			<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('contrCloDt_AD#{EZF_ROW_NUMBER}', 4);"></td>
																		</td>
																		<td align="center"><ezf:inputText name="trmnTotAmt_AD" ezfName="trmnTotAmt_AD" value="1,234.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"trmnTotAmt_AD#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"19\" style=\"text-align: right;\""/>
																		<td align="center"><ezf:inputText name="trmnOvrdTotAmt_AD" ezfName="trmnOvrdTotAmt_AD" value="4,321.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"trmnOvrdTotAmt_AD#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"19\" style=\"text-align: right;\""/>
																		<td align="center"><ezf:inputCheckBox name="supprCrFlg_AD" ezfName="supprCrFlg_AD" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"supprCrFlg_AD#{EZF_ROW_NUMBER}\""/>
																		<td>
																			<ezf:anchor name="xxExstFlg_AD" ezfName="xxExstFlg_AD" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ClickNote" otherAttr=" ezfanchortext=\"\"">
																			<ezf:label name="xxExstFlg_AD" ezfName="xxExstFlg_AD" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxExstFlg_AD#{EZF_ROW_NUMBER}\""/>
																			</ezf:anchor>
																		</td>
																		<td align="center"><ezf:inputCheckBox name="contrTrmnFlg_AD" ezfName="contrTrmnFlg_AD" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"contrTrmnFlg_AD#{EZF_ROW_NUMBER}\""/>
																		<td align="center"><ezf:inputText name="xxGenlFldAreaTxt_AD" ezfName="xxGenlFldAreaTxt_AD" value="123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/></td>
                                                                        <td class="pAuditInfo">
                                                                            <ezf:inputHidden name="xxRecHistCratTs_AD" ezfName="xxRecHistCratTs_AD" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_AD\""/>
                                                                            <ezf:inputHidden name="xxRecHistCratByNm_AD" ezfName="xxRecHistCratByNm_AD" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_AD\""/>
                                                                            <ezf:inputHidden name="xxRecHistUpdTs_AD" ezfName="xxRecHistUpdTs_AD" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_AD\""/>
                                                                            <ezf:inputHidden name="xxRecHistUpdByNm_AD" ezfName="xxRecHistUpdByNm_AD" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_AD\""/>
                                                                            <ezf:inputHidden name="xxRecHistTblNm_AD" ezfName="xxRecHistTblNm_AD" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_AD\""/>
                                                                        </td>
																	</tr>
																</c:when>
															</c:choose>
														</ezf:row>
														<ezf:skip>
														</ezf:skip>
													</table>
												</div>
											</td>
										</tr>
									</table>
									<br>
									<% hdrIdx++; %>
									</ezf:row>
								</tr>
							</table>
						</div>
						
					</td>
					</tr>
				</table>
			</div>
		</td>
		</tr>
	</table>
	</center>

<%-- **** Task specific area ends here **** --%>
