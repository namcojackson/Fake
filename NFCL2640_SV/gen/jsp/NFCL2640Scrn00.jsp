<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160329121901 --%>
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
			<input type="hidden" name="pageID" value="NFCL2640Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Statement Schedule Maintenance">

<center>
	<table border="0" cellpadding="1" cellspacing="0" width="100%">
		<tr>
			<td>

<%-- ######################################## HEADER ######################################## --%>
<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Location Info" class="pTab_ON"><a href="#">StmtSche Mnt</a></li>
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
					<table border="0" cellpadding="1" cellspacing="0" height = "70"style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px">
						<col width="120" align="left">
						<col width="250" align="left">
						<col width="100" align="left">
						<col width="200" align="left">
						<col width="380" align="right">
						<tr>
							<td class="stab">Cycle</td>
							<td>
								<div>
									<ezf:select name="arStmtIssCycleCd" ezfName="arStmtIssCycleCd" ezfCodeName="arStmtIssCycleCd_P" ezfDispName="arStmtIssCycleNm_P" otherAttr=" style=\"width:110px\""/>
								</div>
							</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td class="stab">Statement Date</td>
							<td colspan="3">
								<ezf:inputText name="arStmtDt_FR" ezfName="arStmtDt_FR" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('arStmtDt_FR', 4);" >
								-
								<ezf:inputText name="arStmtDt_TO" ezfName="arStmtDt_TO" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('arStmtDt_TO', 4);" >
							<td>
						</tr>
						<tr>
							<td class="stab">Print Status</td>
							<td>
								<div>
									<ezf:select name="arStmtStsCd" ezfName="arStmtStsCd" ezfBlank="1" ezfCodeName="arStmtStsCd_P" ezfDispName="arStmtStsNm_P" otherAttr=" style=\"width:110px\""/>
								</div>
							</td>
							<td></td>
							<td></td>
							<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
						</tr>
					</table>

					<hr>

	<%-- ######################################## DETAIL ######################################## --%>

					<table border="0" cellpadding="0" cellspacing="0" style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px">
						<col width="450" align="left">
						<col width="60" align="left">	<!-- Bulk Create -->
						<col width="40" align="left">
						<col width="80" align="left">	<!-- Statement Date -->
						<col width="120" align="left">
						<col width="100" align="left">
						<col width="129" align="left">
						<col width="80" align="right">
						<tr>
							<td></td>
							<td class="stab">Bulk Create</td>
							<td><ezf:inputCheckBox name="xxChkBox_BC" ezfName="xxChkBox_BC" value="Y" /></td>
							<td class="stab">Statement Date</td>
							<td>
								<ezf:inputText name="arStmtDt_AD" ezfName="arStmtDt_AD" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('arStmtDt_AD', 4);" >
							</td>
							<td class="stab">Late Fee Calculation</td>
							<td>
									<ezf:inputCheckBox name="lateFeeCalcFlg" ezfName="lateFeeCalcFlg" value="Y" />
							</td>
							<td><ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" /></td>
						</tr>
					</table>
					
					<br>

					<div id="topRightTBL" style="MARGIN-LEFT: 20px; overflow-y:none; height:28; overflow-x:hidden; width:1060;">
						<table border="1" cellpadding="0" cellspacing="0">
							<col width="210"  align="center">
							<col width="210"  align="center">
							<col width="210"  align="center">
							<col width="210"  align="center">
							<col width="210"  align="center">
							<tr height="28" style="font-size:8pt;">
								<td class="pClothBs">Statement Date</td>
								<td class="pClothBs">Print Status</td>
								<td class="pClothBs">Data Created Date</td>
								<td class="pClothBs">Print Requested Date</td>
								<td class="pClothBs">Late Fee Calculation</td>
							</tr>
						</table>
					</div>
					
					<div id="RightTBL" style="MARGIN-LEFT: 20px; overflow-y:scroll; height:422; overflow-x:hidden; width:1077;">
						<table border="1" cellpadding="0" cellspacing="0" id="A1">
							<col width="210"  align="center">
							<col width="210"  align="center">
							<col width="210"  align="center">
							<col width="210"  align="center">
							<col width="210"  align="center">
							<ezf:row ezfHyo="A">
								<tr height="28" style="font-size:8pt;">
									<td align="center"><ezf:label name="arStmtDt_A1" ezfName="arStmtDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td align="center">
										<ezf:select name="arStmtStsCd_A1" ezfName="arStmtStsCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="arStmtStsCd_P" ezfDispName="arStmtStsNm_P" otherAttr=" style=\"width:120px\""/>
									</td>
									<td align="center"><ezf:label name="arStmtDataCratDt_A1" ezfName="arStmtDataCratDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td align="center"><ezf:label name="arStmtPrintDt_A1" ezfName="arStmtPrintDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td align="center">
										<ezf:inputCheckBox name="lateFeeCalcFlg_A1" ezfName="lateFeeCalcFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
									</td>
								</tr>
							</ezf:row>
							
							<ezf:skip>
								<tr height="28" class="pEvenNumberBGcolor" >
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
								<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
								<tr height="28" class="pEvenNumberBGcolor" >
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
								<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
																<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
																<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
																<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
																<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
																<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
																<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
																<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
																<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
																<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
																<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
																<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
																<tr height="28">
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<select class="pHsu" name="RefundReasonCd" style="width:120px" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
											<option>Pending
											<option>Printed
											<option>Skip
										</select>
									</td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center"><label>WWWWWWWWW1</label></td>
									<td align="center">
										<input type="checkbox" name="RefundReasonCd" ezfname="RefundTypeCd_P1" ezflist="RefundReasonCd,RefundReasonTxt,99, ,blank">
									</td>
								</tr>
							</ezf:skip>
							
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>
</center>



<%-- **** Task specific area ends here **** --%>
