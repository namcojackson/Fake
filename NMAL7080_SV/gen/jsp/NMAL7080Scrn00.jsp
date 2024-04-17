<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160620175918 --%>
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
			<input type="hidden" name="pageID" value="NMAL7080Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supply Agreement Plan Set Up">
			
<left>
	<table width="1133" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center">
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Supply Agreement Plan Search" class="pTab_ON"><a href="#">AgmtPln Set</a></li>
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
<%------------------------------------%>
<%-- Header							--%>
<%------------------------------------%>
				<table border="0" cellpadding="0" cellspacing="0" height="180" width="1100" rules="none"  style="padding: 10px; margin-bottom: 5px; solid #333333;">
					<tr>
						<td valign="top" width="1100" align="left">
							<table cellpadding="0" border="0">
								<col align="left" width="117">
								<col align="left" width="80">
								<col align="left" width="120">
								<col align="left" width="740">
								<tr>
									<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_PlnId','plnId')">Plan ID</a></td>
									<td><ezf:inputText name="splyAgmtPlnPk" ezfName="splyAgmtPlnPk" value="XXXXXXXXX0XXXXXXXXX0XXXXXXXX" otherAttr=" size=\"10\" maxlength=\"28\""/></td>
									<td class="stab"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" /></td>
									<td> </td>
								</tr>
							</table>
							<table cellpadding="0" border="0">
								<col align="left" width="120">
								<col align="left" width="500">
								<col align="left" width="480">
								<tr>
									<td class="stab">Plan Name</td>
									<td><ezf:inputText name="splyAgmtPlnNm" ezfName="splyAgmtPlnNm" value="XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0" otherAttr=" size=\"66\" maxlength=\"50\" ezftoupper=\"\""/></td>
									<td> </td>
								</tr>
								<tr>
									<td class="stab">Plan Short Name</td>
									<td><ezf:inputText name="splyAgmtPlnShortNm" ezfName="splyAgmtPlnShortNm" value="XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0" otherAttr=" size=\"66\" maxlength=\"50\" ezftoupper=\"\""/></td>
									<td> </td>
								</tr>
								<tr>
									<td class="stab">Description/Notes</td>
									<td><ezf:inputText name="splyAgmtPlnDescTxt" ezfName="splyAgmtPlnDescTxt" value="XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0XXXXXXXXX0" otherAttr=" size=\"66\" maxlength=\"50\" ezftoupper=\"\""/></td>
									<td> </td>
								</tr>
							</table>
							<table cellpadding="0" border="0">
								<col align="left" width="118">
								<col align="left" width="180">
								<col align="left" width="120">
								<col align="left" width="120">
								<tr>
									<td class="stab">Plan Type</td>
									<td>
										<ezf:select name="splyAgmtPlnTpCd" ezfName="splyAgmtPlnTpCd" ezfBlank="1" ezfCodeName="splyAgmtPlnTpCd_P" ezfDispName="splyAgmtPlnTpDescTxt_P" otherAttr=" style=\"width: 110px\""/>
									</td>
									<td class="stab">Effective Date From</td>
									<td class="stab">
										<ezf:inputText name="effFromDt" ezfName="effFromDt" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >
									</td>
								</tr>
								<tr>
									<td class="stab">Document Type</td>
									<td>
										<ezf:select name="splyAgmtDocTpCd" ezfName="splyAgmtDocTpCd" ezfBlank="1" ezfCodeName="splyAgmtDocTpCd_P" ezfDispName="splyAgmtDocTpDescTxt_P" otherAttr=" style=\"width: 250px\""/>
									</td>
									<td class="stab">Effective Date To</td>
									<td class="stab">
										<ezf:inputText name="effThruDt" ezfName="effThruDt" value="00/00/0000" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" >
									</td>
								</tr>
								<tr>
									<td class="stab">Annual Term Cap</td>
									<td>
										<ezf:inputText name="annTermCapNum" ezfName="annTermCapNum" value="XXXXXXXXX" otherAttr=" size=\"11\" maxlength=\"11\""/>
									</td>
									<td class="stab">Active</td>
									<td class="stab">
										<ezf:inputCheckBox name="xxChkBox_AF" ezfName="xxChkBox_AF" value="Y" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<%------------------------------------%>
<%-- Detail							--%>
<%------------------------------------%>
				<table border="0" cellpadding="01" cellspacing="0" width="1085" align="center"  rules="none"  style="padding: 10px; margin-bottom: 10px;">
					<tr align="right">
						<col width="180"  align="left">
						<col width="30"  align="left">
						<col width="100"  align="left">
						<col width="60"  align="left">
						<col width="50"  align="left">
						<col width="70"  align="left">
						<col width="140"  align="left">
						<col width="100"  align="left">
						<col width="80"  align="left">
						<col width="80"  align="left">
						<col width="80"  align="left">
						<td>
							<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
						</td>
						<td class="stab">Show</td>
						<td>
							<ezf:select name="splyAgmtPlnStsCd" ezfName="splyAgmtPlnStsCd" ezfCodeName="splyAgmtPlnStsCd_P" ezfDispName="splyAgmtPlnStsDescTxt_P" otherEvent1=" onchange=\"sendServer('OnChange_Show')\"" otherAttr=" style=\"width: 150px\""/>
						</td>
						<td><ezf:inputCheckBox name="xxChkBox_SD" ezfName="xxChkBox_SD" value="Y" onClick="this.blur();this.focus();" otherEvent1=" onchange=\"sendServer('OnChange_SelectAll')\"" /></td>
						<td class="stab">Item Code</td>
						<td><ezf:inputText name="mdseCd_IC" ezfName="mdseCd_IC" otherAttr=" size=\"12\" maxlength=\"16\" ezftoupper=\"\""/></td>
						<td><ezf:inputButton name="apply" value="Apply" htmlClass="pBtn6" /></td>
						<td>
							<ezf:inputText name="xxDt10Dt_MD" ezfName="xxDt10Dt_MD" value="00/00/0000" otherAttr=" size=\"10\" maxlength=\"10\""/>
							<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxDt10Dt_MD', 4);" >
						</td>
						<td><ezf:inputButton name="massUpdate" value="Mass Update" htmlClass="pBtn7" /></td>
						<td><ezf:inputButton name="insertRow" value="Insert Row" htmlClass="pBtn7" /></td>
						<td><ezf:inputButton name="deleteRow" value="Delete Row" htmlClass="pBtn7" /></td>
					</tr>
				</table>
				<table>
				<tr>
				<td width="3"></td>
				<td>
					<div id="parentBoxA">
						<div style="float:left; display:block"> <!-- left table -->
							<div id="leftTblHead" style="display:block;">
							</div>
							<div id="leftTbl" style="float:left; display:block; height:274px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
							</div>
						</div>  <!-- left table -->
						<div style="float:left"> <!-- right table -->
							<div id="rightTblHead" style="width:1069px; display:block; overflow:hidden; margin:0px;padding:0px;">
								<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="1069px" style="margin-right:20px">
									<col align="center" width="60">		<!-- Delete/Update			-->
									<col align="center" width="150">	<!-- Item Code				-->
									<col align="center" width="157">	<!-- Item Description		-->
									<col align="center" width="200">	<!-- Frequency				-->
									<col align="center" width="100">	<!-- First Quantity			-->
									<col align="center" width="90">	<!-- Quantity				-->
									<col align="center" width="90">	<!-- Sequence #				-->
									<col align="center" width="110">	<!-- Effective Date From	-->
									<col align="center" width="110">	<!-- Effective Date To		-->
									<col align="center" width="100">	<!-- Creation Date 			-->
									<col align="center" width="170">	<!-- Created By				-->
									<col align="center" width="100">	<!-- Last Update Date		-->
									<col align="center" width="170">	<!-- Last Update By			-->
									<tr height="40">
										<td id="AH0"  class="pClothBs">Delete/<br>Update</td>
										<td id="AH1"  class="pClothBs">Item Code</td>
										<td id="AH2"  class="pClothBs">Item Description</td>
										<td id="AH3"  class="pClothBs">Frequency</td>
										<td id="AH4"  class="pClothBs">First Quantity</td>
										<td id="AH5"  class="pClothBs">Quantity</td>
										<td id="AH6"  class="pClothBs">Sequence #</td>
										<td id="AH7"  class="pClothBs">Effective Date<br>From</td>
										<td id="AH8"  class="pClothBs">Effective Date<br>To</td>
										<td id="AH9"  class="pClothBs">Creation Date</td>
										<td id="AH10" class="pClothBs">Created By</td>
										<td id="AH11" class="pClothBs">Last Update Date</td>
										<td id="AH12" class="pClothBs">Last Update By</td>
									</tr>
								</table>
							</div>
							<div id="rightTBL" style="width:1085px; height:291px; display:block; overflow:scroll; margin:0px; padding:0px;"  onScroll="synchroScrollLeft(this.id, new Array('rightTblHead'));">
								<table border="1" cellpadding="0" cellspacing="0" id="A" style="table-layout: fixed" width="1085px">
									<col align="center" width="60">		<!-- Delete/Update			-->
									<col align="center" width="150">	<!-- Item Code				-->
									<col align="center" width="157">	<!-- Item Description		-->
									<col align="center" width="200">	<!-- Frequency				-->
									<col align="center" width="100">	<!-- First Quantity			-->
									<col align="center" width="90">	<!-- Quantity				-->
									<col align="center" width="90">	<!-- Sequence #				-->
									<col align="center" width="110">	<!-- Effective Date From	-->
									<col align="center" width="110">	<!-- Effective Date To		-->
									<col align="center" width="100">	<!-- Creation Date 			-->
									<col align="center" width="170">	<!-- Created By				-->
									<col align="center" width="100">	<!-- Last Update Date		-->
									<col align="center" width="170">	<!-- Last Update By			-->
									<ezf:row ezfHyo="A">
										<tr id="id_leftA_row{EZF_ROW_NUMBER}" height="25">
											<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"16\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Mdse" value="Search" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn4" otherAttr=" id=\"OpenWin_Mdse{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"250\""/></td>
											<td>
												<ezf:select name="splyAgmtFreqTpCd_A" ezfName="splyAgmtFreqTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="splyAgmtFreqTpCd_P" ezfDispName="splyAgmtFreqTpDescTxt_P" otherAttr=" style=\"width: 200px\""/>
											</td>
											<td><ezf:inputText name="splyAgmtPlnFirstQty_A" ezfName="splyAgmtPlnFirstQty_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
											<td><ezf:inputText name="splyAgmtPlnQty_A" ezfName="splyAgmtPlnQty_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
											<td><ezf:inputText name="splyAgmtPlnSqNum_A" ezfName="splyAgmtPlnSqNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
											<td>
												<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="00/00/0000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
												<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');" >
											</td>
											<td>
												<ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="00/00/0000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
												<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');" >
											</td>
											<td><ezf:label name="xxDt10Dt_AC" ezfName="xxDt10Dt_AC" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="xxFullNm_AC" ezfName="xxFullNm_AC" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"71\""/></td>
											<td><ezf:label name="xxDt10Dt_AU" ezfName="xxDt10Dt_AU" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="xxFullNm_AU" ezfName="xxFullNm_AU" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"71\""/></td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
											<td><input type="text" size="12" maxlength="12" value="XXXXXXXXX1XX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn4" type="button" value="Search" onClick="sendServer(this)" name="itemSearch"></td>
											<td><input type="text" size="25" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7X" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7X" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
											<td><input type="text" size="12" maxlength="12" value="XXXXXXXXX1XX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn4" type="button" value="Search" onClick="sendServer(this)" name="itemSearch"></td>
											<td><input type="text" size="25" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
											<td><input type="text" size="12" maxlength="12" value="XXXXXXXXX1XX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn4" type="button" value="Search" onClick="sendServer(this)" name="itemSearch"></td>
											<td><input type="text" size="25" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
											<td><input type="text" size="12" maxlength="12" value="XXXXXXXXX1XX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn4" type="button" value="Search" onClick="sendServer(this)" name="itemSearch"></td>
											<td><input type="text" size="25" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
											<td><input type="text" size="12" maxlength="12" value="XXXXXXXXX1XX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn4" type="button" value="Search" onClick="sendServer(this)" name="itemSearch"></td>
											<td><input type="text" size="25" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
											<td><input type="text" size="12" maxlength="12" value="XXXXXXXXX1XX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn4" type="button" value="Search" onClick="sendServer(this)" name="itemSearch"></td>
											<td><input type="text" size="25" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
											<td><input type="text" size="12" maxlength="12" value="XXXXXXXXX1XX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn4" type="button" value="Search" onClick="sendServer(this)" name="itemSearch"></td>
											<td><input type="text" size="25" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
											<td><input type="text" size="12" maxlength="12" value="XXXXXXXXX1XX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn4" type="button" value="Search" onClick="sendServer(this)" name="itemSearch"></td>
											<td><input type="text" size="25" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
											<td><input type="text" size="12" maxlength="12" value="XXXXXXXXX1XX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn4" type="button" value="Search" onClick="sendServer(this)" name="itemSearch"></td>
											<td><input type="text" size="25" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
											<td><input type="text" size="12" maxlength="12" value="XXXXXXXXX1XX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn4" type="button" value="Search" onClick="sendServer(this)" name="itemSearch"></td>
											<td><input type="text" size="25" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
											<td><input type="text" size="12" maxlength="12" value="XXXXXXXXX1XX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn4" type="button" value="Search" onClick="sendServer(this)" name="itemSearch"></td>
											<td><input type="text" size="25" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
											<td><input type="text" size="12" maxlength="12" value="XXXXXXXXX1XX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn4" type="button" value="Search" onClick="sendServer(this)" name="itemSearch"></td>
											<td><input type="text" size="25" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type="checkbox" value="Y" name="xxChkBox" ezfname="xxChkBox" ezfhyo="A"></td>
											<td><input type="text" size="12" maxlength="12" value="XXXXXXXXX1XX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn4" type="button" value="Search" onClick="sendServer(this)" name="itemSearch"></td>
											<td><input type="text" size="25" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="10" value="XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" />00/00/0000</td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
									</ezf:skip>
								</table>
							</div>
						</div>
					</div>
				</td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
</left>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A");
</script>

<%-- **** Task specific area ends here **** --%>
