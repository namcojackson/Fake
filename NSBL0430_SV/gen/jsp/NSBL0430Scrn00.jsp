<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161205181951 --%>
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
			<input type="hidden" name="pageID" value="NSBL0430Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mods Serial# Assignment">

			<center>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td>
							<%-- include S21BusinessProcessTAB --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Mods Machine Level Status" class="pTab_ON"><a href="#">MdSerNoAss</a></li>
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

							<div class="pTab_BODY">
								<table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
									<tr>
										<td>
											<table style="table-layout:fixed;" width="913" border="0" cellpadding="0" cellspacing="1">
												<col width="110" valign="top">
												<col width="695" valign="top">
												<col width="" valign="top">
												<tr>
													<td class="stab">Mod Plan ID</td>
													<td><ezf:inputText name="svcModPlnId" ezfName="svcModPlnId" value="1234567890123456" otherAttr=" size=\"18\""/></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Description</td>
													<td><ezf:inputText name="svcModNm" ezfName="svcModNm" value="12345678901234567901234567890123456789X" otherAttr=" size=\"42\""/></td>
													<td><ezf:inputButton name="MoveWin_Search" value="Mod Plan Search" htmlClass="pBtn9" /></td>
												</tr>
												<tr>
													<td class="stab">Comment</td>
													<td><ezf:inputText name="svcModCmntTxt" ezfName="svcModCmntTxt" value="12345678901234567901234567890123456789X" otherAttr=" size=\"42\""/></td>
													<td><ezf:inputButton name="MoveWin_Detail" value="Plan Detail" htmlClass="pBtn9" /></td>
												</tr>
												<tr>
													<td class="stab">Estimated Labor</td>
													<td class="stab">
														<ezf:inputText name="xxEndDplyTmTxt" ezfName="xxEndDplyTmTxt" value="23:59" otherAttr=" size=\"5\""/>(HH:MM)
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														Position to row Item Code
														<ezf:inputText name="mdseCd_F" ezfName="mdseCd_F" value="123456789012345X" otherAttr=" size=\"18\" maxlength=\"16\" ezftoupper=\"\""/>
														<ezf:inputButton name="Filter" value="Filter" htmlClass="pBtn6" />
													</td>
													<td><ezf:inputButton name="MoveWin_MachineStatus" value="Machine Status" htmlClass="pBtn9" /></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td >
										<hr width="98%" align="left">
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
										<table border="0" style="table-layout:fixed;width=913px;margin-left:0;">
											<col width="50" align="left">
											<col width="80" align="left">
											<col width="100" align="left" valign="top">
											<col width="500" align="right" valign="bottom">
											<tr>
												<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ItemMstrPop" >Item Code</ezf:anchor></td>
												<td>
													<ezf:inputText name="mdseCd" ezfName="mdseCd" value="1230000006" otherAttr=" id=\"mdseCd\" size=\"12\" maxlength=\"34\" tabindex=\"-1\" ezftoupper=\"\""/>
												</td>
												<td >
													<ezf:inputButton name="Add" value="Add" htmlClass="pBtn3" otherAttr=" id=\"btnAdd\""/>
												</td>
												<!-- Pagination & Navigation--START-->
												<td>
													<ezf:skip>
													<table border="0" cellpadding="1" cellspacing="0">
														<col >
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="10">
														<col width="0">
														<col width="1">
														<col width="0">
														<tr>
															<td class="stab">Showing</td>
															<td class="pOut">1</td>
															<td class="stab">to</td>
															<td class="pOut">3</td>
															<td class="stab">of</td>
															<td class="pOut">1000</td>
															<td>&nbsp;</td>
															<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
															<td></td>
															<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
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
										</td>
									</tr>
<!-- ######################################## DETAIL #################################### -->
									<tr>
										<td valign="top">
											<%-- ########## TOP ########## --%>
											<div id="top" style="overflow-y:hidden; height:; overflow-x:hidden; width:913; text-align:center;">
												<table border="1" cellpadding="1" cellspacing="0" align="left">
													<col width="20" align="center">
													<col width="120" align="center">
													<col width="240" align="center">
													<col width="240" align="center">
													<col width="110" align="center">
													<col width="150" align="center">
													<tr height="37">
														<td class="pClothBs"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','mdseCd_A')" ezfhyo="A">Item Code</a><img id="sortIMG.mdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','svcModFromSerNum_A')" ezfhyo="A">Serial# Range From</a><img id="sortIMG.svcModFromSerNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','svcModToSerNum_A')" ezfhyo="A">Serial# Range To</a><img id="sortIMG.svcModToSerNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','autoCratCallFlg_A')" ezfhyo="A">Auto Create Calls</a><img id="sortIMG.autoCratCallFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A','autoCratRfrsTmgCd_A')" ezfhyo="A">Auto Create Refresh Rate</a><img id="sortIMG.autoCratRfrsTmgCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													</tr>
												</table>
											</div>
											<%-- ########## DETAIL ########## --%>
											<div id="Detail" style="overflow-y:scroll; height:350; overflow-x:hidden; width:930;">
												<table id="A" border="1" cellpadding="1" cellspacing="0" align="left">
													<col width="20" align="left">
													<col width="120" align="left">
													<col width="240" align="left">
													<col width="240" align="left">
													<col width="110" align="left">
													<col width="150" align="left">
													<ezf:row ezfHyo="A">
													<tr height="28">
														<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="svcModFromSerNum_A" ezfName="svcModFromSerNum_A" value="12345678901234567890123456789X" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"32\" ezftoupper=\"\""/></td>
														<td><ezf:inputText name="svcModToSerNum_A" ezfName="svcModToSerNum_A" value="12345678901234567890123456789X" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"32\" ezftoupper=\"\""/></td>
														<td>
															<ezf:select name="autoCratCallFlg_A" ezfName="autoCratCallFlg_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="xxDplyByCtrlItemCd_01" ezfDispName="xxDplyByCtrlItemCdNm_01" otherAttr=" style=\"width:40\""/>
														</td>
														<td>
															<ezf:select name="autoCratRfrsTmgCd_A" ezfName="autoCratRfrsTmgCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="autoCratRfrsTmgCd_01" ezfDispName="autoCratRfrsTmgDescTxt_01" otherAttr=" style=\"width:100\""/>
														</td>
														<td class="pAuditInfo">
															<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
															<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
														</td>
													</tr>
													</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<table border="0" width="96%" align="center">
									<col width="" align="" valign="top">
									<col width="" align="" valign="top">
									<col width="" align="" valign="top">
									<col width="" align="" valign="top">
									<col width="" align="" valign="top">
									<col width="" align="" valign="top">
									<col width="" align="" valign="top">
									<col width="" align="" valign="top">
									<col width="" align="" valign="top">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0">
												<tr>
													<td >
														<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn7" otherAttr=" id=\"btnSelectAll\""/>
													</td>
													<td>&nbsp;</td>
													<td>
														<ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn7" otherAttr=" id=\"btnUnselectAll\""/>
													</td>
													<td>&nbsp;</td>
													<td>
														<ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn7" otherAttr=" id=\"btnDelete\""/>
													</td>
													<td>&nbsp;&nbsp;&nbsp;</td>
													<td>
														<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TemplateDownload" >Template Download</ezf:anchor>
													</td>
													<td>&nbsp;</td>
													<td>
														<ezf:inputFile name="xxFileData_U" ezfName="xxFileData_U" otherAttr=" size=\"25\" maxlength=\"9999\""/>
														<ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn6" />
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

<%-- **** Task specific area ends here **** --%>
