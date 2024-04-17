<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230905074642 --%>
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
			<input type="hidden" name="pageID" value="NPAL1160Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="PO/Inventory Approval Maintenance Screen">
			
			<center>
				<table border="0" cellPadding="0" cellSpacing="0" height="95%" width="100%">
					<tr>
						<td>
							<%-- #################### UPPER TAB #################### --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
							<ezf:skip>
								<table border="0" cellpadding="0" cellspacing="0" width="100%" style="background-image: url(./img/tab/uppertabbackground.jpg);">
									<tr>
										<td height="28px" width="1070px" valign="bottom">
											<table class="pTab_UPPER_ON" border="0" cellpadding="0" cellspacing="0" >
												<tr title="Apvl Mnt">
													<td align="center" class="same">Apvl Mnt</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</ezf:skip>

							<div class="pTab_BODY">
								<%-- ########## COMMON HEADER [START] ########## --%>
								<%-- ########## COMMON HEADER [END] ########## --%>

								<%-- #################### LOWER TAB #################### --%>
								<div class="pTab_HEAD">
									<ul>
										<table border="0" cellpadding="0" cellspacing="0" width="900px" style="margin-top:10px" >
											<tr>
												<td width="96%">
													<li id="Team" title="Team" class="pTab_ON">
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TAB_Team" >Team</ezf:anchor>
													</li>
													<li id="Member" title="Member" class="pTab_OFF">
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TAB_Member" >Member</ezf:anchor>
													</li>
													<li id="Transaction" title="Transaction" class="pTab_OFF">
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TAB_Transaction" >Transaction</ezf:anchor>
													</li>
													<li id="Location" title="Location" class="pTab_OFF">
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TAB_Location" >Location</ezf:anchor>
													</li>
													<li id="ApvlLimit" title="ApvlLimit" class="pTab_OFF">
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TAB_ApvlLimit" >Apvl Limit</ezf:anchor>
													</li>
													<li id="TechThrhd" title="TechThrhd" class="pTab_OFF">
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TAB_TechThrhd" >Tech Thrhd</ezf:anchor>
													</li>
													<%-- START 2023/05/16 T.Kuroda [QC#61211, MOD] --%>
													<li id="TechMin" title="TechMin" class="pTab_OFF">
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TAB_TechMin" >Tech Min</ezf:anchor>
													</li>
													<%-- END   2023/05/16 T.Kuroda [QC#61211, MOD] --%>
												</td>
											</tr>
										</table>
									</ul>
								</div>

								<%-- #################### DETAIL AREA #################### --%>
								<div class="pTab_BODY_In">
									<div style="height: 528px" >
										<c:choose>
											<%-- #################### Team TAB [START] #################### --%>
											<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Team'}">
												<div id="TabContent-Team">
													<script type="text/javascript">
														document.getElementById("Team").className = "pTab_ON";
														document.getElementById("Member").className = "pTab_OFF";
														document.getElementById("Transaction").className = "pTab_OFF";
														document.getElementById("Location").className = "pTab_OFF";
														document.getElementById("ApvlLimit").className = "pTab_OFF";
														document.getElementById("TechThrhd").className = "pTab_OFF";
														document.getElementById("TechMin").className = "pTab_OFF";
													</script>
													<%-- ########## SEARCH HEADER ########## --%>
													<table border="0" cellpadding="0" cellspacing="0" style="margin-top:8px; margin-left:5px">
														<col width="74px"  align="left"><%-- Team Name (L) --%>
														<col width="185px" align="left"><%-- Team Name --%>
														<col width="10px"  align="left"><%-- ====== spece ====== --%>
														<col width="70px"  align="left"><%-- Description (L) --%>
														<col width="255px" align="left"><%-- Description --%>
														<col width="10px"  align="left"><%-- ====== spece ====== --%>
														<col width="78px"  align="left"><%-- Hierarchy Type (L) --%>
														<col width="130px" align="left"><%-- Hierarchy Type --%>
														<col width="30px"  align="left"><%-- ====== spece ====== --%>
														<col width="50px"  align="left"><%-- Search Button --%>
														<tr>
															<td class="stab">Team Name(*)</td>
															<td><ezf:inputText name="apvlTeamNm_TT" ezfName="apvlTeamNm_TT" value="Procure -EAST" otherAttr=" size=\"25\""/></td>
															<td></td>
															<td class="stab">Description(*)</td>
															<td><ezf:inputText name="apvlTeamDescTxt_TT" ezfName="apvlTeamDescTxt_TT" value="East team for purchase" otherAttr=" size=\"35\""/></td>
															<td></td>
															<td class="stab">Hierarchy Type</td>
															<td>
																<ezf:select name="apvlHrchTpCd_TS" ezfName="apvlHrchTpCd_TS" ezfBlank="1" ezfCodeName="apvlHrchTpCd_TC" ezfDispName="apvlHrchTpDescTxt_TD" otherAttr=" style=\"width:128px\""/>
															</td>
															<td></td>
															<td><ezf:inputButton name="Search_Team" value="Search" htmlClass="pBtn7" /></td>
														</tr>
													</table>
													<hr>
													<center>
														<%-- ########## UPPER BUTTON AREA ########## --%>
														<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
                        								<table style="margin-left: 3px; width: 100%;" >
                            								<colgroup>
																<col width="330">
																<col width="350">
																<col width="45">
                            								</colgroup>
                            								<tbody>
								                                <tr>
								                                    <td>
								                                        <table cellSpacing="0" cellPadding="1" border="0" style="margin-left: 70px;">
								                                            <colgroup>
								                                                <col width="123">
								                                                <col width="">
								                                            </colgroup>
								                                            <tbody>
								                                                <tr>
								                                                    <td>
								                                                        <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxtA\""/>
								                                                    </td>
								                                                </tr>
								                                            </tbody>
								                                        </table>
								                                    </td>
								                                    <td>
																		<ezf:skip>
																		<table border="0" cellpadding="0" width="">
																			<col width="200"  align="center">
																			<col width="400"  align="center">
																			<col width="70"  align="center">
																			<tr>
																				<td align="left">
																					<table border="0" cellpadding="0" align="left" cellspacing="0">
																						<col>
																							<tr>
																							<td>Results 1000 - 1000 of 1000</td>
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
																							<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																							<td class="stab">/</td>
																							<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																							<td class="stab">page</td>
																							<td>&nbsp;</td>
																							<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
																							<td></td>
																							<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
																							<td></td>
																							<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
																						</tr>
																					</table>
																				</td>
																				<td></td>
																			</tr>
																		</table>
																		</ezf:skip>
																		<table width="100%">
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
																	<td></td>
								                                </tr>
								                            </tbody>
								                        </table>
								                        <!-- ######################################## TO (COMMON)PAGENATION ###################################### -->

														<%-- ########## SEARCH RESULT ########## --%>
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top:0px; margin-left:0px">
															<tr>
																<td valign="top">

																<div id="parentBoxA">

																	<div style="float:left; display:block"> <!-- left table -->
																		<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
																		<div id="leftTbl" style="float:left; display:block; height:100px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
																	</div>  <!-- left table -->
																	<div style="float:left"><!-- right table -->
																		<div id='rightTblHead' style="width:980px; display:block; overflow:hidden; margin:0px;padding:0px; height:395px;">
																			<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="950px" >
																				<col width="40px"  align="center"><%-- Check Box --%>
																				<col width="220px" align="center"><%-- Team Name --%>
																				<col width="570px" align="center"><%-- Description --%>
																				<col width="130px" align="center"><%-- Hierarchy Type --%>
																				<tr height="28">
																					<td id="AH0" class="pClothBs">&nbsp;</td>
																					<!--<td class="pClothBs"><u>Team Name</u></td>-->
																					<td id="AH1" class="pClothBs"><u>Team Name</u></td>
																					<!--<td class="pClothBs">Description</td>-->
																					<td id="AH2" class="pClothBs"><u>Description</u></td>
																					<!--<td class="pClothBs">Hierarchy Type</td>-->
																					<td id="AH3" class="pClothBs"><u>Hierarchy Type</u></td>
																				</tr>
																			</table>
																			<%-- ******************************** --%>
                                                                            <%-- *** Right Table Area(Detail) *** --%>
                                                                            <%-- ******************************** --%>
                                                                            <div  id="rightTblDtl" style="width:980px; height:383px; display:block; overflow:scroll; margin:0px; padding:0px;" >
                                                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="980px" >
                                                                                    <col width="40px"  align="center"><%-- Check Box --%>
																					<col width="220px" align="left"><%-- Team Name --%>
																					<col width="570px" align="left"><%-- Description --%>
																					<col width="130px" align="center"><%-- Hierarchy Type --%>
																					<ezf:row ezfHyo="A">
																					<%-- ----- line 1 ----- --%>
																					<tr height="28px" id="id_leftA_row{EZF_ROW_NUMBER}">
																						<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/></td>
																						<td><ezf:inputText name="apvlTeamNm_A1" ezfName="apvlTeamNm_A1" value="Procure - EAST1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
																						<td><ezf:inputText name="apvlTeamDescTxt_A1" ezfName="apvlTeamDescTxt_A1" value="ALL ESS Procurement for EAST Zone WH's" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"80\" maxlength=\"50\""/></td>
																						<td>
																							<ezf:select name="apvlHrchTpCd_AS" ezfName="apvlHrchTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="apvlHrchTpCd_AC" ezfDispName="apvlHrchTpDescTxt_AD" ezfCodeDispHyo="A" otherAttr=" style=\"width:120px\""/>
																						</td>
																						<td><ezf:inputHidden name="apvlTeamPk_A1" ezfName="apvlTeamPk_A1" value="Xxxx" ezfHyo="A" ezfArrayNo="0" /></td>
																					</tr>
																					</ezf:row>
																					<ezf:skip>
																						<%-- ----- line 2 ----- --%>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y"></td>
																							<td><input type="text" class="pHsu" size="30" maxlength="30" value="Procure - WEST1"></td>
																							<td><input type="text" size="80" maxlength="50" value=""></td>
																							<td>
																								<select class="pHsu" style="width:120px">
																									<option>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td><input type="hidden" value="Xxxx"></td>
																						</tr>
																					</ezf:skip>
                                                                                </table>
																			</div>
																		</div>
																	</div> <!-- right table -->
																	</div> <!-- parent box -->
																</td>
															</tr>
														</table>
														
														<%-- ########## LOWER BUTTON AREA ########## --%>
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:0px">
															<col width="799px" align="center"><%-- ===== space ===== --%>
															<col width="82px"  align="center"><%-- Insert Row Button --%>
															<col width="82px"  align="center"><%-- Delete Row Button --%>
															<tr>
																<td></td>
																<td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" /></td>
																<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" /></td>
															</tr>
														</table>
													</center>
												</div>
											</c:when>
											<%-- #################### Team TAB [END] #################### --%>

<%-- #################### Member TAB [START] #################### --%>
											<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Member'}">
												<div id="TabContent-Member">
													<script type="text/javascript">
														document.getElementById("Team").className = "pTab_OFF";
														document.getElementById("Member").className = "pTab_ON";
														document.getElementById("Transaction").className = "pTab_OFF";
														document.getElementById("Location").className = "pTab_OFF";
														document.getElementById("ApvlLimit").className = "pTab_OFF";
														document.getElementById("TechThrhd").className = "pTab_OFF";
														document.getElementById("TechMin").className = "pTab_OFF";
													</script>
													<%-- ########## SEARCH HEADER ########## --%>
													<table border="0" cellpadding="0" cellspacing="0" style="margin-top:8px; margin-left:5px">
														<col width="74px"  align="left"><%-- Team Name (L) --%>
														<col width="185px" align="left"><%-- Team Name --%>
														<col width="10px"  align="left"><%-- ====== spece ====== --%>
														<col width="40px"  align="left"><%-- Position (L) --%>
														<col width="130px" align="left"><%-- Position --%>
														<col width="10px"  align="left"><%-- ====== spece ====== --%>
														<col width="90px"  align="left"><%-- Member Name (L) --%>
														<col width="185px" align="left"><%-- Member Name --%>
														<col width="30px"  align="left"><%-- ====== spece ====== --%>
														<col width="50px"  align="left"><%-- Search Button --%>
														<tr>
															<!--<td class="stab">Team Name(*)</td>-->
															<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Team" >Team Name(*)</ezf:anchor></td>
															<td><ezf:inputText name="apvlTeamNm_MT" ezfName="apvlTeamNm_MT" value="Procure -EAST" otherAttr=" size=\"25\""/></td>
															<td></td>
															<td class="stab">Position</td>
															<td>
																<ezf:select name="apvlTeamPosnTpCd_MS" ezfName="apvlTeamPosnTpCd_MS" ezfBlank="1" ezfCodeName="apvlTeamPosnTpCd_MC" ezfDispName="apvlTeamPosnTpDescTxt_MD" otherAttr=" style=\"width:128px\""/>
															</td>
															<td></td>
															<!-- <td class="stab">Member Name(*)</td> -->
															<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Member" >Member Name(*)</ezf:anchor></td>
															<td><ezf:inputText name="fullPsnNm_MT" ezfName="fullPsnNm_MT" otherAttr=" size=\"25\""/></td>
															<td></td>
															<td><ezf:inputButton name="Search_Member" value="Search" htmlClass="pBtn7" /></td>
														</tr>
													</table>
													<hr>
													<center>
														<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
                        								<table  style="margin-left: 3px; width: 100%;" >
                            								<colgroup>
																<col width="260">
																<col width="350">
																<col width="45">
                            								</colgroup>
                            								<tbody>
								                                <tr>
								                                    <td>
								                                        <table cellSpacing="0" cellPadding="1" border="0" style="margin-left: 80px;">
								                                            <colgroup>
								                                                <col width="123">
								                                                <col width="">
								                                            </colgroup>
								                                            <tbody>
								                                                <tr>
								                                                    <td>
								                                                    </td>
								                                                </tr>
								                                            </tbody>
								                                        </table>
								                                    </td>
								                                    <td>
																		<ezf:skip>
																		<table border="0" cellpadding="0" width="">
																			<col width="200"  align="center">
																			<col width="400"  align="center">
																			<col width="80"  align="center">
																			<tr>
																				<td align="left">
																					<table border="0" cellpadding="0" align="left" cellspacing="0">
																						<col>
																							<tr>
																							<td>Results 1000 - 1000 of 1000</td>
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
																							<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																							<td class="stab">/</td>
																							<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																							<td class="stab">page</td>
																							<td>&nbsp;</td>
																							<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
																							<td></td>
																							<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
																							<td></td>
																							<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
																						</tr>
																					</table>
																				</td>
																				<td></td>
																			</tr>
																		</table>
																		</ezf:skip>
																		<table width="100%">
																			<tr align="right">
																				<td>
																					<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																						<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
																						<jsp:param name="TableNm"           value="B" />
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
																	<td></td>
								                                </tr>
								                            </tbody>
								                        </table>
								                        <!-- ######################################## TO (COMMON)PAGENATION ###################################### -->
														<%-- ########## SEARCH RESULT ########## --%>
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top:0px; margin-left:0px">
															<tr>
																<td valign="top">
																	<div id="parentBoxB">

																		<div style="float:left; display:block"> <!-- left table -->
																			<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
																			<div id="leftTbl" style="float:left; display:block; height:390px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
																		</div>  <!-- left table -->
																		<div style="float:left"> <!-- right table -->
																			<div id='rightTblHead' style="width:980; height:395px; display:block; overflow:hidden; margin:0px;padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="BHEAD" width="963px">
																					<col width="40px"  align="center"><%-- Check Box --%>
																					<col width="320px" align="center"><%-- Team Name --%>
																					<col width="155px" align="center"><%-- Position --%>
																					<col width="430px" align="center"><%-- Member Name --%>
																					<tr height="28px">
																						<td id="BH0" class="pClothBs">&nbsp;</td>
																						<td id="BH1" class="pClothBs"><u>Team Name</u></td>
																						<td id="BH2" class="pClothBs"><u>Position</u></td>
																						<td id="BH3" class="pClothBs"><u>Member Name</u></td>
																					</tr>
																				</table>
																				<%-- ******************************** --%>
                                                                            	<%-- *** Right Table Area(Detail) *** --%>
                                                                            	<%-- ******************************** --%>
                                                                           		<div  id="rightTblDtl" style="width:980; height:383px; display:block; overflow:scroll; margin:0px; padding:0px;" >
	                                                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="B" width="963px" >
	                                                                                    <col width="40px"  align="center"><%-- Check Box --%>
																						<col width="320px" align="left"><%-- Team Name --%>
																						<col width="155px" align="left"><%-- Position --%>
																						<col width="430px" align="left"><%-- Member Name --%>
																						<ezf:row ezfHyo="B">
																						<%-- ----- line 1 ----- --%>
																						<tr height="28px">
																							<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B1{EZF_ROW_NUMBER}\""/></td>
																							<td>
																								<ezf:inputButton name="OpenWin_Team" value="Team" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn5" />
																								<ezf:inputText name="apvlTeamNm_B1" ezfName="apvlTeamNm_B1" value="Procure - EAST1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\""/>
																							</td>
																							<td>
																									<ezf:select name="apvlTeamPosnTpCd_BS" ezfName="apvlTeamPosnTpCd_BS" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="apvlTeamPosnTpCd_BC" ezfDispName="apvlTeamPosnTpDescTxt_BD" ezfCodeDispHyo="B" otherAttr=" style=\"width:148px\""/>
																							</td>
																							<td>
																								<ezf:inputButton name="OpenWin_Member" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn2" />
																								<ezf:inputText name="psnCd_B1" ezfName="psnCd_B1" value="A0123456" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/>
																								<ezf:inputText name="fullPsnNm_B1" ezfName="fullPsnNm_B1" value="Mike Hirsh" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"62\""/>
																							</td>
																							<ezf:inputHidden name="apvlTeamMbrPk_B1" ezfName="apvlTeamMbrPk_B1" value="Xxxx" ezfHyo="B" ezfArrayNo="0" />
																							<ezf:inputHidden name="apvlTeamPk_B1" ezfName="apvlTeamPk_B1" value="Xxxx" ezfHyo="B" ezfArrayNo="0" />
																						</tr>
																						</ezf:row>
																						<ezf:skip>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_B1" ezfname="xxChkBox_B1" ezfhyo="B" id="xxChkBox_B1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<input type="button" class="pBtn5" name="OpenWin_Team" value="Team" ezfhyo="B" onclick="sendServer('OpenWin_Team')" disabled>
																								<input type="text" class="pPro" readonly size="30" maxlength="30" value="Procure - EAST1" name="apvlTeamNm_B1" ezfname="apvlTeamNm_B1" ezfhyo="B">
																							</td>
																							<td>
																									<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_BS" ezfname="apvlTeamPosnTpCd_BS" ezfhyo="B" ezflist="apvlTeamPosnTpCd_BC,apvlTeamPosnTpDescTxt_BD,99,B,blank">
																										<option selected>Vice President</option>
																										<option>Senior Director</option>
																										<option>Director</option>
																										<option>Seniror Manager</option>
																										<option>Manager</option>
																										<option>Supervisor</option>
																									</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfhyo="B" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_B1" ezfname="psnCd_B1" ezfhyo="B" ezftoupper>
																								<input type="text" class="pPro" readonly  size="40" maxlength="62" value="Mike Hirsh" name="fullPsnNm_B1" ezfname="fullPsnNm_B1" ezfhyo="B">
																							</td>
																							<input type="hidden" value="Xxxx" name="apvlTeamMbrPk_B1" ezfname="apvlTeamMbrPk_B1" ezfhyo="B">
																							<input type="hidden" value="Xxxx" name="apvlTeamPk_B1" ezfname="apvlTeamPk_B1" ezfhyo="B">
																						</tr>
																						</ezf:skip>
	                                                                                </table>
																				</div>
																			</div>
																		</div>
																	</div>
																</td>
															</tr>
														</table>
													</center>

													<%-- ########## LOWER BUTTON AREA ########## --%>
													<table border="0" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:0px">
														<col width="120px"  align="center"><%-- ===== space ===== --%>
														<col width="65px"  align="center"><%-- Import File --%>
														<col width="100px"  align="center"><%-- Import File Text --%>
														<col width="5px"  align="center"><%-- ===== space ===== --%>
														<col width="75px"  align="center"><%-- Upload Button --%>
														<col width="10px"  align="center"><%-- ===== space ===== --%>
														<col width="75px"  align="center"><%-- Copy Row Button --%>
														<col width="3px"  align="center"><%-- ===== space ===== --%>
														<col width="75px"  align="center"><%-- Insert Row Button --%>
														<col width="3px"  align="center"><%-- ===== space ===== --%>
														<col width="75px"  align="center"><%-- Delete Row Button --%>
														<tr>
															<td class="stab"></td>
															<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TempleteFileForUpload" >Import File</ezf:anchor></td>
															<td>
																<ezf:inputFile name="xxFileData_UP" ezfName="xxFileData_UP" otherAttr=" size=\"50\" maxlength=\"9999\" ezftoupper=\"\""/>
															</td>
															<td></td>
															<td><ezf:inputButton name="Upload_Member" value="Upload" htmlClass="pBtn6" /></td>
															<td></td>
															
															<td><ezf:inputButton name="CopyRow" value="Copy Row" htmlClass="pBtn7" /></td>
															<td></td>
															<td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" /></td>
															<td></td>
															<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" /></td>
														</tr>
													</table>
												</div>
											</c:when>
											<%-- #################### Member TAB [END] #################### --%>
											
											<%-- #################### Transaction TAB [START] #################### --%>
											<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Transaction'}">
												<div id="TabContent-Transaction">
													<script type="text/javascript">
														document.getElementById("Team").className = "pTab_OFF";
														document.getElementById("Member").className = "pTab_OFF";
														document.getElementById("Transaction").className = "pTab_ON";
														document.getElementById("Location").className = "pTab_OFF";
														document.getElementById("ApvlLimit").className = "pTab_OFF";
														document.getElementById("TechThrhd").className = "pTab_OFF";
														document.getElementById("TechMin").className = "pTab_OFF";
													</script>
													<%-- ########## SEARCH HEADER ########## --%>
													<table border="0" cellpadding="0" cellspacing="0" style="margin-top:8px; margin-left:5px">
														<col width="74px"  align="left"><%-- Team Name (L) --%>
														<col width="185px" align="left"><%-- Team Name --%>
														<col width="10px"  align="left"><%-- ====== spece ====== --%>
														<col width="85px"  align="left"><%-- Planning Group (L) --%>
														<col width="130px" align="left"><%-- Planning Group --%>
														<col width="10px"  align="left"><%-- ====== spece ====== --%>
														<col width="65px"  align="left"><%-- Transaction (L) --%>
														<col width="180px" align="left"><%-- Transaction --%>
														<col width="30px"  align="left"><%-- ====== spece ====== --%>
														<col width="50px"  align="left"><%-- Search Button --%>
														<tr>
															<!--<td class="stab">Team Name(*)</td>-->
															<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Team" >Team Name(*)</ezf:anchor></td>
															<td><ezf:inputText name="apvlTeamNm_ST" ezfName="apvlTeamNm_ST" value="Procure -EAST" otherAttr=" size=\"25\""/></td>
															<td></td>
															<td class="stab">Planning Group</td>
															<td>
																<ezf:select name="prchGrpCd_SS" ezfName="prchGrpCd_SS" ezfBlank="1" ezfCodeName="prchGrpCd_SC" ezfDispName="prchGrpDescTxt_SD" otherAttr=" style=\"width:128px\""/>
															</td>
															<td></td>
															<td class="stab">Transaction</td>
															<td>
																<ezf:select name="apvlHistSrcTpCd_SS" ezfName="apvlHistSrcTpCd_SS" ezfBlank="1" ezfCodeName="apvlHistSrcTpCd_SC" ezfDispName="apvlHistSrcTpDescTxt_SD" otherAttr=" style=\"width:178px\""/>
															</td>
															<td></td>
															<td><ezf:inputButton name="Search_TeamTransaction" value="Search" htmlClass="pBtn7" /></td>
														</tr>
													</table>
													<hr>
													<center>
														<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
														<table border="0" cellpadding="0" cellspacing="0">
															<col width="230">
															<col width="555">
															<tr>
																<td></td>
																<td align="right">
																	<ezf:skip>
																	<table border="0" cellpadding="0" width="100%">
																		<tr>
																			<td align="left">
																				<table border="0" cellpadding="0" align="left" cellspacing="0">
																					<col>
																						<tr>
																						<td>Results 1000 - 1000 of 1000</td>
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
																						<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																						<td class="stab">/</td>
																						<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																						<td class="stab">page</td>
																						<td>&nbsp;</td>
																						<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
																						<td></td>
																						<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
																						<td></td>
																						<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																	</ezf:skip>
																	<table width="100%">
																		<tr align="right">
																			<td>
																				<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																					<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
																					<jsp:param name="TableNm"           value="C" />
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
								                        <!-- ######################################## TO (COMMON)PAGENATION ###################################### -->
								                        <!--
														<%-- ########## UPPER BUTTON AREA ########## --%>
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top:5px; margin-left:0px">
															<col width="72px"  align="center"><%-- Select All Button --%>
															<col width="72px"  align="center"><%-- Un Select All Button --%>
															<col width="598px" align="center"><%-- ===== space ===== --%>
															<tr>
																<td><input type="button" class="pBtn6" name="SelectAll" value="Select All" onclick="sendServer(this)"></td>
																<td><input type="button" class="pBtn6" name="UnSelectAll" value="Un Select All" onclick="sendServer(this)"></td>
																<td></td>
															</tr>
														</table>
														-->
														<%-- ########## SEARCH RESULT ########## --%>
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top:0px; margin-left:0px">
															<tr>
																<td valign="top">
																	<div id="parentBoxC">
																		<div style="float:left; display:block"> <!-- left table -->
																			<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
																			<div id="leftTbl" style="float:left; display:block; height:390px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
																		</div>  <!-- left table -->
																		<div style="float:left"> <!-- right table -->
																			<div id='rightTblHead' style="width:807px; height:395px; display:block; overflow:hidden; margin:0px;padding:0px;">
																			<!--<div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('btmTBL'));">-->
																		<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="CHEAD" width="790px">
																			<col width="40px"  align="center"><%-- Check Box --%>
																			<!--<col width="65px"  align="center"><%-- Team Button --%>-->
																			<col width="320px" align="center"><%-- Team Name --%>
																			<col width="100px" align="center"><%-- Planning Group --%>
																			<col width="120px" align="center"><%-- PARTS/MERCH --%>
																			<col width="180px" align="center"><%-- Transactions --%>
																			<tr height="28px">
																				<td id="CH0" class="pClothBs">&nbsp;</td>
																				<td id="CH1" class="pClothBs"><u>Team Name</u></td>
																				<td id="CH2" class="pClothBs"><u>Planning Group</u></td>
																				<td id="CH3" class="pClothBs"><u>Parts/Merch</u></td>
																				<td id="CH4" class="pClothBs"><u>Transactions</u></td>
																			</tr>
																		</table>
																		<%-- ******************************** --%>
                                                                        <%-- *** Right Table Area(Detail) *** --%>
                                                                        <%-- ******************************** --%>
                                                                        <div  id="rightTblDtl" style="width:807px; height:383px; display:block; overflow:scroll; margin:0px; padding:0px;" >
	                                                                    	<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="C" width="790px" >
                                                                                <col width="40px"  align="center"><%-- Check Box --%>
																				<!--<col width="65px"  align="center"><%-- Team Button --%>-->
																				<col width="320px" align="center"><%-- Team Name --%>
																				<col width="100px" align="center"><%-- Planning Group --%>
																				<col width="120px" align="center"><%-- PARTS/MERCH --%>
																				<col width="180px" align="center"><%-- Transactions --%>
																				<ezf:row ezfHyo="C">
																				<%-- ----- line 1 ----- --%>
																				<tr height="28px">
																					<td><ezf:inputCheckBox name="xxChkBox_C1" ezfName="xxChkBox_C1" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B1{EZF_ROW_NUMBER}\""/></td>
																					<td>
																						<ezf:inputButton name="OpenWin_Team" value="Team" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn5" />
																						<ezf:inputText name="apvlTeamNm_C1" ezfName="apvlTeamNm_C1" value="Procure - EAST1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\""/>
																					</td>
																					<td>
																						<ezf:select name="prchGrpCd_CS" ezfName="prchGrpCd_CS" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prchGrpCd_CC" ezfDispName="prchGrpDescTxt_CD" ezfCodeDispHyo="C" otherAttr=" style=\"width:98px\""/>
																					</td>
																					<td>
																						<ezf:select name="mdseItemTpCd_CS" ezfName="mdseItemTpCd_CS" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mdseItemTpCd_CC" ezfDispName="mdseItemTpDescTxt_CD" ezfCodeDispHyo="C" otherAttr=" style=\"width:118px\""/>
																					</td>
																					<td>
																						<ezf:select name="apvlHistSrcTpCd_CS" ezfName="apvlHistSrcTpCd_CS" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="apvlHistSrcTpCd_CC" ezfDispName="apvlHistSrcTpDescTxt_CD" ezfCodeDispHyo="C" otherAttr=" style=\"width:178px\""/>
																					</td>
																					<ezf:inputHidden name="apvlTeamTrxPk_C1" ezfName="apvlTeamTrxPk_C1" value="Xxxx" ezfHyo="C" ezfArrayNo="0" />
																					<ezf:inputHidden name="apvlTeamPk_C1" ezfName="apvlTeamPk_C1" value="Xxxx" ezfHyo="C" ezfArrayNo="0" />
																				</tr>
																				</ezf:row>
																				<ezf:skip>
																				<tr height="28px">
																					<td><input type="checkbox" class="" value="Y" name="xxChkBox_C1" ezfname="xxChkBox_C1" ezfhyo="C" id="xxChkBox_B1{EZF_ROW_NUMBER}"></td>
																					<td>
																						<input type="button" class="pBtn5" name="OpenWin_Team" value="Team" ezfHyo="C" onclick="sendServer('OpenWin_Team')" disabled>
																						<input type="text" class="pPro" readonly size="30" maxlength="30" value="Procure - EAST1" name="apvlTeamNm_C1" ezfname="apvlTeamNm_C1" ezfhyo="C">
																					</td>
																					<td>
																						<select class="pHsu" style="width:98px" name="prchGrpCd_CS" ezfname="prchGrpCd_CS" ezfhyo="C" ezflist="prchGrpCd_CC,prchGrpDescTxt_CD,99,C,blank">
																							<option selected>ESS</option>
																							<option>LFS</option>
																							<option>PPS</option>
																						</select>
																					</td>
																					<td>
																						<select class="pHsu" style="width:118px" name="mdseItemTpCd_CS" ezfname="mdseItemTpCd_CS" ezfhyo="C" ezflist="mdseItemTpCd_CC,mdseItemTpDescTxt_CD,99,C,blank">
																							<option selected>01 Machine</option>
																							<option>04 Parts</option>
																						</select>
																					</td>
																					<td>
																						<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_CS" ezfname="apvlHistSrcTpCd_CS" ezfhyo="C" ezflist="apvlHistSrcTpCd_CC,apvlHistSrcTpDescTxt_CD,99,C,blank">
																							<option selected>Purchase Order</option>
																							<option>Parts Request</option>
																							<option>Inventory Request</option>
																							<option>Purchase Requisition</option>
																						</select>
																					</td>
																					<input type="hidden" value="Xxxx" name="apvlTeamTrxPk_C1" ezfname="apvlTeamTrxPk_C1" ezfhyo="C">
																					<input type="hidden" value="Xxxx" name="apvlTeamPk_C1" ezfname="apvlTeamPk_C1" ezfhyo="C">
																				</tr>
																				</ezf:skip>	
                                                                            </table>
																		</div>
																		</div>
																	</div>
																</div> <!-- parent box -->
																</td>
															</tr>
														</table>
														<%-- ########## LOWER BUTTON AREA ########## --%>
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:0px">
															<col width="482px" align="center"><%-- ===== space ===== --%>
															<col width="82px"  align="center"><%-- Copy Row Button --%>
															<col width="82px"  align="center"><%-- Insert Row Button --%>
															<col width="82px"  align="center"><%-- Delete Row Button --%>
															<tr>
																<td></td>
																<td><ezf:inputButton name="CopyRow" value="Copy Row" htmlClass="pBtn7" /></td>
																<td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" /></td>
																<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" /></td>
															</tr>
														</table>
													</center>
												</div>
											</c:when>
											<%-- #################### Transaction TAB [END] #################### --%>
											
											<%-- #################### Location TAB [START] #################### --%>
											<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Location'}">
												<div id="TabContent-Location">
													<script type="text/javascript">
														document.getElementById("Team").className = "pTab_OFF";
														document.getElementById("Member").className = "pTab_OFF";
														document.getElementById("Transaction").className = "pTab_OFF";
														document.getElementById("Location").className = "pTab_ON";
														document.getElementById("ApvlLimit").className = "pTab_OFF";
														document.getElementById("TechThrhd").className = "pTab_OFF";
														document.getElementById("TechMin").className = "pTab_OFF";
													</script>
													<%-- ########## SEARCH HEADER ########## --%>
													<table border="0" cellpadding="0" cellspacing="0" style="margin-top:8px; margin-left:5px">
														<col width="74px"  align="left"><%-- Team Name (L) --%>
														<col width="185px" align="left"><%-- Team Name --%>
														<col width="10px"  align="left"><%-- ====== spece ====== --%>
														<col width="70px"  align="left"><%-- Warehouse(*) (L) --%>
														<col width="185px" align="left"><%-- Warehouse(*) --%>
														<col width="30px"  align="left"><%-- ====== spece ====== --%>
														<col width="50px"  align="left"><%-- Search Button --%>
														<tr>
															<!--<td class="stab">Team Name(*)</td>-->
															<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Team" >Team Name(*)</ezf:anchor></td>
															<td><ezf:inputText name="apvlTeamNm_LT" ezfName="apvlTeamNm_LT" value="Procure -EAST" otherAttr=" size=\"25\""/></td>
															<td></td>
															<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_WH" >Warehouse(*)</ezf:anchor></td>
															<td>
																<!--<input type="text" size="3" value="A01">-->
																<ezf:inputText name="rtlWhNm_LT" ezfName="rtlWhNm_LT" value="MONROE" otherAttr=" size=\"20\""/>
															</td>
															<td></td>
															<td><ezf:inputButton name="Search_TeamLocation" value="Search" htmlClass="pBtn7" /></td>
														</tr>
													</table>
													<hr>
													<center>
														<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
                        								<table border="0" style="margin-left: 3px; width: 100%;" >
                            								<colgroup>
																<col width="180">
																<col width="400">
																<col width="130">
                            								</colgroup>
                            								<tbody>
								                                <tr>
								                                    <td>
								                                        <table cellSpacing="0" cellPadding="1" border="0" style="margin-left: 190px;">
								                                            <colgroup>
								                                                <col width="123">
								                                                <col width="">
								                                            </colgroup>
								                                            <tbody>
								                                                <tr>
								                                                    <td>
								                                                        <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxtD\""/>
								                                                    </td>
								                                                </tr>
								                                            </tbody>
								                                        </table>
								                                    </td>
								                                    <td>
																		<ezf:skip>
																		<table border="0" cellpadding="0" width="100%">
																			<col width="260"  align="center">
																			<col width="200"  align="center">
																			<col width="200"  align="center">
																			<tr>
																				<td align="left">
																					<table border="0" cellpadding="0" align="left" cellspacing="0">
																						<col>
																							<tr>
																							<td>Results 1000 - 1000 of 1000</td>
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
																							<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																							<td class="stab">/</td>
																							<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																							<td class="stab">page</td>
																							<td>&nbsp;</td>
																							<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
																							<td></td>
																							<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
																							<td></td>
																							<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
																						</tr>
																					</table>
																				</td>
																				<td></td>
																			</tr>
																		</table>
																		</ezf:skip>
																		<table width="100%">
																			<tr align="right">
																				<td>
																					<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																						<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
																						<jsp:param name="TableNm"           value="D" />
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
																	<td></td>
								                                </tr>
								                            </tbody>
								                        </table>

								                        <!-- ######################################## TO (COMMON)PAGENATION ###################################### -->

														<%-- ########## SEARCH RESULT ########## --%>
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top:0px; margin-left:0px">
															<tr>
																<td valign="top">
																	<div id="parentBoxD">
																		<div style="float:left; display:block"> <!-- left table -->
																			<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
																			<div id="leftTbl" style="float:left; display:block; height:390px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
																		</div>  <!-- left table -->
																		<div style="float:left"> <!-- right table -->
																			<div id='rightTblHead' style="width:750px; height:395px; display:block; overflow:hidden; margin:0px;padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="DHEAD" width="733px">
																					<col width="40px"  align="center"><%-- Check Box --%>
																					<col width="300px" align="center"><%-- Team Name --%>
																					<col width="380px"  align="center"><%-- Warehouse --%>
																					<tr height="28px">
																						<td id="DH0" class="pClothBs">&nbsp;</td>
																						<td id="DH1" class="pClothBs"><u>Team Name</u></td>
																						<td id="DH2" class="pClothBs"><u>Warehouse</u></td>
																					</tr>
																				</table>
																				<%-- ******************************** --%>
		                                                                        <%-- *** Right Table Area(Detail) *** --%>
		                                                                        <%-- ******************************** --%>
		                                                                        <div  id="rightTblDtl" style="width:750px; height:383px; display:block; overflow:scroll; margin:0px; padding:0px;" >
			                                                                    	<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="D" width="733px" >
		                                                                                <col width="40px"  align="center"><%-- Check Box --%>
																						<col width="300px" align="center"><%-- Team Name --%>
																						<col width="380px"  align="center"><%-- Warehouse --%>
																						<ezf:row ezfHyo="D">
																						<%-- ----- line 1 ----- --%>
																						<tr height="28px">
																							<td><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="D" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_D1{EZF_ROW_NUMBER}\""/></td>
																							<td>
																								<ezf:inputButton name="OpenWin_Team" value="Team" ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn5" />
																								<ezf:inputText name="apvlTeamNm_D1" ezfName="apvlTeamNm_D1" value="Procure - EAST1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\""/>
																							</td>
																							<td>
																								<ezf:inputButton name="OpenWin_WH" value="..." ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn2" />
																								<ezf:inputText name="rtlWhCd_D1" ezfName="rtlWhCd_D1" value="A01" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
																								<ezf:inputText name="rtlWhNm_D1" ezfName="rtlWhNm_D1" value="MONROE" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\""/>
																							</td>
																							<ezf:inputHidden name="apvlTeamLocPk_D1" ezfName="apvlTeamLocPk_D1" value="Xxxx" ezfHyo="D" ezfArrayNo="0" />
																							<ezf:inputHidden name="apvlTeamPk_D1" ezfName="apvlTeamPk_D1" value="Xxxx" ezfHyo="D" ezfArrayNo="0" />
																						</tr>
																						</ezf:row>
																						<ezf:skip>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_D1" ezfname="xxChkBox_D1" ezfhyo="D" id="xxChkBox_D1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<input type="button" class="pBtn5" name="OpenWin_Team" value="Team" ezfhyo="D" onclick="sendServer('OpenWin_Team')" disabled>
																								<input type="text" class="pPro" readonly size="30" maxlength="30" value="Procure - EAST1" name="apvlTeamNm_D1" ezfname="apvlTeamNm_D1" ezfhyo="D">
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_WH" ezfhyo="D" value="..." onclick="sendServer('OpenWin_WH')">
																								<input type="text" class="pHsu" size="10" maxlength="20" value="A01" name="rtlWhCd_D1" ezfname="rtlWhCd_D1" ezfhyo="D" ezftoupper>
																								<input type="text" class="pPro" readonly size="30" maxlength="30" value="MONROE" name="rtlWhNm_D1" ezfname="rtlWhNm_D1" ezfhyo="D">
																							</td>
																							<input type="hidden" value="Xxxx" name="apvlTeamLocPk_D1" ezfname="apvlTeamLocPk_D1" ezfhyo="D">
																							<input type="hidden" value="Xxxx" name="apvlTeamPk_D1" ezfname="apvlTeamPk_D1" ezfhyo="D">
																						</tr>
																						</ezf:skip>	
		                                                                            </table>
																				</div>
																			</div>
																		</div> <!-- right table -->
																	</div> <!-- parent box -->
																</td>
															</tr>
														</table>
														<%-- ########## LOWER BUTTON AREA ########## --%>
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:0px">
															<col width="15px"  align="center"><%-- ===== space ===== --%>
															<col width="55px"  align="center"><%-- Import File --%>
															<col width="150px"  align="center"><%-- Import File Text --%>
															<col width="5px"  align="center"><%-- ===== space ===== --%>
															<col width="60px"  align="center"><%-- Upload Button --%>
															<col width="10px"  align="center"><%-- ===== space ===== --%>
															
															<col width="10px"  align="center"><%-- Copy Row Button --%>
															<col width="3px"  align="center"><%-- ===== space ===== --%>
															<col width="50px"  align="center"><%-- Insert Row Button --%>
															<col width="3px"  align="center"><%-- ===== space ===== --%>
															<col width="50px"  align="center"><%-- Delete Row Button --%>
															<tr>
																<td class="stab"></td>
																<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TempleteFileForUpload" >Import File</ezf:anchor></td>
																<td>
																	<ezf:inputFile name="xxFileData_UP" ezfName="xxFileData_UP" otherAttr=" size=\"55\" maxlength=\"9999\" ezftoupper=\"\""/>
																</td>
																<td></td>
																<td><ezf:inputButton name="Upload_WH" value="Upload" htmlClass="pBtn7" /></td>
																<td></td>
																
																<td><ezf:inputButton name="CopyRow" value="Copy Row" htmlClass="pBtn7" /></td>
																<td></td>
																<td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" /></td>
																<td></td>
																<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" /></td>
															</tr>
														</table>
													</center>
												</div>
											</c:when>
											<%-- #################### Location TAB [END] ################### --%>
											
											<%-- #################### ApvlLimit TAB [START] #################### --%>
											<c:when test="${pageScope._ezddatabean.xxDplyTab == 'ApvlLimit'}">
												<div id="TabContent-ApvlLimit">
													<script type="text/javascript">
														document.getElementById("Team").className = "pTab_OFF";
														document.getElementById("Member").className = "pTab_OFF";
														document.getElementById("Transaction").className = "pTab_OFF";
														document.getElementById("Location").className = "pTab_OFF";
														document.getElementById("ApvlLimit").className = "pTab_ON";
														document.getElementById("TechThrhd").className = "pTab_OFF";
														document.getElementById("TechMin").className = "pTab_OFF";
													</script>
													<%-- ########## SEARCH HEADER ########## --%>
													<table border="0" cellpadding="0" cellspacing="0" style="margin-top:8px; margin-left:5px">
														<col width="78px"  align="left"><%-- Hierarchy Type (L) --%>
														<col width="100px" align="left"><%-- Hierarchy Type --%>
														<col width="10px"  align="left"><%-- ====== spece ====== --%>
														<col width="40px"  align="left"><%-- Position (L) --%>
														<col width="135px" align="left"><%-- Position --%>
														<col width="10px"  align="left"><%-- ====== spece ====== --%>
														<col width="100px" align="left"><%-- Employee Name (L) --%>
														<col width="145px" align="left"><%-- Employee Name --%>
														<col width="10px"  align="left"><%-- ====== spece ====== --%>
														<col width="80px"  align="left"><%-- Planning Group (L) --%>
														<col width="60px"  align="left"><%-- Planning Group --%>
														<col width="10px"  align="left"><%-- ====== spece ====== --%>
														<col width="70px"  align="left"><%-- Transactions (L) --%>
														<col width="170px" align="left"><%-- Transactions --%>
														<col width="8px"  align="left"><%-- ====== spece ====== --%>
														<col width="35px"  align="left"><%-- Search Button --%>
														<tr height="20">
															<td rowspan="2" valign="top" class="stab">Hierarchy Type</td>
															<td rowspan="2" valign="top">
																<ezf:select name="apvlHrchTpCd_LS" ezfName="apvlHrchTpCd_LS" ezfBlank="1" ezfCodeName="apvlHrchTpCd_LC" ezfDispName="apvlHrchTpDescTxt_LD" otherAttr=" style=\"width:98px\""/>
															</td>
															<td rowspan="2" valign="top"></td>
															<td rowspan="2" valign="top" class="stab">Position</td>
															<td rowspan="2" valign="top">
																<ezf:select name="apvlTeamPosnTpCd_AS" ezfName="apvlTeamPosnTpCd_AS" ezfBlank="1" ezfCodeName="apvlTeamPosnTpCd_AC" ezfDispName="apvlTeamPosnTpDescTxt_AD" otherAttr=" style=\"width:132px\""/>
															</td>
															<td rowspan="2" valign="top"></td>
															<td rowspan="2" valign="top" class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Member" >Employee Name(*)</ezf:anchor></td>
															<td rowspan="2" valign="top"><ezf:inputText name="fullPsnNm_AT" ezfName="fullPsnNm_AT" otherAttr=" size=\"20\""/></td>
															<td rowspan="2" valign="top"></td>
															<td rowspan="2" valign="top" class="stab">Planning Group</td>
															<td rowspan="2" valign="top">
																<ezf:select name="prchGrpCd_AS" ezfName="prchGrpCd_AS" ezfBlank="1" ezfCodeName="prchGrpCd_AC" ezfDispName="prchGrpDescTxt_AD" otherAttr=" style=\"width:60px\""/>
															</td>
															<td rowspan="2" valign="top"></td>
															<td class="stab" valign="top">Transactions</td>
															<td valign="top">
																<ezf:select name="apvlHistSrcTpCd_AS" ezfName="apvlHistSrcTpCd_AS" ezfBlank="1" ezfCodeName="apvlHistSrcTpCd_AC" ezfDispName="apvlHistSrcTpDescTxt_AD" otherAttr=" style=\"width:168px\""/>
															</td>
															<td rowspan="2" valign="top"></td>
															<td rowspan="2" valign="top"><ezf:inputButton name="Search_ApprovalLimit" value="Search" htmlClass="pBtn7" /></td>
														</tr>
														<tr height="20">
															<td class="stab" valign="top">Request Type</td>
															<td valign="top">
																<ezf:select name="prchReqTpCd_AS" ezfName="prchReqTpCd_AS" ezfBlank="1" ezfCodeName="prchReqTpCd_AC" ezfDispName="prchReqTpNm_AD" otherAttr=" style=\"width:168px\""/>
															</td>
														</tr>
													</table>
													<hr>
													<center>
													<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
                        							<table style="margin-left: 3px; width: 100%;" >
                            							<colgroup>
																<col width="335">
																<col width="350">
																<col width="40">
                            							</colgroup>
                            							<tbody>
								                            <tr>
								                                <td>
								                                    <table cellSpacing="0" cellPadding="1" border="0" style="margin-left: 65px;">
								                                        <colgroup>
								                                            <col width="123">
								                                            <col width="">
								                                        </colgroup>
								                                        <tbody>
								                                            <tr>
								                                                <td>
								                                                    <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxtE\""/>
								                                                </td>
								                                            </tr>
								                                        </tbody>
								                                    </table>
								                                </td>
								                                <td>
																	<ezf:skip>
																	<table border="0" cellpadding="0" width="">
																		<col width="250"  align="center">
																		<col width="350"  align="center">
																		<col width="70"  align="center">
																		<tr>
																			<td align="left">
																				<table border="0" cellpadding="0" align="left" cellspacing="0">
																					<col>
																						<tr>
																						<td>Results 1000 - 1000 of 1000</td>
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
																						<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																						<td class="stab">/</td>
																						<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																						<td class="stab">page</td>
																						<td>&nbsp;</td>
																						<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
																						<td></td>
																						<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
																						<td></td>
																						<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
																					</tr>
																				</table>
																			</td>
																			<td></td>
																		</tr>
																	</table>
																	</ezf:skip>
																	<table width="100%">
																		<tr align="right">
																			<td>
																				<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																					<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
																					<jsp:param name="TableNm"           value="E" />
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
																<td></td>
								                            </tr>
								                        </tbody>
								                    </table>
								                    <!-- ######################################## TO (COMMON)PAGENATION ###################################### -->
								                    <!--
													<%-- ########## UPPER BUTTON AREA ########## --%>
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top:5px; margin-left:0px">
															<col width="72px"  align="center"><%-- Select All Button --%>
															<col width="72px"  align="center"><%-- Un Select All Button --%>
															<col width="595px" align="center"><%-- ===== space ===== --%>
															<tr>
																<td><input type="button" class="pBtn6" name="SelectAll" value="Select All" onclick="sendServer(this)"></td>
																<td><input type="button" class="pBtn6" name="UnSelectAll" value="Un Select All" onclick="sendServer(this)"></td>
																<td></td>
															</tr>
														</table>
														-->
														<%-- ########## SEARCH RESULT ########## --%>
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top:0px; margin-left:0px">
															<tr>
																<td valign="top">
																	<div id="parentBoxE">
																		<div style="float:left; display:block"> <!-- left table -->
																			<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
																			<div id="leftTbl" style="float:left; display:block; height:390px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
																		</div>  <!-- left table -->
																		<div style="float:left"> <!-- right table -->
																			<div id='rightTblHead' style="width:1087px; height:400px; display:block; overflow:scroll; margin:0px;padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="EHEAD"  width="1070px">
																					<col width="40px"  align="center"><%-- Check Box --%>
																					<col width="135px" align="center"><%-- Hierarchy Type --%>
																					<col width="155px" align="center"><%-- Position --%>
																					<col width="330px" align="center"><%-- Employee Name --%>
																					<col width="105px" align="center"><%-- Planning Group --%>
																					<col width="185px" align="center"><%-- Transactions --%>
																					<col width="125px" align="center"><%-- Request Type --%>
																					<col width="120px" align="center"><%-- $Limit --%>
																					<tr height="28px">
																						<td id="EH0" class="pClothBs">&nbsp;</td>
																						<td id="EH1" class="pClothBs"><u>Hierarchy Type</u></td>
																						<td id="EH2" class="pClothBs"><u>Position</u></td>
																						<td id="EH3" class="pClothBs"><u>Employee Name</u></td>
																						<td id="EH4" class="pClothBs"><u>Planning Group</u></td>
																						<td id="EH5" class="pClothBs"><u>Transactions</u></td>
																						<td id="EH6" class="pClothBs"><u>Request Type</u></td>
																						<td id="EH7" class="pClothBs"><u>$Limit</u></td>
																					</tr>
																				</table>
																				<%-- ******************************** --%>
		                                                                        <%-- *** Right Table Area(Detail) *** --%>
		                                                                        <%-- ******************************** --%>
		                                                                        <div  id="rightTblDtl" style="width:1212px; height:390px; display:block; overflow:hidden; margin:0px; padding:0px;" >
			                                                                    	<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="E" width="1195px" >
		                                                                                <col width="40px"  align="center"><%-- Check Box --%>
																						<col width="135px" align="center"><%-- Hierarchy Type --%>
																						<col width="155px" align="center"><%-- Position --%>
																						<col width="330px" align="center"><%-- Employee Name --%>
																						<col width="105px" align="center"><%-- Planning Group --%>
																						<col width="185px" align="center"><%-- Transactions --%>
																						<col width="125px" align="center"><%-- Request Type --%>
																						<col width="120px" align="center"><%-- $Limit --%>
																						<ezf:row ezfHyo="E">
																						<%-- ----- line 1 ----- --%>
																						<tr height="28px">
																							<td><ezf:inputCheckBox name="xxChkBox_E1" ezfName="xxChkBox_E1" value="Y" ezfHyo="E" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_E1{EZF_ROW_NUMBER}\""/></td>
																							<td>
																								<ezf:select name="apvlHrchTpCd_ES" ezfName="apvlHrchTpCd_ES" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="apvlHrchTpCd_EC" ezfDispName="apvlHrchTpDescTxt_ED" ezfCodeDispHyo="E" otherEvent1=" onchange=\"sendServerForPreferredView('OnChange_HierarchyType')\"" otherAttr=" style=\"width:128px\""/>
																							</td>
																							<td>
																								<ezf:select name="apvlTeamPosnTpCd_ES" ezfName="apvlTeamPosnTpCd_ES" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="apvlTeamPosnTpCd_EC" ezfDispName="apvlTeamPosnTpDescTxt_ED" ezfCodeDispHyo="E" otherAttr=" style=\"width:148px\""/>
																							</td>
																							<td>
																								<ezf:inputButton name="OpenWin_Member" value="..." ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn2" />
																								<ezf:inputText name="psnCd_E1" ezfName="psnCd_E1" value="A0123456" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/>
																								<ezf:inputText name="fullPsnNm_E1" ezfName="fullPsnNm_E1" value="Mike Hirsh" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"62\""/>
																							</td>
																							<td>
																								<ezf:select name="prchGrpCd_ES" ezfName="prchGrpCd_ES" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prchGrpCd_EC" ezfDispName="prchGrpDescTxt_ED" ezfCodeDispHyo="E" otherAttr=" style=\"width:98px\""/>
																							</td>
																							<td>
																								<ezf:select name="apvlHistSrcTpCd_ES" ezfName="apvlHistSrcTpCd_ES" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="apvlHistSrcTpCd_EC" ezfDispName="apvlHistSrcTpDescTxt_ED" ezfCodeDispHyo="E" otherEvent1=" onchange=\"sendServerForPreferredView('OnChange_Transactions', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:178px\""/>
																							</td>
																							<td>
																								<ezf:select name="prchReqTpCd_ES" ezfName="prchReqTpCd_ES" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prchReqTpCd_EC" ezfDispName="prchReqTpNm_ED" ezfCodeDispHyo="E" otherAttr=" style=\"width:118px\""/>
																							</td>
																							<td><ezf:inputText name="apvlLimitAmt_E1" ezfName="apvlLimitAmt_E1" value="500,000.00" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"15\" style=\"text-align:right;\""/></td>
																							<ezf:inputHidden name="apvlLimitPk_E1" ezfName="apvlLimitPk_E1" value="Xxxx" ezfHyo="E" ezfArrayNo="0" />
																						</tr>
																						</ezf:row>
																						<ezf:skip>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						<tr height="28px">
																							<td><input type="checkbox" class="" value="Y" name="xxChkBox_E1" ezfname="xxChkBox_E1" ezfhyo="E" id="xxChkBox_E1{EZF_ROW_NUMBER}"></td>
																							<td>
																								<select class="pHsu" style="width:128px" name="apvlHrchTpCd_ES" ezfname="apvlHrchTpCd_ES" ezfhyo="E" ezflist="apvlHrchTpCd_EC,apvlHrchTpDescTxt_ED,99,E,blank" onchange="sendServerForPreferredView('OnChange_HierarchyType')">
																									<option selected>Positional</option>
																									<option>Employee</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:148px" name="apvlTeamPosnTpCd_ES" ezfname="apvlTeamPosnTpCd_ES" ezfhyo="E" ezflist="apvlTeamPosnTpCd_EC,apvlTeamPosnTpDescTxt_ED,99,E,blank">
																									<option selected>Vice President</option>
																									<option>Senior Director</option>
																									<option>Director</option>
																									<option>Seniror Manager</option>
																									<option>Manager</option>
																									<option>Supervisor</option>
																								</select>
																							</td>
																							<td>
																								<input type="button" class="pBtn2" name="OpenWin_Member" ezfHyo="E" value="..." onclick="sendServer('OpenWin_Member')">
																								<input type="text" class="pHsu" size="10" maxlength="8" value="A0123456" name="psnCd_E1" ezfname="psnCd_E1" ezfhyo="E" ezftoupper>
																								<input type="text" class="pPro" size="26" readonly  maxlength="62" value="Mike Hirsh" name="fullPsnNm_E1" ezfname="fullPsnNm_E1" ezfhyo="E">
																							</td>
																							<td>
																								<select class="pHsu" style="width:98px" name="prchGrpCd_ES" ezfname="prchGrpCd_ES" ezfhyo="E" ezflist="prchGrpCd_EC,prchGrpDescTxt_ED,99,E,blank">
																									<option selected>ESS</option>
																									<option>LFS</option>
																									<option>PPS</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:178px" name="apvlHistSrcTpCd_ES" ezfname="apvlHistSrcTpCd_ES" ezfhyo="E" ezflist="apvlHistSrcTpCd_EC,apvlHistSrcTpDescTxt_ED,99,E,blank">
																									<option selected>Purchase Order</option>
																									<option>Parts Request</option>
																									<option>Inventory Request</option>
																									<option>Purchase Requisition</option>
																								</select>
																							</td>
																							<td>
																								<select class="pHsu" style="width:118px" name="prchReqTpCd_ES" ezfname="prchReqTpCd_ES" ezfhyo="E" ezflist="prchReqTpCd_EC,prchReqTpNm_ED,99,E,blank">
																									<option selected>Standard</option>
																									<option>Emergency</option>
																									<option>ITT Inbound</option>
																									<option>WH Transfer</option>
																									<option>Disposal</option>
																									<option>Vendor Return</option>
																									<option>Refurbishing</option>
																									<option>Expense Order</option>
																								</select>
																							</td>
																							<td><input type="text" class="pHsu" size="15" style="text-align:right;" value="500,000.00" name="apvlLimitAmt_E1" ezfname="apvlLimitAmt_E1" ezfhyo="E"></td>
																							<input type="hidden" value="Xxxx" name="apvlLimitPk_E1" ezfname="apvlLimitPk_E1" ezfhyo="E">
																						</tr>
																						
																					</ezf:skip>
		                                                                            </table>
																				</div>
																			</div>
																		</div> <!-- right table -->
																	</div> <!-- parent box -->
																</td>
															</tr>
														</table>
														<%-- ########## LOWER BUTTON AREA ########## --%>
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:0px">
															<col width="750px" align="center"><%-- ===== space ===== --%>
															<col width="82px"  align="center"><%-- Copy Row Button --%>
															<col width="82px"  align="center"><%-- Insert Row Button --%>
															<col width="82px"  align="center"><%-- Delete Row Button --%>
															<tr>
																<td></td>
																<td><ezf:inputButton name="CopyRow" value="Copy Row" htmlClass="pBtn7" /></td>
																<td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" /></td>
																<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" /></td>
															</tr>
														</table>
													</center>
												</div>
											</c:when>
											<%-- #################### ApvlLimit TAB [END] #################### --%>
											
											<%-- L3 Sol#303 Add Tech Thrhd --%>
											<%-- #################### Tech_Thrhd TAB [START] #################### --%>
											<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TechThrhd'}">
												<div id="TabContent-TechThrhd">
													<script type="text/javascript">
														document.getElementById("Team").className = "pTab_OFF";
														document.getElementById("Member").className = "pTab_OFF";
														document.getElementById("Transaction").className = "pTab_OFF";
														document.getElementById("Location").className = "pTab_OFF";
														document.getElementById("ApvlLimit").className = "pTab_OFF";
														document.getElementById("TechThrhd").className = "pTab_ON";
														document.getElementById("TechMin").className = "pTab_OFF";
													</script>
													<%-- ########## SEARCH HEADER ########## --%>
													<center>
														<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
							 							<table style="margin-left: 3px;" >
															<colgroup>
																<col width="200">
																<col width="670">
																<col width="40">
															</colgroup>
															<tbody>
																<tr>
																	<td>
									                                    <table cellSpacing="0" cellPadding="1" border="0" style="margin-left: 65px;">
									                                        <colgroup>
									                                            <col width="123">
									                                            <col width="">
									                                        </colgroup>
									                                        <tbody>
									                                            <tr>
									                                                <td>
									                                                    <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxtK\""/>
									                                                </td>
									                                            </tr>
									                                        </tbody>
									                                    </table>
									                                </td>
								                                	<td>
																	<ezf:skip>
																	<table border="0" cellpadding="0" width="">
																		<col width="250"  align="center">
																		<col width="350"  align="center">
																		<col width="70"  align="center">
																		<tr>
																			<td align="left">
																				<table border="0" cellpadding="0" align="left" cellspacing="0">
																					<col>
																						<tr>
																						<td>Results 1000 - 1000 of 1000</td>
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
																						<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																						<td class="stab">/</td>
																						<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																						<td class="stab">page</td>
																						<td>&nbsp;</td>
																						<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'K')"></td>
																						<td></td>
																						<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'K')"></td>
																						<td></td>
																						<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'K')"></td>
																					</tr>
																				</table>
																			</td>
																			<td></td>
																		</tr>
																	</table>
																	</ezf:skip>
																		<table width="100%">
																			<tr align="right">
																				<td>
																					<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																						<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
																						<jsp:param name="TableNm"           value="K" />
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
																	<td></td>
								                            	</tr>
								                        	</tbody>
														</table>
								                        <%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
														<%-- ########## SEARCH RESULT ########## --%>
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top:0px; margin-left:0px">
															<tr>
																<td valign="top">
																	<div id="parentBoxK">
																		<div style="float:left; display:block"> <!-- left table -->
																			<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
																			<div id="leftTbl" style="float:left; display:block; height:390px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
																		</div>  <!-- left table -->
																		<div style="float:left"> <!-- right table -->
																			<div id='rightTblHead' style="width:750px; height:395px; display:block; overflow:hidden; margin:0px;padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="KHEAD" width="733px">
																					<col width="340px" align="center"><%-- Business Unit --%>
																					<col width="380px"  align="center"><%-- $Threshold Amount --%>
																					<tr height="28px">
																						<td id="KH1" class="pClothBs"><u>Business Unit</u></td>
																						<td id="KH2" class="pClothBs"><u>$Threshold Amount</u></td>
																					</tr>
																				</table>
																				<%-- ******************************** --%>
		                                                                        <%-- *** Right Table Area(Detail) *** --%>
		                                                                        <%-- ******************************** --%>
		                                                                        <div  id="rightTblDtl" style="width:750px; height:383px; display:block; overflow:scroll; margin:0px; padding:0px;" >
			                                                                    	<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="K" width="733px" >
																						<col width="340px" align="center"><%-- Team Name --%>
																						<col width="380px"  align="center"><%-- Warehouse --%>
																						<ezf:row ezfHyo="K">
																						<%-- ----- line 1 ----- --%>
																						<tr height="28px">
																							<td>
																								<ezf:inputText name="lineBizTpDescTxt_K1" ezfName="lineBizTpDescTxt_K1" value="PPS" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"50\""/>
																							</td>
																							<td>
																								<ezf:inputText name="techApvlThrhdAmt_K1" ezfName="techApvlThrhdAmt_K1" value="2000.00" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"45\" maxlength=\"50\" style=\"text-align:right;\""/>
																							</td>
																						</tr>
																						</ezf:row>
																						<ezf:skip>
																						<tr height="28px">
																							<td>
																								<input type="text" class="pPro" readonly size="40" maxlength="50" value="ESS" >
																							</td>
																							<td>
																								<input type="text" size="45" maxlength="50" value="2000.00">
																							</td>
																						</tr>
																						</ezf:skip>	
		                                                                            </table>
																				</div>
																			</div>
																		</div> <!-- right table -->
																	</div> <!-- parent box -->
																</td>
															</tr>
														</table>
														<%-- ########## LOWER BUTTON AREA ########## --%>
													</center>
												</div>
											</c:when>
											<%-- #################### Tech_Thrhd TAB [END] #################### --%>

											<%-- START 2023/05/16 T.Kuroda [QC#61211, ADD] --%>
											<%-- #################### Tech_Min TAB [START] #################### --%>
											<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TechMin'}">
												<div id="TabContent-TechMin">
													<script type="text/javascript">
														document.getElementById("Team").className = "pTab_OFF";
														document.getElementById("Member").className = "pTab_OFF";
														document.getElementById("Transaction").className = "pTab_OFF";
														document.getElementById("Location").className = "pTab_OFF";
														document.getElementById("ApvlLimit").className = "pTab_OFF";
														document.getElementById("TechThrhd").className = "pTab_OFF";
														document.getElementById("TechMin").className = "pTab_ON";
													</script>
													<%-- ########## SEARCH HEADER ########## --%>
													<center>
														<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
														<table style="margin-left: 3px;" >
															<colgroup>
																<col width="200">
																<col width="670">
																<col width="40">
															</colgroup>
															<tbody>
																<tr>
																	<td>
																		<table cellSpacing="0" cellPadding="1" border="0" style="margin-left: 65px;">
																			<colgroup>
																				<col width="123">
																				<col width="">
																			</colgroup>
																			<tbody>
																				<tr>
																					<td>
																						<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxtL\""/>
																					</td>
																				</tr>
																			</tbody>
																		</table>
																	</td>
																	<td>
																	<ezf:skip>
																		<table border="0" cellpadding="0" width="">
																			<col width="250"  align="center">
																			<col width="350"  align="center">
																			<col width="70"  align="center">
																			<tr>
																				<td align="left">
																					<table border="0" cellpadding="0" align="left" cellspacing="0">
																						<col>
																							<tr>
																								<td>Results 1000 - 1000 of 1000</td>
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
																							<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																							<td class="stab">/</td>
																							<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																							<td class="stab">page</td>
																							<td>&nbsp;</td>
																							<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'L')"></td>
																							<td></td>
																							<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'L')"></td>
																							<td></td>
																							<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'L')"></td>
																						</tr>
																					</table>
																				</td>
																				<td></td>
																			</tr>
																		</table>
																	</ezf:skip>
																		<table width="100%">
																			<tr align="right">
																				<td>
																					<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																						<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
																						<jsp:param name="TableNm"           value="L" />
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
																	<td></td>
																</tr>
															</tbody>
														</table>
														<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
														<%-- ########## SEARCH RESULT ########## --%>
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top:0px; margin-left:0px">
															<tr>
																<td valign="top">
																	<div id="parentBoxL">
																		<div style="float:left; display:block"> <!-- left table -->
																			<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
																			<div id="leftTbl" style="float:left; display:block; height:390px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
																		</div> <!-- left table -->
																		<div style="float:left"> <!-- right table -->
																			<div id='rightTblHead' style="width:750px; height:395px; display:block; overflow:hidden; margin:0px;padding:0px;">
																				<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="LHEAD" width="733px">
																					<col width="340px" align="center"><%-- Business Unit --%>
																					<col width="380px"  align="center"><%-- $Premium Rush Minimum --%>
																					<tr height="28px">
																						<td id="LH1" class="pClothBs"><u>Business Unit</u></td>
																						<td id="LH2" class="pClothBs"><u>$Premium Rush Minimum</u></td>
																					</tr>
																				</table>
																				<%-- ******************************** --%>
																				<%-- *** Right Table Area(Detail) *** --%>
																				<%-- ******************************** --%>
																				<div  id="rightTblDtl" style="width:750px; height:383px; display:block; overflow:scroll; margin:0px; padding:0px;" >
																					<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="L" width="733px" >
																						<col width="340px" align="center"><%-- Business Unit --%>
																						<col width="380px"  align="center"><%-- $Premium Rush Minimum --%>
																						<ezf:row ezfHyo="L">
																						<%-- ----- line 1 ----- --%>
																						<tr height="28px">
																							<td>
																								<ezf:inputText name="lineBizTpDescTxt_L1" ezfName="lineBizTpDescTxt_L1" value="PPS" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"50\""/>
																							</td>
																							<td>
																								<ezf:inputText name="techApvlMinAmt_L1" ezfName="techApvlMinAmt_L1" value="2000.00" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"45\" maxlength=\"50\" style=\"text-align:right;\""/>
																							</td>
																						</tr>
																						</ezf:row>
																						<ezf:skip>
																						<tr height="28px">
																							<td>
																								<input type="text" class="pPro" readonly size="40" maxlength="50" value="ESS" >
																							</td>
																							<td>
																								<input type="text" size="45" maxlength="50" value="2000.00">
																							</td>
																						</tr>
																						</ezf:skip>	
																					</table>
																				</div>
																			</div>
																		</div> <!-- right table -->
																	</div> <!-- parent box -->
																</td>
															</tr>
														</table>
														<%-- ########## LOWER BUTTON AREA ########## --%>
													</center>
												</div>
											</c:when>
											<%-- #################### Tech_Min TAB [END] #################### --%>
											<%-- END   2023/05/16 T.Kuroda [QC#61211, ADD] --%>
										</c:choose>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
