<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160712105619 --%>
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
			<input type="hidden" name="pageID" value="NMAL2710Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Reassign Postal Codes">

			<center>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Reassign Postal Codes" class="pTab_ON"><a href="#">PostRuleMove</a></li>
									</div>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
				<%-- include S21BusinessProcessTAB --%>
				<div class="pTab_BODY">
					<table width="100%" >
						<tr>
							<td>
			<%-- ######################################## HEADER ######################################## --%>
								<%-- ##### BODY(TAB) ##### --%>
								<%-- ##### TAB(Main) ##### --%>
								<table style="table-layout:fixed;">
									<col width="100">
									<col width="240">
									<col width="100">
									<col width="240">
									<col width="80">
									<col width="230">
									<col width="110">
										<tr>
											<td class="stab">Business Area</td>
											<td>
												<ezf:select name="bizAreaOrgCd_H" ezfName="bizAreaOrgCd_H" ezfBlank="1" ezfCodeName="bizAreaOrgCd_P" ezfDispName="bizAreaOrgDescTxt_P" otherAttr=" style=\"width:140px\""/>
											</td>
											<td class="stab">Territory Group</td>
											<td>
												<ezf:select name="trtyGrpTpCd_H" ezfName="trtyGrpTpCd_H" ezfBlank="1" ezfCodeName="trtyGrpTpCd_P" ezfDispName="trtyGrpTpDescTxt_P" otherAttr=" style=\"width:140px\""/>
											</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_TrtyNmForSearchItem" >Territory Name(*)</ezf:anchor></td>
											<td><ezf:inputText name="orgNm_H" ezfName="orgNm_H" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
											<td class="stab">Value From</td>
											<td><ezf:inputText name="trtyRuleFromValTxt_H" ezfName="trtyRuleFromValTxt_H" otherAttr=" size=\"30\" maxlength=\"10\" ezftoupper=\"\""/></td>
											<td class="stab">Value To</td>
											<td><ezf:inputText name="trtyRuleThruValTxt_H" ezfName="trtyRuleThruValTxt_H" otherAttr=" size=\"30\" maxlength=\"10\" ezftoupper=\"\""/></td>
											<td  align="center">
												<ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" />
											</td>
										</tr>
								</table>

								<br>

								<table border="0" cellpadding="0" cellspacing="0">
									<col align="left" valign="top">
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed; width:1100 ">
												<col align="center" width="30">
												<col align="center" width="160">
												<col align="center" width="250">
												<col align="center" width="80">
												<col align="center" width="100">
												<col align="center" width="70">
												<col align="center" width="90">
												<col align="center" width="90">
												<col align="center" width="50">
												<col align="center" width="90">
												<col align="center" width="90">
												<tr height="36">
													<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_DH" ezfName="xxChkBox_DH" value="Y" onClick="sendServer('OnChange_ChkBox_SelectUnSelectAll')" /></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'orgNm_A1')">Territory Name<img id="sortIMG.orgNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'orgDescTxt_A1')">Territory Description<img id="sortIMG.orgDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bizAreaOrgDescTxt_A1')">Business<br>Area<img id="sortIMG.bizAreaOrgDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'trtyGrpTpDescTxt_A1')">Territory<br>Group<img id="sortIMG.trtyGrpTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'trtyRuleOprdTpDescTxt_A1')">Operator<img id="sortIMG.trtyRuleOprdTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'trtyRuleFromValTxt_A1')">Value From<img id="sortIMG.trtyRuleFromValTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'trtyRuleThruValTxt_A1')">Value To<img id="sortIMG.trtyRuleThruValTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'trtyRuleLogicTpDescTxt_A1')">Logic<img id="sortIMG.trtyRuleLogicTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effFromDt_A1')">Effective<br>Date From<img id="sortIMG.effFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effThruDt_A1')">Effective<br>Date To<img id="sortIMG.effThruDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
											</table>
											<div style="overflow-x:hidden; overflow-y:scroll; width:1119; height:370px;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A" >
													<col width="30" align="center">
													<col width="160">
													<col width="250">
													<col width="80">
													<col width="100">
													<col width="70">
													<col width="90">
													<col width="90">
													<col width="50">
													<col width="90" align="center">
													<col width="90" align="center">
													<ezf:row ezfHyo="A">
													<tr>
														<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A_#{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:inputText name="orgNm_A1" ezfName="orgNm_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="orgDescTxt_A1" ezfName="orgDescTxt_A1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td class="stab"><ezf:label name="bizAreaOrgDescTxt_A1" ezfName="bizAreaOrgDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td class="stab"><ezf:label name="trtyGrpTpDescTxt_A1" ezfName="trtyGrpTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td class="stab"><ezf:label name="trtyRuleOprdTpDescTxt_A1" ezfName="trtyRuleOprdTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td class="stab"><ezf:label name="trtyRuleFromValTxt_A1" ezfName="trtyRuleFromValTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td class="stab"><ezf:label name="trtyRuleThruValTxt_A1" ezfName="trtyRuleThruValTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td class="stab"><ezf:label name="trtyRuleLogicTpDescTxt_A1" ezfName="trtyRuleLogicTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td class="stab"><ezf:label name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td class="stab"><ezf:label name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table><br>
								<table style="table-layout:fixed;">
									<col width="110">
									<col width="420">
									<col width="130">
									<col width="430">
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_TrtyNmForMoveTo" >Move Postal Code To</ezf:anchor></td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
												<col width="220">
												<col width="33">
												<col width="117">
												<tr>
													<td class="stab"><ezf:inputText name="orgNm_DC" ezfName="orgNm_DC" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
													<td class="stab"><ezf:inputButton name="GetOrgCd" value=">>" htmlClass="pBtn0" otherAttr=" id=\"GetOrgCd\""/></td>
													<td class="stab"><ezf:inputText name="orgCd_DC" ezfName="orgCd_DC" value="12345678" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
												</tr>
											</table>
										</td>
										<td>&nbsp;</td>
										<td  align="right">
											<ezf:inputButton name="MoveWin_CsvUpload" value="Upload" htmlClass="pBtn8" />
										</td>
									</tr>
									<tr>
										<td class="stab">Move Effective From</td>
										<td>
											<ezf:inputText name="effFromDt_DC" ezfName="effFromDt_DC" value="99/99/9999" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_DC', 4);" >
										</td>
										<td class="stab">Mass Update Reason<ezf:inputButton name="OpenWin_RqstHist" value="Request History" htmlClass="pBtn9" /></td>
										<td rowspan="2" valign="top">
											<ezf:textArea name="rqstRsltCmntTxt_DC" ezfName="rqstRsltCmntTxt_DC" otherAttr=" rows=\"5\" cols=\"58\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Move Effective To</td>
										<td>
											<ezf:inputText name="effThruDt_DC" ezfName="effThruDt_DC" value="99/99/9999" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_DC', 4);" >
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</center>

<%-- **** Task specific area ends here **** --%>
