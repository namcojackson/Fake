<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160706111953 --%>
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
			<input type="hidden" name="pageID" value="NMAL2620Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Territory Mass Update">
            <jsp:include page="../tab/S21BusinessProcessTAB.jsp"></jsp:include>
<center>
	<div class="pTab_BODY">
		<table width="100%" >
			<tr>
				<td>
<%-- ######################################## HEADER ######################################## --%>
					<%-- ##### BODY(TAB) ##### --%>
					<table style="table-layout:fixed;">
						<col width="100">
						<col width="240">
						<col width="100">
						<col width="240">
						<col width="80">
						<col width="230">
						<col width="90">
							<tr>
								<td class="stab"><Div Align="left">Business Area</Div></td>
								<td><ezf:select name="bizAreaOrgCd_V" ezfName="bizAreaOrgCd_V" ezfBlank="1" ezfCodeName="bizAreaOrgCd_C" ezfDispName="bizAreaOrgDescTxt_D" /></td>
								<td class="stab"><ezf:anchor name="dsAcctNm_LK" ezfName="dsAcctNm_LK" ezfEmulateBtn="1" ezfGuard="LINK_RESRC_NM" >Resource Name(*)</ezf:anchor></td>
								<td><ezf:inputText name="xxPsnNm_H" ezfName="xxPsnNm_H" otherAttr=" size=\"30\" maxlength=\"40\""/></td>
								<td class="stab"><ezf:anchor name="dsAcctNm_LK" ezfName="dsAcctNm_LK" ezfEmulateBtn="1" ezfGuard="LINK_RESRC_NUM" >Resource #(*)</ezf:anchor></td>
								<td><ezf:inputText name="psnNum_H" ezfName="psnNum_H" otherAttr=" size=\"30\" maxlength=\"40\" ezftoupper=\"\""/></td>
								<td  align="center">
									<ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" />
								</td>
							</tr>
							<tr>
								<td class="stab"><ezf:anchor name="dsAcctNm_LK" ezfName="dsAcctNm_LK" ezfEmulateBtn="1" ezfGuard="LINK_TERR_NM" >Territory Name(*)</ezf:anchor></td>
								<td><ezf:inputText name="orgNm" ezfName="orgNm" otherAttr=" size=\"30\" maxlength=\"40\" ezftoupper=\"\""/></td>
								<td class="stab"><ezf:anchor name="dsAcctNm_LK" ezfName="dsAcctNm_LK" ezfEmulateBtn="1" ezfGuard="LINK_EMP_ID" >Employee ID(*)</ezf:anchor></td>	
								<td><ezf:inputText name="psnCd" ezfName="psnCd" otherAttr=" size=\"30\" maxlength=\"40\" ezftoupper=\"\""/></td>
							</tr>
					</table><br>
					<table border="0" cellpadding="0" cellspacing="0">
						<col align="left" valign="top">
						<tr>
							<td>
								<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed; ">
									<col align="center" width="30">
									<col align="center" width="130">
									<col align="center" width="130">
									<col align="center" width="100">
									<col align="center" width="80">
									<col align="center" width="120">
									<col align="center" width="100">
									<col align="center" width="100">
									<col align="center" width="110">
									<col align="center" width="90">
									<col align="center" width="90">
									<tr height="36">
										<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_AL" ezfName="xxChkBox_AL" value="Y" onClick="sendServer('SelectUnselect_All')" /></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'orgNm_A')">Territory Name<img id="sortIMG.orgNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'orgDescTxt_A')">Territory Description<img id="sortIMG.orgDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'bizAreaOrgDescTxt_A')">Business Area<img id="sortIMG.bizAreaOrgDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'lineBizTpCd_A')">Line of Business<img id="sortIMG.lineBizTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxPsnNm_A')">Resource Name<img id="sortIMG.xxPsnNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'psnNum_A')">Resource#<img id="sortIMG.psnNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'orgFuncRoleTpDescTxt_A')">Role<img id="sortIMG.orgFuncRoleTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'acctTeamRoleTpDescTxt_A')">Account Team <br>Role<img id="sortIMG.acctTeamRoleTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effFromDt_A')">Effective <br>Date From<img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'effThruDt_A')">Effective<br> Date To<img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									</tr>
								</table>
								<div style="overflow-x:hidden; width:1100; height:340px; overflow-y:scroll;">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A" >
										<col width="30" align="center">
										<col width="130">
										<col width="130">
										<col width="100">
										<col width="80">
										<col width="120">
										<col width="100">
										<col width="100">
										<col width="110">
										<col width="90" align="center">
										<col width="90" align="center">
										<% int i = 0; %>
										<ezf:row ezfHyo="A">
										<% i++; %>
										<tr>
											<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A_#{EZF_ROW_NUMBER}\""/></td>
											<td class="stab"><ezf:inputText name="orgNm_A" ezfName="orgNm_A" value="territory name XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td class="stab"><ezf:inputText name="orgDescTxt_A" ezfName="orgDescTxt_A" value="Territory Description Text XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td class="stab"><ezf:inputText name="bizAreaOrgDescTxt_A" ezfName="bizAreaOrgDescTxt_A" value="Business Area Description Text XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td class="stab"><ezf:inputText name="lineBizTpCd_A" ezfName="lineBizTpCd_A" value="LINE_BIZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"15\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td class="stab"><ezf:inputText name="xxPsnNm_A" ezfName="xxPsnNm_A" value="Person Name XXXX XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td class="stab"><ezf:inputText name="psnNum_A" ezfName="psnNum_A" value="Person Number XXXX XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td class="stab"><ezf:inputText name="orgFuncRoleTpDescTxt_A" ezfName="orgFuncRoleTpDescTxt_A" value="Org Func Role Type Decription Text XXXX XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td class="stab"><ezf:inputText name="acctTeamRoleTpDescTxt_A" ezfName="acctTeamRoleTpDescTxt_A" value="Acct Team Role Type Decription Text XXXX XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td class="stab"><ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="02/26/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											<td class="stab"><ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="02/26/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
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
							<td class="stab"><Div Align="left">Select Mode</Div></td>
							<td><ezf:select name="trtyUpdModeTpCd_V" ezfName="trtyUpdModeTpCd_V" ezfBlank="1" ezfCodeName="trtyUpdModeTpCd_C" ezfDispName="trtyUpdModeTpDescTxt_D" otherEvent1=" onchange=\"sendServer('SelectActive_notActive')\"" /></td>
						</tr>
						<tr>
							<td class="stab"><ezf:anchor name="psnNum_LK" ezfName="psnNum_LK" ezfEmulateBtn="1" ezfGuard="LINK_MOVE_TERR" otherAttr=" id=\"psnNum_LK\" ezfanchortext=\"\"">Move Territory To</ezf:anchor></td>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
									<col width="260">
									<col width="33">
									<col width="117">
									<tr>
										<td class="stab"><ezf:inputText name="xxPsnNm_D" ezfName="xxPsnNm_D" otherAttr=" size=\"36\" maxlength=\"40\""/></td>
										<td class="stab"><ezf:inputButton name="GetPersonNum" value=">>" htmlClass="pBtn0" otherAttr=" id=\"GetPersonNum\""/></td>
										<td class="stab"><ezf:inputText name="psnNum_D" ezfName="psnNum_D" value="Person Number" otherAttr=" size=\"16\" maxlength=\"50\""/></td>
									</tr>
								</table>
							</td>
							<td class="stab">End Date Territory on</td>
							<td>
								<ezf:inputText name="endDt" ezfName="endDt" value="99/99/9999" otherAttr=" ezftoupper=\"\" size=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('endDt', 4);" >
							</td>
						</tr>
						<tr>
							<td class="stab">Move Effective From</td>
							<td>
								<ezf:inputText name="xxFromDt" ezfName="xxFromDt" value="99/99/9999" otherAttr=" ezftoupper=\"\" size=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt', 4);" >
							</td>
							<td class="stab">Mass Update Reason<ezf:inputButton name="LINK_RQST_HIST" value="Request_History" htmlClass="pBtn9" /></td>
							<td rowspan="2" valign="top">
								<ezf:textArea name="rqstRsltCmntTxt" ezfName="rqstRsltCmntTxt" otherAttr=" rows=\"3\" cols=\"58\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr>
							<td class="stab">Move Effective To</td>
							<td>
								<ezf:inputText name="xxThruDt" ezfName="xxThruDt" value="99/99/9999" otherAttr=" ezftoupper=\"\" size=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxThruDt', 4);" >
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</center>










<%-- **** Task specific area ends here **** --%>
