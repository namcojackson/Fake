<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240228103752 --%>
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
			<input type="hidden" name="pageID" value="NSAL0620Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract/Meter Search">
<style type="text/css">
.nsal0620_sm_chk{margin:0px 0px 0px 2px;width:12px;height:12px;}
.nsal0620_sm_txt{margin:0px 0px 0px 2px;border:none;background-color:transparent;}
</style>
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Contract Search" class="pTab_ON"><a href="#">Cont Srch</a></li>
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
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
<%@ page import = "com.canon.cusa.s21.framework.ZYP.common.ZYPConstant" %>
<%@ page import = "business.servlet.NSAL0620.NSAL0620BMsg" %>
<% NSAL0620BMsg bMsg = (NSAL0620BMsg) databean.getEZDBMsg(); %>
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="375" valign="top">
									<col width="375" valign="top">
									<col width="375" valign="top">
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td>
														<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0">
															<tr height="20">
																<td class="pClothBs" style="text-align:center">Contract Details</td>
															</tr>
															<tr>
																<td>
																	<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="116">
<!-- QC28627 Add Start-->
																					<col width="105">
																					<col width="50">
																					<col width="95">
<!-- QC28627 End Start-->
																					<tr height="20">
																						<td class="stab">Contract (*)</td>
																						<td><ezf:inputText name="dsContrNum_H" ezfName="dsContrNum_H" value="10001023" otherAttr=" ezftoupper=\"\" size=\"12\""/></td>
<!-- QC28627 Add Start-->
																						<td class="stab">Link # (*)</td>
																						<td><ezf:inputText name="contrLinkNum_H" ezfName="contrLinkNum_H" value="99001023" otherAttr=" style=\"width:95;\" ezftoupper=\"\" size=\"12\""/></td>
<!-- QC28627 End Start-->
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="116">
																					<col width="105">
																					<col width="20">
																					<col width="110">
																					<tr height="20">
																						<td class="stab">Created Between</td>
																						<td>
																							<ezf:inputText name="dsContrCratDt_H1" ezfName="dsContrCratDt_H1" value="01/01/2014" otherAttr=" size=\"10\""/>
																							<img src="./img/calendar.gif" class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrCratDt_H1', 4);">
																						</td>
																						<td class="stab">to</td>
																						<td>
																							<ezf:inputText name="dsContrCratDt_H2" ezfName="dsContrCratDt_H2" value="01/01/2015" otherAttr=" size=\"10\""/>
																							<img src="./img/calendar.gif" class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('dsContrCratDt_H2', 4);">
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="116">
																					<col width="105">
																					<col width="20">
																					<col width="120">
																					<tr height="20">
<!-- START 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ContType" >Contract Type</ezf:anchor></td>
																						<td><ezf:inputText name="xxComnScrColValTxt_H1" ezfName="xxComnScrColValTxt_H1" otherAttr=" size=\"12\""/></td>
<!--
																						<td class="stab">Contract Type</td>
																						<td><select style="width:100;" name="dsContrCatgCd_H" ezfname="dsContrCatgCd_H" ezflist="dsContrCatgCd_L,dsContrCatgDescTxt_L,99, ,blank">
																								<ezf:skip>
																									<option value="1"></option>
																									<option value="1" selected>Non Fleet</option>
																									<option value="1">Fleet</option>
																									<option value="1">Aggregate</option>
																									<option value="1">Warranty</option>
																								</ezf:skip>
																							</select></td>
-->
<!-- END 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																						<td><ezf:inputCheckBox name="invSeptBaseUsgFlg_H" ezfName="invSeptBaseUsgFlg_H" value="Y" /></td>
																						<td class="stab">Consolidated Contract</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="116">
																					<col width="105">
																					<col width="20">
																					<col width="120">
																					<tr height="20">
<!-- START 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ContStatus" >Status</ezf:anchor></td>
																						<td><ezf:inputText name="xxComnScrColValTxt_H2" ezfName="xxComnScrColValTxt_H2" otherAttr=" size=\"12\""/></td>
<!--
																						<td class="stab">Status</td>
																						<td><select style="width:100;" name="dsContrCtrlStsCd_H" ezfname="dsContrCtrlStsCd_H" ezflist="dsContrCtrlStsCd_L,dsContrCtrlStsNm_L,99, ,blank">
																								<ezf:skip>
																									<option value="1"></option>
																									<option value="1">Entered</option>
																									<option value="1">Bill Hold</option>
																									<option value="1" selected>Active</option>
																								</ezf:skip>
																							</select></td>
-->
<!-- END 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																						<td><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" /></td>
																						<td class="stab">Has Sub-Contract</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="116">
																					<col width="105">
																					<col width="20">
																					<col width="120">
																					<tr>
																						<td class="stab">Report Group # (*)</td>
																						<td><ezf:inputText name="dsContrRptGrpNum_H" ezfName="dsContrRptGrpNum_H" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
																						<td>&nbsp;</td>
<!-- START 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ContCategory" >Contract Category</ezf:anchor></td>
<!--
																						<td class="stab">Contract Category</td>
-->
<!-- END 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="116">
																					<col width="105">
																					<col width="20">
<!-- START 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																					<col width="125">
<!--
																					<col width="110">
-->
<!-- END 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																					<tr>
																						<td class="stab">Source Reference # (*)</td>
																						<td><ezf:inputText name="svcContrRefCmntTxt_H" ezfName="svcContrRefCmntTxt_H" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
																						<td>&nbsp;</td>
<!-- START 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																						<td><ezf:inputText name="xxComnScrColValTxt_H3" ezfName="xxComnScrColValTxt_H3" otherAttr=" size=\"17\""/></td>
<!--
																						<td><select style="width:100;" name="dsContrClsCd_H" ezfname="dsContrClsCd_H" ezflist="dsContrClsCd_L,dsContrClsNm_L,99, ,blank">
																								<ezf:skip>
																									<option value="1"></option>
																									<option value="1">NAD</option>
																									<option value="1">GMD</option>
																								</ezf:skip>
																							</select></td>
-->
<!-- END 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr height="4">
																			<td>&nbsp;</td>
																		</tr>
																		<tr height="88">
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="105" valign="top">
																					<col width="125" valign="top">
																					<col width="140" valign="top">
																					<tr height="110">
																						<td class="stab">Region &amp; Branch</td>
																						<td>
																							<div id="Region" style="width:121; overflow-x:hidden; overflow-y:scroll; height:78;">
																								<table id="B" border="1" cellpadding="1" cellspacing="0" align="left">
																									<col width="22" align="left">
																									<col width="80" align="left">
																									<ezf:row ezfHyo="B">
																										<tr height="12">
																											<td><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" htmlClass="nsal0620_sm_chk" otherAttr=" id=\"xxChkBox_B#{EZF_ROW_NUMBER}\""/></td>
																											<td><ezf:inputText name="svcRgDescTxt_B" ezfName="svcRgDescTxt_B" value="*National1" ezfHyo="B" ezfArrayNo="0" htmlClass="nsal0620_sm_txt" otherAttr=" size=\"10\" id=\"svcRgDescTxt_B#{EZF_ROW_NUMBER}\""/></td>
																										</tr>
																									<ezf:skip>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_B" ezfname="xxChkBox_B"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="10" value="*National2" id="svcRgDescTxt_B#{EZF_ROW_NUMBER}" name="svcRgDescTxt_B" ezfname="svcRgDescTxt_B" ezfhyo="B"></td>
																										</tr>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_B" ezfname="xxChkBox_B"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="10" value="*TieredAccts" id="svcRgDescTxt_B#{EZF_ROW_NUMBER}" name="svcRgDescTxt_B" ezfname="svcRgDescTxt_B" ezfhyo="B"></td>
																										</tr>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_B" ezfname="xxChkBox_B"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="10" value="1-NorthEast" id="svcRgDescTxt_B#{EZF_ROW_NUMBER}" name="svcRgDescTxt_B" ezfname="svcRgDescTxt_B" ezfhyo="B"></td>
																										</tr>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_B" ezfname="xxChkBox_B"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="10" value="2-NorthEast" id="svcRgDescTxt_B#{EZF_ROW_NUMBER}" name="svcRgDescTxt_B" ezfname="svcRgDescTxt_B" ezfhyo="B"></td>
																										</tr>
																									</ezf:skip>
																									</ezf:row>
																								</table>
																							</div>
																						</td>
																						<td>
																							<div id="Branch" style="width:141; overflow-x:hidden; overflow-y:scroll; height:78;">
																								<table id="C" border="1" cellpadding="1" cellspacing="0" align="left">
																									<col width="22" align="left">
																									<col width="100" align="left">
																									<ezf:row ezfHyo="C">
																										<tr height="12">
																											<td><ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" ezfHyo="C" ezfArrayNo="0" htmlClass="nsal0620_sm_chk" otherAttr=" id=\"xxChkBox_C#{EZF_ROW_NUMBER}\""/></td>
																											<td><ezf:inputText name="svcContrBrDescTxt_C" ezfName="svcContrBrDescTxt_C" value="020 - CBS E - RENTALS/SUB RENTALS" ezfHyo="C" ezfArrayNo="0" htmlClass="nsal0620_sm_txt" otherAttr=" size=\"12\" id=\"svcContrBrDescTxt_C#{EZF_ROW_NUMBER}\""/></td>
																										</tr>
																									<ezf:skip>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_C" ezfname="xxChkBox_C"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="12" value="021 - CBS E - NAD FIXED TERM" id="svcContrBrDescTxt_C#{EZF_ROW_NUMBER}" name="svcContrBrDescTxt_C" ezfname="svcContrBrDescTxt_C" ezfhyo="C"></td>
																										</tr>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_C" ezfname="xxChkBox_C"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="12" value="022 - CBS C - NAD/FTR" id="svcContrBrDescTxt_C#{EZF_ROW_NUMBER}" name="svcContrBrDescTxt_C" ezfname="svcContrBrDescTxt_C" ezfhyo="C"></td>
																										</tr>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_C" ezfname="xxChkBox_C"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="12" value="023 - CBS C - RENTAL/SUB RENTAL" id="svcContrBrDescTxt_C#{EZF_ROW_NUMBER}" name="svcContrBrDescTxt_C" ezfname="svcContrBrDescTxt_C" ezfhyo="C"></td>
																										</tr>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_C" ezfname="xxChkBox_C"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="12" value="024 - CBS W - RENTAL/SUB RENTAL" id="svcContrBrDescTxt_C#{EZF_ROW_NUMBER}" name="svcContrBrDescTxt_C" ezfname="svcContrBrDescTxt_C" ezfhyo="C"></td>
																										</tr>
																									</ezf:skip>
																									</ezf:row>
																								</table>
																							</div>
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="105">
																					<col width="53">
																					<col width="73">
																					<col width="53">
																					<col width="72">
																					<tr>
																						<td>&nbsp;</td>
																						<td><ezf:inputButton name="SelectAll_Rg" value="Select All" ezfHyo="SelectAll_Rg" ezfArrayNo="0" htmlClass="pBtn4" /></td>
																						<td><ezf:inputButton name="UnSelectAll_Rg" value="Unselect All" ezfHyo="UnSelectAll_Rg" ezfArrayNo="0" htmlClass="pBtn6" /></td>
																						<td><ezf:inputButton name="SelectAll_Br" value="Select All" ezfHyo="SelectAll_Br" ezfArrayNo="0" htmlClass="pBtn4" /></td>
																						<td><ezf:inputButton name="UnSelectAll_Br" value="Unselect All" ezfHyo="UnSelectAll_Br" ezfArrayNo="0" htmlClass="pBtn6" /></td>
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
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td>
														<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0">
															<tr height="20">
																<td class="pClothBs" style="text-align:center">Customer Details</td>
															</tr>
															<tr>
																<td>
																	<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="125">
																					<tr height="20">
																						<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_SrchCust" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchCust" >Customer Name (*)</ezf:anchor></td>
																						<td><ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" value="OMNICARE INC." otherAttr=" size=\"34\" ezftoupper=\"\""/></td>
																					</tr>
																					<tr height="20">
																						<td class="stab">Customer # (*)</td>
																						<td><ezf:inputText name="dsAcctNum_H" ezfName="dsAcctNum_H" value="0010101" otherAttr=" size=\"34\" ezftoupper=\"\""/></td>
																					</tr>
																					<tr height="20">
																						<td class="stab">Bill To Location (*)</td>
																						<td><ezf:inputText name="xxGenlFldAreaTxt_H1" ezfName="xxGenlFldAreaTxt_H1" otherAttr=" size=\"34\" ezftoupper=\"\""/></td>
																					</tr>
																					<tr height="20">
																						<td class="stab">Bill To Code (*)</td>
																						<td><ezf:inputText name="billToCustCd_H1" ezfName="billToCustCd_H1" otherAttr=" size=\"34\" ezftoupper=\"\""/></td>
																					</tr>
																					<tr height="20">
																						<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_ServiceProgram" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ServiceProgram" >Service Program (*)</ezf:anchor></td>
																						<td><ezf:inputText name="mdseDescShortTxt_H" ezfName="mdseDescShortTxt_H" otherAttr=" size=\"34\" ezftoupper=\"\""/></td>
																					</tr>
																					<tr height="20">
																						<td class="stab">Master Account Name</td>
																						<td><ezf:inputText name="dsAcctNm_H3" ezfName="dsAcctNm_H3" otherAttr=" size=\"34\" ezftoupper=\"\""/></td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td>
																	<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																		<tr height="88">
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="45">
																					<col width="130">
																					<col width="45">
																					<col width="150">
																					<tr>
																						<td class="stab">Reading Method</td>
																						<td>
																							<div id="Reading Method" style="width:121; overflow-x:hidden; overflow-y:scroll; height:78;">
																								<table id="D" border="1" cellpadding="1" cellspacing="0" align="left">
																									<col width="22" align="left">
																									<col width="80" align="left">
																									<ezf:row ezfHyo="D">
																										<tr height="10">
																											<td><ezf:inputCheckBox name="xxChkBox_D" ezfName="xxChkBox_D" value="Y" ezfHyo="D" ezfArrayNo="0" htmlClass="nsal0620_sm_chk" otherAttr=" id=\"xxChkBox_D#{EZF_ROW_NUMBER}\""/></td>
																											<td><ezf:inputText name="mtrReadMethNm_D" ezfName="mtrReadMethNm_D" value="Fax" ezfHyo="D" ezfArrayNo="0" htmlClass="nsal0620_sm_txt" otherAttr=" size=\"10\" id=\"mtrReadMethNm_D#{EZF_ROW_NUMBER}\""/></td>
																										</tr>
																									<ezf:skip>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_D" ezfname="xxChkBox_D"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="10" value="Phone" id="mtrReadMethNm_D#{EZF_ROW_NUMBER}" name="mtrReadMethNm_D" ezfname="mtrReadMethNm_D" ezfhyo="D"></td>
																										</tr>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_D" ezfname="xxChkBox_D"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="10" value="Email" id="mtrReadMethNm_D#{EZF_ROW_NUMBER}" name="mtrReadMethNm_D" ezfname="mtrReadMethNm_D" ezfhyo="D"></td>
																										</tr>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_D" ezfname="xxChkBox_D"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="10" value="ROSS - No CBS" id="mtrReadMethNm_D#{EZF_ROW_NUMBER}" name="mtrReadMethNm_D" ezfname="mtrReadMethNm_D" ezfhyo="D"></td>
																										</tr>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_D" ezfname="xxChkBox_D"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="10" value="eManage" id="mtrReadMethNm_D#{EZF_ROW_NUMBER}" name="mtrReadMethNm_D" ezfname="mtrReadMethNm_D" ezfhyo="D"></td>
																										</tr>
																									</ezf:skip>
																									</ezf:row>
																								</table>
																							</div>
																						</td>
																						<td class="stab">Billing Counter</td>
																						<td>
																							<div id="Billing Counter" style="width:146; overflow-x:hidden; overflow-y:scroll; height:78;">
																								<table id="E" border="1" cellpadding="1" cellspacing="0" align="left">
																									<col width="22" align="left">
																									<col width="105" align="left">
																									<ezf:row ezfHyo="E">
																										<tr height="12">
																											<td><ezf:inputCheckBox name="xxChkBox_E" ezfName="xxChkBox_E" value="Y" ezfHyo="E" ezfArrayNo="0" htmlClass="nsal0620_sm_chk" otherAttr=" id=\"xxChkBox_E#{EZF_ROW_NUMBER}\""/></td>
																											<td><ezf:inputText name="mtrLbNm_E" ezfName="mtrLbNm_E" value="SM Billing Counter" ezfHyo="E" ezfArrayNo="0" htmlClass="nsal0620_sm_txt" otherAttr=" size=\"12\" id=\"mtrLbNm_E#{EZF_ROW_NUMBER}\""/></td>
																										</tr>
																									<ezf:skip>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_E" ezfname="xxChkBox_E"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="12" value="BW Billing Counter" id="mtrLbNm_E#{EZF_ROW_NUMBER}" name="mtrLbNm_E" ezfname="mtrLbNm_E" ezfhyo="E"></td>
																										</tr>
																										<tr height="12">
																											<td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_E" ezfname="xxChkBox_E"></td>
																											<td><input type="text" class="nsal0620_sm_txt" size="12" value="CLR Billing Counter" id="mtrLbNm_E#{EZF_ROW_NUMBER}" name="mtrLbNm_E" ezfname="mtrLbNm_E" ezfhyo="E"></td>
																										</tr>
																									</ezf:skip>
																									</ezf:row>
																								</table>
																							</div>
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="45">
																					<col width="53">
																					<col width="73">
																					<col width="50">
																					<col width="53">
																					<col width="73">
																					<tr>
																						<td>&nbsp;</td>
																						<td><ezf:inputButton name="SelectAll_MtrReadMeth" value="Select All" htmlClass="pBtn4" /></td>
																						<td><ezf:inputButton name="UnSelectAll_MtrReadMeth" value="Unselect All" htmlClass="pBtn6" /></td>
																						<td>&nbsp;</td>
																						<td><ezf:inputButton name="SelectAll_BllgMtr" value="Select All" htmlClass="pBtn4" /></td>
																						<td><ezf:inputButton name="UnSelectAll_BllgMtr" value="Unselect All" htmlClass="pBtn6" /></td>
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
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td>
														<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0">
															<tr height="20">
																<td class="pClothBs" style="text-align:center">Machine Details</td>
															</tr>
															<tr>
																<td>
																	<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="120">
																					<col width="95">
																					<col width="65">
																					<col width="95">
																					<tr height="20">
																						<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_SrchSer" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchSer" >Serial #/Tag # (*)</ezf:anchor></td>
																						<td><ezf:inputText name="serNum_H" ezfName="serNum_H" value="HHT0254" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
<!-- START 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_MachStatus" >Status</ezf:anchor></td>
																						<td><ezf:inputText name="xxComnScrColValTxt_H4" ezfName="xxComnScrColValTxt_H4" otherAttr=" size=\"12\""/></td>
<!--
																						<td class="stab">Status</td>
																						<td><select style="width:90;" name="svcMachMstrStsCd_H" ezfname="svcMachMstrStsCd_H" ezflist="svcMachMstrStsCd_L,svcMachMstrStsNm_L,99, ,blank">
																								<ezf:skip>
																									<option value="1"></option>
																									<option value="1" selected>Active</option>
																									<option value="1">Entered</option>
																									<option value="1">Bill Hold</option>
																								</ezf:skip>
																							</select></td>
-->
<!-- END 2022/10/13 M.Komatsu [QC#60078,MOD] -->

																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="120">
																					<col width="95">
																					<col width="65">
																					<col width="95">
																					<tr height="20">
<!-- START 2022/10/13 M.Komatsu [QC#60537,ADD] -->
																						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchItem" >Item # (*)</ezf:anchor></td>
																						<td><ezf:inputText name="mdseCd_H" ezfName="mdseCd_H" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
<!-- END 2022/10/13 M.Komatsu [QC#60537,ADD] -->
																						<td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="OpenWin_SrchMdl" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchMdl" >Model # (*)</ezf:anchor></td>
																						<td><ezf:inputText name="t_MdlNm_H" ezfName="t_MdlNm_H" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
<!-- START 2022/10/13 M.Komatsu [QC#60537,MOD] -->
<!--
																						<td class="stab"><a href="#" onclick="sendServer('OpenWin_SrchConfig')">Config #</a></td>
																						<td><input type="text" size="12" value="" name="svcConfigMstrPk_H" ezfname="svcConfigMstrPk_H" ezftoupper></td>
-->
<!-- END 2022/10/13 M.Komatsu [QC#60537,MOD] -->
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="120">
<!-- START 2022/10/13 M.Komatsu [QC#60537,MOD] -->
																					<col width="145">
																					<col width="43">
																					<col width="70">
<!--
																					<col width="240">
-->
<!-- END 2022/10/13 M.Komatsu [QC#60537,MOD] -->
																					<tr height="20">
																						<td class="stab">Located At Customer (*)</td>
<!-- START 2022/10/13 M.Komatsu [QC#60537,MOD] -->
																						<td><ezf:inputText name="dsAcctNm_H2" ezfName="dsAcctNm_H2" otherAttr=" size=\"19\" ezftoupper=\"\""/></td>
																						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SrchConfig" >Config #</ezf:anchor></td>
																						<td><ezf:inputText name="svcConfigMstrPk_H" ezfName="svcConfigMstrPk_H" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
<!--
																						<td><input type="text" size="33" value="" name="dsAcctNm_H2" ezfname="dsAcctNm_H2" ezftoupper></td>
-->
<!-- END 2022/10/13 M.Komatsu [QC#60537,MOD] -->
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="120">
																					<col width="95">
																					<col width="80">
																					<col width="100">
																					<tr height="20">
																						<td class="stab">Location (*)</td>
																						<td><ezf:inputText name="xxGenlFldAreaTxt_H2" ezfName="xxGenlFldAreaTxt_H2" otherAttr=" size=\"12\" ezftoupper=\"\""/></td>
																						<td class="stab">Ship To Code (*)</td>
																						<td><ezf:inputText name="locNum_H" ezfName="locNum_H" otherAttr=" size=\"10\" ezftoupper=\"\""/></td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td>
																	<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="20">
																					<col width="73">
																					<col width="20">
																					<col width="70">
																					<col width="20">
																					<col width="92">
																					<col width="20">
																					<col width="50">
																					<tr>
																						<td><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="1" /></td>
																						<td class="stab">Schedule Date</td>
																						<td><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="2" /></td>
																						<td class="stab">Interface Date</td>
																						<td><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="3" /></td>
																						<td class="stab">Machine Start Date</td>
																						<td><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="4" /></td>
																						<td class="stab">RMA Date</td>
																					</tr>
																					<tr>
																						<td><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="5" /></td>
																						<td colspan="3" class="stab">Contract Start Date</td>
																						<td><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="6" /></td>
																						<td colspan="3" class="stab">Machine End Date</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="110">
																					<col width="100">
																					<col width="20">
																					<col width="110">
																					<tr>
																						<td class="stab">From</td>
																						<td>
																							<ezf:inputText name="xxFromDt_H" ezfName="xxFromDt_H" value="01/01/2014" otherAttr=" size=\"10\""/>
																							<img src="./img/calendar.gif" class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxFromDt_H', 4);">
																						</td>
																						<td class="stab">to</td>
																						<td>
																							<ezf:inputText name="xxThruDt_H" ezfName="xxThruDt_H" value="01/01/2015" otherAttr=" size=\"10\""/>
																							<img src="./img/calendar.gif" class="pCalendar" style="vertical-align:text-bottom;" onclick="calendar('xxThruDt_H', 4);">
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td>
																	<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="110">
																					<col width="95">
																					<col width="75">
<!-- START 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																					<col width="90">
<!--
																					<col width="80">
-->
<!-- END 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																					<tr>
<!-- START 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_BaseFreq" >Base Freq</ezf:anchor></td>
																						<td><ezf:inputText name="xxComnScrColValTxt_H5" ezfName="xxComnScrColValTxt_H5" otherAttr=" size=\"12\""/></td>
																						<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_OverageFreq" >Overage Freq</ezf:anchor></td>
																						<td><ezf:inputText name="xxComnScrColValTxt_H6" ezfName="xxComnScrColValTxt_H6" otherAttr=" size=\"12\""/></td>
<!--
																						<td class="stab">Base Freq</td>
																						<td><select style="width:90;" name="bllgCycleCd_HB" ezfname="bllgCycleCd_HB" ezflist="bllgCycleCd_LB,bllgCycleNm_LB,99, ,blank">
																								<ezf:skip>
																									<option value="1"></option>
																									<option value="1">Daily</option>
																									<option value="1">Weekly</option>
																									<option value="1">Monthly</option>
																									<option value="1">Quarterly</option>
																									<option value="1" selected>Yearly</option>
																									<option value="1">Contract Period</option>
																								</ezf:skip>
																							</select></td>
																						<td class="stab">Overage Freq</td>
																						<td><select style="width:80;" name="bllgCycleCd_HM" ezfname="bllgCycleCd_HM" ezflist="bllgCycleCd_LM,bllgCycleNm_LM,99, ,blank">
																								<ezf:skip>
																									<option value="1"></option>
																									<option value="1">Daily</option>
																									<option value="1">Weekly</option>
																									<option value="1" selected>Monthly</option>
																									<option value="1">Quarterly</option>
																									<option value="1">Yearly</option>
																									<option value="1">Contract Period</option>
																								</ezf:skip>
																							</select></td>
-->
<!-- END 2022/10/13 M.Komatsu [QC#60078,MOD] -->
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="110">
																					<col width="20">
																					<col width="75">
																					<col width="20">
																					<col width="60">
																					<tr>
																						<td class="stab">Unbilled</td>
																						<td><ezf:inputCheckBox name="baseChrgToLeaseCmpyFlg_H" ezfName="baseChrgToLeaseCmpyFlg_H" value="Y" /></td>
																						<td class="stab">Base</td>
																						<td><ezf:inputCheckBox name="usgChrgToLeaseCmpyFlg_H" ezfName="usgChrgToLeaseCmpyFlg_H" value="Y" /></td>
																						<td class="stab">Overage</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td>
																	<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																		<tr>
																			<td>
																				<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																					<col width="20">
																					<col width="73">
																					<col width="20">
																					<col width="70">
																					<col width="20">
																					<col width="70">
																					<col width="20">
																					<col width="70">
																					<tr>
																						<td><ezf:inputRadio name="xxRadioBtn_H2" ezfName="xxRadioBtn_H2" value="1" /></td>
																						<td class="stab">Null Start Read</td>
																						<td><ezf:inputRadio name="xxRadioBtn_H2" ezfName="xxRadioBtn_H2" value="2" /></td>
																						<td class="stab">RMA'ed</td>
																						<td><ezf:inputRadio name="xxRadioBtn_H2" ezfName="xxRadioBtn_H2" value="3" /></td>
																						<td class="stab">Renewed</td>
																						<td><ezf:inputRadio name="xxRadioBtn_H2" ezfName="xxRadioBtn_H2" value="4" /></td>
																						<td class="stab">None</td>
																					</tr>
																					<tr>
																						<td><ezf:inputRadio name="xxRadioBtn_H2" ezfName="xxRadioBtn_H2" value="5" /></td>
																						<td class="stab">Renewal Error</td>
																						<td><ezf:inputCheckBox name="xxChkBox_H3" ezfName="xxChkBox_H3" value="Y" /></td>
																						<td class="stab">My Team</td>
																						<td><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" /></td>
																						<td class="stab">Only Mytask</td>
																						<td></td>
																						<td></td>
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
							</td>
						</tr>
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="100%" align="right">
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<col width="120">
												<col width="205">
												<col width="100">
												<col width="120">
												<col width="205">
												<col width="100">
												<col width="80">
												<tr>
													<td class="stab">New Search Options</td>
													<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\""/></td>
													<td><ezf:inputButton name="SaveSrchOpt" value="Save" ezfHyo="SaveSrchOpt" ezfArrayNo="0" htmlClass="pBtn6" /></td>
													<td class="stab">Saved Search Options</td>
													<td><ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('ChgSavedSrchOpt')\"" otherAttr=" style=\"width:200;\""/></td>
													<td><ezf:inputButton name="DelSrchOpt" value="Delete" ezfHyo="DelSrchOpt" ezfArrayNo="0" htmlClass="pBtn6" /></td>
													<td><ezf:inputButton name="SrchContr" value="Search" htmlClass="pBtn6" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<hr/>
							</td>
						</tr>
						<tr>
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
							<table border="0" style="table-layout:fixed;width=100%;margin-left:20;">
								<col width="180">
								<col width="400">
								<col width="130">
								<col width="">
								<tr>
									<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
									<td></td>
									<td>
										<table border="0" cellpadding="1" cellspacing="0">
											<col width="54"  align="center">
											<col width="60"  align="right">
											<col width="16"  align="center">
										<tr>
											<td class="stab">Total</td>
											<td class="pOut"><ezf:label name="xxSrchCnt" ezfName="xxSrchCnt" otherAttr=" style=\"width:60;\""/></td>
											<td class="stab"></td>
										</tr>
										</table>
									<!-- Pagination & Navigation--START-->
									<td>
										<ezf:skip>
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="10">
												<col>
												<col width="1">
												<col>
											<tr>
												<td class="stab">Showing</td>
												<td class="pOut">1</td>
												<td class="stab">to</td>
												<td class="pOut">20</td>
												<td class="stab">of</td>
												<td class="pOut">200</td>
												<td>&nbsp;</td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
											</tr>
											</table>
										</ezf:skip>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="A" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											</jsp:include>
									</td>
									<!-- Pagination & Navigation--END-->
								</tr>
							</table>
<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
						</tr>
<!-- ######################################## DETAIL #################################### -->
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"><!-- left table -->
										<div id='leftTblHead' style="display:block;"></div>
										<div id='leftTbl' style="float:left; display:block; height:157px; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px"></div>
									</div><!-- left table -->
									<div style="float:left;"><!-- right table -->
										<div id='rightTblHead' style="width:1105px; display:block; overflow:hidden; margin:0px; padding:0px;">
											<table style="table-layout:fixed; " width="1270" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1299px" style="margin-right:20px">
												<col width="25" align="center"><!-- Select -->
												<col width="40" align="center"><!-- Copy -->
												<col width="80" align="center"><!-- Contract Name -->
												<col width="80" align="center"><!-- Contract Type -->
<!-- QC26424 Add Start-->
												<col width="80" align="center"><!-- Customer # -->
<!-- QC26424 Add End-->
												<col width="150" align="center"><!-- Customer Name -->
<!-- QC26424 Add Start-->
												<col width="80" align="center"><!-- Bill To Code # -->
												<col width="150" align="center"><!-- Bill To Customer Name -->
<!-- QC26424 Add End-->
												<col width="80" align="center"><!-- Start Date -->
												<col width="80" align="center"><!-- End Date -->
												<col width="80" align="center"><!-- Region -->
												<col width="50" align="center"><!-- Branch -->
<!-- QC26424 Add Start-->
												<col width="80" align="center"><!-- Header Status -->
												<col width="80" align="center"><!-- Category -->
												<col width="150" align="center"><!-- Branch Rep -->
												<col width="150" align="center"><!-- Sales Rep -->
												<col width="80" align="center"><!-- Source Ref# -->
												<col width="80" align="center"><!-- Report Group# -->
												<col width="150" align="center"><!-- Description -->
												<col width="150" align="center"><!-- Inv Comments -->
												<col width="250" align="center"><!-- Invoicing Option -->
												<col width="150" align="center"><!-- Allocate Across All Machine -->
												<col width="80" align="center"><!-- Auto Estimation -->
												<col width="150" align="center"><!-- Lease Company -->
												<col width="80" align="center"><!-- Base Lease Flag -->
												<col width="80" align="center"><!-- Usage Lease Flag -->
												<col width="80" align="center"><!-- Lease# -->
												<col width="80" align="center"><!-- PO# -->
												<col width="80" align="center"><!-- PO Expiration Date -->
												<col width="80" align="center"><!-- Credit Card# -->
												<col width="80" align="center"><!-- CC Expiration Date -->
												<col width="80" align="center"><!-- Payment Term -->
												<col width="80" align="center"><!-- Renewal Type -->
												<col width="80" align="center"><!-- Renewal Method -->
												<col width="80" align="center"><!-- % Base -->
												<col width="80" align="center"><!-- % Overage -->
												<col width="80" align="center"><!-- # of Days Before -->
												<col width="80" align="center"><!-- Uplift Type -->
												<col width="80" align="center"><!-- Uplift Method -->
												<col width="80" align="center"><!-- % Base -->
												<col width="80" align="center"><!-- % Overage -->
												<col width="80" align="center"><!-- # of Days Before -->
<!-- QC28627 Add Start -->
												<col width="80" align="center"><!-- Contract Link # -->
<!-- QC28627 Add End   -->
<!-- QC26424 Add End-->
												<col width="80" align="center"><!-- Status -->
												<col width="80" align="center"><!-- Serial # -->
												<col width="150" align="center"><!-- Model # -->
												<col width="80" align="center"><!-- Machine Start Date -->
												<col width="80" align="center"><!-- Machine End Date -->
												<col width="80" align="center"><!-- Machine Status -->
												<col width="80" align="center"><!-- Schedule Date -->
<!-- QC26424 Add Start-->
												<col width="80" align="center"><!-- BaseUnit_Accessory -->
												<col width="80" align="center"><!-- IB ID -->
												<col width="80" align="center"><!-- Config# -->
												<col width="80" align="center"><!-- Item Code -->
												<col width="150" align="center"><!-- Item Name -->
												<col width="80" align="center"><!-- Ship To Code -->
												<col width="150" align="center"><!-- Ship To Customer Name -->
												<col width="150" align="center"><!-- Ship To Customer Address -->
												<col width="80" align="center"><!-- Svc Branch -->
												<col width="80" align="center"><!-- Service Program -->
												<col width="80" align="center"><!-- Read Method -->
												<col width="80" align="center"><!-- Date Terminated -->
												<col width="80" align="center"><!-- Termination Reason -->
												<col width="80" align="center"><!-- Renewal Date -->
												<col width="80" align="center"><!-- Times Renewed -->
												<col width="80" align="center"><!-- Base Bill To Code -->
												<col width="150" align="center"><!-- Base Bill To Customer Name -->
												<col width="150" align="center"><!-- Base Bill To Address -->
												<col width="150" align="center"><!-- Base Bill To Contact -->
												<col width="80" align="center"><!-- Base Frequency -->
												<col width="80" align="center"><!-- Base Charge -->
												<col width="80" align="center"><!-- Term Amount -->
												<col width="80" align="center"><!-- Billing Timing -->
												<col width="80" align="center"><!-- Period End Date -->
												<col width="80" align="center"><!-- Invoice Date -->

<!-- QC26424 Add End-->
												<tr height="35">
													<td id="AH0" class="pClothBs colFix"></td>
													<td id="AH1" class="pClothBs">Copy</td>
													<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')">Contract #</a><img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrCatgDescTxt_A')">Contract<br/>Type</a><img id="sortIMG.dsContrCatgDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
<!-- QC26424 Add -->
													<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNum_A')">Customer #</a><img id="sortIMG.dsAcctNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
<!-- QC26424 Add End-->

													<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A')">CustomerName</a><img id="sortIMG.dsAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
<!-- QC26424 Add -->
													<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'altPayerCustCd_A')">Bill To Code</a><img id="sortIMG.altPayerCustCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToLocNm_A')">Bill To Customer Name</a><img id="sortIMG.billToLocNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
<!-- QC26424 Add End-->
													<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrVrsnEffFromDt_A')">Start Date</a><img id="sortIMG.contrVrsnEffFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrVrsnEffThruDt_A')">End Date</a><img id="sortIMG.contrVrsnEffThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcRgNm_A')">Region</a><img id="sortIMG.svcRgNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcContrBrCd_A')">Branch</a><img id="sortIMG.svcContrBrCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
<!-- QC26424 Add -->
													<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrStsDescTxt_A')">Header<br/>Status</a><img id="sortIMG.dsContrStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrClsDescTxt_A')">Category</a><img id="sortIMG.dsContrClsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxPsnNm_BR')">Branch Rep</a><img id="sortIMG.xxPsnNm_BR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'tocNm_A')">Sales Rep</a><img id="sortIMG.tocNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcContrRefCmntTxt_A')">Source Ref#</a><img id="sortIMG.svcContrRefCmntTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrRptGrpDescTxt_A')">Report Group</a><img id="sortIMG.dsContrRptGrpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNm_A')">Description</a><img id="sortIMG.dsContrNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrInvCmntTxt_A')">Inv Comments</a><img id="sortIMG.contrInvCmntTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsInvTgtrTpDescTxt_A')">Invoicing Option</a><img id="sortIMG.dsInvTgtrTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'prcAllocByMachQtyFlg_A')">Allocate Across<br/>All Machine</a><img id="sortIMG.prcAllocByMachQtyFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>

													<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrEstTpDescTxt_A')">Auto Estimation</a><img id="sortIMG.mtrEstTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_LS')">Lease Company</a><img id="sortIMG.dsAcctNm_LS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'baseChrgToLeaseCmpyFlg_A')">Base Lease</a><img id="sortIMG.baseChrgToLeaseCmpyFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'usgChrgToLeaseCmpyFlg_A')">Usage Lease</a><img id="sortIMG.usgChrgToLeaseCmpyFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'cfsLeaseNumCmntTxt_A')">Lease #</a><img id="sortIMG.cfsLeaseNumCmntTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'custPoNum_A')">PO #</a><img id="sortIMG.custPoNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'poDt_A')">PO Expiration Date</a><img id="sortIMG.poDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH29" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'crCardCustRefNum_A')">Credit Card #</a><img id="sortIMG.crCardCustRefNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH30" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'crCardExprYrMth_A')">CC Expiration Date</a><img id="sortIMG.crCardExprYrMth_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH31" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'pmtTermCashDiscDescTxt_A')">Payment Term</a><img id="sortIMG.pmtTermCashDiscDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH32" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrAutoRnwTpDescTxt_A')">Renewal Type</a><img id="sortIMG.contrAutoRnwTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH33" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rnwPrcMethDescTxt_A')">Renewal Method</a><img id="sortIMG.rnwPrcMethDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH34" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'basePrcUpRatio_A')">% Base</a><img id="sortIMG.basePrcUpRatio_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH35" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrPrcUpRatio_A')">% Overage</a><img id="sortIMG.mtrPrcUpRatio_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH36" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'befEndRnwDaysAot_A')"># of Days Before</a><img id="sortIMG.befEndRnwDaysAot_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH37" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrUplftTpDescTxt_A')">Uplift Type</a><img id="sortIMG.contrUplftTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH38" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'uplftPrcMethDescTxt_A')">Uplift Method</a><img id="sortIMG.uplftPrcMethDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH39" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'uplftBasePrcUpRatio_A')">% Base</a><img id="sortIMG.uplftBasePrcUpRatio_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH40" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'uplftMtrPrcUpRatio_A')">% Overage</a><img id="sortIMG.uplftMtrPrcUpRatio_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH41" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'uplftBefEndRnwDaysAot_A')"># of Days Before</a><img id="sortIMG.uplftBefEndRnwDaysAot_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
<!-- QC26424 End -->
<!-- QC28627 Add Start -->
													<td id="AH42" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrLinkNum_A')">Link #</a><img id="sortIMG.contrLinkNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
<!-- QC28627 Add End -->
													<td id="AH43" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrCtrlStsNm_A')">Status</a><img id="sortIMG.dsContrCtrlStsNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH44" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')">Serial#</a><img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH45" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 't_MdlNm_A')">Model #</a><img id="sortIMG.t_MdlNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH46" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrEffFromDt_A')">Machine<br/>Start Date</a><img id="sortIMG.contrEffFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH47" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrEffThruDt_A')">Machine<br/>End Date</a><img id="sortIMG.contrEffThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH48" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachMstrStsNm_A')">Machine Status</a><img id="sortIMG.svcMachMstrStsNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH49" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'nextBllgDt_A')">Schedule<br/>Date</a><img id="sortIMG.nextBllgDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
<!-- QC26424 Start -->
													<td id="AH50" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachTpDescTxt_A')">BaseUnit<br/>Accessory</a><img id="sortIMG.svcMachTpDescTxt_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH51" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMachMstrPk_IB')">IB ID</a><img id="sortIMG.svcMachMstrPk_IB border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH52" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcConfigMstrPk_A')">Config#</a><img id="sortIMG.svcConfigMstrPk_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH53" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseCd_A')">Item Code</a><img id="sortIMG.mdseCd_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH54" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseDescShortTxt_A')">Item Name</a><img id="sortIMG.mdseDescShortTxt_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH55" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'shipToCustCd_A')">Ship To Code</a><img id="sortIMG.shipToCustCd_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH56" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'shipToLocNm_A')">Ship To Customer Name</a><img id="sortIMG.shipToLocNm_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH57" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'shipToCustLocAddr_A')">Ship To Customer Address</a><img id="sortIMG.shipToCustLocAddr_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH58" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcBrCdDescTxt_A')">Svc Branch</a><img id="sortIMG.svcBrCdDescTxt_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH59" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcPgmMdseCd_A')">Service Program</a><img id="sortIMG.svcPgmMdseCd_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH60" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrReadMethDescTxt_A')">Read Method</a><img id="sortIMG.mtrReadMethDescTxt_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH61" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrCloDt_A')">Date Terminated</a><img id="sortIMG.contrCloDt_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH62" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMemoRsnDescTxt_A')">Termination Reason</a><img id="sortIMG.svcMemoRsnDescTxt_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH63" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rnwEffFromDt_A')">Renewal Date</a><img id="sortIMG.rnwEffFromDt_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH64" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrRnwTotCnt_A')">Times Renewed</a><img id="sortIMG.contrRnwTotCnt_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH65" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgMtrBillToCustCd_A')">Base Bill To Code</a><img id="sortIMG.bllgMtrBillToCustCd_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH66" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToLocNm_BS')">Base Bill To Customer Name</a><img id="sortIMG.billToLocNm_BS border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH67" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustLocAddr_A')">Base Bill To Address</a><img id="sortIMG.billToCustLocAddr_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH68" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxPsnNm_BS')">Base Bill To Contact</a><img id="sortIMG.xxPsnNm_BS border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH69" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgCycleDescTxt_A')">Base Frequency</a><img id="sortIMG.bllgCycleDescTxt_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH70" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'basePrcDealAmt_A')">Base Charge</a><img id="sortIMG.basePrcDealAmt_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH71" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'basePrcTermDealAmtRate_A')">Term Amount</a><img id="sortIMG.basePrcTermDealAmtRate_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH72" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bllgTmgTpDescTxt_A')">Billing Timing</a><img id="sortIMG.bllgTmgTpDescTxt_A border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH73" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxCntnrTxt_CD')">Period End Date</a><img id="sortIMG.xxCntnrTxt_CD border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH74" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxCntnrTxt_BD')">Invoice Date</a><img id="sortIMG.xxCntnrTxt_BD border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
<!-- QC26424 End -->
												</tr>
											</table>
										</div>
										<div id="rightTbl" style="width:1122px; height:174px; display:block; overflow:scroll; margin:0px; padding: 0px;">
											<table style="table-layout:fixed;" width="1270" border="1" cellpadding="1" cellspacing="0" id="A">
												<col width="25" align="center"><!-- Select -->
												<col width="40" align="center"><!-- Copy -->
												<col width="80"><!-- Contract # -->
												<col width="80"><!-- Contract Type -->
<!-- QC26424 Add Start-->
												<col width="80"><!-- Customer # -->
<!-- QC26424 Add End-->

												<col width="150"><!-- Customer Name -->
<!-- QC26424 Add Start-->
												<col width="80"><!-- Bill To Code # -->
												<col width="150"><!-- Bill To Customer Name # -->
<!-- QC26424 Add End-->
												<col width="80" align="center"><!-- Start Date -->
												<col width="80" align="center"><!-- End Date -->
												<col width="80"><!-- Region -->
												<col width="50" align="center"><!-- Branch -->
<!-- QC26424 Add Start-->
												<col width="80"><!-- Header Status -->
												<col width="80"><!-- Category -->
												<col width="150"><!-- Branch Rep -->
												<col width="150"><!-- Sales Rep -->
												<col width="80"><!-- Source Ref# -->
												<col width="80"><!-- Report Group# -->
												<col width="150"><!-- Description -->
												<col width="150"><!-- Inv Comments -->
												<col width="250" align="center"><!-- Invoicing Option -->
												<col width="150" align="center"><!-- Allocate Across All Machine -->
												<col width="80"><!-- Auto Estimation -->
												<col width="150"><!-- Lease Company -->
												<col width="80" align="center"><!-- Base Lease Flag -->
												<col width="80" align="center"><!-- Usage Lease Flag -->
												<col width="80"><!-- Lease# -->
												<col width="80"><!-- PO# -->
												<col width="80"  align="center"><!-- PO Expiration Date -->
												<col width="80"><!-- Credit Card# -->
												<col width="80"  align="center"><!-- CC Expiration Date -->
												<col width="80"><!-- Payment Term -->
												<col width="80"><!-- Renewal Type -->
												<col width="80"><!-- Renewal Method -->
												<col width="80"  align="center"><!-- % Base -->
												<col width="80"  align="center"><!-- % Overage -->
												<col width="80"  align="center"><!-- # of Days Before -->
												<col width="80"><!-- Uplift Type -->
												<col width="80"><!-- Uplift Method -->
												<col width="80"  align="center"><!-- % Base -->
												<col width="80"  align="center"><!-- % Overage -->
												<col width="80"  align="center"><!-- # of Days Before -->
<!-- QC28627 Add Start -->
												<col width="80"  align="center"><!-- # Link# -->
<!-- QC28627 Add End -->

<!-- QC26424 Add End-->
												<col width="80"><!-- Status -->
												<col width="80"><!-- Serial # -->
												<col width="150"><!-- Model # -->
												<col width="80" align="center"><!-- Machine Start Date -->
												<col width="80" align="center"><!-- Machine End Date -->
												<col width="80"><!-- Machine Status -->
												<col width="80" align="center"><!-- Schedule Date -->
<!-- QC26424 Add Start-->
												<col width="80"><!-- BaseUnit_Accessory -->
												<col width="80"><!-- IB ID -->
												<col width="80"><!-- Config# -->
												<col width="80"><!-- Item Code -->
												<col width="150"><!-- Item Name -->
												<col width="80"><!-- Ship To Code -->
												<col width="150"><!-- Ship To Customer Name -->
												<col width="150"><!-- Ship To Customer Address -->
												<col width="80"><!-- Svc Branch -->
												<col width="80"><!-- Service Program -->
												<col width="80"><!-- Read Method -->
												<col width="80" align="center"><!-- Date Terminated -->
												<col width="80"><!-- Termination Reason -->
												<col width="80" align="center"><!-- Renewal Date -->
												<col width="80" align="center"><!-- Times Renewed -->
												<col width="80"><!-- Base Bill To Code -->
												<col width="150"><!-- Base Bill To Customer Name -->
												<col width="150"><!-- Base Bill To Address -->
												<col width="150"><!-- Base Bill To Contact -->
												<col width="80"><!-- Base Frequency -->
												<col width="80" align="right"><!-- Base Charge -->
												<col width="80" align="right"><!-- Term Amount -->
												<col width="80"><!-- Billing Timing -->
												<col width="80" align="center"><!-- Period End Date -->
												<col width="80" align="center"><!-- Invoice Date -->
<!-- QC26424 Add End-->
												<% int i = 0; %>
												<ezf:row ezfHyo="A">
													<tr height="25">
														<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:inputButton name="OpenWin_CopyContr" value="Copy" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
														<td><ezf:anchor name="dsContrNum_AL" ezfName="dsContrNum_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ContrMainte" otherAttr=" id=\"dsContrNum_AL#{EZF_ROW_NUMBER}\""><ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/></ezf:anchor></td>
														<td><ezf:inputText name="dsContrCatgDescTxt_A" ezfName="dsContrCatgDescTxt_A" value="Non Fleet" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
<!-- QC26424 Add Start-->
														<td><ezf:inputText name="dsAcctNum_A" ezfName="dsAcctNum_A" value="512263" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
<!-- QC26424 Add End-->
														<td><ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="Omnicare Inc." ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
<!-- QC26424 Add Start-->
														<td><ezf:inputText name="altPayerCustCd_A" ezfName="altPayerCustCd_A" value="512263" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="billToLocNm_A" ezfName="billToLocNm_A" value="Omnicare Inc." ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
<!-- QC26424 Add End-->
														<td><ezf:label name="contrVrsnEffFromDt_A" ezfName="contrVrsnEffFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="contrVrsnEffThruDt_A" ezfName="contrVrsnEffThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="svcRgNm_A" ezfName="svcRgNm_A" value="North East Region" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:label name="svcContrBrCd_A" ezfName="svcContrBrCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- QC26424 Add Start-->
														<td><ezf:inputText name="dsContrStsDescTxt_A" ezfName="dsContrStsDescTxt_A" value="XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="dsContrClsDescTxt_A" ezfName="dsContrClsDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="xxPsnNm_BR" ezfName="xxPsnNm_BR" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
														<td><ezf:inputText name="tocNm_A" ezfName="tocNm_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
														<td><ezf:inputText name="svcContrRefCmntTxt_A" ezfName="svcContrRefCmntTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="dsContrRptGrpDescTxt_A" ezfName="dsContrRptGrpDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="dsContrNm_A" ezfName="dsContrNm_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
														<td><ezf:inputText name="contrInvCmntTxt_A" ezfName="contrInvCmntTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
														<td><ezf:inputText name="dsInvTgtrTpDescTxt_A" ezfName="dsInvTgtrTpDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"33\""/></td>
														<td><ezf:label name="prcAllocByMachQtyFlg_A" ezfName="prcAllocByMachQtyFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>


														<td><ezf:inputText name="mtrEstTpDescTxt_A" ezfName="mtrEstTpDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="dsAcctNm_LS" ezfName="dsAcctNm_LS" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
														<td><ezf:label name="baseChrgToLeaseCmpyFlg_A" ezfName="baseChrgToLeaseCmpyFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="usgChrgToLeaseCmpyFlg_A" ezfName="usgChrgToLeaseCmpyFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>

														<td><ezf:inputText name="cfsLeaseNumCmntTxt_A" ezfName="cfsLeaseNumCmntTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="custPoNum_A" ezfName="custPoNum_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:label name="poDt_A" ezfName="poDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="crCardCustRefNum_A" ezfName="crCardCustRefNum_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:label name="crCardExprYrMth_A" ezfName="crCardExprYrMth_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="pmtTermCashDiscDescTxt_A" ezfName="pmtTermCashDiscDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="contrAutoRnwTpDescTxt_A" ezfName="contrAutoRnwTpDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="rnwPrcMethDescTxt_A" ezfName="rnwPrcMethDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:label name="basePrcUpRatio_A" ezfName="basePrcUpRatio_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="mtrPrcUpRatio_A" ezfName="mtrPrcUpRatio_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="befEndRnwDaysAot_A" ezfName="befEndRnwDaysAot_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="contrUplftTpDescTxt_A" ezfName="contrUplftTpDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="uplftPrcMethDescTxt_A" ezfName="uplftPrcMethDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:label name="uplftBasePrcUpRatio_A" ezfName="uplftBasePrcUpRatio_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="uplftMtrPrcUpRatio_A" ezfName="uplftMtrPrcUpRatio_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="uplftBefEndRnwDaysAot_A" ezfName="uplftBefEndRnwDaysAot_A" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- QC28627 Add Start -->
														<td><ezf:label name="contrLinkNum_A" ezfName="contrLinkNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- QC28627 Add End -->


<!-- QC26424 Add End-->
														
														
														
														<td><ezf:inputText name="dsContrCtrlStsNm_A" ezfName="dsContrCtrlStsNm_A" value="Active" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td>
															<% if (bMsg.A.no(i).xxExstFlg_PM.getValue().equals(ZYPConstant.FLG_ON_Y)) { %>
															<ezf:anchor name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_MachMainte" otherAttr=" id=\"serNum_AL#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/></ezf:anchor>
															<% } else { %>
															<ezf:label name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" />
															<% } %>
														</td>
														<td><ezf:inputText name="t_MdlNm_A" ezfName="t_MdlNm_A" value="800" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
														<td><ezf:label name="contrEffFromDt_A" ezfName="contrEffFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="contrEffThruDt_A" ezfName="contrEffThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="svcMachMstrStsNm_A" ezfName="svcMachMstrStsNm_A" value="Active" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:label name="nextBllgDt_A" ezfName="nextBllgDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- QC26424 Add Start-->
														<td><ezf:inputText name="svcMachTpDescTxt_A" ezfName="svcMachTpDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:label name="svcMachMstrPk_IB" ezfName="svcMachMstrPk_IB" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="svcConfigMstrPk_A" ezfName="svcConfigMstrPk_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
														<td><ezf:inputText name="shipToCustCd_A" ezfName="shipToCustCd_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="shipToLocNm_A" ezfName="shipToLocNm_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
														<td><ezf:inputText name="shipToCustLocAddr_A" ezfName="shipToCustLocAddr_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
														<td><ezf:inputText name="svcBrCdDescTxt_A" ezfName="svcBrCdDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="mdseDescShortTxt_SA" ezfName="mdseDescShortTxt_SA" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="mtrReadMethDescTxt_A" ezfName="mtrReadMethDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:label name="contrCloDt_A" ezfName="contrCloDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="svcMemoRsnDescTxt_A" ezfName="svcMemoRsnDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:label name="rnwEffFromDt_A" ezfName="rnwEffFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="contrRnwTotCnt_A" ezfName="contrRnwTotCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="bllgMtrBillToCustCd_A" ezfName="bllgMtrBillToCustCd_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:inputText name="billToLocNm_BS" ezfName="billToLocNm_BS" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
														<td><ezf:inputText name="billToCustLocAddr_A" ezfName="billToCustLocAddr_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
														<td><ezf:inputText name="xxPsnNm_BS" ezfName="xxPsnNm_BS" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
														<td><ezf:inputText name="bllgCycleDescTxt_A" ezfName="bllgCycleDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:label name="basePrcDealAmt_A" ezfName="basePrcDealAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="basePrcTermDealAmtRate_A" ezfName="basePrcTermDealAmtRate_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="bllgTmgTpDescTxt_A" ezfName="bllgTmgTpDescTxt_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
														<td><ezf:label name="xxCntnrTxt_CD" ezfName="xxCntnrTxt_CD" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="xxCntnrTxt_BD" ezfName="xxCntnrTxt_BD" ezfHyo="A" ezfArrayNo="0" /></td>
<!-- QC26424 Add End-->
													</tr>
													<% i++; %>
												</ezf:row>
												<ezf:skip>
												<!--
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_A" ezfname="xxChkBox_A"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
													<tr height="25">
														<td><input type="checkbox" name="xxChkBox_05"></td>
														<td><input type="button" class="pBtn1" value="Copy"></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>WWW</label></td>
														<td><a href="#"><label ezfout style="white-space:nowrap; overflow:hidden;">WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3</label></a></td>
														<td><input type="text" class="pPro" size="20" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>01/10/2014</label></td>
														<td><label ezfout>01/09/2015</label></td>
														<td><input type="text" class="pPro" size="10" value="WWWWWWWWW1WWWWWWWWW2"></td>
														<td><label ezfout>09/09/2014</label></td>
													</tr>
												-->
												</ezf:skip>
											</table>
										</div><!-- rightTbl -->
									</div><!-- right table -->
								</div><!-- parent box -->
							</td>
						</tr>
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="310">
									<col width="100%" align="right">
									<tr>
										<td>
											<ezf:inputButton name="SelectAll_Contr" value="Select All" ezfHyo="SelectAll_Contr" ezfArrayNo="0" htmlClass="pBtn6" />
											<ezf:inputButton name="UnSelectAll_Contr" value="Unselect All" ezfHyo="UnSelectAll_Contr" ezfArrayNo="0" htmlClass="pBtn6" />
											<ezf:inputButton name="Print" value="Print" ezfHyo="Print" ezfArrayNo="0" htmlClass="pBtn6" />
<!-- START 2018/06/26 T.Ogura [QC#26336,ADD] -->
											<ezf:inputButton name="Meter_History" value="Meter History" ezfHyo="Meter_History" ezfArrayNo="0" htmlClass="pBtn6" />
<!-- END   2018/06/26 T.Ogura [QC#26336,ADD] -->
										</td>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<col width="80">
												<col width="205">
												<col width="40">
												<tr>
													<td class="stab">Contract Action</td>
													<td>
														<ezf:select name="xxGenlFldAreaTxt_B" ezfName="xxGenlFldAreaTxt_B" ezfBlank="1" ezfCodeName="xxGenlFldAreaTxt_LC" ezfDispName="xxGenlFldAreaTxt_LN" otherAttr=" style=\"width:200;\""/>
													</td>
													<td><ezf:inputButton name="OpenWin_PrepMassUpdScrn" value="Go" ezfHyo="OpenWin_PrepMassUpdScrn" ezfArrayNo="0" htmlClass="pBtn2" /></td>
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
	<script type="text/javascript" defer>
	    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true);
	</script>
<!-- ###SCRIPT -->
<script type="text/javascript">
	function synchroHeaderRightScroll() {
		var topTBL    = document.getElementById( 'topHeaderTBL'    );
		var rightTBL  = document.getElementById( 'rightHeaderTBL'  );
		var leftTBL  = this.document.getElementById( 'leftHeaderTBL'  );
		topTBL.scrollLeft = rightTBL.scrollLeft;
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroScroll_fromHeaderLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftHeaderTBL'  );
		var rightTBL = this.document.getElementById( 'rightHeaderTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroLineRightScroll() {
		var topTBL    = document.getElementById( 'topLineTBL'    );
		var rightTBL  = document.getElementById( 'rightLineTBL'  );
		var leftTBL  = this.document.getElementById( 'leftLineTBL'  );
		topTBL.scrollLeft = rightTBL.scrollLeft;
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
	
	function synchroScroll_fromLineLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftLineTBL'  );
		var rightTBL = this.document.getElementById( 'rightLineTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
	}
</script>

<%-- **** Task specific area ends here **** --%>
