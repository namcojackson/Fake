<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180608101312 --%>
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
			<input type="hidden" name="pageID" value="NMAL7150Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="CSMP Contract Sycronization Errors Correction">
			
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
										<li title="Supply Agreement Plan Search" class="pTab_ON"><a href="#">CSMP Reprc</a></li>
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
<%------------------------------------%>
<%-- Header							--%>
<%------------------------------------%>
				<div class="pTab_BODY_In">
					<table width="1050px">
						<col valign="left" width="270">
						<col valign="left" width="200">
						<col valign="left" width="610">
						<tr>
							<td>
								<table>
									<tr>
										<td class="stab">Transaction Date From</td>
										<td>
											<ezf:inputText name="effFromDt_TD" ezfName="effFromDt_TD" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_TD', 4);" >
										</td>
									</tr>
									<tr>
										<td class="stab">Transaction Date To</td>
										<td>
											<ezf:inputText name="effThruDt_TD" ezfName="effThruDt_TD" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_TD', 4);" >
										</td>
									</tr>
								</table>
							</td>
							<td>
								<fieldset>
									<legend style="font-size:12px;">Process Flag</legend>
									<table>
										<tr>
											<td><ezf:inputCheckBox name="xxChkBox_E" ezfName="xxChkBox_E" value="Y" /></td>
											<td class="stab">Error</td>
											<td><ezf:inputCheckBox name="xxChkBox_R" ezfName="xxChkBox_R" value="Y" /></td>
											<td class="stab">Reprocess</td>
											<td><ezf:inputCheckBox name="xxChkBox_D" ezfName="xxChkBox_D" value="Y" /></td>
											<td class="stab">Delete</td>
										</tr>
									</table>
								</fieldset>
							</td>
							<td>
								<table>
									<col valign="left" width="510">
									<col valign="left" width="100">
									<tr>
										<td><br></td>
									</tr>
									<tr>
										<td><br></td>
									</tr>
									<tr>
										<td></td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" otherAttr=" id=\"search\""/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<hr style="height: 0px;" cellpadding="0">
<%------------------------------------%>
<%-- DetailButton					--%>
<%------------------------------------%>
					<table width="1050px">
						<col valign="left" width="100">
						<col valign="left" width="100">
						<col valign="left" width="100">
						<col valign="left" width="100">
						<col valign="left" width="400">
						<col valign="left" width="280">
						<tr>
							<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn8" otherAttr=" id=\"SelectAll\""/></td>
							<td><ezf:inputButton name="UnSelectAll" value="UnSelect All" htmlClass="pBtn8" otherAttr=" id=\"UnSelectAll\""/></td>
							<td><ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn8" otherAttr=" id=\"Delete\""/></td>
							<td><ezf:inputButton name="Reprocess" value="Reprocess" htmlClass="pBtn8" otherAttr=" id=\"Reprocess\""/></td>
							<td></td>
							<td>
								<div align="right">
									<ezf:skip>
										<table border="0" cellpadding="0" cellspacing="0">
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
												<td class="pOut">20</td>
												<td class="stab">of</td>
												<td class="pOut">200</td>
												<td> </td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
												<td></td>
											</tr>
										</table>
									</ezf:skip>
								</div>
								<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
								</jsp:include>
							</td>
					</table>
<%------------------------------------%>
<%-- Detail							--%>
<%------------------------------------%>
					<div id="rightTblHead" style="width:1050px; display:block; overflow:hidden; margin:0px;padding:0px;">
							<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="1050px" style="margin-right:0px">
								<col align="center" width="40">		<!-- Check box				-->
								<col align="center" width="80">		<!-- Created Date			-->
								<col align="center" width="120">	<!-- Process Flag			-->
								<col align="center" width="260">	<!-- Error Message			-->
								<col align="center" width="160">	<!-- CSA Number				-->
								<col align="center" width="110">	<!-- Transaction Status		-->
								<col align="center" width="128">	<!-- Origin Dealer Code		-->
								<col align="center" width="150">	<!-- Account Name			-->
								<col align="center" width="130">	<!-- CSMP#					-->
								<col align="center" width="90">		<!-- Effective Date 		-->
								<col align="center" width="90">		<!-- Expiration Date		-->
								<col align="center" width="90">		<!-- Credit List			-->
								<col align="center" width="80">		<!-- Generation#			-->
								<col align="center" width="100">	<!-- Contracct Status		-->
								<col align="center" width="50">		<!-- Region					-->
								<col align="center" width="80">		<!-- CSMP Level				-->
								<col align="center" width="90">		<!-- Valid From Date		-->
								<col align="center" width="125">	<!-- Prev Contract			-->
								<col align="center" width="125">	<!-- Prev End User			-->
								<tr height="25">
									<td class="pClothBs">&nbsp;<br>&nbsp;</td>
									<td class="pClothBs">Created Date</td>
									<td class="pClothBs">Process Flag</td>
									<td class="pClothBs">Error Message</td>
									<td class="pClothBs">CSA Number</td>
									<td class="pClothBs">Transaction Status</td>
									<td class="pClothBs">Origin Dealer Code</td>
									<td class="pClothBs">Account Name</td>
									<td class="pClothBs">CSMP#</td>
									<td class="pClothBs">Effective Date</td>
									<td class="pClothBs">Expiration Date</td>
									<td class="pClothBs">Credit List</td>
									<td class="pClothBs">Generation#</td>
									<td class="pClothBs">Contract Status</td>
									<td class="pClothBs">Region</td>
									<td class="pClothBs">CSMP Level</td>
									<td class="pClothBs">Valid From Date</td>
									<td class="pClothBs">Prev Contract</td>
									<td class="pClothBs">Prev End User</td>
								</tr>
							</table>
						</div>
						<div id="rightTBL" style="width:1068px; height:433px; display:block; overflow:scroll; margin-left:19px; padding:0px;"  onScroll="synchroScrollLeft(this.id, new Array('rightTblHead'));">
							<table border="1" cellpadding="0" cellspacing="0" id="A" style="table-layout: fixed" width="1068px">
								<col align="center" width="40">		<!-- Check box				-->
								<col align="center" width="80">		<!-- Created Date			-->
								<col align="center" width="120">	<!-- Process Flag			-->
								<col align="center" width="260">	<!-- Error Message			-->
								<col align="center" width="160">	<!-- CSA Number				-->
								<col align="center" width="110">	<!-- Transaction Status		-->
								<col align="center" width="128">	<!-- Origin Dealer Code		-->
								<col align="center" width="150">	<!-- Account Name			-->
								<col align="center" width="130">	<!-- CSMP#					-->
								<col align="center" width="90">		<!-- Effective Date 		-->
								<col align="center" width="90">		<!-- Expiration Date		-->
								<col align="center" width="90">		<!-- Credit List			-->
								<col align="center" width="80">		<!-- Generation#			-->
								<col align="center" width="100">	<!-- Contracct Status		-->
								<col align="center" width="50">		<!-- Region					-->
								<col align="center" width="80">		<!-- CSMP Level				-->
								<col align="center" width="90">		<!-- Valid From Date		-->
								<col align="center" width="125">	<!-- Prev Contract			-->
								<col align="center" width="125">	<!-- Prev End User			-->
								<ezf:row ezfHyo="A">
									<tr id="id_leftA_row{EZF_ROW_NUMBER}">
										<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A{EZF_ROW_NUMBER}\""/></td>
										<td><ezf:label name="xxDt10Dt_A" ezfName="xxDt10Dt_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:inputText name="xxScrItem54Txt_A" ezfName="xxScrItem54Txt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"52\""/></td>
										<td><ezf:inputText name="csmpErrMsgTxt_A" ezfName="csmpErrMsgTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\" maxlength=\"400\""/></td>
										<td><ezf:inputText name="dlrRefNum_A" ezfName="dlrRefNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:label name="csmpTrxStsCd_A" ezfName="csmpTrxStsCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:inputText name="rtlDlrCd_A" ezfName="rtlDlrCd_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\""/></td>
										<td><ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"360\""/></td>
										<td><ezf:inputText name="xxScrItem40Txt_A" ezfName="xxScrItem40Txt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"36\""/></td>
										<td><ezf:label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:inputText name="crListTxt_A" ezfName="crListTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\""/></td>
										<td><ezf:inputText name="crListGnrnNum_A" ezfName="crListGnrnNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"5\""/></td>
										<td><ezf:label name="csmpContrStsCd_A" ezfName="csmpContrStsCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="csmpRgCd_A" ezfName="csmpRgCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="csmpCatgCd_A" ezfName="csmpCatgCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:label name="vldFromDt_A" ezfName="vldFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td><ezf:inputText name="prevCsmpNum_A" ezfName="prevCsmpNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
										<td><ezf:inputText name="prevUsrTxt_A" ezfName="prevUsrTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
									</tr>
								</ezf:row>
								<ezf:skip>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}01">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}02" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}02">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}03" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}03">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}04" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}04">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}05" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}05">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}06" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}06">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}07" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}07">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}08" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}08">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}09" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}09">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}10" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}10">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}11">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}11" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}12">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}12" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}13">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}13" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									<tr id="id_leftA_row{EZF_ROW_NUMBER}14">
										<td><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A" id="xxChkBox_A{EZF_ROW_NUMBER}14" ></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="52" value="R:Reprocess" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="35" maxlength="400" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="20" maxlength="20" value="XXXXXXXXX1XXXXXXXXX2" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXX</td>
										<td><input type="text" size="15" maxlength="20" value="XXXXXXXXX1XXXXXXXXX1" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="18" maxlength="360" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX4XXXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="10" maxlength="50" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXXX4XXXXXXXX5" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="9" maxlength="5" value="XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />X</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />XXXXXXXXX1</td>
										<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" />MM/DD/YYYY</td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
										<td><input type="text" size="15" maxlength="15" value="XXXXXXXXX1XXXXX" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
									</tr>
									
									
								</ezf:skip>
							</table>
						</div>
					</div>
				</div>
			</td>
		</tr>
	</table>
</left>

<%-- **** Task specific area ends here **** --%>
