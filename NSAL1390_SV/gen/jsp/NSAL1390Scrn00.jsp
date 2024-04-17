<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170824094137 --%>
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
			<input type="hidden" name="pageID" value="NSAL1390Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Renewal/Annual Escalation Letter Setup">


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
					<table border="0" width="99%" align="center">
						<col width="" align="" valign="top">
						<col width="" align="" valign="top">
						<col width="" align="" valign="top">
						<col width="" align="" valign="bottom">
						<tr>
							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="40">
									<col width="40">
									<col width="90">
									<col width="20">
									<col width="50">
									<col width="250">
									<col width="20">
									<col width="50">
									<col width="270">
									<col width="20">
									<col width="220">

									<tr height="21">
										<td>&nbsp;</td>
										<td class="stab">LOB</td>
										<td><ezf:select name="lineBizTpCd_H" ezfName="lineBizTpCd_H" ezfBlank="1" ezfCodeName="lineBizTpCd_PL" ezfDispName="lineBizTpDescTxt_PL" otherAttr=" style=\"width: 80px\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="svcRgPk_LK" ezfName="svcRgPk_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Region" >Region</ezf:anchor></td>
										<td><ezf:inputText name="svcRgPk_H" ezfName="svcRgPk_H" value="123456" otherAttr=" size=\"8\" maxlength=\"28\" style=\"text-align:right\" ezftoupper=\"\""/><ezf:inputText name="svcRgDescTxt_H" ezfName="svcRgDescTxt_H" value="ESS - CENTRAL REGION" otherAttr=" size=\"25\" maxlength=\"50\" tabindex=\"-1\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="svcContrBr_LK" ezfName="svcContrBr_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_Branch" >Branch(*)</ezf:anchor></td>
										<td><ezf:inputText name="svcContrBrCd_H" ezfName="svcContrBrCd_H" value="XX3" otherAttr=" size=\"5\" maxlength=\"3\" ezftoupper=\"\""/><ezf:inputText name="svcContrBrDescTxt_H" ezfName="svcContrBrDescTxt_H" value="010-SOFTWARE" otherAttr=" size=\"25\" maxlength=\"50\" tabindex=\"-1\""/></td>
										<td>&nbsp;</td>
										<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search\""/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

<%-- #################################################### TO HEADER ################################################### --%>
					<hr>
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<!-- Prev/Next Page-->
					<table cellpadding="0" cellspacing="0">
						<col width="1077">
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
								<div >
									<div id="TopTBL" style="overflow-y:none; overflow-x:none;">
										<table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1055px"  style="margin-right:30px; margin-left:20px" >
											<col align="center" width="030">	<!-- Check Box -->
											<col align="center" width="085">	<!-- LOB -->
											<col align="center" width="290">	<!-- Region -->
											<col align="center" width="260">	<!-- Branch -->
											<col align="center" width="130">	<!-- Renewal Letter -->
											<col align="center" width="130">	<!-- Annual Escalation -->
											<col align="center" width="130">	<!-- Print old Price -->
											<tr height="30">
												<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'lineBizTpCd_A' )">LOB<img id="sortIMG.lineBizTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcRgDescTxt_A' )">Region<img id="sortIMG.svcRgDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcContrBrDescTxt_A' )">Branch<img id="sortIMG.svcContrBrDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs">Renewal Letter</td>
												<td class="pClothBs">Annual Escalation</td>
												<td class="pClothBs">Print old Price</td>
											</tr>
										</table>
									</div>
									
									<div id="RowTBL" style="width:1072px; height:440px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
										<table border="1" cellpadding="1" cellspacing="0" id="A" width="1055"  style="table-layout:fixed; margin-left:20px">
											<col align="center" width="030">	<!-- Check Box -->
											<col align="center" width="085">	<!-- LOB -->
											<col align="center" width="290">	<!-- Region -->
											<col align="right"  width="260">	<!-- Branch -->
											<col align="center" width="130">	<!-- Renewal Letter -->
											<col align="center" width="130">	<!-- Annual Escalation -->
											<col align="center" width="130">	<!-- Print old Price -->
											<ezf:row ezfHyo="A">
												<tr id="id_row{EZF_ROW_NUMBER}" height="28">
													<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:select name="lineBizTpCd_A" ezfName="lineBizTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="lineBizTpCd_PL" ezfDispName="lineBizTpDescTxt_PL" otherAttr=" style=\"width: 80px\""/>
													</td>
													<td><ezf:inputText name="svcRgPk_A" ezfName="svcRgPk_A" value="123456" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"28\" style=\"text-align:right\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_RegionLine" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_RegionLine\""/><ezf:inputText name="svcRgDescTxt_A" ezfName="svcRgDescTxt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" size=\"25\" maxlength=\"50\""/></td>
													<td><ezf:inputText name="svcContrBrCd_A" ezfName="svcContrBrCd_A" value="XX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"3\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_BranchLine" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_BranchLine\""/><ezf:inputText name="svcContrBrDescTxt_A" ezfName="svcContrBrDescTxt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" size=\"25\" maxlength=\"50\""/></td>
													<td><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A2{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputCheckBox name="xxChkBox_A3" ezfName="xxChkBox_A3" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A3{EZF_ROW_NUMBER}\""/></td>
													<td><ezf:inputCheckBox name="xxChkBox_A4" ezfName="xxChkBox_A4" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A4{EZF_ROW_NUMBER}\""/></td>
												</tr>
											</ezf:row>
												
										</table>
									</div>
								</div>
							</td>
						</tr>
					</table>
					<table  border="0" style="table-layout:fixed;width=96%; margin-left:20px">
						<col align="left" width="250">
						<col>
						<col align="right">
						<tr>
							<td>
								<ezf:inputButton name="Add" value="Add" htmlClass="pBtn8" />
								<ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn8" />
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
