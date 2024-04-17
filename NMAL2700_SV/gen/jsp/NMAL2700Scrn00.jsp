<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190118130330 --%>
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
			<input type="hidden" name="pageID" value="NMAL2700Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Role Maintenance">

			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<%-- ###TAB - HEAD --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Role Maintenance" class="pTab_ON"><a href="#">Rle Mnt</a></li>
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
							<div class="pTab_BODY_In">
					
					<table border="0" cellpadding="0" cellspacing="0" height="100">
						<tr>
							<td valign="top">
                            </td>
							<td valign="top" width="">
								<table width="" cellpadding="0" border="0" >
									<col align="left" width="5">
									<col align="left" width="100">
									<col align="left" width="400">
									<col align="left" width="60">
									<col align="left" width="40">
									<col align="left" width="80">
									<col align="left" width="30">
									<col align="left" width="210">
									
									
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Buisness Area</td>
										<td>
											<ezf:select name="firstOrgCd" ezfName="firstOrgCd" ezfBlank="1" ezfCodeName="firstOrgCd_P" ezfDispName="bizAreaOrgNm_P" otherAttr=" style=\"width:139px\""/>
										</td>
										<td class="stab">Manager </td>
										<td><ezf:inputCheckBox name="mgrFlg" ezfName="mgrFlg" value="Y" /></td>
										<td class="stab">Commisionable</td>
										<td><ezf:inputCheckBox name="cmsnFlg" ezfName="cmsnFlg" value="Y" /></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Role Code(*)</td>
										<td>
											<ezf:inputText name="orgFuncRoleTpCd" ezfName="orgFuncRoleTpCd" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
										<td class="stab">Specialist </td>
										<td class="stab">
										    <ezf:inputCheckBox name="spclFlg" ezfName="spclFlg" value="Y" />
										</td>
										<td class="stab">
										    Display Inactive
										</td>
										<td><ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="N" /></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Role Name(*)</td>
										<td><ezf:inputText name="orgFuncRoleTpNm" ezfName="orgFuncRoleTpNm" value="12345678901234567892123456789312345678941234567890" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
										<td class="stab">Equipment </td>
										<td class="stab">
										    <ezf:inputCheckBox name="equipFlg" ezfName="equipFlg" value="Y" />
										</td>
										<td>
										    &nbsp
										</td>
										<td>&nbsp</td>
										<td><ezf:inputButton name="InsertRow_RoleMnt" value="Insert Row" htmlClass="pBtn6" />
										    <ezf:inputButton name="DeleteRow_RoleMnt" value="Delete Row" htmlClass="pBtn6" />
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="stab">Role Description(*)</td>
										<td><ezf:inputText name="orgFuncRoleTpDescTxt" ezfName="orgFuncRoleTpDescTxt" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" otherAttr=" size=\"50\" maxlength=\"50\""/>
										    
										</td>
										<td colspan="2">
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
											
										</td>
										<td>&nbsp
										</td>
										<td>&nbsp</td>
										<td>&nbsp;</td>
								    <tr>
								</table>
								<table cellpadding="1" border="0" align="right">
									<col width="150">
									<col width="150">
									<col width="150">
									<col width="30">
									<col width="100">
									
									<tr>
										<td align="center">
											&nbsp
										</td>
										<td align="center">
											&nbsp
										</td>
										<td  colspan="2">
											&nbsp
										</td>
										<td>
											&nbsp
										</td>
									</tr>
									<tr>
										<td align="center">
											&nbsp
										</td>
										<td align="center">
											&nbsp
										</td>
										<td  colspan="2">
											&nbsp
										</td>
										<td>
											&nbsp
										</td>
									</tr>
								</table>

							</td>
						</tr>
					</table>
								<hr style="height: 0px;" cellpadding="0">

<%--------------------------------%>
<%-- Detail						--%>
<%--------------------------------%>

								<table>
									<tr>
										<td align="top" width="1130">
											<%-- ### MEISAI - LEFT TBL - TOP --%>
											<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none; width:740; height:51; float:left;word-break: break-all;">
												<table border="1" cellpadding="1" cellspacing="0" width="740" height="51" style="table-layout: fixed;">
													<col align="center" width="20">
													<col align="center" width="105">
													<col align="center" width="50">
													<col align="center" width="160">
													<col align="center" width="160">
													<col align="center" width="40">
													
													<tr>
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'firstOrgCd_A' )">Business Area<img id="sortIMG.firstOrgCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'orgFuncRoleTpCd_A' )">Role Code<img id="sortIMG.orgFuncRoleTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'orgFuncRoleTpNm_A' )">Role Name<img id="sortIMG.orgFuncRoleTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'orgFuncRoleTpDescTxt_A' )">Role <br>Description<img id="sortIMG.orgFuncRoleTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'actvFlg_A' )">Active<img id="sortIMG.actvFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - Right TBL - TOP --%>
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:365; height:51; float:left;word-break: break-all;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="1000" height="51" style="table-layout: fixed;">
												    <col align="center" width="70">
													<col align="center" width="70">
													<col align="center" width="70">
													<col align="center" width="100">
													<col align="center" width="60">
													<col align="center" width="90">
													<col align="center" width="90">
													<col align="center" width="70">
													<col align="center" width="80">
													<col align="center" width="80">
													<col align="center" width="80">
													<col align="center" width="85">
													<col align="center" width="110">
													<tr>
													    <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'equipFlg_A' )">Equipment<img id="sortIMG.equipFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mgrFlg_A' )">Manager<img id="sortIMG.mgrFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'spclFlg_A' )">Specialist<img id="sortIMG.spclFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cmsnFlg_A' )">Commisionable<img id="sortIMG.cmsnFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'adminFlg_A' )">Admin<img id="sortIMG.adminFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'gesTpCd_A' )">Golden Eagle<br>Specialist<img id="sortIMG.gesTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'apvlLimitAmt_A' )">Approval<br>Limit<img id="sortIMG.apvlLimitAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'slsRepFlg_A' )">Sales Rep<img id="sortIMG.slsRepFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'asgContrFlg_A' )">Assignment<br>Contract<img id="sortIMG.asgContrFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'thirdPtyTechFlg_A' )">Third Party<br>Tech<img id="sortIMG.thirdPtyTechFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'techMstrReqFlg_A' )">Tech Master <br> Request<img id="sortIMG.techMstrReqFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'crmSlsExclFlg_A' )">SFDC Exclude<img id="sortIMG.crmSlsExclFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'crmSlsPrflTpCd_A' )">SFDC Profile Code<img id="sortIMG.crmSlsPrflTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - LEFT TBL - BOTTOM --%>
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:361; width:740; float:left;word-break: break-all;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL' ))" >
												<table border="1" cellpadding="1" cellspacing="0" width="740" id="A1" style="table-layout: fixed;">
													<col align="center" width="20">
													<col align="center" width="105">
													<col align="center" width="50">
													<col align="center" width="160">
													<col align="center" width="160">
													<col align="center" width="40">
													
													<ezf:row ezfHyo="A">
														<tr height="28" id="A_leftTBLRow#{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
															<td>
																<ezf:select name="firstOrgCd_A" ezfName="firstOrgCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="firstOrgCd_P" ezfDispName="bizAreaOrgNm_P" otherAttr=" style=\"width:139px\""/>
															</td>
															<td>
																<ezf:inputText name="orgFuncRoleTpCd_A" ezfName="orgFuncRoleTpCd_A" value="12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
															</td>
															
															<td>
															    <ezf:inputText name="orgFuncRoleTpNm_A" ezfName="orgFuncRoleTpNm_A" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"50\""/>
															</td>
															<td>
															    <ezf:inputText name="orgFuncRoleTpDescTxt_A" ezfName="orgFuncRoleTpDescTxt_A" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"50\""/>
															</td>
															<td>
															    <ezf:inputCheckBox name="actvFlg_A" ezfName="actvFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
															</td>
															
														</tr>
													</ezf:row>
													<ezf:skip>
													<tr height="28">
															<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A" ezfHyo="A" value="Y"></td>
															<td>
																<select style="width:139px" name="firstOrgCd_A" ezfname="firstOrgCd_A" ezflist="firstOrgCd_P,bizAreaOrgNm_P,99, ,blank" ezfHyo="A">
																	<option>12345678 </option>
																	<option>WWWWWWWW</option>
																	<option>Packed</option>
																</select>
															</td>
															<td>
																<input type="text" size="8" maxlength="8" value="12345678" name="orgFuncRoleTpCd_A" ezfname="orgFuncRoleTpCd_A"  ezfHyo="A" >
															</td>
															
															<td>
															    <input type="text" size="30" maxlength="50" value="12345678901234567890123456789012345678901234567890" name="orgFuncRoleTpNm_A" ezfname="orgFuncRoleTpNm_A"  ezfHyo="A" >
															</td>
															<td>
															    <input type="text" size="30" maxlength="50" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" name="orgFuncRoleTpDescTxt_A" ezfname="orgFuncRoleTpDescTxt_A"  ezfHyo="A" >
															</td>
															<td>
															    <input type="checkbox" name="actvFlg_A" ezfname="actvFlg_A" ezfHyo="A" value="Y">
															</td>
															
														</tr>
													</ezf:skip>
												</table>
											</div>
											<%-- ### MEISAI - RIGHT TBL - BOTTOM --%>
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:378; width:382; float:left;word-break: break-all;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="1000" id="A2" style="table-layout: fixed;">
												    <col align="center" width="70">
													<col align="center" width="70"><!--Manager -->
													<col align="center" width="70"><!--Specialist -->
													<col align="center" width="100"><!--Commisionable -->
													<col align="center" width="60"><!--Admin -->
													<col align="center" width="90"><!--Golden Eagle Specialis -->
													<col align="center" width="90"><!--Credit/Rebill Approval Limit -->
													<col align="center" width="70"><!--Sales Rep -->
													<col align="center" width="80"><!--Assignment Contract -->
													<col align="center" width="80"><!--Third Party Tech -->
													<col align="center" width="80"><!--Tech Master  Request -->
													<col align="center" width="85"><!--SFDC Exclude -->
													<col align="right"  width="110"><!--SFDC Profile Code -->
													
													<ezf:row ezfHyo="A">
														<tr height="28" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
														    <td>
															    <ezf:inputCheckBox name="equipFlg_A" ezfName="equipFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>
																<ezf:inputCheckBox name="mgrFlg_A" ezfName="mgrFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>
																
																<ezf:inputCheckBox name="spclFlg_A" ezfName="spclFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
																
															</td>
															<td>
																
																<ezf:inputCheckBox name="cmsnFlg_A" ezfName="cmsnFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
																
															
															</td>
															<td>
																<ezf:inputCheckBox name="adminFlg_A" ezfName="adminFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>
																<ezf:select name="gesTpCd_A" ezfName="gesTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="gesTpCd_P" ezfDispName="gesTpDescTxt_P" otherAttr=" style=\"width:70px\""/>
															
															</td>
															<td>
																<ezf:inputText name="apvlLimitAmt_A" ezfName="apvlLimitAmt_A" value="123,456.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"22\""/>
															</td>
															<td>
																<ezf:inputCheckBox name="slsRepFlg_A" ezfName="slsRepFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>
																<ezf:inputCheckBox name="asgContrFlg_A" ezfName="asgContrFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>
																<ezf:inputCheckBox name="thirdPtyTechFlg_A" ezfName="thirdPtyTechFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>
																<ezf:inputCheckBox name="techMstrReqFlg_A" ezfName="techMstrReqFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>
																<ezf:inputCheckBox name="crmSlsExclFlg_A" ezfName="crmSlsExclFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" />
															</td>
															<td>
																<ezf:label name="crmSlsPrflNm_A" ezfName="crmSlsPrflNm_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:anchor name="crmSlsPrflNm_LK" ezfName="crmSlsPrflNm_LK" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SFDCProfile" otherAttr=" ezfanchortext=\"\" id=\"crmSlsPrflNm_LK#{EZF_ROW_NUMBER}\"">
																	<label>P</label>
																</ezf:anchor>
															</td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" />
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
													<tr height="28">
													        <td>
															    <input type="checkbox" name="equipFlg_A" ezfname="equipFlg_A" ezfHyo="A" value="Y">
															</td>
															<td>
																<input type="checkbox" name="mgrFlg_A" ezfname="mgrFlg_A" ezfHyo="A" value="Y">
															</td>
															<td>
																
																<input type="checkbox" name="spclFlg_A" ezfname="spclFlg_A" ezfHyo="A" value="Y">
																
															</td>
															<td>
																
																<input type="checkbox" name="cmsnFlg_A" ezfname="cmsnFlg_A" ezfHyo="A" value="Y">
																
															
															</td>
															<td>
																<input type="checkbox" name="adminFlg_A" ezfname="adminFlg_A" ezfHyo="A" value="Y">
															</td>
															<td>
																<select style="width:70px" name="gesTpCd_A" ezfname="gesTpCd_A" ezflist="gesTpCd_P,gesTpDescTxt_P,99, ,blank" ezfHyo="A">
																	<option>12345 </option>
																	<option>WWWWW</option>
																	<option>Packed</option>
																</select>
															</td>
															<td>
																<input type="text" size="10" maxlength="22" value="1" name="apvlLimitAmt_A" ezfname="apvlLimitAmt_A"  ezfHyo="A" >
															</td>
															<td>
																<input type="checkbox" name="slsRepFlg_A" ezfname="slsRepFlg_A" ezfHyo="A" value="Y">
															</td>
															<td>
																<input type="checkbox" name="asgContrFlg_A" ezfname="asgContrFlg_A" ezfHyo="A" value="Y">
															</td>
															<td>
																<input type="checkbox" name="thirdPtyTechFlg_A" ezfname="thirdPtyTechFlg_A" ezfHyo="A" value="Y">
															</td>
															<td>
																<input type="checkbox" name="techMstrReqFlg_A" ezfname="techMstrReqFlg_A" ezfHyo="A" value="Y">
															</td>
															<td>
																<input type="checkbox" name="crmSlsExclFlg_A" ezfname="crmSlsExclFlg_A" ezfHyo="A" value="Y">
															</td>
															<td>
																<label ezfout name="crmSlsPrflNm_A" ezfName="crmSlsPrflNm_A" ezfHyo="A">1234567890</label>
																<a href="#" name="crmSlsPrflNm_LK" ezfName="crmSlsPrflNm_LK" ezfHyo="A" ezfanchortext onclick="sendServer('OpenWin_SFDCProfile')" id="crmSlsPrflNm_LK#{EZF_ROW_NUMBER}">
																	<label>P</label>
																</a>
															</td>
														</tr>
													</ezf:skip>
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
