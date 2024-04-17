<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170412111555 --%>
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
			<input type="hidden" name="pageID" value="NSBL0210Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Labor Charge Maintenance">

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
									<li title = "Labor Charge Maintenance" class="pTab_ON" ><a href="#">Lbor Chrg</a></li>
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
				<div class="pTab_BODY">
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<col width="100%" align="left">
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<col width=" 10">
												<col width=" 78">
												<col width=" 10">
												<col width="168">
												<col width=" 10">
												<col width=" 96">
												<col width=" 10">
												<col width=" 96">
												<col width=" 10">
												<col width=" 96">
												<col width=" 10">
												<col width="168">
												<col width=" 10">
												<col width=" 72">
												<tr>
													<td>&nbsp;</td>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ModelGroupHeader" >Model Group</ezf:anchor></td>
													<td>&nbsp;</td>
													<td><ezf:inputText name="mdlGrpNm_H" ezfName="mdlGrpNm_H" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
													<td>&nbsp;</td>
													<td class="stab">Line of Business</td>
													<td>&nbsp;</td>
													<td><ezf:select name="svcLineBizCd_H" ezfName="svcLineBizCd_H" ezfBlank="1" ezfCodeName="lineBizTpCd_L" ezfDispName="lineBizTpDescTxt_L" otherAttr=" style=\"width:80px;\""/></td>
													<td>&nbsp;</td>
													<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShiftHeader" >Shift Description (*)</ezf:anchor></td>
													<td>&nbsp;</td>
													<td><ezf:inputText name="svcPrcShiftDescTxt_H" ezfName="svcPrcShiftDescTxt_H" otherAttr=" size=\"20\""/></td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><hr></td>
						</tr>
						<tr>
							<table width="100%">
								<tr>
									<td>
										<table width="98%">
											<col width="  0">
											<col width=" 72">
											<col width=" 72">
											<col width="20">
											<col width="110">
											<col width="370">
											<col align="right">
											<tr>
												<td></td>
												<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn8" /></td>
												<td><ezf:inputButton name="UnSelectAll" value="Unselect All" htmlClass="pBtn8" /></td>
												<td></td>
												<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TemplateDownload" >Template Download</ezf:anchor></td>
												<td><ezf:inputFile name="xxFileData_U" ezfName="xxFileData_U" otherAttr=" size=\"25\" maxlength=\"9999\""/><ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn6" /></td>
												<td>
													<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
														<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
														<jsp:param name="TableNm"     value="A" />
														<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
														<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
														<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
													</jsp:include>
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
															<td class="pOut">50</td>
															<td class="stab">of</td>
															<td class="pOut">100</td>
															<td>&nbsp;</td>
															<td><input type="button" class="pBtn3" value="Prev" name="PagePrev"></td>
															<td></td>
															<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
														</tr>
													</table>
													</ezf:skip>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<%-- ######################################## DETAIL ######################################## --%>
							<table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
								<col align="left" valign="top">
								<tr>
									<td>
										<div id="parentBoxA">
											<div style="float:left; display:block"> <!-- left table -->
												<div id='leftTblHead' style="display:block;">
												</div>
												<div id='leftTbl' style="float:left; display:block; height:220px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
												</div>
											</div>  <!-- left table -->
											<div style="float:left"> <!-- right table -->
												<div id='rightTblHead' style="width:1065px; display:block; overflow:hidden; margin:0px;padding:0px;">
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1373px" style="margin-right:20px" >
														<col width=" 34" align="center">	<!-- CheckBox -->
														<col width="150" align="center">	<!-- Model Group -->
														<col width="185" align="center">	<!-- Model Group Description -->
														<col width=" 72" align="center">	<!-- Line of Business -->
														<col width=" 56" align="center">	<!-- Charge Travel -->
														<col width="150" align="center">	<!-- Shift# -->
														<col width="185" align="center">	<!-- Shift Description -->
														<col width=" 70" align="center">	<!-- Labor Per Hour -->
														<col width=" 70" align="center">	<!-- Minimum Hours -->
														<col width="185" align="center">	<!-- Default Intangible Item# -->
														<col width="150" align="center">	<!-- Default Intangible Item Description -->
														<tr height="40px">
															<td class="pClothBs">&nbsp;</td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdlGrpNm')">Model Group<img id="sortIMG.mdlGrpNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdlGrpDescTxt')">Model Group Description<img id="sortIMG.mdlGrpDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcLineBizCd')">Line of Business<img id="sortIMG.svcLineBizCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcPrcTrvlChrgFlg')">Charge Travel<img id="sortIMG.svcPrcTrvlChrgFlg" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcPrcShiftNum')">Shift#<img id="sortIMG.svcPrcShiftNum" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcPrcShiftDescTxt')">Shift Description<img id="sortIMG.svcPrcShiftDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcLborUnitAmt')">Labor Per Hour<img id="sortIMG.svcLborUnitAmt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcMinChrgHrsAot')">Minimum Hours<img id="sortIMG.svcMinChrgHrsAot" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'intgMdseCd')">Default Intangible Item#<img id="sortIMG.intgMdseCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mdseDescShortTxt')">Default Intangible Item Description<img id="sortIMG.mdseDescShortTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														</tr>
													</table>
												</div><!-- 'rightTblHead' -->
												<div id="rightTbl" style="width:1082px; height:400px; display:block; overflow:scroll; margin:0px; padding:0px;" >
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1373px" >
														<col width=" 34" align="center">	<!-- CheckBox -->
														<col width="150" align="left">		<!-- Model Group -->
														<col width="185" align="left">		<!-- Model Group Description -->
														<col width=" 72" align="left">		<!-- Line of Business -->
														<col width=" 56" align="center">	<!-- Charge Travel -->
														<col width="150" align="left">		<!-- Shift# -->
														<col width="185" align="left">		<!-- Shift Description -->
														<col width=" 70" align="left">		<!-- Labor Per Hour -->
														<col width=" 70" align="left">		<!-- Minimum Hours -->
														<col width="185" align="left">		<!-- Default Intangible Item# -->
														<col width="150" align="left">		<!-- Default Intangible Item Description -->
														<ezf:skip>
														<tr height="28px">
															<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
															<td><input type="button" class="pBtn0" value="M" name="OpenWin_ModelGroupDetail" onclick="sendServer(this)" ezfhyo="A"><input type="text" size="11" value="IRADV" name="mdlGrpNm" ezftoupper ezfname="mdlGrpNm" ezfhyo="A"><input type="button" class="pBtn0" value=">>" name="ApplyModelGroup" onclick="sendServer(this)" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="25" value="IMAGE RUNNER ADVANCED" name="mdlGrpDescTxt" tabindex="-1" readonly ezfname="mdlGrpDescTxt" ezfhyo="A"></td>
															<td><select style="width:70px;" name="svcLineBizCd" ezfhyo="A" ezfname="svcLineBizCd" ezflist="lineBizTpCd_L,lineBizTpDescTxt_L,99, ,blank" onchange="sendServer('OnChangeLOB', '{EZF_ROW_NUMBER}')"><option></option><option>ESS</option><option>LFS</option><option>PPS</option></select></td>
															<td><input type="checkbox" value="Y" name="svcPrcTrvlChrgFlg" ezfhyo="A" ezfname="svcPrcTrvlChrgFlg"></td>
															<td><input type="button" class="pBtn0" value="M" name="OpenWin_ShiftDetail" onclick="sendServer(this)" ezfhyo="A"><input type="text" style="text-align:right" size="11" value="1" name="svcPrcShiftNum" ezftoupper ezfname="svcPrcShiftNum" ezfhyo="A"><input type="button" class="pBtn0" value=">>" name="ApplyShift" onclick="sendServer(this)" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="25" value="REGULAR Mon-Fri Shift 1" tabindex="-1" readonly ezfname="svcPrcShiftDescTxt" ezfhyo="A"></td>
															<td><input type="text" name="svcLborUnitAmt" value="180.00" style="text-align:right" size="8" maxlength="11" ezfname="svcLborUnitAmt" ezfHyo="A"></td>
															<td><input type="text" name="svcMinChrgHrsAot" value="1" style="text-align:right" size="8" maxlength="11" ezfname="svcMinChrgHrsAot" ezfHyo="A"></td>
															<td><input type="button" class="pBtn0" value="M" name="OpenWin_IntgItemPopup" onclick="sendServer(this)" ezfhyo="A"><input type="text" size="16" maxlength="16" value="ITEM1" name="intgMdseCd" ezfname="intgMdseCd" ezftoupper ezfhyo="A"><input type="button" class="pBtn0" value=">>" name="ApplyIntgItem" onclick="sendServer(this)" ezfhyo="A"></td>
															<td><input type="text" class="pPro" size="20" value="" name="mdseDescShortTxt" tabindex="-1" readonly ezfname="mdseDescShortTxt" ezfhyo="A"></td>
														</tr>
														</ezf:skip>
														<ezf:row ezfHyo="A">
														<tr height="28px">
															<td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputButton name="OpenWin_ModelGroupDetail" value="M" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /><ezf:inputText name="mdlGrpNm" ezfName="mdlGrpNm" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="ApplyModelGroup" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
															<td><ezf:inputText name="mdlGrpDescTxt" ezfName="mdlGrpDescTxt" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" tabindex=\"-1\""/></td>
															<td><ezf:select name="svcLineBizCd" ezfName="svcLineBizCd" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="lineBizTpCd_L" ezfDispName="lineBizTpDescTxt_L" otherEvent1=" onchange=\"sendServer('OnChangeLOB', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:70px;\""/></td>
															<td><ezf:inputCheckBox name="svcPrcTrvlChrgFlg" ezfName="svcPrcTrvlChrgFlg" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputButton name="OpenWin_ShiftDetail" value="M" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /><ezf:inputText name="svcPrcShiftNum" ezfName="svcPrcShiftNum" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right\" size=\"11\" ezftoupper=\"\""/><ezf:inputButton name="ApplyShift" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
															<td><ezf:inputText name="svcPrcShiftDescTxt" ezfName="svcPrcShiftDescTxt" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" tabindex=\"-1\""/></td>
															<td><ezf:inputText name="svcLborUnitAmt" ezfName="svcLborUnitAmt" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
															<td><ezf:inputText name="svcMinChrgHrsAot" ezfName="svcMinChrgHrsAot" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right\" size=\"8\" maxlength=\"11\""/></td>
															<td><ezf:inputButton name="OpenWin_IntgItemPopup" value="M" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /><ezf:inputText name="intgMdseCd" ezfName="intgMdseCd" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/><ezf:inputButton name="ApplyIntgItem" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
															<td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" tabindex=\"-1\""/></td>
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
												</div><!-- rightTbl -->
											</div><!-- right table -->
										</div><!-- parent box -->
									</td>
								</tr>
								<tr>
									<td>
										<table border="0" style="table-layout:fixed;width=98%;">
											<col align="left" width=" 90">
											<col align="left" width=" 90" >
											<col align="right">
											<tr>
												<td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn8" /></td>
												<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn8" /></td>
												<td>&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
	<script type="text/javascript" defer>
		S21TableUI.initialize("parentBoxA", "AHEAD", "A");
	</script>


<%-- **** Task specific area ends here **** --%>
