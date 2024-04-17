<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170530121216 --%>
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
			<input type="hidden" name="pageID" value="NSAL0870Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Meter Interface Status Inquiry">
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Meter Interface Status Inquiry" class="pTab_ON"><a href="#">Mtr Intfc</a></li>
									</div>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<div class="pTab_BODY">
<!-- ################################################## Search Criteria - [START] ################################################## -->
					<table width="99%"  style="margin-top: 0px; margin-left: 5px" border="0">
						<tr>
							<td>
								<table cellSpacing="0" cellPadding="1" border="0">
									<colgroup>
										<col width="100px">
										<col width="120px">
										<col width="20px">
										<col width="120px">
										<col width="120px">
										<col width="10px">
										<col width="10px">
										<col width="120px">
										<col width="420px">
										<col width="50px">
										<col width="5px">
									</colgroup>
									<tbody>
										<tr>
											<td class="stab"><Div Align="left">Meter Source</Div></td>
											<td>
												<ezf:select name="mtrReadSrcTpCd_1V" ezfName="mtrReadSrcTpCd_1V" ezfBlank="1" ezfCodeName="mtrReadSrcTpCd_1C" ezfDispName="mtrReadSrcTpDescTxt_1D" />
											</td>
											<td></td>
											<td><Div Align="left"><ezf:anchor name="dsAcctNum_AR" ezfName="dsAcctNum_AR" ezfEmulateBtn="1" ezfGuard="OpenWin_Serial" >Serial#</ezf:anchor></Div></td>
											<td>
												<ezf:inputText name="serNum_01" ezfName="serNum_01" value="9999999" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td class="stab"><Div Align="left">Status</Div></td>
											<td>
												<ezf:select name="dsMtrProcStsCd_1V" ezfName="dsMtrProcStsCd_1V" ezfBlank="1" ezfCodeName="dsMtrProcStsCd_1C" ezfDispName="dsMtrProcStsDescTxt_1D" />
											</td>
											<td></td>
											<td><Div Align="left">Meter Read Date</Div></td>
											<td>
												<ezf:inputText name="mtrReadDt_FR" ezfName="mtrReadDt_FR" value="99/99/9999" otherAttr=" size=\"12\" maxlength=\"10\""/>
												<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrReadDt_FR', 4);">
											</td>
											<td>-</td>
											<td></td>
											<td>
												<ezf:inputText name="mtrReadDt_TO" ezfName="mtrReadDt_TO" value="99/99/9999" otherAttr=" size=\"12\" maxlength=\"10\""/>
												<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('mtrReadDt_TO', 4);">
											</td>
											<td></td>
											<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn4" /></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
<!-- ################################################## Search Criteria - [E N D] ################################################## -->
					<hr />
<!-- ################################################## Search Result   - [START] ################################################## -->
					<table width="99%">
						<tr align="right">
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
									<col width="200">
									<col width="40">
									<col width="218">
									<col width="120">
									<col width="520">
									<tr height='25px'>
										<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td align="right">
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- ######################################## DETAIL ######################################## --%>
				<table>
						<tr>
							<td>
							
									<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:420px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1098px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1420px" style="margin-right:20px">
												
													<col align="center" width="120px"><!-- Serial# -->
													<col align="center" width="180px"><!-- Model Name -->
													<col align="center" width="180px"><!-- Branch -->
													<col align="center" width="100px"><!-- Meter Source -->
													<col align="center" width="100px"><!-- Meter Date -->
													<col align="center" width="100px"><!-- Number of Reads -->
													<col align="center" width="240px"><!-- Message -->
													<col align="center" width="100px"><!-- Status -->
													<col align="center" width="100px"><!-- Process Date -->
													<col align="center" width="100px"><!-- Process Time -->
													<col align="center" width="100px"><!-- Exec User ID -->
												
												
													<tr align="center" height='25px'>
														<td id="AH0" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')">Serial#<img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 't_MdlNm_A')">Model Name<img id="sortIMG.t_MdlNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcContrBrDescTxt_A')">Branch<img id="sortIMG.svcContrBrDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrReadSrcTpDescTxt_A')">Meter Source<img id="sortIMG.mtrReadSrcTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrReadDt_A')">Meter Date<img id="sortIMG.mtrReadDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrCnt_A')">Number of Reads<img id="sortIMG.mtrCnt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsMsgTxt_A')">Message<img id="sortIMG.dsMsgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsMtrProcStsDescTxt_A')">Status<img id="sortIMG.dsMtrProcStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsMtrProcTs_A1')">Process Date<img id="sortIMG.dsMtrProcTs_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsMtrProcTs_A2')">Process Time<img id="sortIMG.dsMtrProcTs_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rgtnUsrId_A')">Exec User ID<img id="sortIMG.rgtnUsrId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
												
											</table>
										</div>
										<div id="rightTbl" style="width:1115px; height:420px; display:block; overflow:scroll; margin:0px; padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1420px">
												
													<col align="center" width="120px"><!-- Serial# -->
													<col align="center" width="180px"><!-- Model Name -->
													<col align="center" width="180px"><!-- Branch -->
													<col align="center" width="100px"><!-- Meter Source -->
													<col align="center" width="100px"><!-- Meter Date -->
													<col align="center" width="100px"><!-- Number of Reads -->
													<col align="center" width="240px"><!-- Message -->
													<col align="center" width="100px"><!-- Status -->
													<col align="center" width="100px"><!-- Process Date -->
													<col align="center" width="100px"><!-- Process Time -->
													<col align="center" width="100px"><!-- Exec User ID -->
													<% int i = 0; %>
												
													<ezf:row ezfHyo="A">
													<% i++; %>
														<tr align="center" height='25px'>
															<td><ezf:inputText name="serNum_A" ezfName="serNum_A" value="---------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="t_MdlNm_A" ezfName="t_MdlNm_A" value="---------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="svcContrBrDescTxt_A" ezfName="svcContrBrDescTxt_A" value="---------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="mtrReadSrcTpDescTxt_A" ezfName="mtrReadSrcTpDescTxt_A" value="---------1---------2---------3---------4---------5---------6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"60\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="mtrReadDt_A" ezfName="mtrReadDt_A" value="---------1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td align="center">
																<ezf:anchor name="serNum_A" ezfName="serNum_AR" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_Meter" otherAttr=" tabindex=\"-1\" id=\"serNum_AR#{EZF_ROW_NUMBER}\"">
																	<ezf:label name="mtrCnt_A" ezfName="mtrCnt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"serNum_A#{EZF_ROW_NUMBER}\""/>
																</ezf:anchor>
															</td>
															<td><ezf:inputText name="dsMsgTxt_A" ezfName="dsMsgTxt_A" value="---------1---------2---------3---------4---------5---------6---------7---------8---------9---------0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"1000\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="dsMtrProcStsDescTxt_A" ezfName="dsMtrProcStsDescTxt_A" value="---------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="xxDtTm_A1" ezfName="xxDtTm_A1" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="xxDtTm_A2" ezfName="xxDtTm_A2" value="HH'MM'SS" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="rgtnUsrId_A" ezfName="rgtnUsrId_A" value="---------1-----6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												
											</table>
										</div>
									</div> <!-- right table -->
								</div> <!--parent box -->
							</td>
						</tr>
					</table>
					</div>
<!-- ################################################## Search Result   - [E N D] ################################################## -->
				</div>
			</td>
		</tr>
	</table>
</center>

<style TYPE="text/css">
<!--
	tr.checkLineBGcolor{background-color:yellow;}
	a img{border-style:none;}
-->
</style>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true);
</script>


<%-- **** Task specific area ends here **** --%>
