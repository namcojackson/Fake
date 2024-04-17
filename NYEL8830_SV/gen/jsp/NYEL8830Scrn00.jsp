<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180925090615 --%>
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
			<input type="hidden" name="pageID" value="NYEL8830Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Process Status">

			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<!-- ######################################## HEADER ######################################## -->
					<tr>
						<td>
							<div class="pTab_BODY">
								<table border="0" cellpadding="1" cellspacing="0">
									<tr>
										<td>
											<table border="0" cellpadding="2" cellspacing="0">
												<col width="80"> <!-- Process Name -->
												<col width="200"> <!-- Process Name -->
												<col width="80"> <!-- Process Status -->
												<col width="200"> <!-- Process Status -->
												<col width="80"> <!-- Process Date -->
												<col width="150"> <!-- Process Date From -->
												<col width="60"> <!-- Process Date From -->
												<tr>
													<td valign="top">
														<tr>
															<td class="stab">Process Name</td>
															<td>
																<ezf:inputText name="wfProcNm" ezfName="wfProcNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3 WWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" id=\"wfProcNm\" size=\"30\" maxlength=\"60\""/>
															</td>
															<td class="stab">Process Status</td>
															<td>
																<ezf:inputText name="xxWfProcStsNm" ezfName="xxWfProcStsNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" id=\"wfProcStsNm\" size=\"30\" maxlength=\"30\""/>
															</td>
															<td class="stab">Closed Date</td>
															<td>
																<ezf:inputText name="xxDtTm_PE" ezfName="xxDtTm_PE" value="MM/dd/yyyy HH:mm EDT" otherAttr=" id=\"\" size=\"25\" maxlength=\"25\""/>
															</td>
															<td />
														</tr>
														<tr>
															<td class="stab">Description</td>
															<td colspan="5">
																<ezf:textArea name="wfDescTxt_PR" ezfName="wfDescTxt_PR" otherAttr=" cols=\"100\" rows=\"2\" style=\"overflow: auto;background-color:#D3D3D3;\""/>
															</td>
															<td>
																<ezf:inputButton name="Refresh" value="Refresh" htmlClass="pBtn6" />
															</td>
														</tr>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<hr />
										</td>
									</tr>
									<!-- ######################################## STATUS ######################################## -->
									<%@ page import="business.servlet.NYEL8830.NYEL8830BMsg" %>
									<%@ page import="business.servlet.NYEL8830.NYEL8830_ABMsg" %>
									<%@ include file="../common/common.jsp" %>
									<% NYEL8830BMsg bMsg = (NYEL8830BMsg)databean.getEZDBMsg(); %>
									<tr>
										<td>Status</td>
									</tr>
									<tr>
										<td valign="Top" width="1082">
											<div style="overflow-y:hidden; height:17; overflow-x:hidden;">
												<table border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="490" style="table-layout:fixed;">
													<col width="90"> <!-- Status -->
													<col width="140"> <!-- ID -->
													<col width="170"> <!-- TaskName -->
													<col width="090"> <!-- Detail -->
													<tr >
														<td class="pClothBs" style="text-align:center;">Status</td>
														<td class="pClothBs" style="text-align:center;">ID</td>
														<td class="pClothBs" style="text-align:center;">TaskName</td>
														<td class="pClothBs" style="text-align:center;"></td>	
													</tr>
												</table>
											</div>
											<div id="tableA" style="width:507; height:123; display:block; overflow-y:scroll; margin:0px; padding:0px;">
												<table border="1" cellpadding="0" cellspacing="0" id="A" width="490" style="table-layout:fixed;">
													<col align="center" width="90"> <!-- Status -->
													<col align="left"   width="140"> <!-- ID -->
													<col align="left"   width="170"> <!-- TaskName -->
													<col align="center"   width="090"> <!-- Detail -->
													<tbody>
														<% int i = 0; %>
														<ezf:row ezfHyo="A">
														<%NYEL8830_ABMsg abMsg = bMsg.A.no(i++);%>
														<tr height="30">
															<td>
																<%if ("03".equals(abMsg.wfWrkItemStsCd_A.getValue())) {%>
																	<img width="15px" height="15px" src="./img/wfcomp/S21WfStatusCompleted.png" ezfout>
																<%} else if ("04".equals(abMsg.wfWrkItemStsCd_A.getValue())) {%>
																	<img width="15px" height="15px" src="./img/wfcomp/S21WfStatusCompleted.png" ezfout>
																<%} else if ("02".equals(abMsg.wfWrkItemStsCd_A.getValue())) {%>
																	<img width="15px" height="15px" src="./img/wfcomp/S21WfStatusProcessing.png" ezfout>
																<%} else {%>
																	&nbsp;
																<%}%>
															</td>
															<td>
																<ezf:label name="wfWrkItemId_A" ezfName="wfWrkItemId_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"wfWrkItemId_A#{EZF_ROW_NUMBER}\""/>
															</td>
															<td>
                                                                    <%if (abMsg.wfBizScrId_A.getValue() == null) {%>
                                                                        <label>
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </label>
                                                                    <%} else if ("NWAL1500".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NWAL1500',{EZF_ROW_NUMBER}); return false;"  id="NWAL1500_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NPAL0110".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NPAL0110',{EZF_ROW_NUMBER}); return false;"  id="NPAL0110_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NPAL1080".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NPAL1080',{EZF_ROW_NUMBER}); return false;"  id="NPAL1080_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NPAL1280".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NPAL1280',{EZF_ROW_NUMBER}); return false;"  id="NPAL1280_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NPBL0020".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NPBL0020',{EZF_ROW_NUMBER}); return false;"  id="NPBL0020_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NFCL2610".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NFCL2610',{EZF_ROW_NUMBER}); return false;"  id="NFCL2610_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NFBL1110".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NFBL1110',{EZF_ROW_NUMBER}); return false;"  id="NFBL1110_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NFBL2100".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NFBL2100',{EZF_ROW_NUMBER}); return false;"  id="NFBL2100_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NFDL0090".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NFDL0090',{EZF_ROW_NUMBER}); return false;"  id="NFDL0090_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NYEL8899".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NYEL8899',{EZF_ROW_NUMBER}); return false;"  id="NYEL8899_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NPAL1500".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NPAL1500',{EZF_ROW_NUMBER}); return false;"  id="NPAL1500_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NSAL1090".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NSAL1090',{EZF_ROW_NUMBER}); return false;"  id="NSAL1090_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NLCL0290".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NLCL0290',{EZF_ROW_NUMBER}); return false;"  id="NLCL0290_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NFCL0760".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NFCL0760',{EZF_ROW_NUMBER}); return false;"  id="NFCL0760_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NSAL0300".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NSAL0300',{EZF_ROW_NUMBER}); return false;"  id="NSAL0300_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NSAL0920".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NSAL0920',{EZF_ROW_NUMBER}); return false;"  id="NSAL0920_LK#{EZF_ROW_NUMBER}" ezfanchortext="">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else if ("EXTNE307T020".equals(abMsg.wfBizScrId_A.getValue())) {%>
                                                                        <a href="<%= getCustomAppURL("EXTNE307T020") %>?fsr=<%= abMsg.wfBizAttrbTxt_A1.getValue() %>" target="_blank" onclick="f_open_window_max(this.href,'_blank');return false;">
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </a>
                                                                    <%} else {%>
                                                                        <label>
                                                                            <%= abMsg.wfWrkItemNm_A.getValue() %>
                                                                        </label>
                                                                    <%}%>
															</td>
															<td>
																<%if ("03".equals(abMsg.wfWrkItemStsCd_A.getValue()) || "04".equals(abMsg.wfWrkItemStsCd_A.getValue())) {%>
																	&nbsp;
																<%} else {%>
																	<ezf:inputButton name="MoveWin_Detail" value="Action" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" id=\"Detail#{EZF_ROW_NUMBER}\""/>
																<%}%>
															</td>
														</tr>
														</ezf:row>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
									<tr>
										<td style="padding-top:10px;">
											<hr />
										</td>
									</tr>
									<!-- ######################################## HISTORY ######################################## -->
									<tr>
										<td>History</td>
									</tr>
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="Top" width="">
														<div id="rightTbl_Top" style="overflow-y:hidden; height:17; overflow-x:hidden; width:1080" onScroll="synchroScrollLeft( this.id, new Array( 'rightTbl' ) );">
															<table border="1" cellpadding="0" cellspacing="0" id="BHEAD" width="3095"  style="table-layout:fixed;" >
																<col align="center" width="045"><!-- Num -->
																<col align="center" width="100"><!-- button -->
																<col align="center" width="100"><!-- ID -->
																<col align="center" width="160"><!-- taskName -->
																<col align="center" width="100"><!-- Action -->
																<col align="center" width="210"><!-- Action -->
																<col align="center" width="400"><!-- Comment -->
																<col align="center" width="200"><!-- attribute1 -->
																<col align="center" width="200"><!-- attribute2 -->
																<col align="center" width="200"><!-- attribute3 -->
																<col align="center" width="200"><!-- attribute4 -->
																<col align="center" width="200"><!-- attribute5 -->
																<col align="center" width="400"><!-- from -->
																<col align="center" width="400"><!-- to -->
																<col align="center" width="180"><!-- Time -->
																<tr>
																	<td class="pClothBs">No</td>
																	<td class="pClothBs">&nbsp;</td>
																	<td class="pClothBs">ID</td>
																	<td class="pClothBs">TaskName</td>
																	<td class="pClothBs">Last Action</td>
																	<td class="pClothBs">Last Action By</td>
																	<td class="pClothBs">Comment</td>
																	<td class="pClothBs">Subject1</td>
																	<td class="pClothBs">Subject2</td>
																	<td class="pClothBs">Subject3</td>
																	<td class="pClothBs">Subject4</td>
																	<td class="pClothBs">Subject5</td>
																	<td class="pClothBs">From</td>
																	<td class="pClothBs">To</td>
																	<td class="pClothBs">Action Date</td>
																</tr>
															</table>
														</div>

														<div id="rightTbl" style="width:1097; height:244; display:block; overflow-x:scroll;overflow-y:scroll; margin:0px; padding:0px; word-break:break-all;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTbl_Top' ) );">
															<table border="1" cellpadding="0" cellspacing="0" id="B" width="3095"  style="table-layout:fixed; word-break:break-all;" >
																<col align="right"  width="045"> <!-- Num -->
																<col align="center" width="100"><!-- button -->
																<col align="left"   width="100"> <!-- ID -->
																<col align="left" width="160"><!-- taskname -->
																<col align="center" width="100"><!-- Action -->
																<col align="left" width="210"><!-- Action -->
																<col align="center" width="400"><!-- Comment -->
																<col align="center" width="200"><!-- attribute1 -->
																<col align="center" width="200"><!-- attribute2 -->
																<col align="center" width="200"><!-- attribute3 -->
																<col align="center" width="200"><!-- attribute4 -->
																<col align="center" width="200"><!-- attribute5 -->
																<col align="center" width="400"><!-- from -->
																<col align="center" width="400"><!-- to -->
																<col align="center" width="180"><!-- Time -->
																<tbody>

																<ezf:row ezfHyo="B">
																<tr id="id_row{EZF_ROW_NUMBER}" height="45">
																	<td class="">
																		<ezf:inputText name="xxRowNum_B" ezfName="xxRowNum_B" value="999" ezfHyo="B" ezfArrayNo="0" htmlClass="lbTxt" otherAttr=" id=\"xxRowNum_B#{EZF_ROW_NUMBER}\" size=\"3\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/>
																	</td>
																	<td class="">
																		<ezf:inputButton name="MoveWin_History_Detail" value="Detail" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" id=\"Detail_History#{EZF_ROW_NUMBER}\""/>
																	</td>
																	<td class="">
																		<ezf:inputText name="wfWrkItemId_B" ezfName="wfWrkItemId_B" value="WWWWWWWWW1" ezfHyo="B" ezfArrayNo="0" htmlClass="lbTxt" otherAttr=" id=\"taskId_B#{EZF_ROW_NUMBER}\" size=\"10\""/>
																	</td>
																	<td class="">
																		<ezf:label name="wfWrkItemNm_B" ezfName="wfWrkItemNm_B" ezfHyo="B" ezfArrayNo="0" />
																	</td>
																	<td class="">
																		<ezf:label name="actWfCondNm_B" ezfName="actWfCondNm_B" ezfHyo="B" ezfArrayNo="0" />
																	</td>
																	<td class="">
																		<ezf:label name="xxWfActOpNm_B" ezfName="xxWfActOpNm_B" ezfHyo="B" ezfArrayNo="0" />
																	</td>
																	<td class="">
																		<ezf:textArea name="wfCmntTxt_B" ezfName="wfCmntTxt_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" rows=\"3\" cols=\"50\""/>
																	</td>
																	<td class="">
																		<ezf:textArea name="xxScrItem130Txt_B1" ezfName="xxScrItem130Txt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" rows=\"3\" cols=\"25\" style=\"border:none;background:none;overflow:auto\""/>
																	</td>
																	<td class="">
																		<ezf:textArea name="xxScrItem130Txt_B2" ezfName="xxScrItem130Txt_B2" ezfHyo="B" ezfArrayNo="0" otherAttr=" rows=\"3\" cols=\"25\" style=\"border:none;background:none;overflow:auto\""/>
																	</td>
																	<td class="">
																		<ezf:textArea name="xxScrItem130Txt_B3" ezfName="xxScrItem130Txt_B3" ezfHyo="B" ezfArrayNo="0" otherAttr=" rows=\"3\" cols=\"25\" style=\"border:none;background:none;overflow:auto\""/>
																	</td>
																	<td class="">
																		<ezf:textArea name="xxScrItem130Txt_B4" ezfName="xxScrItem130Txt_B4" ezfHyo="B" ezfArrayNo="0" otherAttr=" rows=\"3\" cols=\"25\" style=\"border:none;background:none;overflow:auto\""/>
																	</td>
																	<td class="">
																		<ezf:textArea name="xxScrItem130Txt_B5" ezfName="xxScrItem130Txt_B5" ezfHyo="B" ezfArrayNo="0" otherAttr=" rows=\"3\" cols=\"25\" style=\"border:none;background:none;overflow:auto\""/>
																	</td>
																	<td class="">
																		<ezf:textArea name="xxWfAsgFromNm_B" ezfName="xxWfAsgFromNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" rows=\"3\" cols=\"50\""/>
																	</td>
																	<td class="">
																		<ezf:textArea name="xxWfAsgToNm_B" ezfName="xxWfAsgToNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" rows=\"3\" cols=\"50\""/>
																	</td>
																	<td class="">
																		<ezf:label name="xxDtTm_BT" ezfName="xxDtTm_BT" ezfHyo="B" ezfArrayNo="0" />
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
						</td>
					</tr>
				</table>
			</center>
<style TYPE="text/css">
<!--
	INPUT.lbTxt{border-style:none; background-color:transparent;}
-->
</style>
<script>
function f_open_window_max( aURL, aWinName )
{
   var wOpen;
   var sOptions;

   sOptions = 'status=yes,menubar=no,scrollbars=yes,resizable=yes,toolbar=no';
   sOptions = sOptions + ',width=' + (screen.availWidth - 17).toString();
   sOptions = sOptions + ',height=' + (screen.availHeight - 65).toString();
   sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

   wOpen = window.open( '', aWinName, sOptions );
   wOpen.location = aURL;
   wOpen.focus();
   wOpen.moveTo( 0, 0 );
   //wOpen.resizeTo( screen.availWidth, screen.availHeight  );
   return wOpen;
}
</script>

<%-- **** Task specific area ends here **** --%>
