<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171114102930 --%>
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
			<input type="hidden" name="pageID" value="NWCL0100Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="EasyPAC1 Contract Maintenance">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
<%-- #################################################### FROM HEADER ################################################### --%>
				<div>
					<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="122">
						<col width="345">
						<col width="110">
						<col width="310">
						<col width="">
						<tr>
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk_S0" ezfName="srchOptPk_S0" ezfBlank="1" ezfCodeName="srchOptPk_L0" ezfDispName="srchOptNm_L0" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk_S0\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td class="stab"><ezf:inputText name="srchOptNm_S0" ezfName="srchOptNm_S0" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td>
								<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
								<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					
					<hr>
					
					<table border="0" cellspacing="0" cellpadding="0" style="text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="85">
						<col width="380">
						<col width="150">
						<col width="300">
						<tr>
							<td class="stab"><ezf:anchor name="billToAcctNum_LK" ezfName="billToAcctNum_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_MultiBillToAcct" otherAttr=" id=\"billToAcctNum_LK\" ezfanchortext=\"\"">Bill To Account</ezf:anchor></td>
							<td class="stab"><ezf:inputText name="xxBillToAcctCdSrchTxt_H0" ezfName="xxBillToAcctCdSrchTxt_H0" value="12345678901234567890123456789012345678901234567890" otherAttr=" size=\"50\" maxlength=\"240\" ezftoupper=\"\""/></td>
							<td><ezf:inputCheckBox name="xxChkBox_H0" ezfName="xxChkBox_H0" value="Y" otherAttr=" id=\"xxChkBox_H0\""/>Show Active Only</td>
							<td>
								<ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					<br>
<%-- #################################################### TO HEADER ################################################### --%>

<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<!-- Prev/Next Page-->
					<table width="1120" height="35">
						<tr>
							<td width="3">&nbsp;</td>
							<td width="200">
								<ezf:inputButton name="InsertLine" value="Insert Line" htmlClass="pBtn8" />
								<ezf:inputButton name="DeleteLine" value="Delete Line" htmlClass="pBtn8" />
							</td>
							<td align="right" style="padding-right:10px;">

<%-- After Convert to JSP, this area suppose to be deleted [FROM] 
								<table border="0" cellpadding="0" cellspacing="0">
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
										<td class="pOut">6</td>
										<td class="stab">of</td>
										<td class="pOut">6</td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" otherAttr=" id=\"btnPrev\""/></td>
										<td></td>
										<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" otherAttr=" id=\"btnNext\""/></td>
									</tr>
								</table>
 After Convert to JSP, this area suppose to be deleted [TO] --%>
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
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- #################################################### FROM DETAIL ################################################### --%>
					<div>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<col align="left" valign="top" width="1107">
						<tr>
							<!-- @@@@@ Left -->
							<td style="padding-left:3px;">
								<div>
									<div id="TopTBL" style="overflow-y:none; overflow-x:none;">
										<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1105px" style="margin-right:20px" >
											<col align="center" width="030">	<!-- Check Box -->
											<col align="center" width="095">	<!-- Bill To Acct Cd -->
											<col align="center" width="120">	<!-- Bill To Acct Nm -->
											<col align="center" width="150">	<!-- Customer Group -->
											<col align="center" width="105">	<!-- Valid From -->
											<col align="center" width="105">	<!-- Valid To -->
											<col align="center" width="070">	<!-- Rate -->
											<col align="center" width="070">	<!-- Monthly Quote -->
											<col align="center" width="050">	<!-- Auto Debit -->
											<col align="center" width="070">	<!-- Created By -->
											<col align="center" width="080">	<!-- Created On -->
											<col align="center" width="070">	<!-- Changed By -->
											<col align="center" width="080">	<!-- Changed On -->
											<tr id="id_row${EZF_ROW_NUMBER}" height="30">
												<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustAcctCd_A0' )">Bill To Acct Cd
													<img id="sortIMG.billToCustAcctCd_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
												</td>
												<td class="pClothBs">Bill To Acct Nm</td>
												<td class="pClothBs">Customer Group</td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A0' )">Valid From
													<img id="sortIMG.effFromDt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
												</td>
												<td class="pClothBs">Valid To</td>
												<td class="pClothBs">Rate</td>
												<td class="pClothBs">Monthly Quota</td>
												<td class="pClothBs">Auto Debit</td>
												<td class="pClothBs">Created By</td>
												<td class="pClothBs">Created On</td>
												<td class="pClothBs">Changed By</td>
												<td class="pClothBs">Changed On</td>
											</tr>
										</table>
									</div>
									
									<div id="RowTBL" style="width:1122px; height:422px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
										<table border="1" cellpadding="1" cellspacing="0" id="A" width="1105" style="table-layout:fixed;">
											<col align="center" width="030">	<!-- Check Box -->
											<col align="center" width="095">	<!-- Bill To Acct Cd -->
											<col align="center" width="120">	<!-- Bill To Acct Nm -->
											<col align="center" width="150">	<!-- Customer Group -->
											<col align="center" width="105">	<!-- Valid From -->
											<col align="center" width="105">	<!-- Valid To -->
											<col align="center" width="070">	<!-- Rate -->
											<col align="center" width="070">	<!-- Monthly Quote -->
											<col align="center" width="050">	<!-- Auto Debit -->
											<col align="center" width="070">	<!-- Created By -->
											<col align="center" width="080">	<!-- Created On -->
											<col align="center" width="070">	<!-- Changed By -->
											<col align="center" width="080">	<!-- Changed On -->
											<ezf:row ezfHyo="A">
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><ezf:inputCheckBox name="xxChkBox_A0" ezfName="xxChkBox_A0" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A0{EZF_ROW_NUMBER}\""/></td>
													<td>
														<ezf:inputText name="billToCustAcctCd_A0" ezfName="billToCustAcctCd_A0" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"20\""/>
														<ezf:anchor name="xxLinkAncr_A0" ezfName="xxLinkAncr_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToAcct" otherAttr=" ezfanchortext=\"\"">
															A
														</ezf:anchor>
													</td>
													<td><ezf:inputText name="dsAcctNm_A0" ezfName="dsAcctNm_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"320\""/></td>
													<td>
														<ezf:inputText name="prcGrpPk_A0" ezfName="prcGrpPk_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"28\""/>
														<ezf:anchor name="xxLinkProt_A0" ezfName="xxLinkProt_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_CustPrcGrp" otherAttr=" ezfanchortext=\"\"">C</ezf:anchor>
														<ezf:inputText name="prcGrpNm_A0" ezfName="prcGrpNm_A0" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"50\""/>
													</td>
													<td>
														<ezf:inputText name="effFromDt_A0" ezfName="effFromDt_A0" value="01/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td>
														<ezf:inputText name="effThruDt_A0" ezfName="effThruDt_A0" value="12/31/2017" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4, '{EZF_ROW_NUMBER}');" >
													</td>
													<td><ezf:inputText name="splyPgmUnitAmtRate_A0" ezfName="splyPgmUnitAmtRate_A0" value="0.0123456789" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"26\""/></td>
													<td><ezf:inputText name="splyPgmMlyQuotQty_A0" ezfName="splyPgmMlyQuotQty_A0" value="123.0001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"14\""/></td>
													<td><ezf:inputCheckBox name="autoDrCratFlg_A0" ezfName="autoDrCratFlg_A0" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"autoDrCratFlg_A0{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputText name="xxPsnNm_A0" ezfName="xxPsnNm_A0" value="Createpson Name" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"16\""/></td>
													<td><ezf:inputText name="contrCratDt_A0" ezfName="contrCratDt_A0" value="02/23/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
													<td><ezf:inputText name="xxPsnNm_A1" ezfName="xxPsnNm_A1" value="Upparson Name" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"16\""/></td>
													<td><ezf:inputText name="contrUpdDt_A0" ezfName="contrUpdDt_A0" value="03/24/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												</tr>
											</ezf:row>
											<ezf:skip>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><input type="checkbox" ezfhyo="A" name="xxChkBox_A0" ezfname="xxChkBox_A0" value="Y" id="xxChkBox_A0{EZF_ROW_NUMBER}"></td>
													<td>
														<input type="text" ezfhyo="A"  name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" class="pPro" size="9" maxlength="20" value="0000001">
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="billToCustAcctCd_A0" ezfname="billToCustAcctCd_A0" ezfhyo="A" ezfanchortext>
															A
														</a>
													</td>
													<td><input type="text" ezfhyo="A"  name="dsAcctNm_A0" ezfname="dsAcctNm_A0" class="pPro" size="20" maxlength="320" value="WWWWWWWWW1"></td>
													<td>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="5" maxlength="28" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" />
														<a href="#" onclick="sendServer('OpenWin_BillToAcct')" name="xxLinkAncr_A0" ezfname="xxLinkAncr_A0" ezfhyo="A" ezfanchortext>C</a>
														<input type="text" ezfhyo="A"  name="prcGrpNm_A0" ezfname="prcGrpNm_A0" class="pPro" size="11" maxlength="50" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" />
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effFromDt_A0" ezfname="effFromDt_A0" class="pPro" size="10" maxlength="10" value="02/01/2015">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effFromDt_A0', 4);" >
													</td>
													<td>
														<input type="text" ezfhyo="A"  name="effThruDt_A0" ezfname="effThruDt_A0" class="pPro" size="10" maxlength="10" value="02/11/2016">
														<img src="./img/calendar.gif" class="pCalendar" tabindex="1" onclick="calendar('effThruDt_A0', 4);" >
													</td>
													<td><input type="text" ezfhyo="A"  name="splyPgmUnitAmtRate_A0" ezfname="splyPgmUnitAmtRate_A0" class="pPro" size="8" maxlength="26" value="0.0123"></td>
													<td><input type="text" ezfhyo="A"  name="splyPgmMlyQuotQty_A0" ezfname="splyPgmMlyQuotQty_A0" class="pPro" size="8" maxlength="14" value="123.001"></td>
													<td><input type="checkbox" ezfhyo="A" name="autoDrCratFlg_A0" ezfname="autoDrCratFlg_A0" value="Y" id="autoDrCratFlg_A0{EZF_ROW_NUMBER}"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A0" ezfname="xxPsnNm_A0" class="pPro" size="10" maxlength="16" value="Q11111"></td>
													<td><input type="text" ezfhyo="A"  name="contrCratDt_A0" ezfname="contrCratDt_A0" class="pPro" size="8" maxlength="10" value="03/01/2016"></td>
													<td><input type="text" ezfhyo="A"  name="xxPsnNm_A1" ezfname="xxPsnNm_A1" class="pPro" size="10" maxlength="16" value="Q22222"></td>
													<td><input type="text" ezfhyo="A"  name="contrUpdDt_A0" ezfname="contrUpdDt_A0" class="pPro" size="8" maxlength="10" value="03/02/2016"></td>
												</tr>
												
											</ezf:skip>
										</table>
									</div>
								</div>
							</td>
						</tr>
					</table>
					</div>
<%-- #################################################### DETAIL ################################################### --%>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
