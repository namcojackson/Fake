<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230714084652 --%>
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
			<input type="hidden" name="pageID" value="NLBL3140Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Allocation Rule Setup by BU, WH and Order Category">

<%-- #################################################### FROM HEADER ################################################### --%>
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
			<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%"><li title = "Supersession" class="pTab_ON" ><a href="#">Allc Rule Set</a></li></td>
								<td valign="bottom" align="center"><a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a></td>
								<td valign="bottom" align="center"><a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a></td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
			
			<div class="pTab_BODY">
			<table style="table-layout:fixed;">
				<col width="50">
				<col width="40">
				<col width="220">
				<col width="80">
				<col width="220">
				<col width="80">
				<col width="210">
				<col width="80">
						<tr>
							<td>&nbsp;</td>
							<td class="stab">BU</td>
							<td><ezf:select name="lineBizTpCd" ezfName="lineBizTpCd" ezfBlank="1" ezfCodeName="lineBizTpCd_PL" ezfDispName="lineBizTpDescTxt_PL" otherAttr=" style=\"width: 80px\""/></td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SearchOrdCatg_Link" >Order Category</ezf:anchor></td>
							<td><ezf:inputText name="dsOrdCatgDescTxt" ezfName="dsOrdCatgDescTxt" value="WWWWWWWWW1WWWWWW" otherAttr=" size=\"18\" maxlength=\"16\" ezftoupper=\"\""/>
							    <ezf:inputHidden name="dsOrdCatgCd" ezfName="dsOrdCatgCd" /></td>
							<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_07" ezfEmulateBtn="1" ezfGuard="OpenWin_COA_Product_Link" otherAttr=" tabindex=\"-1\"">Prod Code</ezf:anchor></td>
							<td colspan="3">
								<ezf:inputText name="coaProdCd" ezfName="coaProdCd" value="XX" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>
								<ezf:inputText name="coaProdNm" ezfName="coaProdNm" value="XX" otherAttr=" size=\"25\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SearchWh_Link" >WH(*)</ezf:anchor></td>
							<td><ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="WWWWWW" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/>
								<ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="WWWWWWWWW1WWWWWWWW" otherAttr=" size=\"18\" maxlength=\"16\" ezftoupper=\"\" tabindex=\"-1\""/></td>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SearchWh_Link" >SWH(*)</ezf:anchor></td>
							<td><ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="WWWWWWWW" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/>
								<ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" value="WWWWWWWWW1WWWWWWWW" otherAttr=" size=\"18\" maxlength=\"16\" ezftoupper=\"\" tabindex=\"-1\""/></td>
							<td class="stab">Allocation Type</td>
							<td><ezf:select name="hardAllocTpCd" ezfName="hardAllocTpCd" ezfBlank="1" ezfCodeName="hardAllocTpCd_PL" ezfDispName="hardAllocTpDescTxt_PL" otherAttr=" style=\"width: 80px\""/></td>
							
							<td valign="top" align="right" width="300" >
								<table border="0" width="100%" height="100%">
									<tr align="right" valign="bottom">
										<td colspan="2" valign="bottom" align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />&nbsp;&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
			</table>
			
<%-- #################################################### TO HEADER ################################################### --%>
					<hr>
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>

			<table cellpadding="0" cellspacing="0">
				<col width="350">
				<col width="750">
				<tr>
					<td align="left" style="padding-left:3px;">
						<ezf:inputButton name="AddNewRow" value="Add New Row" htmlClass="pBtn8" />
					</td>
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
											<td><input type="button" class="pBtn3" value="Jump" onclick="sendServer(this)" name="PageJump"></td>
											<td>&nbsp;</td>
											<td><input type="button" class="pBtn3" value="Prev" onclick="sendServer(this)" name="PagePrev"></td>
											<td><input type="button" class="pBtn3" value="Next" onclick="sendServer(this)" name="PageNext"></td>
										</tr>
									</table>
								</ezf:skip>	
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>


<%-- ######################################## TO (COMMON)PAGENATION #################################### --%>
			
<%-- #################################################### FROM DETAIL ################################################### --%>
					<div>
					<table border="0" width="100%">
						<tr>
							<%-- ######################################## DETAIL ######################################## --%>
							<table border="0" cellpadding="1" cellspacing="0" width="100%" align="center">
								<col align="left" valign="top">
								<tr>
									<td>
										<div id="parentBoxA">
											<div style="float:left; display:block"> <!-- left table -->
												<div id='leftTblHead' style="display:block;">
												</div>
												<div id='leftTbl' style="float:left; display:block; height:220px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
												</div>
											</div>  <!-- left table -->
											<div style="float:left"> <!-- right table -->
												<div id='rightTblHead' style="width:1105px; display:block; overflow:hidden; margin:0px;padding:0px;">
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1373px" style="margin-right:20px" >
														<col align="center" width="30">
														<col align="center" width="90">
														<col align="center" width="290">
														<col align="center" width="230">
														<col align="center" width="210">
														<!-- -->
														<col align="center" width="290">
														
														<col align="center" width="90">
														<col align="center" width="160">
														<tr height="30">
															<td class="pClothBs">&nbsp;</td>
															<td class="pClothBs">BU</td>
															<td class="pClothBs">WH</td>
															<td class="pClothBs">SWH</td>
															<td class="pClothBs">Order Category</td>
															<!-- -->
															<td class="pClothBs">PC</td>
															
															<td class="pClothBs">Allocation Type</td>
															<td class="pClothBs">Time Fence(Days)</td>
														</tr>
													</table>
												</div><!-- 'rightTblHead' -->
												<div id="rightTbl" style="width:1122px; height:400px; display:block; overflow:scroll; margin:0px; padding:0px;" >
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1373px" >
														<col align="center" width="30">	
														<col align="center" width="90">
														<col align="center" width="290">
														<col align="center" width="230">
														<col align="center" width="210">
														<!-- -->
														<col align="center" width="290">
														
														<col align="center" width="90">
														<col align="center" width="160">
														<ezf:row ezfHyo="A">
														<tr id="id_row{EZF_ROW_NUMBER}" height="35">
															<ezf:inputHidden name="mdseWhCondPk_A" ezfName="mdseWhCondPk_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mdseWhCondPk_A\""/>
															<ezf:inputHidden name="ezUpTime_A" ezfName="ezUpTime_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"ezUpTime_A\""/>
															<ezf:inputHidden name="ezUpTimeZone_A" ezfName="ezUpTimeZone_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"ezUpTimeZone_A\""/>
															<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:select name="lineBizTpCd_A" ezfName="lineBizTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="lineBizTpCd_PL" ezfDispName="lineBizTpDescTxt_PL" otherAttr=" style=\"width: 80px\""/></td>
															<td>
																<ezf:inputButton name="OpenWin_SearchWh" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
																<ezf:inputText name="rtlWhCd_A" ezfName="rtlWhCd_A" value="WWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" style=\"padding:0px;\" ezftoupper=\"\""/>
																<ezf:inputButton name="SetWh" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
																<ezf:inputText name="rtlWhNm_A" ezfName="rtlWhNm_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"18\" style=\"padding:0px;\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputButton name="OpenWin_SearchSwh" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
																<ezf:inputText name="rtlSwhCd_A" ezfName="rtlSwhCd_A" value="WWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" style=\"padding:0px;\" ezftoupper=\"\""/>
																<ezf:inputButton name="SetSwh" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
																<ezf:inputText name="rtlSwhNm_A" ezfName="rtlSwhNm_A" value="WWWWWWWWW1WW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputButton name="OpenWin_SearchOrdCatg" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
																<ezf:inputText name="dsOrdCatgDescTxt_A" ezfName="dsOrdCatgDescTxt_A" value="WWWWWWWWW1WWWWWWWWW2W" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"20\" style=\"padding:0px;\""/>
															</td>
															<!-- -->
															<td>
																<ezf:inputButton name="OpenWin_COA_Product" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
																<ezf:inputText name="coaProdCd_A" ezfName="coaProdCd_A" value="WWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"8\" style=\"padding:0px;\" ezftoupper=\"\""/>
																<ezf:inputButton name="SetPC" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
																<ezf:inputText name="coaProdNm_A" ezfName="coaProdNm_A" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\" style=\"padding:0px;\" tabindex=\"-1\""/>
															</td>
															
															<td><ezf:select name="hardAllocTpCd_A" ezfName="hardAllocTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="hardAllocTpCd_PL" ezfDispName="hardAllocTpDescTxt_PL" otherAttr=" style=\"width: 80px\""/></td>
															<td>
																<ezf:inputText name="tmFenceDaysAot_A" ezfName="tmFenceDaysAot_A" value="WWWWWWWWW1WWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"18\" style=\"padding:0px;\" style=\"text-align:right;\""/>
															</td>
														</tr>
														</ezf:row>
													</table>
												</div><!-- rightTbl -->
											</div><!-- right table -->
										</div><!-- parent box -->
									</td>
								</tr>
							</table>
							<table border="0">
								<col align="left" width="10">
								<tr>
									<td>&nbsp;</td>
									<td>
										<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn7" />
										<ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="pBtn7" />
										<ezf:inputButton name="LineCancel" value="Line Cancel" htmlClass="pBtn7" />
									</td>
								</tr>
							</table>
						</tr>
					</table>
					</div>
<%-- #################################################### DETAIL ################################################### --%>
			</div>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A");
</script>

<%-- **** Task specific area ends here **** --%>
