<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181015110345 --%>
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
			<input type="hidden" name="pageID" value="NYEL8850Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Process Status">
			
			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<!-- ######################################## HEADER ######################################## -->
					<%@ page import="business.servlet.NYEL8850.NYEL8850BMsg" %>
					<%@ page import="business.servlet.NYEL8850.NYEL8850_ABMsg" %>
					<%@ page import="business.servlet.NYEL8850.NYEL8850_HBMsg" %>
					<% NYEL8850BMsg bMsg = (NYEL8850BMsg)databean.getEZDBMsg(); %>
					<tr>
						<td>
							<div class="pTab_BODY">
								<table border="0" cellpadding="1" cellspacing="0">
									<tr>
										<td>
											<table cellspacing="0">
												<tr>
													<td>Process</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table cellpadding="0" cellspacing="0">
												<tr>
													<td>
														<div style="overflow-y:hidden; height:17; overflow-x:hidden;">
															<table border="1" cellpadding="0" cellspacing="0" id="HHEAD" width="880" style="table-layout:fixed;">
																<col width="50"> <!--  -->
																<col width="165"> <!-- Document ID -->
																<col width="250"> <!-- Process Name -->
																<col width="120"> <!-- Status -->
																<col width="195"> <!-- Completed -->
																<col width="100"> <!-- Display -->
																<tr>
																	<td class="pClothBs" style="text-align:center;">&nbsp;</td>
																	<td class="pClothBs" style="text-align:center;">Source#</td>
																	<td class="pClothBs" style="text-align:center;">Process Name</td>
																	<td class="pClothBs" style="text-align:center;">Process Status</td>
																	<td class="pClothBs" style="text-align:center;">Closed Date</td>
																	<td class="pClothBs" style="text-align:center;">&nbsp;</td>
																</tr>
															</table>
														</div>
														<div id="tableH" style="width:897; height:68; display:block; overflow-y:scroll; margin:0px; padding:0px;">
															<table border="1" cellpadding="0" cellspacing="0" id="H" width="880" style="table-layout:fixed;">
																<col align="center" width="50"> <!--  -->
																<col align="left" width="165"> <!-- Document ID -->
																<col align="left" width="250"> <!-- Process Name -->
																<col align="center"  width="120"> <!-- Status -->
																<col align="center"  width="195"> <!-- Completed -->
																<col align="center"  width="100"> <!-- Display -->
																
																<tbody>
																	<% int j = 0; %>
																	<ezf:row ezfHyo="H">
																	<%NYEL8850_HBMsg hbMsg = bMsg.H.no(j++);%>
																	<tr>
																		<td>
																			<%if ("1".equals(hbMsg.xxSelFlg_H.getValue())) {%>
																				<img width="15px" height="15px" src="./img/arrow-l.png" ezfout>
																			<%} else {%>
																				&nbsp;
																			<%}%>
																		</td>
																		<td>
																			<ezf:inputText name="wfProcDocId_H" ezfName="wfProcDocId_H" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="H" ezfArrayNo="0" otherAttr=" style=\"border:none;background:none;\" size=\"20\" maxlength=\"40\""/>
																		</td>
																		<td>
																			<ezf:inputText name="wfProcNm_H" ezfName="wfProcNm_H" value="NWWP0001" ezfHyo="H" ezfArrayNo="0" otherAttr=" style=\"border:none;background:none;\" size=\"30\" maxlength=\"255\""/>
																		</td>
																		<td>
																			<ezf:label name="xxWfProcStsNm_H" ezfName="xxWfProcStsNm_H" ezfHyo="H" ezfArrayNo="0" />
																		</td>
																		<td>
																			<ezf:label name="xxDtTm_PE" ezfName="xxDtTm_PE" ezfHyo="H" ezfArrayNo="0" />
																		</td>
																		
																		
																		<td>
																			<ezf:inputButton name="Display" value="Display" ezfHyo="H" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" id=\"Display#{EZF_ROW_NUMBER}\""/>
																		</td>
																	</tr>
																	</ezf:row>
																</tbody>
															</table>
														</div>
													</td>
													<td align="center" valign="bottom">
													&nbsp;
														<ezf:inputButton name="Refresh" value="Refresh" htmlClass="pBtn6" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td style="padding-top:0px;">
											<hr />
										</td>
									</tr>
									<!-- ######################################## DESCRIPTION ######################################## -->
									<tr>
										<td>
											<table cellspacing="0">
												<tr>
													<td >Description</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td valign="Top" width="450" >
											<ezf:textArea name="wfDescTxt_PR" ezfName="wfDescTxt_PR" otherAttr=" cols=\"125\" rows=\"2\" style=\"overflow: auto;background-color:#D3D3D3;\""/>
										</td>
									</tr>
									<tr>
										<td>
											<br />
										</td>
									</tr>
									<!-- ######################################## STATUS ######################################## -->
									<tr>
										<td>
											<table cellspacing="0">
												<tr>
													<td>Status</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td valign="Top">
											<div style="overflow-y:hidden; height:17; overflow-x:hidden;">
												<table border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="770" style="table-layout:fixed; height:17;">
													<col width="45"> <!-- Status -->
													<col width="100"> <!-- ID -->
													<col width="200"> <!-- TaskName -->
													<col width="425"> <!-- To -->
													<tr>
														<td class="pClothBs" style="text-align:center;">Status</td>
														<td class="pClothBs" style="text-align:center;">ID</td>
														<td class="pClothBs" style="text-align:center;">TaskName</td>
														<td class="pClothBs" style="text-align:center;">Current Approver</td>
													</tr>
												</table>
											</div>
											<div id="tableA" style="width:787; height:122; display:block; overflow-y:scroll; margin:0px; padding:0px;">
												<table border="1" cellpadding="0" cellspacing="0" id="A" width="770" style="table-layout:fixed;">
													<col align="center" width="45"> <!-- Status -->
													<col align="left"   width="100"> <!-- ID -->
													<col align="left"   width="200"> <!-- TaskName -->
													<col align="center"   width="425"> <!-- To -->
													<tbody>
														<% int i = 0; %>
														<ezf:row ezfHyo="A">
														<%NYEL8850_ABMsg abMsg = bMsg.A.no(i++);%>
															<tr height="30">
																<td>
																	<%if ("04".equals(abMsg.wfWrkItemStsCd_A.getValue())) {%>
																		<img width="15px" height="15px" src="./img/wfcomp/S21WfStatusCompleted.png" ezfout>
																	<%} else if ("03".equals(abMsg.wfWrkItemStsCd_A.getValue())) {%>
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
																	<ezf:anchor name="wfWrkItemNm_AL" ezfName="wfWrkItemNm_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_TaskNm" otherAttr=" id=\"wfWrkItemNm_AL#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
																		<ezf:label name="wfWrkItemNm_A" ezfName="wfWrkItemNm_A" ezfHyo="A" ezfArrayNo="0" />
																	</ezf:anchor>
																</td>
																<td>
																	<ezf:textArea name="xxWfAsgToNm_A" ezfName="xxWfAsgToNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" rows=\"2\" cols=\"57\" id=\"xxWfAsgToNm_A#{EZF_ROW_NUMBER}\""/>
																</td>
															</tr>
														</ezf:row>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
									<tr>
										<td style="padding-top:0px;">
											<br />
										</td>
									</tr>
									<!-- ######################################## HISTORY ######################################## -->
									<tr>
										<td>
											<table cellspacing="0">
												<tr>
													<td>History</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td valign="Top" width="">
											<div id="rightTbl_Top" style="overflow-y:hidden; height:17; overflow-x:hidden; width:987" onScroll="synchroScrollLeft( this.id, new Array( 'rightTbl' ) );">
												<table border="1" cellpadding="0" cellspacing="0" id="BHEAD" width="2135" style="table-layout:fixed;">
													<col align="center" width="045"><!-- Num -->
													<col align="center" width="100"><!-- ID -->
													<col align="center" width="200"><!-- taskName -->
													<col align="center" width="100"><!-- Last Action -->
													<col align="center" width="210"><!-- Last Action By-->
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
														<td class="pClothBs">ID</td>
														<td class="pClothBs">Task Name</td>
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
											<div id="rightTbl" style="width:1004; height:169; display:block; overflow-x:scroll;overflow-y:scroll; margin:0px; padding:0px; word-break:break-all;" onScroll="synchroScrollLeft( this.id, new Array( 'rightTbl_Top' ) );">
												<table border="1" cellpadding="0" cellspacing="0" id="B" width="2135" style="table-layout:fixed;">
													<col align="right"  width="045"> <!-- Num -->
													<col align="left"   width="100"> <!-- ID -->
													<col align="left" width="200"><!-- taskname -->
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
															<tr id="id_row{EZF_ROW_NUMBER}" height="30">
																<td class="">
																	<ezf:inputText name="xxRowNum_B" ezfName="xxRowNum_B" value="999" ezfHyo="B" ezfArrayNo="0" htmlClass="lbTxt" otherAttr=" id=\"xxRowNum_B#{EZF_ROW_NUMBER}\" size=\"3\" style=\"border:none;background-color:transparent;padding:0px;text-align:right;\""/>
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
																	<ezf:textArea name="wfCmntTxt_B" ezfName="wfCmntTxt_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" rows=\"2\" cols=\"50\""/>
																</td>
																<td class="">
																	<ezf:textArea name="xxScrItem130Txt_B1" ezfName="xxScrItem130Txt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" cols=\"25\" style=\"border:none;background:none;overflow:auto;height:2.5em\""/>
																</td>
																<td class="">
																	<ezf:textArea name="xxScrItem130Txt_B2" ezfName="xxScrItem130Txt_B2" ezfHyo="B" ezfArrayNo="0" otherAttr=" cols=\"25\" style=\"border:none;background:none;overflow:auto;height:2.5em\""/>
																</td>
																<td class="">
																	<ezf:textArea name="xxScrItem130Txt_B3" ezfName="xxScrItem130Txt_B3" ezfHyo="B" ezfArrayNo="0" otherAttr=" cols=\"25\" style=\"border:none;background:none;overflow:auto;height:2.5em\""/>
																</td>
																<td class="">
																	<ezf:textArea name="xxScrItem130Txt_B4" ezfName="xxScrItem130Txt_B4" ezfHyo="B" ezfArrayNo="0" otherAttr=" cols=\"25\" style=\"border:none;background:none;overflow:auto;height:2.5em\""/>
																</td>
																<td class="">
																	<ezf:textArea name="xxScrItem130Txt_B5" ezfName="xxScrItem130Txt_B5" ezfHyo="B" ezfArrayNo="0" otherAttr=" cols=\"25\" style=\"border:none;background:none;overflow:auto;height:2.5em\""/>
																</td>
																<td class="">
																	<ezf:textArea name="xxWfAsgFromNm_B" ezfName="xxWfAsgFromNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" rows=\"2\" cols=\"50\""/>
																</td>
																<td class="">
																	<ezf:textArea name="xxWfAsgToNm_B" ezfName="xxWfAsgToNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" rows=\"2\" cols=\"50\""/>
																</td>
																<td class="">
																	<ezf:label name="xxDtTm_BT" ezfName="xxDtTm_BT" ezfHyo="B" ezfArrayNo="0" />
																</td>
															</tr>
														</ezf:row>
													</tbody>
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
<style TYPE="text/css">
<!--
	INPUT.lbTxt{border-style:none; background-color:transparent;}
-->
</style>

<%-- **** Task specific area ends here **** --%>
