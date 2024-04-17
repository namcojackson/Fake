<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160712121258 --%>
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
			<input type="hidden" name="pageID" value="NMAL7160Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="CSMP Manual Claim Search&Entry">
			

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### FROM HEADER ################################################### --%>

				<%-- ###TAB - HEAD --%>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<!-- include S21BusinessProcessTAB --> 
										<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
									</div>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				<div class="pTab_BODY_In">
					<table border="0" cellspacing="0" cellpadding="0" style="text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="40">
						<col width="110">
						<col width="160">
						<col width="100">
						<col width="160">
						<col width="90">
						<col width="120">
						<col width="10">
						<col width="130">
						<col width="170">
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td class="stab">Order Number(*)</td>
							<td><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="12345678" otherAttr=" size=\"15\" maxlength=\"8\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="mdseCd_LK" ezfName="mdseCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ItemCd" otherAttr=" id=\"mdseCd_LK\" ezfanchortext=\"\"">Item Code(*)</ezf:anchor></td>
							<td><ezf:inputText name="mdseCd" ezfName="mdseCd" value="1234A123AA" otherAttr=" size=\"15\" maxlength=\"16\" ezftoupper=\"\""/></td>
							<td class="stab">CSA Number(*)</td>
							<td><ezf:inputText name="dlrRefNum" ezfName="dlrRefNum" value="12345678" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td class="stab">Invoice Number(*)</td>
							<td><ezf:inputText name="invNum" ezfName="invNum" value="1234567890123" otherAttr=" size=\"15\" maxlength=\"13\" ezftoupper=\"\""/></td>
							<td class="stab">CSMP Number(*)</td>
							<td><ezf:inputText name="csmpContrNum" ezfName="csmpContrNum" value="123456789012345" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
							<td class="stab">Error Date</td>
							<td><ezf:inputText name="csmpInvErrDt_FR" ezfName="csmpInvErrDt_FR" value="01/04/2016" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('csmpInvErrDt_FR', 4);"></td>
							<td>-</td>
							<td><ezf:inputText name="csmpInvErrDt_TO" ezfName="csmpInvErrDt_TO" value="01/04/2016" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('csmpInvErrDt_TO', 4);"></td>
							<td align="right">
								<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
							</td>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>

<%-- #################################################### TO HEADER ################################################### --%>
					<hr>
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<!-- Prev/Next Page-->
					<table cellpadding="0" cellspacing="0">
						<col width="1107">
						<tr>
							<td align="right">
								<table cellpadding="0" cellspacing="0">
									<tr>
										<td align="right">
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="A" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
											<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
											<jsp:param name="ViewMode"       value="FULL" />
											</jsp:include>
										<ezf:skip>
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="44"  align="center">
												<col width="20"  align="center">
												<col width="20"  align="center">
												<col width="20"  align="center">
												<col width="20"  align="center">
												<col width="20"  align="center">
												<col width="10"  align="center">
												<col width="46"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="30"  align="center">
												<col width="10">
												<col width="50">
												<col width="20">
												<col width="50">
												<col width="50">
												<tr>
													<td class="stab">Results</td>
													<td class="stab">1</td>
													<td class="stab">-</td>
													<td class="stab">40</td>
													<td class="stab">of</td>
													<td class="stab">41</td>
													<td>&nbsp;</td>
													<td class="stab">Showing</td>
													<td class="pOut">1</td>
													<td class="stab">/</td>
													<td class="pOut">2</td>
													<td class="stab">page</td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Jump" name="PageJump" ezfname="PageJump" onclick="sendServer(this)"></td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" ezfname="PagePrev" onclick="sendServer(this)"></td>
													<td><input type="button" class="pBtn3" value="Next" name="PageNext" ezfname="PageNext" onclick="sendServer(this)"></td>
												</tr>
											</table>
										</ezf:skip>	
										</td>
									</tr>
								</table>
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
											<col align="center" width="115">	<!-- Order Number -->
											<col align="center" width="125">	<!-- Line Number -->
											<col align="center" width="115">	<!-- Invoice Number -->
											<col align="center" width="110">	<!-- Item Code -->
											<col align="center" width="235">	<!-- Description -->
											<col align="center" width="130">	<!-- CSMP Number -->
											<col align="center" width="145">	<!-- CSA Number -->
											<col align="center" width="100">	<!-- Error Date -->
											<tr height="30">
												<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs">Order Number</td>
												<td class="pClothBs">Line Number</td>
												<td class="pClothBs">Invoice Number</td>
												<td class="pClothBs">Item Code</td>
												<td class="pClothBs">Description</td>
												<td class="pClothBs">CSMP Number</td>
												<td class="pClothBs">CSA Number</td>
												<td class="pClothBs">Error Date</td>
											</tr>
										</table>
									</div>
									
									<div id="RowTBL" style="width:1122px; height:440px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
										<table border="1" cellpadding="1" cellspacing="0" id="A" width="1105" style="table-layout:fixed;">
											<col align="center" width="030">	<!-- Check Box -->
											<col align="left" width="115">	<!-- Order Number -->
											<col align="left" width="125">	<!-- Line Number -->
											<col align="left" width="115">	<!-- Invoice Number -->
											<col align="left" width="110">	<!-- Item Code -->
											<col align="left" width="235">	<!-- Description -->
											<col align="left" width="130">	<!-- CSMP Number -->
											<col align="left" width="145">	<!-- CSA Number -->
											<col align="left" width="100">	<!-- Error Date -->
											<ezf:row ezfHyo="A">
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:label name="cpoOrdNum_a" ezfName="cpoOrdNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="dplyLineNum_A" ezfName="dplyLineNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="invNum_A" ezfName="invNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="WWWWWWWWW1WWWWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													<td><ezf:label name="csmpContrNum_A" ezfName="csmpContrNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="dlrRefNum_A" ezfName="dlrRefNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="csmpInvErrDt_A" ezfName="csmpInvErrDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
													
													<ezf:inputHidden name="invBolLineNum_A" ezfName="invBolLineNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"invBolLineNum_A{EZF_ROW_NUMBER}\""/>
													<ezf:inputHidden name="invLineNum_A" ezfName="invLineNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"invLineNum_A{EZF_ROW_NUMBER}\""/>
													<ezf:inputHidden name="invLineSubNum_A" ezfName="invLineSubNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"invLineSubNum_A{EZF_ROW_NUMBER}\""/>
													<ezf:inputHidden name="invLineSubTrxNum_A" ezfName="invLineSubTrxNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"invLineSubTrxNum_A{EZF_ROW_NUMBER}\""/>
												</tr>
											</ezf:row>
												
										</table>
									</div>
								</div>
							</td>
						</tr>
					</table>
					</div>
				</div>
<%-- #################################################### DETAIL ################################################### --%>
			</td>
		</tr>
	</table>
</center>




			

<%-- **** Task specific area ends here **** --%>
