<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180704135525 --%>
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
			<input type="hidden" name="pageID" value="NYEL8820Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Detail">

			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<!-- ######################################## HEADER ######################################## -->
					<%@ page import="business.servlet.NYEL8820.NYEL8820BMsg" %>
					<%@ page import="business.servlet.NYEL8820.NYEL8820_ABMsg" %>
					<%@ include file="../common/common.jsp" %>
					<% NYEL8820BMsg bMsg = (NYEL8820BMsg)databean.getEZDBMsg(); %>
					
					<tr>
						<td>
							<div class="pTab_BODY">
								<table border="0" cellpadding="1" cellspacing="0">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="100"> <!-- Process Name -->
												<col width="200"> <!-- Process Name -->
												<col width="100"> <!-- Process Status -->
												<col width="200"> <!-- Process Status -->
												<col width="100"> <!-- Action Date -->
												<col width="250"> <!-- Action Date -->
												<col width="100"> <!-- button -->
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
														</tr>
														
														<tr>
															<td class="stab">Task Name</td>
															<td>
                                                                    <%if (bMsg.wfBizScrId.getValue() == null) {%>
                                                                        <label>
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </label>
                                                                    <%} else if ("NWAL1500".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NWAL1500',-1); return false;"  id="NWAL1500_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NPAL0110".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NPAL0110',-1); return false;"  id="NPAL0110_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NPAL1080".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NPAL1080',-1); return false;"  id="NPAL1080_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NPAL1280".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NPAL1280',-1); return false;"  id="NPAL1280_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NPBL0020".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NPBL0020',-1); return false;"  id="NPBL0020_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NFCL2610".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NFCL2610',-1); return false;"  id="NFCL2610_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NFBL1110".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NFBL1110',-1); return false;"  id="NFBL1110_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NFBL2100".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NFBL2100',-1); return false;"  id="NFBL2100_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NFDL0090".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NFDL0090',-1); return false;"  id="NFDL0090_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NYEL8899".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NYEL8899',-1); return false;"  id="NYEL8899_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NPAL1500".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NPAL1500',-1); return false;"  id="NPAL1500_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NSAL1090".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NSAL1090',-1); return false;"  id="NSAL1090_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NLCL0290".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NLCL0290',-1); return false;"  id="NLCL0290_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NFCL0760".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NFCL0760',-1); return false;"  id="NFCL0760_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NSAL0300".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NSAL0300',-1); return false;"  id="NSAL0300_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("NSAL0920".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="#" onclick="sendServer('MoveWin_NSAL0920',-1); return false;"  id="NSAL0920_LK" ezfanchortext="">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else if ("EXTNE307T020".equals(bMsg.wfBizScrId.getValue())) {%>
                                                                        <a href="<%= getCustomAppURL("EXTNE307T020") %>?fsr=<%= bMsg.wfBizAttrbTxt_01.getValue() %>" target="_blank" onclick="f_open_window_max(this.href,'_blank');return false;">
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </a>
                                                                    <%} else {%>
                                                                        <label>
                                                                            <%= bMsg.wfWrkItemNm.getValue() %>
                                                                        </label>
                                                                    <%}%>
															</td>
															<td class="stab">Task Status</td>
															<td>
																<ezf:inputText name="xxWfWrkItemStsNm" ezfName="xxWfWrkItemStsNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" id=\"taskStsNm\" size=\"30\" maxlength=\"30\""/>
															</td>
															<td class="stab">Closed Date</td>
															<td>
																<ezf:inputText name="xxDtTm_T" ezfName="xxDtTm_T" value="MM/dd/yyyy HH:mm EDT" otherAttr=" id=\"\" size=\"25\" maxlength=\"25\""/>
															</td>
														</tr>
														
														
														<tr>
															<td class="stab"  style="vertical-align:top">Task Description</td>
															<td colspan="6">
																<ezf:textArea name="wfCmntTxt_T" ezfName="wfCmntTxt_T" otherAttr=" rows=\"2\" cols=\"100\" style=\"overflow: auto;background-color:#D3D3D3;\""/>
															</td>
															<td>
															</td>
														</tr>
														<tr>
															<td class="stab"  style="vertical-align:top">Subject</td>
															<td colspan="6">
																<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;">
																	<col align="center" width="150"> <!-- Attribute1 -->
																	<col align="center" width="150"> <!-- Attribute2 -->
																	<col align="center" width="150"> <!-- Attribute3 -->
																	<col align="center" width="150"> <!-- Attribute4 -->
																	<col align="center" width="150"> <!-- Attribute5 -->
																	<tr>
																		<!-- Attr1Lbl -->
																		<td class="pClothBs">
																			<ezf:inputText name="wfBizAttrbLbTxt_01" ezfName="wfBizAttrbLbTxt_01" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" style=\"border:none;background:none;color: #ffffff;font-weight: bold;font-family:'Arial',sans-serif;font-size:8pt;\" size=\"25\" maxlength=\"255\""/>
																		</td>
																		<!-- Attr2Lbl -->
																		<td class="pClothBs">
																			<ezf:inputText name="wfBizAttrbLbTxt_02" ezfName="wfBizAttrbLbTxt_02" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" style=\"border:none;background:none;color: #ffffff;font-weight: bold;font-family:'Arial',sans-serif;font-size:8pt;\" size=\"25\" maxlength=\"255\""/>
																		</td>
																		<!-- Attr3Lbl -->
																		<td class="pClothBs">
																			<ezf:inputText name="wfBizAttrbLbTxt_03" ezfName="wfBizAttrbLbTxt_03" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" style=\"border:none;background:none;color: #ffffff;font-weight: bold;font-family:'Arial',sans-serif;font-size:8pt;\" size=\"25\" maxlength=\"255\""/>
																		</td>
																		<!-- Attr4Lbl -->
																		<td class="pClothBs">
																			<ezf:inputText name="wfBizAttrbLbTxt_04" ezfName="wfBizAttrbLbTxt_04" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" style=\"border:none;background:none;color: #ffffff;font-weight: bold;font-family:'Arial',sans-serif;font-size:8pt;\" size=\"25\" maxlength=\"255\""/>
																		</td>
																		<!-- Attr5Lbl -->
																		<td class="pClothBs">
																			<ezf:inputText name="wfBizAttrbLbTxt_05" ezfName="wfBizAttrbLbTxt_05" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" style=\"border:none;background:none;color: #ffffff;font-weight: bold;font-family:'Arial',sans-serif;font-size:8pt;\" size=\"25\" maxlength=\"255\""/>
																		</td>
																	</tr>
																	<tr>
																		<!-- Attr1Val -->
																		<td>
																			<ezf:inputText name="wfBizAttrbTxt_01" ezfName="wfBizAttrbTxt_01" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" style=\"border:none;background:none;\" size=\"20\" maxlength=\"255\""/>
																		</td>
																		<!-- Attr2Val -->
																		<td>
																			<ezf:inputText name="wfBizAttrbTxt_02" ezfName="wfBizAttrbTxt_02" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" style=\"border:none;background:none;\" size=\"20\" maxlength=\"255\""/>
																		</td>
																		<!-- Attr3Val -->
																		<td>
																			<ezf:inputText name="wfBizAttrbTxt_03" ezfName="wfBizAttrbTxt_03" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" style=\"border:none;background:none;\" size=\"20\" maxlength=\"255\""/>
																		</td>
																		<!-- Attr4Val -->
																		<td>
																			<ezf:inputText name="wfBizAttrbTxt_04" ezfName="wfBizAttrbTxt_04" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" style=\"border:none;background:none;\" size=\"20\" maxlength=\"255\""/>
																		</td>
																		<!-- Attr5Val -->
																		<td>
																			<ezf:inputText name="wfBizAttrbTxt_05" ezfName="wfBizAttrbTxt_05" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" otherAttr=" style=\"border:none;background:none;\" size=\"20\" maxlength=\"255\""/>
																		</td>
																		
																	</tr>
																</table>
															</td>
														</tr>
													</td>
												</tr>
											</table>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="100">
												<col width="450">
												<col width="100">
												<col width="450">
												<tr>
													<td valign="top">
														<tr>
															<td class="stab" style="vertical-align:top">From</td>
															<td style="vertical-align:top" colspan="6">
																<ezf:inputText name="xxWfAsgFromNm" ezfName="xxWfAsgFromNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3 WWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" id=\"asgFromNm\" size=\"60\" maxlength=\"255\""/>
															</td>
														</tr>
														<tr>
															<td class="stab" style="vertical-align:top">To</td>
															<td style="vertical-align:top">
																<ezf:textArea name="xxWfAsgToNm" ezfName="xxWfAsgToNm" otherAttr=" rows=\"4\" cols=\"58\" id=\"asgTo_A1\" style=\"overflow:auto;background-color:#D3D3D3;\""/>
															</td>
															<td class="stab" style="vertical-align:top">Next</td>
															<td style="vertical-align:top">
																<ezf:textArea name="xxWfAsgToNm_NX" ezfName="xxWfAsgToNm_NX" otherAttr=" rows=\"4\" cols=\"58\" id=\"asgToNx_A1\" style=\"overflow:auto;background-color:#D3D3D3;\""/>
															</td>
														</tr>
														<tr>
															<td class="stab">Last Action By</td>
															<td>
																<ezf:inputText name="xxWfActOpNm" ezfName="xxWfActOpNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7 WWWWWWWW8WWWWWWWWW9" otherAttr=" id=\"\" size=\"60\" maxlength=\"255\""/>
															</td>
															<td class="stab">
																Last Action
															</td>
															<td  colspan="2">
																<ezf:inputText name="actWfCondNm" ezfName="actWfCondNm" value="WWWWWWWWWXWWWWWWWWWXWWWWWWWWWX" otherAttr=" id=\"\" size=\"30\" maxlength=\"30\""/>
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
									<!-- ######################################## BizData ######################################## -->
									<tr>
										<td>
											<div id="divSubj" style="width:1130px; height:20px; display:block;overflow:auto; margin:0px; padding:0px;">

											</div>
											<div id="divDtl" style="width:1130px; height:280px; display:block;overflow:auto; margin:0px; padding:0px;">

											</div>
										</td>
									</tr>

									<tr>
										<td style="padding-top:10px;">
											<hr />
										</td>
									</tr>
									<!-- ######################################## Comment ######################################## -->
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="3">
												<col width="100">
												<col width="610">
												<col width="420">
												<tr>
													<td class="stab" style="vertical-align:top">Comment</td>
													<td>
														<ezf:textArea name="wfCmntTxt" ezfName="wfCmntTxt" otherAttr=" rows=\"2\" cols=\"100\""/>
													</td>
													<td style="vertical-align:top; text-align:right">
														<ezf:inputButton name="Back" value="Back" htmlClass="pBtn6" otherAttr=" id=\"Back\""/>
														<ezf:inputButton name="Continue" value="Continue" htmlClass="pBtn6" otherAttr=" id=\"Continue\""/>
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
			
			<% 
				pageContext.setAttribute("wfBizSubjTxt", bMsg.xxWfBizSubjTxt.getValue());
				pageContext.setAttribute("wfBizDtlTxt", bMsg.xxWfBizDtlTxt.getValue());
			%>
			
			<!-- Set Business Data Subject  -->
			<!--
			**********************************************************************
			Please rewrite the "ezf:inputHidden" to "input type="hidden""
			after "Generate JSP File"
			**********************************************************************
			-->
			<input type="hidden" id="wfBizSubjTxt" name="wfBizSubjTxt" value="<c:out value='${wfBizSubjTxt}' escapeXml='false' />" >
			
			<!-- Set Business Data Detail  -->
			<!--
			**********************************************************************
			Please rewrite the "ezf:inputHidden" to "input type="hidden""
			after "Generate JSP File"
			**********************************************************************
			-->
			<input type="hidden" id="wfBizDtlTxt" name="wfBizDtlTxt" value="<c:out value='${wfBizDtlTxt}' escapeXml='false' />" >
			
			<script type="text/javascript">
				var subjVal = document.getElementById('wfBizSubjTxt');
				var subjDiv = document.getElementById('divSubj');
				if ((subjVal != null) && (subjDiv != null)) {
					subjDiv.innerHTML = subjVal.value;
					subjVal.value = "";
				}
				
				var dtlVal =  document.getElementById('wfBizDtlTxt');
				var dtlDiv =  document.getElementById('divDtl');
				
				if ((dtlVal != null) && (dtlDiv != null)) {
					dtlDiv.innerHTML = dtlVal.value;
					dtlVal.value = "";
				}
			</script>
			
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
