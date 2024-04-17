<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170622084549 --%>
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
			<input type="hidden" name="pageID" value="NSAL1140Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supply Watch">
<center>
	<table cellSpacing="0" cellPadding="0" width="100%" border="0">
		<tr>
			<td>
				<%-- ********************** --%>
				<%-- *** Upper Tab Area *** --%>
				<%-- ********************** --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
							<tr>
								<td width="1070px" height="28px" valign="bottom">
									<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
										<tr title="Memo Entry Screen">
											<td width="107px" align="center" class="same">Sply Watch</td>
										</tr>
									</table>
								</td>
								<td width="10px" valign="bottom" align="center">
									<a href="#" id="PrevPageTabIndex"><img id="PrevPageBtn" src="./img/tab/tabbackbutton.png" alt="Prev" border="0" onclick="prevTabPage()"></a>
								</td>
								<td width="10px" valign="bottom" align="center">
									<a href="#" id="NextPageTabIndex"><img id="NextPageBtn" src="./img/tab/tabnextbutton.png" alt="Next" border="0" onclick="nextTabPage()"></a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
				<div class="pTab_BODY">
<!-- ################################################## Search Criteria - [START] ################################################## -->
					<table style="margin-top: 0px; margin-left: 5px" border="0" width = "1050">
						<tr>
							<td>
								<table style="margin-top: 0px; margin-left: 5px" cellSpacing="0" cellPadding="1" border="0">
									<colgroup>
										<col width="100px">
										<col width="190px">
										<col width="150px">
										<col width="170px">
										<col width="250px">
										<col width="80px">
										<col>
									</colgroup>
									<tbody>
										<tr height='25px'>
											<td class="stab">Bucket</td>
											<td>
												<ezf:select name="abuseBcktCd_1V" ezfName="abuseBcktCd_1V" ezfBlank="1" ezfCodeName="abuseBcktCd_1C" ezfDispName="abuseBcktDescTxt_1D" otherAttr=" id=\"abuseBcktCd01\""/>
											</td>
											<td class="stab">
												Enforcement Flag&nbsp;
												<ezf:select name="abuseFlg_1V" ezfName="abuseFlg_1V" ezfBlank="1" ezfCodeName="abuseFlg_1C" ezfDispName="abuseFlg_1D" otherAttr=" id=\"abuseFlg_01\""/>
											</td>
											<td class="stab">
												Override Flag&nbsp;
												<ezf:select name="ovwrtAbuseFlg_1V" ezfName="ovwrtAbuseFlg_1V" ezfBlank="1" ezfCodeName="ovwrtAbuseFlg_1C" ezfDispName="ovwrtAbuseFlg_1D" otherAttr=" id=\"ovwrtAbuseFlg_01\""/>
											</td>
											<td class="stab">
												<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="openWinBranch" >Branch</ezf:anchor>&nbsp;
												<ezf:inputText name="svcContrBrCd" ezfName="svcContrBrCd" value="XXXXXXX" otherAttr=" size=\"10\" maxlength=\"3\""/>
												<!--<select name="psnCd_H3" ezfname="psnCd_H3" ><option>XXX</option><option>.XXXXXXX</option></select>
												-->
											</td>
											<td class="stab">Contracts EDI</td>
											<td>
												<ezf:select name="dsContrEdiCd_1V" ezfName="dsContrEdiCd_1V" ezfBlank="1" ezfCodeName="dsContrEdiCd_1C" ezfDispName="dsContrEdiDescTxt_1D" />
											</td>
										</tr>
										<tr height='25px'>
											<td class="stab">
												<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="openWinCustomer" >Customer#</ezf:anchor>
											</td>
											<td>
												<ezf:inputText name="shipToCustAcctCd_01" ezfName="shipToCustAcctCd_01" value="XXXXXXX" otherAttr=" size=\"10\" maxlength=\"20\""/>
											</td>
											<td>&nbsp;</td>
											<td class="stab">
												Customer Profile Name(*)
											</td>
											<td>
												<ezf:inputText name="dsAcctGrpNm" ezfName="dsAcctGrpNm" value="XXXXXXXXX1" otherAttr=" size=\"30\" maxlength=\"50\""/>
											</td>
											<td class="stab">
												<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="openWinContract" >Contract#</ezf:anchor>
											</td>
											<td>
												<ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="9999999" otherAttr=" size=\"10\" maxlength=\"28\""/>
											</td>
										</tr>
										<tr>
											<td class="stab">
												<ezf:anchor name="dsAcctNm_AR" ezfName="dsAcctNm_AR" ezfEmulateBtn="1" ezfGuard="openWinCustomer" >Customer Name(*)</ezf:anchor>
											</td>
											<td colspan = "2">
												<ezf:inputText name="dsAcctNm_01" ezfName="dsAcctNm_01" value="XXXXXXXXX1" otherAttr=" size=\"35\" maxlength=\"50\""/>
											</td>
											<td class="stab">
												Date&nbsp;From&nbsp;
												<ezf:inputText name="procDt_01" ezfName="procDt_01" value="99/99/9999" otherAttr=" size=\"12\" maxlength=\"10\""/>
												<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('procDt_01', 4);">
											</td>
											<td colspan = "2" class="stab">
												Date&nbsp;To&nbsp;
												<ezf:inputText name="procDt_02" ezfName="procDt_02" value="99/99/9999" otherAttr=" size=\"12\" maxlength=\"10\""/>
												<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('procDt_02', 4);">
											</td>
											<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
					<hr />
					<table style="margin-top: 0px; margin-left: 5px" border="0" width = "1100px">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
												<col width="205px">
												<col width="120px">
												<col width="220px">
												<col width="110px">
												<col width="220px">
												<col width="90px">
												<col width="90px">
												<tr>
													<td class="stab">&nbsp;</td>
													<td class="stab">Saved Search Options</td>
													<td><ezf:select name="srchOptPk_1V" ezfName="srchOptPk_1V" ezfBlank="1" ezfCodeName="srchOptPk_1C" ezfDispName="srchOptNm_1D" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:200px;\""/></td>
													<td class="stab">New Search Options</td>
													<td class="stab"><ezf:inputText name="srchOptNm_02" ezfName="srchOptNm_02" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\""/></td>
													<td><ezf:inputButton name="SaveSrchOpt" value="Save Search" ezfHyo="SaveSrchOpt" ezfArrayNo="0" htmlClass="pBtn7" /></td>
													<td><ezf:inputButton name="DelSrchOpt" value="Delete Search" ezfHyo="DelSrchOpt" ezfArrayNo="0" htmlClass="pBtn7" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<!-- ################################################## Search Criteria - [E N D] ################################################## -->
					<hr />
<!-- ################################################## Search Result   - [START] ################################################## -->
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" >
						<tr>
							<td align = "left">
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; width:1080px;">
								<tr >
									<td align = "left">
										<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; height:30px;">
											<col width="180">
											<col width="100">
											<tr height='25px'>
												<td class="stab">
													<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" />
													<ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn6" />
												</td>
												<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
											</tr>
										</table>
									</td>
									<td align="right">
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
													<td class="pOut">200</td>
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
								</tr>
								</table>
							</td>
						</tr>
					</table>

					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" >
						<tr>
							<td valign="top">
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:180px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
								<div id='rightTblHead' style="width:1085px; display:block; overflow:hidden; margin:0px; padding:0px;">
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="2024px" style="margin-right:20px">
										<colgroup>
											<col align="center" width="24px"  ><!-- CheckBox -->
											<col align="center" width="50px"><!-- Supply EnforceDate -->
											<col align="center" width="50px"><!-- Overwrite EnforceDate -->
											<col align="center" width="100px"><!-- Overwrite Reason -->
											<col align="center" width="90px"><!-- Other Contract On Enforcement -->
											<col align="center" width="80px"><!-- Process Date -->
											<col align="center" width="50px"><!-- Term & Condition -->
											<col align="center" width="80px"><!-- Term & Condition Date -->
											<col align="center" width="150px"><!-- Customer# -->
											<col align="center" width="100px"><!-- Customer Name -->
											<col align="center" width="110px"><!-- Profile Name -->
											<col align="center" width="100px"><!-- Contract# -->
											<col align="center" width="100px"><!-- Branch -->
											<col align="center" width="100px"><!-- Region -->
											<col align="center" width="100px"><!-- Contr EDI -->
											<col align="center" width="100px"><!-- Service Program -->
											<col align="center" width="90px"><!-- Active Machines -->
											<col align="center" width="80px"><!-- Usage Bill Cycle -->
											<col align="center" width="80px"><!-- Contract Start Date -->
											<col align="center" width="120px"><!-- Total Allowed -->
											<col align="center" width="120px"><!-- Total Used -->
											<col align="center" width="80px"><!-- Variance% -->
											<col align="center" width="80px"><!-- Current -->
										</colgroup>
										<tbody>
											<tr align="center" height='45px'>
												<td id="AH0" class="pClothBs colFix"></td>
											<!-- Supply EnforceDate -->
												<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'ovwrtAbuseFlg_A')">Supply<br>Enfc<img id="sortIMG.abuseFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Overwrite EnforceDate -->
												<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'ovwrtAbuseFlg_A')">Ovrwrt<br>Enfc<img id="sortIMG.ovwrtAbuseFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Overwrite Reason -->
												<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'abuseOvwrtRsnDescTxt_A')">Overwrite<br>Reason<img id="sortIMG.abuseOvwrtRsnDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Other Contract On Enforcement -->
												<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'othContrAbuseFlg_A')">Other&nbsp;Contract<br>Enforce<img id="sortIMG.othContrAbuseFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Process Date -->
												<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'procDt_A')">Process<br>Date<img id="sortIMG.procDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Term & Condition -->
												<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'termCondChkFlg_A')">T&nbsp;&amp;&nbsp;C<img id="sortIMG.termCondChkFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Term & Condition Date -->
												<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'termCondChkDt_A')">T&nbsp;&amp;&nbsp;C<br>Date<img id="sortIMG.termCondChkDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Customer# -->
												<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'shipToCustAcctCd_A')">Cust#<img id="sortIMG.shipToCustAcctCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Customer Name -->
												<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A')">Cust&nbsp;Name<img id="sortIMG.dsAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Profile Name -->
												<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctGrpDescTxt_A')">Profile Name<img id="sortIMG.dsAcctGrpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Contract# -->
												<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')">Contract#<img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Branch -->
												<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcContrBrDescTxt_A')">Branch<img id="sortIMG.svcContrBrDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Region -->
												<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcRgDescTxt_A')">Region<img id="sortIMG.svcRgDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Contr EDI -->
												<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrEdiDescTxt_A')">Contr<br>EDI<img id="sortIMG.dsContrEdiDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Service Program -->
												<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcCovTmplTpDescTxt_A')">Service<br>Program<img id="sortIMG.svcCovTmplTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Active Machines -->
												<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'noMainUnitCnt_A')">Active<br>Machines<img id="sortIMG.noMainUnitCnt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Usage Bill Cycle -->
												<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'mtrBllgCycleCd_A')">Usage<br>Bill&nbsp;Cycle<img id="sortIMG.mtrBllgCycleCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Contract Start Date -->
												<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrVrsnEffFromDt_A')">Contract<br>Start&nbsp;Date<img id="sortIMG.contrVrsnEffFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Total Allowed -->
												<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxTotBaseAmt_A')">Total<br>Allowed<img id="sortIMG.xxTotBaseAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Total Used -->
												<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxUsedQty_A')">Total<br>Used<img id="sortIMG.xxUsedQty_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Variance% -->
												<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'abuseVarPct')">Variance%<br>(Used/Allw)<img id="sortIMG.abuseVarPct" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<!-- Current -->
												<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'abuseBcktCd_A')">Current<img id="sortIMG.abuseBcktCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											</tr>
										</tbody>
									</table>
								</div>

								<div id="rightTbl" style="width:1102px; height:202px; display:block; overflow:scroll; margin:0px; padding:0px;">
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="2024px">
										<colgroup>
											<col align="left" width="24px"  ><!-- CheckBox -->
											<col align="left" width="50px"><!-- Supply EnforceDate -->
											<col align="left" width="50px"><!-- Overwrite EnforceDate -->
											<col align="left" width="100px"><!-- Overwrite Reason -->
											<col align="left" width="90px"><!-- Other Contract On Enforcement -->
											<col align="left" width="80px"><!-- Process Date -->
											<col align="left" width="50px"><!-- Term & Condition -->
											<col align="left" width="80px"><!-- Term & Condition Date -->
											<col align="left" width="150px"><!-- Customer# -->
											<col align="left" width="100px"><!-- Customer Name -->
											<col align="left" width="110px"><!-- Profile Name -->
											<col align="left" width="100px"><!-- Contract# -->
											<col align="left" width="100px"><!-- Branch -->
											<col align="left" width="100px"><!-- Region -->
											<col align="left" width="100px"><!-- Contr EDI -->
											<col align="left" width="100px"><!-- Service Program -->
											<col align="right" width="90px"><!-- Active Machines -->
											<col align="left" width="80px"><!-- Usage Bill Cycle -->
											<col align="left" width="80px"><!-- Contract Start Date -->
											<col align="right" width="120px"><!-- Total Allowed -->
											<col align="right" width="120px"><!-- Total Used -->
											<col align="right" width="80px"><!-- Variance% -->
											<col align="left" width="80px"><!-- Current -->
										</colgroup>
										<tbody>
											<ezf:row ezfHyo="A">
												<tr align="center" height='23px'>
													<!-- CheckBox -->
													<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServerForPreferredView('ClickCheckbox','{EZF_ROW_NUMBER}')" otherAttr=" id=\"xxChkBox_A_#{EZF_ROW_NUMBER}\""/></td>
													<!-- Supply EnforceDate -->
													<td class="stab"><ezf:label name="abuseFlg_A" ezfName="abuseFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Overwrite EnforceDate -->
													<td class="stab"><ezf:label name="ovwrtAbuseFlg_A" ezfName="ovwrtAbuseFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Overwrite Reason -->
													<td class="stab"><ezf:inputText name="abuseOvwrtRsnDescTxt_A" ezfName="abuseOvwrtRsnDescTxt_A" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<!-- Other Contract On Enforcement -->
													<td class="stab"><ezf:label name="othContrAbuseFlg_A" ezfName="othContrAbuseFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Process Date -->
													<td class="stab"><ezf:label name="procDt_A" ezfName="procDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Term & Condition -->
													<td class="stab"><ezf:label name="termCondChkFlg_A" ezfName="termCondChkFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Term & Condition Date -->
													<td class="stab"><ezf:label name="termCondChkDt_A" ezfName="termCondChkDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Customer# -->
													<td class="stab"><ezf:anchor name="shipToCustAcctCd_LK" ezfName="shipToCustAcctCd_LK" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="moveToSupplyWatchUsedAllowed','{EZF_ROW_NUMBER}" otherAttr=" ezfanchortext=\"\""><ezf:label name="shipToCustAcctCd_A" ezfName="shipToCustAcctCd_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
													<!-- Customer Name -->
													<td class="stab"><ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<!-- Profile Name -->
													<td class="stab"><ezf:inputText name="dsAcctGrpDescTxt_A" ezfName="dsAcctGrpDescTxt_A" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<!-- Contract# -->
													<td class="stab"><ezf:anchor name="dsContrNum_LK" ezfName="dsContrNum_LK" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="moveToSupplyWatchNotesAction','{EZF_ROW_NUMBER}" otherAttr=" ezfanchortext=\"\""><ezf:label name="dsContrNum_A" ezfName="dsContrNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
													<!-- Branch -->
													<td class="stab"><ezf:inputText name="svcContrBrDescTxt_A" ezfName="svcContrBrDescTxt_A" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<!-- Region -->
													<td class="stab"><ezf:inputText name="svcRgDescTxt_A" ezfName="svcRgDescTxt_A" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<!-- Contr EDI -->
													<td class="stab"><ezf:inputText name="dsContrEdiDescTxt_A" ezfName="dsContrEdiDescTxt_A" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<!-- Service Program -->
													<td class="stab"><ezf:inputText name="svcCovTmplTpDescTxt_A" ezfName="svcCovTmplTpDescTxt_A" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;\""/></td>
													<!-- Active Mahicnes -->
													<td class="stab"><ezf:label name="noMainUnitCnt_A" ezfName="noMainUnitCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Usage Bill Cycle -->
													<td class="stab"><ezf:inputText name="bllgCycleDescTxt_A" ezfName="bllgCycleDescTxt_A" value="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none; background-color:transparent;\""/></td>
													<!-- Contract Start Date -->
													<td class="stab"><ezf:label name="contrVrsnEffFromDt_A" ezfName="contrVrsnEffFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Total Allowed -->
													<td class="stab"><ezf:label name="xxTotBaseAmt_A" ezfName="xxTotBaseAmt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Total Used -->
													<td class="stab"><ezf:label name="xxUsedQty_A" ezfName="xxUsedQty_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Variance% -->
													<td class="stab"><ezf:label name="abuseVarPct_A" ezfName="abuseVarPct_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<!-- Current -->
													<td class="stab"><ezf:label name="abuseBcktCd_A" ezfName="abuseBcktCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
												</tr>
											</ezf:row>
											<ezf:skip>
											</ezf:skip>
										</tbody>
									</table>
								</div> <!-- right table -->
							</td>
						</tr>
					</table>
					<hr />

<!-- ################################################## Search Result   - [E N D] ################################################## -->
					<table style="margin-top: 0px; margin-left: 5px" border="0">
						<col width = "300px">
						<col width = "400px">
						<col width = "300px">
						<tr height="120px">
							<td>
								<fieldset>
									<legend style="font-size:12px;" class="stab">Contract Status</legend>
									<table style="table-layout:fixed;height:59px;" border="0" cellpadding="0" cellspacing="0" >
										<col width = "150px">
										<col width = "150px">
										<tr>
											<td class="stab">
												Status
												<ezf:select name="svcSplyContrStsCd_1V" ezfName="svcSplyContrStsCd_1V" ezfBlank="1" ezfCodeName="svcSplyContrStsCd_1C" ezfDispName="svcSplyContrStsDescTxt_1D" otherAttr=" style=\"width:100px;\""/>
											</td>
											<td class="stab">
												Term&nbsp;&amp;&nbsp;Conditions
												<ezf:inputCheckBox name="xxChkBox_F" ezfName="xxChkBox_F" value="Y" />
											</td>
										<tr>
											<td class="stab">
												Status&nbsp;History
											</td>
										</tr>
										<tr>
											<td class="stab">&nbsp;Status</td>
											<td class="stab">&nbsp;date</td>
										</tr>
									</table>
									<div style="width:300px; height:50px; display:block; overflow:scroll; margin:0px; padding:0px;">
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="170px" id="B">
										<colgroup>
											<col width = "150px">
											<col width = "120px">
										</colgroup>
										<tbody>
											<ezf:row ezfHyo="B">
												<tr>
													<td class="stab"><ezf:label name="svcSplyContrStsDescTxt_B" ezfName="svcSplyContrStsDescTxt_B" ezfHyo="B" ezfArrayNo="0" /></td>
													<td class="stab"><ezf:label name="procDt_B" ezfName="procDt_B" ezfHyo="B" ezfArrayNo="0" /></td>
												</tr>
											</ezf:row>
											<ezf:skip>
											<tr>
												<td class="stab"><label ezfout>XXXXX</label></td>
												<td class="stab"><label ezfout>XX/XX/XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout>XXXXX</label></td>
												<td class="stab"><label ezfout>XX/XX/XXXX</label></td>
											</tr>
											<tr>
												<td class="stab"><label ezfout>XXXXX</label></td>
												<td class="stab"><label ezfout>XX/XX/XXXX</label></td>
											</tr>
											</ezf:skip>
										</tbody>
									</table>
								</fieldset>
							</td>
							<td>
								<fieldset>
									<legend style="font-size:12px;" class="stab">Enforcement&nbsp;Override</legend>
									<table style="table-layout:fixed;height:110px;width:400px;" border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td class="stab">
												Overwrite&nbsp;Enforcement
												<ezf:select name="ovwrtAbuseFlg_2V" ezfName="ovwrtAbuseFlg_2V" ezfBlank="1" ezfCodeName="ovwrtAbuseFlg_2C" ezfDispName="ovwrtAbuseFlg_2D" />
											</td>
											<td class="stab">
												Overwrite&nbsp;Reason
												<ezf:select name="abuseOvwrtRsnCd_1V" ezfName="abuseOvwrtRsnCd_1V" ezfBlank="1" ezfCodeName="abuseOvwrtRsnCd_1C" ezfDispName="abuseOvwrtRsnNm_1D" otherAttr=" style=\"width:60px\""/>
											</td>
										</tr>
										<tr>
											<td class="stab">
												Approver
												<ezf:select name="psnCd_1V" ezfName="psnCd_1V" ezfBlank="1" ezfCodeName="psnCd_1C" ezfDispName="fullPsnNm_1D" otherAttr=" style=\"width:100px\""/>
											</td>
											<td class="stab">
												Expiration&nbsp;Date
												<ezf:inputText name="svcSplyExprDt" ezfName="svcSplyExprDt" value="99/99/9999" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('svcSplyExprDt', 4);">
											</td>
										</tr>
										<tr>
											<td class="stab">
												CAP%
												<ezf:inputText name="abuseVarPct" ezfName="abuseVarPct" value="XXXXXXX" otherAttr=" size=\"10\" maxlength=\"7\""/>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
							<td>
								<fieldset>
									<legend style="font-size:12px;" class="stab">Notes</legend>
									<table style="table-layout:fixed;height:110px;width:300px;"  height="100px">
										<tr>
											<td class="stab">
												Action
												<ezf:select name="abuseActCd_1V" ezfName="abuseActCd_1V" ezfBlank="1" ezfCodeName="abuseActCd_1C" ezfDispName="abuseActDescTxt_1D" otherAttr=" style=\"width:150px;\""/>
											</td>
										</tr>
										<tr>
											<td>
												<ezf:textArea name="abuseActCmntTxt" ezfName="abuseActCmntTxt" otherAttr=" style=\"height:75px;width:280px;\""/>
											</td>
										</tr>
									</table>
								</fieldset>
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


<%-- **** Task specific area ends here **** --%>
