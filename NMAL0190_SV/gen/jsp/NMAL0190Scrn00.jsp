<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20201215161940 --%>
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
			<input type="hidden" name="pageID" value="NMAL0190Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supersession Staging Info Entry">
			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
<%-- ###################### HEAD ###################### --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Personal Calendar Maintenance" class="pTab_ON"><a href="#">Tech Tng Hist</a></li>
												</div>
											</td>
											<td valign="bottom" align="center">
												<a href="#">
													<img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0">
												</a>
											</td>
											<td valign="bottom" align="center">
												<a href="#">
													<img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0">
												</a>
											</td>
										</tr>
									</table>
								</ul>
							</div>
						</ezf:skip>
<%-- ###################### BODY ###################### --%>
							<div class="pTab_BODY">
								<P>
										&nbsp;
								</P>

<%-- ###################### Detail ###################### --%>
								<table align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td align="top">
											<%-- ### Detail - LEFT TBL - TOP --%>
											<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none;">
												<table border="1" cellpadding="1" cellspacing="0" height="34" style="table-layout:fixed;">
													<col align="center" width="125" />
													<col align="center" width="125" />
													<!-- DELETE START 2016/03/02 QC#2669
													<col align="center" width="50"  />
													DELETE END 2016/03/02 QC#2669 -->
													<col align="center" width="125" />
													<col align="center" width="125" />
													<col align="center" width="110" />
													<col align="center" width="80" />
													<tr>
														<td class="pClothBs" >Item Number</td>
														<td class="pClothBs" >Item Description</td>
														<!-- DELETE START 2016/03/02 QC#2669
														<td class="pClothBs" >Forward</td>
														DELETE END 2016/03/02 QC#2669 -->
														<td class="pClothBs" >Supersedes Item</td>
														<td class="pClothBs" >Supersedes Item<br />Description</td>
														<td class="pClothBs" >Supersede Item Status</td>
														<td class="pClothBs" >Date Staged</td>
													</tr>
												</table>
											</div>
										</td>
										<td align="top">
											<%-- ### Detail - Right TBL - TOP --%>
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; height:34;" >
												<table border="1" cellpadding="1" cellspacing="0" height="34"  style="table-layout:fixed;">
													<col align="center" width="125" />
													<col align="center" width="50"  />
													<col align="center" width="65"  />
													<col align="center" width="80" />
													<col align="center" width="80" />
													<tr>
														<td class="pClothBs" rowspan="2">Compatible With</td>
														<td class="pClothBs" colspan="2">Set Relationship</td>
														<td class="pClothBs" rowspan="2">Last Update<br />By</td>
														<td class="pClothBs" rowspan="2">Last Update<br />Date</td>
													</tr>
													<tr>
														<td class="pClothBs">Forward</td>
														<td class="pClothBs">Backward</td>
													</tr>
												</table>
											</div>
										</td>
									</tr>
									<tr>
										<td align="top">
											<%-- ### Detail - LEFT TBL - BOTTOM --%>
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:400;" >
												<table border="1" cellpadding="1" cellspacing="0" id="A1" style="table-layout:fixed;">
													<col align="center" width="125" />
													<col align="center" width="125" />
													<!-- DELETE START 2016/03/02 QC#2669
													<col align="center" width="50"  />
													DELETE END 2016/03/02 QC#2669 -->
													<col align="center" width="125" />
													<col align="center" width="125" />
													<col align="center" width="110"  />
													<col align="center" width="80" />
													<ezf:row ezfHyo="A">
														<tr id="A_leftTBLRow#{EZF_ROW_NUMBER}">
															<td>
																<!-- Item Number -->
																<ezf:inputText name="supdToMdseCd" ezfName="supdToMdseCd" value="Item Number" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"supdToMdseCd\" size=\"16\" maxlength=\"16\" tabindex=\"-1\""/>
															</td>
															<td>
																<!-- Item Description -->
																<ezf:inputText name="mdseDescShortTxt_TO" ezfName="mdseDescShortTxt_TO" value="Item Description" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mdseDescShortTxt\" size=\"16\" maxlength=\"16\" tabindex=\"-1\""/>
															</td>
															<!-- DELETE START 2016/03/02 QC#2669
															<td>
																<input type="checkbox" name="xxChkBox" ezfname="xxChkBox" ezfHyo="A" value="Y" />
															</td>
															DELETE END 2016/03/02 QC#2669 -->
															<td>
																<!-- Supersedes Item -->
																<ezf:inputText name="supdFromMdseCd" ezfName="supdFromMdseCd" value="Supersedes Item" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"supdFromMdseCd\" size=\"16\" maxlength=\"16\" tabindex=\"-1\""/>
															</td>
															<td>
																<!-- Supersedes Item Description -->
																<ezf:inputText name="mdseDescShortTxt_FR" ezfName="mdseDescShortTxt_FR" value="Item Description" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mdseNm\" size=\"16\" maxlength=\"16\" tabindex=\"-1\""/>
															</td>
															<td>
																<!-- Supersedes Item Status -->
																<!--<input type="text" id="mdseItemStsNm" name="mdseItemStsNm" ezfname="mdseItemStsNm" value="Y" size="14" maxLength="14" ezfHyo="A"/>-->
																<ezf:select name="mdseItemStsCd_SL" ezfName="mdseItemStsCd_SL" ezfHyo="A" ezfArrayNo="0" ezfCodeName="mdseItemStsCd_PD" ezfDispName="mdseItemStsNm_PD" ezfCodeDispHyo="A" otherAttr=" style=\"width: 105px\""/>
															</td>
															<td>
																<!-- Date Staged -->
																<ezf:inputText name="supdRelnStageDt" ezfName="supdRelnStageDt" value="Date Staged" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"supdRelnStageDt\" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/>
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</table>
											</div>
										</td>
										<td align="top">
											<%-- ### Detail - RIGHT TBL - BOTTOM --%>
											<div id="RightTBL" style="overflow-x:hidden; overflow-y:scroll; height:400; " >
												<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout:fixed;">
													<col align="center" width="125" />
													<col align="center" width="50"  />
													<col align="center" width="65"  />
													<col align="center" width="80" />
													<col align="center" width="80" />
													<ezf:row ezfHyo="B">
														<tr id="A_rightTBLRow#{EZF_ROW_NUMBER}">
															<td>
																<!-- Compatible Width -->
																<ezf:inputText name="relnMdseCd" ezfName="relnMdseCd" value="Compatible Width" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"relnMdseCd\" size=\"16\" maxlength=\"16\" tabindex=\"-1\""/>
															</td>
															<td>
																<!-- Forward  -->
																<ezf:inputCheckBox name="xxSupdFlg_FW" ezfName="xxSupdFlg_FW" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxSupdFlg_FW\""/>
															</td>
															<td>
																<!-- Backward  -->
																<ezf:inputCheckBox name="xxSupdFlg_BW" ezfName="xxSupdFlg_BW" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxSupdFlg_BW\""/>
															</td>
															<td>
																<!-- Last Update By -->
																<ezf:inputText name="xxAuthByNm" ezfName="xxAuthByNm" value="Last Update By" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"ezUpUserID\" size=\"10\" maxlength=\"64\" tabindex=\"-1\""/>
															</td>
															<td>
																<!-- Last Update Date -->
																<ezf:inputText name="ezUpTime_F" ezfName="ezUpTime_F" value="Last Update Date" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"ezUpTime\" size=\"10\" maxlength=\"17\" tabindex=\"-1\""/>
																<!-- Last Update TimeZone -->
																<ezf:inputHidden name="ezUpTimeZone_F" ezfName="ezUpTimeZone_F" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"ezUpTimeZone\""/>
															</td>
															<td class="pAuditInfo">
																<ezf:inputHidden name="xxRecHistCratTs_B1" ezfName="xxRecHistCratTs_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistCratByNm_B1" ezfName="xxRecHistCratByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdTs_B1" ezfName="xxRecHistUpdTs_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistUpdByNm_B1" ezfName="xxRecHistUpdByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																<ezf:inputHidden name="xxRecHistTblNm_B1" ezfName="xxRecHistTblNm_B1" ezfHyo="B" ezfArrayNo="0" />
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

							<ezf:inputHidden name="supdRelnStagePk_P" ezfName="supdRelnStagePk_P" />
							</div> <!-- End of pTab_Body -->
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
